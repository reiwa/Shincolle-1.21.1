package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelCarrierHime;
import org.trp.shincolle.entity.EntityCarrierHime;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererCarrierHime extends MobRenderer<EntityCarrierHime, ModelCarrierHime<EntityCarrierHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/carrier_hime.png");
    private static final float MODEL_SCALE = 1.0f;

    public RendererCarrierHime(EntityRendererProvider.Context context) {
        super(context, new ModelCarrierHime<>(context.bakeLayer(ModelCarrierHime.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCarrierHime entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityCarrierHime entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityCarrierHime entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
