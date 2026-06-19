package org.trp.shincolle.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.block.BlockChair;
import org.trp.shincolle.block.entity.ChairBlockEntity;
import org.trp.shincolle.client.model.ModelBlockChair;

public class RenderChair implements BlockEntityRenderer<ChairBlockEntity> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/block/blockchair.png");

    private final ModelBlockChair modelSingle;

    public RenderChair(BlockEntityRendererProvider.Context context) {
        this.modelSingle = new ModelBlockChair(context.bakeLayer(ModelBlockChair.LAYER_LOCATION));
    }

    @Override
    public void render(ChairBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        if (level == null) {
            renderSingle(poseStack, bufferSource, packedLight, packedOverlay, 90.0F);
            return;
        }

        BlockPos pos = blockEntity.getBlockPos();
        BlockState state = blockEntity.getBlockState();
        if (!state.hasProperty(BlockChair.FACING)) return;

        Direction facing = state.getValue(BlockChair.FACING);
        float angle = getYaw(facing);

        Direction dirRight = facing.getClockWise();
        Direction dirLeft = facing.getCounterClockWise();

        BlockPos rightPos = pos.relative(dirRight);
        boolean isRightAlreadyChained = this.canConnectTo(level, rightPos, dirRight, state);
        boolean connectToRight = this.canConnectTo(level, pos, dirRight, state) && !isRightAlreadyChained;
        boolean connectToLeft = this.canConnectTo(level, pos, dirLeft, state);

        if (connectToRight) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        modelSingle.renderToBuffer(poseStack, consumer, packedLight, packedOverlay, 0xFFFFFFFF);
        poseStack.popPose();
    }

    private void renderSingle(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, float angle) {
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        modelSingle.renderToBuffer(poseStack, consumer, packedLight, packedOverlay, 0xFFFFFFFF);
        poseStack.popPose();
    }

    private boolean canConnectTo(Level level, BlockPos pos, Direction direction, BlockState requiredState) {
        BlockPos neighborPos = pos.relative(direction);
        BlockState neighborState = level.getBlockState(neighborPos);
        if (neighborState.is(requiredState.getBlock())) {
            if (neighborState.hasProperty(BlockChair.FACING) && requiredState.hasProperty(BlockChair.FACING)) {
                return neighborState.getValue(BlockChair.FACING) == requiredState.getValue(BlockChair.FACING);
            }
        }
        return false;
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
