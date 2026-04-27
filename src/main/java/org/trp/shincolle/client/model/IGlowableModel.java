package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public interface IGlowableModel {
    void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color);
}