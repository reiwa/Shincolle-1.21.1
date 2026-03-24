package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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

public class EntityBattleshipNagato extends EntityShipBase {

    public static final String EQUIP_HEAD = "equip_head";
    public static final String EQUIP_CANNON = "equip_cannon";

    private static final int EMOTION_ATTACK_PHASE = 5;
    private static final int[] LOVE_PARTICLES = {31, 1, 7, 16, 29};

    public EntityBattleshipNagato(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 6);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 37);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 3);
        setStateMinor(STATE_MINOR_RARITY, 2);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setEquipFlag(EQUIP_HEAD, true);
        setEquipFlag(EQUIP_CANNON, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientParticles();
        } else if ((this.tickCount % 128) == 0 && !this.isInDeadPose()) {
            addMoraleSpecialEvent();
            if (this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0) {
                applyBuffToNearbyAllies();
            }
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            if (checkModelState(1, this.getStateEmotion(0))) {
                return this.getBbHeight() * 0.42f;
            }
            if (this.getStateEmotion(1) == 4) {
                return 0.0;
            }
            return this.getBbHeight() * 0.35f;
        }
        return this.getBbHeight() * 0.75f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_HEAD, "gui.shincolle.equip.head"),
                new EquipOption(EQUIP_CANNON, "gui.shincolle.equip.cannon")
        );
    }

    @Override
    protected boolean performHeavyAttack(Entity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return false;
        }
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (!consumeHeavyAmmo(1)) {
            return false;
        }

        int phase = this.getStateEmotion(EMOTION_ATTACK_PHASE) + 1;
        if (phase > 3) {
            this.setStateEmotion(EMOTION_ATTACK_PHASE, 0, true);
            performFinalAttack(serverLevel, target);
        } else {
            this.setStateEmotion(EMOTION_ATTACK_PHASE, phase, true);
            spawnAttackChargeParticles(serverLevel, phase);
        }

        this.setAttackTick(50);
        this.applyEmotesReaction(3);
        return true;
    }

    private void updateClientParticles() {
        if (this.tickCount % 4 == 0 && checkModelState(1, this.getStateEmotion(0))
                && !this.getIsSitting() && !this.getStateFlag(2) && !this.isInDeadPose()) {
            float[] partPos = rotateXZByAxis(-0.56f, 0.0f, this.yBodyRot * Mth.DEG_TO_RAD, 1.0f);
            this.level().addParticle(ParticleTypes.FLAME,
                    this.getX() + partPos[1], this.getY() + 1.5D, this.getZ() + partPos[0],
                    0.0D, 0.0D, 0.0D);
        }

        if (this.tickCount % 8 == 0) {
            int atkPhase = this.getStateEmotion(EMOTION_ATTACK_PHASE);
            if (atkPhase == 1 || atkPhase == 3) {
                this.level().addParticle(ParticleTypes.SMOKE,
                        this.getX(), this.getY() + 1.2D, this.getZ(),
                        0.0D, 0.02D, 0.0D);
            }
        }
    }

    private void applyBuffToNearbyAllies() {
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }
        int duration = 50 + this.getStateMinor(0);
        int amp = Math.max(0, this.getStateMinor(0) / 70);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, amp, false, false));
        }
    }

    private void addMoraleSpecialEvent() {
        if (this.isInDeadPose()) {
            return;
        }
        List<LivingEntity> nearby = this.level().getEntitiesOfClass(LivingEntity.class,
                this.getBoundingBox().inflate(16.0D, 12.0D, 16.0D),
                entity -> entity instanceof EntityNorthernHime
                        || (entity instanceof EntityShipBase ship && ship.getStateMinor(STATE_MINOR_FACTION_ID) != -1));
        if (nearby.isEmpty()) {
            return;
        }
        if (this.getMorale() < 7650) {
            this.addMorale(150 * nearby.size());
        }
        if (!this.getIsSitting() && !this.isPassenger() && this.getRandom().nextFloat() > 0.5f) {
            LivingEntity target = nearby.get(this.getRandom().nextInt(nearby.size()));
            this.getNavigation().moveTo(target, 1.0D);
            int particleId = LOVE_PARTICLES[this.getRandom().nextInt(LOVE_PARTICLES.length)];
            this.applyParticleEmotion(particleId);
        }
    }

    private void spawnAttackChargeParticles(ServerLevel serverLevel, int phase) {
        int count = phase == 2 ? 8 : 4;
        serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 1.0D, this.getZ(),
                count, 0.3D, 0.2D, 0.3D, 0.02D);
    }

    private void performFinalAttack(ServerLevel serverLevel, Entity target) {
        Vec3 delta = target.position().subtract(this.position());
        Vec3 dir = delta.lengthSqr() < 1.0E-6D ? Vec3.ZERO : delta.normalize();
        Vec3 newPos = target.position().add(dir.scale(2.0D));
        this.moveTo(newPos.x, newPos.y, newPos.z, this.getYRot(), this.getXRot());

        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float damage = Math.max(4.0F, baseDamage * 1.4F);
        target.hurt(this.damageSources().mobAttack(this), damage);

        AABB impact = this.getBoundingBox().inflate(3.5D, 3.5D, 3.5D);
        for (Entity hit : serverLevel.getEntities(this, impact)) {
            if (hit == this || hit == target || !hit.isAlive()) {
                continue;
            }
            hit.hurt(this.damageSources().mobAttack(this), damage * 0.5F);
        }

        serverLevel.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 1.0D, this.getZ(),
                6, 0.2D, 0.2D, 0.2D, 0.0D);
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BATTLESHIP_NAGATO_SPAWN_EGG.get();
    }
}

