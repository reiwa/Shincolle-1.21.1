package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelBBKongou;
import org.trp.shincolle.entity.EntityBBKongou;

public class RendererBBKongou extends MobRenderer<EntityBBKongou, ModelBBKongou<EntityBBKongou>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/bb_kongou.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererBBKongou(EntityRendererProvider.Context context) {
        super(context, new ModelBBKongou<>(context.bakeLayer(ModelBBKongou.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBBKongou entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityBBKongou entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityBBKongou entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
