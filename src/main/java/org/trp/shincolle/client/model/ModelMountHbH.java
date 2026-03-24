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

public class ModelMountHbH<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_hb_h"), "main");

    private final ModelPart BodyMain;
    private final ModelPart EquipBaseR;
    private final ModelPart Back01;
    private final ModelPart Back02;
    private final ModelPart EquipBaseL;
    private final ModelPart EquipR01;
    private final ModelPart Back01b;
    private final ModelPart Back02b;
    private final ModelPart Back02c;
    private final ModelPart Back02d;
    private final ModelPart Back02e;
    private final ModelPart Neck;
    private final ModelPart Head;
    private final ModelPart Jaw;
    private final ModelPart HeadTooth;
    private final ModelPart Road01;
    private final ModelPart Road02;
    private final ModelPart Road03;
    private final ModelPart Road04;
    private final ModelPart Road05;
    private final ModelPart JawTooth;
    private final ModelPart EquipL01;
    private final ModelPart EquipCannonPlate;
    private final ModelPart CanonBase;
    private final ModelPart EquipCannon01;
    private final ModelPart Neck_1;
    private final ModelPart Head_1;
    private final ModelPart Jaw_1;
    private final ModelPart Road01u;
    private final ModelPart Road01v;
    private final ModelPart HeadTooth_1;
    private final ModelPart JawTooth_1;
    private final ModelPart Road02u;
    private final ModelPart Road03u;
    private final ModelPart Road02v;
    private final ModelPart Road03v;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowEquipBaseL;
    private final ModelPart GlowEquipL01;
    private final ModelPart GlowEquipCannonPlate;
    private final ModelPart GlowBack02;
    private final ModelPart GlowBack02b;
    private final ModelPart GlowBack02c;
    private final ModelPart GlowBack02d;
    private final ModelPart GlowBack02e;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowJaw;
    private final ModelPart GlowCanonBase;
    private final ModelPart GlowNeck_1;
    private final ModelPart GlowHead_1;
    private final ModelPart GlowJaw_1;

    public ModelMountHbH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Back01 = this.BodyMain.getChild("Back01");
        this.Back01b = this.Back01.getChild("Back01b");
        this.EquipBaseL = this.BodyMain.getChild("EquipBaseL");
        this.EquipL01 = this.EquipBaseL.getChild("EquipL01");
        this.CanonBase = this.EquipL01.getChild("CanonBase");
        this.Neck_1 = this.CanonBase.getChild("Neck_1");
        this.Jaw_1 = this.Neck_1.getChild("Jaw_1");
        this.Head_1 = this.Neck_1.getChild("Head_1");
        this.EquipCannonPlate = this.EquipL01.getChild("EquipCannonPlate");
        this.Back02 = this.BodyMain.getChild("Back02");
        this.Back02b = this.Back02.getChild("Back02b");
        this.Back02c = this.Back02b.getChild("Back02c");
        this.Back02d = this.Back02c.getChild("Back02d");
        this.Back02e = this.Back02d.getChild("Back02e");
        this.Neck = this.Back02e.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Jaw = this.Neck.getChild("Jaw");
        this.EquipBaseR = this.BodyMain.getChild("EquipBaseR");
        this.EquipR01 = this.EquipBaseR.getChild("EquipR01");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowEquipBaseL = this.GlowBodyMain.getChild("GlowEquipBaseL");
        this.GlowEquipL01 = this.GlowEquipBaseL.getChild("GlowEquipL01");
        this.GlowEquipCannonPlate = this.GlowEquipL01.getChild("GlowEquipCannonPlate");
        this.EquipCannon01 = this.GlowEquipCannonPlate.getChild("EquipCannon01");
        this.GlowCanonBase = this.GlowEquipL01.getChild("GlowCanonBase");
        this.GlowNeck_1 = this.GlowCanonBase.getChild("GlowNeck_1");
        this.GlowHead_1 = this.GlowNeck_1.getChild("GlowHead_1");
        this.HeadTooth_1 = this.GlowHead_1.getChild("HeadTooth_1");
        this.GlowJaw_1 = this.GlowNeck_1.getChild("GlowJaw_1");
        this.JawTooth_1 = this.GlowJaw_1.getChild("JawTooth_1");
        this.Road01u = this.GlowNeck_1.getChild("Road01u");
        this.Road02u = this.Road01u.getChild("Road02u");
        this.Road03u = this.Road02u.getChild("Road03u");
        this.Road01v = this.GlowNeck_1.getChild("Road01v");
        this.Road02v = this.Road01v.getChild("Road02v");
        this.Road03v = this.Road02v.getChild("Road03v");
        this.GlowBack02 = this.GlowBodyMain.getChild("GlowBack02");
        this.GlowBack02b = this.GlowBack02.getChild("GlowBack02b");
        this.GlowBack02c = this.GlowBack02b.getChild("GlowBack02c");
        this.GlowBack02d = this.GlowBack02c.getChild("GlowBack02d");
        this.GlowBack02e = this.GlowBack02d.getChild("GlowBack02e");
        this.GlowNeck = this.GlowBack02e.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.HeadTooth = this.GlowHead.getChild("HeadTooth");
        this.Road01 = this.GlowHead.getChild("Road01");
        this.Road02 = this.Road01.getChild("Road02");
        this.Road03 = this.Road02.getChild("Road03");
        this.Road04 = this.Road03.getChild("Road04");
        this.Road05 = this.Road04.getChild("Road05");
        this.GlowJaw = this.GlowNeck.getChild("GlowJaw");
        this.JawTooth = this.GlowJaw.getChild("JawTooth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, -2F, 14F, 18F, 10F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Back01 = BodyMain.addOrReplaceChild("Back01", CubeListBuilder.create().texOffs(29, 22).addBox(0F, 0F, 0F, 13F, 10F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, -7F, 19F, 0F, 0.1396F, 0F));

        PartDefinition Back01b = Back01.addOrReplaceChild("Back01b", CubeListBuilder.create().texOffs(29, 22).addBox(0F, 0F, 0F, 13F, 10F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, 0F));

        PartDefinition EquipBaseL = BodyMain.addOrReplaceChild("EquipBaseL", CubeListBuilder.create().texOffs(64, 30).addBox(-6F, -4F, -7F, 11F, 11F, 21F, new CubeDeformation(0F)), PartPose.offsetAndRotation(14.5F, 2F, 5F, 0F, -0.0524F, 0F));

        PartDefinition EquipL01 = EquipBaseL.addOrReplaceChild("EquipL01", CubeListBuilder.create().texOffs(66, 31).addBox(-6F, 0F, -7F, 10F, 4F, 20F, new CubeDeformation(0F)), PartPose.offset(0.5F, -8F, 1F));

        PartDefinition CanonBase = EquipL01.addOrReplaceChild("CanonBase", CubeListBuilder.create().texOffs(0, 21).addBox(0F, 0F, 0F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offset(-3.5F, -9F, 3F));

        PartDefinition Neck_1 = CanonBase.addOrReplaceChild("Neck_1", CubeListBuilder.create().texOffs(0, 37).addBox(-4F, -6F, -0.5F, 8F, 8F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -1F, 3F, -0.2618F, -0.0873F, 0F));

        PartDefinition Jaw_1 = Neck_1.addOrReplaceChild("Jaw_1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, -1F, -15F, 10F, 4F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5F, 3F, 0.8727F, 0F, 0F));

        PartDefinition Head_1 = Neck_1.addOrReplaceChild("Head_1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, -4F, -17F, 10F, 4F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, -2.5F, 3F, -0.3643F, 0F, 0F));

        PartDefinition EquipCannonPlate = EquipL01.addOrReplaceChild("EquipCannonPlate", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(-3F, 1.8F, -7.5F));

        PartDefinition Back02 = BodyMain.addOrReplaceChild("Back02", CubeListBuilder.create().texOffs(29, 22).addBox(-14F, 0F, 0F, 13F, 10F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 19F, 0F, -0.1396F, 0F));

        PartDefinition Back02b = Back02.addOrReplaceChild("Back02b", CubeListBuilder.create().texOffs(29, 22).addBox(0F, 0F, 0F, 13F, 10F, 13F, new CubeDeformation(0F)), PartPose.offset(-14F, -10F, 0F));

        PartDefinition Back02c = Back02b.addOrReplaceChild("Back02c", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -4F, -9F, 8F, 8F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, 2F, 8F, -0.44F, 1.22F, 0F));

        PartDefinition Back02d = Back02c.addOrReplaceChild("Back02d", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -4F, -9F, 8F, 8F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -8F, 0.5236F, -0.6981F, -0.2618F));

        PartDefinition Back02e = Back02d.addOrReplaceChild("Back02e", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -4F, -9F, 8F, 8F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -8F, 0.3491F, -0.3491F, 0F));

        PartDefinition Neck = Back02e.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 37).addBox(-4F, -4F, -7F, 8F, 8F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -10F, -0.1745F, 0.0873F, -0.0873F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, -4F, -17F, 10F, 4F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, -4F, 0.0873F, 0F, 0F));

        PartDefinition Jaw = Neck.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, -1F, -15F, 10F, 4F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.8F, -3F, 0.6283F, 0F, 0F));

        PartDefinition EquipBaseR = BodyMain.addOrReplaceChild("EquipBaseR", CubeListBuilder.create().texOffs(64, 30).addBox(-6F, -4F, -7F, 11F, 11F, 21F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13.5F, 2F, 5F, 0F, 0.0524F, 0F));

        PartDefinition EquipR01 = EquipBaseR.addOrReplaceChild("EquipR01", CubeListBuilder.create().texOffs(66, 31).addBox(-5F, 0F, -7F, 10F, 4F, 20F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -8F, 1F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowEquipBaseL = GlowBodyMain.addOrReplaceChild("GlowEquipBaseL", CubeListBuilder.create().texOffs(64, 30), PartPose.offsetAndRotation(14.5F, 2F, 5F, 0F, -0.0524F, 0F));

        PartDefinition GlowEquipL01 = GlowEquipBaseL.addOrReplaceChild("GlowEquipL01", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0.5F, -8F, 1F));

        PartDefinition GlowEquipCannonPlate = GlowEquipL01.addOrReplaceChild("GlowEquipCannonPlate", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(-3F, 1.8F, -7.5F));

        PartDefinition EquipCannon01 = GlowEquipCannonPlate.addOrReplaceChild("EquipCannon01", CubeListBuilder.create().texOffs(47, 0).addBox(0F, 0F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 1F, 0.5F, -0.3187F, -0.0873F, 0F));

        PartDefinition GlowCanonBase = GlowEquipL01.addOrReplaceChild("GlowCanonBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(-3.5F, -9F, 3F));

        PartDefinition GlowNeck_1 = GlowCanonBase.addOrReplaceChild("GlowNeck_1", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(3.5F, -1F, 3F, -0.2618F, -0.0873F, 0F));

        PartDefinition GlowHead_1 = GlowNeck_1.addOrReplaceChild("GlowHead_1", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0.1F, -2.5F, 3F, -0.3643F, 0F, 0F));

        PartDefinition HeadTooth_1 = GlowHead_1.addOrReplaceChild("HeadTooth_1", CubeListBuilder.create().texOffs(22, 46).addBox(-4.5F, 0F, -6.5F, 9F, 4F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -8F, 0.1745F, 0F, 0F));

        PartDefinition GlowJaw_1 = GlowNeck_1.addOrReplaceChild("GlowJaw_1", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -5F, 3F, 0.8727F, 0F, 0F));

        PartDefinition JawTooth_1 = GlowJaw_1.addOrReplaceChild("JawTooth_1", CubeListBuilder.create().texOffs(22, 46).addBox(-4.5F, 0F, -14F, 9F, 3F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, -0.6F, -0.3F, -0.2094F, 0F, 0F));

        PartDefinition Road01u = GlowNeck_1.addOrReplaceChild("Road01u", CubeListBuilder.create().texOffs(86, 16).addBox(-4.5F, 0F, -12F, 9F, 1F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -4.7F, -3F));

        PartDefinition Road02u = Road01u.addOrReplaceChild("Road02u", CubeListBuilder.create().texOffs(86, 16).addBox(-4.5F, 0F, -12F, 9F, 1F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -12F));

        PartDefinition Road03u = Road02u.addOrReplaceChild("Road03u", CubeListBuilder.create().texOffs(86, 16).addBox(-4.5F, 0F, -12F, 9F, 1F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -12F));

        PartDefinition Road01v = GlowNeck_1.addOrReplaceChild("Road01v", CubeListBuilder.create().texOffs(86, 16).addBox(-4.5F, 0F, -12F, 9F, 1F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 3.2F, -2.4F));

        PartDefinition Road02v = Road01v.addOrReplaceChild("Road02v", CubeListBuilder.create().texOffs(86, 16).addBox(-4.5F, 0F, -12F, 9F, 1F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -12F));

        PartDefinition Road03v = Road02v.addOrReplaceChild("Road03v", CubeListBuilder.create().texOffs(86, 16).addBox(-4.5F, 0F, -12F, 9F, 1F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -12F));

        PartDefinition GlowBack02 = GlowBodyMain.addOrReplaceChild("GlowBack02", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -7F, 19F, 0F, -0.1396F, 0F));

        PartDefinition GlowBack02b = GlowBack02.addOrReplaceChild("GlowBack02b", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(-14F, -10F, 0F));

        PartDefinition GlowBack02c = GlowBack02b.addOrReplaceChild("GlowBack02c", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(3.5F, 2F, 8F, -0.44F, 1.22F, 0F));

        PartDefinition GlowBack02d = GlowBack02c.addOrReplaceChild("GlowBack02d", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 0F, -8F, 0.5236F, -0.6981F, -0.2618F));

        PartDefinition GlowBack02e = GlowBack02d.addOrReplaceChild("GlowBack02e", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 0F, -8F, 0.3491F, -0.3491F, 0F));

        PartDefinition GlowNeck = GlowBack02e.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 0F, -10F, -0.1745F, 0.0873F, -0.0873F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -1F, -4F, 0.0873F, 0F, 0F));

        PartDefinition HeadTooth = GlowHead.addOrReplaceChild("HeadTooth", CubeListBuilder.create().texOffs(22, 46).addBox(-4.5F, 0F, -6.5F, 9F, 4F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -8F, 0.1745F, 0F, 0F));

        PartDefinition Road01 = GlowHead.addOrReplaceChild("Road01", CubeListBuilder.create().texOffs(55, 0).addBox(-5.5F, 0F, 0F, 11F, 1F, 14F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, -23F));

        PartDefinition Road02 = Road01.addOrReplaceChild("Road02", CubeListBuilder.create().texOffs(55, 0).addBox(-5.5F, 0F, 0F, 11F, 1F, 14F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 14F));

        PartDefinition Road03 = Road02.addOrReplaceChild("Road03", CubeListBuilder.create().texOffs(55, 0).addBox(-5.5F, 0F, 0F, 11F, 1F, 14F, new CubeDeformation(0F)), PartPose.offset(0.4F, 0.1F, 12F));

        PartDefinition Road04 = Road03.addOrReplaceChild("Road04", CubeListBuilder.create().texOffs(55, 0).addBox(-5.5F, 0F, 0F, 10F, 1F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, 0.1F, 10F, 0.0384F, 0.8652F, 0.014F));

        PartDefinition Road05 = Road04.addOrReplaceChild("Road05", CubeListBuilder.create().texOffs(55, 0).addBox(-5.5F, 0F, 0F, 11F, 1F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 14F, -0.6981F, 0F, 0F));

        PartDefinition GlowJaw = GlowNeck.addOrReplaceChild("GlowJaw", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -0.8F, -3F, 0.6283F, 0F, 0F));

        PartDefinition JawTooth = GlowJaw.addOrReplaceChild("JawTooth", CubeListBuilder.create().texOffs(22, 46).addBox(-4.5F, 0F, -14F, 9F, 3F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.6F, -0.3F, -0.1745F, 0F, 0F));

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
