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
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
        setStateMinor(STATE_MINOR_FACTION_ID, -1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 52);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 5);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_TORPEDO, true);
        setEquipFlag(EQUIP_HAIR_FRONT_1, true);
        setEquipFlag(EQUIP_HAIR_FRONT_2, false);
        setEquipFlag(EQUIP_HAIR_FRONT_3, false);
        this.riderType = 0;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientLogic();
        } else {
            updateServerLogic();
        }

        EntityDestroyerAkatsuki akatsuki = getAkatsukiRiding();
        if (akatsuki != null) {
            akatsuki.syncRotateToRider();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * -0.07f : this.getBbHeight() * 0.26f;
        }
        return this.getBbHeight() * 0.64f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_TORPEDO, "gui.shincolle.equip.torpedo"),
                new EquipOption(EQUIP_HAIR_FRONT_1, "gui.shincolle.equip.hair_front_1"),
                new EquipOption(EQUIP_HAIR_FRONT_2, "gui.shincolle.equip.hair_front_2"),
                new EquipOption(EQUIP_HAIR_FRONT_3, "gui.shincolle.equip.hair_front_3")
        );
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

        checkRiderType();
        checkRidingState();

        if ((this.tickCount % 128) == 0) {
            applyBuffToOwner();
        }
    }

    private void updateClientLogic() {
        if ((this.tickCount % 4) == 0) {
            spawnEngineParticles();
        }
        if ((this.tickCount % 16) == 0) {
            checkRiderType();
            checkRidingState();
        }
    }

    private void applyBuffToOwner() {
        if (this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateMinor(0) / 45 + 1;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.JUMP,
                        80 + this.getStateMinor(0), amp, false, false));
            }
        }
    }

    private void spawnEngineParticles() {
        boolean canSpawn = checkModelState(0, this.getStateEmotion(0)) && !this.getIsSitting()
                && !this.getStateFlag(2) && this.riderType < 2;
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
    protected void setFaceNormal() {
        this.setFaceId(FACE_EYES_OPEN);
        int tick = this.tickCount & EMOTION_TICK_MASK_8BIT;
        if (this.getStateEmotion(7) == 4 && tick > 200) {
            this.setMouthId(mapLegacyMouth(0));
        } else {
            this.setMouthId(mapLegacyMouth(3));
        }
    }

    @Override
    protected void setFaceCry() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 128) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 2 : 1));
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
            this.setMouthId(mapLegacyMouth(tick < 60 ? 2 : 1));
        } else if (tick < 400) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 0 : 3));
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
            this.setMouthId(mapLegacyMouth(tick < 64 ? 3 : 1));
        } else {
            this.setFaceId(FACE_EYES_HALF);
            this.setMouthId(mapLegacyMouth(tick < 170 ? 1 : 3));
        }
    }

    @Override
    protected void setFaceBored() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_9BIT);
        if (tick < 170) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 0 : 3));
        } else if (tick < 340) {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 0 : 3));
        } else {
            this.setFaceId(FACE_EYES_OPEN);
            this.setMouthId(mapLegacyMouth(tick < 420 ? 0 : 3));
        }
    }

    @Override
    protected void setFaceShy() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        this.setFaceId(FACE_EYES_OPEN);
        this.setMouthId(mapLegacyMouth(tick < 150 ? 3 : 2));
    }

    @Override
    protected void setFaceHappy() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 140) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 3 : 4));
        } else {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(0));
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
        return ModItems.DESTROYER_HIBIKI_SPAWN_EGG.get();
    }
}

