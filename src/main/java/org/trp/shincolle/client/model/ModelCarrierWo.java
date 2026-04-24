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

public class ModelCarrierWo<T extends EntityShipBase> extends ShipModelHumanoidBase<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "carrier_wo"), "main");

    private static final float OFFSET_SCALE = 16.0F;
    private static final float DEAD_TRANSLATE_Y = LegacyPoseOffsets.deadY("ModelCarrierWo");
    private static final float SNEAK_TRANSLATE_Y = LegacyPoseOffsets.sneakY("ModelCarrierWo");
    private static final float MODEL_SCALE = 0.44F;
    private static final float MODEL_OFFSET_Y = 1.9F;

    private boolean isDeadPose;
    private float poseTranslateY;
    private boolean isSittingPose;

    private final ModelPart BodyMain;
    private final ModelPart Butt;
    private final ModelPart ArmLeft01;
    private final ModelPart ArmRight01;
    private final ModelPart ArmLeft02;
    private final ModelPart ArmRight02;
    private final ModelPart Neck;
    private final ModelPart Neck02;
    private final ModelPart BoobR;
    private final ModelPart BoobL;
    private final ModelPart CloakNeck;
    private final ModelPart LegRight01;
    private final ModelPart LegLeft01;
    private final ModelPart LegRight02;
    private final ModelPart LegLeft02;
    private final ModelPart ShoesRight;
    private final ModelPart ShoesLeft;
    private final ModelPart Staff;
    private final ModelPart StaffHead;
    private final ModelPart Head;
    private final ModelPart Hair;
    private final ModelPart Ahoke;
    private final ModelPart HairL01;
    private final ModelPart Hair00a;
    private final ModelPart Hair00b;
    private final ModelPart HairR01;
    private final ModelPart HairL02;
    private final ModelPart HairR02;
    private final ModelPart EquipBase;
    private final ModelPart Equip01;
    private final ModelPart Equip02;
    private final ModelPart Equip03;
    private final ModelPart Equip04;
    private final ModelPart EquipEye01;
    private final ModelPart EquipEye02;
    private final ModelPart EquipT01L;
    private final ModelPart EquipT01R;
    private final ModelPart Equip05;
    private final ModelPart Equip06;
    private final ModelPart EquipLC01;
    private final ModelPart EquipRC01;
    private final ModelPart EquipTB01L;
    private final ModelPart EquipTB01R;
    private final ModelPart EquipTooth01;
    private final ModelPart EquipTooth02;
    private final ModelPart EquipTooth03;
    private final ModelPart EquipT02L;
    private final ModelPart EquipT03L;
    private final ModelPart EquipT02R;
    private final ModelPart EquipT03R;
    private final ModelPart EquipLC02;
    private final ModelPart EquipLC03;
    private final ModelPart EquipRC02;
    private final ModelPart EquipRC03;
    private final ModelPart EquipTB02L;
    private final ModelPart EquipTB03L;
    private final ModelPart EquipTB02R;
    private final ModelPart EquipTB03R;
    private final ModelPart Cloak01;
    private final ModelPart Cloak02;
    private final ModelPart Cloak03;
    private final ModelPart Neck03;
    private final ModelPart GlowBodyMain;
    private final ModelPart GlowHead;
    private final ModelPart GlowEquipBase;
    private final float buttDefaultY;
    private final float buttDefaultZ;
    private final float legLeft02DefaultZ;
    private final float legRight02DefaultZ;
    private final float staffDefaultX;
    private final float staffDefaultY;
    private final float staffDefaultZ;

    public ModelCarrierWo(ModelPart root) {
        this.BodyMain = root.getChild("BodyMain");
        this.Head = this.BodyMain.getChild("Head");
        this.Hair = this.Head.getChild("Hair");
        this.Ahoke = this.Hair.getChild("Ahoke");
        this.HairR01 = this.Hair.getChild("HairR01");
        this.HairR02 = this.HairR01.getChild("HairR02");
        this.HairL01 = this.Hair.getChild("HairL01");
        this.HairL02 = this.HairL01.getChild("HairL02");
        this.Hair00a = this.Hair.getChild("Hair00a");
        this.Hair00b = this.Hair.getChild("Hair00b");
        this.EquipBase = this.Head.getChild("EquipBase");
        this.Equip05 = this.EquipBase.getChild("Equip05");
        this.Equip03 = this.EquipBase.getChild("Equip03");
        this.EquipTooth03 = this.EquipBase.getChild("EquipTooth03");
        this.Equip04 = this.EquipBase.getChild("Equip04");
        this.EquipLC01 = this.EquipBase.getChild("EquipLC01");
        this.EquipLC02 = this.EquipLC01.getChild("EquipLC02");
        this.EquipLC03 = this.EquipLC01.getChild("EquipLC03");
        this.EquipTB01L = this.EquipBase.getChild("EquipTB01L");
        this.EquipTB02L = this.EquipTB01L.getChild("EquipTB02L");
        this.EquipTB03L = this.EquipTB02L.getChild("EquipTB03L");
        this.Equip06 = this.EquipBase.getChild("Equip06");
        this.Equip02 = this.EquipBase.getChild("Equip02");
        this.EquipT01R = this.EquipBase.getChild("EquipT01R");
        this.EquipT02R = this.EquipT01R.getChild("EquipT02R");
        this.EquipT03R = this.EquipT02R.getChild("EquipT03R");
        this.EquipTB01R = this.EquipBase.getChild("EquipTB01R");
        this.EquipTB02R = this.EquipTB01R.getChild("EquipTB02R");
        this.EquipTB03R = this.EquipTB02R.getChild("EquipTB03R");
        this.EquipRC01 = this.EquipBase.getChild("EquipRC01");
        this.EquipRC02 = this.EquipRC01.getChild("EquipRC02");
        this.EquipRC03 = this.EquipRC01.getChild("EquipRC03");
        this.EquipTooth02 = this.EquipBase.getChild("EquipTooth02");
        this.EquipT01L = this.EquipBase.getChild("EquipT01L");
        this.EquipT02L = this.EquipT01L.getChild("EquipT02L");
        this.EquipT03L = this.EquipT02L.getChild("EquipT03L");
        this.Equip01 = this.EquipBase.getChild("Equip01");
        this.EquipTooth01 = this.EquipBase.getChild("EquipTooth01");
        this.Neck03 = this.BodyMain.getChild("Neck03");
        this.BoobL = this.BodyMain.getChild("BoobL");
        this.CloakNeck = this.BodyMain.getChild("CloakNeck");
        this.Cloak01 = this.CloakNeck.getChild("Cloak01");
        this.Cloak02 = this.Cloak01.getChild("Cloak02");
        this.Cloak03 = this.Cloak02.getChild("Cloak03");
        this.ArmRight01 = this.BodyMain.getChild("ArmRight01");
        this.ArmRight02 = this.ArmRight01.getChild("ArmRight02");
        this.Staff = this.ArmRight02.getChild("Staff");
        this.StaffHead = this.Staff.getChild("StaffHead");
        this.Neck = this.BodyMain.getChild("Neck");
        this.Neck02 = this.Neck.getChild("Neck02");
        this.ArmLeft01 = this.BodyMain.getChild("ArmLeft01");
        this.ArmLeft02 = this.ArmLeft01.getChild("ArmLeft02");
        this.BoobR = this.BodyMain.getChild("BoobR");
        this.Butt = this.BodyMain.getChild("Butt");
        this.LegLeft01 = this.Butt.getChild("LegLeft01");
        this.LegLeft02 = this.LegLeft01.getChild("LegLeft02");
        this.ShoesLeft = this.LegLeft02.getChild("ShoesLeft");
        this.LegRight01 = this.Butt.getChild("LegRight01");
        this.LegRight02 = this.LegRight01.getChild("LegRight02");
        this.ShoesRight = this.LegRight02.getChild("ShoesRight");
        this.GlowBodyMain = root.getChild("GlowBodyMain");
        this.GlowHead = this.GlowBodyMain.getChild("GlowHead");
        initFaceParts(this.GlowHead);
        this.GlowEquipBase = this.GlowHead.getChild("GlowEquipBase");
        this.EquipEye01 = this.GlowEquipBase.getChild("EquipEye01");
        this.EquipEye02 = this.GlowEquipBase.getChild("EquipEye02");
        this.buttDefaultY = this.Butt.y;
        this.buttDefaultZ = this.Butt.z;
        this.legLeft02DefaultZ = this.LegLeft02.z;
        this.legRight02DefaultZ = this.LegRight02.z;
        this.staffDefaultX = this.Staff.x;
        this.staffDefaultY = this.Staff.y;
        this.staffDefaultZ = this.Staff.z;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition BodyMain = partdefinition.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -12F, -4F, 13F, 17F, 7F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -11F, 0F, -0.3491F, 0F, 0F));

        PartDefinition Head = BodyMain.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(43, 101).addBox(-7F, -14F, -6.5F, 14F, 14F, 13F, new CubeDeformation(0F)), PartPose.offset(0F, -13F, -0.5F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(128, 61).addBox(-8F, -8F, -7.2F, 16F, 14F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, -7F, 0F));

        PartDefinition Ahoke = Hair.addOrReplaceChild("Ahoke", CubeListBuilder.create().texOffs(31, 89).addBox(0F, -13.5F, -12F, 0F, 12F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, -4.5F, 0F, 0.7F, 0F));

        PartDefinition HairR01 = Hair.addOrReplaceChild("HairR01", CubeListBuilder.create().texOffs(175, 61).mirror().addBox(-1F, 0F, -2F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6F, 0F, -2F, -0.5236F, 0.1745F, 0.3142F));

        PartDefinition HairR02 = HairR01.addOrReplaceChild("HairR02", CubeListBuilder.create().texOffs(176, 74).addBox(-1F, 0F, -2.2F, 2F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 0F, 0.3491F, 0F, -0.2618F));

        PartDefinition HairL01 = Hair.addOrReplaceChild("HairL01", CubeListBuilder.create().texOffs(175, 61).addBox(-1F, 0F, -2F, 2F, 9F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6F, 0F, -2F, -0.5236F, -0.1745F, -0.3142F));

        PartDefinition HairL02 = HairL01.addOrReplaceChild("HairL02", CubeListBuilder.create().texOffs(176, 74).mirror().addBox(-1F, 0F, -2.2F, 2F, 9F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 0F, 0.3491F, 0F, 0.2618F));

        PartDefinition Hair00a = Hair.addOrReplaceChild("Hair00a", CubeListBuilder.create().texOffs(128, 82).addBox(-7.5F, -7.5F, -1F, 15F, 8F, 9F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, -0.5F));

        PartDefinition Hair00b = Hair.addOrReplaceChild("Hair00b", CubeListBuilder.create().texOffs(43, 21).addBox(-7.5F, 0F, 0F, 15F, 10F, 10F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0.3F, -2.5F, 0.1745F, 0F, 0F));

        PartDefinition EquipBase = Head.addOrReplaceChild("EquipBase", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -10F, -3F, 0.0873F, 0F, 0F));

        PartDefinition Equip05 = EquipBase.addOrReplaceChild("Equip05", CubeListBuilder.create().texOffs(104, 4).addBox(-24F, -18F, -15F, 48F, 18F, 28F, new CubeDeformation(0F)), PartPose.offset(0F, -5F, 2.5F));

        PartDefinition Equip03 = EquipBase.addOrReplaceChild("Equip03", CubeListBuilder.create().texOffs(112, 0).addBox(-16F, -18F, -20F, 32F, 18F, 40F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -5.5F, 4F, 0.0698F, 0F, 0F));

        PartDefinition EquipTooth03 = EquipBase.addOrReplaceChild("EquipTooth03", CubeListBuilder.create().texOffs(128, 99).mirror().addBox(-14F, 0F, 0F, 14F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-12.4F, -17F, -20.3F, 0.0698F, 0.5236F, -0.0524F));

        PartDefinition Equip04 = EquipBase.addOrReplaceChild("Equip04", CubeListBuilder.create().texOffs(112, 0).addBox(-12F, -15F, -24F, 24F, 15F, 46F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 5.5F, 0.1047F, 0F, 0F));

        PartDefinition EquipLC01 = EquipBase.addOrReplaceChild("EquipLC01", CubeListBuilder.create().texOffs(128, 0).addBox(-3.5F, -5.5F, -7.5F, 7F, 11F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(30F, -7F, 4F, -0.1745F, -0.2618F, 0.1745F));

        PartDefinition EquipLC02 = EquipLC01.addOrReplaceChild("EquipLC02", CubeListBuilder.create().texOffs(128, 0).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1F, -2F, -7F, -0.1047F, 0F, 0F));

        PartDefinition EquipLC03 = EquipLC01.addOrReplaceChild("EquipLC03", CubeListBuilder.create().texOffs(128, 0).addBox(-1.5F, -1.5F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -7F, 0.1047F, 0F, 0F));

        PartDefinition EquipTB01L = EquipBase.addOrReplaceChild("EquipTB01L", CubeListBuilder.create().texOffs(128, 0).addBox(-3F, -2F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(15F, -6F, 10F, 0.1745F, 0F, -0.3491F));

        PartDefinition EquipTB02L = EquipTB01L.addOrReplaceChild("EquipTB02L", CubeListBuilder.create().texOffs(21, 56).addBox(-2.5F, -2F, -2.5F, 5F, 16F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 0F, 0.4363F, 0F, -0.3491F));

        PartDefinition EquipTB03L = EquipTB02L.addOrReplaceChild("EquipTB03L", CubeListBuilder.create().texOffs(21, 56).addBox(-2F, -2F, -2F, 4F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 0F, 0.6981F, 0F, 0.7854F));

        PartDefinition Equip06 = EquipBase.addOrReplaceChild("Equip06", CubeListBuilder.create().texOffs(96, 0).addBox(-29F, -13F, -13F, 58F, 13F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -7F, 4.5F, 0.0698F, 0F, 0F));

        PartDefinition Equip02 = EquipBase.addOrReplaceChild("Equip02", CubeListBuilder.create().texOffs(120, 0).addBox(-18F, -22F, -15F, 36F, 22F, 32F, new CubeDeformation(0F)), PartPose.offset(0F, -3F, 2F));

        PartDefinition EquipT01R = EquipBase.addOrReplaceChild("EquipT01R", CubeListBuilder.create().texOffs(128, 0).addBox(-4F, 0F, -4F, 8F, 10F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-17F, -7F, -8F, -0.2618F, 0F, 0.2618F));

        PartDefinition EquipT02R = EquipT01R.addOrReplaceChild("EquipT02R", CubeListBuilder.create().texOffs(21, 56).addBox(-3F, -2F, -3F, 6F, 22F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 0F, -0.1745F, 0F, 0.2618F));

        PartDefinition EquipT03R = EquipT02R.addOrReplaceChild("EquipT03R", CubeListBuilder.create().texOffs(21, 56).addBox(-2.5F, -2F, -2.5F, 5F, 20F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 20F, 0F, 1.0472F, 0F, -0.7854F));

        PartDefinition EquipTB01R = EquipBase.addOrReplaceChild("EquipTB01R", CubeListBuilder.create().texOffs(128, 0).addBox(-3F, -2F, -3F, 6F, 10F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-15F, -6F, 10F, 0.1745F, 0F, 0.3491F));

        PartDefinition EquipTB02R = EquipTB01R.addOrReplaceChild("EquipTB02R", CubeListBuilder.create().texOffs(21, 56).addBox(-2.5F, -2F, -2.5F, 5F, 16F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 9F, 0F, 0.4363F, 0F, 0.3491F));

        PartDefinition EquipTB03R = EquipTB02R.addOrReplaceChild("EquipTB03R", CubeListBuilder.create().texOffs(21, 56).addBox(-2F, -2F, -2F, 4F, 15F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 13.5F, 0F, 0.6981F, 0F, -0.7854F));

        PartDefinition EquipRC01 = EquipBase.addOrReplaceChild("EquipRC01", CubeListBuilder.create().texOffs(128, 0).addBox(-3.5F, -5.5F, -7.5F, 7F, 11F, 15F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-30F, -7F, 4F, -0.1745F, 0.2618F, -0.1745F));

        PartDefinition EquipRC02 = EquipRC01.addOrReplaceChild("EquipRC02", CubeListBuilder.create().texOffs(128, 0).addBox(-1.5F, -1.5F, -17F, 3F, 3F, 17F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1F, -2F, -7F, -0.1047F, 0F, 0F));

        PartDefinition EquipRC03 = EquipRC01.addOrReplaceChild("EquipRC03", CubeListBuilder.create().texOffs(128, 0).addBox(-1.5F, -1.5F, -16F, 3F, 3F, 16F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -7F, 0.1047F, 0F, 0F));

        PartDefinition EquipTooth02 = EquipBase.addOrReplaceChild("EquipTooth02", CubeListBuilder.create().texOffs(128, 99).addBox(0F, 0F, 0F, 14F, 8F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(12.4F, -17F, -20.3F, 0.1047F, -0.5236F, 0.0524F));

        PartDefinition EquipT01L = EquipBase.addOrReplaceChild("EquipT01L", CubeListBuilder.create().texOffs(128, 0).addBox(-4F, 0F, -4F, 8F, 10F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(17F, -7F, -8F, -0.2618F, 0F, -0.2618F));

        PartDefinition EquipT02L = EquipT01L.addOrReplaceChild("EquipT02L", CubeListBuilder.create().texOffs(21, 56).addBox(-3F, -2F, -3F, 6F, 22F, 6F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 8F, 0F, -0.1745F, 0F, -0.2618F));

        PartDefinition EquipT03L = EquipT02L.addOrReplaceChild("EquipT03L", CubeListBuilder.create().texOffs(21, 56).addBox(-2.5F, -2F, -2.5F, 5F, 20F, 5F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 20F, 0F, 1.0472F, 0F, 0.7854F));

        PartDefinition Equip01 = EquipBase.addOrReplaceChild("Equip01", CubeListBuilder.create().texOffs(128, 0).addBox(-9F, -28.5F, -7F, 18F, 27F, 22F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 0F, 0F, 0.0873F, 0F, 0F));

        PartDefinition EquipTooth01 = EquipBase.addOrReplaceChild("EquipTooth01", CubeListBuilder.create().texOffs(128, 112).addBox(-12F, 0F, 0F, 24F, 15F, 1F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -19.3F, -20.6F, 0.1047F, 0F, 0F));

        PartDefinition Neck03 = BodyMain.addOrReplaceChild("Neck03", CubeListBuilder.create().texOffs(8, 0).addBox(-2.5F, -2F, -2.5F, 5F, 2F, 5F, new CubeDeformation(0F)), PartPose.offset(0F, -11.9F, -0F));

        PartDefinition BoobL = BodyMain.addOrReplaceChild("BoobL", CubeListBuilder.create().texOffs(3, 27).mirror().addBox(-3.5F, 0F, -1F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.5F, -9F, -3.2F, -0.7854F, 0.0873F, 0.14F));

        PartDefinition CloakNeck = BodyMain.addOrReplaceChild("CloakNeck", CubeListBuilder.create().texOffs(192, 61).addBox(-10F, 0F, -6F, 20F, 7F, 12F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -12F, -1.5F, 0.3142F, 0F, 0F));

        PartDefinition Cloak01 = CloakNeck.addOrReplaceChild("Cloak01", CubeListBuilder.create().texOffs(216, 85).addBox(-10F, 0F, 0F, 20F, 12F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 6.5F, 6F, 0.5F, 0F, 0F));

        PartDefinition Cloak02 = Cloak01.addOrReplaceChild("Cloak02", CubeListBuilder.create().texOffs(208, 97).addBox(-12F, 0F, 0F, 24F, 16F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 12F, 0F, -0.4554F, 0F, 0F));

        PartDefinition Cloak03 = Cloak02.addOrReplaceChild("Cloak03", CubeListBuilder.create().texOffs(196, 113).addBox(-15F, 0F, 0F, 30F, 15F, 0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 16F, 0F, 0.3491F, 0F, 0F));

        PartDefinition ArmRight01 = BodyMain.addOrReplaceChild("ArmRight01", CubeListBuilder.create().texOffs(0, 54).addBox(-5F, -1F, -2F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-4.7F, -9F, 0F));

        PartDefinition ArmRight02 = ArmRight01.addOrReplaceChild("ArmRight02", CubeListBuilder.create().texOffs(0, 71).addBox(0F, 0F, -4F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(-5F, 11F, 2F));

        PartDefinition Staff = ArmRight02.addOrReplaceChild("Staff", CubeListBuilder.create().texOffs(128, 0).addBox(0F, -15F, 0F, 3F, 28F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(8F, 35F, 21F, 1.1839F, -0.182F, -1.2292F));

        PartDefinition StaffHead = Staff.addOrReplaceChild("StaffHead", CubeListBuilder.create().texOffs(38, 80).addBox(0F, -13F, 0F, 4F, 13F, 8F, new CubeDeformation(0F)), PartPose.offset(-0.5F, -15F, -1F));

        PartDefinition Neck = BodyMain.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(46, 41).addBox(-7.5F, -1.5F, -7F, 15F, 4F, 13F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, -13F, -2F, 0.4189F, 0F, 0F));

        PartDefinition Neck02 = Neck.addOrReplaceChild("Neck02", CubeListBuilder.create().texOffs(128, 0).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 2F, -5F, -0.52F, 0F, 0F));

        PartDefinition ArmLeft01 = BodyMain.addOrReplaceChild("ArmLeft01", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(0F, -1F, -2F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(4.7F, -9F, 0F));

        PartDefinition ArmLeft02 = ArmLeft01.addOrReplaceChild("ArmLeft02", CubeListBuilder.create().texOffs(0, 71).mirror().addBox(-5F, 0F, -5F, 5F, 12F, 5F, new CubeDeformation(0F)), PartPose.offset(5F, 11F, 3F));

        PartDefinition BoobR = BodyMain.addOrReplaceChild("BoobR", CubeListBuilder.create().texOffs(3, 27).addBox(-3.5F, 0F, -1F, 7F, 5F, 4F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.5F, -9F, -3.2F, -0.7854F, -0.0873F, -0.14F));

        PartDefinition Butt = BodyMain.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(0, 38).addBox(-7.5F, -2F, -4.1F, 15F, 8F, 8F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0F, 4.7F, 0.5F, 0.5236F, 0F, 0F));

        PartDefinition LegLeft01 = Butt.addOrReplaceChild("LegLeft01", CubeListBuilder.create().texOffs(0, 88).mirror().addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offset(4.2F, 5F, -1F));

        PartDefinition LegLeft02 = LegLeft01.addOrReplaceChild("LegLeft02", CubeListBuilder.create().texOffs(1, 110).mirror().addBox(-3F, 0F, 0F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 12F, -3F));

        PartDefinition ShoesLeft = LegLeft02.addOrReplaceChild("ShoesLeft", CubeListBuilder.create().texOffs(0, 109).addBox(-3.5F, 4.5F, -0.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition LegRight01 = Butt.addOrReplaceChild("LegRight01", CubeListBuilder.create().texOffs(0, 88).addBox(-3F, 0F, -3F, 6F, 12F, 6F, new CubeDeformation(0F)), PartPose.offset(-4.2F, 5F, -1F));

        PartDefinition LegRight02 = LegRight01.addOrReplaceChild("LegRight02", CubeListBuilder.create().texOffs(1, 110).addBox(-3F, 0F, 0F, 6F, 7F, 6F, new CubeDeformation(0F)), PartPose.offset(0F, 12F, -3F));

        PartDefinition ShoesRight = LegRight02.addOrReplaceChild("ShoesRight", CubeListBuilder.create().texOffs(0, 109).addBox(-3.5F, 4.5F, -0.5F, 7F, 9F, 7F, new CubeDeformation(0F)), PartPose.offset(0F, 0F, 0F));

        PartDefinition GlowBodyMain = partdefinition.addOrReplaceChild("GlowBodyMain", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -11F, 0F, -0.3491F, 0F, 0F));

        PartDefinition GlowHead = GlowBodyMain.addOrReplaceChild("GlowHead", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0F, -13F, -0.5F));

        PartDefinition GlowEquipBase = GlowHead.addOrReplaceChild("GlowEquipBase", CubeListBuilder.create().texOffs(0, 0), PartPose.offsetAndRotation(0F, -10F, -3F, 0.0873F, 0F, 0F));

        PartDefinition EquipEye01 = GlowEquipBase.addOrReplaceChild("EquipEye01", CubeListBuilder.create().texOffs(44, 0).addBox(-7.5F, -6F, 0F, 15F, 6F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-14.5F, -21F, -8F, 0.1396F, 0.1396F, -0.2618F));

        PartDefinition EquipEye02 = GlowEquipBase.addOrReplaceChild("EquipEye02", CubeListBuilder.create().texOffs(44, 0).addBox(-7.5F, -6F, 0F, 15F, 6F, 14F, new CubeDeformation(0F)), PartPose.offsetAndRotation(14.5F, -21F, -8F, 0.1396F, -0.1396F, 0.2618F));

        ShipModelBaseAdv.addFaceLayerWo(GlowHead);

        return LayerDefinition.create(meshdefinition, 256, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetPoseState();
        this.resetOffsets();
        this.applyEquipVisibility(entity);
        this.applyFaceAndMouth(entity);

        if (entity != null && entity.isInDeadPose()) {
            this.applyDeadPose();
            this.syncGlowParts();
            return;
        }

        this.applyBasePose(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.applySpecialPoseAdjustments(entity, limbSwing, limbSwingAmount, ageInTicks);
        this.syncGlowParts();
    }

    private void resetPoseState() {
        this.isDeadPose = false;
        this.isSittingPose = false;
        this.poseTranslateY = 0.0F;
    }

    private void resetOffsets() {
        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight02.z = this.legRight02DefaultZ;
        this.Staff.x = this.staffDefaultX;
        this.Staff.y = this.staffDefaultY;
        this.Staff.z = this.staffDefaultZ;
    }

    private void applyEquipVisibility(T entity) {
        if (entity == null) {
            this.EquipBase.visible = true;
            this.GlowEquipBase.visible = true;
            this.Staff.visible = true;
            this.Neck.visible = true;
            this.CloakNeck.visible = true;
            return;
        }

        this.EquipBase.visible = hasLegacyModelFlag(entity, 0);
        this.GlowEquipBase.visible = this.EquipBase.visible;
        this.Staff.visible = hasLegacyModelFlag(entity, 1);
        this.Neck.visible = hasLegacyModelFlag(entity, 2);
        this.CloakNeck.visible = hasLegacyModelFlag(entity, 3);
    }

    private void applyDeadPose() {
        this.isDeadPose = true;
        this.poseTranslateY = DEAD_TRANSLATE_Y;

        this.Head.yRot = 0.0F;
        this.Head.xRot = 0.0F;
        this.BoobL.xRot = -0.63F;
        this.BoobR.xRot = -0.63F;
        this.Ahoke.yRot = 0.5236F;
        this.ArmRight02.yRot = 0.0F;
        this.Butt.y = this.buttDefaultY;
        this.BodyMain.xRot = 0.2094F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;
        this.Butt.xRot = -0.4189F;
        this.Butt.z = this.buttDefaultZ + (-0.12F * OFFSET_SCALE);
        this.ArmLeft01.xRot = -1.0472F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmLeft01.zRot = 0.4189F;
        this.ArmLeft02.xRot = -0.1396F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 1.2915F;
        this.ArmRight01.xRot = -0.8727F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmRight01.zRot = -0.0873F;
        this.ArmRight02.zRot = -1.1345F;
        this.LegLeft01.xRot = -2.2689F;
        this.LegLeft01.yRot = -0.2094F;
        this.LegLeft01.zRot = -0.2094F;
        this.LegLeft02.xRot = 1.7454F;
        this.LegLeft02.z = this.legLeft02DefaultZ + (0.3F * OFFSET_SCALE);
        this.LegRight01.xRot = -2.2689F;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = 0.0873F;
        this.LegRight02.xRot = 1.5708F;
        this.LegRight02.z = this.legRight02DefaultZ + (0.3F * OFFSET_SCALE);
        this.Cloak01.xRot = 0.2618F;
        this.Cloak02.xRot = -1.3963F;
        this.Cloak03.xRot = -0.9425F;

        this.Staff.xRot = 1.309F;
        this.Staff.yRot = -0.5934F;
        this.Staff.zRot = -0.2094F;
        this.Staff.x = this.staffDefaultX + (-0.3F * OFFSET_SCALE);
        this.Staff.y = this.staffDefaultY + (-1.5F * OFFSET_SCALE);
        this.Staff.z = this.staffDefaultZ + (-1.7F * OFFSET_SCALE);

        this.EquipLC01.xRot = this.Head.xRot;
        this.EquipRC01.xRot = this.Head.xRot;
        this.EquipT01L.xRot = -0.2618F;
        this.EquipT01L.zRot = -0.2618F;
        this.EquipT02L.xRot = -0.3491F;
        this.EquipT02L.zRot = 0.2618F;
        this.EquipT03L.xRot = 1.0472F;
        this.EquipT03L.zRot = 1.0472F;
        this.EquipT01R.xRot = -0.2618F;
        this.EquipT01R.zRot = 0.2618F;
        this.EquipT02R.xRot = -0.3491F;
        this.EquipT02R.zRot = -0.2618F;
        this.EquipT03R.xRot = 1.0472F;
        this.EquipT03R.zRot = -1.0472F;

        this.EquipTB01L.xRot = 0.1745F;
        this.EquipTB01L.zRot = -0.3491F;
        this.EquipTB02L.xRot = -0.6981F;
        this.EquipTB02L.zRot = 0.3491F;
        this.EquipTB03L.xRot = 0.1745F;
        this.EquipTB03L.zRot = 0.2618F;
        this.EquipTB01R.xRot = 0.1745F;
        this.EquipTB01R.zRot = 0.3491F;
        this.EquipTB02R.xRot = -0.6981F;
        this.EquipTB02R.zRot = -0.3491F;
        this.EquipTB03R.xRot = 0.1745F;
        this.EquipTB03R.zRot = -0.2618F;
    }

    private void applyBasePose(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float angleZ = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float addk1 = net.minecraft.util.Mth.cos(limbSwing * 0.4F) * 0.5F * limbSwingAmount;
        float addk2 = net.minecraft.util.Mth.cos(limbSwing * 0.4F + (float) Math.PI) * 0.5F * limbSwingAmount;

        if(entity.getShipDepth() > 0.0) {
            this.poseTranslateY += angleZ * 0.05F + 0.025F;
        }

        this.Head.xRot = headPitch * ((float) Math.PI / 180F) * 0.6875F;
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.5729F;

        this.BoobL.xRot = -angleZ * 0.06F - 0.63F;
        this.BoobR.xRot = -angleZ * 0.06F - 0.63F;
        this.Ahoke.yRot = angleZ * 0.25F + 0.5236F;

        this.ArmLeft01.xRot = -0.3F;
        this.ArmRight01.xRot = -0.3F;
        this.ArmLeft01.yRot = 0.0F;
        this.ArmRight01.yRot = 0.0F;
        this.ArmLeft01.zRot = 0.24F;
        this.ArmRight01.zRot = -0.24F;
        this.ArmLeft02.xRot = 0.0F;
        this.ArmLeft02.yRot = 0.0F;
        this.ArmLeft02.zRot = 0.0F;
        this.ArmRight02.yRot = 0.0F;
        this.ArmRight02.zRot = 0.0F;

        this.BodyMain.xRot = -0.1745F;
        this.BodyMain.yRot = 0.0F;
        this.BodyMain.zRot = 0.0F;

        this.Butt.xRot = 0.5236F;
        this.Butt.y = this.buttDefaultY;
        this.Butt.z = this.buttDefaultZ;

        this.HairL01.xRot = -0.3F;
        this.HairL02.xRot = 0.35F;
        this.HairR01.xRot = -0.3F;
        this.HairR02.xRot = 0.35F;
        this.HairL01.zRot = -0.314F;
        this.HairL02.zRot = 0.2618F;
        this.HairR01.zRot = 0.314F;
        this.HairR02.zRot = -0.2618F;

        addk1 += -0.349F;
        addk2 += -0.349F;

        this.LegLeft01.yRot = 0.0F;
        this.LegLeft01.zRot = 0.052F;
        this.LegLeft02.xRot = 0.0F;
        this.LegLeft02.z = this.legLeft02DefaultZ;
        this.LegRight01.yRot = 0.0F;
        this.LegRight01.zRot = -0.052F;
        this.LegRight02.xRot = 0.0F;
        this.LegRight02.z = this.legRight02DefaultZ;

        this.Cloak01.xRot = angleZ * 0.05F + 0.2618F;
        this.Cloak02.xRot = angleZ * 0.1F + 0.1745F;
        this.Cloak03.xRot = angleZ * 0.15F + 0.2618F;

        this.Staff.xRot = 0.0F;
        this.Staff.yRot = 0.0F;
        this.Staff.zRot = 1.8326F;
        this.Staff.x = this.staffDefaultX + (-0.7F * OFFSET_SCALE);
        this.Staff.y = this.staffDefaultY + (-1.7F * OFFSET_SCALE);
        this.Staff.z = this.staffDefaultZ + (-1.4F * OFFSET_SCALE);

        this.EquipLC01.xRot = this.Head.xRot;
        this.EquipRC01.xRot = this.Head.xRot;

        this.EquipT01L.xRot = angleZ * 0.05F - 0.2618F;
        this.EquipT01L.zRot = angleZ * 0.05F - 0.2618F;
        this.EquipT02L.xRot = angleZ * 0.1F;
        this.EquipT02L.zRot = angleZ * 0.1F;
        this.EquipT03L.xRot = angleZ * 0.25F;
        this.EquipT03L.zRot = angleZ * 0.25F;

        this.EquipT01R.xRot = angleZ * 0.05F - 0.2618F;
        this.EquipT01R.zRot = -angleZ * 0.05F + 0.2618F;
        this.EquipT02R.xRot = angleZ * 0.1F;
        this.EquipT02R.zRot = -angleZ * 0.1F;
        this.EquipT03R.xRot = angleZ * 0.25F;
        this.EquipT03R.zRot = -angleZ * 0.25F;

        this.EquipTB01L.xRot = -angleZ * 0.05F + 0.2618F;
        this.EquipTB01L.zRot = angleZ * 0.05F - 0.2618F;
        this.EquipTB02L.xRot = -angleZ * 0.1F;
        this.EquipTB02L.zRot = angleZ * 0.1F;
        this.EquipTB03L.xRot = -angleZ * 0.25F;
        this.EquipTB03L.zRot = angleZ * 0.25F;

        this.EquipTB01R.xRot = -angleZ * 0.05F + 0.2618F;
        this.EquipTB01R.zRot = -angleZ * 0.05F + 0.2618F;
        this.EquipTB02R.xRot = -angleZ * 0.1F;
        this.EquipTB02R.zRot = -angleZ * 0.1F;
        this.EquipTB03R.xRot = -angleZ * 0.25F;
        this.EquipTB03R.zRot = -angleZ * 0.25F;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;
    }

    private void applySpecialPoseAdjustments(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        float angleZ = net.minecraft.util.Mth.cos(ageInTicks * 0.08F);
        float addk1 = this.LegLeft01.xRot;
        float addk2 = this.LegRight01.xRot;

        boolean isSprinting = entity != null && entity.isSprinting();
        boolean isCrouching = entity != null && entity.isCrouching();
        boolean isPassenger = entity != null && entity.isPassenger();
        boolean isSitting = entity != null && (entity.getIsSitting() || isPassenger);

        if (isSprinting || limbSwingAmount > 0.9F) {
            float angleZFast = net.minecraft.util.Mth.cos(ageInTicks * 0.3F);
            this.ArmLeft01.xRot = -0.6981F;
            this.ArmRight01.xRot = -0.6981F;
            this.ArmLeft01.yRot = 0.4F;
            this.ArmRight01.yRot = -0.4F;
            this.ArmLeft01.zRot = 0.0F;
            this.ArmRight01.zRot = 0.0F;
            this.BodyMain.xRot = -0.349F;
            addk1 = 0.0F;
            addk2 = 0.0F;
            this.LegLeft01.yRot = 0.0F;
            this.LegRight01.yRot = 0.0F;
            this.LegLeft01.zRot = 0.05236F;
            this.LegRight01.zRot = -0.05236F;

            this.Cloak01.xRot = angleZFast * 0.1F + 1.2F;
            this.Cloak02.xRot = angleZFast * 0.25F;
            this.Cloak03.xRot = angleZFast * 0.15F;

            this.Staff.xRot = 1.3F;
            this.Staff.yRot = -0.182F;
            this.Staff.zRot = -1.2292F;
            this.Staff.x = this.staffDefaultX + (0.2F * OFFSET_SCALE);
            this.Staff.y = this.staffDefaultY + (-1.0F * OFFSET_SCALE);
            this.Staff.z = this.staffDefaultZ + (-0.1F * OFFSET_SCALE);

            this.EquipT01L.xRot = angleZFast * 0.05F + 0.2618F;
            this.EquipT01L.zRot = -0.2618F;
            this.EquipT02L.xRot = angleZFast * 0.15F + 0.2618F;
            this.EquipT02L.zRot = -0.2618F;
            this.EquipT03L.xRot = angleZFast * 0.45F + 0.5236F;
            this.EquipT03L.zRot = -0.2618F;

            this.EquipT01R.xRot = angleZFast * 0.05F + 0.2618F;
            this.EquipT01R.zRot = 0.2618F;
            this.EquipT02R.xRot = angleZFast * 0.15F + 0.2618F;
            this.EquipT02R.zRot = 0.2618F;
            this.EquipT03R.xRot = angleZFast * 0.45F + 0.5236F;
            this.EquipT03R.zRot = 0.2618F;

            this.EquipTB01L.xRot = angleZFast * 0.05F + 0.349F;
            this.EquipTB01L.zRot = -0.349F;
            this.EquipTB02L.xRot = angleZFast * 0.15F + 0.5236F;
            this.EquipTB02L.zRot = 0.1745F;
            this.EquipTB03L.xRot = angleZFast * 0.45F + 0.5236F;
            this.EquipTB03L.zRot = 0.1745F;

            this.EquipTB01R.xRot = angleZFast * 0.05F + 0.349F;
            this.EquipTB01R.zRot = 0.349F;
            this.EquipTB02R.xRot = angleZFast * 0.15F + 0.5236F;
            this.EquipTB02R.zRot = -0.1745F;
            this.EquipTB03R.xRot = angleZFast * 0.45F + 0.5236F;
            this.EquipTB03R.zRot = -0.1745F;
        }

        if (isCrouching) {
            this.poseTranslateY += SNEAK_TRANSLATE_Y;
            this.ArmLeft01.xRot = 0.7F;
            this.ArmRight01.xRot = 0.7F;
            this.BodyMain.xRot = 0.5F;
            this.Head.xRot -= 0.5F;
            this.Cloak01.xRot = angleZ * 0.02F + 0.34F;
            addk1 -= 0.66F;
            addk2 -= 0.66F;
        } else {
            this.Head.xRot += 0.2F;
        }

        if (isSitting) {
            this.isSittingPose = true;
            if (hasLegacyState(entity, 1, 4)) {
                this.poseTranslateY += 0.41F;
                this.BodyMain.xRot = 0.2094F;
                this.BodyMain.yRot = 0.0F;
                this.BodyMain.zRot = 0.0F;
                this.Butt.xRot = -0.4189F;
                this.Butt.z = this.buttDefaultZ + (-0.12F * OFFSET_SCALE);
                this.Head.yRot *= 0.5F;
                this.ArmLeft01.xRot = -1.0472F;
                this.ArmLeft01.yRot = 0.0F;
                this.ArmLeft01.zRot = 0.4189F;
                this.ArmLeft02.xRot = -0.1396F;
                this.ArmLeft02.yRot = 0.0F;
                this.ArmLeft02.zRot = 1.2915F;
                this.ArmRight01.xRot = -0.8727F;
                this.ArmRight01.yRot = 0.0F;
                this.ArmRight01.zRot = -0.0873F;
                this.ArmRight02.zRot = -1.1345F;
                addk1 = -2.2689F;
                addk2 = -2.2689F;
                this.LegLeft01.yRot = -0.2094F;
                this.LegLeft01.zRot = -0.2094F;
                this.LegLeft02.xRot = 1.7454F;
                this.LegLeft02.z = this.legLeft02DefaultZ + (0.3F * OFFSET_SCALE);
                this.LegRight01.yRot = 0.0F;
                this.LegRight01.zRot = 0.0873F;
                this.LegRight02.xRot = 1.5708F;
                this.LegRight02.z = this.legRight02DefaultZ + (0.3F * OFFSET_SCALE);
                this.Cloak01.xRot = 0.2618F;
                this.Cloak02.xRot = -1.3963F;
                this.Cloak03.xRot = -0.9425F;
                this.Staff.xRot = 1.309F;
                this.Staff.yRot = -0.5934F;
                this.Staff.zRot = -0.2094F;
                this.Staff.x = this.staffDefaultX + (-0.3F * OFFSET_SCALE);
                this.Staff.y = this.staffDefaultY + (-1.5F * OFFSET_SCALE);
                this.Staff.z = this.staffDefaultZ + (-1.7F * OFFSET_SCALE);
            } else {
                this.ArmLeft01.xRot = 0.4F;
                this.ArmLeft01.yRot = 0.0F;
                this.ArmLeft01.zRot = -0.32F;
                this.ArmRight01.xRot = 0.34F;
                this.ArmRight01.yRot = 0.0F;
                this.ArmRight01.zRot = 0.5236F;
                this.BodyMain.xRot = -0.349F;
                this.BodyMain.yRot = -1.57F;
                this.BodyMain.zRot = -0.0873F;
                this.Head.xRot += -0.25F;
                this.Head.yRot += 0.4F;
                this.Head.zRot += 0.0F;
                addk1 = angleZ * 0.3F - 1.0472F;
                addk2 = -angleZ * 0.3F - 1.0472F;
                this.LegLeft01.yRot = 0.0F;
                this.LegRight01.yRot = 0.0F;
                this.LegLeft01.zRot = 0.05236F;
                this.LegRight01.zRot = -0.05236F;

                this.Cloak01.xRot = angleZ * 0.1F + 0.4F;
                this.Cloak02.xRot = angleZ * 0.15F;
                this.Cloak03.xRot = angleZ * 0.15F;

                this.Staff.xRot = 0.2F;
                this.Staff.yRot = 0.0F;
                this.Staff.zRot = -2.0F;
                this.Staff.x = this.staffDefaultX + (1.1F * OFFSET_SCALE);
                this.Staff.y = this.staffDefaultY + (-1.95F * OFFSET_SCALE);
                this.Staff.z = this.staffDefaultZ + (-1.4F * OFFSET_SCALE);

                this.EquipT01L.xRot = -angleZ * 0.05F + 0.2618F;
                this.EquipT01L.zRot = -0.2618F;
                this.EquipT02L.xRot = -angleZ * 0.15F + 0.2618F;
                this.EquipT02L.zRot = -0.1618F;
                this.EquipT03L.xRot = -angleZ * 0.45F + 0.0F;
                this.EquipT03L.zRot = -0.2618F;

                this.EquipT01R.xRot = angleZ * 0.05F + 0.2618F;
                this.EquipT01R.zRot = 0.2618F;
                this.EquipT02R.xRot = angleZ * 0.15F + 0.2618F;
                this.EquipT02R.zRot = 0.1618F;
                this.EquipT03R.xRot = angleZ * 0.45F + 0.0F;
                this.EquipT03R.zRot = 0.2618F;

                this.EquipTB01L.xRot = angleZ * 0.05F + 0.349F;
                this.EquipTB01L.zRot = -0.349F;
                this.EquipTB02L.xRot = angleZ * 0.15F + 0.2236F;
                this.EquipTB02L.zRot = 0.1745F;
                this.EquipTB03L.xRot = angleZ * 0.45F + 0.1236F;
                this.EquipTB03L.zRot = 0.1745F;

                this.EquipTB01R.xRot = -angleZ * 0.05F + 0.349F;
                this.EquipTB01R.zRot = 0.349F;
                this.EquipTB02R.xRot = -angleZ * 0.15F + 0.2236F;
                this.EquipTB02R.zRot = -0.1745F;
                this.EquipTB03R.xRot = -angleZ * 0.45F + 0.1236F;
                this.EquipTB03R.zRot = -0.1745F;
            }
        }

        float headX = this.Head.xRot * -0.5F;
        float headZ = this.Head.zRot * -0.5F;
        this.HairL01.zRot += headZ;
        this.HairL02.zRot += headZ;
        this.HairR01.zRot += headZ;
        this.HairR02.zRot += headZ;
        this.HairL01.xRot += headX;
        this.HairL02.xRot += headX;
        this.HairR01.xRot += headX;
        this.HairR02.xRot += headX;

        this.LegLeft01.xRot = addk1;
        this.LegRight01.xRot = addk2;

        if (entity != null && entity.getAttackTick() > 0) {
            this.ArmLeft01.xRot = this.Head.xRot - 1.5F;
            this.ArmRight01.zRot = 0.7F;
            this.ArmRight01.xRot = 0.4F;
            this.Staff.xRot = 1.5F;
            this.Staff.yRot = 0.0F;
            this.Staff.zRot = -1.2F;
            this.Staff.x = this.staffDefaultX + (-0.2F * OFFSET_SCALE);
            this.Staff.y = this.staffDefaultY + (-1.2F * OFFSET_SCALE);
            this.Staff.z = this.staffDefaultZ + (-1.0F * OFFSET_SCALE);
        }

        float swing = getLegacySwingTime(entity, ageInTicks - (int) ageInTicks);
        if (swing != 0.0F) {
            float f7 = net.minecraft.util.Mth.sin(swing * swing * (float) Math.PI);
            float f8 = net.minecraft.util.Mth.sin(net.minecraft.util.Mth.sqrt(swing) * (float) Math.PI);
            this.ArmRight01.xRot = -0.2F;
            this.ArmRight01.yRot = 0.0F;
            this.ArmRight01.zRot = -0.1F;
            this.ArmRight01.xRot += -f8 * 80.0F * ((float) Math.PI / 180F);
            this.ArmRight01.yRot += -f7 * 20.0F * ((float) Math.PI / 180F) + 0.2F;
            this.ArmRight01.zRot += -f8 * 20.0F * ((float) Math.PI / 180F);
            this.ArmRight02.xRot = 0.0F;
            this.ArmRight02.yRot = 0.0F;
            this.ArmRight02.zRot = 0.0F;
        }
    }

    private void syncGlowParts() {
        this.GlowBodyMain.copyFrom(this.BodyMain);
        this.GlowHead.copyFrom(this.Head);
        this.GlowEquipBase.copyFrom(this.EquipBase);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        boolean usePoseTranslate = this.poseTranslateY != 0.0F;
        if (usePoseTranslate) {
            poseStack.pushPose();
            poseStack.translate(0.0F, this.poseTranslateY, 0.0F);
        }

        this.BodyMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.GlowBodyMain.render(poseStack, vertexConsumer, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, packedOverlay, 0xFFFFFFFF);

        if (usePoseTranslate) {
            poseStack.popPose();
        }
    }
}
