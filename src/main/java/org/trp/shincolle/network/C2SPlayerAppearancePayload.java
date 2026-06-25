package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.init.ModDataAttachments;

public record C2SPlayerAppearancePayload(int appearance) implements CustomPacketPayload {
    public static final Type<C2SPlayerAppearancePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_player_appearance"));

    public static final StreamCodec<FriendlyByteBuf, C2SPlayerAppearancePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SPlayerAppearancePayload::appearance,
            C2SPlayerAppearancePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player != null) {
                AdmiralData data = player.getData(
                    ModDataAttachments.ADMIRAL_DATA
                );
                data.setAppearance(this.appearance());
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(
                    player,
                    new S2CAdmiralDataSyncPayload(data.serializeNBT())
                );
            }
        });
    }
}
