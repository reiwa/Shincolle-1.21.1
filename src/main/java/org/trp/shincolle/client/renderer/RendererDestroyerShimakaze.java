package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerShimakaze;
import org.trp.shincolle.entity.EntityDestroyerShimakaze;

public class RendererDestroyerShimakaze extends MobRenderer<EntityDestroyerShimakaze, ModelDestroyerShimakaze<EntityDestroyerShimakaze>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_shimakaze.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererDestroyerShimakaze(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerShimakaze<>(context.bakeLayer(ModelDestroyerShimakaze.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerShimakaze entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerShimakaze entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerShimakaze entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
