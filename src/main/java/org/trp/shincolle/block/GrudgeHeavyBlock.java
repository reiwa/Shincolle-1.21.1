package org.trp.shincolle.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.trp.shincolle.init.ModBlocks;

public class GrudgeHeavyBlock extends Block {

    private static final byte TYPE_OTHER = -1;
    private static final byte TYPE_POLYMETAL = 1;
    private static final byte TYPE_GRUDGE_HEAVY = 2;

    // Legacy 1.12.2 multiblock shape around clicked heavy grudge block (center at y = 0).
    // y = -2 : 3x3 polymetal base
    // y = -1 : polymetal on diagonals only
    // y =  0 : center heavy grudge block, all surrounding blocks must be non-polymetal/non-heavy-grudge
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

        setLargeShipyardSupportFormed(level, pos, true);

        BlockState activatedState = ModBlocks.LARGE_SHIPYARD.get().defaultBlockState()
                .setValue(LargeShipyardBlock.FACING, player.getDirection().getOpposite())
                .setValue(LargeShipyardBlock.ACTIVE, false);
        level.setBlock(pos, activatedState, Block.UPDATE_ALL);
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
