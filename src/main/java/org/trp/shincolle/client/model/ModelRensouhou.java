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

public class ModelRensouhou<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "rensouhou"), "main");

    private final ModelPart BodyMain;
    private final ModelPart SwimRing;
    private final ModelPart Head;
    private final ModelPart ArmLeft;
    private final ModelPart ArmRight;
    private final ModelPart LegLeft;
    private final ModelPart LegRight;
    private final ModelPart Propeller;
    private final ModelPart EarL;
    private final ModelPart EarR;
    private final ModelPart HeadBack;
    private final ModelPart Radar;
    private final ModelPart CannonL01;
    private final ModelPart CannonR01;
    private final ModelPart Face0;
    private final ModelPart Face1;
    private final ModelPart Face2;
    private final ModelPart CannonL02;
    private final ModelPart CannonR02;

    public ModelRensouhou(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmLeft = this.BodyMain.getChild("ArmLeft");
        this.ArmRight = this.BodyMain.getChild("ArmRight");
        this.SwimRing = this.BodyMain.getChild("SwimRing");
        this.Propeller = this.SwimRing.getChild("Propeller");
        this.LegRight = this.SwimRing.getChild("LegRight");
        this.LegLeft = this.SwimRing.getChild("LegLeft");
        this.Head = this.BodyMain.getChild("Head");
        this.Face2 = this.Head.getChild("Face2");
        this.EarL = this.Head.getChild("EarL");
        this.CannonR01 = this.Head.getChild("CannonR01");
        this.CannonR02 = this.CannonR01.getChild("CannonR02");
        this.Face1 = this.Head.getChild("Face1");
        this.Radar = this.Head.getChild("Radar");
        this.CannonL01 = this.Head.getChild("CannonL01");
        this.CannonL02 = this.CannonL01.getChild("CannonL02");
        this.HeadBack = this.Head.getChild("HeadBack");
        this.Face0 = this.Head.getChild("Face0");
        this.EarR = this.Head.getChild("EarR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, -6F, -5F, 10F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 0F));

        PartDefinition ArmLeft = BodyMain.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -8F, 5F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, -4F, -4F, 1.0472F, -0.5236F, 0F));

        PartDefinition ArmRight = BodyMain.addOrReplaceChild("ArmRight", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -8F, 5F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -4F, -4F, 1.0472F, 0.5236F, 0F));

        PartDefinition SwimRing = BodyMain.addOrReplaceChild("SwimRing", CubeListBuilder.create().texOffs(0, 29).addBox(-9F, 0F, -9F, 18F, 7F, 18F, new CubeDeformation(0F)), PartPose.offset(0F, 5F, 0F));

        PartDefinition Propeller = SwimRing.addOrReplaceChild("Propeller", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -2.5F, 0F, 5F, 5F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 9F));

        PartDefinition LegRight = SwimRing.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -7F, 5F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 6F, 0F, 0.5236F, 0.3491F, 0F));

        PartDefinition LegLeft = SwimRing.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -7F, 5F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 6F, 0F, 0.5236F, -0.3491F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(56, 37).addBox(-9F, -8F, -9F, 18F, 9F, 18F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, 0F));

        PartDefinition Face2 = Head.addOrReplaceChild("Face2", CubeListBuilder.create().texOffs(88, 0).addBox(-8.5F, 0F, 0F, 17F, 9F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, -9.1F));

        PartDefinition EarL = Head.addOrReplaceChild("EarL", CubeListBuilder.create().texOffs(55, 20).addBox(-2F, 0F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(7F, -11F, -9F));

        PartDefinition CannonR01 = Head.addOrReplaceChild("CannonR01", CubeListBuilder.create().texOffs(54, 36).addBox(-2F, -2F, -6F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(-2.5F, -9F, -2F));

        PartDefinition CannonR02 = CannonR01.addOrReplaceChild("CannonR02", CubeListBuilder.create().texOffs(0, 1).addBox(-1.5F, -1.5F, -26F, 3F, 3F, 20F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Face1 = Head.addOrReplaceChild("Face1", CubeListBuilder.create().texOffs(54, 9).addBox(-8.5F, 0F, 0F, 17F, 9F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, -9.1F));

        PartDefinition Radar = Head.addOrReplaceChild("Radar", CubeListBuilder.create().texOffs(0, 37).addBox(0F, 0F, 0F, 4F, 4F, 5F, new CubeDeformation(0F)), PartPose.offset(5F, -15F, -5F));

        PartDefinition CannonL01 = Head.addOrReplaceChild("CannonL01", CubeListBuilder.create().texOffs(54, 36).addBox(-2F, -2F, -6F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(2.5F, -9F, -2F));

        PartDefinition CannonL02 = CannonL01.addOrReplaceChild("CannonL02", CubeListBuilder.create().texOffs(0, 1).addBox(-1.5F, -1.5F, -26F, 3F, 3F, 20F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition HeadBack = Head.addOrReplaceChild("HeadBack", CubeListBuilder.create().texOffs(70, 22).addBox(-9F, 0F, 0F, 18F, 4F, 11F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, -2F));

        PartDefinition Face0 = Head.addOrReplaceChild("Face0", CubeListBuilder.create().texOffs(54, 0).addBox(-8.5F, 0F, 0F, 17F, 9F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, -9.1F));

        PartDefinition EarR = Head.addOrReplaceChild("EarR", CubeListBuilder.create().texOffs(55, 20).mirror().addBox(-2F, 0F, 0F, 4F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(-7F, -11F, -9F));

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
}
