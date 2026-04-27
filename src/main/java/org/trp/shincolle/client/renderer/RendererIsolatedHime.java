package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelIsolatedHime;
import org.trp.shincolle.entity.EntityIsolatedHime;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererIsolatedHime extends MobRenderer<EntityIsolatedHime, ModelIsolatedHime<EntityIsolatedHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/isolated_hime.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererIsolatedHime(EntityRendererProvider.Context context) {
        super(context, new ModelIsolatedHime<>(context.bakeLayer(ModelIsolatedHime.LAYER_LOCATION)), 0.5f);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
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

    @Override
    protected RenderType getRenderType(EntityIsolatedHime entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityCutoutNoCull(getTextureLocation(entity));
    }
}
