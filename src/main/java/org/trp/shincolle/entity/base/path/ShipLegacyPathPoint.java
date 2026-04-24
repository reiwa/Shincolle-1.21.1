package org.trp.shincolle.entity.base.path;

import net.minecraft.util.Mth;

final class ShipLegacyPathPoint {

    private final int x;
    private final int y;
    private final int z;
    private final long key;

    private int heapIndex = -1;
    private float totalPathDistance;
    private float distanceToNext;
    private float distanceToTarget;
    private ShipLegacyPathPoint previous;
    private boolean closed;
    private float distanceFromOrigin;

    ShipLegacyPathPoint(int x, int y, int z, long key) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.key = key;
    }

    void initForSearch(ShipLegacyPathPoint target) {
        this.totalPathDistance = 0.0F;
        float dist = distanceManhattan(target);
        this.distanceToNext = dist;
        this.distanceToTarget = dist;
        this.distanceFromOrigin = 0.0F;
        this.previous = null;
        this.closed = false;
        this.heapIndex = -1;
    }

    boolean initPathParameters(ShipLegacyPathPoint parent, ShipLegacyPathPoint target, float range) {
        float dist = parent.distanceManhattan(this);
        float newFrom = parent.distanceFromOrigin + dist;
        float newCost = parent.totalPathDistance + dist;

        if (newFrom >= range || (isAssigned() && newCost >= this.totalPathDistance)) {
            return false;
        }

        this.distanceFromOrigin = newFrom;
        this.previous = parent;
        this.totalPathDistance = newCost;
        this.distanceToNext = distanceManhattan(target);
        return true;
    }

    float distanceTo(ShipLegacyPathPoint point) {
        float dx = (float) point.x - this.x;
        float dy = (float) point.y - this.y;
        float dz = (float) point.z - this.z;
        return Mth.sqrt(dx * dx + dy * dy + dz * dz);
    }

    float distanceToSquared(ShipLegacyPathPoint point) {
        float dx = (float) point.x - this.x;
        float dy = (float) point.y - this.y;
        float dz = (float) point.z - this.z;
        return dx * dx + dy * dy + dz * dz;
    }

    float distanceManhattan(ShipLegacyPathPoint point) {
        return (float) (Math.abs(point.x - this.x)
                + Math.abs(point.y - this.y)
                + Math.abs(point.z - this.z));
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    int getZ() {
        return this.z;
    }

    long getKey() {
        return this.key;
    }

    int getHeapIndex() {
        return this.heapIndex;
    }

    void setHeapIndex(int heapIndex) {
        this.heapIndex = heapIndex;
    }

    float getTotalPathDistance() {
        return this.totalPathDistance;
    }

    float getDistanceToNext() {
        return this.distanceToNext;
    }

    float getDistanceToTarget() {
        return this.distanceToTarget;
    }

    void setDistanceToTarget(float distanceToTarget) {
        this.distanceToTarget = distanceToTarget;
    }

    ShipLegacyPathPoint getPrevious() {
        return this.previous;
    }

    boolean isClosed() {
        return this.closed;
    }

    void setClosed(boolean closed) {
        this.closed = closed;
    }

    boolean isAssigned() {
        return this.heapIndex >= 0;
    }
}
