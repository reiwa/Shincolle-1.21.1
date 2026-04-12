package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

public class EntityDestroyerHa extends EntityShipBase {

    public EntityDestroyerHa(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 0, 0, 25});
        setStateMinor(STATE_MINOR_FACTION_ID, -1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 2);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 1);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            applyBuffToOwner();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getBbHeight() * 0.27f;
        }
        return this.getBbHeight() * 0.7f;
    }

    private void applyBuffToOwner() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateMinor(0) / 45 + 1;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.JUMP,
                        80 + this.getStateMinor(0), amp, false, false));
            }
        }
    }

    @Override
    protected void setFaceNormal() {
        setSimpleFace(FACE_EYES_OPEN);
    }

    @Override
    protected void setFaceCry() {
        setSimpleFace(FACE_EYES_HALF);
    }

    @Override
    protected void setFaceDamaged() {
        setSimpleFace(FACE_EYES_HALF);
    }

    @Override
    protected void setFaceScorn() {
        setSimpleFace(FACE_EYES_HALF);
    }

    @Override
    protected void setFaceHungry() {
        setSimpleFace(FACE_EYES_HALF);
    }

    @Override
    protected void setFaceAngry() {
        setSimpleFace(FACE_EYES_OPEN);
    }

    @Override
    protected void setFaceBored() {
        setSimpleFace(FACE_EYES_CLOSED);
    }

    @Override
    protected void setFaceShy() {
        setSimpleFace(FACE_EYES_OPEN);
    }

    @Override
    protected void setFaceHappy() {
        setSimpleFace(FACE_EYES_OPEN);
    }

    private void setSimpleFace(int faceId) {
        this.setFaceId(faceId);
        this.setMouthId(MOUTH_FRONT_0);
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_HA_SPAWN_EGG.get();
    }
}

