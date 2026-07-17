package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.FaceExpressionConfig;
import org.trp.shincolle.entity.base.FaceStep;
import org.trp.shincolle.entity.base.FaceTimeline;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityBattleshipRe extends EntityShipBase {

    public static final String EQUIP_HAIR = "equip_hair";
    public static final String EQUIP_BAG = "equip_bag";
    public static final String EQUIP_EARS = "equip_ears";

    private static final int PUSH_MAX_TICKS = 200;
    private static final float PUSH_ENGAGE_DISTANCE = 2.5f;

    private boolean isPushing = false;
    private int tickPush = 0;
    private LivingEntity targetPush = null;

    public EntityBattleshipRe(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 25, 0, 40});
        getStateComponent().setFactionId(6);
        getStateComponent().setShipClassId(15);
        getStateComponent().setSpecialEquip(2);
        getStateComponent().setRarity(3);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeBB);
    }


    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount & 0x7F) == 0) {
            updateServerLogic();
        }
        if (this.isPushing) {
            updatePushingState();
        }
    }

    @Override
    protected float[] computeLegacyAuraBuffs() {
        float[] buffs = new float[21];
        if (this.isStateRingEffect()) {
            buffs[10] += 0.10F;
            buffs[11] += 0.10F;
        }
        return buffs;
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * 0.35f : 0.0f;
        }
        return this.getBbHeight() * 0.55f;
    }

    @Override
    protected void performLightAttack(Entity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (target == null || !target.isAlive()) {
            return;
        }
        if (!consumeLightAmmo(1)) {
            return;
        }
        this.setFuel(this.getFuel() - org.trp.shincolle.Config.fuelConsumeActionLight);

        float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        if (damage <= 0.0F) {
            damage = 2.0F;
        }
        boolean hurt = target.hurt(this.damageSources().mobAttack(this), damage);

        this.spawnLightAttackMuzzleParticles(serverLevel, target);
        serverLevel.sendParticles(org.trp.shincolle.init.ModParticles.PARTICLE_LIGHTNING.get(),
                this.getX(), this.getY() + 1.5D, this.getZ(),
                1, 0.1D, (double)this.getId(), 0.0D, 0.0D);
        serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(),
                4, 0.2D, 0.2D, 0.2D, 0.0D);
        
        this.playSound(org.trp.shincolle.init.ModSounds.SHIP_FIRELIGHT.get(), this.getSoundVolume(),
                this.getRandom().nextFloat() * 0.12F + 0.98F);

        this.setAttackTick(50);
        this.applyEmotesReaction(3);

        if (hurt) {
            applyChainedLightningAttack(target, damage);
        }
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
                new FaceStep(128, FACE_DOT_EYES_TEAR, mapLegacyMouth(5)),
                new FaceStep(190, FACE_CRY, mapLegacyMouth(2))
            }, FACE_CRY, mapLegacyMouth(5)))
            .damaged(new FaceTimeline(EMOTION_TICK_MASK_9BIT, new FaceStep[] {
                new FaceStep(60, FACE_DOT_EYES_TEAR, mapLegacyMouth(4)),
                new FaceStep(200, FACE_DOT_EYES_TEAR, mapLegacyMouth(5)),
                new FaceStep(250, FACE_TENSION, mapLegacyMouth(4)),
                new FaceStep(400, FACE_TENSION, mapLegacyMouth(5)),
                new FaceStep(450, FACE_SOFT, mapLegacyMouth(4))
            }, FACE_SOFT, mapLegacyMouth(5)))
            .scorn(new FaceTimeline(0, new FaceStep[0], FACE_EYES_HALF, mapLegacyMouth(1)))
            .hungry(new FaceTimeline(0, new FaceStep[0], FACE_DESPAIR, mapLegacyMouth(5)))
            .angry(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(64, FACE_EYES_CLOSED, mapLegacyMouth(3)),
                new FaceStep(128, FACE_EYES_CLOSED, mapLegacyMouth(4)),
                new FaceStep(170, FACE_EYES_HALF, mapLegacyMouth(1))
            }, FACE_EYES_HALF, mapLegacyMouth(3)))
            .bored(new FaceTimeline(EMOTION_TICK_MASK_9BIT, new FaceStep[] {
                new FaceStep(80, FACE_EYES_CLOSED, mapLegacyMouth(0)),
                new FaceStep(170, FACE_EYES_CLOSED, mapLegacyMouth(4)),
                new FaceStep(250, FACE_WINK, mapLegacyMouth(0)),
                new FaceStep(340, FACE_WINK, mapLegacyMouth(4)),
                new FaceStep(420, FACE_EYES_OPEN, mapLegacyMouth(3))
            }, FACE_EYES_OPEN, mapLegacyMouth(4)))
            .shy(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(150, FACE_EYES_OPEN, mapLegacyMouth(2))
            }, FACE_EYES_OPEN, mapLegacyMouth(4)))
            .happy(new FaceTimeline(EMOTION_TICK_MASK_8BIT, new FaceStep[] {
                new FaceStep(80, FACE_TENSION, mapLegacyMouth(4)),
                new FaceStep(140, FACE_TENSION, mapLegacyMouth(5))
            }, FACE_WINK, mapLegacyMouth(4)))
            .build();
    }

    private void updateServerLogic() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            LivingEntity owner = this.getOwner();
            if (owner != null && this.distanceToSqr(owner) < 256.0D) {
                int duration = 50 + this.getStateComponent().getAffectionLegacy();
                int amp = Math.max(0, this.getStateComponent().getAffectionLegacy() / 50);
                owner.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, amp, false, false));
            }
        }

        boolean canFindTarget = (this.tickCount & 0xFF) == 0 && this.getRandom().nextInt(5) != 0;
        boolean isActionBlocked = this.getIsSitting() || this.isPassenger() || this.isStateNoEquip() || this.isLeashed() || this.isInDeadPose();
        if (canFindTarget && !isActionBlocked) {
            findTargetPush();
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_HAIR, "gui.shincolle.equip.hair"));
        list.add(new EquipOption(EQUIP_BAG, "gui.shincolle.equip.bag"));
        list.add(new EquipOption(EQUIP_EARS, "gui.shincolle.equip.ears"));
        return list;
    }

    private void updatePushingState() {
        this.tickPush++;
        if (this.tickPush > PUSH_MAX_TICKS || this.targetPush == null || !this.targetPush.isAlive() || this.isInDeadPose()) {
            cancelPush();
            return;
        }
        if (this.distanceTo(this.targetPush) <= PUSH_ENGAGE_DISTANCE) {
            executePushAttack();
        } else if (this.tickCount % 32 == 0) {
            this.getNavigation().moveTo(this.targetPush, 1.0D);
        }
    }

    private void executePushAttack() {
        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;
        Vec3 push = new Vec3(-Mth.sin(yawRad) * 0.5f, 0.5f, Mth.cos(yawRad) * 0.5f);

	    this.targetPush.hasImpulse = true;
        this.targetPush.hurtMarked = true;

        this.targetPush.setDeltaMovement(this.targetPush.getDeltaMovement().add(push));
        this.swing(InteractionHand.MAIN_HAND);
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD,
                    this.targetPush.getX(), this.targetPush.getY() + 1.0D, this.targetPush.getZ(),
                    6, 0.2D, 0.2D, 0.2D, 0.02D);

            int shipClassId = this.getStateComponent().getShipClassId();
            net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                org.trp.shincolle.Shincolle.MODID, "ship-hit-" + shipClassId
            );
            net.minecraft.sounds.SoundEvent voiceSound = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(id);
            this.playSound(voiceSound, this.getSoundVolume(), this.getShipSoundPitch());
        }
        cancelPush();
    }

    private void cancelPush() {
        this.isPushing = false;
        this.tickPush = 0;
        this.targetPush = null;
    }

    private void findTargetPush() {
        AABB impactBox = this.getBoundingBox().inflate(12.0D, 6.0D, 12.0D);
        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, impactBox,
                ent -> ent != this && ent.isAlive() && ent.isPushable());
        if (!list.isEmpty()) {
            this.targetPush = list.get(this.getRandom().nextInt(list.size()));
            this.tickPush = 0;
            this.isPushing = true;
        }
    }

    private void applyChainedLightningAttack(Entity primaryTarget, float baseAttack) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        int maxTargets = Math.max(1, (int) (this.getLevel() * 0.05f));
        float damage = baseAttack * 0.2f;
        AABB impactBox = primaryTarget.getBoundingBox().inflate(3.5D, 3.5D, 3.5D);
        List<Entity> potentialTargets = serverLevel.getEntities(this, impactBox);
        int hits = 0;
        for (Entity entity : potentialTargets) {
            if (hits >= maxTargets) {
                break;
            }
            if (entity == this || entity == primaryTarget || !entity.isAlive() || !entity.canBeCollidedWith()) {
                continue;
            }
            if (entity instanceof EntityShipBase ship
                    && Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            entity.hurt(this.damageSources().mobAttack(this), damage);
            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    entity.getX(), entity.getY() + entity.getBbHeight() * 0.5D, entity.getZ(),
                    4, 0.2D, 0.2D, 0.2D, 0.0D);
            hits++;
        }
    }


    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BATTLESHIP_RE_SPAWN_EGG.get();
    }
}

