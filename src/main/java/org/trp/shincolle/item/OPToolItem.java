package org.trp.shincolle.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class OPToolItem extends Item {

    public OPToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId() {
        return "item.shincolle.optool";
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("gui.shincolle.optool1").withStyle(net.minecraft.ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("gui.shincolle.optool2").withStyle(net.minecraft.ChatFormatting.AQUA));
    }
}
