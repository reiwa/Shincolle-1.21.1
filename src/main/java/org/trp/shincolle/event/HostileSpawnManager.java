package org.trp.shincolle.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.AABB;
import org.trp.shincolle.Config;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

final class HostileSpawnManager {
    private static final Map<UUID, Integer> BOSS_COOLDOWNS = new HashMap<>();

    private HostileSpawnManager() {
    }

    static void tickPlayer(Player player) {
        if (player == null || !player.isAlive() || player.isSpectator()) {
            return;
        }
        if (!(player.level() instanceof ServerLevel level)) {
            return;
        }
        if (level.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }

        if ((player.tickCount & 0x7F) == 0) {
            spawnMobShips(level, player);
        }

        spawnBossShips(level, player);
    }

    private static void spawnMobShips(ServerLevel level, Player player) {
        if (Config.hostileSpawnRequireRing && !hasMarriageRing(player)) {
            return;
        }

        int blockX = Mth.floor(player.getX());
        int blockZ = Mth.floor(player.getZ());
        BlockPos playerPos = new BlockPos(blockX, player.getBlockY(), blockZ);
        if (!isWaterOrBeachBiome(level, playerPos)) {
            return;
        }

        RandomSource random = player.getRandom();
        if (countHostileMinions(level) > Config.hostileMobSpawnMax) {
            return;
        }

        if (random.nextInt(100) > Config.hostileMobSpawnChancePercent) {
            return;
        }

        int groups = Math.max(1, Config.hostileMobSpawnGroups);
        for (int loop = 30 + groups * 30; groups > 0 && loop > 0; --loop) {
            int offX = random.nextInt(30) + 20;
            int offZ = random.nextInt(30) + 20;
            int spawnX = blockX;
            int spawnZ = blockZ;

            switch (random.nextInt(4)) {
                case 0 -> {
                    spawnX += offX;
                    spawnZ += offZ;
                }
                case 1 -> {
                    spawnX -= offX;
                    spawnZ -= offZ;
                }
                case 2 -> {
                    spawnX += offX;
                    spawnZ -= offZ;
                }
                default -> {
                    spawnX -= offX;
                    spawnZ += offZ;
                }
            }

            int groundY = level.getSeaLevel() - 2;
            if (!isWaterAt(level, spawnX, groundY, spawnZ)) {
                continue;
            }

            groups--;

            int spawnY = getTopWaterHeight(level, spawnX, level.getSeaLevel() - 3, spawnZ);
            int shipMin = Math.max(1, Config.hostileMobSpawnGroupMin);
            int shipNum = shipMin;
            int rangeMax = Config.hostileMobSpawnGroupMax - shipMin;
            if (rangeMax > 0) {
                shipNum += random.nextInt(rangeMax + 1);
            }

            for (int i = 0; i < shipNum; ++i) {
                int scaleLevel = random.nextInt(10) > 7 ? 1 : 0;
                spawnRandomHostileShip(level, random, scaleLevel,
                        spawnX + random.nextDouble(),
                        spawnY + 0.5D,
                        spawnZ + random.nextDouble());
            }
        }
    }

    private static void spawnBossShips(ServerLevel level, Player player) {
        UUID playerId = player.getUUID();
        int cooldown = BOSS_COOLDOWNS.getOrDefault(playerId, 0);

        BlockPos playerPos = player.blockPosition();
        boolean canTickCooldown = isWaterOrBeachBiome(level, playerPos)
                && (!Config.hostileSpawnRequireRing || hasMarriageRing(player));
        if (canTickCooldown) {
            cooldown--;
        }

        if (cooldown <= 0) {
            RandomSource random = player.getRandom();
            cooldown = Config.hostileBossCooldownTicks;

            if (random.nextInt(4) == 0) {
                trySpawnBossFleet(level, player, random);
            }
        }

        BOSS_COOLDOWNS.put(playerId, cooldown);
    }

    private static void trySpawnBossFleet(ServerLevel level, Player player, RandomSource random) {
        int baseX = Mth.floor(player.getX());
        int baseZ = Mth.floor(player.getZ());

        for (int i = 0; i < 20; ++i) {
            int offX = random.nextInt(32) + 32;
            int offZ = random.nextInt(32) + 32;
            int spawnX = baseX;
            int spawnZ = baseZ;

            switch (random.nextInt(4)) {
                case 0 -> {
                    spawnX += offX;
                    spawnZ += offZ;
                }
                case 1 -> {
                    spawnX -= offX;
                    spawnZ -= offZ;
                }
                case 2 -> {
                    spawnX += offX;
                    spawnZ -= offZ;
                }
                default -> {
                    spawnX -= offX;
                    spawnZ += offZ;
                }
            }

            int groundY = level.getSeaLevel() - 2;
            if (!isWaterAt(level, spawnX, groundY, spawnZ)) {
                continue;
            }

            int spawnY = getTopWaterHeight(level, spawnX, level.getSeaLevel(), spawnZ);
            AABB search = new AABB(spawnX - 48.0D, spawnY - 48.0D, spawnZ - 48.0D,
                    spawnX + 48.0D, spawnY + 48.0D, spawnZ + 48.0D);
            List<EntityShipBase> ships = level.getEntitiesOfClass(EntityShipBase.class, search, EntityShipBase::isHostileShipMob);
            long bossNum = ships.stream().filter(ship -> ship.getScaleLevel() > 1).count();
            if (bossNum >= 2) {
                continue;
            }

            int bossCount = Math.max(1, Config.hostileSpawnBossCount);
            for (int j = 0; j < bossCount; ++j) {
                int scaleLevel = random.nextInt(100) > 65 ? 3 : 2;
                spawnRandomHostileShip(level, random, scaleLevel,
                        spawnX + random.nextInt(3),
                        spawnY + 0.5D,
                        spawnZ + random.nextInt(3));
            }

            int minionCount = Math.max(1, Config.hostileSpawnMinionCount);
            for (int j = 0; j < minionCount; ++j) {
                spawnRandomHostileShip(level, random, random.nextInt(2),
                        spawnX + random.nextInt(3),
                        spawnY + 0.5D,
                        spawnZ + random.nextInt(3));
            }

            broadcastBossSpawn(level, random, spawnX, spawnY, spawnZ);
            break;
        }
    }

