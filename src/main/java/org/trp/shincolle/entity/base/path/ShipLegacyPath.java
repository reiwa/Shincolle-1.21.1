package org.trp.shincolle.entity.base.path;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

final class ShipLegacyPath {

    private final ShipLegacyPathPoint[] points;
    private int currentPathIndex;

    ShipLegacyPath(ShipLegacyPathPoint[] points) {
        this.points = points;
    }

    int getCurrentPathLength() {
        return this.points.length;
    }

    int getCurrentPathIndex() {
        return this.currentPathIndex;
    }

    void setCurrentPathIndex(int currentPathIndex) {
        this.currentPathIndex = currentPathIndex;
    }

    void incrementPathIndex() {
        this.currentPathIndex++;
    }

    boolean isFinished() {
        return this.currentPathIndex >= this.points.length;
    }

    ShipLegacyPathPoint getFinalPathPoint() {
        return this.points.length > 0 ? this.points[this.points.length - 1] : null;
    }

    ShipLegacyPathPoint getPathPointFromIndex(int index) {
        return this.points[index];
    }

    Vec3 getCurrentPos() {
        if (isFinished()) {
            return Vec3.ZERO;
        }

        ShipLegacyPathPoint point = this.points[this.currentPathIndex];
        return new Vec3(point.getX(), point.getY(), point.getZ());
    }

    Vec3 getVectorFromIndex(Entity entity, int index) {
        ShipLegacyPathPoint point = this.points[index];
        double x = point.getX() + (double) ((int) (entity.getBbWidth() + 1.0F)) * 0.5D;
        double y = point.getY();
        double z = point.getZ() + (double) ((int) (entity.getBbWidth() + 1.0F)) * 0.5D;
        return new Vec3(x, y, z);
    }

    Vec3 getPosition(Entity entity) {
        return getVectorFromIndex(entity, this.currentPathIndex);
    }
}
