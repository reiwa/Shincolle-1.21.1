package org.trp.shincolle.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.trp.shincolle.block.entity.LargeShipyardBlockEntity;
import org.trp.shincolle.init.ModBlocks;

public class GrudgeHeavyBlock extends BaseEntityBlock {

    private static final MapCodec<GrudgeHeavyBlock> CODEC = simpleCodec(GrudgeHeavyBlock::new);

    private static final byte TYPE_OTHER = -1;
    private static final byte TYPE_POLYMETAL = 1;
    private static final byte TYPE_GRUDGE_HEAVY = 2;

    private static final byte[][][] LARGE_SHIPYARD_PATTERN = new byte[][][]{
        {
            {TYPE_POLYMETAL, TYPE_POLYMETAL, TYPE_POLYMETAL},
            {TYPE_POLYMETAL, TYPE_POLYMETAL, TYPE_POLYMETAL},
            {TYPE_POLYMETAL, TYPE_POLYMETAL, TYPE_POLYMETAL}
        },
        {
            {TYPE_POLYMETAL, TYPE_OTHER, TYPE_POLYMETAL},
            {TYPE_OTHER, TYPE_OTHER, TYPE_OTHER},
            {TYPE_POLYMETAL, TYPE_OTHER, TYPE_POLYMETAL}
        },
        {
            {TYPE_OTHER, TYPE_OTHER, TYPE_OTHER},
            {TYPE_OTHER, TYPE_GRUDGE_HEAVY, TYPE_OTHER},
            {TYPE_OTHER, TYPE_OTHER, TYPE_OTHER}
        }
    };

    public GrudgeHeavyBlock(Properties properties) {
        super(properties);
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
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData == null) {
                customData = stack.get(DataComponents.BLOCK_ENTITY_DATA);
            }
            if (customData != null) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof LargeShipyardBlockEntity shipyard) {
                    CompoundTag tag = customData.copyTag();
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
                ItemStack stack = new ItemStack(ModBlocks.GRUDGE_HEAVY_BLOCK.get());
                CompoundTag tag = new CompoundTag();
                int[] mats = shipyard.getMatsStock().clone();
                for (int i = 0; i < 4; i++) {
                    mats[i] += shipyard.getMatBuild(i);
                }
                tag.putIntArray("MatsStock", mats);
                tag.putInt("PowerRemained", shipyard.getPowerRemained());
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

                net.minecraft.world.entity.item.ItemEntity itemEntity = new net.minecraft.world.entity.item.ItemEntity(
                        level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        if (!isLargeShipyardPattern(level, pos)) {
            return InteractionResult.PASS;
        }

        BlockEntity oldBe = level.getBlockEntity(pos);
        int[] savedStock = (oldBe instanceof LargeShipyardBlockEntity s) ? s.getMatsStock().clone() : new int[4];
        int savedFuel = (oldBe instanceof LargeShipyardBlockEntity s) ? s.getPowerRemained() : 0;

        setLargeShipyardSupportFormed(level, pos, true);

        BlockState activatedState = ModBlocks.LARGE_SHIPYARD.get().defaultBlockState()
                .setValue(LargeShipyardBlock.FACING, player.getDirection().getOpposite())
                .setValue(LargeShipyardBlock.ACTIVE, false);
        level.setBlock(pos, activatedState, Block.UPDATE_ALL);

        BlockEntity newBe = level.getBlockEntity(pos);
        if (newBe instanceof LargeShipyardBlockEntity shipyard) {
            shipyard.setMatsStock(savedStock);
            shipyard.setPowerRemained(savedFuel);
        }

        level.playSound(null, pos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 0.7F,
                0.9F + level.random.nextFloat() * 0.2F);
        return InteractionResult.CONSUME;
    }

    public static boolean hasLargeShipyardSupport(Level level, BlockPos center) {
        if (center.getY() - 2 < level.getMinBuildHeight()) {
            return false;
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (!level.getBlockState(center.offset(x, -2, z)).is(ModBlocks.POLYMETAL.get())) {
                    return false;
                }
            }
        }

        if (!level.getBlockState(center.offset(1, -1, 1)).is(ModBlocks.POLYMETAL.get())) {
            return false;
        }
        if (!level.getBlockState(center.offset(1, -1, -1)).is(ModBlocks.POLYMETAL.get())) {
            return false;
        }
        if (!level.getBlockState(center.offset(-1, -1, 1)).is(ModBlocks.POLYMETAL.get())) {
            return false;
        }
        if (!level.getBlockState(center.offset(-1, -1, -1)).is(ModBlocks.POLYMETAL.get())) {
            return false;
        }

        return true;
    }

    public static void setLargeShipyardSupportFormed(Level level, BlockPos center, boolean formed) {
        setSupportPolymetalFormed(level, center.offset(1, -1, 1), formed);
        setSupportPolymetalFormed(level, center.offset(1, -1, -1), formed);
        setSupportPolymetalFormed(level, center.offset(-1, -1, 1), formed);
        setSupportPolymetalFormed(level, center.offset(-1, -1, -1), formed);

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                setSupportPolymetalFormed(level, center.offset(x, -2, z), formed);
            }
        }
    }

    private static boolean isLargeShipyardPattern(Level level, BlockPos center) {
        if (center.getY() - 2 < level.getMinBuildHeight()) {
            return false;
        }

        if (!hasLargeShipyardSupport(level, center)) {
            return false;
        }

        for (int dy = -1; dy <= 0; dy++) {
            byte[][] layer = LARGE_SHIPYARD_PATTERN[dy + 2];
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    byte expected = layer[dx + 1][dz + 1];
                    byte actual = getPatternType(level.getBlockState(center.offset(dx, dy, dz)));

                    if (actual != expected) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void setSupportPolymetalFormed(Level level, BlockPos pos, boolean formed) {
        BlockState state = level.getBlockState(pos);
        if (!state.is(ModBlocks.POLYMETAL.get()) || !state.hasProperty(PolymetalBlock.FORMED)) {
            return;
        }

        if (state.getValue(PolymetalBlock.FORMED) != formed) {
            level.setBlock(pos, state.setValue(PolymetalBlock.FORMED, formed), Block.UPDATE_ALL);
        }
    }

    private static byte getPatternType(BlockState state) {
        if (state.is(ModBlocks.POLYMETAL.get())) {
            return TYPE_POLYMETAL;
        }
        if (state.is(ModBlocks.GRUDGE_HEAVY_BLOCK.get())) {
            return TYPE_GRUDGE_HEAVY;
        }
        return TYPE_OTHER;
    }
}
