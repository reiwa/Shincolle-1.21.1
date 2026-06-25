package org.trp.shincolle.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

import static org.trp.shincolle.entity.base.EntityShipBase.*;
import static org.trp.shincolle.entity.base.EntityShipData.*;

public class ShipStateComponent {

    private final IShipDataSyncher syncher;
    private final EntityShipLegacyState legacyState;
    private boolean legacyStateInitialized = false;

    public ShipStateComponent(IShipDataSyncher syncher) {
        this.syncher = syncher;
        this.legacyState = new EntityShipLegacyState();
    }

    public EntityShipLegacyState getLegacyState() {
        return this.legacyState;
    }

    public boolean isLegacyStateInitialized() {
        return this.legacyStateInitialized;
    }

    public void setLegacyStateInitialized(boolean val) {
        this.legacyStateInitialized = val;
    }

    public int getLevel() {
        return this.syncher.getData(SHIP_LEVEL);
    }

    public void setLevel(int level) {
        this.syncher.setData(SHIP_LEVEL, Mth.clamp(level, 1, 150));
    }

    public int getExp() {
        return this.syncher.getData(SHIP_EXP);
    }

    public void setExp(int exp) {
        this.syncher.setData(SHIP_EXP, Math.max(0, exp));
    }

    public int getAmmoLight() {
        return this.syncher.getData(AMMO_LIGHT);
    }

    public void setAmmoLight(int val) {
        this.syncher.setData(AMMO_LIGHT, val);
    }

    public int getAmmoHeavy() {
        return this.syncher.getData(AMMO_HEAVY);
    }

    public void setAmmoHeavy(int val) {
        this.syncher.setData(AMMO_HEAVY, val);
    }

    public int getNumAircraftLight() {
        return this.syncher.getData(AIRCRAFT_LIGHT);
    }

    public int getNumAircraftHeavy() {
        return this.syncher.getData(AIRCRAFT_HEAVY);
    }

    public void setNumAircraftLight(int count) {
        int val = Math.max(0, count);
        this.syncher.setData(AIRCRAFT_LIGHT, val);
        legacyState.setInt(legacyState.stateMinor, STATE_MINOR_AIRCRAFT_LIGHT, val);
    }

    public void setNumAircraftHeavy(int count) {
        int val = Math.max(0, count);
        this.syncher.setData(AIRCRAFT_HEAVY, val);
        legacyState.setInt(legacyState.stateMinor, STATE_MINOR_AIRCRAFT_HEAVY, val);
    }

    public boolean isPointerSelected() {
        return this.syncher.getData(POINTER_SELECTED);
    }

    public void setPointerSelected(boolean selected) {
        this.syncher.setData(POINTER_SELECTED, selected);
    }

    public int getFaceId() {
        return this.syncher.getData(FACE_ID);
    }

    public void setFaceId(int id) {
        this.syncher.setData(FACE_ID, Mth.clamp(id, EntityShipBase.FACE_ID_MIN, EntityShipBase.FACE_ID_MAX));
    }

    public int getMouthId() {
        return this.syncher.getData(MOUTH_ID);
    }

    public void setMouthId(int id) {
        this.syncher.setData(MOUTH_ID, Mth.clamp(id, EntityShipBase.MOUTH_ID_MIN, EntityShipBase.MOUTH_ID_MAX));
    }

    public int getEmotionPrimary() {
        return this.syncher.getData(EMOTION_PRIMARY);
    }

    public void setEmotionPrimary(int val) {
        this.syncher.setData(EMOTION_PRIMARY, val);
        this.setStateEmotion(1, val);
    }

    public int getEmotionSecondary() {
        return this.syncher.getData(EMOTION_SECONDARY);
    }

    public void setEmotionSecondary(int val) {
        this.syncher.setData(EMOTION_SECONDARY, val);
        this.setStateEmotion(7, val);
    }

    public int getMorale() {
        return this.syncher.getData(MORALE);
    }

    public void setMorale(int val) {
        this.syncher.setData(MORALE, Mth.clamp(val, 0, 16000));
    }

    public int getFormationTeam() {
        return this.syncher.getData(FORMATION_TEAM);
    }

    public void setFormationTeam(int team) {
        this.syncher.setData(FORMATION_TEAM, team);
    }

