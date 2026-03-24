package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelIsolatedHime;
import org.trp.shincolle.entity.EntityIsolatedHime;

public class RendererIsolatedHime extends MobRenderer<EntityIsolatedHime, ModelIsolatedHime<EntityIsolatedHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/isolated_hime.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererIsolatedHime(EntityRendererProvider.Context context) {
        super(context, new ModelIsolatedHime<>(context.bakeLayer(ModelIsolatedHime.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityIsolatedHime entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityIsolatedHime entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityIsolatedHime entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
