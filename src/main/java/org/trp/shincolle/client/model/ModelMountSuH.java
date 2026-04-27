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

public class ModelMountSuH<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_su_h"), "main");

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Head01;
    private final ModelPart Jaw;
    private final ModelPart NeckFront;
    private final ModelPart Body01;
    private final ModelPart Head02;
    private final ModelPart Head03;
    private final ModelPart Head04;
    private final ModelPart Head05;
    private final ModelPart Head06;
    private final ModelPart Head07a;
    private final ModelPart HeadTooth;
    private final ModelPart Eye01a;
    private final ModelPart Eye01b;
    private final ModelPart Eye02a;
    private final ModelPart Eye02b;
    private final ModelPart Eye03a;
    private final ModelPart Eye03b;
    private final ModelPart JawTooth;
    private final ModelPart Jaw02;
    private final ModelPart Body02;
    private final ModelPart Body01a;
    private final ModelPart Body02a;
    private final ModelPart Body02b;
    private final ModelPart Body03;
    private final ModelPart Body03a;
    private final ModelPart Body03b;
    private final ModelPart Body04;
    private final ModelPart Body04a;
    private final ModelPart Body04b;
    private final ModelPart Bridge02;
    private final ModelPart Bridge01;
    private final ModelPart Head07b;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowJaw;
    private final ModelPart GlowHead01;

    public ModelMountSuH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head04 = this.Neck.getChild("Head04");
        this.Head06 = this.Neck.getChild("Head06");
        this.Head02 = this.Neck.getChild("Head02");
        this.Head07a = this.Neck.getChild("Head07a");
        this.Head07b = this.Head07a.getChild("Head07b");
        this.Head03 = this.Neck.getChild("Head03");
        this.Bridge01 = this.Head03.getChild("Bridge01");
        this.Head05 = this.Neck.getChild("Head05");
        this.Jaw = this.Neck.getChild("Jaw");
        this.Jaw02 = this.Jaw.getChild("Jaw02");
        this.Body01 = this.Neck.getChild("Body01");
        this.Body01a = this.Body01.getChild("Body01a");
        this.Bridge02 = this.Body01a.getChild("Bridge02");
        this.Body02 = this.Body01.getChild("Body02");
        this.Body03 = this.Body02.getChild("Body03");
        this.Body03b = this.Body03.getChild("Body03b");
        this.Body03a = this.Body03.getChild("Body03a");
        this.Body04 = this.Body03.getChild("Body04");
        this.Body04a = this.Body04.getChild("Body04a");
        this.Body04b = this.Body04.getChild("Body04b");
        this.Body02b = this.Body02.getChild("Body02b");
        this.Body02a = this.Body02.getChild("Body02a");
        this.Head01 = this.Neck.getChild("Head01");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowJaw = this.GlowNeck.getChild("GlowJaw");
        this.JawTooth = this.GlowJaw.getChild("JawTooth");
        this.GlowHead01 = this.GlowNeck.getChild("GlowHead01");
        this.HeadTooth = this.GlowHead01.getChild("HeadTooth");
        this.Eye01a = this.GlowHead01.getChild("Eye01a");
        this.Eye01b = this.GlowHead01.getChild("Eye01b");
        this.Eye02a = this.GlowHead01.getChild("Eye02a");
        this.Eye02b = this.GlowHead01.getChild("Eye02b");
        this.Eye03a = this.GlowHead01.getChild("Eye03a");
        this.Eye03b = this.GlowHead01.getChild("Eye03b");
        this.NeckFront = this.GlowNeck.getChild("NeckFront");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 10F, 8F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, -7.5F, -14F, 14F, 15F, 14F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Head04 = Neck.addOrReplaceChild("Head04", CubeListBuilder.create().texOffs(0, 3).addBox(-9.5F, 0F, 0F, 19F, 8F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -23.9F, -29.9F));

        PartDefinition Head06 = Neck.addOrReplaceChild("Head06", CubeListBuilder.create().texOffs(0, 3).addBox(-7.5F, 0F, 0F, 15F, 6F, 11F, new CubeDeformation(0F)), PartPose.offset(0F, -12.1F, -40.8F));

        PartDefinition Head02 = Neck.addOrReplaceChild("Head02", CubeListBuilder.create().texOffs(0, 3).addBox(-9.5F, 0F, 0F, 19F, 10F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -16F, -29.9F));

        PartDefinition Head07a = Neck.addOrReplaceChild("Head07a", CubeListBuilder.create().texOffs(0, 4).addBox(-6F, 0F, -6F, 12F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -23.8F, -41.7F, 0F, 0.7854F, 0F));

        PartDefinition Head07b = Head07a.addOrReplaceChild("Head07b", CubeListBuilder.create().texOffs(0, 4).addBox(-5F, 0F, -5F, 10F, 12F, 10F, new CubeDeformation(0F)), PartPose.offset(-0.7F, 5.5F, 0.7F));

        PartDefinition Head03 = Neck.addOrReplaceChild("Head03", CubeListBuilder.create().texOffs(0, 3).addBox(-9.5F, 0F, 0F, 19F, 8F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -23.9F, -18F));

        PartDefinition Bridge01 = Head03.addOrReplaceChild("Bridge01", CubeListBuilder.create().texOffs(0, 44).addBox(-3.5F, 0F, 0F, 7F, 12F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.1F, 0F, 1.5708F, 0F, 0F));

        PartDefinition Head05 = Neck.addOrReplaceChild("Head05", CubeListBuilder.create().texOffs(0, 3).addBox(-8.5F, 0F, 0F, 17F, 12F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -24F, -41.8F));

        PartDefinition Jaw = Neck.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, 0F, -16F, 15F, 7F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -11.6F, 0.2618F, 0F, 0F));

        PartDefinition Jaw02 = Jaw.addOrReplaceChild("Jaw02", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, 0F, -5.5F, 11F, 5F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.8F, -14.8F, -0.3316F, 0.7854F, -0.2409F));

        PartDefinition Body01 = Neck.addOrReplaceChild("Body01", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -12F, 0F, 17F, 12F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, -8.3F));

        PartDefinition Body01a = Body01.addOrReplaceChild("Body01a", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, 0F, 0F, 14F, 9F, 11F, new CubeDeformation(0F)), PartPose.offset(0F, -20.7F, 0F));

        PartDefinition Bridge02 = Body01a.addOrReplaceChild("Bridge02", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, 0F, 6F, 10F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.1F, 0F, 1.5708F, 0F, 0F));

        PartDefinition Body02 = Body01.addOrReplaceChild("Body02", CubeListBuilder.create().texOffs(0, 3).addBox(-7F, -15F, 0F, 14F, 15F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 6F, -0.2618F, 0F, 0F));

        PartDefinition Body03 = Body02.addOrReplaceChild("Body03", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, -10F, 0F, 16F, 10F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 8F, -0.1745F, 0F, 0F));

        PartDefinition Body03b = Body03.addOrReplaceChild("Body03b", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, 0F, 0F, 11F, 10F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -19.9F, -2F));

        PartDefinition Body03a = Body03.addOrReplaceChild("Body03a", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -6F, 0F, 12F, 6F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 4.5F, 0.4363F, 0F, 0F));

        PartDefinition Body04 = Body03.addOrReplaceChild("Body04", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8F, 0F, 9F, 8F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, 11F, 0.4363F, 0F, 0F));

        PartDefinition Body04a = Body04.addOrReplaceChild("Body04a", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6F, 0F, 5F, 6F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 9.5F));

        PartDefinition Body04b = Body04.addOrReplaceChild("Body04b", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, 0F, 7F, 9F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15.6F, 6F, -0.4363F, 0F, 0F));

        PartDefinition Body02b = Body02.addOrReplaceChild("Body02b", CubeListBuilder.create().texOffs(0, 3).addBox(-4.5F, 0F, 0F, 9F, 7F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -21.8F, -2F));

        PartDefinition Body02a = Body02.addOrReplaceChild("Body02a", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0F, 0F, 13F, 7F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.1F, 0F, 0.2618F, 0F, 0F));

        PartDefinition Head01 = Neck.addOrReplaceChild("Head01", CubeListBuilder.create().texOffs(0, 3).addBox(-9.5F, -7F, -11F, 19F, 10F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, -7F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 10F, 8F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowJaw = GlowNeck.addOrReplaceChild("GlowJaw", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 2F, -11.6F, 0.2618F, 0F, 0F));

        PartDefinition JawTooth = GlowJaw.addOrReplaceChild("JawTooth", CubeListBuilder.create().texOffs(57, 46).mirror().addBox(-6.5F, 0F, -14F, 13F, 3F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.7F, -2F, -0.0873F, -0.0223F, 0F));

        PartDefinition GlowHead01 = GlowNeck.addOrReplaceChild("GlowHead01", CubeListBuilder.create().texOffs(0, 3), PartPose.offset(0F, -9F, -7F));

        PartDefinition HeadTooth = GlowHead01.addOrReplaceChild("HeadTooth", CubeListBuilder.create().texOffs(56, 45).addBox(-6.5F, 0F, -6.5F, 13F, 4F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, -15F, 0.0524F, 0F, 0F));

        PartDefinition Eye01a = GlowHead01.addOrReplaceChild("Eye01a", CubeListBuilder.create().texOffs(77, 0).addBox(0F, 0F, 0F, 0F, 8F, 8F, new CubeDeformation(0F)), PartPose.offset(9.6F, -9F, -15F));

        PartDefinition Eye01b = GlowHead01.addOrReplaceChild("Eye01b", CubeListBuilder.create().texOffs(77, 0).addBox(0F, 0F, 0F, 0F, 8F, 8F, new CubeDeformation(0F)), PartPose.offset(-9.6F, -9F, -15F));

        PartDefinition Eye02a = GlowHead01.addOrReplaceChild("Eye02a", CubeListBuilder.create().texOffs(77, 8).addBox(0F, 0F, 0.1F, 0F, 8F, 8F, new CubeDeformation(0F)), PartPose.offset(9.6F, -9F, -15F));

        PartDefinition Eye02b = GlowHead01.addOrReplaceChild("Eye02b", CubeListBuilder.create().texOffs(77, 8).addBox(0F, 0F, 0F, 0F, 8F, 8F, new CubeDeformation(0F)), PartPose.offset(-9.6F, -9F, -15F));

        PartDefinition Eye03a = GlowHead01.addOrReplaceChild("Eye03a", CubeListBuilder.create().texOffs(77, 16).addBox(0F, 0F, 0F, 0F, 8F, 8F, new CubeDeformation(0F)), PartPose.offset(9.6F, -9F, -15F));

        PartDefinition Eye03b = GlowHead01.addOrReplaceChild("Eye03b", CubeListBuilder.create().texOffs(77, 16).addBox(0F, 0F, 0F, 0F, 8F, 8F, new CubeDeformation(0F)), PartPose.offset(-9.6F, -9F, -15F));

        PartDefinition NeckFront = GlowNeck.addOrReplaceChild("NeckFront", CubeListBuilder.create().texOffs(30, 48).addBox(-5.5F, 0F, 0F, 11F, 14F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -8.5F, -15F));

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
