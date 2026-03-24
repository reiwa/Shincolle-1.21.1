package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelBattleshipNagato;
import org.trp.shincolle.entity.EntityBattleshipNagato;

public class RendererBattleshipNagato extends MobRenderer<EntityBattleshipNagato, ModelBattleshipNagato<EntityBattleshipNagato>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/battleship_nagato.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererBattleshipNagato(EntityRendererProvider.Context context) {
        super(context, new ModelBattleshipNagato<>(context.bakeLayer(ModelBattleshipNagato.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBattleshipNagato entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityBattleshipNagato entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityBattleshipNagato entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
