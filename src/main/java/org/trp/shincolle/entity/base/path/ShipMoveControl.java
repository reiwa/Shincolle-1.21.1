package org.trp.shincolle.entity.base.path;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class ShipMoveControl extends MoveControl {

    private static final double ARRIVAL_STOP_DISTANCE_SQR = 0.2D * 0.2D;
    private static final double ARRIVAL_SLOWDOWN_DISTANCE_SQR = 2.25D;
    private static final double STEP_ASSIST_MIN_DY = -0.15D;
    private static final double LIQUID_HOVER_OFFSET = 0.08D;
    private static final float LIQUID_CRUISE_SPEED_FACTOR = 1.35F;
    private static final double LIQUID_STEER_STRENGTH = 0.9D;
    private static final double LIQUID_NEAR_STOP_STEER_STRENGTH = 0.78D;
    private static final double LIQUID_INERTIA_DAMPING = 0.35D;
    private static final double LIQUID_STOP_EPSILON = 0.003D;

    private final float maxTurn;

    public ShipMoveControl(Mob host, float maxTurn) {
        super(host);
        this.maxTurn = maxTurn;
    }

    @Override
    public void tick() {
        this.mob.setZza(0.0F);

        if (this.operation != Operation.MOVE_TO) {
            applyIdleLiquidStabilization();
            return;
        }

        this.operation = Operation.WAIT;

        double dx = this.wantedX - this.mob.getX();
        double dy = this.wantedY - this.mob.getY();
        double dz = this.wantedZ - this.mob.getZ();
        double horizontalSq = dx * dx + dz * dz;
        double distSq = horizontalSq + dy * dy;

        if (distSq < ARRIVAL_STOP_DISTANCE_SQR) {
            this.mob.setSpeed(0.0F);
            this.mob.setZza(0.0F);

            Vec3 motion = this.mob.getDeltaMovement();

            if (isInLiquid()) {
                double motionY = Mth.clamp(motion.y * 0.45D + computeFluidSurfaceCorrection(0.08D), -0.04D, 0.04D);
                this.mob.setDeltaMovement(0.0D, motionY, 0.0D);
            } else {
                this.mob.setDeltaMovement(motion.x * 0.75D, motion.y, motion.z * 0.75D);
            }

            return;
        }

        float targetYaw = (float) (Mth.atan2(dz, dx) * (180.0D / Math.PI)) - 90.0F;
        this.mob.setYRot(this.rotlerp(this.mob.getYRot(), targetYaw, this.maxTurn));
        this.mob.yBodyRot = this.mob.getYRot();
        this.mob.yHeadRot = this.mob.getYRot();

        float baseMoveSpeed = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
        double dist = Math.sqrt(distSq);
        float distanceScale = 1.0F;

        if (distSq < ARRIVAL_SLOWDOWN_DISTANCE_SQR) {
            distanceScale = Mth.clamp((float) (dist / 1.5D), 0.2F, 1.0F);
        }

        float moveSpeed = baseMoveSpeed * distanceScale;

        if (isInLiquid()) {
            Vec3 motion = this.mob.getDeltaMovement();
            double verticalBoost = 0.0D;

            if (dy > 1.5D) {
                verticalBoost = moveSpeed * 0.15D;
                moveSpeed *= 0.5F;
            } else if (dy > 0.25D) {
                verticalBoost = moveSpeed * 0.08D;
                moveSpeed *= 0.75F;
            } else if (dy > -0.5D && dy < 0.0D) {
                verticalBoost = moveSpeed * 0.1D;
                moveSpeed *= 0.5F;
            } else if (dy < -1.0D) {
                verticalBoost = -moveSpeed * 0.35D;
                moveSpeed *= 0.82F;
            }

            double motionY = motion.y * 0.72D + verticalBoost + computeFluidSurfaceCorrection(0.06D);

            if (this.mob.horizontalCollision && dy > -0.2D) {
                motionY = Math.max(motionY, 0.14D);
            }

            double horizontal = Math.sqrt(horizontalSq);
            double targetVx = 0.0D;
            double targetVz = 0.0D;

            if (horizontal > 1.0E-5D) {
                double cruise = moveSpeed * LIQUID_CRUISE_SPEED_FACTOR;
                double invHorizontal = 1.0D / horizontal;
                targetVx = dx * invHorizontal * cruise;
                targetVz = dz * invHorizontal * cruise;
            }

            double steerStrength = distSq < ARRIVAL_SLOWDOWN_DISTANCE_SQR
                    ? LIQUID_NEAR_STOP_STEER_STRENGTH
                    : LIQUID_STEER_STRENGTH;
            double newVx = Mth.lerp(steerStrength, motion.x * LIQUID_INERTIA_DAMPING, targetVx);
            double newVz = Mth.lerp(steerStrength, motion.z * LIQUID_INERTIA_DAMPING, targetVz);

            if (Math.abs(targetVx) < LIQUID_STOP_EPSILON && Math.abs(newVx) < LIQUID_STOP_EPSILON) {
                newVx = 0.0D;
            }

            if (Math.abs(targetVz) < LIQUID_STOP_EPSILON && Math.abs(newVz) < LIQUID_STOP_EPSILON) {
                newVz = 0.0D;
            }

            this.mob.setDeltaMovement(
                    newVx,
                    Mth.clamp(motionY, -0.09D, 0.14D),
                    newVz);
            this.mob.setSpeed(moveSpeed);
            //this.mob.setZza(1.0F);
            return;
        }

        boolean needsStepJump = dy > this.mob.maxUpStep() && horizontalSq < 4.0D;
        boolean blockedOnLedge = this.mob.horizontalCollision && dy > STEP_ASSIST_MIN_DY && horizontalSq < 3.0D;

        if (needsStepJump || blockedOnLedge) {
            this.mob.getJumpControl().jump();
        }

        this.mob.setSpeed(moveSpeed);
        this.mob.setZza(1.0F);
    }

    private void applyIdleLiquidStabilization() {
        if (!isInLiquid()) {
            return;
        }

        Vec3 motion = this.mob.getDeltaMovement();
        double motionY = Mth.clamp(motion.y * 0.55D + computeFluidSurfaceCorrection(0.08D), -0.05D, 0.05D);
        double motionX = motion.x * 0.3D;
        double motionZ = motion.z * 0.3D;

        if (Math.abs(motionX) < LIQUID_STOP_EPSILON) {
            motionX = 0.0D;
        }

        if (Math.abs(motionZ) < LIQUID_STOP_EPSILON) {
            motionZ = 0.0D;
        }

        this.mob.setDeltaMovement(motionX, motionY, motionZ);
    }

    private double computeFluidSurfaceCorrection(double strength) {
        double surfaceY = getFluidSurfaceY();
        if (Double.isNaN(surfaceY)) {
            return 0.0D;
        }

        double targetY = surfaceY - LIQUID_HOVER_OFFSET;
        return Mth.clamp((targetY - this.mob.getY()) * strength, -0.03D, 0.03D);
    }

    private double getFluidSurfaceY() {
        Level level = this.mob.level();
        BlockPos pos = BlockPos.containing(this.mob.getX(), this.mob.getY(), this.mob.getZ());
        FluidState fluid = level.getFluidState(pos);

        if (fluid.isEmpty()) {
            BlockPos below = pos.below();
            fluid = level.getFluidState(below);

            if (fluid.isEmpty()) {
                return Double.NaN;
            }

            pos = below;
        }

        return pos.getY() + fluid.getHeight(level, pos);
    }

    private boolean isInLiquid() {
        return this.mob.isInWaterOrBubble() || this.mob.isInLava();
    }
}
