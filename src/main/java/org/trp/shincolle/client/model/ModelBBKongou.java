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

public class ModelBBKongou<T extends EntityShipBase> extends ShipModelHumanoidBase<T> implements IGlowableModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "bb_kongou"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBBKongou");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBBKongou");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBBKongou");
    private static final float SITTING_ALT_TRANSLATE_Y = LegacyPoseOffsets.sittingAltY("ModelBBKongou");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart Butt;
    private final ModelPart Ahoke00;
    private final ModelPart ArmLeft01;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart ArmRight01;
    private final ModelPart EquipBase;
    private final ModelPart Cloth03a1;
    private final ModelPart Cloth03a2;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Ahoke01;
    private final ModelPart EquipHeadBase;
    private final ModelPart HairU01;
    private final ModelPart HairR01;
    private final ModelPart HairL01;
    private final ModelPart HairCBase;
    private final ModelPart HairCBaseB;
    private final ModelPart HairS01;
    private final ModelPart HairS02;
    private final ModelPart HairR02;
    private final ModelPart HairL02;
    private final ModelPart HairC01;
    private final ModelPart HairC02;
    private final ModelPart HairC03;
    private final ModelPart HairC04;
    private final ModelPart HairC05;
    private final ModelPart HairC01b;
    private final ModelPart HairC02b;
    private final ModelPart HairC03b;
    private final ModelPart HairC04b;
    private final ModelPart HairC05b;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Ahoke02;
    private final ModelPart Ahoke03;
    private final ModelPart Ahoke04;
    private final ModelPart EquipHead01;
    private final ModelPart EquipHead01a;
    private final ModelPart EquipHead02;
    private final ModelPart EquipHead03;
    private final ModelPart EquipHead02a;
    private final ModelPart EquipHead03a;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart SkirtB01;
    private final ModelPart LegLeft02;
    private final ModelPart Skirt02;
    private final ModelPart LegRight02;
    private final ModelPart Cloth01a;
    private final ModelPart Cloth02a1;
    private final ModelPart Cloth02b1;
    private final ModelPart Cloth02c1;
    private final ModelPart Cloth02c1_1;
    private final ModelPart Cloth01b;
    private final ModelPart Cloth01c;
    private final ModelPart Cloth01b2;
    private final ModelPart Cloth01c2;
    private final ModelPart Cloth02a2;
    private final ModelPart Cloth02a3;
    private final ModelPart Cloth02b2;
    private final ModelPart Cloth02b3;
    private final ModelPart Cloth02c2;
    private final ModelPart Cloth02c3;
    private final ModelPart Cloth02c4;
    private final ModelPart Cloth02c2_1;
    private final ModelPart Cloth02c3_1;
    private final ModelPart Cloth02c4_1;
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
    private final ModelPart EquipB01;
    private final ModelPart EquipB00a;
    private final ModelPart EquipB00a_1;
    private final ModelPart EquipB02;
    private final ModelPart EquipB01a;
    private final ModelPart EquipB01b00;
    private final ModelPart EquipB04;
    private final ModelPart EquipB04_1;
    private final ModelPart EquipB03;
    private final ModelPart EquipB02a;
    private final ModelPart EquipB01c;
    private final ModelPart EquipB01b01a;
    private final ModelPart EquipB01b01b;
    private final ModelPart EquipB01b01c;
    private final ModelPart EquipB01b02;
    private final ModelPart EquipB01b03;
    private final ModelPart EquipB01b04;
    private final ModelPart EquipB01b05;
    private final ModelPart EquipB01b06;
    private final ModelPart EquipB05;
    private final ModelPart EquipB06a;
    private final ModelPart EquipB06b;
    private final ModelPart EquipB06c;
    private final ModelPart EquipB06d;
    private final ModelPart EquipB06e;
    private final ModelPart EquipB06f;
    private final ModelPart EquipCL1Base01;
    private final ModelPart EquipCL1Base02;
    private final ModelPart EquipCL1a1;
    private final ModelPart EquipCL1a1_1;
    private final ModelPart EquipCL1a2;
    private final ModelPart EquipCL1a2_1;
    private final ModelPart EquipB05_1;
    private final ModelPart EquipB07a1;
    private final ModelPart EquipB07b1;
    private final ModelPart EquipB07c1;
    private final ModelPart EquipB07d1;
    private final ModelPart EquipCL1Base01_1;
    private final ModelPart EquipCL1Base02_1;
    private final ModelPart EquipCL1a1_2;
    private final ModelPart EquipCL1a1_3;
    private final ModelPart EquipCL1a2_2;
    private final ModelPart EquipCL1a2_3;
    private final ModelPart EquipB07a2;
    private final ModelPart EquipB07b2;
    private final ModelPart EquipB07c2;
    private final ModelPart EquipB07d2;
    private final ModelPart EquipB07d3;
    private final ModelPart EquipB05_2;
    private final ModelPart EquipB06a_1;
    private final ModelPart EquipB06b_1;
    private final ModelPart EquipB06c_1;
    private final ModelPart EquipB06d_1;
    private final ModelPart EquipB06e_1;
    private final ModelPart EquipB06f_1;
    private final ModelPart EquipCL1Base01_2;
    private final ModelPart EquipCL1Base02_2;
    private final ModelPart EquipCL1a1_4;
    private final ModelPart EquipCL1a1_5;
    private final ModelPart EquipCL1a2_4;
    private final ModelPart EquipCL1a2_5;
    private final ModelPart EquipB05_3;
    private final ModelPart EquipB07a1_1;
    private final ModelPart EquipB07b1_1;
    private final ModelPart EquipB07c1_1;
    private final ModelPart EquipB07d1_1;
    private final ModelPart EquipCL1Base01_3;
    private final ModelPart EquipCL1Base02_3;
    private final ModelPart EquipCL1a1_6;
    private final ModelPart EquipCL1a1_7;
    private final ModelPart EquipCL1a2_6;
    private final ModelPart EquipCL1a2_7;
    private final ModelPart EquipB07a2_1;
    private final ModelPart EquipB07b2_1;
    private final ModelPart EquipB07c2_1;
    private final ModelPart EquipB07d2_1;
    private final ModelPart EquipB07d3_1;
    private final ModelPart EquipB00b;
    private final ModelPart EquipB00c;
    private final ModelPart EquipB00d;
    private final ModelPart EquipB00b_1;
    private final ModelPart EquipB00c_1;
    private final ModelPart EquipB00d_1;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
    private final ModelPart GlowNeck;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultZ;
    private final float legLeft01DefaultY;
    private final float legLeft01DefaultZ;
    private final float legLeft02DefaultX;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight01DefaultY;
    private final float legRight01DefaultZ;
    private final float legRight02DefaultX;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;
    private final float clothA03DefaultY;
    private final float clothA03DefaultZ;
    private final float clothA04DefaultY;
    private final float clothA04DefaultZ;
    private final float clothA05DefaultY;
    private final float clothA05DefaultZ;
    private final float clothA03aDefaultY;
    private final float clothA03aDefaultZ;
    private final float clothA04aDefaultY;
    private final float clothA04aDefaultZ;
    private final float clothA05aDefaultY;
    private final float clothA05aDefaultZ;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float skirt01DefaultY;
    private final float skirt01DefaultZ;

    public ModelBBKongou(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.Cloth03b_1 = this.BoobL.getChild("Cloth03b_1");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ClothA02a = this.ArmRight02.getChild("ClothA02a");
        this.ClothA03a = this.ClothA02a.getChild("ClothA03a");
        this.ClothA04a = this.ClothA03a.getChild("ClothA04a");
        this.ClothA05a = this.ClothA04a.getChild("ClothA05a");
        this.ClothA01_1 = this.ArmRight01.getChild("ClothA01_1");
        this.Cloth03a1 = this.BodyMain.getChild("Cloth03a1");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.EquipB01 = this.EquipBase.getChild("EquipB01");
        this.EquipB01b00 = this.EquipB01.getChild("EquipB01b00");
        this.EquipB01b01b = this.EquipB01b00.getChild("EquipB01b01b");
        this.EquipB01b02 = this.EquipB01b00.getChild("EquipB01b02");
        this.EquipB01b01a = this.EquipB01b00.getChild("EquipB01b01a");
        this.EquipB01b05 = this.EquipB01b00.getChild("EquipB01b05");
        this.EquipB01b06 = this.EquipB01b00.getChild("EquipB01b06");
        this.EquipB01b04 = this.EquipB01b00.getChild("EquipB01b04");
        this.EquipB01b01c = this.EquipB01b00.getChild("EquipB01b01c");
        this.EquipB01b03 = this.EquipB01b00.getChild("EquipB01b03");
        this.EquipB01a = this.EquipB01.getChild("EquipB01a");
        this.EquipB01c = this.EquipB01a.getChild("EquipB01c");
        this.EquipB02 = this.EquipB01.getChild("EquipB02");
        this.EquipB02a = this.EquipB02.getChild("EquipB02a");
        this.EquipB03 = this.EquipB02.getChild("EquipB03");
        this.EquipB04_1 = this.EquipB01.getChild("EquipB04_1");
        this.EquipB06b_1 = this.EquipB04_1.getChild("EquipB06b_1");
        this.EquipB05_2 = this.EquipB04_1.getChild("EquipB05_2");
        this.EquipCL1Base01_2 = this.EquipB05_2.getChild("EquipCL1Base01_2");
        this.EquipCL1a1_4 = this.EquipCL1Base01_2.getChild("EquipCL1a1_4");
        this.EquipCL1a2_4 = this.EquipCL1a1_4.getChild("EquipCL1a2_4");
        this.EquipCL1Base02_2 = this.EquipCL1Base01_2.getChild("EquipCL1Base02_2");
        this.EquipCL1a1_5 = this.EquipCL1Base01_2.getChild("EquipCL1a1_5");
        this.EquipCL1a2_5 = this.EquipCL1a1_5.getChild("EquipCL1a2_5");
        this.EquipB06e_1 = this.EquipB04_1.getChild("EquipB06e_1");
        this.EquipB06d_1 = this.EquipB04_1.getChild("EquipB06d_1");
        this.EquipB05_3 = this.EquipB06d_1.getChild("EquipB05_3");
        this.EquipCL1Base01_3 = this.EquipB05_3.getChild("EquipCL1Base01_3");
        this.EquipCL1Base02_3 = this.EquipCL1Base01_3.getChild("EquipCL1Base02_3");
        this.EquipCL1a1_7 = this.EquipCL1Base01_3.getChild("EquipCL1a1_7");
        this.EquipCL1a2_7 = this.EquipCL1a1_7.getChild("EquipCL1a2_7");
        this.EquipCL1a1_6 = this.EquipCL1Base01_3.getChild("EquipCL1a1_6");
        this.EquipCL1a2_6 = this.EquipCL1a1_6.getChild("EquipCL1a2_6");
        this.EquipB07a1_1 = this.EquipB06d_1.getChild("EquipB07a1_1");
        this.EquipB07a2_1 = this.EquipB07a1_1.getChild("EquipB07a2_1");
        this.EquipB07d1_1 = this.EquipB06d_1.getChild("EquipB07d1_1");
        this.EquipB07d3_1 = this.EquipB07d1_1.getChild("EquipB07d3_1");
        this.EquipB07d2_1 = this.EquipB07d1_1.getChild("EquipB07d2_1");
        this.EquipB07c1_1 = this.EquipB06d_1.getChild("EquipB07c1_1");
        this.EquipB07c2_1 = this.EquipB07c1_1.getChild("EquipB07c2_1");
        this.EquipB07b1_1 = this.EquipB06d_1.getChild("EquipB07b1_1");
        this.EquipB07b2_1 = this.EquipB07b1_1.getChild("EquipB07b2_1");
        this.EquipB06a_1 = this.EquipB04_1.getChild("EquipB06a_1");
        this.EquipB06f_1 = this.EquipB04_1.getChild("EquipB06f_1");
        this.EquipB06c_1 = this.EquipB04_1.getChild("EquipB06c_1");
        this.EquipB04 = this.EquipB01.getChild("EquipB04");
        this.EquipB05 = this.EquipB04.getChild("EquipB05");
        this.EquipCL1Base01 = this.EquipB05.getChild("EquipCL1Base01");
        this.EquipCL1Base02 = this.EquipCL1Base01.getChild("EquipCL1Base02");
        this.EquipCL1a1_1 = this.EquipCL1Base01.getChild("EquipCL1a1_1");
        this.EquipCL1a2_1 = this.EquipCL1a1_1.getChild("EquipCL1a2_1");
        this.EquipCL1a1 = this.EquipCL1Base01.getChild("EquipCL1a1");
        this.EquipCL1a2 = this.EquipCL1a1.getChild("EquipCL1a2");
        this.EquipB06e = this.EquipB04.getChild("EquipB06e");
        this.EquipB06d = this.EquipB04.getChild("EquipB06d");
        this.EquipB05_1 = this.EquipB06d.getChild("EquipB05_1");
        this.EquipCL1Base01_1 = this.EquipB05_1.getChild("EquipCL1Base01_1");
        this.EquipCL1a1_2 = this.EquipCL1Base01_1.getChild("EquipCL1a1_2");
        this.EquipCL1a2_2 = this.EquipCL1a1_2.getChild("EquipCL1a2_2");
        this.EquipCL1Base02_1 = this.EquipCL1Base01_1.getChild("EquipCL1Base02_1");
        this.EquipCL1a1_3 = this.EquipCL1Base01_1.getChild("EquipCL1a1_3");
        this.EquipCL1a2_3 = this.EquipCL1a1_3.getChild("EquipCL1a2_3");
        this.EquipB07c1 = this.EquipB06d.getChild("EquipB07c1");
        this.EquipB07c2 = this.EquipB07c1.getChild("EquipB07c2");
        this.EquipB07d1 = this.EquipB06d.getChild("EquipB07d1");
        this.EquipB07d3 = this.EquipB07d1.getChild("EquipB07d3");
        this.EquipB07d2 = this.EquipB07d1.getChild("EquipB07d2");
        this.EquipB07b1 = this.EquipB06d.getChild("EquipB07b1");
        this.EquipB07b2 = this.EquipB07b1.getChild("EquipB07b2");
        this.EquipB07a1 = this.EquipB06d.getChild("EquipB07a1");
        this.EquipB07a2 = this.EquipB07a1.getChild("EquipB07a2");
        this.EquipB06a = this.EquipB04.getChild("EquipB06a");
        this.EquipB06b = this.EquipB04.getChild("EquipB06b");
        this.EquipB06c = this.EquipB04.getChild("EquipB06c");
        this.EquipB06f = this.EquipB04.getChild("EquipB06f");
        this.EquipB00a = this.EquipBase.getChild("EquipB00a");
        this.EquipB00b = this.EquipB00a.getChild("EquipB00b");
        this.EquipB00c = this.EquipB00b.getChild("EquipB00c");
        this.EquipB00d = this.EquipB00c.getChild("EquipB00d");
        this.EquipB00a_1 = this.EquipBase.getChild("EquipB00a_1");
        this.EquipB00b_1 = this.EquipB00a_1.getChild("EquipB00b_1");
        this.EquipB00c_1 = this.EquipB00b_1.getChild("EquipB00c_1");
        this.EquipB00d_1 = this.EquipB00c_1.getChild("EquipB00d_1");
        this.Cloth03a2 = this.BodyMain.getChild("Cloth03a2");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.ClothB01 = this.BoobR.getChild("ClothB01");
        this.Cloth03b = this.BoobR.getChild("Cloth03b");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.SkirtB01 = this.Butt.getChild("SkirtB01");
        this.Cloth02b1 = this.SkirtB01.getChild("Cloth02b1");
        this.Cloth02b2 = this.Cloth02b1.getChild("Cloth02b2");
        this.Cloth02b3 = this.Cloth02b2.getChild("Cloth02b3");
        this.Cloth01a = this.SkirtB01.getChild("Cloth01a");
        this.Cloth01c2 = this.Cloth01a.getChild("Cloth01c2");
        this.Cloth01b2 = this.Cloth01a.getChild("Cloth01b2");
        this.Cloth01c = this.Cloth01a.getChild("Cloth01c");
        this.Cloth01b = this.Cloth01a.getChild("Cloth01b");
        this.Cloth02a1 = this.SkirtB01.getChild("Cloth02a1");
        this.Cloth02a2 = this.Cloth02a1.getChild("Cloth02a2");
        this.Cloth02a3 = this.Cloth02a2.getChild("Cloth02a3");
        this.Cloth02c1 = this.SkirtB01.getChild("Cloth02c1");
        this.Cloth02c2 = this.Cloth02c1.getChild("Cloth02c2");
        this.Cloth02c3 = this.Cloth02c2.getChild("Cloth02c3");
        this.Cloth02c4 = this.Cloth02c3.getChild("Cloth02c4");
        this.Cloth02c1_1 = this.SkirtB01.getChild("Cloth02c1_1");
        this.Cloth02c2_1 = this.Cloth02c1_1.getChild("Cloth02c2_1");
        this.Cloth02c3_1 = this.Cloth02c2_1.getChild("Cloth02c3_1");
        this.Cloth02c4_1 = this.Cloth02c3_1.getChild("Cloth02c4_1");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ClothA02 = this.ArmLeft02.getChild("ClothA02");
        this.ClothA03 = this.ClothA02.getChild("ClothA03");
        this.ClothA04 = this.ClothA03.getChild("ClothA04");
        this.ClothA05 = this.ClothA04.getChild("ClothA05");
        this.ClothA01 = this.ArmLeft01.getChild("ClothA01");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairS01 = this.Hair.getChild("HairS01");
        this.HairCBase = this.Hair.getChild("HairCBase");
        this.HairC01 = this.HairCBase.getChild("HairC01");
        this.HairC02 = this.HairC01.getChild("HairC02");
        this.HairC03 = this.HairC02.getChild("HairC03");
        this.HairC04 = this.HairC03.getChild("HairC04");
        this.HairC05 = this.HairC04.getChild("HairC05");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairCBaseB = this.Hair.getChild("HairCBaseB");
        this.HairC01b = this.HairCBaseB.getChild("HairC01b");
        this.HairC02b = this.HairC01b.getChild("HairC02b");
        this.HairC03b = this.HairC02b.getChild("HairC03b");
        this.HairC04b = this.HairC03b.getChild("HairC04b");
        this.HairC05b = this.HairC04b.getChild("HairC05b");
        this.HairS02 = this.Hair.getChild("HairS02");
        this.EquipHeadBase = this.Head.getChild("EquipHeadBase");
        this.EquipHead01a = this.EquipHeadBase.getChild("EquipHead01a");
        this.EquipHead03a = this.EquipHead01a.getChild("EquipHead03a");
        this.EquipHead02a = this.EquipHead01a.getChild("EquipHead02a");
        this.EquipHead01 = this.EquipHeadBase.getChild("EquipHead01");
        this.EquipHead03 = this.EquipHead01.getChild("EquipHead03");
        this.EquipHead02 = this.EquipHead01.getChild("EquipHead02");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Ahoke00 = this.Head.getChild("Ahoke00");
        this.Ahoke01 = this.Head.getChild("Ahoke01");
        this.Ahoke02 = this.Ahoke01.getChild("Ahoke02");
        this.Ahoke03 = this.Ahoke02.getChild("Ahoke03");
        this.Ahoke04 = this.Ahoke03.getChild("Ahoke04");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
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
        this.clothA03DefaultY = this.ClothA03.y;
        this.clothA03DefaultZ = this.ClothA03.z;
        this.clothA04DefaultY = this.ClothA04.y;
        this.clothA04DefaultZ = this.ClothA04.z;
        this.clothA05DefaultY = this.ClothA05.y;
        this.clothA05DefaultZ = this.ClothA05.z;
        this.clothA03aDefaultY = this.ClothA03a.y;
        this.clothA03aDefaultZ = this.ClothA03a.z;
        this.clothA04aDefaultY = this.ClothA04a.y;
        this.clothA04aDefaultZ = this.ClothA04a.z;
        this.clothA05aDefaultY = this.ClothA05a.y;
        this.clothA05aDefaultZ = this.ClothA05a.z;
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.skirt01DefaultY = this.Skirt01.y;
        this.skirt01DefaultZ = this.Skirt01.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(25, 44).addBox(-3.5F, 0F, 0F, 7F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -8.2F, -3.7F, -0.8727F, 0.0873F, 0.0698F));

        PartDefinition Cloth03b_1 = BoobL.addOrReplaceChild("Cloth03b_1", CubeListBuilder.create().texOffs(161, 80).addBox(-1F, 0F, 0F, 2F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.6F, -0.8F, -0.1F, 0F, 0F, -0.0873F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 71).addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, -0.0873F, 0F, 0.3142F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 54).addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition ClothA02a = ArmRight02.addOrReplaceChild("ClothA02a", CubeListBuilder.create().texOffs(128, 49).addBox(-3F, 0F, -3F, 6F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(2.5F, -0.1F, -2.5F));

        PartDefinition ClothA03a = ClothA02a.addOrReplaceChild("ClothA03a", CubeListBuilder.create().texOffs(128, 65).addBox(-2.5F, 0F, 0F, 5F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.1F, -0.1F, -2.2F));

        PartDefinition ClothA04a = ClothA03a.addOrReplaceChild("ClothA04a", CubeListBuilder.create().texOffs(128, 81).addBox(-2F, 0F, 0F, 4F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.9F, 0.8F));

        PartDefinition ClothA05a = ClothA04a.addOrReplaceChild("ClothA05a", CubeListBuilder.create().texOffs(128, 96).addBox(-1.5F, 0F, 0F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, 0.8F));

        PartDefinition ClothA01_1 = ArmRight01.addOrReplaceChild("ClothA01_1", CubeListBuilder.create().texOffs(128, 109).addBox(-3F, 0F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(-0.5F, 5.1F, 0F));

        PartDefinition Cloth03a1 = BodyMain.addOrReplaceChild("Cloth03a1", CubeListBuilder.create().texOffs(159, 55).addBox(-1F, 0F, 0F, 2F, 18F, 7F, new CubeDeformation(0F)), PartPose.offset(4.1F, -11.1F, -4.1F));

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 6.5F, 9F));

        PartDefinition EquipB01 = EquipBase.addOrReplaceChild("EquipB01", CubeListBuilder.create().texOffs(185, 0).addBox(-5.5F, 0F, 0F, 11F, 10F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, -2F, 0F));

        PartDefinition EquipB01b00 = EquipB01.addOrReplaceChild("EquipB01b00", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 0.1F, 9.8F));

        PartDefinition EquipB01b01b = EquipB01b00.addOrReplaceChild("EquipB01b01b", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, -7.9F, 0F, -0.0873F, 0F, -0.1222F));

        PartDefinition EquipB01b02 = EquipB01b00.addOrReplaceChild("EquipB01b02", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4F, 1F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -8.7F, 0.6F));

        PartDefinition EquipB01b01a = EquipB01b00.addOrReplaceChild("EquipB01b01a", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7.9F, 1.2F, 0.1222F, 0F, 0F));

        PartDefinition EquipB01b05 = EquipB01b00.addOrReplaceChild("EquipB01b05", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, 0F, -0.5F, 1F, 18F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -33.4F, 0.3F));

        PartDefinition EquipB01b06 = EquipB01b00.addOrReplaceChild("EquipB01b06", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, 0F, 0F, 11F, 1F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -29F, -0.1F));

        PartDefinition EquipB01b04 = EquipB01b00.addOrReplaceChild("EquipB01b04", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 1F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, -15.5F, 0.5F));

        PartDefinition EquipB01b01c = EquipB01b00.addOrReplaceChild("EquipB01b01c", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -7.9F, 0F, -0.0873F, 0F, 0.1222F));

        PartDefinition EquipB01b03 = EquipB01b00.addOrReplaceChild("EquipB01b03", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 6F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14.5F, 0.5F));

        PartDefinition EquipB01a = EquipB01.addOrReplaceChild("EquipB01a", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -3.5F, 7F, 4F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -3.9F, 4.8F));

        PartDefinition EquipB01c = EquipB01a.addOrReplaceChild("EquipB01c", CubeListBuilder.create().texOffs(0, 20).addBox(-2.5F, 0F, -2.5F, 5F, 5F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -4.9F, 0.5F));

        PartDefinition EquipB02 = EquipB01.addOrReplaceChild("EquipB02", CubeListBuilder.create().texOffs(226, 0).mirror().addBox(-4.5F, 0F, 0F, 9F, 8F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 8.9F));

        PartDefinition EquipB02a = EquipB02.addOrReplaceChild("EquipB02a", CubeListBuilder.create().texOffs(0, 30).addBox(-2F, 0F, -1F, 4F, 5F, 4F, new CubeDeformation(0F)), PartPose.offset(0F, -4.9F, 4.6F));

        PartDefinition EquipB03 = EquipB02.addOrReplaceChild("EquipB03", CubeListBuilder.create().texOffs(185, 20).mirror().addBox(-3F, 0F, 0F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 4.9F));

        PartDefinition EquipB04_1 = EquipB01.addOrReplaceChild("EquipB04_1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 0F, 5F, 4F, 12F, new CubeDeformation(0F)), PartPose.offset(-5F, -2F, -0.5F));

        PartDefinition EquipB06b_1 = EquipB04_1.addOrReplaceChild("EquipB06b_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, 0F, 3F, 6F, 11F, new CubeDeformation(0F)), PartPose.offset(-11.4F, 4F, 0.5F));

        PartDefinition EquipB05_2 = EquipB04_1.addOrReplaceChild("EquipB05_2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -4.5F, 9F, 8F, 9F, new CubeDeformation(0F)), PartPose.offset(-9.4F, -3.8F, 6F));

        PartDefinition EquipCL1Base01_2 = EquipB05_2.addOrReplaceChild("EquipCL1Base01_2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.1F, 0F, 0F, 1.5708F, 0F));

        PartDefinition EquipCL1a1_4 = EquipCL1Base01_2.addOrReplaceChild("EquipCL1a1_4", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipCL1a2_4 = EquipCL1a1_4.addOrReplaceChild("EquipCL1a2_4", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1Base02_2 = EquipCL1Base01_2.addOrReplaceChild("EquipCL1Base02_2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1a1_5 = EquipCL1Base01_2.addOrReplaceChild("EquipCL1a1_5", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipCL1a2_5 = EquipCL1a1_5.addOrReplaceChild("EquipCL1a2_5", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipB06e_1 = EquipB04_1.addOrReplaceChild("EquipB06e_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 9F, 11F, new CubeDeformation(0F)), PartPose.offset(-25.1F, 4F, 0.5F));

        PartDefinition EquipB06d_1 = EquipB04_1.addOrReplaceChild("EquipB06d_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 0F, 4F, 9F, 11F, new CubeDeformation(0F)), PartPose.offset(-17.2F, 4F, 0.5F));

        PartDefinition EquipB05_3 = EquipB06d_1.addOrReplaceChild("EquipB05_3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -4.5F, 9F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(-4.8F, -1.9F, 5F));

        PartDefinition EquipCL1Base01_3 = EquipB05_3.addOrReplaceChild("EquipCL1Base01_3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 0.1F, 0F));

        PartDefinition EquipCL1Base02_3 = EquipCL1Base01_3.addOrReplaceChild("EquipCL1Base02_3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1a1_7 = EquipCL1Base01_3.addOrReplaceChild("EquipCL1a1_7", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipCL1a2_7 = EquipCL1a1_7.addOrReplaceChild("EquipCL1a2_7", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1a1_6 = EquipCL1Base01_3.addOrReplaceChild("EquipCL1a1_6", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipCL1a2_6 = EquipCL1a1_6.addOrReplaceChild("EquipCL1a2_6", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipB07a1_1 = EquipB06d_1.addOrReplaceChild("EquipB07a1_1", CubeListBuilder.create().texOffs(153, 49).mirror().addBox(-12F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.2F, 8.7F, 12F, -0.2094F, 0.0873F, -0.1222F));

        PartDefinition EquipB07a2_1 = EquipB07a1_1.addOrReplaceChild("EquipB07a2_1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, -2F, -1F, 5F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 0F, 0.5F, 0F, -1.0734F, 0F));

        PartDefinition EquipB07d1_1 = EquipB06d_1.addOrReplaceChild("EquipB07d1_1", CubeListBuilder.create().texOffs(153, 49).mirror().addBox(-12F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, -1.6F, 12.4F, 0F, 0.0873F, 0F));

        PartDefinition EquipB07d3_1 = EquipB07d1_1.addOrReplaceChild("EquipB07d3_1", CubeListBuilder.create().texOffs(51, 0).addBox(-2F, -7F, -0.5F, 2F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.7F, 0F, 0F, 0F, 0.0873F));

        PartDefinition EquipB07d2_1 = EquipB07d1_1.addOrReplaceChild("EquipB07d2_1", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -2F, -1F, 6F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 0F, 0.5F, 0F, -1.0472F, 0F));

        PartDefinition EquipB07c1_1 = EquipB06d_1.addOrReplaceChild("EquipB07c1_1", CubeListBuilder.create().texOffs(153, 49).mirror().addBox(-12F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, 2.4F, 12.4F, 0F, 0.0873F, 0F));

        PartDefinition EquipB07c2_1 = EquipB07c1_1.addOrReplaceChild("EquipB07c2_1", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, -2F, -1F, 7F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 0F, 0.5F, 0F, -1.0472F, 0F));

        PartDefinition EquipB07b1_1 = EquipB06d_1.addOrReplaceChild("EquipB07b1_1", CubeListBuilder.create().texOffs(153, 49).mirror().addBox(-12F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, 5.6F, 12.4F, 0F, 0.0873F, -0.0524F));

        PartDefinition EquipB07b2_1 = EquipB07b1_1.addOrReplaceChild("EquipB07b2_1", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -2F, -1F, 6F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12F, 0F, 0.5F, 0F, -1.0472F, 0F));

        PartDefinition EquipB06a_1 = EquipB04_1.addOrReplaceChild("EquipB06a_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9F, 0F, 0F, 9F, 4F, 11F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 3.9F, 0.5F));

        PartDefinition EquipB06f_1 = EquipB04_1.addOrReplaceChild("EquipB06f_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 2F, 8F, 11F, new CubeDeformation(0F)), PartPose.offset(-27F, 4F, 0.5F));

        PartDefinition EquipB06c_1 = EquipB04_1.addOrReplaceChild("EquipB06c_1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, 0F, 3F, 8F, 11F, new CubeDeformation(0F)), PartPose.offset(-14.3F, 4F, 0.5F));

        PartDefinition EquipB04 = EquipB01.addOrReplaceChild("EquipB04", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 5F, 4F, 12F, new CubeDeformation(0F)), PartPose.offset(5F, -2F, -0.5F));

        PartDefinition EquipB05 = EquipB04.addOrReplaceChild("EquipB05", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -4.5F, 9F, 8F, 9F, new CubeDeformation(0F)), PartPose.offset(9.4F, -3.8F, 6F));

        PartDefinition EquipCL1Base01 = EquipB05.addOrReplaceChild("EquipCL1Base01", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.1F, 0F, 0F, -1.5708F, 0F));

        PartDefinition EquipCL1Base02 = EquipCL1Base01.addOrReplaceChild("EquipCL1Base02", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1a1_1 = EquipCL1Base01.addOrReplaceChild("EquipCL1a1_1", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipCL1a2_1 = EquipCL1a1_1.addOrReplaceChild("EquipCL1a2_1", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1a1 = EquipCL1Base01.addOrReplaceChild("EquipCL1a1", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipCL1a2 = EquipCL1a1.addOrReplaceChild("EquipCL1a2", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipB06e = EquipB04.addOrReplaceChild("EquipB06e", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 9F, 11F, new CubeDeformation(0F)), PartPose.offset(21.1F, 4F, 0.5F));

        PartDefinition EquipB06d = EquipB04.addOrReplaceChild("EquipB06d", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 9F, 11F, new CubeDeformation(0F)), PartPose.offset(17.2F, 4F, 0.5F));

        PartDefinition EquipB05_1 = EquipB06d.addOrReplaceChild("EquipB05_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0F, -4.5F, 9F, 2F, 9F, new CubeDeformation(0F)), PartPose.offset(4.8F, -1.9F, 5F));

        PartDefinition EquipCL1Base01_1 = EquipB05_1.addOrReplaceChild("EquipCL1Base01_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -1.5F, 9F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, 0.1F, 0F));

        PartDefinition EquipCL1a1_2 = EquipCL1Base01_1.addOrReplaceChild("EquipCL1a1_2", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -2.3F, -2.5F, -0.2094F, 0F, 0F));

        PartDefinition EquipCL1a2_2 = EquipCL1a1_2.addOrReplaceChild("EquipCL1a2_2", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipCL1Base02_1 = EquipCL1Base01_1.addOrReplaceChild("EquipCL1Base02_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4F, -2F, 9F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.8F, 0.1745F, 0F, 0F));

        PartDefinition EquipCL1a1_3 = EquipCL1Base01_1.addOrReplaceChild("EquipCL1a1_3", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, -1.5F, -5F, 3F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -2.3F, -2.5F, -0.1698F, 0F, 0F));

        PartDefinition EquipCL1a2_3 = EquipCL1a1_3.addOrReplaceChild("EquipCL1a2_3", CubeListBuilder.create().texOffs(151, 67).addBox(-1F, 0F, -1F, 2F, 11F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -5F, -1.5708F, 0F, 0F));

        PartDefinition EquipB07c1 = EquipB06d.addOrReplaceChild("EquipB07c1", CubeListBuilder.create().texOffs(153, 49).addBox(0F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, 2.4F, 12.4F, 0F, -0.0873F, 0F));

        PartDefinition EquipB07c2 = EquipB07c1.addOrReplaceChild("EquipB07c2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -2F, -1F, 7F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 0F, 0.5F, 0F, 1.0472F, 0F));

        PartDefinition EquipB07d1 = EquipB06d.addOrReplaceChild("EquipB07d1", CubeListBuilder.create().texOffs(153, 49).addBox(0F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -1.6F, 12.4F, 0F, -0.0873F, 0F));

        PartDefinition EquipB07d3 = EquipB07d1.addOrReplaceChild("EquipB07d3", CubeListBuilder.create().texOffs(51, 0).addBox(0F, -7F, -0.5F, 2F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.7F, 0F, 0F, 0F, -0.0873F));

        PartDefinition EquipB07d2 = EquipB07d1.addOrReplaceChild("EquipB07d2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -2F, -1F, 6F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 0F, 0.5F, 0F, 1.0472F, 0F));

        PartDefinition EquipB07b1 = EquipB06d.addOrReplaceChild("EquipB07b1", CubeListBuilder.create().texOffs(153, 49).addBox(0F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, 5.6F, 12.4F, 0F, -0.0873F, 0.0524F));

        PartDefinition EquipB07b2 = EquipB07b1.addOrReplaceChild("EquipB07b2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -2F, -1F, 6F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 0F, 0.5F, 0F, 1.0472F, 0F));

        PartDefinition EquipB07a1 = EquipB06d.addOrReplaceChild("EquipB07a1", CubeListBuilder.create().texOffs(153, 49).addBox(0F, -2F, -0.5F, 12F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.2F, 8.7F, 12F, -0.2094F, -0.0873F, 0.1222F));

        PartDefinition EquipB07a2 = EquipB07a1.addOrReplaceChild("EquipB07a2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -2F, -1F, 5F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 0F, 0.5F, 0F, 1.0734F, 0F));

        PartDefinition EquipB06a = EquipB04.addOrReplaceChild("EquipB06a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 9F, 4F, 11F, new CubeDeformation(0F)), PartPose.offset(2.5F, 3.9F, 0.5F));

        PartDefinition EquipB06b = EquipB04.addOrReplaceChild("EquipB06b", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 3F, 6F, 11F, new CubeDeformation(0F)), PartPose.offset(11.4F, 4F, 0.5F));

        PartDefinition EquipB06c = EquipB04.addOrReplaceChild("EquipB06c", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 3F, 8F, 11F, new CubeDeformation(0F)), PartPose.offset(14.3F, 4F, 0.5F));

        PartDefinition EquipB06f = EquipB04.addOrReplaceChild("EquipB06f", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 2F, 8F, 11F, new CubeDeformation(0F)), PartPose.offset(25F, 4F, 0.5F));

        PartDefinition EquipB00a = EquipBase.addOrReplaceChild("EquipB00a", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 6F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.2F, -5.5F, -1F, 0F, 0.2618F, 0F));

        PartDefinition EquipB00b = EquipB00a.addOrReplaceChild("EquipB00b", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -2F, 8F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -0.1F, 2F, 0F, 1.309F, 0F));

        PartDefinition EquipB00c = EquipB00b.addOrReplaceChild("EquipB00c", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.7F, 2F, -0.3F, 0F, 0F, 0.6109F));

        PartDefinition EquipB00d = EquipB00c.addOrReplaceChild("EquipB00d", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -4F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 1.1F));

        PartDefinition EquipB00a_1 = EquipBase.addOrReplaceChild("EquipB00a_1", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, 0F, 6F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.2F, -5.5F, -1F, 0F, -0.2618F, 0F));

        PartDefinition EquipB00b_1 = EquipB00a_1.addOrReplaceChild("EquipB00b_1", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, -2F, 8F, 4F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -0.1F, 2F, 0F, -1.309F, 0F));

        PartDefinition EquipB00c_1 = EquipB00b_1.addOrReplaceChild("EquipB00c_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -1F, 0F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.7F, 2F, -0.3F, 0F, 0F, -0.6109F));

        PartDefinition EquipB00d_1 = EquipB00c_1.addOrReplaceChild("EquipB00d_1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -4F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 1.1F));

        PartDefinition Cloth03a2 = BodyMain.addOrReplaceChild("Cloth03a2", CubeListBuilder.create().texOffs(159, 55).mirror().addBox(-1F, 0F, 0F, 2F, 18F, 7F, new CubeDeformation(0F)), PartPose.offset(-4.1F, -11.1F, -4.1F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 39).addBox(-3.5F, 0F, 0F, 7F, 4F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -8.2F, -3.8F, -0.8727F, -0.0873F, -0.0698F));

        PartDefinition ClothB01 = BoobR.addOrReplaceChild("ClothB01", CubeListBuilder.create().texOffs(25, 37).addBox(-4.5F, 0F, 0F, 9F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.9F, 4.6F, 1.6F, 0.9599F, -0.0068F, 0.0948F));

        PartDefinition Cloth03b = BoobR.addOrReplaceChild("Cloth03b", CubeListBuilder.create().texOffs(161, 80).mirror().addBox(-1F, 0F, 0F, 2F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.6F, -0.8F, -0.1F, 0F, 0F, 0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 88).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 68).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.192F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 68).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2967F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 47).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition SkirtB01 = Butt.addOrReplaceChild("SkirtB01", CubeListBuilder.create().texOffs(128, 36).addBox(-8F, 0F, -4.5F, 16F, 2F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, -1.9F, 0.0873F, 0F, 0F));

        PartDefinition Cloth02b1 = SkirtB01.addOrReplaceChild("Cloth02b1", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 1.8F, -4.9F, -0.5585F, 0F, 0.0698F));

        PartDefinition Cloth02b2 = Cloth02b1.addOrReplaceChild("Cloth02b2", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.9F, 0F, 0.1745F, 0F, -0.0524F));

        PartDefinition Cloth02b3 = Cloth02b2.addOrReplaceChild("Cloth02b3", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0F, 0F, -0.0524F));

        PartDefinition Cloth01a = SkirtB01.addOrReplaceChild("Cloth01a", CubeListBuilder.create().texOffs(81, 0).addBox(-1F, -2.5F, -1F, 2F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.3F, -5F, -0.2618F, 0F, 0F));

        PartDefinition Cloth01c2 = Cloth01a.addOrReplaceChild("Cloth01c2", CubeListBuilder.create().texOffs(73, 5).mirror().addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, -0.4F, -0.7F, -0.2618F, -0.1396F, -0.1745F));

        PartDefinition Cloth01b2 = Cloth01a.addOrReplaceChild("Cloth01b2", CubeListBuilder.create().texOffs(65, 0).addBox(0F, -3F, -1F, 6F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 0.3F, 0.0873F, 0.1745F, 0.3491F));

        PartDefinition Cloth01c = Cloth01a.addOrReplaceChild("Cloth01c", CubeListBuilder.create().texOffs(73, 5).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -0.4F, -0.7F, -0.2618F, 0.1396F, 0.1745F));

        PartDefinition Cloth01b = Cloth01a.addOrReplaceChild("Cloth01b", CubeListBuilder.create().texOffs(65, 0).addBox(-6F, -3F, -1F, 6F, 3F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 0.3F, 0.0873F, -0.1745F, -0.3491F));

        PartDefinition Cloth02a1 = SkirtB01.addOrReplaceChild("Cloth02a1", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 1.8F, -4.9F, -0.5585F, 0F, -0.0698F));

        PartDefinition Cloth02a2 = Cloth02a1.addOrReplaceChild("Cloth02a2", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.9F, 0F, 0.1745F, 0F, 0.0524F));

        PartDefinition Cloth02a3 = Cloth02a2.addOrReplaceChild("Cloth02a3", CubeListBuilder.create().texOffs(59, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.9F, 0F, 0F, 0F, 0.0524F));

        PartDefinition Cloth02c1 = SkirtB01.addOrReplaceChild("Cloth02c1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.6F, 1.9F, 4.4F, 0.6283F, 0F, -0.0873F));

        PartDefinition Cloth02c2 = Cloth02c1.addOrReplaceChild("Cloth02c2", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 0F, -0.7854F, 0F, 0F));

        PartDefinition Cloth02c3 = Cloth02c2.addOrReplaceChild("Cloth02c3", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition Cloth02c4 = Cloth02c3.addOrReplaceChild("Cloth02c4", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 7.9F, 0F));

        PartDefinition Cloth02c1_1 = SkirtB01.addOrReplaceChild("Cloth02c1_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, 1.9F, 4.4F, 0.6283F, 0F, 0.0873F));

        PartDefinition Cloth02c2_1 = Cloth02c1_1.addOrReplaceChild("Cloth02c2_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 0F, -0.7854F, 0F, 0F));

        PartDefinition Cloth02c3_1 = Cloth02c2_1.addOrReplaceChild("Cloth02c3_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 6.9F, 0F));

        PartDefinition Cloth02c4_1 = Cloth02c3_1.addOrReplaceChild("Cloth02c4_1", CubeListBuilder.create().texOffs(58, 7).addBox(-3.5F, 0F, 0F, 7F, 8F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 7.9F, 0F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(128, 0).addBox(-8.5F, 0F, -8.5F, 17F, 5F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, 1.5F, -0.0873F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(128, 17).addBox(-9.5F, 0F, -6.5F, 19F, 5F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.5F, -2.7F, -0.0873F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 71).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0.1745F, 0F, -0.3142F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 54).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition ClothA02 = ArmLeft02.addOrReplaceChild("ClothA02", CubeListBuilder.create().texOffs(128, 49).addBox(-3F, 0F, -3F, 6F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(-2.5F, -0.1F, -2.5F));

        PartDefinition ClothA03 = ClothA02.addOrReplaceChild("ClothA03", CubeListBuilder.create().texOffs(128, 65).addBox(-2.5F, 0F, 0F, 5F, 9F, 6F, new CubeDeformation(0F)), PartPose.offset(0.1F, -0.1F, -2.2F));

        PartDefinition ClothA04 = ClothA03.addOrReplaceChild("ClothA04", CubeListBuilder.create().texOffs(128, 81).addBox(-2F, 0F, 0F, 4F, 8F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.9F, 0.8F));

        PartDefinition ClothA05 = ClothA04.addOrReplaceChild("ClothA05", CubeListBuilder.create().texOffs(128, 96).addBox(-1.5F, 0F, 0F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 1.9F, 0.8F));

        PartDefinition ClothA01 = ArmLeft01.addOrReplaceChild("ClothA01", CubeListBuilder.create().texOffs(128, 109).addBox(-3F, 0F, -3F, 6F, 6F, 6F, new CubeDeformation(0F)), PartPose.offset(0.5F, 5.1F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(24, 63).addBox(-2.5F, -3F, -2.9F, 5F, 3F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9.6F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition HairS01 = Hair.addOrReplaceChild("HairS01", CubeListBuilder.create().texOffs(110, 22).addBox(-1.5F, -3F, -3F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8.8F, 3.1F, 3.3F, 0F, 0.0524F, 0F));

        PartDefinition HairCBase = Hair.addOrReplaceChild("HairCBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 1F, -1.6F, 0F, 0F, -0.3142F));

        PartDefinition HairC01 = HairCBase.addOrReplaceChild("HairC01", CubeListBuilder.create().texOffs(40, 0).addBox(0F, 0F, 0F, 2F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6F, -7F, 1.3963F, -0.1396F, 0F));

        PartDefinition HairC02 = HairC01.addOrReplaceChild("HairC02", CubeListBuilder.create().texOffs(40, 0).addBox(0F, 0F, 0F, 2F, 10F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.3439F, 0F, 0F));

        PartDefinition HairC03 = HairC02.addOrReplaceChild("HairC03", CubeListBuilder.create().texOffs(40, 0).addBox(0F, 0F, 0F, 2F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 0F, 0.5009F, 0F, -0.8727F));

        PartDefinition HairC04 = HairC03.addOrReplaceChild("HairC04", CubeListBuilder.create().texOffs(40, 0).addBox(0F, 0F, 0F, 2F, 11F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 1.0472F, 0F, 0F));

        PartDefinition HairC05 = HairC04.addOrReplaceChild("HairC05", CubeListBuilder.create().texOffs(40, 0).addBox(0F, 0F, 0F, 2F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, 0F, 1.7453F, 0F, 0F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(90, 103).addBox(-0.5F, 0F, 0F, 1F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 1.5F, -4.5F, -0.192F, -0.1745F, -0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(90, 103).addBox(-0.5F, 0F, 0F, 1F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 0F, 0.1745F, 0F, 0.0873F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(90, 103).mirror().addBox(-0.5F, 0F, 0F, 1F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5F, 1.5F, -4.5F, -0.192F, 0.1745F, 0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(90, 103).mirror().addBox(-0.5F, 0F, 0F, 1F, 8F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 8F, 0F, 0.1745F, 0F, -0.0524F));

        PartDefinition HairCBaseB = Hair.addOrReplaceChild("HairCBaseB", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 1F, -1.6F, 0F, 0F, 0.3142F));

        PartDefinition HairC01b = HairCBaseB.addOrReplaceChild("HairC01b", CubeListBuilder.create().texOffs(40, 0).addBox(-2F, 0F, 0F, 2F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -6F, -7F, 1.3963F, 0.1396F, 0F));

        PartDefinition HairC02b = HairC01b.addOrReplaceChild("HairC02b", CubeListBuilder.create().texOffs(40, 0).addBox(-2F, 0F, 0F, 2F, 10F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, -1.3439F, 0F, 0F));

        PartDefinition HairC03b = HairC02b.addOrReplaceChild("HairC03b", CubeListBuilder.create().texOffs(40, 0).addBox(-2F, 0F, 0F, 2F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10F, 0F, 0.5009F, 0F, 0.8727F));

        PartDefinition HairC04b = HairC03b.addOrReplaceChild("HairC04b", CubeListBuilder.create().texOffs(40, 0).addBox(-2F, 0F, 0F, 2F, 11F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 1.0472F, 0F, 0F));

        PartDefinition HairC05b = HairC04b.addOrReplaceChild("HairC05b", CubeListBuilder.create().texOffs(40, 0).addBox(-2F, 0F, 0F, 2F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, 0F, 1.7453F, 0F, 0F));

        PartDefinition HairS02 = Hair.addOrReplaceChild("HairS02", CubeListBuilder.create().texOffs(110, 22).addBox(-1.5F, -3F, -3F, 3F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.8F, 3.1F, 3.3F, 0F, -0.0524F, 0F));

        PartDefinition EquipHeadBase = Head.addOrReplaceChild("EquipHeadBase", CubeListBuilder.create().texOffs(33, 16).addBox(-8F, 0F, 0F, 16F, 2F, 15F, new CubeDeformation(0F)), PartPose.offset(0F, -11.8F, -7.6F));

        PartDefinition EquipHead01a = EquipHeadBase.addOrReplaceChild("EquipHead01a", CubeListBuilder.create().texOffs(36, 108).addBox(-8F, 0F, 0F, 8F, 1F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.5F, 0.2F, 7F, 0F, 0F, 0.0873F));

        PartDefinition EquipHead03a = EquipHead01a.addOrReplaceChild("EquipHead03a", CubeListBuilder.create().texOffs(40, 105).addBox(-7F, 0F, 0F, 7F, 2F, 1F, new CubeDeformation(0F)), PartPose.offset(0.2F, 0.9F, 0.5F));

        PartDefinition EquipHead02a = EquipHead01a.addOrReplaceChild("EquipHead02a", CubeListBuilder.create().texOffs(44, 82).addBox(-7F, 0F, 1F, 7F, 2F, 0F, new CubeDeformation(0F)), PartPose.offset(-0.4F, -1.9F, 0F));

        PartDefinition EquipHead01 = EquipHeadBase.addOrReplaceChild("EquipHead01", CubeListBuilder.create().texOffs(36, 108).addBox(0F, 0F, 0F, 8F, 1F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.5F, 0.2F, 7F, 0F, 0F, -0.0873F));

        PartDefinition EquipHead03 = EquipHead01.addOrReplaceChild("EquipHead03", CubeListBuilder.create().texOffs(40, 105).addBox(0F, 0F, 0F, 7F, 2F, 1F, new CubeDeformation(0F)), PartPose.offset(0.2F, 0.9F, 0.5F));

        PartDefinition EquipHead02 = EquipHead01.addOrReplaceChild("EquipHead02", CubeListBuilder.create().texOffs(44, 82).addBox(0F, 0F, 1F, 7F, 2F, 0F, new CubeDeformation(0F)), PartPose.offset(0.4F, -1.9F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(80, 0).addBox(-7.5F, 0F, 0F, 15F, 13F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 1F, 0.2618F, 0F, 0F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(52, 35).addBox(-8F, 0F, -5F, 16F, 13F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 10.5F, 5.7F, -0.1745F, 0F, 0F));

        PartDefinition Ahoke00 = Head.addOrReplaceChild("Ahoke00", CubeListBuilder.create().texOffs(100, 28).addBox(0F, -9F, 0F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.6F, -13F, -4F, 0.6632F, 0.5236F, 0F));

        PartDefinition Ahoke01 = Head.addOrReplaceChild("Ahoke01", CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, 0F, 0F, 3F, 5F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, -5F, 2.7053F, -2.8798F, 0F));

        PartDefinition Ahoke02 = Ahoke01.addOrReplaceChild("Ahoke02", CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, 0F, 0F, 3F, 7F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5F, 0F, 1.2217F, 0F, 0F));

        PartDefinition Ahoke03 = Ahoke02.addOrReplaceChild("Ahoke03", CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, 0F, 0F, 3F, 6F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 0F, 1.4835F, 0F, 0F));

        PartDefinition Ahoke04 = Ahoke03.addOrReplaceChild("Ahoke04", CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, 0F, 0F, 3F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6F, 0F, 0.9599F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 104), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(24, 63), PartPose.offsetAndRotation(0F, -9.6F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(44, 101), PartPose.offset(0F, -1F, -0.7F));

        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
        applyHairAndClothAnimation(ctx, limbSwing, ageInTicks, limbSwingAmount);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        ArmLeft02.x = armLeft02DefaultX;
        ArmLeft02.z = armLeft02DefaultZ;
        ArmRight02.x = armRight02DefaultX;
        ArmRight02.z = armRight02DefaultZ;

        LegLeft01.y = legLeft01DefaultY;
        LegLeft01.z = legLeft01DefaultZ;
        LegLeft02.x = legLeft02DefaultX;
        LegLeft02.y = legLeft02DefaultY;
        LegLeft02.z = legLeft02DefaultZ;

        LegRight01.y = legRight01DefaultY;
        LegRight01.z = legRight01DefaultZ;
        LegRight02.x = legRight02DefaultX;
        LegRight02.y = legRight02DefaultY;
        LegRight02.z = legRight02DefaultZ;

        ClothA03.y = clothA03DefaultY;
        ClothA03.z = clothA03DefaultZ;
        ClothA04.y = clothA04DefaultY;
        ClothA04.z = clothA04DefaultZ;
        ClothA05.y = clothA05DefaultY;
        ClothA05.z = clothA05DefaultZ;

        ClothA03a.y = clothA03aDefaultY;
        ClothA03a.z = clothA03aDefaultZ;
        ClothA04a.y = clothA04aDefaultY;
        ClothA04a.z = clothA04aDefaultZ;
        ClothA05a.y = clothA05aDefaultY;
        ClothA05a.z = clothA05aDefaultZ;

        Butt.y = buttDefaultY;
        Butt.z = buttDefaultZ;
        Skirt01.y = skirt01DefaultY;
        Skirt01.z = skirt01DefaultZ;
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        boolean showEquip = entity.getEquipFlag(org.trp.shincolle.entity.EntityBBKongou.EQUIP_RIGGING);
        boolean showHead = entity.getEquipFlag(org.trp.shincolle.entity.EntityBBKongou.EQUIP_HEAD_BASE);
        boolean showHairSet = entity.getEquipFlag(org.trp.shincolle.entity.EntityBBKongou.EQUIP_HAIR_SET);
        boolean showAhoke00 = entity.getEquipFlag(org.trp.shincolle.entity.EntityBBKongou.EQUIP_AHOKE);
        if (EquipBase != null) EquipBase.visible = showEquip;
        if (EquipHeadBase != null) EquipHeadBase.visible = showHead;
        if (HairS01 != null) HairS01.visible = showHairSet;
        if (HairS02 != null) HairS02.visible = showHairSet;
        if (HairCBase != null) HairCBase.visible = showHairSet;
        if (HairCBaseB != null) HairCBaseB.visible = showHairSet;
        if (Ahoke00 != null) Ahoke00.visible = showAhoke00;
        if (Ahoke01 != null) Ahoke01.visible = !showAhoke00;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = 0.0F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        BodyMain.xRot = 1.4F;
        Butt.xRot = 0.21F;
        BoobL.xRot = -0.8F;
        BoobR.xRot = -0.8F;
        ClothB01.xRot = 0.96F;
        Skirt01.xRot = -0.087F;
        Skirt02.xRot = -0.087F;
        SkirtB01.xRot = 0.087F;

        Cloth02a1.xRot = -0.5585F;
        Cloth02b1.xRot = -0.5585F;
        Cloth02c1.xRot = 0.6283F;
        Cloth02c1_1.xRot = 0.6283F;
        Cloth02c2.xRot = -0.7854F;
        Cloth02c2_1.xRot = -0.7854F;
        Cloth02c3.xRot = -0.1396F;
        Cloth02c3_1.xRot = -0.1396F;
        Cloth02c4.xRot = 0.0F;
        Cloth02c4_1.xRot = 0.0F;
        Cloth02a2.xRot = 0.1745F;
        Cloth02b2.xRot = 0.1745F;
        Cloth02a3.xRot = 0.0F;
        Cloth02b3.xRot = 0.0F;

        Ahoke00.xRot = 0.6632F;
        Ahoke00.yRot = 0.523F;
        Ahoke00.zRot = 0.0F;
        Ahoke01.xRot = 2.7F;
        Ahoke02.xRot = 1.22F;
        Ahoke03.xRot = 1.48F;
        Ahoke04.xRot = 0.96F;

        Hair01.xRot = 0.1F;
        Hair01.yRot = 0.0F;
        Hair01.zRot = 0.0F;
        Hair02.xRot = -0.3F;
        Hair02.yRot = 0.0F;
        Hair02.zRot = 0.0F;

        ArmLeft01.xRot = -2.8F;
        ArmLeft01.yRot = 0.1F;
        ArmLeft01.zRot = 0.84F;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 1.0F;

        ArmRight01.xRot = 0.0F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = 0.2F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        LegLeft01.xRot = -0.12F;
        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = -0.05F;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.xRot = -0.12F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = 0.26F;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = -0.4F;

        EquipBase.visible = false;
    }

    private void applyBasePose(PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks, float headPitch) {
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.1F + 0.35F + limbSwing * 0.5F);
        float angleX2 = Mth.cos(ageInTicks * 0.1F + 0.7F + limbSwing * 0.5F);
        float angleX3 = Mth.cos(ageInTicks * 0.1F + 1.05F + limbSwing * 0.5F);
        float angleX4 = Mth.cos(ageInTicks * 0.1F + 1.4F + limbSwing * 0.5F);

        Ahoke00.xRot = angleX2 * 0.05F + 0.66F;
        Ahoke00.yRot = -angleX * 0.15F + 0.53F;
        Ahoke01.xRot = -angleX1 * 0.09F + 2.7F;
        Ahoke02.xRot = angleX2 * 0.15F + 1.22F;
        Ahoke03.xRot = -angleX3 * 0.1F + 1.48F;
        Ahoke04.xRot = -angleX4 * 0.1F + 0.96F;

        BoobL.xRot = angleX * 0.06F - 0.8F;
        BoobR.xRot = angleX * 0.06F - 0.8F;
        ClothB01.xRot = 0.96F - angleX * 0.08F;

        BodyMain.xRot = -0.1047F;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;
        Butt.xRot = 0.35F;
        Skirt01.xRot = -0.087F;
        Skirt02.xRot = -0.087F;
        SkirtB01.xRot = 0.087F;

        Cloth02a1.xRot = -0.5585F;
        Cloth02b1.xRot = -0.5585F;
        Cloth02c1.xRot = 0.6283F;
        Cloth02c1_1.xRot = 0.6283F;
        Cloth02c2.xRot = -0.7854F;
        Cloth02c2_1.xRot = -0.7854F;
        Cloth02c3.xRot = -0.1396F + angleX1 * 0.06F;
        Cloth02c3_1.xRot = -0.1396F + angleX1 * 0.06F;
        Cloth02c4.xRot = -angleX2 * 0.06F;
        Cloth02c4_1.xRot = -angleX2 * 0.06F;
        Cloth02a2.xRot = 0.12F + angleX1 * 0.06F;
        Cloth02b2.xRot = 0.12F + angleX1 * 0.06F;
        Cloth02a3.xRot = -angleX2 * 0.06F;
        Cloth02b3.xRot = -angleX2 * 0.06F;

        ArmLeft01.xRot = ctx.angleAdd2 * 0.25F + 0.3F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = angleX * 0.03F - 0.25F;
        ArmLeft02.xRot = 0.0F;
        ArmLeft02.zRot = 0.0F;

        ArmRight01.xRot = ctx.angleAdd1 * 0.25F - 0.087F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -angleX * 0.03F + 0.25F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.0873F;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -0.0873F;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;

        EquipCL1a1.xRot = Head.xRot * 0.8F - 0.21F;
        EquipCL1a1_1.xRot = Head.xRot * 0.7F - 0.23F;
        EquipCL1a1_2.xRot = Head.xRot * 0.85F - 0.2F;
        EquipCL1a1_3.xRot = Head.xRot * 0.75F - 0.25F;
        EquipCL1a1_4.xRot = Head.xRot * 0.8F - 0.2F;
        EquipCL1a1_5.xRot = Head.xRot * 0.85F - 0.19F;
        EquipCL1a1_6.xRot = Head.xRot * 0.75F - 0.21F;
        EquipCL1a1_7.xRot = Head.xRot * 0.88F - 0.19F;
        EquipCL1Base01.yRot = Head.yRot * 0.5F - 0.9F;
        EquipCL1Base01_1.yRot = Head.yRot * 0.75F;
        EquipCL1Base01_2.yRot = Head.yRot * 0.5F + 0.9F;
        EquipCL1Base01_3.yRot = Head.yRot * 0.75F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float legAddLeft = ctx.angleAdd1 * 0.3F - 0.28F;
        float legAddRight = ctx.angleAdd2 * 0.3F - 0.21F;
        boolean spcStand = true;

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = (entity != null && entity.getIsSprinting()) || limbSwingAmount > 0.9F;

        if (isSprinting) {
            spcStand = false;
            BodyMain.xRot = 0.2F;
            Skirt01.xRot = -0.4F;
            Skirt02.xRot = -0.1F;
            SkirtB01.xRot = -0.13F;
            Cloth02c1.xRot = 1.17F;
            Cloth02c1_1.xRot = 1.17F;
            Cloth02c2.xRot = -0.63F;
            Cloth02c2_1.xRot = -0.63F;
            Hair01.xRot += 0.2F;
            Hair02.xRot += 0.2F;

            ArmLeft01.xRot = ctx.angleAdd2 * 1.2F + 0.5F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft02.xRot = -1.0F;
            ArmLeft02.zRot = 0.0F;
            ArmRight01.xRot = ctx.angleAdd1 * 1.2F + 0.5F;
            ArmRight01.yRot = 0.0F;
            ArmRight02.xRot = -1.0F;
            ArmRight02.zRot = 0.0F;

            legAddLeft = ctx.angleAdd1 * 0.7F - 0.48F;
            legAddRight = ctx.angleAdd2 * 0.7F - 0.41F;
        }

        if (isCrouching) {
            spcStand = false;
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

            legAddLeft -= 0.94F;
            legAddRight -= 0.94F;
            LegLeft01.zRot = 0.2F;
            LegRight01.zRot = -0.2F;
        }

        if (isSitting) {
            spcStand = false;
            this.isSittingPose = true;
            int sitPhase = entity != null ? (entity.tickCount % 512) : 0;
            if (sitPhase > 256) {
                if (entity != null && hasLegacyState(entity, 1, 4)) {
                    this.poseTranslateY += SITTING_ALT_TRANSLATE_Y;
                    Head.xRot = -0.35F;
                    Head.yRot = 0.0F;
                    BodyMain.xRot = -1.6F;
                    ArmLeft01.xRot = 3.0F;
                    ArmLeft01.yRot = 0.0F;
                    ArmLeft01.zRot = 0.7F;
                    ArmRight01.xRot = 3.0F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.7F;
                    ArmLeft02.xRot = 0.0F;
                    ArmRight02.xRot = 0.0F;
                    legAddLeft = -0.2F;
                    legAddRight = -0.2F;
                    LegLeft01.yRot = 0.0F;
                    LegLeft01.zRot = -0.1F;
                    LegLeft02.xRot = 0.0F;
                    LegRight01.yRot = 0.0F;
                    LegRight01.zRot = 0.1F;
                    LegRight02.xRot = 0.0F;
                    EquipBase.visible = false;
                } else {
                    this.poseTranslateY += 0.55F * 3;
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
                    legAddLeft = -2.57F;
                    legAddRight = -2.57F;
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
                }
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
                legAddLeft = -1.309F;
                legAddRight = -1.7F;
                LegLeft01.yRot = 0.3142F;
                LegLeft02.xRot = 1.0472F;
                LegRight01.yRot = -0.35F;
                LegRight01.zRot = -0.2618F;
                LegRight02.xRot = 0.9F;
                Hair01.xRot += 0.12F;
                Hair02.xRot += 0.15F;
            }
        }

        if (entity != null && entity.getAttackTick() > 20) {
            spcStand = false;
            BodyMain.xRot = -0.17F;
            ArmLeft01.xRot = -1.57F;
            ArmLeft01.yRot = -0.26F;
            ArmLeft01.zRot = 0.0F;
            ArmRight01.xRot = 0.0F;
            ArmRight01.zRot = 0.87F;
            ArmRight02.zRot = -1.57F;
            legAddLeft += 0.14F;
            legAddRight += 0.07F;
            LegLeft01.yRot = 0.0F;
            LegLeft01.zRot = -0.17F;
            LegRight01.yRot = 0.0F;
            LegRight01.zRot = 0.17F;
        }

        if (spcStand && (entity == null || (entity.tickCount % 512) > 256)) {
            BodyMain.xRot = -0.17F;
            ArmLeft01.xRot = -1.57F;
            ArmLeft01.yRot = -0.26F;
            ArmLeft01.zRot = 0.0F;
            ArmRight01.xRot = 0.0F;
            ArmRight01.zRot = 0.87F;
            ArmRight02.zRot = -1.57F;
            legAddLeft += 0.14F;
            legAddRight += 0.07F;
            LegLeft01.yRot = 0.0F;
            LegLeft01.zRot = -0.17F;
            LegRight01.yRot = 0.0F;
            LegRight01.zRot = 0.17F;
            if (entity != null && hasLegacyState(entity, 7, 4)) {
                setFace(3);
                setMouth(5);
            }
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot = -0.4F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.2F;
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
        }

        LegLeft01.xRot = legAddLeft;
        LegRight01.xRot = legAddRight;
    }

    private void applyHairAndClothAnimation(PoseContext ctx, float limbSwing, float ageInTicks, float limbSwingAmount) {
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.1F + 0.35F + limbSwing * 0.5F);
        float headX = Head.xRot * -0.5F;
        float headZ = Head.zRot * -0.5F;

        Hair01.xRot = angleX * 0.03F + 0.26F + headX;
        Hair02.xRot = -angleX1 * 0.04F - 0.17F + headX;

        if (this.isSittingPose) {
            Hair01.xRot = 0.21F + headX;
            Hair02.xRot = -0.28F + headX;
        } else if (limbSwingAmount > 0.9F) {
            Hair01.xRot += 0.2F;
            Hair02.xRot += 0.2F;
        } else if (this.poseTranslateY != 0.0F && !this.isDeadPose) {
            Hair01.xRot = Hair01.xRot * 0.5F + 0.4F;
            Hair02.xRot = Hair02.xRot * 0.75F + 0.25F;
        }

        HairL01.xRot = angleX * 0.02F + headX - 0.19F;
        HairL02.xRot = -angleX1 * 0.04F + headX + 0.17F;
        HairR01.xRot = angleX * 0.02F + headX - 0.19F;
        HairR02.xRot = -angleX1 * 0.04F + headX + 0.17F;

        Hair01.zRot = headZ;
        Hair02.zRot = headZ;
        HairL01.zRot = headZ - 0.087F;
        HairL02.zRot = headZ + 0.087F;
        HairR01.zRot = headZ + 0.087F;
        HairR02.zRot = headZ - 0.052F;

        float HandL = BodyMain.xRot + ArmLeft01.xRot + ArmLeft02.xRot;
        float HandR = BodyMain.xRot + ArmRight01.xRot + ArmRight02.xRot;
        float HandLc = Mth.cos(HandL);
        float HandLs = Mth.sin(HandL);
        float HandRc = Mth.cos(HandR);
        float HandRs = Mth.sin(HandR);

        ClothA03.y = clothA03DefaultY + (HandLc * 0.1F * OFFSET_SCALE);
        ClothA04.y = clothA04DefaultY + (HandLc * 0.2F * OFFSET_SCALE);
        ClothA05.y = clothA05DefaultY + (HandLc * 0.25F * OFFSET_SCALE);
        ClothA03.z = clothA03DefaultZ + (HandLs * -0.32F * OFFSET_SCALE);
        ClothA04.z = clothA04DefaultZ + (HandLs * -0.32F * OFFSET_SCALE);
        ClothA05.z = clothA05DefaultZ + (HandLs * -0.32F * OFFSET_SCALE);

        ClothA03a.y = clothA03aDefaultY + (HandRc * 0.1F * OFFSET_SCALE);
        ClothA04a.y = clothA04aDefaultY + (HandRc * 0.2F * OFFSET_SCALE);
        ClothA05a.y = clothA05aDefaultY + (HandRc * 0.25F * OFFSET_SCALE);
        ClothA03a.z = clothA03aDefaultZ + (HandRs * -0.32F * OFFSET_SCALE);
        ClothA04a.z = clothA04aDefaultZ + (HandRs * -0.32F * OFFSET_SCALE);
        ClothA05a.z = clothA05aDefaultZ + (HandRs * -0.32F * OFFSET_SCALE);
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
            if (this.GlowNeck != null) GlowNeck.copyFrom(Neck);
            if (this.GlowHead != null) GlowHead.copyFrom(Head);
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
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        if (GlowBodyMain != null) {
            GlowBodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
