package org.trp.shincolle.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.client.renderer.ShincolleRenderTypes;
import org.trp.shincolle.entity.EntityCustomShipGeo;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CustomShipGlowLayer extends GeoRenderLayer<EntityCustomShipGeo> {

    public CustomShipGlowLayer(GeoRenderer<EntityCustomShipGeo> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, EntityCustomShipGeo animatable, BakedGeoModel bakedModel, RenderType renderType,
                       MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (animatable.isInvisible()) {
            return;
        }

        ResourceLocation texture = getTextureResource(animatable);
        if (texture == null) {
            return;
        }

        RenderType glowRenderType = ShincolleRenderTypes.getFlatGlow(texture);
        VertexConsumer glowConsumer = bufferSource.getBuffer(glowRenderType);

        getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, glowRenderType, glowConsumer,
                partialTick, LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);
    }

    public static boolean isGlowBone(GeoBone bone) {
        if (bone == null) {
            return false;
        }
        String selfName = bone.getName();
        if (selfName != null) {
            String lowerSelf = selfName.toLowerCase();
            if (lowerSelf.startsWith("face") || lowerSelf.startsWith("mouth") || lowerSelf.startsWith("flush")) {
                return true;
            }
        }
        GeoBone current = bone;
        while (current != null) {
            String name = current.getName();
            if (name != null) {
                String lower = name.toLowerCase();
                if (lower.contains("glow") || lower.contains("lightef")) {
                    return true;
                }
            }
            current = current.getParent();
        }
        return false;
    }
}
