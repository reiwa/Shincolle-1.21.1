package org.trp.shincolle;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.trp.shincolle.init.*;
import org.trp.shincolle.menu.ModMenus;

@Mod(Shincolle.MODID)
public class Shincolle {
    public static final String MODID = "shincolle";

    public Shincolle(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
    }
}
