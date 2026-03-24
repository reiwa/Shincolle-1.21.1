package org.trp.shincolle.client.renderer;

import net.minecraft.client.model.EntityModel;
import org.trp.shincolle.client.model.ShipModelBaseAdv;
import org.trp.shincolle.entity.base.EntityShipBase;

public final class LegacyScale {
    private static final float DEFAULT = 0.34f;

    private LegacyScale() {
    }

    public static float getScale(EntityShipBase entity, EntityModel<?> model) {
        if (model instanceof ShipModelBaseAdv<?> shipModel) {
            return shipModel.getLegacyScale(entity);
        }
        return DEFAULT;
    }
}