    public int getFormationSlot() {
        return this.syncher.getData(FORMATION_SLOT);
    }

    public void setFormationSlot(int slot) {
        this.syncher.setData(FORMATION_SLOT, slot);
    }

    public boolean isNoFuel() {
        return this.getFuel() <= 0;
    }

    public void setNoFuel(boolean val) {
        this.syncher.setData(NO_FUEL, val);
        if (val) {
            this.syncher.setData(FUEL, 0);
        }
    }

    public int getFuel() {
        return this.syncher.getData(FUEL);
    }

    public void setFuel(int val) {
        int max = 10000;
        int fuel = Mth.clamp(val, 0, max);
        this.syncher.setData(FUEL, fuel);
        this.legacyState.stateMinor[6] = fuel;
        this.setNoFuel(fuel <= 0);
    }

    public int getAttackTick() {
        return this.syncher.getData(LEGACY_ATTACK_TICK);
    }

    public void setAttackTick(int value) {
        this.syncher.setData(LEGACY_ATTACK_TICK, Mth.clamp(value, 0, 100));
    }

    public int getAttackTick2() {
        return this.syncher.getData(LEGACY_ATTACK_TICK_2);
    }

    public void setAttackTick2(int value) {
        this.syncher.setData(LEGACY_ATTACK_TICK_2, Math.max(0, value));
    }

    public int getRidingState() {
        return this.syncher.getData(LEGACY_RIDING_STATE);
    }

    public void setRidingState(int state) {
        this.syncher.setData(LEGACY_RIDING_STATE, state);
    }

    public int getScaleLevel() {
        return this.syncher.getData(LEGACY_SCALE_LEVEL);
    }

    public void setScaleLevel(int level) {
        this.syncher.setData(LEGACY_SCALE_LEVEL, Mth.clamp(level, 0, 3));
    }

    public int getStateEmotion(int index) {
        return switch (index) {
            case 0 -> this.syncher.getData(LEGACY_EMOTION_0);
            case 1 -> this.syncher.getData(LEGACY_EMOTION_1);
            case 2 -> this.syncher.getData(LEGACY_EMOTION_2);
            case 3 -> this.syncher.getData(LEGACY_EMOTION_3);
            case 4 -> this.syncher.getData(LEGACY_EMOTION_4);
            case 5 -> this.syncher.getData(LEGACY_EMOTION_5);
            case 6 -> this.syncher.getData(LEGACY_EMOTION_6);
            case 7 -> this.syncher.getData(LEGACY_EMOTION_7);
            default -> 0;
        };
    }

    public void setStateEmotion(int index, int value) {
        switch (index) {
            case 0 -> this.syncher.setData(LEGACY_EMOTION_0, value);
            case 1 -> {
                this.syncher.setData(LEGACY_EMOTION_1, value);
                this.syncher.setData(EMOTION_PRIMARY, value);
            }
            case 2 -> this.syncher.setData(LEGACY_EMOTION_2, value);
            case 3 -> this.syncher.setData(LEGACY_EMOTION_3, value);
            case 4 -> this.syncher.setData(LEGACY_EMOTION_4, value);
            case 5 -> this.syncher.setData(LEGACY_EMOTION_5, value);
            case 6 -> this.syncher.setData(LEGACY_EMOTION_6, value);
            case 7 -> {
                this.syncher.setData(LEGACY_EMOTION_7, value);
                this.syncher.setData(EMOTION_SECONDARY, value);
            }
            default -> {}
        }
    }



    public boolean getEquipFlag(String key) {
        if (EQUIP_MOUNT.equals(key)) {
            return (this.syncher.getData(LEGACY_EMOTION_0) & 1) != 0;
        }
        CompoundTag nbt = this.syncher.getData(EQUIP_FLAGS);
        return nbt.getBoolean(key);
    }

    public void setEquipFlag(String key, boolean value) {
        if (EQUIP_MOUNT.equals(key)) {
            int current = this.syncher.getData(LEGACY_EMOTION_0);
            this.setStateEmotion(
                0,
                value ? (current | 1) : (current & ~1)
            );
            return;
        }
        CompoundTag nbt = this.syncher.getData(EQUIP_FLAGS).copy();
        nbt.putBoolean(key, value);
        this.syncher.setData(EQUIP_FLAGS, nbt);
    }

