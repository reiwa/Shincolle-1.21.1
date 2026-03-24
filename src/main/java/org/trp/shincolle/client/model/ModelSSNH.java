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

public class ModelSSNH<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "subm_hime_new"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelSSNH");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelSSNH");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelSSNH");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;
    private float poseTranslateZ;

    private final ModelPart BodyMain;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Butt;
    private final ModelPart EquipBase;
    private final ModelPart Cloth01;
    private final ModelPart Neck;
    private final ModelPart Cloth00;
    private final ModelPart RingBase;
    private final ModelPart ArmLeft02;
    private final ModelPart EquipTBase;
    private final ModelPart EqyuipT01;
    private final ModelPart EqyuipT02;
    private final ModelPart EqyuipT04;
    private final ModelPart EqyuipT03;
    private final ModelPart EquipT03a;
    private final ModelPart EqyuipT05;
    private final ModelPart EquipT05a;
    private final ModelPart EquipT05b;
    private final ModelPart EquipT05c;
    private final ModelPart EquipT05d;
    private final ModelPart EquipTBase_2;
    private final ModelPart EqyuipT01_2;
    private final ModelPart EqyuipT02_2;
    private final ModelPart EqyuipT04_2;
    private final ModelPart EqyuipT03_2;
    private final ModelPart EquipT03a_2;
    private final ModelPart EqyuipT05_2;
    private final ModelPart EquipT05a_2;
    private final ModelPart EquipT05b_2;
    private final ModelPart EquipT05c_2;
    private final ModelPart EquipT05d_2;
    private final ModelPart ArmRight02;
    private final ModelPart EquipHandRing;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart LegLeft02;
    private final ModelPart Cloth02;
    private final ModelPart Cloth03;
    private final ModelPart Cloth04;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke01;
    private final ModelPart Ahoke01a;
    private final ModelPart HairU01;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Ahoke02;
    private final ModelPart Ahoke03;
    private final ModelPart Ahoke04;
    private final ModelPart Ahoke05;
    private final ModelPart Ahoke06;
    private final ModelPart Ahoke02a;
    private final ModelPart Ahoke03a;
    private final ModelPart Ahoke04a;
    private final ModelPart Ahoke05a;
    private final ModelPart Ahoke06a;
    private final ModelPart Ring01;
    private final ModelPart Ring02;
    private final ModelPart Ring03Base;
    private final ModelPart Ring03a;
    private final ModelPart Ring03b;
    private final ModelPart Ring03c;
    private final ModelPart Ring03d;
    private final ModelPart Ring03e;
    private final ModelPart Ring03f;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultZ;
    private final float legLeft02DefaultX;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultX;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;
    private final float equipTBaseDefaultX;
    private final float equipTBaseDefaultY;
    private final float equipTBase2DefaultX;
    private final float equipTBase2DefaultY;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float cloth02DefaultY;
    private final float cloth03DefaultY;
    private final float cloth03DefaultZ;
    private final float cloth04DefaultY;
    private final float cloth04DefaultZ;

    public ModelSSNH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.EquipTBase = this.ArmLeft02.getChild("EquipTBase");
        this.EqyuipT01 = this.EquipTBase.getChild("EqyuipT01");
        this.EqyuipT02 = this.EqyuipT01.getChild("EqyuipT02");
        this.EqyuipT03 = this.EqyuipT02.getChild("EqyuipT03");
        this.EquipT03a = this.EqyuipT03.getChild("EquipT03a");
        this.EqyuipT04 = this.EqyuipT01.getChild("EqyuipT04");
        this.EqyuipT05 = this.EqyuipT04.getChild("EqyuipT05");
        this.EquipT05b = this.EqyuipT05.getChild("EquipT05b");
        this.EquipT05c = this.EqyuipT05.getChild("EquipT05c");
        this.EquipT05a = this.EqyuipT05.getChild("EquipT05a");
        this.EquipT05d = this.EqyuipT05.getChild("EquipT05d");
        this.Cloth00 = this.BodyMain.getChild("Cloth00");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Ahoke01 = this.Head.getChild("Ahoke01");
        this.Ahoke02 = this.Ahoke01.getChild("Ahoke02");
        this.Ahoke03 = this.Ahoke02.getChild("Ahoke03");
        this.Ahoke04 = this.Ahoke03.getChild("Ahoke04");
        this.Ahoke05 = this.Ahoke04.getChild("Ahoke05");
        this.Ahoke06 = this.Ahoke05.getChild("Ahoke06");
        this.Ahoke01a = this.Head.getChild("Ahoke01a");
        this.Ahoke02a = this.Ahoke01a.getChild("Ahoke02a");
        this.Ahoke03a = this.Ahoke02a.getChild("Ahoke03a");
        this.Ahoke04a = this.Ahoke03a.getChild("Ahoke04a");
        this.Ahoke05a = this.Ahoke04a.getChild("Ahoke05a");
        this.Ahoke06a = this.Ahoke05a.getChild("Ahoke06a");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02 = this.Cloth01.getChild("Cloth02");
        this.Cloth03 = this.Cloth02.getChild("Cloth03");
        this.Cloth04 = this.Cloth03.getChild("Cloth04");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.EquipHandRing = this.ArmRight02.getChild("EquipHandRing");
        this.EquipTBase_2 = this.ArmRight02.getChild("EquipTBase_2");
        this.EqyuipT01_2 = this.EquipTBase_2.getChild("EqyuipT01_2");
        this.EqyuipT02_2 = this.EqyuipT01_2.getChild("EqyuipT02_2");
        this.EqyuipT03_2 = this.EqyuipT02_2.getChild("EqyuipT03_2");
        this.EquipT03a_2 = this.EqyuipT03_2.getChild("EquipT03a_2");
        this.EqyuipT04_2 = this.EqyuipT01_2.getChild("EqyuipT04_2");
        this.EqyuipT05_2 = this.EqyuipT04_2.getChild("EqyuipT05_2");
        this.EquipT05a_2 = this.EqyuipT05_2.getChild("EquipT05a_2");
        this.EquipT05b_2 = this.EqyuipT05_2.getChild("EquipT05b_2");
        this.EquipT05c_2 = this.EqyuipT05_2.getChild("EquipT05c_2");
        this.EquipT05d_2 = this.EqyuipT05_2.getChild("EquipT05d_2");
        this.RingBase = this.BodyMain.getChild("RingBase");
        this.Ring01 = this.RingBase.getChild("Ring01");
        this.Ring02 = this.Ring01.getChild("Ring02");
        this.Ring03Base = this.Ring02.getChild("Ring03Base");
        this.Ring03b = this.Ring03Base.getChild("Ring03b");
        this.Ring03c = this.Ring03Base.getChild("Ring03c");
        this.Ring03a = this.Ring03Base.getChild("Ring03a");
        this.Ring03e = this.Ring03Base.getChild("Ring03e");
        this.Ring03d = this.Ring03Base.getChild("Ring03d");
        this.Ring03f = this.Ring03Base.getChild("Ring03f");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultZ = this.ArmRight02.z;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.equipTBaseDefaultX = this.EquipTBase.x;
        this.equipTBaseDefaultY = this.EquipTBase.y;
        this.equipTBase2DefaultX = this.EquipTBase_2.x;
        this.equipTBase2DefaultY = this.EquipTBase_2.y;
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.cloth02DefaultY = this.Cloth02.y;
        this.cloth03DefaultY = this.Cloth03.y;
        this.cloth03DefaultZ = this.Cloth03.z;
        this.cloth04DefaultY = this.Cloth04.y;
        this.cloth04DefaultZ = this.Cloth04.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 113).addBox(-5.5F, -11F, -3.5F, 11F, 9F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0F, -0.0873F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 78).addBox(-5.5F, 0F, 0F, 11F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -4F, 0.2094F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 98).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 9F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, 5.5F, 2.4F, -0.1047F, 0F, -0.0524F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 98).mirror().addBox(-2.5F, 0F, 0F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, -2.5F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 98).addBox(-2.5F, 0F, -2.5F, 5F, 9F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, 5.5F, 2.4F, -0.1047F, 0F, 0.0524F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 98).addBox(-2.5F, 0F, 0F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, -2.5F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 99).mirror().addBox(-1F, -1F, -2F, 4F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -9.3F, -0.7F, 0.1396F, 0F, -0.2618F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(2, 99).mirror().addBox(-4F, 0F, -4F, 4F, 9F, 4F, new CubeDeformation(0F)), PartPose.offset(3F, 7F, 2F));

        PartDefinition EquipTBase = ArmLeft02.addOrReplaceChild("EquipTBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, 9F, -2F, 0.0873F, 0F, 0F));

        PartDefinition EqyuipT01 = EquipTBase.addOrReplaceChild("EqyuipT01", CubeListBuilder.create().texOffs(5, 0).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.5708F, 0F, 0F));

        PartDefinition EqyuipT02 = EqyuipT01.addOrReplaceChild("EqyuipT02", CubeListBuilder.create().texOffs(4, 0).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition EqyuipT03 = EqyuipT02.addOrReplaceChild("EqyuipT03", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition EquipT03a = EqyuipT03.addOrReplaceChild("EquipT03a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 1F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition EqyuipT04 = EqyuipT01.addOrReplaceChild("EqyuipT04", CubeListBuilder.create().texOffs(3, 4).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -6.9F, 0F));

        PartDefinition EqyuipT05 = EqyuipT04.addOrReplaceChild("EqyuipT05", CubeListBuilder.create().texOffs(2, 3).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6.9F, 0F, 0F, 0F, 0.0214F));

        PartDefinition EquipT05b = EqyuipT05.addOrReplaceChild("EquipT05b", CubeListBuilder.create().texOffs(14, 4).addBox(-0.5F, 0F, 0F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.9F, 1F, 0F, 0F, -1.5708F, 0F));

        PartDefinition EquipT05c = EqyuipT05.addOrReplaceChild("EquipT05c", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, 0F, 0F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.9F, 1F, 0F, 0F, 1.5708F, 0F));

        PartDefinition EquipT05a = EqyuipT05.addOrReplaceChild("EquipT05a", CubeListBuilder.create().texOffs(8, 7).addBox(-0.5F, 0F, 0F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 1F, 1.9F));

        PartDefinition EquipT05d = EqyuipT05.addOrReplaceChild("EquipT05d", CubeListBuilder.create().texOffs(0, 8).addBox(-0.5F, 0F, -2F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 1F, -1.9F));

        PartDefinition Cloth00 = BodyMain.addOrReplaceChild("Cloth00", CubeListBuilder.create().texOffs(56, 41).addBox(-6F, 0F, -2.9F, 12F, 8F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -11.3F, -1F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(2, 99).addBox(-2F, -2F, -2F, 4F, 2F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.2F, 0.0873F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, -1F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.6F, 0.1F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition Ahoke01 = Head.addOrReplaceChild("Ahoke01", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -15F, 0F, -2.0071F, 0.5236F, 0F));

        PartDefinition Ahoke02 = Ahoke01.addOrReplaceChild("Ahoke02", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, 1.0472F, -0.0524F, 0F));

        PartDefinition Ahoke03 = Ahoke02.addOrReplaceChild("Ahoke03", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, 0.7854F, 0.0524F, 0F));

        PartDefinition Ahoke04 = Ahoke03.addOrReplaceChild("Ahoke04", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0.4363F, 0.0524F, 0F));

        PartDefinition Ahoke05 = Ahoke04.addOrReplaceChild("Ahoke05", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, -0.1745F, 0.0873F, 0F));

        PartDefinition Ahoke06 = Ahoke05.addOrReplaceChild("Ahoke06", CubeListBuilder.create().texOffs(42, 90).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, -0.4363F, 0.0873F, 0F));

        PartDefinition Ahoke01a = Head.addOrReplaceChild("Ahoke01a", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, -1.5F, -2.2689F, -2.618F, 0F));

        PartDefinition Ahoke02a = Ahoke01a.addOrReplaceChild("Ahoke02a", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0.7854F, -0.0524F, 0F));

        PartDefinition Ahoke03a = Ahoke02a.addOrReplaceChild("Ahoke03a", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 1.0472F, 0.0524F, 0F));

        PartDefinition Ahoke04a = Ahoke03a.addOrReplaceChild("Ahoke04a", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0.4887F, 0.0524F, 0F));

        PartDefinition Ahoke05a = Ahoke04a.addOrReplaceChild("Ahoke05a", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, -0.2618F, 0.0873F, 0F));

        PartDefinition Ahoke06a = Ahoke05a.addOrReplaceChild("Ahoke06a", CubeListBuilder.create().texOffs(42, 89).mirror().addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, -0.5236F, 0.0873F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(80, 0).addBox(-7.5F, 0F, 0F, 15F, 12F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2618F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(80, 22).addBox(-8F, 0F, -5F, 16F, 11F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, 5.8F, -0.0873F, 0F, 0F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(0, 66).addBox(-6F, 0F, 0F, 12F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.3F, -4.3F, 0.0524F, 0F, 0F));

        PartDefinition Cloth02 = Cloth01.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(0, 53).addBox(-7F, 0F, 0F, 14F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -0.3F, 0.0873F, 0F, 0F));

        PartDefinition Cloth03 = Cloth02.addOrReplaceChild("Cloth03", CubeListBuilder.create().texOffs(0, 40).addBox(-8F, 0F, 0F, 16F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.3F, -0.2F, 0.0873F, 0F, 0F));

        PartDefinition Cloth04 = Cloth03.addOrReplaceChild("Cloth04", CubeListBuilder.create().texOffs(0, 26).addBox(-9F, 0F, 0F, 18F, 3F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -0.3F, -0.0524F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 99).addBox(-3F, -1F, -2F, 4F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -9.3F, -0.7F, 0.1396F, 0F, 0.6109F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(2, 99).addBox(0F, 0F, -4F, 4F, 9F, 4F, new CubeDeformation(0F)), PartPose.offset(-3F, 7F, 2F));

        PartDefinition EquipHandRing = ArmRight02.addOrReplaceChild("EquipHandRing", CubeListBuilder.create().texOffs(0, 91).addBox(-2.5F, 0F, -2.5F, 5F, 2F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, -2F));

        PartDefinition EquipTBase_2 = ArmRight02.addOrReplaceChild("EquipTBase_2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.6F, 9F, -2F, 0.0873F, 0F, 0F));

        PartDefinition EqyuipT01_2 = EquipTBase_2.addOrReplaceChild("EqyuipT01_2", CubeListBuilder.create().texOffs(5, 0).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.5708F, 0F, 0F));

        PartDefinition EqyuipT02_2 = EqyuipT01_2.addOrReplaceChild("EqyuipT02_2", CubeListBuilder.create().texOffs(4, 0).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition EqyuipT03_2 = EqyuipT02_2.addOrReplaceChild("EqyuipT03_2", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition EquipT03a_2 = EqyuipT03_2.addOrReplaceChild("EquipT03a_2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 1F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition EqyuipT04_2 = EqyuipT01_2.addOrReplaceChild("EqyuipT04_2", CubeListBuilder.create().texOffs(3, 4).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -6.9F, 0F));

        PartDefinition EqyuipT05_2 = EqyuipT04_2.addOrReplaceChild("EqyuipT05_2", CubeListBuilder.create().texOffs(2, 3).addBox(-2F, 0F, -2F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6.9F, 0F, 0F, 0F, 0.0214F));

        PartDefinition EquipT05a_2 = EqyuipT05_2.addOrReplaceChild("EquipT05a_2", CubeListBuilder.create().texOffs(8, 7).addBox(-0.5F, 0F, 0F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 1F, 1.9F));

        PartDefinition EquipT05b_2 = EqyuipT05_2.addOrReplaceChild("EquipT05b_2", CubeListBuilder.create().texOffs(14, 4).addBox(-0.5F, 0F, 0F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.9F, 1F, 0F, 0F, -1.5708F, 0F));

        PartDefinition EquipT05c_2 = EqyuipT05_2.addOrReplaceChild("EquipT05c_2", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, 0F, 0F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.9F, 1F, 0F, 0F, 1.5708F, 0F));

        PartDefinition EquipT05d_2 = EqyuipT05_2.addOrReplaceChild("EquipT05d_2", CubeListBuilder.create().texOffs(0, 8).addBox(-0.5F, 0F, -2F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 1F, -1.9F));

        PartDefinition RingBase = BodyMain.addOrReplaceChild("RingBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -16F, -0.4F));

        PartDefinition Ring01 = RingBase.addOrReplaceChild("Ring01", CubeListBuilder.create().texOffs(62, 0).addBox(-4F, 0F, -0.5F, 8F, 10F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 4.3F, 0F, -0.8203F, 1.501F, 0F));

        PartDefinition Ring02 = Ring01.addOrReplaceChild("Ring02", CubeListBuilder.create().texOffs(62, 13).addBox(-4F, -9F, -0.5F, 8F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 8.5F, 0.2F, 0.2276F, -0.0387F, -2.7925F));

        PartDefinition Ring03Base = Ring02.addOrReplaceChild("Ring03Base", CubeListBuilder.create().texOffs(0, 0).addBox(2F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -10F, 1.7F, -0.6109F, 0.1745F, -0.1047F));

        PartDefinition Ring03b = Ring03Base.addOrReplaceChild("Ring03b", CubeListBuilder.create().texOffs(36, 0).addBox(0F, -2F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.9F, 2F, 0F, 0F, 0F, -1.5708F));

        PartDefinition Ring03c = Ring03Base.addOrReplaceChild("Ring03c", CubeListBuilder.create().texOffs(36, 0).addBox(0F, -2F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(-4F, -8.9F, 0F));

        PartDefinition Ring03a = Ring03Base.addOrReplaceChild("Ring03a", CubeListBuilder.create().texOffs(36, 0).addBox(0F, -2F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Ring03e = Ring03Base.addOrReplaceChild("Ring03e", CubeListBuilder.create().texOffs(36, 0).addBox(0F, -2F, -1F, 1F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -10.8F, 0F, 0F, 0F, 0.0873F));

        PartDefinition Ring03d = Ring03Base.addOrReplaceChild("Ring03d", CubeListBuilder.create().texOffs(36, 0).addBox(0F, -2F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, -1.9F, 0F, 0F, 0F, -1.5708F));

        PartDefinition Ring03f = Ring03Base.addOrReplaceChild("Ring03f", CubeListBuilder.create().texOffs(36, 0).addBox(-1F, -2F, -1F, 1F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, -10.8F, 0F, 0F, 0F, -0.0873F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 2F, 0F, -0.0873F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.2F, 0.0873F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, -1F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        applyFaceAndMouth(entity);

        boolean inDeadPose = entity != null && entity.isInDeadPose();

        if (inDeadPose) {
            this.applyDeadPose();
            this.syncGlowParts();
            return;
        }

        this.applyBasePose(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.applySpecialPoseAdjustments(entity, limbSwing, limbSwingAmount, ageInTicks);
        this.syncGlowParts();
    }

    private void resetOffsets() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
        this.poseTranslateZ = 0.0F;

        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.z = this.armRight02DefaultZ;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.EquipTBase.x = this.equipTBaseDefaultX;
        this.EquipTBase.y = this.equipTBaseDefaultY;
        this.EquipTBase_2.x = this.equipTBase2DefaultX;
        this.EquipTBase_2.y = this.equipTBase2DefaultY;
        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.Cloth02.y = this.cloth02DefaultY;
        this.Cloth03.y = this.cloth03DefaultY;
        this.Cloth03.z = this.cloth03DefaultZ;
        this.Cloth04.y = this.cloth04DefaultY;
        this.Cloth04.z = this.cloth04DefaultZ;
    }

    private void applyEquipVisibility(T entity) {
        boolean showHandRing = entity.getEquipFlag(org.trp.shincolle.entity.EntitySSNH.EQUIP_HAND_RING);
        boolean showRingBase = entity.getEquipFlag(org.trp.shincolle.entity.EntitySSNH.EQUIP_RING_BASE);
        boolean showTorpedo = entity.getEquipFlag(org.trp.shincolle.entity.EntitySSNH.EQUIP_TORPEDO);

        this.EquipHandRing.visible = showHandRing;
        this.RingBase.visible = showRingBase;
        this.EquipTBase.visible = showTorpedo;
        this.EquipTBase_2.visible = false;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = -0.15F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.BodyMain.xRot = 1.6F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.21F;
        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.Cloth03.xRot = 0.087F;
        this.Cloth03.y = this.cloth03DefaultY;
        this.Cloth03.z = this.cloth03DefaultZ;
        this.Cloth04.xRot = -0.052F;
        this.Cloth04.y = this.cloth04DefaultY;
        this.Cloth04.z = this.cloth04DefaultZ;
        this.Hair01.xRot = 0.35F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -0.2F;
        this.Hair02.zRot = 0.0F;

        this.ArmLeft01.xRot = -3.0F;
        this.ArmLeft01.yRot = -0.6981F;
        this.ArmLeft01.zRot = 0.08F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;
        this.ArmRight01.xRot = -3.0F;
        this.ArmRight01.yRot = 0.6981F;
        this.ArmRight01.zRot = -0.08F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.xRot = -0.3F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = -0.05F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight01.xRot = -0.3F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.05F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;

        this.EquipTBase.xRot = 0.8F;
        this.EquipTBase.yRot = 0.0F;
        this.EquipTBase.zRot = 1.2F;
        this.EquipTBase.x = this.equipTBaseDefaultX;
        this.EquipTBase.y = this.equipTBaseDefaultY;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.4F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.8F + limbSwing * 0.5F);
        float angleX3 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 1.2F + limbSwing * 0.5F);
        float angleX4 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 1.6F + limbSwing * 0.5F);
        float angleX5 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 2.0F + limbSwing * 0.5F);
        float angleX6 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 2.4F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.5F;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY = angleX * 0.025F + 0.025F;
        }

        float addk1 = angleAdd1 * 0.6F - 0.1F;
        float addk2 = angleAdd2 * 0.6F - 0.1F;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.4F;
        this.Head.zRot = 0.0F;
        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;

        this.Ahoke01.xRot = angleX1 * 0.07F - 2.01F;
        this.Ahoke01.yRot = 0.52F;
        this.Ahoke01.zRot = 0.0F;
        this.Ahoke02.xRot = -angleX2 * 0.09F + 1.04F;
        this.Ahoke03.xRot = angleX3 * 0.15F + 0.78F;
        this.Ahoke04.xRot = -angleX4 * 0.1F + 0.44F;
        this.Ahoke05.xRot = -angleX5 * 0.15F - 0.17F;
        this.Ahoke06.xRot = angleX6 * 0.18F - 0.31F;

        this.Ahoke01a.xRot = angleX1 * 0.07F - 2.27F;
        this.Ahoke01a.yRot = -2.62F;
        this.Ahoke01a.zRot = 0.0F;
        this.Ahoke02a.xRot = -angleX2 * 0.09F + 0.79F;
        this.Ahoke03a.xRot = angleX3 * 0.15F + 1.05F;
        this.Ahoke04a.xRot = -angleX4 * 0.1F + 0.41F;
        this.Ahoke05a.xRot = -angleX5 * 0.15F - 0.3F;
        this.Ahoke06a.xRot = angleX6 * 0.18F - 0.25F;

        this.BodyMain.xRot = -0.0873F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.21F;
        this.Cloth02.xRot = 0.087F;
        this.Cloth03.xRot = 0.087F;
        this.Cloth04.xRot = -0.052F;

        this.Hair01.xRot = angleX * 0.03F + 0.26F + headX;
        this.Hair01.zRot = headZ;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.087F + headX;
        this.Hair02.zRot = headZ;

        this.ArmLeft01.xRot = angleAdd2 * 0.8F - 0.05F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = angleX * 0.025F - 0.3F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.8F + 0.26F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -angleX * 0.025F + 0.4F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = -0.035F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.035F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.EquipTBase.xRot = 0.15F;
        this.EquipTBase.yRot = 0.0F;
        this.EquipTBase.zRot = 0.0F;
        this.EquipTBase.x = this.equipTBaseDefaultX + (-0.13F * OFFSET_SCALE);
        this.EquipTBase.y = this.equipTBaseDefaultY;

        this.EquipTBase_2.xRot = 0.15F;
        this.EquipTBase_2.yRot = 0.0F;
        this.EquipTBase_2.zRot = 0.0F;
        this.EquipTBase_2.x = this.equipTBase2DefaultX;
        this.EquipTBase_2.y = this.equipTBase2DefaultY;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity != null ? entity.getIsSprinting() : limbSwingAmount > 0.9F;
        boolean isSitting = entity.getIsSitting() || isPassenger;
        boolean showTorpedo = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntitySSNH.EQUIP_TORPEDO);

        if (isSprinting) {
            if (isPassenger) {
                if (limbSwingAmount > 0.5F) {
                    this.Head.xRot += 0.4F;
                    this.Hair01.xRot += 0.1F;
                    this.Hair02.xRot -= 0.2F;
                }
            } else {
                this.poseTranslateY -= 0.06F;
                this.Head.xRot -= 1.3F;
                this.Hair01.xRot += 0.6F;
                this.Hair02.xRot += 0.5F;
                this.Ahoke01.xRot += 0.38F;
                this.Ahoke01.yRot = 0.7F;
                this.Ahoke01.zRot = 0.4F;
                this.Ahoke01a.yRot = -2.5F;
                this.Ahoke01a.zRot = -0.2F;
            }
            this.BodyMain.xRot = 1.5F;
            this.ArmLeft01.xRot = -2.9F;
            this.ArmLeft01.zRot = -0.22F;
            this.ArmRight01.xRot = -2.9F;
            this.ArmRight01.zRot = 0.22F;
            this.LegLeft01.zRot = 0.05F;
            this.LegRight01.zRot = -0.05F;

            if (showTorpedo) {
                this.EquipTBase.xRot = 1.42F;
                this.EquipTBase.yRot = 0.0F;
                this.EquipTBase.zRot = -0.22F;
                this.EquipTBase.x = this.equipTBaseDefaultX + (0.17F * OFFSET_SCALE);
                this.EquipTBase.y = this.equipTBaseDefaultY + (0.64F * OFFSET_SCALE);
                this.EquipTBase_2.visible = true;
                this.EquipTBase_2.xRot = 1.42F;
                this.EquipTBase_2.yRot = 0.0F;
                this.EquipTBase_2.zRot = 0.22F;
                this.EquipTBase_2.x = this.equipTBase2DefaultX + (-0.17F * OFFSET_SCALE);
                this.EquipTBase_2.y = this.equipTBase2DefaultY + (0.64F * OFFSET_SCALE);
            } else {
                this.EquipTBase_2.visible = false;
            }
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.Cloth03.xRot = -0.34F;
            this.Cloth03.y = this.cloth03DefaultY + (-0.2F * OFFSET_SCALE);
            this.Cloth03.z = this.cloth03DefaultZ + (0.03F * OFFSET_SCALE);
            this.Cloth04.xRot = -0.27F;
            this.ArmLeft01.xRot = -0.35F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.35F;
            this.ArmRight01.zRot = -0.2618F;

            this.LegLeft01.xRot -= 0.94F;
            this.LegRight01.xRot -= 0.94F;
            this.LegLeft01.zRot = 0.2F;
            this.LegRight01.zRot = -0.2F;
            this.Hair01.xRot = this.Hair01.xRot * 0.5F + 0.4F;
            this.Hair02.xRot = this.Hair02.xRot * 0.75F + 0.25F;

            this.EquipTBase.xRot = 0.48F;
            this.EquipTBase.yRot = 1.55F;
            this.EquipTBase.zRot = 0.0F;
            this.EquipTBase.x = this.equipTBaseDefaultX;
            this.EquipTBase.y = this.equipTBaseDefaultY;
        }

        if (isSitting) {
            if (!isPassenger && entity != null && (entity.tickCount & 0x1FF) > 256) {
                this.poseTranslateY += -angleX * 0.05F - 0.1F;
                this.Head.xRot *= 0.5F;
                this.Head.yRot *= 0.75F;
                this.Head.xRot += 0.5F;
                this.BodyMain.xRot = 1.6F;
                this.Cloth03.xRot = -0.33F;
                this.Cloth03.y = this.cloth03DefaultY + (-0.23F * OFFSET_SCALE);
                this.Cloth04.xRot = -0.12F;
                this.Cloth04.y = this.cloth04DefaultY + (-0.16F * OFFSET_SCALE);
                this.Ahoke01.xRot += 0.38F;
                this.Ahoke01.yRot = 0.8F;
                this.Ahoke01.zRot = 0.4F;
                this.Hair01.xRot -= 0.2F;
                this.Hair02.xRot -= 0.25F;
                this.ArmLeft01.xRot = -1.5F;
                this.ArmLeft01.zRot = -2.3F;
                this.ArmRight01.xRot = -1.5F;
                this.ArmRight01.zRot = 2.3F;
                this.LegLeft01.xRot = -1.8F;
                this.LegRight01.xRot = -1.8F;
                this.LegLeft01.yRot = -0.1F - angleX * 0.02F;
                this.LegRight01.yRot = 0.1F + angleX * 0.02F;
            } else if (!isPassenger && entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.26F * 3.5F;
                this.Head.xRot = 0.4F;
                this.Cloth03.xRot = -0.64F;
                this.Cloth03.y = this.cloth03DefaultY + (-0.17F * OFFSET_SCALE);
                this.Cloth03.z = this.cloth03DefaultZ;
                this.Cloth04.xRot = 0.29F;
                this.Cloth04.y = this.cloth04DefaultY + (-0.04F * OFFSET_SCALE);
                this.Cloth04.z = this.cloth04DefaultZ + (0.02F * OFFSET_SCALE);
                this.Hair01.xRot -= 0.2F;
                this.Hair02.xRot -= 0.15F;
                this.Ahoke01.xRot -= 0.1F;
                this.ArmLeft01.xRot = 0.4F;
                this.ArmLeft01.yRot = -2.9670596F;
                this.ArmLeft01.zRot = -2.62F;
                this.ArmLeft02.xRot = 0.0F;
                this.ArmLeft02.yRot = 0.0F;
                this.ArmLeft02.zRot = 1.0F;
                this.ArmRight01.xRot = 0.5235988F;
                this.ArmRight01.yRot = 2.9670596F;
                this.ArmRight01.zRot = 2.62F;
                this.ArmRight02.xRot = 0.0F;
                this.ArmRight02.yRot = 0.0F;
                this.ArmRight02.zRot = -1.0F;
                this.LegLeft01.xRot = -2.4130921F;
                this.LegRight01.xRot = -2.268928F;
                this.LegLeft01.yRot = 0.0F;
                this.LegLeft01.zRot = -0.27314404F;
                this.LegLeft02.xRot = 1.4570009F;
                this.LegLeft02.yRot = 0.0F;
                this.LegLeft02.zRot = 0.0F;
                this.LegRight01.yRot = 0.0F;
                this.LegRight01.zRot = 0.22759093F;
                this.LegRight02.xRot = 1.0471976F;
                this.LegRight02.yRot = 0.0F;
                this.LegRight02.zRot = 0.0F;
                this.EquipTBase.visible = false;
                this.EquipTBase_2.visible = false;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                this.Head.xRot -= 0.7F;
                this.BodyMain.xRot = 0.35F;
                this.Hair01.xRot += 0.3F;
                this.Hair02.xRot += 0.3F;
                this.Cloth03.xRot = -0.32F;
                this.Cloth03.y = this.cloth03DefaultY + (-0.05F * OFFSET_SCALE);
                this.Cloth04.xRot = -0.21F;

                this.ArmLeft01.xRot = -0.5235F;
                this.ArmLeft01.yRot = 0.0F;
                this.ArmLeft01.zRot = 0.349F;
                this.ArmRight01.xRot = -0.5235F;
                this.ArmRight01.yRot = 0.0F;
                this.ArmRight01.zRot = -0.349F;

                this.LegLeft01.xRot = -1.4486F;
                this.LegRight01.xRot = -1.4486F;
                this.LegLeft01.yRot = -0.5235F;
                this.LegLeft01.zRot = -1.3962F;
                this.LegRight01.yRot = 0.5235F;
                this.LegRight01.zRot = 1.3962F;

                this.LegLeft02.xRot = 2.1816F;
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.37F * OFFSET_SCALE);
                this.LegRight02.xRot = 2.1816F;
                this.LegRight02.z = this.legRight02DefaultZ + (0.37F * OFFSET_SCALE);
            }
        }

        if (entity != null && entity.getAttackTick() > 40) {
            this.poseTranslateY += 0.08F;
            this.Head.xRot -= 1.0472F;
            this.BodyMain.xRot = 1.7F;
            this.Butt.xRot = -0.8378F;
            this.Cloth03.xRot -= 0.7F;
            this.Cloth04.xRot -= 1.1F;
            this.ArmLeft01.xRot = -0.9F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -1.9F;
            this.ArmRight01.zRot = -0.2618F;
            this.EquipTBase.xRot = -1.4F;
            this.LegLeft01.xRot -= 0.7F;
            this.LegRight01.xRot -= 0.7F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
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
        this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
