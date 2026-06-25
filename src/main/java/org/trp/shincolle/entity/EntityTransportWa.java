package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.LegacyShipStats;
import org.trp.shincolle.entity.base.ShipStateComponent;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityTransportWa extends EntityShipBase {

    public static final String EQUIP_BASE = "equip_base";
    public static final String EQUIP_LEG = "equip_leg";
    public static final String EQUIP_HEAD_BASE = "equip_head_base";

    public EntityTransportWa(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-3, 20, 0, 45});
        getStateComponent().setFactionId(7);
        getStateComponent().setShipClassId(16);
        getStateComponent().setSpecialEquip(0);
        getStateComponent().setRarity(3);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeAP);
        setStateGuiBtn1(false);
        setStateGuiBtn2(false);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
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

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_BASE, "gui.shincolle.equip.base"));
        list.add(new EquipOption(EQUIP_LEG, "gui.shincolle.equip.leg"));
        list.add(new EquipOption(EQUIP_HEAD_BASE, "gui.shincolle.equip.head_base"));
        return list;
    }

    private void updateClientEffects() {
        if ((this.tickCount % 128) == 0 && this.getRandom().nextInt(4) == 0) {
            this.applyParticleEmotion(2);
        }
    }

    private void updateServerLogic() {
        if (this.getStateComponent().getFuel() <= 5400) {
            consumeSupplyItems(0);
        }
        if (this.getAmmoLight() <= 540) {
            consumeSupplyItems(1);
        }
        if (this.getAmmoHeavy() <= 270) {
            consumeSupplyItems(2);
        }

        if ((this.tickCount % 256) == 0 && !this.isStateNoEquip()) {
            trySupplyAllies();
        }
    }

    private void trySupplyAllies() {
        int supCount = this.getLevel() / 50 + 1;
        double range = 2.0D + this.getAttributeValue(Attributes.FOLLOW_RANGE) * 0.5D;
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                 this.getBoundingBox().inflate(range, range, range));
        if (ships.isEmpty()) {
            return;
        }

        for (EntityShipBase ship : ships) {
            if (supCount <= 0) {
                break;
            }
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }

            boolean supplied = false;
            if (this.getStateComponent().getFuel() > 5400 && ship.getStateComponent().getFuel() < 2700) {
                addGrudge(-5400);
                ship.getStateComponent().setFuel(Math.max(0, ship.getStateComponent().getFuel() + 5400));
                supplied = true;
            }
            if (this.getAmmoLight() >= 540 && ship.getAmmoLight() < 270) {
                addAmmoLight(-540);
                ship.setAmmoLight(Math.max(0, ship.getAmmoLight() + 540));
                supplied = true;
            }
            if (this.getAmmoHeavy() >= 270 && ship.getAmmoHeavy() < 135) {
                addAmmoHeavy(-270);
                ship.setAmmoHeavy(Math.max(0, ship.getAmmoHeavy() + 270));
                supplied = true;
            }

            if (supplied) {
                spawnSupplyParticles(ship);
                supCount--;
            }
        }
    }

    private void spawnSupplyParticles(EntityShipBase target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        double midX = (this.getX() + target.getX()) * 0.5D;
        double midY = (this.getY() + target.getY()) * 0.5D + 0.6D;
        double midZ = (this.getZ() + target.getZ()) * 0.5D;
        serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, midX, midY, midZ,
                6, 0.3D, 0.2D, 0.3D, 0.01D);
    }

    private void consumeSupplyItems(int type) {
        int level = org.trp.shincolle.Config.consumptionLevel;
        float modFuel = this.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_FUEL_CONSUMPTION);
        float modAmmo = this.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
        int multiplier = level == 0 ? 10 : 1;

        switch (type) {
            case 0:
                if (consumeItemInInventory(ModItems.GRUDGE.get())) {
                    addGrudge((int) (300 * modFuel * multiplier));
                    break;
                }
                if (consumeItemInInventory(ModItems.GRUDGE_HEAVY_BLOCK.get())) {
                    addGrudge((int) (2700 * modFuel * multiplier));
                }
                break;
            case 1:
                if (consumeItemInInventory(ModItems.AMMO_LIGHT.get())) {
                    addAmmoLight((int) (30 * modAmmo * multiplier));
                    break;
                }
                if (consumeItemInInventory(ModItems.AMMO_LIGHT_CONTAINER.get())) {
                    addAmmoLight((int) (270 * modAmmo * multiplier));
                }
                break;
            case 2:
                if (consumeItemInInventory(ModItems.AMMO_HEAVY.get())) {
                    addAmmoHeavy((int) (15 * modAmmo * multiplier));
                    break;
                }
                if (consumeItemInInventory(ModItems.AMMO_HEAVY_CONTAINER.get())) {
                    addAmmoHeavy((int) (135 * modAmmo * multiplier));
                }
                break;
            default:
        }
    }


    private void addGrudge(int amount) {
        int next = Math.max(0, this.getStateComponent().getFuel() + amount);
        this.getStateComponent().setFuel(next);
    }

    private void addAmmoLight(int amount) {
        int next = Math.max(0, this.getAmmoLight() + amount);
        this.setAmmoLight(next);
    }

    private void addAmmoHeavy(int amount) {
        int next = Math.max(0, this.getAmmoHeavy() + amount);
        this.setAmmoHeavy(next);
    }

    @Override
    protected ShipStateComponent createStateComponent() {
        return new ShipStateComponent(this) {
            @Override
            public int getEquipDrum() {
                return 2;
            }

            @Override
            public boolean isStateAutoPump() {
                if (this.isStateMarried() && this.isStateRingEffect()) {
                    return true;
                }
                return super.isStateAutoPump();
            }
        };
    }



    @Override
    protected boolean hasLiquidDrumEquip() {
        if (this.isStateMarried() && this.isStateRingEffect()) {
            return true;
        }
        return super.hasLiquidDrumEquip();
    }

    @Override
    public boolean isStateAutoPump() {
        if (this.isStateMarried() && this.isStateRingEffect()) {
            return true;
        }
        return super.isStateAutoPump();
    }



    @Override
    public boolean supportsItemPickup() {
        return true;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.TRANSPORT_WA_SPAWN_EGG.get();
    }
}

