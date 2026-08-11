package org.trp.shincolle.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.trp.shincolle.Shincolle;

public class ModNetwork {

    public static void sendToServer(CustomPacketPayload payload) {
        PacketDistributor.sendToServer(payload);
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Shincolle.MODID);
        registrar.playToServer(
            C2SBookStatePayload.TYPE,
            C2SBookStatePayload.STREAM_CODEC,
            C2SBookStatePayload::handle
        );
        registrar.playToServer(
            C2SDeskGuiPayload.TYPE,
            C2SDeskGuiPayload.STREAM_CODEC,
            C2SDeskGuiPayload::handle
        );
        registrar.playToServer(
            C2SWaypointActionPayload.TYPE,
            C2SWaypointActionPayload.STREAM_CODEC,
            C2SWaypointActionPayload::handle
        );
        registrar.playToServer(
            C2SPointerActionPayload.TYPE,
            C2SPointerActionPayload.STREAM_CODEC,
            C2SPointerActionPayload::handle
        );
        registrar.playToServer(
            C2SFormationActionPayload.TYPE,
            C2SFormationActionPayload.STREAM_CODEC,
            C2SFormationActionPayload::handle
        );
        registrar.playToServer(
            C2SDeskSummonPayload.TYPE,
            C2SDeskSummonPayload.STREAM_CODEC,
            C2SDeskSummonPayload::handle
        );
        registrar.playToServer(
            C2SOPToolActionPayload.TYPE,
            C2SOPToolActionPayload.STREAM_CODEC,
            C2SOPToolActionPayload::handle
        );
        registrar.playToServer(
            C2SMountInputPayload.TYPE,
            C2SMountInputPayload.STREAM_CODEC,
            C2SMountInputPayload::handle
        );
        registrar.playToClient(
            S2CAdmiralDataSyncPayload.TYPE,
            S2CAdmiralDataSyncPayload.STREAM_CODEC,
            S2CAdmiralDataSyncPayload::handle
        );
        registrar.playToClient(
            S2CCollectedShipsSyncPayload.TYPE,
            S2CCollectedShipsSyncPayload.STREAM_CODEC,
            S2CCollectedShipsSyncPayload::handle
        );
        registrar.playToServer(
            C2SPlayerAppearancePayload.TYPE,
            C2SPlayerAppearancePayload.STREAM_CODEC,
            C2SPlayerAppearancePayload::handle
        );
        registrar.playToServer(
            C2SPetShipPayload.TYPE,
            C2SPetShipPayload.STREAM_CODEC,
            C2SPetShipPayload::handle
        );
        registrar.playToServer(
            C2STeamActionPayload.TYPE,
            C2STeamActionPayload.STREAM_CODEC,
            C2STeamActionPayload::handle
        );
        registrar.playToClient(
            S2CTeamSyncPayload.TYPE,
            S2CTeamSyncPayload.STREAM_CODEC,
            S2CTeamSyncPayload::handle
        );
        registrar.playToServer(
            C2STargetClassTogglePayload.TYPE,
            C2STargetClassTogglePayload.STREAM_CODEC,
            C2STargetClassTogglePayload::handle
        );
    }
}
