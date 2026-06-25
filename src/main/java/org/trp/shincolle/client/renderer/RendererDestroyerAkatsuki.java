package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerAkatsuki;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntityDestroyerAkatsuki;

public class RendererDestroyerAkatsuki extends MobRenderer<EntityDestroyerAkatsuki, ModelDestroyerAkatsuki<EntityDestroyerAkatsuki>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_akatsuki.png");

    public RendererDestroyerAkatsuki(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerAkatsuki<>(context.bakeLayer(ModelDestroyerAkatsuki.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    protected RenderType getRenderType(EntityDestroyerAkatsuki entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerAkatsuki entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerAkatsuki entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerAkatsuki entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