    private static boolean spawnRandomHostileShip(ServerLevel level, RandomSource random, int scaleLevel,
                                                  double x, double y, double z) {
        EntityType<? extends EntityShipBase> type = rollRandomMobShipType(random);
        EntityShipBase ship = type.create(level);
        if (ship == null) {
            return false;
        }

        ship.initializeHostileSpawnState(scaleLevel);
        ship.moveTo(x, y, z, random.nextFloat() * 360.0F, 0.0F);
        if (!level.noCollision(ship, ship.getBoundingBox())) {
            return false;
        }

        return level.addFreshEntity(ship);
    }

    private static EntityType<? extends EntityShipBase> rollRandomMobShipType(RandomSource random) {
        int ran1 = random.nextInt(100);
        if (ran1 > 75) {
            return switch (random.nextInt(3)) {
                case 1 -> ModEntities.BATTLESHIP_YAMATO.get();
                case 2 -> switch (random.nextInt(4)) {
                    case 1 -> ModEntities.BB_HIEI.get();
                    case 2 -> ModEntities.BB_HARUNA.get();
                    case 3 -> ModEntities.BB_KIRISHIMA.get();
                    default -> ModEntities.BB_KONGOU.get();
                };
                default -> ModEntities.BATTLESHIP_NAGATO.get();
            };
        }

        if (ran1 > 45) {
            return switch (random.nextInt(3)) {
                case 1, 2 -> switch (random.nextInt(4)) {
                    case 1 -> ModEntities.CRUISER_TENRYUU.get();
                    case 2 -> ModEntities.CRUISER_TATSUTA.get();
                    case 3 -> ModEntities.CRUISER_ATAGO.get();
                    default -> ModEntities.CRUISER_TAKAO.get();
                };
                default -> random.nextInt(2) == 1 ? ModEntities.CARRIER_KAGA.get() : ModEntities.CARRIER_AKAGI.get();
            };
        }

        return switch (random.nextInt(7)) {
            case 1 -> ModEntities.DESTROYER_HIBIKI.get();
            case 2 -> ModEntities.DESTROYER_IKAZUCHI.get();
            case 3 -> ModEntities.DESTROYER_INAZUMA.get();
            case 4 -> ModEntities.DESTROYER_SHIMAKAZE.get();
            case 5 -> ModEntities.SUBM_U511.get();
            case 6 -> ModEntities.SUBM_RO500.get();
            default -> ModEntities.DESTROYER_AKATSUKI.get();
        };
    }

    private static int countHostileMinions(ServerLevel level) {
        int count = 0;
        for (Entity entity : level.getAllEntities()) {
            if (entity instanceof EntityShipBase ship
                    && ship.isHostileShipMob()
                    && ship.getScaleLevel() < 2) {
                count++;
            }
        }
        return count;
    }

    private static int getTopWaterHeight(ServerLevel level, int x, int startY, int z) {
        int minY = level.getMinBuildHeight();
        int maxY = level.getMaxBuildHeight() - 1;
        int y = Mth.clamp(startY, minY, maxY);

        if (!isWaterAt(level, x, y, z)) {
            return y - 1;
        }

        while (y < maxY && isWaterAt(level, x, y + 1, z)) {
            y++;
        }

        return y;
    }

    private static boolean isWaterAt(ServerLevel level, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return level.getFluidState(pos).is(FluidTags.WATER);
    }

    private static boolean isWaterOrBeachBiome(ServerLevel level, BlockPos pos) {
        Holder<Biome> biome = level.getBiome(pos);
        return biome.is(BiomeTags.IS_OCEAN)
                || biome.is(BiomeTags.IS_BEACH)
                || biome.is(BiomeTags.IS_RIVER);
    }

    private static boolean hasMarriageRing(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.is(ModItems.MARRIAGE_RING.get())) {
                return true;
            }
        }
        for (ItemStack stack : player.getInventory().offhand) {
            if (!stack.isEmpty() && stack.is(ModItems.MARRIAGE_RING.get())) {
                return true;
            }
        }
        return false;
    }

    private static void broadcastBossSpawn(ServerLevel level, RandomSource random, int x, int y, int z) {
        if (level.getServer() == null) {
            return;
        }

        Component text = Component.translatable(random.nextBoolean()
                        ? "chat.shincolle:bossspawn1"
                        : "chat.shincolle:bossspawn2")
                .append(Component.literal(" " + x + " " + y + " " + z));
        level.getServer().getPlayerList().broadcastSystemMessage(text, false);
    }
}
