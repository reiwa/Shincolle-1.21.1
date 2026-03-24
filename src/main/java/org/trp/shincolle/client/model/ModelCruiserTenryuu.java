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

public class ModelCruiserTenryuu<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "cruiser_tenryuu"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCruiserTenryuu");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelCruiserTenryuu");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelCruiserTenryuu");
    private static final float SITTING_ALT_TRANSLATE_Y = LegacyPoseOffsets.sittingAltY("ModelCruiserTenryuu");

    private static final float BODY_BASE_X_ROT = -0.1047F;
    private static final float BUTT_BASE_X_ROT = 0.35F;
    private static final float SKIRT_BASE_X_ROT = -0.14F;
    private static final float SKIRT02_BASE_X_ROT = -0.09F;
    private static final float LEG_BASE_Z_ROT = 0.0873F;

    private boolean isSittingPose;
    private boolean isDeadPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft01;
    private final ModelPart Cloth01;
    private final ModelPart EquipSR01;
    private final ModelPart Equip00;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart EarL01;
    private final ModelPart EarR01;
    private final ModelPart EyeMask;
    private final ModelPart HairU01;
    private final ModelPart Ahoke;
    private final ModelPart Hair01;
    private final ModelPart EarL02;
    private final ModelPart EarL03;
    private final ModelPart EarL04;
    private final ModelPart EarR02;
    private final ModelPart EarR03;
    private final ModelPart EarR04;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft02;
    private final ModelPart ShoeL01;
    private final ModelPart ShoeL00;
    private final ModelPart ShoeL02;
    private final ModelPart Skirt02;
    private final ModelPart LegRight02;
    private final ModelPart ShoeR01;
    private final ModelPart ShoeR00;
    private final ModelPart ShoeR02;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight02a;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft02a;
    private final ModelPart EquipSL00;
    private final ModelPart EquipSL00a;
    private final ModelPart EquipSL00b;
    private final ModelPart EquipSL01;
    private final ModelPart EquipSL02;
    private final ModelPart EquipSL02a;
    private final ModelPart EquipSL03;
    private final ModelPart EquipSL03a;
    private final ModelPart EquipSR02;
    private final ModelPart EquipSR03;
    private final ModelPart Equip01a;
    private final ModelPart Equip01b;
    private final ModelPart Equip01c;
    private final ModelPart Equip02a;
    private final ModelPart Equip01d;
    private final ModelPart Equip03L;
    private final ModelPart Equip03R;
    private final ModelPart EquipCL01;
    private final ModelPart EquipCL02;
    private final ModelPart EquipCL03;
    private final ModelPart EquipCL04;
    private final ModelPart EquipCL05;
    private final ModelPart EquipCR01;
    private final ModelPart EquipCR02;
    private final ModelPart EquipCR03;
    private final ModelPart EquipCR04;
    private final ModelPart EquipCR05;
    private final ModelPart Equip02b;
    private final ModelPart Equip02c;
    private final ModelPart Equip02d;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowEquip00;
    private final ModelPart GlowEquip01a;
    private final ModelPart GlowEquip02a;
    private final float buttDefaultY;
    private final float skirt01DefaultY;
    private final float skirt02DefaultY;
    private final float hair01DefaultY;
    private final float legLeft02DefaultX;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultX;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultY;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultY;
    private final float armRight02DefaultZ;

    public ModelCruiserTenryuu(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight02a = this.ArmRight02.getChild("ArmRight02a");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.EquipSL00 = this.ArmLeft02.getChild("EquipSL00");
        this.EquipSL00b = this.EquipSL00.getChild("EquipSL00b");
        this.EquipSL01 = this.EquipSL00.getChild("EquipSL01");
        this.EquipSL02 = this.EquipSL01.getChild("EquipSL02");
        this.EquipSL03 = this.EquipSL02.getChild("EquipSL03");
        this.EquipSL03a = this.EquipSL03.getChild("EquipSL03a");
        this.EquipSL02a = this.EquipSL02.getChild("EquipSL02a");
        this.EquipSL00a = this.EquipSL00.getChild("EquipSL00a");
        this.ArmLeft02a = this.ArmLeft02.getChild("ArmLeft02a");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.EyeMask = this.Head.getChild("EyeMask");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoeR01 = this.LegRight02.getChild("ShoeR01");
        this.ShoeR02 = this.ShoeR01.getChild("ShoeR02");
        this.ShoeR00 = this.LegRight02.getChild("ShoeR00");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoeL01 = this.LegLeft02.getChild("ShoeL01");
        this.ShoeL02 = this.ShoeL01.getChild("ShoeL02");
        this.ShoeL00 = this.LegLeft02.getChild("ShoeL00");
        this.EquipSR01 = this.BodyMain.getChild("EquipSR01");
        this.EquipSR02 = this.EquipSR01.getChild("EquipSR02");
        this.EquipSR03 = this.EquipSR02.getChild("EquipSR03");
        this.Equip00 = this.BodyMain.getChild("Equip00");
        this.Equip01a = this.Equip00.getChild("Equip01a");
        this.Equip01b = this.Equip01a.getChild("Equip01b");
        this.Equip01c = this.Equip01a.getChild("Equip01c");
        this.Equip01d = this.Equip01c.getChild("Equip01d");
        this.Equip03R = this.Equip01d.getChild("Equip03R");
        this.EquipCR01 = this.Equip03R.getChild("EquipCR01");
        this.EquipCR02 = this.EquipCR01.getChild("EquipCR02");
        this.EquipCR03 = this.EquipCR02.getChild("EquipCR03");
        this.EquipCR04 = this.EquipCR03.getChild("EquipCR04");
        this.EquipCR05 = this.EquipCR04.getChild("EquipCR05");
        this.Equip03L = this.Equip01d.getChild("Equip03L");
        this.EquipCL01 = this.Equip03L.getChild("EquipCL01");
        this.EquipCL02 = this.EquipCL01.getChild("EquipCL02");
        this.EquipCL03 = this.EquipCL02.getChild("EquipCL03");
        this.EquipCL04 = this.EquipCL03.getChild("EquipCL04");
        this.EquipCL05 = this.EquipCL04.getChild("EquipCL05");
        this.Equip02a = this.Equip01a.getChild("Equip02a");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.EarL01 = this.GlowHead.getChild("EarL01");
        this.EarL02 = this.EarL01.getChild("EarL02");
        this.EarL03 = this.EarL02.getChild("EarL03");
        this.EarL04 = this.EarL03.getChild("EarL04");
        this.EarR01 = this.GlowHead.getChild("EarR01");
        this.EarR02 = this.EarR01.getChild("EarR02");
        this.EarR03 = this.EarR02.getChild("EarR03");
        this.EarR04 = this.EarR03.getChild("EarR04");
        this.GlowEquip00 = this.GlowBodyMain.getChild("GlowEquip00");
        this.GlowEquip01a = this.GlowEquip00.getChild("GlowEquip01a");
        this.GlowEquip02a = this.GlowEquip01a.getChild("GlowEquip02a");
        this.Equip02b = this.GlowEquip02a.getChild("Equip02b");
        this.Equip02c = this.Equip02b.getChild("Equip02c");
        this.Equip02d = this.Equip02c.getChild("Equip02d");
        this.buttDefaultY = this.Butt.y;
        this.skirt01DefaultY = this.Skirt01.y;
        this.skirt02DefaultY = this.Skirt02.y;
        this.hair01DefaultY = this.Hair01.y;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultY = this.ArmLeft02.y;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultY = this.ArmRight02.y;
        this.armRight02DefaultZ = this.ArmRight02.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0F, 0F, 0.3491F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 63).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition ArmRight02a = ArmRight02.addOrReplaceChild("ArmRight02a", CubeListBuilder.create().texOffs(104, 33).mirror().addBox(-3F, 0F, -3F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 1.3F, -2.4F, 0.0698F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(16, 22).addBox(-4F, 0F, -4F, 8F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -11.7F, -0.2F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 84).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.1047F, 0F, -0.3491F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 63).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition EquipSL00 = ArmLeft02.addOrReplaceChild("EquipSL00", CubeListBuilder.create().texOffs(98, 27).addBox(0F, -4F, -0.5F, 2F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 9.3F, -3F, -1.5708F, -0.1396F, 1.5708F));

        PartDefinition EquipSL00b = EquipSL00.addOrReplaceChild("EquipSL00b", CubeListBuilder.create().texOffs(66, 40).addBox(0F, -2F, -0.5F, 3F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, -3.8F, 0F, 0F, 0F, 0.1396F));

        PartDefinition EquipSL01 = EquipSL00.addOrReplaceChild("EquipSL01", CubeListBuilder.create().texOffs(90, 0).addBox(-2.5F, 0F, -0.5F, 3F, 12F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.1F, 4.7F, 0F, 0F, 0F, 0.0698F));

        PartDefinition EquipSL02 = EquipSL01.addOrReplaceChild("EquipSL02", CubeListBuilder.create().texOffs(90, 0).addBox(-2.5F, 0F, -0.5F, 3F, 11F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11.9F, 0F, 0F, 0F, 0.1047F));

        PartDefinition EquipSL03 = EquipSL02.addOrReplaceChild("EquipSL03", CubeListBuilder.create().texOffs(90, 0).addBox(-2.5F, 0F, -0.5F, 3F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10.9F, 0F, 0F, 0F, 0.1396F));

        PartDefinition EquipSL03a = EquipSL03.addOrReplaceChild("EquipSL03a", CubeListBuilder.create().texOffs(46, 62).addBox(-2.5F, 0F, -0.5F, 2F, 11F, 1F, new CubeDeformation(0F)), PartPose.offset(-1.7F, -3F, -0.2F));

        PartDefinition EquipSL02a = EquipSL02.addOrReplaceChild("EquipSL02a", CubeListBuilder.create().texOffs(46, 62).mirror().addBox(0F, 0F, -0.5F, 2F, 11F, 1F, new CubeDeformation(0F)), PartPose.offset(-4.3F, -3F, 0F));

        PartDefinition EquipSL00a = EquipSL00.addOrReplaceChild("EquipSL00a", CubeListBuilder.create().texOffs(67, 35).addBox(0F, 0F, -1F, 4F, 1F, 2F, new CubeDeformation(0F)), PartPose.offset(-0.7F, 3.9F, 0F));

        PartDefinition ArmLeft02a = ArmLeft02.addOrReplaceChild("ArmLeft02a", CubeListBuilder.create().texOffs(104, 33).addBox(-3F, 0F, -3F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 1.3F, -2.4F, 0.0698F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(29, 12).addBox(-2.5F, -2F, -3.6F, 5F, 2F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(106, 31).addBox(0F, -6F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -7F, -6F, 0.2094F, 0.6981F, 0F));

        PartDefinition EyeMask = Head.addOrReplaceChild("EyeMask", CubeListBuilder.create().texOffs(114, 17).addBox(0F, 0F, 0F, 6F, 5F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.7F, -8.4F, -6.7F, 0F, 0F, 0.4363F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(46, 21).addBox(-7.5F, 0F, 0F, 15F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11.5F, 3.3F, 0.2618F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -8.1F, -3.7F, -0.6981F, 0.0873F, 0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 47).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.2094F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition ShoeR01 = LegRight02.addOrReplaceChild("ShoeR01", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(3F, 10.5F, 3F));

        PartDefinition ShoeR02 = ShoeR01.addOrReplaceChild("ShoeR02", CubeListBuilder.create().texOffs(74, 6).mirror().addBox(-0.5F, 0F, -10F, 1F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -2.5F, -0.1745F, 0F, 0F));

        PartDefinition ShoeR00 = LegRight02.addOrReplaceChild("ShoeR00", CubeListBuilder.create().texOffs(6, 5).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(3F, 4.2F, 3F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(46, 43).addBox(-8.5F, 0F, -6F, 17F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.9F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(0, 33).addBox(-9F, 0F, -6F, 18F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.8F, -0.5F, -0.0873F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 63).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition ShoeL01 = LegLeft02.addOrReplaceChild("ShoeL01", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 10.5F, 3F, 0.0873F, 0F, 0F));

        PartDefinition ShoeL02 = ShoeL01.addOrReplaceChild("ShoeL02", CubeListBuilder.create().texOffs(74, 6).addBox(-0.5F, 0F, -10F, 1F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -2.5F, -0.1745F, 0F, 0F));

        PartDefinition ShoeL00 = LegLeft02.addOrReplaceChild("ShoeL00", CubeListBuilder.create().texOffs(6, 5).addBox(-3.6F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(-3F, 4.2F, 3F));

        PartDefinition EquipSR01 = BodyMain.addOrReplaceChild("EquipSR01", CubeListBuilder.create().texOffs(118, 0).addBox(-1F, -2F, -1.5F, 2F, 12F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 5.5F, -5F, 1.309F, -0.1396F, -0.1396F));

        PartDefinition EquipSR02 = EquipSR01.addOrReplaceChild("EquipSR02", CubeListBuilder.create().texOffs(108, 0).addBox(-1F, 0F, -3F, 2F, 12F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 1.5F, -0.0524F, 0F, 0F));

        PartDefinition EquipSR03 = EquipSR02.addOrReplaceChild("EquipSR03", CubeListBuilder.create().texOffs(98, 0).addBox(-1F, 0F, -3F, 2F, 12F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, -0.0524F, 0F, 0F));

        PartDefinition Equip00 = BodyMain.addOrReplaceChild("Equip00", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -2F, 3F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 5F, 0.0873F, 0F, 0F));

        PartDefinition Equip01a = Equip00.addOrReplaceChild("Equip01a", CubeListBuilder.create().texOffs(28, 0).addBox(-3.5F, 0F, 0F, 7F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, 0F));

        PartDefinition Equip01b = Equip01a.addOrReplaceChild("Equip01b", CubeListBuilder.create().texOffs(52, 0).addBox(-5.5F, 0F, 0F, 11F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 0F));

        PartDefinition Equip01c = Equip01a.addOrReplaceChild("Equip01c", CubeListBuilder.create().texOffs(28, 0).addBox(-3.5F, 0F, 0F, 7F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 5F));

        PartDefinition Equip01d = Equip01c.addOrReplaceChild("Equip01d", CubeListBuilder.create().texOffs(52, 0).addBox(-5.5F, 0F, 0F, 11F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 0F));

        PartDefinition Equip03R = Equip01d.addOrReplaceChild("Equip03R", CubeListBuilder.create().texOffs(86, 104).mirror().addBox(-4F, 0F, 0F, 4F, 8F, 2F, new CubeDeformation(0F)), PartPose.offset(-5F, 1.5F, 4.5F));

        PartDefinition EquipCR01 = Equip03R.addOrReplaceChild("EquipCR01", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, -1F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-3.5F, 3.5F, 2F));

        PartDefinition EquipCR02 = EquipCR01.addOrReplaceChild("EquipCR02", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, -3F, -2F, 5F, 7F, 7F, new CubeDeformation(0F)), PartPose.offset(-1.9F, 0F, 0F));

        PartDefinition EquipCR03 = EquipCR02.addOrReplaceChild("EquipCR03", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-5F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, -1.5F));

        PartDefinition EquipCR04 = EquipCR03.addOrReplaceChild("EquipCR04", CubeListBuilder.create().texOffs(46, 36).mirror().addBox(-1.5F, -5.8F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 3F, 3F));

        PartDefinition EquipCR05 = EquipCR04.addOrReplaceChild("EquipCR05", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, -13.6F, -1F, 2F, 8F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Equip03L = Equip01d.addOrReplaceChild("Equip03L", CubeListBuilder.create().texOffs(86, 104).addBox(0F, 0F, 0F, 4F, 8F, 2F, new CubeDeformation(0F)), PartPose.offset(5F, 1.5F, 4.5F));

        PartDefinition EquipCL01 = Equip03L.addOrReplaceChild("EquipCL01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(3.5F, 3.5F, 2F));

        PartDefinition EquipCL02 = EquipCL01.addOrReplaceChild("EquipCL02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -3F, -2F, 5F, 7F, 7F, new CubeDeformation(0F)), PartPose.offset(1.9F, 0F, 0F));

        PartDefinition EquipCL03 = EquipCL02.addOrReplaceChild("EquipCL03", CubeListBuilder.create().texOffs(0, 18).addBox(0F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, -1.5F));

        PartDefinition EquipCL04 = EquipCL03.addOrReplaceChild("EquipCL04", CubeListBuilder.create().texOffs(46, 36).addBox(-1.5F, -5.8F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(2.5F, 3F, 3F));

        PartDefinition EquipCL05 = EquipCL04.addOrReplaceChild("EquipCL05", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -13.6F, -1F, 2F, 8F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Equip02a = Equip01a.addOrReplaceChild("Equip02a", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -0.4F, 10F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -8.1F, -3.7F, -0.6981F, -0.0873F, -0.0873F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition EarL01 = GlowHead.addOrReplaceChild("EarL01", CubeListBuilder.create().texOffs(43, 75).addBox(-1F, -2.5F, -2.5F, 2F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, -11F, 4F, 0.0873F, -0.1745F, -0.0873F));

        PartDefinition EarL02 = EarL01.addOrReplaceChild("EarL02", CubeListBuilder.create().texOffs(88, 41).addBox(0F, -4F, -3.5F, 2F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(-1F, -2.5F, -1F));

        PartDefinition EarL03 = EarL02.addOrReplaceChild("EarL03", CubeListBuilder.create().texOffs(88, 31).addBox(0F, -5F, 0F, 2F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, -3.2F));

        PartDefinition EarL04 = EarL03.addOrReplaceChild("EarL04", CubeListBuilder.create().texOffs(74, 34).addBox(0F, -4F, 0F, 2F, 4F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, 0.3F));

        PartDefinition EarR01 = GlowHead.addOrReplaceChild("EarR01", CubeListBuilder.create().texOffs(43, 75).mirror().addBox(-1F, -2.5F, -2.5F, 2F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, -11F, 4F, 0.0873F, 0.1745F, 0.0873F));

        PartDefinition EarR02 = EarR01.addOrReplaceChild("EarR02", CubeListBuilder.create().texOffs(88, 41).mirror().addBox(0F, -4F, -3.5F, 2F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(-1F, -2.5F, -1F));

        PartDefinition EarR03 = EarR02.addOrReplaceChild("EarR03", CubeListBuilder.create().texOffs(88, 31).mirror().addBox(0F, -5F, 0F, 2F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, -3.2F));

        PartDefinition EarR04 = EarR03.addOrReplaceChild("EarR04", CubeListBuilder.create().texOffs(74, 34).mirror().addBox(0F, -4F, 0F, 2F, 4F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, 0.3F));

        PartDefinition GlowEquip00 = GlowBodyMain.addOrReplaceChild("GlowEquip00", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 4F, 5F, 0.0873F, 0F, 0F));

        PartDefinition GlowEquip01a = GlowEquip00.addOrReplaceChild("GlowEquip01a", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -12F, 0F));

        PartDefinition GlowEquip02a = GlowEquip01a.addOrReplaceChild("GlowEquip02a", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -0.4F, 10F));

        PartDefinition Equip02b = GlowEquip02a.addOrReplaceChild("Equip02b", CubeListBuilder.create().texOffs(104, 23).addBox(-4F, 0F, 0F, 8F, 6F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 4F));

        PartDefinition Equip02c = Equip02b.addOrReplaceChild("Equip02c", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, 0F, 7F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, 4F));

        PartDefinition Equip02d = Equip02c.addOrReplaceChild("Equip02d", CubeListBuilder.create().texOffs(0, 28).addBox(-1F, 0F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        resetPoseState();
        resetOffsets();

        applyFaceAndMouth(entity);
        applyEquipVisibility(entity);

        if (isDeadPose(entity)) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);

        applyBasePose(ctx);
        applyEarAnimation(ageInTicks);
        applyEquipAnimation(entity, headPitch);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks, limbSwingAmount);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;

        boolean showEquip = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTenryuu.EQUIP_RIGGING);
        Equip00.visible = showEquip;
        if (GlowEquip00 != null) GlowEquip00.visible = showEquip;
        boolean showEars = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTenryuu.EQUIP_EARS);
        EarL01.visible = showEars;
        EarR01.visible = showEars;
        boolean showSideEquip = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTenryuu.EQUIP_SIDE);
        EquipSL00.visible = showSideEquip;
        EquipSR01.visible = showSideEquip;
        EyeMask.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTenryuu.EQUIP_MASK);
        boolean showShoes = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTenryuu.EQUIP_SHOES);
        ShoeL02.visible = showShoes;
        ShoeR02.visible = showShoes;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Ahoke.xRot = 0.2094F;
        Ahoke.yRot = 0.6981F;
        Ahoke.zRot = 0.0F;
        Head.xRot = 0.15F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        BodyMain.xRot = 1.7453F;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = -0.5236F;
        Butt.xRot = -0.7854F;
        Butt.yRot = 0.0F;
        Butt.zRot = 0.0F;
        Skirt01.xRot = 0.0F;
        Skirt02.xRot = -0.0873F;
        Skirt02.yRot = 0.0F;
        Skirt02.zRot = 0.0F;

        ArmLeft01.xRot = -1.3963F;
        ArmLeft01.yRot = -0.3491F;
        ArmLeft01.zRot = -0.1745F;
        ArmLeft02.xRot = -1.4835F;
        ArmLeft02.yRot = 0.0F;
        ArmLeft02.zRot = 0.0F;
        ArmLeft02.z = armLeft02DefaultZ + (-0.2F * OFFSET_SCALE);

        ArmRight01.xRot = -1.3090F;
        ArmRight01.yRot = -0.8727F;
        ArmRight01.zRot = 0.0F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.yRot = 0.0F;
        ArmRight02.zRot = -0.1745F;

        LegLeft01.xRot = -0.6981F;
        LegLeft01.yRot = -0.6981F;
        LegLeft01.zRot = -0.2618F;
        LegLeft02.xRot = 1.5708F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.xRot = 0.0F;
        LegRight01.yRot = -0.7854F;
        LegRight01.zRot = -0.5760F;
        LegRight02.xRot = 1.3090F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;

        EquipSL00.visible = false;
        Equip00.xRot = 0.0873F;
        Equip00.yRot = 0.0F;
        Equip00.zRot = 0.0F;
        EquipSR01.xRot = -0.1F;
        EquipSR01.yRot = -0.1396F;
        EquipSR01.zRot = -0.1396F;
        EarL01.xRot = 0.6F;
        EarL01.yRot = -0.1745F;
        EarL01.zRot = -0.0873F;
        EarR01.xRot = 0.6F;
        EarR01.yRot = 0.1745F;
        EarR01.zRot = 0.0873F;
    }

    private void applyBasePose(PoseContext ctx) {
        float angleX = ctx.angleX;

        BoobL.xRot = angleX * 0.06F - 0.8F;
        BoobR.xRot = angleX * 0.06F - 0.8F;
        Ahoke.yRot = angleX * 0.25F + 0.7F;

        BodyMain.xRot = BODY_BASE_X_ROT;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;
        Butt.xRot = BUTT_BASE_X_ROT;
        Skirt01.xRot = SKIRT_BASE_X_ROT;
        Skirt02.xRot = SKIRT02_BASE_X_ROT;

        ArmLeft01.xRot = ctx.angleAdd2 * 0.25F + 0.2F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = angleX * 0.03F - 0.25F;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = 0.0F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -angleX * 0.03F + 0.25F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = LEG_BASE_Z_ROT;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -LEG_BASE_Z_ROT;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;

        EquipSL00.xRot = -1.57F;
        EquipSL00.yRot = -0.14F;
        EquipSL00.zRot = 1.57F;
        EquipSR01.xRot = 1.3F;
    }

    private void applyEarAnimation(float ageInTicks) {
        EarL01.xRot = Mth.cos(ageInTicks * 0.08F) * 0.1F + 0.0873F;
        EarR01.xRot = EarL01.xRot;

        float modf2 = ageInTicks % 128.0F;
        if (modf2 < 6.0F) {
            if (modf2 >= 3.0F) {
                modf2 -= 3.0F;
            }
            float anglef2 = Mth.sin(modf2 * 1.0472F) * 0.08F;
            EarL01.zRot = -anglef2 - 0.0873F;
            EarR01.zRot = anglef2 + 0.0873F;
        } else {
            EarL01.zRot = -0.0873F;
            EarR01.zRot = 0.0873F;
        }
    }

    private void applyEquipAnimation(T entity, float headPitch) {
        EquipCL02.xRot = headPitch * 0.008F + 0.7F;
        EquipCR02.xRot = headPitch * 0.008F + 0.7F;
        EquipCL04.xRot = headPitch * 0.008F;
        EquipCR04.xRot = headPitch * 0.008F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks, float limbSwingAmount) {
        float legAddLeft = ctx.angleAdd1 * 0.5F - 0.28F;
        float legAddRight = ctx.angleAdd2 * 0.5F - 0.21F;

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        int tickPhase = entity != null ? (entity.tickCount & 0x1FF) : 0;
        if (entity != null && hasLegacyState(entity, 1, 4)) {
            if (tickPhase > 180) {
                ArmLeft01.xRot = 0.44F;
                ArmLeft01.yRot = -0.14F;
                ArmLeft01.zRot = -0.52F;
                ArmRight01.xRot = -0.17F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.7F;
                ArmRight02.zRot = -1.22F;
                legAddLeft = ctx.angleAdd1 * 0.5F - 0.35F;
                legAddRight = ctx.angleAdd2 * 0.5F - 0.09F;
                if (hasLegacyState(entity, 7, 4)) {
                    ArmLeft01.xRot = -1.4F;
                    ArmLeft01.yRot = -1.4F;
                    ArmLeft01.zRot = 0.87F;
                    ArmLeft02.xRot = -2.1F;
                    ArmLeft02.z = armLeft02DefaultZ + (-0.32F * OFFSET_SCALE);
                    EquipSL00.xRot = -1.83F;
                    EquipSL00.yRot = 0.35F;
                    EquipSL00.zRot = 1.57F;
                }
            } else {
                setFace(EntityShipBase.FACE_WINK);
                BodyMain.xRot = -0.44F;
                Head.xRot = 0.52F;
                Head.yRot = 0.0F;
                Head.zRot = 0.0F;
                ArmLeft01.xRot = -1.05F;
                ArmLeft01.yRot = -1.05F;
                ArmLeft01.zRot = 1.4F;
                ArmLeft02.zRot = 2.1F;
                ArmLeft02.x = armLeft02DefaultX + (-0.32F * OFFSET_SCALE);
                ArmLeft02.z = armLeft02DefaultZ;
                ArmRight01.xRot = -1.57F;
                ArmRight01.yRot = -1.31F;
                ArmRight01.zRot = 1.22F;
                ArmRight02.xRot = -0.96F;
                legAddLeft = ctx.angleAdd1 * 0.5F + 0.4F;
                legAddRight = ctx.angleAdd2 * 0.5F + 0.09F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = limbSwingAmount > 0.1F ? 0.05F : 0.26F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = limbSwingAmount > 0.1F ? -0.05F : -0.26F;
                Skirt01.xRot = 0.0F;
                Skirt02.xRot = 0.09F;
                EquipSL00.visible = false;
            }
        }

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = entity != null && entity.isSprinting() || limbSwingAmount > 0.9F;

        if (isSprinting) {
            this.poseTranslateY += 0.1F;
            Head.xRot -= 0.6F;
            BodyMain.xRot = 0.9F;
            Butt.xRot -= 0.7F;
            Skirt01.xRot = -0.15F;
            Skirt02.xRot = -0.32F;
            ArmLeft01.xRot = 0.7F;
            ArmLeft01.yRot = -1.1F;
            ArmLeft01.zRot = -1.0F;
            ArmRight01.xRot = 0.7F;
            ArmRight01.yRot = 1.1F;
            ArmRight01.zRot = 1.0F;
            if (!EquipSR01.visible) {
                ArmRight02.zRot = 0.0F;
            } else if (tickPhase > 300) {
                ArmRight02.zRot = -1.1F;
            }
            legAddLeft = ctx.angleAdd1 - 0.28F;
            legAddRight = ctx.angleAdd2 - 0.21F;
            EquipSR01.xRot = 0.7F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            Head.xRot -= 1.0472F;
            BodyMain.xRot = 1.0472F;
            Butt.xRot = -0.4F;
            Skirt01.xRot = -0.12F;
            Skirt02.xRot = -0.16F;
            Skirt02.y = skirt02DefaultY + (-0.1F * OFFSET_SCALE);

            if (!EquipSL00.visible) {
                ArmLeft01.xRot = -0.6F;
                ArmLeft01.zRot = 0.2618F;
                ArmRight01.xRot = -0.6F;
                ArmRight01.zRot = -0.2618F;
            } else {
                ArmLeft01.xRot = ctx.angleAdd2 * 0.25F - 0.1F;
                ArmLeft01.yRot = -0.7F;
                ArmLeft01.zRot = -0.3F;
                ArmRight01.xRot = ctx.angleAdd1 * 0.25F - 0.1F;
                ArmRight01.yRot = 0.7F;
                ArmRight01.zRot = 0.3F;
            }

            legAddLeft -= 0.4F;
            legAddRight -= 0.4F;
            EquipSR01.xRot = 0.0F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            this.poseTranslateY += SITTING_TRANSLATE_Y;

            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY -= 0.1F;
                BodyMain.xRot = 0.7F;
                Butt.xRot = -0.79F;
                Head.xRot -= 1.2F;
                if (EquipSL00.visible && hasLegacyState(entity, 7, 4)) {
                    ArmLeft01.xRot = -2.44F;
                    ArmLeft01.yRot = 1.05F;
                    ArmLeft01.zRot = 2.44F;
                    ArmLeft02.xRot = 0.0F;
                    ArmLeft02.zRot = 1.92F;
                    ArmLeft02.x = armLeft02DefaultX + (-0.32F * OFFSET_SCALE);
                    ArmRight01.xRot = -1.13F;
                    ArmRight01.yRot = 0.44F;
                    ArmRight01.zRot = 0.52F;
                    ArmRight02.xRot = 0.0F;
                    ArmRight02.zRot = -0.52F;
                    EquipSL00.xRot = -0.3F;
                    EquipSL00.yRot = -0.22F;
                    EquipSL00.zRot = 1.77F;
                    EquipSR01.xRot = 0.81F;
                } else {
                    ArmLeft01.xRot = -1.13F;
                    ArmLeft01.yRot = -0.44F;
                    ArmLeft01.zRot = -0.52F;
                    ArmLeft02.xRot = 0.0F;
                    ArmLeft02.zRot = 0.52F;
                    ArmRight01.xRot = -1.13F;
                    ArmRight01.yRot = 0.44F;
                    ArmRight01.zRot = 0.52F;
                    ArmRight02.xRot = 0.0F;
                    ArmRight02.zRot = -0.52F;
                    EquipSL00.xRot = -0.2F;
                    EquipSL00.yRot = -0.1F;
                    EquipSL00.zRot = 1.4F;
                    EquipSR01.xRot = 0.81F;
                }
                legAddLeft = -2.1F;
                legAddRight = -2.1F;
                LegLeft01.yRot = -0.58F;
                LegLeft01.zRot = 0.05F;
                LegLeft02.xRot = 2.44F;
                LegLeft02.z = legLeft02DefaultZ + (0.38F * OFFSET_SCALE);
                LegRight01.yRot = 0.58F;
                LegRight01.zRot = -0.05F;
                LegRight02.xRot = 2.44F;
                LegRight02.z = legRight02DefaultZ + (0.38F * OFFSET_SCALE);
                Skirt01.xRot = -0.17F;
                Skirt02.xRot = -0.26F;
            } else {
                BodyMain.xRot = 0.0873F;
                Butt.xRot = -0.1745F;
                Head.xRot -= 0.2F;
                ArmLeft01.xRot = 0.2618F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = -0.2618F;
                ArmRight01.xRot = -1.1345F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.0F;
                ArmRight02.zRot = -1.2217F;
                legAddLeft = -1.45F;
                legAddRight = -2.1F;
                LegLeft01.xRot = -1.4486F;
                LegLeft01.yRot = 0.0873F;
                LegLeft01.zRot = 0.0F;
                LegRight01.xRot = -2.0944F;
                LegRight01.yRot = 0.0911F;
                LegRight01.zRot = 0.1745F;
                LegRight02.xRot = 1.3963F;
                Skirt01.xRot = -0.17F;
                Skirt02.xRot = -0.26F;
                EquipSL00.xRot = -1.6755F;
                EquipSL00.yRot = 0.1745F;
                EquipSL00.zRot = 0.8727F;
                EquipSR01.xRot = 1.3090F;
                EquipSR01.yRot = -0.1396F;
                EquipSR01.zRot = -0.1396F;
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            this.poseTranslateY = 0.22F + entity.getScaleLevel() * 0.12F;
            Head.xRot = -0.4363F;
            Head.yRot = 0.0F;
            Head.zRot = 0.0F;
            BodyMain.xRot = 1.0472F;
            BodyMain.yRot = 0.2618F;
            BodyMain.zRot = 0.0F;
            Butt.xRot = -0.5236F;
            Butt.yRot = 0.0F;
            Butt.zRot = 0.0F;
            ArmLeft01.xRot = -0.7854F;
            ArmLeft01.yRot = 0.2618F;
            ArmLeft01.zRot = 0.5236F;
            ArmLeft02.xRot = 0.0F;
            ArmLeft02.yRot = 0.0F;
            ArmLeft02.zRot = 0.7854F;
            ArmRight01.xRot = 0.5236F;
            ArmRight01.yRot = -0.3491F;
            ArmRight01.zRot = 0.1745F;
            ArmRight02.xRot = -1.3090F;
            ArmRight02.yRot = 0.0F;
            ArmRight02.zRot = 0.0F;
            legAddLeft = 0.31F;
            legAddRight = -1.57F;
            LegLeft01.yRot = -0.1745F;
            LegLeft01.zRot = 0.0873F;
            LegLeft02.xRot = 0.13F;
            LegRight01.yRot = 0.0F;
            LegRight01.zRot = 0.1396F;
            LegRight02.xRot = 1.2292F;
            EquipSL00.visible = true;
            EquipSR01.xRot = 0.8652F;
            EquipSR01.yRot = -0.1396F;
            EquipSR01.zRot = -0.1396F;
            EquipSL00.xRot = 1.5935F;
            EquipSL00.yRot = 0.1820F;
            EquipSL00.zRot = 1.5708F;

            if (entity.getAttackTick() < 51) {
                if (entity.getAttackTick() > 45) {
                    int tick = 4 - (entity.getAttackTick() - 46);
                    float parTick = ageInTicks - (int) ageInTicks + tick;
                    ArmLeft01.xRot = -0.785F - 0.644F * parTick;
                    ArmLeft02.zRot = 0.785F - 0.157F * parTick;
                    EquipSL00.yRot = 0.182F + 0.278F * parTick;
                } else {
                    ArmLeft01.xRot = -4.1F;
                    ArmLeft02.zRot = 0.0F;
                    EquipSL00.yRot = 1.57F;
                }
            }

            if (hasLegacyState(entity, 5, 3)) {
                BodyMain.xRot = 2.1F;
                ArmLeft01.xRot = -1.92F;
                ArmLeft01.yRot = 0.4F;
                ArmLeft01.zRot = 0.26F;
                ArmLeft02.zRot = 0.0F;
                ArmRight01.xRot = -1.92F;
                ArmRight01.yRot = -0.4F;
                ArmRight01.zRot = 0.26F;
                ArmRight02.xRot = 0.0F;
                EquipSL00.xRot = -1.4F;
                EquipSL00.yRot = -0.14F;
                EquipSL00.zRot = 1.57F;
            }
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot = -0.4F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.2F;
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        LegLeft01.xRot = legAddLeft;
        LegRight01.xRot = legAddRight;
    }

    private void resetOffsets() {
        Butt.y = buttDefaultY;
        Skirt01.y = skirt01DefaultY;
        Skirt02.y = skirt02DefaultY;
        Hair01.y = hair01DefaultY;
        LegLeft02.x = legLeft02DefaultX;
        LegLeft02.y = legLeft02DefaultY;
        LegLeft02.z = legLeft02DefaultZ;
        LegRight02.x = legRight02DefaultX;
        LegRight02.y = legRight02DefaultY;
        LegRight02.z = legRight02DefaultZ;
        ArmLeft02.x = armLeft02DefaultX;
        ArmLeft02.y = armLeft02DefaultY;
        ArmLeft02.z = armLeft02DefaultZ;
        ArmRight02.x = armRight02DefaultX;
        ArmRight02.y = armRight02DefaultY;
        ArmRight02.z = armRight02DefaultZ;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
            GlowNeck.copyFrom(Neck);
            GlowHead.copyFrom(Head);

            if (GlowEquip00 != null) {
                GlowEquip00.copyFrom(Equip00);
                GlowEquip01a.copyFrom(Equip01a);
                GlowEquip02a.copyFrom(Equip02a);
            }
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        if (GlowBodyMain != null) {
            GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
