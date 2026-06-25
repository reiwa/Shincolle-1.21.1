package org.trp.shincolle.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.item.PointerItem;

import java.util.List;

public record S2CAdmiralDataSyncPayload(CompoundTag nbt) implements CustomPacketPayload {
    public static final Type<S2CAdmiralDataSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "s2c_admiral_data_sync"));

    public static final StreamCodec<FriendlyByteBuf, S2CAdmiralDataSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.COMPOUND_TAG, S2CAdmiralDataSyncPayload::nbt,
            S2CAdmiralDataSyncPayload::new
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
                data.deserializeNBT(this.nbt());

                if (player.level().isClientSide) {
                    int mode = PointerItem.MODE_SINGLE;
                    ItemStack pointerStack = ItemStack.EMPTY;
                    ItemStack main = player.getMainHandItem();
                    if (
                        main.is(
                            org.trp.shincolle.init.ModItems.POINTER_ITEM.get()
                        )
                    ) {
                        pointerStack = main;
                    } else {
                        ItemStack off = player.getOffhandItem();
                        if (
                            off.is(
                                org.trp.shincolle.init.ModItems.POINTER_ITEM.get()
                            )
                        ) {
                            pointerStack = off;
                        }
                    }
                    if (
                        !pointerStack.isEmpty() &&
                        pointerStack.getItem() instanceof PointerItem pi
                    ) {
                        mode = pi.getMode(pointerStack);
                    }

                    if (mode == PointerItem.MODE_FORMATION) {
                        int teamId = data.getCurrentTeamID();
                        List<EntityShipBase> ships = player
                            .level()
                            .getEntitiesOfClass(
                                EntityShipBase.class,
                                player.getBoundingBox().inflate(100.0),
                                ship ->
                                    ship.isOwnedBy(player) &&
                                    !ship.isInDeadPose()
                            );
                        for (EntityShipBase ship : ships) {
                            if (ship.getFormationTeam() == teamId) {
                                int slot = ship.getFormationSlot();
                                ship.setPointerSelected(
                                    data.isSelected(teamId, slot)
                                );
                            } else {
                                ship.setPointerSelected(false);
                            }
                        }
                    }
                }
            }
        });
    }
}
