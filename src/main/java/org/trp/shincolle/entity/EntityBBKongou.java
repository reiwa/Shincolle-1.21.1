package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityBBKongou extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_HEAD_BASE = "equip_head_base";
    public static final String EQUIP_HAIR_SET = "equip_hair_set";
    public static final String EQUIP_AHOKE = "equip_ahoke";

    public EntityBBKongou(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        getStateComponent().setFactionId(6);
        getStateComponent().setShipClassId(60);
        getStateComponent().setSpecialEquip(3);
        getStateComponent().setRarity(4);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeBB);
        setStateGuiBtn4(false);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientParticles();
        }
    }

    @Override
    protected void calcShipAttributesAddEffect() {
        super.calcShipAttributesAddEffect();
        this.attackEffectMap.put(MobEffects.WEAKNESS, new int[] {this.getLevel() / 75, 60 + this.getLevel() * 2, 15 + this.getLevel() / 10});
    }

    public double getPassengersRidingOffset() {
        if (!this.getIsSitting()) {
            return this.getBbHeight() * 0.75f;
        }
        if (checkModelState(1, this.getStateEmotion(0))) {
            return this.getBbHeight() * 0.42f;
        }
        if (this.getStateEmotion(1) == 4) {
            return 0.0;
        }
        return this.getBbHeight() * 0.35f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        list.add(new EquipOption(EQUIP_HEAD_BASE, "gui.shincolle.equip.head_base"));
        list.add(new EquipOption(EQUIP_HAIR_SET, "gui.shincolle.equip.hair"));
        list.add(new EquipOption(EQUIP_AHOKE, "gui.shincolle.equip.ahoke"));
        return list;
    }

    private void updateClientParticles() {
        if (this.tickCount % 4 == 0 && !this.getIsSitting() && this.getEquipFlag(EQUIP_RIGGING) && !this.isInDeadPose()) {
            float[] partPos = rotateXZByAxis(-0.7f, 0.0f, this.yBodyRot * Mth.DEG_TO_RAD, 1.0f);
            for (int i = 0; i < 3; i++) {
                this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX() + partPos[1], this.getY() + 1.17D + i * 0.1D, this.getZ() + partPos[0],
                    0.0D, 0.0D, 0.0D);
            }
            partPos = rotateXZByAxis(-0.45f, 0.0f, this.yBodyRot * Mth.DEG_TO_RAD, 1.0f);
            for (int i = 0; i < 3; i++) {
                this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX() + partPos[1], this.getY() + 1.32D + i * 0.1D, this.getZ() + partPos[0],
                    0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void applyAuraEffects() {
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }
        int duration = 140 + this.getStateComponent().getAffectionLegacy();
        int amp = Math.max(0, this.getStateComponent().getAffectionLegacy() / 120);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, duration, amp, false, false));
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BB_KONGOU_SPAWN_EGG.get();
    }
}

