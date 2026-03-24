package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelSSNH;
import org.trp.shincolle.entity.EntitySSNH;

public class RendererSSNH extends MobRenderer<EntitySSNH, ModelSSNH<EntitySSNH>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/subm_hime_new.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererSSNH(EntityRendererProvider.Context context) {
        super(context, new ModelSSNH<>(context.bakeLayer(ModelSSNH.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySSNH entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntitySSNH entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntitySSNH entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