    public CompoundTag copyEquipFlagsTag() {
        return this.syncher.getData(EQUIP_FLAGS).copy();
    }

    public void setEquipFlagsTag(CompoundTag flags) {
        this.syncher.setData(EQUIP_FLAGS, flags);
    }



    public boolean isStateMarried() {
        return getLegacyStateFlag(STATE_FLAG_MARRIED);
    }

    public void setStateMarried(boolean value) {
        setLegacyStateFlag(STATE_FLAG_MARRIED, value);
    }

    public boolean isStateNoEquip() {
        return getLegacyStateFlag(STATE_FLAG_NO_EQUIP);
    }

    public void setStateNoEquip(boolean value) {
        setLegacyStateFlag(STATE_FLAG_NO_EQUIP, value);
    }

    public boolean isStateCanMelee() {
        return getLegacyStateFlag(STATE_FLAG_CAN_MELEE);
    }

    public void setStateCanMelee(boolean value) {
        setLegacyStateFlag(STATE_FLAG_CAN_MELEE, value);
    }

    public boolean isStateLightAttack() {
        return getLegacyStateFlag(STATE_FLAG_LIGHT_ATTACK);
    }

    public void setStateLightAttack(boolean value) {
        setLegacyStateFlag(STATE_FLAG_LIGHT_ATTACK, value);
    }

    public boolean isStateHeavyAttack() {
        return getLegacyStateFlag(STATE_FLAG_HEAVY_ATTACK);
    }

    public void setStateHeavyAttack(boolean value) {
        setLegacyStateFlag(STATE_FLAG_HEAVY_ATTACK, value);
    }

