package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelHeavyCruiserRi;
import org.trp.shincolle.entity.EntityHeavyCruiserRi;

public class RendererHeavyCruiserRi extends MobRenderer<EntityHeavyCruiserRi, ModelHeavyCruiserRi<EntityHeavyCruiserRi>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/heavy_cruiser_ri.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererHeavyCruiserRi(EntityRendererProvider.Context context) {
        super(context, new ModelHeavyCruiserRi<>(context.bakeLayer(ModelHeavyCruiserRi.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHeavyCruiserRi entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityHeavyCruiserRi entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityHeavyCruiserRi entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
