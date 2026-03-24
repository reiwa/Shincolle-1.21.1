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

public class ModelSubmRo500<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "subm_ro_500"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelSubmRo500");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelSubmRo500");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelSubmRo500");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Butt;
    private final ModelPart Cloth01;
    private final ModelPart EquipBase1;
    private final ModelPart EquipBase2;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart FlowerBase;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Flower1;
    private final ModelPart Flower2;
    private final ModelPart Flower3;
    private final ModelPart Flower4;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart LegLeft02;
    private final ModelPart Equip101;
    private final ModelPart Equip102;
    private final ModelPart Equip103;
    private final ModelPart Equip104;
    private final ModelPart Equip201;
    private final ModelPart Equip202;
    private final ModelPart Equip203;
    private final ModelPart Equip204;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float armLeft01DefaultXRot;
    private final float armLeft01DefaultYRot;
    private final float armLeft01DefaultZRot;
    private final float armRight01DefaultXRot;
    private final float armRight01DefaultYRot;
    private final float armRight01DefaultZRot;
    private final float armLeft02DefaultXRot;
    private final float armRight02DefaultXRot;
    private final float legLeft01DefaultXRot;
    private final float legLeft01DefaultYRot;
    private final float legLeft01DefaultZRot;
    private final float legRight01DefaultXRot;
    private final float legRight01DefaultYRot;
    private final float legRight01DefaultZRot;
    private final float legLeft02DefaultXRot;
    private final float legRight02DefaultXRot;
    private final float hair01DefaultXRot;
    private final float hair01DefaultZRot;
    private final float hairL01DefaultXRot;
    private final float hairL02DefaultXRot;
    private final float hairR01DefaultXRot;
    private final float hairR02DefaultXRot;
    private final float hairL01DefaultZRot;
    private final float hairL02DefaultZRot;
    private final float hairR01DefaultZRot;
    private final float hairR02DefaultZRot;
    private final float equipBase1DefaultZ;
    private final float equipBase2DefaultY;
    private final float equipBase2DefaultXRot;

    public ModelSubmRo500(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.EquipBase1 = this.BodyMain.getChild("EquipBase1");
        this.Equip101 = this.EquipBase1.getChild("Equip101");
        this.Equip103 = this.Equip101.getChild("Equip103");
        this.Equip104 = this.Equip101.getChild("Equip104");
        this.Equip102 = this.Equip101.getChild("Equip102");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.EquipBase2 = this.BodyMain.getChild("EquipBase2");
        this.Equip204 = this.EquipBase2.getChild("Equip204");
        this.Equip203 = this.EquipBase2.getChild("Equip203");
        this.Equip202 = this.EquipBase2.getChild("Equip202");
        this.Equip201 = this.EquipBase2.getChild("Equip201");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.FlowerBase = this.GlowHead.getChild("FlowerBase");
        this.Flower1 = this.FlowerBase.getChild("Flower1");
        this.Flower2 = this.FlowerBase.getChild("Flower2");
        this.Flower3 = this.FlowerBase.getChild("Flower3");
        this.Flower4 = this.FlowerBase.getChild("Flower4");
        this.initFaceParts(this.GlowHead);
        this.armLeft01DefaultXRot = this.ArmLeft01.xRot;
        this.armLeft01DefaultYRot = this.ArmLeft01.yRot;
        this.armLeft01DefaultZRot = this.ArmLeft01.zRot;
        this.armRight01DefaultXRot = this.ArmRight01.xRot;
        this.armRight01DefaultYRot = this.ArmRight01.yRot;
        this.armRight01DefaultZRot = this.ArmRight01.zRot;
        this.armLeft02DefaultXRot = this.ArmLeft02.xRot;
        this.armRight02DefaultXRot = this.ArmRight02.xRot;
        this.legLeft01DefaultXRot = this.LegLeft01.xRot;
        this.legLeft01DefaultYRot = this.LegLeft01.yRot;
        this.legLeft01DefaultZRot = this.LegLeft01.zRot;
        this.legRight01DefaultXRot = this.LegRight01.xRot;
        this.legRight01DefaultYRot = this.LegRight01.yRot;
        this.legRight01DefaultZRot = this.LegRight01.zRot;
        this.legLeft02DefaultXRot = this.LegLeft02.xRot;
        this.legRight02DefaultXRot = this.LegRight02.xRot;
        this.hair01DefaultXRot = this.Hair01.xRot;
        this.hair01DefaultZRot = this.Hair01.zRot;
        this.hairL01DefaultXRot = this.HairL01.xRot;
        this.hairL02DefaultXRot = this.HairL02.xRot;
        this.hairR01DefaultXRot = this.HairR01.xRot;
        this.hairR02DefaultXRot = this.HairR02.xRot;
        this.hairL01DefaultZRot = this.HairL01.zRot;
        this.hairL02DefaultZRot = this.HairL02.zRot;
        this.hairR01DefaultZRot = this.HairR01.zRot;
        this.hairR02DefaultZRot = this.HairR02.zRot;
        this.equipBase1DefaultZ = this.EquipBase1.z;
        this.equipBase2DefaultY = this.EquipBase2.y;
        this.equipBase2DefaultXRot = this.EquipBase2.xRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -13.5F, 0F, -0.1047F, 0F, 0F));

        PartDefinition EquipBase1 = BodyMain.addOrReplaceChild("EquipBase1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 18F));

        PartDefinition Equip101 = EquipBase1.addOrReplaceChild("Equip101", CubeListBuilder.create().texOffs(0, 0).addBox(-15F, -2.5F, -2.5F, 36F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6F, -9.5F, 0.5236F, 0.0524F, 0.1396F));

        PartDefinition Equip103 = Equip101.addOrReplaceChild("Equip103", CubeListBuilder.create().texOffs(24, 73).addBox(0F, -1F, -3F, 7F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-22F, 0F, 0F, 0.7854F, 0F, 0F));

        PartDefinition Equip104 = Equip101.addOrReplaceChild("Equip104", CubeListBuilder.create().texOffs(54, 10).addBox(0F, -1.5F, -1.5F, 2F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(21F, 0F, 0F));

        PartDefinition Equip102 = Equip101.addOrReplaceChild("Equip102", CubeListBuilder.create().texOffs(28, 73).addBox(0F, -3F, -1F, 7F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-22F, 0F, 0F, 0.7854F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(82, 18).addBox(-7.5F, 4.8F, -5.6F, 15F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2618F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 85).addBox(-3F, 0F, -3F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(4.2F, 11F, -2.2F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 65).mirror().addBox(-3F, 0F, 0F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 13F, -3F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 85).addBox(-3F, 0F, -3F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(-4.2F, 11F, -2.2F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 65).addBox(-3F, 0F, 0F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 13F, -3F));

        PartDefinition EquipBase2 = BodyMain.addOrReplaceChild("EquipBase2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, -2F, 0.3142F, 0F, 0F));

        PartDefinition Equip204 = EquipBase2.addOrReplaceChild("Equip204", CubeListBuilder.create().texOffs(0, 10).addBox(0F, 0F, 0F, 24F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(-9F, 0F, -14F));

        PartDefinition Equip203 = EquipBase2.addOrReplaceChild("Equip203", CubeListBuilder.create().texOffs(46, 10).addBox(0F, 0F, 0F, 6F, 6F, 24F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 6F, 16F, (float) -Math.PI, 0F, 0F));

        PartDefinition Equip202 = EquipBase2.addOrReplaceChild("Equip202", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(0F, 0F, 0F, 24F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(-15F, 0F, 10F));

        PartDefinition Equip201 = EquipBase2.addOrReplaceChild("Equip201", CubeListBuilder.create().texOffs(46, 10).addBox(0F, 0F, 0F, 6F, 6F, 24F, new CubeDeformation(0F)), PartPose.offset(-15F, 0F, -14F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 22).addBox(-3F, -2F, -3F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.5F, 0F, 0.0524F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1.5F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 75).addBox(-8F, -8F, -6.8F, 16F, 17F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, -0.5F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 0F, -4F, -0.1745F, 0.1745F, 0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(88, 100).mirror().addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 6F, 0F, -0.1745F, 0F, -0.0524F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(88, 100).mirror().addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 0F, -4F, -0.1745F, -0.1745F, -0.1396F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(88, 100).addBox(-1F, 0F, 0F, 2F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6F, 0F, -0.1745F, 0F, 0.0873F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -5F, -12F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8.5F, -5F, 0F, 0.5236F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(48, 47).addBox(-7.5F, 0F, 0F, 15F, 9F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -15F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(49, 47).addBox(-7.5F, 0F, 0F, 15F, 18F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.1F, 0.3491F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 81).addBox(-4.5F, -0.5F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -9F, -0.5F, 0.1571F, 0F, 0.384F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 86).addBox(-2.5F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(-2F, 10.5F, 2.5F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(84, 0).addBox(-7F, 0F, -4.5F, 14F, 10F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.3F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 81).mirror().addBox(-0.5F, -0.5F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -9F, -0.5F, 0.1571F, 0F, -0.384F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 56).mirror().addBox(-2.5F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(2F, 10.5F, 2.5F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -13.5F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.5F, 0F, 0.0524F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1.5F, 0F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition FlowerBase = GlowHead.addOrReplaceChild("FlowerBase", CubeListBuilder.create().texOffs(0, 7).addBox(0F, 0F, -1.5F, 0F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.8F, -12F, -4F, -0.6981F, 0.0873F, -0.0873F));

        PartDefinition Flower1 = FlowerBase.addOrReplaceChild("Flower1", CubeListBuilder.create().texOffs(0, 7).addBox(0F, 0F, -1.5F, 0F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 1.309F, -0.0873F, 0F));

        PartDefinition Flower2 = FlowerBase.addOrReplaceChild("Flower2", CubeListBuilder.create().texOffs(0, 7).addBox(0F, 0F, -1.5F, 0F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 2.5307F, 0F, -0.0873F));

        PartDefinition Flower3 = FlowerBase.addOrReplaceChild("Flower3", CubeListBuilder.create().texOffs(0, 7).addBox(0F, 0F, -1.5F, 0F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -2.618F, 0F, -0.0873F));

        PartDefinition Flower4 = FlowerBase.addOrReplaceChild("Flower4", CubeListBuilder.create().texOffs(0, 7).addBox(0F, 0F, -1.5F, 0F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.2217F, 0F, 0F));

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

        this.ArmLeft01.xRot = this.armLeft01DefaultXRot;
        this.ArmLeft01.yRot = this.armLeft01DefaultYRot;
        this.ArmLeft01.zRot = this.armLeft01DefaultZRot;
        this.ArmRight01.xRot = this.armRight01DefaultXRot;
        this.ArmRight01.yRot = this.armRight01DefaultYRot;
        this.ArmRight01.zRot = this.armRight01DefaultZRot;
        this.ArmLeft02.xRot = this.armLeft02DefaultXRot;
        this.ArmRight02.xRot = this.armRight02DefaultXRot;
        this.LegLeft01.xRot = this.legLeft01DefaultXRot;
        this.LegLeft01.yRot = this.legLeft01DefaultYRot;
        this.LegLeft01.zRot = this.legLeft01DefaultZRot;
        this.LegRight01.xRot = this.legRight01DefaultXRot;
        this.LegRight01.yRot = this.legRight01DefaultYRot;
        this.LegRight01.zRot = this.legRight01DefaultZRot;
        this.LegLeft02.xRot = this.legLeft02DefaultXRot;
        this.LegRight02.xRot = this.legRight02DefaultXRot;
        this.Hair01.xRot = this.hair01DefaultXRot;
        this.Hair01.zRot = this.hair01DefaultZRot;
        this.HairL01.xRot = this.hairL01DefaultXRot;
        this.HairL02.xRot = this.hairL02DefaultXRot;
        this.HairR01.xRot = this.hairR01DefaultXRot;
        this.HairR02.xRot = this.hairR02DefaultXRot;
        this.HairL01.zRot = this.hairL01DefaultZRot;
        this.HairL02.zRot = this.hairL02DefaultZRot;
        this.HairR01.zRot = this.hairR01DefaultZRot;
        this.HairR02.zRot = this.hairR02DefaultZRot;
        this.EquipBase1.z = this.equipBase1DefaultZ;
        this.EquipBase2.y = this.equipBase2DefaultY;
        this.EquipBase2.xRot = this.equipBase2DefaultXRot;
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        this.EquipBase1.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmRo500.EQUIP_BASE_1);
        this.EquipBase2.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmRo500.EQUIP_BASE_2);
        this.FlowerBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmRo500.EQUIP_FLOWER);
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = -0.35F;
        this.Head.yRot = 0.0F;
        this.Ahoke.yRot = 0.5236F;
        this.BodyMain.xRot = -1.6F;
        this.Hair01.xRot = 0.3F;
        this.ArmLeft01.xRot = 3.1F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = 0.7F;
        this.ArmRight01.xRot = 3.1F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -0.7F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmRight02.xRot = 0.0F;

        this.LegLeft01.xRot = -0.2F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = -0.1F;
        this.LegRight01.xRot = -0.2F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.1F;
        this.LegLeft02.xRot = 0.0F;
        this.LegRight02.xRot = 0.0F;

        this.EquipBase1.z = this.equipBase1DefaultZ;
        this.EquipBase2.y = this.equipBase2DefaultY;
        this.EquipBase2.xRot = 0.3142F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY = angleX * 0.05F + 0.025F;
        }

        float addk1 = angleAdd1 - 0.122F;
        float addk2 = angleAdd2 - 0.122F;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) * 0.8F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.7F;
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

        this.ArmLeft01.xRot = 0.157F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -0.384F;
        this.ArmRight01.xRot = 0.157F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = 0.384F;

        this.ArmLeft02.xRot = 0.0F;
        this.ArmRight02.xRot = 0.0F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = -0.035F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.035F;
        this.LegLeft02.xRot = 0.0F;
        this.LegRight02.xRot = 0.0F;

        this.EquipBase1.z = this.equipBase1DefaultZ;
        this.EquipBase2.y = this.equipBase2DefaultY;
        this.EquipBase2.xRot = 0.3142F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity != null ? entity.getIsSprinting() : limbSwingAmount > 0.9F;
        boolean isSitting = entity.getIsSitting() || isPassenger;

        if (isSprinting) {
            this.BodyMain.xRot = 0.1745F;
            this.Head.xRot -= 0.35F;
            this.LegLeft01.xRot -= 0.25F;
            this.LegRight01.xRot -= 0.25F;

            if (net.minecraft.util.Mth.sin(ageInTicks * 0.15F) > 0.0F) {
                this.ArmLeft01.xRot = 2.6F;
                this.ArmLeft01.zRot = 0.7F;
                this.ArmRight01.xRot = 2.6F;
                this.ArmRight01.zRot = -0.7F;
            } else {
                this.ArmRight01.xRot = -2.8F;
                this.ArmRight01.zRot = -0.7F;
            }
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.8727F;
            this.BodyMain.xRot = 1.0472F;
            this.Hair01.xRot += 0.2236F;
            this.LegLeft01.xRot -= 1.2F;
            this.LegRight01.xRot -= 1.2F;
        }

        if (isSitting) {
            float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.25F);
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                if (entity.getShipDepth() > 0.0) {
                    this.poseTranslateY -= 0.21F;
                } else {
                    this.poseTranslateY += 0.43F * 3.2F;
                }
                this.Head.xRot += 0.35F;
                this.BodyMain.xRot = -0.7F;
                this.ArmLeft01.xRot = 0.5236F;
                this.ArmLeft01.zRot = -0.5236F;
                this.ArmLeft02.xRot = -1.0472F;
                this.ArmRight01.xRot = 0.7F;
                this.ArmRight01.zRot = 0.5236F;
                this.ArmRight02.xRot = -1.0472F;
                this.LegLeft01.xRot = -1.9F;
                this.LegRight01.xRot = -1.9F;
                this.LegLeft02.xRot = angleX2 * 0.4F + 0.8F;
                this.LegRight02.xRot = -angleX2 * 0.4F + 0.8F;
                this.EquipBase1.z = this.equipBase1DefaultZ + (-0.9F * OFFSET_SCALE);
                this.EquipBase2.visible = true;
                this.EquipBase2.xRot = 0.7F;
            } else {
                if (entity.getShipDepth() > 0.0) {
                    this.poseTranslateY -= 0.22F;
                } else {
                    this.poseTranslateY += SITTING_TRANSLATE_Y;
                }
                this.Head.xRot += 0.2F;
                this.BodyMain.xRot = -0.7F;
                this.ArmLeft01.xRot = 0.95F;
                this.ArmLeft01.zRot = -0.3146F;
                this.ArmRight01.xRot = 0.95F;
                this.ArmRight01.zRot = 0.3146F;
                this.LegLeft01.xRot = -1.1F;
                this.LegRight01.xRot = -1.1F;
                this.LegLeft01.yRot = -0.3491F;
                this.LegRight01.yRot = 0.3491F;
                this.EquipBase1.z = this.equipBase1DefaultZ + (-0.15F * OFFSET_SCALE);
                this.EquipBase2.y = this.equipBase2DefaultY + (-0.15F * OFFSET_SCALE);
            }
        }

        if (entity != null && entity.getAttackTick() > 41) {
            float ft = (50 - entity.getAttackTick()) + (ageInTicks - (int) ageInTicks);
            float fa = net.minecraft.util.Mth.sin((ft *= 0.125F) * ft * (float) Math.PI);
            float fb = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(ft) * (float) Math.PI);
            this.ArmLeft01.xRot += -fb * 180.0F * ((float) Math.PI / 180F) + 0.1F;
            this.ArmLeft01.yRot += fa * 20.0F * ((float) Math.PI / 180F) - 0.6F;
            this.ArmLeft01.zRot += fb * 20.0F * ((float) Math.PI / 180F);
            this.ArmRight01.xRot += -fb * 180.0F * ((float) Math.PI / 180F) + 0.1F;
            this.ArmRight01.yRot += -fa * 20.0F * ((float) Math.PI / 180F) + 0.6F;
            this.ArmRight01.zRot += -fb * 20.0F * ((float) Math.PI / 180F);
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
        this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
