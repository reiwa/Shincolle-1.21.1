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

public class ModelIsolatedHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "isolated_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelIsolatedHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelIsolatedHime");
    private static final float SIT_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelIsolatedHime");
    private static final float RIDING_TRANSLATE_Y = LegacyPoseOffsets.ridingY("ModelIsolatedHime");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft01;
    private final ModelPart Cloth02a;
    private final ModelPart Head;
    private final ModelPart Cloth01a;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Hair01;
    private final ModelPart HatBase;
    private final ModelPart HairU01;
    private final ModelPart Ahoke;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart Hat01;
    private final ModelPart Hat03;
    private final ModelPart Hat05;
    private final ModelPart HeadH1;
    private final ModelPart HeadH2;
    private final ModelPart HeadH3;
    private final ModelPart HeadH4;
    private final ModelPart HeadH5;
    private final ModelPart HeadH6;
    private final ModelPart Hat02a;
    private final ModelPart Hat02b;
    private final ModelPart Hat02c;
    private final ModelPart Hat02d;
    private final ModelPart Hat02e;
    private final ModelPart Hat02f;
    private final ModelPart Hat02g;
    private final ModelPart Hat02h;
    private final ModelPart Hat02i;
    private final ModelPart Hat02j;
    private final ModelPart Hat04a;
    private final ModelPart Hat04b;
    private final ModelPart Hat04c;
    private final ModelPart Hat04d;
    private final ModelPart Hat04e;
    private final ModelPart Hat04f;
    private final ModelPart Hat04g;
    private final ModelPart Hat04h;
    private final ModelPart Hat06a;
    private final ModelPart Hat02b_1;
    private final ModelPart Hat02d_1;
    private final ModelPart Hat02e_1;
    private final ModelPart Hat02f_1;
    private final ModelPart Hat02g_1;
    private final ModelPart Hat02h_1;
    private final ModelPart Hat02i_1;
    private final ModelPart Cloth01b;
    private final ModelPart Cloth01c;
    private final ModelPart Cloth01b2;
    private final ModelPart Cloth01c2;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt02;
    private final ModelPart Skirt03;
    private final ModelPart LegRight02a;
    private final ModelPart LegArmor02a;
    private final ModelPart LegRight02b;
    private final ModelPart LegArmor02b;
    private final ModelPart LegArmor02c;
    private final ModelPart LegLeft02a;
    private final ModelPart LegArmor01a;
    private final ModelPart LegLeft02b;
    private final ModelPart LegArmor01b;
    private final ModelPart LegArmor01c;
    private final ModelPart ArmRight02;
    private final ModelPart Cloth02c;
    private final ModelPart Cloth03a;
    private final ModelPart ArmLeft02;
    private final ModelPart Cloth02b;
    private final ModelPart Cloth03b;
    private final ModelPart EquipRdL01;
    private final ModelPart EquipRdL02;
    private final ModelPart EquipRdL03;
    private final ModelPart EquipRdL04;
    private final ModelPart EquipRdL05;
    private final ModelPart EquipRdL06;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowHatBase;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float cloth01aDefaultY;
    private final float cloth01aDefaultZ;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultZ;
    private final float legLeft01DefaultY;
    private final float legLeft01DefaultZ;
    private final float legLeft02aDefaultX;
    private final float legLeft02aDefaultZ;
    private final float legRight01DefaultY;
    private final float legRight01DefaultZ;
    private final float legRight02aDefaultX;
    private final float legRight02aDefaultY;
    private final float legRight02aDefaultZ;

    public ModelIsolatedHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.Cloth02b = this.ArmLeft01.getChild("Cloth02b");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.Cloth03b = this.ArmLeft02.getChild("Cloth03b");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.Head.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HatBase = this.Head.getChild("HatBase");
        this.Hat03 = this.HatBase.getChild("Hat03");
        this.Hat04f = this.Hat03.getChild("Hat04f");
        this.Hat04g = this.Hat03.getChild("Hat04g");
        this.Hat04e = this.Hat03.getChild("Hat04e");
        this.Hat04d = this.Hat03.getChild("Hat04d");
        this.Hat04a = this.Hat03.getChild("Hat04a");
        this.Hat04h = this.Hat03.getChild("Hat04h");
        this.Hat04b = this.Hat03.getChild("Hat04b");
        this.Hat04c = this.Hat03.getChild("Hat04c");
        this.Hat01 = this.HatBase.getChild("Hat01");
        this.Hat02d = this.Hat01.getChild("Hat02d");
        this.Hat02a = this.Hat01.getChild("Hat02a");
        this.Hat02b = this.Hat01.getChild("Hat02b");
        this.Hat02g = this.Hat01.getChild("Hat02g");
        this.Hat02h = this.Hat01.getChild("Hat02h");
        this.Hat02e = this.Hat01.getChild("Hat02e");
        this.Hat02c = this.Hat01.getChild("Hat02c");
        this.Hat02i = this.Hat01.getChild("Hat02i");
        this.Hat02f = this.Hat01.getChild("Hat02f");
        this.Hat02j = this.Hat01.getChild("Hat02j");
        this.Hat05 = this.HatBase.getChild("Hat05");
        this.Hat02b_1 = this.Hat05.getChild("Hat02b_1");
        this.Hat02e_1 = this.Hat05.getChild("Hat02e_1");
        this.Hat06a = this.Hat05.getChild("Hat06a");
        this.Hat02g_1 = this.Hat05.getChild("Hat02g_1");
        this.Hat02i_1 = this.Hat05.getChild("Hat02i_1");
        this.Hat02h_1 = this.Hat05.getChild("Hat02h_1");
        this.Hat02f_1 = this.Hat05.getChild("Hat02f_1");
        this.Hat02d_1 = this.Hat05.getChild("Hat02d_1");
        this.Cloth01a = this.Neck.getChild("Cloth01a");
        this.Cloth01b2 = this.Cloth01a.getChild("Cloth01b2");
        this.Cloth01c = this.Cloth01a.getChild("Cloth01c");
        this.Cloth01b = this.Cloth01a.getChild("Cloth01b");
        this.Cloth01c2 = this.Cloth01a.getChild("Cloth01c2");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.Cloth02c = this.ArmRight01.getChild("Cloth02c");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.Cloth03a = this.ArmRight02.getChild("Cloth03a");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02a = this.LegLeft01.getChild("LegLeft02a");
        this.LegArmor01a = this.LegLeft01.getChild("LegArmor01a");
        this.LegArmor01b = this.LegArmor01a.getChild("LegArmor01b");
        this.LegArmor01c = this.LegArmor01b.getChild("LegArmor01c");
        this.LegLeft02b = this.LegLeft01.getChild("LegLeft02b");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.Skirt03 = this.Skirt02.getChild("Skirt03");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02b = this.LegRight01.getChild("LegRight02b");
        this.LegRight02a = this.LegRight01.getChild("LegRight02a");
        this.LegArmor02a = this.LegRight01.getChild("LegArmor02a");
        this.LegArmor02b = this.LegArmor02a.getChild("LegArmor02b");
        this.LegArmor02c = this.LegArmor02b.getChild("LegArmor02c");
        this.Cloth02a = this.BodyMain.getChild("Cloth02a");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.GlowHatBase = this.GlowHead.getChild("GlowHatBase");
        this.HeadH1 = this.GlowHatBase.getChild("HeadH1");
        this.HeadH2 = this.HeadH1.getChild("HeadH2");
        this.HeadH3 = this.HeadH2.getChild("HeadH3");
        this.HeadH4 = this.GlowHatBase.getChild("HeadH4");
        this.HeadH5 = this.HeadH4.getChild("HeadH5");
        this.HeadH6 = this.HeadH5.getChild("HeadH6");
        this.EquipRdL01 = this.GlowBodyMain.getChild("EquipRdL01");
        this.EquipRdL02 = this.EquipRdL01.getChild("EquipRdL02");
        this.EquipRdL03 = this.EquipRdL02.getChild("EquipRdL03");
        this.EquipRdL04 = this.EquipRdL03.getChild("EquipRdL04");
        this.EquipRdL05 = this.EquipRdL04.getChild("EquipRdL05");
        this.EquipRdL06 = this.EquipRdL05.getChild("EquipRdL06");
        this.initFaceParts(this.GlowHead);
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.cloth01aDefaultY = this.Cloth01a.y;
        this.cloth01aDefaultZ = this.Cloth01a.z;
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultZ = this.ArmRight02.z;
        this.legLeft01DefaultY = this.LegLeft01.y;
        this.legLeft01DefaultZ = this.LegLeft01.z;
        this.legLeft02aDefaultX = this.LegLeft02a.x;
        this.legLeft02aDefaultZ = this.LegLeft02a.z;
        this.legRight01DefaultY = this.LegRight01.y;
        this.legRight01DefaultZ = this.LegRight01.z;
        this.legRight02aDefaultX = this.LegRight02a.x;
        this.legRight02aDefaultY = this.LegRight02a.y;
        this.legRight02aDefaultZ = this.LegRight02a.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 105).addBox(-6.5F, -11F, -4F, 13F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 84).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, -0.0524F, 0F, -0.2793F));

        PartDefinition Cloth02b = ArmLeft01.addOrReplaceChild("Cloth02b", CubeListBuilder.create().texOffs(128, 85).addBox(-3F, 0F, -3.5F, 6F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.9F, -1.5F, 0F, 0F, 0F, 0.0524F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 63).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition Cloth03b = ArmLeft02.addOrReplaceChild("Cloth03b", CubeListBuilder.create().texOffs(128, 50).mirror().addBox(-3F, 0F, -3F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 3.5F, -2.5F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(103, 35).addBox(-2.5F, -2F, -3F, 5F, 2F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = Head.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(50, 30).addBox(-7.5F, 0F, -4F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6F, 2F, 0.2094F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 38).addBox(-8F, 0F, -6F, 16F, 16F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 2.5F, 0.1222F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(0, 15).addBox(-7.5F, 0F, -5.5F, 15F, 15F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -0.1F, -0.2618F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(106, 31).addBox(0F, -6F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -7F, -6F, 0.5236F, 0.6981F, 0F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition HatBase = Head.addOrReplaceChild("HatBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -14.6F, -2F, -0.0524F, 0F, 0F));

        PartDefinition Hat03 = HatBase.addOrReplaceChild("Hat03", CubeListBuilder.create().texOffs(88, 23).addBox(-8.5F, 0F, -0.5F, 17F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, 5.5F, 1.3F, 0F, -0.0524F, 1.5708F));

        PartDefinition Hat04f = Hat03.addOrReplaceChild("Hat04f", CubeListBuilder.create().texOffs(30, 6).addBox(-2F, -3F, 0F, 4F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.3F, -1F, 2.5F, 0F, -0.034906585F, 2.9670596F));

        PartDefinition Hat04g = Hat03.addOrReplaceChild("Hat04g", CubeListBuilder.create().texOffs(30, 6).addBox(-2F, -3F, 0F, 4F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -0.6F, 2.2F, -0.0524F, -0.034906585F, -3.0718F));

        PartDefinition Hat04e = Hat03.addOrReplaceChild("Hat04e", CubeListBuilder.create().texOffs(60, 2).addBox(-2F, -3F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.2F, 1.1F, 2.8F, 0.1396F, 0F, 0.034906585F));

        PartDefinition Hat04d = Hat03.addOrReplaceChild("Hat04d", CubeListBuilder.create().texOffs(42, 10).addBox(-2F, -3F, -10F, 4F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.9F, -1.5F, -0.2F, 0.017453293F, 0.017453293F, 2.7925267F));

        PartDefinition Hat04a = Hat03.addOrReplaceChild("Hat04a", CubeListBuilder.create().texOffs(60, 15).addBox(-2F, -3F, -10F, 4F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -0.5F, -0.0873F, -0.034906585F, -0.0698F));

        PartDefinition Hat04h = Hat03.addOrReplaceChild("Hat04h", CubeListBuilder.create().texOffs(60, 2).addBox(-2F, -3F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.9F, 1.2F, 2.4F, 0.0524F, 0.0698F, 0.2967F));

        PartDefinition Hat04b = Hat03.addOrReplaceChild("Hat04b", CubeListBuilder.create().texOffs(42, 10).addBox(-2F, -3F, -10F, 4F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -2F, -0.2F, -0.0524F, 0.0873F, -3.0718F));

        PartDefinition Hat04c = Hat03.addOrReplaceChild("Hat04c", CubeListBuilder.create().texOffs(60, 15).addBox(-2F, -3F, -10F, 4F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.4F, 1F, -0.1F, 0.0698F, -0.1396F, 0.2618F));

        PartDefinition Hat01 = HatBase.addOrReplaceChild("Hat01", CubeListBuilder.create().texOffs(88, 23).addBox(-8.5F, 0F, -0.5F, 17F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, -3.6F, 1F));

        PartDefinition Hat02d = Hat01.addOrReplaceChild("Hat02d", CubeListBuilder.create().texOffs(42, 10).addBox(-2F, -3F, -10F, 4F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.2F, -0.7F, -0.6F, -0.0524F, -0.017453293F, 2.9670596F));

        PartDefinition Hat02a = Hat01.addOrReplaceChild("Hat02a", CubeListBuilder.create().texOffs(60, 15).addBox(-2F, -3F, -10F, 4F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.5F, -0.7F, -0.0698F, 0F, 0F));

        PartDefinition Hat02b = Hat01.addOrReplaceChild("Hat02b", CubeListBuilder.create().texOffs(42, 10).addBox(-2F, -3F, -10F, 4F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.2F, -0.7F, -0.6F, -0.0524F, 0.017453293F, -2.9670596F));

        PartDefinition Hat02g = Hat01.addOrReplaceChild("Hat02g", CubeListBuilder.create().texOffs(30, 6).addBox(-2F, -3F, 0F, 4F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.8F, -0.5F, 2.3F, 0.0524F, 0.0524F, 3.1067F));

        PartDefinition Hat02h = Hat01.addOrReplaceChild("Hat02h", CubeListBuilder.create().texOffs(60, 2).addBox(-2F, -3F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.2F, 2.4F, 2.6F, 0.0873F, -0.0524F, -0.5236F));

        PartDefinition Hat02e = Hat01.addOrReplaceChild("Hat02e", CubeListBuilder.create().texOffs(60, 15).addBox(-2F, -3F, -10F, 4F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.6F, 2F, -0.6F, 0.0524F, 0.034906585F, -0.5760F));

        PartDefinition Hat02c = Hat01.addOrReplaceChild("Hat02c", CubeListBuilder.create().texOffs(60, 15).addBox(-2F, -3F, -10F, 4F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.6F, 2F, -0.6F, 0.0524F, -0.034906585F, 0.5760F));

        PartDefinition Hat02i = Hat01.addOrReplaceChild("Hat02i", CubeListBuilder.create().texOffs(30, 6).addBox(-2F, -3F, 0F, 4F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.8F, -0.5F, 2.3F, 0.0524F, -0.0524F, -3.1067F));

        PartDefinition Hat02f = Hat01.addOrReplaceChild("Hat02f", CubeListBuilder.create().texOffs(60, 2).addBox(-2F, -3F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.7F, 2.6F, 0.0873F, 0F, 0F));

        PartDefinition Hat02j = Hat01.addOrReplaceChild("Hat02j", CubeListBuilder.create().texOffs(60, 2).addBox(-2F, -3F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.2F, 2.4F, 2.6F, 0.0873F, 0.0524F, 0.5236F));

        PartDefinition Hat05 = HatBase.addOrReplaceChild("Hat05", CubeListBuilder.create().texOffs(88, 23).addBox(-8.5F, 0F, -0.5F, 17F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, 5.5F, 1.3F, 0F, 0.0524F, -1.5708F));

        PartDefinition Hat02b_1 = Hat05.addOrReplaceChild("Hat02b_1", CubeListBuilder.create().texOffs(42, 10).addBox(-2F, -3F, -10F, 4F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.9F, -1.5F, -0.2F, 0.017453293F, -0.017453293F, -2.7925267F));

        PartDefinition Hat02e_1 = Hat05.addOrReplaceChild("Hat02e_1", CubeListBuilder.create().texOffs(60, 15).addBox(-2F, -3F, -10F, 4F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.4F, 1F, -0.1F, -0.0698F, 0.1396F, -0.2618F));

        PartDefinition Hat06a = Hat05.addOrReplaceChild("Hat06a", CubeListBuilder.create().texOffs(60, 15).addBox(-2F, -3F, -10F, 4F, 3F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 0.5F, -0.5F, -0.0873F, 0.034906585F, 0.0698F));

        PartDefinition Hat02g_1 = Hat05.addOrReplaceChild("Hat02g_1", CubeListBuilder.create().texOffs(30, 6).addBox(-2F, -3F, 0F, 4F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -0.6F, 2.2F, -0.0524F, 0.034906585F, 3.0718F));

        PartDefinition Hat02i_1 = Hat05.addOrReplaceChild("Hat02i_1", CubeListBuilder.create().texOffs(30, 6).addBox(-2F, -3F, 0F, 4F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.3F, -1F, 2.5F, 0F, 0.034906585F, -2.9670596F));

        PartDefinition Hat02h_1 = Hat05.addOrReplaceChild("Hat02h_1", CubeListBuilder.create().texOffs(60, 2).addBox(-2F, -3F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.9F, 1.2F, 2.4F, 0.0524F, -0.0698F, -0.2967F));

        PartDefinition Hat02f_1 = Hat05.addOrReplaceChild("Hat02f_1", CubeListBuilder.create().texOffs(60, 2).addBox(-2F, -3F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 1.1F, 2.8F, 0.1396F, 0F, 0.034906585F));

        PartDefinition Hat02d_1 = Hat05.addOrReplaceChild("Hat02d_1", CubeListBuilder.create().texOffs(42, 10).addBox(-2F, -3F, -10F, 4F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -2F, -0.2F, -0.0524F, -0.0873F, 3.0718F));

        PartDefinition Cloth01a = Neck.addOrReplaceChild("Cloth01a", CubeListBuilder.create().texOffs(51, 2).addBox(-1F, -2.5F, -1F, 2F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -7.9F, 0.7854F, 0F, 0F));

        PartDefinition Cloth01b2 = Cloth01a.addOrReplaceChild("Cloth01b2", CubeListBuilder.create().texOffs(51, 0).addBox(0F, -3F, -1F, 6F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 0.3F, 0.3F, 0.0873F, 0.1745F, -0.1489F));

        PartDefinition Cloth01c = Cloth01a.addOrReplaceChild("Cloth01c", CubeListBuilder.create().texOffs(0, 16).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 1.6F, -0.7F, -0.7854F, 0.1396F, 0.1745F));

        PartDefinition Cloth01b = Cloth01a.addOrReplaceChild("Cloth01b", CubeListBuilder.create().texOffs(51, 0).addBox(-6F, -3F, -1F, 6F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 0.3F, 0.3F, 0.0873F, -0.1745F, 0.1396F));

        PartDefinition Cloth01c2 = Cloth01a.addOrReplaceChild("Cloth01c2", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 1.6F, -0.7F, -0.733F, -0.1396F, -0.1745F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0.2618F, 0F, 0.2793F));

        PartDefinition Cloth02c = ArmRight01.addOrReplaceChild("Cloth02c", CubeListBuilder.create().texOffs(128, 85).mirror().addBox(-3F, 0F, -3.5F, 6F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.9F, -1.5F, 0F, 0F, 0F, -0.0524F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 63).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition Cloth03a = ArmRight02.addOrReplaceChild("Cloth03a", CubeListBuilder.create().texOffs(128, 50).addBox(-3F, 0F, -3F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(2.5F, 3.5F, -2.5F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(82, 0).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.1571F, 0F, 0.0873F));

        PartDefinition LegLeft02a = LegLeft01.addOrReplaceChild("LegLeft02a", CubeListBuilder.create().texOffs(0, 63).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition LegArmor01a = LegLeft01.addOrReplaceChild("LegArmor01a", CubeListBuilder.create().texOffs(0, 3).addBox(-3.5F, -4F, 0F, 7F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13F, -5F, -0.2618F, 0F, 0F));

        PartDefinition LegArmor01b = LegArmor01a.addOrReplaceChild("LegArmor01b", CubeListBuilder.create().texOffs(12, 0).addBox(-2.5F, 0F, 0F, 5F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.6109F, 0F, 0F));

        PartDefinition LegArmor01c = LegArmor01b.addOrReplaceChild("LegArmor01c", CubeListBuilder.create().texOffs(0, 3).addBox(-1F, -4F, 0F, 2F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0.2F, 0.6109F, 0F, 0F));

        PartDefinition LegLeft02b = LegLeft01.addOrReplaceChild("LegLeft02b", CubeListBuilder.create().texOffs(128, 63).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(128, 0).addBox(-9F, 0F, -6.2F, 18F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0F, -0.0873F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(128, 15).addBox(-10.5F, 0F, -6F, 21F, 4F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.7F, -1F, -0.0873F, 0F, 0F));

        PartDefinition Skirt03 = Skirt02.addOrReplaceChild("Skirt03", CubeListBuilder.create().texOffs(128, 32).addBox(-11.5F, 0F, -6.5F, 23F, 4F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 0F, -0.0524F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.2967F, 0F, -0.0873F));

        PartDefinition LegRight02b = LegRight01.addOrReplaceChild("LegRight02b", CubeListBuilder.create().texOffs(128, 63).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition LegRight02a = LegRight01.addOrReplaceChild("LegRight02a", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition LegArmor02a = LegRight01.addOrReplaceChild("LegArmor02a", CubeListBuilder.create().texOffs(10, 0).addBox(-3.5F, -4F, 0F, 7F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13F, -5F, -0.2618F, 0F, 0F));

        PartDefinition LegArmor02b = LegArmor02a.addOrReplaceChild("LegArmor02b", CubeListBuilder.create().texOffs(1, 0).addBox(-2.5F, 0F, 0F, 5F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.6109F, 0F, 0F));

        PartDefinition LegArmor02c = LegArmor02b.addOrReplaceChild("LegArmor02c", CubeListBuilder.create().texOffs(0, 3).addBox(-1F, -4F, 0F, 2F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0.2F, 0.6109F, 0F, 0F));

        PartDefinition Cloth02a = BodyMain.addOrReplaceChild("Cloth02a", CubeListBuilder.create().texOffs(128, 99).addBox(-7F, 0F, -4F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.5F, -0.6F, 0.0524F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowHatBase = GlowHead.addOrReplaceChild("GlowHatBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -14.6F, -2F, -0.0524F, 0F, 0F));

        PartDefinition HeadH1 = GlowHatBase.addOrReplaceChild("HeadH1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, -2F, -2F, 2F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8.5F, -2F, 2F, 0.1745F, 0F, 0.4363F));

        PartDefinition HeadH2 = HeadH1.addOrReplaceChild("HeadH2", CubeListBuilder.create().texOffs(33, 102).addBox(-1F, -1.5F, -1.5F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.8F, 0F, 0F, 0F, 0F, 0.1222F));

        PartDefinition HeadH3 = HeadH2.addOrReplaceChild("HeadH3", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -1F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.7F, 0F, 0F, 0F, -0.0873F, 0.1745F));

        PartDefinition HeadH4 = GlowHatBase.addOrReplaceChild("HeadH4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, -2F, -2F, 2F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.5F, -2F, 2F, 0.1745F, 0F, -0.4363F));

        PartDefinition HeadH5 = HeadH4.addOrReplaceChild("HeadH5", CubeListBuilder.create().texOffs(33, 102).addBox(0F, -1.5F, -1.5F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.8F, 0F, 0F, 0F, 0F, -0.1222F));

        PartDefinition HeadH6 = HeadH5.addOrReplaceChild("HeadH6", CubeListBuilder.create().texOffs(0, 900).addBox(0F, -1F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.7F, 0F, 0F, 0F, 0.0873F, -0.1745F));

        PartDefinition EquipRdL01 = GlowBodyMain.addOrReplaceChild("EquipRdL01", CubeListBuilder.create().texOffs(128, 115).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, -6F, 5F, 1.5708F, -0.1745F, -0.7854F));

        PartDefinition EquipRdL02 = EquipRdL01.addOrReplaceChild("EquipRdL02", CubeListBuilder.create().texOffs(128, 115).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.4363F, 0F, 0F));

        PartDefinition EquipRdL03 = EquipRdL02.addOrReplaceChild("EquipRdL03", CubeListBuilder.create().texOffs(128, 115).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.6981F, 0F, 0F));

        PartDefinition EquipRdL04 = EquipRdL03.addOrReplaceChild("EquipRdL04", CubeListBuilder.create().texOffs(128, 115).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.3491F, 0F, 0F));

        PartDefinition EquipRdL05 = EquipRdL04.addOrReplaceChild("EquipRdL05", CubeListBuilder.create().texOffs(128, 115).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.2618F, 0F, 0F));

        PartDefinition EquipRdL06 = EquipRdL05.addOrReplaceChild("EquipRdL06", CubeListBuilder.create().texOffs(128, 115).addBox(-3.5F, 0F, -12F, 7F, 1F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -11F, -0.1745F, 0F, 0F));

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
        this.Cloth01a.y = this.cloth01aDefaultY;
        this.Cloth01a.z = this.cloth01aDefaultZ;
        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.z = this.armRight02DefaultZ;
        this.LegLeft01.y = this.legLeft01DefaultY;
        this.LegLeft01.z = this.legLeft01DefaultZ;
        this.LegLeft02a.x = this.legLeft02aDefaultX;
        this.LegLeft02a.z = this.legLeft02aDefaultZ;
        this.LegRight01.y = this.legRight01DefaultY;
        this.LegRight01.z = this.legRight01DefaultZ;
        this.LegRight02a.x = this.legRight02aDefaultX;
        this.LegRight02a.y = this.legRight02aDefaultY;
        this.LegRight02a.z = this.legRight02aDefaultZ;
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) {
            return;
        }

        boolean showHatBase = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_HAT_BASE);
        boolean showHeadGear = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_HEAD_GEAR);
        boolean showCloth1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_CLOTH_1);
        boolean showCloth2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_CLOTH_2);
        boolean showCloth3 = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_CLOTH_3);
        boolean showLegOuter = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_LEG_OUTER);
        boolean showLegArmor = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_LEG_ARMOR);
        boolean showRoad = entity.getEquipFlag(org.trp.shincolle.entity.EntityIsolatedHime.EQUIP_ROAD);

        this.HatBase.visible = showHatBase;
        this.GlowHatBase.visible = showHatBase;
        this.HeadH1.visible = showHeadGear;
        this.HeadH4.visible = showHeadGear;
        this.Cloth01a.visible = showCloth1;
        this.Cloth02a.visible = showCloth2;
        this.Cloth02b.visible = showCloth2;
        this.Cloth02c.visible = showCloth2;
        this.Cloth03a.visible = showCloth3;
        this.Cloth03b.visible = showCloth3;
        this.LegLeft02b.visible = showLegOuter;
        this.LegRight02b.visible = showLegOuter;
        this.LegLeft02a.visible = !showLegOuter;
        this.LegRight02a.visible = !showLegOuter;
        this.LegArmor01a.visible = showLegArmor;
        this.LegArmor02a.visible = showLegArmor;
        this.EquipRdL01.visible = showRoad;
        this.EquipRdL02.visible = showRoad;
        this.EquipRdL03.visible = showRoad;
        this.EquipRdL04.visible = showRoad;
        this.EquipRdL05.visible = showRoad;
        this.EquipRdL06.visible = showRoad;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.5F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.Ahoke.yRot = 0.45F;
        this.BodyMain.xRot = 0.5F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = -0.85F;
        this.Skirt01.xRot = -0.087F;
        this.Skirt02.xRot = -0.087F;
        this.Skirt03.xRot = -0.052F;

        this.Cloth01a.y = this.cloth01aDefaultY + (0.092F * OFFSET_SCALE);
        this.Cloth01a.z = this.cloth01aDefaultZ + (0.1F * OFFSET_SCALE);
        this.Cloth01c.xRot = -0.79F;
        this.Cloth01c2.xRot = -0.73F;

        this.Hair01.xRot = -0.12F;
        this.Hair01.yRot = 0.0F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -0.33F;
        this.Hair02.yRot = 0.0F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -0.38F;
        this.Hair03.yRot = 0.0F;
        this.Hair03.zRot = 0.0F;

        this.ArmLeft01.xRot = -1.1F;
        this.ArmLeft01.yRot = 0.39F;
        this.ArmLeft01.zRot = -0.05F;
        this.ArmLeft02.xRot = -1.46F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = -1.1F;
        this.ArmRight01.yRot = -0.39F;
        this.ArmRight01.zRot = 0.05F;
        this.ArmRight02.xRot = -1.46F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.xRot = -1.96F;
        this.LegLeft01.yRot = -0.6F;
        this.LegLeft01.zRot = 1.56F;
        this.LegLeft02a.xRot = 2.1F;
        this.LegLeft02a.yRot = 0.0F;
        this.LegLeft02a.zRot = 0.0F;
        this.LegLeft02a.z = this.legLeft02aDefaultZ + (0.37F * OFFSET_SCALE);

        this.LegRight01.xRot = -0.96F;
        this.LegRight01.yRot = 0.36F;
        this.LegRight01.zRot = 0.14F;
        this.LegRight02a.xRot = 1.2217F;
        this.LegRight02a.yRot = -1.2217F;
        this.LegRight02a.zRot = 1.0472F;
        this.LegRight02a.y = this.legRight02aDefaultY + (-0.06F * OFFSET_SCALE);
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = (float) Math.cos(ageInTicks * 0.08F);
        float angleX1 = (float) Math.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = (float) Math.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float angleAdd2 = (float) Math.cos(limbSwing * 0.7F + Math.PI) * limbSwingAmount * 0.5F;

        if (entity.isInWater()) {
            this.poseTranslateY += angleX * 0.05F + 0.025F;
        }

        float addk1 = angleAdd1 - 0.157F;
        float addk2 = angleAdd2 - 0.296F;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = 0.0F;
        float headX = this.Head.xRot * -0.5F;

        this.Ahoke.yRot = angleX * 0.25F + 0.5236F;
        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.35F;

        this.Skirt01.xRot = -0.087F;
        this.Skirt02.xRot = -0.087F;
        this.Skirt03.xRot = -0.052F;

        this.Cloth01a.xRot = angleX * 0.08F + 0.79F;
        this.Cloth01a.y = this.cloth01aDefaultY + (0.092F * OFFSET_SCALE);
        this.Cloth01a.z = this.cloth01aDefaultZ + (0.1F * OFFSET_SCALE);
        this.Cloth01c.xRot = -angleX * 0.12F - 0.9F;
        this.Cloth01c2.xRot = -angleX * 0.12F - 0.85F;

        this.Hair01.xRot = angleX * 0.03F + 0.21F + headX;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -angleX1 * 0.04F + 0.12F + headX;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.26F;
        this.Hair03.zRot = 0.0F;

        this.ArmLeft01.xRot = angleAdd2 * 0.8F - 0.05F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = angleX * 0.025F - 0.3F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.8F + 0.26F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -angleX * 0.025F + 0.3F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.087F;
        this.LegLeft02a.xRot = 0.0F;
        this.LegLeft02a.yRot = 0.0F;
        this.LegLeft02a.zRot = 0.0F;

        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.087F;
        this.LegRight02a.xRot = 0.0F;
        this.LegRight02a.yRot = 0.0F;
        this.LegRight02a.zRot = 0.0F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;

        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.zRot = headZ;
        this.Hair02.zRot = headZ;
        this.Hair03.zRot = headZ;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float headX = this.Head.xRot * -0.5F;

        boolean isSprinting = entity.isSprinting() || limbSwingAmount > 0.9F;
        boolean isCrouching = entity.isCrouching();
        boolean isPassenger = entity.isPassenger();
        boolean isSitting = entity.getIsSitting() || isPassenger;

        if (isSprinting) {
            this.Hair01.xRot = angleAdd1 * 0.1F + limbSwingAmount * 0.4F + headX;
            this.Hair03.xRot += 0.1F;
            this.ArmLeft01.zRot += limbSwingAmount * -0.2F;
            this.ArmRight01.zRot += limbSwingAmount * 0.2F;
        }

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.Skirt01.xRot = -0.35F;
            this.Skirt02.xRot = -0.19F;
            this.Skirt03.xRot = -0.24F;
            this.ArmLeft01.xRot = -0.35F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.35F;
            this.ArmRight01.zRot = -0.2618F;
            this.LegLeft01.xRot -= 1.02F;
            this.LegRight01.xRot -= 1.02F;
            this.Hair01.xRot += 0.37F;
            this.Hair02.xRot += 0.23F;
            this.Hair03.xRot -= 0.1F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY = 0.27F * 3F;
                this.Head.xRot += 0.14F;
                this.BodyMain.xRot = -0.4363F;
                this.Skirt01.xRot = -0.35F;
                this.Skirt02.xRot = -0.19F;
                this.Skirt03.xRot = -0.24F;
                this.ArmLeft01.xRot = -0.3142F;
                this.ArmLeft01.zRot = 0.349F;
                this.ArmLeft02.zRot = 1.15F;
                this.ArmRight01.xRot = -0.4363F;
                this.ArmRight01.zRot = -0.2793F;
                this.ArmRight02.zRot = -1.4F;
                this.LegLeft01.xRot = -1.309F;
                this.LegRight01.xRot = -1.7F;
                this.LegLeft01.yRot = 0.3142F;
                this.LegLeft02a.xRot = 1.0472F;
                this.LegRight01.yRot = -0.35F;
                this.LegRight01.zRot = -0.2618F;
                this.LegRight02a.xRot = 0.9F;
                this.Hair01.xRot += 0.12F;
                this.Hair02.xRot += 0.15F;
                this.Hair03.xRot += 0.25F;
            } else {
                this.poseTranslateY = isPassenger ? RIDING_TRANSLATE_Y : SIT_TRANSLATE_Y;
                this.Head.xRot += 0.1F;
                this.BodyMain.xRot = -0.1F;
                this.Butt.xRot = -0.4F;
                this.Butt.z = this.buttDefaultZ + (0.19F * OFFSET_SCALE);
                this.Ahoke.yRot = 0.5236F;
                this.Skirt01.xRot = -0.35F;
                this.Skirt02.xRot = -0.19F;
                this.Skirt03.xRot = -0.24F;
                this.Hair01.xRot = 0.21F + headX;
                this.Hair02.xRot = -0.28F + headX;
                this.Hair03.xRot = -0.24F;
                this.ArmLeft01.xRot = -1.18F;
                this.ArmLeft01.yRot = 0.27F;
                this.ArmLeft01.zRot = -0.1F;
                this.ArmLeft02.zRot = 0.92F;
                this.ArmRight01.xRot = -1.18F;
                this.ArmRight01.yRot = -0.27F;
                this.ArmRight01.zRot = 0.1F;
                this.ArmRight02.zRot = -1.32F;
                this.LegLeft01.xRot = -2.57F;
                this.LegRight01.xRot = -2.57F;
                this.LegLeft01.y = this.legLeft01DefaultY + (0.25F * OFFSET_SCALE);
                this.LegLeft01.z = this.legLeft01DefaultZ + (-0.2F * OFFSET_SCALE);
                this.LegLeft01.yRot = 0.11F;
                this.LegLeft01.zRot = -0.12F;
                this.LegLeft02a.xRot = 2.75F;
                this.LegLeft02a.zRot = 0.02F;
                this.LegLeft02a.z = this.legLeft02aDefaultZ + (0.37F * OFFSET_SCALE);
                this.LegRight01.y = this.legRight01DefaultY + (0.25F * OFFSET_SCALE);
                this.LegRight01.z = this.legRight01DefaultZ + (-0.2F * OFFSET_SCALE);
                this.LegRight01.yRot = -0.11F;
                this.LegRight01.zRot = 0.12F;
                this.LegRight02a.xRot = 2.75F;
                this.LegRight02a.zRot = -0.02F;
                this.LegRight02a.z = this.legRight02aDefaultZ + (0.37F * OFFSET_SCALE);
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            if (entity.getAttackTick() > 25) {
                this.ArmLeft01.xRot = -1.3F + this.Head.xRot * 0.75F;
                this.ArmLeft01.yRot = -0.2F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft02.xRot = 0.0F;
                this.ArmLeft02.yRot = 0.0F;
                this.ArmLeft02.zRot = 0.0F;
            }
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
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);

        this.LegLeft02b.copyFrom(this.LegLeft02a);
        this.LegRight02b.copyFrom(this.LegRight02a);
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
