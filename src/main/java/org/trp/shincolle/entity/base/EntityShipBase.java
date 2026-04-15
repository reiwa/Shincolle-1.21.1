package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.EntityShipGrudge;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.menu.ShipContainerMenu;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class EntityShipBase extends TamableAnimal {

    public static final int EMOTION_NORMAL = 0;
    public static final int EMOTION_BLINK = 1;
    public static final int EMOTION_CRY = 2;
    public static final int EMOTION_SCORN = 3;
    public static final int EMOTION_BORED = 4;
    public static final int EMOTION_HUNGRY = 5;
    public static final int EMOTION_ANGRY = 6;
    public static final int EMOTION_SHY = 7;
    public static final int EMOTION_HAPPY = 8;
    public static final int EMOTION_DEBUG = 9;

    public static final int FACE_ID_MIN = 0;
    public static final int FACE_ID_MAX = 9;
    public static final int MOUTH_ID_MIN = 0;
    public static final int MOUTH_ID_MAX = 5;

    public static final int FACE_EYES_OPEN = 0;
    public static final int FACE_EYES_CLOSED = 1;
    public static final int FACE_EYES_HALF = 2;
    public static final int FACE_TENSION = 3;
    public static final int FACE_DESPAIR = 4;
    public static final int FACE_DOT_EYES = 5;
    public static final int FACE_DOT_EYES_TEAR = 6;
    public static final int FACE_CRY = 7;
    public static final int FACE_WINK = 8;
    public static final int FACE_SOFT = 9;

    private static final int FUEL_DECAY_INTERVAL_TICKS = 200;
    private static final int FUEL_DECAY_AMOUNT = 1;
    private static final int MORALE_MAX = 16000;
    private static final int MORALE_DEFAULT = 4000;
    private static final float AUTO_HEAL_THRESHOLD_RATIO = 0.9F;
    private static final float AUTO_HEAL_FAST_RATIO = 0.08F;
    private static final float AUTO_HEAL_FAST_FLAT = 15.0F;
    private static final float AUTO_HEAL_SLOW_RATIO = 0.03F;
    private static final float AUTO_HEAL_SLOW_FLAT = 1.0F;
    private static final float WATER_SLOWDOWN_FACTOR = 0.98F;
    private static final int SHIP_DEATH_MAX_TICKS = 300;
    private static final String TAG_SPAWN_EGG = "ShincolleSpawnEgg";

    public static final int STATE_MINOR_FACTION_ID = 19;
    public static final int STATE_MINOR_SHIP_CLASS = 20;
    public static final int STATE_MINOR_SPECIAL_EQUIP = 25;
    public static final int STATE_MINOR_RARITY = 13;

    public static final int STATE_FLAG_MARRIED = 1;
    public static final int STATE_FLAG_NO_EQUIP = 2;
    public static final int STATE_FLAG_CAN_MELEE = 3;
    public static final int STATE_FLAG_LIGHT_ATTACK = 4;
    public static final int STATE_FLAG_HEAVY_ATTACK = 5;
    public static final int STATE_FLAG_LIGHT_AIRCRAFT_ATTACK = 6;
    public static final int STATE_FLAG_HEAVY_AIRCRAFT_ATTACK = 7;
    public static final int STATE_FLAG_RING_EFFECT = 9;
    public static final int STATE_FLAG_GUI_BTN_1 = 13;
    public static final int STATE_FLAG_GUI_BTN_2 = 14;
    public static final int STATE_FLAG_GUI_BTN_3 = 15;
    public static final int STATE_FLAG_GUI_BTN_4 = 16;
    public static final int STATE_FLAG_ANTI_AIR = 19;
    public static final int STATE_FLAG_CAN_RIDE = 24;
    public static final int STATE_FLAG_APPEARANCE = 25;

    private static final int LEGACY_STATE_MINOR_SIZE = 46;
    private static final int LEGACY_STATE_TIMER_SIZE = 21;
    private static final int LEGACY_STATE_FLAG_SIZE = 28;
    private static final int LEGACY_UPDATE_FLAG_SIZE = 8;
    private static final int[] LEGACY_STATE_MINOR_DEFAULTS = {
            1, 0, 0, 40, 0, 0, 0, 0, 0, 3, 3, 12, 35, 1, -1, -1, -1, 0, -1, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0, 0, 60, 0, 10, 0, 0, -1, 0, 0, 0, 0, -1, -1, -1, 0, 0, -1
    };
    private static final boolean[] LEGACY_STATE_FLAG_DEFAULTS = {
            false, false, false, false, true, true, true, true, false, true, true, false, true, true, true, true, true, true, true, false, false, false, true, true, false, true, false, false
    };
    private static final byte[] LEGACY_BODY_HEIGHT_STAND_DEFAULTS = {92, 78, 73, 58, 47, 37};
    private static final byte[] LEGACY_BODY_HEIGHT_SIT_DEFAULTS = {64, 49, 44, 29, 23, 12};
    private static final float[] LEGACY_MODEL_POS_DEFAULTS = {0.0f, 0.0f, 0.0f, 50.0f};

    protected static final int MOUTH_FRONT_0 = 0;
    protected static final int MOUTH_FRONT_1 = 1;
    protected static final int MOUTH_FRONT_2 = 2;
    protected static final int MOUTH_FLIP_0 = 3;
    protected static final int MOUTH_FLIP_1 = 4;
    protected static final int MOUTH_FLIP_2 = 5;

    protected static final int EMOTION_TICK_MASK_8BIT = 0xFF;
    protected static final int EMOTION_TICK_MASK_9BIT = 0x1FF;

    protected static final int LEGACY_STATE_EMOTION_COUNT = 8;
    protected static final int LEGACY_ATTACK_TICK_MAX = 100;

    protected static final EntityDataAccessor<Integer> SHIP_LEVEL = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> SHIP_EXP = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);

    protected static final EntityDataAccessor<Integer> FACE_ID = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Boolean> POINTER_SELECTED = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Integer> MOUTH_ID = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);

    protected static final EntityDataAccessor<Integer> EMOTION_PRIMARY = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> EMOTION_SECONDARY = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> EMOTION_PARTICLE = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Boolean> NO_FUEL = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Integer> MORALE = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);

    protected static final EntityDataAccessor<Integer> FUEL = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);

    protected static final EntityDataAccessor<Integer> AMMO_LIGHT = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> AMMO_HEAVY = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);

    protected static final EntityDataAccessor<CompoundTag> EQUIP_FLAGS = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.COMPOUND_TAG);

    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_0 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_1 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_2 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_3 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_4 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_5 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_6 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_7 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);

    protected static final EntityDataAccessor<Integer> LEGACY_ATTACK_TICK = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_ATTACK_TICK_2 = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_RIDING_STATE = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEGACY_SCALE_LEVEL = SynchedEntityData.defineId(EntityShipBase.class, EntityDataSerializers.INT);

    protected final ShipInventoryHandler inventory;
    private final EntityShipBaseCombat combat;
    private final EntityShipBasePointer pointer;
    private final EntityShipBaseEmotions emotions;
    private final LegacyShipStats legacyShipStats;
    private final LegacyShipState legacyState;
    private boolean legacyStateInitialized = false;
    private double smoothedForce = 0.0D;
    private int shipDeathTicks = 0;
    private int emotesTick = 0;
    private int emotionParticleSeq = 0;
    private int stateUpdateTimer;
    private int customHurtTime;
    private int hurtSoundCooldown;
    private int feedSoundCooldown;

    protected EntityShipBase(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.inventory = new ShipInventoryHandler(this, 60);
        this.combat = new EntityShipBaseCombat(this);
        this.pointer = new EntityShipBasePointer(this);
        this.emotions = new EntityShipBaseEmotions(this);
        this.legacyShipStats = new LegacyShipStats();
        this.legacyState = new LegacyShipState();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SHIP_LEVEL, 1);
        builder.define(SHIP_EXP, 0);
        builder.define(FACE_ID, 0);
        builder.define(MOUTH_ID, 0);
        builder.define(EMOTION_PRIMARY, EMOTION_NORMAL);
        builder.define(EMOTION_SECONDARY, EMOTION_NORMAL);
        builder.define(EMOTION_PARTICLE, 0);
        builder.define(NO_FUEL, false);
        builder.define(MORALE, MORALE_DEFAULT);
        builder.define(FUEL, 0);
        builder.define(AMMO_LIGHT, 0);
        builder.define(AMMO_HEAVY, 0);
        builder.define(EQUIP_FLAGS, new CompoundTag());
        builder.define(POINTER_SELECTED, false);

        builder.define(LEGACY_EMOTION_0, 0);
        builder.define(LEGACY_EMOTION_1, 0);
        builder.define(LEGACY_EMOTION_2, 0);
        builder.define(LEGACY_EMOTION_3, 0);
        builder.define(LEGACY_EMOTION_4, 0);
        builder.define(LEGACY_EMOTION_5, 0);
        builder.define(LEGACY_EMOTION_6, 0);
        builder.define(LEGACY_EMOTION_7, 0);

        builder.define(LEGACY_ATTACK_TICK, 0);
        builder.define(LEGACY_ATTACK_TICK_2, 0);
        builder.define(LEGACY_RIDING_STATE, 0);
        builder.define(LEGACY_SCALE_LEVEL, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.put("ShipInventory", this.inventory.serializeNBT(this.registryAccess()));

        compound.putInt("ShipLevel", this.getLevel());
        compound.putInt("ShipExp", this.getExp());
        compound.putInt("AmmoLight", this.getAmmoLight());
        compound.putInt("AmmoHeavy", this.getAmmoHeavy());
        compound.putInt("EmotionPrimary", this.getEmotionPrimary());
        compound.putInt("EmotionSecondary", this.getEmotionSecondary());
        compound.putInt("Morale", this.getMorale());
        compound.putBoolean("NoFuel", this.isNoFuel());
        compound.putInt("Fuel", this.getFuel());
        compound.put("EquipFlags", this.entityData.get(EQUIP_FLAGS).copy());
        compound.putBoolean("PointerSelected", this.isPointerSelected());
        compound.putIntArray("StateEmotion", getLegacyEmotionSnapshot());
        compound.putInt("AttackTick", this.getAttackTick());
        compound.putInt("AttackTick2", this.getAttackTick2());
        compound.putInt("RidingState", this.getRidingState());
        compound.putInt("ScaleLevel", this.getScaleLevel());
        CompoundTag legacyPoint = new CompoundTag();
        legacyPoint.putByte("HP", (byte) this.legacyShipStats.getBonus(0));
        legacyPoint.putByte("ATK", (byte) this.legacyShipStats.getBonus(1));
        legacyPoint.putByte("DEF", (byte) this.legacyShipStats.getBonus(2));
        legacyPoint.putByte("SPD", (byte) this.legacyShipStats.getBonus(3));
        legacyPoint.putByte("MOV", (byte) this.legacyShipStats.getBonus(4));
        legacyPoint.putByte("HIT", (byte) this.legacyShipStats.getBonus(5));
        compound.put("LegacyPoint", legacyPoint);
        compound.putBoolean("LegacyStateInit", this.legacyStateInitialized);
        compound.putIntArray("LegacyStateMinor", legacyState.stateMinor);
        compound.putIntArray("LegacyStateTimer", legacyState.stateTimer);
        compound.putByteArray("LegacyStateFlags", legacyState.toByteArray(legacyState.stateFlag));
        compound.putByteArray("LegacyUpdateFlags", legacyState.toByteArray(legacyState.updateFlag));
        compound.putByteArray("LegacyBodyHeightStand", legacyState.bodyHeightStand);
        compound.putByteArray("LegacyBodyHeightSit", legacyState.bodyHeightSit);
        compound.putIntArray("LegacyModelPos", legacyState.getModelPosBits());
        compound.putIntArray("LegacyWaypoints", legacyState.getWaypointBits());
        this.pointer.saveToNbt(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("ShipInventory")) {
            this.inventory.deserializeNBT(this.registryAccess(), compound.getCompound("ShipInventory"));
        }

        this.setLevel(compound.getInt("ShipLevel"));
        this.setExp(compound.getInt("ShipExp"));
        this.setAmmoLight(compound.getInt("AmmoLight"));
        this.setAmmoHeavy(compound.getInt("AmmoHeavy"));
        this.setEmotionPrimary(compound.getInt("EmotionPrimary"));
        this.setEmotionSecondary(compound.getInt("EmotionSecondary"));
        if (compound.contains("Morale")) {
            this.setMorale(compound.getInt("Morale"));
        }
        int fuel = compound.contains("Fuel") ? compound.getInt("Fuel") : 100;
        this.setFuel(fuel);
        if (compound.contains("PointerSelected")) {
            this.setPointerSelected(compound.getBoolean("PointerSelected"));
        } else {
            this.setPointerSelected(false);
        }
        this.pointer.loadFromNbt(compound);
        if (compound.contains("EquipFlags")) {
            this.entityData.set(EQUIP_FLAGS, compound.getCompound("EquipFlags"));
        } else if (compound.contains("StateFlags")) {
            this.migrateLegacyStateFlags(compound.getInt("StateFlags"));
        }

        if (compound.contains("StateEmotion")) {
            int[] legacy = compound.getIntArray("StateEmotion");
            applyLegacyEmotionSnapshot(legacy);
            this.legacyStateInitialized = true;
        }
        if (compound.contains("AttackTick")) {
            this.setAttackTick(compound.getInt("AttackTick"));
        }
        if (compound.contains("AttackTick2")) {
            this.setAttackTick2(compound.getInt("AttackTick2"));
        }
        if (compound.contains("RidingState")) {
            this.setRidingState(compound.getInt("RidingState"));
        }
        if (compound.contains("ScaleLevel")) {
            this.setScaleLevel(compound.getInt("ScaleLevel"));
        }
        if (compound.contains("LegacyPoint")) {
            CompoundTag point = compound.getCompound("LegacyPoint");
            this.legacyShipStats.setBonus(0, point.getByte("HP"));
            this.legacyShipStats.setBonus(1, point.getByte("ATK"));
            this.legacyShipStats.setBonus(2, point.getByte("DEF"));
            this.legacyShipStats.setBonus(3, point.getByte("SPD"));
            this.legacyShipStats.setBonus(4, point.getByte("MOV"));
            this.legacyShipStats.setBonus(5, point.getByte("HIT"));
        }
        if (compound.contains("LegacyStateInit")) {
            this.legacyStateInitialized = compound.getBoolean("LegacyStateInit");
        }
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

        if (compound.getBoolean(TAG_SPAWN_EGG)) {
            this.setHealth(this.getMaxHealth());
            this.deathTime = 0;
            this.shipDeathTicks = 0;
        }

        if (!this.legacyStateInitialized) {
            initializeLegacyState();
        }

        this.recalculateLegacyShipStats();
    }

    public int getLevel() {
        return this.entityData.get(SHIP_LEVEL);
    }

    public void setLevel(int level) {
        this.entityData.set(SHIP_LEVEL, level);
        this.recalculateLegacyShipStats();
    }

    public int getExp() {
        return this.entityData.get(SHIP_EXP);
    }

    public void setExp(int exp) {
        this.entityData.set(SHIP_EXP, exp);
    }

    public int getAmmoLight() {
        return this.entityData.get(AMMO_LIGHT);
    }

    public void setAmmoLight(int val) {
        this.entityData.set(AMMO_LIGHT, val);
    }

    public int getAmmoHeavy() {
        return this.entityData.get(AMMO_HEAVY);
    }

    public void setAmmoHeavy(int val) {
        this.entityData.set(AMMO_HEAVY, val);
    }

    public int getNumAircraftLight() {
        return this.getStateMinor(7);
    }

    public int getNumAircraftHeavy() {
        return this.getStateMinor(8);
    }

    public boolean hasAirLight() {
        return this.getNumAircraftLight() > 0;
    }

    public boolean hasAirHeavy() {
        return this.getNumAircraftHeavy() > 0;
    }

    public void setNumAircraftLight(int count) {
        this.setStateMinor(7, Math.max(0, count));
    }

    public void setNumAircraftHeavy(int count) {
        this.setStateMinor(8, Math.max(0, count));
    }

    public boolean isPointerSelected() {
        return this.entityData.get(POINTER_SELECTED);
    }

    public void setPointerSelected(boolean selected) {
        this.entityData.set(POINTER_SELECTED, selected);
    }

    public void togglePointerSelected() {
        this.setPointerSelected(!this.isPointerSelected());
    }

    public void setPointerTarget(Vec3 target, long durationTicks) {
        this.pointer.setPointerTarget(target, durationTicks);
    }

    public boolean hasPointerTarget() {
        return this.pointer.hasPointerTarget();
    }

    public Vec3 getPointerTarget() {
        return this.pointer.getPointerTarget();
    }

    public long getPointerTargetRemainingTicks() {
        return this.pointer.getPointerTargetRemainingTicks();
    }

    public void clearPointerTarget() {
        this.pointer.clearPointerTarget();
    }

    public void setPointerTargetEntity(Entity target, long durationTicks) {
        this.pointer.setPointerTargetEntity(target, durationTicks);
    }

    public boolean hasPointerTargetEntity() {
        return this.pointer.hasPointerTargetEntity();
    }

    public Entity getPointerTargetEntity() {
        return this.pointer.getPointerTargetEntity();
    }

    public long getPointerTargetEntityRemainingTicks() {
        return this.pointer.getPointerTargetEntityRemainingTicks();
    }

    public void clearPointerTargetEntity() {
        this.pointer.clearPointerTargetEntity();
    }

    public int getFaceId() {
        return this.entityData.get(FACE_ID);
    }

    public void setFaceId(int id) {
        this.entityData.set(FACE_ID, Mth.clamp(id, FACE_ID_MIN, FACE_ID_MAX));
    }

    public int getMouthId() {
        return this.entityData.get(MOUTH_ID);
    }

    public void setMouthId(int id) {
        this.entityData.set(MOUTH_ID, Mth.clamp(id, MOUTH_ID_MIN, MOUTH_ID_MAX));
    }

    public int getEmotionPrimary() {
        return this.entityData.get(EMOTION_PRIMARY);
    }

    public void setEmotionPrimary(int val) {
        this.entityData.set(EMOTION_PRIMARY, val);
        this.setStateEmotion(1, val, false);
    }

    public int getEmotionSecondary() {
        return this.entityData.get(EMOTION_SECONDARY);
    }

    public void setEmotionSecondary(int val) {
        this.entityData.set(EMOTION_SECONDARY, val);
        this.setStateEmotion(7, val, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (EMOTION_PARTICLE.equals(key) && this.level().isClientSide) {
            int packed = this.entityData.get(EMOTION_PARTICLE);
            int typeId = packed & 0xFF;
            spawnEmotionParticleClient(EmotionParticleType.fromId(typeId));
        }
    }

    public int getMorale() {
        return this.entityData.get(MORALE);
    }

    public void setMorale(int val) {
        this.entityData.set(MORALE, Mth.clamp(val, 0, MORALE_MAX));
    }

    public void addMorale(int delta) {
        this.setMorale(this.getMorale() + delta);
    }

    public boolean isNoFuel() {
        return this.getFuel() <= 0;
    }

    public void setNoFuel(boolean val) {
        boolean wasNoFuel = this.isNoFuel();
        this.entityData.set(NO_FUEL, val);
        if (val) {
            this.entityData.set(FUEL, 0);
        }
        boolean isNoFuelNow = this.isNoFuel();
        if (wasNoFuel != isNoFuelNow) {
            this.updateFuelState(isNoFuelNow);
        }
    }

    protected void updateFuelState(boolean nofuel) {
    }

    public boolean isInDeadPose() {
        return this.isDeadOrDying() || this.getHealth() <= 0.0F || this.isNoFuel();
    }

    public int getStateEmotion(int index) {
        return switch (index) {
            case 0 -> this.entityData.get(LEGACY_EMOTION_0);
            case 1 -> this.entityData.get(LEGACY_EMOTION_1);
            case 2 -> this.entityData.get(LEGACY_EMOTION_2);
            case 3 -> this.entityData.get(LEGACY_EMOTION_3);
            case 4 -> this.entityData.get(LEGACY_EMOTION_4);
            case 5 -> this.entityData.get(LEGACY_EMOTION_5);
            case 6 -> this.entityData.get(LEGACY_EMOTION_6);
            case 7 -> this.entityData.get(LEGACY_EMOTION_7);
            default -> 0;
        };
    }

    public void setStateEmotion(int index, int value, boolean sync) {
        switch (index) {
            case 0 -> this.entityData.set(LEGACY_EMOTION_0, value);
            case 1 -> this.entityData.set(LEGACY_EMOTION_1, value);
            case 2 -> this.entityData.set(LEGACY_EMOTION_2, value);
            case 3 -> this.entityData.set(LEGACY_EMOTION_3, value);
            case 4 -> this.entityData.set(LEGACY_EMOTION_4, value);
            case 5 -> this.entityData.set(LEGACY_EMOTION_5, value);
            case 6 -> this.entityData.set(LEGACY_EMOTION_6, value);
            case 7 -> this.entityData.set(LEGACY_EMOTION_7, value);
            default -> {
            }
        }
    }

    public int getAttackTick() {
        return this.entityData.get(LEGACY_ATTACK_TICK);
    }

    public void setAttackTick(int value) {
        this.entityData.set(LEGACY_ATTACK_TICK, Mth.clamp(value, 0, LEGACY_ATTACK_TICK_MAX));
    }

    public int getAttackTick2() {
        return this.entityData.get(LEGACY_ATTACK_TICK_2);
    }

    public void setAttackTick2(int value) {
        this.entityData.set(LEGACY_ATTACK_TICK_2, Math.max(0, value));
    }

    public float getSwingTime(float partialTick) {
        return this.getAttackAnim(partialTick);
    }

    public boolean getIsSitting() {
        return this.isOrderedToSit() || this.isInSittingPose();
    }

    public boolean getIsSprinting() {
        return this.isSprinting() || this.walkAnimation.speed() > 0.9F;
    }

    protected static boolean checkModelState(int id, int state) {
        if (id < 0 || id >= 31) {
            return false;
        }
        return (state & (1 << id)) != 0;
    }

    protected static float[] rotateXZByAxis(float z, float x, float radians, float scale) {
        float cosD = Mth.cos(radians);
        float sinD = Mth.sin(radians);
        float[] newPos = new float[]{0.0f, 0.0f};
        newPos[0] = z * cosD + x * sinD;
        newPos[1] = x * cosD - z * sinD;
        newPos[0] = newPos[0] * scale;
        newPos[1] = newPos[1] * scale;
        return newPos;
    }

    protected Player getOwnerPlayer() {
        LivingEntity owner = this.getOwner();
        return owner instanceof Player player ? player : null;
    }

    protected boolean consumeLightAmmo(int amount) {
        return this.combat.consumeLightAmmo(amount);
    }

    protected boolean consumeHeavyAmmo(int amount) {
        return this.combat.consumeHeavyAmmo(amount);
    }

    public boolean supportsAircraftCombat() {
        return false;
    }

    public EntityType<? extends TamableAnimal> getAttackAircraftType(boolean isLightAircraft) {
        return null;
    }

    public double getAircraftLaunchHeight() {
        return this.getBbHeight() * 0.65D;
    }

    public float getAircraftLightLevelBonus() {
        return 0.0F;
    }

    public float getAircraftHeavyLevelBonus() {
        return 0.0F;
    }

    protected void performLightAttack(Entity target) {
        this.combat.performLightAttack(target);
    }

    protected boolean performHeavyAttack(Entity target) {
        return this.combat.performHeavyAttack(target);
    }

    public int getStateMinor(int index) {
        return legacyState.getInt(legacyState.stateMinor, index);
    }

    public void setStateMinor(int index, int value) {
        legacyState.setInt(legacyState.stateMinor, index, value);
        if (index == STATE_MINOR_SHIP_CLASS) {
            this.recalculateLegacyShipStats();
        }
    }

    public int getStateTimer(int index) {
        return legacyState.getInt(legacyState.stateTimer, index);
    }

    public void setStateTimer(int index, int value) {
        legacyState.setInt(legacyState.stateTimer, index, value);
    }

    public boolean getStateFlag(int index) {
        return legacyState.getBoolean(legacyState.stateFlag, index);
    }

    public byte getStateFlagI(int index) {
        return legacyState.getBoolean(legacyState.stateFlag, index) ? (byte) 1 : (byte) 0;
    }

    public void setStateFlag(int index, boolean value) {
        legacyState.setBoolean(legacyState.stateFlag, index, value);
    }

    public void setStateFlagI(int index, int value) {
        legacyState.setBoolean(legacyState.stateFlag, index, value > 0);
    }

    public boolean isStateMarried() { return getStateFlag(STATE_FLAG_MARRIED); }
    public void setStateMarried(boolean value) { setStateFlag(STATE_FLAG_MARRIED, value); }
    
    public boolean isStateNoEquip() { return getStateFlag(STATE_FLAG_NO_EQUIP); }
    public void setStateNoEquip(boolean value) { setStateFlag(STATE_FLAG_NO_EQUIP, value); }
    
    public boolean isStateCanMelee() { return getStateFlag(STATE_FLAG_CAN_MELEE); }
    public void setStateCanMelee(boolean value) { setStateFlag(STATE_FLAG_CAN_MELEE, value); }
    
    public boolean isStateLightAttack() { return getStateFlag(STATE_FLAG_LIGHT_ATTACK); }
    public void setStateLightAttack(boolean value) { setStateFlag(STATE_FLAG_LIGHT_ATTACK, value); }
    
    public boolean isStateHeavyAttack() { return getStateFlag(STATE_FLAG_HEAVY_ATTACK); }
    public void setStateHeavyAttack(boolean value) { setStateFlag(STATE_FLAG_HEAVY_ATTACK, value); }
    
    public boolean isStateLightAircraftAttack() { return getStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK); }
    public void setStateLightAircraftAttack(boolean value) { setStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK, value); }
    
    public boolean isStateHeavyAircraftAttack() { return getStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK); }
    public void setStateHeavyAircraftAttack(boolean value) { setStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK, value); }
    
    public boolean isStateRingEffect() { return getStateFlag(STATE_FLAG_RING_EFFECT); }
    public void setStateRingEffect(boolean value) { setStateFlag(STATE_FLAG_RING_EFFECT, value); }
    
    public boolean isStateGuiBtn1() { return getStateFlag(STATE_FLAG_GUI_BTN_1); }
    public void setStateGuiBtn1(boolean value) { setStateFlag(STATE_FLAG_GUI_BTN_1, value); }
    
    public boolean isStateGuiBtn2() { return getStateFlag(STATE_FLAG_GUI_BTN_2); }
    public void setStateGuiBtn2(boolean value) { setStateFlag(STATE_FLAG_GUI_BTN_2, value); }
    
    public boolean isStateGuiBtn3() { return getStateFlag(STATE_FLAG_GUI_BTN_3); }
    public void setStateGuiBtn3(boolean value) { setStateFlag(STATE_FLAG_GUI_BTN_3, value); }
    
    public boolean isStateGuiBtn4() { return getStateFlag(STATE_FLAG_GUI_BTN_4); }
    public void setStateGuiBtn4(boolean value) { setStateFlag(STATE_FLAG_GUI_BTN_4, value); }
    
    public boolean isStateAntiAir() { return getStateFlag(STATE_FLAG_ANTI_AIR); }
    public void setStateAntiAir(boolean value) { setStateFlag(STATE_FLAG_ANTI_AIR, value); }
    
    public boolean isStateCanRide() { return getStateFlag(STATE_FLAG_CAN_RIDE); }
    public void setStateCanRide(boolean value) { setStateFlag(STATE_FLAG_CAN_RIDE, value); }
    
    public boolean isStateAppearance() { return getStateFlag(STATE_FLAG_APPEARANCE); }
    public void setStateAppearance(boolean value) { setStateFlag(STATE_FLAG_APPEARANCE, value); }

    public boolean getUpdateFlag(int index) {
        return legacyState.getBoolean(legacyState.updateFlag, index);
    }

    public void setUpdateFlag(int index, boolean value) {
        legacyState.setBoolean(legacyState.updateFlag, index, value);
    }

    public byte[] getBodyHeightStand() {
        return legacyState.bodyHeightStand;
    }

    public byte[] getBodyHeightSit() {
        return legacyState.bodyHeightSit;
    }

    public float[] getModelPos() {
        return legacyState.modelPos;
    }

    public void setModelPos(float[] pos) {
        legacyState.applyModelPos(pos);
    }

    public BlockPos[] getWaypoints() {
        return legacyState.waypoints;
    }

    public void setWaypoints(BlockPos[] points) {
        legacyState.applyWaypoints(points);
    }

    public int getRidingState() {
        return this.entityData.get(LEGACY_RIDING_STATE);
    }

    public void setRidingState(int state) {
        this.entityData.set(LEGACY_RIDING_STATE, Math.max(0, state));
    }

    public int getScaleLevel() {
        return this.entityData.get(LEGACY_SCALE_LEVEL);
    }

    public void setScaleLevel(int level) {
        this.entityData.set(LEGACY_SCALE_LEVEL, Math.max(0, level));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }


    private static final float SHIP_SOUND_VOLUME = 0.6F;
    private static final int AMBIENT_SOUND_MIN_INTERVAL_TICKS = 80;
    private static final int AMBIENT_SOUND_MAX_PER_TICK = 3;
    private static final ConcurrentMap<Long, Integer> AMBIENT_SOUNDS_PER_TICK = new ConcurrentHashMap<>();

    @Override
    protected float getSoundVolume() {
        return SHIP_SOUND_VOLUME;
    }

    protected float getShipSoundPitch() {
        return this.getRandom().nextFloat() * 0.12F + 0.98F;
    }

    private boolean tryAcquireAmbientSoundSlot() {
        if (this.level() == null) {
            return false;
        }

        long gameTime = this.level().getGameTime();
        int count = AMBIENT_SOUNDS_PER_TICK.merge(gameTime, 1, Integer::sum);

        if (count == 1) {
            AMBIENT_SOUNDS_PER_TICK.keySet().removeIf(tick -> tick < gameTime - 1L);
        }

        if (count > AMBIENT_SOUND_MAX_PER_TICK) {
            AMBIENT_SOUNDS_PER_TICK.computeIfPresent(gameTime, (tick, value) -> Math.max(0, value - 1));
            return false;
        }

        return true;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.SHIP_IDLE.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.SHIP_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SHIP_DEATH.get();
    }

    @Override
    public void playAmbientSound() {
        if ((this.tickCount % AMBIENT_SOUND_MIN_INTERVAL_TICKS) != 0) {
            return;
        }
        if (this.isNoFuel() || this.getRandom().nextInt(10) > 3) {
            return;
        }
        if (!tryAcquireAmbientSoundSlot()) {
            return;
        }
        SoundEvent sound;
        if (this.getStateFlag(1) && this.getRandom().nextInt(5) == 0) {
            sound = ModSounds.SHIP_MARRY.get();
        } else {
            sound = this.getAmbientSound();
        }
        if (sound != null) {
            this.playSound(sound, this.getSoundVolume(), this.getShipSoundPitch());
        }
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        if (this.hurtSoundCooldown <= 0) {
            this.hurtSoundCooldown = 20 + this.getRandom().nextInt(30);
            super.playHurtSound(source);
        }
    }

    @Override
    public boolean isOnFire() {
        if (super.isOnFire()) {
            return true;
        }
        return (this.getHealth() / this.getMaxHealth()) <= 0.25F;
    }

    public int getFuel() {
        return this.entityData.get(FUEL);
    }

    public void setFuel(int val) {
        int newFuel = Math.max(0, val);
        boolean wasNoFuel = this.isNoFuel();
        this.entityData.set(FUEL, newFuel);
        this.entityData.set(NO_FUEL, newFuel == 0);
        boolean isNoFuelNow = newFuel == 0;
        if (wasNoFuel != isNoFuelNow) {
            this.updateFuelState(isNoFuelNow);
        }
    }

    public boolean getEquipFlag(String key) {
        return this.entityData.get(EQUIP_FLAGS).getBoolean(key);
    }

    public void setEquipFlag(String key, boolean value) {
        CompoundTag tag = this.entityData.get(EQUIP_FLAGS).copy();
        tag.putBoolean(key, value);
        this.entityData.set(EQUIP_FLAGS, tag);
    }

    public List<EquipOption> getEquipOptions() {
        return Collections.emptyList();
    }

    protected abstract Item getShipSpawnEggItem();

    public ShipInventoryHandler getInventory() {
        return this.inventory;
    }

    EntityShipBaseCombat getCombat() {
        return this.combat;
    }

    public void returnAircraftToDeck(boolean lightAircraft) {
        this.combat.returnAircraftToDeck(lightAircraft);
    }

    public LegacyShipStats getLegacyShipStats() {
        return this.legacyShipStats;
    }

    public int getAttrBonus(int index) {
        return this.legacyShipStats.getBonus(index);
    }

    public void setAttrBonus(int index, int value) {
        this.legacyShipStats.setBonus(index, value);
        this.recalculateLegacyShipStats();
    }

    public void resetInteractionEmotionState() {
        this.emotions.resetFaceTick();
        if (this.getEmotionPrimary() == EMOTION_BORED) {
            this.setEmotionPrimary(EMOTION_NORMAL);
        }
        if (this.getEmotionSecondary() == EMOTION_BORED) {
            this.setEmotionSecondary(EMOTION_NORMAL);
        }
    }

    public void focusOnPlayer(Player player) {
        if (player == null) {
            return;
        }
        this.getLookControl().setLookAt(player, 30.0F, 30.0F);
    }

    public float getHeadTiltAngle(float ageInTicks) {
        return this.emotions.getHeadTiltAngle(ageInTicks);
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    @Override
    protected float getWaterSlowDown() {
        return WATER_SLOWDOWN_FACTOR;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isAlive()) {
            if (this.isInWater() && !this.isPassenger()) {
                double acceleration = getAcceleration();

                this.smoothedForce += acceleration;

                double motionY = Mth.clamp(this.smoothedForce, -0.18D, 0.18D);

                Vec3 delta = this.getDeltaMovement();
                this.setDeltaMovement(delta.x, motionY, delta.z);
                this.fallDistance = 0.0F;
            }

            applyShoreClimbAssist();

            if (!this.level().isClientSide) {
                this.tickAliveLogic();
            }
        }
    }

    protected void tickAliveLogic() {
        if (!this.isNoFuel()) {
            this.pointer.tickPointerTargetEntity();
            this.emotions.tickEmotions();
            this.combat.tickAircraftRecovery();
            tickEmotes();
            if ((this.tickCount & 0xFF) == 0) {
                applyEmotesReaction(4);
            }
        }
        tickFuelDecay();
        tickAutoRecovery();
        tickLegacyTimers();
        if ((this.tickCount % 40) == 0) {
            this.recalculateLegacyShipStats();
        }
    }

    @Override
    protected void tickDeath() {
        this.shipDeathTicks++;
        if (!this.level().isClientSide && this.shipDeathTicks == SHIP_DEATH_MAX_TICKS) {
            spawnShipGrudge();
        }
        if (this.shipDeathTicks >= SHIP_DEATH_MAX_TICKS) {
            this.discard();
        }
        this.deathTime = 0;
    }

    private void spawnShipGrudge() {
        ItemStack spawnEgg = createShipSpawnEggStack();
        EntityShipGrudge grudge = new EntityShipGrudge(this.level(), this.getX(), this.getY() + 0.5D,
                this.getZ(), spawnEgg, this.getOwnerUUID());
        this.level().addFreshEntity(grudge);
    }

    private ItemStack createShipSpawnEggStack() {
        ItemStack egg = new ItemStack(getShipSpawnEggItem());
        CompoundTag shipTag = new CompoundTag();
        this.addAdditionalSaveData(shipTag);
        shipTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(this.getType()).toString());
        shipTag.putBoolean(TAG_SPAWN_EGG, true);
        shipTag.putFloat("Health", this.getMaxHealth());
        shipTag.putShort("DeathTime", (short) 0);
        shipTag.putShort("HurtTime", (short) 0);
        egg.set(DataComponents.ENTITY_DATA, CustomData.of(shipTag));
        egg.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
        return egg;
    }

    private void tickFuelDecay() {
        if (this.tickCount % FUEL_DECAY_INTERVAL_TICKS != 0) {
            return;
        }
        if (this.getFuel() <= 0) {
            return;
        }
        this.setFuel(this.getFuel() - FUEL_DECAY_AMOUNT);
    }

    private void tickAutoRecovery() {
        if ((this.tickCount & 0x1F) == 0 && this.getHealth() < this.getMaxHealth() * AUTO_HEAL_THRESHOLD_RATIO) {
            this.heal(this.getMaxHealth() * AUTO_HEAL_FAST_RATIO + AUTO_HEAL_FAST_FLAT);
        }

        if ((this.tickCount & 0xFF) == 0 && this.getHealth() < this.getMaxHealth()) {
            this.heal(this.getMaxHealth() * AUTO_HEAL_SLOW_RATIO + AUTO_HEAL_SLOW_FLAT);
        }
    }

    private double getAcceleration() {
        double waterHeight = this.getShipDepth();

        double targetSubmersion = 0.3D;
        double springStiffness = 0.12D;
        double dampingFactor = 0.18D;
        double waveAmplitude = 0.005D;
        double waveFreq = 0.1D;

        double depthDiff = waterHeight - targetSubmersion;
        double springForce = depthDiff * springStiffness;

        double waveForce = Math.sin(this.tickCount * waveFreq) * waveAmplitude;

        return springForce + waveForce - (this.smoothedForce * dampingFactor);
    }

    private void applyShoreClimbAssist() {
        if (!this.isInWater() || this.isPassenger() || !this.horizontalCollision) {
            return;
        }

        double waterHeight = this.getShipDepth();
        if (waterHeight > 1.2D) {
            return;
        }

        Vec3 look = this.getLookAngle();
        Vec3 delta = this.getDeltaMovement();
        double pushY = Math.max(delta.y, 0.1D);
        double pushX = delta.x + look.x * 0.05D;
        double pushZ = delta.z + look.z * 0.05D;
        this.setDeltaMovement(pushX, pushY, pushZ);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        AmphibiousPathNavigation navigation = new AmphibiousPathNavigation(this, level);
        navigation.setCanFloat(true);
        return navigation;
    }

    public double getShipDepth() {
        Level level = this.level();
        int px = Mth.floor(this.getX());
        int py = Mth.floor(this.getBoundingBox().minY);
        int pz = Mth.floor(this.getZ());
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(px, py, pz);
        FluidState state = level.getFluidState(pos);

        if (state.isEmpty()) {
            return 0.0D;
        }

        double depth = 1.0D;
        int maxY = level.getMaxBuildHeight();
        for (int i = 1; py + i < maxY; i++) {
            pos.setY(py + i);
            if (!level.getFluidState(pos).isEmpty()) {
                depth += 1.0D;
            } else {
                break;
            }
        }

        depth -= (this.getY() - Mth.floor(this.getY()));
        return depth;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            if (!this.isTame()) {
                return InteractionResult.PASS;
            }

            if (!this.isOwnedBy(player)) {
                return InteractionResult.PASS;
            }

            ItemStack stack = player.getItemInHand(hand);
            if (stack.has(DataComponents.FOOD)) {
                FoodProperties food = stack.getFoodProperties(player);
                if (food != null && food.nutrition() > 0) {
                    this.setFuel(this.getFuel() + food.nutrition());
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    if (this.feedSoundCooldown <= 0) {
                        this.playSound(ModSounds.SHIP_FEED.get(), this.getSoundVolume(), this.getShipSoundPitch());
                        this.feedSoundCooldown = 30;
                    }
                    this.setEmotionPrimary(EMOTION_HAPPY);
                    this.resetInteractionEmotionState();
                    this.focusOnPlayer(player);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }

            if (player.isShiftKeyDown()) {
                this.openShipMenu(player);
                this.resetInteractionEmotionState();
                this.focusOnPlayer(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            boolean isSitting = !this.isOrderedToSit();
            this.setOrderedToSit(isSitting);
            this.setInSittingPose(isSitting);
            this.resetInteractionEmotionState();
            this.focusOnPlayer(player);

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (!this.getStateFlag(org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_CAN_MELEE)) {
            return false;
        }
        boolean result = super.doHurtTarget(target);
        if (result && !this.level().isClientSide) {
            this.playSound(ModSounds.SHIP_HIT.get(), this.getSoundVolume(), this.getShipSoundPitch());
            this.setAttackTick(50);
            applyEmotesReaction(3);
        }
        return result;
    }

    @Override
    public boolean hurt(net.minecraft.world.damagesource.DamageSource source, float amount) {
        float reduced = amount;
        if (!this.level().isClientSide) {
            reduced = this.legacyShipStats.getDefenseReducedDamage(amount, this.getRandom());
        }
        boolean result = super.hurt(source, reduced);
        if (!this.level().isClientSide && result && this.getRandom().nextInt(5) == 0) {
            applyEmotesReaction(2);
            this.setEmotionPrimary(EMOTION_SCORN);
        }
        return result;
    }

    @Override
    public void heal(float amount) {
        if (!this.level().isClientSide) {
            this.spawnLegacyHealParticles();
        }
        super.heal(amount);
    }

    private void spawnLegacyHealParticles() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double beamFad = this.getBbWidth() * 1.5D;
        double beamRiseSpeed = 0.1D;
        double beamHeight = this.getBbHeight() * 0.4D;
        serverLevel.sendParticles(
                ModParticles.PARTICLE_HEAL_SPARKLE.get(),
                this.getX(),
                this.getY() + beamHeight,
                this.getZ(),
                0,
                beamFad,
                beamRiseSpeed,
                beamHeight,
                1.0D
        );
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(1, new EntityShipPointerLookTargetGoal(this));
        this.goalSelector.addGoal(1, new EntityShipPointerMoveGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new EntityShipFollowOwnerGoal(this, 1.0D, 16.0F, 4.0F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F) {
            @Override
            public boolean canUse() {
                return !EntityShipBase.this.isInDeadPose() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !EntityShipBase.this.isInDeadPose() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this) {
            @Override
            public boolean canUse() {
                return !EntityShipBase.this.isInDeadPose() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !EntityShipBase.this.isInDeadPose() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D) {
            @Override
            public boolean canUse() {
                return !EntityShipBase.this.isOrderedToSit()
                        && !EntityShipBase.this.isInSittingPose()
                        && !EntityShipBase.this.isInDeadPose()
                        && !EntityShipBase.this.isPassenger()
                        && !EntityShipBase.this.isVehicle()
                        && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !EntityShipBase.this.isOrderedToSit()
                        && !EntityShipBase.this.isInSittingPose()
                        && !EntityShipBase.this.isInDeadPose()
                        && !EntityShipBase.this.isPassenger()
                        && !EntityShipBase.this.isVehicle()
                        && super.canContinueToUse();
            }
        });
    }

    public void onInventoryChanged() {
        this.combat.recalculateAmmoCounts();
        this.recalculateLegacyShipStats();
    }

    protected void recalculateLegacyShipStats() {
        this.legacyShipStats.recalculate(this.getStateMinor(STATE_MINOR_SHIP_CLASS), this.getLevel());

        if (this.level() != null && !this.level().isClientSide) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.legacyShipStats.getMaxHealth());
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.legacyShipStats.getFirepower());
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.legacyShipStats.getMoveSpeed());
            this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(Math.max(24.0D, this.legacyShipStats.getAttackRange()));
            if (this.getHealth() > this.getMaxHealth()) {
                this.setHealth(this.getMaxHealth());
            }
        }
    }

    protected void setFaceNormal() {
        this.setFaceId(FACE_EYES_OPEN);
        if (this.getEmotionSecondary() == EMOTION_BORED
                && (this.tickCount & this.emotions.getNormalMouthTickMask()) > this.emotions.getNormalMouthTickThreshold()) {
            this.setMouthId(resolveMouthId(MOUTH_FLIP_0));
        } else {
            this.setMouthId(MOUTH_FRONT_0);
        }
    }

    protected void setFaceCry() {
        int tick = getFaceElapsed() & this.emotions.getCryMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(64, FACE_DOT_EYES_TEAR, MOUTH_FLIP_2),
                new FaceStep(128, FACE_DOT_EYES_TEAR, MOUTH_FRONT_2),
                new FaceStep(256, FACE_CRY, MOUTH_FRONT_2)
        };
        applyFaceTimeline(steps, FACE_CRY, MOUTH_FRONT_2, tick);
    }

    protected void setFaceScornOrDamaged() {
        if ((this.tickCount & this.emotions.getScornToggleMask()) > this.emotions.getScornToggleThreshold()) {
            setFaceDamaged();
        } else {
            setFaceScorn();
        }
    }

    protected void setFaceScorn() {
        this.setFaceId(FACE_EYES_HALF);
        this.setMouthId(MOUTH_FRONT_1);
    }

    protected void setFaceDamaged() {
        int tick = getFaceElapsed() & this.emotions.getDamagedMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(60, FACE_DOT_EYES_TEAR, MOUTH_FLIP_2),
                new FaceStep(200, FACE_DOT_EYES_TEAR, MOUTH_FRONT_2),
                new FaceStep(250, FACE_TENSION, MOUTH_FRONT_0),
                new FaceStep(400, FACE_TENSION, MOUTH_FLIP_1),
                new FaceStep(450, FACE_SOFT, MOUTH_FRONT_0)
        };
        applyFaceTimeline(steps, FACE_SOFT, MOUTH_FRONT_1, tick);
    }

    protected void setFaceHungry() {
        this.setFaceId(FACE_DESPAIR);
        this.setMouthId(MOUTH_FRONT_2);
    }

    protected void setFaceAngry() {
        int tick = getFaceElapsed() & this.emotions.getAngryMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(64, FACE_EYES_CLOSED, MOUTH_FRONT_0),
                new FaceStep(128, FACE_EYES_CLOSED, MOUTH_FRONT_1),
                new FaceStep(170, FACE_EYES_HALF, MOUTH_FRONT_1)
        };
        applyFaceTimeline(steps, FACE_EYES_HALF, MOUTH_FRONT_2, tick);
    }

    protected void setFaceBored() {
        int tick = getFaceElapsed() & this.emotions.getBoredMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(80, FACE_DOT_EYES, MOUTH_FRONT_0),
                new FaceStep(170, FACE_DOT_EYES, MOUTH_FLIP_1),
                new FaceStep(340, FACE_WINK, MOUTH_FRONT_0)
        };
        applyFaceTimeline(steps, FACE_EYES_OPEN, MOUTH_FRONT_0, tick);
    }

    protected void setFaceShy() {
        int tick = getFaceElapsed() & this.emotions.getShyMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(80, FACE_EYES_OPEN, MOUTH_FLIP_0),
                new FaceStep(140, FACE_EYES_OPEN, MOUTH_FRONT_2)
        };
        applyFaceTimeline(steps, FACE_WINK, MOUTH_FRONT_0, tick);
    }

    protected void setFaceHappy() {
        int tick = getFaceElapsed() & this.emotions.getHappyMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(80, FACE_TENSION, MOUTH_FRONT_0),
                new FaceStep(140, FACE_TENSION, MOUTH_FLIP_1)
        };
        applyFaceTimeline(steps, FACE_WINK, MOUTH_FLIP_1, tick);
    }

    protected void ensureFaceTick() {
        this.emotions.ensureFaceTick();
    }

    protected int getFaceElapsed() {
        return this.emotions.getFaceElapsed();
    }

    protected int resolveMouthId(int id) {
        return switch (id) {
            case MOUTH_FLIP_0 -> MOUTH_FRONT_0;
            case MOUTH_FLIP_1 -> MOUTH_FRONT_1;
            case MOUTH_FLIP_2 -> MOUTH_FRONT_2;
            default -> id;
        };
    }

    protected int mapLegacyMouth(int legacyId) {
        return switch (legacyId) {
            case 0 -> MOUTH_FRONT_0;
            case 1 -> MOUTH_FRONT_1;
            case 2 -> MOUTH_FRONT_2;
            case 3 -> MOUTH_FLIP_0;
            case 4 -> MOUTH_FLIP_1;
            case 5 -> MOUTH_FLIP_2;
            default -> MOUTH_FRONT_0;
        };
    }

    protected int getLegacyFaceTick(int mask) {
        return (this.tickCount + (this.getStateMinor(22) << 7)) & mask;
    }

    protected void applyFaceTimeline(FaceStep[] steps, int fallbackFaceId, int fallbackMouthId, int tick) {
        for (FaceStep step : steps) {
            if (tick < step.untilTick) {
                this.setFaceId(step.faceId);
                this.setMouthId(resolveMouthId(step.mouthId));
                return;
            }
        }
        this.setFaceId(fallbackFaceId);
        this.setMouthId(resolveMouthId(fallbackMouthId));
    }

    protected static final class FaceStep {
        private final int untilTick;
        private final int faceId;
        private final int mouthId;

        public FaceStep(int untilTick, int faceId, int mouthId) {
            this.untilTick = untilTick;
            this.faceId = faceId;
            this.mouthId = mouthId;
        }
    }

    protected void openShipMenu(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MenuProvider provider = new SimpleMenuProvider(
                    (id, inv, ply) -> new ShipContainerMenu(id, inv, this),
                    Component.translatable("gui.shincolle.ship")
            );
            (serverPlayer).openMenu(provider, buffer -> buffer.writeInt(this.getId()));
        }
    }

    protected void migrateLegacyStateFlags(int stateFlags) {
    }

    protected int getLegacyModelStateRange() {
        return 128;
    }

    protected int getInitialLegacyEmotion(int index) {
        if (index == 0) {
            int range = Math.max(1, getLegacyModelStateRange());
            return this.getRandom().nextInt(range);
        }
        return 0;
    }

    private void initializeLegacyState() {
        for (int i = 0; i < LEGACY_STATE_EMOTION_COUNT; i++) {
            setStateEmotion(i, getInitialLegacyEmotion(i), false);
        }
        this.legacyStateInitialized = true;
    }

    private int[] getLegacyEmotionSnapshot() {
        return new int[]{
                getStateEmotion(0),
                getStateEmotion(1),
                getStateEmotion(2),
                getStateEmotion(3),
                getStateEmotion(4),
                getStateEmotion(5),
                getStateEmotion(6),
                getStateEmotion(7)
        };
    }

    private void applyLegacyEmotionSnapshot(int[] legacy) {
        if (legacy == null || legacy.length == 0) {
            return;
        }
        int length = Math.min(legacy.length, LEGACY_STATE_EMOTION_COUNT);
        for (int i = 0; i < length; i++) {
            setStateEmotion(i, legacy[i], false);
        }
    }

    private void tickLegacyTimers() {
        int attackTick = getAttackTick();
        if (attackTick > 0) {
            setAttackTick(attackTick - 1);
        }
        if (this.customHurtTime > 0) {
            this.customHurtTime--;
        }
        if (this.hurtSoundCooldown > 0) {
            this.hurtSoundCooldown--;
        }
        if (this.feedSoundCooldown > 0) {
            this.feedSoundCooldown--;
        }
    }

    private void tickEmotes() {
        if (this.emotesTick > 0) {
            this.emotesTick--;
        }
    }

    private int getMoraleLevel() {
        int morale = this.getMorale();
        if (morale > 5100) return 0;
        if (morale > 3900) return 1;
        if (morale > 2100) return 2;
        if (morale > 900) return 3;
        return 4;
    }

    private void setEmotesTick(int ticks) {
        this.emotesTick = Math.max(this.emotesTick, ticks);
    }

    public void applyEmotesReaction(int type) {
        if (this.emotesTick > 10 && type == 2) {
            return;
        }
        if (this.emotesTick > 0 && type != 2) {
            return;
        }
        switch (type) {
            case 0 -> {
                if (this.getRandom().nextInt(7) == 0) {
                    setEmotesTick(50);
                    reactionNormal();
                }
            }
            case 1 -> {
                if (this.getRandom().nextInt(9) == 0) {
                    setEmotesTick(60);
                    reactionStranger();
                }
            }
            case 2 -> {
                setEmotesTick(40);
                reactionDamaged();
            }
            case 3 -> {
                if (this.getRandom().nextInt(6) == 0) {
                    setEmotesTick(60);
                    reactionAttack();
                }
            }
            case 4 -> {
                if (this.getRandom().nextInt(3) == 0) {
                    setEmotesTick(20);
                    reactionIdle();
                }
            }
            case 5 -> {
                if (this.getRandom().nextInt(3) == 0) {
                    setEmotesTick(25);
                    reactionCommand();
                }
            }
            case 6 -> reactionShock();
            default -> {
            }
        }
    }

    private void reactionNormal() {
        switch (getMoraleLevel()) {
            case 0 -> {
                this.setEmotionPrimary(EMOTION_HAPPY);
                EmotionParticleType[] emotes = {
                        EmotionParticleType.BLUSH,
                        EmotionParticleType.DIZZY_EYES,
                        EmotionParticleType.POUT_BOUNCE,
                        EmotionParticleType.HEART,
                        EmotionParticleType.MUSIC_NOTE
                };
                applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
            }
            case 1 -> {
                this.setEmotionPrimary(EMOTION_SHY);
                EmotionParticleType[] emotes = {
                        EmotionParticleType.HEART,
                        EmotionParticleType.LAUGH,
                        EmotionParticleType.MUSIC_NOTE
                };
                applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
            }
            case 2 -> {
                this.setEmotionPrimary(EMOTION_SHY);
                EmotionParticleType[] emotes = {
                        EmotionParticleType.SIGH,
                        EmotionParticleType.MUSIC_NOTE,
                        EmotionParticleType.PEACE,
                        EmotionParticleType.HAPPY_GLANCE,
                        EmotionParticleType.BLINK
                };
                applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
            }
            case 3 -> {
                EmotionParticleType[] emotes = {
                        EmotionParticleType.SIGH,
                        EmotionParticleType.SWEAT_DROPS,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.SWEAT_DROP_BIG
                };
                applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
            }
            default -> {
                EmotionParticleType[] emotes = {
                        EmotionParticleType.TEARS,
                        EmotionParticleType.SWEAT_DROPS,
                        EmotionParticleType.ORZ,
                        EmotionParticleType.SILENCE,
                        EmotionParticleType.GLOOM
                };
                applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
            }
        }
    }

    private void reactionStranger() {
        this.setEmotionPrimary(EMOTION_ANGRY);
        if (this.getRandom().nextBoolean()) {
            applyParticleEmotion(this.getRandom().nextBoolean()
                    ? EmotionParticleType.ANGER
                    : EmotionParticleType.CROSS);
        } else {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.DROOL,
                    EmotionParticleType.SWEAT_DROPS,
                    EmotionParticleType.ORZ,
                    EmotionParticleType.TEARS,
                    EmotionParticleType.SWEAT_DROP_BIG,
                    EmotionParticleType.GLOOM
            };
            applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
        }
    }

    private void reactionAttack() {
        if (getMoraleLevel() == 0) {
            this.setEmotionPrimary(EMOTION_HAPPY);
            EmotionParticleType[] emotes = {
                    EmotionParticleType.SILLY_TONGUE,
                    EmotionParticleType.EVIL_GRIN,
                    EmotionParticleType.TONGUE_OUT,
                    EmotionParticleType.LAUGH,
                    EmotionParticleType.MUSIC_NOTE
            };
            applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
        } else {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.SPARKLE_EYES,
                    EmotionParticleType.SIGH,
                    EmotionParticleType.MUSIC_NOTE,
                    EmotionParticleType.EXCLAMATION,
                    EmotionParticleType.MUSIC_NOTE,
                    EmotionParticleType.ANGER
            };
            applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
        }
    }

    private void reactionDamaged() {
        if (getMoraleLevel() <= 2) {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.SIGH,
                    EmotionParticleType.SILENCE,
                    EmotionParticleType.SWEAT_DROPS,
                    EmotionParticleType.QUESTION,
                    EmotionParticleType.TEARS
            };
            applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
        } else {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.SIGH,
                    EmotionParticleType.SILENCE,
                    EmotionParticleType.SWEAT_DROPS,
                    EmotionParticleType.QUESTION,
                    EmotionParticleType.SWEAT_DROP_BIG,
                    EmotionParticleType.TEARS
            };
            applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
        }
    }

    private void reactionIdle() {
        switch (getMoraleLevel()) {
            case 0, 1 -> {
                EmotionParticleType[] emotesSparkling = {
                        EmotionParticleType.SILLY_TONGUE,
                        EmotionParticleType.EVIL_GRIN,
                        EmotionParticleType.TONGUE_OUT,
                        EmotionParticleType.DROOL,
                        EmotionParticleType.HEART,
                        EmotionParticleType.POUT_BOUNCE,
                        EmotionParticleType.LAUGH,
                        EmotionParticleType.SPARKLE_EYES,
                        EmotionParticleType.MUSIC_NOTE
                };
                applyParticleEmotion(emotesSparkling[this.getRandom().nextInt(emotesSparkling.length)]);
            }
            case 2 -> {
                EmotionParticleType[] emotesNormal = {
                        EmotionParticleType.HAPPY_GLANCE,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.HAPPY_BOB,
                        EmotionParticleType.DROOL,
                        EmotionParticleType.SHAKE_HEAD,
                        EmotionParticleType.LAUGH,
                        EmotionParticleType.BLINK
                };
                applyParticleEmotion(emotesNormal[this.getRandom().nextInt(emotesNormal.length)]);
            }
            default -> {
                EmotionParticleType[] emotesTired = {
                        EmotionParticleType.SWEAT_DROP_BIG,
                        EmotionParticleType.SWEAT_DROPS,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.TEARS,
                        EmotionParticleType.DIZZY_EYES,
                        EmotionParticleType.ORZ,
                        EmotionParticleType.SCRATCH_HEAD
                };
                applyParticleEmotion(emotesTired[this.getRandom().nextInt(emotesTired.length)]);
            }
        }
    }

    private void reactionCommand() {
        switch (getMoraleLevel()) {
            case 0, 1, 2 -> {
                EmotionParticleType[] emotesOk = {
                        EmotionParticleType.CIRCLE,
                        EmotionParticleType.EXCLAMATION,
                        EmotionParticleType.SPARKLE_EYES,
                        EmotionParticleType.HAPPY_GLANCE,
                        EmotionParticleType.HAPPY_BOB
                };
                applyParticleEmotion(emotesOk[this.getRandom().nextInt(emotesOk.length)]);
            }
            default -> {
                EmotionParticleType[] emotesTired = {
                        EmotionParticleType.SWEAT_DROP_BIG,
                        EmotionParticleType.SILLY_TONGUE,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.DIZZY_EYES,
                        EmotionParticleType.HAPPY_BOB,
                        EmotionParticleType.SCRATCH_HEAD
                };
                applyParticleEmotion(emotesTired[this.getRandom().nextInt(emotesTired.length)]);
            }
        }
    }

    private void reactionShock() {
        EmotionParticleType[] emotes = {
                EmotionParticleType.SWEAT_DROP_BIG,
                EmotionParticleType.TEARS,
                EmotionParticleType.EXCLAMATION,
                EmotionParticleType.SHOCK
        };
        applyParticleEmotion(emotes[this.getRandom().nextInt(emotes.length)]);
    }

    public void applyParticleEmotion(EmotionParticleType type) {
        if (this.level().isClientSide) {
            spawnEmotionParticleClient(type);
            return;
        }
        if (!(this.level() instanceof net.minecraft.server.level.ServerLevel)) {
            return;
        }
        int nextSeq = this.emotionParticleSeq++ & 0x7FFF;
        int packed = (nextSeq << 16) | (type.getId() & 0xFF);
        this.entityData.set(EMOTION_PARTICLE, packed);
    }

    public void applyParticleEmotion(int typeId) {
        applyParticleEmotion(EmotionParticleType.fromId(typeId));
    }

    private void spawnEmotionParticleClient(EmotionParticleType type) {
        if (!(this.level() instanceof net.minecraft.client.multiplayer.ClientLevel clientLevel)) {
            return;
        }
        double baseX = this.getX() + (this.getRandom().nextDouble() - 0.5D) * 0.2D;
        double baseY = this.getY() + this.getBbHeight() * 0.6D;
        double baseZ = this.getZ() + (this.getRandom().nextDouble() - 0.5D) * 0.2D;
        float height = (float) (this.getBbHeight() * 0.6D);
        clientLevel.addParticle(ModParticles.PARTICLE_EMOTION.get(), baseX, baseY, baseZ,
                height, this.getId(), type.getId());
    }

    public record EquipOption(String key, String labelKey) {
    }

    private static final class LegacyShipState {
        private final int[] stateMinor;
        private final int[] stateTimer;
        private final boolean[] stateFlag;
        private final boolean[] updateFlag;
        private final byte[] bodyHeightStand;
        private final byte[] bodyHeightSit;
        private final float[] modelPos;
        private BlockPos[] waypoints;

        private LegacyShipState() {
            this.stateMinor = Arrays.copyOf(LEGACY_STATE_MINOR_DEFAULTS, LEGACY_STATE_MINOR_SIZE);
            this.stateTimer = new int[LEGACY_STATE_TIMER_SIZE];
            this.stateFlag = Arrays.copyOf(LEGACY_STATE_FLAG_DEFAULTS, LEGACY_STATE_FLAG_SIZE);
            this.updateFlag = new boolean[LEGACY_UPDATE_FLAG_SIZE];
            this.bodyHeightStand = Arrays.copyOf(LEGACY_BODY_HEIGHT_STAND_DEFAULTS, LEGACY_BODY_HEIGHT_STAND_DEFAULTS.length);
            this.bodyHeightSit = Arrays.copyOf(LEGACY_BODY_HEIGHT_SIT_DEFAULTS, LEGACY_BODY_HEIGHT_SIT_DEFAULTS.length);
            this.modelPos = Arrays.copyOf(LEGACY_MODEL_POS_DEFAULTS, LEGACY_MODEL_POS_DEFAULTS.length);
            this.waypoints = new BlockPos[]{BlockPos.ZERO};
        }

        private int getInt(int[] data, int index) {
            if (index < 0 || index >= data.length) {
                return 0;
            }
            return data[index];
        }

        private void setInt(int[] data, int index, int value) {
            if (index < 0 || index >= data.length) {
                return;
            }
            data[index] = value;
        }

        private boolean getBoolean(boolean[] data, int index) {
            if (index < 0 || index >= data.length) {
                return false;
            }
            return data[index];
        }

        private void setBoolean(boolean[] data, int index, boolean value) {
            if (index < 0 || index >= data.length) {
                return;
            }
            data[index] = value;
        }

        private void applyIntArray(int[] target, int[] source) {
            if (source == null || target == null) {
                return;
            }
            int length = Math.min(target.length, source.length);
            System.arraycopy(source, 0, target, 0, length);
        }

        private void applyByteArray(boolean[] target, byte[] source) {
            if (source == null || target == null) {
                return;
            }
            int length = Math.min(target.length, source.length);
            for (int i = 0; i < length; i++) {
                target[i] = source[i] != 0;
            }
        }

        private void applyByteArray(byte[] target, byte[] source) {
            if (source == null || target == null) {
                return;
            }
            int length = Math.min(target.length, source.length);
            System.arraycopy(source, 0, target, 0, length);
        }

        private byte[] toByteArray(boolean[] source) {
            byte[] data = new byte[source.length];
            for (int i = 0; i < source.length; i++) {
                data[i] = source[i] ? (byte) 1 : (byte) 0;
            }
            return data;
        }

        private int[] getModelPosBits() {
            int[] bits = new int[this.modelPos.length];
            for (int i = 0; i < this.modelPos.length; i++) {
                bits[i] = Float.floatToIntBits(this.modelPos[i]);
            }
            return bits;
        }

        private void applyModelPos(float[] pos) {
            if (pos == null) {
                return;
            }
            int length = Math.min(this.modelPos.length, pos.length);
            System.arraycopy(pos, 0, this.modelPos, 0, length);
        }

        private void applyModelPosBits(int[] bits) {
            if (bits == null) {
                return;
            }
            int length = Math.min(this.modelPos.length, bits.length);
            for (int i = 0; i < length; i++) {
                this.modelPos[i] = Float.intBitsToFloat(bits[i]);
            }
        }

        private int[] getWaypointBits() {
            if (this.waypoints == null || this.waypoints.length == 0) {
                return new int[0];
            }
            int[] data = new int[this.waypoints.length * 3];
            for (int i = 0; i < this.waypoints.length; i++) {
                BlockPos pos = this.waypoints[i];
                int base = i * 3;
                data[base] = pos.getX();
                data[base + 1] = pos.getY();
                data[base + 2] = pos.getZ();
            }
            return data;
        }

        private void applyWaypoints(BlockPos[] points) {
            if (points == null || points.length == 0) {
                this.waypoints = new BlockPos[]{BlockPos.ZERO};
                return;
            }
            this.waypoints = Arrays.copyOf(points, points.length);
        }

        private void applyWaypointBits(int[] data) {
            if (data == null || data.length < 3) {
                return;
            }
            int count = data.length / 3;
            BlockPos[] points = new BlockPos[count];
            for (int i = 0; i < count; i++) {
                int base = i * 3;
                points[i] = new BlockPos(data[base], data[base + 1], data[base + 2]);
            }
            this.waypoints = points;
        }
    }

    private static final class EntityShipFollowOwnerGoal extends Goal {
        private final EntityShipBase ship;
        private final double speed;
        private final float maxDist;
        private final float minDist;
        private int refreshTick;

        private EntityShipFollowOwnerGoal(EntityShipBase ship, double speed, float maxDist, float minDist) {
            this.ship = ship;
            this.speed = speed;
            this.maxDist = maxDist;
            this.minDist = minDist;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return canFollowOwner();
        }

        @Override
        public boolean canContinueToUse() {
            return canFollowOwner();
        }

        @Override
        public void start() {
            this.refreshTick = 0;
        }

        @Override
        public void tick() {
            LivingEntity owner = ship.getOwner();
            if (owner == null) {
                return;
            }

            ship.resetInteractionEmotionState();
            ship.getLookControl().setLookAt(owner, 30.0F, 30.0F);
            if (this.refreshTick-- <= 0) {
                this.refreshTick = 10;
                ship.getNavigation().moveTo(owner, this.speed);
            }
        }

        @Override
        public void stop() {
            ship.getNavigation().stop();
        }

        private boolean canFollowOwner() {
            if (ship.isOrderedToSit() || ship.isInSittingPose() || ship.isInDeadPose() || ship.isPassenger()) {
                return false;
            }
            LivingEntity owner = ship.getOwner();
            if (owner == null) {
                return false;
            }
            double distanceSqr = ship.distanceToSqr(owner);
            return distanceSqr > (this.minDist * this.minDist)
                    && distanceSqr < (this.maxDist * this.maxDist) * 256.0D;
        }
    }
}