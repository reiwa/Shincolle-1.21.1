package org.trp.shincolle.client.model;

import net.minecraft.client.model.geom.ModelPart;
import org.trp.shincolle.entity.base.EntityShipBase;

public abstract class ShipModelHumanoidBase<T extends EntityShipBase> extends ShipModelBaseAdv<T> {

    protected static final class PoseContext {
        public final float angleX;
        public final float angleAdd1;
        public final float angleAdd2;
        public final float legAddLeft;
        public final float legAddRight;
        public final boolean isSitting;

        private PoseContext(float angleX, float angleAdd1, float angleAdd2,
                            float legAddLeft, float legAddRight, boolean isSitting) {
            this.angleX = angleX;
            this.angleAdd1 = angleAdd1;
            this.angleAdd2 = angleAdd2;
            this.legAddLeft = legAddLeft;
            this.legAddRight = legAddRight;
            this.isSitting = isSitting;
        }
    }

    protected PoseContext computePoseContext(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float legBaseXRotOffset) {
        float angleX = (float) Math.cos(ageInTicks * 0.08F);
        float angleAdd1 = (float) Math.cos(limbSwing * 0.7F) * limbSwingAmount;
        float angleAdd2 = (float) Math.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;
        float addk1 = angleAdd1 - legBaseXRotOffset;
        float addk2 = angleAdd2 - legBaseXRotOffset;
        boolean isSitting = entity != null && entity.isInSittingPose();

        return new PoseContext(angleX, angleAdd1, angleAdd2, addk1, addk2, isSitting);
    }

    protected void applyFaceAndMouth(EntityShipBase entity) {
        if (entity == null) {
            return;
        }
        setFace(entity.getFaceId());
        setMouth(entity.getMouthId());
    }

    protected void applyHeadRotation(ModelPart head, T entity, float ageInTicks, float netHeadYaw, float headPitch) {
        if (head == null) {
            return;
        }
        float headRadY = netHeadYaw * ((float) Math.PI / 180F);
        float headRadX = headPitch * ((float) Math.PI / 180F);

        head.yRot = headRadY;
        head.xRot = headRadX;
        head.zRot = entity != null ? entity.getHeadTiltAngle(ageInTicks) : 0.0F;
    }

    protected void applySittingPose(ModelPart head, ModelPart armLeft, ModelPart armRight,
                                    ModelPart legLeft, ModelPart legRight,
                                    float headYawScale, float armZRotDelta,
                                    float legYRot, float legXRot) {
        if (head != null) {
            head.yRot *= headYawScale;
        }

        if (armLeft != null) {
            armLeft.zRot -= armZRotDelta;
        }
        if (armRight != null) {
            armRight.zRot += armZRotDelta;
        }

        if (legLeft != null) {
            legLeft.yRot = -legYRot;
            legLeft.xRot = legXRot;
        }
        if (legRight != null) {
            legRight.yRot = legYRot;
            legRight.xRot = legXRot;
        }
    }

    protected void applySittingOrLegPose(PoseContext ctx, ModelPart legLeft, ModelPart legRight,
                                         float headYawScale, float armZRotDelta, float legYRot, float legXRot,
                                         ModelPart head, ModelPart armLeft, ModelPart armRight) {
        if (ctx.isSitting) {
            applySittingPose(head, armLeft, armRight, legLeft, legRight, headYawScale, armZRotDelta, legYRot, legXRot);
        } else {
            if (legLeft != null) {
                legLeft.xRot = ctx.legAddLeft;
            }
            if (legRight != null) {
                legRight.xRot = ctx.legAddRight;
            }
        }
    }
}
