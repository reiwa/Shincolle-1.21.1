package org.trp.shincolle.team;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;

public class TeamData {
    private int teamID;
    private String teamName;
    private String leaderName;
    private List<Integer> teamBanID;
    private List<Integer> teamAllyID;

    public TeamData() {
        this.teamID = 0;
        this.teamName = "   ";
        this.leaderName = "   ";
        this.teamBanID = new ArrayList<>();
        this.teamAllyID = new ArrayList<>();
    }

    public TeamData(int teamID, String teamName, String leaderName) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.leaderName = leaderName;
        this.teamBanID = new ArrayList<>();
        this.teamAllyID = new ArrayList<>();
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String getTeamLeaderName() {
        return this.leaderName;
    }

    public int getTeamID() {
        return this.teamID;
    }

    public List<Integer> getTeamBannedList() {
        if (this.teamBanID == null) {
            this.teamBanID = new ArrayList<>();
        }
        return this.teamBanID;
    }

    public List<Integer> getTeamAllyList() {
        if (this.teamAllyID == null) {
            this.teamAllyID = new ArrayList<>();
        }
        return this.teamAllyID;
    }

    public void setTeamName(String name) {
        this.teamName = name;
    }

    public void setTeamLeaderName(String name) {
        this.leaderName = name;
    }

    public void setTeamID(int id) {
        this.teamID = id;
    }

    public void setTeamBannedList(List<Integer> list) {
        this.teamBanID = list;
    }

    public void setTeamAllyList(List<Integer> list) {
        this.teamAllyID = list;
    }

    public void addTeamAlly(int id) {
        if (id > 0 && this.teamAllyID != null && !this.teamAllyID.contains(id) && !this.teamBanID.contains(id)) {
            this.teamAllyID.add(id);
        }
    }

    public void removeTeamAlly(int id) {
        if (id > 0 && this.teamAllyID != null && this.teamAllyID.contains(id)) {
            this.teamAllyID.remove(Integer.valueOf(id));
        }
    }

    public void addTeamBanned(int id) {
        if (id > 0 && this.teamBanID != null && !this.teamBanID.contains(id) && !this.teamAllyID.contains(id)) {
            this.teamBanID.add(id);
        }
    }

    public void removeTeamBanned(int id) {
        if (id > 0 && this.teamBanID != null && this.teamBanID.contains(id)) {
            this.teamBanID.remove(Integer.valueOf(id));
        }
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("ID", this.teamID);
        tag.putString("Name", this.teamName != null ? this.teamName : "");
        tag.putString("Leader", this.leaderName != null ? this.leaderName : "");
        
        ListTag allyList = new ListTag();
        for (int id : getTeamAllyList()) {
            CompoundTag entry = new CompoundTag();
            entry.putInt("V", id);
            allyList.add(entry);
        }
        tag.put("Ally", allyList);

        ListTag banList = new ListTag();
        for (int id : getTeamBannedList()) {
            CompoundTag entry = new CompoundTag();
            entry.putInt("V", id);
            banList.add(entry);
        }
        tag.put("Ban", banList);

        return tag;
    }

    public static TeamData deserializeNBT(CompoundTag tag) {
        TeamData data = new TeamData();
        data.teamID = tag.getInt("ID");
        data.teamName = tag.getString("Name");
        data.leaderName = tag.getString("Leader");

        if (tag.contains("Ally", Tag.TAG_LIST)) {
            ListTag allyList = tag.getList("Ally", Tag.TAG_COMPOUND);
            for (int i = 0; i < allyList.size(); i++) {
                data.getTeamAllyList().add(allyList.getCompound(i).getInt("V"));
            }
        }

        if (tag.contains("Ban", Tag.TAG_LIST)) {
            ListTag banList = tag.getList("Ban", Tag.TAG_COMPOUND);
            for (int i = 0; i < banList.size(); i++) {
                data.getTeamBannedList().add(banList.getCompound(i).getInt("V"));
            }
        }

        return data;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.teamID);
        buf.writeUtf(this.teamName != null ? this.teamName : "");
        buf.writeUtf(this.leaderName != null ? this.leaderName : "");

        List<Integer> allys = getTeamAllyList();
        buf.writeInt(allys.size());
        for (int id : allys) {
            buf.writeInt(id);
        }

        List<Integer> bans = getTeamBannedList();
        buf.writeInt(bans.size());
        for (int id : bans) {
            buf.writeInt(id);
        }
    }

    public static TeamData decode(FriendlyByteBuf buf) {
        TeamData data = new TeamData();
        data.teamID = buf.readInt();
        data.teamName = buf.readUtf();
        data.leaderName = buf.readUtf();

        int allySize = buf.readInt();
        for (int i = 0; i < allySize; i++) {
            data.getTeamAllyList().add(buf.readInt());
        }

        int banSize = buf.readInt();
        for (int i = 0; i < banSize; i++) {
            data.getTeamBannedList().add(buf.readInt());
        }

        return data;
    }
}
