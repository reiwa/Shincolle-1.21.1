package org.trp.shincolle.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.screen.DeskScreen;
import org.trp.shincolle.team.TeamData;

import java.util.ArrayList;
import java.util.List;

public record S2CTeamSyncPayload(List<TeamData> teams) implements CustomPacketPayload {
    public static final Type<S2CTeamSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "s2c_team_sync"));

    public static final StreamCodec<FriendlyByteBuf, S2CTeamSyncPayload> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public S2CTeamSyncPayload decode(FriendlyByteBuf buf) {
            int size = buf.readInt();
            List<TeamData> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(TeamData.decode(buf));
            }
            return new S2CTeamSyncPayload(list);
        }

        @Override
        public void encode(FriendlyByteBuf buf, S2CTeamSyncPayload payload) {
            buf.writeInt(payload.teams().size());
            for (TeamData data : payload.teams()) {
                data.encode(buf);
            }
        }
    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(final IPayloadContext context) {
        context.enqueueWork(() -> {
            DeskScreen.updateClientTeams(this.teams());
        });
    }
}
