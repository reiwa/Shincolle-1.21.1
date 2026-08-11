package org.trp.shincolle.network;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.init.ModDataAttachments;

public record C2STargetClassTogglePayload(String targetClass) implements CustomPacketPayload {
    public static final Type<C2STargetClassTogglePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_target_class_toggle"));

    public static final StreamCodec<FriendlyByteBuf, C2STargetClassTogglePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, C2STargetClassTogglePayload::targetClass,
            C2STargetClassTogglePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (!(player instanceof ServerPlayer serverPlayer)) return;

            AdmiralData data = serverPlayer.getData(ModDataAttachments.ADMIRAL_DATA);
            if (data != null && this.targetClass() != null && !this.targetClass().isEmpty()) {
                boolean added = data.toggleCustomTargetClass(this.targetClass());
                if (added) {
                    serverPlayer.displayClientMessage(Component.literal("ADD: ").withStyle(ChatFormatting.AQUA)
                            .append(Component.literal(this.targetClass()).withStyle(ChatFormatting.YELLOW)), false);
                } else {
                    serverPlayer.displayClientMessage(Component.literal("REMOVE: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal(this.targetClass()).withStyle(ChatFormatting.YELLOW)), false);
                }
                PacketDistributor.sendToPlayer(serverPlayer, new S2CAdmiralDataSyncPayload(data.serializeNBT()));
            }
        });
    }
}
