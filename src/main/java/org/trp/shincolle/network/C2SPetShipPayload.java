package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityShipBase;

import java.util.UUID;

public record C2SPetShipPayload(UUID shipUUID, int hitHeight, int hitAngle) implements CustomPacketPayload {
    public static final Type<C2SPetShipPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_pet_ship"));

    public static final StreamCodec<FriendlyByteBuf, C2SPetShipPayload> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, C2SPetShipPayload::shipUUID,
            ByteBufCodecs.VAR_INT, C2SPetShipPayload::hitHeight,
            ByteBufCodecs.VAR_INT, C2SPetShipPayload::hitAngle,
            C2SPetShipPayload::new
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
                player.level() instanceof
                    net.minecraft.server.level.ServerLevel serverLevel
            ) {
                net.minecraft.world.entity.Entity entity =
                    serverLevel.getEntity(this.shipUUID());
                if (entity instanceof EntityShipBase ship) {
                    ship.setHitHeight(this.hitHeight());
                    ship.setHitAngle(this.hitAngle());
                    ship.checkCaressed();
                    ship.interactPointer(player);
                }
            }
        });
    }

    private static class UUIDUtil {
        public static final StreamCodec<FriendlyByteBuf, UUID> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public UUID decode(FriendlyByteBuf buffer) {
                return buffer.readUUID();
            }

            @Override
            public void encode(FriendlyByteBuf buffer, UUID value) {
                buffer.writeUUID(value);
            }
        };
    }
}
