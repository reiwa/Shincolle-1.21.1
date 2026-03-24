package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.EntityDestroyerAkatsuki;
import org.trp.shincolle.entity.EntityDestroyerIkazuchi;
import org.trp.shincolle.entity.EntityDestroyerInazuma;
import org.trp.shincolle.entity.base.EntityShipBase;

public class ModelDestroyerIkazuchi<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_ikazuchi"), "main");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelDestroyerIkazuchi");
    private static final float SIT_HEAD_YAW_SCALE = 0.25F;
    private static final float SIT_LEG_Y_ROT = 0.2618F;
    private static final float SIT_LEG_X_ROT = -1.66F;
    private static final float SIT_ARM_Z_ROT_DELTA = 0.05F;
    private static final float SIT_LOWER_LEG_X_OFFSET = 0.32F;
    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerIkazuchi");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelDestroyerIkazuchi");
    private static final float RIDING_TRANSLATE_Y = LegacyPoseOffsets.ridingY("ModelDestroyerIkazuchi");
    private static final float BODY_BASE_X_ROT = -0.1047F;
    private static final float HEAD_BASE_X_ROT = 0.1047F;
    private static final float BUTT_BASE_X_ROT = 0.2094F;
    private static final float SKIRT_BASE_X_ROT = -0.0524F;
    private static final float ARM_LEFT_BASE_X_ROT = 0.21F;
    private static final float ARM_RIGHT_BASE_X_ROT = -0.07F;
    private static final float ARM_BASE_Z_ROT = 0.3491F;
    private static final float LEG_BASE_Z_ROT = 0.1047F;
    private static final float LEG_BASE_X_ROT_OFFSET = 0.1745F;
    private static final float AHOKE_BASE_Y_ROT = 1.0472F;
    private final ModelPart BodyMain;
    private final ModelPart ArmRight01;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead05;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead04;
    private final ModelPart EquipBase;
    private final ModelPart EquipC01;
    private final ModelPart EquipC02;
    private final ModelPart EquipC05a;
    private final ModelPart EquipC05b;
    private final ModelPart EquipC04a;
    private final ModelPart EquipC04b;
    private final ModelPart EquipC03;
    private final ModelPart EquipMain01;
    private final ModelPart EquipTL02_1;
    private final ModelPart EquipTL02c_1;
    private final ModelPart EquipTL02a_1;
    private final ModelPart EquipTL02d_1;
    private final ModelPart EquipTL02b_1;
    private final ModelPart EquipTL02f_1;
    private final ModelPart EquipTL03_1;
    private final ModelPart EquipTL02e_1;
    private final ModelPart EquipMain02;
    private final ModelPart EquipMain03;
    private final ModelPart EquipMain04;
    private final ModelPart EquipTL02;
    private final ModelPart EquipTL02c;
    private final ModelPart EquipTL02b;
    private final ModelPart EquipTL03;
    private final ModelPart EquipTL02e;
    private final ModelPart EquipTL02f;
    private final ModelPart EquipTL02d;
    private final ModelPart EquipTL02a;
    private final ModelPart Cloth01;
    private final ModelPart Cloth02;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart Butt;
    private final ModelPart LegRight01;
    private final ModelPart LegRight02;
    private final ModelPart LegRight03;
    private final ModelPart Skirt01;
    private final ModelPart Skirt02;
    private final ModelPart LegLeft01;
    private final ModelPart LegLeft02;
    private final ModelPart LegLeft03;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairU01;
    private final ModelPart HairL01;
    private final ModelPart HairL02;
    private final ModelPart Ahoke;
    private final ModelPart HairR01;
    private final ModelPart HairR02;
    private final ModelPart HairMain;
    private final ModelPart Hair01;
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
    private boolean isDeadPose;
    private float poseTranslateY;
    private boolean isSittingPose;

    public ModelDestroyerIkazuchi(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.EquipHead01 = this.ArmRight03.getChild("EquipHead01");
        this.EquipHead02 = this.EquipHead01.getChild("EquipHead02");
        this.EquipHead05 = this.EquipHead02.getChild("EquipHead05");
        this.EquipHead03 = this.EquipHead02.getChild("EquipHead03");
        this.EquipHead04 = this.EquipHead02.getChild("EquipHead04");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipC01 = this.EquipBase.getChild("EquipC01");
        this.EquipC02 = this.EquipC01.getChild("EquipC02");
        this.EquipC05a = this.EquipC02.getChild("EquipC05a");
        this.EquipC05b = this.EquipC05a.getChild("EquipC05b");
        this.EquipC04a = this.EquipC02.getChild("EquipC04a");
        this.EquipC04b = this.EquipC04a.getChild("EquipC04b");
        this.EquipC03 = this.EquipC02.getChild("EquipC03");
        this.EquipMain01 = this.EquipBase.getChild("EquipMain01");
        this.EquipTL02_1 = this.EquipMain01.getChild("EquipTL02_1");
        this.EquipTL02c_1 = this.EquipTL02_1.getChild("EquipTL02c_1");
        this.EquipTL02a_1 = this.EquipTL02_1.getChild("EquipTL02a_1");
        this.EquipTL02d_1 = this.EquipTL02_1.getChild("EquipTL02d_1");
        this.EquipTL02b_1 = this.EquipTL02_1.getChild("EquipTL02b_1");
        this.EquipTL02f_1 = this.EquipTL02_1.getChild("EquipTL02f_1");
        this.EquipTL03_1 = this.EquipTL02_1.getChild("EquipTL03_1");
        this.EquipTL02e_1 = this.EquipTL02_1.getChild("EquipTL02e_1");
        this.EquipMain02 = this.EquipMain01.getChild("EquipMain02");
        this.EquipMain03 = this.EquipMain01.getChild("EquipMain03");
        this.EquipMain04 = this.EquipMain01.getChild("EquipMain04");
        this.EquipTL02 = this.EquipMain01.getChild("EquipTL02");
        this.EquipTL02c = this.EquipTL02.getChild("EquipTL02c");
        this.EquipTL02b = this.EquipTL02.getChild("EquipTL02b");
        this.EquipTL03 = this.EquipTL02.getChild("EquipTL03");
        this.EquipTL02e = this.EquipTL02.getChild("EquipTL02e");
        this.EquipTL02f = this.EquipTL02.getChild("EquipTL02f");
        this.EquipTL02d = this.EquipTL02.getChild("EquipTL02d");
        this.EquipTL02a = this.EquipTL02.getChild("EquipTL02a");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02 = this.Cloth01.getChild("Cloth02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegRight03 = this.LegRight02.getChild("LegRight03");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegLeft03 = this.LegLeft02.getChild("LegLeft03");
        this.Head = this.BodyMain.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
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

        this.initFaceParts(this.GlowHead);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 105).addBox(-6.5F, -11.0F, -4.0F, 13.0F, 14.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.1047F, 0.0F, 0.0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 88).addBox(-3.5F, -1.0F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.3F, -9.4F, -0.7F, -0.0698F, 0.0F, 0.3491F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 88).addBox(0.0F, 0.0F, -6.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 10.0F, 3.0F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(36, 102).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 6.0F, -3.0F));

        PartDefinition EquipHead01 = ArmRight03.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -12.0F, 2.0F, 3.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.0F, 0.0F, 0.3142F, 0.0F, 0.0F));

        PartDefinition EquipHead02 = EquipHead01.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -7.0F, 0.0F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.5F, -15.0F));

        PartDefinition EquipHead05 = EquipHead02.addOrReplaceChild("EquipHead05", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition EquipHead03 = EquipHead02.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.8F, 2.5F, -0.2618F, 0.0F, 0.0F));

        PartDefinition EquipHead04 = EquipHead02.addOrReplaceChild("EquipHead04", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.8F, 2.5F, 0.2618F, 0.0F, 0.0F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition EquipC01 = EquipBase.addOrReplaceChild("EquipC01", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -11.0F, 9.0F));

        PartDefinition EquipC02 = EquipC01.addOrReplaceChild("EquipC02", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.5F, 0.0F, -0.1745F, 0.6283F, 0.0F));

        PartDefinition EquipC05a = EquipC02.addOrReplaceChild("EquipC05a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -3.0F, 0.0F));

        PartDefinition EquipC05b = EquipC05a.addOrReplaceChild("EquipC05b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

        PartDefinition EquipC04a = EquipC02.addOrReplaceChild("EquipC04a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -3.0F, 0.0F));

        PartDefinition EquipC04b = EquipC04a.addOrReplaceChild("EquipC04b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -10.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

        PartDefinition EquipC03 = EquipC02.addOrReplaceChild("EquipC03", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -2.0F));

        PartDefinition EquipMain01 = EquipBase.addOrReplaceChild("EquipMain01", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -1.0F, 0.0F, 11.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 5.0F));

        PartDefinition EquipTL02_1 = EquipMain01.addOrReplaceChild("EquipTL02_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -9.0F, 3.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 6.0F, 4.5F, 0.1396F, 0.0698F, 0.0F));

        PartDefinition EquipTL02c_1 = EquipTL02_1.addOrReplaceChild("EquipTL02c_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, 2.3F, -18.8F));

        PartDefinition EquipTL02a_1 = EquipTL02_1.addOrReplaceChild("EquipTL02a_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, 0.0F, -19.8F));

        PartDefinition EquipTL02d_1 = EquipTL02_1.addOrReplaceChild("EquipTL02d_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, -2.3F, 3.0F));

        PartDefinition EquipTL02b_1 = EquipTL02_1.addOrReplaceChild("EquipTL02b_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, -2.3F, -18.8F));

        PartDefinition EquipTL02f_1 = EquipTL02_1.addOrReplaceChild("EquipTL02f_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, 2.3F, 3.0F));

        PartDefinition EquipTL03_1 = EquipTL02_1.addOrReplaceChild("EquipTL03_1", CubeListBuilder.create().texOffs(36, 45).addBox(-1.0F, 0.0F, -8.0F, 1.0F, 24.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -12.0F, 3.0F, 0.0F, 0.3491F, 0.0873F));

        PartDefinition EquipTL02e_1 = EquipTL02_1.addOrReplaceChild("EquipTL02e_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, 0.0F, 2.5F));

        PartDefinition EquipMain02 = EquipMain01.addOrReplaceChild("EquipMain02", CubeListBuilder.create().texOffs(46, 9).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.7F, 0.6F, 0.6283F, 0.0F, 0.0F));

        PartDefinition EquipMain03 = EquipMain01.addOrReplaceChild("EquipMain03", CubeListBuilder.create().texOffs(59, 15).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.5F, 9.0F, 0.5009F, 0.0F, 0.0F));

        PartDefinition EquipMain04 = EquipMain01.addOrReplaceChild("EquipMain04", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.5F, 9.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition EquipTL02 = EquipMain01.addOrReplaceChild("EquipTL02", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -4.0F, -9.0F, 3.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 6.0F, 4.5F, 0.1396F, -0.0698F, 0.0F));

        PartDefinition EquipTL02c = EquipTL02.addOrReplaceChild("EquipTL02c", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(1.3F, 2.3F, -18.8F));

        PartDefinition EquipTL02b = EquipTL02.addOrReplaceChild("EquipTL02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(1.3F, -2.3F, -18.8F));

        PartDefinition EquipTL03 = EquipTL02.addOrReplaceChild("EquipTL03", CubeListBuilder.create().texOffs(36, 45).addBox(0.0F, 0.0F, -8.0F, 1.0F, 24.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -12.0F, 3.0F, 0.0F, -0.3491F, -0.0873F));

        PartDefinition EquipTL02e = EquipTL02.addOrReplaceChild("EquipTL02e", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.3F, 0.0F, 2.5F));

        PartDefinition EquipTL02f = EquipTL02.addOrReplaceChild("EquipTL02f", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.3F, 2.3F, 3.0F));

        PartDefinition EquipTL02d = EquipTL02.addOrReplaceChild("EquipTL02d", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.3F, -2.3F, 3.0F));

        PartDefinition EquipTL02a = EquipTL02.addOrReplaceChild("EquipTL02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(1.3F, 0.0F, -19.8F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 31).addBox(-7.0F, 0.0F, -4.4F, 14.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.6F, 0.0F));

        PartDefinition Cloth02 = Cloth01.addOrReplaceChild("Cloth02", CubeListBuilder.create().texOffs(22, 48).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.8F, -4.3F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 88).addBox(-2.5F, -1.0F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.3F, -9.4F, -0.7F, 0.2094F, 0.0F, -0.3491F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 88).addBox(-6.0F, 0.0F, -6.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 10.0F, 3.0F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(36, 102).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 6.0F, -3.0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(54, 66).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -4.0F, 0.2094F, 0.0F, 0.0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 59).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.4F, 5.5F, 3.2F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 72).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 12.0F, -3.0F));

        PartDefinition LegRight03 = LegRight02.addOrReplaceChild("LegRight03", CubeListBuilder.create().texOffs(30, 76).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 8.0F, 2.9F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(80, 16).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.7F, -0.4F, -0.0524F, 0.0F, 0.0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(76, 0).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -0.4F, -0.0524F, 0.0F, 0.0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 59).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, 5.5F, 3.2F, -0.1396F, 0.0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 72).addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 12.0F, -3.0F));

        PartDefinition LegLeft03 = LegLeft02.addOrReplaceChild("LegLeft03", CubeListBuilder.create().texOffs(30, 76).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 8.0F, 2.9F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7.0F, -14.5F, -6.5F, 14.0F, 14.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.8F, -1.0F, 0.1047F, 0.0F, 0.0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8.0F, -8.0F, -7.4F, 16.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.5F, 0.3F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 45).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -7.0F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(88, 101).addBox(0.0F, 0.0F, 0.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -1.0F, -4.7F, 0.576F, 0.2618F, -0.2618F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 103).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 9.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, -11.0F, -7.0F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.0F, -6.0F, 1.0472F, 1.0472F, 0.0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(88, 101).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.0F, -4.7F, 0.576F, -0.2618F, 0.2618F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 103).addBox(0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 9.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.8F, -3.0F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(36, 26).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.8F, 1.1F, 0.2094F, 0.0F, 0.0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create(), PartPose.offset(0.0F, -11.8F, -1.0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, LEG_BASE_X_ROT_OFFSET);
        resetPoseState();
        resetOffsets();

        applyFaceAndMouth(entity);
        setFlushVisible(entity != null
                && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY
                || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        applyEquipVisibility(entity);
        if (isDeadPose(entity)) {
            applyDeadPose();
            syncGlowParts();
            return;
        }
        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);
        Head.xRot += HEAD_BASE_X_ROT;

        applyBasePose(ctx);
        applyEquipAnimation(entity, ctx, limbSwingAmount);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
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
        ArmLeft02.y = armLeft02DefaultY;
        ArmLeft02.z = armLeft02DefaultZ;
        ArmRight02.x = armRight02DefaultX;
        ArmRight02.y = armRight02DefaultY;
        ArmRight02.z = armRight02DefaultZ;
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
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;
        EquipHead01.yRot = -1.4F;
        EquipHead01.zRot = 1.4F;
        EquipC02.yRot = 0.6F;
        EquipC04a.xRot = 0.0F;
        EquipC05a.xRot = -0.2F;
    }

    private void applyEquipVisibility(EntityShipBase entity) {
        if (entity == null) {
            return;
        }
        boolean showRigging = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerIkazuchi.EQUIP_RIGGING);
        boolean showAnchor = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerIkazuchi.EQUIP_ANCHOR);

        EquipBase.visible = showRigging;
        EquipHead01.visible = showAnchor;
    }

    private void applyBasePose(PoseContext ctx) {
        float angleX = ctx.angleX;

        BodyMain.xRot = BODY_BASE_X_ROT;
        Butt.xRot = BUTT_BASE_X_ROT;
        Skirt01.xRot = SKIRT_BASE_X_ROT;
        Skirt02.xRot = SKIRT_BASE_X_ROT;

        Ahoke.yRot = AHOKE_BASE_Y_ROT + angleX * 0.2F;

        ArmLeft01.xRot = ctx.angleAdd2 * 0.25F + ARM_LEFT_BASE_X_ROT;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = angleX * 0.03F - ARM_BASE_Z_ROT;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = ctx.angleAdd1 * 0.25F + ARM_RIGHT_BASE_X_ROT;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -angleX * 0.03F + ARM_BASE_Z_ROT;
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
    }

    private void applyEquipAnimation(T entity, PoseContext ctx, float limbSwingAmount) {
        EquipHead01.yRot = 0.0F;
        EquipHead01.zRot = 0.0F;
        EquipC02.yRot = 0.5F + Head.yRot * 0.5F;
        EquipC04a.xRot = -0.2F + Head.xRot;
        if (EquipC04a.xRot > 0.0F) {
            EquipC04a.xRot = 0.0F;
        }
        EquipC05a.xRot = EquipC04a.xRot;

        boolean showRigging = entity != null && entity.getEquipFlag(EntityDestroyerIkazuchi.EQUIP_RIGGING);
        if (!showRigging) {
            ArmLeft01.zRot += 0.1F;
            ArmRight01.zRot -= 0.1F;
        }

        if (entity != null && (entity.isSprinting() || limbSwingAmount > 0.9F)) {
            this.setFace(EntityShipBase.FACE_TENSION);
            Head.xRot -= 0.25F;
            BodyMain.xRot = 0.1F;
            Skirt01.xRot = -0.1F;
            Skirt02.xRot = -0.1885F;
            ArmLeft01.xRot += 0.1F;
            ArmLeft01.zRot -= 0.3F;
            ArmRight01.xRot = -2.2F + ctx.angleAdd1 * 0.2F;
            ArmRight01.zRot = -0.4712F;
            EquipHead01.yRot = -0.3142F;
        }
    }

    private void applySittingOrLegPose(PoseContext ctx) {
        this.isSittingPose = ctx.isSitting;
        if (ctx.isSitting) {
            applySittingPose(Head, ArmLeft01, ArmRight01, LegLeft01, LegRight01,
                    SIT_HEAD_YAW_SCALE, SIT_ARM_Z_ROT_DELTA, SIT_LEG_Y_ROT, SIT_LEG_X_ROT);
            return;
        }

        float legAddLeft = ctx.angleAdd1 * 0.5F - 0.14F;
        float legAddRight = ctx.angleAdd2 * 0.5F - 0.03F;
        LegLeft01.xRot = legAddLeft;
        LegRight01.xRot = legAddRight;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks) {
        float legLeftX = ctx.angleAdd1 * 0.5F - 0.14F;
        float legRightX = ctx.angleAdd2 * 0.5F - 0.03F;
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean useAltSit = entity != null && entity.getEmotionSecondary() == EntityShipBase.EMOTION_BORED;

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
            this.isSittingPose = ctx.isSitting;
            this.poseTranslateY = ctx.isSitting && !isPassenger ? SITTING_TRANSLATE_Y : RIDING_TRANSLATE_Y;
            Entity mount = entity != null ? entity.getVehicle() : null;
            boolean ridingInazumaOrAkatsuki = mount instanceof EntityDestroyerInazuma || mount instanceof EntityDestroyerAkatsuki;
            boolean mountScorn = mount instanceof EntityShipBase shipMount && shipMount.getStateEmotion(1) == 4;
            if (ridingInazumaOrAkatsuki) {
                if (mountScorn) {
                    BodyMain.xRot = -0.1F;
                    Butt.xRot = -0.2F;
                    Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                    Skirt01.xRot = -0.07F;
                    Skirt01.y = skirt01DefaultY + (-0.05F * OFFSET_SCALE);
                    Skirt02.xRot = -0.16F;
                    Skirt02.y = skirt02DefaultY + (-0.08F * OFFSET_SCALE);
                    ArmLeft01.xRot = -0.5F;
                    ArmLeft01.yRot = -0.2F;
                    ArmLeft01.zRot = 0.0F;
                    ArmLeft02.xRot = -1.45F;
                    ArmLeft02.zRot = 0.0F;
                    ArmRight01.xRot = -0.5F;
                    ArmRight01.yRot = 0.2F;
                    ArmRight01.zRot = 0.0F;
                    ArmRight02.xRot = -1.45F;
                    ArmRight02.zRot = 0.0F;
                    legLeftX = -0.65F;
                    legRightX = -0.65F;
                    LegLeft01.yRot = 0.0F;
                    LegLeft01.zRot = -0.25F;
                    LegLeft02.z = legLeft02DefaultZ;
                    LegLeft02.xRot = 0.8F;
                    LegLeft02.zRot = 0.0175F;
                    LegRight01.yRot = 0.0F;
                    LegRight01.zRot = 0.25F;
                    LegRight02.z = legRight02DefaultZ;
                    LegRight02.xRot = 0.8F;
                    LegRight02.zRot = -0.0175F;
                    EquipHead01.visible = false;
                } else {
                    Butt.xRot = -0.2F;
                    Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                    Skirt01.xRot = -0.07F;
                    Skirt01.y = skirt01DefaultY + (-0.1F * OFFSET_SCALE);
                    Skirt02.xRot = -0.16F;
                    Skirt02.y = skirt02DefaultY + (-0.15F * OFFSET_SCALE);
                    ArmLeft01.xRot = -0.3F;
                    ArmLeft01.yRot = -0.2F;
                    ArmLeft01.zRot = 0.0F;
                    ArmLeft02.xRot = -1.2F;
                    ArmLeft02.zRot = 0.0F;
                    ArmRight01.xRot = -1.8F;
                    ArmRight01.yRot = 0.2F;
                    ArmRight01.zRot = 0.0F;
                    ArmRight02.xRot = 0.0F;
                    ArmRight02.zRot = 0.0F;
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
            } else if (useAltSit) {
                float parTick = ageInTicks - (int) ageInTicks + (entity != null ? (entity.tickCount % 256) : 0);
                Head.xRot -= 0.1F;
                BodyMain.xRot = -0.25F;
                Butt.xRot = -0.2F;
                Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                Skirt01.xRot = -0.07F;
                Skirt01.y = skirt01DefaultY + (-0.1F * OFFSET_SCALE);
                Skirt02.xRot = -0.16F;
                Skirt02.y = skirt02DefaultY + (-0.15F * OFFSET_SCALE);
                ArmLeft01.xRot = 2.5F;
                ArmLeft01.zRot = 0.1F;
                ArmLeft02.zRot = 1.0F;
                ArmRight01.xRot = 2.5F;
                ArmRight01.zRot = -0.1F;
                ArmRight02.zRot = -1.0F;
                legLeftX = -0.9F;
                legRightX = -0.9F;
                LegLeft01.zRot = -0.14F;
                LegLeft02.xRot = 1.2217F;
                LegLeft02.yRot = 1.2217F;
                LegLeft02.zRot = -1.0472F;
                LegLeft02.x = legLeft02DefaultX + (SIT_LOWER_LEG_X_OFFSET * OFFSET_SCALE);
                LegLeft02.y = legLeft02DefaultY + (0.05F * OFFSET_SCALE);
                LegLeft02.z = legLeft02DefaultZ + (0.35F * OFFSET_SCALE);
                LegRight01.zRot = 0.14F;
                LegRight02.xRot = 1.2217F;
                LegRight02.yRot = -1.2217F;
                LegRight02.zRot = 1.0472F;
                LegRight02.x = legRight02DefaultX + (-SIT_LOWER_LEG_X_OFFSET * OFFSET_SCALE);
                LegRight02.y = legRight02DefaultY + (0.05F * OFFSET_SCALE);
                LegRight02.z = legRight02DefaultZ + (0.35F * OFFSET_SCALE);
                EquipHead01.visible = false;
                if (parTick < 30.0F) {
                    float az = Mth.sin(parTick * 0.033F * 1.5708F) * 1.8F;
                    float az1 = az * 1.6F;
                    ArmLeft01.zRot = 0.1F + az;
                    ArmLeft02.zRot = 1.0F - az1;
                    if (ArmLeft02.zRot < 0.0F) {
                        ArmLeft02.zRot = 0.0F;
                    }
                    ArmRight01.zRot = -0.1F - az;
                    ArmRight02.zRot = -1.0F + az1;
                    if (ArmRight02.zRot > 0.0F) {
                        ArmRight02.zRot = 0.0F;
                    }
                } else if (parTick < 45.0F) {
                    ArmLeft01.zRot = 1.9F;
                    ArmLeft02.zRot = 0.0F;
                    ArmRight01.zRot = -1.9F;
                    ArmRight02.zRot = 0.0F;
                } else if (parTick < 53.0F) {
                    float az = Mth.cos((parTick - 45.0F) * 0.125F * 1.5708F);
                    float az1 = az * 1.8F;
                    ArmLeft01.zRot = 0.1F + az1;
                    ArmLeft02.zRot = 1.0F - az;
                    ArmRight01.zRot = -0.1F - az1;
                    ArmRight02.zRot = -1.0F + az;
                }
            } else {
                Head.xRot -= 0.1F;
                BodyMain.xRot = -0.25F;
                Butt.xRot = -0.2F;
                Butt.y = buttDefaultY + (-0.1F * OFFSET_SCALE);
                Skirt01.xRot = -0.07F;
                Skirt01.y = skirt01DefaultY + (-0.1F * OFFSET_SCALE);
                Skirt02.xRot = -0.16F;
                Skirt02.y = skirt02DefaultY + (-0.15F * OFFSET_SCALE);
                ArmLeft01.xRot = 0.3F;
                ArmLeft01.zRot = -0.2618F;
                ArmRight01.xRot = 0.3F;
                ArmRight01.zRot = 0.2618F;
                legLeftX = -0.9F;
                legRightX = -0.9F;
                LegLeft01.zRot = -0.14F;
                LegLeft02.xRot = 1.2217F;
                LegLeft02.yRot = 1.2217F;
                LegLeft02.zRot = -1.0472F;
                LegLeft02.x = legLeft02DefaultX + (SIT_LOWER_LEG_X_OFFSET * OFFSET_SCALE);
                LegLeft02.y = legLeft02DefaultY + (0.05F * OFFSET_SCALE);
                LegLeft02.z = legLeft02DefaultZ + (0.35F * OFFSET_SCALE);
                LegRight01.zRot = 0.14F;
                LegRight02.xRot = 1.2217F;
                LegRight02.yRot = -1.2217F;
                LegRight02.zRot = 1.0472F;
                LegRight02.x = legRight02DefaultX + (-SIT_LOWER_LEG_X_OFFSET * OFFSET_SCALE);
                LegRight02.y = legRight02DefaultY + (0.05F * OFFSET_SCALE);
                LegRight02.z = legRight02DefaultZ + (0.35F * OFFSET_SCALE);
                EquipHead01.zRot = 1.2F;
            }
        }

        float attackAnim = entity != null ? entity.getAttackAnim(ageInTicks) : 0.0F;
        float attackTicks = attackAnim * 50.0F;
        if (attackTicks > 20.0F && !isPassenger) {
            Head.xRot -= 0.1F;
            EquipHead01.yRot = -0.3142F;
            if (entity != null && (entity.tickCount % 128) < 64) {
                ArmLeft01.xRot = 0.2356F;
                ArmLeft01.zRot = -0.7854F;
                ArmLeft02.zRot = 1.5708F;
                ArmLeft02.x = armLeft02DefaultX + (-0.15F * OFFSET_SCALE);
                ArmRight01.xRot = -1.6F + ctx.angleAdd1 * 0.2F;
                ArmRight01.zRot = -0.4F;
            } else {
                ArmLeft01.xRot = 0.2356F;
                ArmLeft01.zRot = -0.7854F;
                ArmLeft02.zRot = 1.5708F;
                ArmLeft02.x = armLeft02DefaultX + (-0.15F * OFFSET_SCALE);
                ArmRight01.xRot = 0.2356F;
                ArmRight01.zRot = 0.7854F;
                ArmRight02.zRot = -1.5708F;
                ArmRight02.x = armRight02DefaultX + (0.15F * OFFSET_SCALE);
                EquipHead01.visible = false;
            }
        }

        if (attackAnim > 0.0F) {
            float f7 = Mth.sin(attackAnim * attackAnim * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(attackAnim) * (float) Math.PI);
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        LegLeft01.xRot = legLeftX;
        LegRight01.xRot = legRightX;
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
        GlowBodyMain.render(poseStack, vertexConsumer, LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}