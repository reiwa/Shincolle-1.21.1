package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelBattleshipRu;
import org.trp.shincolle.entity.EntityBattleshipRu;

public class RendererBattleshipRu extends MobRenderer<EntityBattleshipRu, ModelBattleshipRu<EntityBattleshipRu>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/battleship_ru.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererBattleshipRu(EntityRendererProvider.Context context) {
        super(context, new ModelBattleshipRu<>(context.bakeLayer(ModelBattleshipRu.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBattleshipRu entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityBattleshipRu entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityBattleshipRu entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
