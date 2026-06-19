package org.trp.shincolle.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class VolBlock extends Block {

    public VolBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(3.0F, 200.0F)
                .sound(SoundType.METAL)
                .lightLevel(state -> 15)
        );
    }
}
