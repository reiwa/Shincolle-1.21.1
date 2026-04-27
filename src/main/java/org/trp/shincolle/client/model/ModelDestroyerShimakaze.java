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

public class ModelDestroyerShimakaze<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "destroyer_shimakaze"), "main");

    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelDestroyerShimakaze");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelDestroyerShimakaze");
    private static final float OFFSET_SCALE = 16.0F;

    private static final float BODY_BASE_X_ROT = -0.1F;
    private static final float HEAD_BASE_X_ROT = 0.1F;
    private static final float BUTT_BASE_X_ROT = 0.2618F;
    private static final float AHOKE_BASE_Y_ROT = 0.5236F;

    private boolean isDeadPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart NeckCloth;
    private final ModelPart ArmLeft;
    private final ModelPart ArmRight;
    private final ModelPart Butt;
    private final ModelPart EquipBase;
    private final ModelPart Head;
    private final ModelPart NeckTie;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairAnchor;
    private final ModelPart HairR02;
    private final ModelPart HairMidL01;
    private final ModelPart HairMidR01;
    private final ModelPart EarBase;
    private final ModelPart HairMidL02;
    private final ModelPart HairMidR02;
    private final ModelPart EarL01;
    private final ModelPart EarL02;
    private final ModelPart EarR01;
    private final ModelPart EarR02;
    private final ModelPart LegRight;
    private final ModelPart LegLeft;
    private final ModelPart Skirt;
    private final ModelPart ShoesR;
    private final ModelPart ShoesL;
    private final ModelPart EquipHead;
    private final ModelPart EquipT01;
    private final ModelPart EquipT02;
    private final ModelPart EquipT03;
    private final ModelPart EquipT04;
    private final ModelPart EquipT05;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeckCloth;
    private final ModelPart GlowHead;

    public ModelDestroyerShimakaze(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.NeckCloth = this.BodyMain.getChild("NeckCloth");
        this.Head = this.NeckCloth.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.HairMidR01 = this.HairMain.getChild("HairMidR01");
        this.HairMidR02 = this.HairMidR01.getChild("HairMidR02");
        this.HairMidL01 = this.HairMain.getChild("HairMidL01");
        this.HairMidL02 = this.HairMidL01.getChild("HairMidL02");
        this.EarBase = this.HairMain.getChild("EarBase");
        this.EarL01 = this.EarBase.getChild("EarL01");
        this.EarL02 = this.EarL01.getChild("EarL02");
        this.EarR01 = this.EarBase.getChild("EarR01");
        this.EarR02 = this.EarR01.getChild("EarR02");
        this.Hair = this.Head.getChild("Hair");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairAnchor = this.HairL02.getChild("HairAnchor");
        this.NeckTie = this.NeckCloth.getChild("NeckTie");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight = this.Butt.getChild("LegRight");
        this.ShoesR = this.LegRight.getChild("ShoesR");
        this.LegLeft = this.Butt.getChild("LegLeft");
        this.ShoesL = this.LegLeft.getChild("ShoesL");
        this.Skirt = this.Butt.getChild("Skirt");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipT05 = this.EquipBase.getChild("EquipT05");
        this.EquipT01 = this.EquipBase.getChild("EquipT01");
        this.EquipT04 = this.EquipBase.getChild("EquipT04");
        this.EquipT03 = this.EquipBase.getChild("EquipT03");
        this.EquipHead = this.EquipBase.getChild("EquipHead");
        this.EquipT02 = this.EquipBase.getChild("EquipT02");
        this.ArmRight = this.BodyMain.getChild("ArmRight");
        this.ArmLeft = this.BodyMain.getChild("ArmLeft");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeckCloth = this.GlowBodyMain.getChild("GlowNeckCloth");
        this.GlowHead = this.GlowNeckCloth.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 37).addBox(-7F, -11F, -4F, 14F, 17F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, 0F));

        PartDefinition NeckCloth = BodyMain.addOrReplaceChild("NeckCloth", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -1.5F, -4.5F, 15F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, 0F));

        PartDefinition Head = NeckCloth.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(24, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(23, 61).addBox(-7.5F, 0F, 0F, 15F, 9F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition HairMidR01 = HairMain.addOrReplaceChild("HairMidR01", CubeListBuilder.create().texOffs(42, 40).mirror().addBox(-4.5F, 0F, 0F, 9F, 13F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 9F, 2.5F, 0.1396F, -0.0873F, 0.2618F));

        PartDefinition HairMidR02 = HairMidR01.addOrReplaceChild("HairMidR02", CubeListBuilder.create().texOffs(46, 21).mirror().addBox(-4.5F, 0F, 0F, 9F, 14F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 3F, 0.1396F, 0F, 0.1396F));

        PartDefinition HairMidL01 = HairMain.addOrReplaceChild("HairMidL01", CubeListBuilder.create().texOffs(42, 40).addBox(-4.5F, 0F, 0F, 9F, 13F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 9F, 2.5F, 0.1396F, 0.0873F, -0.2618F));

        PartDefinition HairMidL02 = HairMidL01.addOrReplaceChild("HairMidL02", CubeListBuilder.create().texOffs(46, 21).addBox(-4.5F, 0F, 0F, 9F, 14F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 3F, 0.1396F, 0F, -0.1396F));

        PartDefinition EarBase = HairMain.addOrReplaceChild("EarBase", CubeListBuilder.create().texOffs(80, 113).addBox(0F, 0F, 0F, 4F, 3F, 4F, new CubeDeformation(0F)), PartPose.offset(-2F, -2F, 2F));

        PartDefinition EarL01 = EarBase.addOrReplaceChild("EarL01", CubeListBuilder.create().texOffs(83, 113).addBox(-1.5F, -10F, -1F, 3F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(4F, 2.5F, 2F));

        PartDefinition EarL02 = EarL01.addOrReplaceChild("EarL02", CubeListBuilder.create().texOffs(82, 113).addBox(-2F, -13F, -1F, 4F, 13F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 0F));

        PartDefinition EarR01 = EarBase.addOrReplaceChild("EarR01", CubeListBuilder.create().texOffs(83, 113).addBox(-1.5F, -10F, -1F, 3F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 2.5F, 2F));

        PartDefinition EarR02 = EarR01.addOrReplaceChild("EarR02", CubeListBuilder.create().texOffs(82, 113).addBox(-2F, -13F, -1F, 4F, 13F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(24, 80).addBox(-8F, -7.5F, -8F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(102, 0).addBox(-1F, 0F, 0F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.5F, 0F, -3F, -0.2618F, 0.1745F, 0.2618F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(103, 1).addBox(-1F, 0F, 0F, 2F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 8.5F, 0.5F, 0.1745F, 0F, -0.1745F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(65, 88).addBox(0F, 0F, -12F, 0F, 13F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -14F, -4F, 0F, 0.5236F, 0F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(102, 0).addBox(-1F, 0F, 0F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 0F, -3F, -0.2618F, -0.1745F, -0.2618F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(103, 1).addBox(-1F, 0F, 0F, 2F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.2F, 8.5F, 0.5F, 0.2618F, 0F, 0.1745F));

        PartDefinition HairAnchor = HairL02.addOrReplaceChild("HairAnchor", CubeListBuilder.create().texOffs(112, 7).addBox(-1.5F, 0F, 0F, 2F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 8F, -1F, 0.0873F, 0F, 0.1367F));

        PartDefinition NeckTie = NeckCloth.addOrReplaceChild("NeckTie", CubeListBuilder.create().texOffs(39, 0).addBox(-3.5F, 0F, 0F, 7F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, -4.7F, -0.1396F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 22).addBox(-8F, 4F, -5.4F, 16F, 8F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2618F, 0F, 0F));

        PartDefinition LegRight = Butt.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(0, 96).addBox(-3F, 0F, -3F, 6F, 19F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, 9.5F, -3F, -0.2618F, 0F, -0.0524F));

        PartDefinition ShoesR = LegRight.addOrReplaceChild("ShoesR", CubeListBuilder.create().texOffs(88, 15).addBox(-3.5F, 0F, -3.5F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 19F, -0.2F));

        PartDefinition LegLeft = Butt.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(0, 96).mirror().addBox(-3F, 0F, -3F, 6F, 19F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 9.5F, -3F, -0.2618F, 0F, 0.0524F));

        PartDefinition ShoesL = LegLeft.addOrReplaceChild("ShoesL", CubeListBuilder.create().texOffs(88, 15).addBox(-3.5F, 0F, -3.5F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 19F, -0.2F));

        PartDefinition Skirt = Butt.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(50, 0).addBox(-8.5F, 0F, -6F, 17F, 6F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.5F, 0F, -0.1745F, 0F, 0F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(76, 33).addBox(-7F, 0F, -3.7F, 14F, 8F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -5F, 7F, 0.1396F, 0F, 0.5236F));

        PartDefinition EquipT05 = EquipBase.addOrReplaceChild("EquipT05", CubeListBuilder.create().texOffs(85, 65).addBox(0F, 0F, 0F, 3F, 31F, 3F, new CubeDeformation(0F)), PartPose.offset(-8.1F, -8F, 1F));

        PartDefinition EquipT01 = EquipBase.addOrReplaceChild("EquipT01", CubeListBuilder.create().texOffs(85, 65).addBox(0F, 0F, 0F, 3F, 31F, 3F, new CubeDeformation(0F)), PartPose.offset(5.1F, -8F, 1F));

        PartDefinition EquipT04 = EquipBase.addOrReplaceChild("EquipT04", CubeListBuilder.create().texOffs(85, 65).addBox(0F, 0F, 0F, 3F, 31F, 3F, new CubeDeformation(0F)), PartPose.offset(-4.8F, -8F, 1F));

        PartDefinition EquipT03 = EquipBase.addOrReplaceChild("EquipT03", CubeListBuilder.create().texOffs(85, 65).addBox(0F, 0F, 0F, 3F, 31F, 3F, new CubeDeformation(0F)), PartPose.offset(-1.5F, -8F, 1F));

        PartDefinition EquipHead = EquipBase.addOrReplaceChild("EquipHead", CubeListBuilder.create().texOffs(77, 29).addBox(-9F, 0F, 0F, 18F, 17F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, -0.3F));

        PartDefinition EquipT02 = EquipBase.addOrReplaceChild("EquipT02", CubeListBuilder.create().texOffs(85, 65).addBox(0F, 0F, 0F, 3F, 31F, 3F, new CubeDeformation(0F)), PartPose.offset(1.8F, -8F, 1F));

        PartDefinition ArmRight = BodyMain.addOrReplaceChild("ArmRight", CubeListBuilder.create().texOffs(0, 61).addBox(-2.5F, 0F, -2.5F, 5F, 22F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, -10.5F, 0F, 0F, 0F, 0.4363F));

        PartDefinition ArmLeft = BodyMain.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(0, 61).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 22F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, -10.5F, 0F, 0F, 0F, -0.3491F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -12F, 0F));

        PartDefinition GlowNeckCloth = GlowBodyMain.addOrReplaceChild("GlowNeckCloth", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -10F, 0F));

        PartDefinition GlowHead = GlowNeckCloth.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, 0F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        this.isDeadPose = false;
        this.poseTranslateY = 0.0F;

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

        applyBasePose(ctx);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks, limbSwing, limbSwingAmount);
        applyHairAndEarAnimation(entity, ctx, ageInTicks, limbSwing, limbSwingAmount);

        syncGlowParts();
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        EarL01.xRot = 1.0F;
        EarL01.yRot = -0.4F;
        EarL01.zRot = 0.0F;
        EarR01.xRot = 1.0F;
        EarR01.yRot = 1.0472F;
        EarR01.zRot = 0.0F;
        EarL02.xRot = -0.8F;
        EarL02.yRot = 0.0F;
        EarL02.zRot = 0.0F;
        EarR02.xRot = -0.2F;
        EarR02.yRot = -0.2F;
        EarR02.zRot = 0.0F;

        EquipBase.zRot = 0.52F;
        Head.xRot = 0.0F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        Ahoke.yRot = 0.5236F;
        BodyMain.yRot = 0.0F;
        BodyMain.xRot = 1.4835F;
        HairMidL01.xRot = -0.05F;
        HairMidR01.xRot = -0.05F;
        HairMidL02.xRot = -0.1F;
        HairMidR02.xRot = -0.1F;

        ArmLeft.xRot = -0.12F;
        ArmLeft.zRot = -0.2F;
        ArmRight.xRot = -0.12F;
        ArmRight.zRot = 0.2F;
        LegLeft.xRot = -0.2618F;
        LegRight.xRot = -0.2618F;
        LegLeft.yRot = 0.0F;
        LegRight.yRot = 0.0F;
        LegLeft.zRot = 0.03F;
        LegRight.zRot = -0.03F;
    }

    private void applyEquipVisibility(EntityShipBase entity) {
        if (entity == null) return;
        boolean showRigging = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_RIGGING);
        EquipBase.visible = showRigging;
        HairAnchor.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_HAIR_ANCHOR);
        boolean fh1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_HAIR_FRONT_1);
        boolean fh2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_HAIR_FRONT_2);
        boolean fh3 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_HAIR_FRONT_3);
        EarBase.visible = fh1 || fh2 || fh3;
    }

    private void applyBasePose(PoseContext ctx) {
        float angleX = ctx.angleX;

        BodyMain.xRot = BODY_BASE_X_ROT;
        BodyMain.yRot = 0.0F;
        Ahoke.yRot = angleX * 0.25F + AHOKE_BASE_Y_ROT;

        ArmLeft.xRot = 0.15F;
        ArmLeft.zRot = angleX * 0.1F - 0.5236F;
        ArmRight.xRot = 0.0F;
        ArmRight.yRot = 0.0F;
        ArmRight.zRot = -angleX * 0.1F + 0.5236F;

        LegLeft.yRot = 0.0F;
        LegLeft.zRot = 0.05F;
        LegRight.yRot = 0.0F;
        LegRight.zRot = -0.05F;

        EquipBase.zRot = 0.52F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks, float limbSwing, float limbSwingAmount) {
        float legLeftX = ctx.angleAdd1 - 0.21F;
        float legRightX = ctx.angleAdd2 - 0.11F;

        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSprinting = entity != null && entity.isSprinting();

        if (isSprinting || limbSwingAmount > 0.6F) {
            this.setFace(EntityShipBase.FACE_TENSION);
            Head.xRot -= 0.2618F;
            BodyMain.xRot = 0.2618F;
            HairMidL01.xRot += 0.5F;
            HairMidR01.xRot += 0.5F;
            HairMidL02.xRot += 0.5F;
            HairMidR02.xRot += 0.5F;
            ArmLeft.xRot = 0.7F;
            ArmLeft.zRot = -1.0472F;
            ArmRight.xRot = 0.7F;
            ArmRight.zRot = 1.0472F;

            legLeftX = Mth.cos(limbSwing * 2.0F) * limbSwingAmount * 1.5F - 0.5F;
            legRightX = Mth.cos(limbSwing * 2.0F + (float) Math.PI) * limbSwingAmount * 1.5F - 0.5F;

            LegLeft.yRot = 0.0F;
            LegLeft.zRot = 0.05F;
            LegRight.yRot = 0.0F;
            LegRight.zRot = -0.05F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            Head.xRot -= 0.7854F;
            BodyMain.xRot = 0.7854F;
            ArmLeft.zRot = -0.5F;
            ArmRight.zRot = 0.5F;
            legLeftX -= 0.8F;
            legRightX -= 0.8F;
        }

        if (ctx.isSitting || isPassenger) {
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY = 0.575F * 3.3F;
                Head.xRot = -1.48F;
                Head.yRot = 0.0F;
                Head.zRot = 0.0F;
                BodyMain.xRot = 1.4835F;
                ArmLeft.xRot = -3.0543F;
                ArmLeft.zRot = -0.7F;
                ArmRight.xRot = -2.8F;
                ArmRight.zRot = 0.35F;
                legLeftX = 0.0F;
                legRightX = -0.2618F;
                LegLeft.zRot = 0.1745F;
                LegRight.zRot = -0.35F;
            } else {
                this.poseTranslateY = 0.45F * 3.2F;
                Head.xRot -= 0.7F;
                BodyMain.xRot = 0.5236F;
                HairL01.xRot -= 0.2F;
                HairL02.xRot -= 0.2F;
                HairR01.xRot -= 0.2F;
                HairR02.xRot -= 0.2F;
                ArmLeft.xRot = -0.5236F;
                ArmLeft.zRot = 0.3146F;
                ArmRight.xRot = -0.5236F;
                ArmRight.zRot = -0.3146F;
                legLeftX = -2.2689F;
                legRightX = -2.2689F;
                LegLeft.yRot = -0.3491F;
                LegRight.yRot = 0.3491F;
            }
        }

        if (entity != null && entity.getAttackTick() > 20) {
            this.poseTranslateY = 0.14F + entity.getScaleLevel() * 0.07F;
            Head.xRot = -0.8727F;
            Head.yRot = 1.0472F;
            Head.zRot = -0.7F;
            BodyMain.xRot = 1.3F;
            BodyMain.yRot = -1.57F;
            ArmLeft.xRot = 0.0F;
            ArmLeft.zRot = -0.5F;
            ArmRight.xRot = 0.0F;
            ArmRight.zRot = 1.57F;
            legLeftX = -1.75F;
            legRightX = -1.92F;
            EquipBase.zRot = 1.57F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight.xRot = -0.4F;
            ArmRight.yRot = 0.0F;
            ArmRight.zRot = -0.2F;
            ArmRight.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        LegLeft.xRot = legLeftX;
        LegRight.xRot = legRightX;
    }

    private void applyHairAndEarAnimation(T entity, PoseContext ctx, float ageInTicks, float limbSwing, float limbSwingAmount) {
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleRun = Mth.cos(limbSwing * 1.5F) * limbSwingAmount;

        boolean fh1 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_HAIR_FRONT_1);
        boolean fh2 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_HAIR_FRONT_2);
        boolean fh3 = entity.getEquipFlag(org.trp.shincolle.entity.EntityDestroyerShimakaze.EQUIP_HAIR_FRONT_3);
        boolean fh4 = fh1 && fh2;
        boolean fh5 = fh1 && fh3;
        boolean fh6 = fh2 && fh3;
        boolean fh7 = fh1 && fh2 && fh3;

        if (fh7) {
            EarL01.xRot = angleX * 0.075F + 0.6F;
            EarL01.yRot = -0.5F;
            EarL01.zRot = 0.0F;
            EarR01.xRot = angleX * 0.075F + 1.1F;
            EarR01.yRot = 0.5F;
            EarR01.zRot = 0.0F;
            EarL02.xRot = angleX1 * 0.1F + 0.7F;
            EarL02.yRot = 0.1F;
            EarL02.zRot = 0.0F;
            EarR02.xRot = angleX1 * 0.1F + 1.0F;
            EarR02.yRot = -0.1F;
            EarR02.zRot = 0.0F;
        } else if (fh6) {
            EarL01.xRot = angleX * 0.075F + 1.1F;
            EarL01.yRot = -0.5F;
            EarL01.zRot = 0.0F;
            EarR01.xRot = angleX * 0.075F + 1.1F;
            EarR01.yRot = 0.5F;
            EarR01.zRot = 0.0F;
            EarL02.xRot = angleX1 * 0.1F + 1.0F;
            EarL02.yRot = 0.1F;
            EarL02.zRot = 0.0F;
            EarR02.xRot = angleX1 * 0.1F + 1.0F;
            EarR02.yRot = -0.1F;
            EarR02.zRot = 0.0F;
        } else if (fh5) {
            EarL01.xRot = angleX * 0.075F - 1.1F;
            EarL01.yRot = 0.5F;
            EarL01.zRot = 0.0F;
            EarR01.xRot = angleX1 * 0.075F - 1.1F;
            EarR01.yRot = -0.5F;
            EarR01.zRot = 0.0F;
            EarL02.xRot = angleX * 0.075F - 0.8F;
            EarL02.yRot = 0.0F;
            EarL02.zRot = -0.5F;
            EarR02.xRot = angleX1 * 0.075F - 0.8F;
            EarR02.yRot = 0.0F;
            EarR02.zRot = 0.5F;
        } else if (fh4) {
            EarL01.xRot = angleX * 0.075F + 0.6F;
            EarL01.yRot = -0.5F;
            EarL01.zRot = 0.0F;
            EarR01.xRot = angleX * 0.075F + 0.6F;
            EarR01.yRot = 0.5F;
            EarR01.zRot = 0.0F;
            EarL02.xRot = angleX1 * 0.1F + 0.7F;
            EarL02.yRot = 0.1F;
            EarL02.zRot = 0.0F;
            EarR02.xRot = angleX1 * 0.1F + 0.7F;
            EarR02.yRot = -0.1F;
            EarR02.zRot = 0.0F;
        } else if (fh3) {
            EarL01.xRot = angleX * 0.075F + 0.3F;
            EarL01.yRot = -0.8F;
            EarL01.zRot = 0.0F;
            EarR01.xRot = angleX * 0.075F + 0.9F;
            EarR01.yRot = 0.6F;
            EarR01.zRot = 0.0F;
            EarL02.xRot = angleX1 * 0.1F + 0.6F;
            EarL02.yRot = 0.1F;
            EarL02.zRot = 0.0F;
            EarR02.xRot = angleX1 * 0.1F + 1.0F;
            EarR02.yRot = -0.1F;
            EarR02.zRot = 0.0F;
        }

        HairMidL01.xRot = angleX * 0.07F + 0.14F;
        HairMidL02.xRot = -angleX1 * 0.2F + 0.14F;
        HairMidR01.xRot = HairMidL01.xRot;
        HairMidR02.xRot = HairMidL02.xRot;
        HairMidL01.zRot = -0.2618F;
        HairMidL02.zRot = -0.14F;
        HairMidR01.zRot = 0.2618F;
        HairMidR02.zRot = 0.14F;

        HairL01.xRot = angleX * 0.06F - 0.2618F;
        HairL02.xRot = -angleX1 * 0.1F + 0.2618F;
        HairR01.xRot = angleX * 0.06F - 0.2618F;
        HairR02.xRot = -angleX1 * 0.1F + 0.2618F;
        HairL01.zRot = -0.2618F;
        HairL02.zRot = 0.1745F;
        HairR01.zRot = 0.2618F;
        HairR02.zRot = -0.1745F;

        float headX = Head.xRot * -0.5F;
        float headZ = Head.zRot * -0.5F;

        HairMidL01.xRot += headX;
        HairMidL01.zRot += headZ;
        HairMidL02.xRot += headX * 0.5F;
        HairMidL02.zRot += headZ * 0.5F;
        HairMidR01.xRot += headX;
        HairMidR01.zRot += headZ;
        HairMidR02.xRot += headX * 0.5F;
        HairMidR02.zRot += headZ * 0.5F;

        HairL01.xRot += headX;
        HairL02.xRot += headX;
        HairR01.xRot += headX;
        HairR02.xRot += headX;
        HairL01.zRot += headZ;
        HairL02.zRot += headZ;
        HairR01.zRot += headZ;
        HairR02.zRot += headZ;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
            GlowNeckCloth.copyFrom(NeckCloth);
            GlowHead.copyFrom(Head);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, poseTranslateY, 0.0F);
        }

        BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, poseTranslateY, 0.0F);
        }

        if (GlowBodyMain != null) {
            GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
