package org.trp.shincolle.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.base.EntityMountBase;
import org.trp.shincolle.entity.base.EntityShipBase;

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

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null) return;
            if (!(player.getVehicle() instanceof EntityMountBase mount)) {
                return;
            }
            EntityShipBase ship = mount.getHost();
            if (ship == null || !ship.isOwnedBy(player)) return;

            if (this.action() == 1) {
                ship.openShipMenu(player);
            } else if (this.action() == 12) {
                int skill = this.skillKey();
                if (skill < 0 || skill > 3) return;

                boolean canAttack = switch (skill) {
                    case 0 -> ship.getStateComponent().isStateLightAttack();
                    case 1 -> ship.getStateComponent().isStateHeavyAttack();
                    case 2 -> ship.getStateComponent().isStateLightAircraftAttack();
                    case 3 -> ship.getStateComponent().isStateHeavyAircraftAttack();
                    default -> false;
                };

                if (skill < 2 && !canAttack) {
                    return;
                }
                if (ship.getStateComponent().getMountAttackCd(skill) > 0) return;

                double range = Math.max(
                    2.0D,
                    ship.getLegacyShipStats().getAttackRange()
                );
                double rangeSq = range * range;

                net.minecraft.world.entity.Entity target = null;
                Vec3 targetPosition = null;

                if (this.targetEntityId().isPresent()) {
                    net.minecraft.world.entity.Entity found = player
                        .level()
                        .getEntity(this.targetEntityId().get());
                    if (found != null && ship.distanceToSqr(found) <= rangeSq) {
                        target = found;
                    }
                } else if (this.targetPos().isPresent()) {
                    BlockPos pos = this.targetPos().get();
                    Vec3 center = Vec3.atCenterOf(pos);
                    if (ship.distanceToSqr(center) <= rangeSq) {
                        targetPosition = center;
                    }
                }

                if (target == null && targetPosition == null) return;
                if (
                    target != null && target.getUUID().equals(player.getUUID())
                ) return;

                switch (skill) {
                    case 0:
                        if (target != null) {
                            ship.executeMountLightAttack(target);
                            ship.getStateComponent().setMountAttackCd(
                                0,
                                ship.getLegacyShipStats().getLightDelay()
                            );
                        }
                        break;
                    case 1:
                        if (target != null) {
                            ship.executeMountHeavyAttack(target);
                            ship.getStateComponent().setMountAttackCd(
                                1,
                                ship.getLegacyShipStats().getHeavyDelay()
                            );
                        } else if (targetPosition != null) {
                            ship.executeMountHeavyAttack(targetPosition);
                            ship.getStateComponent().setMountAttackCd(
                                1,
                                ship.getLegacyShipStats().getHeavyDelay()
                            );
                        }
                        break;
                    case 2:
                        if (target != null) {
                            boolean launched =
                                ship.executeMountLightAircraftAttack(target);
                            if (launched) {
                                int delay = ship
                                    .getLegacyShipStats()
                                    .getLightDelay();
                                ship.getStateComponent().setMountAttackCd(2, delay);
                                ship.getStateComponent().setMountAttackCd(3, delay);
                            }
                        }
                        break;
                    case 3:
                        if (target != null) {
                            boolean launched =
                                ship.executeMountHeavyAircraftAttack(target);
                            if (launched) {
                                int delay = ship
                                    .getLegacyShipStats()
                                    .getHeavyDelay();
                                ship.getStateComponent().setMountAttackCd(2, delay);
                                ship.getStateComponent().setMountAttackCd(3, delay);
                            }
                        }
                        break;
                }
            }
        });
    }
}
