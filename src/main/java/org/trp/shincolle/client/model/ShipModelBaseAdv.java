package org.trp.shincolle.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.trp.shincolle.entity.base.EntityShipBase;

public abstract class ShipModelBaseAdv<T extends EntityShipBase> extends EntityModel<T> {

    public ModelPart Face0, Face1, Face2, Face3, Face4;
    public ModelPart Mouth0, Mouth1, Mouth2;
    public ModelPart Flush0, Flush1;

    public void initFaceParts(ModelPart headPart) {
        this.Face0 = getChildOrNull(headPart, "Face0");
        this.Face1 = getChildOrNull(headPart, "Face1");
        this.Face2 = getChildOrNull(headPart, "Face2");
        this.Face3 = getChildOrNull(headPart, "Face3");
        this.Face4 = getChildOrNull(headPart, "Face4");

        this.Mouth0 = getChildOrNull(headPart, "Mouth0");
        this.Mouth1 = getChildOrNull(headPart, "Mouth1");
        this.Mouth2 = getChildOrNull(headPart, "Mouth2");

        this.Flush0 = getChildOrNull(headPart, "Flush0");
        this.Flush1 = getChildOrNull(headPart, "Flush1");
    }

    private ModelPart getChildOrNull(ModelPart parent, String name) {
        return parent.hasChild(name) ? parent.getChild(name) : null;
    }

    public static void addFaceLayer(PartDefinition headPartDef) {
        headPartDef.addOrReplaceChild("Face0", CubeListBuilder.create().texOffs(98, 63).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face1", CubeListBuilder.create().texOffs(98, 76).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face2", CubeListBuilder.create().texOffs(98, 89).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face3", CubeListBuilder.create().texOffs(98, 102).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face4", CubeListBuilder.create().texOffs(98, 115).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));

