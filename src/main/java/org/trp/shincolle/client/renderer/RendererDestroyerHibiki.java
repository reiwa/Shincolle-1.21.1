package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerHibiki;
import org.trp.shincolle.entity.EntityDestroyerHibiki;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererDestroyerHibiki extends MobRenderer<EntityDestroyerHibiki, ModelDestroyerHibiki<EntityDestroyerHibiki>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_hibiki.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererDestroyerHibiki(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerHibiki<>(context.bakeLayer(ModelDestroyerHibiki.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerHibiki entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerHibiki entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerHibiki entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
