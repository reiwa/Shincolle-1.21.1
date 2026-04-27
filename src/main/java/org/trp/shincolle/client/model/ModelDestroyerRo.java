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

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelDestroyerRo<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_ro"), "main");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelDestroyerRo");
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerRo");

    private final ModelPart Back;
    private final ModelPart NeckBack;
    private final ModelPart Body;
    private final ModelPart TailBack;
    private final ModelPart LegLeftFront;
    private final ModelPart LegRightFront;
    private final ModelPart BodyTurbine;
    private final ModelPart Head;
    private final ModelPart NeckBody;
    private final ModelPart HeadD03;
    private final ModelPart HeadU01;
    private final ModelPart HeadD01;
    private final ModelPart FaceL00;
    private final ModelPart FaceL01;
    private final ModelPart FaceL02;
    private final ModelPart FaceR00;
    private final ModelPart FaceR01;
    private final ModelPart FaceR02;
    private final ModelPart k00;
    private final ModelPart HeadD04;
    private final ModelPart UpperTooth;
    private final ModelPart HeadU02;
    private final ModelPart LowerTooth;
    private final ModelPart k01;
    private final ModelPart k02;
    private final ModelPart k03;
    private final ModelPart tube01;
    private final ModelPart tube02;
    private final ModelPart tube03;
    private final ModelPart TailEnd;
    private final ModelPart TailBack01;
    private final ModelPart TailBack02;
    private final ModelPart LegLeftEnd;
    private final ModelPart LegRightEnd;
    private final ModelPart GlowBack;
    private final ModelPart GlowNeckBack;
    private final ModelPart GlowHead;
    private float poseTranslateY;

    public ModelDestroyerRo(ModelPart root) {
        this.Back = root.getChild("Back");
        this.TailBack = this.Back.getChild("TailBack");
        this.TailBack02 = this.TailBack.getChild("TailBack02");
        this.TailBack01 = this.TailBack.getChild("TailBack01");
        this.TailEnd = this.TailBack.getChild("TailEnd");
        this.LegRightFront = this.Back.getChild("LegRightFront");
        this.LegRightEnd = this.LegRightFront.getChild("LegRightEnd");
        this.Body = this.Back.getChild("Body");
        this.LegLeftFront = this.Back.getChild("LegLeftFront");
        this.LegLeftEnd = this.LegLeftFront.getChild("LegLeftEnd");
        this.NeckBack = this.Back.getChild("NeckBack");
        this.Head = this.NeckBack.getChild("Head");
        this.HeadD04 = this.Head.getChild("HeadD04");
        this.HeadU01 = this.Head.getChild("HeadU01");
        this.HeadU02 = this.HeadU01.getChild("HeadU02");
        this.UpperTooth = this.HeadU01.getChild("UpperTooth");
        this.HeadD01 = this.Head.getChild("HeadD01");
        this.LowerTooth = this.HeadD01.getChild("LowerTooth");
        this.HeadD03 = this.NeckBack.getChild("HeadD03");
        this.NeckBody = this.NeckBack.getChild("NeckBody");
        this.tube01 = this.NeckBody.getChild("tube01");
        this.tube03 = this.tube01.getChild("tube03");
        this.tube02 = this.tube01.getChild("tube02");
        this.BodyTurbine = this.Back.getChild("BodyTurbine");
        this.GlowBack = root.getChild("GlowBack");
        this.GlowNeckBack = this.GlowBack.getChild("GlowNeckBack");
        this.GlowHead = this.GlowNeckBack.getChild("GlowHead");
        this.FaceL00 = this.GlowHead.getChild("FaceL00");
        this.FaceL01 = this.GlowHead.getChild("FaceL01");
        this.FaceL02 = this.GlowHead.getChild("FaceL02");
        this.FaceR00 = this.GlowHead.getChild("FaceR00");
        this.FaceR01 = this.GlowHead.getChild("FaceR01");
        this.FaceR02 = this.GlowHead.getChild("FaceR02");
        this.k00 = this.GlowHead.getChild("k00");
        this.k01 = this.k00.getChild("k01");
        this.k02 = this.k00.getChild("k02");
        this.k03 = this.k00.getChild("k03");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Back = partdefinition.addOrReplaceChild("Back", CubeListBuilder.create().texOffs(2, 32).addBox(-12F, -12F, -14F, 24F, 22F, 28F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -16F, 0F, -0.2618F, 0F, 0F));

        PartDefinition TailBack = Back.addOrReplaceChild("TailBack", CubeListBuilder.create().texOffs(12, 38).addBox(-10F, -8F, 0F, 20F, 14F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, 11F, -0.0873F, 0F, 0F));

        PartDefinition TailBack02 = TailBack.addOrReplaceChild("TailBack02", CubeListBuilder.create().texOffs(30, 40).addBox(-2F, 0F, 0F, 4F, 10F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 0F, 15F, -1.0472F, 0F, -0.4014F));

        PartDefinition TailBack01 = TailBack.addOrReplaceChild("TailBack01", CubeListBuilder.create().texOffs(30, 40).addBox(-2F, 0F, 0F, 4F, 10F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 0F, 15F, -1.0472F, 0F, 0.4014F));

        PartDefinition TailEnd = TailBack.addOrReplaceChild("TailEnd", CubeListBuilder.create().texOffs(14, 36).addBox(-8F, -6.5F, 0F, 16F, 10F, 24F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 19F, -0.0873F, 0F, 0F));

        PartDefinition LegRightFront = Back.addOrReplaceChild("LegRightFront", CubeListBuilder.create().texOffs(20, 104).addBox(-4F, -4F, -4F, 8F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, 12F, -3F, 0.7854F, 0F, 0F));

        PartDefinition LegRightEnd = LegRightFront.addOrReplaceChild("LegRightEnd", CubeListBuilder.create().texOffs(24, 106).addBox(-3F, -3F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, 0.5236F, 0F, 0F));

        PartDefinition Body = Back.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(4, 96).addBox(-8F, 0F, 0F, 16F, 7F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, -10F, 0.5236F, 0F, 0F));

        PartDefinition LegLeftFront = Back.addOrReplaceChild("LegLeftFront", CubeListBuilder.create().texOffs(20, 104).addBox(-4F, -4F, -4F, 8F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, 12F, -3F, 0.7854F, 0F, 0F));

        PartDefinition LegLeftEnd = LegLeftFront.addOrReplaceChild("LegLeftEnd", CubeListBuilder.create().texOffs(24, 106).addBox(-3F, -3F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, 0.5236F, 0F, 0F));

        PartDefinition NeckBack = Back.addOrReplaceChild("NeckBack", CubeListBuilder.create().texOffs(8, 40).addBox(-13F, -11F, -20F, 26F, 26F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, -12F, 0.0873F, 0F, 0F));

        PartDefinition Head = NeckBack.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(6, 42).addBox(-15F, -12F, -16F, 30F, 27F, 18F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -17.5F, 0.2618F, 0F, 0F));

        PartDefinition HeadD04 = Head.addOrReplaceChild("HeadD04", CubeListBuilder.create().texOffs(2, 94).addBox(-8F, 0F, 0F, 16F, 12F, 18F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -15F, -0.2618F, 0F, 0F));

        PartDefinition HeadU01 = Head.addOrReplaceChild("HeadU01", CubeListBuilder.create().texOffs(6, 40).addBox(-14F, -21F, -9F, 28F, 16F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -19F, -0.0873F, 0F, 0F));

        PartDefinition HeadU02 = HeadU01.addOrReplaceChild("HeadU02", CubeListBuilder.create().texOffs(6, 40).addBox(-14F, 0F, 0F, 28F, 15F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -20F, -23F, 0.0873F, 0F, 0F));

        PartDefinition UpperTooth = HeadU01.addOrReplaceChild("UpperTooth", CubeListBuilder.create().texOffs(0, 0).addBox(-12F, 0F, 0F, 24F, 10F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6F, -15F, 0.3491F, 0F, 0F));

        PartDefinition HeadD01 = Head.addOrReplaceChild("HeadD01", CubeListBuilder.create().texOffs(0, 34).addBox(-13F, 1.5F, -25F, 26F, 10F, 28F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, -10.3F, 0.6981F, 0F, 0F));

        PartDefinition LowerTooth = HeadD01.addOrReplaceChild("LowerTooth", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-12F, 0F, 0F, 24F, 10F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, -5.5F, -3.4907F, 0F, 0F));

        PartDefinition HeadD03 = NeckBack.addOrReplaceChild("HeadD03", CubeListBuilder.create().texOffs(2, 94).addBox(-8.5F, 0F, 0F, 17F, 12F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10.3F, -23F, -0.0524F, 0F, 0F));

        PartDefinition NeckBody = NeckBack.addOrReplaceChild("NeckBody", CubeListBuilder.create().texOffs(0, 94).addBox(-9F, 0F, -9F, 18F, 14F, 18F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -9F, 0.3491F, 0F, 0F));

        PartDefinition tube01 = NeckBody.addOrReplaceChild("tube01", CubeListBuilder.create().texOffs(31, 40).addBox(-1.5F, 0F, 0F, 3F, 3F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 3F, -0.8727F, 0F, 0F));

        PartDefinition tube03 = tube01.addOrReplaceChild("tube03", CubeListBuilder.create().texOffs(24, 32).addBox(-1F, 0F, 0F, 2F, 2F, 28F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, 1.5F, 18F, 1.0472F, -0.1396F, 0F));

        PartDefinition tube02 = tube01.addOrReplaceChild("tube02", CubeListBuilder.create().texOffs(24, 32).addBox(-1F, 0F, 0F, 2F, 2F, 28F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, 1.5F, 18F, 1.0472F, 0.1396F, 0F));

        PartDefinition BodyTurbine = Back.addOrReplaceChild("BodyTurbine", CubeListBuilder.create().texOffs(86, 89).addBox(-4.5F, 0F, 0F, 9F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -2F, -0.5236F, 0F, 0F));

        PartDefinition GlowBack = partdefinition.addOrReplaceChild("GlowBack", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -16F, 0F, -0.2618F, 0F, 0F));

        PartDefinition GlowNeckBack = GlowBack.addOrReplaceChild("GlowNeckBack", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -3F, -12F, 0.0873F, 0F, 0F));

        PartDefinition GlowHead = GlowNeckBack.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 0F, -17.5F, 0.2618F, 0F, 0F));

        PartDefinition FaceL00 = GlowHead.addOrReplaceChild("FaceL00", CubeListBuilder.create().texOffs(96, 96).addBox(0F, 0F, 0F, 0F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(15.1F, -8F, -16F));

        PartDefinition FaceL01 = GlowHead.addOrReplaceChild("FaceL01", CubeListBuilder.create().texOffs(96, 0).addBox(0F, 0F, 0F, 0F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(15.1F, -8F, -16F));

        PartDefinition FaceL02 = GlowHead.addOrReplaceChild("FaceL02", CubeListBuilder.create().texOffs(96, 16).addBox(0F, 0F, 0F, 0F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(15.1F, -8F, -16F));

        PartDefinition FaceR00 = GlowHead.addOrReplaceChild("FaceR00", CubeListBuilder.create().texOffs(96, 96).addBox(0F, 0F, 0F, 0F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(-15.1F, -8F, -16F));

        PartDefinition FaceR01 = GlowHead.addOrReplaceChild("FaceR01", CubeListBuilder.create().texOffs(96, 0).addBox(0F, 0F, 0F, 0F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(-15.1F, -8F, -16F));

        PartDefinition FaceR02 = GlowHead.addOrReplaceChild("FaceR02", CubeListBuilder.create().texOffs(96, 16).addBox(0F, 0F, 0F, 0F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(-15.1F, -8F, -16F));

        PartDefinition k00 = GlowHead.addOrReplaceChild("k00", CubeListBuilder.create().texOffs(54, 94).addBox(0F, 0F, 0F, 5F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, -10F, 0F, 0F, 0.1745F, 0F));

        PartDefinition k01 = k00.addOrReplaceChild("k01", CubeListBuilder.create().texOffs(72, 102).addBox(1F, -18.5F, 1F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -0.5236F, 0F, 0F));

        PartDefinition k02 = k00.addOrReplaceChild("k02", CubeListBuilder.create().texOffs(72, 102).addBox(0.8F, -25F, -0.7F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.3963F, 0F, 0F));

        PartDefinition k03 = k00.addOrReplaceChild("k03", CubeListBuilder.create().texOffs(72, 102).addBox(0.6F, -24.5F, -2.5F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -2.0944F, 0F, 0F));

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
            applyDeadPose(ship);
            syncGlowParts();
            return;
        }

        Back.xRot = -0.2618F;
        Back.yRot = 0.0F;
        Back.zRot = 0.0F;
        NeckBack.xRot = 0.0873F;
        Head.xRot = 0.3F;
        LegRightFront.yRot = 0.0F;
        LegLeftFront.yRot = 0.0F;

        applyFaceFromEntity(ship);
        applyLook(netHeadYaw, headPitch, angleX);
        if (ship.isInSittingPose()) {
            applySittingPose(ship, angleX);
        } else {
            applyLegPose(ship, limbSwing, limbSwingAmount, angleX);
            applyTailPose(angleX);
        }
        syncGlowParts();
    }

    private void applyFaceFromEntity(EntityShipBase ship) {
        int faceId = ship.getFaceId();
        int faceIndex = faceId >= 0 ? faceId % 3 : 0;
        setFace(faceIndex);
    }

    private void setFace(int faceIndex) {
        FaceL00.visible = faceIndex == 0;
        FaceR00.visible = faceIndex == 0;
        FaceL01.visible = faceIndex == 1;
        FaceR01.visible = faceIndex == 1;
        FaceL02.visible = faceIndex == 2;
        FaceR02.visible = faceIndex == 2;
    }

    private void applyLook(float headYaw, float headPitch, float angleX) {
        if (headPitch != 0.0F) {
            NeckBack.xRot = headPitch * 0.005F;
            NeckBack.yRot = headYaw * 0.005F;
            Head.xRot = headPitch * 0.005F;
            Head.yRot = headYaw * 0.005F;
            TailBack.xRot = 0.1F;
            TailBack.yRot = headYaw * -0.005F;
            TailEnd.xRot = 0.1F;
            TailEnd.yRot = headYaw * -0.005F;
            tube01.xRot = headPitch * -0.005F - 0.8727F;
            tube01.yRot = headYaw * -0.005F;
        } else {
            Head.xRot = angleX * 0.08F + 0.3F;
            HeadD01.xRot = angleX * 0.05F + 0.7F;
            NeckBack.xRot = 0.0873F;
            NeckBack.yRot = 0.0F;
            Head.yRot = 0.0F;
            TailBack.yRot = 0.0F;
            TailEnd.yRot = 0.0F;
            tube01.xRot = -0.8727F;
            tube01.yRot = 0.0F;
        }
    }

    private void applyDeadPose(EntityShipBase ship) {
        this.poseTranslateY = DEAD_TRANSLATE_Y;
        setFace(1);
        HeadD01.xRot = 0.7F;
        NeckBack.xRot = 0.0F;
        NeckBack.yRot = 0.1F;
        Head.xRot = 0.1F;
        Head.yRot = 0.1F;
        Back.xRot = 0.0F;
        Back.yRot = (float) Math.PI;
        Back.zRot = (float) Math.PI;
        LegRightFront.xRot = 1.57F;
        LegRightFront.yRot = -0.52F;
        LegLeftFront.xRot = 1.57F;
        LegLeftFront.yRot = 0.52F;
        LegRightEnd.xRot = 1.0F;
        LegLeftEnd.xRot = 1.0F;
        TailBack.xRot = 0.1F;
        TailBack.yRot = -0.15F;
        TailEnd.xRot = 0.1F;
        TailEnd.yRot = -0.15F;
        tube01.xRot = -0.8F;
        tube01.yRot = -0.12F;
    }

    private void applySittingPose(EntityShipBase ship, float angleX) {
        this.poseTranslateY = SITTING_TRANSLATE_Y;
        poseTranslateY += 0.45F;
        if (ship.getEmotionSecondary() == EntityShipBase.EMOTION_BORED) {
            setFace(2);
            Back.xRot = 0.0F;
            Back.yRot = (float) Math.PI;
            Back.zRot = (float) Math.PI;
            Head.xRot = angleX * 0.08F + 0.35F;
            LegRightFront.xRot = angleX * 0.3F + 0.5F;
            LegLeftFront.xRot = -angleX * 0.3F + 0.5F;
            LegRightEnd.xRot = angleX * 0.3F + 0.5F;
            LegLeftEnd.xRot = -angleX * 0.3F + 0.5F;
            TailBack.xRot = -0.3F;
            TailBack.yRot = angleX * 0.3F;
            TailEnd.xRot = -0.3F;
            TailEnd.yRot = angleX * 0.5F;
            tube01.xRot = -0.8F;
        } else {
            Back.xRot = -0.7F;
            Head.xRot = angleX * 0.08F + 0.35F;
            LegRightFront.xRot = -0.6981F;
            LegLeftFront.xRot = -0.6981F;
            LegRightEnd.xRot = 0.1745F;
            LegLeftEnd.xRot = 0.1745F;
            TailBack.xRot = 0.5F;
            TailBack.yRot = angleX * 0.3F;
            TailEnd.xRot = 0.6F;
            TailEnd.yRot = angleX * 0.5F;
            tube01.xRot = -0.6F;
        }
    }

    private void applyTailPose(float angleX) {
        TailBack.xRot = angleX * 0.1F - 0.1F;
        TailEnd.xRot = angleX * 0.25F - 0.1F;
    }

    private void applyLegPose(EntityShipBase ship, float limbSwing, float limbSwingAmount, float angleX) {
        if (ship.isSprinting() || limbSwingAmount > 0.9F) {
            LegRightFront.xRot = Mth.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount + 1.0F;
            LegLeftFront.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount + 1.0F;
            LegRightEnd.xRot = Mth.sin(limbSwing * 0.6662F) * limbSwingAmount + 0.5F;
            LegLeftEnd.xRot = Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount + 0.5F;
        } else {
            LegRightFront.xRot = angleX * 0.3F + 0.8F;
            LegLeftFront.xRot = -angleX * 0.3F + 0.8F;
            LegRightEnd.xRot = angleX * 0.3F + 0.5F;
            LegLeftEnd.xRot = -angleX * 0.3F + 0.5F;
        }
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

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, poseTranslateY, 0.0F);
        }

        GlowBack.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
