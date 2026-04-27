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

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelSubmU511<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "subm_u_511"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelSubmU511");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelSubmU511");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelSubmU511");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Butt;
    private final ModelPart Cloth01;
    private final ModelPart EquipBase;
    private final ModelPart Head;
    private final ModelPart Pipe;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Hat01;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hat02;
    private final ModelPart Ear1;
    private final ModelPart Ear2;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft03;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight03;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt;
    private final ModelPart LegRight02;
    private final ModelPart LegLeft02;
    private final ModelPart EquipMid;
    private final ModelPart EquipL;
    private final ModelPart EquipR;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float armRight03DefaultX;
    private final float armRight03DefaultY;
    private final float armRight03DefaultZRot;
    private final float legLeft01DefaultXRot;
    private final float legLeft01DefaultYRot;
    private final float legLeft01DefaultZRot;
    private final float legRight01DefaultXRot;
    private final float legRight01DefaultYRot;
    private final float legRight01DefaultZRot;
    private final float hair01DefaultXRot;
    private final float hairL01DefaultXRot;
    private final float hairL02DefaultXRot;
    private final float hairR01DefaultXRot;
    private final float hairR02DefaultXRot;
    private final float hairL01DefaultZRot;
    private final float hairL02DefaultZRot;
    private final float hairR01DefaultZRot;
    private final float hairR02DefaultZRot;
    private final float ear1DefaultZRot;
    private final float ear2DefaultZRot;
    private final float pipeDefaultXRot;
    private final float skirtDefaultXRot;

    public ModelSubmU511(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hat01 = this.Head.getChild("Hat01");
        this.Hat02 = this.Hat01.getChild("Hat02");
        this.Ear2 = this.Hat02.getChild("Ear2");
        this.Ear1 = this.Hat02.getChild("Ear1");
        this.Hair = this.Head.getChild("Hair");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Pipe = this.Neck.getChild("Pipe");
        this.Butt = this.BodyMain.getChild("Butt");
        this.Skirt = this.Butt.getChild("Skirt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight03 = this.ArmRight02.getChild("ArmRight03");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft03 = this.ArmLeft02.getChild("ArmLeft03");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipMid = this.EquipBase.getChild("EquipMid");
        this.EquipL = this.EquipMid.getChild("EquipL");
        this.EquipR = this.EquipMid.getChild("EquipR");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.armRight03DefaultX = this.ArmRight03.x;
        this.armRight03DefaultY = this.ArmRight03.y;
        this.armRight03DefaultZRot = this.ArmRight03.zRot;
        this.legLeft01DefaultXRot = this.LegLeft01.xRot;
        this.legLeft01DefaultYRot = this.LegLeft01.yRot;
        this.legLeft01DefaultZRot = this.LegLeft01.zRot;
        this.legRight01DefaultXRot = this.LegRight01.xRot;
        this.legRight01DefaultYRot = this.LegRight01.yRot;
        this.legRight01DefaultZRot = this.LegRight01.zRot;
        this.hair01DefaultXRot = this.Hair01.xRot;
        this.hairL01DefaultXRot = this.HairL01.xRot;
        this.hairL02DefaultXRot = this.HairL02.xRot;
        this.hairR01DefaultXRot = this.HairR01.xRot;
        this.hairR02DefaultXRot = this.HairR02.xRot;
        this.hairL01DefaultZRot = this.HairL01.zRot;
        this.hairL02DefaultZRot = this.HairL02.zRot;
        this.hairR01DefaultZRot = this.HairR01.zRot;
        this.hairR02DefaultZRot = this.HairR02.zRot;
        this.ear1DefaultZRot = this.Ear1.zRot;
        this.ear2DefaultZRot = this.Ear2.zRot;
        this.pipeDefaultXRot = this.Pipe.xRot;
        this.skirtDefaultXRot = this.Skirt.xRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 21F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -13F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 0).addBox(-7F, 0F, -4.5F, 14F, 11F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.5F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -2F, -6F, 9F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.5F, 0F, 0.0524F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition Hat01 = Head.addOrReplaceChild("Hat01", CubeListBuilder.create().texOffs(30, 24).addBox(-3F, -6F, 0.5F, 6F, 6F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, -6F, -0.1396F, 0F, 0F));

        PartDefinition Hat02 = Hat01.addOrReplaceChild("Hat02", CubeListBuilder.create().texOffs(4, 17).addBox(-8F, 0F, 0.5F, 16F, 1F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 8.4F, 0.3142F, 0F, 0F));

        PartDefinition Ear2 = Hat02.addOrReplaceChild("Ear2", CubeListBuilder.create().texOffs(4, 18).addBox(0F, 0F, -4F, 0F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, -1F, 0F, 0F, 0F, 0.2618F));

        PartDefinition Ear1 = Hat02.addOrReplaceChild("Ear1", CubeListBuilder.create().texOffs(4, 18).mirror().addBox(0F, 0F, -4F, 0F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, -1F, 0F, 0F, 0F, -0.2618F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 75).addBox(-8F, -8F, -6.8F, 16F, 17F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, -0.5F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(88, 100).mirror().addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 0F, -4F, -0.1745F, -0.1745F, -0.1396F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6F, 0F, -0.1745F, 0F, 0.0873F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 0F, -4F, -0.1745F, 0.1745F, 0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 100).mirror().addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 6F, 0F, -0.1745F, 0F, -0.0524F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -4F, -11F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, -5F, 0F, 0.5236F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(48, 47).addBox(-7.5F, 0F, 0F, 15F, 9F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(49, 47).addBox(-7.5F, 0F, 0F, 15F, 18F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.1F, 0.2618F, 0F, 0F));

        PartDefinition Pipe = Neck.addOrReplaceChild("Pipe", CubeListBuilder.create().texOffs(0, 17).addBox(0F, -26F, 0F, 1F, 25F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, -1F, -3.5F, -0.0873F, 0F, 0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(80, 19).addBox(-8F, 5F, -5F, 16F, 9F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2618F, 0F, 0F));

        PartDefinition Skirt = Butt.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(80, 19).addBox(-8F, 0F, -4.5F, 16F, 9F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 5F, -2F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 85).addBox(-3F, 0F, -3F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(-3.8F, 9.5F, -2.7F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 67).addBox(-3F, 0F, 0F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 13F, -3F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 85).addBox(-3F, 0F, -3F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(3.8F, 9.5F, -2.7F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 67).addBox(-3F, 0F, 0F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 13F, -3F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 67).addBox(-4.5F, -1F, -3.5F, 7F, 8F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.2F, -9F, -0.7F, 0F, 0F, 0.1047F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 95).addBox(-2.5F, 0F, -3F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(-0.8F, 7F, 0.5F));

        PartDefinition ArmRight03 = ArmRight02.addOrReplaceChild("ArmRight03", CubeListBuilder.create().texOffs(28, 78).addBox(-2.5F, 0F, -4F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, 1F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 67).mirror().addBox(-2.5F, -1F, -3.5F, 7F, 8F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.2F, -9F, -0.7F, 0F, 0F, -0.1047F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 95).mirror().addBox(-2.5F, 0F, -3F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(0.8F, 7F, 0.5F));

        PartDefinition ArmLeft03 = ArmLeft02.addOrReplaceChild("ArmLeft03", CubeListBuilder.create().texOffs(28, 78).mirror().addBox(-2.5F, 0F, -4F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, 1F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(60, 0).addBox(-3F, 0F, 1F, 6F, 16F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 3F, 0.4363F, 0F, 0F));

        PartDefinition EquipMid = EquipBase.addOrReplaceChild("EquipMid", CubeListBuilder.create().texOffs(0, 0).addBox(-13F, 0F, 0F, 26F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5F, 2F, 0.1396F, 0F, 0F));

        PartDefinition EquipL = EquipMid.addOrReplaceChild("EquipL", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(0F, 0F, -20F, 5F, 13F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11.5F, 0F, 4F, -0.3142F, -0.1745F, 0F));

        PartDefinition EquipR = EquipMid.addOrReplaceChild("EquipR", CubeListBuilder.create().texOffs(0, 23).addBox(-5F, 0F, -20F, 5F, 13F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.5F, 0F, 4F, -0.3142F, 0.1745F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -13F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.5F, 0F, 0.0524F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, 0F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        applyFaceAndMouth(entity);

        boolean inDeadPose = entity != null && entity.isInDeadPose();

        if (inDeadPose) {
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

        this.ArmRight03.x = this.armRight03DefaultX;
        this.ArmRight03.y = this.armRight03DefaultY;
        this.ArmRight03.zRot = this.armRight03DefaultZRot;
        this.LegLeft01.xRot = this.legLeft01DefaultXRot;
        this.LegLeft01.yRot = this.legLeft01DefaultYRot;
        this.LegLeft01.zRot = this.legLeft01DefaultZRot;
        this.LegRight01.xRot = this.legRight01DefaultXRot;
        this.LegRight01.yRot = this.legRight01DefaultYRot;
        this.LegRight01.zRot = this.legRight01DefaultZRot;
        this.Hair01.xRot = this.hair01DefaultXRot;
        this.HairL01.xRot = this.hairL01DefaultXRot;
        this.HairL02.xRot = this.hairL02DefaultXRot;
        this.HairR01.xRot = this.hairR01DefaultXRot;
        this.HairR02.xRot = this.hairR02DefaultXRot;
        this.HairL01.zRot = this.hairL01DefaultZRot;
        this.HairL02.zRot = this.hairL02DefaultZRot;
        this.HairR01.zRot = this.hairR01DefaultZRot;
        this.HairR02.zRot = this.hairR02DefaultZRot;
        this.Ear1.zRot = this.ear1DefaultZRot;
        this.Ear2.zRot = this.ear2DefaultZRot;
        this.Pipe.xRot = this.pipeDefaultXRot;
        this.Skirt.xRot = this.skirtDefaultXRot;
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        this.EquipBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmU511.EQUIP_BASE);
        this.Hat01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmU511.EQUIP_HAT);
        this.Pipe.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmU511.EQUIP_PIPE);
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.035F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.035F;
        this.LegLeft01.xRot = -2.8F;
        this.LegLeft02.xRot = 1.4F;
        this.LegRight01.xRot = -2.8F;
        this.LegRight02.xRot = 1.4F;

        this.Pipe.xRot = -0.0873F;
        this.Ahoke.yRot = 0.5236F;
        this.Head.xRot = 0.2618F;
        this.Head.yRot = 0.0F;
        this.BodyMain.xRot = 0.35F;

        this.ArmLeft01.xRot = -0.7F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -0.12F;
        this.ArmRight01.xRot = -0.96F;
        this.ArmRight01.yRot = -0.35F;
        this.ArmRight01.zRot = 0.12F;
        this.ArmRight03.zRot = -1.57F;
        this.ArmRight03.x = this.armRight03DefaultX + (-0.153F * OFFSET_SCALE);
        this.ArmRight03.y = this.armRight03DefaultY + (0.1F * OFFSET_SCALE);

        this.Hair01.xRot = 0.05F;
        this.Ear1.zRot = -0.2618F;
        this.Ear2.zRot = 0.2618F;
        this.Skirt.xRot = 2.618F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.5F;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY = angleX * 0.05F + 0.025F;
        }

        float addk1 = angleAdd1 - 0.2118F;
        float addk2 = angleAdd2 - 0.1118F;
        this.Head.xRot = headPitch * ((float) Math.PI / 180F) * 0.8F + 0.1F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.5F;
        this.Head.zRot = 0.0F;

        this.Ahoke.yRot = angleX * 0.25F + 0.5236F;
        this.BodyMain.xRot = -0.1F;

        this.Hair01.xRot = angleX * 0.06F + 0.3F;
        this.Hair01.zRot = 0.0F;
        this.HairL01.xRot = -0.17F;
        this.HairL02.xRot = 0.17F;
        this.HairR01.xRot = -0.17F;
        this.HairR02.xRot = 0.17F;
        this.HairL01.zRot = -0.14F;
        this.HairL02.zRot = 0.08F;
        this.HairR01.zRot = 0.14F;
        this.HairR02.zRot = -0.05F;

        this.Ear1.zRot = angleX * 0.1F - 0.2618F;
        this.Ear2.zRot = angleX * 0.1F + 0.2618F;

        this.ArmLeft01.xRot = angleAdd2 * 0.5F + 0.15F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -angleX * 0.06F - 0.16F;
        this.ArmRight01.xRot = angleAdd1 * 0.5F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = angleX * 0.06F + 0.16F;
        this.ArmRight03.zRot = 0.0F;
        this.ArmRight03.x = this.armRight03DefaultX;
        this.ArmRight03.y = this.armRight03DefaultY;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.035F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.035F;
        this.LegLeft02.xRot = 0.0F;
        this.LegRight02.xRot = 0.0F;
        this.Pipe.xRot = -0.0873F;
        this.Skirt.xRot = 0.35F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.xRot += headX;
        this.Hair01.zRot += headZ;
        this.HairL01.zRot += headZ;
        this.HairL02.zRot += headZ;
        this.HairR01.zRot += headZ;
        this.HairR02.zRot += headZ;
        this.HairL01.xRot += headX;
        this.HairL02.xRot += headX;
        this.HairR01.xRot += headX;
        this.HairR02.xRot += headX;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity != null ? entity.getIsSprinting() : limbSwingAmount > 0.9F;
        boolean isSitting = entity.getIsSitting() || isPassenger;

        if (isSprinting) {

        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.8727F;
            this.BodyMain.xRot = 1.0472F;
            this.Hair01.xRot += 0.2236F;
            this.LegLeft01.xRot -= 1.2F;
            this.LegRight01.xRot -= 1.2F;
            this.Pipe.xRot = -0.7854F;
            this.Skirt.xRot = 0.8727F;
        }

        if (isSitting) {
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.41F * 3.2F;
                this.Head.xRot += 0.2618F;
                this.BodyMain.xRot = 0.35F;
                this.HairL01.xRot -= 0.2F;
                this.HairR01.xRot -= 0.2F;
                this.HairL02.xRot -= 0.2F;
                this.HairR02.xRot -= 0.2F;
                this.ArmLeft01.xRot = -angleX * 0.2F - 0.7F;
                this.ArmRight01.xRot = -0.96F;
                this.ArmRight01.yRot = -0.35F;
                this.ArmRight03.zRot = -1.57F;
                this.ArmRight03.x = this.armRight03DefaultX + (-0.153F * OFFSET_SCALE);
                this.ArmRight03.y = this.armRight03DefaultY + (0.1F * OFFSET_SCALE);
                this.Hair01.xRot -= 0.25F;
                this.LegLeft01.xRot = -2.8F;
                this.LegRight01.xRot = -2.8F;
                this.LegLeft02.xRot = 1.4F;
                this.LegRight02.xRot = 1.4F;
                this.Skirt.xRot = 2.618F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                this.Head.xRot -= 0.7F;
                this.BodyMain.xRot = 0.5236F;
                this.HairL01.xRot -= 0.3F;
                this.HairR01.xRot -= 0.3F;
                this.HairL02.xRot -= 0.3F;
                this.HairR02.xRot -= 0.3F;
                this.ArmLeft01.xRot = -0.5236F;
                this.ArmLeft01.zRot = 0.3146F;
                this.ArmRight01.xRot = -0.5236F;
                this.ArmRight01.zRot = -0.3146F;
                this.LegLeft01.xRot = -2.2689F;
                this.LegRight01.xRot = -2.2689F;
                this.LegLeft01.yRot = -0.3491F;
                this.LegRight01.yRot = 0.3491F;
                this.Pipe.xRot = -0.7854F;
                this.Skirt.xRot = 0.8727F;
            }
        }

        if (entity != null && entity.getAttackTick() > 43) {
            float ft = (50 - entity.getAttackTick()) + (ageInTicks - (int) ageInTicks);
            float fa = net.minecraft.util.Mth.cos((ft *= 0.08F) * ft * (float) Math.PI);
            float fb = net.minecraft.util.Mth.cos(net.minecraft.util.Mth.sqrt(ft) * (float) Math.PI);
            this.ArmLeft01.xRot += -fb * 80.0F * ((float) Math.PI / 180F) - 0.9F;
            this.ArmLeft01.yRot += fa * 20.0F * ((float) Math.PI / 180F) - 0.3F;
            this.ArmLeft01.zRot += fb * 10.0F * ((float) Math.PI / 180F);
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
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);
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
