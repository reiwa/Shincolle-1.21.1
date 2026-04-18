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
import org.trp.shincolle.block.entity.SmallShipyardBlockEntity;
import org.trp.shincolle.crafting.ShipyardRecipes;
import org.trp.shincolle.init.ModItems;

public class SmallShipyardMenu extends AbstractContainerMenu {
    public static final int BUTTON_SHIP = 0;
    public static final int BUTTON_EQUIP = 1;

    private static final int TILE_SLOT_COUNT = 6;
    private static final int TILE_SLOT_START = 0;
    private static final int TILE_SLOT_END = TILE_SLOT_START + TILE_SLOT_COUNT;

    private static final int SLOT_GRUDGE = 0;
    private static final int SLOT_ABYSSIUM = 1;
    private static final int SLOT_AMMO = 2;
    private static final int SLOT_POLYMETAL = 3;
    private static final int SLOT_FUEL = 4;
    private static final int SLOT_OUTPUT = 5;

    private static final int PLAYER_INV_START = TILE_SLOT_END;
    private static final int PLAYER_INV_END = PLAYER_INV_START + 27;
    private static final int HOTBAR_START = PLAYER_INV_END;
    private static final int HOTBAR_END = HOTBAR_START + 9;

    private final SmallShipyardBlockEntity blockEntity;
    private final boolean clientSide;

    private int buildTypeSynced;
    private int powerScaleSynced;
    private int hasMaterialSynced;
    private int hasPowerSynced;
    private int remainingSecondsSynced;
    private int powerRemainedSynced;
    private int powerRemainedLowSynced;
    private int powerRemainedHighSynced;

