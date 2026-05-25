package org.trp.shincolle.entity.base;

import net.minecraft.util.Mth;

public final class ShipUtils {

    private ShipUtils() {}

    public static boolean checkModelState(int id, int state) {
        if (id < 0 || id >= 31) {
            return false;
        }
        return (state & (1 << id)) != 0;
    }

    public static float[] rotateXZByAxis(
        float z,
        float x,
        float radians,
        float scale
    ) {
        float cosD = Mth.cos(radians);
        float sinD = Mth.sin(radians);
        float[] newPos = new float[] { 0.0f, 0.0f };
        newPos[0] = z * cosD + x * sinD;
        newPos[1] = x * cosD - z * sinD;
        newPos[0] = newPos[0] * scale;
        newPos[1] = newPos[1] * scale;
        return newPos;
    }

    public static int resolveMouthId(int id) {
        return switch (id) {
            case EntityShipBase.MOUTH_FLIP_0 -> EntityShipBase.MOUTH_FRONT_0;
            case EntityShipBase.MOUTH_FLIP_1 -> EntityShipBase.MOUTH_FRONT_1;
            case EntityShipBase.MOUTH_FLIP_2 -> EntityShipBase.MOUTH_FRONT_2;
            default -> id;
        };
    }

    public static int mapLegacyMouth(int legacyId) {
        return switch (legacyId) {
            case 0 -> EntityShipBase.MOUTH_FRONT_0;
            case 1 -> EntityShipBase.MOUTH_FRONT_1;
            case 2 -> EntityShipBase.MOUTH_FRONT_2;
            case 3 -> EntityShipBase.MOUTH_FLIP_0;
            case 4 -> EntityShipBase.MOUTH_FLIP_1;
            case 5 -> EntityShipBase.MOUTH_FLIP_2;
            default -> EntityShipBase.MOUTH_FRONT_0;
        };
    }

    public static int getLegacyFaceTick(int tickCount, int stateMinor22, int mask) {
        return (tickCount + (stateMinor22 << 7)) & mask;
    }
}
