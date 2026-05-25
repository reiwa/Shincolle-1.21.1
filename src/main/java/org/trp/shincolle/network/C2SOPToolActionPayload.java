package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;

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
}
