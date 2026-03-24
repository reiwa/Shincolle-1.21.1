package org.trp.shincolle.menu;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.trp.shincolle.entity.base.EntityShipBase;

import java.util.List;

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
    public static final int TOGGLE_BUTTON_APPEARANCE = 35;

    public static final int STATE_FLAG_CAN_MELEE = 3;
    public static final int STATE_FLAG_LIGHT_ATTACK = 4;
    public static final int STATE_FLAG_HEAVY_ATTACK = 5;
    public static final int STATE_FLAG_LIGHT_AIRCRAFT_ATTACK = 6;
    public static final int STATE_FLAG_HEAVY_AIRCRAFT_ATTACK = 7;
    private static final int STATE_FLAG_APPEARANCE = 25;

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
            return inventoryPage;
        }

        @Override
        public void set(int value) {
            inventoryPage = clampPage(value);
        }
    };
    private final DataSlot canMeleeData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateFlag(STATE_FLAG_CAN_MELEE) ? 1 : 0;
        }

        @Override
        public void set(int value) {
            canMeleeSynced = value != 0;
        }
    };
    private final DataSlot lightAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateFlag(STATE_FLAG_LIGHT_ATTACK) ? 1 : 0;
        }

        @Override
        public void set(int value) {
            lightAttackSynced = value != 0;
        }
    };
    private final DataSlot heavyAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateFlag(STATE_FLAG_HEAVY_ATTACK) ? 1 : 0;
        }

        @Override
        public void set(int value) {
            heavyAttackSynced = value != 0;
        }
    };
    private final DataSlot lightAircraftAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK) ? 1 : 0;
        }

        @Override
        public void set(int value) {
            lightAircraftAttackSynced = value != 0;
        }
    };
    private final DataSlot heavyAircraftAttackData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK) ? 1 : 0;
        }

        @Override
        public void set(int value) {
            heavyAircraftAttackSynced = value != 0;
        }
    };
    private final DataSlot appearanceData = new DataSlot() {
        @Override
        public int get() {
            return ship.getStateFlag(STATE_FLAG_APPEARANCE) ? 1 : 0;
        }

        @Override
        public void set(int value) {
            appearanceSynced = value != 0;
        }
    };
    private int inventoryPage = 0;
    private boolean canMeleeSynced;
    private boolean lightAttackSynced;
    private boolean heavyAttackSynced;
    private boolean lightAircraftAttackSynced;
    private boolean heavyAircraftAttackSynced;
    private boolean appearanceSynced;

    public ShipContainerMenu(int containerId, Inventory playerInv, RegistryFriendlyByteBuf buf) {
        this(containerId, playerInv, getEntity(playerInv, buf));
    }

    public ShipContainerMenu(int containerId, Inventory playerInv, EntityShipBase ship) {
        super(ModMenus.SHIP_MENU.get(), containerId);
        this.ship = ship;
        this.canMeleeSynced = ship.getStateFlag(STATE_FLAG_CAN_MELEE);
        this.lightAttackSynced = ship.getStateFlag(STATE_FLAG_LIGHT_ATTACK);
        this.heavyAttackSynced = ship.getStateFlag(STATE_FLAG_HEAVY_ATTACK);
        this.lightAircraftAttackSynced = ship.getStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK);
        this.heavyAircraftAttackSynced = ship.getStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK);
        this.appearanceSynced = ship.getStateFlag(STATE_FLAG_APPEARANCE);
        this.addDataSlot(pageData);
        this.addDataSlot(canMeleeData);
        this.addDataSlot(lightAttackData);
        this.addDataSlot(heavyAttackData);
        this.addDataSlot(lightAircraftAttackData);
        this.addDataSlot(heavyAircraftAttackData);
        this.addDataSlot(appearanceData);

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
        return inventoryPage;
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

    public boolean isAppearanceEnabled() {
        return appearanceSynced;
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
                this.inventoryPage = 1;
                this.broadcastFullState();
                return true;
            }
            case PAGE_BUTTON_2 -> {
                this.inventoryPage = 2;
                this.broadcastFullState();
                return true;
            }
            default -> {
            }
        }

        switch (id) {
            case TOGGLE_BUTTON_CAN_MELEE -> {
                ship.setStateFlag(STATE_FLAG_CAN_MELEE, !ship.getStateFlag(STATE_FLAG_CAN_MELEE));
                canMeleeSynced = ship.getStateFlag(STATE_FLAG_CAN_MELEE);
                return true;
            }
            case TOGGLE_BUTTON_LIGHT_ATTACK -> {
                ship.setStateFlag(STATE_FLAG_LIGHT_ATTACK, !ship.getStateFlag(STATE_FLAG_LIGHT_ATTACK));
                lightAttackSynced = ship.getStateFlag(STATE_FLAG_LIGHT_ATTACK);
                return true;
            }
            case TOGGLE_BUTTON_HEAVY_ATTACK -> {
                ship.setStateFlag(STATE_FLAG_HEAVY_ATTACK, !ship.getStateFlag(STATE_FLAG_HEAVY_ATTACK));
                heavyAttackSynced = ship.getStateFlag(STATE_FLAG_HEAVY_ATTACK);
                return true;
            }
            case TOGGLE_BUTTON_LIGHT_AIRCRAFT -> {
                ship.setStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK, !ship.getStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK));
                lightAircraftAttackSynced = ship.getStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK);
                return true;
            }
            case TOGGLE_BUTTON_HEAVY_AIRCRAFT -> {
                ship.setStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK, !ship.getStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK));
                heavyAircraftAttackSynced = ship.getStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK);
                return true;
            }
            case TOGGLE_BUTTON_APPEARANCE -> {
                ship.setStateFlag(STATE_FLAG_APPEARANCE, !ship.getStateFlag(STATE_FLAG_APPEARANCE));
                appearanceSynced = ship.getStateFlag(STATE_FLAG_APPEARANCE);
                return true;
            }
            default -> {
            }
        }

        int index = id - EQUIP_BUTTON_BASE;
        if (index >= 0 && index < ship.getEquipOptions().size()) {
            EntityShipBase.EquipOption option = ship.getEquipOptions().get(index);
            boolean next = !ship.getEquipFlag(option.key());
            ship.setEquipFlag(option.key(), next);
            return true;
        }
        return super.clickMenuButton(player, id);
    }

    private int clampPage(int page) {
        if (page < 0) {
            return 0;
        }
        if (page >= SHIP_PAGE_COUNT) {
            return SHIP_PAGE_COUNT - 1;
        }
        return page;
    }

    private int toActualShipSlot(int localVisibleSlot) {
        int mapped = EQUIP_SLOTS + (inventoryPage * PAGE_SLOTS) + localVisibleSlot;
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
            if (ship.level().isClientSide) {
                return this.clientStack;
            }
            return ship.getInventory().getStackInSlot(toActualShipSlot(localVisibleSlot));
        }

        @Override
        public void set(ItemStack stack) {
            if (ship.level().isClientSide) {
                this.clientStack = stack.copy();
                return;
            }
            ship.getInventory().setStackInSlot(toActualShipSlot(localVisibleSlot), stack);
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
            int idx = toActualShipSlot(localVisibleSlot);
            return ship.getInventory().extractItem(idx, amount, false);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return ship.getInventory().isItemValid(toActualShipSlot(localVisibleSlot), stack);
        }

        @Override
        public boolean mayPickup(Player player) {
            if (ship.level().isClientSide) {
                return !this.clientStack.isEmpty();
            }
            return !ship.getInventory().extractItem(toActualShipSlot(localVisibleSlot), 1, true).isEmpty();
        }

        @Override
        public int getMaxStackSize() {
            return ship.getInventory().getSlotLimit(toActualShipSlot(localVisibleSlot));
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
