package org.trp.shincolle.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.joml.Matrix4f;
import org.trp.shincolle.block.entity.IWaypoint;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.PointerItem;
import org.trp.shincolle.item.TargetWrenchItem;

import java.util.ArrayList;
import java.util.List;

public class RenderWaypoint<T extends BlockEntity & IWaypoint> implements BlockEntityRenderer<T> {

    public RenderWaypoint(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        Player localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) return;

        if (!isWatchingItem(localPlayer.getMainHandItem()) && !isWatchingItem(localPlayer.getOffhandItem())) {
            return;
        }

        BlockPos lastPos = blockEntity.getLastPos();
        BlockPos nextPos = blockEntity.getNextPos();
        BlockPos chestPos = blockEntity.getChestPos();

        boolean hasLast = lastPos != null && !lastPos.equals(BlockPos.ZERO);
        boolean hasNext = nextPos != null && !nextPos.equals(BlockPos.ZERO);
        boolean hasChest = chestPos != null && !chestPos.equals(BlockPos.ZERO);

        if (!hasLast && !hasNext && !hasChest) {
            return;
        }

        List<Component> lines = new ArrayList<>();
        String ownerName = blockEntity.getOwnerName();
        if (ownerName != null && !ownerName.isEmpty()) {
            lines.add(Component.literal(ChatFormatting.GREEN + ownerName));
        }

        String postext1 = "F: " + ChatFormatting.LIGHT_PURPLE + (hasLast ? (lastPos.getX() + ", " + lastPos.getY() + ", " + lastPos.getZ()) : "0, 0, 0");
        String postext2 = "T: " + ChatFormatting.AQUA + (hasNext ? (nextPos.getX() + ", " + nextPos.getY() + ", " + nextPos.getZ()) : "0, 0, 0");
        String postext3 = "C: " + ChatFormatting.YELLOW + (hasChest ? (chestPos.getX() + ", " + chestPos.getY() + ", " + chestPos.getZ()) : "0, 0, 0");

        lines.add(Component.literal(ChatFormatting.WHITE + postext1));
        lines.add(Component.literal(ChatFormatting.WHITE + postext2));
        lines.add(Component.literal(ChatFormatting.WHITE + postext3));

        Font font = Minecraft.getInstance().font;
        int maxWidth = 0;
        for (Component line : lines) {
            maxWidth = Math.max(maxWidth, font.width(line));
        }

        int textWidth = (maxWidth + 1) / 2;
        int textHeight = lines.size() - 1;

        poseStack.pushPose();
        poseStack.translate(0.5D, 1.9D, 0.5D);

        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        poseStack.mulPose(Axis.YP.rotationDegrees(-camera.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));
        poseStack.scale(-0.025F, -0.025F, 0.025F);

        Matrix4f pose = poseStack.last().pose();
        VertexConsumer builder = bufferSource.getBuffer(RenderType.textBackground());

        float x1 = -textWidth - 1.0F;
        float y1 = -1.0F - textHeight * 9.0F;
        float x2 = textWidth + 1.0F;
        float y2 = 8.0F;

        int fullLight = LightTexture.FULL_BRIGHT;
        builder.addVertex(pose, x1, y1, 0.0F).setColor(0.0F, 0.0F, 0.0F, 0.25F).setLight(fullLight);
        builder.addVertex(pose, x1, y2, 0.0F).setColor(0.0F, 0.0F, 0.0F, 0.25F).setLight(fullLight);
        builder.addVertex(pose, x2, y2, 0.0F).setColor(0.0F, 0.0F, 0.0F, 0.25F).setLight(fullLight);
        builder.addVertex(pose, x2, y1, 0.0F).setColor(0.0F, 0.0F, 0.0F, 0.25F).setLight(fullLight);

        poseStack.translate(0.0D, 0.0D, -0.05D);
        Matrix4f textPose = poseStack.last().pose();

        for (int i = 0; i < lines.size(); i++) {
            float yPos = -textHeight * 9.0F + i * 9.0F;
            font.drawInBatch(lines.get(i), -textWidth, yPos, -1, false, textPose, bufferSource, Font.DisplayMode.NORMAL, 0, fullLight);
        }

        poseStack.popPose();
    }

    private static boolean isWatchingItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        if (stack.getItem() instanceof TargetWrenchItem) return true;
        if (stack.getItem() == ModItems.WAYPOINT.get()) return true;
        if (stack.getItem() == ModItems.CRANE.get()) return true;
        if (stack.getItem() instanceof PointerItem pointer) {
            int mode = pointer.getMode(stack);
            return mode < 3;
        }
        return false;
    }
}
