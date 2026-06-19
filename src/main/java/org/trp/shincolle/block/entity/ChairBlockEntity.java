package org.trp.shincolle.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.trp.shincolle.entity.EntitySeat;
import org.trp.shincolle.init.ModBlockEntities;

import java.util.UUID;

public class ChairBlockEntity extends BlockEntity {
    private EntitySeat seatEntity;
    private UUID seatEntityUUID;

    public ChairBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CHAIR.get(), pos, blockState);
    }

    public boolean isOccupied() {
        if (this.seatEntity != null) {
            return !this.seatEntity.getPassengers().isEmpty();
        }
        return false;
    }

    public void sit(Entity entity) {
        if (this.level.isClientSide) {
            return;
        }
        if (this.isOccupied()) {
            return;
        }
        if (this.seatEntity == null || !this.seatEntity.isAlive()) {
            this.seatEntity = new EntitySeat(this.level);
            this.seatEntity.setPos(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.25D, this.worldPosition.getZ() + 0.5D);
            this.level.addFreshEntity(this.seatEntity);
            this.seatEntityUUID = this.seatEntity.getUUID();
        }
        entity.startRiding(this.seatEntity, true);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.hasUUID("SeatUUID")) {
            this.seatEntityUUID = tag.getUUID("SeatUUID");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (this.seatEntityUUID != null) {
            tag.putUUID("SeatUUID", this.seatEntityUUID);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ChairBlockEntity blockEntity) {
        if (!level.isClientSide && blockEntity.seatEntity == null && blockEntity.seatEntityUUID != null) {
            if (level instanceof ServerLevel serverLevel) {
                Entity e = serverLevel.getEntity(blockEntity.seatEntityUUID);
                if (e instanceof EntitySeat seat) {
                    blockEntity.seatEntity = seat;
                } else {
                    blockEntity.seatEntityUUID = null;
                }
            }
        }
        if (blockEntity.seatEntity != null && !level.isClientSide && blockEntity.seatEntity.tickCount > 20) {
            if (blockEntity.seatEntity.getPassengers().isEmpty()) {
                blockEntity.seatEntity.discard();
                blockEntity.seatEntity = null;
                blockEntity.seatEntityUUID = null;
            }
        }
    }
}
