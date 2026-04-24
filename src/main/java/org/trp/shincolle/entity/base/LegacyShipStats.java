package org.trp.shincolle.entity.base;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LegacyShipStats {
    private static final float LV_SCALE_DEF = 0.00133F;
    private static final float LV_SCALE_SPD = 0.004F;
    private static final float LV_SCALE_MOV = 0.002F;
    private static final float LV_SCALE_HIT = 0.02F;
    private static final float LV_SCALE_ATK = 0.133F;

    private static final float[] DEFAULT_BASE = new float[]{20.0f, 3.0f, 0.05f, 1.0f, 0.5f, 6.0f, 0.3f, 0.25f, 0.11f, 0.5f, 1.0f, 0.4f};

    private static final int[] BASE_ATTACK_SPEED = new int[]{40, 80, 120, 100, 100};
    private static final int[] FIXED_ATTACK_DELAY = new int[]{0, 20, 50, 35, 35};

    private static final float[] ATTR_LIMITS = new float[]{
            -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 0.8f, 4.0f, 0.6f, 64.0f,
            0.9f, 0.9f, 0.9f, 0.9f, -1.0f, -1.0f, 0.75f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f
    };

    private static final int MODERN_LIMIT = 3;

    private static final Map<Integer, float[]> SHIP_ATTR_MAP = new HashMap<>();

    static {
        SHIP_ATTR_MAP.put(-10, new float[]{20.0f, 3.0f, 0.08f, 1.0f, 0.38f, 6.0f, 0.3f, 0.25f, 0.1f, 0.5f, 0.8f, 0.5f});
        SHIP_ATTR_MAP.put(-11, new float[]{40.0f, 6.0f, 0.2f, 1.0f, 0.3f, 8.0f, 0.6f, 0.45f, 0.2f, 0.65f, 0.6f, 0.6f});
        SHIP_ATTR_MAP.put(-12, new float[]{35.0f, 4.0f, 0.16f, 1.0f, 0.32f, 8.0f, 0.5f, 0.35f, 0.16f, 0.6f, 0.7f, 0.6f});
        SHIP_ATTR_MAP.put(-13, new float[]{25.0f, 4.0f, 0.1f, 1.0f, 0.4f, 6.0f, 0.35f, 0.35f, 0.1f, 0.6f, 0.8f, 0.5f});
        SHIP_ATTR_MAP.put(-14, new float[]{30.0f, 5.0f, 0.12f, 1.0f, 0.32f, 15.0f, 0.4f, 0.4f, 0.12f, 0.55f, 0.7f, 0.8f});
        SHIP_ATTR_MAP.put(-15, new float[]{20.0f, 3.0f, 0.08f, 1.0f, 0.5f, 5.0f, 0.3f, 0.3f, 0.08f, 0.55f, 1.0f, 0.45f});
        SHIP_ATTR_MAP.put(-16, new float[]{15.0f, 8.0f, 0.05f, 1.0f, 0.3f, 4.0f, 0.25f, 0.6f, 0.05f, 0.6f, 0.6f, 0.4f});
        SHIP_ATTR_MAP.put(0, new float[]{20.0f, 3.0f, 0.05f, 1.0f, 0.5f, 6.0f, 0.3f, 0.25f, 0.11f, 0.5f, 1.0f, 0.4f});
        SHIP_ATTR_MAP.put(1, new float[]{22.0f, 4.0f, 0.06f, 1.0f, 0.5f, 6.0f, 0.32f, 0.28f, 0.12f, 0.5f, 1.0f, 0.4f});
        SHIP_ATTR_MAP.put(2, new float[]{24.0f, 3.0f, 0.07f, 1.0f, 0.5f, 6.0f, 0.34f, 0.25f, 0.13f, 0.5f, 1.0f, 0.4f});
        SHIP_ATTR_MAP.put(3, new float[]{28.0f, 4.0f, 0.09f, 1.0f, 0.5f, 6.0f, 0.36f, 0.28f, 0.15f, 0.5f, 1.0f, 0.4f});
        SHIP_ATTR_MAP.put(9, new float[]{58.0f, 14.0f, 0.18f, 1.0f, 0.42f, 9.0f, 0.48f, 0.4f, 0.21f, 0.56f, 0.84f, 0.5f});
        SHIP_ATTR_MAP.put(10, new float[]{62.0f, 15.0f, 0.19f, 1.0f, 0.42f, 9.0f, 0.5f, 0.42f, 0.22f, 0.56f, 0.84f, 0.5f});
        SHIP_ATTR_MAP.put(12, new float[]{85.0f, 25.0f, 0.21f, 1.0f, 0.36f, 16.0f, 0.65f, 0.6f, 0.23f, 0.6f, 0.72f, 0.6f});
        SHIP_ATTR_MAP.put(13, new float[]{95.0f, 30.0f, 0.3f, 1.0f, 0.32f, 12.0f, 0.85f, 0.65f, 0.27f, 0.63f, 0.66f, 0.5f});
        SHIP_ATTR_MAP.put(14, new float[]{84.0f, 19.0f, 0.23f, 1.2f, 0.42f, 10.0f, 0.65f, 0.55f, 0.24f, 0.7f, 0.84f, 0.5f});
        SHIP_ATTR_MAP.put(15, new float[]{120.0f, 27.0f, 0.25f, 1.1f, 0.36f, 12.0f, 0.8f, 0.65f, 0.25f, 0.63f, 0.72f, 0.5f});
        SHIP_ATTR_MAP.put(16, new float[]{90.0f, 3.0f, 0.1f, 1.0f, 0.3f, 8.0f, 0.7f, 0.25f, 0.16f, 0.35f, 0.6f, 0.3f});
        SHIP_ATTR_MAP.put(17, new float[]{40.0f, 28.0f, 0.09f, 0.8f, 0.3f, 5.0f, 0.35f, 0.67f, 0.14f, 0.7f, 0.6f, 0.3f});
        SHIP_ATTR_MAP.put(18, new float[]{36.0f, 30.0f, 0.1f, 0.8f, 0.3f, 5.0f, 0.33f, 0.7f, 0.16f, 0.7f, 0.6f, 0.3f});
        SHIP_ATTR_MAP.put(19, new float[]{34.0f, 38.0f, 0.12f, 0.8f, 0.28f, 5.5f, 0.3f, 0.8f, 0.18f, 0.7f, 0.6f, 0.3f});
        SHIP_ATTR_MAP.put(20, new float[]{180.0f, 40.0f, 0.28f, 1.0f, 0.45f, 22.0f, 0.85f, 0.75f, 0.26f, 0.62f, 0.85f, 0.7f});
        SHIP_ATTR_MAP.put(21, new float[]{240.0f, 16.0f, 0.32f, 1.0f, 0.3f, 26.0f, 1.2f, 0.45f, 0.28f, 0.6f, 0.6f, 0.8f});
        SHIP_ATTR_MAP.put(26, new float[]{220.0f, 42.0f, 0.4f, 1.0f, 0.4f, 16.0f, 1.0f, 0.8f, 0.32f, 0.73f, 0.8f, 0.6f});
        SHIP_ATTR_MAP.put(27, new float[]{90.0f, 22.0f, 0.2f, 1.0f, 0.52f, 12.0f, 0.55f, 0.5f, 0.22f, 0.6f, 1.0f, 0.5f});
        SHIP_ATTR_MAP.put(28, new float[]{260.0f, 14.0f, 0.36f, 0.8f, 0.2f, 24.0f, 1.35f, 0.4f, 0.3f, 0.6f, 0.4f, 0.8f});
        SHIP_ATTR_MAP.put(29, new float[]{225.0f, 13.0f, 0.34f, 0.9f, 0.22f, 24.0f, 1.3f, 0.4f, 0.29f, 0.6f, 0.44f, 0.8f});
        SHIP_ATTR_MAP.put(30, new float[]{350.0f, 22.0f, 0.45f, 0.8f, 0.25f, 30.0f, 1.5f, 0.5f, 0.34f, 0.6f, 0.4f, 0.8f});
        SHIP_ATTR_MAP.put(31, new float[]{210.0f, 13.0f, 0.3f, 0.8f, 0.32f, 22.0f, 1.15f, 0.35f, 0.27f, 0.6f, 0.64f, 0.8f});
        SHIP_ATTR_MAP.put(33, new float[]{190.0f, 45.0f, 0.4f, 1.0f, 0.42f, 25.0f, 1.0f, 0.95f, 0.32f, 0.75f, 0.84f, 0.8f});
        SHIP_ATTR_MAP.put(36, new float[]{38.0f, 11.0f, 0.12f, 1.0f, 0.6f, 9.0f, 0.35f, 0.4f, 0.16f, 0.55f, 1.2f, 0.46f});
        SHIP_ATTR_MAP.put(37, new float[]{135.0f, 40.0f, 0.26f, 1.0f, 0.32f, 14.0f, 0.85f, 0.8f, 0.25f, 0.63f, 0.64f, 0.6f});
        SHIP_ATTR_MAP.put(38, new float[]{28.0f, 30.0f, 0.07f, 0.8f, 0.3f, 10.0f, 0.3f, 0.7f, 0.13f, 0.7f, 0.6f, 0.4f});
        SHIP_ATTR_MAP.put(39, new float[]{32.0f, 32.0f, 0.1f, 0.8f, 0.3f, 11.0f, 0.33f, 0.75f, 0.16f, 0.7f, 0.6f, 0.4f});
        SHIP_ATTR_MAP.put(44, new float[]{75.0f, 45.0f, 0.15f, 1.0f, 0.3f, 7.5f, 0.5f, 0.9f, 0.2f, 0.7f, 0.6f, 0.4f});
        SHIP_ATTR_MAP.put(46, new float[]{150.0f, 55.0f, 0.36f, 1.0f, 0.3f, 20.0f, 1.0f, 1.0f, 0.3f, 0.7f, 0.6f, 0.7f});
        SHIP_ATTR_MAP.put(47, new float[]{70.0f, 22.0f, 0.21f, 1.0f, 0.34f, 16.0f, 0.65f, 0.6f, 0.23f, 0.6f, 0.72f, 0.6f});
        SHIP_ATTR_MAP.put(48, new float[]{75.0f, 22.0f, 0.2f, 1.0f, 0.32f, 16.0f, 0.65f, 0.6f, 0.23f, 0.6f, 0.72f, 0.6f});
        SHIP_ATTR_MAP.put(49, new float[]{180.0f, 35.0f, 0.32f, 1.0f, 0.45f, 14.0f, 0.85f, 0.77f, 0.29f, 0.65f, 0.9f, 0.6f});
        SHIP_ATTR_MAP.put(51, new float[]{32.0f, 9.0f, 0.09f, 1.0f, 0.5f, 11.0f, 0.32f, 0.38f, 0.12f, 0.5f, 1.0f, 0.5f});
        SHIP_ATTR_MAP.put(52, new float[]{40.0f, 7.0f, 0.11f, 1.0f, 0.5f, 10.0f, 0.38f, 0.36f, 0.14f, 0.5f, 1.0f, 0.48f});
        SHIP_ATTR_MAP.put(53, new float[]{30.0f, 5.0f, 0.09f, 1.0f, 0.5f, 9.0f, 0.3f, 0.32f, 0.12f, 0.5f, 1.0f, 0.46f});
        SHIP_ATTR_MAP.put(54, new float[]{30.0f, 5.0f, 0.09f, 1.0f, 0.5f, 9.0f, 0.3f, 0.32f, 0.12f, 0.5f, 1.0f, 0.46f});
        SHIP_ATTR_MAP.put(56, new float[]{42.0f, 13.0f, 0.16f, 1.0f, 0.42f, 8.0f, 0.4f, 0.4f, 0.2f, 0.6f, 0.9f, 0.4f});
        SHIP_ATTR_MAP.put(57, new float[]{42.0f, 13.0f, 0.16f, 1.0f, 0.42f, 8.0f, 0.4f, 0.4f, 0.2f, 0.6f, 0.9f, 0.4f});
        SHIP_ATTR_MAP.put(58, new float[]{62.0f, 15.0f, 0.18f, 1.0f, 0.42f, 9.0f, 0.5f, 0.42f, 0.22f, 0.56f, 0.84f, 0.5f});
        SHIP_ATTR_MAP.put(59, new float[]{62.0f, 15.0f, 0.18f, 1.0f, 0.42f, 9.0f, 0.5f, 0.42f, 0.22f, 0.56f, 0.84f, 0.5f});
        SHIP_ATTR_MAP.put(60, new float[]{90.0f, 28.0f, 0.36f, 1.0f, 0.42f, 12.0f, 0.7f, 0.6f, 0.24f, 0.6f, 0.84f, 0.55f});
        SHIP_ATTR_MAP.put(61, new float[]{90.0f, 28.0f, 0.36f, 1.0f, 0.42f, 12.0f, 0.7f, 0.6f, 0.24f, 0.6f, 0.84f, 0.55f});
        SHIP_ATTR_MAP.put(62, new float[]{90.0f, 28.0f, 0.36f, 1.0f, 0.42f, 12.0f, 0.7f, 0.6f, 0.24f, 0.6f, 0.84f, 0.55f});
        SHIP_ATTR_MAP.put(63, new float[]{90.0f, 28.0f, 0.36f, 1.0f, 0.42f, 12.0f, 0.7f, 0.6f, 0.24f, 0.6f, 0.84f, 0.55f});
        SHIP_ATTR_MAP.put(72, new float[]{55.0f, 34.0f, 0.1f, 1.0f, 0.4f, 5.5f, 0.4f, 0.75f, 0.16f, 0.7f, 0.7f, 0.4f});
    }

    private final byte[] bonus = new byte[6];
    private final float[] raw = new float[21];
    private final float[] buffed = new float[21];

    public void copyBonusFrom(byte[] data) {
        if (data == null) {
            return;
        }
        int len = Math.min(this.bonus.length, data.length);
        System.arraycopy(data, 0, this.bonus, 0, len);
        for (int i = 0; i < len; i++) {
            this.bonus[i] = (byte) Mth.clamp(this.bonus[i], 0, MODERN_LIMIT);
        }
    }

    public byte[] copyBonus() {
        return this.bonus.clone();
    }

    public int getBonus(int index) {
        if (index < 0 || index >= this.bonus.length) {
            return 0;
        }
        return this.bonus[index];
    }

    public void setBonus(int index, int value) {
        if (index < 0 || index >= this.bonus.length) {
            return;
        }
        this.bonus[index] = (byte) Mth.clamp(value, 0, MODERN_LIMIT);
    }

    public boolean addBonusRandom(Random random) {
        int pick = random.nextInt(this.bonus.length);
        if (this.bonus[pick] < MODERN_LIMIT) {
            this.bonus[pick]++;
            return true;
        }
        for (int i = 0; i < this.bonus.length; i++) {
            if (this.bonus[i] < MODERN_LIMIT) {
                this.bonus[i]++;
                return true;
            }
        }
        return false;
    }

    public void recalculate(int shipClass, int level, float[] equipBonuses) {
        float[] base = SHIP_ATTR_MAP.getOrDefault(shipClass, DEFAULT_BASE);
        float[] type = new float[]{base[6], base[7], base[8], base[9], base[10], base[11]};

        float safeLevel = Math.max(1, level);

        zero(raw);
        zero(buffed);

        raw[0] = base[0] + (this.bonus[0] + 1.0F) * safeLevel * type[0];
        raw[5] = base[2] + (this.bonus[2] + 1.0F) * safeLevel * LV_SCALE_DEF * type[2];
        raw[6] = base[3] + (this.bonus[3] + 1.0F) * safeLevel * LV_SCALE_SPD * type[3];
        raw[7] = base[4] + (this.bonus[4] + 1.0F) * safeLevel * LV_SCALE_MOV * type[4];
        raw[8] = base[5] + (this.bonus[5] + 1.0F) * safeLevel * LV_SCALE_HIT * type[5];

        float baseAtk = base[1] + (this.bonus[1] + 1.0F) * safeLevel * LV_SCALE_ATK * type[1];
        raw[1] = baseAtk;
        raw[2] = baseAtk * 3.0F;
        raw[3] = baseAtk;
        raw[4] = baseAtk * 3.0F;

        raw[16] = 1.0F;
        raw[17] = 1.0F;
        raw[18] = 1.0F;
        raw[19] = 1.0F;
        raw[20] = safeLevel * 0.005F;

        if (equipBonuses != null) {
            int len = Math.min(raw.length, equipBonuses.length);
            for (int i = 0; i < len; i++) {
                raw[i] += equipBonuses[i];
            }
        }

        System.arraycopy(raw, 0, buffed, 0, raw.length);
        applyLimits(buffed);
    }

    private static void applyLimits(float[] data) {
        for (int i = 0; i < data.length && i < ATTR_LIMITS.length; i++) {
            float limit = ATTR_LIMITS[i];
            if (limit >= 0.0F && data[i] > limit) {
                data[i] = limit;
            }
            if (data[i] < 0.0F) {
                data[i] = 0.0F;
            }
        }

        if (data[0] < 1.0F) data[0] = 1.0F;
        if (data[1] < 1.0F) data[1] = 1.0F;
        if (data[2] < 1.0F) data[2] = 1.0F;
        if (data[3] < 1.0F) data[3] = 1.0F;
        if (data[4] < 1.0F) data[4] = 1.0F;
        if (data[8] < 1.0F) data[8] = 1.0F;
        if (data[6] < 0.2F) data[6] = 0.2F;
    }

    private static void zero(float[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = 0.0F;
        }
    }

    public float getMaxHealth() {
        return buffed[0];
    }

    public float getFirepower() {
        return buffed[1];
    }

    public float getArmor() {
        return buffed[5];
    }

    public float getReloadSpeed() {
        return Math.max(0.001F, buffed[6]);
    }

    public float getMoveSpeed() {
        return buffed[7];
    }

    public float getAttackRange() {
        return buffed[8];
    }

    public float getDefenseReducedDamage(float incoming, RandomSource random) {
        float reduced = incoming * (1.0F - getArmor() + (random.nextFloat() * 0.5F - 0.25F));
        if (reduced > 0.0F && reduced < 1.0F) {
            reduced = 1.0F;
        } else if (reduced < 0.0F) {
            reduced = 0.0F;
        }
        return reduced;
    }

    public int getMeleeDelay() {
        return getAttackDelay(getReloadSpeed(), 0);
    }

    public int getLightDelay() {
        return getAttackDelay(getReloadSpeed(), 1);
    }

    public int getHeavyDelay() {
        return getAttackDelay(getReloadSpeed(), 2);
    }

    public static int getAttackDelay(float attackSpeed, int type) {
        float safe = Math.max(0.01F, attackSpeed);
        if (type >= 0 && type < BASE_ATTACK_SPEED.length) {
            return (int) (BASE_ATTACK_SPEED[type] / safe) + FIXED_ATTACK_DELAY[type];
        }
        return 40;
    }

    public float getBuffedAttr(int index) {
        if (index < 0 || index >= this.buffed.length) {
            return 0.0F;
        }
        return this.buffed[index];
    }
}
