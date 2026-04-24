package org.trp.shincolle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class PointerItem extends Item {
    private static final String TAG_VARIANT = "LegacyVariant";

    public static final int MODE_SINGLE = 0;
    public static final int MODE_GROUP = 1;
    public static final int MODE_FORMATION = 2;
    public static final int MODE_TEAM = 3;

    private static final int MODE_COUNT = 4;

    public PointerItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    public int getMode(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return MODE_SINGLE;
        }

        int raw = customData.copyTag().getInt(TAG_VARIANT);
        return Mth.clamp(raw, MODE_SINGLE, MODE_COUNT - 1);
    }

    public int getModelVariant(ItemStack stack) {
        return getMode(stack);
    }

    public int cycleMode(ItemStack stack) {
        int next = (getMode(stack) + 1) % MODE_COUNT;
        setMode(stack, next);
        return next;
    }

    public void setMode(ItemStack stack, int mode) {
        int clamped = Mth.clamp(mode, MODE_SINGLE, MODE_COUNT - 1);
        if (clamped == MODE_SINGLE) {
            stack.remove(DataComponents.CUSTOM_DATA);
            return;
        }

        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY,
                data -> data.update(tag -> tag.putInt(TAG_VARIANT, clamped)));
    }

    public ItemStack createVariantStack(int mode) {
        ItemStack stack = new ItemStack(this);
        setMode(stack, mode);
        return stack;
    }

    public void addAllVariantsToCreativeTab(CreativeModeTab.Output output) {
        for (int mode = MODE_SINGLE; mode < MODE_COUNT; mode++) {
            output.accept(createVariantStack(mode));
        }
    }

    public static String getModeTranslationKey(int mode) {
        return switch (mode) {
            case MODE_GROUP -> "gui.shincolle.pointer1";
            case MODE_FORMATION -> "gui.shincolle.pointer2";
            case MODE_TEAM -> "gui.shincolle.pointer4";
            default -> "gui.shincolle.pointer0";
        };
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        int mode = getMode(stack);
        tooltipComponents.add(Component.translatable(getModeTranslationKey(mode)).withStyle(ChatFormatting.GOLD));
    }
}
