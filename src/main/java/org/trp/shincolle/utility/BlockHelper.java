package org.trp.shincolle.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.trp.shincolle.init.ModBlocks;


public class BlockHelper {

    private BlockHelper() {}

    
    public static void placeLightBlock(Level world, BlockPos pos) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        for (int i = -1; i <= 1; i++) {
            for (int j = 1; j <= 2; j++) {
                for (int k = -1; k <= 1; k++) {
                    mutablePos.set(
                        pos.getX() + i,
                        pos.getY() + j,
                        pos.getZ() + k
                    );
                    if (world.getBlockState(mutablePos).isAir()) {
                        world.setBlock(
                            mutablePos,
                            ModBlocks.LIGHT_AIR.get().defaultBlockState(),
                            3
                        );
                        world.scheduleTick(
                            mutablePos,
                            ModBlocks.LIGHT_AIR.get(),
                            120
                        );
                        return;
                    }
                }
            }
        }
    }

    
    public static void updateNearbyLightBlock(Level world, BlockPos pos) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        for (int i = -1; i <= 1; i++) {
            for (int j = 1; j <= 2; j++) {
                for (int k = -1; k <= 1; k++) {
                    mutablePos.set(
                        pos.getX() + i,
                        pos.getY() + j,
                        pos.getZ() + k
                    );
                    if (
                        world
                            .getBlockState(mutablePos)
                            .is(ModBlocks.LIGHT_AIR.get())
                    ) {
                        
                        
                        world.scheduleTick(
                            mutablePos,
                            ModBlocks.LIGHT_AIR.get(),
                            120
                        );
                        return;
                    }
                }
            }
        }
    }
}
