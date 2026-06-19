package org.trp.shincolle.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.trp.shincolle.init.ModParticles;

import java.util.ArrayList;
import java.util.List;

public class ShiningOreBlock extends DropExperienceBlock {

    public ShiningOreBlock(IntProvider xpRange, Properties properties) {
        super(xpRange, properties);
    }

    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, false);
        if (dropExperience) {
            int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(
                level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FORTUNE),
                stack
            );
            int xp = (level.getRandom().nextInt(4) + 1) * (fortuneLevel + 1);
            this.popExperience(level, pos, xp);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(3) == 0) {
            return;
        }

        List<Direction> openSides = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = level.getBlockState(neighborPos);
            if (!neighborState.isSolidRender(level, neighborPos)) {
                openSides.add(direction);
            }
        }

        if (!openSides.isEmpty()) {
            Direction chosenSide = openSides.get(random.nextInt(openSides.size()));
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            double randomX = random.nextDouble();
            double randomY = random.nextDouble();
            double randomZ = random.nextDouble();

            double particleX = x + 0.5D;
            double particleY = y + 0.5D;
            double particleZ = z + 0.5D;

            switch (chosenSide) {
                case DOWN -> {
                    particleY = y;
                    particleX = x + randomX;
                    particleZ = z + randomZ;
                }
                case UP -> {
                    particleY = y + 1.0D;
                    particleX = x + randomX;
                    particleZ = z + randomZ;
                }
                case NORTH -> {
                    particleZ = z;
                    particleX = x + randomX;
                    particleY = y + randomY;
                }
                case SOUTH -> {
                    particleZ = z + 1.0D;
                    particleX = x + randomX;
                    particleY = y + randomY;
                }
                case WEST -> {
                    particleX = x;
                    particleY = y + randomY;
                    particleZ = z + randomZ;
                }
                case EAST -> {
                    particleX = x + 1.0D;
                    particleY = y + randomY;
                    particleZ = z + randomZ;
                }
            }

            level.addParticle(ModParticles.PARTICLE_SHINE.get(), particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
        }
    }
}
