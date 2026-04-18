package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.trp.shincolle.Shincolle;

public class ModelSmallShipyard extends EntityModel<Entity> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "small_shipyard"), "main");

    private final ModelPart root;

    public ModelSmallShipyard(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("shape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape2",
                CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, -4.0F, -3.0F, 14.0F, 3.0F, 10.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape3",
                CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -7.0F, -1.5F, 6.0F, 4.0F, 6.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape4",
                CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -13.0F, -1.0F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape5",
                CubeListBuilder.create().texOffs(48, 6).addBox(-4.5F, -16.0F, -0.5F, 3.0F, 3.0F, 3.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape6",
                CubeListBuilder.create().texOffs(32, 20).addBox(-3.5F, -6.0F, 0.0F, 10.0F, 3.0F, 6.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape7",
                CubeListBuilder.create().texOffs(0, 10).addBox(2.0F, -9.0F, 1.5F, 4.0F, 3.0F, 4.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape8",
                CubeListBuilder.create().texOffs(48, 12).addBox(2.5F, -11.5F, 2.0F, 3.0F, 3.0F, 3.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape9",
                CubeListBuilder.create().texOffs(0, 17).addBox(-5.0F, -3.0F, -7.0F, 11.0F, 2.0F, 4.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape10",
                CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -5.0F, -6.5F, 4.0F, 2.0F, 4.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        part.addOrReplaceChild("shape11",
                CubeListBuilder.create().texOffs(48, 0).addBox(-0.5F, -8.0F, -6.0F, 3.0F, 3.0F, 3.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
