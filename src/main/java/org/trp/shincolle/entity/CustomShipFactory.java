package org.trp.shincolle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import org.trp.shincolle.custom.CustomShipRegistry;

public final class CustomShipFactory {
    private CustomShipFactory() {
    }

    public static EntityCustomShip create(EntityType<? extends TamableAnimal> type, Level level) {
        if (CustomShipRegistry.isGeckoLibLoaded()) {
            return GeoHolder.create(type, level);
        }
        return new EntityCustomShip(type, level);
    }

    private static final class GeoHolder {
        static EntityCustomShip create(EntityType<? extends TamableAnimal> type, Level level) {
            return new EntityCustomShipGeo(type, level);
        }
    }
}
