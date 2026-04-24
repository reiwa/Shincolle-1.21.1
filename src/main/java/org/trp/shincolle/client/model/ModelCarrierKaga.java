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

public class ModelCarrierKaga<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "carrier_kaga"), "main");

    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCarrierKaga");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelCarrierKaga");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelCarrierKaga");
    private static final float OFFSET_SCALE = 16.0F;

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart Butt;
    private final ModelPart Head;
    private final ModelPart Cloth01;
    private final ModelPart Cloth02;
    private final ModelPart Cloth05;
    private final ModelPart Cloth06;
    private final ModelPart EquipB01;
    private final ModelPart EquipC01;
    private final ModelPart EquipABase;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart ClothBody01;
    private final ModelPart ClothBody02;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight02;
    private final ModelPart EquipSR01;
    private final ModelPart LegLeft02;
    private final ModelPart EquipSL01;
    private final ModelPart Skirt02;
    private final ModelPart Cloth07;
    private final ModelPart Cloth08;
    private final ModelPart Cloth09;
    private final ModelPart EquipS01;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke;
    private final ModelPart HairU01;
    private final ModelPart Hair00;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Cloth03;
    private final ModelPart Cloth04;
    private final ModelPart EquipC02;
    private final ModelPart EquipABelt01;
    private final ModelPart EquipABody01;
    private final ModelPart EquipABody04;
    private final ModelPart EquipABody02;
    private final ModelPart EquipABody03;
    private final ModelPart EquipABody05;
    private final ModelPart EquipAArr01a;
    private final ModelPart EquipAArr02a;
    private final ModelPart EquipAArr03a;
    private final ModelPart EquipABody05b;
    private final ModelPart EquipABody05c;
    private final ModelPart EquipABelt02;
    private final ModelPart EquipAArr01b;
    private final ModelPart EquipAArr02b;
    private final ModelPart EquipAArr03b;
    private final ModelPart ClothHL01;
    private final ModelPart EquipD01;
    private final ModelPart EquipE01;
    private final ModelPart EquipE02;
    private final ModelPart EquipE04;
    private final ModelPart EquipE03;
    private final ModelPart EquipE05;
    private final ModelPart EquipE06;
    private final ModelPart ClothHL02;
    private final ModelPart ClothHL03;
    private final ModelPart EquipD02;
    private final ModelPart EquipD03;
    private final ModelPart EquipD04;
    private final ModelPart ClothHR01;
    private final ModelPart ClothHR02;
    private final ModelPart ClothHR03;
    private final ModelPart EquipGlove;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
    private final ModelPart Ear01;
    private final ModelPart Ear02;
    private final ModelPart Tail01;
    private final ModelPart Tail02;
    private final ModelPart Tail03;
    private final float clothHR02DefaultY;
    private final float clothHR03DefaultY;
    private final float armRight02DefaultX;
    private final float legLeft02DefaultX, legLeft02DefaultY, legLeft02DefaultZ;
    private final float legRight02DefaultX, legRight02DefaultY, legRight02DefaultZ;
    private final float equipE01DefaultX;
    private final float equipD02DefaultY;

    public ModelCarrierKaga(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.EquipABase = this.BodyMain.getChild("EquipABase");
        this.EquipABelt01 = this.EquipABase.getChild("EquipABelt01");
        this.EquipABody01 = this.EquipABelt01.getChild("EquipABody01");
        this.EquipABody03 = this.EquipABody01.getChild("EquipABody03");
        this.EquipABody02 = this.EquipABody01.getChild("EquipABody02");
        this.EquipABody04 = this.EquipABody01.getChild("EquipABody04");
        this.EquipABody05 = this.EquipABody04.getChild("EquipABody05");
        this.EquipABody05b = this.EquipABody05.getChild("EquipABody05b");
        this.EquipABody05c = this.EquipABody05b.getChild("EquipABody05c");
        this.EquipABelt02 = this.EquipABody05c.getChild("EquipABelt02");
        this.EquipAArr03a = this.EquipABody04.getChild("EquipAArr03a");
        this.EquipAArr03b = this.EquipAArr03a.getChild("EquipAArr03b");
        this.EquipAArr01a = this.EquipABody04.getChild("EquipAArr01a");
        this.EquipAArr01b = this.EquipAArr01a.getChild("EquipAArr01b");
        this.EquipAArr02a = this.EquipABody04.getChild("EquipAArr02a");
        this.EquipAArr02b = this.EquipAArr02a.getChild("EquipAArr02b");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.EquipD01 = this.ArmLeft01.getChild("EquipD01");
        this.EquipD02 = this.EquipD01.getChild("EquipD02");
        this.EquipD03 = this.EquipD02.getChild("EquipD03");
        this.EquipD04 = this.EquipD03.getChild("EquipD04");
        this.ClothHL01 = this.ArmLeft01.getChild("ClothHL01");
        this.ClothHL02 = this.ClothHL01.getChild("ClothHL02");
        this.ClothHL03 = this.ClothHL02.getChild("ClothHL03");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.EquipE01 = this.ArmLeft02.getChild("EquipE01");
        this.EquipE04 = this.EquipE01.getChild("EquipE04");
        this.EquipE05 = this.EquipE04.getChild("EquipE05");
        this.EquipE06 = this.EquipE05.getChild("EquipE06");
        this.EquipE02 = this.EquipE01.getChild("EquipE02");
        this.EquipE03 = this.EquipE02.getChild("EquipE03");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.ClothBody02 = this.BodyMain.getChild("ClothBody02");
        this.EquipB01 = this.BodyMain.getChild("EquipB01");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Cloth06 = this.BodyMain.getChild("Cloth06");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Head = this.BodyMain.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair00 = this.HairMain.getChild("Hair00");
        this.Hair01 = this.Hair00.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Ear01 = this.Head.getChild("Ear01");
        this.Ear02 = this.Head.getChild("Ear02");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.Cloth05 = this.BodyMain.getChild("Cloth05");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.EquipGlove = this.ArmRight02.getChild("EquipGlove");
        this.ClothHR01 = this.ArmRight01.getChild("ClothHR01");
        this.ClothHR02 = this.ClothHR01.getChild("ClothHR02");
        this.ClothHR03 = this.ClothHR02.getChild("ClothHR03");
        this.Cloth02 = this.BodyMain.getChild("Cloth02");
        this.Cloth04 = this.Cloth02.getChild("Cloth04");
        this.Cloth03 = this.Cloth02.getChild("Cloth03");
        this.EquipC01 = this.BodyMain.getChild("EquipC01");
        this.EquipC02 = this.EquipC01.getChild("EquipC02");
        this.ClothBody01 = this.BodyMain.getChild("ClothBody01");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.EquipSR01 = this.LegRight02.getChild("EquipSR01");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.EquipSL01 = this.LegLeft02.getChild("EquipSL01");
        this.Tail01 = this.Butt.getChild("Tail01");
        this.Tail02 = this.Tail01.getChild("Tail02");
        this.Tail03 = this.Tail02.getChild("Tail03");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Cloth09 = this.Skirt01.getChild("Cloth09");
        this.EquipS01 = this.Skirt01.getChild("EquipS01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.Cloth07 = this.Skirt01.getChild("Cloth07");
        this.Cloth08 = this.Skirt01.getChild("Cloth08");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.clothHR02DefaultY = this.ClothHR02.y;
        this.clothHR03DefaultY = this.ClothHR03.y;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.equipE01DefaultX = this.EquipE01.x;
        this.equipD02DefaultY = this.EquipD02.y;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition EquipABase = BodyMain.addOrReplaceChild("EquipABase", CubeListBuilder.create().texOffs(44, 35).addBox(-0.5F, -1F, -0.3F, 3F, 2F, 1F, new CubeDeformation(0F)), PartPose.offset(-1F, -8F, 3.6F));

        PartDefinition EquipABelt01 = EquipABase.addOrReplaceChild("EquipABelt01", CubeListBuilder.create().texOffs(0, 27).addBox(-13F, 0F, -0.5F, 13F, 0F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.3491F, 0.1396F, -0.5236F));

        PartDefinition EquipABody01 = EquipABelt01.addOrReplaceChild("EquipABody01", CubeListBuilder.create().texOffs(86, 55).addBox(-5F, -5.5F, -1F, 4F, 8F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12.5F, -1.5F, 0F, 0F, 0F, -0.7854F));

        PartDefinition EquipABody03 = EquipABody01.addOrReplaceChild("EquipABody03", CubeListBuilder.create().texOffs(128, 34).addBox(-3.5F, -0.5F, -0.5F, 7F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(-3.5F, -6.5F, 0F));

        PartDefinition EquipABody02 = EquipABody01.addOrReplaceChild("EquipABody02", CubeListBuilder.create().texOffs(128, 37).addBox(-0.5F, 0F, -0.5F, 1F, 6F, 1F, new CubeDeformation(0F)), PartPose.offset(-3.5F, -11.4F, 0F));

        PartDefinition EquipABody04 = EquipABody01.addOrReplaceChild("EquipABody04", CubeListBuilder.create().texOffs(128, 28).addBox(-3F, 0F, -3F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offset(-8F, 0F, 0F));

        PartDefinition EquipABody05 = EquipABody04.addOrReplaceChild("EquipABody05", CubeListBuilder.create().texOffs(128, 13).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, 0F));

        PartDefinition EquipABody05b = EquipABody05.addOrReplaceChild("EquipABody05b", CubeListBuilder.create().texOffs(128, 13).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 10F, 0F));

        PartDefinition EquipABody05c = EquipABody05b.addOrReplaceChild("EquipABody05c", CubeListBuilder.create().texOffs(128, 13).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 10F, 0F));

        PartDefinition EquipABelt02 = EquipABody05c.addOrReplaceChild("EquipABelt02", CubeListBuilder.create().texOffs(0, 27).addBox(0F, 0F, -0.5F, 18F, 0F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 2F, 0F, 0F, 0F, -0.7741F));

        PartDefinition EquipAArr03a = EquipABody04.addOrReplaceChild("EquipAArr03a", CubeListBuilder.create().texOffs(4, 47).addBox(0F, -4F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offset(-1.6F, 0F, 0.4F));

        PartDefinition EquipAArr03b = EquipAArr03a.addOrReplaceChild("EquipAArr03b", CubeListBuilder.create().texOffs(0, 48).addBox(-0.5F, -2.7F, 0.5F, 2F, 4F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -2F, 0F));

        PartDefinition EquipAArr01a = EquipABody04.addOrReplaceChild("EquipAArr01a", CubeListBuilder.create().texOffs(4, 47).addBox(0F, -4F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 0.7F, 0F, 0F, 0F, 0.0524F));

        PartDefinition EquipAArr01b = EquipAArr01a.addOrReplaceChild("EquipAArr01b", CubeListBuilder.create().texOffs(0, 48).addBox(-0.5F, -2.7F, 0.5F, 2F, 4F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -2F, 0F));

        PartDefinition EquipAArr02a = EquipABody04.addOrReplaceChild("EquipAArr02a", CubeListBuilder.create().texOffs(4, 47).addBox(0F, -4F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.5F, 0.3F, -1.1F, 0.0524F, -0.3187F, -0.0524F));

        PartDefinition EquipAArr02b = EquipAArr02a.addOrReplaceChild("EquipAArr02b", CubeListBuilder.create().texOffs(0, 48).addBox(-0.5F, -2.7F, 0.5F, 2F, 4F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -2F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -8.7F, -0.7F, 0.2094F, 0F, -0.2094F));

        PartDefinition EquipD01 = ArmLeft01.addOrReplaceChild("EquipD01", CubeListBuilder.create().texOffs(150, 13).addBox(-3F, 0F, -3.5F, 8F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0.3F, 2F, 0F));

        PartDefinition EquipD02 = EquipD01.addOrReplaceChild("EquipD02", CubeListBuilder.create().texOffs(58, 55).addBox(-5.5F, 0F, 0F, 11F, 9F, 1F, new CubeDeformation(0F)), PartPose.offset(5.6F, 3F, 0F));

        PartDefinition EquipD03 = EquipD02.addOrReplaceChild("EquipD03", CubeListBuilder.create().texOffs(153, 21).addBox(-5.5F, -26F, 0F, 11F, 26F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipD04 = EquipD03.addOrReplaceChild("EquipD04", CubeListBuilder.create().texOffs(128, 90).addBox(-4.5F, 0F, 0F, 9F, 11F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -37F, 0F));

        PartDefinition ClothHL01 = ArmLeft01.addOrReplaceChild("ClothHL01", CubeListBuilder.create().texOffs(43, 1).addBox(-2.5F, 0F, -3F, 6F, 5F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition ClothHL02 = ClothHL01.addOrReplaceChild("ClothHL02", CubeListBuilder.create().texOffs(42, 1).addBox(-3F, 0F, -3F, 7F, 5F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 4.5F, 0F));

        PartDefinition ClothHL03 = ClothHL02.addOrReplaceChild("ClothHL03", CubeListBuilder.create().texOffs(40, 0).addBox(-2.5F, 0F, -3.5F, 8F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(-1F, 4F, 0F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition EquipE01 = ArmLeft02.addOrReplaceChild("EquipE01", CubeListBuilder.create().texOffs(128, 37).addBox(-0.5F, -0.5F, -20F, 1F, 1F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.8F, 10.5F, -3F, 0.0524F, 0F, 0F));

        PartDefinition EquipE04 = EquipE01.addOrReplaceChild("EquipE04", CubeListBuilder.create().texOffs(133, 58).addBox(-0.5F, -0.5F, 0F, 1F, 1F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -0.2F, -0.1396F, 0F, 0F));

        PartDefinition EquipE05 = EquipE04.addOrReplaceChild("EquipE05", CubeListBuilder.create().texOffs(131, 77).addBox(-0.5F, -0.5F, 0F, 1F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 14.7F, 0.4538F, 0F, 0F));

        PartDefinition EquipE06 = EquipE05.addOrReplaceChild("EquipE06", CubeListBuilder.create().texOffs(135, 81).addBox(-0.5F, -0.5F, 0F, 1F, 1F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 11.7F, -0.2793F, 0F, 0F));

        PartDefinition EquipE02 = EquipE01.addOrReplaceChild("EquipE02", CubeListBuilder.create().texOffs(128, 74).addBox(-0.5F, -0.5F, -15F, 1F, 1F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -19.7F, -0.4887F, 0F, 0F));

        PartDefinition EquipE03 = EquipE02.addOrReplaceChild("EquipE03", CubeListBuilder.create().texOffs(134, 80).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -14.7F, 0.3142F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(98, 31).addBox(-4F, 0F, -4F, 8F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -12.1F, -0.6F, 0.1745F, 0F, 0F));

        PartDefinition ClothBody02 = BodyMain.addOrReplaceChild("ClothBody02", CubeListBuilder.create().texOffs(0, 113).addBox(-1F, 0F, 0F, 2F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -3.8F, -2.3F, 0.2618F, 0F, 0.2618F));

        PartDefinition EquipB01 = BodyMain.addOrReplaceChild("EquipB01", CubeListBuilder.create().texOffs(62, 22).addBox(-7F, -6F, -6F, 14F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.2F, 0.7F, 0.6981F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.8F, -8.5F, -3.5F, -0.6981F, -0.1047F, -0.0873F));

        PartDefinition Cloth06 = BodyMain.addOrReplaceChild("Cloth06", CubeListBuilder.create().texOffs(104, 21).addBox(0F, 0F, 0F, 12F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -11.6F, 3.2F, 0.0698F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.8F, -8.5F, -3.5F, -0.6981F, 0.1047F, 0.0873F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.8F, -1F, 0.1047F, 0F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(48, 34).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair00 = HairMain.addOrReplaceChild("Hair00", CubeListBuilder.create().texOffs(64, 40).addBox(0F, -2F, -2F, 4F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, 4F, 5.5F, 0.0524F, -0.1745F, -0.0873F));

        PartDefinition Hair01 = Hair00.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(60, 38).addBox(0F, -3F, -3F, 6F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, -1F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(63, 39).mirror().addBox(0F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.6F, 4.2F, 0F, -0.0524F, 0F, 0.1047F));

        PartDefinition Ear01 = Head.addOrReplaceChild("Ear01", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-1.5F, 0F, -6F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.8F, -14.5F, 5.7F, -0.7854F, 0.2618F, -0.1396F));

        PartDefinition Ear02 = Head.addOrReplaceChild("Ear02", CubeListBuilder.create().texOffs(20, 0).addBox(-1.5F, 0F, -6F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.8F, -14.5F, 5.7F, -0.7854F, -0.2618F, 0.1396F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.2F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(82, 0).addBox(-8.5F, 0F, 0F, 17F, 12F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -6.5F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -4F, -11.5F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -9F, -5.5F, 0.0873F, 0.6981F, 0F));

        PartDefinition Cloth05 = BodyMain.addOrReplaceChild("Cloth05", CubeListBuilder.create().texOffs(44, 19).addBox(-1F, -3.5F, -4.6F, 1F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.8F, -7.9F, 0F, 0.0873F, 0.1396F, 0.1396F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 8).addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -8.7F, -0.7F, 0.0524F, 0F, 0.3142F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 8).addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition EquipGlove = ArmRight02.addOrReplaceChild("EquipGlove", CubeListBuilder.create().texOffs(128, 103).addBox(-3F, 0F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(2.5F, 6.3F, -2.5F));

        PartDefinition ClothHR01 = ArmRight01.addOrReplaceChild("ClothHR01", CubeListBuilder.create().texOffs(43, 1).addBox(-3.5F, 0F, -3F, 6F, 5F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition ClothHR02 = ClothHR01.addOrReplaceChild("ClothHR02", CubeListBuilder.create().texOffs(42, 1).addBox(-4F, 0F, -3F, 7F, 5F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 4.5F, 0F));

        PartDefinition ClothHR03 = ClothHR02.addOrReplaceChild("ClothHR03", CubeListBuilder.create().texOffs(40, 0).addBox(-3.5F, 0F, -3.5F, 8F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(-1F, 4F, 0F));

        PartDefinition Cloth02 = BodyMain.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(44, 19).mirror().addBox(0F, -3.5F, -4.6F, 1F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.8F, -7.9F, 0F, 0.0873F, -0.1396F, -0.1396F));

        PartDefinition Cloth04 = Cloth02.addOrReplaceChild("Cloth04", CubeListBuilder.create().texOffs(0, 64).addBox(0F, 0F, -1F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 0.6F, -4.5F, -0.1396F, -0.3491F, -0.2094F));

        PartDefinition Cloth03 = Cloth02.addOrReplaceChild("Cloth03", CubeListBuilder.create().texOffs(0, 64).addBox(0F, -4F, -1F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -4.6F, 0.1396F, -0.3142F, 0.1396F));

        PartDefinition EquipC01 = BodyMain.addOrReplaceChild("EquipC01", CubeListBuilder.create().texOffs(128, 0).addBox(-9F, 0F, -4F, 18F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.2F, 6.4F, -0.8F, 0F, 0.0873F, -0.182F));

        PartDefinition EquipC02 = EquipC01.addOrReplaceChild("EquipC02", CubeListBuilder.create().texOffs(64, 7).addBox(-2.5F, 0F, -3F, 3F, 9F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, -0.5F, 1.5F, 0.1745F, 0F, 0.3491F));

        PartDefinition ClothBody01 = BodyMain.addOrReplaceChild("ClothBody01", CubeListBuilder.create().texOffs(0, 113).addBox(-1F, 0F, 0F, 2F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -3.8F, -2.3F, 0.2618F, 0F, -0.2618F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(52, 65).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3142F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 63).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.1396F, 0F, -0.1396F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 83).addBox(-3F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition EquipSR01 = LegRight02.addOrReplaceChild("EquipSR01", CubeListBuilder.create().texOffs(24, 90).addBox(-3F, 0F, -3.5F, 6F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 15F, 3F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.1396F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 83).mirror().addBox(-3F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition EquipSL01 = LegLeft02.addOrReplaceChild("EquipSL01", CubeListBuilder.create().texOffs(24, 90).mirror().addBox(-3F, 0F, -3.5F, 6F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 15F, 3F));

        PartDefinition Tail01 = Butt.addOrReplaceChild("Tail01", CubeListBuilder.create().texOffs(63, 36).addBox(-1F, -1F, 0F, 2F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.5F, 1F, -0.6981F, 0F, 0F));

        PartDefinition Tail02 = Tail01.addOrReplaceChild("Tail02", CubeListBuilder.create().texOffs(63, 36).addBox(-1F, -1F, 0F, 2F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 7.5F, 0.6981F, 0F, 0F));

        PartDefinition Tail03 = Tail02.addOrReplaceChild("Tail03", CubeListBuilder.create().texOffs(63, 36).addBox(-1F, -1F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 7.5F, 0.6981F, 0F, 0F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(0, 28).addBox(-8.5F, 0F, -6.3F, 17F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.3F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Cloth09 = Skirt01.addOrReplaceChild("Cloth09", CubeListBuilder.create().texOffs(34, 80).addBox(-3F, 0F, 0F, 6F, 10F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.2F, -6.8F, -0.1396F, 0F, 0F));

        PartDefinition EquipS01 = Skirt01.addOrReplaceChild("EquipS01", CubeListBuilder.create().texOffs(58, 55).addBox(-5.5F, 0F, 0F, 11F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -7.3F, -0.2793F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(0, 44).addBox(-9F, 0F, -6F, 18F, 8F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, -0.6F, -0.0873F, 0F, 0F));

        PartDefinition Cloth07 = Skirt01.addOrReplaceChild("Cloth07", CubeListBuilder.create().texOffs(24, 80).addBox(0F, 0F, 0F, 3F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 0.5F, -7F, -0.1745F, -0.1396F, -0.2094F));

        PartDefinition Cloth08 = Skirt01.addOrReplaceChild("Cloth08", CubeListBuilder.create().texOffs(24, 80).addBox(-3F, 0F, 0F, 3F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -7F, -0.1571F, -0.1047F, 0.1745F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -11.8F, -1F, 0.1047F, 0F, 0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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

    private void resetOffsets() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;

        this.ClothHR02.y = this.clothHR02DefaultY;
        this.ClothHR03.y = this.clothHR03DefaultY;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.EquipE01.x = this.equipE01DefaultX;
        this.EquipD02.y = this.equipD02DefaultY;
    }

        private void applyEquipVisibility(EntityShipBase entity) {
        if (entity == null) return;
        boolean show_Ear01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EAR01);
        this.Ear01.visible = show_Ear01;
        boolean show_Ear02 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EAR02);
        this.Ear02.visible = show_Ear02;
        boolean show_EquipABase = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPABASE);
        this.EquipABase.visible = show_EquipABase;
        boolean show_EquipB01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPB01);
        this.EquipB01.visible = show_EquipB01;
        boolean show_EquipC01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPC01);
        this.EquipC01.visible = show_EquipC01;
        boolean show_EquipD01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPD01);
        this.EquipD01.visible = show_EquipD01;
        boolean show_EquipE01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPE01);
        this.EquipE01.visible = show_EquipE01;
        boolean show_EquipGlove = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPGLOVE);
        this.EquipGlove.visible = show_EquipGlove;
        boolean show_EquipS01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPS01);
        this.EquipS01.visible = show_EquipS01;
        boolean show_EquipSL01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPSL01);
        this.EquipSL01.visible = show_EquipSL01;
        boolean show_EquipSR01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_EQUIPSR01);
        this.EquipSR01.visible = show_EquipSR01;
        boolean show_Tail01 = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_TAIL01);
        this.Tail01.visible = show_Tail01;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.BoobL.xRot = -0.8F;
        this.BoobR.xRot = -0.8F;
        this.Ahoke.yRot = 0.45F;

        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;

        this.Hair01.xRot = 0.0F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = 0.0F;
        this.Hair02.zRot = 0.0F;

        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight01.zRot += 0.15F;

        this.LegLeft01.xRot = -0.9F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = -0.14F;
        this.LegRight01.xRot = -0.9F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.14F;

        this.EquipE01.xRot = 0.05F;
        this.EquipE01.x = this.equipE01DefaultX;
        this.EquipE02.xRot = -0.4887F;
        this.EquipE05.xRot = 0.4538F;

        this.EquipD02.yRot = 1.6755F;
        this.EquipD02.zRot = 3.1416F;

        this.Head.xRot = 0.65F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;

        this.Butt.xRot = -0.2F;
        this.Skirt01.xRot = -0.26F;
        this.Skirt02.xRot = -0.45F;

        this.ArmLeft01.xRot = 0.4F;
        this.ArmLeft01.zRot = -0.2618F;
        this.ArmRight01.xRot = 0.4F;
        this.ArmRight01.zRot = 0.2618F;

        this.LegLeft02.xRot = 1.2217F;
        this.LegLeft02.yRot = 1.2217F;
        this.LegLeft02.zRot = -1.0472F;
        this.LegLeft02.x = this.legLeft02DefaultX + (0.18F * OFFSET_SCALE);
        this.LegLeft02.y = this.legLeft02DefaultY + (-0.03F * OFFSET_SCALE);
        this.LegLeft02.z = this.legLeft02DefaultZ + (0.1635F * OFFSET_SCALE);

        this.LegRight02.xRot = 1.2217F;
        this.LegRight02.yRot = -1.2217F;
        this.LegRight02.zRot = 1.0472F;
        this.LegRight02.x = this.legRight02DefaultX + (-0.18F * OFFSET_SCALE);
        this.LegRight02.y = this.legRight02DefaultY + (-0.03F * OFFSET_SCALE);
        this.LegRight02.z = this.legRight02DefaultZ + (0.1635F * OFFSET_SCALE);

        this.Tail01.xRot = 0.1F;
        this.Tail02.xRot = 0.15F;
        this.Tail03.xRot = 0.15F;

        this.EquipE01.yRot = 1.7F;
        this.EquipE01.zRot = 0.15F;
        this.EquipD02.xRot = 0.2F;
        this.EquipD02.y = this.equipD02DefaultY + (-0.5F * OFFSET_SCALE);
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + limbSwing * 0.25F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.6F + limbSwing * 0.5F);
        float angleX3 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.9F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) + 0.1047F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = 0.0F;

        this.BoobL.xRot = angleX * 0.06F - 0.8F;
        this.BoobR.xRot = angleX * 0.06F - 0.8F;
        this.Ahoke.yRot = angleX * 0.25F + 0.45F;

        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;

        this.Butt.xRot = 0.3142F;
        this.Skirt01.xRot = -0.14F;
        this.Skirt02.xRot = -0.0873F;

        this.ClothHR02.y = this.clothHR02DefaultY;
        this.ClothHR03.y = this.clothHR03DefaultY;

        this.Hair01.xRot = 0.0F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = 0.0F;
        this.Hair02.zRot = 0.0F;

        this.ArmLeft01.xRot = angleAdd2 * 0.25F + 0.21F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = angleX * 0.03F - 0.21F;
        this.ArmLeft02.xRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.25F + 0.05F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -angleX * 0.03F + 0.21F;
        this.ArmRight02.zRot = 0.0F;
        this.ArmRight02.x = this.armRight02DefaultX;

        int state = entity != null ? entity.getStateEmotion(0) : 0;
        boolean hasBag = (state & (1 << 3)) != 0;
        boolean hasTail = entity.getEquipFlag(org.trp.shincolle.entity.EntityCarrierKaga.EQUIP_TAIL01);
        if (hasBag) {
            this.ArmRight01.zRot += 0.15F;
        }
        if (hasTail) {
            this.Tail01.xRot = angleX1 * 0.5F - 0.7F;
            this.Tail02.xRot = -angleX2 * 0.5F;
            this.Tail03.xRot = -angleX3 * 0.5F;
        }

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.1396F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;

        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.1396F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;

        this.EquipE01.xRot = 0.05F;
        this.EquipE01.yRot = 0.0F;
        this.EquipE01.zRot = 0.0F;
        this.EquipE01.x = this.equipE01DefaultX;

        this.EquipE02.xRot = -0.4887F;
        this.EquipE05.xRot = 0.4538F;

        this.EquipD01.xRot = 0.0F;
        this.EquipD02.xRot = -0.05F;
        this.EquipD02.yRot = 1.6755F;
        this.EquipD02.zRot = 3.1416F;
        this.EquipD02.y = this.equipD02DefaultY;

        float modf2 = ageInTicks % 128.0F;
        if (modf2 < 6.0F) {
            if (modf2 >= 3.0F) {
                modf2 -= 3.0F;
            }
            float anglef2 = net.minecraft.util.Mth.sin(modf2 * 1.0472F) * 0.25F;
            this.Ear01.zRot = -anglef2 - 0.14F;
            this.Ear02.zRot = anglef2 + 0.14F;
        } else {
            this.Ear01.zRot = -0.14F;
            this.Ear02.zRot = 0.14F;
        }

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.xRot += headX;
        this.Hair02.xRot += headX * 0.5F;
        this.Hair01.zRot += headZ;
        this.Hair02.zRot += headZ * 0.5F;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;
        float addk1 = angleAdd1 * 0.5F - 0.2793F;
        float addk2 = angleAdd2 * 0.5F - 0.1396F;

        boolean isSprinting = entity != null && entity.isSprinting();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isSitting = entity != null && (entity.getIsSitting() || entity.isPassenger());

        if (isSprinting || limbSwingAmount > 0.1F) {
            this.Hair01.xRot += angleAdd1 * 0.1F + limbSwingAmount * 0.75F;
            this.ArmLeft01.zRot += limbSwingAmount * -0.2F;
            this.ArmRight01.zRot += limbSwingAmount * 0.2F;
        }

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 1.0472F;
            this.BodyMain.xRot = 1.0472F;
            this.Butt.xRot = -0.8378F;
            this.ArmLeft01.xRot = -0.7F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.7F;
            this.ArmRight01.zRot = -0.2618F;
            this.EquipD02.xRot = 0.15F;
            this.EquipE01.yRot = 1.3F;
            this.Tail01.xRot += 1.3F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (entity != null && entity.getStateEmotion(1) == 4) {
                this.poseTranslateY = 0.43F * 3;
                int nodTick = (int) ageInTicks % 60;
                this.Head.xRot = 0.4F;
                if (nodTick < 30) {
                    if (nodTick < 6) {
                        this.Head.xRot = nodTick * 0.02F + 0.4F;
                    } else if (nodTick < 11) {
                        this.Head.xRot = (nodTick - 5) * 0.03F + 0.5F;
                    } else if (nodTick < 14) {
                        this.Head.xRot = (nodTick - 10) * -0.09F + 0.65F;
                    }
                }
                this.Head.yRot = 0.0F;
                this.Head.zRot = 0.0F;
                this.Butt.xRot = -0.2F;
                this.Skirt01.xRot = -0.26F;
                this.Skirt02.xRot = -0.45F;
                this.ArmLeft01.xRot = 0.4F;
                this.ArmLeft01.zRot = -0.2618F;
                this.ArmRight01.xRot = 0.4F;
                this.ArmRight01.zRot = 0.2618F;
                addk1 = -0.9F;
                addk2 = -0.9F;
                this.LegLeft01.zRot = -0.14F;
                this.LegLeft02.xRot = 1.2217F;
                this.LegLeft02.yRot = 1.2217F;
                this.LegLeft02.zRot = -1.0472F;
                this.LegLeft02.x = this.legLeft02DefaultX + (0.18F * OFFSET_SCALE);
                this.LegLeft02.y = this.legLeft02DefaultY + (-0.03F * OFFSET_SCALE);
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.1635F * OFFSET_SCALE);
                this.LegRight01.zRot = 0.14F;
                this.LegRight02.xRot = 1.2217F;
                this.LegRight02.yRot = -1.2217F;
                this.LegRight02.zRot = 1.0472F;
                this.LegRight02.x = this.legRight02DefaultX + (-0.18F * OFFSET_SCALE);
                this.LegRight02.y = this.legRight02DefaultY + (-0.03F * OFFSET_SCALE);
                this.LegRight02.z = this.legRight02DefaultZ + (0.1635F * OFFSET_SCALE);
                this.Tail01.xRot += 1.7F;
                this.Tail02.xRot += 0.15F;
                this.Tail03.xRot += 0.15F;
                this.Tail01.xRot *= 0.2F;
                this.Tail02.xRot *= 0.2F;
                this.Tail03.xRot *= 0.2F;
                this.EquipE01.yRot = 1.7F;
                this.EquipE01.zRot = 0.15F;
                this.EquipD02.xRot = 0.2F;
                this.EquipD02.y = this.equipD02DefaultY + (-0.5F * OFFSET_SCALE);
            } else {
                this.poseTranslateY = SITTING_TRANSLATE_Y;
                this.Head.xRot += 0.1047F;
                this.BodyMain.xRot = -0.1396F;
                this.Butt.xRot = 0.1396F;
                this.ArmLeft01.xRot = -0.4F;
                this.ArmLeft01.zRot = 0.2618F;
                this.ArmRight01.xRot = -0.4F;
                this.ArmRight01.zRot = -0.2618F;
                addk1 = -1.0472F;
                addk2 = -1.0472F;
                this.LegLeft01.yRot = 0.0524F;
                this.LegLeft01.zRot = 0.0F;
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.38F * OFFSET_SCALE);
                this.LegLeft02.xRot = 2.5831F;
                this.LegLeft02.zRot = 0.0175F;
                this.LegRight01.yRot = -0.0524F;
                this.LegRight01.zRot = 0.0F;
                this.LegRight02.z = this.legRight02DefaultZ + (0.38F * OFFSET_SCALE);
                this.LegRight02.xRot = 2.5831F;
                this.LegRight02.zRot = -0.0175F;
                this.Tail01.xRot += 1.0F;
                this.Tail02.xRot += 0.15F;
                this.Tail03.xRot += 0.15F;
                this.EquipE01.yRot = 1.7F;
                this.EquipE01.zRot = -0.2F;
                this.EquipD02.xRot = 0.2F;
                this.EquipD02.y = this.equipD02DefaultY + (-0.5F * OFFSET_SCALE);
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            if (entity.getAttackTick() >= 49) {
                entity.setAttackTick2(0);
            }
            int tick = entity.getAttackTick2();
            float parTick = ageInTicks - (int) ageInTicks + tick;
            this.Head.xRot = 0.0F;
            this.Head.yRot = -1.31F;
            this.BodyMain.xRot = -0.05F;
            this.BodyMain.yRot = 1.4F;
            this.ClothHR02.y = this.clothHR02DefaultY + (-0.17F * OFFSET_SCALE);
            this.ClothHR03.y = this.clothHR03DefaultY + (-0.2F * OFFSET_SCALE);
            this.ArmLeft01.xRot = -1.5708F;
            this.ArmLeft01.yRot = -1.35F;
            this.ArmLeft01.zRot = 0.0F;
            this.ArmRight01.xRot = 0.0F;
            this.ArmRight01.yRot = 2.1817F;
            this.ArmRight01.zRot = 1.5708F;
            this.ArmRight02.zRot = Math.max(-1.57F, -2.44F + 0.15F * parTick);
            this.ArmRight02.x = this.armRight02DefaultX + (0.31F * OFFSET_SCALE);
            addk1 = -0.35F;
            addk2 = -0.23F;
            this.LegLeft01.zRot = -0.14F;
            this.LegRight01.zRot = 0.14F;
            this.EquipD01.xRot = -1.3F;
            this.EquipD02.xRot = -1.5708F;
            this.EquipD02.yRot = -1.15F;
            this.EquipD02.zRot = 1.7453F;
            this.EquipE01.xRot = 0.2618F;
            this.EquipE01.zRot = -0.23F;
            this.EquipE01.x = this.equipE01DefaultX + (-0.15F * OFFSET_SCALE);
            this.EquipE02.xRot = Math.min(-0.49F, -0.7F + 0.1F * parTick);
            this.EquipE05.xRot = Math.max(0.45F, 0.7F - 0.1F * parTick);
            if (tick > 5 && tick < 12) {
                float wave = net.minecraft.util.Mth.sin(parTick * 0.2244F);
                this.EquipE01.xRot -= 0.36F * wave;
                this.EquipE01.zRot -= 5.0F * wave;
            }
            if (tick >= 12) {
                this.EquipE01.xRot = -0.1F;
                this.EquipE01.zRot = -3.3F;
            }
            entity.setAttackTick2(++tick);
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

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.copyFrom(this.BodyMain);
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
        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
