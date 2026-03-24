package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelCruiserTakao;
import org.trp.shincolle.entity.EntityCruiserTakao;

public class RendererCruiserTakao extends MobRenderer<EntityCruiserTakao, ModelCruiserTakao<EntityCruiserTakao>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/cruiser_takao.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererCruiserTakao(EntityRendererProvider.Context context) {
        super(context, new ModelCruiserTakao<>(context.bakeLayer(ModelCruiserTakao.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCruiserTakao entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityCruiserTakao entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityCruiserTakao entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
