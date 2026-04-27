package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelBattleshipRe<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "battleship_re"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBattleshipRe");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBattleshipRe");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBattleshipRe");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private boolean isTailPoseOverride;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Cloth;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart BagMain;
    private final ModelPart TailBase;
    private final ModelPart Butt;
    private final ModelPart Cloth2;
    private final ModelPart Head;
    private final ModelPart Ear01;
    private final ModelPart Ear02;
    private final ModelPart Hair;
    private final ModelPart Hair01;
    private final ModelPart HairU01;
    private final ModelPart Cap;
    private final ModelPart Cap2;
    private final ModelPart Ahoke;
    private final ModelPart BoobM;
    private final ModelPart PalmLeft;
    private final ModelPart PalmRight;
    private final ModelPart BagMain2;
    private final ModelPart BagStrap1;
    private final ModelPart BagStrap2;
    private final ModelPart Tail1;
    private final ModelPart TailBack0;
    private final ModelPart Tail2;
    private final ModelPart TailBack1;
    private final ModelPart Tail3;
    private final ModelPart TailBack2;
    private final ModelPart Tail4;
    private final ModelPart TailBack3;
    private final ModelPart Tail5;
    private final ModelPart TailBack4;
    private final ModelPart Tail6;
    private final ModelPart TailBack5;
    private final ModelPart TailHeadBase;
    private final ModelPart TailBack6;
    private final ModelPart TailJaw1;
    private final ModelPart TailHead1;
    private final ModelPart TailHeadCL1;
    private final ModelPart TailHeadCR1;
    private final ModelPart TailJawT01;
    private final ModelPart TailJaw2;
    private final ModelPart TailJaw3;
    private final ModelPart TailHead2;
    private final ModelPart TailHeadT01;
    private final ModelPart TailHeadC1;
    private final ModelPart TailHead3;
    private final ModelPart TailHeadC2;
    private final ModelPart TailHeadC3;
    private final ModelPart TailHeadC4;
    private final ModelPart TailHeadCL2;
    private final ModelPart TailHeadCL3;
    private final ModelPart TailHeadCR2;
    private final ModelPart TailHeadCR3;
    private final ModelPart LegRight;
    private final ModelPart LegLeft;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowTailBase;
    private final ModelPart GlowTail1;
    private final ModelPart GlowTail2;
    private final ModelPart GlowTail3;
    private final ModelPart GlowTail4;
    private final ModelPart GlowTail5;
    private final ModelPart GlowTail6;
    private final ModelPart GlowTailHeadBase;
    private final ModelPart GlowTailHead1;
    private final ModelPart GlowTailJaw1;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultY;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultY;
    private final float armRight02DefaultZ;

    public ModelBattleshipRe(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.PalmLeft = this.ArmLeft02.getChild("PalmLeft");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.BagMain = this.BodyMain.getChild("BagMain");
        this.BagStrap2 = this.BagMain.getChild("BagStrap2");
        this.BagStrap1 = this.BagMain.getChild("BagStrap1");
        this.BagMain2 = this.BagMain.getChild("BagMain2");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.PalmRight = this.ArmRight02.getChild("PalmRight");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.BoobM = this.BoobR.getChild("BoobM");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft = this.Butt.getChild("LegLeft");
        this.LegRight = this.Butt.getChild("LegRight");
        this.Cloth = this.BodyMain.getChild("Cloth");
        this.Cloth2 = this.Cloth.getChild("Cloth2");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Cap = this.Head.getChild("Cap");
        this.Ear01 = this.Head.getChild("Ear01");
        this.Ear02 = this.Head.getChild("Ear02");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.Hair01 = this.Head.getChild("Hair01");
        this.HairU01 = this.Head.getChild("HairU01");
        this.Cap2 = this.Neck.getChild("Cap2");
        this.TailBase = this.BodyMain.getChild("TailBase");
        this.Tail1 = this.TailBase.getChild("Tail1");
        this.Tail2 = this.Tail1.getChild("Tail2");
        this.Tail3 = this.Tail2.getChild("Tail3");
        this.Tail4 = this.Tail3.getChild("Tail4");
        this.Tail5 = this.Tail4.getChild("Tail5");
        this.Tail6 = this.Tail5.getChild("Tail6");
        this.TailHeadBase = this.Tail6.getChild("TailHeadBase");
        this.TailHeadCL1 = this.TailHeadBase.getChild("TailHeadCL1");
        this.TailHeadCL2 = this.TailHeadCL1.getChild("TailHeadCL2");
        this.TailHeadCL3 = this.TailHeadCL1.getChild("TailHeadCL3");
        this.TailHeadCR1 = this.TailHeadBase.getChild("TailHeadCR1");
        this.TailHeadCR2 = this.TailHeadCR1.getChild("TailHeadCR2");
        this.TailHeadCR3 = this.TailHeadCR1.getChild("TailHeadCR3");
        this.TailHead1 = this.TailHeadBase.getChild("TailHead1");
        this.TailHeadC1 = this.TailHead1.getChild("TailHeadC1");
        this.TailHeadC2 = this.TailHeadC1.getChild("TailHeadC2");
        this.TailHeadC3 = this.TailHeadC1.getChild("TailHeadC3");
        this.TailHeadC4 = this.TailHeadC1.getChild("TailHeadC4");
        this.TailHead3 = this.TailHead1.getChild("TailHead3");
        this.TailHead2 = this.TailHead1.getChild("TailHead2");
        this.TailJaw1 = this.TailHeadBase.getChild("TailJaw1");
        this.TailJaw2 = this.TailJaw1.getChild("TailJaw2");
        this.TailJaw3 = this.TailJaw1.getChild("TailJaw3");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.GlowTailBase = this.GlowBodyMain.getChild("GlowTailBase");
        this.GlowTail1 = this.GlowTailBase.getChild("GlowTail1");
        this.GlowTail2 = this.GlowTail1.getChild("GlowTail2");
        this.GlowTail3 = this.GlowTail2.getChild("GlowTail3");
        this.GlowTail4 = this.GlowTail3.getChild("GlowTail4");
        this.GlowTail5 = this.GlowTail4.getChild("GlowTail5");
        this.GlowTail6 = this.GlowTail5.getChild("GlowTail6");
        this.TailBack6 = this.GlowTail6.getChild("TailBack6");
        this.GlowTailHeadBase = this.GlowTail6.getChild("GlowTailHeadBase");
        this.GlowTailHead1 = this.GlowTailHeadBase.getChild("GlowTailHead1");
        this.TailHeadT01 = this.GlowTailHead1.getChild("TailHeadT01");
        this.GlowTailJaw1 = this.GlowTailHeadBase.getChild("GlowTailJaw1");
        this.TailJawT01 = this.GlowTailJaw1.getChild("TailJawT01");
        this.TailBack5 = this.GlowTail5.getChild("TailBack5");
        this.TailBack4 = this.GlowTail4.getChild("TailBack4");
        this.TailBack3 = this.GlowTail3.getChild("TailBack3");
        this.TailBack2 = this.GlowTail2.getChild("TailBack2");
        this.TailBack1 = this.GlowTail1.getChild("TailBack1");
        this.TailBack0 = this.GlowTailBase.getChild("TailBack0");
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultY = this.ArmLeft02.y;
        this.armLeft02DefaultZ = this.ArmLeft02.z;

        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultY = this.ArmRight02.y;
        this.armRight02DefaultZ = this.ArmRight02.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 34).addBox(-7F, -9F, -4F, 14F, 15F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8F, 0F, 0.0524F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(0F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, -8.5F, -0.5F, 0.2618F, 0F, -0.4363F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-6F, 0F, -6F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(6F, 10F, 3F));

        PartDefinition PalmLeft = ArmLeft02.addOrReplaceChild("PalmLeft", CubeListBuilder.create().texOffs(0, 89).mirror().addBox(-2.5F, 0F, -2.5F, 5F, 4F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 7F, -3F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(0, 80).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -9.5F, -3F, -0.7854F, -0.1222F, -0.0873F));

        PartDefinition BagMain = BodyMain.addOrReplaceChild("BagMain", CubeListBuilder.create().texOffs(37, 23).addBox(-8F, 0F, 0F, 14F, 12F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, -13F, 6.5F, -0.2618F, 0F, 0.0873F));

        PartDefinition BagStrap2 = BagMain.addOrReplaceChild("BagStrap2", CubeListBuilder.create().texOffs(82, 24).addBox(-3F, 0F, -15F, 3F, 10F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, 1F, 2F, 0.3491F, 0.3491F, 0.1396F));

        PartDefinition BagStrap1 = BagMain.addOrReplaceChild("BagStrap1", CubeListBuilder.create().texOffs(103, 16).addBox(0F, 0F, -11F, 3F, 10F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, 1F, 0.5F, 0.2618F, -0.1396F, -0.1745F));

        PartDefinition BagMain2 = BagMain.addOrReplaceChild("BagMain2", CubeListBuilder.create().texOffs(36, 23).addBox(-7.5F, 0F, 0F, 15F, 9F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 11F, -0.5F, 0.6981F, 0F, -0.2618F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 57).addBox(-6F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, -8.5F, -0.5F, 0.2618F, 0F, 0.4363F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 57).addBox(0F, 0F, -6F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(-6F, 10F, 3F));

        PartDefinition PalmRight = ArmRight02.addOrReplaceChild("PalmRight", CubeListBuilder.create().texOffs(0, 89).addBox(-2.5F, 0F, -2.5F, 5F, 4F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3F, 7F, -3F, 0F, 0.0253F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 80).addBox(-3.5F, 0F, 0F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -9.5F, -3F, -0.7854F, 0.1222F, 0.0873F));

        PartDefinition BoobM = BoobR.addOrReplaceChild("BoobM", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.2F, 4.5F, 0.3F, 0.7854F, 0F, -0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(106, 0).addBox(-8F, 4F, -5F, 16F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.1745F, 0F, 0F));

        PartDefinition LegLeft = Butt.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(0, 98).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 22F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.5F, 11F, -2F, -0.2269F, 0F, 0.0524F));

        PartDefinition LegRight = Butt.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(0, 98).addBox(-3.5F, 0F, -3.5F, 7F, 22F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.5F, 11F, -2F, -0.2269F, 0F, -0.0524F));

        PartDefinition Cloth = BodyMain.addOrReplaceChild("Cloth", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, -4.5F, 16F, 14F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, -8.5F, 0F));

        PartDefinition Cloth2 = Cloth.addOrReplaceChild("Cloth2", CubeListBuilder.create().texOffs(50, 0).addBox(-8.5F, 0F, -5F, 17F, 12F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, -0.0524F, 0F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -1.5F, -7F, 15F, 5F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11.5F, 0.5F, 0.2618F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(39, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.5F, 0F, -0.1745F, 0F, 0F));

        PartDefinition Cap = Head.addOrReplaceChild("Cap", CubeListBuilder.create().texOffs(204, 40).addBox(-8F, -17F, -2F, 16F, 17F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.6F, 2F, 0.2F, 0F, 0F));

        PartDefinition Ear01 = Head.addOrReplaceChild("Ear01", CubeListBuilder.create().texOffs(136, 17).mirror().addBox(-1.5F, 0F, -6F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -14.5F, 5.7F, -0.6981F, 0.2618F, -0.1396F));

        PartDefinition Ear02 = Head.addOrReplaceChild("Ear02", CubeListBuilder.create().texOffs(136, 17).addBox(-1.5F, 0F, -6F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -14.5F, 5.7F, -0.6981F, -0.2618F, 0.1396F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(24, 61).addBox(-7.5F, -8F, -8F, 15F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.3F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(28, 90).addBox(0F, -6F, -11F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, -4F, -0.1742F, 0.5236F, 0F));

        PartDefinition Hair01 = Head.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(186, 0).addBox(-7F, 0F, -12F, 14F, 9F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9.5F, 9.5F, 0.1257F, 0F, 0F));

        PartDefinition HairU01 = Head.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(189, 19).addBox(-8F, -14.7F, 0F, 16F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -0.2F, -7.2F));

        PartDefinition Cap2 = Neck.addOrReplaceChild("Cap2", CubeListBuilder.create().texOffs(206, 42).addBox(-8F, -15F, 0F, 16F, 15F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -2F, -3F, -1.4F, 0F, 0F));

        PartDefinition TailBase = BodyMain.addOrReplaceChild("TailBase", CubeListBuilder.create().texOffs(208, 103).addBox(-6F, -6F, 0F, 12F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.5F, 0F, -0.2618F, 0F, 0F));

        PartDefinition Tail1 = TailBase.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(208, 103).addBox(-6F, -6F, 0F, 12F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 9F, 0.2618F, 0F, 0F));

        PartDefinition Tail2 = Tail1.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(208, 103).addBox(-6F, -6F, 0F, 12F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 9F, 0.5236F, 0F, 0F));

        PartDefinition Tail3 = Tail2.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(208, 103).addBox(-6F, -6F, 0F, 12F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 9F, 0.5236F, 0F, 0F));

        PartDefinition Tail4 = Tail3.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(208, 103).addBox(-6F, -6F, 0F, 12F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 9F, 0.2618F, 0F, 0F));

        PartDefinition Tail5 = Tail4.addOrReplaceChild("Tail5", CubeListBuilder.create().texOffs(208, 103).addBox(-6F, -6F, 0F, 12F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 9F, -0.5236F, 0F, 0F));

        PartDefinition Tail6 = Tail5.addOrReplaceChild("Tail6", CubeListBuilder.create().texOffs(208, 103).addBox(-5.5F, -6.5F, 0F, 11F, 13F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 9F, -0.5236F, 0F, 0F));

        PartDefinition TailHeadBase = Tail6.addOrReplaceChild("TailHeadBase", CubeListBuilder.create().texOffs(157, 96), PartPose.offset(0F, 0F, 9F));

        PartDefinition TailHeadCL1 = TailHeadBase.addOrReplaceChild("TailHeadCL1", CubeListBuilder.create().texOffs(207, 80).addBox(0F, 0F, 0F, 5F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -6F, 5F, 0.0873F, 0.1745F, 0F));

        PartDefinition TailHeadCL2 = TailHeadCL1.addOrReplaceChild("TailHeadCL2", CubeListBuilder.create().texOffs(207, 77).addBox(0F, 0F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 0.5F, 7F, 0.0873F, 0.1745F, 0F));

        PartDefinition TailHeadCL3 = TailHeadCL1.addOrReplaceChild("TailHeadCL3", CubeListBuilder.create().texOffs(207, 77).addBox(0F, 0F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 3.5F, 7F, -0.0524F, 0.1745F, 0F));

        PartDefinition TailHeadCR1 = TailHeadBase.addOrReplaceChild("TailHeadCR1", CubeListBuilder.create().texOffs(207, 80).addBox(-5F, 0F, 0F, 5F, 6F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -6F, 5F, 0.0873F, -0.1745F, 0F));

        PartDefinition TailHeadCR2 = TailHeadCR1.addOrReplaceChild("TailHeadCR2", CubeListBuilder.create().texOffs(207, 77).addBox(-2F, 0F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 0.5F, 7F, 0.0873F, -0.1745F, 0F));

        PartDefinition TailHeadCR3 = TailHeadCR1.addOrReplaceChild("TailHeadCR3", CubeListBuilder.create().texOffs(207, 77).addBox(-2F, 0F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 3.5F, 7F, -0.0524F, -0.1745F, 0F));

        PartDefinition TailHead1 = TailHeadBase.addOrReplaceChild("TailHead1", CubeListBuilder.create().texOffs(191, 70).addBox(-5.5F, 0F, -0.5F, 11F, 8F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -8.5F, 4F, 0.1396F, 0F, 0F));

        PartDefinition TailHeadC1 = TailHead1.addOrReplaceChild("TailHeadC1", CubeListBuilder.create().texOffs(201, 78).addBox(-4.5F, 0F, 0F, 9F, 5F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3.5F, 0F, 0.3491F, 0F, 0F));

        PartDefinition TailHeadC2 = TailHeadC1.addOrReplaceChild("TailHeadC2", CubeListBuilder.create().texOffs(207, 77).addBox(-1F, 0F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 1F, 8.5F, 0.1396F, 0F, 0F));

        PartDefinition TailHeadC3 = TailHeadC1.addOrReplaceChild("TailHeadC3", CubeListBuilder.create().texOffs(207, 77).addBox(-1F, 0F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.8F, 1F, 8.5F, 0.1396F, -0.0524F, 0F));

        PartDefinition TailHeadC4 = TailHeadC1.addOrReplaceChild("TailHeadC4", CubeListBuilder.create().texOffs(207, 77).addBox(-1F, 0F, 0F, 2F, 2F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.8F, 1F, 8.5F, 0.1396F, 0.0524F, 0F));

        PartDefinition TailHead3 = TailHead1.addOrReplaceChild("TailHead3", CubeListBuilder.create().texOffs(200, 80).addBox(-6F, 0F, 0F, 12F, 4F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 14.5F, 0.5236F, 0F, 0F));

        PartDefinition TailHead2 = TailHead1.addOrReplaceChild("TailHead2", CubeListBuilder.create().texOffs(182, 68).addBox(-9F, 0F, 0F, 18F, 8F, 19F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.5F, 4.5F, 0.1396F, 0F, 0F));

        PartDefinition TailJaw1 = TailHeadBase.addOrReplaceChild("TailJaw1", CubeListBuilder.create().texOffs(194, 106).addBox(-6.5F, 0F, 0F, 13F, 5F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, 5F, -0.1745F, 0F, 0F));

        PartDefinition TailJaw2 = TailJaw1.addOrReplaceChild("TailJaw2", CubeListBuilder.create().texOffs(197, 77).addBox(-5F, 0F, 0F, 10F, 5F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, 8F, -0.1745F, 0F, 0F));

        PartDefinition TailJaw3 = TailJaw1.addOrReplaceChild("TailJaw3", CubeListBuilder.create().texOffs(207, 80).addBox(-2.5F, -2.5F, 0F, 5F, 5F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 15.5F, -0.1004F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -8F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -11.5F, 0.5F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -0.5F, 0F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition GlowTailBase = GlowBodyMain.addOrReplaceChild("GlowTailBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 7.5F, 0F));

        PartDefinition GlowTail1 = GlowTailBase.addOrReplaceChild("GlowTail1", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 9F));

        PartDefinition GlowTail2 = GlowTail1.addOrReplaceChild("GlowTail2", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 9F));

        PartDefinition GlowTail3 = GlowTail2.addOrReplaceChild("GlowTail3", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 9F));

        PartDefinition GlowTail4 = GlowTail3.addOrReplaceChild("GlowTail4", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 9F));

        PartDefinition GlowTail5 = GlowTail4.addOrReplaceChild("GlowTail5", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 9F));

        PartDefinition GlowTail6 = GlowTail5.addOrReplaceChild("GlowTail6", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 0F, 9F));

        PartDefinition TailBack6 = GlowTail6.addOrReplaceChild("TailBack6", CubeListBuilder.create().texOffs(163, 70).addBox(-3.5F, 0F, 0F, 7F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7.5F, 0F, 0.1745F, 0F, 0F));

        PartDefinition GlowTailHeadBase = GlowTail6.addOrReplaceChild("GlowTailHeadBase", CubeListBuilder.create().texOffs(157, 96).addBox(-5F, -7F, 0F, 10F, 14F, 12F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 9F));

        PartDefinition GlowTailHead1 = GlowTailHeadBase.addOrReplaceChild("GlowTailHead1", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -8.5F, 4F));

        PartDefinition TailHeadT01 = GlowTailHead1.addOrReplaceChild("TailHeadT01", CubeListBuilder.create().texOffs(141, 29).addBox(-6F, 0F, 0F, 12F, 5F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.5F, 4.5F, -0.1745F, 0F, 0F));

        PartDefinition GlowTailJaw1 = GlowTailHeadBase.addOrReplaceChild("GlowTailJaw1", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, 3F, 5F));

        PartDefinition TailJawT01 = GlowTailJaw1.addOrReplaceChild("TailJawT01", CubeListBuilder.create().texOffs(143, 46).addBox(-5.5F, 0F, 0F, 11F, 5F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, 4F, 0.1745F, 0F, 0F));

        PartDefinition TailBack5 = GlowTail5.addOrReplaceChild("TailBack5", CubeListBuilder.create().texOffs(163, 70).addBox(-3.5F, 0F, 0F, 7F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 0.1745F, 0F, 0F));

        PartDefinition TailBack4 = GlowTail4.addOrReplaceChild("TailBack4", CubeListBuilder.create().texOffs(163, 70).addBox(-3.5F, 0F, 0F, 7F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 0.1745F, 0F, 0F));

        PartDefinition TailBack3 = GlowTail3.addOrReplaceChild("TailBack3", CubeListBuilder.create().texOffs(163, 70).addBox(-3.5F, 0F, 0F, 7F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 0.1745F, 0F, 0F));

        PartDefinition TailBack2 = GlowTail2.addOrReplaceChild("TailBack2", CubeListBuilder.create().texOffs(163, 70).addBox(-3.5F, 0F, 0F, 7F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 0.1745F, 0F, 0F));

        PartDefinition TailBack1 = GlowTail1.addOrReplaceChild("TailBack1", CubeListBuilder.create().texOffs(163, 70).addBox(-3.5F, 0F, 0F, 7F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 0.1745F, 0F, 0F));

        PartDefinition TailBack0 = GlowTailBase.addOrReplaceChild("TailBack0", CubeListBuilder.create().texOffs(163, 70).addBox(-3.5F, 0F, 0F, 7F, 2F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 0F, 0.1745F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.BodyMain.getAllParts().forEach(ModelPart::resetPose);
        this.GlowBodyMain.getAllParts().forEach(ModelPart::resetPose);

        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        resetPoseState();
        resetOffsets();

        applyFaceAndMouth(entity);
        setFlushVisible(entity != null && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY
                || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        applyEquipVisibility(entity);

        if (isDeadPose(entity)) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);

        applyBasePose(ctx, limbSwing, limbSwingAmount, ageInTicks, headPitch);
        applySpecialPoseAdjustments(entity, ctx, limbSwing, limbSwingAmount, ageInTicks);
        applyTailAnimation(ctx, limbSwing, limbSwingAmount, ageInTicks);

        float modf2 = ageInTicks % 128.0F;
        if (modf2 < 6.0F) {
            if (modf2 >= 3.0F) {
                modf2 -= 3.0F;
            }
            float anglef2 = Mth.sin(modf2 * 1.0472F) * 0.25F;
            Ear01.zRot = -anglef2 - 0.14F;
            Ear02.zRot = anglef2 + 0.14F;
        } else {
            Ear01.zRot = -0.14F;
            Ear02.zRot = 0.14F;
        }

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.isTailPoseOverride = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        if (ArmLeft02 != null) {
            ArmLeft02.x = armLeft02DefaultX;
            ArmLeft02.y = armLeft02DefaultY;
            ArmLeft02.z = armLeft02DefaultZ;
        }
        if (ArmRight02 != null) {
            ArmRight02.x = armRight02DefaultX;
            ArmRight02.y = armRight02DefaultY;
            ArmRight02.z = armRight02DefaultZ;
        }
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;

        boolean showHair = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipRe.EQUIP_HAIR);
        if (Hair01 != null) Hair01.visible = showHair;
        if (HairU01 != null) HairU01.visible = showHair;
        if (Cap != null) Cap.visible = !showHair;
        if (Cap2 != null) Cap2.visible = showHair;
        if (BagMain != null)
            BagMain.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipRe.EQUIP_BAG);

        boolean showEars = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipRe.EQUIP_EARS);
        if (Ear01 != null) Ear01.visible = showEars;
        if (Ear02 != null) Ear02.visible = showEars;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = -0.5236F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        BoobL.xRot = -0.73F;
        BoobR.xRot = -0.73F;
        Ahoke.yRot = 0.5236F;

        BodyMain.xRot = 1.5708F;
        BodyMain.yRot = 0.0F;
        Cloth2.xRot = -0.0524F;

        ArmLeft01.xRot = -2.9671F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = 0.0349F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = -2.9671F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -0.0349F;
        ArmRight02.zRot = 0.0F;

        BagStrap1.xRot = 0.2618F;
        BagStrap1.yRot = -0.1396F;
        BagStrap1.zRot = -0.1745F;
        BagStrap2.xRot = 0.3491F;
        BagStrap2.yRot = 0.3491F;

        LegLeft.xRot = -0.3491F;
        LegLeft.yRot = 0.0F;
        LegRight.xRot = -0.3491F;
        LegRight.yRot = 0.0F;

        TailBase.xRot = -0.4F;
        TailBase.yRot = -0.8F;
        TailBase.zRot = 0.0F;
        Tail1.xRot = -0.3F;
        Tail1.yRot = -0.35F;
        Tail2.xRot = -0.35F;
        Tail2.yRot = -0.3F;
        Tail3.xRot = -0.4F;
        Tail3.yRot = -0.2F;
        Tail4.xRot = -0.25F;
        Tail4.yRot = 0.2F;
        Tail5.xRot = 0.25F;
        Tail5.yRot = 0.2F;
        Tail6.xRot = 0.35F;
        Tail6.yRot = 0.2F;
        TailHeadBase.xRot = 0.4F;
        TailHeadBase.yRot = 0.0F;
        TailHead1.xRot = 0.2618F;
        TailJaw1.xRot = -0.7F;

        if (Hair01 != null) Hair01.visible = false;
        if (Ear01 != null) Ear01.visible = false;
        if (Ear02 != null) Ear02.visible = false;
    }

    private void applyBasePose(PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks, float headPitch) {
        float angleX = ctx.angleX;

        BoobL.xRot = -angleX * 0.06F - 0.73F;
        BoobR.xRot = -angleX * 0.06F - 0.73F;
        Ahoke.yRot = angleX * 0.25F + 0.5236F;

        Head.xRot -= 0.5236F;
        Cap2.xRot = -1.4F;

        BodyMain.xRot = 0.0873F;
        BodyMain.yRot = 0.0F;
        Cloth2.xRot = -0.0524F;

        ArmLeft01.xRot = 0.2618F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = angleX * 0.1F - 0.5236F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = 0.2618F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -angleX * 0.1F + 0.5236F;
        ArmRight02.zRot = 0.0F;

        BagStrap1.xRot = 0.2618F;
        BagStrap1.yRot = -0.1396F;
        BagStrap1.zRot = -0.1745F;
        BagStrap2.xRot = 0.3491F;
        BagStrap2.yRot = 0.3491F;

        LegLeft.yRot = 0.0F;
        LegRight.yRot = 0.0F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float addk1 = Mth.cos(limbSwing * 0.7F) * limbSwingAmount - 0.2618F;
        float addk2 = Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount - 0.2618F;

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        int tickPhase = entity != null ? entity.tickCount : 0;

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = entity != null && entity.isSprinting() || limbSwingAmount > 0.9F;

        if (isSprinting) {
            this.poseTranslateY += 0.05F;
            ArmLeft01.xRot = Mth.cos(ageInTicks * 0.8F) * 0.1F - 2.0944F;
            ArmLeft01.yRot = -0.5236F;
            ArmRight01.xRot = -Mth.cos(ageInTicks * 0.8F) * 0.1F - 2.0944F;
            ArmRight01.yRot = 0.5236F;

            Head.xRot *= 0.75F;
            Head.xRot -= 0.5236F;
            BodyMain.xRot = 0.5236F;
            BodyMain.yRot = 3.1416F;
            Cloth2.xRot = -0.7854F;

            addk1 = addk1 * 0.1F - 1.2708F;
            addk2 = addk2 * 0.1F - 1.2708F;
            LegLeft.yRot = -0.2618F;
            LegRight.yRot = 0.2618F;

            float t2 = tickPhase & 0x3FF;
            if (t2 > 700.0F) {
                applySprintingTailAnimation(ctx.angleX, limbSwing, limbSwingAmount);
            } else if (t2 > 400.0F) {
                applySprintingTailPoseStatic(ctx.angleX);
            } else {
                applySprintingTailAnimationAlt(ctx.angleX, limbSwing, limbSwingAmount);
            }
            this.isTailPoseOverride = true;
        } else if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            ArmLeft01.xRot = 0.5236F;
            ArmLeft01.zRot = -0.5236F;
            ArmRight01.xRot = 0.5236F;
            ArmRight01.zRot = 0.5236F;

            Head.xRot = -1.2217F;
            BodyMain.xRot = 1.0472F;
            Cloth2.xRot = -0.5236F;
            addk1 -= 0.95F;
            addk2 -= 0.95F;

            LegLeft.yRot = 0.0F;
            LegRight.yRot = 0.0F;

            BagStrap1.xRot = 0.15F;
            BagStrap1.yRot = -1.0472F;
            BagStrap1.zRot = 0.0F;
            BagStrap2.xRot = 0.3491F;
            BagStrap2.yRot = 1.0472F;
            BagStrap2.zRot = 0.0F;

            applyCrouchTailPose();
            this.isTailPoseOverride = true;
        } else if (isSitting) {
            this.isSittingPose = true;
            Cap2.visible = false;
            if ((tickPhase & 0x3FF) > 512) {
                if (entity != null && hasLegacyState(entity, 1, 4)) {
                    this.poseTranslateY += 0.13F;
                    Head.xRot += 0.3F;
                    BodyMain.xRot = -0.3F;
                    Cloth2.xRot = -0.3F;
                    ArmLeft01.xRot = 2.3F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = 0.2F;
                    ArmLeft02.zRot = 1.0F;
                    ArmRight01.xRot = 2.3F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.2F;
                    ArmRight02.zRot = -1.0F;

                    float parTick = ageInTicks - (int) ageInTicks + (tickPhase & 0xFF);
                    if (parTick < 30.0F) {
                        float az = Mth.sin(parTick * 0.033F * 1.5708F) * 1.6F;
                        float az1 = az * 1.6F;
                        setFace(EntityShipBase.FACE_WINK);
                        ArmLeft01.zRot = 0.2F + az;
                        ArmLeft02.zRot = 1.0F - az1;
                        if (ArmLeft02.zRot < 0.0F) ArmLeft02.zRot = 0.0F;
                        ArmRight01.zRot = -0.2F - az;
                        ArmRight02.zRot = -1.0F + az1;
                        if (ArmRight02.zRot > 0.0F) ArmRight02.zRot = 0.0F;
                    } else if (parTick < 45.0F) {
                        setFace(EntityShipBase.FACE_WINK);
                        ArmLeft01.zRot = 1.8F;
                        ArmLeft02.zRot = 0.0F;
                        ArmRight01.zRot = -1.8F;
                        ArmRight02.zRot = 0.0F;
                    } else if (parTick < 53.0F) {
                        float az = Mth.cos((parTick - 45.0F) * 0.125F * 1.5708F);
                        float az1 = az * 1.6F;
                        ArmLeft01.zRot = 0.2F + az1;
                        ArmLeft02.zRot = 1.0F - az;
                        ArmRight01.zRot = -0.2F - az1;
                        ArmRight02.zRot = -1.0F + az;
                    }

                    BagStrap1.xRot = 0.6F;
                    BagStrap1.yRot = 0.0F;
                    BagStrap1.zRot = 0.0F;
                    BagStrap2.xRot = 1.0472F;
                    BagStrap2.yRot = 1.3963F;
                    addk1 = ctx.angleX * 0.1F - 0.9F;
                    addk2 = -ctx.angleX * 0.1F - 0.9F;
                    LegLeft.yRot = -0.2F;
                    LegRight.yRot = 0.2F;
                    TailBase.xRot = -1.0F;
                    TailBase.yRot = 0.2618F;
                    TailBase.zRot = 0.0F;
                    Tail1.xRot = 0.6981F;
                    Tail1.yRot = 0.0872F;
                    Tail1.zRot = 0.0F;
                    Tail2.xRot = 0.5236F;
                    Tail2.yRot = 0.0872F;
                    Tail2.zRot = 0.1745F;
                    Tail3.xRot = 0.0F;
                    Tail3.yRot = 0.6981F;
                    Tail3.zRot = 0.0F;
                    Tail4.xRot = 0.0F;
                    Tail4.yRot = 0.6981F;
                    Tail4.zRot = 0.0F;
                    Tail5.xRot = 0.0F;
                    Tail5.yRot = 0.5236F;
                    Tail5.zRot = 0.0F;
                    Tail6.xRot = 0.0F;
                    Tail6.yRot = 0.5236F;
                    Tail6.zRot = 0.0F;
                    TailHeadBase.xRot = 0.2618F;
                    TailHeadBase.yRot = 0.5236F;
                    TailHeadBase.zRot = 0.0F;
                    TailHead1.xRot = 0.2618F;
                    TailJaw1.xRot = ctx.angleX * 0.1F - 0.2618F;

                    this.isTailPoseOverride = true;
                } else {
                    this.poseTranslateY += SITTING_TRANSLATE_Y;
                    Head.xRot *= 0.8F;
                    Head.xRot -= 1.8F;
                    Head.yRot *= 0.5F;
                    BodyMain.xRot = 1.5708F;
                    Cloth2.xRot = -0.0524F;
                    ArmLeft01.xRot = -2.9671F;
                    ArmLeft01.zRot = 0.0349F;
                    ArmLeft02.zRot = 1.3962F;
                    ArmRight01.xRot = -2.9671F;
                    ArmRight01.zRot = -0.0349F;
                    ArmRight02.zRot = -1.3962F;
                    BagStrap1.xRot = 0.2618F;
                    BagStrap1.yRot = -0.1396F;
                    BagStrap1.zRot = -0.1745F;
                    BagStrap2.xRot = 0.3491F;
                    BagStrap2.yRot = 0.3491F;
                    addk1 = -0.3491F;
                    addk2 = -0.3491F;

                    applySittingTailAnimation(ctx.angleX, ageInTicks);
                    this.isTailPoseOverride = true;
                }
            } else {
                setFace(EntityShipBase.FACE_EYES_CLOSED);
                this.poseTranslateY += 0.17F * 2;
                ArmLeft01.xRot = -1.7F;
                ArmLeft01.yRot = -0.1F;
                ArmLeft01.zRot = 0.0F;
                ArmRight01.xRot = -1.8F;
                ArmRight01.yRot = 0.1F;
                ArmRight01.zRot = 0.0F;
                Head.xRot = -1.5F;
                Head.yRot = 0.0F;
                Head.zRot = 0.7F;
                Cap2.xRot = -1.74F;
                BodyMain.xRot = 1.8F;
                Cloth2.xRot = -0.3491F;
                addk1 = -1.8F;
                addk2 = -1.8F;
                LegLeft.yRot = -0.23F;
                LegRight.yRot = 0.23F;
                BagStrap1.xRot = 0.2618F;
                BagStrap1.yRot = 0.0F;
                BagStrap1.zRot = 0.0F;
                BagStrap2.xRot = 0.3491F;
                BagStrap2.yRot = 0.3491F;
                TailBase.xRot = 1.6F;
                TailBase.yRot = 0.0F;
                TailBase.zRot = 3.1415F;
                Tail1.xRot = 0.8F;
                Tail1.yRot = 0.0F;
                Tail1.zRot = 0.0F;
                Tail2.xRot = 0.8F;
                Tail2.yRot = 0.0F;
                Tail2.zRot = 0.0F;
                Tail3.xRot = 0.9F;
                Tail3.yRot = 0.0F;
                Tail3.zRot = 0.0F;
                Tail4.xRot = 0.9F;
                Tail4.yRot = 0.0F;
                Tail4.zRot = 0.0F;
                Tail5.xRot = 0.4F;
                Tail5.yRot = 0.0F;
                Tail5.zRot = 0.0F;
                Tail6.xRot = -0.4F;
                Tail6.yRot = 0.0F;
                Tail6.zRot = 0.0F;
                TailHeadBase.xRot = -0.3F;
                TailHeadBase.yRot = 0.0F;
                TailHeadBase.zRot = 0.8F;
                TailHead1.xRot = 0.1745F;
                TailJaw1.xRot = -0.5F;

                this.isTailPoseOverride = true;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            this.poseTranslateY += 0.13F;
            ArmLeft01.xRot = 0.5236F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft01.zRot = -0.5236F;
            ArmRight01.xRot = -2.7925F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.2618F;
            Head.xRot = -1.2217F;
            Head.yRot = 0.0F;
            BodyMain.xRot = 1.0472F;
            Cloth2.xRot = -0.5236F;
            addk1 -= 1.48F;
            addk2 -= 0.26F;
            BagStrap1.xRot = 0.15F;
            BagStrap1.yRot = -1.0472F;
            BagStrap1.zRot = 0.0F;
            BagStrap2.xRot = 0.3491F;
            BagStrap2.yRot = 0.3491F;
            TailBase.xRot = 0.6F;
            TailBase.yRot = 0.0F;
            TailBase.zRot = 3.1416F;
            Tail1.xRot = -0.2618F;
            Tail1.yRot = 0.0F;
            Tail1.zRot = 0.0F;
            Tail2.xRot = -0.5236F;
            Tail2.yRot = 0.0F;
            Tail2.zRot = 0.0F;
            Tail3.xRot = -0.2618F;
            Tail3.yRot = 0.0F;
            Tail3.zRot = 0.0F;
            Tail4.xRot = -0.2618F;
            Tail4.yRot = 0.0F;
            Tail4.zRot = 0.0F;
            Tail5.xRot = -0.5236F;
            Tail5.yRot = 0.0F;
            Tail5.zRot = 0.0F;
            Tail6.xRot = -0.5236F;
            Tail6.yRot = 0.0F;
            Tail6.zRot = 0.0F;
            TailHeadBase.xRot = -0.2618F;
            TailHeadBase.yRot = 0.0F;
            TailHeadBase.zRot = 0.0F;
            if (entity.getAttackTick() > 47) {
                TailHead1.xRot = (50 - entity.getAttackTick()) * 0.15F + 0.4F;
                TailJaw1.xRot = (entity.getAttackTick() - 50) * 0.15F - 0.4F;
            } else if (entity.getAttackTick() > 39) {
                TailHead1.xRot = 0.76F - (46 - entity.getAttackTick()) * 0.06F;
                TailJaw1.xRot = -0.76F + (46 - entity.getAttackTick()) * 0.06F;
            } else {
                TailHead1.xRot = 0.4F;
                TailJaw1.xRot = -0.4F;
            }

            this.isTailPoseOverride = true;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot = -0.6F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = 0.2F;
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        LegLeft.xRot = addk1;
        LegRight.xRot = addk2;
    }

    private void applyTailAnimation(PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        if (this.isTailPoseOverride) {
            return;
        }

        applyIdleTailAnimation(ctx.angleX, ageInTicks);
    }

    private void applyIdleTailAnimation(float angleX, float ageInTicks) {
        TailBase.xRot = -0.5236F;
        TailBase.yRot = Mth.cos(-ageInTicks * 0.1F) * 0.1F;
        TailBase.zRot = 0.0F;
        Tail1.xRot = 0.5236F;
        Tail1.yRot = Mth.cos(-ageInTicks * 0.1F + 0.7F) * 0.1F;
        Tail1.zRot = 0.0F;
        Tail2.xRot = 0.5236F;
        Tail2.yRot = Mth.cos(-ageInTicks * 0.1F + 1.4F) * 0.15F;
        Tail2.zRot = 0.0F;
        Tail3.xRot = 0.5236F;
        Tail3.yRot = Mth.cos(-ageInTicks * 0.1F + 2.1F) * 0.2F;
        Tail3.zRot = 0.0F;
        Tail4.xRot = 0.5236F;
        Tail4.yRot = Mth.cos(-ageInTicks * 0.1F + 2.8F) * 0.25F;
        Tail4.zRot = 0.0F;
        Tail5.xRot = -0.5236F;
        Tail5.yRot = Mth.cos(-ageInTicks * 0.1F + 3.5F) * 0.3F;
        Tail5.zRot = 0.0F;
        Tail6.xRot = -0.5236F;
        Tail6.yRot = Mth.cos(-ageInTicks * 0.1F + 4.2F) * 0.35F;
        Tail6.zRot = 0.0F;
        TailHeadBase.xRot = -0.5236F;
        TailHeadBase.yRot = Mth.cos(-ageInTicks * 0.1F + 4.9F) * 0.4F;
        TailHeadBase.zRot = 0.0F;
        TailHead1.xRot = 0.1745F;
        TailJaw1.xRot = angleX * 0.1F - 0.15F;
    }

    private void applySittingTailAnimation(float angleX, float ageInTicks) {
        TailBase.xRot = -0.7F;
        TailBase.yRot = Mth.cos(-ageInTicks * 0.1F) * 0.1F;
        TailBase.zRot = Mth.cos(-ageInTicks * 0.1F) * 0.05F;
        Tail1.xRot = 0.35F;
        Tail1.yRot = Mth.cos(-ageInTicks * 0.1F + 0.7F) * 0.2F;
        Tail1.zRot = -Mth.cos(-ageInTicks * 0.1F + 0.7F) * 0.05F;
        Tail2.xRot = 0.35F;
        Tail2.yRot = Mth.cos(-ageInTicks * 0.1F + 1.4F) * 0.3F;
        Tail2.zRot = -Mth.cos(-ageInTicks * 0.1F + 1.4F) * 0.05F;
        Tail3.xRot = 0.35F;
        Tail3.yRot = Mth.cos(-ageInTicks * 0.1F + 2.1F) * 0.4F;
        Tail3.zRot = -Mth.cos(-ageInTicks * 0.1F + 2.1F) * 0.05F;
        Tail4.xRot = -0.2618F;
        Tail4.yRot = Mth.cos(-ageInTicks * 0.1F + 2.8F) * 0.5F;
        Tail4.zRot = Mth.cos(-ageInTicks * 0.1F + 2.8F) * 0.025F;
        Tail5.xRot = -0.35F;
        Tail5.yRot = Mth.cos(-ageInTicks * 0.1F + 3.5F) * 0.55F;
        Tail5.zRot = Mth.cos(-ageInTicks * 0.1F + 3.5F) * 0.05F;
        Tail6.xRot = -0.35F;
        Tail6.yRot = Mth.cos(-ageInTicks * 0.1F + 4.2F) * 0.6F;
        Tail6.zRot = Mth.cos(-ageInTicks * 0.1F + 4.2F) * 0.05F;
        TailHeadBase.xRot = -0.15F;
        TailHeadBase.yRot = Mth.cos(-ageInTicks * 0.1F + 4.9F) * 0.65F;
        TailHeadBase.zRot = Mth.cos(-ageInTicks * 0.1F + 4.9F) * 0.025F;
        TailHead1.xRot = 0.2618F;
        TailJaw1.xRot = angleX * 0.1F - 0.15F;
    }

    private void applySprintingTailAnimation(float angleX, float limbSwing, float limbSwingAmount) {
        TailBase.xRot = -1.3F;
        TailBase.yRot = -Mth.cos(limbSwing * 0.25F - 5.0F) * 0.2F * limbSwingAmount;
        TailBase.zRot = Mth.cos(limbSwing * 0.25F - 5.0F) * 0.4F * limbSwingAmount;
        Tail1.xRot = 0.2618F;
        Tail1.yRot = -Mth.cos(limbSwing * 0.25F - 4.2F) * 0.3F * limbSwingAmount;
        Tail1.zRot = -Mth.cos(limbSwing * 0.25F - 4.2F) * 0.1F * limbSwingAmount;
        Tail2.xRot = 0.2618F;
        Tail2.yRot = -Mth.cos(limbSwing * 0.25F - 3.5F) * 0.4F * limbSwingAmount;
        Tail2.zRot = -Mth.cos(limbSwing * 0.25F - 3.5F) * 0.1F * limbSwingAmount;
        Tail3.xRot = 0.1745F;
        Tail3.yRot = -Mth.cos(limbSwing * 0.25F - 2.8F) * 0.5F * limbSwingAmount;
        Tail3.zRot = 0.0F;
        Tail4.xRot = 0.1745F;
        Tail4.yRot = -Mth.cos(limbSwing * 0.25F - 2.1F) * 0.5F * limbSwingAmount;
        Tail4.zRot = 0.0F;
        Tail5.xRot = 0.0873F;
        Tail5.yRot = -Mth.cos(limbSwing * 0.25F - 1.4F) * 0.4F * limbSwingAmount;
        Tail5.zRot = 0.0F;
        Tail6.xRot = 0.0873F;
        Tail6.yRot = -Mth.cos(limbSwing * 0.25F - 0.7F) * 0.3F * limbSwingAmount;
        Tail6.zRot = 0.0F;
        TailHeadBase.xRot = -0.0873F;
        TailHeadBase.yRot = -Mth.cos(limbSwing * 0.25F) * 0.2F * limbSwingAmount;
        TailHeadBase.zRot = 0.0F;
        TailHead1.xRot = 0.3F;
        TailJaw1.xRot = angleX * 0.2F - 0.3F;
    }

    private void applySprintingTailPoseStatic(float angleX) {
        TailBase.xRot = 1.0472F;
        TailBase.yRot = 0.0F;
        TailBase.zRot = 3.1415F;
        Tail1.xRot = 0.7854F;
        Tail1.yRot = 0.0F;
        Tail1.zRot = 0.0F;
        Tail2.xRot = 0.7854F;
        Tail2.yRot = 0.0F;
        Tail2.zRot = 0.0F;
        Tail3.xRot = 0.7854F;
        Tail3.yRot = 0.0F;
        Tail3.zRot = 0.0F;
        Tail4.xRot = 0.7854F;
        Tail4.yRot = 0.0F;
        Tail4.zRot = 0.0F;
        Tail5.xRot = 0.5236F;
        Tail5.yRot = 0.0F;
        Tail5.zRot = 0.0F;
        Tail6.xRot = -0.2618F;
        Tail6.yRot = 0.0F;
        Tail6.zRot = 0.0F;
        TailHeadBase.xRot = 0.0F;
        TailHeadBase.yRot = 0.0F;
        TailHeadBase.zRot = 0.0F;
        TailHead1.xRot = 0.1745F;
        TailJaw1.xRot = angleX * 0.15F - 0.3F;
    }

    private void applySprintingTailAnimationAlt(float angleX, float limbSwing, float limbSwingAmount) {
        float swing = -limbSwing * 0.3F;
        TailBase.xRot = -0.7F;
        TailBase.yRot = -Mth.cos(swing) * 0.2F * limbSwingAmount;
        TailBase.zRot = Mth.cos(swing) * 0.3F * limbSwingAmount;
        Tail1.xRot = 0.2618F;
        Tail1.yRot = -Mth.cos(swing + 0.7F) * 0.2F * limbSwingAmount;
        Tail1.zRot = -Mth.cos(swing + 0.7F) * 0.1F * limbSwingAmount;
        Tail2.xRot = 0.2618F;
        Tail2.yRot = -Mth.cos(swing + 1.4F) * 0.3F * limbSwingAmount;
        Tail2.zRot = -Mth.cos(swing + 1.4F) * 0.1F * limbSwingAmount;
        Tail3.xRot = -0.2618F;
        Tail3.yRot = -Mth.cos(swing + 2.2F) * 0.3F * limbSwingAmount;
        Tail3.zRot = Mth.cos(swing + 2.2F) * 0.1F * limbSwingAmount;
        Tail4.xRot = -0.2618F;
        Tail4.yRot = -Mth.cos(swing + 2.8F) * 0.4F * limbSwingAmount;
        Tail4.zRot = Mth.cos(swing + 2.8F) * 0.1F * limbSwingAmount;
        Tail5.xRot = -0.2618F;
        Tail5.yRot = -Mth.cos(swing + 3.5F) * 0.4F * limbSwingAmount;
        Tail5.zRot = Mth.cos(swing + 3.5F) * 0.1F * limbSwingAmount;
        Tail6.xRot = -0.2618F;
        Tail6.yRot = -Mth.cos(swing + 4.2F) * 0.5F * limbSwingAmount;
        Tail6.zRot = Mth.cos(swing + 4.2F) * 0.1F * limbSwingAmount;
        TailHeadBase.xRot = 0.2618F;
        TailHeadBase.yRot = -Mth.cos(swing + 4.9F) * 0.6F * limbSwingAmount;
        TailHeadBase.zRot = -Mth.cos(swing + 4.9F) * 0.1F * limbSwingAmount;
        TailHead1.xRot = 0.1745F;
        TailJaw1.xRot = angleX * 0.15F - 0.3F;
    }

    private void applyCrouchTailPose() {
        TailBase.xRot = 0.7F;
        TailBase.yRot = 0.0F;
        TailBase.zRot = 3.1416F;
        Tail1.xRot = -0.2618F;
        Tail1.yRot = 0.0F;
        Tail1.zRot = 0.0F;
        Tail2.xRot = -0.5236F;
        Tail2.yRot = 0.0F;
        Tail2.zRot = 0.0F;
        Tail3.xRot = -0.2618F;
        Tail3.yRot = 0.0F;
        Tail3.zRot = 0.0F;
        Tail4.xRot = -0.2618F;
        Tail4.yRot = 0.0F;
        Tail4.zRot = 0.0F;
        Tail5.xRot = -0.5236F;
        Tail5.yRot = 0.0F;
        Tail5.zRot = 0.0F;
        Tail6.xRot = -0.5236F;
        Tail6.yRot = 0.0F;
        Tail6.zRot = 0.0F;
        TailHeadBase.xRot = -0.2618F;
        TailHeadBase.yRot = 0.0F;
        TailHeadBase.zRot = 0.0F;
        TailHead1.xRot = 0.1745F;
        TailJaw1.xRot = -0.2F;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
            GlowNeck.copyFrom(Neck);
            GlowHead.copyFrom(Head);

            GlowTailBase.copyFrom(TailBase);
            GlowTail1.copyFrom(Tail1);
            GlowTail2.copyFrom(Tail2);
            GlowTail3.copyFrom(Tail3);
            GlowTail4.copyFrom(Tail4);
            GlowTail5.copyFrom(Tail5);
            GlowTail6.copyFrom(Tail6);

            GlowTailHeadBase.copyFrom(TailHeadBase);
            GlowTailHead1.copyFrom(TailHead1);
            GlowTailJaw1.copyFrom(TailJaw1);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        if (GlowBodyMain == null) return;
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
