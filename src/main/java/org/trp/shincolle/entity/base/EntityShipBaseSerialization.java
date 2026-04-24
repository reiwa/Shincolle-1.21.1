package org.trp.shincolle.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;

final class EntityShipBaseSerialization {

    private final EntityShipBase ship;

    EntityShipBaseSerialization(EntityShipBase ship) {
        this.ship = ship;
    }

    static void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(EntityShipBase.SHIP_LEVEL, 1);
        builder.define(EntityShipBase.SHIP_EXP, 0);
        builder.define(EntityShipBase.FACE_ID, 0);
        builder.define(EntityShipBase.MOUTH_ID, 0);
        builder.define(EntityShipBase.EMOTION_PRIMARY, EntityShipBase.EMOTION_NORMAL);
        builder.define(EntityShipBase.EMOTION_SECONDARY, EntityShipBase.EMOTION_NORMAL);
        builder.define(EntityShipBase.EMOTION_PARTICLE, 0);
        builder.define(EntityShipBase.NO_FUEL, false);
        builder.define(EntityShipBase.MORALE, EntityShipBase.getMoraleDefaultValue());
        builder.define(EntityShipBase.FUEL, 0);
        builder.define(EntityShipBase.AMMO_LIGHT, 0);
        builder.define(EntityShipBase.AMMO_HEAVY, 0);
        builder.define(EntityShipBase.EQUIP_FLAGS, new CompoundTag());
        builder.define(EntityShipBase.POINTER_SELECTED, false);

        builder.define(EntityShipBase.LEGACY_EMOTION_0, 0);
        builder.define(EntityShipBase.LEGACY_EMOTION_1, 0);
        builder.define(EntityShipBase.LEGACY_EMOTION_2, 0);
        builder.define(EntityShipBase.LEGACY_EMOTION_3, 0);
        builder.define(EntityShipBase.LEGACY_EMOTION_4, 0);
        builder.define(EntityShipBase.LEGACY_EMOTION_5, 0);
        builder.define(EntityShipBase.LEGACY_EMOTION_6, 0);
        builder.define(EntityShipBase.LEGACY_EMOTION_7, 0);

