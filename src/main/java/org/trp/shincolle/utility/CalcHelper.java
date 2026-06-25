package org.trp.shincolle.utility;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CalcHelper {

    private CalcHelper() {}

    public static int getEntityHitHeight(Player host, Entity target) {
        if (target == null || host == null || target.getBbHeight() < 0.1f) {
            return 0;
        }
        float rotPitch = host.getXRot();
        float x1 = (float) Math.tan(rotPitch * (float) (Math.PI / 180));
        if (x1 > 30.0f) return -10;
        if (x1 < -30.0f) return 110;
        float dist = (float) host.distanceTo(target) - target.getBbWidth() * 0.5f;
        if (dist < 0.0f) dist = 0.0f;
        float x2 = (float) (host.getEyeY() - target.getY());
        float x = x2 - (x1 * dist);
        return (int) (x / target.getBbHeight() * 100.0f);
    }

    public static int getEntityHitSide(Player host, Entity target) {
        if (host == null || target == null) return 0;
        float angHost = host.yHeadRot;
        float angTarget = target instanceof LivingEntity living ? living.yBodyRot : target.getYRot();
        int result = (int) ((angHost % 360.0f - angTarget % 360.0f) % 360.0f);
        if (result < 0) result += 360;
        return result;
    }

    public static float[] rotateXZByAxis(float z, float x, float rad, float scale) {
        float cosD = Mth.cos(rad);
        float sinD = Mth.sin(rad);
        float[] newPos = new float[]{0.0f, 0.0f};
        newPos[0] = z * cosD + x * sinD;
        newPos[1] = x * cosD - z * sinD;
        newPos[0] = newPos[0] * scale;
        newPos[1] = newPos[1] * scale;
        return newPos;
    }

    public static boolean checkIntNotInArray(int target, int[] host) {
        if (host == null) {
            return true;
        }
        for (int i : host) {
            if (target == i) return false;
        }
        return true;
    }

    public static float[] getLookDegree(double motX, double motY, double motZ, boolean getDegree) {
        double d1 = Math.sqrt(motX * motX + motY * motY + motZ * motZ);
        if (d1 > 1.0E-4) {
            motX /= d1;
            motY /= d1;
            motZ /= d1;
        }
        double f1 = Math.sqrt(motX * motX + motZ * motZ);
        float[] degree = new float[2];
        degree[1] = -((float)Math.atan2(motY, f1));
        degree[0] = -((float)Math.atan2(motX, motZ));
        if (getDegree) {
            degree[0] = degree[0] * 57.29578f;
            degree[1] = degree[1] * 57.29578f;
        }
        return degree;
    }

    public static float[] rotateXYZByYawPitch(float x, float y, float z, float yaw, float pitch, float scale) {
        float cosYaw = Mth.cos(yaw);
        float sinYaw = Mth.sin(yaw);
        float cosPitch = Mth.cos(-pitch);
        float sinPitch = Mth.sin(-pitch);
        float[] newPos = new float[]{x, y, z};
        newPos[1] = y * cosPitch + z * sinPitch;
        newPos[2] = z * cosPitch - y * sinPitch;
        float x2 = newPos[0];
        float z2 = newPos[2];
        newPos[0] = x2 * cosYaw - z2 * sinYaw;
        newPos[2] = z2 * cosYaw + x2 * sinYaw;
        newPos[0] = newPos[0] * scale;
        newPos[1] = newPos[1] * scale;
        newPos[2] = newPos[2] * scale;
        return newPos;
    }
}
