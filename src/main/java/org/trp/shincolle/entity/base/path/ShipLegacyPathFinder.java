package org.trp.shincolle.entity.base.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

final class ShipLegacyPathFinder {

    private enum LegacyPathType {
        OPEN,
        FLUID,
        OPENABLE,
        FENCE,
        STAIR_OR_LADDER,
        BLOCKED
    }

    private static final int MAX_DESCEND_SCAN = 64;
    private static final int MAX_SEARCH_ITERS = 4096;
    private static final int MAX_PATH_OPTIONS = 32;

    private final LevelReader level;
    private final boolean canEntityFly;

    private final ShipLegacyPathHeap openSet = new ShipLegacyPathHeap();
    private final Map<Long, ShipLegacyPathPoint> pointMap = new HashMap<>();
    private final ShipLegacyPathPoint[] pathOptions = new ShipLegacyPathPoint[MAX_PATH_OPTIONS];

    ShipLegacyPathFinder(LevelReader level, boolean canEntityFly) {
        this.level = level;
        this.canEntityFly = canEntityFly;
    }

    @Nullable
    ShipLegacyPath findPath(Entity host, Entity target, float range) {
        return findPath(host, target.getX(), target.getY(), target.getZ(), range);
    }

    @Nullable
    ShipLegacyPath findPath(Entity host, int x, int y, int z, float range) {
        return findPath(host, (double) x + 0.5D, y, (double) z + 0.5D, range);
    }

    @Nullable
    ShipLegacyPath findPath(Entity host, double tx, double ty, double tz, float range) {
        this.openSet.clearPath();
        this.pointMap.clear();

        int startY;
        BlockPos hostPos = host.blockPosition();

        if (isInLiquid(host)) {
            startY = hostPos.getY();

            BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos(hostPos.getX(), startY, hostPos.getZ());

            while (startY < this.level.getMaxBuildHeight()
                    && !this.level.getFluidState(cursor).isEmpty()) {
                startY++;
                cursor.setY(startY);
            }
        } else {
            startY = Mth.floor(host.getBoundingBox().minY + 0.5D);
        }

        ShipLegacyPathPoint start = openPoint(
                Mth.floor(host.getBoundingBox().minX),
                startY,
                Mth.floor(host.getBoundingBox().minZ));
        ShipLegacyPathPoint end = openPoint(Mth.floor(tx), Mth.floor(ty), Mth.floor(tz));

        int sizeX = Mth.floor(host.getBbWidth() + 1.0F);
        int sizeY = Mth.floor(host.getBbHeight() + 1.0F);
        int sizeZ = Mth.floor(host.getBbWidth() + 1.0F);

        return addToPath(host, start, end, range, sizeX, sizeY, sizeZ);
    }

    @Nullable
    private ShipLegacyPath addToPath(
            Entity host,
            ShipLegacyPathPoint start,
            ShipLegacyPathPoint end,
            float range,
            int sizeX,
            int sizeY,
            int sizeZ) {
        start.initForSearch(end);
        this.openSet.clearPath();
        this.openSet.addPoint(start);

        ShipLegacyPathPoint nearest = start;
        int loops = 0;

        while (!this.openSet.isPathEmpty() && loops++ < MAX_SEARCH_ITERS) {
            ShipLegacyPathPoint current = this.openSet.dequeue();

            if (current.getKey() == end.getKey()) {
                return createEntityPath(start, end);
            }

            if (current.distanceToSquared(end) < nearest.distanceToSquared(end)) {
                nearest = current;
            }

            current.setClosed(true);
            int optionCount = findPathOptions(host, current, end, range, sizeX, sizeY, sizeZ);

            for (int i = 0; i < optionCount; i++) {
                ShipLegacyPathPoint next = this.pathOptions[i];

                if (next.initPathParameters(current, end, range)) {
                    float score = next.getTotalPathDistance() + next.getDistanceToNext();

                    if (next.isAssigned()) {
                        this.openSet.changeDistance(next, score);
                    } else {
                        next.setDistanceToTarget(score);
                        this.openSet.addPoint(next);
                    }
                }
            }
        }

        return nearest == start ? null : createEntityPath(start, nearest);
    }

    private int findPathOptions(
            Entity host,
            ShipLegacyPathPoint current,
            ShipLegacyPathPoint target,
            float maxRange,
            int sizeX,
            int sizeY,
            int sizeZ) {
        int yOffset = 0;
        BlockPos above = new BlockPos(current.getX(), current.getY() + 1, current.getZ());

        LegacyPathType aboveType = getPathType(above.getX(), above.getY(), above.getZ(), sizeX, sizeY, sizeZ);
        if (aboveType == LegacyPathType.OPEN || aboveType == LegacyPathType.FLUID || aboveType == LegacyPathType.OPENABLE) {
            yOffset = 1;
        }

        int found = 0;
        ShipLegacyPathPoint[] candidates = new ShipLegacyPathPoint[]{
                getSafePoint(host, current.getX(), current.getY(), current.getZ() + 1, yOffset, sizeX, sizeY, sizeZ),
                getSafePoint(host, current.getX() - 1, current.getY(), current.getZ(), yOffset, sizeX, sizeY, sizeZ),
                getSafePoint(host, current.getX() + 1, current.getY(), current.getZ(), yOffset, sizeX, sizeY, sizeZ),
                getSafePoint(host, current.getX(), current.getY(), current.getZ() - 1, yOffset, sizeX, sizeY, sizeZ),
                getSafePoint(host, current.getX(), current.getY() + 1, current.getZ(), 0, sizeX, sizeY, sizeZ),
                getSafePoint(host, current.getX(), current.getY() - 1, current.getZ(), 0, sizeX, sizeY, sizeZ)
        };

        for (ShipLegacyPathPoint candidate : candidates) {
            if (candidate != null
                    && !candidate.isClosed()
                    && candidate.distanceTo(target) < maxRange) {
                this.pathOptions[found++] = candidate;
            }
        }

        return found;
    }

