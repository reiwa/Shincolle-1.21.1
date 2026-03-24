package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelCruiserTatsuta;
import org.trp.shincolle.entity.EntityCruiserTatsuta;

public class RendererCruiserTatsuta extends MobRenderer<EntityCruiserTatsuta, ModelCruiserTatsuta<EntityCruiserTatsuta>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/cruiser_tatsuta.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererCruiserTatsuta(EntityRendererProvider.Context context) {
        super(context, new ModelCruiserTatsuta<>(context.bakeLayer(ModelCruiserTatsuta.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCruiserTatsuta entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityCruiserTatsuta entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityCruiserTatsuta entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
