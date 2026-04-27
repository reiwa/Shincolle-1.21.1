package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelSubmHime;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntitySubmHime;

public class RendererSubmHime extends MobRenderer<EntitySubmHime, ModelSubmHime<EntitySubmHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/subm_hime.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererSubmHime(EntityRendererProvider.Context context) {
        super(context, new ModelSubmHime<>(context.bakeLayer(ModelSubmHime.LAYER_LOCATION)), 0.5f);

        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySubmHime entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntitySubmHime entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntitySubmHime entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
