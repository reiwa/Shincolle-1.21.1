package org.trp.shincolle.menu;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.item.ShipTankItem;

import java.util.List;
import java.util.Optional;

public class ShipContainerMenu extends AbstractContainerMenu {
    public static final int EQUIP_BUTTON_BASE = 100;
    public static final int PAGE_BUTTON_0 = 15;
    public static final int PAGE_BUTTON_1 = 16;
    public static final int PAGE_BUTTON_2 = 17;
    public static final int TOGGLE_BUTTON_CAN_MELEE = 30;
    public static final int TOGGLE_BUTTON_LIGHT_ATTACK = 31;
    public static final int TOGGLE_BUTTON_HEAVY_ATTACK = 32;
    public static final int TOGGLE_BUTTON_LIGHT_AIRCRAFT = 33;
    public static final int TOGGLE_BUTTON_HEAVY_AIRCRAFT = 34;
    public static final int TOGGLE_BUTTON_RING_EFFECT = 35;
    public static final int TOGGLE_BUTTON_PASSIVE_ATTACK = 50;
    public static final int TOGGLE_BUTTON_ON_SIGHT = 51;
    public static final int TOGGLE_BUTTON_PVP = 52;
    public static final int TOGGLE_BUTTON_ANTI_AIR = 53;
    public static final int TOGGLE_BUTTON_ANTI_SUB = 54;
    public static final int TOGGLE_BUTTON_TIMEKEEP = 55;
    public static final int TOGGLE_BUTTON_PICK_ITEM = 60;
    public static final int TOGGLE_BUTTON_AUTO_PUMP = 61;
    public static final int TOGGLE_BUTTON_MOUNT = 70;
    public static final int TOGGLE_BUTTON_SHOW_HELD = 71;

    public static final int SLIDER_FOLLOW_MIN_BASE = 400;
    public static final int SLIDER_FOLLOW_MAX_BASE = 500;
    public static final int SLIDER_FLEE_HP_BASE = 700;
    public static final int SLIDER_WPSTAY_BASE = 800;
    public static final int SLIDER_RATION_MORALE_BASE = 900;
    public static final int ACTION_TASK_SELECT_BASE = 1000;
    public static final int ACTION_TASK_META_TOGGLE = 1010;
    public static final int ACTION_TASK_ORE_TOGGLE = 1011;
    public static final int ACTION_TASK_NBT_TOGGLE = 1012;
    public static final int ACTION_SIDE_TOGGLE_BASE = 1100;

    public static final int STATE_FLAG_CAN_MELEE = 3;
    public static final int STATE_FLAG_LIGHT_ATTACK = 4;
    public static final int STATE_FLAG_HEAVY_ATTACK = 5;
    public static final int STATE_FLAG_LIGHT_AIRCRAFT_ATTACK = 6;
    public static final int STATE_FLAG_HEAVY_AIRCRAFT_ATTACK = 7;
    public static final int STATE_FLAG_RING_EFFECT = 9;
    public static final int STATE_FLAG_ON_SIGHT = 12;
    public static final int STATE_FLAG_PVP = 18;
    public static final int STATE_FLAG_ANTI_AIR = 19;
    public static final int STATE_FLAG_ANTI_SUB = 20;
    public static final int STATE_FLAG_PASSIVE_ATTACK = 21;
    public static final int STATE_FLAG_TIMEKEEP = 22;
    public static final int STATE_FLAG_PICK_ITEM = 23;
    private static final int STATE_FLAG_APPEARANCE = 25;
    public static final int STATE_FLAG_AUTO_PUMP = 26;

    public static final int STATE_MINOR_RATION_MORALE = EntityShipBase.STATE_MINOR_RATION_MORALE;
    public static final int STATE_MINOR_FOLLOW_MIN = EntityShipBase.STATE_MINOR_FOLLOW_MIN;
    public static final int STATE_MINOR_FOLLOW_MAX = EntityShipBase.STATE_MINOR_FOLLOW_MAX;
    public static final int STATE_MINOR_FLEE_HP = EntityShipBase.STATE_MINOR_FLEE_HP;
    public static final int STATE_MINOR_TASK_ID = EntityShipBase.STATE_MINOR_TASK_ID;
    public static final int STATE_MINOR_TASK_SIDE = EntityShipBase.STATE_MINOR_TASK_SIDE;
    public static final int STATE_MINOR_GUARD_X = EntityShipBase.STATE_MINOR_GUARD_X;
    public static final int STATE_MINOR_GUARD_Y = EntityShipBase.STATE_MINOR_GUARD_Y;
    public static final int STATE_MINOR_GUARD_Z = EntityShipBase.STATE_MINOR_GUARD_Z;
    public static final int STATE_MINOR_GUARD_DIM = EntityShipBase.STATE_MINOR_GUARD_DIM;
    public static final int STATE_MINOR_GUARD_TYPE = EntityShipBase.STATE_MINOR_GUARD_TYPE;
    private static final int STATE_MINOR_EQUIP_DRUM = EntityShipBase.STATE_MINOR_EQUIP_DRUM;

