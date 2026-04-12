package org.trp.shincolle.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.trp.shincolle.entity.EntityDestroyerIkazuchi;
import org.trp.shincolle.entity.EntityDestroyerInazuma;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.menu.ShipContainerMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipInventoryScreen extends AbstractContainerScreen<ShipContainerMenu> {
    private static final ResourceLocation TEXTURE_BG = ResourceLocation.fromNamespaceAndPath("shincolle", "textures/gui/guishipinventory.png");
    private static final ResourceLocation TEXTURE_ICON0 = ResourceLocation.fromNamespaceAndPath("shincolle", "textures/gui/guinameicon0.png");
    private static final ResourceLocation TEXTURE_ICON1 = ResourceLocation.fromNamespaceAndPath("shincolle", "textures/gui/guinameicon1.png");
    private static final ResourceLocation TEXTURE_ICON2 = ResourceLocation.fromNamespaceAndPath("shincolle", "textures/gui/guinameicon2.png");

    private static final int[] DEFAULT_SHIP_TYPE_ICON = {41, 0};
    private static final int[] DEFAULT_SHIP_NAME_ICON = {1, 0, 0};
    private static final Map<Byte, int[]> SHIP_TYPE_ICON_MAP = createShipTypeIconMap();
    private static final Map<Integer, int[]> SHIP_NAME_ICON_MAP = createShipNameIconMap();

    private static final int DETAIL_TAB_BASIC = 1;
    private static final int DETAIL_TAB_STATUS = 2;
    private static final int SETTINGS_TAB_1 = 1;
    private static final int SETTINGS_TAB_2 = 2;
    private static final int APPEARANCE_ROWS_PER_PAGE = 6;
    private static final int MODEL_BOX_HALF_WIDTH = 150;
    private static final int MODEL_BOX_TOP = 170;
    private static final int MODEL_BOX_BOTTOM = 110;
    private static final int MODEL_BOX_HALF_WIDTH_GATTAI = 175;
    private static final int MODEL_BOX_TOP_GATTAI = 195;
    private static final int MODEL_BOX_BOTTOM_GATTAI = 130;
    private static final float MODEL_SCALE_GATTAI_MULTIPLIER = 0.90F;

    private int activeDetailTab = DETAIL_TAB_BASIC;
    private int activeSettingsTab = SETTINGS_TAB_1;
    private int appearancePage = 0;

    private boolean canMelee;
    private boolean lightAttack;
    private boolean heavyAttack;
    private boolean lightAircraftAttack;
    private boolean heavyAircraftAttack;
    private boolean appearance;

    public ShipInventoryScreen(ShipContainerMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageWidth = 256;
        this.imageHeight = 214;
    }

    @Override
    protected void init() {
        super.init();
        syncStateFromMenu();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE_BG, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        drawInventoryPageIndicator(guiGraphics);
        drawDetailTabIndicator(guiGraphics);
        drawSettingsTabIndicator(guiGraphics);
        drawToggleStateMarks(guiGraphics);
        drawShipAndNameIcons(guiGraphics);
        drawShipEntityModel(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        drawLabel(guiGraphics, this.menu.getShip().getName().getString(), 8, 6);
        drawTopRightStatus(guiGraphics);

        if (this.activeDetailTab == DETAIL_TAB_BASIC) {
            drawLabel(guiGraphics, "EXP", 75, 20);
            drawValue(guiGraphics, String.valueOf(this.menu.getShipExp()), 115, 30);

            drawLabel(guiGraphics, "AMMO L", 75, 41);
            drawValue(guiGraphics, String.valueOf(this.menu.getShip().getAmmoLight()), 115, 51);

            drawLabel(guiGraphics, "AMMO H", 75, 62);
            drawValue(guiGraphics, String.valueOf(this.menu.getShip().getAmmoHeavy()), 115, 72);

            drawLabel(guiGraphics, "GRUDGE", 75, 83);
            drawValue(guiGraphics, String.valueOf(this.menu.getShipFuel()), 115, 93);

            drawLabel(guiGraphics, "AIRCRAFT", 75, 104);
            drawValue(guiGraphics, this.menu.getAircraftLight() + " / " + this.menu.getAircraftHeavy(), 115, 114);
        } else {
            drawLabel(guiGraphics, "FIREPWR", 75, 20);
            drawValue(guiGraphics, String.format("%.0f", this.menu.getShipFirepower()), 115, 30);

            drawLabel(guiGraphics, "ARMOR", 75, 41);
            drawValue(guiGraphics, String.format("%.1f%%", this.menu.getShipArmor() * 100.0f), 115, 51);

            drawLabel(guiGraphics, "RELOAD", 75, 62);
            drawValue(guiGraphics, String.format("%.2f", this.menu.getShipReloadSpeed()), 115, 72);

            drawLabel(guiGraphics, "MOVE", 75, 83);
            drawValue(guiGraphics, String.format("%.2f", this.menu.getShipMoveSpeed()), 115, 93);

            drawLabel(guiGraphics, "RANGE", 75, 104);
            drawValue(guiGraphics, String.format("%.1f", this.menu.getShipRange()), 115, 114);
        }

        if (this.activeSettingsTab == SETTINGS_TAB_1) {
            drawLabel(guiGraphics, "Can Melee", 187, 133);
            drawLabel(guiGraphics, "Light Attack", 187, 146);
            drawLabel(guiGraphics, "Heavy Attack", 187, 159);
            drawLabel(guiGraphics, "L.Aircraft", 187, 172);
            drawLabel(guiGraphics, "H.Aircraft", 187, 185);
        } else {
            drawAppearanceLabels(guiGraphics);
        }
    }

    @Override
    public void containerTick() {
        super.containerTick();
        syncStateFromMenu();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && handleLegacyButtonClick(mouseX, mouseY)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean handleLegacyButtonClick(double mouseX, double mouseY) {
        int x = (int) mouseX - this.leftPos;
        int y = (int) mouseY - this.topPos;

        if (inside(x, y, 61, 18, 70, 52)) {
            sendMenuButton(ShipContainerMenu.PAGE_BUTTON_0);
            return true;
        }
        if (inside(x, y, 61, 53, 70, 88)) {
            sendMenuButton(ShipContainerMenu.PAGE_BUTTON_1);
            return true;
        }
        if (inside(x, y, 61, 89, 70, 125)) {
            sendMenuButton(ShipContainerMenu.PAGE_BUTTON_2);
            return true;
        }

        if (inside(x, y, 133, 18, 142, 52)) {
            this.activeDetailTab = DETAIL_TAB_BASIC;
            return true;
        }
        if (inside(x, y, 133, 53, 142, 88)) {
            this.activeDetailTab = DETAIL_TAB_STATUS;
            return true;
        }

        if (inside(x, y, 239, 131, 245, 142)) {
            this.activeSettingsTab = SETTINGS_TAB_1;
            return true;
        }
        if (inside(x, y, 239, 144, 245, 155)) {
            this.activeSettingsTab = SETTINGS_TAB_2;
            return true;
        }

        if (this.activeSettingsTab == SETTINGS_TAB_1) {
            if (inside(x, y, 173, 131, 237, 143)) {
                this.canMelee = !this.canMelee;
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_CAN_MELEE);
                return true;
            }
            if (inside(x, y, 173, 144, 237, 156)) {
                this.lightAttack = !this.lightAttack;
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_LIGHT_ATTACK);
                return true;
            }
            if (inside(x, y, 173, 157, 237, 169)) {
                this.heavyAttack = !this.heavyAttack;
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_HEAVY_ATTACK);
                return true;
            }
            if (inside(x, y, 173, 170, 237, 182)) {
                this.lightAircraftAttack = !this.lightAircraftAttack;
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_LIGHT_AIRCRAFT);
                return true;
            }
            if (inside(x, y, 173, 183, 237, 195)) {
                this.heavyAircraftAttack = !this.heavyAircraftAttack;
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_HEAVY_AIRCRAFT);
                return true;
            }
        } else {
            int maxPage = Math.max(0, (this.menu.getEquipOptionCount() - 1) / APPEARANCE_ROWS_PER_PAGE);
            if (inside(x, y, 246, 131, 252, 142) && this.appearancePage > 0) {
                this.appearancePage--;
                return true;
            }
            if (inside(x, y, 246, 144, 252, 155) && this.appearancePage < maxPage) {
                this.appearancePage++;
                return true;
            }
            List<?> options = this.menu.getEquipOptions();
            int start = this.appearancePage * APPEARANCE_ROWS_PER_PAGE;
            int end = Math.min(options.size(), start + APPEARANCE_ROWS_PER_PAGE);
            for (int optionIndex = start; optionIndex < end; optionIndex++) {
                int row = optionIndex - start;
                int rowY = 131 + row * 13;
                if (inside(x, y, 173, rowY, 237, rowY + 12)) {
                    sendMenuButton(this.menu.getEquipOptionButtonId(optionIndex));
                    return true;
                }
            }
        }

        return false;
    }

    private void syncStateFromMenu() {
        this.canMelee = this.menu.isCanMeleeEnabled();
        this.lightAttack = this.menu.isLightAttackEnabled();
        this.heavyAttack = this.menu.isHeavyAttackEnabled();
        this.lightAircraftAttack = this.menu.isLightAircraftAttackEnabled();
        this.heavyAircraftAttack = this.menu.isHeavyAircraftAttackEnabled();
        this.appearance = this.menu.isAppearanceEnabled();
    }

    private void drawInventoryPageIndicator(GuiGraphics guiGraphics) {
        int y = switch (this.menu.getInventoryPage()) {
            case 1 -> 54;
            case 2 -> 90;
            default -> 18;
        };

        guiGraphics.blit(TEXTURE_BG, this.leftPos + 62, this.topPos + y, 74, 214, 6, 34, 256, 256);
    }

    private void drawDetailTabIndicator(GuiGraphics guiGraphics) {
        int y = this.activeDetailTab == DETAIL_TAB_STATUS ? 54 : 18;
        guiGraphics.blit(TEXTURE_BG, this.leftPos + 135, this.topPos + y, 74, 214, 6, 34, 256, 256);
    }

    private void drawSettingsTabIndicator(GuiGraphics guiGraphics) {
        int y = this.activeSettingsTab == SETTINGS_TAB_2 ? 144 : 131;
        guiGraphics.blit(TEXTURE_BG, this.leftPos + 239, this.topPos + y, 74, 214, 6, 11, 256, 256);
    }

    private void drawToggleStateMarks(GuiGraphics guiGraphics) {
        if (this.activeSettingsTab == SETTINGS_TAB_1) {
            drawOnOff(guiGraphics, 174, 131, this.canMelee);
            drawOnOff(guiGraphics, 174, 144, this.lightAttack);
            drawOnOff(guiGraphics, 174, 157, this.heavyAttack);
            drawOnOff(guiGraphics, 174, 170, this.lightAircraftAttack);
            drawOnOff(guiGraphics, 174, 183, this.heavyAircraftAttack);
        } else {
            drawAppearanceToggleMarks(guiGraphics);
        }
    }

    private void drawAppearanceToggleMarks(GuiGraphics guiGraphics) {
        int optionCount = this.menu.getEquipOptionCount();
        int maxPage = Math.max(0, (optionCount - 1) / APPEARANCE_ROWS_PER_PAGE);
        this.appearancePage = Math.max(0, Math.min(this.appearancePage, maxPage));

        int start = this.appearancePage * APPEARANCE_ROWS_PER_PAGE;
        int end = Math.min(optionCount, start + APPEARANCE_ROWS_PER_PAGE);

        for (int optionIndex = start; optionIndex < end; optionIndex++) {
            int row = optionIndex - start;
            drawOnOff(guiGraphics, 174, 131 + row * 13, this.menu.isEquipOptionEnabled(optionIndex));
        }

        if (this.appearancePage > 0) {
            guiGraphics.blit(TEXTURE_BG, this.leftPos + 246, this.topPos + 131, 74, 214, 6, 11, 256, 256);
        }
        if (this.appearancePage < maxPage) {
            guiGraphics.blit(TEXTURE_BG, this.leftPos + 246, this.topPos + 144, 74, 214, 6, 11, 256, 256);
        }
    }

    private void drawOnOff(GuiGraphics guiGraphics, int x, int y, boolean on) {
        int u = on ? 0 : 11;
        guiGraphics.blit(TEXTURE_BG, this.leftPos + x, this.topPos + y, u, 214, 11, 11, 256, 256);
    }

    private void sendMenuButton(int id) {
        if (Minecraft.getInstance().gameMode != null) {
            Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, id);
        }
    }

    private void drawTopRightStatus(GuiGraphics guiGraphics) {
        String levelLabel = "LEVEL";
        String hpLabel = "HP";
        String levelValue = String.valueOf(this.menu.getShipLevel());
        String hpCurrent = String.valueOf(Math.round(this.menu.getShipHealth()));
        String hpMax = String.valueOf(Math.round(this.menu.getShipMaxHealth()));

        int levelLabelX = 231 - this.font.width(levelLabel);
        int hpLabelX = 145 - this.font.width(hpLabel);
        int levelValueX = this.imageWidth - 6 - this.font.width(levelValue);

        guiGraphics.drawString(this.font, levelLabel, levelLabelX, 6, 0x00FFFF, true);
        guiGraphics.drawString(this.font, hpLabel, hpLabelX, 6, 0x00FFFF, true);
        guiGraphics.drawString(this.font, levelValue, levelValueX, 6, 0xFFFFFF, true);
        guiGraphics.drawString(this.font, hpCurrent, 147, 6, 0xFFFFFF, true);
        guiGraphics.drawString(this.font, "/" + hpMax, 148 + this.font.width(hpCurrent), 6, 0xFFFFFF, true);
    }

    private void drawAppearanceLabels(GuiGraphics guiGraphics) {
        int optionCount = this.menu.getEquipOptionCount();
        int maxPage = Math.max(0, (optionCount - 1) / APPEARANCE_ROWS_PER_PAGE);
        this.appearancePage = Math.max(0, Math.min(this.appearancePage, maxPage));

        int start = this.appearancePage * APPEARANCE_ROWS_PER_PAGE;
        int end = Math.min(optionCount, start + APPEARANCE_ROWS_PER_PAGE);
        for (int optionIndex = start; optionIndex < end; optionIndex++) {
            int row = optionIndex - start;
            int y = 133 + row * 13;
            String label = this.menu.getEquipOptionLabel(optionIndex).getString();
            drawLabel(guiGraphics, trimLabelToWidth(label, 66), 187, y);
        }
    }

    private void drawLabel(GuiGraphics guiGraphics, String text, int x, int y) {
        guiGraphics.drawString(this.font, text, x, y, 0x000000, false);
    }

    private void drawValue(GuiGraphics guiGraphics, String text, int x, int y) {
        guiGraphics.drawString(this.font, text, x + 1, y + 1, 0x000000, false);
        guiGraphics.drawString(this.font, text, x, y, 0xFFFFFF, false);
    }

    private boolean inside(int x, int y, int x1, int y1, int x2, int y2) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    private String trimLabelToWidth(String text, int maxWidth) {
        if (this.font.width(text) <= maxWidth) {
            return text;
        }
        String ellipsis = "...";
        int end = text.length();
        while (end > 0 && this.font.width(text.substring(0, end) + ellipsis) > maxWidth) {
            end--;
        }
        if (end <= 0) {
            return ellipsis;
        }
        return text.substring(0, end) + ellipsis;
    }

    private void drawShipAndNameIcons(GuiGraphics guiGraphics) {
        EntityShipBase ship = this.menu.getShip();
        int shipType = ship.getStateMinor(EntityShipBase.STATE_MINOR_FACTION_ID);
        int shipClass = ship.getStateMinor(EntityShipBase.STATE_MINOR_SHIP_CLASS);

        if (isRaidenGattaiState(ship)) {
            shipType = 2;
            shipClass = 55;
        }

        int[] shipTypeIconUv = SHIP_TYPE_ICON_MAP.getOrDefault((byte) shipType, DEFAULT_SHIP_TYPE_ICON);
        int[] shipNameIconData = SHIP_NAME_ICON_MAP.getOrDefault(shipClass, DEFAULT_SHIP_NAME_ICON);

        int rarity = ship.getStateMinor(EntityShipBase.STATE_MINOR_RARITY);
        int frameU = 0;
        int frameV = rarity > 99 ? 0 : 43;
        int frameW = rarity > 99 ? 40 : 30;
        int frameH = rarity > 99 ? 42 : 30;

        guiGraphics.blit(TEXTURE_ICON0, this.leftPos + 165, this.topPos + 18, frameU, frameV, frameW, frameH, 256, 256);
        guiGraphics.blit(TEXTURE_ICON0, this.leftPos + 167, this.topPos + 22, shipTypeIconUv[0], shipTypeIconUv[1], 28, 28, 256, 256);
        guiGraphics.blit(TEXTURE_ICON0, this.leftPos + 239, this.topPos + 18, getMoraleLevel(ship.getMorale()) * 11, 240, 11, 11, 256, 256);

        ResourceLocation nameTexture;
        int nameOffsetY;
        if (shipNameIconData[0] < 100) {
            nameTexture = TEXTURE_ICON1;
            nameOffsetY = shipNameIconData[0] == 4 ? -10 : 0;
        } else {
            nameTexture = TEXTURE_ICON2;
            nameOffsetY = shipNameIconData[0] == 6 ? -10 : 10;
        }

        guiGraphics.blit(nameTexture, this.leftPos + 176, this.topPos + 63 + nameOffsetY, shipNameIconData[1], shipNameIconData[2], 11, 59, 256, 256);
    }

    private void drawShipEntityModel(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        EntityShipBase ship = this.menu.getShip();
        float[] modelPos = ship.getModelPos();
        boolean isGattai = isRaidenGattaiState(ship);

        int modelX = this.leftPos + 218 + Mth.floor(modelPos[0]);
        int modelY = this.topPos + 100 + Mth.floor(modelPos[1]);
        float scaleMultiplier = isGattai ? MODEL_SCALE_GATTAI_MULTIPLIER : 1.0F;
        int modelScale = Math.max(16, Mth.floor(modelPos[3] * scaleMultiplier));

        int boxHalfWidth = isGattai ? MODEL_BOX_HALF_WIDTH_GATTAI : MODEL_BOX_HALF_WIDTH;
        int boxTop = isGattai ? MODEL_BOX_TOP_GATTAI : MODEL_BOX_TOP;
        int boxBottom = isGattai ? MODEL_BOX_BOTTOM_GATTAI : MODEL_BOX_BOTTOM;

        int boxX1 = modelX - boxHalfWidth;
        int boxY1 = modelY - boxTop;
        int boxX2 = modelX + boxHalfWidth;
        int boxY2 = modelY + boxBottom;

        if(isGattai){
            renderEntityWithPassengers(guiGraphics, modelX, modelY, modelScale, mouseX, mouseY, ship);
        } else {
            InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, boxX1, boxY1, boxX2, boxY2, modelScale, 0.0F, mouseX, mouseY, ship);
        }
    }

    private boolean isRaidenGattaiState(EntityShipBase ship) {
        return (ship instanceof EntityDestroyerIkazuchi || ship instanceof EntityDestroyerInazuma)
                && ship.getRidingState() > 1;
    }

    public static void renderEntityWithPassengers(GuiGraphics guiGraphics, int x, int y, int scale, float mouseX, float mouseY, LivingEntity entity) {
        float f = (float)Math.atan((x - mouseX) / 40.0F);
        float f1 = (float)Math.atan((y - 50.0F - mouseY) / 40.0F);

        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(x, y, 50.0D);
        poseStack.scale(scale, scale, -scale);

        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(f1 * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf1);
        poseStack.mulPose(quaternionf);

        float yBodyRotO = entity.yBodyRotO;
        float yBodyRot = entity.yBodyRot;
        float yRotO = entity.getYRot();
        float xRotO = entity.getXRot();
        float yHeadRotO = entity.yHeadRotO;
        float yHeadRot = entity.yHeadRot;

        entity.yBodyRotO = 180.0F + f * 20.0F;
        entity.yBodyRot = 180.0F + f * 20.0F;
        entity.setYRot(180.0F + f * 40.0F);
        entity.yHeadRotO = entity.getYRot();
        entity.yHeadRot = entity.getYRot();
        entity.setXRot(f1 * 20.0F);

        EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternionf1.conjugate();
        entityRenderDispatcher.overrideCameraOrientation(quaternionf1);
        entityRenderDispatcher.setRenderShadow(false);

        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, poseStack, guiGraphics.bufferSource(), 15728880);

            for (Entity passenger : entity.getPassengers()) {
                float passYBodyRotO = 0, passYBodyRot = 0, passYRotO = 0, passXRotO = 0, passYHeadRotO = 0, passYHeadRot = 0;

                if (passenger instanceof LivingEntity livingPassenger) {
                    passYBodyRotO = livingPassenger.yBodyRotO;
                    passYBodyRot = livingPassenger.yBodyRot;
                    passYRotO = livingPassenger.getYRot();
                    passXRotO = livingPassenger.getXRot();
                    passYHeadRotO = livingPassenger.yHeadRotO;
                    passYHeadRot = livingPassenger.yHeadRot;

                    livingPassenger.yBodyRotO = entity.yBodyRotO;
                    livingPassenger.yBodyRot = entity.yBodyRot;
                    livingPassenger.setYRot(entity.getYRot());
                    livingPassenger.yHeadRotO = entity.yHeadRotO;
                    livingPassenger.yHeadRot = entity.yHeadRot;
                    livingPassenger.setXRot(entity.getXRot());
                }

                poseStack.pushPose();

                Vec3 ridingPos = entity.getPassengerRidingPosition(passenger);

                double invScale = 1.0D / scale;
                double relativeX = (ridingPos.x - entity.getX()) * invScale;
                double relativeY = (ridingPos.y - entity.getY()) * invScale + 0.09D;
                double relativeZ = (ridingPos.z - entity.getZ()) * invScale;

                double customZOffset = -0.3D * invScale;
                float yawRad = entity.getYRot() * ((float) Math.PI / 180F);
                relativeX += -Math.sin(yawRad) * customZOffset;
                relativeZ += Math.cos(yawRad) * customZOffset;

                poseStack.translate(relativeX, relativeY, relativeZ+0.2F);

                poseStack.translate(relativeX, relativeY, relativeZ);

                entityRenderDispatcher.render(passenger, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, poseStack, guiGraphics.bufferSource(), 15728880);

                poseStack.popPose();

                if (passenger instanceof LivingEntity livingPassenger) {
                    livingPassenger.yBodyRotO = passYBodyRotO;
                    livingPassenger.yBodyRot = passYBodyRot;
                    livingPassenger.setYRot(passYRotO);
                    livingPassenger.setXRot(passXRotO);
                    livingPassenger.yHeadRotO = passYHeadRotO;
                    livingPassenger.yHeadRot = passYHeadRot;
                }
            }
        });

        guiGraphics.flush();
        entityRenderDispatcher.setRenderShadow(true);

        entity.yBodyRotO = yBodyRotO;
        entity.yBodyRot = yBodyRot;
        entity.setYRot(yRotO);
        entity.setXRot(xRotO);
        entity.yHeadRotO = yHeadRotO;
        entity.yHeadRot = yHeadRot;

        poseStack.popPose();
    }

    private int getMoraleLevel(int morale) {
        if (morale > 5100) {
            return 0;
        }
        if (morale > 3900) {
            return 1;
        }
        if (morale > 2100) {
            return 2;
        }
        if (morale > 900) {
            return 3;
        }
        return 4;
    }

    private static Map<Byte, int[]> createShipTypeIconMap() {
        Map<Byte, int[]> map = new HashMap<>();
        map.put((byte) 7, new int[]{12, 74});
        map.put((byte) -1, new int[]{41, 0});
        map.put((byte) 1, new int[]{41, 29});
        map.put((byte) 2, new int[]{41, 58});
        map.put((byte) 3, new int[]{41, 87});
        map.put((byte) 6, new int[]{70, 0});
        map.put((byte) 5, new int[]{70, 29});
        map.put((byte) 4, new int[]{70, 58});
        map.put((byte) 10, new int[]{70, 87});
        map.put((byte) 8, new int[]{99, 0});
        map.put((byte) 9, new int[]{99, 58});
        return map;
    }

    private static Map<Integer, int[]> createShipNameIconMap() {
        Map<Integer, int[]> map = new HashMap<>();
        map.put(0, new int[]{1, 0, 0});
        map.put(1, new int[]{1, 11, 0});
        map.put(2, new int[]{1, 22, 0});
        map.put(3, new int[]{1, 33, 0});
        map.put(4, new int[]{1, 44, 0});
        map.put(5, new int[]{1, 55, 0});
        map.put(6, new int[]{1, 66, 0});
        map.put(7, new int[]{1, 77, 0});
        map.put(8, new int[]{1, 88, 0});
        map.put(9, new int[]{1, 99, 0});
        map.put(10, new int[]{1, 110, 0});
        map.put(11, new int[]{1, 121, 0});
        map.put(12, new int[]{1, 132, 0});
        map.put(13, new int[]{1, 143, 0});
        map.put(14, new int[]{1, 154, 0});
        map.put(15, new int[]{1, 165, 0});
        map.put(16, new int[]{1, 176, 0});
        map.put(17, new int[]{1, 187, 0});
        map.put(18, new int[]{1, 198, 0});
        map.put(19, new int[]{1, 209, 0});
        map.put(64, new int[]{1, 220, 0});
        map.put(20, new int[]{2, 0, 59});
        map.put(21, new int[]{2, 11, 59});
        map.put(22, new int[]{2, 22, 59});
        map.put(23, new int[]{2, 33, 59});
        map.put(24, new int[]{2, 187, 59});
        map.put(25, new int[]{2, 176, 59});
        map.put(26, new int[]{2, 66, 59});
        map.put(27, new int[]{2, 77, 59});
        map.put(28, new int[]{2, 88, 59});
        map.put(29, new int[]{2, 99, 59});
        map.put(30, new int[]{2, 110, 59});
        map.put(31, new int[]{2, 121, 59});
        map.put(32, new int[]{2, 132, 59});
        map.put(33, new int[]{2, 154, 59});
        map.put(34, new int[]{2, 44, 59});
        map.put(35, new int[]{2, 165, 59});
        map.put(36, new int[]{101, 0, 0});
        map.put(37, new int[]{101, 11, 0});
        map.put(38, new int[]{101, 198, 0});
        map.put(39, new int[]{101, 209, 0});
        map.put(40, new int[]{2, 143, 59});
        map.put(41, new int[]{2, 55, 59});
        map.put(43, new int[]{2, 209, 59});
        map.put(44, new int[]{2, 231, 59});
        map.put(45, new int[]{2, 198, 59});
        map.put(46, new int[]{101, 22, 0});
        map.put(47, new int[]{101, 33, 0});
        map.put(48, new int[]{101, 44, 0});
        map.put(49, new int[]{2, 220, 59});
        map.put(50, new int[]{2, 242, 59});
        map.put(51, new int[]{101, 55, 0});
        map.put(52, new int[]{101, 66, 0});
        map.put(53, new int[]{101, 77, 0});
        map.put(54, new int[]{101, 88, 0});
        map.put(55, new int[]{101, 99, 0});
        map.put(56, new int[]{101, 110, 0});
        map.put(57, new int[]{101, 121, 0});
        map.put(58, new int[]{101, 132, 0});
        map.put(59, new int[]{101, 143, 0});
        map.put(60, new int[]{101, 154, 0});
        map.put(61, new int[]{101, 165, 0});
        map.put(62, new int[]{101, 176, 0});
        map.put(63, new int[]{101, 187, 0});
        map.put(65, new int[]{3, 0, 118});
        map.put(66, new int[]{3, 11, 118});
        map.put(67, new int[]{3, 22, 118});
        map.put(68, new int[]{3, 33, 118});
        map.put(69, new int[]{3, 44, 118});
        map.put(70, new int[]{3, 55, 118});
        map.put(71, new int[]{3, 66, 118});
        map.put(72, new int[]{3, 77, 118});
        map.put(73, new int[]{3, 88, 118});
        map.put(74, new int[]{3, 99, 118});
        map.put(75, new int[]{3, 110, 118});
        map.put(76, new int[]{3, 121, 118});
        map.put(77, new int[]{3, 132, 118});
        map.put(78, new int[]{3, 143, 118});
        map.put(79, new int[]{3, 154, 118});
        map.put(80, new int[]{3, 165, 118});
        map.put(81, new int[]{3, 176, 118});
        map.put(82, new int[]{3, 187, 118});
        map.put(83, new int[]{4, 0, 177});
        map.put(84, new int[]{4, 11, 177});
        return map;
    }
}
