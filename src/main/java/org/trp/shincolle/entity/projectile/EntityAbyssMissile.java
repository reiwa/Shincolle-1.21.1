package org.trp.shincolle.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModSounds;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntityAbyssMissile extends Entity {
    private static final double MIN_DIST_FOR_ARC = 4.0D;
    private static final double ARC_ACCEL_LIMIT = 0.15D;
    private static final double TORPEDO_VEL_MULTIPLIER = 0.85D;
    private static final double TORPEDO_ACCEL_MULTIPLIER = 1.05D;
    private static final int TORPEDO_START_DELAY = 3;
    private static final float ARC_FACTOR_DEFAULT = 0.35F;

    public enum MoveType {
        DIRECT,
        ARC,
        TORPEDO,
        ARC_HOMING,
        PRESET_VELOCITY;

        public static MoveType fromId(int id) {
            if (id < 0 || id >= values().length) {
                return DIRECT;
            }
            return values()[id];
        }
    }

    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityAbyssMissile.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Optional<UUID>> TARGET_UUID = SynchedEntityData.defineId(EntityAbyssMissile.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(EntityAbyssMissile.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(EntityAbyssMissile.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> LIFE = SynchedEntityData.defineId(EntityAbyssMissile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> EXPLOSION_RADIUS = SynchedEntityData.defineId(EntityAbyssMissile.class, EntityDataSerializers.FLOAT);

    private int age;
    private MoveType moveType = MoveType.DIRECT;
    private double velX;
    private double velY;
    private double velZ;
    private double accY1;
    private double accY2;
    private int arcTick;
    private int arcSwitchTick;
    private boolean torpedoStarted;
    private int torpedoDelay;
    private Vec3 targetPos;

    public EntityAbyssMissile(EntityType<EntityAbyssMissile> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public EntityAbyssMissile(Level level, Entity owner, Entity target, float damage, float speed, int life, float explosionRadius) {
        this(ModEntities.ABYSS_MISSILE.get(), level);
        if (owner != null) {
            this.setOwner(owner);
            this.setPos(owner.getX(), owner.getY() + owner.getBbHeight() * 0.6D, owner.getZ());
        }
        if (target != null) {
            this.setTarget(target);
            this.targetPos = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        }
        this.setDamage(damage);
        this.setSpeed(speed);
        this.setLife(life);
        this.setExplosionRadius(explosionRadius);
        initializeMovement(MoveType.DIRECT, speed, 1.04F, 1.04F, null);
    }

    public EntityAbyssMissile(Level level, Entity owner, Entity target, float damage, MoveType moveType,
                              float vel0, float accY1, float accY2, Vec3 presetVelocity,
                              int life, float explosionRadius) {
        this(ModEntities.ABYSS_MISSILE.get(), level);
        if (owner != null) {
            this.setOwner(owner);
            this.setPos(owner.getX(), owner.getY() + owner.getBbHeight() * 0.6D, owner.getZ());
        }
        if (target != null) {
            this.setTarget(target);
            this.targetPos = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        }
        this.setDamage(damage);
        this.setSpeed(vel0);
        this.setLife(life);
        this.setExplosionRadius(explosionRadius);
        initializeMovement(moveType, vel0, accY1, accY2, presetVelocity);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(OWNER_UUID, Optional.empty());
        builder.define(TARGET_UUID, Optional.empty());
        builder.define(DAMAGE, 6.0F);
        builder.define(SPEED, 0.7F);
        builder.define(LIFE, 200);
        builder.define(EXPLOSION_RADIUS, 3.5F);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("Owner")) {
            this.entityData.set(OWNER_UUID, Optional.of(tag.getUUID("Owner")));
        }
        if (tag.hasUUID("Target")) {
            this.entityData.set(TARGET_UUID, Optional.of(tag.getUUID("Target")));
        }
        this.entityData.set(DAMAGE, tag.getFloat("Damage"));
        this.entityData.set(SPEED, tag.getFloat("Speed"));
        this.entityData.set(LIFE, tag.getInt("Life"));
        this.entityData.set(EXPLOSION_RADIUS, tag.getFloat("ExplosionRadius"));
        this.moveType = MoveType.fromId(tag.getInt("MoveType"));
        this.velX = tag.getDouble("VelX");
        this.velY = tag.getDouble("VelY");
        this.velZ = tag.getDouble("VelZ");
        this.accY1 = tag.getDouble("AccY1");
        this.accY2 = tag.getDouble("AccY2");
        this.arcTick = tag.getInt("ArcTick");
        this.arcSwitchTick = tag.getInt("ArcSwitch");
        this.torpedoStarted = tag.getBoolean("TorpedoStarted");
        this.torpedoDelay = tag.getInt("TorpedoDelay");
        if (tag.contains("TargetX")) {
            this.targetPos = new Vec3(tag.getDouble("TargetX"), tag.getDouble("TargetY"), tag.getDouble("TargetZ"));
        }
        this.setDeltaMovement(this.velX, this.velY, this.velZ);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        getOwnerUuid().ifPresent(uuid -> tag.putUUID("Owner", uuid));
        getTargetUuid().ifPresent(uuid -> tag.putUUID("Target", uuid));
        tag.putFloat("Damage", getDamage());
        tag.putFloat("Speed", getSpeed());
        tag.putInt("Life", getLife());
        tag.putFloat("ExplosionRadius", getExplosionRadius());
        tag.putInt("MoveType", this.moveType.ordinal());
        tag.putDouble("VelX", this.velX);
        tag.putDouble("VelY", this.velY);
        tag.putDouble("VelZ", this.velZ);
        tag.putDouble("AccY1", this.accY1);
        tag.putDouble("AccY2", this.accY2);
        tag.putInt("ArcTick", this.arcTick);
        tag.putInt("ArcSwitch", this.arcSwitchTick);
        tag.putBoolean("TorpedoStarted", this.torpedoStarted);
        tag.putInt("TorpedoDelay", this.torpedoDelay);
        if (this.targetPos != null) {
            tag.putDouble("TargetX", this.targetPos.x);
            tag.putDouble("TargetY", this.targetPos.y);
            tag.putDouble("TargetZ", this.targetPos.z);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.age++;
            if (this.age > getLife()) {
                onImpact(null);
                return;
            }
        }

        Vec3 delta;
        if (!this.level().isClientSide) {
            updateVelocityByMoveType();
            delta = new Vec3(this.velX, this.velY, this.velZ);
            this.setDeltaMovement(delta);
        } else {
            delta = this.getDeltaMovement();
        }
        Vec3 start = this.position();
        Vec3 end = start.add(delta);

        BlockHitResult blockHit = this.level().clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (blockHit.getType() != HitResult.Type.MISS) {
            end = blockHit.getLocation();
        }

        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(this.level(), this, start, end,
                this.getBoundingBox().expandTowards(delta).inflate(1.0D), this::canHitEntity);
        if (entityHit != null) {
            onImpact(entityHit.getEntity());
            return;
        }

        if (blockHit.getType() == HitResult.Type.BLOCK) {
            onImpact(null);
            return;
        }

        move(MoverType.SELF, delta);
        updateRotationFromMovement(delta);
    }

    private void updateHomingMovement() {
        Entity target = getTargetEntity();
        if (target == null || !target.isAlive()) {
            return;
        }
        Vec3 aim = getAimVector(target);
        if (aim.lengthSqr() < 1.0E-6D) {
            return;
        }
        Vec3 desired = aim.scale(getSpeed());
        Vec3 current = new Vec3(this.velX, this.velY, this.velZ);
        Vec3 blended = current.scale(0.8D).add(desired.scale(0.2D));
        this.velX = blended.x;
        this.velY = blended.y;
        this.velZ = blended.z;
    }

    private void updateRotationFromMovement(Vec3 delta) {
        double d0 = delta.horizontalDistance();
        float yaw = (float) (Mth.atan2(delta.x, delta.z) * (180.0F / Math.PI));
        float pitch = (float) (Mth.atan2(delta.y, d0) * (180.0F / Math.PI));

        this.setYRot(Mth.rotLerp(0.2F, this.yRotO, yaw));
        this.setXRot(Mth.rotLerp(0.2F, this.xRotO, pitch));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    private void initializeMovement(MoveType moveType, float vel0, float accY1, float accY2, Vec3 presetVelocity) {
        this.moveType = moveType == null ? MoveType.DIRECT : moveType;
        this.accY1 = accY1;
        this.accY2 = accY2;

        Vec3 targetVector = resolveTargetVector();
        if (this.moveType == MoveType.PRESET_VELOCITY && presetVelocity != null) {
            this.velX = presetVelocity.x;
            this.velY = presetVelocity.y;
            this.velZ = presetVelocity.z;
            return;
        }

        if (targetVector == null) {
            Vec3 fallback = this.getLookAngle().scale(vel0);
            this.velX = fallback.x;
            this.velY = fallback.y;
            this.velZ = fallback.z;
            return;
        }

        switch (this.moveType) {
            case DIRECT -> setDirectMovement(targetVector, vel0);
            case ARC -> initializeArcMovement(targetVector, vel0);
            case TORPEDO -> initializeTorpedoMovement(targetVector, vel0);
            case ARC_HOMING -> {
                setDirectMovement(targetVector, vel0);
                this.accY1 = -Math.abs(this.accY1) * 0.035D;
                this.accY2 = -Math.abs(this.accY2) * 0.035D;
            }
            case PRESET_VELOCITY -> setDirectMovement(targetVector, vel0);
        }
    }

    private Vec3 resolveTargetVector() {
        Vec3 targetVector = null;
        if (this.targetPos != null) {
            targetVector = this.targetPos.subtract(this.position());
        } else {
            Entity target = getTargetEntity();
            if (target != null) {
                targetVector = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D).subtract(this.position());
            }
        }
        return targetVector;
    }

    private void setDirectMovement(Vec3 targetVector, float velocity) {
        Vec3 dir = targetVector.normalize();
        this.velX = dir.x * velocity;
        this.velY = dir.y * velocity;
        this.velZ = dir.z * velocity;
    }

    private void initializeArcMovement(Vec3 targetVector, float initialVelocity) {
        Vec3 to = targetVector;
        double dx = to.x;
        double dz = to.z;
        double dxz = Math.sqrt(dx * dx + dz * dz);
        if (dxz <= MIN_DIST_FOR_ARC) {
            setDirectMovement(to, initialVelocity);
            return;
        }
        double t = dxz / initialVelocity;
        double addHeight = to.length() * ARC_FACTOR_DEFAULT;
        double dy = Math.abs(to.y);

        double nx = dx / dxz;
        double nz = dz / dxz;

        this.velX = nx * initialVelocity;
        this.velZ = nz * initialVelocity;

        double t0;
        double t1;
        if (to.y < 1.0) {
            double hy = Math.sqrt(addHeight / (addHeight + dy));
            t0 = Math.floor(t / (1.0 + hy));
            t1 = Math.floor(t * hy / (1.0 + hy));
            this.velY = 2.0 * (addHeight + dy) / t0;
            this.accY1 = -this.velY / t0;
            this.accY2 = -2.0 * addHeight / (t1 * t1);
        } else {
            double hy = Math.sqrt(addHeight / (addHeight + dy));
            t0 = Math.floor(t * hy / (1.0 + hy));
            t1 = Math.floor(t / (1.0 + hy));
            this.accY1 = -2.0 * addHeight / (t0 * t0);
            this.velY = -this.accY1 * t0;
            this.accY2 = -2.0 * (addHeight + dy) / (t1 * t1);
        }
        if (Math.abs(this.accY1) > ARC_ACCEL_LIMIT || Math.abs(this.accY2) > ARC_ACCEL_LIMIT) {
            setDirectMovement(to, initialVelocity);
            return;
        }
        this.arcTick = 0;
        this.arcSwitchTick = (int) Math.max(1, t0);
    }

    private void initializeTorpedoMovement(Vec3 targetVector, float initialVelocity) {
        Vec3 dir = targetVector.normalize();
        this.velX = dir.x * initialVelocity * 0.6D;
        this.velY = 0.1D;
        this.velZ = dir.z * initialVelocity * 0.6D;
        this.accY1 = -Math.abs(this.accY1) * 0.035D;
        this.torpedoStarted = false;
        this.torpedoDelay = TORPEDO_START_DELAY;
    }

    private void updateVelocityByMoveType() {
        switch (this.moveType) {
            case DIRECT -> {

            }
            case ARC -> {
                if (this.arcTick <= this.arcSwitchTick) {
                    this.velY += this.accY1;
                } else {
                    this.velY += this.accY2;
                }
                this.arcTick++;
            }
            case ARC_HOMING -> {
                updateHomingMovement();
                this.velY += this.accY1;
            }
            case TORPEDO -> updateTorpedoMovement();
            case PRESET_VELOCITY -> {

            }
        }
    }

    private void updateTorpedoMovement() {
        if (!this.torpedoStarted) {
            this.velX *= TORPEDO_VEL_MULTIPLIER;
            this.velZ *= TORPEDO_VEL_MULTIPLIER;
            this.velY += this.accY1;
            if (this.isInWater()) {
                this.torpedoStarted = true;
                this.torpedoDelay = TORPEDO_START_DELAY;
            }
            return;
        }

        if (this.torpedoDelay > 0) {
            this.torpedoDelay--;
            return;
        }

        double accel = this.accY2 != 0.0 ? this.accY2 : TORPEDO_ACCEL_MULTIPLIER;
        this.velX *= accel;
        this.velY *= accel;
        this.velZ *= accel;
    }

    private boolean canHitEntity(Entity entity) {
        Entity owner = getOwnerEntity();
        return entity.isPickable() && entity.isAlive() && entity != owner;
    }

    private void onImpact(Entity hit) {
        if (this.level().isClientSide) {
            this.discard();
            return;
        }
        ServerLevel serverLevel = (ServerLevel) this.level();
        spawnImpactParticles(serverLevel);
        this.playSound(ModSounds.SHIP_EXPLODE.get(), 0.7F,
                this.getRandom().nextFloat() * 0.12F + 0.98F);
        applyExplosionDamage(serverLevel, hit);
        this.discard();
    }

    private void spawnImpactParticles(ServerLevel serverLevel) {
        double posX = this.getX();
        double posY = this.getY();
        double posZ = this.getZ();
        serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, posX, posY + 1.0D, posZ,
                1, 0.0D, 0.0D, 0.0D, 0.0D);
        for (int i = 0; i < 24; ++i) {
            double ran1 = (this.random.nextFloat() * 6.0F) - 3.0F;
            double ran2 = (this.random.nextFloat() * 6.0F) - 3.0F;
            serverLevel.sendParticles(ParticleTypes.LAVA,
                    posX + ran1, posY + 1.0D, posZ + ran2,
                    1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }

    private void applyExplosionDamage(ServerLevel serverLevel, Entity directHit) {
        float radius = getExplosionRadius();
        float damage = getDamage();
        Entity owner = getOwnerEntity();
        DamageSource source = owner instanceof net.minecraft.world.entity.LivingEntity livingOwner
                ? this.damageSources().mobAttack(livingOwner)
                : this.damageSources().generic();

        List<Entity> targets = serverLevel.getEntities(this, this.getBoundingBox().inflate(radius),
                entity -> entity.isAlive() && entity.isPickable() && !isFriendlyTarget(owner, entity));
        for (Entity entity : targets) {
            entity.hurt(source, damage);
        }
        if (directHit != null && directHit.isAlive() && !isFriendlyTarget(owner, directHit)) {
            directHit.hurt(source, damage);
        }
    }

    private boolean isFriendlyTarget(Entity owner, Entity target) {
        if (owner == target) return true;

        UUID ownerId = null;
        if (owner instanceof TamableAnimal t1) {
            ownerId = t1.getOwnerUUID();
        } else if (owner instanceof Player p1) {
            ownerId = p1.getUUID();
        }

        if (ownerId == null) return false;

        UUID targetId = null;
        if (target instanceof TamableAnimal t2) {
            targetId = t2.getOwnerUUID();
        } else if (target instanceof Player p2) {
            targetId = p2.getUUID();
        }

        return ownerId.equals(targetId);
    }

    private Vec3 getAimVector(Entity target) {
        if (target == null) {
            return Vec3.ZERO;
        }
        Vec3 from = this.position();
        Vec3 to = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        Vec3 dir = to.subtract(from);
        if (dir.lengthSqr() < 1.0E-6D) {
            return Vec3.ZERO;
        }
        return dir.normalize();
    }

    public void setOwner(Entity owner) {
        this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
    }

    public Entity getOwnerEntity() {
        Optional<UUID> ownerUuid = getOwnerUuid();
        if (ownerUuid.isEmpty() || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }
        return serverLevel.getEntity(ownerUuid.get());
    }

    public Optional<UUID> getOwnerUuid() {
        return this.entityData.get(OWNER_UUID);
    }

    public void setTarget(Entity target) {
        this.entityData.set(TARGET_UUID, Optional.of(target.getUUID()));
    }

    public Entity getTargetEntity() {
        Optional<UUID> targetUuid = getTargetUuid();
        if (targetUuid.isEmpty() || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }
        return serverLevel.getEntity(targetUuid.get());
    }

    public Optional<UUID> getTargetUuid() {
        return this.entityData.get(TARGET_UUID);
    }

    public void setDamage(float damage) {
        this.entityData.set(DAMAGE, damage);
    }

    public float getDamage() {
        return this.entityData.get(DAMAGE);
    }

    public void setSpeed(float speed) {
        this.entityData.set(SPEED, speed);
    }

    public float getSpeed() {
        return this.entityData.get(SPEED);
    }

    public void setLife(int life) {
        this.entityData.set(LIFE, life);
    }

    public int getLife() {
        return this.entityData.get(LIFE);
    }

    public void setExplosionRadius(float radius) {
        this.entityData.set(EXPLOSION_RADIUS, radius);
    }

    public float getExplosionRadius() {
        return this.entityData.get(EXPLOSION_RADIUS);
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.level().isClientSide) {
            return false;
        }
        onImpact(null);
        return true;
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        moveFunction.accept(passenger, this.getX(), this.getY(), this.getZ());
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
