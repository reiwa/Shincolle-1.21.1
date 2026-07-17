package org.trp.shincolle;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Spec definitions
    // general
    private static final ModConfigSpec.ConfigValue<String> CONSUMPTION_MODE;
    private static final ModConfigSpec.BooleanValue ALWAYS_SHOW_TEAM_CIRCLE;
    private static final ModConfigSpec.IntValue COOLDOWN_BOSS;
    private static final ModConfigSpec.IntValue CLOSE_GUI_DISTANCE;
    private static final ModConfigSpec.IntValue MODE_CHUNKLOADER;
    private static final ModConfigSpec.IntValue DEATH_TIME;
    private static final ModConfigSpec.BooleanValue MODE_DEBUG;
    private static final ModConfigSpec.IntValue DESPAWN_BOSS;
    private static final ModConfigSpec.IntValue DESPAWN_MINION;
    private static final ModConfigSpec.IntValue DESPAWN_EGG;
    private static final ModConfigSpec.DoubleValue DROP_RATE_GRUDGE;
    private static final ModConfigSpec.BooleanValue FRIENDLY_FIRE;
    private static final ModConfigSpec.IntValue RECYCLE_SMALL;
    private static final ModConfigSpec.IntValue RECYCLE_LARGE;
    private static final ModConfigSpec.BooleanValue ATTACK_PLAYER_SHIPMOB;
    private static final ModConfigSpec.IntValue ATTACK_PLAYER_SHIP;
    private static final ModConfigSpec.BooleanValue POLYMETAL_AS_MN;
    private static final ModConfigSpec.IntValue RADAR_UPDATE;
    private static final ModConfigSpec.BooleanValue NAMETAG_ALWAYS_SHOW;
    private static final ModConfigSpec.IntValue NAMETAG_DISTANCE;
    private static final ModConfigSpec.IntValue COMMAND_SHIPNUM;
    private static final ModConfigSpec.IntValue COOLDOWN_TEAM;
    private static final ModConfigSpec.BooleanValue DEPTH_HADALVORTEX;
    private static final ModConfigSpec.IntValue SPAWN_BOSS_NUMBER;
    private static final ModConfigSpec.IntValue SPAWN_MOB_NUMBER;
    private static final ModConfigSpec.IntValue PAIRING_DIST_CHEST;
    private static final ModConfigSpec.IntValue PAIRING_DIST_WAYPOINT;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> TILE_SMALLSHIPYARD;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> TILE_LARGESHIPYARD;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> TILE_VOLCORE;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> TILE_CRANE;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> RING_ABILITY;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> INFINITE_PUMP;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> POSITION_HUD;

    // ship setting
    private static final ModConfigSpec.BooleanValue CAN_FLARE;
    private static final ModConfigSpec.BooleanValue CAN_SEARCHLIGHT;
    private static final ModConfigSpec.BooleanValue CAN_TELEPORT;
    private static final ModConfigSpec.BooleanValue CHECK_RING;
    private static final ModConfigSpec.BooleanValue CAN_TIMEKEEPING;
    private static final ModConfigSpec.DoubleValue VOLUME_TIMEKEEPING;
    private static final ModConfigSpec.DoubleValue VOLUME_SHIP;
    private static final ModConfigSpec.DoubleValue VOLUME_ATTACK;
    private static final ModConfigSpec.IntValue CARESS_BASE_MORALE;
    private static final ModConfigSpec.IntValue ATTRS_LIMIT_MODERNIZATION;
    private static final ModConfigSpec.IntValue CD_SEARCHLIGHT;
    private static final ModConfigSpec.IntValue CD_AIRPLANERECOVERY;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> ATTRS_SCALE;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> ATTRS_LIMIT;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> ATTRS_HOSTILE_SMALLBOSS;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> ATTRS_HOSTILE_LARGEBOSS;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> ATTRS_HOSTILE_SMALLMOB;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> ATTRS_HOSTILE_LARGEMOB;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> CONSUME_AMMO;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> CONSUME_GRUDGE_IDLE;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> CONSUME_GRUDGE_ACTION;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> CONSUME_GRUDGE_TASK;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> ATTACK_BASE_SPEED;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> ATTACK_FIXED_DELAY;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> EXP_GAIN;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> EXP_GAIN_TASK;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> LIMIT_MOBSPAWNNUMBER;
    private static final ModConfigSpec.ConfigValue<List<? extends Double>> HELD_ITEM;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> DRUM_LIQUID;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> SHIP_TELEPORT;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> TICK_FISHING;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> TICK_MINING;
    private static final ModConfigSpec.ConfigValue<List<? extends Boolean>> TASK_ENABLE;
    private static final ModConfigSpec.IntValue DMGTAKEN_SVS;
    private static final ModConfigSpec.IntValue EXP_MODIFIER;

    private static final ModConfigSpec.IntValue TRAINING_BOOK_LEVEL_MIN;
    private static final ModConfigSpec.IntValue TRAINING_BOOK_LEVEL_MAX;
    private static final ModConfigSpec.IntValue SHIP_EXP_GAIN_KILL;
    private static final ModConfigSpec.IntValue SHIP_MAX_LEVEL_NORMAL;
    private static final ModConfigSpec.IntValue SHIP_MAX_LEVEL_MARRIED;
    private static final ModConfigSpec.IntValue FUEL_DECAY_INTERVAL;

    // world gen
    private static final ModConfigSpec.IntValue POLYMETAL_ORE;
    private static final ModConfigSpec.IntValue POLYMETAL_GRAVEL;
    private static final ModConfigSpec.ConfigValue<List<? extends Boolean>> POLYMETAL_GRAVEL_REPLACE;

    // inter-mod
    private static final ModConfigSpec.BooleanValue MOD_IC2;
    private static final ModConfigSpec.ConfigValue<List<? extends Integer>> DRUM_EU;
    private static final ModConfigSpec.BooleanValue MOD_METAMORPHSKILL;
    private static final ModConfigSpec.DoubleValue METAMORPH_EXPGAIN;
    private static final ModConfigSpec.DoubleValue METAMORPH_HPRATIO;
    private static final ModConfigSpec.DoubleValue METAMORPH_DMGTAKENRATIO;

    // buff
    private static final ModConfigSpec.IntValue BUFF_SATURATION;

    public static final ModConfigSpec SPEC;

    // Run-time field values
    // general
    public static String consumption = "Middle";
    public static int consumptionLevel = 1;
    public static boolean alwaysShowTeamParticle = false;
    public static int bossCooldown = 4800;
    public static int closeGUIDist = 64;
    public static int chunkloaderMode = 2;
    public static int deathMaxTick = 400;
    public static boolean debugMode = false;
    public static int despawnBoss = 12000;
    public static int despawnMinion = 600;
    public static int despawnEgg = 12000;
    public static float dropGrudge = 1.0F;
    public static boolean friendlyFire = true;
    public static int kaitaiAmountSmall = 20;
    public static int kaitaiAmountLarge = 20;
    public static boolean mobAttackPlayer = true;
    public static int shipAttackPlayer = 0;
    public static boolean polyAsMn = false;
    public static int radarUpdate = 64;
    public static boolean showTag = true;
    public static int nameTagDist = 16;
    public static int shipNumPerPage = 5;
    public static int teamCooldown = 6000;
    public static boolean vortexDepth = false;
    public static int spawnBossNum = 2;
    public static int spawnMobNum = 4;
    public static int pairDistChest = 16;
    public static int pairDistWp = 48;
    public static double[] tileShipyardSmall = {460800.0, 48.0, 1.0};
    public static double[] tileShipyardLarge = {1382400.0, 48.0, 1.0};
    public static double[] tileVolCore = {9600.0, 16.0, 240.0};
    public static int[] tileCrane = {2048000, 160000000};
    public static int[] ringAbility = {0, 6, 30, 20, 12};
    public static int[] infLiquid = {12, 8};
    public static double[] posHUD = {0.5, 0.6};

    // ship setting
    public static boolean canFlare = true;
    public static boolean canSearchlight = true;
    public static boolean canTeleport = true;
    public static boolean checkRing = true;
    public static boolean timeKeeping = true;
    public static float volumeTimekeep = 1.0F;
    public static float volumeShip = 1.0F;
    public static float volumeFire = 0.7F;
    public static int baseCaressMorale = 20;
    public static int modernLimit = 3;
    public static int searchlightCD = 4;
    public static int airplaneDelay = 3600;
    public static double[] scaleShip = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
    public static double[] limitShipAttrs = {-1.0, -1.0, -1.0, -1.0, -1.0, 0.8, 4.0, 0.6, 64.0, 0.9, 0.9, 0.9, 0.9, -1.0, -1.0, 0.75, -1.0, -1.0, -1.0, -1.0, 1.0};
    public static double[] scaleBossSmall = {1600.0, 120.0, 0.5, 1.6, 0.38, 18.0};
    public static double[] scaleBossLarge = {3200.0, 240.0, 0.75, 2.0, 0.35, 22.0};
    public static double[] scaleMobSmall = {250.0, 25.0, 0.15, 0.7, 0.45, 12.0};
    public static double[] scaleMobLarge = {500.0, 50.0, 0.3, 0.9, 0.4, 15.0};
    public static double[] scaleHeldItemArray = {1.0, 0.0, 0.0, 0.0};
    public static float scaleHeldItem = 1.0F;
    public static float offsetHeldItemX = 0.0F;
    public static float offsetHeldItemY = 0.0F;
    public static float offsetHeldItemZ = 0.0F;
    public static int[] consumeAmmoShip = {1, 2, 2, 2, 2, 3, 3, 4, 4, 1, 1};
    public static int[] consumeGrudgeShip = {5, 7, 8, 9, 8, 11, 12, 15, 14, 4, 3};
    public static int[] consumeGrudgeAction = {4, 8, 6, 12, 3};
    public static int[] consumeGrudgeTask = {3, 30, 300, 2};
    public static int[] baseAttackSpeed = {40, 80, 120, 100, 100};
    public static int[] fixedAttackDelay = {0, 20, 50, 35, 35};
    public static int[] expGain = {4, 8, 24, 16, 48, 2, 4};
    public static int[] expGainTask = {4, 40, 20, 2};
    public static int[] mobSpawn = {50, 10, 1, 1, 1};
    public static int[] drumLiquid = {40, 5};
    public static int[] shipTeleport = {200, 256};
    public static int[] tickFishing = {400, 600};
    public static int[] tickMining = {100, 200};
    public static boolean[] enableTask = {true, true, true, true};
    public static int dmgSvS = 100;
    public static int shipExpModifier = 20;

    public static int trainingBookLevelMin = 5;
    public static int trainingBookLevelMax = 10;
    public static int shipExpGainMelee = 4;
    public static int shipExpGainKill = 8;
    public static int shipMaxLevelNormal = 100;
    public static int shipMaxLevelMarried = 150;
    public static int fuelDecayInterval = 128;

    // world gen
    public static int polyOreBaseRate = 7;
    public static int polyGravelBaseRate = 4;
    public static boolean[] polyGravelBaseBlock = {true, true, false, false};

    // inter-mod
    public static boolean enableIC2 = true;
    public static int[] drumEU = {400, 100};
    public static boolean enableMetamorphSkill = true;
    public static double expGainPlayerSkill = 6.0;
    public static double morphHPRatio = 0.1;
    public static double morphDmgTakenRatio = 0.2;

    // buff
    public static int buffSaturation = 100;

    public static int fuelConsumeDD = 5;
    public static int fuelConsumeCL = 7;
    public static int fuelConsumeCA = 8;
    public static int fuelConsumeCAV = 9;
    public static int fuelConsumeCLT = 8;
    public static int fuelConsumeCVL = 11;
    public static int fuelConsumeCV = 12;
    public static int fuelConsumeBB = 15;
    public static int fuelConsumeBBV = 14;
    public static int fuelConsumeSS = 4;
    public static int fuelConsumeAP = 3;

    public static int fuelConsumeActionLight = 4;
    public static int fuelConsumeActionHeavy = 8;
    public static int fuelConsumeActionLightAircraft = 6;
    public static int fuelConsumeActionHeavyAircraft = 12;
    public static int fuelMoveDecayFactor = 3;

    public static int tickFishingMin = 400;
    public static int tickFishingMax = 600;
    public static int tickMiningMin = 100;
    public static int tickMiningMax = 200;

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
        BUILDER.comment("general setting").push("general");

        CONSUMPTION_MODE = BUILDER.comment(
            "Sets consumption level. Low : ++Treasure chest loot ++Resource input to a shipyard Middle : +Resource input to a shipyard High : --Treasure chest loot"
        ).define("Consumption_Mode", "Middle");

        ALWAYS_SHOW_TEAM_CIRCLE = BUILDER.comment("Always show team circle indicator particle")
            .define("AlwaysShow_TeamCircle", alwaysShowTeamParticle);

        COOLDOWN_BOSS = BUILDER.comment("Boss spawn cooldown")
            .defineInRange("Cooldown_Boss", bossCooldown, 20, 1728000);

        CLOSE_GUI_DISTANCE = BUILDER.comment("Close inventory GUI if ship away from player X blocks")
            .defineInRange("Close_GUI_Distance", closeGUIDist, 2, 64);

        MODE_CHUNKLOADER = BUILDER.comment("Chunk loader mode: 0: disable, 1: only 1 chunk each ship, 2: 3x3 chunks each ship")
            .defineInRange("Mode_ChunkLoader", chunkloaderMode, 0, 2);

        DEATH_TIME = BUILDER.comment("Ship death animation time")
            .defineInRange("Death_Time", deathMaxTick, 0, 3600);

        MODE_DEBUG = BUILDER.comment("Enable debug message (SPAM WARNING)")
            .define("Mode_Debug", debugMode);

        DESPAWN_BOSS = BUILDER.comment("Despawn time of boss ship, -1 = do NOT despawn")
            .defineInRange("Despawn_Boss", despawnBoss, -1, 1728000);

        DESPAWN_MINION = BUILDER.comment("Despawn time of nonboss ship, -1 = do NOT despawn")
            .defineInRange("Despawn_Minion", despawnMinion, -1, 1728000);

        DESPAWN_EGG = BUILDER.comment("Despawn time of spawn egg of ship mob, -1 = do NOT despawn")
            .defineInRange("Despawn_Egg", despawnEgg, -1, 1728000);

        DROP_RATE_GRUDGE = BUILDER.comment("Grudge drop rate (ex: 0.5 = 50% drop 1 grudge, 5.5 = drop 5 grudge + 50% drop 1 grudge)")
            .defineInRange("DropRate_Grudge", (double) dropGrudge, 0.0D, 64.0D);

        FRIENDLY_FIRE = BUILDER.comment("false: disable damage done by player (except owner)")
            .define("Friendly_Fire", friendlyFire);

        RECYCLE_SMALL = BUILDER.comment("Recycle amount by Dismantle Hammer for common ship, ex: Ro500.")
            .defineInRange("Recycle_Small", kaitaiAmountSmall, 0, 1000);

        RECYCLE_LARGE = BUILDER.comment("Recycle amount by Dismantle Hammer for rare ship, ex: Yamato.")
            .defineInRange("Recycle_Large", kaitaiAmountLarge, 0, 1000);

        ATTACK_PLAYER_SHIPMOB = BUILDER.comment("for mob ship, true: attack player automatically")
            .define("Attack_Player_ShipMob", mobAttackPlayer);

        ATTACK_PLAYER_SHIP = BUILDER.comment("for pet ship, 0: ship don't attack player automatically, 1: attack hostile player, 2: attack hostile and neutral player, 3: attack all player even if the player isn't in a team")
            .defineInRange("Attack_Player_Ship", shipAttackPlayer, 0, 3);

        POLYMETAL_AS_MN = BUILDER.comment("true: Polymetallic Nodules = Manganese Dust, Polymetallic Ore = Manganese Ore")
            .define("Polymetal_as_Mn", polyAsMn);

        RADAR_UPDATE = BUILDER.comment("Radar update interval (ticks) in Admiral's Desk GUI")
            .defineInRange("Radar_Update", radarUpdate, 20, 6000);

        NAMETAG_ALWAYS_SHOW = BUILDER.comment("Always show custom name tag")
            .define("NameTag_AlwaysShow", showTag);

        NAMETAG_DISTANCE = BUILDER.comment("Show name tag if player get close to ship X blocks")
            .defineInRange("NameTag_Distance", nameTagDist, 1, 64);

        COMMAND_SHIPNUM = BUILDER.comment("#Ship per page for command: /ship list")
            .defineInRange("Command_ShipNum", shipNumPerPage, 1, 5000);

        COOLDOWN_TEAM = BUILDER.comment("Create/Disband Team Cooldown")
            .defineInRange("Cooldown_Team", teamCooldown, 20, 1728000);

        DEPTH_HADALVORTEX = BUILDER.comment("Enable depth while rendering Hadal Vortex block.")
            .define("Depth_HadalVortex", vortexDepth);

        SPAWN_BOSS_NUMBER = BUILDER.comment("large hostile ship (boss) number per spawn")
            .defineInRange("Spawn_Boss_Number", spawnBossNum, 1, 10);

        SPAWN_MOB_NUMBER = BUILDER.comment("small hostile ship number per spawn")
            .defineInRange("Spawn_Mob_Number", spawnMobNum, 1, 10);

        PAIRING_DIST_CHEST = BUILDER.comment("Max pairing distance between waypoint and chest")
            .defineInRange("PairingDist_Chest", pairDistChest, 0, 64);

        PAIRING_DIST_WAYPOINT = BUILDER.comment("Max pairing distance between waypoints")
            .defineInRange("PairingDist_Waypoint", pairDistWp, 0, 64);

        TILE_SMALLSHIPYARD = defineDoubleList("Tile_SmallShipyard", tileShipyardSmall);
        TILE_LARGESHIPYARD = defineDoubleList("Tile_LargeShipyard", tileShipyardLarge);
        TILE_VOLCORE = defineDoubleList("Tile_VolCore", tileVolCore);
        TILE_CRANE = defineIntList("Tile_Crane", tileCrane);
        RING_ABILITY = defineIntList("Ring_Ability", ringAbility);
        INFINITE_PUMP = defineIntList("Infinite_Pump", infLiquid);
        POSITION_HUD = defineDoubleList("Position_HUD", posHUD);

        BUILDER.pop();


        BUILDER.comment("ship setting").push("ship setting");

        CAN_FLARE = BUILDER.comment("Can ship spawn Flare lighting effect, CLIENT SIDE only")
            .define("Can_Flare", canFlare);

        CAN_SEARCHLIGHT = BUILDER.comment("Can ship spawn Searchlight lighting effect, CLIENT SIDE only")
            .define("Can_Searchlight", canSearchlight);

        CAN_TELEPORT = BUILDER.comment("Can ship teleport to owner/guarding position if too far away.")
            .define("Can_Teleport", canTeleport);

        CHECK_RING = BUILDER.comment("Should check wedding ring when spawning NON-BOSS ship mob")
            .define("Check_Ring", checkRing);

        CAN_TIMEKEEPING = BUILDER.comment("Play timekeeping sound every 1000 ticks (1 minecraft hour)")
            .define("Can_Timekeeping", timeKeeping);

        VOLUME_TIMEKEEPING = BUILDER.comment("Timekeeping sound volume")
            .defineInRange("Volume_Timekeeping", (double) volumeTimekeep, 0.0D, 10.0D);

        VOLUME_SHIP = BUILDER.comment("Other sound volume")
            .defineInRange("Volume_Ship", (double) volumeShip, 0.0D, 10.0D);

        VOLUME_ATTACK = BUILDER.comment("Attack sound volume")
            .defineInRange("Volume_Attack", (double) volumeFire, 0.0D, 10.0D);

        CARESS_BASE_MORALE = BUILDER.comment("base morale value per CaressTick (4 ticks)")
            .defineInRange("Caress_BaseMorale", baseCaressMorale, 1, 5000);

        ATTRS_LIMIT_MODERNIZATION = BUILDER.comment("Max upgrade level by Modernization Toolkit")
            .defineInRange("Attrs_Limit_Modernization", modernLimit, 3, 100);

        CD_SEARCHLIGHT = BUILDER.comment("Cooldown for placing light block of searchlight")
            .defineInRange("CD_SearchLight", searchlightCD, 1, 256);

        CD_AIRPLANERECOVERY = BUILDER.comment("Base cooldown for airplane recovery, actual recovery time = CD_AirplaneRecovery / attack speed + 20")
            .defineInRange("CD_AirplaneRecovery", airplaneDelay, 1, 30000);

        ATTRS_SCALE = defineDoubleList("Attrs_Scale", scaleShip);
        ATTRS_LIMIT = defineDoubleList("Attrs_Limit", limitShipAttrs);
        ATTRS_HOSTILE_SMALLBOSS = defineDoubleList("Attrs_Hostile_SmallBoss", scaleBossSmall);
        ATTRS_HOSTILE_LARGEBOSS = defineDoubleList("Attrs_Hostile_LargeBoss", scaleBossLarge);
        ATTRS_HOSTILE_SMALLMOB = defineDoubleList("Attrs_Hostile_SmallMob", scaleMobSmall);
        ATTRS_HOSTILE_LARGEMOB = defineDoubleList("Attrs_Hostile_LargeMob", scaleMobLarge);
        CONSUME_AMMO = defineIntList("Consume_Ammo", consumeAmmoShip);
        CONSUME_GRUDGE_IDLE = defineIntList("Consume_Grudge_Idle", consumeGrudgeShip);
        CONSUME_GRUDGE_ACTION = defineIntList("Consume_Grudge_Action", consumeGrudgeAction);
        CONSUME_GRUDGE_TASK = defineIntList("Consume_Grudge_Task", consumeGrudgeTask);
        ATTACK_BASE_SPEED = defineIntList("Attack_Base_Speed", baseAttackSpeed);
        ATTACK_FIXED_DELAY = defineIntList("Attack_Fixed_Delay", fixedAttackDelay);
        EXP_GAIN = defineIntList("Exp_Gain", expGain);
        EXP_GAIN_TASK = defineIntList("Exp_Gain_Task", expGainTask);
        LIMIT_MOBSPAWNNUMBER = defineIntList("Limit_MobSpawnNumber", mobSpawn);
        HELD_ITEM = defineDoubleList("Held_Item", scaleHeldItemArray);
        DRUM_LIQUID = defineIntList("Drum_Liquid", drumLiquid);
        SHIP_TELEPORT = defineIntList("ship_teleport", shipTeleport);
        TICK_FISHING = defineIntList("Tick_Fishing", tickFishing);
        TICK_MINING = defineIntList("Tick_Mining", tickMining);
        TASK_ENABLE = defineBooleanList("Task_Enable", enableTask);

        DMGTAKEN_SVS = BUILDER.comment("Ship vs Ship damage modifier, 20 = damage * 20%")
            .defineInRange("DmgTaken_SvS", dmgSvS, 0, 10000);

        EXP_MODIFIER = BUILDER.comment("ship experience modifier, 20 = level 150: 150*20+20 = 3020")
            .defineInRange("EXP_Modifier", shipExpModifier, 1, 10000);

        TRAINING_BOOK_LEVEL_MIN = BUILDER.comment("Training book minimum level gain")
            .defineInRange("trainingBookLevelMin", trainingBookLevelMin, 1, 50);

        TRAINING_BOOK_LEVEL_MAX = BUILDER.comment("Training book maximum level gain")
            .defineInRange("trainingBookLevelMax", trainingBookLevelMax, 1, 50);

        SHIP_EXP_GAIN_KILL = BUILDER.comment("EXP gained when ship kills an enemy")
            .defineInRange("expGainKill", shipExpGainKill, 0, 10000);

        SHIP_MAX_LEVEL_NORMAL = BUILDER.comment("Max ship level for non-married ships")
            .defineInRange("maxLevelNormal", shipMaxLevelNormal, 1, 150);

        SHIP_MAX_LEVEL_MARRIED = BUILDER.comment("Max ship level for married ships")
            .defineInRange("maxLevelMarried", shipMaxLevelMarried, 1, 150);

        FUEL_DECAY_INTERVAL = BUILDER.comment("Interval in ticks between fuel decay checks")
            .defineInRange("decayInterval", fuelDecayInterval, 1, 10000);

        BUILDER.pop();


        BUILDER.comment("world generate setting").push("world gen");

        POLYMETAL_ORE = BUILDER.comment("Polymetallic Ore clusters in one chunk")
            .defineInRange("Polymetal_Ore", polyOreBaseRate, 0, 100);

        POLYMETAL_GRAVEL = BUILDER.comment("Polymetallic Gravel clusters in one chunk")
            .defineInRange("Polymetal_Gravel", polyGravelBaseRate, 0, 100);

        POLYMETAL_GRAVEL_REPLACE = defineBooleanList("Polymetal_Gravel_Replace", polyGravelBaseBlock);

        BUILDER.pop();


        BUILDER.comment("mod interaction setting").push("inter-mod");

        MOD_IC2 = BUILDER.comment("Enable IC2 module if mod existed: add EU related function.")
            .define("Mod_IC2", enableIC2);

        DRUM_EU = defineIntList("Drum_EU", drumEU);

        MOD_METAMORPHSKILL = BUILDER.comment("Enable Metamorph module, if true: 1. player can use ship skill in morphing, 2. if no grudge, player will be demorphed.")
            .define("Mod_MetamorphSkill", enableMetamorphSkill);

        METAMORPH_EXPGAIN = BUILDER.comment("Exp modify for casting ship attack skill by player in morph, final exp = raw exp * Metamorph_ExpGain, req: Metamorph mod.")
            .defineInRange("Metamorph_ExpGain", expGainPlayerSkill, 0.0D, 1000.0D);

        METAMORPH_HPRATIO = BUILDER.comment("HP modify of player in morph, final HP = 20 + shipHP * Metamorph_HPRatio, req: Metamorph mod.")
            .defineInRange("Metamorph_HPRatio", morphHPRatio, 0.0D, 10.0D);

        METAMORPH_DMGTAKENRATIO = BUILDER.comment("Damage by ship attack modify of player in morph, final DamageTaken = raw damage * Metamorph_DmgTakenRatio, req: Metamorph mod.")
            .defineInRange("Metamorph_DmgTakenRatio", morphDmgTakenRatio, 0.0D, 1.0D);

        BUILDER.pop();


        BUILDER.comment("potion buff and debuff setting").push("buff");

        BUFF_SATURATION = BUILDER.comment("buffSaturation")
            .defineInRange("buffSaturation", buffSaturation, 0, 10000);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    private static ModConfigSpec.ConfigValue<List<? extends Double>> defineDoubleList(String path, double[] defaults) {
        List<Double> list = new ArrayList<>();
        for (double d : defaults) {
            list.add(d);
        }
        return BUILDER.defineList(path, list, obj -> obj instanceof Double);
    }

    private static ModConfigSpec.ConfigValue<List<? extends Integer>> defineIntList(String path, int[] defaults) {
        List<Integer> list = new ArrayList<>();
        for (int i : defaults) {
            list.add(i);
        }
        return BUILDER.defineList(path, list, obj -> obj instanceof Integer);
    }

    private static ModConfigSpec.ConfigValue<List<? extends Boolean>> defineBooleanList(String path, boolean[] defaults) {
        List<Boolean> list = new ArrayList<>();
        for (boolean b : defaults) {
            list.add(b);
        }
        return BUILDER.defineList(path, list, obj -> obj instanceof Boolean);
    }

    private static int[] getIntArray(ModConfigSpec.ConfigValue<List<? extends Integer>> spec, int[] defaults) {
        List<? extends Integer> list = spec.get();
        if (list == null || list.size() != defaults.length) {
            return defaults;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static double[] getDoubleArray(ModConfigSpec.ConfigValue<List<? extends Double>> spec, double[] defaults) {
        List<? extends Double> list = spec.get();
        if (list == null || list.size() != defaults.length) {
            return defaults;
        }
        double[] arr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static boolean[] getBooleanArray(ModConfigSpec.ConfigValue<List<? extends Boolean>> spec, boolean[] defaults) {
        List<? extends Boolean> list = spec.get();
        if (list == null || list.size() != defaults.length) {
            return defaults;
        }
        boolean[] arr = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() != SPEC) {
            return;
        }

        consumption = CONSUMPTION_MODE.get();
        consumptionLevel = switch (consumption.toLowerCase()) {
            case "low" -> 0;
            case "high" -> 2;
            default -> 1;
        };

        alwaysShowTeamParticle = ALWAYS_SHOW_TEAM_CIRCLE.get();
        bossCooldown = COOLDOWN_BOSS.get();
        closeGUIDist = CLOSE_GUI_DISTANCE.get();
        chunkloaderMode = MODE_CHUNKLOADER.get();
        deathMaxTick = DEATH_TIME.get();
        debugMode = MODE_DEBUG.get();
        despawnBoss = DESPAWN_BOSS.get();
        despawnMinion = DESPAWN_MINION.get();
        despawnEgg = DESPAWN_EGG.get();
        dropGrudge = DROP_RATE_GRUDGE.get().floatValue();
        friendlyFire = FRIENDLY_FIRE.get();
        kaitaiAmountSmall = RECYCLE_SMALL.get();
        kaitaiAmountLarge = RECYCLE_LARGE.get();
        mobAttackPlayer = ATTACK_PLAYER_SHIPMOB.get();
        shipAttackPlayer = ATTACK_PLAYER_SHIP.get();
        polyAsMn = POLYMETAL_AS_MN.get();
        radarUpdate = RADAR_UPDATE.get();
        showTag = NAMETAG_ALWAYS_SHOW.get();
        nameTagDist = NAMETAG_DISTANCE.get();
        shipNumPerPage = COMMAND_SHIPNUM.get();
        teamCooldown = COOLDOWN_TEAM.get();
        vortexDepth = DEPTH_HADALVORTEX.get();
        spawnBossNum = SPAWN_BOSS_NUMBER.get();
        spawnMobNum = SPAWN_MOB_NUMBER.get();
        pairDistChest = PAIRING_DIST_CHEST.get();
        pairDistWp = PAIRING_DIST_WAYPOINT.get();

        tileShipyardSmall = getDoubleArray(TILE_SMALLSHIPYARD, tileShipyardSmall);
        tileShipyardLarge = getDoubleArray(TILE_LARGESHIPYARD, tileShipyardLarge);
        tileVolCore = getDoubleArray(TILE_VOLCORE, tileVolCore);
        tileCrane = getIntArray(TILE_CRANE, tileCrane);
        ringAbility = getIntArray(RING_ABILITY, ringAbility);
        infLiquid = getIntArray(INFINITE_PUMP, infLiquid);
        posHUD = getDoubleArray(POSITION_HUD, posHUD);

        // ship setting
        canFlare = CAN_FLARE.get();
        canSearchlight = CAN_SEARCHLIGHT.get();
        canTeleport = CAN_TELEPORT.get();
        checkRing = CHECK_RING.get();
        timeKeeping = CAN_TIMEKEEPING.get();
        volumeTimekeep = VOLUME_TIMEKEEPING.get().floatValue();
        volumeShip = VOLUME_SHIP.get().floatValue();
        volumeFire = VOLUME_ATTACK.get().floatValue();
        baseCaressMorale = CARESS_BASE_MORALE.get();
        modernLimit = ATTRS_LIMIT_MODERNIZATION.get();
        searchlightCD = CD_SEARCHLIGHT.get();
        airplaneDelay = CD_AIRPLANERECOVERY.get();

        scaleShip = getDoubleArray(ATTRS_SCALE, scaleShip);
        limitShipAttrs = getDoubleArray(ATTRS_LIMIT, limitShipAttrs);
        scaleBossSmall = getDoubleArray(ATTRS_HOSTILE_SMALLBOSS, scaleBossSmall);
        scaleBossLarge = getDoubleArray(ATTRS_HOSTILE_LARGEBOSS, scaleBossLarge);
        scaleMobSmall = getDoubleArray(ATTRS_HOSTILE_SMALLMOB, scaleMobSmall);
        scaleMobLarge = getDoubleArray(ATTRS_HOSTILE_LARGEMOB, scaleMobLarge);
        consumeAmmoShip = getIntArray(CONSUME_AMMO, consumeAmmoShip);
        consumeGrudgeShip = getIntArray(CONSUME_GRUDGE_IDLE, consumeGrudgeShip);
        consumeGrudgeAction = getIntArray(CONSUME_GRUDGE_ACTION, consumeGrudgeAction);
        consumeGrudgeTask = getIntArray(CONSUME_GRUDGE_TASK, consumeGrudgeTask);
        baseAttackSpeed = getIntArray(ATTACK_BASE_SPEED, baseAttackSpeed);
        fixedAttackDelay = getIntArray(ATTACK_FIXED_DELAY, fixedAttackDelay);
        expGain = getIntArray(EXP_GAIN, expGain);
        expGainTask = getIntArray(EXP_GAIN_TASK, expGainTask);
        mobSpawn = getIntArray(LIMIT_MOBSPAWNNUMBER, mobSpawn);
        scaleHeldItemArray = getDoubleArray(HELD_ITEM, scaleHeldItemArray);
        scaleHeldItem = (float) scaleHeldItemArray[0];
        offsetHeldItemX = (float) scaleHeldItemArray[1];
        offsetHeldItemY = (float) scaleHeldItemArray[2];
        offsetHeldItemZ = (float) scaleHeldItemArray[3];
        drumLiquid = getIntArray(DRUM_LIQUID, drumLiquid);
        shipTeleport = getIntArray(SHIP_TELEPORT, shipTeleport);
        tickFishing = getIntArray(TICK_FISHING, tickFishing);
        tickMining = getIntArray(TICK_MINING, tickMining);
        enableTask = getBooleanArray(TASK_ENABLE, enableTask);

        dmgSvS = DMGTAKEN_SVS.get();
        shipExpModifier = EXP_MODIFIER.get();

        trainingBookLevelMin = TRAINING_BOOK_LEVEL_MIN.get();
        trainingBookLevelMax = TRAINING_BOOK_LEVEL_MAX.get();
        shipExpGainMelee = expGain[0];
        shipExpGainKill = SHIP_EXP_GAIN_KILL.get();
        shipMaxLevelNormal = SHIP_MAX_LEVEL_NORMAL.get();
        shipMaxLevelMarried = SHIP_MAX_LEVEL_MARRIED.get();
        fuelDecayInterval = FUEL_DECAY_INTERVAL.get();

        // world gen
        polyOreBaseRate = POLYMETAL_ORE.get();
        polyGravelBaseRate = POLYMETAL_GRAVEL.get();
        polyGravelBaseBlock = getBooleanArray(POLYMETAL_GRAVEL_REPLACE, polyGravelBaseBlock);

        // inter-mod
        enableIC2 = MOD_IC2.get();
        drumEU = getIntArray(DRUM_EU, drumEU);
        enableMetamorphSkill = MOD_METAMORPHSKILL.get();
        expGainPlayerSkill = METAMORPH_EXPGAIN.get();
        morphHPRatio = METAMORPH_HPRATIO.get();
        morphDmgTakenRatio = METAMORPH_DMGTAKENRATIO.get();

        // buff
        buffSaturation = BUFF_SATURATION.get();

        fuelConsumeDD = consumeGrudgeShip[0];
        fuelConsumeCL = consumeGrudgeShip[1];
        fuelConsumeCA = consumeGrudgeShip[2];
        fuelConsumeCAV = consumeGrudgeShip[3];
        fuelConsumeCLT = consumeGrudgeShip[4];
        fuelConsumeCVL = consumeGrudgeShip[5];
        fuelConsumeCV = consumeGrudgeShip[6];
        fuelConsumeBB = consumeGrudgeShip[7];
        fuelConsumeBBV = consumeGrudgeShip[8];
        fuelConsumeSS = consumeGrudgeShip[9];
        fuelConsumeAP = consumeGrudgeShip[10];

        fuelConsumeActionLight = consumeGrudgeAction[0];
        fuelConsumeActionHeavy = consumeGrudgeAction[1];
        fuelConsumeActionLightAircraft = consumeGrudgeAction[2];
        fuelConsumeActionHeavyAircraft = consumeGrudgeAction[3];
        fuelMoveDecayFactor = consumeGrudgeAction[4];

        tickFishingMin = tickFishing[0];
        tickFishingMax = tickFishing[1];
        tickMiningMin = tickMining[0];
        tickMiningMax = tickMining[1];

        hostileDropGrudgeRate = dropGrudge;
        hostileDeathMaxTicks = deathMaxTick;
        hostileDespawnBossTicks = despawnBoss;
        hostileDespawnMinionTicks = despawnMinion;
        hostileBossCooldownTicks = bossCooldown;
        hostileSpawnBossCount = spawnBossNum;
        hostileSpawnMinionCount = spawnMobNum;
        hostileSpawnRequireRing = checkRing;
        hostileMobSpawnMax = mobSpawn[0];
        hostileMobSpawnChancePercent = mobSpawn[1];
        hostileMobSpawnGroups = mobSpawn[2];
        hostileMobSpawnGroupMin = mobSpawn[3];
        hostileMobSpawnGroupMax = mobSpawn[4];

        org.trp.shincolle.crafting.ShipyardRecipes.loadConfig();
    }
}
