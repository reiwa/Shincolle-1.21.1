package org.trp.shincolle.crafting;

import net.minecraft.util.StringRepresentable;
import com.mojang.serialization.Codec;

public enum ShipyardMaterial implements StringRepresentable {
    GRUDGE("grudge", 0),
    ABYSS_METAL("abyss_metal", 1),
    AMMO("ammo", 2),
    ABYSS_POLYMETAL("abyss_polymetal", 3),
    NONE("none", -1);

    public static final Codec<ShipyardMaterial> CODEC = StringRepresentable.fromEnum(ShipyardMaterial::values);

    private final String name;
    private final int index;

    ShipyardMaterial(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public static ShipyardMaterial fromIndex(int index) {
        for (ShipyardMaterial mat : values()) {
            if (mat.index == index) {
                return mat;
            }
        }
        return NONE;
    }
}
