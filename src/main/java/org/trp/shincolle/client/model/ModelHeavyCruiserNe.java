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

public class ModelHeavyCruiserNe<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "heavy_cruiser_ne"), "main");

    private static final float OFFSET_SCALE = 16.0F;

    private float poseTranslateY;
    private final float headDefaultY;
    private final float glowHeadDefaultY;
    private final float armLeft01DefaultZ;
    private final float armRight01DefaultZ;

    private final ModelPart BodyMain;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight01;
    private final ModelPart Neck;
    private final ModelPart Head;
    private final ModelPart Cloth01;
    private final ModelPart TailBase;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart LegLeft02;
    private final ModelPart LegRight02;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ear01;
    private final ModelPart Ear02;
    private final ModelPart Ahoke;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart TailL01;
    private final ModelPart TailR01;
    private final ModelPart TailL02;
    private final ModelPart TailL03;
    private final ModelPart TailL04;
    private final ModelPart TailL05;
    private final ModelPart TailL06;
    private final ModelPart TailLHead01;
    private final ModelPart TailLHead02;
    private final ModelPart TailLC01;
    private final ModelPart TailLC02;
    private final ModelPart TailLC03;
    private final ModelPart TailR02;
    private final ModelPart TailR03;
    private final ModelPart TailR04;
    private final ModelPart TailR05;
    private final ModelPart TailR06;
    private final ModelPart TailRHead01;
    private final ModelPart TailRHead02;
    private final ModelPart TailRC01;
    private final ModelPart TailRC02;
    private final ModelPart TailRC03;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;

    public ModelHeavyCruiserNe(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.LegLeft01 = this.BodyMain.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.Neck = this.BodyMain.getChild("Neck");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.TailBase = this.BodyMain.getChild("TailBase");
        this.TailL01 = this.TailBase.getChild("TailL01");
        this.TailL02 = this.TailL01.getChild("TailL02");
        this.TailL03 = this.TailL02.getChild("TailL03");
        this.TailL04 = this.TailL03.getChild("TailL04");
        this.TailL05 = this.TailL04.getChild("TailL05");
        this.TailL06 = this.TailL05.getChild("TailL06");
        this.TailLHead01 = this.TailL06.getChild("TailLHead01");
        this.TailLC01 = this.TailLHead01.getChild("TailLC01");
        this.TailLC02 = this.TailLHead01.getChild("TailLC02");
        this.TailLC03 = this.TailLHead01.getChild("TailLC03");
        this.TailLHead02 = this.TailL06.getChild("TailLHead02");
        this.TailR01 = this.TailBase.getChild("TailR01");
        this.TailR02 = this.TailR01.getChild("TailR02");
        this.TailR03 = this.TailR02.getChild("TailR03");
        this.TailR04 = this.TailR03.getChild("TailR04");
        this.TailR05 = this.TailR04.getChild("TailR05");
        this.TailR06 = this.TailR05.getChild("TailR06");
        this.TailRHead01 = this.TailR06.getChild("TailRHead01");
        this.TailRC02 = this.TailRHead01.getChild("TailRC02");
        this.TailRC01 = this.TailRHead01.getChild("TailRC01");
        this.TailRC03 = this.TailRHead01.getChild("TailRC03");
        this.TailRHead02 = this.TailR06.getChild("TailRHead02");
        this.LegRight01 = this.BodyMain.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.Head = this.BodyMain.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.Ear02 = this.Head.getChild("Ear02");
        this.Ear01 = this.Head.getChild("Ear01");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair02 = this.HairMain.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.headDefaultY = this.Head.y;
        this.glowHeadDefaultY = this.GlowHead.y;
        this.armLeft01DefaultZ = this.ArmLeft01.z;
        this.armRight01DefaultZ = this.ArmRight01.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 93).addBox(-5.5F, -4.5F, -12F, 11F, 10F, 24F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition LegLeft01 = BodyMain.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(48, 92).addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 3F, 8.3F, 0.1396F, 0F, 0.1745F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(48, 105).addBox(-2.5F, 0F, 0F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 8F, -2.5F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 78).addBox(-5F, -2F, -4.5F, 10F, 5F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4F, -9.4F, 0.4189F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 92).addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 3F, -6F, -0.1396F, 0F, 0.2094F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 105).addBox(-5F, 0F, -5F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(2.5F, 8F, 2.5F));

        PartDefinition TailBase = BodyMain.addOrReplaceChild("TailBase", CubeListBuilder.create().texOffs(98, 0).addBox(-4F, -4F, 0F, 8F, 8F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.5F, 9F, 0.7854F, 0F, 0F));

        PartDefinition TailL01 = TailBase.addOrReplaceChild("TailL01", CubeListBuilder.create().texOffs(98, 0).addBox(-3F, -3F, 0F, 6F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 0F, 6F, 0.2618F, 0.4189F, 0F));

        PartDefinition TailL02 = TailL01.addOrReplaceChild("TailL02", CubeListBuilder.create().texOffs(95, 3).addBox(-3F, -3F, 0F, 6F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.2618F, 0.3142F, 0F));

        PartDefinition TailL03 = TailL02.addOrReplaceChild("TailL03", CubeListBuilder.create().texOffs(95, 1).addBox(-3.5F, -3.5F, 0F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.2618F, 0.2443F, 0F));

        PartDefinition TailL04 = TailL03.addOrReplaceChild("TailL04", CubeListBuilder.create().texOffs(97, 3).addBox(-3.5F, -3.5F, 0F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.2618F, 0.2094F, 0F));

        PartDefinition TailL05 = TailL04.addOrReplaceChild("TailL05", CubeListBuilder.create().texOffs(95, 2).addBox(-4F, -3.5F, 0F, 8F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.2618F, 0.1396F, 0F));

        PartDefinition TailL06 = TailL05.addOrReplaceChild("TailL06", CubeListBuilder.create().texOffs(89, 0).addBox(-4.5F, -3.5F, 0F, 9F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.2618F, 0.0698F, 0F));

        PartDefinition TailLHead01 = TailL06.addOrReplaceChild("TailLHead01", CubeListBuilder.create().texOffs(76, 18).addBox(-5.5F, -2F, 0F, 11F, 6F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -2.5F, -0.1222F, 0F, 0F));

        PartDefinition TailLC01 = TailLHead01.addOrReplaceChild("TailLC01", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.2F, 13.5F, -0.182F, 0F, 0F));

        PartDefinition TailLC02 = TailLHead01.addOrReplaceChild("TailLC02", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 2F, 13.5F, -0.0911F, -0.0873F, 0F));

        PartDefinition TailLC03 = TailLHead01.addOrReplaceChild("TailLC03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 2F, 13.5F, -0.1367F, 0.0873F, 0F));

        PartDefinition TailLHead02 = TailL06.addOrReplaceChild("TailLHead02", CubeListBuilder.create().texOffs(22, 27).addBox(-5F, -4F, 0F, 10F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 1.5F, 0.0873F, 0F, 0F));

        PartDefinition TailR01 = TailBase.addOrReplaceChild("TailR01", CubeListBuilder.create().texOffs(101, 0).mirror().addBox(-3F, -3F, 0F, 6F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.5F, 0F, 6F, 0.2618F, -0.0698F, 0F));

        PartDefinition TailR02 = TailR01.addOrReplaceChild("TailR02", CubeListBuilder.create().texOffs(102, 3).mirror().addBox(-3F, -3F, 0F, 6F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.2618F, 0F, 0F));

        PartDefinition TailR03 = TailR02.addOrReplaceChild("TailR03", CubeListBuilder.create().texOffs(97, 2).mirror().addBox(-3.5F, -3.5F, 0F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.3142F, 0.0698F, 0F));

        PartDefinition TailR04 = TailR03.addOrReplaceChild("TailR04", CubeListBuilder.create().texOffs(100, 2).mirror().addBox(-3.5F, -3.5F, 0F, 7F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.4189F, 0.1396F, 0F));

        PartDefinition TailR05 = TailR04.addOrReplaceChild("TailR05", CubeListBuilder.create().texOffs(97, 0).mirror().addBox(-4F, -3.5F, 0F, 8F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.5236F, 0.1396F, 0F));

        PartDefinition TailR06 = TailR05.addOrReplaceChild("TailR06", CubeListBuilder.create().texOffs(89, 1).mirror().addBox(-4.5F, -3.5F, 0F, 9F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, 0.2618F, 0.1396F, 0F));

        PartDefinition TailRHead01 = TailR06.addOrReplaceChild("TailRHead01", CubeListBuilder.create().texOffs(76, 18).mirror().addBox(-5.5F, -2F, 0F, 11F, 6F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -2.5F, -0.1222F, 0F, 0F));

        PartDefinition TailRC02 = TailRHead01.addOrReplaceChild("TailRC02", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 2F, 13.5F, -0.0911F, -0.0873F, 0F));

        PartDefinition TailRC01 = TailRHead01.addOrReplaceChild("TailRC01", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.2F, 13.5F, -0.182F, 0F, 0F));

        PartDefinition TailRC03 = TailRHead01.addOrReplaceChild("TailRC03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 2F, 13.5F, -0.1367F, 0.0873F, 0F));

        PartDefinition TailRHead02 = TailR06.addOrReplaceChild("TailRHead02", CubeListBuilder.create().texOffs(22, 27).mirror().addBox(-5F, -4F, 0F, 10F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 1.5F, 0.0873F, 0F, 0F));

        PartDefinition LegRight01 = BodyMain.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(48, 92).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 3F, 8.3F, -0.1396F, 0F, -0.1745F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(48, 105).mirror().addBox(-2.5F, 0F, 0F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 8F, -2.5F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 92).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 3F, -6F, 0.1396F, 0F, -0.2094F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 105).mirror().addBox(0F, 0F, -5F, 5F, 7F, 5F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 8F, 2.5F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 65).addBox(-7F, -11F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -13F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 40).addBox(-8F, -8F, -7.2F, 16F, 17F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -4F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -4F, -11.5F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8.5F, -5F, 0F, 0.5236F, 0F));

        PartDefinition Ear02 = Head.addOrReplaceChild("Ear02", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-2F, 0F, -7F, 4F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.2F, -11F, 6.8F, -0.8378F, 0.1222F, -0.1745F));

        PartDefinition Ear01 = Head.addOrReplaceChild("Ear01", CubeListBuilder.create().texOffs(0, 26).addBox(-2F, 0F, -7F, 4F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.2F, -11F, 6.8F, -0.8378F, -0.1222F, 0.1745F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(0, 56).addBox(-7.5F, 0F, 0F, 15F, 12F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -11.5F, -3F));

        PartDefinition Hair02 = HairMain.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(78, 92).addBox(-2F, 0F, -3.5F, 3F, 10F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.3F, 4.7F, 2F, 0.2094F, 0F, 0.1745F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(80, 109).addBox(-2F, 0F, -3F, 3F, 12F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 7.5F, -0.3F, -0.2618F, 0F, -0.2618F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(0, 40).addBox(-7.5F, 0F, 0F, 15F, 7F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1.6F, 0.3491F, 0F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(42, 39).addBox(-4F, 0F, 0F, 8F, 9F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -13F, -0.0873F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -6F, -13F));
        ShipModelBaseAdv.addFaceLayerCAHime(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.poseTranslateY = 0.0F;
        this.Head.y = this.headDefaultY;
        this.GlowHead.y = this.glowHeadDefaultY;
        this.ArmLeft01.z = this.armLeft01DefaultZ;
        this.ArmRight01.z = this.armRight01DefaultZ;
        if (!(entity instanceof EntityShipBase ship)) {
            return;
        }

        if (ship.isInDeadPose()) {
            this.applyDeadPose();
            this.GlowBodyMain.copyFrom(this.BodyMain);
            this.GlowHead.copyFrom(this.Head);
            return;
        }

        float angleX = Mth.cos(ageInTicks * 0.08F + limbSwing * 0.25F);
        float angleX1 = Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleAdd1 = Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;
        float addk1;
        float addk2;

        if (ship.getShipDepth() > 0.0F) {
            this.poseTranslateY += angleX * 0.05F + 0.025F;
        }

        addk1 = angleAdd1 * 0.5F - 0.14F;
        addk2 = angleAdd2 * 0.5F + 0.14F;
        this.ArmRight01.xRot = addk1;
        this.ArmLeft01.xRot = addk2;
        this.Head.xRot = headPitch * 0.014F;
        this.Head.yRot = netHeadYaw * 0.01F;
        this.Ahoke.yRot = angleX * 0.25F + 0.45F;
        this.BodyMain.xRot = 0.0F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Hair02.xRot = angleX1 * 0.04F + 0.21F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = angleX2 * 0.07F - 0.2618F;
        this.Hair03.zRot = 0.0F;
        this.ArmLeft01.zRot = 0.21F;
        this.ArmLeft02.zRot = 0.0F;
        this.ArmRight01.zRot = -0.21F;
        this.ArmRight02.zRot = 0.0F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.1745F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.1745F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.zRot = 0.0F;
        this.TailBase.xRot = 0.8F;
        this.TailL01.xRot = 0.2618F;
        this.TailL01.yRot = Mth.cos(-ageInTicks * 0.1F + 0.7F) * 0.2F + 0.5F;
        this.TailL01.zRot = this.TailL01.yRot * 0.25F;
        this.TailL02.xRot = 0.2618F;
        this.TailL02.yRot = Mth.cos(-ageInTicks * 0.1F + 1.4F) * 0.25F;
        this.TailL02.zRot = this.TailL02.yRot * 0.25F;
        this.TailL03.xRot = 0.2618F;
        this.TailL03.yRot = Mth.cos(-ageInTicks * 0.1F + 2.1F) * 0.3F;
        this.TailL03.zRot = this.TailL03.yRot * 0.25F;
        this.TailL04.xRot = 0.35F;
        this.TailL04.yRot = Mth.cos(-ageInTicks * 0.1F + 2.8F) * 0.35F;
        this.TailL04.zRot = this.TailL04.yRot * 0.25F;
        this.TailL05.xRot = 0.4F;
        this.TailL05.yRot = Mth.cos(-ageInTicks * 0.1F + 3.5F) * 0.4F;
        this.TailL05.zRot = this.TailL05.yRot * 0.25F;
        this.TailL06.xRot = 0.45F;
        this.TailL06.yRot = Mth.cos(-ageInTicks * 0.1F + 4.2F) * 0.35F;
        this.TailL06.zRot = this.TailL06.yRot * 0.25F;
        this.TailR01.xRot = 0.2618F;
        this.TailR01.yRot = Mth.cos(-ageInTicks * 0.1F + 0.7F) * 0.2F - 0.5F;
        this.TailR01.zRot = this.TailR01.yRot * 0.25F;
        this.TailR02.xRot = 0.2618F;
        this.TailR02.yRot = Mth.cos(-ageInTicks * 0.1F + 1.4F) * 0.25F;
        this.TailR02.zRot = this.TailR02.yRot * 0.25F;
        this.TailR03.xRot = 0.2618F;
        this.TailR03.yRot = Mth.cos(-ageInTicks * 0.1F + 2.1F) * 0.3F;
        this.TailR03.zRot = this.TailR03.yRot * 0.25F;
        this.TailR04.xRot = 0.35F;
        this.TailR04.yRot = Mth.cos(-ageInTicks * 0.1F + 2.8F) * 0.35F;
        this.TailR04.zRot = this.TailR04.yRot * 0.25F;
        this.TailR05.xRot = 0.4F;
        this.TailR05.yRot = Mth.cos(-ageInTicks * 0.1F + 3.5F) * 0.4F;
        this.TailR05.zRot = this.TailR05.yRot * 0.25F;
        this.TailR06.xRot = 0.45F;
        this.TailR06.yRot = Mth.cos(-ageInTicks * 0.1F + 4.2F) * 0.45F;
        this.TailR06.zRot = this.TailR06.yRot * 0.25F;

        float modf2 = ageInTicks % 128.0F;
        if (modf2 < 6.0F) {
            if (modf2 >= 3.0F) {
                modf2 -= 3.0F;
            }
            float anglef2 = Mth.sin(modf2 * 1.0472F) * 0.25F;
            this.Ear01.zRot = anglef2 + 0.1745F;
            this.Ear02.zRot = -anglef2 - 0.1745F;
        } else {
            this.Ear01.zRot = 0.1745F;
            this.Ear02.zRot = -0.1745F;
        }

        if (ship.getIsSprinting() || limbSwingAmount > 0.8F) {
            addk1 *= 2.0F;
            addk2 *= 2.0F;
            this.ArmRight01.xRot = addk1;
            this.ArmLeft01.xRot = addk2;
        }

        this.Head.zRot = ship.getHeadTiltAngle(ageInTicks);
        if (entity.isCrouching()) {
            this.Head.y = this.headDefaultY + (0.2F * OFFSET_SCALE);
            this.GlowHead.y = this.glowHeadDefaultY + (0.2F * OFFSET_SCALE);
        }

        if (ship.getIsSitting() || entity.isPassenger()) {
            if (ship.getStateEmotion(1) == 4) {
                this.poseTranslateY += 0.22F * 5;
                this.Head.xRot = 1.5359F;
                this.Head.y = this.headDefaultY + (0.25F * OFFSET_SCALE);
                this.GlowHead.xRot = 1.5359F;
                this.GlowHead.y = this.glowHeadDefaultY + (0.25F * OFFSET_SCALE);
                addk1 = 1.5359F;
                addk2 = 1.5359F;
                this.ArmLeft01.xRot = -1.5359F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft01.z = this.armLeft01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.ArmRight01.xRot = -1.5359F;
                this.ArmRight01.zRot = 0.0F;
                this.ArmRight01.z = this.armRight01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.TailBase.xRot = 0.0873F;
                this.TailL01.xRot = 0.02618F;
                this.TailL01.yRot *= 0.5F;
                this.TailL02.xRot = -0.02618F;
                this.TailL02.yRot *= 0.5F;
                this.TailL03.xRot = -0.02618F;
                this.TailL03.yRot *= 0.5F;
                this.TailL04.xRot = -0.035F;
                this.TailL04.yRot *= 0.5F;
                this.TailL05.xRot = -0.04F;
                this.TailL05.yRot *= 0.5F;
                this.TailL06.xRot = -0.045F;
                this.TailL06.yRot *= 0.5F;
                this.TailR01.xRot = -0.02618F;
                this.TailR01.yRot *= 0.5F;
                this.TailR02.xRot = -0.02618F;
                this.TailR02.yRot *= 0.5F;
                this.TailR03.xRot = -0.02618F;
                this.TailR03.yRot *= 0.5F;
                this.TailR04.xRot = -0.035F;
                this.TailR04.yRot *= 0.5F;
                this.TailR05.xRot = -0.04F;
                this.TailR05.yRot *= 0.5F;
                this.TailR06.xRot = -0.045F;
                this.TailR06.yRot *= 0.5F;
            } else {
                this.poseTranslateY += 0.22F * 5;
                this.Head.xRot -= 0.5F;
                this.GlowHead.xRot -= 0.5F;
                this.Head.y = this.headDefaultY + (0.25F * OFFSET_SCALE);
                this.GlowHead.y = this.glowHeadDefaultY + (0.25F * OFFSET_SCALE);
                addk1 = 1.5359F;
                addk2 = 1.5359F;
                this.ArmLeft01.xRot = -1.5359F;
                this.ArmLeft01.zRot = 0.0F;
                this.ArmLeft01.z = this.armLeft01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.ArmLeft02.zRot = 1.1868F;
                this.ArmRight01.xRot = -1.5359F;
                this.ArmRight01.zRot = 0.0F;
                this.ArmRight01.z = this.armRight01DefaultZ + (-0.18F * OFFSET_SCALE);
                this.ArmRight02.zRot = -1.1868F;
            }
        }

        if (ship.getAttackTick() > 20) {
            this.TailL01.xRot = 0.2618F;
            this.TailL01.yRot = 0.2618F;
            this.TailL01.zRot = 0.0F;
            this.TailL02.xRot = 0.35F;
            this.TailL02.yRot = 0.1748F;
            this.TailL02.zRot = 0.0F;
            this.TailL03.xRot = 0.4363F;
            this.TailL03.yRot = 0.14F;
            this.TailL03.zRot = 0.0F;
            this.TailL04.xRot = 0.5236F;
            this.TailL04.yRot = 0.14F;
            this.TailL04.zRot = 0.0F;
            this.TailL05.xRot = 0.6109F;
            this.TailL05.yRot = 0.1745F;
            this.TailL05.zRot = 0.0F;
            this.TailL06.xRot = 0.35F;
            this.TailL06.yRot = 0.0F;
            this.TailL06.zRot = 0.0F;
            this.TailR01.xRot = 0.2618F;
            this.TailR01.yRot = -0.2618F;
            this.TailR01.zRot = 0.0F;
            this.TailR02.xRot = 0.35F;
            this.TailR02.yRot = -0.1748F;
            this.TailR02.zRot = 0.0F;
            this.TailR03.xRot = 0.35F;
            this.TailR03.yRot = -0.14F;
            this.TailR03.zRot = 0.0F;
            this.TailR04.xRot = 0.4363F;
            this.TailR04.yRot = -0.14F;
            this.TailR04.zRot = 0.0F;
            this.TailR05.xRot = 0.4363F;
            this.TailR05.yRot = -0.14F;
            this.TailR05.zRot = 0.0F;
            this.TailR06.xRot = 0.35F;
            this.TailR06.yRot = 0.0F;
            this.TailR06.zRot = 0.0F;
        }

        float swing = ship.getSwingTime(ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot = -0.6F - f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot = 0.0F - f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            this.ArmRight01.zRot = 0.2F - -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        float headZ = this.Head.zRot * -0.5F;
        float headX = this.Head.xRot * -0.5F - 0.05F;
        this.Hair02.xRot += headX * 0.5F;
        this.Hair03.xRot += headX * 0.2F;
        this.Hair02.zRot += headZ * 0.8F;
        this.Hair03.zRot += headZ * 0.4F;
        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
        applyFaceAndMouth(ship);
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowHead.copyFrom(this.Head);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    private void applyDeadPose() {
        this.poseTranslateY += 0.2F * 5;
        this.Head.xRot = 0.7853F;
        this.Head.yRot = 0.0F;
        this.Ahoke.yRot = 0.45F;
        this.BodyMain.xRot = 0.0F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = -1.4835F;
        this.Head.y = this.headDefaultY + (0.0F * OFFSET_SCALE);
        this.GlowHead.y = this.glowHeadDefaultY + (0.0F * OFFSET_SCALE);
        this.Hair02.xRot = 0.21F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -0.2618F;
        this.Hair03.zRot = 0.0F;
        this.ArmLeft01.xRot = 0.1745F;
        this.ArmLeft01.zRot = 0.4537F;
        this.ArmLeft01.z = this.armLeft01DefaultZ + (0.0F * OFFSET_SCALE);
        this.ArmLeft02.zRot = 0.0F;
        this.ArmRight01.xRot = -0.1745F;
        this.ArmRight01.zRot = -0.05F;
        this.ArmRight01.z = this.armRight01DefaultZ + (0.0F * OFFSET_SCALE);
        this.ArmRight02.zRot = 0.0F;
        this.LegLeft01.xRot = -0.1745F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.4537F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.xRot = 0.1745F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.05F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.zRot = 0.0F;
        this.TailBase.xRot = 0.8F;
        this.TailL01.xRot = 0.2618F;
        this.TailL01.yRot = -0.2F;
        this.TailL01.zRot = this.TailL01.yRot * 0.25F;
        this.TailL02.xRot = 0.2618F;
        this.TailL02.yRot = -0.3F;
        this.TailL02.zRot = this.TailL02.yRot * 0.25F;
        this.TailL03.xRot = 0.2618F;
        this.TailL03.yRot = -0.2F;
        this.TailL03.zRot = this.TailL03.yRot * 0.25F;
        this.TailL04.xRot = 0.35F;
        this.TailL04.yRot = 0.2F;
        this.TailL04.zRot = this.TailL04.yRot * 0.25F;
        this.TailL05.xRot = 0.4F;
        this.TailL05.yRot = 0.2F;
        this.TailL05.zRot = this.TailL05.yRot * 0.25F;
        this.TailL06.xRot = 0.45F;
        this.TailL06.yRot = 0.1F;
        this.TailL06.zRot = this.TailL06.yRot * 0.25F;
        this.TailR01.xRot = 0.6F;
        this.TailR01.yRot = 0.2617F;
        this.TailR01.zRot = this.TailR01.yRot * 0.25F;
        this.TailR02.xRot = 0.6F;
        this.TailR02.yRot = -0.2F;
        this.TailR02.zRot = this.TailR02.yRot * 0.25F;
        this.TailR03.xRot = 0.5F;
        this.TailR03.yRot = -0.1F;
        this.TailR03.zRot = this.TailR03.yRot * 0.25F;
        this.TailR04.xRot = 0.3F;
        this.TailR04.yRot = -0.1F;
        this.TailR04.zRot = this.TailR04.yRot * 0.25F;
        this.TailR05.xRot = 0.1F;
        this.TailR05.yRot = 0.1F;
        this.TailR05.zRot = this.TailR05.yRot * 0.25F;
        this.TailR06.xRot = -0.1F;
        this.TailR06.yRot = 0.1F;
        this.TailR06.zRot = this.TailR06.yRot * 0.25F;
    }
}
