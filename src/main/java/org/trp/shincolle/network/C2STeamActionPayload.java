package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.team.TeamData;
import org.trp.shincolle.world.TeamSavedData;

import java.util.ArrayList;
import java.util.List;

public record C2STeamActionPayload(int action, int targetTeamId, String valueStr) implements CustomPacketPayload {
    public static final Type<C2STeamActionPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "c2s_team_action"));

    public static final StreamCodec<FriendlyByteBuf, C2STeamActionPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2STeamActionPayload::action,
            ByteBufCodecs.VAR_INT, C2STeamActionPayload::targetTeamId,
            ByteBufCodecs.STRING_UTF8, C2STeamActionPayload::valueStr,
            C2STeamActionPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (!(player instanceof ServerPlayer serverPlayer)) return;

            ServerLevel level = serverPlayer.serverLevel();
            TeamSavedData savedData = TeamSavedData.get(level);
            int playerUID = TeamSavedData.getPlayerUID(player);

            switch (this.action()) {
                case 0 -> savedData.createTeam(player, this.valueStr());
                case 1 -> savedData.renameTeam(playerUID, this.valueStr());
                case 2 -> savedData.disbandTeam(playerUID);
                case 3 -> savedData.addAlly(playerUID, this.targetTeamId());
                case 4 -> savedData.removeAlly(playerUID, this.targetTeamId());
                case 5 -> savedData.addBan(playerUID, this.targetTeamId());
                case 6 -> savedData.removeBan(playerUID, this.targetTeamId());
            }

            List<TeamData> teams = new ArrayList<>(savedData.getAllTeams());
            S2CTeamSyncPayload syncPayload = new S2CTeamSyncPayload(teams);

            for (ServerPlayer p : level.getServer().getPlayerList().getPlayers()) {
                PacketDistributor.sendToPlayer(p, syncPayload);
            }
        });
    }
}
