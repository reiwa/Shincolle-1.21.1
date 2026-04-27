package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.EntityNorthernHime;
import org.trp.shincolle.entity.base.EntityShipBase;

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelNorthernHime<T extends EntityNorthernHime> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "northern_hime"), "main");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelNorthernHime");
    private static final float SIT_HEAD_YAW_SCALE = 0.25F;
    private static final float SIT_LEG_Y_ROT = 0.2618F;
    private static final float SIT_LEG_X_ROT = -1.66F;
    private static final float SIT_ARM_Z_ROT_DELTA = 0.05F;
    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelNorthernHime");
    private static final float RIDING_TRANSLATE_Y = LegacyPoseOffsets.ridingY("ModelNorthernHime");
    private static final float RIDING_TRANSLATE_Z = LegacyPoseOffsets.ridingZ("ModelNorthernHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelNorthernHime");
    private static final float BODY_BASE_X_ROT = -0.087F;
    private static final float ARM_BASE_X_ROT = 0.2618F;
    private static final float ARM_BASE_Z_ROT = 0.5235F;
    private static final float LEG_BASE_Z_ROT = 0.05F;
    private static final float LEG_BASE_X_ROT_OFFSET = 0.1745F;
    private final ModelPart BodyMain;
    private final ModelPart Cloth01;
    private final ModelPart Cloth02;
    private final ModelPart Cloth03;
    private final ModelPart SantaCloth01;
    private final ModelPart Neck;
    private final ModelPart Head;
    private final ModelPart HairMain;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart HairS01a;
    private final ModelPart HairS01b;
    private final ModelPart HairS02a;
    private final ModelPart HairS02b;
    private final ModelPart Hair;
    private final ModelPart HairR01;
    private final ModelPart HairR02;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairL02;
    private final ModelPart SantaHat01;
    private final ModelPart SantaHat02;
    private final ModelPart SantaHat03;
    private final ModelPart SantaHat04;
    private final ModelPart SantaHat05;
    private final ModelPart Butt;
    private final ModelPart LegRight01;
    private final ModelPart LegRight02;
    private final ModelPart ShoesR;
    private final ModelPart LegLeft01;
    private final ModelPart LegLeft02;
    private final ModelPart ShoesL;
    private final ModelPart ShoesL2;
    private final ModelPart EquipBase;
    private final ModelPart EquipLT01;
    private final ModelPart EquipLT02;
    private final ModelPart EquipLT03;
    private final ModelPart EquipLT04;
    private final ModelPart EquipLT05;
    private final ModelPart EquipLT06;
    private final ModelPart EquipRT01;
    private final ModelPart EquipRT02;
    private final ModelPart HeadBase;
    private final ModelPart TailHead1;
    private final ModelPart TailHead2;
    private final ModelPart TailHeadCR1;
    private final ModelPart TailJaw1;
    private final ModelPart TailHeadCL1;
    private final ModelPart ArmRight01;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart ArmRight04;
    private final ModelPart ArmRight05;
    private final ModelPart ArmRightItem;
    private final ModelPart ArmRight06;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart ArmLeft04;
    private final ModelPart ArmLeft05;
    private final ModelPart ArmLeft06;
    private final ModelPart EquipUmbre01a;
    private final ModelPart EquipUmbre01b;
    private final ModelPart EquipUmbre01c;
    private final ModelPart EquipUmbre03a;
    private final ModelPart EquipUmbre03b;
    private final ModelPart EquipUmbre02a;
    private final ModelPart EquipUmbre02b;
    private final ModelPart EquipUmbre02;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart HeadHR;
    private final ModelPart HeadHR2;
    private final ModelPart HeadHR3;
    private final ModelPart HeadHL;
    private final ModelPart HeadHL2;
    private final ModelPart HeadHL3;
    private final ModelPart GlowEquipBase;
    private final ModelPart GlowEquipRT01;
    private final ModelPart GlowEquipRT02;
    private final ModelPart GlowHeadBase;
    private final ModelPart GlowTailHead1;
    private final ModelPart TailHeadT01;
    private final ModelPart GlowTailHead2;
    private final ModelPart TailHeadC2;
    private final ModelPart TailHeadC3;
    private final ModelPart GlowTailJaw1;
    private final ModelPart TailJawT01;
    private final ModelPart EquipRoad01;
    private final ModelPart EquipRoad02;
    private final ModelPart EquipRoad03;
    private final ModelPart GlowEquipLT01;
    private final ModelPart GlowEquipLT02;
    private final ModelPart GlowEquipLT03;
    private final ModelPart GlowEquipLT04;
    private final ModelPart GlowEquipLT05;
    private final ModelPart GlowEquipLT06;
    private final ModelPart EquipLHead;
    private final ModelPart EquipLHead01;
    private final ModelPart EquipLHead02;
    private final ModelPart EquipLHead03;
    private boolean isDeadPose;
    private float poseTranslateY;
    private float poseTranslateZ;
    private boolean isSittingPose;

    public ModelNorthernHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02 = this.Cloth01.getChild("Cloth02");
        this.Cloth03 = this.Cloth02.getChild("Cloth03");
        this.SantaCloth01 = this.Cloth03.getChild("SantaCloth01");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.HairS01a = this.Hair01.getChild("HairS01a");
        this.HairS01b = this.HairS01a.getChild("HairS01b");
        this.HairS02a = this.Hair01.getChild("HairS02a");
        this.HairS02b = this.HairS02a.getChild("HairS02b");
        this.Hair = this.Head.getChild("Hair");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.SantaHat01 = this.Head.getChild("SantaHat01");
        this.SantaHat02 = this.SantaHat01.getChild("SantaHat02");
        this.SantaHat03 = this.SantaHat02.getChild("SantaHat03");
        this.SantaHat04 = this.SantaHat03.getChild("SantaHat04");
        this.SantaHat05 = this.SantaHat04.getChild("SantaHat05");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoesR = this.LegRight02.getChild("ShoesR");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoesL = this.LegLeft02.getChild("ShoesL");
        this.ShoesL2 = this.LegLeft01.getChild("ShoesL2");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipLT01 = this.EquipBase.getChild("EquipLT01");
        this.EquipLT02 = this.EquipLT01.getChild("EquipLT02");
        this.EquipLT03 = this.EquipLT02.getChild("EquipLT03");
        this.EquipLT04 = this.EquipLT03.getChild("EquipLT04");
        this.EquipLT05 = this.EquipLT04.getChild("EquipLT05");
        this.EquipLT06 = this.EquipLT05.getChild("EquipLT06");
        this.EquipRT01 = this.EquipBase.getChild("EquipRT01");
        this.EquipRT02 = this.EquipRT01.getChild("EquipRT02");
        this.HeadBase = this.EquipRT02.getChild("HeadBase");
        this.TailHead1 = this.HeadBase.getChild("TailHead1");
        this.TailHead2 = this.TailHead1.getChild("TailHead2");
        this.TailHeadCR1 = this.HeadBase.getChild("TailHeadCR1");
        this.TailJaw1 = this.HeadBase.getChild("TailJaw1");
        this.TailHeadCL1 = this.HeadBase.getChild("TailHeadCL1");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.ArmRight04 = this.ArmRight03.getChild("ArmRight04");
        this.ArmRight05 = this.ArmRight04.getChild("ArmRight05");
        this.ArmRightItem = this.ArmRight05.getChild("ArmRightItem");
        this.ArmRight06 = this.ArmRight05.getChild("ArmRight06");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.ArmLeft04 = this.ArmLeft03.getChild("ArmLeft04");
        this.ArmLeft05 = this.ArmLeft04.getChild("ArmLeft05");
        this.ArmLeft06 = this.ArmLeft05.getChild("ArmLeft06");
        this.EquipUmbre01a = this.ArmLeft05.getChild("EquipUmbre01a");
        this.EquipUmbre01b = this.EquipUmbre01a.getChild("EquipUmbre01b");
        this.EquipUmbre01c = this.EquipUmbre01b.getChild("EquipUmbre01c");
        this.EquipUmbre03a = this.EquipUmbre01c.getChild("EquipUmbre03a");
        this.EquipUmbre03b = this.EquipUmbre03a.getChild("EquipUmbre03b");
        this.EquipUmbre02a = this.EquipUmbre01c.getChild("EquipUmbre02a");
        this.EquipUmbre02b = this.EquipUmbre02a.getChild("EquipUmbre02b");
        this.EquipUmbre02 = this.EquipUmbre01a.getChild("EquipUmbre02");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.HeadHR = this.GlowHead.getChild("HeadHR");
        this.HeadHR2 = this.HeadHR.getChild("HeadHR2");
        this.HeadHR3 = this.HeadHR2.getChild("HeadHR3");
        this.HeadHL = this.GlowHead.getChild("HeadHL");
        this.HeadHL2 = this.HeadHL.getChild("HeadHL2");
        this.HeadHL3 = this.HeadHL2.getChild("HeadHL3");
        this.GlowEquipBase = this.GlowBodyMain.getChild("GlowEquipBase");
        this.GlowEquipRT01 = this.GlowEquipBase.getChild("GlowEquipRT01");
        this.GlowEquipRT02 = this.GlowEquipRT01.getChild("GlowEquipRT02");
        this.GlowHeadBase = this.GlowEquipRT02.getChild("GlowHeadBase");
        this.GlowTailHead1 = this.GlowHeadBase.getChild("GlowTailHead1");
        this.TailHeadT01 = this.GlowTailHead1.getChild("TailHeadT01");
        this.GlowTailHead2 = this.GlowTailHead1.getChild("GlowTailHead2");
        this.TailHeadC2 = this.GlowTailHead2.getChild("TailHeadC2");
        this.TailHeadC3 = this.GlowTailHead2.getChild("TailHeadC3");
        this.GlowTailJaw1 = this.GlowHeadBase.getChild("GlowTailJaw1");
        this.TailJawT01 = this.GlowTailJaw1.getChild("TailJawT01");
        this.EquipRoad01 = this.GlowHeadBase.getChild("EquipRoad01");
        this.EquipRoad02 = this.EquipRoad01.getChild("EquipRoad02");
        this.EquipRoad03 = this.EquipRoad02.getChild("EquipRoad03");
        this.GlowEquipLT01 = this.GlowEquipBase.getChild("GlowEquipLT01");
        this.GlowEquipLT02 = this.GlowEquipLT01.getChild("GlowEquipLT02");
        this.GlowEquipLT03 = this.GlowEquipLT02.getChild("GlowEquipLT03");
        this.GlowEquipLT04 = this.GlowEquipLT03.getChild("GlowEquipLT04");
        this.GlowEquipLT05 = this.GlowEquipLT04.getChild("GlowEquipLT05");
        this.GlowEquipLT06 = this.GlowEquipLT05.getChild("GlowEquipLT06");
        this.EquipLHead = this.GlowEquipLT06.getChild("EquipLHead");
        this.EquipLHead01 = this.EquipLHead.getChild("EquipLHead01");
        this.EquipLHead02 = this.EquipLHead01.getChild("EquipLHead02");
        this.EquipLHead03 = this.EquipLHead02.getChild("EquipLHead03");

        this.initFaceParts(GlowHead);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 114).addBox(-6.5F, -11.0F, -4.0F, 13.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(128, 75).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -4.4F));

        PartDefinition Cloth02 = Cloth01.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(128, 87).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -0.3F, 0.1396F, 0.0F, 0.0F));

        PartDefinition Cloth03 = Cloth02.addOrReplaceChild("Cloth03", CubeListBuilder.create().texOffs(128, 100).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -0.2F, 0.1396F, 0.0F, 0.0F));

        PartDefinition SantaCloth01 = Cloth03.addOrReplaceChild("SantaCloth01", CubeListBuilder.create().texOffs(128, 114).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -0.3F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(129, 58).addBox(-7.0F, -2.0F, -6.0F, 14.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.3F, -0.5F, 0.0524F, 0.0F, 0.0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7.0F, -14.5F, -6.5F, 14.0F, 14.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(48, 55).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, -3.0F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(1, 70).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 2.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 70).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.5F, 7.5F, 0.1367F, 0.0F, 0.0F));

        PartDefinition HairS01a = Hair01.addOrReplaceChild("HairS01a", CubeListBuilder.create().texOffs(38, 19).addBox(0.0F, 0.0F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, -1.0F, 3.5F, 0.087F, 0.0F, -0.2618F));

        PartDefinition HairS01b = HairS01a.addOrReplaceChild("HairS01b", CubeListBuilder.create().texOffs(46, 26).addBox(0.0F, 0.0F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition HairS02a = Hair01.addOrReplaceChild("HairS02a", CubeListBuilder.create().texOffs(38, 19).addBox(0.0F, 0.0F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, 3.0F, 2.5F, 0.087F, 0.0F, 0.35F));

        PartDefinition HairS02b = HairS02a.addOrReplaceChild("HairS02b", CubeListBuilder.create().texOffs(38, 25).addBox(0.0F, 0.0F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, 0.0F, 0.35F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8.0F, -8.0F, -7.2F, 16.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.5F, 0.0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(86, 102).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, 3.0F, -4.5F, -0.2618F, 0.1745F, 0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(86, 102).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 7.5F, 0.0F, 0.2618F, 0.0F, -0.0524F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0.0F, -12.0F, -6.0F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -5.0F, 0.35F, 2.1F, 0.0F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(86, 102).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, 3.0F, -4.5F, -0.2618F, -0.1745F, -0.1396F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(86, 102).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 7.5F, 0.0F, 0.2618F, 0.0F, 0.0873F));

        PartDefinition SantaHat01 = Head.addOrReplaceChild("SantaHat01", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0.0F, -6.5F, 13.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -16.5F, 3.0F, -0.4363F, 0.8727F, -0.1396F));

        PartDefinition SantaHat02 = SantaHat01.addOrReplaceChild("SantaHat02", CubeListBuilder.create().texOffs(58, 24).addBox(-4.5F, -8.0F, -4.5F, 9.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -0.5F, -0.5236F, 0.1745F, 0.0F));

        PartDefinition SantaHat03 = SantaHat02.addOrReplaceChild("SantaHat03", CubeListBuilder.create().texOffs(65, 27).addBox(-2.5F, -6.0F, -2.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -1.0F, -0.2731F, 0.0F, -0.5009F));

        PartDefinition SantaHat04 = SantaHat03.addOrReplaceChild("SantaHat04", CubeListBuilder.create().texOffs(67, 28).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.5F, 0.0F, -1.1383F, -0.2731F, 0.0F));

        PartDefinition SantaHat05 = SantaHat04.addOrReplaceChild("SantaHat05", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.8F, 2.0F, 0.6109F, 0.6981F, -0.5236F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(92, 28).addBox(-5.5F, 0.0F, 0.0F, 11.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -4.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 99).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, 5.5F, 2.4F, -0.1745F, 0.0F, 0.0524F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 99).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -2.5F));

        PartDefinition ShoesR = LegRight02.addOrReplaceChild("ShoesR", CubeListBuilder.create().texOffs(80, 45).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 2.5F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 99).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2F, 5.5F, 2.4F, -0.1745F, 0.0F, -0.0524F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 99).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -2.5F));

        PartDefinition ShoesL = LegLeft02.addOrReplaceChild("ShoesL", CubeListBuilder.create().texOffs(80, 45).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 2.5F));

        PartDefinition ShoesL2 = LegLeft01.addOrReplaceChild("ShoesL2", CubeListBuilder.create().texOffs(80, 45).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition EquipLT01 = EquipBase.addOrReplaceChild("EquipLT01", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, 2.5F, 0.0F, -1.0472F, -0.2618F));

        PartDefinition EquipLT02 = EquipLT01.addOrReplaceChild("EquipLT02", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition EquipLT03 = EquipLT02.addOrReplaceChild("EquipLT03", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition EquipLT04 = EquipLT03.addOrReplaceChild("EquipLT04", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition EquipLT05 = EquipLT04.addOrReplaceChild("EquipLT05", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition EquipLT06 = EquipLT05.addOrReplaceChild("EquipLT06", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.5F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition EquipRT01 = EquipBase.addOrReplaceChild("EquipRT01", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -2.0F, -2.0F, 16.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.0F, 0.7854F, 0.3491F));

        PartDefinition EquipRT02 = EquipRT01.addOrReplaceChild("EquipRT02", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -2.0F, -4.0F, 16.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, 0.0F, 2.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition HeadBase = EquipRT02.addOrReplaceChild("HeadBase", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.0F, 2.0F, 12.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -3.0F, 0.0F, -0.4363F, -2.7925F, -0.1396F));

        PartDefinition TailHead1 = HeadBase.addOrReplaceChild("TailHead1", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -0.2F, -5.6F, 14.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, 4.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition TailHead2 = TailHead1.addOrReplaceChild("TailHead2", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 4.5F));

        PartDefinition TailHeadCR1 = HeadBase.addOrReplaceChild("TailHeadCR1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -5.0F, 12.0F, 0.0F, -0.0524F, 0.0F));

        PartDefinition TailJaw1 = HeadBase.addOrReplaceChild("TailJaw1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0.0F, 0.0F, 13.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 5.0F, -0.2731F, 0.0F, 0.0F));

        PartDefinition TailHeadCL1 = HeadBase.addOrReplaceChild("TailHeadCL1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -5.0F, 12.0F, 0.0F, 0.0524F, 0.0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 100).addBox(-3.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -9.8F, -0.7F, 0.2618F, 0.0F, 0.5236F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(2, 100).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 2.0F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(0, 90).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

        PartDefinition ArmRight04 = ArmRight03.addOrReplaceChild("ArmRight04", CubeListBuilder.create().texOffs(72, 43).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

        PartDefinition ArmRight05 = ArmRight04.addOrReplaceChild("ArmRight05", CubeListBuilder.create().texOffs(20, 100).addBox(-3.0F, 0.0F, -3.5F, 6.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition ArmRightItem = ArmRight05.addOrReplaceChild("ArmRightItem", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ArmRight06 = ArmRight05.addOrReplaceChild("ArmRight06", CubeListBuilder.create().texOffs(20, 100).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, -1.5F, -0.0873F, -0.0873F, -0.1745F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 100).addBox(-1.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -9.8F, -0.7F, -0.2731F, 0.0F, -0.5236F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(2, 100).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 4.0F, 2.0F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(0, 90).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

        PartDefinition ArmLeft04 = ArmLeft03.addOrReplaceChild("ArmLeft04", CubeListBuilder.create().texOffs(72, 43).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

        PartDefinition ArmLeft05 = ArmLeft04.addOrReplaceChild("ArmLeft05", CubeListBuilder.create().texOffs(20, 100).addBox(-3.0F, 0.0F, -3.5F, 6.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition ArmLeft06 = ArmLeft05.addOrReplaceChild("ArmLeft06", CubeListBuilder.create().texOffs(20, 100).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.0F, -1.5F, -0.0873F, 0.0873F, 0.1745F));

        PartDefinition EquipUmbre01a = ArmLeft05.addOrReplaceChild("EquipUmbre01a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, -1.0F));

        PartDefinition EquipUmbre01b = EquipUmbre01a.addOrReplaceChild("EquipUmbre01b", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

        PartDefinition EquipUmbre01c = EquipUmbre01b.addOrReplaceChild("EquipUmbre01c", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -12.0F));

        PartDefinition EquipUmbre03a = EquipUmbre01c.addOrReplaceChild("EquipUmbre03a", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -7.0F, 0.0F, 13.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.4F, 0.0F, -0.2618F, 0.3643F));

        PartDefinition EquipUmbre03b = EquipUmbre03a.addOrReplaceChild("EquipUmbre03b", CubeListBuilder.create().texOffs(54, 0).addBox(-2.0F, -6.0F, 0.0F, 5.0F, 12.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 2.0F, 2.9F, -0.0911F, 0.6829F, 0.1367F));

        PartDefinition EquipUmbre02a = EquipUmbre01c.addOrReplaceChild("EquipUmbre02a", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -9.0F, -2.0F, 20.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -12.0F, 0.0F, 0.1745F, 0.5236F));

        PartDefinition EquipUmbre02b = EquipUmbre02a.addOrReplaceChild("EquipUmbre02b", CubeListBuilder.create().texOffs(54, 0).addBox(-11.0F, -8.0F, 0.0F, 13.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, -0.0524F, -0.0873F, 0.0F));

        PartDefinition EquipUmbre02 = EquipUmbre01a.addOrReplaceChild("EquipUmbre02", CubeListBuilder.create().texOffs(38, 57).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.3F, -0.5F, 0.0524F, 0.0F, 0.0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition HeadHR = GlowHead.addOrReplaceChild("HeadHR", CubeListBuilder.create().texOffs(30, 90).addBox(-3.0F, -2.5F, -2.5F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9F, -10.8F, 1.0F, -0.7854F, 0.1745F, 0.3142F));

        PartDefinition HeadHR2 = HeadHR.addOrReplaceChild("HeadHR2", CubeListBuilder.create().texOffs(30, 90).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

        PartDefinition HeadHR3 = HeadHR2.addOrReplaceChild("HeadHR3", CubeListBuilder.create().texOffs(30, 90).addBox(-1.0F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition HeadHL = GlowHead.addOrReplaceChild("HeadHL", CubeListBuilder.create().texOffs(30, 90).addBox(0.0F, -2.5F, -2.5F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9F, -10.9F, 1.0F, -0.7854F, -0.1745F, -0.3142F));

        PartDefinition HeadHL2 = HeadHL.addOrReplaceChild("HeadHL2", CubeListBuilder.create().texOffs(30, 90).addBox(0.0F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition HeadHL3 = HeadHL2.addOrReplaceChild("HeadHL3", CubeListBuilder.create().texOffs(30, 90).addBox(0.0F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition GlowEquipBase = GlowBodyMain.addOrReplaceChild("GlowEquipBase", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition GlowEquipRT01 = GlowEquipBase.addOrReplaceChild("GlowEquipRT01", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.0F, 0.7854F, 0.3491F));

        PartDefinition GlowEquipRT02 = GlowEquipRT01.addOrReplaceChild("GlowEquipRT02", CubeListBuilder.create(), PartPose.offsetAndRotation(-16.0F, 0.0F, 2.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition GlowHeadBase = GlowEquipRT02.addOrReplaceChild("GlowHeadBase", CubeListBuilder.create(), PartPose.offsetAndRotation(-14.0F, -3.0F, 0.0F, -0.4363F, -2.7925F, -0.1396F));

        PartDefinition GlowTailHead1 = GlowHeadBase.addOrReplaceChild("GlowTailHead1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9.5F, 4.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition TailHeadT01 = GlowTailHead1.addOrReplaceChild("TailHeadT01", CubeListBuilder.create().texOffs(0, 55).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, 4.5F, -0.1745F, 0.0F, 0.0F));

        PartDefinition GlowTailHead2 = GlowTailHead1.addOrReplaceChild("GlowTailHead2", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 4.5F));

        PartDefinition TailHeadC2 = GlowTailHead2.addOrReplaceChild("TailHeadC2", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2F, 3.2F, 10.5F, 0.0873F, 0.0873F, 0.0176F));

        PartDefinition TailHeadC3 = GlowTailHead2.addOrReplaceChild("TailHeadC3", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, 3.2F, 10.5F, 0.0873F, -0.0873F, 0.0F));

        PartDefinition GlowTailJaw1 = GlowHeadBase.addOrReplaceChild("GlowTailJaw1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.0F, 5.0F, -0.2731F, 0.0F, 0.0F));

        PartDefinition TailJawT01 = GlowTailJaw1.addOrReplaceChild("TailJawT01", CubeListBuilder.create().texOffs(0, 56).addBox(-5.5F, 0.0F, 0.0F, 11.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 4.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition EquipRoad01 = GlowHeadBase.addOrReplaceChild("EquipRoad01", CubeListBuilder.create().texOffs(46, 41).addBox(0.0F, 0.0F, 0.0F, 7.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -11.5F, -3.0F, -0.2094F, 0.0873F, 0.0F));

        PartDefinition EquipRoad02 = EquipRoad01.addOrReplaceChild("EquipRoad02", CubeListBuilder.create().texOffs(46, 41).addBox(0.0F, 0.0F, 0.0F, 7.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

        PartDefinition EquipRoad03 = EquipRoad02.addOrReplaceChild("EquipRoad03", CubeListBuilder.create().texOffs(46, 41).addBox(0.0F, 0.0F, 0.0F, 7.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

        PartDefinition GlowEquipLT01 = GlowEquipBase.addOrReplaceChild("GlowEquipLT01", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 4.0F, 2.5F, 0.0F, -1.0472F, -0.2618F));

        PartDefinition GlowEquipLT02 = GlowEquipLT01.addOrReplaceChild("GlowEquipLT02", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition GlowEquipLT03 = GlowEquipLT02.addOrReplaceChild("GlowEquipLT03", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition GlowEquipLT04 = GlowEquipLT03.addOrReplaceChild("GlowEquipLT04", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition GlowEquipLT05 = GlowEquipLT04.addOrReplaceChild("GlowEquipLT05", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition GlowEquipLT06 = GlowEquipLT05.addOrReplaceChild("GlowEquipLT06", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

        PartDefinition EquipLHead = GlowEquipLT06.addOrReplaceChild("EquipLHead", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, -3.5F, -5.0F, 10.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, -1.0F, 0.0F, -0.6981F, -0.1745F));

        PartDefinition EquipLHead01 = EquipLHead.addOrReplaceChild("EquipLHead01", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -1.0F, 0.0F, 12.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, -4.0F, 0.0F, -0.5236F, -0.3491F));

        PartDefinition EquipLHead02 = EquipLHead01.addOrReplaceChild("EquipLHead02", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -1.0F, 0.0F, 12.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, 0.0F, 0.0F, 0.0F, 0.5236F, -0.2618F));

        PartDefinition EquipLHead03 = EquipLHead02.addOrReplaceChild("EquipLHead03", CubeListBuilder.create().texOffs(24, 48).addBox(-5.0F, -1.5F, -1.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, 0.0F, 0.0F, 0.0F, 0.3187F, 0.0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, LEG_BASE_X_ROT_OFFSET);
        resetPoseState();

        applyFaceAndGear(entity, ctx);
        if (isDeadPose(entity)) {
            applyDeadPose();
            syncGlowParts();
            return;
        }
        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);
        applyBasePose(ctx);
        applyHeadTiltHair();
        applyEquipAnimation(entity, ctx, ageInTicks, limbSwingAmount);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks);
        syncGlowParts();
    }

    private void applyEquipVisibility(EntityShipBase ship) {
        if (ship instanceof EntityNorthernHime northernHime) {
            boolean cannon = northernHime.getEquipFlag(EntityNorthernHime.EQUIP_CANNON);
            boolean santaCloth = northernHime.getEquipFlag(EntityNorthernHime.EQUIP_SANTA_CLOTH);
            boolean santaHat = northernHime.getEquipFlag(EntityNorthernHime.EQUIP_SANTA_HAT);
            boolean umbrella = northernHime.getEquipFlag(EntityNorthernHime.EQUIP_UMBRELLA);
            boolean shoes = northernHime.getEquipFlag(EntityNorthernHime.EQUIP_SHOES);

            this.GlowEquipBase.visible = cannon;
            this.EquipBase.visible = cannon;
            this.SantaCloth01.visible = santaCloth;
            this.SantaHat01.visible = santaHat;
            this.EquipUmbre01a.visible = umbrella;
            this.ShoesL.visible = shoes;
            this.ShoesL2.visible = shoes;
            this.ShoesR.visible = shoes;
        }
    }

    private void applyBasePose(PoseContext ctx) {
        float headX = Head.xRot * -0.5F;

        BodyMain.xRot = BODY_BASE_X_ROT;
        Ahoke.xRot = ctx.angleX * 0.25F + 0.35F;
        Hair01.xRot = ctx.angleX * 0.02F + 0.35F + headX;
        Hair02.xRot = ctx.angleX * 0.04F + 0.14F + headX;
        HairL01.xRot = ctx.angleX * 0.02F + headX - 0.26F;
        HairL02.xRot = ctx.angleX * 0.02F + headX + 0.26F;
        HairR01.xRot = ctx.angleX * 0.02F + headX - 0.26F;
        HairR02.xRot = ctx.angleX * 0.02F + headX + 0.26F;

        ArmLeft01.xRot = ctx.angleAdd2 + ARM_BASE_X_ROT;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = -ctx.angleX * 0.1F - ARM_BASE_Z_ROT;
        ArmLeft02.xRot = 0.0F;
        ArmLeft04.yRot = 0.0F;

        ArmRight01.xRot = ctx.angleAdd1 + ARM_BASE_X_ROT;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = ctx.angleX * 0.1F + ARM_BASE_Z_ROT;
        ArmRight02.xRot = 0.0F;
        ArmRight04.yRot = 0.0F;

        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = -LEG_BASE_Z_ROT;
        LegLeft02.xRot = 0.0F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = LEG_BASE_Z_ROT;
        LegRight02.xRot = 0.0F;
    }

    private void applyFaceAndGear(T entity, PoseContext ctx) {
        if (entity instanceof EntityShipBase ship) {
            applyFaceAndMouth(ship);
            setFlushVisible(ship.getEmotionPrimary() == EntityShipBase.EMOTION_SHY
                    || ship.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY);
            applyEquipVisibility(ship);
        }
    }

    private void applyEquipAnimation(T entity, PoseContext ctx, float ageInTicks, float limbSwingAmount) {
        float angleX = Mth.cos(ageInTicks * 0.08F);
        boolean showCannon = entity.getEquipFlag(EntityNorthernHime.EQUIP_CANNON);
        boolean showUmbrella = entity.getEquipFlag(EntityNorthernHime.EQUIP_UMBRELLA);

        ArmLeft02.y = 0.0F;
        EquipUmbre03b.yRot = 0.0F;

        if (showCannon) {
            EquipBase.xRot = 0.0F;
            TailJaw1.xRot = angleX * 0.08F - 0.15F;
            TailHeadC2.xRot = angleX * 0.12F;
            TailHeadC3.xRot = -angleX * 0.08F + 0.1F;
            EquipLHead01.yRot = angleX * 0.1F - 0.5F;
            EquipLHead01.zRot = -angleX * 0.1F - 0.1F;
            EquipLHead02.yRot = angleX * 0.3F + 0.1F;
            EquipLHead02.zRot = -angleX * 0.3F;
        }

        if (showUmbrella) {
            ArmLeft01.xRot = 0.0F;
            ArmLeft01.yRot = -0.26F;
            ArmLeft01.zRot = -0.52F;
            ArmLeft02.y = 0.25F * OFFSET_SCALE;
            ArmLeft02.xRot = -1.57F;
            ArmLeft04.yRot = -0.52F;
            EquipUmbre03b.yRot = angleX * 0.3F + 0.7F;
        }

        if (entity.isSprinting() || limbSwingAmount > 0.9F) {
            this.setFace(EntityShipBase.FACE_TENSION);
            ArmLeft01.zRot = -1.0F;
            ArmRight01.xRot = -2.9F;
            ArmRight01.zRot = -0.7F;
            if (showUmbrella) {
                ArmLeft04.yRot = -1.0F;
            }
        }
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(this.BodyMain);
            GlowNeck.copyFrom(Neck);
            GlowHead.copyFrom(Head);
            GlowTailJaw1.copyFrom(TailJaw1);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F || this.poseTranslateZ != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, this.poseTranslateZ);
        }

        if (this.BodyMain != null) {
            this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F || this.poseTranslateZ != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, this.poseTranslateZ);
        }

        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
        this.poseTranslateZ = 0.0F;
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = 0.5F;
        Head.yRot = 0.0F;
        BodyMain.xRot = BODY_BASE_X_ROT;
        Hair01.xRot = 0.2F;
        Hair02.xRot = -0.3F;
        HairL01.xRot = -0.26F;
        HairL02.xRot = 0.26F;
        HairR01.xRot = -0.26F;
        HairR02.xRot = 0.26F;

        ArmLeft01.xRot = ARM_BASE_X_ROT;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = -0.57F;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.y = 0.0F;
        ArmLeft02.z = 0.0F;
        ArmLeft04.yRot = 0.0F;

        ArmRight01.xRot = ARM_BASE_X_ROT;
        ArmRight01.zRot = 0.57F;
        ArmRight02.xRot = 0.0F;

        LegLeft01.xRot = SIT_LEG_X_ROT;
        LegLeft01.yRot = -SIT_LEG_Y_ROT;
        LegLeft01.zRot = -LEG_BASE_Z_ROT;
        LegLeft02.xRot = 0.0F;
        LegRight01.xRot = SIT_LEG_X_ROT;
        LegRight01.yRot = SIT_LEG_Y_ROT;
        LegRight01.zRot = LEG_BASE_Z_ROT;
        LegRight02.xRot = 0.0F;
    }

    private void applyHeadTiltHair() {
        float headZ = Head.zRot * -0.5F;
        Hair01.zRot = headZ;
        Hair02.zRot = headZ;
        HairL01.zRot = headZ - 0.14F;
        HairL02.zRot = headZ + 0.087F;
        HairR01.zRot = headZ + 0.14F;
        HairR02.zRot = headZ - 0.052F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks) {
        float legLeftX = ctx.legAddLeft;
        float legRightX = ctx.legAddRight;
        boolean showUmbrella = entity != null && entity.getEquipFlag(EntityNorthernHime.EQUIP_UMBRELLA);
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean useAltSit = entity != null && entity.getEmotionSecondary() == EntityShipBase.EMOTION_BORED;

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            Head.xRot -= 0.8727F;
            BodyMain.xRot = 1.0472F;
            Hair01.xRot += 0.2236F;
            legLeftX -= 1.2F;
            legRightX -= 1.2F;
            EquipBase.xRot -= 0.8727F;
            if (showUmbrella) {
                ArmLeft01.yRot = -1.05F;
                ArmLeft02.xRot = -2.01F;
                ArmLeft04.yRot = -1.05F;
            }
        }

        if (ctx.isSitting && !isPassenger) {
            this.isSittingPose = true;
            this.poseTranslateY = SITTING_TRANSLATE_Y;
            Head.yRot *= SIT_HEAD_YAW_SCALE;
            if (useAltSit) {
                Head.xRot -= 0.15F;
                BodyMain.xRot = -0.3142F;
                ArmLeft01.xRot = -2.0F;
                ArmLeft01.yRot = -0.35F;
                ArmLeft01.zRot = 0.35F;
                ArmRight01.xRot = -2.9F;
                ArmRight01.yRot = 0.35F;
                ArmRight01.zRot = -0.35F;
                legLeftX = -1.4F;
                legRightX = -1.4F;
                LegLeft01.yRot = -SIT_LEG_Y_ROT;
                LegRight01.yRot = SIT_LEG_Y_ROT;
                ArmLeft02.y = 0.0F;
                ArmLeft02.xRot = 0.0F;
                ArmLeft04.yRot = 0.0F;
            } else {
                ArmLeft01.zRot -= SIT_ARM_Z_ROT_DELTA;
                ArmRight01.zRot += SIT_ARM_Z_ROT_DELTA;
                legLeftX = SIT_LEG_X_ROT;
                legRightX = SIT_LEG_X_ROT;
                LegLeft01.yRot = -SIT_LEG_Y_ROT;
                LegRight01.yRot = SIT_LEG_Y_ROT;
                ArmLeft02.y = 0.0F;
            }
        }

        if (isPassenger) {
            this.poseTranslateY = RIDING_TRANSLATE_Y;
            this.poseTranslateZ = RIDING_TRANSLATE_Z;
            if (ctx.isSitting) {
                ArmLeft01.xRot = -0.8F;
                ArmLeft01.zRot = -0.35F;
                ArmRight01.xRot = -0.8F;
                ArmRight01.zRot = 0.35F;
                legLeftX = SIT_LEG_X_ROT;
                legRightX = SIT_LEG_X_ROT;
                LegLeft01.yRot = -0.5F;
                LegRight01.yRot = 0.5F;
                if (showUmbrella) {
                    ArmLeft02.y = 0.0F;
                    ArmLeft02.xRot = -0.8F;
                    ArmLeft04.yRot = -0.4F;
                }
            } else {
                Head.xRot -= 0.25F;
                ArmLeft01.xRot = -1.2F;
                ArmLeft01.yRot = -0.2F;
                ArmLeft01.zRot = -0.2F;
                ArmRight01.xRot = -2.53F;
                ArmRight01.zRot = -0.7F;
                legLeftX = SIT_LEG_X_ROT;
                legRightX = SIT_LEG_X_ROT;
                LegLeft01.yRot = -0.5F;
                LegRight01.yRot = 0.5F;
                if (showUmbrella) {
                    ArmLeft02.y = 0.0F;
                    ArmLeft02.xRot = -0.2F;
                    ArmLeft04.yRot = -0.4F;
                }
            }
        }

        float attackAnim = entity != null ? entity.getAttackAnim(ageInTicks) : 0.0F;
        float attackTicks = attackAnim * 50.0F;
        float partial = ageInTicks - (int) ageInTicks;
        if (attackTicks > 49.0F) {
            ArmRight01.xRot = -3.5F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.35F;
            ArmRight04.yRot = -1.57F;
        } else if (attackTicks > 46.0F) {
            ArmRight01.xRot = (46.0F - attackTicks + partial) * 0.75F - 0.5F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.35F;
            ArmRight04.yRot = -1.57F;
        } else if (attackTicks > 35.0F) {
            ArmRight01.xRot = -0.5F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = 0.5F;
            ArmRight04.yRot = -1.57F;
        }

        if (!ctx.isSitting && !isPassenger) {
            LegLeft01.yRot = 0.0F;
            LegRight01.yRot = 0.0F;
        }
        LegLeft01.xRot = legLeftX;
        LegRight01.xRot = legRightX;
    }
}