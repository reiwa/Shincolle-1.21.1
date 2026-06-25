package org.trp.shincolle.entity.base;

import net.minecraft.world.entity.ai.goal.RandomStrollGoal;

/**
 * Goal that makes the ship entity stroll randomly, suppressed while sitting, dead, riding, or being ridden.
 */
class ShipRandomStrollGoal extends RandomStrollGoal {

    private final EntityShipBase ship;

    ShipRandomStrollGoal(EntityShipBase ship, double speed) {
        super(ship, speed);
        this.ship = ship;
    }

    @Override
    public boolean canUse() {
        return (
            !this.ship.isOrderedToSit() &&
            !this.ship.isInSittingPose() &&
            !this.ship.isInDeadPose() &&
            !this.ship.isPassenger() &&
            !this.ship.isVehicle() &&
            super.canUse()
        );
    }

    @Override
    public boolean canContinueToUse() {
        return (
            !this.ship.isOrderedToSit() &&
            !this.ship.isInSittingPose() &&
            !this.ship.isInDeadPose() &&
            !this.ship.isPassenger() &&
            !this.ship.isVehicle() &&
            super.canContinueToUse()
        );
    }
}
