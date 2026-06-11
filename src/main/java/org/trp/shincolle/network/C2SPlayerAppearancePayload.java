package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;

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
}
