package org.trp.shincolle.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.util.valueproviders.UniformInt;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Shincolle.MODID);

    public static final DeferredBlock<Block> GRUDGE_BLOCK = BLOCKS.register("grudge_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).noOcclusion()));

    public static final DeferredBlock<Block> GRUDGE_HEAVY_BLOCK = BLOCKS.register("grudge_heavy_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F)));

    public static final DeferredBlock<Block> POLYMETAL = BLOCKS.register("polymetal",
            () -> new Block(BlockBehaviour.Properties.of().strength(3.0F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> POLYMETAL_ORE = BLOCKS.register("polymetal_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 4), BlockBehaviour.Properties.of()
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 10)
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> POLYMETAL_GRAVEL = BLOCKS.register("polymetal_gravel",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.SAND)));
}
