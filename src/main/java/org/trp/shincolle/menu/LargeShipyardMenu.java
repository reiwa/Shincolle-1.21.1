package org.trp.shincolle.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.trp.shincolle.block.entity.LargeShipyardBlockEntity;

public class LargeShipyardMenu extends AbstractContainerMenu {
    public static final int BUTTON_BUILD_SHIP = 0;
    public static final int BUTTON_BUILD_EQUIP = 1;
    public static final int BUTTON_TOGGLE_INV_MODE = 2;

    public static final int BUTTON_SELECT_MAT_0_A = 3;
    public static final int BUTTON_SELECT_MAT_1_A = 4;
    public static final int BUTTON_SELECT_MAT_2_A = 5;
    public static final int BUTTON_SELECT_MAT_3_A = 6;

    public static final int BUTTON_SELECT_MAT_0_B = 7;
    public static final int BUTTON_SELECT_MAT_1_B = 8;
    public static final int BUTTON_SELECT_MAT_2_B = 9;
    public static final int BUTTON_SELECT_MAT_3_B = 10;

    public static final int BUTTON_MAT_AMOUNT_BASE = 20;

    private static final int TILE_SLOT_COUNT = 10;
    private static final int TILE_SLOT_START = 0;
    private static final int TILE_SLOT_END = TILE_SLOT_START + TILE_SLOT_COUNT;

    private static final int SLOT_OUTPUT = 0;
    private static final int SLOT_IO_START = 1;
    private static final int SLOT_IO_END = 9;

    private static final int PLAYER_INV_START = TILE_SLOT_END;
    private static final int PLAYER_INV_END = PLAYER_INV_START + 27;
    private static final int HOTBAR_START = PLAYER_INV_END;
    private static final int HOTBAR_END = HOTBAR_START + 9;

    private final LargeShipyardBlockEntity blockEntity;
    private final boolean clientSide;

    private int buildTypeSynced;
    private int selectMatSynced;
    private int invModeSynced;
    private final int[] matBuildSynced = new int[]{0, 0, 0, 0};
    private final int[] matStockSynced = new int[]{0, 0, 0, 0};
    private int powerScaleSynced;
    private int hasMaterialSynced;
    private int hasPowerSynced;
    private int remainingSecondsSynced;
    private int powerRemainedSynced;
    private int powerRemainedLowSynced;
    private int powerRemainedHighSynced;

