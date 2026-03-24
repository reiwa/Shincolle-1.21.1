package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelSubmRo500;
import org.trp.shincolle.entity.EntitySubmRo500;

public class RendererSubmRo500 extends MobRenderer<EntitySubmRo500, ModelSubmRo500<EntitySubmRo500>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/subm_ro_500.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererSubmRo500(EntityRendererProvider.Context context) {
        super(context, new ModelSubmRo500<>(context.bakeLayer(ModelSubmRo500.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySubmRo500 entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntitySubmRo500 entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntitySubmRo500 entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
