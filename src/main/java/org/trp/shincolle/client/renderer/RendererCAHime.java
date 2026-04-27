package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelCAHime;
import org.trp.shincolle.entity.EntityCAHime;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererCAHime extends MobRenderer<EntityCAHime, ModelCAHime<EntityCAHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/ca_hime.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererCAHime(EntityRendererProvider.Context context) {
        super(context, new ModelCAHime<>(context.bakeLayer(ModelCAHime.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCAHime entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityCAHime entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityCAHime entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
