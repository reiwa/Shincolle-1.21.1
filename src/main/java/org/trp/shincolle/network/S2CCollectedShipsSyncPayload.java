package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.init.ModDataAttachments;

import java.util.ArrayList;
import java.util.List;

public record S2CCollectedShipsSyncPayload(List<Integer> collectedShips) implements CustomPacketPayload {
    public static final Type<S2CCollectedShipsSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "s2c_collected_ships_sync"));

    public static final StreamCodec<FriendlyByteBuf, S2CCollectedShipsSyncPayload> STREAM_CODEC = StreamCodec.of(
            (buf, payload) -> {
                buf.writeVarInt(payload.collectedShips().size());
                for (int val : payload.collectedShips()) {
                    buf.writeVarInt(val);
                }
            },
            buf -> {
                int size = buf.readVarInt();
                List<Integer> list = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    list.add(buf.readVarInt());
                }
                return new S2CCollectedShipsSyncPayload(list);
            }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player != null) {
                player.setData(
                    ModDataAttachments.COLLECTED_SHIPS,
                    new java.util.HashSet<>(this.collectedShips())
                );
            }
        });
    }
}
