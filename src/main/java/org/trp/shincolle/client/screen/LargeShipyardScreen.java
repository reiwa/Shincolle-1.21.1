package org.trp.shincolle.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.menu.LargeShipyardMenu;

public class LargeShipyardScreen extends AbstractContainerScreen<LargeShipyardMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guilargeshipyard.png");

    private static final int[] ANIM_ICON_V_OFFSETS = new int[]{103, 121, 139, 157, 175, 193};

    private float guiTicks;

    public LargeShipyardScreen(LargeShipyardMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 208;
        this.imageHeight = 223;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        int mx = mouseX - this.leftPos;
        int my = mouseY - this.topPos;
        if (inside(mx, my, 8, 19, 22, 84)) {
            guiGraphics.renderTooltip(this.font, Component.literal(String.valueOf(this.menu.getPowerRemained())), mouseX, mouseY);
        }

        this.guiTicks += 0.125F;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        int powerScale = this.menu.getPowerScale();
        if (powerScale > 0) {
            guiGraphics.blit(TEXTURE, this.leftPos + 9, this.topPos + 83 - powerScale, 208, 64 - powerScale,
                    12, powerScale, 256, 256);
        }

        drawBuildIcons(guiGraphics);
        drawSelectionHighlights(guiGraphics);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        String time = this.menu.getBuildTimeString();
        guiGraphics.drawString(this.font, time, 176 - this.font.width(time) / 2, 77, 0x707070, false);

        if (!this.menu.hasMaterial() && this.menu.getBuildType() != 0) {
            Component text = Component.translatable("gui.shincolle.nomaterial");
            guiGraphics.drawString(this.font, text, 105 - this.font.width(text) / 2, 99, 0xFF6666, false);
        } else if (!this.menu.hasPower()) {
            Component text = Component.translatable("gui.shincolle.nofuel");
            guiGraphics.drawString(this.font, text, 105 - this.font.width(text) / 2, 99, 0xFF6666, false);
        }

        for (int i = 0; i < 4; i++) {
            int y = 20 + i * 19;
            String matBuild = String.valueOf(this.menu.getMatBuild(i));
            String matStock = String.valueOf(this.menu.getMatStock(i));

            int color = getMaterialColor(this.menu.getMatBuild(i));
            guiGraphics.drawString(this.font, matBuild, 73 - this.font.width(matBuild) / 2, y, color, false);
            guiGraphics.drawString(this.font, matStock, 125 - this.font.width(matStock) / 2, y, 0xEED15A, false);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int x = (int) mouseX - this.leftPos;
            int y = (int) mouseY - this.topPos;

            if (insideInclusive(x, y, 157, 24, 175, 42)) {
                sendMenuButton(LargeShipyardMenu.BUTTON_BUILD_SHIP);
                return true;
            }
            if (insideInclusive(x, y, 177, 24, 195, 42)) {
                sendMenuButton(LargeShipyardMenu.BUTTON_BUILD_EQUIP);
                return true;
            }
            if (insideInclusive(x, y, 23, 93, 48, 112)) {
                sendMenuButton(LargeShipyardMenu.BUTTON_TOGGLE_INV_MODE);
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int iconTop = 14 + i * 19;
                if (insideInclusive(x, y, 27, iconTop, 45, iconTop + 18)) {
                    sendMenuButton(LargeShipyardMenu.BUTTON_SELECT_MAT_0_A + i);
                    return true;
                }
            }

            for (int i = 0; i < 4; i++) {
                int rowTop = 19 + i * 19;
                if (insideInclusive(x, y, 51, rowTop, 97, rowTop + 8)) {
                    sendMenuButton(LargeShipyardMenu.BUTTON_SELECT_MAT_0_B + i);
                    return true;
                }
            }

            int selected = this.menu.getSelectMat();
            int addRowTop = 8 + selected * 19;
            int removeRowTop = 28 + selected * 19;
            for (int i = 0; i < 4; i++) {
                int x1 = 50 + i * 12;
                int x2 = x1 + 12;
                if (insideInclusive(x, y, x1, addRowTop, x2, addRowTop + 10)) {
                    sendMenuButton(LargeShipyardMenu.BUTTON_MAT_AMOUNT_BASE + i);
                    return true;
                }
                if (insideInclusive(x, y, x1, removeRowTop, x2, removeRowTop + 10)) {
                    sendMenuButton(LargeShipyardMenu.BUTTON_MAT_AMOUNT_BASE + 4 + i);
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private int getMaterialColor(int amount) {
        if (amount < 100) {
            return 0xFF6666;
        }
        if (amount == 1000) {
            return 0xEED15A;
        }
        return 0xFFFFFF;
    }

    private void drawBuildIcons(GuiGraphics guiGraphics) {
        int buildType = this.menu.getBuildType();
        if (buildType == 0) {
            return;
        }

        boolean equip = buildType == 2 || buildType == 4;
        boolean building = buildType == 3 || buildType == 4;
        int x = this.leftPos + (equip ? 177 : 157);
        int y = this.topPos + 24;
        int v = building ? ANIM_ICON_V_OFFSETS[(int) this.guiTicks % ANIM_ICON_V_OFFSETS.length] : 64;

        guiGraphics.blit(TEXTURE, x, y, 208, v, 18, 18, 256, 256);
    }

    private void drawSelectionHighlights(GuiGraphics guiGraphics) {
        int selectMat = this.menu.getSelectMat();
        guiGraphics.blit(TEXTURE, this.leftPos + 50, this.topPos + 8 + selectMat * 19, 0, 223, 48, 30, 256, 256);
        guiGraphics.blit(TEXTURE, this.leftPos + 27, this.topPos + 14 + selectMat * 19, 208, 64, 18, 18, 256, 256);

        if (this.menu.getInvMode() == 1) {
            guiGraphics.blit(TEXTURE, this.leftPos + 23, this.topPos + 92, 208, 82, 25, 20, 256, 256);
        }
    }

    private void sendMenuButton(int id) {
        if (Minecraft.getInstance().gameMode != null) {
            Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, id);
        }
    }

    private static boolean inside(int x, int y, int x1, int y1, int x2, int y2) {
        return x >= x1 && x < x2 && y >= y1 && y < y2;
    }

    private static boolean insideInclusive(int x, int y, int x1, int y1, int x2, int y2) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }
}
