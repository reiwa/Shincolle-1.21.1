package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.init.ModDataAttachments;

import java.util.Optional;

public record C2SOPToolActionPayload(int action, Optional<String> targetClassName) implements CustomPacketPayload {
    public static final Type<C2SOPToolActionPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_optool_action"));

    public static final StreamCodec<FriendlyByteBuf, C2SOPToolActionPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SOPToolActionPayload::action,
            ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8), C2SOPToolActionPayload::targetClassName,
            C2SOPToolActionPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null) return;

            if (this.action() == 0) {
                if (!player.hasPermissions(2)) {
                    player.displayClientMessage(
                        Component.translatable("chat.shincolle.command.notop"),
                        false
                    );
                    return;
                }
                this.targetClassName().ifPresent(className -> {
                    java.util.HashSet<String> unatk = new java.util.HashSet<>(
                        player
                            .level()
                            .getData(ModDataAttachments.UNATTACKABLE_TARGETS)
                    );
                    boolean added;
                    if (unatk.contains(className)) {
                        unatk.remove(className);
                        added = false;
                    } else {
                        unatk.add(className);
                        added = true;
                    }
                    player
                        .level()
                        .setData(
                            ModDataAttachments.UNATTACKABLE_TARGETS,
                            unatk
                        );

                    if (added) {
                        player.displayClientMessage(
                            Component.translatable(
                                "chat.shincolle.optool.add"
                            ).append(" " + className),
                            false
                        );
                    } else {
                        player.displayClientMessage(
                            Component.translatable(
                                "chat.shincolle.optool.remove"
                            ).append(" " + className),
                            false
                        );
                    }
                });
            } else if (this.action() == 1) {
                if (!player.hasPermissions(2)) {
                    player.displayClientMessage(
                        Component.translatable("chat.shincolle.command.notop"),
                        false
                    );
                    return;
                }
                player
                    .level()
                    .setData(
                        ModDataAttachments.UNATTACKABLE_TARGETS,
                        new java.util.HashSet<>()
                    );
                player.displayClientMessage(
                    Component.translatable("chat.shincolle.optool.clear"),
                    false
                );
            }
        });
    }
}
