package org.trp.shincolle.entity;

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
import org.trp.shincolle.entity.projectile.EntityProjectileBeam;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityBattleshipYamato extends EntityShipBase {

    public static final String EQUIP_BELT = "equip_belt";
    public static final String EQUIP_HEAD_BASE = "equip_head_base";
    public static final String EQUIP_UPPER = "equip_upper";
    public static final String EQUIP_LEG = "equip_leg";

    private static final int EMOTION_ATTACK_PHASE = 5;

    public EntityBattleshipYamato(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 6);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 46);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 3);
        setStateMinor(STATE_MINOR_RARITY, 4);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setEquipFlag(EQUIP_BELT, true);
        setEquipFlag(EQUIP_HEAD_BASE, true);
        setEquipFlag(EQUIP_UPPER, true);
        setEquipFlag(EQUIP_LEG, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientParticles();
        }
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        if ((this.tickCount % 128) == 0) {
            applyBuffToNearbyAllies();
        }
    }

    public double getPassengersRidingOffset() {
        if (!this.getIsSitting()) {
            return this.getBbHeight() * 0.75f;
        }
        if (checkModelState(0, this.getStateEmotion(0))) {
            return this.getBbHeight() * 0.5f;
        }
        if (this.getStateEmotion(1) == 4) {
            return this.getBbHeight() * 0.1f;
        }
        return this.getBbHeight() * 0.4f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_BELT, "gui.shincolle.equip.belt"),
                new EquipOption(EQUIP_HEAD_BASE, "gui.shincolle.equip.head_base"),
                new EquipOption(EQUIP_UPPER, "gui.shincolle.equip.upper"),
                new EquipOption(EQUIP_LEG, "gui.shincolle.equip.leg")
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

        if (this.getStateEmotion(EMOTION_ATTACK_PHASE) > 0) {
            if (!consumeHeavyAmmo(1)) {
                return false;
            }
            float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float damage = Math.max(6.0F, baseDamage * 1.6F);
            spawnBeamEntity(serverLevel, target, damage);
            this.setStateEmotion(EMOTION_ATTACK_PHASE, 0, true);
        } else {
            this.setStateEmotion(EMOTION_ATTACK_PHASE, 1, true);
            serverLevel.sendParticles(ParticleTypes.END_ROD, this.getX(), this.getY() + 1.4D, this.getZ(),
                    6, 0.3D, 0.2D, 0.3D, 0.02D);
            this.setAttackTick(50);
            this.applyEmotesReaction(3);
            return false;
        }

        this.setAttackTick(50);
        this.applyEmotesReaction(3);
        return true;
    }

    private void updateClientParticles() {
        if (this.tickCount % 4 == 0 && checkModelState(0, this.getStateEmotion(0))
                && !this.getIsSitting() && !this.isStateNoEquip()) {
            float[] partPos = rotateXZByAxis(-0.63f, 0.0f, this.yBodyRot * Mth.DEG_TO_RAD, 1.0f);
            for (int i = 0; i < 3; i++) {
            this.level().addParticle(ParticleTypes.SMOKE,
                this.getX() + partPos[1], this.getY() + 1.65D + i * 0.1D, this.getZ() + partPos[0],
                0.0D, 0.0D, 0.0D);
            }
        }

        if (this.tickCount % 16 == 0 && this.getStateEmotion(EMOTION_ATTACK_PHASE) > 0) {
            this.level().addParticle(ParticleTypes.END_ROD,
                    this.getX(), this.getY() + 1.2D, this.getZ(),
                    0.0D, 0.02D, 0.0D);
        }
    }

    private void applyBuffToNearbyAllies() {
        if (!(this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0)) {
            return;
        }
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
            ship.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, amp, false, false));
            ship.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, amp, false, false));
        }
    }

    private void spawnBeamEntity(ServerLevel serverLevel, Entity target, float damage) {
        Vec3 start = this.position().add(0.0D, this.getBbHeight() * 0.7D, 0.0D);
        Vec3 end = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        Vec3 delta = end.subtract(start);
        EntityProjectileBeam beam = new EntityProjectileBeam(serverLevel);
        beam.initAttrs(this, 0, (float) delta.x, (float) delta.y, (float) delta.z, damage);
        serverLevel.addFreshEntity(beam);
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BATTLESHIP_YAMATO_SPAWN_EGG.get();
    }
}

