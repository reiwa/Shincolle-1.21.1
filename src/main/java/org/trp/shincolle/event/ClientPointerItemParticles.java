package org.trp.shincolle.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
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
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.particle.ParticleTeam;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.item.PointerItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Shincolle.MODID, value = Dist.CLIENT)
public final class ClientPointerItemParticles {

    private static final int PARTICLE_INTERVAL_TICKS = 10;
    private static final double SEARCH_RADIUS = 100.0;
    private static int optoolCooldown = 0;

    private ClientPointerItemParticles() {}

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        Level level = minecraft.level;

        if (player == null || level == null) {
            return;
        }

        if (optoolCooldown > 0) {
            optoolCooldown--;
        }

        ItemStack mainHand = player.getMainHandItem();
        boolean holdingOPTool =
            !mainHand.isEmpty() && mainHand.is(ModItems.OP_TOOL.get());
        if (!holdingOPTool) {
            ItemStack offHand = player.getOffhandItem();
            holdingOPTool =
                !offHand.isEmpty() && offHand.is(ModItems.OP_TOOL.get());
        }

        if (holdingOPTool && minecraft.screen == null) {
            long window = minecraft.getWindow().getWindow();
            if (
                com.mojang.blaze3d.platform.InputConstants.isKeyDown(
                    window,
                    org.lwjgl.glfw.GLFW.GLFW_KEY_KP_1
                )
            ) {
                if (optoolCooldown <= 0) {
                    optoolCooldown = 5;
                    EntityHitResult hit = getLookTargetEntity(player, 32.0);
                    if (
                        hit != null &&
                        hit.getEntity() != null &&
                        !(hit.getEntity() instanceof EntityShipBase)
                    ) {
                        String targetClassName = hit
                            .getEntity()
                            .getClass()
                            .getSimpleName();
                        org.trp.shincolle.network.ModNetwork.sendToServer(
                            new org.trp.shincolle.network.C2SOPToolActionPayload(
                                0,
                                java.util.Optional.of(targetClassName)
                            )
                        );
                    }
                }
            } else if (
                com.mojang.blaze3d.platform.InputConstants.isKeyDown(
                    window,
                    org.lwjgl.glfw.GLFW.GLFW_KEY_KP_2
                )
            ) {
                if (optoolCooldown <= 0) {
                    optoolCooldown = 20;
                    org.trp.shincolle.network.ModNetwork.sendToServer(
                        new org.trp.shincolle.network.C2SOPToolActionPayload(
                            1,
                            java.util.Optional.empty()
                        )
                    );
                }
            }
        }

        ItemStack pointerStack = getPointerStack(player);
        if (pointerStack.isEmpty()) {
            ParticleTeam.clearAllFollowParticles();
            return;
        }

        if (minecraft.screen == null) {
            if (minecraft.options.keyPlayerList.consumeClick()) {
                player.swing(InteractionHand.MAIN_HAND);
                org.trp.shincolle.network.ModNetwork.sendToServer(
                    new org.trp.shincolle.network.C2SPointerActionPayload(
                        6,
                        java.util.Optional.empty(),
                        java.util.Optional.empty()
                    )
                );
            }
        }

        int pointerMode = getPointerMode(pointerStack);

        AABB searchArea = player.getBoundingBox().inflate(SEARCH_RADIUS);
        List<EntityShipBase> ships = level.getEntitiesOfClass(
            EntityShipBase.class,
            searchArea,
            ship -> ship.isOwnedBy(player) && !ship.isInDeadPose()
        );
        if (ships.isEmpty()) {
            ParticleTeam.clearFollowParticles(
                ParticleTeam.FollowKind.SHIP_MARKER,
                null
            );
            return;
        }

        boolean isIntervalTick =
            level.getGameTime() % PARTICLE_INTERVAL_TICKS == 0;
        Set<Integer> activeShipIds = new HashSet<>();
        Set<Integer> activeTargetEntityIds = new HashSet<>();

