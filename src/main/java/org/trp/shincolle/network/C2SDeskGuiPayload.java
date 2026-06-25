package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;

public record C2SDeskGuiPayload(int guiFunc, int radarZoom) implements CustomPacketPayload {
    public static final Type<C2SDeskGuiPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_desk_gui"));

    public static final StreamCodec<FriendlyByteBuf, C2SDeskGuiPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SDeskGuiPayload::guiFunc,
            ByteBufCodecs.VAR_INT, C2SDeskGuiPayload::radarZoom,
            C2SDeskGuiPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null) return;
            if (
                player.containerMenu instanceof
                    org.trp.shincolle.menu.DeskMenu deskMenu
            ) {
                if (
                    deskMenu.getDeskType() == 0 &&
                    deskMenu.getBlockEntity() != null
                ) {
                    deskMenu.getBlockEntity().setGuiFunc(this.guiFunc());
                    deskMenu
                        .getBlockEntity()
                        .setRadarZoomLv(this.radarZoom());
                }
            }
        });
    }
}
