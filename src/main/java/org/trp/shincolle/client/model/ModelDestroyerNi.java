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

public class ModelDestroyerNi<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_ni"), "main");

    private final ModelPart Back;
    private final ModelPart NeckBack;
    private final ModelPart Body;
    private final ModelPart TailBack;
    private final ModelPart Head;
    private final ModelPart NeckBody;
    private final ModelPart EquipBase;
    private final ModelPart ArmLeft;
    private final ModelPart ArmRight;
    private final ModelPart k00;
    private final ModelPart ToothU;
    private final ModelPart Face00;
    private final ModelPart Face01;
    private final ModelPart Face02;
    private final ModelPart k01;
    private final ModelPart k02;
    private final ModelPart k03;
    private final ModelPart Equip01;
    private final ModelPart Equip02;
    private final ModelPart Equip03;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart TailEnd1;
    private final ModelPart GlowBack;
    private final ModelPart GlowNeckBack;
    private final ModelPart GlowHead;
    private float poseTranslateY;

    public ModelDestroyerNi(ModelPart root) {
        this.Back = root.getChild("Back");
        this.Body = this.Back.getChild("Body");
        this.NeckBack = this.Back.getChild("NeckBack");
        this.NeckBody = this.NeckBack.getChild("NeckBody");
        this.ArmRight = this.NeckBack.getChild("ArmRight");
        this.ArmRight01 = this.ArmRight.getChild("ArmRight01");
        this.EquipBase = this.NeckBack.getChild("EquipBase");
        this.Equip01 = this.EquipBase.getChild("Equip01");
        this.Equip02 = this.Equip01.getChild("Equip02");
        this.Equip03 = this.Equip02.getChild("Equip03");
        this.Head = this.NeckBack.getChild("Head");
        this.ToothU = this.Head.getChild("ToothU");
        this.ArmLeft = this.NeckBack.getChild("ArmLeft");
        this.ArmLeft01 = this.ArmLeft.getChild("ArmLeft01");
        this.TailBack = this.Back.getChild("TailBack");
        this.TailEnd1 = this.TailBack.getChild("TailEnd1");
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

        PartDefinition Back = partdefinition.addOrReplaceChild("Back", CubeListBuilder.create().texOffs(14, 76).addBox(-12F, -12F, -14F, 24F, 21F, 26F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -40F, 0F, 0.7854F, 0F, 0F));

        PartDefinition Body = Back.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 33).addBox(-10F, 0F, 0F, 20F, 12F, 24F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, -14F, 0.3643F, 0F, 0F));

        PartDefinition NeckBack = Back.addOrReplaceChild("NeckBack", CubeListBuilder.create().texOffs(10, 76).addBox(-14F, -10F, -20F, 28F, 25F, 26F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.5F, -14F, 0.0873F, 0F, 0F));

        PartDefinition NeckBody = NeckBack.addOrReplaceChild("NeckBody", CubeListBuilder.create().texOffs(1, 36).addBox(-11F, 0F, -9F, 22F, 10F, 21F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13F, -4F, -0.3187F, 0F, 0F));

        PartDefinition ArmRight = NeckBack.addOrReplaceChild("ArmRight", CubeListBuilder.create().texOffs(0, 31).addBox(-4F, 0F, -4F, 8F, 30F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13F, 15F, -9F, -0.5236F, 0.6981F, 1.0472F));

        PartDefinition ArmRight01 = ArmRight.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 32).addBox(-3.5F, 0F, -3.5F, 7F, 30F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 28F, 0F, 0F, 0F, -1.3963F));

        PartDefinition EquipBase = NeckBack.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(11, 89).addBox(-20F, 0F, 0F, 40F, 13F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 11F, -26F));

        PartDefinition Equip01 = EquipBase.addOrReplaceChild("Equip01", CubeListBuilder.create().texOffs(54, 64).addBox(0F, 0F, 0F, 0F, 24F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(18F, 13F, 9F, 1.0472F, 0.7854F, 0F));

        PartDefinition Equip02 = Equip01.addOrReplaceChild("Equip02", CubeListBuilder.create().texOffs(54, 64).addBox(0F, 0F, 0F, 0F, 28F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 24F, 0F, 0F, 0F, 1.309F));

        PartDefinition Equip03 = Equip02.addOrReplaceChild("Equip03", CubeListBuilder.create().texOffs(54, 64).addBox(0F, 0F, 0F, 0F, 32F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 28F, 0F, 0F, 0F, -1.0472F));

        PartDefinition Head = NeckBack.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 70).addBox(-16F, -14F, -28F, 32F, 22F, 32F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -19F, 0.0873F, 0F, 0F));

        PartDefinition ToothU = Head.addOrReplaceChild("ToothU", CubeListBuilder.create().texOffs(0, 0).addBox(-11F, 0F, 0F, 22F, 9F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -29F, 0.1396F, 0F, 0F));

        PartDefinition ArmLeft = NeckBack.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(0, 31).addBox(-4F, 0F, -4F, 8F, 30F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(13F, 15F, -9F, -0.5236F, -0.6981F, -1.0472F));

        PartDefinition ArmLeft01 = ArmLeft.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 32).addBox(-3.5F, 0F, -3.5F, 7F, 30F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 28F, 0F, 0F, 0F, 1.3963F));

        PartDefinition TailBack = Back.addOrReplaceChild("TailBack", CubeListBuilder.create().texOffs(22, 80).addBox(-10F, -4F, 0F, 20F, 17F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 9F, -0.1745F, 0F, 0F));

        PartDefinition TailEnd1 = TailBack.addOrReplaceChild("TailEnd1", CubeListBuilder.create().texOffs(28, 82).addBox(-8F, -3F, 0F, 16F, 13F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 19F, -0.1745F, 0F, 0F));

        PartDefinition GlowBack = partdefinition.addOrReplaceChild("GlowBack", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -40F, 0F, 0.7854F, 0F, 0F));

        PartDefinition GlowNeckBack = GlowBack.addOrReplaceChild("GlowNeckBack", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -2.5F, -14F, 0.0873F, 0F, 0F));

        PartDefinition GlowHead = GlowNeckBack.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 3F, -19F, 0.0873F, 0F, 0F));

        PartDefinition Face00 = GlowHead.addOrReplaceChild("Face00", CubeListBuilder.create().texOffs(68, 40).addBox(-10F, 0F, 0F, 20F, 0F, 20F, new CubeDeformation(0F)), PartPose.offset(0F, -14.3F, -27F));

        PartDefinition Face01 = GlowHead.addOrReplaceChild("Face01", CubeListBuilder.create().texOffs(68, 20).addBox(-10F, 0F, 0F, 20F, 0F, 20F, new CubeDeformation(0F)), PartPose.offset(0F, -14.2F, -27F));

        PartDefinition Face02 = GlowHead.addOrReplaceChild("Face02", CubeListBuilder.create().texOffs(68, 0).addBox(-10F, 0F, 0F, 20F, 0F, 20F, new CubeDeformation(0F)), PartPose.offset(0F, -14.1F, -27F));

        PartDefinition k00 = GlowHead.addOrReplaceChild("k00", CubeListBuilder.create().texOffs(100, 60).addBox(0F, 0F, 0F, 5F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(14F, -12F, 0F, -0.3491F, 0.2618F, 0F));

        PartDefinition k01 = k00.addOrReplaceChild("k01", CubeListBuilder.create().texOffs(106, 76).addBox(1F, -18.5F, 1F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -0.5236F, 0F, 0F));

        PartDefinition k02 = k00.addOrReplaceChild("k02", CubeListBuilder.create().texOffs(106, 76).addBox(0.8F, -25F, -0.7F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.3963F, 0F, 0F));

        PartDefinition k03 = k00.addOrReplaceChild("k03", CubeListBuilder.create().texOffs(106, 76).addBox(0.6F, -24.5F, -2.5F, 3F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -2.0944F, 0F, 0F));

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

        Back.xRot = 0.7854F;
        ArmLeft.xRot = -0.5F;
        ArmLeft.yRot = -0.7F;
        ArmLeft.zRot = -1.2217F;
        ArmRight.xRot = -0.5F;
        ArmRight.yRot = 0.7F;
        ArmRight.zRot = 1.2217F;
        ArmLeft01.xRot = 0.0F;
        ArmLeft01.zRot = 1.4F;
        ArmRight01.xRot = 0.0F;
        ArmRight01.zRot = -1.4F;
        Equip01.xRot = 1.0F;

        applyFaceFromEntity(ship);
        applyLook(netHeadYaw, headPitch);
        applyTailPose(angleX);
        if (ship.isInSittingPose()) {
            applySittingPose(ship, angleX);
        } else {
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

    private void applyLook(float headYaw, float headPitch) {
        if (headPitch != 0.0F) {
            NeckBack.xRot = headPitch * 0.005F;
            NeckBack.yRot = headYaw * 0.005F;
            Head.xRot = headPitch * 0.005F;
            Head.yRot = headYaw * 0.005F;
        }
    }

    private void applyStopPose(EntityShipBase ship) {
        poseTranslateY += 0.75F;
        setFace(2);
        NeckBack.xRot = 0.3F;
        NeckBack.yRot = 0.0F;
        Head.xRot = 0.3F;
        Head.yRot = 0.0F;
        Equip01.yRot = 0.5F;
        Equip02.zRot = 1.0F;
        Equip03.zRot = -0.8F;
        Back.xRot = -0.3236F;
        ArmLeft.xRot = -1.4F;
        ArmLeft.yRot = -0.7F;
        ArmLeft.zRot = -0.2618F;
        ArmRight.xRot = -1.4F;
        ArmRight.yRot = 0.9F;
        ArmRight.zRot = 0.2618F;
        ArmLeft01.xRot = 0.0F;
        ArmLeft01.zRot = 1.2F;
        ArmRight01.xRot = 0.0F;
        ArmRight01.zRot = -0.8F;
        TailBack.xRot = -0.1F;
        TailEnd1.xRot = 0.05F;
        Equip01.xRot = 2.0F;
    }

    private void applyLegPose(float limbSwing, float limbSwingAmount) {
        float angle1 = Mth.cos(limbSwing * 0.6662F) * 1.1F * limbSwingAmount;
        ArmLeft.xRot = angle1 - 0.5F;
        ArmRight.xRot = -angle1 - 0.5F;
    }

    private void applySittingPose(EntityShipBase ship, float angleX) {
        if (ship.getEmotionSecondary() == EntityShipBase.EMOTION_BORED) {
            poseTranslateY += angleX * 0.2F - 0.05F;
            ArmLeft.zRot = -angleX * 0.6F - 1.0472F;
            ArmLeft01.zRot = angleX * 0.5F + 1.2F;
            ArmRight.zRot = angleX * 0.6F + 1.0472F;
            ArmRight01.zRot = -angleX * 0.5F - 1.2F;
            TailBack.xRot = angleX * 0.1F + 0.2F;
            TailEnd1.xRot = angleX * 0.1F + 0.2F;
        } else {
            poseTranslateY += 0.75F;
            Back.xRot = -0.5236F;
            ArmLeft.xRot = -0.6981F;
            ArmLeft.yRot = -0.2618F;
            ArmLeft.zRot = -0.2618F;
            ArmRight.xRot = -0.6981F;
            ArmRight.yRot = 0.2618F;
            ArmRight.zRot = 0.2618F;
            ArmLeft01.xRot = -1.9199F;
            ArmLeft01.zRot = -0.6981F;
            ArmRight01.xRot = -1.9199F;
            ArmRight01.zRot = 0.6981F;
            TailBack.xRot = angleX * 0.1F + 0.2F;
            TailEnd1.xRot = angleX * 0.1F + 0.2F;
            Equip01.xRot = 2.0F;
        }
    }

    private void applyTailPose(float angleX) {
        TailBack.xRot = angleX * 0.2F;
        TailEnd1.xRot = angleX * 0.2F;
        Equip01.yRot = angleX * 0.2F + 0.5F;
        Equip02.zRot = angleX * 0.3F + 1.0F;
        Equip03.zRot = angleX * 0.4F - 0.8F;
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
