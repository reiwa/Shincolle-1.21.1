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

public class ModelMidwayHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "midway_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelMidwayHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelMidwayHime");
    private static final float SIT_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelMidwayHime");
    private static final float RIDING_TRANSLATE_Y = LegacyPoseOffsets.ridingY("ModelMidwayHime");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft01;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart EquipSR01;
    private final ModelPart EquipSR01b;
    private final ModelPart EquipSR01c;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart HeadHL;
    private final ModelPart HeadHR;
    private final ModelPart HairU01;
    private final ModelPart Ahoke;
    private final ModelPart HairR01;
    private final ModelPart HairL01;
    private final ModelPart HairR02;
    private final ModelPart HairL02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart HeadHL2;
    private final ModelPart HeadHL3;
    private final ModelPart HeadHR2;
    private final ModelPart HeadHR3;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft02;
    private final ModelPart Skirt02;
    private final ModelPart Skirt03;
    private final ModelPart LegRight02;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight02a;
    private final ModelPart ArmRight02b;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft02a;
    private final ModelPart ArmLeft02b;
    private final ModelPart Collar02;
    private final ModelPart Collar03a1;
    private final ModelPart Collar03a2;
    private final ModelPart Collar03a3;
    private final ModelPart Collar03a3_1;
    private final ModelPart Collar03a4;
    private final ModelPart Collar03a5;
    private final ModelPart Collar03a6;
    private final ModelPart Collar03a7;
    private final ModelPart Collar03a8;
    private final ModelPart Collar03a9;
    private final ModelPart Collar03a10;
    private final ModelPart Collar03a11;
    private final ModelPart Collar03a12;
    private final ModelPart Collar03a13;
    private final ModelPart Collar03a14;
    private final ModelPart Collar03a15;
    private final ModelPart Collar03b1;
    private final ModelPart Collar03b2;
    private final ModelPart Collar03b3;
    private final ModelPart Collar03b3_1;
    private final ModelPart Collar03b4;
    private final ModelPart Collar03b5;
    private final ModelPart Collar03b6;
    private final ModelPart Collar03b7;
    private final ModelPart Collar03b8;
    private final ModelPart Collar03b9;
    private final ModelPart Collar03b10;
    private final ModelPart Collar03b11;
    private final ModelPart Collar03b12;
    private final ModelPart Collar03b13;
    private final ModelPart Collar03b14;
    private final ModelPart Collar03b15;
    private final ModelPart EquipSR02;
    private final ModelPart EquipSR03;
    private final ModelPart EquipSR04;
    private final ModelPart EquipSR05;
    private final ModelPart EquipSR02b;
    private final ModelPart EquipSR03b;
    private final ModelPart EquipSR04b;
    private final ModelPart EquipSR02c;
    private final ModelPart EquipSR03c;
    private final ModelPart EquipSR04c;
    private final ModelPart EquipSR05c;
    private final ModelPart Collar01;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowBodyMain2;
    private final ModelPart GlowBodyMain2a;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float skirt01DefaultY;
    private final float skirt01DefaultZ;
    private final float skirt02DefaultY;
    private final float skirt03DefaultY;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultZ;
    private final float legLeft01DefaultY;
    private final float legLeft01DefaultZ;
    private final float legLeft02DefaultX;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight01DefaultY;
    private final float legRight01DefaultZ;
    private final float legRight02DefaultX;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;
    private final float equipSR01DefaultZ;
    private final float equipSR02DefaultZ;
    private final float equipSR03DefaultZ;
    private final float equipSR04DefaultZ;
    private final float equipSR05DefaultZ;
    private final float equipSR01bDefaultZ;
    private final float equipSR02bDefaultZ;
    private final float equipSR03bDefaultZ;
    private final float equipSR04bDefaultZ;
    private final float equipSR01cDefaultZ;
    private final float equipSR02cDefaultZ;
    private final float equipSR03cDefaultZ;
    private final float equipSR04cDefaultZ;
    private final float equipSR05cDefaultZ;

    public ModelMidwayHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight02a = this.ArmRight02.getChild("ArmRight02a");
        this.ArmRight02b = this.ArmRight02a.getChild("ArmRight02b");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft02a = this.ArmLeft02.getChild("ArmLeft02a");
        this.ArmLeft02b = this.ArmLeft02a.getChild("ArmLeft02b");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.Skirt03 = this.Skirt02.getChild("Skirt03");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.Collar01 = this.GlowNeck.getChild("Collar01");
        this.Collar02 = this.Collar01.getChild("Collar02");
        this.Collar03a2 = this.Collar02.getChild("Collar03a2");
        this.Collar03b2 = this.Collar03a2.getChild("Collar03b2");
        this.Collar03a8 = this.Collar02.getChild("Collar03a8");
        this.Collar03b8 = this.Collar03a8.getChild("Collar03b8");
        this.Collar03a7 = this.Collar02.getChild("Collar03a7");
        this.Collar03b7 = this.Collar03a7.getChild("Collar03b7");
        this.Collar03a15 = this.Collar02.getChild("Collar03a15");
        this.Collar03b15 = this.Collar03a15.getChild("Collar03b15");
        this.Collar03a9 = this.Collar02.getChild("Collar03a9");
        this.Collar03b9 = this.Collar03a9.getChild("Collar03b9");
        this.Collar03a10 = this.Collar02.getChild("Collar03a10");
        this.Collar03b10 = this.Collar03a10.getChild("Collar03b10");
        this.Collar03a12 = this.Collar02.getChild("Collar03a12");
        this.Collar03b12 = this.Collar03a12.getChild("Collar03b12");
        this.Collar03a11 = this.Collar02.getChild("Collar03a11");
        this.Collar03b11 = this.Collar03a11.getChild("Collar03b11");
        this.Collar03a13 = this.Collar02.getChild("Collar03a13");
        this.Collar03b13 = this.Collar03a13.getChild("Collar03b13");
        this.Collar03a1 = this.Collar02.getChild("Collar03a1");
        this.Collar03b1 = this.Collar03a1.getChild("Collar03b1");
        this.Collar03a4 = this.Collar02.getChild("Collar03a4");
        this.Collar03b4 = this.Collar03a4.getChild("Collar03b4");
        this.Collar03a3 = this.Collar02.getChild("Collar03a3");
        this.Collar03b3 = this.Collar03a3.getChild("Collar03b3");
        this.Collar03a14 = this.Collar02.getChild("Collar03a14");
        this.Collar03b14 = this.Collar03a14.getChild("Collar03b14");
        this.Collar03a3_1 = this.Collar02.getChild("Collar03a3_1");
        this.Collar03b3_1 = this.Collar03a3_1.getChild("Collar03b3_1");
        this.Collar03a6 = this.Collar02.getChild("Collar03a6");
        this.Collar03b6 = this.Collar03a6.getChild("Collar03b6");
        this.Collar03a5 = this.Collar02.getChild("Collar03a5");
        this.Collar03b5 = this.Collar03a5.getChild("Collar03b5");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.HeadHL = this.GlowHead.getChild("HeadHL");
        this.HeadHL2 = this.HeadHL.getChild("HeadHL2");
        this.HeadHL3 = this.HeadHL2.getChild("HeadHL3");
        this.HeadHR = this.GlowHead.getChild("HeadHR");
        this.HeadHR2 = this.HeadHR.getChild("HeadHR2");
        this.HeadHR3 = this.HeadHR2.getChild("HeadHR3");
        this.initFaceParts(this.GlowHead);
        this.GlowBodyMain2 = root.getChild("GlowBodyMain2");
        this.GlowBodyMain2a = this.GlowBodyMain2.getChild("GlowBodyMain2a");
        this.EquipSR01 = this.GlowBodyMain2a.getChild("EquipSR01");
        this.EquipSR02 = this.EquipSR01.getChild("EquipSR02");
        this.EquipSR03 = this.EquipSR02.getChild("EquipSR03");
        this.EquipSR04 = this.EquipSR03.getChild("EquipSR04");
        this.EquipSR05 = this.EquipSR04.getChild("EquipSR05");
        this.EquipSR01b = this.GlowBodyMain2a.getChild("EquipSR01b");
        this.EquipSR02b = this.EquipSR01b.getChild("EquipSR02b");
        this.EquipSR03b = this.EquipSR02b.getChild("EquipSR03b");
        this.EquipSR04b = this.EquipSR03b.getChild("EquipSR04b");
        this.EquipSR01c = this.GlowBodyMain2a.getChild("EquipSR01c");
        this.EquipSR02c = this.EquipSR01c.getChild("EquipSR02c");
        this.EquipSR03c = this.EquipSR02c.getChild("EquipSR03c");
        this.EquipSR04c = this.EquipSR03c.getChild("EquipSR04c");
        this.EquipSR05c = this.EquipSR04c.getChild("EquipSR05c");
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.skirt01DefaultY = this.Skirt01.y;
        this.skirt01DefaultZ = this.Skirt01.z;
        this.skirt02DefaultY = this.Skirt02.y;
        this.skirt03DefaultY = this.Skirt03.y;
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultZ = this.ArmRight02.z;
        this.legLeft01DefaultY = this.LegLeft01.y;
        this.legLeft01DefaultZ = this.LegLeft01.z;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight01DefaultY = this.LegRight01.y;
        this.legRight01DefaultZ = this.LegRight01.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.equipSR01DefaultZ = this.EquipSR01.z;
        this.equipSR02DefaultZ = this.EquipSR02.z;
        this.equipSR03DefaultZ = this.EquipSR03.z;
        this.equipSR04DefaultZ = this.EquipSR04.z;
        this.equipSR05DefaultZ = this.EquipSR05.z;
        this.equipSR01bDefaultZ = this.EquipSR01b.z;
        this.equipSR02bDefaultZ = this.EquipSR02b.z;
        this.equipSR03bDefaultZ = this.EquipSR03b.z;
        this.equipSR04bDefaultZ = this.EquipSR04b.z;
        this.equipSR01cDefaultZ = this.EquipSR01c.z;
        this.equipSR02cDefaultZ = this.EquipSR02c.z;
        this.equipSR03cDefaultZ = this.EquipSR03c.z;
        this.equipSR04cDefaultZ = this.EquipSR04c.z;
        this.equipSR05cDefaultZ = this.EquipSR05c.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 71).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, -0.0873F, 0F, 0.2618F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 54).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition ArmRight02a = ArmRight02.addOrReplaceChild("ArmRight02a", CubeListBuilder.create().texOffs(75, 47).mirror().addBox(-3F, 0F, -3F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 3F, -2.5F, 0.0524F, 0F, 0F));

        PartDefinition ArmRight02b = ArmRight02a.addOrReplaceChild("ArmRight02b", CubeListBuilder.create().texOffs(78, 37).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, 0.2F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(-3.5F, 0F, 0F, 7F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.4F, -8.5F, -3.7F, -0.8727F, -0.0873F, -0.0698F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 71).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.3491F, 0F, -0.2618F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 54).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition ArmLeft02a = ArmLeft02.addOrReplaceChild("ArmLeft02a", CubeListBuilder.create().texOffs(75, 47).addBox(-3F, 0F, -3F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 3F, -2.5F, 0.0524F, 0F, 0F));

        PartDefinition ArmLeft02b = ArmLeft02a.addOrReplaceChild("ArmLeft02b", CubeListBuilder.create().texOffs(78, 37).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, 0.2F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 35).addBox(-3.5F, 0F, 0F, 7F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -8.5F, -3.8F, -0.8727F, 0.0873F, 0.0698F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(24, 80).addBox(-2.5F, -3F, -2.9F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -0.8F, -0.7F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(0, 10).addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 3F, -5.5F, -0.192F, -0.1745F, -0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(0, 10).addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 0F, 0.1745F, 0F, 0.0873F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(106, 31).addBox(0F, -6F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -7F, -6F, 0.2094F, 0.6981F, 0F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 3F, -5.5F, -0.192F, 0.1745F, 0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 10F, 0F, 0.1745F, 0F, -0.0524F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(14, 0).addBox(-7.5F, 0F, 0F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2618F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(62, 0).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, -0.0873F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(26, 28).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -0.1F, -0.0524F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 88).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 68).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.0873F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(128, 0).addBox(-8.5F, 0F, -8.5F, 17F, 6F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, 1.5F, -0.0873F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(128, 17).addBox(-10.5F, 0F, -6.5F, 21F, 6F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.5F, -2.7F, -0.0873F, 0F, 0F));

        PartDefinition Skirt03 = Skirt02.addOrReplaceChild("Skirt03", CubeListBuilder.create().texOffs(128, 37).addBox(-13F, 0F, -7.5F, 26F, 6F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.5F, 0.3F, -0.0524F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 68).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 47).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Collar01 = GlowNeck.addOrReplaceChild("Collar01", CubeListBuilder.create().texOffs(66, 25).addBox(-6F, -2F, -4F, 12F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.9F, -1.2F, 0.035F, 0F, 0F));

        PartDefinition Collar02 = Collar01.addOrReplaceChild("Collar02", CubeListBuilder.create().texOffs(128, 60).addBox(-7F, -1.5F, -5.7F, 14F, 3F, 11F, new CubeDeformation(0F)), PartPose.offset(0F, -2.5F, -1F));

        PartDefinition Collar03a2 = Collar02.addOrReplaceChild("Collar03a2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 0.6F, -3.5F));

        PartDefinition Collar03b2 = Collar03a2.addOrReplaceChild("Collar03b2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8727F, 0F, 0F));

        PartDefinition Collar03a8 = Collar02.addOrReplaceChild("Collar03a8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, 0.2F, 3.4F, -1.7453F, -2.618F, 0.0524F));

        PartDefinition Collar03b8 = Collar03a8.addOrReplaceChild("Collar03b8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -3F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.7679F, 0F, 0F));

        PartDefinition Collar03a7 = Collar02.addOrReplaceChild("Collar03a7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.4F, 0.1F, 2.7F, -1.7453F, -2.2689F, 0F));

        PartDefinition Collar03b7 = Collar03a7.addOrReplaceChild("Collar03b7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8378F, 0F, 0F));

        PartDefinition Collar03a15 = Collar02.addOrReplaceChild("Collar03a15", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, 0.2F, -2.1F, -1.6581F, 0.8029F, 0.0873F));

        PartDefinition Collar03b15 = Collar03a15.addOrReplaceChild("Collar03b15", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -3F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8378F, 0F, 0F));

        PartDefinition Collar03a9 = Collar02.addOrReplaceChild("Collar03a9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.4F, 0.4F, 2.6F, -1.7453F, -3.0369F, 0.0524F));

        PartDefinition Collar03b9 = Collar03a9.addOrReplaceChild("Collar03b9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.6981F, 0F, 0F));

        PartDefinition Collar03a10 = Collar02.addOrReplaceChild("Collar03a10", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.4F, 0.4F, 2.6F, -1.7453F, 3.0369F, -0.0524F));

        PartDefinition Collar03b10 = Collar03a10.addOrReplaceChild("Collar03b10", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -3F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.6981F, 0F, 0F));

        PartDefinition Collar03a12 = Collar02.addOrReplaceChild("Collar03a12", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.4F, 0.1F, 2.7F, -1.7453F, 2.2689F, 0F));

        PartDefinition Collar03b12 = Collar03a12.addOrReplaceChild("Collar03b12", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -3F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8378F, 0F, 0F));

        PartDefinition Collar03a11 = Collar02.addOrReplaceChild("Collar03a11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, 0.2F, 3.4F, -1.7453F, 2.618F, -0.0524F));

        PartDefinition Collar03b11 = Collar03a11.addOrReplaceChild("Collar03b11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.7679F, 0F, 0F));

        PartDefinition Collar03a13 = Collar02.addOrReplaceChild("Collar03a13", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.6F, 0.1F, 1.7F, -1.7453F, 1.6057F, 0F));

        PartDefinition Collar03b13 = Collar03a13.addOrReplaceChild("Collar03b13", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -3F, 3F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.9948F, 0F, 0F));

        PartDefinition Collar03a1 = Collar02.addOrReplaceChild("Collar03a1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(1.3F, 0.6F, -3.5F));

        PartDefinition Collar03b1 = Collar03a1.addOrReplaceChild("Collar03b1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -3F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8727F, 0F, 0F));

        PartDefinition Collar03a4 = Collar02.addOrReplaceChild("Collar03a4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, 0.2F, -2.1F, -1.6581F, -0.8029F, -0.0873F));

        PartDefinition Collar03b4 = Collar03a4.addOrReplaceChild("Collar03b4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8378F, 0F, 0F));

        PartDefinition Collar03a3 = Collar02.addOrReplaceChild("Collar03a3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.3F, 0.5F, -3.5F, -2.0071F, -0.2094F, 0.0698F));

        PartDefinition Collar03b3 = Collar03a3.addOrReplaceChild("Collar03b3", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -2F, 2F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.9599F, 0F, 0F));

        PartDefinition Collar03a14 = Collar02.addOrReplaceChild("Collar03a14", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.6F, 0.1F, -1.3F, -1.7453F, 1.4312F, 0F));

        PartDefinition Collar03b14 = Collar03a14.addOrReplaceChild("Collar03b14", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8378F, 0F, 0F));

        PartDefinition Collar03a3_1 = Collar02.addOrReplaceChild("Collar03a3_1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.3F, 0.5F, -3.5F, -2.0071F, 0.2094F, -0.0698F));

        PartDefinition Collar03b3_1 = Collar03a3_1.addOrReplaceChild("Collar03b3_1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, -2F, 2F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.9599F, 0F, 0F));

        PartDefinition Collar03a6 = Collar02.addOrReplaceChild("Collar03a6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.6F, 0.1F, 1.7F, -1.7453F, -1.6057F, 0F));

        PartDefinition Collar03b6 = Collar03a6.addOrReplaceChild("Collar03b6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3F, 3F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 1.5F, -0.9948F, 0F, 0F));

        PartDefinition Collar03a5 = Collar02.addOrReplaceChild("Collar03a5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.6F, 0.1F, -1.3F, -1.7453F, -1.4312F, 0F));

        PartDefinition Collar03b5 = Collar03a5.addOrReplaceChild("Collar03b5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -3F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.5F, -0.8378F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -0.8F, -0.7F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition HeadHL = GlowHead.addOrReplaceChild("HeadHL", CubeListBuilder.create().texOffs(40, 104).mirror().addBox(0F, -2.5F, -2.5F, 3F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.4F, -10.6F, 0.8F, -0.7854F, -0.1745F, -0.384F));

        PartDefinition HeadHL2 = HeadHL.addOrReplaceChild("HeadHL2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -2F, -2F, 1F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(3F, 0F, 0F));

        PartDefinition HeadHL3 = HeadHL2.addOrReplaceChild("HeadHL3", CubeListBuilder.create().texOffs(44, 70).addBox(0F, -1.5F, -1.5F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(1F, 0F, 0F));

        PartDefinition HeadHR = GlowHead.addOrReplaceChild("HeadHR", CubeListBuilder.create().texOffs(40, 104).addBox(-3F, -2.5F, -2.5F, 3F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.4F, -10.6F, 0.8F, -0.7854F, 0.1745F, 0.384F));

        PartDefinition HeadHR2 = HeadHR.addOrReplaceChild("HeadHR2", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -2F, -2F, 1F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(-3F, 0F, 0F));

        PartDefinition HeadHR3 = HeadHR2.addOrReplaceChild("HeadHR3", CubeListBuilder.create().texOffs(44, 70).addBox(-1F, -1.5F, -1.5F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(-1F, 0F, 0F));

        PartDefinition GlowBodyMain2 = partdefinition.addOrReplaceChild("GlowBodyMain2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowBodyMain2a = GlowBodyMain2.addOrReplaceChild("GlowBodyMain2a", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(10F, -14F, -39F));

        PartDefinition EquipSR01 = GlowBodyMain2a.addOrReplaceChild("EquipSR01", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(40F, -11F, 13F, 0F, 0.5236F, 1.5708F));

        PartDefinition EquipSR02 = EquipSR01.addOrReplaceChild("EquipSR02", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR03 = EquipSR02.addOrReplaceChild("EquipSR03", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR04 = EquipSR03.addOrReplaceChild("EquipSR04", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR05 = EquipSR04.addOrReplaceChild("EquipSR05", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR01b = GlowBodyMain2a.addOrReplaceChild("EquipSR01b", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-33F, -9F, 13.7F, -0.5918F, -0.3665F, -0.5918F));

        PartDefinition EquipSR02b = EquipSR01b.addOrReplaceChild("EquipSR02b", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR03b = EquipSR02b.addOrReplaceChild("EquipSR03b", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR04b = EquipSR03b.addOrReplaceChild("EquipSR04b", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR01c = GlowBodyMain2a.addOrReplaceChild("EquipSR01c", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 30F, -19F, 0.5585F, -0.3491F, -2.5307F));

        PartDefinition EquipSR02c = EquipSR01c.addOrReplaceChild("EquipSR02c", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR03c = EquipSR02c.addOrReplaceChild("EquipSR03c", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR04c = EquipSR03c.addOrReplaceChild("EquipSR04c", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        PartDefinition EquipSR05c = EquipSR04c.addOrReplaceChild("EquipSR05c", CubeListBuilder.create().texOffs(108, 25).addBox(-4.5F, 0F, -0.5F, 9F, 16F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 0F));

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        applyFaceAndMouth(entity);

        if (entity instanceof org.trp.shincolle.entity.base.EntityShipBase ship && ship.isInDeadPose()) {
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

        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.Skirt01.y = this.skirt01DefaultY;
        this.Skirt01.z = this.skirt01DefaultZ;
        this.Skirt02.y = this.skirt02DefaultY;
        this.Skirt03.y = this.skirt03DefaultY;
        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.z = this.armRight02DefaultZ;
        this.LegLeft01.y = this.legLeft01DefaultY;
        this.LegLeft01.z = this.legLeft01DefaultZ;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight01.y = this.legRight01DefaultY;
        this.LegRight01.z = this.legRight01DefaultZ;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) {
            return;
        }

        boolean showRigging = entity.getEquipFlag(org.trp.shincolle.entity.EntityMidwayHime.EQUIP_RIGGING)
                && entity.getAttackTick() > 0;
        boolean showCollar = entity.getEquipFlag(org.trp.shincolle.entity.EntityMidwayHime.EQUIP_COLLAR);

        if (showRigging) {
            applyRoadAppearance(entity.getAttackTick());
        } else {
            hideRoadSegments();
        }
        this.GlowBodyMain2.visible = showRigging;
        this.Collar01.visible = showCollar;
    }

    private void applyRoadAppearance(int attackTick) {
        hideRoadSegments();

        switch (attackTick) {
            case 26:
            case 50:
                setRoadStageVisible(1);
                setRoadStageOffsetZ(0.7F, 0F, 0F, 0F, 0F);
                break;
            case 27:
            case 49:
                setRoadStageVisible(2);
                setRoadStageOffsetZ(0.45F, 0.25F, 0F, 0F, 0F);
                break;
            case 28:
            case 48:
                setRoadStageVisible(3);
                setRoadStageOffsetZ(0.25F, 0.2F, 0.25F, 0F, 0F);
                break;
            case 29:
            case 47:
                setRoadStageVisible(4);
                setRoadStageOffsetZ(0.1F, 0.15F, 0.2F, 0.25F, 0F);
                break;
            case 30:
            case 46:
                setRoadStageVisible(5);
                setRoadStageOffsetZ(0F, 0.1F, 0.15F, 0.2F, 0.25F);
                break;
            case 31:
            case 45:
                setRoadStageVisible(5);
                setRoadStageOffsetZ(0F, 0F, 0.1F, 0.15F, 0.2F);
                break;
            case 32:
            case 44:
                setRoadStageVisible(5);
                setRoadStageOffsetZ(0F, 0F, 0F, 0.1F, 0.15F);
                break;
            case 33:
            case 43:
                setRoadStageVisible(5);
                setRoadStageOffsetZ(0F, 0F, 0F, 0F, 0.1F);
                break;
            default:
                if (attackTick > 29 && attackTick < 47) {
                    setRoadStageVisible(5);
                    setRoadStageOffsetZ(0F, 0F, 0F, 0F, 0F);
                }
                break;
        }
    }

    private void hideRoadSegments() {
        setRoadStageVisible(0);
        setRoadStageOffsetZ(0F, 0F, 0F, 0F, 0F);
    }

    private void setRoadStageVisible(int stage) {
        boolean stage1 = stage >= 1;
        boolean stage2 = stage >= 2;
        boolean stage3 = stage >= 3;
        boolean stage4 = stage >= 4;
        boolean stage5 = stage >= 5;

        this.EquipSR01.visible = stage1;
        this.EquipSR01b.visible = stage1;
        this.EquipSR01c.visible = stage1;
        this.EquipSR02.visible = stage2;
        this.EquipSR02b.visible = stage2;
        this.EquipSR02c.visible = stage2;
        this.EquipSR03.visible = stage3;
        this.EquipSR03b.visible = stage3;
        this.EquipSR03c.visible = stage3;
        this.EquipSR04.visible = stage4;
        this.EquipSR04b.visible = stage4;
        this.EquipSR04c.visible = stage4;
        this.EquipSR05.visible = stage5;
        this.EquipSR05c.visible = stage5;
    }

    private void setRoadStageOffsetZ(float offset1, float offset2, float offset3, float offset4, float offset5) {
        this.EquipSR01.z = this.equipSR01DefaultZ + (offset1 * OFFSET_SCALE);
        this.EquipSR01b.z = this.equipSR01bDefaultZ + (offset1 * OFFSET_SCALE);
        this.EquipSR01c.z = this.equipSR01cDefaultZ + (offset1 * OFFSET_SCALE);

        this.EquipSR02.z = this.equipSR02DefaultZ + (offset2 * OFFSET_SCALE);
        this.EquipSR02b.z = this.equipSR02bDefaultZ + (offset2 * OFFSET_SCALE);
        this.EquipSR02c.z = this.equipSR02cDefaultZ + (offset2 * OFFSET_SCALE);

        this.EquipSR03.z = this.equipSR03DefaultZ + (offset3 * OFFSET_SCALE);
        this.EquipSR03b.z = this.equipSR03bDefaultZ + (offset3 * OFFSET_SCALE);
        this.EquipSR03c.z = this.equipSR03cDefaultZ + (offset3 * OFFSET_SCALE);

        this.EquipSR04.z = this.equipSR04DefaultZ + (offset4 * OFFSET_SCALE);
        this.EquipSR04b.z = this.equipSR04bDefaultZ + (offset4 * OFFSET_SCALE);
        this.EquipSR04c.z = this.equipSR04cDefaultZ + (offset4 * OFFSET_SCALE);

        this.EquipSR05.z = this.equipSR05DefaultZ + (offset5 * OFFSET_SCALE);
        this.EquipSR05c.z = this.equipSR05cDefaultZ + (offset5 * OFFSET_SCALE);
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = -0.2618F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.Ahoke.yRot = -1.0F;
        this.BodyMain.xRot = 1.2217F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 1.2217F;
        this.Butt.xRot = -0.05F;
        this.Skirt01.xRot = -0.34F;
        this.Skirt02.xRot = -0.27F;
        this.Skirt03.xRot = -0.22F;
        this.Collar01.xRot = 0.035F;
        this.Hair01.xRot = 0.2F;
        this.Hair01.zRot = -0.2F;
        this.Hair02.xRot = 0.2F;
        this.Hair02.zRot = -0.15F;
        this.HairL01.zRot = 0.0873F;
        this.HairL02.zRot = -0.3142F;
        this.HairR01.zRot = -0.0873F;
        this.HairR02.zRot = -1.2217F;
        this.HairL01.xRot = -0.28F;
        this.HairL02.xRot = 0.15F;
        this.HairR01.xRot = -0.35F;
        this.HairR02.xRot = 0.18F;
        this.BoobL.xRot = -1.0F;
        this.BoobL.zRot = -0.12F;
        this.BoobR.xRot = -0.7F;
        this.BoobR.zRot = -0.12F;

        this.ArmLeft01.xRot = -0.35F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -3.0F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = -0.5F;
        this.ArmRight01.yRot = 0.3F;
        this.ArmRight01.zRot = -0.5F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.zRot = -0.8727F;

        this.LegLeft01.xRot = -0.14F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.09F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;

        this.LegRight01.xRot = -1.2217F;
        this.LegRight01.yRot = -0.5236F;
        this.LegRight01.zRot = 0.0F;
        this.LegRight02.xRot = 1.0472F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = (float) Math.cos(ageInTicks * 0.08F);
        float angleX1 = (float) Math.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = (float) Math.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float angleAdd2 = (float) Math.cos(limbSwing * 0.7F + Math.PI) * limbSwingAmount * 0.5F;

        if (entity.isInWater()) {
            this.poseTranslateY += angleX * 0.025F + 0.025F;
        }

        float addk1 = angleAdd1 * 0.6F - 0.27F;
        float addk2 = angleAdd2 * 0.6F - 0.19F;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = 0.0F;
        float headX = this.Head.xRot * -0.5F;

        this.Ahoke.yRot = angleX * 0.15F + 0.6F;
        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.35F;
        this.BoobL.xRot = angleX * 0.08F - 0.76F;
        this.BoobL.zRot = 0.08F;
        this.BoobR.xRot = angleX * 0.08F - 0.76F;
        this.BoobR.zRot = -0.08F;
        this.Collar01.xRot = 0.035F + this.Head.xRot * 0.8F;

        this.Skirt01.xRot = -0.087F;
        this.Skirt02.xRot = angleX1 * 0.015F - 0.087F;
        this.Skirt03.xRot = -angleX2 * 0.04F - 0.052F;

        this.Hair01.xRot = angleX * 0.03F + 0.26F + headX;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.087F + headX;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.052F;
        this.Hair03.zRot = 0.0F;

        this.ArmLeft01.xRot = -0.26F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = 0.28F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = -0.26F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -0.28F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.087F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.087F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;

        float headZ = this.Head.zRot * -0.5F;
        this.HairL01.xRot = angleX * 0.02F + headX - 0.19F;
        this.HairL02.xRot = -angleX1 * 0.04F + headX + 0.17F;
        this.HairR01.xRot = angleX * 0.02F + headX - 0.19F;
        this.HairR02.xRot = -angleX1 * 0.04F + headX + 0.17F;
        this.Hair01.zRot = headZ;
        this.Hair02.zRot = headZ;
        this.Hair03.zRot = headZ;
        this.HairL01.zRot = headZ - 0.087F;
        this.HairL02.zRot = headZ + 0.087F;
        this.HairR01.zRot = headZ + 0.087F;
        this.HairR02.zRot = headZ - 0.052F;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float angleAdd2 = (float) Math.cos(limbSwing * 0.7F + Math.PI) * limbSwingAmount * 0.5F;
        float headX = this.Head.xRot * -0.5F;

        boolean isSprinting = entity.isSprinting() || limbSwingAmount > 0.9F;
        boolean isCrouching = entity.isCrouching();
        boolean isPassenger = entity.isPassenger();
        boolean isSitting = entity.getIsSitting() || isPassenger;

        if (isSprinting) {
            this.Hair01.xRot = angleAdd1 * 0.1F + limbSwingAmount * 0.4F + headX;
            this.Hair03.xRot += 0.1F;
            this.BoobL.xRot = angleAdd2 * 0.1F - 0.83F;
            this.BoobL.zRot = -0.07F;
            this.BoobR.xRot = angleAdd1 * 0.1F - 0.83F;
            this.BoobR.zRot = 0.07F;
            this.ArmLeft01.xRot = angleAdd2 * 0.8F + 0.1745F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = -0.35F;
            this.ArmRight01.xRot = angleAdd1 * 0.8F + 0.1745F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = 0.35F;
        }

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.Skirt01.xRot = -0.34F;
            this.Skirt01.y = this.skirt01DefaultY + (-0.2F * OFFSET_SCALE);
            this.Skirt01.z = this.skirt01DefaultZ + (0.03F * OFFSET_SCALE);
            this.Skirt02.xRot = -0.27F;
            this.Skirt03.xRot = -0.22F;
            this.Collar01.xRot -= 0.35F;
            this.BoobL.xRot -= 0.2F;
            this.BoobL.zRot = -0.04F;
            this.BoobR.xRot -= 0.2F;
            this.BoobR.zRot = 0.04F;
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
            this.Hair03.xRot -= 0.1F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY = 0.43F * 3F;
                this.Head.xRot -= 0.1F;
                this.BodyMain.xRot = 0.0F;
                this.Butt.xRot = -0.2F;
                this.BoobL.xRot -= 0.1F;
                this.BoobL.zRot = 0.16F;
                this.BoobR.xRot -= 0.1F;
                this.BoobR.zRot = -0.16F;
                this.Skirt01.xRot = -0.05F;
                this.Skirt01.y = this.skirt01DefaultY + (-0.1F * OFFSET_SCALE);
                this.Skirt02.xRot = -0.15F;
                this.Skirt02.y = this.skirt02DefaultY + (-0.1F * OFFSET_SCALE);
                this.Skirt03.xRot = -0.1F;
                this.Skirt03.y = this.skirt03DefaultY + (-0.1F * OFFSET_SCALE);
                this.ArmLeft01.xRot = -0.6F;
                this.ArmLeft01.zRot = 0.1F;
                this.ArmLeft02.zRot = 0.39F;
                this.ArmRight01.xRot = -0.6F;
                this.ArmRight01.zRot = -0.1F;
                this.ArmRight02.zRot = -0.39F;
                this.LegLeft01.xRot = -0.9F;
                this.LegRight01.xRot = -0.9F;
                this.LegLeft01.yRot = 0.19F;
                this.LegLeft01.zRot = 0.0F;
                this.LegLeft02.xRot = 2.67F;
                this.LegLeft02.zRot = 0.0175F;
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.375F * OFFSET_SCALE);
                this.LegRight01.yRot = -0.19F;
                this.LegRight01.zRot = 0.0F;
                this.LegRight02.xRot = 2.67F;
                this.LegRight02.zRot = -0.0175F;
                this.LegRight02.z = this.legRight02DefaultZ + (0.375F * OFFSET_SCALE);
            } else {
                this.poseTranslateY = isPassenger ? RIDING_TRANSLATE_Y : SIT_TRANSLATE_Y;
                this.Head.xRot -= 0.7F;
                this.BodyMain.xRot = 0.35F;
                this.Skirt01.xRot = -0.23F;
                this.Skirt01.y = this.skirt01DefaultY + (-0.23F * OFFSET_SCALE);
                this.Skirt02.xRot = -0.2F;
                this.Skirt02.y = this.skirt02DefaultY + (-0.17F * OFFSET_SCALE);
                this.Skirt03.xRot = -0.2F;
                this.Skirt03.y = this.skirt03DefaultY + (-0.15F * OFFSET_SCALE);
                this.Collar01.xRot -= 0.35F;
                this.ArmLeft01.xRot = -0.5236F;
                this.ArmLeft01.yRot = 0.0F;
                this.ArmLeft01.zRot = 0.3491F;
                this.ArmRight01.xRot = -0.5236F;
                this.ArmRight01.yRot = 0.0F;
                this.ArmRight01.zRot = -0.3491F;
                this.LegLeft01.xRot = -1.4486F;
                this.LegRight01.xRot = -1.4486F;
                this.LegLeft01.yRot = -0.5236F;
                this.LegLeft01.zRot = -1.3963F;
                this.LegLeft02.xRot = 2.1817F;
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.37F * OFFSET_SCALE);
                this.LegRight01.yRot = 0.5236F;
                this.LegRight01.zRot = 1.3963F;
                this.LegRight02.xRot = 2.1817F;
                this.LegRight02.z = this.legRight02DefaultZ + (0.37F * OFFSET_SCALE);
                if (isSprinting) {
                    this.Hair01.xRot += 0.5F;
                    this.Hair02.xRot += 0.4F;
                    this.Hair03.xRot += 0.2F;
                } else {
                    this.Hair01.xRot += 0.2F;
                    this.Hair02.xRot += 0.4F;
                    this.Hair03.xRot += 0.2F;
                }
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            if (entity.getAttackTick() > 20) {
                this.ArmLeft01.xRot = -1.7F + this.Head.xRot * 0.75F;
                this.ArmLeft01.yRot = -0.2F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft02.xRot = 0.0F;
                this.ArmLeft02.yRot = 0.0F;
                this.ArmLeft02.zRot = 0.0F;
            }
            this.GlowBodyMain2a.xRot = this.ArmLeft01.xRot * -0.3F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot = -0.3F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = -0.1F;
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F);
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
            this.ArmRight02.xRot = 0.0F;
            this.ArmRight02.zRot = 0.0F;
        }
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowBodyMain2.copyFrom(this.BodyMain);
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);
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
        this.GlowBodyMain2.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
