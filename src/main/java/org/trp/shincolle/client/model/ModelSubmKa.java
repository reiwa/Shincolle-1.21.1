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

public class ModelSubmKa<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "subm_ka"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelSubmKa");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelSubmKa");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelSubmKa");
    private static final float SPRINT_TRANSLATE_Y = LegacyPoseOffsets.sprintY("ModelSubmKa");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Butt;
    private final ModelPart Head;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart BodyMain1;
    private final ModelPart BodyMain2;
    private final ModelPart BoobL;
    private final ModelPart BoobL2;
    private final ModelPart BoobR;
    private final ModelPart BoobR2;
    private final ModelPart Butt1;
    private final ModelPart Butt2;
    private final ModelPart EquipBase;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart LegLeft02;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart EquipHead01;
    private final ModelPart Ahoke;
    private final ModelPart HairU01;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead04;
    private final ModelPart EquipHead05;
    private final ModelPart ArmLeft02;
    private final ModelPart EquipT01a;
    private final ModelPart EquipT01b;
    private final ModelPart ArmRight02;
    private final ModelPart EquipC01;
    private final ModelPart EquipC02;
    private final ModelPart EquipC02a;
    private final ModelPart EquipC02b;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
    private final ModelPart GlowArmLeft01;
    private final ModelPart GlowArmLeft02;
    private final float buttDefaultXRot;
    private final float hair01DefaultXRot;
    private final float hair02DefaultXRot;
    private final float hair03DefaultXRot;
    private final float hairL01DefaultZRot;
    private final float hairL02DefaultZRot;
    private final float hairR01DefaultZRot;
    private final float hairR02DefaultZRot;
    private final float equipT01aDefaultX;
    private final float equipT01aDefaultY;
    private final float equipT01aDefaultZ;

    public ModelSubmKa(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Head = this.BodyMain.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.EquipHead01 = this.Head.getChild("EquipHead01");
        this.EquipHead03 = this.EquipHead01.getChild("EquipHead03");
        this.EquipHead04 = this.EquipHead03.getChild("EquipHead04");
        this.EquipHead05 = this.EquipHead04.getChild("EquipHead05");
        this.EquipHead02 = this.EquipHead01.getChild("EquipHead02");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.BodyMain2 = this.BodyMain.getChild("BodyMain2");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.Butt2 = this.BodyMain.getChild("Butt2");
        this.BoobL2 = this.BodyMain.getChild("BoobL2");
        this.BodyMain1 = this.BodyMain.getChild("BodyMain1");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipC01 = this.EquipBase.getChild("EquipC01");
        this.EquipC02 = this.EquipC01.getChild("EquipC02");
        this.EquipC02b = this.EquipC02.getChild("EquipC02b");
        this.EquipC02a = this.EquipC02.getChild("EquipC02a");
        this.Butt1 = this.BodyMain.getChild("Butt1");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.BoobR2 = this.BodyMain.getChild("BoobR2");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        this.GlowArmLeft01 = this.GlowBodyMain.getChild("GlowArmLeft01");
        this.GlowArmLeft02 = this.GlowArmLeft01.getChild("GlowArmLeft02");
        this.EquipT01a = this.GlowArmLeft02.getChild("EquipT01a");
        this.EquipT01b = this.EquipT01a.getChild("EquipT01b");
        this.initFaceParts(this.GlowHead);
        this.buttDefaultXRot = this.Butt.xRot;
        this.hair01DefaultXRot = this.Hair01.xRot;
        this.hair02DefaultXRot = this.Hair02.xRot;
        this.hair03DefaultXRot = this.Hair03.xRot;
        this.hairL01DefaultZRot = this.HairL01.zRot;
        this.hairL02DefaultZRot = this.HairL02.zRot;
        this.hairR01DefaultZRot = this.HairR01.zRot;
        this.hairR02DefaultZRot = this.HairR02.zRot;
        this.equipT01aDefaultX = this.EquipT01a.x;
        this.equipT01aDefaultY = this.EquipT01a.y;
        this.equipT01aDefaultZ = this.EquipT01a.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 106).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11F, -3F, -0.1047F, 0F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -11.8F, -0.5F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.4F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(24, 91).addBox(-2.5F, 0F, 0F, 5F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.9F, 8F, -7.2F, -0.1745F, 0.1396F, -0.0524F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(24, 91).addBox(-2.5F, 0F, 0F, 5F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 7.5F, 0.1F, 0.3142F, 0.1745F, 0.1745F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(39, 14).addBox(0F, -5F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -7F, -5.5F, 0.2618F, 0.6981F, 0F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(50, 44).mirror().addBox(-8.5F, 0F, 0F, 17F, 15F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7.7F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(24, 88).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.9F, 7.7F, -7.5F, -0.1745F, 0.4363F, 0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(24, 88).mirror().addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 10F, 0F, 0.2443F, 0.0873F, -0.1396F));

        PartDefinition EquipHead01 = Head.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -3F, 0F, 6F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -8F, 0.2618F, 0F, 0F));

        PartDefinition EquipHead03 = EquipHead01.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1F, -1F, 8F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 0.5F, -1F, 0F, -0.0873F, 0F));

        PartDefinition EquipHead04 = EquipHead03.addOrReplaceChild("EquipHead04", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 0F, -1F, -0.1745F, 0.0873F, 0F));

        PartDefinition EquipHead05 = EquipHead04.addOrReplaceChild("EquipHead05", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.4F, 14F, 0F, 0F, 0.5918F));

        PartDefinition EquipHead02 = EquipHead01.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -2F, 0F, 4F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0.5F, -3F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 62).addBox(-7.5F, 0F, 0F, 15F, 16F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 1.1F, 0.2094F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 63).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, -0.0873F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(0, 40).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, 0F, -0.1396F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(34, 102).addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -8.6F, -3.9F, -0.6981F, 0.0873F, 0.0698F));

        PartDefinition BodyMain2 = BodyMain.addOrReplaceChild("BodyMain2", CubeListBuilder.create().texOffs(88, 0).addBox(-6.5F, -11F, -4F, 13F, 15F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 88).mirror().addBox(-2F, -1F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.7F, -0.7F, 0.2094F, 0F, -0.3142F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(2, 88).mirror().addBox(-5F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 10F, 2.5F));

        PartDefinition Butt2 = BodyMain.addOrReplaceChild("Butt2", CubeListBuilder.create().texOffs(82, 22).addBox(-7.5F, 0F, -7F, 15F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 2.8F, 0.2094F, 0F, 0F));

        PartDefinition BoobL2 = BodyMain.addOrReplaceChild("BoobL2", CubeListBuilder.create().texOffs(65, 34).mirror().addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.44F, -8.6F, -3.9F, -0.6981F, -0.0873F, -0.0698F));

        PartDefinition BodyMain1 = BodyMain.addOrReplaceChild("BodyMain1", CubeListBuilder.create().texOffs(0, 106).addBox(-6.5F, -11F, -4F, 13F, 15F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipC01 = EquipBase.addOrReplaceChild("EquipC01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, 0F, 12F, 10F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 3F));

        PartDefinition EquipC02 = EquipC01.addOrReplaceChild("EquipC02", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.5F, 0F, 7F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 3F, -0.0873F, 0F, 0F));

        PartDefinition EquipC02b = EquipC02.addOrReplaceChild("EquipC02b", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 9F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(3F, -2F, 1F));

        PartDefinition EquipC02a = EquipC02.addOrReplaceChild("EquipC02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -11F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, 8F));

        PartDefinition Butt1 = BodyMain.addOrReplaceChild("Butt1", CubeListBuilder.create().texOffs(52, 66).addBox(-7.5F, 0F, -7F, 15F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 2.8F, 0.2094F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(52, 66), PartPose.offsetAndRotation(0F, 2.5F, 2.8F, 0.2094F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 87).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.4F, 6.5F, -4F, -0.1571F, 0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 87).mirror().addBox(0F, 0F, 0F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, -3F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 87).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offset(-4.4F, 6.5F, -4F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 87).addBox(-6F, 0F, 0F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, -3F));

        PartDefinition BoobR2 = BodyMain.addOrReplaceChild("BoobR2", CubeListBuilder.create().texOffs(106, 37).addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.44F, -8.6F, -3.9F, -0.6981F, 0.0873F, 0.0698F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 88).addBox(-3F, -1F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.7F, -0.7F, 0F, 0F, 0.2094F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(2, 88).addBox(0F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 10F, 2.5F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(34, 102).mirror().addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -8.6F, -3.9F, -0.6981F, -0.0873F, -0.0698F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -11F, -3F, -0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -11.8F, -0.5F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowArmLeft01 = GlowBodyMain.addOrReplaceChild("GlowArmLeft01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(7.8F, -9.7F, -0.7F, 0.2094F, 0F, -0.3142F));

        PartDefinition GlowArmLeft02 = GlowArmLeft01.addOrReplaceChild("GlowArmLeft02", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(3F, 10F, 2.5F));

        PartDefinition EquipT01a = GlowArmLeft02.addOrReplaceChild("EquipT01a", CubeListBuilder.create().texOffs(16, 0).addBox(-2F, -3F, -5F, 4F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(-6.5F, 6.5F, -1F));

        PartDefinition EquipT01b = EquipT01a.addOrReplaceChild("EquipT01b", CubeListBuilder.create().texOffs(0, 10).addBox(-2.5F, -3.5F, 0F, 5F, 7F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -12.9F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        applyFaceAndMouth(entity);

        boolean inDeadPose = entity != null && entity.isInDeadPose();

        if (inDeadPose) {
            this.applyDeadPose(ageInTicks);
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

        this.Butt.xRot = this.buttDefaultXRot;
        this.Hair01.xRot = this.hair01DefaultXRot;
        this.Hair02.xRot = this.hair02DefaultXRot;
        this.Hair03.xRot = this.hair03DefaultXRot;
        this.HairL01.zRot = this.hairL01DefaultZRot;
        this.HairL02.zRot = this.hairL02DefaultZRot;
        this.HairR01.zRot = this.hairR01DefaultZRot;
        this.HairR02.zRot = this.hairR02DefaultZRot;

        this.EquipT01a.x = this.equipT01aDefaultX;
        this.EquipT01a.y = this.equipT01aDefaultY;
        this.EquipT01a.z = this.equipT01aDefaultZ;
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        this.EquipBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmKa.EQUIP_BASE);
        this.EquipHead01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmKa.EQUIP_HEAD_BASE);
        boolean showNormalBody = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmKa.EQUIP_NORMAL_BODY);
        this.BodyMain1.visible = showNormalBody;
        this.Butt1.visible = showNormalBody;
        this.BoobL.visible = showNormalBody;
        this.BoobR.visible = showNormalBody;
        this.BodyMain2.visible = !showNormalBody;
        this.Butt2.visible = !showNormalBody;
        this.BoobL2.visible = !showNormalBody;
        this.BoobR2.visible = !showNormalBody;
        this.EquipT01a.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmKa.EQUIP_TORPEDO);
    }

    private void applyDeadPose(float ageInTicks) {
        this.isDeadPose = true;
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        this.poseTranslateY = DEAD_TRANSLATE_Y + angleX * 0.05F;

        this.Head.xRot = 0.5F;
        this.Head.yRot = 0.0F;
        this.BodyMain.xRot = 1.6F;
        this.Hair01.xRot = 0.1F;
        this.Hair02.xRot = -0.5F;
        this.Hair03.xRot = -0.5F;

        this.ArmLeft01.xRot = -1.6F;
        this.ArmLeft01.yRot = -0.15F - angleX * 0.05F;
        this.ArmRight01.xRot = -1.6F;
        this.ArmRight01.yRot = 0.15F + angleX * 0.05F;

        this.LegLeft01.xRot = -1.6F;
        this.LegRight01.xRot = -1.6F;
        this.LegLeft01.yRot = -0.1F - angleX * 0.05F;
        this.LegRight01.yRot = 0.1F + angleX * 0.05F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.6F + limbSwing * 0.5F);
        float angleX3 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.9F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.7F;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY = angleX * 0.05F + 0.025F;
        }

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) + 0.1047F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = 0.0F;

        this.BoobL.xRot = angleX * 0.08F - 0.76F;
        this.BoobR.xRot = angleX * 0.08F - 0.76F;
        this.Ahoke.yRot = angleX * 0.15F + 0.6F;

        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.21F;

        this.Hair01.xRot = 0.209F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -0.087F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -0.139F;
        this.Hair03.zRot = 0.0F;

        this.HairL01.xRot = -0.1F;
        this.HairL02.xRot = 0.3142F;
        this.HairR01.xRot = -0.1F;
        this.HairR02.xRot = 0.1745F;
        this.HairL01.zRot = -0.0524F;
        this.HairL02.zRot = 0.1745F;
        this.HairR01.zRot = 0.1396F;
        this.HairR02.zRot = -0.1396F;

        this.ArmLeft01.xRot = 0.2094F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = -angleX * 0.05F - 0.3142F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = 0.0F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = angleX * 0.05F + 0.2094F;
        this.ArmRight02.xRot = 0.0F;

        float addk1 = angleAdd1 * 0.6F - 0.157F;
        float addk2 = angleAdd2 * 0.6F - 0.035F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.1F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.1F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;

        this.EquipT01a.xRot = 0.14F;
        this.EquipT01a.zRot = 0.0F;
        this.EquipT01a.x = this.equipT01aDefaultX;
        this.EquipT01a.y = this.equipT01aDefaultY;
        this.EquipT01a.z = this.equipT01aDefaultZ;

        this.EquipC02.yRot = this.Head.yRot;
        this.EquipC02a.xRot = this.Head.xRot;

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.xRot += angleX1 * 0.08F + headX;
        this.Hair02.xRot += -angleX2 * 0.08F + headX * 0.5F + 0.1F;
        this.Hair03.xRot += -angleX3 * 0.08F + headX * 0.5F + 0.1F;
        this.Hair01.zRot += headZ;
        this.Hair02.zRot += headZ * 0.5F;
        this.Hair03.zRot += headZ * 0.5F;

        this.HairL01.xRot += angleX * 0.04F + headX;
        this.HairL02.xRot += angleX * 0.05F + headX * 0.8F;
        this.HairR01.xRot += angleX * 0.04F + headX;
        this.HairR02.xRot += angleX * 0.05F + headX * 0.8F;
        this.HairL01.zRot += headZ;
        this.HairL02.zRot += headZ;
        this.HairR01.zRot += headZ * 2.5F;
        this.HairR02.zRot += headZ * 0.8F;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity != null ? entity.getIsSprinting() : limbSwingAmount > 0.92F;
        boolean isSitting = entity.getIsSitting() || isPassenger;
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;

        if (isSprinting) {
            this.poseTranslateY = SPRINT_TRANSLATE_Y;
            this.Head.xRot -= 1.1F;
            this.BodyMain.xRot = 1.2566F;
            this.BoobL.xRot = angleAdd1 * 0.08F - 0.7F;
            this.BoobL.zRot = -0.07F;
            this.BoobR.xRot = angleAdd1 * 0.08F - 0.7F;
            this.BoobR.zRot = 0.07F;

            this.ArmLeft01.xRot = -2.5133F;
            this.ArmLeft01.zRot = -0.22F;
            this.ArmRight01.xRot = -2.5133F;
            this.ArmRight01.zRot = 0.22F;
            this.LegLeft01.zRot = 0.05F;
            this.LegRight01.zRot = -0.05F;
            this.EquipT01a.xRot = 1.2566F;
            this.EquipT01a.zRot = -0.1885F;
            this.EquipT01a.x = this.equipT01aDefaultX + (-0.08F * OFFSET_SCALE);
        }

        if (isCrouching) {
            this.poseTranslateY = SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 1.0472F;
            this.BodyMain.xRot = 1.0472F;
            this.Butt.xRot = -0.8378F;
            this.Hair01.xRot -= 0.1F;
            this.Hair02.xRot -= 0.2F;
            this.Hair03.xRot -= 0.5F;
            this.HairR01.zRot -= 0.5F;
            this.HairR02.zRot -= 0.2F;
            this.ArmLeft01.xRot = -0.7F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.7F;
            this.ArmRight01.zRot = -0.2618F;

            this.LegLeft01.xRot -= 0.1F;
            this.LegRight01.xRot -= 0.1F;
        }

        if (isSitting && !isPassenger) {
            float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY = angleX * 0.05F + 0.18F;
                this.Head.xRot -= 0.8F;
                this.BodyMain.xRot = 0.7854F;
                this.ArmLeft01.xRot = -0.8727F;
                this.ArmLeft01.yRot = -0.7854F;
                this.ArmLeft01.zRot = -0.3491F;
                this.ArmLeft02.zRot = 1.5708F;
                this.ArmRight01.xRot = -2.3562F;
                this.ArmRight01.yRot = 0.5236F;
                this.ArmRight01.zRot = -0.2618F;
                this.ArmRight02.xRot = -0.7F;
                this.LegLeft01.xRot = 0.45F + angleX * 0.1F;
                this.LegRight01.xRot = 0.45F - angleX * 0.1F;
                this.EquipT01a.visible = true;
                this.EquipT01a.xRot = 0.2618F;
            } else {
                this.poseTranslateY = SITTING_TRANSLATE_Y;
                this.Head.xRot -= 0.7F;
                this.BodyMain.xRot = 0.5236F;
                this.ArmLeft01.xRot = -0.4F;
                this.ArmLeft01.zRot = 0.3146F;
                this.ArmRight01.xRot = -0.4F;
                this.ArmRight01.zRot = -0.3146F;
                this.LegLeft01.xRot = -2.18F;
                this.LegRight01.xRot = -2.18F;
                this.LegLeft01.yRot = -0.3491F;
                this.LegRight01.yRot = 0.3491F;
            }
        }

        if (entity != null && entity.getAttackTick() > 41) {
            float ft = (50 - entity.getAttackTick()) + (ageInTicks - (int) ageInTicks);
            float fa = net.minecraft.util.Mth.cos((ft *= 0.125F) * ft * (float) Math.PI);
            float fb = net.minecraft.util.Mth.cos(net.minecraft.util.Mth.sqrt(ft) * (float) Math.PI);
            this.ArmLeft01.xRot += -fb * 80.0F * ((float) Math.PI / 180F) - 1.6F;
            this.ArmLeft01.yRot += fa * 20.0F * ((float) Math.PI / 180F);
            this.ArmLeft01.zRot += fb * 20.0F * ((float) Math.PI / 180F) + 0.4F;
            this.EquipT01a.x = this.equipT01aDefaultX + (0.2F * OFFSET_SCALE);
            this.EquipT01a.y = this.equipT01aDefaultY + (0.2F * OFFSET_SCALE);
            this.EquipT01a.z = this.equipT01aDefaultZ + (-0.5F * OFFSET_SCALE);
        }
        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F) - 0.3F;
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.4F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowHead.copyFrom(this.Head);
        this.GlowArmLeft01.copyFrom(this.ArmLeft01);
        this.GlowArmLeft02.copyFrom(this.ArmLeft02);
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