    private static final int FOLLOW_MIN_MIN = 1;
    private static final int FOLLOW_MIN_MAX = 31;
    private static final int FOLLOW_MAX_MIN = 2;
    private static final int FOLLOW_MAX_MAX = 32;
    private static final int FLEE_HP_MIN = 0;
    private static final int FLEE_HP_MAX = 100;
    private static final int RATION_MORALE_MIN = 1;
    private static final int RATION_MORALE_MAX = 4;

    private static final int EQUIP_SLOTS = 6;
    private static final int PAGE_SLOTS = 18;
    private static final int SHIP_STORAGE_SIZE = 60;
    private static final int SHIP_PAGE_COUNT = 3;

    public static final int SHIP_INV_X = 8;
    public static final int SHIP_INV_Y = 18;
    public static final int EQUIP_INV_X = 144;
    public static final int EQUIP_INV_Y = 18;
    public static final int PLAYER_INV_X = 8;
    public static final int PLAYER_INV_Y = 132;
    public static final int HOTBAR_Y = 190;

    private static final int VISIBLE_SHIP_SLOTS = EQUIP_SLOTS + PAGE_SLOTS;
    private static final int PLAYER_INV_SLOTS = 27;
    private static final int PLAYER_HOTBAR_SLOTS = 9;
    private static final int FIRST_PLAYER_SLOT = VISIBLE_SHIP_SLOTS;
    private static final int END_PLAYER_SLOT = FIRST_PLAYER_SLOT + PLAYER_INV_SLOTS + PLAYER_HOTBAR_SLOTS;

