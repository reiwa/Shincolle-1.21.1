package org.trp.shincolle.inventory;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.item.LegacyEquipItem;

public class ShipInventoryHandler extends ItemStackHandler {
    private static final int EQUIP_SLOT_COUNT = 6;
    private static final int STORAGE_BASE_SIZE = 24;
    private static final int STORAGE_PAGE_SIZE = 18;
    private static final int STORAGE_EXTRA_PAGES_MAX = 2;
    private static final int STATE_MINOR_EQUIP_DRUM = 36;
    private static final TagKey<Item> EQUIP_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "ship_equip_items"));

    private final EntityShipBase ship;

    public ShipInventoryHandler(EntityShipBase ship, int size) {
        super(size);
        this.ship = ship;
    }

    public static int getEquipSlotCount() {
        return EQUIP_SLOT_COUNT;
    }

    public int getUnlockedExtraPages() {
        return Mth.clamp(this.ship.getStateMinor(STATE_MINOR_EQUIP_DRUM), 0, STORAGE_EXTRA_PAGES_MAX);
    }

    public int getAccessibleSlotCount() {
        int allowed = STORAGE_BASE_SIZE + getUnlockedExtraPages() * STORAGE_PAGE_SIZE;
        return Mth.clamp(allowed, EQUIP_SLOT_COUNT, this.getSlots());
    }

    public boolean isSlotAvailable(int slot) {
        return slot >= 0 && slot < getAccessibleSlotCount();
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.ship.onInventoryChanged();
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot < 0 || stack.isEmpty() || !isSlotAvailable(slot)) {
            return false;
        }
        if (slot < EQUIP_SLOT_COUNT) {
            return stack.is(EQUIP_ITEMS_TAG) || stack.getItem() instanceof LegacyEquipItem;
        }
        return true;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (!isSlotAvailable(slot)) {
            return stack;
        }
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (!isSlotAvailable(slot)) {
            return ItemStack.EMPTY;
        }
        return super.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        if (!isSlotAvailable(slot)) {
            return 0;
        }
        return super.getSlotLimit(slot);
    }
}