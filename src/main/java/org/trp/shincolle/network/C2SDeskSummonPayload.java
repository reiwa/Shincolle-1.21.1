package org.trp.shincolle.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record C2SDeskSummonPayload(BlockPos deskPos, List<UUID> shipUuids, boolean isItem) implements CustomPacketPayload {
    public static final Type<C2SDeskSummonPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_desk_summon"));

    public static final StreamCodec<FriendlyByteBuf, C2SDeskSummonPayload> STREAM_CODEC = StreamCodec.of(
            (buf, payload) -> {
                buf.writeBlockPos(payload.deskPos());
                buf.writeInt(payload.shipUuids().size());
                for (UUID uuid : payload.shipUuids()) {
                    buf.writeUUID(uuid);
                }
                buf.writeBoolean(payload.isItem());
            },
            buf -> {
                BlockPos pos = buf.readBlockPos();
                int size = buf.readInt();
                List<UUID> uuids = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    uuids.add(buf.readUUID());
                }
                boolean isItem = buf.readBoolean();
                return new C2SDeskSummonPayload(pos, uuids, isItem);
            }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null) return;
            if (this.isItem()) {
                org.trp.shincolle.utility.FormationHelper.applySummonShipsToPlayer(
                    player,
                    this.shipUuids()
                );
            } else {
                org.trp.shincolle.utility.FormationHelper.applySummonShipsToDesk(
                    player,
                    this.deskPos(),
                    this.shipUuids()
                );
            }
        });
    }
}
