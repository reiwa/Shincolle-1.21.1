package org.trp.shincolle.entity.base;

import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

/**
 * Goal that makes the ship entity look around randomly, suppressed while in dead pose.
 */
class ShipRandomLookAroundGoal extends RandomLookAroundGoal {

    private final EntityShipBase ship;

    ShipRandomLookAroundGoal(EntityShipBase ship) {
        super(ship);
        this.ship = ship;
    }

    @Override
    public boolean canUse() {
        return !this.ship.isInDeadPose() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !this.ship.isInDeadPose() && super.canContinueToUse();
    }
}
