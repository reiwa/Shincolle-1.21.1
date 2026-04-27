package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelHeavyCruiserNe;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntityHeavyCruiserNe;

public class RendererHeavyCruiserNe extends MobRenderer<EntityHeavyCruiserNe, ModelHeavyCruiserNe<EntityHeavyCruiserNe>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/heavy_cruiser_ne.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererHeavyCruiserNe(EntityRendererProvider.Context context) {
        super(context, new ModelHeavyCruiserNe<>(context.bakeLayer(ModelHeavyCruiserNe.LAYER_LOCATION)), 0.5f);

        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHeavyCruiserNe entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityHeavyCruiserNe entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityHeavyCruiserNe entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
