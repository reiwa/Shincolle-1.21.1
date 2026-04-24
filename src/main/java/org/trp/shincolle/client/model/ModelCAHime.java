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

public class ModelCAHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "ca_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCAHime");
    private static final float SIT_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelCAHime");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight01;
    private final ModelPart Neck;
    private final ModelPart Head;
    private final ModelPart TailBase;
    private final ModelPart Band01;
    private final ModelPart Band02;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart LegLeft02;
    private final ModelPart LegRight02;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ear01;
    private final ModelPart Ear02;
    private final ModelPart Horn01;
    private final ModelPart Horn02;
    private final ModelPart HatBase;
    private final ModelPart Ahoke;
    private final ModelPart Hair01;
    private final ModelPart Hair02a;
    private final ModelPart Hair02b;
    private final ModelPart Hair03a;
    private final ModelPart Hair03b;
    private final ModelPart Horn03;
    private final ModelPart HatL;
    private final ModelPart HatR;
    private final ModelPart HatEyeL;
    private final ModelPart HatEyeR;
    private final ModelPart Tail01;
    private final ModelPart Tail01_1;
    private final ModelPart Tail02;
    private final ModelPart Tail03;
    private final ModelPart Tail04;
    private final ModelPart Tail05;
    private final ModelPart Tail06;
    private final ModelPart Tail07;
    private final ModelPart Tail08;
    private final ModelPart Tail09;
    private final ModelPart TailHead01;
    private final ModelPart TailJaw01;
    private final ModelPart TailC01;
    private final ModelPart TailC02;
    private final ModelPart Tail02_1;
    private final ModelPart Tail03_1;
    private final ModelPart Tail04_1;
    private final ModelPart Tail05_1;
    private final ModelPart Tail06_1;
    private final ModelPart Tail07_1;
    private final ModelPart Tail08_1;
    private final ModelPart Tail09_1;
    private final ModelPart TailHead01_1;
    private final ModelPart TailJaw01_1;
    private final ModelPart TailC01_1;
    private final ModelPart TailC02_1;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
    private final float headDefaultY;
    private final float hatBaseDefaultY;
    private final float hatBaseDefaultZ;
    private final float armLeft01DefaultZ;
    private final float armRight01DefaultZ;
    private final float tailBaseDefaultY;
    private final float tailBaseDefaultZ;

    public ModelCAHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Neck = this.BodyMain.getChild("Neck");
        this.LegRight01 = this.BodyMain.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegLeft01 = this.BodyMain.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.Band02 = this.BodyMain.getChild("Band02");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.TailBase = this.BodyMain.getChild("TailBase");
        this.Tail01 = this.TailBase.getChild("Tail01");
        this.Tail02 = this.Tail01.getChild("Tail02");
        this.Tail03 = this.Tail02.getChild("Tail03");
        this.Tail04 = this.Tail03.getChild("Tail04");
        this.Tail05 = this.Tail04.getChild("Tail05");
        this.Tail06 = this.Tail05.getChild("Tail06");
        this.Tail07 = this.Tail06.getChild("Tail07");
        this.Tail08 = this.Tail07.getChild("Tail08");
        this.Tail09 = this.Tail08.getChild("Tail09");
        this.TailHead01 = this.Tail09.getChild("TailHead01");
        this.TailC01 = this.TailHead01.getChild("TailC01");
        this.TailC02 = this.TailHead01.getChild("TailC02");
        this.TailJaw01 = this.Tail09.getChild("TailJaw01");
        this.Tail01_1 = this.TailBase.getChild("Tail01_1");
        this.Tail02_1 = this.Tail01_1.getChild("Tail02_1");
        this.Tail03_1 = this.Tail02_1.getChild("Tail03_1");
        this.Tail04_1 = this.Tail03_1.getChild("Tail04_1");
        this.Tail05_1 = this.Tail04_1.getChild("Tail05_1");
        this.Tail06_1 = this.Tail05_1.getChild("Tail06_1");
        this.Tail07_1 = this.Tail06_1.getChild("Tail07_1");
        this.Tail08_1 = this.Tail07_1.getChild("Tail08_1");
        this.Tail09_1 = this.Tail08_1.getChild("Tail09_1");
        this.TailHead01_1 = this.Tail09_1.getChild("TailHead01_1");
        this.TailC02_1 = this.TailHead01_1.getChild("TailC02_1");
        this.TailC01_1 = this.TailHead01_1.getChild("TailC01_1");
        this.TailJaw01_1 = this.Tail09_1.getChild("TailJaw01_1");
        this.Head = this.BodyMain.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair03a = this.HairMain.getChild("Hair03a");
        this.Hair02b = this.HairMain.getChild("Hair02b");
        this.Hair02a = this.HairMain.getChild("Hair02a");
        this.Hair03b = this.HairMain.getChild("Hair03b");
        this.Ear01 = this.Head.getChild("Ear01");
        this.Horn02 = this.Head.getChild("Horn02");
        this.Horn03 = this.Horn02.getChild("Horn03");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HatBase = this.Head.getChild("HatBase");
        this.HatL = this.HatBase.getChild("HatL");
        this.HatEyeL = this.HatL.getChild("HatEyeL");
        this.HatR = this.HatBase.getChild("HatR");
        this.HatEyeR = this.HatR.getChild("HatEyeR");
        this.Ear02 = this.Head.getChild("Ear02");
        this.Horn01 = this.Head.getChild("Horn01");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.Band01 = this.BodyMain.getChild("Band01");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.headDefaultY = this.Head.y;
        this.hatBaseDefaultY = this.HatBase.y;
        this.hatBaseDefaultZ = this.HatBase.z;
        this.armLeft01DefaultZ = this.ArmLeft01.z;
        this.armRight01DefaultZ = this.ArmRight01.z;
        this.tailBaseDefaultY = this.TailBase.y;
        this.tailBaseDefaultZ = this.TailBase.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 93).addBox(-5.5F, -4.5F, -12F, 11F, 10F, 24F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 78).addBox(-5F, -4F, -4.5F, 10F, 5F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -9.4F, 0.4189F, 0F, 0F));

        PartDefinition LegRight01 = BodyMain.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(66, 92).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 3F, 8.3F, -0.1396F, 0F, -0.1745F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(66, 105).mirror().addBox(-2.5F, 0F, 0F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 8F, -2.5F));

        PartDefinition LegLeft01 = BodyMain.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(46, 92).addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 3F, 8.3F, 0.1396F, 0F, 0.1745F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(46, 105).addBox(-2.5F, 0F, 0F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 8F, -2.5F));

        PartDefinition Band02 = BodyMain.addOrReplaceChild("Band02", CubeListBuilder.create().texOffs(40, 39).addBox(-0.5F, 0F, 0F, 1F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, 1.7F, -12F, -0.0873F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 92).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 3F, -6F, 0.1396F, 0F, -0.2094F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 105).mirror().addBox(0F, 0F, -5F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 8F, 2.5F));

        PartDefinition TailBase = BodyMain.addOrReplaceChild("TailBase", CubeListBuilder.create().texOffs(57, 21).addBox(-4F, -2F, 0F, 8F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, -2F));

        PartDefinition Tail01 = TailBase.addOrReplaceChild("Tail01", CubeListBuilder.create().texOffs(58, 16).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 0F, 3F, 0.2618F, 1.5708F, 0F));

        PartDefinition Tail02 = Tail01.addOrReplaceChild("Tail02", CubeListBuilder.create().texOffs(58, 17).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.6109F, -0.0873F, 0F));

        PartDefinition Tail03 = Tail02.addOrReplaceChild("Tail03", CubeListBuilder.create().texOffs(54, 16).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.6109F, -0.0873F, 0F));

        PartDefinition Tail04 = Tail03.addOrReplaceChild("Tail04", CubeListBuilder.create().texOffs(54, 19).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.5236F, 0F, 0F));

        PartDefinition Tail05 = Tail04.addOrReplaceChild("Tail05", CubeListBuilder.create().texOffs(53, 16).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.5236F, 0F, 0F));

        PartDefinition Tail06 = Tail05.addOrReplaceChild("Tail06", CubeListBuilder.create().texOffs(83, 0).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.3491F, 0F, 0F));

        PartDefinition Tail07 = Tail06.addOrReplaceChild("Tail07", CubeListBuilder.create().texOffs(86, 0).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.1745F, 0.0873F, 0F));

        PartDefinition Tail08 = Tail07.addOrReplaceChild("Tail08", CubeListBuilder.create().texOffs(83, 0).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.0873F, 0.2618F, 0F));

        PartDefinition Tail09 = Tail08.addOrReplaceChild("Tail09", CubeListBuilder.create().texOffs(96, 0).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, -0.0873F, 0.4363F, 0F));

        PartDefinition TailHead01 = Tail09.addOrReplaceChild("TailHead01", CubeListBuilder.create().texOffs(40, 0).addBox(-4.5F, 0F, 0F, 9F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.8F, 3.5F, -0.1745F, 0F, 0F));

        PartDefinition TailC01 = TailHead01.addOrReplaceChild("TailC01", CubeListBuilder.create().texOffs(100, 8).addBox(-1F, -1F, 0F, 2F, 2F, 8F, new CubeDeformation(0F)), PartPose.offset(2F, 4.5F, 9.5F));

        PartDefinition TailC02 = TailHead01.addOrReplaceChild("TailC02", CubeListBuilder.create().texOffs(100, 8).addBox(-1F, -1F, 0F, 2F, 2F, 8F, new CubeDeformation(0F)), PartPose.offset(-2F, 4.5F, 9.5F));

        PartDefinition TailJaw01 = Tail09.addOrReplaceChild("TailJaw01", CubeListBuilder.create().texOffs(90, 18).addBox(-4.5F, -4F, 0F, 9F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.7F, 3.3F, 0.2618F, 0F, 0F));

        PartDefinition Tail01_1 = TailBase.addOrReplaceChild("Tail01_1", CubeListBuilder.create().texOffs(54, 16).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.5F, 0F, 3F, 0.6981F, -1.5708F, 0F));

        PartDefinition Tail02_1 = Tail01_1.addOrReplaceChild("Tail02_1", CubeListBuilder.create().texOffs(56, 17).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.3491F, 0.2618F, 0F));

        PartDefinition Tail03_1 = Tail02_1.addOrReplaceChild("Tail03_1", CubeListBuilder.create().texOffs(58, 16).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.4363F, 0.3491F, 0F));

        PartDefinition Tail04_1 = Tail03_1.addOrReplaceChild("Tail04_1", CubeListBuilder.create().texOffs(53, 18).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.3491F, 0.4363F, 0F));

        PartDefinition Tail05_1 = Tail04_1.addOrReplaceChild("Tail05_1", CubeListBuilder.create().texOffs(58, 19).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.5236F, 0.3491F, 0F));

        PartDefinition Tail06_1 = Tail05_1.addOrReplaceChild("Tail06_1", CubeListBuilder.create().texOffs(85, 2).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.0873F, 0.2618F, 0F));

        PartDefinition Tail07_1 = Tail06_1.addOrReplaceChild("Tail07_1", CubeListBuilder.create().texOffs(86, 0).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, -0.3491F, 0.3491F, 0F));

        PartDefinition Tail08_1 = Tail07_1.addOrReplaceChild("Tail08_1", CubeListBuilder.create().texOffs(83, 0).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, -0.5236F, 0.3491F, 0F));

        PartDefinition Tail09_1 = Tail08_1.addOrReplaceChild("Tail09_1", CubeListBuilder.create().texOffs(96, 0).addBox(-4F, -3.5F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, -0.0873F, 0.4363F, 0F));

        PartDefinition TailHead01_1 = Tail09_1.addOrReplaceChild("TailHead01_1", CubeListBuilder.create().texOffs(40, 0).addBox(-4.5F, 0F, 0F, 9F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.8F, 3.5F, -0.1745F, 0F, 0F));

        PartDefinition TailC02_1 = TailHead01_1.addOrReplaceChild("TailC02_1", CubeListBuilder.create().texOffs(100, 8).addBox(-1F, -1F, 0F, 2F, 2F, 8F, new CubeDeformation(0F)), PartPose.offset(-2F, 4.5F, 9.5F));

        PartDefinition TailC01_1 = TailHead01_1.addOrReplaceChild("TailC01_1", CubeListBuilder.create().texOffs(100, 8).addBox(-1F, -1F, 0F, 2F, 2F, 8F, new CubeDeformation(0F)), PartPose.offset(2F, 4.5F, 9.5F));

        PartDefinition TailJaw01_1 = Tail09_1.addOrReplaceChild("TailJaw01_1", CubeListBuilder.create().texOffs(90, 18).addBox(-4.5F, -4F, 0F, 9F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.7F, 2.7F, 0.1745F, 0F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 65).addBox(-7F, -11F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -13F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(0, 56).addBox(-7.5F, 0F, 0F, 15F, 12F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -11.5F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 40).addBox(-7.5F, 0F, 0F, 15F, 7F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.6F, 0.3491F, 0F, 0F));

        PartDefinition Hair03a = HairMain.addOrReplaceChild("Hair03a", CubeListBuilder.create().texOffs(90, 32).addBox(-1.5F, 0F, -3F, 3F, 12F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.4F, 9.8F, 5.5F, -0.2094F, -0.1396F, 0.0698F));

        PartDefinition Hair02b = HairMain.addOrReplaceChild("Hair02b", CubeListBuilder.create().texOffs(81, 116).addBox(-1.5F, 0F, -3.3F, 3F, 7F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.9F, 4.7F, 0F, 0F, 0F, 0.0873F));

        PartDefinition Hair02a = HairMain.addOrReplaceChild("Hair02a", CubeListBuilder.create().texOffs(81, 116).addBox(-1.5F, 0F, -3.3F, 3F, 7F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.9F, 4.7F, 0F, 0F, 0F, -0.0873F));

        PartDefinition Hair03b = HairMain.addOrReplaceChild("Hair03b", CubeListBuilder.create().texOffs(90, 32).addBox(-1.5F, 0F, -3F, 3F, 12F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.4F, 9.8F, 5.5F, -0.2094F, 0.1396F, -0.0698F));

        PartDefinition Ear01 = Head.addOrReplaceChild("Ear01", CubeListBuilder.create().texOffs(0, 26).addBox(-2F, 0F, -7F, 4F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.2F, -11F, 6.8F, -0.8378F, -0.1222F, 0.1745F));

        PartDefinition Horn02 = Head.addOrReplaceChild("Horn02", CubeListBuilder.create().texOffs(40, 39).addBox(-1.5F, -1.5F, -6F, 3F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.3F, -7.5F, -6F, -0.8727F, -0.4363F, 0.2618F));

        PartDefinition Horn03 = Horn02.addOrReplaceChild("Horn03", CubeListBuilder.create().texOffs(40, 39).addBox(-3F, -3F, -6F, 3F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 1.5F, -6F, -0.6981F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 40).addBox(-8F, -8F, -7.2F, 16F, 17F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -4F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, 0F, -12F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -4F, -7.6F, -0.2618F, 1.4835F, -0.2618F));

        PartDefinition HatBase = Head.addOrReplaceChild("HatBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -3.1F, 5.8F));

        PartDefinition HatL = HatBase.addOrReplaceChild("HatL", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -14F, -1F, 10F, 16F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.3F, 2.1F, -2.9F, 0.5236F, 0.0873F, 0.0698F));

        PartDefinition HatEyeL = HatL.addOrReplaceChild("HatEyeL", CubeListBuilder.create().texOffs(22, 28).addBox(0F, -3F, -3F, 1F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9.6F, -6F, 5.3F, 0.0873F, -0.0524F, -0.0524F));

        PartDefinition HatR = HatBase.addOrReplaceChild("HatR", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-10F, -14F, -1F, 10F, 16F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.3F, 2.1F, -2.9F, 0.5236F, -0.0873F, -0.0698F));

        PartDefinition HatEyeR = HatR.addOrReplaceChild("HatEyeR", CubeListBuilder.create().texOffs(22, 28).mirror().addBox(-1F, -3F, -3F, 1F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9.6F, -6F, 5.3F, 0.0873F, 0.0524F, 0.0524F));

        PartDefinition Ear02 = Head.addOrReplaceChild("Ear02", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-2F, 0F, -7F, 4F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.2F, -11F, 6.8F, -0.8378F, 0.1222F, -0.1745F));

        PartDefinition Horn01 = Head.addOrReplaceChild("Horn01", CubeListBuilder.create().texOffs(40, 39).addBox(-1.5F, -1.5F, -6F, 3F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, -7.5F, -6F, -0.8727F, 0.4363F, -0.5236F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 92).addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 3F, -6F, -0.1396F, 0F, 0.2094F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 105).addBox(-5F, 0F, -5F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(2.5F, 8F, 2.5F));

        PartDefinition Band01 = BodyMain.addOrReplaceChild("Band01", CubeListBuilder.create().texOffs(40, 39).addBox(-0.5F, 0F, 0F, 1F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 1.7F, -12F, -0.1745F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -6F, -13F));

        ShipModelBaseAdv.addFaceLayerCAHime(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        this.applyFaceAndMouth(entity);

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

        this.Head.y = this.headDefaultY;
        this.HatBase.y = this.hatBaseDefaultY;
        this.HatBase.z = this.hatBaseDefaultZ;
        this.ArmLeft01.z = this.armLeft01DefaultZ;
        this.ArmRight01.z = this.armRight01DefaultZ;
        this.TailBase.y = this.tailBaseDefaultY;
        this.TailBase.z = this.tailBaseDefaultZ;
    }

    private void applyEquipVisibility(T entity) {
        boolean showTail1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_TAIL_1);
        boolean showTail2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_TAIL_2);
        boolean showHat1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_HAT_1);
        boolean showHat2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_HAT_2);
        boolean showHat3 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_HAT_3);

        this.TailBase.visible = showTail1 || showTail2;
        if (showHat2 || showHat3) {
            this.HatBase.visible = true;
            this.Hair01.visible = false;
            this.Horn01.visible = true;
            this.Horn02.visible = true;
            this.Ear01.visible = true;
            this.Ear02.visible = true;
        } else if (showHat1) {
            this.HatBase.visible = true;
            this.Hair01.visible = false;
            this.Horn01.visible = false;
            this.Horn02.visible = false;
            this.Ear01.visible = false;
            this.Ear02.visible = false;
        } else {
            this.HatBase.visible = false;
            this.Hair01.visible = true;
            this.Horn01.visible = true;
            this.Horn02.visible = true;
            this.Ear01.visible = true;
            this.Ear02.visible = true;
        }
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.7853F;
        this.Head.yRot = 0.0F;
        this.Ahoke.xRot = -0.2618F;
        this.BodyMain.xRot = 0.0F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = -1.4835F;

        this.ArmLeft01.xRot = -0.4F;
        this.ArmLeft01.zRot = 0.4537F;
        this.ArmLeft02.zRot = 0.0F;
        this.ArmRight01.xRot = -0.8F;
        this.ArmRight01.zRot = -0.05F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.xRot = 0.5F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.4537F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.xRot = 0.8F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.05F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.HatBase.xRot = 0.0F;

        this.TailHead01.xRot = -0.17F;
        this.TailJaw01.xRot = 0.26F;
        this.TailHead01_1.xRot = 0.0F;
        this.TailJaw01_1.xRot = 0.2F;
        this.Tail01.xRot = -1.4F;
        this.Tail01.yRot = 1.57F;
        this.Tail02.xRot = -0.3F;
        this.Tail02.yRot = 0.2F;
        this.Tail03.xRot = -0.3F;
        this.Tail03.yRot = 0.3F;
        this.Tail04.xRot = 0.2F;
        this.Tail04.yRot = 0.4F;
        this.Tail05.xRot = 0.1F;
        this.Tail05.yRot = 0.5F;
        this.Tail06.xRot = -0.1F;
        this.Tail06.yRot = 0.4F;
        this.Tail07.xRot = -0.1F;
        this.Tail07.yRot = 0.3F;
        this.Tail08.xRot = 0.1F;
        this.Tail08.yRot = 0.2F;
        this.Tail09.xRot = 0.0F;
        this.Tail09.yRot = 0.1F;

        this.Tail01_1.xRot = -1.4F;
        this.Tail01_1.yRot = -1.7F;
        this.Tail02_1.xRot = -0.2F;
        this.Tail02_1.yRot = 0.2F;
        this.Tail03_1.xRot = -0.1F;
        this.Tail03_1.yRot = 0.3F;
        this.Tail04_1.xRot = 0.0F;
        this.Tail04_1.yRot = 0.4F;
        this.Tail05_1.xRot = 0.0F;
        this.Tail05_1.yRot = 0.5F;
        this.Tail06_1.xRot = -0.1F;
        this.Tail06_1.yRot = 0.4F;
        this.Tail07_1.xRot = -0.1F;
        this.Tail07_1.yRot = 0.3F;
        this.Tail08_1.xRot = 0.2F;
        this.Tail08_1.yRot = 0.2F;
        this.Tail09_1.xRot = -0.2F;
        this.Tail09_1.yRot = 0.3F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = (float) Math.cos(ageInTicks * 0.08F + limbSwing * 0.25F);
        float angleX1 = (float) Math.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = (float) Math.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = (float) Math.cos(limbSwing * 0.7F + Math.PI) * limbSwingAmount;

        if (entity.isInWater()) {
            this.poseTranslateY += angleX * 0.05F + 0.025F;
        } else {
            this.poseTranslateY += 0.5F;
        }

        float addk1 = angleAdd1 * 0.35F - 0.14F;
        float addk2 = angleAdd2 * 0.35F + 0.14F;

        if (entity.isSprinting() || limbSwingAmount > 0.8F) {
            addk1 *= 2.0F;
            addk2 *= 2.0F;
        }

        this.ArmRight01.xRot = addk1;
        this.ArmLeft01.xRot = addk2;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);

        this.Ahoke.xRot = angleX * 0.05F - 0.2618F;
        this.BodyMain.xRot = 0.0F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;

        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = 0.21F;
        this.ArmLeft02.zRot = 0.0F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -0.21F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.1745F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.1745F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.HatBase.xRot = 0.0F;

        float[] cosf2 = new float[9];
        for (int i = 0; i < 9; ++i) {
            cosf2[i] = (float) Math.cos(ageInTicks * 0.1F + limbSwing * 0.25F + 0.8F * i);
        }

        this.TailHead01.xRot = -angleX * 0.075F - 0.1F;
        this.TailJaw01.xRot = angleX * 0.1F + 0.18F;
        this.TailHead01_1.xRot = -angleX2 * 0.12F - 0.1F;
        this.TailJaw01_1.xRot = angleX2 * 0.15F + 0.26F;
        this.TailC01.xRot = angleX1 * 0.3F - 0.2F;
        this.TailC02.xRot = angleX2 * 0.3F - 0.2F;
        this.TailC01_1.xRot = angleX1 * 0.3F - 0.2F;
        this.TailC02_1.xRot = angleX2 * 0.3F - 0.2F;

        boolean showTail1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_TAIL_1);
        boolean showTail2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_TAIL_2);
        boolean showTailBoth = showTail1 && showTail2;
        if (showTailBoth) {
            this.TailBase.y = this.tailBaseDefaultY + (-0.15F * OFFSET_SCALE);
            this.TailBase.z = this.tailBaseDefaultZ;
            this.Tail01.xRot = 0.26F;
            this.Tail01.yRot = 1.7F + cosf2[0] * 0.015F;
            this.Tail02.xRot = 0.61F;
            this.Tail02.yRot = -0.09F + cosf2[1] * 0.02F;
            this.Tail03.xRot = 0.61F;
            this.Tail03.yRot = -0.09F + cosf2[2] * 0.025F;
            this.Tail04.xRot = 0.52F;
            this.Tail04.yRot = cosf2[3] * 0.03F;
            this.Tail05.xRot = 0.52F;
            this.Tail05.yRot = cosf2[4] * 0.04F;
            this.Tail06.xRot = 0.35F;
            this.Tail06.yRot = cosf2[5] * 0.05F;
            this.Tail07.xRot = 0.17F;
            this.Tail07.yRot = 0.1F + cosf2[6] * 0.06F;
            this.Tail08.xRot = 0.09F;
            this.Tail08.yRot = 0.1F + cosf2[7] * 0.08F;
            this.Tail09.xRot = -0.09F;
            this.Tail09.yRot = 0.5F + cosf2[8] * 0.15F;

            this.Tail01_1.xRot = 0.7F;
            this.Tail01_1.yRot = -1.57F + cosf2[0] * 0.02F;
            this.Tail02_1.xRot = 0.35F;
            this.Tail02_1.yRot = 0.26F + cosf2[1] * 0.03F;
            this.Tail03_1.xRot = 0.44F;
            this.Tail03_1.yRot = 0.35F + cosf2[2] * 0.04F;
            this.Tail04_1.xRot = 0.35F;
            this.Tail04_1.yRot = 0.44F + cosf2[3] * 0.05F;
            this.Tail05_1.xRot = 0.52F;
            this.Tail05_1.yRot = 0.35F + cosf2[4] * 0.06F;
            this.Tail06_1.xRot = 0.09F;
            this.Tail06_1.yRot = 0.26F + cosf2[5] * 0.07F;
            this.Tail07_1.xRot = -0.35F;
            this.Tail07_1.yRot = 0.35F + cosf2[6] * 0.08F;
            this.Tail08_1.xRot = -0.52F;
            this.Tail08_1.yRot = 0.35F + cosf2[7] * 0.09F;
            this.Tail09_1.xRot = -0.09F;
            this.Tail09_1.yRot = 0.44F + cosf2[8] * 0.12F;
        } else if (showTail1) {
            this.TailBase.y = this.tailBaseDefaultY + (-0.15F * OFFSET_SCALE);
            this.TailBase.z = this.tailBaseDefaultZ;
            this.Tail01.xRot = -0.17F + cosf2[0] * 0.03F;
            this.Tail01.yRot = 1.3F + cosf2[0] * 0.03F;
            this.Tail02.xRot = 0.26F + cosf2[1] * 0.03F;
            this.Tail02.yRot = -0.52F + cosf2[1] * 0.03F;
            this.Tail03.xRot = 0.35F + cosf2[2] * 0.03F;
            this.Tail03.yRot = -0.52F + cosf2[2] * 0.03F;
            this.Tail04.xRot = 0.52F + cosf2[3] * 0.03F;
            this.Tail04.yRot = -0.44F + cosf2[3] * 0.03F;
            this.Tail05.xRot = 0.52F + cosf2[4] * 0.04F;
            this.Tail05.yRot = -0.17F + cosf2[4] * 0.04F;
            this.Tail06.xRot = 0.35F + cosf2[5] * 0.05F;
            this.Tail06.yRot = 0.35F + cosf2[5] * 0.05F;
            this.Tail07.xRot = 0.44F + cosf2[6] * 0.06F;
            this.Tail07.yRot = 0.17F + cosf2[6] * 0.06F;
            this.Tail08.xRot = 0.52F + cosf2[7] * 0.08F;
            this.Tail08.yRot = 0.17F + cosf2[7] * 0.08F;
            this.Tail09.xRot = 0.52F + cosf2[8] * 0.15F;
            this.Tail09.yRot = 0.17F + cosf2[8] * 0.15F;

            this.Tail01_1.xRot = -0.17F + cosf2[0] * 0.03F;
            this.Tail01_1.yRot = -1.3F + cosf2[0] * 0.03F;
            this.Tail02_1.xRot = 0.26F + cosf2[1] * 0.03F;
            this.Tail02_1.yRot = 0.52F + cosf2[1] * 0.03F;
            this.Tail03_1.xRot = 0.35F + cosf2[2] * 0.03F;
            this.Tail03_1.yRot = 0.52F + cosf2[2] * 0.03F;
            this.Tail04_1.xRot = 0.52F + cosf2[3] * 0.03F;
            this.Tail04_1.yRot = 0.44F + cosf2[3] * 0.03F;
            this.Tail05_1.xRot = 0.52F + cosf2[4] * 0.04F;
            this.Tail05_1.yRot = 0.17F + cosf2[4] * 0.04F;
            this.Tail06_1.xRot = 0.35F + cosf2[5] * 0.05F;
            this.Tail06_1.yRot = -0.35F + cosf2[5] * 0.05F;
            this.Tail07_1.xRot = 0.44F + cosf2[6] * 0.06F;
            this.Tail07_1.yRot = -0.17F + cosf2[6] * 0.06F;
            this.Tail08_1.xRot = 0.52F + cosf2[7] * 0.08F;
            this.Tail08_1.yRot = -0.17F + cosf2[7] * 0.08F;
            this.Tail09_1.xRot = 0.52F + cosf2[8] * 0.15F;
            this.Tail09_1.yRot = -0.17F + cosf2[8] * 0.15F;
        } else if (showTail2) {
            this.TailBase.y = this.tailBaseDefaultY + (-0.54F * OFFSET_SCALE);
            this.TailBase.z = this.tailBaseDefaultZ + (0.86F * OFFSET_SCALE);
            this.Tail01.xRot = -0.17F + cosf2[0] * 0.03F;
            this.Tail01.yRot = 1.3F + cosf2[0] * 0.03F;
            this.Tail02.xRot = 0.26F + cosf2[1] * 0.03F;
            this.Tail02.yRot = -0.52F + cosf2[1] * 0.03F;
            this.Tail03.xRot = 0.35F + cosf2[2] * 0.03F;
            this.Tail03.yRot = -0.52F + cosf2[2] * 0.03F;
            this.Tail04.xRot = 0.52F + cosf2[3] * 0.03F;
            this.Tail04.yRot = -0.44F + cosf2[3] * 0.03F;
            this.Tail05.xRot = 0.52F + cosf2[4] * 0.04F;
            this.Tail05.yRot = -0.17F + cosf2[4] * 0.04F;
            this.Tail06.xRot = 0.35F + cosf2[5] * 0.05F;
            this.Tail06.yRot = 0.35F + cosf2[5] * 0.05F;
            this.Tail07.xRot = 0.44F + cosf2[6] * 0.06F;
            this.Tail07.yRot = 0.17F + cosf2[6] * 0.06F;
            this.Tail08.xRot = 0.52F + cosf2[7] * 0.08F;
            this.Tail08.yRot = 0.17F + cosf2[7] * 0.08F;
            this.Tail09.xRot = 0.52F + cosf2[8] * 0.15F;
            this.Tail09.yRot = 0.17F + cosf2[8] * 0.15F;

            this.Tail01_1.xRot = -0.17F + cosf2[0] * 0.03F;
            this.Tail01_1.yRot = -1.3F + cosf2[0] * 0.03F;
            this.Tail02_1.xRot = 0.26F + cosf2[1] * 0.03F;
            this.Tail02_1.yRot = 0.52F + cosf2[1] * 0.03F;
            this.Tail03_1.xRot = 0.35F + cosf2[2] * 0.03F;
            this.Tail03_1.yRot = 0.52F + cosf2[2] * 0.03F;
            this.Tail04_1.xRot = 0.52F + cosf2[3] * 0.03F;
            this.Tail04_1.yRot = 0.44F + cosf2[3] * 0.03F;
            this.Tail05_1.xRot = 0.52F + cosf2[4] * 0.04F;
            this.Tail05_1.yRot = 0.17F + cosf2[4] * 0.04F;
            this.Tail06_1.xRot = 0.35F + cosf2[5] * 0.05F;
            this.Tail06_1.yRot = -0.35F + cosf2[5] * 0.05F;
            this.Tail07_1.xRot = 0.44F + cosf2[6] * 0.06F;
            this.Tail07_1.yRot = -0.17F + cosf2[6] * 0.06F;
            this.Tail08_1.xRot = 0.52F + cosf2[7] * 0.08F;
            this.Tail08_1.yRot = -0.17F + cosf2[7] * 0.08F;
            this.Tail09_1.xRot = 0.52F + cosf2[8] * 0.15F;
            this.Tail09_1.yRot = -0.17F + cosf2[8] * 0.15F;
        }

        float modf2 = ageInTicks % 128.0F;
        if (modf2 < 6.0F) {
            if (modf2 >= 3.0F) {
                modf2 -= 3.0F;
            }
            float anglef2 = (float) Math.sin(modf2 * 1.0472F) * 0.25F;
            this.Ear01.zRot = anglef2 + 0.1745F;
            this.Ear02.zRot = -anglef2 - 0.1745F;
        } else {
            this.Ear01.zRot = 0.1745F;
            this.Ear02.zRot = -0.1745F;
        }

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        boolean isCrouching = entity.isCrouching();
        boolean isPassenger = entity.isPassenger();
        boolean isSitting = entity.getIsSitting() || isPassenger;
        boolean hat1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_HAT_1);
        boolean hat2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_HAT_2);
        boolean hat3 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCAHime.EQUIP_HAT_3);
        boolean hatBoth = hat1 && hat2;

        if (isCrouching) {
            this.Head.y = this.headDefaultY + (0.2F * OFFSET_SCALE);
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                if (hatBoth) {
                    this.HatBase.xRot = -1.8F;
                    this.HatBase.y = this.hatBaseDefaultY + (0.3F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (0.07F * OFFSET_SCALE);
                } else if (hat1) {
                    this.HatBase.xRot = 1.37F;
                    this.HatBase.y = this.hatBaseDefaultY + (-0.45F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (-0.2F * OFFSET_SCALE);
                } else if (hat3) {
                    this.HatBase.xRot = -0.85F;
                    this.HatBase.y = this.hatBaseDefaultY + (0.1F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (0.07F * OFFSET_SCALE);
                } else {
                    this.HatBase.xRot = 0.0F;
                    this.HatBase.y = this.hatBaseDefaultY;
                    this.HatBase.z = this.hatBaseDefaultZ;
                }
                this.poseTranslateY = SIT_TRANSLATE_Y;
                this.Head.xRot = -0.2F;
                this.Head.zRot = -0.09F;
                this.BodyMain.zRot = 0.09F;
                this.ArmLeft01.xRot = -1.31F;
                this.ArmLeft01.yRot = 0.17F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft02.zRot = 0.0F;
                this.ArmRight01.xRot = -1.22F;
                this.ArmRight01.yRot = 1.05F;
                this.ArmRight01.zRot = 0.0F;
                this.ArmRight02.zRot = 0.0F;
                this.LegLeft01.xRot = 1.31F;
                this.LegLeft01.yRot = -0.7F;
                this.LegLeft01.zRot = 0.0F;
                this.LegRight01.xRot = 1.22F;
                this.LegRight01.yRot = -0.87F;
                this.LegRight01.zRot = 0.0F;
            } else if (entity != null && hasLegacyState(entity, 7, 4)) {
                if (hatBoth) {
                    this.HatBase.xRot = -1.8F;
                    this.HatBase.y = this.hatBaseDefaultY + (0.6F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (-0.3F * OFFSET_SCALE);
                } else if (hat1) {
                    this.HatBase.xRot = 1.37F;
                    this.HatBase.y = this.hatBaseDefaultY + (-0.45F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (-0.2F * OFFSET_SCALE);
                } else if (hat3) {
                    this.HatBase.xRot = -0.85F;
                    this.HatBase.y = this.hatBaseDefaultY + (0.6F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (0.07F * OFFSET_SCALE);
                } else {
                    this.HatBase.xRot = 0.0F;
                    this.HatBase.y = this.hatBaseDefaultY;
                    this.HatBase.z = this.hatBaseDefaultZ;
                }
                this.poseTranslateY = 0.22F * 4.1F;
                this.Head.xRot = 1.5359F;
                this.Head.y = this.headDefaultY + (0.25F * OFFSET_SCALE);
                this.ArmLeft01.xRot = -1.5359F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft01.z = this.armLeft01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.ArmLeft02.zRot = 0.0F;
                this.ArmRight01.xRot = -1.5359F;
                this.ArmRight01.zRot = 0.0F;
                this.ArmRight01.z = this.armRight01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.ArmRight02.zRot = 0.0F;
                this.LegLeft01.xRot = 1.5359F;
                this.LegRight01.xRot = 1.5359F;
            } else {
                if (hatBoth) {
                    this.HatBase.xRot = -1.8F;
                    this.HatBase.y = this.hatBaseDefaultY + (0.2F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (0.07F * OFFSET_SCALE);
                } else if (hat1) {
                    this.HatBase.xRot = 1.37F;
                    this.HatBase.y = this.hatBaseDefaultY + (-0.45F * OFFSET_SCALE);
                    this.HatBase.z = this.hatBaseDefaultZ + (-0.2F * OFFSET_SCALE);
                } else if (hat3) {
                    this.HatBase.xRot = -0.85F;
                    this.HatBase.y = this.hatBaseDefaultY;
                    this.HatBase.z = this.hatBaseDefaultZ + (0.07F * OFFSET_SCALE);
                } else {
                    this.HatBase.xRot = 0.0F;
                    this.HatBase.y = this.hatBaseDefaultY;
                    this.HatBase.z = this.hatBaseDefaultZ;
                }
                this.poseTranslateY = 0.22F * 4.1F;
                this.Head.xRot = -0.5F;
                this.Head.y = this.headDefaultY + (0.25F * OFFSET_SCALE);
                this.ArmLeft01.xRot = -1.5359F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft01.z = this.armLeft01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.ArmLeft02.zRot = 1.1868F;
                this.ArmRight01.xRot = -1.5359F;
                this.ArmRight01.zRot = 0.0F;
                this.ArmRight01.z = this.armRight01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.ArmRight02.zRot = -1.1868F;
                this.LegLeft01.xRot = 1.5359F;
                this.LegRight01.xRot = 1.5359F;
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            this.TailHead01.xRot = -0.6F;
            this.TailJaw01.xRot = 0.5F;
            this.TailHead01_1.xRot = -0.6F;
            this.TailJaw01_1.xRot = 0.5F;
            this.TailC01.xRot = -0.1F;
            this.TailC02.xRot = -0.1F;
            this.TailC01_1.xRot = -0.1F;
            this.TailC02_1.xRot = -0.1F;
            this.Tail01.xRot = 0.2F;
            this.Tail01.yRot = 1.2F;
            this.Tail02.xRot = 0.4F;
            this.Tail02.yRot = -0.5F;
            this.Tail03.xRot = 0.4F;
            this.Tail03.yRot = -0.32F;
            this.Tail04.xRot = 0.4F;
            this.Tail04.yRot = 0.4F;
            this.Tail05.xRot = 0.2F;
            this.Tail05.yRot = 0.4F;
            this.Tail06.xRot = 0.3F;
            this.Tail06.yRot = 0.4F;
            this.Tail07.xRot = 0.2F;
            this.Tail07.yRot = 0.4F;
            this.Tail08.xRot = 0.1F;
            this.Tail08.yRot = 0.3F;
            this.Tail09.xRot = 0.1F;
            this.Tail09.yRot = 0.3F;
            this.Tail01_1.xRot = -0.17F;
            this.Tail01_1.yRot = -1.5F;
            this.Tail02_1.xRot = 0.26F;
            this.Tail02_1.yRot = 0.52F;
            this.Tail03_1.xRot = 0.35F;
            this.Tail03_1.yRot = 0.52F;
            this.Tail04_1.xRot = 0.52F;
            this.Tail04_1.yRot = 0.3F;
            this.Tail05_1.xRot = 0.52F;
            this.Tail05_1.yRot = 0.17F;
            this.Tail06_1.xRot = 0.35F;
            this.Tail06_1.yRot = -0.35F;
            this.Tail07_1.xRot = 0.2F;
            this.Tail07_1.yRot = -0.17F;
            this.Tail08_1.xRot = 0.3F;
            this.Tail08_1.yRot = -0.17F;
            this.Tail09_1.xRot = 0.5F;
            this.Tail09_1.yRot = -0.17F;
            float progress = entity.getAttackTick() + (1.0F - ageInTicks + (int) ageInTicks);
            if (entity.getAttackTick() > 47) {
                this.TailHead01.xRot = (progress - 50.0F) * 0.3F - 0.1F;
                this.TailJaw01.xRot = (50.0F - progress) * 0.3F + 0.1F;
            } else if (entity.getAttackTick() > 39) {
                this.TailHead01.xRot = -0.7F + (47.0F - progress) * 0.06F;
                this.TailJaw01.xRot = 0.7F - (47.0F - progress) * 0.06F;
            } else {
                this.TailHead01.xRot = -0.25F;
                this.TailJaw01.xRot = 0.25F;
            }
            this.TailHead01_1.xRot = this.TailHead01.xRot;
            this.TailJaw01_1.xRot = this.TailJaw01.xRot;
        }
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
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

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
