package org.trp.shincolle.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.trp.shincolle.block.CraneBlock;
import org.trp.shincolle.client.WaypointClientHelper;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModBlockEntities;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.menu.CraneMenu;
import org.trp.shincolle.utility.InventoryHelper;
import org.trp.shincolle.item.LegacyEquipItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class CraneBlockEntity extends BlockEntity implements MenuProvider, IWaypoint {

    private final ItemStackHandler inventory = new ItemStackHandler(18) {
        @Override
        protected void onContentsChanged(int slot) {
            markForSync();
        }
    };

    private final FluidTank fluidTank = new FluidTank(16000) {
        @Override
        protected void onContentsChanged() {
            markForSync();
        }
    };

    private int remainedPower = 0;
    private int powerMax = 1000000;
    private boolean isActive = false;
    private boolean checkMetadata = false;
    private boolean checkOredict = false;
    private boolean checkNbt = false;
    private boolean enabLoad = true;
    private boolean enabUnload = true;
    private int craneMode = 0;
    private int modeItem = 0;
    private int modeRedstone = 0;
    private int modeLiquid = 0;
    private int modeEnergy = 0;

    private BlockPos lastPos = BlockPos.ZERO;
    private BlockPos nextPos = BlockPos.ZERO;
    private BlockPos chestPos = BlockPos.ZERO;
    private boolean isPaired = false;
    private UUID ownerUUID = null;
    private String ownerName = "";

    private int tickCount = 0;
    private int tickRedstone = 0;
    private EntityShipBase craningShip = null;
    private int syncedShipId = -1;
    private IItemHandler chestHandler = null;
    private int partDelay = 0;

    public CraneBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CRANE.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CraneBlockEntity be) {
        be.tickCount++;
        if (!level.isClientSide) {
            be.serverTick();
        } else {
            be.clientTick();
        }
    }

    private void clientTick() {
        if (this.level != null) {
            WaypointClientHelper.tickClient(this.level, this.worldPosition, this, this.tickCount);

            if (this.partDelay > 0) this.partDelay--;

            if (this.isActive && this.partDelay <= 0) {
                EntityShipBase targetShip = null;
                if (this.level.getEntity(this.syncedShipId) instanceof EntityShipBase ship) {
                    targetShip = ship;
                }
                
                if (targetShip != null) {
                    this.partDelay = 128;
                    double distY = this.worldPosition.getY() - targetShip.getY() - 1.0;
                    if (distY < 1.0) {
                        distY = 1.0;
                    }
                    this.level.addParticle(ModParticles.PARTICLE_CRANING.get(), 
                        this.worldPosition.getX() + 0.5, this.worldPosition.getY() - 1.0, this.worldPosition.getZ() + 0.5,
                        distY, 0.25, 0.0);
                    
                    
                    this.level.addParticle(ModParticles.PARTICLE_SPARKLE.get(), 
                        targetShip.getX(), targetShip.getY() + targetShip.getBbHeight() * 0.4, targetShip.getZ(), 
                        3.0, targetShip.getBbWidth(), 0.1);
                }
            }
        }
    }

    private void serverTick() {
        if (this.tickRedstone > 0) {
            this.tickRedstone--;
            if (this.tickRedstone == 0) setRedstoneSignal(false);
        }

        if (this.tickCount % 16 == 0) {
            if (this.isActive) {
                if (checkPairedChest()) {
                    
                    applyPreLiquidTransfer(this.modeLiquid);

                    if (checkCraningShip()) {
                        boolean moved = false;

                        if (this.enabLoad) {
                            if (applyItemTransfer(true)) moved = true;
                        }

                        if (!moved && this.enabUnload) {
                            if (applyItemTransfer(false)) moved = true;
                        }

                        
                        if (this.modeLiquid != 0) {
                            if (applyLiquidTransfer(this.modeLiquid)) moved = true;
                        }

                        

                        if (moved) {
                            this.tickRedstone = 24;
                            setRedstoneSignal(true);
                            if (this.level != null) {
                                this.level.playSound(null, this.worldPosition, ModSounds.SHIP_AIRCRAFT.get(), SoundSource.BLOCKS, 0.5F, 1.0F);
                            }
                        }
                        checkCraneEnding();
                    }
                } else {
                    this.isActive = false;
                    markForSync();
                }
            }
        }

        if (this.tickCount % 64 == 0) {
            checkValidity();
        }
    }

    private void checkValidity() {
        if (this.level == null || this.level.isClientSide) return;

        if (this.isPaired && this.chestPos != BlockPos.ZERO) {
            var handler = this.level.getCapability(Capabilities.ItemHandler.BLOCK, this.chestPos, null);
            if (handler == null) {
                this.isPaired = false;
                this.chestPos = BlockPos.ZERO;
                this.chestHandler = null;
                markForSync();
            }
        }

        if (this.nextPos != BlockPos.ZERO) {
            var be = this.level.getBlockEntity(this.nextPos);
            if (!(be instanceof IWaypoint)) {
                this.nextPos = BlockPos.ZERO;
                markForSync();
            }
        }
    }

    private boolean checkPairedChest() {
        if (this.chestPos == BlockPos.ZERO || this.level == null) return false;
        var handler = this.level.getCapability(Capabilities.ItemHandler.BLOCK, this.chestPos, null);
        if (handler != null) {
            this.chestHandler = handler;
            return true;
        }
        this.chestHandler = null;
        return false;
    }

    private boolean checkCraningShip() {
        if (this.craningShip != null && this.craningShip.isAlive() && !this.craningShip.isRemoved()) {
            if (this.craningShip.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY(), this.worldPosition.getZ() + 0.5) < 64.0) {
                if (this.craningShip.getStateMinor(43) == 2) {
                    if (this.syncedShipId != this.craningShip.getId()) {
                        this.syncedShipId = this.craningShip.getId();
                        markForSync();
                    }
                    return true;
                }
                if (this.craningShip.getStateMinor(43) == 1) {
                    this.craningShip.getNavigation().moveTo(this.worldPosition.getX() + 0.5, this.worldPosition.getY() - 2.0, this.worldPosition.getZ() + 0.5, 1.0D);
                    if (this.syncedShipId != this.craningShip.getId()) {
                        this.syncedShipId = this.craningShip.getId();
                        markForSync();
                    }
                    return true;
                }
            }
        }

        AABB aabb = new AABB(this.worldPosition).inflate(8.0);
        List<EntityShipBase> ships = this.level.getEntitiesOfClass(EntityShipBase.class, aabb);
        for (EntityShipBase ship : ships) {
            if (ship.isAlive() && ship.isTame() && this.ownerUUID != null && this.ownerUUID.equals(ship.getOwnerUUID())) {
                if (ship.getStateMinor(43) == 1 || ship.getStateMinor(43) == 2) {
                    this.craningShip = ship;
                    if (this.syncedShipId != ship.getId()) {
                        this.syncedShipId = ship.getId();
                        markForSync();
                    }
                    if (ship.getStateMinor(43) == 1) {
                        ship.getNavigation().moveTo(this.worldPosition.getX() + 0.5, this.worldPosition.getY() - 2.0, this.worldPosition.getZ() + 0.5, 1.0D);
                        ship.setStateMinor(43, 2);
                    }
                    return true;
                }
            }
        }
        if (this.syncedShipId != -1) {
            this.syncedShipId = -1;
            markForSync();
        }
        return false;
    }

    private void applyPreLiquidTransfer(int mode) {
        if (this.chestHandler == null) return;
        if (mode == 1) { 
            FluidStack drained = InventoryHelper.tryDrainContainer(this.chestHandler, this.fluidTank.getFluid(), 1000);
            if (!drained.isEmpty()) {
                this.fluidTank.fill(drained, IFluidHandler.FluidAction.EXECUTE);
            }
        } else if (mode == 2) { 
            if (!this.fluidTank.getFluid().isEmpty()) {
                InventoryHelper.tryFillContainer(this.chestHandler, this.fluidTank.getFluid());
                markChestForSync();
            }
        }
    }

    private boolean applyItemTransfer(boolean isLoading) {
        if (this.craningShip == null || this.chestHandler == null) return false;
        IItemHandler invFrom = isLoading ? this.chestHandler : this.craningShip.getInventory();
        IItemHandler invTo = isLoading ? this.craningShip.getInventory() : this.chestHandler;
        
        int filterStart = isLoading ? 0 : 9;
        boolean hasNormalFilter = false;
        for (int i = 0; i < 9; i++) {
            ItemStack filter = this.inventory.getStackInSlot(filterStart + i);
            if (!filter.isEmpty() && !getItemMode(filterStart + i)) {
                hasNormalFilter = true;
                break;
            }
        }

        if (hasNormalFilter) {
            for (int i = 0; i < 9; i++) {
                ItemStack filter = this.inventory.getStackInSlot(filterStart + i);
                if (!filter.isEmpty() && !getItemMode(filterStart + i)) {
                    if (canMoveItem(isLoading, filter)) {
                        for (int slot = 0; slot < invFrom.getSlots(); slot++) {
                            ItemStack stack = invFrom.getStackInSlot(slot);
                            if (InventoryHelper.matchTargetItem(stack, filter, this.checkMetadata, this.checkNbt, this.checkOredict)) {
                                ItemStack extracted = invFrom.extractItem(slot, stack.getCount(), false);
                                if (!extracted.isEmpty()) {
                                    boolean moved = InventoryHelper.moveItemstackToInv(invTo, extracted, null);
                                    if (extracted.getCount() > 0) {
                                        returnRemainderToSourceOrDrop(invFrom, extracted);
                                    }
                                    if (moved) {
                                        markChestForSync();
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (int slot = 0; slot < invFrom.getSlots(); slot++) {
                ItemStack stack = invFrom.getStackInSlot(slot);
                if (!stack.isEmpty() && isNotModeItem(stack, isLoading)) {
                    ItemStack extracted = invFrom.extractItem(slot, stack.getCount(), false);
                    if (!extracted.isEmpty()) {
                        boolean moved = InventoryHelper.moveItemstackToInv(invTo, extracted, null);
                        if (extracted.getCount() > 0) {
                            returnRemainderToSourceOrDrop(invFrom, extracted);
                        }
                        if (moved) {
                            markChestForSync();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean canMoveItem(boolean isLoading, ItemStack temp) {
        if (this.craneMode == 3) { 
            IItemHandler targetInv = isLoading ? this.craningShip.getInventory() : this.chestHandler;
            int current = InventoryHelper.calcItemStackAmount(targetInv, temp, this.checkMetadata, this.checkNbt, this.checkOredict);
            return current < temp.getCount();
        } else if (this.craneMode == 4) { 
            IItemHandler sourceInv = isLoading ? this.chestHandler : this.craningShip.getInventory();
            int current = InventoryHelper.calcItemStackAmount(sourceInv, temp, this.checkMetadata, this.checkNbt, this.checkOredict);
            return current > temp.getCount();
        }
        return true;
    }

    private boolean isNotModeItem(ItemStack stack, boolean isLoading) {
        int startIdx = isLoading ? 0 : 9;
        for (int i = 0; i < 9; i++) {
            ItemStack temp = this.inventory.getStackInSlot(startIdx + i);
            if (!temp.isEmpty() && InventoryHelper.matchTargetItem(stack, temp, this.checkMetadata, this.checkNbt, this.checkOredict)) {
                if (getItemMode(startIdx + i)) return false;
            }
        }
        return true;
    }

    private int calcLiquidRate(EntityShipBase ship) {
        int totalDrums = 0;
        int totalEnchants = 0;
        
        if (ship.getStateMinor(EntityShipBase.STATE_MINOR_FACTION_ID) == 7 && ship.isStateMarried()) {
            totalDrums = 1;
        }
        
        int equipSlots = Math.min(
            org.trp.shincolle.inventory.ShipInventoryHandler.getEquipSlotCount(),
            ship.getInventory().getSlots()
        );
        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = ship.getInventory().getStackInSlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof LegacyEquipItem equipItem)) {
                continue;
            }
            if (equipItem.getEquipTypeId(stack) == EntityShipBase.EQUIP_TYPE_DRUM &&
                equipItem.getVariant(stack) == EntityShipBase.EQUIP_DRUM_VARIANT_LIQUID) {
                totalDrums += 1;
                totalEnchants += org.trp.shincolle.utility.EnchantHelper.calcEnchantNumber(stack);
            }
        }
        
        return (totalEnchants * 5 + totalDrums * 40) * 16 * ((int)(ship.getLevel() * 0.1F) + 1);
    }

    private boolean applyLiquidTransfer(int mode) {
        if (this.craningShip == null) return false;
        int rateLiquid = calcLiquidRate(this.craningShip);
        if (rateLiquid <= 0) return false;

        if (mode == 1) { 
            if (this.fluidTank.getFluidAmount() <= 0) return false;
            FluidStack toFill = this.fluidTank.getFluid().copy();
            if (toFill.getAmount() > rateLiquid) {
                toFill.setAmount(rateLiquid);
            }
            int amountBefore = toFill.getAmount();
            if (InventoryHelper.tryFillContainer(this.craningShip.getInventory(), toFill)) {
                int filled = amountBefore - toFill.getAmount();
                if (filled > 0) {
                    this.fluidTank.drain(filled, IFluidHandler.FluidAction.EXECUTE);
                    return true;
                }
            }
        } else if (mode == 2) { 
            int maxDrain = Math.min(rateLiquid, this.fluidTank.getCapacity() - this.fluidTank.getFluidAmount());
            if (maxDrain <= 0) return false;
            FluidStack drained = InventoryHelper.tryDrainContainer(this.craningShip.getInventory(), this.fluidTank.getFluid(), maxDrain);
            if (!drained.isEmpty()) {
                this.fluidTank.fill(drained, IFluidHandler.FluidAction.EXECUTE);
                return true;
            }
        }
        return false;
    }

    private boolean applyEnergyTransfer() {
        return false;
    }

    private void checkCraneEnding() {
        if (this.craningShip == null) return;
        boolean stop = false;
        if (this.craneMode == 1) { 
            stop = isInventoryFull(this.enabLoad ? this.craningShip.getInventory() : this.chestHandler);
        } else if (this.craneMode == 2) { 
            stop = isInventoryEmpty(this.enabLoad ? this.chestHandler : this.craningShip.getInventory());
        }

        if (stop) {
            this.craningShip.setStateMinor(43, 0);
            this.craningShip = null;
            markForSync();
        }
    }

    private boolean isInventoryFull(IItemHandler inv) {
        for (int i = 0; i < inv.getSlots(); i++) {
            if (inv.getStackInSlot(i).isEmpty() || inv.getStackInSlot(i).getCount() < inv.getSlotLimit(i)) return false;
        }
        return true;
    }

    private boolean isInventoryEmpty(IItemHandler inv) {
        for (int i = 0; i < inv.getSlots(); i++) {
            if (!inv.getStackInSlot(i).isEmpty()) return false;
        }
        return true;
    }

    private void setRedstoneSignal(boolean power) {
        if (this.level != null && !this.level.isClientSide) {
            BlockState state = this.level.getBlockState(this.worldPosition);
            if (state.hasProperty(CraneBlock.POWERED) && state.getValue(CraneBlock.POWERED) != power) {
                this.level.setBlock(this.worldPosition, state.setValue(CraneBlock.POWERED, power), 3);
            }
        }
    }

    private void returnRemainderToSourceOrDrop(IItemHandler invFrom, ItemStack remainder) {
        if (remainder.isEmpty()) return;
        InventoryHelper.moveItemstackToInv(invFrom, remainder, null);
        if (!remainder.isEmpty() && this.level instanceof ServerLevel serverLevel) {
            net.minecraft.world.entity.item.ItemEntity drop = new net.minecraft.world.entity.item.ItemEntity(serverLevel, this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 1.0, this.worldPosition.getZ() + 0.5, remainder.copy());
            drop.setDefaultPickUpDelay();
            serverLevel.addFreshEntity(drop);
            remainder.setCount(0);
        }
    }

    private void markChestForSync() {
        if (this.chestPos != null && this.level != null && !this.level.isClientSide) {
            net.minecraft.world.level.block.entity.BlockEntity be = this.level.getBlockEntity(this.chestPos);
            if (be != null) {
                be.setChanged();
                this.level.sendBlockUpdated(this.chestPos, be.getBlockState(), be.getBlockState(), 3);
            }
        }
    }



    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.put("Tank", fluidTank.writeToNBT(registries, new CompoundTag()));
        tag.putInt("Power", remainedPower);
        tag.putInt("PowerMax", powerMax);
        tag.putBoolean("IsActive", isActive);
        tag.putBoolean("CheckMetadata", checkMetadata);
        tag.putBoolean("CheckOredict", checkOredict);
        tag.putBoolean("CheckNbt", checkNbt);
        tag.putBoolean("EnabLoad", enabLoad);
        tag.putBoolean("EnabUnload", enabUnload);
        tag.putInt("CraneMode", craneMode);
        tag.putInt("ModeItem", modeItem);
        tag.putInt("ModeRedstone", modeRedstone);
        tag.putInt("ModeLiquid", modeLiquid);
        tag.putInt("ModeEnergy", modeEnergy);
        tag.putLong("LastPos", lastPos.asLong());
        tag.putLong("NextPos", nextPos.asLong());
        tag.putLong("ChestPos", chestPos.asLong());
        tag.putBoolean("IsPaired", isPaired);
        if (ownerUUID != null) tag.putUUID("OwnerUUID", ownerUUID);
        tag.putString("OwnerName", ownerName);
        tag.putInt("SyncedShipId", syncedShipId);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory")) inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        if (tag.contains("Tank")) fluidTank.readFromNBT(registries, tag.getCompound("Tank"));
        remainedPower = tag.getInt("Power");
        powerMax = tag.getInt("PowerMax");
        isActive = tag.getBoolean("IsActive");
        checkMetadata = tag.getBoolean("CheckMetadata");
        checkOredict = tag.getBoolean("CheckOredict");
        checkNbt = tag.getBoolean("CheckNbt");
        enabLoad = tag.getBoolean("EnabLoad");
        enabUnload = tag.getBoolean("EnabUnload");
        craneMode = tag.getInt("CraneMode");
        modeItem = tag.getInt("ModeItem");
        modeRedstone = tag.getInt("ModeRedstone");
        modeLiquid = tag.getInt("ModeLiquid");
        modeEnergy = tag.getInt("ModeEnergy");
        if (tag.contains("LastPos")) lastPos = BlockPos.of(tag.getLong("LastPos"));
        if (tag.contains("NextPos")) nextPos = BlockPos.of(tag.getLong("NextPos"));
        if (tag.contains("ChestPos")) chestPos = BlockPos.of(tag.getLong("ChestPos"));
        isPaired = tag.getBoolean("IsPaired");
        if (tag.hasUUID("OwnerUUID")) ownerUUID = tag.getUUID("OwnerUUID");
        ownerName = tag.getString("OwnerName");
        syncedShipId = tag.getInt("SyncedShipId");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket getUpdatePacket() {
        return net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("tile.shincolle.BlockCrane.name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CraneMenu(containerId, playerInventory, this);
    }

    public ItemStackHandler getInventory() { return inventory; }
    public FluidTank getFluidTank() { return fluidTank; }

    public int getCraningShipId() {
        return this.craningShip == null ? 0 : this.craningShip.getId();
    }

    public int getCraningShipTimer() {
        return this.craningShip == null ? 0 : this.craningShip.getStateTimer(1);
    }

    public int getRemainedPower() { return remainedPower; }
    public void setRemainedPower(int val) { this.remainedPower = val; markForSync(); }

    public int getPowerMax() { return powerMax; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean val) { this.isActive = val; markForSync(); }

    public boolean isCheckMetadata() { return checkMetadata; }
    public void setCheckMetadata(boolean val) { this.checkMetadata = val; markForSync(); }

    public boolean isCheckOredict() { return checkOredict; }
    public void setCheckOredict(boolean val) { this.checkOredict = val; markForSync(); }

    public boolean isCheckNbt() { return checkNbt; }
    public void setCheckNbt(boolean val) { this.checkNbt = val; markForSync(); }

    public boolean isEnabLoad() { return enabLoad; }
    public void setEnabLoad(boolean val) { this.enabLoad = val; markForSync(); }

    public boolean isEnabUnload() { return enabUnload; }
    public void setEnabUnload(boolean val) { this.enabUnload = val; markForSync(); }

    public int getCraneMode() { return craneMode; }
    public void setCraneMode(int val) { this.craneMode = val; markForSync(); }

    public int getModeItem() { return modeItem; }
    public void setModeItem(int val) { this.modeItem = val; markForSync(); }
    public void setItemMode(int id, boolean val) {
        if (val) modeItem |= (1 << id);
        else modeItem &= ~(1 << id);
        markForSync();
    }
    public boolean getItemMode(int id) { return (modeItem & (1 << id)) != 0; }

    public int getModeRedstone() { return modeRedstone; }
    public void setModeRedstone(int val) { this.modeRedstone = val; markForSync(); }

    public int getModeLiquid() { return modeLiquid; }
    public void setModeLiquid(int val) { this.modeLiquid = val; markForSync(); }

    public int getModeEnergy() { return modeEnergy; }
    public void setModeEnergy(int val) { this.modeEnergy = val; markForSync(); }

    public BlockPos getLastPos() { return lastPos; }
    public void setLastPos(BlockPos pos) { this.lastPos = (pos == null ? BlockPos.ZERO : pos); markForSync(); }

    public BlockPos getNextPos() { return nextPos; }
    public void setNextPos(BlockPos pos) { this.nextPos = (pos == null ? BlockPos.ZERO : pos); markForSync(); }

    public BlockPos getChestPos() { return chestPos; }
    public void setChestPos(BlockPos pos) { this.chestPos = (pos == null ? BlockPos.ZERO : pos); this.isPaired = (this.chestPos != BlockPos.ZERO); markForSync(); }

    @Override
    @Nullable
    public UUID getOwnerUUID() { return ownerUUID; }

    public void setOwnerUUID(@Nullable UUID uuid) {
        this.ownerUUID = uuid;
        markForSync();
    }

    @Override
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String name) {
        this.ownerName = name;
        markForSync();
    }

    @Override
    public boolean showBaseParticle() { return false; }

    public void markForSync() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
}
