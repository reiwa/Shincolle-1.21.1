package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelShipGrudge;
import org.trp.shincolle.entity.EntityShipGrudge;

public class RendererShipGrudge extends EntityRenderer<EntityShipGrudge> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/modelbasicentityitem.png");
    private final ModelShipGrudge<EntityShipGrudge> model;

    public RendererShipGrudge(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ModelShipGrudge<>(context.bakeLayer(ModelShipGrudge.LAYER_LOCATION));
    }

    @Override
    public void render(EntityShipGrudge entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float age = entity.tickCount + partialTicks;
        float wobble = Mth.cos(age * 0.12F) * 0.5F;
        float alpha = wobble < 0.0F ? 0.9F + wobble : 0.9F - wobble;
        float scale = wobble < 0.0F ? 0.25F - wobble * 0.5F : 0.25F + wobble * 1.25F;
        int color = (Mth.clamp((int) (alpha * 255.0F), 0, 255) << 24) | 0xFFFFFF;

        poseStack.translate(0.0D, 0.1D, 0.0D);
        poseStack.scale(scale, scale, scale);
        this.model.setDynamicRotation(age);

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        this.model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, color);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityShipGrudge entity) {
        return TEXTURE;
    }
}
