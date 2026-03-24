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

public class ModelTakoyaki<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "takoyaki"), "main");

    private final ModelPart BodyMain;
    private final ModelPart JawMain;
    private final ModelPart EyeL;
    private final ModelPart Body1;
    private final ModelPart Body2;
    private final ModelPart Body3;
    private final ModelPart EarL;
    private final ModelPart EarR;
    private final ModelPart Jaw1;
    private final ModelPart Jaw2;
    private final ModelPart Jaw3;
    private final ModelPart Tongue;
    private final ModelPart GlowBodyMain;

    public ModelTakoyaki(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Body1 = this.BodyMain.getChild("Body1");
        this.JawMain = this.BodyMain.getChild("JawMain");
        this.Jaw1 = this.JawMain.getChild("Jaw1");
        this.Jaw2 = this.JawMain.getChild("Jaw2");
        this.Jaw3 = this.JawMain.getChild("Jaw3");
        this.Tongue = this.JawMain.getChild("Tongue");
        this.Body3 = this.BodyMain.getChild("Body3");
        this.Body2 = this.BodyMain.getChild("Body2");
        this.EarL = this.BodyMain.getChild("EarL");
        this.EarR = this.BodyMain.getChild("EarR");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.EyeL = this.GlowBodyMain.getChild("EyeL");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(76, 42).addBox(-6.5F, -6.4F, -6.5F, 13F, 9F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -0.3491F, 0F, 0F));

        PartDefinition Body1 = BodyMain.addOrReplaceChild("Body1", CubeListBuilder.create().texOffs(76, 19).addBox(-5F, -4.3F, -8F, 10F, 7F, 16F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition JawMain = BodyMain.addOrReplaceChild("JawMain", CubeListBuilder.create().texOffs(0, 38).addBox(-6.5F, -1.1F, -8F, 13F, 6F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, 3F, 1.3F, 0F, 0F));

        PartDefinition Jaw1 = JawMain.addOrReplaceChild("Jaw1", CubeListBuilder.create().texOffs(0, 17).addBox(-5F, 0F, 0F, 10F, 5F, 16F, new CubeDeformation(0F)), PartPose.offset(0F, -1.2F, -9.5F));

        PartDefinition Jaw2 = JawMain.addOrReplaceChild("Jaw2", CubeListBuilder.create().texOffs(0, 2).addBox(-8F, 0F, 0F, 16F, 5F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -6.5F));

        PartDefinition Jaw3 = JawMain.addOrReplaceChild("Jaw3", CubeListBuilder.create().texOffs(42, 0).addBox(-5F, 5F, -5.5F, 10F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Tongue = JawMain.addOrReplaceChild("Tongue", CubeListBuilder.create().texOffs(50, 39).addBox(-4.5F, 0F, -7F, 9F, 3F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, 0.5F, -0.0873F, 0F, 0F));

        PartDefinition Body3 = BodyMain.addOrReplaceChild("Body3", CubeListBuilder.create().texOffs(76, 1).addBox(-8F, -5F, -5F, 16F, 8F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -0.5F, 0F));

        PartDefinition Body2 = BodyMain.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(54, 19).addBox(-5F, -8.5F, -4.5F, 10F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.0136F));

        PartDefinition EarL = BodyMain.addOrReplaceChild("EarL", CubeListBuilder.create().texOffs(114, 20).mirror().addBox(-2F, -8F, -1.5F, 4F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, -4.5F, 3F, -0.5236F, -0.5236F, 0.7854F));

        PartDefinition EarR = BodyMain.addOrReplaceChild("EarR", CubeListBuilder.create().texOffs(114, 20).addBox(-2F, -8F, -1.5F, 4F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.5F, -4.5F, 3F, -0.5236F, 0.5236F, -0.7854F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition EyeL = GlowBodyMain.addOrReplaceChild("EyeL", CubeListBuilder.create().texOffs(65, 50).addBox(0F, -3F, -3F, 0F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.1F, -3.3F, 0.5F, -0.1745F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
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
