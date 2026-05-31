package org.trp.shincolle.entity.base;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
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
import org.trp.shincolle.entity.EntityNorthernHime;
import org.trp.shincolle.entity.EntityShipFishingHook;
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
import org.trp.shincolle.utility.BlockHelper;

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

    private static final int FUEL_DECAY_AMOUNT = 1;
    private static final int MAX_FUEL = 10000;
    private static final int MORALE_MAX = 16000;
    private static final int MORALE_DEFAULT = 4000;
    static final float CRUISE_SPEED_FACTOR = 0.3F;
    private static final float AUTO_HEAL_THRESHOLD_RATIO = 0.9F;
    private static final float AUTO_HEAL_FAST_RATIO = 0.08F;
    private static final float AUTO_HEAL_FAST_FLAT = 15.0F;
    private static final int AUTO_HEAL_FAST_FUEL_COST = 7;
    private static final float AUTO_HEAL_SLOW_RATIO = 0.03F;
    private static final float AUTO_HEAL_SLOW_FLAT = 1.0F;
    static final float LEGACY_MELEE_DAMAGE_FACTOR = 0.125F;
    private static final float PICK_RADIUS_MODEL_SCALE = 0.012F;
    private static final float PICK_RADIUS_MIN = 0.05F;
    private static final float PICK_RADIUS_MAX = 0.20F;
    static final int SHIP_DEATH_MAX_TICKS = 300;
    private static final int SHIP_LEVEL_HARD_CAP = 150;
    static final double DEAD_FLOAT_HOVER_OFFSET = 0.08D;
    static final double DEAD_FLOAT_STOP_EPSILON = 0.003D;
    static final long TIMEKEEP_INTERVAL_TICKS = 1000L;
    static final int PICK_ITEM_SCAN_INTERVAL_TICKS = 16;
    static final int AUTO_PUMP_INTERVAL_TICKS = 40;
    static final int AUTO_PUMP_XP_INTERVAL_TICKS = 4;
    private static final int AUTO_RATION_INTERVAL_TICKS = 128;
    private static final int AUTO_RATION_MAX_FUEL = 100;
    private static final int COMPASS_CHUNK_REFRESH_INTERVAL_TICKS = 40;
    private static final int COMPASS_CHUNK_RADIUS = 1;
    private static final int SPECIAL_EQUIP_FLARE_GLOW_TICKS = 80;
    static final int XP_BOTTLE_COST = 8;
    private static final int HOSTILE_LIGHT_AMMO_CONTAINER_COUNT = 16;
    private static final int HOSTILE_HEAVY_AMMO_CONTAINER_COUNT = 12;
    public static final int KAITAI_AMOUNT_SMALL = 20;
    public static final int KAITAI_AMOUNT_LARGE = 20;
    static final String TAG_SPAWN_EGG = "ShincolleSpawnEgg";
    static final String TAG_SPAWN_EGG_NO_EXP = "ShincolleSpawnEggNoExpCost";

    public static final int STATE_MINOR_FACTION_ID = 19;
    public static final int STATE_MINOR_SHIP_CLASS = 20;
    public static final int STATE_MINOR_SPECIAL_EQUIP = 25;
    public static final int STATE_MINOR_GRUDGE_CONSUMPTION = 28;
    public static final int STATE_MINOR_RARITY = 13;
    protected static final int STATE_MINOR_EQUIP_DRUM = 36;
    static final int STATE_MINOR_EQUIP_COMPASS = 37;
    static final int STATE_MINOR_EQUIP_FLARE = 38;
    static final int STATE_MINOR_EQUIP_SEARCHLIGHT = 39;
    public static final int STATE_MINOR_PUMPED_XP = 42;
    public static final int STATE_MINOR_GUARD_X = 14;
    public static final int STATE_MINOR_GUARD_Y = 15;
    public static final int STATE_MINOR_GUARD_Z = 16;
    public static final int STATE_MINOR_GUARD_DIM = 17;
    public static final int STATE_MINOR_GUARD_TYPE = 18;
    public static final int STATE_MINOR_CRANING = 43;

    private static final int HELD_MAINHAND_SLOT = 22;
    private static final int HELD_OFFHAND_SLOT = 23;

    public static final int EQUIP_TYPE_DRUM = 24;
    public static final int EQUIP_TYPE_COMPASS = 25;
    public static final int EQUIP_TYPE_FLARE = 26;
    public static final int EQUIP_TYPE_SEARCHLIGHT = 27;
    public static final int EQUIP_TYPE_AMMO = 28;
    public static final int EQUIP_TYPE_AMMO_2 = 29;
    public static final int EQUIP_DRUM_VARIANT_LIQUID = 1;

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
    public static final int STATE_FLAG_DISABLE_GUARD_POS = 11;

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

    protected static final EntityDataAccessor<Integer> SHIP_LEVEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> SHIP_EXP =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    protected static final EntityDataAccessor<Integer> FACE_ID =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Boolean> POINTER_SELECTED =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.BOOLEAN
        );
    protected static final EntityDataAccessor<Integer> MOUTH_ID =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    protected static final EntityDataAccessor<Integer> EMOTION_PRIMARY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> EMOTION_SECONDARY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> EMOTION_PARTICLE =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Boolean> NO_FUEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.BOOLEAN
        );
    protected static final EntityDataAccessor<Integer> MORALE =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> FORMATION_TEAM =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> FORMATION_SLOT =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    protected static final EntityDataAccessor<Integer> FUEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    protected static final EntityDataAccessor<Integer> AMMO_LIGHT =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> AMMO_HEAVY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    protected static final EntityDataAccessor<Integer> AIRCRAFT_LIGHT =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> AIRCRAFT_HEAVY =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    protected static final EntityDataAccessor<CompoundTag> EQUIP_FLAGS =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.COMPOUND_TAG
        );
    public static final String EQUIP_MOUNT = "equip_mount";

    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_0 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_1 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_2 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_3 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_4 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_5 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_6 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_EMOTION_7 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );

    protected static final EntityDataAccessor<Integer> LEGACY_ATTACK_TICK =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_ATTACK_TICK_2 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_RIDING_STATE =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> LEGACY_SCALE_LEVEL =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_0 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_1 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_2 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<Integer> MOUNT_ATTACK_CD_3 =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.INT
        );
    protected static final EntityDataAccessor<CompoundTag> POINTER_TARGET_DATA =
        SynchedEntityData.defineId(
            EntityShipBase.class,
            EntityDataSerializers.COMPOUND_TAG
        );

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
    private final EntityShipBaseInventoryHelper inventoryHelper;
    private final EntityShipBaseSupplies suppliesHelper;
    private final EntityShipBaseKaitai kaitaiHelper;
    private final EntityShipBaseCompass compassHelper;
    private final EntityShipBaseStatsHelper statsHelper;
    final EntityShipBaseMovementHelper movementHelper;
    private final EntityShipBaseDeathHelper deathHelper;
    final EntityShipBaseAudioHelper audioHelper;
    private EntityShipFishingHook fishHook;
    private boolean legacyStateInitialized = false;
    int shipDeathTicks = 0;
    private boolean hostileCanDrop = true;
    private int stateUpdateTimer;
    private int customHurtTime;
    int hurtSoundCooldown;
    protected int combatTick = 0;
    public int customSwingTicks = 0;
    public boolean isCustomSwinging = false;
    public static final int MAX_SWING_TICKS = 6;

    protected EntityShipBase(
        EntityType<? extends TamableAnimal> type,
        Level level
    ) {
        super(type, level);
        this.inventory = new ShipInventoryHandler(this, 60);
        this.combat = new EntityShipBaseCombat(this);
        this.pointer = new EntityShipBasePointer(this);
        this.emotions = new EntityShipBaseEmotions(this);
        this.faceExpressions = new EntityShipBaseFaceExpressions(
            this,
            this.emotions
        );
        this.reactions = new EntityShipBaseReactions(this);
        this.passiveCombat = new EntityShipBasePassiveCombat(this);
        this.serialization = new EntityShipBaseSerialization(this);
        this.legacyShipStats = new LegacyShipStats();
        this.legacyState = new EntityShipLegacyState();
        this.inventoryHelper = new EntityShipBaseInventoryHelper(this);
        this.suppliesHelper = new EntityShipBaseSupplies(this);
        this.kaitaiHelper = new EntityShipBaseKaitai(this);
        this.compassHelper = new EntityShipBaseCompass(this);
        this.statsHelper = new EntityShipBaseStatsHelper(this);
        this.movementHelper = new EntityShipBaseMovementHelper(this);
        this.deathHelper = new EntityShipBaseDeathHelper(this);
        this.audioHelper = new EntityShipBaseAudioHelper(this);
        this.moveControl = new ShipMoveControl(this, 30.0F);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.LAVA, 0.0F);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
        setStateMinor(
            STATE_MINOR_GRUDGE_CONSUMPTION,
            org.trp.shincolle.Config.fuelConsumeDD
        );
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
        builder.define(POINTER_TARGET_DATA, new CompoundTag());
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
        this.entityData.set(
            SHIP_LEVEL,
            Mth.clamp(level, 1, SHIP_LEVEL_HARD_CAP)
        );
        this.recalculateLegacyShipStats();
    }

    public int getExp() {
        return this.entityData.get(SHIP_EXP);
    }

    public void setExp(int exp) {
        this.entityData.set(SHIP_EXP, Math.max(0, exp));
    }

    public int getMaxShipLevel() {
        int configured = this.isStateMarried()
            ? Config.shipMaxLevelMarried
            : Config.shipMaxLevelNormal;
        return Mth.clamp(configured, 1, SHIP_LEVEL_HARD_CAP);
    }

    public int getExpToNextLevel() {
        int level = Math.max(1, this.getLevel());
        return Math.max(
            1,
            level * Config.shipExpModifier + Config.shipExpModifier
        );
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
            int expNext = Math.max(
                1,
                level * Config.shipExpModifier + Config.shipExpModifier
            );
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
        return this.entityData.get(AIRCRAFT_LIGHT);
    }

    public int getNumAircraftHeavy() {
        return this.entityData.get(AIRCRAFT_HEAVY);
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
        if (this.getGuardedPos(4) == 1) {
            this.setGuardedPos(
                this.getGuardedPos(0),
                this.getGuardedPos(1),
                this.getGuardedPos(2),
                this.getGuardedPos(3),
                0
            );
        }
        this.pointer.setPointerTarget(target, durationTicks);
    }

    public boolean hasPointerTarget() {
        return this.pointer.hasPointerTarget();
    }

    public Vec3 getPointerTarget() {
        return this.pointer.getPointerTarget();
    }

    public Vec3 getRawPointerTarget() {
        return this.pointer.getRawPointerTarget();
    }

    public long getPointerTargetRemainingTicks() {
        return this.pointer.getPointerTargetRemainingTicks();
    }

    public void clearPointerTarget() {
        this.pointer.clearPointerTarget();
    }

    public void setPointerTargetEntity(Entity target, long durationTicks) {
        if (this.getGuardedPos(4) == 1) {
            this.setGuardedPos(
                this.getGuardedPos(0),
                this.getGuardedPos(1),
                this.getGuardedPos(2),
                this.getGuardedPos(3),
                0
            );
        }
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
        this.entityData.set(
            MOUTH_ID,
            Mth.clamp(id, MOUTH_ID_MIN, MOUTH_ID_MAX)
        );
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
            this.reactions.spawnEmotionParticleClient(
                EmotionParticleType.fromId(typeId)
            );
        }
    }

    public int getMorale() {
        return this.entityData.get(MORALE);
    }

    public void setMorale(int val) {
        this.entityData.set(MORALE, Mth.clamp(val, 0, MORALE_MAX));
        if (!this.level().isClientSide) {
            this.recalculateLegacyShipStats();
        }
    }

    public void addMorale(int delta) {
        this.setMorale(this.getMorale() + delta);
    }

    public int getFormationTeam() {
        return this.entityData.get(FORMATION_TEAM);
    }

    public void setFormationTeam(int team) {
        this.entityData.set(FORMATION_TEAM, team);
    }

    public int getFormationSlot() {
        return this.entityData.get(FORMATION_SLOT);
    }

    public void setFormationSlot(int slot) {
        this.entityData.set(FORMATION_SLOT, slot);
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

    public int getCombatTick() {
        return this.combatTick;
    }

    public void setCombatTick(int val) {
        this.combatTick = val;
    }

    public boolean isOutOfCombat() {
        return this.tickCount - this.combatTick > 128;
    }

    protected void updateFuelState(boolean nofuel) {}

    public boolean hasShipMounts() {
        return false;
    }

    public boolean canSummonMounts() {
        return (this.getStateEmotion(0) & 1) == 1 && !this.isInDeadPose();
    }

    public EntityMountBase summonMountEntity() {
        return null;
    }

    protected void updateMountSummon() {
        if (!this.level().isClientSide) {
            if (
                this.hasShipMounts() &&
                this.canSummonMounts() &&
                !this.isPassenger()
            ) {
                EntityMountBase mount = this.summonMountEntity();
                if (mount != null) {
                    mount.setHostUUID(this.getUUID());
                    mount.moveTo(
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        this.getYRot(),
                        this.getXRot()
                    );
                    this.level().addFreshEntity(mount);
                    this.getPassengers().forEach(Entity::stopRiding);
                    this.startRiding(mount, true);
                }
            } else if (
                this.isPassenger() &&
                this.getVehicle() instanceof EntityMountBase
            ) {
                if (!this.canSummonMounts()) {
                    this.stopRiding();
                }
            }
        }
    }

    public boolean isInDeadPose() {
        return (
            this.isDeadOrDying() || this.getHealth() <= 0.0F || this.isNoFuel()
        );
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
        this.entityData.set(
            LEGACY_ATTACK_TICK,
            Mth.clamp(value, 0, LEGACY_ATTACK_TICK_MAX)
        );
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

    boolean isCombatSuppressed() {
        return (
            this.getIsSitting() || this.getStateMinor(STATE_MINOR_CRANING) > 0
        );
    }

    public boolean getIsSprinting() {
        return this.isSprinting() || this.walkAnimation.speed() > 0.9F;
    }

    protected static boolean checkModelState(int id, int state) {
        return ShipUtils.checkModelState(id, state);
    }

    protected static float[] rotateXZByAxis(
        float z,
        float x,
        float radians,
        float scale
    ) {
        return ShipUtils.rotateXZByAxis(z, x, radians, scale);
    }

    protected Player getOwnerPlayer() {
        LivingEntity owner = this.getOwner();
        return owner instanceof Player player ? player : null;
    }

    public boolean playerHasCombatRation(Player player) {
        if (player == null) return false;
        ItemStack mainHand = player.getMainHandItem();
        if (
            !mainHand.isEmpty() &&
            mainHand.getItem() instanceof CombatRationItem
        ) return true;
        ItemStack offHand = player.getOffhandItem();
        return (
            !offHand.isEmpty() && offHand.getItem() instanceof CombatRationItem
        );
    }

    public boolean shouldFollowOwner() {
        return this.movementHelper.shouldFollowOwner();
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

    public EntityType<? extends TamableAnimal> getAttackAircraftType(
        boolean isLightAircraft
    ) {
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

    public void executeMountLightAttack(Entity target) {
        this.performLightAttack(target);
    }

    protected void spawnLightAttackMuzzleParticles(
        ServerLevel serverLevel,
        Entity target
    ) {
        Vec3 from = this.position().add(0.0D, 0.8D, 0.0D);
        Vec3 to = target
            .position()
            .add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        Vec3 look = to.subtract(from);
        if (look.lengthSqr() < 1.0E-6D) {
            look = this.getLookAngle();
        } else {
            look = look.normalize();
        }

        double posX = this.getX();
        double posY = this.getY();
        double posZ = this.getZ();

        for (int i = 0; i < 24; ++i) {
            double ran1 = this.getRandom().nextFloat() - 0.5F;
            double ran2 = this.getRandom().nextFloat();
            double ran3 = this.getRandom().nextFloat();
            double baseX = posX + look.x - 0.5D + 0.05D * i;
            double baseZ = posZ + look.z - 0.5D + 0.05D * i;

            serverLevel.sendParticles(
                ParticleTypes.LARGE_SMOKE,
                baseX,
                posY + 0.6D + ran1,
                baseZ,
                1,
                look.x * 0.3D * ran2,
                0.05D * ran2,
                look.z * 0.3D * ran2,
                0.0D
            );
            serverLevel.sendParticles(
                ParticleTypes.LARGE_SMOKE,
                baseX,
                posY + 1.0D + ran1,
                baseZ,
                1,
                look.x * 0.3D * ran3,
                0.05D * ran3,
                look.z * 0.3D * ran3,
                0.0D
            );
        }
    }

    protected void spawnLightAttackTargetParticles(
        ServerLevel serverLevel,
        Entity target
    ) {
        double posX = target.getX();
        double posY = target.getY();
        double posZ = target.getZ();

        serverLevel.sendParticles(
            ParticleTypes.EXPLOSION_EMITTER,
            posX,
            posY + 1.5D,
            posZ,
            1,
            0.0D,
            0.0D,
            0.0D,
            0.0D
        );

        for (int i = 0; i < 15; ++i) {
            double ran1 = (this.getRandom().nextFloat() * 3.0F) - 1.5F;
            double ran2 = (this.getRandom().nextFloat() * 3.0F) - 1.5F;
            serverLevel.sendParticles(
                ParticleTypes.LAVA,
                posX + ran1,
                posY + 1.0D,
                posZ + ran2,
                1,
                0.0D,
                0.0D,
                0.0D,
                0.0D
            );
        }
    }

    protected boolean performHeavyAttack(Entity target) {
        return this.combat.performHeavyAttack(target);
    }

    protected boolean performHeavyAttack(Vec3 targetPos) {
        return this.combat.performHeavyAttack(targetPos);
    }

    boolean performLightAircraftAttack(Entity target) {
        return this.combat.performLightAircraftAttack(target);
    }

    boolean performHeavyAircraftAttack(Entity target) {
        return this.combat.performHeavyAircraftAttack(target);
    }

    public boolean executeMountHeavyAttack(Entity target) {
        return this.performHeavyAttack(target);
    }

    public boolean executeMountHeavyAttack(Vec3 targetPos) {
        return this.performHeavyAttack(targetPos);
    }

    public boolean executeMountLightAircraftAttack(Entity target) {
        return this.combat.performLightAircraftAttackManual(target);
    }

    public boolean executeMountHeavyAircraftAttack(Entity target) {
        return this.combat.performHeavyAircraftAttackManual(target);
    }

    public int getStateMinor(int index) {
        if (index == 6) {
            return this.getFuel();
        }
        return legacyState.getInt(legacyState.stateMinor, index);
    }

    public void setStateMinor(int index, int value) {
        if (index == 6) {
            this.setFuel(value);
            return;
        }
        if (index == 7) {
            this.entityData.set(AIRCRAFT_LIGHT, value);
        } else if (index == 8) {
            this.entityData.set(AIRCRAFT_HEAVY, value);
        }

        legacyState.setInt(legacyState.stateMinor, index, value);
        if (index == STATE_MINOR_SHIP_CLASS) {
            this.recalculateLegacyShipStats();
        }
    }

    public int[] getGuardedPos() {
        return new int[] {
            getStateMinor(STATE_MINOR_GUARD_X),
            getStateMinor(STATE_MINOR_GUARD_Y),
            getStateMinor(STATE_MINOR_GUARD_Z),
            getStateMinor(STATE_MINOR_GUARD_DIM),
            getStateMinor(STATE_MINOR_GUARD_TYPE),
        };
    }

    public void setGuardedPos(int x, int y, int z, int dim, int type) {
        this.setStateMinor(STATE_MINOR_GUARD_X, x);
        this.setStateMinor(STATE_MINOR_GUARD_Y, y);
        this.setStateMinor(STATE_MINOR_GUARD_Z, z);
        this.setStateMinor(STATE_MINOR_GUARD_DIM, dim);
        this.setStateMinor(STATE_MINOR_GUARD_TYPE, type);
    }

    public int getStateTimer(int index) {
        if (index >= 16 && index <= 19) {
            
            return switch (index) {
                case 16 -> this.entityData.get(MOUNT_ATTACK_CD_0);
                case 17 -> this.entityData.get(MOUNT_ATTACK_CD_1);
                case 18 -> this.entityData.get(MOUNT_ATTACK_CD_2);
                case 19 -> this.entityData.get(MOUNT_ATTACK_CD_3);
                default -> legacyState.getInt(legacyState.stateTimer, index);
            };
        }
        return legacyState.getInt(legacyState.stateTimer, index);
    }

    public void setStateTimer(int index, int value) {
        legacyState.setInt(legacyState.stateTimer, index, value);
        if (!this.level().isClientSide) {
            switch (index) {
                case 16 -> this.entityData.set(MOUNT_ATTACK_CD_0, value);
                case 17 -> this.entityData.set(MOUNT_ATTACK_CD_1, value);
                case 18 -> this.entityData.set(MOUNT_ATTACK_CD_2, value);
                case 19 -> this.entityData.set(MOUNT_ATTACK_CD_3, value);
            }
        }
    }

    public boolean getStateFlag(int index) {
        return legacyState.getBoolean(legacyState.stateFlag, index);
    }

    public byte getStateFlagI(int index) {
        return legacyState.getBoolean(legacyState.stateFlag, index)
            ? (byte) 1
            : (byte) 0;
    }

    public void setStateFlag(int index, boolean value) {
        legacyState.setBoolean(legacyState.stateFlag, index, value);
    }

    public void setStateFlagI(int index, int value) {
        legacyState.setBoolean(legacyState.stateFlag, index, value > 0);
    }

    public boolean isStateMarried() {
        return getStateFlag(STATE_FLAG_MARRIED);
    }

    public void setStateMarried(boolean value) {
        setStateFlag(STATE_FLAG_MARRIED, value);
    }

    public boolean isStateNoEquip() {
        return getStateFlag(STATE_FLAG_NO_EQUIP);
    }

    public void setStateNoEquip(boolean value) {
        setStateFlag(STATE_FLAG_NO_EQUIP, value);
    }

    public boolean isStateCanMelee() {
        return getStateFlag(STATE_FLAG_CAN_MELEE);
    }

    public void setStateCanMelee(boolean value) {
        setStateFlag(STATE_FLAG_CAN_MELEE, value);
    }

    public boolean isStateLightAttack() {
        return getStateFlag(STATE_FLAG_LIGHT_ATTACK);
    }

    public void setStateLightAttack(boolean value) {
        setStateFlag(STATE_FLAG_LIGHT_ATTACK, value);
    }

    public boolean isStateHeavyAttack() {
        return getStateFlag(STATE_FLAG_HEAVY_ATTACK);
    }

    public void setStateHeavyAttack(boolean value) {
        setStateFlag(STATE_FLAG_HEAVY_ATTACK, value);
    }

    public boolean isStateLightAircraftAttack() {
        return getStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK);
    }

    public void setStateLightAircraftAttack(boolean value) {
        setStateFlag(STATE_FLAG_LIGHT_AIRCRAFT_ATTACK, value);
    }

    public boolean isStateHeavyAircraftAttack() {
        return getStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK);
    }

    public void setStateHeavyAircraftAttack(boolean value) {
        setStateFlag(STATE_FLAG_HEAVY_AIRCRAFT_ATTACK, value);
    }

    public boolean isStateRingEffect() {
        return getStateFlag(STATE_FLAG_RING_EFFECT);
    }

    public void setStateRingEffect(boolean value) {
        setStateFlag(STATE_FLAG_RING_EFFECT, value);
    }

    public boolean isStateGuiBtn1() {
        if (
            this instanceof org.trp.shincolle.entity.EntityTransportWa
        ) return false;
        if (
            this instanceof org.trp.shincolle.entity.EntityCarrierAkagi
        ) return false;
        if (
            this instanceof org.trp.shincolle.entity.EntityCarrierKaga
        ) return false;
        if (
            this instanceof org.trp.shincolle.entity.EntityCarrierWo
        ) return false;
        if (
            this instanceof org.trp.shincolle.entity.EntityCarrierHime
        ) return false;
        return true;
    }

    public void setStateGuiBtn1(boolean value) {
        setStateFlag(STATE_FLAG_GUI_BTN_1, value);
    }

    public boolean isStateGuiBtn2() {
        if (
            this instanceof org.trp.shincolle.entity.EntityTransportWa
        ) return false;
        if (this.supportsAircraftCombat()) return false;
        return true;
    }

    public void setStateGuiBtn2(boolean value) {
        setStateFlag(STATE_FLAG_GUI_BTN_2, value);
    }

    public boolean isStateGuiBtn3() {
        return this.supportsAircraftCombat();
    }

    public void setStateGuiBtn3(boolean value) {
        setStateFlag(STATE_FLAG_GUI_BTN_3, value);
    }

    public boolean isStateGuiBtn4() {
        return this.supportsAircraftCombat();
    }

    public void setStateGuiBtn4(boolean value) {
        setStateFlag(STATE_FLAG_GUI_BTN_4, value);
    }

    public boolean isStateAntiAir() {
        return getStateFlag(STATE_FLAG_ANTI_AIR);
    }

    public void setStateAntiAir(boolean value) {
        setStateFlag(STATE_FLAG_ANTI_AIR, value);
    }

    public boolean isStateCanRide() {
        return getStateFlag(STATE_FLAG_CAN_RIDE);
    }

    public void setStateCanRide(boolean value) {
        setStateFlag(STATE_FLAG_CAN_RIDE, value);
    }

    public boolean isStateAppearance() {
        return getStateFlag(STATE_FLAG_APPEARANCE);
    }

    public void setStateAppearance(boolean value) {
        setStateFlag(STATE_FLAG_APPEARANCE, value);
    }

    public boolean canShowHeldItem() {
        return (
            this.isStateAppearance() &&
            this.getAttackTick() <= 0 &&
            this.getAttackTick2() <= 0
        );
    }

    public ItemStack getHeldItemMainhandSlot() {
        if (
            this.inventory == null ||
            HELD_MAINHAND_SLOT >= this.inventory.getSlots()
        ) {
            return ItemStack.EMPTY;
        }
        return this.inventory.getStackInSlot(HELD_MAINHAND_SLOT);
    }

    public ItemStack getHeldItemOffhandSlot() {
        if (
            this.inventory == null ||
            HELD_OFFHAND_SLOT >= this.inventory.getSlots()
        ) {
            return ItemStack.EMPTY;
        }
        return this.inventory.getStackInSlot(HELD_OFFHAND_SLOT);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            if (!canShowHeldItem()) {
                return ItemStack.EMPTY;
            }
            if (this.level().isClientSide) {
                return super.getItemBySlot(slot);
            }
            return slot == EquipmentSlot.MAINHAND
                ? getHeldItemMainhandSlot()
                : getHeldItemOffhandSlot();
        }
        return super.getItemBySlot(slot);
    }

    public boolean isSubmarine() {
        return false;
    }

    @Override
    public boolean isCurrentlyGlowing() {
        if (
            this.level().isClientSide &&
            this.isSubmarine() &&
            this.isStateRingEffect() &&
            this.isInvisible()
        ) {
            return isLocalPlayerOwner();
        }
        return super.isCurrentlyGlowing();
    }

    private boolean isLocalPlayerOwner() {
        return org.trp.shincolle.client.ClientProxy.isLocalPlayerOwner(this);
    }

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

    public int getGuardedPos(int index) {
        return switch (index) {
            case 0 -> this.getStateMinor(ShipContainerMenu.STATE_MINOR_GUARD_X);
            case 1 -> this.getStateMinor(ShipContainerMenu.STATE_MINOR_GUARD_Y);
            case 2 -> this.getStateMinor(ShipContainerMenu.STATE_MINOR_GUARD_Z);
            case 3 -> this.getStateMinor(
                ShipContainerMenu.STATE_MINOR_GUARD_DIM
            );
            case 4 -> this.getStateMinor(
                ShipContainerMenu.STATE_MINOR_GUARD_TYPE
            );
            default -> 0;
        };
    }

    public int getScaleLevel() {
        return this.entityData.get(LEGACY_SCALE_LEVEL);
    }

    public void setScaleLevel(int level) {
        this.entityData.set(LEGACY_SCALE_LEVEL, Math.max(0, level));
        var scaleAttr = this.getAttribute(
            net.minecraft.world.entity.ai.attributes.Attributes.SCALE
        );
        if (scaleAttr != null) {
            float scaleFactor = net.minecraft.util.Mth.clamp(
                1.0F + level * 0.5F,
                1.0F,
                2.5F
            );
            scaleAttr.setBaseValue(scaleFactor);
        }
        this.refreshDimensions();
    }

    @Override
    public float getPickRadius() {
        float[] modelPos = this.getModelPos();
        float visualSize = (modelPos != null && modelPos.length > 3)
            ? modelPos[3]
            : 50.0F;
        float radius = Mth.clamp(
            visualSize * PICK_RADIUS_MODEL_SCALE,
            PICK_RADIUS_MIN,
            PICK_RADIUS_MAX
        );
        float scaleFactor = Mth.clamp(
            1.0F + this.getScaleLevel() * 0.5F,
            1.0F,
            2.5F
        );
        return Mth.clamp(
            radius * scaleFactor,
            PICK_RADIUS_MIN,
            PICK_RADIUS_MAX
        );
    }

    public boolean supportsItemPickup() {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(
        ServerLevel level,
        AgeableMob otherParent
    ) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    private static final float SHIP_SOUND_VOLUME = 0.6F;
    private static final int AMBIENT_SOUND_MIN_INTERVAL_TICKS = 80;
    private static final int AMBIENT_SOUND_MAX_PER_TICK = 3;

    @Override
    protected float getSoundVolume() {
        return EntityShipBaseAudioHelper.SHIP_SOUND_VOLUME;
    }

    protected float getShipSoundPitch() {
        return this.getRandom().nextFloat() * 0.12F + 0.98F;
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
        this.audioHelper.playAmbientSound();
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.audioHelper.playHurtSound(source, () ->
            super.playHurtSound(source)
        );
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
        int newFuel = Math.max(0, Math.min(MAX_FUEL, val));
        boolean wasNoFuel = this.isNoFuel();
        this.entityData.set(FUEL, newFuel);
        this.legacyState.stateMinor[6] = newFuel;
        this.entityData.set(NO_FUEL, newFuel == 0);
        boolean isNoFuelNow = newFuel == 0;
        if (wasNoFuel != isNoFuelNow) {
            this.updateFuelState(isNoFuelNow);
        }
    }

    public boolean getEquipFlag(String key) {
        if (EQUIP_MOUNT.equals(key)) {
            return (this.entityData.get(LEGACY_EMOTION_0) & 1) != 0;
        }
        return this.entityData.get(EQUIP_FLAGS).getBoolean(key);
    }

    public void setEquipFlag(String key, boolean value) {
        if (EQUIP_MOUNT.equals(key)) {
            int current = this.entityData.get(LEGACY_EMOTION_0);
            this.setStateEmotion(
                0,
                value ? (current | 1) : (current & ~1),
                true
            );
            return;
        }
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
        List<EquipOption> list = new java.util.ArrayList<>();
        if (this.hasShipMounts()) {
            list.add(new EquipOption(EQUIP_MOUNT, "gui.shincolle.equip.mount"));
        }
        return list;
    }

    protected abstract Item getShipSpawnEggItem();

    public ShipInventoryHandler getInventory() {
        return this.inventory;
    }

    public EntityShipFishingHook getFishHook() {
        return this.fishHook;
    }

    public void setFishHook(EntityShipFishingHook hook) {
        this.fishHook = hook;
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
        this.setLevel(
            switch (clampedScale) {
                case 0 -> 75;
                case 1 -> 100;
                case 2 -> 125;
                default -> 150;
            }
        );

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
        this.randomizeEquipFlags();

        fillHostileAmmoLoadout();
        this.recalculateLegacyShipStats();
        this.setHealth(this.getMaxHealth());
        this.shipDeathTicks = 0;
        this.hostileCanDrop = true;
    }

    public void randomizeEquipFlags() {
        this.entityData.set(
            LEGACY_EMOTION_0,
            this.getRandom().nextInt(128) & ~1
        );
        List<EquipOption> options = this.getEquipOptions();
        if (!options.isEmpty()) {
            CompoundTag tag = this.entityData.get(EQUIP_FLAGS).copy();
            if (tag.isEmpty()) {
                for (EquipOption option : options) {
                    if (option.key().equals(EQUIP_MOUNT)) {
                        continue;
                    }
                    tag.putBoolean(
                        option.key(),
                        this.getRandom().nextBoolean()
                    );
                }
                this.entityData.set(EQUIP_FLAGS, tag);
            }
        }
    }

    private void fillHostileAmmoLoadout() {
        int slots = this.getAccessibleInventorySlotCount();
        for (int i = 0; i < slots; i++) {
            this.getInventory().setStackInSlot(i, ItemStack.EMPTY);
        }

        if (slots > 0) {
            this.getInventory().setStackInSlot(
                0,
                new ItemStack(
                    ModItems.AMMO_LIGHT_CONTAINER.get(),
                    HOSTILE_LIGHT_AMMO_CONTAINER_COUNT
                )
            );
        }
        if (slots > 1) {
            this.getInventory().setStackInSlot(
                1,
                new ItemStack(
                    ModItems.AMMO_HEAVY_CONTAINER.get(),
                    HOSTILE_HEAVY_AMMO_CONTAINER_COUNT
                )
            );
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
    public void tick() {
        super.tick();

        if (this.isCustomSwinging) {
            this.customSwingTicks++;
            if (this.customSwingTicks >= MAX_SWING_TICKS) {
                this.isCustomSwinging = false;
                this.customSwingTicks = 0;
            }
        }
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

    public static final ThreadLocal<Boolean> shincolle$isSavingChunkEntities =
        ThreadLocal.withInitial(() -> false);
    private transient boolean shincolle$isSavingAsRoot = false;

    @Override
    public boolean isPassenger() {
        if (
            shincolle$isSavingChunkEntities.get() &&
            this.getVehicle() instanceof Player
        ) {
            return false;
        }
        if (this.shincolle$isSavingAsRoot) {
            return false;
        }
        return super.isPassenger();
    }

    @Override
    public boolean startRiding(Entity vehicle, boolean force) {
        boolean result = super.startRiding(vehicle, force);
        if (
            result &&
            !this.level().isClientSide() &&
            vehicle instanceof ServerPlayer serverPlayer
        ) {
            serverPlayer.connection.send(
                new net.minecraft.network.protocol.game.ClientboundSetPassengersPacket(
                    serverPlayer
                )
            );
        }
        return result;
    }

    @Override
    public void stopRiding() {
        Entity vehicle = this.getVehicle();
        super.stopRiding();
        if (
            !this.level().isClientSide() &&
            vehicle instanceof ServerPlayer serverPlayer
        ) {
            serverPlayer.connection.send(
                new net.minecraft.network.protocol.game.ClientboundSetPassengersPacket(
                    serverPlayer
                )
            );
        }
    }

    private boolean hasPlayerAsVehicle() {
        return this.getVehicle() instanceof Player;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.hasPlayerAsVehicle() && super.canBeCollidedWith();
    }

    @Override
    public boolean isPickable() {
        return !this.hasPlayerAsVehicle() && super.isPickable();
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return null;
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getBbHeight() * 0.35D;
        }
        return this.getBbHeight() * 0.75D;
    }

    @Override
    protected void positionRider(
        Entity passenger,
        Entity.MoveFunction moveFunction
    ) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        float offsetZ = 0.0F;
        float offsetX = 0.0F;
        if (passenger instanceof EntityNorthernHime) {
            float[] partPos = rotateXZByAxis(
                -0.1F,
                0.0F,
                (this.yBodyRot % 360.0F) * Mth.DEG_TO_RAD,
                1.0F
            );
            offsetX = partPos[1];
            offsetZ = partPos[0];
        }

        moveFunction.accept(
            passenger,
            this.getX() + offsetX,
            this.getY() + this.getPassengersRidingOffset(),
            this.getZ() + offsetZ
        );
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
        if (
            this.level().getDifficulty() ==
            net.minecraft.world.Difficulty.PEACEFUL
        ) {
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
                    if (
                        this.tickCount > minionDespawn &&
                        this.getRandom().nextInt(800) == 0
                    ) {
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

        this.updateMountSummon();

        if (this.getIsSitting() || this.isInDeadPose()) {
            this.getNavigation().stop();
        }

        if (!this.isNoFuel()) {
            this.pointer.tickPointerTargetEntity();

            if (this.movementHelper.shouldRetreatForLowHealth()) {
                this.passiveCombat.clearTarget(true);
                this.movementHelper.tickRetreatMovement();
            } else if (this.hasPointerTargetEntity()) {
                this.passiveCombat.clearTarget(true);
            } else {
                this.passiveCombat.tickTargeting();
                this.passiveCombat.tickActions();
            }

            this.combat.tickAircraftRecovery();
            this.inventoryHelper.tickAutoPickupItems();
            this.inventoryHelper.tickAutoPump();
            this.suppliesHelper.tickAutoRation();
            this.reactions.tickEmotes();
            if ((this.tickCount & 0xFF) == 0) {
                applyEmotesReaction(4);
            }
        } else {
            this.passiveCombat.clearTarget(true);
        }

        if (this.isAlive() && (this.tickCount & 7) == 0) {
            org.trp.shincolle.utility.TaskHelper.onUpdateTask(this);
        }

        tickSearchlightAssist();
        this.compassHelper.tickCompassChunkLoading();
        this.audioHelper.tickTimeKeepingSound();

        if (!this.level().isClientSide) {
            if ((this.tickCount % 32) == 0) {
                this.tickPeriodicEffects();
            }
            if ((this.tickCount % 128) == 0) {
                if (
                    this.isStateMarried() &&
                    this.isStateRingEffect() &&
                    !this.isNoFuel()
                ) {
                    this.applyAuraEffects();
                }
            }
        }

        tickFuelDecay();
        tickAutoRecovery();
        this.suppliesHelper.tickAutoSupplies();
        tickLegacyTimers();
        if ((this.tickCount % 16) == 0) {
            this.movementHelper.tickWaypointMove();
        }
        if ((this.tickCount % 40) == 0) {
            this.recalculateLegacyShipStats();
        }
    }

    @Override
    protected void tickDeath() {
        this.deathHelper.tickDeath();
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        if (this.level() instanceof ServerLevel serverLevel) {
            this.compassHelper.clearCompassForcedChunks(serverLevel);
        }
        super.remove(reason);
    }

    private void tickFuelDecay() {
        if (this.isHostileShipMob()) {
            return;
        }
        if (this.tickCount % org.trp.shincolle.Config.fuelDecayInterval != 0) {
            return;
        }
        if (this.getFuel() <= 0) {
            return;
        }

        int consume = this.getStateMinor(STATE_MINOR_GRUDGE_CONSUMPTION);

        double dist = Math.sqrt(this.distanceToSqr(this.xo, this.yo, this.zo));
        consume += (int) (dist * org.trp.shincolle.Config.fuelMoveDecayFactor);

        this.setFuel(this.getFuel() - consume);
    }

    private void tickAutoRecovery() {
        if (this.isHostileShipMob()) {
            return;
        }

        if (
            (this.tickCount & 0x1F) == 0 &&
            this.getHealth() < this.getMaxHealth() * AUTO_HEAL_THRESHOLD_RATIO
        ) {
            if (this.consumeItemInInventory(ModItems.BUCKET_REPAIR.get())) {
                this.heal(
                    this.getMaxHealth() * AUTO_HEAL_FAST_RATIO +
                        AUTO_HEAL_FAST_FLAT
                );
                if (this.supportsAircraftCombat()) {
                    this.setNumAircraftLight(this.getNumAircraftLight() + 1);
                    this.setNumAircraftHeavy(this.getNumAircraftHeavy() + 1);
                }
                this.applyParticleEmotion(EmotionParticleType.HEART);
            }
        }

        if (
            (this.tickCount & 0xFF) == 0 &&
            this.getHealth() < this.getMaxHealth()
        ) {
            this.heal(
                this.getMaxHealth() * AUTO_HEAL_SLOW_RATIO + AUTO_HEAL_SLOW_FLAT
            );
        }
    }

    private boolean shouldRetreatForLowHealth() {
        int fleeHp = Mth.clamp(
            this.getStateMinor(ShipContainerMenu.STATE_MINOR_FLEE_HP),
            0,
            100
        );
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
        if (
            !this.getStateFlag(ShipContainerMenu.STATE_FLAG_TIMEKEEP) ||
            !this.isAlive() ||
            this.isInDeadPose()
        ) {
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

    protected void tryFlareTarget(@Nullable Entity target) {
        if (
            target == null || this.getStateMinor(STATE_MINOR_EQUIP_FLARE) <= 0
        ) {
            return;
        }
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double posX = target.getX();
        double posY = target.getY() + target.getBbHeight() * 0.5D;
        double posZ = target.getZ();
        serverLevel.sendParticles(
            ParticleTypes.FIREWORK,
            posX,
            posY,
            posZ,
            12,
            0.5D,
            0.6D,
            0.5D,
            0.05D
        );

        if (target instanceof LivingEntity living) {
            living.addEffect(
                new MobEffectInstance(
                    MobEffects.GLOWING,
                    SPECIAL_EQUIP_FLARE_GLOW_TICKS,
                    0,
                    false,
                    true,
                    true
                ),
                this
            );
        }
    }

    protected boolean hasSearchlightEquip() {
        return this.getStateMinor(STATE_MINOR_EQUIP_SEARCHLIGHT) > 0;
    }

    protected void tickSearchlightAssist() {
        if (!Config.canSearchlight) {
            return;
        }
        if ((this.tickCount % Config.searchlightCD) != 0) {
            return;
        }
        if (!this.hasSearchlightEquip() || !this.isAlive()) {
            return;
        }
        if (this.level().isClientSide) {
            return;
        }

        BlockPos pos = this.blockPosition();
        if (this.level().getBrightness(LightLayer.BLOCK, pos) < 10) {
            BlockHelper.placeLightBlock(this.level(), pos);
        } else {
            BlockHelper.updateNearbyLightBlock(this.level(), pos);
        }
    }

    public int findItemInInventory(Item item) {
        return this.inventoryHelper.findItemInInventory(item);
    }

    public boolean consumeItemInInventory(Item item) {
        return this.inventoryHelper.consumeItemInInventory(item);
    }

    protected boolean hasLiquidDrumEquip() {
        return this.inventoryHelper.hasLiquidDrumEquip();
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

    public boolean useModernKitInHand(ItemStack stack, Player player) {
        java.util.Random javaRand = new java.util.Random();
        if (!this.legacyShipStats.addBonusRandom(javaRand)) {
            return false;
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        this.setEmotionPrimary(EMOTION_HAPPY);
        this.resetInteractionEmotionState();
        this.recalculateLegacyShipStats();
        this.playSound(
            ModSounds.SHIP_MARRY.get(),
            this.getSoundVolume(),
            this.getShipSoundPitch()
        );
        this.focusOnPlayer(player);
        return true;
    }

    public boolean useKaitaiHammer(Player player, ItemStack stack) {
        return this.kaitaiHelper.useKaitaiHammer(player, stack);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            ItemStack stack = player.getItemInHand(hand);
            if (
                stack.is(ModItems.KAITAI_HAMMER.get()) &&
                player.isShiftKeyDown()
            ) {
                return this.useKaitaiHammer(player, stack)
                    ? InteractionResult.sidedSuccess(this.level().isClientSide)
                    : InteractionResult.PASS;
            }

            if (!this.isTame()) {
                return InteractionResult.PASS;
            }

            if (!this.isOwnedBy(player)) {
                return InteractionResult.PASS;
            }

            if (stack.is(ModItems.TRAINING_BOOK.get())) {
                return InteractionResult.PASS;
            }

            if (
                stack.is(ModItems.MARRIAGE_RING.get()) && !this.isStateMarried()
            ) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                this.setStateMarried(true);
                this.setMorale(16000);
                this.setEmotionPrimary(EMOTION_HAPPY);
                this.applyParticleEmotion(EmotionParticleType.HEART);
                if (this.level() instanceof ServerLevel serverLevel) {
                    for (int i = 0; i < 7; ++i) {
                        double px =
                            this.getX() +
                            (this.getRandom().nextFloat() * 2.0F - 1.0F);
                        double py =
                            this.getY() +
                            0.5D +
                            (this.getRandom().nextFloat() * 2.0F);
                        double pz =
                            this.getZ() +
                            (this.getRandom().nextFloat() * 2.0F - 1.0F);
                        double d0 = this.getRandom().nextGaussian() * 0.02D;
                        double d1 = this.getRandom().nextGaussian() * 0.02D;
                        double d2 = this.getRandom().nextGaussian() * 0.02D;
                        serverLevel.sendParticles(
                            ParticleTypes.HEART,
                            px,
                            py,
                            pz,
                            0,
                            d0,
                            d1,
                            d2,
                            1.0D
                        );
                    }
                }
                this.playSound(
                    ModSounds.SHIP_MARRY.get(),
                    this.getSoundVolume(),
                    this.getShipSoundPitch()
                );

                java.util.Random javaRand = new java.util.Random();
                for (int i = 0; i < 3; ++i) {
                    this.legacyShipStats.addBonusRandom(javaRand);
                }
                this.recalculateLegacyShipStats();

                this.resetInteractionEmotionState();
                this.focusOnPlayer(player);
                return InteractionResult.sidedSuccess(
                    this.level().isClientSide
                );
            }

            if (stack.getItem() instanceof CombatRationItem) {
                if (
                    this.suppliesHelper.consumeCombatRationInHand(stack, player)
                ) {
                    this.focusOnPlayer(player);
                    return InteractionResult.sidedSuccess(
                        this.level().isClientSide
                    );
                }
            }

            if (stack.is(ModItems.MODERN_KIT.get())) {
                if (this.useModernKitInHand(stack, player)) {
                    return InteractionResult.sidedSuccess(
                        this.level().isClientSide
                    );
                }
            }

            if (stack.is(ModItems.BUCKET_REPAIR.get())) {
                if (
                    this.suppliesHelper.consumeBucketRepairInHand(stack, player)
                ) {
                    return InteractionResult.sidedSuccess(
                        this.level().isClientSide
                    );
                }
            }

            if (stack.is(ModItems.TOY_AIRPLANE.get())) {
                if (
                    this.suppliesHelper.consumeToyAirplaneInHand(stack, player)
                ) {
                    return InteractionResult.sidedSuccess(
                        this.level().isClientSide
                    );
                }
            }

            if (stack.is(ModItems.GRUDGE.get())) {
                int gain = 300 + this.getRandom().nextInt(500);
                this.setFuel(this.getFuel() + gain);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                this.suppliesHelper.checkAndPlayFeedSound();
                this.setEmotionPrimary(EMOTION_HAPPY);
                this.resetInteractionEmotionState();
                this.focusOnPlayer(player);
                return InteractionResult.sidedSuccess(
                    this.level().isClientSide
                );
            }

            if (stack.has(DataComponents.FOOD)) {
                FoodProperties food = stack.getFoodProperties(player);
                if (food != null && food.nutrition() > 0) {
                    float fv = food.nutrition();
                    float sv = food.saturation();
                    if (fv < 1.0F) fv = 1.0F;
                    int grudgeValue = (int) ((fv +
                            this.getRandom().nextInt((int) fv + 5)) *
                        sv *
                        20.0F);
                    float modFuel = this.getLegacyShipStats().getBuffedAttr(17);
                    int gain = (int) (grudgeValue * modFuel);
                    this.setFuel(this.getFuel() + gain);
                    this.addMorale(grudgeValue);
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    this.suppliesHelper.checkAndPlayFeedSound();
                    this.setEmotionPrimary(EMOTION_HAPPY);
                    this.resetInteractionEmotionState();
                    this.focusOnPlayer(player);
                    return InteractionResult.sidedSuccess(
                        this.level().isClientSide
                    );
                }
            }

            if (player.isShiftKeyDown()) {
                this.openShipMenu(player);
                this.resetInteractionEmotionState();
                this.focusOnPlayer(player);
                return InteractionResult.sidedSuccess(
                    this.level().isClientSide
                );
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
        if (this.isCombatSuppressed()) {
            return false;
        }
        if (
            !this.getStateFlag(
                org.trp.shincolle.menu.ShipContainerMenu.STATE_FLAG_CAN_MELEE
            )
        ) {
            return false;
        }
        if (!this.level().isClientSide) {
            this.addShipExp(Config.shipExpGainMelee);
        }
        boolean result = super.doHurtTarget(target);
        if (result && !this.level().isClientSide) {
            this.setCombatTick(this.tickCount);
            this.playSound(
                ModSounds.SHIP_HIT.get(),
                this.getSoundVolume(),
                this.getShipSoundPitch()
            );
            this.setAttackTick(50);
            applyEmotesReaction(3);
            if (target instanceof LivingEntity livingTarget) {
                this.applyAttackEffects(livingTarget);
            }
        }
        return result;
    }

    @Override
    public boolean hurt(
        net.minecraft.world.damagesource.DamageSource source,
        float amount
    ) {
        if (this.customHurtTime > 0 || source.is(DamageTypeTags.IS_FIRE)) {
            return false;
        }

        if (!this.level().isClientSide && tryLegacyDodge(source)) {
            return false;
        }

        float reduced = amount;
        if (!this.level().isClientSide && amount < 100000.0F) {
            reduced = this.legacyShipStats.getDefenseReducedDamage(
                amount,
                this.getRandom()
            );
        }

        boolean isHammer =
            source.getEntity() instanceof Player p &&
            p.getMainHandItem().is(ModItems.KAITAI_HAMMER.get());

        if (
            !this.level().isClientSide &&
            !isHammer &&
            !this.isDeadOrDying() &&
            reduced >= this.getHealth() &&
            !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)
        ) {
            Entity attacker = source.getEntity();
            boolean isOwnerAttack =
                attacker instanceof Player &&
                attacker.getUUID().equals(this.getOwnerUUID());

            if (
                !isOwnerAttack &&
                this.consumeItemInInventory(ModItems.REPAIR_GODDESS.get())
            ) {
                this.setHealth(this.getMaxHealth());
                this.customHurtTime = 120;
                this.spawnGoddessParticles();
                this.playSound(
                    ModSounds.SHIP_FEED.get(),
                    this.getSoundVolume(),
                    this.getShipSoundPitch()
                );
                return false;
            }
        }

        boolean result = super.hurt(source, reduced);
        if (!this.level().isClientSide && result) {
            this.setCombatTick(this.tickCount);
            if (this.isOrderedToSit() || this.isInSittingPose()) {
                this.setOrderedToSit(false);
                this.setInSittingPose(false);
            }
            if (this.getRandom().nextInt(5) == 0) {
                applyEmotesReaction(2);
                this.setEmotionPrimary(EMOTION_SCORN);
            }
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

        float dodge = Mth.clamp(
            this.legacyShipStats.getBuffedAttr(15) + getInvisibleDodgeBonus(),
            0.0F,
            0.9F
        );
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
        super.heal(amount * this.legacyShipStats.getBuffedAttr(19));
    }

    private void spawnLegacyHealParticles() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double beamHeight = this.getBbHeight() * 0.4D;
        double beamRiseSpeed = 0.1D;
        double beamFad = this.getBbWidth() * 1.5D;

        serverLevel.sendParticles(
            ModParticles.PARTICLE_HEAL_SPARKLE.get(),
            this.getX(),
            this.getY(),
            this.getZ(),
            0,
            beamFad,
            beamRiseSpeed,
            beamHeight,
            1.0D
        );
    }

    private void spawnGoddessParticles() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double beamHeight = this.getBbHeight() * 0.4D;
        double beamRiseSpeed = 0.03D;
        double beamFad = this.getBbWidth() * 2.0D;

        serverLevel.sendParticles(
            ModParticles.PARTICLE_GODDESS.get(),
            this.getX(),
            this.getY(),
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
        this.goalSelector.addGoal(2, new EntityShipGuardGoal(this, 1.1D));
        this.goalSelector.addGoal(
            3,
            new EntityShipFollowOwnerGoal(this, 1.2D, 16.0F, 5.0F)
        );
        this.goalSelector.addGoal(
            4,
            new LookAtPlayerGoal(this, Player.class, 8.0F) {
                @Override
                public boolean canUse() {
                    return (
                        !EntityShipBase.this.isInDeadPose() && super.canUse()
                    );
                }

                @Override
                public boolean canContinueToUse() {
                    return (
                        !EntityShipBase.this.isInDeadPose() &&
                        super.canContinueToUse()
                    );
                }
            }
        );
        this.goalSelector.addGoal(
            5,
            new RandomLookAroundGoal(this) {
                @Override
                public boolean canUse() {
                    return (
                        !EntityShipBase.this.isInDeadPose() && super.canUse()
                    );
                }

                @Override
                public boolean canContinueToUse() {
                    return (
                        !EntityShipBase.this.isInDeadPose() &&
                        super.canContinueToUse()
                    );
                }
            }
        );
        this.goalSelector.addGoal(
            6,
            new RandomStrollGoal(this, 1.0D) {
                @Override
                public boolean canUse() {
                    return (
                        !EntityShipBase.this.isOrderedToSit() &&
                        !EntityShipBase.this.isInSittingPose() &&
                        !EntityShipBase.this.isInDeadPose() &&
                        !EntityShipBase.this.isPassenger() &&
                        !EntityShipBase.this.isVehicle() &&
                        super.canUse()
                    );
                }

                @Override
                public boolean canContinueToUse() {
                    return (
                        !EntityShipBase.this.isOrderedToSit() &&
                        !EntityShipBase.this.isInSittingPose() &&
                        !EntityShipBase.this.isInDeadPose() &&
                        !EntityShipBase.this.isPassenger() &&
                        !EntityShipBase.this.isVehicle() &&
                        super.canContinueToUse()
                    );
                }
            }
        );
    }

    public void onInventoryChanged() {
        this.combat.recalculateAmmoCounts();
        this.recalculateLegacyShipStats();
    }

    protected void recalculateLegacyShipStats() {
        this.statsHelper.recalculateLegacyShipStats();
    }

    protected final java.util.Map<
        net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>,
        int[]
    > attackEffectMap = new java.util.HashMap<>();

    public java.util.Map<
        net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>,
        int[]
    > getAttackEffectMap() {
        return this.attackEffectMap;
    }

    public void applyAttackEffects(LivingEntity target) {
        if (target == null || this.attackEffectMap.isEmpty()) {
            return;
        }
        for (java.util.Map.Entry<
            net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>,
            int[]
        > entry : this.attackEffectMap.entrySet()) {
            net.minecraft.core.Holder<
                net.minecraft.world.effect.MobEffect
            > effect = entry.getKey();
            int[] values = entry.getValue();
            int amp = values[0];
            int duration = values[1];
            int chance = values[2];
            if (this.getRandom().nextInt(100) < chance) {
                int actualDuration = (effect == MobEffects.HEAL ||
                    effect == MobEffects.HARM)
                    ? 5
                    : duration;
                target.addEffect(
                    new net.minecraft.world.effect.MobEffectInstance(
                        effect,
                        actualDuration,
                        amp,
                        false,
                        true
                    )
                );
            }
        }
    }

    protected void calcShipAttributesAddEffect() {
        this.attackEffectMap.clear();
    }

    protected float[] computeLegacyAuraBuffs() {
        return new float[21];
    }

    protected float getInvisibleDodgeBonus() {
        return 0.0F;
    }

    protected void applyAuraEffects() {}

    protected void tickPeriodicEffects() {
        this.statsHelper.tickPeriodicEffects();
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

    public void openShipMenu(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MenuProvider provider = new SimpleMenuProvider(
                (id, inv, ply) -> new ShipContainerMenu(id, inv, this),
                Component.translatable("gui.shincolle.ship")
            );
            (serverPlayer).openMenu(provider, buffer ->
                buffer.writeInt(this.getId())
            );
        }
    }

    protected void migrateLegacyStateFlags(int stateFlags) {}

    protected int getLegacyModelStateRange() {
        return 128;
    }

    protected int getInitialLegacyEmotion(int index) {
        if (index == 0) {
            return 0;
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
        return new int[] {
            getStateEmotion(0),
            getStateEmotion(1),
            getStateEmotion(2),
            getStateEmotion(3),
            getStateEmotion(4),
            getStateEmotion(5),
            getStateEmotion(6),
            getStateEmotion(7),
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
        this.deathHelper.resetDeathStateForSpawnEgg();
    }

    public int getWpStayTimeMax() {
        return this.movementHelper.getWpStayTimeMax();
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
        this.suppliesHelper.tickSupplies();

        
        @SuppressWarnings("unchecked")
        EntityDataAccessor<Integer>[] cdAccessors = new EntityDataAccessor[] {
            MOUNT_ATTACK_CD_0,
            MOUNT_ATTACK_CD_1,
            MOUNT_ATTACK_CD_2,
            MOUNT_ATTACK_CD_3,
        };
        for (int i = 0; i < 4; i++) {
            int idx = 16 + i;
            int timer = legacyState.stateTimer[idx];
            if (timer > 0) {
                int newTimer = timer - 1;
                legacyState.stateTimer[idx] = newTimer;
                if (!this.level().isClientSide) {
                    this.entityData.set(cdAccessors[i], newTimer);
                }
            }
        }
    }

    public void applyEmotesReaction(int type) {
        this.reactions.applyEmotesReaction(type);
    }

    public void applyEmotesAOE(
        double range,
        int type,
        boolean includeNonOwned
    ) {
        if (this.level().isClientSide) return;
        AABB box = this.getBoundingBox().inflate(range);
        List<EntityShipBase> list = this.level().getEntitiesOfClass(
            EntityShipBase.class,
            box
        );
        LivingEntity owner = this.getOwner();
        for (EntityShipBase s : list) {
            if (s.isAlive() && !s.equals(this)) {
                if (includeNonOwned || (owner != null && s.isOwnedBy(owner))) {
                    s.applyEmotesReaction(type);
                }
            }
        }
    }

    public void applyParticleEmotion(EmotionParticleType type) {
        this.reactions.applyParticleEmotion(type);
    }

    public void applyParticleEmotion(int typeId) {
        this.reactions.applyParticleEmotion(typeId);
    }

    public int getEmotesTick() {
        return this.reactions.getEmotesTick();
    }

    public void setEmotesTick(int ticks) {
        this.reactions.setEmotesTick(ticks);
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

    public record EquipOption(String key, String labelKey) {}

    @Override
    public void die(DamageSource cause) {
        this.deathHelper.die(cause, () -> super.die(cause));
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 104) {
            this.isCustomSwinging = true;
            this.customSwingTicks = 0;
        } else {
            super.handleEntityEvent(id);
        }
    }

    public float getCustomAttackAnim(float partialTick) {
        if (!this.isCustomSwinging) return 0.0F;
        return (
            ((float) this.customSwingTicks + partialTick) /
            (float) MAX_SWING_TICKS
        );
    }

    public void startCustomSwing() {
        this.isCustomSwinging = true;
        this.customSwingTicks = 0;

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 104);
        }
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean causeFallDamage(
        float fallDistance,
        float damageMultiplier,
        DamageSource source
    ) {
        return false;
    }
}
