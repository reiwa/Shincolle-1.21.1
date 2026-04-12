package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class EntityDestroyerAkatsuki extends EntityShipBase implements IShipRiderType {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_ANCHOR = "equip_anchor";

    private static final int STATE_FLAG_15 = 15;
    private static final int STATE_FLAG_16 = 16;
    private static final int RIDER_TYPE_NONE = 0;
    private static final int RIDER_TYPE_HIBIKI = 1;
    private static final int RIDER_TYPE_INAZUMA = 2;
    private static final int RIDER_TYPE_IKAZUCHI = 4;
    private static final int RIDER_TYPE_ALL = 7;

    private int riderType = RIDER_TYPE_NONE;

    public EntityDestroyerAkatsuki(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setStateMinor(STATE_MINOR_FACTION_ID, -1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 51);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 5);
        setModelPos(new float[]{0.0f, 25.0f, 0.0f, 50.0f});
        setStateFlag(STATE_FLAG_15, false);
        setStateFlag(STATE_FLAG_16, false);
        setStateCanRide(true);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_ANCHOR, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 160.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 36.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_ANCHOR, "gui.shincolle.equip.anchor")
        );
    }

    @Override
    public void aiStep() {
        super.aiStep();

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
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        if (passenger instanceof EntityDestroyerHibiki) {
            double yOffset = this.getIsSitting() ? 0.22 : 0.68;
            float[] partPos = rotateXZByAxis(-0.2f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            moveFunction.accept(passenger, this.getX() + partPos[1], this.getY() + yOffset - 0.45, this.getZ() + partPos[0]);
            return;
        }

        if (passenger instanceof EntityDestroyerInazuma inazuma) {
            inazuma.setStateEmotion(1, this.getStateEmotion(1), false);
            double yOffset = this.getIsSitting() ? -0.08 : 0.68;
            float[] partPos = rotateXZByAxis(-0.48f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            moveFunction.accept(passenger, this.getX() + partPos[1], this.getY() + yOffset + 0.1, this.getZ() + partPos[0]);
            return;
        }

        if (passenger instanceof EntityDestroyerIkazuchi) {
            EntityDestroyerInazuma inazuma = this.getPassengers().stream()
                    .filter(EntityDestroyerInazuma.class::isInstance)
                    .map(EntityDestroyerInazuma.class::cast)
                    .findFirst()
                    .orElse(null);
            if (inazuma != null) {
                double yOffset = inazuma.getStateEmotion(1) == 4 ? 0.5 : 0.6;
                float[] partPos = rotateXZByAxis(-0.68f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
                moveFunction.accept(passenger, this.getX() + partPos[1], this.getY() + yOffset, this.getZ() + partPos[0]);
                return;
            }
        }

        super.positionRider(passenger, moveFunction);
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * -0.07f : this.getBbHeight() * 0.26f;
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
            if (!this.getIsSitting() && this.getEquipFlag(EQUIP_RIGGING)
                    && this.riderType < 1) {
                float addZ = this.isPassenger() ? -0.2f : 0.0f;
                float[] partPos = rotateXZByAxis(-0.42f + addZ, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
                this.level().addParticle(ParticleTypes.SMOKE,
                        this.getX() + partPos[1], this.getY() + 1.4D, this.getZ() + partPos[0],
                        0.0D, 0.0D, 0.0D);
            }
        }
        if ((this.tickCount % 16) == 0) {
            checkRiderType();
        }
    }

    private void updateServerLogic() {
        if ((this.tickCount % 32) != 0) {
            return;
        }

        checkRiderType();
        if (this.riderType == 2 || this.riderType == 4 || this.riderType == 5 || this.riderType == 6) {
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
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateMinor(0) / 30;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,
                        80 + this.getStateMinor(0), amp, false, false));
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
            if (rider instanceof EntityShipBase ship && ship.getMorale() < 7650) {
                ship.addMorale(100);
            }
            if (rider instanceof IShipRiderType riderShip) {
                riderShip.setRiderType(this.riderType);
            }
        }
    }

    private boolean isGattaiCandidate(EntityShipBase ship) {
        if (ship == null || !ship.isAlive()) {
            return false;
        }
        if (!Objects.equals(this.getOwnerUUID(), ship.getOwnerUUID())) {
            return false;
        }
        return !ship.isPassenger() && !ship.getIsSitting() && !ship.isStateNoEquip()
                && ship.getStateMinor(43) == 0 && ship.getStateMinor(26) == 1;
    }

    private void tryGattai() {
        if (this.getStateMinor(43) > 0) {
            dismountAllRider();
            this.stopRiding();
            return;
        }
        if (this.getIsSitting() || this.isStateNoEquip() || this.riderType == RIDER_TYPE_ALL) {
            return;
        }

        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(6.0D, 5.0D, 6.0D));
        if (ships.isEmpty()) {
            return;
        }

        EntityDestroyerHibiki hibiki = null;
        EntityDestroyerInazuma inazuma = null;
        EntityDestroyerIkazuchi ikazuchi = null;

        for (EntityShipBase ship : ships) {
            if (ship instanceof EntityDestroyerHibiki && isGattaiCandidate(ship)) {
                hibiki = (EntityDestroyerHibiki) ship;
            } else if (ship instanceof EntityDestroyerInazuma && isGattaiCandidate(ship)) {
                inazuma = (EntityDestroyerInazuma) ship;
            } else if (ship instanceof EntityDestroyerIkazuchi && isGattaiCandidate(ship)) {
                ikazuchi = (EntityDestroyerIkazuchi) ship;
            }
        }

        if ((this.riderType & RIDER_TYPE_HIBIKI) == 0 && hibiki != null) {
            hibiki.startRiding(this, true);
        }
        if ((this.riderType & RIDER_TYPE_INAZUMA) == 0 && inazuma != null) {
            inazuma.startRiding(this, true);
        }
        if ((this.riderType & RIDER_TYPE_IKAZUCHI) == 0 && ikazuchi != null) {
            ikazuchi.startRiding(this, true);
        }
    }

    public void syncRotateToRider() {
        for (Entity rider : this.getPassengers()) {
            if (rider instanceof LivingEntity living) {
                living.yBodyRot = this.yBodyRot;
                living.yBodyRotO = this.yBodyRotO;
                living.setYRot(this.getYRot());
                living.yRotO = this.yRotO;
            }
        }
    }

    public void dismountAllRider() {
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
    protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_AKATSUKI_SPAWN_EGG.get();
    }
}
