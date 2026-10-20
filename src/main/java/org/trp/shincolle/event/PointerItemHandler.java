package org.trp.shincolle.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.PointerItem;
import org.trp.shincolle.network.C2SPointerActionPayload;
import org.trp.shincolle.network.ModNetwork;

import java.util.Optional;

@EventBusSubscriber(modid = Shincolle.MODID)
public class PointerItemHandler {

    private static final double SEARCH_RADIUS = 100.0D;

    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        handleLeftClick(event.getEntity());
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getLevel().isClientSide) {
            boolean handled = handleLeftClick(event.getEntity());
            if (handled) {
                event.setCanceled(true);
            }
        }
    }

    private static boolean handleLeftClick(Player player) {
        if (player == null || !player.level().isClientSide) {
            return false;
        }

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        boolean hasPointerInMain = main.is(ModItems.POINTER_ITEM.get());
        boolean hasPointerInOff = off.is(ModItems.POINTER_ITEM.get());

        ItemStack pointer = ItemStack.EMPTY;
        if (hasPointerInMain) {
            pointer = main;
        } else if (hasPointerInOff && main.isEmpty()) {
            pointer = off;
        }

        if (pointer.isEmpty() || !(pointer.getItem() instanceof PointerItem pointerItem)) {
            return false;
        }

        if (player.isShiftKeyDown()) {
            pointerItem.onSwingMiss(player, pointer);
            return true;
        }

        EntityHitResult hit = getLookTargetEntity(player, SEARCH_RADIUS);
        if (hit != null && hit.getEntity() != null) {
            Entity target = hit.getEntity();
            ModNetwork.sendToServer(
                new C2SPointerActionPayload(
                    7,
                    Optional.of(target.getUUID()),
                    Optional.empty()
                )
            );
            return true;
        }

        return false;
    }

    private static EntityHitResult getLookTargetEntity(Player player, double range) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * range, look.y * range, look.z * range);

        BlockHitResult blockHit = player.level().clip(new ClipContext(eyePos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        double maxDist = range;
        if (blockHit.getType() != HitResult.Type.MISS) {
            maxDist = eyePos.distanceTo(blockHit.getLocation());
            end = eyePos.add(look.scale(maxDist));
        }

        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(maxDist)).inflate(1.0D);
        return ProjectileUtil.getEntityHitResult(
            player.level(),
            player,
            eyePos,
            end,
            searchBox,
            entity -> !entity.isSpectator() && entity.isPickable() && entity != player
        );
    }
}
