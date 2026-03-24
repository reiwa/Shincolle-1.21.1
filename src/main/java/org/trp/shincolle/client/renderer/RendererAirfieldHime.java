package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelAirfieldHime;
import org.trp.shincolle.entity.EntityAirfieldHime;

public class RendererAirfieldHime extends MobRenderer<EntityAirfieldHime, ModelAirfieldHime<EntityAirfieldHime>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/airfield_hime.png");
    private static final float MODEL_SCALE = 0.34f;

    public RendererAirfieldHime(EntityRendererProvider.Context context) {
        super(context, new ModelAirfieldHime<>(context.bakeLayer(ModelAirfieldHime.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityAirfieldHime entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(EntityAirfieldHime entity, PoseStack poseStack, float partialTickTime) {
        float s = LegacyScale.getScale(entity, this.model);
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(EntityAirfieldHime entity) {
        if (entity.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
