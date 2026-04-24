package org.trp.shincolle;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Shincolle.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue SHIP_EXP_MODIFIER;
    private static final ModConfigSpec.IntValue SHIP_EXP_GAIN_MELEE;
    private static final ModConfigSpec.IntValue SHIP_EXP_GAIN_KILL;
    private static final ModConfigSpec.IntValue SHIP_EXP_GAIN_LIGHT_ATTACK;
    private static final ModConfigSpec.IntValue SHIP_EXP_GAIN_HEAVY_ATTACK;
    private static final ModConfigSpec.IntValue SHIP_EXP_GAIN_LIGHT_AIRCRAFT;
    private static final ModConfigSpec.IntValue SHIP_EXP_GAIN_HEAVY_AIRCRAFT;
    private static final ModConfigSpec.IntValue SHIP_MAX_LEVEL_NORMAL;
    private static final ModConfigSpec.IntValue SHIP_MAX_LEVEL_MARRIED;
    private static final ModConfigSpec.IntValue TRAINING_BOOK_LEVEL_MIN;
    private static final ModConfigSpec.IntValue TRAINING_BOOK_LEVEL_MAX;

        private static final ModConfigSpec.DoubleValue HOSTILE_DROP_GRUDGE_RATE;
        private static final ModConfigSpec.IntValue HOSTILE_DEATH_MAX_TICKS;
        private static final ModConfigSpec.IntValue HOSTILE_DESPAWN_BOSS_TICKS;
        private static final ModConfigSpec.IntValue HOSTILE_DESPAWN_MINION_TICKS;
        private static final ModConfigSpec.IntValue HOSTILE_BOSS_COOLDOWN_TICKS;
        private static final ModConfigSpec.IntValue HOSTILE_SPAWN_BOSS_COUNT;
        private static final ModConfigSpec.IntValue HOSTILE_SPAWN_MINION_COUNT;
        private static final ModConfigSpec.BooleanValue HOSTILE_SPAWN_REQUIRE_RING;
        private static final ModConfigSpec.IntValue HOSTILE_MOB_SPAWN_MAX;
        private static final ModConfigSpec.IntValue HOSTILE_MOB_SPAWN_CHANCE_PERCENT;
        private static final ModConfigSpec.IntValue HOSTILE_MOB_SPAWN_GROUPS;
        private static final ModConfigSpec.IntValue HOSTILE_MOB_SPAWN_GROUP_MIN;
        private static final ModConfigSpec.IntValue HOSTILE_MOB_SPAWN_GROUP_MAX;

    public static final ModConfigSpec SPEC;

    public static int shipExpModifier = 20;
    public static int shipExpGainMelee = 4;
    public static int shipExpGainKill = 8;
    public static int shipExpGainLightAttack = 8;
    public static int shipExpGainHeavyAttack = 24;
    public static int shipExpGainLightAircraft = 16;
    public static int shipExpGainHeavyAircraft = 48;
    public static int shipMaxLevelNormal = 100;
    public static int shipMaxLevelMarried = 150;
    public static int trainingBookLevelMin = 5;
    public static int trainingBookLevelMax = 10;

        public static float hostileDropGrudgeRate = 1.0F;
        public static int hostileDeathMaxTicks = 400;
        public static int hostileDespawnBossTicks = 12000;
        public static int hostileDespawnMinionTicks = 600;
        public static int hostileBossCooldownTicks = 4800;
        public static int hostileSpawnBossCount = 2;
        public static int hostileSpawnMinionCount = 4;
        public static boolean hostileSpawnRequireRing = true;
        public static int hostileMobSpawnMax = 50;
        public static int hostileMobSpawnChancePercent = 10;
        public static int hostileMobSpawnGroups = 1;
        public static int hostileMobSpawnGroupMin = 1;
        public static int hostileMobSpawnGroupMax = 1;

    static {
        BUILDER.comment("Ship EXP and level settings").push("ship_exp");

        SHIP_EXP_MODIFIER = BUILDER
                .comment("EXP required for next level = currentLevel * value + value")
                .defineInRange("expModifier", shipExpModifier, 1, 10000);

        SHIP_EXP_GAIN_MELEE = BUILDER
                .comment("EXP gained when ship performs melee attack")
                .defineInRange("expGainMelee", shipExpGainMelee, 0, 10000);

        SHIP_EXP_GAIN_KILL = BUILDER
                .comment("EXP gained when ship kills an enemy")
                .defineInRange("expGainKill", shipExpGainKill, 0, 10000);

        SHIP_EXP_GAIN_LIGHT_ATTACK = BUILDER
                .comment("EXP gained when ship performs light ammo attack")
                .defineInRange("expGainLightAttack", shipExpGainLightAttack, 0, 10000);

        SHIP_EXP_GAIN_HEAVY_ATTACK = BUILDER
                .comment("EXP gained when ship performs heavy ammo attack")
                .defineInRange("expGainHeavyAttack", shipExpGainHeavyAttack, 0, 10000);

        SHIP_EXP_GAIN_LIGHT_AIRCRAFT = BUILDER
                .comment("EXP gained when ship launches light aircraft attack")
                .defineInRange("expGainLightAircraft", shipExpGainLightAircraft, 0, 10000);

        SHIP_EXP_GAIN_HEAVY_AIRCRAFT = BUILDER
                .comment("EXP gained when ship launches heavy aircraft attack")
                .defineInRange("expGainHeavyAircraft", shipExpGainHeavyAircraft, 0, 10000);

        SHIP_MAX_LEVEL_NORMAL = BUILDER
                .comment("Max ship level for non-married ships")
                .defineInRange("maxLevelNormal", shipMaxLevelNormal, 1, 150);

        SHIP_MAX_LEVEL_MARRIED = BUILDER
                .comment("Max ship level for married ships")
                .defineInRange("maxLevelMarried", shipMaxLevelMarried, 1, 150);

        TRAINING_BOOK_LEVEL_MIN = BUILDER
                .comment("Training book minimum level gain")
                .defineInRange("trainingBookLevelMin", trainingBookLevelMin, 1, 50);

        TRAINING_BOOK_LEVEL_MAX = BUILDER
                .comment("Training book maximum level gain")
                .defineInRange("trainingBookLevelMax", trainingBookLevelMax, 1, 50);

        BUILDER.pop();

        BUILDER.comment("Legacy hostile ship spawn/death/drop settings").push("hostile");

        HOSTILE_DROP_GRUDGE_RATE = BUILDER
                .comment("Grudge drop rate for hostile entities (fixed + chance, e.g. 5.5 = 5 guaranteed + 50% for +1)")
                .defineInRange("dropGrudgeRate", hostileDropGrudgeRate, 0.0D, 64.0D);

        HOSTILE_DEATH_MAX_TICKS = BUILDER
                .comment("Hostile ship death animation ticks before final sink processing")
                .defineInRange("deathMaxTicks", hostileDeathMaxTicks, 0, 3600);

        HOSTILE_DESPAWN_BOSS_TICKS = BUILDER
                .comment("Hostile boss despawn ticks, -1 disables despawn")
                .defineInRange("despawnBossTicks", hostileDespawnBossTicks, -1, 1728000);

        HOSTILE_DESPAWN_MINION_TICKS = BUILDER
                .comment("Hostile minion despawn ticks, -1 disables despawn")
                .defineInRange("despawnMinionTicks", hostileDespawnMinionTicks, -1, 1728000);

        HOSTILE_BOSS_COOLDOWN_TICKS = BUILDER
                .comment("Boss fleet spawn cooldown ticks")
                .defineInRange("bossCooldownTicks", hostileBossCooldownTicks, 20, 1728000);

        HOSTILE_SPAWN_BOSS_COUNT = BUILDER
                .comment("Boss ships per boss fleet spawn")
                .defineInRange("spawnBossCount", hostileSpawnBossCount, 1, 10);

        HOSTILE_SPAWN_MINION_COUNT = BUILDER
                .comment("Minion ships per boss fleet spawn")
                .defineInRange("spawnMinionCount", hostileSpawnMinionCount, 1, 10);

        HOSTILE_SPAWN_REQUIRE_RING = BUILDER
                .comment("Require player inventory to have a marriage ring for regular hostile mob spawns")
                .define("spawnRequireRing", hostileSpawnRequireRing);

        HOSTILE_MOB_SPAWN_MAX = BUILDER
                .comment("Maximum number of non-boss hostile ships loaded in a level for regular spawn checks")
                .defineInRange("mobSpawnMax", hostileMobSpawnMax, 0, 10000);

        HOSTILE_MOB_SPAWN_CHANCE_PERCENT = BUILDER
                .comment("Regular hostile spawn chance percent per check")
                .defineInRange("mobSpawnChancePercent", hostileMobSpawnChancePercent, 0, 100);

        HOSTILE_MOB_SPAWN_GROUPS = BUILDER
                .comment("Regular hostile spawn group count per successful spawn check")
                .defineInRange("mobSpawnGroups", hostileMobSpawnGroups, 1, 16);

        HOSTILE_MOB_SPAWN_GROUP_MIN = BUILDER
                .comment("Minimum hostile ship count per regular spawn group")
                .defineInRange("mobSpawnGroupMin", hostileMobSpawnGroupMin, 1, 16);

        HOSTILE_MOB_SPAWN_GROUP_MAX = BUILDER
                .comment("Maximum hostile ship count per regular spawn group")
                .defineInRange("mobSpawnGroupMax", hostileMobSpawnGroupMax, 1, 16);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() != SPEC) {
            return;
        }

        shipExpModifier = SHIP_EXP_MODIFIER.get();
        shipExpGainMelee = SHIP_EXP_GAIN_MELEE.get();
        shipExpGainKill = SHIP_EXP_GAIN_KILL.get();
        shipExpGainLightAttack = SHIP_EXP_GAIN_LIGHT_ATTACK.get();
        shipExpGainHeavyAttack = SHIP_EXP_GAIN_HEAVY_ATTACK.get();
        shipExpGainLightAircraft = SHIP_EXP_GAIN_LIGHT_AIRCRAFT.get();
        shipExpGainHeavyAircraft = SHIP_EXP_GAIN_HEAVY_AIRCRAFT.get();
        shipMaxLevelNormal = SHIP_MAX_LEVEL_NORMAL.get();
        shipMaxLevelMarried = Math.max(shipMaxLevelNormal, SHIP_MAX_LEVEL_MARRIED.get());
        trainingBookLevelMin = TRAINING_BOOK_LEVEL_MIN.get();
        trainingBookLevelMax = Math.max(trainingBookLevelMin, TRAINING_BOOK_LEVEL_MAX.get());

                hostileDropGrudgeRate = HOSTILE_DROP_GRUDGE_RATE.get().floatValue();
                hostileDeathMaxTicks = HOSTILE_DEATH_MAX_TICKS.get();
                hostileDespawnBossTicks = HOSTILE_DESPAWN_BOSS_TICKS.get();
                hostileDespawnMinionTicks = HOSTILE_DESPAWN_MINION_TICKS.get();
                hostileBossCooldownTicks = HOSTILE_BOSS_COOLDOWN_TICKS.get();
                hostileSpawnBossCount = HOSTILE_SPAWN_BOSS_COUNT.get();
                hostileSpawnMinionCount = HOSTILE_SPAWN_MINION_COUNT.get();
                hostileSpawnRequireRing = HOSTILE_SPAWN_REQUIRE_RING.get();
                hostileMobSpawnMax = HOSTILE_MOB_SPAWN_MAX.get();
                hostileMobSpawnChancePercent = HOSTILE_MOB_SPAWN_CHANCE_PERCENT.get();
                hostileMobSpawnGroups = Math.max(1, HOSTILE_MOB_SPAWN_GROUPS.get());
                hostileMobSpawnGroupMin = Math.max(1, HOSTILE_MOB_SPAWN_GROUP_MIN.get());
                hostileMobSpawnGroupMax = Math.max(hostileMobSpawnGroupMin, HOSTILE_MOB_SPAWN_GROUP_MAX.get());
    }
}
