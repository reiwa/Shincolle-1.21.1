package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.trp.shincolle.Shincolle;

public class ModelMountAfH<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_af_h"), "main");

    private final ModelPart BodyMain;
    private final ModelPart ChestCannon01a;
    private final ModelPart ChestCannon02a;
    private final ModelPart ChestCannon03a;
    private final ModelPart EquipBaseL;
    private final ModelPart EquipBaseR;
    private final ModelPart Neck;
    private final ModelPart EquipL01;
    private final ModelPart EquipL02;
    private final ModelPart EquipCannonPlate;
    private final ModelPart EquipCannon01;
    private final ModelPart EquipCannon02;
    private final ModelPart EquipR01;
    private final ModelPart EquipR02;
    private final ModelPart EquipCannonPlate_1;
    private final ModelPart EquipCannon01_1;
    private final ModelPart EquipCannon02_1;
    private final ModelPart Head;
    private final ModelPart Jaw;
    private final ModelPart HeadBack01;
    private final ModelPart NeckBack;
    private final ModelPart HeadBack03;
    private final ModelPart NeckFront;
    private final ModelPart CannonBase;
    private final ModelPart HeadTooth;
    private final ModelPart HeadTooth2;
    private final ModelPart JawTooth;
    private final ModelPart JawTooth2;
    private final ModelPart Cannon01;
    private final ModelPart Cannon02;
    private final ModelPart Cannon03;
    private final ModelPart Cannon04;
    private final ModelPart Cannon05;
    private final ModelPart Cannon06;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowJaw;
    private final ModelPart GlowHead;
    private final ModelPart GlowCannonBase;
    private final ModelPart GlowCannon04;
    private final ModelPart GlowEquipBaseL;
    private final ModelPart GlowEquipL01;
    private final ModelPart GlowEquipL02;
    private final ModelPart GlowEquipBaseR;
    private final ModelPart GlowEquipR01;

    public ModelMountAfH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ChestCannon01a = this.BodyMain.getChild("ChestCannon01a");
        this.EquipBaseL = this.BodyMain.getChild("EquipBaseL");
        this.EquipL01 = this.EquipBaseL.getChild("EquipL01");
        this.EquipL02 = this.EquipL01.getChild("EquipL02");
        this.ChestCannon03a = this.BodyMain.getChild("ChestCannon03a");
        this.Neck = this.BodyMain.getChild("Neck");
        this.CannonBase = this.Neck.getChild("CannonBase");
        this.Cannon01 = this.CannonBase.getChild("Cannon01");
        this.Cannon02 = this.CannonBase.getChild("Cannon02");
        this.Cannon05 = this.CannonBase.getChild("Cannon05");
        this.Cannon04 = this.CannonBase.getChild("Cannon04");
        this.Cannon03 = this.CannonBase.getChild("Cannon03");
        this.HeadBack03 = this.Neck.getChild("HeadBack03");
        this.NeckBack = this.Neck.getChild("NeckBack");
        this.HeadBack01 = this.Neck.getChild("HeadBack01");
        this.Head = this.Neck.getChild("Head");
        this.Jaw = this.Neck.getChild("Jaw");
        this.EquipBaseR = this.BodyMain.getChild("EquipBaseR");
        this.EquipR01 = this.EquipBaseR.getChild("EquipR01");
        this.EquipR02 = this.EquipR01.getChild("EquipR02");
        this.ChestCannon02a = this.BodyMain.getChild("ChestCannon02a");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowJaw = this.GlowNeck.getChild("GlowJaw");
        this.JawTooth = this.GlowJaw.getChild("JawTooth");
        this.JawTooth2 = this.GlowJaw.getChild("JawTooth2");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.HeadTooth = this.GlowHead.getChild("HeadTooth");
        this.HeadTooth2 = this.GlowHead.getChild("HeadTooth2");
        this.NeckFront = this.GlowNeck.getChild("NeckFront");
        this.GlowCannonBase = this.GlowNeck.getChild("GlowCannonBase");
        this.GlowCannon04 = this.GlowCannonBase.getChild("GlowCannon04");
        this.Cannon06 = this.GlowCannon04.getChild("Cannon06");
        this.GlowEquipBaseL = this.GlowBodyMain.getChild("GlowEquipBaseL");
        this.GlowEquipL01 = this.GlowEquipBaseL.getChild("GlowEquipL01");
        this.GlowEquipL02 = this.GlowEquipL01.getChild("GlowEquipL02");
        this.EquipCannonPlate = this.GlowEquipL01.getChild("EquipCannonPlate");
        this.EquipCannon01 = this.EquipCannonPlate.getChild("EquipCannon01");
        this.EquipCannon02 = this.EquipCannonPlate.getChild("EquipCannon02");
        this.GlowEquipBaseR = this.GlowBodyMain.getChild("GlowEquipBaseR");
        this.GlowEquipR01 = this.GlowEquipBaseR.getChild("GlowEquipR01");
        this.EquipCannonPlate_1 = this.GlowEquipR01.getChild("EquipCannonPlate_1");
        this.EquipCannon01_1 = this.EquipCannonPlate_1.getChild("EquipCannon01_1");
        this.EquipCannon02_1 = this.EquipCannonPlate_1.getChild("EquipCannon02_1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, -7F, 10F, 18F, 12F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition ChestCannon01a = BodyMain.addOrReplaceChild("ChestCannon01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 5F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.3F, 6F, -2F, 0.0873F, 0F, 0F));

        PartDefinition EquipBaseL = BodyMain.addOrReplaceChild("EquipBaseL", CubeListBuilder.create().texOffs(0, 10).addBox(-6F, 0F, -10F, 11F, 6F, 21F, new CubeDeformation(0F)), PartPose.offsetAndRotation(14.5F, 2F, 5F, 0F, -0.0873F, 0F));

        PartDefinition EquipL01 = EquipBaseL.addOrReplaceChild("EquipL01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -7F, 10F, 9F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, 1F, -0.1396F, 0F, 0F));

        PartDefinition EquipL02 = EquipL01.addOrReplaceChild("EquipL02", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0F, -9F, 11F, 4F, 23F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 0F, 0.0524F, 0F, 0F));

        PartDefinition ChestCannon03a = BodyMain.addOrReplaceChild("ChestCannon03a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 5F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8.3F, 6F, -2F, 0.0873F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(70, 58).addBox(-7.5F, -15F, -3F, 15F, 15F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-29F, 5F, 6F, 0F, 0.2618F, 0F));

        PartDefinition CannonBase = Neck.addOrReplaceChild("CannonBase", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -14F, 0F, 10F, 14F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -16F, 7F, -0.5236F, 0F, 0F));

        PartDefinition Cannon01 = CannonBase.addOrReplaceChild("Cannon01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -10F, 3F, 3F, 10F, new CubeDeformation(0F)), PartPose.offset(2F, -9F, 0F));

        PartDefinition Cannon02 = CannonBase.addOrReplaceChild("Cannon02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -10F, 3F, 3F, 10F, new CubeDeformation(0F)), PartPose.offset(-3F, -9F, 0F));

        PartDefinition Cannon05 = CannonBase.addOrReplaceChild("Cannon05", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -10F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -14.6F, 0.5F, -0.0524F, 0F, 0F));

        PartDefinition Cannon04 = CannonBase.addOrReplaceChild("Cannon04", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -13F, 4F, 4F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, -13.5F, 0F, 0F, 0F, 0.7854F));

        PartDefinition Cannon03 = CannonBase.addOrReplaceChild("Cannon03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(-3.5F, -11.3F, 0F));

        PartDefinition HeadBack03 = Neck.addOrReplaceChild("HeadBack03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 8F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, -16.1F, 14.5F, 0.0911F, 0F, 0F));

        PartDefinition NeckBack = Neck.addOrReplaceChild("NeckBack", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(-2F, -6F, 11F));

        PartDefinition HeadBack01 = Neck.addOrReplaceChild("HeadBack01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 8F, 8F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, -18F, 8F, -0.1396F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 94).addBox(-9.5F, -7F, -22F, 19F, 10F, 24F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 7F, -0.2094F, 0F, 0F));

        PartDefinition Jaw = Neck.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(0, 68).mirror().addBox(-9.5F, 0F, -15F, 19F, 7F, 19F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 0.5F, 0.5463F, 0F, 0F));

        PartDefinition EquipBaseR = BodyMain.addOrReplaceChild("EquipBaseR", CubeListBuilder.create().texOffs(0, 10).addBox(-6F, 0F, -10F, 11F, 6F, 21F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13.5F, 2F, 5F, 0F, 0.0873F, 0F));

        PartDefinition EquipR01 = EquipBaseR.addOrReplaceChild("EquipR01", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -7F, 10F, 9F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, 1F, -0.1396F, 0F, 0F));

        PartDefinition EquipR02 = EquipR01.addOrReplaceChild("EquipR02", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, 0F, -9F, 11F, 4F, 23F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 0F, 0.0524F, 0F, 0F));

        PartDefinition ChestCannon02a = BodyMain.addOrReplaceChild("ChestCannon02a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 5F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 6F, -2F, 0.0873F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-29F, 5F, 6F, 0F, 0.2618F, 0F));

        PartDefinition GlowJaw = GlowNeck.addOrReplaceChild("GlowJaw", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -3F, 0.5F));

        PartDefinition JawTooth = GlowJaw.addOrReplaceChild("JawTooth", CubeListBuilder.create().texOffs(63, 99).addBox(-9F, 0F, -14F, 18F, 3F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.6F, -0.3F, -0.0873F, -0.0223F, 0F));

        PartDefinition JawTooth2 = GlowJaw.addOrReplaceChild("JawTooth2", CubeListBuilder.create().texOffs(66, 100).mirror().addBox(-8F, 0F, -13F, 16F, 3F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.6F, 0F, -0.1396F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 7F, -0.2094F, 0F, 0F));

        PartDefinition HeadTooth = GlowHead.addOrReplaceChild("HeadTooth", CubeListBuilder.create().texOffs(62, 98).addBox(-9F, 0F, -6.5F, 18F, 4F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -15F, 0.0524F, 0F, 0F));

        PartDefinition HeadTooth2 = GlowHead.addOrReplaceChild("HeadTooth2", CubeListBuilder.create().texOffs(65, 99).mirror().addBox(-8F, 0F, -14F, 16F, 3F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.6F, -6.5F, 0.1745F, 0F, 0F));

        PartDefinition NeckFront = GlowNeck.addOrReplaceChild("NeckFront", CubeListBuilder.create().texOffs(0, 52).addBox(-6.5F, 0F, 0F, 13F, 14F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14F, -5F));

        PartDefinition GlowCannonBase = GlowNeck.addOrReplaceChild("GlowCannonBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-1F, -16F, 7F, -0.5236F, 0F, 0F));

        PartDefinition GlowCannon04 = GlowCannonBase.addOrReplaceChild("GlowCannon04", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(1F, -13.5F, 0F, 0F, 0F, 0.7854F));

        PartDefinition Cannon06 = GlowCannon04.addOrReplaceChild("Cannon06", CubeListBuilder.create().texOffs(74, 0).addBox(0F, 0F, -15F, 2F, 2F, 15F, new CubeDeformation(0F)), PartPose.offset(1F, 1F, -13F));

        PartDefinition GlowEquipBaseL = GlowBodyMain.addOrReplaceChild("GlowEquipBaseL", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(14.5F, 2F, 5F, 0F, -0.0873F, 0F));

        PartDefinition GlowEquipL01 = GlowEquipBaseL.addOrReplaceChild("GlowEquipL01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -8F, 1F, -0.1396F, 0F, 0F));

        PartDefinition GlowEquipL02 = GlowEquipL01.addOrReplaceChild("GlowEquipL02", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -3F, 0F, 0.0524F, 0F, 0F));

        PartDefinition EquipCannonPlate = GlowEquipL01.addOrReplaceChild("EquipCannonPlate", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 6F, 1F, new CubeDeformation(0F)), PartPose.offset(-2F, 2F, -8F));

        PartDefinition EquipCannon01 = EquipCannonPlate.addOrReplaceChild("EquipCannon01", CubeListBuilder.create().texOffs(73, 0).addBox(0F, 0F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 1F, 0.5F, -0.3187F, -0.0873F, 0F));

        PartDefinition EquipCannon02 = EquipCannonPlate.addOrReplaceChild("EquipCannon02", CubeListBuilder.create().texOffs(73, 0).addBox(0F, 0F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 4F, 0.5F, 0F, -0.0873F, 0F));

        PartDefinition GlowEquipBaseR = GlowBodyMain.addOrReplaceChild("GlowEquipBaseR", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-13.5F, 2F, 5F, 0F, 0.0873F, 0F));

        PartDefinition GlowEquipR01 = GlowEquipBaseR.addOrReplaceChild("GlowEquipR01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -8F, 1F, -0.1396F, 0F, 0F));

        PartDefinition EquipCannonPlate_1 = GlowEquipR01.addOrReplaceChild("EquipCannonPlate_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 6F, 1F, new CubeDeformation(0F)), PartPose.offset(-2F, 2F, -8F));

        PartDefinition EquipCannon01_1 = EquipCannonPlate_1.addOrReplaceChild("EquipCannon01_1", CubeListBuilder.create().texOffs(73, 0).addBox(0F, 0F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 1F, 0.5F, -0.182F, 0.1367F, 0F));

        PartDefinition EquipCannon02_1 = EquipCannonPlate_1.addOrReplaceChild("EquipCannon02_1", CubeListBuilder.create().texOffs(73, 0).addBox(0F, 0F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 4F, 0.5F, 0.182F, 0.0911F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // TODO: Port animation logic from setRotationAngles
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