    @Nullable
    private ShipLegacyPathPoint getSafePoint(
            Entity host,
            int x,
            int y,
            int z,
            int yOffset,
            int sizeX,
            int sizeY,
            int sizeZ) {
        ShipLegacyPathPoint result = null;

        LegacyPathType type = getPathType(x, y, z, sizeX, sizeY, sizeZ);

        if (type == LegacyPathType.OPEN || type == LegacyPathType.OPENABLE || type == LegacyPathType.FLUID) {
            result = openPoint(x, y, z);
        }

        if (result == null && yOffset > 0 && type != LegacyPathType.FENCE && type != LegacyPathType.STAIR_OR_LADDER) {
            result = getSafePoint(host, x, y + yOffset, z, 0, sizeX, sizeY, sizeZ);
        }

        if (result == null && (type == LegacyPathType.STAIR_OR_LADDER || type == LegacyPathType.BLOCKED)) {
            return null;
        }

        if (result != null && !this.canEntityFly && !isInLiquid(host)) {
            int scanY = result.getY();
            int scanCount = 0;

            while (scanY > this.level.getMinBuildHeight()) {
                LegacyPathType below = getPathType(x, scanY - 1, z, sizeX, sizeY, sizeZ);

                if (below == LegacyPathType.FLUID) {
                    return openPoint(x, scanY - 1, z);
                }

                if (below != LegacyPathType.OPEN) {
                    break;
                }

                if (scanCount++ > MAX_DESCEND_SCAN) {
                    return null;
                }

                scanY--;
                result = openPoint(x, scanY, z);
            }
        }

        return result;
    }

    private LegacyPathType getPathType(int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
        boolean sawOpen = false;
        boolean sawFluid = false;
        boolean sawOpenable = false;

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int ix = x; ix < x + sizeX; ix++) {
            for (int iy = y; iy < y + sizeY; iy++) {
                for (int iz = z; iz < z + sizeZ; iz++) {
                    pos.set(ix, iy, iz);
                    BlockState state = this.level.getBlockState(pos);

                    if (state.isAir()) {
                        sawOpen = true;
                        continue;
                    }

                    if (state.getBlock() instanceof StairBlock
                            || state.getBlock() instanceof LadderBlock
                            || state.is(BlockTags.CLIMBABLE)) {
                        return LegacyPathType.STAIR_OR_LADDER;
                    }

                    if (state.getBlock() instanceof FenceBlock || state.getBlock() instanceof WallBlock) {
                        return LegacyPathType.FENCE;
                    }

                    if (state.getBlock() instanceof DoorBlock || state.getBlock() instanceof FenceGateBlock) {
                        if (state.getCollisionShape(this.level, pos).isEmpty()) {
                            sawOpen = true;
                        } else {
                            sawOpenable = true;
                        }
                        continue;
                    }

                    if (!state.getFluidState().isEmpty()) {
                        sawFluid = true;
                        continue;
                    }

                    if (!state.getCollisionShape(this.level, pos).isEmpty()) {
                        return LegacyPathType.BLOCKED;
                    }

                    sawOpen = true;
                }
            }
        }

        if (sawFluid) {
            return LegacyPathType.FLUID;
        }

        if (sawOpenable) {
            return LegacyPathType.OPENABLE;
        }

        return sawOpen ? LegacyPathType.OPEN : LegacyPathType.BLOCKED;
    }

    private ShipLegacyPathPoint openPoint(int x, int y, int z) {
        long key = BlockPos.asLong(x, y, z);
        ShipLegacyPathPoint point = this.pointMap.get(key);

        if (point == null) {
            point = new ShipLegacyPathPoint(x, y, z, key);
            this.pointMap.put(key, point);
        }

        return point;
    }

    private ShipLegacyPath createEntityPath(ShipLegacyPathPoint start, ShipLegacyPathPoint end) {
        List<ShipLegacyPathPoint> path = new ArrayList<>();
        ShipLegacyPathPoint node = end;

        path.add(end);

        while (node.getPrevious() != null && node != start) {
            node = node.getPrevious();
            path.add(0, node);
        }

        return new ShipLegacyPath(path.toArray(new ShipLegacyPathPoint[0]));
    }

    private static boolean isInLiquid(Entity entity) {
        return entity.isInWaterOrBubble() || entity.isInLava();
    }
}
