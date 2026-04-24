package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import org.trp.shincolle.Config;
import org.trp.shincolle.entity.EntityShipGrudge;
import org.trp.shincolle.entity.base.path.ShipLegacyNavigation;
import org.trp.shincolle.entity.base.path.ShipMoveControl;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.item.CombatRationItem;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.item.LegacyEquipStats;
import org.trp.shincolle.menu.ShipContainerMenu;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public static final int COMBAT_TEXT_MISS = 0;
    public static final int COMBAT_TEXT_CRITICAL = 1;
    public static final int COMBAT_TEXT_DOUBLE_HIT = 2;
    public static final int COMBAT_TEXT_TRIPLE_HIT = 3;
    public static final int COMBAT_TEXT_DODGE = 4;

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
    private static final float CRUISE_SPEED_FACTOR = 0.3F;
    private static final float AUTO_HEAL_THRESHOLD_RATIO = 0.9F;
    private static final float AUTO_HEAL_FAST_RATIO = 0.08F;
    private static final float AUTO_HEAL_FAST_FLAT = 15.0F;
    private static final int AUTO_HEAL_FAST_FUEL_COST = 7;
    private static final float AUTO_HEAL_SLOW_RATIO = 0.03F;
    private static final float AUTO_HEAL_SLOW_FLAT = 1.0F;
    private static final float LEGACY_MELEE_DAMAGE_FACTOR = 0.125F;
    private static final float PICK_RADIUS_MODEL_SCALE = 0.012F;
    private static final float PICK_RADIUS_MIN = 0.35F;
    private static final float PICK_RADIUS_MAX = 1.15F;
    private static final int SHIP_DEATH_MAX_TICKS = 300;
    private static final int SHIP_LEVEL_HARD_CAP = 150;
    private static final double DEAD_FLOAT_HOVER_OFFSET = 0.08D;
    private static final double DEAD_FLOAT_STOP_EPSILON = 0.003D;
    private static final long TIMEKEEP_INTERVAL_TICKS = 1000L;
    private static final int PICK_ITEM_SCAN_INTERVAL_TICKS = 16;
    private static final int AUTO_PUMP_INTERVAL_TICKS = 40;
    private static final int AUTO_PUMP_XP_INTERVAL_TICKS = 4;
    private static final int AUTO_RATION_INTERVAL_TICKS = 128;
    private static final int AUTO_RATION_MAX_FUEL = 100;
    private static final int SEARCHLIGHT_INTERVAL_TICKS = 4;
    private static final int COMPASS_CHUNK_REFRESH_INTERVAL_TICKS = 40;
    private static final int COMPASS_CHUNK_RADIUS = 1;
    private static final int SPECIAL_EQUIP_FLARE_GLOW_TICKS = 80;
    private static final int SPECIAL_EQUIP_SEARCHLIGHT_NIGHT_VISION_TICKS = 220;
    private static final int XP_BOTTLE_COST = 8;
    private static final int HOSTILE_LIGHT_AMMO_CONTAINER_COUNT = 16;
    private static final int HOSTILE_HEAVY_AMMO_CONTAINER_COUNT = 12;
    private static final String TAG_SPAWN_EGG = "ShincolleSpawnEgg";
    private static final String TAG_SPAWN_EGG_NO_EXP = "ShincolleSpawnEggNoExpCost";

    public static final int STATE_MINOR_FACTION_ID = 19;
    public static final int STATE_MINOR_SHIP_CLASS = 20;
    public static final int STATE_MINOR_SPECIAL_EQUIP = 25;
    public static final int STATE_MINOR_RARITY = 13;
    private static final int STATE_MINOR_EQUIP_DRUM = 36;
    private static final int STATE_MINOR_EQUIP_COMPASS = 37;
    private static final int STATE_MINOR_EQUIP_FLARE = 38;
    private static final int STATE_MINOR_EQUIP_SEARCHLIGHT = 39;
    private static final int STATE_MINOR_PUMPED_XP = 42;

    private static final int EQUIP_TYPE_DRUM = 24;
    private static final int EQUIP_TYPE_COMPASS = 25;
    private static final int EQUIP_TYPE_FLARE = 26;
    private static final int EQUIP_TYPE_SEARCHLIGHT = 27;
    private static final int EQUIP_DRUM_VARIANT_LIQUID = 1;

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
    private final EntityShipBaseFaceExpressions faceExpressions;
    private final EntityShipBaseReactions reactions;
    private final EntityShipBasePassiveCombat passiveCombat;
    private final EntityShipBaseSerialization serialization;
    private final LegacyShipStats legacyShipStats;
    private final EntityShipLegacyState legacyState;
    private boolean legacyStateInitialized = false;
    private int shipDeathTicks = 0;
    private boolean hostileCanDrop = true;
    private int stateUpdateTimer;
    private int customHurtTime;
    private int hurtSoundCooldown;
    private int feedSoundCooldown;
    private final Set<Long> forcedCompassChunks = new HashSet<>();
    private int forcedCompassChunkCenterX = Integer.MIN_VALUE;
    private int forcedCompassChunkCenterZ = Integer.MIN_VALUE;

    protected EntityShipBase(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.inventory = new ShipInventoryHandler(this, 60);
        this.combat = new EntityShipBaseCombat(this);
        this.pointer = new EntityShipBasePointer(this);
        this.emotions = new EntityShipBaseEmotions(this);
        this.faceExpressions = new EntityShipBaseFaceExpressions(this, this.emotions);
        this.reactions = new EntityShipBaseReactions(this);
        this.passiveCombat = new EntityShipBasePassiveCombat(this);
        this.serialization = new EntityShipBaseSerialization(this);
        this.legacyShipStats = new LegacyShipStats();
        this.legacyState = new EntityShipLegacyState();
        this.moveControl = new ShipMoveControl(this, 30.0F);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.LAVA, 0.0F);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
    }

    @Override
    public float maxUpStep() {
        return 1.0F;
    }

    static int getMoraleDefaultValue() {
        return MORALE_DEFAULT;
    }

    static String getSpawnEggTagName() {
        return TAG_SPAWN_EGG;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        EntityShipBaseSerialization.defineSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        this.serialization.addAdditionalSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.serialization.readAdditionalSaveData(compound);
    }

    public int getLevel() {
        return this.entityData.get(SHIP_LEVEL);
    }

    public void setLevel(int level) {
        this.entityData.set(SHIP_LEVEL, Mth.clamp(level, 1, SHIP_LEVEL_HARD_CAP));
        this.recalculateLegacyShipStats();
    }

    public int getExp() {
        return this.entityData.get(SHIP_EXP);
    }

    public void setExp(int exp) {
        this.entityData.set(SHIP_EXP, Math.max(0, exp));
    }

    public int getMaxShipLevel() {
        int configured = this.isStateMarried() ? Config.shipMaxLevelMarried : Config.shipMaxLevelNormal;
        return Mth.clamp(configured, 1, SHIP_LEVEL_HARD_CAP);
    }

    public int getExpToNextLevel() {
        int level = Math.max(1, this.getLevel());
        return Math.max(1, level * Config.shipExpModifier + Config.shipExpModifier);
    }

    public void addShipExp(int exp) {
        if (exp <= 0 || this.level().isClientSide || !this.isTame()) {
            return;
        }

        int maxLevel = this.getMaxShipLevel();
        int level = this.getLevel();
        if (level >= maxLevel) {
            return;
        }

        int totalExp = this.getExp() + exp;
        boolean leveledUp = false;
        while (level < maxLevel) {
            int expNext = Math.max(1, level * Config.shipExpModifier + Config.shipExpModifier);
            if (totalExp < expNext) {
                break;
            }
            totalExp -= expNext;
            level++;
            leveledUp = true;
        }

        if (level >= maxLevel) {
            totalExp = 0;
        }

        this.setExp(totalExp);
        if (leveledUp) {
            this.setLevel(level);
            this.setHealth(this.getMaxHealth());
            this.playLevelUpEffects();
        }
    }

    public boolean addTrainingBookLevel(int levelGain) {
        if (levelGain <= 0 || this.level().isClientSide || !this.isTame()) {
            return false;
        }

        int maxLevel = this.getMaxShipLevel();
        int currentLevel = this.getLevel();
        if (currentLevel >= maxLevel) {
            return false;
        }

        int targetLevel = Math.min(maxLevel, currentLevel + levelGain);
        this.setLevel(targetLevel);
        this.setHealth(this.getMaxHealth());
        this.playLevelUpEffects();
        return true;
    }

    private void playLevelUpEffects() {
        if (this.level().isClientSide) {
            return;
        }

        if (this.getRandom().nextInt(4) == 0) {
            this.playSound(SoundEvents.PLAYER_LEVELUP, 0.75F, 1.0F);
        } else {
            this.playSound(ModSounds.SHIP_LEVELUP.get(), 0.75F, 1.0F);
        }
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
            this.reactions.spawnEmotionParticleClient(EmotionParticleType.fromId(typeId));
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
        this.refreshDimensions();
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
        this.refreshDimensions();
    }

    @Override
    public float getPickRadius() {
        float[] modelPos = this.getModelPos();
        float visualSize = (modelPos != null && modelPos.length > 3) ? modelPos[3] : 50.0F;
        float radius = Mth.clamp(visualSize * PICK_RADIUS_MODEL_SCALE, PICK_RADIUS_MIN, PICK_RADIUS_MAX);
        float scaleFactor = Mth.clamp(1.0F + this.getScaleLevel() * 0.5F, 1.0F, 2.5F);
        return Mth.clamp(radius * scaleFactor, PICK_RADIUS_MIN, PICK_RADIUS_MAX);
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
    public boolean displayFireAnimation() {
        if (super.displayFireAnimation()) {
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

    CompoundTag copyEquipFlagsTag() {
        return this.entityData.get(EQUIP_FLAGS).copy();
    }

    void setEquipFlagsTag(CompoundTag flags) {
        this.entityData.set(EQUIP_FLAGS, flags);
    }

    public List<EquipOption> getEquipOptions() {
        return Collections.emptyList();
    }

    protected abstract Item getShipSpawnEggItem();

    public ShipInventoryHandler getInventory() {
        return this.inventory;
    }

    public int getAccessibleInventorySlotCount() {
        return this.inventory.getAccessibleSlotCount();
    }

    public boolean isHostileShipMob() {
        return !this.isTame() && this.getOwnerUUID() == null;
    }

    public void initializeHostileSpawnState(int scaleLevel) {
        int clampedScale = Mth.clamp(scaleLevel, 0, 3);

        this.setTame(false, false);
        this.setOwnerUUID(null);
        this.setOrderedToSit(false);
        this.setInSittingPose(false);
        this.setScaleLevel(clampedScale);
        this.setLevel(switch (clampedScale) {
            case 0 -> 75;
            case 1 -> 100;
            case 2 -> 125;
            default -> 150;
        });

        this.setFuel(100);
        this.setStateCanMelee(true);
        this.setStateLightAttack(true);
        this.setStateHeavyAttack(true);
        this.setStateLightAircraftAttack(true);
        this.setStateHeavyAircraftAttack(true);
        this.setStateAntiAir(true);
        this.setStateFlag(ShipContainerMenu.STATE_FLAG_ANTI_SUB, true);
        this.setStateFlag(ShipContainerMenu.STATE_FLAG_PVP, true);
        this.setStateFlag(ShipContainerMenu.STATE_FLAG_PASSIVE_ATTACK, true);
        this.setStateFlag(ShipContainerMenu.STATE_FLAG_ON_SIGHT, false);
        this.setStateFlag(ShipContainerMenu.STATE_FLAG_PICK_ITEM, false);
        this.setStateFlag(ShipContainerMenu.STATE_FLAG_AUTO_PUMP, false);
        this.setStateMinor(ShipContainerMenu.STATE_MINOR_FLEE_HP, 0);
        this.entityData.set(LEGACY_EMOTION_0, this.getRandom().nextInt(128));

        fillHostileAmmoLoadout();
        this.recalculateLegacyShipStats();
        this.setHealth(this.getMaxHealth());
        this.shipDeathTicks = 0;
        this.hostileCanDrop = true;
    }

    private void fillHostileAmmoLoadout() {
        int slots = this.getAccessibleInventorySlotCount();
        for (int i = 0; i < slots; i++) {
            this.getInventory().setStackInSlot(i, ItemStack.EMPTY);
        }

        if (slots > 0) {
            this.getInventory().setStackInSlot(0, new ItemStack(ModItems.AMMO_LIGHT_CONTAINER.get(), HOSTILE_LIGHT_AMMO_CONTAINER_COUNT));
        }
        if (slots > 1) {
            this.getInventory().setStackInSlot(1, new ItemStack(ModItems.AMMO_HEAVY_CONTAINER.get(), HOSTILE_HEAVY_AMMO_CONTAINER_COUNT));
        }
        this.onInventoryChanged();
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

    EntityShipLegacyState getLegacyStateInternal() {
        return this.legacyState;
    }

    void savePointerToNbt(CompoundTag compound) {
        this.pointer.saveToNbt(compound);
    }

    void loadPointerFromNbt(CompoundTag compound) {
        this.pointer.loadFromNbt(compound);
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
    public void aiStep() {
        super.aiStep();

        if (this.isOnFire()) {
            this.clearFire();
        }

        if (this.isAlive() && !this.level().isClientSide) {
            this.tickAliveLogic();
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        super.travel(travelVector);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isHostileShipMob()) {
            return false;
        }
        return super.removeWhenFarAway(distanceToClosestPlayer);
    }

    protected boolean tickHostileDespawn() {
        if (!this.isHostileShipMob()) {
            return false;
        }
        if (this.level().getDifficulty() == net.minecraft.world.Difficulty.PEACEFUL) {
            this.discard();
            return true;
        }
        int minionDespawn = org.trp.shincolle.Config.hostileDespawnMinionTicks;
        if (minionDespawn >= 0) {
            Player player = this.level().getNearestPlayer(this, -1.0D);
            if (player != null) {
                double distSq = player.distanceToSqr(this);
                if (distSq > 16384.0D) {
                    this.discard();
                    return true;
                } else if (distSq > 1024.0D) {
                    if (this.tickCount > minionDespawn && this.getRandom().nextInt(800) == 0) {
                        this.discard();
                        return true;
                    }
                }
            } else {
                this.discard();
                return true;
            }
        }
        return false;
    }

    protected void tickAliveLogic() {
        this.emotions.tickEmotions();

        if (!this.level().isClientSide && tickHostileDespawn()) {
            return;
        }

        if (this.getIsSitting() || this.isInDeadPose()) {
            this.getNavigation().stop();
        }

        if (!this.isNoFuel()) {
            this.pointer.tickPointerTargetEntity();

            if (shouldRetreatForLowHealth()) {
                this.passiveCombat.clearTarget(true);
                tickRetreatMovement();
            } else if (this.hasPointerTargetEntity()) {
                this.passiveCombat.clearTarget(true);
            } else {
                this.passiveCombat.tickTargeting();
                this.passiveCombat.tickActions();
            }

            this.combat.tickAircraftRecovery();
            tickAutoPickupItems();
            tickAutoPump();
            tickAutoRation();
            this.reactions.tickEmotes();
            if ((this.tickCount & 0xFF) == 0) {
                applyEmotesReaction(4);
            }
        } else {
            this.passiveCombat.clearTarget(true);
        }

        tickSearchlightAssist();
        tickCompassChunkLoading();
        tickTimeKeepingSound();

        tickFuelDecay();
        tickAutoRecovery();
        tickLegacyTimers();
        if ((this.tickCount % 40) == 0) {
            this.recalculateLegacyShipStats();
        }
    }

    @Override
    protected void tickDeath() {
        this.setEmotionPrimary(EMOTION_HUNGRY);
        this.setFaceHungry();
        this.shipDeathTicks++;
        if (this.isInWaterOrBubble() || this.isInLava()) {
            this.applyDeadFloatStabilization();
        }
        if (!this.level().isClientSide && this.shipDeathTicks == SHIP_DEATH_MAX_TICKS) {
            spawnShipGrudge();
        }
        if (this.shipDeathTicks >= SHIP_DEATH_MAX_TICKS) {
            this.discard();
        }
        this.deathTime = 0;
    }

    private void applyDeadFloatStabilization() {
        Vec3 motion = this.getDeltaMovement();
        double motionY = Mth.clamp(motion.y * 0.55D + computeDeadFluidSurfaceCorrection(0.08D), -0.05D, 0.05D);
        double motionX = motion.x * 0.3D;
        double motionZ = motion.z * 0.3D;

        if (Math.abs(motionX) < DEAD_FLOAT_STOP_EPSILON) {
            motionX = 0.0D;
        }
        if (Math.abs(motionZ) < DEAD_FLOAT_STOP_EPSILON) {
            motionZ = 0.0D;
        }

        this.setDeltaMovement(motionX, motionY, motionZ);
    }

    private double computeDeadFluidSurfaceCorrection(double strength) {
        double surfaceY = getDeadFluidSurfaceY();
        if (Double.isNaN(surfaceY)) {
            return 0.0D;
        }

        double targetY = surfaceY - DEAD_FLOAT_HOVER_OFFSET;
        return Mth.clamp((targetY - this.getY()) * strength, -0.03D, 0.03D);
    }

    private double getDeadFluidSurfaceY() {
        Level level = this.level();
        BlockPos pos = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        FluidState fluid = level.getFluidState(pos);

        if (fluid.isEmpty()) {
            BlockPos below = pos.below();
            fluid = level.getFluidState(below);

            if (fluid.isEmpty()) {
                return Double.NaN;
            }

            pos = below;
        }

        return pos.getY() + fluid.getHeight(level, pos);
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        if (this.level() instanceof ServerLevel serverLevel) {
            clearCompassForcedChunks(serverLevel);
        }
        super.remove(reason);
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
        if (this.isHostileShipMob()) {
            shipTag.putBoolean(TAG_SPAWN_EGG_NO_EXP, true);
        }
        shipTag.putFloat("Health", this.getMaxHealth());
        shipTag.putShort("DeathTime", (short) 0);
        shipTag.putShort("HurtTime", (short) 0);
        egg.set(DataComponents.ENTITY_DATA, CustomData.of(shipTag));
        egg.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
        return egg;
    }

    private void tickFuelDecay() {
        if (this.isHostileShipMob()) {
            return;
        }
        if (this.tickCount % FUEL_DECAY_INTERVAL_TICKS != 0) {
            return;
        }
        if (this.getFuel() <= 0) {
            return;
        }
        this.setFuel(this.getFuel() - FUEL_DECAY_AMOUNT);
    }

    private void tickAutoRecovery() {
        if ((this.tickCount & 0x1F) == 0
                && this.getHealth() < this.getMaxHealth() * AUTO_HEAL_THRESHOLD_RATIO
                && this.getFuel() >= AUTO_HEAL_FAST_FUEL_COST) {
            this.setFuel(this.getFuel() - AUTO_HEAL_FAST_FUEL_COST);
            this.heal(this.getMaxHealth() * AUTO_HEAL_FAST_RATIO + AUTO_HEAL_FAST_FLAT);
        }

        if ((this.tickCount & 0xFF) == 0 && this.getHealth() < this.getMaxHealth()) {
            this.heal(this.getMaxHealth() * AUTO_HEAL_SLOW_RATIO + AUTO_HEAL_SLOW_FLAT);
        }
    }

    private boolean shouldRetreatForLowHealth() {
        int fleeHp = Mth.clamp(this.getStateMinor(ShipContainerMenu.STATE_MINOR_FLEE_HP), 0, 100);
        if (fleeHp <= 0) {
            return false;
        }
        return this.getHealth() <= this.getMaxHealth() * (fleeHp / 100.0F);
    }

    private void tickRetreatMovement() {
        LivingEntity owner = this.getOwner();
        if (owner == null) {
            this.getNavigation().stop();
            return;
        }

        double distanceSqr = this.distanceToSqr(owner);
        if (distanceSqr > 4.0D) {
            this.getNavigation().moveTo(owner, 1.25D);
        } else {
            this.getNavigation().stop();
        }
        this.getLookControl().setLookAt(owner, 30.0F, 30.0F);
    }

    private void tickTimeKeepingSound() {
        if (!this.getStateFlag(ShipContainerMenu.STATE_FLAG_TIMEKEEP) || !this.isAlive() || this.isInDeadPose()) {
            return;
        }
        long worldTime = this.level().getDayTime();
        if (worldTime % TIMEKEEP_INTERVAL_TICKS != 0L) {
            return;
        }

        int hour = (int) ((worldTime / TIMEKEEP_INTERVAL_TICKS) % 24L);
        SoundEvent timeSound = ModSounds.getShipTimeSound(hour);
        if (timeSound != null) {
            this.playSound(timeSound, this.getSoundVolume(), 1.0F);
        }
    }

    void tryFlareTarget(@Nullable Entity target) {
        if (target == null || this.getStateMinor(STATE_MINOR_EQUIP_FLARE) <= 0) {
            return;
        }
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double posX = target.getX();
        double posY = target.getY() + target.getBbHeight() * 0.5D;
        double posZ = target.getZ();
        serverLevel.sendParticles(ParticleTypes.FIREWORK,
                posX, posY, posZ,
                12, 0.5D, 0.6D, 0.5D, 0.05D);

        if (target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.GLOWING,
                    SPECIAL_EQUIP_FLARE_GLOW_TICKS, 0, false, true, true), this);
        }
    }

    private void tickSearchlightAssist() {
        if ((this.tickCount % SEARCHLIGHT_INTERVAL_TICKS) != 0) {
            return;
        }
        if (this.getStateMinor(STATE_MINOR_EQUIP_SEARCHLIGHT) <= 0 || !this.isAlive()) {
            return;
        }
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (this.level().getMaxLocalRawBrightness(this.blockPosition()) >= 10) {
            return;
        }

        this.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,
                SPECIAL_EQUIP_SEARCHLIGHT_NIGHT_VISION_TICKS, 0, true, false, false));
        serverLevel.sendParticles(ParticleTypes.END_ROD,
                this.getX(), this.getY() + 1.2D, this.getZ(),
                3, 0.25D, 0.35D, 0.25D, 0.01D);
    }

    private void tickCompassChunkLoading() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (!this.isAlive() || this.getStateMinor(STATE_MINOR_EQUIP_COMPASS) <= 0) {
            clearCompassForcedChunks(serverLevel);
            return;
        }

        ChunkPos chunkPos = this.chunkPosition();
        boolean movedChunk = chunkPos.x != this.forcedCompassChunkCenterX || chunkPos.z != this.forcedCompassChunkCenterZ;
        if (!movedChunk && (this.tickCount % COMPASS_CHUNK_REFRESH_INTERVAL_TICKS) != 0) {
            return;
        }

        this.forcedCompassChunkCenterX = chunkPos.x;
        this.forcedCompassChunkCenterZ = chunkPos.z;
        updateCompassForcedChunks(serverLevel, chunkPos.x, chunkPos.z);
    }

    private void updateCompassForcedChunks(ServerLevel serverLevel, int centerX, int centerZ) {
        Set<Long> desired = new HashSet<>();
        for (int dx = -COMPASS_CHUNK_RADIUS; dx <= COMPASS_CHUNK_RADIUS; dx++) {
            for (int dz = -COMPASS_CHUNK_RADIUS; dz <= COMPASS_CHUNK_RADIUS; dz++) {
                int cx = centerX + dx;
                int cz = centerZ + dz;
                long key = ChunkPos.asLong(cx, cz);
                desired.add(key);
                if (!this.forcedCompassChunks.contains(key)) {
                    serverLevel.setChunkForced(cx, cz, true);
                }
            }
        }

        if (!this.forcedCompassChunks.isEmpty()) {
            for (long key : new HashSet<>(this.forcedCompassChunks)) {
                if (desired.contains(key)) {
                    continue;
                }
                serverLevel.setChunkForced(ChunkPos.getX(key), ChunkPos.getZ(key), false);
            }
        }

        this.forcedCompassChunks.clear();
        this.forcedCompassChunks.addAll(desired);
    }

    private void clearCompassForcedChunks(ServerLevel serverLevel) {
        if (this.forcedCompassChunks.isEmpty()) {
            this.forcedCompassChunkCenterX = Integer.MIN_VALUE;
            this.forcedCompassChunkCenterZ = Integer.MIN_VALUE;
            return;
        }

        for (long key : new HashSet<>(this.forcedCompassChunks)) {
            serverLevel.setChunkForced(ChunkPos.getX(key), ChunkPos.getZ(key), false);
        }
        this.forcedCompassChunks.clear();
        this.forcedCompassChunkCenterX = Integer.MIN_VALUE;
        this.forcedCompassChunkCenterZ = Integer.MIN_VALUE;
    }

    private void tickAutoPickupItems() {
        if (!this.getStateFlag(ShipContainerMenu.STATE_FLAG_PICK_ITEM)) {
            return;
        }
        if ((this.tickCount % PICK_ITEM_SCAN_INTERVAL_TICKS) != 0) {
            return;
        }
        if (this.getIsSitting() || this.isPassenger() || this.isVehicle() || this.isInDeadPose()) {
            return;
        }
        if (this.hasPointerTargetEntity() || this.getTarget() != null) {
            return;
        }
        if (!hasCargoRoom()) {
            return;
        }

        ItemEntity target = findNearestPickItem();
        if (target == null) {
            return;
        }

        if (this.distanceToSqr(target) <= 9.0D) {
            tryPickupItemEntity(target);
            this.getNavigation().stop();
        } else {
            this.getNavigation().moveTo(target, 1.0D);
        }
    }

    private boolean hasCargoRoom() {
        int slotCount = getAccessibleInventorySlotCount();
        for (int i = ShipInventoryHandler.getEquipSlotCount(); i < slotCount; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                return true;
            }
            int limit = Math.min(stack.getMaxStackSize(), this.inventory.getSlotLimit(i));
            if (stack.getCount() < limit) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    private ItemEntity findNearestPickItem() {
        double followCap = Math.max(2.0D, this.getStateMinor(ShipContainerMenu.STATE_MINOR_FOLLOW_MAX));
        double statRange = Math.max(2.0D, this.getLegacyShipStats().getAttackRange() * 0.5D + 2.0D);
        double pickRange = Math.min(followCap + 2.0D, statRange);

        AABB scanBox = this.getBoundingBox().inflate(pickRange, pickRange * 0.5D + 1.0D, pickRange);
        List<ItemEntity> items = this.level().getEntitiesOfClass(ItemEntity.class, scanBox,
                item -> item.isAlive() && !item.getItem().isEmpty() && !item.hasPickUpDelay());
        if (items.isEmpty()) {
            return null;
        }

        items.sort((a, b) -> Double.compare(this.distanceToSqr(a), this.distanceToSqr(b)));
        return items.get(0);
    }

    private void tryPickupItemEntity(ItemEntity itemEntity) {
        ItemStack stack = itemEntity.getItem();
        if (stack.isEmpty()) {
            return;
        }

        int originalCount = stack.getCount();
        ItemStack remaining = insertIntoCargo(stack.copy());
        int inserted = originalCount - remaining.getCount();
        if (inserted <= 0) {
            return;
        }

        if (remaining.isEmpty()) {
            itemEntity.discard();
        } else {
            itemEntity.setItem(remaining);
        }

        this.playSound(SoundEvents.ITEM_PICKUP, 0.2F,
                ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
    }

    private ItemStack insertIntoCargo(ItemStack stack) {
        ItemStack remaining = stack;
        int slotCount = getAccessibleInventorySlotCount();
        for (int i = ShipInventoryHandler.getEquipSlotCount(); i < slotCount && !remaining.isEmpty(); i++) {
            remaining = this.inventory.insertItem(i, remaining, false);
        }
        return remaining;
    }

    private boolean hasLiquidDrumEquip() {
        int equipSlots = Math.min(ShipInventoryHandler.getEquipSlotCount(), this.inventory.getSlots());
        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = this.inventory.getStackInSlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof LegacyEquipItem equipItem)) {
                continue;
            }
            if (equipItem.getEquipTypeId(stack) == EQUIP_TYPE_DRUM
                    && equipItem.getVariant(stack) == EQUIP_DRUM_VARIANT_LIQUID) {
                return true;
            }
        }
        return false;
    }

    private void tickAutoPump() {
        if (!this.getStateFlag(ShipContainerMenu.STATE_FLAG_AUTO_PUMP)) {
            return;
        }
        if (!hasLiquidDrumEquip()) {
            return;
        }
        if (this.getIsSitting() || this.isPassenger() || this.isVehicle() || this.isInDeadPose()) {
            return;
        }

        tickAutoPumpXp();

        if ((this.tickCount % AUTO_PUMP_INTERVAL_TICKS) != 0) {
            return;
        }

        BlockPos sourcePos = findNearbyPumpSource();
        if (sourcePos == null) {
            return;
        }

        FluidState fluidState = this.level().getFluidState(sourcePos);
        if (!fluidState.is(Fluids.WATER) && !fluidState.is(Fluids.LAVA)) {
            return;
        }

        FluidStack pumpedFluid = new FluidStack(fluidState.getType(), FluidType.BUCKET_VOLUME);

        if (tryStorePumpedFluid(pumpedFluid)) {
            if (this.level() instanceof ServerLevel) {
                this.level().setBlockAndUpdate(sourcePos, Blocks.AIR.defaultBlockState());
            }
            SoundEvent sound = fluidState.is(Fluids.LAVA) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL;
            this.playSound(sound, 0.5F, this.getRandom().nextFloat() * 0.4F + 0.8F);
        }
    }

    @Nullable
    private BlockPos findNearbyPumpSource() {
        BlockPos center = this.blockPosition();
        BlockPos bestPos = null;
        double bestDist = Double.MAX_VALUE;

        for (int dx = -3; dx <= 3; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -3; dz <= 3; dz++) {
                    BlockPos pos = center.offset(dx, dy, dz);
                    FluidState fluidState = this.level().getFluidState(pos);
                    if (fluidState.isEmpty() || !fluidState.isSource()) {
                        continue;
                    }
                    if (!fluidState.is(Fluids.WATER) && !fluidState.is(Fluids.LAVA)) {
                        continue;
                    }
                    double dist = pos.distToCenterSqr(this.position());
                    if (dist < bestDist) {
                        bestDist = dist;
                        bestPos = pos;
                    }
                }
            }
        }

        return bestPos;
    }

    private boolean tryStorePumpedFluid(FluidStack pumpedFluid) {
        int slotCount = getAccessibleInventorySlotCount();
        for (int i = ShipInventoryHandler.getEquipSlotCount(); i < slotCount; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            ItemStack extracted = this.inventory.extractItem(i, 1, false);
            if (extracted.isEmpty()) {
                continue;
            }

            Optional<IFluidHandlerItem> handlerOptional = FluidUtil.getFluidHandler(extracted);
            if (handlerOptional.isEmpty()) {
                ItemStack remainder = this.inventory.insertItem(i, extracted, false);
                if (!remainder.isEmpty() && this.level() instanceof ServerLevel serverLevel) {
                    serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), remainder));
                }
                continue;
            }

            IFluidHandlerItem handler = handlerOptional.get();
            if (handler.fill(pumpedFluid.copy(), IFluidHandler.FluidAction.SIMULATE) < pumpedFluid.getAmount()) {
                ItemStack remainder = this.inventory.insertItem(i, extracted, false);
                if (!remainder.isEmpty() && this.level() instanceof ServerLevel serverLevel) {
                    serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), remainder));
                }
                continue;
            }

            int filled = handler.fill(pumpedFluid.copy(), IFluidHandler.FluidAction.EXECUTE);
            ItemStack container = handler.getContainer();
            ItemStack remaining = this.inventory.insertItem(i, container, false);
            if (!remaining.isEmpty() && this.level() instanceof ServerLevel serverLevel) {
                serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), remaining));
            }
            if (filled >= pumpedFluid.getAmount()) {
                return true;
            }
        }
        return false;
    }

    private void tickAutoPumpXp() {
        if ((this.tickCount % AUTO_PUMP_XP_INTERVAL_TICKS) != 0) {
            return;
        }

        if (!(this.level() instanceof ServerLevel)) {
            return;
        }

        List<ExperienceOrb> orbs = this.level().getEntitiesOfClass(ExperienceOrb.class,
                this.getBoundingBox().inflate(7.0D));
        if (!orbs.isEmpty()) {
            for (ExperienceOrb orb : orbs) {
                if (!orb.isAlive()) {
                    continue;
                }

                double distSqr = this.distanceToSqr(orb);
                if (distSqr > 9.0D) {
                    Vec3 pull = this.position().add(0.0D, 0.4D, 0.0D)
                            .subtract(orb.position())
                            .normalize()
                            .scale(0.25D);
                    orb.setDeltaMovement(orb.getDeltaMovement().add(pull));
                } else {
                    this.setStateMinor(STATE_MINOR_PUMPED_XP, this.getStateMinor(STATE_MINOR_PUMPED_XP) + orb.getValue());
                    orb.discard();
                }
            }
        }

        int bottleSlot = findFirstCargoItem(Items.GLASS_BOTTLE);
        while (bottleSlot >= 0 && this.getStateMinor(STATE_MINOR_PUMPED_XP) >= XP_BOTTLE_COST) {
            ItemStack extracted = this.inventory.extractItem(bottleSlot, 1, false);
            if (extracted.isEmpty()) {
                break;
            }

            this.setStateMinor(STATE_MINOR_PUMPED_XP, this.getStateMinor(STATE_MINOR_PUMPED_XP) - XP_BOTTLE_COST);

            ItemStack remaining = insertIntoCargo(new ItemStack(Items.EXPERIENCE_BOTTLE));
            if (!remaining.isEmpty() && this.level() instanceof ServerLevel serverLevel) {
                serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), remaining));
            }

            bottleSlot = findFirstCargoItem(Items.GLASS_BOTTLE);
        }
    }

    private int findFirstCargoItem(Item item) {
        int slotCount = getAccessibleInventorySlotCount();
        for (int i = ShipInventoryHandler.getEquipSlotCount(); i < slotCount; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.is(item)) {
                return i;
            }
        }
        return -1;
    }

    private void tickAutoRation() {
        if ((this.tickCount % AUTO_RATION_INTERVAL_TICKS) != 0) {
            return;
        }

        int threshold = Mth.clamp(this.getStateMinor(ShipContainerMenu.STATE_MINOR_RATION_MORALE), 1, 4);
        if (getMoraleLevelLegacy(this.getMorale()) < threshold) {
            return;
        }

        if (this.getFuel() >= AUTO_RATION_MAX_FUEL && this.getHealth() >= this.getMaxHealth()) {
            return;
        }

        consumeOneCombatRation();
    }

    private int getMoraleLevelLegacy(int morale) {
        if (morale > 5100) {
            return 0;
        }
        if (morale > 3900) {
            return 1;
        }
        if (morale > 2100) {
            return 2;
        }
        if (morale > 900) {
            return 3;
        }
        return 4;
    }

    private boolean consumeOneCombatRation() {
        int slotCount = getAccessibleInventorySlotCount();
        for (int i = ShipInventoryHandler.getEquipSlotCount(); i < slotCount; i++) {
            ItemStack stack = this.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            if (!(stack.getItem() instanceof CombatRationItem combatRationItem)) {
                continue;
            }

            int variant = combatRationItem.getVariant(stack);
            applyCombatRationEffect(variant);
            stack.shrink(1);
            if (stack.isEmpty()) {
                this.inventory.setStackInSlot(i, ItemStack.EMPTY);
            } else {
                this.inventory.setStackInSlot(i, stack);
            }

            return true;
        }

        return false;
    }

    private boolean consumeCombatRationInHand(ItemStack stack, Player player) {
        if (!(stack.getItem() instanceof CombatRationItem combatRationItem)) {
            return false;
        }

        applyCombatRationEffect(combatRationItem.getVariant(stack));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return true;
    }

    private void applyCombatRationEffect(int variant) {
        int fuelGain = CombatRationItem.rollFuelGain(this.getRandom(), variant);
        this.setFuel(Math.min(AUTO_RATION_MAX_FUEL, this.getFuel() + fuelGain));
        this.addMorale(CombatRationItem.getMoraleValue(variant));

        if (this.getHealth() < this.getMaxHealth()) {
            this.heal(Math.max(2.0F, this.getMaxHealth() * 0.03F));
        }

        if (this.feedSoundCooldown <= 0) {
            this.playSound(ModSounds.SHIP_FEED.get(), this.getSoundVolume(), this.getShipSoundPitch());
            this.feedSoundCooldown = 30;
        }

        this.applyParticleEmotion(switch (this.getRandom().nextInt(3)) {
            case 1 -> EmotionParticleType.DROOL;
            case 2 -> EmotionParticleType.SIGH;
            default -> EmotionParticleType.HEART;
        });
        this.setEmotionPrimary(EMOTION_HAPPY);
        this.resetInteractionEmotionState();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        ShipLegacyNavigation navigation = new ShipLegacyNavigation(this, level);
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
            if (stack.is(ModItems.TRAINING_BOOK.get())) {
                return InteractionResult.PASS;
            }

            if (stack.getItem() instanceof CombatRationItem) {
                if (consumeCombatRationInHand(stack, player)) {
                    this.focusOnPlayer(player);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }

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
        if (!this.level().isClientSide) {
            this.addShipExp(Config.shipExpGainMelee);
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
        if (source.is(DamageTypeTags.IS_FIRE)) {
            return false;
        }

        if (!this.level().isClientSide && tryLegacyDodge(source)) {
            return false;
        }

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

    private boolean tryLegacyDodge(DamageSource source) {
        Entity attacker = source.getEntity();
        if (attacker == null || attacker == this) {
            return false;
        }
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }

        float dodge = Mth.clamp(this.legacyShipStats.getBuffedAttr(15), 0.0F, 0.9F);
        if (dodge <= 0.0F || this.getRandom().nextFloat() > dodge) {
            return false;
        }

        this.spawnCombatTextParticle(COMBAT_TEXT_DODGE);
        return true;
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
        this.goalSelector.addGoal(1, new EntityShipPointerMoveGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new EntityShipFollowOwnerGoal(this, 1.2D, 16.0F, 4.0F));
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
        this.legacyShipStats.recalculate(
                this.getStateMinor(STATE_MINOR_SHIP_CLASS),
                this.getLevel(),
                this.collectEquipBonuses());

        if (this.level() != null && !this.level().isClientSide) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.legacyShipStats.getMaxHealth());
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.legacyShipStats.getFirepower() * LEGACY_MELEE_DAMAGE_FACTOR);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.legacyShipStats.getMoveSpeed()*CRUISE_SPEED_FACTOR);
            this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(Math.max(24.0D, this.legacyShipStats.getAttackRange()));
            if (this.getHealth() > this.getMaxHealth()) {
                this.setHealth(this.getMaxHealth());
            }
        }
    }

    private float[] collectEquipBonuses() {
        float[] equipBonuses = new float[LegacyEquipStats.ATTR_COUNT];
        int equipSlots = Math.min(ShipInventoryHandler.getEquipSlotCount(), this.inventory.getSlots());
        int drumCount = 0;
        int compassCount = 0;
        int flareCount = 0;
        int searchlightCount = 0;

        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = this.inventory.getStackInSlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof LegacyEquipItem equipItem)) {
                continue;
            }

            int equipTypeId = equipItem.getEquipTypeId(stack);
            switch (equipTypeId) {
                case EQUIP_TYPE_DRUM -> drumCount++;
                case EQUIP_TYPE_COMPASS -> compassCount++;
                case EQUIP_TYPE_FLARE -> flareCount++;
                case EQUIP_TYPE_SEARCHLIGHT -> searchlightCount++;
                default -> {
                }
            }

            float[] stats = LegacyEquipStats.getMainAttrs(equipItem.getEquipId(stack));
            if (stats == null) {
                continue;
            }

            int len = Math.min(equipBonuses.length, stats.length);
            for (int i = 0; i < len; i++) {
                equipBonuses[i] += stats[i];
            }
        }

        this.setStateMinor(STATE_MINOR_EQUIP_DRUM, drumCount);
        this.setStateMinor(STATE_MINOR_EQUIP_COMPASS, compassCount);
        this.setStateMinor(STATE_MINOR_EQUIP_FLARE, flareCount);
        this.setStateMinor(STATE_MINOR_EQUIP_SEARCHLIGHT, searchlightCount);

        return equipBonuses;
    }

    protected void setFaceNormal() {
        this.faceExpressions.setFaceNormal();
    }

    protected void setFaceCry() {
        this.faceExpressions.setFaceCry();
    }

    protected void setFaceScornOrDamaged() {
        this.faceExpressions.setFaceScornOrDamaged();
    }

    protected void setFaceScorn() {
        this.faceExpressions.setFaceScorn();
    }

    protected void setFaceDamaged() {
        this.faceExpressions.setFaceDamaged();
    }

    protected void setFaceHungry() {
        this.faceExpressions.setFaceHungry();
    }

    protected void setFaceAngry() {
        this.faceExpressions.setFaceAngry();
    }

    protected void setFaceBored() {
        this.faceExpressions.setFaceBored();
    }

    protected void setFaceShy() {
        this.faceExpressions.setFaceShy();
    }

    protected void setFaceHappy() {
        this.faceExpressions.setFaceHappy();
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

    void initializeLegacyStateInternal() {
        initializeLegacyState();
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

    int[] getLegacyEmotionSnapshotInternal() {
        return getLegacyEmotionSnapshot();
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

    void applyLegacyEmotionSnapshotInternal(int[] legacy) {
        applyLegacyEmotionSnapshot(legacy);
    }

    boolean isLegacyStateInitializedInternal() {
        return this.legacyStateInitialized;
    }

    void setLegacyStateInitializedInternal(boolean initialized) {
        this.legacyStateInitialized = initialized;
    }

    void resetDeathStateForSpawnEgg() {
        this.setHealth(this.getMaxHealth());
        this.deathTime = 0;
        this.shipDeathTicks = 0;
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

    public void applyEmotesReaction(int type) {
        this.reactions.applyEmotesReaction(type);
    }

    public void applyParticleEmotion(EmotionParticleType type) {
        this.reactions.applyParticleEmotion(type);
    }

    public void applyParticleEmotion(int typeId) {
        this.reactions.applyParticleEmotion(typeId);
    }

    public void spawnCombatTextParticle(int type) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        int clampedType = Mth.clamp(type, COMBAT_TEXT_MISS, COMBAT_TEXT_DODGE);
        serverLevel.sendParticles(
                ModParticles.PARTICLE_TEXTS.get(),
                this.getX(),
            this.getY() + this.getBbHeight() * 1.3D,
                this.getZ(),
                0,
                clampedType,
            0.08D,
            Math.max(0.2D, this.getBbWidth() * 0.45D),
                1.0D
        );
    }

    void setEmotionParticlePacked(int packed) {
        this.entityData.set(EMOTION_PARTICLE, packed);
    }

    public record EquipOption(String key, String labelKey) {
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }
}