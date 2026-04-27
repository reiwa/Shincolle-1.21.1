package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelDestroyerI;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntityDestroyerI;

public class RendererDestroyerI extends MobRenderer<EntityDestroyerI, ModelDestroyerI<EntityDestroyerI>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/destroyer_i.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererDestroyerI(EntityRendererProvider.Context context) {
        super(context, new ModelDestroyerI<>(context.bakeLayer(ModelDestroyerI.LAYER_LOCATION)), 0.5f);

        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDestroyerI entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityDestroyerI entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityDestroyerI entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
