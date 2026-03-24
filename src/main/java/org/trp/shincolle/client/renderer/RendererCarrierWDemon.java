package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelCarrierWDemon;
import org.trp.shincolle.entity.EntityCarrierWDemon;

public class RendererCarrierWDemon extends MobRenderer<EntityCarrierWDemon, ModelCarrierWDemon<EntityCarrierWDemon>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/carrier_w_demon.png");
    private static final float MODEL_SCALE = 1.0f;

    public RendererCarrierWDemon(EntityRendererProvider.Context context) {
        super(context, new ModelCarrierWDemon<>(context.bakeLayer(ModelCarrierWDemon.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCarrierWDemon entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityCarrierWDemon entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityCarrierWDemon entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
