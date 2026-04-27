package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerRo;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntityDestroyerRo;

public class RendererDestroyerRo extends MobRenderer<EntityDestroyerRo, ModelDestroyerRo<EntityDestroyerRo>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_ro.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererDestroyerRo(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerRo<>(context.bakeLayer(ModelDestroyerRo.LAYER_LOCATION)), 0.5f);

        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerRo entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerRo entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerRo entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
