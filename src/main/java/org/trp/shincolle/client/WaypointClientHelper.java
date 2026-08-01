package org.trp.shincolle.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.trp.shincolle.block.entity.IWaypoint;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.item.PointerItem;
import org.trp.shincolle.item.TargetWrenchItem;

public class WaypointClientHelper {

    public static void tickClient(Level level, BlockPos pos, IWaypoint be, int tickCount) {
        Player localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) return;

        boolean playerWatching = isWatchingItem(localPlayer.getMainHandItem()) || isWatchingItem(localPlayer.getOffhandItem());
        if (!playerWatching) return;

        if ((tickCount & 7) == 0) {
            if (be.showBaseParticle()) {
                level.addParticle(ModParticles.PARTICLE_WAYPOINT.get(),
                    pos.getX() + 0.5, pos.getY() - 0.25, pos.getZ() + 0.5,
                    0.2, 0.0, 0.0);
            }

            if ((tickCount & 15) == 0) {
                BlockPos next = be.getNextPos();
                if (next != null && !next.equals(BlockPos.ZERO)) {
                    double dx = (next.getX() - pos.getX()) * 0.01;
                    double dy = (next.getY() - pos.getY()) * 0.01;
                    double dz = (next.getZ() - pos.getZ()) * 0.01;
                    level.addParticle(ModParticles.PARTICLE_WAYPOINT_LINE.get(),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        dx, dy, dz);
                }
                BlockPos chest = be.getChestPos();
                if (chest != null && !chest.equals(BlockPos.ZERO)) {
                    double dx = (chest.getX() - pos.getX()) * 0.01;
                    double dy = (chest.getY() - pos.getY()) * 0.01;
                    double dz = (chest.getZ() - pos.getZ()) * 0.01;
                    level.addParticle(ModParticles.PARTICLE_WAYPOINT_LINE_PURPLE.get(),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        dx, dy, dz);
                }

                if ((tickCount & 31) == 0) {
                    if (be.showBaseParticle()) {
                        level.addParticle(ModParticles.PARTICLE_WAYPOINT.get(),
                                pos.getX() + 0.5, pos.getY() - 0.25, pos.getZ() + 0.5,
                                0.2, 0.0, 0.0);
                    }
                }
            }
        }
    }

    private static boolean isWatchingItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        if (stack.getItem() instanceof TargetWrenchItem) return true;
        if (stack.getItem() == ModItems.WAYPOINT.get()) return true;
        if (stack.getItem() == ModItems.CRANE.get()) return true;
        if (stack.getItem() instanceof PointerItem pointer) {
            int mode = pointer.getMode(stack);
            return mode < 3;
        }
        return false;
    }
}
