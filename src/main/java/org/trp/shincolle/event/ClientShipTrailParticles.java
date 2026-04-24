package org.trp.shincolle.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

import java.util.List;

@EventBusSubscriber(modid = Shincolle.MODID, value = Dist.CLIENT)
public final class ClientShipTrailParticles {
    private static final double SEARCH_RADIUS = 128.0D;
    private static final double WATER_TRAIL_SPEED_CLAMP = 0.25D;
    private static final double WATER_TRAIL_MIN_SPEED_SQR = 0.001D;
    private static final double WATER_TRAIL_OFFSET_Y = 0.4D;
    private static final double WATER_TRAIL_DISTANCE_MULT = 3.0D;
    private static final double WATER_TRAIL_SPREAD_Y = 0.15D;
    private static final double WATER_TRAIL_MOTION_SCALE = 1.5D;
    private static final int HEALTH_PARTICLE_INTERVAL = 16;
    private static final double HEALTH_PARTICLE_OFFSET_Y = 0.7D;
    private static final double HEALTH_PARTICLE_UP_SPEED = 0.05D;

    private ClientShipTrailParticles() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        Level level = minecraft.level;

        if (player == null || level == null) {
            return;
        }

        int maxParticles = getMaxTrailParticles(minecraft);
        if (maxParticles <= 0) {
            return;
        }

        AABB searchArea = player.getBoundingBox().inflate(SEARCH_RADIUS);
        List<EntityShipBase> ships = level.getEntitiesOfClass(EntityShipBase.class, searchArea);
        if (ships.isEmpty()) {
            return;
        }

        for (EntityShipBase ship : ships) {
            spawnShipTrail(level, ship, maxParticles);
            spawnShipHealthParticles(level, ship);
        }
    }

    private static int getMaxTrailParticles(Minecraft minecraft) {
        if (minecraft.options == null) {
            return 0;
        }

        ParticleStatus status = minecraft.options.particles().get();
        int setting = switch (status) {
            case ALL -> 0;
            case DECREASED -> 1;
            case MINIMAL -> 2;
        };

        return (int) ((3 - setting) * 1.8F);
    }

    private static void spawnShipTrail(Level level, EntityShipBase ship, int maxParticles) {
        if (ship.isPassenger() || ship.getShipDepth() <= 0.0D) {
            return;
        }

        Vec3 delta = ship.getDeltaMovement();
        double motX = Mth.clamp(delta.x, -WATER_TRAIL_SPEED_CLAMP, WATER_TRAIL_SPEED_CLAMP);
        double motZ = Mth.clamp(delta.z, -WATER_TRAIL_SPEED_CLAMP, WATER_TRAIL_SPEED_CLAMP);
        if (motX * motX + motZ * motZ <= WATER_TRAIL_MIN_SPEED_SQR) {
            return;
        }

        double px = ship.getX() + motX * WATER_TRAIL_DISTANCE_MULT;
        double py = ship.getY() + WATER_TRAIL_OFFSET_Y;
        double pz = ship.getZ() + motZ * WATER_TRAIL_DISTANCE_MULT;

        double width = ship.getBbWidth();
        double velX = Mth.clamp(-motX * WATER_TRAIL_MOTION_SCALE, -WATER_TRAIL_SPEED_CLAMP, WATER_TRAIL_SPEED_CLAMP);
        double velZ = Mth.clamp(-motZ * WATER_TRAIL_MOTION_SCALE, -WATER_TRAIL_SPEED_CLAMP, WATER_TRAIL_SPEED_CLAMP);
        RandomSource random = ship.getRandom();

        for (int i = 0; i < maxParticles; i++) {
            double ox = (random.nextDouble() - 0.5D) * width;
            double oy = (random.nextDouble() - 0.5D) * width * WATER_TRAIL_SPREAD_Y - 0.1F;
            double oz = (random.nextDouble() - 0.5D) * width;
            level.addParticle(ParticleTypes.CLOUD, px + ox, py + oy, pz + oz, velX, 0.0D, velZ);
        }
    }

    private static void spawnShipHealthParticles(Level level, EntityShipBase ship) {
        if ((ship.tickCount & (HEALTH_PARTICLE_INTERVAL - 1)) != 0) {
            return;
        }

        float healthRatio = ship.getHealth() / ship.getMaxHealth();
        if (healthRatio > 0.75F) {
            return;
        }

        double baseX = ship.getX();
        double baseY = ship.getY() + HEALTH_PARTICLE_OFFSET_Y;
        double baseZ = ship.getZ();
        double spread = ship.getBbWidth();
        RandomSource random = ship.getRandom();

        if (healthRatio > 0.5F) {
            spawnSmokeNormal(level, random, baseX, baseY, baseZ, spread, 3, false);
        } else if (healthRatio > 0.25F) {
            spawnSmokeNormal(level, random, baseX, baseY, baseZ, spread, 3, true);
        } else {
            spawnSmokeLarge(level, random, baseX, baseY, baseZ, spread, 4, true);
        }
    }


    private static void spawnSmokeNormal(Level level, RandomSource random, double baseX, double baseY, double baseZ,
                                         double spread, int count, boolean withFlame) {
        for (int i = 0; i < count; i++) {
            double ranX = random.nextDouble() * spread - spread / 2.0D;
            double ranY = random.nextDouble() * spread - spread / 2.0D;
            double ranZ = random.nextDouble() * spread - spread / 2.0D;
            level.addParticle(ParticleTypes.SMOKE, baseX + ranX, baseY + ranY, baseZ + ranZ, 0.0D,
                    HEALTH_PARTICLE_UP_SPEED, 0.0D);
            if (withFlame) {
                level.addParticle(ParticleTypes.FLAME, baseX + ranZ, baseY + ranY, baseZ + ranX, 0.0D,
                        HEALTH_PARTICLE_UP_SPEED, 0.0D);
            }
        }
    }

    private static void spawnSmokeLarge(Level level, RandomSource random, double baseX, double baseY, double baseZ,
                                        double spread, int count, boolean withFlame) {
        for (int i = 0; i < count; i++) {
            double ranX = random.nextDouble() * spread - spread / 2.0D;
            double ranY = random.nextDouble() * spread - spread / 2.0D;
            double ranZ = random.nextDouble() * spread - spread / 2.0D;
            level.addParticle(ParticleTypes.LARGE_SMOKE, baseX + ranX, baseY + ranY, baseZ + ranZ, 0.0D,
                    0.0D, 0.0D);
            if (withFlame) {
                level.addParticle(ParticleTypes.FLAME, baseX + ranZ, baseY + ranY, baseZ + ranX, 0.0D,
                        HEALTH_PARTICLE_UP_SPEED, 0.0D);
            }
        }
    }
}
