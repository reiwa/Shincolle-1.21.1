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

public class ModelMountMiH<T extends Entity> extends EntityModel<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "mount_mi_h"), "main");

    private final ModelPart BodyMain;
    private final ModelPart UpperMain;
    private final ModelPart LowerMain;
    private final ModelPart LegArmorBase;
    private final ModelPart Back;
    private final ModelPart Head;
    private final ModelPart Back_1;
    private final ModelPart EquipHeadBack1;
    private final ModelPart EquipHeadBack1b;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead01c;
    private final ModelPart EquipHeadBack2;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHeadBack3;
    private final ModelPart EquipHeadBack3b;
    private final ModelPart EquipHeadBack2b;
    private final ModelPart EquipHead03_1;
    private final ModelPart EquipHead03_2;
    private final ModelPart EquipHead03_3;
    private final ModelPart EquipHeadBack3c;
    private final ModelPart EquipHeadBack3d;
    private final ModelPart EquipHeadBack3e;
    private final ModelPart EquipHeadBack3f;
    private final ModelPart EquipHeadBack3g;
    private final ModelPart EquipHeadBack3h;
    private final ModelPart EquipHead03a;
    private final ModelPart EquipHead01_1;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead00;
    private final ModelPart EquipHead01a;
    private final ModelPart EquipHead02a;
    private final ModelPart EquipHead00a;
    private final ModelPart EquipHead03a_1;
    private final ModelPart EquipHead03a_2;
    private final ModelPart EquipHead03a_3;
    private final ModelPart EquipHead01a_1;
    private final ModelPart EquipHead01b;
    private final ModelPart EquipHead01d;
    private final ModelPart Back_2;
    private final ModelPart TopCannonBase;
    private final ModelPart TopCannonBase_1;
    private final ModelPart TongueBase1;
    private final ModelPart Head_1;
    private final ModelPart Back_3;
    private final ModelPart EquipHeadBack1_1;
    private final ModelPart EquipHeadBack1b_1;
    private final ModelPart EquipHead01_2;
    private final ModelPart EquipHead01c_1;
    private final ModelPart EquipHeadBack2_1;
    private final ModelPart EquipHead03_4;
    private final ModelPart EquipHeadBack3_1;
    private final ModelPart EquipHeadBack3b_1;
    private final ModelPart EquipHeadBack2b_1;
    private final ModelPart EquipHead03_5;
    private final ModelPart EquipHead03_6;
    private final ModelPart EquipHead03_7;
    private final ModelPart EquipHeadBack3c_1;
    private final ModelPart EquipHeadBack3d_1;
    private final ModelPart EquipHeadBack3e_1;
    private final ModelPart EquipHeadBack3f_1;
    private final ModelPart EquipHeadBack3g_1;
    private final ModelPart EquipHeadBack3h_1;
    private final ModelPart EquipHead03a_4;
    private final ModelPart EquipHead01_3;
    private final ModelPart EquipHead02_1;
    private final ModelPart EquipHead00_1;
    private final ModelPart EquipHead01a_2;
    private final ModelPart EquipHead02a_1;
    private final ModelPart EquipHead00a_1;
    private final ModelPart EquipHead03a_5;
    private final ModelPart EquipHead03a_6;
    private final ModelPart EquipHead03a_7;
    private final ModelPart EquipHead01a_3;
    private final ModelPart EquipHead01b_1;
    private final ModelPart EquipHead01d_1;
    private final ModelPart TopCannon01b;
    private final ModelPart TopCannon01b_1;
    private final ModelPart TopCannon01b_2;
    private final ModelPart TopCannonUnder;
    private final ModelPart TopCannon02b;
    private final ModelPart TopCannon03b;
    private final ModelPart TopCannon04b;
    private final ModelPart TopCannon02b_1;
    private final ModelPart TopCannon03b_1;
    private final ModelPart TopCannon04b_1;
    private final ModelPart TopCannon02b_2;
    private final ModelPart TopCannon03b_2;
    private final ModelPart TopCannon04b_2;
    private final ModelPart TopCannon01b_3;
    private final ModelPart TopCannon01b_4;
    private final ModelPart TopCannon01b_5;
    private final ModelPart TopCannonUnder_1;
    private final ModelPart TopCannon02b_3;
    private final ModelPart TopCannon03b_3;
    private final ModelPart TopCannon04b_3;
    private final ModelPart TopCannon02b_4;
    private final ModelPart TopCannon03b_4;
    private final ModelPart TopCannon04b_4;
    private final ModelPart TopCannon02b_5;
    private final ModelPart TopCannon03b_5;
    private final ModelPart TopCannon04b_5;
    private final ModelPart Tongue01;
    private final ModelPart Tongue01a;
    private final ModelPart TongueBase2;
    private final ModelPart Tongue02;
    private final ModelPart Tongue02a;
    private final ModelPart TongueBase3;
    private final ModelPart Tongue03;
    private final ModelPart Tongue03a;
    private final ModelPart LegArmorA1;
    private final ModelPart LegArmorA2;
    private final ModelPart LegArmorA3;
    private final ModelPart LegArmorA4;
    private final ModelPart LegArmorB1;
    private final ModelPart LegArmorB2;
    private final ModelPart LegArmorB3;
    private final ModelPart LegArmorB4;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowLowerMain;
    private final ModelPart GlowTopCannonBase;
    private final ModelPart GlowTopCannonBase_1;

    public ModelMountMiH(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.UpperMain = this.BodyMain.getChild("UpperMain");
        this.Back = this.UpperMain.getChild("Back");
        this.Back_1 = this.Back.getChild("Back_1");
        this.EquipHead01b = this.Back_1.getChild("EquipHead01b");
        this.EquipHead01d = this.Back_1.getChild("EquipHead01d");
        this.Head = this.Back.getChild("Head");
        this.EquipHead01 = this.Head.getChild("EquipHead01");
        this.EquipHead01a_1 = this.EquipHead01.getChild("EquipHead01a_1");
        this.EquipHeadBack1 = this.Head.getChild("EquipHeadBack1");
        this.EquipHeadBack2 = this.EquipHeadBack1.getChild("EquipHeadBack2");
        this.EquipHead03_1 = this.EquipHeadBack2.getChild("EquipHead03_1");
        this.EquipHead03a_1 = this.EquipHead03_1.getChild("EquipHead03a_1");
        this.EquipHeadBack3g = this.EquipHeadBack2.getChild("EquipHeadBack3g");
        this.EquipHeadBack3b = this.EquipHeadBack2.getChild("EquipHeadBack3b");
        this.EquipHead03 = this.EquipHeadBack2.getChild("EquipHead03");
        this.EquipHead03a = this.EquipHead03.getChild("EquipHead03a");
        this.EquipHeadBack3h = this.EquipHeadBack2.getChild("EquipHeadBack3h");
        this.EquipHeadBack2b = this.EquipHeadBack2.getChild("EquipHeadBack2b");
        this.EquipHeadBack3 = this.EquipHeadBack2.getChild("EquipHeadBack3");
        this.EquipHead02 = this.EquipHeadBack3.getChild("EquipHead02");
        this.EquipHead02a = this.EquipHead02.getChild("EquipHead02a");
        this.EquipHead01_1 = this.EquipHeadBack3.getChild("EquipHead01_1");
        this.EquipHead01a = this.EquipHead01_1.getChild("EquipHead01a");
        this.EquipHead00 = this.EquipHeadBack3.getChild("EquipHead00");
        this.EquipHead00a = this.EquipHead00.getChild("EquipHead00a");
        this.EquipHeadBack3e = this.EquipHeadBack2.getChild("EquipHeadBack3e");
        this.EquipHeadBack3f = this.EquipHeadBack2.getChild("EquipHeadBack3f");
        this.EquipHead03_3 = this.EquipHeadBack2.getChild("EquipHead03_3");
        this.EquipHead03a_3 = this.EquipHead03_3.getChild("EquipHead03a_3");
        this.EquipHeadBack3c = this.EquipHeadBack2.getChild("EquipHeadBack3c");
        this.EquipHeadBack3d = this.EquipHeadBack2.getChild("EquipHeadBack3d");
        this.EquipHead03_2 = this.EquipHeadBack2.getChild("EquipHead03_2");
        this.EquipHead03a_2 = this.EquipHead03_2.getChild("EquipHead03a_2");
        this.EquipHeadBack1b = this.Head.getChild("EquipHeadBack1b");
        this.EquipHead01c = this.Head.getChild("EquipHead01c");
        this.LowerMain = this.BodyMain.getChild("LowerMain");
        this.Back_2 = this.LowerMain.getChild("Back_2");
        this.Head_1 = this.Back_2.getChild("Head_1");
        this.EquipHead01c_1 = this.Head_1.getChild("EquipHead01c_1");
        this.EquipHeadBack1_1 = this.Head_1.getChild("EquipHeadBack1_1");
        this.EquipHeadBack2_1 = this.EquipHeadBack1_1.getChild("EquipHeadBack2_1");
        this.EquipHeadBack3_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3_1");
        this.EquipHead02_1 = this.EquipHeadBack3_1.getChild("EquipHead02_1");
        this.EquipHead02a_1 = this.EquipHead02_1.getChild("EquipHead02a_1");
        this.EquipHead00_1 = this.EquipHeadBack3_1.getChild("EquipHead00_1");
        this.EquipHead00a_1 = this.EquipHead00_1.getChild("EquipHead00a_1");
        this.EquipHead01_3 = this.EquipHeadBack3_1.getChild("EquipHead01_3");
        this.EquipHead01a_2 = this.EquipHead01_3.getChild("EquipHead01a_2");
        this.EquipHeadBack3g_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3g_1");
        this.EquipHeadBack3e_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3e_1");
        this.EquipHead03_6 = this.EquipHeadBack2_1.getChild("EquipHead03_6");
        this.EquipHead03a_6 = this.EquipHead03_6.getChild("EquipHead03a_6");
        this.EquipHead03_7 = this.EquipHeadBack2_1.getChild("EquipHead03_7");
        this.EquipHead03a_7 = this.EquipHead03_7.getChild("EquipHead03a_7");
        this.EquipHead03_5 = this.EquipHeadBack2_1.getChild("EquipHead03_5");
        this.EquipHead03a_5 = this.EquipHead03_5.getChild("EquipHead03a_5");
        this.EquipHeadBack3d_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3d_1");
        this.EquipHead03_4 = this.EquipHeadBack2_1.getChild("EquipHead03_4");
        this.EquipHead03a_4 = this.EquipHead03_4.getChild("EquipHead03a_4");
        this.EquipHeadBack2b_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack2b_1");
        this.EquipHeadBack3c_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3c_1");
        this.EquipHeadBack3f_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3f_1");
        this.EquipHeadBack3h_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3h_1");
        this.EquipHeadBack3b_1 = this.EquipHeadBack2_1.getChild("EquipHeadBack3b_1");
        this.EquipHead01_2 = this.Head_1.getChild("EquipHead01_2");
        this.EquipHead01a_3 = this.EquipHead01_2.getChild("EquipHead01a_3");
        this.EquipHeadBack1b_1 = this.Head_1.getChild("EquipHeadBack1b_1");
        this.Back_3 = this.Back_2.getChild("Back_3");
        this.EquipHead01b_1 = this.Back_3.getChild("EquipHead01b_1");
        this.EquipHead01d_1 = this.Back_3.getChild("EquipHead01d_1");
        this.TopCannonBase = this.LowerMain.getChild("TopCannonBase");
        this.TopCannonUnder = this.TopCannonBase.getChild("TopCannonUnder");
        this.TopCannonBase_1 = this.LowerMain.getChild("TopCannonBase_1");
        this.TopCannonUnder_1 = this.TopCannonBase_1.getChild("TopCannonUnder_1");
        this.LegArmorBase = this.BodyMain.getChild("LegArmorBase");
        this.LegArmorA4 = this.LegArmorBase.getChild("LegArmorA4");
        this.LegArmorB4 = this.LegArmorA4.getChild("LegArmorB4");
        this.LegArmorA1 = this.LegArmorBase.getChild("LegArmorA1");
        this.LegArmorB1 = this.LegArmorA1.getChild("LegArmorB1");
        this.LegArmorA2 = this.LegArmorBase.getChild("LegArmorA2");
        this.LegArmorB2 = this.LegArmorA2.getChild("LegArmorB2");
        this.LegArmorA3 = this.LegArmorBase.getChild("LegArmorA3");
        this.LegArmorB3 = this.LegArmorA3.getChild("LegArmorB3");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowLowerMain = this.GlowBodyMain.getChild("GlowLowerMain");
        this.TongueBase1 = this.GlowLowerMain.getChild("TongueBase1");
        this.Tongue01a = this.TongueBase1.getChild("Tongue01a");
        this.Tongue01 = this.TongueBase1.getChild("Tongue01");
        this.TongueBase2 = this.TongueBase1.getChild("TongueBase2");
        this.TongueBase3 = this.TongueBase2.getChild("TongueBase3");
        this.Tongue03 = this.TongueBase3.getChild("Tongue03");
        this.Tongue03a = this.TongueBase3.getChild("Tongue03a");
        this.Tongue02a = this.TongueBase2.getChild("Tongue02a");
        this.Tongue02 = this.TongueBase2.getChild("Tongue02");
        this.GlowTopCannonBase = this.GlowLowerMain.getChild("GlowTopCannonBase");
        this.TopCannon01b_2 = this.GlowTopCannonBase.getChild("TopCannon01b_2");
        this.TopCannon02b_2 = this.TopCannon01b_2.getChild("TopCannon02b_2");
        this.TopCannon03b_2 = this.TopCannon01b_2.getChild("TopCannon03b_2");
        this.TopCannon04b_2 = this.TopCannon03b_2.getChild("TopCannon04b_2");
        this.TopCannon01b = this.GlowTopCannonBase.getChild("TopCannon01b");
        this.TopCannon02b = this.TopCannon01b.getChild("TopCannon02b");
        this.TopCannon03b = this.TopCannon01b.getChild("TopCannon03b");
        this.TopCannon04b = this.TopCannon03b.getChild("TopCannon04b");
        this.TopCannon01b_1 = this.GlowTopCannonBase.getChild("TopCannon01b_1");
        this.TopCannon03b_1 = this.TopCannon01b_1.getChild("TopCannon03b_1");
        this.TopCannon04b_1 = this.TopCannon03b_1.getChild("TopCannon04b_1");
        this.TopCannon02b_1 = this.TopCannon01b_1.getChild("TopCannon02b_1");
        this.GlowTopCannonBase_1 = this.GlowLowerMain.getChild("GlowTopCannonBase_1");
        this.TopCannon01b_4 = this.GlowTopCannonBase_1.getChild("TopCannon01b_4");
        this.TopCannon03b_4 = this.TopCannon01b_4.getChild("TopCannon03b_4");
        this.TopCannon04b_4 = this.TopCannon03b_4.getChild("TopCannon04b_4");
        this.TopCannon02b_4 = this.TopCannon01b_4.getChild("TopCannon02b_4");
        this.TopCannon01b_3 = this.GlowTopCannonBase_1.getChild("TopCannon01b_3");
        this.TopCannon03b_3 = this.TopCannon01b_3.getChild("TopCannon03b_3");
        this.TopCannon04b_3 = this.TopCannon03b_3.getChild("TopCannon04b_3");
        this.TopCannon02b_3 = this.TopCannon01b_3.getChild("TopCannon02b_3");
        this.TopCannon01b_5 = this.GlowTopCannonBase_1.getChild("TopCannon01b_5");
        this.TopCannon02b_5 = this.TopCannon01b_5.getChild("TopCannon02b_5");
        this.TopCannon03b_5 = this.TopCannon01b_5.getChild("TopCannon03b_5");
        this.TopCannon04b_5 = this.TopCannon03b_5.getChild("TopCannon04b_5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10F, 0F, -0.1396F, 0F, 0F));

        PartDefinition UpperMain = BodyMain.addOrReplaceChild("UpperMain", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, 14F, -0.4363F, 0F, 0F));

        PartDefinition Back = UpperMain.addOrReplaceChild("Back", CubeListBuilder.create().texOffs(19, 15).mirror().addBox(-10F, -14F, 9.5F, 10F, 6F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, -10F));

        PartDefinition Back_1 = Back.addOrReplaceChild("Back_1", CubeListBuilder.create().texOffs(19, 15).addBox(0F, -5F, 0F, 10F, 6F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, -9F, 9.5F));

        PartDefinition EquipHead01b = Back_1.addOrReplaceChild("EquipHead01b", CubeListBuilder.create().texOffs(16, 17).addBox(-6.5F, 0F, 0F, 13F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(13.6F, -5F, -3.5F, 0F, 0.6109F, 0F));

        PartDefinition EquipHead01d = Back_1.addOrReplaceChild("EquipHead01d", CubeListBuilder.create().texOffs(16, 15).addBox(-6.5F, 0F, 0F, 13F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13.6F, -5F, -3.5F, 0F, -0.6109F, 0F));

        PartDefinition Head = Back.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(20, 17).mirror().addBox(-9F, -8F, -5F, 9F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -12F, 14F, 0.0873F, 0F, 0F));

        PartDefinition EquipHead01 = Head.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(20, 16).addBox(0F, -8F, -5F, 9F, 6F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipHead01a_1 = EquipHead01.addOrReplaceChild("EquipHead01a_1", CubeListBuilder.create().texOffs(17, 0).addBox(-6F, 0F, 0F, 12F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(14F, -8.1F, -6.1F, -0.2094F, -2.5307F, 0.0524F));

        PartDefinition EquipHeadBack1 = Head.addOrReplaceChild("EquipHeadBack1", CubeListBuilder.create().texOffs(13, 1).mirror().addBox(-9F, -9.5F, -9.2F, 9F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -10.1F, -0.8727F, 0F, 0F));

        PartDefinition EquipHeadBack2 = EquipHeadBack1.addOrReplaceChild("EquipHeadBack2", CubeListBuilder.create().texOffs(9, 0).mirror().addBox(-10F, -12F, -11F, 10F, 2F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -2.2F, 0.7854F, 0F, 0F));

        PartDefinition EquipHead03_1 = EquipHeadBack2.addOrReplaceChild("EquipHead03_1", CubeListBuilder.create().texOffs(31, 50).addBox(-6F, 1F, -4F, 10F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(13.3F, -8.2F, -1.4F, -0.1745F, -1.5708F, -0.1745F));

        PartDefinition EquipHead03a_1 = EquipHead03_1.addOrReplaceChild("EquipHead03a_1", CubeListBuilder.create().texOffs(28, 43).mirror().addBox(-6F, 0F, 0F, 10F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 10.5F, -3.7F, 0.2443F, 0F, 0F));

        PartDefinition EquipHeadBack3g = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3g", CubeListBuilder.create().texOffs(12, 0).addBox(-8F, 0F, 0F, 8F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -12.5F, -10F, 0F, 0F, -0.576F));

        PartDefinition EquipHeadBack3b = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3b", CubeListBuilder.create().texOffs(11, 0).addBox(0F, -4F, -5.5F, 10F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, -13F, 0.4363F, 0F, 0F));

        PartDefinition EquipHead03 = EquipHeadBack2.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-6F, 1F, -4F, 12F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11.2F, -7.9F, -10.6F, -0.384F, -1.2217F, 0F));

        PartDefinition EquipHead03a = EquipHead03.addOrReplaceChild("EquipHead03a", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 10.5F, -3.7F, 0.2618F, 0F, 0F));

        PartDefinition EquipHeadBack3h = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3h", CubeListBuilder.create().texOffs(15, 0).addBox(-9F, 0F, 0F, 9F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -12F, 3.5F, -1.0123F, -0.2443F, -0.5236F));

        PartDefinition EquipHeadBack2b = EquipHeadBack2.addOrReplaceChild("EquipHeadBack2b", CubeListBuilder.create().texOffs(9, 0).addBox(0F, -12F, -11F, 10F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipHeadBack3 = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(-10F, -4F, -5.5F, 10F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, -13F, 0.4363F, 0F, 0F));

        PartDefinition EquipHead02 = EquipHeadBack3.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(0, 50).addBox(-6F, 0F, -4F, 12F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, -1F, -2F, -0.8727F, -0.4363F, 0.2269F));

        PartDefinition EquipHead02a = EquipHead02.addOrReplaceChild("EquipHead02a", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, -3.6F, 0.2967F, 0F, 0F));

        PartDefinition EquipHead01_1 = EquipHeadBack3.addOrReplaceChild("EquipHead01_1", CubeListBuilder.create().texOffs(0, 50).addBox(-6F, 0F, -4F, 12F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, -1F, -2F, -0.8727F, 0.4363F, -0.2269F));

        PartDefinition EquipHead01a = EquipHead01_1.addOrReplaceChild("EquipHead01a", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, -3.6F, 0.2967F, 0F, 0F));

        PartDefinition EquipHead00 = EquipHeadBack3.addOrReplaceChild("EquipHead00", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-6F, 0F, -4F, 12F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -4F, -0.8029F, 0F, 0F));

        PartDefinition EquipHead00a = EquipHead00.addOrReplaceChild("EquipHead00a", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, -3.6F, 0.3142F, 0F, 0F));

        PartDefinition EquipHeadBack3e = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3e", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(0F, 0F, 0F, 9F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, -12F, 3.5F, -1.0123F, 0.2443F, 0.5236F));

        PartDefinition EquipHeadBack3f = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3f", CubeListBuilder.create().texOffs(18, 3).addBox(-3F, -2F, -4F, 6F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.2F, -8F, -12.4F, 0.3491F, 0F, -0.5236F));

        PartDefinition EquipHead03_3 = EquipHeadBack2.addOrReplaceChild("EquipHead03_3", CubeListBuilder.create().texOffs(31, 50).mirror().addBox(-4F, 1F, -4F, 10F, 11F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13.3F, -8.2F, -1.4F, -0.1745F, 1.5708F, 0.1745F));

        PartDefinition EquipHead03a_3 = EquipHead03_3.addOrReplaceChild("EquipHead03a_3", CubeListBuilder.create().texOffs(28, 43).addBox(-4F, 0F, 0F, 10F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 10.5F, -3.7F, 0.2443F, 0F, 0F));

        PartDefinition EquipHeadBack3c = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3c", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-3F, -2F, -4F, 6F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11.2F, -8F, -12.4F, 0.3491F, 0F, 0.5236F));

        PartDefinition EquipHeadBack3d = EquipHeadBack2.addOrReplaceChild("EquipHeadBack3d", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(0F, 0F, 0F, 8F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, -12.5F, -10F, 0F, 0F, 0.576F));

        PartDefinition EquipHead03_2 = EquipHeadBack2.addOrReplaceChild("EquipHead03_2", CubeListBuilder.create().texOffs(0, 50).addBox(-6F, 1F, -4F, 12F, 10F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.2F, -7.9F, -10.6F, -0.384F, 1.2217F, 0F));

        PartDefinition EquipHead03a_2 = EquipHead03_2.addOrReplaceChild("EquipHead03a_2", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 10.5F, -3.7F, 0.2618F, 0F, 0F));

        PartDefinition EquipHeadBack1b = Head.addOrReplaceChild("EquipHeadBack1b", CubeListBuilder.create().texOffs(13, 0).addBox(-8F, -9.5F, -9.4F, 9F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -2F, -10.1F, -0.8727F, 0F, 0F));

        PartDefinition EquipHead01c = Head.addOrReplaceChild("EquipHead01c", CubeListBuilder.create().texOffs(17, 0).mirror().addBox(-6F, 0F, 0F, 12F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-14F, -8.1F, -6.1F, -0.2094F, 2.5307F, 0.0524F));

        PartDefinition LowerMain = BodyMain.addOrReplaceChild("LowerMain", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 14F, 0.2618F, 0F, 0F));

        PartDefinition Back_2 = LowerMain.addOrReplaceChild("Back_2", CubeListBuilder.create().texOffs(16, 15).mirror().addBox(-10F, -17F, 9.5F, 10F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -11F, -11F));

        PartDefinition Head_1 = Back_2.addOrReplaceChild("Head_1", CubeListBuilder.create().texOffs(19, 16).addBox(-9F, -8F, -5F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -12F, 14F, 0.0873F, 0F, 0F));

        PartDefinition EquipHead01c_1 = Head_1.addOrReplaceChild("EquipHead01c_1", CubeListBuilder.create().texOffs(17, 17).addBox(-6F, 0F, 0F, 12F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.8F, -8.1F, -8.9F, 0.2094F, -0.6109F, 0.0524F));

        PartDefinition EquipHeadBack1_1 = Head_1.addOrReplaceChild("EquipHeadBack1_1", CubeListBuilder.create().texOffs(13, 3).mirror().addBox(-9F, -10.5F, -9.2F, 9F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -10.1F, -0.8727F, 0F, 0F));

        PartDefinition EquipHeadBack2_1 = EquipHeadBack1_1.addOrReplaceChild("EquipHeadBack2_1", CubeListBuilder.create().texOffs(9, 0).mirror().addBox(-10F, -12F, -11F, 10F, 2F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -2.2F, 0.7854F, 0F, 0F));

        PartDefinition EquipHeadBack3_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3_1", CubeListBuilder.create().texOffs(11, 0).mirror().addBox(-10F, -4F, -5.5F, 10F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, -13F, 0.4363F, 0F, 0F));

        PartDefinition EquipHead02_1 = EquipHeadBack3_1.addOrReplaceChild("EquipHead02_1", CubeListBuilder.create().texOffs(0, 50).addBox(-6F, 0F, -4F, 12F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, -1F, -2F, -0.8727F, -0.4363F, 0.2269F));

        PartDefinition EquipHead02a_1 = EquipHead02_1.addOrReplaceChild("EquipHead02a_1", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.5F, -3.6F, 0.2967F, 0F, 0F));

        PartDefinition EquipHead00_1 = EquipHeadBack3_1.addOrReplaceChild("EquipHead00_1", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-6F, 0F, -4F, 12F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -4F, -0.8029F, 0F, 0F));

        PartDefinition EquipHead00a_1 = EquipHead00_1.addOrReplaceChild("EquipHead00a_1", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.5F, -3.6F, 0.192F, 0F, 0F));

        PartDefinition EquipHead01_3 = EquipHeadBack3_1.addOrReplaceChild("EquipHead01_3", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-6F, 0F, -4F, 12F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, -1F, -2F, -0.8727F, 0.4363F, -0.2269F));

        PartDefinition EquipHead01a_2 = EquipHead01_3.addOrReplaceChild("EquipHead01a_2", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 12F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.5F, -3.6F, 0.2967F, 0F, 0F));

        PartDefinition EquipHeadBack3g_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3g_1", CubeListBuilder.create().texOffs(12, 0).addBox(-8F, 0F, 0F, 8F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -12.5F, -10F, 0F, 0F, -0.576F));

        PartDefinition EquipHeadBack3e_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3e_1", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(0F, 0F, 0F, 9F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, -12F, 3.5F, -1.0123F, 0.2443F, 0.5236F));

        PartDefinition EquipHead03_6 = EquipHeadBack2_1.addOrReplaceChild("EquipHead03_6", CubeListBuilder.create().texOffs(0, 50).addBox(-6F, 1F, -4F, 12F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.2F, -7.9F, -10.6F, -0.384F, 1.2217F, 0F));

        PartDefinition EquipHead03a_6 = EquipHead03_6.addOrReplaceChild("EquipHead03a_6", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 11F, 5F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 6.2F, -3.7F));

        PartDefinition EquipHead03_7 = EquipHeadBack2_1.addOrReplaceChild("EquipHead03_7", CubeListBuilder.create().texOffs(31, 50).mirror().addBox(-4F, 1F, -4F, 10F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13.3F, -8.2F, -1.4F, -0.1745F, 1.5708F, 0.1745F));

        PartDefinition EquipHead03a_7 = EquipHead03_7.addOrReplaceChild("EquipHead03a_7", CubeListBuilder.create().texOffs(28, 43).addBox(-4F, 0F, 0F, 10F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 6.5F, -3.7F, 0.2443F, 0F, 0F));

        PartDefinition EquipHead03_5 = EquipHeadBack2_1.addOrReplaceChild("EquipHead03_5", CubeListBuilder.create().texOffs(31, 50).addBox(-6F, 1F, -4F, 10F, 7F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(13.3F, -8.2F, -1.4F, -0.1745F, -1.5708F, -0.1745F));

        PartDefinition EquipHead03a_5 = EquipHead03_5.addOrReplaceChild("EquipHead03a_5", CubeListBuilder.create().texOffs(28, 43).mirror().addBox(-6F, 0F, 0F, 10F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 6.5F, -3.7F, 0.2443F, 0F, 0F));

        PartDefinition EquipHeadBack3d_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3d_1", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(0F, 0F, 0F, 8F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10F, -12.5F, -10F, 0F, 0F, 0.576F));

        PartDefinition EquipHead03_4 = EquipHeadBack2_1.addOrReplaceChild("EquipHead03_4", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-6F, 1F, -4F, 12F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11.2F, -7.9F, -10.6F, -0.384F, -1.2217F, 0F));

        PartDefinition EquipHead03a_4 = EquipHead03_4.addOrReplaceChild("EquipHead03a_4", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, 0F, 11F, 5F, 2F, new CubeDeformation(0F)), PartPose.offset(1F, 6.3F, -3.7F));

        PartDefinition EquipHeadBack2b_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack2b_1", CubeListBuilder.create().texOffs(9, 0).addBox(0F, -12F, -11F, 10F, 2F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipHeadBack3c_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3c_1", CubeListBuilder.create().texOffs(18, 2).mirror().addBox(-3F, -2F, -4F, 6F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11.2F, -8F, -12.4F, 0.3491F, 0F, 0.5236F));

        PartDefinition EquipHeadBack3f_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3f_1", CubeListBuilder.create().texOffs(18, 2).addBox(-3F, -2F, -4F, 6F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.2F, -8F, -12.4F, 0.3491F, 0F, -0.5236F));

        PartDefinition EquipHeadBack3h_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3h_1", CubeListBuilder.create().texOffs(15, 0).addBox(-9F, 0F, 0F, 9F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10F, -12F, 3.5F, -1.0123F, -0.2443F, -0.5236F));

        PartDefinition EquipHeadBack3b_1 = EquipHeadBack2_1.addOrReplaceChild("EquipHeadBack3b_1", CubeListBuilder.create().texOffs(11, 0).addBox(0F, -4F, -5.5F, 10F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, -13F, 0.4363F, 0F, 0F));

        PartDefinition EquipHead01_2 = Head_1.addOrReplaceChild("EquipHead01_2", CubeListBuilder.create().texOffs(19, 18).addBox(0F, -8F, -5F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipHead01a_3 = EquipHead01_2.addOrReplaceChild("EquipHead01a_3", CubeListBuilder.create().texOffs(17, 17).mirror().addBox(-6F, 0F, 0F, 12F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(11.7F, -8.1F, -8.9F, 0.2094F, 0.6109F, -0.0524F));

        PartDefinition EquipHeadBack1b_1 = Head_1.addOrReplaceChild("EquipHeadBack1b_1", CubeListBuilder.create().texOffs(13, 3).addBox(-8F, -10.5F, -9.4F, 9F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -2F, -10.1F, -0.8727F, 0F, 0F));

        PartDefinition Back_3 = Back_2.addOrReplaceChild("Back_3", CubeListBuilder.create().texOffs(16, 15).addBox(0F, -5F, 0F, 10F, 7F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, 9.5F));

        PartDefinition EquipHead01b_1 = Back_3.addOrReplaceChild("EquipHead01b_1", CubeListBuilder.create().texOffs(16, 15).mirror().addBox(-6.5F, 0F, 0F, 13F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12.7F, -5F, -3.3F, 0F, 0.6109F, 0F));

        PartDefinition EquipHead01d_1 = Back_3.addOrReplaceChild("EquipHead01d_1", CubeListBuilder.create().texOffs(16, 15).addBox(-6.5F, 0F, 0F, 13F, 6F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12.7F, -5F, -3.3F, 0F, -0.6981F, 0F));

        PartDefinition TopCannonBase = LowerMain.addOrReplaceChild("TopCannonBase", CubeListBuilder.create().texOffs(32, 26).addBox(-5F, -6F, -3F, 10F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(13F, 1F, -15F, -0.1396F, -0.1745F, 0.0873F));

        PartDefinition TopCannonUnder = TopCannonBase.addOrReplaceChild("TopCannonUnder", CubeListBuilder.create().texOffs(44, 27).addBox(-2F, 0F, -4F, 4F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 2F));

        PartDefinition TopCannonBase_1 = LowerMain.addOrReplaceChild("TopCannonBase_1", CubeListBuilder.create().texOffs(32, 26).addBox(-5F, -6F, -3F, 10F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13F, 1F, -15F, -0.1396F, 0.2618F, -0.0873F));

        PartDefinition TopCannonUnder_1 = TopCannonBase_1.addOrReplaceChild("TopCannonUnder_1", CubeListBuilder.create().texOffs(38, 28).addBox(-2F, 0F, -4F, 4F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, 2F));

        PartDefinition LegArmorBase = BodyMain.addOrReplaceChild("LegArmorBase", CubeListBuilder.create().texOffs(32, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 20F, 6F));

        PartDefinition LegArmorA4 = LegArmorBase.addOrReplaceChild("LegArmorA4", CubeListBuilder.create().texOffs(0, 4).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 11F, 1.3963F, -0.6829F, -0.3142F));

        PartDefinition LegArmorB4 = LegArmorA4.addOrReplaceChild("LegArmorB4", CubeListBuilder.create().texOffs(21, 2).mirror().addBox(-4.5F, -4.5F, -1F, 9F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 3F, 0F, 0.5236F, 0.7854F, -0.2793F));

        PartDefinition LegArmorA1 = LegArmorBase.addOrReplaceChild("LegArmorA1", CubeListBuilder.create().texOffs(0, 4).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6F, -6F, -1.2217F, 0.3491F, 0.3142F));

        PartDefinition LegArmorB1 = LegArmorA1.addOrReplaceChild("LegArmorB1", CubeListBuilder.create().texOffs(21, 15).addBox(-4.5F, -4.5F, -1F, 9F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(20F, -4.5F, 0F, -0.1396F, 0.6109F, -0.1396F));

        PartDefinition LegArmorA2 = LegArmorBase.addOrReplaceChild("LegArmorA2", CubeListBuilder.create().texOffs(0, 4).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6F, -10F, -1.2217F, 0F, -0.3142F));

        PartDefinition LegArmorB2 = LegArmorA2.addOrReplaceChild("LegArmorB2", CubeListBuilder.create().texOffs(21, 15).mirror().addBox(-4.5F, -4.5F, -1F, 9F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-18F, -4.5F, 0F, -0.1396F, -0.6981F, 0.1396F));

        PartDefinition LegArmorA3 = LegArmorBase.addOrReplaceChild("LegArmorA3", CubeListBuilder.create().texOffs(0, 4).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 6F, 10F, -1.2217F, 0F, 0.3142F));

        PartDefinition LegArmorB3 = LegArmorA3.addOrReplaceChild("LegArmorB3", CubeListBuilder.create().texOffs(21, 15).addBox(-4.5F, -4.5F, -1F, 9F, 9F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 0F, 0F, -0.0524F, 0.5236F, -0.2793F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10F, 0F, -0.1396F, 0F, 0F));

        PartDefinition GlowLowerMain = GlowBodyMain.addOrReplaceChild("GlowLowerMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, 8F, 14F, 0.2618F, 0F, 0F));

        PartDefinition TongueBase1 = GlowLowerMain.addOrReplaceChild("TongueBase1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, -17F, -0.6109F, 0.2618F, -0.0524F));

        PartDefinition Tongue01a = TongueBase1.addOrReplaceChild("Tongue01a", CubeListBuilder.create().texOffs(0, 29).addBox(-10F, -2F, -10F, 10F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 0F, 0F, 0F, 0F, -0.1047F));

        PartDefinition Tongue01 = TongueBase1.addOrReplaceChild("Tongue01", CubeListBuilder.create().texOffs(0, 29).addBox(0F, -2F, -10F, 10F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 0F, 0F, 0F, 0F, 0.1047F));

        PartDefinition TongueBase2 = TongueBase1.addOrReplaceChild("TongueBase2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -9F, 0.6109F, 0F, 0F));

        PartDefinition TongueBase3 = TongueBase2.addOrReplaceChild("TongueBase3", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.2F, -6.5F, 0.6109F, 0F, 0F));

        PartDefinition Tongue03 = TongueBase3.addOrReplaceChild("Tongue03", CubeListBuilder.create().texOffs(8, 29).addBox(0F, -2F, -8F, 8F, 3F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 0.2F, 0F, 0F, 0.0524F, 0.1396F));

        PartDefinition Tongue03a = TongueBase3.addOrReplaceChild("Tongue03a", CubeListBuilder.create().texOffs(6, 29).addBox(-8F, -2F, -8F, 8F, 3F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 0F, 0F, 0F, -0.0524F, -0.1396F));

        PartDefinition Tongue02a = TongueBase2.addOrReplaceChild("Tongue02a", CubeListBuilder.create().texOffs(0, 31).addBox(-9F, -2F, -8F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3F, 0F, 0F, 0F, 0F, -0.1047F));

        PartDefinition Tongue02 = TongueBase2.addOrReplaceChild("Tongue02", CubeListBuilder.create().texOffs(4, 30).addBox(0F, -2F, -8F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, 0F, 0F, 0F, 0F, 0.1047F));

        PartDefinition GlowTopCannonBase = GlowLowerMain.addOrReplaceChild("GlowTopCannonBase", CubeListBuilder.create().texOffs(32, 26), PartPose.offsetAndRotation(13F, 1F, -15F, -0.1396F, -0.1745F, 0.0873F));

        PartDefinition TopCannon01b_2 = GlowTopCannonBase.addOrReplaceChild("TopCannon01b_2", CubeListBuilder.create().texOffs(43, 26).addBox(-1F, -1.2F, -4F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, -3.5F, -2F, -0.3187F, 0F, 0F));

        PartDefinition TopCannon02b_2 = TopCannon01b_2.addOrReplaceChild("TopCannon02b_2", CubeListBuilder.create().texOffs(45, 26).addBox(-0.5F, 0F, 0F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, -7F));

        PartDefinition TopCannon03b_2 = TopCannon01b_2.addOrReplaceChild("TopCannon03b_2", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5F, 0F, -0.5F, 1F, 10F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -3.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04b_2 = TopCannon03b_2.addOrReplaceChild("TopCannon04b_2", CubeListBuilder.create().texOffs(56, 26).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition TopCannon01b = GlowTopCannonBase.addOrReplaceChild("TopCannon01b", CubeListBuilder.create().texOffs(37, 30).addBox(-1F, -1.2F, -4F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, -3.5F, -2F, -0.3187F, 0F, 0F));

        PartDefinition TopCannon02b = TopCannon01b.addOrReplaceChild("TopCannon02b", CubeListBuilder.create().texOffs(42, 27).addBox(-0.5F, 0F, 0F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, -7F));

        PartDefinition TopCannon03b = TopCannon01b.addOrReplaceChild("TopCannon03b", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5F, 0F, -0.5F, 1F, 10F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -3.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04b = TopCannon03b.addOrReplaceChild("TopCannon04b", CubeListBuilder.create().texOffs(56, 28).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition TopCannon01b_1 = GlowTopCannonBase.addOrReplaceChild("TopCannon01b_1", CubeListBuilder.create().texOffs(46, 30).addBox(-1F, -1.2F, -4F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.5F, -2F, -0.3187F, 0F, 0F));

        PartDefinition TopCannon03b_1 = TopCannon01b_1.addOrReplaceChild("TopCannon03b_1", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5F, 0F, -0.5F, 1F, 10F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -3.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04b_1 = TopCannon03b_1.addOrReplaceChild("TopCannon04b_1", CubeListBuilder.create().texOffs(47, 26).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition TopCannon02b_1 = TopCannon01b_1.addOrReplaceChild("TopCannon02b_1", CubeListBuilder.create().texOffs(35, 26).addBox(-0.5F, 0F, 0F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, -7F));

        PartDefinition GlowTopCannonBase_1 = GlowLowerMain.addOrReplaceChild("GlowTopCannonBase_1", CubeListBuilder.create().texOffs(32, 26), PartPose.offsetAndRotation(-13F, 1F, -15F, -0.1396F, 0.2618F, -0.0873F));

        PartDefinition TopCannon01b_4 = GlowTopCannonBase_1.addOrReplaceChild("TopCannon01b_4", CubeListBuilder.create().texOffs(52, 28).addBox(-1F, -1.2F, -4F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.5F, -2F, -0.3187F, 0F, 0F));

        PartDefinition TopCannon03b_4 = TopCannon01b_4.addOrReplaceChild("TopCannon03b_4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5F, 0F, -0.5F, 1F, 10F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -3.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04b_4 = TopCannon03b_4.addOrReplaceChild("TopCannon04b_4", CubeListBuilder.create().texOffs(33, 26).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition TopCannon02b_4 = TopCannon01b_4.addOrReplaceChild("TopCannon02b_4", CubeListBuilder.create().texOffs(37, 27).addBox(-0.5F, 0F, 0F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, -7F));

        PartDefinition TopCannon01b_3 = GlowTopCannonBase_1.addOrReplaceChild("TopCannon01b_3", CubeListBuilder.create().texOffs(35, 28).addBox(-1F, -1.2F, -4F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, -3.5F, -2F, -0.3187F, 0F, 0F));

        PartDefinition TopCannon03b_3 = TopCannon01b_3.addOrReplaceChild("TopCannon03b_3", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5F, 0F, -0.5F, 1F, 10F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -3.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04b_3 = TopCannon03b_3.addOrReplaceChild("TopCannon04b_3", CubeListBuilder.create().texOffs(33, 28).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

        PartDefinition TopCannon02b_3 = TopCannon01b_3.addOrReplaceChild("TopCannon02b_3", CubeListBuilder.create().texOffs(37, 27).addBox(-0.5F, 0F, 0F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, -7F));

        PartDefinition TopCannon01b_5 = GlowTopCannonBase_1.addOrReplaceChild("TopCannon01b_5", CubeListBuilder.create().texOffs(46, 27).addBox(-1F, -1.2F, -4F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, -3.5F, -2F, -0.3187F, 0F, 0F));

        PartDefinition TopCannon02b_5 = TopCannon01b_5.addOrReplaceChild("TopCannon02b_5", CubeListBuilder.create().texOffs(42, 27).addBox(-0.5F, 0F, 0F, 1F, 1F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, -7F));

        PartDefinition TopCannon03b_5 = TopCannon01b_5.addOrReplaceChild("TopCannon03b_5", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5F, 0F, -0.5F, 1F, 10F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.3F, -3.9F, -1.5708F, 0F, 0F));

        PartDefinition TopCannon04b_5 = TopCannon03b_5.addOrReplaceChild("TopCannon04b_5", CubeListBuilder.create().texOffs(40, 27).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 2F, 0F));

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
