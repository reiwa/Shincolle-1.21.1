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
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityCarrierWo extends EntityShipBase {

    public static final String EQUIP_CLOAKNECK = "equip_cloakneck";
    public static final String EQUIP_EQUIPBASE = "equip_equipbase";
    public static final String EQUIP_NECK = "equip_neck";
    public static final String EQUIP_STAFF = "equip_staff";

    public EntityCarrierWo(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 20, 0, 30});
        getStateComponent().setFactionId(5);
        getStateComponent().setShipClassId(12);
        getStateComponent().setSpecialEquip(1);
        getStateComponent().setRarity(5);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeCV);
        setStateGuiBtn1(false);
        setStateGuiBtn2(false);
        setStateLightAircraftAttack(true);
        setStateHeavyAircraftAttack(true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientEffects();
        }
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateClientEffects() {
        if ((this.tickCount % 4) == 0) {
            boolean shouldGlow = checkModelState(0, this.getStateEmotion(0))
                    && !this.isStateNoEquip()
                    && !(this.getIsSitting() && this.getStateEmotion(1) == 4);
            if (shouldGlow) {
                spawnEyeGlowParticles();
            }
        }

        if ((this.tickCount & 0xF) == 0 && checkModelState(4, this.getStateEmotion(0))
                && !this.getIsSitting() && !this.isPassenger()) {
            this.level().addParticle(ParticleTypes.CLOUD,
                    this.getX(), this.getY() + 1.2D, this.getZ(),
                    0.0D, 0.02D, 0.0D);
        }
    }

    private void spawnEyeGlowParticles() {
        float radYaw = (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD;
        float zOffset = this.getIsSitting() ? -0.15f : 0.2f;
        float[] left = rotateXZByAxis(zOffset, 0.55f, radYaw, 1.0f);
        float[] right = rotateXZByAxis(zOffset, -0.55f, radYaw, 1.0f);
        double yOffset = this.getIsSitting() ? 1.25D : 1.5D;
        this.level().addParticle(ParticleTypes.END_ROD,
                this.getX() + left[1], this.getY() + yOffset, this.getZ() + left[0],
                0.0D, 0.02D, 0.0D);
        this.level().addParticle(ParticleTypes.END_ROD,
                this.getX() + right[1], this.getY() + yOffset, this.getZ() + right[0],
                0.0D, 0.02D, 0.0D);
    }

    private void updateServerLogic() {
        if (!(this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0)) {
            return;
        }

        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }

        int duration = 30 + this.getStateComponent().getAffectionLegacy();
        int amp = Math.max(0, this.getStateComponent().getAffectionLegacy() / 80);
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

    @Override
    public boolean supportsAircraftCombat() {
        return true;
    }

    @Override
    public EntityType<? extends TamableAnimal> getAttackAircraftType(boolean isLightAircraft) {
        return isLightAircraft ? ModEntities.AIRPLANE.get() : ModEntities.TAKOYAKI.get();
    }

    @Override
    public double getAircraftLaunchHeight() {
        return this.getBbHeight() * 0.9D;
    }

    @Override
    public float getAircraftLightLevelBonus() {
        return 0.25F;
    }

    @Override
    public float getAircraftHeavyLevelBonus() {
        return 0.15F;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_WO_SPAWN_EGG.get();
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_CLOAKNECK, "gui.shincolle.equip.cloakneck"));
        list.add(new EquipOption(EQUIP_EQUIPBASE, "gui.shincolle.equip.equipbase"));
        list.add(new EquipOption(EQUIP_NECK, "gui.shincolle.equip.neck"));
        list.add(new EquipOption(EQUIP_STAFF, "gui.shincolle.equip.staff"));
        return list;
    }
}

