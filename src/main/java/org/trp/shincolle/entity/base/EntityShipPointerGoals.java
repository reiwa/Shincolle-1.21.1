package org.trp.shincolle.entity.base;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

class EntityShipPointerMoveGoal extends Goal {
    private static final double TARGET_REACH_SQR = 1.0D;

    private final EntityShipBase ship;
    private final double speed;
    private int nextPathTick;
    private int checkTP_T;
    private int checkTP_D;

    EntityShipPointerMoveGoal(EntityShipBase ship, double speed) {
        this.ship = ship;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return ship.hasPointerTarget()
                && !ship.isOrderedToSit()
                && !ship.isInSittingPose()
                && !ship.isPassenger()
                && !ship.isInDeadPose();
    }

    @Override
    public boolean canContinueToUse() {
        if (!ship.hasPointerTarget() || ship.isOrderedToSit() || ship.isInSittingPose() || ship.isPassenger() || ship.isInDeadPose()) {
            return false;
        }
        Vec3 target = ship.getPointerTarget();
        return target != null && ship.distanceToSqr(target) > TARGET_REACH_SQR;
    }

    @Override
    public void start() {
        this.nextPathTick = 0;
        this.checkTP_T = 0;
        this.checkTP_D = 0;
        moveToTarget();
    }

    private Vec3 lastRawTarget;

    @Override
    public void tick() {
        if (!ship.hasPointerTarget()) {
            return;
        }

        Vec3 rawTarget = ship.getRawPointerTarget();
        if (rawTarget == null) {
            return;
        }

        if (lastRawTarget == null || rawTarget.distanceToSqr(lastRawTarget) > 0.01D) {
            this.nextPathTick = 0;
            this.lastRawTarget = rawTarget;
            this.checkTP_T = 0;
            this.checkTP_D = 0;
        }

        ship.resetInteractionEmotionState();

        if (this.nextPathTick-- <= 0) {
            this.nextPathTick = 10;
            moveToTarget();
        }

        if (org.trp.shincolle.Config.canTeleport) {
            Vec3 target = ship.getPointerTarget();
            if (target != null) {
                double distSq = ship.distanceToSqr(target);
                int tpCooldown = org.trp.shincolle.Config.shipTeleport.length > 0 ? org.trp.shincolle.Config.shipTeleport[0] : 200;
                double tpDistSq = org.trp.shincolle.Config.shipTeleport.length > 1 ? org.trp.shincolle.Config.shipTeleport[1] : 256.0;

                Vec3 delta = ship.getDeltaMovement();
                if (delta.x * delta.x + delta.z * delta.z < 3.0E-4) {
                    ++this.checkTP_T;
                }

                if (distSq > tpDistSq) {
                    ++this.checkTP_D;
                    if (this.checkTP_D > tpCooldown) {
                        this.checkTP_D = 0;
                        applyTeleport(target);
                        return;
                    }
                } else {
                    this.checkTP_D = 0;
                }

                if (this.checkTP_T > tpCooldown) {
                    this.checkTP_T = 0;
                    applyTeleport(target);
                }
            }
        }
    }

    @Override
    public void stop() {
        ship.getNavigation().stop();
    }

    private void moveToTarget() {
        Vec3 target = ship.getPointerTarget();
        if (target != null) {
            ship.getNavigation().moveTo(target.x, target.y, target.z, this.speed);
        }
    }

    private void applyTeleport(Vec3 target) {
        double tx = target.x;
        double ty = target.y + 0.75;
        double tz = target.z;
        if (ship.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            int cx = net.minecraft.util.Mth.floor(tx) >> 4;
            int cz = net.minecraft.util.Mth.floor(tz) >> 4;
            if (!serverLevel.hasChunk(cx, cz)) {
                return;
            }
        }
        ship.getNavigation().stop();
        ship.teleportTo(tx, ty, tz);
        this.checkTP_T = 0;
        this.checkTP_D = 0;
    }
}

class EntityShipPointerLookTargetGoal extends Goal {
    private final EntityShipBase ship;

    EntityShipPointerLookTargetGoal(EntityShipBase ship) {
        this.ship = ship;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return ship.hasPointerTargetEntity()
                && ship.getPointerTargetEntity() != null
                && !ship.isInDeadPose();
    }

    @Override
    public boolean canContinueToUse() {
        return ship.hasPointerTargetEntity()
                && ship.getPointerTargetEntity() != null
                && !ship.isInDeadPose();
    }

    @Override
    public void tick() {
        Entity target = ship.getPointerTargetEntity();
        if (target != null) {
            ship.getLookControl().setLookAt(target, 30.0F, 30.0F);
        }
    }
}
