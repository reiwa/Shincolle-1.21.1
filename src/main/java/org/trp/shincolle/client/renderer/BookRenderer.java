package org.trp.shincolle.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.reference.Values;

import java.util.List;

public class BookRenderer {

    public static final ResourceLocation GUI_BOOK = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guideskbook.png");
    public static final ResourceLocation GUI_BOOK2 = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guideskbook2.png");
    public static final ResourceLocation GUI_NAME_ICON0 = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guinameicon0.png");
    public static final ResourceLocation GUI_NAME_ICON1 = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guinameicon1.png");
    public static final ResourceLocation GUI_NAME_ICON2 = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guinameicon2.png");
    public static final ResourceLocation GUI_RADAR = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guideskradar.png");
    public static final ResourceLocation GUI_DESK = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guidesk.png");

    public static void drawBookBase(GuiGraphics guiGraphics, int x, int y, int chapId, int pageId) {
        guiGraphics.blit(GUI_BOOK, x, y, 0, 0, 256, 192);
    }

    public static void drawBookContent(GuiGraphics guiGraphics, int x, int y, int page, int chapNum) {
        int bookID = chapNum * 1000 + page;
        List<int[]> content = Values.BookList.get(bookID);
        
        if (content == null) {
            drawTitleText(guiGraphics, x, y, page, chapNum);
            drawBookText(guiGraphics, x, y, 0, 0, 0, bookID);
            drawBookText(guiGraphics, x, y, 1, 0, 0, bookID);
            return;
        }

        drawTitleText(guiGraphics, x, y, page, chapNum);

        for (int[] data : content) {
            if (data == null) continue;
            switch (data[0]) {
                case 0:
                    drawBookText(guiGraphics, x, y, data[1], data[2], data[3], bookID);
                    break;
                case 1:
                    drawBookPic(guiGraphics, x, y, data);
                    break;
                case 2:
                    drawBookIcon(guiGraphics, x, y, data[1], data[2], data[3], data[4]);
                    break;
            }
        }
    }

    public static void drawStateFlags(GuiGraphics guiGraphics, int x, int y, net.minecraft.world.entity.LivingEntity entity) {
        if (!(entity instanceof org.trp.shincolle.entity.base.EntityShipBase ship)) return;
        
        int shipStats = ship.getStateEmotion(0);
        int shipMaxStats = ship.getStateMinor(13);
        
        int drawIdx = 0;
        int startIdx = ship.hasShipMounts() ? 1 : 0;
        for (int i = startIdx; i < 16; ++i) {
            if (i >= shipMaxStats) break;
            int u = 115;
            int v = ((shipStats >> i) & 1) == 1 ? 156 : 147;
            guiGraphics.blit(GUI_BOOK2, x + 45 + (drawIdx % 8) * 9, y + 158 + (drawIdx / 8) * 9, u, v, 7, 9);
            drawIdx++;
        }
    }

    private static void drawBookText(GuiGraphics guiGraphics, int x, int y, int side, int offX, int offY, int bookID) {
        String key = "gui.shincolle.book.chap" + (bookID / 1000) + ".text" + (bookID % 1000) + "d" + side;
        
        String text = net.minecraft.client.resources.language.I18n.get(key);
        if (text.equals(key)) return;
        
        Font font = Minecraft.getInstance().font;
        int startX = x + (side == 0 ? 13 : 132) + offX;
        int startY = y + 44 + offY;
        
        float scale = 0.75f;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(startX, startY, 0);
        guiGraphics.pose().scale(scale, scale, scale);
        
        String[] lines = text.split("<BR>|<BR/>|<br>|<br/>|#");
        int curY = 0;
        int maxWidth = (int) (105 / scale);
        
        for (String line : lines) {
            if (line.isEmpty()) {
                curY += font.lineHeight;
                continue;
            }

            guiGraphics.drawWordWrap(font, Component.literal(line), 0, curY, maxWidth, 0);
            
            int linesDrawn = font.split(Component.literal(line), maxWidth).size();
            curY += linesDrawn * font.lineHeight;
        }
        
        guiGraphics.pose().popPose();
    }

    private static void drawBookPic(GuiGraphics guiGraphics, int x, int y, int[] data) {
        if (data.length < 9) return;
        
        int side = data[1];
        int px = x + (side == 0 ? 13 : 133) + data[2];
        int py = y + 48 + data[3];
        int picID = data[4];
        
        String fileName = "bookpic0" + (picID == 0 ? 1 : picID) + ".png";
        ResourceLocation tex = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/book/" + fileName);
        
        guiGraphics.blit(tex, px, py, (float)data[5], (float)data[6], data[7], data[8], 256, 256);
    }

    private static void drawBookIcon(GuiGraphics guiGraphics, int x, int y, int side, int offX, int offY, int iconID) {
        int px = x + (side == 0 ? 13 : 133) + offX;
        int py = y + 48 + offY;
        
        ItemStack stack = Values.ItemIconMap.get((short)iconID);
        if (stack != null) {
            guiGraphics.renderItem(stack, px, py);
        }
    }

    private static void drawTitleText(GuiGraphics guiGraphics, int x, int y, int page, int chap) {
        String key;
        if (chap == 0) {
            key = "gui.shincolle.book.chap" + chap + ".title";
        } else {
            key = "gui.shincolle.book.chap" + chap + ".title" + page;
        }

        String text = net.minecraft.client.resources.language.I18n.get(key);
        if (text.equals(key)) return;

        Font font = Minecraft.getInstance().font;
        int strlen = (int) (font.width(text) * 0.5f);
        
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
        Component comp = Component.literal(text).withStyle(net.minecraft.ChatFormatting.UNDERLINE).withStyle(net.minecraft.ChatFormatting.DARK_RED);
        int centerPos = 66;
        guiGraphics.drawString(font, comp, (int)((x + centerPos - strlen) / 0.8f), (int)((y + 35f) / 0.8f), 0xFFFFFF, false);
        guiGraphics.pose().popPose();
    }
}
