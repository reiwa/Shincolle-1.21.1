package org.trp.shincolle.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.block.LargeShipyardBlock;
import org.trp.shincolle.block.entity.LargeShipyardBlockEntity;
import org.trp.shincolle.client.model.ModelLargeShipyard;
import org.trp.shincolle.client.model.ModelVortex;

public class RenderLargeShipyard implements BlockEntityRenderer<LargeShipyardBlockEntity> {
    private static final ResourceLocation TEXTURE_BASE =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/block/blocklargeshipyard.png");
    private static final ResourceLocation VORTEX_OFF =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/block/modelvortex.png");
    private static final ResourceLocation VORTEX_ON =
            ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/block/modelvortexon.png");
    private static final float VORTEX_SCALE = 0.5F;

    private final ModelLargeShipyard modelBase;
    private final ModelVortex modelVortex;

    public RenderLargeShipyard(BlockEntityRendererProvider.Context context) {
        this.modelBase = new ModelLargeShipyard(context.bakeLayer(ModelLargeShipyard.LAYER_LOCATION));
        this.modelVortex = new ModelVortex(context.bakeLayer(ModelVortex.LAYER_LOCATION));
    }

    @Override
    public void render(LargeShipyardBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        BlockState state = blockEntity.getBlockState();
        boolean isActive = state.hasProperty(LargeShipyardBlock.ACTIVE) && state.getValue(LargeShipyardBlock.ACTIVE);

        float yaw = 0.0F;
        float pitch = 90.0F;
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            BlockPos pos = blockEntity.getBlockPos();
            double distX = pos.getX() + 0.5D - player.getX();
            double distY = pos.getY() - 0.75D - player.getY();
            double distZ = pos.getZ() + 0.5D - player.getZ();
            float horizontalDistance = (float) Math.sqrt(distX * distX + distZ * distZ);

            float currentYawDeg = (float) Math.atan2(distX, distZ) * Mth.RAD_TO_DEG;
            float currentPitchDeg = ((float) Math.atan2(horizontalDistance, distY) + (Mth.PI / 2.0F)) * Mth.RAD_TO_DEG;

            if (!blockEntity.hasRenderAngles()) {
                blockEntity.setRenderAngles(currentYawDeg, currentPitchDeg);
            }

            float interpFactor = 0.15F;
            yaw = blockEntity.getRenderYaw() + Mth.wrapDegrees(currentYawDeg - blockEntity.getRenderYaw()) * interpFactor;
            pitch = blockEntity.getRenderPitch() + Mth.wrapDegrees(currentPitchDeg - blockEntity.getRenderPitch()) * interpFactor;
            blockEntity.setRenderAngles(yaw, pitch);
        }

        float angle = player != null ? -((float) player.tickCount + partialTick) % 360.0F : 0.0F;
        if (isActive) {
            angle *= 5.0F;
        }

        poseStack.pushPose();
        poseStack.translate(0.5D, -0.2D, 0.5D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        poseStack.scale(1.0F, 1.2F, 1.0F);
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.entityCutout(TEXTURE_BASE));
        modelBase.renderToBuffer(poseStack, baseConsumer, packedLight, packedOverlay, 0xFFFFFFFF);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.5D, 0.5D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        poseStack.mulPose(Axis.XP.rotationDegrees(pitch));
        poseStack.mulPose(Axis.ZP.rotationDegrees(angle));
        poseStack.scale(VORTEX_SCALE, VORTEX_SCALE, VORTEX_SCALE);

        ResourceLocation texture = isActive ? VORTEX_ON : VORTEX_OFF;
        RenderType renderType = RenderType.beaconBeam(texture, true);

        VertexConsumer vortexConsumer = bufferSource.getBuffer(renderType);
        modelVortex.renderToBuffer(poseStack, vortexConsumer, LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(LargeShipyardBlockEntity blockEntity) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRender(LargeShipyardBlockEntity be, Vec3 cameraPos) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(LargeShipyardBlockEntity be) {
        return AABB.INFINITE;
    }
}
