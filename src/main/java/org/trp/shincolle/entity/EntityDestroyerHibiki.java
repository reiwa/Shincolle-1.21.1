package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
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

public class EntityDestroyerHibiki extends EntityShipBase implements IShipRiderType {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_TORPEDO = "equip_torpedo";
    public static final String EQUIP_HAIR_FRONT_1 = "equip_hair_front_1";
    public static final String EQUIP_HAIR_FRONT_2 = "equip_hair_front_2";
    public static final String EQUIP_HAIR_FRONT_3 = "equip_hair_front_3";

    private int riderType;

    public EntityDestroyerHibiki(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 50});
        setStateCanRide(true);
        getStateComponent().setFactionId(-1);
        getStateComponent().setShipClassId(52);
        getStateComponent().setSpecialEquip(5);
        getStateComponent().setRarity(5);
        setStateGuiBtn4(false);
        this.riderType = 0;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        checkRiderType();
        checkRidingState();

        if (this.level().isClientSide) {
            updateClientLogic();
        }

        EntityDestroyerAkatsuki akatsuki = getAkatsukiRiding();
        if (akatsuki != null) {
            akatsuki.syncRotateToRider();
        }
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        updateServerLogic();
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * -0.07f : this.getBbHeight() * 0.26f;
        }
        return this.getBbHeight() * 0.64f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        list.add(new EquipOption(EQUIP_TORPEDO, "gui.shincolle.equip.torpedo"));
        list.add(new EquipOption(EQUIP_HAIR_FRONT_1, "gui.shincolle.equip.hair_front_1"));
        list.add(new EquipOption(EQUIP_HAIR_FRONT_2, "gui.shincolle.equip.hair_front_2"));
        list.add(new EquipOption(EQUIP_HAIR_FRONT_3, "gui.shincolle.equip.hair_front_3"));
        return list;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean damaged = super.hurt(source, amount);
        if (damaged && !this.level().isClientSide) {
            EntityDestroyerAkatsuki akatsuki = getAkatsukiRiding();
            if (akatsuki != null) {
                akatsuki.dismountAllRider();
            }
        }
        return damaged;
    }

    private void updateServerLogic() {
        if ((this.tickCount % 32) != 0) {
            return;
        }

        if ((this.tickCount % 128) == 0) {
            applyBuffToOwner();
        }
    }

    private void updateClientLogic() {
        if ((this.tickCount % 4) == 0) {
            spawnEngineParticles();
        }
    }

    private void applyBuffToOwner() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateComponent().getAffectionLegacy() / 45 + 1;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.JUMP,
                        80 + this.getStateComponent().getAffectionLegacy(), amp, false, false));
            }
        }
    }

    private void spawnEngineParticles() {
        boolean canSpawn = !this.getIsSitting()
                && this.getEquipFlag(EQUIP_RIGGING) && this.riderType < 2;
        if (canSpawn) {
            float[] partPos = rotateXZByAxis(-0.42f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX() + partPos[1], this.getY() + 1.4D, this.getZ() + partPos[0],
                    0.0D, 0.0D, 0.0D);
        }
    }

    private void checkRiderType() {
        this.riderType = 0;
        EntityDestroyerAkatsuki akatsuki = getAkatsukiRiding();
        if (akatsuki != null) {
            this.riderType = akatsuki.getRiderType();
        }
    }

    private void checkRidingState() {
        if (this.riderType > 1) {
            this.setRidingState(2);
        } else if (this.riderType == 1) {
            this.setRidingState(1);
        } else {
            this.setRidingState(0);
        }
    }

    private EntityDestroyerAkatsuki getAkatsukiRiding() {
        if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
            return akatsuki;
        }
        return null;
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
    protected FaceExpressionConfig createFaceExpressionConfig() {
        return FaceExpressionConfig.builder()
            .normal(new FaceTimeline(0xFF, new FaceStep[0], FACE_EYES_OPEN, mapLegacyMouth(3)))
            .normalBored(new FaceTimeline(0xFF, new FaceStep[] {
                new FaceStep(200, FACE_EYES_OPEN, mapLegacyMouth(3))
            }, FACE_EYES_OPEN, mapLegacyMouth(0)))
            .cry(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(64, FACE_DOT_EYES_TEAR, mapLegacyMouth(2)),
                new FaceStep(128, FACE_DOT_EYES_TEAR, mapLegacyMouth(1))
            }, FACE_CRY, mapLegacyMouth(2)))
            .damaged(new FaceTimeline(EMOTION_TICK_MASK_9BIT, new FaceStep[] {
                new FaceStep(60, FACE_DOT_EYES_TEAR, mapLegacyMouth(2)),
                new FaceStep(200, FACE_DOT_EYES_TEAR, mapLegacyMouth(1)),
                new FaceStep(250, FACE_TENSION, mapLegacyMouth(0)),
                new FaceStep(400, FACE_TENSION, mapLegacyMouth(3)),
                new FaceStep(450, FACE_SOFT, mapLegacyMouth(0))
            }, FACE_SOFT, mapLegacyMouth(1)))
            .scorn(new FaceTimeline(0, new FaceStep[0], FACE_EYES_HALF, mapLegacyMouth(1)))
            .hungry(new FaceTimeline(0, new FaceStep[0], FACE_DESPAIR, mapLegacyMouth(2)))
            .angry(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(64, FACE_EYES_CLOSED, mapLegacyMouth(3)),
                new FaceStep(128, FACE_EYES_CLOSED, mapLegacyMouth(1)),
                new FaceStep(170, FACE_EYES_HALF, mapLegacyMouth(1))
            }, FACE_EYES_HALF, mapLegacyMouth(3)))
            .bored(new FaceTimeline(EMOTION_TICK_MASK_9BIT, new FaceStep[] {
                new FaceStep(80, FACE_EYES_CLOSED, mapLegacyMouth(0)),
                new FaceStep(170, FACE_EYES_CLOSED, mapLegacyMouth(3)),
                new FaceStep(250, FACE_WINK, mapLegacyMouth(0)),
                new FaceStep(340, FACE_WINK, mapLegacyMouth(3)),
                new FaceStep(420, FACE_EYES_OPEN, mapLegacyMouth(0))
            }, FACE_EYES_OPEN, mapLegacyMouth(3)))
            .shy(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(150, FACE_EYES_OPEN, mapLegacyMouth(3))
            }, FACE_EYES_OPEN, mapLegacyMouth(2)))
            .happy(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(80, FACE_TENSION, mapLegacyMouth(3)),
                new FaceStep(140, FACE_TENSION, mapLegacyMouth(4))
            }, FACE_WINK, mapLegacyMouth(0)))
            .build();
    }


    @Override
    public boolean supportsItemPickup() {
        return true;
    }
protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_HIBIKI_SPAWN_EGG.get();
    }
}

