package org.trp.shincolle.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.client.model.ShipModelBaseAdv;
import org.trp.shincolle.entity.base.EntityShipBase;

public class ShipHeldItemLayer<T extends EntityShipBase, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public ShipHeldItemLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity,
                       float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks,
                       float netHeadYaw, float headPitch) {
        ItemStack main = entity.getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack off = entity.getItemBySlot(EquipmentSlot.OFFHAND);
        if (main.isEmpty() && off.isEmpty()) {
            return;
        }

        if (!main.isEmpty()) {
            renderHeldItem(entity, main, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT,
                    poseStack, bufferSource, packedLight);
        }
        if (!off.isEmpty()) {
            renderHeldItem(entity, off, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT,
                    poseStack, bufferSource, packedLight);
        }
    }

    private void renderHeldItem(T entity, ItemStack stack, ItemDisplayContext displayContext, HumanoidArm side,
                                PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (stack.isEmpty()) {
            return;
        }
        if (!(this.getParentModel() instanceof ShipModelBaseAdv<?> shipModel)) {
            return;
        }

        poseStack.pushPose();

        
        if (entity.isCrouching()) {
            poseStack.translate(0.0F, 0.2F, 0.0F);
        }

        float poseTranslateY = shipModel.getPoseTranslateY();
        if (poseTranslateY != 0.0F) {
            poseStack.translate(0.0F, poseTranslateY, 0.0F);
        }

        boolean isBlock = stack.getItem() instanceof BlockItem;
        float[] offset = shipModel.getHeldItemOffset(entity, side, isBlock);
        float[] rotate = shipModel.getHeldItemRotate(entity, side, isBlock);
        float modelScale = shipModel.getScale(entity);
        boolean left = side == HumanoidArm.LEFT;

        
        float ox = (offset[0] + org.trp.shincolle.Config.offsetHeldItemX) / 16.0F;
        float oy = (offset[1] + org.trp.shincolle.Config.offsetHeldItemY) / 16.0F;
        float oz = (offset[2] + org.trp.shincolle.Config.offsetHeldItemZ) / 16.0F;

        poseStack.translate(ox * (left ? -1.0F : 1.0F), oy, oz);

        
        shipModel.translateToHand(side, poseStack);

        
        
        
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F + rotate[0]));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F + rotate[1]));
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotate[2]));

        
        
        poseStack.translate((left ? -1.0F : 1.0F) / 16.0F, 0.3125F, -0.625F);

        
        
        float itemScale = org.trp.shincolle.Config.scaleHeldItem;
        if (isBlock) {
             poseStack.scale(itemScale * 0.75F, itemScale * 0.75F, itemScale * 0.75F);
        } else {
             poseStack.scale(itemScale, itemScale, itemScale);
        }

        Minecraft.getInstance().getItemRenderer().renderStatic(
                entity,
                stack,
                displayContext,
                left,
                poseStack,
                bufferSource,
                entity.level(),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                entity.getId()
        );

        poseStack.popPose();
    }
}