    public LargeShipyardMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf buffer) {
        this(containerId, playerInventory, getBlockEntity(playerInventory, buffer));
    }

    public LargeShipyardMenu(int containerId, Inventory playerInventory, LargeShipyardBlockEntity blockEntity) {
        super(ModMenus.LARGE_SHIPYARD_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.clientSide = playerInventory.player.level().isClientSide;

        this.buildTypeSynced = blockEntity.getBuildType();
        this.selectMatSynced = blockEntity.getSelectMat();
        this.invModeSynced = blockEntity.getInvMode();
        for (int i = 0; i < 4; i++) {
            this.matBuildSynced[i] = blockEntity.getMatBuild(i);
            this.matStockSynced[i] = Math.min(Short.MAX_VALUE, blockEntity.getMatStock(i));
        }
        this.powerScaleSynced = blockEntity.getPowerRemainingScaled(64);
        this.hasMaterialSynced = blockEntity.getPowerGoal() > 0 ? 1 : 0;
        this.hasPowerSynced = blockEntity.hasRemainedPower() ? 1 : 0;
        this.remainingSecondsSynced = blockEntity.getRemainingTimeSeconds();
        this.powerRemainedSynced = blockEntity.getPowerRemained();
        this.powerRemainedLowSynced = this.powerRemainedSynced & 0xFFFF;
        this.powerRemainedHighSynced = (this.powerRemainedSynced >>> 16) & 0xFFFF;

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return LargeShipyardMenu.this.blockEntity.getBuildType();
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.buildTypeSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return LargeShipyardMenu.this.blockEntity.getSelectMat();
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.selectMatSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return LargeShipyardMenu.this.blockEntity.getInvMode();
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.invModeSynced = value;
            }
        });

        for (int i = 0; i < 4; i++) {
            final int idx = i;
            this.addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return LargeShipyardMenu.this.blockEntity.getMatBuild(idx);
                }

                @Override
                public void set(int value) {
                    LargeShipyardMenu.this.matBuildSynced[idx] = value;
                }
            });
        }

        for (int i = 0; i < 4; i++) {
            final int idx = i;
            this.addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return Math.min(Short.MAX_VALUE, LargeShipyardMenu.this.blockEntity.getMatStock(idx));
                }

                @Override
                public void set(int value) {
                    LargeShipyardMenu.this.matStockSynced[idx] = value;
                }
            });
        }

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return LargeShipyardMenu.this.blockEntity.getPowerRemainingScaled(64);
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.powerScaleSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return LargeShipyardMenu.this.blockEntity.getPowerGoal() > 0 ? 1 : 0;
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.hasMaterialSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return LargeShipyardMenu.this.blockEntity.hasRemainedPower() ? 1 : 0;
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.hasPowerSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return Math.min(Short.MAX_VALUE, LargeShipyardMenu.this.blockEntity.getRemainingTimeSeconds());
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.remainingSecondsSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return LargeShipyardMenu.this.blockEntity.getPowerRemained() & 0xFFFF;
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.powerRemainedLowSynced = value & 0xFFFF;
                LargeShipyardMenu.this.powerRemainedSynced = combineShortParts(
                        LargeShipyardMenu.this.powerRemainedLowSynced,
                        LargeShipyardMenu.this.powerRemainedHighSynced);
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (LargeShipyardMenu.this.blockEntity.getPowerRemained() >>> 16) & 0xFFFF;
            }

            @Override
            public void set(int value) {
                LargeShipyardMenu.this.powerRemainedHighSynced = value & 0xFFFF;
                LargeShipyardMenu.this.powerRemainedSynced = combineShortParts(
                        LargeShipyardMenu.this.powerRemainedLowSynced,
                        LargeShipyardMenu.this.powerRemainedHighSynced);
            }
        });

        this.addSlot(new OutputSlot(SLOT_OUTPUT, 168, 51));
        for (int i = SLOT_IO_START; i <= SLOT_IO_END; i++) {
            this.addSlot(new InputSlot(i, 7 + i * 18, 116));
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int index = col + row * 9 + 9;
                this.addSlot(new Slot(playerInventory, index, 25 + col * 18, 141 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 25 + col * 18, 199));
        }
    }

    public int getBuildType() {
        return this.clientSide ? this.buildTypeSynced : this.blockEntity.getBuildType();
    }

    public int getSelectMat() {
        return this.clientSide ? this.selectMatSynced : this.blockEntity.getSelectMat();
    }

    public int getInvMode() {
        return this.clientSide ? this.invModeSynced : this.blockEntity.getInvMode();
    }

    public int getMatBuild(int index) {
        return this.clientSide ? this.matBuildSynced[index] : this.blockEntity.getMatBuild(index);
    }

    public int getMatStock(int index) {
        return this.clientSide ? this.matStockSynced[index] : this.blockEntity.getMatStock(index);
    }

    public int getPowerScale() {
        return this.clientSide ? this.powerScaleSynced : this.blockEntity.getPowerRemainingScaled(64);
    }

    public boolean hasMaterial() {
        return (this.clientSide ? this.hasMaterialSynced : (this.blockEntity.getPowerGoal() > 0 ? 1 : 0)) != 0;
    }

    public boolean hasPower() {
        return (this.clientSide ? this.hasPowerSynced : (this.blockEntity.hasRemainedPower() ? 1 : 0)) != 0;
    }

    public int getPowerRemained() {
        return this.clientSide ? this.powerRemainedSynced : this.blockEntity.getPowerRemained();
    }

    public String getBuildTimeString() {
        int seconds = this.clientSide ? this.remainingSecondsSynced : this.blockEntity.getRemainingTimeSeconds();
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    @Override
    public boolean stillValid(Player player) {
        if (player.level().getBlockEntity(this.blockEntity.getBlockPos()) != this.blockEntity) {
            return false;
        }
        double x = this.blockEntity.getBlockPos().getX() + 0.5D;
        double y = this.blockEntity.getBlockPos().getY() + 0.5D;
        double z = this.blockEntity.getBlockPos().getZ() + 0.5D;
        return player.distanceToSqr(x, y, z) <= 64.0D;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copied = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copied = stack.copy();
            boolean success;

            if (index == SLOT_OUTPUT) {
                success = this.moveItemStackTo(stack, SLOT_IO_START, HOTBAR_END, true);
            } else if (index >= HOTBAR_START) {
                success = this.moveItemStackTo(stack, SLOT_IO_START, PLAYER_INV_END, false);
            } else if (index >= PLAYER_INV_START && index < PLAYER_INV_END) {
                success = this.moveItemStackTo(stack, SLOT_IO_START, TILE_SLOT_END, true);
            } else {
                success = this.moveItemStackTo(stack, PLAYER_INV_START, HOTBAR_END, false);
            }

            if (!success) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == copied.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }
        return copied;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (player.level().isClientSide) {
            return true;
        }

        int buildType = this.blockEntity.getBuildType();
        if (id == BUTTON_BUILD_SHIP) {
            int next = buildType == 1 ? 3 : (buildType == 3 ? 0 : 1);
            this.blockEntity.setBuildType(next);
            this.broadcastFullState();
            return true;
        }
        if (id == BUTTON_BUILD_EQUIP) {
            int next = buildType == 2 ? 4 : (buildType == 4 ? 0 : 2);
            this.blockEntity.setBuildType(next);
            this.broadcastFullState();
            return true;
        }
        if (id == BUTTON_TOGGLE_INV_MODE) {
            this.blockEntity.setInvMode(this.blockEntity.getInvMode() == 0 ? 1 : 0);
            this.broadcastFullState();
            return true;
        }

        if (id >= BUTTON_SELECT_MAT_0_A && id <= BUTTON_SELECT_MAT_3_A) {
            this.blockEntity.setSelectMat(id - BUTTON_SELECT_MAT_0_A);
            this.broadcastFullState();
            return true;
        }
        if (id >= BUTTON_SELECT_MAT_0_B && id <= BUTTON_SELECT_MAT_3_B) {
            this.blockEntity.setSelectMat(id - BUTTON_SELECT_MAT_0_B);
            this.broadcastFullState();
            return true;
        }

        if (id >= BUTTON_MAT_AMOUNT_BASE && id < BUTTON_MAT_AMOUNT_BASE + 8) {
            this.blockEntity.moveBuildMaterialAmount(this.blockEntity.getSelectMat(), id - BUTTON_MAT_AMOUNT_BASE);
            this.broadcastFullState();
            return true;
        }

        return super.clickMenuButton(player, id);
    }

    private static LargeShipyardBlockEntity getBlockEntity(Inventory playerInventory, RegistryFriendlyByteBuf buffer) {
        if (buffer == null) {
            throw new IllegalStateException("Missing large shipyard menu data.");
        }

        BlockPos pos = buffer.readBlockPos();
        if (playerInventory.player.level().getBlockEntity(pos) instanceof LargeShipyardBlockEntity shipyard) {
            return shipyard;
        }

        throw new IllegalStateException("Large shipyard block entity not found.");
    }

    private static int combineShortParts(int low, int high) {
        return ((high & 0xFFFF) << 16) | (low & 0xFFFF);
    }

    private final class InputSlot extends SlotItemHandler {
        private InputSlot(int slot, int x, int y) {
            super(LargeShipyardMenu.this.blockEntity.getInventory(), slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return this.getSlotIndex() != SLOT_OUTPUT;
        }
    }

    private final class OutputSlot extends SlotItemHandler {
        private OutputSlot(int slot, int x, int y) {
            super(LargeShipyardMenu.this.blockEntity.getInventory(), slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
