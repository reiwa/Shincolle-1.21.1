package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;

public class EntityHeavyCruiserNe extends EntityShipBase {

    private static final int PUSH_MAX_TICKS = 200;
    private static final float PUSH_ENGAGE_DISTANCE = 2.5f;

    private boolean isPushing = false;
    private int tickPush = 0;
    private LivingEntity targetPush = null;

    public EntityHeavyCruiserNe(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 10, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 2);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 10);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 4);
        setStateMinor(STATE_MINOR_RARITY, 0);
        setStateFlag(15, false);
        setStateFlag(16, false);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            if ((this.tickCount % 128) == 0) {
                updateServerLogic();
            }
            if (this.isPushing) {
                updatePushingState();
            }
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * 0.05f : this.getBbHeight() * 0.55f;
        }
        return this.getBbHeight() * 0.7f;
    }

    private void updateServerLogic() {
        if (!this.level().isDay() && this.getStateFlag(9)) {
            int duration = 150;
            int ampSpeed = Math.max(0, this.getStateMinor(0) / 50);
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, ampSpeed, false, false));
        }

        boolean canFindTarget = (this.tickCount & 0xFF) == 0 && this.getRandom().nextInt(5) == 0;
        boolean isActionBlocked = this.getIsSitting() || this.isPassenger() || this.getStateFlag(2) || this.isLeashed();
        if (canFindTarget && !isActionBlocked) {
            findTargetPush();
        }
    }

    private void updatePushingState() {
        this.tickPush++;
        if (this.tickPush > PUSH_MAX_TICKS || this.targetPush == null || !this.targetPush.isAlive()) {
            cancelPush();
            return;
        }
        if (this.distanceTo(this.targetPush) <= PUSH_ENGAGE_DISTANCE) {
            executePushAttack();
        } else if (this.tickCount % 32 == 0) {
            this.getNavigation().moveTo(this.targetPush, 1.0D);
        }
    }

    private void executePushAttack() {
        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;
        Vec3 push = new Vec3(-Mth.sin(yawRad) * 0.5f, 0.5f, Mth.cos(yawRad) * 0.5f);
        this.targetPush.setDeltaMovement(this.targetPush.getDeltaMovement().add(push));
        this.swing(InteractionHand.MAIN_HAND);
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD,
                    this.targetPush.getX(), this.targetPush.getY() + 1.0D, this.targetPush.getZ(),
                    6, 0.2D, 0.2D, 0.2D, 0.02D);
        }
        cancelPush();
    }

    private void cancelPush() {
        this.isPushing = false;
        this.tickPush = 0;
        this.targetPush = null;
    }

    private void findTargetPush() {
        AABB impactBox = this.getBoundingBox().inflate(12.0D, 6.0D, 12.0D);
        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, impactBox,
                ent -> ent != this && ent.isAlive() && ent.canBeCollidedWith());
        if (!list.isEmpty()) {
            this.targetPush = list.get(this.getRandom().nextInt(list.size()));
            this.tickPush = 0;
            this.isPushing = true;
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.HEAVY_CRUISER_NE_SPAWN_EGG.get();
    }
}

