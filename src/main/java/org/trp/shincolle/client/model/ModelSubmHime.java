package org.trp.shincolle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

import org.trp.shincolle.client.model.IGlowableModel;

public class ModelSubmHime<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "subm_hime"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelSubmHime");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelSubmHime");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelSubmHime");
    private static final float RIDING_TRANSLATE_Y = LegacyPoseOffsets.ridingY("ModelSubmHime");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Butt;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft01;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart EquipBack;
    private final ModelPart Head;
    private final ModelPart Collar01;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke01;
    private final ModelPart Ahoke01a;
    private final ModelPart HairU01;
    private final ModelPart HairR01;
    private final ModelPart HairL01;
    private final ModelPart HairR02;
    private final ModelPart HairL02;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart Ahoke02;
    private final ModelPart Ahoke03;
    private final ModelPart Ahoke04;
    private final ModelPart Ahoke05;
    private final ModelPart Ahoke06;
    private final ModelPart Ahoke02a;
    private final ModelPart Ahoke03a;
    private final ModelPart Ahoke04a;
    private final ModelPart Ahoke05a;
    private final ModelPart Ahoke06a;
    private final ModelPart Collar02;
    private final ModelPart Collar03;
    private final ModelPart Collar04;
    private final ModelPart Collar05;
    private final ModelPart Collar05a;
    private final ModelPart Collar05b;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft02;
    private final ModelPart Skirt02;
    private final ModelPart LegRight02;
    private final ModelPart ArmRight02;
    private final ModelPart ArmLeft02;
    private final ModelPart EquipTube00;
    private final ModelPart EquipTube00_1;
    private final ModelPart EquipTube01;
    private final ModelPart EquipTube01a;
    private final ModelPart EquipTube02;
    private final ModelPart EquipTube02a;
    private final ModelPart EquipTube03;
    private final ModelPart EquipTube03a;
    private final ModelPart EquipTube04;
    private final ModelPart EquipTube04a;
    private final ModelPart EquipTube05;
    private final ModelPart EquipTube05a;
    private final ModelPart EquipTBase;
    private final ModelPart EquipT01;
    private final ModelPart EquipT02;
    private final ModelPart EquipT03;
    private final ModelPart EquipT04;
    private final ModelPart EquipT05;
    private final ModelPart EquipT06;
    private final ModelPart EquipT07;
    private final ModelPart EquipT02a;
    private final ModelPart EquipT02b;
    private final ModelPart EquipT02c;
    private final ModelPart EquipT02d;
    private final ModelPart EquipTJaw01;
    private final ModelPart EquipTJaw02;
    private final ModelPart EquipTEyeA;
    private final ModelPart EquipTEyeB;
    private final ModelPart EquipTube01_1;
    private final ModelPart EquipTube01a_1;
    private final ModelPart EquipTube02_1;
    private final ModelPart EquipTube02a_1;
    private final ModelPart EquipTube03_1;
    private final ModelPart EquipTube03a_1;
    private final ModelPart EquipTube04_1;
    private final ModelPart EquipTube04a_1;
    private final ModelPart EquipTube05_1;
    private final ModelPart EquipTube05a_1;
    private final ModelPart EquipTBase_1;
    private final ModelPart EquipT01_1;
    private final ModelPart EquipT03_1;
    private final ModelPart EquipT05_1;
    private final ModelPart EquipT06_1;
    private final ModelPart EquipT07_1;
    private final ModelPart EquipT02_1;
    private final ModelPart EquipT04_1;
    private final ModelPart EquipT02a_1;
    private final ModelPart EquipT02b_1;
    private final ModelPart EquipT02c_1;
    private final ModelPart EquipT02d_1;
    private final ModelPart EquipTJaw01_1;
    private final ModelPart EquipTJaw02_1;
    private final ModelPart EquipTEyeA_1;
    private final ModelPart EquipTEyeB_1;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final ModelPart GlowEquipBase;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float skirt01DefaultY;
    private final float skirt01DefaultZ;
    private final float skirt02DefaultY;
    private final float skirt02DefaultZ;
    private final float legLeft02DefaultX;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultX;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultZ;
    private final float boobLDefaultX;
    private final float boobRDefaultX;
    private final float equipTBaseDefaultY;
    private final float equipTBase1DefaultY;


    public ModelSubmHime(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.Butt = this.BodyMain.getChild("Butt");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Collar01 = this.Neck.getChild("Collar01");
        this.Collar02 = this.Collar01.getChild("Collar02");
        this.Collar03 = this.Collar02.getChild("Collar03");
        this.Collar04 = this.Collar03.getChild("Collar04");
        this.Collar05 = this.Collar04.getChild("Collar05");
        this.Collar05a = this.Collar05.getChild("Collar05a");
        this.Collar05b = this.Collar05.getChild("Collar05b");
        this.Head = this.Neck.getChild("Head");
        this.Ahoke01a = this.Head.getChild("Ahoke01a");
        this.Ahoke02a = this.Ahoke01a.getChild("Ahoke02a");
        this.Ahoke03a = this.Ahoke02a.getChild("Ahoke03a");
        this.Ahoke04a = this.Ahoke03a.getChild("Ahoke04a");
        this.Ahoke05a = this.Ahoke04a.getChild("Ahoke05a");
        this.Ahoke06a = this.Ahoke05a.getChild("Ahoke06a");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair = this.Head.getChild("Hair");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Ahoke01 = this.Head.getChild("Ahoke01");
        this.Ahoke02 = this.Ahoke01.getChild("Ahoke02");
        this.Ahoke03 = this.Ahoke02.getChild("Ahoke03");
        this.Ahoke04 = this.Ahoke03.getChild("Ahoke04");
        this.Ahoke05 = this.Ahoke04.getChild("Ahoke05");
        this.Ahoke06 = this.Ahoke05.getChild("Ahoke06");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.EquipBack = this.GlowBodyMain.getChild("EquipBack");
        this.initFaceParts(this.GlowHead);
        this.GlowEquipBase = this.GlowBodyMain.getChild("GlowEquipBase");
        this.EquipTube00 = this.GlowEquipBase.getChild("EquipTube00");
        this.EquipTube01 = this.EquipTube00.getChild("EquipTube01");
        this.EquipTube02 = this.EquipTube01.getChild("EquipTube02");
        this.EquipTube03 = this.EquipTube02.getChild("EquipTube03");
        this.EquipTube04 = this.EquipTube03.getChild("EquipTube04");
        this.EquipTube05 = this.EquipTube04.getChild("EquipTube05");
        this.EquipTube05a = this.EquipTube05.getChild("EquipTube05a");
        this.EquipTBase = this.EquipTube05a.getChild("EquipTBase");
        this.EquipT01 = this.EquipTBase.getChild("EquipT01");
        this.EquipT07 = this.EquipTBase.getChild("EquipT07");
        this.EquipT02 = this.EquipTBase.getChild("EquipT02");
        this.EquipT02c = this.EquipT02.getChild("EquipT02c");
        this.EquipT02a = this.EquipT02.getChild("EquipT02a");
        this.EquipT02d = this.EquipT02.getChild("EquipT02d");
        this.EquipT02b = this.EquipT02.getChild("EquipT02b");
        this.EquipT04 = this.EquipTBase.getChild("EquipT04");
        this.EquipTEyeB = this.EquipT04.getChild("EquipTEyeB");
        this.EquipTJaw01 = this.EquipT04.getChild("EquipTJaw01");
        this.EquipTJaw02 = this.EquipT04.getChild("EquipTJaw02");
        this.EquipTEyeA = this.EquipT04.getChild("EquipTEyeA");
        this.EquipT06 = this.EquipTBase.getChild("EquipT06");
        this.EquipT03 = this.EquipTBase.getChild("EquipT03");
        this.EquipT05 = this.EquipTBase.getChild("EquipT05");
        this.EquipTube04a = this.EquipTube04.getChild("EquipTube04a");
        this.EquipTube03a = this.EquipTube03.getChild("EquipTube03a");
        this.EquipTube02a = this.EquipTube02.getChild("EquipTube02a");
        this.EquipTube01a = this.EquipTube01.getChild("EquipTube01a");
        this.EquipTube00_1 = this.GlowEquipBase.getChild("EquipTube00_1");
        this.EquipTube01_1 = this.EquipTube00_1.getChild("EquipTube01_1");
        this.EquipTube01a_1 = this.EquipTube01_1.getChild("EquipTube01a_1");
        this.EquipTube02_1 = this.EquipTube01_1.getChild("EquipTube02_1");
        this.EquipTube03_1 = this.EquipTube02_1.getChild("EquipTube03_1");
        this.EquipTube03a_1 = this.EquipTube03_1.getChild("EquipTube03a_1");
        this.EquipTube04_1 = this.EquipTube03_1.getChild("EquipTube04_1");
        this.EquipTube04a_1 = this.EquipTube04_1.getChild("EquipTube04a_1");
        this.EquipTube05_1 = this.EquipTube04_1.getChild("EquipTube05_1");
        this.EquipTube05a_1 = this.EquipTube05_1.getChild("EquipTube05a_1");
        this.EquipTBase_1 = this.EquipTube05a_1.getChild("EquipTBase_1");
        this.EquipT05_1 = this.EquipTBase_1.getChild("EquipT05_1");
        this.EquipT04_1 = this.EquipTBase_1.getChild("EquipT04_1");
        this.EquipTJaw01_1 = this.EquipT04_1.getChild("EquipTJaw01_1");
        this.EquipTJaw02_1 = this.EquipT04_1.getChild("EquipTJaw02_1");
        this.EquipTEyeB_1 = this.EquipT04_1.getChild("EquipTEyeB_1");
        this.EquipTEyeA_1 = this.EquipT04_1.getChild("EquipTEyeA_1");
        this.EquipT07_1 = this.EquipTBase_1.getChild("EquipT07_1");
        this.EquipT01_1 = this.EquipTBase_1.getChild("EquipT01_1");
        this.EquipT02_1 = this.EquipTBase_1.getChild("EquipT02_1");
        this.EquipT02a_1 = this.EquipT02_1.getChild("EquipT02a_1");
        this.EquipT02b_1 = this.EquipT02_1.getChild("EquipT02b_1");
        this.EquipT02c_1 = this.EquipT02_1.getChild("EquipT02c_1");
        this.EquipT02d_1 = this.EquipT02_1.getChild("EquipT02d_1");
        this.EquipT03_1 = this.EquipTBase_1.getChild("EquipT03_1");
        this.EquipT06_1 = this.EquipTBase_1.getChild("EquipT06_1");
        this.EquipTube02a_1 = this.EquipTube02_1.getChild("EquipTube02a_1");
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.skirt01DefaultY = this.Skirt01.y;
        this.skirt01DefaultZ = this.Skirt01.z;
        this.skirt02DefaultY = this.Skirt02.y;
        this.skirt02DefaultZ = this.Skirt02.z;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultZ = this.ArmRight02.z;
        this.boobLDefaultX = this.BoobL.x;
        this.boobRDefaultX = this.BoobR.x;
        this.equipTBaseDefaultY = this.EquipTBase.y;
        this.equipTBase1DefaultY = this.EquipTBase_1.y;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 36).addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -8.5F, -3.8F, -0.8727F, 0.0873F, 0.0698F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 71).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0F, 0F, 0.2618F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 54).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 88).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(128, 0).addBox(-8.5F, 0F, -8.5F, 17F, 5F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, 1.5F, -0.0873F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(128, 17).addBox(-10.5F, 0F, -6.5F, 21F, 5F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, -2.7F, -0.0873F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 68).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.192F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 68).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2967F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 47).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 71).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.2094F, 0F, -0.2618F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 54).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(24, 63).addBox(-2.5F, -3F, -2.9F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9.6F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Collar01 = Neck.addOrReplaceChild("Collar01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -2F, -4F, 12F, 3F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -1.1F, -1.2F));

        PartDefinition Collar02 = Collar01.addOrReplaceChild("Collar02", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.6F, -3.2F, -0.6981F, 0F, 0F));

        PartDefinition Collar03 = Collar02.addOrReplaceChild("Collar03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, 0F, 0.2618F, 0F, 0F));

        PartDefinition Collar04 = Collar03.addOrReplaceChild("Collar04", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -1F, 1F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.5F, 0F, 0.4554F, 0F, 0F));

        PartDefinition Collar05 = Collar04.addOrReplaceChild("Collar05", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -1F, 5F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, -0.2F));

        PartDefinition Collar05a = Collar05.addOrReplaceChild("Collar05a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -2F, -0.5F, 3F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, 2F, 0F, 0F, -0.0873F, -0.3491F));

        PartDefinition Collar05b = Collar05.addOrReplaceChild("Collar05b", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -2F, -0.5F, 3F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 2F, 0F, 0F, 0.0873F, 0.3491F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition Ahoke01a = Head.addOrReplaceChild("Ahoke01a", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, -1.5F, -2.2689F, -2.618F, 0F));

        PartDefinition Ahoke02a = Ahoke01a.addOrReplaceChild("Ahoke02a", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0.7854F, -0.0524F, 0F));

        PartDefinition Ahoke03a = Ahoke02a.addOrReplaceChild("Ahoke03a", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 1.0472F, 0.0524F, 0F));

        PartDefinition Ahoke04a = Ahoke03a.addOrReplaceChild("Ahoke04a", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0.4887F, 0.0524F, 0F));

        PartDefinition Ahoke05a = Ahoke04a.addOrReplaceChild("Ahoke05a", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, -0.2618F, 0.0873F, 0F));

        PartDefinition Ahoke06a = Ahoke05a.addOrReplaceChild("Ahoke06a", CubeListBuilder.create().texOffs(42, 89).mirror().addBox(-2F, 0F, 0F, 4F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, -0.5236F, 0.0873F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(80, 0).addBox(-7.5F, 0F, 0F, 15F, 17F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2618F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(72, 29).addBox(-8F, 0F, -5F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 5.5F, -0.0873F, 0F, 0F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(26, 32).addBox(-8F, 0F, -4.5F, 16F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12.5F, -0.1F, -0.0524F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 3F, -5.5F, -0.192F, 0.1745F, 0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 10F, 0F, 0.1745F, 0F, -0.0524F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(0, 18).addBox(-1F, 0F, 0F, 2F, 11F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 3F, -5.5F, -0.192F, -0.1745F, -0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(0, 18).addBox(-1F, 0F, 0F, 2F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 0F, 0.1745F, 0F, 0.0873F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition Ahoke01 = Head.addOrReplaceChild("Ahoke01", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -15F, 0F, -2.0071F, 0.5236F, 0F));

        PartDefinition Ahoke02 = Ahoke01.addOrReplaceChild("Ahoke02", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, 1.0472F, -0.0524F, 0F));

        PartDefinition Ahoke03 = Ahoke02.addOrReplaceChild("Ahoke03", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, 0.7854F, 0.0524F, 0F));

        PartDefinition Ahoke04 = Ahoke03.addOrReplaceChild("Ahoke04", CubeListBuilder.create().texOffs(50, 77).addBox(-2F, 0F, 0F, 4F, 8F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0.4363F, 0.0524F, 0F));

        PartDefinition Ahoke05 = Ahoke04.addOrReplaceChild("Ahoke05", CubeListBuilder.create().texOffs(50, 79).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.9F, 0F, -0.1745F, 0.0873F, 0F));

        PartDefinition Ahoke06 = Ahoke05.addOrReplaceChild("Ahoke06", CubeListBuilder.create().texOffs(42, 90).addBox(-2F, 0F, 0F, 4F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, -0.4363F, 0.0873F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-3F, 0F, 0F, 6F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -8.5F, -3.7F, -0.8727F, -0.0873F, -0.0698F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 104), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(24, 63), PartPose.offsetAndRotation(0F, -9.6F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(44, 101), PartPose.offset(0F, -1F, -0.7F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        PartDefinition EquipBack = GlowBodyMain.addOrReplaceChild("EquipBack", CubeListBuilder.create().texOffs(17, 31).addBox(-2F, 0F, -2F, 4F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.7F, 4.4F, -0.7854F, 0F, 0F));

        PartDefinition GlowEquipBase = GlowBodyMain.addOrReplaceChild("GlowEquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 8F, 3F));

        PartDefinition EquipTube00 = GlowEquipBase.addOrReplaceChild("EquipTube00", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 3F, 1.4F, 0.2618F, 0.61F, 0F));

        PartDefinition EquipTube01 = EquipTube00.addOrReplaceChild("EquipTube01", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.5F, 0F, 0.3491F, 0F, 0F));

        PartDefinition EquipTube02 = EquipTube01.addOrReplaceChild("EquipTube02", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -1F, 0.5236F, 0F, 0F));

        PartDefinition EquipTube03 = EquipTube02.addOrReplaceChild("EquipTube03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.6109F, 0F, 0F));

        PartDefinition EquipTube04 = EquipTube03.addOrReplaceChild("EquipTube04", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.6981F, 0F, 0F));

        PartDefinition EquipTube05 = EquipTube04.addOrReplaceChild("EquipTube05", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.6109F, 0F, 0F));

        PartDefinition EquipTube05a = EquipTube05.addOrReplaceChild("EquipTube05a", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTBase = EquipTube05a.addOrReplaceChild("EquipTBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 26F, 1F, 0F, 0.61F, 0F));

        PartDefinition EquipT01 = EquipTBase.addOrReplaceChild("EquipT01", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 3F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -19F, 0F));

        PartDefinition EquipT07 = EquipTBase.addOrReplaceChild("EquipT07", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 1F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 24.7F, 0F));

        PartDefinition EquipT02 = EquipTBase.addOrReplaceChild("EquipT02", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -16F, 0F));

        PartDefinition EquipT02c = EquipT02.addOrReplaceChild("EquipT02c", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 5.9F, 0F, 1.5708F, 0F));

        PartDefinition EquipT02a = EquipT02.addOrReplaceChild("EquipT02a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offset(2.9F, 0.5F, 0F));

        PartDefinition EquipT02d = EquipT02.addOrReplaceChild("EquipT02d", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -5.9F, 0F, -1.5708F, 0F));

        PartDefinition EquipT02b = EquipT02.addOrReplaceChild("EquipT02b", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offset(-2.9F, 0.5F, 0F));

        PartDefinition EquipT04 = EquipTBase.addOrReplaceChild("EquipT04", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 0F));

        PartDefinition EquipTEyeB = EquipT04.addOrReplaceChild("EquipTEyeB", CubeListBuilder.create().texOffs(0, 14).addBox(0F, 0F, 0F, 0F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, 10.9F, 3F, -2.0944F, 0F, 0F));

        PartDefinition EquipTJaw01 = EquipT04.addOrReplaceChild("EquipTJaw01", CubeListBuilder.create().texOffs(59, 25).addBox(-3.5F, 0F, 0F, 7F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6.3F, 0.5F, 0.2618F, 0F, 0F));

        PartDefinition EquipTJaw02 = EquipT04.addOrReplaceChild("EquipTJaw02", CubeListBuilder.create().texOffs(59, 25).mirror().addBox(-3.5F, 0F, -2.5F, 7F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 4.6F, 1F));

        PartDefinition EquipTEyeA = EquipT04.addOrReplaceChild("EquipTEyeA", CubeListBuilder.create().texOffs(0, 14).addBox(0F, 0F, 0F, 0F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, 10.9F, 3F, -2.0944F, 0F, 0F));

        PartDefinition EquipT06 = EquipTBase.addOrReplaceChild("EquipT06", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -2.5F, 5F, 1F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 24F, 0F));

        PartDefinition EquipT03 = EquipTBase.addOrReplaceChild("EquipT03", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, 0F));

        PartDefinition EquipT05 = EquipTBase.addOrReplaceChild("EquipT05", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, 0F));

        PartDefinition EquipTube04a = EquipTube04.addOrReplaceChild("EquipTube04a", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTube03a = EquipTube03.addOrReplaceChild("EquipTube03a", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTube02a = EquipTube02.addOrReplaceChild("EquipTube02a", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTube01a = EquipTube01.addOrReplaceChild("EquipTube01a", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, -1F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTube00_1 = GlowEquipBase.addOrReplaceChild("EquipTube00_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 3F, 1.4F, 0.2618F, -0.61F, 0F));

        PartDefinition EquipTube01_1 = EquipTube00_1.addOrReplaceChild("EquipTube01_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.5F, 0F, 0.3491F, 0F, 0F));

        PartDefinition EquipTube01a_1 = EquipTube01_1.addOrReplaceChild("EquipTube01a_1", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, -1F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTube02_1 = EquipTube01_1.addOrReplaceChild("EquipTube02_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, -1F, 0.5236F, 0F, 0F));

        PartDefinition EquipTube03_1 = EquipTube02_1.addOrReplaceChild("EquipTube03_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.6109F, 0F, 0F));

        PartDefinition EquipTube03a_1 = EquipTube03_1.addOrReplaceChild("EquipTube03a_1", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTube04_1 = EquipTube03_1.addOrReplaceChild("EquipTube04_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.6981F, 0F, 0F));

        PartDefinition EquipTube04a_1 = EquipTube04_1.addOrReplaceChild("EquipTube04a_1", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTube05_1 = EquipTube04_1.addOrReplaceChild("EquipTube05_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 0.6109F, 0F, 0F));

        PartDefinition EquipTube05a_1 = EquipTube05_1.addOrReplaceChild("EquipTube05a_1", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition EquipTBase_1 = EquipTube05a_1.addOrReplaceChild("EquipTBase_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 26F, 1F, 0F, -0.61F, 0F));

        PartDefinition EquipT05_1 = EquipTBase_1.addOrReplaceChild("EquipT05_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, 0F));

        PartDefinition EquipT04_1 = EquipTBase_1.addOrReplaceChild("EquipT04_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 4F, 0F));

        PartDefinition EquipTJaw01_1 = EquipT04_1.addOrReplaceChild("EquipTJaw01_1", CubeListBuilder.create().texOffs(59, 25).addBox(-3.5F, 0F, 0F, 7F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6.3F, 0.5F, 0.2618F, 0F, 0F));

        PartDefinition EquipTJaw02_1 = EquipT04_1.addOrReplaceChild("EquipTJaw02_1", CubeListBuilder.create().texOffs(59, 25).mirror().addBox(-3.5F, 0F, -2.5F, 7F, 4F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 4.6F, 1F));

        PartDefinition EquipTEyeB_1 = EquipT04_1.addOrReplaceChild("EquipTEyeB_1", CubeListBuilder.create().texOffs(0, 14).addBox(0F, 0F, 0F, 0F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, 10.9F, 3F, -2.0944F, 0F, 0F));

        PartDefinition EquipTEyeA_1 = EquipT04_1.addOrReplaceChild("EquipTEyeA_1", CubeListBuilder.create().texOffs(0, 14).addBox(0F, 0F, 0F, 0F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, 10.9F, 3F, -2.0944F, 0F, 0F));

        PartDefinition EquipT07_1 = EquipTBase_1.addOrReplaceChild("EquipT07_1", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 1F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, 24.7F, 0F));

        PartDefinition EquipT01_1 = EquipTBase_1.addOrReplaceChild("EquipT01_1", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 3F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -19F, 0F));

        PartDefinition EquipT02_1 = EquipTBase_1.addOrReplaceChild("EquipT02_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -16F, 0F));

        PartDefinition EquipT02a_1 = EquipT02_1.addOrReplaceChild("EquipT02a_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offset(2.9F, 0.5F, 0F));

        PartDefinition EquipT02b_1 = EquipT02_1.addOrReplaceChild("EquipT02b_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offset(-2.9F, 0.5F, 0F));

        PartDefinition EquipT02c_1 = EquipT02_1.addOrReplaceChild("EquipT02c_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 5.9F, 0F, 1.5708F, 0F));

        PartDefinition EquipT02d_1 = EquipT02_1.addOrReplaceChild("EquipT02d_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -0.5F, 3F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -5.9F, 0F, -1.5708F, 0F));

        PartDefinition EquipT03_1 = EquipTBase_1.addOrReplaceChild("EquipT03_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, 0F));

        PartDefinition EquipT06_1 = EquipTBase_1.addOrReplaceChild("EquipT06_1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -2.5F, 5F, 1F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 24F, 0F));

        PartDefinition EquipTube02a_1 = EquipTube02_1.addOrReplaceChild("EquipTube02a_1", CubeListBuilder.create().texOffs(44, 67).addBox(-1F, 0F, 0F, 2F, 7F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        applyFaceAndMouth(entity);

        boolean inDeadPose = entity != null && entity.isInDeadPose();

        if (inDeadPose) {
            this.applyDeadPose();
            this.syncGlowParts();
            return;
        }

        this.applyBasePose(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.applySpecialPoseAdjustments(entity, limbSwing, limbSwingAmount, ageInTicks);
        this.syncGlowParts();
    }

    private void resetOffsets() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;

        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.Skirt01.y = this.skirt01DefaultY;
        this.Skirt01.z = this.skirt01DefaultZ;
        this.Skirt02.y = this.skirt02DefaultY;
        this.Skirt02.z = this.skirt02DefaultZ;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.z = this.armRight02DefaultZ;
        this.BoobL.x = this.boobLDefaultX;
        this.BoobR.x = this.boobRDefaultX;
        this.EquipTBase.y = this.equipTBaseDefaultY;
        this.EquipTBase_1.y = this.equipTBase1DefaultY;
    }

    private void applyEquipVisibility(T entity) {
        this.Collar01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmHime.EQUIP_COLLAR);
        this.EquipTBase.visible = true;
        this.EquipTBase_1.visible = true;
        this.GlowEquipBase.visible = true;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = -0.15F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.BoobL.xRot = -0.76F;
        this.BoobR.xRot = -0.76F;
        this.BodyMain.xRot = 1.6F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 1.2F;
        this.Butt.y = this.buttDefaultY + (-0.2F * OFFSET_SCALE);
        this.Butt.z = this.buttDefaultZ + (-0.14F * OFFSET_SCALE);
        this.Skirt01.xRot = -0.94F;
        this.Skirt01.y = this.skirt01DefaultY + (0.09F * OFFSET_SCALE);
        this.Skirt01.z = this.skirt01DefaultZ + (-0.03F * OFFSET_SCALE);
        this.Skirt02.xRot = -0.3F;

        this.Hair01.xRot = 0.35F;
        this.Hair01.zRot = 0.0F;
        this.Hair02.xRot = -0.2F;
        this.Hair02.zRot = 0.0F;
        this.Hair03.xRot = -0.35F;
        this.Hair03.zRot = 0.0F;
        this.HairL01.xRot = -0.14F;
        this.HairL02.xRot = 0.17F;
        this.HairR01.xRot = -0.14F;
        this.HairR02.xRot = 0.17F;

        this.ArmLeft01.xRot = -2.9F;
        this.ArmLeft01.yRot = -0.6981F;
        this.ArmLeft01.zRot = 0.08F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;
        this.ArmRight01.xRot = -2.9F;
        this.ArmRight01.yRot = 0.6981F;
        this.ArmRight01.zRot = -0.08F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.xRot = -1.9F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.05F;
        this.LegLeft02.xRot = 0.64F;
        this.LegRight01.xRot = -1.9F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.05F;
        this.LegRight02.xRot = 0.64F;

        this.GlowEquipBase.visible = false;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.4F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.8F + limbSwing * 0.5F);
        float angleX3 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 1.2F + limbSwing * 0.5F);
        float angleX4 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 1.6F + limbSwing * 0.5F);
        float angleX5 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 2.0F + limbSwing * 0.5F);
        float angleX6 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 2.4F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.5F;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = 0.0F;

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;

        this.Ahoke01.xRot = angleX1 * 0.07F - 2.01F;
        this.Ahoke01.yRot = 0.52F;
        this.Ahoke01.zRot = 0.0F;
        this.Ahoke02.xRot = -angleX2 * 0.09F + 1.04F;
        this.Ahoke03.xRot = angleX3 * 0.15F + 0.78F;
        this.Ahoke04.xRot = -angleX4 * 0.1F + 0.44F;
        this.Ahoke05.xRot = -angleX5 * 0.15F - 0.17F;
        this.Ahoke06.xRot = angleX6 * 0.18F - 0.31F;

        this.Ahoke01a.xRot = angleX1 * 0.07F - 2.27F;
        this.Ahoke01a.yRot = -2.62F;
        this.Ahoke01a.zRot = 0.0F;
        this.Ahoke02a.xRot = -angleX2 * 0.09F + 0.79F;
        this.Ahoke03a.xRot = angleX3 * 0.15F + 1.05F;
        this.Ahoke04a.xRot = -angleX4 * 0.1F + 0.41F;
        this.Ahoke05a.xRot = -angleX5 * 0.15F - 0.3F;
        this.Ahoke06a.xRot = angleX6 * 0.18F - 0.25F;

        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;

        this.Butt.xRot = 0.35F;

        this.BoobL.xRot = angleX * 0.06F - 0.76F;
        this.BoobL.yRot = -0.087F;
        this.BoobL.zRot = -0.07F;
        this.BoobR.xRot = angleX * 0.06F - 0.76F;
        this.BoobR.yRot = 0.087F;
        this.BoobR.zRot = 0.07F;

        boolean showCollar = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmHime.EQUIP_COLLAR);
        boolean showTails = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmHime.EQUIP_TAILS);
        if (showCollar) {
            this.BoobL.x = this.boobLDefaultX;
            this.BoobR.x = this.boobRDefaultX;
        } else {
            this.BoobL.x = this.boobLDefaultX + (-0.05F * OFFSET_SCALE);
            this.BoobR.x = this.boobRDefaultX + (0.05F * OFFSET_SCALE);
        }

        this.Collar01.xRot = 0.035F;
        this.Collar03.xRot = angleX * 0.08F + 0.26F;
        this.Collar04.xRot = -angleX * 0.08F + 0.45F;

        this.Skirt01.xRot = -0.087F;
        this.Skirt02.xRot = -0.087F;

        this.Hair01.xRot = angleX * 0.03F + 0.26F + headX;
        this.Hair01.zRot = headZ;
        this.Hair02.xRot = -angleX1 * 0.04F - 0.087F + headX;
        this.Hair02.zRot = headZ;
        this.Hair03.xRot = -angleX2 * 0.07F - 0.052F;
        this.Hair03.zRot = headZ;

        this.HairL01.xRot = angleX * 0.02F + headX - 0.19F;
        this.HairL01.zRot = headZ - 0.087F;
        this.HairL02.xRot = -angleX1 * 0.04F + headX + 0.17F;
        this.HairL02.zRot = headZ + 0.087F;

        this.HairR01.xRot = angleX * 0.02F + headX - 0.19F;
        this.HairR01.zRot = headZ + 0.087F;
        this.HairR02.xRot = -angleX1 * 0.04F + headX + 0.17F;
        this.HairR02.zRot = headZ - 0.052F;

        this.ArmLeft01.xRot = angleAdd2 * 0.8F - 0.05F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = angleX * 0.025F - 0.3F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.8F + 0.26F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -angleX * 0.025F + 0.3F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.xRot = angleAdd1 * 0.6F - 0.3F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.087F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;

        this.LegRight01.xRot = angleAdd2 * 0.6F - 0.2F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.087F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        if (showTails) {
            this.EquipTBase.visible = false;
            this.EquipTBase_1.visible = false;
            this.GlowEquipBase.xRot = 0.3F;
            this.EquipTube00.xRot = 0.2618F;
            this.EquipTube00.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.1F + 0.7F) * 0.1F + 0.61F;
            this.EquipTube00.zRot = this.EquipTube00.yRot * 0.125F;
            this.EquipTube01.xRot = 0.35F;
            this.EquipTube01.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.1F + 1.4F) * 0.125F;
            this.EquipTube01.zRot = this.EquipTube01.yRot * 0.125F;
            this.EquipTube02.xRot = 0.5235F;
            this.EquipTube02.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.1F + 2.1F) * 0.15F;
            this.EquipTube02.zRot = this.EquipTube02.yRot * 0.125F;
            this.EquipTube03.xRot = 0.61F;
            this.EquipTube03.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.1F + 2.8F) * 0.175F;
            this.EquipTube03.zRot = this.EquipTube03.yRot * 0.125F;
            this.EquipTube04.xRot = 0.6981F;
            this.EquipTube04.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.1F + 3.5F) * 0.2F;
            this.EquipTube04.zRot = this.EquipTube04.yRot * 0.125F;
            this.EquipTube05.xRot = 0.61F;
            this.EquipTube05.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.1F + 4.2F) * 0.175F;
            this.EquipTube05.zRot = this.EquipTube05.yRot * 0.125F;

            this.EquipTube00_1.xRot = this.EquipTube00.xRot;
            this.EquipTube00_1.yRot = -this.EquipTube00.yRot;
            this.EquipTube00_1.zRot = this.EquipTube00.zRot;
            this.EquipTube01_1.xRot = this.EquipTube01.xRot;
            this.EquipTube01_1.yRot = this.EquipTube01.yRot;
            this.EquipTube01_1.zRot = this.EquipTube01.zRot;
            this.EquipTube02_1.xRot = this.EquipTube02.xRot;
            this.EquipTube02_1.yRot = this.EquipTube02.yRot;
            this.EquipTube02_1.zRot = this.EquipTube02.zRot;
            this.EquipTube03_1.xRot = this.EquipTube03.xRot;
            this.EquipTube03_1.yRot = this.EquipTube03.yRot;
            this.EquipTube03_1.zRot = this.EquipTube03.zRot;
            this.EquipTube04_1.xRot = this.EquipTube04.xRot;
            this.EquipTube04_1.yRot = this.EquipTube04.yRot;
            this.EquipTube04_1.zRot = this.EquipTube04.zRot;
            this.EquipTube05_1.xRot = this.EquipTube05.xRot;
            this.EquipTube05_1.yRot = this.EquipTube05.yRot;
            this.EquipTube05_1.zRot = this.EquipTube05.zRot;
        } else {
            this.EquipTBase.visible = true;
            this.EquipTBase_1.visible = true;
            this.GlowEquipBase.xRot = 0.0F;
        }
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + 0.4F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount * 0.5F;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount * 0.5F;
        float addk1 = angleAdd1 * 0.6F - 0.3F;
        float addk2 = angleAdd2 * 0.6F - 0.2F;
        float addHL1 = 0.0F;
        float addHR1 = 0.0F;
        float addHL2 = 0.0F;
        float addHR2 = 0.0F;
        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity != null ? entity.getIsSprinting() : limbSwingAmount > 0.9F;
        boolean isSitting = entity.getIsSitting() || isPassenger;
        boolean showTails = entity.getEquipFlag(org.trp.shincolle.entity.EntitySubmHime.EQUIP_TAILS);

        if (isSprinting) {
            if (isPassenger) {
                if (limbSwingAmount > 0.5F) {
                    this.Head.xRot += 0.4F;
                    this.Hair01.xRot += 0.1F;
                    this.Hair02.xRot -= 0.2F;
                    this.Hair03.xRot -= 0.2F;
                }
            } else {
                this.poseTranslateY += 0.06F;
                this.Head.xRot -= 1.1F;
                this.Hair01.xRot += 0.6F;
                this.Hair02.xRot += 0.5F;
                this.Hair03.xRot += 0.2F;
                this.Ahoke01.xRot += 0.38F;
                this.Ahoke01.yRot = 0.7F;
                this.Ahoke01.zRot = 0.4F;
                this.Ahoke01a.yRot = -2.5F;
                this.Ahoke01a.zRot = -0.2F;
            }
            this.BodyMain.xRot = 1.2566F;
            this.BoobL.xRot = angleAdd2 * 0.1F - 0.83F;
            this.BoobL.zRot = -0.07F;
            this.BoobR.xRot = angleAdd2 * 0.1F - 0.83F;
            this.BoobR.zRot = 0.07F;
            this.Collar03.xRot += angleAdd2 * 0.1F;
            this.Collar04.xRot += angleAdd2 * 0.1F;
            this.ArmLeft01.xRot = -2.7F;
            this.ArmLeft01.zRot = -0.22F;
            this.ArmRight01.xRot = -2.7F;
            this.ArmRight01.zRot = 0.22F;
            this.LegLeft01.zRot = 0.05F;
            this.LegRight01.zRot = -0.05F;
            if (showTails) {
                this.GlowEquipBase.xRot = 0.3F;
                this.EquipTube00.xRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 0.7F) * 0.1F + 0.4F;
                this.EquipTube00.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 0.7F) * 0.1F + 0.9F;
                this.EquipTube00.zRot = this.EquipTube00.yRot * 0.125F;
                this.EquipTube01.xRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 1.4F) * 0.125F;
                this.EquipTube01.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 1.4F) * 0.125F;
                this.EquipTube01.zRot = this.EquipTube01.yRot * 0.125F;
                this.EquipTube02.xRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 2.1F) * 0.15F;
                this.EquipTube02.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 2.1F) * 0.15F;
                this.EquipTube02.zRot = this.EquipTube02.yRot * 0.125F;
                this.EquipTube03.xRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 2.8F) * 0.175F;
                this.EquipTube03.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 2.8F) * 0.175F;
                this.EquipTube03.zRot = this.EquipTube03.yRot * 0.125F;
                this.EquipTube04.xRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 3.5F) * 0.2F;
                this.EquipTube04.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 3.5F) * 0.2F;
                this.EquipTube04.zRot = this.EquipTube04.yRot * 0.125F;
                this.EquipTube05.xRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 4.2F) * 0.175F;
                this.EquipTube05.yRot = net.minecraft.util.Mth.cos(-ageInTicks * 0.4F + 4.2F) * 0.175F;
                this.EquipTube05.zRot = this.EquipTube05.yRot * 0.125F;
                this.EquipTube00_1.xRot = this.EquipTube00.xRot;
                this.EquipTube00_1.yRot = -this.EquipTube00.yRot;
                this.EquipTube00_1.zRot = -this.EquipTube00.zRot;
                this.EquipTube01_1.xRot = this.EquipTube01.xRot;
                this.EquipTube01_1.yRot = this.EquipTube01.yRot;
                this.EquipTube01_1.zRot = this.EquipTube01.zRot;
                this.EquipTube02_1.xRot = this.EquipTube02.xRot;
                this.EquipTube02_1.yRot = this.EquipTube02.yRot;
                this.EquipTube02_1.zRot = this.EquipTube02.zRot;
                this.EquipTube03_1.xRot = this.EquipTube03.xRot;
                this.EquipTube03_1.yRot = this.EquipTube03.yRot;
                this.EquipTube03_1.zRot = this.EquipTube03.zRot;
                this.EquipTube04_1.xRot = this.EquipTube04.xRot;
                this.EquipTube04_1.yRot = this.EquipTube04.yRot;
                this.EquipTube04_1.zRot = this.EquipTube04.zRot;
                this.EquipTube05_1.xRot = this.EquipTube05.xRot;
                this.EquipTube05_1.yRot = this.EquipTube05.yRot;
                this.EquipTube05_1.zRot = this.EquipTube05.zRot;
            }
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.Head.xRot -= 0.6283F;
            this.BodyMain.xRot = 0.8727F;
            this.Skirt01.xRot = -0.34F;
            this.Skirt01.y = this.skirt01DefaultY + (-0.2F * OFFSET_SCALE);
            this.Skirt01.z = this.skirt01DefaultZ + (0.03F * OFFSET_SCALE);
            this.Skirt02.xRot = -0.27F;
            this.Collar01.xRot -= 0.35F;
            this.Collar03.xRot -= 0.3F;
            this.Collar04.xRot -= 0.35F;
            this.BoobL.xRot -= 0.2F;
            this.BoobL.zRot = -0.04F;
            this.BoobR.xRot -= 0.2F;
            this.BoobR.zRot = 0.04F;
            this.ArmLeft01.xRot = -0.35F;
            this.ArmLeft01.zRot = 0.2618F;
            this.ArmRight01.xRot = -0.35F;
            this.ArmRight01.zRot = -0.2618F;
            addk1 -= 0.94F;
            addk2 -= 0.94F;
            this.LegLeft01.zRot = 0.2F;
            this.LegRight01.zRot = -0.2F;
            this.Hair01.xRot = this.Hair01.xRot * 0.5F + 0.4F;
            this.Hair02.xRot = this.Hair02.xRot * 0.75F + 0.25F;
            this.Hair03.xRot -= 0.1F;
            this.GlowEquipBase.xRot = -0.2F;
        }

        if (isSitting && !isPassenger) {
            if ((entity.tickCount & 0x1FF) > 256) {
                this.poseTranslateY += -angleX * 0.05F + 0.1F;
                this.Head.xRot *= 0.5F;
                this.Head.yRot *= 0.75F;
                this.Head.xRot += 0.5F;
                this.BodyMain.xRot = 1.6F;
                this.Skirt01.xRot = -0.33F;
                this.Skirt01.y = this.skirt01DefaultY + (-0.23F * OFFSET_SCALE);
                this.Skirt02.xRot = -0.12F;
                this.Skirt02.y = this.skirt02DefaultY + (-0.16F * OFFSET_SCALE);
                this.Ahoke01.xRot += 0.38F;
                this.Ahoke01.yRot = 0.8F;
                this.Ahoke01.zRot = 0.4F;
                this.Hair01.xRot -= 0.2F;
                this.Hair02.xRot -= 0.25F;
                this.Hair03.xRot -= 0.3F;
                this.ArmLeft01.xRot = -1.5F;
                this.ArmLeft01.zRot = -2.3F;
                this.ArmRight01.xRot = -1.5F;
                this.ArmRight01.zRot = 2.3F;
                addk1 = -1.8F;
                addk2 = -1.8F;
                this.LegLeft01.yRot = -0.1F - angleX * 0.02F;
                this.LegRight01.yRot = 0.1F + angleX * 0.02F;
            } else if (hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.52F * 3;
                this.Head.xRot = 0.4F;
                this.Skirt01.xRot = -0.64F;
                this.Skirt01.y = this.skirt01DefaultY + (-0.17F * OFFSET_SCALE);
                this.Skirt01.z = this.skirt01DefaultZ;
                this.Skirt02.xRot = 0.29F;
                this.Skirt02.y = this.skirt02DefaultY + (-0.04F * OFFSET_SCALE);
                this.Skirt02.z = this.skirt02DefaultZ + (0.02F * OFFSET_SCALE);
                this.Hair01.xRot -= 0.2F;
                this.Hair02.xRot -= 0.15F;
                this.Hair03.xRot -= 0.1F;
                this.Ahoke01.xRot -= 0.1F;
                this.ArmLeft01.xRot = 0.4F;
                this.ArmLeft01.yRot = -2.9670596F;
                this.ArmLeft01.zRot = -2.62F;
                this.ArmLeft02.xRot = 0.0F;
                this.ArmLeft02.yRot = 0.0F;
                this.ArmLeft02.zRot = 1.0F;
                this.ArmRight01.xRot = 0.5235988F;
                this.ArmRight01.yRot = 2.9670596F;
                this.ArmRight01.zRot = 2.62F;
                this.ArmRight02.xRot = 0.0F;
                this.ArmRight02.yRot = 0.0F;
                this.ArmRight02.zRot = -1.0F;
                addk1 = -2.4130921F;
                addk2 = -2.268928F;
                this.LegLeft01.yRot = 0.0F;
                this.LegLeft01.zRot = -0.27314404F;
                this.LegLeft02.xRot = 1.4570009F;
                this.LegLeft02.yRot = 0.0F;
                this.LegLeft02.zRot = 0.0F;
                this.LegRight01.yRot = 0.0F;
                this.LegRight01.zRot = 0.22759093F;
                this.LegRight02.xRot = 1.0471976F;
                this.LegRight02.yRot = 0.0F;
                this.LegRight02.zRot = 0.0F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                this.Head.xRot -= 0.7F;
                this.BodyMain.xRot = 0.35F;
                this.Hair01.xRot += 0.3F;
                this.Hair02.xRot += 0.3F;
                this.Hair03.xRot += 0.3F;
                this.Skirt01.xRot = -0.32F;
                this.Skirt01.y = this.skirt01DefaultY + (-0.05F * OFFSET_SCALE);
                this.Skirt02.xRot = -0.21F;
                this.Collar01.xRot += 0.1F;
                this.Collar03.xRot += 0.1F;
                this.ArmLeft01.xRot = -0.5235988F;
                this.ArmLeft01.yRot = 0.0F;
                this.ArmLeft01.zRot = 0.34906584F;
                this.ArmLeft02.xRot = 0.0F;
                this.ArmLeft02.zRot = 0.0F;
                this.ArmRight01.xRot = -0.5235988F;
                this.ArmRight01.yRot = 0.0F;
                this.ArmRight01.zRot = -0.34906584F;
                this.ArmRight02.xRot = 0.0F;
                this.ArmRight02.zRot = 0.0F;
                addk1 = -1.4486233F;
                addk2 = -1.4486233F;
                this.LegLeft01.yRot = -0.5235988F;
                this.LegLeft01.zRot = -1.3962634F;
                this.LegLeft02.xRot = 2.1816616F;
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.37F * OFFSET_SCALE);
                this.LegRight01.yRot = 0.5235988F;
                this.LegRight01.zRot = 1.3962634F;
                this.LegRight02.xRot = 2.1816616F;
                this.LegRight02.z = this.legRight02DefaultZ + (0.37F * OFFSET_SCALE);
            }
        } else if (isPassenger) {
            this.poseTranslateY += RIDING_TRANSLATE_Y;
            this.Head.xRot *= 0.5F;
            this.Head.yRot *= 0.75F;
            this.Head.xRot -= 1.0F;
            this.BodyMain.xRot = 1.0F;
            this.Skirt01.xRot = -0.33F;
            this.Skirt01.y = this.skirt01DefaultY + (-0.23F * OFFSET_SCALE);
            this.Skirt02.xRot = -0.12F;
            this.Skirt02.y = this.skirt02DefaultY + (-0.16F * OFFSET_SCALE);
            this.Collar01.xRot -= 0.5F;
            this.Collar03.xRot -= 0.5F;
            this.Collar04.xRot -= 0.5F;
            this.Ahoke01.xRot += 0.38F;
            this.Ahoke01.yRot = 0.8F;
            this.Ahoke01.zRot = 0.4F;
            this.Hair01.xRot += 0.5F;
            this.Hair02.xRot += 0.65F;
            this.Hair03.xRot += 0.5F;
            addHL1 = -0.6F;
            addHR1 = -0.6F;
            addHL2 = -0.5F;
            addHR2 = -0.5F;
            this.ArmLeft01.xRot = -1.4F;
            this.ArmRight01.xRot = -1.4F;
            addk1 = -1.7F;
            addk2 = -1.7F;
            this.LegLeft01.yRot = -0.2F;
            this.LegRight01.yRot = 0.2F;
        }

        if (entity != null && entity.getAttackTick() > 14) {
            if (isPassenger) {
                this.poseTranslateY += 0.02F;
                this.Head.xRot *= 0.5F;
                this.Head.yRot *= 0.75F;
                this.Head.xRot -= 0.5F;
                this.BodyMain.xRot = 1.1F;
                this.Collar01.xRot -= 0.2F;
                this.Ahoke01.xRot += 0.38F;
                this.Ahoke01.yRot = 0.8F;
                this.Ahoke01.zRot = 0.4F;
                this.Hair01.xRot += 0.2F;
                this.Hair02.xRot += -0.1F;
                this.Hair03.xRot += -0.1F;
                addHL1 = -0.6F;
                addHR1 = -0.6F;
                addHL2 = -0.5F;
                addHR2 = -0.5F;
                addk1 = -1.8F;
                addk2 = -1.8F;
                this.LegLeft01.yRot = -0.1F;
                this.LegRight01.yRot = 0.1F;
                this.GlowEquipBase.xRot = 0.5F;
            } else {
                this.poseTranslateY += 0.22F;
                this.Head.xRot *= 0.5F;
                this.Head.yRot *= 0.75F;
                this.Head.xRot -= 1.6F;
                this.BodyMain.xRot = 1.6F;
                this.Collar01.xRot -= 0.5F;
                this.Collar03.xRot -= 0.5F;
                this.Collar04.xRot -= 0.5F;
                this.Ahoke01.xRot += 0.38F;
                this.Ahoke01.yRot = 0.8F;
                this.Ahoke01.zRot = 0.4F;
                this.Hair01.xRot += 1.0F;
                this.Hair02.xRot += 0.6F;
                this.Hair03.xRot += 0.7F;
                addHL1 = -0.6F;
                addHR1 = -0.6F;
                addHL2 = -0.5F;
                addHR2 = -0.5F;
                addk1 = -2.2F;
                addk2 = -2.2F;
                this.LegLeft01.yRot = -0.1F;
                this.LegRight01.yRot = 0.1F;
                this.GlowEquipBase.xRot = 0.0F;
            }
            this.Skirt01.xRot = -0.33F;
            this.Skirt01.y = this.skirt01DefaultY + (-0.23F * OFFSET_SCALE);
            this.Skirt02.xRot = -0.12F;
            this.Skirt02.y = this.skirt02DefaultY + (-0.16F * OFFSET_SCALE);
            this.ArmLeft01.xRot = -1.6F;
            this.ArmLeft01.yRot = -0.2F;
            this.ArmRight01.xRot = -1.2F;
            this.ArmRight01.yRot = 1.2F;
        }

        setTorpedo(entity.getAttackTick(), showTails);

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot = -0.3F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = -0.1F;
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F);
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
            this.ArmRight02.xRot = 0.0F;
            this.ArmRight02.zRot = 0.0F;
        }

        float headX = this.Head.xRot * -0.5F;
        this.HairL01.xRot = angleX * 0.02F + headX - 0.19F + addHL1;
        this.HairL02.xRot = -angleX1 * 0.04F + headX + 0.17F + addHL2;
        this.HairR01.xRot = angleX * 0.02F + headX - 0.19F + addHR1;
        this.HairR02.xRot = -angleX1 * 0.04F + headX + 0.17F + addHR2;
        float headZ = this.Head.zRot * -0.5F;
        this.Hair01.zRot = headZ;
        this.Hair02.zRot = headZ;
        this.Hair03.zRot = headZ;
        this.HairL01.zRot = headZ - 0.087F;
        this.HairL02.zRot = headZ + 0.087F;
        this.HairR01.zRot = headZ + 0.087F;
        this.HairR02.zRot = headZ - 0.052F;
        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void setTorpedo(int attackTime, boolean showTails) {
        if (attackTime <= 14) {
            this.GlowEquipBase.visible = showTails;
            this.EquipTBase.visible = false;
            this.EquipTBase_1.visible = false;
            return;
        }
        this.EquipTube00.xRot = 0.2618F;
        this.EquipTube00.yRot = 0.61F;
        this.EquipTube00.zRot = 0.0F;
        this.EquipTube01.xRot = 0.35F;
        this.EquipTube01.yRot = 0.0F;
        this.EquipTube01.zRot = 0.0F;
        this.EquipTube02.xRot = 0.5235F;
        this.EquipTube02.yRot = 0.0F;
        this.EquipTube02.zRot = 0.0F;
        this.EquipTube03.xRot = 0.61F;
        this.EquipTube03.yRot = 0.0F;
        this.EquipTube03.zRot = 0.0F;
        this.EquipTube04.xRot = 0.6981F;
        this.EquipTube04.yRot = 0.0F;
        this.EquipTube04.zRot = 0.0F;
        this.EquipTube05.xRot = 0.61F;
        this.EquipTube05.yRot = 0.0F;
        this.EquipTube05.zRot = 0.0F;
        this.EquipTube00_1.xRot = 0.2618F;
        this.EquipTube00_1.yRot = -0.61F;
        this.EquipTube00_1.zRot = 0.0F;
        this.EquipTube01_1.xRot = 0.35F;
        this.EquipTube01_1.yRot = 0.0F;
        this.EquipTube01_1.zRot = 0.0F;
        this.EquipTube02_1.xRot = 0.5235F;
        this.EquipTube02_1.yRot = 0.0F;
        this.EquipTube02_1.zRot = 0.0F;
        this.EquipTube03_1.xRot = 0.61F;
        this.EquipTube03_1.yRot = 0.0F;
        this.EquipTube03_1.zRot = 0.0F;
        this.EquipTube04_1.xRot = 0.6981F;
        this.EquipTube04_1.yRot = 0.0F;
        this.EquipTube04_1.zRot = 0.0F;
        this.EquipTube05_1.xRot = 0.61F;
        this.EquipTube05_1.yRot = 0.0F;
        this.EquipTube05_1.zRot = 0.0F;
        this.EquipTBase.visible = true;
        this.EquipTBase_1.visible = true;
        this.GlowEquipBase.visible = true;
        switch (attackTime) {
            case 50:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-2.73F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-2.73F * OFFSET_SCALE);
                break;
            case 49:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-2.71F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-2.71F * OFFSET_SCALE);
                break;
            case 48:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-2.69F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-2.69F * OFFSET_SCALE);
                break;
            case 47:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-2.375F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-2.375F * OFFSET_SCALE);
                break;
            case 46:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-2.06F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-2.06F * OFFSET_SCALE);
                break;
            case 45:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-1.75F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-1.75F * OFFSET_SCALE);
                break;
            case 44:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-1.44F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-1.44F * OFFSET_SCALE);
                break;
            case 43:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-1.125F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-1.125F * OFFSET_SCALE);
                break;
            case 42:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-0.81F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-0.81F * OFFSET_SCALE);
                break;
            case 41:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-0.5F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-0.5F * OFFSET_SCALE);
                break;
            case 40:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-0.19F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-0.19F * OFFSET_SCALE);
                break;
            case 39:
                this.EquipTBase.y = this.equipTBaseDefaultY + (-0.095F * OFFSET_SCALE);
                this.EquipTBase_1.y = this.equipTBase1DefaultY + (-0.095F * OFFSET_SCALE);
                break;
            default:
                this.EquipTBase.y = this.equipTBaseDefaultY;
                this.EquipTBase_1.y = this.equipTBase1DefaultY;
        }
        switch (attackTime) {
            case 49:
            case 50:
                this.EquipT07.visible = true;
                this.EquipT07_1.visible = true;
                this.EquipT06.visible = false;
                this.EquipT06_1.visible = false;
                this.EquipT05.visible = false;
                this.EquipT05_1.visible = false;
                this.EquipT04.visible = false;
                this.EquipT04_1.visible = false;
                this.EquipT03.visible = false;
                this.EquipT03_1.visible = false;
                this.EquipT02.visible = false;
                this.EquipT02_1.visible = false;
                this.EquipT01.visible = false;
                this.EquipT01_1.visible = false;
                break;
            case 47:
            case 48:
                this.EquipT07.visible = true;
                this.EquipT07_1.visible = true;
                this.EquipT06.visible = true;
                this.EquipT06_1.visible = true;
                this.EquipT05.visible = false;
                this.EquipT05_1.visible = false;
                this.EquipT04.visible = false;
                this.EquipT04_1.visible = false;
                this.EquipT03.visible = false;
                this.EquipT03_1.visible = false;
                this.EquipT02.visible = false;
                this.EquipT02_1.visible = false;
                this.EquipT01.visible = false;
                this.EquipT01_1.visible = false;
                break;
            case 45:
            case 46:
                this.EquipT07.visible = true;
                this.EquipT07_1.visible = true;
                this.EquipT06.visible = true;
                this.EquipT06_1.visible = true;
                this.EquipT05.visible = true;
                this.EquipT05_1.visible = true;
                this.EquipT04.visible = false;
                this.EquipT04_1.visible = false;
                this.EquipT03.visible = false;
                this.EquipT03_1.visible = false;
                this.EquipT02.visible = false;
                this.EquipT02_1.visible = false;
                this.EquipT01.visible = false;
                this.EquipT01_1.visible = false;
                break;
            case 43:
            case 44:
                this.EquipT07.visible = true;
                this.EquipT07_1.visible = true;
                this.EquipT06.visible = true;
                this.EquipT06_1.visible = true;
                this.EquipT05.visible = true;
                this.EquipT05_1.visible = true;
                this.EquipT04.visible = true;
                this.EquipT04_1.visible = true;
                this.EquipT03.visible = false;
                this.EquipT03_1.visible = false;
                this.EquipT02.visible = false;
                this.EquipT02_1.visible = false;
                this.EquipT01.visible = false;
                this.EquipT01_1.visible = false;
                break;
            case 41:
            case 42:
                this.EquipT07.visible = true;
                this.EquipT07_1.visible = true;
                this.EquipT06.visible = true;
                this.EquipT06_1.visible = true;
                this.EquipT05.visible = true;
                this.EquipT05_1.visible = true;
                this.EquipT04.visible = true;
                this.EquipT04_1.visible = true;
                this.EquipT03.visible = true;
                this.EquipT03_1.visible = true;
                this.EquipT02.visible = false;
                this.EquipT02_1.visible = false;
                this.EquipT01.visible = false;
                this.EquipT01_1.visible = false;
                break;
            case 39:
            case 40:
                this.EquipT07.visible = true;
                this.EquipT07_1.visible = true;
                this.EquipT06.visible = true;
                this.EquipT06_1.visible = true;
                this.EquipT05.visible = true;
                this.EquipT05_1.visible = true;
                this.EquipT04.visible = true;
                this.EquipT04_1.visible = true;
                this.EquipT03.visible = true;
                this.EquipT03_1.visible = true;
                this.EquipT02.visible = true;
                this.EquipT02_1.visible = true;
                this.EquipT01.visible = false;
                this.EquipT01_1.visible = false;
                break;
            default:
                if (attackTime < 39) {
                    this.EquipT07.visible = true;
                    this.EquipT07_1.visible = true;
                    this.EquipT06.visible = true;
                    this.EquipT06_1.visible = true;
                    this.EquipT05.visible = true;
                    this.EquipT05_1.visible = true;
                    this.EquipT04.visible = true;
                    this.EquipT04_1.visible = true;
                    this.EquipT03.visible = true;
                    this.EquipT03_1.visible = true;
                    this.EquipT02.visible = true;
                    this.EquipT02_1.visible = true;
                    this.EquipT01.visible = true;
                    this.EquipT01_1.visible = true;
                }
        }
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowNeck.copyFrom(this.Neck);
        this.GlowHead.copyFrom(this.Head);
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }

    @Override
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        this.GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
