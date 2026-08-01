package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.item.PointerItem;

import java.util.Optional;
import java.util.UUID;

public record C2SPointerActionPayload(int action, Optional<UUID> targetEntity, Optional<Vec3> targetPos) implements CustomPacketPayload {
    public static final Type<C2SPointerActionPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_pointer_action"));

    public static final StreamCodec<FriendlyByteBuf, C2SPointerActionPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SPointerActionPayload::action,
            ByteBufCodecs.optional(UUIDUtil.STREAM_CODEC), C2SPointerActionPayload::targetEntity,
            ByteBufCodecs.optional(Vec3Util.STREAM_CODEC), C2SPointerActionPayload::targetPos,
            C2SPointerActionPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null) return;
            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof PointerItem)) {
                stack = player.getOffhandItem();
            }

            if (!(stack.getItem() instanceof PointerItem pointerItem)) return;

            if (this.action() == 0) {
                int next = pointerItem.cycleMode(stack);
                PointerItem.updateServerSideMode(player, stack, next);
            } else if (this.action() == 1 || this.action() == 2) {
                int mode = pointerItem.getMode(stack);
                AdmiralData data = player.getData(
                    ModDataAttachments.ADMIRAL_DATA
                );
                int teamId = data.getCurrentTeamID();

                if (mode == PointerItem.MODE_FORMATION) {
                    for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
                        if (data.isSelected(teamId, i)) {
                            UUID shipUUID = data.getShipUUID(teamId, i);
                            if (
                                shipUUID != null &&
                                player.level() instanceof
                                    net.minecraft.server.level.ServerLevel serverLevel
                            ) {
                                net.minecraft.world.entity.Entity entity =
                                    serverLevel.getEntity(shipUUID);
                                if (entity instanceof EntityShipBase ship) {
                                    if (
                                        this.action() == 1 &&
                                        this.targetEntity().isPresent()
                                    ) {
                                        ship.setPointerTargetEntity(
                                            serverLevel.getEntity(
                                                this.targetEntity().get()
                                            ),
                                            1200
                                        );
                                    } else if (
                                         this.action() == 2 &&
                                         this.targetPos().isPresent()
                                     ) {
                                         net.minecraft.world.phys.Vec3 pos = this.targetPos().get();
                                         ship.setPointerTarget(
                                             pos,
                                             1200
                                         );
                                         net.minecraft.core.BlockPos bpos = net.minecraft.core.BlockPos.containing(pos);
                                         ship.getStateComponent().setGuardX(bpos.getX());
                                         ship.getStateComponent().setGuardY(bpos.getY());
                                         ship.getStateComponent().setGuardZ(bpos.getZ());
                                         ship.getStateComponent().setGuardType(1);
                                         ship.getStateComponent().setStateDisableGuardPos(false);
                                         if (ship.getFuel() > 0) {
                                             ship.getNavigation().moveTo(pos.x, pos.y, pos.z, 1.2D);
                                         }
                                     }
                                }
                            }
                        }
                    }
                }
            } else if (
                this.action() == 3 && this.targetEntity().isPresent()
            ) {
                if (
                    player.level() instanceof
                        net.minecraft.server.level.ServerLevel serverLevel
                ) {
                    net.minecraft.world.entity.Entity entity =
                        serverLevel.getEntity(this.targetEntity().get());
                    if (
                        entity instanceof EntityShipBase ship &&
                        ship.isOwnedBy(player)
                    ) {
                        ship.openShipMenu(player);
                    }
                }
            } else if (
                this.action() == 5 && this.targetEntity().isPresent()
            ) {
                UUID targetUUID = this.targetEntity().get();
                AdmiralData data = player.getData(
                    ModDataAttachments.ADMIRAL_DATA
                );
                int teamId = data.getCurrentTeamID();
                int slot = -1;
                for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
                    if (targetUUID.equals(data.getShipUUID(teamId, i))) {
                        slot = i;
                        break;
                    }
                }

                if (slot != -1) {
                    boolean nextState = !data.isSelected(teamId, slot);
                    data.setSelected(teamId, slot, nextState);
                    if (
                        player.level() instanceof
                            net.minecraft.server.level.ServerLevel serverLevel
                    ) {
                        net.minecraft.world.entity.Entity e =
                            serverLevel.getEntity(targetUUID);
                        if (e instanceof EntityShipBase ship) {
                            ship.setPointerSelected(nextState);
                        }
                    }
                } else {
                    for (int i = 0; i < AdmiralData.SLOT_COUNT; i++) {
                        if (data.getShipUUID(teamId, i) == null) {
                            data.setShipUUID(teamId, i, targetUUID);
                            data.setSelected(teamId, i, true);
                            if (
                                player.level() instanceof
                                    net.minecraft.server.level.ServerLevel serverLevel
                            ) {
                                net.minecraft.world.entity.Entity e =
                                    serverLevel.getEntity(targetUUID);
                                if (e instanceof EntityShipBase ship) {
                                    ship.setFormationTeam(teamId);
                                    ship.setFormationSlot(i);
                                    ship.setPointerSelected(true);
                                }
                            }
                            break;
                        }
                    }
                }
                context.reply(
                    new S2CAdmiralDataSyncPayload(data.serializeNBT())
                );
            } else if (this.action() == 4) {
                player.openMenu(
                    new net.minecraft.world.SimpleMenuProvider(
                        (id, inv, p) ->
                            new org.trp.shincolle.menu.FormationMenu(id, inv),
                        net.minecraft.network.chat.Component.translatable(
                            "gui.shincolle.formation.title"
                        )
                    )
                );
            } else if (this.action() == 6) {
                ItemStack main = player.getMainHandItem();
                ItemStack off = player.getOffhandItem();
                ItemStack pointer = main.getItem() instanceof PointerItem
                    ? main
                    : (off.getItem() instanceof PointerItem
                          ? off
                          : ItemStack.EMPTY);
                if (
                    !pointer.isEmpty() &&
                    pointer.getItem() instanceof PointerItem pi
                ) {
                    boolean next = !pi.isPetting(pointer);
                    pi.setPetting(pointer, next);
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

    private static class Vec3Util {
        public static final StreamCodec<FriendlyByteBuf, Vec3> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public Vec3 decode(FriendlyByteBuf buffer) {
                return new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
            }

            @Override
            public void encode(FriendlyByteBuf buffer, Vec3 value) {
                buffer.writeDouble(value.x);
                buffer.writeDouble(value.y);
                buffer.writeDouble(value.z);
            }
        };
    }
}
