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

public class ModelAirfieldHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "airfield_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelAirfieldHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelAirfieldHime");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelAirfieldHime");
    private static final float RIDING_TRANSLATE_Y = LegacyPoseOffsets.ridingY("ModelAirfieldHime");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Butt;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart HeadHL;
    private final ModelPart HeadHR;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart HeadHL2;
    private final ModelPart HeadHL3;
    private final ModelPart HeadHR2;
    private final ModelPart HeadHR3;
    private final ModelPart ArmLeft02;
    private final ModelPart EquipHand01;
    private final ModelPart ArmRight02;
    private final ModelPart EquipHand02;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart ShoesR;
    private final ModelPart LegLeft02;
    private final ModelPart ShoesL;
    private final ModelPart EquipRdL01;
    private final ModelPart EquipRdR01;
    private final ModelPart EquipRdL02;
    private final ModelPart EquipRdL03;
    private final ModelPart EquipRdL04;
    private final ModelPart EquipRdL05;
    private final ModelPart EquipRdL06;
    private final ModelPart EquipRdR02;
    private final ModelPart EquipRdR03;
    private final ModelPart EquipRdR04;
    private final ModelPart EquipRdR05;
    private final ModelPart EquipRdR06;
    private final ModelPart GlowEquipBase;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float buttDefaultY;
    private final float buttDefaultZ;

    public ModelAirfieldHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoesL = this.LegLeft02.getChild("ShoesL");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoesR = this.LegRight02.getChild("ShoesR");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.EquipHand02 = this.ArmRight02.getChild("EquipHand02");
        this.EquipHand01 = this.ArmRight01.getChild("EquipHand01");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.HeadHL = this.GlowHead.getChild("HeadHL");
        this.HeadHL2 = this.HeadHL.getChild("HeadHL2");
        this.HeadHL3 = this.HeadHL2.getChild("HeadHL3");
        this.HeadHR = this.GlowHead.getChild("HeadHR");
        this.HeadHR2 = this.HeadHR.getChild("HeadHR2");
        this.HeadHR3 = this.HeadHR2.getChild("HeadHR3");
        this.GlowEquipBase = this.GlowBodyMain.getChild("GlowEquipBase");
        this.EquipRdL01 = this.GlowEquipBase.getChild("EquipRdL01");
        this.EquipRdL02 = this.EquipRdL01.getChild("EquipRdL02");
        this.EquipRdL03 = this.EquipRdL02.getChild("EquipRdL03");
        this.EquipRdL04 = this.EquipRdL03.getChild("EquipRdL04");
        this.EquipRdL05 = this.EquipRdL04.getChild("EquipRdL05");
        this.EquipRdL06 = this.EquipRdL05.getChild("EquipRdL06");
        this.EquipRdR01 = this.GlowEquipBase.getChild("EquipRdR01");
        this.EquipRdR02 = this.EquipRdR01.getChild("EquipRdR02");
        this.EquipRdR03 = this.EquipRdR02.getChild("EquipRdR03");
        this.EquipRdR04 = this.EquipRdR03.getChild("EquipRdR04");
        this.EquipRdR05 = this.EquipRdR04.getChild("EquipRdR05");
        this.EquipRdR06 = this.EquipRdR05.getChild("EquipRdR06");
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1745F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.7F, -8.6F, -3.5F, -0.6981F, -0.1396F, -0.0873F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.7F, -8.6F, -3.5F, -0.6981F, 0.1396F, 0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(39, 0).addBox(-7.5F, 4F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.3142F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.7F, 9.5F, -2.6F, 0F, 0F, 0.14F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, 0F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesL = LegLeft02.addOrReplaceChild("ShoesL", CubeListBuilder.create().texOffs(87, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 8F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 3F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.7F, 9.5F, -2.6F, -0.1047F, 0F, -0.14F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, 0F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesR = LegRight02.addOrReplaceChild("ShoesR", CubeListBuilder.create().texOffs(87, 0).addBox(-3.5F, 0F, -3.5F, 7F, 8F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 3F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 85).addBox(-3F, -1F, -2.5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0.2094F, 0F, 0.2094F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 83).addBox(0F, 0F, -5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, 2.5F));

        PartDefinition EquipHand02 = ArmRight02.addOrReplaceChild("EquipHand02", CubeListBuilder.create().texOffs(0, 17).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offset(2.5F, -0.5F, -2.5F));

        PartDefinition EquipHand01 = ArmRight01.addOrReplaceChild("EquipHand01", CubeListBuilder.create().texOffs(0, 17).addBox(-3F, 0F, -3F, 6F, 5F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.5F, 7.5F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(88, 26).addBox(-5.5F, -2F, -5F, 11F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, -0.5F, 0.2094F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(48, 55).addBox(-7.5F, 0F, 0F, 15F, 12F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(46, 29).addBox(-7.5F, 0F, 0F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2618F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 59).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, -0.0873F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(0, 37).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -0.1F, -0.1396F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(45, 77).addBox(-8F, -8F, -7.2F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -4F, -11.5F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.5F, -5F, 0F, 0.5236F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(25, 18).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 3F, -3F, -0.2618F, 0.1745F, 0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(25, 18).mirror().addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 10F, 0F, 0.2618F, 0F, -0.0524F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(25, 18).addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 3F, -3F, -0.2618F, -0.1745F, -0.1396F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(25, 18).addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 0F, 0.2618F, 0F, 0.0873F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 85).mirror().addBox(-2F, -1F, -2.5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.2094F, 0F, -0.2094F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 83).mirror().addBox(-5F, 0F, -5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, 2.5F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 104), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1745F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(88, 26), PartPose.offsetAndRotation(0F, -10.3F, -0.5F, 0.2094F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(44, 101), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition HeadHL = GlowHead.addOrReplaceChild("HeadHL", CubeListBuilder.create().texOffs(39, 28).mirror().addBox(0F, -2.5F, -2.5F, 3F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.4F, -10.6F, 0.8F, -0.7854F, -0.1745F, -0.3142F));

        PartDefinition HeadHL2 = HeadHL.addOrReplaceChild("HeadHL2", CubeListBuilder.create().texOffs(47, 56).addBox(0F, -2F, -2F, 1F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(3F, 0F, 0F));

        PartDefinition HeadHL3 = HeadHL2.addOrReplaceChild("HeadHL3", CubeListBuilder.create().texOffs(43, 30).addBox(0F, -1.5F, -1.5F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(1F, 0F, 0F));

        PartDefinition HeadHR = GlowHead.addOrReplaceChild("HeadHR", CubeListBuilder.create().texOffs(39, 28).mirror().addBox(-3F, -2.5F, -2.5F, 3F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.4F, -10.6F, 0.8F, -0.7854F, 0.1745F, 0.3142F));

        PartDefinition HeadHR2 = HeadHR.addOrReplaceChild("HeadHR2", CubeListBuilder.create().texOffs(47, 56).addBox(-1F, -2F, -2F, 1F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(-3F, 0F, 0F));

        PartDefinition HeadHR3 = HeadHR2.addOrReplaceChild("HeadHR3", CubeListBuilder.create().texOffs(43, 30).addBox(-1F, -1.5F, -1.5F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(-1F, 0F, 0F));

        PartDefinition GlowEquipBase = GlowBodyMain.addOrReplaceChild("GlowEquipBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipRdL01 = GlowEquipBase.addOrReplaceChild("EquipRdL01", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, 0F, 6F, 1.4F, -0.3491F, -0.3491F));

        PartDefinition EquipRdL02 = EquipRdL01.addOrReplaceChild("EquipRdL02", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.3491F, 0F, 0F));

        PartDefinition EquipRdL03 = EquipRdL02.addOrReplaceChild("EquipRdL03", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.4363F, 0F, 0F));

        PartDefinition EquipRdL04 = EquipRdL03.addOrReplaceChild("EquipRdL04", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.3491F, 0F, 0F));

        PartDefinition EquipRdL05 = EquipRdL04.addOrReplaceChild("EquipRdL05", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.2618F, 0F, 0F));

        PartDefinition EquipRdL06 = EquipRdL05.addOrReplaceChild("EquipRdL06", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.1745F, 0F, 0F));

        PartDefinition EquipRdR01 = GlowEquipBase.addOrReplaceChild("EquipRdR01", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, 0F, 6F, 1.4F, 0.3491F, 0.3491F));

        PartDefinition EquipRdR02 = EquipRdR01.addOrReplaceChild("EquipRdR02", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.3491F, 0F, 0F));

        PartDefinition EquipRdR03 = EquipRdR02.addOrReplaceChild("EquipRdR03", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.4363F, 0F, 0F));

        PartDefinition EquipRdR04 = EquipRdR03.addOrReplaceChild("EquipRdR04", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.3491F, 0F, 0F));

        PartDefinition EquipRdR05 = EquipRdR04.addOrReplaceChild("EquipRdR05", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.2618F, 0F, 0F));

        PartDefinition EquipRdR06 = EquipRdR05.addOrReplaceChild("EquipRdR06", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.1745F, 0F, 0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

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
    }

    private void applyEquipVisibility(T entity) {
        boolean showHand = entity.getEquipFlag(org.trp.shincolle.entity.EntityAirfieldHime.EQUIP_HAND);
        this.EquipHand01.visible = showHand;
        this.EquipHand02.visible = showHand;
        this.EquipRdL01.visible = false;
        this.EquipRdR01.visible = false;
        this.EquipRdL02.visible = false;
        this.EquipRdR02.visible = false;
        this.EquipRdL03.visible = false;
        this.EquipRdR03.visible = false;
        this.EquipRdL04.visible = false;
        this.EquipRdR04.visible = false;
        this.EquipRdL05.visible = false;
        this.EquipRdR05.visible = false;
        this.EquipRdL06.visible = false;
        this.EquipRdR06.visible = false;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.0F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        float headX = this.Head.xRot * -0.5F;
        this.BoobL.xRot = -0.7F;
        this.BoobR.xRot = -0.7F;
        this.Ahoke.yRot = 0.5236F;
        this.BodyMain.zRot = 0.0F;
        this.Hair01.xRot = 0.26F + headX;
        this.Hair02.xRot = -0.08F + headX;
        this.Hair03.xRot = -0.14F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmRight02.xRot = 0.0F;
        this.LegLeft01.yRot = 0.0F;
        this.LegRight01.yRot = 0.0F;

        this.Head.xRot += 0.14F;
        this.BodyMain.xRot = 0.4F;
        this.Butt.xRot = -0.4F;
        this.Butt.z = this.buttDefaultZ + (0.19F * OFFSET_SCALE);
        this.BoobL.xRot -= 0.2F;
        this.BoobR.xRot -= 0.2F;
        this.ArmLeft01.xRot = -1.3F;
        this.ArmLeft01.zRot = -0.1F;
        this.ArmLeft02.zRot = 1.15F;
        this.ArmRight01.xRot = -1.3F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = 0.1F;
        this.ArmRight02.zRot = -1.4F;

        this.LegLeft01.zRot = -0.2F;
        this.LegLeft02.xRot = 1.34F;
        this.LegRight01.zRot = 0.2F;
        this.LegRight02.xRot = 1.13F;

        this.Hair01.xRot -= 0.2F;
        this.Hair02.xRot -= 0.2F;
        this.Hair03.xRot -= 0.1F;

        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.zRot = headZ;
        this.Hair02.zRot = headZ;
        this.HairL01.zRot = headZ;
        this.HairL02.zRot = headZ + 0.087F;
        this.HairR01.zRot = headZ;
        this.HairR02.zRot = headZ - 0.052F;

        headX = this.Head.xRot * -0.5F;
        this.HairL01.xRot = headX - 0.5F;
        this.HairL02.xRot = headX - 0.1F;
        this.HairR01.xRot = headX - 0.5F;
        this.HairR02.xRot = headX - 0.1F;

        this.LegLeft01.xRot = -2.1232F;
        this.LegRight01.xRot = -2.0708F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.7F;

        if(entity.getShipDepth() > 0) {
            this.poseTranslateY = angleX * 0.05F + 0.025F;
        }

        this.Head.xRot = headPitch * 0.014F;
        this.Head.yRot = netHeadYaw * 0.01F;
        this.Head.zRot = 0.0F;

        float headX = this.Head.xRot * -0.5F;

        this.BoobL.xRot = angleX * 0.06F - 0.7F;
        this.BoobR.xRot = angleX * 0.06F - 0.7F;
        this.Ahoke.yRot = angleX * 0.25F + 0.5236F;
        this.BodyMain.xRot = -0.1745F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.3142F;
        this.Butt.z = this.buttDefaultZ;

        this.Hair01.xRot = angleX * 0.03F + 0.26F + headX;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.08F + headX;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.14F;
        this.Hair03.zRot = 0.0F;

        this.ArmLeft01.xRot = angleAdd2 * 0.8F + 0.2F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = angleX * 0.08F - 0.2F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.8F + 0.2F;
        this.ArmRight01.zRot = -angleX * 0.08F + 0.2F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.14F;
        this.LegLeft02.xRot = 0.0F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.14F;
        this.LegRight02.xRot = 0.0F;

        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.zRot = headZ;
        this.Hair02.zRot = headZ;
        this.HairL01.zRot = headZ - 0.14F;
        this.HairL02.zRot = headZ + 0.087F;
        this.HairR01.zRot = headZ + 0.14F;
        this.HairR02.zRot = headZ - 0.052F;

        this.HairL01.xRot = angleX * 0.03F + headX - 0.26F;
        this.HairL02.xRot = -angleX1 * 0.04F + headX + 0.26F;
        this.HairR01.xRot = angleX * 0.03F + headX - 0.26F;
        this.HairR02.xRot = -angleX1 * 0.04F + headX + 0.26F;

        this.LegLeft01.xRot = angleAdd1;
        this.LegRight01.xRot = angleAdd2 - 0.2F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        boolean isCrouching = entity.isCrouching();
        boolean isPassenger = entity.isPassenger();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.ArmLeft01.xRot = -0.35F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.35F;
            this.ArmRight01.zRot = -0.2618F;

            this.LegLeft01.xRot -= 1.1F;
            this.LegRight01.xRot -= 1.1F;

            this.Hair01.xRot += 0.37F;
            this.Hair02.xRot += 0.23F;
            this.Hair03.xRot -= 0.1F;
        }

        if (isSitting || isPassenger) {
            this.isSittingPose = true;
            this.poseTranslateY += (isPassenger ? RIDING_TRANSLATE_Y : SITTING_TRANSLATE_Y);
            if (hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY = 0.27F * 3F;
                this.Head.xRot += 0.14F;
                this.BodyMain.xRot = -0.4363F;
                this.BoobL.xRot -= 0.25F;
                this.BoobR.xRot -= 0.25F;
                this.ArmLeft01.xRot = -0.3142F;
                this.ArmLeft01.zRot = 0.349F;
                this.ArmLeft02.zRot = 1.15F;
                this.ArmRight01.xRot = -0.4363F;
                this.ArmRight01.zRot = -0.2793F;
                this.ArmRight02.zRot = -1.4F;
                this.LegLeft01.xRot = -1.309F;
                this.LegRight01.xRot = -1.7F;
                this.LegLeft01.yRot = 0.3142F;
                this.LegLeft02.xRot = 1.0472F;
                this.LegRight01.yRot = -0.35F;
                this.LegRight01.zRot = -0.2618F;
                this.LegRight02.xRot = 0.9F;
                this.Hair01.xRot += 0.12F;
                this.Hair02.xRot += 0.15F;
                this.Hair03.xRot += 0.25F;
            } else {
                this.Head.xRot += 0.14F;
                this.BodyMain.xRot = -0.5236F;
                this.BoobL.xRot -= 0.2F;
                this.BoobR.xRot -= 0.2F;
                this.ArmLeft01.xRot = -0.4363F;
                this.ArmLeft01.zRot = 0.3142F;
                this.ArmRight01.xRot = -0.4363F;
                this.ArmRight01.zRot = -0.3142F;
                this.LegLeft01.zRot = -0.3142F;
                this.LegLeft02.xRot = 1.34F;
                this.LegRight01.zRot = 0.35F;
                this.LegRight02.xRot = 1.13F;
                this.Hair01.xRot += 0.09F;
                this.Hair02.xRot += 0.43F;
                this.Hair03.xRot += 0.49F;
                this.LegLeft01.xRot = -1.6232F;
                this.LegRight01.xRot = -1.5708F;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            if (entity.getAttackTick() > 25) {
                if (hasLegacyModelFlag(entity, 2)) {
                    this.poseTranslateY += 0.15F;
                    this.Head.yRot *= 0.8F;
                    this.Head.xRot = 0.4538F;
                    this.BodyMain.xRot = -1.0472F;
                    this.BodyMain.zRot = -0.2094F;
                    this.ArmLeft01.xRot = -0.35F;
                    this.ArmLeft01.zRot = -0.35F;
                    this.ArmLeft02.xRot = -0.5F;
                    this.ArmRight01.xRot = 1.2F;
                    this.ArmRight01.zRot = 0.5236F;
                    this.ArmRight02.xRot = -0.35F;
                    this.LegLeft01.xRot = 0.5236F;
                    this.LegRight01.xRot = 0.1745F;
                    this.LegLeft01.zRot = 0.2618F;
                    this.LegLeft02.xRot = 0.5236F;
                    this.LegRight01.zRot = 0.1745F;
                    this.LegRight02.xRot = 0.5236F;
                    this.Hair01.xRot += 0.09F;
                    this.Hair02.xRot += 0.43F;
                    this.Hair03.xRot += 0.49F;
                } else if (hasLegacyModelFlag(entity, 3)) {
                    this.Head.yRot *= 0.8F;
                    this.Head.xRot = 0.2094F;
                    this.Head.zRot = -0.2618F;
                    this.BodyMain.xRot = -0.35F;
                    this.BodyMain.zRot = 0.1745F;
                    this.ArmLeft01.xRot = -1.2217F;
                    this.ArmLeft01.yRot = 0.5236F;
                    this.ArmLeft01.zRot = -0.35F;
                    this.ArmLeft02.xRot = -1.3963F;
                    this.ArmRight01.xRot = 0.7854F;
                    this.ArmRight01.zRot = 0.5236F;
                    this.ArmRight02.xRot = -0.5236F;
                    this.LegLeft01.xRot = -0.2618F;
                    this.LegRight01.xRot = 0.3142F;
                    this.LegLeft01.zRot = -0.4363F;
                    this.LegLeft02.xRot = 0.2618F;
                    this.LegRight01.zRot = 0.0873F;
                    this.Hair01.xRot += 0.09F;
                    this.Hair02.xRot += 0.43F;
                    this.Hair03.xRot += 0.49F;
                } else {
                    this.ArmLeft01.xRot = -1.3F;
                    this.ArmLeft01.yRot = -0.7F;
                    this.ArmLeft01.zRot = 0.0F;
                }
            }
            setRoad(entity.getAttackTick());
        }

        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float headX = this.Head.xRot * -0.5F;
        this.HairL01.xRot = angleX * 0.03F + headX - 0.26F;
        this.HairL02.xRot = -angleX1 * 0.04F + headX + 0.26F;
        this.HairR01.xRot = angleX * 0.03F + headX - 0.26F;
        this.HairR02.xRot = -angleX1 * 0.04F + headX + 0.26F;

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

    private void setRoad(int attackTime) {
        switch (attackTime) {
            case 26:
            case 50:
                this.EquipRdL01.visible = true;
                this.EquipRdR01.visible = true;
                this.EquipRdL02.visible = false;
                this.EquipRdR02.visible = false;
                break;
            case 27:
            case 49:
                this.EquipRdL01.visible = true;
                this.EquipRdR01.visible = true;
                this.EquipRdL02.visible = true;
                this.EquipRdR02.visible = true;
                this.EquipRdL03.visible = false;
                this.EquipRdR03.visible = false;
                break;
            case 28:
            case 48:
                this.EquipRdL01.visible = true;
                this.EquipRdR01.visible = true;
                this.EquipRdL02.visible = true;
                this.EquipRdR02.visible = true;
                this.EquipRdL03.visible = true;
                this.EquipRdR03.visible = true;
                this.EquipRdL04.visible = false;
                this.EquipRdR04.visible = false;
                break;
            case 29:
            case 47:
                this.EquipRdL01.visible = true;
                this.EquipRdR01.visible = true;
                this.EquipRdL02.visible = true;
                this.EquipRdR02.visible = true;
                this.EquipRdL03.visible = true;
                this.EquipRdR03.visible = true;
                this.EquipRdL04.visible = true;
                this.EquipRdR04.visible = true;
                this.EquipRdL05.visible = false;
                this.EquipRdR05.visible = false;
                break;
            case 30:
            case 46:
                this.EquipRdL01.visible = true;
                this.EquipRdR01.visible = true;
                this.EquipRdL02.visible = true;
                this.EquipRdR02.visible = true;
                this.EquipRdL03.visible = true;
                this.EquipRdR03.visible = true;
                this.EquipRdL04.visible = true;
                this.EquipRdR04.visible = true;
                this.EquipRdL05.visible = true;
                this.EquipRdR05.visible = true;
                this.EquipRdL06.visible = false;
                this.EquipRdR06.visible = false;
                break;
            default:
                if (attackTime > 30 && attackTime < 46) {
                    this.EquipRdL01.visible = true;
                    this.EquipRdR01.visible = true;
                    this.EquipRdL02.visible = true;
                    this.EquipRdR02.visible = true;
                    this.EquipRdL03.visible = true;
                    this.EquipRdR03.visible = true;
                    this.EquipRdL04.visible = true;
                    this.EquipRdR04.visible = true;
                    this.EquipRdL05.visible = true;
                    this.EquipRdR05.visible = true;
                    this.EquipRdL06.visible = true;
                    this.EquipRdR06.visible = true;
                }
        }
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.copyFrom(this.BodyMain);
            this.GlowNeck.copyFrom(this.Neck);
            this.GlowHead.copyFrom(this.Head);
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
