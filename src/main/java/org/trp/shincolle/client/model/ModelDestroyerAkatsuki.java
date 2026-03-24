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

public class ModelDestroyerAkatsuki<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_akatsuki"), "main");

    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerAkatsuki");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelDestroyerAkatsuki");
    private static final float OFFSET_SCALE = 16.0F;

    private static final float BODY_BASE_X_ROT = -0.1047F;
    private static final float HEAD_BASE_X_ROT = 0.1047F;
    private static final float BUTT_BASE_X_ROT = 0.21F;
    private static final float SKIRT_BASE_X_ROT = -0.052F;
    private static final float ARM_BASE_X_ROT = -0.1745F;
    private static final float ARM_BASE_Z_ROT = 0.2618F;
    private static final float AHOKE_BASE_Y_ROT = 0.5236F;

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
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart EquipTL03;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart EquipTR03;
    private final ModelPart EquipC12;
    private final ModelPart EquipC13;
    private final ModelPart EquipC14a;
    private final ModelPart EquipC15a;
    private final ModelPart EquipC14b;
    private final ModelPart EquipC15b;
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
    private final ModelPart EquipTL02g;
    private final ModelPart EquipTL02h;
    private final ModelPart EquipTR02a;
    private final ModelPart EquipTR02b;
    private final ModelPart EquipTR02c;
    private final ModelPart EquipTR02d;
    private final ModelPart EquipTR02e;
    private final ModelPart EquipTR02f;
    private final ModelPart EquipTR02g;
    private final ModelPart EquipTR02h;
    private final ModelPart EquipSL01;
    private final ModelPart EquipSL01a;
    private final ModelPart EquipSL01b;
    private final ModelPart EquipSL01c;
    private final ModelPart EquipSL01d;
    private final ModelPart EquipSL01e;
    private final ModelPart EquipSL01f;
    private final ModelPart LightEF01;
    private final ModelPart LightEF01a;
    private final ModelPart LightEF01b;
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
    private final float hair01DefaultXRot;
    private final float hair01DefaultZRot;
    private final float hair02a1DefaultXRot;
    private final float hair02a1DefaultZRot;
    private final float hair02a2DefaultXRot;
    private final float hair02a2DefaultZRot;
    private final float hair02b1DefaultXRot;
    private final float hair02b1DefaultZRot;
    private final float hair02b2DefaultXRot;
    private final float hair02b2DefaultZRot;
    private final float hair02c1DefaultXRot;
    private final float hair02c1DefaultZRot;
    private final float hair02c2DefaultXRot;
    private final float hair02c2DefaultZRot;
    private final float hair02d1DefaultXRot;
    private final float hair02d1DefaultZRot;
    private final float hair02d2DefaultXRot;
    private final float hair02d2DefaultZRot;
    private final float hair02e1DefaultXRot;
    private final float hair02e1DefaultZRot;
    private final float hair02e2DefaultXRot;
    private final float hair02e2DefaultZRot;

    public ModelDestroyerAkatsuki(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02 = this.Cloth01.getChild("Cloth02");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.EquipTR03 = this.ArmRight02.getChild("EquipTR03");
        this.EquipC12 = this.ArmRight02.getChild("EquipC12");
        this.EquipC13 = this.EquipC12.getChild("EquipC13");
        this.EquipC15a = this.EquipC12.getChild("EquipC15a");
        this.EquipC15b = this.EquipC15a.getChild("EquipC15b");
        this.EquipC14a = this.EquipC12.getChild("EquipC14a");
        this.EquipC14b = this.EquipC14a.getChild("EquipC14b");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipC01 = this.EquipBase.getChild("EquipC01");
        this.EquipC02 = this.EquipC01.getChild("EquipC02");
        this.EquipC04a = this.EquipC02.getChild("EquipC04a");
        this.EquipC04b = this.EquipC04a.getChild("EquipC04b");
        this.EquipC03 = this.EquipC02.getChild("EquipC03");
        this.EquipC05a = this.EquipC02.getChild("EquipC05a");
        this.EquipC05b = this.EquipC05a.getChild("EquipC05b");
        this.EquipMain01 = this.EquipBase.getChild("EquipMain01");
        this.EquipMain03 = this.EquipMain01.getChild("EquipMain03");
        this.EquipHead01 = this.EquipMain03.getChild("EquipHead01");
        this.EquipHead02 = this.EquipHead01.getChild("EquipHead02");
        this.EquipHead04 = this.EquipHead02.getChild("EquipHead04");
        this.EquipHead03 = this.EquipHead02.getChild("EquipHead03");
        this.EquipHead05 = this.EquipHead02.getChild("EquipHead05");
        this.EquipTL02 = this.EquipMain01.getChild("EquipTL02");
        this.EquipTL02g = this.EquipTL02.getChild("EquipTL02g");
        this.EquipTL02e = this.EquipTL02.getChild("EquipTL02e");
        this.EquipTL02c = this.EquipTL02.getChild("EquipTL02c");
        this.EquipTL02f = this.EquipTL02.getChild("EquipTL02f");
        this.EquipTL02d = this.EquipTL02.getChild("EquipTL02d");
        this.EquipTL02a = this.EquipTL02.getChild("EquipTL02a");
        this.EquipTL02b = this.EquipTL02.getChild("EquipTL02b");
        this.EquipTL02h = this.EquipTL02.getChild("EquipTL02h");
        this.EquipTR02 = this.EquipMain01.getChild("EquipTR02");
        this.EquipTR02c = this.EquipTR02.getChild("EquipTR02c");
        this.EquipTR02b = this.EquipTR02.getChild("EquipTR02b");
        this.EquipTR02h = this.EquipTR02.getChild("EquipTR02h");
        this.EquipTR02e = this.EquipTR02.getChild("EquipTR02e");
        this.EquipTR02f = this.EquipTR02.getChild("EquipTR02f");
        this.EquipTR02d = this.EquipTR02.getChild("EquipTR02d");
        this.EquipTR02a = this.EquipTR02.getChild("EquipTR02a");
        this.EquipTR02g = this.EquipTR02.getChild("EquipTR02g");
        this.EquipMain02 = this.EquipMain01.getChild("EquipMain02");
        this.EquipMain04 = this.EquipMain01.getChild("EquipMain04");
        this.Head = this.BodyMain.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.HatBase = this.HairMain.getChild("HatBase");
        this.Hat03a = this.HatBase.getChild("Hat03a");
        this.Hat01b = this.HatBase.getChild("Hat01b");
        this.Hat01c = this.HatBase.getChild("Hat01c");
        this.Hat01d = this.HatBase.getChild("Hat01d");
        this.Hat01a = this.HatBase.getChild("Hat01a");
        this.Hat02a = this.HatBase.getChild("Hat02a");
        this.Hat03c = this.HatBase.getChild("Hat03c");
        this.Hat03d = this.HatBase.getChild("Hat03d");
        this.Hat03b = this.HatBase.getChild("Hat03b");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02b1 = this.Hair01.getChild("Hair02b1");
        this.Hair02b2 = this.Hair02b1.getChild("Hair02b2");
        this.Hair02a1 = this.Hair01.getChild("Hair02a1");
        this.Hair02a2 = this.Hair02a1.getChild("Hair02a2");
        this.Hair02e1 = this.Hair01.getChild("Hair02e1");
        this.Hair02e2 = this.Hair02e1.getChild("Hair02e2");
        this.Hair02c1 = this.Hair01.getChild("Hair02c1");
        this.Hair02c2 = this.Hair02c1.getChild("Hair02c2");
        this.Hair02d1 = this.Hair01.getChild("Hair02d1");
        this.Hair02d2 = this.Hair02d1.getChild("Hair02d2");
        this.Hair = this.Head.getChild("Hair");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.EquipTL03 = this.ArmLeft02.getChild("EquipTL03");
        this.EquipSL01 = this.BodyMain.getChild("EquipSL01");
        this.EquipSL01a = this.EquipSL01.getChild("EquipSL01a");
        this.EquipSL01b = this.EquipSL01.getChild("EquipSL01b");
        this.EquipSL01c = this.EquipSL01.getChild("EquipSL01c");
        this.EquipSL01d = this.EquipSL01.getChild("EquipSL01d");
        this.EquipSL01e = this.EquipSL01.getChild("EquipSL01e");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegRight03 = this.LegRight02.getChild("LegRight03");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegLeft03 = this.LegLeft02.getChild("LegLeft03");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.EquipSL01f = this.GlowBodyMain.getChild("EquipSL01f");
        this.LightEF01 = this.EquipSL01f.getChild("LightEF01");
        this.LightEF01a = this.EquipSL01f.getChild("LightEF01a");
        this.LightEF01b = this.EquipSL01f.getChild("LightEF01b");
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
        this.hair01DefaultXRot = this.Hair01.xRot;
        this.hair01DefaultZRot = this.Hair01.zRot;
        this.hair02a1DefaultXRot = this.Hair02a1.xRot;
        this.hair02a1DefaultZRot = this.Hair02a1.zRot;
        this.hair02a2DefaultXRot = this.Hair02a2.xRot;
        this.hair02a2DefaultZRot = this.Hair02a2.zRot;
        this.hair02b1DefaultXRot = this.Hair02b1.xRot;
        this.hair02b1DefaultZRot = this.Hair02b1.zRot;
        this.hair02b2DefaultXRot = this.Hair02b2.xRot;
        this.hair02b2DefaultZRot = this.Hair02b2.zRot;
        this.hair02c1DefaultXRot = this.Hair02c1.xRot;
        this.hair02c1DefaultZRot = this.Hair02c1.zRot;
        this.hair02c2DefaultXRot = this.Hair02c2.xRot;
        this.hair02c2DefaultZRot = this.Hair02c2.zRot;
        this.hair02d1DefaultXRot = this.Hair02d1.xRot;
        this.hair02d1DefaultZRot = this.Hair02d1.zRot;
        this.hair02d2DefaultXRot = this.Hair02d2.xRot;
        this.hair02d2DefaultZRot = this.Hair02d2.zRot;
        this.hair02e1DefaultXRot = this.Hair02e1.xRot;
        this.hair02e1DefaultZRot = this.Hair02e1.zRot;
        this.hair02e2DefaultXRot = this.Hair02e2.xRot;
        this.hair02e2DefaultZRot = this.Hair02e2.zRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 105).addBox(-6.5F, -11F, -4F, 13F, 14F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, 0F, -0.1047F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 31).addBox(-7F, 0F, -4.4F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.6F, 0F));

        PartDefinition Cloth02 = Cloth01.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(24, 73).addBox(-3F, 0F, 0F, 6F, 10F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 4.8F, -4.3F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 88).addBox(-3.5F, -1F, -3F, 6F, 11F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.3F, -9.4F, -0.7F, -0.0524F, 0F, 0.4189F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 88).addBox(0F, 0F, -6F, 6F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(-3.5F, 10F, 3F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(36, 102).addBox(-2.5F, 0F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 6F, -3F));

        PartDefinition EquipTR03 = ArmRight02.addOrReplaceChild("EquipTR03", CubeListBuilder.create().texOffs(36, 45).addBox(-1F, -12F, -3.5F, 1F, 24F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 2F, 3F, 0.3491F, 0.3491F, 0.0873F));

        PartDefinition EquipC12 = ArmRight02.addOrReplaceChild("EquipC12", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3F, -4.5F, 7F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, -3F, 1.5708F, 1.5708F, 0F));

        PartDefinition EquipC13 = EquipC12.addOrReplaceChild("EquipC13", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, 0F, 6F, 2F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, -2F));

        PartDefinition EquipC15a = EquipC12.addOrReplaceChild("EquipC15a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(1.5F, -3F, 0F));

        PartDefinition EquipC15b = EquipC15a.addOrReplaceChild("EquipC15b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition EquipC14a = EquipC12.addOrReplaceChild("EquipC14a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-1.5F, -3F, 0F));

        PartDefinition EquipC14b = EquipC14a.addOrReplaceChild("EquipC14b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 0F));

        PartDefinition EquipC01 = EquipBase.addOrReplaceChild("EquipC01", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 8F, 4F, new CubeDeformation(0F)), PartPose.offset(-7F, -11F, 9F));

        PartDefinition EquipC02 = EquipC01.addOrReplaceChild("EquipC02", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 0.5F, 0F, -0.1745F, 0.6283F, 0F));

        PartDefinition EquipC04a = EquipC02.addOrReplaceChild("EquipC04a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-1.5F, -3F, 0F));

        PartDefinition EquipC04b = EquipC04a.addOrReplaceChild("EquipC04b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition EquipC03 = EquipC02.addOrReplaceChild("EquipC03", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, 0F, 6F, 2F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, -2F));

        PartDefinition EquipC05a = EquipC02.addOrReplaceChild("EquipC05a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(1.5F, -3F, 0F));

        PartDefinition EquipC05b = EquipC05a.addOrReplaceChild("EquipC05b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition EquipMain01 = EquipBase.addOrReplaceChild("EquipMain01", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -1F, 0F, 11F, 9F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -4F, 5F));

        PartDefinition EquipMain03 = EquipMain01.addOrReplaceChild("EquipMain03", CubeListBuilder.create().texOffs(63, 13).addBox(-1F, 0F, -1.5F, 2F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, 9F, 0.5009F, 0F, 0F));

        PartDefinition EquipHead01 = EquipMain03.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1.5F, -12F, 2F, 3F, 18F, new CubeDeformation(0F)), PartPose.offset(0F, 6.5F, -0.5F));

        PartDefinition EquipHead02 = EquipHead01.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -7F, 0F, 3F, 14F, 3F, new CubeDeformation(0F)), PartPose.offset(1F, 0F, -15F));

        PartDefinition EquipHead04 = EquipHead02.addOrReplaceChild("EquipHead04", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.8F, 2.5F, 0.2618F, 0F, 0F));

        PartDefinition EquipHead03 = EquipHead02.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.8F, 2.5F, -0.2618F, 0F, 0F));

        PartDefinition EquipHead05 = EquipHead02.addOrReplaceChild("EquipHead05", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -5F, 0F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipTL02 = EquipMain01.addOrReplaceChild("EquipTL02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -4F, -9F, 3F, 11F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 6F, 4.5F, 0.1396F, -0.0698F, 0F));

        PartDefinition EquipTL02g = EquipTL02.addOrReplaceChild("EquipTL02g", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(1.3F, 4.6F, -19F));

        PartDefinition EquipTL02e = EquipTL02.addOrReplaceChild("EquipTL02e", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, 0F, 2.2F));

        PartDefinition EquipTL02c = EquipTL02.addOrReplaceChild("EquipTL02c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(1.3F, 2.3F, -19.5F));

        PartDefinition EquipTL02f = EquipTL02.addOrReplaceChild("EquipTL02f", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, 2.3F, 2.5F));

        PartDefinition EquipTL02d = EquipTL02.addOrReplaceChild("EquipTL02d", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, -2.3F, 3F));

        PartDefinition EquipTL02a = EquipTL02.addOrReplaceChild("EquipTL02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(1.3F, 0F, -19.8F));

        PartDefinition EquipTL02b = EquipTL02.addOrReplaceChild("EquipTL02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(1.3F, -2.3F, -18.8F));

        PartDefinition EquipTL02h = EquipTL02.addOrReplaceChild("EquipTL02h", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, 4.6F, 2.8F));

        PartDefinition EquipTR02 = EquipMain01.addOrReplaceChild("EquipTR02", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -4F, -9F, 3F, 11F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.5F, 6F, 4.5F, 0.1396F, 0.0698F, 0F));

        PartDefinition EquipTR02c = EquipTR02.addOrReplaceChild("EquipTR02c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 2.3F, -19.5F));

        PartDefinition EquipTR02b = EquipTR02.addOrReplaceChild("EquipTR02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(-1.3F, -2.3F, -18.8F));

        PartDefinition EquipTR02h = EquipTR02.addOrReplaceChild("EquipTR02h", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 4.6F, 2.8F));

        PartDefinition EquipTR02e = EquipTR02.addOrReplaceChild("EquipTR02e", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 0F, 2.2F));

        PartDefinition EquipTR02f = EquipTR02.addOrReplaceChild("EquipTR02f", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 2.3F, 2.5F));

        PartDefinition EquipTR02d = EquipTR02.addOrReplaceChild("EquipTR02d", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, -2.3F, 3F));

        PartDefinition EquipTR02a = EquipTR02.addOrReplaceChild("EquipTR02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 0F, -19.8F));

        PartDefinition EquipTR02g = EquipTR02.addOrReplaceChild("EquipTR02g", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 4.6F, -19F));

        PartDefinition EquipMain02 = EquipMain01.addOrReplaceChild("EquipMain02", CubeListBuilder.create().texOffs(52, 8).addBox(-4F, 0F, 0F, 8F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6.9F, 1.2F, 0.6283F, 0F, 0F));

        PartDefinition EquipMain04 = EquipMain01.addOrReplaceChild("EquipMain04", CubeListBuilder.create().texOffs(0, 26).addBox(-3F, 0F, -3F, 6F, 16F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -16.5F, 9F, -0.0873F, 0F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.8F, -1F, 0.1047F, 0F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -2.8F));

        PartDefinition HatBase = HairMain.addOrReplaceChild("HatBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.6F, 4.5F, -0.0698F, 0.182F, 0F));

        PartDefinition Hat03a = HatBase.addOrReplaceChild("Hat03a", CubeListBuilder.create().texOffs(23, 43).mirror().addBox(0F, -4F, -5F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 2F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Hat01b = HatBase.addOrReplaceChild("Hat01b", CubeListBuilder.create().texOffs(46, 0).addBox(0F, 0F, 0F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.7F, 0F, 0F));

        PartDefinition Hat01c = HatBase.addOrReplaceChild("Hat01c", CubeListBuilder.create().texOffs(46, 0).addBox(-6F, 0F, 0F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(0.7F, 0F, 0F));

        PartDefinition Hat01d = HatBase.addOrReplaceChild("Hat01d", CubeListBuilder.create().texOffs(46, 0).addBox(-6F, 0F, -6F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(0.7F, 0F, 0F));

        PartDefinition Hat01a = HatBase.addOrReplaceChild("Hat01a", CubeListBuilder.create().texOffs(46, 0).addBox(0F, 0F, -6F, 6F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.7F, 0F, 0F));

        PartDefinition Hat02a = HatBase.addOrReplaceChild("Hat02a", CubeListBuilder.create().texOffs(55, 0).addBox(-4.5F, 0F, -6F, 9F, 0F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -6F, 0.0873F, 0F, 0F));

        PartDefinition Hat03c = HatBase.addOrReplaceChild("Hat03c", CubeListBuilder.create().texOffs(23, 43).addBox(0F, -4F, 0F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(-0.5F, 2F, 0F));

        PartDefinition Hat03d = HatBase.addOrReplaceChild("Hat03d", CubeListBuilder.create().texOffs(23, 43).addBox(-5F, -4F, 0F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(0.5F, 2F, 0F));

        PartDefinition Hat03b = HatBase.addOrReplaceChild("Hat03b", CubeListBuilder.create().texOffs(23, 43).addBox(-5F, -4F, -5F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 2F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(38, 23).addBox(-7.5F, 0F, -10F, 15F, 12F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 12F, 0.1396F, 0F, 0F));

        PartDefinition Hair02b1 = Hair01.addOrReplaceChild("Hair02b1", CubeListBuilder.create().texOffs(24, 26).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 7F, -2.4F, 0.2094F, -0.1745F, 0.1745F));

        PartDefinition Hair02b2 = Hair02b1.addOrReplaceChild("Hair02b2", CubeListBuilder.create().texOffs(24, 59).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.1396F, 0F, 0F));

        PartDefinition Hair02a1 = Hair01.addOrReplaceChild("Hair02a1", CubeListBuilder.create().texOffs(24, 26).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, -2.2F, 0.2094F, 0F, 0F));

        PartDefinition Hair02a2 = Hair02a1.addOrReplaceChild("Hair02a2", CubeListBuilder.create().texOffs(24, 32).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.1396F, 0F, 0F));

        PartDefinition Hair02e1 = Hair01.addOrReplaceChild("Hair02e1", CubeListBuilder.create().texOffs(24, 22).addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.4F, 1F, -5.5F, 0.2618F, 0F, -0.4363F));

        PartDefinition Hair02e2 = Hair02e1.addOrReplaceChild("Hair02e2", CubeListBuilder.create().texOffs(24, 62).mirror().addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0F, 0F, 0.2618F));

        PartDefinition Hair02c1 = Hair01.addOrReplaceChild("Hair02c1", CubeListBuilder.create().texOffs(24, 26).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.2F, 6F, -2.4F, 0.1396F, 0.1745F, -0.1745F));

        PartDefinition Hair02c2 = Hair02c1.addOrReplaceChild("Hair02c2", CubeListBuilder.create().texOffs(24, 66).addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.1396F, 0F, 0F));

        PartDefinition Hair02d1 = Hair01.addOrReplaceChild("Hair02d1", CubeListBuilder.create().texOffs(24, 22).addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.4F, 1F, -5.5F, 0.2618F, 0F, 0.3491F));

        PartDefinition Hair02d2 = Hair02d1.addOrReplaceChild("Hair02d2", CubeListBuilder.create().texOffs(24, 62).mirror().addBox(0F, 0F, -2F, 0F, 7F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0F, 0F, -0.1745F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.3F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(89, 102).addBox(-0.5F, 0F, 0F, 1F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 2F, -6.7F, -0.0524F, -0.0873F, 0.1396F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 104).addBox(-0.5F, 0F, 0F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 7.5F, 0F, 0.0873F, 0F, 0.0873F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(89, 102).addBox(-0.5F, 0F, 0F, 1F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 2F, -6.7F, -0.0524F, 0.0873F, -0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 104).addBox(-0.5F, 0F, 0F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 7.5F, 0F, 0.0873F, 0F, -0.0873F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(0, 37).addBox(0F, -11F, -7F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, -4.4F, -5.3F, 1.9199F, 1.0472F, 0F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 45).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6.2F, -7.1F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 88).mirror().addBox(-2.5F, -1F, -3F, 6F, 11F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.3F, -9.4F, -0.7F, 0.1745F, 0F, -0.4189F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 88).mirror().addBox(-6F, 0F, -6F, 6F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(3.5F, 10F, 3F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(36, 102).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 6F, -3F));

        PartDefinition EquipTL03 = ArmLeft02.addOrReplaceChild("EquipTL03", CubeListBuilder.create().texOffs(36, 45).addBox(0F, -12F, -3.5F, 1F, 24F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 4F, -3F, -1.2217F, -0.1047F, -0.0873F));

        PartDefinition EquipSL01 = BodyMain.addOrReplaceChild("EquipSL01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(7F, -14F, 0F));

        PartDefinition EquipSL01a = EquipSL01.addOrReplaceChild("EquipSL01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, -1F));

        PartDefinition EquipSL01b = EquipSL01.addOrReplaceChild("EquipSL01b", CubeListBuilder.create().texOffs(28, 8).addBox(0F, 0F, 0F, 1F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -4F, -2F));

        PartDefinition EquipSL01c = EquipSL01.addOrReplaceChild("EquipSL01c", CubeListBuilder.create().texOffs(6, 15).addBox(0F, 0F, 0F, 1F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(4F, -4F, -2F));

        PartDefinition EquipSL01d = EquipSL01.addOrReplaceChild("EquipSL01d", CubeListBuilder.create().texOffs(12, 3).addBox(0F, 0F, 0F, 3F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(1F, -5F, -2F));

        PartDefinition EquipSL01e = EquipSL01.addOrReplaceChild("EquipSL01e", CubeListBuilder.create().texOffs(29, 5).addBox(0F, 0F, 0F, 3F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(1F, -1F, -2F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(54, 66).addBox(-7F, 0F, 0F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -4F, 0.2094F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 61).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.4F, 5.5F, 3.2F, -0.0524F, 0F, -0.1047F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 72).addBox(-6F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, -3F));

        PartDefinition LegRight03 = LegRight02.addOrReplaceChild("LegRight03", CubeListBuilder.create().texOffs(30, 76).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(-3F, 8F, 2.9F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 61).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.4F, 5.5F, 3.2F, -0.1396F, 0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 72).mirror().addBox(0F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, -3F));

        PartDefinition LegLeft03 = LegLeft02.addOrReplaceChild("LegLeft03", CubeListBuilder.create().texOffs(30, 76).addBox(-3.5F, 0F, -3.5F, 7F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(3F, 8F, 2.9F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(80, 16).addBox(-7.5F, 0F, 0F, 15F, 6F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.7F, -0.4F, -0.0524F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(76, 0).addBox(-8F, 0F, 0F, 16F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, -0.4F, -0.0524F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -9F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -11.8F, -1F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition EquipSL01f = GlowBodyMain.addOrReplaceChild("EquipSL01f", CubeListBuilder.create().texOffs(43, 26).addBox(0F, 0F, 0F, 3F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(8F, -18F, -2F));

        PartDefinition LightEF01 = EquipSL01f.addOrReplaceChild("LightEF01", CubeListBuilder.create().texOffs(47, 27).addBox(0F, 0F, 0F, 16F, 3F, 0F, new CubeDeformation(0F)), PartPose.offset(3F, 0F, -0.1F));

        PartDefinition LightEF01a = EquipSL01f.addOrReplaceChild("LightEF01a", CubeListBuilder.create().texOffs(47, 27).addBox(0F, 0F, 0F, 16F, 3F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -0.1F, 0F, 0F, -1.5708F));

        PartDefinition LightEF01b = EquipSL01f.addOrReplaceChild("LightEF01b", CubeListBuilder.create().texOffs(47, 27).addBox(0F, 0F, 0F, 16F, 3F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 3F, -0.1F, 0F, 0F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.14F);

        this.isDeadPose = false;
        this.poseTranslateY = 0.0F;
        resetOffsets();
        resetHairRotations();

        applyFaceAndMouth(entity);
        setFlushVisible(entity != null && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        applyEquipVisibility(entity);

        if (entity != null && entity.isInDeadPose()) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);
        Head.xRot += HEAD_BASE_X_ROT;

        applyBasePose(entity, ctx, ageInTicks, limbSwing, limbSwingAmount);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks);
        applyHairAnimation(ctx, ageInTicks, limbSwing);

        syncGlowParts();
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

    private void resetHairRotations() {
        Hair01.xRot = hair01DefaultXRot;
        Hair01.zRot = hair01DefaultZRot;
        Hair02a1.xRot = hair02a1DefaultXRot;
        Hair02a1.zRot = hair02a1DefaultZRot;
        Hair02a2.xRot = hair02a2DefaultXRot;
        Hair02a2.zRot = hair02a2DefaultZRot;
        Hair02b1.xRot = hair02b1DefaultXRot;
        Hair02b1.zRot = hair02b1DefaultZRot;
        Hair02b2.xRot = hair02b2DefaultXRot;
        Hair02b2.zRot = hair02b2DefaultZRot;
        Hair02c1.xRot = hair02c1DefaultXRot;
        Hair02c1.zRot = hair02c1DefaultZRot;
        Hair02c2.xRot = hair02c2DefaultXRot;
        Hair02c2.zRot = hair02c2DefaultZRot;
        Hair02d1.xRot = hair02d1DefaultXRot;
        Hair02d1.zRot = hair02d1DefaultZRot;
        Hair02d2.xRot = hair02d2DefaultXRot;
        Hair02d2.zRot = hair02d2DefaultZRot;
        Hair02e1.xRot = hair02e1DefaultXRot;
        Hair02e1.zRot = hair02e1DefaultZRot;
        Hair02e2.xRot = hair02e2DefaultXRot;
        Hair02e2.zRot = hair02e2DefaultZRot;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = 0.0F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        Ahoke.yRot = 0.5236F;
        BodyMain.xRot = 1.55F;
        Butt.xRot = BUTT_BASE_X_ROT;
        Skirt01.xRot = SKIRT_BASE_X_ROT;
        Skirt02.xRot = SKIRT_BASE_X_ROT;

        ArmLeft01.xRot = -3.0F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = 0.3F;
        ArmRight01.xRot = -3.0F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -0.3F;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 0.0F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        LegLeft01.xRot = -0.2618F;
        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.03F;
        LegRight01.xRot = -0.2618F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -0.03F;
        LegLeft02.xRot = 0.0F;
        LegRight02.xRot = 0.0F;

        EquipHead01.yRot = -1.4F;
        EquipHead01.zRot = 1.4F;
        EquipC02.yRot = 0.6F;
        EquipC04a.xRot = 0.0F;
        EquipC05a.xRot = -0.2F;
    }

    private void applyEquipVisibility(EntityShipBase entity) {
        if (entity == null) return;
        boolean showRigging = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerAkatsuki.EQUIP_RIGGING);
        boolean showAnchor = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerAkatsuki.EQUIP_ANCHOR);

        EquipBase.visible = showRigging;
        EquipHead01.visible = showAnchor;
    }

    private void applyBasePose(T entity, PoseContext ctx, float ageInTicks, float limbSwing, float limbSwingAmount) {
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.08f + 0.3f + limbSwing * 0.5f);
        float angleX2 = Mth.cos(ageInTicks * 0.08f + 0.6f + limbSwing * 0.5f);
        float angleAdd1 = Mth.cos(limbSwing * 0.7f) * limbSwingAmount;
        float angleAdd2 = Mth.cos(limbSwing * 0.7f + (float) Math.PI) * limbSwingAmount;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY += angleX * 0.05f + 0.025f;
        }

        this.Ahoke.yRot = angleX * 0.2f + 1.0f;
        this.BodyMain.xRot = -0.1047f;
        this.BodyMain.yRot = 0.0f;
        this.BodyMain.zRot = 0.0f;
        this.Butt.xRot = 0.21f;
        this.Skirt01.xRot = -0.052f;
        this.Skirt02.xRot = -0.052f;
        this.Hair01.xRot = angleX * 0.04f + 0.23f;
        this.Hair01.zRot = 0.0f;
        this.Hair02a1.xRot = -angleX1 * 0.1f + 0.21f;
        this.Hair02a1.zRot = 0.0f;
        this.Hair02b1.xRot = -angleX1 * 0.1f + 0.21f;
        this.Hair02b1.zRot = 0.1745f;
        this.Hair02c1.xRot = -angleX1 * 0.1f + 0.14f;
        this.Hair02c1.zRot = -0.1745f;
        this.Hair02d1.xRot = 0.2618f;
        this.Hair02d1.zRot = -angleX1 * 0.1f + 0.35f;
        this.Hair02e1.xRot = 0.2618f;
        this.Hair02e1.zRot = angleX1 * 0.1f - 0.44f;
        this.Hair02a2.xRot = -angleX2 * 0.13f + 0.14f;
        this.Hair02b2.xRot = -angleX2 * 0.13f + 0.14f;
        this.Hair02c2.xRot = -angleX2 * 0.13f + 0.14f;
        this.Hair02d2.zRot = -angleX2 * 0.13f - 0.17f;
        this.Hair02e2.zRot = angleX2 * 0.13f + 0.26f;
        this.HairL01.xRot = angleX * 0.04f + -0.0524f;
        this.HairL01.zRot = 0.1396f;
        this.HairL02.xRot = -angleX1 * 0.1f + 0.0873f;
        this.HairL02.zRot = 0.0873f;
        this.HairR01.xRot = angleX * 0.04f + -0.0524f;
        this.HairR01.zRot = -0.1396f;
        this.HairR02.xRot = -angleX1 * 0.1f + 0.0873f;
        this.HairR02.zRot = -0.0873f;
        this.ArmLeft01.xRot = angleAdd2 * 0.25f + 0.1745f;
        this.ArmLeft01.yRot = 0.0f;
        this.ArmLeft01.zRot = angleX * 0.03f - 0.42f;
        this.ArmLeft02.xRot = 0.0f;
        this.ArmLeft02.zRot = 0.0f;
        this.ArmRight01.xRot = angleAdd1 * 0.25f - 0.0523f;
        this.ArmRight01.yRot = 0.0f;
        this.ArmRight01.zRot = -angleX * 0.03f + 0.42f;
        this.ArmRight02.xRot = 0.0f;
        this.ArmRight02.zRot = 0.0f;
        this.LegLeft01.yRot = 0.0f;
        this.LegLeft01.zRot = 0.1047f;
        this.LegLeft02.xRot = 0.0f;
        this.LegLeft02.yRot = 0.0f;
        this.LegLeft02.zRot = 0.0f;
        this.LegRight01.yRot = 0.0f;
        this.LegRight01.zRot = -0.1047f;
        this.LegRight02.xRot = 0.0f;
        this.LegRight02.yRot = 0.0f;
        this.LegRight02.zRot = 0.0f;
        this.EquipHead01.zRot = angleX * 0.2f - 1.5708f;
        this.EquipC02.yRot = 0.5f + this.Head.yRot * 0.5f;
        this.EquipC04a.xRot = -0.2f + this.Head.xRot;
        if (this.EquipC04a.xRot > 0.0f) {
            this.EquipC04a.xRot = 0.0f;
        }
        this.EquipC05a.xRot = this.EquipC04a.xRot;
        this.EquipC14a.xRot = this.EquipC04a.xRot;
        this.EquipC15a.xRot = this.EquipC04a.xRot;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks) {
        float legLeftX = ctx.legAddLeft;
        float legRightX = ctx.legAddRight;

        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSprinting = entity != null && entity.isSprinting();
        boolean isLegacyEmote1 = hasLegacyState(entity, 1, 4);
        boolean isLegacyEmote6 = hasLegacyState(entity, 6, 1);
        int ridingState = entity != null ? entity.getRidingState() : 0;

        if (entity != null && !entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerAkatsuki.EQUIP_RIGGING)) {
            ArmLeft01.zRot += 0.1F;
            ArmRight01.zRot -= 0.1F;
        }

        if (isSprinting) {
            this.setFace(EntityShipBase.FACE_TENSION);
            Head.xRot = -0.25F;
            BodyMain.xRot = 0.1F;
            Skirt01.xRot = -0.1F;
            Skirt02.xRot = -0.1885F;
            ArmLeft01.xRot = -3.6F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft01.zRot = 0.87F;
            ArmRight01.xRot = -3.6F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.87F;
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

        if (ridingState > 0) {
            Head.yRot *= 0.5F;
            Head.zRot = 0.0F;
            ArmLeft01.xRot = -0.8F;
            ArmLeft01.yRot = -1.5F;
            ArmLeft01.zRot = 0.0F;
            ArmLeft02.zRot = 1.45F;
            ArmRight01.xRot = -0.8F;
            ArmRight01.yRot = 1.5F;
            ArmRight01.zRot = 0.0F;
            ArmRight02.zRot = -1.45F;
            EquipBase.visible = false;
            EquipTL03.visible = false;
            EquipTR03.visible = false;
            EquipC12.visible = false;

            if (entity != null && entity.getIsSitting()) {
                this.poseTranslateY = 0.525F * 3.0F;
                this.setFace(EntityShipBase.FACE_DOT_EYES);
                Head.xRot = -1.1F;
                Head.yRot = 0.0F;
                Head.zRot = 0.0F;
                BodyMain.xRot = 1.4F;
                Hair01.xRot -= 0.3F;
                legLeftX = -0.1F;
                legRightX = 0.0F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.2F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.2F;
                LegRight02.xRot = 0.3F;

                int tickMod = entity.tickCount % 128;
                if (tickMod < 64) {
                    float armx = Mth.cos(ageInTicks * 0.6F) * -0.5F;
                    this.setFace(EntityShipBase.FACE_TENSION);
                    ArmLeft01.xRot = armx - 3.3F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = 0.7F;
                    ArmLeft02.zRot = 0.0F;
                    ArmRight01.xRot = -armx - 3.3F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.7F;
                    ArmRight02.zRot = 0.0F;
                } else {
                    ArmLeft01.xRot = -2.8F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = 0.7F;
                    ArmLeft02.zRot = 1.0F;
                    ArmRight01.xRot = -2.8F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.7F;
                    ArmRight02.zRot = -1.0F;
                }

                ArmLeft02.x = armLeft02DefaultX;
                ArmLeft02.y = armLeft02DefaultY;
                ArmLeft02.z = armLeft02DefaultZ;
                ArmRight02.x = armRight02DefaultX;
                ArmRight02.y = armRight02DefaultY;
                ArmRight02.z = armRight02DefaultZ;
            }
        } else if (ctx.isSitting || isPassenger) {
            if (isLegacyEmote1) {
                this.poseTranslateY = 0.675F * 2.5F;
                this.setFace(EntityShipBase.FACE_DOT_EYES);
                Head.xRot = -1.1F;
                Head.yRot = 0.0F;
                Head.zRot = 0.0F;
                BodyMain.xRot = 1.4F;
                Hair01.xRot -= 0.3F;
                legLeftX = -0.1F;
                legRightX = 0.0F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.2F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.2F;
                LegRight02.xRot = 0.3F;

                int tickMod = entity != null ? entity.tickCount % 80 : 0;
                if (tickMod < 40) {
                    float armx = Mth.cos(ageInTicks * 0.6F) * -0.5F;
                    this.setFace(EntityShipBase.FACE_TENSION);
                    ArmLeft01.xRot = armx - 3.3F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = 0.7F;
                    ArmRight01.xRot = -armx - 3.3F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.7F;
                    ArmLeft02.zRot = 0.0F;
                    ArmRight02.zRot = 0.0F;
                } else {
                    ArmLeft01.xRot = -2.8F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = 0.7F;
                    ArmRight01.xRot = -2.8F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.7F;
                    ArmLeft02.zRot = 1.0F;
                    ArmRight02.zRot = -1.0F;
                }
                ArmLeft02.x = armLeft02DefaultX;
                ArmRight02.x = armRight02DefaultX;
            } else {
                this.poseTranslateY = 0.375F * 2.8F;
                BodyMain.xRot = -0.25F;
                Butt.xRot = -0.2F;
                Skirt01.xRot = -0.07F;
                Skirt02.xRot = -0.16F;

                ArmLeft01.xRot = 0.35F;
                ArmLeft01.zRot = -0.2618F;
                ArmRight01.xRot = 0.35F;
                ArmRight01.zRot = 0.2618F;

                legLeftX = -0.9F;
                legRightX = -0.9F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = -0.14F;
                LegLeft02.xRot = 1.2217F;
                LegLeft02.yRot = 1.2217F;
                LegLeft02.zRot = -1.0472F;
                LegLeft02.x = legLeft02DefaultX + (0.32F * OFFSET_SCALE);
                LegLeft02.y = legLeft02DefaultY + (0.05F * OFFSET_SCALE);
                LegLeft02.z = legLeft02DefaultZ + (0.35F * OFFSET_SCALE);

                LegRight01.yRot = 0.0F;
                LegRight01.zRot = 0.14F;
                LegRight02.xRot = 1.2217F;
                LegRight02.yRot = -1.2217F;
                LegRight02.zRot = 1.0472F;
                LegRight02.x = legRight02DefaultX + (-0.32F * OFFSET_SCALE);
                LegRight02.y = legRight02DefaultY + (0.05F * OFFSET_SCALE);
                LegRight02.z = legRight02DefaultZ + (0.35F * OFFSET_SCALE);
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            this.setFace(EntityShipBase.FACE_TENSION);
            ArmLeft01.xRot = -1.55F;
            ArmLeft01.yRot = 0.3F;
            ArmLeft01.zRot = 0.0F;
            ArmLeft02.xRot = 0.0F;
            ArmLeft02.zRot = 0.7F;
            ArmRight01.xRot = -1.7F;
            ArmRight01.yRot = -0.1F;
            ArmRight01.zRot = 1.5F;
            ArmRight02.xRot = 0.0F;
            ArmRight02.zRot = 0.0F;
        }

        if (isLegacyEmote6) {
            this.setFace(EntityShipBase.FACE_WINK);
            Head.xRot += 0.6F;
            Head.yRot = 0.0F;
            Head.zRot = 0.0F;
            ArmLeft01.xRot = -2.4F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft01.zRot = 0.5F;
            ArmLeft02.zRot = 0.9F;
            ArmRight01.xRot = -2.4F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.5F;
            ArmRight02.zRot = -0.9F;
            EquipTL03.visible = false;
            EquipTR03.visible = false;
            EquipC12.visible = false;
        }

        LegLeft01.xRot = legLeftX;
        LegRight01.xRot = legRightX;
    }

    private void applyHairAnimation(PoseContext ctx, float ageInTicks, float limbSwing) {
        float angleX1 = Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float headX = Head.xRot * -0.5F;
        float headZ = Head.zRot * -0.5F;

        HairL01.zRot += headZ;
        HairL02.zRot += headZ;
        HairR01.zRot += headZ;
        HairR02.zRot += headZ;
        HairL01.xRot += ctx.angleX * 0.04F + headX;
        HairL02.xRot += -angleX1 * 0.07F + headX;
        HairR01.xRot += ctx.angleX * 0.04F + headX;
        HairR02.xRot += -angleX1 * 0.07F + headX;

        Hair01.xRot += headX;
        Hair01.zRot += headZ;

        if (Hair02a1 != null) {
            Hair02a1.xRot += headX;
            Hair02a1.zRot += headZ;
            Hair02a2.xRot += headX * 0.5F;
            Hair02a2.zRot += headZ * 0.5F;
        }
        if (Hair02b1 != null) {
            Hair02b1.xRot += headX;
            Hair02b1.zRot += headZ;
            Hair02b2.xRot += headX * 0.5F;
            Hair02b2.zRot += headZ * 0.5F;
        }
        if (Hair02c1 != null) {
            Hair02c1.xRot += headX;
            Hair02c1.zRot += headZ;
            Hair02c2.xRot += headX * 0.5F;
            Hair02c2.zRot += headZ * 0.5F;
        }
        if (Hair02d1 != null) {
            Hair02d1.xRot += headX;
            Hair02d1.zRot += headZ;
            Hair02d2.xRot += headX * 0.5F;
            Hair02d2.zRot += headZ * 0.5F;
        }
        if (Hair02e1 != null) {
            Hair02e1.xRot += headX;
            Hair02e1.zRot += headZ;
            Hair02e2.xRot += headX * 0.5F;
            Hair02e2.zRot += headZ * 0.5F;
        }
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
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
