package org.trp.shincolle.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class LightAirBlock extends Block {

    public static final MapCodec<LightAirBlock> CODEC = simpleCodec(
        properties -> new LightAirBlock()
    );

    public LightAirBlock() {
        super(
            BlockBehaviour.Properties.of()
                .strength(0F)
                .noCollission()
                .noOcclusion()
                .noLootTable()
                .lightLevel(state -> 15)
        );
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public VoxelShape getShape(
        BlockState state,
        BlockGetter level,
        BlockPos pos,
        CollisionContext context
    ) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getCollisionShape(
        BlockState state,
        BlockGetter level,
        BlockPos pos,
        CollisionContext context
    ) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getInteractionShape(
        BlockState state,
        BlockGetter level,
        BlockPos pos
    ) {
        return Shapes.empty();
    }

    @Override
    public boolean propagatesSkylightDown(
        BlockState state,
        BlockGetter level,
        BlockPos pos
    ) {
        return true;
    }

    @Override
    public float getShadeBrightness(
        BlockState state,
        BlockGetter level,
        BlockPos pos
    ) {
        return 1.0F;
    }

    @Override
    public boolean isPossibleToRespawnInThis(BlockState state) {
        return true;
    }

    @Override
    protected void tick(
        BlockState state,
        ServerLevel level,
        BlockPos pos,
        RandomSource random
    ) {
        level.setBlock(
            pos,
            net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(),
            Block.UPDATE_ALL
        );
    }
}
