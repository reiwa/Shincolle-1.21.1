package org.trp.shincolle.entity.base;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;

class ShipLookAtPlayerGoal extends LookAtPlayerGoal {

    private final EntityShipBase ship;

    ShipLookAtPlayerGoal(EntityShipBase ship, float lookDistance) {
        super(ship, Player.class, lookDistance);
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
