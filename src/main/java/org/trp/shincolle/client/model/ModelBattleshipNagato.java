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

public class ModelBattleshipNagato<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "battleship_nagato"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBattleshipNagato");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBattleshipNagato");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBattleshipNagato");

    private static final float BODY_BASE_X_ROT = -0.1047F;
    private static final float BUTT_BASE_X_ROT = 0.1745F;
    private static final float SKIRT_BASE_X_ROT = -0.1745F;
    private static final float LEG_BASE_Z_ROT = 0.0873F;

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
    private final ModelPart Cloth;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart HeadEquip;
    private final ModelPart HeadEquip05;
    private final ModelPart Ahoke;
    private final ModelPart HairMidL01;
    private final ModelPart HairMidL02;
    private final ModelPart HeadEquip01;
    private final ModelPart HeadEquip03;
    private final ModelPart HeadEquip02;
    private final ModelPart HeadEquip04;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart LegRight;
    private final ModelPart LegLeft;
    private final ModelPart Skirt;
    private final ModelPart ShoesR;
    private final ModelPart ShoesL;
    private final ModelPart SkirtEquip;
    private final ModelPart EquipBase;
    private final ModelPart EquipL01;
    private final ModelPart EquipR01;
    private final ModelPart EquipBaseM01;
    private final ModelPart EquipBaseM02;
    private final ModelPart EquipBaseM03;
    private final ModelPart EquipL02;
    private final ModelPart EquipL03;
    private final ModelPart EquipR04;
    private final ModelPart EquipLCBase01;
    private final ModelPart EquipLC2Base01;
    private final ModelPart EquipLC2Base02;
    private final ModelPart EquipLC201;
    private final ModelPart EquipLC203;
    private final ModelPart EquipLC202;
    private final ModelPart EquipLC204;
    private final ModelPart EquipLCBase02;
    private final ModelPart EquipLC01;
    private final ModelPart EquipLC03;
    private final ModelPart EquipLCRadar;
    private final ModelPart EquipLC02;
    private final ModelPart EquipLC04;
    private final ModelPart EquipR02;
    private final ModelPart EquipR03;
    private final ModelPart EquipRCBase01;
    private final ModelPart EquipR04_1;
    private final ModelPart EquipRCBase02;
    private final ModelPart EquipRC01;
    private final ModelPart EquipRC03;
    private final ModelPart EquipRCRadar;
    private final ModelPart EquipRC02;
    private final ModelPart EquipRC04;
    private final ModelPart EquipRC2Base01;
    private final ModelPart EquipRC2Base02;
    private final ModelPart EquipRC201;
    private final ModelPart EquipRC203;
    private final ModelPart EquipRC202;
    private final ModelPart EquipRC204;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultY;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultY;
    private final float armRight02DefaultZ;

    public ModelBattleshipNagato(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Cloth = this.BodyMain.getChild("Cloth");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairMain = this.Head.getChild("HairMain");
        this.HairMidL01 = this.HairMain.getChild("HairMidL01");
        this.HairMidL02 = this.HairMidL01.getChild("HairMidL02");
        this.HeadEquip05 = this.Head.getChild("HeadEquip05");
        this.HeadEquip = this.Head.getChild("HeadEquip");
        this.HeadEquip03 = this.HeadEquip.getChild("HeadEquip03");
        this.HeadEquip04 = this.HeadEquip03.getChild("HeadEquip04");
        this.HeadEquip01 = this.HeadEquip.getChild("HeadEquip01");
        this.HeadEquip02 = this.HeadEquip01.getChild("HeadEquip02");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight = this.Butt.getChild("LegRight");
        this.ShoesR = this.LegRight.getChild("ShoesR");
        this.Skirt = this.Butt.getChild("Skirt");
        this.SkirtEquip = this.Skirt.getChild("SkirtEquip");
        this.EquipBase = this.SkirtEquip.getChild("EquipBase");
        this.EquipBaseM01 = this.EquipBase.getChild("EquipBaseM01");
        this.EquipBaseM03 = this.EquipBase.getChild("EquipBaseM03");
        this.EquipBaseM02 = this.EquipBase.getChild("EquipBaseM02");
        this.EquipL01 = this.EquipBase.getChild("EquipL01");
        this.EquipL02 = this.EquipL01.getChild("EquipL02");
        this.EquipL03 = this.EquipL02.getChild("EquipL03");
        this.EquipR04 = this.EquipL03.getChild("EquipR04");
        this.EquipLC2Base01 = this.EquipR04.getChild("EquipLC2Base01");
        this.EquipLC2Base02 = this.EquipLC2Base01.getChild("EquipLC2Base02");
        this.EquipLC201 = this.EquipLC2Base02.getChild("EquipLC201");
        this.EquipLC202 = this.EquipLC201.getChild("EquipLC202");
        this.EquipLC203 = this.EquipLC2Base02.getChild("EquipLC203");
        this.EquipLC204 = this.EquipLC203.getChild("EquipLC204");
        this.EquipLCBase01 = this.EquipL03.getChild("EquipLCBase01");
        this.EquipLCBase02 = this.EquipLCBase01.getChild("EquipLCBase02");
        this.EquipLC01 = this.EquipLCBase02.getChild("EquipLC01");
        this.EquipLC02 = this.EquipLC01.getChild("EquipLC02");
        this.EquipLCRadar = this.EquipLCBase02.getChild("EquipLCRadar");
        this.EquipLC03 = this.EquipLCBase02.getChild("EquipLC03");
        this.EquipLC04 = this.EquipLC03.getChild("EquipLC04");
        this.EquipR01 = this.EquipBase.getChild("EquipR01");
        this.EquipR02 = this.EquipR01.getChild("EquipR02");
        this.EquipR03 = this.EquipR02.getChild("EquipR03");
        this.EquipRCBase01 = this.EquipR03.getChild("EquipRCBase01");
        this.EquipRCBase02 = this.EquipRCBase01.getChild("EquipRCBase02");
        this.EquipRC03 = this.EquipRCBase02.getChild("EquipRC03");
        this.EquipRC04 = this.EquipRC03.getChild("EquipRC04");
        this.EquipRC01 = this.EquipRCBase02.getChild("EquipRC01");
        this.EquipRC02 = this.EquipRC01.getChild("EquipRC02");
        this.EquipRCRadar = this.EquipRCBase02.getChild("EquipRCRadar");
        this.EquipR04_1 = this.EquipR03.getChild("EquipR04_1");
        this.EquipRC2Base01 = this.EquipR04_1.getChild("EquipRC2Base01");
        this.EquipRC2Base02 = this.EquipRC2Base01.getChild("EquipRC2Base02");
        this.EquipRC203 = this.EquipRC2Base02.getChild("EquipRC203");
        this.EquipRC204 = this.EquipRC203.getChild("EquipRC204");
        this.EquipRC201 = this.EquipRC2Base02.getChild("EquipRC201");
        this.EquipRC202 = this.EquipRC201.getChild("EquipRC202");
        this.LegLeft = this.Butt.getChild("LegLeft");
        this.ShoesL = this.LegLeft.getChild("ShoesL");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
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

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 105).addBox(-6.5F, -10F, -4F, 13F, 15F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -14F, 0F));

        PartDefinition Cloth = BodyMain.addOrReplaceChild("Cloth", CubeListBuilder.create().texOffs(96, 16).addBox(-5.5F, 0F, 0F, 11F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, -11.5F, -5F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(46, 14).addBox(-7F, -0.5F, -4.5F, 14F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 75).addBox(-8F, -8F, -7.2F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(33, 87).addBox(0F, -3F, -10F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -10F, -5F, 0F, 0.5236F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(48, 56).addBox(-7.5F, 0F, 0F, 15F, 9F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition HairMidL01 = HairMain.addOrReplaceChild("HairMidL01", CubeListBuilder.create().texOffs(48, 34).addBox(-7.5F, 0F, 0F, 15F, 13F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.5F, 0.3491F, 0F, 0F));

        PartDefinition HairMidL02 = HairMidL01.addOrReplaceChild("HairMidL02", CubeListBuilder.create().texOffs(0, 32).addBox(-7F, 0F, 0F, 14F, 14F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.8F, -0.1745F, 0F, 0F));

        PartDefinition HeadEquip05 = Head.addOrReplaceChild("HeadEquip05", CubeListBuilder.create().texOffs(128, 0).addBox(-16F, 0F, 0F, 32F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -11.5F, -1F));

        PartDefinition HeadEquip = Head.addOrReplaceChild("HeadEquip", CubeListBuilder.create().texOffs(128, 0).addBox(-9.5F, 0F, 0F, 19F, 4F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, -1F, 0.1047F, 0F, 0F));

        PartDefinition HeadEquip03 = HeadEquip.addOrReplaceChild("HeadEquip03", CubeListBuilder.create().texOffs(128, 0).addBox(-4F, 0F, -2F, 4F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.5F, 0F, 9F, 0F, 0.7854F, 0.1745F));

        PartDefinition HeadEquip04 = HeadEquip03.addOrReplaceChild("HeadEquip04", CubeListBuilder.create().texOffs(92, 30).addBox(-10F, -1F, -1F, 10F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 1F, 0F, 0F, 0F, 0.0873F));

        PartDefinition HeadEquip01 = HeadEquip.addOrReplaceChild("HeadEquip01", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, -2F, 4F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.5F, 0F, 9F, 0F, -0.7854F, -0.1745F));

        PartDefinition HeadEquip02 = HeadEquip01.addOrReplaceChild("HeadEquip02", CubeListBuilder.create().texOffs(92, 30).mirror().addBox(0F, -1F, -1F, 10F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 1F, 0F, 0F, 0F, -0.0873F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 70).addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.7F, -9F, -3.5F, -0.7854F, -0.1396F, -0.0873F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(0, 70).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.7F, -9F, -3.5F, -0.7854F, 0.1396F, 0.0873F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 53).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.5F, -10F, 0F, 0F, 0F, -0.1571F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 10F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 53).addBox(-2.5F, 0F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8.5F, -10F, 0F, 0F, 0F, 0.1571F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 53).addBox(-2.5F, 0F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 10F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 17).addBox(-8F, 4F, -5.5F, 16F, 8F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2618F, 0F, 0F));

        PartDefinition LegRight = Butt.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(0, 80).addBox(-3F, 0F, -3F, 6F, 19F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, 9.5F, -3F, -0.2618F, 0F, -0.0524F));

        PartDefinition ShoesR = LegRight.addOrReplaceChild("ShoesR", CubeListBuilder.create().texOffs(22, 70).addBox(-3.5F, 0F, -3.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 19F, -0.2F));

        PartDefinition Skirt = Butt.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0F, -4.5F, 17F, 6F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -2F, -0.1367F, 0F, 0F));

        PartDefinition SkirtEquip = Skirt.addOrReplaceChild("SkirtEquip", CubeListBuilder.create().texOffs(71, 0).addBox(-9F, 0F, -5F, 18F, 3F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, 0.2F));

        PartDefinition EquipBase = SkirtEquip.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(128, 0).addBox(-2.5F, 0F, 0F, 5F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 4F, 0.1745F, 0F, 0F));

        PartDefinition EquipBaseM01 = EquipBase.addOrReplaceChild("EquipBaseM01", CubeListBuilder.create().texOffs(128, 0).addBox(-1F, 0F, 0F, 2F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 12F, -0.6981F, 0F, 0F));

        PartDefinition EquipBaseM03 = EquipBase.addOrReplaceChild("EquipBaseM03", CubeListBuilder.create().texOffs(128, 92).addBox(-3F, -14F, 0F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 9F, -0.4363F, 0F, 0F));

        PartDefinition EquipBaseM02 = EquipBase.addOrReplaceChild("EquipBaseM02", CubeListBuilder.create().texOffs(128, 0).addBox(-3.5F, 0F, 0F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 11F));

        PartDefinition EquipL01 = EquipBase.addOrReplaceChild("EquipL01", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 14F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 8F, -0.2094F, 0F, 0F));

        PartDefinition EquipL02 = EquipL01.addOrReplaceChild("EquipL02", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 10F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11.5F, 0F, 0.6F, 0F, 0.5236F, 0F));

        PartDefinition EquipL03 = EquipL02.addOrReplaceChild("EquipL03", CubeListBuilder.create().texOffs(128, 26).addBox(0F, 0F, -14F, 6F, 18F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.3F, 0F, 1.3F, 0F, -0.6981F, 0F));

        PartDefinition EquipR04 = EquipL03.addOrReplaceChild("EquipR04", CubeListBuilder.create().texOffs(128, 60).addBox(0F, 0F, -10F, 6F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, -13F, 0F, 0.1745F, 0F));

        PartDefinition EquipLC2Base01 = EquipR04.addOrReplaceChild("EquipLC2Base01", CubeListBuilder.create().texOffs(128, 0).addBox(-4.5F, 0F, -10F, 9F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(3F, -1F, -10F));

        PartDefinition EquipLC2Base02 = EquipLC2Base01.addOrReplaceChild("EquipLC2Base02", CubeListBuilder.create().texOffs(128, 0).addBox(-5F, -5F, -10F, 10F, 5F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -1F, 0.0524F, 0F, 0F));

        PartDefinition EquipLC201 = EquipLC2Base02.addOrReplaceChild("EquipLC201", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -4F, -8F, -0.1396F, 0F, 0F));

        PartDefinition EquipLC202 = EquipLC201.addOrReplaceChild("EquipLC202", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipLC203 = EquipLC2Base02.addOrReplaceChild("EquipLC203", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -4F, -8F, -0.0873F, 0F, 0F));

        PartDefinition EquipLC204 = EquipLC203.addOrReplaceChild("EquipLC204", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipLCBase01 = EquipL03.addOrReplaceChild("EquipLCBase01", CubeListBuilder.create().texOffs(128, 0).addBox(0F, -5.5F, -10F, 7F, 11F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, -7F, 0F, 0.0873F, 0F));

        PartDefinition EquipLCBase02 = EquipLCBase01.addOrReplaceChild("EquipLCBase02", CubeListBuilder.create().texOffs(128, 0).addBox(0F, -5F, -4F, 5F, 10F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 0F, -6.5F, -0.1745F, 0.0524F, 0F));

        PartDefinition EquipLC01 = EquipLCBase02.addOrReplaceChild("EquipLC01", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, -2F, -4F, 0F, -0.2618F, 0F));

        PartDefinition EquipLC02 = EquipLC01.addOrReplaceChild("EquipLC02", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipLCRadar = EquipLCBase02.addOrReplaceChild("EquipLCRadar", CubeListBuilder.create().texOffs(128, 0).addBox(0F, -7.5F, 0F, 1F, 15F, 5F, new CubeDeformation(0F)), PartPose.offset(5.2F, 0F, 5.5F));

        PartDefinition EquipLC03 = EquipLCBase02.addOrReplaceChild("EquipLC03", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 2F, -4F, 0F, -0.1396F, 0F));

        PartDefinition EquipLC04 = EquipLC03.addOrReplaceChild("EquipLC04", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipR01 = EquipBase.addOrReplaceChild("EquipR01", CubeListBuilder.create().texOffs(128, 0).addBox(-14F, 0F, 0F, 14F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 8F, -0.2094F, 0F, 0F));

        PartDefinition EquipR02 = EquipR01.addOrReplaceChild("EquipR02", CubeListBuilder.create().texOffs(128, 0).addBox(-10F, 0F, 0F, 10F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.5F, 0F, 0.6F, 0F, -0.5236F, 0F));

        PartDefinition EquipR03 = EquipR02.addOrReplaceChild("EquipR03", CubeListBuilder.create().texOffs(128, 26).addBox(-6F, 0F, -14F, 6F, 18F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.3F, 0F, 1.3F, 0F, 0.6981F, 0F));

        PartDefinition EquipRCBase01 = EquipR03.addOrReplaceChild("EquipRCBase01", CubeListBuilder.create().texOffs(128, 0).addBox(-7F, -5.5F, -10F, 7F, 11F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, -7F, 0F, -0.0873F, 0F));

        PartDefinition EquipRCBase02 = EquipRCBase01.addOrReplaceChild("EquipRCBase02", CubeListBuilder.create().texOffs(128, 0).addBox(-5F, -5F, -4F, 5F, 10F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 0F, -6.5F, -0.1745F, -0.0524F, 0F));

        PartDefinition EquipRC03 = EquipRCBase02.addOrReplaceChild("EquipRC03", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 2F, -4F, 0F, 0.2094F, 0F));

        PartDefinition EquipRC04 = EquipRC03.addOrReplaceChild("EquipRC04", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipRC01 = EquipRCBase02.addOrReplaceChild("EquipRC01", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, -2F, -4F, 0F, 0.2618F, 0F));

        PartDefinition EquipRC02 = EquipRC01.addOrReplaceChild("EquipRC02", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipRCRadar = EquipRCBase02.addOrReplaceChild("EquipRCRadar", CubeListBuilder.create().texOffs(128, 0).addBox(-1F, -7.5F, 0F, 1F, 15F, 5F, new CubeDeformation(0F)), PartPose.offset(-5.2F, 0F, 5.5F));

        PartDefinition EquipR04_1 = EquipR03.addOrReplaceChild("EquipR04_1", CubeListBuilder.create().texOffs(128, 60).addBox(-6F, 0F, -10F, 6F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, -13F, 0F, -0.1745F, 0F));

        PartDefinition EquipRC2Base01 = EquipR04_1.addOrReplaceChild("EquipRC2Base01", CubeListBuilder.create().texOffs(128, 0).addBox(-4.5F, 0F, -10F, 9F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(-3F, -1F, -10F));

        PartDefinition EquipRC2Base02 = EquipRC2Base01.addOrReplaceChild("EquipRC2Base02", CubeListBuilder.create().texOffs(128, 0).addBox(-5F, -5F, -10F, 10F, 5F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -1F, 0.0524F, 0F, 0F));

        PartDefinition EquipRC203 = EquipRC2Base02.addOrReplaceChild("EquipRC203", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -4F, -8F, -0.1745F, 0F, 0F));

        PartDefinition EquipRC204 = EquipRC203.addOrReplaceChild("EquipRC204", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipRC201 = EquipRC2Base02.addOrReplaceChild("EquipRC201", CubeListBuilder.create().texOffs(128, 117).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -4F, -8F, -0.0873F, 0F, 0F));

        PartDefinition EquipRC202 = EquipRC201.addOrReplaceChild("EquipRC202", CubeListBuilder.create().texOffs(132, 113).addBox(-1F, -1F, -13F, 2F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition LegLeft = Butt.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(0, 80).mirror().addBox(-3F, 0F, -3F, 6F, 19F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 9.5F, -3F, -0.2618F, 0F, 0.0524F));

        PartDefinition ShoesL = LegLeft.addOrReplaceChild("ShoesL", CubeListBuilder.create().texOffs(22, 70).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 19F, -0.2F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -14F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -10F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, 0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        resetPoseState();
        resetOffsets();

        applyFaceAndMouth(entity);
        setFlushVisible(entity != null && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY
                || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        applyEquipVisibility(entity);

        if (isDeadPose(entity)) {
            this.applyDeadPose();
            this.syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);

        applyBasePose(ctx, ageInTicks, headPitch, limbSwing, limbSwingAmount);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks, limbSwingAmount);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        if (ArmLeft02 != null) {
            ArmLeft02.x = armLeft02DefaultX;
            ArmLeft02.y = armLeft02DefaultY;
            ArmLeft02.z = armLeft02DefaultZ;
        }
        if (ArmRight02 != null) {
            ArmRight02.x = armRight02DefaultX;
            ArmRight02.y = armRight02DefaultY;
            ArmRight02.z = armRight02DefaultZ;
        }
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        boolean showHead = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipNagato.EQUIP_HEAD);
        boolean showCannon = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipNagato.EQUIP_CANNON);
        if (HeadEquip != null) HeadEquip.visible = showHead;
        if (HeadEquip01 != null) HeadEquip01.visible = showHead;
        if (HeadEquip02 != null) HeadEquip02.visible = showHead;
        if (HeadEquip03 != null) HeadEquip03.visible = showHead;
        if (HeadEquip04 != null) HeadEquip04.visible = showHead;
        if (HeadEquip05 != null) HeadEquip05.visible = showHead;
        if (EquipBase != null) EquipBase.visible = showCannon;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.0f;
        this.Head.yRot = 0.0f;
        this.BoobL.xRot = -0.7854f;
        this.BoobR.xRot = -0.7854f;
        this.Ahoke.yRot = 0.5236f;
        this.ArmLeft01.yRot = 0.0f;
        this.ArmRight01.yRot = 0.0f;
        this.BodyMain.xRot = 1.48f;
        this.HairMidL01.xRot = 0.2f;
        this.HairMidL02.xRot = -0.3f;
        this.ArmLeft01.xRot = -2.97f;
        this.ArmLeft01.zRot = 0.26f;
        this.ArmRight01.xRot = -2.8f;
        this.ArmRight01.zRot = -1.3f;
        this.ArmRight02.zRot = -0.9f;
        this.LegLeft.xRot = -0.26f;
        this.LegRight.xRot = -0.26f;
        this.LegLeft.yRot = 0.0f;
        this.LegRight.yRot = 0.0f;
        this.LegLeft.zRot = -0.14f;
        this.LegRight.zRot = 0.14f;
        this.EquipBase.visible = false;
    }

    private void applyBasePose(PoseContext ctx, float ageInTicks, float headPitch, float limbSwing, float limbSwingAmount) {
        float f6;
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.08f + 0.3f + limbSwing * 0.5f);
        float angleAdd1 = Mth.cos(limbSwing * 0.7f) * limbSwingAmount;
        float angleAdd2 = Mth.cos(limbSwing * 0.7f + (float) Math.PI) * limbSwingAmount;

        this.BoobL.xRot = angleX * 0.06f - 0.7854f;
        this.BoobR.xRot = angleX * 0.06f - 0.7854f;
        this.Ahoke.yRot = angleX * 0.25f + 0.5236f;
        this.BodyMain.xRot = -0.1f;
        this.HairMidL01.xRot = angleX * 0.06f + 0.2f;
        this.HairMidL02.xRot = -angleX1 * 0.09f - 0.17f;
        this.HairMidL01.zRot = 0.0f;
        this.HairMidL02.zRot = 0.0f;
        this.ArmLeft01.xRot = angleAdd2 * 0.6f + 0.15f;
        this.ArmLeft01.yRot = 0.0f;
        this.ArmLeft01.zRot = angleX * 0.1f - 0.26f;
        this.ArmRight01.xRot = angleAdd1 * 0.6f;
        this.ArmRight01.yRot = 0.0f;
        this.ArmRight01.zRot = -angleX * 0.1f + 0.26f;
        this.ArmRight02.xRot = 0.0f;
        this.ArmRight02.yRot = 0.0f;
        this.ArmRight02.zRot = 0.0f;
        this.LegLeft.yRot = 0.0f;
        this.LegLeft.zRot = 0.05f;
        this.LegRight.yRot = 0.0f;
        this.LegRight.zRot = -0.05f;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks, float limbSwingAmount) {
        float angleX = ctx.angleX;
        float legAddLeft = ctx.angleAdd1 * 0.5F - 0.28F;
        float legAddRight = ctx.angleAdd2 * 0.5F - 0.21F;
        boolean showCannon = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipNagato.EQUIP_CANNON);

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = entity != null && entity.isSprinting() || limbSwingAmount > 0.9F;

        if (showCannon) {
            this.EquipBase.xRot = 0.17f;
            if (this.Head.xRot <= 0.0f) {
                this.EquipLC201.xRot = this.Head.xRot * 0.9f;
                this.EquipLC203.xRot = this.Head.xRot * 1.2f;
                this.EquipRC201.xRot = this.Head.xRot * 1.1f;
                this.EquipRC203.xRot = this.Head.xRot * 0.85f;
            }
            this.EquipLCBase02.xRot = this.Head.xRot;
            this.EquipLC2Base01.xRot = 0.0f;
            this.EquipLC2Base02.yRot = this.Head.yRot;
            this.EquipLC01.yRot = angleX * 0.1f - 0.26f;
            this.EquipLC03.yRot = -angleX * 0.08f - 0.15f;
            this.EquipRCBase02.xRot = this.Head.xRot;
            this.EquipRC2Base01.xRot = 0.0f;
            this.EquipRC2Base02.yRot = this.Head.yRot;
            this.EquipRC01.yRot = angleX * 0.08f + 0.2f;
            this.EquipRC03.yRot = -angleX * 0.1f + 0.1f;
        }

        if (isSprinting) {
            Head.xRot -= 0.35F;
            BodyMain.xRot = 0.5236F;
            HairMidL01.xRot += 0.3F;
            HairMidL02.xRot += 0.3F;

            ArmLeft01.xRot = ctx.angleAdd2 * 1.4F - 0.1F;
            ArmRight01.xRot = ctx.angleAdd1 * 1.4F - 0.1F;
            ArmLeft01.zRot = ctx.angleX * 0.1F - 0.4F;
            ArmRight01.zRot = -ctx.angleX * 0.1F + 0.4F;

            legAddLeft -= 0.55F;
            legAddRight -= 0.55F;
            LegLeft.yRot = 0.0F;
            LegRight.yRot = 0.0F;
            LegLeft.zRot = 0.0F;
            LegRight.zRot = 0.0F;
            if (showCannon) {
                EquipLCBase02.xRot -= 0.45F;
                EquipRCBase02.xRot -= 0.5F;
            }
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            Head.xRot -= 0.35F;
            BodyMain.xRot = 0.5236F;
            ArmLeft01.zRot = ctx.angleX * 0.1F - 0.4F;
            ArmRight01.zRot = -ctx.angleX * 0.1F + 0.4F;

            legAddLeft -= 0.55F;
            legAddRight -= 0.55F;
            LegLeft.yRot = 0.0F;
            LegRight.yRot = 0.0F;
            LegLeft.zRot = 0.0F;
            LegRight.zRot = 0.0F;
            if (showCannon) {
                EquipLCBase02.xRot -= 0.45F;
                EquipRCBase02.xRot -= 0.5F;
            }
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (showCannon) {
                this.poseTranslateY += 0.42F;
                BodyMain.xRot = -0.09F;
                ArmLeft01.xRot = 0.52F;
                ArmLeft01.zRot = -1.04F;
                ArmRight01.xRot = 0.52F;
                ArmRight01.zRot = 1.04F;
                legAddLeft = -1.4F;
                legAddRight = -1.4F;
                LegLeft.yRot = -0.14F;
                LegRight.yRot = 0.14F;
                LegLeft.zRot = 0.0F;
                LegRight.zRot = 0.0F;
                EquipLCBase02.xRot = 1.57F;
                EquipLC2Base01.xRot = 0.8F;
                EquipLC01.yRot = 0.0F;
                EquipLC03.yRot = 0.0F;
                EquipLC201.xRot = 0.0F;
                EquipLC203.xRot = 0.0F;
                EquipRCBase02.xRot = 1.57F;
                EquipRC2Base01.xRot = 0.8F;
                EquipRC01.yRot = 0.0F;
                EquipRC03.yRot = 0.0F;
                EquipRC201.xRot = 0.0F;
                EquipRC203.xRot = 0.0F;
            } else if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.71F * 2.7;
                BodyMain.xRot = 1.48F;
                HairMidL01.xRot = 0.2F;
                HairMidL02.xRot = -0.3F;
                ArmLeft01.xRot = -2.97F;
                ArmLeft01.zRot = 0.26F;
                ArmRight01.xRot = -2.8F;
                ArmRight01.zRot = -1.3F;
                ArmRight02.zRot = -0.9F;
                legAddLeft = -0.26F;
                legAddRight = -0.26F;
                LegLeft.yRot = 0.0F;
                LegRight.yRot = 0.0F;
                LegLeft.zRot = -0.14F;
                LegRight.zRot = 0.14F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                BodyMain.xRot = -0.09F;
                ArmLeft01.xRot = -0.63F;
                ArmLeft01.zRot = 0.14F;
                ArmRight01.xRot = -0.63F;
                ArmRight01.zRot = -0.14F;
                legAddLeft = -1.75F;
                legAddRight = -1.75F;
                LegLeft.yRot = -0.14F;
                LegRight.yRot = 0.14F;
                LegLeft.zRot = 0.0F;
                LegRight.zRot = 0.0F;
            }
        }

        if (entity != null && entity.getAttackTick() > 20) {
            if (entity.getStateEmotion(5) == 0 || entity.getStateEmotion(5) == 2) {
                this.poseTranslateY += 0.35F;
                Head.xRot -= 1.22F;
                BodyMain.xRot = 1.75F;
                HairMidL01.xRot += 0.3F;
                HairMidL02.xRot += 0.6F;
                ArmLeft01.xRot = -1.75F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = 0.0F;
                ArmRight01.xRot = -1.05F;
                ArmRight01.yRot = 2.62F;
                ArmRight01.zRot = 0.7F;
                ArmRight02.zRot = -0.79F;
                legAddLeft = -1.75F;
                legAddRight = -2.27F;
                LegLeft.yRot = -0.44F;
                LegRight.yRot = 0.44F;
                LegLeft.zRot = 0.0F;
                LegRight.zRot = 0.0F;
                EquipBase.xRot = -1.22F;
                EquipLCBase02.xRot -= 0.5F;
                EquipRCBase02.xRot -= 0.5F;
            } else {
                BodyMain.xRot = -0.17F;
                ArmLeft01.xRot = -1.57F;
                ArmLeft01.yRot = -0.26F;
                ArmLeft01.zRot = 0.0F;
                ArmRight01.xRot = 0.0F;
                ArmRight01.zRot = 0.87F;
                ArmRight02.zRot = -1.57F;
                legAddLeft += 0.2618F;
                legAddRight += 0.2618F;
                LegLeft.yRot = 0.0F;
                LegRight.yRot = 0.0F;
                LegLeft.zRot = -0.17F;
                LegRight.zRot = 0.17F;
            }
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot = -0.4F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.2F;
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
            ArmRight02.xRot = 0.0F;
            ArmRight02.yRot = 0.0F;
            ArmRight02.zRot = 0.0F;
        }

        LegLeft.xRot = legAddLeft;
        LegRight.xRot = legAddRight;
    }

    private boolean isDeadPose(T entity) {
        if (entity == null) return false;
        return entity instanceof org.trp.shincolle.entity.base.EntityShipBase ship && ship.isInDeadPose();
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
        }
        if (this.GlowNeck != null && this.Neck != null) {
            GlowNeck.copyFrom(Neck);
        }
        if (this.GlowHead != null && this.Head != null) {
            GlowHead.copyFrom(Head);
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
