package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.client.model.CustomShipGeoModel;
import org.trp.shincolle.client.renderer.layer.CustomShipGlowLayer;
import org.trp.shincolle.entity.EntityCustomShipGeo;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CustomShipRenderer extends GeoEntityRenderer<EntityCustomShipGeo> {

    private boolean isCurrentlyReRendering = false;

    public CustomShipRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CustomShipGeoModel());
        this.addRenderLayer(new CustomShipGlowLayer(this));
    }

    @Override
    public void render(EntityCustomShipGeo entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        String id = entity.getCustomShipId();
        if (id.isEmpty() || !org.trp.shincolle.custom.CustomShipRegistry.hasShip(id) || org.trp.shincolle.custom.CustomShipGeoData.getBakedModel(id) == null) {
            entity.discard();
            return;
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(EntityCustomShipGeo animatable) {
        return 0.0F;
    }

    @Override
    public RenderType getRenderType(EntityCustomShipGeo animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, EntityCustomShipGeo animatable,
                                   BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        if (!isReRender) {
            float s = 0.4F;
            int level = Math.max(0, Math.min(animatable.getScaleLevel(), 3));
            s = s * (level + 1);
            float attributeScale = animatable.getScale();
            if (attributeScale > 0.0F) {
                s = s / attributeScale;
            }
            poseStack.scale(s, s, s);
        }

        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }

    @Override
    public void reRender(BakedGeoModel bakedModel, PoseStack poseStack, MultiBufferSource bufferSource, EntityCustomShipGeo animatable,
                         RenderType renderType, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay, int color) {
        this.isCurrentlyReRendering = true;
        try {
            super.reRender(bakedModel, poseStack, bufferSource, animatable, renderType, buffer, partialTick, packedLight, packedOverlay, color);
        } finally {
            this.isCurrentlyReRendering = false;
        }
    }

    @Override
    public void renderCubesOfBone(PoseStack poseStack, GeoBone bone, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        boolean isGlow = CustomShipGlowLayer.isGlowBone(bone);
        if (this.isCurrentlyReRendering) {
            if (!isGlow) {
                return;
            }
        } else {
            if (isGlow) {
                return;
            }
        }
        super.renderCubesOfBone(poseStack, bone, buffer, packedLight, packedOverlay, color);
    }
}
