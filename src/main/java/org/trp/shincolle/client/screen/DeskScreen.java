package org.trp.shincolle.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;

import net.neoforged.neoforge.network.PacketDistributor;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.client.renderer.BookRenderer;
import org.trp.shincolle.entity.base.EntityMountBase;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.menu.DeskMenu;
import org.trp.shincolle.network.C2SBookStatePayload;
import org.trp.shincolle.network.C2SDeskGuiPayload;
import org.trp.shincolle.network.C2SDeskSummonPayload;
import org.trp.shincolle.network.C2STargetClassTogglePayload;
import org.trp.shincolle.network.C2STeamActionPayload;
import org.trp.shincolle.reference.Values;
import org.trp.shincolle.team.TeamData;
import org.trp.shincolle.world.TeamSavedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class DeskScreen extends AbstractContainerScreen<DeskMenu> {

    private static final float GUI_SCALE = 1.25f;
    private static final float GUI_SCALE_INV = 1.0f / GUI_SCALE;

    public static final ResourceLocation GUI_TEAM = ResourceLocation.fromNamespaceAndPath("shincolle", "textures/gui/guideskteam.png");
    public static final ResourceLocation GUI_TARGET = ResourceLocation.fromNamespaceAndPath("shincolle", "textures/gui/guidesktarget.png");

    private static List<TeamData> clientTeams = new ArrayList<>();

    public static void updateClientTeams(List<TeamData> teams) {
        clientTeams = teams != null ? teams : new ArrayList<>();
    }

    private int pageId = 0;
    private int chapId = 0;
    private LivingEntity entityTemp = null;
    private float targetRotateX = 0f;
    private float targetRotateY = -30f;
    private float currentRotateX = 0f;
    private float currentRotateY = -30f;
    private float prevRotateX = 0f;
    private float prevRotateY = -30f;
    private float targetScale = 30f;
    private float currentScale = 30f;
    private float prevScale = 30f;
    private double lastXMouse = 0;
    private double lastYMouse = 0;
    private int guiFunc = 0;

    private int radarZoomLv = 0;
    private final List<RadarEntity> shipList = new ArrayList<>();
    private final List<Integer> selectedShips = new ArrayList<>();
    private int tickGUI = 0;
    private int tempCD = 60;
    private int teamState = 0;
    private int listFocus = 1;
    private EditBox textField;
    private Entity targetEntity = null;
    private final List<String> tarList = new ArrayList<>();

    private final int[] listNum = new int[]{0, 0, 0, 0, 0};
    private final int[] listClicked = new int[]{-1, -1, -1, -1, -1};

    private static final Map<Integer, Supplier<? extends EntityType<?>>> CLASS_ID_TO_ENTITY_TYPE = new HashMap<>();

    static {
        CLASS_ID_TO_ENTITY_TYPE.put(0, ModEntities.DESTROYER_I);
        CLASS_ID_TO_ENTITY_TYPE.put(1, ModEntities.DESTROYER_RO);
        CLASS_ID_TO_ENTITY_TYPE.put(2, ModEntities.DESTROYER_HA);
        CLASS_ID_TO_ENTITY_TYPE.put(3, ModEntities.DESTROYER_NI);
        CLASS_ID_TO_ENTITY_TYPE.put(9, ModEntities.HEAVY_CRUISER_RI);
        CLASS_ID_TO_ENTITY_TYPE.put(10, ModEntities.HEAVY_CRUISER_NE);
        CLASS_ID_TO_ENTITY_TYPE.put(12, ModEntities.CARRIER_WO);
        CLASS_ID_TO_ENTITY_TYPE.put(13, ModEntities.BATTLESHIP_RU);
        CLASS_ID_TO_ENTITY_TYPE.put(14, ModEntities.BATTLESHIP_TA);
        CLASS_ID_TO_ENTITY_TYPE.put(15, ModEntities.BATTLESHIP_RE);
        CLASS_ID_TO_ENTITY_TYPE.put(16, ModEntities.TRANSPORT_WA);
        CLASS_ID_TO_ENTITY_TYPE.put(17, ModEntities.SUBM_KA);
        CLASS_ID_TO_ENTITY_TYPE.put(18, ModEntities.SUBM_YO);
        CLASS_ID_TO_ENTITY_TYPE.put(19, ModEntities.SUBM_SO);
        CLASS_ID_TO_ENTITY_TYPE.put(20, ModEntities.CARRIER_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(21, ModEntities.AIRFIELD_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(26, ModEntities.BATTLESHIP_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(27, ModEntities.DESTROYER_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(28, ModEntities.HARBOUR_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(29, ModEntities.ISOLATED_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(30, ModEntities.MIDWAY_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(31, ModEntities.NORTHERN_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(44, ModEntities.SUBM_HIME);
        CLASS_ID_TO_ENTITY_TYPE.put(72, ModEntities.SSNH);
        CLASS_ID_TO_ENTITY_TYPE.put(33, ModEntities.CARRIER_W_DEMON);
        CLASS_ID_TO_ENTITY_TYPE.put(49, ModEntities.CA_HIME);

        CLASS_ID_TO_ENTITY_TYPE.put(36, ModEntities.DESTROYER_SHIMAKAZE);
        CLASS_ID_TO_ENTITY_TYPE.put(37, ModEntities.BATTLESHIP_NAGATO);
        CLASS_ID_TO_ENTITY_TYPE.put(38, ModEntities.SUBM_U511);
        CLASS_ID_TO_ENTITY_TYPE.put(39, ModEntities.SUBM_RO500);
        CLASS_ID_TO_ENTITY_TYPE.put(46, ModEntities.BATTLESHIP_YAMATO);
        CLASS_ID_TO_ENTITY_TYPE.put(47, ModEntities.CARRIER_KAGA);
        CLASS_ID_TO_ENTITY_TYPE.put(48, ModEntities.CARRIER_AKAGI);
        CLASS_ID_TO_ENTITY_TYPE.put(51, ModEntities.DESTROYER_AKATSUKI);
        CLASS_ID_TO_ENTITY_TYPE.put(52, ModEntities.DESTROYER_HIBIKI);
        CLASS_ID_TO_ENTITY_TYPE.put(53, ModEntities.DESTROYER_IKAZUCHI);
        CLASS_ID_TO_ENTITY_TYPE.put(54, ModEntities.DESTROYER_INAZUMA);
        CLASS_ID_TO_ENTITY_TYPE.put(56, ModEntities.CRUISER_TENRYUU);
        CLASS_ID_TO_ENTITY_TYPE.put(57, ModEntities.CRUISER_TATSUTA);
        CLASS_ID_TO_ENTITY_TYPE.put(58, ModEntities.CRUISER_ATAGO);
        CLASS_ID_TO_ENTITY_TYPE.put(59, ModEntities.CRUISER_TAKAO);
        CLASS_ID_TO_ENTITY_TYPE.put(60, ModEntities.BB_KONGOU);
        CLASS_ID_TO_ENTITY_TYPE.put(61, ModEntities.BB_HIEI);
        CLASS_ID_TO_ENTITY_TYPE.put(62, ModEntities.BB_HARUNA);
        CLASS_ID_TO_ENTITY_TYPE.put(63, ModEntities.BB_KIRISHIMA);
    }

    private static final int[][][] DESK_BUTTONS = new int[][][]{
            {{3, 2, 19, 18}, {22, 2, 38, 18}, {41, 2, 57, 18}, {60, 2, 76, 18}},
            {{7, 158, 55, 170}, {86, 158, 134, 170}, {140, 23, 252, 54}, {140, 55, 252, 86}, {140, 87, 252, 118}, {140, 119, 252, 150}, {140, 151, 252, 187}, {7, 172, 55, 184}},
            {{0, 25, 122, 193}, {123, 25, 240, 193}, {243, 34, 256, 45}, {243, 46, 256, 59}, {243, 60, 256, 71}, {243, 72, 256, 82}, {243, 83, 256, 96}, {243, 97, 256, 109}, {243, 110, 256, 121}},
            {{7, 158, 55, 170}, {140, 23, 252, 54}, {140, 55, 252, 86}, {140, 87, 252, 118}, {140, 119, 252, 150}, {140, 151, 252, 187}, {7, 172, 55, 184}, {86, 158, 134, 170}, {86, 172, 135, 184}, {7, 61, 134, 91}, {7, 92, 134, 122}, {7, 123, 134, 153}},
            {{7, 158, 55, 170}, {140, 23, 252, 37}, {140, 38, 252, 49}, {140, 50, 252, 61}, {140, 62, 252, 73}, {140, 74, 252, 85}, {140, 86, 252, 97}, {140, 98, 252, 109}, {140, 110, 252, 121}, {140, 122, 252, 133}, {140, 134, 252, 145}, {140, 146, 252, 157}, {140, 158, 252, 169}, {140, 170, 252, 183}}
    };

    private int getDeskButton(int page, int x, int y) {
        if (page < 0 || page >= DESK_BUTTONS.length) return -1;
        int[][] pageBtns = DESK_BUTTONS[page];
        for (int i = 0; i < pageBtns.length; i++) {
            if (x >= pageBtns[i][0] && y >= pageBtns[i][1] && x <= pageBtns[i][2] && y <= pageBtns[i][3]) {
                return i;
            }
        }
        return -1;
    }

    public DeskScreen(DeskMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = (int) (256 * GUI_SCALE);
        this.imageHeight = (int) (192 * GUI_SCALE);
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if (menu.getDeskType() == 0) {
            this.guiFunc = menu.getGuiFunc();
            this.radarZoomLv = menu.getRadarZoom();
            this.chapId = menu.getChapter();
            this.pageId = menu.getPage();
        } else if (menu.getDeskType() == 1) {
            this.guiFunc = 1;
            this.radarZoomLv = 0;
        } else if (menu.getDeskType() == 2) {
            this.guiFunc = 2;
            this.chapId = menu.getChapter();
            this.pageId = menu.getPage();
        }

        this.textField = new EditBox(this.font, this.leftPos + (int)(10 * GUI_SCALE), this.topPos + (int)(24 * GUI_SCALE), (int)(124 * GUI_SCALE), (int)(12 * GUI_SCALE), Component.literal(""));
        this.textField.setTextColor(-1);
        this.textField.setTextColorUneditable(-1);
        this.textField.setCanLoseFocus(true);
        this.textField.setMaxLength(64);
        this.textField.setVisible(false);
        this.addRenderableWidget(this.textField);

        updateTargetClassList();
    }

    private void syncBookState() {
        if (menu.getDeskType() == 2 || (menu.getDeskType() == 0 && guiFunc == 2)) {
            PacketDistributor.sendToServer(new C2SBookStatePayload(chapId, pageId));
        }
    }

    private void syncDeskGui() {
        if (menu.getDeskType() == 0) {
            PacketDistributor.sendToServer(new C2SDeskGuiPayload(guiFunc, radarZoomLv));
        }
    }

    @Override
    public void removed() {
        super.removed();
        syncBookState();
        syncDeskGui();
    }

    @Override
    public void containerTick() {
        super.containerTick();
        this.tickGUI++;
        if (this.tempCD > 0) {
            this.tempCD--;
        }

        if (this.textField != null) {
            if (this.guiFunc == 3 && (this.teamState == 1 || this.teamState == 3)) {
                this.textField.setVisible(true);
                this.textField.setEditable(true);
            } else {
                this.textField.setVisible(false);
                this.textField.setEditable(false);
            }
        }

        if ((chapId == 4 || chapId == 5) && entityTemp instanceof EntityShipBase ship) {
            ship.tickCount++;
            ship.tickEmotions();
            if (ship.getAttackTick() > 0) {
                ship.setAttackTick(ship.getAttackTick() - 1);
            }
            if (ship.isSprinting()) {
                ship.walkAnimation.update(0.8F, 0.4F);
            } else {
                ship.walkAnimation.setSpeed(0.0F);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        int mx = (int) ((mouseX - leftPos) * GUI_SCALE_INV);
        int my = (int) ((mouseY - topPos) * GUI_SCALE_INV);

        if (guiFunc == 1) {
            drawRadarHoverText(guiGraphics, mx, my, mouseX, mouseY);
        } else if (guiFunc == 2) {
            drawBookHoverText(guiGraphics, mx, my, mouseX, mouseY);
        }
    }

    private void drawPageButtons(GuiGraphics guiGraphics, int mx, int my) {
        if (chapId < 0 || chapId >= Values.PageLimit.length) return;

        if (mx >= 50 && mx <= 80 && my >= 180 && my <= 195 && pageId > 0) {
            guiGraphics.blit(BookRenderer.GUI_BOOK, 53, 182, 0, 192, 18, 10, 256, 256);
        } else if (mx >= 170 && mx <= 200 && my >= 180 && my <= 195 && pageId < Values.PageLimit[chapId]) {
            guiGraphics.blit(BookRenderer.GUI_BOOK, 175, 182, 0, 202, 18, 10, 256, 256);
        }
    }

    private void drawBookHoverText(GuiGraphics guiGraphics, int mx, int my, int mouseX, int mouseY) {
        if (mx >= 243 && mx <= 256 && my >= 34 && my <= 121) {
            int getbtn = (my - 34) / 12;
            if (getbtn >= 0 && getbtn < 7) {
                String strChap = Component.translatable("gui.shincolle.book.chap" + getbtn + ".title").getString();
                guiGraphics.renderTooltip(this.font, Component.literal(strChap), mouseX, mouseY);
                return;
            }
        }

        int bookID = chapId * 1000 + pageId;
        List<int[]> cont = Values.BookList.get(bookID);
        if (cont == null) return;

        drawBookItemTooltip(guiGraphics, cont, mx, my, mouseX, mouseY);
    }

    private void drawBookItemTooltip(GuiGraphics guiGraphics, List<int[]> cont, int mx, int my, int mouseX, int mouseY) {
        for (int[] getc : cont) {
            if (getc != null && getc.length >= 5 && getc[0] == 2) {
                int xa = (getc[1] == 1) ? (getc[2] + 132) : (getc[2] + 12);
                int ya = getc[3] + 48;
                if (mx > xa - 1 && mx < xa + 17 && my > ya - 1 && my < ya + 17) {
                    net.minecraft.world.item.ItemStack stack = Values.ItemIconMap.get((short)getc[4]);
                    if (stack != null && !stack.isEmpty()) {
                        guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                    }
                    return;
                }
            }
        }
    }

    private void drawRadarHoverText(GuiGraphics guiGraphics, int mx, int my, int mouseX, int mouseY) {
        List<Component> list = new ArrayList<>();
        for (RadarEntity obj : this.shipList) {
            if (obj != null && obj.ship != null && mx < obj.pixelx + 4.0 && mx > obj.pixelx - 2.0 && my < obj.pixelz + 4.0 && my > obj.pixelz - 2.0) {
                list.add(obj.ship.getName());
            }
        }
        if (!list.isEmpty()) {
            guiGraphics.renderComponentTooltip(this.font, list, mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(this.leftPos, this.topPos, 0);
        guiGraphics.pose().scale(GUI_SCALE, GUI_SCALE, 1.0f);

        if (menu.getDeskType() == 0) {
            drawBaseBackground(guiGraphics);
        }

        if (guiFunc == 1) {
            drawRadarBackground(guiGraphics);
            drawRadarIcon(guiGraphics);
            drawMoraleIcon(guiGraphics);
            drawRadarText(guiGraphics);
        } else if (guiFunc == 2) {
            renderBookBg(guiGraphics, partialTick, mouseX, mouseY);
        } else if (guiFunc == 3) {
            drawTeamBackground(guiGraphics);
            drawTeamText(guiGraphics);
        } else if (guiFunc == 4) {
            drawTargetBackground(guiGraphics, partialTick);
            drawTargetText(guiGraphics);
        }

        guiGraphics.pose().popPose();
    }

    private void renderBookBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        BookRenderer.drawBookBase(guiGraphics, 0, 0);
        BookRenderer.drawBookContent(guiGraphics, 0, 0, pageId, chapId);

        if (chapId == 4 || chapId == 5) {
            int classID = -1;
            if (pageId > 0) {
                if (chapId == 4 && pageId - 1 < Values.ShipBookList.size()) {
                    classID = Values.ShipBookList.get(pageId - 1);
                } else if (chapId == 5 && pageId - 1 < Values.EnemyBookList.size()) {
                    classID = Values.EnemyBookList.get(pageId - 1);
                }
            }

            if (pageId > 0) {
                if (!isCollected(classID)) {
                    entityTemp = null;
                    guiGraphics.blit(BookRenderer.GUI_BOOK2, 20, 48, 0, 148, 87, 108);
                } else {
                    int u = (chapId == 4) ? 0 : 105;
                    guiGraphics.blit(BookRenderer.GUI_BOOK2, 20, 48, u, 0, 87, 130);

                    updateEntityTemp();
                    updateModelTransforms();
                    renderBookEntity(guiGraphics, 0, 0, partialTick);
                    renderShipNameIcons(guiGraphics, 0, 0);
                    BookRenderer.drawStateFlags(guiGraphics, 0, 0, entityTemp);
                    drawNoText(guiGraphics, 0, 0);
                }
            }
        }

        drawPageButtons(guiGraphics, (int)((mouseX - leftPos) * GUI_SCALE_INV), (int)((mouseY - topPos) * GUI_SCALE_INV));
    }

    private void drawBaseBackground(GuiGraphics guiGraphics) {
        guiGraphics.blit(BookRenderer.GUI_DESK, 0, 0, 0, 0, 256, 192);
        if (guiFunc > 0) {
            int u = (guiFunc - 1) * 16;
            int xOffset = switch (guiFunc) {
                case 1 -> 3;
                case 2 -> 22;
                case 3 -> 41;
                case 4 -> 60;
                default -> 0;
            };
            if (u >= 0) {
                guiGraphics.blit(BookRenderer.GUI_DESK, xOffset, 2, u, 192, 16, 16);
            }
        }
    }

    private void drawRadarBackground(GuiGraphics guiGraphics) {
        guiGraphics.blit(BookRenderer.GUI_RADAR, 0, 0, 0, 0, 256, 192);
        int texty = 192 + (this.radarZoomLv * 8);
        guiGraphics.blit(BookRenderer.GUI_RADAR, 9, 160, 24, texty, 44, 8);
        for (int i = 0; i < 5; ++i) {
            int actualShipIndex = this.listNum[0] + i;
            if (actualShipIndex < this.shipList.size() && this.selectedShips.contains(actualShipIndex)) {
                guiGraphics.blit(BookRenderer.GUI_RADAR, 142, 25 + i * 32, 68, 192, 108, 31);
            }
        }
        if (!this.selectedShips.isEmpty()) {
            guiGraphics.blit(BookRenderer.GUI_RADAR, 88, 159, 24, 216, 44, 10);
        }
    }

    private void drawRadarIcon(GuiGraphics guiGraphics) {
        if (this.minecraft == null || this.minecraft.player == null) return;

        double ox = this.minecraft.player.getX();
        double oy = this.minecraft.player.getY();
        double oz = this.minecraft.player.getZ();
        float radarScale = (float) Math.pow(2.0, this.radarZoomLv);

        this.shipList.clear();

        int id = 0;
        for (Entity entity : this.minecraft.level.entitiesForRendering()) {
            if (entity instanceof EntityShipBase ship && ship.isAlive() && ship.getOwnerUUID() != null && ship.getOwnerUUID().equals(this.minecraft.player.getUUID())) {
                double px = (ship.getX() - ox) * radarScale;
                double py = ship.getY() - oy;
                double pz = (ship.getZ() - oz) * radarScale;
                px = Mth.clamp(px, -64.0, 64.0);
                pz = Mth.clamp(pz, -64.0, 64.0);

                RadarEntity getent = new RadarEntity(ship);
                getent.pixelx = 69 + px;
                getent.pixely = py;
                getent.pixelz = 88 + pz;
                this.shipList.add(getent);

                int color = this.selectedShips.contains(id) ? 0xFFFF0000 : 0xFFFFAFC9;
                guiGraphics.fill(69 + (int)px, 88 + (int)pz, 69 + (int)px + 3, 88 + (int)pz + 3, color);
                id++;
            }
        }
    }

    private void drawMoraleIcon(GuiGraphics guiGraphics) {
        int texty = 37;
        for (int i = 0; i < 5; i++) {
            int index = this.listNum[0] + i;
            if (index >= this.shipList.size()) break;

            RadarEntity s = this.shipList.get(index);
            if (s != null && s.ship instanceof EntityShipBase s2) {
                int ix = getMoraleLevel(s2.getMorale()) * 11;
                guiGraphics.blit(BookRenderer.GUI_NAME_ICON0, 237, texty - 1, ix, 240, 11, 11);
            }
            texty += 32;
        }
    }

    private int getMoraleLevel(int morale) {
        if (morale >= 12000) return 0;
        if (morale >= 5000) return 1;
        if (morale >= 2000) return 2;
        return 3;
    }

    private void drawRadarText(GuiGraphics guiGraphics) {
        int texty = 27;
        for (int i = 0; i < 5; i++) {
            int index = this.listNum[0] + i;
            if (index >= this.shipList.size()) {
                break;
            }

            RadarEntity s = this.shipList.get(index);
            if (s != null && s.ship instanceof EntityShipBase s2) {
                guiGraphics.drawString(this.font, s.ship.getName().getString(), 147, texty, 0xFFFFFF, false);

                String str = "LV " + ChatFormatting.YELLOW + s2.getLevel() + "   " + ChatFormatting.GOLD + (int)s2.getHealth() + ChatFormatting.RED + " / " + (int)s2.getMaxHealth();
                String str2 = "Pos: " + ChatFormatting.YELLOW + Mth.ceil(s.ship.getX()) + ", " + Mth.ceil(s.ship.getZ()) + "  H: " + ChatFormatting.YELLOW + (int)s.ship.getY();

                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(0.8f, 0.8f, 1.0f);
                guiGraphics.drawString(this.font, str, (int)(147 / 0.8f), (int)((texty + 12) / 0.8f), 0xFF00FFFF, false);
                guiGraphics.drawString(this.font, str2, (int)(147 / 0.8f), (int)((texty + 21) / 0.8f), 0xFFA000A0, false);
                guiGraphics.pose().popPose();

                texty += 32;
            }
        }
    }

    private void drawTeamBackground(GuiGraphics guiGraphics) {
        guiGraphics.blit(GUI_TEAM, 0, 0, 0, 0, 256, 192);
        int cirY;
        if (this.listFocus == 1 && this.listClicked[1] > -1 && this.listClicked[1] < 5) {
            cirY = 25 + this.listClicked[1] * 32;
            guiGraphics.blit(GUI_TEAM, 142, cirY, 0, 192, 108, 31);
        } else if (this.listFocus == 3 && this.listClicked[3] > -1 && this.listClicked[3] < 3) {
            cirY = 61 + this.listClicked[3] * 31;
            guiGraphics.blit(GUI_TEAM, 6, cirY, 109, 192, 129, 31);
        } else if (this.listFocus == 4 && this.listClicked[4] > -1 && this.listClicked[4] < 3) {
            cirY = 61 + this.listClicked[4] * 31;
            guiGraphics.blit(GUI_TEAM, 6, cirY, 109, 192, 129, 31);
        }
    }

    private void drawTeamText(GuiGraphics guiGraphics) {
        if (this.minecraft == null || this.minecraft.player == null) return;
        drawTeamInfoBar(guiGraphics);
        drawTeamActionButtons(guiGraphics);
        drawTeamList(guiGraphics);
        drawAllyOrBanList(guiGraphics);
    }

    private boolean hasTeam() {
        if (this.minecraft == null || this.minecraft.player == null) return false;
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
        return getTeamData(pUID) != null;
    }

    private TeamData getTeamData(int uid) {
        for (TeamData t : clientTeams) {
            if (t.getTeamID() == uid) return t;
        }
        return null;
    }

    private boolean isTeamAlly(int targetTeamID) {
        if (this.minecraft == null || this.minecraft.player == null) return false;
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
        TeamData t = getTeamData(pUID);
        return t != null && t.getTeamAllyList().contains(targetTeamID);
    }

    private boolean isTeamBanned(int targetTeamID) {
        if (this.minecraft == null || this.minecraft.player == null) return false;
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
        TeamData t = getTeamData(pUID);
        return t != null && t.getTeamBannedList().contains(targetTeamID);
    }

    private void drawTeamInfoBar(GuiGraphics guiGraphics) {
        if (this.minecraft == null || this.minecraft.player == null) return;
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
        TeamData tdata = getTeamData(pUID);
        if (tdata != null) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
            String str = ChatFormatting.GRAY + Component.translatable("gui.shincolle.team.teamid").getString() + ":  " + ChatFormatting.YELLOW + pUID + " : " + ChatFormatting.LIGHT_PURPLE + tdata.getTeamLeaderName();
            guiGraphics.drawString(this.font, str, 11, 34, 0, false);
            guiGraphics.drawString(this.font, ChatFormatting.WHITE + tdata.getTeamName(), 11, 44, 0, false);
            guiGraphics.pose().popPose();
        }
    }

    private void drawTeamActionButtons(GuiGraphics guiGraphics) {
        String strLT = null;
        String strLB = null;
        String strRT = null;
        String strRB = null;
        int colorLT = 0xFFFFFF;
        int colorLB = colorLT;
        int colorRT = colorLT;
        int colorRB = colorLT;

        boolean inTeam = hasTeam();

        switch (this.teamState) {
            case 0 -> {
                if (inTeam) {
                    strLT = Component.translatable("gui.shincolle.team.allylist").getString(); colorLT = 0x00FFFF;
                    strLB = Component.translatable("gui.shincolle.team.banlist").getString(); colorLB = 0xFFFF00;
                    if (this.tempCD > 0) {
                        strRT = String.valueOf(this.tempCD / 20); colorRT = 0xC0C0C0;
                    } else {
                        strRT = Component.translatable("gui.shincolle.team.rename").getString();
                    }
                    strRB = Component.translatable("gui.shincolle.team.disband").getString(); colorRB = 0x555555;
                } else {
                    strRB = Component.translatable("gui.shincolle.team.create").getString(); colorRB = 0x00FFFF;
                }
            }
            case 1, 3 -> {
                strLT = Component.translatable("gui.shincolle.general.cancel").getString(); colorLT = 0xC0C0C0;
                strLB = Component.translatable("gui.shincolle.general.ok").getString();
                if (this.teamState == 1 && this.minecraft != null && this.minecraft.player != null) {
                    int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
                    guiGraphics.drawString(this.font, ChatFormatting.WHITE + Component.translatable("gui.shincolle.team.teamid").getString() + "  " + ChatFormatting.YELLOW + pUID, 10, 43, 0, false);
                }
            }
            case 2, 4 -> {
                strLB = Component.translatable("gui.shincolle.general.ok").getString();
                if (this.tempCD > 0) {
                    strLT = String.valueOf(this.tempCD / 20); colorLT = 0xC0C0C0;
                } else {
                    ButtonInfo info = getTeamActionButtonInfo();
                    strLT = info.text;
                    colorLT = info.color;
                }
            }
        }

        drawCenteredString(guiGraphics, strLT, 31, 160, colorLT);
        drawCenteredString(guiGraphics, strLB, 31, 174, colorLB);
        drawCenteredString(guiGraphics, strRT, 110, 160, colorRT);
        drawCenteredString(guiGraphics, strRB, 110, 174, colorRB);
    }

    private void drawCenteredString(GuiGraphics guiGraphics, String text, int x, int y, int color) {
        if (text != null) {
            guiGraphics.drawString(this.font, text, x - this.font.width(text) / 2, y, color, false);
        }
    }

    private record ButtonInfo(String text, int color) {}

    private ButtonInfo getTeamActionButtonInfo() {
        if (this.minecraft == null || this.minecraft.player == null) return new ButtonInfo(null, 0);
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);

        int clicki;
        if (this.listFocus == 1 && (clicki = this.listClicked[1] + this.listNum[1]) >= 0 && clicki < clientTeams.size()) {
            TeamData getd = clientTeams.get(clicki);
            if (getd != null && pUID != getd.getTeamID()) {
                if (this.teamState == 2) {
                    return isTeamAlly(getd.getTeamID()) ?
                            new ButtonInfo(Component.translatable("gui.shincolle.team.break").getString(), 0xFFFF00) :
                            new ButtonInfo(Component.translatable("gui.shincolle.team.ally").getString(), 0x00FFFF);
                }
                if (this.teamState == 4) {
                    return isTeamBanned(getd.getTeamID()) ?
                            new ButtonInfo(Component.translatable("gui.shincolle.team.unban").getString(), 0x00FFFF) :
                            new ButtonInfo(Component.translatable("gui.shincolle.team.ban").getString(), 0xFFFF00);
                }
            }
        } else if (this.listFocus == 3 && this.teamState == 2) {
            return new ButtonInfo(Component.translatable("gui.shincolle.team.break").getString(), 0xFFFF00);
        } else if (this.listFocus == 4 && this.teamState == 4) {
            return new ButtonInfo(Component.translatable("gui.shincolle.team.unban").getString(), 0x00FFFF);
        }
        return new ButtonInfo(null, 0);
    }

    private void drawTeamList(GuiGraphics guiGraphics) {
        if (this.minecraft == null || this.minecraft.player == null) return;
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);

        int texty = 33;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
        for (int i = 0; i < 5; ++i) {
            int index = this.listNum[1] + i;
            if (index >= clientTeams.size()) break;
            TeamData tdata2 = clientTeams.get(index);
            if (tdata2 != null) {
                String allyInfo = ChatFormatting.WHITE + "(" + Component.translatable("gui.shincolle.team.neutral").getString() + ")";
                if (pUID == tdata2.getTeamID()) allyInfo = ChatFormatting.GOLD + "(" + Component.translatable("gui.shincolle.team.belong").getString() + ")";
                else if (isTeamAlly(tdata2.getTeamID())) allyInfo = ChatFormatting.AQUA + "(" + Component.translatable("gui.shincolle.team.allied").getString() + ")";
                else if (isTeamBanned(tdata2.getTeamID())) allyInfo = ChatFormatting.RED + "(" + Component.translatable("gui.shincolle.team.hostile").getString() + ")";

                String str = ChatFormatting.YELLOW + "" + tdata2.getTeamID() + " : " + ChatFormatting.LIGHT_PURPLE + tdata2.getTeamLeaderName() + "  " + allyInfo;
                guiGraphics.drawString(this.font, str, 181, texty, 0xFFFFFF, false);
                guiGraphics.drawString(this.font, tdata2.getTeamName(), 181, texty + 9, 0xFFFFFF, false);
            }
            texty += 40;
        }
        guiGraphics.pose().popPose();
    }

    private void drawAllyOrBanList(GuiGraphics guiGraphics) {
        if (this.minecraft == null || this.minecraft.player == null) return;
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
        TeamData tdata = getTeamData(pUID);
        if (tdata == null || (this.teamState != 2 && this.teamState != 4)) return;

        List<Integer> tlist3 = (this.teamState == 2) ? tdata.getTeamAllyList() : tdata.getTeamBannedList();
        int listID = (this.teamState == 2) ? 3 : 4;
        if (tlist3 == null) return;

        int texty = 79;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
        for (int i = 0; i < 3; ++i) {
            int index = this.listNum[listID] + i;
            if (index >= tlist3.size()) break;

            TeamData tdata3 = getTeamData(tlist3.get(index));
            if (tdata3 != null) {
                String allyInfo = isTeamAlly(tdata3.getTeamID()) ?
                        ChatFormatting.AQUA + "(" + Component.translatable("gui.shincolle.team.allied").getString() + ")" :
                        ChatFormatting.RED + "(" + Component.translatable("gui.shincolle.team.hostile").getString() + ")";
                String str = ChatFormatting.GRAY + Component.translatable("gui.shincolle.team.teamid").getString() + ":  " + ChatFormatting.YELLOW + tdata3.getTeamID() + " : " + ChatFormatting.LIGHT_PURPLE + tdata3.getTeamLeaderName() + "  " + allyInfo;
                guiGraphics.drawString(this.font, str, 11, texty, 0, false);
                guiGraphics.drawString(this.font, tdata3.getTeamName(), 11, texty + 9, 0xFFFFFF, false);
            }
            texty += 39;
        }
        guiGraphics.pose().popPose();
    }

    private void drawTargetBackground(GuiGraphics guiGraphics, float partialTick) {
        guiGraphics.blit(GUI_TARGET, 0, 0, 0, 0, 256, 192);
        if (this.listClicked[2] > -1 && this.listClicked[2] < 13) {
            int cirY = 25 + this.listClicked[2] * 12;
            guiGraphics.blit(GUI_TARGET, 142, cirY, 68, 192, 108, 31);
        }
        updateModelTransforms();
        drawTargetModel(guiGraphics, partialTick);
    }

    private void drawTargetModel(GuiGraphics guiGraphics, float partialTick) {
        if (this.targetEntity != null) {
            float renderScale = this.prevScale + (this.currentScale - this.prevScale) * partialTick;
            float rotX = this.prevRotateX + (this.currentRotateX - this.prevRotateX) * partialTick;
            float rotY = this.prevRotateY + (this.currentRotateY - this.prevRotateY) * partialTick;

            int px = 72;
            int py = 136;

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(px, py, 50.0f);
            guiGraphics.pose().scale(-renderScale, renderScale, renderScale);
            guiGraphics.pose().mulPose(new org.joml.Quaternionf().rotateZ((float)Math.toRadians(180)));
            guiGraphics.pose().mulPose(new org.joml.Quaternionf().rotateY((float)Math.toRadians(135)));
            guiGraphics.pose().mulPose(new org.joml.Quaternionf().rotateY((float)Math.toRadians(rotY - 135)));
            guiGraphics.pose().mulPose(new org.joml.Quaternionf().rotateX((float)Math.toRadians(rotX)));

            net.minecraft.client.renderer.entity.EntityRenderDispatcher dispatcher = this.minecraft.getEntityRenderDispatcher();
            dispatcher.setRenderShadow(false);
            RenderSystem.runAsFancy(() -> {
                dispatcher.render(this.targetEntity, 0.0, 0.0, 0.0, 0.0f, 1.0f, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880);
            });
            guiGraphics.flush();
            dispatcher.setRenderShadow(true);

            guiGraphics.pose().popPose();
        }
    }

    private void drawTargetText(GuiGraphics guiGraphics) {
        String removeStr = Component.translatable("gui.shincolle.target.remove").getString();
        drawCenteredString(guiGraphics, removeStr, 31, 160, 0xFFFFFF);
        int texty = 28;
        for (int i = 0; i < 13; i++) {
            int index = this.listNum[2] + i;
            if (index >= this.tarList.size()) break;
            String str = this.tarList.get(index);
            if (str != null) {
                guiGraphics.drawString(this.font, str, 146, texty, 0xFFFFFF, false);
            }
            texty += 12;
        }
    }

    private void updateTargetClassList() {
        this.tarList.clear();
        if (this.minecraft != null && this.minecraft.player != null) {
            AdmiralData data = this.minecraft.player.getData(ModDataAttachments.ADMIRAL_DATA);
            if (data != null) {
                this.tarList.addAll(data.getCustomTargetClasses());
            }
        }
    }

    private void getEntityByClick() {
        int clicked = this.listClicked[2] + this.listNum[2];
        if (clicked < 0 || clicked >= this.tarList.size()) {
            this.targetEntity = null;
            return;
        }
        String tarStr = this.tarList.get(clicked);
        if (tarStr == null || this.minecraft == null || this.minecraft.level == null) {
            this.targetEntity = null;
            return;
        }

        EntityType<?> foundType = null;
        for (EntityType<?> type : BuiltInRegistries.ENTITY_TYPE) {
            if (type.getBaseClass().getSimpleName().equalsIgnoreCase(tarStr) ||
                BuiltInRegistries.ENTITY_TYPE.getKey(type).getPath().equalsIgnoreCase(tarStr) ||
                BuiltInRegistries.ENTITY_TYPE.getKey(type).toString().equalsIgnoreCase(tarStr)) {
                foundType = type;
                break;
            }
        }

        if (foundType != null) {
            this.targetEntity = foundType.create(this.minecraft.level);
            this.currentRotateX = 0f;
            this.currentRotateY = -30f;
            this.targetRotateX = 0f;
            this.targetRotateY = -30f;
            this.currentScale = 30f;
            this.targetScale = 30f;
            this.prevRotateX = 0f;
            this.prevRotateY = -30f;
            this.prevScale = 30f;
        } else {
            this.targetEntity = null;
        }
    }

    private void drawNoText(GuiGraphics guiGraphics, int x, int y) {
        if (pageId > 0 && entityTemp instanceof EntityShipBase) {
            String str = "No. " + pageId;
            int color = (chapId == 4) ? 0xAA0000 : 0x00AAAA;
            guiGraphics.drawString(this.font, str, x + 55, y + 32, color, false);
        }
    }

    private void renderShipNameIcons(GuiGraphics guiGraphics, int x, int y) {
        if (!(entityTemp instanceof EntityShipBase ship)) return;

        int shipType = ship.getStateComponent().getFactionId();
        int[] typeXY = Values.ShipTypeIconMap.get((byte)shipType);

        int shipClass = ship.getStateComponent().getShipClassId();
        int[] classXY = Values.ShipNameIconMap.get(shipClass);
        if (classXY == null) return;

        if (typeXY != null) {
            RenderSystem.setShaderTexture(0, BookRenderer.GUI_NAME_ICON0);
            guiGraphics.blit(BookRenderer.GUI_NAME_ICON0, x + 23, y + 53, typeXY[0], typeXY[1], 28, 28, 256, 256);
        }

        ResourceLocation iconTexture = BookRenderer.GUI_NAME_ICON1;
        if (classXY[0] >= 101) iconTexture = BookRenderer.GUI_NAME_ICON2;

        int offy = 0;
        if (classXY[0] == 4 || classXY[0] == 6) offy = -10;
        else if (classXY[0] >= 101) offy = 10;

        RenderSystem.setShaderTexture(0, iconTexture);
        guiGraphics.blit(iconTexture, x + 30, y + 94 + offy, classXY[1], classXY[2], 11, 59, 256, 256);
    }

    private void updateEntityTemp() {
        int classID = -1;
        if (pageId > 0) {
            if (chapId == 4 && pageId - 1 < Values.ShipBookList.size()) {
                classID = Values.ShipBookList.get(pageId - 1);
            } else if (chapId == 5 && pageId - 1 < Values.EnemyBookList.size()) {
                classID = Values.EnemyBookList.get(pageId - 1);
            }
        }

        if (classID < 0) {
            entityTemp = null;
            return;
        }

        EntityType<?> type = getEntityTypeFromClassID(classID);
        if (type == null) {
            entityTemp = null;
            return;
        }

        if ((entityTemp == null || entityTemp.getType() != type) && this.minecraft != null && this.minecraft.level != null) {
            entityTemp = (LivingEntity) type.create(this.minecraft.level);
            if (entityTemp instanceof EntityShipBase ship) {
                ship.setLevel(1);
                ship.setAmmoLight(100);
                ship.setAmmoHeavy(100);
                ship.setFuel(100);
            }
            this.currentRotateX = 0f;
            this.currentRotateY = 0f;
            this.targetRotateX = 0f;
            this.targetRotateY = 0f;
            this.currentScale = 30f;
            this.targetScale = 30f;
            this.prevRotateX = 0f;
            this.prevRotateY = 0f;
            this.prevScale = 30f;
        }
    }

    private EntityType<?> getEntityTypeFromClassID(int classID) {
        Supplier<? extends EntityType<?>> supplier = CLASS_ID_TO_ENTITY_TYPE.get(classID);
        return supplier != null ? supplier.get() : null;
    }

    private void handleBookModelControls(int btn) {
        if (!(entityTemp instanceof EntityShipBase ship)) return;

        switch (btn) {
            case 1 -> {
                ship.setOrderedToSit(!ship.isOrderedToSit());
                ship.setStateEmotion(1, ship.getRandom().nextInt(2) == 0 ? 4 : 0, false);
                ship.setStateEmotion(7, ship.getRandom().nextInt(2) == 0 ? 4 : 0, false);
            }
            case 2 -> {
                ship.setSprinting(!ship.isSprinting());
            }
            case 3 -> {
                ship.setAttackTick(50);
                ship.setStateEmotion(5, ship.getRandom().nextInt(4), false);
            }
            case 4 -> {
                ship.setStateEmotion(7, ship.getRandom().nextInt(2) == 0 ? 4 : 0, false);
                ship.setShiftKeyDown(ship.getRandom().nextInt(5) == 0);
                ship.setStateNoEquip(ship.getRandom().nextInt(8) == 0);
                ship.setStateEmotion(1, ship.getRandom().nextInt(10), false);
            }
            default -> {
                if (btn >= 5 && btn <= 20) {
                    int bit = btn - 5;
                    int stats = ship.getStateEmotion(0);
                    boolean newValue = ((stats >> bit) & 1) == 0;
                    ship.setStateEmotion(0, stats ^ (1 << bit), false);

                    var options = ship.getEquipOptions();
                    if (bit < options.size()) {
                        ship.setEquipFlag(options.get(bit).key(), newValue);
                    }
                }
            }
        }
    }

    private boolean isCollected(int classID) {
        if (classID < 0) return false;
        if (this.minecraft == null || this.minecraft.player == null) return false;
        return this.minecraft.player.getData(ModDataAttachments.COLLECTED_SHIPS).contains(classID);
    }

    private void renderBookEntity(GuiGraphics guiGraphics, int x, int y, float partialTick) {
        if (entityTemp != null) {
            float renderScale = this.prevScale + (this.currentScale - this.prevScale) * partialTick;
            float rotX = this.prevRotateX + (this.currentRotateX - this.prevRotateX) * partialTick;
            float rotY = this.prevRotateY + (this.currentRotateY - this.prevRotateY) * partialTick;
            float baseYaw = entityTemp.getType() == ModEntities.DESTROYER_I.get() ? -120.0f : -30.0f;

            int px = x + 72;
            int py = y + 110 + (int)(renderScale * 1.1f);

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(px, py, 50.0f);
            guiGraphics.pose().scale(-renderScale, renderScale, renderScale);
            guiGraphics.pose().mulPose(new org.joml.Quaternionf().rotateZ((float)Math.toRadians(180)));
            guiGraphics.pose().translate(0.0f, 0.7f, 0.0f);
            guiGraphics.pose().mulPose(new org.joml.Quaternionf().rotateY((float)Math.toRadians(rotY + baseYaw)));
            guiGraphics.pose().mulPose(new org.joml.Quaternionf().rotateX((float)Math.toRadians(rotX)));

            guiGraphics.pose().translate(0.0D, -0.7D, 0.0D);

            net.minecraft.client.renderer.entity.EntityRenderDispatcher dispatcher = this.minecraft.getEntityRenderDispatcher();
            dispatcher.setRenderShadow(false);
            RenderSystem.runAsFancy(() -> {
                if (entityTemp.getVehicle() instanceof EntityMountBase mount) {
                    float[] seatPos = mount.getSeatPos();
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().translate(seatPos[2], seatPos[1], seatPos[0]);
                    dispatcher.render(entityTemp, 0.0, 0.0, 0.0, 0.0f, 1.0f, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880);
                    guiGraphics.pose().popPose();
                    dispatcher.render(mount, 0.0, 0.0, 0.0, 0.0f, 1.0f, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880);
                } else {
                    dispatcher.render(entityTemp, 0.0, 0.0, 0.0, 0.0f, 1.0f, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880);
                }
            });
            guiGraphics.flush();
            dispatcher.setRenderShadow(true);

            guiGraphics.pose().popPose();
        }
    }

    private void updateModelTransforms() {
        this.prevRotateX = this.currentRotateX;
        this.prevRotateY = this.currentRotateY;
        this.prevScale = this.currentScale;
        float smoothingFactor = 0.7F;
        this.currentRotateX += (this.targetRotateX - this.currentRotateX) * smoothingFactor;
        this.currentRotateY += (this.targetRotateY - this.currentRotateY) * smoothingFactor;
        this.currentScale += (this.targetScale - this.currentScale) * smoothingFactor;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        double mx = (mouseX - leftPos) * GUI_SCALE_INV;
        double my = (mouseY - topPos) * GUI_SCALE_INV;

        if ((guiFunc == 4 || (guiFunc == 2 && (chapId == 4 || chapId == 5))) && mx > 8 && mx < 135 && my > 20 && my < 158) {
            if (dragX != 0) this.targetRotateY += (float)dragX * 3.0f;
            if (dragY != 0) this.targetRotateX = Mth.clamp(this.targetRotateX + (float)dragY * 2.0f, -90.0f, 90.0f);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        double mx = (mouseX - leftPos) * GUI_SCALE_INV;
        double my = (mouseY - topPos) * GUI_SCALE_INV;

        if (menu.getDeskType() == 1 && guiFunc == 1) {
            scrollList(0, scrollY > 0);
            return true;
        }

        if (guiFunc == 3) {
            int listID = -1;
            if (mx > 138) {
                listID = 1;
            } else if (this.teamState == 2) {
                listID = 3;
            } else if (this.teamState == 4) {
                listID = 4;
            }
            if (listID != -1) {
                scrollList(listID, scrollY > 0);
                return true;
            }
        }

        if ((guiFunc == 4 || (guiFunc == 2 && (chapId == 4 || chapId == 5))) && mx > 8 && mx < 135 && my > 20 && my < 158) {
            float change = scrollY > 0 ? 4.0f : -4.0f;
            if (guiFunc == 2) change /= 1.7f;
            this.targetScale = Mth.clamp(this.targetScale + change, 5.0f, 200.0f);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    private void scrollList(int listID, boolean isUp) {
        int listSize = getListSize(listID);
        if (listSize <= 0) return;

        if (isUp) {
            if (this.listNum[listID] > 0) {
                this.listNum[listID]--;
                this.listClicked[listID]++;
            }
        } else {
            if (this.listNum[listID] < listSize - 1) {
                this.listNum[listID]++;
                this.listClicked[listID]--;
            }
        }
        this.listNum[listID] = Math.max(0, this.listNum[listID]);
    }

    private int getListSize(int listID) {
        switch (listID) {
            case 0 -> { return this.shipList.size(); }
            case 1 -> { return clientTeams.size(); }
            case 2 -> { return this.tarList.size(); }
            case 3 -> {
                if (this.minecraft == null || this.minecraft.player == null) return 0;
                int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
                TeamData t = getTeamData(pUID);
                return t != null ? t.getTeamAllyList().size() : 0;
            }
            case 4 -> {
                if (this.minecraft == null || this.minecraft.player == null) return 0;
                int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
                TeamData t = getTeamData(pUID);
                return t != null ? t.getTeamBannedList().size() : 0;
            }
            default -> { return 0; }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Labels are not rendered in this screen
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int mx = (int)((mouseX - leftPos) * GUI_SCALE_INV);
        int my = (int)((mouseY - topPos) * GUI_SCALE_INV);
        this.lastXMouse = mx;
        this.lastYMouse = my;

        if (menu.getDeskType() == 0 && my >= 2 && my <= 18) {
            if (handleTopTabClicks(mx)) return true;
        }

        if (guiFunc == 1) {
            if (handleRadarClicks(mx, my)) return true;
        }

        if (guiFunc == 2) {
            if (handleBookClicks(mx, my, button)) return true;
        }

        if (guiFunc == 3) {
            if (handleTeamClicks(mx, my)) return true;
        }

        if (guiFunc == 4) {
            if (handleTargetClicks(mx, my)) return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean handleTopTabClicks(int mx) {
        if (mx >= 3 && mx <= 19) { setDeskFunction(1); return true; }
        if (mx >= 22 && mx <= 38) { setDeskFunction(2); return true; }
        if (mx >= 41 && mx <= 57) { setDeskFunction(3); return true; }
        if (mx >= 60 && mx <= 76) { setDeskFunction(4); return true; }
        return false;
    }

    private boolean handleRadarClicks(int mx, int my) {
        if (mx >= 9 && mx <= 53 && my >= 160 && my <= 168) {
            this.radarZoomLv = (this.radarZoomLv + 1) % 3;
            syncDeskGui();
            return true;
        }
        if (mx >= 142 && mx <= 250) {
            for (int i = 0; i < 5; i++) {
                int ry = 25 + i * 32;
                if (my >= ry && my <= ry + 31) {
                    int index = this.listNum[0] + i;
                    if (index < this.shipList.size()) {
                        if (hasShiftDown()) {
                            if (this.selectedShips.contains(index)) {
                                this.selectedShips.remove(Integer.valueOf(index));
                            } else {
                                this.selectedShips.add(index);
                            }
                        } else {
                            this.selectedShips.clear();
                            this.selectedShips.add(index);
                        }
                    }
                    return true;
                }
            }
        }
        if (!this.selectedShips.isEmpty() && mx >= 88 && mx <= 132 && my >= 159 && my <= 169) {
            handleSummonSelectedShips();
            return true;
        }
        this.selectedShips.clear();
        return true;
    }

    private boolean handleBookClicks(int mx, int my, int button) {
        if (my >= 180 && my <= 195) {
            if (mx >= 50 && mx <= 80) {
                if (pageId > 0) {
                    pageId -= (button == 1 ? 10 : 1);
                    if (pageId < 0) pageId = 0;
                    syncBookState();
                }
                return true;
            }
            if (mx >= 170 && mx <= 200) {
                if (chapId >= 0 && chapId < Values.PageLimit.length && pageId < Values.PageLimit[chapId]) {
                    pageId += (button == 1 ? 10 : 1);
                    if (pageId > Values.PageLimit[chapId]) pageId = Values.PageLimit[chapId];
                    syncBookState();
                }
                return true;
            }
        }

        if (mx >= 243 && mx <= 256 && my >= 34 && my <= 121) {
            int getbtn = (my - 34) / 12;
            if (getbtn >= 0 && getbtn < 7) {
                this.chapId = getbtn;
                this.pageId = 0;
                syncBookState();
                syncDeskGui();
                return true;
            }
        }

        if (entityTemp != null && (chapId == 4 || chapId == 5)) {
            if (mx >= 22 && mx <= 30) {
                if (my >= 158 && my <= 166) { handleBookModelControls(1); return true; }
                if (my >= 169 && my <= 177) { handleBookModelControls(3); return true; }
            }
            if (mx >= 33 && mx <= 41) {
                if (my >= 158 && my <= 166) { handleBookModelControls(2); return true; }
                if (my >= 169 && my <= 177) { handleBookModelControls(4); return true; }
            }
            int drawIdx = 0;
            int startIdx = (entityTemp instanceof EntityShipBase ship && ship.hasShipMounts()) ? 1 : 0;
            for (int i = startIdx; i < 16; i++) {
                if (i >= ((EntityShipBase)entityTemp).getStateComponent().getRarity()) break;
                int dx = 45 + (drawIdx % 8) * 9;
                int dy = 158 + (drawIdx / 8) * 9;
                if (mx >= dx && mx <= dx + 7 && my >= dy && my <= dy + 9) {
                    handleBookModelControls(5 + i);
                    return true;
                }
                drawIdx++;
            }
        }
        return false;
    }

    private boolean handleTeamClicks(int mx, int my) {
        int teamBtn = getDeskButton(3, mx, my);
        switch (teamBtn) {
            case 0, 6, 7, 8 -> {
                handleClickTeamState(teamBtn);
                return true;
            }
            case 1, 2, 3, 4, 5 -> {
                this.listFocus = 1;
                this.listClicked[1] = teamBtn - 1;
                return true;
            }
            case 9, 10, 11 -> {
                if (this.teamState == 2) {
                    this.listFocus = 3;
                    this.listClicked[3] = teamBtn - 9;
                    return true;
                } else if (this.teamState == 4) {
                    this.listFocus = 4;
                    this.listClicked[4] = teamBtn - 9;
                    return true;
                }
            }
        }
        return false;
    }

    private void handleClickTeamState(int btn) {
        switch (this.teamState) {
            case 0 -> handleClickTeamMain(btn);
            case 1 -> handleClickTeamCreate(btn);
            case 2 -> handleClickTeamAlly(btn);
            case 3 -> handleClickTeamRename(btn);
            case 4 -> handleClickTeamBan(btn);
        }
    }

    private void handleClickTeamMain(int btn) {
        boolean inTeam = hasTeam();
        switch (btn) {
            case 0 -> { if (inTeam) this.teamState = 2; }
            case 6 -> { if (inTeam) this.teamState = 4; }
            case 7 -> { if (this.tempCD <= 0 && inTeam) this.teamState = 3; }
            case 8 -> {
                if (inTeam) {
                    PacketDistributor.sendToServer(new C2STeamActionPayload(2, 0, ""));
                    this.teamState = 0;
                    this.tempCD = 60;
                } else {
                    this.teamState = 1;
                }
            }
        }
    }

    private void handleClickTeamCreate(int btn) {
        if (btn == 0) { this.teamState = 0; return; }
        if (btn == 6) {
            String str = this.textField.getValue();
            if (!hasTeam() && str != null && str.length() > 1) {
                PacketDistributor.sendToServer(new C2STeamActionPayload(0, 0, str));
                this.teamState = 0;
                this.tempCD = 60;
            }
        }
    }

    private void handleClickTeamRename(int btn) {
        if (btn == 0) { this.teamState = 0; return; }
        if (btn == 6) {
            String str = this.textField.getValue();
            if (hasTeam() && str != null && str.length() > 1) {
                PacketDistributor.sendToServer(new C2STeamActionPayload(1, 0, str));
                this.teamState = 0;
                this.tempCD = 60;
            }
        }
    }

    private void handleClickTeamAlly(int btn) {
        if (btn == 6) { this.teamState = 0; return; }
        if (btn == 0 && this.tempCD <= 0) {
            int getTeamID = getSelectedTeamID();
            if (getTeamID > 0) {
                int action = isTeamAlly(getTeamID) ? 4 : 3;
                PacketDistributor.sendToServer(new C2STeamActionPayload(action, getTeamID, ""));
                this.tempCD = 60;
            }
        }
    }

    private void handleClickTeamBan(int btn) {
        if (btn == 6) { this.teamState = 0; return; }
        if (btn == 0 && this.tempCD <= 0) {
            int getTeamID = getSelectedTeamID();
            if (getTeamID > 0) {
                int action = isTeamBanned(getTeamID) ? 6 : 5;
                PacketDistributor.sendToServer(new C2STeamActionPayload(action, getTeamID, ""));
                this.tempCD = 60;
            }
        }
    }

    private int getSelectedTeamID() {
        if (this.minecraft == null || this.minecraft.player == null) return 0;
        int pUID = TeamSavedData.getPlayerUID(this.minecraft.player);
        int clicki;

        if (this.listFocus == 1) {
            clicki = this.listClicked[1] + this.listNum[1];
            if (clicki >= 0 && clicki < clientTeams.size()) {
                TeamData getd = clientTeams.get(clicki);
                if (getd != null) return getd.getTeamID();
            }
        } else if (this.listFocus == 3) {
            TeamData myTeam = getTeamData(pUID);
            clicki = this.listClicked[3] + this.listNum[3];
            if (myTeam != null && clicki >= 0 && clicki < myTeam.getTeamAllyList().size()) {
                return myTeam.getTeamAllyList().get(clicki);
            }
        } else if (this.listFocus == 4) {
            TeamData myTeam = getTeamData(pUID);
            clicki = this.listClicked[4] + this.listNum[4];
            if (myTeam != null && clicki >= 0 && clicki < myTeam.getTeamBannedList().size()) {
                return myTeam.getTeamBannedList().get(clicki);
            }
        }
        return 0;
    }

    private boolean handleTargetClicks(int mx, int my) {
        updateTargetClassList();
        int targetBtn = getDeskButton(4, mx, my);
        if (targetBtn == 0) {
            int clicked = this.listNum[2] + this.listClicked[2];
            if (clicked >= 0 && clicked < this.tarList.size()) {
                String tarstr = this.tarList.get(clicked);
                PacketDistributor.sendToServer(new C2STargetClassTogglePayload(tarstr));
            }
            return true;
        } else if (targetBtn >= 1 && targetBtn <= 13) {
            this.listClicked[2] = targetBtn - 1;
            this.getEntityByClick();
            return true;
        }
        return false;
    }

    private void setDeskFunction(int func) {
        if (this.guiFunc == func) {
            this.guiFunc = 0;
        } else {
            this.guiFunc = func;
        }
        syncDeskGui();
    }

    private void handleSummonSelectedShips() {
        if (this.selectedShips.isEmpty()) return;

        List<java.util.UUID> uuids = new ArrayList<>();
        for (int index : this.selectedShips) {
            if (index >= 0 && index < this.shipList.size()) {
                Entity e = this.shipList.get(index).ship;
                if (e != null) {
                    uuids.add(e.getUUID());
                }
            }
        }

        if (!uuids.isEmpty()) {
            net.minecraft.core.BlockPos pos;
            if (menu.getDeskType() == 0 && menu.getBlockEntity() != null) {
                pos = menu.getBlockEntity().getBlockPos();
            } else {
                pos = this.minecraft.player.blockPosition();
            }
            boolean isItem = (menu.getDeskType() == 1);
            PacketDistributor.sendToServer(new C2SDeskSummonPayload(pos, uuids, isItem));
            this.minecraft.player.closeContainer();
        }
    }

    private static class RadarEntity {
        final Entity ship;
        double pixelx;
        double pixely;
        double pixelz;

        RadarEntity(Entity ship) {
            this.ship = ship;
        }
    }
}
