package org.trp.shincolle.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class GrudgeXPBlock extends Block {

    public GrudgeXPBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(1.0F, 200.0F)
                .sound(SoundType.SAND)
                .lightLevel(state -> 15)
                .noOcclusion());
    }
}
