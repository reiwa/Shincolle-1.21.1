package org.trp.shincolle.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class GrudgeHeavyDecoBlock extends Block {

    public GrudgeHeavyDecoBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(3.0F, 300.0F)
                .sound(SoundType.SAND)
                .lightLevel(state -> 15)
                .requiresCorrectToolForDrops());
    }
}
