package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelBBHaruna;
import org.trp.shincolle.entity.EntityBBHaruna;

public class RendererBBHaruna extends MobRenderer<EntityBBHaruna, ModelBBHaruna<EntityBBHaruna>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/bb_haruna.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererBBHaruna(EntityRendererProvider.Context context) {
        super(context, new ModelBBHaruna<>(context.bakeLayer(ModelBBHaruna.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBBHaruna entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityBBHaruna entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityBBHaruna entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
