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

public class ModelRensouhouS<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "rensouhou_s"), "main");

    private final ModelPart BodyMain;
    private final ModelPart HeadBase;
    private final ModelPart TailJaw1;
    private final ModelPart Head;
    private final ModelPart TailHeadCL1;
    private final ModelPart TailHeadCR1;
    private final ModelPart Tooth02;
    private final ModelPart Tube01;
    private final ModelPart Tube02;
    private final ModelPart Tube03;
    private final ModelPart TailHead2;
    private final ModelPart Tooth01;
    private final ModelPart HeadCannon1;
    private final ModelPart HeadCannon2;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHeadBase;
    private final ModelPart GlowHead;
    private final ModelPart GlowTailJaw1;
    private final ModelPart GlowTailHead2;

    public ModelRensouhouS(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.HeadBase = this.BodyMain.getChild("HeadBase");
        this.TailJaw1 = this.HeadBase.getChild("TailJaw1");
        this.Tube03 = this.TailJaw1.getChild("Tube03");
        this.Tube01 = this.TailJaw1.getChild("Tube01");
        this.Tube02 = this.TailJaw1.getChild("Tube02");
        this.Head = this.HeadBase.getChild("Head");
        this.TailHead2 = this.Head.getChild("TailHead2");
        this.TailHeadCR1 = this.HeadBase.getChild("TailHeadCR1");
        this.TailHeadCL1 = this.HeadBase.getChild("TailHeadCL1");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHeadBase = this.GlowBodyMain.getChild("GlowHeadBase");
        this.GlowHead = this.GlowHeadBase.getChild("GlowHead");
        this.GlowTailHead2 = this.GlowHead.getChild("GlowTailHead2");
        this.HeadCannon1 = this.GlowTailHead2.getChild("HeadCannon1");
        this.HeadCannon2 = this.GlowTailHead2.getChild("HeadCannon2");
        this.Tooth01 = this.GlowHead.getChild("Tooth01");
        this.GlowTailJaw1 = this.GlowHeadBase.getChild("GlowTailJaw1");
        this.Tooth02 = this.GlowTailJaw1.getChild("Tooth02");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition HeadBase = BodyMain.addOrReplaceChild("HeadBase", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -8F, 2F, 12F, 15F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 9F));

        PartDefinition TailJaw1 = HeadBase.addOrReplaceChild("TailJaw1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0F, 0F, 13F, 5F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 5.5F, -0.3142F, 0F, 0F));

        PartDefinition Tube03 = TailJaw1.addOrReplaceChild("Tube03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 11F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(-5.5F, 4.6F, 22F));

        PartDefinition Tube01 = TailJaw1.addOrReplaceChild("Tube01", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, 0F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, 3F, 13F, -0.1745F, -0.0524F, 0F));

        PartDefinition Tube02 = TailJaw1.addOrReplaceChild("Tube02", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, 0F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 3F, 13F, -0.1745F, 0.0524F, 0F));

        PartDefinition Head = HeadBase.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, -0.2F, -3.6F, 14F, 8F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8.5F, 4F, 0.1745F, 0F, 0F));

        PartDefinition TailHead2 = Head.addOrReplaceChild("TailHead2", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, 0F, 0F, 14F, 8F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 6.5F));

        PartDefinition TailHeadCR1 = HeadBase.addOrReplaceChild("TailHeadCR1", CubeListBuilder.create().texOffs(36, 25).mirror().addBox(-3F, -3F, -3F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.5F, 0F, 9F, 0.7854F, -0.1396F, 0F));

        PartDefinition TailHeadCL1 = HeadBase.addOrReplaceChild("TailHeadCL1", CubeListBuilder.create().texOffs(36, 25).addBox(0F, -3F, -3F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 0F, 9F, 0.7854F, 0.1396F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowHeadBase = GlowBodyMain.addOrReplaceChild("GlowHeadBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 9F));

        PartDefinition GlowHead = GlowHeadBase.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -8.5F, 4F, 0.1745F, 0F, 0F));

        PartDefinition GlowTailHead2 = GlowHead.addOrReplaceChild("GlowTailHead2", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, 6.5F));

        PartDefinition HeadCannon1 = GlowTailHead2.addOrReplaceChild("HeadCannon1", CubeListBuilder.create().texOffs(26, 6).addBox(-2F, -2F, 0F, 4F, 4F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, 3.5F, 12F, 0.0873F, 0.0873F, 0.0176F));

        PartDefinition HeadCannon2 = GlowTailHead2.addOrReplaceChild("HeadCannon2", CubeListBuilder.create().texOffs(26, 6).addBox(-2F, -2F, 0F, 4F, 4F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, 3.5F, 12F, 0.0873F, -0.0873F, 0F));

        PartDefinition Tooth01 = GlowHead.addOrReplaceChild("Tooth01", CubeListBuilder.create().texOffs(0, 25).addBox(-6F, 0F, 0F, 12F, 5F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.5F, 4.5F, -0.1745F, 0F, 0F));

        PartDefinition GlowTailJaw1 = GlowHeadBase.addOrReplaceChild("GlowTailJaw1", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 0F, 5.5F, -0.3142F, 0F, 0F));

        PartDefinition Tooth02 = GlowTailJaw1.addOrReplaceChild("Tooth02", CubeListBuilder.create().texOffs(2, 42).addBox(-5.5F, 0F, 0F, 11F, 5F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 4F, 0.1745F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
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
