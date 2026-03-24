package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelSubmKa;
import org.trp.shincolle.entity.EntitySubmKa;

public class RendererSubmKa extends MobRenderer<EntitySubmKa, ModelSubmKa<EntitySubmKa>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/subm_ka.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererSubmKa(EntityRendererProvider.Context context) {
        super(context, new ModelSubmKa<>(context.bakeLayer(ModelSubmKa.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySubmKa entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntitySubmKa entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntitySubmKa entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
