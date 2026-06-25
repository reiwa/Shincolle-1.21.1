package org.trp.shincolle.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import org.trp.shincolle.entity.base.path.ShipLegacyNavigation;
import org.trp.shincolle.entity.base.path.ShipMoveControl;
import org.trp.shincolle.init.ModSounds;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class EntitySummonBase extends EntityShincolleSimpleMob {

    protected static final int LIFETIME_TICKS = 1200;

    protected UUID carrierId;
    protected UUID targetId;
    protected int missionTick;
    protected int numAmmoLight;
    protected int numAmmoHeavy;
    protected float attackRangeSq;

    protected EntitySummonBase(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.numAmmoLight = 6;
        this.numAmmoHeavy = 0;
        this.attackRangeSq = 16.0F;
        this.moveControl = new ShipMoveControl(this, 30.0F);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.LAVA, 0.0F);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SummonAttackGoal(this));
        this.goalSelector.addGoal(2, new SummonFollowCarrierGoal(this, 1.2D));
        this.goalSelector.addGoal(3, new net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new net.minecraft.world.entity.ai.goal.LookAtPlayerGoal(this, net.minecraft.world.entity.player.Player.class, 8.0F));
        this.goalSelector.addGoal(5, new net.minecraft.world.entity.ai.goal.RandomLookAroundGoal(this));
    }

    public void performAttack(LivingEntity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        EntityShipBase carrier = getCarrier();
        float damage = 4.0F;
        if (carrier != null) {
            damage = Math.max(2.0F, carrier.getLegacyShipStats().getFirepower() * 0.35F);
        }
        
        boolean hurt = target.hurt(this.damageSources().mobAttack(this), damage);
        if (hurt && carrier != null) {
            carrier.applyAttackEffects(target);
        }
        
        serverLevel.sendParticles(net.minecraft.core.particles.ParticleTypes.CRIT, target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(), 8, 0.2D, 0.2D, 0.2D, 0.1D);
        this.playSound(ModSounds.SHIP_FIRELIGHT.get(), 1.0F, 1.0F);
    }

    public float getAttackRangeSq() {
        return this.attackRangeSq;
    }

    private static class SummonAttackGoal extends net.minecraft.world.entity.ai.goal.Goal {
        private final EntitySummonBase mob;
        private int attackDelay;

        public SummonAttackGoal(EntitySummonBase mob) {
            this.mob = mob;
            this.setFlags(java.util.EnumSet.of(net.minecraft.world.entity.ai.goal.Goal.Flag.MOVE, net.minecraft.world.entity.ai.goal.Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = mob.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void tick() {
            LivingEntity target = mob.getTarget();
            if (target == null) return;

            mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            double distSq = mob.distanceToSqr(target);

            if (distSq > mob.getAttackRangeSq()) {
                mob.getNavigation().moveTo(target, 1.2D);
            } else {
                mob.getNavigation().stop();
                if (this.attackDelay <= 0) {
                    mob.performAttack(target);
                    this.attackDelay = 20;
                }
            }

            if (this.attackDelay > 0) {
                this.attackDelay--;
            }
        }
    }

    private static class SummonFollowCarrierGoal extends net.minecraft.world.entity.ai.goal.Goal {
        private final EntitySummonBase mob;
        private final double speed;
        private int timeToRecalcPath;

        public SummonFollowCarrierGoal(EntitySummonBase mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            this.setFlags(java.util.EnumSet.of(net.minecraft.world.entity.ai.goal.Goal.Flag.MOVE, net.minecraft.world.entity.ai.goal.Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            EntityShipBase carrier = mob.getCarrier();
            return carrier != null && carrier.isAlive() && mob.distanceToSqr(carrier) > 64.0D && mob.getTarget() == null;
        }

        @Override
        public void tick() {
            EntityShipBase carrier = mob.getCarrier();
            if (carrier == null) return;

            mob.getLookControl().setLookAt(carrier, 30.0F, 30.0F);
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                mob.getNavigation().moveTo(carrier, this.speed);
            }
        }
    }

    public void initSummon(EntityShipBase carrier, Entity target, int scaleLevel) {
        if (carrier == null) {
            return;
        }
        this.carrierId = carrier.getUUID();
        this.targetId = target == null ? null : target.getUUID();
        this.missionTick = 0;
        this.setScaleLevel(scaleLevel);

        double offsetX = (this.random.nextDouble() * 3.0D - 1.5D);
        double offsetZ = (this.random.nextDouble() * 3.0D - 1.5D);
        this.moveTo(this.getX() + offsetX, this.getY(), this.getZ() + offsetZ, this.getYRot(), this.getXRot());

        this.setOwnerUUID(carrier.getOwnerUUID());
        this.setTame(true, false);

        float maxHealth = 10.0f + carrier.getLegacyShipStats().getMaxHealth() * 0.2f;
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.setHealth(maxHealth);

        float speed = 0.25f + carrier.getLegacyShipStats().getMoveSpeed() * 0.1f;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);

        float damage = Math.max(2.0f, carrier.getLegacyShipStats().getFirepower() * 0.5f);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(damage);
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(32.0D);

        this.attackRangeSq = 16.0f * 16.0f;

        if (target instanceof LivingEntity livingTarget) {
            this.setTarget(livingTarget);
        }
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
        compound.putInt("MissionTick", this.missionTick);
        compound.putInt("NumAmmoLight", this.numAmmoLight);
        compound.putInt("NumAmmoHeavy", this.numAmmoHeavy);
        compound.putFloat("AttackRangeSq", this.attackRangeSq);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.carrierId = compound.hasUUID("CarrierId") ? compound.getUUID("CarrierId") : null;
        this.targetId = compound.hasUUID("TargetId") ? compound.getUUID("TargetId") : null;
        this.missionTick = compound.getInt("MissionTick");
        this.numAmmoLight = compound.getInt("NumAmmoLight");
        this.numAmmoHeavy = compound.getInt("NumAmmoHeavy");
        this.attackRangeSq = compound.getFloat("AttackRangeSq");
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            updateServerLogic();
        }
    }

    protected void updateServerLogic() {
        this.missionTick++;

        EntityShipBase carrier = getCarrier();
        if (carrier == null || !carrier.isAlive()) {
            this.discard();
            return;
        }

        if (checkReturnToCarrier(carrier)) {
            handleReturnToCarrier(carrier);
            return;
        }

        if (this.getTarget() == null || !this.getTarget().isAlive()) {
            Entity currentTarget = getMissionTarget();
            if (currentTarget instanceof LivingEntity livingTarget && livingTarget.isAlive()) {
                this.setTarget(livingTarget);
            } else {
                Entity carrierTarget = carrier.getTarget();
                if (carrierTarget instanceof LivingEntity livingTarget && livingTarget.isAlive()) {
                    this.setTarget(livingTarget);
                    this.targetId = carrierTarget.getUUID();
                } else {
                    this.setTarget(null);
                    handleReturnToCarrier(carrier);
                    return;
                }
            }
        }
    }

    protected boolean checkReturnToCarrier(EntityShipBase carrier) {
        if (this.missionTick >= LIFETIME_TICKS) {
            return true;
        }
        if (this.numAmmoLight <= 0 && this.numAmmoHeavy <= 0) {
            return true;
        }
        return false;
    }

    protected void handleReturnToCarrier(EntityShipBase carrier) {
        double distSq = this.distanceToSqr(carrier);
        if (distSq <= 4.0D && this.missionTick > 40) {
            returnSummonResources(carrier);
            this.discard();
        } else {
            this.getNavigation().moveTo(carrier, 1.2D);
            if (this.tickCount % 20 == 0 && this.distanceToSqr(carrier) > 1024.0D) {
                this.discard();
            }
        }
    }

    protected void returnSummonResources(EntityShipBase carrier) {

    }

    @Nullable
    protected EntityShipBase getCarrier() {
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
    protected Entity getMissionTarget() {
        if (this.targetId == null || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }
        return serverLevel.getEntity(this.targetId);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        ShipLegacyNavigation navigation = new ShipLegacyNavigation(this, level);
        navigation.setCanFloat(true);
        return navigation;
    }
}
