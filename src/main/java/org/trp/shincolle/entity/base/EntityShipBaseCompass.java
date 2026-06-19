package org.trp.shincolle.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

import java.util.HashSet;
import java.util.Set;

class EntityShipBaseCompass {

    private static final int COMPASS_CHUNK_REFRESH_INTERVAL_TICKS = 40;
    private static final int COMPASS_CHUNK_RADIUS = 1;

    private final EntityShipBase ship;
    private final Set<Long> forcedCompassChunks = new HashSet<>();
    private int forcedCompassChunkCenterX = Integer.MIN_VALUE;
    private int forcedCompassChunkCenterZ = Integer.MIN_VALUE;

    EntityShipBaseCompass(EntityShipBase ship) {
        this.ship = ship;
    }

    void tickCompassChunkLoading() {
        if (!(this.ship.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (
            !this.ship.isAlive() ||
            this.ship.getStateMinor(EntityShipBase.STATE_MINOR_EQUIP_COMPASS) <= 0
        ) {
            clearCompassForcedChunks(serverLevel);
            return;
        }

        ChunkPos chunkPos = this.ship.chunkPosition();
        boolean movedChunk =
            chunkPos.x != this.forcedCompassChunkCenterX ||
            chunkPos.z != this.forcedCompassChunkCenterZ;
        if (
            !movedChunk &&
            (this.ship.tickCount % COMPASS_CHUNK_REFRESH_INTERVAL_TICKS) != 0
        ) {
            return;
        }

        this.forcedCompassChunkCenterX = chunkPos.x;
        this.forcedCompassChunkCenterZ = chunkPos.z;
        updateCompassForcedChunks(serverLevel, chunkPos.x, chunkPos.z);
    }

    private void updateCompassForcedChunks(
        ServerLevel serverLevel,
        int centerX,
        int centerZ
    ) {
        Set<Long> desired = new HashSet<>();
        for (int dx = -COMPASS_CHUNK_RADIUS; dx <= COMPASS_CHUNK_RADIUS; dx++) {
            for (
                int dz = -COMPASS_CHUNK_RADIUS;
                dz <= COMPASS_CHUNK_RADIUS;
                dz++
            ) {
                int cx = centerX + dx;
                int cz = centerZ + dz;
                long key = ChunkPos.asLong(cx, cz);
                desired.add(key);
                if (!this.forcedCompassChunks.contains(key)) {
                    serverLevel.setChunkForced(cx, cz, true);
                }
            }
        }

        if (!this.forcedCompassChunks.isEmpty()) {
            for (long key : new HashSet<>(this.forcedCompassChunks)) {
                if (desired.contains(key)) {
                    continue;
                }
                serverLevel.setChunkForced(
                    ChunkPos.getX(key),
                    ChunkPos.getZ(key),
                    false
                );
            }
        }

        this.forcedCompassChunks.clear();
        this.forcedCompassChunks.addAll(desired);
    }

    void clearCompassForcedChunks(ServerLevel serverLevel) {
        if (this.forcedCompassChunks.isEmpty()) {
            this.forcedCompassChunkCenterX = Integer.MIN_VALUE;
            this.forcedCompassChunkCenterZ = Integer.MIN_VALUE;
            return;
        }

        for (long key : new HashSet<>(this.forcedCompassChunks)) {
            serverLevel.setChunkForced(
                ChunkPos.getX(key),
                ChunkPos.getZ(key),
                false
            );
        }
        this.forcedCompassChunks.clear();
        this.forcedCompassChunkCenterX = Integer.MIN_VALUE;
        this.forcedCompassChunkCenterZ = Integer.MIN_VALUE;
    }
}
