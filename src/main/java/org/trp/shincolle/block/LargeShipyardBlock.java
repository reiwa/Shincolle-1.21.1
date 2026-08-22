package org.trp.shincolle.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.trp.shincolle.block.entity.LargeShipyardBlockEntity;
import org.trp.shincolle.init.ModBlockEntities;

public class LargeShipyardBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    private static final MapCodec<LargeShipyardBlock> CODEC = simpleCodec(properties -> new LargeShipyardBlock());

    public LargeShipyardBlock() {
        super(Block.Properties.of()
                .strength(1.5F)
                .lightLevel(state -> state.getValue(ACTIVE) ? 12 : 4)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ACTIVE, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LargeShipyardBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(ACTIVE, false);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof LargeShipyardBlockEntity shipyard && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(shipyard, buffer -> buffer.writeBlockPos(pos));
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, net.minecraft.world.entity.LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            net.minecraft.world.item.component.CustomData customData = stack.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
            if (customData == null) {
                customData = stack.get(net.minecraft.core.component.DataComponents.BLOCK_ENTITY_DATA);
            }
            if (customData != null) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof LargeShipyardBlockEntity shipyard) {
                    net.minecraft.nbt.CompoundTag tag = customData.copyTag();
                    if (tag.contains("MatsStock")) {
                        shipyard.setMatsStock(tag.getIntArray("MatsStock"));
                    }
                    if (tag.contains("PowerRemained")) {
                        shipyard.setPowerRemained(tag.getInt("PowerRemained"));
                    }
                }
            }
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof LargeShipyardBlockEntity shipyard) {
            if (!level.isClientSide && player.isCreative()) {
                ItemStack stack = new ItemStack(org.trp.shincolle.init.ModBlocks.GRUDGE_HEAVY_BLOCK.get());
                net.minecraft.nbt.CompoundTag tag = new net.minecraft.nbt.CompoundTag();
                int[] mats = shipyard.getMatsStock().clone();
                for (int i = 0; i < 4; i++) {
                    mats[i] += shipyard.getMatBuild(i);
                }
                tag.putIntArray("MatsStock", mats);
                tag.putInt("PowerRemained", shipyard.getPowerRemained());
                stack.set(net.minecraft.core.component.DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.of(tag));

                net.minecraft.world.entity.item.ItemEntity itemEntity = new net.minecraft.world.entity.item.ItemEntity(
                        level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            GrudgeHeavyBlock.setLargeShipyardSupportFormed(level, pos, false);
            if (!level.isClientSide) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof LargeShipyardBlockEntity shipyard && !newState.is(org.trp.shincolle.init.ModBlocks.GRUDGE_HEAVY_BLOCK.get())) {
                    for (int i = 0; i < LargeShipyardBlockEntity.SLOT_COUNT; i++) {
                        ItemStack itemStack = shipyard.getInventory().getStackInSlot(i);
                        if (!itemStack.isEmpty()) {
                            popResource(level, pos, itemStack.copy());
                            shipyard.getInventory().setStackInSlot(i, ItemStack.EMPTY);
                        }
                    }
                }
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return null;
        }
        return createTickerHelper(blockEntityType, ModBlockEntities.LARGE_SHIPYARD.get(), LargeShipyardBlockEntity::serverTick);
    }
}
