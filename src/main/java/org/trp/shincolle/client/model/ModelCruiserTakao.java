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

public class ModelCruiserTakao<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "cruiser_takao"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCruiserTakao");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelCruiserTakao");
    private static final float SITTING_TRANSLATE_Y = LegacyPoseOffsets.sittingY("ModelCruiserTakao");

    private static final float BODY_BASE_X_ROT = -0.1047F;
    private static final float BUTT_BASE_X_ROT = 0.35F;
    private static final float SKIRT_BASE_X_ROT = -0.17F;
    private static final float LEG_BASE_Z_ROT = 0.0873F;

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
    private final ModelPart Cloth01;
    private final ModelPart EquipBase;
    private final ModelPart EquipBag00;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart HairMain;
    private final ModelPart Hat01;
    private final ModelPart HairU01;
    private final ModelPart Ahoke;
    private final ModelPart Hair01;
    private final ModelPart Hat02;
    private final ModelPart Hat03;
    private final ModelPart LegLeft01;
    private final ModelPart Skirt01;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft02;
    private final ModelPart ShoeL03;
    private final ModelPart ShoeL01;
    private final ModelPart ShoeL02;
    private final ModelPart ShoeL04;
    private final ModelPart Belt01;
    private final ModelPart LegRight02;
    private final ModelPart ShoeL03_1;
    private final ModelPart ShoeR01;
    private final ModelPart ShoeR02;
    private final ModelPart ShoeL04_1;
    private final ModelPart ArmRight02;
    private final ModelPart ArmRight02a;
    private final ModelPart ArmRight02b;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmLeft02a;
    private final ModelPart ArmLeft02b;
    private final ModelPart Equip00;
    private final ModelPart EquipCannonBase;
    private final ModelPart EquipLIn01;
    private final ModelPart EquipRIn01;
    private final ModelPart EquipOut01;
    private final ModelPart EquipOut01_1;
    private final ModelPart EquipC01a;
    private final ModelPart EquipLIn02;
    private final ModelPart EquipLIn03;
    private final ModelPart EquipLIn07;
    private final ModelPart EquipLIn08;
    private final ModelPart EquipLIn09;
    private final ModelPart EquipLIn04;
    private final ModelPart EquipLIn06a;
    private final ModelPart EquipLIn05;
    private final ModelPart EquipLIn06b;
    private final ModelPart EquipRIn02;
    private final ModelPart EquipRIn03;
    private final ModelPart EquipRIn07;
    private final ModelPart EquipRIn08;
    private final ModelPart EquipRIn09;
    private final ModelPart EquipRIn04;
    private final ModelPart EquipRIn06a;
    private final ModelPart EquipRIn05;
    private final ModelPart EquipRIn06b;
    private final ModelPart EquipOut02;
    private final ModelPart EquipOut03;
    private final ModelPart EquipOut04;
    private final ModelPart EquipOut05;
    private final ModelPart EquipOut02_1;
    private final ModelPart EquipOut03_1;
    private final ModelPart EquipOut04_1;
    private final ModelPart EquipOut05_1;
    private final ModelPart EquipC01b;
    private final ModelPart EquipC01c;
    private final ModelPart EquipC01e;
    private final ModelPart EquipC01d;
    private final ModelPart EquipC01f;
    private final ModelPart EquipC01a_1;
    private final ModelPart EquipC01a_2;
    private final ModelPart EquipC01b_1;
    private final ModelPart EquipC01c_1;
    private final ModelPart EquipC01e_1;
    private final ModelPart EquipC01d_1;
    private final ModelPart EquipC01f_1;
    private final ModelPart EquipC01b_2;
    private final ModelPart EquipC01c_2;
    private final ModelPart EquipC01e_2;
    private final ModelPart EquipC01d_2;
    private final ModelPart EquipC01f_2;
    private final ModelPart EquipBag01;
    private final ModelPart EquipBag02;
    private final ModelPart EquipBag03;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowNeck;
    private final ModelPart GlowHead;
    private final float buttDefaultY;
    private final float skirt01DefaultY;
    private final float hair01DefaultY;
    private final float legLeft02DefaultX;
    private final float legLeft02DefaultY;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultX;
    private final float legRight02DefaultY;
    private final float legRight02DefaultZ;
    private final float armLeft02DefaultX;
    private final float armLeft02DefaultY;
    private final float armLeft02DefaultZ;
    private final float armRight02DefaultX;
    private final float armRight02DefaultY;
    private final float armRight02DefaultZ;

    public ModelCruiserTakao(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.EquipBase = this.BodyMain.getChild("EquipBase");
        this.Equip00 = this.EquipBase.getChild("Equip00");
        this.EquipOut01_1 = this.Equip00.getChild("EquipOut01_1");
        this.EquipOut02_1 = this.EquipOut01_1.getChild("EquipOut02_1");
        this.EquipOut03_1 = this.EquipOut02_1.getChild("EquipOut03_1");
        this.EquipOut04_1 = this.EquipOut03_1.getChild("EquipOut04_1");
        this.EquipOut05_1 = this.EquipOut04_1.getChild("EquipOut05_1");
        this.EquipC01a = this.Equip00.getChild("EquipC01a");
        this.EquipC01b = this.EquipC01a.getChild("EquipC01b");
        this.EquipC01c = this.EquipC01b.getChild("EquipC01c");
        this.EquipC01d = this.EquipC01c.getChild("EquipC01d");
        this.EquipC01e = this.EquipC01b.getChild("EquipC01e");
        this.EquipC01f = this.EquipC01e.getChild("EquipC01f");
        this.EquipOut01 = this.Equip00.getChild("EquipOut01");
        this.EquipOut02 = this.EquipOut01.getChild("EquipOut02");
        this.EquipOut03 = this.EquipOut02.getChild("EquipOut03");
        this.EquipOut04 = this.EquipOut03.getChild("EquipOut04");
        this.EquipOut05 = this.EquipOut04.getChild("EquipOut05");
        this.EquipLIn01 = this.Equip00.getChild("EquipLIn01");
        this.EquipLIn02 = this.EquipLIn01.getChild("EquipLIn02");
        this.EquipLIn09 = this.EquipLIn02.getChild("EquipLIn09");
        this.EquipLIn07 = this.EquipLIn02.getChild("EquipLIn07");
        this.EquipLIn08 = this.EquipLIn02.getChild("EquipLIn08");
        this.EquipLIn03 = this.EquipLIn02.getChild("EquipLIn03");
        this.EquipLIn04 = this.EquipLIn03.getChild("EquipLIn04");
        this.EquipLIn05 = this.EquipLIn04.getChild("EquipLIn05");
        this.EquipLIn06a = this.EquipLIn03.getChild("EquipLIn06a");
        this.EquipLIn06b = this.EquipLIn06a.getChild("EquipLIn06b");
        this.EquipRIn01 = this.Equip00.getChild("EquipRIn01");
        this.EquipRIn02 = this.EquipRIn01.getChild("EquipRIn02");
        this.EquipRIn09 = this.EquipRIn02.getChild("EquipRIn09");
        this.EquipRIn03 = this.EquipRIn02.getChild("EquipRIn03");
        this.EquipRIn04 = this.EquipRIn03.getChild("EquipRIn04");
        this.EquipRIn05 = this.EquipRIn04.getChild("EquipRIn05");
        this.EquipRIn06a = this.EquipRIn03.getChild("EquipRIn06a");
        this.EquipRIn06b = this.EquipRIn06a.getChild("EquipRIn06b");
        this.EquipRIn08 = this.EquipRIn02.getChild("EquipRIn08");
        this.EquipRIn07 = this.EquipRIn02.getChild("EquipRIn07");
        this.EquipCannonBase = this.EquipBase.getChild("EquipCannonBase");
        this.EquipC01a_1 = this.EquipCannonBase.getChild("EquipC01a_1");
        this.EquipC01b_1 = this.EquipC01a_1.getChild("EquipC01b_1");
        this.EquipC01c_1 = this.EquipC01b_1.getChild("EquipC01c_1");
        this.EquipC01d_1 = this.EquipC01c_1.getChild("EquipC01d_1");
        this.EquipC01e_1 = this.EquipC01b_1.getChild("EquipC01e_1");
        this.EquipC01f_1 = this.EquipC01e_1.getChild("EquipC01f_1");
        this.EquipC01a_2 = this.EquipCannonBase.getChild("EquipC01a_2");
        this.EquipC01b_2 = this.EquipC01a_2.getChild("EquipC01b_2");
        this.EquipC01e_2 = this.EquipC01b_2.getChild("EquipC01e_2");
        this.EquipC01f_2 = this.EquipC01e_2.getChild("EquipC01f_2");
        this.EquipC01c_2 = this.EquipC01b_2.getChild("EquipC01c_2");
        this.EquipC01d_2 = this.EquipC01c_2.getChild("EquipC01d_2");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.ArmRight02a = this.ArmRight02.getChild("ArmRight02a");
        this.ArmRight02b = this.ArmRight02a.getChild("ArmRight02b");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.HairMain = this.Head.getChild("HairMain");
        this.Hair01 = this.HairMain.getChild("Hair01");
        this.Hair = this.Head.getChild("Hair");
        this.HairU01 = this.Hair.getChild("HairU01");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.Hat01 = this.Head.getChild("Hat01");
        this.Hat02 = this.Hat01.getChild("Hat02");
        this.Hat03 = this.Hat02.getChild("Hat03");
        this.EquipBag00 = this.BodyMain.getChild("EquipBag00");
        this.EquipBag01 = this.EquipBag00.getChild("EquipBag01");
        this.EquipBag03 = this.EquipBag01.getChild("EquipBag03");
        this.EquipBag02 = this.EquipBag01.getChild("EquipBag02");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoeR01 = this.LegRight02.getChild("ShoeR01");
        this.ShoeR02 = this.ShoeR01.getChild("ShoeR02");
        this.ShoeL03_1 = this.LegRight01.getChild("ShoeL03_1");
        this.ShoeL04_1 = this.ShoeL03_1.getChild("ShoeL04_1");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoeL01 = this.LegLeft02.getChild("ShoeL01");
        this.ShoeL02 = this.ShoeL01.getChild("ShoeL02");
        this.ShoeL03 = this.LegLeft01.getChild("ShoeL03");
        this.ShoeL04 = this.ShoeL03.getChild("ShoeL04");
        this.Skirt01 = this.Butt.getChild("Skirt01");
        this.Belt01 = this.Skirt01.getChild("Belt01");
        this.Cloth01 = this.BodyMain.getChild("Cloth01");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.ArmLeft02a = this.ArmLeft02.getChild("ArmLeft02a");
        this.ArmLeft02b = this.ArmLeft02a.getChild("ArmLeft02b");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowNeck = this.GlowBodyMain.getChild("GlowNeck");
        this.GlowHead = this.GlowNeck.getChild("GlowHead");
        this.initFaceParts(this.GlowHead);
        this.buttDefaultY = this.Butt.y;
        this.skirt01DefaultY = this.Skirt01.y;
        this.hair01DefaultY = this.Hair01.y;
        this.legLeft02DefaultX = this.LegLeft02.x;
        this.legLeft02DefaultY = this.LegLeft02.y;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultX = this.LegRight02.x;
        this.legRight02DefaultY = this.LegRight02.y;
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

        PartDefinition EquipBase = BodyMain.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition Equip00 = EquipBase.addOrReplaceChild("Equip00", CubeListBuilder.create().texOffs(0, 0).addBox(-11.5F, -1F, -1.5F, 12F, 3F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3F, 3F, 0F, 1.5708F, 0F));

        PartDefinition EquipOut01_1 = Equip00.addOrReplaceChild("EquipOut01_1", CubeListBuilder.create().texOffs(30, 0).addBox(0F, 0F, -0.5F, 12F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.2F, -1F, 0.2F, -0.2618F, 1.4835F, 0F));

        PartDefinition EquipOut02_1 = EquipOut01_1.addOrReplaceChild("EquipOut02_1", CubeListBuilder.create().texOffs(33, 0).mirror().addBox(0F, 0F, 0F, 9F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 0F, -0.5F, 0F, -0.5236F, 0F));

        PartDefinition EquipOut03_1 = EquipOut02_1.addOrReplaceChild("EquipOut03_1", CubeListBuilder.create().texOffs(36, 0).addBox(0F, 0F, 0F, 6F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 0F, 0F, 0F, -0.6981F, 0F));

        PartDefinition EquipOut04_1 = EquipOut03_1.addOrReplaceChild("EquipOut04_1", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(0F, 0F, 0F, 6F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 0F, 0F, 0F, -0.6981F, 0F));

        PartDefinition EquipOut05_1 = EquipOut04_1.addOrReplaceChild("EquipOut05_1", CubeListBuilder.create().texOffs(0, 28).addBox(0F, 0F, -1F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, 2.5F, 0.5F, 0F, 0F, 0.3491F));

        PartDefinition EquipC01a = Equip00.addOrReplaceChild("EquipC01a", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -1.5F, -1.5F, 4F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(-13F, 0.5F, 0F));

        PartDefinition EquipC01b = EquipC01a.addOrReplaceChild("EquipC01b", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -9F, -4.5F, 4F, 13F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.9F, 0F, 0F, 0F, 0F, 0.0873F));

        PartDefinition EquipC01c = EquipC01b.addOrReplaceChild("EquipC01c", CubeListBuilder.create().texOffs(30, 9).addBox(-1.5F, -5F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offset(2F, -8F, -2.2F));

        PartDefinition EquipC01d = EquipC01c.addOrReplaceChild("EquipC01d", CubeListBuilder.create().texOffs(14, 22).addBox(-1F, 0F, -1F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14.9F, 0F));

        PartDefinition EquipC01e = EquipC01b.addOrReplaceChild("EquipC01e", CubeListBuilder.create().texOffs(30, 9).addBox(-1.5F, -5F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offset(2F, -8F, 2.2F));

        PartDefinition EquipC01f = EquipC01e.addOrReplaceChild("EquipC01f", CubeListBuilder.create().texOffs(14, 22).addBox(-1F, 0F, -1F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14.9F, 0F));

        PartDefinition EquipOut01 = Equip00.addOrReplaceChild("EquipOut01", CubeListBuilder.create().texOffs(30, 0).addBox(0F, 0F, -0.5F, 12F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-11.2F, -1F, -0.2F, 0.2618F, -1.4835F, 0F));

        PartDefinition EquipOut02 = EquipOut01.addOrReplaceChild("EquipOut02", CubeListBuilder.create().texOffs(33, 0).mirror().addBox(0F, 0F, -1F, 9F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12F, 0F, 0.5F, 0F, 0.5236F, 0F));

        PartDefinition EquipOut03 = EquipOut02.addOrReplaceChild("EquipOut03", CubeListBuilder.create().texOffs(36, 0).addBox(0F, 0F, -1F, 6F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(9F, 0F, 0F, 0F, 0.6981F, 0F));

        PartDefinition EquipOut04 = EquipOut03.addOrReplaceChild("EquipOut04", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(0F, 0F, -1F, 6F, 8F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 0F, 0F, 0F, 0.6981F, 0F));

        PartDefinition EquipOut05 = EquipOut04.addOrReplaceChild("EquipOut05", CubeListBuilder.create().texOffs(0, 28).addBox(0F, 0F, -1F, 1F, 6F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, 2.5F, -0.5F, 0F, 0F, 0.3491F));

        PartDefinition EquipLIn01 = Equip00.addOrReplaceChild("EquipLIn01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 7F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -1F, 1F, 0.0873F, -1.2217F, 0F));

        PartDefinition EquipLIn02 = EquipLIn01.addOrReplaceChild("EquipLIn02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -1F, 8F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 0F, 1F, 0F, 1.1345F, 0F));

        PartDefinition EquipLIn09 = EquipLIn02.addOrReplaceChild("EquipLIn09", CubeListBuilder.create().texOffs(6, 22).addBox(-1F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0F)), PartPose.offset(2.9F, -3.2F, -0.5F));

        PartDefinition EquipLIn07 = EquipLIn02.addOrReplaceChild("EquipLIn07", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 1F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.5F, -0.7F, -0.5F, 0F, 0.5236F, 0F));

        PartDefinition EquipLIn08 = EquipLIn02.addOrReplaceChild("EquipLIn08", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(5.2F, -1.9F, -1.3F));

        PartDefinition EquipLIn03 = EquipLIn02.addOrReplaceChild("EquipLIn03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -1F, 4F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 0F, 0F, 0F, 0.8727F, 0F));

        PartDefinition EquipLIn04 = EquipLIn03.addOrReplaceChild("EquipLIn04", CubeListBuilder.create().texOffs(0, 22).addBox(0F, 0F, 0F, 2F, 5F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 1.9F, -1.3F, 0F, 0.2618F, 0F));

        PartDefinition EquipLIn05 = EquipLIn04.addOrReplaceChild("EquipLIn05", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, 0F, 7F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 4.7F, 0.4F, 0F, 0.2618F, 0.0873F));

        PartDefinition EquipLIn06a = EquipLIn03.addOrReplaceChild("EquipLIn06a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.3F, -0.5F, -1.5F, 0F, 0.6981F, 0F));

        PartDefinition EquipLIn06b = EquipLIn06a.addOrReplaceChild("EquipLIn06b", CubeListBuilder.create().texOffs(6, 22).addBox(-1F, -3F, -1F, 2F, 3F, 2F, new CubeDeformation(0F)), PartPose.offset(-0.3F, 0F, 0F));

        PartDefinition EquipRIn01 = Equip00.addOrReplaceChild("EquipRIn01", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -1F, 7F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, -1F, -1F, -0.0873F, 1.2217F, 0F));

        PartDefinition EquipRIn02 = EquipRIn01.addOrReplaceChild("EquipRIn02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 8F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7F, 0F, -1F, 0F, -1.1345F, 0F));

        PartDefinition EquipRIn09 = EquipRIn02.addOrReplaceChild("EquipRIn09", CubeListBuilder.create().texOffs(6, 22).addBox(-1F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0F)), PartPose.offset(2.9F, -3.2F, 0.5F));

        PartDefinition EquipRIn03 = EquipRIn02.addOrReplaceChild("EquipRIn03", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4F, 2F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 0F, 0F, 0F, -0.8727F, 0F));

        PartDefinition EquipRIn04 = EquipRIn03.addOrReplaceChild("EquipRIn04", CubeListBuilder.create().texOffs(0, 22).addBox(0F, 0F, -1F, 2F, 5F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.5F, 1.9F, 1.3F, 0F, -0.2618F, 0F));

        PartDefinition EquipRIn05 = EquipRIn04.addOrReplaceChild("EquipRIn05", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -1F, 7F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, 4.7F, -0.4F, 0F, -0.2618F, 0.0873F));

        PartDefinition EquipRIn06a = EquipRIn03.addOrReplaceChild("EquipRIn06a", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.3F, -0.5F, 1.5F, 0F, -0.6981F, 0F));

        PartDefinition EquipRIn06b = EquipRIn06a.addOrReplaceChild("EquipRIn06b", CubeListBuilder.create().texOffs(6, 22).addBox(-1F, -3F, -1F, 2F, 3F, 2F, new CubeDeformation(0F)), PartPose.offset(-0.3F, 0F, 0F));

        PartDefinition EquipRIn08 = EquipRIn02.addOrReplaceChild("EquipRIn08", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2F, 2F, 2F, new CubeDeformation(0F)), PartPose.offset(5.2F, -1.9F, 1.3F));

        PartDefinition EquipRIn07 = EquipRIn02.addOrReplaceChild("EquipRIn07", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3F, 1F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8.5F, -0.7F, 0.5F, 0F, -0.5236F, 0F));

        PartDefinition EquipCannonBase = EquipBase.addOrReplaceChild("EquipCannonBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 5F, 0.2618F, 0F, 0F));

        PartDefinition EquipC01a_1 = EquipCannonBase.addOrReplaceChild("EquipC01a_1", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -1.5F, -1.5F, 4F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(20F, 5F, 0F));

        PartDefinition EquipC01b_1 = EquipC01a_1.addOrReplaceChild("EquipC01b_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, -9F, -4.5F, 4F, 13F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.9F, 0F, 0F, 0F, 0F, 0.0873F));

        PartDefinition EquipC01c_1 = EquipC01b_1.addOrReplaceChild("EquipC01c_1", CubeListBuilder.create().texOffs(30, 9).addBox(-1.5F, -5F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offset(2F, -8F, -2.2F));

        PartDefinition EquipC01d_1 = EquipC01c_1.addOrReplaceChild("EquipC01d_1", CubeListBuilder.create().texOffs(14, 22).addBox(-1F, 0F, -1F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14.9F, 0F));

        PartDefinition EquipC01e_1 = EquipC01b_1.addOrReplaceChild("EquipC01e_1", CubeListBuilder.create().texOffs(30, 9).addBox(-1.5F, -5F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offset(2F, -8F, 2.2F));

        PartDefinition EquipC01f_1 = EquipC01e_1.addOrReplaceChild("EquipC01f_1", CubeListBuilder.create().texOffs(14, 22).addBox(-1F, 0F, -1F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14.9F, 0F));

        PartDefinition EquipC01a_2 = EquipCannonBase.addOrReplaceChild("EquipC01a_2", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -1.5F, -1.5F, 4F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(-20F, 5F, 0F));

        PartDefinition EquipC01b_2 = EquipC01a_2.addOrReplaceChild("EquipC01b_2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -9F, -4.5F, 4F, 13F, 9F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.9F, 0F, 0F, 0F, 0F, -0.0873F));

        PartDefinition EquipC01e_2 = EquipC01b_2.addOrReplaceChild("EquipC01e_2", CubeListBuilder.create().texOffs(30, 9).addBox(-1.5F, -5F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offset(-2F, -8F, 2.2F));

        PartDefinition EquipC01f_2 = EquipC01e_2.addOrReplaceChild("EquipC01f_2", CubeListBuilder.create().texOffs(14, 22).addBox(-1F, 0F, -1F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14.9F, 0F));

        PartDefinition EquipC01c_2 = EquipC01b_2.addOrReplaceChild("EquipC01c_2", CubeListBuilder.create().texOffs(30, 9).addBox(-1.5F, -5F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offset(-2F, -8F, -2.2F));

        PartDefinition EquipC01d_2 = EquipC01c_2.addOrReplaceChild("EquipC01d_2", CubeListBuilder.create().texOffs(14, 22).addBox(-1F, 0F, -1F, 2F, 10F, 2F, new CubeDeformation(0F)), PartPose.offset(0F, -14.9F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(24, 84).mirror().addBox(-3F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.8F, -9.3F, -0.7F, 0.2094F, 0F, 0.2618F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(24, 63).mirror().addBox(0F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-3F, 11F, 2.5F));

        PartDefinition ArmRight02a = ArmRight02.addOrReplaceChild("ArmRight02a", CubeListBuilder.create().texOffs(104, 32).mirror().addBox(-3F, 0F, -3F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(2.5F, 5.5F, -2.4F));

        PartDefinition ArmRight02b = ArmRight02a.addOrReplaceChild("ArmRight02b", CubeListBuilder.create().texOffs(0, 64).addBox(-1F, 0F, 0F, 2F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(-4F, 1F, 0F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(98, 22).addBox(-3.5F, -2F, -4.9F, 7F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 101).addBox(-7F, -14.5F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -1F, -0.7F));

        PartDefinition HairMain = Head.addOrReplaceChild("HairMain", CubeListBuilder.create().texOffs(46, 104).addBox(-7.5F, 0F, 0F, 15F, 11F, 10F, new CubeDeformation(0F)), PartPose.offset(0F, -14.8F, -3F));

        PartDefinition Hair01 = HairMain.addOrReplaceChild("Hair01", CubeListBuilder.create().texOffs(51, 41).addBox(-8F, 0F, -8F, 16F, 7F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 7F, 10.9F, 0.1396F, 0F, 0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(50, 77).addBox(-8F, -8F, -7.4F, 16F, 16F, 8F, new CubeDeformation(0F)), PartPose.offset(0F, -7.5F, 0.1F));

        PartDefinition HairU01 = Hair.addOrReplaceChild("HairU01", CubeListBuilder.create().texOffs(52, 56).addBox(-8.5F, 0F, 0F, 17F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, -6F, -7F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(106, 31).addBox(0F, -6F, -10.5F, 0F, 11F, 11F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -7F, -6F, 0.2094F, 0.6981F, 0F));

        PartDefinition Hat01 = Head.addOrReplaceChild("Hat01", CubeListBuilder.create().texOffs(22, 17).addBox(-4F, 0F, -4F, 8F, 2F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, -15.4F, 3.2F, -0.1745F, 0F, -0.0873F));

        PartDefinition Hat02 = Hat01.addOrReplaceChild("Hat02", CubeListBuilder.create().texOffs(47, 10).addBox(-4.5F, 0F, -4.5F, 9F, 3F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, 0F));

        PartDefinition Hat03 = Hat02.addOrReplaceChild("Hat03", CubeListBuilder.create().texOffs(42, 7).addBox(0F, 0F, -1F, 0F, 5F, 2F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.6F, 1.6F, 2F, 0.2618F, 0.1396F, 0.5236F));

        PartDefinition EquipBag00 = BodyMain.addOrReplaceChild("EquipBag00", CubeListBuilder.create().texOffs(32, 27).addBox(-3F, 0F, 0F, 6F, 14F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.9F, -10.9F, -0.7F, -0.1745F, 1.7453F, 0.0873F));

        PartDefinition EquipBag01 = EquipBag00.addOrReplaceChild("EquipBag01", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1.5F, 8F, 4F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.5F, 13.5F, 0.5F, 0F, 0F, 0.0873F));

        PartDefinition EquipBag03 = EquipBag01.addOrReplaceChild("EquipBag03", CubeListBuilder.create().texOffs(6, 22).addBox(-1F, 0F, -1F, 2F, 3F, 2F, new CubeDeformation(0F)), PartPose.offset(3F, -2.9F, 0F));

        PartDefinition EquipBag02 = EquipBag01.addOrReplaceChild("EquipBag02", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 2F, 1F, new CubeDeformation(0F)), PartPose.offset(5F, -1.9F, -0.5F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(-3.5F, 0F, 0F, 7F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -8.7F, -3.8F, -0.8727F, -0.0524F, 0.0873F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(0, 35).addBox(-3.5F, 0F, 0F, 7F, 6F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -8.7F, -3.8F, -0.8727F, 0.0524F, -0.0873F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 47).addBox(-7.5F, 0F, -5.7F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4F, 1.3F, 0.3491F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.8F, 5.5F, -2.6F, -0.2967F, 0F, -0.0873F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(0F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(-3F, 14F, -3F));

        PartDefinition ShoeR01 = LegRight02.addOrReplaceChild("ShoeR01", CubeListBuilder.create().texOffs(19, 63).mirror().addBox(-4F, 0F, 0F, 4F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2F, 12.5F, 3.6F, -0.6981F, 0.1396F, 0.6981F));

        PartDefinition ShoeR02 = ShoeR01.addOrReplaceChild("ShoeR02", CubeListBuilder.create().texOffs(24, 80).mirror().addBox(-10F, -3F, 0F, 10F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4F, 3F, 0.1F, 0F, 0F, 0.6981F));

        PartDefinition ShoeL03_1 = LegRight01.addOrReplaceChild("ShoeL03_1", CubeListBuilder.create().texOffs(20, 33).addBox(0F, 0F, -2.2F, 1F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(-3.9F, 9.5F, 0F));

        PartDefinition ShoeL04_1 = ShoeL03_1.addOrReplaceChild("ShoeL04_1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, -0.7F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 84).addBox(-3F, 0F, -3F, 6F, 14F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.8F, 5.5F, -2.6F, -0.1571F, 0F, 0.0873F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(0, 63).addBox(-6F, 0F, 0F, 6F, 15F, 6F, new CubeDeformation(0F)), PartPose.offset(3F, 14F, -3F));

        PartDefinition ShoeL01 = LegLeft02.addOrReplaceChild("ShoeL01", CubeListBuilder.create().texOffs(19, 63).addBox(0F, 0F, 0F, 4F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2F, 12.5F, 3.6F, -0.6981F, -0.1396F, -0.6981F));

        PartDefinition ShoeL02 = ShoeL01.addOrReplaceChild("ShoeL02", CubeListBuilder.create().texOffs(24, 80).addBox(0F, -3F, 0F, 10F, 3F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4F, 3F, 0.1F, 0F, 0F, -0.6981F));

        PartDefinition ShoeL03 = LegLeft01.addOrReplaceChild("ShoeL03", CubeListBuilder.create().texOffs(20, 33).addBox(0F, 0F, -2.2F, 1F, 3F, 5F, new CubeDeformation(0F)), PartPose.offset(2.9F, 9.5F, 0F));

        PartDefinition ShoeL04 = ShoeL03.addOrReplaceChild("ShoeL04", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1F, 3F, 3F, new CubeDeformation(0F)), PartPose.offset(0F, 3F, -0.7F));

        PartDefinition Skirt01 = Butt.addOrReplaceChild("Skirt01", CubeListBuilder.create().texOffs(74, 0).addBox(-8.5F, 0F, -6.2F, 17F, 7F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 3.1F, 0F, -0.1745F, 0F, 0F));

        PartDefinition Belt01 = Skirt01.addOrReplaceChild("Belt01", CubeListBuilder.create().texOffs(56, 0).addBox(-5.5F, 0F, 0F, 11F, 7F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6F, 0.9F, -1F, -1.1345F, 1.5708F, 0F));

        PartDefinition Cloth01 = BodyMain.addOrReplaceChild("Cloth01", CubeListBuilder.create().texOffs(0, 48).addBox(-1.5F, 0F, -0.5F, 3F, 6F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10.5F, -3.5F, -0.6981F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(24, 84).addBox(-2F, -1F, -2.5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.8F, -9.3F, -0.7F, 0F, 0F, -0.2618F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(24, 63).addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(3F, 11F, 2.5F));

        PartDefinition ArmLeft02a = ArmLeft02.addOrReplaceChild("ArmLeft02a", CubeListBuilder.create().texOffs(104, 32).addBox(-3F, 0F, -3F, 6F, 4F, 6F, new CubeDeformation(0F)), PartPose.offset(-2.5F, 5.5F, -2.4F));

        PartDefinition ArmLeft02b = ArmLeft02a.addOrReplaceChild("ArmLeft02b", CubeListBuilder.create().texOffs(0, 64).addBox(-1F, 0F, 0F, 2F, 3F, 1F, new CubeDeformation(0F)), PartPose.offset(4F, 1F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -15F, 0F, -0.1047F, 0F, 0F));

        PartDefinition GlowNeck = GlowBodyMain.addOrReplaceChild("GlowNeck", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10.3F, 0.5F, 0.1047F, 0F, 0F));

        PartDefinition GlowHead = GlowNeck.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -1F, -0.7F));
        ShipModelBaseAdv.addFaceLayer(GlowHead);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PoseContext ctx = computePoseContext(entity, limbSwing, limbSwingAmount, ageInTicks, 0.0F);
        resetPoseState();
        resetOffsets();

        applyFaceAndMouth(entity);
        applyEquipVisibility(entity);

        if (isDeadPose(entity)) {
            applyDeadPose();
            syncGlowParts();
            return;
        }

        applyHeadRotation(Head, entity, ageInTicks, netHeadYaw, headPitch);

        applyBasePose(ctx);
        applyEquipAnimation(entity, ctx);
        applySpecialPoseAdjustments(entity, ctx, ageInTicks);

        syncGlowParts();
    }

    private boolean isDeadPose(T entity) {
        return entity != null && entity.isInDeadPose();
    }

    private void applyDeadPose() {
        poseTranslateY += DEAD_TRANSLATE_Y;
        BoobL.xRot = -0.8F;
        BoobR.xRot = -0.8F;
        Head.xRot = 0.5F;
        Head.yRot = 0.0F;
        Head.zRot = 0.0F;
        Ahoke.yRot = 0.45F;
        BodyMain.xRot = 0.31F;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;
        Butt.xRot = -0.85F;
        Skirt01.xRot = -0.33F;
        ArmLeft01.xRot = -1.1F;
        ArmLeft01.yRot = 0.39F;
        ArmLeft01.zRot = -0.05F;
        ArmLeft02.xRot = -1.46F;
        ArmLeft02.zRot = 0.0F;
        ArmRight01.xRot = -1.1F;
        ArmRight01.yRot = -0.39F;
        ArmRight01.zRot = 0.05F;
        ArmRight02.xRot = -1.46F;
        ArmRight02.zRot = 0.0F;
        LegLeft01.xRot = -0.66F;
        LegLeft01.yRot = 0.0F;
        LegLeft01.zRot = -0.14F;
        LegLeft02.xRot = 1.2217F;
        LegLeft02.yRot = 1.2217F;
        LegLeft02.zRot = -1.0472F;
        LegLeft02.y = legLeft02DefaultY + (-0.06F * 16.0F);
        LegRight01.xRot = -0.66F;
        LegRight01.yRot = 0.0F;
        LegRight01.zRot = 0.14F;
        LegRight02.xRot = 1.2217F;
        LegRight02.yRot = -1.2217F;
        LegRight02.zRot = 1.0472F;
        LegRight02.y = legRight02DefaultY + (-0.06F * 16.0F);
    }

    private void applyEquipVisibility(EntityShipBase entity) {
        if (entity == null) return;

        EquipBase.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTakao.EQUIP_RIGGING);
        EquipBag00.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTakao.EQUIP_BAG);
        Hat01.visible = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTakao.EQUIP_HAT);

        boolean showShoes = entity.getEquipFlag(org.trp.shincolle.entity.EntityCruiserTakao.EQUIP_SHOES);
        ShoeL01.visible = showShoes;
        ShoeR01.visible = showShoes;
        ShoeL03.visible = showShoes;
        ShoeL03_1.visible = showShoes;
    }

    private void applyBasePose(PoseContext ctx) {
        float angleX = ctx.angleX;

        Ahoke.yRot = angleX * 0.15F + 0.65F;
        BoobL.xRot = angleX * 0.06F - 0.8F;
        BoobR.xRot = angleX * 0.06F - 0.8F;
        Hat03.xRot = angleX * 0.05F + 0.26F;

        BodyMain.xRot = BODY_BASE_X_ROT;
        BodyMain.yRot = 0.0F;
        BodyMain.zRot = 0.0F;
        Butt.xRot = BUTT_BASE_X_ROT;
        Skirt01.xRot = SKIRT_BASE_X_ROT;
        Cloth01.xRot = 0.0F;

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
        LegLeft01.zRot = LEG_BASE_Z_ROT;
        LegLeft02.xRot = 0.0F;
        LegLeft02.yRot = 0.0F;
        LegLeft02.zRot = 0.0F;

        LegRight01.yRot = 0.0F;
        LegRight01.zRot = -LEG_BASE_Z_ROT;
        LegRight02.xRot = 0.0F;
        LegRight02.yRot = 0.0F;
        LegRight02.zRot = 0.0F;
    }

    private void applyEquipAnimation(T entity, PoseContext ctx) {
        EquipCannonBase.yRot = Head.yRot * 0.35F;
        EquipC01b.xRot = Head.yRot;
        EquipC01b_1.xRot = Head.xRot + 1.2F;
        EquipC01b_2.xRot = Head.xRot + 1.2F;
        EquipC01c_1.zRot = -Head.yRot * 0.5F;
        EquipC01e_1.zRot = -Head.yRot * 0.5F;
        EquipC01c_2.zRot = -Head.yRot * 0.5F;
        EquipC01e_2.zRot = -Head.yRot * 0.5F;
    }

    private void applySpecialPoseAdjustments(T entity, PoseContext ctx, float ageInTicks) {
        float legAddLeft = ctx.angleAdd1 * 0.3F - 0.28F;
        float legAddRight = ctx.angleAdd2 * 0.3F - 0.21F;

        if (entity != null && entity.getShipDepth() > 0.0) {
            this.poseTranslateY += ctx.angleX * 0.05F + 0.025F;
        }

        boolean spcStand = false;
        if (entity != null && hasLegacyState(entity, 1, 4)) {
            spcStand = true;
            Head.yRot *= 0.25F;
            ArmLeft01.xRot = -0.3491F;
            ArmLeft01.yRot = 0.0F;
            ArmLeft01.zRot = 0.4554F;
            ArmLeft02.xRot = 0.0F;
            ArmLeft02.yRot = 0.0F;
            ArmLeft02.zRot = 1.0472F;
            ArmRight01.xRot = -0.5463F;
            ArmRight01.yRot = -0.2618F;
            ArmRight01.zRot = -0.1396F;
            ArmRight02.xRot = -2.5307F;
            ArmRight02.zRot = 0.0F;
            ArmRight02.z = armRight02DefaultZ + (-0.32F * OFFSET_SCALE);
            if (hasLegacyState(entity, 7, 4)) {
                setFace(EntityShipBase.FACE_WINK);
            }
        }

        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isSitting = ctx.isSitting || (entity != null && entity.isPassenger());
        boolean isSprinting = entity != null && entity.isSprinting();

        if (isSprinting && entity != null && hasLegacyState(entity, 1, 4)) {
            ArmLeft01.xRot = -0.35F;
            ArmLeft01.yRot = -1.7F - ctx.angleAdd2 * 0.5F;
            ArmLeft01.zRot = 0.0F;
            ArmLeft02.xRot = -2.4F;
            ArmLeft02.yRot = 0.0F;
            ArmLeft02.zRot = 0.0F;
            ArmLeft02.z = armLeft02DefaultZ + (-0.315F * OFFSET_SCALE);
            ArmRight01.xRot = -0.35F;
            ArmRight01.yRot = 1.7F + ctx.angleAdd1 * 0.5F;
            ArmRight01.zRot = 0.0F;
            ArmRight02.xRot = -2.4F;
            ArmRight02.yRot = 0.0F;
            ArmRight02.zRot = 0.0F;
            ArmRight02.z = armRight02DefaultZ + (-0.315F * OFFSET_SCALE);
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            Head.xRot -= 1.0472F;
            BodyMain.xRot = 1.0472F;
            Butt.xRot = -0.4F;
            Skirt01.xRot = -0.24F;
            ArmLeft01.xRot = -0.6F;
            ArmLeft01.zRot = 0.2618F;
            ArmRight01.xRot = -0.6F;
            ArmRight01.zRot = -0.2618F;
            legAddLeft -= 0.4F;
            legAddRight -= 0.4F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (hasLegacyState(entity, 6, 1)) {
                this.poseTranslateY += 0.34F * 3;
                Head.xRot -= 0.91F;
                BodyMain.xRot = 0.7F;
                BodyMain.yRot = 0.0F;
                BodyMain.zRot = 0.0F;
                Skirt01.xRot = -0.24F;
                ArmLeft01.xRot = -0.45F;
                ArmLeft01.yRot = 0.0F;
                ArmLeft01.zRot = 0.21F;
                ArmRight01.xRot = -0.45F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = -0.21F;
                legAddLeft = -1.59F;
                legAddRight = -1.59F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = 0.09F;
                LegLeft02.xRot = 2.1F;
                LegLeft02.yRot = 0.0F;
                LegLeft02.zRot = 0.0F;
                LegLeft02.z = legLeft02DefaultZ + (0.37F * OFFSET_SCALE);
                LegRight01.yRot = 0.0F;
                LegRight01.zRot = -0.09F;
                LegRight02.xRot = 2.1F;
                LegRight02.yRot = 0.0F;
                LegRight02.zRot = 0.0F;
                LegRight02.z = legRight02DefaultZ + (0.37F * OFFSET_SCALE);
            } else if (hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.58F * 3;
                setFlushVisible(true);
                Head.xRot = 0.55F;
                Head.yRot = -0.2F;
                BodyMain.xRot = -0.7F;
                BodyMain.yRot = -0.2618F;
                BodyMain.zRot = -0.5236F;
                Butt.xRot = -0.2618F;
                Cloth01.xRot = 0.3F;
                Skirt01.xRot = -0.2443F;
                ArmLeft01.xRot = -0.2618F;
                ArmLeft01.yRot = 0.7F;
                ArmLeft01.zRot = -0.5236F;
                ArmLeft02.xRot = -2.1F;
                ArmLeft02.yRot = 0.0F;
                ArmLeft02.zRot = 0.0F;
                ArmLeft02.z = armLeft02DefaultZ + (-0.31F * OFFSET_SCALE);
                ArmRight01.xRot = 0.7F;
                ArmRight01.yRot = 0.0F;
                ArmRight01.zRot = 0.5236F;
                ArmRight02.xRot = -1.45F;
                ArmRight02.yRot = 0.0F;
                ArmRight02.zRot = 0.0F;
                legAddLeft = -0.79F;
                legAddRight = -0.7F;
                LegLeft01.yRot = 0.0F;
                LegLeft01.zRot = -0.14F;
                LegLeft02.xRot = 1.4F;
                LegRight01.yRot = -0.4363F;
                LegRight01.zRot = 0.0F;
                LegRight02.xRot = 0.7F;
            } else {
                this.poseTranslateY += SITTING_TRANSLATE_Y;
                Head.xRot -= 0.1F;
                BodyMain.xRot = 0.0F;
                Butt.xRot = -0.2F;
                Skirt01.xRot = -0.15F;
                if (!spcStand) {
                    ArmLeft01.xRot = -0.4F;
                    ArmLeft01.zRot = 0.2618F;
                    ArmRight01.xRot = -0.4F;
                    ArmRight01.zRot = -0.2618F;
                }
                legAddLeft = -0.65F;
                legAddRight = -0.65F;
                LegLeft01.yRot = 0.1F;
                LegLeft01.zRot = 0.0F;
                LegLeft02.z = legLeft02DefaultZ + (0.375F * OFFSET_SCALE);
                LegLeft02.xRot = 2.45F;
                LegLeft02.zRot = 0.0175F;
                LegRight01.yRot = -0.1F;
                LegRight01.zRot = 0.0F;
                LegRight02.z = legRight02DefaultZ + (0.375F * OFFSET_SCALE);
                LegRight02.xRot = 2.45F;
                LegRight02.zRot = -0.0175F;
            }
        }

        if (entity != null && entity.getAttackTick() > 30) {
            ArmLeft01.xRot = -1.5F + Head.xRot * 0.75F;
            ArmLeft01.yRot = 0.17F;
            ArmLeft01.zRot = 0.1F;
            ArmLeft02.xRot = 0.0F;
            ArmLeft02.zRot = 0.0F;
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

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        Butt.y = buttDefaultY;
        Skirt01.y = skirt01DefaultY;

        ArmLeft02.x = armLeft02DefaultX;
        ArmLeft02.y = armLeft02DefaultY;
        ArmLeft02.z = armLeft02DefaultZ;

        ArmRight02.x = armRight02DefaultX;
        ArmRight02.y = armRight02DefaultY;
        ArmRight02.z = armRight02DefaultZ;

        LegLeft02.x = legLeft02DefaultX;
        LegLeft02.y = legLeft02DefaultY;
        LegLeft02.z = legLeft02DefaultZ;

        LegRight02.x = legRight02DefaultX;
        LegRight02.y = legRight02DefaultY;
        LegRight02.z = legRight02DefaultZ;
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
        GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
