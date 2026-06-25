package org.trp.shincolle.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.block.DeskBlock;
import org.trp.shincolle.entity.base.EntityShipBase;

import java.util.List;
import java.util.UUID;

public final class FormationHelper {
    private FormationHelper() {}

    public static void applySummonShipsToDesk(Player player, BlockPos deskPos, List<UUID> shipUuids) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        ServerLevel world = serverPlayer.serverLevel();
        BlockState deskState = world.getBlockState(deskPos);
        if (!(deskState.getBlock() instanceof DeskBlock)) return;

        Direction facing = deskState.getValue(DeskBlock.FACING);
        Direction spawnDir = facing.getOpposite();
        Direction rightDir = spawnDir.getClockWise();
        Direction leftDir = spawnDir.getCounterClockWise();

        BlockPos refPos = deskPos.relative(spawnDir, 3).relative(leftDir, 1);

        int totalShips = 0;
        for (UUID uuid : shipUuids) {
            Entity e = world.getEntity(uuid);
            if (e instanceof EntityShipBase) totalShips++;
        }

        final int maxPerRow = 4;
        final int horizontalSpacing = 1;
        final int depthSpacing = 1;
        int col = 0;
        int row = 0;

        for (UUID uuid : shipUuids) {
            Entity entity = world.getEntity(uuid);
            if (!(entity instanceof EntityShipBase ship)) continue;

            if (totalShips == 1) {
                col = 1;
            } else if (col >= maxPerRow) {
                row++;
                col = 0;
            }

            BlockPos spawnBlock = refPos.relative(rightDir, col * horizontalSpacing).relative(spawnDir, row * depthSpacing);
            double spawnX = spawnBlock.getX() + 0.5;
            double spawnY = deskPos.getY() + 1.0;
            double spawnZ = spawnBlock.getZ() + 0.5;

            if (!world.isEmptyBlock(new BlockPos((int)spawnX, (int)spawnY, (int)spawnZ))) {
                row++;
                col = 0;
                spawnBlock = refPos.relative(rightDir, col * horizontalSpacing).relative(spawnDir, row * depthSpacing);
                spawnX = spawnBlock.getX() + 0.5;
                spawnZ = spawnBlock.getZ() + 0.5;
            }

            if (ship.distanceToSqr(spawnX, spawnY, spawnZ) > 1024.0D) {
                ship.teleportTo(spawnX, spawnY, spawnZ);
            }
            
            float yaw = facing.toYRot();
            ship.setYRot(yaw);
            ship.setYHeadRot(yaw);
            ship.setYBodyRot(yaw);
            ship.setXRot(0);
            
            applyShipGuard(ship, Mth.floor(spawnX), (int) spawnY, Mth.floor(spawnZ));
            col++;
        }
    }

    public static void applySummonShipsToPlayer(Player player, List<UUID> shipUuids) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        ServerLevel world = serverPlayer.serverLevel();

        Direction facing = player.getDirection();
        Direction spawnDir = facing;
        Direction rightDir = spawnDir.getClockWise();
        Direction leftDir = spawnDir.getCounterClockWise();

        BlockPos refPos = player.blockPosition().relative(spawnDir, 2).relative(leftDir, 1);

        int totalShips = 0;
        for (UUID uuid : shipUuids) {
            Entity e = world.getEntity(uuid);
            if (e instanceof EntityShipBase) totalShips++;
        }

        final int maxPerRow = 4;
        final int horizontalSpacing = 1;
        final int depthSpacing = 1;
        int col = 0;
        int row = 0;

        for (UUID uuid : shipUuids) {
            Entity entity = world.getEntity(uuid);
            if (!(entity instanceof EntityShipBase ship)) continue;

            if (totalShips == 1) {
                col = 1;
            } else if (col >= maxPerRow) {
                row++;
                col = 0;
            }

            BlockPos spawnBlock = refPos.relative(rightDir, col * horizontalSpacing).relative(spawnDir, row * depthSpacing);
            double spawnX = spawnBlock.getX() + 0.5;
            double spawnY = player.getY();
            double spawnZ = spawnBlock.getZ() + 0.5;

            BlockPos checkPos = new BlockPos((int)spawnX, (int)spawnY, (int)spawnZ);
            if (!world.isEmptyBlock(checkPos)) {
                if (world.isEmptyBlock(checkPos.above())) {
                    spawnY += 1.0;
                } else {
                    row++;
                    col = 0;
                    spawnBlock = refPos.relative(rightDir, col * horizontalSpacing).relative(spawnDir, row * depthSpacing);
                    spawnX = spawnBlock.getX() + 0.5;
                    spawnZ = spawnBlock.getZ() + 0.5;
                    spawnY = player.getY();
                }
            }

            if (ship.distanceToSqr(spawnX, spawnY, spawnZ) > 1024.0D) {
                ship.teleportTo(spawnX, spawnY, spawnZ);
            }
            
            float yaw = facing.toYRot();
            ship.setYRot(yaw);
            ship.setYHeadRot(yaw);
            ship.setYBodyRot(yaw);
            ship.setXRot(0);
            
            applyShipGuard(ship, Mth.floor(spawnX), (int) spawnY, Mth.floor(spawnZ), 20);
            col++;
        }
    }

    public static void applyShipGuard(EntityShipBase ship, int x, int y, int z) {
        applyShipGuard(ship, x, y, z, 200);
    }

    public static void applyShipGuard(EntityShipBase ship, int x, int y, int z, int guardTimer) {
        if (ship == null) return;
        ship.setOrderedToSit(false);
        ship.setInSittingPose(false);
        ship.setGuardedPos(x, y, z, 0, 1);
        ship.getStateComponent().setStateDisableGuardPos(false);
        ship.getNavigation().moveTo(x + 0.5, y, z + 0.5, 1.2);
        ship.getStateComponent().setGuardTimer(guardTimer);
    }

    
    public static Vec3 getFormationPos(int formationId, int slotId, Vec3 flagshipPos, float yaw) {
        if (slotId == 0) return flagshipPos;
        boolean[] dir = getFormationDirectionFromYaw(yaw);
        return getFormationPos(formationId, slotId, flagshipPos, dir[0], dir[1]);
    }

    public static Vec3 getFormationPos(int formationId, int slotId, Vec3 flagshipPos, boolean alongX, boolean faceP) {
        double[] target = calcFormationPos(formationId, slotId, flagshipPos.x, flagshipPos.y, flagshipPos.z, alongX, faceP);
        return new Vec3(target[0], flagshipPos.y, target[2]);
    }

    public static boolean[] getFormationDirection(double toX, double toZ, double fromX, double fromZ) {
        boolean[] face = new boolean[2];
        double dx = toX - fromX;
        double dz = toZ - fromZ;
        face[0] = Math.abs(dx) > Math.abs(dz);
        face[1] = face[0] ? (dx >= 0.0) : (dz >= 0.0);
        return face;
    }

    public static boolean[] getFormationDirectionFromYaw(float yaw) {
        boolean[] dir = new boolean[2];
        double rad = Math.toRadians(yaw);
        double dx = -Math.sin(rad);
        double dz = Math.cos(rad);
        dir[0] = Math.abs(dx) > Math.abs(dz);
        dir[1] = dir[0] ? (dx >= 0.0D) : (dz >= 0.0D);
        return dir;
    }

    public static double[] calcFormationPos(int formationId, int slotId, double x, double y, double z, boolean alongX, boolean faceP) {
        double[] newPos = {x, y, z};
        if (slotId == 0) return newPos;

        switch (formationId) {
            case 1:
                for (int i = 0; i < slotId; ++i) newPos = nextLineAheadPos(alongX, faceP, newPos[0], newPos[1], newPos[2]);
                break;
            case 4:
                for (int i = 0; i < slotId; ++i) newPos = nextEchelonPos(faceP, newPos[0], newPos[1], newPos[2]);
                break;
            case 2:
                newPos = nextDoubleLinePos(alongX, faceP, slotId, newPos[0], newPos[1], newPos[2]);
                break;
            case 3:
                newPos = nextDiamondPos(alongX, faceP, slotId, newPos[0], newPos[1], newPos[2]);
                break;
            case 5:
                newPos = nextLineAbreastPos(alongX, slotId, newPos[0], newPos[1], newPos[2]);
                break;
            default:
        }
        return newPos;
    }

    private static double[] nextLineAheadPos(boolean alongX, boolean faceP, double x, double y, double z) {
        double[] pos = {x, y, z};
        double offset = faceP ? -3.0 : 3.0;
        if (alongX) {
            pos[0] += offset;
        } else {
            pos[2] += offset;
        }
        return pos;
    }

    private static double[] nextDoubleLinePos(boolean alongX, boolean faceP, int formatPos, double x, double y, double z) {
        double[] pos = {x, y, z};
        switch (formatPos) {
            case 1:
                if (alongX) pos[2] += 3.0; else pos[0] += 3.0;
                break;
            case 2:
                if (alongX) pos[0] += faceP ? 3.0 : -3.0; else pos[2] += faceP ? 3.0 : -3.0;
                break;
            case 3:
                if (alongX) { pos[0] += faceP ? 3.0 : -3.0; pos[2] += 3.0; }
                else { pos[0] += 3.0; pos[2] += faceP ? 3.0 : -3.0; }
                break;
            case 4:
                if (alongX) pos[0] += faceP ? -3.0 : 3.0; else pos[2] += faceP ? -3.0 : 3.0;
                break;
            case 5:
                if (alongX) { pos[0] += faceP ? -3.0 : 3.0; pos[2] += 3.0; }
                else { pos[0] += 3.0; pos[2] += faceP ? -3.0 : 3.0; }
                break;
            default:
        }
        return pos;
    }

    private static double[] nextDiamondPos(boolean alongX, boolean faceP, int formatPos, double x, double y, double z) {
        double[] pos = {x, y, z};
        switch (formatPos) {
            case 1:
                if (alongX) pos[0] += faceP ? 5.0 : -5.0; else pos[2] += faceP ? 5.0 : -5.0;
                break;
            case 2:
                if (alongX) { pos[0] += faceP ? 1.0 : -1.0; pos[2] -= 4.0; }
                else { pos[0] -= 4.0; pos[2] += faceP ? 1.0 : -1.0; }
                break;
            case 3:
                if (alongX) { pos[0] += faceP ? 1.0 : -1.0; pos[2] += 4.0; }
                else { pos[0] += 4.0; pos[2] += faceP ? 1.0 : -1.0; }
                break;
            case 4:
                if (alongX) pos[0] += faceP ? -3.0 : 3.0; else pos[2] += faceP ? -3.0 : 3.0;
                break;
            case 5:
                if (alongX) pos[0] += faceP ? 2.0 : -2.0; else pos[2] += faceP ? 2.0 : -2.0;
                break;
            default:
        }
        return pos;
    }

    private static double[] nextEchelonPos(boolean faceP, double x, double y, double z) {
        double[] pos = {x, y, z};
        double offset = faceP ? -2.0 : 2.0;
        pos[0] += offset;
        pos[2] += offset;
        return pos;
    }

    private static double[] nextLineAbreastPos(boolean alongX, int formatPos, double x, double y, double z) {
        double[] pos = {x, y, z};
        double offset;
        switch (formatPos) {
            case 1: offset = 3.0; break;
            case 2: offset = -3.0; break;
            case 3: offset = 6.0; break;
            case 4: offset = -6.0; break;
            case 5: offset = 9.0; break;
            default: offset = 0.0; break;
        }
        if (alongX) pos[2] += offset; else pos[0] += offset;
        return pos;
    }

    public static float[] getFormationBuffs(int formationId, int slotId) {
        float[] fvalue = org.trp.shincolle.reference.Values.FormationAttrs.get(formationId * 10 + slotId);
        return fvalue != null ? java.util.Arrays.copyOf(fvalue, fvalue.length) : org.trp.shincolle.reference.Values.getResetFormationValue();
    }
}
