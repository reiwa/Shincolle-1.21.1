package org.trp.shincolle.event;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.trp.shincolle.Config;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.block.entity.CraneBlockEntity;
import org.trp.shincolle.client.tooltip.ScaledTextClientTooltip;
import org.trp.shincolle.entity.EntityAirfieldHime;
import org.trp.shincolle.entity.EntityBattleshipRu;
import org.trp.shincolle.entity.EntityDestroyerIkazuchi;
import org.trp.shincolle.entity.EntityNorthernHime;
import org.trp.shincolle.entity.EntityAircraftBase;
import org.trp.shincolle.entity.base.EntityShincolleSimpleMob;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.EntityShipBaseSimple;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.PointerItem;
import org.trp.shincolle.item.ScaledTextTooltipData;

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

        event.put(ModEntities.AIRPLANE.get(), EntityAircraftBase.createAttributes().build());
        event.put(ModEntities.AIRPLANE_T.get(), EntityAircraftBase.createAttributes().build());
        event.put(ModEntities.AIRPLANE_ZERO.get(), EntityAircraftBase.createAttributes().build());
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
        event.put(ModEntities.TAKOYAKI.get(), EntityAircraftBase.createAttributes().build());
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        HostileSpawnManager.tickPlayer(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerLogin(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            org.trp.shincolle.attachment.AdmiralData data = serverPlayer.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            if (!data.hasReceivedBook()) {
                ItemStack bookStack = new ItemStack(ModItems.DESK_ITEM_BOOK.get());
                if (!serverPlayer.addItem(bookStack)) {
                    serverPlayer.drop(bookStack, false);
                }
                data.setHasReceivedBook(true);
            }
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, new org.trp.shincolle.network.S2CAdmiralDataSyncPayload(data.serializeNBT()));
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        if (player != null && !player.level().isClientSide) {
            player.ejectPassengers();
        }
    }

    @SubscribeEvent
    public static void onPointerItemAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player == null) {
            return;
        }

        ItemStack pointerStack = getPointerStack(player);
        if (pointerStack.isEmpty()) {
            return;
        }

        if (player.level().isClientSide) {
            return;
        }

        if (player.isShiftKeyDown()) {
            if (pointerStack.getItem() instanceof PointerItem pointerItem) {
                int next = pointerItem.cycleMode(pointerStack);
                PointerItem.updateServerSideMode(player, pointerStack, next);
            }
            event.setCanceled(true);
            return;
        }

        event.setCanceled(true);

        if (!(event.getTarget() instanceof EntityShipBase ship)) {
            Entity target = event.getTarget();
            if (target instanceof net.minecraft.world.entity.LivingEntity && !(target instanceof Player)) {
                String targetName = target.getClass().getSimpleName();
                org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
                boolean added = data.toggleCustomTargetClass(targetName);
                player.displayClientMessage(Component.translatable("chat.shincolle.pointer.settargetclass", "  " + targetName), false);
                if (added) {
                    player.displayClientMessage(Component.literal("ADD: ").withStyle(net.minecraft.ChatFormatting.AQUA)
                            .append(Component.literal(targetName).withStyle(net.minecraft.ChatFormatting.YELLOW)), false);
                } else {
                    player.displayClientMessage(Component.literal("REMOVE: ").withStyle(net.minecraft.ChatFormatting.RED)
                            .append(Component.literal(targetName).withStyle(net.minecraft.ChatFormatting.YELLOW)), false);
                }
                net.neoforged.neoforge.network.PacketDistributor.sendToPlayer((net.minecraft.server.level.ServerPlayer) player, new org.trp.shincolle.network.S2CAdmiralDataSyncPayload(data.serializeNBT()));
            }
            return;
        }

        if (!ship.isAlive() || ship.isInDeadPose() || !ship.isOwnedBy(player)) {
            return;
        }

        int mode = pointerStack.getItem() instanceof PointerItem pi ? pi.getMode(pointerStack) : PointerItem.MODE_SINGLE;
        if (mode == PointerItem.MODE_GROUP || mode == PointerItem.MODE_FORMATION) {
            org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            int teamId = data.getCurrentTeamID();
            int existingTeam = -1;
            int existingSlot = -1;
            for (int t = 0; t < org.trp.shincolle.attachment.AdmiralData.TEAM_COUNT; t++) {
                for (int s = 0; s < org.trp.shincolle.attachment.AdmiralData.SLOT_COUNT; s++) {
                    if (ship.getUUID().equals(data.getShipUUID(t, s))) {
                        existingTeam = t;
                        existingSlot = s;
                        break;
                    }
                }
                if (existingTeam != -1) break;
            }

            if (existingTeam != -1) {
                if (existingTeam == teamId) {
                    if (mode == PointerItem.MODE_FORMATION) {
                        data.setShipUUID(teamId, existingSlot, null);
                        data.setSelected(teamId, existingSlot, true);
                        ship.setFormationTeam(-1);
                        ship.setFormationSlot(-1);
                        ship.setPointerSelected(false);
                    } else {
                        boolean nextState = !data.isSelected(teamId, existingSlot);
                        data.setSelected(teamId, existingSlot, nextState);
                        ship.setPointerSelected(nextState);
                    }
                } else {
                    if (mode == PointerItem.MODE_FORMATION) {
                        int emptySlot = data.findFirstEmptySlot(teamId);
                        if (emptySlot != -1) {
                            data.setShipUUID(existingTeam, existingSlot, null);
                            data.setSelected(existingTeam, existingSlot, true);
                            
                            data.setShipUUID(teamId, emptySlot, ship.getUUID());
                            data.setSelected(teamId, emptySlot, true);
                            ship.setFormationTeam(teamId);
                            ship.setFormationSlot(emptySlot);
                            ship.setPointerSelected(true);
                        }
                    } else {
                        ship.togglePointerSelected();
                    }
                }
                net.neoforged.neoforge.network.PacketDistributor.sendToPlayer((net.minecraft.server.level.ServerPlayer) player, new org.trp.shincolle.network.S2CAdmiralDataSyncPayload(data.serializeNBT()));
            } else if (mode == PointerItem.MODE_FORMATION) {
                int emptySlot = data.findFirstEmptySlot(teamId);
                if (emptySlot != -1) {
                    data.setShipUUID(teamId, emptySlot, ship.getUUID());
                    data.setSelected(teamId, emptySlot, true);
                    ship.setFormationTeam(teamId);
                    ship.setFormationSlot(emptySlot);
                    ship.setPointerSelected(true);
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer((net.minecraft.server.level.ServerPlayer) player, new org.trp.shincolle.network.S2CAdmiralDataSyncPayload(data.serializeNBT()));
                }
            } else {
                ship.togglePointerSelected();
            }

            if (!ship.isPointerSelected()) {
                ship.clearPointerTarget();
                ship.clearPointerTargetEntity();
            }
            return;
        }

        PointerItem.clearOwnedPointerSelection(player, ship, POINTER_SEARCH_RADIUS);
        PointerItem.updateServerSideMode(player, pointerStack, PointerItem.MODE_SINGLE);
        ship.setPointerSelected(true);
    }

    @SubscribeEvent
    public static void onPointerItemLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        if (player == null) return;
        ItemStack pointerStack = getPointerStack(player);
        if (pointerStack.isEmpty()) return;

        if (player.level().isClientSide) {
            return;
        }

        if (player.isShiftKeyDown()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPointerItemRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack pointerStack = player == null ? ItemStack.EMPTY : getPointerStack(player);
        if (player == null || pointerStack.isEmpty() || player.isShiftKeyDown()) {
            return;
        }

        handlePointerTargetCommand(player, pointerStack);
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide));
    }

    @SubscribeEvent
    public static void onPointerItemRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack pointerStack = player == null ? ItemStack.EMPTY : getPointerStack(player);
        if (player == null || pointerStack.isEmpty() || player.isShiftKeyDown()) {
            return;
        }

        handlePointerTargetCommand(player, pointerStack);
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide));
    }


    @SubscribeEvent
    public static void onHostileEntityDropsGrudge(LivingDropsEvent event) {
        Entity target = event.getEntity();
        if (target.level().isClientSide) {
            return;
        }

        if (!isHostileDropTarget(target)) {
            return;
        }

        if (!target.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            return;
        }

        Entity sourceEntity = event.getSource().getEntity();
        if (sourceEntity instanceof EntityShipBase ship) {
            ship.addShipExp(Config.shipExpGainKill);
        }

        float dropRate = Math.max(0.0F, Config.hostileDropGrudgeRate);
        if (dropRate <= 0.0F) {
            return;
        }

        int fixedDrop = (int) dropRate;
        if (fixedDrop > 0) {
            event.getDrops().add(new ItemEntity(target.level(),
                    target.getX(), target.getY(), target.getZ(), new ItemStack(ModItems.GRUDGE.get(), fixedDrop)));
        }

        if (target.getRandom().nextFloat() < (dropRate - fixedDrop)) {
            event.getDrops().add(new ItemEntity(target.level(),
                    target.getX(), target.getY(), target.getZ(), new ItemStack(ModItems.GRUDGE.get())));
        }
    }

    private static boolean isHostileDropTarget(Entity entity) {
        if (entity instanceof EntityShipBase ship) {
            return ship.isHostileShipMob();
        }
        return entity instanceof Enemy || entity instanceof Slime || entity instanceof AbstractGolem;
    }

    private static ItemStack getPointerStack(Player player) {
        ItemStack main = player.getMainHandItem();
        if (isPointerItem(main)) {
            return main;
        }
        ItemStack off = player.getOffhandItem();
        if (isPointerItem(off)) {
            return off;
        }
        return ItemStack.EMPTY;
    }

    private static boolean isPointerItem(ItemStack stack) {
        return !stack.isEmpty() && stack.is(ModItems.POINTER_ITEM.get());
    }



    private static void handlePointerTargetCommand(Player player, ItemStack pointerStack) {
        if (player == null || player.level().isClientSide) {
            return;
        }

        if (pointerStack.isEmpty()) {
            return;
        }

        AABB searchArea = player.getBoundingBox().inflate(POINTER_SEARCH_RADIUS);
        List<EntityShipBase> ships = player.level().getEntitiesOfClass(EntityShipBase.class, searchArea,
                ship -> ship.isOwnedBy(player) && ship.isPointerSelected() && !ship.isInDeadPose());
        if (ships.isEmpty()) {
            return;
        }

        if (!(pointerStack.getItem() instanceof PointerItem pointerItem)) {
            return;
        }

        int mode = pointerItem.getMode(pointerStack);
        if (mode == PointerItem.MODE_SINGLE && ships.size() > 1) {
            ships.sort((a, b) -> Double.compare(a.distanceToSqr(player), b.distanceToSqr(player)));
            EntityShipBase selected = ships.get(0);
            PointerItem.updateServerSideMode(player, pointerStack, PointerItem.MODE_SINGLE);
            ships = List.of(selected);
        } else if (mode == PointerItem.MODE_FORMATION) {
            org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            int tid = data.getCurrentTeamID();
            ships = player.level().getEntitiesOfClass(EntityShipBase.class, searchArea,
                    ship -> ship.isOwnedBy(player) && ship.getFormationTeam() == tid && !ship.isInDeadPose());
        }

        EntityHitResult hitRes = getLookTargetResult(player);
        if (hitRes != null) {
            Entity target = hitRes.getEntity();
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
        BlockHitResult blockHit = getLookBlockResult(player);
        BlockPos guardPos = null;
        if (blockHit != null && player.level().getBlockEntity(blockHit.getBlockPos()) instanceof org.trp.shincolle.block.entity.IWaypoint wp) {
            BlockPos resolved = resolveWaypointTarget(player.level(), blockHit.getBlockPos(), wp);
            guardPos = resolved;
            target = Vec3.atBottomCenterOf(resolved);
        }
        for (EntityShipBase ship : ships) {
            if (ship.hasPointerTarget() && isSamePointerTarget(ship.getPointerTarget(), target)) {
                ship.clearPointerTarget();
                continue;
            }
            ship.setPointerTarget(target, POINTER_TARGET_DURATION_TICKS);
            ship.clearPointerTargetEntity();

            
            if (guardPos != null) {
                ship.setStateMinor(EntityShipBase.STATE_MINOR_GUARD_X, guardPos.getX());
                ship.setStateMinor(EntityShipBase.STATE_MINOR_GUARD_Y, guardPos.getY());
                ship.setStateMinor(EntityShipBase.STATE_MINOR_GUARD_Z, guardPos.getZ());
                ship.setStateMinor(24, 1); 
                ship.setStateFlag(EntityShipBase.STATE_FLAG_DISABLE_GUARD_POS, false); 
            }
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
        BlockHitResult hit = getLookBlockResult(player);
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) {
            return null;
        }
        
        BlockPos pos = hit.getBlockPos();
        if (player.level().getBlockEntity(pos) instanceof org.trp.shincolle.block.entity.IWaypoint) {
            return Vec3.atBottomCenterOf(pos).add(0.0, 1.0, 0.0);
        }
        return Vec3.atBottomCenterOf(pos).add(0.0, 1.0, 0.0);
    }

    private static BlockHitResult getLookBlockResult(Player player) {
        double reach = POINTER_SEARCH_RADIUS;
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * reach, look.y * reach, look.z * reach);
        return player.level().clip(new ClipContext(eyePos, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, player));
    }

    private static BlockPos resolveWaypointTarget(Level level, BlockPos waypointPos, org.trp.shincolle.block.entity.IWaypoint waypoint) {
        BlockPos next = waypoint.getNextPos();
        if (isCraneTarget(level, next)) {
            return next;
        }
        BlockPos chest = waypoint.getChestPos();
        if (isCraneTarget(level, chest)) {
            return chest;
        }
        return waypointPos;
    }

    private static boolean isCraneTarget(Level level, BlockPos pos) {
        if (pos == null || pos.equals(BlockPos.ZERO)) {
            return false;
        }
        return level.getBlockEntity(pos) instanceof CraneBlockEntity;
    }

    public static EntityHitResult getLookTargetResult(Player player) {
        double reach = POINTER_SEARCH_RADIUS;
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * reach, look.y * reach, look.z * reach);
        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(reach)).inflate(1.0D);
        return ProjectileUtil.getEntityHitResult(player.level(), player, eyePos, end, searchBox,
                entity -> !entity.isSpectator() && entity.isPickable() && entity != player);
    }

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ScaledTextTooltipData.class, ScaledTextClientTooltip::new);
    }
}
