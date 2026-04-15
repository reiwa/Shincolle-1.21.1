package org.trp.shincolle.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Shincolle.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SHINCOLLE_TAB = CREATIVE_MODE_TABS.register("shincolle_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.shincolle"))
            .icon(() -> new ItemStack(ModItems.NORTHERN_HIME_SPAWN_EGG.get()))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.DESTROYER_I_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_RO_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_HA_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_NI_SPAWN_EGG.get());
                output.accept(ModItems.CARRIER_WO_SPAWN_EGG.get());
                output.accept(ModItems.BATTLESHIP_TA_SPAWN_EGG.get());
                output.accept(ModItems.BATTLESHIP_RE_SPAWN_EGG.get());
                output.accept(ModItems.TRANSPORT_WA_SPAWN_EGG.get());
                output.accept(ModItems.SUBM_KA_SPAWN_EGG.get());
                output.accept(ModItems.SUBM_YO_SPAWN_EGG.get());
                output.accept(ModItems.SUBM_SO_SPAWN_EGG.get());
                output.accept(ModItems.CARRIER_HIME_SPAWN_EGG.get());
                output.accept(ModItems.AIRFIELD_HIME_SPAWN_EGG.get());
                output.accept(ModItems.ISOLATED_HIME_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_AKATSUKI_SPAWN_EGG.get());
                output.accept(ModItems.HARBOUR_HIME_SPAWN_EGG.get());
                output.accept(ModItems.MIDWAY_HIME_SPAWN_EGG.get());
                output.accept(ModItems.NORTHERN_HIME_SPAWN_EGG.get());
                output.accept(ModItems.CARRIER_W_DEMON_SPAWN_EGG.get());
                output.accept(ModItems.BATTLESHIP_YAMATO_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_IKAZUCHI_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_INAZUMA_SPAWN_EGG.get());
                output.accept(ModItems.CRUISER_TENRYUU_SPAWN_EGG.get());
                output.accept(ModItems.SUBM_U511_SPAWN_EGG.get());
                output.accept(ModItems.CRUISER_ATAGO_SPAWN_EGG.get());
                output.accept(ModItems.CRUISER_TAKAO_SPAWN_EGG.get());
                output.accept(ModItems.BB_HIEI_SPAWN_EGG.get());
                output.accept(ModItems.BB_KONGOU_SPAWN_EGG.get());
                output.accept(ModItems.CA_HIME_SPAWN_EGG.get());
                output.accept(ModItems.BB_HARUNA_SPAWN_EGG.get());
                output.accept(ModItems.BB_KIRISHIMA_SPAWN_EGG.get());
                output.accept(ModItems.SUBM_RO500_SPAWN_EGG.get());
                output.accept(ModItems.CARRIER_AKAGI_SPAWN_EGG.get());
                output.accept(ModItems.HEAVY_CRUISER_RI_SPAWN_EGG.get());
                output.accept(ModItems.HEAVY_CRUISER_NE_SPAWN_EGG.get());
                output.accept(ModItems.BATTLESHIP_RU_SPAWN_EGG.get());
                output.accept(ModItems.BATTLESHIP_HIME_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_HIME_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_SHIMAKAZE_SPAWN_EGG.get());
                output.accept(ModItems.BATTLESHIP_NAGATO_SPAWN_EGG.get());
                output.accept(ModItems.SUBM_HIME_SPAWN_EGG.get());
                output.accept(ModItems.CARRIER_KAGA_SPAWN_EGG.get());
                output.accept(ModItems.DESTROYER_HIBIKI_SPAWN_EGG.get());
                output.accept(ModItems.CRUISER_TATSUTA_SPAWN_EGG.get());
                output.accept(ModItems.SSNH_SPAWN_EGG.get());
                output.accept(ModItems.POINTER_ITEM.get());
                output.accept(ModItems.AMMO_LIGHT.get());
                output.accept(ModItems.AMMO_LIGHT_CONTAINER.get());
                output.accept(ModItems.AMMO_HEAVY.get());
                output.accept(ModItems.AMMO_HEAVY_CONTAINER.get());
                output.accept(ModItems.ABYSS_METAL.get());
                output.accept(ModItems.ABYSS_POLYMETAL.get());
                output.accept(ModItems.GRUDGE.get());
                output.accept(ModItems.GRUDGE_BLOCK.get());
                output.accept(ModItems.GRUDGE_HEAVY_BLOCK.get());
                output.accept(ModItems.POLYMETAL.get());
                output.accept(ModItems.POLYMETAL_ORE.get());
                output.accept(ModItems.POLYMETAL_GRAVEL.get());
            }).build());
}
