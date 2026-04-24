package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;

import java.util.Arrays;

final class EntityShipLegacyState {

    private static final int LEGACY_STATE_MINOR_SIZE = 46;
    private static final int LEGACY_STATE_TIMER_SIZE = 21;
    private static final int LEGACY_STATE_FLAG_SIZE = 28;
    private static final int LEGACY_UPDATE_FLAG_SIZE = 8;
    private static final int[] LEGACY_STATE_MINOR_DEFAULTS = {
            1, 0, 0, 40, 0, 0, 0, 0, 0, 3, 3, 12, 35, 1, -1, -1, -1, 0, -1, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0, 0, 60, 0, 10, 0, 0, -1, 0, 0, 0, 0, -1, -1, -1, 0, 0, -1
    };
    private static final boolean[] LEGACY_STATE_FLAG_DEFAULTS = {
            false, false, false, false, true, true, true, true, false, true, true, false, true, true, true, true, true, true, true, false, false, false, true, true, false, true, false, false
    };
    private static final byte[] LEGACY_BODY_HEIGHT_STAND_DEFAULTS = {92, 78, 73, 58, 47, 37};
    private static final byte[] LEGACY_BODY_HEIGHT_SIT_DEFAULTS = {64, 49, 44, 29, 23, 12};
    private static final float[] LEGACY_MODEL_POS_DEFAULTS = {0.0f, 0.0f, 0.0f, 50.0f};

    final int[] stateMinor;
    final int[] stateTimer;
    final boolean[] stateFlag;
    final boolean[] updateFlag;
    final byte[] bodyHeightStand;
    final byte[] bodyHeightSit;
    final float[] modelPos;
    BlockPos[] waypoints;

    EntityShipLegacyState() {
        this.stateMinor = Arrays.copyOf(LEGACY_STATE_MINOR_DEFAULTS, LEGACY_STATE_MINOR_SIZE);
        this.stateTimer = new int[LEGACY_STATE_TIMER_SIZE];
        this.stateFlag = Arrays.copyOf(LEGACY_STATE_FLAG_DEFAULTS, LEGACY_STATE_FLAG_SIZE);
        this.updateFlag = new boolean[LEGACY_UPDATE_FLAG_SIZE];
        this.bodyHeightStand = Arrays.copyOf(LEGACY_BODY_HEIGHT_STAND_DEFAULTS, LEGACY_BODY_HEIGHT_STAND_DEFAULTS.length);
        this.bodyHeightSit = Arrays.copyOf(LEGACY_BODY_HEIGHT_SIT_DEFAULTS, LEGACY_BODY_HEIGHT_SIT_DEFAULTS.length);
        this.modelPos = Arrays.copyOf(LEGACY_MODEL_POS_DEFAULTS, LEGACY_MODEL_POS_DEFAULTS.length);
        this.waypoints = new BlockPos[]{BlockPos.ZERO};
    }

    int getInt(int[] data, int index) {
        if (index < 0 || index >= data.length) {
            return 0;
        }
        return data[index];
    }

    void setInt(int[] data, int index, int value) {
        if (index < 0 || index >= data.length) {
            return;
        }
        data[index] = value;
    }

    boolean getBoolean(boolean[] data, int index) {
        if (index < 0 || index >= data.length) {
            return false;
        }
        return data[index];
    }

    void setBoolean(boolean[] data, int index, boolean value) {
        if (index < 0 || index >= data.length) {
            return;
        }
        data[index] = value;
    }

    void applyIntArray(int[] target, int[] source) {
        if (source == null || target == null) {
            return;
        }
        int length = Math.min(target.length, source.length);
        System.arraycopy(source, 0, target, 0, length);
    }

    void applyByteArray(boolean[] target, byte[] source) {
        if (source == null || target == null) {
            return;
        }
        int length = Math.min(target.length, source.length);
        for (int i = 0; i < length; i++) {
            target[i] = source[i] != 0;
        }
    }

    void applyByteArray(byte[] target, byte[] source) {
        if (source == null || target == null) {
            return;
        }
        int length = Math.min(target.length, source.length);
        System.arraycopy(source, 0, target, 0, length);
    }

    byte[] toByteArray(boolean[] source) {
        byte[] data = new byte[source.length];
        for (int i = 0; i < source.length; i++) {
            data[i] = source[i] ? (byte) 1 : (byte) 0;
        }
        return data;
    }

    int[] getModelPosBits() {
        int[] bits = new int[this.modelPos.length];
        for (int i = 0; i < this.modelPos.length; i++) {
            bits[i] = Float.floatToIntBits(this.modelPos[i]);
        }
        return bits;
    }

    void applyModelPos(float[] pos) {
        if (pos == null) {
            return;
        }
        int length = Math.min(this.modelPos.length, pos.length);
        System.arraycopy(pos, 0, this.modelPos, 0, length);
    }

    void applyModelPosBits(int[] bits) {
        if (bits == null) {
            return;
        }
        int length = Math.min(this.modelPos.length, bits.length);
        for (int i = 0; i < length; i++) {
            this.modelPos[i] = Float.intBitsToFloat(bits[i]);
        }
    }

    int[] getWaypointBits() {
        if (this.waypoints == null || this.waypoints.length == 0) {
            return new int[0];
        }
        int[] data = new int[this.waypoints.length * 3];
        for (int i = 0; i < this.waypoints.length; i++) {
            BlockPos pos = this.waypoints[i];
            int base = i * 3;
            data[base] = pos.getX();
            data[base + 1] = pos.getY();
            data[base + 2] = pos.getZ();
        }
        return data;
    }

    void applyWaypoints(BlockPos[] points) {
        if (points == null || points.length == 0) {
            this.waypoints = new BlockPos[]{BlockPos.ZERO};
            return;
        }
        this.waypoints = Arrays.copyOf(points, points.length);
    }

    void applyWaypointBits(int[] data) {
        if (data == null || data.length < 3) {
            return;
        }
        int count = data.length / 3;
        BlockPos[] points = new BlockPos[count];
        for (int i = 0; i < count; i++) {
            int base = i * 3;
            points[i] = new BlockPos(data[base], data[base + 1], data[base + 2]);
        }
        this.waypoints = points;
    }
}
