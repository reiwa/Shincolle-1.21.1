package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerIkazuchi;
import org.trp.shincolle.entity.EntityDestroyerIkazuchi;

public class RendererDestroyerIkazuchi extends MobRenderer<EntityDestroyerIkazuchi, ModelDestroyerIkazuchi<EntityDestroyerIkazuchi>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_ikazuchi.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererDestroyerIkazuchi(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerIkazuchi<>(context.bakeLayer(ModelDestroyerIkazuchi.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerIkazuchi entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerIkazuchi entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerIkazuchi entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
