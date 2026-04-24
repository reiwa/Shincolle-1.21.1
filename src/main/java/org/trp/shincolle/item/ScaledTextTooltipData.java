package org.trp.shincolle.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import java.util.List;

public record ScaledTextTooltipData(List<Component> lines, float scale) implements TooltipComponent {
}