    private final EntityShipBase ship;
    private final DataSlot pageData = new DataSlot() {
        @Override
        public int get() {
            inventoryPage = clampPage(inventoryPage);
            return inventoryPage;
        }

        @Override
        public void set(int value) {
            inventoryPage = clampPage(value);
        }
    };
    private final DataSlot unlockedStoragePagesData = new DataSlot() {
        @Override
        public int get() {
            return getUnlockedStoragePagesServer();
        }

        @Override
        public void set(int value) {
            unlockedStoragePagesSynced = Mth.clamp(value, 0, SHIP_PAGE_COUNT - 1);
            inventoryPage = clampPage(inventoryPage);
        }
    };
    private final DataSlot canMeleeData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateCanMelee() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            canMeleeSynced = value != 0;
        }
    };
    private final DataSlot lightAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateLightAttack() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            lightAttackSynced = value != 0;
        }
    };
    private final DataSlot heavyAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateHeavyAttack() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            heavyAttackSynced = value != 0;
        }
    };
    private final DataSlot lightAircraftAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateLightAircraftAttack() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            lightAircraftAttackSynced = value != 0;
        }
    };
    private final DataSlot heavyAircraftAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateHeavyAircraftAttack() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            heavyAircraftAttackSynced = value != 0;
        }
    };
    private final DataSlot ringEffectData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateRingEffect() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            ringEffectSynced = value != 0;
        }
    };
    private final DataSlot marriedData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateMarried() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            marriedSynced = value != 0;
        }
    };
    private final DataSlot followMinData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().getFollowMin();
        }

        @Override
        public void set(int value) {
            followMinSynced = clampFollowMin(value);
        }
    };
    private final DataSlot followMaxData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().getFollowMax();
        }

        @Override
        public void set(int value) {
            followMaxSynced = clampFollowMax(value, followMinSynced);
        }
    };
    private final DataSlot fleeHpData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().getFleeHp();
        }

        @Override
        public void set(int value) {
            fleeHpSynced = clampFleeHp(value);
        }
    };
    private final DataSlot passiveAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStatePassiveAttack() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            passiveAttackSynced = value != 0;
        }
    };
    private final DataSlot onSightData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStateOnSight() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            onSightSynced = value != 0;
        }
    };
    private final DataSlot pvpData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStatePvp() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            pvpSynced = value != 0;
        }
    };
    private final DataSlot antiAirData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStateAntiAir() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            antiAirSynced = value != 0;
        }
    };
    private final DataSlot antiSubData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStateAntiSub() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            antiSubSynced = value != 0;
        }
    };
    private final DataSlot timeKeepingData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStateTimekeep() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            timeKeepingSynced = value != 0;
        }
    };
    private final DataSlot pickItemData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStatePickItem() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            pickItemSynced = value != 0;
        }
    };
    private final DataSlot autoPumpData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().isStateAutoPump() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            autoPumpSynced = value != 0;
        }
    };
    private final DataSlot appearanceData = new DataSlot() {
        @Override
        public int get() {
            return ship.isStateAppearance() ? 1 : 0;
        }

        @Override
        public void set(int value) {
            appearanceSynced = value != 0;
        }
    };
    private final DataSlot mountData = new DataSlot() {
        @Override
        public int get() {
            return (ship.getStateEmotion(0) & 1);
        }

        @Override
        public void set(int value) {
            mountSynced = value != 0;
        }
    };
    private final DataSlot rationMoraleData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().getRationMorale();
        }

        @Override
        public void set(int value) {
            rationMoraleSynced = clampRationMorale(value);
        }
    };
    private final DataSlot taskIdData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().getTaskId();
        }

        @Override
        public void set(int value) {
            taskIdSynced = value;
        }
    };
    private final DataSlot taskSideData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateComponent().getTaskSide();
        }

        @Override
        public void set(int value) {
            taskSideSynced = value;
        }
    };
    private final DataSlot shipTankFluidAmountLowData = new DataSlot() {
        @Override
        public int get() {
            refreshShipTankFluidSyncValues();
            return shipTankFluidAmountSynced & 0xFFFF;
        }

        @Override
        public void set(int value) {
            shipTankFluidAmountSynced = (shipTankFluidAmountSynced & 0xFFFF0000) | (value & 0xFFFF);
        }
    };
    private final DataSlot shipTankFluidAmountHighData = new DataSlot() {
        @Override
        public int get() {
            refreshShipTankFluidSyncValues();
            return (shipTankFluidAmountSynced >>> 16) & 0xFFFF;
        }

        @Override
        public void set(int value) {
            shipTankFluidAmountSynced = (shipTankFluidAmountSynced & 0xFFFF) | ((value & 0xFFFF) << 16);
        }
    };
    private final DataSlot shipTankFluidCapacityLowData = new DataSlot() {
        @Override
        public int get() {
            refreshShipTankFluidSyncValues();
            return shipTankFluidCapacitySynced & 0xFFFF;
        }

        @Override
        public void set(int value) {
            shipTankFluidCapacitySynced = (shipTankFluidCapacitySynced & 0xFFFF0000) | (value & 0xFFFF);
        }
    };
    private final DataSlot shipTankFluidCapacityHighData = new DataSlot() {
        @Override
        public int get() {
            refreshShipTankFluidSyncValues();
            return (shipTankFluidCapacitySynced >>> 16) & 0xFFFF;
        }

        @Override
        public void set(int value) {
            shipTankFluidCapacitySynced = (shipTankFluidCapacitySynced & 0xFFFF) | ((value & 0xFFFF) << 16);
        }
    };
    private final DataSlot bonusHpData = new DataSlot() {
        @Override
        public int get() {
            return ship.getAttrBonus(0);
        }

        @Override
        public void set(int value) {
            ship.setAttrBonus(0, value);
        }
    };
    private final DataSlot bonusAtkData = new DataSlot() {
        @Override
        public int get() {
            return ship.getAttrBonus(1);
        }

        @Override
        public void set(int value) {
            ship.setAttrBonus(1, value);
        }
    };
    private final DataSlot bonusDefData = new DataSlot() {
        @Override
        public int get() {
            return ship.getAttrBonus(2);
        }

        @Override
        public void set(int value) {
            ship.setAttrBonus(2, value);
        }
    };
    private final DataSlot bonusSpdData = new DataSlot() {
        @Override
        public int get() {
            return ship.getAttrBonus(3);
        }

        @Override
        public void set(int value) {
            ship.setAttrBonus(3, value);
        }
    };
    private final DataSlot bonusMovData = new DataSlot() {
        @Override
        public int get() {
            return ship.getAttrBonus(4);
        }

        @Override
        public void set(int value) {
            ship.setAttrBonus(4, value);
        }
    };
    private final DataSlot bonusHitData = new DataSlot() {
        @Override
        public int get() {
            return ship.getAttrBonus(5);
        }

        @Override
        public void set(int value) {
            ship.setAttrBonus(5, value);
        }
    };
    private int inventoryPage = 0;
    private int unlockedStoragePagesSynced;
    private boolean canMeleeSynced;
    private boolean lightAttackSynced;
    private boolean heavyAttackSynced;
    private boolean lightAircraftAttackSynced;
    private boolean heavyAircraftAttackSynced;
    private boolean ringEffectSynced;
    private boolean marriedSynced;
    private int followMinSynced;
    private int followMaxSynced;
    private int fleeHpSynced;
    private boolean passiveAttackSynced;
    private boolean onSightSynced;
    private boolean pvpSynced;
    private boolean antiAirSynced;
    private boolean antiSubSynced;
    private boolean timeKeepingSynced;
    private boolean pickItemSynced;
    private boolean autoPumpSynced;
    private boolean appearanceSynced;
    private boolean mountSynced;
    private int rationMoraleSynced;
    private int taskIdSynced;
    private int taskSideSynced;
    private int shipTankFluidAmountSynced;
    private int shipTankFluidCapacitySynced;

    public ShipContainerMenu(int containerId, Inventory playerInv, RegistryFriendlyByteBuf buf) {
        this(containerId, playerInv, getEntity(playerInv, buf));
    }

    public ShipContainerMenu(int containerId, Inventory playerInv, EntityShipBase ship) {
        super(ModMenus.SHIP_MENU.get(), containerId);
        this.ship = ship;

        if (!ship.level().isClientSide && playerInv.player != null) {
            int classID = ship.getStateComponent().getShipClassId();
            if (classID >= 0) {
                java.util.HashSet<Integer> collected = playerInv.player.getData(org.trp.shincolle.init.ModDataAttachments.COLLECTED_SHIPS);
                if (collected.add(classID)) {
                    if (playerInv.player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, new org.trp.shincolle.network.S2CCollectedShipsSyncPayload(new java.util.ArrayList<>(collected)));
                    }
                }
            }
        }

        this.unlockedStoragePagesSynced = Mth.clamp(ship.getStateComponent().getEquipDrum(), 0, SHIP_PAGE_COUNT - 1);
        this.canMeleeSynced = ship.isStateCanMelee();
        this.lightAttackSynced = ship.isStateLightAttack();
        this.heavyAttackSynced = ship.isStateHeavyAttack();
        this.lightAircraftAttackSynced = ship.isStateLightAircraftAttack();
        this.heavyAircraftAttackSynced = ship.isStateHeavyAircraftAttack();
        this.ringEffectSynced = ship.isStateRingEffect();
        this.marriedSynced = ship.isStateMarried();
        this.followMinSynced = clampFollowMin(ship.getStateComponent().getFollowMin());
        this.followMaxSynced = clampFollowMax(ship.getStateComponent().getFollowMax(), this.followMinSynced);
        this.fleeHpSynced = clampFleeHp(ship.getStateComponent().getFleeHp());
        this.passiveAttackSynced = ship.getStateComponent().isStatePassiveAttack();
        this.onSightSynced = ship.getStateComponent().isStateOnSight();
        this.pvpSynced = ship.getStateComponent().isStatePvp();
        this.antiAirSynced = ship.getStateComponent().isStateAntiAir();
        this.antiSubSynced = ship.getStateComponent().isStateAntiSub();
        this.timeKeepingSynced = ship.getStateComponent().isStateTimekeep();
        this.pickItemSynced = ship.getStateComponent().isStatePickItem();
        this.autoPumpSynced = ship.getStateComponent().isStateAutoPump();
        this.appearanceSynced = ship.isStateAppearance();
        this.mountSynced = (ship.getStateEmotion(0) & 1) != 0;
        this.rationMoraleSynced = clampRationMorale(ship.getStateComponent().getRationMorale());
        this.taskIdSynced = ship.getStateComponent().getTaskId();
        this.taskSideSynced = ship.getStateComponent().getTaskSide();
        refreshShipTankFluidSyncValues();

        ship.getStateComponent().setFollowMin(this.followMinSynced);
        ship.getStateComponent().setFollowMax(this.followMaxSynced);
        ship.getStateComponent().setFleeHp(this.fleeHpSynced);
        ship.getStateComponent().setRationMorale(this.rationMoraleSynced);

        this.addDataSlot(pageData);
        this.addDataSlot(unlockedStoragePagesData);
        this.addDataSlot(canMeleeData);
        this.addDataSlot(lightAttackData);
        this.addDataSlot(heavyAttackData);
        this.addDataSlot(lightAircraftAttackData);
        this.addDataSlot(heavyAircraftAttackData);
        this.addDataSlot(ringEffectData);
        this.addDataSlot(marriedData);
        this.addDataSlot(followMinData);
        this.addDataSlot(followMaxData);
        this.addDataSlot(fleeHpData);
        this.addDataSlot(passiveAttackData);
        this.addDataSlot(onSightData);
        this.addDataSlot(pvpData);
        this.addDataSlot(antiAirData);
        this.addDataSlot(antiSubData);
        this.addDataSlot(timeKeepingData);
        this.addDataSlot(pickItemData);
        this.addDataSlot(autoPumpData);
        this.addDataSlot(appearanceData);
        this.addDataSlot(mountData);
        this.addDataSlot(rationMoraleData);
        this.addDataSlot(taskIdData);
        this.addDataSlot(taskSideData);
        this.addDataSlot(shipTankFluidAmountLowData);
        this.addDataSlot(shipTankFluidAmountHighData);
        this.addDataSlot(shipTankFluidCapacityLowData);
        this.addDataSlot(shipTankFluidCapacityHighData);
        this.addDataSlot(bonusHpData);
        this.addDataSlot(bonusAtkData);
        this.addDataSlot(bonusDefData);
        this.addDataSlot(bonusSpdData);
        this.addDataSlot(bonusMovData);
        this.addDataSlot(bonusHitData);

        for (int i = 0; i < EQUIP_SLOTS; i++) {
            this.addSlot(new EquipSlot(i, EQUIP_INV_X, EQUIP_INV_Y + i * 18));
        }

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 3; col++) {
                int localIndex = row * 3 + col;
                this.addSlot(new PagedShipSlot(localIndex, SHIP_INV_X + col * 18, SHIP_INV_Y + row * 18));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = PLAYER_INV_X + col * 18;
                int y = PLAYER_INV_Y + row * 18;
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, x, y));
            }
        }

        for (int col = 0; col < 9; col++) {
            int x = PLAYER_INV_X + col * 18;
            this.addSlot(new Slot(playerInv, col, x, HOTBAR_Y));
        }
    }

    public int getShipLevel() {
        return ship.getLevel();
    }

    public int getShipExp() {
        return ship.getExp();
    }

    public int getShipFuel() {
        return ship.getFuel();
    }

    public float getShipFirepower() {
        return ship.getLegacyShipStats().getFirepower();
    }

    public float getShipArmor() {
        return ship.getLegacyShipStats().getArmor();
    }

    public float getShipReloadSpeed() {
        return ship.getLegacyShipStats().getReloadSpeed();
    }

    public float getShipMoveSpeed() {
        return ship.getLegacyShipStats().getMoveSpeed();
    }

    public float getShipRange() {
        return ship.getLegacyShipStats().getAttackRange();
    }

    public float getShipHealth() {
        return ship.getHealth();
    }

    public float getShipMaxHealth() {
        return ship.getMaxHealth();
    }

    public int getAircraftLight() {
        return ship.getNumAircraftLight();
    }

    public int getAircraftHeavy() {
        return ship.getNumAircraftHeavy();
    }

    public EntityShipBase getShip() {
        return ship;
    }

    public int getInventoryPage() {
        this.inventoryPage = clampPage(this.inventoryPage);
        return inventoryPage;
    }

    public int getUnlockedStoragePages() {
        return Mth.clamp(this.unlockedStoragePagesSynced, 0, SHIP_PAGE_COUNT - 1);
    }

    public int getUnlockedInventoryPageCount() {
        return 1 + getUnlockedStoragePages();
    }

    public boolean isCanMeleeEnabled() {
        return canMeleeSynced;
    }

    public boolean isLightAttackEnabled() {
        return lightAttackSynced;
    }

    public boolean isHeavyAttackEnabled() {
        return heavyAttackSynced;
    }

    public boolean isLightAircraftAttackEnabled() {
        return lightAircraftAttackSynced;
    }

    public boolean isHeavyAircraftAttackEnabled() {
        return heavyAircraftAttackSynced;
    }

    public boolean isRingEffectEnabled() {
        return ringEffectSynced;
    }

    public boolean isMarriedEnabled() {
        return marriedSynced;
    }

    public int getFollowMinDistance() {
        return followMinSynced;
    }

    public int getFollowMaxDistance() {
        return followMaxSynced;
    }

    public int getFleeHpPercent() {
        return fleeHpSynced;
    }

    public boolean isPassiveAttackEnabled() {
        return passiveAttackSynced;
    }

    public boolean isOnSightEnabled() {
        return onSightSynced;
    }

    public boolean isPvpEnabled() {
        return pvpSynced;
    }

    public boolean isAntiAirEnabled() {
        return antiAirSynced;
    }

    public boolean isAntiSubEnabled() {
        return antiSubSynced;
    }

    public boolean isTimeKeepingEnabled() {
        return timeKeepingSynced;
    }

    public boolean isPickItemEnabled() {
        return pickItemSynced;
    }

    public boolean isAutoPumpEnabled() {
        return autoPumpSynced;
    }

    public boolean isAppearanceEnabled() {
        return appearanceSynced;
    }

    public boolean isMountEnabled() {
        return mountSynced;
    }

    public int getRationMoraleThreshold() {
        return rationMoraleSynced;
    }

    public int getTaskId() {
        return taskIdSynced;
    }

    public int getTaskSideFlags() {
        return taskSideSynced;
    }

    public int getShipTankFluidAmount() {
        return Math.max(0, shipTankFluidAmountSynced);
    }

    public int getShipTankFluidCapacity() {
        return Math.max(0, shipTankFluidCapacitySynced);
    }

    public List<EntityShipBase.EquipOption> getEquipOptions() {
        return ship.getEquipOptions();
    }

    public int getEquipOptionCount() {
        return ship.getEquipOptions().size();
    }

    public Component getEquipOptionLabel(int index) {
        if (index < 0 || index >= ship.getEquipOptions().size()) {
            return Component.empty();
        }
        return Component.translatable(ship.getEquipOptions().get(index).labelKey());
    }

    public boolean isEquipOptionEnabled(int index) {
        if (index < 0 || index >= ship.getEquipOptions().size()) {
            return false;
        }
        return ship.getEquipFlag(ship.getEquipOptions().get(index).key());
    }

    public int getEquipOptionButtonId(int index) {
        return EQUIP_BUTTON_BASE + index;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copied = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copied = stack.copy();

            if (index < VISIBLE_SHIP_SLOTS) {
                if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, END_PLAYER_SLOT, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stack, 0, EQUIP_SLOTS, false)
                        && !this.moveItemStackTo(stack, EQUIP_SLOTS, VISIBLE_SHIP_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return copied;
    }

    @Override
    public boolean stillValid(Player player) {
        return ship.isAlive() && player.distanceToSqr(ship) < 64.0D;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        switch (id) {
            case PAGE_BUTTON_0 -> {
                this.inventoryPage = 0;
                this.broadcastFullState();
                return true;
            }
            case PAGE_BUTTON_1 -> {
                if (!isPageUnlocked(1)) {
                    return true;
                }
                this.inventoryPage = 1;
                this.broadcastFullState();
                return true;
            }
            case PAGE_BUTTON_2 -> {
                if (!isPageUnlocked(2)) {
                    return true;
                }
                this.inventoryPage = 2;
                this.broadcastFullState();
                return true;
            }
            default -> {
            }
        }

        switch (id) {
            case TOGGLE_BUTTON_CAN_MELEE -> {
                ship.setStateCanMelee(!ship.isStateCanMelee());
                canMeleeSynced = ship.isStateCanMelee();
                return true;
            }
            case TOGGLE_BUTTON_LIGHT_ATTACK -> {
                if (ship.isStateGuiBtn1()) {
                    ship.setStateLightAttack(!ship.isStateLightAttack());
                    lightAttackSynced = ship.isStateLightAttack();
                }
                return true;
            }
            case TOGGLE_BUTTON_HEAVY_ATTACK -> {
                if (ship.isStateGuiBtn2()) {
                    ship.setStateHeavyAttack(!ship.isStateHeavyAttack());
                    heavyAttackSynced = ship.isStateHeavyAttack();
                }
                return true;
            }
            case TOGGLE_BUTTON_LIGHT_AIRCRAFT -> {
                if (ship.isStateGuiBtn3()) {
                    ship.setStateLightAircraftAttack(!ship.isStateLightAircraftAttack());
                    lightAircraftAttackSynced = ship.isStateLightAircraftAttack();
                }
                return true;
            }
            case TOGGLE_BUTTON_HEAVY_AIRCRAFT -> {
                if (ship.isStateGuiBtn4()) {
                    ship.setStateHeavyAircraftAttack(!ship.isStateHeavyAircraftAttack());
                    heavyAircraftAttackSynced = ship.isStateHeavyAircraftAttack();
                }
                return true;
            }
            case TOGGLE_BUTTON_RING_EFFECT -> {
                ship.setStateRingEffect(!ship.isStateRingEffect());
                ringEffectSynced = ship.isStateRingEffect();
                return true;
            }
            case TOGGLE_BUTTON_PASSIVE_ATTACK -> {
                ship.getStateComponent().setStatePassiveAttack(!ship.getStateComponent().isStatePassiveAttack());
                passiveAttackSynced = ship.getStateComponent().isStatePassiveAttack();
                return true;
            }
            case TOGGLE_BUTTON_ON_SIGHT -> {
                ship.getStateComponent().setStateOnSight(!ship.getStateComponent().isStateOnSight());
                onSightSynced = ship.getStateComponent().isStateOnSight();
                return true;
            }
            case TOGGLE_BUTTON_PVP -> {
                ship.getStateComponent().setStatePvp(!ship.getStateComponent().isStatePvp());
                pvpSynced = ship.getStateComponent().isStatePvp();
                return true;
            }
            case TOGGLE_BUTTON_ANTI_AIR -> {
                ship.getStateComponent().setStateAntiAir(!ship.getStateComponent().isStateAntiAir());
                antiAirSynced = ship.getStateComponent().isStateAntiAir();
                return true;
            }
            case TOGGLE_BUTTON_ANTI_SUB -> {
                ship.getStateComponent().setStateAntiSub(!ship.getStateComponent().isStateAntiSub());
                antiSubSynced = ship.getStateComponent().isStateAntiSub();
                return true;
            }
            case TOGGLE_BUTTON_TIMEKEEP -> {
                ship.getStateComponent().setStateTimekeep(!ship.getStateComponent().isStateTimekeep());
                timeKeepingSynced = ship.getStateComponent().isStateTimekeep();
                return true;
            }
            case TOGGLE_BUTTON_PICK_ITEM -> {
                ship.getStateComponent().setStatePickItem(!ship.getStateComponent().isStatePickItem());
                pickItemSynced = ship.getStateComponent().isStatePickItem();
                return true;
            }
            case TOGGLE_BUTTON_AUTO_PUMP -> {
                ship.getStateComponent().setStateAutoPump(!ship.getStateComponent().isStateAutoPump());
                autoPumpSynced = ship.getStateComponent().isStateAutoPump();
                return true;
            }
            case TOGGLE_BUTTON_SHOW_HELD -> {
                ship.setStateAppearance(!ship.isStateAppearance());
                appearanceSynced = ship.isStateAppearance();
                return true;
            }
            case TOGGLE_BUTTON_MOUNT -> {
                int current = ship.getStateEmotion(0);
                ship.setStateEmotion(0, current ^ 1, true);
                mountSynced = (ship.getStateEmotion(0) & 1) != 0;
                return true;
            }
            default -> {
            }
        }

        if (id >= SLIDER_FOLLOW_MIN_BASE && id <= SLIDER_FOLLOW_MIN_BASE + FOLLOW_MIN_MAX) {
            int requested = id - SLIDER_FOLLOW_MIN_BASE;
            this.followMinSynced = clampFollowMin(requested);
            this.followMaxSynced = clampFollowMax(this.followMaxSynced, this.followMinSynced);
            ship.getStateComponent().setFollowMin(this.followMinSynced);
            ship.getStateComponent().setFollowMax(this.followMaxSynced);
            return true;
        }

        if (id >= SLIDER_FOLLOW_MAX_BASE && id <= SLIDER_FOLLOW_MAX_BASE + FOLLOW_MAX_MAX) {
            int requested = id - SLIDER_FOLLOW_MAX_BASE;
            this.followMaxSynced = clampFollowMax(requested, this.followMinSynced);
            ship.getStateComponent().setFollowMax(this.followMaxSynced);
            return true;
        }

        if (id >= SLIDER_FLEE_HP_BASE && id <= SLIDER_FLEE_HP_BASE + FLEE_HP_MAX) {
            int requested = id - SLIDER_FLEE_HP_BASE;
            this.fleeHpSynced = clampFleeHp(requested);
            ship.getStateComponent().setFleeHp(this.fleeHpSynced);
            return true;
        }

        if (id >= SLIDER_WPSTAY_BASE && id <= SLIDER_WPSTAY_BASE + 16) {
            int requested = id - SLIDER_WPSTAY_BASE;
            ship.getStateComponent().setWpStay(Mth.clamp(requested, 0, 16));
            return true;
        }

        if (id >= SLIDER_RATION_MORALE_BASE && id <= SLIDER_RATION_MORALE_BASE + RATION_MORALE_MAX) {
            int requested = id - SLIDER_RATION_MORALE_BASE;
            this.rationMoraleSynced = clampRationMorale(requested);
            ship.getStateComponent().setRationMorale(this.rationMoraleSynced);
            return true;
        }

        int index = id - EQUIP_BUTTON_BASE;
        if (index >= 0 && index < ship.getEquipOptions().size()) {
            EntityShipBase.EquipOption option = ship.getEquipOptions().get(index);
            boolean next = !ship.getEquipFlag(option.key());
            ship.setEquipFlag(option.key(), next);
            return true;
        }

        if (id >= ACTION_TASK_SELECT_BASE && id <= ACTION_TASK_SELECT_BASE + 4) {
            int newTask = id - ACTION_TASK_SELECT_BASE;
            int curTask = ship.getStateComponent().getTaskId();
            ship.getStateComponent().setTaskId((curTask != newTask) ? newTask : 0);
            this.taskIdSynced = ship.getStateComponent().getTaskId();
            return true;
        }

        if (id == ACTION_TASK_META_TOGGLE) {
            ship.getStateComponent().setTaskSide(ship.getStateComponent().getTaskSide() ^ (1 << 18));
            this.taskSideSynced = ship.getStateComponent().getTaskSide();
            return true;
        }
        if (id == ACTION_TASK_ORE_TOGGLE) {
            ship.getStateComponent().setTaskSide(ship.getStateComponent().getTaskSide() ^ (1 << 19));
            this.taskSideSynced = ship.getStateComponent().getTaskSide();
            return true;
        }
        if (id == ACTION_TASK_NBT_TOGGLE) {
            ship.getStateComponent().setTaskSide(ship.getStateComponent().getTaskSide() ^ (1 << 20));
            this.taskSideSynced = ship.getStateComponent().getTaskSide();
            return true;
        }

        if (id >= ACTION_SIDE_TOGGLE_BASE && id <= ACTION_SIDE_TOGGLE_BASE + 17) {
            int bit = id - ACTION_SIDE_TOGGLE_BASE;
            ship.getStateComponent().setTaskSide(ship.getStateComponent().getTaskSide() ^ (1 << bit));
            this.taskSideSynced = ship.getStateComponent().getTaskSide();
            return true;
        }

        return super.clickMenuButton(player, id);
    }

    private int clampPage(int page) {
        int unlockedCount = getUnlockedPageCount();
        if (unlockedCount <= 1) {
            return 0;
        }
        if (page < 0) {
            return 0;
        }
        if (page >= unlockedCount) {
            return unlockedCount - 1;
        }
        return page;
    }

    private int getUnlockedStoragePagesServer() {
        if (this.ship.level().isClientSide) {
            return getUnlockedStoragePages();
        }
        return Mth.clamp(this.ship.getStateComponent().getEquipDrum(), 0, SHIP_PAGE_COUNT - 1);
    }

    private int getUnlockedPageCount() {
        return 1 + Mth.clamp(getUnlockedStoragePagesServer(), 0, SHIP_PAGE_COUNT - 1);
    }

    private boolean isPageUnlocked(int page) {
        return page >= 0 && page < getUnlockedPageCount();
    }

    private int clampFollowMin(int value) {
        int clamped = Math.max(FOLLOW_MIN_MIN, Math.min(FOLLOW_MIN_MAX, value));
        return Math.min(clamped, FOLLOW_MAX_MAX - 1);
    }

    private int clampFollowMax(int value, int followMin) {
        int min = Math.max(FOLLOW_MAX_MIN, followMin + 1);
        return Math.max(min, Math.min(FOLLOW_MAX_MAX, value));
    }

    private int clampFleeHp(int value) {
        return Math.max(FLEE_HP_MIN, Math.min(FLEE_HP_MAX, value));
    }

    private int clampRationMorale(int value) {
        return Math.max(RATION_MORALE_MIN, Math.min(RATION_MORALE_MAX, value));
    }

    private void refreshShipTankFluidSyncValues() {
        if (this.ship.level().isClientSide) {
            return;
        }

        int totalAmount = 0;
        int totalCapacity = 0;
        int slotCount = this.ship.getAccessibleInventorySlotCount();
        for (int i = ShipInventoryHandler.getEquipSlotCount(); i < slotCount; i++) {
            ItemStack stack = this.ship.getInventory().getStackInSlot(i);
            if (stack.isEmpty() || !(stack.getItem() instanceof ShipTankItem)) {
                continue;
            }

            Optional<IFluidHandlerItem> handlerOptional = FluidUtil.getFluidHandler(stack);
            if (handlerOptional.isPresent()) {
                IFluidHandlerItem handler = handlerOptional.get();
                totalAmount += handler.getFluidInTank(0).getAmount();
                totalCapacity += handler.getTankCapacity(0);
            } else {
                totalCapacity += ShipTankItem.getCapacity(stack);
            }
        }

        this.shipTankFluidAmountSynced = Math.max(0, totalAmount);
        this.shipTankFluidCapacitySynced = Math.max(0, totalCapacity);
    }

    private int toActualShipSlot(int localVisibleSlot) {
        int mapped = EQUIP_SLOTS + (clampPage(inventoryPage) * PAGE_SLOTS) + localVisibleSlot;
        if (mapped < 0) {
            return 0;
        }
        if (mapped >= SHIP_STORAGE_SIZE) {
            return SHIP_STORAGE_SIZE - 1;
        }
        return mapped;
    }

    private final class EquipSlot extends SlotItemHandler {
        private EquipSlot(int index, int x, int y) {
            super(ship.getInventory(), index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return ship.getInventory().isItemValid(this.getSlotIndex(), stack);
        }
    }

    private final class PagedShipSlot extends SlotItemHandler {
        private final int localVisibleSlot;
        private ItemStack clientStack = ItemStack.EMPTY;

        private PagedShipSlot(int localVisibleSlot, int x, int y) {
            super(ship.getInventory(), localVisibleSlot + EQUIP_SLOTS, x, y);
            this.localVisibleSlot = localVisibleSlot;
        }

        @Override
        public boolean hasItem() {
            return !getItem().isEmpty();
        }

        @Override
        public ItemStack getItem() {
            int idx = toActualShipSlot(localVisibleSlot);
            if (!ship.getInventory().isSlotAvailable(idx)) {
                return ItemStack.EMPTY;
            }
            if (ship.level().isClientSide) {
                return this.clientStack;
            }
            return ship.getInventory().getStackInSlot(idx);
        }

        @Override
        public void set(ItemStack stack) {
            int idx = toActualShipSlot(localVisibleSlot);
            if (!ship.getInventory().isSlotAvailable(idx)) {
                return;
            }
            if (ship.level().isClientSide) {
                this.clientStack = stack.copy();
                return;
            }
            ship.getInventory().setStackInSlot(idx, stack);
            setChanged();
        }

        @Override
        public void setByPlayer(ItemStack newStack) {
            set(newStack);
        }

        @Override
        public void setByPlayer(ItemStack newStack, ItemStack oldStack) {
            set(newStack);
        }

        @Override
        public ItemStack remove(int amount) {
            int idx = toActualShipSlot(localVisibleSlot);
            if (!ship.getInventory().isSlotAvailable(idx)) {
                return ItemStack.EMPTY;
            }
            if (ship.level().isClientSide) {
                if (this.clientStack.isEmpty() || amount <= 0) {
                    return ItemStack.EMPTY;
                }
                int taken = Math.min(amount, this.clientStack.getCount());
                ItemStack result = this.clientStack.copyWithCount(taken);
                this.clientStack.shrink(taken);
                if (this.clientStack.isEmpty()) {
                    this.clientStack = ItemStack.EMPTY;
                }
                return result;
            }
            return ship.getInventory().extractItem(idx, amount, false);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            int idx = toActualShipSlot(localVisibleSlot);
            return ship.getInventory().isSlotAvailable(idx)
                    && ship.getInventory().isItemValid(idx, stack);
        }

        @Override
        public boolean mayPickup(Player player) {
            int idx = toActualShipSlot(localVisibleSlot);
            if (!ship.getInventory().isSlotAvailable(idx)) {
                return false;
            }
            if (ship.level().isClientSide) {
                return !this.clientStack.isEmpty();
            }
            return !ship.getInventory().extractItem(idx, 1, true).isEmpty();
        }

        @Override
        public int getMaxStackSize() {
            int idx = toActualShipSlot(localVisibleSlot);
            if (!ship.getInventory().isSlotAvailable(idx)) {
                return 0;
            }
            return ship.getInventory().getSlotLimit(idx);
        }

        @Override
        public void setChanged() {
            if (!ship.level().isClientSide) {
                ship.onInventoryChanged();
            }
            super.setChanged();
        }
    }

    private static EntityShipBase getEntity(Inventory playerInv, RegistryFriendlyByteBuf buf) {
        if (buf == null) {
            throw new IllegalStateException("Missing ship entity data.");
        }

        int entityId = buf.readInt();
        if (playerInv.player.level().getEntity(entityId) instanceof EntityShipBase ship) {
            return ship;
        }

        throw new IllegalStateException("Ship entity not found.");
    }
}
