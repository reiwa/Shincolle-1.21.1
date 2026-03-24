package org.trp.shincolle.inventory;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

public class ShipInventoryHandler extends ItemStackHandler {
    private static final int EQUIP_SLOT_COUNT = 6;
    private static final TagKey<Item> EQUIP_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "ship_equip_items"));

    private final EntityShipBase ship;

    public ShipInventoryHandler(EntityShipBase ship, int size) {
        super(size);
        this.ship = ship;
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.ship.onInventoryChanged();
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot < 0 || stack.isEmpty()) {
            return false;
        }
        if (slot < EQUIP_SLOT_COUNT) {
            return stack.is(EQUIP_ITEMS_TAG);
        }
        return true;
    }
}