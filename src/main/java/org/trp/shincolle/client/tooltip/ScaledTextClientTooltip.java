package org.trp.shincolle.client.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import org.trp.shincolle.item.ScaledTextTooltipData;

import java.util.List;

public class ScaledTextClientTooltip implements ClientTooltipComponent {
    private final List<Component> lines;
    private final float scale;

    public ScaledTextClientTooltip(ScaledTextTooltipData data) {
        this.lines = data.lines();
        this.scale = data.scale();
    }

    @Override
    public int getHeight() {
        return (int) (this.lines.size() * 10 * this.scale);
    }

    @Override
    public int getWidth(Font font) {
        int maxWidth = 0;
        for (Component line : this.lines) {
            int width = font.width(line);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return (int) (maxWidth * this.scale);
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        guiGraphics.pose().pushPose();

        guiGraphics.pose().translate(x, y, 0);
        guiGraphics.pose().scale(this.scale, this.scale, 1.0F);

        int currentY = 0;
        for (Component line : this.lines) {
            guiGraphics.drawString(font, line, 0, currentY, -1, true);
            currentY += 10;
        }

        guiGraphics.pose().popPose();
    }
}