        builder.define(EntityShipBase.LEGACY_ATTACK_TICK, 0);
        builder.define(EntityShipBase.LEGACY_ATTACK_TICK_2, 0);
        builder.define(EntityShipBase.LEGACY_RIDING_STATE, 0);
        builder.define(EntityShipBase.LEGACY_SCALE_LEVEL, 0);
    }

    void addAdditionalSaveData(CompoundTag compound) {
        compound.put("ShipInventory", this.ship.getInventory().serializeNBT(this.ship.registryAccess()));

        compound.putInt("ShipLevel", this.ship.getLevel());
        compound.putInt("ShipExp", this.ship.getExp());
        compound.putInt("AmmoLight", this.ship.getAmmoLight());
        compound.putInt("AmmoHeavy", this.ship.getAmmoHeavy());
        compound.putInt("EmotionPrimary", this.ship.getEmotionPrimary());
        compound.putInt("EmotionSecondary", this.ship.getEmotionSecondary());
        compound.putInt("Morale", this.ship.getMorale());
        compound.putBoolean("NoFuel", this.ship.isNoFuel());
        compound.putInt("Fuel", this.ship.getFuel());
        compound.put("EquipFlags", this.ship.copyEquipFlagsTag());
        compound.putBoolean("PointerSelected", this.ship.isPointerSelected());
        compound.putIntArray("StateEmotion", this.ship.getLegacyEmotionSnapshotInternal());
        compound.putInt("AttackTick", this.ship.getAttackTick());
        compound.putInt("AttackTick2", this.ship.getAttackTick2());
        compound.putInt("RidingState", this.ship.getRidingState());
        compound.putInt("ScaleLevel", this.ship.getScaleLevel());

        CompoundTag legacyPoint = new CompoundTag();
        legacyPoint.putByte("HP", (byte) this.ship.getLegacyShipStats().getBonus(0));
        legacyPoint.putByte("ATK", (byte) this.ship.getLegacyShipStats().getBonus(1));
        legacyPoint.putByte("DEF", (byte) this.ship.getLegacyShipStats().getBonus(2));
        legacyPoint.putByte("SPD", (byte) this.ship.getLegacyShipStats().getBonus(3));
        legacyPoint.putByte("MOV", (byte) this.ship.getLegacyShipStats().getBonus(4));
        legacyPoint.putByte("HIT", (byte) this.ship.getLegacyShipStats().getBonus(5));
        compound.put("LegacyPoint", legacyPoint);

        compound.putBoolean("LegacyStateInit", this.ship.isLegacyStateInitializedInternal());
        EntityShipLegacyState legacyState = this.ship.getLegacyStateInternal();
        compound.putIntArray("LegacyStateMinor", legacyState.stateMinor);
        compound.putIntArray("LegacyStateTimer", legacyState.stateTimer);
        compound.putByteArray("LegacyStateFlags", legacyState.toByteArray(legacyState.stateFlag));
        compound.putByteArray("LegacyUpdateFlags", legacyState.toByteArray(legacyState.updateFlag));
        compound.putByteArray("LegacyBodyHeightStand", legacyState.bodyHeightStand);
        compound.putByteArray("LegacyBodyHeightSit", legacyState.bodyHeightSit);
        compound.putIntArray("LegacyModelPos", legacyState.getModelPosBits());
        compound.putIntArray("LegacyWaypoints", legacyState.getWaypointBits());
        this.ship.savePointerToNbt(compound);
    }

    void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("ShipInventory")) {
            this.ship.getInventory().deserializeNBT(this.ship.registryAccess(), compound.getCompound("ShipInventory"));
        }

        this.ship.setLevel(compound.getInt("ShipLevel"));
        this.ship.setExp(compound.getInt("ShipExp"));
        this.ship.setAmmoLight(compound.getInt("AmmoLight"));
        this.ship.setAmmoHeavy(compound.getInt("AmmoHeavy"));
        this.ship.setEmotionPrimary(compound.getInt("EmotionPrimary"));
        this.ship.setEmotionSecondary(compound.getInt("EmotionSecondary"));

        if (compound.contains("Morale")) {
            this.ship.setMorale(compound.getInt("Morale"));
        }
        int fuel = compound.contains("Fuel") ? compound.getInt("Fuel") : 100;
        this.ship.setFuel(fuel);

        if (compound.contains("PointerSelected")) {
            this.ship.setPointerSelected(compound.getBoolean("PointerSelected"));
        } else {
            this.ship.setPointerSelected(false);
        }
        this.ship.loadPointerFromNbt(compound);

        if (compound.contains("EquipFlags")) {
            this.ship.setEquipFlagsTag(compound.getCompound("EquipFlags"));
        } else if (compound.contains("StateFlags")) {
            this.ship.migrateLegacyStateFlags(compound.getInt("StateFlags"));
        }

        if (compound.contains("StateEmotion")) {
            this.ship.applyLegacyEmotionSnapshotInternal(compound.getIntArray("StateEmotion"));
            this.ship.setLegacyStateInitializedInternal(true);
        }
        if (compound.contains("AttackTick")) {
            this.ship.setAttackTick(compound.getInt("AttackTick"));
        }
        if (compound.contains("AttackTick2")) {
            this.ship.setAttackTick2(compound.getInt("AttackTick2"));
        }
        if (compound.contains("RidingState")) {
            this.ship.setRidingState(compound.getInt("RidingState"));
        }
        if (compound.contains("ScaleLevel")) {
            this.ship.setScaleLevel(compound.getInt("ScaleLevel"));
        }

        if (compound.contains("LegacyPoint")) {
            CompoundTag point = compound.getCompound("LegacyPoint");
            this.ship.getLegacyShipStats().setBonus(0, point.getByte("HP"));
            this.ship.getLegacyShipStats().setBonus(1, point.getByte("ATK"));
            this.ship.getLegacyShipStats().setBonus(2, point.getByte("DEF"));
            this.ship.getLegacyShipStats().setBonus(3, point.getByte("SPD"));
            this.ship.getLegacyShipStats().setBonus(4, point.getByte("MOV"));
            this.ship.getLegacyShipStats().setBonus(5, point.getByte("HIT"));
        }

        if (compound.contains("LegacyStateInit")) {
            this.ship.setLegacyStateInitializedInternal(compound.getBoolean("LegacyStateInit"));
        }

        EntityShipLegacyState legacyState = this.ship.getLegacyStateInternal();
        if (compound.contains("LegacyStateMinor")) {
            legacyState.applyIntArray(legacyState.stateMinor, compound.getIntArray("LegacyStateMinor"));
        }
        if (compound.contains("LegacyStateTimer")) {
            legacyState.applyIntArray(legacyState.stateTimer, compound.getIntArray("LegacyStateTimer"));
        }
        if (compound.contains("LegacyStateFlags")) {
            legacyState.applyByteArray(legacyState.stateFlag, compound.getByteArray("LegacyStateFlags"));
        }
        if (compound.contains("LegacyUpdateFlags")) {
            legacyState.applyByteArray(legacyState.updateFlag, compound.getByteArray("LegacyUpdateFlags"));
        }
        if (compound.contains("LegacyBodyHeightStand")) {
            legacyState.applyByteArray(legacyState.bodyHeightStand, compound.getByteArray("LegacyBodyHeightStand"));
        }
        if (compound.contains("LegacyBodyHeightSit")) {
            legacyState.applyByteArray(legacyState.bodyHeightSit, compound.getByteArray("LegacyBodyHeightSit"));
        }
        if (compound.contains("LegacyModelPos")) {
            legacyState.applyModelPosBits(compound.getIntArray("LegacyModelPos"));
        }
        if (compound.contains("LegacyWaypoints")) {
            legacyState.applyWaypointBits(compound.getIntArray("LegacyWaypoints"));
        }

        this.ship.refreshDimensions();

        if (compound.getBoolean(EntityShipBase.getSpawnEggTagName())) {
            this.ship.resetDeathStateForSpawnEgg();
        }

        if (!this.ship.isLegacyStateInitializedInternal()) {
            this.ship.initializeLegacyStateInternal();
        }

        this.ship.recalculateLegacyShipStats();
    }
}
