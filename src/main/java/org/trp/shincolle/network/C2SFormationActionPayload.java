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
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModDataAttachments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record C2SFormationActionPayload(int action, int param1, int param2, String paramString, Optional<UUID> paramUUID) implements CustomPacketPayload {
    public static final Type<C2SFormationActionPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_formation_action"));

    public static final StreamCodec<FriendlyByteBuf, C2SFormationActionPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SFormationActionPayload::action,
            ByteBufCodecs.VAR_INT, C2SFormationActionPayload::param1,
            ByteBufCodecs.VAR_INT, C2SFormationActionPayload::param2,
            ByteBufCodecs.STRING_UTF8, C2SFormationActionPayload::paramString,
            ByteBufCodecs.optional(UUIDUtil.STREAM_CODEC), C2SFormationActionPayload::paramUUID,
            C2SFormationActionPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null) return;
            AdmiralData data = player.getData(ModDataAttachments.ADMIRAL_DATA);

            switch (this.action()) {
                case 0: {
                    int nextTeam = this.param1();
                    data.setCurrentTeamID(nextTeam);
                    if (
                        player.level() instanceof
                            net.minecraft.server.level.ServerLevel serverLevel
                    ) {
                        List<EntityShipBase> ships =
                            serverLevel.getEntitiesOfClass(
                                EntityShipBase.class,
                                player.getBoundingBox().inflate(100.0),
                                ship ->
                                    player
                                        .getUUID()
                                        .equals(ship.getOwnerUUID()) &&
                                    !ship.isInDeadPose()
                            );
                        for (EntityShipBase ship : ships) {
                            if (ship.getFormationTeam() == nextTeam) {
                                int slot = ship.getFormationSlot();
                                ship.setPointerSelected(
                                    data.isSelected(nextTeam, slot)
                                );
                            } else {
                                ship.setPointerSelected(false);
                            }
                        }
                    }
                    break;
                }
                case 1:
                    data.setFormationID(
                        data.getCurrentTeamID(),
                        this.param1()
                    );
                    break;
                case 2: {
                    boolean nextState = this.param2() != 0;
                    data.setSelected(
                        data.getCurrentTeamID(),
                        this.param1(),
                        nextState
                    );
                    UUID shipUUID = data.getShipUUID(
                        data.getCurrentTeamID(),
                        this.param1()
                    );
                    if (
                        shipUUID != null &&
                        player.level() instanceof
                            net.minecraft.server.level.ServerLevel serverLevel
                    ) {
                        net.minecraft.world.entity.Entity e =
                            serverLevel.getEntity(shipUUID);
                        if (e instanceof EntityShipBase ship) {
                            ship.setPointerSelected(nextState);
                        }
                    }
                    break;
                }
                case 8:
                    UUID guiTarget = data.getShipUUID(
                        data.getCurrentTeamID(),
                        this.param1()
                    );
                    if (
                        guiTarget != null &&
                        player.level() instanceof
                            net.minecraft.server.level.ServerLevel serverLevel
                    ) {
                        net.minecraft.world.entity.Entity e =
                            serverLevel.getEntity(guiTarget);
                        if (
                            e instanceof
                                org.trp.shincolle.entity.base.EntityShipBase ship
                        ) {
                            ship.openShipMenu(player);
                        }
                    }
                    break;
                case 3: {
                    UUID shipUUID = data.getShipUUID(
                        data.getCurrentTeamID(),
                        this.param1()
                    );
                    if (
                        shipUUID != null &&
                        player.level() instanceof
                            net.minecraft.server.level.ServerLevel serverLevel
                    ) {
                        net.minecraft.world.entity.Entity entity =
                            serverLevel.getEntity(shipUUID);
                        if (entity instanceof EntityShipBase ship) {
                            ship.setFormationTeam(-1);
                            ship.setFormationSlot(-1);
                            ship.setPointerSelected(false);
                        }
                    }
                    data.setShipUUID(
                        data.getCurrentTeamID(),
                        this.param1(),
                        null
                    );
                    break;
                }
                case 4:
                    data.setTeamName(
                        data.getCurrentTeamID(),
                        this.paramString()
                    );
                    break;
                case 5:
                    this.paramUUID().ifPresent(uuid -> {
                        data.setShipUUID(
                            data.getCurrentTeamID(),
                            this.param1(),
                            uuid
                        );
                        if (
                            player.level() instanceof
                                net.minecraft.server.level.ServerLevel serverLevel
                        ) {
                            net.minecraft.world.entity.Entity entity =
                                serverLevel.getEntity(uuid);
                            if (entity instanceof EntityShipBase ship) {
                                ship.setFormationTeam(data.getCurrentTeamID());
                                ship.setFormationSlot(this.param1());
                            }
                        }
                    });
                    break;
                case 6:
                    int slot1 = this.param1();
                    int slot2 = this.param2();
                    data.swapShips(data.getCurrentTeamID(), slot1, slot2);
                    if (
                        player.level() instanceof
                            net.minecraft.server.level.ServerLevel serverLevel
                    ) {
                        UUID uuid1 = data.getShipUUID(
                            data.getCurrentTeamID(),
                            slot1
                        );
                        if (uuid1 != null) {
                            net.minecraft.world.entity.Entity e1 =
                                serverLevel.getEntity(uuid1);
                            if (e1 instanceof EntityShipBase ship1) {
                                ship1.setFormationSlot(slot1);
                            }
                        }
                        UUID uuid2 = data.getShipUUID(
                            data.getCurrentTeamID(),
                            slot2
                        );
                        if (uuid2 != null) {
                            net.minecraft.world.entity.Entity e2 =
                                serverLevel.getEntity(uuid2);
                            if (e2 instanceof EntityShipBase ship2) {
                                ship2.setFormationSlot(slot2);
                            }
                        }
                    }
                    break;
                case 7:
                    if (
                        player.level() instanceof
                            net.minecraft.server.level.ServerLevel serverLevel
                    ) {
                        int tid = data.getCurrentTeamID();
                        List<EntityShipBase> nearbySelected =
                            serverLevel.getEntitiesOfClass(
                                EntityShipBase.class,
                                player.getBoundingBox().inflate(64),
                                ship ->
                                    ship.isPointerSelected() &&
                                    player.getUUID().equals(ship.getOwnerUUID())
                            );

                        for (EntityShipBase ship : nearbySelected) {
                            if (!data.isShipInTeam(tid, ship.getUUID())) {
                                int emptySlot = data.findFirstEmptySlot(tid);
                                if (emptySlot != -1) {
                                    data.setShipUUID(
                                        tid,
                                        emptySlot,
                                        ship.getUUID()
                                    );
                                }
                            }
                        }

                        for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
                            UUID uuid = data.getShipUUID(tid, i);
                            if (uuid != null) {
                                net.minecraft.world.entity.Entity e =
                                    serverLevel.getEntity(uuid);
                                if (e instanceof EntityShipBase ship) {
                                    ship.setFormationTeam(tid);
                                    ship.setFormationSlot(i);
                                    ship.setPointerSelected(
                                        data.isSelected(tid, i)
                                    );
                                }
                            }
                        }
                    }
                    break;
            }

            if (
                player instanceof
                    net.minecraft.server.level.ServerPlayer serverPlayer
            ) {
                PacketDistributor.sendToPlayer(
                    serverPlayer,
                    new S2CAdmiralDataSyncPayload(data.serializeNBT())
                );
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
