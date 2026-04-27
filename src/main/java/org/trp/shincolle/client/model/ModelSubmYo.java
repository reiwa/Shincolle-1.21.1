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

public class ModelSubmYo<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "subm_yo"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelSubmYo");
    private static final float DEAD_TRANSLATE_Z = LegacyPoseOffsets.deadZ("ModelSubmYo");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelSubmYo");
    private static final float SPRINT_TRANSLATE_Y = LegacyPoseOffsets.sprintY("ModelSubmYo");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;
    private float poseTranslateZ;

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
    private final ModelPart Ahoke;
    private final ModelPart HairU01;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart EquipBody00;
    private final ModelPart EquipJaw00;
    private final ModelPart EquipHeadBack00;
    private final ModelPart EquipBody01;
    private final ModelPart EquipBody02;
    private final ModelPart EquipJaw00a;
    private final ModelPart EquipT01;
    private final ModelPart EquipJaw01;
    private final ModelPart EquipJaw02;
    private final ModelPart EquipJaw03;
    private final ModelPart EquipJaw04;
    private final ModelPart EquipJaw01a;
    private final ModelPart EquipJaw02a;
    private final ModelPart EquipJaw03a;
    private final ModelPart EquipJaw04a;
    private final ModelPart EquipT01a;
    private final ModelPart EquipT01b;
    private final ModelPart EquipT01c;
    private final ModelPart EquipHeadBack00a;
    private final ModelPart EquipHead00;
    private final ModelPart EquipT02;
    private final ModelPart EquipHead00a;
    private final ModelPart EquipHead00b;
    private final ModelPart EquipHead00c;
    private final ModelPart Eye01;
    private final ModelPart Eye02;
    private final ModelPart Eye03;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead04;
    private final ModelPart EquipHead01a;
    private final ModelPart EquipHead02a;
    private final ModelPart EquipHead03a;
    private final ModelPart EquipHead04a;
    private final ModelPart EquipE01a;
    private final ModelPart EquipE01b;
    private final ModelPart EquipE01c;
    private final ModelPart EquipE01d;
    private final ModelPart EquipT02a;
    private final ModelPart EquipT02b;
    private final ModelPart EquipT02c;
    private final ModelPart EquipS02a;
    private final ModelPart EquipS02b;
    private final ModelPart EquipS02c;
    private final ModelPart EquipS02d;
    private final ModelPart EquipS01a;
    private final ModelPart EquipS01b;
    private final ModelPart EquipS01c;
    private final ModelPart EquipS01d;
    private final ModelPart EquipT03;
    private final ModelPart EquipT04;
    private final ModelPart EquipT03a;
    private final ModelPart EquipT03b;
    private final ModelPart EquipT03c;
    private final ModelPart EquipT04a;
    private final ModelPart EquipT04b;
    private final ModelPart EquipT04c;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
    private final ModelPart GlowEquipBase;
    private final ModelPart GlowEquipBody00;
    private final ModelPart GlowEquipHeadBack00;
    private final ModelPart GlowEquipHeadBack00a;
    private final ModelPart GlowEquipHead00;
    private final ModelPart GlowEquipBody01;
    private final float buttDefaultZ;
    private final float hair01DefaultXRot;
    private final float hair01DefaultZRot;
    private final float hair02DefaultXRot;
    private final float hair02DefaultZRot;
    private final float hair03DefaultXRot;
    private final float hair03DefaultZRot;
    private final float hairL01DefaultXRot;
    private final float hairL01DefaultZRot;
    private final float hairL02DefaultXRot;
    private final float hairL02DefaultZRot;
    private final float hairR01DefaultXRot;
    private final float hairR01DefaultZRot;
    private final float hairR02DefaultXRot;
    private final float hairR02DefaultZRot;
    private final float equipHeadBack00DefaultXRot;
    private final float equipT01aDefaultXRot;
    private final float equipT01bDefaultXRot;
    private final float equipT01cDefaultXRot;
    private final float equipT02aDefaultXRot;
    private final float equipT02bDefaultXRot;
    private final float equipT02cDefaultXRot;
    private final float equipT03aDefaultXRot;
    private final float equipT03bDefaultXRot;
    private final float equipT03cDefaultXRot;
    private final float equipT04aDefaultXRot;
    private final float equipT04bDefaultXRot;
    private final float equipT04cDefaultXRot;
    private final float equipT03aDefaultZRot;
    private final float equipT03bDefaultZRot;
    private final float equipT03cDefaultZRot;
    private final float equipT04aDefaultZRot;
    private final float equipT04bDefaultZRot;
    private final float equipT04cDefaultZRot;
    private final float equipS01aDefaultXRot;
    private final float equipS01bDefaultXRot;
    private final float equipS01cDefaultXRot;
    private final float equipS01dDefaultXRot;

    public ModelSubmYo(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.BodyMain2 = this.BodyMain.getChild("BodyMain2");
        this.BoobL2 = this.BodyMain.getChild("BoobL2");
        this.BoobR2 = this.BodyMain.getChild("BoobR2");
        this.Head = this.BodyMain.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair = this.Head.getChild("Hair");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Butt1 = this.BodyMain.getChild("Butt1");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.BodyMain1 = this.BodyMain.getChild("BodyMain1");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipBody00 = this.EquipBase.getChild("EquipBody00");
        this.EquipBody01 = this.EquipBody00.getChild("EquipBody01");
        this.EquipS02b = this.EquipBody01.getChild("EquipS02b");
        this.EquipS02a = this.EquipBody01.getChild("EquipS02a");
        this.EquipS02c = this.EquipBody01.getChild("EquipS02c");
        this.EquipS02d = this.EquipBody01.getChild("EquipS02d");
        this.EquipJaw00 = this.EquipBody00.getChild("EquipJaw00");
        this.EquipJaw00a = this.EquipJaw00.getChild("EquipJaw00a");
        this.EquipJaw04 = this.EquipJaw00a.getChild("EquipJaw04");
        this.EquipJaw04a = this.EquipJaw04.getChild("EquipJaw04a");
        this.EquipJaw03 = this.EquipJaw00a.getChild("EquipJaw03");
        this.EquipJaw03a = this.EquipJaw03.getChild("EquipJaw03a");
        this.EquipJaw01 = this.EquipJaw00a.getChild("EquipJaw01");
        this.EquipJaw01a = this.EquipJaw01.getChild("EquipJaw01a");
        this.EquipJaw02 = this.EquipJaw00a.getChild("EquipJaw02");
        this.EquipJaw02a = this.EquipJaw02.getChild("EquipJaw02a");
        this.EquipT01 = this.EquipJaw00.getChild("EquipT01");
        this.EquipT01a = this.EquipT01.getChild("EquipT01a");
        this.EquipT01b = this.EquipT01a.getChild("EquipT01b");
        this.EquipT01c = this.EquipT01b.getChild("EquipT01c");
        this.EquipBody02 = this.EquipBody00.getChild("EquipBody02");
        this.EquipT03 = this.EquipBody02.getChild("EquipT03");
        this.EquipT03a = this.EquipT03.getChild("EquipT03a");
        this.EquipT03b = this.EquipT03a.getChild("EquipT03b");
        this.EquipT03c = this.EquipT03b.getChild("EquipT03c");
        this.EquipT04 = this.EquipBody02.getChild("EquipT04");
        this.EquipT04a = this.EquipT04.getChild("EquipT04a");
        this.EquipT04b = this.EquipT04a.getChild("EquipT04b");
        this.EquipT04c = this.EquipT04b.getChild("EquipT04c");
        this.EquipHeadBack00 = this.EquipBody00.getChild("EquipHeadBack00");
        this.EquipHeadBack00a = this.EquipHeadBack00.getChild("EquipHeadBack00a");
        this.EquipT02 = this.EquipHeadBack00a.getChild("EquipT02");
        this.EquipT02a = this.EquipT02.getChild("EquipT02a");
        this.EquipT02b = this.EquipT02a.getChild("EquipT02b");
        this.EquipT02c = this.EquipT02b.getChild("EquipT02c");
        this.EquipHead00 = this.EquipHeadBack00a.getChild("EquipHead00");
        this.EquipHead00b = this.EquipHead00.getChild("EquipHead00b");
        this.EquipHead00c = this.EquipHead00.getChild("EquipHead00c");
        this.EquipHead00a = this.EquipHead00.getChild("EquipHead00a");
        this.EquipHead01 = this.EquipHead00a.getChild("EquipHead01");
        this.EquipHead01a = this.EquipHead01.getChild("EquipHead01a");
        this.EquipHead02 = this.EquipHead00a.getChild("EquipHead02");
        this.EquipHead02a = this.EquipHead02.getChild("EquipHead02a");
        this.EquipHead04 = this.EquipHead00a.getChild("EquipHead04");
        this.EquipHead04a = this.EquipHead04.getChild("EquipHead04a");
        this.EquipHead03 = this.EquipHead00a.getChild("EquipHead03");
        this.EquipHead03a = this.EquipHead03.getChild("EquipHead03a");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.Butt2 = this.BodyMain.getChild("Butt2");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        this.GlowEquipBase = this.GlowBodyMain.getChild("GlowEquipBase");
        this.initFaceParts(this.GlowHead);
        this.GlowEquipBody00 = this.GlowEquipBase.getChild("GlowEquipBody00");
        this.GlowEquipHeadBack00 = this.GlowEquipBody00.getChild("GlowEquipHeadBack00");
        this.GlowEquipHeadBack00a = this.GlowEquipHeadBack00.getChild("GlowEquipHeadBack00a");
        this.GlowEquipHead00 = this.GlowEquipHeadBack00a.getChild("GlowEquipHead00");
        this.Eye01 = this.GlowEquipHead00.getChild("Eye01");
        this.Eye02 = this.GlowEquipHead00.getChild("Eye02");
        this.Eye03 = this.GlowEquipHead00.getChild("Eye03");
        this.EquipE01b = this.Eye03.getChild("EquipE01b");
        this.EquipE01d = this.Eye03.getChild("EquipE01d");
        this.EquipE01c = this.Eye03.getChild("EquipE01c");
        this.EquipE01a = this.Eye03.getChild("EquipE01a");
        this.GlowEquipBody01 = this.GlowEquipBody00.getChild("GlowEquipBody01");
        this.EquipS01a = this.GlowEquipBody01.getChild("EquipS01a");
        this.EquipS01b = this.GlowEquipBody01.getChild("EquipS01b");
        this.EquipS01c = this.GlowEquipBody01.getChild("EquipS01c");
        this.EquipS01d = this.GlowEquipBody01.getChild("EquipS01d");
        this.buttDefaultZ = this.Butt.z;
        this.hair01DefaultXRot = this.Hair01.xRot;
        this.hair01DefaultZRot = this.Hair01.zRot;
        this.hair02DefaultXRot = this.Hair02.xRot;
        this.hair02DefaultZRot = this.Hair02.zRot;
        this.hair03DefaultXRot = this.Hair03.xRot;
        this.hair03DefaultZRot = this.Hair03.zRot;
        this.hairL01DefaultXRot = this.HairL01.xRot;
        this.hairL01DefaultZRot = this.HairL01.zRot;
        this.hairL02DefaultXRot = this.HairL02.xRot;
        this.hairL02DefaultZRot = this.HairL02.zRot;
        this.hairR01DefaultXRot = this.HairR01.xRot;
        this.hairR01DefaultZRot = this.HairR01.zRot;
        this.hairR02DefaultXRot = this.HairR02.xRot;
        this.hairR02DefaultZRot = this.HairR02.zRot;
        this.equipHeadBack00DefaultXRot = this.EquipHeadBack00.xRot;
        this.equipT01aDefaultXRot = this.EquipT01a.xRot;
        this.equipT01bDefaultXRot = this.EquipT01b.xRot;
        this.equipT01cDefaultXRot = this.EquipT01c.xRot;
        this.equipT02aDefaultXRot = this.EquipT02a.xRot;
        this.equipT02bDefaultXRot = this.EquipT02b.xRot;
        this.equipT02cDefaultXRot = this.EquipT02c.xRot;
        this.equipT03aDefaultXRot = this.EquipT03a.xRot;
        this.equipT03bDefaultXRot = this.EquipT03b.xRot;
        this.equipT03cDefaultXRot = this.EquipT03c.xRot;
        this.equipT04aDefaultXRot = this.EquipT04a.xRot;
        this.equipT04bDefaultXRot = this.EquipT04b.xRot;
        this.equipT04cDefaultXRot = this.EquipT04c.xRot;
        this.equipT03aDefaultZRot = this.EquipT03a.zRot;
        this.equipT03bDefaultZRot = this.EquipT03b.zRot;
        this.equipT03cDefaultZRot = this.EquipT03c.zRot;
        this.equipT04aDefaultZRot = this.EquipT04a.zRot;
        this.equipT04bDefaultZRot = this.EquipT04b.zRot;
        this.equipT04cDefaultZRot = this.EquipT04c.zRot;
        this.equipS01aDefaultXRot = this.EquipS01a.xRot;
        this.equipS01bDefaultXRot = this.EquipS01b.xRot;
        this.equipS01cDefaultXRot = this.EquipS01c.xRot;
        this.equipS01dDefaultXRot = this.EquipS01d.xRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 106).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11F, -3F, 0.6981F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 88).addBox(-3F, -1F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.7F, -0.7F, -1.2217F, 0F, 0.8727F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(2, 88).addBox(0F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 10F, 2.5F));

        PartDefinition BodyMain2 = BodyMain.addOrReplaceChild("BodyMain2", CubeListBuilder.create().texOffs(88, 0).addBox(-6.5F, -11F, -4F, 13F, 15F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition BoobL2 = BodyMain.addOrReplaceChild("BoobL2", CubeListBuilder.create().texOffs(65, 34).mirror().addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.44F, -8.6F, -3.9F, -0.6981F, -0.0873F, -0.0698F));

        PartDefinition BoobR2 = BodyMain.addOrReplaceChild("BoobR2", CubeListBuilder.create().texOffs(106, 37).addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.44F, -8.6F, -3.9F, -0.6981F, 0.0873F, 0.0698F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.8F, -0.5F, -0.5236F, 0F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 62).addBox(-7.5F, 0F, 0F, 15F, 16F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 1.1F, 0.576F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(0, 63).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, 0.3491F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(0, 40).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, 0F, 1.7453F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.4F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(24, 91).addBox(-2.5F, 0F, 0F, 5F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.9F, 8F, -7.2F, 0.0873F, 0.1396F, -0.0524F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(24, 91).addBox(-2.5F, 0F, 0F, 5F, 12F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 7.5F, 0.1F, 0.3142F, 0.1745F, 0.1745F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(24, 88).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.7F, 7.9F, -7.5F, -0.1396F, 0.4363F, 0.1396F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(24, 88).mirror().addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 10F, 0F, 0.1745F, 0.0873F, -0.1396F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(0, 18).addBox(0F, -5F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -7F, -5.5F, 0.2618F, 0.6981F, 0F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(50, 44).mirror().addBox(-8.5F, 0F, 0F, 17F, 15F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7.7F));

        PartDefinition Butt1 = BodyMain.addOrReplaceChild("Butt1", CubeListBuilder.create().texOffs(52, 66).addBox(-7.5F, 0F, -7F, 15F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 2.8F, 0.2094F, 0F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 2.5F, 2.8F, 0.2094F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 87).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.4F, 6.5F, -4F, 1.5708F, 0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 87).mirror().addBox(0F, 0F, 0F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, -3F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 87).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.4F, 6.5F, -4F, 1.5708F, 0F, -0.1047F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 87).addBox(-6F, 0F, 0F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, -3F));

        PartDefinition BodyMain1 = BodyMain.addOrReplaceChild("BodyMain1", CubeListBuilder.create().texOffs(0, 106).addBox(-6.5F, -11F, -4F, 13F, 15F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(34, 102).mirror().addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -8.6F, -3.9F, -0.6981F, -0.0873F, -0.0698F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipBody00 = EquipBase.addOrReplaceChild("EquipBody00", CubeListBuilder.create().texOffs(1, 0).addBox(-10F, -10F, 1F, 20F, 12F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 7.5F, -0.5236F, 0F, 0F));

        PartDefinition EquipBody01 = EquipBody00.addOrReplaceChild("EquipBody01", CubeListBuilder.create().texOffs(5, 0).addBox(-8.5F, 0F, 0F, 17F, 12F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.4F, 3F, 0.4538F, 0F, 0F));

        PartDefinition EquipS02b = EquipBody01.addOrReplaceChild("EquipS02b", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 9F, 12F, -0.7854F, -1.7453F, 0F));

        PartDefinition EquipS02a = EquipBody01.addOrReplaceChild("EquipS02a", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 9F, 4F, -0.7854F, -1.3963F, 0F));

        PartDefinition EquipS02c = EquipBody01.addOrReplaceChild("EquipS02c", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 9F, 12F, -0.7854F, 1.7453F, 0F));

        PartDefinition EquipS02d = EquipBody01.addOrReplaceChild("EquipS02d", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 9F, 4F, -0.7854F, 1.3963F, 0F));

        PartDefinition EquipJaw00 = EquipBody00.addOrReplaceChild("EquipJaw00", CubeListBuilder.create().texOffs(1, 0).addBox(-10F, 0F, -11F, 20F, 12F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 5F, 0.1396F, 0F, 0F));

        PartDefinition EquipJaw00a = EquipJaw00.addOrReplaceChild("EquipJaw00a", CubeListBuilder.create().texOffs(0, 0).addBox(-10F, -2F, -6F, 20F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, -10F, 0.3491F, 0F, 0F));

        PartDefinition EquipJaw04 = EquipJaw00a.addOrReplaceChild("EquipJaw04", CubeListBuilder.create().texOffs(18, 5).addBox(-6F, -15F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.8F, 1.2F, -2.7F, 0.1396F, 1.5708F, 0F));

        PartDefinition EquipJaw04a = EquipJaw04.addOrReplaceChild("EquipJaw04a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, -5F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -14.5F, -3F, -0.1745F, 0F, 0F));

        PartDefinition EquipJaw03 = EquipJaw00a.addOrReplaceChild("EquipJaw03", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -15F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.8F, 1.2F, -2.7F, 0.1396F, -1.5708F, 0F));

        PartDefinition EquipJaw03a = EquipJaw03.addOrReplaceChild("EquipJaw03a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, -5F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -14.5F, -3F, -0.1745F, 0F, 0F));

        PartDefinition EquipJaw01 = EquipJaw00a.addOrReplaceChild("EquipJaw01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -16F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.1F, 2F, -4F, 0.1745F, 0.1396F, 0F));

        PartDefinition EquipJaw01a = EquipJaw01.addOrReplaceChild("EquipJaw01a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, -5F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15.5F, -3F, -0.1745F, 0F, 0F));

        PartDefinition EquipJaw02 = EquipJaw00a.addOrReplaceChild("EquipJaw02", CubeListBuilder.create().texOffs(35, 0).addBox(-6F, -16F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.1F, 2F, -4F, 0.1745F, -0.1396F, 0F));

        PartDefinition EquipJaw02a = EquipJaw02.addOrReplaceChild("EquipJaw02a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, -5F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15.5F, -3F, -0.1745F, 0F, 0F));

        PartDefinition EquipT01 = EquipJaw00.addOrReplaceChild("EquipT01", CubeListBuilder.create().texOffs(38, 0).addBox(-3.5F, -3.5F, -3.5F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 15F, -8F, 0.6283F, 0F, 0F));

        PartDefinition EquipT01a = EquipT01.addOrReplaceChild("EquipT01a", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0F, 0.3187F, 0F, 0F));

        PartDefinition EquipT01b = EquipT01a.addOrReplaceChild("EquipT01b", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, 0.5236F, 0F, 0F));

        PartDefinition EquipT01c = EquipT01b.addOrReplaceChild("EquipT01c", CubeListBuilder.create().texOffs(70, 15).addBox(-2F, 0F, -2F, 4F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, -0.3491F, 0F, 0F));

        PartDefinition EquipBody02 = EquipBody00.addOrReplaceChild("EquipBody02", CubeListBuilder.create().texOffs(7, 0).addBox(-8F, 0F, 0F, 16F, 12F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, 11F));

        PartDefinition EquipT03 = EquipBody02.addOrReplaceChild("EquipT03", CubeListBuilder.create().texOffs(24, 7).addBox(-3.5F, 0F, -3.5F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 9.5F, 8.5F, 1.3963F, 0.1745F, 0F));

        PartDefinition EquipT03a = EquipT03.addOrReplaceChild("EquipT03a", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0F, -0.2618F, 0F, 0F));

        PartDefinition EquipT03b = EquipT03a.addOrReplaceChild("EquipT03b", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, 0.3491F, 0F, 0F));

        PartDefinition EquipT03c = EquipT03b.addOrReplaceChild("EquipT03c", CubeListBuilder.create().texOffs(70, 15).addBox(-2F, 0F, -2F, 4F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, -0.3491F, 0F, 0F));

        PartDefinition EquipT04 = EquipBody02.addOrReplaceChild("EquipT04", CubeListBuilder.create().texOffs(0, 7).addBox(-3.5F, 0F, -3.5F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 9.5F, 8.5F, 1.3963F, -0.1745F, 0F));

        PartDefinition EquipT04a = EquipT04.addOrReplaceChild("EquipT04a", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0F, 0.1745F, 0F, 0F));

        PartDefinition EquipT04b = EquipT04a.addOrReplaceChild("EquipT04b", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, -0.5236F, 0F, 0F));

        PartDefinition EquipT04c = EquipT04b.addOrReplaceChild("EquipT04c", CubeListBuilder.create().texOffs(70, 15).addBox(-2F, 0F, -2F, 4F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, 0.3491F, 0F, 0F));

        PartDefinition EquipHeadBack00 = EquipBody00.addOrReplaceChild("EquipHeadBack00", CubeListBuilder.create().texOffs(1, 0).addBox(-9F, -10F, -10F, 18F, 12F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, 9F, -1.3963F, 0F, 0F));

        PartDefinition EquipHeadBack00a = EquipHeadBack00.addOrReplaceChild("EquipHeadBack00a", CubeListBuilder.create().texOffs(6, 0).addBox(-8F, -11F, -11F, 16F, 11F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -4F, -3F));

        PartDefinition EquipT02 = EquipHeadBack00a.addOrReplaceChild("EquipT02", CubeListBuilder.create().texOffs(20, 3).addBox(-3.5F, 0F, -3.5F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, -10F, 2.618F, 0F, 0F));

        PartDefinition EquipT02a = EquipT02.addOrReplaceChild("EquipT02a", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0F, -0.1745F, 0F, 0F));

        PartDefinition EquipT02b = EquipT02a.addOrReplaceChild("EquipT02b", CubeListBuilder.create().texOffs(68, 14).addBox(-2.5F, 0F, -2.5F, 5F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, -0.5236F, 0F, 0F));

        PartDefinition EquipT02c = EquipT02b.addOrReplaceChild("EquipT02c", CubeListBuilder.create().texOffs(70, 15).addBox(-2F, 0F, -2F, 4F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8.5F, 0F, 0.4189F, 0F, 0F));

        PartDefinition EquipHead00 = EquipHeadBack00a.addOrReplaceChild("EquipHead00", CubeListBuilder.create().texOffs(1, 0).addBox(-10F, -12F, -11F, 20F, 12F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -10F, 0.2094F, 0F, 0F));

        PartDefinition EquipHead00b = EquipHead00.addOrReplaceChild("EquipHead00b", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -3.5F, 3F, 10F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -2F, -2F, 0.2094F, -0.0698F, 0.1396F));

        PartDefinition EquipHead00c = EquipHead00.addOrReplaceChild("EquipHead00c", CubeListBuilder.create().texOffs(17, 5).addBox(-1.5F, 0F, -3.5F, 3F, 10F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, -2F, -2F, 0.2094F, 0.0698F, -0.1396F));

        PartDefinition EquipHead00a = EquipHead00.addOrReplaceChild("EquipHead00a", CubeListBuilder.create().texOffs(0, 0).addBox(-10F, -4F, -5.5F, 20F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, -12F, 0.2094F, 0F, 0F));

        PartDefinition EquipHead01 = EquipHead00a.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(8, 6).addBox(-6F, 0F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.1F, -4F, -4F, -0.1745F, 0.1396F, 0F));

        PartDefinition EquipHead01a = EquipHead01.addOrReplaceChild("EquipHead01a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 14.5F, -3F, 0.1745F, 0F, 0F));

        PartDefinition EquipHead02 = EquipHead00a.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(32, 0).addBox(-6F, 0F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.1F, -4F, -4F, -0.1745F, -0.1396F, 0F));

        PartDefinition EquipHead02a = EquipHead02.addOrReplaceChild("EquipHead02a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 14.5F, -3F, 0.1745F, 0F, 0F));

        PartDefinition EquipHead04 = EquipHead00a.addOrReplaceChild("EquipHead04", CubeListBuilder.create().texOffs(34, 5).addBox(-6F, 0F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.8F, -4.2F, -2.6F, -0.1396F, 1.5708F, 0F));

        PartDefinition EquipHead04a = EquipHead04.addOrReplaceChild("EquipHead04a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 14.5F, -3F, 0.1745F, 0F, 0F));

        PartDefinition EquipHead03 = EquipHead00a.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 4).addBox(-6F, 0F, -4F, 12F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.8F, -4.2F, -2.6F, -0.1396F, -1.5708F, 0F));

        PartDefinition EquipHead03a = EquipHead03.addOrReplaceChild("EquipHead03a", CubeListBuilder.create().texOffs(22, 25).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 14.5F, -3F, 0.1745F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 88).mirror().addBox(-2F, -1F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.7F, -0.7F, -1.2217F, 0F, -0.8727F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(2, 88).mirror().addBox(-5F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 10F, 2.5F));

        PartDefinition Butt2 = BodyMain.addOrReplaceChild("Butt2", CubeListBuilder.create().texOffs(82, 22).addBox(-7.5F, 0F, -7F, 15F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 2.8F, 0.2094F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(34, 102).addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -8.6F, -3.9F, -0.6981F, 0.0873F, 0.0698F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -11F, -3F, 0.6981F, 0F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -11.8F, -0.5F, -0.5236F, 0F, 0F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowEquipBase = GlowBodyMain.addOrReplaceChild("GlowEquipBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowEquipBody00 = GlowEquipBase.addOrReplaceChild("GlowEquipBody00", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 5F, 7.5F, -0.5236F, 0F, 0F));

        PartDefinition GlowEquipHeadBack00 = GlowEquipBody00.addOrReplaceChild("GlowEquipHeadBack00", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -8F, 9F, -1.3963F, 0F, 0F));

        PartDefinition GlowEquipHeadBack00a = GlowEquipHeadBack00.addOrReplaceChild("GlowEquipHeadBack00a", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -4F, -3F));

        PartDefinition GlowEquipHead00 = GlowEquipHeadBack00a.addOrReplaceChild("GlowEquipHead00", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -1F, -10F, 0.2094F, 0F, 0F));

        PartDefinition Eye01 = GlowEquipHead00.addOrReplaceChild("Eye01", CubeListBuilder.create().texOffs(70, 0).addBox(-1F, 0F, -3F, 2F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11F, -8.5F, -6F, 0F, -0.1047F, -0.1745F));

        PartDefinition Eye02 = GlowEquipHead00.addOrReplaceChild("Eye02", CubeListBuilder.create().texOffs(70, 0).addBox(-1F, 0F, -3F, 2F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11F, -8.5F, -6F, 0F, 0.1047F, 0.1745F));

        PartDefinition Eye03 = GlowEquipHead00.addOrReplaceChild("Eye03", CubeListBuilder.create().texOffs(70, 0).addBox(-1F, -3.5F, -3.5F, 2F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 0F, 0F, 0.8727F, 1.5708F));

        PartDefinition EquipE01b = Eye03.addOrReplaceChild("EquipE01b", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, -5F, 0F, -2.2689F, 1.5708F, 0F));

        PartDefinition EquipE01d = Eye03.addOrReplaceChild("EquipE01d", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 0F, 5F, 0.5236F, 0F, 1.5708F));

        PartDefinition EquipE01c = Eye03.addOrReplaceChild("EquipE01c", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.0472F, 0F, 1.5708F));

        PartDefinition EquipE01a = Eye03.addOrReplaceChild("EquipE01a", CubeListBuilder.create().texOffs(22, 32).addBox(-4.5F, -2.5F, -1F, 9F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 5F, 0F, 2.2689F, 1.5708F, 0F));

        PartDefinition GlowEquipBody01 = GlowEquipBody00.addOrReplaceChild("GlowEquipBody01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -2.4F, 3F, 0.4538F, 0F, 0F));

        PartDefinition EquipS01a = GlowEquipBody01.addOrReplaceChild("EquipS01a", CubeListBuilder.create().texOffs(41, 35).addBox(-3F, 0F, -3F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 11F, 3F, -0.2618F, 0F, -0.2618F));

        PartDefinition EquipS01b = GlowEquipBody01.addOrReplaceChild("EquipS01b", CubeListBuilder.create().texOffs(41, 35).addBox(-3F, 0F, -3F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 11F, 3F, -0.2618F, 0F, 0.2618F));

        PartDefinition EquipS01c = GlowEquipBody01.addOrReplaceChild("EquipS01c", CubeListBuilder.create().texOffs(41, 35).addBox(-3F, 0F, -3F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 11F, 11F, 0.2618F, 0F, -0.2618F));

        PartDefinition EquipS01d = GlowEquipBody01.addOrReplaceChild("EquipS01d", CubeListBuilder.create().texOffs(41, 35).addBox(-3F, 0F, -3F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 11F, 11F, 0.2618F, 0F, 0.2618F));

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
        this.poseTranslateZ = 0.0F;

        this.Butt.z = this.buttDefaultZ;
        this.Hair01.xRot = this.hair01DefaultXRot;
        this.Hair01.zRot = this.hair01DefaultZRot;
        this.Hair02.xRot = this.hair02DefaultXRot;
        this.Hair02.zRot = this.hair02DefaultZRot;
        this.Hair03.xRot = this.hair03DefaultXRot;
        this.Hair03.zRot = this.hair03DefaultZRot;
        this.HairL01.xRot = this.hairL01DefaultXRot;
        this.HairL01.zRot = this.hairL01DefaultZRot;
        this.HairL02.xRot = this.hairL02DefaultXRot;
        this.HairL02.zRot = this.hairL02DefaultZRot;
        this.HairR01.xRot = this.hairR01DefaultXRot;
        this.HairR01.zRot = this.hairR01DefaultZRot;
        this.HairR02.xRot = this.hairR02DefaultXRot;
        this.HairR02.zRot = this.hairR02DefaultZRot;

        this.EquipHeadBack00.xRot = this.equipHeadBack00DefaultXRot;
        this.EquipT01a.xRot = this.equipT01aDefaultXRot;
        this.EquipT01b.xRot = this.equipT01bDefaultXRot;
        this.EquipT01c.xRot = this.equipT01cDefaultXRot;
        this.EquipT02a.xRot = this.equipT02aDefaultXRot;
        this.EquipT02b.xRot = this.equipT02bDefaultXRot;
        this.EquipT02c.xRot = this.equipT02cDefaultXRot;
        this.EquipT03a.xRot = this.equipT03aDefaultXRot;
        this.EquipT03b.xRot = this.equipT03bDefaultXRot;
        this.EquipT03c.xRot = this.equipT03cDefaultXRot;
        this.EquipT04a.xRot = this.equipT04aDefaultXRot;
        this.EquipT04b.xRot = this.equipT04bDefaultXRot;
        this.EquipT04c.xRot = this.equipT04cDefaultXRot;
        this.EquipT03a.zRot = this.equipT03aDefaultZRot;
        this.EquipT03b.zRot = this.equipT03bDefaultZRot;
        this.EquipT03c.zRot = this.equipT03cDefaultZRot;
        this.EquipT04a.zRot = this.equipT04aDefaultZRot;
        this.EquipT04b.zRot = this.equipT04bDefaultZRot;
        this.EquipT04c.zRot = this.equipT04cDefaultZRot;
        this.EquipS01a.xRot = this.equipS01aDefaultXRot;
        this.EquipS01b.xRot = this.equipS01bDefaultXRot;
        this.EquipS01c.xRot = this.equipS01cDefaultXRot;
        this.EquipS01d.xRot = this.equipS01dDefaultXRot;
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        boolean showEquip = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmYo.EQUIP_BASE);
        this.EquipBase.visible = showEquip;
        this.GlowEquipBase.visible = showEquip;
        this.Hair03.visible = showEquip;
        this.LegLeft01.visible = !showEquip;
        this.LegRight01.visible = !showEquip;
        this.Head.visible = true;
        this.GlowHead.visible = true;
        boolean showNormalBody = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmYo.EQUIP_NORMAL_BODY);
        this.BodyMain1.visible = showNormalBody;
        this.Butt1.visible = showNormalBody;
        this.BoobL.visible = showNormalBody;
        this.BoobR.visible = showNormalBody;
        this.BodyMain2.visible = !showNormalBody;
        this.Butt2.visible = !showNormalBody;
        this.BoobL2.visible = !showNormalBody;
        this.BoobR2.visible = !showNormalBody;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;
        this.poseTranslateZ = DEAD_TRANSLATE_Z;

        this.EquipBase.visible = true;
        this.GlowEquipBase.visible = true;

        this.Head.visible = false;
        this.GlowHead.visible = false;
        this.LegLeft01.visible = false;
        this.LegRight01.visible = false;

        this.BoobL.xRot = -0.76F;
        this.BoobR.xRot = -0.76F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.21F;
        this.Butt.z = this.buttDefaultZ;

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

        this.BodyMain.xRot = 0.2F;
        this.ArmLeft01.xRot = -0.25F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = 0.2618F;
        this.ArmRight01.xRot = -0.25F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -0.2618F;

        this.EquipHeadBack00.xRot = -0.15F;
        this.EquipT01a.xRot = 0.5F;
        this.EquipT01b.xRot = 0.5F;
        this.EquipT01c.xRot = 0.5F;
        this.EquipT02a.xRot = -0.7F;
        this.EquipT02b.xRot = -0.5F;
        this.EquipT02c.xRot = -0.5F;

        this.EquipT03a.xRot = 0.0F;
        this.EquipT03b.xRot = 0.0F;
        this.EquipT03c.xRot = 0.0F;
        this.EquipT04a.xRot = 0.0F;
        this.EquipT04b.xRot = 0.0F;
        this.EquipT04c.xRot = 0.0F;
        this.EquipT03a.zRot = 0.5F;
        this.EquipT03b.zRot = 0.6F;
        this.EquipT03c.zRot = 0.7F;
        this.EquipT04a.zRot = 0.3F;
        this.EquipT04b.zRot = 0.3F;
        this.EquipT04c.zRot = 0.3F;
        this.EquipS01a.xRot = 0.2F;
        this.EquipS01b.xRot = 0.3F;
        this.EquipS01c.xRot = 0.2F;
        this.EquipS01d.xRot = 0.5F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX4 = net.minecraft.util.Mth.cos(ageInTicks * 0.3F + 2.0F + limbSwing * 0.5F);
        float angleX5 = net.minecraft.util.Mth.cos(ageInTicks * 0.3F + 4.0F + limbSwing * 0.5F);
        float angleX6 = net.minecraft.util.Mth.cos(ageInTicks * 0.3F + 6.0F + limbSwing * 0.5F);
        float angleX7 = net.minecraft.util.Mth.sin(ageInTicks);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.7F;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY = angleX * 0.05F + 0.025F;
        }

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) * 0.8F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.57F;
        this.Head.zRot = 0.0F;

        this.BoobL.xRot = angleX * 0.08F - 0.76F;
        this.BoobR.xRot = angleX * 0.08F - 0.76F;
        this.Ahoke.yRot = angleX * 0.15F + 0.6F;

        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.21F;
        this.Butt.z = this.buttDefaultZ;

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

        boolean showEquip = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmYo.EQUIP_BASE);
        float addk1 = angleAdd1 * 0.6F - 0.157F;
        float addk2 = angleAdd2 * 0.6F - 0.035F;
        if (showEquip) {
            this.poseTranslateY += angleX * 0.035F + 0.1F;
            this.poseTranslateZ += -0.1F;
            this.Head.xRot -= 0.7F;
            this.BodyMain.xRot = 0.7F;

            this.ArmLeft01.xRot = -angleX * 0.1F - 1.0472F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = -angleX * 0.1F - 0.7F;
            this.ArmRight01.xRot = -angleX * 0.1F - 1.0472F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = angleX * 0.1F + 0.7F;

            this.EquipHeadBack00.xRot = angleX * 0.05F - 1.7F;
            this.EquipT01a.xRot = angleX6 * 0.22F + 0.5F;
            this.EquipT01b.xRot = angleX5 * 0.44F;
            this.EquipT01c.xRot = angleX4 * 0.66F;
            this.EquipT02a.xRot = -angleX6 * 0.22F;
            this.EquipT02b.xRot = -angleX5 * 0.44F;
            this.EquipT02c.xRot = -angleX4 * 0.66F;

            this.EquipT03a.xRot = 0.0F;
            this.EquipT03b.xRot = 0.0F;
            this.EquipT03c.xRot = 0.0F;
            this.EquipT04a.xRot = 0.0F;
            this.EquipT04b.xRot = 0.0F;
            this.EquipT04c.xRot = 0.0F;
            this.EquipT03a.zRot = angleX6 * 0.25F;
            this.EquipT03b.zRot = angleX5 * 0.5F;
            this.EquipT03c.zRot = angleX4 * 0.75F;
            this.EquipT04a.zRot = -angleX6 * 0.25F;
            this.EquipT04b.zRot = -angleX5 * 0.5F;
            this.EquipT04c.zRot = -angleX4 * 0.75F;

            float randSim = net.minecraft.util.Mth.sin(ageInTicks * 0.5F) * 0.5F + 0.5F;
            this.EquipS01a.xRot = angleX7 * 0.05F * randSim - 0.2618F;
            this.EquipS01b.xRot = angleX7 * 0.05F * randSim - 0.2618F;
            this.EquipS01c.xRot = -angleX7 * 0.05F * randSim + 0.2618F;
            this.EquipS01d.xRot = -angleX7 * 0.05F * randSim + 0.2618F;
            addk1 = 0.0F;
            addk2 = 0.0F;
        } else {
            this.Head.xRot += 0.1F;
            this.BodyMain.xRot = -0.1047F;
            this.ArmLeft01.xRot = 0.2094F;
            this.ArmLeft01.yRot = 0.0F;
            this.ArmLeft01.zRot = -angleX * 0.05F - 0.3142F;
            this.ArmRight01.xRot = 0.0F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = angleX * 0.05F + 0.2094F;
            this.LegLeft01.yRot = 0.0F;
            this.LegLeft01.zRot = 0.1F;
            this.LegRight01.yRot = 0.0F;
            this.LegRight01.zRot = -0.1F;
        }

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;

    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity != null ? entity.getIsSprinting() : limbSwingAmount > 0.92F;
        boolean isSitting = entity.getIsSitting() || isPassenger;
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.7F;

        if (isSprinting) {
            this.poseTranslateY += SPRINT_TRANSLATE_Y;
            this.Head.xRot += 0.6F;
            this.Head.xRot -= 1.1F;
            this.BodyMain.xRot = 1.1F;
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
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 1.0472F;
            this.BodyMain.xRot = 1.0472F;
            this.Butt.xRot = -0.8378F;
            this.Hair01.xRot -= 0.1F;
            this.Hair02.xRot -= 0.2F;
            this.Hair03.xRot -= 0.5F;
            this.ArmLeft01.xRot = -0.7F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.7F;
            this.ArmRight01.zRot = -0.2618F;
            this.LegLeft01.xRot -= 0.1F;
            this.LegRight01.xRot -= 0.1F;

            this.Head.xRot += 0.8F;
            this.ArmLeft01.xRot = -0.25F;
            this.ArmRight01.xRot = -0.25F;
            this.EquipHeadBack00.xRot += 0.4F;
        }

        if (isSitting && !isPassenger) {
            boolean showEquip = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmYo.EQUIP_BASE);
            float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
            if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY -= angleX * 0.05F;
                this.Head.xRot *= 0.5F;
                this.Head.yRot *= 0.75F;
                this.Head.xRot += 0.5F;
                this.BodyMain.xRot = 1.6F;
                this.ArmLeft01.xRot = -1.6F;
                this.ArmLeft01.zRot = -2.3F;
                this.ArmRight01.xRot = -1.6F;
                this.ArmRight01.zRot = 2.3F;
                this.LegLeft01.xRot = -1.6F;
                this.LegRight01.xRot = -1.6F;
                this.LegLeft01.yRot = -0.1F - angleX * 0.05F;
                this.LegRight01.yRot = 0.1F + angleX * 0.05F;
                if (showEquip) {
                    this.poseTranslateY += 0.36F;
                    float ax = net.minecraft.util.Mth.cos(ageInTicks * 0.5F) * 0.5F;
                    this.ArmLeft01.xRot = ax + 0.1F;
                    this.ArmRight01.xRot = -ax + 0.1F;
                    this.EquipHeadBack00.xRot = ax * 0.1F - 0.7F;
                }
            } else {
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
                if (showEquip) {
                    this.Head.xRot += 0.7F;
                    this.BodyMain.xRot = 0.3F;
                    this.ArmLeft01.xRot = -0.27F;
                    this.ArmLeft01.zRot = 0.3146F;
                    this.ArmRight01.xRot = -0.27F;
                    this.ArmRight01.zRot = -0.3146F;
                    this.EquipHeadBack00.xRot += 0.45F;
                } else {
                    this.poseTranslateY += 0.45F * 3;
                }
            }
        }

        if (entity != null && entity.getAttackTick() > 41) {
            float ft = (50 - entity.getAttackTick()) + (ageInTicks - (int) ageInTicks);
            float fa = net.minecraft.util.Mth.sin((ft *= 0.125F) * ft * (float) Math.PI);
            float fb = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(ft) * (float) Math.PI);
            this.ArmLeft01.xRot += -fb * 80.0F * ((float) Math.PI / 180F) - 0.3F;
            this.ArmLeft01.yRot += fa * 20.0F * ((float) Math.PI / 180F) - 0.4F;
            this.ArmLeft01.zRot += fb * 20.0F * ((float) Math.PI / 180F);
            this.ArmRight01.xRot += -fb * 80.0F * ((float) Math.PI / 180F) - 0.3F;
            this.ArmRight01.yRot += -fa * 20.0F * ((float) Math.PI / 180F) + 0.4F;
            this.ArmRight01.zRot += -fb * 20.0F * ((float) Math.PI / 180F);
        }

        applyHairMotion(limbSwing, ageInTicks);

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F) - 0.3F;
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.4F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }
    }

    private void applyHairMotion(float limbSwing, float ageInTicks) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.3F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.6F + limbSwing * 0.5F);
        float angleX3 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.9F + limbSwing * 0.5F);

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

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowHead.copyFrom(this.Head);
        this.GlowEquipHeadBack00.copyFrom(this.EquipHeadBack00);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F || this.poseTranslateZ != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, this.poseTranslateZ);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F || this.poseTranslateZ != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, this.poseTranslateZ);
        }

        this.GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
