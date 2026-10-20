package org.trp.shincolle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;
import org.trp.shincolle.custom.CustomShipConfig;
import org.trp.shincolle.custom.CustomShipRegistry;

import java.util.List;
import java.util.function.Supplier;

public class CustomShipSpawnEggItem extends OwnedSpawnEggItem {

    public static final String TAG_CUSTOM_SHIP_ID = "CustomShipId";

    public CustomShipSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int primaryColor, int secondaryColor, Properties properties) {
        super(type, primaryColor, secondaryColor, properties);
    }

    public static ItemStack createForShip(ItemLike item, String customShipId) {
        ItemStack stack = new ItemStack(item);
        setCustomShipId(stack, customShipId);
        return stack;
    }

    public static void setCustomShipId(ItemStack stack, String customShipId) {
        String id = customShipId != null ? customShipId : "";
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data -> data.update(tag -> {
            tag.putString(TAG_CUSTOM_SHIP_ID, id);
        }));
        stack.update(DataComponents.ENTITY_DATA, CustomData.EMPTY, data -> data.update(tag -> {
            tag.putString("id", "shincolle:custom_ship");
            tag.putString(TAG_CUSTOM_SHIP_ID, id);
        }));
    }

    public static String getCustomShipId(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains(TAG_CUSTOM_SHIP_ID)) {
                return tag.getString(TAG_CUSTOM_SHIP_ID);
            }
        }
        CustomData entityData = stack.get(DataComponents.ENTITY_DATA);
        if (entityData != null) {
            CompoundTag tag = entityData.copyTag();
            if (tag.contains(TAG_CUSTOM_SHIP_ID)) {
                return tag.getString(TAG_CUSTOM_SHIP_ID);
            }
        }
        return "";
    }

    @Override
    public Component getName(ItemStack stack) {
        String shipId = getCustomShipId(stack);
        if (!shipId.isEmpty()) {
            CustomShipConfig config = CustomShipRegistry.getConfig(shipId);
            if (config != null && config.name != null && !config.name.isEmpty()) {
                return Component.literal(config.name);
            }
            return Component.literal(shipId);
        }
        return super.getName(stack);
    }

    @Override
    public int getColor(int tintIndex) {
        return 0xFFFFFF;
    }
}
