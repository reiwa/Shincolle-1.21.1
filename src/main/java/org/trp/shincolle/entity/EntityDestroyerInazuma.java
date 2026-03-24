package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
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
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityDestroyerInazuma extends EntityShipBase implements IShipRiderType {

    public static final String EQUIP_RIGGING = "equip_rigging";

    private int riderType;
    private boolean isRaiden;

    public EntityDestroyerInazuma(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 50});
        setStateMinor(STATE_MINOR_FACTION_ID, -1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 54);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 1);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
        setEquipFlag(EQUIP_RIGGING, true);
        this.riderType = 0;
        this.isRaiden = false;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientLogic();
        } else {
            updateServerLogic();
            applyRaidenFollowOwner();
        }

        updateRiderRotation();
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        if (passenger instanceof EntityDestroyerIkazuchi ikazuchi) {
            double yOffsetEmotion = this.getStateEmotion(1) == 4 ? -0.6 : -0.45;
            double baseOffset = this.getIsSitting() ? 0.26 : 0.68;
            float[] partPos = rotateXZByAxis(-0.2f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            moveFunction.accept(passenger,
                    this.getX() + partPos[1],
                    this.getY() + baseOffset + yOffsetEmotion,
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

    private void updateClientLogic() {
        if ((this.tickCount % 4) == 0 && checkModelState(0, this.getStateEmotion(0))
                && !this.getIsSitting() && !this.getStateFlag(2) && this.riderType < 4) {
            float[] partPos = rotateXZByAxis(-0.42f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX() + partPos[1], this.getY() + 1.4D, this.getZ() + partPos[0],
                    0.0D, 0.0D, 0.0D);
        }
        if ((this.tickCount % 16) == 0) {
            updateState();
        }
    }

    private void updateServerLogic() {
        if ((this.tickCount % 32) != 0) {
            return;
        }

        updateState();
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
        if (this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateMinor(0) / 45;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        80 + this.getStateMinor(0), amp, false, false));
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
                    living.setYRot(this.getYRot());
                    living.yRotO = this.yRotO;
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
        if (this.getStateMinor(43) > 0) {
            dismountRaiden();
            this.stopRiding();
            return;
        }
        if (this.getIsSitting() || this.getStateFlag(2) || this.riderType > 0 || this.isRaiden || this.isPassenger()) {
            return;
        }
        if (this.getHealth() <= this.getMaxHealth() * 0.5f) {
            return;
        }

        List<EntityDestroyerIkazuchi> list = this.level().getEntitiesOfClass(EntityDestroyerIkazuchi.class,
                this.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
        for (EntityDestroyerIkazuchi ikazuchi : list) {
            if (canGattaiWith(ikazuchi)) {
                ikazuchi.startRiding(this, true);
                this.isRaiden = true;
                ikazuchi.setRaiden(true);
                break;
            }
        }
    }

    private boolean canGattaiWith(EntityDestroyerIkazuchi ikazuchi) {
        if (ikazuchi == null || !ikazuchi.isAlive()) {
            return false;
        }
        if (!Objects.equals(this.getOwnerUUID(), ikazuchi.getOwnerUUID())) {
            return false;
        }
        return ikazuchi.getRiderType() == 0 && !ikazuchi.isRaiden()
                && !ikazuchi.getStateFlag(2) && ikazuchi.getStateMinor(43) == 0;
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
        for (Entity rider : this.getPassengers()) {
            if (rider instanceof EntityDestroyerIkazuchi ikazuchi) {
                ikazuchi.setRaiden(false);
                ikazuchi.stopRiding();
            }
        }
        this.isRaiden = false;
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

    private int getLegacyFaceTick(int mask) {
        return (this.tickCount + (this.getStateMinor(22) << 7)) & mask;
    }

    private int mapLegacyMouth(int legacyId) {
        return switch (legacyId) {
            case 0 -> MOUTH_FRONT_0;
            case 1 -> MOUTH_FRONT_1;
            case 2 -> MOUTH_FRONT_2;
            case 3 -> MOUTH_FLIP_0;
            case 4 -> MOUTH_FLIP_1;
            case 5 -> MOUTH_FLIP_2;
            default -> MOUTH_FRONT_0;
        };
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_INAZUMA_SPAWN_EGG.get();
    }
}

