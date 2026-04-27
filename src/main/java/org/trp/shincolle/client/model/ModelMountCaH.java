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

public class ModelMountCaH<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_ca_h"), "main");

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Seat01;
    private final ModelPart Back01;
    private final ModelPart Back02;
    private final ModelPart Back03;
    private final ModelPart Back04;
    private final ModelPart WingL02;
    private final ModelPart WingR02;
    private final ModelPart CannonL01;
    private final ModelPart CannonR01;
    private final ModelPart Tube01a;
    private final ModelPart Tube02a;
    private final ModelPart Cannon01a;
    private final ModelPart Cannon01a_1;
    private final ModelPart Cannon01a_2;
    private final ModelPart Cannon01a_3;
    private final ModelPart Cannon01a_4;
    private final ModelPart Cannon01a_5;
    private final ModelPart Cannon01a_6;
    private final ModelPart Cannon01a_7;
    private final ModelPart Tube01a_1;
    private final ModelPart Tube01a_2;
    private final ModelPart Head01;
    private final ModelPart Jaw01;
    private final ModelPart Head02;
    private final ModelPart HeadTooth01;
    private final ModelPart HeadTooth02;
    private final ModelPart Jaw02;
    private final ModelPart JawTooth01;
    private final ModelPart JawTooth02;
    private final ModelPart CannonL02;
    private final ModelPart CannonR02;
    private final ModelPart Tube01b;
    private final ModelPart Tube02b;
    private final ModelPart Cannon01b;
    private final ModelPart Cannon01c;
    private final ModelPart Cannon01b_1;
    private final ModelPart Cannon01c_1;
    private final ModelPart Cannon01b_2;
    private final ModelPart Cannon01c_2;
    private final ModelPart Cannon01b_3;
    private final ModelPart Cannon01c_3;
    private final ModelPart Cannon01b_4;
    private final ModelPart Cannon01c_4;
    private final ModelPart Cannon01b_5;
    private final ModelPart Cannon01c_5;
    private final ModelPart Cannon01b_6;
    private final ModelPart Cannon01c_6;
    private final ModelPart Cannon01b_7;
    private final ModelPart Cannon01c_7;
    private final ModelPart Tube01b_1;
    private final ModelPart Tube01b_2;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowBodyMain2;
    private final ModelPart GlowNeck;
    private final ModelPart GlowJaw01;
    private final ModelPart GlowHead01;
    private final ModelPart GlowCannonL01;
    private final ModelPart GlowCannonR01;

    public ModelMountCaH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Back02 = this.BodyMain.getChild("Back02");
        this.Cannon01a_7 = this.BodyMain.getChild("Cannon01a_7");
        this.Cannon01b_7 = this.Cannon01a_7.getChild("Cannon01b_7");
        this.Cannon01c_7 = this.Cannon01b_7.getChild("Cannon01c_7");
        this.Seat01 = this.BodyMain.getChild("Seat01");
        this.Cannon01a = this.BodyMain.getChild("Cannon01a");
        this.Cannon01b = this.Cannon01a.getChild("Cannon01b");
        this.Cannon01c = this.Cannon01b.getChild("Cannon01c");
        this.Tube02a = this.BodyMain.getChild("Tube02a");
        this.Tube02b = this.Tube02a.getChild("Tube02b");
        this.Cannon01a_6 = this.BodyMain.getChild("Cannon01a_6");
        this.Cannon01b_6 = this.Cannon01a_6.getChild("Cannon01b_6");
        this.Cannon01c_6 = this.Cannon01b_6.getChild("Cannon01c_6");
        this.CannonR01 = this.BodyMain.getChild("CannonR01");
        this.Cannon01a_1 = this.BodyMain.getChild("Cannon01a_1");
        this.Cannon01b_1 = this.Cannon01a_1.getChild("Cannon01b_1");
        this.Cannon01c_1 = this.Cannon01b_1.getChild("Cannon01c_1");
        this.Cannon01a_5 = this.BodyMain.getChild("Cannon01a_5");
        this.Cannon01b_5 = this.Cannon01a_5.getChild("Cannon01b_5");
        this.Cannon01c_5 = this.Cannon01b_5.getChild("Cannon01c_5");
        this.CannonL01 = this.BodyMain.getChild("CannonL01");
        this.Cannon01a_4 = this.BodyMain.getChild("Cannon01a_4");
        this.Cannon01b_4 = this.Cannon01a_4.getChild("Cannon01b_4");
        this.Cannon01c_4 = this.Cannon01b_4.getChild("Cannon01c_4");
        this.Tube01a_1 = this.BodyMain.getChild("Tube01a_1");
        this.Tube01b_1 = this.Tube01a_1.getChild("Tube01b_1");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head01 = this.Neck.getChild("Head01");
        this.Head02 = this.Head01.getChild("Head02");
        this.Jaw01 = this.Neck.getChild("Jaw01");
        this.Jaw02 = this.Jaw01.getChild("Jaw02");
        this.Cannon01a_3 = this.BodyMain.getChild("Cannon01a_3");
        this.Cannon01b_3 = this.Cannon01a_3.getChild("Cannon01b_3");
        this.Cannon01c_3 = this.Cannon01b_3.getChild("Cannon01c_3");
        this.Back01 = this.BodyMain.getChild("Back01");
        this.Back04 = this.BodyMain.getChild("Back04");
        this.Cannon01a_2 = this.BodyMain.getChild("Cannon01a_2");
        this.Cannon01b_2 = this.Cannon01a_2.getChild("Cannon01b_2");
        this.Cannon01c_2 = this.Cannon01b_2.getChild("Cannon01c_2");
        this.Tube01a_2 = this.BodyMain.getChild("Tube01a_2");
        this.Tube01b_2 = this.Tube01a_2.getChild("Tube01b_2");
        this.Back03 = this.BodyMain.getChild("Back03");
        this.Tube01a = this.BodyMain.getChild("Tube01a");
        this.Tube01b = this.Tube01a.getChild("Tube01b");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowJaw01 = this.GlowNeck.getChild("GlowJaw01");
        this.JawTooth01 = this.GlowJaw01.getChild("JawTooth01");
        this.JawTooth02 = this.JawTooth01.getChild("JawTooth02");
        this.GlowHead01 = this.GlowNeck.getChild("GlowHead01");
        this.HeadTooth01 = this.GlowHead01.getChild("HeadTooth01");
        this.HeadTooth02 = this.HeadTooth01.getChild("HeadTooth02");
        this.GlowCannonL01 = this.GlowBodyMain.getChild("GlowCannonL01");
        this.CannonL02 = this.GlowCannonL01.getChild("CannonL02");
        this.GlowCannonR01 = this.GlowBodyMain.getChild("GlowCannonR01");
        this.CannonR02 = this.GlowCannonR01.getChild("CannonR02");
        this.GlowBodyMain2 = root.getChild("GlowBodyMain2");
        this.WingL02 = this.GlowBodyMain2.getChild("WingL02");
        this.WingR02 = this.GlowBodyMain2.getChild("WingR02");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0F, 0F, 13F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 8F));

        PartDefinition Back02 = BodyMain.addOrReplaceChild("Back02", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 14F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, 6F));

        PartDefinition Cannon01a_7 = BodyMain.addOrReplaceChild("Cannon01a_7", CubeListBuilder.create().texOffs(20, 8).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12.5F, 7.5F, 5F, -0.1745F, 0.5236F, 0F));

        PartDefinition Cannon01b_7 = Cannon01a_7.addOrReplaceChild("Cannon01b_7", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c_7 = Cannon01b_7.addOrReplaceChild("Cannon01c_7", CubeListBuilder.create().texOffs(16, 12).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition Seat01 = BodyMain.addOrReplaceChild("Seat01", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, 0F, 0F, 15F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.5F, 0.3F, -0.1047F, 0F, 0F));

        PartDefinition Cannon01a = BodyMain.addOrReplaceChild("Cannon01a", CubeListBuilder.create().texOffs(19, 0).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 12F, 4F, 0.2094F, -0.2618F, 0F));

        PartDefinition Cannon01b = Cannon01a.addOrReplaceChild("Cannon01b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c = Cannon01b.addOrReplaceChild("Cannon01c", CubeListBuilder.create().texOffs(4, 8).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition Tube02a = BodyMain.addOrReplaceChild("Tube02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 2F, 9F, -0.7854F, -0.1396F, -0.2618F));

        PartDefinition Tube02b = Tube02a.addOrReplaceChild("Tube02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 1.3963F, 0F, 0F));

        PartDefinition Cannon01a_6 = BodyMain.addOrReplaceChild("Cannon01a_6", CubeListBuilder.create().texOffs(20, 8).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-9F, 8F, 4F, -0.1396F, 0.2618F, 0F));

        PartDefinition Cannon01b_6 = Cannon01a_6.addOrReplaceChild("Cannon01b_6", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c_6 = Cannon01b_6.addOrReplaceChild("Cannon01c_6", CubeListBuilder.create().texOffs(28, 12).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition CannonR01 = BodyMain.addOrReplaceChild("CannonR01", CubeListBuilder.create().texOffs(9, 0).addBox(-3.5F, -5F, -8F, 7F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, -6F, 9F, -0.6981F, 0.1047F, 0F));

        PartDefinition Cannon01a_1 = BodyMain.addOrReplaceChild("Cannon01a_1", CubeListBuilder.create().texOffs(20, 8).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 11F, 5F, 0.1396F, -0.4189F, 0F));

        PartDefinition Cannon01b_1 = Cannon01a_1.addOrReplaceChild("Cannon01b_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c_1 = Cannon01b_1.addOrReplaceChild("Cannon01c_1", CubeListBuilder.create().texOffs(20, 12).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition Cannon01a_5 = BodyMain.addOrReplaceChild("Cannon01a_5", CubeListBuilder.create().texOffs(20, 8).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 11F, 5F, 0.2094F, 0.3142F, 0F));

        PartDefinition Cannon01b_5 = Cannon01a_5.addOrReplaceChild("Cannon01b_5", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c_5 = Cannon01b_5.addOrReplaceChild("Cannon01c_5", CubeListBuilder.create().texOffs(12, 12).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition CannonL01 = BodyMain.addOrReplaceChild("CannonL01", CubeListBuilder.create().texOffs(9, 0).addBox(-3.5F, -5F, -8F, 7F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, -6F, 9F, -0.6981F, -0.1047F, 0F));

        PartDefinition Cannon01a_4 = BodyMain.addOrReplaceChild("Cannon01a_4", CubeListBuilder.create().texOffs(20, 8).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 12F, 4F, 0.2094F, 0.2094F, 0F));

        PartDefinition Cannon01b_4 = Cannon01a_4.addOrReplaceChild("Cannon01b_4", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c_4 = Cannon01b_4.addOrReplaceChild("Cannon01c_4", CubeListBuilder.create().texOffs(32, 12).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition Tube01a_1 = BodyMain.addOrReplaceChild("Tube01a_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11F, 8F, 7F, -0.6981F, 0F, -0.3491F));

        PartDefinition Tube01b_1 = Tube01a_1.addOrReplaceChild("Tube01b_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 1F, 1.3963F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(54, 0).addBox(-6F, 0F, 0F, 12F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition Head01 = Neck.addOrReplaceChild("Head01", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, -6F, -15F, 14F, 6F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 5.8F, 5F));

        PartDefinition Head02 = Head01.addOrReplaceChild("Head02", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.9F, -15F, 0F, 0.7854F, 0F));

        PartDefinition Jaw01 = Neck.addOrReplaceChild("Jaw01", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, 0F, -15F, 14F, 6F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 6F, 0.3142F, 0F, 0F));

        PartDefinition Jaw02 = Jaw01.addOrReplaceChild("Jaw02", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.1F, -15F, 0F, 0.7854F, 0F));

        PartDefinition Cannon01a_3 = BodyMain.addOrReplaceChild("Cannon01a_3", CubeListBuilder.create().texOffs(20, 8).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12.5F, 7.5F, 5F, -0.0524F, -0.5236F, 0F));

        PartDefinition Cannon01b_3 = Cannon01a_3.addOrReplaceChild("Cannon01b_3", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c_3 = Cannon01b_3.addOrReplaceChild("Cannon01c_3", CubeListBuilder.create().texOffs(24, 12).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition Back01 = BodyMain.addOrReplaceChild("Back01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, 0F, 12F, 9F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 1F));

        PartDefinition Back04 = BodyMain.addOrReplaceChild("Back04", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, 0F, 16F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 6F));

        PartDefinition Cannon01a_2 = BodyMain.addOrReplaceChild("Cannon01a_2", CubeListBuilder.create().texOffs(20, 8).addBox(-2F, -2F, -3F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 8F, 4F, -0.1396F, -0.3142F, 0F));

        PartDefinition Cannon01b_2 = Cannon01a_2.addOrReplaceChild("Cannon01b_2", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, -4F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -1.8F));

        PartDefinition Cannon01c_2 = Cannon01b_2.addOrReplaceChild("Cannon01c_2", CubeListBuilder.create().texOffs(8, 12).addBox(-0.5F, -0.5F, -7F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -4F));

        PartDefinition Tube01a_2 = BodyMain.addOrReplaceChild("Tube01a_2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 8F, 6.7F, -0.6981F, 0F, 0.3491F));

        PartDefinition Tube01b_2 = Tube01a_2.addOrReplaceChild("Tube01b_2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 1F, 1.3963F, 0F, 0F));

        PartDefinition Back03 = BodyMain.addOrReplaceChild("Back03", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, 0F, 0F, 18F, 14F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, 0F));

        PartDefinition Tube01a = BodyMain.addOrReplaceChild("Tube01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 1F, 9F, -0.7854F, 0.8727F, 0.2618F));

        PartDefinition Tube01b = Tube01a.addOrReplaceChild("Tube01b", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 1F, 1.3963F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 8F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, -5F));

        PartDefinition GlowJaw01 = GlowNeck.addOrReplaceChild("GlowJaw01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 7F, 6F, 0.3142F, 0F, 0F));

        PartDefinition JawTooth01 = GlowJaw01.addOrReplaceChild("JawTooth01", CubeListBuilder.create().texOffs(24, 24).addBox(-6.5F, 0F, -14F, 13F, 4F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.8F, -0.8F, -0.1396F, 0F, 0F));

        PartDefinition JawTooth02 = JawTooth01.addOrReplaceChild("JawTooth02", CubeListBuilder.create().texOffs(0, 23).addBox(-4.5F, 0F, -4.5F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -13.9F, -0.075F, 0.7854F, -0.0524F));

        PartDefinition GlowHead01 = GlowNeck.addOrReplaceChild("GlowHead01", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 5.8F, 5F));

        PartDefinition HeadTooth01 = GlowHead01.addOrReplaceChild("HeadTooth01", CubeListBuilder.create().texOffs(24, 24).addBox(-6.5F, 0F, -6.5F, 13F, 4F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, -7.5F));

        PartDefinition HeadTooth02 = HeadTooth01.addOrReplaceChild("HeadTooth02", CubeListBuilder.create().texOffs(0, 23).addBox(-4.5F, 0F, -4.5F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -6.4F, -0.075F, 0.7854F, -0.0524F));

        PartDefinition GlowCannonL01 = GlowBodyMain.addOrReplaceChild("GlowCannonL01", CubeListBuilder.create().texOffs(9, 0), PartPose.offsetAndRotation(4F, -6F, 9F, -0.6981F, -0.1047F, 0F));

        PartDefinition CannonL02 = GlowCannonL01.addOrReplaceChild("CannonL02", CubeListBuilder.create().texOffs(0, 9).addBox(-1F, -1F, -12F, 2F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.2F, -7.5F, -0.2618F, 0F, 0F));

        PartDefinition GlowCannonR01 = GlowBodyMain.addOrReplaceChild("GlowCannonR01", CubeListBuilder.create().texOffs(9, 0), PartPose.offsetAndRotation(-4F, -6F, 9F, -0.6981F, 0.1047F, 0F));

        PartDefinition CannonR02 = GlowCannonR01.addOrReplaceChild("CannonR02", CubeListBuilder.create().texOffs(0, 9).addBox(-1F, -1F, -12F, 2F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.2F, -7.5F, -0.1396F, 0F, 0F));

        PartDefinition GlowBodyMain2 = partdefinition.addOrReplaceChild("GlowBodyMain2", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 8F));

        PartDefinition WingL02 = GlowBodyMain2.addOrReplaceChild("WingL02", CubeListBuilder.create().texOffs(0, 41).addBox(0F, -3F, -14F, 4F, 6F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -2F, 6F, 0F, -0.1047F, 0F));

        PartDefinition WingR02 = GlowBodyMain2.addOrReplaceChild("WingR02", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-4F, -3F, -14F, 4F, 6F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -2F, 6F, 0F, 0.1047F, 0F));

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

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        GlowBodyMain2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
