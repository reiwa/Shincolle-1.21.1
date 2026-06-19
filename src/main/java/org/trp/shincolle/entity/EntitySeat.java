package org.trp.shincolle.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.trp.shincolle.block.BlockChair;
import org.trp.shincolle.init.ModEntities;

public class EntitySeat extends Entity {

    public EntitySeat(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public EntitySeat(Level level) {
        this(ModEntities.SEAT.get(), level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            if (!(this.level().getBlockState(this.blockPosition()).getBlock() instanceof BlockChair)) {
                this.ejectPassengers();
                this.discard();
                return;
            }
            if (this.tickCount > 20 && this.getPassengers().isEmpty()) {
                this.discard();
            }
        }
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    protected net.minecraft.world.phys.Vec3 getPassengerAttachmentPoint(
        Entity passenger,
        net.minecraft.world.entity.EntityDimensions dimensions,
        float scale
    ) {
        if (passenger instanceof net.minecraft.world.entity.player.Player) {
            return new net.minecraft.world.phys.Vec3(0.0D, 0.15D, 0.0D);
        }
        return new net.minecraft.world.phys.Vec3(0.0D, 0.32D, 0.0D);
    }
}
