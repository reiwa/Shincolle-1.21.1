package org.trp.shincolle.menu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Shincolle.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ShipContainerMenu>> SHIP_MENU = MENUS.register("ship_menu",
            () -> IMenuTypeExtension.create(ShipContainerMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<SmallShipyardMenu>> SMALL_SHIPYARD_MENU = MENUS.register(
        "small_shipyard_menu",
        () -> IMenuTypeExtension.create(SmallShipyardMenu::new)
    );

    public static final DeferredHolder<MenuType<?>, MenuType<LargeShipyardMenu>> LARGE_SHIPYARD_MENU = MENUS.register(
        "large_shipyard_menu",
        () -> IMenuTypeExtension.create(LargeShipyardMenu::new)
    );
}
