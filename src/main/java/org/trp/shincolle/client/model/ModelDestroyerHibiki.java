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

public class ModelDestroyerHibiki<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_hibiki"), "main");

    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerHibiki");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelDestroyerHibiki");
    private static final float OFFSET_SCALE = 16.0F;

    private boolean isDeadPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Butt;
    private final ModelPart Head;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Cloth01;
    private final ModelPart EquipBase;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight02;
    private final ModelPart LegRight03;
    private final ModelPart LegLeft02;
    private final ModelPart LegLeft03;
    private final ModelPart Skirt02;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke;
    private final ModelPart HairU01;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart HatBase;
    private final ModelPart Hair02f1;
    private final ModelPart Hair02a1;
    private final ModelPart Hair02b1;
    private final ModelPart Hair02c1;
    private final ModelPart Hair02d1;
    private final ModelPart Hair02e1;
    private final ModelPart Hair02a2;
    private final ModelPart Hair02b2;
    private final ModelPart Hair02c2;
    private final ModelPart Hair02d2;
    private final ModelPart Hair02e2;
    private final ModelPart Hat01a;
    private final ModelPart Hat01b;
    private final ModelPart Hat01c;
    private final ModelPart Hat01d;
    private final ModelPart Hat02a;
    private final ModelPart Hat03a;
    private final ModelPart Hat03b;
    private final ModelPart Hat03c;
    private final ModelPart Hat03d;
    private final ModelPart Hat02b;
    private final ModelPart HatBase2;
    private final ModelPart Hat201_01;
    private final ModelPart Hat201_02;
    private final ModelPart Hat201_03;
    private final ModelPart Hat201_04;
    private final ModelPart Hat201_05;
    private final ModelPart Hat201_06;
    private final ModelPart Hat201_07;
    private final ModelPart Hat201_08;
    private final ModelPart Hat201_09;
    private final ModelPart Hat201_10;
    private final ModelPart Hat201_11;
    private final ModelPart Hat201_12;
    private final ModelPart Hat202a;
    private final ModelPart Hat202b;
    private final ModelPart Hair02f2;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart EquipTL03;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart Cloth02;
    private final ModelPart EquipMain01;
    private final ModelPart EquipC01;
    private final ModelPart EquipMain02;
    private final ModelPart EquipMain03;
    private final ModelPart EquipMain04;
    private final ModelPart EquipTL02;
    private final ModelPart EquipTR02;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead04;
    private final ModelPart EquipHead05;
    private final ModelPart EquipTL02a;
    private final ModelPart EquipTL02b;
    private final ModelPart EquipTL02c;
    private final ModelPart EquipTL02d;
    private final ModelPart EquipTL02e;
    private final ModelPart EquipTL02f;
    private final ModelPart EquipTR02a;
    private final ModelPart EquipTR02b;
    private final ModelPart EquipTR02c;
    private final ModelPart EquipTR02d;
    private final ModelPart EquipTR02e;
    private final ModelPart EquipTR02f;
    private final ModelPart EquipC02;
    private final ModelPart EquipC03;
    private final ModelPart EquipC04a;
    private final ModelPart EquipC05a;
    private final ModelPart EquipC04b;
    private final ModelPart EquipC05b;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
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
    private final float hatBase2DefaultY;
    private final float hatBase2DefaultZ;
    private final float hatBase2DefaultXRot;

    public ModelDestroyerHibiki(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02 = this.Cloth01.getChild("Cloth02");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegLeft03 = this.LegLeft02.getChild("LegLeft03");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegRight03 = this.LegRight02.getChild("LegRight03");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.EquipTL03 = this.ArmLeft02.getChild("EquipTL03");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipMain01 = this.EquipBase.getChild("EquipMain01");
        this.EquipMain04 = this.EquipMain01.getChild("EquipMain04");
        this.EquipMain03 = this.EquipMain01.getChild("EquipMain03");
        this.EquipHead01 = this.EquipMain03.getChild("EquipHead01");
        this.EquipHead02 = this.EquipHead01.getChild("EquipHead02");
        this.EquipHead04 = this.EquipHead02.getChild("EquipHead04");
        this.EquipHead05 = this.EquipHead02.getChild("EquipHead05");
        this.EquipHead03 = this.EquipHead02.getChild("EquipHead03");
        this.EquipTR02 = this.EquipMain01.getChild("EquipTR02");
        this.EquipTR02f = this.EquipTR02.getChild("EquipTR02f");
        this.EquipTR02a = this.EquipTR02.getChild("EquipTR02a");
        this.EquipTR02e = this.EquipTR02.getChild("EquipTR02e");
        this.EquipTR02d = this.EquipTR02.getChild("EquipTR02d");
        this.EquipTR02b = this.EquipTR02.getChild("EquipTR02b");
        this.EquipTR02c = this.EquipTR02.getChild("EquipTR02c");
        this.EquipMain02 = this.EquipMain01.getChild("EquipMain02");
        this.EquipTL02 = this.EquipMain01.getChild("EquipTL02");
        this.EquipTL02d = this.EquipTL02.getChild("EquipTL02d");
        this.EquipTL02b = this.EquipTL02.getChild("EquipTL02b");
        this.EquipTL02e = this.EquipTL02.getChild("EquipTL02e");
        this.EquipTL02a = this.EquipTL02.getChild("EquipTL02a");
        this.EquipTL02c = this.EquipTL02.getChild("EquipTL02c");
        this.EquipTL02f = this.EquipTL02.getChild("EquipTL02f");
        this.EquipC01 = this.EquipBase.getChild("EquipC01");
        this.EquipC02 = this.EquipC01.getChild("EquipC02");
        this.EquipC03 = this.EquipC02.getChild("EquipC03");
        this.EquipC05a = this.EquipC02.getChild("EquipC05a");
        this.EquipC05b = this.EquipC05a.getChild("EquipC05b");
        this.EquipC04a = this.EquipC02.getChild("EquipC04a");
        this.EquipC04b = this.EquipC04a.getChild("EquipC04b");
        this.Head = this.BodyMain.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.HatBase = this.HairMain.getChild("HatBase");
        this.Hat03c = this.HatBase.getChild("Hat03c");
        this.Hat01a = this.HatBase.getChild("Hat01a");
        this.Hat01b = this.HatBase.getChild("Hat01b");
        this.Hat01c = this.HatBase.getChild("Hat01c");
        this.Hat03d = this.HatBase.getChild("Hat03d");
        this.Hat02b = this.HatBase.getChild("Hat02b");
        this.Hat01d = this.HatBase.getChild("Hat01d");
        this.Hat03a = this.HatBase.getChild("Hat03a");
        this.Hat03b = this.HatBase.getChild("Hat03b");
        this.Hat02a = this.HatBase.getChild("Hat02a");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02b1 = this.Hair01.getChild("Hair02b1");
        this.Hair02b2 = this.Hair02b1.getChild("Hair02b2");
        this.Hair02a1 = this.Hair01.getChild("Hair02a1");
        this.Hair02a2 = this.Hair02a1.getChild("Hair02a2");
        this.Hair02e1 = this.Hair01.getChild("Hair02e1");
        this.Hair02e2 = this.Hair02e1.getChild("Hair02e2");
        this.Hair02d1 = this.Hair01.getChild("Hair02d1");
        this.Hair02d2 = this.Hair02d1.getChild("Hair02d2");
        this.Hair02c1 = this.Hair01.getChild("Hair02c1");
        this.Hair02c2 = this.Hair02c1.getChild("Hair02c2");
        this.Hair02f1 = this.HairMain.getChild("Hair02f1");
        this.Hair02f2 = this.Hair02f1.getChild("Hair02f2");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.HatBase2 = this.GlowHead.getChild("HatBase2");
        this.Hat201_01 = this.HatBase2.getChild("Hat201_01");
        this.Hat201_02 = this.HatBase2.getChild("Hat201_02");
        this.Hat201_03 = this.HatBase2.getChild("Hat201_03");
        this.Hat201_04 = this.HatBase2.getChild("Hat201_04");
        this.Hat201_05 = this.HatBase2.getChild("Hat201_05");
        this.Hat201_06 = this.HatBase2.getChild("Hat201_06");
        this.Hat201_07 = this.HatBase2.getChild("Hat201_07");
        this.Hat201_08 = this.HatBase2.getChild("Hat201_08");
        this.Hat201_09 = this.HatBase2.getChild("Hat201_09");
        this.Hat201_10 = this.HatBase2.getChild("Hat201_10");
        this.Hat201_11 = this.HatBase2.getChild("Hat201_11");
        this.Hat201_12 = this.HatBase2.getChild("Hat201_12");
        this.Hat202a = this.HatBase2.getChild("Hat202a");
        this.Hat202b = this.HatBase2.getChild("Hat202b");
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
        this.hatBase2DefaultY = this.HatBase2.y;
        this.hatBase2DefaultZ = this.HatBase2.z;
        this.hatBase2DefaultXRot = this.HatBase2.xRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 105).addBox(-6.5F, -11F, -4F, 13F, 14F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, 0F, -0.1047F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 31).addBox(-7F, 0F, -4.4F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.6F, 0F));

        PartDefinition Cloth02 = Cloth01.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(24, 73).addBox(-3F, 0F, 0F, 6F, 10F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 4.8F, -4.3F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(54, 66).addBox(-7F, 0F, 0F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -4F, 0.2094F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 59).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.4F, 5.5F, 3.2F, -0.1396F, 0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 72).mirror().addBox(0F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, -3F));

        PartDefinition LegLeft03 = LegLeft02.addOrReplaceChild("LegLeft03", CubeListBuilder.create().texOffs(30, 76).addBox(-3.5F, 0F, -3.5F, 7F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(3F, 8F, 2.9F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(80, 16).addBox(-7.5F, 0F, 0F, 15F, 6F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.7F, -0.4F, -0.0524F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(76, 0).addBox(-8F, 0F, 0F, 16F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, -0.4F, -0.0524F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 59).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.4F, 5.5F, 3.2F, -0.0524F, 0F, -0.1047F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 72).addBox(-6F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, -3F));

        PartDefinition LegRight03 = LegRight02.addOrReplaceChild("LegRight03", CubeListBuilder.create().texOffs(30, 76).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(-3F, 8F, 2.9F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 88).mirror().addBox(-2.5F, -1F, -3F, 6F, 11F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.3F, -9.4F, -0.7F, 0.1745F, 0F, -0.3142F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 88).mirror().addBox(-6F, 0F, -6F, 6F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(3.5F, 10F, 3F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(36, 102).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 6F, -3F));

        PartDefinition EquipTL03 = ArmLeft02.addOrReplaceChild("EquipTL03", CubeListBuilder.create().texOffs(36, 45).addBox(0F, -12F, -3.5F, 1F, 24F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 4F, -3F, -0.1396F, -0.1047F, -0.0524F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 0F));

        PartDefinition EquipMain01 = EquipBase.addOrReplaceChild("EquipMain01", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -1F, 0F, 11F, 9F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -4F, 5F));

        PartDefinition EquipMain04 = EquipMain01.addOrReplaceChild("EquipMain04", CubeListBuilder.create().texOffs(0, 26).addBox(-3F, 0F, -3F, 6F, 16F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -16.5F, 9F, -0.0873F, 0F, 0F));

        PartDefinition EquipMain03 = EquipMain01.addOrReplaceChild("EquipMain03", CubeListBuilder.create().texOffs(63, 13).addBox(-1F, 0F, -1.5F, 2F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, 9F, 0.5009F, 0F, 0F));

        PartDefinition EquipHead01 = EquipMain03.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1.5F, -12F, 2F, 3F, 18F, new CubeDeformation(0F)), PartPose.offset(0F, 6.5F, -0.5F));

        PartDefinition EquipHead02 = EquipHead01.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -7F, 0F, 3F, 14F, 3F, new CubeDeformation(0F)), PartPose.offset(1F, 0F, -15F));

        PartDefinition EquipHead04 = EquipHead02.addOrReplaceChild("EquipHead04", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.8F, 2.5F, 0.2618F, 0F, 0F));

        PartDefinition EquipHead05 = EquipHead02.addOrReplaceChild("EquipHead05", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -5F, 0F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipHead03 = EquipHead02.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.8F, 2.5F, -0.2618F, 0F, 0F));

        PartDefinition EquipTR02 = EquipMain01.addOrReplaceChild("EquipTR02", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -4F, -9F, 3F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.5F, 6F, 4.5F, 0.1396F, 0.0698F, 0F));

        PartDefinition EquipTR02f = EquipTR02.addOrReplaceChild("EquipTR02f", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 2.3F, 2.5F));

        PartDefinition EquipTR02a = EquipTR02.addOrReplaceChild("EquipTR02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 0F, -19.8F));

        PartDefinition EquipTR02e = EquipTR02.addOrReplaceChild("EquipTR02e", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 0F, 2.2F));

        PartDefinition EquipTR02d = EquipTR02.addOrReplaceChild("EquipTR02d", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, -2.3F, 3F));

        PartDefinition EquipTR02b = EquipTR02.addOrReplaceChild("EquipTR02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(-1.3F, -2.3F, -18.8F));

        PartDefinition EquipTR02c = EquipTR02.addOrReplaceChild("EquipTR02c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 2.3F, -19.5F));

        PartDefinition EquipMain02 = EquipMain01.addOrReplaceChild("EquipMain02", CubeListBuilder.create().texOffs(52, 8).addBox(-4F, 0F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6.9F, 1.2F, 0.6283F, 0F, 0F));

        PartDefinition EquipTL02 = EquipMain01.addOrReplaceChild("EquipTL02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -4F, -9F, 3F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 6F, 4.5F, 0.1396F, -0.0698F, 0F));

        PartDefinition EquipTL02d = EquipTL02.addOrReplaceChild("EquipTL02d", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, -2.3F, 3F));

        PartDefinition EquipTL02b = EquipTL02.addOrReplaceChild("EquipTL02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(1.3F, -2.3F, -18.8F));

        PartDefinition EquipTL02e = EquipTL02.addOrReplaceChild("EquipTL02e", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, 0F, 2.2F));

        PartDefinition EquipTL02a = EquipTL02.addOrReplaceChild("EquipTL02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(1.3F, 0F, -19.8F));

        PartDefinition EquipTL02c = EquipTL02.addOrReplaceChild("EquipTL02c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(1.3F, 2.3F, -19.5F));

        PartDefinition EquipTL02f = EquipTL02.addOrReplaceChild("EquipTL02f", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, 2.3F, 2.5F));

        PartDefinition EquipC01 = EquipBase.addOrReplaceChild("EquipC01", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 8F, 4F, new CubeDeformation(0F)), PartPose.offset(-7F, -11F, 9F));

        PartDefinition EquipC02 = EquipC01.addOrReplaceChild("EquipC02", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 0.5F, 0F, -0.1745F, 0.6283F, 0F));

        PartDefinition EquipC03 = EquipC02.addOrReplaceChild("EquipC03", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, 0F, 6F, 2F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, -2F));

        PartDefinition EquipC05a = EquipC02.addOrReplaceChild("EquipC05a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(1.5F, -3F, 0F));

        PartDefinition EquipC05b = EquipC05a.addOrReplaceChild("EquipC05b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition EquipC04a = EquipC02.addOrReplaceChild("EquipC04a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-1.5F, -3F, 0F));

        PartDefinition EquipC04b = EquipC04a.addOrReplaceChild("EquipC04b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.8F, -1F, 0.1047F, 0F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -2.8F));

        PartDefinition HatBase = HairMain.addOrReplaceChild("HatBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -2.4F, 1.5F, -0.1047F, 0.7854F, 0F));

        PartDefinition Hat03c = HatBase.addOrReplaceChild("Hat03c", CubeListBuilder.create().texOffs(23, 43).addBox(0F, -4F, 0F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(-0.5F, 2F, 0F));

        PartDefinition Hat01a = HatBase.addOrReplaceChild("Hat01a", CubeListBuilder.create().texOffs(46, 0).addBox(0F, 0F, -6F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.7F, 0F, 0F));

        PartDefinition Hat01b = HatBase.addOrReplaceChild("Hat01b", CubeListBuilder.create().texOffs(46, 0).addBox(0F, 0F, 0F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.7F, 0F, 0F));

        PartDefinition Hat01c = HatBase.addOrReplaceChild("Hat01c", CubeListBuilder.create().texOffs(46, 0).addBox(-6F, 0F, 0F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(0.7F, 0F, 0F));

        PartDefinition Hat03d = HatBase.addOrReplaceChild("Hat03d", CubeListBuilder.create().texOffs(23, 43).addBox(-5F, -4F, 0F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(0.5F, 2F, 0F));

        PartDefinition Hat02b = HatBase.addOrReplaceChild("Hat02b", CubeListBuilder.create().texOffs(0, 24).addBox(0F, 0F, 0F, 0F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, -1.7F, -2F, -0.1396F, 0F, 0F));

        PartDefinition Hat01d = HatBase.addOrReplaceChild("Hat01d", CubeListBuilder.create().texOffs(46, 0).addBox(-6F, 0F, -6F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(0.7F, 0F, 0F));

        PartDefinition Hat03a = HatBase.addOrReplaceChild("Hat03a", CubeListBuilder.create().texOffs(23, 43).mirror().addBox(0F, -4F, -5F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 2F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Hat03b = HatBase.addOrReplaceChild("Hat03b", CubeListBuilder.create().texOffs(23, 43).addBox(-5F, -4F, -5F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 2F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Hat02a = HatBase.addOrReplaceChild("Hat02a", CubeListBuilder.create().texOffs(55, 0).addBox(-4.5F, 0F, -6F, 9F, 0F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -6F, 0.1745F, 0F, 0F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(38, 23).addBox(-7.5F, 0F, -10F, 15F, 12F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 12F, 0.2618F, 0F, 0F));

        PartDefinition Hair02b1 = Hair01.addOrReplaceChild("Hair02b1", CubeListBuilder.create().texOffs(24, 26).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 7F, -2.4F, 0.2618F, -0.1745F, 0.2618F));

        PartDefinition Hair02b2 = Hair02b1.addOrReplaceChild("Hair02b2", CubeListBuilder.create().texOffs(24, 66).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, -0.4363F, 0F, 0F));

        PartDefinition Hair02a1 = Hair01.addOrReplaceChild("Hair02a1", CubeListBuilder.create().texOffs(24, 26).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, -2.2F, 0.2618F, 0F, 0F));

        PartDefinition Hair02a2 = Hair02a1.addOrReplaceChild("Hair02a2", CubeListBuilder.create().texOffs(24, 32).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, -0.2618F, 0F, 0F));

        PartDefinition Hair02e1 = Hair01.addOrReplaceChild("Hair02e1", CubeListBuilder.create().texOffs(24, 22).addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.4F, -4F, -5.5F, 0.0524F, 0F, -0.6109F));

        PartDefinition Hair02e2 = Hair02e1.addOrReplaceChild("Hair02e2", CubeListBuilder.create().texOffs(24, 62).mirror().addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0F, 0F, 0.8727F));

        PartDefinition Hair02d1 = Hair01.addOrReplaceChild("Hair02d1", CubeListBuilder.create().texOffs(28, 22).addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.4F, 0F, -5.5F, 0.2618F, 0F, 0.3491F));

        PartDefinition Hair02d2 = Hair02d1.addOrReplaceChild("Hair02d2", CubeListBuilder.create().texOffs(28, 62).mirror().addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0F, 0F, -0.5236F));

        PartDefinition Hair02c1 = Hair01.addOrReplaceChild("Hair02c1", CubeListBuilder.create().texOffs(24, 26).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.2F, 6F, -2.4F, 0.1745F, 0.1745F, -0.4014F));

        PartDefinition Hair02c2 = Hair02c1.addOrReplaceChild("Hair02c2", CubeListBuilder.create().texOffs(24, 66).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, -0.3491F, 0F, 0F));

        PartDefinition Hair02f1 = HairMain.addOrReplaceChild("Hair02f1", CubeListBuilder.create().texOffs(25, 26).addBox(-1.5F, 0F, 0F, 3F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, 1F, 9.5F, 0.7854F, 0.3491F, -0.1396F));

        PartDefinition Hair02f2 = Hair02f1.addOrReplaceChild("Hair02f2", CubeListBuilder.create().texOffs(26, 68).mirror().addBox(-1.5F, 0F, 0F, 3F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 0F, 0.6283F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.3F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 45).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6.2F, -7.1F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(89, 102).addBox(-0.5F, 0F, 0F, 1F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 2.6F, -4.7F, -0.2618F, 0.0873F, -0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 104).addBox(-0.5F, 0F, 0F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 8.5F, 0F, 0.2094F, 0F, -0.0873F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(0, 37).addBox(0F, 0F, -11F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, -7.4F, -7F, -0.5236F, 1.2217F, 0F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(89, 102).addBox(-0.5F, 0F, 0F, 1F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 2.5F, -4.4F, -0.2618F, -0.0873F, 0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 104).addBox(-0.5F, 0F, 0F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 8.5F, 0F, 0.3142F, 0F, 0.0873F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 88).addBox(-3.5F, -1F, -3F, 6F, 11F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.3F, -9.4F, -0.7F, 0.1745F, 0F, 0.3142F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 88).addBox(0F, 0F, -6F, 6F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(-3.5F, 10F, 3F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(36, 102).addBox(-2.5F, 0F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 6F, -3F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -9F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -11.8F, -1F));
        ShipModelBaseAdv.addFaceLayerHibiki(GlowHead);

        PartDefinition HatBase2 = GlowHead.addOrReplaceChild("HatBase2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, 0F, -0.2618F, 0F, 0F));

        PartDefinition Hat201_01 = HatBase2.addOrReplaceChild("Hat201_01", CubeListBuilder.create().texOffs(98, 46).mirror().addBox(0F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -9F));

        PartDefinition Hat201_02 = HatBase2.addOrReplaceChild("Hat201_02", CubeListBuilder.create().texOffs(98, 46).addBox(-9F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -9F));

        PartDefinition Hat201_03 = HatBase2.addOrReplaceChild("Hat201_03", CubeListBuilder.create().texOffs(98, 46).mirror().addBox(0F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 8F));

        PartDefinition Hat201_04 = HatBase2.addOrReplaceChild("Hat201_04", CubeListBuilder.create().texOffs(98, 46).addBox(-9F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 8F));

        PartDefinition Hat201_05 = HatBase2.addOrReplaceChild("Hat201_05", CubeListBuilder.create().texOffs(98, 46).mirror().addBox(0F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 0F, 0F, 0F, 1.5708F, 0F));

        PartDefinition Hat201_06 = HatBase2.addOrReplaceChild("Hat201_06", CubeListBuilder.create().texOffs(98, 46).addBox(0F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 0F, 9F, 0F, 1.5708F, 0F));

        PartDefinition Hat201_07 = HatBase2.addOrReplaceChild("Hat201_07", CubeListBuilder.create().texOffs(98, 46).mirror().addBox(0F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 0F, 0F, 0F, 1.5708F, 0F));

        PartDefinition Hat201_08 = HatBase2.addOrReplaceChild("Hat201_08", CubeListBuilder.create().texOffs(98, 46).addBox(0F, -9F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 0F, 9F, 0F, 1.5708F, 0F));

        PartDefinition Hat201_09 = HatBase2.addOrReplaceChild("Hat201_09", CubeListBuilder.create().texOffs(98, 46).mirror().addBox(0F, 0F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, 0F, -1.5708F, 0F, 0F));

        PartDefinition Hat201_10 = HatBase2.addOrReplaceChild("Hat201_10", CubeListBuilder.create().texOffs(98, 46).addBox(0F, 0F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, -9F, 0F, -1.5708F, 0F, 0F));

        PartDefinition Hat201_11 = HatBase2.addOrReplaceChild("Hat201_11", CubeListBuilder.create().texOffs(98, 46).mirror().addBox(0F, 0F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, 9F, -1.5708F, 0F, 0F));

        PartDefinition Hat201_12 = HatBase2.addOrReplaceChild("Hat201_12", CubeListBuilder.create().texOffs(98, 46).addBox(0F, 0F, 0F, 9F, 9F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, -9F, 9F, -1.5708F, 0F, 0F));

        PartDefinition Hat202a = HatBase2.addOrReplaceChild("Hat202a", CubeListBuilder.create().texOffs(46, 8).addBox(-3F, 0F, -0.5F, 6F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9.5F, -2.5F, 0F, 0F, 1.57F, -0.0873F));

        PartDefinition Hat202b = HatBase2.addOrReplaceChild("Hat202b", CubeListBuilder.create().texOffs(46, 8).addBox(-3F, 0F, -0.5F, 6F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9.5F, -2.5F, 0F, 0F, 1.57F, 0.0873F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.14F);

        this.isDeadPose = false;
        this.poseTranslateY = 0.0F;
        resetOffsets();

        applyFaceAndMouth(entity);
        if (entity != null && entity.getEmotionPrimary() == EntityShipBase.EMOTION_NORMAL) {
            hideMouthParts();
        }
        setFlushVisible(entity != null && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        applyEquipVisibility(entity);

        if (entity != null && entity.isInDeadPose()) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);
        Head.xRot += 0.11F;

        applyBasePose(ctx);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks);
        applyHairAnimation(ctx, ageInTicks, limbSwing);

        syncGlowParts();
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) {
            return;
        }
        EquipBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHibiki.EQUIP_RIGGING);
        EquipTL03.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHibiki.EQUIP_TORPEDO);
        boolean fh1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHibiki.EQUIP_HAIR_FRONT_1);
        boolean fh2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHibiki.EQUIP_HAIR_FRONT_2);
        boolean fh3 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHibiki.EQUIP_HAIR_FRONT_3);

        HatBase2.xRot = hatBase2DefaultXRot;
        HatBase2.y = hatBase2DefaultY;
        HatBase2.z = hatBase2DefaultZ;

        if (fh1) {
            HatBase.visible = true;
            Hair02f1.visible = true;
            Hair01.visible = true;
            HatBase2.visible = false;
        } else if (fh2 && fh3) {
            HatBase.visible = false;
            Hair01.visible = true;
            Hair02f1.visible = true;
            HatBase2.visible = true;
            HatBase2.xRot = -1.35F;
            HatBase2.z = hatBase2DefaultZ + (0.1F * OFFSET_SCALE);
        } else if (fh2) {
            HatBase.visible = false;
            Hair02f1.visible = false;
            Hair01.visible = true;
            HatBase2.visible = true;
            HatBase2.xRot = -0.2618F;
        } else if (fh3) {
            HatBase.visible = false;
            Hair02f1.visible = false;
            Hair01.visible = true;
            HatBase2.visible = true;
            HatBase2.xRot = -0.7F;
            HatBase2.y = hatBase2DefaultY + (-0.06F * OFFSET_SCALE);
            HatBase2.z = hatBase2DefaultZ + (0.06F * OFFSET_SCALE);
        } else {
            HatBase.visible = false;
            Hair02f1.visible = true;
            Hair01.visible = true;
            HatBase2.visible = false;
        }
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = 0.0F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        Ahoke.yRot = 0.5236F;
        BodyMain.xRot = 1.4F;
        Butt.xRot = 0.21F;
        Skirt01.xRot = -0.052F;
        Skirt02.xRot = -0.052F;
        Hair01.xRot = -0.07F;
        Hair01.y = hair01DefaultY + (-0.2F * OFFSET_SCALE);

        ArmLeft01.xRot = -2.8F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = 0.7F;
        ArmRight01.xRot = -2.8F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -0.7F;
        ArmLeft02.zRot = 1.0F;
        ArmRight02.zRot = -1.0F;

        LegLeft01.xRot = 0.1F;
        LegLeft01.yRot = (float) Math.PI;
        LegLeft01.zRot = -0.1F;
        LegRight01.xRot = 0.1F;
        LegRight01.yRot = (float) Math.PI;
        LegRight01.zRot = 0.1F;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;
    }

    private void hideMouthParts() {
        if (Mouth0 != null) {
            Mouth0.visible = false;
        }
        if (Mouth1 != null) {
            Mouth1.visible = false;
        }
        if (Mouth2 != null) {
            Mouth2.visible = false;
        }
    }

    private void applyBasePose(PoseContext ctx) {
        float angleX = ctx.angleX;

        Ahoke.yRot = angleX * 0.2F + 1.2F;
        BodyMain.xRot = -0.1047F;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;
        Butt.xRot = 0.21F;
        Skirt01.xRot = -0.052F;
        Skirt02.xRot = -0.052F;

        ArmLeft01.xRot = 0.1745F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = angleX * 0.03F - 0.3F;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = -0.0523F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -angleX * 0.03F + 0.3F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.1047F;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -0.1047F;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;

        EquipHead01.zRot = angleX * 0.2F - 1.5708F;
        EquipC02.yRot = 0.5F + Head.yRot * 0.5F;
        EquipC04a.xRot = Math.min(0.0F, -0.2F + Head.xRot);
        EquipC05a.xRot = EquipC04a.xRot;
        EquipC04b.xRot = EquipC04a.xRot;
        EquipC05b.xRot = EquipC04a.xRot;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks) {
        float legLeftX = ctx.legAddLeft;
        float legRightX = ctx.legAddRight;

        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSprinting = entity != null && entity.isSprinting();

        if (entity != null && !entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerHibiki.EQUIP_RIGGING)) {
            ArmLeft01.zRot += 0.1F;
            ArmRight01.zRot -= 0.1F;
        }

        if (isSprinting) {
            Head.xRot -= 0.25F;
            BodyMain.xRot = 0.1F;
            Skirt01.xRot = -0.1F;
            Skirt02.xRot = -0.1885F;
            ArmLeft01.xRot = 0.35F;
            ArmLeft01.zRot = -0.5F;
            ArmRight01.xRot = 0.35F;
            ArmRight01.zRot = 0.5F;
            legLeftX -= 0.2F;
            legRightX -= 0.2F;
        }

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            Head.xRot -= 1.0472F;
            BodyMain.xRot = 1.0472F;
            Butt.xRot = -0.4F;
            Butt.y = buttDefaultY + (-0.19F * OFFSET_SCALE);
            Skirt01.xRot = -0.12F;
            Skirt02.xRot = -0.4F;
            Skirt02.y = skirt02DefaultY + (-0.1F * OFFSET_SCALE);
            ArmLeft01.xRot = -0.6F;
            ArmLeft01.zRot = 0.2618F;
            ArmRight01.xRot = -0.6F;
            ArmRight01.zRot = -0.2618F;
            legLeftX -= 0.55F;
            legRightX -= 0.55F;
        }

        if (ctx.isSitting || isPassenger) {
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY = 0.52F * 3.2F;
                Head.xRot = -0.9F;
                Head.yRot = -1.1F;
                Head.zRot = 0.0F;
                BodyMain.xRot = 1.4F;
                Hair01.xRot -= 0.1F;
                Hair01.y = hair01DefaultY + (-0.2F * OFFSET_SCALE);

                legLeftX = -0.1F;
                legRightX = 0.0F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.2F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.2F;
                LegRight02.xRot = 0.3F;

                ArmLeft01.xRot = -2.8F;
                ArmLeft01.zRot = -0.2F;
                ArmRight01.xRot = -2.8F;
                ArmRight01.zRot = -0.7F;
                ArmLeft02.zRot = 0.5F;
                ArmRight02.zRot = -1.0F;
            } else {
                this.poseTranslateY = isPassenger ? 0.375F * 3 : 0.3F * 3;
                Head.xRot -= 0.1F;
                BodyMain.xRot = 0.0F;
                Butt.xRot = -0.2F;
                Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                Skirt01.xRot = -0.07F;
                Skirt01.y = skirt01DefaultY + (-0.05F * OFFSET_SCALE);
                Skirt02.xRot = -0.16F;
                Skirt02.y = skirt02DefaultY + (-0.08F * OFFSET_SCALE);

                ArmLeft01.xRot = -0.4F;
                ArmLeft01.zRot = 0.15F;
                ArmRight01.xRot = -0.4F;
                ArmRight01.zRot = -0.15F;

                legLeftX = -0.65F;
                legRightX = -0.65F;
                LegLeft01.yRot = 0.2F;
                LegLeft02.z = legLeft02DefaultZ + (0.375F * OFFSET_SCALE);
                LegLeft02.xRot = 2.45F;

                LegRight01.yRot = -0.2F;
                LegRight02.z = legRight02DefaultZ + (0.375F * OFFSET_SCALE);
                LegRight02.xRot = 2.45F;
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            ArmLeft01.xRot = -1.55F;
            ArmLeft01.yRot = 0.3F;
            ArmLeft01.zRot = 0.0F;
            ArmLeft02.zRot = 0.7F;
            ArmRight01.xRot = -1.7F;
            ArmRight01.yRot = -0.1F;
            ArmRight01.zRot = 1.5F;
            ArmRight02.zRot = 0.0F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        if (entity != null && hasLegacyState(entity, 6, 1)) {
            Head.xRot += 0.6F;
            ArmLeft01.xRot = -0.44F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft01.zRot = 0.4F;
            ArmLeft02.zRot = 0.0F;
            ArmRight01.xRot = -0.4F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.4F;
            ArmRight02.zRot = 0.0F;
        }

        LegLeft01.xRot = legLeftX;
        LegRight01.xRot = legRightX;
    }

    private void applyHairAnimation(PoseContext ctx, float ageInTicks, float limbSwing) {
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);

        Hair01.xRot = angleX * 0.04F + 0.26F;
        Hair01.zRot = 0.0F;

        if (Hair02a1 != null) {
            Hair02a1.xRot = -angleX1 * 0.1F + 0.26F;
            Hair02a1.zRot = 0.0F;
            Hair02a2.xRot = -angleX2 * 0.13F - 0.26F;
        }
        if (Hair02b1 != null) {
            Hair02b1.xRot = -angleX1 * 0.1F + 0.26F;
            Hair02b1.zRot = 0.26F;
            Hair02b2.xRot = -angleX2 * 0.13F - 0.44F;
        }
        if (Hair02c1 != null) {
            Hair02c1.xRot = -angleX1 * 0.1F + 0.17F;
            Hair02c1.zRot = -0.4F;
            Hair02c2.xRot = -angleX2 * 0.13F - 0.35F;
        }
        if (Hair02d1 != null) {
            Hair02d1.xRot = 0.2618F;
            Hair02d1.zRot = -angleX1 * 0.05F + 0.35F;
            Hair02d2.zRot = -angleX2 * 0.07F - 0.52F;
        }
        if (Hair02e1 != null) {
            Hair02e1.xRot = 0.05F;
            Hair02e1.zRot = angleX1 * 0.05F - 0.6F;
            Hair02e2.zRot = angleX2 * 0.07F + 0.87F;
        }

        HairL01.xRot = angleX * 0.04F - 0.2618F;
        HairL01.zRot = 0.087F;
        HairL02.xRot = -angleX1 * 0.1F + 0.3142F;
        HairL02.zRot = 0.0873F;
        HairR01.xRot = angleX * 0.04F - 0.2618F;
        HairR01.zRot = -0.0873F;
        HairR02.xRot = -angleX1 * 0.1F + 0.21F;
        HairR02.zRot = -0.0873F;

        float headX = Head.xRot * -0.5F;
        float headZ = Head.zRot * -0.5F;

        Hair01.xRot += headX;
        Hair01.zRot += headZ;
        if (Hair02a1 != null) {
            Hair02a1.xRot += headX;
            Hair02a1.zRot += headZ;
        }
        if (Hair02b1 != null) {
            Hair02b1.xRot += headX;
            Hair02b1.zRot += headZ;
        }
        if (Hair02c1 != null) {
            Hair02c1.xRot += headX;
            Hair02c1.zRot += headZ;
        }
        if (Hair02d1 != null) {
            Hair02d1.xRot += headX;
            Hair02d1.zRot += headZ;
        }
        if (Hair02e1 != null) {
            Hair02e1.xRot += headX;
            Hair02e1.zRot += headZ;
        }

        HairL01.zRot += headZ;
        HairL02.zRot += headZ;
        HairR01.zRot += headZ;
        HairR02.zRot += headZ * 2.0F;
        HairL01.xRot += headX;
        HairL02.xRot += headX;
        HairR01.xRot += headX;
        HairR02.xRot += headX;
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
    }

    private void syncGlowParts() {
        GlowBodyMain.copyFrom(BodyMain);
        GlowHead.copyFrom(Head);
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
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
