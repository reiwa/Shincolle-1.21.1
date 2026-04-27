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

public class ModelCruiserTatsuta<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "cruiser_tatsuta"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCruiserTatsuta");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelCruiserTatsuta");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelCruiserTatsuta");

    private static final float BODY_BASE_X_ROT = -0.1047F;
    private static final float BUTT_BASE_X_ROT = 0.35F;
    private static final float SKIRT_BASE_X_ROT = -0.14F;
    private static final float SKIRT02_BASE_X_ROT = -0.09F;
    private static final float LEG_BASE_Z_ROT = 0.0873F;

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft01;
    private final ModelPart Cloth01;
    private final ModelPart Equip00;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart CirBase;
    private final ModelPart HairU01;
    private final ModelPart Ahoke;
    private final ModelPart Hair01;
    private final ModelPart Cir00;
    private final ModelPart Cir01;
    private final ModelPart Cir02;
    private final ModelPart Cir03;
    private final ModelPart Cir04;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft02;
    private final ModelPart Skirt02;
    private final ModelPart LegRight02;
    private final ModelPart ArmRight02;
    private final ModelPart ArmLeft02;
    private final ModelPart EquipSL00;
    private final ModelPart EquipSL01;
    private final ModelPart EquipSL04;
    private final ModelPart EquipSL02;
    private final ModelPart EquipSL03a;
    private final ModelPart EquipSL03b;
    private final ModelPart EquipSL03c;
    private final ModelPart EquipSL05;
    private final ModelPart Equip01a;
    private final ModelPart Equip01b;
    private final ModelPart Equip01c;
    private final ModelPart Equip02a;
    private final ModelPart Equip01d;
    private final ModelPart Equip03L;
    private final ModelPart Equip03R;
    private final ModelPart EquipCL01;
    private final ModelPart EquipCL02;
    private final ModelPart EquipCL03a;
    private final ModelPart EquipCL03b;
    private final ModelPart EquipCL03c;
    private final ModelPart EquipCR01;
    private final ModelPart EquipCR02;
    private final ModelPart EquipCR03a;
    private final ModelPart EquipCR03b;
    private final ModelPart EquipCR03c;
    private final ModelPart Equip02b;
    private final ModelPart Equip02c;
    private final ModelPart Equip02d;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowBodyMain2;
    private final ModelPart GlowNeck2;
    private final ModelPart GlowHead2;
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
    private final float cirBaseDefaultY;
    private final float hair01DefaultZRot;
    private final float equipSL00DefaultX;
    private final float equipSL00DefaultY;
    private final float equipSL00DefaultZ;

    public ModelCruiserTatsuta(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.EquipSL00 = this.ArmLeft02.getChild("EquipSL00");
        this.equipSL00DefaultX = this.EquipSL00.x;
        this.equipSL00DefaultY = this.EquipSL00.y;
        this.equipSL00DefaultZ = this.EquipSL00.z;
        this.EquipSL04 = this.EquipSL00.getChild("EquipSL04");
        this.EquipSL05 = this.EquipSL04.getChild("EquipSL05");
        this.EquipSL01 = this.EquipSL00.getChild("EquipSL01");
        this.EquipSL02 = this.EquipSL01.getChild("EquipSL02");
        this.EquipSL03c = this.EquipSL02.getChild("EquipSL03c");
        this.EquipSL03a = this.EquipSL02.getChild("EquipSL03a");
        this.EquipSL03b = this.EquipSL02.getChild("EquipSL03b");
        this.Equip00 = this.BodyMain.getChild("Equip00");
        this.Equip01a = this.Equip00.getChild("Equip01a");
        this.Equip02a = this.Equip01a.getChild("Equip02a");
        this.Equip01b = this.Equip01a.getChild("Equip01b");
        this.Equip01c = this.Equip01a.getChild("Equip01c");
        this.Equip01d = this.Equip01c.getChild("Equip01d");
        this.Equip03R = this.Equip01d.getChild("Equip03R");
        this.EquipCR01 = this.Equip03R.getChild("EquipCR01");
        this.EquipCR02 = this.EquipCR01.getChild("EquipCR02");
        this.EquipCR03c = this.EquipCR02.getChild("EquipCR03c");
        this.EquipCR03b = this.EquipCR02.getChild("EquipCR03b");
        this.EquipCR03a = this.EquipCR02.getChild("EquipCR03a");
        this.Equip03L = this.Equip01d.getChild("Equip03L");
        this.EquipCL01 = this.Equip03L.getChild("EquipCL01");
        this.EquipCL02 = this.EquipCL01.getChild("EquipCL02");
        this.EquipCL03b = this.EquipCL02.getChild("EquipCL03b");
        this.EquipCL03a = this.EquipCL02.getChild("EquipCL03a");
        this.EquipCL03c = this.EquipCL02.getChild("EquipCL03c");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.GlowEquip00 = this.GlowBodyMain.getChild("GlowEquip00");
        this.GlowEquip01a = this.GlowEquip00.getChild("GlowEquip01a");
        this.GlowEquip02a = this.GlowEquip01a.getChild("GlowEquip02a");
        this.Equip02b = this.GlowEquip02a.getChild("Equip02b");
        this.Equip02c = this.Equip02b.getChild("Equip02c");
        this.Equip02d = this.Equip02c.getChild("Equip02d");
        this.GlowBodyMain2 = root.getChild("GlowBodyMain2");
        this.GlowNeck2 = this.GlowBodyMain2.getChild("GlowNeck2");
        this.GlowHead2 = this.GlowNeck2.getChild("GlowHead2");
        this.CirBase = this.GlowHead2.getChild("CirBase");
        this.Cir00 = this.CirBase.getChild("Cir00");
        this.Cir01 = this.Cir00.getChild("Cir01");
        this.Cir02 = this.Cir00.getChild("Cir02");
        this.Cir03 = this.Cir00.getChild("Cir03");
        this.Cir04 = this.Cir00.getChild("Cir04");
        this.cirBaseDefaultY = this.CirBase.y;
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
        this.hair01DefaultZRot = this.Hair01.zRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, -0.1745F, 0F, 0.2618F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 63).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.1F, -8F, -3.6F, -0.6981F, 0.1047F, 0.1396F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(34, 101).mirror().addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -8F, -3.7F, -0.6981F, -0.0925F, -0.1396F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 18).addBox(-2.5F, -2F, -3.6F, 5F, 2F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.7F, 0.1F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(106, 31).addBox(0F, -1F, -5.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, -4F, -7.5F, 0.2618F, 1.8326F, 0.2618F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -6.9F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(10, 16).addBox(-8F, 0F, -8F, 16F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 8.2F, 0.1745F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 84).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.3142F, 0F, -0.5236F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 63).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition EquipSL00 = ArmLeft02.addOrReplaceChild("EquipSL00", CubeListBuilder.create().texOffs(106, 0).addBox(-0.5F, -6F, -0.5F, 1F, 12F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 10F, -2F, -1.5708F, -0.0873F, 0.5236F));

        PartDefinition EquipSL04 = EquipSL00.addOrReplaceChild("EquipSL04", CubeListBuilder.create().texOffs(106, 0).addBox(-0.5F, 0F, -0.5F, 1F, 12F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -17.9F, 0F));

        PartDefinition EquipSL05 = EquipSL04.addOrReplaceChild("EquipSL05", CubeListBuilder.create().texOffs(106, 0).addBox(-0.5F, 0F, -0.5F, 1F, 12F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -11.9F, 0F));

        PartDefinition EquipSL01 = EquipSL00.addOrReplaceChild("EquipSL01", CubeListBuilder.create().texOffs(106, 0).addBox(-0.5F, 0F, -0.5F, 1F, 12F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 5.9F, 0F));

        PartDefinition EquipSL02 = EquipSL01.addOrReplaceChild("EquipSL02", CubeListBuilder.create().texOffs(110, 0).addBox(-0.5F, 0F, -0.5F, 1F, 12F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 11.9F, 0F));

        PartDefinition EquipSL03c = EquipSL02.addOrReplaceChild("EquipSL03c", CubeListBuilder.create().texOffs(114, 0).addBox(-0.5F, -7F, -2F, 1F, 8F, 2F, new CubeDeformation(0F)), PartPose.offset(-0.1F, 13.9F, 3.1F));

        PartDefinition EquipSL03a = EquipSL02.addOrReplaceChild("EquipSL03a", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, 0F, -0.5F, 1F, 14F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 11.9F, -0.4F));

        PartDefinition EquipSL03b = EquipSL02.addOrReplaceChild("EquipSL03b", CubeListBuilder.create().texOffs(102, 0).addBox(-0.5F, -11F, -1F, 1F, 11F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 25.7F, 2.1F, -0.0873F, 0F, 0F));

        PartDefinition Equip00 = BodyMain.addOrReplaceChild("Equip00", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -2F, 3F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 5F, 0.0873F, 0F, 0F));

        PartDefinition Equip01a = Equip00.addOrReplaceChild("Equip01a", CubeListBuilder.create().texOffs(26, 0).addBox(-3.5F, 0F, 0F, 7F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, 0F));

        PartDefinition Equip02a = Equip01a.addOrReplaceChild("Equip02a", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -0.4F, 10F));

        PartDefinition Equip01b = Equip01a.addOrReplaceChild("Equip01b", CubeListBuilder.create().texOffs(50, 0).addBox(-5.5F, 0F, 0F, 11F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 0F));

        PartDefinition Equip01c = Equip01a.addOrReplaceChild("Equip01c", CubeListBuilder.create().texOffs(26, 0).addBox(-3.5F, 0F, 0F, 7F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 5F));

        PartDefinition Equip01d = Equip01c.addOrReplaceChild("Equip01d", CubeListBuilder.create().texOffs(50, 0).addBox(-5.5F, 0F, 0F, 11F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 0F));

        PartDefinition Equip03R = Equip01d.addOrReplaceChild("Equip03R", CubeListBuilder.create().texOffs(86, 104).mirror().addBox(-4F, 0F, 0F, 4F, 8F, 2F, new CubeDeformation(0F)), PartPose.offset(-5F, 1.5F, 4.5F));

        PartDefinition EquipCR01 = Equip03R.addOrReplaceChild("EquipCR01", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, -1F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-3.5F, 3.5F, 2F));

        PartDefinition EquipCR02 = EquipCR01.addOrReplaceChild("EquipCR02", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-1F, -3F, -4F, 1F, 7F, 9F, new CubeDeformation(0F)), PartPose.offset(-1.9F, 0F, 0F));

        PartDefinition EquipCR03c = EquipCR02.addOrReplaceChild("EquipCR03c", CubeListBuilder.create().texOffs(0, 27).addBox(-1F, -7F, -1F, 2F, 14F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.9F, -0.5F, 2.7F, 0F, -0.3491F, 0F));

        PartDefinition EquipCR03b = EquipCR02.addOrReplaceChild("EquipCR03b", CubeListBuilder.create().texOffs(0, 27).addBox(-1F, -7F, -1F, 2F, 14F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.9F, -0.5F, 0.5F, 0F, -0.3491F, 0F));

        PartDefinition EquipCR03a = EquipCR02.addOrReplaceChild("EquipCR03a", CubeListBuilder.create().texOffs(0, 27).addBox(-1F, -7F, -1F, 2F, 14F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.9F, -0.5F, -1.7F, 0F, -0.3491F, 0F));

        PartDefinition Equip03L = Equip01d.addOrReplaceChild("Equip03L", CubeListBuilder.create().texOffs(86, 104).addBox(0F, 0F, 0F, 4F, 8F, 2F, new CubeDeformation(0F)), PartPose.offset(5F, 1.5F, 4.5F));

        PartDefinition EquipCL01 = Equip03L.addOrReplaceChild("EquipCL01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(3.5F, 3.5F, 2F));

        PartDefinition EquipCL02 = EquipCL01.addOrReplaceChild("EquipCL02", CubeListBuilder.create().texOffs(0, 2).addBox(0F, -3F, -4F, 1F, 7F, 9F, new CubeDeformation(0F)), PartPose.offset(1.9F, 0F, 0F));

        PartDefinition EquipCL03b = EquipCL02.addOrReplaceChild("EquipCL03b", CubeListBuilder.create().texOffs(0, 27).addBox(-1F, -7F, -1F, 2F, 14F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.9F, -0.5F, 0.5F, 0F, 0.3491F, 0F));

        PartDefinition EquipCL03a = EquipCL02.addOrReplaceChild("EquipCL03a", CubeListBuilder.create().texOffs(0, 27).addBox(-1F, -7F, -1F, 2F, 14F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.9F, -0.5F, -1.7F, 0F, 0.3491F, 0F));

        PartDefinition EquipCL03c = EquipCL02.addOrReplaceChild("EquipCL03c", CubeListBuilder.create().texOffs(0, 27).addBox(-1F, -7F, -1F, 2F, 14F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.9F, -0.5F, 2.7F, 0F, 0.3491F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 47).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.0873F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(46, 43).addBox(-8.5F, 0F, -6F, 17F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.9F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(0, 33).addBox(-9F, 0F, -6F, 18F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.8F, -0.5F, -0.0873F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 63).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(112, 34).addBox(-4F, 0F, 0F, 8F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9.6F, -3.8F, -0.576F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowEquip00 = GlowBodyMain.addOrReplaceChild("GlowEquip00", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 4F, 5F, 0.0873F, 0F, 0F));

        PartDefinition GlowEquip01a = GlowEquip00.addOrReplaceChild("GlowEquip01a", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -12F, 0F));

        PartDefinition GlowEquip02a = GlowEquip01a.addOrReplaceChild("GlowEquip02a", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -0.4F, 10F));

        PartDefinition Equip02b = GlowEquip02a.addOrReplaceChild("Equip02b", CubeListBuilder.create().texOffs(104, 24).addBox(-4F, 0F, 0F, 8F, 6F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 4F));

        PartDefinition Equip02c = Equip02b.addOrReplaceChild("Equip02c", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, 0F, 7F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, 4F));

        PartDefinition Equip02d = Equip02c.addOrReplaceChild("Equip02d", CubeListBuilder.create().texOffs(0, 49).addBox(-1F, 0F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 0F));

        PartDefinition GlowBodyMain2 = partdefinition.addOrReplaceChild("GlowBodyMain2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck2 = GlowBodyMain2.addOrReplaceChild("GlowNeck2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead2 = GlowNeck2.addOrReplaceChild("GlowHead2", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition CirBase = GlowHead2.addOrReplaceChild("CirBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -21F, 4F, -0.2618F, 0F, 0F));

        PartDefinition Cir00 = CirBase.addOrReplaceChild("Cir00", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Cir01 = Cir00.addOrReplaceChild("Cir01", CubeListBuilder.create().texOffs(20, 12).addBox(-6F, 0F, -0.5F, 12F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5.5F));

        PartDefinition Cir02 = Cir00.addOrReplaceChild("Cir02", CubeListBuilder.create().texOffs(20, 12).addBox(-6F, 0F, -0.5F, 12F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 5.5F));

        PartDefinition Cir03 = Cir00.addOrReplaceChild("Cir03", CubeListBuilder.create().texOffs(20, 12).addBox(-6F, 0F, -0.5F, 12F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 0F, 0F, 0F, 1.5708F, 0F));

        PartDefinition Cir04 = Cir00.addOrReplaceChild("Cir04", CubeListBuilder.create().texOffs(20, 12).addBox(-6F, 0F, -0.5F, 12F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.5F, 0F, 0F, 0F, -1.5708F, 0F));

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

        applyBasePose(ctx, ageInTicks, headPitch);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks, limbSwingAmount);

        float headX = Head.xRot * -0.5F;
        float headZ = Head.zRot * -0.5F;
        Hair01.xRot += headX;
        Hair01.zRot += headZ;

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

        boolean showEquip = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTatsuta.EQUIP_RIGGING);
        Equip00.visible = showEquip;
        if (GlowEquip00 != null) GlowEquip00.visible = showEquip;
        CirBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTatsuta.EQUIP_RING);
        EquipSL00.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTatsuta.EQUIP_SIDE);
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = 0.9599F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        Ahoke.xRot = 0.2618F;
        Ahoke.yRot = 1.8326F;
        Ahoke.zRot = 0.2618F;

        BodyMain.xRot = -0.2618F;
        Butt.xRot = -0.2618F;
        Skirt01.xRot = -0.1745F;
        Skirt02.xRot = -0.2094F;

        ArmLeft01.xRot = 0.4142F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = -0.4363F;
        ArmLeft02.xRot = -0.1047F;
        ArmLeft02.yRot = 0.0F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = 0.3618F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = 0.2731F;
        ArmRight02.xRot = -0.2731F;
        ArmRight02.yRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        EquipSL00.xRot = -1.6835F;
        EquipSL00.yRot = 0.0F;
        EquipSL00.zRot = -1.1F;
        EquipCL02.xRot = 1.63F;
        EquipCR02.xRot = 1.63F;

        Cir00.yRot = 0.0F;
        CirBase.y = cirBaseDefaultY + (0.26F * OFFSET_SCALE);

        LegLeft01.xRot = -1.7453F;
        LegLeft01.yRot = -0.5463F;
        LegLeft01.zRot = 1.4835F;
        LegLeft02.xRot = 0.4363F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.xRot = -1.5708F;
        LegRight01.yRot = 0.0873F;
        LegRight01.zRot = -0.1745F;
        LegRight02.xRot = 1.1345F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;
    }

    private void applyBasePose(PoseContext ctx, float ageInTicks, float headPitch) {
        float angleX = ctx.angleX;

        Neck.xRot = 0.1047F;
        Neck.yRot = 0.0F;
        Neck.zRot = 0.0F;

        BodyMain.xRot = BODY_BASE_X_ROT;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;
        Butt.xRot = BUTT_BASE_X_ROT;
        Skirt01.xRot = SKIRT_BASE_X_ROT;
        Skirt02.xRot = SKIRT02_BASE_X_ROT;

        Cloth01.xRot = angleX * 0.06F - 0.7F;
        BoobL.xRot = angleX * 0.06F - 0.8F;
        BoobR.xRot = angleX * 0.06F - 0.8F;
        Hair01.xRot = angleX * 0.04F + 0.2618F;
        Hair01.zRot = hair01DefaultZRot;

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

        Cir00.yRot = 0.0F;
        CirBase.y = cirBaseDefaultY;

        EquipSL00.xRot = -1.1F;
        EquipSL00.yRot = 0.4F;
        EquipSL00.zRot = 0.0F;

        EquipCL02.xRot = headPitch * 0.015F + 0.7F;
        EquipCR02.xRot = headPitch * 0.015F + 0.7F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks, float limbSwingAmount) {
        float legAddLeft = ctx.angleAdd1 * 0.5F - 0.28F;
        float legAddRight = ctx.angleAdd2 * 0.5F - 0.21F;

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        boolean spcStand = false;
        int tickPhase = entity != null ? (entity.tickCount & 0x1FF) : 0;
        if (entity != null && hasLegacyState(entity, 1, 4)) {
            spcStand = true;
            if (tickPhase > 320) {
                setFace(EntityShipBase.FACE_WINK);
            } else if (tickPhase > 160) {
                setFace(EntityShipBase.FACE_EYES_OPEN);
            } else {
                setFace(EntityShipBase.FACE_EYES_CLOSED);
            }
            Head.yRot = 0.0F;
            ArmLeft01.xRot = -0.3491F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft01.zRot = 0.4554F;
            ArmLeft02.xRot = 0.0F;
            ArmLeft02.yRot = 0.0F;
            ArmLeft02.zRot = 1.0472F;
            ArmRight01.xRot = -0.5463F;
            ArmRight01.yRot = -0.2618F;
            ArmRight01.zRot = -0.1396F;
            ArmRight02.xRot = -2.5307F;
            ArmRight02.zRot = 0.0F;
            ArmRight02.z = armRight02DefaultZ + (-0.32F * OFFSET_SCALE);
            EquipSL00.xRot = -1.17F;
            EquipSL00.yRot = 1.45F;
            EquipSL00.zRot = 0.0F;
            if (hasLegacyState(entity, 7, 4)) {
                ArmLeft01.xRot = 0.6981F;
                ArmLeft01.yRot = -1.0472F;
                ArmLeft01.zRot = -2.4435F;
                ArmLeft02.xRot = -1.3963F;
                ArmLeft02.yRot = 0.0F;
                ArmLeft02.zRot = 0.0F;
                EquipSL00.xRot = -1.5708F;
                EquipSL00.yRot = 0.9F;
                EquipSL00.zRot = 0.0F;
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
            ArmRight02.zRot = 0.0F;

            legAddLeft = ctx.angleAdd1 - 0.28F;
            legAddRight = ctx.angleAdd2 - 0.21F;

            EquipSL00.xRot = -1.5F;
            EquipSL00.yRot = 0.2F;
            EquipSL00.zRot = 0.0F;
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

            Cir00.yRot = ageInTicks * 0.025F;
        }

        if (isSitting) {
            this.isSittingPose = true;

            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.5F * 3.2F;
                Head.xRot = 0.0F;
                Head.yRot += 1.2217F;
                Head.zRot = -0.0873F;
                BodyMain.xRot = -0.35F;
                BodyMain.yRot = -1.4486F;
                Butt.xRot = -0.3840F;
                Skirt01.xRot = -0.1745F;
                Skirt02.xRot = -0.2618F;
                ArmLeft01.xRot = -1.22F;
                ArmLeft01.yRot = 0.3142F;
                ArmLeft01.zRot = 0.0F;
                ArmRight01.xRot = -0.1745F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.2618F;
                legAddLeft = -1.57F;
                legAddRight = -1.4F;
                LegLeft01.zRot = 0.0873F;
                LegLeft02.xRot = 0.6109F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.0873F;
                LegRight02.xRot = 1.4835F;
                Cir00.yRot = ageInTicks * 0.025F;
                EquipSL00.xRot = 1.42F;
                EquipSL00.yRot = -0.18F;
                EquipSL00.zRot = 0.0F;
                EquipSL00.y = equipSL00DefaultY + (0.15F * OFFSET_SCALE);
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                BodyMain.xRot = -0.3F;
                Butt.xRot = -0.2F;
                Skirt01.xRot = -0.26F;
                Skirt02.xRot = -0.45F;
                legAddLeft = -0.9F;
                legAddRight = -0.9F;
                LegLeft01.zRot = -0.14F;
                LegLeft02.xRot = 1.2217F;
                LegLeft02.yRot = 1.2217F;
                LegLeft02.zRot = -1.0472F;
                LegLeft02.y = legLeft02DefaultY + (-0.06F * OFFSET_SCALE);
                LegRight01.zRot = 0.14F;
                LegRight02.xRot = 1.2217F;
                LegRight02.yRot = -1.2217F;
                LegRight02.zRot = 1.0472F;
                LegRight02.y = legRight02DefaultY + (-0.06F * OFFSET_SCALE);
                Cir00.yRot = ageInTicks * 0.025F;
                EquipSL00.xRot = -1.06F;
                EquipSL00.yRot = 0.02F;
                EquipSL00.zRot = -1.29F;
                if (spcStand) {
                    ArmRight01.xRot += 0.3F;
                    if (entity != null && hasLegacyState(entity, 7, 4)) {
                        EquipSL00.xRot = -1.5708F;
                        EquipSL00.yRot = 1.2F;
                        EquipSL00.zRot = 0.0F;
                    } else {
                        EquipSL00.visible = false;
                    }
                } else {
                    ArmLeft01.xRot += 0.1F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = -0.25F;
                    ArmRight01.xRot += 0.3F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = 0.25F;
                }
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            if (!hasLegacyState(entity, 5, 1)) {
                this.poseTranslateY += 0.05F + entity.getScaleLevel() * 0.02F;
                BodyMain.xRot = 0.1745F;
                BodyMain.yRot = 0.0F;
                BodyMain.zRot = 0.0F;
                Butt.xRot = 0.0F;
                Head.xRot = -0.1F;
                Skirt01.xRot = -0.1396F;
                Skirt02.xRot = -0.0873F;
                ArmLeft01.xRot = -1.6755F;
                ArmLeft01.yRot = 0.5236F;
                ArmLeft01.zRot = 0.0F;
                ArmLeft02.xRot = 0.0F;
                ArmLeft02.yRot = 0.0F;
                ArmLeft02.zRot = 0.0F;
                ArmRight01.xRot = 0.5236F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.5236F;
                ArmRight02.xRot = 0.0F;
                ArmRight02.yRot = 0.0F;
                ArmRight02.zRot = 0.0F;
                legAddLeft = -0.5236F;
                legAddRight = 0.2618F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.0873F;
                LegLeft02.xRot = 0.3643F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.0873F;
                EquipSL00.visible = true;
                EquipSL00.xRot = -0.1367F;
                EquipSL00.yRot = 1.5708F;
                EquipSL00.zRot = 0.1367F;
                if (entity.getAttackTick() < 51) {
                    if (entity.getAttackTick() > 45) {
                        int tick = 4 - (entity.getAttackTick() - 46);
                        float parTick = ageInTicks - (int) ageInTicks + tick;
                        ArmLeft01.yRot = 0.52F - 0.524F * parTick;
                    } else {
                        ArmLeft01.yRot = -2.1F;
                    }
                }
            } else {
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
                ArmRight02.zRot = 0.0F;
                legAddLeft = ctx.angleAdd1 - 0.28F;
                legAddRight = ctx.angleAdd2 - 0.21F;
                EquipSL00.xRot = -1.5F;
                EquipSL00.yRot = 0.2F;
                EquipSL00.zRot = 0.0F;
            }

            if (hasLegacyState(entity, 5, 2)) {
                Head.xRot = -0.2618F;
                BodyMain.xRot = 0.0F;
                BodyMain.yRot = ageInTicks * -2.0F;
                ArmLeft01.xRot = -1.6755F;
                ArmLeft01.yRot = -1.3963F;
                ArmLeft01.zRot = 0.0F;
                ArmRight01.xRot = 0.1745F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 1.6755F;
                legAddLeft = -0.5236F;
                legAddRight = 0.1396F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.0873F;
                LegLeft02.xRot = 1.0472F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.0873F;
            } else if (hasLegacyState(entity, 5, 3)) {
                Head.xRot = -0.7854F;
                BodyMain.xRot = 1.3963F;
                Butt.xRot = -0.8727F;
                ArmLeft01.xRot = -2.35F;
                ArmLeft01.yRot = 0.2618F;
                ArmLeft01.zRot = 0.0F;
                ArmRight01.xRot = 0.6981F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.6981F;
                legAddLeft = 0.2618F;
                legAddRight = -0.5236F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.0873F;
                LegLeft02.xRot = 0.2618F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.0873F;
                LegRight02.xRot = 1.3963F;
                EquipSL00.xRot = 0.0F;
                EquipSL00.yRot = 0.0F;
                EquipSL00.zRot = -0.1745F;
                EquipSL00.x = equipSL00DefaultX + (0.32F * OFFSET_SCALE) + (50 - entity.getAttackTick()) * (0.22F * OFFSET_SCALE);
                EquipSL00.y = equipSL00DefaultY + (2.0F * OFFSET_SCALE) + (50 - entity.getAttackTick()) * (5.0F * OFFSET_SCALE);
                EquipSL00.z = equipSL00DefaultZ + (-0.08F * OFFSET_SCALE);
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
        CirBase.y = cirBaseDefaultY;
        EquipSL00.x = equipSL00DefaultX;
        EquipSL00.y = equipSL00DefaultY;
        EquipSL00.z = equipSL00DefaultZ;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
            GlowNeck.copyFrom(Neck);
            GlowHead.copyFrom(Head);

            GlowBodyMain2.copyFrom(BodyMain);
            GlowNeck2.copyFrom(Neck);
            GlowHead2.copyFrom(Head);

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

        if (GlowBodyMain != null) {
            GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
            GlowBodyMain2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
