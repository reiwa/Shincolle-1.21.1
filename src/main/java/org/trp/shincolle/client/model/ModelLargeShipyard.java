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

public class ModelLargeShipyard extends EntityModel<Entity> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "large_shipyard"), "main");

    private final ModelPart root;

    public ModelLargeShipyard(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition body = part.addOrReplaceChild("body_main",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 18.0F, 0.0F));

        body.addOrReplaceChild("base00",
                CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, 0.0F, -24.0F, 16.0F, 6.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base01",
                CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -2.0F, -24.0F, 16.0F, 8.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base02",
                CubeListBuilder.create().texOffs(0, 0).addBox(8.0F, -1.0F, -24.0F, 16.0F, 7.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base03",
                CubeListBuilder.create().texOffs(0, 0).addBox(8.0F, 0.0F, -8.0F, 16.0F, 6.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base04",
                CubeListBuilder.create().texOffs(0, 0).addBox(8.0F, -3.0F, 8.0F, 16.0F, 9.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base05",
                CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -2.0F, 8.0F, 16.0F, 8.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base06",
                CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -1.0F, 8.0F, 16.0F, 7.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base07",
                CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -2.0F, -8.0F, 16.0F, 8.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        body.addOrReplaceChild("base08",
                CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 1.0F, -8.0F, 16.0F, 5.0F, 16.0F, CubeDeformation.NONE),
                PartPose.ZERO);

        PartDefinition body01 = body.addOrReplaceChild("body01",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 6.0F, 14.0F, CubeDeformation.NONE),
                PartPose.offset(7.0F, -6.0F, 6.0F));
        body.addOrReplaceChild("body02",
                CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -4.9F, 11.0F, 18.0F, 5.0F, 11.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        PartDefinition body03 = body.addOrReplaceChild("body03",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 15.0F, 7.0F, 13.0F, CubeDeformation.NONE),
                PartPose.offset(-20.6F, -7.0F, 8.0F));
        body.addOrReplaceChild("body04",
                CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, -4.0F, -10.0F, 12.0F, 4.0F, 20.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        PartDefinition body05 = body.addOrReplaceChild("body05",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 12.0F, 5.0F, 15.0F, CubeDeformation.NONE),
                PartPose.offset(-20.0F, -5.0F, -22.0F));
        body.addOrReplaceChild("body06",
                CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -6.0F, -23.0F, 20.0F, 6.0F, 10.0F, CubeDeformation.NONE),
                PartPose.ZERO);
        PartDefinition body07 = body.addOrReplaceChild("body07",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 12.0F, 7.0F, 14.0F, CubeDeformation.NONE),
                PartPose.offset(10.0F, -7.0F, -20.0F));
        body.addOrReplaceChild("body08",
                CubeListBuilder.create().texOffs(0, 0).addBox(15.0F, -5.0F, -10.0F, 8.0F, 5.0F, 18.0F, CubeDeformation.NONE),
                PartPose.ZERO);

        PartDefinition pillar01a = body01.addOrReplaceChild("pillar01a",
                CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(7.0F, 2.0F, 7.0F, 0.17453292F, 0.0F, -0.17453292F));
        pillar01a.addOrReplaceChild("pillar01b",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -13.3F, -3.0F, 7.0F, 8.0F, 7.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.17453292F, 0.0F, -0.17453292F));

        PartDefinition pillar01a_1 = body07.addOrReplaceChild("pillar01a_1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 9.0F, 10.0F, 9.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(5.0F, 2.0F, 6.0F, -0.17453292F, 0.0F, -0.17453292F));
        pillar01a_1.addOrReplaceChild("pillar01b_1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 8.0F, 6.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -9.0F, 0.5F, -0.17453292F, 0.0F, -0.17453292F));

        PartDefinition pillar02a = body03.addOrReplaceChild("pillar02a",
                CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -10.0F, -4.5F, 11.0F, 10.0F, 9.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(8.0F, 2.0F, 6.0F, 0.17453292F, 0.0F, 0.17453292F));
        pillar02a.addOrReplaceChild("pillar02b",
                CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -6.0F, -4.0F, 8.0F, 8.0F, 7.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(2.0F, -10.6F, 0.5F, 0.17453292F, 0.0F, 0.17453292F));

        PartDefinition pillar03a = body05.addOrReplaceChild("pillar03a",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.0F, -5.0F, 9.0F, 9.0F, 10.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(6.0F, 1.0F, 7.0F, -0.17453292F, 0.0F, 0.17453292F));
        pillar03a.addOrReplaceChild("pillar03b",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 8.0F, 6.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, -8.5F, 0.0F, -0.17453292F, 0.0F, 0.17453292F));

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
