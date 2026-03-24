package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

public class ModelHarbourHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "harbour_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = 0.74F * 3;
    private static final float SNEAK_TRANSLATE_Y = 0.1F;
    private static final float SITTING_TRANSLATE_Y = 0.4F * 3;
    private static final float RIDING_TRANSLATE_Y = 0.5F;

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart ArmLeft01;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart HeadH;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart HeadH2;
    private final ModelPart HeadH3;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart ArmLeft04;
    private final ModelPart ArmLeft05;
    private final ModelPart ArmLeft06;
    private final ModelPart ArmLeft07;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt;
    private final ModelPart LegRight02;
    private final ModelPart ShoesR;
    private final ModelPart LegLeft02;
    private final ModelPart ShoesL;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart ArmRight04;
    private final ModelPart ArmRight05;
    private final ModelPart ArmRight06;
    private final ModelPart ArmRight07;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float skirtDefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultZ;

    public ModelHarbourHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.ArmLeft04 = this.ArmLeft03.getChild("ArmLeft04");
        this.ArmLeft05 = this.ArmLeft04.getChild("ArmLeft05");
        this.ArmLeft06 = this.ArmLeft05.getChild("ArmLeft06");
        this.ArmLeft07 = this.ArmLeft06.getChild("ArmLeft07");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.ArmRight04 = this.ArmRight03.getChild("ArmRight04");
        this.ArmRight05 = this.ArmRight04.getChild("ArmRight05");
        this.ArmRight06 = this.ArmRight05.getChild("ArmRight06");
        this.ArmRight07 = this.ArmRight06.getChild("ArmRight07");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoesR = this.LegRight02.getChild("ShoesR");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoesL = this.LegLeft02.getChild("ShoesL");
        this.Skirt = this.Butt.getChild("Skirt");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.HeadH = this.GlowHead.getChild("HeadH");
        this.HeadH2 = this.HeadH.getChild("HeadH2");
        this.HeadH3 = this.HeadH2.getChild("HeadH3");
        this.initFaceParts(this.GlowHead);
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.skirtDefaultY = this.Skirt.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultZ = this.LegRight02.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 85).mirror().addBox(-2F, -1F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, -0.2618F, 0.6981F, 0F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(72, 38).mirror().addBox(-3F, 0F, -3.5F, 6F, 10F, 7F, new CubeDeformation(0F)), PartPose.offset(0.5F, 4F, 0F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(46, 46).addBox(-4F, 0F, -4.5F, 8F, 5F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0F, 0.3491F, 0F));

        PartDefinition ArmLeft04 = ArmLeft03.addOrReplaceChild("ArmLeft04", CubeListBuilder.create().texOffs(50, 60).addBox(-5F, 0F, -5.5F, 10F, 6F, 11F, new CubeDeformation(0F)), PartPose.offset(0F, 5F, 0F));

        PartDefinition ArmLeft05 = ArmLeft04.addOrReplaceChild("ArmLeft05", CubeListBuilder.create().texOffs(46, 0).addBox(-5.5F, -0.2F, -6.5F, 11F, 4F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 6F, 0F));

        PartDefinition ArmLeft06 = ArmLeft05.addOrReplaceChild("ArmLeft06", CubeListBuilder.create().texOffs(68, 17).addBox(0F, 0F, -4.2F, 5F, 9F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 1F, 0.5F, 0.0873F, 0.1396F, 0.2618F));

        PartDefinition ArmLeft07 = ArmLeft06.addOrReplaceChild("ArmLeft07", CubeListBuilder.create().texOffs(43, 18).addBox(0F, 0F, -3F, 4F, 6F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, 0F, -2F, -0.2618F, 0F, 0.1745F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 85).mirror().addBox(-3F, -1F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, -0.2618F, -0.6981F, 0F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(72, 38).mirror().addBox(-3F, 0F, -3.5F, 6F, 10F, 7F, new CubeDeformation(0F)), PartPose.offset(-0.5F, 4F, 0F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(46, 46).addBox(-4F, 0F, -4.5F, 8F, 5F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0F, -0.3491F, 0F));

        PartDefinition ArmRight04 = ArmRight03.addOrReplaceChild("ArmRight04", CubeListBuilder.create().texOffs(50, 60).mirror().addBox(-5F, 0F, -5.5F, 10F, 6F, 11F, new CubeDeformation(0F)), PartPose.offset(0F, 5F, 0F));

        PartDefinition ArmRight05 = ArmRight04.addOrReplaceChild("ArmRight05", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-5.5F, -0.2F, -6.5F, 11F, 4F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 6F, 0F));

        PartDefinition ArmRight06 = ArmRight05.addOrReplaceChild("ArmRight06", CubeListBuilder.create().texOffs(68, 17).mirror().addBox(-4F, 0F, -4.2F, 5F, 9F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, 1F, 0.5F, 0.0873F, -0.1396F, -0.2618F));

        PartDefinition ArmRight07 = ArmRight06.addOrReplaceChild("ArmRight07", CubeListBuilder.create().texOffs(43, 18).addBox(-3F, 0F, -3F, 4F, 6F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, 0F, -2F, -0.2618F, 0F, -0.1745F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(46, 33).addBox(-4F, 0F, 0F, 8F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.9F, -8.1F, -4F, -0.8727F, 0.0873F, -0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, 4F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.3142F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.2F, 9.5F, -2.6F, -0.2094F, 0F, -0.0524F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, 0F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesR = LegRight02.addOrReplaceChild("ShoesR", CubeListBuilder.create().texOffs(100, 0).addBox(-3.5F, 0F, -3.5F, 7F, 14F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 1F, 3F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.2F, 9.5F, -2.6F, -0.2094F, 0F, 0.0524F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, 0F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesL = LegLeft02.addOrReplaceChild("ShoesL", CubeListBuilder.create().texOffs(100, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 14F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 3F, 0F, 0F, 0.0365F));

        PartDefinition Skirt = Butt.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(0, 19).addBox(-8.5F, 0F, 0F, 17F, 6F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6.9F, -6F, -0.1396F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(46, 33).mirror().addBox(-4F, 0F, 0F, 8F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.9F, -8.1F, -4F, -0.8727F, -0.0873F, 0.0873F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(88, 26).addBox(-5.5F, -2F, -5F, 11F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, -0.2F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(45, 77).addBox(-8F, -8F, -7.2F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(24, 84).addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 3F, -5.5F, -0.14F, -0.1745F, -0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(24, 84).addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 10F, 0.1F, 0.1745F, 0F, 0.0873F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 3F, -5.5F, -0.14F, 0.1745F, 0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 10F, 0.1F, 0.1745F, 0F, -0.0524F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -4F, -11.5F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, -5.5F, -0.1745F, 0.6981F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(0, 57).addBox(-7.5F, 0F, 0F, 15F, 12F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 57).addBox(-7.5F, 0F, 0F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2094F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 58).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, -0.0873F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(0, 35).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -0.1F, -0.1396F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, -0.2F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, 0F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition HeadH = GlowHead.addOrReplaceChild("HeadH", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-2F, -2F, -3F, 4F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10F, -6.5F, -0.3142F, 0F, 0F));

        PartDefinition HeadH2 = HeadH.addOrReplaceChild("HeadH2", CubeListBuilder.create().texOffs(84, 64).addBox(-1.5F, -1.5F, -3F, 3F, 3F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.2F, -3.7F, -0.1396F, 0F, 0F));

        PartDefinition HeadH3 = HeadH2.addOrReplaceChild("HeadH3", CubeListBuilder.create().texOffs(45, 105).addBox(-1F, -1.2F, -3F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -3.8F, -0.1396F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        applyFaceAndMouth(entity);

        if (entity instanceof org.trp.shincolle.entity.base.EntityShipBase ship && ship.isInDeadPose()) {
            this.applyDeadPose();
            this.syncGlowParts();
            return;
        }

        this.applyBasePose(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.applySpecialPoseAdjustments(entity, ctx, limbSwing, limbSwingAmount, ageInTicks);
        this.syncGlowParts();
    }

    private void resetOffsets() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;

        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.Skirt.y = this.skirtDefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.z = this.legRight02DefaultZ;
    }

    private void applyEquipVisibility(T entity) {

    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = -0.35F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.BoobL.xRot = -0.76F;
        this.BoobR.xRot = -0.76F;
        this.Ahoke.yRot = 0.6F;
        this.BodyMain.xRot = 1.4835F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 1.0472F;
        this.Butt.z = this.buttDefaultZ + (-0.05F * OFFSET_SCALE);
        this.Skirt.y = this.skirtDefaultY + (-0.1F * OFFSET_SCALE);
        this.Hair01.xRot = 0.35F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = 0.2F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -0.3F;
        this.Hair03.zRot = 0.0F;
        this.HairL01.xRot = -0.14F;
        this.HairL02.xRot = 0.17F;
        this.HairR01.xRot = -0.14F;
        this.HairR02.xRot = 0.17F;

        this.ArmLeft01.xRot = -2.967F;
        this.ArmLeft01.yRot = -0.6981F;
        this.ArmLeft01.zRot = 0.08F;
        this.ArmLeft03.xRot = 0.0F;
        this.ArmLeft03.yRot = 0.35F;
        this.ArmLeft03.zRot = 0.0F;
        this.ArmLeft06.xRot = 0.0873F;
        this.ArmLeft06.yRot = 0.14F;
        this.ArmLeft06.zRot = 0.26F;
        this.ArmLeft07.xRot = -0.2618F;

        this.ArmRight01.xRot = -2.967F;
        this.ArmRight01.yRot = 0.6981F;
        this.ArmRight01.zRot = -0.08F;
        this.ArmRight03.xRot = 0.0F;
        this.ArmRight03.yRot = -0.35F;
        this.ArmRight03.zRot = 0.0F;
        this.ArmRight06.zRot = -0.26F;
        this.ArmRight07.xRot = -0.2618F;

        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.LegLeft01.xRot = -1.7F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.05F;
        this.LegLeft02.xRot = 0.7F;
        this.LegRight01.xRot = -1.7F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.05F;
        this.LegRight02.xRot = 0.7F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.7F;
        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY = angleX * 0.05F + 0.025F;
        }

        this.Head.xRot = headPitch * 0.014F;
        this.Head.yRot = netHeadYaw * 0.01F;
        this.Head.zRot = 0.0F;
        float headX = this.Head.xRot * -0.5F;

        this.BoobL.xRot = angleX * 0.08F - 0.76F;
        this.BoobR.xRot = angleX * 0.08F - 0.76F;
        this.Ahoke.yRot = angleX * 0.15F + 0.6F;
        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.3142F;

        this.Hair01.xRot = angleX * 0.03F + 0.21F + headX;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.08F + headX;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.14F;
        this.Hair03.zRot = 0.0F;

        this.ArmLeft01.xRot = -0.2618F;
        this.ArmLeft01.yRot = 0.7F;
        this.ArmLeft01.zRot = 0.0F;
        this.ArmLeft03.xRot = 0.0F;
        this.ArmLeft03.yRot = 0.35F;
        this.ArmLeft03.zRot = 0.0F;
        this.ArmLeft06.xRot = 0.0873F;
        this.ArmLeft06.yRot = 0.14F;
        this.ArmLeft06.zRot = angleX * 0.1F + 0.26F;
        this.ArmLeft07.xRot = -0.2618F;

        this.ArmRight01.xRot = -0.2618F;
        this.ArmRight01.yRot = -0.7F;
        this.ArmRight01.zRot = 0.0F;
        this.ArmRight03.xRot = 0.0F;
        this.ArmRight03.yRot = -0.35F;
        this.ArmRight03.zRot = 0.0F;
        this.ArmRight06.zRot = -angleX * 0.1F - 0.26F;
        this.ArmRight07.xRot = -0.2618F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.05F;
        this.LegLeft02.xRot = 0.0F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.05F;
        this.LegRight02.xRot = 0.0F;

        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.zRot = headZ;
        this.Hair02.zRot = headZ;
        this.HairL01.zRot = headZ - 0.14F;
        this.HairL02.zRot = headZ + 0.087F;
        this.HairR01.zRot = headZ + 0.14F;
        this.HairR02.zRot = headZ - 0.052F;

        this.HairL01.xRot = angleX * 0.02F + headX - 0.14F;
        this.HairL02.xRot = -angleX1 * 0.04F + headX + 0.17F;
        this.HairR01.xRot = angleX * 0.02F + headX - 0.14F;
        this.HairR02.xRot = -angleX1 * 0.04F + headX + 0.17F;

        this.LegLeft01.xRot = angleAdd1 * 0.6F - 0.21F;
        this.LegRight01.xRot = angleAdd2 * 0.6F - 0.21F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        boolean isCrouching = entity.isCrouching();
        boolean isPassenger = entity.isPassenger();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.ArmLeft01.xRot = -0.61F;
            this.ArmLeft01.yRot = 0.35F;
            this.ArmLeft01.zRot = -0.14F;
            this.ArmLeft03.yRot = 0.7F;
            this.ArmLeft06.zRot = -0.35F;
            this.ArmRight01.xRot = -0.61F;
            this.ArmRight01.yRot = -0.35F;
            this.ArmRight01.zRot = 0.14F;
            this.ArmRight03.yRot = -0.7F;
            this.ArmRight06.zRot = 0.35F;

            this.LegLeft01.xRot -= 1.0F;
            this.LegRight01.xRot -= 1.0F;

            this.Hair01.xRot += 0.37F;
            this.Hair02.xRot += 0.23F;
            this.Hair03.xRot -= 0.1F;
        }

        if (isSitting || isPassenger) {
            this.isSittingPose = true;
            this.poseTranslateY += (isPassenger ? RIDING_TRANSLATE_Y : SITTING_TRANSLATE_Y);
            if (hasLegacyState(entity, 1, 4)) {
                this.setFace(2);
                this.poseTranslateY += 0.2F;
                this.Head.xRot = this.Head.xRot * 0.5F + 0.55F;
                this.Head.yRot = this.Head.yRot * 0.5F - 0.2F;
                this.BodyMain.xRot = -0.61F;
                this.BodyMain.yRot = -0.2618F;
                this.BodyMain.zRot = -0.5236F;
                this.ArmLeft01.xRot = 1.3F;
                this.ArmLeft01.yRot = 0.7F;
                this.ArmLeft01.zRot = -0.1745F;
                this.ArmLeft03.xRot = -2.53F;
                this.ArmLeft03.yRot = -0.7F;
                this.ArmLeft06.xRot = -0.5236F;
                this.ArmLeft06.yRot = -0.5236F;
                this.ArmLeft06.zRot = 0.7F;
                this.ArmRight01.xRot = 0.7F;
                this.ArmRight01.yRot = 0.0F;
                this.ArmRight01.zRot = 0.5236F;
                this.ArmRight03.xRot = -1.57F;
                this.ArmRight03.yRot = 0.14F;
                this.ArmRight03.zRot = 1.7453F;
                this.ArmRight06.zRot = -0.5236F;
                this.LegLeft01.xRot = -1.05F;
                this.LegRight01.xRot = -1.31F;
                this.LegLeft01.zRot = -0.5236F;
                this.LegLeft02.xRot = 1.05F;
                this.LegRight01.yRot = -0.4363F;
                this.LegRight02.xRot = 0.7F;
                this.Hair01.xRot -= 0.12F;
                this.Hair01.zRot = -0.09F;
            } else {
                this.Head.xRot -= 0.25F;
                this.ArmLeft01.xRot = -0.44F;
                this.ArmLeft01.yRot = 0.44F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft03.yRot = 0.87F;
                this.ArmLeft06.zRot = 0.1F;
                this.ArmRight01.xRot = -0.44F;
                this.ArmRight01.yRot = -0.44F;
                this.ArmRight01.zRot = 0.0F;
                this.ArmRight03.yRot = -0.87F;
                this.ArmRight06.zRot = -0.1F;
                this.LegLeft01.xRot = -1.2217F;
                this.LegRight01.xRot = -1.2217F;
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.37F * OFFSET_SCALE);
                this.LegRight02.z = this.legRight02DefaultZ + (0.37F * OFFSET_SCALE);
                this.LegLeft01.yRot = 0.14F;
                this.LegRight01.yRot = -0.14F;
                this.LegLeft02.xRot = 2.53F;
                this.LegRight02.xRot = 2.53F;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            if (entity.getAttackTick() > 25) {
                this.setFace(3);
            }
            this.ArmLeft01.xRot = -1.4F;
            this.ArmLeft01.yRot = -0.14F;
            this.ArmLeft01.zRot = 0.0F;
            this.ArmLeft06.zRot = -0.96F;
            this.ArmRight01.xRot = -1.4F;
            this.ArmRight01.yRot = 0.14F;
            this.ArmRight01.zRot = 0.0F;
            this.ArmRight06.zRot = 0.96F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 1.0F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.copyFrom(this.BodyMain);
            this.GlowNeck.copyFrom(this.Neck);
            this.GlowHead.copyFrom(this.Head);
            this.GlowHead.getChild("HeadH").copyFrom(this.HeadH);
            this.GlowHead.getChild("HeadH").getChild("HeadH2").copyFrom(this.HeadH2);
            this.GlowHead.getChild("HeadH").getChild("HeadH2").getChild("HeadH3").copyFrom(this.HeadH3);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
