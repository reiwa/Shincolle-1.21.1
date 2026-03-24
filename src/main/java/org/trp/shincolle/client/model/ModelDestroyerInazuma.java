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

public class ModelDestroyerInazuma<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_inazuma"), "main");

    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerInazuma");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelDestroyerInazuma");
    private static final float OFFSET_SCALE = 16.0F;

    private static final float BODY_BASE_X_ROT = -0.1047F;
    private static final float HEAD_BASE_X_ROT = 0.11F;
    private static final float BUTT_BASE_X_ROT = 0.21F;
    private static final float SKIRT_BASE_X_ROT = -0.052F;
    private static final float ARM_BASE_X_ROT = -0.2793F;
    private static final float ARM_BASE_Z_ROT = 0.2793F;
    private static final float LEG_BASE_Z_ROT = 0.1047F;
    private static final float AHOKE_BASE_Y_ROT = 1.0472F;

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
    private final ModelPart Hair02;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart Cloth02;
    private final ModelPart EquipMain01;
    private final ModelPart EquipC01;
    private final ModelPart EquipMain02;
    private final ModelPart EquipMain03;
    private final ModelPart EquipMain04;
    private final ModelPart EquipTL02;
    private final ModelPart EquipTL02_1;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead04;
    private final ModelPart EquipHead05;
    private final ModelPart EquipTL02a;
    private final ModelPart EquipTL02b;
    private final ModelPart EquipTL02c;
    private final ModelPart EquipTL03;
    private final ModelPart EquipTL02d;
    private final ModelPart EquipTL02e;
    private final ModelPart EquipTL02f;
    private final ModelPart EquipTL02a_1;
    private final ModelPart EquipTL02b_1;
    private final ModelPart EquipTL02c_1;
    private final ModelPart EquipTL03_1;
    private final ModelPart EquipTL02d_1;
    private final ModelPart EquipTL02e_1;
    private final ModelPart EquipTL02f_1;
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

    public ModelDestroyerInazuma(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Head = this.BodyMain.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegRight03 = this.LegRight02.getChild("LegRight03");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegLeft03 = this.LegLeft02.getChild("LegLeft03");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipC01 = this.EquipBase.getChild("EquipC01");
        this.EquipC02 = this.EquipC01.getChild("EquipC02");
        this.EquipC05a = this.EquipC02.getChild("EquipC05a");
        this.EquipC05b = this.EquipC05a.getChild("EquipC05b");
        this.EquipC03 = this.EquipC02.getChild("EquipC03");
        this.EquipC04a = this.EquipC02.getChild("EquipC04a");
        this.EquipC04b = this.EquipC04a.getChild("EquipC04b");
        this.EquipMain01 = this.EquipBase.getChild("EquipMain01");
        this.EquipMain03 = this.EquipMain01.getChild("EquipMain03");
        this.EquipHead01 = this.EquipMain03.getChild("EquipHead01");
        this.EquipHead02 = this.EquipHead01.getChild("EquipHead02");
        this.EquipHead05 = this.EquipHead02.getChild("EquipHead05");
        this.EquipHead04 = this.EquipHead02.getChild("EquipHead04");
        this.EquipHead03 = this.EquipHead02.getChild("EquipHead03");
        this.EquipMain02 = this.EquipMain01.getChild("EquipMain02");
        this.EquipTL02 = this.EquipMain01.getChild("EquipTL02");
        this.EquipTL03 = this.EquipTL02.getChild("EquipTL03");
        this.EquipTL02f = this.EquipTL02.getChild("EquipTL02f");
        this.EquipTL02b = this.EquipTL02.getChild("EquipTL02b");
        this.EquipTL02a = this.EquipTL02.getChild("EquipTL02a");
        this.EquipTL02c = this.EquipTL02.getChild("EquipTL02c");
        this.EquipTL02d = this.EquipTL02.getChild("EquipTL02d");
        this.EquipTL02e = this.EquipTL02.getChild("EquipTL02e");
        this.EquipMain04 = this.EquipMain01.getChild("EquipMain04");
        this.EquipTL02_1 = this.EquipMain01.getChild("EquipTL02_1");
        this.EquipTL02b_1 = this.EquipTL02_1.getChild("EquipTL02b_1");
        this.EquipTL02d_1 = this.EquipTL02_1.getChild("EquipTL02d_1");
        this.EquipTL02c_1 = this.EquipTL02_1.getChild("EquipTL02c_1");
        this.EquipTL02f_1 = this.EquipTL02_1.getChild("EquipTL02f_1");
        this.EquipTL02a_1 = this.EquipTL02_1.getChild("EquipTL02a_1");
        this.EquipTL03_1 = this.EquipTL02_1.getChild("EquipTL03_1");
        this.EquipTL02e_1 = this.EquipTL02_1.getChild("EquipTL02e_1");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02 = this.Cloth01.getChild("Cloth02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.buttDefaultY = this.Butt.y;
        this.skirt01DefaultY = this.Skirt01.y;
        this.skirt02DefaultY = this.Skirt02.y;
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
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 105).addBox(-6.5F, -11F, -4F, 13F, 14F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, 0F, -0.1047F, 0F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.8F, -1F, 0.1047F, 0F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -12.5F, -2.8F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(51, 108).addBox(-3.5F, 0F, 0F, 7F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.7F, 6.2F, 0.1367F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(62, 27).addBox(-3.5F, -9F, 0F, 7F, 14F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.4F, 3.5F, -0.3142F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.3F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(0, 37).addBox(0F, -11F, -7F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -6F, -6F, 1.0472F, 1.0472F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(89, 102).addBox(-0.5F, 0F, 0F, 1F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 2F, -6.7F, -0.0524F, 0.0873F, -0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 104).addBox(-0.5F, 0F, 0F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 7.5F, 0F, 0.0873F, 0F, -0.0873F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 45).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(89, 102).addBox(-0.5F, 0F, 0F, 1F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 2F, -6.7F, -0.0524F, -0.0873F, 0.1396F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 104).addBox(-0.5F, 0F, 0F, 1F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 7.5F, 0F, 0.0873F, 0F, 0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(54, 66).addBox(-7F, 0F, 0F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -4F, 0.2094F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 59).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.4F, 5.5F, 3.2F, -0.0698F, 0F, -0.1047F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 71).addBox(-6F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, -3F));

        PartDefinition LegRight03 = LegRight02.addOrReplaceChild("LegRight03", CubeListBuilder.create().texOffs(30, 76).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(-3F, 8F, 2.9F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(80, 16).addBox(-7.5F, 0F, 0F, 15F, 6F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.7F, -0.4F, -0.0524F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(76, 0).addBox(-8F, 0F, 0F, 16F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, -0.4F, -0.0524F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 59).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.4F, 5.5F, 3.2F, -0.1222F, 0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 71).mirror().addBox(0F, 0F, 0F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, -3F));

        PartDefinition LegLeft03 = LegLeft02.addOrReplaceChild("LegLeft03", CubeListBuilder.create().texOffs(30, 76).addBox(-3.5F, 0F, -3.5F, 7F, 5F, 7F, new CubeDeformation(0F)), PartPose.offset(3F, 8F, 2.9F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 0F));

        PartDefinition EquipC01 = EquipBase.addOrReplaceChild("EquipC01", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 8F, 4F, new CubeDeformation(0F)), PartPose.offset(-7F, -11F, 9F));

        PartDefinition EquipC02 = EquipC01.addOrReplaceChild("EquipC02", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 0.5F, 0F, -0.1745F, 0.6283F, 0F));

        PartDefinition EquipC05a = EquipC02.addOrReplaceChild("EquipC05a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(1.5F, -3F, 0F));

        PartDefinition EquipC05b = EquipC05a.addOrReplaceChild("EquipC05b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition EquipC03 = EquipC02.addOrReplaceChild("EquipC03", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, 0F, 6F, 2F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, -2F));

        PartDefinition EquipC04a = EquipC02.addOrReplaceChild("EquipC04a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -6F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(-1.5F, -3F, 0F));

        PartDefinition EquipC04b = EquipC04a.addOrReplaceChild("EquipC04b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -6F));

        PartDefinition EquipMain01 = EquipBase.addOrReplaceChild("EquipMain01", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -1F, 0F, 11F, 9F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -4F, 5F));

        PartDefinition EquipMain03 = EquipMain01.addOrReplaceChild("EquipMain03", CubeListBuilder.create().texOffs(59, 15).addBox(-1F, 0F, -1.5F, 2F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, 9F, 0.5009F, 0F, 0F));

        PartDefinition EquipHead01 = EquipMain03.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1.5F, -12F, 2F, 3F, 18F, new CubeDeformation(0F)), PartPose.offset(0F, 6.5F, -0.5F));

        PartDefinition EquipHead02 = EquipHead01.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -7F, 0F, 3F, 14F, 3F, new CubeDeformation(0F)), PartPose.offset(1F, 0F, -15F));

        PartDefinition EquipHead05 = EquipHead02.addOrReplaceChild("EquipHead05", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -5F, 0F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipHead04 = EquipHead02.addOrReplaceChild("EquipHead04", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.8F, 2.5F, 0.2618F, 0F, 0F));

        PartDefinition EquipHead03 = EquipHead02.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.8F, 2.5F, -0.2618F, 0F, 0F));

        PartDefinition EquipMain02 = EquipMain01.addOrReplaceChild("EquipMain02", CubeListBuilder.create().texOffs(46, 9).addBox(-4.5F, 0F, 0F, 9F, 7F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.7F, 0.6F, 0.6283F, 0F, 0F));

        PartDefinition EquipTL02 = EquipMain01.addOrReplaceChild("EquipTL02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -4F, -9F, 3F, 8F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 6F, 4.5F, 0.1396F, -0.0698F, 0F));

        PartDefinition EquipTL03 = EquipTL02.addOrReplaceChild("EquipTL03", CubeListBuilder.create().texOffs(36, 45).addBox(0F, 0F, -8F, 1F, 24F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, -12F, 3F, 0F, -0.3491F, -0.0873F));

        PartDefinition EquipTL02f = EquipTL02.addOrReplaceChild("EquipTL02f", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, 2.3F, 3F));

        PartDefinition EquipTL02b = EquipTL02.addOrReplaceChild("EquipTL02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(1.3F, -2.3F, -18.8F));

        PartDefinition EquipTL02a = EquipTL02.addOrReplaceChild("EquipTL02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(1.3F, 0F, -19.8F));

        PartDefinition EquipTL02c = EquipTL02.addOrReplaceChild("EquipTL02c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(1.3F, 2.3F, -18.8F));

        PartDefinition EquipTL02d = EquipTL02.addOrReplaceChild("EquipTL02d", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, -2.3F, 3F));

        PartDefinition EquipTL02e = EquipTL02.addOrReplaceChild("EquipTL02e", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, 0F, 2.5F));

        PartDefinition EquipMain04 = EquipMain01.addOrReplaceChild("EquipMain04", CubeListBuilder.create().texOffs(0, 26).addBox(-3F, 0F, -3F, 6F, 16F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -16.5F, 9F, -0.0873F, 0F, 0F));

        PartDefinition EquipTL02_1 = EquipMain01.addOrReplaceChild("EquipTL02_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -4F, -9F, 3F, 8F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.5F, 6F, 4.5F, 0.1396F, 0.0698F, 0F));

        PartDefinition EquipTL02b_1 = EquipTL02_1.addOrReplaceChild("EquipTL02b_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(-1.3F, -2.3F, -18.8F));

        PartDefinition EquipTL02d_1 = EquipTL02_1.addOrReplaceChild("EquipTL02d_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, -2.3F, 3F));

        PartDefinition EquipTL02c_1 = EquipTL02_1.addOrReplaceChild("EquipTL02c_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 2.3F, -18.8F));

        PartDefinition EquipTL02f_1 = EquipTL02_1.addOrReplaceChild("EquipTL02f_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 2.3F, 3F));

        PartDefinition EquipTL02a_1 = EquipTL02_1.addOrReplaceChild("EquipTL02a_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 0F, -19.8F));

        PartDefinition EquipTL03_1 = EquipTL02_1.addOrReplaceChild("EquipTL03_1", CubeListBuilder.create().texOffs(36, 45).addBox(-1F, 0F, -8F, 1F, 24F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, -12F, 3F, 0F, 0.3491F, 0.0873F));

        PartDefinition EquipTL02e_1 = EquipTL02_1.addOrReplaceChild("EquipTL02e_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, 0F, 2.5F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 31).addBox(-7F, 0F, -4.4F, 14F, 7F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.6F, 0F));

        PartDefinition Cloth02 = Cloth01.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(22, 48).addBox(-3F, 0F, 0F, 6F, 10F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 4.8F, -4.3F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 88).mirror().addBox(-2.5F, -1F, -3F, 6F, 11F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.3F, -9.4F, -0.7F, -0.2793F, 0F, 0.2793F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 88).mirror().addBox(-6F, 0F, -6F, 6F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(3.5F, 10F, 3F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(36, 102).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 6F, -3F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 88).addBox(-3.5F, -1F, -3F, 6F, 11F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.3F, -9.4F, -0.7F, -0.2793F, 0F, -0.2793F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 88).addBox(0F, 0F, -6F, 6F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(-3.5F, 10F, 3F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(36, 102).addBox(-2.5F, 0F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 6F, -3F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -9F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -11.8F, -1F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.1222F);
        resetPoseState();
        resetOffsets();

        applyFaceAndMouth(entity);
        setFlushVisible(entity != null && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        applyEquipVisibility(entity);

        if (isDeadPose(entity)) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);
        Head.xRot += HEAD_BASE_X_ROT;

        applyBasePose(ctx);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks);
        applyHairAnimation(ctx, ageInTicks, limbSwing);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        Butt.y = buttDefaultY;
        Skirt01.y = skirt01DefaultY;
        Skirt02.y = skirt02DefaultY;
        LegLeft02.x = legLeft02DefaultX;
        LegLeft02.y = legLeft02DefaultY;
        LegLeft02.z = legLeft02DefaultZ;
        LegRight02.x = legRight02DefaultX;
        LegRight02.y = legRight02DefaultY;
        LegRight02.z = legRight02DefaultZ;
        ArmLeft02.x = armLeft02DefaultX;
        ArmRight02.x = armRight02DefaultX;
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
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
        ArmLeft02.zRot = 0.0F;
        ArmRight02.zRot = 0.0F;
        LegLeft01.xRot = -0.2618F;
        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.03F;
        LegRight01.xRot = -0.2618F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -0.03F;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;
    }

    private void applyEquipVisibility(EntityShipBase entity) {
        if (entity == null) return;
        boolean showRigging = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerInazuma.EQUIP_RIGGING);
        EquipBase.visible = showRigging;
    }

    private void applyBasePose(PoseContext ctx) {
        float angleX = ctx.angleX;

        BodyMain.xRot = BODY_BASE_X_ROT;
        Butt.xRot = BUTT_BASE_X_ROT;
        Skirt01.xRot = SKIRT_BASE_X_ROT;
        Skirt02.xRot = SKIRT_BASE_X_ROT;

        Ahoke.yRot = AHOKE_BASE_Y_ROT + angleX * 0.2F;

        HairL01.xRot = -0.0524F;
        HairL01.zRot = 0.1396F;
        HairL02.xRot = 0.0873F;
        HairL02.zRot = 0.0873F;
        HairR01.xRot = -0.0524F;
        HairR01.zRot = -0.1396F;
        HairR02.xRot = 0.0873F;
        HairR02.zRot = -0.0873F;

        ArmLeft01.xRot = ARM_BASE_X_ROT;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = ARM_BASE_Z_ROT;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = ARM_BASE_X_ROT;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -ARM_BASE_Z_ROT;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = LEG_BASE_Z_ROT;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -LEG_BASE_Z_ROT;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;

        EquipHead01.zRot = angleX * 0.2F - 1.5708F;
        EquipC02.yRot = 0.5F + Head.yRot * 0.5F;
        EquipC04a.xRot = Math.min(0.0F, -0.2F + Head.xRot);
        EquipC05a.xRot = EquipC04a.xRot;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks) {
        float legLeftX = ctx.angleAdd1 * 0.5F - 0.1222F;
        float legRightX = ctx.angleAdd2 * 0.5F - 0.0698F;
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSprinting = entity != null && entity.isSprinting();

        if (isSprinting) {
            float armz = Mth.cos(ageInTicks * 0.8F) * 0.6F;
            float armx = Mth.sin(ageInTicks * 0.8F) * -0.5F;
            this.setFace(EntityShipBase.FACE_TENSION);
            Head.xRot -= 0.25F;
            BodyMain.xRot = 0.1F;
            Skirt01.xRot = -0.1F;
            Skirt02.xRot = -0.1885F;
            ArmLeft01.xRot = armx;
            ArmLeft01.zRot = -1.9F + armz;
            ArmRight01.xRot = -armx;
            ArmRight01.zRot = 1.9F + armz;
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

        if (entity != null && entity.getRidingState() > 0) {
            if (entity.getRidingState() > 1) {
                Head.yRot *= 0.5F;
                Head.zRot = 0.0F;
                if (hasLegacyState(entity, 1, 4)) {
                    ArmLeft01.xRot = 0.1F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = -0.4F;
                    ArmLeft02.zRot = 0.8F;
                    ArmRight01.xRot = 0.1F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = 0.4F;
                    ArmRight02.zRot = -0.8F;
                } else {
                    ArmLeft01.xRot = -0.8F;
                    ArmLeft01.yRot = -1.5F;
                    ArmLeft01.zRot = 0.0F;
                    ArmLeft02.zRot = 1.45F;
                    ArmRight01.xRot = -0.8F;
                    ArmRight01.yRot = 1.5F;
                    ArmRight01.zRot = 0.0F;
                    ArmRight02.zRot = -1.45F;
                }
                EquipBase.visible = false;
                if (entity.getIsSitting() && entity.getRidingState() != 3) {
                    this.poseTranslateY = 0.275F * 3;
                    Head.xRot -= 0.1F;
                    BodyMain.xRot = 0.0F;
                    Butt.xRot = -0.2F;
                    Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                    Skirt01.xRot = -0.07F;
                    Skirt01.y = skirt01DefaultY + (-0.05F * OFFSET_SCALE);
                    Skirt02.xRot = -0.16F;
                    Skirt02.y = skirt02DefaultY + (-0.08F * OFFSET_SCALE);
                    legLeftX = -0.65F;
                    legRightX = -0.65F;
                    LegLeft01.yRot = 0.1F;
                    LegLeft02.z = legLeft02DefaultZ + (0.375F * OFFSET_SCALE);
                    LegLeft02.xRot = 2.45F;
                    LegLeft02.zRot = 0.0175F;
                    LegRight01.yRot = -0.1F;
                    LegRight02.z = legRight02DefaultZ + (0.375F * OFFSET_SCALE);
                    LegRight02.xRot = 2.45F;
                    LegRight02.zRot = -0.0175F;
                }
            }
            if (entity.getRidingState() == 1 || entity.getRidingState() == 3) {
                Butt.xRot = -0.2F;
                Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                Skirt01.xRot = -0.07F;
                Skirt01.y = skirt01DefaultY + (-0.1F * OFFSET_SCALE);
                Skirt02.xRot = -0.16F;
                Skirt02.y = skirt02DefaultY + (-0.15F * OFFSET_SCALE);
                legLeftX = -0.95F;
                legRightX = -0.95F;
                LegLeft01.yRot = -0.5F;
                LegLeft01.zRot = -0.1F;
                LegLeft02.z = legLeft02DefaultZ;
                LegLeft02.xRot = 0.8F;
                LegLeft02.zRot = 0.0175F;
                LegRight01.yRot = 0.5F;
                LegRight01.zRot = 0.1F;
                LegRight02.z = legRight02DefaultZ;
                LegRight02.xRot = 0.8F;
                LegRight02.zRot = -0.0175F;
            }
        } else if (ctx.isSitting || isPassenger) {
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY = 0.375F * 3.2F;
                Head.yRot -= 0.4F;
                Head.zRot += 0.2F;
                BodyMain.xRot = -0.25F;
                Butt.xRot = -0.2F;
                Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                Skirt01.xRot = -0.07F;
                Skirt01.y = skirt01DefaultY + (-0.1F * OFFSET_SCALE);
                Skirt02.xRot = -0.16F;
                Skirt02.y = skirt02DefaultY + (-0.15F * OFFSET_SCALE);
                ArmLeft01.xRot = 0.35F;
                ArmLeft01.zRot = -0.2618F;
                ArmRight01.xRot = -0.4F;
                ArmRight01.zRot = -0.2356F;
                ArmRight02.zRot = -0.2356F;
                legLeftX = -0.9F;
                legRightX = -0.9F;
                LegLeft01.zRot = -0.14F;
                LegLeft02.xRot = 1.2217F;
                LegLeft02.yRot = 1.2217F;
                LegLeft02.zRot = -1.0472F;
                LegLeft02.x = legLeft02DefaultX + (0.32F * OFFSET_SCALE);
                LegLeft02.y = legLeft02DefaultY + (0.05F * OFFSET_SCALE);
                LegLeft02.z = legLeft02DefaultZ + (0.35F * OFFSET_SCALE);
                LegRight01.zRot = 0.14F;
                LegRight02.xRot = 1.2217F;
                LegRight02.yRot = -1.2217F;
                LegRight02.zRot = 1.0472F;
                LegRight02.x = legRight02DefaultX + (-0.32F * OFFSET_SCALE);
                LegRight02.y = legRight02DefaultY + (0.05F * OFFSET_SCALE);
                LegRight02.z = legRight02DefaultZ + (0.35F * OFFSET_SCALE);
            } else {
                this.poseTranslateY = isPassenger ? 0.375F * 3.2F : 0.275F * 3.2F;
                Head.xRot -= 0.1F;
                BodyMain.xRot = 0.0F;
                Butt.xRot = -0.2F;
                Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                Skirt01.xRot = -0.07F;
                Skirt01.y = skirt01DefaultY + (-0.05F * OFFSET_SCALE);
                Skirt02.xRot = -0.16F;
                Skirt02.y = skirt02DefaultY + (-0.08F * OFFSET_SCALE);
                ArmLeft01.xRot = -0.4F;
                ArmLeft01.zRot = 0.2618F;
                ArmRight01.xRot = -0.4F;
                ArmRight01.zRot = -0.2618F;
                legLeftX = -0.65F;
                legRightX = -0.65F;
                LegLeft01.yRot = 0.1F;
                LegLeft02.z = legLeft02DefaultZ + (0.375F * OFFSET_SCALE);
                LegLeft02.xRot = 2.45F;
                LegLeft02.zRot = 0.0175F;
                LegRight01.yRot = -0.1F;
                LegRight02.z = legRight02DefaultZ + (0.375F * OFFSET_SCALE);
                LegRight02.xRot = 2.45F;
                LegRight02.zRot = -0.0175F;
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            BodyMain.xRot = 0.5F;
            Butt.xRot = -0.3F;
            Butt.y = buttDefaultY + (-0.15F * OFFSET_SCALE);
            ArmLeft01.xRot = -0.8F;
            ArmLeft01.yRot = 0.4712F;
            ArmLeft01.zRot = -0.3142F;
            ArmLeft02.xRot = -0.9425F;
            ArmLeft02.zRot = 0.0F;
            ArmRight01.xRot = -0.8F;
            ArmRight01.yRot = -0.4712F;
            ArmRight01.zRot = 0.3142F;
            ArmRight02.xRot = -0.9425F;
            ArmRight02.zRot = 0.0F;
            legLeftX -= 0.15F;
            legRightX -= 0.15F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
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
        HairR02.zRot += headZ * 2.0F;

        HairL01.xRot += ctx.angleX * 0.04F + headX;
        HairL02.xRot += -angleX1 * 0.07F + headX;
        HairR01.xRot += ctx.angleX * 0.04F + headX;
        HairR02.xRot += -angleX1 * 0.07F + headX;
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
