package org.trp.shincolle.client.screen;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.LegacyShipStats;
import org.trp.shincolle.menu.FormationMenu;
import org.trp.shincolle.network.C2SFormationActionPayload;
import org.trp.shincolle.utility.FormationHelper;

import java.util.*;

public class FormationScreen extends AbstractContainerScreen<FormationMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guiformation.png");
    private static final ResourceLocation NAME_ICON_TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guinameicon0.png");

    private static final int BAR_LENGTH = 20;
    private static final int[] BAR_ROWS = new int[]{54, 69, 84, 99, 114, 129};
    private static final int[] BAR_COLS = new int[]{9, 52, 95};
    private static final Map<Integer, int[][]> FORMATION_POSITIONS = new HashMap<>();

    private int selectedSlot = 0;
    private EditBox nameBox;
    private boolean editingName = false;
    private int tickWaitSync = 0;
    private int tickGUI = 0;

    private final float[][] spotPos = new float[2][6];
    private final int[][] spotPosTarget = new int[2][6];
    private final float[] buffBar = new float[21];
    private final float[] buffBarTarget = new float[21];
    private final float[] totalFP = new float[6];
    private final float[] unbuffedAttrs = new float[21];
    private String teamNameStr = "";

    static {
        FORMATION_POSITIONS.put(0, new int[][]{{25, 25, 25, 25, 25, 25}, {25, 25, 25, 25, 25, 25}});
        FORMATION_POSITIONS.put(1, new int[][]{{25, 25, 25, 25, 25, 25}, {9, 15, 21, 27, 33, 39}});
        FORMATION_POSITIONS.put(2, new int[][]{{21, 29, 21, 29, 21, 29}, {25, 25, 16, 16, 34, 34}});
        FORMATION_POSITIONS.put(3, new int[][]{{25, 25, 15, 35, 25, 25}, {29, 15, 26, 26, 36, 23}});
        FORMATION_POSITIONS.put(4, new int[][]{{40, 34, 28, 22, 16, 10}, {9, 15, 21, 27, 33, 39}});
        FORMATION_POSITIONS.put(5, new int[][]{{40, 34, 28, 22, 16, 10}, {25, 25, 25, 25, 25, 25}});
    }

    public FormationScreen(FormationMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 256;
        this.imageHeight = 192;
    }

    @Override
    protected void init() {
        super.init();
        this.nameBox = new EditBox(this.font, this.leftPos + 100, this.topPos + 180, 150, 12, Component.empty());
        this.nameBox.setTextColor(0xFFFF55);
        this.nameBox.setEditable(false);
        this.nameBox.setBordered(true);
        this.nameBox.setMaxLength(250);
        this.nameBox.visible = false;
        this.addRenderableWidget(this.nameBox);
        this.editingName = false;
        this.tickWaitSync = 0;
        this.tickGUI = 0;
        for (int i = 0; i < 6; i++) {
            this.spotPos[0][i] = 25.0f;
            this.spotPos[1][i] = 25.0f;
            this.spotPosTarget[0][i] = 25;
            this.spotPosTarget[1][i] = 25;
        }
        Arrays.fill(this.buffBar, 0.0f);
        Arrays.fill(this.buffBarTarget, 0.0f);
        sendAction(7, 0, 0, "", Optional.empty());
        updateData();
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);

        this.tickGUI++;
        if (this.tickWaitSync > 0) this.tickWaitSync--;
        
        updateAnimation();
        if (this.tickGUI % 32 == 0) {
            updateData();
        }

        this.renderTooltip(graphics, mouseX, mouseY);
    }

    private void updateAnimation() {
        for (int i = 0; i < 6; i++) {
            if (this.spotPos[0][i] != this.spotPosTarget[0][i]) {
                this.spotPos[0][i] += Math.signum(this.spotPosTarget[0][i] - this.spotPos[0][i]);
            }
            if (this.spotPos[1][i] != this.spotPosTarget[1][i]) {
                this.spotPos[1][i] += Math.signum(this.spotPosTarget[1][i] - this.spotPos[1][i]);
            }
        }

        for (int i = 0; i < this.buffBar.length; i++) {
            if (Math.abs(this.buffBar[i] - this.buffBarTarget[i]) > 0.1f) {
                this.buffBar[i] += (this.buffBarTarget[i] - this.buffBar[i]) * 0.1f;
            } else {
                this.buffBar[i] = this.buffBarTarget[i];
            }
        }
    }

    private void updateData() {
        AdmiralData data = menu.getAdmiralData();
        int currentTeam = data.getCurrentTeamID();
        EntityShipBase[] ships = getShipsForTeam(data, currentTeam);
        
        EntityShipBase selectedShip = ships[selectedSlot];
        if (selectedShip != null && selectedShip.getLegacyShipStats() != null) {
            for (int i = 0; i < LegacyShipStats.STAT_KNOCKBACK_RESISTANCE + 1; i++) {
                this.unbuffedAttrs[i] = selectedShip.getLegacyShipStats().getRawAttr(i);
            }
        } else {
            Arrays.fill(this.unbuffedAttrs, 0.0f);
        }

        Arrays.fill(this.totalFP, 0.0f);
        for (EntityShipBase ship : ships) {
            if (ship == null || ship.getLegacyShipStats() == null) continue;
            this.totalFP[0] += ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_FIREPOWER);
            this.totalFP[1] += ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_HEAVY_FIREPOWER);
            this.totalFP[2] += ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_LIGHT_AIRCRAFT_FIREPOWER);
            this.totalFP[3] += ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_HEAVY_AIRCRAFT_FIREPOWER);
            this.totalFP[4] += ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_ANTI_AIR);
            this.totalFP[5] += ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_ANTI_SUB);
        }
        this.teamNameStr = data.getTeamName(currentTeam);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;

        graphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        AdmiralData data = menu.getAdmiralData();
        int currentTeam = data.getCurrentTeamID();

        if (selectedSlot >= 0 && selectedSlot < AdmiralData.SLOT_COUNT) {
            graphics.blit(TEXTURE, x + 142, y + 5 + selectedSlot * 27, 3, 192, 108, 27);
        }

        graphics.blit(TEXTURE, x + 18 + currentTeam * 12, y + 167, 111 + currentTeam * 9, 207, 9, 11);

        int formation = data.getFormationID(currentTeam);
        graphics.blit(TEXTURE, x + 18 + formation * 18, y + 149, 111 + formation * 15, 192, 15, 15);

        int[][] targets = FORMATION_POSITIONS.getOrDefault(formation, FORMATION_POSITIONS.get(0));
        for (int i = 0; i < 6; i++) {
            this.spotPosTarget[0][i] = targets[0][i];
            this.spotPosTarget[1][i] = targets[1][i];
        }

        for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
            graphics.blit(TEXTURE, x + (int)this.spotPos[0][i], y + (int)this.spotPos[1][i], 0, i == selectedSlot ? 195 : 192, 3, 3);
        }

        drawFormationBuffBars(graphics, x, y, formation, selectedSlot);
        drawMoraleIcons(graphics, x, y, data, currentTeam);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        AdmiralData data = menu.getAdmiralData();
        int currentTeam = data.getCurrentTeamID();

        String teamName = data.getTeamName(currentTeam);
        if (teamName != null && !editingName) {
            graphics.drawString(this.font, teamName, 100, 182, 0xFFFF55, true);
            if (nameBox != null && !nameBox.getValue().equals(teamName)) {
                nameBox.setValue(teamName);
            }
        }

        drawFormationText(graphics, data, currentTeam);
        drawShipListText(graphics, data, currentTeam);
        drawAttributeLabels(graphics);
        handleHoveringText(graphics, mouseX, mouseY, data, currentTeam);

        if (this.tickWaitSync > 0) {
            String str = String.format("%.1f", this.tickWaitSync * 0.05f);
            graphics.drawString(this.font, str, 190, 171, 0xFFFF55, false);
        }
        
        Component radarLabel = Component.translatable("gui.shincolle.radar.tname");
        graphics.drawString(this.font, radarLabel, 70 - this.font.width(radarLabel) / 2, 182, 0xFFFF55, true);
    }

    private void drawAttributeLabels(GuiGraphics graphics) {
        graphics.pose().pushPose();
        graphics.pose().scale(0.75f, 0.75f, 0.75f);
        
        String[] labels = {
            ChatFormatting.RED + Component.translatable("gui.shincolle.firepower1").getString(),
            ChatFormatting.GREEN + Component.translatable("gui.shincolle.torpedo").getString(),
            ChatFormatting.RED + Component.translatable("gui.shincolle.airfirepower").getString(),
            ChatFormatting.GREEN + Component.translatable("gui.shincolle.airtorpedo").getString(),
            ChatFormatting.WHITE + Component.translatable("gui.shincolle.attackspeed").getString(),
            ChatFormatting.LIGHT_PURPLE + Component.translatable("gui.shincolle.range").getString(),
            ChatFormatting.AQUA + Component.translatable("gui.shincolle.critical").getString(),
            ChatFormatting.YELLOW + Component.translatable("gui.shincolle.doublehit").getString(),
            ChatFormatting.GOLD + Component.translatable("gui.shincolle.triplehit").getString(),
            ChatFormatting.RED + Component.translatable("gui.shincolle.missreduce").getString(),
            ChatFormatting.YELLOW + Component.translatable("gui.shincolle.antiair").getString(),
            ChatFormatting.AQUA + Component.translatable("gui.shincolle.antiss").getString(),
            ChatFormatting.WHITE + Component.translatable("gui.shincolle.armor").getString(),
            ChatFormatting.GOLD + Component.translatable("gui.shincolle.dodge").getString(),
            ChatFormatting.DARK_PURPLE + Component.translatable("gui.shincolle.equip.grudge").getString(),
            ChatFormatting.DARK_GREEN + Component.translatable("gui.shincolle.equip.hpres").getString(),
            ChatFormatting.YELLOW + Component.translatable("gui.shincolle.equip.kb").getString(),
            ChatFormatting.GRAY + Component.translatable("gui.shincolle.movespeed").getString()
        };
        float[] xPos = {12, 12, 12, 12, 12, 12, 69, 69, 69, 69, 69, 69, 126, 126, 126, 126, 126, 126};
        float[] yPos = {60, 80, 100, 120, 140, 160, 60, 80, 100, 120, 140, 160, 60, 80, 100, 120, 140, 160};
        
        for (int i = 0; i < labels.length; i++) {
            graphics.drawString(this.font, labels[i], (int)xPos[i], (int)yPos[i], 0xFFFFFF, false);
        }
        graphics.pose().popPose();
    }
    
    private void handleHoveringText(GuiGraphics graphics, int mouseX, int mouseY, AdmiralData data, int currentTeam) {
        int mx = mouseX - this.leftPos;
        int my = mouseY - this.topPos;
        
        EntityShipBase[] ships = getShipsForTeam(data, currentTeam);
        if (ships[selectedSlot] != null && mx > 3 && mx < 138 && my > 43 && my < 145) {
            byte attrId = getHoveredAttributeId(mx, my);
            if (attrId != -1) {
                EntityShipBase ship = ships[selectedSlot];
                float formationValue = FormationHelper.getFormationBuffs(data.getFormationID(currentTeam), selectedSlot)[attrId];
                float rawValue = this.unbuffedAttrs[attrId];
                float buffedValue = ship.getLegacyShipStats().getBuffedAttr(attrId);
                
                String prefix = (formationValue > (attrId == LegacyShipStats.STAT_ATTACK_RANGE || attrId == LegacyShipStats.STAT_MOVE_SPEED ? 0.0f : 1.0f) && attrId < LegacyShipStats.STAT_DODGE) || (formationValue > 0f && attrId >= LegacyShipStats.STAT_DODGE) ? "+" : "";
                String formationStr;
                String orgStr;
                String buffedStr;

                if (attrId == LegacyShipStats.STAT_ATTACK_RANGE || attrId == LegacyShipStats.STAT_MOVE_SPEED) {
                    formationStr = String.format("%.2f", formationValue);
                    orgStr = String.format("%.2f", rawValue);
                    buffedStr = String.format("%.2f", buffedValue);
                } else if (attrId == LegacyShipStats.STAT_DODGE || attrId == LegacyShipStats.STAT_FUEL_CONSUMPTION || attrId == LegacyShipStats.STAT_HEALING_MODIFIER || attrId == LegacyShipStats.STAT_KNOCKBACK_RESISTANCE) {
                    formationStr = String.format("%.0f%%", formationValue * 100.0f);
                    orgStr = String.format("%.1f%%", rawValue * 100.0f);
                    buffedStr = String.format("%.1f%%", buffedValue * 100.0f);
                } else if (attrId >= LegacyShipStats.STAT_CRITICAL_RATE && attrId <= LegacyShipStats.STAT_ANTI_SUB) {
                    formationStr = String.format("%.0f%%", (formationValue - 1.0f) * 100.0f);
                    orgStr = String.format("%.1f%%", rawValue * 100.0f);
                    buffedStr = String.format("%.1f%%", buffedValue * 100.0f);
                } else {
                    formationStr = String.format("%.0f%%", (formationValue - 1.0f) * 100.0f);
                    orgStr = String.format("%.1f", rawValue);
                    buffedStr = String.format("%.1f", buffedValue);
                }
                
                String display = prefix + formationStr + " : " + ChatFormatting.GRAY + orgStr + ChatFormatting.WHITE + " -> " + ChatFormatting.YELLOW + buffedStr;
                graphics.renderTooltip(this.font, Component.literal(display), mouseX, mouseY);
            }
        } else if (mx > 45 && mx < 138 && my > 3 && my < 43) {
            List<Component> list = new ArrayList<>();
            list.add(Component.translatable("gui.shincolle.formation.totalfirepower").withStyle(ChatFormatting.LIGHT_PURPLE));
            list.add(Component.translatable("gui.shincolle.firepower1").append(": ").append(Component.literal(String.format("%.1f", totalFP[0])).withStyle(ChatFormatting.RED)));
            list.add(Component.translatable("gui.shincolle.torpedo").append(": ").append(Component.literal(String.format("%.1f", totalFP[1])).withStyle(ChatFormatting.GREEN)));
            list.add(Component.translatable("gui.shincolle.airfirepower").append(": ").append(Component.literal(String.format("%.1f", totalFP[2])).withStyle(ChatFormatting.RED)));
            list.add(Component.translatable("gui.shincolle.airtorpedo").append(": ").append(Component.literal(String.format("%.1f", totalFP[3])).withStyle(ChatFormatting.GREEN)));
            list.add(Component.translatable("gui.shincolle.antiair").append(": ").append(Component.literal(String.format("%.1f", totalFP[4])).withStyle(ChatFormatting.YELLOW)));
            list.add(Component.translatable("gui.shincolle.antiss").append(": ").append(Component.literal(String.format("%.1f", totalFP[5])).withStyle(ChatFormatting.AQUA)));
            graphics.renderComponentTooltip(this.font, list, mouseX, mouseY);
        }
    }

    private byte getHoveredAttributeId(int mx, int my) {
        int range = 5;
        for (int row = 0; row < BAR_ROWS.length; row++) {
            if (my < BAR_ROWS[row] + range) {
                if (mx < 51) return new byte[]{
                    LegacyShipStats.STAT_FIREPOWER,
                    LegacyShipStats.STAT_HEAVY_FIREPOWER,
                    LegacyShipStats.STAT_LIGHT_AIRCRAFT_FIREPOWER,
                    LegacyShipStats.STAT_HEAVY_AIRCRAFT_FIREPOWER,
                    LegacyShipStats.STAT_RELOAD_SPEED,
                    LegacyShipStats.STAT_ATTACK_RANGE
                }[row];
                if (mx < 94) return new byte[]{
                    LegacyShipStats.STAT_CRITICAL_RATE,
                    LegacyShipStats.STAT_DOUBLE_HIT_RATE,
                    LegacyShipStats.STAT_TRIPLE_HIT_RATE,
                    LegacyShipStats.STAT_ACCURACY,
                    LegacyShipStats.STAT_ANTI_AIR,
                    LegacyShipStats.STAT_ANTI_SUB
                }[row];
                return new byte[]{
                    LegacyShipStats.STAT_ARMOR,
                    LegacyShipStats.STAT_DODGE,
                    LegacyShipStats.STAT_FUEL_CONSUMPTION,
                    LegacyShipStats.STAT_HEALING_MODIFIER,
                    LegacyShipStats.STAT_KNOCKBACK_RESISTANCE,
                    LegacyShipStats.STAT_MOVE_SPEED
                }[row];
            }
        }
        return -1;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int xClick = (int) (mouseX - leftPos);
        int yClick = (int) (mouseY - topPos);
        AdmiralData data = menu.getAdmiralData();
        EntityShipBase[] ships = getShipsForTeam(data, data.getCurrentTeamID());

        if (yClick >= 149 && yClick < 164) {
            for (int i = 0; i < 6; i++) {
                if (xClick >= 18 + i * 18 && xClick < 33 + i * 18) {
                    sendAction(1, i, 0, "", Optional.empty());
                    return true;
                }
            }
        }

        if (yClick >= 167 && yClick < 178) {
            for (int i = 0; i < 9; i++) {
                if (xClick >= 18 + i * 12 && xClick < 27 + i * 12) {
                    if (data.getCurrentTeamID() == i) {
                        sendAction(7, 0, 0, "", Optional.empty());
                    } else {
                        sendAction(0, i, 0, "", Optional.empty());
                    }
                    return true;
                }
            }
        }

        if (xClick >= 142 && xClick < 250) {
            for (int i = 0; i < 6; i++) {
                int rowY = 5 + i * 27;
                if (yClick >= rowY && yClick < rowY + 27) {
                    if (button == 1) {
                        sendAction(3, i, 0, "", Optional.empty());
                        sendAction(7, 0, 0, "", Optional.empty());
                        return true;
                    } else if (button == 0) {
                        if (this.selectedSlot == i) {
                            if (ships[i] != null) {
                                sendAction(8, i, 0, "", Optional.empty());
                            }
                        } else {
                            this.selectedSlot = i;
                        }
                        return true;
                    }
                }
            }
        }

        if (yClick >= 170 && yClick < 180) {
            if (xClick >= 159 && xClick < 189) {
                if (this.tickWaitSync == 0) {
                    int target = (selectedSlot + 1) % 6;
                    sendAction(6, selectedSlot, target, "", Optional.empty());
                    sendAction(7, 0, 0, "", Optional.empty());
                    selectedSlot = target;
                    this.tickWaitSync = 40;
                    return true;
                }
            } else if (xClick >= 203 && xClick < 233) {
                if (this.tickWaitSync == 0) {
                    int target = (selectedSlot + 5) % 6;
                    sendAction(6, selectedSlot, target, "", Optional.empty());
                    sendAction(7, 0, 0, "", Optional.empty());
                    selectedSlot = target;
                    this.tickWaitSync = 40;
                    return true;
                }
            }
        }

        if (xClick >= 46 && xClick < 94 && yClick >= 180 && yClick < 192) {
            toggleNameEdit();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (editingName && (keyCode == 257 || keyCode == 335)) {
            submitNameEdit();
            return true;
        }
        if (editingName && keyCode == 256) {
            cancelNameEdit();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void sendAction(int action, int p1, int p2, String s, Optional<java.util.UUID> uuid) {
        Minecraft.getInstance().getConnection().send(new net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket(
            new C2SFormationActionPayload(action, p1, p2, s, uuid)
        ));
    }

    private void drawFormationText(GuiGraphics graphics, AdmiralData data, int currentTeam) {
        Component formationText = Component.translatable("gui.shincolle.formation.format" + data.getFormationID(currentTeam));
        Component positionText = Component.translatable("gui.shincolle.formation.position")
                .append(" ").append(Component.literal(String.valueOf(selectedSlot + 1)));

        graphics.pose().pushPose();
        graphics.pose().scale(0.75f, 0.75f, 0.75f);
        int centerX = 115;
        graphics.drawString(this.font, formationText, centerX - this.font.width(formationText) / 2, 18, 0xFFFFFF, false);
        graphics.drawString(this.font, positionText, centerX - this.font.width(positionText) / 2, 30, 0xFFFFFF, false);
        graphics.pose().popPose();
    }

    private void drawShipListText(GuiGraphics graphics, AdmiralData data, int currentTeam) {
        EntityShipBase[] ships = getShipsForTeam(data, currentTeam);
        graphics.pose().pushPose();
        graphics.pose().scale(0.75f, 0.75f, 0.75f);
        for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
            int textY = 14 + i * 36;
            EntityShipBase ship = ships[i];
            if (ship != null) {
                String name = ship.hasCustomName() ? ship.getCustomName().getString() : ship.getDisplayName().getString();
                graphics.drawString(this.font, name, 210, textY, 0xFFFFFF, false);
                String stats = ChatFormatting.AQUA + "LV " + ChatFormatting.YELLOW + ship.getLevel()
                        + "   " + ChatFormatting.GOLD + (int) ship.getHealth()
                        + " / " + ChatFormatting.RED + (int) ship.getMaxHealth();
                graphics.drawString(this.font, stats, 195, textY + 14, 0, false);
            } else {
                UUID uuid = data.getShipUUID(currentTeam, i);
                MutableComponent noSignal = Component.translatable("gui.shincolle.formation.nosignal")
                        .withStyle(ChatFormatting.DARK_RED, ChatFormatting.OBFUSCATED);
                MutableComponent uidPart = Component.literal(" UID: " + (uuid != null ? uuid.toString().substring(0, 8) : "-1"))
                        .withStyle(style -> style.withColor(ChatFormatting.GRAY).withObfuscated(false));
                
                Component combined = Component.empty().append(noSignal).append(uidPart);
                graphics.drawString(this.font, combined, 195, textY, 0xFFFFFF, false);
            }
        }
        graphics.pose().popPose();
    }

    private void drawMoraleIcons(GuiGraphics graphics, int left, int top, AdmiralData data, int teamId) {
        EntityShipBase[] ships = getShipsForTeam(data, teamId);
        for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
            EntityShipBase ship = ships[i];
            if (ship == null) continue;
            int icon = getMoraleIconIndex(ship.getMorale());
            graphics.blit(NAME_ICON_TEXTURE, left + 145, top + 8 + i * 27, icon * 11, 240, 11, 11);
        }
    }

    private void drawFormationBuffBars(GuiGraphics graphics, int left, int top, int formationId, int slotId) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 6; ++j) {
                graphics.blit(TEXTURE, left + BAR_COLS[i], top + BAR_ROWS[j], 0, 220, BAR_LENGTH, 4);
            }
        }

        float[] value = FormationHelper.getFormationBuffs(formationId, slotId);
        byte[] attrIds = {
            LegacyShipStats.STAT_FIREPOWER,
            LegacyShipStats.STAT_HEAVY_FIREPOWER,
            LegacyShipStats.STAT_LIGHT_AIRCRAFT_FIREPOWER,
            LegacyShipStats.STAT_HEAVY_AIRCRAFT_FIREPOWER,
            LegacyShipStats.STAT_RELOAD_SPEED,
            LegacyShipStats.STAT_ATTACK_RANGE,
            LegacyShipStats.STAT_CRITICAL_RATE,
            LegacyShipStats.STAT_DOUBLE_HIT_RATE,
            LegacyShipStats.STAT_TRIPLE_HIT_RATE,
            LegacyShipStats.STAT_ACCURACY,
            LegacyShipStats.STAT_ANTI_AIR,
            LegacyShipStats.STAT_ANTI_SUB,
            LegacyShipStats.STAT_ARMOR,
            LegacyShipStats.STAT_DODGE,
            LegacyShipStats.STAT_FUEL_CONSUMPTION,
            LegacyShipStats.STAT_HEALING_MODIFIER,
            LegacyShipStats.STAT_KNOCKBACK_RESISTANCE,
            LegacyShipStats.STAT_MOVE_SPEED
        };
        int[] colMap = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        int[] rowMap = {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5};

        for (int i = 0; i < attrIds.length; i++) {
            int attrId = attrIds[i];
            if (attrId < 0 || attrId >= value.length) continue;
            float lenModify = 20.0f;
            float base = 1.0f;
            if (attrId == LegacyShipStats.STAT_MOVE_SPEED) {
                lenModify /= 0.5f;
                base = 0.0f;
            } else if (attrId == LegacyShipStats.STAT_ATTACK_RANGE) {
                lenModify /= 10.0f;
                base = 0.0f;
            } else if (attrId >= LegacyShipStats.STAT_DODGE && attrId <= LegacyShipStats.STAT_KNOCKBACK_RESISTANCE) {
                base = 0.0f;
            }

            this.buffBarTarget[attrId] = (value[attrId] - base) * lenModify;
            int len = (int) Math.abs(this.buffBar[attrId]);
            if (len <= 0) continue;

            int x = left + BAR_COLS[colMap[i]] + (this.buffBar[attrId] > 0 ? BAR_LENGTH : -len + BAR_LENGTH);
            int y = top + BAR_ROWS[rowMap[i]];
            int v = this.buffBar[attrId] > 0 ? 230 : 225;
            graphics.blit(TEXTURE, x, y, 0, v, len, 4);
        }
    }

    private EntityShipBase[] getShipsForTeam(AdmiralData data, int teamId) {
        EntityShipBase[] ships = new EntityShipBase[AdmiralData.SLOT_COUNT];
        for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
            UUID uuid = data.getShipUUID(teamId, i);
            if (uuid == null) continue;
            if (this.minecraft != null && this.minecraft.level != null) {
                EntityShipBase ship = null;
                for (net.minecraft.world.entity.Entity e : this.minecraft.level.entitiesForRendering()) {
                    if (e.getUUID().equals(uuid) && e instanceof EntityShipBase sb) {
                        ship = sb;
                        break;
                    }
                }
                ships[i] = ship;
            }
        }
        return ships;
    }

    private int getMoraleIconIndex(int morale) {
        if (morale > 5100) return 0;
        if (morale > 3900) return 1;
        if (morale > 2100) return 2;
        if (morale > 900) return 3;
        return 4;
    }

    private void toggleNameEdit() {
        if (!editingName) {
            nameBox.setEditable(true);
            nameBox.setFocused(true);
            nameBox.visible = true;
            nameBox.setValue(menu.getAdmiralData().getTeamName(menu.getAdmiralData().getCurrentTeamID()));
            editingName = true;
        } else {
            submitNameEdit();
        }
    }

    private void submitNameEdit() {
        if (!editingName) return;
        sendAction(4, 0, 0, nameBox.getValue(), Optional.empty());
        cancelNameEdit();
    }

    private void cancelNameEdit() {
        nameBox.setEditable(false);
        nameBox.setFocused(false);
        nameBox.visible = false;
        editingName = false;
    }
}
