package org.trp.shincolle.event;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack;
import org.trp.shincolle.init.ModDataComponents;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.ShipTankItem;

public final class ModCapabilityEvents {
    private ModCapabilityEvents() {
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.FluidHandler.ITEM,
                (stack, context) -> new FluidHandlerItemStack(
                        ModDataComponents.SHIPTANK_FLUID,
                        stack,
                        ShipTankItem.getCapacity(stack)),
                ModItems.SHIP_TANK.get());
    }
}