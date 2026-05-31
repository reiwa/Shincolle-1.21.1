package org.trp.shincolle.entity;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

public class EntityDestroyerAkatsuki
    extends EntityShipBase
    implements IShipRiderType
{

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_ANCHOR = "equip_anchor";
    public static final String EQUIP_HAT = "equip_hat";
    public static final String EQUIP_HAND_CANNON = "equip_hand_cannon";
    public static final String EQUIP_ARM_TORPEDO = "equip_arm_torpedo";
    public static final String EQUIP_SHOULDER_SEARCHLIGHT =
        "equip_shoulder_searchlight";

    private static final int STATE_FLAG_15 = 15;
    private static final int STATE_FLAG_16 = 16;
    private static final int RIDER_TYPE_NONE = 0;
    private static final int RIDER_TYPE_HIBIKI = 1;
    private static final int RIDER_TYPE_INAZUMA = 2;
    private static final int RIDER_TYPE_IKAZUCHI = 4;
    private static final int RIDER_TYPE_ALL = 7;

    private static final long AKATSUKI_GATTAI_DURATION_TICKS = 20L * 60L;
    private static final long AKATSUKI_GATTAI_COOLDOWN_TICKS = 20L * 120L;

    private int riderType = RIDER_TYPE_NONE;
    private long akatsukiGattaiExpireTick = 0L;
    private long akatsukiGattaiCooldownUntilTick = 0L;

    public EntityDestroyerAkatsuki(
        EntityType<? extends TamableAnimal> type,
        Level level
    ) {
        super(type, level);
        setStateMinor(STATE_MINOR_FACTION_ID, -1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 51);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 5);
        setStateMinor(
            STATE_MINOR_GRUDGE_CONSUMPTION,
            org.trp.shincolle.Config.fuelConsumeDD
        );
        setModelPos(new float[] { 0.0f, 25.0f, 0.0f, 50.0f });
        setStateFlag(STATE_FLAG_15, false);
        setStateFlag(STATE_FLAG_16, false);
        setStateCanRide(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
            .add(Attributes.MAX_HEALTH, 160.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.ATTACK_DAMAGE, 8.0D)
            .add(Attributes.FOLLOW_RANGE, 36.0D)
            .add(Attributes.STEP_HEIGHT, 1.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(
        ServerLevel level,
        AgeableMob otherParent
    ) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    protected boolean hasSearchlightEquip() {
        return super.hasSearchlightEquip() || this.isStateMarried();
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(
            super.getEquipOptions()
        );
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        list.add(new EquipOption(EQUIP_ANCHOR, "gui.shincolle.equip.anchor"));
        list.add(new EquipOption(EQUIP_HAT, "gui.shincolle.equip.hat"));
        list.add(
            new EquipOption(EQUIP_HAND_CANNON, "gui.shincolle.equip.cannon")
        );
        list.add(
            new EquipOption(EQUIP_ARM_TORPEDO, "gui.shincolle.equip.torpedo")
        );
        list.add(
            new EquipOption(
                EQUIP_SHOULDER_SEARCHLIGHT,
                "gui.shincolle.equip.shoulder_searchlight"
            )
        );
        return list;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        checkRiderType();

        if (this.level().isClientSide) {
            updateClientEffects();
        }

        if (!this.getPassengers().isEmpty()) {
            syncRotateToRider();
        }
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        updateServerLogic();
        updateGattaiDurationAndCooldown();
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        double baseOffset = this.getIsSitting() ? 0.26 : 0.68;
        double yOffsetEmotion =
            this.getStateEmotion(1) == 4 || this.getIsSitting() ? 0.0 : 0.1;

        if (passenger instanceof EntityDestroyerHibiki hibiki) {
            hibiki.setStateEmotion(1, this.getStateEmotion(1), false);
            float[] partPos = rotateXZByAxis(
                -0.2f,
                0.0f,
                (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD,
                1.0f
            );
            moveFunction.accept(
                passenger,
                this.getX() + partPos[1],
                this.getY() + baseOffset + yOffsetEmotion * 2.5 - 0.4F,
                this.getZ() + partPos[0]
            );
            return;
        }
        if (passenger instanceof EntityDestroyerInazuma inazuma) {
            inazuma.setStateEmotion(1, this.getStateEmotion(1), false);
            float[] partPos = rotateXZByAxis(
                -0.48f,
                0.0f,
                (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD,
                1.0f
            );
            moveFunction.accept(
                passenger,
                this.getX() + partPos[1],
                this.getY() + baseOffset + yOffsetEmotion * 4.5 - 0.05F,
                this.getZ() + partPos[0]
            );
            return;
        }
        if (passenger instanceof EntityDestroyerIkazuchi ikazuchi) {
            ikazuchi.setStateEmotion(1, this.getStateEmotion(1), false);
            float[] partPos = rotateXZByAxis(
                -0.68f,
                0.0f,
                (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD,
                1.0f
            );
            moveFunction.accept(
                passenger,
                this.getX() + partPos[1],
                this.getY() + baseOffset + yOffsetEmotion * 6 + 0.4F,
                this.getZ() + partPos[0]
            );
            return;
        }

        super.positionRider(passenger, moveFunction);
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4
                ? this.getBbHeight() * -0.07f
                : this.getBbHeight() * 0.26f;
        }
        return this.getBbHeight() * 0.64f;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean damaged = super.hurt(source, amount);
        if (damaged && !this.level().isClientSide) {
            dismountAllRider();
        }
        return damaged;
    }

    private void updateClientEffects() {
        if ((this.tickCount % 4) == 0) {
            if (
                !this.getIsSitting() &&
                this.getEquipFlag(EQUIP_RIGGING) &&
                this.riderType < 1
            ) {
                float addZ = this.isPassenger() ? -0.2f : 0.0f;
                float[] partPos = rotateXZByAxis(
                    -0.42f + addZ,
                    0.0f,
                    (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD,
                    1.0f
                );
                this.level().addParticle(
                    ParticleTypes.SMOKE,
                    this.getX() + partPos[1],
                    this.getY() + 1.4D,
                    this.getZ() + partPos[0],
                    0.0D,
                    0.0D,
                    0.0D
                );
            }
        }
    }

    private void updateServerLogic() {
        if ((this.tickCount % 32) != 0) {
            return;
        }

        if (
            this.riderType == 2 ||
            this.riderType == 4 ||
            this.riderType == 5 ||
            this.riderType == 6
        ) {
            dismountAllRider();
        }
        if (this.riderType > 0) {
            addMoraleToRider();
            if (this.getMorale() < 7650) {
                this.addMorale(100);
            }
        }
        if ((this.tickCount % 128) == 0) {
            applyPlayerBuff();
            tryGattai();
        }
    }

    private void applyPlayerBuff() {
        if (
            this.isStateMarried() &&
            this.isStateRingEffect() &&
            this.getStateMinor(6) > 0
        ) {
            if (
                this.getOwnerPlayer() != null &&
                this.distanceToSqr(this.getOwnerPlayer()) < 256.0D
            ) {
                int amp = this.getStateMinor(0) / 30;
                this.getOwnerPlayer().addEffect(
                    new MobEffectInstance(
                        MobEffects.DIG_SPEED,
                        80 + this.getStateMinor(0),
                        amp,
                        false,
                        false
                    )
                );
            }
        }
    }

    private void checkRiderType() {
        this.riderType = RIDER_TYPE_NONE;
        boolean hasHibiki = false;
        for (Entity rider : this.getPassengers()) {
            if (rider instanceof EntityDestroyerHibiki) {
                this.riderType |= RIDER_TYPE_HIBIKI;
                hasHibiki = true;
            } else if (rider instanceof EntityDestroyerInazuma) {
                this.riderType |= RIDER_TYPE_INAZUMA;
            } else if (rider instanceof EntityDestroyerIkazuchi) {
                this.riderType |= RIDER_TYPE_IKAZUCHI;
            }
        }
        this.setRidingState(hasHibiki ? 1 : 0);
    }

    private void addMoraleToRider() {
        for (Entity rider : this.getPassengers()) {
            if (
                rider instanceof EntityShipBase ship && ship.getMorale() < 7650
            ) {
                ship.addMorale(100);
            }
            if (rider instanceof IShipRiderType riderShip) {
                riderShip.setRiderType(this.riderType);
            }
        }
    }

    private boolean isGattaiCandidate(EntityShipBase ship) {
        if (ship == null) {
            return false;
        }
        if (
            this.getFormationTeam() == -1 ||
            ship.getFormationTeam() != this.getFormationTeam()
        ) {
            return false;
        }
        if (!ship.isAlive()) {
            return false;
        }
        if (!Objects.equals(this.getOwnerUUID(), ship.getOwnerUUID())) {
            return false;
        }
        if (ship.getIsSitting()) {
            return false;
        }
        if (ship.isStateNoEquip()) {
            return false;
        }
        if (ship.getStateMinor(43) > 0) {
            return false;
        }
        if (ship.getStateMinor(26) != 0 && ship.getStateMinor(26) != 1) {
            return false;
        }
        if (ship.isPassenger()) {
            Entity vehicle = ship.getVehicle();
            if (vehicle == this) {
                return true;
            }
            if (
                ship instanceof EntityDestroyerIkazuchi &&
                vehicle instanceof EntityDestroyerInazuma
            ) {
                return true;
            }
            return false;
        }
        return true;
    }

    private void tryGattai() {
        if (this.getStateMinor(43) > 0) {
            dismountAllRider();
            this.stopRiding();
            return;
        }
        if (isGattaiCooldownActive()) {
            return;
        }
        if (this.getHealth() <= this.getMaxHealth() * 0.5f) {
            return;
        }
        if (
            this.getIsSitting() ||
            this.isStateNoEquip() ||
            this.riderType == RIDER_TYPE_ALL
        ) {
            return;
        }

        List<EntityShipBase> ships = this.level().getEntitiesOfClass(
            EntityShipBase.class,
            this.getBoundingBox().inflate(6.0D, 5.0D, 6.0D)
        );

        EntityDestroyerHibiki hibiki = null;
        EntityDestroyerInazuma inazuma = null;
        EntityDestroyerIkazuchi ikazuchi = null;

        for (EntityShipBase ship : ships) {
            if (
                ship instanceof EntityDestroyerHibiki && isGattaiCandidate(ship)
            ) {
                hibiki = (EntityDestroyerHibiki) ship;
            } else if (
                ship instanceof EntityDestroyerInazuma &&
                isGattaiCandidate(ship)
            ) {
                inazuma = (EntityDestroyerInazuma) ship;
            } else if (
                ship instanceof EntityDestroyerIkazuchi &&
                isGattaiCandidate(ship)
            ) {
                ikazuchi = (EntityDestroyerIkazuchi) ship;
            }
        }

        if (this.riderType == RIDER_TYPE_NONE) {
            if (hibiki != null) {
                hibiki.startRiding(this, true);
                if (inazuma != null) {
                    inazuma.startRiding(this, true);
                    if (ikazuchi != null) {
                        ikazuchi.startRiding(this, true);
                    }
                }
            }
        } else if (this.riderType == RIDER_TYPE_HIBIKI) {
            if (inazuma != null) {
                inazuma.startRiding(this, true);
                if (ikazuchi != null) {
                    ikazuchi.startRiding(this, true);
                }
            }
        } else if (this.riderType == (RIDER_TYPE_HIBIKI | RIDER_TYPE_INAZUMA)) {
            if (ikazuchi != null) {
                ikazuchi.startRiding(this, true);
            }
        }
    }

    public void syncRotateToRider() {
        for (Entity rider : this.getPassengers()) {
            if (rider instanceof LivingEntity living) {
                living.yBodyRot = this.yBodyRot;
                living.yBodyRotO = this.yBodyRotO;
                living.yHeadRot = this.yBodyRot;
                living.yHeadRotO = this.yBodyRotO;
                living.setYRot(this.yBodyRot);
                living.yRotO = this.yBodyRotO;
            }
        }
    }

    public void dismountAllRider() {
        boolean wasFullyCombined = (this.riderType == RIDER_TYPE_ALL);
        this.riderType = RIDER_TYPE_NONE;
        this.setRidingState(0);
        for (Entity rider : this.getPassengers()) {
            if (rider instanceof IShipRiderType riderShip) {
                riderShip.setRiderType(RIDER_TYPE_NONE);
            }
            if (rider instanceof EntityShipBase ship) {
                ship.setRidingState(0);
            }
        }
        this.ejectPassengers();
        if (wasFullyCombined) {
            startGattaiCooldown();
        }
    }

    private void updateGattaiDurationAndCooldown() {
        if (this.level().isClientSide) {
            return;
        }

        boolean isFullyCombined = (this.riderType == RIDER_TYPE_ALL);

        if (isFullyCombined) {
            if (this.akatsukiGattaiExpireTick == 0L) {
                this.akatsukiGattaiExpireTick =
                    this.level().getGameTime() + AKATSUKI_GATTAI_DURATION_TICKS;
            }
            if (
                this.getIsSitting() ||
                this.isInDeadPose() ||
                this.getHealth() <= this.getMaxHealth() * 0.5f ||
                isGattaiDurationExpired()
            ) {
                dismountAllRider();
            }
        } else {
            this.akatsukiGattaiExpireTick = 0L;
            if (
                this.riderType > 0 &&
                this.riderType != RIDER_TYPE_HIBIKI &&
                this.riderType != (RIDER_TYPE_HIBIKI | RIDER_TYPE_INAZUMA)
            ) {
                dismountAllRider();
            }
        }

        if (
            this.akatsukiGattaiCooldownUntilTick > 0L &&
            this.level().getGameTime() >= this.akatsukiGattaiCooldownUntilTick
        ) {
            this.akatsukiGattaiCooldownUntilTick = 0L;
        }
    }

    private boolean isGattaiDurationExpired() {
        return (
            this.akatsukiGattaiExpireTick > 0L &&
            this.level().getGameTime() >= this.akatsukiGattaiExpireTick
        );
    }

    public boolean isGattaiCooldownActive() {
        return (
            this.akatsukiGattaiCooldownUntilTick > this.level().getGameTime()
        );
    }

    public void startGattaiCooldown() {
        this.akatsukiGattaiExpireTick = 0L;
        this.akatsukiGattaiCooldownUntilTick = Math.max(
            this.akatsukiGattaiCooldownUntilTick,
            this.level().getGameTime() + AKATSUKI_GATTAI_COOLDOWN_TICKS
        );
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putLong(
            "AkatsukiGattaiExpireTick",
            this.akatsukiGattaiExpireTick
        );
        compound.putLong(
            "AkatsukiGattaiCooldownUntilTick",
            this.akatsukiGattaiCooldownUntilTick
        );
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.akatsukiGattaiExpireTick = compound.getLong(
            "AkatsukiGattaiExpireTick"
        );
        this.akatsukiGattaiCooldownUntilTick = compound.getLong(
            "AkatsukiGattaiCooldownUntilTick"
        );
    }

    @Override
    public int getRiderType() {
        return this.riderType;
    }

    @Override
    public void setRiderType(int type) {
        this.riderType = type;
    }

    @Override
    protected void setFaceNormal() {
        this.setFaceId(FACE_EYES_OPEN);
        int tick = this.tickCount & EMOTION_TICK_MASK_8BIT;
        if (this.getStateEmotion(7) == 4 && tick > 160) {
            this.setMouthId(mapLegacyMouth(3));
        } else {
            this.setMouthId(mapLegacyMouth(0));
        }
    }

    @Override
    protected void setFaceCry() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 128) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 5 : 2));
        } else {
            this.setFaceId(FACE_CRY);
            this.setMouthId(mapLegacyMouth(2));
        }
    }

    @Override
    protected void setFaceDamaged() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_9BIT);
        if (tick < 200) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 60 ? 5 : 2));
        } else if (tick < 400) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 0 : 4));
        } else {
            this.setFaceId(FACE_SOFT);
            this.setMouthId(mapLegacyMouth(tick < 450 ? 0 : 1));
        }
    }

    @Override
    protected void setFaceScorn() {
        this.setFaceId(FACE_EYES_HALF);
        this.setMouthId(mapLegacyMouth(1));
    }

    @Override
    protected void setFaceHungry() {
        this.setFaceId(FACE_DESPAIR);
        this.setMouthId(mapLegacyMouth(2));
    }

    @Override
    protected void setFaceAngry() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 128) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 0 : 1));
        } else {
            this.setFaceId(FACE_EYES_HALF);
            this.setMouthId(mapLegacyMouth(tick < 170 ? 1 : 2));
        }
    }

    @Override
    protected void setFaceBored() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_9BIT);
        if (tick < 170) {
            this.setFaceId(FACE_DOT_EYES);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 0 : 4));
        } else if (tick < 340) {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(0));
        } else {
            this.setFaceId(FACE_EYES_OPEN);
            this.setMouthId(mapLegacyMouth(0));
        }
    }

    @Override
    protected void setFaceShy() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 140) {
            this.setFaceId(FACE_EYES_OPEN);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 3 : 2));
        } else {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(0));
        }
    }

    @Override
    protected void setFaceHappy() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 140) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 0 : 4));
        } else {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(4));
        }
    }

    @Override
    public boolean supportsItemPickup() {
        return true;
    }

    protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_AKATSUKI_SPAWN_EGG.get();
    }
}
