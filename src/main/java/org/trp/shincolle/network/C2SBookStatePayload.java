package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.init.ModDataComponents;
import org.trp.shincolle.item.DeskItemBook;

public record C2SBookStatePayload(int chapter, int page) implements CustomPacketPayload {
    public static final Type<C2SBookStatePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_book_state"));

    public static final StreamCodec<FriendlyByteBuf, C2SBookStatePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SBookStatePayload::chapter,
            ByteBufCodecs.VAR_INT, C2SBookStatePayload::page,
            C2SBookStatePayload::new
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
                    deskMenu.getBlockEntity().setBookChap(this.chapter());
                    deskMenu.getBlockEntity().setBookPage(this.page());
                    return;
                }
            }

            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof DeskItemBook)) {
                stack = player.getOffhandItem();
            }

            if (stack.getItem() instanceof DeskItemBook) {
                stack.set(ModDataComponents.BOOK_CHAPTER, this.chapter());
                stack.set(ModDataComponents.BOOK_PAGE, this.page());
            }
        });
    }
}
