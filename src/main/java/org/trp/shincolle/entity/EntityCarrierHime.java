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

public class EntityCarrierHime extends EntityShipBase {

    public static final String EQUIP_LEFT = "equip_left";
    public static final String EQUIP_RIGHT = "equip_right";

    public EntityCarrierHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        getStateComponent().setFactionId(10);
        getStateComponent().setShipClassId(20);
        getStateComponent().setSpecialEquip(1);
        getStateComponent().setRarity(3);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeCV);
        setStateGuiBtn1(false);
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
        if (!(this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0)) {
            return;
        }

        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }

        int duration = 50 + this.getStateComponent().getAffectionLegacy();
        int amp = Math.max(0, this.getStateComponent().getAffectionLegacy() / 70);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, amp, false, false));
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_LEFT, "gui.shincolle.equip.left"));
        list.add(new EquipOption(EQUIP_RIGHT, "gui.shincolle.equip.right"));
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
        return this.getBbHeight() * 0.9D;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_HIME_SPAWN_EGG.get();
    }

    @Override
    public boolean hasShipMounts() {
        return true;
    }

    @Override
    public org.trp.shincolle.entity.base.EntityMountBase summonMountEntity() {
        return new EntityMountCaH(org.trp.shincolle.init.ModEntities.MOUNT_CA_H.get(), this.level());
    }
}

