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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.trp.shincolle.block.GrudgeHeavyBlock;
import org.trp.shincolle.block.LargeShipyardBlock;
import org.trp.shincolle.crafting.ShipyardRecipes;
import org.trp.shincolle.init.ModBlockEntities;
import org.trp.shincolle.init.ModBlocks;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.menu.LargeShipyardMenu;

public class LargeShipyardBlockEntity extends BlockEntity implements MenuProvider {
    public static final int SLOT_OUTPUT = 0;
    public static final int SLOT_IO_START = 1;
    public static final int SLOT_IO_END = 9;
    public static final int SLOT_COUNT = 10;

    public static final int POWER_MAX = 1382400;
    public static final int BUILD_SPEED = 48;
    public static final int INSTANT_BUILD_BONUS = BUILD_SPEED * 1200;

    private static final int MAT_COUNT = 4;

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
    private int invMode;
    private int selectMat;
    private int[] matsBuild = new int[]{0, 0, 0, 0};
    private int[] matsStock = new int[]{0, 0, 0, 0};
    private boolean active;
    private float renderYaw;
    private float renderPitch;
    private boolean renderAnglesInitialized;

    public LargeShipyardBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LARGE_SHIPYARD.get(), pos, blockState);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, LargeShipyardBlockEntity blockEntity) {
        if (level.isClientSide) {
            return;
        }

        if (!GrudgeHeavyBlock.hasLargeShipyardSupport(level, pos)) {
            blockEntity.collapseStructure();
            return;
        }

        boolean stateChanged = false;

        int oldGoal = blockEntity.powerGoal;
        blockEntity.powerGoal = blockEntity.buildType == 0 ? 0 : ShipyardRecipes.calcLargeGoalPower(blockEntity.matsBuild);
        if (oldGoal != blockEntity.powerGoal) {
            stateChanged = true;
        }

        if (blockEntity.consumeFuel()) {
            stateChanged = true;
        }

        if (blockEntity.handleMaterials()) {
            stateChanged = true;
        }

        if (blockEntity.isBuilding()) {
            blockEntity.powerRemained -= BUILD_SPEED;
            blockEntity.powerConsumed += BUILD_SPEED;
            stateChanged = true;

            if (blockEntity.consumeInstantConstructionMaterial()) {
                stateChanged = true;
            }

            if (blockEntity.powerConsumed >= blockEntity.powerGoal) {
                blockEntity.finishBuild();
                stateChanged = true;
            }
        } else if (blockEntity.powerConsumed != 0) {
            blockEntity.powerConsumed = 0;
            stateChanged = true;
        }

        boolean nowActive = blockEntity.isBuilding();
        if (blockEntity.active != nowActive) {
            blockEntity.active = nowActive;
            blockEntity.updateActiveBlockState(nowActive);
            stateChanged = true;
        }

        if (stateChanged) {
            blockEntity.setChanged();
        }
    }

    private void collapseStructure() {
        if (this.level == null) {
            return;
        }

        dropInventoryContents();
        GrudgeHeavyBlock.setLargeShipyardSupportFormed(this.level, this.worldPosition, false);
        this.level.setBlock(this.worldPosition, ModBlocks.GRUDGE_HEAVY_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
    }

    private void dropInventoryContents() {
        if (this.level == null) {
            return;
        }

        for (int i = 0; i < SLOT_COUNT; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }
            Block.popResource(this.level, this.worldPosition, stack.copy());
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
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

    public int getBuildType() {
        return this.buildType;
    }

    public void setBuildType(int buildType) {
        this.buildType = Math.max(0, Math.min(buildType, 4));
        setChanged();
    }

    public int getInvMode() {
        return this.invMode;
    }

    public void setInvMode(int invMode) {
        this.invMode = invMode <= 0 ? 0 : 1;
        setChanged();
    }

    public int getSelectMat() {
        return this.selectMat;
    }

    public void setSelectMat(int selectMat) {
        this.selectMat = Math.max(0, Math.min(selectMat, MAT_COUNT - 1));
        setChanged();
    }

    public int[] getMatsBuild() {
        return this.matsBuild;
    }

    public int[] getMatsStock() {
        return this.matsStock;
    }

    public int getMatBuild(int index) {
        return this.matsBuild[index];
    }

    public int getMatStock(int index) {
        return this.matsStock[index];
    }

    public int getPowerRemainingScaled(int scale) {
        if (scale <= 0) {
            return 0;
        }
        return this.powerRemained * scale / POWER_MAX;
    }

    public boolean hasRemainedPower() {
        return this.powerRemained > BUILD_SPEED;
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

    public void moveBuildMaterialAmount(int matType, int value) {
        ShipyardRecipes.moveBuildMaterialAmount(this.matsBuild, this.matsStock, matType, value);
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", this.inventory.serializeNBT(registries));
        tag.putInt("PowerConsumed", this.powerConsumed);
        tag.putInt("PowerRemained", this.powerRemained);
        tag.putInt("PowerGoal", this.powerGoal);
        tag.putInt("BuildType", this.buildType);
        tag.putInt("InvMode", this.invMode);
        tag.putInt("SelectMat", this.selectMat);
        tag.putIntArray("MatsBuild", this.matsBuild);
        tag.putIntArray("MatsStock", this.matsStock);
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
        this.invMode = tag.getInt("InvMode");
        this.selectMat = Math.max(0, Math.min(tag.getInt("SelectMat"), MAT_COUNT - 1));
        this.matsBuild = sanitizeMatsArray(tag.getIntArray("MatsBuild"));
        this.matsStock = sanitizeMatsArray(tag.getIntArray("MatsStock"));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("tile.shincolle:BlockLargeShipyard.name");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new LargeShipyardMenu(containerId, playerInventory, this);
    }

    public float getRenderYaw() {
        return this.renderYaw;
    }

    public float getRenderPitch() {
        return this.renderPitch;
    }

    public boolean hasRenderAngles() {
        return this.renderAnglesInitialized;
    }

    public void setRenderAngles(float yaw, float pitch) {
        this.renderYaw = yaw;
        this.renderPitch = pitch;
        this.renderAnglesInitialized = true;
    }

    private boolean consumeFuel() {
        if (this.powerRemained >= POWER_MAX) {
            return false;
        }

        for (int i = SLOT_IO_START; i <= SLOT_IO_END; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            int fuelPower = ShipyardRecipes.getFuelValue(stack);
            if (fuelPower <= 0 || this.powerRemained + fuelPower > POWER_MAX) {
                continue;
            }

            if (stack.is(Items.LAVA_BUCKET)) {
                this.inventory.setStackInSlot(i, new ItemStack(Items.BUCKET));
            } else {
                stack.shrink(1);
                this.inventory.setStackInSlot(i, stack);
            }

            this.powerRemained += fuelPower;
            return true;
        }

        return false;
    }

    private boolean handleMaterials() {
        if (this.invMode == 0) {
            for (int i = SLOT_IO_START; i <= SLOT_IO_END; i++) {
                ItemStack stack = this.inventory.getStackInSlot(i);
                if (stack.isEmpty()) {
                    continue;
                }
                if (!ShipyardRecipes.addLargeMaterialStock(this.matsStock, stack)) {
                    continue;
                }

                stack.shrink(1);
                this.inventory.setStackInSlot(i, stack);
                return true;
            }
            return false;
        }

        int compressNum = 9;
        int normalNum = 1;
        int matType = this.selectMat;

        if (this.matsStock[matType] >= compressNum && outputMaterialToSlot(matType, true)) {
            this.matsStock[matType] -= compressNum;
            return true;
        }

        if (this.matsStock[matType] >= normalNum && outputMaterialToSlot(matType, false)) {
            this.matsStock[matType] -= normalNum;
            return true;
        }

        return false;
    }

    private boolean outputMaterialToSlot(int selectMat, boolean compressed) {
        ItemStack output = ShipyardRecipes.createLargeOutputMaterial(selectMat, compressed);
        if (output.isEmpty()) {
            return false;
        }

        int slot = findFitSlot(output);
        if (slot < 0) {
            return false;
        }

        ItemStack current = this.inventory.getStackInSlot(slot);
        if (current.isEmpty()) {
            this.inventory.setStackInSlot(slot, output);
        } else {
            current.grow(output.getCount());
            this.inventory.setStackInSlot(slot, current);
        }
        return true;
    }

    private int findFitSlot(ItemStack output) {
        for (int i = SLOT_IO_START; i <= SLOT_IO_END; i++) {
            ItemStack current = this.inventory.getStackInSlot(i);
            if (current.isEmpty()) {
                return i;
            }

            if (ItemStack.isSameItemSameComponents(current, output)
                    && current.getCount() + output.getCount() <= current.getMaxStackSize()) {
                return i;
            }
        }
        return -1;
    }

    private boolean isBuilding() {
        return hasRemainedPower() && canBuild();
    }

    private boolean canBuild() {
        return this.powerGoal > 0 && this.inventory.getStackInSlot(SLOT_OUTPUT).isEmpty();
    }

    private boolean consumeInstantConstructionMaterial() {
        if (this.powerConsumed >= this.powerGoal) {
            return false;
        }

        for (int i = SLOT_IO_START; i <= SLOT_IO_END; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (stack.isEmpty() || !stack.is(ModItems.INSTANT_CON_MAT.get())) {
                continue;
            }

            stack.shrink(1);
            this.inventory.setStackInSlot(i, stack);
            this.powerConsumed += INSTANT_BUILD_BONUS;
            return true;
        }

        return false;
    }

    private void finishBuild() {
        ItemStack result = switch (this.buildType) {
            case 2, 4 -> ShipyardRecipes.createLargeEquipResult(this.matsBuild);
            default -> ShipyardRecipes.createLargeShipResult(this.matsBuild);
        };
        this.inventory.setStackInSlot(SLOT_OUTPUT, result);

        this.powerConsumed = 0;
        this.powerGoal = 0;

        if (this.buildType == 3 || this.buildType == 4) {
            setupRepeatBuild();
        } else {
            this.buildType = 0;
            this.matsBuild = new int[]{0, 0, 0, 0};
        }
    }

    private void setupRepeatBuild() {
        boolean canRepeat = true;
        for (int i = 0; i < MAT_COUNT; i++) {
            if (this.matsStock[i] < this.matsBuild[i]) {
                canRepeat = false;
                break;
            }
        }

        if (!canRepeat) {
            this.buildType = 0;
            this.matsBuild = new int[]{0, 0, 0, 0};
            return;
        }

        for (int i = 0; i < MAT_COUNT; i++) {
            this.matsStock[i] -= this.matsBuild[i];
        }
    }

    private void updateActiveBlockState(boolean nowActive) {
        if (this.level == null) {
            return;
        }

        BlockState state = getBlockState();
        if (!state.hasProperty(LargeShipyardBlock.ACTIVE)) {
            return;
        }

        if (state.getValue(LargeShipyardBlock.ACTIVE) != nowActive) {
            this.level.setBlock(this.worldPosition, state.setValue(LargeShipyardBlock.ACTIVE, nowActive), 3);
        }
    }

    private static int[] sanitizeMatsArray(int[] input) {
        if (input.length < MAT_COUNT) {
            return new int[]{0, 0, 0, 0};
        }
        return new int[]{input[0], input[1], input[2], input[3]};
    }
}
