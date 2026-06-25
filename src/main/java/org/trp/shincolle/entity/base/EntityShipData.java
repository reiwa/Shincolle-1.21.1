package org.trp.shincolle.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

public final class EntityShipData {

    private EntityShipData() {
    }

    public static final EntityDataAccessor<Integer> SHIP_LEVEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> SHIP_EXP =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> FACE_ID =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Boolean> POINTER_SELECTED =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.BOOLEAN
        );

    public static final EntityDataAccessor<Integer> MOUTH_ID =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> EMOTION_PRIMARY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> EMOTION_SECONDARY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> EMOTION_PARTICLE =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Boolean> NO_FUEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.BOOLEAN
        );

    public static final EntityDataAccessor<Integer> MORALE =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> FORMATION_TEAM =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> FORMATION_SLOT =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> FUEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> AMMO_LIGHT =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> AMMO_HEAVY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> AIRCRAFT_LIGHT =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> AIRCRAFT_HEAVY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<CompoundTag> EQUIP_FLAGS =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.COMPOUND_TAG
        );

    public static final String EQUIP_MOUNT = "equip_mount";

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_0 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_1 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_2 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_3 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_4 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_5 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_6 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_EMOTION_7 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_ATTACK_TICK =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_ATTACK_TICK_2 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_RIDING_STATE =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> LEGACY_SCALE_LEVEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_0 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_1 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_2 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_3 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    public static final EntityDataAccessor<CompoundTag> POINTER_TARGET_DATA =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.COMPOUND_TAG
        );
}
