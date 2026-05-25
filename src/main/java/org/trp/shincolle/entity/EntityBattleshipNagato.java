package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
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
    private int lastClientAttackTick = -1;
    private int lastClientAttackPhase = 0;

    public EntityBattleshipNagato(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 6);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 37);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 3);
        setStateMinor(STATE_MINOR_RARITY, 2);
        setStateMinor(STATE_MINOR_GRUDGE_CONSUMPTION, org.trp.shincolle.Config.fuelConsumeBB);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientParticles();
            updateClientAttackVisuals();
        }
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        if ((this.tickCount % 128) == 0) {
            addMoraleSpecialEvent();
            if (this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0) {
                applyBuffToNearbyAllies();
            }
        }

        if (!this.level().isClientSide && this.getStateEmotion(EMOTION_ATTACK_PHASE) > 0) {
            if (!this.isStateGuiBtn2() || !this.isStateHeavyAttack() || this.getAmmoHeavy() <= 0) {
                this.setStateEmotion(EMOTION_ATTACK_PHASE, 0, true);
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
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_HEAD, "gui.shincolle.equip.head"));
        list.add(new EquipOption(EQUIP_CANNON, "gui.shincolle.equip.cannon"));
        return list;
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
        this.setFuel(this.getFuel() - org.trp.shincolle.Config.fuelConsumeActionHeavy);

        int phase = this.getStateEmotion(EMOTION_ATTACK_PHASE) + 1;
        
        switch (phase) {
            case 1 -> this.playSound(org.trp.shincolle.init.ModSounds.SHIP_AP_P2.get(), 1.0F, 1.0F);
            case 3 -> this.playSound(org.trp.shincolle.init.ModSounds.SHIP_AP_ATTACK.get(), 1.0F, 1.0F);
            default -> this.playSound(org.trp.shincolle.init.ModSounds.SHIP_AP_P1.get(), 1.0F, 1.0F);
        }

        if (phase > 3) {
            this.setStateEmotion(EMOTION_ATTACK_PHASE, 0, true);
            performFinalAttack(serverLevel, target);
            this.tryFlareTarget(target);
            this.setAttackTick(50);
            this.applyEmotesReaction(3);
            return true;
        } else {
            this.setStateEmotion(EMOTION_ATTACK_PHASE, phase, true);
            spawnAttackChargeParticles(serverLevel, phase);
            this.tryFlareTarget(target);
            this.setAttackTick(50);
            this.applyEmotesReaction(3);
            return false;
        }
    }

    private void updateClientParticles() {
        if (this.tickCount % 4 == 0 && !this.getIsSitting() && this.getEquipFlag(EQUIP_CANNON) && !this.isInDeadPose()) {
            float[] partPos = rotateXZByAxis(-0.56f, 0.0f, this.yBodyRot * Mth.DEG_TO_RAD, 1.0f);
            for (int i = 0; i < 3; i++) {
                this.level().addParticle(ParticleTypes.SMOKE,
                        this.getX() + partPos[1], this.getY() + 1.5D + i * 0.1D, this.getZ() + partPos[0],
                        0.0D, 0.0D, 0.0D);
            }
        }

        if (this.tickCount % 8 == 0) {
            int atkPhase = this.getStateEmotion(EMOTION_ATTACK_PHASE);
            if (atkPhase == 1 || atkPhase == 3) {
                this.level().addParticle(org.trp.shincolle.init.ModParticles.PARTICLE_CHI.get(),
                        this.getX(), this.getY(), this.getZ(),
                        0.12D, (double) this.getId(), 1.0D);
            }
        }
    }

    private void updateClientAttackVisuals() {
        int attackTick = this.getAttackTick();
        int phase = this.getStateEmotion(EMOTION_ATTACK_PHASE);
        if (attackTick > 0 && this.lastClientAttackTick <= 0 && this.lastClientAttackPhase > 0 && phase == 0) {
            Entity target = this.getPointerTargetEntity();
            if (target == null) {
                target = this.getTarget();
            }
            double baseX = target != null ? target.getX() : this.getX();
            double baseY = (target != null ? target.getY() + target.getBbHeight() * 0.5D : this.getY() + this.getBbHeight() * 0.5D) + 2.5D;
            double baseZ = target != null ? target.getZ() : this.getZ();
            if (this.level() instanceof ClientLevel clientLevel) {
                clientLevel.addParticle(org.trp.shincolle.init.ModParticles.PARTICLE_91TYPE.get(), true,
                        baseX, baseY, baseZ,
                        0.6D, 0.0D, 0.0D);
            }
        }
        this.lastClientAttackTick = attackTick;
        this.lastClientAttackPhase = phase;
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
        if (!this.getIsSitting() && !this.isPassenger() && !this.isNoFuel() && this.isOutOfCombat() && this.getRandom().nextFloat() > 0.5f) {
            LivingEntity target = nearby.get(this.getRandom().nextInt(nearby.size()));
            this.getNavigation().moveTo(target, 1.0D);
            int particleId = LOVE_PARTICLES[this.getRandom().nextInt(LOVE_PARTICLES.length)];
            this.applyParticleEmotion(particleId);
        }
    }

    private void spawnAttackChargeParticles(ServerLevel serverLevel, int phase) {
        if (phase == 2) {
            for (int i = 0; i < 20; ++i) {
                float[] newPos1 = rotateXZByAxis(0.35F, 0.0F, 0.314F * i, 1.0F);
                serverLevel.sendParticles(org.trp.shincolle.init.ModParticles.PARTICLE_SPRAY.get(),
                        this.getX(), this.getY() + 0.3D, this.getZ(),
                        0, newPos1[0], 0.0D, newPos1[1], 1.0D);
            }
        } else {
            for (int i = 0; i < 20; ++i) {
                float[] newPos1 = rotateXZByAxis(2.0F, 0.0F, 0.314F * i, 1.0F);
                serverLevel.sendParticles(org.trp.shincolle.init.ModParticles.PARTICLE_SPRAY.get(),
                        this.getX() + newPos1[0], this.getY() + 1.0D, this.getZ() + newPos1[1],
                        0, -newPos1[0] * 0.06D, 0.0D, -newPos1[1] * 0.06D, 1.0D);
            }
        }
    }

    private void performFinalAttack(ServerLevel serverLevel, Entity target) {
        Vec3 delta = target.position().subtract(this.position());
        Vec3 dir = delta.lengthSqr() < 1.0E-6D ? Vec3.ZERO : delta.normalize();
        Vec3 newPos = target.position().add(dir.scale(2.0D));

        double originX = this.getX();
        double originY = this.getY();
        double originZ = this.getZ();

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

        double tx = target.getX();
        double ty = target.getY() + target.getBbHeight() * 0.5D;
        double tz = target.getZ();
        double dx = tx - originX;
        double dy = ty - originY;
        double dz = tz - originZ;

        serverLevel.sendParticles(org.trp.shincolle.init.ModParticles.PARTICLE_WAYPOINT_LINE_RED.get(), originX, originY, originZ, 0, dx, dy, dz, 1.0D);
        serverLevel.sendParticles(org.trp.shincolle.init.ModParticles.PARTICLE_WAYPOINT_LINE_RED.get(), originX, originY + 0.4D, originZ, 0, dx, dy, dz, 1.0D);
        serverLevel.sendParticles(org.trp.shincolle.init.ModParticles.PARTICLE_WAYPOINT_LINE_RED.get(), originX, originY + 0.8D, originZ, 0, dx, dy, dz, 1.0D);

        for (int i = 0; i < 20; ++i) {
            float[] newPos1 = rotateXZByAxis(1.0F, 0.0F, 0.314F * i, 1.0F);
            serverLevel.sendParticles(org.trp.shincolle.init.ModParticles.PARTICLE_SPRAY_RED.get(),
                    tx, ty + 0.3D, tz,
                    0, newPos1[0] * 0.35D, 0.0D, newPos1[1] * 0.35D, 1.0D);
        }

        serverLevel.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 1.0D, this.getZ(),
                6, 0.2D, 0.2D, 0.2D, 0.0D);
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BATTLESHIP_NAGATO_SPAWN_EGG.get();
    }
}

