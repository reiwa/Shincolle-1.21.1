package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

public class ModelDestroyerI<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_i"), "main");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelDestroyerI");
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerI");

    private final ModelPart PBack;
    private final ModelPart PNeck;
    private final ModelPart PHead;
    private final ModelPart PJawBottom;
    private final ModelPart PBody;
    private final ModelPart PLegLeft;
    private final ModelPart PLegLeftEnd;
    private final ModelPart PLegRight;
    private final ModelPart PLegRightEnd;
    private final ModelPart PTail;
    private final ModelPart PTailLeft;
    private final ModelPart PTailLeftEnd;
    private final ModelPart PTailRight;
    private final ModelPart PTailRightEnd;
    private final ModelPart PTailEnd;
    private final ModelPart PKisaragi00;
    private final ModelPart PKisaragi01;
    private final ModelPart PKisaragi02;
    private final ModelPart PKisaragi03;
    private final ModelPart PEyeLightL0;
    private final ModelPart PEyeLightL1;
    private final ModelPart PEyeLightL2;
    private final ModelPart PEyeLightR0;
    private final ModelPart PEyeLightR1;
    private final ModelPart PEyeLightR2;
    private final ModelPart GlowPBack;
    private final ModelPart GlowPNeck;
    private final ModelPart GlowPHead;
    private float poseTranslateY;

    public ModelDestroyerI(ModelPart root) {
        this.PBack = root.getChild("PBack");
        this.PNeck = this.PBack.getChild("PNeck");
        this.PHead = this.PNeck.getChild("PHead");
        this.PJawBottom = this.PHead.getChild("PJawBottom");
        this.PBody = this.PBack.getChild("PBody");
        this.PLegLeft = this.PBody.getChild("PLegLeft");
        this.PLegLeftEnd = this.PLegLeft.getChild("PLegLeftEnd");
        this.PLegRight = this.PBody.getChild("PLegRight");
        this.PLegRightEnd = this.PLegRight.getChild("PLegRightEnd");
        this.PTail = this.PBack.getChild("PTail");
        this.PTailLeft = this.PTail.getChild("PTailLeft");
        this.PTailLeftEnd = this.PTailLeft.getChild("PTailLeftEnd");
        this.PTailRight = this.PTail.getChild("PTailRight");
        this.PTailRightEnd = this.PTailRight.getChild("PTailRightEnd");
        this.PTailEnd = this.PTail.getChild("PTailEnd");
        this.GlowPBack = root.getChild("GlowPBack");
        this.GlowPNeck = this.GlowPBack.getChild("GlowPNeck");
        this.GlowPHead = this.GlowPNeck.getChild("GlowPHead");
        this.PEyeLightL0 = this.GlowPHead.getChild("PEyeLightL0");
        this.PEyeLightL1 = this.GlowPHead.getChild("PEyeLightL1");
        this.PEyeLightL2 = this.GlowPHead.getChild("PEyeLightL2");
        this.PEyeLightR0 = this.GlowPHead.getChild("PEyeLightR0");
        this.PEyeLightR1 = this.GlowPHead.getChild("PEyeLightR1");
        this.PEyeLightR2 = this.GlowPHead.getChild("PEyeLightR2");
        this.PKisaragi00 = this.GlowPHead.getChild("PKisaragi00");
        this.PKisaragi01 = this.GlowPHead.getChild("PKisaragi01");
        this.PKisaragi02 = this.GlowPHead.getChild("PKisaragi02");
        this.PKisaragi03 = this.GlowPHead.getChild("PKisaragi03");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition PBack = partdefinition.addOrReplaceChild("PBack", CubeListBuilder.create().texOffs(128, 8)
                .addBox(-12F, -10F, -12F, 28, 20, 24), PartPose.offset(-8F, -16F, 0F));

        PartDefinition PNeck = PBack.addOrReplaceChild("PNeck", CubeListBuilder.create()
                .texOffs(128, 0).addBox(-3F, -11F, -13F, 30, 26, 26)
                .texOffs(128, 28).addBox(6F, 15F, -10F, 21, 4, 20)
                .texOffs(0, 70).addBox(-8F, 7F, -9F, 18, 14, 18), PartPose.offset(15F, 0F, 0F));

        PartDefinition PHead = PNeck.addOrReplaceChild("PHead", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3F, -12F, -16F, 32, 32, 32)
                .texOffs(96, 0).addBox(14.5F, 20F, -6F, 4, 6, 12)
                .texOffs(128, 54).addBox(0F, 20F, -10F, 18, 6, 4)
                .texOffs(128, 54).addBox(0F, 20F, 6F, 18, 6, 4)
                .texOffs(0, 102).addBox(-3F, 20F, -11F, 22, 2, 22), PartPose.offset(26F, 0F, 0F));

        PartDefinition PJawBottom = PHead.addOrReplaceChild("PJawBottom", CubeListBuilder.create()
                .texOffs(92, 64).addBox(-3F, 0F, -10F, 3, 18, 20)
                .texOffs(96, 19).addBox(-1F, 7.5F, 6F, 4, 10, 3)
                .texOffs(96, 19).addBox(-1F, 7.5F, -9F, 4, 10, 3)
                .texOffs(0, 0).addBox(-1F, 14.5F, -6F, 4, 3, 12), PartPose.offset(-6F, 18F, 0F));

        PartDefinition PBody = PBack.addOrReplaceChild("PBody", CubeListBuilder.create().texOffs(0, 64)
                .addBox(-10F, 10F, -11F, 24, 16, 22), PartPose.offset(0F, 0F, 0F));

        PartDefinition PLegLeft = PBody.addOrReplaceChild("PLegLeft", CubeListBuilder.create().texOffs(0, 80)
                .addBox(-3F, -4F, -1F, 8, 14, 8), PartPose.offset(-3F, 24F, 6F));

        PartDefinition PLegLeftEnd = PLegLeft.addOrReplaceChild("PLegLeftEnd", CubeListBuilder.create().texOffs(0, 90)
                .addBox(-12F, -3F, -4F, 12, 6, 6), PartPose.offset(1F, 8F, 4F));

        PartDefinition PLegRight = PBody.addOrReplaceChild("PLegRight", CubeListBuilder.create().texOffs(0, 80)
                .addBox(-3F, -4F, -5F, 8, 14, 8), PartPose.offset(-3F, 24F, -8F));

        PartDefinition PLegRightEnd = PLegRight.addOrReplaceChild("PLegRightEnd", CubeListBuilder.create().texOffs(0, 90)
                .addBox(-12F, -3F, -3F, 12, 6, 6), PartPose.offset(1F, 8F, -1F));

        PartDefinition PTail = PBack.addOrReplaceChild("PTail", CubeListBuilder.create()
                .texOffs(128, 16).addBox(-22F, -6F, -10F, 26, 16, 20)
                .texOffs(0, 68).addBox(-8F, 2F, -8F, 18, 18, 14), PartPose.offset(-12F, -2F, 0F));

        PartDefinition PTailLeft = PTail.addOrReplaceChild("PTailLeft", CubeListBuilder.create().texOffs(128, 28)
                .addBox(-8F, -4F, 0F, 12, 18, 6), PartPose.offset(-12F, 4F, 8F));

        PartDefinition PTailLeftEnd = PTailLeft.addOrReplaceChild("PTailLeftEnd", CubeListBuilder.create().texOffs(128, 36)
                .addBox(-24F, -4F, -2F, 24, 12, 4), PartPose.offset(0F, 9F, 5F));

        PartDefinition PTailRight = PTail.addOrReplaceChild("PTailRight", CubeListBuilder.create().texOffs(128, 28)
                .addBox(-8F, -4F, -6F, 12, 18, 6), PartPose.offset(-12F, 4F, -8F));

        PartDefinition PTailRightEnd = PTailRight.addOrReplaceChild("PTailRightEnd", CubeListBuilder.create().texOffs(128, 36)
                .addBox(-24F, -4F, -2F, 24, 12, 4), PartPose.offset(0F, 9F, -5F));

        PartDefinition PTailEnd = PTail.addOrReplaceChild("PTailEnd", CubeListBuilder.create().texOffs(128, 26)
                .addBox(-20F, -6F, -8F, 24, 10, 16), PartPose.offset(-22F, 2F, 0F));

        PartDefinition GlowPBack = partdefinition.addOrReplaceChild("GlowPBack", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(-8F, -16F, 0F));

        PartDefinition GlowPNeck = GlowPBack.addOrReplaceChild("GlowPNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(15F, 0F, 0F));

        PartDefinition GlowPHead = GlowPNeck.addOrReplaceChild("GlowPHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(26F, 0F, 0F));
        GlowPHead.addOrReplaceChild("PEyeLightL0", CubeListBuilder.create().texOffs(138, 64).mirror().addBox(-3F, 0F, 15.1F, 24, 20, 1), PartPose.ZERO);
        GlowPHead.addOrReplaceChild("PEyeLightL1", CubeListBuilder.create().texOffs(138, 85).mirror().addBox(-3F, 0F, 15.1F, 24, 20, 1), PartPose.ZERO);
        GlowPHead.addOrReplaceChild("PEyeLightL2", CubeListBuilder.create().texOffs(138, 106).mirror().addBox(-3F, 0F, 15.1F, 24, 20, 1), PartPose.ZERO);
        GlowPHead.addOrReplaceChild("PEyeLightR0", CubeListBuilder.create().texOffs(138, 64).addBox(-3F, 0F, -16.1F, 24, 20, 1), PartPose.ZERO);
        GlowPHead.addOrReplaceChild("PEyeLightR1", CubeListBuilder.create().texOffs(138, 85).addBox(-3F, 0F, -16.1F, 24, 20, 1), PartPose.ZERO);
        GlowPHead.addOrReplaceChild("PEyeLightR2", CubeListBuilder.create().texOffs(138, 106).addBox(-3F, 0F, -16.1F, 24, 20, 1), PartPose.ZERO);

        PartDefinition PKisaragi00 = GlowPHead.addOrReplaceChild("PKisaragi00", CubeListBuilder.create().texOffs(66, 102)
                .addBox(0F, 0F, 0F, 8, 8, 5), PartPose.offset(-7F, -9F, 14F));

        PartDefinition PKisaragi01 = GlowPHead.addOrReplaceChild("PKisaragi01", CubeListBuilder.create().texOffs(114, 102)
                .addBox(-2F, -16F, 1F, 8, 20, 3), PartPose.offset(-7F, -9F, 14F));

        PartDefinition PKisaragi02 = GlowPHead.addOrReplaceChild("PKisaragi02", CubeListBuilder.create().texOffs(92, 102)
                .addBox(-7F, -17F, 0.8F, 8, 18, 3), PartPose.offset(-7F, -9F, 14F));

        PartDefinition PKisaragi03 = GlowPHead.addOrReplaceChild("PKisaragi03", CubeListBuilder.create().texOffs(92, 102)
                .addBox(-9F, -18F, 0.6F, 8, 18, 3), PartPose.offset(-7F, -9F, 14F));

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        poseTranslateY = 0.0F;
        if (!(entity instanceof EntityShipBase ship)) {
            return;
        }

        float angleZ = Mth.cos(ageInTicks * 0.125F);
        if (ship.getShipDepth() > 0.0D) {
            poseTranslateY += angleZ * 0.05F + 0.025F;
        }

        if (ship.isInDeadPose()) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        resetPose();
        applyFaceFromEntity(ship);
        applyKisaragiVisibility(ship);
        applyLook(netHeadYaw, headPitch, angleZ);
        if (ship.isInSittingPose()) {
            applySittingPose(ship, angleZ);
        } else {
            applyLegPose(limbSwing, limbSwingAmount);
            applyTailPose(angleZ);
            PBack.zRot = -0.31F;
            poseTranslateY += 0.42F;
        }
        syncGlowParts();
    }

    private void resetPose() {
        PBack.xRot = 0.0F;
        PBack.yRot = 0.0F;
        PBack.zRot = 0.0F;
        PLegLeft.xRot = 0.0F;
        PLegLeft.zRot = 0.0F;
        PLegRight.xRot = 0.0F;
        PLegRight.zRot = 0.0F;
    }

    private void applyLook(float headYaw, float headPitch, float angleZ) {
        if (headPitch != 0.0F) {
            PNeck.yRot = headYaw * 0.006F;
            PNeck.zRot = headPitch * 0.006F;
            PHead.yRot = headYaw * 0.006F;
            PHead.zRot = headPitch * 0.006F;
            PTail.yRot = headYaw * -0.006F;
        } else {
            PNeck.yRot = 0.0F;
            PNeck.zRot = 0.2F;
            PHead.yRot = 0.0F;
            PHead.zRot = angleZ * 0.15F + 0.2F;
            PTail.yRot = 0.0F;
        }
    }

    private void applyDeadPose() {
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        setFace(2);
        PBack.xRot = 1.4835F;
        PBack.zRot = 0.0F;
        PNeck.yRot = 0.0F;
        PNeck.zRot = 0.2F;
        PHead.yRot = 0.0F;
        PHead.zRot = 0.2F;
        PTail.yRot = 0.0F;
        PLegLeft.xRot = -1.0472F;
        PLegLeft.zRot = 0.0F;
        PLegLeftEnd.zRot = -1.4F;
        PLegRight.xRot = 0.087F;
        PLegRight.zRot = -0.7854F;
        PLegRightEnd.zRot = -1.4F;
        PTail.zRot = 0.2F;
        PTailEnd.zRot = 0.3F;
        PJawBottom.zRot = -0.3F;
    }

    private void applyKisaragiVisibility(EntityShipBase ship) {
        boolean show = true;
        PKisaragi00.visible = show;
        PKisaragi01.visible = show;
        PKisaragi02.visible = show;
        PKisaragi03.visible = show;
    }

    private void applyFaceFromEntity(EntityShipBase ship) {
        int faceId = ship.getFaceId();
        int faceIndex = faceId >= 0 ? faceId % 3 : 0;
        setFace(faceIndex);
    }

    private void setFace(int faceIndex) {
        boolean show0 = faceIndex == 0;
        boolean show1 = faceIndex == 1;
        boolean show2 = faceIndex == 2;
        PEyeLightL0.visible = show0;
        PEyeLightR0.visible = show0;
        PEyeLightL1.visible = show1;
        PEyeLightR1.visible = show1;
        PEyeLightL2.visible = show2;
        PEyeLightR2.visible = show2;
    }

    private void applySittingPose(EntityShipBase ship, float angleZ) {
        this.poseTranslateY = SITTING_TRANSLATE_Y;
        if (ship.getEmotionSecondary() == EntityShipBase.EMOTION_BORED) {
            poseTranslateY += 0.5F;
            PBack.zRot = 0.6F;
            PNeck.zRot = -0.25F;
            PHead.zRot = -0.3F;
            PLegRight.zRot = -1.0F;
            PLegLeft.zRot = -1.0F;
            PLegRightEnd.zRot = -1.1F;
            PLegLeftEnd.zRot = -1.1F;
            PTail.zRot = -0.6F;
            PTailEnd.zRot = -0.6F;
            PJawBottom.zRot = -0.7F;
        } else {
            poseTranslateY += 0.68F;
            PBack.zRot = -0.8F;
            PNeck.zRot = -0.3F;
            PLegRight.zRot = -0.8F;
            PLegLeft.zRot = -0.8F;
            PLegRightEnd.zRot = -1.4F;
            PLegLeftEnd.zRot = -1.4F;
            PTail.zRot = 0.4F;
            PTailEnd.zRot = angleZ * 0.2F + 0.4F;
            PJawBottom.zRot = angleZ * 0.05F - 0.3F;
            PHead.zRot = angleZ * 0.02F + 0.4F;
        }
    }

    private void applyTailPose(float angleZ) {
        PTail.zRot = angleZ * 0.2F;
        PTailEnd.zRot = angleZ * 0.3F;
        PJawBottom.zRot = angleZ * 0.2F - 0.3F;
    }

    private void applyLegPose(float limbSwing, float limbSwingAmount) {
        PLegRight.zRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount - 0.6F;
        PLegLeft.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount - 0.6F;
        PLegRightEnd.zRot = Mth.sin(limbSwing * 0.6662F) * limbSwingAmount - 0.4F;
        PLegLeftEnd.zRot = Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount - 0.4F;
    }

    private void syncGlowParts() {
        GlowPBack.copyFrom(PBack);
        GlowPNeck.copyFrom(PNeck);
        GlowPHead.copyFrom(PHead);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, poseTranslateY, 0.0F);
        }

        PBack.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        GlowPBack.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
