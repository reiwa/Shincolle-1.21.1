package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMidwayHime;
import org.trp.shincolle.entity.EntityMidwayHime;

public class RendererMidwayHime extends MobRenderer<EntityMidwayHime, ModelMidwayHime<EntityMidwayHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/midway_hime.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererMidwayHime(EntityRendererProvider.Context context) {
        super(context, new ModelMidwayHime<>(context.bakeLayer(ModelMidwayHime.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityMidwayHime entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityMidwayHime entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityMidwayHime entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
