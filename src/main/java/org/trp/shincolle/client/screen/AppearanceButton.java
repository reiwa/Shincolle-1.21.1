package org.trp.shincolle.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.init.ModDataAttachments;

public class AppearanceButton extends Button {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/gui/guiappearance.png");
    private final int appearanceId;

    public AppearanceButton(int x, int y, int appearanceId, OnPress onPress) {
        super(x, y, 11, 11, Component.empty(), onPress, DEFAULT_NARRATION);
        this.appearanceId = appearanceId;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        
        AdmiralData data = mc.player.getData(ModDataAttachments.ADMIRAL_DATA.get());
        boolean isCurrent = data.getAppearance() == this.appearanceId;

        int u = isCurrent ? 0 : 11;
        int v = 167;

        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics.blit(TEXTURE, this.getX(), this.getY(), u, v, this.width, this.height, 256, 256);
    }
}
