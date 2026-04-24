package org.trp.shincolle.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.trp.shincolle.block.SmallShipyardBlock;
import org.trp.shincolle.crafting.ShipyardRecipes;
import org.trp.shincolle.init.ModBlockEntities;
import org.trp.shincolle.menu.SmallShipyardMenu;

public class SmallShipyardBlockEntity extends BlockEntity implements MenuProvider {
    public static final int SLOT_GRUDGE = 0;
    public static final int SLOT_ABYSSIUM = 1;
    public static final int SLOT_AMMO = 2;
    public static final int SLOT_POLYMETAL = 3;
    public static final int SLOT_FUEL = 4;
    public static final int SLOT_OUTPUT = 5;
    public static final int SLOT_COUNT = 6;

    public static final int POWER_MAX = 460800;
    public static final int BUILD_SPEED = 48;

    private final ItemStackHandler inventory = new ItemStackHandler(SLOT_COUNT) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private int powerConsumed;
    private int powerRemained;
    private int powerGoal;
    private int buildType;
    private int[] buildRecord = new int[]{0, 0, 0, 0};
    private boolean active;

    public SmallShipyardBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SMALL_SHIPYARD.get(), pos, blockState);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SmallShipyardBlockEntity blockEntity) {
        if (level.isClientSide) {
            return;
        }

        boolean stateChanged = false;

        if (blockEntity.consumeFuel()) {
            stateChanged = true;
        }

        int oldGoal = blockEntity.powerGoal;
        blockEntity.updatePowerGoal();
        if (oldGoal != blockEntity.powerGoal) {
            stateChanged = true;
        }

        if (blockEntity.isBuilding()) {
            blockEntity.powerRemained -= BUILD_SPEED;
            blockEntity.powerConsumed += BUILD_SPEED;
            stateChanged = true;

            if (blockEntity.powerConsumed >= blockEntity.powerGoal) {
                blockEntity.finishBuild();
                stateChanged = true;
            }
        }

        if (!blockEntity.canBuild() && blockEntity.powerConsumed != 0) {
            blockEntity.powerConsumed = 0;
            stateChanged = true;
        }

        boolean nowActive = blockEntity.isBuilding();
        if (nowActive != blockEntity.active) {
            blockEntity.active = nowActive;
            blockEntity.updateActiveBlockState(nowActive);
            stateChanged = true;
        }

        if (stateChanged) {
            blockEntity.setChanged();
        }
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public int getBuildType() {
        return this.buildType;
    }

    public void setBuildType(int buildType) {
        int clamped = Math.max(0, Math.min(buildType, 4));
        this.buildType = clamped;
        if (clamped == 3 || clamped == 4) {
            this.buildRecord = getCurrentMaterialAmount();
        }
        setChanged();
    }

    public int getPowerConsumed() {
        return this.powerConsumed;
    }

    public int getPowerRemained() {
        return this.powerRemained;
    }

    public int getPowerGoal() {
        return this.powerGoal;
    }

    public boolean hasRemainedPower() {
        return this.powerRemained > BUILD_SPEED;
    }

    public int getPowerRemainingScaled(int scale) {
        if (scale <= 0) {
            return 0;
        }
        return this.powerRemained * scale / POWER_MAX;
    }

    public int getRemainingTimeSeconds() {
        if (this.powerGoal <= 0 || this.powerConsumed >= this.powerGoal) {
            return 0;
        }
        return (int) (((double) (this.powerGoal - this.powerConsumed) / BUILD_SPEED) * 0.05D);
    }

    public String getBuildTimeString() {
        int sec = getRemainingTimeSeconds();
        int hours = sec / 3600;
        int minutes = (sec % 3600) / 60;
        int seconds = sec % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public int[] getBuildRecord() {
        if (this.buildRecord == null || this.buildRecord.length < 4) {
            this.buildRecord = new int[]{0, 0, 0, 0};
        }
        return this.buildRecord;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", this.inventory.serializeNBT(registries));
        tag.putInt("PowerConsumed", this.powerConsumed);
        tag.putInt("PowerRemained", this.powerRemained);
        tag.putInt("PowerGoal", this.powerGoal);
        tag.putInt("BuildType", this.buildType);
        tag.putIntArray("BuildRecord", getBuildRecord());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory")) {
            this.inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        }
        this.powerConsumed = tag.getInt("PowerConsumed");
        this.powerRemained = tag.getInt("PowerRemained");
        this.powerGoal = tag.getInt("PowerGoal");
        this.buildType = tag.getInt("BuildType");
        int[] loaded = tag.getIntArray("BuildRecord");
        if (loaded.length >= 4) {
            this.buildRecord = new int[]{loaded[0], loaded[1], loaded[2], loaded[3]};
        } else {
            this.buildRecord = new int[]{0, 0, 0, 0};
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("tile.shincolle:BlockSmallShipyard.name");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new SmallShipyardMenu(containerId, playerInventory, this);
    }

    private boolean consumeFuel() {
        if (this.powerRemained >= POWER_MAX) {
            return false;
        }

        ItemStack fuel = this.inventory.getStackInSlot(SLOT_FUEL);
        if (fuel.isEmpty()) {
            return false;
        }

        int fuelPower = ShipyardRecipes.getFuelValue(fuel);
        if (fuelPower <= 0 || this.powerRemained + fuelPower > POWER_MAX) {
            return false;
        }

        this.inventory.setStackInSlot(SLOT_FUEL, ShipyardRecipes.consumeOneFuel(fuel));

        this.powerRemained += fuelPower;
        return true;
    }

    private void updatePowerGoal() {
        if (this.buildType == 0) {
            this.powerGoal = 0;
            return;
        }

        if (this.buildType == 3 || this.buildType == 4) {
            this.powerGoal = ShipyardRecipes.canSmallBuild(getBuildRecord())
                    ? ShipyardRecipes.calcSmallGoalPower(getBuildRecord())
                    : 0;
            return;
        }

        this.powerGoal = ShipyardRecipes.calcSmallGoalPower(getCurrentMaterialAmount());
    }

    private boolean isBuilding() {
        return hasRemainedPower() && canBuild();
    }

    private boolean canBuild() {
        if (this.powerGoal <= 0) {
            return false;
        }
        if (!this.inventory.getStackInSlot(SLOT_OUTPUT).isEmpty()) {
            return false;
        }
        if (this.buildType == 3 || this.buildType == 4) {
            return ShipyardRecipes.canSmallBuild(getBuildRecord()) && canConsumeMaterials(getBuildRecord());
        }
        return true;
    }

    private void finishBuild() {
        int[] mats;
        if (this.buildType == 3 || this.buildType == 4) {
            mats = getBuildRecord().clone();
        } else {
            mats = getCurrentMaterialAmount();
        }

        if (!canConsumeMaterials(mats)) {
            this.powerConsumed = 0;
            return;
        }

        consumeMaterials(mats);

        ItemStack result = switch (this.buildType) {
            case 2, 4 -> ShipyardRecipes.createSmallEquipResult(mats);
            default -> ShipyardRecipes.createSmallShipResult(mats);
        };
        this.inventory.setStackInSlot(SLOT_OUTPUT, result);

        this.powerConsumed = 0;
        this.powerGoal = 0;
        if (this.buildType < 3) {
            this.buildType = 0;
        }
    }

    private int[] getCurrentMaterialAmount() {
        ItemStack[] mats = new ItemStack[]{
                this.inventory.getStackInSlot(SLOT_GRUDGE),
                this.inventory.getStackInSlot(SLOT_ABYSSIUM),
                this.inventory.getStackInSlot(SLOT_AMMO),
                this.inventory.getStackInSlot(SLOT_POLYMETAL)
        };
        return ShipyardRecipes.getCurrentSmallMaterialAmount(mats);
    }

    private boolean canConsumeMaterials(int[] mats) {
        for (int i = 0; i < 4; i++) {
            if (this.inventory.getStackInSlot(i).getCount() < mats[i]) {
                return false;
            }
        }
        return true;
    }

    private void consumeMaterials(int[] mats) {
        for (int i = 0; i < 4; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }
            stack.shrink(mats[i]);
            this.inventory.setStackInSlot(i, stack);
        }
    }

    private void updateActiveBlockState(boolean nowActive) {
        if (this.level == null) {
            return;
        }

        BlockState state = getBlockState();
        if (!state.hasProperty(SmallShipyardBlock.ACTIVE)) {
            return;
        }

        if (state.getValue(SmallShipyardBlock.ACTIVE) != nowActive) {
            this.level.setBlock(this.worldPosition, state.setValue(SmallShipyardBlock.ACTIVE, nowActive), 3);
        }
    }
}
