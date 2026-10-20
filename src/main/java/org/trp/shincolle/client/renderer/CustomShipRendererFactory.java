package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.custom.CustomShipRegistry;
import org.trp.shincolle.entity.EntityCustomShip;

public final class CustomShipRendererFactory {
    private CustomShipRendererFactory() {
    }

    public static EntityRenderer<EntityCustomShip> create(EntityRendererProvider.Context context) {
        if (CustomShipRegistry.isGeckoLibLoaded()) {
            return GeoHolder.create(context);
        }
        return new EmptyRenderer(context);
    }

    private static final class GeoHolder {
        @SuppressWarnings({"unchecked", "rawtypes"})
        static EntityRenderer<EntityCustomShip> create(EntityRendererProvider.Context context) {
            return (EntityRenderer) new CustomShipRenderer(context);
        }
    }

    private static final class EmptyRenderer extends EntityRenderer<EntityCustomShip> {
        EmptyRenderer(EntityRendererProvider.Context context) {
            super(context);
        }

        @Override
        public ResourceLocation getTextureLocation(EntityCustomShip entity) {
            return ResourceLocation.withDefaultNamespace("textures/misc/white.png");
        }

        @Override
        public boolean shouldRender(EntityCustomShip entity, net.minecraft.client.renderer.culling.Frustum frustum, double x, double y, double z) {
            return false;
        }
    }
}