        for (EntityShipBase ship : ships) {
            activeShipIds.add(ship.getId());
            if (ship.isPointerSelected() && ship.hasPointerTargetEntity()) {
                Entity target = ship.getPointerTargetEntity();
                if (target != null) {
                    activeTargetEntityIds.add(target.getId());
                    ParticleTeam existingTargetPart = ParticleTeam.getFollowParticle(
                        ParticleTeam.FollowKind.TARGET_ENTITY,
                        target.getId()
                    );
                    if (existingTargetPart == null || !existingTargetPart.isAliveParticle()) {
                        spawnEntityTargetMarker(level, target);
                    }
                }
            }

            boolean groupMode = pointerMode == PointerItem.MODE_GROUP;
            boolean formationMode = pointerMode == PointerItem.MODE_FORMATION;
            ParticleTeam.RenderStyle selectedStyle = groupMode
                ? ParticleTeam.RenderStyle.SELECTED_RED
                : (formationMode
                      ? ParticleTeam.RenderStyle.SELECTED_YELLOW
                      : ParticleTeam.RenderStyle.DEFAULT_BLUE);
            ParticleTeam.RenderStyle desiredStyle = ship.isPointerSelected()
                ? selectedStyle
                : ParticleTeam.RenderStyle.DEFAULT_GREEN;
            ParticleTeam existing = ParticleTeam.getFollowParticle(
                ParticleTeam.FollowKind.SHIP_MARKER,
                ship.getId()
            );
            boolean styleMismatch =
                existing == null ||
                !existing.isAliveParticle() ||
                existing.getRenderStyle() != desiredStyle;

            if (isIntervalTick || styleMismatch) {
                spawnShipMarker(level, ship, pointerMode);
            }

            handleShipTargetParticles(level, ship);
        }

