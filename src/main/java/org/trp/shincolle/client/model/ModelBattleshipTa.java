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

public class ModelBattleshipTa<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "battleship_ta"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBattleshipTa");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBattleshipTa");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBattleshipTa");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart NeckCloth;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Butt;
    private final ModelPart EquipLeft;
    private final ModelPart EquipRight;
    private final ModelPart Cloak01;
    private final ModelPart Head;
    private final ModelPart NeckTie;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart HairMidL01;
    private final ModelPart HairMidL02;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart LegRight;
    private final ModelPart LegLeft;
    private final ModelPart ShoesR;
    private final ModelPart ShoesL;
    private final ModelPart Cloak02;
    private final ModelPart Cloak03;
    private final ModelPart Cloak04;
    private final ModelPart Cloak05;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeckCloth;
    private final ModelPart GlowHead;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultY;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultY;
    private final float armRight02DefaultZ;

    public ModelBattleshipTa(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.EquipLeft = this.BodyMain.getChild("EquipLeft");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight = this.Butt.getChild("LegRight");
        this.ShoesR = this.LegRight.getChild("ShoesR");
        this.LegLeft = this.Butt.getChild("LegLeft");
        this.ShoesL = this.LegLeft.getChild("ShoesL");
        this.NeckCloth = this.BodyMain.getChild("NeckCloth");
        this.Head = this.NeckCloth.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.HairMidL01 = this.HairMain.getChild("HairMidL01");
        this.HairMidL02 = this.HairMidL01.getChild("HairMidL02");
        this.Hair = this.Head.getChild("Hair");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.NeckTie = this.NeckCloth.getChild("NeckTie");
        this.Cloak01 = this.BodyMain.getChild("Cloak01");
        this.Cloak02 = this.Cloak01.getChild("Cloak02");
        this.Cloak03 = this.Cloak02.getChild("Cloak03");
        this.Cloak04 = this.Cloak03.getChild("Cloak04");
        this.Cloak05 = this.Cloak04.getChild("Cloak05");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.EquipRight = this.BodyMain.getChild("EquipRight");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeckCloth = this.GlowBodyMain.getChild("GlowNeckCloth");
        this.GlowHead = this.GlowNeckCloth.getChild("GlowHead");
        initFaceParts(this.GlowHead);
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

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 56).addBox(-3F, 0F, -3F, 6F, 9F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, -10.5F, 0F, 0F, 0F, 0.1571F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 56).addBox(-2.5F, 0F, -2.5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

        PartDefinition EquipLeft = BodyMain.addOrReplaceChild("EquipLeft", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 14F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, -13F, -6F, 0F, -0.1396F, 0.2618F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 74).addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.8F, -9F, -3.5F, -0.7854F, -0.1745F, -0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 19).addBox(-8F, 4F, -5.5F, 16F, 8F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2618F, 0F, 0F));

        PartDefinition LegRight = Butt.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(0, 91).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, 9.5F, -3F, -0.2618F, 0F, -0.0524F));

        PartDefinition ShoesR = LegRight.addOrReplaceChild("ShoesR", CubeListBuilder.create().texOffs(22, 71).addBox(-3.5F, 0F, -3.5F, 7F, 19F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, -0.2F));

        PartDefinition LegLeft = Butt.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(0, 91).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 9.5F, -3F, -0.2618F, 0F, 0.0524F));

        PartDefinition ShoesL = LegLeft.addOrReplaceChild("ShoesL", CubeListBuilder.create().texOffs(22, 71).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 19F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, -0.2F));

        PartDefinition NeckCloth = BodyMain.addOrReplaceChild("NeckCloth", CubeListBuilder.create().texOffs(46, 14).addBox(-7.5F, -1.5F, -4.5F, 15F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, 0F));

        PartDefinition Head = NeckCloth.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(48, 56).addBox(-7.5F, 0F, 0F, 15F, 9F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition HairMidL01 = HairMain.addOrReplaceChild("HairMidL01", CubeListBuilder.create().texOffs(48, 34).addBox(-7.5F, 0F, 0F, 15F, 13F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.5F, 0.2618F, 0F, 0F));

        PartDefinition HairMidL02 = HairMidL01.addOrReplaceChild("HairMidL02", CubeListBuilder.create().texOffs(0, 34).addBox(-7F, 0F, 0F, 14F, 14F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.8F, -0.0873F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 75).addBox(-8F, -8F, -7.2F, 16F, 17F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 0F, -6F, -0.1745F, -0.1745F, -0.0524F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(89, 103).addBox(-1F, 0F, 0F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 0F, 0.2618F, 0F, 0.0524F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 0F, -6F, -0.1396F, 0.1745F, 0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(89, 103).addBox(-1F, 0F, 0F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 7F, 0.5F, 0.1745F, 0F, -0.0524F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(37, 101).addBox(-4.5F, 0F, 0F, 10F, 10F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7.3F, -7.5F, -0.1367F, -0.2276F, 0F));

        PartDefinition NeckTie = NeckCloth.addOrReplaceChild("NeckTie", CubeListBuilder.create().texOffs(24, 97).addBox(-3F, 0F, 0F, 6F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 1.3F, -5.2F, -0.7F, 0.1396F, 0.1396F));

        PartDefinition Cloak01 = BodyMain.addOrReplaceChild("Cloak01", CubeListBuilder.create().texOffs(128, 0).addBox(-11.5F, 0F, 0F, 23F, 5F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, -4.4F));

        PartDefinition Cloak02 = Cloak01.addOrReplaceChild("Cloak02", CubeListBuilder.create().texOffs(128, 15).addBox(-12F, 0F, 0F, 24F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 0.3F, 0.0524F, 0F, 0F));

        PartDefinition Cloak03 = Cloak02.addOrReplaceChild("Cloak03", CubeListBuilder.create().texOffs(128, 31).addBox(-12.5F, 0F, 0F, 25F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 0.3F, 0.0524F, 0F, 0F));

        PartDefinition Cloak04 = Cloak03.addOrReplaceChild("Cloak04", CubeListBuilder.create().texOffs(128, 48).addBox(-13.5F, 0F, 0F, 27F, 8F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.0873F, 0F, 0F));

        PartDefinition Cloak05 = Cloak04.addOrReplaceChild("Cloak05", CubeListBuilder.create().texOffs(128, 67).addBox(-14.5F, 0F, 0F, 29F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 0F, 0.0873F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 56).mirror().addBox(-3F, 0F, -3F, 6F, 9F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, -10.5F, 0F, 0F, 0F, -0.1571F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 56).addBox(-2.5F, 0F, -2.5F, 5F, 13F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.8F, -9F, -3.5F, -0.7854F, 0.1745F, 0.0873F));

        PartDefinition EquipRight = BodyMain.addOrReplaceChild("EquipRight", CubeListBuilder.create().texOffs(38, 0).addBox(-12F, 0F, 0F, 12F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, -12F, -2F, 0F, 0.1396F, -0.1745F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -12F, 0F));

        PartDefinition GlowNeckCloth = GlowBodyMain.addOrReplaceChild("GlowNeckCloth", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -10F, 0F));

        PartDefinition GlowHead = GlowNeckCloth.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, 0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 256, 128);
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
        applyHairAnimation(ctx, ageInTicks);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        if (ArmLeft02 != null) {
            ArmLeft02.x = armLeft02DefaultX;
            ArmLeft02.y = armLeft02DefaultY;
            ArmLeft02.z = armLeft02DefaultZ;
        }
        if (ArmRight02 != null) {
            ArmRight02.x = armRight02DefaultX;
            ArmRight02.y = armRight02DefaultY;
            ArmRight02.z = armRight02DefaultZ;
        }
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        if (Cloak01 != null)
            Cloak01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipTa.EQUIP_CLOAK);
        boolean showEquip = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipTa.EQUIP_RIGGING);
        if (EquipLeft != null) EquipLeft.visible = showEquip;
        if (EquipRight != null) EquipRight.visible = showEquip;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = 0.2F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        BoobL.xRot = -0.7854F;
        BoobR.xRot = -0.7854F;
        NeckTie.xRot = -0.7F;
        Ahoke.zRot = -0.06F;

        BodyMain.xRot = 1.4F;

        ArmLeft01.xRot = -2.8F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = 0.8727F;

        ArmRight01.xRot = -2.8F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -0.35F;

        EquipLeft.xRot = 0.0F;
        EquipRight.xRot = 0.0F;

        HairMidL01.xRot = 0.05F;
        HairMidL02.xRot = -0.3F;

        LegLeft.xRot = -0.087F;
        LegLeft.yRot = 0.0F;
        LegLeft.zRot = -0.2618F;

        LegRight.xRot = -0.087F;
        LegRight.yRot = 0.0F;
        LegRight.zRot = 0.4F;

        Cloak01.xRot = 0.0F;
        Cloak02.xRot = 0.0F;
        Cloak03.xRot = 0.0F;
        Cloak04.xRot = 0.0F;
        Cloak05.xRot = 0.0F;
    }

    private void applyBasePose(PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks, float headPitch) {
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);

        BoobL.xRot = -angleX * 0.06F - 0.7854F;
        BoobR.xRot = -angleX * 0.06F - 0.7854F;
        NeckTie.xRot = -angleX * 0.1F - 0.7F;

        HairMidL01.xRot = angleX * 0.06F + 0.2618F;
        HairMidL01.zRot = 0.0F;
        HairMidL02.xRot = -angleX1 * 0.08F - 0.087F;
        HairMidL02.zRot = 0.0F;

        HairL01.xRot = angleX * 0.06F - 0.13F;
        HairL01.zRot = -0.05F;
        HairL02.xRot = -angleX1 * 0.08F + 0.21F;
        HairL02.zRot = 0.05F;

        HairR01.xRot = angleX * 0.06F - 0.13F;
        HairR01.zRot = 0.087F;
        HairR02.xRot = -angleX1 * 0.08F + 0.21F;
        HairR02.zRot = -0.05F;

        Ahoke.zRot = angleX * 0.1F - 0.06F;

        BodyMain.xRot = 0.0F;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;

        ArmLeft01.xRot = 0.35F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = 0.2618F;

        ArmRight01.xRot = 0.35F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -0.2618F;

        LegLeft.yRot = 0.0F;
        LegLeft.zRot = 0.14F;

        LegRight.yRot = 0.0F;
        LegRight.zRot = -0.14F;

        EquipLeft.xRot = 0.0F;
        EquipRight.xRot = 0.0F;

        Cloak01.xRot = 0.0F;
        Cloak02.xRot = angleX * 0.05F + 0.15F;
        Cloak03.xRot = angleX * 0.05F + 0.18F;
        Cloak04.xRot = angleX * 0.05F + 0.15F;
        Cloak05.xRot = 0.2F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleRun = Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.6F;
        float angleRun2 = Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.6F;
        float addk1 = angleRun - 0.35F;
        float addk2 = angleRun2 - 0.087F;

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = entity != null && entity.isSprinting() || limbSwingAmount > 0.9F;

        if (isSprinting) {
            addk2 -= 0.35F;
            HairMidL01.xRot += angleRun * 0.1F + 0.2F;
            HairMidL02.xRot += angleRun2 * 0.1F + 0.2F;
            BodyMain.xRot = 0.087F;
            BodyMain.yRot = 0.0F;
            ArmLeft01.xRot = angleRun2;
            ArmLeft01.zRot = -0.1745F;
            ArmRight01.xRot = angleRun;
            ArmRight01.zRot = 0.1745F;
            LegLeft.zRot = 0.05F;
            LegRight.zRot = -0.05F;
            Cloak02.xRot = angleRun * 0.05F + 0.3F;
            Cloak03.xRot = angleRun * 0.05F + 0.3F;
            Cloak04.xRot = angleRun * 0.05F + 0.35F;
            Cloak05.xRot = angleRun * 0.05F + 0.4F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            addk1 -= 0.52F;
            addk2 -= 1.0F;
            BodyMain.xRot = 0.7F;
            ArmLeft01.xRot = -0.35F;
            ArmLeft01.zRot = 0.26F;
            ArmRight01.xRot = -0.35F;
            ArmRight01.zRot = -0.26F;
            Cloak02.xRot = ctx.angleX * 0.05F + 0.15F;
            Cloak03.xRot = ctx.angleX * 0.05F + 0.15F;
            Cloak04.xRot = ctx.angleX * 0.05F + 0.2F;
            Cloak05.xRot = ctx.angleX * 0.05F + 0.2F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.65F * 2.9F;
                addk1 = -0.087F;
                addk2 = 0.174F;
                Head.xRot -= 1.4F;
                Head.yRot *= 0.5F;
                BodyMain.xRot = 1.4F;
                ArmLeft01.xRot = -2.8F;
                ArmLeft01.zRot = -0.8727F;
                ArmRight01.xRot = -2.6F;
                ArmRight01.zRot = 0.35F;
                LegLeft.zRot = 0.2618F;
                LegRight.zRot = -0.2618F;
                Cloak01.xRot = 0.0F;
                Cloak02.xRot = ctx.angleX * 0.01F + 0.15F;
                Cloak03.xRot = ctx.angleX * 0.01F + 0.18F;
                Cloak04.xRot = 0.0F;
                Cloak05.xRot = 0.0F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                addk1 = -1.0472F;
                addk2 = -1.3F;
                Head.xRot += 0.35F;
                HairMidL01.xRot += 0.2F;
                HairMidL02.xRot += 0.2F;
                BodyMain.xRot = -0.7F;
                ArmLeft01.xRot = 1.0472F;
                ArmLeft01.zRot = -0.2618F;
                ArmRight01.xRot = 1.0472F;
                ArmRight01.zRot = 0.2618F;
                LegLeft.zRot = 0.6F;
                LegRight.zRot = -0.6F;
                EquipLeft.xRot = 0.7F;
                EquipRight.xRot = 0.7F;
                Cloak01.xRot = 0.7F;
                Cloak02.xRot = ctx.angleX * 0.03F + 0.15F;
                Cloak03.xRot = ctx.angleX * 0.03F + 0.15F;
                Cloak04.xRot = ctx.angleX * 0.03F + 0.5F;
                Cloak05.xRot = ctx.angleX * 0.03F + 0.2F;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            ArmLeft01.xRot = -1.3F;
            ArmLeft01.yRot = -0.7F;
            ArmLeft01.zRot = 0.0F;
            ArmRight01.xRot = 0.17F;
            ArmRight01.zRot = 0.17F;
            EquipLeft.xRot = 0.2618F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot = 0.35F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.26F;
            ArmRight01.xRot += -f8 * 120.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.5F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        LegLeft.xRot = addk1;
        LegRight.xRot = addk2;
    }

    private void applyHairAnimation(PoseContext ctx, float ageInTicks) {
        float headX = Head.xRot * -0.5F;
        float headZ = Head.zRot * -0.5F;

        HairMidL01.xRot += headX;
        HairMidL01.zRot += headZ;
        HairMidL02.xRot += headX * 0.5F;
        HairMidL02.zRot += headZ * 0.5F;

        HairL01.zRot += headZ;
        HairL02.zRot += headZ;
        HairR01.zRot += headZ;
        HairR02.zRot += headZ;

        HairL01.xRot += headX;
        HairL02.xRot += headX;
        HairR01.xRot += headX;
        HairR02.xRot += headX;
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
