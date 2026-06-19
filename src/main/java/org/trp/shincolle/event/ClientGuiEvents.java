package org.trp.shincolle.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.client.model.ModelPlayerAccessory;
import org.trp.shincolle.client.renderer.layer.PlayerAccessoryLayer;
import org.trp.shincolle.client.screen.AppearanceButton;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.network.C2SPlayerAppearancePayload;
import org.trp.shincolle.network.ModNetwork;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;
import org.trp.shincolle.init.ModSounds;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.util.RandomSource;

@EventBusSubscriber(modid = Shincolle.MODID, value = Dist.CLIENT)
public class ClientGuiEvents {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/player_accessory.png");
    private static ModelPlayerAccessory<Player> accessoryModel;
    
    private static float customBodyYaw = 0.0f;
    private static boolean hasInitBodyYaw = false;

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof InventoryScreen inventoryScreen) {
            int left = inventoryScreen.getGuiLeft();
            int top = inventoryScreen.getGuiTop();

            event.addListener(new AppearanceButton(left - 18 + 5, top + 16, 1, btn -> toggleAppearance()));
        } else if (event.getScreen() instanceof CreativeModeInventoryScreen creativeScreen) {
            int left = creativeScreen.getGuiLeft();
            int top = creativeScreen.getGuiTop();

            event.addListener(new AppearanceButton(left - 18 + 5, top + 16, 1, btn -> toggleAppearance()));
        }
    }

    private static void toggleAppearance() {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            AdmiralData data = player.getData(ModDataAttachments.ADMIRAL_DATA.get());
            int next = data.getAppearance() == 1 ? 0 : 1;
            ModNetwork.sendToServer(new C2SPlayerAppearancePayload(next));
        }
    }

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.options.getCameraType().isFirstPerson()) {
            return;
        }

        Player player = mc.player;
        AdmiralData data = player.getData(ModDataAttachments.ADMIRAL_DATA.get());
        if (data.getAppearance() != 1) {
            return;
        }

        if (accessoryModel == null) {
            accessoryModel = new ModelPlayerAccessory<>(mc.getEntityModels().bakeLayer(ClientModEventBusEvents.ACCESSORY_LAYER));
        }

        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();

        float partialTick = event.getPartialTick().getGameTimeDeltaTicks();

        double px = 0.0D;
        double py = -player.getEyeHeight();
        double pz = 0.0D;

        poseStack.translate(px, py, pz);

        float headYaw = player.getViewYRot(partialTick);
        if (!hasInitBodyYaw) {
            customBodyYaw = headYaw;
            hasInitBodyYaw = true;
        }

        float diff = net.minecraft.util.Mth.wrapDegrees(headYaw - customBodyYaw);
        if (diff > 50.0f) {
            customBodyYaw = headYaw - 50.0f;
        } else if (diff < -50.0f) {
            customBodyYaw = headYaw + 50.0f;
        }

        customBodyYaw = net.minecraft.util.Mth.rotLerp(0.15f, customBodyYaw, headYaw);

        poseStack.mulPose(Axis.YP.rotationDegrees(-customBodyYaw));

        poseStack.scale(PlayerAccessoryLayer.SCALE, PlayerAccessoryLayer.SCALE, PlayerAccessoryLayer.SCALE);

        MultiBufferSource bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        
        int light = net.minecraft.client.renderer.LevelRenderer.getLightColor(player.level(), player.blockPosition());
        accessoryModel.renderToBuffer(poseStack, vertexConsumer, light, net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

        poseStack.popPose();
    }

    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event) {
        if (event.getSound() == null) {
            return;
        }
        ResourceLocation id = event.getSound().getLocation();
        if (id.getNamespace().equals(Shincolle.MODID)) {
            String path = id.getPath();
            if (path.startsWith("ship-item-") || path.startsWith("ship-knockback-") ||
                path.startsWith("ship-idle-") || path.startsWith("ship-hit-") ||
                path.startsWith("ship-hurt-") || path.startsWith("ship-death-") ||
                path.startsWith("ship-marry-") || path.startsWith("ship-feed-")) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.getSoundManager().getSoundEvent(id) == null) {
                    net.minecraft.sounds.SoundEvent fallbackSoundEvent = null;
                    if (path.startsWith("ship-item-")) fallbackSoundEvent = ModSounds.SHIP_ITEM.get();
                    else if (path.startsWith("ship-knockback-")) fallbackSoundEvent = ModSounds.SHIP_KNOCKBACK.get();
                    else if (path.startsWith("ship-idle-")) fallbackSoundEvent = ModSounds.SHIP_IDLE.get();
                    else if (path.startsWith("ship-hit-")) fallbackSoundEvent = ModSounds.SHIP_HIT.get();
                    else if (path.startsWith("ship-hurt-")) fallbackSoundEvent = ModSounds.SHIP_HURT.get();
                    else if (path.startsWith("ship-death-")) fallbackSoundEvent = ModSounds.SHIP_DEATH.get();
                    else if (path.startsWith("ship-marry-")) fallbackSoundEvent = ModSounds.SHIP_MARRY.get();
                    else if (path.startsWith("ship-feed-")) fallbackSoundEvent = ModSounds.SHIP_FEED.get();

                    if (fallbackSoundEvent != null) {
                        net.minecraft.client.resources.sounds.SoundInstance orig = event.getSound();
                        float volume = 1.0F;
                        try {
                            volume = orig.getVolume();
                        } catch (NullPointerException e) {
                            try {
                                java.lang.reflect.Field f = net.minecraft.client.resources.sounds.AbstractSoundInstance.class.getDeclaredField("volume");
                                f.setAccessible(true);
                                volume = f.getFloat(orig);
                            } catch (Exception ex) {
                                volume = 1.0F;
                            }
                        }
                        float pitch = 1.0F;
                        try {
                            pitch = orig.getPitch();
                        } catch (NullPointerException e) {
                            try {
                                java.lang.reflect.Field f = net.minecraft.client.resources.sounds.AbstractSoundInstance.class.getDeclaredField("pitch");
                                f.setAccessible(true);
                                pitch = f.getFloat(orig);
                            } catch (Exception ex) {
                                pitch = 1.0F;
                            }
                        }
                        SimpleSoundInstance fallbackSound = new SimpleSoundInstance(
                            fallbackSoundEvent,
                            orig.getSource(),
                            volume,
                            pitch,
                            RandomSource.create(),
                            orig.getX(),
                            orig.getY(),
                            orig.getZ()
                        );
                        event.setSound(fallbackSound);
                    }
                }
            }
        }
    }
}
