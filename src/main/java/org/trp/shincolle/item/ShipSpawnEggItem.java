package org.trp.shincolle.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ShipSpawnEggItem extends OwnedSpawnEggItem {
    private final ShipClass shipClass;

    public ShipSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, ShipClass shipClass, int primaryColor, int secondaryColor, Item.Properties properties) {
        super(type, primaryColor, secondaryColor, properties);
        this.shipClass = shipClass;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }
}
