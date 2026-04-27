package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelHeavyCruiserRi<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "heavy_cruiser_ri"), "main");

    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Butt;
    private final ModelPart ArmLeft;
    private final ModelPart ArmRight;
    private final ModelPart Neck;
    private final ModelPart EquipBase;
    private final ModelPart LegRight;
    private final ModelPart LegLeft;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart EquipLeftBase;
    private final ModelPart EquipLeftTube1;
    private final ModelPart EquipLeftBase2;
    private final ModelPart EquipLeftBase3;
    private final ModelPart EquipLeftBase4;
    private final ModelPart EquipLeftTube2;
    private final ModelPart EquipLeftTube3;
    private final ModelPart EquipLeftTooth;
    private final ModelPart EquipRightBase;
    private final ModelPart EquipRightTube1;
    private final ModelPart EquipRightBase1;
    private final ModelPart EquipRightBase2;
    private final ModelPart EquipRightBase3;
    private final ModelPart EquipRightBase4;
    private final ModelPart EquipRightTube2;
    private final ModelPart EquipRightTube3;
    private final ModelPart EquipRightTooth1;
    private final ModelPart EquipRightTooth2;
    private final ModelPart Head;
    private final ModelPart Cloak;
    private final ModelPart Hair;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowArmLeft;
    private final ModelPart GlowEquipLeftBase;
    private final ModelPart GlowEquipLeftBase3;
    private final ModelPart GlowArmRight;
    private final ModelPart GlowEquipRightBase;
    private final ModelPart GlowEquipRightBase2;
    private final ModelPart GlowEquipRightBase3;
    private final ModelPart ShoesRight;
    private final ModelPart ShoesLeft;
    private final ModelPart HeadTail0;
    private final ModelPart HeadTail1;
    private final ModelPart HeadTail2;

    public ModelHeavyCruiserRi(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.ArmRight = this.BodyMain.getChild("ArmRight");
        this.EquipRightBase = this.ArmRight.getChild("EquipRightBase");
        this.EquipRightBase1 = this.EquipRightBase.getChild("EquipRightBase1");
        this.EquipRightBase4 = this.EquipRightBase.getChild("EquipRightBase4");
        this.EquipRightBase3 = this.EquipRightBase.getChild("EquipRightBase3");
        this.EquipRightBase2 = this.EquipRightBase.getChild("EquipRightBase2");
        this.EquipRightTube1 = this.EquipRightBase.getChild("EquipRightTube1");
        this.EquipRightTube2 = this.EquipRightTube1.getChild("EquipRightTube2");
        this.EquipRightTube3 = this.EquipRightTube2.getChild("EquipRightTube3");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HeadTail0 = this.Head.getChild("HeadTail0");
        this.HeadTail1 = this.HeadTail0.getChild("HeadTail1");
        this.HeadTail2 = this.HeadTail1.getChild("HeadTail2");
        this.Cloak = this.Neck.getChild("Cloak");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight = this.Butt.getChild("LegRight");
        this.ShoesRight = this.LegRight.getChild("ShoesRight");
        this.LegLeft = this.Butt.getChild("LegLeft");
        this.ShoesLeft = this.LegLeft.getChild("ShoesLeft");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.ArmLeft = this.BodyMain.getChild("ArmLeft");
        this.EquipLeftBase = this.ArmLeft.getChild("EquipLeftBase");
        this.EquipLeftBase2 = this.EquipLeftBase.getChild("EquipLeftBase2");
        this.EquipLeftBase4 = this.EquipLeftBase.getChild("EquipLeftBase4");
        this.EquipLeftBase3 = this.EquipLeftBase.getChild("EquipLeftBase3");
        this.EquipLeftTube1 = this.EquipLeftBase.getChild("EquipLeftTube1");
        this.EquipLeftTube2 = this.EquipLeftTube1.getChild("EquipLeftTube2");
        this.EquipLeftTube3 = this.EquipLeftTube2.getChild("EquipLeftTube3");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.GlowArmLeft = this.GlowBodyMain.getChild("GlowArmLeft");
        this.GlowEquipLeftBase = this.GlowArmLeft.getChild("GlowEquipLeftBase");
        this.GlowEquipLeftBase3 = this.GlowEquipLeftBase.getChild("GlowEquipLeftBase3");
        this.EquipLeftTooth = this.GlowEquipLeftBase3.getChild("EquipLeftTooth");
        this.GlowArmRight = this.GlowBodyMain.getChild("GlowArmRight");
        this.GlowEquipRightBase = this.GlowArmRight.getChild("GlowEquipRightBase");
        this.GlowEquipRightBase2 = this.GlowEquipRightBase.getChild("GlowEquipRightBase2");
        this.EquipRightTooth1 = this.GlowEquipRightBase2.getChild("EquipRightTooth1");
        this.GlowEquipRightBase3 = this.GlowEquipRightBase.getChild("GlowEquipRightBase3");
        this.EquipRightTooth2 = this.GlowEquipRightBase3.getChild("EquipRightTooth2");
        this.initFaceParts(this.GlowHead);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -10F, -4F, 13F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -14F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(1, 26).mirror().addBox(-3.5F, 0F, -1F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.3F, -8.5F, -2.5F, -0.7854F, 0.087F, 0.087F));

        PartDefinition ArmRight = BodyMain.addOrReplaceChild("ArmRight", CubeListBuilder.create().texOffs(0, 53).addBox(-5F, 0F, -2.5F, 5F, 25F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -9.5F, 0F, 0.2F, 0F, 0.2618F));

        PartDefinition EquipRightBase = ArmRight.addOrReplaceChild("EquipRightBase", CubeListBuilder.create().texOffs(78, 6).addBox(-7.5F, 0F, -4.5F, 13F, 14F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 16F, 0F, 0F, 0F, -0.0873F));

        PartDefinition EquipRightBase1 = EquipRightBase.addOrReplaceChild("EquipRightBase1", CubeListBuilder.create().texOffs(85, 4).addBox(0F, -20F, 0F, 4F, 21F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, 0F, -5.5F, 0F, 0F, -0.0873F));

        PartDefinition EquipRightBase4 = EquipRightBase.addOrReplaceChild("EquipRightBase4", CubeListBuilder.create().texOffs(81, 0).addBox(0F, 0F, 0F, 4F, 25F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, 0F, -7.5F, 0F, 0F, -0.0873F));

        PartDefinition EquipRightBase3 = EquipRightBase.addOrReplaceChild("EquipRightBase3", CubeListBuilder.create().texOffs(90, 8).addBox(0F, 0F, -3.5F, 3F, 8F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, 14F, 0F, 0F, 0F, -0.2618F));

        PartDefinition EquipRightBase2 = EquipRightBase.addOrReplaceChild("EquipRightBase2", CubeListBuilder.create().texOffs(85, 5).addBox(-5F, 0F, -5F, 5F, 10F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.2F, 13F, 0F, 0F, 0F, 0.0873F));

        PartDefinition EquipRightTube1 = EquipRightBase.addOrReplaceChild("EquipRightTube1", CubeListBuilder.create().texOffs(82, 56).addBox(-1.5F, -16F, -1.5F, 3F, 16F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, 8F, 3F, -1.0472F, 0F, 0F));

        PartDefinition EquipRightTube2 = EquipRightTube1.addOrReplaceChild("EquipRightTube2", CubeListBuilder.create().texOffs(82, 56).addBox(-1.5F, -13F, -1.5F, 3F, 14F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, 0.7854F, -0.1745F, 0F));

        PartDefinition EquipRightTube3 = EquipRightTube2.addOrReplaceChild("EquipRightTube3", CubeListBuilder.create().texOffs(82, 56).addBox(-3.5F, -23.5F, -1.4F, 3F, 25F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -12F, 0F, 1.3963F, -0.3491F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(78, 5).addBox(-5.5F, 0F, -5.6F, 11F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -13F, 1F, 0.1F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(43, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0.5F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(34, 68).addBox(-8F, -8F, -7.2F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, 0F));

        PartDefinition HeadTail0 = Head.addOrReplaceChild("HeadTail0", CubeListBuilder.create().texOffs(20, 54).addBox(-4.5F, 0F, -3F, 9F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 8F, 0.2618F, 0F, 0F));

        PartDefinition HeadTail1 = HeadTail0.addOrReplaceChild("HeadTail1", CubeListBuilder.create().texOffs(24, 54).addBox(-3.5F, 0F, -3F, 7F, 16F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, 0.09F, 0F, 0F));

        PartDefinition HeadTail2 = HeadTail1.addOrReplaceChild("HeadTail2", CubeListBuilder.create().texOffs(21, 55).addBox(-4F, 0F, -2.5F, 8F, 18F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 14F, 0F, -0.1745F, 0F, 0F));

        PartDefinition Cloak = Neck.addOrReplaceChild("Cloak", CubeListBuilder.create().texOffs(0, 112).addBox(-8F, 0F, 0F, 16F, 16F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 4F, 1.309F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 36).addBox(-8F, 0F, -4.1F, 16F, 8F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 0F, 0.2618F, 0F, 0F));

        PartDefinition LegRight = Butt.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(1, 85).addBox(-3F, 0F, -3F, 6F, 17F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.7F, 7.5F, -1F, -0.2F, 0F, -0.087F));

        PartDefinition ShoesRight = LegRight.addOrReplaceChild("ShoesRight", CubeListBuilder.create().texOffs(52, 52).addBox(-3.5F, 17F, -3.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition LegLeft = Butt.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(1, 85).addBox(-3F, 0F, -3F, 6F, 17F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.7F, 7.5F, -1F, -0.087F, 0F, 0.087F));

        PartDefinition ShoesLeft = LegLeft.addOrReplaceChild("ShoesLeft", CubeListBuilder.create().texOffs(52, 52).mirror().addBox(-3.5F, 17F, -3.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(82, 12).addBox(-5F, 0F, 0F, 10F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -11F, 4F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(1, 26).addBox(-3.5F, 0F, -1F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.3F, -8.5F, -2.5F, -0.7854F, -0.087F, -0.087F));

        PartDefinition ArmLeft = BodyMain.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(0F, 0F, -2.5F, 5F, 25F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -9.5F, 0F, -0.087F, 0F, -0.2618F));

        PartDefinition EquipLeftBase = ArmLeft.addOrReplaceChild("EquipLeftBase", CubeListBuilder.create().texOffs(76, 1).addBox(-6F, 0F, -7F, 10F, 14F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 16F, 0F, 0F, 0F, 0.0873F));

        PartDefinition EquipLeftBase2 = EquipLeftBase.addOrReplaceChild("EquipLeftBase2", CubeListBuilder.create().texOffs(82, 5).addBox(-3F, -7F, -5F, 8F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 0F, 0F, 0F, 0F, 0.0255F));

        PartDefinition EquipLeftBase4 = EquipLeftBase.addOrReplaceChild("EquipLeftBase4", CubeListBuilder.create().texOffs(83, 9).addBox(-6.5F, 0F, 0F, 11F, 16F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6.5F, 2.5F, 0.0873F, 0F, 0F));

        PartDefinition EquipLeftBase3 = EquipLeftBase.addOrReplaceChild("EquipLeftBase3", CubeListBuilder.create().texOffs(77, 5).addBox(-7.5F, 5F, -10F, 13F, 19F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -0.0873F, 0F, 0F));

        PartDefinition EquipLeftTube1 = EquipLeftBase.addOrReplaceChild("EquipLeftTube1", CubeListBuilder.create().texOffs(82, 56).addBox(-1.5F, -16F, -1.5F, 3F, 16F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 8F, 3F, -0.6981F, 0.5236F, 0F));

        PartDefinition EquipLeftTube2 = EquipLeftTube1.addOrReplaceChild("EquipLeftTube2", CubeListBuilder.create().texOffs(82, 56).addBox(-1.5F, -12F, -1.5F, 3F, 12F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, 0.8727F, 0F, 0F));

        PartDefinition EquipLeftTube3 = EquipLeftTube2.addOrReplaceChild("EquipLeftTube3", CubeListBuilder.create().texOffs(82, 56).addBox(-1.5F, -20F, -1.5F, 3F, 20F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11F, 0F, 1.4486F, 0.7854F, 0.2618F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -14F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -13F, 1F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0.5F, 0F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowArmLeft = GlowBodyMain.addOrReplaceChild("GlowArmLeft", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(7F, -10F, 0F, 0F, 0F, -0.2618F));

        PartDefinition GlowEquipLeftBase = GlowArmLeft.addOrReplaceChild("GlowEquipLeftBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(7F, 16F, 0F, 0F, 0F, 0.0873F));

        PartDefinition GlowEquipLeftBase3 = GlowEquipLeftBase.addOrReplaceChild("GlowEquipLeftBase3", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 0F, 0F, -0.0873F, 0F, 0F));

        PartDefinition EquipLeftTooth = GlowEquipLeftBase3.addOrReplaceChild("EquipLeftTooth", CubeListBuilder.create().texOffs(44, 0).addBox(-5.5F, 0F, 0F, 9F, 7F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 14F, -1.2F, 0.0873F, 0F, 0F));

        PartDefinition GlowArmRight = GlowBodyMain.addOrReplaceChild("GlowArmRight", CubeListBuilder.create().texOffs(0, 53), PartPose.offsetAndRotation(-7F, -10F, 0F, 0F, 0F, 0.2618F));

        PartDefinition GlowEquipRightBase = GlowArmRight.addOrReplaceChild("GlowEquipRightBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-6F, 16F, 0F, 0F, 0F, -0.0873F));

        PartDefinition GlowEquipRightBase2 = GlowEquipRightBase.addOrReplaceChild("GlowEquipRightBase2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-4.2F, 13F, 0F, 0F, 0F, 0.0873F));

        PartDefinition EquipRightTooth1 = GlowEquipRightBase2.addOrReplaceChild("EquipRightTooth1", CubeListBuilder.create().texOffs(44, 13).addBox(0F, 0F, -4F, 2F, 5F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 0F));

        PartDefinition GlowEquipRightBase3 = GlowEquipRightBase.addOrReplaceChild("GlowEquipRightBase3", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(1F, 14F, 0F, 0F, 0F, -0.2618F));

        PartDefinition EquipRightTooth2 = GlowEquipRightBase3.addOrReplaceChild("EquipRightTooth2", CubeListBuilder.create().texOffs(59, 24).addBox(0F, 0F, -2.5F, 2F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(-1.6F, 2.3F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.poseTranslateY = 0.0F;
        if (!(entity instanceof EntityShipBase ship)) {
            return;
        }

        applyEquipVisibility(entity);

        if (ship.isInDeadPose()) {
            this.applyDeadPose();
            this.GlowBodyMain.copyFrom(this.BodyMain);
            this.GlowNeck.copyFrom(this.Neck);
            this.GlowHead.copyFrom(this.Head);
            this.GlowArmLeft.copyFrom(this.ArmLeft);
            this.GlowArmRight.copyFrom(this.ArmRight);
            return;
        }
        float angleZ = Mth.cos(ageInTicks * 0.08F);
        if (ship.getShipDepth() > 0.0F) {
            this.poseTranslateY += angleZ * 0.05F + 0.025F;
        }

        float addk1 = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount - 0.087F;
        float addk2 = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount - 0.2F;

        this.Head.yRot = netHeadYaw * 0.01F;
        this.Head.xRot = headPitch * 0.008F;
        this.Cloak.xRot = angleZ * 0.2F + 1.0F;
        this.BoobL.xRot = -angleZ * 0.06F - 0.73F;
        this.BoobR.xRot = -angleZ * 0.06F - 0.73F;
        this.BodyMain.xRot = -0.15F;
        this.ArmLeft.zRot = angleZ * -0.06F - 0.25F;
        this.ArmLeft.xRot = 0.2F;
        this.ArmRight.xRot = 0.2F;
        this.ArmRight.yRot = 0.0F;
        this.ArmRight.zRot = angleZ * 0.06F + 0.25F;
        this.LegLeft.zRot = 0.087F;
        this.LegRight.zRot = -0.087F;
        this.LegLeft.yRot = 0.0F;
        this.LegRight.yRot = 0.0F;
        this.HeadTail0.xRot = angleZ * 0.05F + 0.26F;
        this.HeadTail1.xRot = angleZ * 0.1F + 0.09F;

        if (ship.getIsSprinting() || limbSwingAmount > 0.9F) {
            this.ArmLeft.xRot = 1.0F;
            this.ArmRight.xRot = 1.0F;
            this.BodyMain.xRot = 0.5F;
            this.HeadTail0.xRot = angleZ * 0.05F + 0.8F;
            addk1 -= 0.4F;
            addk2 -= 0.4F;
        }

        this.Head.zRot = ship.getHeadTiltAngle(ageInTicks);
        if (entity.isCrouching()) {
            this.poseTranslateY += 0.05F;
            this.ArmLeft.xRot = 0.7F;
            this.ArmRight.xRot = 0.7F;
            this.BodyMain.xRot = 0.5F;
            addk1 -= 0.6F;
            addk2 -= 0.6F;
        }

        boolean isSitting = ship.getIsSitting() || entity.isPassenger();
        if (isSitting) {
            if (ship.getStateEmotion(1) == 4) {
                this.poseTranslateY += 0.44F * 3;
                this.ArmLeft.xRot = 0.6F;
                this.ArmRight.xRot = 0.6F;
                this.ArmLeft.zRot = -0.6F;
                this.ArmRight.zRot = 0.6F;
                this.BodyMain.xRot = -0.6F;
                this.Head.xRot -= 0.2F;
                addk1 = -1.58F;
                addk2 = -1.58F;
                this.LegLeft.zRot = 1.2F;
                this.LegRight.zRot = -1.2F;
                this.LegLeft.yRot = -0.75F;
                this.LegRight.yRot = 0.75F;
                this.HeadTail0.xRot += 0.7F;
            } else {
                this.poseTranslateY += 0.45F * 3;
                this.ArmLeft.xRot = -0.6F;
                this.ArmLeft.zRot = 0.3F;
                this.ArmRight.xRot = -0.6F;
                this.ArmRight.zRot = -0.3F;
                this.BodyMain.xRot = 0.3F;
                this.Head.xRot -= 0.35F;
                addk1 = -2.0F;
                addk2 = -2.0F;
                this.LegLeft.yRot = 0.15F;
                this.LegRight.yRot = -0.15F;
                this.LegLeft.zRot = 1.2F;
                this.LegRight.zRot = -1.2F;
            }
        }

        this.LegLeft.xRot = addk1;
        this.LegRight.xRot = addk2;

        if (ship.getAttackTick() > 15) {
            this.ArmLeft.xRot = headPitch / 57.29578F - 1.5F;
            this.ArmRight.zRot = 0.7F;
            this.ArmRight.xRot = 0.4F;
        }

        float swing = ship.getSwingTime(ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight.xRot = -0.5F;
            this.ArmRight.yRot = 0.0F;
            this.ArmRight.zRot = 0.2F;
            this.ArmRight.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            this.ArmRight.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        applyFaceAndMouth(ship);

        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);
        this.GlowArmLeft.copyFrom(this.ArmLeft);
        this.GlowArmRight.copyFrom(this.ArmRight);
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) {
            return;
        }

        boolean showLeft = entity.getEquipFlag(org.trp.shincolle.entity.EntityHeavyCruiserRi.EQUIP_LEFT);
        boolean showRight = entity.getEquipFlag(org.trp.shincolle.entity.EntityHeavyCruiserRi.EQUIP_RIGHT);
        boolean showCloak = entity.getEquipFlag(org.trp.shincolle.entity.EntityHeavyCruiserRi.EQUIP_CLOAK);
        boolean showHair = entity.getEquipFlag(org.trp.shincolle.entity.EntityHeavyCruiserRi.EQUIP_HAIR);

        this.EquipBase.visible = showLeft || showRight;
        this.EquipLeftBase.visible = showLeft;
        this.GlowEquipLeftBase.visible = showLeft;
        this.EquipRightBase.visible = showRight;
        this.GlowEquipRightBase.visible = showRight;
        this.Cloak.visible = showCloak;
        this.HeadTail0.visible = showHair;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    private void applyDeadPose() {
        this.poseTranslateY += 0.46F * 3;
        this.Head.xRot = 0.2F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.Cloak.xRot = -0.2F;
        this.BoobL.xRot = -0.73F;
        this.BoobR.xRot = -0.73F;
        this.BodyMain.xRot = 0.3F;
        this.HeadTail0.xRot = -0.05F;
        this.HeadTail1.xRot = -0.05F;
        this.ArmLeft.xRot = -0.6F;
        this.ArmRight.xRot = -0.6F;
        this.ArmLeft.zRot = 0.5F;
        this.ArmRight.zRot = -0.5F;
        this.LegLeft.xRot = -2.0F;
        this.LegLeft.yRot = 0.15F;
        this.LegLeft.zRot = 1.2F;
        this.LegRight.xRot = -2.0F;
        this.LegRight.yRot = -0.15F;
        this.LegRight.zRot = -1.2F;
    }
}
