package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerInazuma;
import org.trp.shincolle.entity.EntityDestroyerInazuma;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererDestroyerInazuma extends MobRenderer<EntityDestroyerInazuma, ModelDestroyerInazuma<EntityDestroyerInazuma>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_inazuma.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererDestroyerInazuma(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerInazuma<>(context.bakeLayer(ModelDestroyerInazuma.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerInazuma entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerInazuma entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerInazuma entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
