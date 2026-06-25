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
import org.trp.shincolle.entity.base.FaceExpressionConfig;
import org.trp.shincolle.entity.base.FaceStep;
import org.trp.shincolle.entity.base.FaceTimeline;
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
        getStateComponent().setFactionId(-1);
        getStateComponent().setShipClassId(36);
        getStateComponent().setSpecialEquip(5);
        getStateComponent().setRarity(6);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
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
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.addAll(List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_HAIR_ANCHOR, "gui.shincolle.equip.hair_anchor"),
                new EquipOption(EQUIP_HAIR_FRONT_1, "gui.shincolle.equip.hair_front_1"),
                new EquipOption(EQUIP_HAIR_FRONT_2, "gui.shincolle.equip.hair_front_2"),
                new EquipOption(EQUIP_HAIR_FRONT_3, "gui.shincolle.equip.hair_front_3")
        ));
        return list;
    }

    private void updateServerLogic() {
        if (this.numRensouhou < MAX_RENSOUHOU) {
            this.numRensouhou++;
        }
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateComponent().getAffectionLegacy() / 35 + 1;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        80 + this.getStateComponent().getAffectionLegacy(), amp, false, false));
            }
        }
    }

    @Override
    public void performLightAttack(Entity target) {
        if (this.numRensouhou > 0 && this.getRandom().nextInt(3) == 0) {
            if (this.attackEntityWithAmmo(target)) {
                return;
            }
        }
        super.performLightAttack(target);
    }

    @Override
    public boolean performHeavyAttack(Entity target) {
        if (this.attackEntityWithHeavyAmmo(target)) {
            return true;
        }
        return super.performHeavyAttack(target);
    }

    public boolean attackEntityWithAmmo(Entity target) {
        if (this.numRensouhou <= 0) {
            return false;
        }
        if (!consumeLightAmmo(4)) {
            return false;
        }

        this.numRensouhou--;
        this.setAttackTick(100);
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
                rensouhou.initSummon(this, target, 0);
                serverLevel.addFreshEntity(rensouhou);
            }
        } else {
            EntityRensouhou rensouhou = ModEntities.RENSOUHOU.get().create(serverLevel);
            if (rensouhou != null) {
                rensouhou.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                rensouhou.initSummon(this, target, 0);
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

        if (this.level() instanceof ServerLevel serverLevel) {
            double distance = targetEntity != null ? this.distanceTo(targetEntity) : this.position().distanceTo(aimPos);
            if (this.getRandom().nextFloat() <= org.trp.shincolle.utility.CombatHelper.calcMissRate(this, (float) distance)) {
                double offsetX = (this.getRandom().nextDouble() - 0.5D) * 10.0D;
                double offsetY = this.getRandom().nextDouble() * 5.0D;
                double offsetZ = (this.getRandom().nextDouble() - 0.5D) * 10.0D;
                aimPos = aimPos.add(offsetX, offsetY, offsetZ);
                this.spawnCombatTextParticle(COMBAT_TEXT_MISS);
            }
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
        return ModItems.DESTROYER_SHIMAKAZE_SPAWN_EGG.get();
    }

    @Override
    protected net.minecraft.world.BossEvent.BossBarColor getBossBarColor() {
        return net.minecraft.world.BossEvent.BossBarColor.YELLOW;
    }
}

