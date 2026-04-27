package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelBattleshipRe;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.EntityBattleshipRe;

public class RendererBattleshipRe extends MobRenderer<EntityBattleshipRe, ModelBattleshipRe<EntityBattleshipRe>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/battleship_re.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererBattleshipRe(EntityRendererProvider.Context context) {
        super(context, new ModelBattleshipRe<>(context.bakeLayer(ModelBattleshipRe.LAYER_LOCATION)), 0.5f);

        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBattleshipRe entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityBattleshipRe entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityBattleshipRe entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
