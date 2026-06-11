package org.trp.shincolle.entity.base;

import net.minecraft.world.entity.ai.goal.Goal;
import org.trp.shincolle.entity.EntitySeat;

import java.util.EnumSet;

public class EntityAIShipGetOffChair extends Goal {

    private final EntityShipBase host;

    public EntityAIShipGetOffChair(EntityShipBase entity) {
        this.host = entity;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.host.isNoAi()) {
            return false;
        }
        if (this.host.getRandom().nextInt(300) != 0) {
            return false;
        }
        return this.host.isPassenger() && this.host.getVehicle() instanceof EntitySeat;
    }

    @Override
    public void start() {
        this.host.stopRiding();
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }
}
