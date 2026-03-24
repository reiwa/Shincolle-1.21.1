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

public class ModelBattleshipHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "battleship_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float BASE_TRANSLATE_Y = LegacyPoseOffsets.baseY("ModelBattleshipHime");
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBattleshipHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBattleshipHime");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBattleshipHime");
    private static final float RIDING_TRANSLATE_Y = LegacyPoseOffsets.ridingY("ModelBattleshipHime");
    private static final float RIDING_TRANSLATE_Z = LegacyPoseOffsets.ridingZ("ModelBattleshipHime");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;
    private float poseTranslateZ;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Butt;
    private final ModelPart Cloth01;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart HeadHL;
    private final ModelPart HeadHR;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairL03;
    private final ModelPart HairR02;
    private final ModelPart HairR03;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart ClothR02;
    private final ModelPart ClothR03;
    private final ModelPart LegLeft02;
    private final ModelPart ClothL02;
    private final ModelPart ClothL03;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float buttDefaultZ;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultY;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultY;
    private final float armRight02DefaultZ;

    public ModelBattleshipHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.ClothL02 = this.LegLeft01.getChild("ClothL02");
        this.ClothL03 = this.LegLeft01.getChild("ClothL03");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.ClothR02 = this.LegRight01.getChild("ClothR02");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ClothR03 = this.LegRight01.getChild("ClothR03");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairR03 = this.HairR02.getChild("HairR03");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairL03 = this.HairL02.getChild("HairL03");
        this.HeadHL = this.Head.getChild("HeadHL");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.HeadHR = this.Head.getChild("HeadHR");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.buttDefaultZ = this.Butt.z;
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

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.0524F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.7F, -9F, -3.5F, -0.6981F, -0.1396F, -0.0873F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(15, 80).mirror().addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0F, 0F, -0.2094F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 71).mirror().addBox(-5F, 0F, -5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.7F, -9F, -3.5F, -0.6981F, 0.1396F, 0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(82, 13).addBox(-7.5F, 4F, -5.5F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2618F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 9.5F, -2.7F, -0.2094F, 0F, 0.0524F));

        PartDefinition ClothL02 = LegLeft01.addOrReplaceChild("ClothL02", CubeListBuilder.create().texOffs(10, 1).addBox(-4.4F, 0F, -3.7F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, 0F, 0.1396F, 0F, 0F));

        PartDefinition ClothL03 = LegLeft01.addOrReplaceChild("ClothL03", CubeListBuilder.create().texOffs(8, 0).addBox(-4.5F, 0F, -3.8F, 9F, 5F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.5F, 0.1F, 0.0873F, 0F, 0F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(24, 80).mirror().addBox(-3F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, 9.5F, -2.7F, -0.2094F, 0F, -0.0524F));

        PartDefinition ClothR02 = LegRight01.addOrReplaceChild("ClothR02", CubeListBuilder.create().texOffs(10, 1).addBox(-3.6F, 0F, -3.7F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, 0F, 0.1396F, 0F, 0F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(24, 80).addBox(-3F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ClothR03 = LegRight01.addOrReplaceChild("ClothR03", CubeListBuilder.create().texOffs(8, 0).addBox(-4.5F, 0F, -3.8F, 9F, 5F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.5F, 0.1F, 0.0873F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 0).addBox(-7F, 0F, -4.5F, 14F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 0F, 0.1396F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(44, 0).addBox(-4.5F, -2F, -4F, 9F, 1F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10F, -0.5F, 0.0524F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 75).addBox(-8F, -8F, -7.2F, 16F, 17F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 0F, -5F, -0.1396F, 0.1745F, 0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 12F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 10F, 0F, 0.0873F, 0F, -0.0524F));

        PartDefinition HairR03 = HairR02.addOrReplaceChild("HairR03", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 13F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, 0F, 0.1396F, 0F, -0.0524F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(108, 41).addBox(-2F, 0F, 0F, 10F, 12F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -5.3F, -7.2F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 0F, -5F, -0.1396F, -0.1745F, -0.1396F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 12F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 0F, 0.0524F, 0F, 0.0873F));

        PartDefinition HairL03 = HairL02.addOrReplaceChild("HairL03", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 13F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, 0F, 0.1396F, 0F, 0.0873F));

        PartDefinition HeadHL = Head.addOrReplaceChild("HeadHL", CubeListBuilder.create().texOffs(120, 29).mirror().addBox(-1F, -9F, -1F, 2F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -7.5F, -3.3F, 0.6981F, 0F, 0.1396F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(2, 0).addBox(-7.5F, 0F, 0F, 15F, 9F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(50, 46).addBox(-7.5F, 0F, 0F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.1745F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(2, 47).addBox(-8F, 0F, -5F, 16F, 15F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 5.7F, -0.0524F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(3, 24).addBox(-8F, 0F, -5.5F, 16F, 13F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0.2F, -0.0897F, 0F, 0.0162F));

        PartDefinition HeadHR = Head.addOrReplaceChild("HeadHR", CubeListBuilder.create().texOffs(120, 29).addBox(-1F, -9F, -1F, 2F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -7.5F, -3.3F, 0.6981F, 0F, -0.1396F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(15, 80).addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0.1047F, 0F, 0.2094F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 71).addBox(0F, 0F, -5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.0524F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10F, -0.5F, 0.0524F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, 0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        applyFaceAndMouth(entity);
        setFlushVisible(entity != null && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY
                || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        this.resetOffsets();
        this.applyEquipVisibility(entity);

        if (entity instanceof org.trp.shincolle.entity.base.EntityShipBase ship && ship.isInDeadPose()) {
            this.applyDeadPose();
            this.syncGlowParts();
            return;
        }

        this.applyBasePose(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.applySpecialPoseAdjustments(entity, ctx, limbSwing, limbSwingAmount, ageInTicks);
        this.syncGlowParts();
    }

    private void resetOffsets() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
        this.poseTranslateZ = 0.0F;

        this.Butt.z = this.buttDefaultZ;
        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.y = this.armLeft02DefaultY;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.y = this.armRight02DefaultY;
        this.ArmRight02.z = this.armRight02DefaultZ;
    }

    private void applyEquipVisibility(T entity) {

    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.0F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.BoobL.xRot = -0.7F;
        this.BoobR.xRot = -0.7F;
        this.BodyMain.zRot = 0.0F;
        this.Hair01.xRot = 0.26F;
        this.Hair02.xRot = -0.08F;
        this.Hair03.xRot = -0.14F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmRight02.xRot = 0.0F;
        this.LegLeft01.yRot = 0.0F;
        this.LegRight01.yRot = 0.0F;

        this.Head.xRot += 0.14F;
        this.BodyMain.xRot = 0.4F;
        this.Butt.xRot = -0.4F;
        this.Butt.z = this.buttDefaultZ + (0.19F * OFFSET_SCALE);
        this.BoobL.xRot -= 0.2F;
        this.BoobR.xRot -= 0.2F;
        this.ArmLeft01.xRot = -1.3F;
        this.ArmLeft01.zRot = -0.1F;
        this.ArmLeft02.zRot = 1.15F;
        this.ArmRight01.xRot = -1.3F;
        this.ArmRight01.zRot = 0.1F;
        this.ArmRight02.zRot = -1.4F;

        this.LegLeft01.zRot = -0.2F;
        this.LegLeft02.xRot = 1.34F;
        this.LegRight01.zRot = 0.2F;
        this.LegRight02.xRot = 1.13F;

        this.Hair01.xRot -= 0.2F;
        this.Hair02.xRot -= 0.2F;
        this.Hair03.xRot -= 0.1F;

        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.zRot = headZ;
        this.Hair02.zRot = headZ;
        this.HairL01.zRot = headZ;
        this.HairL02.zRot = headZ + 0.087F;
        this.HairR01.zRot = headZ;
        this.HairR02.zRot = headZ - 0.052F;

        float headX = this.Head.xRot * -0.5F;
        this.HairL01.xRot = headX - 0.1F;
        this.HairL02.xRot = headX - 0.3F;
        this.HairL03.xRot = headX;
        this.HairR01.xRot = headX - 0.1F;
        this.HairR02.xRot = headX - 0.3F;
        this.HairR03.xRot = headX;

        this.LegLeft01.xRot = -2.1232F;
        this.LegRight01.xRot = -2.0708F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.7F;

        this.poseTranslateY = BASE_TRANSLATE_Y;
        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY += angleX * 0.05f + 0.025f;
        }

        this.Head.xRot = headPitch * 0.014F + 0.05F;
        this.Head.yRot = netHeadYaw * 0.01F;
        this.BoobL.xRot = angleX * 0.06F - 0.7F;
        this.BoobR.xRot = angleX * 0.06F - 0.7F;
        this.Ahoke.zRot = angleX * 0.02F - 0.02F;
        this.BodyMain.xRot = -0.1F;
        this.Butt.xRot = 0.2618F;

        this.Hair01.xRot = angleX * 0.03F + 0.15F;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.05F;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.08F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.zRot = 0.0F;

        this.HairL01.xRot = angleX * 0.02F - 0.14F;
        this.HairL02.xRot = -angleX1 * 0.04F + 0.08F;
        this.HairL03.xRot = -angleX2 * 0.07F + 0.1F;
        this.HairR01.xRot = angleX * 0.02F - 0.14F;
        this.HairR02.xRot = -angleX1 * 0.04F + 0.08F;
        this.HairR03.xRot = -angleX2 * 0.07F + 0.1F;

        this.HairL01.zRot = -0.14F;
        this.HairL02.zRot = 0.087F;
        this.HairL03.zRot = 0.087F;
        this.HairR01.zRot = 0.14F;
        this.HairR02.zRot = -0.06F;
        this.HairR03.zRot = -0.06F;

        this.ArmLeft01.xRot = angleAdd2 * 0.8F;
        this.ArmLeft01.zRot = angleX * 0.08F - 0.2F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.8F + 0.1745F;
        this.ArmRight01.zRot = -angleX * 0.08F + 0.2F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.087F;
        this.LegLeft02.xRot = 0.0F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.087F;
        this.LegRight02.xRot = 0.0F;

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;
        if (entity != null) {
            headZ += entity.getHeadTiltAngle(ageInTicks) * -0.5F;
        }

        this.Hair01.xRot += headX;
        this.Hair01.zRot += headZ;
        this.Hair02.xRot += headX * 0.5F;
        this.Hair02.zRot += headZ * 0.5F;
        this.Hair03.xRot += headX * 0.5F;
        this.Hair03.zRot += headZ * 0.5F;

        this.HairL01.xRot += headX;
        this.HairL02.xRot += headX * 0.5F;
        this.HairL03.xRot += headX * 0.5F;
        this.HairR01.xRot += headX;
        this.HairR02.xRot += headX * 0.5F;
        this.HairR03.xRot += headX * 0.5F;

        this.HairL01.zRot += headZ;
        this.HairL02.zRot += headZ * 0.5F;
        this.HairL03.zRot += headZ * 0.5F;
        this.HairR01.zRot += headZ;
        this.HairR02.zRot += headZ * 0.5F;
        this.HairR03.zRot += headZ * 0.5F;

        this.LegLeft01.xRot = angleAdd1 - 0.122F;
        this.LegRight01.xRot = angleAdd2 - 0.174F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleAdd1 = ctx.angleAdd1;
        float angleAdd2 = ctx.angleAdd2;
        float legAddLeft = angleAdd1 - 0.122F;
        float legAddRight = angleAdd2 - 0.174F;
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isSitting = ctx.isSitting || isPassenger;

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.ArmLeft01.xRot = -0.35F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.35F;
            this.ArmRight01.zRot = -0.2618F;

            legAddLeft -= 0.88F;
            legAddRight -= 0.88F;

            this.Hair01.xRot += 0.37F;
            this.Hair02.xRot += 0.23F;
            this.Hair03.xRot -= 0.1F;
        }

        if (isSitting && !isPassenger) {
            this.isSittingPose = true;
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.65F * 2.4;
                this.Head.xRot = -1.2217F;
                this.Head.yRot *= 0.5F;
                this.BodyMain.xRot = 1.2217F;
                this.ArmLeft01.xRot = -1.9199F;
                this.ArmLeft01.zRot = -0.1745F;
                this.ArmLeft02.xRot = -2.31F;
                this.ArmLeft02.y = this.armLeft02DefaultY + (0.22F * OFFSET_SCALE);
                this.ArmLeft02.z = this.armLeft02DefaultZ + (-0.21F * OFFSET_SCALE);
                this.ArmRight01.xRot = -1.9199F;
                this.ArmRight01.zRot = 0.1745F;
                this.ArmRight02.xRot = -2.31F;
                this.ArmRight02.y = this.armRight02DefaultY + (0.22F * OFFSET_SCALE);
                this.ArmRight02.z = this.armRight02DefaultZ + (-0.21F * OFFSET_SCALE);
                legAddLeft = 0.0F;
                legAddRight = 0.0F;
                this.LegLeft02.xRot = ctx.angleX * 0.4F + 1.0F;
                this.LegRight02.xRot = -ctx.angleX * 0.4F + 1.0F;
                this.Hair01.xRot += 0.1F;
                this.Hair02.xRot += 0.05F;
                this.HairL01.xRot -= 0.3F;
                this.HairR01.xRot -= 0.3F;
                this.HairL02.xRot += 0.3F;
                this.HairR02.xRot += 0.3F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                this.Head.xRot += 0.14F;
                this.BodyMain.xRot = -0.4363F;
                this.BoobL.xRot -= 0.25F;
                this.BoobR.xRot -= 0.25F;
                this.ArmLeft01.xRot = -0.3142F;
                this.ArmLeft01.zRot = 0.349F;
                this.ArmLeft02.zRot = 1.15F;
                this.ArmRight01.xRot = -0.4363F;
                this.ArmRight01.zRot = -0.2793F;
                this.ArmRight02.zRot = -1.4F;
                legAddLeft = -1.309F;
                legAddRight = -1.7F;
                this.LegLeft01.yRot = 0.3142F;
                this.LegLeft02.xRot = 1.0472F;
                this.LegRight01.yRot = -0.35F;
                this.LegRight01.zRot = -0.2618F;
                this.LegRight02.xRot = 0.9F;
                this.Hair01.xRot += 0.12F;
                this.Hair02.xRot += 0.15F;
                this.Hair03.xRot += 0.25F;
            }
        }

        if (isPassenger) {
            this.isSittingPose = true;
            this.poseTranslateZ = RIDING_TRANSLATE_Z;
            if (isSitting) {
                if (entity != null && hasLegacyState(entity, 1, 4)) {
                    this.poseTranslateY += 0.68F;
                    this.Head.xRot = -1.2217F;
                    this.Head.yRot *= 0.5F;
                    this.BodyMain.xRot = 1.2217F;
                    this.ArmLeft01.xRot = -1.9199F;
                    this.ArmLeft01.zRot = -0.1745F;
                    this.ArmLeft02.xRot = -2.31F;
                    this.ArmLeft02.y = this.armLeft02DefaultY + (0.22F * OFFSET_SCALE);
                    this.ArmLeft02.z = this.armLeft02DefaultZ + (-0.21F * OFFSET_SCALE);
                    this.ArmRight01.xRot = -1.9199F;
                    this.ArmRight01.zRot = 0.1745F;
                    this.ArmRight02.xRot = -2.31F;
                    this.ArmRight02.y = this.armRight02DefaultY + (0.22F * OFFSET_SCALE);
                    this.ArmRight02.z = this.armRight02DefaultZ + (-0.21F * OFFSET_SCALE);
                    legAddLeft = 0.0F;
                    legAddRight = 0.0F;
                    this.LegLeft02.xRot = ctx.angleX * 0.4F + 1.0F;
                    this.LegRight02.xRot = -ctx.angleX * 0.4F + 1.0F;
                    this.Hair01.xRot += 0.1F;
                    this.Hair02.xRot += 0.05F;
                    this.HairL01.xRot -= 0.3F;
                    this.HairR01.xRot -= 0.3F;
                    this.HairL02.xRot += 0.3F;
                    this.HairR02.xRot += 0.3F;
                } else {
                    this.poseTranslateY += RIDING_TRANSLATE_Y;
                    this.Head.xRot += 0.14F;
                    this.BodyMain.xRot = -0.4363F;
                    this.BoobL.xRot -= 0.25F;
                    this.BoobR.xRot -= 0.25F;
                    this.ArmLeft01.xRot = -0.3142F;
                    this.ArmLeft01.zRot = 0.349F;
                    this.ArmLeft02.zRot = 1.15F;
                    this.ArmRight01.xRot = -0.4363F;
                    this.ArmRight01.zRot = -0.2793F;
                    this.ArmRight02.zRot = -1.4F;
                    legAddLeft = -1.309F;
                    legAddRight = -1.7F;
                    this.LegLeft01.yRot = 0.3142F;
                    this.LegLeft02.xRot = 1.0472F;
                    this.LegRight01.yRot = -0.35F;
                    this.LegRight01.zRot = -0.2618F;
                    this.LegRight02.xRot = 0.9F;
                    this.Hair01.xRot += 0.12F;
                    this.Hair02.xRot += 0.15F;
                    this.Hair03.xRot += 0.25F;
                }
            } else {
                this.poseTranslateY += 0.17F;
                this.Head.xRot += 0.1745F;
                this.BodyMain.xRot = -0.35F;
                this.ArmLeft01.xRot = -0.2F;
                this.ArmLeft01.zRot = 0.349F;
                this.ArmLeft02.zRot = 1.15F;
                this.ArmRight01.xRot = -0.3F;
                this.ArmRight01.zRot = -0.2793F;
                this.ArmRight02.zRot = -1.4F;
                legAddLeft = 0.1745F;
                legAddRight = -0.8727F;
                this.LegLeft01.zRot = -0.1F;
                this.LegRight01.zRot = 0.1F;
                this.LegRight02.xRot = 1.0472F;
                this.Hair01.xRot += 0.12F;
                this.Hair02.xRot += 0.22F;
                this.Hair03.xRot += 0.25F;
            }
        }

        if (entity != null && entity.getAttackTick() > 20) {
            this.ArmLeft01.xRot = -1.6F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = 0.21F;
            this.ArmLeft02.xRot = 0.0F;
            this.ArmLeft02.zRot = 0.0F;
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
            this.ArmRight02.xRot = 0.0F;
            this.ArmRight02.yRot = 0.0F;
            this.ArmRight02.zRot = 0.0F;
        }

        this.LegLeft01.xRot = legAddLeft;
        this.LegRight01.xRot = legAddRight;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.copyFrom(this.BodyMain);
            this.GlowNeck.copyFrom(this.Neck);
            this.GlowHead.copyFrom(this.Head);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F || this.poseTranslateZ != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, this.poseTranslateZ);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
