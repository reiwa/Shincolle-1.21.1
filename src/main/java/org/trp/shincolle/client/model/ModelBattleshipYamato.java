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

public class ModelBattleshipYamato<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "battleship_yamato"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelBattleshipYamato");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelBattleshipYamato");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelBattleshipYamato");

    private boolean isDeadPose;
    private boolean isSittingPose;
    private float poseTranslateY;

    private final ModelPart BodyMain;
    private final ModelPart Neck;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart Butt;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart Cloth01;
    private final ModelPart EquipBaseBelt;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart EquipHeadBase;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart HairR01;
    private final ModelPart HairU01;
    private final ModelPart HairL02;
    private final ModelPart HairL03;
    private final ModelPart HairR02;
    private final ModelPart HairR03;
    private final ModelPart HairBase;
    private final ModelPart Hair00;
    private final ModelPart Hair01;
    private final ModelPart Hair02;
    private final ModelPart Hair03;
    private final ModelPart Hair04;
    private final ModelPart HeadEquip01a;
    private final ModelPart HeadEquip02a;
    private final ModelPart HeadEquip01b;
    private final ModelPart HeadEquip01c;
    private final ModelPart HeadEquip01d;
    private final ModelPart HeadEquip01b2;
    private final ModelPart HeadEquip02b;
    private final ModelPart HeadEquip02c;
    private final ModelPart HeadEquip02d;
    private final ModelPart HeadEquip02b2;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart AnchorL;
    private final ModelPart AnchorR;
    private final ModelPart LegRight02;
    private final ModelPart EquipLegR01;
    private final ModelPart ShoesR01;
    private final ModelPart EquipLegR02a;
    private final ModelPart EquipLegR02b;
    private final ModelPart EquipLegR02c;
    private final ModelPart LegLeft02;
    private final ModelPart EquipLegL01;
    private final ModelPart ShoesL01;
    private final ModelPart EquipLegL02a;
    private final ModelPart EquipLegL02b;
    private final ModelPart EquipLegL02c;
    private final ModelPart Skirt02;
    private final ModelPart ArmLeft01a;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart EquipU01;
    private final ModelPart EquipU01a;
    private final ModelPart EquipU01b;
    private final ModelPart EquipU02;
    private final ModelPart EquipU03a;
    private final ModelPart EquipU04a;
    private final ModelPart EquipU05a;
    private final ModelPart EquipU06;
    private final ModelPart EquipU09a;
    private final ModelPart EquipU09b;
    private final ModelPart EquipU09c;
    private final ModelPart EquipU03b;
    private final ModelPart EquipU03c;
    private final ModelPart EquipU03d;
    private final ModelPart EquipU04b;
    private final ModelPart EquipU04c;
    private final ModelPart EquipU04d;
    private final ModelPart EquipU05b;
    private final ModelPart EquipU05c;
    private final ModelPart EquipU05d;
    private final ModelPart EquipU07;
    private final ModelPart EquipU08;
    private final ModelPart Cloth02a;
    private final ModelPart Cloth02b;
    private final ModelPart EquipRotateBase;
    private final ModelPart EquipBaseBelt2;
    private final ModelPart EquipBaseM01a;
    private final ModelPart EquipBaseM01b;
    private final ModelPart EquipL01;
    private final ModelPart EquipR01;
    private final ModelPart EquipBaseM02;
    private final ModelPart EquipL02;
    private final ModelPart EquipL03;
    private final ModelPart EquipL04;
    private final ModelPart EquipLCBase01;
    private final ModelPart EquipL05;
    private final ModelPart EquipLC2Base01;
    private final ModelPart EquipLC3Base01;
    private final ModelPart EquipLC2Base02;
    private final ModelPart EquipLC201a;
    private final ModelPart EquipLC202a;
    private final ModelPart EquipLC203a;
    private final ModelPart EquipLC2Radar01;
    private final ModelPart EquipLC2Radar02;
    private final ModelPart EquipLC201b;
    private final ModelPart EquipLC202b;
    private final ModelPart EquipLC203b;
    private final ModelPart EquipLC3Base02;
    private final ModelPart EquipLC301a;
    private final ModelPart EquipLC302a;
    private final ModelPart EquipLC303a;
    private final ModelPart EquipLC3Radar01;
    private final ModelPart EquipLC3Radar02;
    private final ModelPart EquipLC301b;
    private final ModelPart EquipLC302b;
    private final ModelPart EquipLC303b;
    private final ModelPart EquipLCBase02;
    private final ModelPart EquipLC01a;
    private final ModelPart EquipLC02a;
    private final ModelPart EquipLC03a;
    private final ModelPart EquipLCRadar01;
    private final ModelPart EquipLCRadar02;
    private final ModelPart EquipLC01b;
    private final ModelPart EquipLC02b;
    private final ModelPart EquipLC03b;
    private final ModelPart EquipR02;
    private final ModelPart EquipMCBase01a;
    private final ModelPart EquipMCBase01b;
    private final ModelPart EquipR03;
    private final ModelPart EquipRCBase01;
    private final ModelPart EquipR04;
    private final ModelPart EquipRCBase02;
    private final ModelPart EquipRC01a;
    private final ModelPart EquipRC02a;
    private final ModelPart EquipRC03a;
    private final ModelPart EquipRCRadar01;
    private final ModelPart EquipRCRadar02;
    private final ModelPart EquipRC01b;
    private final ModelPart EquipRC02b;
    private final ModelPart EquipRC03b;
    private final ModelPart EquipR05;
    private final ModelPart EquipRC2Base01;
    private final ModelPart EquipRC3Base01;
    private final ModelPart EquipRC2Base02;
    private final ModelPart EquipRC201a;
    private final ModelPart EquipRC202a;
    private final ModelPart EquipRC203a;
    private final ModelPart EquipRC2Radar01;
    private final ModelPart EquipRC2Radar02;
    private final ModelPart EquipRC201b;
    private final ModelPart EquipRC202b;
    private final ModelPart EquipRC203b;
    private final ModelPart EquipRC3Base02;
    private final ModelPart EquipRC301a;
    private final ModelPart EquipRC302a;
    private final ModelPart EquipRC303a;
    private final ModelPart EquipRC3Radar01;
    private final ModelPart EquipRC3Radar02;
    private final ModelPart EquipRC301b;
    private final ModelPart EquipRC302b;
    private final ModelPart EquipRC303b;
    private final ModelPart EquipLCBase01_1;
    private final ModelPart EquipLCBase02_1;
    private final ModelPart EquipLC01a_1;
    private final ModelPart EquipLC02a_1;
    private final ModelPart EquipLC03a_1;
    private final ModelPart EquipMCRadar01;
    private final ModelPart EquipMCRadar02;
    private final ModelPart EquipLC01b_1;
    private final ModelPart EquipLC02b_1;
    private final ModelPart EquipLC03b_1;
    private final ModelPart EquipBaseM03;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultZ;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultY;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultY;
    private final float armRight02DefaultZ;

    public ModelBattleshipYamato(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft01a = this.ArmLeft01.getChild("ArmLeft01a");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.Cloth02a = this.Cloth01.getChild("Cloth02a");
        this.Cloth02b = this.Cloth02a.getChild("Cloth02b");
        this.EquipBaseBelt = this.BodyMain.getChild("EquipBaseBelt");
        this.EquipRotateBase = this.EquipBaseBelt.getChild("EquipRotateBase");
        this.EquipBaseM01a = this.EquipRotateBase.getChild("EquipBaseM01a");
        this.EquipBaseM01b = this.EquipRotateBase.getChild("EquipBaseM01b");
        this.EquipR01 = this.EquipBaseM01b.getChild("EquipR01");
        this.EquipMCBase01b = this.EquipR01.getChild("EquipMCBase01b");
        this.EquipR02 = this.EquipR01.getChild("EquipR02");
        this.EquipR03 = this.EquipR02.getChild("EquipR03");
        this.EquipRCBase01 = this.EquipR03.getChild("EquipRCBase01");
        this.EquipRCBase02 = this.EquipRCBase01.getChild("EquipRCBase02");
        this.EquipRCRadar02 = this.EquipRCBase02.getChild("EquipRCRadar02");
        this.EquipRCRadar01 = this.EquipRCBase02.getChild("EquipRCRadar01");
        this.EquipRC02a = this.EquipRCBase02.getChild("EquipRC02a");
        this.EquipRC02b = this.EquipRC02a.getChild("EquipRC02b");
        this.EquipRC03a = this.EquipRCBase02.getChild("EquipRC03a");
        this.EquipRC03b = this.EquipRC03a.getChild("EquipRC03b");
        this.EquipRC01a = this.EquipRCBase02.getChild("EquipRC01a");
        this.EquipRC01b = this.EquipRC01a.getChild("EquipRC01b");
        this.EquipR04 = this.EquipR03.getChild("EquipR04");
        this.EquipRC3Base01 = this.EquipR04.getChild("EquipRC3Base01");
        this.EquipRC3Base02 = this.EquipRC3Base01.getChild("EquipRC3Base02");
        this.EquipRC302a = this.EquipRC3Base02.getChild("EquipRC302a");
        this.EquipRC302b = this.EquipRC302a.getChild("EquipRC302b");
        this.EquipRC3Radar02 = this.EquipRC3Base02.getChild("EquipRC3Radar02");
        this.EquipRC303a = this.EquipRC3Base02.getChild("EquipRC303a");
        this.EquipRC303b = this.EquipRC303a.getChild("EquipRC303b");
        this.EquipRC3Radar01 = this.EquipRC3Base02.getChild("EquipRC3Radar01");
        this.EquipRC301a = this.EquipRC3Base02.getChild("EquipRC301a");
        this.EquipRC301b = this.EquipRC301a.getChild("EquipRC301b");
        this.EquipRC2Base01 = this.EquipR04.getChild("EquipRC2Base01");
        this.EquipRC2Base02 = this.EquipRC2Base01.getChild("EquipRC2Base02");
        this.EquipRC2Radar02 = this.EquipRC2Base02.getChild("EquipRC2Radar02");
        this.EquipRC2Radar01 = this.EquipRC2Base02.getChild("EquipRC2Radar01");
        this.EquipRC203a = this.EquipRC2Base02.getChild("EquipRC203a");
        this.EquipRC203b = this.EquipRC203a.getChild("EquipRC203b");
        this.EquipRC202a = this.EquipRC2Base02.getChild("EquipRC202a");
        this.EquipRC202b = this.EquipRC202a.getChild("EquipRC202b");
        this.EquipRC201a = this.EquipRC2Base02.getChild("EquipRC201a");
        this.EquipRC201b = this.EquipRC201a.getChild("EquipRC201b");
        this.EquipR05 = this.EquipR04.getChild("EquipR05");
        this.EquipMCBase01a = this.EquipR01.getChild("EquipMCBase01a");
        this.EquipLCBase01_1 = this.EquipMCBase01a.getChild("EquipLCBase01_1");
        this.EquipLCBase02_1 = this.EquipLCBase01_1.getChild("EquipLCBase02_1");
        this.EquipMCRadar02 = this.EquipLCBase02_1.getChild("EquipMCRadar02");
        this.EquipLC02a_1 = this.EquipLCBase02_1.getChild("EquipLC02a_1");
        this.EquipLC02b_1 = this.EquipLC02a_1.getChild("EquipLC02b_1");
        this.EquipLC01a_1 = this.EquipLCBase02_1.getChild("EquipLC01a_1");
        this.EquipLC01b_1 = this.EquipLC01a_1.getChild("EquipLC01b_1");
        this.EquipLC03a_1 = this.EquipLCBase02_1.getChild("EquipLC03a_1");
        this.EquipLC03b_1 = this.EquipLC03a_1.getChild("EquipLC03b_1");
        this.EquipMCRadar01 = this.EquipLCBase02_1.getChild("EquipMCRadar01");
        this.EquipL01 = this.EquipBaseM01b.getChild("EquipL01");
        this.EquipL02 = this.EquipL01.getChild("EquipL02");
        this.EquipL03 = this.EquipL02.getChild("EquipL03");
        this.EquipLCBase01 = this.EquipL03.getChild("EquipLCBase01");
        this.EquipLCBase02 = this.EquipLCBase01.getChild("EquipLCBase02");
        this.EquipLC02a = this.EquipLCBase02.getChild("EquipLC02a");
        this.EquipLC02b = this.EquipLC02a.getChild("EquipLC02b");
        this.EquipLCRadar02 = this.EquipLCBase02.getChild("EquipLCRadar02");
        this.EquipLCRadar01 = this.EquipLCBase02.getChild("EquipLCRadar01");
        this.EquipLC03a = this.EquipLCBase02.getChild("EquipLC03a");
        this.EquipLC03b = this.EquipLC03a.getChild("EquipLC03b");
        this.EquipLC01a = this.EquipLCBase02.getChild("EquipLC01a");
        this.EquipLC01b = this.EquipLC01a.getChild("EquipLC01b");
        this.EquipL04 = this.EquipL03.getChild("EquipL04");
        this.EquipLC2Base01 = this.EquipL04.getChild("EquipLC2Base01");
        this.EquipLC2Base02 = this.EquipLC2Base01.getChild("EquipLC2Base02");
        this.EquipLC203a = this.EquipLC2Base02.getChild("EquipLC203a");
        this.EquipLC203b = this.EquipLC203a.getChild("EquipLC203b");
        this.EquipLC2Radar02 = this.EquipLC2Base02.getChild("EquipLC2Radar02");
        this.EquipLC2Radar01 = this.EquipLC2Base02.getChild("EquipLC2Radar01");
        this.EquipLC201a = this.EquipLC2Base02.getChild("EquipLC201a");
        this.EquipLC201b = this.EquipLC201a.getChild("EquipLC201b");
        this.EquipLC202a = this.EquipLC2Base02.getChild("EquipLC202a");
        this.EquipLC202b = this.EquipLC202a.getChild("EquipLC202b");
        this.EquipLC3Base01 = this.EquipL04.getChild("EquipLC3Base01");
        this.EquipLC3Base02 = this.EquipLC3Base01.getChild("EquipLC3Base02");
        this.EquipLC3Radar02 = this.EquipLC3Base02.getChild("EquipLC3Radar02");
        this.EquipLC3Radar01 = this.EquipLC3Base02.getChild("EquipLC3Radar01");
        this.EquipLC303a = this.EquipLC3Base02.getChild("EquipLC303a");
        this.EquipLC303b = this.EquipLC303a.getChild("EquipLC303b");
        this.EquipLC302a = this.EquipLC3Base02.getChild("EquipLC302a");
        this.EquipLC302b = this.EquipLC302a.getChild("EquipLC302b");
        this.EquipLC301a = this.EquipLC3Base02.getChild("EquipLC301a");
        this.EquipLC301b = this.EquipLC301a.getChild("EquipLC301b");
        this.EquipL05 = this.EquipL04.getChild("EquipL05");
        this.EquipBaseM02 = this.EquipBaseM01b.getChild("EquipBaseM02");
        this.EquipBaseM03 = this.EquipBaseM02.getChild("EquipBaseM03");
        this.EquipBaseBelt2 = this.EquipBaseBelt.getChild("EquipBaseBelt2");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.EquipU01 = this.ArmRight02.getChild("EquipU01");
        this.EquipU01a = this.EquipU01.getChild("EquipU01a");
        this.EquipU02 = this.EquipU01.getChild("EquipU02");
        this.EquipU09a = this.EquipU02.getChild("EquipU09a");
        this.EquipU06 = this.EquipU02.getChild("EquipU06");
        this.EquipU07 = this.EquipU06.getChild("EquipU07");
        this.EquipU08 = this.EquipU07.getChild("EquipU08");
        this.EquipU09b = this.EquipU02.getChild("EquipU09b");
        this.EquipU04a = this.EquipU02.getChild("EquipU04a");
        this.EquipU04b = this.EquipU04a.getChild("EquipU04b");
        this.EquipU04c = this.EquipU04b.getChild("EquipU04c");
        this.EquipU04d = this.EquipU04c.getChild("EquipU04d");
        this.EquipU03a = this.EquipU02.getChild("EquipU03a");
        this.EquipU03b = this.EquipU03a.getChild("EquipU03b");
        this.EquipU03c = this.EquipU03b.getChild("EquipU03c");
        this.EquipU03d = this.EquipU03c.getChild("EquipU03d");
        this.EquipU05a = this.EquipU02.getChild("EquipU05a");
        this.EquipU05b = this.EquipU05a.getChild("EquipU05b");
        this.EquipU05c = this.EquipU05b.getChild("EquipU05c");
        this.EquipU05d = this.EquipU05c.getChild("EquipU05d");
        this.EquipU09c = this.EquipU02.getChild("EquipU09c");
        this.EquipU01b = this.EquipU01.getChild("EquipU01b");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.EquipHeadBase = this.Head.getChild("EquipHeadBase");
        this.HeadEquip02a = this.EquipHeadBase.getChild("HeadEquip02a");
        this.HeadEquip02d = this.HeadEquip02a.getChild("HeadEquip02d");
        this.HeadEquip02b = this.HeadEquip02a.getChild("HeadEquip02b");
        this.HeadEquip02b2 = this.HeadEquip02b.getChild("HeadEquip02b2");
        this.HeadEquip02c = this.HeadEquip02a.getChild("HeadEquip02c");
        this.HeadEquip01a = this.EquipHeadBase.getChild("HeadEquip01a");
        this.HeadEquip01d = this.HeadEquip01a.getChild("HeadEquip01d");
        this.HeadEquip01c = this.HeadEquip01a.getChild("HeadEquip01c");
        this.HeadEquip01b = this.HeadEquip01a.getChild("HeadEquip01b");
        this.HeadEquip01b2 = this.HeadEquip01b.getChild("HeadEquip01b2");
        this.Hair = this.Head.getChild("Hair");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairR03 = this.HairR02.getChild("HairR03");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.HairL03 = this.HairL02.getChild("HairL03");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairMain = this.Head.getChild("HairMain");
        this.HairBase = this.HairMain.getChild("HairBase");
        this.Hair00 = this.HairBase.getChild("Hair00");
        this.Hair01 = this.Hair00.getChild("Hair01");
        this.Hair02 = this.Hair01.getChild("Hair02");
        this.Hair03 = this.Hair02.getChild("Hair03");
        this.Hair04 = this.Hair03.getChild("Hair04");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.EquipLegL01 = this.LegLeft01.getChild("EquipLegL01");
        this.EquipLegL02c = this.EquipLegL01.getChild("EquipLegL02c");
        this.EquipLegL02a = this.EquipLegL01.getChild("EquipLegL02a");
        this.EquipLegL02b = this.EquipLegL01.getChild("EquipLegL02b");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoesL01 = this.LegLeft02.getChild("ShoesL01");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Skirt02 = this.Skirt01.getChild("Skirt02");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.EquipLegR01 = this.LegRight01.getChild("EquipLegR01");
        this.EquipLegR02a = this.EquipLegR01.getChild("EquipLegR02a");
        this.EquipLegR02c = this.EquipLegR01.getChild("EquipLegR02c");
        this.EquipLegR02b = this.EquipLegR01.getChild("EquipLegR02b");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoesR01 = this.LegRight02.getChild("ShoesR01");
        this.AnchorL = this.Butt.getChild("AnchorL");
        this.AnchorR = this.Butt.getChild("AnchorR");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultZ = this.LegRight02.z;

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

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 104).addBox(-6.5F, -11F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(33, 101).mirror().addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.7F, -8.5F, -3.5F, -0.6981F, 0.1396F, 0.0873F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(-2F, -1F, -2.5F, 5F, 14F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.7F, -0.7F, 0.2094F, 0F, -0.2618F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(20, 29).mirror().addBox(-5F, 0F, -5F, 5F, 14F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 13F, 2.5F));

        PartDefinition ArmLeft01a = ArmLeft01.addOrReplaceChild("ArmLeft01a", CubeListBuilder.create().texOffs(25, 69).mirror().addBox(-3F, 0F, -3F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(0.5F, 5.5F, 0F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(33, 101).addBox(-3.5F, 0F, 0F, 7F, 5F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.7F, -8.5F, -3.5F, -0.6981F, -0.1396F, -0.0873F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -4F, 12F, 4F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -11.3F, -0.3F));

        PartDefinition Cloth02a = Cloth01.addOrReplaceChild("Cloth02a", CubeListBuilder.create().texOffs(21, 62).addBox(-3.5F, 0F, 0F, 7F, 4F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.2F, -4F, -0.6981F, 0F, 0F));

        PartDefinition Cloth02b = Cloth02a.addOrReplaceChild("Cloth02b", CubeListBuilder.create().texOffs(24, 66).addBox(-1F, 0F, 0F, 2F, 3F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 0F, 0.9425F, 0F, 0F));

        PartDefinition EquipBaseBelt = BodyMain.addOrReplaceChild("EquipBaseBelt", CubeListBuilder.create().texOffs(66, 0).addBox(-8F, 0.7F, -2F, 16F, 4F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -2.5F, 0.1047F, 0F, 0F));

        PartDefinition EquipRotateBase = EquipBaseBelt.addOrReplaceChild("EquipRotateBase", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 10F));

        PartDefinition EquipBaseM01a = EquipRotateBase.addOrReplaceChild("EquipBaseM01a", CubeListBuilder.create().texOffs(128, 0).addBox(2.5F, 0F, -1F, 5F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -1F, 0.8727F, 0F, 0F));

        PartDefinition EquipBaseM01b = EquipRotateBase.addOrReplaceChild("EquipBaseM01b", CubeListBuilder.create().texOffs(128, 0).addBox(-7.5F, 0F, -1F, 5F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -1F, 0.8727F, 0F, 0F));

        PartDefinition EquipR01 = EquipBaseM01b.addOrReplaceChild("EquipR01", CubeListBuilder.create().texOffs(128, 0).addBox(-16F, 0F, 0F, 16F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.5F, 3F, -0.8727F, 0F, 0F));

        PartDefinition EquipMCBase01b = EquipR01.addOrReplaceChild("EquipMCBase01b", CubeListBuilder.create().texOffs(128, 0).addBox(-4F, 0F, 0F, 4F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 8F, 0F, 1.0472F, 0F, 0F));

        PartDefinition EquipR02 = EquipR01.addOrReplaceChild("EquipR02", CubeListBuilder.create().texOffs(128, 0).addBox(-13F, 0F, 0F, 13F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-13.5F, -0.5F, 0.6F, 0F, -0.5236F, 0F));

        PartDefinition EquipR03 = EquipR02.addOrReplaceChild("EquipR03", CubeListBuilder.create().texOffs(128, 29).addBox(-6F, 0F, -14F, 6F, 22F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-10.5F, -2.5F, -1F, 0F, 0.6981F, 0F));

        PartDefinition EquipRCBase01 = EquipR03.addOrReplaceChild("EquipRCBase01", CubeListBuilder.create().texOffs(196, 16).addBox(-8.5F, -5F, -7F, 16F, 9F, 14F, new CubeDeformation(0F)), PartPose.offset(-3F, 3F, -5.5F));

        PartDefinition EquipRCBase02 = EquipRCBase01.addOrReplaceChild("EquipRCBase02", CubeListBuilder.create().texOffs(128, 0).addBox(-8.5F, -8F, -7F, 17F, 8F, 21F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -4.5F, -2F, -0.0524F, 0F, 0F));

        PartDefinition EquipRCRadar02 = EquipRCBase02.addOrReplaceChild("EquipRCRadar02", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(0F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(-13.3F, -7F, 5F));

        PartDefinition EquipRCRadar01 = EquipRCBase02.addOrReplaceChild("EquipRCRadar01", CubeListBuilder.create().texOffs(58, 0).addBox(0F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(8.3F, -7F, 5F));

        PartDefinition EquipRC02a = EquipRCBase02.addOrReplaceChild("EquipRC02a", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.5F, -6F, -0.0873F, 0F, 0F));

        PartDefinition EquipRC02b = EquipRC02a.addOrReplaceChild("EquipRC02b", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipRC03a = EquipRCBase02.addOrReplaceChild("EquipRC03a", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, -4.5F, -6F, -0.3491F, 0F, 0F));

        PartDefinition EquipRC03b = EquipRC03a.addOrReplaceChild("EquipRC03b", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipRC01a = EquipRCBase02.addOrReplaceChild("EquipRC01a", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -4.5F, -6F, -0.1745F, 0F, 0F));

        PartDefinition EquipRC01b = EquipRC01a.addOrReplaceChild("EquipRC01b", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipR04 = EquipR03.addOrReplaceChild("EquipR04", CubeListBuilder.create().texOffs(128, 70).addBox(-6F, 0F, -13F, 6F, 11F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, -12.7F, 0F, -0.2094F, 0F));

        PartDefinition EquipRC3Base01 = EquipR04.addOrReplaceChild("EquipRC3Base01", CubeListBuilder.create().texOffs(211, 23).addBox(-4F, 0F, 0F, 8F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 5F, -12F, 0F, 0F, -1.5708F));

        PartDefinition EquipRC3Base02 = EquipRC3Base01.addOrReplaceChild("EquipRC3Base02", CubeListBuilder.create().texOffs(128, 0).addBox(-4.5F, -5F, -5.5F, 9F, 5F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 4F, -0.0524F, -0.182F, 0F));

        PartDefinition EquipRC302a = EquipRC3Base02.addOrReplaceChild("EquipRC302a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipRC302b = EquipRC302a.addOrReplaceChild("EquipRC302b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipRC3Radar02 = EquipRC3Base02.addOrReplaceChild("EquipRC3Radar02", CubeListBuilder.create().texOffs(128, 38).mirror().addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-6.4F, -4F, -1F));

        PartDefinition EquipRC303a = EquipRC3Base02.addOrReplaceChild("EquipRC303a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.6F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipRC303b = EquipRC303a.addOrReplaceChild("EquipRC303b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipRC3Radar01 = EquipRC3Base02.addOrReplaceChild("EquipRC3Radar01", CubeListBuilder.create().texOffs(128, 38).addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(4.4F, -4F, -1F));

        PartDefinition EquipRC301a = EquipRC3Base02.addOrReplaceChild("EquipRC301a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, -3F, -6F, -0.1745F, 0F, 0F));

        PartDefinition EquipRC301b = EquipRC301a.addOrReplaceChild("EquipRC301b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipRC2Base01 = EquipR04.addOrReplaceChild("EquipRC2Base01", CubeListBuilder.create().texOffs(211, 23).addBox(-4F, 0F, 0F, 8F, 6F, 7F, new CubeDeformation(0F)), PartPose.offset(-2.5F, -4F, -10.5F));

        PartDefinition EquipRC2Base02 = EquipRC2Base01.addOrReplaceChild("EquipRC2Base02", CubeListBuilder.create().texOffs(128, 0).addBox(-4.5F, -5F, -5.5F, 9F, 5F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 4F, -0.0524F, 0F, 0F));

        PartDefinition EquipRC2Radar02 = EquipRC2Base02.addOrReplaceChild("EquipRC2Radar02", CubeListBuilder.create().texOffs(128, 38).mirror().addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-6.4F, -4F, -1F));

        PartDefinition EquipRC2Radar01 = EquipRC2Base02.addOrReplaceChild("EquipRC2Radar01", CubeListBuilder.create().texOffs(128, 38).addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(4.4F, -4F, -1F));

        PartDefinition EquipRC203a = EquipRC2Base02.addOrReplaceChild("EquipRC203a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.6F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipRC203b = EquipRC203a.addOrReplaceChild("EquipRC203b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipRC202a = EquipRC2Base02.addOrReplaceChild("EquipRC202a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipRC202b = EquipRC202a.addOrReplaceChild("EquipRC202b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipRC201a = EquipRC2Base02.addOrReplaceChild("EquipRC201a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, -3F, -6F, -0.1745F, 0F, 0F));

        PartDefinition EquipRC201b = EquipRC201a.addOrReplaceChild("EquipRC201b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipR05 = EquipR04.addOrReplaceChild("EquipR05", CubeListBuilder.create().texOffs(174, 36).addBox(0F, 0F, -10F, 5F, 13F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, -2.5F, -13F, 0F, -0.7854F, 0F));

        PartDefinition EquipMCBase01a = EquipR01.addOrReplaceChild("EquipMCBase01a", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 4F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 8F, 0F, 1.0472F, 0F, 0F));

        PartDefinition EquipLCBase01_1 = EquipMCBase01a.addOrReplaceChild("EquipLCBase01_1", CubeListBuilder.create().texOffs(196, 16).addBox(-8F, -5F, -7F, 16F, 8F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-8F, 7F, 3F, -2.5953F, 0F, 0F));

        PartDefinition EquipLCBase02_1 = EquipLCBase01_1.addOrReplaceChild("EquipLCBase02_1", CubeListBuilder.create().texOffs(128, 0).addBox(-8.5F, -8F, -10F, 17F, 8F, 21F, new CubeDeformation(0F)), PartPose.offset(0.5F, -4.5F, 0F));

        PartDefinition EquipMCRadar02 = EquipLCBase02_1.addOrReplaceChild("EquipMCRadar02", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(0F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(-13.3F, -7F, 2F));

        PartDefinition EquipLC02a_1 = EquipLCBase02_1.addOrReplaceChild("EquipLC02a_1", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.5F, -9F, -0.2618F, 0F, 0F));

        PartDefinition EquipLC02b_1 = EquipLC02a_1.addOrReplaceChild("EquipLC02b_1", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipLC01a_1 = EquipLCBase02_1.addOrReplaceChild("EquipLC01a_1", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -4.5F, -9F, -0.1745F, 0F, 0F));

        PartDefinition EquipLC01b_1 = EquipLC01a_1.addOrReplaceChild("EquipLC01b_1", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipLC03a_1 = EquipLCBase02_1.addOrReplaceChild("EquipLC03a_1", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, -4.5F, -9F, -0.1396F, 0F, 0F));

        PartDefinition EquipLC03b_1 = EquipLC03a_1.addOrReplaceChild("EquipLC03b_1", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipMCRadar01 = EquipLCBase02_1.addOrReplaceChild("EquipMCRadar01", CubeListBuilder.create().texOffs(58, 0).addBox(0F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(8.3F, -7F, 2F));

        PartDefinition EquipL01 = EquipBaseM01b.addOrReplaceChild("EquipL01", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 16F, 8F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 5.5F, 3F, -0.8727F, 0F, 0F));

        PartDefinition EquipL02 = EquipL01.addOrReplaceChild("EquipL02", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 13F, 10F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(13.5F, -0.5F, 0.6F, 0F, 0.5236F, 0F));

        PartDefinition EquipL03 = EquipL02.addOrReplaceChild("EquipL03", CubeListBuilder.create().texOffs(128, 29).addBox(0F, 0F, -14F, 6F, 22F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(10.5F, -2.5F, -1F, 0F, -0.6981F, 0F));

        PartDefinition EquipLCBase01 = EquipL03.addOrReplaceChild("EquipLCBase01", CubeListBuilder.create().texOffs(196, 16).addBox(-7.5F, -5F, -7F, 16F, 9F, 14F, new CubeDeformation(0F)), PartPose.offset(3F, 3F, -5.5F));

        PartDefinition EquipLCBase02 = EquipLCBase01.addOrReplaceChild("EquipLCBase02", CubeListBuilder.create().texOffs(128, 0).addBox(-8.5F, -8F, -7F, 17F, 8F, 21F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, -4.5F, -2F, -0.0524F, 0F, 0F));

        PartDefinition EquipLC02a = EquipLCBase02.addOrReplaceChild("EquipLC02a", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -4.5F, -6F, -0.2618F, 0F, 0F));

        PartDefinition EquipLC02b = EquipLC02a.addOrReplaceChild("EquipLC02b", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipLCRadar02 = EquipLCBase02.addOrReplaceChild("EquipLCRadar02", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(0F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(-13.3F, -7F, 5F));

        PartDefinition EquipLCRadar01 = EquipLCBase02.addOrReplaceChild("EquipLCRadar01", CubeListBuilder.create().texOffs(58, 0).addBox(0F, 0F, 0F, 5F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(8.3F, -7F, 5F));

        PartDefinition EquipLC03a = EquipLCBase02.addOrReplaceChild("EquipLC03a", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5F, -4.5F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipLC03b = EquipLC03a.addOrReplaceChild("EquipLC03b", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipLC01a = EquipLCBase02.addOrReplaceChild("EquipLC01a", CubeListBuilder.create().texOffs(128, 118).addBox(-2F, -2F, -5F, 4F, 4F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -4.5F, -6F, -0.1745F, 0F, 0F));

        PartDefinition EquipLC01b = EquipLC01a.addOrReplaceChild("EquipLC01b", CubeListBuilder.create().texOffs(204, 39).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -5F));

        PartDefinition EquipL04 = EquipL03.addOrReplaceChild("EquipL04", CubeListBuilder.create().texOffs(128, 70).addBox(0F, 0F, -13F, 6F, 11F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 11F, -12.7F, 0F, 0.2094F, 0F));

        PartDefinition EquipLC2Base01 = EquipL04.addOrReplaceChild("EquipLC2Base01", CubeListBuilder.create().texOffs(211, 23).addBox(-4F, 0F, 0F, 8F, 6F, 7F, new CubeDeformation(0F)), PartPose.offset(2.5F, -4F, -10.5F));

        PartDefinition EquipLC2Base02 = EquipLC2Base01.addOrReplaceChild("EquipLC2Base02", CubeListBuilder.create().texOffs(128, 0).addBox(-4.5F, -5F, -5.5F, 9F, 5F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 4F, -0.0524F, -0.2731F, 0F));

        PartDefinition EquipLC203a = EquipLC2Base02.addOrReplaceChild("EquipLC203a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.6F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipLC203b = EquipLC203a.addOrReplaceChild("EquipLC203b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipLC2Radar02 = EquipLC2Base02.addOrReplaceChild("EquipLC2Radar02", CubeListBuilder.create().texOffs(128, 38).mirror().addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-6.4F, -4F, -1F));

        PartDefinition EquipLC2Radar01 = EquipLC2Base02.addOrReplaceChild("EquipLC2Radar01", CubeListBuilder.create().texOffs(128, 38).addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(4.4F, -4F, -1F));

        PartDefinition EquipLC201a = EquipLC2Base02.addOrReplaceChild("EquipLC201a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, -3F, -6F, -0.1745F, 0F, 0F));

        PartDefinition EquipLC201b = EquipLC201a.addOrReplaceChild("EquipLC201b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipLC202a = EquipLC2Base02.addOrReplaceChild("EquipLC202a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipLC202b = EquipLC202a.addOrReplaceChild("EquipLC202b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipLC3Base01 = EquipL04.addOrReplaceChild("EquipLC3Base01", CubeListBuilder.create().texOffs(211, 23).addBox(-4F, 0F, 0F, 8F, 2F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 5F, -12F, 0F, 0F, 1.5708F));

        PartDefinition EquipLC3Base02 = EquipLC3Base01.addOrReplaceChild("EquipLC3Base02", CubeListBuilder.create().texOffs(128, 0).addBox(-4.5F, -5F, -5.5F, 9F, 5F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.5F, 4F, 0.0524F, 0.1367F, 0F));

        PartDefinition EquipLC3Radar02 = EquipLC3Base02.addOrReplaceChild("EquipLC3Radar02", CubeListBuilder.create().texOffs(128, 38).mirror().addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(-6.4F, -4F, -1F));

        PartDefinition EquipLC3Radar01 = EquipLC3Base02.addOrReplaceChild("EquipLC3Radar01", CubeListBuilder.create().texOffs(128, 38).addBox(0F, 0F, 0F, 2F, 2F, 4F, new CubeDeformation(0F)), PartPose.offset(4.4F, -4F, -1F));

        PartDefinition EquipLC303a = EquipLC3Base02.addOrReplaceChild("EquipLC303a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.6F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipLC303b = EquipLC303a.addOrReplaceChild("EquipLC303b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipLC302a = EquipLC3Base02.addOrReplaceChild("EquipLC302a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -3F, -6F, -0.1396F, 0F, 0F));

        PartDefinition EquipLC302b = EquipLC302a.addOrReplaceChild("EquipLC302b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipLC301a = EquipLC3Base02.addOrReplaceChild("EquipLC301a", CubeListBuilder.create().texOffs(128, 122).addBox(-1F, -1F, -2F, 2F, 2F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, -3F, -6F, -0.1745F, 0F, 0F));

        PartDefinition EquipLC301b = EquipLC301a.addOrReplaceChild("EquipLC301b", CubeListBuilder.create().texOffs(163, 30).addBox(-0.5F, -0.5F, -9F, 1F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -2F));

        PartDefinition EquipL05 = EquipL04.addOrReplaceChild("EquipL05", CubeListBuilder.create().texOffs(174, 36).addBox(-5F, 0F, -10F, 5F, 13F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, -2.5F, -13F, 0F, 0.7854F, 0F));

        PartDefinition EquipBaseM02 = EquipBaseM01b.addOrReplaceChild("EquipBaseM02", CubeListBuilder.create().texOffs(128, 0).addBox(-9F, 0F, 0F, 18F, 10F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1.3F, 7.7F, -0.5918F, 0F, 0F));

        PartDefinition EquipBaseM03 = EquipBaseM02.addOrReplaceChild("EquipBaseM03", CubeListBuilder.create().texOffs(128, 95).addBox(-3.5F, -15F, 0F, 7F, 15F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6F, -2.5F, -0.6981F, 0F, 0F));

        PartDefinition EquipBaseBelt2 = EquipBaseBelt.addOrReplaceChild("EquipBaseBelt2", CubeListBuilder.create().texOffs(210, 0).addBox(-7F, 0F, -4F, 14F, 6F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -8.7F, 2.5F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 29).addBox(-3F, -1F, -2.5F, 5F, 14F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.7F, -0.7F, 0.2618F, 0F, 0.2094F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(20, 29).addBox(0F, 0F, -5F, 5F, 14F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3F, 13F, 2.5F, -1.4835F, 0F, 0F));

        PartDefinition EquipU01 = ArmRight02.addOrReplaceChild("EquipU01", CubeListBuilder.create().texOffs(128, 0).addBox(0F, -4F, 0F, 1F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 13F, -5F, -1.7453F, 2.4086F, -1.9199F));

        PartDefinition EquipU01a = EquipU01.addOrReplaceChild("EquipU01a", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -12F, 0F));

        PartDefinition EquipU02 = EquipU01.addOrReplaceChild("EquipU02", CubeListBuilder.create().texOffs(222, 32).addBox(-1F, 0F, -1F, 2F, 3F, 2F, new CubeDeformation(0F)), PartPose.offset(0.5F, -15F, 0.5F));

        PartDefinition EquipU09a = EquipU02.addOrReplaceChild("EquipU09a", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 10F, 1F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5F, -23F, 6F, -0.2618F, 0F, 0F));

        PartDefinition EquipU06 = EquipU02.addOrReplaceChild("EquipU06", CubeListBuilder.create().texOffs(166, 60).addBox(-8F, 0F, -8F, 16F, 1F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -31.1F, 5.5F, -0.1379F, 0.7854F, -0.096F));

        PartDefinition EquipU07 = EquipU06.addOrReplaceChild("EquipU07", CubeListBuilder.create().texOffs(214, 66).addBox(0F, -1F, 0F, 9F, 1F, 9F, new CubeDeformation(0F)), PartPose.offset(-4.5F, 0F, -4.5F));

        PartDefinition EquipU08 = EquipU07.addOrReplaceChild("EquipU08", CubeListBuilder.create().texOffs(214, 61).addBox(0F, -2F, 0F, 4F, 1F, 4F, new CubeDeformation(0F)), PartPose.offset(2.5F, 0F, 2.5F));

        PartDefinition EquipU09b = EquipU02.addOrReplaceChild("EquipU09b", CubeListBuilder.create().texOffs(128, 0).addBox(-0.4F, 0F, 0F, 1F, 1F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -24F, -3F, 0F, 0.5061F, 0.2618F));

        PartDefinition EquipU04a = EquipU02.addOrReplaceChild("EquipU04a", CubeListBuilder.create().texOffs(128, 0).addBox(-0.5F, -8F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 0.1F, 0.3F, -0.2618F, 0F, 0.2094F));

        PartDefinition EquipU04b = EquipU04a.addOrReplaceChild("EquipU04b", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -16F, -0.5F));

        PartDefinition EquipU04c = EquipU04b.addOrReplaceChild("EquipU04c", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, 0F));

        PartDefinition EquipU04d = EquipU04c.addOrReplaceChild("EquipU04d", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, 0F));

        PartDefinition EquipU03a = EquipU02.addOrReplaceChild("EquipU03a", CubeListBuilder.create().texOffs(128, 0).addBox(-0.5F, -8F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.1F, -0.3F, 0.1047F, 0F, 0F));

        PartDefinition EquipU03b = EquipU03a.addOrReplaceChild("EquipU03b", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -16F, -0.5F));

        PartDefinition EquipU03c = EquipU03b.addOrReplaceChild("EquipU03c", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, 0F));

        PartDefinition EquipU03d = EquipU03c.addOrReplaceChild("EquipU03d", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, 0F));

        PartDefinition EquipU05a = EquipU02.addOrReplaceChild("EquipU05a", CubeListBuilder.create().texOffs(128, 0).addBox(-0.5F, -8F, -0.5F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.2F, 0.1F, 0.3F, -0.2618F, 0F, -0.2094F));

        PartDefinition EquipU05b = EquipU05a.addOrReplaceChild("EquipU05b", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -16F, -0.5F));

        PartDefinition EquipU05c = EquipU05b.addOrReplaceChild("EquipU05c", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, 0F));

        PartDefinition EquipU05d = EquipU05c.addOrReplaceChild("EquipU05d", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, -8F, 0F));

        PartDefinition EquipU09c = EquipU02.addOrReplaceChild("EquipU09c", CubeListBuilder.create().texOffs(128, 0).addBox(-0.6F, 0F, 0F, 1F, 1F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -24F, -3F, 0F, -0.5061F, -0.2618F));

        PartDefinition EquipU01b = EquipU01.addOrReplaceChild("EquipU01b", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, 0F, 1F, 8F, 1F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 16).addBox(-4.5F, -2F, -5F, 9F, 3F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.7F, -0.2F, 0.2094F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -1F, 0F, -0.1047F, 0F, 0F));

        PartDefinition EquipHeadBase = Head.addOrReplaceChild("EquipHeadBase", CubeListBuilder.create().texOffs(128, 0).addBox(-8F, 0F, 0F, 16F, 4F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, -9.5F, 0F));

        PartDefinition HeadEquip02a = EquipHeadBase.addOrReplaceChild("HeadEquip02a", CubeListBuilder.create().texOffs(128, 0).addBox(-2F, 0F, -2F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offset(-8F, 0.2F, 5F));

        PartDefinition HeadEquip02d = HeadEquip02a.addOrReplaceChild("HeadEquip02d", CubeListBuilder.create().texOffs(91, 64).addBox(0F, 0F, 0F, 0F, 4F, 2F, new CubeDeformation(0F)), PartPose.offset(-3.5F, 0.2F, -1F));

        PartDefinition HeadEquip02b = HeadEquip02a.addOrReplaceChild("HeadEquip02b", CubeListBuilder.create().texOffs(128, 0).addBox(-4F, -1F, -1F, 4F, 1F, 2F, new CubeDeformation(0F)), PartPose.offset(-2F, 1.5F, 0.5F));

        PartDefinition HeadEquip02b2 = HeadEquip02b.addOrReplaceChild("HeadEquip02b2", CubeListBuilder.create().texOffs(128, 0).addBox(-1.5F, -1.5F, 0F, 3F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(-4F, 0F, -2F));

        PartDefinition HeadEquip02c = HeadEquip02a.addOrReplaceChild("HeadEquip02c", CubeListBuilder.create().texOffs(43, 82).mirror().addBox(0F, 0F, 0F, 7F, 4F, 0F, new CubeDeformation(0F)), PartPose.offset(-7F, -3.5F, 0.5F));

        PartDefinition HeadEquip01a = EquipHeadBase.addOrReplaceChild("HeadEquip01a", CubeListBuilder.create().texOffs(128, 0).addBox(0F, 0F, -2F, 2F, 3F, 4F, new CubeDeformation(0F)), PartPose.offset(8F, 0.2F, 5F));

        PartDefinition HeadEquip01d = HeadEquip01a.addOrReplaceChild("HeadEquip01d", CubeListBuilder.create().texOffs(91, 64).addBox(0F, 0F, 0F, 0F, 4F, 2F, new CubeDeformation(0F)), PartPose.offset(3.5F, 0.2F, -1F));

        PartDefinition HeadEquip01c = HeadEquip01a.addOrReplaceChild("HeadEquip01c", CubeListBuilder.create().texOffs(43, 82).addBox(0F, 0F, 0F, 7F, 4F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, -3.5F, 0.5F));

        PartDefinition HeadEquip01b = HeadEquip01a.addOrReplaceChild("HeadEquip01b", CubeListBuilder.create().texOffs(128, 0).mirror().addBox(0F, -1F, -1F, 4F, 1F, 2F, new CubeDeformation(0F)), PartPose.offset(2F, 1.5F, 0.5F));

        PartDefinition HeadEquip01b2 = HeadEquip01b.addOrReplaceChild("HeadEquip01b2", CubeListBuilder.create().texOffs(128, 0).addBox(-1.5F, -1.5F, 0F, 3F, 2F, 3F, new CubeDeformation(0F)), PartPose.offset(4F, 0F, -2F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 81).addBox(-8F, -8F, -7.4F, 16F, 12F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.2F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(40, 89).mirror().addBox(-1F, 0F, 0F, 2F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7F, 1F, -3F, -0.4014F, 0.1745F, -0.0873F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(86, 101).mirror().addBox(-1F, 0F, 0F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2F, 8F, 0.3F, 0.2967F, 0F, 0.3142F));

        PartDefinition HairR03 = HairR02.addOrReplaceChild("HairR03", CubeListBuilder.create().texOffs(86, 101).mirror().addBox(-1F, 0F, 0F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1F, 9F, 0.1F, 0.1396F, 0F, -0.2269F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(56, 23).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -8.8F, -5.7F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(40, 89).addBox(-1F, 0F, 0F, 2F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 1F, -3F, -0.3665F, -0.1745F, 0.0873F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(86, 101).addBox(-1F, 0F, 0F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.2F, 8F, 0.3F, 0.2269F, 0F, -0.3142F));

        PartDefinition HairL03 = HairL02.addOrReplaceChild("HairL03", CubeListBuilder.create().texOffs(86, 101).addBox(-1F, 0F, 0F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1F, 9F, 0.1F, 0.1745F, 0F, 0.2269F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(104, 29).addBox(0F, -4F, -11.5F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -9F, -5.5F, 0.1745F, 0.6981F, 0F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(159, 107).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition HairBase = HairMain.addOrReplaceChild("HairBase", CubeListBuilder.create().texOffs(102, 35).addBox(-5F, 0F, -0.7F, 10F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -0.5F, 5.5F, 0.8727F, 0F, 0F));

        PartDefinition Hair00 = HairBase.addOrReplaceChild("Hair00", CubeListBuilder.create().texOffs(170, 81).addBox(-3.5F, 0F, -4F, 7F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 0.2F, 2.5F));

        PartDefinition Hair01 = Hair00.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(166, 78).addBox(-4F, -1F, -0.2F, 8F, 20F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.7F, 1.3F, -0.7285F, 0F, -0.3643F));

        PartDefinition Hair02 = Hair01.addOrReplaceChild("Hair02", CubeListBuilder.create().texOffs(169, 80).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 18F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 16.5F, 5F, -0.3491F, 0F, -0.2731F));

        PartDefinition Hair03 = Hair02.addOrReplaceChild("Hair03", CubeListBuilder.create().texOffs(170, 81).addBox(-3.5F, 0F, -4F, 7F, 16F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 15F, 1F, 0.2618F, 0F, 0.3643F));

        PartDefinition Hair04 = Hair03.addOrReplaceChild("Hair04", CubeListBuilder.create().texOffs(209, 108).addBox(-3F, 0F, -3.2F, 6F, 15F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13F, 0F, -0.3491F, 0F, 0.2731F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(52, 65).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3142F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.2793F, 0F, 0.1396F));

        PartDefinition EquipLegL01 = LegLeft01.addOrReplaceChild("EquipLegL01", CubeListBuilder.create().texOffs(154, 12).mirror().addBox(-3.5F, 0F, -3.5F, 7F, 2F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

        PartDefinition EquipLegL02c = EquipLegL01.addOrReplaceChild("EquipLegL02c", CubeListBuilder.create().texOffs(0, 84).addBox(0F, 0F, 0F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -0.7F, -2.5F, 0F, 0F, -0.0524F));

        PartDefinition EquipLegL02a = EquipLegL01.addOrReplaceChild("EquipLegL02a", CubeListBuilder.create().texOffs(0, 84).addBox(0F, 0F, 0F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.4F, -0.9F, -0.9F, 0F, 0F, -0.0524F));

        PartDefinition EquipLegL02b = EquipLegL01.addOrReplaceChild("EquipLegL02b", CubeListBuilder.create().texOffs(0, 84).addBox(0F, 0F, 0F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.2F, -0.7F, 0.7F, 0F, 0F, -0.0524F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 83).mirror().addBox(-3F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesL01 = LegLeft02.addOrReplaceChild("ShoesL01", CubeListBuilder.create().texOffs(18, 80).mirror().addBox(-3.5F, 0F, -0.5F, 7F, 2F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 0F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(0, 48).addBox(-8.5F, 0F, -6F, 17F, 4F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.3F, 0F, -0.1396F, 0F, 0F));

        PartDefinition Skirt02 = Skirt01.addOrReplaceChild("Skirt02", CubeListBuilder.create().texOffs(42, 51).addBox(-9F, 0F, -6F, 18F, 4F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2.9F, -0.4F, -0.0873F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(226, 83).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.1396F, 0F, -0.1396F));

        PartDefinition EquipLegR01 = LegRight01.addOrReplaceChild("EquipLegR01", CubeListBuilder.create().texOffs(133, 8).addBox(-3.5F, 0F, -3.5F, 7F, 2F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 9F, 0F));

        PartDefinition EquipLegR02a = EquipLegR01.addOrReplaceChild("EquipLegR02a", CubeListBuilder.create().texOffs(0, 84).addBox(-1F, 0F, 0F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -0.7F, -2.5F, 0F, 0F, 0.0524F));

        PartDefinition EquipLegR02c = EquipLegR01.addOrReplaceChild("EquipLegR02c", CubeListBuilder.create().texOffs(0, 84).addBox(-1F, 0F, 0F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.2F, -0.7F, 0.7F, 0F, 0F, 0.0524F));

        PartDefinition EquipLegR02b = EquipLegR01.addOrReplaceChild("EquipLegR02b", CubeListBuilder.create().texOffs(0, 84).addBox(-1F, 0F, 0F, 1F, 4F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.4F, -0.8F, -0.9F, 0F, 0F, 0.0524F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(201, 83).addBox(-3F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 14F, -3F));

        PartDefinition ShoesR01 = LegRight02.addOrReplaceChild("ShoesR01", CubeListBuilder.create().texOffs(18, 80).addBox(-3.5F, 0F, -0.5F, 7F, 2F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 7F, 0F));

        PartDefinition AnchorL = Butt.addOrReplaceChild("AnchorL", CubeListBuilder.create().texOffs(24, 90).mirror().addBox(0F, 0F, -3F, 1F, 7F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.7F, 2F, -2F, 0F, 0F, -0.3491F));

        PartDefinition AnchorR = Butt.addOrReplaceChild("AnchorR", CubeListBuilder.create().texOffs(24, 90).addBox(-1F, 0F, -3F, 1F, 7F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.7F, 2F, -2F, 0F, 0F, 0.3491F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -15F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -10.7F, -0.2F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, 0F));

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

        applyBasePose(ctx, ageInTicks, headPitch, limbSwingAmount);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks, limbSwingAmount);
        applyHairAnimation(ctx, ageInTicks, limbSwing, limbSwingAmount);

        syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        LegLeft02.z = legLeft02DefaultZ;
        LegRight02.z = legRight02DefaultZ;

        ArmLeft02.x = armLeft02DefaultX;
        ArmLeft02.y = armLeft02DefaultY;
        ArmLeft02.z = armLeft02DefaultZ;

        ArmRight02.x = armRight02DefaultX;
        ArmRight02.y = armRight02DefaultY;
        ArmRight02.z = armRight02DefaultZ;
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) return;
        if (EquipBaseBelt != null)
            EquipBaseBelt.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipYamato.EQUIP_BELT);
        if (EquipHeadBase != null)
            EquipHeadBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipYamato.EQUIP_HEAD_BASE);
        if (EquipU01 != null)
            EquipU01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipYamato.EQUIP_UPPER);
        boolean showLeg = entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipYamato.EQUIP_LEG);
        if (EquipLegR01 != null) EquipLegR01.visible = showLeg;
        if (EquipLegL01 != null) EquipLegL01.visible = showLeg;
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        Head.xRot = -0.2618F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        BoobL.xRot = -1.0F;
        BoobR.xRot = -1.0F;
        Cloth02a.xRot = -1.0F;
        Ahoke.yRot = -1.0F;

        BodyMain.xRot = 1.2217F;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 1.2217F;
        Butt.xRot = -0.05F;

        ArmLeft01.xRot = -0.35F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = -3.0F;
        ArmLeft02.xRot = 0.0F;

        ArmRight01.xRot = -0.35F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -0.35F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = -0.8727F;

        LegLeft01.xRot = -0.14F;
        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.09F;
        LegLeft02.xRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.xRot = -1.2217F;
        LegRight01.yRot = -0.5236F;
        LegRight01.zRot = 0.0F;
        LegRight02.xRot = 1.0472F;
        LegRight02.zRot = 0.0F;

        AnchorL.xRot = -0.2F;
        AnchorR.xRot = -0.2F;
        AnchorR.zRot = 0.35F;

        if (EquipU01 != null) EquipU01.visible = false;
        if (EquipBaseBelt != null) EquipBaseBelt.visible = false;
    }

    private void applyBasePose(PoseContext ctx, float ageInTicks, float headPitch, float limbSwingAmount) {
        float angleX = ctx.angleX;

        BoobL.xRot = angleX * 0.06F - 0.75F;
        BoobR.xRot = angleX * 0.06F - 0.75F;
        Cloth02a.xRot = angleX * 0.06F - 0.7F;
        Ahoke.yRot = angleX * 0.25F + 0.45F;

        BodyMain.xRot = -0.1047F;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;
        Butt.xRot = 0.3142F;

        ArmLeft01.xRot = ctx.angleAdd2 * 0.25F + 0.18F;
        ArmLeft01.yRot = 0.0F;
        ArmLeft01.zRot = angleX * 0.03F - 0.26F;
        ArmLeft02.xRot = 0.0F;

        ArmRight01.xRot = ctx.angleAdd1 * 0.25F + 0.18F;
        ArmRight01.yRot = 0.0F;
        ArmRight01.zRot = -angleX * 0.03F + 0.26F;
        ArmRight02.xRot = 0.0F;
        ArmRight02.zRot = 0.0F;

        if (EquipU01 != null) EquipU01.yRot = 2.4F;

        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = 0.1396F;
        LegLeft02.xRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -0.1396F;
        LegRight02.xRot = 0.0F;
        LegRight02.zRot = 0.0F;

        AnchorL.xRot = limbSwingAmount * 0.5F - 0.2F;
        AnchorR.xRot = limbSwingAmount * 0.5F - 0.2F;
        AnchorR.zRot = 0.35F;

        EquipRotateBase.xRot = 0.0F;
        EquipLCBase02_1.yRot = 3.1415F;

        if (Head.xRot <= 0.0F) {
            EquipLC01a.xRot = Head.xRot * 0.7F;
            EquipLC02a.xRot = Head.xRot;
            EquipLC03a.xRot = Head.xRot * 0.8F;
            EquipLC201a.xRot = Head.xRot * 1.2F;
            EquipLC202a.xRot = Head.xRot;
            EquipLC203a.xRot = Head.xRot * 0.9F;
            EquipRC01a.xRot = Head.xRot * 0.9F;
            EquipRC02a.xRot = Head.xRot;
            EquipRC03a.xRot = Head.xRot * 0.75F;
            EquipRC201a.xRot = Head.xRot * 0.85F;
            EquipRC202a.xRot = Head.xRot * 1.1F;
            EquipRC203a.xRot = Head.xRot;
        }

        EquipLCBase02.yRot = Head.yRot * 1.3F;
        EquipLC2Base02.yRot = Head.yRot * 1.45F;
        EquipLC3Base02.yRot = -Head.xRot;
        EquipRCBase02.yRot = Head.yRot * 1.3F;
        EquipRC2Base02.yRot = Head.yRot * 1.45F;
        EquipRC3Base02.yRot = Head.xRot;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks, float limbSwingAmount) {
        float legAddLeft = ctx.angleAdd1 * 0.5F - 0.2793F;
        float legAddRight = ctx.angleAdd2 * 0.5F - 0.1396F;
        boolean showCannon = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipYamato.EQUIP_BELT);
        boolean showUmbrella = entity != null && entity.getEquipFlag(org.trp.shincolle.entity.EntityBattleshipYamato.EQUIP_UPPER);

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = entity != null && entity.isSprinting() || limbSwingAmount > 0.1F;

        if (isSprinting) {
            Hair01.xRot += limbSwingAmount * 0.25F;
            ArmLeft01.zRot += limbSwingAmount * -0.25F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            Head.xRot -= 1.0472F;
            BodyMain.xRot = 1.0472F;
            Butt.xRot = -0.8378F;

            ArmLeft01.xRot = -0.7F;
            ArmLeft01.zRot = 0.2618F;
            if (showUmbrella) {
                ArmRight01.xRot -= 1.0472F;
            } else {
                ArmRight01.xRot = -0.7F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = -0.2618F;
                ArmRight02.xRot = 0.0F;
            }

            Hair01.xRot = -1.2109F;
            Hair01.zRot = -0.4363F;
            Hair02.xRot = -0.5236F;
            Hair02.zRot = -0.3491F;
            Hair03.xRot = 0.0F;
            Hair03.zRot = 0.4363F;
            Hair04.xRot = -0.3491F;
            Hair04.zRot = 0.2618F;

            if (showCannon) {
                EquipRotateBase.xRot -= 1.0472F;
            }
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (showCannon) {
                this.poseTranslateY += 0.4F * 3;
                Head.xRot -= 0.2F;
                BodyMain.xRot = -0.1396F;
                Butt.xRot = 0.1396F;
                ArmLeft01.xRot = -0.2094F;
                ArmLeft01.zRot = 0.2618F;
                if (showUmbrella) {
                    ArmRight01.xRot = 0.1745F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = 0.1571F;
                    ArmRight02.xRot = -1.4835F;
                } else {
                    ArmRight01.xRot = -0.2094F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.2618F;
                    ArmRight02.xRot = 0.0F;
                }
                legAddLeft = -1.0472F;
                legAddRight = -1.0472F;
                LegLeft01.yRot = 0.0524F;
                LegLeft01.zRot = 0.0F;
                LegLeft02.z = legLeft02DefaultZ + (0.38F * OFFSET_SCALE);
                LegLeft02.xRot = 2.5831F;
                LegLeft02.zRot = 0.0175F;
                LegRight01.yRot = -0.0524F;
                LegRight01.zRot = 0.0F;
                LegRight02.z = legRight02DefaultZ + (0.38F * OFFSET_SCALE);
                LegRight02.xRot = 2.5831F;
                LegRight02.zRot = -0.0175F;
                EquipLCBase02_1.yRot = 0.0F;
            } else if (entity != null && hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.5F * 2.5F;
                Head.xRot -= 0.21F;
                Head.yRot -= 0.4363F;
                BodyMain.xRot = 0.2618F;
                BodyMain.yRot = 0.35F;
                BodyMain.zRot = 0.4363F;
                Hair01.xRot = -0.95F;
                Hair01.zRot = -0.2618F;
                Hair02.xRot = -0.3491F;
                Hair02.zRot = -0.3491F;
                Hair03.xRot = -0.3491F;
                Hair03.zRot = -0.3491F;
                Hair04.xRot = -0.4363F;
                Hair04.zRot = -0.4363F;
                ArmLeft01.xRot = -0.35F;
                ArmLeft01.yRot = -0.5236F;
                ArmLeft01.zRot = -0.2618F;
                ArmLeft02.xRot = -0.5236F;
                if (showUmbrella) {
                    ArmRight01.xRot = 0.0F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.0524F;
                    ArmRight02.xRot = -1.0472F;
                } else {
                    ArmRight01.xRot = 0.0873F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = 0.0873F;
                    ArmRight02.xRot = -0.5236F;
                }
                legAddLeft = -0.0873F;
                legAddRight = -0.4363F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 1.0472F;
                LegLeft02.xRot = 0.4363F;
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = 0.925F;
                LegRight02.xRot = 0.5236F;
                EquipU01.yRot = 2.15F;
                EquipU01.zRot = -1.85F;
                AnchorR.zRot = 0.7F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                Head.xRot += 0.1047F;
                BodyMain.xRot = -0.1396F;
                Butt.xRot = 0.1396F;
                Hair01.xRot = -0.6108F;
                Hair01.zRot = -0.2618F;
                Hair02.xRot = -0.4363F;
                Hair02.zRot = 0.4363F;
                Hair03.xRot = -0.3491F;
                Hair03.zRot = 0.4363F;
                Hair04.xRot = -0.5236F;
                Hair04.zRot = 0.5236F;
                ArmLeft01.xRot = -0.2094F;
                ArmLeft01.zRot = 0.2618F;
                if (showUmbrella) {
                    ArmRight01.xRot = 0.1745F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = 0.1571F;
                    ArmRight02.xRot = -1.4835F;
                } else {
                    ArmRight01.xRot = -0.2094F;
                    ArmRight01.yRot = 0.0F;
                    ArmRight01.zRot = -0.2618F;
                    ArmRight02.xRot = 0.0F;
                }
                legAddLeft = -1.4835F;
                legAddRight = -1.4835F;
                LegLeft01.yRot = 0.0524F;
                LegLeft01.zRot = -1.4835F;
                LegLeft02.z = legLeft02DefaultZ + (0.38F * OFFSET_SCALE);
                LegLeft02.xRot = 2.1F;
                LegLeft02.zRot = 0.0175F;
                LegRight01.yRot = -0.0524F;
                LegRight01.zRot = 1.4835F;
                LegRight02.z = legRight02DefaultZ + (0.38F * OFFSET_SCALE);
                LegRight02.xRot = 1.9199F;
                LegRight02.zRot = -0.0175F;
            }
        }

        if (entity != null && entity.getAttackTick() > 0) {
            ArmLeft01.xRot = -1.5708F;
            ArmLeft01.yRot = -0.2F + Head.yRot;
            ArmLeft01.zRot = 0.0F;
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = Mth.sin(swing * swing * (float) Math.PI);
            float f8 = Mth.sin(Mth.sqrt(swing) * (float) Math.PI);
            ArmRight01.xRot = -0.2F;
            ArmRight01.yRot = 0.0F;
            ArmRight01.zRot = -0.1F;
            ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
            ArmRight02.xRot = 0.0F;
            ArmRight02.yRot = 0.0F;
            ArmRight02.zRot = 0.0F;
        }

        LegLeft01.xRot = legAddLeft;
        LegRight01.xRot = legAddRight;
    }

    private void applyHairAnimation(PoseContext ctx, float ageInTicks, float limbSwing, float limbSwingAmount) {
        float headZ = Head.zRot * -0.5F;
        float headX = Head.xRot * -0.5F - 0.05F;
        float angleX = ctx.angleX;
        float angleX1 = Mth.cos(ageInTicks * 0.08F + 0.3F + limbSwing * 0.5F);
        float angleX2 = Mth.cos(ageInTicks * 0.08F + 0.6F + limbSwing * 0.5F);
        float angleX3 = Mth.cos(ageInTicks * 0.08F + 0.9F + limbSwing * 0.5F);

        if (!this.isDeadPose && !this.isSittingPose) {
            Hair01.xRot = angleX * 0.03F - 0.7F;
            Hair01.zRot = 0.0F;
            Hair02.xRot = -angleX1 * 0.04F - 0.11F;
            Hair02.zRot = 0.0F;
            Hair03.xRot = -angleX2 * 0.07F - 0.05F;
            Hair03.zRot = 0.0F;
            Hair04.xRot = -angleX3 * 0.1F - 0.02F;
            Hair04.zRot = 0.0F;

            if (limbSwingAmount > 0.1F) {
                Hair01.xRot += limbSwingAmount * 0.25F;
            }
        } else if (this.isSittingPose) {
            Hair01.xRot = -0.6108F;
            Hair01.zRot = -0.2618F;
            Hair02.xRot = -0.4363F;
            Hair02.zRot = 0.4363F;
            Hair03.xRot = -0.3491F;
            Hair03.zRot = 0.4363F;
            Hair04.xRot = -0.5236F;
            Hair04.zRot = 0.5236F;
        } else {
            Hair01.zRot = -0.36F;
            Hair04.xRot = -0.35F;
            Hair04.zRot = 0.1F;
        }

        Hair01.xRot += headX;
        Hair02.xRot += headX * 0.5F;
        Hair03.xRot += headX * 0.2F;
        Hair04.xRot += headX * 0.2F;

        float movementShake = this.isSittingPose ? 0 : 1.0F;

        Hair01.zRot += (ctx.angleAdd1 * 0.04F * movementShake) + headZ;
        Hair02.zRot += (ctx.angleAdd2 * 0.06F * movementShake) + headZ * 0.8F;
        Hair03.zRot += (ctx.angleAdd2 * 0.08F * movementShake) + headZ * 0.4F;
        Hair04.zRot += (ctx.angleAdd2 * 0.1F * movementShake) + headZ * 0.4F;

        HairL01.zRot = headZ + 0.0873F;
        HairL02.zRot = headZ * 0.8F - 0.3142F;
        HairL03.zRot = headZ * 0.4F + 0.18F;
        HairR01.zRot = headZ - 0.0873F;
        HairR02.zRot = headZ * 0.8F + 0.25F;
        HairR03.zRot = headZ * 0.4F - 0.15F;

        HairL01.xRot = (angleX * 0.04F * movementShake) + headX - 0.28F;
        HairL02.xRot = (angleX1 * 0.05F * movementShake) + headX * 0.8F + 0.15F;
        HairL03.xRot = (angleX2 * 0.07F * movementShake) + headX * 0.4F + 0.05F;
        HairR01.xRot = (angleX * 0.04F * movementShake) + headX - 0.35F;
        HairR02.xRot = (angleX1 * 0.05F * movementShake) + headX * 0.8F + 0.18F;
        HairR03.xRot = (angleX2 * 0.07F * movementShake) + headX * 0.4F + 0.02F;
    }

    private void syncGlowParts() {
        if (this.GlowBodyMain != null) {
            GlowBodyMain.copyFrom(BodyMain);
            GlowNeck.copyFrom(Neck);
            GlowHead.copyFrom(Head);
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

        if (GlowBodyMain != null) {
            GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);
        }

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
