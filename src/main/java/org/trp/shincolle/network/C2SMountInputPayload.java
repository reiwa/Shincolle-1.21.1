package org.trp.shincolle.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;

import java.util.Optional;

public record C2SMountInputPayload(int action, int skillKey, Optional<Integer> targetEntityId, Optional<BlockPos> targetPos) implements CustomPacketPayload {
    public static final Type<C2SMountInputPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_mount_input"));

    public static final StreamCodec<FriendlyByteBuf, C2SMountInputPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SMountInputPayload::action,
            ByteBufCodecs.VAR_INT, C2SMountInputPayload::skillKey,
            ByteBufCodecs.optional(ByteBufCodecs.VAR_INT), C2SMountInputPayload::targetEntityId,
            ByteBufCodecs.optional(BlockPos.STREAM_CODEC), C2SMountInputPayload::targetPos,
            C2SMountInputPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
