package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;

public class EntityDestroyerHime extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_HAT = "equip_hat";
    public static final String EQUIP_CANNON = "equip_cannon";
    public static final String EQUIP_BELT = "equip_belt";
    public static final String EQUIP_LEG = "equip_leg";
    public static final String EQUIP_HAND = "equip_hand";

    public EntityDestroyerHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 50});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 27);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 6);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);

        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_HAT, true);
        setEquipFlag(EQUIP_CANNON, true);
        setEquipFlag(EQUIP_BELT, true);
        setEquipFlag(EQUIP_LEG, true);
        setEquipFlag(EQUIP_HAND, true);
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_HAT, "gui.shincolle.equip.hat"),
                new EquipOption(EQUIP_CANNON, "gui.shincolle.equip.cannon"),
                new EquipOption(EQUIP_BELT, "gui.shincolle.equip.belt"),
                new EquipOption(EQUIP_LEG, "gui.shincolle.equip.leg"),
                new EquipOption(EQUIP_HAND, "gui.shincolle.equip.hand")
        );
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
            if (this.getStateEmotion(1) == 4) {
                return 0.0f;
            }
            return this.getBbHeight() * 0.62f;
        }
        return this.getBbHeight() * 0.76f;
    }

    private void applyBuffToOwner() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int ampSpeed = this.getStateMinor(0) / 45 + 1;
                int ampHaste = this.getStateMinor(0) / 30;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        80 + this.getStateMinor(0), ampSpeed, false, false));
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,
                        80 + this.getStateMinor(0), ampHaste, false, false));
            }
        }
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
        return ModItems.DESTROYER_HIME_SPAWN_EGG.get();
    }
}

