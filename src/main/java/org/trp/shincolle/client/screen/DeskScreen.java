package org.trp.shincolle.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import org.trp.shincolle.client.renderer.BookRenderer;
import org.trp.shincolle.entity.base.EntityMountBase;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.menu.DeskMenu;
import org.trp.shincolle.network.C2SBookStatePayload;
import org.trp.shincolle.network.C2SDeskGuiPayload;
import org.trp.shincolle.network.C2SDeskSummonPayload;
import org.trp.shincolle.reference.Values;

public class DeskScreen extends AbstractContainerScreen<DeskMenu> {

    private static final float GUI_SCALE = 1.25f;
    private static final float GUI_SCALE_INV = 1.0f / GUI_SCALE;
    
    private int pageId = 0;
    private int chapId = 0;
    private LivingEntity entityTemp = null;
    private float targetRotateX = 0f;
    private float targetRotateY = 0f;
    private float currentRotateX = 0f;
    private float currentRotateY = 0f;
    private float prevRotateX = 0f;
    private float prevRotateY = 0f;
    private float targetScale = 30f;
    private float currentScale = 30f;
    private float prevScale = 30f;
    private double lastXMouse = 0;
    private double lastYMouse = 0;
    private int guiFunc = 0;

    private int radarZoomLv = 0;
    private final java.util.List<RadarEntity> shipList = new java.util.ArrayList<>();
    private final java.util.List<Integer> selectedShips = new java.util.ArrayList<>();
    private int tickGUI = 0;
    private int[] listNum = new int[]{0, 0, 0, 0, 0};
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
        
