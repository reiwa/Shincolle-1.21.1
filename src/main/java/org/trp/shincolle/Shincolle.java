package org.trp.shincolle;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import org.trp.shincolle.event.ClientModEventBusEvents;
import org.trp.shincolle.event.ModBusEvents;
import org.trp.shincolle.event.ModCapabilityEvents;
import org.trp.shincolle.init.*;
import org.trp.shincolle.menu.ModMenus;
import org.trp.shincolle.network.ModNetwork;

@Mod(Shincolle.MODID)
public class Shincolle {
    public static final String MODID = "shincolle";

    public Shincolle(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.register(ModBusEvents.class);
        modEventBus.register(Config.class);
        modEventBus.register(ModNetwork.class);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.register(ClientModEventBusEvents.class);
        }

        ModItems.ITEMS.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModDataAttachments.ATTACHMENT_TYPES.register(modEventBus);
        modEventBus.addListener(ModCapabilityEvents::registerCapabilities);
    }
}

