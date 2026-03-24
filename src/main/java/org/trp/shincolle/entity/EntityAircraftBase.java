package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModSounds;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class EntityAircraftBase extends org.trp.shincolle.entity.base.EntityShincolleSimpleMob {

    private static final int LIFETIME_TICKS = 1200;
    private static final int HOST_CHECK_TIMEOUT = 20;
    private static final int INITIAL_BOOST_DURATION = 34;
    private static final double INITIAL_BOOST_SPEED = 0.375D;
    private static final double INITIAL_BOOST_Y = 0.1D;
    private static final int TARGETING_INTERVAL = 16;
    private static final int RETURN_HOME_CHECK_INTERVAL = 16;
    private static final double RETURN_MAX_DISTANCE_SQR = 4096.0D;
    private static final double TARGETING_RANGE_NORMAL = 24.0D;
    private static final double TARGETING_RANGE_AIR_ONLY = 32.0D;

    private static final float ATTACK_RANGE_LIGHT = 6.0F;
    private static final float ATTACK_RANGE_HEAVY = 16.0F;

    private static final double RAND_POS_MIN_LIGHT = 4.5D;
    private static final double RAND_POS_RAND_LIGHT = 1.5D;
    private static final double RAND_POS_MIN_HEAVY = 12.0D;
    private static final double RAND_POS_RAND_HEAVY = 4.0D;

    private static final double DEATH_GRAVITY = 0.08D;
    private static final int DEATH_TIME_BURNING = 30;
    private static final int DEATH_TIME_EXPLOSION = 90;

    private static final int AMMO_RETURN_PENALTY_LIGHT = 3;
    private static final int AMMO_RETURN_PENALTY_HEAVY = 1;

    private static final int INITIAL_AMMO_LIGHT = 9;
    private static final int INITIAL_AMMO_HEAVY = 3;

    private static final int BASE_ATTACK_SPEED_AIRCRAFT = 100;
    private static final int FIXED_ATTACK_DELAY_AIRCRAFT = 35;

    private UUID carrierId;
    private UUID targetId;
    private boolean backHome;
    private boolean missionLightAircraft;
    private int missionTick;
    private int attackDelay;
    private int maxAttackDelay;
    private int numAmmoLight;
    private int numAmmoHeavy;

    private int deathAnimTick;
    private boolean isDying;
    private double deadMotionX;
    private double deadMotionZ;

    private double[] randPos;
    private float attackRangeSq;

    protected EntityAircraftBase(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.randPos = new double[3];
    }

    public void initCarrierMission(EntityShipBase carrier, Entity target, boolean lightAircraft) {
        if (carrier == null) {
            return;
        }
        this.carrierId = carrier.getUUID();
        this.targetId = target == null ? null : target.getUUID();
        this.backHome = false;
        this.missionTick = 0;
        this.missionLightAircraft = lightAircraft;
        this.isDying = false;
        this.deathAnimTick = 0;

        if (lightAircraft) {
            this.numAmmoLight = INITIAL_AMMO_LIGHT;
            this.numAmmoHeavy = 0;
        } else {
            this.numAmmoLight = 0;
            this.numAmmoHeavy = INITIAL_AMMO_HEAVY;
        }

        float attackSpeed = carrier.getLegacyShipStats().getReloadSpeed();
        this.maxAttackDelay = (int) (BASE_ATTACK_SPEED_AIRCRAFT / attackSpeed) + FIXED_ATTACK_DELAY_AIRCRAFT;
        this.attackDelay = 0;

        float range = lightAircraft ? ATTACK_RANGE_LIGHT : ATTACK_RANGE_HEAVY;
        this.attackRangeSq = range * range;

        this.setNoGravity(true);
        this.setOwnerUUID(carrier.getOwnerUUID());
        this.setTame(true, false);

        if (target != null) {
            this.randPos[0] = target.getX();
            this.randPos[1] = target.getY();
            this.randPos[2] = target.getZ();
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.isDying) {
            this.setNoGravity(true);
            this.fallDistance = 0.0F;
        }
        updateRotation();

        if (this.level().isClientSide) {
            if (!this.isDying) {
                applyFlyParticle();
            }
        } else {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (this.isDying) {
            tickDeathAnimation();
            return;
        }

        this.missionTick++;
        if (this.attackDelay > 0) {
            this.attackDelay--;
        }

        EntityShipBase carrier = getCarrier();
        if (carrier == null || !carrier.isAlive()) {
            this.discard();
            return;
        }

        if (this.backHome) {
            handleReturnToHome(carrier);
            return;
        }

        handleInitialBoost();

        handleTargeting(carrier);

        if (this.missionTick >= LIFETIME_TICKS) {
            this.backHome = true;
            this.targetId = null;
            return;
        }

        if (this.missionLightAircraft && this.numAmmoLight <= 0) {
            this.backHome = true;
            this.targetId = null;
            return;
        }
        if (!this.missionLightAircraft && this.numAmmoHeavy <= 0) {
            this.backHome = true;
            this.targetId = null;
            return;
        }

        Entity target = getMissionTarget();
        if (target != null && target.isAlive()) {
            tickCombatMovement(target);
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isNoGravity() || !this.isDying) {
            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            super.travel(travelVector);
        }
    }

    private void tickCombatMovement(Entity target) {
        double distX = target.getX() - this.getX();
        double distY = target.getY() + 2.0D - this.getY();
        double distZ = target.getZ() - this.getZ();
        double distSq = distX * distX + distY * distY + distZ * distZ;

        if ((this.tickCount & 0xF) == 0) {
            this.randPos = findRandomPositionNearTarget(target);
        }

        double speed;
        if (distSq > this.attackRangeSq) {
            speed = 1.0D;
        } else {
            speed = 0.4D;
        }
        navigateToRandomPos(speed);

        boolean canSee = this.hasLineOfSight(target);
        if (this.attackDelay <= 0 && canSee && distSq < this.attackRangeSq) {
            EntityShipBase carrier = getCarrier();
            if (carrier != null) {
                if (this.missionLightAircraft && this.numAmmoLight > 0) {
                    attackWithLightAmmo(carrier, target);
                    this.attackDelay = this.maxAttackDelay;
                }
                if (!this.missionLightAircraft && this.numAmmoHeavy > 0) {
                    attackWithHeavyAmmo(carrier, target);
                    this.attackDelay = this.maxAttackDelay;
                }
            }
        }
    }

    private double[] findRandomPositionNearTarget(Entity target) {
        double minDist, randDist;
        if (this.missionLightAircraft) {
            minDist = RAND_POS_MIN_LIGHT;
            randDist = RAND_POS_RAND_LIGHT;
        } else {
            minDist = RAND_POS_MIN_HEAVY;
            randDist = RAND_POS_RAND_HEAVY;
        }

        double rdx = this.random.nextDouble() * randDist + minDist;
        double rdy = this.random.nextDouble() * randDist * 0.5D;
        double rdz = this.random.nextDouble() * randDist + minDist;

        Vec3 motion = this.getDeltaMovement();
        double newX = motion.x < 0.0D ? target.getX() - rdx : target.getX() + rdx;
        double newY = target.getY() + target.getBbHeight() * 0.75D + rdy;
        double newZ = motion.z < 0.0D ? target.getZ() - rdz : target.getZ() + rdz;

        return new double[]{newX, newY, newZ};
    }

    private void navigateToRandomPos(double speedFactor) {
        double dx = this.randPos[0] - this.getX();
        double dy = this.randPos[1] - this.getY();
        double dz = this.randPos[2] - this.getZ();
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (dist < 1.0E-4D) {
            return;
        }
        double maxSpeed = 0.35D * speedFactor;
        double steerGain = 0.15D;

        Vec3 desired = new Vec3(dx / dist, dy / dist, dz / dist).scale(maxSpeed);
        Vec3 current = this.getDeltaMovement();
        Vec3 next = current.scale(1.0D - steerGain).add(desired.scale(steerGain));
        this.setDeltaMovement(next);
        this.hasImpulse = true;
    }

    private void handleInitialBoost() {
        if (this.missionTick >= INITIAL_BOOST_DURATION) {
            return;
        }
        Entity target = getMissionTarget();
        if (target == null) {
            return;
        }
        double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double distSqrt = Mth.sqrt((float) (dx * dx + dz * dz));
        if (distSqrt > 1.0E-4D) {
            this.setDeltaMovement(
                    dx / distSqrt * INITIAL_BOOST_SPEED,
                    INITIAL_BOOST_Y,
                    dz / distSqrt * INITIAL_BOOST_SPEED
            );
            this.hasImpulse = true;
        }
    }

    private void handleTargeting(EntityShipBase carrier) {
        if (this.missionTick % TARGETING_INTERVAL != 0) {
            return;
        }
        if (this.missionTick < HOST_CHECK_TIMEOUT) {
            return;
        }

        Entity currentTarget = getMissionTarget();
        boolean needsNewTarget = currentTarget == null || !currentTarget.isAlive()
                || isFriendlyTarget(carrier, currentTarget);

        if (!needsNewTarget) {
            return;
        }

        Entity newTarget = findNewTarget(carrier);

        if (newTarget == null) {
            Entity carrierTarget = carrier.getTarget();
            if (carrierTarget != null && carrierTarget.isAlive() && !isFriendlyTarget(carrier, carrierTarget)) {
                newTarget = carrierTarget;
            }
        }

        if (newTarget != null) {
            this.targetId = newTarget.getUUID();
            this.backHome = false;
        } else {
            this.targetId = null;
            this.backHome = true;
        }
    }

    private void handleReturnToHome(EntityShipBase carrier) {
        if (!this.isAlive()) {
            return;
        }

        double distSq = this.distanceToSqr(carrier);
        double arrivalDist = Math.pow(2.0D + carrier.getBbHeight(), 2.0D);

        if (distSq <= arrivalDist) {
            returnSummonResources(carrier);
            this.discard();
            return;
        }

        Vec3 homePos = carrier.position().add(0.0D, carrier.getBbHeight() + 1.0D, 0.0D);
        moveToPoint(homePos, 0.55D, 0.12D);

        if (this.tickCount % RETURN_HOME_CHECK_INTERVAL == 0) {
            if (this.distanceToSqr(carrier) >= RETURN_MAX_DISTANCE_SQR) {
                this.discard();
            }
        }
    }

    private void returnSummonResources(EntityShipBase carrier) {
        int returnLight = Math.max(0, this.numAmmoLight - AMMO_RETURN_PENALTY_LIGHT);
        int returnHeavy = Math.max(0, this.numAmmoHeavy - AMMO_RETURN_PENALTY_HEAVY);

        // TODO: return actual ammo items to carrier inventory if the mod supports it
        carrier.returnAircraftToDeck(this.missionLightAircraft);
    }

    private void attackWithLightAmmo(EntityShipBase carrier, Entity target) {
        if (this.numAmmoLight > 0) {
            this.numAmmoLight--;
        }
        this.playSound(ModSounds.SHIP_MACHINEGUN.get(), 1.0F, 1.0F);
        float atk = Math.max(2.0F, carrier.getLegacyShipStats().getFirepower() * 0.35F);
        target.hurt(this.damageSources().mobProjectile(this, carrier), atk);
    }

    private void attackWithHeavyAmmo(EntityShipBase carrier, Entity target) {
        if (this.numAmmoHeavy > 0) {
            this.numAmmoHeavy--;
        }
        float atk = Math.max(4.0F, carrier.getLegacyShipStats().getFirepower() * 0.55F);
        if (this.level() instanceof ServerLevel serverLevel) {
            float missileDamage = atk * 1.4F;
            org.trp.shincolle.entity.projectile.EntityAbyssMissile missile =
                    new org.trp.shincolle.entity.projectile.EntityAbyssMissile(
                            serverLevel, this, target, missileDamage,
                            org.trp.shincolle.entity.projectile.EntityAbyssMissile.MoveType.ARC,
                            0.7F, 1.04F, 1.04F, null,
                            200, 3.5F);
            serverLevel.addFreshEntity(missile);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (!this.level().isClientSide && result && !this.isAlive() && !this.isDying) {
            this.isDying = true;
            this.deathAnimTick = 0;
            this.setNoGravity(false);
            Vec3 motion = this.getDeltaMovement();
            this.deadMotionX = motion.x;
            this.deadMotionZ = motion.z;
            this.setHealth(1.0F);
        }
        return result;
    }

    private void tickDeathAnimation() {
        this.deathAnimTick++;
        this.setNoGravity(false);

        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(this.deadMotionX, motion.y - DEATH_GRAVITY, this.deadMotionZ);
        this.hasImpulse = true;

        if (this.level() instanceof ServerLevel serverLevel) {
            if (this.deathAnimTick % 2 == 0) {
                double range = this.getBbWidth() * 0.5D;
                for (int i = 0; i < 3; i++) {
                    serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE,
                            this.getX() - range + this.random.nextDouble() * range * 2.0D,
                            this.getY() + this.getBbHeight() * 0.3D + this.random.nextDouble() * 0.3D,
                            this.getZ() - range + this.random.nextDouble() * range * 2.0D,
                            1, 0.0D, 0.0D, 0.0D, 0.02D);
                }
            }

            if (this.deathAnimTick >= DEATH_TIME_EXPLOSION - 1) {
                for (int i = 0; i < 12; i++) {
                    double ran1 = this.getBbWidth() * (this.random.nextFloat() - 0.5F);
                    double ran2 = this.getBbWidth() * (this.random.nextFloat() - 0.5F);
                    serverLevel.sendParticles(ParticleTypes.LAVA,
                            this.getX() + ran1, this.getY() + this.getBbHeight() * 0.3D, this.getZ() + ran2,
                            1, 0.0D, 0.0D, 0.0D, 0.0D);
                    if ((i & 3) == 0) {
                        serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                                this.getX() + ran2, this.getY() + this.getBbHeight() * 0.5D, this.getZ() + ran1,
                                1, 0.0D, 0.0D, 0.0D, 0.0D);
                    }
                }
            }

            if (this.deathAnimTick >= DEATH_TIME_EXPLOSION) {
                for (int k = 0; k < 20; k++) {
                    double d2 = this.random.nextGaussian() * 0.02D;
                    double d0 = this.random.nextGaussian() * 0.02D;
                    double d1 = this.random.nextGaussian() * 0.02D;
                    serverLevel.sendParticles(ParticleTypes.POOF,
                            this.getX() + (this.random.nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(),
                            this.getY() + (this.random.nextFloat() * this.getBbHeight()),
                            this.getZ() + (this.random.nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(),
                            1, d2, d0, d1, 0.05D);
                }
                this.discard();
            }
        }
    }

    @Override
    public boolean isOnFire() {
        if (this.isDying && this.deathAnimTick > DEATH_TIME_BURNING) {
            return true;
        }
        return super.isOnFire();
    }


    @Override
    public void move(MoverType type, Vec3 pos) {
        super.move(type, pos);
        this.checkInsideBlocks();
    }

    private void moveToPoint(Vec3 target, double maxSpeed, double steerGain) {
        Vec3 toTarget = target.subtract(this.position());
        double dist = toTarget.length();
        if (dist < 1.0E-6D) {
            this.setDeltaMovement(this.getDeltaMovement().scale(0.8D));
            return;
        }

        Vec3 desired = toTarget.scale(1.0D / dist).scale(maxSpeed);
        Vec3 current = this.getDeltaMovement();
        Vec3 next = current.scale(1.0D - steerGain).add(desired.scale(steerGain));
        this.setDeltaMovement(next);
        this.hasImpulse = true;
    }

    private void updateRotation() {
        Vec3 delta = this.getDeltaMovement();
        if (delta.lengthSqr() < 1.0E-7D) {
            return;
        }
        double horizontal = Math.sqrt(delta.x * delta.x + delta.z * delta.z);
        float targetYaw = (float) (Math.toDegrees(Math.atan2(delta.z, delta.x)) - 90.0D);
        float targetPitch = (float) (-Math.toDegrees(Math.atan2(delta.y, horizontal)));

        float smoothFactor = 0.4F;
        float currentYaw = this.getYRot();
        float currentPitch = this.getXRot();

        float yawDiff = Mth.wrapDegrees(targetYaw - currentYaw);
        float smoothYaw = currentYaw + yawDiff * smoothFactor;
        float smoothPitch = currentPitch + (targetPitch - currentPitch) * smoothFactor;

        this.yRotO = currentYaw;
        this.yBodyRotO = currentYaw;
        this.yHeadRotO = currentYaw;
        this.xRotO = currentPitch;

        this.setYRot(smoothYaw);
        this.yBodyRot = smoothYaw;
        this.yHeadRot = smoothYaw;
        this.setXRot(smoothPitch);
    }


    @Override
    protected void pushEntities() {
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    public boolean onClimbable() {
        return false;
    }

    protected void applyFlyParticle() {
    }


    @Nullable
    private EntityShipBase getCarrier() {
        if (this.carrierId == null || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = serverLevel.getEntity(this.carrierId);
        if (entity instanceof EntityShipBase ship) {
            return ship;
        }
        return null;
    }

    @Nullable
    private Entity getMissionTarget() {
        if (this.targetId == null || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }
        return serverLevel.getEntity(this.targetId);
    }

    @Nullable
    private Entity findNewTarget(EntityShipBase carrier) {
        double range = carrier.getStateFlag(19) ? TARGETING_RANGE_AIR_ONLY : TARGETING_RANGE_NORMAL;
        AABB box = this.getBoundingBox().inflate(range, range, range);
        List<Entity> entities = this.level().getEntities(this, box, entity -> {
            if (entity == null || !entity.isAlive() || entity == this) return false;
            return entity instanceof LivingEntity;
        });

        Entity nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Entity entity : entities) {
            if (isFriendlyTarget(carrier, entity)) {
                continue;
            }
            double dist = this.distanceToSqr(entity);
            if (dist < nearestDistance) {
                nearestDistance = dist;
                nearest = entity;
            }
        }
        return nearest;
    }

    private boolean isFriendlyTarget(EntityShipBase carrier, Entity target) {
        if (target == carrier) {
            return true;
        }
        if (target instanceof Player player && Objects.equals(player.getUUID(), carrier.getOwnerUUID())) {
            return true;
        }
        if (target instanceof TamableAnimal tamable && Objects.equals(tamable.getOwnerUUID(), carrier.getOwnerUUID())) {
            return true;
        }
        if (target instanceof EntityShipBase ship && Objects.equals(ship.getOwnerUUID(), carrier.getOwnerUUID())) {
            return true;
        }
        if (target instanceof EntityAircraftBase aircraft) {
            EntityShipBase otherCarrier = aircraft.getCarrier();
            return otherCarrier != null && Objects.equals(otherCarrier.getOwnerUUID(), carrier.getOwnerUUID());
        }
        return false;
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.carrierId != null) {
            compound.putUUID("CarrierId", this.carrierId);
        }
        if (this.targetId != null) {
            compound.putUUID("TargetId", this.targetId);
        }
        compound.putBoolean("BackHome", this.backHome);
        compound.putBoolean("MissionLight", this.missionLightAircraft);
        compound.putInt("MissionTick", this.missionTick);
        compound.putInt("AttackDelay", this.attackDelay);
        compound.putInt("MaxAttackDelay", this.maxAttackDelay);
        compound.putInt("NumAmmoLight", this.numAmmoLight);
        compound.putInt("NumAmmoHeavy", this.numAmmoHeavy);
        compound.putFloat("AttackRangeSq", this.attackRangeSq);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.carrierId = compound.hasUUID("CarrierId") ? compound.getUUID("CarrierId") : null;
        this.targetId = compound.hasUUID("TargetId") ? compound.getUUID("TargetId") : null;
        this.backHome = compound.getBoolean("BackHome");
        this.missionLightAircraft = compound.getBoolean("MissionLight");
        this.missionTick = compound.getInt("MissionTick");
        this.attackDelay = compound.getInt("AttackDelay");
        this.maxAttackDelay = compound.getInt("MaxAttackDelay");
        this.numAmmoLight = compound.getInt("NumAmmoLight");
        this.numAmmoHeavy = compound.getInt("NumAmmoHeavy");
        this.attackRangeSq = compound.getFloat("AttackRangeSq");

        if (this.attackRangeSq <= 0.0F) {
            this.attackRangeSq = (this.missionLightAircraft ? ATTACK_RANGE_LIGHT : ATTACK_RANGE_HEAVY);
            this.attackRangeSq *= this.attackRangeSq;
        }
        if (this.maxAttackDelay <= 0) {
            this.maxAttackDelay = FIXED_ATTACK_DELAY_AIRCRAFT + BASE_ATTACK_SPEED_AIRCRAFT;
        }
        if (this.attackDelay <= 0) {
            this.attackDelay = this.maxAttackDelay + HOST_CHECK_TIMEOUT;
        }
    }

    public boolean isMissionLightAircraft() {
        return this.missionLightAircraft;
    }

    protected boolean isDefaultLightAircraft() {
        return true;
    }
}
