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


public class ModelCarrierWDemon<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "carrier_w_demon"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCarrierWDemon");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelCarrierWDemon");
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
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Cloth01;
    private final ModelPart EquipBase;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart HeadS04;
    private final ModelPart HeadS05;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart HeadS02;
    private final ModelPart HeadS03;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight02;
    private final ModelPart ShoesR01;
    private final ModelPart ShoesR02;
    private final ModelPart ShoesR03;
    private final ModelPart ShoesR04;
    private final ModelPart LegLeft02;
    private final ModelPart ShoesL01;
    private final ModelPart ShoesL02;
    private final ModelPart ShoesL03;
    private final ModelPart ShoesL04;
    private final ModelPart Skirt02;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart ArmLeft04;
    private final ModelPart ArmLeft05;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart ArmRight04;
    private final ModelPart ArmRight05;
    private final ModelPart EquipL01;
    private final ModelPart EquipR01;
    private final ModelPart EquipL02;
    private final ModelPart EquipL03;
    private final ModelPart EquipL04;
    private final ModelPart EquipL05;
    private final ModelPart EquipR02;
    private final ModelPart EquipR03;
    private final ModelPart EquipR04;
    private final ModelPart EquipR05;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowBodyMain2;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float equipL01DefaultX, equipL01DefaultY, equipL01DefaultZ;
    private final float equipR01DefaultX, equipR01DefaultY, equipR01DefaultZ;
    private final float legLeft02DefaultX, legLeft02DefaultY, legLeft02DefaultZ;
    private final float legRight02DefaultX, legRight02DefaultY, legRight02DefaultZ;

    public ModelCarrierWDemon(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.ArmRight04 = this.ArmRight03.getChild("ArmRight04");
        this.ArmRight05 = this.ArmRight04.getChild("ArmRight05");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.ArmLeft04 = this.ArmLeft03.getChild("ArmLeft04");
        this.ArmLeft05 = this.ArmLeft04.getChild("ArmLeft05");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoesL01 = this.LegLeft02.getChild("ShoesL01");
        this.ShoesL02 = this.ShoesL01.getChild("ShoesL02");
        this.ShoesL03 = this.ShoesL02.getChild("ShoesL03");
        this.ShoesL04 = this.ShoesL03.getChild("ShoesL04");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoesR01 = this.LegRight02.getChild("ShoesR01");
        this.ShoesR02 = this.ShoesR01.getChild("ShoesR02");
        this.ShoesR03 = this.ShoesR02.getChild("ShoesR03");
        this.ShoesR04 = this.ShoesR03.getChild("ShoesR04");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HeadS05 = this.Head.getChild("HeadS05");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.HeadS04 = this.Head.getChild("HeadS04");
        this.HeadS02 = root.getChild("HeadS02");
        this.HeadS03 = root.getChild("HeadS03");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.GlowBodyMain2 = root.getChild("GlowBodyMain2");
        this.EquipBase = this.GlowBodyMain2.getChild("EquipBase");
        this.EquipL01 = this.EquipBase.getChild("EquipL01");
        this.EquipL02 = this.EquipL01.getChild("EquipL02");
        this.EquipL03 = this.EquipL02.getChild("EquipL03");
        this.EquipL04 = this.EquipL03.getChild("EquipL04");
        this.EquipL05 = this.EquipL04.getChild("EquipL05");
        this.EquipR01 = this.EquipBase.getChild("EquipR01");
        this.EquipR02 = this.EquipR01.getChild("EquipR02");
        this.EquipR03 = this.EquipR02.getChild("EquipR03");
        this.EquipR04 = this.EquipR03.getChild("EquipR04");
        this.EquipR05 = this.EquipR04.getChild("EquipR05");
        this.equipL01DefaultX = this.EquipL01.x;
        this.equipL01DefaultY = this.EquipL01.y;
        this.equipL01DefaultZ = this.EquipL01.z;
        this.equipR01DefaultX = this.EquipR01.x;
        this.equipR01DefaultY = this.EquipR01.y;
        this.equipR01DefaultZ = this.EquipR01.z;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(0, 9).addBox(-3.5F, 0F, 0F, 6F, 7F, 0F, new CubeDeformation(0F)), PartPose.offset(0.5F, -4.7F, -6.3F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 85).addBox(-3F, -1F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(-7.8F, -9.3F, -0.7F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(25, 85).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.5F, 3F, 0F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(100, 14).addBox(-1.5F, 0F, -6.5F, 7F, 8F, 7F, new CubeDeformation(0F)), PartPose.offset(-2F, 10F, 3F));

        PartDefinition ArmRight04 = ArmRight03.addOrReplaceChild("ArmRight04", CubeListBuilder.create().texOffs(54, 49).addBox(-4F, 0F, -4F, 8F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 8F, -3F, 0F, 0.0873F, 0F));

        PartDefinition ArmRight05 = ArmRight04.addOrReplaceChild("ArmRight05", CubeListBuilder.create().texOffs(72, 36).addBox(-2.5F, 0F, -3F, 5F, 5F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.5F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 85).mirror().addBox(-2F, -1F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(7.8F, -9.3F, -0.7F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(25, 85).mirror().addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0.5F, 3F, 0F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(100, 14).mirror().addBox(-5.5F, 0F, -6.5F, 7F, 8F, 7F, new CubeDeformation(0F)), PartPose.offset(2F, 10F, 3F));

        PartDefinition ArmLeft04 = ArmLeft03.addOrReplaceChild("ArmLeft04", CubeListBuilder.create().texOffs(54, 49).mirror().addBox(-4F, 0F, -4F, 8F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 8F, -3F, 0F, -0.0873F, 0F));

        PartDefinition ArmLeft05 = ArmLeft04.addOrReplaceChild("ArmLeft05", CubeListBuilder.create().texOffs(72, 36).mirror().addBox(-2.5F, 0F, -3F, 5F, 5F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.5F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(52, 61).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 1.3F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offset(4.8F, 5.5F, -2.6F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 95).mirror().addBox(-3F, 0F, 0F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesL01 = LegLeft02.addOrReplaceChild("ShoesL01", CubeListBuilder.create().texOffs(99, 1).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 2.6F, 0.2618F, 0F, 0F));

        PartDefinition ShoesL02 = ShoesL01.addOrReplaceChild("ShoesL02", CubeListBuilder.create().texOffs(98, 0).mirror().addBox(-3.5F, 0F, -4F, 7F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, -0.7F));

        PartDefinition ShoesL03 = ShoesL02.addOrReplaceChild("ShoesL03", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(-4F, 0F, -4F, 8F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, -0.9F));

        PartDefinition ShoesL04 = ShoesL03.addOrReplaceChild("ShoesL04", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -1F, -0.12F, 0F, 0F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(0, 22).addBox(-8.5F, 0F, -6F, 17F, 4F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 2.9F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(42, 47).addBox(-9F, 0F, -6F, 18F, 4F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 2.8F, -0.5F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offset(-4.8F, 5.5F, -2.6F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 95).addBox(-3F, 0F, 0F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesR01 = LegRight02.addOrReplaceChild("ShoesR01", CubeListBuilder.create().texOffs(99, 1).addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 2.6F, 0.2618F, 0F, 0F));

        PartDefinition ShoesR02 = ShoesR01.addOrReplaceChild("ShoesR02", CubeListBuilder.create().texOffs(98, 0).addBox(-3.5F, 0F, -4F, 7F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, -0.7F));

        PartDefinition ShoesR03 = ShoesR02.addOrReplaceChild("ShoesR03", CubeListBuilder.create().texOffs(66, 0).addBox(-4F, 0F, -4F, 8F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, -0.9F));

        PartDefinition ShoesR04 = ShoesR03.addOrReplaceChild("ShoesR04", CubeListBuilder.create().texOffs(32, 0).addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -1F, -0.12F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.7F, -8.1F, -3.7F, -0.6981F, 0.1396F, 0.0873F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.7F, -8.1F, -3.7F, -0.6981F, -0.1396F, -0.0873F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(88, 29).addBox(-5.5F, -2F, -5F, 11F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, -10.3F, -0.2F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.2F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -4F, -11.5F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, -5.5F, 0F, 0.6981F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(86, 101).mirror().addBox(-1F, 0F, 0F, 2F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 3F, -5.5F, -0.1396F, 0.1745F, 0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(86, 101).mirror().addBox(-1F, 0F, 0F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 7F, 0F, 0.1745F, 0F, -0.0524F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(86, 101).addBox(-1F, 0F, 0F, 2F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 3F, -5.5F, -0.1396F, -0.1745F, -0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(86, 101).addBox(-1F, 0F, 0F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.1745F, 0F, 0.0873F));

        PartDefinition HeadS05 = Head.addOrReplaceChild("HeadS05", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8.1F, -7.5F, -6.7F, 0.7854F, 0F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(0, 57).addBox(-7.5F, 0F, 0F, 15F, 12F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 57).addBox(-7.5F, 0F, 0F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2094F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 58).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, -0.0873F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(0, 35).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -0.1F, -0.1396F, 0F, 0F));

        PartDefinition HeadS04 = Head.addOrReplaceChild("HeadS04", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.1F, -7.5F, -6.7F, 0.7854F, 0F, 0F));

        PartDefinition HeadS02 = partdefinition.addOrReplaceChild("HeadS02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 3F, 3F, 0F, new CubeDeformation(0F)), PartPose.offset(2.9F, -2.5F, 0F));

        PartDefinition HeadS03 = partdefinition.addOrReplaceChild("HeadS03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 3F, 3F, 0F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 2.9F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -10.3F, -0.2F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, 0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowBodyMain2 = partdefinition.addOrReplaceChild("GlowBodyMain2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition EquipBase = GlowBodyMain2.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -23F, 0F));

        PartDefinition EquipL01 = EquipBase.addOrReplaceChild("EquipL01", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, -3.5F, 2F, 2F, 7F, new CubeDeformation(0F)), PartPose.offset(30F, 0F, 0F));

        PartDefinition EquipL02 = EquipL01.addOrReplaceChild("EquipL02", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -10F, 3F, 2F, 20F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition EquipL03 = EquipL02.addOrReplaceChild("EquipL03", CubeListBuilder.create().texOffs(43, 0).mirror().addBox(0F, 0F, -8.5F, 2F, 9F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition EquipL04 = EquipL03.addOrReplaceChild("EquipL04", CubeListBuilder.create().texOffs(67, 14).mirror().addBox(0F, 0F, -6.5F, 2F, 9F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

        PartDefinition EquipL05 = EquipL04.addOrReplaceChild("EquipL05", CubeListBuilder.create().texOffs(46, 29).mirror().addBox(0F, 0F, -4.5F, 2F, 9F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

        PartDefinition EquipR01 = EquipBase.addOrReplaceChild("EquipR01", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -3.5F, 2F, 2F, 7F, new CubeDeformation(0F)), PartPose.offset(-30F, 0F, 0F));

        PartDefinition EquipR02 = EquipR01.addOrReplaceChild("EquipR02", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -10F, 3F, 2F, 20F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition EquipR03 = EquipR02.addOrReplaceChild("EquipR03", CubeListBuilder.create().texOffs(43, 0).addBox(-2F, 0F, -8.5F, 2F, 9F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition EquipR04 = EquipR03.addOrReplaceChild("EquipR04", CubeListBuilder.create().texOffs(67, 14).addBox(-2F, 0F, -6.5F, 2F, 9F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

        PartDefinition EquipR05 = EquipR04.addOrReplaceChild("EquipR05", CubeListBuilder.create().texOffs(46, 29).addBox(-2F, 0F, -4.5F, 2F, 9F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

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
        this.EquipL01.x = this.equipL01DefaultX;
        this.EquipL01.y = this.equipL01DefaultY;
        this.EquipL01.z = this.equipL01DefaultZ;
        this.EquipR01.x = this.equipR01DefaultX;
        this.EquipR01.y = this.equipR01DefaultY;
        this.EquipR01.z = this.equipR01DefaultZ;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
    }

    private void applyEquipVisibility(T entity) {
        this.EquipBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierWDemon.EQUIP_RIGGING);
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.55F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.BoobL.xRot = -0.7F;
        this.BoobR.xRot = -0.7F;
        this.Ahoke.yRot = 0.7F;
        this.BodyMain.xRot = -0.1047F;
        this.Hair01.xRot = -0.1F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -0.2F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -0.14F;
        this.Hair03.zRot = 0.0F;
        this.HairL01.zRot = 0.087F;
        this.HairL02.zRot = 0.087F;
        this.HairR01.zRot = 0.087F;
        this.HairR02.zRot = -0.052F;
        this.HairL01.xRot = -0.65F;
        this.HairL02.xRot = 0.17F;
        this.HairR01.xRot = -0.65F;
        this.HairR02.xRot = 0.17F;

        this.Neck.xRot = 0.3F;
        this.Butt.xRot = -0.14F;
        this.Skirt01.xRot = -0.1745F;
        this.Skirt02.xRot = -0.2618F;

        this.ArmLeft01.xRot = 0.4F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -0.2618F;
        this.ArmLeft03.xRot = 0.0F;
        this.ArmLeft03.zRot = 0.0F;
        this.ArmLeft05.zRot = 0.2618F;

        this.ArmRight01.xRot = 0.4F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = 0.2618F;
        this.ArmRight03.xRot = 0.0F;
        this.ArmRight03.zRot = 0.0F;

        this.LegLeft01.xRot = -1.0472F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = -0.14F;
        this.LegLeft02.xRot = 1.2217F;
        this.LegLeft02.yRot = 1.2217F;
        this.LegLeft02.zRot = -1.0472F;
        this.LegLeft02.x = this.legLeft02DefaultX + (0.175F * OFFSET_SCALE);
        this.LegLeft02.y = this.legLeft02DefaultY + (-0.02F * OFFSET_SCALE);
        this.LegLeft02.z = this.legLeft02DefaultZ + (0.1635F * OFFSET_SCALE);
        this.ShoesL04.xRot = -0.1F;

        this.LegRight01.xRot = -1.0472F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.14F;
        this.LegRight02.xRot = 1.2217F;
        this.LegRight02.yRot = -1.2217F;
        this.LegRight02.zRot = 1.0472F;
        this.LegRight02.x = this.legRight02DefaultX + (-0.175F * OFFSET_SCALE);
        this.LegRight02.y = this.legRight02DefaultY + (-0.05F * OFFSET_SCALE);
        this.LegRight02.z = this.legRight02DefaultZ + (0.1635F * OFFSET_SCALE);

        this.EquipBase.xRot = 0.0F;
        this.EquipL01.xRot = 0.2618F;
        this.EquipL01.yRot = 0.1745F;
        this.EquipL01.zRot = 0.0F;
        this.EquipL01.x = this.equipL01DefaultX;
        this.EquipL01.y = this.equipL01DefaultY + (0.6F * OFFSET_SCALE);
        this.EquipL01.z = this.equipL01DefaultZ;
        this.EquipL05.zRot = 0.0F;

        this.EquipR01.xRot = 0.2618F;
        this.EquipR01.yRot = -0.1745F;
        this.EquipR01.zRot = 0.0F;
        this.EquipR01.x = this.equipR01DefaultX;
        this.EquipR01.y = this.equipR01DefaultY + (0.6F * OFFSET_SCALE);
        this.EquipR01.z = this.equipR01DefaultZ;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.7F;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY += angleX * 0.05F + 0.025F;
        }

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) * 0.8F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.57F;
        this.Head.zRot = 0.0F;

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;

        this.BoobL.xRot = angleX * 0.08F - 0.7F;
        this.BoobR.xRot = angleX * 0.08F - 0.7F;
        this.Ahoke.yRot = angleX * 0.15F + 0.7F;
        this.Neck.xRot = 0.1F;
        this.BodyMain.xRot = -0.1047F;
        this.Butt.xRot = 0.3142F;
        this.Skirt01.xRot = -0.14F;
        this.Skirt02.xRot = -0.087F;

        this.Hair01.xRot = angleX * 0.03F + 0.21F + headX;
        this.Hair01.zRot = headZ;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.09F + headX;
        this.Hair02.zRot = headZ;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.14F;
        this.Hair03.zRot = 0.0F;

        this.HairL01.zRot = headZ - 0.087F;
        this.HairL02.zRot = headZ + 0.087F;
        this.HairR01.zRot = headZ + 0.087F;
        this.HairR02.zRot = headZ - 0.052F;
        this.HairL01.xRot = angleX * 0.02F + headX - 0.14F;
        this.HairL02.xRot = angleX * 0.02F + headX + 0.17F;
        this.HairR01.xRot = angleX * 0.02F + headX - 0.14F;
        this.HairR02.xRot = angleX * 0.02F + headX + 0.17F;

        this.ArmLeft01.xRot = 0.2618F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -0.7F;
        this.ArmLeft03.xRot = -0.14F;
        this.ArmLeft03.zRot = 1.4835F;
        this.ArmLeft05.zRot = 0.2618F;
        this.ArmRight01.xRot = angleAdd1 * 0.375F + 0.2618F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = 0.2618F;
        this.ArmRight03.xRot = 0.0F;
        this.ArmRight03.zRot = 0.0F;

        this.LegLeft01.xRot = angleAdd1 * 0.6F - 0.35F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.14F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.ShoesL04.xRot = -0.1F;

        this.LegRight01.xRot = angleAdd2 * 0.6F - 0.07F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.14F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.EquipBase.xRot = 0.0F;
        this.EquipL01.xRot = 0.2618F;
        this.EquipL01.yRot = 0.1745F;
        this.EquipL01.zRot = 0.0F;
        this.EquipL01.y = this.equipL01DefaultY + (angleX * 0.125F * OFFSET_SCALE);
        this.EquipL05.zRot = 0.0F;

        this.EquipR01.xRot = 0.2618F;
        this.EquipR01.yRot = -0.1745F;
        this.EquipR01.zRot = 0.0F;
        this.EquipR01.y = this.equipR01DefaultY + (-angleX * 0.125F * OFFSET_SCALE);
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.7F;

        float addk1 = angleAdd1 * 0.6F - 0.35F;
        float addk2 = angleAdd2 * 0.6F - 0.07F;

        boolean isSprinting = entity != null && entity.isSprinting();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isSitting = entity != null && (entity.getIsSitting() || entity.isPassenger());

        if (isSprinting || limbSwingAmount > 0.9F) {
            this.Hair01.xRot += 0.09F;
            this.Hair02.xRot += 0.43F;
            this.Hair03.xRot += 0.49F;
            this.BoobL.xRot = angleAdd2 * 0.1F - 0.83F;
            this.BoobR.xRot = angleAdd1 * 0.1F - 0.83F;
            this.ArmLeft01.xRot = angleAdd2 * 0.6F + 0.2618F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = -0.3F;
            this.ArmLeft03.xRot = 0.0F;
            this.ArmLeft03.zRot = 0.0F;
            this.ArmLeft05.zRot = 0.0F;
            this.ArmRight01.xRot = angleAdd1 * 0.6F + 0.2618F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = 0.3F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.Butt.xRot = -0.6283F;
            this.Skirt01.xRot = -0.1745F;
            this.Skirt02.xRot = -0.2618F;
            this.ArmLeft01.xRot = 0.2618F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmLeft03.xRot = 0.0F;
            this.ArmLeft03.zRot = 0.0F;
            this.ArmLeft05.zRot = 0.0F;
            this.ArmRight01.xRot = 0.2618F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = -0.2618F;
            this.ArmRight05.zRot = 0.0F;
            this.Hair01.xRot += 0.37F;
            this.Hair02.xRot += 0.23F;
            this.Hair03.xRot -= 0.1F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (entity != null && entity.getStateEmotion(1) == 4) {
                this.poseTranslateY += 0.48F * 3;
                int nodTick = (int) ageInTicks % 60;
                this.Head.xRot = 0.2F;
                if (nodTick < 30) {
                    if (nodTick < 6) {
                        this.Head.xRot = nodTick * 0.02F + 0.2F;
                    } else if (nodTick < 11) {
                        this.Head.xRot = (nodTick - 5) * 0.03F + 0.3F;
                    } else if (nodTick < 14) {
                        this.Head.xRot = (nodTick - 10) * -0.09F + 0.45F;
                    }
                }
                this.Head.yRot = 0.0F;
                this.Head.zRot = 0.0F;
                float headX = this.Head.xRot * -0.5F;
                this.Hair01.xRot = angleX * 0.012F + 0.21F + headX;
                this.Hair02.xRot = angleX * 0.015F - 0.09F + headX;
                this.Neck.xRot = 0.3F;
                this.Butt.xRot = -0.14F;
                this.Skirt01.xRot = -0.1745F;
                this.Skirt02.xRot = -0.2618F;
                this.ArmLeft01.xRot = 0.4F;
                this.ArmLeft01.zRot = -0.2618F;
                this.ArmLeft03.xRot = 0.0F;
                this.ArmLeft03.zRot = 0.0F;
                this.ArmRight01.xRot = 0.4F;
                this.ArmRight01.zRot = 0.2618F;
                this.LegLeft01.zRot = -0.14F;
                this.LegLeft02.xRot = 1.2217F;
                this.LegLeft02.yRot = 1.2217F;
                this.LegLeft02.zRot = -1.0472F;
                this.LegLeft02.x = this.legLeft02DefaultX + (0.175F * OFFSET_SCALE);
                this.LegLeft02.y = this.legLeft02DefaultY + (-0.02F * OFFSET_SCALE);
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.1635F * OFFSET_SCALE);
                this.LegRight01.zRot = 0.14F;
                this.LegRight02.xRot = 1.2217F;
                this.LegRight02.yRot = -1.2217F;
                this.LegRight02.zRot = 1.0472F;
                this.LegRight02.x = this.legRight02DefaultX + (-0.175F * OFFSET_SCALE);
                this.LegRight02.y = this.legRight02DefaultY + (-0.05F * OFFSET_SCALE);
                this.LegRight02.z = this.legRight02DefaultZ + (0.1635F * OFFSET_SCALE);
                addk1 = -1.0472F;
                addk2 = -1.0472F;
                this.LegLeft01.xRot = addk1;
                this.LegRight01.xRot = addk2;
                this.EquipL01.y = this.equipL01DefaultY + (0.6F * OFFSET_SCALE);
                this.EquipR01.y = this.equipR01DefaultY + (0.6F * OFFSET_SCALE);
            } else {
                this.poseTranslateY += 0.39F * 3;
                this.Neck.xRot = 0.35F;
                this.BodyMain.xRot = -0.6283F;
                this.Butt.xRot = -0.6283F;
                this.Skirt01.xRot = -0.1745F;
                this.Skirt02.xRot = -0.2618F;
                this.ArmRight01.xRot = angleX * 0.125F + 0.5236F;
                this.ArmRight01.zRot = 0.45F;
                this.ArmRight03.xRot = -0.5F;
                addk1 = -0.8727F;
                addk2 = -0.35F;
                this.LegLeft01.xRot = addk1;
                this.LegRight01.xRot = addk2;
                this.LegLeft01.zRot = 0.4363F;
                this.LegLeft02.xRot = 0.7854F;
                this.LegRight01.zRot = -0.35F;
                this.LegRight02.xRot = 0.8727F;
                this.ShoesL04.xRot = angleX * 0.25F - 0.1F;
                this.EquipL01.x = this.equipL01DefaultX + (-1.9F * OFFSET_SCALE);
                this.EquipL01.y = this.equipL01DefaultY + (0.6F * OFFSET_SCALE);
                this.EquipL01.z = this.equipL01DefaultZ + (0.4F * OFFSET_SCALE);
                this.EquipL01.xRot = 0.0F;
                this.EquipL01.yRot = 1.57F;
                this.EquipL05.zRot = -1.0F;
                this.EquipR01.x = this.equipR01DefaultX + (1.9F * OFFSET_SCALE);
                this.EquipR01.y = this.equipR01DefaultY + (-1.0F * OFFSET_SCALE);
                this.EquipR01.z = this.equipR01DefaultZ + (-0.4F * OFFSET_SCALE);
                this.EquipR01.xRot = -1.5708F;
                this.EquipR01.yRot = 0.6F;
                this.EquipR01.zRot = -1.5708F;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            if (isPassenger) {
                this.ArmRight01.xRot = -1.1F;
                this.ArmRight03.xRot = 0.0F;
                this.EquipBase.visible = true;
                this.EquipBase.xRot = -1.2F + this.Head.xRot;
                this.EquipL01.xRot = -0.1F;
                this.EquipR01.xRot = 0.1F;
            } else {
                this.ArmRight01.xRot = -1.5F;
                this.EquipBase.visible = true;
                this.EquipBase.xRot = -1.6F + this.Head.xRot;
                this.EquipL01.y = this.equipL01DefaultY;
                this.EquipR01.y = this.equipR01DefaultY;
            }
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot = -0.4F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = -0.2F;
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

		/*
		if (entity != null && entity.getAttackTick() > 0) {

		}
		if (entity != null && entity.getSwingTime(ageInTicks) != 0.0f) {

		}
		*/
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowBodyMain2.copyFrom(this.BodyMain);
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F || this.poseTranslateZ != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, this.poseTranslateZ);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.HeadS02.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.HeadS03.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);
        this.GlowBodyMain2.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
