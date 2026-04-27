package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerHa;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntityDestroyerHa;

public class RendererDestroyerHa extends MobRenderer<EntityDestroyerHa, ModelDestroyerHa<EntityDestroyerHa>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_ha.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererDestroyerHa(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerHa<>(context.bakeLayer(ModelDestroyerHa.LAYER_LOCATION)), 0.5f);

        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerHa entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerHa entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerHa entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
