package org.trp.shincolle.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Shincolle.MODID);

    public static final DeferredBlock<Block> GRUDGE_BLOCK = BLOCKS.register("grudge_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).noOcclusion()));

    public static final DeferredBlock<Block> GRUDGE_HEAVY_BLOCK = BLOCKS.register("grudge_heavy_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F)));
}