        if (guiFunc == 1) {
            drawRadarHoverText(guiGraphics, (int)((mouseX - leftPos) * GUI_SCALE_INV), (int)((mouseY - topPos) * GUI_SCALE_INV), mouseX, mouseY);
        } else if (guiFunc == 2) {
            drawBookHoverText(guiGraphics, (int)((mouseX - leftPos) * GUI_SCALE_INV), (int)((mouseY - topPos) * GUI_SCALE_INV), mouseX, mouseY);
        }
    }

    private void drawPageButtons(GuiGraphics guiGraphics, int mx, int my) {
        if (chapId < 0 || chapId >= Values.PageLimit.length) return;
        
        if (mx >= 50 && mx <= 80 && my >= 180 && my <= 195) {
            if (pageId > 0) {
                guiGraphics.blit(BookRenderer.GUI_BOOK, 53, 182, 0, 192, 18, 10, 256, 256);
            }
        } else if (mx >= 170 && mx <= 200 && my >= 180 && my <= 195) {
            if (pageId < Values.PageLimit[chapId]) {
                guiGraphics.blit(BookRenderer.GUI_BOOK, 175, 182, 0, 202, 18, 10, 256, 256);
            }
        }
    }

    private void drawBookHoverText(GuiGraphics guiGraphics, int mx, int my, int mouseX, int mouseY) {
        if (mx >= 243 && mx <= 256) {
            if (my >= 34 && my <= 121) {
                int getbtn = (my - 34) / 12;
                if (getbtn >= 0 && getbtn < 7) {
                    String strChap = Component.translatable("gui.shincolle.book.chap" + getbtn + ".title").getString();
                    guiGraphics.renderTooltip(this.font, Component.literal(strChap), mouseX, mouseY);
                    return;
                }
            }
        }

        int bookID = chapId * 1000 + pageId;
        java.util.List<int[]> cont = Values.BookList.get(bookID);
        if (cont == null) return;
        
        for (int[] getc : cont) {
            if (getc == null || getc.length < 5 || getc[0] != 2) continue;
            int xa = (getc[1] == 1) ? (getc[2] + 133 - 1) : (getc[2] + 13 - 1);
            int ya = getc[3] + 48;
            if (mx > xa - 1 && mx < xa + 17 && my > ya - 1 && my < ya + 17) {
                net.minecraft.world.item.ItemStack stack = Values.ItemIconMap.get((short)getc[4]);
                if (stack != null && !stack.isEmpty()) {
                    guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                }
                break;
            }
        }
    }

    private void drawRadarHoverText(GuiGraphics guiGraphics, int mx, int my, int mouseX, int mouseY) {
        java.util.List<Component> list = new java.util.ArrayList<>();
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
            BookRenderer.drawBookBase(guiGraphics, 0, 0, chapId, pageId);
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
                        renderPoseControls(guiGraphics, 0, 0);
                        BookRenderer.drawStateFlags(guiGraphics, 0, 0, entityTemp);
                        drawNoText(guiGraphics, 0, 0);
                    }
                }
            }

            drawPageButtons(guiGraphics, (int)((mouseX - leftPos) * GUI_SCALE_INV), (int)((mouseY - topPos) * GUI_SCALE_INV));
        }

        guiGraphics.pose().popPose();
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
            if (index >= this.shipList.size()) break;

            RadarEntity s = this.shipList.get(index);
            if (s == null || !(s.ship instanceof EntityShipBase s2)) continue;

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

    private void drawNoText(GuiGraphics guiGraphics, int x, int y) {
        if (pageId > 0 && entityTemp instanceof EntityShipBase ship) {
            String str = "No. " + pageId;
            int color = (chapId == 4) ? 0xAA0000 : 0x00AAAA;
            guiGraphics.drawString(this.font, str, x + 55, y + 32, color, false);
        }
    }

    private void renderShipNameIcons(GuiGraphics guiGraphics, int x, int y) {
        if (!(entityTemp instanceof EntityShipBase ship)) return;
        
        int shipType = ship.getStateMinor(19);
        int[] typeXY = Values.ShipTypeIconMap.get((byte)shipType);
        
        int shipClass = ship.getStateMinor(EntityShipBase.STATE_MINOR_SHIP_CLASS);
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

    private void renderPoseControls(GuiGraphics guiGraphics, int x, int y) {
        
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

        if (entityTemp == null || entityTemp.getType() != type) {
            if (this.minecraft.level != null) {
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
    }

    private EntityType<?> getEntityTypeFromClassID(int classID) {
        return switch (classID) {
            case 0 -> ModEntities.DESTROYER_I.get();
            case 1 -> ModEntities.DESTROYER_RO.get();
            case 2 -> ModEntities.DESTROYER_HA.get();
            case 3 -> ModEntities.DESTROYER_NI.get();
            case 9 -> ModEntities.HEAVY_CRUISER_RI.get();
            case 10 -> ModEntities.HEAVY_CRUISER_NE.get();
            case 12 -> ModEntities.CARRIER_WO.get();
            case 13 -> ModEntities.BATTLESHIP_RU.get();
            case 14 -> ModEntities.BATTLESHIP_TA.get();
            case 15 -> ModEntities.BATTLESHIP_RE.get();
            case 16 -> ModEntities.TRANSPORT_WA.get();
            case 17 -> ModEntities.SUBM_KA.get();
            case 18 -> ModEntities.SUBM_YO.get();
            case 19 -> ModEntities.SUBM_SO.get();
            case 20 -> ModEntities.CARRIER_HIME.get();
            case 21 -> ModEntities.AIRFIELD_HIME.get();
            case 26 -> ModEntities.BATTLESHIP_HIME.get();
            case 27 -> ModEntities.DESTROYER_HIME.get();
            case 28 -> ModEntities.HARBOUR_HIME.get();
            case 29 -> ModEntities.ISOLATED_HIME.get();
            case 30 -> ModEntities.MIDWAY_HIME.get();
            case 31 -> ModEntities.NORTHERN_HIME.get();
            case 44 -> ModEntities.SUBM_HIME.get();
            case 72 -> ModEntities.SSNH.get();
            case 33 -> ModEntities.CARRIER_W_DEMON.get();
            case 49 -> ModEntities.CA_HIME.get();
            
            case 36 -> ModEntities.DESTROYER_SHIMAKAZE.get();
            case 37 -> ModEntities.BATTLESHIP_NAGATO.get();
            case 38 -> ModEntities.SUBM_U511.get();
            case 39 -> ModEntities.SUBM_RO500.get();
            case 46 -> ModEntities.BATTLESHIP_YAMATO.get();
            case 47 -> ModEntities.CARRIER_KAGA.get();
            case 48 -> ModEntities.CARRIER_AKAGI.get();
            case 51 -> ModEntities.DESTROYER_AKATSUKI.get();
            case 52 -> ModEntities.DESTROYER_HIBIKI.get();
            case 53 -> ModEntities.DESTROYER_IKAZUCHI.get();
            case 54 -> ModEntities.DESTROYER_INAZUMA.get();
            case 56 -> ModEntities.CRUISER_TENRYUU.get();
            case 57 -> ModEntities.CRUISER_TATSUTA.get();
            case 58 -> ModEntities.CRUISER_ATAGO.get();
            case 59 -> ModEntities.CRUISER_TAKAO.get();
            case 60 -> ModEntities.BB_KONGOU.get();
            case 61 -> ModEntities.BB_HIEI.get();
            case 62 -> ModEntities.BB_HARUNA.get();
            case 63 -> ModEntities.BB_KIRISHIMA.get();
            
            default -> null;
        };
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
                ship.setStateFlag(2, ship.getRandom().nextInt(8) == 0);
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
            com.mojang.blaze3d.systems.RenderSystem.runAsFancy(() -> {
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
        double dx = mx - this.lastXMouse;
        double dy = my - this.lastYMouse;
        this.lastXMouse = mx;
        this.lastYMouse = my;
        
        if (Math.abs(dx) > 20 || Math.abs(dy) > 20) return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        
        if ((chapId == 4 || chapId == 5) && mx > 8 && mx < 117 && my > 47 && my < 154) {
            if (dx != 0) this.targetRotateY += dx * 3.0f;
            if (dy != 0) this.targetRotateX = net.minecraft.util.Mth.clamp(this.targetRotateX + (float)dy * 2.0f, -90.0f, 90.0f);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        double mx = (mouseX - leftPos) * GUI_SCALE_INV;
        double my = (mouseY - topPos) * GUI_SCALE_INV;
        
        if (menu.getDeskType() == 1) {
            if (scrollY > 0) {
                if (this.listNum[0] > 0) {
                    this.listNum[0]--;
                }
            } else if (scrollY < 0) {
                if (this.listNum[0] < this.shipList.size() - 1) {
                    this.listNum[0]++;
                }
            }
            return true;
        }

        if ((chapId == 4 || chapId == 5) && mx > 8 && mx < 117 && my > 47 && my < 154) {
            if (scrollY > 0) this.targetScale += 5.0f;
            else if (scrollY < 0) this.targetScale -= 5.0f;
            this.targetScale = net.minecraft.util.Mth.clamp(this.targetScale, 10.0f, 150.0f);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int mx = (int)((mouseX - leftPos) * GUI_SCALE_INV);
        int my = (int)((mouseY - topPos) * GUI_SCALE_INV);
        this.lastXMouse = mx;
        this.lastYMouse = my;

        if (menu.getDeskType() == 0) {
            if (my >= 2 && my <= 18) {
                if (mx >= 3 && mx <= 19) { setDeskFunction(1); return true; }
                if (mx >= 22 && mx <= 38) { setDeskFunction(2); return true; }
                if (mx >= 41 && mx <= 57) { setDeskFunction(3); return true; }
                if (mx >= 60 && mx <= 76) { setDeskFunction(4); return true; }
            }
        }

        if (guiFunc == 1) {
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
            if (!this.selectedShips.isEmpty()) {
                if (mx >= 88 && mx <= 132 && my >= 159 && my <= 169) {
                    handleSummonSelectedShips();
                    return true;
                }
            }
            this.selectedShips.clear();
            return true;
        }

        if (guiFunc == 2) {
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
            
            if (mx >= 243 && mx <= 256) {
                if (my >= 34 && my <= 121) {
                    int getbtn = (my - 34) / 12;
                    if (getbtn >= 0 && getbtn < 7) {
                        this.chapId = getbtn;
                        this.pageId = 0;
                        syncBookState();
                        syncDeskGui();
                        return true;
                    }
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
                    if (i >= ((EntityShipBase)entityTemp).getStateMinor(13)) break;
                    int dx = 45 + (drawIdx % 8) * 9;
                    int dy = 158 + (drawIdx / 8) * 9;
                    if (mx >= dx && mx <= dx + 7 && my >= dy && my <= dy + 9) {
                        handleBookModelControls(5 + i);
                        return true;
                    }
                    drawIdx++;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
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

        java.util.List<java.util.UUID> uuids = new java.util.ArrayList<>();
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
            PacketDistributor.sendToServer(new C2SDeskSummonPayload(pos, uuids));
            this.minecraft.player.closeContainer();
        }
    }

    private static class RadarEntity {
        public Entity ship;
        public double pixelx, pixely, pixelz;
        public int posX, posY, posZ;

        public RadarEntity(Entity ship) {
            this.ship = ship;
        }
    }
}




