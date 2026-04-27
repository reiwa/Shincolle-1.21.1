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

public class ModelAirplaneZero<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "airplane_zero"), "main");

    private final ModelPart BodyMain;
    private final ModelPart Tail01;
    private final ModelPart Wing01;
    private final ModelPart Wing02;
    private final ModelPart BodyU;
    private final ModelPart Propeller;
    private final ModelPart Prop02;
    private final ModelPart Tank;
    private final ModelPart Tail02;
    private final ModelPart Tail03;
    private final ModelPart Tail04;
    private final ModelPart GlowBodyMain;

    public ModelAirplaneZero(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Propeller = this.BodyMain.getChild("Propeller");
        this.Wing02 = this.BodyMain.getChild("Wing02");
        this.Prop02 = this.BodyMain.getChild("Prop02");
        this.Tail01 = this.BodyMain.getChild("Tail01");
        this.Tail02 = this.Tail01.getChild("Tail02");
        this.Tail03 = this.Tail02.getChild("Tail03");
        this.Tail04 = this.Tail02.getChild("Tail04");
        this.Wing01 = this.BodyMain.getChild("Wing01");
        this.Tank = this.BodyMain.getChild("Tank");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.BodyU = this.GlowBodyMain.getChild("BodyU");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 17).addBox(-2F, -3F, -6F, 4F, 4F, 11F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Propeller = BodyMain.addOrReplaceChild("Propeller", CubeListBuilder.create().texOffs(0, 6).addBox(-3F, -3F, 0F, 6F, 6F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -6.5F));

        PartDefinition Wing02 = BodyMain.addOrReplaceChild("Wing02", CubeListBuilder.create().texOffs(0, 0).addBox(-13F, 0F, 0F, 13F, 1F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -0.4F, -3.2F, 0F, 0F, 0.0698F));

        PartDefinition Prop02 = BodyMain.addOrReplaceChild("Prop02", CubeListBuilder.create().texOffs(0, 24).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -7.5F));

        PartDefinition Tail01 = BodyMain.addOrReplaceChild("Tail01", CubeListBuilder.create().texOffs(30, 25).addBox(-2F, 0F, 0F, 4F, 3F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -2.8F, 5F));

        PartDefinition Tail02 = Tail01.addOrReplaceChild("Tail02", CubeListBuilder.create().texOffs(46, 24).addBox(-1.5F, 0F, 0F, 3F, 2F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.1F, 4F));

        PartDefinition Tail03 = Tail02.addOrReplaceChild("Tail03", CubeListBuilder.create().texOffs(0, 17).addBox(-0.5F, 0F, 0F, 1F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2.2F, 4.5F, -1.0472F, 0F, 0F));

        PartDefinition Tail04 = Tail02.addOrReplaceChild("Tail04", CubeListBuilder.create().texOffs(0, 13).addBox(-6.5F, 0F, 0F, 13F, 1F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, 2F));

        PartDefinition Wing01 = BodyMain.addOrReplaceChild("Wing01", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 13F, 1F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -0.4F, -3.2F, 0F, 0F, -0.0698F));

        PartDefinition Tank = BodyMain.addOrReplaceChild("Tank", CubeListBuilder.create().texOffs(14, 7).addBox(-1F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 0.5F, -3F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 17), PartPose.offset(0F, 0F, 0F));

        PartDefinition BodyU = GlowBodyMain.addOrReplaceChild("BodyU", CubeListBuilder.create().texOffs(19, 17).addBox(-1.5F, 0F, 0F, 3F, 2F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.9F, -1.8F, -0.3142F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
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
