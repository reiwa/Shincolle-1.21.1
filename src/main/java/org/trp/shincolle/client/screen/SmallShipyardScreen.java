package org.trp.shincolle.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.menu.SmallShipyardMenu;

public class SmallShipyardScreen extends AbstractContainerScreen<SmallShipyardMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guismallshipyard.png");

    private float guiTicks;

    public SmallShipyardScreen(SmallShipyardMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 164;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        if (inside(mouseX - this.leftPos, mouseY - this.topPos, 9, 17, 23, 49)) {
            guiGraphics.renderTooltip(this.font, Component.literal(String.valueOf(this.menu.getPowerRemained())), mouseX, mouseY);
        }

        this.guiTicks += 0.125F;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        int powerScale = this.menu.getPowerScale();
        if (powerScale > 0) {
            guiGraphics.blit(TEXTURE, this.leftPos + 10, this.topPos + 48 - powerScale, 176, 47 - powerScale,
                    12, powerScale, 256, 256);
        }

        drawBuildTypeIndicator(guiGraphics);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.imageWidth / 2 - this.font.width(this.title) / 2, 6, 0x404040, false);

        String time = this.menu.getBuildTimeString();
        guiGraphics.drawString(this.font, time, 71 - this.font.width(time) / 2, 51, 0x404040, false);

        if (!this.menu.hasMaterial()) {
            Component text = Component.translatable("gui.shincolle.nomaterial");
            guiGraphics.drawString(this.font, text, 80 - this.font.width(text) / 2, 67, 0xFF3333, false);
        } else if (!this.menu.hasPower()) {
            Component text = Component.translatable("gui.shincolle.nofuel");
            guiGraphics.drawString(this.font, text, 80 - this.font.width(text) / 2, 67, 0xFF3333, false);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int x = (int) mouseX - this.leftPos;
            int y = (int) mouseY - this.topPos;
            if (inside(x, y, 123, 17, 141, 35)) {
                sendMenuButton(SmallShipyardMenu.BUTTON_SHIP);
                return true;
            }
            if (inside(x, y, 143, 17, 161, 35)) {
                sendMenuButton(SmallShipyardMenu.BUTTON_EQUIP);
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void drawBuildTypeIndicator(GuiGraphics guiGraphics) {
        int buildType = this.menu.getBuildType();
        if (buildType == 0) {
            return;
        }

        int u = 176;
        int x = buildType == 1 || buildType == 3 ? 123 : 143;
        boolean animating = buildType == 3 || buildType == 4;
        int v = animating ? 65 + ((int) this.guiTicks % 6) * 18 : 47;
        guiGraphics.blit(TEXTURE, this.leftPos + x, this.topPos + 17, u, v, 18, 18, 256, 256);
    }

    private void sendMenuButton(int id) {
        if (Minecraft.getInstance().gameMode != null) {
            Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, id);
        }
    }

    private static boolean inside(int x, int y, int x1, int y1, int x2, int y2) {
        return x >= x1 && x < x2 && y >= y1 && y < y2;
    }
}
