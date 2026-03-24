package org.trp.shincolle.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.EntityAirfieldHime;
import org.trp.shincolle.entity.EntityBattleshipRu;
import org.trp.shincolle.entity.EntityDestroyerIkazuchi;
import org.trp.shincolle.entity.EntityNorthernHime;
import org.trp.shincolle.entity.base.EntityShincolleSimpleMob;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.EntityShipBaseSimple;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.List;

@EventBusSubscriber(modid = Shincolle.MODID)
public class ModEventBusEvents {

    private static final double POINTER_SEARCH_RADIUS = 100.0;
    private static final long POINTER_TARGET_DURATION_TICKS = 20L * 60L * 5L;
    private static final double POINTER_TARGET_SAME_DISTANCE_SQR = 0.25D;

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NORTHERN_HIME.get(), EntityNorthernHime.createAttributes().build());
        event.put(ModEntities.DESTROYER_IKAZUCHI.get(), EntityDestroyerIkazuchi.createAttributes().build());
        event.put(ModEntities.AIRFIELD_HIME.get(), EntityAirfieldHime.createAttributes().build());
        event.put(ModEntities.BATTLESHIP_RU.get(), EntityBattleshipRu.createAttributes().build());

        event.put(ModEntities.BATTLESHIP_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BATTLESHIP_NAGATO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BATTLESHIP_RE.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BATTLESHIP_TA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BATTLESHIP_YAMATO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BB_HARUNA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BB_HIEI.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BB_KIRISHIMA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.BB_KONGOU.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CA_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CARRIER_AKAGI.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CARRIER_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CARRIER_KAGA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CARRIER_W_DEMON.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CARRIER_WO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CRUISER_ATAGO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CRUISER_TAKAO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CRUISER_TATSUTA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.CRUISER_TENRYUU.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_AKATSUKI.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_HA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_HIBIKI.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_I.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_INAZUMA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_NI.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_RO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.DESTROYER_SHIMAKAZE.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.HARBOUR_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.HEAVY_CRUISER_NE.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.HEAVY_CRUISER_RI.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.ISOLATED_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.MIDWAY_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.SSNH.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.SUBM_HIME.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.SUBM_KA.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.SUBM_RO500.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.SUBM_SO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.SUBM_U511.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.SUBM_YO.get(), EntityShipBaseSimple.createAttributes().build());
        event.put(ModEntities.TRANSPORT_WA.get(), EntityShipBaseSimple.createAttributes().build());

        event.put(ModEntities.AIRPLANE.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.AIRPLANE_T.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.AIRPLANE_ZERO.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_AF_H.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_BA_H.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_CA_H.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_CA_WD.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_HB_H.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_IS_H.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_MI_H.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.MOUNT_SU_H.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.RENSOUHOU.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.RENSOUHOU_S.get(), EntityShincolleSimpleMob.createAttributes().build());
        event.put(ModEntities.TAKOYAKI.get(), EntityShincolleSimpleMob.createAttributes().build());
    }

    @SubscribeEvent
    public static void onPointerItemAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide) {
            return;
        }

        if (!isHoldingPointerItem(player)) {
            return;
        }

        event.setCanceled(true);

        if (!(event.getTarget() instanceof EntityShipBase ship)) {
            return;
        }

        if (!ship.isAlive() || ship.isInDeadPose() || !ship.isOwnedBy(player)) {
            return;
        }

        ship.togglePointerSelected();
        if (!ship.isPointerSelected()) {
            ship.clearPointerTarget();
            ship.clearPointerTargetEntity();
        }
    }


    @SubscribeEvent
    public static void onPointerItemRightClickBlock(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (player == null || !isHoldingPointerItem(player)) {
            return;
        }
        handlePointerTargetCommand(player);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onPointerItemRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (player == null || !isHoldingPointerItem(player)) {
            return;
        }
        handlePointerTargetCommand(player);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onPointerItemRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide) {
            return;
        }

        if (!isHoldingPointerItem(player)) {
            return;
        }

        Entity target = event.getTarget();
        if (target == null) {
            return;
        }

        if (target == player) {
            event.setCanceled(true);
            return;
        }

        if (target instanceof EntityShipBase ship && ship.isOwnedBy(player)) {
            event.setCanceled(true);
            return;
        }

        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onHostileEntityDropsGrudge(LivingDropsEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (!(event.getEntity() instanceof Enemy)) {
            return;
        }

        if (!(event.getSource().getEntity() instanceof Player)) {
            return;
        }

        ItemStack drop = new ItemStack(ModItems.GRUDGE.get());
        event.getDrops().add(new ItemEntity(event.getEntity().level(),
                event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), drop));
    }

    private static boolean isHoldingPointerItem(Player player) {
        return isPointerItem(player.getMainHandItem()) || isPointerItem(player.getOffhandItem());
    }

    private static boolean isPointerItem(ItemStack stack) {
        return !stack.isEmpty() && stack.is(ModItems.POINTER_ITEM.get());
    }

    private static void handlePointerTargetCommand(Player player) {
        if (player == null || player.level().isClientSide) {
            return;
        }

        if (!isHoldingPointerItem(player)) {
            return;
        }

        AABB searchArea = player.getBoundingBox().inflate(POINTER_SEARCH_RADIUS);
        List<EntityShipBase> ships = player.level().getEntitiesOfClass(EntityShipBase.class, searchArea,
                ship -> ship.isOwnedBy(player) && ship.isPointerSelected() && !ship.isInDeadPose());
        if (ships.isEmpty()) {
            return;
        }

        EntityHitResult entityHit = getLookTargetEntity(player);
        if (entityHit != null) {
            Entity target = entityHit.getEntity();
            if (target == player || target instanceof EntityShipBase ship && ship.isOwnedBy(player)) {
                return;
            }
            for (EntityShipBase ship : ships) {
                if (ship.hasPointerTargetEntity() && ship.getPointerTargetEntity() == target) {
                    ship.clearPointerTargetEntity();
                    ship.clearPointerTarget();
                    continue;
                }
                ship.setPointerTargetEntity(target, POINTER_TARGET_DURATION_TICKS);
                ship.clearPointerTarget();
            }
            return;
        }

        Vec3 target = getLookTarget(player);
        if (target == null) {
            return;
        }
        for (EntityShipBase ship : ships) {
            if (ship.hasPointerTarget() && isSamePointerTarget(ship.getPointerTarget(), target)) {
                ship.clearPointerTarget();
                continue;
            }
            ship.setPointerTarget(target, POINTER_TARGET_DURATION_TICKS);
            ship.clearPointerTargetEntity();
        }
    }

    private static boolean isSamePointerTarget(Vec3 current, Vec3 next) {
        if (current == null || next == null) {
            return false;
        }
        return current.distanceToSqr(next) <= POINTER_TARGET_SAME_DISTANCE_SQR;
    }

    private static EntityHitResult getLookTargetEntity(Player player) {
        double reach = POINTER_SEARCH_RADIUS;
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * reach, look.y * reach, look.z * reach);
        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(reach)).inflate(1.0D);
        return ProjectileUtil.getEntityHitResult(player.level(), player, eyePos, end, searchBox,
                entity -> !entity.isSpectator() && entity.isPickable() && entity != player);
    }

    private static Vec3 getLookTarget(Player player) {
        double reach = POINTER_SEARCH_RADIUS;
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * reach, look.y * reach, look.z * reach);
        BlockHitResult hit = player.level().clip(new ClipContext(eyePos, end, ClipContext.Block.OUTLINE,
                ClipContext.Fluid.ANY, player));
        if (hit.getType() != HitResult.Type.BLOCK) {
            return null;
        }
        return Vec3.atCenterOf(hit.getBlockPos()).add(0.0D, 1.0D, 0.0D);
    }
}