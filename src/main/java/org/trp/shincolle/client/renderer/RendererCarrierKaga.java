package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelCarrierKaga;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntityCarrierKaga;

public class RendererCarrierKaga extends MobRenderer<EntityCarrierKaga, ModelCarrierKaga<EntityCarrierKaga>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/carrier_kaga.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererCarrierKaga(EntityRendererProvider.Context context) {
        super(context, new ModelCarrierKaga<>(context.bakeLayer(ModelCarrierKaga.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCarrierKaga entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityCarrierKaga entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityCarrierKaga entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
