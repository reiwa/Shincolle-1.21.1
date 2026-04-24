package org.trp.shincolle.init;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.item.GrudgeItem;
import org.trp.shincolle.item.CombatRationItem;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.item.PointerItem;
import org.trp.shincolle.item.RandomShipSpawnEggItem;
import org.trp.shincolle.item.ShipClass;
import org.trp.shincolle.item.ShipTankItem;
import org.trp.shincolle.item.ShipSpawnEggItem;
import org.trp.shincolle.item.SmallShipyardBlockItem;
import org.trp.shincolle.item.TrainingBookItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Shincolle.MODID);

    private static final FoodProperties GRUDGE_FOOD = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.0F)
            .alwaysEdible()
            .build();

        private static final int[] EQUIP_AIRPLANE_TYPES = new int[]{6, 6, 6, 7, 8, 8, 8, 9, 9, 10, 10, 11, 11, 12, 13, 7, 9, 11, 9, 11, 11, 9};
        private static final int[] EQUIP_AIRPLANE_MODELS = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 0, 1, 2, 1, 2, 2, 1};
        private static final int[] EQUIP_AMMO_TYPES = new int[]{28, 29, 28, 29, 29, 29, 29, 29, 29};
        private static final int[] EQUIP_ARMOR_TYPES = new int[]{18, 19, 18, 18, 19, 18, 19};
        private static final int[] EQUIP_CANNON_TYPES = new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 0, 1, 3, 3};
        private static final int[] EQUIP_CANNON_MODELS = new int[]{0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0, 1, 2, 2};
        private static final int[] EQUIP_CATAPULT_TYPES = new int[]{22, 22, 23, 23};
        private static final int[] EQUIP_COMPASS_TYPES = new int[]{25};
        private static final int[] EQUIP_DRUM_TYPES = new int[]{24, 24, 24};
        private static final int[] EQUIP_DRUM_MODELS = new int[]{0, 1, 2};
        private static final int[] EQUIP_FLARE_TYPES = new int[]{26};
        private static final int[] EQUIP_MACHINEGUN_TYPES = new int[]{20, 20, 20, 20, 21, 21, 21};
        private static final int[] EQUIP_RADAR_TYPES = new int[]{14, 14, 14, 14, 14, 15, 15, 15, 15};
        private static final int[] EQUIP_SEARCHLIGHT_TYPES = new int[]{27};
        private static final int[] EQUIP_TORPEDO_TYPES = new int[]{4, 4, 4, 5, 5, 5, 5};
        private static final int[] EQUIP_TURBINE_TYPES = new int[]{16, 16, 17, 17, 17};

    public static final DeferredItem<Item> SHIPSPAWNEGGS = ITEMS.register("shipspawneggs",
            () -> new RandomShipSpawnEggItem(ModEntities.DESTROYER_I, ShipClass.DESTROYER, false,
                    0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SHIPSPAWNEGGL = ITEMS.register("shipspawneggl",
            () -> new RandomShipSpawnEggItem(ModEntities.DESTROYER_HIME, ShipClass.PRINCESS, true,
                    0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_I_SPAWN_EGG = ITEMS.register("destroyer_i_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_I, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_RO_SPAWN_EGG = ITEMS.register("destroyer_ro_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_RO, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_HA_SPAWN_EGG = ITEMS.register("destroyer_ha_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_HA, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_NI_SPAWN_EGG = ITEMS.register("destroyer_ni_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_NI, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> HEAVY_CRUISER_RI_SPAWN_EGG = ITEMS.register("heavy_cruiser_ri_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.HEAVY_CRUISER_RI, ShipClass.HEAVY_CRUISER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> HEAVY_CRUISER_NE_SPAWN_EGG = ITEMS.register("heavy_cruiser_ne_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.HEAVY_CRUISER_NE, ShipClass.HEAVY_CRUISER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CARRIER_WO_SPAWN_EGG = ITEMS.register("carrier_wo_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CARRIER_WO, ShipClass.AIRCRAFT_CARRIER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BATTLESHIP_RU_SPAWN_EGG = ITEMS.register("battleship_ru_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BATTLESHIP_RU, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BATTLESHIP_TA_SPAWN_EGG = ITEMS.register("battleship_ta_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BATTLESHIP_TA, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BATTLESHIP_RE_SPAWN_EGG = ITEMS.register("battleship_re_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BATTLESHIP_RE, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> TRANSPORT_WA_SPAWN_EGG = ITEMS.register("transport_wa_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.TRANSPORT_WA, ShipClass.AUXILIARY_OILER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SUBM_KA_SPAWN_EGG = ITEMS.register("subm_ka_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.SUBM_KA, ShipClass.SUBMARINE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SUBM_YO_SPAWN_EGG = ITEMS.register("subm_yo_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.SUBM_YO, ShipClass.SUBMARINE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SUBM_SO_SPAWN_EGG = ITEMS.register("subm_so_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.SUBM_SO, ShipClass.SUBMARINE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_HIME_SPAWN_EGG = ITEMS.register("destroyer_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CA_HIME_SPAWN_EGG = ITEMS.register("ca_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CA_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> AIRFIELD_HIME_SPAWN_EGG = ITEMS.register("airfield_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.AIRFIELD_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BATTLESHIP_HIME_SPAWN_EGG = ITEMS.register("battleship_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BATTLESHIP_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CARRIER_HIME_SPAWN_EGG = ITEMS.register("carrier_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CARRIER_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> HARBOUR_HIME_SPAWN_EGG = ITEMS.register("harbour_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.HARBOUR_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> ISOLATED_HIME_SPAWN_EGG = ITEMS.register("isolated_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.ISOLATED_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> MIDWAY_HIME_SPAWN_EGG = ITEMS.register("midway_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.MIDWAY_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> NORTHERN_HIME_SPAWN_EGG = ITEMS.register("northern_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.NORTHERN_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SUBM_HIME_SPAWN_EGG = ITEMS.register("subm_hime_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.SUBM_HIME, ShipClass.PRINCESS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SSNH_SPAWN_EGG = ITEMS.register("ssnh_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.SSNH, ShipClass.SUBMARINE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CARRIER_W_DEMON_SPAWN_EGG = ITEMS.register("carrier_w_demon_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CARRIER_W_DEMON, ShipClass.DESTROYER_ESCORT, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_AKATSUKI_SPAWN_EGG = ITEMS.register("destroyer_akatsuki_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_AKATSUKI, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_HIBIKI_SPAWN_EGG = ITEMS.register("destroyer_hibiki_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_HIBIKI, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_IKAZUCHI_SPAWN_EGG = ITEMS.register("destroyer_ikazuchi_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_IKAZUCHI, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_INAZUMA_SPAWN_EGG = ITEMS.register("destroyer_inazuma_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_INAZUMA, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> DESTROYER_SHIMAKAZE_SPAWN_EGG = ITEMS.register("destroyer_shimakaze_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.DESTROYER_SHIMAKAZE, ShipClass.DESTROYER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CRUISER_TENRYUU_SPAWN_EGG = ITEMS.register("cruiser_tenryuu_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CRUISER_TENRYUU, ShipClass.LIGHT_CRUISER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CRUISER_TATSUTA_SPAWN_EGG = ITEMS.register("cruiser_tatsuta_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CRUISER_TATSUTA, ShipClass.LIGHT_CRUISER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CRUISER_TAKAO_SPAWN_EGG = ITEMS.register("cruiser_takao_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CRUISER_TAKAO, ShipClass.HEAVY_CRUISER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CRUISER_ATAGO_SPAWN_EGG = ITEMS.register("cruiser_atago_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CRUISER_ATAGO, ShipClass.HEAVY_CRUISER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CARRIER_KAGA_SPAWN_EGG = ITEMS.register("carrier_kaga_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CARRIER_KAGA, ShipClass.AIRCRAFT_CARRIER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> CARRIER_AKAGI_SPAWN_EGG = ITEMS.register("carrier_akagi_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.CARRIER_AKAGI, ShipClass.AIRCRAFT_CARRIER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BB_KONGOU_SPAWN_EGG = ITEMS.register("bb_kongou_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BB_KONGOU, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BB_HIEI_SPAWN_EGG = ITEMS.register("bb_hiei_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BB_HIEI, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BB_HARUNA_SPAWN_EGG = ITEMS.register("bb_haruna_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BB_HARUNA, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BB_KIRISHIMA_SPAWN_EGG = ITEMS.register("bb_kirishima_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BB_KIRISHIMA, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BATTLESHIP_NAGATO_SPAWN_EGG = ITEMS.register("battleship_nagato_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BATTLESHIP_NAGATO, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> BATTLESHIP_YAMATO_SPAWN_EGG = ITEMS.register("battleship_yamato_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.BATTLESHIP_YAMATO, ShipClass.BATTLESHIP, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SUBM_U511_SPAWN_EGG = ITEMS.register("subm_u511_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.SUBM_U511, ShipClass.SUBMARINE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> SUBM_RO500_SPAWN_EGG = ITEMS.register("subm_ro500_spawn_egg",
            () -> new ShipSpawnEggItem(ModEntities.SUBM_RO500, ShipClass.SUBMARINE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> POINTER_ITEM = ITEMS.register("pointer_item",
            () -> new PointerItem(new Item.Properties().attributes(
                    ItemAttributeModifiers.builder()
                            .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "pointer_reach"), 100.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                            .build()
            )));

    public static final DeferredItem<Item> COMBAT_RATION = ITEMS.register("combatration",
            () -> new CombatRationItem(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> INSTANT_CON_MAT = ITEMS.register("instantconmat",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> KAITAI_HAMMER = ITEMS.register("kaitaihammer",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MARRIAGE_RING = ITEMS.register("marriagering",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> EQUIP_AIRPLANE = ITEMS.register("equipairplane",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipAirplane", EQUIP_AIRPLANE_TYPES, EQUIP_AIRPLANE_MODELS));

    public static final DeferredItem<Item> EQUIP_AMMO = ITEMS.register("equipammo",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipAmmo", EQUIP_AMMO_TYPES));

    public static final DeferredItem<Item> EQUIP_ARMOR = ITEMS.register("equiparmor",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipArmor", EQUIP_ARMOR_TYPES));

    public static final DeferredItem<Item> EQUIP_CANNON = ITEMS.register("equipcannon",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipCannon", EQUIP_CANNON_TYPES, EQUIP_CANNON_MODELS));

    public static final DeferredItem<Item> EQUIP_CATAPULT = ITEMS.register("equipcatapult",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipCatapult", EQUIP_CATAPULT_TYPES));

    public static final DeferredItem<Item> EQUIP_COMPASS = ITEMS.register("equipcompass",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipCompass", EQUIP_COMPASS_TYPES));

    public static final DeferredItem<Item> EQUIP_DRUM = ITEMS.register("equipdrum",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipDrum", EQUIP_DRUM_TYPES, EQUIP_DRUM_MODELS));

    public static final DeferredItem<Item> SHIP_TANK = ITEMS.register("shiptank",
            () -> new ShipTankItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> EQUIP_FLARE = ITEMS.register("equipflare",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipFlare", EQUIP_FLARE_TYPES));

    public static final DeferredItem<Item> EQUIP_MACHINEGUN = ITEMS.register("equipmachinegun",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipMachinegun", EQUIP_MACHINEGUN_TYPES));

    public static final DeferredItem<Item> EQUIP_RADAR = ITEMS.register("equipradar",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipRadar", EQUIP_RADAR_TYPES));

    public static final DeferredItem<Item> EQUIP_SEARCHLIGHT = ITEMS.register("equipsearchlight",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipSearchlight", EQUIP_SEARCHLIGHT_TYPES));

    public static final DeferredItem<Item> EQUIP_TORPEDO = ITEMS.register("equiptorpedo",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipTorpedo", EQUIP_TORPEDO_TYPES));

    public static final DeferredItem<Item> EQUIP_TURBINE = ITEMS.register("equipturbine",
            () -> new LegacyEquipItem(new Item.Properties(), "EquipTurbine", EQUIP_TURBINE_TYPES));

    public static final DeferredItem<Item> TRAINING_BOOK = ITEMS.register("trainingbook",
            () -> new TrainingBookItem(new Item.Properties()));

    public static final DeferredItem<Item> AMMO_LIGHT = ITEMS.register("ammo",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AMMO_LIGHT_CONTAINER = ITEMS.register("ammo1",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AMMO_HEAVY = ITEMS.register("ammo2",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AMMO_HEAVY_CONTAINER = ITEMS.register("ammo3",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GRUDGE = ITEMS.register("grudge",
            () -> new GrudgeItem(new Item.Properties().food(GRUDGE_FOOD)));

    public static final DeferredItem<Item> ABYSS_METAL = ITEMS.register("abyss_metal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ABYSS_POLYMETAL = ITEMS.register("abyss_polymetal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ABYSSIUM = ITEMS.register("abyssium",
            () -> new BlockItem(ModBlocks.ABYSSIUM.get(), new Item.Properties()));

    public static final DeferredItem<Item> GRUDGE_BLOCK = ITEMS.register("grudge_block",
            () -> new BlockItem(ModBlocks.GRUDGE_BLOCK.get(), new Item.Properties()));

    public static final DeferredItem<Item> GRUDGE_HEAVY_BLOCK = ITEMS.register("grudge_heavy_block",
            () -> new BlockItem(ModBlocks.GRUDGE_HEAVY_BLOCK.get(), new Item.Properties()));

    public static final DeferredItem<Item> POLYMETAL = ITEMS.register("polymetal",
            () -> new BlockItem(ModBlocks.POLYMETAL.get(), new Item.Properties()));

    public static final DeferredItem<Item> POLYMETAL_ORE = ITEMS.register("polymetal_ore",
            () -> new BlockItem(ModBlocks.POLYMETAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<Item> POLYMETAL_GRAVEL = ITEMS.register("polymetal_gravel",
            () -> new BlockItem(ModBlocks.POLYMETAL_GRAVEL.get(), new Item.Properties()));

    public static final DeferredItem<Item> SMALL_SHIPYARD = ITEMS.register("small_shipyard",
            () -> new SmallShipyardBlockItem(ModBlocks.SMALL_SHIPYARD.get(), new Item.Properties()));

        public static void addLegacyEquipVariants(CreativeModeTab.Output output, DeferredItem<Item> item) {
                Item resolved = item.get();
                if (resolved instanceof LegacyEquipItem legacyEquipItem) {
                        legacyEquipItem.addAllVariantsToCreativeTab(output);
                        return;
                }

                output.accept(resolved);
        }

        public static void addShipTankVariants(CreativeModeTab.Output output) {
                Item resolved = SHIP_TANK.get();
                if (resolved instanceof ShipTankItem shipTankItem) {
                        shipTankItem.addAllVariantsToCreativeTab(output);
                        return;
                }

                output.accept(resolved);
        }

        public static void addCombatRationVariants(CreativeModeTab.Output output) {
                Item resolved = COMBAT_RATION.get();
                if (resolved instanceof CombatRationItem combatRationItem) {
                        combatRationItem.addAllVariantsToCreativeTab(output);
                        return;
                }

                output.accept(resolved);
        }
}
