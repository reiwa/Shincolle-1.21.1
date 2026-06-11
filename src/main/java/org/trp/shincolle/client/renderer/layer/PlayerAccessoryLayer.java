package org.trp.shincolle.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.client.model.ModelPlayerAccessory;
import org.trp.shincolle.init.ModDataAttachments;

public class PlayerAccessoryLayer<T extends Player, M extends PlayerModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/player_accessory.png");
    private final ModelPlayerAccessory<T> accessoryModel;

    public static float Y_OFFSET = -1.0F;
    public static float SCALE = 0.7F;

    public PlayerAccessoryLayer(RenderLayerParent<T, M> renderer, ModelPart modelPart) {
        super(renderer);
        this.accessoryModel = new ModelPlayerAccessory<>(modelPart);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T player, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (player.isInvisible()) {
            return;
        }

        AdmiralData data = player.getData(ModDataAttachments.ADMIRAL_DATA.get());
        if (data.getAppearance() != 1) {
            return;
        }

        poseStack.pushPose();
        
        this.getParentModel().body.translateAndRotate(poseStack);

        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

        poseStack.scale(SCALE, SCALE, SCALE);
        poseStack.translate(0.0F, Y_OFFSET, 0.0F);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.accessoryModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

        poseStack.popPose();
    }
}
