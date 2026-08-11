package org.trp.shincolle.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;
import org.trp.shincolle.team.TeamData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TeamSavedData extends SavedData {
    public static final String SAVE_ID = "shincolle_teams";
    private final Map<Integer, TeamData> teams = new HashMap<>();

    public TeamSavedData() {
    }

    public static TeamSavedData get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        TeamSavedData::new,
                        TeamSavedData::load
                ),
                SAVE_ID
        );
    }

    public static int getPlayerUID(Player player) {
        if (player == null) return 0;
        int hash = player.getUUID().hashCode();
        return hash == 0 ? 1 : Math.abs(hash);
    }

    private static TeamSavedData load(CompoundTag tag, HolderLookup.Provider registries) {
        TeamSavedData data = new TeamSavedData();
        if (tag.contains("Teams", Tag.TAG_LIST)) {
            ListTag list = tag.getList("Teams", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                TeamData tdata = TeamData.deserializeNBT(list.getCompound(i));
                data.teams.put(tdata.getTeamID(), tdata);
            }
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        ListTag list = new ListTag();
        for (TeamData tdata : teams.values()) {
            list.add(tdata.serializeNBT());
        }
        tag.put("Teams", list);
        return tag;
    }

    public Collection<TeamData> getAllTeams() {
        return teams.values();
    }

    public TeamData getTeamData(int teamID) {
        return teams.get(teamID);
    }

    public void createTeam(Player player, String teamName) {
        int uid = getPlayerUID(player);
        if (uid > 0 && teamName != null && teamName.length() > 1) {
            TeamData tdata = new TeamData(uid, teamName, player.getScoreboardName());
            teams.put(uid, tdata);
            setDirty();
        }
    }

    public void renameTeam(int teamID, String newName) {
        TeamData tdata = teams.get(teamID);
        if (tdata != null && newName != null && newName.length() > 1) {
            tdata.setTeamName(newName);
            setDirty();
        }
    }

    public void disbandTeam(int teamID) {
        if (teams.containsKey(teamID)) {
            teams.remove(teamID);
            for (TeamData tdata : teams.values()) {
                tdata.removeTeamAlly(teamID);
                tdata.removeTeamBanned(teamID);
            }
            setDirty();
        }
    }

    public void addAlly(int teamID1, int teamID2) {
        if (teamID1 > 0 && teamID2 > 0 && teamID1 != teamID2) {
            TeamData t1 = teams.get(teamID1);
            if (t1 != null && teams.containsKey(teamID2)) {
                t1.addTeamAlly(teamID2);
                setDirty();
            }
        }
    }

    public void removeAlly(int teamID1, int teamID2) {
        if (teamID1 > 0 && teamID2 > 0) {
            TeamData t1 = teams.get(teamID1);
            if (t1 != null) t1.removeTeamAlly(teamID2);
            TeamData t2 = teams.get(teamID2);
            if (t2 != null) t2.removeTeamAlly(teamID1);
            setDirty();
        }
    }

    public void addBan(int teamID1, int teamID2) {
        if (teamID1 > 0 && teamID2 > 0 && teamID1 != teamID2) {
            TeamData t1 = teams.get(teamID1);
            TeamData t2 = teams.get(teamID2);
            if (t1 != null && t2 != null) {
                t1.addTeamBanned(teamID2);
                t2.addTeamBanned(teamID1);
                setDirty();
            }
        }
    }

    public void removeBan(int teamID1, int teamID2) {
        if (teamID1 > 0 && teamID2 > 0) {
            TeamData t1 = teams.get(teamID1);
            if (t1 != null) {
                t1.removeTeamBanned(teamID2);
                setDirty();
            }
        }
    }
}
