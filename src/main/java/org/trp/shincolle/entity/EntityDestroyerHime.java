package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.FaceExpressionConfig;
import org.trp.shincolle.entity.base.FaceStep;
import org.trp.shincolle.entity.base.FaceTimeline;
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
        getStateComponent().setFactionId(10);
        getStateComponent().setShipClassId(27);
        getStateComponent().setSpecialEquip(5);
        getStateComponent().setRarity(6);
        setStateCanRide(true);
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        list.add(new EquipOption(EQUIP_HAT, "gui.shincolle.equip.hat"));
        list.add(new EquipOption(EQUIP_CANNON, "gui.shincolle.equip.cannon"));
        list.add(new EquipOption(EQUIP_BELT, "gui.shincolle.equip.belt"));
        list.add(new EquipOption(EQUIP_LEG, "gui.shincolle.equip.leg"));
        list.add(new EquipOption(EQUIP_HAND, "gui.shincolle.equip.hand"));
        return list;
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
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int ampSpeed = this.getStateComponent().getAffectionLegacy() / 45 + 1;
                int ampHaste = this.getStateComponent().getAffectionLegacy() / 30;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        300, ampSpeed, false, false));
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,
                        300, ampHaste, false, false));
            }
        }
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
protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_HIME_SPAWN_EGG.get();
    }
}

