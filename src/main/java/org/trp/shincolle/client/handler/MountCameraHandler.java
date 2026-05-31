package org.trp.shincolle.client.handler;

import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.lwjgl.glfw.GLFW;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityMountBase;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.network.C2SMountInputPayload;
import org.trp.shincolle.network.ModNetwork;

@EventBusSubscriber(modid = Shincolle.MODID, value = Dist.CLIENT)
public class MountCameraHandler {

    private static boolean isCameraHijacked = false;
    private static int keyPlayerSkillCD = 0;
    private static int keyMountActionCD = 0;
    private static final boolean[] prevHotbarState = new boolean[4];
    
    private static final boolean[] hotbarKeyPressed = new boolean[4];

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (event.getAction() != GLFW.GLFW_PRESS) return;
        for (int i = 0; i < 4; i++) {
            if (
                mc.options.keyHotbarSlots[i].matches(
                    event.getKey(),
                    event.getScanCode()
                )
            ) {
                hotbarKeyPressed[i] = true;
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (keyPlayerSkillCD > 0) keyPlayerSkillCD--;
        if (keyMountActionCD > 0) keyMountActionCD--;

        if (mc.player.getVehicle() instanceof EntityMountBase mount) {
            EntityShipBase ship = mount.getHost();
            if (ship == null) return;

            
            if (
                keyMountActionCD <= 0 && mc.options.keyInventory.consumeClick()
            ) {
                keyMountActionCD = 8;
                ModNetwork.sendToServer(
                    new C2SMountInputPayload(
                        1,
                        0,
                        Optional.empty(),
                        Optional.empty()
                    )
                );
                return;
            }

            
            if (keyPlayerSkillCD <= 0) {
                int skillKey = -1;
                for (int i = 0; i < 4; i++) {
                    boolean current = mc.options.keyHotbarSlots[i].isDown();
                    
                    if (hotbarKeyPressed[i]) {
                        current = true;
                        hotbarKeyPressed[i] = false;
                    }
                    if (current && !prevHotbarState[i]) {
                        skillKey = i;
                    }
                    prevHotbarState[i] = current;
                }
                if (skillKey >= 0) {
                    keyPlayerSkillCD = 4;
                    
                    
                    double range = Math.max(
                        2.0D,
                        ship.getLegacyShipStats().getAttackRange()
                    );

                    
                    java.util.List<Entity> excludeList =
                        java.util.Arrays.asList(mc.player, mount, ship);

                    Entity viewer = mc.getCameraEntity();
                    if (viewer == null) {
                        viewer = mc.player;
                    }

                    Vec3 eyePos = viewer.getEyePosition(1.0f);
                    Vec3 lookVec = viewer.getViewVector(1.0f);
                    Vec3 endPos = eyePos.add(lookVec.scale(range));
                    net.minecraft.world.phys.AABB area = viewer
                        .getBoundingBox()
                        .expandTowards(lookVec.scale(range))
                        .inflate(1.0);

                    net.minecraft.world.phys.EntityHitResult entityHit =
                        net.minecraft.world.entity.projectile.ProjectileUtil.getEntityHitResult(
                            viewer.level(),
                            viewer,
                            eyePos,
                            endPos,
                            area,
                            e ->
                                !e.isSpectator() &&
                                e.isPickable() &&
                                !excludeList.contains(e)
                        );

                    net.minecraft.world.phys.BlockHitResult blockHit = viewer
                        .level()
                        .clip(
                            new net.minecraft.world.level.ClipContext(
                                eyePos,
                                endPos,
                                net.minecraft.world.level.ClipContext.Block.COLLIDER,
                                net.minecraft.world.level.ClipContext.Fluid.ANY,
                                viewer
                            )
                        );

                    double entityDist =
                        entityHit != null
                            ? eyePos.distanceToSqr(entityHit.getLocation())
                            : Double.MAX_VALUE;
                    double blockDist =
                        blockHit != null &&
                        blockHit.getType() !=
                            net.minecraft.world.phys.HitResult.Type.MISS
                            ? eyePos.distanceToSqr(blockHit.getLocation())
                            : Double.MAX_VALUE;

                    boolean requireEntityTarget = skillKey >= 2;

                    if (entityHit != null && (requireEntityTarget || entityDist <= blockDist)) {
                        ModNetwork.sendToServer(
                            new C2SMountInputPayload(
                                12,
                                skillKey,
                                Optional.of(entityHit.getEntity().getId()),
                                Optional.empty()
                            )
                        );
                    } else if (
                        !requireEntityTarget &&
                        blockHit != null &&
                        blockHit.getType() !=
                            net.minecraft.world.phys.HitResult.Type.MISS
                    ) {
                        
                        ModNetwork.sendToServer(
                            new C2SMountInputPayload(
                                12,
                                skillKey,
                                Optional.empty(),
                                Optional.of(blockHit.getBlockPos())
                            )
                        );
                    } else if (skillKey >= 2) {
                        
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRenderFrame(RenderFrameEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        if (player.getVehicle() instanceof EntityMountBase mount) {
            Entity host = mount.getHost();
            if (host != null) {
                if (mc.getCameraEntity() != host) {
                    mc.setCameraEntity(host);
                    isCameraHijacked = true;
                }

                if (host instanceof LivingEntity livingHost) {
                    livingHost.setXRot(player.getXRot());
                    livingHost.setYRot(player.getYRot());
                    livingHost.xRotO = player.xRotO;
                    livingHost.yRotO = player.yRotO;
                    livingHost.yHeadRot = player.getYHeadRot();
                    livingHost.yHeadRotO = player.yHeadRotO;
                    livingHost.yBodyRot = player.yBodyRot;
                    livingHost.yBodyRotO = player.yBodyRotO;
                }
            }
        } else if (isCameraHijacked) {
            if (mc.getCameraEntity() != player) {
                mc.setCameraEntity(player);
            }
            isCameraHijacked = false;
        }
    }

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        if (
            isCameraHijacked &&
            event.getEntity() == Minecraft.getInstance().player
        ) {
            if (
                Minecraft.getInstance().options.getCameraType().isFirstPerson()
            ) {
                return;
            }
            event.setCanceled(true);
        }
    }
}
