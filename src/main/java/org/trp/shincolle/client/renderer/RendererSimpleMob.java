package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.client.model.IGlowableModel;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;
import org.trp.shincolle.entity.base.EntityShipBase;

public class RendererSimpleMob<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation texture;
    private final float modelScale;

    public RendererSimpleMob(EntityRendererProvider.Context context, M model, float shadowSize, float modelScale, ResourceLocation texture) {
        super(context, model, shadowSize);
        this.texture = texture;
        this.modelScale = modelScale;

        if (model instanceof IGlowableModel) {
            this.addLayer(new GenericGlowLayer(this, texture));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return texture;
    }

    @Override
    protected void scale(T entity, PoseStack poseStack, float partialTickTime) {
        float s = this.modelScale;
        if (entity instanceof org.trp.shincolle.entity.base.EntityShipBase ship) {
            s = LegacyScale.getScale(ship, this.model);
        } else if (entity instanceof org.trp.shincolle.entity.EntityAircraftBase aircraft) {
            s = aircraft.isMissionLightAircraft() ? 0.5f : 0.6f;
        }
        poseStack.scale(s, s, s);
    }

    @Override
    protected float getFlipDegrees(T entity) {
        if (entity instanceof EntityShipBase ship && ship.isInDeadPose()) {
            return 0.0F;
        }
        return super.getFlipDegrees(entity);
    }
}
