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
        setStateMinor(STATE_MINOR_FACTION_ID, 6);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 15);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 2);
        setStateMinor(STATE_MINOR_RARITY, 3);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            setEquipFlag(EQUIP_HAIR, true);
            setEquipFlag(EQUIP_BAG, true);
            setEquipFlag(EQUIP_EARS, true);
            if ((this.tickCount & 0x7F) == 0) {
                updateServerLogic();
            }
            if (this.isPushing) {
                updatePushingState();
            }
        }
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

        float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        if (damage <= 0.0F) {
            damage = 2.0F;
        }
        boolean hurt = target.hurt(this.damageSources().mobAttack(this), damage);

        serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(),
                4, 0.2D, 0.2D, 0.2D, 0.0D);

        this.setAttackTick(50);
        this.applyEmotesReaction(3);

        if (hurt) {
            applyChainedLightningAttack(target, damage);
        }
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
            this.setMouthId(mapLegacyMouth(tick < 64 ? 2 : 5));
        } else {
            this.setFaceId(FACE_CRY);
            this.setMouthId(mapLegacyMouth(tick < 190 ? 2 : 5));
        }
    }

    @Override
    protected void setFaceDamaged() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_9BIT);
        if (tick < 200) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 60 ? 4 : 5));
        } else if (tick < 400) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 4 : 5));
        } else {
            this.setFaceId(FACE_SOFT);
            this.setMouthId(mapLegacyMouth(tick < 450 ? 4 : 5));
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
        this.setMouthId(mapLegacyMouth(5));
    }

    @Override
    protected void setFaceAngry() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 128) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 3 : 4));
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
            this.setMouthId(mapLegacyMouth(tick < 80 ? 0 : 4));
        } else if (tick < 340) {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 0 : 4));
        } else {
            this.setFaceId(FACE_EYES_OPEN);
            this.setMouthId(mapLegacyMouth(tick < 420 ? 3 : 4));
        }
    }

    @Override
    protected void setFaceShy() {
        this.setFaceId(FACE_EYES_OPEN);
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        this.setMouthId(mapLegacyMouth(tick < 150 ? 2 : 4));
    }

    @Override
    protected void setFaceHappy() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 140) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 4 : 5));
        } else {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(4));
        }
    }

    private void updateServerLogic() {
        if (this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            LivingEntity owner = this.getOwner();
            if (owner != null && this.distanceToSqr(owner) < 256.0D) {
                int duration = 50 + this.getStateMinor(0);
                int amp = Math.max(0, this.getStateMinor(0) / 50);
                owner.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, amp, false, false));
            }
        }

        boolean canFindTarget = (this.tickCount & 0xFF) == 0 && this.getRandom().nextInt(5) != 0;
        boolean isActionBlocked = this.getIsSitting() || this.isPassenger() || this.getStateFlag(2) || this.isLeashed();
        if (canFindTarget && !isActionBlocked) {
            findTargetPush();
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_HAIR, "gui.shincolle.equip.hair"),
                new EquipOption(EQUIP_BAG, "gui.shincolle.equip.bag"),
                new EquipOption(EQUIP_EARS, "gui.shincolle.equip.ears")
        );
    }

    private void updatePushingState() {
        this.tickPush++;
        if (this.tickPush > PUSH_MAX_TICKS || this.targetPush == null || !this.targetPush.isAlive()) {
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
        this.targetPush.setDeltaMovement(this.targetPush.getDeltaMovement().add(push));
        this.swing(InteractionHand.MAIN_HAND);
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD,
                    this.targetPush.getX(), this.targetPush.getY() + 1.0D, this.targetPush.getZ(),
                    6, 0.2D, 0.2D, 0.2D, 0.02D);
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
                ent -> ent != this && ent.isAlive() && ent.canBeCollidedWith());
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
        return ModItems.BATTLESHIP_RE_SPAWN_EGG.get();
    }
}

