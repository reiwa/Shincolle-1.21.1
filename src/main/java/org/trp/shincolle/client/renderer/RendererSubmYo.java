package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelSubmYo;
import org.trp.shincolle.entity.EntitySubmYo;

public class RendererSubmYo extends MobRenderer<EntitySubmYo, ModelSubmYo<EntitySubmYo>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/subm_yo.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererSubmYo(EntityRendererProvider.Context context) {
        super(context, new ModelSubmYo<>(context.bakeLayer(ModelSubmYo.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySubmYo entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntitySubmYo entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntitySubmYo entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
