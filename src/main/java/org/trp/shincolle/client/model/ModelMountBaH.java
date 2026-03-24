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

public class ModelMountBaH<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_ba_h"), "main");

    private final ModelPart BodyMain;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Butt;
    private final ModelPart Neck;
    private final ModelPart ChestCannon01a;
    private final ModelPart ChestCannon02a;
    private final ModelPart ChestCannon03a;
    private final ModelPart ChestCannon04a;
    private final ModelPart ChestCannon05a;
    private final ModelPart ChestCannon06;
    private final ModelPart EquipBaseL;
    private final ModelPart EquipBaseR;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart LefLeft02;
    private final ModelPart Head;
    private final ModelPart HeadTooth;
    private final ModelPart Jaw;
    private final ModelPart HeadBack01;
    private final ModelPart HeadBack02;
    private final ModelPart HeadBack03;
    private final ModelPart JawTooth;
    private final ModelPart Tongue;
    private final ModelPart ChestCannon01b;
    private final ModelPart ChestCannon02b;
    private final ModelPart ChestCannon03b;
    private final ModelPart ChestCannon04b;
    private final ModelPart ChestCannon05b;
    private final ModelPart EquipL01;
    private final ModelPart EquipL03;
    private final ModelPart EquipL02;
    private final ModelPart EquipCannon01;
    private final ModelPart EquipCannon02;
    private final ModelPart EquipCannon03;
    private final ModelPart ChestCannonL01a;
    private final ModelPart ChestCannonL01b;
    private final ModelPart EquipR01;
    private final ModelPart EquipR03;
    private final ModelPart EquipR02;
    private final ModelPart EquipCannon01_1;
    private final ModelPart EquipCannon02_1;
    private final ModelPart EquipCannon03_1;
    private final ModelPart ChestCannonR01a;
    private final ModelPart ChestCannonR01b;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowEquipBaseL;
    private final ModelPart GlowEquipBaseR;
    private final ModelPart GlowEquipL01;
    private final ModelPart GlowEquipL02;
    private final ModelPart GlowEquipR01;
    private final ModelPart GlowEquipR02;
    private final ModelPart GlowChestCannonL01a;
    private final ModelPart GlowChestCannonR01a;
    private final ModelPart GlowChestCannon01a;
    private final ModelPart GlowChestCannon02a;
    private final ModelPart GlowChestCannon03a;
    private final ModelPart GlowChestCannon04a;
    private final ModelPart GlowChestCannon05a;
    private final ModelPart GlowEquipL03;
    private final ModelPart GlowEquipR03;

    public ModelMountBaH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ChestCannon06 = this.BodyMain.getChild("ChestCannon06");
        this.ChestCannon01a = this.BodyMain.getChild("ChestCannon01a");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LefLeft02 = this.LegLeft01.getChild("LefLeft02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ChestCannon04a = this.BodyMain.getChild("ChestCannon04a");
        this.EquipBaseL = this.BodyMain.getChild("EquipBaseL");
        this.EquipL01 = this.EquipBaseL.getChild("EquipL01");
        this.EquipL02 = this.EquipL01.getChild("EquipL02");
        this.EquipL03 = this.EquipBaseL.getChild("EquipL03");
        this.ChestCannonL01a = this.EquipL03.getChild("ChestCannonL01a");
        this.EquipBaseR = this.BodyMain.getChild("EquipBaseR");
        this.EquipR01 = this.EquipBaseR.getChild("EquipR01");
        this.EquipR02 = this.EquipR01.getChild("EquipR02");
        this.EquipR03 = this.EquipBaseR.getChild("EquipR03");
        this.ChestCannonR01a = this.EquipR03.getChild("ChestCannonR01a");
        this.ChestCannon03a = this.BodyMain.getChild("ChestCannon03a");
        this.ChestCannon02a = this.BodyMain.getChild("ChestCannon02a");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ChestCannon05a = this.BodyMain.getChild("ChestCannon05a");
        this.Neck = this.BodyMain.getChild("Neck");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.Head = this.GlowNeck.getChild("Head");
        this.HeadTooth = this.Head.getChild("HeadTooth");
        this.HeadBack01 = this.Head.getChild("HeadBack01");
        this.HeadBack02 = this.Head.getChild("HeadBack02");
        this.HeadBack03 = this.Head.getChild("HeadBack03");
        this.Jaw = this.Head.getChild("Jaw");
        this.Tongue = this.Jaw.getChild("Tongue");
        this.JawTooth = this.Jaw.getChild("JawTooth");
        this.GlowEquipBaseL = this.GlowBodyMain.getChild("GlowEquipBaseL");
        this.GlowEquipL01 = this.GlowEquipBaseL.getChild("GlowEquipL01");
        this.GlowEquipL02 = this.GlowEquipL01.getChild("GlowEquipL02");
        this.EquipCannon01 = this.GlowEquipL02.getChild("EquipCannon01");
        this.EquipCannon02 = this.GlowEquipL02.getChild("EquipCannon02");
        this.EquipCannon03 = this.GlowEquipL02.getChild("EquipCannon03");
        this.GlowEquipL03 = this.GlowEquipBaseL.getChild("GlowEquipL03");
        this.GlowChestCannonL01a = this.GlowEquipL03.getChild("GlowChestCannonL01a");
        this.ChestCannonL01b = this.GlowChestCannonL01a.getChild("ChestCannonL01b");
        this.GlowEquipBaseR = this.GlowBodyMain.getChild("GlowEquipBaseR");
        this.GlowEquipR01 = this.GlowEquipBaseR.getChild("GlowEquipR01");
        this.GlowEquipR02 = this.GlowEquipR01.getChild("GlowEquipR02");
        this.EquipCannon01_1 = this.GlowEquipR02.getChild("EquipCannon01_1");
        this.EquipCannon02_1 = this.GlowEquipR02.getChild("EquipCannon02_1");
        this.EquipCannon03_1 = this.GlowEquipR02.getChild("EquipCannon03_1");
        this.GlowEquipR03 = this.GlowEquipBaseR.getChild("GlowEquipR03");
        this.GlowChestCannonR01a = this.GlowEquipR03.getChild("GlowChestCannonR01a");
        this.ChestCannonR01b = this.GlowChestCannonR01a.getChild("ChestCannonR01b");
        this.GlowChestCannon01a = this.GlowBodyMain.getChild("GlowChestCannon01a");
        this.ChestCannon01b = this.GlowChestCannon01a.getChild("ChestCannon01b");
        this.GlowChestCannon02a = this.GlowBodyMain.getChild("GlowChestCannon02a");
        this.ChestCannon02b = this.GlowChestCannon02a.getChild("ChestCannon02b");
        this.GlowChestCannon03a = this.GlowBodyMain.getChild("GlowChestCannon03a");
        this.ChestCannon03b = this.GlowChestCannon03a.getChild("ChestCannon03b");
        this.GlowChestCannon04a = this.GlowBodyMain.getChild("GlowChestCannon04a");
        this.ChestCannon04b = this.GlowChestCannon04a.getChild("ChestCannon04b");
        this.GlowChestCannon05a = this.GlowBodyMain.getChild("GlowChestCannon05a");
        this.ChestCannon05b = this.GlowChestCannon05a.getChild("ChestCannon05b");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 51).addBox(-15F, -11F, -5F, 30F, 20F, 18F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10F, 0F, 1.0472F, 0F, 0F));

        PartDefinition ChestCannon06 = BodyMain.addOrReplaceChild("ChestCannon06", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 9F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, -1F, -7F, 0.0911F, 0F, 0F));

        PartDefinition ChestCannon01a = BodyMain.addOrReplaceChild("ChestCannon01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 7F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -8F, -7F, -0.1367F, -0.0911F, 0F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 96).addBox(-11F, 0F, -2.5F, 22F, 18F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 0F, -0.5009F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 101).addBox(-4.5F, 0F, -4.5F, 9F, 18F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, 16F, 7F, -1.6755F, 0.2094F, 0F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 102).addBox(-4F, 0F, -2F, 8F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 18F, -2F, 1.7453F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 101).mirror().addBox(-4.5F, 0F, -4.5F, 9F, 18F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, 16F, 7F, -1.6755F, -0.2094F, 0F));

        PartDefinition LefLeft02 = LegLeft01.addOrReplaceChild("LefLeft02", CubeListBuilder.create().texOffs(0, 102).mirror().addBox(-4F, 0F, -2F, 8F, 18F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 18F, -2F, 1.7453F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 92).mirror().addBox(-1F, -7F, -7F, 14F, 22F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(15F, 1F, 2F, -0.8727F, -0.2094F, 0F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 89).mirror().addBox(-6.5F, 0F, -13F, 13F, 26F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 15F, 7F, -0.6981F, 0F, 0F));

        PartDefinition ChestCannon04a = BodyMain.addOrReplaceChild("ChestCannon04a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 9F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -1F, -7F, 0.182F, 0.0911F, 0F));

        PartDefinition EquipBaseL = BodyMain.addOrReplaceChild("EquipBaseL", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, -6F, -8.5F, 18F, 5F, 18F, new CubeDeformation(0F)), PartPose.offsetAndRotation(20F, -3F, 2F, -0.7741F, 0F, 0.1745F));

        PartDefinition EquipL01 = EquipBaseL.addOrReplaceChild("EquipL01", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, 0F, -7F, 14F, 4F, 14F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, 1F));

        PartDefinition EquipL02 = EquipL01.addOrReplaceChild("EquipL02", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, -9F, -9F, 18F, 9F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.8F, 0F, -0.2618F, -0.1396F, 0F));

        PartDefinition EquipL03 = EquipBaseL.addOrReplaceChild("EquipL03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 13F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -6F, -2F, 0F, 0F, -0.3142F));

        PartDefinition ChestCannonL01a = EquipL03.addOrReplaceChild("ChestCannonL01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 7F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 2F, 1F, -0.1367F, -1.4114F, 0F));

        PartDefinition EquipBaseR = BodyMain.addOrReplaceChild("EquipBaseR", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, -6F, -8.5F, 18F, 5F, 18F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-20F, -3F, 2F, -0.7741F, 0F, -0.1745F));

        PartDefinition EquipR01 = EquipBaseR.addOrReplaceChild("EquipR01", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, 0F, -7F, 14F, 4F, 14F, new CubeDeformation(0F)), PartPose.offset(0F, -10F, 1F));

        PartDefinition EquipR02 = EquipR01.addOrReplaceChild("EquipR02", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, -9F, -9F, 18F, 9F, 20F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1.8F, 0F, -0.3142F, 0.1396F, 0F));

        PartDefinition EquipR03 = EquipBaseR.addOrReplaceChild("EquipR03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 13F, 10F, new CubeDeformation(0F)), PartPose.offset(-7F, -6F, 7F));

        PartDefinition ChestCannonR01a = EquipR03.addOrReplaceChild("ChestCannonR01a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 7F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5F, 2F, 1F, -0.1367F, -1.4114F, 0F));

        PartDefinition ChestCannon03a = BodyMain.addOrReplaceChild("ChestCannon03a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 12F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -6.4F, -7F, -0.182F, -0.0202F, 0F));

        PartDefinition ChestCannon02a = BodyMain.addOrReplaceChild("ChestCannon02a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 7F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.6F, -2.4F, -7F, 0.0911F, -0.0911F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 92).addBox(-13F, -7F, -7F, 14F, 22F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-15F, 0F, 2F, -0.8727F, 0.2094F, 0F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 89).addBox(-6.5F, 0F, -13F, 13F, 26F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 15F, 7F, -0.6981F, 0F, 0F));

        PartDefinition ChestCannon05a = BodyMain.addOrReplaceChild("ChestCannon05a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 9F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13F, -8F, -6F, -0.0911F, 0.1367F, 0.0221F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, -18F, -6F, 18F, 18F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, 6F, -0.2618F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10F, 0F, 1.0472F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -8F, 6F, -0.2618F, 0F, 0F));

        PartDefinition Head = GlowNeck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 20).addBox(-9.5F, -9F, -16F, 19F, 12F, 19F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -12F, -2.6F, -0.8727F, 0F, 0F));

        PartDefinition HeadTooth = Head.addOrReplaceChild("HeadTooth", CubeListBuilder.create().texOffs(68, 91).addBox(-7.5F, 0F, -7.5F, 15F, 4F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, -7F, 0.1745F, 0F, 0F));

        PartDefinition HeadBack01 = Head.addOrReplaceChild("HeadBack01", CubeListBuilder.create().texOffs(45, 6).addBox(-3.5F, -3.5F, 0F, 7F, 7F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -2F, 4F, 0.6829F, 0.4363F, 0F));

        PartDefinition HeadBack02 = Head.addOrReplaceChild("HeadBack02", CubeListBuilder.create().texOffs(45, 6).addBox(-3.5F, -3.5F, 0F, 7F, 7F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 5F, 0.7741F, 0.0873F, 0F));

        PartDefinition HeadBack03 = Head.addOrReplaceChild("HeadBack03", CubeListBuilder.create().texOffs(45, 6).addBox(-3.5F, -3.5F, 0F, 7F, 7F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -3F, 5F, 0.5918F, -0.5009F, 0F));

        PartDefinition Jaw = Head.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(77, 25).addBox(-8.5F, 0F, -14.5F, 17F, 9F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 0F, 0.8727F, 0F, 0F));

        PartDefinition Tongue = Jaw.addOrReplaceChild("Tongue", CubeListBuilder.create().texOffs(82, 54).addBox(-5F, 0F, -13F, 10F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -3.1F, 1F));

        PartDefinition JawTooth = Jaw.addOrReplaceChild("JawTooth", CubeListBuilder.create().texOffs(68, 91).addBox(-7.5F, 0F, -13F, 15F, 3F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.5F, -0.5F, -0.0873F, 0F, 0F));

        PartDefinition GlowEquipBaseL = GlowBodyMain.addOrReplaceChild("GlowEquipBaseL", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(20F, -3F, 2F, -0.7741F, 0F, 0.1745F));

        PartDefinition GlowEquipL01 = GlowEquipBaseL.addOrReplaceChild("GlowEquipL01", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -10F, 1F));

        PartDefinition GlowEquipL02 = GlowEquipL01.addOrReplaceChild("GlowEquipL02", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 1.8F, 0F, -0.2618F, -0.1396F, 0F));

        PartDefinition EquipCannon01 = GlowEquipL02.addOrReplaceChild("EquipCannon01", CubeListBuilder.create().texOffs(90, 0).addBox(0F, 0F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, -7.5F, -8F, -0.1367F, -0.0873F, 0F));

        PartDefinition EquipCannon02 = GlowEquipL02.addOrReplaceChild("EquipCannon02", CubeListBuilder.create().texOffs(90, 0).addBox(0F, 0F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.5F, -7.5F, -8F, -0.4554F, 0F, 0F));

        PartDefinition EquipCannon03 = GlowEquipL02.addOrReplaceChild("EquipCannon03", CubeListBuilder.create().texOffs(90, 0).addBox(0F, 0F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, -7.5F, -8F, -0.2731F, 0.0873F, 0F));

        PartDefinition GlowEquipL03 = GlowEquipBaseL.addOrReplaceChild("GlowEquipL03", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(6F, -6F, -2F, 0F, 0F, -0.3142F));

        PartDefinition GlowChestCannonL01a = GlowEquipL03.addOrReplaceChild("GlowChestCannonL01a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(5.5F, 2F, 1F, -0.1367F, -1.4114F, 0F));

        PartDefinition ChestCannonL01b = GlowChestCannonL01a.addOrReplaceChild("ChestCannonL01b", CubeListBuilder.create().texOffs(84, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 2.5F, 1F, 0.1367F, 0.0911F, 0F));

        PartDefinition GlowEquipBaseR = GlowBodyMain.addOrReplaceChild("GlowEquipBaseR", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-20F, -3F, 2F, -0.7741F, 0F, -0.1745F));

        PartDefinition GlowEquipR01 = GlowEquipBaseR.addOrReplaceChild("GlowEquipR01", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -10F, 1F));

        PartDefinition GlowEquipR02 = GlowEquipR01.addOrReplaceChild("GlowEquipR02", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 1.8F, 0F, -0.3142F, 0.1396F, 0F));

        PartDefinition EquipCannon01_1 = GlowEquipR02.addOrReplaceChild("EquipCannon01_1", CubeListBuilder.create().texOffs(90, 0).addBox(0F, 0F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, -7.5F, -8F, 0F, -0.0873F, 0F));

        PartDefinition EquipCannon02_1 = GlowEquipR02.addOrReplaceChild("EquipCannon02_1", CubeListBuilder.create().texOffs(90, 0).addBox(0F, 0F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.5F, -7.5F, -8F, -0.182F, 0F, 0F));

        PartDefinition EquipCannon03_1 = GlowEquipR02.addOrReplaceChild("EquipCannon03_1", CubeListBuilder.create().texOffs(90, 0).addBox(0F, 0F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, -7.5F, -8F, -0.2731F, 0.0873F, 0F));

        PartDefinition GlowEquipR03 = GlowEquipBaseR.addOrReplaceChild("GlowEquipR03", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(-7F, -6F, 7F));

        PartDefinition GlowChestCannonR01a = GlowEquipR03.addOrReplaceChild("GlowChestCannonR01a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(5.5F, 2F, 1F, -0.1367F, -1.4114F, 0F));

        PartDefinition ChestCannonR01b = GlowChestCannonR01a.addOrReplaceChild("ChestCannonR01b", CubeListBuilder.create().texOffs(84, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 2.5F, 1F, 0.182F, -0.182F, 0F));

        PartDefinition GlowChestCannon01a = GlowBodyMain.addOrReplaceChild("GlowChestCannon01a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(6F, -8F, -7F, -0.1367F, -0.0911F, 0F));

        PartDefinition ChestCannon01b = GlowChestCannon01a.addOrReplaceChild("ChestCannon01b", CubeListBuilder.create().texOffs(84, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 3.5F, 1F, -0.4554F, -0.5463F, 0F));

        PartDefinition GlowChestCannon02a = GlowBodyMain.addOrReplaceChild("GlowChestCannon02a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(4.6F, -2.4F, -7F, 0.0911F, -0.0911F, 0F));

        PartDefinition ChestCannon02b = GlowChestCannon02a.addOrReplaceChild("ChestCannon02b", CubeListBuilder.create().texOffs(84, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 2.5F, 1F, 0.0456F, -0.2731F, 0F));

        PartDefinition GlowChestCannon03a = GlowBodyMain.addOrReplaceChild("GlowChestCannon03a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-5F, -6.4F, -7F, -0.182F, -0.0202F, 0F));

        PartDefinition ChestCannon03b = GlowChestCannon03a.addOrReplaceChild("ChestCannon03b", CubeListBuilder.create().texOffs(84, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 2.5F, 1.5F, -0.6374F, 0F, 0F));

        PartDefinition GlowChestCannon04a = GlowBodyMain.addOrReplaceChild("GlowChestCannon04a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-10F, -1F, -7F, 0.182F, 0.0911F, 0F));

        PartDefinition ChestCannon04b = GlowChestCannon04a.addOrReplaceChild("ChestCannon04b", CubeListBuilder.create().texOffs(84, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 2.5F, 1F, -0.2731F, 0.2276F, 0F));

        PartDefinition GlowChestCannon05a = GlowBodyMain.addOrReplaceChild("GlowChestCannon05a", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(-13F, -8F, -6F, -0.0911F, 0.1367F, 0.0221F));

        PartDefinition ChestCannon05b = GlowChestCannon05a.addOrReplaceChild("ChestCannon05b", CubeListBuilder.create().texOffs(84, 0).addBox(0F, 0F, -9F, 2F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 2.5F, 1F, -0.7285F, 0.3389F, 0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
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
