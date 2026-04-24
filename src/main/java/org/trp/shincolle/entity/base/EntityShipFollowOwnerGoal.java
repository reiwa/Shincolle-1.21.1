package org.trp.shincolle.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.trp.shincolle.menu.ShipContainerMenu;

import java.util.EnumSet;

final class EntityShipFollowOwnerGoal extends Goal {

    private static final int TP_COOLDOWN = 200;
    private static final double TP_DIST_SQ = 256.0;

    private final EntityShipBase ship;
    private final double speed;
    private final float defaultMaxDist;
    private final float defaultMinDist;
    private int checkTP_T;
    private int checkTP_D;

    EntityShipFollowOwnerGoal(EntityShipBase ship, double speed, float maxDist, float minDist) {
        this.ship = ship;
        this.speed = speed;
        this.defaultMaxDist = maxDist;
        this.defaultMinDist = minDist;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return canFollowOwner();
    }

    @Override
    public boolean canContinueToUse() {
        return canFollowOwner();
    }

    @Override
    public void start() {
        this.checkTP_T = 0;
        this.checkTP_D = 0;
    }

    @Override
    public void tick() {
        LivingEntity owner = ship.getOwner();
        if (owner == null) {
            return;
        }

        ++this.checkTP_T;
        ship.resetInteractionEmotionState();
        ship.getLookControl().setLookAt(owner, 30.0F, 30.0F);
        ship.getNavigation().moveTo(owner, this.speed);

        double distSq = ship.distanceToSqr(owner);

        if (distSq > TP_DIST_SQ) {
            ++this.checkTP_D;
            if (this.checkTP_D > TP_COOLDOWN) {
                this.checkTP_D = 0;
                applyTeleport(owner);
                return;
            }
        } else {
            this.checkTP_D = 0;
        }

        if (this.checkTP_T > TP_COOLDOWN) {
            this.checkTP_T = 0;
            applyTeleport(owner);
        }
    }

    @Override
    public void stop() {
        ship.getNavigation().stop();
    }

    private boolean canFollowOwner() {
        if (ship.isOrderedToSit() || ship.isInSittingPose() || ship.isInDeadPose() || ship.isPassenger()) {
            return false;
        }
        LivingEntity owner = ship.getOwner();
        if (owner == null) {
            return false;
        }
        if (ship.hasPointerTargetEntity()) {
            return false;
        }
        LivingEntity combatTarget = ship.getTarget();
        if (combatTarget != null && combatTarget.isAlive()) {
            return false;
        }

        float minDist = resolveFollowMinDistance();
        float maxDist = resolveFollowMaxDistance(minDist);
        double distanceSqr = ship.distanceToSqr(owner);
        return distanceSqr > (minDist * minDist)
                && distanceSqr < (maxDist * maxDist) * 256.0D;
    }

    private float resolveFollowMinDistance() {
        int configured = this.ship.getStateMinor(ShipContainerMenu.STATE_MINOR_FOLLOW_MIN);
        if (configured <= 0) {
            return this.defaultMinDist;
        }
        int clamped = Mth.clamp(configured, 1, 31);
        return (float) clamped;
    }

    private float resolveFollowMaxDistance(float minDist) {
        int configured = this.ship.getStateMinor(ShipContainerMenu.STATE_MINOR_FOLLOW_MAX);
        if (configured <= 0) {
            return Math.max(this.defaultMaxDist, minDist + 1.0F);
        }
        int minValue = Math.max(2, Mth.floor(minDist) + 1);
        int clamped = Mth.clamp(configured, minValue, 32);
        return (float) clamped;
    }

    private void applyTeleport(LivingEntity owner) {
        double tx = owner.getX();
        double ty = owner.getY() + 0.75;
        double tz = owner.getZ();
        if (ship.level() instanceof ServerLevel serverLevel) {
            int cx = Mth.floor(tx) >> 4;
            int cz = Mth.floor(tz) >> 4;
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
