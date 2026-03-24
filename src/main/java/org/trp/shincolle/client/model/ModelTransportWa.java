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

public class ModelTransportWa<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "transport_wa"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelTransportWa");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelTransportWa");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelTransportWa");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart Butt;
    private final ModelPart Head;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Cloth03;
    private final ModelPart EquipBase;
    private final ModelPart Cloth01b;
    private final ModelPart Cloth01a;
    private final ModelPart Cloth2b;
    private final ModelPart Cloth2a;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart LegLeft02;
    private final ModelPart ClothLeg;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart EquipHeadBase;
    private final ModelPart Ahoke;
    private final ModelPart HairU01;
    private final ModelPart Hair01;
    private final ModelPart ClothHead;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead04;
    private final ModelPart EquipHead05;
    private final ModelPart EquipHead06;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart Cloth04;
    private final ModelPart Cloth00a;
    private final ModelPart Cloth00b;
    private final ModelPart Cloth00c;
    private final ModelPart Cloth00d;
    private final ModelPart EquipBack01a;
    private final ModelPart EquipBack01b;
    private final ModelPart EquipBack01c;
    private final ModelPart EquipBack01d;
    private final ModelPart EquipBack01e;
    private final ModelPart EquipBack01f;
    private final ModelPart EquipBack01g;
    private final ModelPart EquipBack01h;
    private final ModelPart EquipBack01i;
    private final ModelPart EquipBack01j;
    private final ModelPart EquipBack01k;
    private final ModelPart EquipBack01l;
    private final ModelPart EquipBack01m;
    private final ModelPart EquipBack01n;
    private final ModelPart EquipBack01o;
    private final ModelPart EquipBack01p;
    private final ModelPart EquipBack01q;
    private final ModelPart EquipBack01r;
    private final ModelPart EquipTubeR01;
    private final ModelPart EquipTubeL01;
    private final ModelPart EquipBack01s;
    private final ModelPart EquipBack01t;
    private final ModelPart EquipBack01u;
    private final ModelPart EquipBack01v;
    private final ModelPart EquipBack01w;
    private final ModelPart EquipBack01x;
    private final ModelPart EquipBack01y;
    private final ModelPart EquipBack01z;
    private final ModelPart EquipBack01za;
    private final ModelPart EquipBack01zb;
    private final ModelPart EquipBack01zc;
    private final ModelPart EquipBack01zd;
    private final ModelPart EquipTubeR02;
    private final ModelPart EquipTubeR03;
    private final ModelPart EquipTubeL02;
    private final ModelPart EquipTubeL03;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowBodyMain2;
    private final ModelPart GlowHead;
    private final ModelPart GlowEquipBase;
    private final ModelPart GlowEquipTubeL01;
    private final ModelPart GlowEquipTubeL02;
    private final ModelPart GlowEquipTubeR01;
    private final ModelPart GlowEquipTubeR02;
    private final float buttDefaultXRot;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float cloth03DefaultXRot;
    private final float cloth04DefaultXRot;
    private final float armLeft01DefaultXRot;
    private final float armLeft01DefaultYRot;
    private final float armLeft01DefaultZRot;
    private final float armRight01DefaultXRot;
    private final float armRight01DefaultYRot;
    private final float armRight01DefaultZRot;
    private final float legLeft01DefaultXRot;
    private final float legLeft01DefaultYRot;
    private final float legLeft01DefaultZRot;
    private final float legRight01DefaultXRot;
    private final float legRight01DefaultYRot;
    private final float legRight01DefaultZRot;
    private final float equipBaseDefaultXRot;
    private final float equipBaseDefaultY;
    private final float equipBaseDefaultZ;
    private final float equipTubeL01DefaultXRot;
    private final float equipTubeR01DefaultXRot;

    public ModelTransportWa(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Head = this.BodyMain.getChild("Head");
        this.EquipHeadBase = this.Head.getChild("EquipHeadBase");
        this.EquipHead03 = this.EquipHeadBase.getChild("EquipHead03");
        this.EquipHead04 = this.EquipHeadBase.getChild("EquipHead04");
        this.EquipHead05 = this.EquipHeadBase.getChild("EquipHead05");
        this.EquipHead06 = this.EquipHeadBase.getChild("EquipHead06");
        this.EquipHead02 = this.EquipHeadBase.getChild("EquipHead02");
        this.EquipHead01 = this.EquipHeadBase.getChild("EquipHead01");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.ClothHead = this.HairMain.getChild("ClothHead");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.Cloth01b = this.BodyMain.getChild("Cloth01b");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ClothLeg = this.LegLeft01.getChild("ClothLeg");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.Cloth01a = this.BodyMain.getChild("Cloth01a");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipBack01t = this.EquipBase.getChild("EquipBack01t");
        this.EquipBack01m = this.EquipBase.getChild("EquipBack01m");
        this.EquipBack01v = this.EquipBase.getChild("EquipBack01v");
        this.EquipBack01j = this.EquipBase.getChild("EquipBack01j");
        this.EquipBack01q = this.EquipBase.getChild("EquipBack01q");
        this.EquipBack01x = this.EquipBase.getChild("EquipBack01x");
        this.EquipBack01zb = this.EquipBase.getChild("EquipBack01zb");
        this.EquipBack01n = this.EquipBase.getChild("EquipBack01n");
        this.EquipBack01a = this.EquipBase.getChild("EquipBack01a");
        this.EquipBack01p = this.EquipBase.getChild("EquipBack01p");
        this.EquipBack01i = this.EquipBase.getChild("EquipBack01i");
        this.EquipBack01d = this.EquipBase.getChild("EquipBack01d");
        this.EquipBack01w = this.EquipBase.getChild("EquipBack01w");
        this.EquipBack01o = this.EquipBase.getChild("EquipBack01o");
        this.EquipTubeR01 = this.EquipBase.getChild("EquipTubeR01");
        this.EquipTubeR02 = this.EquipTubeR01.getChild("EquipTubeR02");
        this.EquipBack01g = this.EquipBase.getChild("EquipBack01g");
        this.EquipTubeL01 = this.EquipBase.getChild("EquipTubeL01");
        this.EquipTubeL02 = this.EquipTubeL01.getChild("EquipTubeL02");
        this.EquipBack01zc = this.EquipBase.getChild("EquipBack01zc");
        this.EquipBack01b = this.EquipBase.getChild("EquipBack01b");
        this.EquipBack01e = this.EquipBase.getChild("EquipBack01e");
        this.EquipBack01h = this.EquipBase.getChild("EquipBack01h");
        this.EquipBack01s = this.EquipBase.getChild("EquipBack01s");
        this.EquipBack01r = this.EquipBase.getChild("EquipBack01r");
        this.EquipBack01f = this.EquipBase.getChild("EquipBack01f");
        this.EquipBack01k = this.EquipBase.getChild("EquipBack01k");
        this.EquipBack01l = this.EquipBase.getChild("EquipBack01l");
        this.EquipBack01za = this.EquipBase.getChild("EquipBack01za");
        this.EquipBack01z = this.EquipBase.getChild("EquipBack01z");
        this.EquipBack01zd = this.EquipBase.getChild("EquipBack01zd");
        this.EquipBack01u = this.EquipBase.getChild("EquipBack01u");
        this.EquipBack01y = this.EquipBase.getChild("EquipBack01y");
        this.EquipBack01c = this.EquipBase.getChild("EquipBack01c");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.Cloth03 = this.BodyMain.getChild("Cloth03");
        this.Cloth00b = this.Cloth03.getChild("Cloth00b");
        this.Cloth04 = this.Cloth03.getChild("Cloth04");
        this.Cloth00d = this.Cloth03.getChild("Cloth00d");
        this.Cloth00a = this.Cloth03.getChild("Cloth00a");
        this.Cloth00c = this.Cloth03.getChild("Cloth00c");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Cloth2a = this.BoobL.getChild("Cloth2a");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Cloth2b = this.BoobR.getChild("Cloth2b");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.GlowBodyMain2 = root.getChild("GlowBodyMain2");
        this.GlowEquipBase = this.GlowBodyMain2.getChild("GlowEquipBase");
        this.GlowEquipTubeL01 = this.GlowEquipBase.getChild("GlowEquipTubeL01");
        this.GlowEquipTubeL02 = this.GlowEquipTubeL01.getChild("GlowEquipTubeL02");
        this.EquipTubeL03 = this.GlowEquipTubeL02.getChild("EquipTubeL03");
        this.GlowEquipTubeR01 = this.GlowEquipBase.getChild("GlowEquipTubeR01");
        this.GlowEquipTubeR02 = this.GlowEquipTubeR01.getChild("GlowEquipTubeR02");
        this.EquipTubeR03 = this.GlowEquipTubeR02.getChild("EquipTubeR03");
        this.buttDefaultXRot = this.Butt.xRot;
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.cloth03DefaultXRot = this.Cloth03.xRot;
        this.cloth04DefaultXRot = this.Cloth04.xRot;
        this.armLeft01DefaultXRot = this.ArmLeft01.xRot;
        this.armLeft01DefaultYRot = this.ArmLeft01.yRot;
        this.armLeft01DefaultZRot = this.ArmLeft01.zRot;
        this.armRight01DefaultXRot = this.ArmRight01.xRot;
        this.armRight01DefaultYRot = this.ArmRight01.yRot;
        this.armRight01DefaultZRot = this.ArmRight01.zRot;
        this.legLeft01DefaultXRot = this.LegLeft01.xRot;
        this.legLeft01DefaultYRot = this.LegLeft01.yRot;
        this.legLeft01DefaultZRot = this.LegLeft01.zRot;
        this.legRight01DefaultXRot = this.LegRight01.xRot;
        this.legRight01DefaultYRot = this.LegRight01.yRot;
        this.legRight01DefaultZRot = this.LegRight01.zRot;
        this.equipBaseDefaultXRot = this.EquipBase.xRot;
        this.equipBaseDefaultY = this.EquipBase.y;
        this.equipBaseDefaultZ = this.EquipBase.z;
        this.equipTubeL01DefaultXRot = this.EquipTubeL01.xRot;
        this.equipTubeR01DefaultXRot = this.EquipTubeR01.xRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 105).addBox(-6.5F, -11F, -4F, 13F, 16F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11F, -3F, -0.1047F, 0F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.8F, -1F, 0.1047F, 0F, 0F));

        PartDefinition EquipHeadBase = Head.addOrReplaceChild("EquipHeadBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -13.8F, 0F));

        PartDefinition EquipHead03 = EquipHeadBase.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, -9F, 16F, 10F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.3F, -7F, 0.2618F, 0F, 0F));

        PartDefinition EquipHead04 = EquipHeadBase.addOrReplaceChild("EquipHead04", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 16F, 9F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.2F, -2.8F, -0.5009F, -0.7213F, 0.3449F));

        PartDefinition EquipHead05 = EquipHeadBase.addOrReplaceChild("EquipHead05", CubeListBuilder.create().texOffs(0, 63).addBox(-7F, 0F, 0F, 14F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.3F, -12F, 0.3142F, 0F, 0F));

        PartDefinition EquipHead06 = EquipHeadBase.addOrReplaceChild("EquipHead06", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipHead02 = EquipHeadBase.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(0, 0).addBox(-15F, 0F, 0F, 15F, 9F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, -3F, -12F, 0F, 0.3491F, -0.2094F));

        PartDefinition EquipHead01 = EquipHeadBase.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 15F, 9F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, -3F, -12F, 0F, -0.3491F, 0.2094F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(47, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 32).addBox(-7.5F, 0F, 0F, 15F, 9F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.2F, 1.1F, 0.0873F, 0F, 0F));

        PartDefinition ClothHead = HairMain.addOrReplaceChild("ClothHead", CubeListBuilder.create().texOffs(48, 0).addBox(-8F, 0F, 0F, 16F, 10F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.1F, 1.5F, -0.0698F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.4F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(50, 45).addBox(-8.5F, 0F, 0F, 17F, 14F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -6.5F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -12F, -6.5F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, -4.5F, 1.2F, 0.6981F, 0F));

        PartDefinition Cloth01b = BodyMain.addOrReplaceChild("Cloth01b", CubeListBuilder.create().texOffs(96, 19).mirror().addBox(-4F, 0F, -4F, 8F, 14F, 8F, new CubeDeformation(0F)), PartPose.offset(-5.6F, -11.6F, -0.6F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(52, 66).addBox(-7.5F, 0F, -5.7F, 15F, 7F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, 1.3F, 0.3142F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 83).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.4F, 5.5F, -2.6F, -0.2443F, 0F, 0.1047F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 83).mirror().addBox(0F, 0F, 0F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 12F, -3F));

        PartDefinition ClothLeg = LegLeft01.addOrReplaceChild("ClothLeg", CubeListBuilder.create().texOffs(30, 78).addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 83).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.4F, 5.5F, -2.6F, -0.1396F, 0F, -0.1047F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 83).addBox(-6F, 0F, 0F, 6F, 13F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 12F, -3F));

        PartDefinition Cloth01a = BodyMain.addOrReplaceChild("Cloth01a", CubeListBuilder.create().texOffs(96, 19).addBox(-4F, 0F, -4F, 8F, 14F, 8F, new CubeDeformation(0F)), PartPose.offset(5.6F, -11.6F, -0.6F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.5F, 7.5F, 0.0524F, 0F, 0F));

        PartDefinition EquipBack01t = EquipBase.addOrReplaceChild("EquipBack01t", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, 32F));

        PartDefinition EquipBack01m = EquipBase.addOrReplaceChild("EquipBack01m", CubeListBuilder.create().texOffs(21, 6).addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(16F, -10F, 16F));

        PartDefinition EquipBack01v = EquipBase.addOrReplaceChild("EquipBack01v", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 10F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-10F, 0F, 32F));

        PartDefinition EquipBack01j = EquipBase.addOrReplaceChild("EquipBack01j", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(-16F, -16F, 16F));

        PartDefinition EquipBack01q = EquipBase.addOrReplaceChild("EquipBack01q", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(-22F, 0F, 16F));

        PartDefinition EquipBack01x = EquipBase.addOrReplaceChild("EquipBack01x", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -22F, 16F));

        PartDefinition EquipBack01zb = EquipBase.addOrReplaceChild("EquipBack01zb", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(-10F, 16F, 6F));

        PartDefinition EquipBack01n = EquipBase.addOrReplaceChild("EquipBack01n", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(16F, 0F, 16F));

        PartDefinition EquipBack01a = EquipBase.addOrReplaceChild("EquipBack01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(0F, -16F, 0F));

        PartDefinition EquipBack01p = EquipBase.addOrReplaceChild("EquipBack01p", CubeListBuilder.create().texOffs(7, 6).addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(-22F, -10F, 6F));

        PartDefinition EquipBack01i = EquipBase.addOrReplaceChild("EquipBack01i", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(-16F, 0F, 16F));

        PartDefinition EquipBack01d = EquipBase.addOrReplaceChild("EquipBack01d", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(-16F, 0F, 0F));

        PartDefinition EquipBack01w = EquipBase.addOrReplaceChild("EquipBack01w", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -22F, 6F));

        PartDefinition EquipBack01o = EquipBase.addOrReplaceChild("EquipBack01o", CubeListBuilder.create().texOffs(26, 12).addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(-22F, 0F, 6F));

        PartDefinition EquipTubeR01 = EquipBase.addOrReplaceChild("EquipTubeR01", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-18F, 3F, 28F, -0.3491F, 0.1396F, 0.1396F));

        PartDefinition EquipTubeR02 = EquipTubeR01.addOrReplaceChild("EquipTubeR02", CubeListBuilder.create().texOffs(10, 0).addBox(-4.5F, 0F, -8.5F, 9F, 16F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 16F, 4F, -0.9561F, 0F, 0F));

        PartDefinition EquipBack01g = EquipBase.addOrReplaceChild("EquipBack01g", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 16F));

        PartDefinition EquipTubeL01 = EquipBase.addOrReplaceChild("EquipTubeL01", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(18F, 3F, 28F, -0.3491F, -0.1396F, -0.1396F));

        PartDefinition EquipTubeL02 = EquipTubeL01.addOrReplaceChild("EquipTubeL02", CubeListBuilder.create().texOffs(12, 0).addBox(-4.5F, 0F, -8.5F, 9F, 16F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 16F, 4F, -0.9561F, 0F, 0F));

        PartDefinition EquipBack01zc = EquipBase.addOrReplaceChild("EquipBack01zc", CubeListBuilder.create().texOffs(7, 13).mirror().addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(-10F, 16F, 16F));

        PartDefinition EquipBack01b = EquipBase.addOrReplaceChild("EquipBack01b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(-16F, -16F, 0F));

        PartDefinition EquipBack01e = EquipBase.addOrReplaceChild("EquipBack01e", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10F, 16F, 4F, new CubeDeformation(0F)), PartPose.offset(-10F, -6F, -4F));

        PartDefinition EquipBack01h = EquipBase.addOrReplaceChild("EquipBack01h", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(0F, -16F, 16F));

        PartDefinition EquipBack01s = EquipBase.addOrReplaceChild("EquipBack01s", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 32F));

        PartDefinition EquipBack01r = EquipBase.addOrReplaceChild("EquipBack01r", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(-22F, -10F, 16F));

        PartDefinition EquipBack01f = EquipBase.addOrReplaceChild("EquipBack01f", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 10F, 16F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -4F));

        PartDefinition EquipBack01k = EquipBase.addOrReplaceChild("EquipBack01k", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(16F, 0F, 6F));

        PartDefinition EquipBack01l = EquipBase.addOrReplaceChild("EquipBack01l", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(0F, 0F, 0F, 6F, 10F, 10F, new CubeDeformation(0F)), PartPose.offset(16F, -10F, 6F));

        PartDefinition EquipBack01za = EquipBase.addOrReplaceChild("EquipBack01za", CubeListBuilder.create().texOffs(0, 16).addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 6F));

        PartDefinition EquipBack01z = EquipBase.addOrReplaceChild("EquipBack01z", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(-10F, -22F, 6F));

        PartDefinition EquipBack01zd = EquipBase.addOrReplaceChild("EquipBack01zd", CubeListBuilder.create().texOffs(0, 6).addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 16F, 16F));

        PartDefinition EquipBack01u = EquipBase.addOrReplaceChild("EquipBack01u", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 10F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(-10F, -10F, 32F));

        PartDefinition EquipBack01y = EquipBase.addOrReplaceChild("EquipBack01y", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offset(-10F, -22F, 16F));

        PartDefinition EquipBack01c = EquipBase.addOrReplaceChild("EquipBack01c", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 16F, 16F, 16F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(2, 84).mirror().addBox(-2F, -1F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.7F, -0.7F, 0F, 0F, -0.2094F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(2, 84).mirror().addBox(-5F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 10F, 2.5F));

        PartDefinition Cloth03 = BodyMain.addOrReplaceChild("Cloth03", CubeListBuilder.create().texOffs(58, 32).addBox(-7F, 0F, -4.7F, 14F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 1.3F, -0.5F, 0.1745F, 0F, 0.0873F));

        PartDefinition Cloth00b = Cloth03.addOrReplaceChild("Cloth00b", CubeListBuilder.create().texOffs(19, 79).addBox(-7F, -2F, 0F, 7F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 3.3F, -0.2094F, 0.2618F, 0.1745F));

        PartDefinition Cloth04 = Cloth03.addOrReplaceChild("Cloth04", CubeListBuilder.create().texOffs(70, 21).addBox(-6.5F, 0F, 0F, 13F, 11F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, -4.7F, -0.2094F, 0F, -0.0873F));

        PartDefinition Cloth00d = Cloth03.addOrReplaceChild("Cloth00d", CubeListBuilder.create().texOffs(88, 101).addBox(-2.5F, 0F, 0F, 5F, 12F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.8F, 1F, 4.5F, 0.3491F, -0.1396F, 0.3142F));

        PartDefinition Cloth00a = Cloth03.addOrReplaceChild("Cloth00a", CubeListBuilder.create().texOffs(19, 79).mirror().addBox(0F, -2F, 0F, 7F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 0.5F, 3.5F, -0.0911F, -0.2618F, -0.1745F));

        PartDefinition Cloth00c = Cloth03.addOrReplaceChild("Cloth00c", CubeListBuilder.create().texOffs(88, 101).mirror().addBox(-2.5F, 0F, 0F, 5F, 12F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.3F, 1F, 4.5F, 0.3142F, 0.1396F, -0.3491F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.1F, -8.5F, -3.7F, -0.6981F, 0.0785F, 0.0785F));

        PartDefinition Cloth2a = BoobL.addOrReplaceChild("Cloth2a", CubeListBuilder.create().texOffs(26, 89).addBox(0F, 0F, 0F, 5F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.2F, -0.5F, -0.7F, 0F, -0.0785F, -0.0785F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(2, 84).addBox(-3F, -1F, -2.5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.7F, -0.7F, 0.2094F, 0F, 0.2094F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(2, 84).addBox(0F, 0F, -5F, 5F, 11F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 10F, 2.5F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.1F, -8.5F, -3.7F, -0.6981F, -0.0785F, -0.0785F));

        PartDefinition Cloth2b = BoobR.addOrReplaceChild("Cloth2b", CubeListBuilder.create().texOffs(26, 89).mirror().addBox(-5F, 0F, 0F, 5F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.2F, -0.5F, -0.7F, 0F, 0.0785F, 0.0785F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 105), PartPose.offset(0F, -11F, -3F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(44, 101), PartPose.offset(0F, -11.8F, -1F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowBodyMain2 = partdefinition.addOrReplaceChild("GlowBodyMain2", CubeListBuilder.create().texOffs(0, 105), PartPose.offset(0F, -11F, -3F));

        PartDefinition GlowEquipBase = GlowBodyMain2.addOrReplaceChild("GlowEquipBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -4.5F, 7.5F));

        PartDefinition GlowEquipTubeL01 = GlowEquipBase.addOrReplaceChild("GlowEquipTubeL01", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(18F, 3F, 28F));

        PartDefinition GlowEquipTubeL02 = GlowEquipTubeL01.addOrReplaceChild("GlowEquipTubeL02", CubeListBuilder.create().texOffs(12, 0), PartPose.offsetAndRotation(0F, 16F, 4F, -0.9561F, 0F, 0F));

        PartDefinition EquipTubeL03 = GlowEquipTubeL02.addOrReplaceChild("EquipTubeL03", CubeListBuilder.create().texOffs(92, 0).addBox(-4.5F, 0F, -4.5F, 9F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 16.1F, -4F));

        PartDefinition GlowEquipTubeR01 = GlowEquipBase.addOrReplaceChild("GlowEquipTubeR01", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(-18F, 3F, 28F));

        PartDefinition GlowEquipTubeR02 = GlowEquipTubeR01.addOrReplaceChild("GlowEquipTubeR02", CubeListBuilder.create().texOffs(10, 0), PartPose.offsetAndRotation(0F, 16F, 4F, -0.9561F, 0F, 0F));

        PartDefinition EquipTubeR03 = GlowEquipTubeR02.addOrReplaceChild("EquipTubeR03", CubeListBuilder.create().texOffs(92, 0).addBox(-4.5F, 0F, -4.5F, 9F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 16.1F, -4F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        this.applyFaceAndMouth(entity);

        boolean inDeadPose = entity != null && entity.isInDeadPose();

        if (inDeadPose) {
            this.applyDeadPose(entity);
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
        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.Cloth03.xRot = this.cloth03DefaultXRot;
        this.Cloth04.xRot = this.cloth04DefaultXRot;
        this.ArmLeft01.xRot = this.armLeft01DefaultXRot;
        this.ArmLeft01.yRot = this.armLeft01DefaultYRot;
        this.ArmLeft01.zRot = this.armLeft01DefaultZRot;
        this.ArmRight01.xRot = this.armRight01DefaultXRot;
        this.ArmRight01.yRot = this.armRight01DefaultYRot;
        this.ArmRight01.zRot = this.armRight01DefaultZRot;
        this.LegLeft01.xRot = this.legLeft01DefaultXRot;
        this.LegLeft01.yRot = this.legLeft01DefaultYRot;
        this.LegLeft01.zRot = this.legLeft01DefaultZRot;
        this.LegRight01.xRot = this.legRight01DefaultXRot;
        this.LegRight01.yRot = this.legRight01DefaultYRot;
        this.LegRight01.zRot = this.legRight01DefaultZRot;
        this.EquipBase.xRot = this.equipBaseDefaultXRot;
        this.EquipBase.y = this.equipBaseDefaultY;
        this.EquipBase.z = this.equipBaseDefaultZ;
        this.EquipTubeL01.xRot = this.equipTubeL01DefaultXRot;
        this.EquipTubeR01.xRot = this.equipTubeR01DefaultXRot;
    }

    private void applyEquipVisibility(T entity) {
        boolean showEquipBase = entity.getEquipFlag(org.trp.shincolle.entity.EntityTransportWa.EQUIP_BASE);
        boolean showLeg = entity.getEquipFlag(org.trp.shincolle.entity.EntityTransportWa.EQUIP_LEG);
        boolean showHeadBase = entity.getEquipFlag(org.trp.shincolle.entity.EntityTransportWa.EQUIP_HEAD_BASE);
        boolean showEquipBaseWithLeg = showEquipBase || !showLeg;

        this.EquipBase.visible = showEquipBaseWithLeg;
        this.GlowEquipBase.visible = showEquipBaseWithLeg;
        this.LegLeft01.visible = showLeg;
        this.LegRight01.visible = showLeg;
        this.EquipHeadBase.visible = showHeadBase;
        this.Ahoke.visible = !showHeadBase;
    }

    private void applyDeadPose(T entity) {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;
        boolean showLeg = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntityTransportWa.EQUIP_LEG);

        this.Head.xRot = 0.3F;
        this.Head.yRot = 0.0F;
        this.BoobL.xRot = -0.75F;
        this.BoobR.xRot = -0.75F;
        this.Ahoke.yRot = 0.7F;
        this.BodyMain.xRot = 2.8F;
        this.Cloth03.xRot = 0.17F;
        this.Cloth04.xRot = -0.8F;
        this.Butt.xRot = showLeg ? -0.8F : -1.1F;
        this.Butt.z = this.buttDefaultZ + ((showLeg ? 0.0F : 0.1F) * OFFSET_SCALE);

        this.ArmLeft01.xRot = -0.35F;
        this.ArmLeft01.zRot = -2.6F;
        this.ArmRight01.xRot = -0.35F;
        this.ArmRight01.zRot = 2.6F;

        this.LegLeft01.xRot = showLeg ? -1.0F : -0.24F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.1047F;
        this.LegRight01.xRot = showLeg ? -0.9F : -0.14F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.1047F;

        this.EquipBase.y = this.equipBaseDefaultY + ((showLeg ? 0.2F : 0.45F) * OFFSET_SCALE);
        this.EquipBase.z = this.equipBaseDefaultZ + ((showLeg ? -0.45F : -0.85F) * OFFSET_SCALE);
        this.EquipBase.xRot = showLeg ? -2.2F : -3.1F;
        this.EquipTubeL01.xRot = showLeg ? 0.1F : -0.3F;
        this.EquipTubeR01.xRot = showLeg ? 0.1F : -0.3F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + limbSwing * 0.25F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY = angleX * 0.05F + 0.025F;
        }

        float addk1 = angleAdd1 * 0.5F - 0.24F;
        float addk2 = angleAdd2 * 0.5F - 0.14F;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) + 0.1047F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = entity != null ? entity.getHeadTiltAngle(ageInTicks) : 0.0F;

        this.BoobL.xRot = angleX * 0.05F - 0.75F;
        this.BoobR.xRot = angleX * 0.05F - 0.75F;
        this.Ahoke.yRot = angleX * 0.25F + 0.7F;

        this.BodyMain.xRot = -0.1047F;
        this.Butt.xRot = 0.3142F;
        this.Cloth03.xRot = 0.1745F;
        this.Cloth04.xRot = angleX * 0.05F - 0.15F;

        this.ArmLeft01.xRot = angleAdd2 * 0.25F + 0.21F;
        this.ArmLeft01.zRot = angleX * 0.03F - 0.21F;
        this.ArmRight01.xRot = angleAdd1 * 0.25F + 0.05F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -angleX * 0.03F + 0.21F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.1047F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.1047F;

        this.EquipBase.xRot = 0.05236F;
        this.EquipTubeL01.xRot = angleX * 0.08F - 0.35F;
        this.EquipTubeR01.xRot = -angleX * 0.08F - 0.35F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        boolean hideLeg = entity != null && !entity.getEquipFlag(org.trp.shincolle.entity.EntityTransportWa.EQUIP_LEG);
        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity != null ? entity.getIsSprinting() : limbSwingAmount > 0.9F;
        boolean isSitting = entity.getIsSitting() || isPassenger;
        float sitSwing = net.minecraft.util.Mth.cos(ageInTicks * 0.5F) * 0.5F;

        if (hideLeg) {
            this.Cloth04.xRot += 0.23F;
            this.Butt.xRot = 0.7F;
            this.Butt.y = this.buttDefaultY + (-0.1F * OFFSET_SCALE);
            this.Butt.z = this.buttDefaultZ + (-0.05F * OFFSET_SCALE);
            this.ArmLeft01.xRot += 0.2F;
            this.ArmLeft01.zRot -= 0.3F;
            this.ArmRight01.xRot += 0.2F;
            this.ArmRight01.zRot += 0.3F;
            this.EquipBase.xRot = -0.4F;
            this.EquipTubeL01.xRot += 0.35F;
            this.EquipTubeR01.xRot += 0.35F;
        }

        if (isSprinting) {
            this.poseTranslateY += 0.1F;
            this.Head.xRot -= 0.2F;
            this.BodyMain.xRot = 0.35F;
            this.Cloth04.xRot -= 0.4F;
            this.ArmLeft01.zRot -= 0.2F + limbSwingAmount * 0.25F;
            this.ArmRight01.zRot += 0.2F + limbSwingAmount * 0.25F;
            this.LegLeft01.xRot -= 0.45F;
            this.LegRight01.xRot -= 0.45F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 1.0472F;
            this.BodyMain.xRot = 1.0472F;
            this.Butt.xRot = hideLeg ? 0.8F : -0.8378F;
            this.Cloth03.xRot -= 0.7F;
            this.Cloth04.xRot -= 0.45F;
            this.ArmLeft01.xRot = -0.7F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.7F;
            this.ArmRight01.zRot = -0.2618F;
        }

        if (isSitting) {
            if (hideLeg) {
                if (entity != null && hasLegacyState(entity, 1, 4)) {
                    this.poseTranslateY += 0.54F;
                    this.Head.xRot = -0.9F;
                    this.Head.yRot = 0.0F;
                    this.Head.zRot = 0.0F;
                    this.Ahoke.yRot = 0.5236F;
                    this.BodyMain.xRot = 1.4835F;
                    this.ArmLeft01.xRot = sitSwing + 0.25F;
                    this.ArmLeft01.zRot = -2.3F;
                    this.ArmRight01.xRot = -sitSwing + 0.25F;
                    this.ArmRight01.zRot = 2.3F;
                    this.LegLeft01.yRot = 0.0F;
                    this.LegLeft01.zRot = 0.03F;
                    this.LegRight01.yRot = 0.0F;
                    this.LegRight01.zRot = -0.03F;
                } else {
                    this.poseTranslateY += -0.17F;
                    this.Head.xRot = -0.7F;
                    this.Head.yRot = 0.0F;
                    this.Head.zRot = 0.0F;
                    this.Ahoke.yRot = 0.5236F;
                    this.BodyMain.xRot = -1.7453F;
                    this.Cloth04.xRot = 0.4F;
                    this.ArmLeft01.xRot = 0.85F;
                    this.ArmLeft01.zRot = -2.3F;
                    this.ArmRight01.xRot = 0.85F;
                    this.ArmRight01.zRot = 2.3F;
                    this.LegLeft01.yRot = 0.0F;
                    this.LegLeft01.zRot = 0.03F;
                    this.LegRight01.yRot = 0.0F;
                    this.LegRight01.zRot = -0.03F;
                }
            } else if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.53F * 3.2F;
                this.Head.xRot = -0.7F;
                this.Head.yRot = 0.0F;
                this.Head.zRot = 0.0F;
                this.Ahoke.yRot = 0.5236F;
                this.BodyMain.xRot = 1.4835F;
                this.ArmLeft01.xRot = sitSwing + 0.25F;
                this.ArmLeft01.zRot = -2.3F;
                this.ArmRight01.xRot = -sitSwing + 0.25F;
                this.ArmRight01.zRot = 2.3F;
                this.LegLeft01.xRot = -sitSwing + 0.2F;
                this.LegRight01.xRot = sitSwing + 0.2F;
                this.LegLeft01.yRot = 0.0F;
                this.LegLeft01.zRot = 0.03F;
                this.LegRight01.yRot = 0.0F;
                this.LegRight01.zRot = -0.03F;
                this.EquipTubeL01.xRot = 1.3F;
                this.EquipTubeR01.xRot = 1.3F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                this.Head.xRot -= 0.7F;
                this.BodyMain.xRot = 0.5236F;
                this.ArmLeft01.xRot = -0.5236F;
                this.ArmLeft01.zRot = 0.3146F;
                this.ArmRight01.xRot = -0.5236F;
                this.ArmRight01.zRot = -0.3146F;
                this.LegLeft01.xRot = -2.2689F;
                this.LegRight01.xRot = -2.2689F;
                this.LegLeft01.yRot = -0.3491F;
                this.LegRight01.yRot = 0.3491F;
                this.EquipBase.xRot = -0.4F;
                this.EquipTubeL01.xRot = 0.9F;
                this.EquipTubeR01.xRot = 0.9F;
            }
        }

        if (entity != null && entity.getAttackTick() > 40) {
            this.poseTranslateY += 0.08F;
            this.Head.xRot -= 1.0472F;
            this.BodyMain.xRot = 1.7F;
            this.Butt.xRot = -0.8378F;
            this.Cloth03.xRot -= 0.7F;
            this.Cloth04.xRot -= 1.1F;
            this.ArmLeft01.xRot = -0.9F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -1.9F;
            this.ArmRight01.zRot = -0.2618F;
            this.EquipBase.xRot = -1.4F;
            this.LegLeft01.xRot -= 0.7F;
            this.LegRight01.xRot -= 0.7F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowBodyMain2.copyFrom(this.BodyMain);
        this.GlowHead.copyFrom(this.Head);
        this.GlowEquipBase.copyFrom(this.EquipBase);
        this.GlowEquipTubeL01.copyFrom(this.EquipTubeL01);
        this.GlowEquipTubeR01.copyFrom(this.EquipTubeR01);
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
        this.GlowBodyMain2.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
