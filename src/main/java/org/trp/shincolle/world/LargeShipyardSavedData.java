package org.trp.shincolle.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class LargeShipyardSavedData extends SavedData {
    public static final String SAVE_ID = "shincolle_large_shipyard";
    private static final int MAT_COUNT = 4;

    private int[] matsStock = new int[]{0, 0, 0, 0};

    public LargeShipyardSavedData() {
    }

    public static LargeShipyardSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        LargeShipyardSavedData::new,
                        LargeShipyardSavedData::load
                ),
                SAVE_ID
        );
    }

    private static LargeShipyardSavedData load(CompoundTag tag, HolderLookup.Provider registries) {
        LargeShipyardSavedData data = new LargeShipyardSavedData();
        int[] loaded = tag.getIntArray("MatsStock");
        if (loaded.length >= MAT_COUNT) {
            data.matsStock = new int[]{loaded[0], loaded[1], loaded[2], loaded[3]};
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putIntArray("MatsStock", this.matsStock);
        return tag;
    }

    public int getMatStock(int index) {
        if (index < 0 || index >= MAT_COUNT) return 0;
        return this.matsStock[index];
    }

    public void setMatStock(int index, int value) {
        if (index < 0 || index >= MAT_COUNT) return;
        this.matsStock[index] = value;
        this.setDirty();
    }

    public void addMatStock(int index, int amount) {
        if (index < 0 || index >= MAT_COUNT) return;
        this.matsStock[index] += amount;
        this.setDirty();
    }

    public int[] getMatsStockCopy() {
        return new int[]{matsStock[0], matsStock[1], matsStock[2], matsStock[3]};
    }

    public void setMatsStock(int[] mats) {
        if (mats == null || mats.length < MAT_COUNT) return;
        this.matsStock = new int[]{mats[0], mats[1], mats[2], mats[3]};
        this.setDirty();
    }
}
