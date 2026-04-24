package org.trp.shincolle.entity.base.path;

import java.util.Arrays;

final class ShipLegacyPathHeap {

    private ShipLegacyPathPoint[] points = new ShipLegacyPathPoint[1024];
    private int count;

    void clearPath() {
        this.count = 0;
    }

    boolean isPathEmpty() {
        return this.count == 0;
    }

    ShipLegacyPathPoint addPoint(ShipLegacyPathPoint point) {
        if (point.isAssigned()) {
            throw new IllegalStateException("Point already assigned to heap");
        }

        if (this.count == this.points.length) {
            this.points = Arrays.copyOf(this.points, this.points.length * 2);
        }

        this.points[this.count] = point;
        point.setHeapIndex(this.count);
        this.sortBack(this.count++);
        return point;
    }

    ShipLegacyPathPoint dequeue() {
        ShipLegacyPathPoint result = this.points[0];
        this.points[0] = this.points[--this.count];
        this.points[this.count] = null;

        if (this.count > 0) {
            this.points[0].setHeapIndex(0);
            this.sortForward(0);
        }

        result.setHeapIndex(-1);
        return result;
    }

    void changeDistance(ShipLegacyPathPoint point, float distance) {
        float prev = point.getDistanceToTarget();
        point.setDistanceToTarget(distance);

        if (distance < prev) {
            this.sortBack(point.getHeapIndex());
        } else {
            this.sortForward(point.getHeapIndex());
        }
    }

    private void sortBack(int index) {
        ShipLegacyPathPoint current = this.points[index];

        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            ShipLegacyPathPoint parent = this.points[parentIndex];

            if (current.getDistanceToTarget() >= parent.getDistanceToTarget()) {
                break;
            }

            this.points[index] = parent;
            parent.setHeapIndex(index);
            index = parentIndex;
        }

        this.points[index] = current;
        current.setHeapIndex(index);
    }

    private void sortForward(int index) {
        ShipLegacyPathPoint current = this.points[index];
        float currentDist = current.getDistanceToTarget();

        while (true) {
            int left = 1 + (index << 1);
            int right = left + 1;

            if (left >= this.count) {
                break;
            }

            ShipLegacyPathPoint leftPoint = this.points[left];
            float leftDist = leftPoint.getDistanceToTarget();
            float rightDist = Float.POSITIVE_INFINITY;
            ShipLegacyPathPoint rightPoint = null;

            if (right < this.count) {
                rightPoint = this.points[right];
                rightDist = rightPoint.getDistanceToTarget();
            }

            if (leftDist < rightDist) {
                if (leftDist >= currentDist) {
                    break;
                }

                this.points[index] = leftPoint;
                leftPoint.setHeapIndex(index);
                index = left;
            } else {
                if (rightDist >= currentDist) {
                    break;
                }

                this.points[index] = rightPoint;
                rightPoint.setHeapIndex(index);
                index = right;
            }
        }

        this.points[index] = current;
        current.setHeapIndex(index);
    }
}
