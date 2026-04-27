package org.trp.shincolle.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.trp.shincolle.client.model.IGlowableModel;
import org.trp.shincolle.client.renderer.ShincolleRenderTypes;

public class GenericGlowLayer<T extends LivingEntity, M extends EntityModel<T> & IGlowableModel> extends RenderLayer<T, M> {

    private final ResourceLocation glowTexture;

    public GenericGlowLayer(RenderLayerParent<T, M> renderer, ResourceLocation glowTexture) {
        super(renderer);
        this.glowTexture = glowTexture;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        VertexConsumer vertexConsumer = bufferSource.getBuffer(ShincolleRenderTypes.getFlatGlow(this.glowTexture));

        this.getParentModel().renderGlow(
                poseStack,
                vertexConsumer,
                LightTexture.FULL_BRIGHT,
                OverlayTexture.NO_OVERLAY,
                0xFFFFFFFF
        );
    }
}