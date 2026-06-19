package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class ModelPlayerAccessory<T extends LivingEntity> extends EntityModel<T> {
    private final ModelPart bone;

    public ModelPlayerAccessory(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create()
                .texOffs(23, 39).addBox(14.5F, -6.0F, 0.2F, 4F, 6F, 5F)
                .texOffs(23, 31).addBox(14.5F, -9.0F, 0.2F, 4F, 3F, 5F)
                .texOffs(56, 33).addBox(16.7F, -7.0F, 0.5F, 2F, 2F, 4F)
                .texOffs(0, 1).addBox(-4.5F, -11.0F, -16.6F, 9F, 8F, 9F)
                .texOffs(32, 4).addBox(7.5F, -15.0F, -13.7F, 1F, 2F, 6F)
                .texOffs(29, 4).addBox(3.5F, -15.0F, -13.7F, 4F, 7F, 6F)
                .texOffs(37, 0).addBox(5.5F, -36.0F, -12.7F, 1F, 21F, 1F)
                .texOffs(64, 24).addBox(4.5F, -26.0F, -13.7F, 3F, 2F, 3F)
                .texOffs(64, 24).addBox(4.5F, -34.0F, -13.7F, 3F, 1F, 3F)
                .texOffs(47, 42).addBox(4.5F, -39.0F, -13.7F, 3F, 3F, 3F)
                .texOffs(40, 13).addBox(4.5F, -45.0F, -12.7F, 1F, 12F, 1F)
                .texOffs(26, 13).addBox(-1.5F, -32.0F, -12.7F, 15F, 1F, 1F)
                .texOffs(32, 13).addBox(0.5F, -42.0F, -12.7F, 9F, 1F, 1F)
                .mirror()
                .texOffs(23, 39).addBox(-18.5F, -6.0F, 0.2F, 4F, 6F, 5F)
                .texOffs(56, 33).addBox(-18.7F, -7.0F, 0.5F, 2F, 2F, 4F)
                .texOffs(23, 31).addBox(-18.5F, -9.0F, 0.2F, 4F, 3F, 5F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().mirror().texOffs(24, 0).addBox(-0.1F, 21.0F, -3.7F, 3F, 3F, 9F), PartPose.offsetAndRotation(-6.0F, -28.0F, -8.0F, 0.0F, -0.7854F, 0.0F));
        bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().mirror().texOffs(26, 0).addBox(-0.7F, 21.0F, -0.5F, 2F, 3F, 8F), PartPose.offsetAndRotation(-9.0F, -28.0F, -4.0F, 0.0F, 0.2182F, 0.0F));
        bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().mirror().texOffs(7, 9).addBox(-0.1F, 21.2F, 6.4F, 4F, 3F, 1F), PartPose.offsetAndRotation(-19.0F, -33.0F, 2.0F, -0.0175F, 0.2618F, 0.0F));
        
        bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().mirror()
                .texOffs(47, 34).addBox(1.0F, 23.5F, 0.0F, 1F, 1F, 6F)
                .texOffs(47, 34).addBox(3.0F, 23.5F, 0.0F, 1F, 1F, 6F),
                PartPose.offsetAndRotation(-19.0F, -35.0F, 2.0F, 0.1745F, 0.1745F, 0.0F));
                
        bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().mirror()
                .texOffs(48, 30).addBox(2.2F, 23.7F, 1.5F, 1F, 1F, 1F)
                .texOffs(48, 30).addBox(-0.2F, 23.7F, 1.5F, 1F, 1F, 1F),
                PartPose.offsetAndRotation(-18.0F, -38.0F, 2.0F, -0.0175F, 0.1745F, 0.0F));

        bone.addOrReplaceChild("cube_r6", CubeListBuilder.create().mirror()
                .texOffs(0, 4).addBox(-0.9F, 20.5F, -6.0F, 4F, 4F, 6F)
                .texOffs(4, 5).addBox(-1.5F, 20.5F, -5.5F, 1F, 4F, 5F),
                PartPose.offsetAndRotation(-16.0F, -33.0F, -4.0F, 0.0F, -0.6109F, 0.0F));

        bone.addOrReplaceChild("cube_r7", CubeListBuilder.create().mirror().texOffs(56, 30).addBox(-0.5F, 23.5F, -3.0F, 5F, 1F, 1F), PartPose.offsetAndRotation(-20.0F, -35.0F, -6.0F, 0.0F, -0.2618F, 0.0436F));
        bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().mirror().texOffs(-2, 2).addBox(-0.5F, 21.6F, -3.0F, 6F, 3F, 8F), PartPose.offsetAndRotation(-19.0F, -35.0F, 2.0F, -0.0175F, 0.1745F, 0.0F));
        bone.addOrReplaceChild("cube_r9", CubeListBuilder.create().mirror().texOffs(0, 0).addBox(-1.0F, 22.5F, -1.8F, 7F, 2F, 7F), PartPose.offsetAndRotation(-19.0F, -33.0F, 2.0F, 0.0F, 0.1745F, 0.0F));

        bone.addOrReplaceChild("cube_r10", CubeListBuilder.create().mirror()
                .texOffs(25, 31).addBox(-0.7F, 21.0F, -1.1F, 4F, 3F, 5F)
                .texOffs(25, 39).addBox(-0.7F, 24.0F, -1.1F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(-17.5F, -30.0F, 6.0F, 0.0F, 0.3491F, 0.0F));

        bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().mirror()
                .texOffs(18, 31).addBox(-0.2F, 21.0F, 1.7F, 4F, 3F, 5F)
                .texOffs(16, 39).addBox(-0.2F, 24.0F, 1.7F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(-16.0F, -30.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

        bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().mirror().texOffs(46, 30).addBox(-1.0F, 24.5F, -1.0F, 4F, 1F, 1F), PartPose.offsetAndRotation(-21.0F, -31.0F, 4.0F, 0.0F, 0.3927F, 0.0F));

        bone.addOrReplaceChild("cube_r13", CubeListBuilder.create().mirror()
                .texOffs(12, 31).addBox(0.3F, 21.0F, -3.3F, 4F, 3F, 5F)
                .texOffs(10, 39).addBox(0.3F, 24.0F, -3.3F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(-16.0F, -30.0F, -6.0F, 0.0F, -0.6109F, 0.0F));

        bone.addOrReplaceChild("cube_r14", CubeListBuilder.create().mirror().texOffs(46, 30).addBox(-0.8F, 24.5F, -5.5F, 4F, 1F, 1F), PartPose.offsetAndRotation(-20.0F, -31.0F, -8.0F, 0.0F, -1.0472F, 0.0F));
        bone.addOrReplaceChild("cube_r15", CubeListBuilder.create().mirror().texOffs(56, 33).addBox(0.2F, 24.0F, -6.2F, 2F, 2F, 4F), PartPose.offsetAndRotation(-17.0F, -31.0F, -5.0F, 0.0F, -0.6545F, 0.0F));

        bone.addOrReplaceChild("cube_r16", CubeListBuilder.create().mirror()
                .texOffs(10, 31).addBox(-0.3F, 21.0F, -8.3F, 4F, 3F, 5F)
                .texOffs(10, 39).addBox(-0.3F, 24.0F, -8.3F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(-16.0F, -30.0F, -6.0F, 0.0F, -0.7854F, 0.0F));

        bone.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(26, 0).addBox(-1.3F, 21.0F, -0.5F, 2F, 3F, 8F), PartPose.offsetAndRotation(9.0F, -28.0F, -4.0F, 0.0F, -0.2182F, 0.0F));
        bone.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(24, 0).addBox(-2.9F, 21.0F, -3.7F, 3F, 3F, 9F), PartPose.offsetAndRotation(6.0F, -28.0F, -8.0F, 0.0F, 0.7854F, 0.0F));
        bone.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(27, 0).addBox(-4.9F, 19.1F, -2.5F, 4F, 5F, 7F), PartPose.offsetAndRotation(7.0F, -28.0F, -15.0F, 0.0F, 1.0297F, 0.0F));

        bone.addOrReplaceChild("cube_r20", CubeListBuilder.create()
                .texOffs(21, 53).addBox(-4.0F, 4.0F, -4.1F, 8F, 4F, 8F)
                .texOffs(4, 3).addBox(-3.5F, 8.0F, -3.6F, 7F, 16F, 7F),
                PartPose.offsetAndRotation(0.0F, -34.0F, -12.0F, 0.1309F, 0.0F, 0.0F));

        bone.addOrReplaceChild("cube_r21", CubeListBuilder.create()
                .texOffs(4, 5).addBox(0.5F, 20.5F, -5.5F, 1F, 4F, 5F)
                .texOffs(0, 4).addBox(-3.1F, 20.5F, -6.0F, 4F, 4F, 6F),
                PartPose.offsetAndRotation(16.0F, -33.0F, -4.0F, 0.0F, 0.6109F, 0.0F));

        bone.addOrReplaceChild("cube_r22", CubeListBuilder.create()
                .texOffs(47, 34).addBox(-4.0F, 23.5F, 0.0F, 1F, 1F, 6F)
                .texOffs(47, 34).addBox(-2.0F, 23.5F, 0.0F, 1F, 1F, 6F),
                PartPose.offsetAndRotation(19.0F, -35.0F, 2.0F, 0.1745F, -0.1745F, 0.0F));

        bone.addOrReplaceChild("cube_r23", CubeListBuilder.create()
                .texOffs(48, 30).addBox(-0.8F, 23.7F, 1.5F, 1F, 1F, 1F)
                .texOffs(48, 30).addBox(-3.2F, 23.7F, 1.5F, 1F, 1F, 1F),
                PartPose.offsetAndRotation(18.0F, -38.0F, 2.0F, -0.0175F, -0.1745F, 0.0F));

        bone.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(7, 9).addBox(-3.9F, 21.2F, 6.4F, 4F, 3F, 1F), PartPose.offsetAndRotation(19.0F, -33.0F, 2.0F, -0.0175F, -0.2618F, 0.0F));
        bone.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(-2, 2).addBox(-5.5F, 21.6F, -3.0F, 6F, 3F, 8F), PartPose.offsetAndRotation(19.0F, -35.0F, 2.0F, -0.0175F, -0.1745F, 0.0F));
        bone.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 22.5F, -1.8F, 7F, 2F, 7F), PartPose.offsetAndRotation(19.0F, -33.0F, 2.0F, 0.0F, -0.1745F, 0.0F));
        bone.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(56, 30).addBox(-4.5F, 23.5F, -3.0F, 5F, 1F, 1F), PartPose.offsetAndRotation(20.0F, -35.0F, -6.0F, 0.0F, 0.2618F, -0.0436F));
        bone.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(46, 30).addBox(-3.2F, 24.5F, -5.5F, 4F, 1F, 1F), PartPose.offsetAndRotation(20.0F, -31.0F, -8.0F, 0.0F, 1.0472F, 0.0F));
        bone.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(46, 30).addBox(-3.0F, 24.5F, -1.0F, 4F, 1F, 1F), PartPose.offsetAndRotation(21.0F, -31.0F, 4.0F, 0.0F, -0.3927F, 0.0F));
        bone.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(56, 33).addBox(-2.2F, 24.0F, -6.2F, 2F, 2F, 4F), PartPose.offsetAndRotation(17.0F, -31.0F, -5.0F, 0.0F, 0.6545F, 0.0F));

        bone.addOrReplaceChild("cube_r31", CubeListBuilder.create()
                .texOffs(12, 31).addBox(-4.3F, 21.0F, -3.3F, 4F, 3F, 5F)
                .texOffs(10, 39).addBox(-4.3F, 24.0F, -3.3F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(16.0F, -30.0F, -6.0F, 0.0F, 0.6109F, 0.0F));

        bone.addOrReplaceChild("cube_r32", CubeListBuilder.create()
                .texOffs(18, 31).addBox(-3.8F, 21.0F, 1.7F, 4F, 3F, 5F)
                .texOffs(16, 39).addBox(-3.8F, 24.0F, 1.7F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(16.0F, -30.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

        bone.addOrReplaceChild("cube_r33", CubeListBuilder.create()
                .texOffs(25, 31).addBox(-3.3F, 21.0F, -1.1F, 4F, 3F, 5F)
                .texOffs(25, 39).addBox(-3.3F, 24.0F, -1.1F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(17.5F, -30.0F, 6.0F, 0.0F, -0.3491F, 0.0F));

        bone.addOrReplaceChild("cube_r34", CubeListBuilder.create()
                .texOffs(10, 31).addBox(-3.7F, 21.0F, -8.3F, 4F, 3F, 5F)
                .texOffs(10, 39).addBox(-3.7F, 24.0F, -8.3F, 4F, 6F, 5F),
                PartPose.offsetAndRotation(16.0F, -30.0F, -6.0F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