    public SmallShipyardMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf buffer) {
        this(containerId, playerInventory, getBlockEntity(playerInventory, buffer));
    }

    public SmallShipyardMenu(int containerId, Inventory playerInventory, SmallShipyardBlockEntity blockEntity) {
        super(ModMenus.SMALL_SHIPYARD_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.clientSide = playerInventory.player.level().isClientSide;

        this.buildTypeSynced = blockEntity.getBuildType();
        this.powerScaleSynced = blockEntity.getPowerRemainingScaled(31);
        this.hasMaterialSynced = blockEntity.getPowerGoal() > 0 ? 1 : 0;
        this.hasPowerSynced = blockEntity.hasRemainedPower() ? 1 : 0;
        this.remainingSecondsSynced = blockEntity.getRemainingTimeSeconds();
        this.powerRemainedSynced = blockEntity.getPowerRemained();
        this.powerRemainedLowSynced = this.powerRemainedSynced & 0xFFFF;
        this.powerRemainedHighSynced = (this.powerRemainedSynced >>> 16) & 0xFFFF;

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return SmallShipyardMenu.this.blockEntity.getBuildType();
            }

            @Override
            public void set(int value) {
                SmallShipyardMenu.this.buildTypeSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return SmallShipyardMenu.this.blockEntity.getPowerRemainingScaled(31);
            }

            @Override
            public void set(int value) {
                SmallShipyardMenu.this.powerScaleSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return SmallShipyardMenu.this.blockEntity.getPowerGoal() > 0 ? 1 : 0;
            }

            @Override
            public void set(int value) {
                SmallShipyardMenu.this.hasMaterialSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return SmallShipyardMenu.this.blockEntity.hasRemainedPower() ? 1 : 0;
            }

            @Override
            public void set(int value) {
                SmallShipyardMenu.this.hasPowerSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return Math.min(Short.MAX_VALUE, SmallShipyardMenu.this.blockEntity.getRemainingTimeSeconds());
            }

            @Override
            public void set(int value) {
                SmallShipyardMenu.this.remainingSecondsSynced = value;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return SmallShipyardMenu.this.blockEntity.getPowerRemained() & 0xFFFF;
            }

            @Override
            public void set(int value) {
                SmallShipyardMenu.this.powerRemainedLowSynced = value & 0xFFFF;
                SmallShipyardMenu.this.powerRemainedSynced = combineShortParts(
                        SmallShipyardMenu.this.powerRemainedLowSynced,
                        SmallShipyardMenu.this.powerRemainedHighSynced);
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (SmallShipyardMenu.this.blockEntity.getPowerRemained() >>> 16) & 0xFFFF;
            }

            @Override
            public void set(int value) {
                SmallShipyardMenu.this.powerRemainedHighSynced = value & 0xFFFF;
                SmallShipyardMenu.this.powerRemainedSynced = combineShortParts(
                        SmallShipyardMenu.this.powerRemainedLowSynced,
                        SmallShipyardMenu.this.powerRemainedHighSynced);
            }
        });

        this.addSlot(new MaterialSlot(SLOT_GRUDGE, 33, 29));
        this.addSlot(new MaterialSlot(SLOT_ABYSSIUM, 53, 29));
        this.addSlot(new MaterialSlot(SLOT_AMMO, 73, 29));
        this.addSlot(new MaterialSlot(SLOT_POLYMETAL, 93, 29));
        this.addSlot(new MaterialSlot(SLOT_FUEL, 8, 53));
        this.addSlot(new OutputSlot(SLOT_OUTPUT, 134, 44));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int index = col + row * 9 + 9;
                this.addSlot(new Slot(playerInventory, index, 8 + col * 18, 87 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 145));
        }
    }

    public int getBuildType() {
        return this.clientSide ? this.buildTypeSynced : this.blockEntity.getBuildType();
    }

    public int getPowerScale() {
        return this.clientSide ? this.powerScaleSynced : this.blockEntity.getPowerRemainingScaled(31);
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

            if (index == SLOT_OUTPUT) {
                if (!this.moveItemStackTo(stack, PLAYER_INV_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, copied);
            } else if (index < TILE_SLOT_END) {
                if (!this.moveItemStackTo(stack, PLAYER_INV_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                int type = ShipyardRecipes.getSmallMaterialType(stack);
                if (type >= 0 && type <= 3) {
                    if (!this.moveItemStackTo(stack, type, type + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (type == 4) {
                    if (!this.moveItemStackTo(stack, SLOT_FUEL, SLOT_FUEL + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= PLAYER_INV_START && index < PLAYER_INV_END) {
                    if (!this.moveItemStackTo(stack, HOTBAR_START, HOTBAR_END, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= HOTBAR_START && index < HOTBAR_END) {
                    if (!this.moveItemStackTo(stack, PLAYER_INV_START, PLAYER_INV_END, false)) {
                        return ItemStack.EMPTY;
                    }
                }
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

        int current = this.blockEntity.getBuildType();
        int next;
        if (id == BUTTON_SHIP) {
            next = current == 1 ? 3 : (current == 3 ? 0 : 1);
            this.blockEntity.setBuildType(next);
            return true;
        }
        if (id == BUTTON_EQUIP) {
            next = current == 2 ? 4 : (current == 4 ? 0 : 2);
            this.blockEntity.setBuildType(next);
            return true;
        }

        return super.clickMenuButton(player, id);
    }

    private static SmallShipyardBlockEntity getBlockEntity(Inventory playerInventory, RegistryFriendlyByteBuf buffer) {
        if (buffer == null) {
            throw new IllegalStateException("Missing small shipyard menu data.");
        }

        BlockPos pos = buffer.readBlockPos();
        if (playerInventory.player.level().getBlockEntity(pos) instanceof SmallShipyardBlockEntity shipyard) {
            return shipyard;
        }

        throw new IllegalStateException("Small shipyard block entity not found.");
    }

    private static int combineShortParts(int low, int high) {
        return ((high & 0xFFFF) << 16) | (low & 0xFFFF);
    }

    private final class MaterialSlot extends SlotItemHandler {
        private MaterialSlot(int slot, int x, int y) {
            super(SmallShipyardMenu.this.blockEntity.getInventory(), slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return switch (this.getSlotIndex()) {
                case SLOT_GRUDGE -> stack.is(ModItems.GRUDGE.get());
                case SLOT_ABYSSIUM -> stack.is(ModItems.ABYSS_METAL.get());
                case SLOT_AMMO -> stack.is(ModItems.AMMO_LIGHT.get())
                        || stack.is(ModItems.AMMO_LIGHT_CONTAINER.get())
                        || stack.is(ModItems.AMMO_HEAVY.get())
                        || stack.is(ModItems.AMMO_HEAVY_CONTAINER.get());
                case SLOT_POLYMETAL -> stack.is(ModItems.ABYSS_POLYMETAL.get());
                case SLOT_FUEL -> ShipyardRecipes.isFuel(stack);
                default -> false;
            };
        }
    }

    private final class OutputSlot extends SlotItemHandler {
        private OutputSlot(int slot, int x, int y) {
            super(SmallShipyardMenu.this.blockEntity.getInventory(), slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