        headPartDef.addOrReplaceChild("Mouth0", CubeListBuilder.create().texOffs(100, 53).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth1", CubeListBuilder.create().texOffs(100, 58).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth2", CubeListBuilder.create().texOffs(114, 53).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));

        headPartDef.addOrReplaceChild("Flush0", CubeListBuilder.create().texOffs(114, 58).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(-6.0F, -3.0F, -6.9F));
        headPartDef.addOrReplaceChild("Flush1", CubeListBuilder.create().texOffs(114, 58).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(6.0F, -3.0F, -6.9F));
    }

    public static void addFaceLayerHibiki(PartDefinition headPartDef) {
        headPartDef.addOrReplaceChild("Face0", CubeListBuilder.create().texOffs(98, 63).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face1", CubeListBuilder.create().texOffs(98, 76).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face2", CubeListBuilder.create().texOffs(98, 89).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face3", CubeListBuilder.create().texOffs(98, 102).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face4", CubeListBuilder.create().texOffs(98, 115).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));

        headPartDef.addOrReplaceChild("Mouth0", CubeListBuilder.create().texOffs(22, 52).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth1", CubeListBuilder.create().texOffs(100, 58).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth2", CubeListBuilder.create().texOffs(114, 56).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));

        headPartDef.addOrReplaceChild("Flush0", CubeListBuilder.create().texOffs(114, 61).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(-6.0F, -3.0F, -6.8F));
        headPartDef.addOrReplaceChild("Flush1", CubeListBuilder.create().texOffs(114, 61).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(6.0F, -3.0F, -6.8F));
    }

    public static void addFaceLayerCAHime(PartDefinition headPartDef) {
        headPartDef.addOrReplaceChild("Face0", CubeListBuilder.create().texOffs(98, 63).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -8.5F, -6.1F));
        headPartDef.addOrReplaceChild("Face1", CubeListBuilder.create().texOffs(98, 76).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -8.5F, -6.1F));
        headPartDef.addOrReplaceChild("Face2", CubeListBuilder.create().texOffs(98, 89).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -8.5F, -6.1F));
        headPartDef.addOrReplaceChild("Face3", CubeListBuilder.create().texOffs(98, 102).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -8.5F, -6.1F));
        headPartDef.addOrReplaceChild("Face4", CubeListBuilder.create().texOffs(98, 115).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -8.5F, -6.1F));

        headPartDef.addOrReplaceChild("Mouth0", CubeListBuilder.create().texOffs(100, 53).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -0.7F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth1", CubeListBuilder.create().texOffs(100, 58).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -0.7F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth2", CubeListBuilder.create().texOffs(114, 53).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -0.7F, -6.2F));

        headPartDef.addOrReplaceChild("Flush0", CubeListBuilder.create().texOffs(114, 58).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(-6.0F, 0.7F, -6.8F));
        headPartDef.addOrReplaceChild("Flush1", CubeListBuilder.create().texOffs(114, 58).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(6.0F, 0.7F, -6.8F));
    }

    public static void addFaceLayerWo(PartDefinition headPartDef) {
        headPartDef.addOrReplaceChild("Face0", CubeListBuilder.create().texOffs(98, 63).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face1", CubeListBuilder.create().texOffs(98, 76).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face2", CubeListBuilder.create().texOffs(98, 89).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face3", CubeListBuilder.create().texOffs(98, 102).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));
        headPartDef.addOrReplaceChild("Face4", CubeListBuilder.create().texOffs(98, 115).addBox(-7.0F, 0.0F, -0.5F, 14.0F, 12.0F, 1.0F), PartPose.offset(0.0F, -12.2F, -6.1F));

        headPartDef.addOrReplaceChild("Mouth0", CubeListBuilder.create().texOffs(69, 91).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth1", CubeListBuilder.create().texOffs(69, 96).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));
        headPartDef.addOrReplaceChild("Mouth2", CubeListBuilder.create().texOffs(83, 91).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 4.0F, 1.0F), PartPose.offset(0.0F, -4.2F, -6.2F));

        headPartDef.addOrReplaceChild("Flush0", CubeListBuilder.create().texOffs(83, 96).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(-6.0F, -3.0F, -6.8F));
        headPartDef.addOrReplaceChild("Flush1", CubeListBuilder.create().texOffs(83, 96).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 0.0F), PartPose.offset(6.0F, -3.0F, -6.8F));
    }

    public void setFace(int emo) {
        if (Face0 == null) return;

        resetFaceParts();

        switch (emo) {
            case 0 -> setFacePartVisible(0, false);
            case 1 -> setFacePartVisible(1, false);
            case 2 -> setFacePartVisible(2, false);
            case 3 -> setFacePartVisible(3, false);
            case 4 -> setFacePartVisible(4, false);
            case 5 -> setFacePartVisible(0, true);
            case 6 -> setFacePartVisible(1, true);
            case 7 -> setFacePartVisible(2, true);
            case 8 -> setFacePartVisible(3, true);
            case 9 -> setFacePartVisible(4, true);
            default -> setFacePartVisible(0, false);
        }
    }

    public void setMouth(int emo) {
        if (Mouth0 == null) return;
        resetMouthParts();

        switch (emo) {
            case 0 -> setMouthPartVisible(0, false);
            case 1 -> setMouthPartVisible(1, false);
            case 2 -> setMouthPartVisible(2, false);
            case 3 -> setMouthPartVisible(0, true);
            case 4 -> setMouthPartVisible(1, true);
            case 5 -> setMouthPartVisible(2, true);
            default -> setMouthPartVisible(0, false);
        }
    }

    public void setFlushVisible(boolean show) {
        if (Flush0 == null || Flush1 == null) {
            return;
        }
        Flush0.visible = show;
        Flush1.visible = show;
    }

    protected void resetFaceParts() {
        Face0.visible = false;
        Face1.visible = false;
        Face2.visible = false;
        Face3.visible = false;
        Face4.visible = false;
        Face0.yRot = 0.0F;
        Face1.yRot = 0.0F;
        Face2.yRot = 0.0F;
        Face3.yRot = 0.0F;
        Face4.yRot = 0.0F;
    }

    protected void resetMouthParts() {
        Mouth0.visible = false;
        Mouth1.visible = false;
        Mouth2.visible = false;
        Mouth0.yRot = 0.0F;
        Mouth1.yRot = 0.0F;
        Mouth2.yRot = 0.0F;
    }

    protected ModelPart getFacePart(int index) {
        return switch (index) {
            case 0 -> Face0;
            case 1 -> Face1;
            case 2 -> Face2;
            case 3 -> Face3;
            case 4 -> Face4;
            default -> null;
        };
    }

    protected ModelPart getMouthPart(int index) {
        return switch (index) {
            case 0 -> Mouth0;
            case 1 -> Mouth1;
            case 2 -> Mouth2;
            default -> null;
        };
    }

    protected void setFacePartVisible(int index, boolean mirror) {
        ModelPart part = getFacePart(index);
        if (part == null) return;
        part.visible = true;
        part.yRot = mirror ? (float) Math.PI : 0.0F;
    }

    protected void setMouthPartVisible(int index, boolean mirror) {
        ModelPart part = getMouthPart(index);
        if (part == null) return;
        part.visible = true;
        part.yRot = mirror ? (float) Math.PI : 0.0F;
    }

    protected int getLegacyState(T entity, int index) {
        return entity != null ? entity.getStateEmotion(index) : 0;
    }

    protected boolean hasLegacyState(T entity, int index, int value) {
        return getLegacyState(entity, index) == value;
    }

    protected boolean hasLegacyModelFlag(T entity, int flagIndex) {
        int state = getLegacyState(entity, 0);
        int mask = 1 << flagIndex;
        return (state & mask) == mask;
    }

    protected float getLegacySwingTime(T entity, float partialTick) {
        return entity != null ? entity.getSwingTime(partialTick) : 0.0F;
    }

    public float getLegacyScale(EntityShipBase entity) {
        float base = switch (this.getClass().getSimpleName()) {
            case "ModelBattleshipRe",
                 "ModelDestroyerAkatsuki",
                 "ModelDestroyerHa",
                 "ModelDestroyerHibiki",
                 "ModelDestroyerHime",
                 "ModelDestroyerI",
                 "ModelDestroyerIkazuchi",
                 "ModelDestroyerInazuma",

                 "ModelDestroyerNi",
                 "ModelDestroyerRo",
                 "ModelDestroyerShimakaze" -> 0.4f;

            case "ModelCruiserAtago",
                 "ModelCruiserTakao",
                 "ModelCruiserTatsuta",
                 "ModelCruiserTenryuu" -> 0.43f;

            case "ModelBattleshipHime",
                 "ModelBattleshipTa",
                 "ModelBattleshipNagato",
                 "ModelBattleshipRu",
                 "ModelBattleshipYamato" -> 0.5f;

            case "ModelCarrierAkagi",
                 "ModelCarrierKaga",
                 "ModelCarrierHime" -> 0.46f;

            case "ModelCarrierWDemon",
                 "ModelAirfieldHime" -> 0.47f;

            case "ModelCarrierWo" -> 0.44f;

            case "ModelBBHaruna",
                 "ModelBBHiei",
                 "ModelBBKirishima",
                 "ModelBBKongou" -> 0.45f;

            case "ModelCAHime" -> 0.45f;

            case "ModelNorthernHime" -> 0.34f;
            case "ModelSSNH" -> 0.32f;
            case "ModelSubmHime",
                 "ModelSubmSo",
                 "ModelSubmKa",
                 "ModelSubmYo" -> 0.48f;
            case "ModelTransportWa" -> 0.4f;
            case "ModelIsolatedHime" -> 0.38f;
            case "ModelHeavyCruiserNe" -> 0.4f;
            case "ModelHeavyCruiserRi" -> 0.41f;
            case "ModelMidwayHime" -> 0.48f;

            default -> 0.34f;
        };

        int level = entity != null ? entity.getScaleLevel() : 0;
        level = Math.max(0, Math.min(level, 3));
        return base * (level + 1);
    }
}