    public boolean isStateLightAircraftAttack() {
        return getLegacyStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK);
    }

    public void setStateLightAircraftAttack(boolean value) {
        setLegacyStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK, value);
    }

    public boolean isStateHeavyAircraftAttack() {
        return getLegacyStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK);
    }

    public void setStateHeavyAircraftAttack(boolean value) {
        setLegacyStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK, value);
    }

    public boolean isStateRingEffect() {
        return getLegacyStateFlag(STATE_FLAG_RING_EFFECT);
    }

    public void setStateRingEffect(boolean value) {
        setLegacyStateFlag(STATE_FLAG_RING_EFFECT, value);
    }

    public boolean isStateGuiBtn1() {
        return getLegacyStateFlag(STATE_FLAG_GUI_BTN_1);
    }

    public void setStateGuiBtn1(boolean value) {
        setLegacyStateFlag(STATE_FLAG_GUI_BTN_1, value);
    }

    public boolean isStateGuiBtn2() {
        return getLegacyStateFlag(STATE_FLAG_GUI_BTN_2);
    }

    public void setStateGuiBtn2(boolean value) {
        setLegacyStateFlag(STATE_FLAG_GUI_BTN_2, value);
    }

    public boolean isStateGuiBtn3() {
        return getLegacyStateFlag(STATE_FLAG_GUI_BTN_3);
    }

    public void setStateGuiBtn3(boolean value) {
        setLegacyStateFlag(STATE_FLAG_GUI_BTN_3, value);
    }

    public boolean isStateGuiBtn4() {
        return getLegacyStateFlag(STATE_FLAG_GUI_BTN_4);
    }

    public void setStateGuiBtn4(boolean value) {
        setLegacyStateFlag(STATE_FLAG_GUI_BTN_4, value);
    }

    public boolean isStateAntiAir() {
        return getLegacyStateFlag(STATE_FLAG_ANTI_AIR);
    }

    public void setStateAntiAir(boolean value) {
        setLegacyStateFlag(STATE_FLAG_ANTI_AIR, value);
    }

    public boolean isStateCanRide() {
        return getLegacyStateFlag(STATE_FLAG_CAN_RIDE);
    }

    public void setStateCanRide(boolean value) {
        setLegacyStateFlag(STATE_FLAG_CAN_RIDE, value);
    }

    public boolean isStateAppearance() {
        return getLegacyStateFlag(STATE_FLAG_APPEARANCE);
    }

    public void setStateAppearance(boolean value) {
        setLegacyStateFlag(STATE_FLAG_APPEARANCE, value);
    }

    public boolean isStateDisableGuardPos() {
        return getLegacyStateFlag(STATE_FLAG_DISABLE_GUARD_POS);
    }

    public void setStateDisableGuardPos(boolean value) {
        setLegacyStateFlag(STATE_FLAG_DISABLE_GUARD_POS, value);
    }

    public boolean getLegacyStateFlag(int index) {
        return this.legacyState.getBoolean(this.legacyState.stateFlag, index);
    }

    public void setLegacyStateFlag(int index, boolean value) {
        this.legacyState.setBoolean(this.legacyState.stateFlag, index, value);
    }

    public int getAffectionLegacy() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_AFFECTION_LEGACY);
    }

    public void setAffectionLegacy(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_AFFECTION_LEGACY, val);
    }

    public int getRationMorale() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_RATION_MORALE);
    }

    public void setRationMorale(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_RATION_MORALE, val);
    }

    public int getFollowMin() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_FOLLOW_MIN);
    }

    public void setFollowMin(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_FOLLOW_MIN, val);
    }

    public int getFollowMax() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_FOLLOW_MAX);
    }

    public void setFollowMax(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_FOLLOW_MAX, val);
    }

    public int getFleeHp() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_FLEE_HP);
    }

    public void setFleeHp(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_FLEE_HP, val);
    }

    public int getTaskId() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_TASK_ID);
    }

    public void setTaskId(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_TASK_ID, val);
    }

    public int getTaskSide() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_TASK_SIDE);
    }

    public void setTaskSide(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_TASK_SIDE, val);
    }

    public int getWpStay() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_WP_STAY);
    }

    public void setWpStay(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_WP_STAY, val);
    }

    public int getFactionId() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_FACTION_ID);
    }

    public void setFactionId(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_FACTION_ID, val);
    }

    public int getShipClassId() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_SHIP_CLASS);
    }

    public void setShipClassId(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_SHIP_CLASS, val);
        if (this.syncher instanceof EntityShipBase ship) {
            ship.recalculateLegacyShipStats();
        }
    }

    public int getSpecialEquip() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_SPECIAL_EQUIP);
    }

    public void setSpecialEquip(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_SPECIAL_EQUIP, val);
    }

    public int getGrudgeConsumption() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_GRUDGE_CONSUMPTION);
    }

    public void setGrudgeConsumption(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_GRUDGE_CONSUMPTION, val);
    }

    public int getRarity() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_RARITY);
    }

    public void setRarity(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_RARITY, val);
    }

    public int getEquipDrum() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_DRUM);
    }

    public void setEquipDrum(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_DRUM, val);
    }

    public int getEquipCompass() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_COMPASS);
    }

    public void setEquipCompass(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_COMPASS, val);
    }

    public int getEquipFlare() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_FLARE);
    }

    public void setEquipFlare(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_FLARE, val);
    }

    public int getEquipSearchlight() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_SEARCHLIGHT);
    }

    public void setEquipSearchlight(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_EQUIP_SEARCHLIGHT, val);
    }

    public int getPumpedXp() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_PUMPED_XP);
    }

    public void setPumpedXp(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_PUMPED_XP, val);
    }

    public int getGuardX() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_X);
    }

    public void setGuardX(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_X, val);
    }

    public int getGuardY() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_Y);
    }

    public void setGuardY(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_Y, val);
    }

    public int getGuardZ() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_Z);
    }

    public void setGuardZ(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_Z, val);
    }

    public int getGuardDim() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_DIM);
    }

    public void setGuardDim(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_DIM, val);
    }

    public int getGuardType() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_TYPE);
    }

    public void setGuardType(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_GUARD_TYPE, val);
    }

    public int getCraning() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_CRANING);
    }

    public void setCraning(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_CRANING, val);
    }

    public int getTimekeepLegacy() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_TIMEKEEP_LEGACY);
    }

    public void setTimekeepLegacy(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_TIMEKEEP_LEGACY, val);
    }

    public int getHitHeight() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_HIT_HEIGHT);
    }

    public void setHitHeight(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_HIT_HEIGHT, val);
    }

    public int getHitAngle() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_HIT_ANGLE);
    }

    public void setHitAngle(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_HIT_ANGLE, val);
    }

    public int getSensitiveBody() {
        return this.legacyState.getInt(this.legacyState.stateMinor, STATE_MINOR_SENSITIVE_BODY);
    }

    public void setSensitiveBody(int val) {
        this.legacyState.setInt(this.legacyState.stateMinor, STATE_MINOR_SENSITIVE_BODY, val);
    }

    public boolean isStateOnSight() {
        return this.getLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_ON_SIGHT);
    }

    public void setStateOnSight(boolean val) {
        this.setLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_ON_SIGHT, val);
    }

    public boolean isStatePvp() {
        return this.getLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_PVP);
    }

    public void setStatePvp(boolean val) {
        this.setLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_PVP, val);
    }

    public boolean isStateAntiSub() {
        return this.getLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_ANTI_SUB);
    }

    public void setStateAntiSub(boolean val) {
        this.setLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_ANTI_SUB, val);
    }

    public boolean isStatePassiveAttack() {
        return this.getLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_PASSIVE_ATTACK);
    }

    public void setStatePassiveAttack(boolean val) {
        this.setLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_PASSIVE_ATTACK, val);
    }

    public boolean isStateTimekeep() {
        return this.getLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_TIMEKEEP);
    }

    public void setStateTimekeep(boolean val) {
        this.setLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_TIMEKEEP, val);
    }

    public boolean isStatePickItem() {
        return this.getLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_PICK_ITEM);
    }

    public void setStatePickItem(boolean val) {
        this.setLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_PICK_ITEM, val);
    }

    public boolean isStateAutoPump() {
        return this.getLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_AUTO_PUMP);
    }

    public void setStateAutoPump(boolean val) {
        this.setLegacyStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_AUTO_PUMP, val);
    }

    public int getCraneTimer() {
        return this.legacyState.getInt(this.legacyState.stateTimer, 1);
    }
 
    public void setCraneTimer(int val) {
        this.legacyState.setInt(this.legacyState.stateTimer, 1, val);
    }
 
    public int getWpStayTimer() {
        return this.legacyState.getInt(this.legacyState.stateTimer, 4);
    }
 
    public void setWpStayTimer(int val) {
        this.legacyState.setInt(this.legacyState.stateTimer, 4, val);
    }
 
    public int getEmotionTimer() {
        return this.legacyState.getInt(this.legacyState.stateTimer, 5);
    }
 
    public void setEmotionTimer(int val) {
        this.legacyState.setInt(this.legacyState.stateTimer, 5, val);
    }
 
    public int getAudioTimer() {
        return this.legacyState.getInt(this.legacyState.stateTimer, 6);
    }
 
    public void setAudioTimer(int val) {
        this.legacyState.setInt(this.legacyState.stateTimer, 6, val);
    }
 
    public int getMiningTimer() {
        return this.legacyState.getInt(this.legacyState.stateTimer, 15);
    }
 
    public void setMiningTimer(int val) {
        this.legacyState.setInt(this.legacyState.stateTimer, 15, val);
    }
 
    public int getGuardTimer() {
        return this.legacyState.getInt(this.legacyState.stateTimer, 18);
    }
 
    public void setGuardTimer(int val) {
        this.legacyState.setInt(this.legacyState.stateTimer, 18, val);
    }
 
    public int getMountAttackCd(int index) {
        if (index < 0 || index > 3) return 0;
        return switch (16 + index) {
            case 16 -> this.syncher.getData(MOUNT_ATTACK_CD_0);
            case 17 -> this.syncher.getData(MOUNT_ATTACK_CD_1);
            case 18 -> this.syncher.getData(MOUNT_ATTACK_CD_2);
            case 19 -> this.syncher.getData(MOUNT_ATTACK_CD_3);
            default -> 0;
        };
    }
 
    public void setMountAttackCd(int index, int val) {
        if (index < 0 || index > 3) return;
        int stateTimerIndex = 16 + index;
        this.legacyState.setInt(this.legacyState.stateTimer, stateTimerIndex, val);
        boolean isClientSide = this.syncher instanceof EntityShipBase ship && ship.level().isClientSide;
        if (!isClientSide) {
            switch (stateTimerIndex) {
                case 16 -> this.syncher.setData(MOUNT_ATTACK_CD_0, val);
                case 17 -> this.syncher.setData(MOUNT_ATTACK_CD_1, val);
                case 18 -> this.syncher.setData(MOUNT_ATTACK_CD_2, val);
                case 19 -> this.syncher.setData(MOUNT_ATTACK_CD_3, val);
            }
        }
    }
}

