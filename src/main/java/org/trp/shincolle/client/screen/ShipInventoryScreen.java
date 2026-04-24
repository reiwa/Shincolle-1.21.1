package org.trp.shincolle.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
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

import java.util.ArrayList;
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
    private static final int SETTINGS_TAB_6 = 6;
    private static final int APPEARANCE_ROWS_PER_PAGE = 5;
    private static final int SLIDER_NONE = -1;
    private static final int SLIDER_FOLLOW_MIN = 0;
    private static final int SLIDER_FOLLOW_MAX = 1;
    private static final int SLIDER_FLEE_HP = 2;
    private static final int SLIDER_RATION_MORALE = 3;
    private static final int MODEL_BOX_HALF_WIDTH = 150;
    private static final int MODEL_BOX_TOP = 170;
    private static final int MODEL_BOX_BOTTOM = 110;
    private static final int MODEL_BOX_HALF_WIDTH_GATTAI = 175;
    private static final int MODEL_BOX_TOP_GATTAI = 195;
    private static final int MODEL_BOX_BOTTOM_GATTAI = 130;
    private static final float MODEL_SCALE_GATTAI_MULTIPLIER = 0.90F;

        private static final float[] LEGACY_MORALE_NEUTRAL =
            {0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
        private static final float[] LEGACY_MORALE_LEVEL_0 =
            {0.0F, 1.25F, 1.25F, 1.25F, 1.25F, 0.2F, 1.4F, 0.15F, 4.0F, 1.2F, 1.2F, 1.2F, 1.5F, 1.5F, 1.5F, 0.25F, 0.5F, 0.5F, 0.5F, 0.5F, 0.25F};
        private static final float[] LEGACY_MORALE_LEVEL_1 =
            {0.0F, 1.1F, 1.1F, 1.1F, 1.1F, 0.1F, 1.2F, 0.08F, 2.0F, 1.1F, 1.1F, 1.1F, 1.25F, 1.25F, 1.25F, 0.12F, 0.25F, 0.25F, 0.25F, 0.25F, 0.15F};
        private static final float[] LEGACY_MORALE_LEVEL_3 =
            {0.0F, 0.9F, 0.9F, 0.9F, 0.9F, -0.1F, 0.8F, -0.08F, -2.0F, 0.9F, 0.9F, 0.9F, 0.75F, 0.75F, 0.75F, -0.12F, -0.25F, -0.25F, -0.25F, -0.25F, -0.1F};
        private static final float[] LEGACY_MORALE_LEVEL_4 =
            {0.0F, 0.75F, 0.75F, 0.75F, 0.75F, -0.2F, 0.6F, -0.15F, -4.0F, 0.8F, 0.8F, 0.8F, 0.5F, 0.5F, 0.5F, -0.25F, -0.5F, -0.5F, -0.5F, -0.5F, -0.2F};

    private int activeDetailTab = DETAIL_TAB_BASIC;
    private int activeSettingsTab = SETTINGS_TAB_1;
    private int appearancePage = 0;
    private int activeSlider = SLIDER_NONE;
    private int sliderBarPos = 0;

    private boolean canMelee;
    private boolean lightAttack;
    private boolean heavyAttack;
    private boolean lightAircraftAttack;
    private boolean heavyAircraftAttack;
    private boolean ringEffect;
    private int followMinDistance;
    private int followMaxDistance;
    private int fleeHpPercent;
    private boolean passiveAttack;
    private boolean onSight;
    private boolean pvpMode;
    private boolean antiAir;
    private boolean antiSub;
    private boolean timeKeeping;
    private boolean pickItem;
    private boolean autoPump;
    private int rationMorale;
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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderLegacyHoverTooltips(guiGraphics, mouseX, mouseY);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE_BG, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        drawLockedInventoryPageOverlays(guiGraphics);
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

        switch (this.activeSettingsTab) {
            case 1 -> {
                drawLabel(guiGraphics, tr("gui.shincolle.canmelee"), 187, 133);
                drawLabel(guiGraphics, tr("gui.shincolle.canlightattack"), 187, 146);
                drawLabel(guiGraphics, tr("gui.shincolle.canheavyattack"), 187, 159);
                drawLabel(guiGraphics, tr("gui.shincolle.canairlightattack"), 187, 172);
                drawLabel(guiGraphics, tr("gui.shincolle.canairheavyattack"), 187, 185);
                drawLabel(guiGraphics, tr("gui.shincolle.auraeffect"), 187, 198);
            }
            case 2 -> {
                drawLabel(guiGraphics, tr("gui.shincolle.followmin"), 174, 134);
                drawLabel(guiGraphics, tr("gui.shincolle.followmax"), 174, 158);
                drawLabel(guiGraphics, tr("gui.shincolle.fleehp"), 174, 182);
                drawValue(guiGraphics, String.valueOf(getFollowMinDisplayValue()), 174, 145);
                drawValue(guiGraphics, String.valueOf(getFollowMaxDisplayValue()), 174, 169);
                drawValue(guiGraphics, String.valueOf(getFleeHpDisplayValue()), 174, 193);
            }
            case 3 -> {
                drawLabel(guiGraphics, tr("gui.shincolle.targetAI"), 187, 133);
                drawLabel(guiGraphics, tr("gui.shincolle.onsightAI"), 187, 146);
                drawLabel(guiGraphics, tr("gui.shincolle.ai.pvp"), 187, 159);
                drawLabel(guiGraphics, tr("gui.shincolle.ai.aa"), 187, 172);
                drawLabel(guiGraphics, tr("gui.shincolle.ai.asm"), 187, 185);
                drawLabel(guiGraphics, tr("gui.shincolle.ai.timekeeper"), 187, 198);
            }
            case 4 -> {
                drawLabel(guiGraphics, tr("gui.shincolle.ai.pickitem"), 187, 133);
                drawLabel(guiGraphics, tr("gui.shincolle.autopump"), 187, 146);
            }
            case 5 -> {
                drawLabel(guiGraphics, tr("gui.shincolle.ai.wpstay"), 174, 134);
                drawLabel(guiGraphics, tr("gui.shincolle.autocombatration"), 174, 158);
                drawValue(guiGraphics, getWpStayDisplay(), 174, 145);
                drawValue(guiGraphics, getRationMoraleDisplay(), 174, 169);
            }
            case 6 -> {
                drawLabel(guiGraphics, tr("gui.shincolle.showhelditem"), 187, 133);
                drawAppearanceLabels(guiGraphics);
            }
            default -> {
            }
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

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0 && this.activeSlider != SLIDER_NONE) {
            int x = (int) mouseX - this.leftPos;
            this.sliderBarPos = Mth.clamp(x - 191, 0, 42);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && this.activeSlider != SLIDER_NONE) {
            sendSliderValue();
            this.activeSlider = SLIDER_NONE;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean handleLegacyButtonClick(double mouseX, double mouseY) {
        int x = (int) mouseX - this.leftPos;
        int y = (int) mouseY - this.topPos;
        int unlockedPages = this.menu.getUnlockedStoragePages();

        if (inside(x, y, 61, 18, 70, 52)) {
            sendMenuButton(ShipContainerMenu.PAGE_BUTTON_0);
            return true;
        }
        if (inside(x, y, 61, 53, 70, 88)) {
            if (unlockedPages >= 1) {
                sendMenuButton(ShipContainerMenu.PAGE_BUTTON_1);
            }
            return true;
        }
        if (inside(x, y, 61, 89, 70, 125)) {
            if (unlockedPages >= 2) {
                sendMenuButton(ShipContainerMenu.PAGE_BUTTON_2);
            }
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

        for (int tab = SETTINGS_TAB_1; tab <= SETTINGS_TAB_6; tab++) {
            int y1 = 131 + (tab - 1) * 13;
            int y2 = y1 + 11;
            if (inside(x, y, 239, y1, 245, y2)) {
                this.activeSettingsTab = tab;
                this.activeSlider = SLIDER_NONE;
                return true;
            }
        }

        if (tryStartSliderDrag(x, y)) {
            return true;
        }

        if (this.activeSettingsTab == 1) {
            if (inside(x, y, 173, 131, 237, 143)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_CAN_MELEE);
                return true;
            }
            if (inside(x, y, 173, 144, 237, 156)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_LIGHT_ATTACK);
                return true;
            }
            if (inside(x, y, 173, 157, 237, 169)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_HEAVY_ATTACK);
                return true;
            }
            if (inside(x, y, 173, 170, 237, 182)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_LIGHT_AIRCRAFT);
                return true;
            }
            if (inside(x, y, 173, 183, 237, 195)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_HEAVY_AIRCRAFT);
                return true;
            }
            if (inside(x, y, 173, 196, 237, 208)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_RING_EFFECT);
                return true;
            }
        } else if (this.activeSettingsTab == 3) {
            if (inside(x, y, 173, 131, 237, 143)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_PASSIVE_ATTACK);
                return true;
            }
            if (inside(x, y, 173, 144, 237, 156)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_ON_SIGHT);
                return true;
            }
            if (inside(x, y, 173, 157, 237, 169)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_PVP);
                return true;
            }
            if (inside(x, y, 173, 170, 237, 182)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_ANTI_AIR);
                return true;
            }
            if (inside(x, y, 173, 183, 237, 195)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_ANTI_SUB);
                return true;
            }
            if (inside(x, y, 173, 196, 237, 208)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_TIMEKEEP);
                return true;
            }
        } else if (this.activeSettingsTab == 4) {
            if (inside(x, y, 173, 131, 237, 143)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_PICK_ITEM);
                return true;
            }
            if (inside(x, y, 173, 144, 237, 156)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_AUTO_PUMP);
                return true;
            }
        } else if (this.activeSettingsTab == 6) {
            if (inside(x, y, 173, 131, 237, 143)) {
                sendMenuButton(ShipContainerMenu.TOGGLE_BUTTON_SHOW_HELD);
                return true;
            }

            int maxPage = Math.max(0, (this.menu.getEquipOptionCount() - 1) / APPEARANCE_ROWS_PER_PAGE);
            if (inside(x, y, 246, 144, 252, 155) && this.appearancePage > 0) {
                this.appearancePage--;
                return true;
            }
            if (inside(x, y, 246, 157, 252, 168) && this.appearancePage < maxPage) {
                this.appearancePage++;
                return true;
            }

            List<?> options = this.menu.getEquipOptions();
            int start = this.appearancePage * APPEARANCE_ROWS_PER_PAGE;
            int end = Math.min(options.size(), start + APPEARANCE_ROWS_PER_PAGE);
            for (int optionIndex = start; optionIndex < end; optionIndex++) {
                int row = optionIndex - start;
                int rowY = 144 + row * 13;
                if (inside(x, y, 173, rowY, 237, rowY + 12)) {
                    sendMenuButton(this.menu.getEquipOptionButtonId(optionIndex));
                    return true;
                }
            }
        }

        return false;
    }

    private boolean tryStartSliderDrag(int x, int y) {
        if (this.activeSettingsTab == 2) {
            if (inside(x, y, 187, 145, 237, 154)) {
                this.activeSlider = SLIDER_FOLLOW_MIN;
                this.sliderBarPos = Mth.clamp(x - 191, 0, 42);
                return true;
            }
            if (inside(x, y, 187, 169, 237, 178)) {
                this.activeSlider = SLIDER_FOLLOW_MAX;
                this.sliderBarPos = Mth.clamp(x - 191, 0, 42);
                return true;
            }
            if (inside(x, y, 187, 193, 237, 202)) {
                this.activeSlider = SLIDER_FLEE_HP;
                this.sliderBarPos = Mth.clamp(x - 191, 0, 42);
                return true;
            }
        }

        if (this.activeSettingsTab == 5 && inside(x, y, 187, 169, 237, 178)) {
            this.activeSlider = SLIDER_RATION_MORALE;
            this.sliderBarPos = Mth.clamp(x - 191, 0, 42);
            return true;
        }

        return false;
    }

    private void sendSliderValue() {
        switch (this.activeSlider) {
            case SLIDER_FOLLOW_MIN -> {
                int value = (int) (this.sliderBarPos / 42.0f * 30.0f + 1.0f);
                sendMenuButton(ShipContainerMenu.SLIDER_FOLLOW_MIN_BASE + value);
            }
            case SLIDER_FOLLOW_MAX -> {
                int value = (int) (this.sliderBarPos / 42.0f * 30.0f + 2.0f);
                sendMenuButton(ShipContainerMenu.SLIDER_FOLLOW_MAX_BASE + value);
            }
            case SLIDER_FLEE_HP -> {
                int value = (int) (this.sliderBarPos / 42.0f * 100.0f);
                sendMenuButton(ShipContainerMenu.SLIDER_FLEE_HP_BASE + value);
            }
            case SLIDER_RATION_MORALE -> {
                int value = Math.max(1, Math.min(4, (this.sliderBarPos / 14) + 1));
                sendMenuButton(ShipContainerMenu.SLIDER_RATION_MORALE_BASE + value);
            }
            default -> {
            }
        }
    }

    private void syncStateFromMenu() {
        this.canMelee = this.menu.isCanMeleeEnabled();
        this.lightAttack = this.menu.isLightAttackEnabled();
        this.heavyAttack = this.menu.isHeavyAttackEnabled();
        this.lightAircraftAttack = this.menu.isLightAircraftAttackEnabled();
        this.heavyAircraftAttack = this.menu.isHeavyAircraftAttackEnabled();
        this.ringEffect = this.menu.isRingEffectEnabled();
        this.followMinDistance = this.menu.getFollowMinDistance();
        this.followMaxDistance = this.menu.getFollowMaxDistance();
        this.fleeHpPercent = this.menu.getFleeHpPercent();
        this.passiveAttack = this.menu.isPassiveAttackEnabled();
        this.onSight = this.menu.isOnSightEnabled();
        this.pvpMode = this.menu.isPvpEnabled();
        this.antiAir = this.menu.isAntiAirEnabled();
        this.antiSub = this.menu.isAntiSubEnabled();
        this.timeKeeping = this.menu.isTimeKeepingEnabled();
        this.pickItem = this.menu.isPickItemEnabled();
        this.autoPump = this.menu.isAutoPumpEnabled();
        this.rationMorale = this.menu.getRationMoraleThreshold();
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

    private void drawLockedInventoryPageOverlays(GuiGraphics guiGraphics) {
        int unlockedPages = this.menu.getUnlockedStoragePages();
        if (unlockedPages <= 0) {
            drawInventoryPageSlash(guiGraphics, 54);
            drawInventoryPageSlash(guiGraphics, 90);
        } else if (unlockedPages == 1) {
            drawInventoryPageSlash(guiGraphics, 90);
        }
    }

    private void drawInventoryPageSlash(GuiGraphics guiGraphics, int y) {
        guiGraphics.blit(TEXTURE_BG, this.leftPos + 62, this.topPos + y, 80, 214, 6, 34, 256, 256);
    }

    private void drawDetailTabIndicator(GuiGraphics guiGraphics) {
        int y = this.activeDetailTab == DETAIL_TAB_STATUS ? 54 : 18;
        guiGraphics.blit(TEXTURE_BG, this.leftPos + 135, this.topPos + y, 74, 214, 6, 34, 256, 256);
    }

    private void drawSettingsTabIndicator(GuiGraphics guiGraphics) {
        int y = 131 + (Math.max(SETTINGS_TAB_1, Math.min(SETTINGS_TAB_6, this.activeSettingsTab)) - 1) * 13;
        guiGraphics.blit(TEXTURE_BG, this.leftPos + 239, this.topPos + y, 74, 214, 6, 11, 256, 256);
    }

    private void drawToggleStateMarks(GuiGraphics guiGraphics) {
        switch (this.activeSettingsTab) {
            case 1 -> {
                drawOnOff(guiGraphics, 174, 131, this.canMelee);
                drawOnOff(guiGraphics, 174, 144, this.lightAttack);
                drawOnOff(guiGraphics, 174, 157, this.heavyAttack);
                drawOnOff(guiGraphics, 174, 170, this.lightAircraftAttack);
                drawOnOff(guiGraphics, 174, 183, this.heavyAircraftAttack);
                drawOnOff(guiGraphics, 174, 196, this.ringEffect);
            }
            case 2 -> drawFollowSliderTab(guiGraphics);
            case 3 -> {
                drawOnOff(guiGraphics, 174, 131, this.passiveAttack);
                drawOnOff(guiGraphics, 174, 144, this.onSight);
                drawOnOff(guiGraphics, 174, 157, this.pvpMode);
                drawOnOff(guiGraphics, 174, 170, this.antiAir);
                drawOnOff(guiGraphics, 174, 183, this.antiSub);
                drawOnOff(guiGraphics, 174, 196, this.timeKeeping);
            }
            case 4 -> {
                drawOnOff(guiGraphics, 174, 131, this.pickItem);
                drawOnOff(guiGraphics, 174, 144, this.autoPump);
            }
            case 5 -> drawRationSliderTab(guiGraphics);
            case 6 -> drawAppearanceToggleMarks(guiGraphics);
            default -> {
            }
        }
    }

    private void drawFollowSliderTab(GuiGraphics guiGraphics) {
        int followMinPos = this.activeSlider == SLIDER_FOLLOW_MIN
                ? this.sliderBarPos
                : (int) ((Math.max(1, this.followMinDistance) - 1) / 30.0f * 42.0f);
        int followMaxPos = this.activeSlider == SLIDER_FOLLOW_MAX
                ? this.sliderBarPos
                : (int) ((Math.max(2, this.followMaxDistance) - 2) / 30.0f * 42.0f);
        int fleeHpPos = this.activeSlider == SLIDER_FLEE_HP
                ? this.sliderBarPos
                : (int) (Math.max(0, Math.min(100, this.fleeHpPercent)) / 100.0f * 42.0f);

        drawSlider(guiGraphics, 191, 148, followMinPos);
        drawSlider(guiGraphics, 191, 172, followMaxPos);
        drawSlider(guiGraphics, 191, 196, fleeHpPos);
    }

    private void drawRationSliderTab(GuiGraphics guiGraphics) {
        int wpStayPos = (int) (Math.max(0, this.menu.getShip().getStateMinor(44)) * 0.0625f * 42.0f);
        int rationPos = this.activeSlider == SLIDER_RATION_MORALE
                ? this.sliderBarPos
                : (int) ((Math.max(1, Math.min(4, this.rationMorale)) - 1) * 14.0f);

        drawSlider(guiGraphics, 191, 148, Math.max(0, Math.min(42, wpStayPos)));
        drawSlider(guiGraphics, 191, 172, rationPos);
    }

    private void drawSlider(GuiGraphics guiGraphics, int x, int y, int pos) {
        int clamped = Math.max(0, Math.min(42, pos));
        guiGraphics.blit(TEXTURE_BG, this.leftPos + x, this.topPos + y, 31, 214, 43, 3, 256, 256);
        guiGraphics.blit(TEXTURE_BG, this.leftPos + x - 4 + clamped, this.topPos + y - 3, 22, 214, 9, 9, 256, 256);
    }

    private void drawAppearanceToggleMarks(GuiGraphics guiGraphics) {
        drawOnOff(guiGraphics, 174, 131, this.appearance);

        int optionCount = this.menu.getEquipOptionCount();
        int maxPage = Math.max(0, (optionCount - 1) / APPEARANCE_ROWS_PER_PAGE);
        this.appearancePage = Math.max(0, Math.min(this.appearancePage, maxPage));

        int start = this.appearancePage * APPEARANCE_ROWS_PER_PAGE;
        int end = Math.min(optionCount, start + APPEARANCE_ROWS_PER_PAGE);

        for (int optionIndex = start; optionIndex < end; optionIndex++) {
            int row = optionIndex - start;
            drawOnOff(guiGraphics, 174, 144 + row * 13, this.menu.isEquipOptionEnabled(optionIndex));
        }

        if (this.appearancePage > 0) {
            guiGraphics.blit(TEXTURE_BG, this.leftPos + 246, this.topPos + 144, 74, 214, 6, 11, 256, 256);
        }
        if (this.appearancePage < maxPage) {
            guiGraphics.blit(TEXTURE_BG, this.leftPos + 246, this.topPos + 157, 74, 214, 6, 11, 256, 256);
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
        int shipLevel = this.menu.getShipLevel();
        int hpCurrentValue = Math.round(this.menu.getShipHealth());
        int hpMaxValue = Math.round(this.menu.getShipMaxHealth());
        int hpColor = getModernizationColor(this.menu.getShip().getAttrBonus(0));

        String levelValue = String.valueOf(shipLevel);
        String hpCurrent = String.valueOf(hpCurrentValue);
        String hpMax = String.valueOf(hpMaxValue);

        int levelLabelX = 231 - this.font.width(levelLabel);
        int hpLabelX = 145 - this.font.width(hpLabel);
        int levelValueX = this.imageWidth - 6 - this.font.width(levelValue);
        int levelColor = shipLevel < 150 ? 0xFFFFFF : 0xFFD700;
        int hpCurrentColor = hpCurrentValue < hpMaxValue ? getDarkerColor(hpColor, 0.8F) : hpColor;

        guiGraphics.drawString(this.font, levelLabel, levelLabelX, 6, 0x00FFFF, true);
        guiGraphics.drawString(this.font, hpLabel, hpLabelX, 6, 0x00FFFF, true);
        guiGraphics.drawString(this.font, levelValue, levelValueX, 6, levelColor, true);
        guiGraphics.drawString(this.font, hpCurrent, 147, 6, hpCurrentColor, true);
        guiGraphics.drawString(this.font, "/" + hpMax, 148 + this.font.width(hpCurrent), 6, hpColor, true);
    }

    private int getModernizationColor(int level) {
        float ratio = (float) level / 3.0F - 0.5F;
        if (ratio >= 0.5F) {
            return 0xFF0000;
        }

        if (ratio >= 0.0F) {
            int green = (int) (255.0F * (1.0F - ratio * 2.0F));
            return 0xFF0000 + (green << 8);
        }

        float shifted = ratio + 0.5F;
        int blue = (int) (255.0F * (1.0F - shifted * 2.0F));
        return 0xFFFF00 + blue;
    }

    private int getDarkerColor(int color, float dark) {
        int b = (int) ((color & 0xFF) * dark);
        int g = (int) (((color >> 8) & 0xFF) * dark);
        int r = (int) (((color >> 16) & 0xFF) * dark);
        return (r << 16) | (g << 8) | b;
    }

    private void drawAppearanceLabels(GuiGraphics guiGraphics) {
        int optionCount = this.menu.getEquipOptionCount();
        int maxPage = Math.max(0, (optionCount - 1) / APPEARANCE_ROWS_PER_PAGE);
        this.appearancePage = Math.max(0, Math.min(this.appearancePage, maxPage));

        int start = this.appearancePage * APPEARANCE_ROWS_PER_PAGE;
        int end = Math.min(optionCount, start + APPEARANCE_ROWS_PER_PAGE);
        for (int optionIndex = start; optionIndex < end; optionIndex++) {
            int row = optionIndex - start;
            int y = 146 + row * 13;
            String label = this.menu.getEquipOptionLabel(optionIndex).getString();
            drawLabel(guiGraphics, trimLabelToWidth(label, 66), 187, y);
        }
    }

    private int getFollowMinDisplayValue() {
        if (this.activeSlider == SLIDER_FOLLOW_MIN) {
            return (int) (this.sliderBarPos / 42.0f * 30.0f + 1.0f);
        }
        return this.followMinDistance;
    }

    private int getFollowMaxDisplayValue() {
        if (this.activeSlider == SLIDER_FOLLOW_MAX) {
            return (int) (this.sliderBarPos / 42.0f * 30.0f + 2.0f);
        }
        return this.followMaxDistance;
    }

    private int getFleeHpDisplayValue() {
        if (this.activeSlider == SLIDER_FLEE_HP) {
            return (int) (this.sliderBarPos / 42.0f * 100.0f);
        }
        return this.fleeHpPercent;
    }

    private String getWpStayDisplay() {
        int wpStay = Math.max(0, this.menu.getShip().getStateMinor(44));
        int seconds = wpStay * 5;
        if (seconds >= 60) {
            return (seconds / 60) + "m";
        }
        return seconds + "s";
    }

    private String getRationMoraleDisplay() {
        int threshold;
        if (this.activeSlider == SLIDER_RATION_MORALE) {
            threshold = Math.max(1, Math.min(4, (this.sliderBarPos / 14) + 1));
        } else {
            threshold = Math.max(1, Math.min(4, this.rationMorale));
        }
        return tr("gui.shincolle.morale" + threshold);
    }

    private String tr(String key) {
        return Component.translatable(key).getString();
    }

    private void renderLegacyHoverTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (isHovering(mouseX, mouseY, 239, 18, 11, 11)) {
            renderMoraleTooltip(guiGraphics);
            return;
        }

        if (isHovering(mouseX, mouseY, 145, 4, 57, 11)) {
            renderModernizationHpTooltip(guiGraphics);
            return;
        }
    }

    private void renderMoraleTooltip(GuiGraphics guiGraphics) {
        EntityShipBase ship = this.menu.getShip();
        float[] moraleBuffs = getLegacyMoraleBuffs(ship.getMorale());

        List<Component> moraleState = new ArrayList<>();
        moraleState.add(Component.literal(tr("gui.shincolle.morale" + getMoraleLevel(ship.getMorale()))));
        guiGraphics.renderComponentTooltip(this.font, moraleState, this.leftPos + 120, this.topPos + 30);

        List<Component> labels = new ArrayList<>();
        List<Component> values = new ArrayList<>();
        addMoraleStat(labels, values, ChatFormatting.RED, tr("gui.shincolle.firepower1"), "x %.0f %% / %.0f %%", moraleBuffs[1] * 100.0F, moraleBuffs[2] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.RED, tr("gui.shincolle.firepower2"), "x %.0f %% / %.0f %%", moraleBuffs[3] * 100.0F, moraleBuffs[4] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.WHITE, tr("gui.shincolle.attackspeed"), "x %.0f %%", moraleBuffs[6] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.LIGHT_PURPLE, tr("gui.shincolle.range"), "+ %.1f", moraleBuffs[8]);
        addMoraleStat(labels, values, ChatFormatting.AQUA, tr("gui.shincolle.critical"), "x %.0f %%", moraleBuffs[9] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.YELLOW, tr("gui.shincolle.doublehit"), "x %.0f %%", moraleBuffs[10] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.GOLD, tr("gui.shincolle.triplehit"), "x %.0f %%", moraleBuffs[11] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.RED, tr("gui.shincolle.missreduce"), "x %.0f %%", moraleBuffs[12] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.YELLOW, tr("gui.shincolle.antiair"), "x %.0f %%", moraleBuffs[13] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.AQUA, tr("gui.shincolle.antiss"), "x %.0f %%", moraleBuffs[14] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.WHITE, tr("gui.shincolle.armor"), "+ %.0f %%", moraleBuffs[5] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.GOLD, tr("gui.shincolle.dodge"), "+ %.0f %%", moraleBuffs[15] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.GREEN, tr("gui.shincolle.equip.xp"), "+ %.0f %%", moraleBuffs[16] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.DARK_PURPLE, tr("gui.shincolle.equip.grudge"), "+ %.0f %%", moraleBuffs[17] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.DARK_AQUA, tr("gui.shincolle.equip.ammo"), "+ %.0f %%", moraleBuffs[18] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.DARK_GREEN, tr("gui.shincolle.equip.hpres"), "+ %.0f %%", moraleBuffs[19] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.DARK_RED, tr("gui.shincolle.equip.kb"), "+ %.0f %%", moraleBuffs[20] * 100.0F);
        addMoraleStat(labels, values, ChatFormatting.GRAY, tr("gui.shincolle.movespeed"), "+ %.2f", moraleBuffs[7]);

        int labelWidth = 0;
        for (Component label : labels) {
            labelWidth = Math.max(labelWidth, this.font.width(label));
        }

        guiGraphics.renderComponentTooltip(this.font, labels, this.leftPos + 120, this.topPos + 46);
        guiGraphics.renderComponentTooltip(this.font, values, this.leftPos + 126 + labelWidth, this.topPos + 46);
    }

    private void addMoraleStat(List<Component> labels, List<Component> values, ChatFormatting color,
                               String label, String format, Object... args) {
        labels.add(Component.literal(label).withStyle(color));
        values.add(Component.literal(String.format(format, args)));
    }

    private float[] getLegacyMoraleBuffs(int morale) {
        if (morale > 5100) {
            return LEGACY_MORALE_LEVEL_0;
        }
        if (morale > 3900) {
            return LEGACY_MORALE_LEVEL_1;
        }
        if (morale > 2100) {
            return LEGACY_MORALE_NEUTRAL;
        }
        if (morale > 900) {
            return LEGACY_MORALE_LEVEL_3;
        }
        return LEGACY_MORALE_LEVEL_4;
    }

    private void renderModernizationHpTooltip(GuiGraphics guiGraphics) {
        int hpBonus = this.menu.getShip().getAttrBonus(0);

        List<Component> lines = new ArrayList<>();
        lines.add(Component.literal(tr("gui.shincolle.modernlevel") + " " + hpBonus));
        guiGraphics.renderComponentTooltip(this.font, lines, this.leftPos + 145, this.topPos + 32);
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

    private boolean isHovering(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= this.leftPos + x
                && mouseX < this.leftPos + x + width
                && mouseY >= this.topPos + y
                && mouseY < this.topPos + y + height;
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
