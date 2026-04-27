package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelBattleshipHime;
import org.trp.shincolle.entity.EntityBattleshipHime;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererBattleshipHime extends MobRenderer<EntityBattleshipHime, ModelBattleshipHime<EntityBattleshipHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/battleship_hime.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererBattleshipHime(EntityRendererProvider.Context context) {
        super(context, new ModelBattleshipHime<>(context.bakeLayer(ModelBattleshipHime.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBattleshipHime entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityBattleshipHime entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityBattleshipHime entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