        if (isIntervalTick) {
            ParticleTeam.clearFollowParticles(
                ParticleTeam.FollowKind.SHIP_MARKER,
                activeShipIds
            );
            ParticleTeam.clearFollowParticles(
                ParticleTeam.FollowKind.TARGET_ENTITY,
                activeTargetEntityIds
            );
        }
    }

    @SubscribeEvent
    public static void onPointerItemUse(
        PlayerInteractEvent.RightClickItem event
    ) {
        handlePointerTargetMarker(event.getLevel(), event.getEntity());
    }

    @SubscribeEvent
    public static void onPointerItemUse(
        PlayerInteractEvent.RightClickBlock event
    ) {
        handlePointerTargetMarker(event.getLevel(), event.getEntity());
    }

    @SubscribeEvent
    public static void onPointerItemUse(
        PlayerInteractEvent.EntityInteract event
    ) {
        handlePointerEntityMarker(
            event.getLevel(),
            event.getEntity(),
            event.getTarget()
        );
    }

    private static boolean isHoldingPointerItem(Player player) {
        return !getPointerStack(player).isEmpty();
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

    private static int getPointerMode(ItemStack stack) {
        if (stack.getItem() instanceof PointerItem pointerItem) {
            return pointerItem.getMode(stack);
        }
        return PointerItem.MODE_SINGLE;
    }

    private static void spawnShipMarker(
        Level level,
        EntityShipBase ship,
        int pointerMode
    ) {
        double baseX = ship.getX();
        double baseY = ship.getY();
        double baseZ = ship.getZ();

        boolean groupMode = pointerMode == PointerItem.MODE_GROUP;
        boolean formationMode = pointerMode == PointerItem.MODE_FORMATION;
        ParticleTeam.RenderStyle selectedStyle = groupMode
            ? ParticleTeam.RenderStyle.SELECTED_RED
            : (formationMode
                  ? ParticleTeam.RenderStyle.SELECTED_YELLOW
                  : ParticleTeam.RenderStyle.DEFAULT_BLUE);
        ParticleTeam.RenderStyle desiredStyle = ship.isPointerSelected()
            ? selectedStyle
            : ParticleTeam.RenderStyle.DEFAULT_GREEN;
        ParticleTeam existing = ParticleTeam.getFollowParticle(
            ParticleTeam.FollowKind.SHIP_MARKER,
            ship.getId()
        );
        if (existing != null) {
            if (
                existing.isAliveParticle() &&
                existing.getRenderStyle() == desiredStyle
            ) {
                return;
            }
            ParticleTeam.removeFollowParticle(
                ParticleTeam.FollowKind.SHIP_MARKER,
                ship.getId()
            );
        }

        if (ship.isPointerSelected()) {
            net.minecraft.core.particles.SimpleParticleType type =
                ModParticles.PARTICLE_TEAM_SELECTED.get();
            if (groupMode) type = ModParticles.PARTICLE_TEAM_SELECTED_RED.get();
            else if (formationMode) type =
                ModParticles.PARTICLE_TEAM_SELECTED_YELLOW.get();

            level.addParticle(
                type,
                baseX,
                baseY,
                baseZ,
                ship.getBbHeight(),
                ship.getId(),
                ParticleTeam.FollowKind.SHIP_MARKER.getMarkerId()
            );
        } else {
            level.addParticle(
                ModParticles.PARTICLE_TEAM.get(),
                baseX,
                baseY,
                baseZ,
                ship.getBbHeight(),
                ship.getId(),
                ParticleTeam.FollowKind.SHIP_MARKER.getMarkerId()
            );
        }
    }

    private static void handleShipTargetParticles(
        Level level,
        EntityShipBase ship
    ) {
        Vec3 targetPos = null;
        boolean isEntity = false;
        boolean isGuardPos = false;

        if (ship.hasPointerTargetEntity()) {
            Entity target = ship.getPointerTargetEntity();
            if (target != null && target.isAlive()) {
                targetPos = target
                    .position()
                    .add(0, target.getBbHeight() * 0.5, 0);
                isEntity = true;
            }
        } else if (ship.hasPointerTarget()) {
            targetPos = ship.getPointerTarget();
        } else if (ship.getTarget() != null && ship.getTarget().isAlive()) {
            targetPos = ship
                .getTarget()
                .position()
                .add(0, ship.getTarget().getBbHeight() * 0.5, 0);
            isEntity = true;
        } else if (ship.getGuardedPos(4) == 1) {
            targetPos = new Vec3(
                ship.getGuardedPos(0) + 0.5,
                ship.getGuardedPos(1) + 0.5,
                ship.getGuardedPos(2) + 0.5
            );
            isGuardPos = true;
        }

        if (targetPos != null) {
            boolean isIntervalTick = level.getGameTime() % 16 == 0;

            if (isIntervalTick) {
                if (!isEntity) {
                    spawnTargetMarker(
                        level,
                        targetPos,
                        ModParticles.PARTICLE_TEAM_TARGET.get(),
                        1.5D
                    );
                }

                if (isGuardPos) {
                    Vec3 start = ship
                        .position()
                        .add(0, ship.getBbHeight() * 0.5, 0);
                    Vec3 dir = targetPos.subtract(start);
                    double dist = dir.length();
                    if (dist > 0.5) {
                        level.addParticle(
                            ModParticles.PARTICLE_LINE.get(),
                            start.x,
                            start.y,
                            start.z,
                            dir.x,
                            dir.y,
                            dir.z
                        );
                    }
                }
            }
        }
    }

    private static void spawnTargetMarker(
        Level level,
        Vec3 target,
        SimpleParticleType type,
        double height
    ) {
        level.addParticle(
            type,
            target.x,
            target.y,
            target.z,
            height,
            -1.0D,
            ParticleTeam.FollowKind.NONE.getMarkerId()
        );
    }

    private static void spawnEntityTargetMarker(Level level, Vec3 target) {
        level.addParticle(
            ModParticles.PARTICLE_TEAM_TARGET_ENTITY.get(),
            target.x,
            target.y,
            target.z,
            1.5D,
            -1.0D,
            ParticleTeam.FollowKind.NONE.getMarkerId()
        );
    }

    private static void spawnEntityTargetMarker(Level level, Entity target) {
        ParticleTeam existing = ParticleTeam.getFollowParticle(
            ParticleTeam.FollowKind.TARGET_ENTITY,
            target.getId()
        );
        if (existing != null) {
            ParticleTeam.removeFollowParticle(
                ParticleTeam.FollowKind.TARGET_ENTITY,
                target.getId()
            );
        }
        Vec3 pos = target.position();
        level.addParticle(
            ModParticles.PARTICLE_TEAM_TARGET_ENTITY.get(),
            pos.x,
            pos.y,
            pos.z,
            target.getBbHeight(),
            target.getId(),
            ParticleTeam.FollowKind.TARGET_ENTITY.getMarkerId()
        );
    }

    private static void handlePointerTargetMarker(Level level, Player player) {
        if (level == null || !level.isClientSide) {
            return;
        }

        if (player == null || !isHoldingPointerItem(player)) {
            return;
        }

        AABB searchArea = player.getBoundingBox().inflate(SEARCH_RADIUS);
        List<EntityShipBase> ships = level.getEntitiesOfClass(
            EntityShipBase.class,
            searchArea,
            ship ->
                ship.isOwnedBy(player) &&
                ship.isPointerSelected() &&
                !ship.isInDeadPose()
        );
        if (ships.isEmpty()) {
            return;
        }

        EntityHitResult entityHit = getLookTargetEntity(player);
        if (entityHit != null) {
            Entity entity = entityHit.getEntity();
            if (
                entity != player &&
                !(entity instanceof EntityShipBase ship &&
                    ship.isOwnedBy(player))
            ) {
                spawnEntityTargetMarker(level, entity);
                return;
            }
        }

        Vec3 target = getLookTarget(player, level);
        if (target == null) {
            return;
        }
        spawnTargetMarker(
            level,
            target,
            ModParticles.PARTICLE_TEAM.get(),
            1.5D
        );
    }

    private static void handlePointerEntityMarker(
        Level level,
        Player player,
        Entity target
    ) {
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
        List<EntityShipBase> ships = level.getEntitiesOfClass(
            EntityShipBase.class,
            searchArea,
            ship ->
                ship.isOwnedBy(player) &&
                ship.isPointerSelected() &&
                !ship.isInDeadPose()
        );
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
        AABB searchBox = player
            .getBoundingBox()
            .expandTowards(look.scale(range))
            .inflate(1.0D);
        return ProjectileUtil.getEntityHitResult(
            player.level(),
            player,
            eyePos,
            end,
            searchBox,
            entity ->
                !entity.isSpectator() && entity.isPickable() && entity != player
        );
    }

    private static Vec3 getLookTarget(Player player, Level level) {
        double range = SEARCH_RADIUS;

        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);

        Vec3 end = eyePos.add(look.x * range, look.y * range, look.z * range);

        BlockHitResult hit = level.clip(
            new ClipContext(
                eyePos,
                end,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.ANY,
                player
            )
        );

        if (hit.getType() != HitResult.Type.BLOCK) {
            return null;
        }

        BlockPos pos = hit.getBlockPos();
        if (
            level.getBlockEntity(pos) instanceof
                org.trp.shincolle.block.entity.IWaypoint
        ) {
            return Vec3.atBottomCenterOf(pos).add(0.0, 1.0, 0.0);
        }
        return Vec3.atBottomCenterOf(pos).add(0.0, 1.0, 0.0);
    }

    private static EntityHitResult getLookTargetEntity(
        Player player,
        double range
    ) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * range, look.y * range, look.z * range);
        AABB searchBox = player
            .getBoundingBox()
            .expandTowards(look.scale(range))
            .inflate(1.0D);
        return ProjectileUtil.getEntityHitResult(
            player.level(),
            player,
            eyePos,
            end,
            searchBox,
            entity ->
                !entity.isSpectator() && entity.isPickable() && entity != player
        );
    }

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        if (event.getHand() != InteractionHand.MAIN_HAND) return;
        ItemStack stack = event.getItemStack();
        if (
            !(stack.getItem() instanceof PointerItem ptr) ||
            !ptr.isPetting(stack)
        ) return;

        event.setCanceled(true);
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.player instanceof AbstractClientPlayer clientPlayer)) return;

        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource buffer = event.getMultiBufferSource();
        int light = event.getPackedLight();
        HumanoidArm arm = clientPlayer.getMainArm();
        boolean isRight = arm != HumanoidArm.LEFT;
        float f = isRight ? 1.0F : -1.0F;

        float swingProgress = 0.0F;
        float equipProgress = 0.0F;
        float sqrtSwing = 0.0F;

        float tx = -0.3F * Mth.sin(sqrtSwing * (float) Math.PI);
        float ty = 0.4F * Mth.sin(sqrtSwing * (float) (Math.PI * 2));
        float tz = -0.4F * Mth.sin(swingProgress * (float) Math.PI);

        poseStack.translate(
            f * (tx + 0.64000005F),
            ty + -0.6F + equipProgress * -0.6F,
            tz + -0.71999997F
        );
        poseStack.mulPose(Axis.YP.rotationDegrees(f * 45.0F));

        float f1 = Mth.sin(swingProgress * swingProgress * (float) Math.PI);
        float f2 = Mth.sin(sqrtSwing * (float) Math.PI);
        poseStack.mulPose(Axis.YP.rotationDegrees(f * f2 * 70.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(f * f1 * -20.0F));
        poseStack.translate(f * -1.0F, 3.6F, 3.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(f * 120.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(200.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(f * -135.0F));
        poseStack.translate(f * 5.6F, 0.0F, 0.0F);

        if (mc.options.keyUse.isDown()) {
            float ptick = event.getPartialTick();
            float time = clientPlayer.tickCount + ptick;
            switch (ptr.getMode(stack)) {
                case PointerItem.MODE_SINGLE -> {
                    poseStack.translate(1.3F, 4.0F, 0.0F);
                    poseStack.scale(3.0F, 3.0F, 3.0F);
                    poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.cos(time * 0.125F) * -20.0F - 60.0F));
                }
                case PointerItem.MODE_GROUP -> {
                    poseStack.mulPose(Axis.YP.rotationDegrees(70.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(-20.0F));
                    poseStack.translate(-2.0F, 16.0F, 10.0F);
                    poseStack.scale(12.0F, 12.0F, 12.0F);
                    poseStack.mulPose(Axis.XP.rotationDegrees(Mth.cos(time * 0.1F) * -15.0F + 20.0F));
                }
                case PointerItem.MODE_FORMATION -> {
                    poseStack.translate(13.5F, 12.5F, 2.5F);
                    poseStack.scale(9.0F, 9.0F, 9.0F);
                    float angle = Mth.cos(time * 0.2F) * -15.0F - 20.0F;
                    poseStack.mulPose(new org.joml.Quaternionf().rotationAxis(angle * ((float) Math.PI / 180F), 1.0F, 1.0F, 0.0F));
                }
            }
        }

        PlayerRenderer playerRenderer = (PlayerRenderer) mc
            .getEntityRenderDispatcher()
            .<AbstractClientPlayer>getRenderer(clientPlayer);
        if (isRight) {
            playerRenderer.renderRightHand(
                poseStack,
                buffer,
                light,
                clientPlayer
            );
        } else {
            playerRenderer.renderLeftHand(
                poseStack,
                buffer,
                light,
                clientPlayer
            );
        }
    }

    @SubscribeEvent
    public static void onSetLiquidFog(net.neoforged.neoforge.client.event.ViewportEvent.RenderFog event) {
        if (org.trp.shincolle.Config.ringAbility[3] < 0) return;
        Entity entity = event.getCamera().getEntity();
        if (!(entity instanceof Player player)) return;
        if (!(player.isInWaterOrBubble() || player.isInLava())) return;

        org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
        if (data.hasRing() && data.isRingActive()) {
            float far = event.getFarPlaneDistance();
            if (org.trp.shincolle.Config.ringAbility[3] == 0) {
                event.setFarPlaneDistance(far * 10.0f);
            } else {
                float factor = (float) data.getMarriageNum() / (float) org.trp.shincolle.Config.ringAbility[3];
                event.setFarPlaneDistance(far * (1.0f + factor * 9.0f));
            }
            event.setCanceled(true);
        }
    }
}
