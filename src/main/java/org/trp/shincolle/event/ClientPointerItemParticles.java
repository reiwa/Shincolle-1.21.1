package org.trp.shincolle.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.particle.ParticleTeam;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModParticles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Shincolle.MODID, value = Dist.CLIENT)
public final class ClientPointerItemParticles {
    private static final int PARTICLE_INTERVAL_TICKS = 10;
    private static final double SEARCH_RADIUS = 100.0;

    private ClientPointerItemParticles() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        Level level = minecraft.level;

        if (player == null || level == null) {
            return;
        }

        if (!isHoldingPointerItem(player)) {
            ParticleTeam.clearAllFollowParticles();
            return;
        }

        if (level.getGameTime() % PARTICLE_INTERVAL_TICKS != 0) {
            return;
        }

        AABB searchArea = player.getBoundingBox().inflate(SEARCH_RADIUS);
        List<EntityShipBase> ships = level.getEntitiesOfClass(EntityShipBase.class, searchArea,
                ship -> ship.isOwnedBy(player) && !ship.isInDeadPose());
        if (ships.isEmpty()) {
            ParticleTeam.clearFollowParticles(ParticleTeam.FollowKind.SHIP_MARKER, null);
            return;
        }

        Set<Integer> activeShipIds = new HashSet<>();
        for (EntityShipBase ship : ships) {
            activeShipIds.add(ship.getId());
            spawnShipMarker(level, ship);
        }

