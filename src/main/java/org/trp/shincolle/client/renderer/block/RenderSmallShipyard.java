package org.trp.shincolle.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.block.SmallShipyardBlock;
import org.trp.shincolle.block.entity.SmallShipyardBlockEntity;
import org.trp.shincolle.client.model.ModelSmallShipyard;

public class RenderSmallShipyard implements BlockEntityRenderer<SmallShipyardBlockEntity> {
    private static final ResourceLocation TEXTURE_ON =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/block/blocksmallshipyardon.png");
    private static final ResourceLocation TEXTURE_OFF =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/block/blocksmallshipyardoff.png");

    private final ModelSmallShipyard model;

    public RenderSmallShipyard(BlockEntityRendererProvider.Context context) {
        this.model = new ModelSmallShipyard(context.bakeLayer(ModelSmallShipyard.LAYER_LOCATION));
    }

    @Override
    public void render(SmallShipyardBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        BlockState state = blockEntity.getBlockState();
        Direction facing = state.hasProperty(SmallShipyardBlock.FACING)
                ? state.getValue(SmallShipyardBlock.FACING)
                : Direction.NORTH;
        boolean isActive = state.hasProperty(SmallShipyardBlock.ACTIVE) && state.getValue(SmallShipyardBlock.ACTIVE);
        ResourceLocation texture = isActive ? TEXTURE_ON : TEXTURE_OFF;

        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(getYaw(facing)));

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
        model.renderToBuffer(poseStack, consumer, packedLight, packedOverlay, 0xFFFFFFFF);
        poseStack.popPose();
    }

    private static float getYaw(Direction direction) {
        return switch (direction) {
            case EAST -> 90.0F;
            case SOUTH -> 180.0F;
            case WEST -> -90.0F;
            default -> 0.0F;
        };
    }
}
