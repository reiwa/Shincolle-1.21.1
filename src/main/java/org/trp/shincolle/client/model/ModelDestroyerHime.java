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

public class ModelDestroyerHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelDestroyerHime");
    private static final float SIT_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelDestroyerHime");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft01;
    private final ModelPart Cloth01;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Hair02;
    private final ModelPart Hat01;
    private final ModelPart HairU01;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart Hair01;
    private final ModelPart Hair03;
    private final ModelPart Hair04;
    private final ModelPart Hair05;
    private final ModelPart Hair06;
    private final ModelPart Hat02a;
    private final ModelPart Hat03;
    private final ModelPart Hat04a;
    private final ModelPart Hat05a;
    private final ModelPart Hat06a;
    private final ModelPart Hat06b;
    private final ModelPart Hat02b;
    private final ModelPart Hat04b;
    private final ModelPart Hat04c;
    private final ModelPart Hat05b;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight01;
    private final ModelPart EquipLegL;
    private final ModelPart EquipLegR;
    private final ModelPart EquipBaseL;
    private final ModelPart EquipBaseR;
    private final ModelPart BeltBase;
    private final ModelPart LegLeft02;
    private final ModelPart LegRight02;
    private final ModelPart EquipLHead;
    private final ModelPart EquipLJaw;
    private final ModelPart EquipLB;
    private final ModelPart EquipLT01;
    private final ModelPart EquipLTU;
    private final ModelPart EquipHeadC01;
    private final ModelPart EquipHeadC02;
    private final ModelPart EquipLTD;
    private final ModelPart EquipLT02a;
    private final ModelPart EquipLT02b;
    private final ModelPart EquipLT02c;
    private final ModelPart EquipLT02d;
    private final ModelPart EquipRHead;
    private final ModelPart EquipLJaw_1;
    private final ModelPart EquipLB_1;
    private final ModelPart EquipLT01_1;
    private final ModelPart EquipLTU_1;
    private final ModelPart EquipHeadC01_1;
    private final ModelPart EquipHeadC02_1;
    private final ModelPart EquipLTD_1;
    private final ModelPart EquipLT02a_1;
    private final ModelPart EquipLT02b_1;
    private final ModelPart EquipLT02c_1;
    private final ModelPart EquipLT02d_1;
    private final ModelPart Belt01;
    private final ModelPart Belt02;
    private final ModelPart Belt03;
    private final ModelPart Belt04;
    private final ModelPart Belt05;
    private final ModelPart Belt06;
    private final ModelPart Belt07;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight02a;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft02a;
    private final ModelPart Cannon01;
    private final ModelPart Cannon02;
    private final ModelPart Cannon03;
    private final ModelPart Cannon04;
    private final ModelPart Cannon05;
    private final ModelPart Cloth02;
    private final ModelPart Skirt01;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float buttDefaultY;
    private final float skirt01DefaultY;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultZ;
    private final float legLeft01DefaultY;
    private final float legLeft02DefaultX;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight01DefaultY;
    private final float legRight02DefaultX;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;
    private final float equipBaseLDefaultY;
    private final float equipBaseRDefaultY;

    public ModelDestroyerHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight02a = this.ArmRight02.getChild("ArmRight02a");
        this.Butt = this.BodyMain.getChild("Butt");
        this.EquipLegL = this.Butt.getChild("EquipLegL");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.EquipLegR = this.Butt.getChild("EquipLegR");
        this.BeltBase = this.Butt.getChild("BeltBase");
        this.Belt05 = this.BeltBase.getChild("Belt05");
        this.Belt01 = this.BeltBase.getChild("Belt01");
        this.Belt02 = this.BeltBase.getChild("Belt02");
        this.Belt06 = this.BeltBase.getChild("Belt06");
        this.Belt03 = this.BeltBase.getChild("Belt03");
        this.Belt07 = this.BeltBase.getChild("Belt07");
        this.Belt04 = this.BeltBase.getChild("Belt04");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.EquipBaseL = this.Butt.getChild("EquipBaseL");
        this.EquipLHead = this.EquipBaseL.getChild("EquipLHead");
        this.EquipLTU = this.EquipLHead.getChild("EquipLTU");
        this.EquipHeadC01 = this.EquipLHead.getChild("EquipHeadC01");
        this.EquipHeadC02 = this.EquipHeadC01.getChild("EquipHeadC02");
        this.EquipLT01 = this.EquipBaseL.getChild("EquipLT01");
        this.EquipLT02b = this.EquipLT01.getChild("EquipLT02b");
        this.EquipLT02d = this.EquipLT01.getChild("EquipLT02d");
        this.EquipLT02c = this.EquipLT01.getChild("EquipLT02c");
        this.EquipLT02a = this.EquipLT01.getChild("EquipLT02a");
        this.EquipLJaw = this.EquipBaseL.getChild("EquipLJaw");
        this.EquipLTD = this.EquipLJaw.getChild("EquipLTD");
        this.EquipLB = this.EquipBaseL.getChild("EquipLB");
        this.EquipBaseR = this.Butt.getChild("EquipBaseR");
        this.EquipRHead = this.EquipBaseR.getChild("EquipRHead");
        this.EquipLTU_1 = this.EquipRHead.getChild("EquipLTU_1");
        this.EquipHeadC01_1 = this.EquipRHead.getChild("EquipHeadC01_1");
        this.EquipHeadC02_1 = this.EquipHeadC01_1.getChild("EquipHeadC02_1");
        this.EquipLT01_1 = this.EquipBaseR.getChild("EquipLT01_1");
        this.EquipLT02a_1 = this.EquipLT01_1.getChild("EquipLT02a_1");
        this.EquipLT02b_1 = this.EquipLT01_1.getChild("EquipLT02b_1");
        this.EquipLT02d_1 = this.EquipLT01_1.getChild("EquipLT02d_1");
        this.EquipLT02c_1 = this.EquipLT01_1.getChild("EquipLT02c_1");
        this.EquipLJaw_1 = this.EquipBaseR.getChild("EquipLJaw_1");
        this.EquipLTD_1 = this.EquipLJaw_1.getChild("EquipLTD_1");
        this.EquipLB_1 = this.EquipBaseR.getChild("EquipLB_1");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02 = this.Cloth01.getChild("Cloth02");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Hair02 = this.Head.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair04 = this.Hair03.getChild("Hair04");
        this.Hair05 = this.Hair04.getChild("Hair05");
        this.Hair06 = this.Hair05.getChild("Hair06");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hat01 = this.Head.getChild("Hat01");
        this.Hat04a = this.Hat01.getChild("Hat04a");
        this.Hat04b = this.Hat04a.getChild("Hat04b");
        this.Hat04c = this.Hat04b.getChild("Hat04c");
        this.Hat05a = this.Hat01.getChild("Hat05a");
        this.Hat05b = this.Hat05a.getChild("Hat05b");
        this.Hat03 = this.Hat01.getChild("Hat03");
        this.Hat02b = this.Hat01.getChild("Hat02b");
        this.Hat06b = this.Hat01.getChild("Hat06b");
        this.Hat02a = this.Hat01.getChild("Hat02a");
        this.Hat06a = this.Hat01.getChild("Hat06a");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft02a = this.ArmLeft02.getChild("ArmLeft02a");
        this.Cannon01 = this.ArmLeft02.getChild("Cannon01");
        this.Cannon03 = this.Cannon01.getChild("Cannon03");
        this.Cannon04 = this.Cannon01.getChild("Cannon04");
        this.Cannon02 = this.Cannon01.getChild("Cannon02");
        this.Cannon05 = this.Cannon01.getChild("Cannon05");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.buttDefaultY = this.Butt.y;
        this.skirt01DefaultY = this.Skirt01.y;
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultZ = this.ArmRight02.z;
        this.legLeft01DefaultY = this.LegLeft01.y;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight01DefaultY = this.LegRight01.y;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.equipBaseLDefaultY = this.EquipBaseL.y;
        this.equipBaseRDefaultY = this.EquipBaseR.y;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 86).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0.2618F, 0F, 0.7854F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 69).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition ArmRight02a = ArmRight02.addOrReplaceChild("ArmRight02a", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 6.5F, -2.4F, 0.0524F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 47).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition EquipLegL = Butt.addOrReplaceChild("EquipLegL", CubeListBuilder.create().texOffs(19, 3).addBox(-3.5F, 0F, -3.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.0873F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(74, 10).addBox(-8.5F, 0F, -6F, 17F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, -0.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipLegR = Butt.addOrReplaceChild("EquipLegR", CubeListBuilder.create().texOffs(9, 0).addBox(-3.5F, 0F, -3.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.2094F, 0F, -0.0873F));

        PartDefinition BeltBase = Butt.addOrReplaceChild("BeltBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.0873F, 0F, 0F));

        PartDefinition Belt05 = BeltBase.addOrReplaceChild("Belt05", CubeListBuilder.create().texOffs(0, 2).addBox(0F, 0F, -1F, 9F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, 4F, 0F, 0.1047F, 0F));

        PartDefinition Belt01 = BeltBase.addOrReplaceChild("Belt01", CubeListBuilder.create().texOffs(0, 8).addBox(-9F, 0F, 0F, 9F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -8F, 0F, 0.1047F, 0F));

        PartDefinition Belt02 = BeltBase.addOrReplaceChild("Belt02", CubeListBuilder.create().texOffs(0, 13).addBox(0F, 0F, 0F, 9F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -8F, 0F, -0.1047F, 0F));

        PartDefinition Belt06 = BeltBase.addOrReplaceChild("Belt06", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, 0F, -1F, 9F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, 4F, 0F, -0.1047F, 0F));

        PartDefinition Belt03 = BeltBase.addOrReplaceChild("Belt03", CubeListBuilder.create().texOffs(0, 11).addBox(0F, 0F, 0F, 9F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8.9F, -1F, 2.8F, 0F, 1.5708F, 0F));

        PartDefinition Belt07 = BeltBase.addOrReplaceChild("Belt07", CubeListBuilder.create().texOffs(0, 34).addBox(0F, 0F, 0F, 1F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.8F, -2.1F, -4F, -0.5236F, 0F, 0F));

        PartDefinition Belt04 = BeltBase.addOrReplaceChild("Belt04", CubeListBuilder.create().texOffs(0, 6).addBox(-9F, 0F, 0F, 9F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.9F, -1F, 2.8F, 0F, -1.5708F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 63).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.2094F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition EquipBaseL = Butt.addOrReplaceChild("EquipBaseL", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -3F, -3F, 16F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 10F, -3F, 0.0524F, -0.1396F, 0.1396F));

        PartDefinition EquipLHead = EquipBaseL.addOrReplaceChild("EquipLHead", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -6F, -10F, 11F, 6F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, -2F, 0F, -0.1396F, 0F, 0F));

        PartDefinition EquipLTU = EquipLHead.addOrReplaceChild("EquipLTU", CubeListBuilder.create().texOffs(47, 29).addBox(-4.5F, 0F, -9F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.1F, -0.7F, 0.0873F, 0F, 0F));

        PartDefinition EquipHeadC01 = EquipLHead.addOrReplaceChild("EquipHeadC01", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, 0F, 5F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8.7F, -7F, -0.1745F, 0F, 0F));

        PartDefinition EquipHeadC02 = EquipHeadC01.addOrReplaceChild("EquipHeadC02", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.6F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 1.5F, 0.5F));

        PartDefinition EquipLT01 = EquipBaseL.addOrReplaceChild("EquipLT01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -5F, -6F, 4F, 11F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(15F, 0F, 0F, 0F, -0.1396F, 0F));

        PartDefinition EquipLT02b = EquipLT01.addOrReplaceChild("EquipLT02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(2F, -0.3F, -5.8F));

        PartDefinition EquipLT02d = EquipLT01.addOrReplaceChild("EquipLT02d", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(2F, 4.3F, -5.8F));

        PartDefinition EquipLT02c = EquipLT01.addOrReplaceChild("EquipLT02c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(2F, 2F, -5.8F));

        PartDefinition EquipLT02a = EquipLT01.addOrReplaceChild("EquipLT02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(2F, -2.6F, -5.8F));

        PartDefinition EquipLJaw = EquipBaseL.addOrReplaceChild("EquipLJaw", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 4F, -9F, 8F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 0F, -1.2F, 0.1745F, 0F, 0F));

        PartDefinition EquipLTD = EquipLJaw.addOrReplaceChild("EquipLTD", CubeListBuilder.create().texOffs(47, 29).mirror().addBox(-4.5F, 0F, -9F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 5F, 0.2F));

        PartDefinition EquipLB = EquipBaseL.addOrReplaceChild("EquipLB", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 10F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, -3.5F, -5F, 0.0873F, 0F, 0F));

        PartDefinition EquipBaseR = Butt.addOrReplaceChild("EquipBaseR", CubeListBuilder.create().texOffs(0, 0).addBox(-16F, -3F, -3F, 16F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 10F, -3F, 0.0524F, 0.1396F, -0.1396F));

        PartDefinition EquipRHead = EquipBaseR.addOrReplaceChild("EquipRHead", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -6F, -10F, 11F, 6F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, -2F, 0F, -0.1396F, 0F, 0F));

        PartDefinition EquipLTU_1 = EquipRHead.addOrReplaceChild("EquipLTU_1", CubeListBuilder.create().texOffs(47, 29).addBox(-4.5F, 0F, -9F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.1F, -0.7F, 0.0873F, 0F, 0F));

        PartDefinition EquipHeadC01_1 = EquipRHead.addOrReplaceChild("EquipHeadC01_1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, 0F, 5F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8.7F, -7F, -0.1745F, 0F, 0F));

        PartDefinition EquipHeadC02_1 = EquipHeadC01_1.addOrReplaceChild("EquipHeadC02_1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.6F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 1.5F, 0.5F));

        PartDefinition EquipLT01_1 = EquipBaseR.addOrReplaceChild("EquipLT01_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -5F, -6F, 4F, 11F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-15F, 0F, 0F, 0F, 0.1396F, 0F));

        PartDefinition EquipLT02a_1 = EquipLT01_1.addOrReplaceChild("EquipLT02a_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-2F, -2.6F, -5.8F));

        PartDefinition EquipLT02b_1 = EquipLT01_1.addOrReplaceChild("EquipLT02b_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-2F, -0.3F, -5.8F));

        PartDefinition EquipLT02d_1 = EquipLT01_1.addOrReplaceChild("EquipLT02d_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-2F, 4.3F, -5.8F));

        PartDefinition EquipLT02c_1 = EquipLT01_1.addOrReplaceChild("EquipLT02c_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-2F, 2F, -5.8F));

        PartDefinition EquipLJaw_1 = EquipBaseR.addOrReplaceChild("EquipLJaw_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 4F, -9F, 8F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 0F, -1.2F, 0.1745F, 0F, 0F));

        PartDefinition EquipLTD_1 = EquipLJaw_1.addOrReplaceChild("EquipLTD_1", CubeListBuilder.create().texOffs(47, 29).addBox(-4.5F, 0F, -9F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 5F, 0.2F));

        PartDefinition EquipLB_1 = EquipBaseR.addOrReplaceChild("EquipLB_1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 10F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, -3.5F, -5F, 0.0873F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 27).addBox(-7F, 0F, -4.4F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.3F, 0F, -0.0873F, 0F, 0F));

        PartDefinition Cloth02 = Cloth01.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(38, 47).addBox(-4F, 0F, 0F, 8F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.4F, -4.3F, 0.0524F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -2F, -4.9F, 7F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(90, 101).mirror().addBox(-0.5F, 0F, -1.5F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, 6.5F, -4.4F, -0.0873F, -0.0873F, -0.0873F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(90, 101).addBox(-0.5F, 0F, -1.5F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, 7F, -4.4F, -0.1396F, 0.0873F, 0.0873F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(106, 31).addBox(0F, -6F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -7F, -6F, 0.2094F, 0.6981F, 0F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition Hair02 = Head.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(9, 6).addBox(0F, -1.5F, -1.5F, 2F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, -10F, 3.5F, 0F, -0.0873F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(40, 99).addBox(0F, -3F, -2F, 4F, 11F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.7F, 0F, 0F, -0.0873F, 0F, -0.0873F));

        PartDefinition Hair04 = Hair03.addOrReplaceChild("Hair04", CubeListBuilder.create().texOffs(40, 99).addBox(-2F, 0F, -2F, 4F, 11F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 6.5F, 0F, 0.2618F, 0F, -0.2276F));

        PartDefinition Hair05 = Hair04.addOrReplaceChild("Hair05", CubeListBuilder.create().texOffs(40, 99).addBox(-2F, 0F, -2F, 4F, 11F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 0F, 0.5236F, 0F, 0.3491F));

        PartDefinition Hair06 = Hair05.addOrReplaceChild("Hair06", CubeListBuilder.create().texOffs(40, 99).addBox(-2F, 0F, -2F, 4F, 11F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 0F, -0.2618F, 0F, 0.5236F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(54, 44).addBox(-7.5F, 0F, -7F, 15F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.9F, 10F, 0.1571F, 0F, 0F));

        PartDefinition Hat01 = Head.addOrReplaceChild("Hat01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -6F, -6F, 12F, 6F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11F, 1F, -0.4363F, 0F, 0F));

        PartDefinition Hat04a = Hat01.addOrReplaceChild("Hat04a", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -8F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7.4F, -5.5F, 0.2618F, 0.2618F, -0.1745F));

        PartDefinition Hat04b = Hat04a.addOrReplaceChild("Hat04b", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6.8F, -0.4F, -0.5236F, 0F, 0F));

        PartDefinition Hat04c = Hat04b.addOrReplaceChild("Hat04c", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -5F, -2F, 4F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.9F, 0F, -0.6109F, 0F, 0F));

        PartDefinition Hat05a = Hat01.addOrReplaceChild("Hat05a", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -5F, -2F, 4F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -10F, 6F, -0.0873F, 0.5236F, 0.1745F));

        PartDefinition Hat05b = Hat05a.addOrReplaceChild("Hat05b", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -4F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.1F, 0.3F, 0.6109F, 0F, 0F));

        PartDefinition Hat03 = Hat01.addOrReplaceChild("Hat03", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -6F, 0F, 17F, 7F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.4F, 0F, -0.2731F, 0F, 0F));

        PartDefinition Hat02b = Hat01.addOrReplaceChild("Hat02b", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -6F, -7F, 10F, 7F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.7F, -5F, -2.9F, 0.1745F, -0.0524F, 0.0524F));

        PartDefinition Hat06b = Hat01.addOrReplaceChild("Hat06b", CubeListBuilder.create().texOffs(44, 61).addBox(0F, 0F, -2F, 0F, 12F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.5F, -6.4F, 2.5F, 1.0472F, 0.0873F, -0.4363F));

        PartDefinition Hat02a = Hat01.addOrReplaceChild("Hat02a", CubeListBuilder.create().texOffs(0, 0).addBox(-10F, -6F, -7F, 10F, 7F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.7F, -5F, -3F, 0.1745F, 0.0524F, -0.0524F));

        PartDefinition Hat06a = Hat01.addOrReplaceChild("Hat06a", CubeListBuilder.create().texOffs(44, 61).mirror().addBox(0F, 0F, -2F, 0F, 12F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.5F, -6F, 2F, 0.6981F, 0.2618F, -0.6981F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 86).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.2618F, 0F, -0.7854F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 69).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition ArmLeft02a = ArmLeft02.addOrReplaceChild("ArmLeft02a", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 6.5F, -2.4F, 0.0524F, 0F, 0F));

        PartDefinition Cannon01 = ArmLeft02.addOrReplaceChild("Cannon01", CubeListBuilder.create().texOffs(22, 21).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 3F, -2.5F));

        PartDefinition Cannon03 = Cannon01.addOrReplaceChild("Cannon03", CubeListBuilder.create().texOffs(0, 21).addBox(-3.5F, 0F, 0F, 7F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.3F, 0F, 0.0524F, 0F, 0F));

        PartDefinition Cannon04 = Cannon01.addOrReplaceChild("Cannon04", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(-1F, 10F, 0F));

        PartDefinition Cannon02 = Cannon01.addOrReplaceChild("Cannon02", CubeListBuilder.create().texOffs(52, 0).addBox(-4F, 0F, -6F, 8F, 13F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, 2F, -0.0873F, 0F, 0F));

        PartDefinition Cannon05 = Cannon01.addOrReplaceChild("Cannon05", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(1F, 10F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

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

        this.Butt.y = this.buttDefaultY;
        this.Skirt01.y = this.skirt01DefaultY;
        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.z = this.armRight02DefaultZ;
        this.LegLeft01.y = this.legLeft01DefaultY;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight01.y = this.legRight01DefaultY;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.EquipBaseL.y = this.equipBaseLDefaultY;
        this.EquipBaseR.y = this.equipBaseRDefaultY;
    }

    private void applyEquipVisibility(EntityShipBase entity) {
        if (entity == null) {
            return;
        }
        boolean showRigging = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHime.EQUIP_RIGGING);
        this.EquipBaseL.visible = showRigging;
        this.EquipBaseR.visible = showRigging;
        this.Hat01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHime.EQUIP_HAT);
        this.Cannon01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHime.EQUIP_CANNON);
        this.BeltBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHime.EQUIP_BELT);
        boolean showLeg = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHime.EQUIP_LEG);
        this.LegLeft01.visible = showLeg;
        this.LegRight01.visible = showLeg;
        boolean showHand = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHime.EQUIP_HAND);
        this.ArmLeft02a.visible = showHand;
        this.ArmRight02a.visible = showHand;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.0F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.Ahoke.yRot = 0.7F;
        this.BodyMain.xRot = 1.45F;
        this.Butt.xRot = 0.21F;
        this.BeltBase.xRot = 0.09F;
        this.Skirt01.xRot = -0.21F;

        this.Hair03.xRot = 0.0F;
        this.Hair04.xRot = 0.0F;
        this.Hair05.xRot = 0.0F;
        this.Hair06.xRot = 0.0F;
        this.Hair03.zRot = 0.1F;
        this.Hair04.zRot = 0.2F;
        this.Hair05.zRot = 0.3F;
        this.Hair06.zRot = 0.4F;

        this.ArmLeft01.xRot = -2.8F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = 0.7F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 1.0F;

        this.ArmRight01.xRot = -2.8F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -0.7F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = -1.0F;

        this.LegLeft01.xRot = 0.1F;
        this.LegLeft01.yRot = 3.1415F;
        this.LegLeft01.zRot = -0.1F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;

        this.LegRight01.xRot = 0.1F;
        this.LegRight01.yRot = 3.1415F;
        this.LegRight01.zRot = 0.1F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = (float) Math.cos(ageInTicks * 0.08F + limbSwing * 0.25F);
        float angleX1 = (float) Math.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = (float) Math.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleX3 = (float) Math.cos(ageInTicks * 0.08F + 0.9F + limbSwing * 0.5F);
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = (float) Math.cos(limbSwing * 0.7F + Math.PI) * limbSwingAmount;

        if (entity.isInWater()) {
            this.poseTranslateY += angleX * 0.05F + 0.025F;
        } else {
            this.poseTranslateY += angleX * 0.015F + 0.025F;
        }

        float addk1 = angleAdd1 * 0.5F - 0.28F;
        float addk2 = angleAdd2 * 0.5F - 0.21F;
        float headX = headPitch * ((float) Math.PI / 180F) * -0.5F;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = 0.0F;
        this.Ahoke.yRot = angleX * 0.05F + 0.7F;
        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.35F;
        this.BeltBase.xRot = 0.09F;
        this.Skirt01.xRot = -0.21F;

        this.Hat06a.xRot = -angleX * 0.1F + 0.7F;
        this.Hat06b.xRot = -angleX3 * 0.1F + 1.04F;
        this.Hair03.xRot = angleX * 0.05F - 0.09F + headX;
        this.Hair03.zRot = -0.09F;
        this.Hair04.xRot = -angleX1 * 0.06F + 0.26F + headX;
        this.Hair04.zRot = -0.22F;
        this.Hair05.xRot = -angleX2 * 0.07F + 0.52F + headX;
        this.Hair05.zRot = 0.35F;
        this.Hair06.xRot = -angleX3 * 0.12F - 0.15F + headX;
        this.Hair06.zRot = 0.52F;

        this.ArmLeft01.zRot = angleX * 0.03F - 0.3F;
        this.ArmRight01.zRot = -angleX * 0.03F + 0.3F;

        this.ArmLeft01.xRot = angleAdd2 * 0.4F + 0.26F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.4F + 0.26F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.0873F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;

        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.0873F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.EquipBaseL.xRot = 0.05F;
        this.EquipBaseR.xRot = 0.05F;
        this.EquipHeadC02.xRot = this.Head.xRot * 0.5F - 0.04F;
        this.EquipHeadC02_1.xRot = this.Head.xRot * 0.5F - 0.12F;
        this.EquipLT01.xRot = this.Head.xRot * 0.8F - 0.2F;
        this.EquipLT01_1.xRot = this.Head.xRot * 0.8F - 0.2F;
        this.EquipLJaw.xRot = angleX * 0.15F + 0.15F;
        this.EquipLJaw_1.xRot = angleX3 * 0.15F + 0.15F;

        this.HairL01.xRot = angleX * 0.02F + headX + 0.14F;
        this.HairR01.xRot = angleX * 0.02F + headX - 0.09F;

        float headZ = this.Head.zRot * -0.5F;
        this.Hair03.zRot += headZ;
        this.Hair04.zRot += headZ;
        this.Hair05.zRot += headZ;
        this.Hair06.zRot += headZ;
        this.HairL01.zRot = headZ - 0.09F;
        this.HairR01.zRot = headZ - 0.09F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = (float) Math.cos(limbSwing * 0.7F + Math.PI) * limbSwingAmount;

        boolean isSprinting = entity.isSprinting() || limbSwingAmount > 0.9F;
        boolean isCrouching = entity.isCrouching();
        boolean isPassenger = entity.isPassenger();
        boolean isSitting = entity.getIsSitting() || isPassenger;

        if (isSprinting) {
            this.Head.xRot -= 0.5F;
            this.BodyMain.xRot = 0.5F;
            this.ArmLeft01.xRot = angleAdd2 * 0.1F + 0.55F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = -0.5F;
            this.ArmRight01.xRot = angleAdd1 * 0.1F + 0.55F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = 0.5F;
            this.Hair05.xRot -= 0.2F;
            this.LegLeft01.xRot = angleAdd1 * 0.8F - 0.75F;
            this.LegRight01.xRot = angleAdd2 * 0.8F - 0.75F;
        }

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.ArmLeft01.xRot = -0.35F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.35F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = -0.2618F;
            this.LegLeft01.xRot -= 1.0F;
            this.LegRight01.xRot -= 1.0F;
            this.Hair03.xRot -= 0.6F;
            this.Hair04.xRot -= 0.6F;
            this.Hair05.xRot -= 0.6F;
            this.Hair06.xRot -= 0.6F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (hasLegacyState(entity, 1, 4)) {
                if (hasLegacyState(entity, 7, 4)) {
                    this.poseTranslateY = 0.46F * 3.5F;
                    Head.xRot = 0.4F;
                    BeltBase.xRot = -0.9F;
                    Skirt01.xRot = -0.14F;
                    Skirt01.y = skirt01DefaultY + (-0.12F * OFFSET_SCALE);
                    ArmLeft01.xRot = 0.4F;
                    ArmLeft01.yRot = -2.9671F;
                    ArmLeft01.zRot = -2.62F;
                    ArmLeft02.zRot = 1.0F;
                    ArmRight01.xRot = 0.5236F;
                    ArmRight01.yRot = 2.9671F;
                    ArmRight01.zRot = 2.62F;
                    ArmRight02.zRot = -1.0F;
                    LegLeft01.xRot = -2.4131F;
                    LegRight01.xRot = -2.2689F;
                    LegLeft01.zRot = -0.2731F;
                    LegLeft02.xRot = 1.4570F;
                    LegRight01.zRot = 0.2276F;
                    LegRight02.xRot = 1.0472F;
                    EquipBaseL.xRot = -0.6F;
                    EquipBaseR.xRot = -0.6F;
                    EquipBaseL.y = equipBaseLDefaultY + (-0.62F * OFFSET_SCALE);
                    EquipBaseR.y = equipBaseRDefaultY + (-0.62F * OFFSET_SCALE);
                } else {
                    this.poseTranslateY = 0.43F * 3.5F;
                    Head.xRot -= 0.7F;
                    BodyMain.xRot = 0.35F;
                    BeltBase.xRot = -0.5F;
                    Skirt01.xRot = -0.14F;
                    Skirt01.y = skirt01DefaultY + (-0.12F * OFFSET_SCALE);
                    ArmLeft01.xRot = -0.5236F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = 0.3491F;
                    ArmRight01.xRot = -0.5236F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.3491F;
                    LegLeft01.xRot = -1.4486F;
                    LegLeft01.yRot = -0.5236F;
                    LegLeft01.zRot = -1.3963F;
                    LegLeft02.xRot = 2.1817F;
                    LegLeft02.z = legLeft02DefaultZ + (0.37F * OFFSET_SCALE);
                    LegRight01.xRot = -1.4486F;
                    LegRight01.yRot = 0.5236F;
                    LegRight01.zRot = 1.3963F;
                    LegRight02.xRot = 2.1817F;
                    LegRight02.z = legRight02DefaultZ + (0.37F * OFFSET_SCALE);
                    EquipBaseL.xRot = -0.9F;
                    EquipBaseR.xRot = -0.9F;
                    EquipBaseL.y = equipBaseLDefaultY + (-0.4F * OFFSET_SCALE);
                    EquipBaseR.y = equipBaseRDefaultY + (-0.4F * OFFSET_SCALE);
                    Hair03.xRot -= 0.1F;
                    Hair04.xRot -= 0.3F;
                    Hair05.xRot -= 0.5F;
                    Hair06.xRot -= 0.6F;
                }
            } else if (!EquipBaseL.visible) {
                this.poseTranslateY = 0.44F * 3.5F;
                Head.xRot -= 0.7F;
                BodyMain.xRot = 0.5236F;
                BeltBase.xRot = -0.9F;
                Skirt01.xRot = -0.14F;
                Skirt01.y = skirt01DefaultY + (-0.12F * OFFSET_SCALE);
                ArmLeft01.xRot = -0.5236F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = 0.3146F;
                ArmRight01.xRot = -0.5236F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = -0.3146F;
                LegLeft01.xRot = -2.2689F;
                LegRight01.xRot = -2.2689F;
                LegLeft01.yRot = -0.3491F;
                LegLeft01.zRot = 0.0873F;
                LegLeft02.z = legLeft02DefaultZ;
                LegRight01.yRot = 0.3491F;
                LegRight01.zRot = -0.0873F;
                LegRight02.z = legRight02DefaultZ;
                Hair03.xRot -= 0.1F;
                Hair04.xRot -= 0.3F;
                Hair05.xRot -= 0.5F;
                Hair06.xRot -= 0.6F;
            } else {
                this.poseTranslateY = SIT_TRANSLATE_Y;
                Head.xRot -= 0.7F;
                BodyMain.xRot = 0.5236F;
                ArmLeft01.xRot = -0.5236F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = 0.3146F;
                ArmRight01.xRot = -0.5236F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = -0.3146F;
                LegLeft01.xRot = -2.23F;
                LegRight01.xRot = -2.23F;
                LegLeft01.yRot = -0.3491F;
                LegLeft01.zRot = 0.0873F;
                LegRight01.yRot = 0.3491F;
                LegRight01.zRot = -0.0873F;
                EquipBaseL.xRot = -1.34F;
                EquipBaseR.xRot = -1.34F;
                Hair03.xRot -= 0.1F;
                Hair04.xRot -= 0.3F;
                Hair05.xRot -= 0.5F;
                Hair06.xRot -= 0.6F;
            }
        }

        if (entity.getAttackTick() > 0) {
            ArmLeft01.xRot = -1.4F + Head.xRot * 0.75F;
            ArmLeft01.yRot = 0.17F;
            ArmLeft01.zRot = 0.26F;
            ArmLeft02.zRot = 0.0F;
            ArmRight01.xRot = -1.22F + Head.xRot * 0.75F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.52F;
            ArmRight02.zRot = -0.78F;
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
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);
        this.EquipLegL.copyFrom(this.LegLeft01);
        this.EquipLegR.copyFrom(this.LegRight01);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

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

        this.GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
