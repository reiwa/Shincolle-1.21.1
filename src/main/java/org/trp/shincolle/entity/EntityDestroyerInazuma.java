package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.FaceExpressionConfig;
import org.trp.shincolle.entity.base.FaceStep;
import org.trp.shincolle.entity.base.FaceTimeline;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityDestroyerInazuma extends EntityShipBase implements IShipRiderType {

    public static final String EQUIP_RIGGING = "equip_rigging";
    private static final long RAIDEN_GATTAI_DURATION_TICKS = 20L * 45L;
    private static final long RAIDEN_GATTAI_COOLDOWN_TICKS = 20L * 20L;

    private int riderType;
    private boolean isRaiden;
    private long raidenGattaiExpireTick;
    private long raidenGattaiCooldownUntilTick;

    public EntityDestroyerInazuma(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 50});
        getStateComponent().setFactionId(-1);
        getStateComponent().setShipClassId(54);
        getStateComponent().setSpecialEquip(5);
        getStateComponent().setRarity(1);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
        this.riderType = 0;
        this.isRaiden = false;
        this.raidenGattaiExpireTick = 0L;
        this.raidenGattaiCooldownUntilTick = 0L;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        updateState();

        if (this.level().isClientSide) {
            updateClientLogic();
        }

        updateRiderRotation();
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        updateServerLogic();
        applyRaidenFollowOwner();
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        return list;
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        if (passenger instanceof EntityDestroyerIkazuchi ikazuchi) {
            double yOffsetEmotion = this.getStateEmotion(1) == 4 ? -0.65 : -0.45;
            double baseOffset = this.getIsSitting() ? 0.26 : 0.68;
            float[] partPos = rotateXZByAxis(-0.2f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            moveFunction.accept(passenger,
                    this.getX() + partPos[1],
                    this.getY() + baseOffset + yOffsetEmotion + 0.375,
                    this.getZ() + partPos[0]);
            return;
        }

        super.positionRider(passenger, moveFunction);
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * 0.23f : this.getBbHeight() * 0.44f;
        }
        return this.getBbHeight() * 0.64f;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean damaged = super.hurt(source, amount);
        if (damaged && !this.level().isClientSide) {
            if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
                akatsuki.dismountAllRider();
            }
            if (this.isRaiden) {
                dismountRaiden();
            }
        }
        return damaged;
    }

    @Override
    protected void updateFuelState(boolean nofuel) {
        if (nofuel) {
            if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
                akatsuki.dismountAllRider();
                this.stopRiding();
            }
            if (this.isRaiden) {
                dismountRaiden();
            }
        }
        super.updateFuelState(nofuel);
    }

    private void updateClientLogic() {
        if ((this.tickCount % 4) == 0 && !this.getIsSitting() && !this.isInDeadPose() && this.getEquipFlag(EQUIP_RIGGING)
            && this.riderType < 4
            && this.getPassengers().stream().noneMatch(EntityDestroyerIkazuchi.class::isInstance)) {
            float[] partPos = rotateXZByAxis(-0.42f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX() + partPos[1], this.getY() + 1.4D, this.getZ() + partPos[0],
                    0.0D, 0.0D, 0.0D);
        }
    }

    private void updateServerLogic() {
        if ((this.tickCount % 32) != 0) {
            return;
        }

        if (!this.isRaiden) {
            this.raidenGattaiExpireTick = 0L;
        }
        if (this.raidenGattaiCooldownUntilTick > 0L && this.level().getGameTime() >= this.raidenGattaiCooldownUntilTick) {
            this.raidenGattaiCooldownUntilTick = 0L;
        }
        if (this.isRaiden && (this.getIsSitting() || this.isInDeadPose() || this.getHealth() <= this.getMaxHealth() * 0.5f
                || isRaidenGattaiDurationExpired())) {
            dismountRaiden();
        }
        if (this.isRaiden && this.getPassengers().stream().noneMatch(EntityDestroyerIkazuchi.class::isInstance)) {
            this.isRaiden = false;
        }
        if (this.riderType == 0 && this.isRaiden && this.getMorale() < 7650) {
            this.addMorale(100);
        }
        if ((this.tickCount % 128) == 0) {
            applyBuffToPlayer();
            tryRaidenGattai();
        }
    }

    private void updateState() {
        checkRiderType();
        checkIsRaiden();
        checkRidingState();
    }

    private void applyBuffToPlayer() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateComponent().getAffectionLegacy() / 45;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        80 + this.getStateComponent().getAffectionLegacy(), amp, false, false));
            }
        }
    }

    private void updateRiderRotation() {
        if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
            akatsuki.syncRotateToRider();
        } else if (this.isRaiden) {
            for (Entity rider : this.getPassengers()) {
                if (rider instanceof LivingEntity living && rider instanceof EntityDestroyerIkazuchi) {
                    living.yBodyRot = this.yBodyRot;
                    living.yBodyRotO = this.yBodyRotO;
                    living.yHeadRot = this.yBodyRot;
                    living.yHeadRotO = this.yBodyRotO;
                    living.setYRot(this.yBodyRot);
                    living.yRotO = this.yBodyRotO;
                }
            }
        }
    }

    private void applyRaidenFollowOwner() {
        if (!this.isRaiden || this.getIsSitting() || this.isPassenger() || this.isInDeadPose()) {
            return;
        }

        LivingEntity owner = this.getOwner();
        if (owner == null) {
            return;
        }

        double minDist = 2.0D;
        double maxDist = 10.0D;
        double distanceSqr = this.distanceToSqr(owner);
        if (distanceSqr <= minDist * minDist) {
            this.getNavigation().stop();
            return;
        }

        if (distanceSqr < (maxDist * maxDist) * 256.0D) {
            this.getLookControl().setLookAt(owner, 30.0F, 30.0F);
            this.getNavigation().moveTo(owner, 1.0D);
        }
    }

    private void tryRaidenGattai() {
        if (!canAttemptGattai()) {
            return;
        }

        List<EntityDestroyerIkazuchi> list = this.level().getEntitiesOfClass(EntityDestroyerIkazuchi.class,
                this.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
        for (EntityDestroyerIkazuchi ikazuchi : list) {
            if (canGattaiWith(ikazuchi)) {
                ikazuchi.startRiding(this, true);
                beginRaidenGattai(ikazuchi);
                break;
            }
        }
    }

    private boolean canAttemptGattai() {
        if (this.getStateComponent().getCraning() > 0) {
            dismountRaiden();
            this.stopRiding();
            return false;
        }
        if (isRaidenGattaiCooldownActive()) {
            return false;
        }
        if (this.getIsSitting() || this.isStateNoEquip() || this.riderType > 0 || this.isRaiden || this.isPassenger()) {
            return false;
        }
        return this.getHealth() > this.getMaxHealth() * 0.5f;
    }

    private void beginRaidenGattai(EntityDestroyerIkazuchi ikazuchi) {
        this.isRaiden = true;
        ikazuchi.setRaiden(true);

        long expireTick = this.level().getGameTime() + RAIDEN_GATTAI_DURATION_TICKS;
        this.setRaidenGattaiExpireTick(expireTick);
        ikazuchi.setRaidenGattaiExpireTick(expireTick);
    }

    private boolean canGattaiWith(EntityDestroyerIkazuchi ikazuchi) {
        if (ikazuchi == null || !ikazuchi.isAlive()) {
            return false;
        }
        if (!Objects.equals(this.getOwnerUUID(), ikazuchi.getOwnerUUID())) {
            return false;
        }
        return ikazuchi.getRiderType() == 0 && !ikazuchi.isRaiden()
                && !ikazuchi.isStateNoEquip() && ikazuchi.getStateComponent().getCraning() == 0
                && !ikazuchi.isRaidenGattaiCooldownActive();
    }

    private void checkRiderType() {
        this.riderType = 0;
        if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
            this.riderType = akatsuki.getRiderType();
        }
    }

    private void checkRidingState() {
        if (this.riderType == 7) {
            this.setRidingState(3);
        } else if (this.isRaiden) {
            this.setRidingState(2);
        } else if (this.riderType == 3) {
            this.setRidingState(1);
        } else {
            this.setRidingState(0);
        }
    }

    private void checkIsRaiden() {
        this.isRaiden = this.getPassengers().stream().anyMatch(EntityDestroyerIkazuchi.class::isInstance);
    }

    private void dismountRaiden() {
        boolean hadRaiden = this.isRaiden;
        for (Entity rider : this.getPassengers()) {
            if (rider instanceof EntityDestroyerIkazuchi ikazuchi) {
                hadRaiden = true;
                ikazuchi.setRaiden(false);
                ikazuchi.startRaidenGattaiCooldown();
                ikazuchi.stopRiding();
                placeIkazuchiAfterRaidenDismount(ikazuchi);
            }
        }
        if (hadRaiden) {
            this.startRaidenGattaiCooldown();
        }
        this.isRaiden = false;
    }

    void placeIkazuchiAfterRaidenDismount(EntityDestroyerIkazuchi ikazuchi) {
        if (ikazuchi == null) {
            return;
        }
        float[] dismountOffset = rotateXZByAxis(0.0f, 1.1f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
        ikazuchi.moveTo(
                this.getX() + dismountOffset[1],
                this.getY() + 0.1D,
                this.getZ() + dismountOffset[0],
                ikazuchi.getYRot(),
                ikazuchi.getXRot()
        );
    }

    boolean isRaidenGattaiCooldownActive() {
        return this.raidenGattaiCooldownUntilTick > this.level().getGameTime();
    }

    void setRaidenGattaiExpireTick(long expireTick) {
        this.raidenGattaiExpireTick = expireTick;
    }

    void startRaidenGattaiCooldown() {
        this.raidenGattaiExpireTick = 0L;
        this.raidenGattaiCooldownUntilTick = Math.max(
                this.raidenGattaiCooldownUntilTick,
                this.level().getGameTime() + RAIDEN_GATTAI_COOLDOWN_TICKS
        );
    }

    private boolean isRaidenGattaiDurationExpired() {
        return this.raidenGattaiExpireTick > 0L && this.level().getGameTime() >= this.raidenGattaiExpireTick;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putLong("RaidenGattaiExpireTick", this.raidenGattaiExpireTick);
        compound.putLong("RaidenGattaiCooldownUntilTick", this.raidenGattaiCooldownUntilTick);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.raidenGattaiExpireTick = compound.getLong("RaidenGattaiExpireTick");
        this.raidenGattaiCooldownUntilTick = compound.getLong("RaidenGattaiCooldownUntilTick");
    }

    @Override
    public int getRiderType() {
        return this.riderType;
    }

    @Override
    public void setRiderType(int type) {
        this.riderType = type;
    }

    public boolean isRaiden() {
        return this.isRaiden;
    }

    public void setRaiden(boolean raiden) {
        this.isRaiden = raiden;
    }

    @Override
    protected FaceExpressionConfig createFaceExpressionConfig() {
        return FaceExpressionConfig.builder()
            .normal(new FaceTimeline(0xFF, new FaceStep[0], FACE_EYES_OPEN, mapLegacyMouth(0)))
            .normalBored(new FaceTimeline(0xFF, new FaceStep[] {
                new FaceStep(160, FACE_EYES_OPEN, mapLegacyMouth(0))
            }, FACE_EYES_OPEN, mapLegacyMouth(3)))
            .cry(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(64, FACE_DOT_EYES_TEAR, mapLegacyMouth(5)),
                new FaceStep(128, FACE_DOT_EYES_TEAR, mapLegacyMouth(2))
            }, FACE_CRY, mapLegacyMouth(2)))
            .damaged(new FaceTimeline(EMOTION_TICK_MASK_9BIT, new FaceStep[] {
                new FaceStep(60, FACE_DOT_EYES_TEAR, mapLegacyMouth(5)),
                new FaceStep(200, FACE_DOT_EYES_TEAR, mapLegacyMouth(2)),
                new FaceStep(250, FACE_TENSION, mapLegacyMouth(0)),
                new FaceStep(400, FACE_TENSION, mapLegacyMouth(4)),
                new FaceStep(450, FACE_SOFT, mapLegacyMouth(0))
            }, FACE_SOFT, mapLegacyMouth(1)))
            .scorn(new FaceTimeline(0, new FaceStep[0], FACE_EYES_HALF, mapLegacyMouth(1)))
            .hungry(new FaceTimeline(0, new FaceStep[0], FACE_DESPAIR, mapLegacyMouth(2)))
            .angry(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(64, FACE_EYES_CLOSED, mapLegacyMouth(0)),
                new FaceStep(128, FACE_EYES_CLOSED, mapLegacyMouth(1)),
                new FaceStep(170, FACE_EYES_HALF, mapLegacyMouth(1))
            }, FACE_EYES_HALF, mapLegacyMouth(2)))
            .bored(new FaceTimeline(EMOTION_TICK_MASK_9BIT, new FaceStep[] {
                new FaceStep(80, FACE_DOT_EYES, mapLegacyMouth(0)),
                new FaceStep(170, FACE_DOT_EYES, mapLegacyMouth(4)),
                new FaceStep(340, FACE_WINK, mapLegacyMouth(0))
            }, FACE_EYES_OPEN, mapLegacyMouth(0)))
            .shy(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(80, FACE_EYES_OPEN, mapLegacyMouth(3)),
                new FaceStep(140, FACE_EYES_OPEN, mapLegacyMouth(2))
            }, FACE_WINK, mapLegacyMouth(0)))
            .happy(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(80, FACE_TENSION, mapLegacyMouth(0)),
                new FaceStep(140, FACE_TENSION, mapLegacyMouth(4))
            }, FACE_WINK, mapLegacyMouth(4)))
            .build();
    }


    @Override
    public boolean supportsItemPickup() {
        return true;
    }
    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_INAZUMA_SPAWN_EGG.get();
    }

    @Override
    protected net.minecraft.world.BossEvent.BossBarColor getBossBarColor() {
        return net.minecraft.world.BossEvent.BossBarColor.PINK;
    }
}

