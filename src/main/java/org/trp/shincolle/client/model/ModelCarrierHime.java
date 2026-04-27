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

public class ModelCarrierHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "carrier_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCarrierHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelCarrierHime");
    private static final float MODEL_SCALE = 0.47F;
    private static final float MODEL_OFFSET_Y = 1.7F;

    private boolean isDeadPose;
    private float poseTranslateY;
    private float poseTranslateZ;
    private boolean isSittingPose;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft01;
    private final ModelPart Cloth01;
    private final ModelPart Cloth02;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hair04;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart Hair05;
    private final ModelPart Hair06;
    private final ModelPart Hair07;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft02;
    private final ModelPart LegLeft01a;
    private final ModelPart LegLeft01b;
    private final ModelPart ShoesL01;
    private final ModelPart ShoesL02;
    private final ModelPart ShoesL03;
    private final ModelPart ShoesL04;
    private final ModelPart Skirt02;
    private final ModelPart LegRight01a;
    private final ModelPart LegRight01b;
    private final ModelPart LegRight02;
    private final ModelPart ShoesR01;
    private final ModelPart ShoesR02;
    private final ModelPart ShoesR03;
    private final ModelPart ShoesR04;
    private final ModelPart ArmRight01a;
    private final ModelPart ArmRight01b;
    private final ModelPart ArmRight02;
    private final ModelPart EquipSR01;
    private final ModelPart EquipSR02;
    private final ModelPart EquipSR04;
    private final ModelPart EquipSR03;
    private final ModelPart EquipSR05;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft01a;
    private final ModelPart ArmLeft01b;
    private final ModelPart EquipSL01;
    private final ModelPart EquipSL02;
    private final ModelPart EquipSL04;
    private final ModelPart EquipSL03;
    private final ModelPart EquipSL05;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowBodyMain2;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowArmLeft01;
    private final ModelPart GlowArmLeft02;
    private final ModelPart GlowArmRight01;
    private final ModelPart GlowArmRight02;
    private final float legLeft02DefaultX, legLeft02DefaultY, legLeft02DefaultZ;
    private final float legRight02DefaultX, legRight02DefaultY, legRight02DefaultZ;
    private final float equipSL01DefaultX, equipSL01DefaultY, equipSL01DefaultZ;
    private final float equipSR01DefaultX, equipSR01DefaultY, equipSR01DefaultZ;
    private final float armLeft02DefaultX, armLeft02DefaultY, armLeft02DefaultZ;
    private final float armRight02DefaultX, armRight02DefaultY, armRight02DefaultZ;
    private final float skirt01DefaultY;
    private final float skirt02DefaultY;

    public ModelCarrierHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoesR01 = this.LegRight02.getChild("ShoesR01");
        this.ShoesR02 = this.ShoesR01.getChild("ShoesR02");
        this.ShoesR03 = this.ShoesR02.getChild("ShoesR03");
        this.ShoesR04 = this.ShoesR03.getChild("ShoesR04");
        this.LegRight01a = this.LegRight01.getChild("LegRight01a");
        this.LegRight01b = this.LegRight01.getChild("LegRight01b");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoesL01 = this.LegLeft02.getChild("ShoesL01");
        this.ShoesL02 = this.ShoesL01.getChild("ShoesL02");
        this.ShoesL03 = this.ShoesL02.getChild("ShoesL03");
        this.ShoesL04 = this.ShoesL03.getChild("ShoesL04");
        this.LegLeft01a = this.LegLeft01.getChild("LegLeft01a");
        this.LegLeft01b = this.LegLeft01.getChild("LegLeft01b");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft01b = this.ArmLeft01.getChild("ArmLeft01b");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft01a = this.ArmLeft01.getChild("ArmLeft01a");
        this.Cloth02 = this.BodyMain.getChild("Cloth02");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair04 = this.HairMain.getChild("Hair04");
        this.Hair05 = this.Hair04.getChild("Hair05");
        this.Hair06 = this.Hair05.getChild("Hair06");
        this.Hair07 = this.Hair06.getChild("Hair07");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight01a = this.ArmRight01.getChild("ArmRight01a");
        this.ArmRight01b = this.ArmRight01.getChild("ArmRight01b");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.GlowBodyMain2 = root.getChild("GlowBodyMain2");
        this.GlowArmLeft01 = this.GlowBodyMain2.getChild("GlowArmLeft01");
        this.GlowArmLeft02 = this.GlowArmLeft01.getChild("GlowArmLeft02");
        this.EquipSL01 = this.GlowArmLeft02.getChild("EquipSL01");
        this.EquipSL02 = this.EquipSL01.getChild("EquipSL02");
        this.EquipSL03 = this.EquipSL02.getChild("EquipSL03");
        this.EquipSL04 = this.EquipSL01.getChild("EquipSL04");
        this.EquipSL05 = this.EquipSL04.getChild("EquipSL05");
        this.GlowArmRight01 = this.GlowBodyMain2.getChild("GlowArmRight01");
        this.GlowArmRight02 = this.GlowArmRight01.getChild("GlowArmRight02");
        this.EquipSR01 = this.GlowArmRight02.getChild("EquipSR01");
        this.EquipSR02 = this.EquipSR01.getChild("EquipSR02");
        this.EquipSR03 = this.EquipSR02.getChild("EquipSR03");
        this.EquipSR04 = this.EquipSR01.getChild("EquipSR04");
        this.EquipSR05 = this.EquipSR04.getChild("EquipSR05");
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.equipSL01DefaultX = this.EquipSL01.x;
        this.equipSL01DefaultY = this.EquipSL01.y;
        this.equipSL01DefaultZ = this.EquipSL01.z;
        this.equipSR01DefaultX = this.EquipSR01.x;
        this.equipSR01DefaultY = this.EquipSR01.y;
        this.equipSR01DefaultZ = this.EquipSR01.z;
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultY = this.ArmLeft02.y;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultY = this.ArmRight02.y;
        this.armRight02DefaultZ = this.ArmRight02.z;
        this.skirt01DefaultY = this.Skirt01.y;
        this.skirt02DefaultY = this.Skirt02.y;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(52, 61).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 83).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.1745F, 0F, -0.1047F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(103, 0).mirror().addBox(-3F, 0F, 0F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesR01 = LegRight02.addOrReplaceChild("ShoesR01", CubeListBuilder.create().texOffs(100, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 3F, 0.1396F, 0F, 0F));

        PartDefinition ShoesR02 = ShoesR01.addOrReplaceChild("ShoesR02", CubeListBuilder.create().texOffs(90, 0).mirror().addBox(-4F, 0F, -4F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, -0.7F, 0.2094F, 0F, 0F));

        PartDefinition ShoesR03 = ShoesR02.addOrReplaceChild("ShoesR03", CubeListBuilder.create().texOffs(100, 3).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.4F, -0.7F, -0.1396F, 0F, 0F));

        PartDefinition ShoesR04 = ShoesR03.addOrReplaceChild("ShoesR04", CubeListBuilder.create().texOffs(104, 13).mirror().addBox(-3F, 0F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.2F, -0.3F, -0.2094F, 0F, 0F));

        PartDefinition LegRight01a = LegRight01.addOrReplaceChild("LegRight01a", CubeListBuilder.create().texOffs(95, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.6F, -0.2F, 0.2094F, 0F, 0F));

        PartDefinition LegRight01b = LegRight01.addOrReplaceChild("LegRight01b", CubeListBuilder.create().texOffs(96, 2).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11.6F, -0.1F, 0.2094F, 0F, 0F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(46, 34).addBox(-8.5F, 0F, -6F, 17F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.9F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(42, 47).addBox(-9F, 0F, -6F, 18F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.8F, -0.5F, -0.0873F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 83).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.3491F, 0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(92, 2).addBox(-3F, 0F, 0F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesL01 = LegLeft02.addOrReplaceChild("ShoesL01", CubeListBuilder.create().texOffs(97, 2).addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 3F, 0.1396F, 0F, 0F));

        PartDefinition ShoesL02 = ShoesL01.addOrReplaceChild("ShoesL02", CubeListBuilder.create().texOffs(90, 0).addBox(-4F, 0F, -4F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, -0.7F, 0.2094F, 0F, 0F));

        PartDefinition ShoesL03 = ShoesL02.addOrReplaceChild("ShoesL03", CubeListBuilder.create().texOffs(95, 0).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.4F, -0.7F, -0.1396F, 0F, 0F));

        PartDefinition ShoesL04 = ShoesL03.addOrReplaceChild("ShoesL04", CubeListBuilder.create().texOffs(104, 13).addBox(-3F, 0F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.2F, -0.3F, -0.2094F, 0F, 0F));

        PartDefinition LegLeft01a = LegLeft01.addOrReplaceChild("LegLeft01a", CubeListBuilder.create().texOffs(92, 2).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.6F, -0.2F, 0.2094F, 0F, 0F));

        PartDefinition LegLeft01b = LegLeft01.addOrReplaceChild("LegLeft01b", CubeListBuilder.create().texOffs(93, 3).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11.6F, -0.1F, 0.2094F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(0, 22).addBox(-7F, 0F, -4F, 14F, 5F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.5F, -0.3F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -8.1F, -3.7F, -0.6981F, 0.0873F, 0.0873F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(4, 85).mirror().addBox(-2F, -1F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.3491F, 0F, -0.2618F));

        PartDefinition ArmLeft01b = ArmLeft01.addOrReplaceChild("ArmLeft01b", CubeListBuilder.create().texOffs(90, 6).mirror().addBox(-3F, 0F, -3F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 9F, -0.1F, 0.2094F, 0F, 0F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(96, 2).mirror().addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, 2.5F));

        PartDefinition ArmLeft01a = ArmLeft01.addOrReplaceChild("ArmLeft01a", CubeListBuilder.create().texOffs(90, 9).mirror().addBox(-3F, 0F, -3F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 5.5F, -0.2F, 0.2094F, 0F, 0F));

        PartDefinition Cloth02 = BodyMain.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(36, 93).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0.3F, -4.5F, -6.5F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(100, 2).addBox(-3F, -2F, -3.5F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.2F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(106, 31).addBox(0F, -6F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, -6F, 0.2094F, 0.6981F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(86, 101).mirror().addBox(-1F, 0F, 0F, 2F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 3F, -5.5F, -0.1396F, 0.1745F, 0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(86, 101).mirror().addBox(-1F, 0F, 0F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 7F, 0F, 0.1745F, 0F, -0.0524F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(86, 101).addBox(-1F, 0F, 0F, 2F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 3F, -5.5F, -0.1396F, -0.1745F, -0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(86, 101).addBox(-1F, 0F, 0F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.1745F, 0F, 0.0873F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(0, 57).addBox(-7.5F, 0F, 0F, 15F, 12F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 57).addBox(-7.5F, 0F, 0F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2094F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 58).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, -0.0873F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(0, 35).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -0.1F, -0.1396F, 0F, 0F));

        PartDefinition Hair04 = HairMain.addOrReplaceChild("Hair04", CubeListBuilder.create().texOffs(108, 0).addBox(0F, -2F, -2F, 2F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 3.5F, 6F, 0F, -0.0873F, -0.0873F));

        PartDefinition Hair05 = Hair04.addOrReplaceChild("Hair05", CubeListBuilder.create().texOffs(108, 28).addBox(0F, -2.5F, -2.5F, 5F, 9F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, -1F, 0F, 0.1047F, -0.0873F, -0.1745F));

        PartDefinition Hair06 = Hair05.addOrReplaceChild("Hair06", CubeListBuilder.create().texOffs(109, 28).addBox(-2F, 0F, -2.5F, 4F, 7F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 4F, 0F, 0.2094F, 0F, 0.1396F));

        PartDefinition Hair07 = Hair06.addOrReplaceChild("Hair07", CubeListBuilder.create().texOffs(110, 29).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6F, 0F, -0.2618F, 0F, 0.1396F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 85).mirror().addBox(-3F, -1F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0F, 0F, 0.2618F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(93, 0).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, 2.5F));

        PartDefinition ArmRight01a = ArmRight01.addOrReplaceChild("ArmRight01a", CubeListBuilder.create().texOffs(92, 3).addBox(-3F, 0F, -3F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 5.5F, -0.2F, 0.2094F, 0F, 0F));

        PartDefinition ArmRight01b = ArmRight01.addOrReplaceChild("ArmRight01b", CubeListBuilder.create().texOffs(92, 0).addBox(-3F, 0F, -3F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 9F, -0.1F, 0.2094F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -8.1F, -3.7F, -0.6981F, -0.0873F, -0.0873F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowBodyMain2 = partdefinition.addOrReplaceChild("GlowBodyMain2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowArmLeft01 = GlowBodyMain2.addOrReplaceChild("GlowArmLeft01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.3491F, 0F, -0.2618F));

        PartDefinition GlowArmLeft02 = GlowArmLeft01.addOrReplaceChild("GlowArmLeft02", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(3F, 12F, 2.5F));

        PartDefinition EquipSL01 = GlowArmLeft02.addOrReplaceChild("EquipSL01", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 10.5F, -6F, -1.5708F, 0F, 1.5708F));

        PartDefinition EquipSL02 = EquipSL01.addOrReplaceChild("EquipSL02", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSL03 = EquipSL02.addOrReplaceChild("EquipSL03", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSL04 = EquipSL01.addOrReplaceChild("EquipSL04", CubeListBuilder.create().texOffs(109, 0).addBox(-0.5F, -9F, -0.5F, 1F, 9F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipSL05 = EquipSL04.addOrReplaceChild("EquipSL05", CubeListBuilder.create().texOffs(100, 0).addBox(-1F, -2F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 0F));

        PartDefinition GlowArmRight01 = GlowBodyMain2.addOrReplaceChild("GlowArmRight01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0F, 0F, 0.2618F));

        PartDefinition GlowArmRight02 = GlowArmRight01.addOrReplaceChild("GlowArmRight02", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(-3F, 12F, 2.5F));

        PartDefinition EquipSR01 = GlowArmRight02.addOrReplaceChild("EquipSR01", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 10.5F, -6F, -1.5708F, 0F, 1.5708F));

        PartDefinition EquipSR02 = EquipSR01.addOrReplaceChild("EquipSR02", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR03 = EquipSR02.addOrReplaceChild("EquipSR03", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR04 = EquipSR01.addOrReplaceChild("EquipSR04", CubeListBuilder.create().texOffs(107, 0).addBox(-0.5F, -9F, -0.5F, 1F, 9F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipSR05 = EquipSR04.addOrReplaceChild("EquipSR05", CubeListBuilder.create().texOffs(100, 0).addBox(-1F, -2F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetPoseState();
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        this.applyFaceAndMouth(entity);

        if (entity != null && entity.isInDeadPose()) {
            this.applyDeadPose();
            this.syncGlowParts();
            return;
        }

        this.applyBasePose(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.applySpecialPoseAdjustments(entity, limbSwing, limbSwingAmount, ageInTicks);
        this.syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
        this.poseTranslateZ = 0.0F;
    }

    private void resetOffsets() {
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.EquipSL01.x = this.equipSL01DefaultX;
        this.EquipSL01.y = this.equipSL01DefaultY;
        this.EquipSL01.z = this.equipSL01DefaultZ;
        this.EquipSR01.x = this.equipSR01DefaultX;
        this.EquipSR01.y = this.equipSR01DefaultY;
        this.EquipSR01.z = this.equipSR01DefaultZ;
        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.y = this.armLeft02DefaultY;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.y = this.armRight02DefaultY;
        this.ArmRight02.z = this.armRight02DefaultZ;
        this.Skirt01.y = this.skirt01DefaultY;
        this.Skirt02.y = this.skirt02DefaultY;
    }

    private void applyEquipVisibility(T entity) {
        boolean showLeft = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierHime.EQUIP_LEFT);
        boolean showRight = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierHime.EQUIP_RIGHT);
        boolean showAny = showLeft || showRight;
        this.GlowBodyMain2.visible = showAny;
        this.GlowArmLeft01.visible = showLeft;
        this.GlowArmRight01.visible = showRight;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.65F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.BoobL.xRot = -0.75F;
        this.BoobR.xRot = -0.75F;
        this.Ahoke.yRot = 0.7F;
        this.BodyMain.xRot = -0.2F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = -0.14F;
        this.Skirt01.xRot = -0.1745F;
        this.Skirt02.xRot = -0.2618F;
        this.Hair01.xRot = -0.1F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -0.2F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -0.14F;
        this.Hair03.zRot = 0.0F;
        this.Hair05.xRot = -0.4F;
        this.Hair05.zRot = 0.0F;
        this.Hair06.xRot = 0.14F;
        this.Hair06.zRot = 0.0F;
        this.Hair07.xRot = -0.2F;
        this.Hair07.zRot = 0.0F;
        this.HairL01.xRot = -0.14F;
        this.HairL01.zRot = 0.087F;
        this.HairL02.xRot = 0.17F;
        this.HairL02.zRot = 0.087F;
        this.HairR01.xRot = -0.14F;
        this.HairR01.zRot = 0.087F;
        this.HairR02.xRot = 0.17F;
        this.HairR02.zRot = -0.052F;

        this.ArmLeft01.xRot = 0.2F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -0.2618F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmRight01.xRot = 0.2F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = 0.2618F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.xRot = -0.9F;
        this.LegLeft01.zRot = -0.14F;
        this.LegLeft02.xRot = 1.2217F;
        this.LegLeft02.yRot = 1.2217F;
        this.LegLeft02.zRot = -1.0472F;
        this.LegLeft02.x = this.legLeft02DefaultX + (0.22F * OFFSET_SCALE);
        this.LegLeft02.y = this.legLeft02DefaultY + (-0.03F * OFFSET_SCALE);
        this.LegLeft02.z = this.legLeft02DefaultZ + (0.2F * OFFSET_SCALE);

        this.LegRight01.xRot = -0.9F;
        this.LegRight01.zRot = 0.14F;
        this.LegRight02.xRot = 1.2217F;
        this.LegRight02.yRot = -1.2217F;
        this.LegRight02.zRot = 1.0472F;
        this.LegRight02.x = this.legRight02DefaultX + (-0.22F * OFFSET_SCALE);
        this.LegRight02.y = this.legRight02DefaultY + (-0.03F * OFFSET_SCALE);
        this.LegRight02.z = this.legRight02DefaultZ + (0.2F * OFFSET_SCALE);
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY += angleX * 0.05F + 0.025F;
        }

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) * 0.8F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.57F;

        this.BoobL.xRot = angleX * 0.06F - 0.75F;
        this.BoobR.xRot = angleX * 0.06F - 0.75F;
        this.Ahoke.yRot = angleX * 0.2F + 0.7F;
        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.35F;
        this.Skirt01.xRot = -0.14F;
        this.Skirt02.xRot = -0.087F;

        this.Hair01.xRot = angleX * 0.03F + 0.21F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.087F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.14F;
        this.Hair03.zRot = 0.0F;
        this.Hair05.xRot = angleX * 0.06F + 0.1F;
        this.Hair05.zRot = 0.0F;
        this.Hair06.xRot = -angleX1 * 0.08F + 0.14F;
        this.Hair06.zRot = 0.0F;
        this.Hair07.xRot = -angleX2 * 0.1F - 0.2F;
        this.Hair07.zRot = 0.0F;
        this.HairL01.xRot = angleX * 0.04F - 0.14F;
        this.HairL01.zRot = 0.0F;
        this.HairL02.xRot = -angleX1 * 0.06F + 0.17F;
        this.HairL02.zRot = 0.0F;
        this.HairR01.xRot = angleX * 0.04F - 0.14F;
        this.HairR01.zRot = 0.0F;
        this.HairR02.xRot = -angleX1 * 0.06F + 0.17F;
        this.HairR02.zRot = 0.0F;

        this.ArmLeft01.xRot = angleAdd2 * 0.25F + 0.35F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = angleX * 0.03F - 0.26F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmRight01.xRot = angleAdd1 * 0.25F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -angleX * 0.03F + 0.26F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.zRot = 0.1F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.zRot = -0.1F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.EquipSL01.xRot = -1.57F;
        this.EquipSL01.yRot = 0.0F;
        this.EquipSL01.zRot = 1.57F;
        this.EquipSR01.xRot = -1.57F;
        this.EquipSR01.yRot = 0.0F;
        this.EquipSR01.zRot = 1.57F;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);

        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;
        float addk1 = angleAdd1 * 0.5F - 0.35F;
        float addk2 = angleAdd2 * 0.5F - 0.1745F;

        boolean isSprinting = entity != null && entity.isSprinting();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isSitting = entity != null && (entity.getIsSitting() || entity.isPassenger());

        if (isSprinting || limbSwingAmount > 0.95F) {
            this.poseTranslateY += 0.05F;
            this.Head.xRot -= 0.4F;
            this.BodyMain.xRot = 0.7F;
            this.Butt.xRot -= 0.7F;
            this.Skirt01.xRot = -0.15F;
            this.Skirt02.xRot = -0.32F;
            this.Hair01.xRot += 0.3F;
            this.ArmLeft01.xRot = 0.4F;
            this.ArmLeft01.yRot = -0.5F;
            this.ArmLeft01.zRot = -0.7F;
            this.ArmRight01.xRot = 0.4F;
            this.ArmRight01.yRot = 0.5F;
            this.ArmRight01.zRot = 0.7F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 1.0472F;
            this.BodyMain.xRot = 1.0472F;
            this.Butt.xRot = -0.4F;
            this.Skirt01.xRot = -0.12F;
            this.Skirt02.xRot = -0.16F;
            this.Skirt02.y = this.skirt02DefaultY + (-0.1F * OFFSET_SCALE);
            this.Hair02.xRot -= 0.3F;
            this.Hair03.xRot -= 0.3F;

            this.ArmLeft01.xRot = -0.6F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.6F;
            this.ArmRight01.zRot = -0.2618F;

            addk1 -= 0.4F;
            addk2 -= 0.4F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (entity != null && entity.getStateEmotion(1) == 4) {
                this.poseTranslateY += 0.65F * 3;
                this.Head.xRot = -1.2217F;
                this.Head.yRot = 0.0F;
                this.Head.zRot = 0.0F;
                this.BodyMain.xRot = 1.2217F;
                this.Hair02.xRot += 0.2F;
                this.Hair03.xRot += 0.2F;
                this.Hair05.xRot -= 0.6F;
                this.Hair06.xRot -= 0.5F;
                this.ArmLeft01.xRot = -2.0F;
                this.ArmLeft01.yRot = -0.1F;
                this.ArmLeft01.zRot = -0.1F;
                this.ArmLeft02.xRot = -2.5F;
                this.ArmLeft02.y = this.armLeft02DefaultY + (0.1F * OFFSET_SCALE);
                this.ArmLeft02.z = this.armLeft02DefaultZ + (-0.3F * OFFSET_SCALE);
                this.ArmRight01.xRot = -2.0F;
                this.ArmRight01.yRot = 0.1F;
                this.ArmRight01.zRot = 0.1F;
                this.ArmRight02.xRot = -2.5F;
                this.ArmRight02.y = this.armRight02DefaultY + (0.1F * OFFSET_SCALE);
                this.ArmRight02.z = this.armRight02DefaultZ + (-0.3F * OFFSET_SCALE);
                addk1 = 0.0F;
                addk2 = 0.0F;
                this.LegLeft02.xRot = angleX * 0.4F + 0.8F;
                this.LegRight02.xRot = -angleX * 0.4F + 0.8F;
                this.GlowBodyMain2.visible = false;
            } else {
                this.poseTranslateY += 0.51F * 3;
                this.Head.yRot = -0.4F;
                this.Head.zRot = 0.2F;
                this.BodyMain.xRot = -0.25F;
                this.Butt.xRot = -0.2F;
                this.Skirt01.xRot = -0.13F;
                this.Skirt01.y = this.skirt01DefaultY + (-0.05F * OFFSET_SCALE);
                this.Skirt02.xRot = -0.13F;
                this.Skirt02.y = this.skirt02DefaultY + (-0.05F * OFFSET_SCALE);
                this.ArmLeft01.xRot = 0.35F;
                this.ArmLeft01.zRot = -0.2618F;
                this.ArmRight01.xRot = -0.4F;
                this.ArmRight01.zRot = 0.4F;
                addk1 = -0.9F;
                addk2 = -0.9F;
                this.LegLeft01.zRot = -0.14F;
                this.LegLeft02.xRot = 1.2217F;
                this.LegLeft02.yRot = 1.2217F;
                this.LegLeft02.zRot = -1.0472F;
                this.LegLeft02.x = this.legLeft02DefaultX + (0.22F * OFFSET_SCALE);
                this.LegLeft02.y = this.legLeft02DefaultY + (-0.03F * OFFSET_SCALE);
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.2F * OFFSET_SCALE);
                this.LegRight01.zRot = 0.14F;
                this.LegRight02.xRot = 1.2217F;
                this.LegRight02.yRot = -1.2217F;
                this.LegRight02.zRot = 1.0472F;
                this.LegRight02.x = this.legRight02DefaultX + (-0.22F * OFFSET_SCALE);
                this.LegRight02.y = this.legRight02DefaultY + (-0.03F * OFFSET_SCALE);
                this.LegRight02.z = this.legRight02DefaultZ + (0.2F * OFFSET_SCALE);
                this.EquipSL01.xRot -= 0.06F;
                this.EquipSL01.zRot -= 1.2F;
                this.EquipSR01.xRot -= 1.2F;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            int attackTick = entity.getAttackTick();
            if (attackTick > 41) {
                float ft = (50 - attackTick) + (ageInTicks - (int) ageInTicks);
                float fa = net.minecraft.util.Mth.cos((ft *= 0.125F) * ft * (float) Math.PI);
                float fb = net.minecraft.util.Mth.cos(net.minecraft.util.Mth.sqrt(ft) * (float) Math.PI);
                this.ArmLeft01.xRot += -fb * 120.0F * ((float) Math.PI / 180F) - 1.5F;
                this.ArmLeft01.yRot += fa * 20.0F * ((float) Math.PI / 180F);
                this.ArmLeft01.zRot += fb * 20.0F * ((float) Math.PI / 180F) + 0.26F;
            }
            if (attackTick > 36 && attackTick < 45) {
                float ft = (45 - attackTick) + (ageInTicks - (int) ageInTicks);
                float fa = net.minecraft.util.Mth.cos((ft *= 0.125F) * ft * (float) Math.PI);
                float fb = net.minecraft.util.Mth.cos(net.minecraft.util.Mth.sqrt(ft) * (float) Math.PI);
                this.ArmRight01.xRot += -fb * 120.0F * ((float) Math.PI / 180F) - 1.5F;
                this.ArmRight01.yRot += -fa * 20.0F * ((float) Math.PI / 180F);
                this.ArmRight01.zRot += -fb * 20.0F * ((float) Math.PI / 180F) - 0.26F;
            }
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot += -f8 * 95.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.xRot += headX;
        this.Hair01.zRot += headZ;
        this.Hair02.xRot += headX * 0.5F;
        this.Hair02.zRot += headZ * 0.5F;
        this.Hair03.xRot += headX * 0.5F;
        this.Hair03.zRot += headZ * 0.5F;
        this.Hair05.xRot += headX;
        this.Hair05.zRot += headZ;
        this.Hair06.xRot += headX;
        this.Hair06.zRot += headZ;
        this.HairL01.zRot += headZ;
        this.HairL02.zRot += headZ;
        this.HairR01.zRot += headZ;
        this.HairR02.zRot += headZ;
        this.HairL01.xRot += headX;
        this.HairL02.xRot += headX;
        this.HairR01.xRot += headX;
        this.HairR02.xRot += headX;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowBodyMain2.copyFrom(this.BodyMain);
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);
        this.GlowArmLeft01.copyFrom(this.ArmLeft01);
        this.GlowArmLeft02.copyFrom(this.ArmLeft02);
        this.GlowArmRight01.copyFrom(this.ArmRight01);
        this.GlowArmRight02.copyFrom(this.ArmRight02);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F || this.poseTranslateZ != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, this.poseTranslateZ);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

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

        this.GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.GlowBodyMain2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
