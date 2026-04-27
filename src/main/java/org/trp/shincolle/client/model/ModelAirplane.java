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

public class ModelAirplane<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "airplane"), "main");

    private final ModelPart BodyMain;
    private final ModelPart EyeL;
    private final ModelPart EyeR;
    private final ModelPart AirfoilL;
    private final ModelPart AirfoilR;
    private final ModelPart Head;
    private final ModelPart BodyFront;
    private final ModelPart Tail;
    private final ModelPart Tongue;
    private final ModelPart BombL;
    private final ModelPart BombR;
    private final ModelPart GunBase;
    private final ModelPart Gun;
    private final ModelPart GlowBodyMain;

    public ModelAirplane(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Head = this.BodyMain.getChild("Head");
        this.BombR = this.BodyMain.getChild("BombR");
        this.AirfoilL = this.BodyMain.getChild("AirfoilL");
        this.AirfoilR = this.BodyMain.getChild("AirfoilR");
        this.BombL = this.BodyMain.getChild("BombL");
        this.GunBase = this.BodyMain.getChild("GunBase");
        this.Gun = this.GunBase.getChild("Gun");
        this.Tail = this.BodyMain.getChild("Tail");
        this.BodyFront = this.BodyMain.getChild("BodyFront");
        this.Tongue = this.BodyMain.getChild("Tongue");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.EyeL = this.GlowBodyMain.getChild("EyeL");
        this.EyeR = this.GlowBodyMain.getChild("EyeR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(3, 18).addBox(-3F, -3F, -1F, 6F, 7F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(8, 24).addBox(-2F, -2F, -2F, 4F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -6.2F, 0F, 0.7854F, 0F));

        PartDefinition BombR = BodyMain.addOrReplaceChild("BombR", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 2.3F, -1F, 0F, 0F, 0.7854F));

        PartDefinition AirfoilL = BodyMain.addOrReplaceChild("AirfoilL", CubeListBuilder.create().texOffs(0, 17).addBox(-2.5F, -2F, -6F, 5F, 4F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, 0F, 0F, 0F, 0.5236F, 0.1222F));

        PartDefinition AirfoilR = BodyMain.addOrReplaceChild("AirfoilR", CubeListBuilder.create().texOffs(0, 17).addBox(-2.5F, -2F, -6F, 5F, 4F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, 0F, 0F, 0F, -0.5236F, -0.1222F));

        PartDefinition BombL = BodyMain.addOrReplaceChild("BombL", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 2F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 2.3F, -1F, 0F, 0F, 0.7854F));

        PartDefinition GunBase = BodyMain.addOrReplaceChild("GunBase", CubeListBuilder.create().texOffs(10, 24).addBox(-1.5F, 0F, 0F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 2.5F));

        PartDefinition Gun = GunBase.addOrReplaceChild("Gun", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -8F, 1F, 1F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 0F, 0.0524F, 0F, 0F));

        PartDefinition Tail = BodyMain.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 19).addBox(-4F, -2.5F, -4F, 8F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 4.3F, 0F, 0.7854F, 0F));

        PartDefinition BodyFront = BodyMain.addOrReplaceChild("BodyFront", CubeListBuilder.create().texOffs(12, 6).addBox(-2.5F, -2.6F, -2.5F, 5F, 6F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -3.2F, 0.0873F, 0F, 0F));

        PartDefinition Tongue = BodyMain.addOrReplaceChild("Tongue", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, 0F, -3F, 3F, 1F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.3F, -3.5F, 1.6581F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition EyeL = GlowBodyMain.addOrReplaceChild("EyeL", CubeListBuilder.create().texOffs(16, 0).addBox(-2F, 0F, -2F, 4F, 2F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.7F, -3.2F, 2F, 0F, 0.7854F, 0.1745F));

        PartDefinition EyeR = GlowBodyMain.addOrReplaceChild("EyeR", CubeListBuilder.create().texOffs(16, 0).addBox(-2F, 0F, -2F, 4F, 2F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.7F, -3.2F, 2F, 0F, -2.3562F, -0.1745F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float yaw = netHeadYaw * ((float) Math.PI / 180F);
        float pitch = headPitch * ((float) Math.PI / 180F);

        BodyMain.yRot = yaw;
        BodyMain.xRot = pitch;
        syncGlowParts();
    }

    private void syncGlowParts() {
        GlowBodyMain.copyFrom(BodyMain);
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
