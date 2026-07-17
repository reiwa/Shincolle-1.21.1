package org.trp.shincolle.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.entity.base.EntityShipBase;

import java.util.List;

public class ClientProxy {
    public static boolean isLocalPlayerOwner(EntityShipBase ship) {
        Player localPlayer = Minecraft.getInstance().player;
        return localPlayer != null && ship.isOwnedBy(localPlayer);
    }

    public static void appendPointerTooltip(ItemStack stack, List<Component> tooltipComponents, int mode) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        org.trp.shincolle.attachment.AdmiralData data = mc.player.getData(
            org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA
        );
        int teamId = data.getCurrentTeamID();
        int fid = data.getFormationID(teamId);

        Component modeComp = Component.translatable(
            org.trp.shincolle.item.PointerItem.getModeTranslationKey(mode)
        ).withStyle(getModeStyle(mode));
        if (mode == org.trp.shincolle.item.PointerItem.MODE_FORMATION) {
            Component formationComp = Component.translatable(
                "gui.shincolle.formation.format" + fid
            ).withStyle(ChatFormatting.GOLD);
            tooltipComponents.add(
                modeComp.copy().append(" : ").append(formationComp)
            );
        } else {
            tooltipComponents.add(modeComp);
        }

        tooltipComponents.add(
            Component.translatable("gui.shincolle.pointer3").withStyle(
                ChatFormatting.GRAY
            )
        );

        tooltipComponents.add(
            Component.translatable("gui.shincolle.pointer4")
                .append(" " + (teamId + 1))
                .withStyle(ChatFormatting.YELLOW, ChatFormatting.UNDERLINE)
        );

        int displayedCount = 1;
        if (mode == org.trp.shincolle.item.PointerItem.MODE_FORMATION) {
            for (
                int i = 0;
                i < org.trp.shincolle.attachment.AdmiralData.SLOT_COUNT;
                i++
            ) {
                java.util.UUID uuid = data.getShipUUID(teamId, i);
                if (uuid != null) {
                    String name = null;
                    int level = 0;
                    if (mc.level != null) {
                        for (net.minecraft.world.entity.Entity e : mc.level.entitiesForRendering()) {
                            if (
                                e.getUUID().equals(uuid) &&
                                e instanceof
                                    org.trp.shincolle.entity.base.EntityShipBase ship
                            ) {
                                name = ship.hasCustomName()
                                    ? ship.getCustomName().getString()
                                    : ship.getDisplayName().getString();
                                level = ship.getLevel();
                                break;
                            }
                        }
                    }

                    if (name != null) {
                        ChatFormatting color = data.isSelected(teamId, i)
                            ? ChatFormatting.WHITE
                            : ChatFormatting.GRAY;
                        tooltipComponents.add(
                            Component.literal(
                                displayedCount + ": " + name + " - Lv " + level
                            ).withStyle(color)
                        );
                    } else {
                        tooltipComponents.add(
                            Component.translatable(
                                "gui.shincolle.formation.nosignal"
                            ).withStyle(
                                ChatFormatting.DARK_RED,
                                ChatFormatting.OBFUSCATED
                            )
                        );
                    }
                    displayedCount++;
                }
            }
        } else {
            if (mc.level != null) {
                for (net.minecraft.world.entity.Entity e : mc.level.entitiesForRendering()) {
                    if (e instanceof org.trp.shincolle.entity.base.EntityShipBase ship && ship.isOwnedBy(mc.player) && ship.isPointerSelected()) {
                        String name = ship.hasCustomName()
                            ? ship.getCustomName().getString()
                            : ship.getDisplayName().getString();
                        tooltipComponents.add(
                            Component.literal(
                                displayedCount + ": " + name + " - Lv " + ship.getLevel()
                            ).withStyle(ChatFormatting.WHITE)
                        );
                        displayedCount++;
                    }
                }
            }
            if (displayedCount == 1) {
                tooltipComponents.add(
                    Component.literal("  None").withStyle(ChatFormatting.GRAY)
                );
            }
        }
    }

    public static void spawn91TypeParticle(net.minecraft.world.level.Level level, double x, double y, double z, double dx, double dy, double dz) {
        if (level instanceof net.minecraft.client.multiplayer.ClientLevel clientLevel) {
            clientLevel.addParticle(org.trp.shincolle.init.ModParticles.PARTICLE_91TYPE.get(), true, x, y, z, dx, dy, dz);
        }
    }

    public static void spawnSphereLight(net.minecraft.world.level.Level level, double x, double y, double z, double range, double entityId, double type) {
        if (level instanceof net.minecraft.client.multiplayer.ClientLevel clientLevel) {
            clientLevel.addParticle(org.trp.shincolle.init.ModParticles.PARTICLE_SPHERE_LIGHT.get(), true,
                    x, y, z,
                    range, entityId, type);
        }
    }

    private static ChatFormatting getModeStyle(int mode) {
        return switch (mode) {
            case org.trp.shincolle.item.PointerItem.MODE_GROUP -> ChatFormatting.RED;
            case org.trp.shincolle.item.PointerItem.MODE_FORMATION -> ChatFormatting.GOLD;
            default -> ChatFormatting.AQUA;
        };
    }

    public static void applyFrameBlockClimbVelocity(net.minecraft.world.entity.Entity entity) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && (entity.equals(mc.player) || entity.equals(mc.player.getVehicle()))) {
            if (mc.options.keyUp.isDown() || mc.options.keyDown.isDown() || mc.options.keyLeft.isDown() || mc.options.keyRight.isDown()) {
                mc.player.setDeltaMovement(mc.player.getDeltaMovement().add(0.0, 0.4, 0.0));
                net.minecraft.world.entity.Entity vehicle = mc.player.getVehicle();
                if (vehicle != null) {
                    vehicle.setDeltaMovement(vehicle.getDeltaMovement().add(0.0, 0.4, 0.0));
                }
            }
        }
    }

    public static void appendMarriageRingTooltip(ItemStack stack, List<Component> tooltipComponents) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        org.trp.shincolle.attachment.AdmiralData data = mc.player.getData(
            org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA
        );
        if (data != null) {
            tooltipComponents.add(
                Component.translatable("gui.shincolle.ringText")
                    .append(" " + data.getMarriageNum())
                    .withStyle(ChatFormatting.AQUA)
            );
        }
    }
}
