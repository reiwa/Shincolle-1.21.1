package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.menu.ShipContainerMenu;

import java.util.List;
import java.util.Objects;

public class EntityTransportWa extends EntityShipBase {

    public static final String EQUIP_BASE = "equip_base";
    public static final String EQUIP_LEG = "equip_leg";
    public static final String EQUIP_HEAD_BASE = "equip_head_base";

    public EntityTransportWa(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-3, 20, 0, 45});
        setStateMinor(STATE_MINOR_FACTION_ID, 7);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 16);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 0);
        setStateMinor(STATE_MINOR_RARITY, 3);
        setStateMinor(STATE_MINOR_GRUDGE_CONSUMPTION, org.trp.shincolle.Config.fuelConsumeAP);
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
        if (this.getStateMinor(6) <= 5400) {
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
            if (this.getStateMinor(6) > 5400 && ship.getStateMinor(6) < 2700) {
                addGrudge(-5400);
                ship.setStateMinor(6, Math.max(0, ship.getStateMinor(6) + 5400));
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
        switch (type) {
            case 0:
                if (consumeItemInInventory(ModItems.GRUDGE.get())) {
                    addGrudge(3000);
                    break;
                }
                if (consumeItemInInventory(ModItems.GRUDGE_HEAVY_BLOCK.get())) {
                    addGrudge(27000);
                }
                break;
            case 1:
                if (consumeItemInInventory(ModItems.AMMO_LIGHT.get())) {
                    addAmmoLight(300);
                    break;
                }
                if (consumeItemInInventory(ModItems.AMMO_LIGHT_CONTAINER.get())) {
                    addAmmoLight(2700);
                }
                break;
            case 2:
                if (consumeItemInInventory(ModItems.AMMO_HEAVY.get())) {
                    addAmmoHeavy(150);
                    break;
                }
                if (consumeItemInInventory(ModItems.AMMO_HEAVY_CONTAINER.get())) {
                    addAmmoHeavy(1350);
                }
                break;
            default:
        }
    }


    private void addGrudge(int amount) {
        int next = Math.max(0, this.getStateMinor(6) + amount);
        this.setStateMinor(6, next);
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
    public int getStateMinor(int index) {
        if (index == STATE_MINOR_EQUIP_DRUM) {
            return 2;
        }
        return super.getStateMinor(index);
    }

    @Override
    protected boolean hasLiquidDrumEquip() {
        if (this.isStateMarried() && this.isStateRingEffect()) {
            return true;
        }
        return super.hasLiquidDrumEquip();
    }

    @Override
    public boolean getStateFlag(int index) {
        if (index == ShipContainerMenu.STATE_FLAG_AUTO_PUMP && this.isStateMarried() && this.isStateRingEffect()) {
            return true;
        }
        return super.getStateFlag(index);
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

