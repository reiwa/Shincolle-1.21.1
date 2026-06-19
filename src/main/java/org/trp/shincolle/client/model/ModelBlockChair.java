package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;

public class ModelBlockChair extends Model {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "blockchair"), "main");

    private final ModelPart root;

    public ModelBlockChair(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("bone",
                CubeListBuilder.create()
                        .texOffs(29, 25).addBox(-10.0F, -2.0F, 13.0F, 11, 2, 2, CubeDeformation.NONE)
                        .texOffs(56, 6).addBox(-12.0F, -5.0F, 13.0F, 2, 13, 2, CubeDeformation.NONE)
                        .texOffs(56, 25).addBox(1.0F, -21.0F, -1.0F, 2, 29, 2, CubeDeformation.NONE)
                        .texOffs(56, 25).addBox(1.0F, -21.0F, 13.0F, 2, 29, 2, CubeDeformation.NONE)
                        .texOffs(56, 6).addBox(-12.0F, -5.0F, -1.0F, 2, 13, 2, CubeDeformation.NONE)
                        .texOffs(0, 33).addBox(-12.0F, -2.0F, 1.0F, 13, 2, 12, CubeDeformation.NONE)
                        .texOffs(36, 10).addBox(1.0F, -2.0F, 1.0F, 2, 2, 12, CubeDeformation.NONE)
                        .texOffs(0, 0).addBox(1.0F, -22.0F, 1.0F, 2, 20, 12, CubeDeformation.NONE)
                        .texOffs(32, 0).addBox(-12.0F, -7.0F, -1.0F, 13, 2, 3, CubeDeformation.NONE)
                        .texOffs(32, 0).addBox(-12.0F, -7.0F, 12.0F, 13, 2, 3, CubeDeformation.NONE)
                        .texOffs(29, 25).addBox(-10.0F, -2.0F, -1.0F, 11, 2, 2, CubeDeformation.NONE),
                PartPose.offsetAndRotation(7.0F, 16.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
