package org.trp.shincolle.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.trp.shincolle.entity.EntityAircraftBase;
import org.trp.shincolle.entity.base.EntityMountBase;
import org.trp.shincolle.entity.base.EntityShincolleSimpleMob;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModBlockEntities;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.menu.VolCoreMenu;

import java.util.List;

public class VolCoreBlockEntity extends BlockEntity implements MenuProvider {
    public static final int SLOT_COUNT = 9;
    public static final int POWER_MAX = 9600;
    public static final int CONSUME_SPEED = 16;
    public static final int FUEL_MAGNITUDE = 240;

    private final ItemStackHandler inventory = new ItemStackHandler(SLOT_COUNT) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return stack.is(ModItems.GRUDGE.get()) || stack.is(ModItems.GRUDGE_BLOCK.get());
        }
    };

    private int remainedPower = 0;
    private boolean btnActive = false;
    private int syncTime = 0;

    public VolCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.VOL_CORE.get(), pos, blockState);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, VolCoreBlockEntity blockEntity) {
        if (level.isClientSide) return;
        blockEntity.syncTime++;

        if (blockEntity.syncTime % 16 == 0) {
            boolean canWork = blockEntity.remainedPower >= CONSUME_SPEED;
            if (canWork && blockEntity.btnActive) {
                blockEntity.remainedPower -= CONSUME_SPEED;
                blockEntity.markForSync();
            }
            if (blockEntity.isWorking() && level instanceof ServerLevel serverLevel) {
                double bx = pos.getX() + 0.5;
                double by = pos.getY() + 1.5;
                double bz = pos.getZ() + 0.5;
                for (int i = 0; i < 25; i++) {
                    double px = bx + (level.getRandom().nextFloat() * 13.0f) - 6.5;
                    double py = by + (level.getRandom().nextFloat() * 13.0f) - 4.5;
                    double pz = bz + (level.getRandom().nextFloat() * 13.0f) - 6.5;
                    serverLevel.sendParticles(
                        ModParticles.PARTICLE_SPRAY.get(),
                        px, py, pz,
                        0,
                        0.0, 0.05, 0.0,
                        1.0
                    );
                }
            }
        }

        if (blockEntity.syncTime % 32 == 0) {
            blockEntity.decrItemFuel();
            if (blockEntity.isWorking()) {
                blockEntity.volcoreFunction();
            }
        }

        if (blockEntity.syncTime % 256 == 0 && blockEntity.isWorking()) {
            double dx = pos.getX() + 0.5;
            double dy = pos.getY() + 2.5;
            double dz = pos.getZ() + 0.5;
            AABB box = new AABB(dx - 6.0, dy - 6.0, dz - 6.0, dx + 6.0, dy + 6.0, dz + 6.0);
            List<EntityShipBase> slist = level.getEntitiesOfClass(EntityShipBase.class, box);

            if (!slist.isEmpty()) {
                int emotes = level.getRandom().nextInt(11);
                for (EntityShipBase ship : slist) {
                    if (ship.isAlive()) {
                        ship.applyParticleEmotion(emotes);
                    }
                }
            }
        }
    }

    private void decrItemFuel() {
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.isEmpty()) continue;

            int fuelx = 0;
            if (stack.is(ModItems.GRUDGE.get())) {
                fuelx = FUEL_MAGNITUDE;
            } else if (stack.is(ModItems.GRUDGE_BLOCK.get())) {
                fuelx = FUEL_MAGNITUDE * 9;
            }

            if (fuelx > 0 && remainedPower + fuelx <= POWER_MAX) {
                stack.shrink(1);
                remainedPower += fuelx;
                markForSync();
                break;
            }
        }
    }

    private boolean isWorking() {
        return btnActive && remainedPower >= CONSUME_SPEED;
    }

    private void volcoreFunction() {
        if (level == null) return;

        double dx = worldPosition.getX() + 0.5;
        double dy = worldPosition.getY() + 0.5;
        double dz = worldPosition.getZ() + 0.5;
        AABB box = new AABB(dx - 6.0, dy - 6.0, dz - 6.0, dx + 6.0, dy + 6.0, dz + 6.0);

        if (isNearbyLiquid()) {
            List<EntityShipBase> slist = level.getEntitiesOfClass(EntityShipBase.class, box);
            for (EntityShipBase s : slist) {
                if (s.isTame() && (s.isInWaterOrBubble() || s.isInLava())) {
                    if (s.getHealth() < s.getMaxHealth()) {
                        s.heal(s.getMaxHealth() * 0.01f + 4.0f);
                    }
                    if (s.getMorale() < 9180) {
                        s.addMorale(80);
                    }
                }
            }
        } else {
            List<LivingEntity> elist = level.getEntitiesOfClass(LivingEntity.class, box);
            for (LivingEntity ent : elist) {
                if (ent instanceof EntityShipBase || ent instanceof EntityMountBase || ent instanceof EntityAircraftBase || ent instanceof EntityShincolleSimpleMob) {
                    continue;
                }

                ent.igniteForTicks(40);
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                        ParticleTypes.FLAME,
                        ent.getX(), ent.getY() + ent.getBbHeight() * 0.5, ent.getZ(),
                        5,
                        0.2, 0.2, 0.2,
                        0.02
                    );
                }
            }
        }
    }

    private boolean isNearbyLiquid() {
        if (level == null) return false;
        for (BlockPos p : BlockPos.betweenClosed(worldPosition.offset(-1, -1, -1), worldPosition.offset(1, 1, 1))) {
            if (!level.getFluidState(p).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public int getRemainedPower() {
        return remainedPower;
    }

    public void setRemainedPower(int remainedPower) {
        this.remainedPower = remainedPower;
        setChanged();
    }

    public boolean isBtnActive() {
        return btnActive;
    }

    public void setBtnActive(boolean btnActive) {
        this.btnActive = btnActive;
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("Power", remainedPower);
        tag.putBoolean("Active", btnActive);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        }
        remainedPower = tag.getInt("Power");
        btnActive = tag.getBoolean("Active");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.shincolle.blockvolcore");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new VolCoreMenu(containerId, playerInventory, this);
    }

    public void markForSync() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public net.minecraft.network.protocol.Packet<net.minecraft.network.protocol.game.ClientGamePacketListener> getUpdatePacket() {
        return net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
