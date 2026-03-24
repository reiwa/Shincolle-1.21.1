package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelCarrierWo;
import org.trp.shincolle.entity.EntityCarrierWo;

public class RendererCarrierWo extends MobRenderer<EntityCarrierWo, ModelCarrierWo<EntityCarrierWo>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/carrier_wo.png");
    private static final float MODEL_SCALE = 1.0f;

    public RendererCarrierWo(EntityRendererProvider.Context context) {
        super(context, new ModelCarrierWo<>(context.bakeLayer(ModelCarrierWo.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCarrierWo entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityCarrierWo entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityCarrierWo entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
