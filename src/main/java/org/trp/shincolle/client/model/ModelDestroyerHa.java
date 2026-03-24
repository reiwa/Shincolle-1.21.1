package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

public class ModelDestroyerHa<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_ha"), "main");

    private final ModelPart Back;
    private final ModelPart NeckBack;
    private final ModelPart Body;
    private final ModelPart TailBack;
    private final ModelPart Head;
    private final ModelPart NeckBody;
    private final ModelPart HeadD01;
    private final ModelPart k00;
    private final ModelPart ToothU;
    private final ModelPart Face00;
    private final ModelPart Face01;
    private final ModelPart Face02;
    private final ModelPart HeadD02;
    private final ModelPart ToothL;
    private final ModelPart HeadD03;
    private final ModelPart k01;
    private final ModelPart k02;
    private final ModelPart k03;
    private final ModelPart LegLeftFront;
    private final ModelPart LegRightFront;
    private final ModelPart LegLeftEnd;
    private final ModelPart LegRightEnd;
    private final ModelPart TailEnd1;
    private final ModelPart TailEnd2;
    private final ModelPart GlowBack;
    private final ModelPart GlowNeckBack;
    private final ModelPart GlowHead;
    private float poseTranslateY;

    public ModelDestroyerHa(ModelPart root) {
        this.Back = root.getChild("Back");
        this.TailBack = this.Back.getChild("TailBack");
        this.TailEnd1 = this.TailBack.getChild("TailEnd1");
        this.TailEnd2 = this.TailBack.getChild("TailEnd2");
        this.NeckBack = this.Back.getChild("NeckBack");
        this.Head = this.NeckBack.getChild("Head");
        this.ToothU = this.Head.getChild("ToothU");
        this.HeadD01 = this.Head.getChild("HeadD01");
        this.HeadD02 = this.HeadD01.getChild("HeadD02");
        this.ToothL = this.HeadD02.getChild("ToothL");
        this.HeadD03 = this.HeadD02.getChild("HeadD03");
        this.NeckBody = this.NeckBack.getChild("NeckBody");
        this.Body = this.Back.getChild("Body");
        this.LegRightFront = this.Body.getChild("LegRightFront");
        this.LegRightEnd = this.LegRightFront.getChild("LegRightEnd");
        this.LegLeftFront = this.Body.getChild("LegLeftFront");
        this.LegLeftEnd = this.LegLeftFront.getChild("LegLeftEnd");
        this.GlowBack = root.getChild("GlowBack");
        this.GlowNeckBack = this.GlowBack.getChild("GlowNeckBack");
        this.GlowHead = this.GlowNeckBack.getChild("GlowHead");
        this.Face00 = this.GlowHead.getChild("Face00");
        this.Face01 = this.GlowHead.getChild("Face01");
        this.Face02 = this.GlowHead.getChild("Face02");
        this.k00 = this.GlowHead.getChild("k00");
        this.k01 = this.k00.getChild("k01");
        this.k02 = this.k00.getChild("k02");
        this.k03 = this.k00.getChild("k03");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Back = partdefinition.addOrReplaceChild("Back", CubeListBuilder.create().texOffs(20, 73).addBox(-12F, -12F, -14F, 24F, 22F, 28F, new CubeDeformation(0F)), PartPose.offset(0F, -22F, 0F));

        PartDefinition TailBack = Back.addOrReplaceChild("TailBack", CubeListBuilder.create().texOffs(30, 79).addBox(-10F, -4F, 0F, 20F, 17F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 9F, 0.0873F, 0F, 0F));

        PartDefinition TailEnd1 = TailBack.addOrReplaceChild("TailEnd1", CubeListBuilder.create().texOffs(36, 81).addBox(-8F, -3F, 0F, 16F, 12F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 19F, 0.1745F, 0F, 0F));

        PartDefinition TailEnd2 = TailBack.addOrReplaceChild("TailEnd2", CubeListBuilder.create().texOffs(42, 85).addBox(-7F, -5F, 0F, 14F, 10F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 20F, -0.5236F, 0F, 0F));

        PartDefinition NeckBack = Back.addOrReplaceChild("NeckBack", CubeListBuilder.create().texOffs(24, 79).addBox(-13F, -10F, -20F, 26F, 26F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.5F, -11F, -0.0873F, 0F, 0F));

        PartDefinition Head = NeckBack.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(16, 75).addBox(-13.5F, -14F, -28F, 27F, 27F, 26F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -13F, -0.1745F, 0F, 0F));

        PartDefinition ToothU = Head.addOrReplaceChild("ToothU", CubeListBuilder.create().texOffs(0, 0).addBox(-11F, 0F, 0F, 22F, 7F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -28.5F, 0.0524F, 0F, 0F));

        PartDefinition HeadD01 = Head.addOrReplaceChild("HeadD01", CubeListBuilder.create().texOffs(45, 94).addBox(-12F, 0F, -3F, 24F, 16F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, -3F, -0.1396F, 0F, 0F));

        PartDefinition HeadD02 = HeadD01.addOrReplaceChild("HeadD02", CubeListBuilder.create().texOffs(27, 77).addBox(-10.5F, 0F, -21F, 21F, 8F, 24F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, -1.5F, 0.3491F, 0F, 0F));

        PartDefinition ToothL = HeadD02.addOrReplaceChild("ToothL", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-11F, 0F, -22F, 22F, 7F, 22F, new CubeDeformation(0F)), PartPose.offset(0F, 1F, 0.5F));

        PartDefinition HeadD03 = HeadD02.addOrReplaceChild("HeadD03", CubeListBuilder.create().texOffs(44, 83).addBox(-5F, 0F, 0F, 10F, 10F, 18F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, -28F, 0.3491F, 0F, 0F));

        PartDefinition NeckBody = NeckBack.addOrReplaceChild("NeckBody", CubeListBuilder.create().texOffs(46, 34).addBox(-9F, 0F, -9F, 18F, 11F, 22F, new CubeDeformation(0F)), PartPose.offset(0F, 15F, -8F));

        PartDefinition Body = Back.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(44, 32).addBox(-9F, 0F, 0F, 18F, 14F, 24F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, -18F, 0.1745F, 0F, 0F));

        PartDefinition LegRightFront = Body.addOrReplaceChild("LegRightFront", CubeListBuilder.create().texOffs(66, 46).addBox(-5F, -4F, -5F, 10F, 16F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 7F, 14F, -0.5236F, 0F, 0F));

        PartDefinition LegRightEnd = LegRightFront.addOrReplaceChild("LegRightEnd", CubeListBuilder.create().texOffs(70, 48).addBox(-4F, -3F, -4F, 8F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, 0.6981F, 0F, 0F));

        PartDefinition LegLeftFront = Body.addOrReplaceChild("LegLeftFront", CubeListBuilder.create().texOffs(66, 46).addBox(-5F, -4F, -5F, 10F, 16F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 7F, 14F, -0.5236F, 0F, 0F));

        PartDefinition LegLeftEnd = LegLeftFront.addOrReplaceChild("LegLeftEnd", CubeListBuilder.create().texOffs(70, 48).addBox(-4F, -3F, -4F, 8F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, 0.6981F, 0F, 0F));

        PartDefinition GlowBack = partdefinition.addOrReplaceChild("GlowBack", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -22F, 0F));

        PartDefinition GlowNeckBack = GlowBack.addOrReplaceChild("GlowNeckBack", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -2.5F, -11F, -0.0873F, 0F, 0F));

        PartDefinition GlowHead = GlowNeckBack.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 3F, -13F, -0.1745F, 0F, 0F));

        PartDefinition Face00 = GlowHead.addOrReplaceChild("Face00", CubeListBuilder.create().texOffs(0, 81).addBox(-10F, 0F, 0F, 20F, 20F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, -28.1F));

        PartDefinition Face01 = GlowHead.addOrReplaceChild("Face01", CubeListBuilder.create().texOffs(0, 61).addBox(-10F, 0F, 0F, 20F, 20F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, -28.2F));

        PartDefinition Face02 = GlowHead.addOrReplaceChild("Face02", CubeListBuilder.create().texOffs(0, 41).addBox(-10F, 0F, 0F, 20F, 20F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, -28.3F));

        PartDefinition k00 = GlowHead.addOrReplaceChild("k00", CubeListBuilder.create().texOffs(102, 84).addBox(0F, 0F, 0F, 5F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(13F, -8F, -10F, 0F, 0.1745F, 0F));

        PartDefinition k01 = k00.addOrReplaceChild("k01", CubeListBuilder.create().texOffs(90, 0).addBox(1F, -18.5F, 1F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -0.5236F, 0F, 0F));

        PartDefinition k02 = k00.addOrReplaceChild("k02", CubeListBuilder.create().texOffs(90, 0).addBox(0.8F, -25F, -0.7F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.3963F, 0F, 0F));

        PartDefinition k03 = k00.addOrReplaceChild("k03", CubeListBuilder.create().texOffs(90, 0).addBox(0.6F, -24.5F, -2.5F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -2.0944F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        poseTranslateY = 0.0F;
        if (!(entity instanceof EntityShipBase ship)) {
            return;
        }

        float angleX = Mth.cos(ageInTicks * 0.125F);
        if (ship.getShipDepth() > 0.0D) {
            poseTranslateY += angleX * 0.05F + 0.025F;
        }

        if (ship.isInDeadPose()) {
            applyStopPose(ship);
            syncGlowParts();
            return;
        }

        Back.xRot = -0.1F;
        Back.zRot = 0.0F;
        NeckBack.xRot = -0.15F;
        NeckBack.yRot = 0.0F;
        Head.xRot = -0.2F;
        Head.yRot = 0.0F;
        LegLeftFront.zRot = 0.0F;
        LegLeftEnd.zRot = 0.0F;
        LegRightFront.zRot = 0.0F;

        applyFaceFromEntity(ship);
        applyLook(netHeadYaw, headPitch, angleX);
        if (ship.isInSittingPose()) {
            applySittingPose(ship, ageInTicks);
        } else {
            applyTailPose(angleX);
            applyLegPose(limbSwing, limbSwingAmount);
        }
        syncGlowParts();
    }

    private void applyFaceFromEntity(EntityShipBase ship) {
        int faceId = ship.getFaceId();
        int faceIndex = faceId >= 0 ? faceId % 3 : 0;
        setFace(faceIndex);
    }

    private void setFace(int faceIndex) {
        Face00.visible = faceIndex == 0;
        Face01.visible = faceIndex == 1;
        Face02.visible = faceIndex == 2;
    }

    private void applyLook(float headYaw, float headPitch, float angleX) {
        if (headPitch != 0.0F) {
            NeckBack.xRot = headPitch * 0.005F;
            NeckBack.yRot = headYaw * 0.005F;
            Head.xRot = headPitch * 0.005F;
            Head.yRot = headYaw * 0.005F;
            HeadD01.xRot = angleX * 0.05F - 0.05F;
            TailBack.xRot = 0.15F;
            TailBack.yRot = headYaw * -0.005F;
            TailEnd1.xRot = 0.2F;
            TailEnd1.yRot = headYaw * -0.005F;
        } else {
            HeadD01.xRot = angleX * 0.05F - 0.05F;
        }
    }

    private void applyStopPose(EntityShipBase ship) {
        poseTranslateY += 0.5F;
        setFace(2);
        Back.xRot = 0.0F;
        Back.zRot = -1.66F;
        NeckBack.xRot = 0.1745F;
        NeckBack.yRot = 0.0F;
        Head.xRot = 0.1745F;
        Head.yRot = 0.0F;
        HeadD01.xRot = 0.1745F;
        TailBack.xRot = 0.4F;
        TailBack.yRot = 0.0F;
        TailEnd1.xRot = 0.4F;
        TailEnd1.yRot = 0.0F;
        LegLeftFront.xRot = 0.35F;
        LegLeftFront.zRot = 0.52F;
        LegLeftEnd.xRot = 0.0F;
        LegLeftEnd.zRot = 0.52F;
        LegRightFront.xRot = -0.2F;
        LegRightFront.zRot = 0.087F;
        LegRightEnd.xRot = 0.52F;
    }

    private void applySittingPose(EntityShipBase ship, float ageInTicks) {
        float angle1 = Mth.cos(ageInTicks);
        if (ship.getEmotionSecondary() == EntityShipBase.EMOTION_BORED) {
            setFace(1);
            poseTranslateY += 0.4F;
            Back.xRot = -0.8F;
            NeckBack.xRot = -0.2618F;
            Head.xRot = -0.2618F;
            HeadD01.xRot = -angle1 * 0.05F + 0.2618F;
            LegRightFront.xRot = -0.7F;
            LegLeftFront.xRot = angle1 * 0.5F - 2.5F;
            LegRightEnd.xRot = 0.35F;
            LegLeftEnd.xRot = angle1 * 0.3F + 0.7F;
            TailBack.xRot = 0.35F;
            TailEnd1.xRot = 0.35F;
        } else {
            poseTranslateY += 0.5F;
            Back.xRot = 0.0F;
            Back.zRot = -1.5708F;
            NeckBack.xRot = 0.1745F;
            Head.xRot = 0.1745F;
            HeadD01.xRot = 0.1745F;
            LegRightFront.xRot = 0.0F;
            LegLeftFront.xRot = 0.5F;
            LegRightEnd.xRot = 1.7F;
            LegLeftEnd.xRot = 1.5F;
            TailBack.xRot = -0.7F;
            TailEnd1.xRot = -0.5F;
        }
    }

    private void applyTailPose(float angleX) {
        TailBack.xRot = angleX * 0.05F + 0.1745F;
        TailEnd1.xRot = angleX * 0.1F + 0.2618F;
    }

    private void applyLegPose(float limbSwing, float limbSwingAmount) {
        float angle1 = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        float angle2 = Mth.sin(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        LegRightFront.xRot = angle1 - 0.5F;
        LegLeftFront.xRot = -angle1 - 0.5F;
        LegRightEnd.xRot = angle2 + 1.0F;
        LegLeftEnd.xRot = -angle2 + 1.0F;
    }

    private void syncGlowParts() {
        GlowBack.copyFrom(Back);
        GlowNeckBack.copyFrom(NeckBack);
        GlowHead.copyFrom(Head);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, poseTranslateY, 0.0F);
        }

        Back.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        GlowBack.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
