package org.trp.shincolle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class CombatRationItem extends Item {
    private static final String TAG_VARIANT = "LegacyVariant";
    private static final int[] FOOD_VALUE = new int[]{900, 3600, 1200, 3900, 100, 900};
    private static final int[] MORALE_VALUE = new int[]{1400, 1800, 1600, 2000, 3000, 4000};

    public CombatRationItem(Properties properties) {
        super(properties.stacksTo(16));
    }

    public int getVariantCount() {
        return FOOD_VALUE.length;
    }

    public int getVariant(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return 0;
        }

        int raw = customData.copyTag().getInt(TAG_VARIANT);
        return Mth.clamp(raw, 0, FOOD_VALUE.length - 1);
    }

    public int getModelVariant(ItemStack stack) {
        return getVariant(stack);
    }

    public ItemStack createVariantStack(int variant) {
        int clamped = Mth.clamp(variant, 0, FOOD_VALUE.length - 1);
        ItemStack stack = new ItemStack(this);
        if (clamped > 0) {
            stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY,
                    data -> data.update(tag -> tag.putInt(TAG_VARIANT, clamped)));
        }
        return stack;
    }

    public void addAllVariantsToCreativeTab(CreativeModeTab.Output output) {
        for (int i = 0; i < FOOD_VALUE.length; i++) {
            output.accept(createVariantStack(i));
        }
    }

    public static int getFoodValue(int variant) {
        int clamped = Mth.clamp(variant, 0, FOOD_VALUE.length - 1);
        return FOOD_VALUE[clamped];
    }

    public static int getMoraleValue(int variant) {
        int clamped = Mth.clamp(variant, 0, MORALE_VALUE.length - 1);
        return MORALE_VALUE[clamped];
    }

    public static int getFuelGainMin(int variant) {
        return Math.max(1, getFoodValue(variant) / 100);
    }

    public static int getFuelGainMax(int variant) {
        return getFuelGainMin(variant) * 2;
    }

    public static int rollFuelGain(RandomSource random, int variant) {
        int min = getFuelGainMin(variant);
        return min + random.nextInt(min + 1);
    }

    @Override
    public Component getName(ItemStack stack) {
        int variant = getVariant(stack);
        String suffix = variant > 0 ? String.valueOf(variant) : "";
        return Component.translatable("item.shincolle:CombatRation" + suffix + ".name");
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        int variant = getVariant(stack);
        String[] lines = Component.translatable("gui.shincolle.combatration" + variant).getString().split("<br>");
        for (String line : lines) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                tooltipComponents.add(Component.literal(trimmed).withStyle(ChatFormatting.GRAY));
            }
        }

        tooltipComponents.add(Component.literal("+" + getMoraleValue(variant) + " ")
                .append(Component.translatable("gui.shincolle.combatration"))
                .withStyle(ChatFormatting.LIGHT_PURPLE));

        tooltipComponents.add(Component.literal("+" + getFuelGainMin(variant) + "~" + getFuelGainMax(variant) + " ")
                .append(Component.translatable("item.shincolle:Grudge.name"))
                .withStyle(ChatFormatting.RED));
    }
}
