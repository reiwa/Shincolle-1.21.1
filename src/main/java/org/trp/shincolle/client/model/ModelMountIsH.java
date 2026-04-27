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

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelMountIsH<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_is_h"), "main");

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Cannon01a;
    private final ModelPart Cannon01b;
    private final ModelPart Body01;
    private final ModelPart Body04;
    private final ModelPart Body05;
    private final ModelPart LegFL01;
    private final ModelPart LegFR01;
    private final ModelPart Head;
    private final ModelPart Jaw;
    private final ModelPart NeckFront;
    private final ModelPart HeadTooth;
    private final ModelPart HeadCannon;
    private final ModelPart TopCannonBase;
    private final ModelPart TopCannon01a;
    private final ModelPart TopCannon01b;
    private final ModelPart TopCannonBase02;
    private final ModelPart TopCannon02a;
    private final ModelPart TopCannon03a;
    private final ModelPart TopCannon04a;
    private final ModelPart TopCannon02b;
    private final ModelPart TopCannon03b;
    private final ModelPart TopCannon04b;
    private final ModelPart JawTooth;
    private final ModelPart Tongue01;
    private final ModelPart Tongue02;
    private final ModelPart Tongue03;
    private final ModelPart Cannon02a;
    private final ModelPart Cannon03a;
    private final ModelPart Cannon02b;
    private final ModelPart Cannon03b;
    private final ModelPart Body02;
    private final ModelPart Body03;
    private final ModelPart LegBR01;
    private final ModelPart LegBL01;
    private final ModelPart LegBR02;
    private final ModelPart LegBR03;
    private final ModelPart LegFR02;
    private final ModelPart LegFR03;
    private final ModelPart LegFL02;
    private final ModelPart LegFL03;
    private final ModelPart LegBL02;
    private final ModelPart LegBL03;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowJaw;
    private final ModelPart GlowTopCannonBase;

    public ModelMountIsH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Body04 = this.BodyMain.getChild("Body04");
        this.Body01 = this.BodyMain.getChild("Body01");
        this.Body02 = this.Body01.getChild("Body02");
        this.Body03 = this.Body02.getChild("Body03");
        this.LegFL01 = this.BodyMain.getChild("LegFL01");
        this.LegFL02 = this.LegFL01.getChild("LegFL02");
        this.LegFL03 = this.LegFL02.getChild("LegFL03");
        this.LegFR01 = this.BodyMain.getChild("LegFR01");
        this.LegBL02 = this.LegFR01.getChild("LegBL02");
        this.LegBL03 = this.LegBL02.getChild("LegBL03");
        this.Body05 = this.BodyMain.getChild("Body05");
        this.LegBR01 = this.Body05.getChild("LegBR01");
        this.LegBR02 = this.LegBR01.getChild("LegBR02");
        this.LegBR03 = this.LegBR02.getChild("LegBR03");
        this.LegBL01 = this.Body05.getChild("LegBL01");
        this.LegFR02 = this.LegBL01.getChild("LegFR02");
        this.LegFR03 = this.LegFR02.getChild("LegFR03");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Jaw = this.Neck.getChild("Jaw");
        this.Head = this.Neck.getChild("Head");
        this.TopCannonBase = this.Head.getChild("TopCannonBase");
        this.TopCannonBase02 = this.TopCannonBase.getChild("TopCannonBase02");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.HeadTooth = this.GlowHead.getChild("HeadTooth");
        this.HeadCannon = this.GlowHead.getChild("HeadCannon");
        this.GlowTopCannonBase = this.GlowHead.getChild("GlowTopCannonBase");
        this.TopCannon01a = this.GlowTopCannonBase.getChild("TopCannon01a");
        this.TopCannon02a = this.TopCannon01a.getChild("TopCannon02a");
        this.TopCannon03a = this.TopCannon01a.getChild("TopCannon03a");
        this.TopCannon04a = this.TopCannon03a.getChild("TopCannon04a");
        this.TopCannon01b = this.GlowTopCannonBase.getChild("TopCannon01b");
        this.TopCannon02b = this.TopCannon01b.getChild("TopCannon02b");
        this.TopCannon03b = this.TopCannon01b.getChild("TopCannon03b");
        this.TopCannon04b = this.TopCannon03b.getChild("TopCannon04b");
        this.GlowJaw = this.GlowNeck.getChild("GlowJaw");
        this.JawTooth = this.GlowJaw.getChild("JawTooth");
        this.Tongue01 = this.GlowJaw.getChild("Tongue01");
        this.Tongue02 = this.Tongue01.getChild("Tongue02");
        this.Tongue03 = this.Tongue02.getChild("Tongue03");
        this.NeckFront = this.GlowNeck.getChild("NeckFront");
        this.Cannon01a = this.GlowBodyMain.getChild("Cannon01a");
        this.Cannon02a = this.Cannon01a.getChild("Cannon02a");
        this.Cannon03a = this.Cannon02a.getChild("Cannon03a");
        this.Cannon01b = this.GlowBodyMain.getChild("Cannon01b");
        this.Cannon02b = this.Cannon01b.getChild("Cannon02b");
        this.Cannon03b = this.Cannon02b.getChild("Cannon03b");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, 5F));

        PartDefinition Body04 = BodyMain.addOrReplaceChild("Body04", CubeListBuilder.create().texOffs(7, 0).addBox(-7.5F, -6F, 0F, 15F, 15F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, -3F, -0.3491F, 0F, 0F));

        PartDefinition Body01 = BodyMain.addOrReplaceChild("Body01", CubeListBuilder.create().texOffs(12, 0).addBox(-8.5F, -12F, -6F, 17F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -0.5F, -0.1745F, 0F, 0F));

        PartDefinition Body02 = Body01.addOrReplaceChild("Body02", CubeListBuilder.create().texOffs(6, 3).addBox(-8F, -12F, -6F, 16F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 7F, -0.2618F, 0F, 0F));

        PartDefinition Body03 = Body02.addOrReplaceChild("Body03", CubeListBuilder.create().texOffs(18, 0).addBox(-7.5F, -12F, -6F, 15F, 12F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 10F, 0.3491F, 0F, 0F));

        PartDefinition LegFL01 = BodyMain.addOrReplaceChild("LegFL01", CubeListBuilder.create().texOffs(34, 7).addBox(0F, -4.5F, -9F, 3F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 13F, -19F, 0.8727F, -0.1396F, 0.0524F));

        PartDefinition LegFL02 = LegFL01.addOrReplaceChild("LegFL02", CubeListBuilder.create().texOffs(3, 5).addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.6F, -2F, -8.5F, -0.2618F, 0F, 0F));

        PartDefinition LegFL03 = LegFL02.addOrReplaceChild("LegFL03", CubeListBuilder.create().texOffs(9, 0).addBox(-0.5F, -6F, 0F, 1F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10.2F, 1F, -1.0472F, 0F, 0F));

        PartDefinition LegFR01 = BodyMain.addOrReplaceChild("LegFR01", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-3F, -4.5F, -9F, 3F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 13F, -19F, 0.8727F, 0.1396F, -0.0524F));

        PartDefinition LegBL02 = LegFR01.addOrReplaceChild("LegBL02", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.6F, -2F, -8.5F, -0.2618F, 0F, 0F));

        PartDefinition LegBL03 = LegBL02.addOrReplaceChild("LegBL03", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(-0.5F, -6F, 0F, 1F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10.2F, 1F, -1.0472F, 0F, 0F));

        PartDefinition Body05 = BodyMain.addOrReplaceChild("Body05", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -6F, 0F, 13F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.4F, 4.5F, 0.0873F, 0F, 0F));

        PartDefinition LegBR01 = Body05.addOrReplaceChild("LegBR01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -4.5F, -9F, 3F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 4F, 5F, 1.0472F, 3.002F, -0.0524F));

        PartDefinition LegBR02 = LegBR01.addOrReplaceChild("LegBR02", CubeListBuilder.create().texOffs(0, 17).addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.6F, -2F, -8.5F, -0.2618F, 0F, 0F));

        PartDefinition LegBR03 = LegBR02.addOrReplaceChild("LegBR03", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -6F, 0F, 1F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10.2F, 1F, -1.0472F, 0F, 0F));

        PartDefinition LegBL01 = Body05.addOrReplaceChild("LegBL01", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(-3F, -4.5F, -9F, 3F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 4F, 5F, 1.0472F, -3.002F, 0.0524F));

        PartDefinition LegFR02 = LegBL01.addOrReplaceChild("LegFR02", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.6F, -2F, -8.5F, -0.2618F, 0F, 0F));

        PartDefinition LegFR03 = LegFR02.addOrReplaceChild("LegFR03", CubeListBuilder.create().texOffs(19, 0).mirror().addBox(-0.5F, -6F, 0F, 1F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10.2F, 1F, -1.0472F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -7.5F, -14F, 15F, 15F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.182F, 0F, 0F));

        PartDefinition Jaw = Neck.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(7, 0).addBox(-9.5F, 0F, -15F, 19F, 7F, 19F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, -11F, 0.2618F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 3).addBox(-9.5F, -7F, -22F, 19F, 10F, 24F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, -4F, -0.2094F, 0F, 0F));

        PartDefinition TopCannonBase = Head.addOrReplaceChild("TopCannonBase", CubeListBuilder.create().texOffs(3, 0).addBox(-7.5F, -8F, -8F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6F, -5F, -0.0873F, 0F, 0F));

        PartDefinition TopCannonBase02 = TopCannonBase.addOrReplaceChild("TopCannonBase02", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, -6F, 0F, 10F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.5F, -0.7F, -0.0873F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -8F, 5F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 0F, 0F, 0.182F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -9F, -4F, -0.2094F, 0F, 0F));

        PartDefinition HeadTooth = GlowHead.addOrReplaceChild("HeadTooth", CubeListBuilder.create().texOffs(62, 45).addBox(-9F, 0F, -6.5F, 18F, 4F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, -15F, 0.0524F, 0F, 0F));

        PartDefinition HeadCannon = GlowHead.addOrReplaceChild("HeadCannon", CubeListBuilder.create().texOffs(107, 0).addBox(-1.5F, 0F, -1.5F, 3F, 16F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.9F, -21F, -1.6581F, 0F, 0F));

        PartDefinition GlowTopCannonBase = GlowHead.addOrReplaceChild("GlowTopCannonBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -6F, -5F, -0.0873F, 0F, 0F));

        PartDefinition TopCannon01a = GlowTopCannonBase.addOrReplaceChild("TopCannon01a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -6F, 3F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -4F, -6.7F, -0.3491F, 0F, 0F));

        PartDefinition TopCannon02a = TopCannon01a.addOrReplaceChild("TopCannon02a", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, 0F, 1F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.8F, -7F));

        PartDefinition TopCannon03a = TopCannon01a.addOrReplaceChild("TopCannon03a", CubeListBuilder.create().texOffs(120, 0).addBox(-1F, 0F, -1F, 2F, 18F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04a = TopCannon03a.addOrReplaceChild("TopCannon04a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 0F));

        PartDefinition TopCannon01b = GlowTopCannonBase.addOrReplaceChild("TopCannon01b", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -6F, 3F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -4F, -6.7F, -0.3491F, 0F, 0F));

        PartDefinition TopCannon02b = TopCannon01b.addOrReplaceChild("TopCannon02b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, 0F, 1F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.8F, -7F));

        PartDefinition TopCannon03b = TopCannon01b.addOrReplaceChild("TopCannon03b", CubeListBuilder.create().texOffs(120, 0).addBox(-1F, 0F, -1F, 2F, 18F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04b = TopCannon03b.addOrReplaceChild("TopCannon04b", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 0F));

        PartDefinition GlowJaw = GlowNeck.addOrReplaceChild("GlowJaw", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 4F, -11F, 0.2618F, 0F, 0F));

        PartDefinition JawTooth = GlowJaw.addOrReplaceChild("JawTooth", CubeListBuilder.create().texOffs(63, 46).addBox(-9F, 0F, -14F, 18F, 3F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.7F, -0.3F, -0.0873F, -0.0223F, 0F));

        PartDefinition Tongue01 = GlowJaw.addOrReplaceChild("Tongue01", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-7F, 0F, -10F, 14F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 0F, -0.384F, 0.3491F, -0.0524F));

        PartDefinition Tongue02 = Tongue01.addOrReplaceChild("Tongue02", CubeListBuilder.create().texOffs(8, 52).addBox(-6F, -0.7F, -7F, 12F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, -10F, 0.5236F, 0F, 0F));

        PartDefinition Tongue03 = Tongue02.addOrReplaceChild("Tongue03", CubeListBuilder.create().texOffs(0, 51).addBox(-5F, -0.3F, -6F, 10F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.2F, -6.7F, 0.6981F, 0F, 0F));

        PartDefinition NeckFront = GlowNeck.addOrReplaceChild("NeckFront", CubeListBuilder.create().texOffs(46, 39).addBox(-6.5F, 0F, 0F, 13F, 14F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -8.5F, -16F));

        PartDefinition Cannon01a = GlowBodyMain.addOrReplaceChild("Cannon01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -2.5F, -2.5F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(7F, 2F, -10F));

        PartDefinition Cannon02a = Cannon01a.addOrReplaceChild("Cannon02a", CubeListBuilder.create().texOffs(65, 0).addBox(0F, -4F, -8F, 8F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, -0.5F, 2F, 0.2094F, -0.1745F, 0F));

        PartDefinition Cannon03a = Cannon02a.addOrReplaceChild("Cannon03a", CubeListBuilder.create().texOffs(98, 0).addBox(-1F, 0F, -1F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 0F, -7F, -1.7453F, 0F, 0F));

        PartDefinition Cannon01b = GlowBodyMain.addOrReplaceChild("Cannon01b", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, -2.5F, -2.5F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(-7F, 2F, -10F));

        PartDefinition Cannon02b = Cannon01b.addOrReplaceChild("Cannon02b", CubeListBuilder.create().texOffs(65, 0).mirror().addBox(-8F, -4F, -8F, 8F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, -0.5F, 2F, 0.2094F, 0.1745F, 0F));

        PartDefinition Cannon03b = Cannon02b.addOrReplaceChild("Cannon03b", CubeListBuilder.create().texOffs(98, 0).addBox(-1F, 0F, -1F, 2F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 0F, -7F, -1.7453F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // TODO: Port animation logic from setRotationAngles
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
