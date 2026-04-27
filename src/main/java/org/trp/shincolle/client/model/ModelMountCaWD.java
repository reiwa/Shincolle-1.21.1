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

public class ModelMountCaWD<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_ca_w_d"), "main");

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart WingL01a;
    private final ModelPart WingR01a;
    private final ModelPart Seat01;
    private final ModelPart Back01;
    private final ModelPart Back02;
    private final ModelPart WingL03;
    private final ModelPart WingR03;
    private final ModelPart WingL04;
    private final ModelPart WingR04;
    private final ModelPart Back03;
    private final ModelPart Back04;
    private final ModelPart WingL02;
    private final ModelPart WingR02;
    private final ModelPart CannonL01;
    private final ModelPart CannonR01;
    private final ModelPart Tube01a;
    private final ModelPart Tube02a;
    private final ModelPart CannonM01;
    private final ModelPart Head01;
    private final ModelPart Jaw01;
    private final ModelPart Head02;
    private final ModelPart HeadTooth01;
    private final ModelPart HeadTooth02;
    private final ModelPart Jaw02;
    private final ModelPart JawTooth01;
    private final ModelPart JawTooth02;
    private final ModelPart WingL01b;
    private final ModelPart WingL01c;
    private final ModelPart WingL01Fire;
    private final ModelPart WingR01b;
    private final ModelPart WingR01c;
    private final ModelPart WingR01Fire;
    private final ModelPart Seat02;
    private final ModelPart Seat03;
    private final ModelPart CannonL02;
    private final ModelPart CannonR02;
    private final ModelPart Tube01b;
    private final ModelPart Tube02b;
    private final ModelPart CannonM02;
    private final ModelPart CannonM04;
    private final ModelPart CannonM03;
    private final ModelPart CannonM05;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowBodyMain2;
    private final ModelPart GlowNeck;
    private final ModelPart GlowJaw01;
    private final ModelPart GlowHead01;
    private final ModelPart GlowWingL01a;
    private final ModelPart GlowWingL01a2;
    private final ModelPart GlowWingL01b;
    private final ModelPart GlowWingR01a;
    private final ModelPart GlowWingR01a2;
    private final ModelPart GlowWingR01b;
    private final ModelPart GlowCannonL01;
    private final ModelPart GlowCannonR01;
    private final ModelPart GlowCannonM01;
    private final ModelPart GlowCannonM02;
    private final ModelPart GlowCannonM04;

    public ModelMountCaWD(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head01 = this.Neck.getChild("Head01");
        this.Head02 = this.Head01.getChild("Head02");
        this.Jaw01 = this.Neck.getChild("Jaw01");
        this.Jaw02 = this.Jaw01.getChild("Jaw02");
        this.Tube01a = this.BodyMain.getChild("Tube01a");
        this.Tube01b = this.Tube01a.getChild("Tube01b");
        this.Seat01 = this.BodyMain.getChild("Seat01");
        this.Seat03 = this.Seat01.getChild("Seat03");
        this.Seat02 = this.Seat01.getChild("Seat02");
        this.WingR01a = this.BodyMain.getChild("WingR01a");
        this.Back01 = this.BodyMain.getChild("Back01");
        this.Back03 = this.BodyMain.getChild("Back03");
        this.CannonM01 = this.BodyMain.getChild("CannonM01");
        this.CannonM02 = this.CannonM01.getChild("CannonM02");
        this.CannonM04 = this.CannonM01.getChild("CannonM04");
        this.CannonR01 = this.BodyMain.getChild("CannonR01");
        this.WingL01a = this.BodyMain.getChild("WingL01a");
        this.Back02 = this.BodyMain.getChild("Back02");
        this.Back04 = this.BodyMain.getChild("Back04");
        this.CannonL01 = this.BodyMain.getChild("CannonL01");
        this.Tube02a = this.BodyMain.getChild("Tube02a");
        this.Tube02b = this.Tube02a.getChild("Tube02b");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowJaw01 = this.GlowNeck.getChild("GlowJaw01");
        this.JawTooth01 = this.GlowJaw01.getChild("JawTooth01");
        this.JawTooth02 = this.JawTooth01.getChild("JawTooth02");
        this.GlowHead01 = this.GlowNeck.getChild("GlowHead01");
        this.HeadTooth01 = this.GlowHead01.getChild("HeadTooth01");
        this.HeadTooth02 = this.HeadTooth01.getChild("HeadTooth02");
        this.GlowWingL01a = this.GlowBodyMain.getChild("GlowWingL01a");
        this.GlowWingL01b = this.GlowWingL01a.getChild("GlowWingL01b");
        this.WingL01Fire = this.GlowWingL01b.getChild("WingL01Fire");
        this.GlowWingR01a = this.GlowBodyMain.getChild("GlowWingR01a");
        this.GlowWingR01b = this.GlowWingR01a.getChild("GlowWingR01b");
        this.WingR01Fire = this.GlowWingR01b.getChild("WingR01Fire");
        this.GlowCannonL01 = this.GlowBodyMain.getChild("GlowCannonL01");
        this.CannonL02 = this.GlowCannonL01.getChild("CannonL02");
        this.GlowCannonR01 = this.GlowBodyMain.getChild("GlowCannonR01");
        this.CannonR02 = this.GlowCannonR01.getChild("CannonR02");
        this.GlowCannonM01 = this.GlowBodyMain.getChild("GlowCannonM01");
        this.GlowCannonM02 = this.GlowCannonM01.getChild("GlowCannonM02");
        this.CannonM03 = this.GlowCannonM02.getChild("CannonM03");
        this.GlowCannonM04 = this.GlowCannonM01.getChild("GlowCannonM04");
        this.CannonM05 = this.GlowCannonM04.getChild("CannonM05");
        this.GlowBodyMain2 = root.getChild("GlowBodyMain2");
        this.WingL02 = this.GlowBodyMain2.getChild("WingL02");
        this.WingR02 = this.GlowBodyMain2.getChild("WingR02");
        this.WingL03 = this.GlowBodyMain2.getChild("WingL03");
        this.WingR03 = this.GlowBodyMain2.getChild("WingR03");
        this.WingL04 = this.GlowBodyMain2.getChild("WingL04");
        this.WingR04 = this.GlowBodyMain2.getChild("WingR04");
        this.GlowWingL01a2 = this.GlowBodyMain2.getChild("GlowWingL01a2");
        this.WingL01b = this.GlowWingL01a2.getChild("WingL01b");
        this.WingL01c = this.WingL01b.getChild("WingL01c");
        this.GlowWingR01a2 = this.GlowBodyMain2.getChild("GlowWingR01a2");
        this.WingR01b = this.GlowWingR01a2.getChild("WingR01b");
        this.WingR01c = this.WingR01b.getChild("WingR01c");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0F, 0F, 13F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 8F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(54, 0).addBox(-6F, 0F, 0F, 12F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition Head01 = Neck.addOrReplaceChild("Head01", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, -6F, -15F, 14F, 6F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 5.8F, 5F));

        PartDefinition Head02 = Head01.addOrReplaceChild("Head02", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.9F, -15F, 0F, 0.7854F, 0F));

        PartDefinition Jaw01 = Neck.addOrReplaceChild("Jaw01", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, 0F, -15F, 14F, 6F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 6F, 0.3142F, 0F, 0F));

        PartDefinition Jaw02 = Jaw01.addOrReplaceChild("Jaw02", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10F, 6F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.1F, -15F, 0F, 0.7854F, 0F));

        PartDefinition Tube01a = BodyMain.addOrReplaceChild("Tube01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 1F, 9F, -0.7854F, 0.8727F, 0.2618F));

        PartDefinition Tube01b = Tube01a.addOrReplaceChild("Tube01b", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 1F, 1.3963F, 0F, 0F));

        PartDefinition Seat01 = BodyMain.addOrReplaceChild("Seat01", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, 0F, 0F, 15F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.5F, 0.3F, -0.1047F, 0F, 0F));

        PartDefinition Seat03 = Seat01.addOrReplaceChild("Seat03", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -9F, 2F, 10F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.2F, 1F, 0.5F, 0.1047F, 0.1047F, -0.1047F));

        PartDefinition Seat02 = Seat01.addOrReplaceChild("Seat02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -9F, 2F, 10F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.2F, 1F, 0.5F, 0.1047F, -0.1047F, 0.1047F));

        PartDefinition WingR01a = BodyMain.addOrReplaceChild("WingR01a", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, 0F, 0F, 7F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 13.5F, -4F, 0F, 0.3491F, -0.5236F));

        PartDefinition Back01 = BodyMain.addOrReplaceChild("Back01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, 0F, 12F, 9F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 1F));

        PartDefinition Back03 = BodyMain.addOrReplaceChild("Back03", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, 0F, 0F, 18F, 14F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, 0F));

        PartDefinition CannonM01 = BodyMain.addOrReplaceChild("CannonM01", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -3F, -4F, 6F, 3F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8.5F, 7F, -0.8727F, 0F, 0F));

        PartDefinition CannonM02 = CannonM01.addOrReplaceChild("CannonM02", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1F, -2F, 1F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(1.3F, -1.7F, -3.5F));

        PartDefinition CannonM04 = CannonM01.addOrReplaceChild("CannonM04", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1F, -2F, 1F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(-1.3F, -1.7F, -3.5F));

        PartDefinition CannonR01 = BodyMain.addOrReplaceChild("CannonR01", CubeListBuilder.create().texOffs(9, 0).addBox(-3.5F, -5F, -8F, 7F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, -6F, 9F, -0.5236F, 0.5236F, 0F));

        PartDefinition WingL01a = BodyMain.addOrReplaceChild("WingL01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 7F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 13.5F, -4F, 0F, -0.3491F, 0.5236F));

        PartDefinition Back02 = BodyMain.addOrReplaceChild("Back02", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 10F, 14F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, 6F));

        PartDefinition Back04 = BodyMain.addOrReplaceChild("Back04", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, 0F, 16F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 6F));

        PartDefinition CannonL01 = BodyMain.addOrReplaceChild("CannonL01", CubeListBuilder.create().texOffs(9, 0).addBox(-3.5F, -5F, -8F, 7F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, -6F, 9F, -0.5236F, -0.5236F, 0F));

        PartDefinition Tube02a = BodyMain.addOrReplaceChild("Tube02a", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, 2F, 9F, -0.7854F, -0.1396F, -0.2618F));

        PartDefinition Tube02b = Tube02a.addOrReplaceChild("Tube02b", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -7F, -1F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 1.3963F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 8F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, -5F));

        PartDefinition GlowJaw01 = GlowNeck.addOrReplaceChild("GlowJaw01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 7F, 6F, 0.3142F, 0F, 0F));

        PartDefinition JawTooth01 = GlowJaw01.addOrReplaceChild("JawTooth01", CubeListBuilder.create().texOffs(78, 48).addBox(-6.5F, 0F, -14F, 13F, 4F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.8F, -0.8F, -0.1396F, 0F, 0F));

        PartDefinition JawTooth02 = JawTooth01.addOrReplaceChild("JawTooth02", CubeListBuilder.create().texOffs(54, 46).addBox(-4.5F, 0F, -4.5F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -13.9F, -0.075F, 0.7854F, -0.0524F));

        PartDefinition GlowHead01 = GlowNeck.addOrReplaceChild("GlowHead01", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 5.8F, 5F));

        PartDefinition HeadTooth01 = GlowHead01.addOrReplaceChild("HeadTooth01", CubeListBuilder.create().texOffs(78, 48).addBox(-6.5F, 0F, -6.5F, 13F, 4F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, -7.5F));

        PartDefinition HeadTooth02 = HeadTooth01.addOrReplaceChild("HeadTooth02", CubeListBuilder.create().texOffs(54, 46).addBox(-4.5F, 0F, -4.5F, 9F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -6.4F, -0.075F, 0.7854F, -0.0524F));

        PartDefinition GlowWingL01a = GlowBodyMain.addOrReplaceChild("GlowWingL01a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(6F, 13.5F, -4F, 0F, -0.3491F, 0.5236F));

        PartDefinition GlowWingL01b = GlowWingL01a.addOrReplaceChild("GlowWingL01b", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(6.5F, -1.5F, -4F, -0.0873F, -0.0873F, 0F));

        PartDefinition WingL01Fire = GlowWingL01b.addOrReplaceChild("WingL01Fire", CubeListBuilder.create().texOffs(116, 48).addBox(-1F, -2F, 0F, 2F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(1.5F, 2.5F, 8.1F));

        PartDefinition GlowWingR01a = GlowBodyMain.addOrReplaceChild("GlowWingR01a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-6F, 13.5F, -4F, 0F, 0.3491F, -0.5236F));

        PartDefinition GlowWingR01b = GlowWingR01a.addOrReplaceChild("GlowWingR01b", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-6.5F, -1.5F, -4F, -0.0873F, 0.0873F, 0F));

        PartDefinition WingR01Fire = GlowWingR01b.addOrReplaceChild("WingR01Fire", CubeListBuilder.create().texOffs(116, 48).addBox(-1F, -2F, 0F, 2F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(-1.5F, 2.5F, 8.1F));

        PartDefinition GlowCannonL01 = GlowBodyMain.addOrReplaceChild("GlowCannonL01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(8F, -6F, 9F, -0.5236F, -0.5236F, 0F));

        PartDefinition CannonL02 = GlowCannonL01.addOrReplaceChild("CannonL02", CubeListBuilder.create().texOffs(0, 9).addBox(-1F, -1F, -12F, 2F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.2F, -7.5F, -0.2618F, 0F, 0F));

        PartDefinition GlowCannonR01 = GlowBodyMain.addOrReplaceChild("GlowCannonR01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-8F, -6F, 9F, -0.5236F, 0.5236F, 0F));

        PartDefinition CannonR02 = GlowCannonR01.addOrReplaceChild("CannonR02", CubeListBuilder.create().texOffs(0, 9).addBox(-1F, -1F, -12F, 2F, 2F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, -3.2F, -7.5F));

        PartDefinition GlowCannonM01 = GlowBodyMain.addOrReplaceChild("GlowCannonM01", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -8.5F, 7F, -0.8727F, 0F, 0F));

        PartDefinition GlowCannonM02 = GlowCannonM01.addOrReplaceChild("GlowCannonM02", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(1.3F, -1.7F, -3.5F));

        PartDefinition CannonM03 = GlowCannonM02.addOrReplaceChild("CannonM03", CubeListBuilder.create().texOffs(28, 15).addBox(0F, 0F, -6F, 1F, 1F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -0.7F, -2F));

        PartDefinition GlowCannonM04 = GlowCannonM01.addOrReplaceChild("GlowCannonM04", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(-1.3F, -1.7F, -3.5F));

        PartDefinition CannonM05 = GlowCannonM04.addOrReplaceChild("CannonM05", CubeListBuilder.create().texOffs(28, 15).addBox(0F, 0F, -6F, 1F, 1F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -0.7F, -2F));

        PartDefinition GlowBodyMain2 = partdefinition.addOrReplaceChild("GlowBodyMain2", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 8F));

        PartDefinition WingL02 = GlowBodyMain2.addOrReplaceChild("WingL02", CubeListBuilder.create().texOffs(0, 35).addBox(0F, -3F, -14F, 4F, 6F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -5F, 6F, 0F, -0.1745F, 0F));

        PartDefinition WingR02 = GlowBodyMain2.addOrReplaceChild("WingR02", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(-4F, -3F, -14F, 4F, 6F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -5F, 6F, 0F, 0.1745F, 0F));

        PartDefinition WingL03 = GlowBodyMain2.addOrReplaceChild("WingL03", CubeListBuilder.create().texOffs(30, 40).addBox(0F, 0F, -20F, 2F, 4F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.5F, -0.5F, 11F, 0.2094F, -0.2618F, 0F));

        PartDefinition WingR03 = GlowBodyMain2.addOrReplaceChild("WingR03", CubeListBuilder.create().texOffs(30, 40).mirror().addBox(-2F, 0F, -20F, 2F, 4F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.5F, -0.5F, 11F, 0.2094F, 0.2618F, 0F));

        PartDefinition WingL04 = GlowBodyMain2.addOrReplaceChild("WingL04", CubeListBuilder.create().texOffs(0, 47).addBox(0F, 0F, -10F, 2F, 5F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 6F, 9F, 0.2094F, -0.3491F, 0.1745F));

        PartDefinition WingR04 = GlowBodyMain2.addOrReplaceChild("WingR04", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(-2F, 0F, -10F, 2F, 5F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 6F, 9F, 0.2094F, 0.3491F, -0.1745F));

        PartDefinition GlowWingL01a2 = GlowBodyMain2.addOrReplaceChild("GlowWingL01a2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(6F, 13.5F, -4F, 0F, -0.3491F, 0.5236F));

        PartDefinition WingL01b = GlowWingL01a2.addOrReplaceChild("WingL01b", CubeListBuilder.create().texOffs(25, 39).addBox(0F, 0F, 0F, 3F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, -1.5F, -4F, -0.0873F, -0.0873F, 0F));

        PartDefinition WingL01c = WingL01b.addOrReplaceChild("WingL01c", CubeListBuilder.create().texOffs(0, 53).addBox(-3F, 0F, -6F, 3F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 0F, 0F, 0F, 0.5236F, 0F));

        PartDefinition GlowWingR01a2 = GlowBodyMain2.addOrReplaceChild("GlowWingR01a2", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-6F, 13.5F, -4F, 0F, 0.3491F, -0.5236F));

        PartDefinition WingR01b = GlowWingR01a2.addOrReplaceChild("WingR01b", CubeListBuilder.create().texOffs(25, 39).mirror().addBox(-3F, 0F, 0F, 3F, 5F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, -1.5F, -4F, -0.0873F, 0.0873F, 0F));

        PartDefinition WingR01c = WingR01b.addOrReplaceChild("WingR01c", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(0F, 0F, -6F, 3F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 0F, 0F, 0F, -0.5236F, 0F));

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
