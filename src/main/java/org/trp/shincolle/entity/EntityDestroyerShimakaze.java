package org.trp.shincolle.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.List;

public class EntityDestroyerShimakaze extends EntityShipBase implements IShipSummonAttack {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_HAIR_ANCHOR = "equip_hair_anchor";
    public static final String EQUIP_HAIR_FRONT_1 = "equip_hair_front_1";
    public static final String EQUIP_HAIR_FRONT_2 = "equip_hair_front_2";
    public static final String EQUIP_HAIR_FRONT_3 = "equip_hair_front_3";

    private static final int MAX_RENSOUHOU = 6;
    private static final float[][] TORPEDO_OFFSETS = {
            {0f, 0f}, {3.5f, 3.5f}, {3.5f, -3.5f}, {-3.5f, 3.5f}, {-3.5f, -3.5f}
    };

    private int numRensouhou = MAX_RENSOUHOU;

    public EntityDestroyerShimakaze(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 45});
        setStateMinor(STATE_MINOR_FACTION_ID, -1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 36);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 6);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_HAIR_ANCHOR, true);
        setEquipFlag(EQUIP_HAIR_FRONT_1, true);
        setEquipFlag(EQUIP_HAIR_FRONT_2, false);
        setEquipFlag(EQUIP_HAIR_FRONT_3, false);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * -0.04f : this.getBbHeight() * 0.16f;
        }
        return this.getBbHeight() * 0.67f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_HAIR_ANCHOR, "gui.shincolle.equip.hair_anchor"),
                new EquipOption(EQUIP_HAIR_FRONT_1, "gui.shincolle.equip.hair_front_1"),
                new EquipOption(EQUIP_HAIR_FRONT_2, "gui.shincolle.equip.hair_front_2"),
                new EquipOption(EQUIP_HAIR_FRONT_3, "gui.shincolle.equip.hair_front_3")
        );
    }

    private void updateServerLogic() {
        if (this.numRensouhou < MAX_RENSOUHOU) {
            this.numRensouhou++;
        }
        if (this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateMinor(0) / 35 + 1;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        80 + this.getStateMinor(0), amp, false, false));
            }
        }
    }

    public boolean attackEntityWithAmmo(Entity target) {
        if (this.numRensouhou <= 0) {
            return false;
        }
        if (!consumeLightAmmo(4)) {
            return false;
        }

        this.numRensouhou--;
        this.setAttackTick(50);
        this.applyEmotesReaction(3);

        spawnAttackEffects();
        summonRensouhou(target);

        return true;
    }

    public boolean attackEntityWithHeavyAmmo(BlockPos target) {
        return launchTorpedoSalvo(target, null);
    }

    public boolean attackEntityWithHeavyAmmo(Entity target) {
        if (target == null) {
            return false;
        }
        return launchTorpedoSalvo(target.blockPosition(), target);
    }

    private void spawnAttackEffects() {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY() + 1.0D, this.getZ(),
                    12, 0.25D, 0.1D, 0.25D, 0.02D);
        }
    }

    private void summonRensouhou(Entity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (checkModelState(0, this.getStateEmotion(0))) {
            EntityRensouhouS rensouhou = ModEntities.RENSOUHOU_S.get().create(serverLevel);
            if (rensouhou != null) {
                rensouhou.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                serverLevel.addFreshEntity(rensouhou);
            }
        } else {
            EntityRensouhou rensouhou = ModEntities.RENSOUHOU.get().create(serverLevel);
            if (rensouhou != null) {
                rensouhou.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                serverLevel.addFreshEntity(rensouhou);
            }
        }
    }

    private boolean launchTorpedoSalvo(BlockPos targetPos, Entity targetEntity) {
        if (!consumeHeavyAmmo(1)) {
            return false;
        }

        this.setAttackTick(50);
        this.applyEmotesReaction(3);

        Vec3 aimPos = new Vec3(targetPos.getX() + 0.5D, targetPos.getY() + 0.5D, targetPos.getZ() + 0.5D);
        Vec3 toTarget = aimPos.subtract(this.position());
        if (toTarget.length() < 6.0D && toTarget.length() > 1.0E-6D) {
            Vec3 push = toTarget.normalize().scale(6.0D - toTarget.length());
            aimPos = aimPos.add(push);
        }

        spawnTorpedoes(aimPos, targetEntity);
        return true;
    }

    private void spawnTorpedoes(Vec3 centerTarget, Entity targetEntity) {
        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float damage = Math.max(4.0F, baseDamage * 0.3F);
        float speed = 0.7f;
        int life = 160;
        float explosionRadius = 3.5f;

        double targetY = targetEntity != null
                ? targetEntity.getY() + targetEntity.getBbHeight() * 0.1D
                : centerTarget.y + 0.2D;

        for (float[] offset : TORPEDO_OFFSETS) {
            Vec3 target = centerTarget.add(offset[0], targetY - centerTarget.y, offset[1]);
            Vec3 direction = target.subtract(this.position().add(0.0D, this.getBbHeight() * 0.7D, 0.0D));
            Vec3 velocity = direction.lengthSqr() < 1.0E-6D ? new Vec3(0.0D, 0.0D, 0.0D)
                    : direction.normalize().scale(speed);

            EntityAbyssMissile missile = new EntityAbyssMissile(this.level(), this, targetEntity, damage,
                    EntityAbyssMissile.MoveType.PRESET_VELOCITY, speed, 0.25f, 0.25f, velocity, life, explosionRadius);
            this.level().addFreshEntity(missile);
        }
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("NumRensouhou", this.numRensouhou);
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("NumRensouhou")) {
            this.numRensouhou = Mth.clamp(tag.getInt("NumRensouhou"), 0, MAX_RENSOUHOU);
        }
    }

    @Override
    public int getNumServant() {
        return this.numRensouhou;
    }

    @Override
    public void setNumServant(int num) {
        this.numRensouhou = Mth.clamp(num, 0, MAX_RENSOUHOU);
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
        return ModItems.DESTROYER_SHIMAKAZE_SPAWN_EGG.get();
    }
}

