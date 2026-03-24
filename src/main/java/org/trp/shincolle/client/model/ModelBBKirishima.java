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

public class ModelBBKirishima<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "bb_kirishima"), "main");

    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBBKirishima");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBBKirishima");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBBKirishima");
    private static final float OFFSET_SCALE = 16.0F;

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Butt;
    private final ModelPart ArmLeft01;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart ArmRight01;
    private final ModelPart Cloth03a1;
    private final ModelPart Cloth03a2;
    private final ModelPart EquipBase;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart EquipHeadBase;
    private final ModelPart EquipGlass01;
    private final ModelPart HairU01;
    private final ModelPart Ahoke;
    private final ModelPart Hair01;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead00;
    private final ModelPart EquipHead01_1;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead02_1;
    private final ModelPart EquipHead03_1;
    private final ModelPart EquipGlass02a;
    private final ModelPart EquipGlass02b;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart SkirtB01;
    private final ModelPart Cloth02a1;
    private final ModelPart Cloth02b1;
    private final ModelPart LegLeft02;
    private final ModelPart Skirt02;
    private final ModelPart LegRight02;
    private final ModelPart Cloth01a;
    private final ModelPart Cloth02c1;
    private final ModelPart Cloth02c1_1;
    private final ModelPart Cloth01b;
    private final ModelPart Cloth01c;
    private final ModelPart Cloth01b2;
    private final ModelPart Cloth01c2;
    private final ModelPart Cloth02c2;
    private final ModelPart Cloth02c3;
    private final ModelPart Cloth02c4;
    private final ModelPart Cloth02c2_1;
    private final ModelPart Cloth02c3_1;
    private final ModelPart Cloth02c4_1;
    private final ModelPart Cloth02a2;
    private final ModelPart Cloth02a3;
    private final ModelPart Cloth02b2;
    private final ModelPart Cloth02b3;
    private final ModelPart ArmLeft02;
    private final ModelPart ClothA01;
    private final ModelPart ClothA02;
    private final ModelPart ClothA03;
    private final ModelPart ClothA04;
    private final ModelPart ClothA05;
    private final ModelPart Cloth03b;
    private final ModelPart ClothB01;
    private final ModelPart Cloth03b_1;
    private final ModelPart ArmRight02;
    private final ModelPart ClothA01_1;
    private final ModelPart ClothA02a;
    private final ModelPart ClothA03a;
    private final ModelPart ClothA04a;
    private final ModelPart ClothA05a;
    private final ModelPart EquipD01a;
    private final ModelPart EquipD02a;
    private final ModelPart EquipD02b;
    private final ModelPart EquipD01b;
    private final ModelPart EquipD02c;
    private final ModelPart EquipD02d;
    private final ModelPart EquipD03a1;
    private final ModelPart EquipD03b1;
    private final ModelPart EquipD03c1;
    private final ModelPart EquipD03d1;
    private final ModelPart EquipD01aa;
    private final ModelPart EquipD01ba;
    private final ModelPart EquipD01bb;
    private final ModelPart EquipD03a2;
    private final ModelPart EquipD03aa;
    private final ModelPart EquipD03ab;
    private final ModelPart EquipD03a3;
    private final ModelPart EquipD03a4;
    private final ModelPart EquipB05;
    private final ModelPart EquipCL1Base01L2;
    private final ModelPart EquipCL1Base02;
    private final ModelPart EquipCL1a1;
    private final ModelPart EquipCL1a1_1;
    private final ModelPart EquipCL1Base01a;
    private final ModelPart EquipCL1a2;
    private final ModelPart EquipCL1a2_1;
    private final ModelPart EquipD03a2_1;
    private final ModelPart EquipD03aa_1;
    private final ModelPart EquipD03ab_1;
    private final ModelPart EquipD03a3_1;
    private final ModelPart EquipD03a4_1;
    private final ModelPart EquipB05_1;
    private final ModelPart EquipCL1Base01R2;
    private final ModelPart EquipCL1Base02_1;
    private final ModelPart EquipCL1a1_2;
    private final ModelPart EquipCL1a1_3;
    private final ModelPart EquipCL1Base01a_1;
    private final ModelPart EquipCL1a2_2;
    private final ModelPart EquipCL1a2_3;
    private final ModelPart EquipD03c1a;
    private final ModelPart EquipD03c1b;
    private final ModelPart EquipD03c2;
    private final ModelPart EquipD03c2a;
    private final ModelPart EquipD03c3;
    private final ModelPart EquipD03c3a;
    private final ModelPart EquipB05_2;
    private final ModelPart EquipCL1Base01L1;
    private final ModelPart EquipCL1Base02_2;
    private final ModelPart EquipCL1a1_4;
    private final ModelPart EquipCL1a1_5;
    private final ModelPart EquipCL1Base01a_2;
    private final ModelPart EquipCL1Base01b;
    private final ModelPart EquipCL1a2_4;
    private final ModelPart EquipCL1a2_5;
    private final ModelPart EquipD03c1a_1;
    private final ModelPart EquipD03c1b_1;
    private final ModelPart EquipD03c2_1;
    private final ModelPart EquipD03c2a_1;
    private final ModelPart EquipD03c3_1;
    private final ModelPart EquipD03c3a_1;
    private final ModelPart EquipB05_3;
    private final ModelPart EquipCL1Base01R1;
    private final ModelPart EquipCL1Base02_3;
    private final ModelPart EquipCL1a1_6;
    private final ModelPart EquipCL1a1_7;
    private final ModelPart EquipCL1Base01a_3;
    private final ModelPart EquipCL1Base01b_1;
    private final ModelPart EquipCL1a2_6;
    private final ModelPart EquipCL1a2_7;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
    private final ModelPart GlowNeck;
    private final float buttDefaultY, buttDefaultZ;
    private final float skirt01DefaultY, skirt01DefaultZ;
    private final float clothA03DefaultX, clothA03DefaultY, clothA03DefaultZ;
    private final float clothA04DefaultY, clothA04DefaultZ;
    private final float clothA05DefaultY, clothA05DefaultZ;
    private final float clothA03aDefaultX, clothA03aDefaultY, clothA03aDefaultZ;
    private final float clothA04aDefaultY, clothA04aDefaultZ;
    private final float clothA05aDefaultY, clothA05aDefaultZ;
    private final float armLeft02DefaultX, armLeft02DefaultZ;
    private final float armRight02DefaultX, armRight02DefaultZ;
    private final float legLeft01DefaultY, legLeft01DefaultZ;
    private final float legLeft02DefaultX, legLeft02DefaultY, legLeft02DefaultZ;
    private final float legRight01DefaultY, legRight01DefaultZ;
    private final float legRight02DefaultX, legRight02DefaultY, legRight02DefaultZ;
    private final float equipGlass01DefaultZ;

    public ModelBBKirishima(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.ClothB01 = this.BoobR.getChild("ClothB01");
        this.Cloth03b = this.BoobR.getChild("Cloth03b");
        this.Cloth03a1 = this.BodyMain.getChild("Cloth03a1");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ClothA01_1 = this.ArmRight01.getChild("ClothA01_1");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ClothA02a = this.ArmRight02.getChild("ClothA02a");
        this.ClothA03a = this.ClothA02a.getChild("ClothA03a");
        this.ClothA04a = this.ClothA03a.getChild("ClothA04a");
        this.ClothA05a = this.ClothA04a.getChild("ClothA05a");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipD01a = this.EquipBase.getChild("EquipD01a");
        this.EquipD01b = this.EquipD01a.getChild("EquipD01b");
        this.EquipD01ba = this.EquipD01b.getChild("EquipD01ba");
        this.EquipD01bb = this.EquipD01b.getChild("EquipD01bb");
        this.EquipD03a1 = this.EquipD01a.getChild("EquipD03a1");
        this.EquipD03ab = this.EquipD03a1.getChild("EquipD03ab");
        this.EquipD03aa = this.EquipD03a1.getChild("EquipD03aa");
        this.EquipD03a2 = this.EquipD03a1.getChild("EquipD03a2");
        this.EquipD03a3 = this.EquipD03a2.getChild("EquipD03a3");
        this.EquipD03a4 = this.EquipD03a3.getChild("EquipD03a4");
        this.EquipB05 = this.EquipD03a4.getChild("EquipB05");
        this.EquipCL1Base01L2 = this.EquipB05.getChild("EquipCL1Base01L2");
        this.EquipCL1a1_1 = this.EquipCL1Base01L2.getChild("EquipCL1a1_1");
        this.EquipCL1a2_1 = this.EquipCL1a1_1.getChild("EquipCL1a2_1");
        this.EquipCL1Base02 = this.EquipCL1Base01L2.getChild("EquipCL1Base02");
        this.EquipCL1a1 = this.EquipCL1Base01L2.getChild("EquipCL1a1");
        this.EquipCL1a2 = this.EquipCL1a1.getChild("EquipCL1a2");
        this.EquipCL1Base01a = this.EquipCL1Base01L2.getChild("EquipCL1Base01a");
        this.EquipD03c1 = this.EquipD01a.getChild("EquipD03c1");
        this.EquipD03c1b = this.EquipD03c1.getChild("EquipD03c1b");
        this.EquipD03c2 = this.EquipD03c1.getChild("EquipD03c2");
        this.EquipD03c3 = this.EquipD03c2.getChild("EquipD03c3");
        this.EquipB05_2 = this.EquipD03c3.getChild("EquipB05_2");
        this.EquipCL1Base01L1 = this.EquipB05_2.getChild("EquipCL1Base01L1");
        this.EquipCL1Base01b = this.EquipCL1Base01L1.getChild("EquipCL1Base01b");
        this.EquipCL1a1_4 = this.EquipCL1Base01L1.getChild("EquipCL1a1_4");
        this.EquipCL1a2_4 = this.EquipCL1a1_4.getChild("EquipCL1a2_4");
        this.EquipCL1a1_5 = this.EquipCL1Base01L1.getChild("EquipCL1a1_5");
        this.EquipCL1a2_5 = this.EquipCL1a1_5.getChild("EquipCL1a2_5");
        this.EquipCL1Base02_2 = this.EquipCL1Base01L1.getChild("EquipCL1Base02_2");
        this.EquipCL1Base01a_2 = this.EquipCL1Base01L1.getChild("EquipCL1Base01a_2");
        this.EquipD03c3a = this.EquipD03c3.getChild("EquipD03c3a");
        this.EquipD03c2a = this.EquipD03c2.getChild("EquipD03c2a");
        this.EquipD03c1a = this.EquipD03c1.getChild("EquipD03c1a");
        this.EquipD02c = this.EquipD01a.getChild("EquipD02c");
        this.EquipD02a = this.EquipD01a.getChild("EquipD02a");
        this.EquipD03d1 = this.EquipD01a.getChild("EquipD03d1");
        this.EquipD03c1a_1 = this.EquipD03d1.getChild("EquipD03c1a_1");
        this.EquipD03c1b_1 = this.EquipD03d1.getChild("EquipD03c1b_1");
        this.EquipD03c2_1 = this.EquipD03d1.getChild("EquipD03c2_1");
        this.EquipD03c2a_1 = this.EquipD03c2_1.getChild("EquipD03c2a_1");
        this.EquipD03c3_1 = this.EquipD03c2_1.getChild("EquipD03c3_1");
        this.EquipD03c3a_1 = this.EquipD03c3_1.getChild("EquipD03c3a_1");
        this.EquipB05_3 = this.EquipD03c3_1.getChild("EquipB05_3");
        this.EquipCL1Base01R1 = this.EquipB05_3.getChild("EquipCL1Base01R1");
        this.EquipCL1Base01a_3 = this.EquipCL1Base01R1.getChild("EquipCL1Base01a_3");
        this.EquipCL1a1_7 = this.EquipCL1Base01R1.getChild("EquipCL1a1_7");
        this.EquipCL1a2_7 = this.EquipCL1a1_7.getChild("EquipCL1a2_7");
        this.EquipCL1Base02_3 = this.EquipCL1Base01R1.getChild("EquipCL1Base02_3");
        this.EquipCL1Base01b_1 = this.EquipCL1Base01R1.getChild("EquipCL1Base01b_1");
        this.EquipCL1a1_6 = this.EquipCL1Base01R1.getChild("EquipCL1a1_6");
        this.EquipCL1a2_6 = this.EquipCL1a1_6.getChild("EquipCL1a2_6");
        this.EquipD02b = this.EquipD01a.getChild("EquipD02b");
        this.EquipD01aa = this.EquipD01a.getChild("EquipD01aa");
        this.EquipD03b1 = this.EquipD01a.getChild("EquipD03b1");
        this.EquipD03aa_1 = this.EquipD03b1.getChild("EquipD03aa_1");
        this.EquipD03a2_1 = this.EquipD03b1.getChild("EquipD03a2_1");
        this.EquipD03a3_1 = this.EquipD03a2_1.getChild("EquipD03a3_1");
        this.EquipD03a4_1 = this.EquipD03a3_1.getChild("EquipD03a4_1");
        this.EquipB05_1 = this.EquipD03a4_1.getChild("EquipB05_1");
        this.EquipCL1Base01R2 = this.EquipB05_1.getChild("EquipCL1Base01R2");
        this.EquipCL1Base02_1 = this.EquipCL1Base01R2.getChild("EquipCL1Base02_1");
        this.EquipCL1a1_3 = this.EquipCL1Base01R2.getChild("EquipCL1a1_3");
        this.EquipCL1a2_3 = this.EquipCL1a1_3.getChild("EquipCL1a2_3");
        this.EquipCL1Base01a_1 = this.EquipCL1Base01R2.getChild("EquipCL1Base01a_1");
        this.EquipCL1a1_2 = this.EquipCL1Base01R2.getChild("EquipCL1a1_2");
        this.EquipCL1a2_2 = this.EquipCL1a1_2.getChild("EquipCL1a2_2");
        this.EquipD03ab_1 = this.EquipD03b1.getChild("EquipD03ab_1");
        this.EquipD02d = this.EquipD01a.getChild("EquipD02d");
        this.Cloth03a2 = this.BodyMain.getChild("Cloth03a2");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.EquipHeadBase = this.Head.getChild("EquipHeadBase");
        this.EquipHead00 = this.EquipHeadBase.getChild("EquipHead00");
        this.EquipHead01 = this.EquipHeadBase.getChild("EquipHead01");
        this.EquipHead02 = this.EquipHead01.getChild("EquipHead02");
        this.EquipHead03 = this.EquipHead02.getChild("EquipHead03");
        this.EquipHead01_1 = this.EquipHeadBase.getChild("EquipHead01_1");
        this.EquipHead02_1 = this.EquipHead01_1.getChild("EquipHead02_1");
        this.EquipHead03_1 = this.EquipHead02_1.getChild("EquipHead03_1");
        this.EquipGlass01 = this.Head.getChild("EquipGlass01");
        this.EquipGlass02a = this.EquipGlass01.getChild("EquipGlass02a");
        this.EquipGlass02b = this.EquipGlass01.getChild("EquipGlass02b");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.Butt = this.BodyMain.getChild("Butt");
        this.Cloth02b1 = this.Butt.getChild("Cloth02b1");
        this.Cloth02b2 = this.Cloth02b1.getChild("Cloth02b2");
        this.Cloth02b3 = this.Cloth02b2.getChild("Cloth02b3");
        this.SkirtB01 = this.Butt.getChild("SkirtB01");
        this.Cloth02c1 = this.SkirtB01.getChild("Cloth02c1");
        this.Cloth02c2 = this.Cloth02c1.getChild("Cloth02c2");
        this.Cloth02c3 = this.Cloth02c2.getChild("Cloth02c3");
        this.Cloth02c4 = this.Cloth02c3.getChild("Cloth02c4");
        this.Cloth02c1_1 = this.SkirtB01.getChild("Cloth02c1_1");
        this.Cloth02c2_1 = this.Cloth02c1_1.getChild("Cloth02c2_1");
        this.Cloth02c3_1 = this.Cloth02c2_1.getChild("Cloth02c3_1");
        this.Cloth02c4_1 = this.Cloth02c3_1.getChild("Cloth02c4_1");
        this.Cloth01a = this.SkirtB01.getChild("Cloth01a");
        this.Cloth01b = this.Cloth01a.getChild("Cloth01b");
        this.Cloth01b2 = this.Cloth01a.getChild("Cloth01b2");
        this.Cloth01c = this.Cloth01a.getChild("Cloth01c");
        this.Cloth01c2 = this.Cloth01a.getChild("Cloth01c2");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.Cloth02a1 = this.Butt.getChild("Cloth02a1");
        this.Cloth02a2 = this.Cloth02a1.getChild("Cloth02a2");
        this.Cloth02a3 = this.Cloth02a2.getChild("Cloth02a3");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Cloth03b_1 = this.BoobL.getChild("Cloth03b_1");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ClothA01 = this.ArmLeft01.getChild("ClothA01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ClothA02 = this.ArmLeft02.getChild("ClothA02");
        this.ClothA03 = this.ClothA02.getChild("ClothA03");
        this.ClothA04 = this.ClothA03.getChild("ClothA04");
        this.ClothA05 = this.ClothA04.getChild("ClothA05");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.skirt01DefaultY = this.Skirt01.y;
        this.skirt01DefaultZ = this.Skirt01.z;
        this.clothA03DefaultX = this.ClothA03.x;
        this.clothA03DefaultY = this.ClothA03.y;
        this.clothA03DefaultZ = this.ClothA03.z;
        this.clothA04DefaultY = this.ClothA04.y;
        this.clothA04DefaultZ = this.ClothA04.z;
        this.clothA05DefaultY = this.ClothA05.y;
        this.clothA05DefaultZ = this.ClothA05.z;
        this.clothA03aDefaultX = this.ClothA03a.x;
        this.clothA03aDefaultY = this.ClothA03a.y;
        this.clothA03aDefaultZ = this.ClothA03a.z;
        this.clothA04aDefaultY = this.ClothA04a.y;
        this.clothA04aDefaultZ = this.ClothA04a.z;
        this.clothA05aDefaultY = this.ClothA05a.y;
        this.clothA05aDefaultZ = this.ClothA05a.z;
        this.armLeft02DefaultX = this.ArmLeft02.x;
        this.armLeft02DefaultZ = this.ArmLeft02.z;
        this.armRight02DefaultX = this.ArmRight02.x;
        this.armRight02DefaultZ = this.ArmRight02.z;
        this.legLeft01DefaultY = this.LegLeft01.y;
        this.legLeft01DefaultZ = this.LegLeft01.z;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight01DefaultY = this.LegRight01.y;
        this.legRight01DefaultZ = this.LegRight01.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.equipGlass01DefaultZ = this.EquipGlass01.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 39).addBox(-3.5F, 0F, 0F, 7F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -8.2F, -3.8F, -0.8727F, -0.0873F, -0.0698F));

        PartDefinition ClothB01 = BoobR.addOrReplaceChild("ClothB01", CubeListBuilder.create().texOffs(25, 37).addBox(-4.5F, 0F, 0F, 9F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.9F, 4.6F, 1.6F, 0.9599F, -0.0068F, 0.0948F));

        PartDefinition Cloth03b = BoobR.addOrReplaceChild("Cloth03b", CubeListBuilder.create().texOffs(161, 80).mirror().addBox(-1F, 0F, 0F, 2F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.6F, -0.8F, -0.1F, 0F, 0F, 0.0873F));

        PartDefinition Cloth03a1 = BodyMain.addOrReplaceChild("Cloth03a1", CubeListBuilder.create().texOffs(159, 55).addBox(-1F, 0F, 0F, 2F, 18F, 7F, new CubeDeformation(0F)), PartPose.offset(4.1F, -11.1F, -4.1F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 71).addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, -0.0873F, 0F, 0.3142F));

        PartDefinition ClothA01_1 = ArmRight01.addOrReplaceChild("ClothA01_1", CubeListBuilder.create().texOffs(128, 109).addBox(-3F, 0F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.5F, 5.1F, 0F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 54).addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition ClothA02a = ArmRight02.addOrReplaceChild("ClothA02a", CubeListBuilder.create().texOffs(128, 49).addBox(-3F, 0F, -3F, 6F, 9F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.5F, -0.1F, -2.5F, 0F, 0.0128F, 0F));

        PartDefinition ClothA03a = ClothA02a.addOrReplaceChild("ClothA03a", CubeListBuilder.create().texOffs(128, 65).addBox(-2.5F, 0F, 0F, 5F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.1F, 1.9F, -2.2F));

        PartDefinition ClothA04a = ClothA03a.addOrReplaceChild("ClothA04a", CubeListBuilder.create().texOffs(128, 81).addBox(-2F, 0F, 0F, 4F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.9F, 0.8F));

        PartDefinition ClothA05a = ClothA04a.addOrReplaceChild("ClothA05a", CubeListBuilder.create().texOffs(128, 96).addBox(-1.5F, 0F, 0F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, 0.8F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 7.5F, 5.5F));

        PartDefinition EquipD01a = EquipBase.addOrReplaceChild("EquipD01a", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 8F, 10F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, 0.0698F, 0F, 0F));

        PartDefinition EquipD01b = EquipD01a.addOrReplaceChild("EquipD01b", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 8F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.4F, 5.9F));

        PartDefinition EquipD01ba = EquipD01b.addOrReplaceChild("EquipD01ba", CubeListBuilder.create().texOffs(22, 22).addBox(-1.5F, 0F, 0F, 3F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 1F, 6F));

        PartDefinition EquipD01bb = EquipD01b.addOrReplaceChild("EquipD01bb", CubeListBuilder.create().texOffs(22, 22).addBox(-1.5F, 0F, 0F, 3F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 4.9F, 6F));

        PartDefinition EquipD03a1 = EquipD01a.addOrReplaceChild("EquipD03a1", CubeListBuilder.create().texOffs(107, 13).addBox(0.5F, -1F, -2.5F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, 5.8F, 3.5F, 0F, 0F, 0.5236F));

        PartDefinition EquipD03ab = EquipD03a1.addOrReplaceChild("EquipD03ab", CubeListBuilder.create().texOffs(100, 30).addBox(0F, 0F, 0F, 9F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -1.5F, 6.4F));

        PartDefinition EquipD03aa = EquipD03a1.addOrReplaceChild("EquipD03aa", CubeListBuilder.create().texOffs(100, 30).addBox(0F, 0F, 0F, 9F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -1.5F, -3.4F));

        PartDefinition EquipD03a2 = EquipD03a1.addOrReplaceChild("EquipD03a2", CubeListBuilder.create().texOffs(107, 13).addBox(0F, 0F, 0F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(6.4F, -1F, -2.5F));

        PartDefinition EquipD03a3 = EquipD03a2.addOrReplaceChild("EquipD03a3", CubeListBuilder.create().texOffs(107, 13).addBox(0F, 0F, 0F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(5.9F, 0F, 0F));

        PartDefinition EquipD03a4 = EquipD03a3.addOrReplaceChild("EquipD03a4", CubeListBuilder.create().texOffs(107, 13).addBox(0F, 0F, 0F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(5.9F, 0F, 0F));

        PartDefinition EquipB05 = EquipD03a4.addOrReplaceChild("EquipB05", CubeListBuilder.create().texOffs(100, 30).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(1.5F, -0.4F, 4.5F));

        PartDefinition EquipCL1Base01L2 = EquipB05.addOrReplaceChild("EquipCL1Base01L2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0.2F, 0.1F, 0F));

        PartDefinition EquipCL1a1_1 = EquipCL1Base01L2.addOrReplaceChild("EquipCL1a1_1", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.1698F, 0F, 0F));

        PartDefinition EquipCL1a2_1 = EquipCL1a1_1.addOrReplaceChild("EquipCL1a2_1", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1Base02 = EquipCL1Base01L2.addOrReplaceChild("EquipCL1Base02", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1a1 = EquipCL1Base01L2.addOrReplaceChild("EquipCL1a1", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.182F, 0F, 0F));

        PartDefinition EquipCL1a2 = EquipCL1a1.addOrReplaceChild("EquipCL1a2", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1Base01a = EquipCL1Base01L2.addOrReplaceChild("EquipCL1Base01a", CubeListBuilder.create().texOffs(0, 9).addBox(-2.5F, 0F, 0F, 5F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.4F, -1F, -0.0873F, 0F, 0F));

        PartDefinition EquipD03c1 = EquipD01a.addOrReplaceChild("EquipD03c1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1.5F, 0F, 8F, 1F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, 5.5F, 4F, 0F, 0F, -0.3491F));

        PartDefinition EquipD03c1b = EquipD03c1.addOrReplaceChild("EquipD03c1b", CubeListBuilder.create().texOffs(100, 30).addBox(-0.5F, 0F, -0.5F, 2F, 2F, 1F, new CubeDeformation(0F)), PartPose.offset(7.2F, -1F, 1.5F));

        PartDefinition EquipD03c2 = EquipD03c1.addOrReplaceChild("EquipD03c2", CubeListBuilder.create().texOffs(100, 30).addBox(0F, -1.5F, -1.5F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, -0.2F, 1.5F, 0F, 0F, -0.2618F));

        PartDefinition EquipD03c3 = EquipD03c2.addOrReplaceChild("EquipD03c3", CubeListBuilder.create().texOffs(100, 30).addBox(0F, -1.5F, -1.5F, 4F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.9F, 0F, 0F, 0F, 0F, 0.6109F));

        PartDefinition EquipB05_2 = EquipD03c3.addOrReplaceChild("EquipB05_2", CubeListBuilder.create().texOffs(100, 30).addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(6.3F, -2F, 0F));

        PartDefinition EquipCL1Base01L1 = EquipB05_2.addOrReplaceChild("EquipCL1Base01L1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0.2F, 0.1F, 0F));

        PartDefinition EquipCL1Base01b = EquipCL1Base01L1.addOrReplaceChild("EquipCL1Base01b", CubeListBuilder.create().texOffs(109, 24).addBox(-5.5F, 0F, 0F, 11F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -5.6F, 2F));

        PartDefinition EquipCL1a1_4 = EquipCL1Base01L1.addOrReplaceChild("EquipCL1a1_4", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.182F, 0F, 0F));

        PartDefinition EquipCL1a2_4 = EquipCL1a1_4.addOrReplaceChild("EquipCL1a2_4", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1a1_5 = EquipCL1Base01L1.addOrReplaceChild("EquipCL1a1_5", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.1698F, 0F, 0F));

        PartDefinition EquipCL1a2_5 = EquipCL1a1_5.addOrReplaceChild("EquipCL1a2_5", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1Base02_2 = EquipCL1Base01L1.addOrReplaceChild("EquipCL1Base02_2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1Base01a_2 = EquipCL1Base01L1.addOrReplaceChild("EquipCL1Base01a_2", CubeListBuilder.create().texOffs(0, 9).addBox(-2.5F, 0F, 0F, 5F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.4F, -1F, -0.0873F, 0F, 0F));

        PartDefinition EquipD03c3a = EquipD03c3.addOrReplaceChild("EquipD03c3a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(2.3F, -1.4F, -4.5F));

        PartDefinition EquipD03c2a = EquipD03c2.addOrReplaceChild("EquipD03c2a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1.5F, -4.5F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(1.5F, 0.1F, 0F));

        PartDefinition EquipD03c1a = EquipD03c1.addOrReplaceChild("EquipD03c1a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 8F, 1F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, 0F));

        PartDefinition EquipD02c = EquipD01a.addOrReplaceChild("EquipD02c", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0F, 0F, 6F, 12F, 4F, new CubeDeformation(0F)), PartPose.offset(3.3F, 5F, 5.9F));

        PartDefinition EquipD02a = EquipD01a.addOrReplaceChild("EquipD02a", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0F, -0.6F, 7F, 14F, 5F, new CubeDeformation(0F)), PartPose.offset(3.4F, 5F, 1.7F));

        PartDefinition EquipD03d1 = EquipD01a.addOrReplaceChild("EquipD03d1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1.5F, 0F, 8F, 1F, 3F, new CubeDeformation(0F)), PartPose.offset(-5F, 5.5F, 7F));

        PartDefinition EquipD03c1a_1 = EquipD03d1.addOrReplaceChild("EquipD03c1a_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 8F, 1F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, 0F));

        PartDefinition EquipD03c1b_1 = EquipD03d1.addOrReplaceChild("EquipD03c1b_1", CubeListBuilder.create().texOffs(100, 30).addBox(-0.5F, 0F, -0.5F, 2F, 2F, 1F, new CubeDeformation(0F)), PartPose.offset(7.2F, -1F, 1.5F));

        PartDefinition EquipD03c2_1 = EquipD03d1.addOrReplaceChild("EquipD03c2_1", CubeListBuilder.create().texOffs(100, 30).addBox(0F, -1.5F, -1.5F, 3F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, -0.2F, 1.5F, 0F, 0F, -0.2618F));

        PartDefinition EquipD03c2a_1 = EquipD03c2_1.addOrReplaceChild("EquipD03c2a_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -1.5F, -4.5F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(1.5F, 0.1F, 0F));

        PartDefinition EquipD03c3_1 = EquipD03c2_1.addOrReplaceChild("EquipD03c3_1", CubeListBuilder.create().texOffs(100, 30).addBox(0F, -1.5F, -1.5F, 4F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.9F, 0F, 0F, 0F, 0F, 0.6109F));

        PartDefinition EquipD03c3a_1 = EquipD03c3_1.addOrReplaceChild("EquipD03c3a_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 8F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(2.3F, -1.4F, -4.5F));

        PartDefinition EquipB05_3 = EquipD03c3_1.addOrReplaceChild("EquipB05_3", CubeListBuilder.create().texOffs(100, 30).addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(6.3F, -2F, 0F));

        PartDefinition EquipCL1Base01R1 = EquipB05_3.addOrReplaceChild("EquipCL1Base01R1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0.2F, 0.1F, 0F));

        PartDefinition EquipCL1Base01a_3 = EquipCL1Base01R1.addOrReplaceChild("EquipCL1Base01a_3", CubeListBuilder.create().texOffs(0, 9).addBox(-2.5F, 0F, 0F, 5F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.4F, -1F, -0.0873F, 0F, 0F));

        PartDefinition EquipCL1a1_7 = EquipCL1Base01R1.addOrReplaceChild("EquipCL1a1_7", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.1698F, 0F, 0F));

        PartDefinition EquipCL1a2_7 = EquipCL1a1_7.addOrReplaceChild("EquipCL1a2_7", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1Base02_3 = EquipCL1Base01R1.addOrReplaceChild("EquipCL1Base02_3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1Base01b_1 = EquipCL1Base01R1.addOrReplaceChild("EquipCL1Base01b_1", CubeListBuilder.create().texOffs(109, 24).addBox(-5.5F, 0F, 0F, 11F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -5.6F, 2F));

        PartDefinition EquipCL1a1_6 = EquipCL1Base01R1.addOrReplaceChild("EquipCL1a1_6", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.182F, 0F, 0F));

        PartDefinition EquipCL1a2_6 = EquipCL1a1_6.addOrReplaceChild("EquipCL1a2_6", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipD02b = EquipD01a.addOrReplaceChild("EquipD02b", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -0.6F, 7F, 14F, 5F, new CubeDeformation(0F)), PartPose.offset(-3.4F, 5F, 1.7F));

        PartDefinition EquipD01aa = EquipD01a.addOrReplaceChild("EquipD01aa", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, 0F, 5F, 5F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9.5F, -4F, 0.2269F, 0F, 0F));

        PartDefinition EquipD03b1 = EquipD01a.addOrReplaceChild("EquipD03b1", CubeListBuilder.create().texOffs(107, 13).addBox(0.5F, -1F, -2.5F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(-5F, 5.8F, 7.5F));

        PartDefinition EquipD03aa_1 = EquipD03b1.addOrReplaceChild("EquipD03aa_1", CubeListBuilder.create().texOffs(100, 30).addBox(0F, 0F, 0F, 9F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -1.5F, -3.4F));

        PartDefinition EquipD03a2_1 = EquipD03b1.addOrReplaceChild("EquipD03a2_1", CubeListBuilder.create().texOffs(107, 13).addBox(0F, 0F, 0F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(6.4F, -1F, -2.5F));

        PartDefinition EquipD03a3_1 = EquipD03a2_1.addOrReplaceChild("EquipD03a3_1", CubeListBuilder.create().texOffs(107, 13).addBox(0F, 0F, 0F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(5.9F, 0F, 0F));

        PartDefinition EquipD03a4_1 = EquipD03a3_1.addOrReplaceChild("EquipD03a4_1", CubeListBuilder.create().texOffs(107, 13).addBox(0F, 0F, 0F, 6F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(5.9F, 0F, 0F));

        PartDefinition EquipB05_1 = EquipD03a4_1.addOrReplaceChild("EquipB05_1", CubeListBuilder.create().texOffs(100, 30).addBox(-3.5F, 0F, -3.5F, 7F, 3F, 7F, new CubeDeformation(0F)), PartPose.offset(1.5F, -0.4F, 4.5F));

        PartDefinition EquipCL1Base01R2 = EquipB05_1.addOrReplaceChild("EquipCL1Base01R2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0.2F, 0.1F, 0F));

        PartDefinition EquipCL1Base02_1 = EquipCL1Base01R2.addOrReplaceChild("EquipCL1Base02_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1a1_3 = EquipCL1Base01R2.addOrReplaceChild("EquipCL1a1_3", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.1698F, 0F, 0F));

        PartDefinition EquipCL1a2_3 = EquipCL1a1_3.addOrReplaceChild("EquipCL1a2_3", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1Base01a_1 = EquipCL1Base01R2.addOrReplaceChild("EquipCL1Base01a_1", CubeListBuilder.create().texOffs(0, 9).addBox(-2.5F, 0F, 0F, 5F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.4F, -1F, -0.0873F, 0F, 0F));

        PartDefinition EquipCL1a1_2 = EquipCL1Base01R2.addOrReplaceChild("EquipCL1a1_2", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.182F, 0F, 0F));

        PartDefinition EquipCL1a2_2 = EquipCL1a1_2.addOrReplaceChild("EquipCL1a2_2", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipD03ab_1 = EquipD03b1.addOrReplaceChild("EquipD03ab_1", CubeListBuilder.create().texOffs(100, 30).addBox(0F, 0F, 0F, 9F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -1.5F, 6.4F));

        PartDefinition EquipD02d = EquipD01a.addOrReplaceChild("EquipD02d", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 6F, 12F, 4F, new CubeDeformation(0F)), PartPose.offset(-5.9F, 5F, 5.9F));

        PartDefinition Cloth03a2 = BodyMain.addOrReplaceChild("Cloth03a2", CubeListBuilder.create().texOffs(159, 55).mirror().addBox(-1F, 0F, 0F, 2F, 18F, 7F, new CubeDeformation(0F)), PartPose.offset(-4.1F, -11.1F, -4.1F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(24, 63).addBox(-2.5F, -3F, -2.9F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9.6F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.4F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition EquipHeadBase = Head.addOrReplaceChild("EquipHeadBase", CubeListBuilder.create().texOffs(40, 23).addBox(-8F, 0F, 7F, 16F, 2F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.8F, -7.6F));

        PartDefinition EquipHead00 = EquipHeadBase.addOrReplaceChild("EquipHead00", CubeListBuilder.create().texOffs(44, 16).addBox(-8.5F, 0F, 0F, 17F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.1F, 5F, 0.1047F, 0F, 0F));

        PartDefinition EquipHead01 = EquipHeadBase.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(43, 105).addBox(0F, -0.7F, -0.3F, 2F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(6.7F, 0.2F, 5.7F));

        PartDefinition EquipHead02 = EquipHead01.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(45, 106).addBox(-0.5F, -0.5F, -0.5F, 1F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(2.4F, 0.8F, 1.2F));

        PartDefinition EquipHead03 = EquipHead02.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(33, 105).addBox(0F, 0F, 0F, 5F, 3F, 0F, new CubeDeformation(0F)), PartPose.offset(0.2F, -1.5F, 0F));

        PartDefinition EquipHead01_1 = EquipHeadBase.addOrReplaceChild("EquipHead01_1", CubeListBuilder.create().texOffs(43, 105).addBox(-2F, -0.7F, -0.3F, 2F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(-6.7F, 0.2F, 5.7F));

        PartDefinition EquipHead02_1 = EquipHead01_1.addOrReplaceChild("EquipHead02_1", CubeListBuilder.create().texOffs(43, 107).addBox(-0.5F, -0.5F, -0.5F, 1F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(-2.4F, 0.8F, 1.2F));

        PartDefinition EquipHead03_1 = EquipHead02_1.addOrReplaceChild("EquipHead03_1", CubeListBuilder.create().texOffs(33, 105).addBox(-5F, 0F, 0F, 5F, 3F, 0F, new CubeDeformation(0F)), PartPose.offset(-0.2F, -1.5F, 0F));

        PartDefinition EquipGlass01 = Head.addOrReplaceChild("EquipGlass01", CubeListBuilder.create().texOffs(90, 0).addBox(-8F, 0F, 0F, 16F, 5F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -8.1F, -8.4F));

        PartDefinition EquipGlass02a = EquipGlass01.addOrReplaceChild("EquipGlass02a", CubeListBuilder.create().texOffs(90, 5).addBox(-0.5F, 0F, 0F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(7.8F, 2.1F, -0.2F));

        PartDefinition EquipGlass02b = EquipGlass01.addOrReplaceChild("EquipGlass02b", CubeListBuilder.create().texOffs(90, 5).addBox(-0.5F, 0F, 0F, 1F, 1F, 10F, new CubeDeformation(0F)), PartPose.offset(-7.8F, 2.1F, -0.2F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(50, 39).addBox(-8F, 0F, 0F, 16F, 8F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7.7F, 1.2F, 0.1047F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(118, 42).addBox(-1.5F, 0F, 0F, 5F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3F, -5.1F, -7.5F, -0.1396F, -0.1745F, 0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 88).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition Cloth02b1 = Butt.addOrReplaceChild("Cloth02b1", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 2.3F, -6.8F, -0.4363F, 0F, 0.0698F));

        PartDefinition Cloth02b2 = Cloth02b1.addOrReplaceChild("Cloth02b2", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.9F, 0F, 0.1745F, 0F, -0.0524F));

        PartDefinition Cloth02b3 = Cloth02b2.addOrReplaceChild("Cloth02b3", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0F, 0F, -0.0524F));

        PartDefinition SkirtB01 = Butt.addOrReplaceChild("SkirtB01", CubeListBuilder.create().texOffs(128, 36).addBox(-8F, 0F, -4.5F, 16F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -1.9F, 0.0873F, 0F, 0F));

        PartDefinition Cloth02c1 = SkirtB01.addOrReplaceChild("Cloth02c1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.6F, 1.9F, 4.4F, 0.6283F, 0F, -0.0873F));

        PartDefinition Cloth02c2 = Cloth02c1.addOrReplaceChild("Cloth02c2", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 0F, -0.7854F, 0F, 0F));

        PartDefinition Cloth02c3 = Cloth02c2.addOrReplaceChild("Cloth02c3", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition Cloth02c4 = Cloth02c3.addOrReplaceChild("Cloth02c4", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 7.9F, 0F));

        PartDefinition Cloth02c1_1 = SkirtB01.addOrReplaceChild("Cloth02c1_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, 1.9F, 4.4F, 0.6283F, 0F, 0.0873F));

        PartDefinition Cloth02c2_1 = Cloth02c1_1.addOrReplaceChild("Cloth02c2_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 0F, -0.7854F, 0F, 0F));

        PartDefinition Cloth02c3_1 = Cloth02c2_1.addOrReplaceChild("Cloth02c3_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition Cloth02c4_1 = Cloth02c3_1.addOrReplaceChild("Cloth02c4_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 7.9F, 0F));

        PartDefinition Cloth01a = SkirtB01.addOrReplaceChild("Cloth01a", CubeListBuilder.create().texOffs(81, 0).addBox(-1F, -2.5F, -1F, 2F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.3F, -5F, -0.2618F, 0F, 0F));

        PartDefinition Cloth01b = Cloth01a.addOrReplaceChild("Cloth01b", CubeListBuilder.create().texOffs(65, 0).addBox(-6F, -3F, -1F, 6F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 0.3F, 0.0873F, -0.1745F, -0.3491F));

        PartDefinition Cloth01b2 = Cloth01a.addOrReplaceChild("Cloth01b2", CubeListBuilder.create().texOffs(65, 0).addBox(0F, -3F, -1F, 6F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 0.3F, 0.0873F, 0.1745F, 0.3491F));

        PartDefinition Cloth01c = Cloth01a.addOrReplaceChild("Cloth01c", CubeListBuilder.create().texOffs(73, 5).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -0.4F, -0.7F, -0.2618F, 0.1396F, 0.1745F));

        PartDefinition Cloth01c2 = Cloth01a.addOrReplaceChild("Cloth01c2", CubeListBuilder.create().texOffs(73, 5).mirror().addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -0.4F, -0.7F, -0.2618F, -0.1396F, -0.1745F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 68).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2967F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 47).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 68).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.192F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition Cloth02a1 = Butt.addOrReplaceChild("Cloth02a1", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 2.3F, -6.8F, -0.4363F, 0F, -0.0698F));

        PartDefinition Cloth02a2 = Cloth02a1.addOrReplaceChild("Cloth02a2", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.9F, 0F, 0.2443F, 0F, 0.0524F));

        PartDefinition Cloth02a3 = Cloth02a2.addOrReplaceChild("Cloth02a3", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0F, 0F, 0.0524F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(128, 0).addBox(-8.5F, 0F, -8.5F, 17F, 5F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, 1.5F, -0.0873F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(128, 17).addBox(-9.5F, 0F, -6.5F, 19F, 5F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, -2.7F, -0.0873F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(25, 44).addBox(-3.5F, 0F, 0F, 7F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -8.2F, -3.7F, -0.8727F, 0.0873F, 0.0698F));

        PartDefinition Cloth03b_1 = BoobL.addOrReplaceChild("Cloth03b_1", CubeListBuilder.create().texOffs(161, 80).addBox(-1F, 0F, 0F, 2F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.6F, -0.8F, -0.1F, 0F, 0F, -0.0873F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 71).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.2731F, 0F, -0.3142F));

        PartDefinition ClothA01 = ArmLeft01.addOrReplaceChild("ClothA01", CubeListBuilder.create().texOffs(128, 109).addBox(-3F, 0F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0.5F, 5.1F, 0F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 54).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition ClothA02 = ArmLeft02.addOrReplaceChild("ClothA02", CubeListBuilder.create().texOffs(128, 49).addBox(-3F, 0F, -3F, 6F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(-2.5F, -0.1F, -2.5F));

        PartDefinition ClothA03 = ClothA02.addOrReplaceChild("ClothA03", CubeListBuilder.create().texOffs(128, 65).addBox(-2.5F, 0F, 0F, 5F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(0.1F, 1.9F, -2.2F));

        PartDefinition ClothA04 = ClothA03.addOrReplaceChild("ClothA04", CubeListBuilder.create().texOffs(128, 81).addBox(-2F, 0F, 0F, 4F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.9F, 0.8F));

        PartDefinition ClothA05 = ClothA04.addOrReplaceChild("ClothA05", CubeListBuilder.create().texOffs(128, 96).addBox(-1.5F, 0F, 0F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, 0.8F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 104), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(24, 63), PartPose.offsetAndRotation(0F, -9.6F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(44, 101), PartPose.offset(0F, -1F, -0.7F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        resetOffsets();

        applyFaceAndMouth(entity);
        setFlushVisible(entity != null && (entity.getEmotionPrimary() == EntityShipBase.EMOTION_SHY || entity.getEmotionPrimary() == EntityShipBase.EMOTION_HAPPY));
        applyEquipVisibility();

        if (isDeadPose(entity)) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyBasePose(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks, limbSwing, limbSwingAmount);
        syncGlowParts();
    }

    private void resetOffsets() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;

        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.Skirt01.y = this.skirt01DefaultY;
        this.Skirt01.z = this.skirt01DefaultZ;
        this.ClothA03.x = this.clothA03DefaultX;
        this.ClothA03.y = this.clothA03DefaultY;
        this.ClothA03.z = this.clothA03DefaultZ;
        this.ClothA04.y = this.clothA04DefaultY;
        this.ClothA04.z = this.clothA04DefaultZ;
        this.ClothA05.y = this.clothA05DefaultY;
        this.ClothA05.z = this.clothA05DefaultZ;
        this.ClothA03a.x = this.clothA03aDefaultX;
        this.ClothA03a.y = this.clothA03aDefaultY;
        this.ClothA03a.z = this.clothA03aDefaultZ;
        this.ClothA04a.y = this.clothA04aDefaultY;
        this.ClothA04a.z = this.clothA04aDefaultZ;
        this.ClothA05a.y = this.clothA05aDefaultY;
        this.ClothA05a.z = this.clothA05aDefaultZ;
        this.ArmLeft02.x = this.armLeft02DefaultX;
        this.ArmLeft02.z = this.armLeft02DefaultZ;
        this.ArmRight02.x = this.armRight02DefaultX;
        this.ArmRight02.z = this.armRight02DefaultZ;
        this.LegLeft01.y = this.legLeft01DefaultY;
        this.LegLeft01.z = this.legLeft01DefaultZ;
        this.LegLeft02.x = this.legLeft02DefaultX;
        this.LegLeft02.y = this.legLeft02DefaultY;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight01.y = this.legRight01DefaultY;
        this.LegRight01.z = this.legRight01DefaultZ;
        this.LegRight02.x = this.legRight02DefaultX;
        this.LegRight02.y = this.legRight02DefaultY;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.EquipGlass01.z = this.equipGlass01DefaultZ;
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyEquipVisibility() {
        this.EquipBase.visible = true;
        this.EquipHeadBase.visible = true;
        this.EquipGlass01.visible = true;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.xRot = 0.0F;
        this.Head.yRot = 0.0F;
        this.Head.zRot = 0.0F;
        this.BodyMain.xRot = 1.4F;
        this.Butt.xRot = 0.21F;
        this.BoobL.xRot = -0.8F;
        this.BoobR.xRot = -0.8F;
        this.ClothB01.xRot = 0.96F;
        this.Skirt01.xRot = -0.087F;
        this.Skirt02.xRot = -0.087F;
        this.SkirtB01.xRot = 0.087F;
        this.ClothA03.yRot = 0.0F;
        this.ClothA03a.yRot = 0.0F;
        this.Cloth02a1.xRot = -0.5585F;
        this.Cloth02b1.xRot = -0.5585F;
        this.Cloth02c1.xRot = 0.6283F;
        this.Cloth02c1_1.xRot = 0.6283F;
        this.Cloth02c2.xRot = -0.7854F;
        this.Cloth02c2_1.xRot = -0.7854F;
        this.Cloth02c3.xRot = -0.1396F;
        this.Cloth02c3_1.xRot = -0.1396F;
        this.Cloth02c4.xRot = 0.0F;
        this.Cloth02c4_1.xRot = 0.0F;
        this.Cloth02a2.xRot = 0.1745F;
        this.Cloth02b2.xRot = 0.1745F;
        this.Cloth02a3.xRot = 0.0F;
        this.Cloth02b3.xRot = 0.0F;
        this.Ahoke.zRot = 0.087F;
        this.ArmLeft01.xRot = -2.8F;
        this.ArmLeft01.yRot = 0.1F;
        this.ArmLeft01.zRot = 0.84F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.zRot = 1.0F;
        this.ArmRight01.xRot = 0.0F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = 0.2F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;
        this.LegLeft01.xRot = -0.12F;
        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = -0.05F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;
        this.LegRight01.xRot = -0.12F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.26F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = -0.4F;
    }

    private void applyBasePose(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleX = net.minecraft.util.Mth.cos(ageInTicks * 0.08F + limbSwing * 0.25F);
        float angleX1 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.35F + limbSwing * 0.5F);
        float angleX2 = net.minecraft.util.Mth.cos(ageInTicks * 0.1F + 0.7F + limbSwing * 0.5F);
        float angleAdd1 = net.minecraft.util.Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = net.minecraft.util.Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;

        this.Head.xRot = headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Ahoke.zRot = angleX * 0.08F + 0.05F;

        this.BoobL.xRot = angleX * 0.06F - 0.8F;
        this.BoobR.xRot = angleX * 0.06F - 0.8F;
        this.ClothB01.xRot = 0.96F - angleX * 0.08F;
        this.EquipGlass01.z = this.equipGlass01DefaultZ + (0.06F * OFFSET_SCALE);

        this.BodyMain.xRot = -0.1047F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = 0.35F;
        this.Skirt01.xRot = -0.087F;
        this.Skirt02.xRot = -0.087F;
        this.ClothA03.yRot = 0.0F;
        this.ClothA03a.yRot = 0.0F;
        this.SkirtB01.xRot = 0.087F;

        this.Cloth02a1.xRot = -0.5585F;
        this.Cloth02b1.xRot = -0.5585F;
        this.Cloth02c1.xRot = 0.6283F;
        this.Cloth02c1_1.xRot = 0.6283F;
        this.Cloth02c2.xRot = -0.7854F;
        this.Cloth02c2_1.xRot = -0.7854F;
        this.Cloth02c3.xRot = -0.1396F + angleX1 * 0.06F;
        this.Cloth02c3_1.xRot = -0.1396F + angleX1 * 0.06F;
        this.Cloth02c4.xRot = -angleX2 * 0.06F;
        this.Cloth02c4_1.xRot = -angleX2 * 0.06F;
        this.Cloth02a2.xRot = 0.12F + angleX1 * 0.06F;
        this.Cloth02b2.xRot = 0.12F + angleX1 * 0.06F;
        this.Cloth02a3.xRot = -angleX2 * 0.06F;
        this.Cloth02b3.xRot = -angleX2 * 0.06F;

        this.ArmLeft01.xRot = angleAdd2 * 0.25F + 0.3F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = angleX * 0.03F - 0.25F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;

        this.ArmRight01.xRot = angleAdd1 * 0.25F - 0.087F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -angleX * 0.03F + 0.25F;
        this.ArmRight02.xRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.0873F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.yRot = 0.0F;
        this.LegLeft02.zRot = 0.0F;

        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.0873F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.yRot = 0.0F;
        this.LegRight02.zRot = 0.0F;

        this.EquipCL1a1.xRot = this.Head.xRot * 0.8F - 0.21F;
        this.EquipCL1a1_1.xRot = this.Head.xRot * 0.7F - 0.23F;
        this.EquipCL1a1_2.xRot = this.Head.xRot * 0.85F - 0.2F;
        this.EquipCL1a1_3.xRot = this.Head.xRot * 0.75F - 0.25F;
        this.EquipCL1a1_4.xRot = this.Head.xRot * 0.8F - 0.2F;
        this.EquipCL1a1_5.xRot = this.Head.xRot * 0.85F - 0.19F;
        this.EquipCL1a1_6.xRot = this.Head.xRot * 0.75F - 0.21F;
        this.EquipCL1a1_7.xRot = this.Head.xRot * 0.88F - 0.19F;
        this.EquipD03c1.zRot = -0.35F + this.Head.xRot * 0.5F;
        this.EquipD03c2.zRot = -0.26F + this.Head.xRot * 0.5F;
        this.EquipD03c3.zRot = 0.61F - this.Head.xRot;
        this.EquipD03d1.zRot = -this.EquipD03c1.zRot;
        this.EquipD03c2_1.zRot = this.EquipD03c2.zRot;
        this.EquipD03c3_1.zRot = this.EquipD03c3.zRot;
        this.EquipD03a1.zRot = 0.52F + this.Head.xRot * 0.5F;
        this.EquipD03b1.zRot = -this.EquipD03a1.zRot;
        this.EquipCL1Base01L1.yRot = this.Head.yRot * 0.75F;
        this.EquipCL1Base01L2.yRot = this.Head.yRot * 0.75F;
        this.EquipCL1Base01R1.yRot = this.Head.yRot * 0.75F;
        this.EquipCL1Base01R2.yRot = this.Head.yRot * 0.75F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks, float limbSwing, float limbSwingAmount) {
        if (entity == null) return;

        float angleX = ctx.angleX;
        float addk1 = ctx.angleAdd1 * 0.3F - 0.28F;
        float addk2 = ctx.angleAdd2 * 0.3F - 0.21F;
        float addCA031 = 0.0F;
        float addCA032 = 0.0F;

        boolean isPassenger = entity.isPassenger();
        boolean isCrouching = entity.isCrouching();
        boolean isSprinting = entity.isSprinting();
        boolean isSitting = entity.getIsSitting() || isPassenger;

        if (entity.getShipDepth() > 0.0) {
            this.poseTranslateY += (Mth.cos(ageInTicks * 0.08F + limbSwing * 0.25F) * 0.05F + 0.025F);
        }

        if (isSprinting || limbSwingAmount > 0.9F) {
            if ((entity.tickCount % 256) > 128) {
                //applyFace(entity, 3);
                //applyMouth(entity, 5);
            }
            BodyMain.xRot = 0.2F;
            Skirt01.xRot = -0.4F;
            Skirt02.xRot = -0.1F;
            SkirtB01.xRot = -0.13F;
            Cloth02c1.xRot = 1.17F;
            Cloth02c1_1.xRot = 1.17F;
            Cloth02c2.xRot = -0.63F;
            Cloth02c2_1.xRot = -0.63F;
            ArmLeft01.xRot = ctx.angleAdd2 * 1.2F + 0.5F;
            ArmRight01.xRot = ctx.angleAdd1 * 1.2F + 0.5F;
            ArmLeft02.xRot = -1.0F;
            ArmRight02.xRot = -1.0F;
            addk1 = ctx.angleAdd1 * 0.7F - 0.48F;
            addk2 = ctx.angleAdd2 * 0.7F - 0.41F;
            LegLeft01.zRot = 0.0873F;
            LegRight01.zRot = -0.0873F;
        }

        //Head.zRot = entity.getHeadTiltAngle(ctx.partialTicks);

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            Head.xRot -= 0.6283F;
            BodyMain.xRot = 0.8727F;
            Skirt01.xRot = -0.34F;
            Skirt01.y = skirt01DefaultY + (-0.2F * OFFSET_SCALE);
            Skirt01.z = skirt01DefaultZ + (0.03F * OFFSET_SCALE);
            Skirt02.xRot = -0.27F;
            Cloth02a1.xRot = -1.23F;
            Cloth02b1.xRot = -1.23F;
            Cloth02c2.xRot -= 0.35F;
            Cloth02c2_1.xRot -= 0.35F;
            ArmLeft01.xRot = -0.35F;
            ArmLeft01.zRot = 0.2618F;
            ArmRight01.xRot = -0.35F;
            ArmRight01.zRot = -0.2618F;
            addk1 -= 0.94F;
            addk2 -= 0.94F;
            LegLeft01.zRot = 0.2F;
            LegRight01.zRot = -0.2F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            int sitTick = entity.tickCount % 512;
            if (sitTick > 256) {
                this.poseTranslateY += 0.55F * 3;
                //applyFaceScorn(entity);
                Head.xRot += 0.1F;
                BodyMain.xRot = -0.1F;
                Butt.xRot = -0.4F;
                Butt.z = buttDefaultZ + (0.19F * OFFSET_SCALE);
                Skirt01.xRot = -0.35F;
                Skirt02.xRot = -0.19F;
                Cloth02a1.xRot = 0.2F;
                Cloth02b1.xRot = 0.2F;
                Cloth02c1.xRot = 1.5F;
                Cloth02c2.xRot = 0.35F;
                Cloth02c3.xRot = 0.05F;
                Cloth02c4.xRot = 0.0F;
                Cloth02c1_1.xRot = 1.5F;
                Cloth02c2_1.xRot = 0.35F;
                Cloth02c3_1.xRot = 0.05F;
                Cloth02c4_1.xRot = 0.0F;
                ClothA03.yRot = 0.2F;
                ClothA03a.yRot = -0.2F;
                ArmLeft01.xRot = -1.18F;
                ArmLeft01.yRot = 0.27F;
                ArmLeft01.zRot = -0.1F;
                ArmLeft02.zRot = 0.92F;
                ArmRight01.xRot = -1.18F;
                ArmRight01.yRot = -0.27F;
                ArmRight01.zRot = 0.1F;
                ArmRight02.zRot = -1.32F;
                addk1 = -2.57F;
                addk2 = -2.57F;
                LegLeft01.y = legLeft01DefaultY + (0.25F * OFFSET_SCALE);
                LegLeft01.z = legLeft01DefaultZ + (-0.2F * OFFSET_SCALE);
                LegLeft01.yRot = 0.11F;
                LegLeft01.zRot = -0.12F;
                LegLeft02.xRot = 2.75F;
                LegLeft02.zRot = 0.02F;
                LegLeft02.z = legLeft02DefaultZ + (0.37F * OFFSET_SCALE);
                LegRight01.y = legRight01DefaultY + (0.25F * OFFSET_SCALE);
                LegRight01.z = legRight01DefaultZ + (-0.2F * OFFSET_SCALE);
                LegRight01.yRot = -0.11F;
                LegRight01.zRot = 0.12F;
                LegRight02.xRot = 2.75F;
                LegRight02.zRot = -0.02F;
                LegRight02.z = legRight02DefaultZ + (0.37F * OFFSET_SCALE);
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                Head.xRot += 0.14F;
                BodyMain.xRot = -0.4363F;
                Skirt01.xRot = -0.35F;
                Skirt02.xRot = -0.19F;
                SkirtB01.xRot = -0.12F;
                Cloth02a2.xRot += 0.32F;
                Cloth02a3.xRot += 0.4F;
                Cloth02b2.xRot += 0.32F;
                Cloth02b3.xRot += 0.4F;
                Cloth02c1.xRot += 0.45F;
                Cloth02c2.xRot += 0.1F;
                Cloth02c1_1.xRot += 0.45F;
                Cloth02c2_1.xRot += 0.1F;
                ClothA03.yRot = 1.49F;
                ClothA03a.yRot = -1.33F;
                ArmLeft01.xRot = -0.3142F;
                ArmLeft01.zRot = 0.349F;
                ArmLeft02.zRot = 1.15F;
                ArmRight01.xRot = -0.4363F;
                ArmRight01.zRot = -0.2793F;
                ArmRight02.zRot = -1.4F;
                addk1 = -1.309F;
                addk2 = -1.7F;
                LegLeft01.yRot = 0.3142F;
                LegLeft02.xRot = 1.0472F;
                LegRight01.yRot = -0.35F;
                LegRight01.zRot = -0.2618F;
                LegRight02.xRot = 0.9F;
            }
        }

		/*boolean isAttacking = entity.getAttackTick() > 20;
		if (isAttacking) {
			applyFace(entity, 3);
			applyMouth(entity, 5);
			BodyMain.xRot = -0.17F;
			ArmLeft01.xRot = 0.17F; ArmLeft01.yRot = 0.0F; ArmLeft01.zRot = -0.35F;
			ArmLeft02.xRot = -1.57F; ArmLeft02.yRot = 0.0F; ArmLeft02.zRot = 0.0F;
			ArmRight01.xRot = 0.17F; ArmRight01.yRot = 0.0F; ArmRight01.zRot = 0.35F;
			ArmRight02.xRot = -1.57F; ArmRight02.yRot = 0.0F; ArmRight02.zRot = 0.0F;
			addk1 += 0.14F; addk2 += 0.07F;
			LegLeft01.yRot = 0.0F; LegLeft01.zRot = -0.17F;
			LegRight01.yRot = 0.0F; LegRight01.zRot = 0.17F;
		}*/

		/*if (!isSprinting && !isCrouching && !isSitting && !isAttacking) {
			BodyMain.xRot = -0.17F;
			ArmLeft01.xRot = 0.17F; ArmLeft01.zRot = -0.35F;
			ArmLeft02.xRot = -1.57F;
			ArmRight01.xRot = 0.17F; ArmRight01.zRot = 0.35F;
			ArmRight02.xRot = -1.57F;
			addk1 += 0.14F; addk2 += 0.07F;
			LegLeft01.zRot = -0.17F;
			LegRight01.zRot = 0.17F;
			if (entity.getStateEmotion(7) == 4) {
				applyFace(entity, 3);
				applyMouth(entity, 5);
			}
		}

		float swingProgress = entity.getSwingTime(ctx.partialTicks);
		if (swingProgress != 0.0F) {
			float f7 = Mth.sin(swingProgress * swingProgress * (float)Math.PI);
			float f8 = Mth.sin(Mth.sqrt(swingProgress) * (float)Math.PI);
			ArmRight01.xRot = -0.4F;
			ArmRight01.yRot = 0.2F;
			ArmRight01.zRot = -0.2F;
			ArmRight01.xRot += -f8 * 80.0F * ((float)Math.PI / 180F);
			ArmRight01.yRot += -f7 * 20.0F * ((float)Math.PI / 180F);
			ArmRight01.zRot += -f8 * 20.0F * ((float)Math.PI / 180F);
		}*/

        float handL = BodyMain.xRot + ArmLeft01.xRot + ArmLeft02.xRot;
        float handR = BodyMain.xRot + ArmRight01.xRot + ArmRight02.xRot;
        float handLc = Mth.cos(handL);
        float handLs = Mth.sin(handL);
        float handRc = Mth.cos(handR);
        float handRs = Mth.sin(handR);

        ClothA03.y = clothA03DefaultY + (handLc * 0.1F + addCA031) * OFFSET_SCALE;
        ClothA04.y = clothA04DefaultY + (handLc * 0.2F) * OFFSET_SCALE;
        ClothA05.y = clothA05DefaultY + (handLc * 0.25F) * OFFSET_SCALE;
        ClothA03.z = clothA03DefaultZ + (handLs * -0.32F + addCA032) * OFFSET_SCALE;
        ClothA04.z = clothA04DefaultZ + (handLs * -0.32F) * OFFSET_SCALE;
        ClothA05.z = clothA05DefaultZ + (handLs * -0.32F) * OFFSET_SCALE;

        ClothA03a.y = clothA03aDefaultY + (handRc * 0.1F - addCA031) * OFFSET_SCALE;
        ClothA04a.y = clothA04aDefaultY + (handRc * 0.2F) * OFFSET_SCALE;
        ClothA05a.y = clothA05aDefaultY + (handRc * 0.25F) * OFFSET_SCALE;
        ClothA03a.z = clothA03aDefaultZ + (handRs * -0.32F + addCA032) * OFFSET_SCALE;
        ClothA04a.z = clothA04aDefaultZ + (handRs * -0.32F) * OFFSET_SCALE;
        ClothA05a.z = clothA05aDefaultZ + (handRs * -0.32F) * OFFSET_SCALE;

        LegLeft01.xRot = addk1;
        LegRight01.xRot = addk2;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.copyFrom(this.BodyMain);
            this.GlowNeck.copyFrom(this.Neck);
            this.GlowHead.copyFrom(this.Head);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        if (this.GlowBodyMain != null) {
            this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
