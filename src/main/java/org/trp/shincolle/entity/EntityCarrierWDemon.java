package org.trp.shincolle.entity;

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

public class EntityCarrierWDemon extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";

    public EntityCarrierWDemon(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        getStateComponent().setFactionId(9);
        getStateComponent().setShipClassId(33);
        getStateComponent().setSpecialEquip(1);
        getStateComponent().setRarity(2);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeCV);
        setStateGuiBtn2(false);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                    this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
            if (!ships.isEmpty()) {
                int duration = 50 + this.getStateComponent().getAffectionLegacy();
                int amp = Math.max(0, this.getStateComponent().getAffectionLegacy() / 70);
                for (EntityShipBase ship : ships) {
                    if (ship == this) {
                        continue;
                    }
                    if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                        continue;
                    }
                    ship.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, duration, amp, false, false));
                }
            }
        }

        this.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 150, 0, false, false));
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        return list;
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
        return this.getBbHeight() * 1.2D;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_W_DEMON_SPAWN_EGG.get();
    }

    @Override
    public boolean hasShipMounts() {
        return true;
    }

    @Override
    public org.trp.shincolle.entity.base.EntityMountBase summonMountEntity() {
        return new EntityMountCaWD(org.trp.shincolle.init.ModEntities.MOUNT_CA_WD.get(), this.level());
    }
}

