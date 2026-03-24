package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelBBKirishima;
import org.trp.shincolle.entity.EntityBBKirishima;

public class RendererBBKirishima extends MobRenderer<EntityBBKirishima, ModelBBKirishima<EntityBBKirishima>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/bb_kirishima.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererBBKirishima(EntityRendererProvider.Context context) {
        super(context, new ModelBBKirishima<>(context.bakeLayer(ModelBBKirishima.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBBKirishima entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityBBKirishima entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityBBKirishima entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
