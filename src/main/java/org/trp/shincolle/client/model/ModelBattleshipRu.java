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

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelBattleshipRu<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "battleship_ru"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBattleshipRu");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBattleshipRu");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBattleshipRu");

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
    private final ModelPart EquipBase;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft02;
    private final ModelPart Shoe01;
    private final ModelPart LegRight02;
    private final ModelPart Shoe02;
    private final ModelPart ArmRight02;
    private final ModelPart EquipRBase;
    private final ModelPart EquipR01;
    private final ModelPart EquipRC01a;
    private final ModelPart EquipRC02a;
    private final ModelPart EquipRC03a;
    private final ModelPart EquipRC04a;
    private final ModelPart EquipR02a;
    private final ModelPart EquipR03a;
    private final ModelPart EquipR04a;
    private final ModelPart EquipR05a;
    private final ModelPart EquipR06a;
    private final ModelPart EquipR07;
    private final ModelPart EquipR02b;
    private final ModelPart EquipR03b;
    private final ModelPart EquipR04b;
    private final ModelPart EquipR05b;
    private final ModelPart EquipR06b;
    private final ModelPart EquipR08;
    private final ModelPart EquipR09;
    private final ModelPart EquipR10;
    private final ModelPart EquipRC01b;
    private final ModelPart EquipRC01c;
    private final ModelPart EquipRC02b;
    private final ModelPart EquipRC03b;
    private final ModelPart EquipRC03c;
    private final ModelPart ArmLeft02;
    private final ModelPart EquipLBase;
    private final ModelPart EquipL01;
    private final ModelPart EquipLC01a;
    private final ModelPart EquipLC02a;
    private final ModelPart EquipLC03a;
    private final ModelPart EquipLC04a;
    private final ModelPart EquipL02a;
    private final ModelPart EquipL03a;
    private final ModelPart EquipL04a;
    private final ModelPart EquipL05a;
    private final ModelPart EquipL06a;
    private final ModelPart EquipL07;
    private final ModelPart EquipL02b;
    private final ModelPart EquipL03b;
    private final ModelPart EquipL04b;
    private final ModelPart EquipL05b;
    private final ModelPart EquipL06b;
    private final ModelPart EquipL08;
    private final ModelPart EquipL09;
    private final ModelPart EquipL10;
    private final ModelPart EquipLC01b;
    private final ModelPart EquipLC01c;
    private final ModelPart EquipLC02b;
    private final ModelPart EquipLC03b;
    private final ModelPart EquipLC03c;
    private final ModelPart Equip01a;
    private final ModelPart Equip01b;
    private final ModelPart Equip02;
    private final ModelPart Equip03a;
    private final ModelPart EquipCB01;
    private final ModelPart Equip03b;
    private final ModelPart EquipCB03;
    private final ModelPart EquipCB02a;
    private final ModelPart EquipCB02b;
    private final ModelPart EquipCB04a;
    private final ModelPart EquipCB04b;
    private final ModelPart GloveR;
    private final ModelPart GloveL;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart Skirt01;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;

    public ModelBattleshipRu(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.Equip02 = this.EquipBase.getChild("Equip02");
        this.Equip03b = this.Equip02.getChild("Equip03b");
        this.EquipCB03 = this.Equip02.getChild("EquipCB03");
        this.EquipCB04b = this.EquipCB03.getChild("EquipCB04b");
        this.EquipCB04a = this.EquipCB03.getChild("EquipCB04a");
        this.EquipCB01 = this.Equip02.getChild("EquipCB01");
        this.EquipCB02a = this.EquipCB01.getChild("EquipCB02a");
        this.EquipCB02b = this.EquipCB01.getChild("EquipCB02b");
        this.Equip03a = this.Equip02.getChild("Equip03a");
        this.Equip01b = this.EquipBase.getChild("Equip01b");
        this.Equip01a = this.EquipBase.getChild("Equip01a");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.EquipRBase = this.ArmRight02.getChild("EquipRBase");
        this.EquipRC02a = this.EquipRBase.getChild("EquipRC02a");
        this.EquipRC02b = this.EquipRC02a.getChild("EquipRC02b");
        this.EquipR04a = this.EquipRBase.getChild("EquipR04a");
        this.EquipR03a = this.EquipRBase.getChild("EquipR03a");
        this.EquipR02a = this.EquipRBase.getChild("EquipR02a");
        this.EquipR06a = this.EquipRBase.getChild("EquipR06a");
        this.EquipR04b = this.EquipRBase.getChild("EquipR04b");
        this.EquipR02b = this.EquipRBase.getChild("EquipR02b");
        this.EquipR01 = this.EquipRBase.getChild("EquipR01");
        this.EquipR09 = this.EquipRBase.getChild("EquipR09");
        this.EquipR05a = this.EquipRBase.getChild("EquipR05a");
        this.EquipRC01a = this.EquipRBase.getChild("EquipRC01a");
        this.EquipRC01b = this.EquipRC01a.getChild("EquipRC01b");
        this.EquipRC01c = this.EquipRC01a.getChild("EquipRC01c");
        this.EquipR05b = this.EquipRBase.getChild("EquipR05b");
        this.EquipRC03a = this.EquipRBase.getChild("EquipRC03a");
        this.EquipRC03b = this.EquipRC03a.getChild("EquipRC03b");
        this.EquipRC03c = this.EquipRC03a.getChild("EquipRC03c");
        this.EquipR06b = this.EquipRBase.getChild("EquipR06b");
        this.EquipR10 = this.EquipRBase.getChild("EquipR10");
        this.EquipR08 = this.EquipRBase.getChild("EquipR08");
        this.EquipR07 = this.EquipRBase.getChild("EquipR07");
        this.EquipR03b = this.EquipRBase.getChild("EquipR03b");
        this.EquipRC04a = this.EquipRBase.getChild("EquipRC04a");
        this.GloveR = this.ArmRight02.getChild("GloveR");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.EquipLBase = this.ArmLeft02.getChild("EquipLBase");
        this.EquipL04a = this.EquipLBase.getChild("EquipL04a");
        this.EquipLC01a = this.EquipLBase.getChild("EquipLC01a");
        this.EquipLC01b = this.EquipLC01a.getChild("EquipLC01b");
        this.EquipLC01c = this.EquipLC01a.getChild("EquipLC01c");
        this.EquipL06b = this.EquipLBase.getChild("EquipL06b");
        this.EquipLC02a = this.EquipLBase.getChild("EquipLC02a");
        this.EquipLC02b = this.EquipLC02a.getChild("EquipLC02b");
        this.EquipL08 = this.EquipLBase.getChild("EquipL08");
        this.EquipL04b = this.EquipLBase.getChild("EquipL04b");
        this.EquipL05b = this.EquipLBase.getChild("EquipL05b");
        this.EquipLC03a = this.EquipLBase.getChild("EquipLC03a");
        this.EquipLC03b = this.EquipLC03a.getChild("EquipLC03b");
        this.EquipLC03c = this.EquipLC03a.getChild("EquipLC03c");
        this.EquipL06a = this.EquipLBase.getChild("EquipL06a");
        this.EquipL10 = this.EquipLBase.getChild("EquipL10");
        this.EquipL02a = this.EquipLBase.getChild("EquipL02a");
        this.EquipL07 = this.EquipLBase.getChild("EquipL07");
        this.EquipL09 = this.EquipLBase.getChild("EquipL09");
        this.EquipL01 = this.EquipLBase.getChild("EquipL01");
        this.EquipLC04a = this.EquipLBase.getChild("EquipLC04a");
        this.EquipL05a = this.EquipLBase.getChild("EquipL05a");
        this.EquipL03a = this.EquipLBase.getChild("EquipL03a");
        this.EquipL02b = this.EquipLBase.getChild("EquipL02b");
        this.EquipL03b = this.EquipLBase.getChild("EquipL03b");
        this.GloveL = this.ArmLeft02.getChild("GloveL");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.Shoe02 = this.LegRight02.getChild("Shoe02");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.Shoe01 = this.LegLeft02.getChild("Shoe01");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(54, 121).addBox(-2.5F, -2F, -3.6F, 5F, 2F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(50, 54).addBox(-7.5F, 0F, 0F, 15F, 14F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.1396F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 63).addBox(-7F, 0F, -5F, 14F, 12F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, 6.5F, -0.1222F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 15F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(114, 45).addBox(-1.5F, 0F, 0F, 5F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.7F, -6F, -7.5F, -0.0873F, 0F, 0.1367F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.8F, 6.5F, -6.3F, -0.0873F, -0.07F, 0.0524F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 0F, 0.1222F, 0F, -0.0873F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(88, 101).mirror().addBox(-1F, 0F, 0F, 2F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.8F, 6.5F, -6.3F, -0.0873F, 0.07F, -0.0524F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 100).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 10F, 0F, 0.0873F, 0F, 0.0873F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -9F, -3.4F, -0.6981F, 0.0873F, 0.0873F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 1F));

        PartDefinition Equip02 = EquipBase.addOrReplaceChild("Equip02", CubeListBuilder.create().texOffs(4, 4).addBox(-6F, 0F, 0F, 12F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, 3F));

        PartDefinition Equip03b = Equip02.addOrReplaceChild("Equip03b", CubeListBuilder.create().texOffs(66, 0).addBox(-10F, 0F, 0F, 10F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -2F, 0.5F, -0.4363F, 0.2618F, -0.7854F));

        PartDefinition EquipCB03 = Equip02.addOrReplaceChild("EquipCB03", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(-10F, 0F, 0F, 10F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -4F, 1F, -0.3491F, 0.3491F, -0.4363F));

        PartDefinition EquipCB04b = EquipCB03.addOrReplaceChild("EquipCB04b", CubeListBuilder.create().texOffs(11, 8).addBox(-1F, -9F, -1F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 1F, 5F, 0.6109F, 0F, 0F));

        PartDefinition EquipCB04a = EquipCB03.addOrReplaceChild("EquipCB04a", CubeListBuilder.create().texOffs(9, 4).addBox(-1F, -9F, -1F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 1F, 5F, 0.7854F, 0F, 0F));

        PartDefinition EquipCB01 = Equip02.addOrReplaceChild("EquipCB01", CubeListBuilder.create().texOffs(66, 0).addBox(0F, 0F, 0F, 10F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, -4F, 1F, -0.3491F, -0.3491F, 0.4363F));

        PartDefinition EquipCB02a = EquipCB01.addOrReplaceChild("EquipCB02a", CubeListBuilder.create().texOffs(13, 8).mirror().addBox(-1F, -9F, -1F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 1F, 5F, 0.5236F, 0F, 0F));

        PartDefinition EquipCB02b = EquipCB01.addOrReplaceChild("EquipCB02b", CubeListBuilder.create().texOffs(13, 8).addBox(-1F, -9F, -1F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 1F, 5F, 0.8727F, 0F, 0F));

        PartDefinition Equip03a = Equip02.addOrReplaceChild("Equip03a", CubeListBuilder.create().texOffs(66, 0).addBox(0F, 0F, 0F, 10F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, -2F, 0.5F, -0.4363F, -0.2618F, 0.7854F));

        PartDefinition Equip01b = EquipBase.addOrReplaceChild("Equip01b", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(-10F, 0F, 0F, 10F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -3F, -5.5F, 0F, 0.1745F, 0.3491F));

        PartDefinition Equip01a = EquipBase.addOrReplaceChild("Equip01a", CubeListBuilder.create().texOffs(66, 0).addBox(0F, 0F, 0F, 10F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -3F, -5.5F, 0F, -0.1745F, -0.3491F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9F, -0.7F, 0F, 0.4363F, 0.3491F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition EquipRBase = ArmRight02.addOrReplaceChild("EquipRBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(2F, 12F, -3F));

        PartDefinition EquipRC02a = EquipRBase.addOrReplaceChild("EquipRC02a", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 8F, 11F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 1.5F, 0F));

        PartDefinition EquipRC02b = EquipRC02a.addOrReplaceChild("EquipRC02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 10F, 4F));

        PartDefinition EquipR04a = EquipRBase.addOrReplaceChild("EquipR04a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 14F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.5F, 1F, 1.2F, 0.0873F, 0F, 0F));

        PartDefinition EquipR03a = EquipRBase.addOrReplaceChild("EquipR03a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, -2.4F, -3F, -0.2269F, -0.1396F, 0F));

        PartDefinition EquipR02a = EquipRBase.addOrReplaceChild("EquipR02a", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, 0F, 1F, 21F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -4F, -9F, -0.5236F, -0.1745F, 0F));

        PartDefinition EquipR06a = EquipRBase.addOrReplaceChild("EquipR06a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 14F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9.6F, -3.3F, 24.2F, 0.3491F, 0.6981F, 0.2618F));

        PartDefinition EquipR04b = EquipRBase.addOrReplaceChild("EquipR04b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 1F, 14F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.5F, 1F, 1.2F, 0.0873F, 0F, 0F));

        PartDefinition EquipR02b = EquipRBase.addOrReplaceChild("EquipR02b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, 0F, 1F, 21F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, -4F, -9F, -0.5236F, 0.1745F, 0F));

        PartDefinition EquipR01 = EquipRBase.addOrReplaceChild("EquipR01", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 8F, 5F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, -2F, 0F, 0F, -1.5708F, 0F));

        PartDefinition EquipR09 = EquipRBase.addOrReplaceChild("EquipR09", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, 0F, 12F, 12F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.2F, 20.6F, 0.3142F, 0F, 0F));

        PartDefinition EquipR05a = EquipRBase.addOrReplaceChild("EquipR05a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 14F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 0F, 13F, 0.2618F, -0.2618F, 0F));

        PartDefinition EquipRC01a = EquipRBase.addOrReplaceChild("EquipRC01a", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 12F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -6F, 0.1396F, 0F, 0F));

        PartDefinition EquipRC01b = EquipRC01a.addOrReplaceChild("EquipRC01b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.8F, 11F, 2F, -0.0524F, 0F, 0F));

        PartDefinition EquipRC01c = EquipRC01a.addOrReplaceChild("EquipRC01c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offset(1.8F, 11F, 2F));

        PartDefinition EquipR05b = EquipRBase.addOrReplaceChild("EquipR05b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 1F, 14F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 0F, 13F, 0.2618F, 0.2618F, 0F));

        PartDefinition EquipRC03a = EquipRBase.addOrReplaceChild("EquipRC03a", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 11F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 9F, -0.1745F, 0F, 0F));

        PartDefinition EquipRC03b = EquipRC03a.addOrReplaceChild("EquipRC03b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.8F, 10F, 3.5F, 0.1047F, 0F, 0F));

        PartDefinition EquipRC03c = EquipRC03a.addOrReplaceChild("EquipRC03c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.8F, 10F, 3.5F, 0.1396F, 0F, 0F));

        PartDefinition EquipR06b = EquipRBase.addOrReplaceChild("EquipR06b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 1F, 14F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9.6F, -3.3F, 24.2F, 0.3491F, -0.6981F, -0.2618F));

        PartDefinition EquipR10 = EquipRBase.addOrReplaceChild("EquipR10", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 4F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.7F, 1F, 14F, 0.1745F, -0.1745F, 0F));

        PartDefinition EquipR08 = EquipRBase.addOrReplaceChild("EquipR08", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 13F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -10F, -0.1396F, 0F, 0F));

        PartDefinition EquipR07 = EquipRBase.addOrReplaceChild("EquipR07", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 13F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4F, 29F, -0.0873F, 0F, 0F));

        PartDefinition EquipR03b = EquipRBase.addOrReplaceChild("EquipR03b", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1F, 0F, 0F, 1F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, -2.4F, -3F, -0.2269F, 0.1396F, 0F));

        PartDefinition EquipRC04a = EquipRBase.addOrReplaceChild("EquipRC04a", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 13F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 12.5F, 0.1396F, 0F, 0F));

        PartDefinition GloveR = ArmRight02.addOrReplaceChild("GloveR", CubeListBuilder.create().texOffs(2, 34).addBox(2.5F, 5.5F, -2.5F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 0F, -3F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 84).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9F, -0.7F, 0.2276F, -0.4363F, -0.3491F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 84).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition EquipLBase = ArmLeft02.addOrReplaceChild("EquipLBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, -3F));

        PartDefinition EquipL04a = EquipLBase.addOrReplaceChild("EquipL04a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 14F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.5F, 1F, 1.2F, 0.0873F, 0F, 0F));

        PartDefinition EquipLC01a = EquipLBase.addOrReplaceChild("EquipLC01a", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 12F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -6F, 0.1396F, 0F, 0F));

        PartDefinition EquipLC01b = EquipLC01a.addOrReplaceChild("EquipLC01b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.8F, 11F, 2F, -0.0524F, 0F, 0F));

        PartDefinition EquipLC01c = EquipLC01a.addOrReplaceChild("EquipLC01c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offset(1.8F, 11F, 2F));

        PartDefinition EquipL06b = EquipLBase.addOrReplaceChild("EquipL06b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 1F, 14F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9.6F, -3.3F, 24.2F, 0.3491F, -0.6981F, -0.2618F));

        PartDefinition EquipLC02a = EquipLBase.addOrReplaceChild("EquipLC02a", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 8F, 11F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 1.5F, 0F));

        PartDefinition EquipLC02b = EquipLC02a.addOrReplaceChild("EquipLC02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 10F, 4F));

        PartDefinition EquipL08 = EquipLBase.addOrReplaceChild("EquipL08", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 13F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -10F, -0.1396F, 0F, 0F));

        PartDefinition EquipL04b = EquipLBase.addOrReplaceChild("EquipL04b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 1F, 14F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.5F, 1F, 1.2F, 0.0873F, 0F, 0F));

        PartDefinition EquipL05b = EquipLBase.addOrReplaceChild("EquipL05b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 1F, 14F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 0F, 13F, 0.2618F, 0.2618F, 0F));

        PartDefinition EquipLC03a = EquipLBase.addOrReplaceChild("EquipLC03a", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, 0F, 9F, 11F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 9F, -0.1745F, 0F, 0F));

        PartDefinition EquipLC03b = EquipLC03a.addOrReplaceChild("EquipLC03b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.8F, 10F, 3.5F, 0.1047F, 0F, 0F));

        PartDefinition EquipLC03c = EquipLC03a.addOrReplaceChild("EquipLC03c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.8F, 10F, 3.5F, 0.1396F, 0F, 0F));

        PartDefinition EquipL06a = EquipLBase.addOrReplaceChild("EquipL06a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 14F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9.6F, -3.3F, 24.2F, 0.3491F, 0.6981F, 0.2618F));

        PartDefinition EquipL10 = EquipLBase.addOrReplaceChild("EquipL10", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.7F, 1F, 14F, 0.1745F, 0.1745F, 0F));

        PartDefinition EquipL02a = EquipLBase.addOrReplaceChild("EquipL02a", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, 0F, 1F, 21F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -4F, -9F, -0.5236F, -0.1745F, 0F));

        PartDefinition EquipL07 = EquipLBase.addOrReplaceChild("EquipL07", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 13F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4F, 29F, -0.0873F, 0F, 0F));

        PartDefinition EquipL09 = EquipLBase.addOrReplaceChild("EquipL09", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, 0F, 12F, 12F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.2F, 20.6F, 0.3142F, 0F, 0F));

        PartDefinition EquipL01 = EquipLBase.addOrReplaceChild("EquipL01", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 8F, 5F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, -2F, 0F, 0F, -1.5708F, 0F));

        PartDefinition EquipLC04a = EquipLBase.addOrReplaceChild("EquipLC04a", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 13F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 12.5F, 0.1396F, 0F, 0F));

        PartDefinition EquipL05a = EquipLBase.addOrReplaceChild("EquipL05a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 14F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 0F, 13F, 0.2618F, -0.2618F, 0F));

        PartDefinition EquipL03a = EquipLBase.addOrReplaceChild("EquipL03a", CubeListBuilder.create().texOffs(46, 0).addBox(0F, 0F, 0F, 1F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, -2.4F, -3F, -0.2269F, -0.1396F, 0F));

        PartDefinition EquipL02b = EquipLBase.addOrReplaceChild("EquipL02b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, 0F, 1F, 21F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, -4F, -9F, -0.5236F, 0.1745F, 0F));

        PartDefinition EquipL03b = EquipLBase.addOrReplaceChild("EquipL03b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 1F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, -2.4F, -3F, -0.2269F, 0.1396F, 0F));

        PartDefinition GloveL = ArmLeft02.addOrReplaceChild("GloveL", CubeListBuilder.create().texOffs(2, 34).addBox(-2.5F, 5.5F, -2.5F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 0F, -3F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 47).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.1396F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(0F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition Shoe02 = LegRight02.addOrReplaceChild("Shoe02", CubeListBuilder.create().texOffs(0, 33).addBox(-3.5F, 0F, -3.5F, 7F, 6F, 7F, new CubeDeformation(0F)), PartPose.offset(3F, 9F, 3F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(46, 41).addBox(-8.5F, 0F, -6F, 17F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.9F, 0F, -0.1396F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 84).addBox(-6F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition Shoe01 = LegLeft02.addOrReplaceChild("Shoe01", CubeListBuilder.create().texOffs(0, 33).addBox(-3.5F, 0F, -3.5F, 7F, 6F, 7F, new CubeDeformation(0F)), PartPose.offset(-3F, 9F, 3F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -9F, -3.4F, -0.6981F, -0.0873F, -0.0873F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
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
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);

        applyBasePose(ctx, limbSwing, limbSwingAmount, ageInTicks, headPitch);
        applySpecialPoseAdjustments(entity, ctx, limbSwing, limbSwingAmount, ageInTicks);
        applyHairAnimation(ctx, limbSwing, ageInTicks, limbSwingAmount);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        if (LegLeft02 != null) {
            LegLeft02.y = legLeft02DefaultY;
            LegLeft02.z = legLeft02DefaultZ;
        }
        if (LegRight02 != null) {
            LegRight02.y = legRight02DefaultY;
            LegRight02.z = legRight02DefaultZ;
        }
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        boolean showWeapon = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipRu.EQUIP_WEAPON);
        boolean showBase = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipRu.EQUIP_BASE);
        boolean showGlove = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipRu.EQUIP_GLOVES);
        if (EquipLBase != null) EquipLBase.visible = showWeapon;
        if (EquipRBase != null) EquipRBase.visible = showWeapon;
        if (EquipBase != null) EquipBase.visible = showBase;
        if (GloveL != null) GloveL.visible = showGlove;
        if (GloveR != null) GloveR.visible = showGlove;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = 0.0F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;

        BoobL.xRot = -0.7F;
        BoobR.xRot = -0.7F;
        Ahoke.yRot = 0.5236F;

        BodyMain.xRot = 1.4F;
        BodyMain.yRot = 0.0F;
        Neck.xRot = 0.1F;

        ArmLeft01.xRot = -3.0F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = 0.2618F;

        ArmRight01.xRot = -3.0F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -0.2618F;

        LegLeft01.xRot = -0.1F;
        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.0873F;

        LegRight01.xRot = -0.1F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -0.0873F;

        HairL01.xRot = -0.2F;
        HairR01.xRot = -0.2F;
        HairL02.xRot = -0.1F;
        HairR02.xRot = -0.1F;
        Hair01.xRot = -0.4F;
        Hair02.xRot = -0.3F;
    }

    private void applyBasePose(PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks, float headPitch) {
        float angleX = ctx.angleX;

        BoobL.xRot = angleX * 0.06F - 0.7F;
        BoobR.xRot = angleX * 0.06F - 0.7F;
        Ahoke.yRot = angleX * 0.25F + 0.5236F;

        BodyMain.xRot = -0.1047F;
        BodyMain.yRot = 0.0F;
        Neck.xRot = 0.1047F;

        ArmLeft01.xRot = ctx.angleAdd2 * 0.4f + 0.1f;
        ArmLeft01.yRot = 0.0F;

        ArmRight01.xRot = ctx.angleAdd1 * 0.4f;
        ArmRight01.yRot = 0.0F;

        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 0.0F;


        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.0873F;
        LegLeft02.xRot = 0.0F;

        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -0.0873F;
        LegRight02.xRot = 0.0F;

        this.EquipLBase.xRot = 0.0f;
        this.EquipLBase.yRot = 0.0f;
        this.EquipLBase.zRot = 0.0f;
        this.EquipRBase.xRot = 0.0f;
        this.EquipRBase.yRot = 0.0f;
        this.EquipRBase.zRot = 0.0f;
        this.EquipCB02a.xRot = this.Head.xRot * 0.9f + 0.8f;
        this.EquipCB02b.xRot = this.Head.xRot * 0.8f + 0.9f;
        this.EquipCB04a.xRot = this.Head.xRot * 1.1f + 0.7f;
        this.EquipCB04b.xRot = this.Head.xRot * 0.9f + 0.8f;
        this.EquipLC01b.xRot = this.Head.xRot * 0.9f - 0.05f;
        this.EquipLC01c.xRot = this.Head.xRot * 0.8f - 0.08f;
        this.EquipLC02b.xRot = this.Head.xRot * 1.1f + 0.1f;
        this.EquipLC03b.xRot = this.Head.xRot * 0.9f + 0.05f;
        this.EquipLC03c.xRot = this.Head.xRot * 0.8f + 0.08f;
        this.EquipRC01b.xRot = this.Head.xRot * 0.9f - 0.05f;
        this.EquipRC01c.xRot = this.Head.xRot * 0.8f - 0.08f;
        this.EquipRC02b.xRot = this.Head.xRot * 1.1f + 0.1f;
        this.EquipRC03b.xRot = this.Head.xRot * 0.9f + 0.05f;
        this.EquipRC03c.xRot = this.Head.xRot * 0.8f + 0.08f;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleX = ctx.angleX;

        float legAddLeft = ctx.angleAdd1 * 0.5F - 0.18F;
        float legAddRight = ctx.angleAdd2 * 0.5F - 0.18F;
        boolean showWeapon = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipRu.EQUIP_WEAPON);
        boolean spStand = entity != null && showWeapon && hasLegacyState(entity, 1, 4) && ((entity.tickCount & 0x1FF) > 400);

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        if (showWeapon) {
            this.ArmLeft01.zRot = angleX * 0.03f - 0.3f;
            this.ArmRight01.zRot = -angleX * 0.03f + 0.3f;
        } else {
            this.ArmLeft01.zRot = angleX * 0.03f - 0.15f;
            this.ArmRight01.zRot = -angleX * 0.03f + 0.15f;
        }

        if (spStand) {
            this.poseTranslateY += 0.12F;
            BodyMain.xRot = 1.0472F;
            Head.xRot -= 0.18203785F;
            ArmLeft01.xRot = -1.0472F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft01.zRot = -0.34906584F;
            ArmRight01.xRot = -1.0472F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = 0.34906584F;
            legAddLeft = -1.3962634F;
            legAddRight = -1.3962634F;
            LegLeft01.yRot = 0.0F;
            LegLeft01.zRot = 0.08726646F;
            LegRight01.yRot = 0.0F;
            LegRight01.zRot = -0.08726646F;
        }

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = entity != null && entity.isSprinting() || limbSwingAmount > 0.9F;

        if (isSprinting) {
            if (spStand) this.poseTranslateY -= 0.12F;
            this.poseTranslateY += 0.05F;
            BodyMain.xRot = -0.1F;

            if (showWeapon) {
                ArmLeft01.xRot = ctx.angleAdd2 * 0.05F + 0.5F;
                ArmRight01.xRot = ctx.angleAdd1 * 0.05F + 0.5F;
            } else {
                ArmLeft01.xRot = ctx.angleAdd2 * 0.9F + 0.5F;
                ArmRight01.xRot = ctx.angleAdd1 * 0.9F + 0.5F;
            }

            ArmLeft01.yRot = 0.0F;
            ArmLeft02.xRot = -1.0F;
            ArmLeft02.zRot = 0.0F;
            ArmRight01.yRot = 0.0F;
            ArmRight02.xRot = -1.0F;
            ArmRight02.zRot = 0.0F;

            legAddLeft = ctx.angleAdd1 * 0.7F - 0.28F;
            legAddRight = ctx.angleAdd2 * 0.7F - 0.21F;
            LegLeft01.yRot = 0.0F;
            LegLeft01.zRot = 0.0873F;
            LegRight01.yRot = 0.0F;
            LegRight01.zRot = -0.0873F;

            EquipLBase.xRot = 0.5F;
            EquipLBase.yRot = 0.0F;
            EquipLBase.zRot = 0.0F;
            EquipRBase.xRot = 0.5F;
            EquipRBase.yRot = 0.0F;
            EquipRBase.zRot = 0.0F;
        }

        if (isCrouching) {
            if (spStand) this.poseTranslateY -= 0.12F;
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            Head.xRot -= 0.6283F;
            BodyMain.xRot = 0.8727F;

            if (showWeapon) {
                ArmLeft01.xRot = ctx.angleAdd2 * 0.05F + 0.5F;
                ArmLeft01.zRot = -0.25F;
                ArmLeft02.xRot = -1.0F;
                ArmRight01.xRot = ctx.angleAdd1 * 0.05F + 0.5F;
                ArmRight01.zRot = 0.25F;
                ArmRight02.xRot = -1.0F;
            } else {
                ArmLeft01.xRot = -0.35F;
                ArmLeft01.zRot = 0.2618F;
                ArmLeft02.xRot = 0.0F;
                ArmRight01.xRot = -0.35F;
                ArmRight01.zRot = -0.2618F;
                ArmRight02.xRot = 0.0F;
            }

            ArmLeft01.yRot = 0.0F;
            ArmLeft02.zRot = 0.0F;
            ArmRight01.yRot = 0.0F;
            ArmRight02.zRot = 0.0F;

            legAddLeft -= 1.1F;
            legAddRight -= 1.1F;
            Hair01.xRot += 0.37F;
            Hair02.xRot += 0.23F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (spStand) this.poseTranslateY -= 0.12F;

            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.25F * 3;
                BodyMain.xRot = -0.10471976F;
                BodyMain.yRot = -0.34906584F;
                Head.yRot -= 0.5235988F;
                ArmLeft01.xRot = 0.87266463F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = -0.34906584F;
                ArmLeft02.xRot = -0.7853982F;
                ArmLeft02.yRot = 0.0F;
                ArmLeft02.zRot = 0.0F;
                ArmRight01.xRot = -0.43633232F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.34906584F;
                ArmRight02.xRot = -0.87266463F;
                ArmRight02.yRot = 0.0F;
                ArmRight02.zRot = 0.0F;
                legAddLeft = -1.4835298F;
                legAddRight = -0.43633232F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.08726646F;
                LegLeft02.xRot = 1.3962634F;
                LegLeft02.yRot = 0.0F;
                LegLeft02.zRot = 0.0F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.08726646F;
                LegRight02.xRot = 1.4835298F;
                LegRight02.yRot = 0.0F;
                LegRight02.zRot = 0.0F;
            } else if (entity != null && hasLegacyState(entity, 7, 4) && showWeapon) {
                this.poseTranslateY += 0.52F * 3;
                BodyMain.xRot = 0.7853982F;
                Butt.xRot = 0.2617994F;
                Head.xRot = 0.5235988F;
                Hair01.xRot = -0.34906584F;
                Hair02.xRot = -0.12217305F;
                ArmLeft01.xRot = 2.6179938F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = 0.0F;
                ArmRight01.xRot = 2.6179938F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.0F;
                legAddLeft = 0.2617994F;
                legAddRight = 0.2617994F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.08726646F;
                LegLeft02.xRot = 0.2617994F;
                LegLeft02.yRot = 0.0F;
                LegLeft02.zRot = 0.0F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.08726646F;
                LegRight02.xRot = 0.2617994F;
                LegRight02.yRot = 0.0F;
                LegRight02.zRot = 0.0F;
                EquipLBase.xRot = 1.2217305F;
                EquipLBase.yRot = 0.0F;
                EquipLBase.zRot = 0.0F;
                EquipRBase.xRot = 1.2217305F;
                EquipRBase.yRot = 0.0F;
                EquipRBase.zRot = 0.0F;
            } else if (showWeapon) {
                this.poseTranslateY += 0.2F * 3;
                BodyMain.xRot = 0.18203785F;
                Butt.xRot = 0.2617994F;
                Head.xRot -= 0.20943952F;
                ArmLeft01.xRot = 0.13962634F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = -0.34906584F;
                ArmRight01.xRot = -1.1838568F;
                ArmRight01.yRot = 0.8F;
                ArmRight01.zRot = 0.0F;
                ArmRight02.xRot = -1.3089969F;
                ArmRight02.yRot = 0.0F;
                ArmRight02.zRot = 0.0F;
                legAddLeft = -1.61F;
                legAddRight = -1.57F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.08726646F;
                LegLeft02.xRot = 1.5F;
                LegLeft02.yRot = 0.0F;
                LegLeft02.zRot = 0.0F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.08726646F;
                LegRight02.xRot = 0.6F;
                LegRight02.yRot = 0.0F;
                LegRight02.zRot = 0.0F;
                EquipLBase.xRot = 0.0F;
                EquipLBase.yRot = -1.5707964F;
                EquipLBase.zRot = 0.31415927F;
                EquipRBase.xRot = 0.7285004F;
                EquipRBase.yRot = 0.0F;
                EquipRBase.zRot = 0.0F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                BodyMain.xRot = 0.27314404F;
                Butt.xRot = 0.2617994F;
                Head.xRot -= 0.41887903F;
                ArmLeft01.xRot = 0.091106184F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = -0.63739425F;
                ArmLeft02.xRot = 0.0F;
                ArmLeft02.yRot = 0.0F;
                ArmLeft02.zRot = 1.3658947F;
                ArmRight01.xRot = -0.85F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.0F;
                ArmRight02.xRot = 0.0F;
                ArmRight02.yRot = 0.0F;
                ArmRight02.zRot = -0.5009095F;
                legAddLeft = -1.2747885F;
                legAddRight = -2.1399481F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.08726646F;
                LegLeft02.xRot = 2.321986F;
                LegLeft02.yRot = 0.0F;
                LegLeft02.zRot = 0.0F;
                LegLeft02.z = legLeft02DefaultZ + (0.375F * OFFSET_SCALE);
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.08726646F;
                LegRight02.xRot = 1.5707964F;
                LegRight02.yRot = 0.0F;
                LegRight02.zRot = 0.0F;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            if (spStand) this.poseTranslateY -= 0.12F;
            BodyMain.xRot = -0.1047F;
            BodyMain.yRot = 0.0F;
            BodyMain.zRot = 0.0F;
            Butt.xRot = 0.35F;
            if (showWeapon) {
                ArmLeft02.xRot = -0.87266463F;
                ArmRight02.xRot = -0.87266463F;
            } else {
                ArmLeft02.xRot = 0.0F;
                ArmRight02.xRot = 0.0F;
            }
            ArmLeft01.xRot = -0.5235988F;
            ArmLeft01.yRot = -0.5235988F;
            ArmLeft01.zRot = -0.2617994F;
            ArmLeft02.yRot = 0.0F;
            ArmLeft02.zRot = 0.0F;
            ArmRight01.xRot = -0.5235988F;
            ArmRight01.yRot = 0.5235988F;
            ArmRight01.zRot = 0.2617994F;
            ArmRight02.yRot = 0.0F;
            ArmRight02.zRot = 0.0F;
            legAddLeft = ctx.angleAdd1 * 0.5F - 0.28F;
            legAddRight = ctx.angleAdd2 * 0.5F - 0.21F;
            EquipLBase.xRot = 0.0F;
            EquipLBase.yRot = -0.2617994F;
            EquipLBase.zRot = 0.34906584F;
            EquipRBase.xRot = 0.0F;
            EquipRBase.yRot = 0.2617994F;
            EquipRBase.zRot = -0.34906584F;
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

    private void applyHairAnimation(PoseContext ctx, float limbSwing, float ageInTicks, float limbSwingAmount) {
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);

        HairL01.xRot = angleX * 0.02F - 0.09F;
        HairL02.xRot = angleX1 * 0.03F - 0.09F;
        HairR01.xRot = angleX * 0.02F - 0.09F;
        HairR02.xRot = angleX1 * 0.03F - 0.09F;
        Hair01.xRot = angleX * 0.03F - 0.1F;
        Hair02.xRot = angleX1 * 0.04F - 0.1F;

        if (limbSwingAmount > 0.9F) {
            HairL01.xRot += 0.2F;
            HairL02.xRot += 0.2F;
            HairR01.xRot += 0.2F;
            HairR02.xRot += 0.2F;
            Hair01.xRot += 0.3F;
            Hair02.xRot += 0.3F;
        } else if (this.isSittingPose) {
            HairL01.xRot -= 0.1F;
            HairL02.xRot -= 0.1F;
            HairR01.xRot -= 0.1F;
            HairR02.xRot -= 0.1F;
            Hair01.xRot -= 0.15F;
            Hair02.xRot -= 0.15F;
        }

        float headX = Head.xRot * -0.5F;
        float headZ = Head.zRot * -0.5F;

        Hair01.xRot += headX;
        Hair02.xRot += headX;
        HairL01.xRot += headX;
        HairL02.xRot += headX;
        HairR01.xRot += headX;
        HairR02.xRot += headX;

        HairL01.zRot = headZ;
        HairL02.zRot = headZ;
        HairR01.zRot = headZ;
        HairR02.zRot = headZ;
        Hair01.zRot = headZ;
        Hair02.zRot = headZ;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
            GlowNeck.copyFrom(Neck);
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

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        if (GlowBodyMain == null) return;
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
