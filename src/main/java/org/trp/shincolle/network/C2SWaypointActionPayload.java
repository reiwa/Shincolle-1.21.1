package org.trp.shincolle.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.block.entity.IWaypoint;

public record C2SWaypointActionPayload(
        int action,
        int x1, int y1, int z1,
        int x2, int y2, int z2
) implements CustomPacketPayload {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_waypoint_action");
    public static final CustomPacketPayload.Type<C2SWaypointActionPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<FriendlyByteBuf, C2SWaypointActionPayload> STREAM_CODEC = StreamCodec.of(
            (buf, payload) -> {
                buf.writeInt(payload.action());
                buf.writeInt(payload.x1());
                buf.writeInt(payload.y1());
                buf.writeInt(payload.z1());
                buf.writeInt(payload.x2());
                buf.writeInt(payload.y2());
                buf.writeInt(payload.z2());
            },
            buf -> new C2SWaypointActionPayload(
                    buf.readInt(),
                    buf.readInt(), buf.readInt(), buf.readInt(),
                    buf.readInt(), buf.readInt(), buf.readInt()
            )
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null || player.level() == null) return;

            BlockPos pos1 = new BlockPos(
                this.x1(),
                this.y1(),
                this.z1()
            );
            BlockPos pos2 = new BlockPos(
                this.x2(),
                this.y2(),
                this.z2()
            );

            double dist = pos1.distSqr(pos2);

            if (this.action() == 0) {
                if (dist > 48.0 * 48.0) {
                    player.displayClientMessage(
                        Component.translatable(
                            "chat.shincolle.wrench.wptoofar"
                        ),
                        false
                    );
                    return;
                }
                if (
                    player.level().getBlockEntity(pos1) instanceof
                        IWaypoint wpFrom &&
                    player.level().getBlockEntity(pos2) instanceof
                        IWaypoint wpTo
                ) {
                    if (
                        wpFrom.getOwnerUUID() != null &&
                        !wpFrom.getOwnerUUID().equals(player.getUUID())
                    ) {
                        player.displayClientMessage(
                            Component.translatable("chat.shincolle.wrongowner"),
                            false
                        );
                        return;
                    }
                    wpFrom.setNextPos(pos2);
                    if (!wpTo.getNextPos().equals(pos1)) {
                        wpTo.setLastPos(pos1);
                    }
                    player.displayClientMessage(
                        Component.translatable(
                            "chat.shincolle.wrench.setwp"
                        ).append(
                            " " +
                                pos1.getX() +
                                " " +
                                pos1.getY() +
                                " " +
                                pos1.getZ() +
                                " --> " +
                                pos2.getX() +
                                " " +
                                pos2.getY() +
                                " " +
                                pos2.getZ()
                        ),
                        false
                    );
                }
            } else if (this.action() == 1) {
                if (dist > 16.0 * 16.0) {
                    player.displayClientMessage(
                        Component.translatable("chat.shincolle.wrench.toofar"),
                        false
                    );
                    return;
                }
                if (
                    player.level().getBlockEntity(pos1) instanceof
                        IWaypoint wpFrom &&
                    (player.level().getBlockEntity(pos2) instanceof
                            net.minecraft.world.level.block.entity.BaseContainerBlockEntity ||
                        player.level().getBlockEntity(pos2) instanceof
                            org.trp.shincolle.block.entity.CraneBlockEntity)
                ) {
                    if (
                        wpFrom.getOwnerUUID() != null &&
                        !wpFrom.getOwnerUUID().equals(player.getUUID())
                    ) {
                        player.displayClientMessage(
                            Component.translatable("chat.shincolle.wrongowner"),
                            false
                        );
                        return;
                    }
                    wpFrom.setChestPos(pos2);
                    player.displayClientMessage(
                        Component.translatable(
                            "chat.shincolle.wrench.setwp"
                        ).append(
                            " " +
                                pos1.getX() +
                                " " +
                                pos1.getY() +
                                " " +
                                pos1.getZ() +
                                " & " +
                                pos2.getX() +
                                " " +
                                pos2.getY() +
                                " " +
                                pos2.getZ()
                        ),
                        false
                    );
                }
            } else if (this.action() == 2) {
                var be1 = player.level().getBlockEntity(pos1);
                var be2 = player.level().getBlockEntity(pos2);

                if (
                    be1 instanceof IWaypoint wp1 && be2 instanceof IWaypoint wp2
                ) {
                    if (dist > 48.0 * 48.0) {
                        player.displayClientMessage(
                            Component.translatable(
                                "chat.shincolle.wrench.wptoofar"
                            ),
                            false
                        );
                        return;
                    }
                    if (
                        wp1.getOwnerUUID() != null &&
                        !wp1.getOwnerUUID().equals(player.getUUID())
                    ) {
                        player.displayClientMessage(
                            Component.translatable("chat.shincolle.wrongowner"),
                            false
                        );
                        return;
                    }
                    wp1.setNextPos(pos2);
                    if (!wp2.getNextPos().equals(pos1)) {
                        wp2.setLastPos(pos1);
                    }
                    player.displayClientMessage(
                        Component.translatable(
                            "chat.shincolle.wrench.setwp"
                        ).append(
                            " " +
                                pos1.getX() +
                                " " +
                                pos1.getY() +
                                " " +
                                pos1.getZ() +
                                " --> " +
                                pos2.getX() +
                                " " +
                                pos2.getY() +
                                " " +
                                pos2.getZ()
                        ),
                        false
                    );
                } else if (
                    be1 instanceof IWaypoint wp &&
                    (be2 instanceof
                            net.minecraft.world.level.block.entity.BaseContainerBlockEntity ||
                        be2 instanceof
                            org.trp.shincolle.block.entity.CraneBlockEntity)
                ) {
                    if (dist > 16.0 * 16.0) {
                        player.displayClientMessage(
                            Component.translatable(
                                "chat.shincolle.wrench.toofar"
                            ),
                            false
                        );
                        return;
                    }
                    if (
                        wp.getOwnerUUID() != null &&
                        !wp.getOwnerUUID().equals(player.getUUID())
                    ) {
                        player.displayClientMessage(
                            Component.translatable("chat.shincolle.wrongowner"),
                            false
                        );
                        return;
                    }
                    wp.setChestPos(pos2);
                    player.displayClientMessage(
                        Component.translatable(
                            "chat.shincolle.wrench.setwp"
                        ).append(
                            " " +
                                pos1.getX() +
                                " " +
                                pos1.getY() +
                                " " +
                                pos1.getZ() +
                                " & " +
                                pos2.getX() +
                                " " +
                                pos2.getY() +
                                " " +
                                pos2.getZ()
                        ),
                        false
                    );
                } else if (
                    be2 instanceof IWaypoint wp &&
                    (be1 instanceof
                            net.minecraft.world.level.block.entity.BaseContainerBlockEntity ||
                        be1 instanceof
                            org.trp.shincolle.block.entity.CraneBlockEntity)
                ) {
                    if (dist > 16.0 * 16.0) {
                        player.displayClientMessage(
                            Component.translatable(
                                "chat.shincolle.wrench.toofar"
                            ),
                            false
                        );
                        return;
                    }
                    if (
                        wp.getOwnerUUID() != null &&
                        !wp.getOwnerUUID().equals(player.getUUID())
                    ) {
                        player.displayClientMessage(
                            Component.translatable("chat.shincolle.wrongowner"),
                            false
                        );
                        return;
                    }
                    wp.setChestPos(pos1);
                    player.displayClientMessage(
                        Component.translatable(
                            "chat.shincolle.wrench.setwp"
                        ).append(
                            " " +
                                pos2.getX() +
                                " " +
                                pos2.getY() +
                                " " +
                                pos2.getZ() +
                                " & " +
                                pos1.getX() +
                                " " +
                                pos1.getY() +
                                " " +
                                pos1.getZ()
                        ),
                        false
                    );
                } else {
                    player.displayClientMessage(
                        Component.translatable(
                            "chat.shincolle.wrench.wrongtile"
                        ),
                        false
                    );
                }
            }
        });
    }
}
