package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelAbyssMissile;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;

public class RendererAbyssMissile extends EntityRenderer<EntityAbyssMissile> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/abyss_missile.png");
    private final ModelAbyssMissile<EntityAbyssMissile> model;

    public RendererAbyssMissile(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ModelAbyssMissile<>(context.bakeLayer(ModelAbyssMissile.LAYER_LOCATION));
    }

    @Override
    public void render(EntityAbyssMissile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float yaw = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
        float pitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(yaw + 180.0F));
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(-pitch));
        poseStack.scale(0.6f, 0.6f, 0.6f);

        VertexConsumer consumer = buffer.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFF);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityAbyssMissile entity) {
        return TEXTURE;
    }
}