        ParticleTeam.clearFollowParticles(ParticleTeam.FollowKind.SHIP_MARKER, activeShipIds);
    }

    @SubscribeEvent
    public static void onPointerItemUse(PlayerInteractEvent.RightClickItem event) {
        handlePointerTargetMarker(event.getLevel(), event.getEntity());
    }

    @SubscribeEvent
    public static void onPointerItemUse(PlayerInteractEvent.RightClickBlock event) {
        handlePointerTargetMarker(event.getLevel(), event.getEntity());
    }

    @SubscribeEvent
    public static void onPointerItemUse(PlayerInteractEvent.EntityInteract event) {
        handlePointerEntityMarker(event.getLevel(), event.getEntity(), event.getTarget());
    }

    private static boolean isHoldingPointerItem(Player player) {
        return isPointerItem(player.getMainHandItem()) || isPointerItem(player.getOffhandItem());
    }

    private static boolean isPointerItem(ItemStack stack) {
        return !stack.isEmpty() && stack.is(ModItems.POINTER_ITEM.get());
    }

    private static void spawnShipMarker(Level level, EntityShipBase ship) {
        double baseX = ship.getX();
        double baseY = ship.getY();
        double baseZ = ship.getZ();

        ParticleTeam.RenderStyle desiredStyle = ship.isPointerSelected()
                ? ParticleTeam.RenderStyle.DEFAULT_BLUE
                : ParticleTeam.RenderStyle.DEFAULT_GREEN;
        ParticleTeam existing = ParticleTeam.getFollowParticle(ParticleTeam.FollowKind.SHIP_MARKER, ship.getId());
        if (existing != null) {
            if (existing.isAliveParticle() && existing.getRenderStyle() == desiredStyle) {
                return;
            }
            ParticleTeam.removeFollowParticle(ParticleTeam.FollowKind.SHIP_MARKER, ship.getId());
        }

        if (ship.isPointerSelected()) {
            level.addParticle(ModParticles.PARTICLE_TEAM_SELECTED.get(), baseX, baseY, baseZ,
                    ship.getBbHeight(), ship.getId(), ParticleTeam.FollowKind.SHIP_MARKER.getMarkerId());
        } else {
            level.addParticle(ModParticles.PARTICLE_TEAM.get(), baseX, baseY, baseZ,
                    ship.getBbHeight(), ship.getId(), ParticleTeam.FollowKind.SHIP_MARKER.getMarkerId());
        }
    }

    private static void spawnTargetMarker(Level level, Vec3 target) {
        level.addParticle(ModParticles.PARTICLE_TEAM_TARGET.get(),
                target.x, target.y, target.z,
                0.1D, -1.0D, ParticleTeam.FollowKind.NONE.getMarkerId());
    }

    private static void spawnEntityTargetMarker(Level level, Vec3 target) {
        level.addParticle(ModParticles.PARTICLE_TEAM_TARGET_ENTITY.get(),
                target.x, target.y, target.z,
                0.1D, -1.0D, ParticleTeam.FollowKind.NONE.getMarkerId());
    }

    private static void spawnEntityTargetMarker(Level level, Entity target) {
        ParticleTeam existing = ParticleTeam.getFollowParticle(ParticleTeam.FollowKind.TARGET_ENTITY, target.getId());
        if (existing != null) {
            ParticleTeam.removeFollowParticle(ParticleTeam.FollowKind.TARGET_ENTITY, target.getId());
        }
        Vec3 pos = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        level.addParticle(ModParticles.PARTICLE_TEAM_TARGET_ENTITY.get(),
                pos.x, pos.y, pos.z,
                0.1D, target.getId(), ParticleTeam.FollowKind.TARGET_ENTITY.getMarkerId());
    }

    private static void handlePointerTargetMarker(Level level, Player player) {
        if (level == null || !level.isClientSide) {
            return;
        }

        if (player == null || !isHoldingPointerItem(player)) {
            return;
        }

        AABB searchArea = player.getBoundingBox().inflate(SEARCH_RADIUS);
        List<EntityShipBase> ships = level.getEntitiesOfClass(EntityShipBase.class, searchArea,
                ship -> ship.isOwnedBy(player) && ship.isPointerSelected() && !ship.isInDeadPose());
        if (ships.isEmpty()) {
            return;
        }

        EntityHitResult entityHit = getLookTargetEntity(player);
        if (entityHit != null) {
            Entity entity = entityHit.getEntity();
            if (entity != player && !(entity instanceof EntityShipBase ship && ship.isOwnedBy(player))) {
                spawnEntityTargetMarker(level, entity);
                return;
            }
        }

        Vec3 target = getLookTarget(player, level);
        if (target == null) {
            return;
        }
        spawnTargetMarker(level, target);
    }

    private static void handlePointerEntityMarker(Level level, Player player, Entity target) {
        if (level == null || !level.isClientSide) {
            return;
        }

        if (player == null || !isHoldingPointerItem(player)) {
            return;
        }

        if (target == null) {
            return;
        }

        if (target == player) {
            return;
        }

        if (target instanceof EntityShipBase ship && ship.isOwnedBy(player)) {
            return;
        }

        AABB searchArea = player.getBoundingBox().inflate(SEARCH_RADIUS);
        List<EntityShipBase> ships = level.getEntitiesOfClass(EntityShipBase.class, searchArea,
                ship -> ship.isOwnedBy(player) && ship.isPointerSelected() && !ship.isInDeadPose());
        if (ships.isEmpty()) {
            return;
        }

        spawnEntityTargetMarker(level, target);
    }

    private static EntityHitResult getLookTargetEntity(Player player) {
        double range = SEARCH_RADIUS;
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * range, look.y * range, look.z * range);
        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(range)).inflate(1.0D);
        return ProjectileUtil.getEntityHitResult(player.level(), player, eyePos, end, searchBox,
                entity -> !entity.isSpectator() && entity.isPickable() && entity != player);
    }

    private static Vec3 getLookTarget(Player player, Level level) {
        double range = SEARCH_RADIUS;

        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);

        Vec3 end = eyePos.add(look.x * range, look.y * range, look.z * range);

        BlockHitResult hit = level.clip(new ClipContext(
                eyePos,
                end,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.ANY,
                player
        ));

        if (hit.getType() != HitResult.Type.BLOCK) {
            return null;
        }

        return Vec3.atCenterOf(hit.getBlockPos()).add(0.0D, 1.0D, 0.0D);
    }
}