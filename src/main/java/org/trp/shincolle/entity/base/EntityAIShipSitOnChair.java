package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.trp.shincolle.block.entity.ChairBlockEntity;

import java.util.EnumSet;

public class EntityAIShipSitOnChair extends Goal {

    private final EntityShipBase host;
    private final double moveSpeed;
    private final int searchRange;
    private final int searchHeight;

    private BlockPos targetChairPos;

    public EntityAIShipSitOnChair(EntityShipBase entity, double speed) {
        this.host = entity;
        this.moveSpeed = speed;
        this.searchRange = 16;
        this.searchHeight = 8;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.host.isPassenger() || this.host.getIsSitting() || this.host.isNoAi()) {
            return false;
        }
        if (this.host.getRandom().nextInt(100) != 0) {
            return false;
        }
        this.targetChairPos = this.findNearestAvailableChair();
        return this.targetChairPos != null;
    }

    @Override
    public void start() {
        this.host.getNavigation().moveTo(
            this.targetChairPos.getX() + 0.5D,
            this.targetChairPos.getY() + 0.5D,
            this.targetChairPos.getZ() + 0.5D,
            this.moveSpeed
        );
    }

    @Override
    public boolean canContinueToUse() {
        if (this.host.isPassenger() || this.host.getNavigation().isDone()) {
            return false;
        }
        BlockEntity te = this.host.level().getBlockEntity(this.targetChairPos);
        if (!(te instanceof ChairBlockEntity)) {
            return false;
        }
        return !((ChairBlockEntity) te).isOccupied();
    }

    @Override
    public void tick() {
        if (this.host.distanceToSqr(this.targetChairPos.getX() + 0.5D, this.targetChairPos.getY() + 0.5D, this.targetChairPos.getZ() + 0.5D) < 5.5f) {
            this.host.getNavigation().stop();

            BlockEntity te = this.host.level().getBlockEntity(this.targetChairPos);
            if (te instanceof ChairBlockEntity) {
                ((ChairBlockEntity) te).sit(this.host);
            }
        }
    }

    @Override
    public void stop() {
        this.host.getNavigation().stop();
    }

    private BlockPos findNearestAvailableChair() {
        BlockPos hostPos = this.host.blockPosition();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        
        BlockPos nearestChair = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (int y = -this.searchHeight; y <= this.searchHeight; y++) {
            for (int x = -this.searchRange; x <= this.searchRange; x++) {
                for (int z = -this.searchRange; z <= this.searchRange; z++) {
                    checkPos.set(hostPos.getX() + x, hostPos.getY() + y, hostPos.getZ() + z);
                    BlockEntity te = this.host.level().getBlockEntity(checkPos);
                    if (te instanceof ChairBlockEntity && !((ChairBlockEntity) te).isOccupied()) {
                        double distSq = this.host.distanceToSqr(checkPos.getX() + 0.5D, checkPos.getY() + 0.5D, checkPos.getZ() + 0.5D);
                        if (distSq < nearestDistSq) {
                            nearestDistSq = distSq;
                            nearestChair = checkPos.immutable();
                        }
                    }
                }
            }
        }
        return nearestChair;
    }
}
