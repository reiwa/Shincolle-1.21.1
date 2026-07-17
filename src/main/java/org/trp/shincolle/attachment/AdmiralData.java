package org.trp.shincolle.attachment;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AdmiralData {
    public static final int TEAM_COUNT = 9;
    public static final int SLOT_COUNT = 6;

    private final UUID[][] teams = new UUID[TEAM_COUNT][SLOT_COUNT];
    private final boolean[][] selectionStates = new boolean[TEAM_COUNT][SLOT_COUNT];
    private final int[] formationIDs = new int[TEAM_COUNT];
    private final String[] teamNames = new String[TEAM_COUNT];
    private int currentTeamID = 0;
    private boolean hasReceivedBook = false;
    private final java.util.Set<String> customTargetClasses = new java.util.HashSet<>();
    private int appearance = 0;
    private int marriageNum = 0;
    private boolean hasRing = false;
    private boolean ringActive = false;
    private boolean ringFlying = false;

    public AdmiralData() {
        for (int i = 0; i < TEAM_COUNT; i++) {
            teamNames[i] = "Team " + (i + 1);
            for (int j = 0; j < SLOT_COUNT; j++) {
                selectionStates[i][j] = true;
            }
        }
    }

    public boolean isRingFlying() {
        return ringFlying;
    }

    public void setRingFlying(boolean ringFlying) {
        this.ringFlying = ringFlying;
    }

    public int getMarriageNum() {
        return marriageNum;
    }

    public void setMarriageNum(int marriageNum) {
        this.marriageNum = marriageNum;
    }

    public boolean hasRing() {
        return hasRing;
    }

    public void setHasRing(boolean hasRing) {
        this.hasRing = hasRing;
    }

    public boolean isRingActive() {
        return ringActive;
    }

    public void setRingActive(boolean ringActive) {
        this.ringActive = ringActive;
    }

    public boolean hasReceivedBook() {
        return hasReceivedBook;
    }

    public void setHasReceivedBook(boolean hasReceivedBook) {
        this.hasReceivedBook = hasReceivedBook;
    }

    public int getAppearance() {
        return appearance;
    }

    public void setAppearance(int appearance) {
        this.appearance = appearance;
    }

    public UUID getShipUUID(int teamId, int slotId) {
        if (teamId < 0 || teamId >= TEAM_COUNT || slotId < 0 || slotId >= SLOT_COUNT) return null;
        return teams[teamId][slotId];
    }

    public void setShipUUID(int teamId, int slotId, @Nullable UUID uuid) {
        if (teamId < 0 || teamId >= TEAM_COUNT || slotId < 0 || slotId >= SLOT_COUNT) return;
        teams[teamId][slotId] = uuid;
    }

    public boolean isSelected(int teamId, int slotId) {
        if (teamId < 0 || teamId >= TEAM_COUNT || slotId < 0 || slotId >= SLOT_COUNT) return false;
        return selectionStates[teamId][slotId];
    }

    public void setSelected(int teamId, int slotId, boolean selected) {
        if (teamId < 0 || teamId >= TEAM_COUNT || slotId < 0 || slotId >= SLOT_COUNT) return;
        selectionStates[teamId][slotId] = selected;
    }

    public int getFormationID(int teamId) {
        if (teamId < 0 || teamId >= TEAM_COUNT) return 0;
        return formationIDs[teamId];
    }

    public void setFormationID(int teamId, int formationId) {
        if (teamId < 0 || teamId >= TEAM_COUNT) return;
        formationIDs[teamId] = formationId;
    }

    public String getTeamName(int teamId) {
        if (teamId < 0 || teamId >= TEAM_COUNT) return "";
        return teamNames[teamId];
    }

    public void swapShips(int teamId, int slot1, int slot2) {
        if (teamId < 0 || teamId >= TEAM_COUNT) return;
        if (slot1 < 0 || slot1 >= SLOT_COUNT || slot2 < 0 || slot2 >= SLOT_COUNT) return;
        
        UUID tempUUID = teams[teamId][slot1];
        teams[teamId][slot1] = teams[teamId][slot2];
        teams[teamId][slot2] = tempUUID;
        
        boolean tempSel = selectionStates[teamId][slot1];
        selectionStates[teamId][slot1] = selectionStates[teamId][slot2];
        selectionStates[teamId][slot2] = tempSel;
    }

    public void setTeamName(int teamId, String name) {
        if (teamId < 0 || teamId >= TEAM_COUNT) return;
        teamNames[teamId] = name;
    }

    public int getCurrentTeamID() {
        return currentTeamID;
    }

    public void setCurrentTeamID(int currentTeamID) {
        this.currentTeamID = currentTeamID;
    }

    public int findFirstEmptySlot(int teamId) {
        if (teamId < 0 || teamId >= TEAM_COUNT) return -1;
        for (int i = 0; i < SLOT_COUNT; i++) {
            if (teams[teamId][i] == null) return i;
        }
        return -1;
    }

    public boolean isShipInTeam(int teamId, UUID uuid) {
        if (teamId < 0 || teamId >= TEAM_COUNT || uuid == null) return false;
        for (int i = 0; i < SLOT_COUNT; i++) {
            if (uuid.equals(teams[teamId][i])) return true;
        }
        return false;
    }

    public boolean isShipInAnyTeam(UUID uuid) {
        if (uuid == null) return false;
        for (int i = 0; i < TEAM_COUNT; i++) {
            if (isShipInTeam(i, uuid)) return true;
        }
        return false;
    }

    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag teamsList = new ListTag();
        for (int i = 0; i < TEAM_COUNT; i++) {
            CompoundTag teamTag = new CompoundTag();
            ListTag slotsList = new ListTag();
            for (int j = 0; j < SLOT_COUNT; j++) {
                CompoundTag slotTag = new CompoundTag();
                if (teams[i][j] != null) {
                    slotTag.putUUID("UUID", teams[i][j]);
                }
                slotTag.putBoolean("Selected", selectionStates[i][j]);
                slotsList.add(slotTag);
            }
            teamTag.put("Slots", slotsList);
            teamTag.putInt("Formation", formationIDs[i]);
            teamTag.putString("Name", teamNames[i]);
            teamsList.add(teamTag);
        }
        nbt.put("Teams", teamsList);
        nbt.putInt("CurrentTeam", currentTeamID);
        nbt.putBoolean("HasReceivedBook", hasReceivedBook);
        nbt.putInt("Appearance", appearance);
        nbt.putInt("MarriageNum", marriageNum);
        nbt.putBoolean("HasRing", hasRing);
        nbt.putBoolean("RingActive", ringActive);
        nbt.putBoolean("RingFlying", ringFlying);

        ListTag customTargetsList = new ListTag();
        for (String targetClass : customTargetClasses) {
            customTargetsList.add(net.minecraft.nbt.StringTag.valueOf(targetClass));
        }
        nbt.put("CustomTargetClasses", customTargetsList);

        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        if (nbt.contains("Teams", Tag.TAG_LIST)) {
            ListTag teamsList = nbt.getList("Teams", Tag.TAG_COMPOUND);
            for (int i = 0; i < Math.min(TEAM_COUNT, teamsList.size()); i++) {
                CompoundTag teamTag = teamsList.getCompound(i);
                if (teamTag.contains("Slots", Tag.TAG_LIST)) {
                    ListTag slotsList = teamTag.getList("Slots", Tag.TAG_COMPOUND);
                    for (int j = 0; j < Math.min(SLOT_COUNT, slotsList.size()); j++) {
                        CompoundTag slotTag = slotsList.getCompound(j);
                        if (slotTag.hasUUID("UUID")) {
                            teams[i][j] = slotTag.getUUID("UUID");
                        } else {
                            teams[i][j] = null;
                        }
                        selectionStates[i][j] = slotTag.getBoolean("Selected");
                    }
                }
                formationIDs[i] = teamTag.getInt("Formation");
                teamNames[i] = teamTag.getString("Name");
            }
        }
        currentTeamID = nbt.getInt("CurrentTeam");
        hasReceivedBook = nbt.getBoolean("HasReceivedBook");
        appearance = nbt.getInt("Appearance");
        marriageNum = nbt.getInt("MarriageNum");
        hasRing = nbt.getBoolean("HasRing");
        ringActive = nbt.getBoolean("RingActive");
        ringFlying = nbt.getBoolean("RingFlying");

        customTargetClasses.clear();
        if (nbt.contains("CustomTargetClasses", Tag.TAG_LIST)) {
            ListTag customTargetsList = nbt.getList("CustomTargetClasses", Tag.TAG_STRING);
            for (int i = 0; i < customTargetsList.size(); i++) {
                customTargetClasses.add(customTargetsList.getString(i));
            }
        }
    }

    public java.util.Set<String> getCustomTargetClasses() {
        return customTargetClasses;
    }

    public boolean toggleCustomTargetClass(String targetClass) {
        if (customTargetClasses.contains(targetClass)) {
            customTargetClasses.remove(targetClass);
            return false;
        } else {
            customTargetClasses.add(targetClass);
            return true;
        }
    }

    public static AdmiralData read(CompoundTag nbt, IAttachmentHolder holder) {
        AdmiralData data = new AdmiralData();
        data.deserializeNBT(nbt);
        return data;
    }

    public CompoundTag write(IAttachmentHolder holder) {
        return serializeNBT();
    }
}
