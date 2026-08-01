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
import org.trp.shincolle.entity.*;
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
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        HostileSpawnManager.tickPlayer(player);

        if (!player.level().isClientSide) {
            if (player.tickCount % 16 == 0) {
                updatePlayerRingStatus(player);
            }
            updatePlayerAbilities(player);

            int timer = org.trp.shincolle.item.BucketRepairItem.getParticleTimer(player);
            if (timer > 0) {
                org.trp.shincolle.item.BucketRepairItem.setParticleTimer(player, timer - 1);

                if (player.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                    double px = player.getX();
                    double py = player.getY();
                    double pz = player.getZ();
                    net.minecraft.util.RandomSource rand = player.getRandom();

                    for (int i = 0; i < 3; i++) {
                        double x = px + (rand.nextDouble() - 0.5D) * 1.5D;
                        double y = py + rand.nextDouble() * 2.0D;
                        double z = pz + (rand.nextDouble() - 0.5D) * 1.5D;

                        serverLevel.sendParticles(
                            org.trp.shincolle.init.ModParticles.PARTICLE_FOG.get(),
                            x, y, z,
                            1,
                            0.0D, 0.0D, 0.0D,
                            0.0D
                        );
                    }
                }
            }
        }
    }

    private static void updatePlayerRingStatus(Player player) {
        org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
        ItemStack ringStack = ItemStack.EMPTY;
        for (ItemStack itemStack : player.getInventory().items) {
            if (!itemStack.isEmpty() && itemStack.is(ModItems.MARRIAGE_RING.get())) {
                ringStack = itemStack;
                break;
            }
        }
        boolean hasRing = !ringStack.isEmpty();
        if (ringStack.isEmpty() && player.getOffhandItem().is(ModItems.MARRIAGE_RING.get())) {
            ringStack = player.getOffhandItem();
            hasRing = true;
        }

        boolean oldHasRing = data.hasRing();
        data.setHasRing(hasRing);

        boolean isActive = false;
        if (hasRing) {
            net.minecraft.world.item.component.CustomData customData = ringStack.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
            if (customData != null) {
                isActive = customData.copyTag().getBoolean("isActive");
            }
        }
        data.setRingActive(isActive);

        if (oldHasRing && !hasRing) {
            if (!player.getAbilities().instabuild && player.getAbilities().flying) {
                player.getAbilities().flying = false;
                player.onUpdateAbilities();
            }
        }
    }

    private static void updatePlayerAbilities(Player player) {
        org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
        if (!data.hasRing() || !data.isRingActive()) {
            return;
        }

        int marriageNum = data.getMarriageNum();

        if (Config.ringAbility[0] >= 0 && marriageNum >= Config.ringAbility[0] && player.getAirSupply() < 300) {
            if (player.tickCount % 20 == 0) {
                player.setAirSupply(300);
            }
        }

        if (Config.ringAbility[1] >= 0 && marriageNum >= Config.ringAbility[1]) {
            boolean inLiquid = player.isInWaterOrBubble() || player.isInLava();
            if (inLiquid) {
                if (!player.getAbilities().flying) {
                    player.getAbilities().flying = true;
                    data.setRingFlying(true);
                    player.onUpdateAbilities();
                }
            } else {
                if (data.isRingFlying() && !player.getAbilities().instabuild && player.getAbilities().flying) {
                    player.getAbilities().flying = false;
                    data.setRingFlying(false);
                    player.onUpdateAbilities();
                }
            }
        }
    }

    @SubscribeEvent
    public static void onGetBreakSpeed(net.neoforged.neoforge.event.entity.player.PlayerEvent.BreakSpeed event) {
        if (Config.ringAbility[2] <= 0) return;
        Player player = event.getEntity();
        org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
        if (data.hasRing() && data.isRingActive() && (player.isInWaterOrBubble() || player.isInLava())) {
            int marriageNum = Math.min(data.getMarriageNum(), Config.ringAbility[2]);
            float digBoost = 1.0f + marriageNum * 0.2f;
            event.setNewSpeed(event.getOriginalSpeed() * 5.0f * digBoost);
        }
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.level().isClientSide) return;
            org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            if (data.hasRing() && data.isRingActive() && Config.ringAbility[4] >= 0 && data.getMarriageNum() >= Config.ringAbility[4]) {
                if (event.getSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE)) {
                    if (player.isOnFire()) {
                        player.clearFire();
                    }
                    event.setCanceled(true);
                }
            }
        }
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
            
            java.util.HashSet<Integer> collected = serverPlayer.getData(org.trp.shincolle.init.ModDataAttachments.COLLECTED_SHIPS);
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, new org.trp.shincolle.network.S2CCollectedShipsSyncPayload(new java.util.ArrayList<>(collected)));
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(net.neoforged.neoforge.event.entity.player.PlayerEvent.Clone event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer newPlayer) {
            java.util.HashSet<Integer> collected = newPlayer.getData(org.trp.shincolle.init.ModDataAttachments.COLLECTED_SHIPS);
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(newPlayer, new org.trp.shincolle.network.S2CCollectedShipsSyncPayload(new java.util.ArrayList<>(collected)));

            org.trp.shincolle.attachment.AdmiralData data = newPlayer.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(newPlayer, new org.trp.shincolle.network.S2CAdmiralDataSyncPayload(data.serializeNBT()));
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            java.util.HashSet<Integer> collected = serverPlayer.getData(org.trp.shincolle.init.ModDataAttachments.COLLECTED_SHIPS);
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, new org.trp.shincolle.network.S2CCollectedShipsSyncPayload(new java.util.ArrayList<>(collected)));
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

        ItemStack pointerStack = getPointerStackForLeftClick(player);
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
        ItemStack pointerStack = getPointerStackForLeftClick(player);
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
        if (player == null) return;
        ItemStack pointerStack = event.getItemStack();
        if (!isPointerItem(pointerStack) || player.isShiftKeyDown()) {
            return;
        }

        handlePointerTargetCommand(player, pointerStack);
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide));
    }

    @SubscribeEvent
    public static void onPointerItemRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (player == null) return;
        ItemStack pointerStack = event.getItemStack();
        if (!isPointerItem(pointerStack) || player.isShiftKeyDown()) {
            return;
        }

        handlePointerTargetCommand(player, pointerStack);
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide));
    }

    @SubscribeEvent
    public static void onPointerItemInteractEntity(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (player == null) return;
        ItemStack pointerStack = event.getItemStack();
        if (!isPointerItem(pointerStack)) {
            return;
        }

        if (player.isShiftKeyDown()) {
            return;
        }

        if (pointerStack.getItem() instanceof PointerItem pointerItem && pointerItem.isPetting(pointerStack)) {
            if (event.getTarget() instanceof EntityShipBase) {
                return;
            }
        }

        if (event.getTarget() instanceof EntityShipBase ship && ship.isOwnedBy(player)) {
            return;
        }

        if (!player.level().isClientSide) {
            handlePointerTargetCommand(player, pointerStack);
        }
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
            ship.addMorale(2);
        }

        float dropRate = Math.max(0.0F, Config.dropGrudge);
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

    private static ItemStack getPointerStackForLeftClick(Player player) {
        ItemStack main = player.getMainHandItem();
        if (isPointerItem(main)) {
            return main;
        }
        ItemStack off = player.getOffhandItem();
        if (isPointerItem(off) && main.isEmpty()) {
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
                ship.getStateComponent().setGuardX(guardPos.getX());
                ship.getStateComponent().setGuardY(guardPos.getY());
                ship.getStateComponent().setGuardZ(guardPos.getZ());
                ship.getStateComponent().setGuardType(1); 
                ship.getStateComponent().setStateDisableGuardPos(false); 
                if (ship.getFuel() > 0) {
                    ship.getNavigation().moveTo(guardPos.getX() + 0.5D, guardPos.getY(), guardPos.getZ() + 0.5D, 1.2D);
                }
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
    public static void onRegisterCommands(net.neoforged.neoforge.event.RegisterCommandsEvent event) {
        org.trp.shincolle.command.ModCommands.register(event.getDispatcher(), event.getBuildContext());
    }

    @SubscribeEvent
    public static void onLootTableLoad(net.neoforged.neoforge.event.LootTableLoadEvent event) {
        if (event.getName().getNamespace().equals(org.trp.shincolle.Shincolle.MODID)) {
            float rate = 1.0f;
            int level = org.trp.shincolle.Config.consumptionLevel;
            if (level == 1) {
                rate = 0.5f;
            } else if (level == 2) {
                rate = 0.1f;
            }
            if (rate < 1.0f) {
                net.minecraft.world.level.storage.loot.predicates.LootItemCondition cond = 
                    net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance(rate).build();
                try {
                    java.lang.reflect.Field poolsField = net.minecraft.world.level.storage.loot.LootTable.class.getDeclaredField("pools");
                    poolsField.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    java.util.List<net.minecraft.world.level.storage.loot.LootPool> pools = 
                        (java.util.List<net.minecraft.world.level.storage.loot.LootPool>) poolsField.get(event.getTable());
                    
                    if (pools != null) {
                        for (net.minecraft.world.level.storage.loot.LootPool pool : pools) {
                            java.lang.reflect.Field condsField = net.minecraft.world.level.storage.loot.LootPool.class.getDeclaredField("conditions");
                            condsField.setAccessible(true);
                            @SuppressWarnings("unchecked")
                            java.util.List<net.minecraft.world.level.storage.loot.predicates.LootItemCondition> conds = 
                                (java.util.List<net.minecraft.world.level.storage.loot.predicates.LootItemCondition>) condsField.get(pool);
                            
                            java.util.List<net.minecraft.world.level.storage.loot.predicates.LootItemCondition> newConds = new java.util.ArrayList<>();
                            if (conds != null) {
                                newConds.addAll(conds);
                            }
                            newConds.add(cond);
                            condsField.set(pool, newConds);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
