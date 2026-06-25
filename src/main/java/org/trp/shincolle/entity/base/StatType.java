package org.trp.shincolle.entity.base;

public enum StatType {
    MAX_HP(0),
    FIREPOWER(1),
    HEAVY_FIREPOWER(2),
    LIGHT_AIRCRAFT_FIREPOWER(3),
    HEAVY_AIRCRAFT_FIREPOWER(4),
    ARMOR(5),
    RELOAD_SPEED(6),
    MOVE_SPEED(7),
    ATTACK_RANGE(8),
    CRITICAL_RATE(9),
    DOUBLE_HIT_RATE(10),
    TRIPLE_HIT_RATE(11),
    ACCURACY(12),
    ANTI_AIR(13),
    ANTI_SUB(14),
    DODGE(15),
    XP_MULTIPLIER(16),
    FUEL_CONSUMPTION(17),
    AMMO_CONSUMPTION(18),
    HEALING_MODIFIER(19),
    KNOCKBACK_RESISTANCE(20);

    private final int index;

    StatType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static StatType fromIndex(int index) {
        if (index < 0 || index >= values().length) {
            return MAX_HP;
        }
        return values()[index];
    }
}
