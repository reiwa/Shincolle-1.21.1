package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.Config;
import org.trp.shincolle.utility.BlockHelper;
import org.trp.shincolle.entity.EntityNorthernHime;
import org.trp.shincolle.entity.EntityShipFishingHook;
import org.trp.shincolle.entity.base.path.ShipLegacyNavigation;
import org.trp.shincolle.entity.base.path.ShipMoveControl;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.item.CombatRationItem;
import org.trp.shincolle.menu.ShipContainerMenu;

import javax.annotation.Nullable;
import java.util.List;

import static org.trp.shincolle.entity.base.EntityShipData.*;

public abstract class EntityShipBase extends TamableAnimal implements IShipRenderState, IShipCombatOwner, IShipStatsOwner, IShipDataSyncher {

    static {
        try {
            Class.forName("org.trp.shincolle.entity.base.EntityShipData");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean stopAI = false;

    public static boolean isStopAI() {
        return stopAI;
    }

    public static void setStopAI(boolean stopAI) {
        EntityShipBase.stopAI = stopAI;
    }

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

    private static final int MORALE_DEFAULT = 60;
    static final float CRUISE_SPEED_FACTOR = 0.3F;

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
    private static final int SPECIAL_EQUIP_FLARE_GLOW_TICKS = 80;
    static final int XP_BOTTLE_COST = 8;
    private static final int HOSTILE_LIGHT_AMMO_CONTAINER_COUNT = 16;
    private static final int HOSTILE_HEAVY_AMMO_CONTAINER_COUNT = 12;
    public static final int KAITAI_AMOUNT_SMALL = 20;
    public static final int KAITAI_AMOUNT_LARGE = 20;
    static final String TAG_SPAWN_EGG = "ShincolleSpawnEgg";
    static final String TAG_SPAWN_EGG_NO_EXP = "ShincolleSpawnEggNoExpCost";

    public static final int STATE_MINOR_AFFECTION_LEGACY = 0;
    public static final int STATE_MINOR_FUEL = 6;
    public static final int STATE_MINOR_AIRCRAFT_LIGHT = 7;
    public static final int STATE_MINOR_AIRCRAFT_HEAVY = 8;
    public static final int STATE_MINOR_RATION_MORALE = 9;
    public static final int STATE_MINOR_FOLLOW_MIN = 10;
    public static final int STATE_MINOR_FOLLOW_MAX = 11;
    public static final int STATE_MINOR_FLEE_HP = 12;
    public static final int STATE_MINOR_TASK_ID = 40;
    public static final int STATE_MINOR_TASK_SIDE = 41;
    public static final int STATE_MINOR_WP_STAY = 44;

    public static final int STATE_MINOR_FACTION_ID = 19;
    public static final int STATE_MINOR_SHIP_CLASS = 20;
    public static final int STATE_MINOR_SPECIAL_EQUIP = 25;
    public static final int STATE_MINOR_GRUDGE_CONSUMPTION = 28;
    public static final int STATE_MINOR_RARITY = 13;
    public static final int STATE_MINOR_EQUIP_DRUM = 36;
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
    public static final int STATE_MINOR_TIMEKEEP_LEGACY = 22;

    public static final int STATE_MINOR_HIT_HEIGHT = 33;
    public static final int STATE_MINOR_HIT_ANGLE = 34;
    public static final int STATE_MINOR_SENSITIVE_BODY = 35;

    private static final int CARESS_MORALE_CAP = 6630;

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

    protected final ShipInventoryHandler inventory;
    private final EntityShipBaseCombat combat;
    private final EntityShipBasePointer pointer;
    private final EntityShipBaseEmotions emotions;
    private final EntityShipBaseFaceExpressions faceExpressions;
    private final EntityShipBaseReactions reactions;
    private final EntityShipBasePassiveCombat passiveCombat;
    private final EntityShipBaseSerialization serialization;
    private final LegacyShipStats legacyShipStats;
    private final ShipStateComponent stateComponent;
    private final EntityShipBaseInventoryHelper inventoryHelper;
    final EntityShipBaseSupplies suppliesHelper;
    private final EntityShipBaseInteractionHelper interactionHelper;
    private final EntityShipBaseKaitai kaitaiHelper;
    private final EntityShipBaseCompass compassHelper;
    private final EntityShipBaseStatsHelper statsHelper;
    final EntityShipBaseMovementHelper movementHelper;
    private final EntityShipBaseDeathHelper deathHelper;
    final EntityShipBaseAudioHelper audioHelper;
    private final FaceExpressionConfig faceExpressionConfig;
    private EntityShipFishingHook fishHook;
    @Nullable
    private ServerBossEvent bossEvent;

    protected BossEvent.BossBarColor getBossBarColor() {
        return BossEvent.BossBarColor.WHITE;
    }

    protected BossEvent.BossBarOverlay getBossBarOverlay() {
        return BossEvent.BossBarOverlay.NOTCHED_10;
    }
    int shipDeathTicks = 0;
    private int customHurtTime;
    int hurtSoundCooldown;
    protected int combatTick = 0;
    private int customSwingTicks = 0;
    private boolean isCustomSwinging = false;
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
        this.stateComponent = this.createStateComponent();
        this.inventoryHelper = new EntityShipBaseInventoryHelper(this);
        this.suppliesHelper = new EntityShipBaseSupplies(this);
        this.interactionHelper = new EntityShipBaseInteractionHelper(this);
        this.kaitaiHelper = new EntityShipBaseKaitai(this);
        this.compassHelper = new EntityShipBaseCompass(this);
        this.statsHelper = new EntityShipBaseStatsHelper(this);
        this.movementHelper = new EntityShipBaseMovementHelper(this);
        this.deathHelper = new EntityShipBaseDeathHelper(this);
        this.audioHelper = new EntityShipBaseAudioHelper(this);
        this.faceExpressionConfig = this.createFaceExpressionConfig();
        this.moveControl = new ShipMoveControl(this, 30.0F);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        if (level != null && !level.isClientSide) {
            this.bossEvent = new ServerBossEvent(
                this.getDisplayName(),
                this.getBossBarColor(),
                this.getBossBarOverlay()
            );
        } else {
            this.bossEvent = null;
        }
        this.setPathfindingMalus(PathType.LAVA, 0.0F);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
        this.stateComponent.setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeDD);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        if (this.bossEvent != null && this.isHostileShipMob() && this.getScaleLevel() > 1) {
            this.bossEvent.addPlayer(player);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        if (this.bossEvent != null) {
            this.bossEvent.removePlayer(player);
        }
    }

    static int getMoraleDefaultValue() {
        return MORALE_DEFAULT;
    }

    static String getSpawnEggTagName() {
        return TAG_SPAWN_EGG;
    }

    @javax.annotation.Nullable
    @Override
    public SpawnGroupData finalizeSpawn(
        net.minecraft.world.level.ServerLevelAccessor level,
        net.minecraft.world.DifficultyInstance difficulty,
        net.minecraft.world.entity.MobSpawnType spawnType,
        @javax.annotation.Nullable net.minecraft.world.entity.SpawnGroupData spawnGroupData
    ) {
        SpawnGroupData data = super.finalizeSpawn(
            level,
            difficulty,
            spawnType,
            spawnGroupData
        );
        this.recalculateLegacyShipStats();
        this.setHealth(this.getMaxHealth());
        return data;
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

    @Override
    public int getTickCount() {
        return this.tickCount;
    }

    @Override
    public LivingEntity asLivingEntity() {
        return this;
    }

    @Override
    public EntityShipBase asShipEntity() {
        return this;
    }

    @Override
    public float getShipSoundVolume() {
        return this.getSoundVolume();
    }

    @Override
    public <T> T getData(EntityDataAccessor<T> key) {
        return this.entityData.get(key);
    }

    @Override
    public <T> void setData(EntityDataAccessor<T> key, T value) {
        this.entityData.set(key, value);
    }


    public int getLevel() {
        return this.stateComponent.getLevel();
    }

    public void setLevel(int level) {
        this.stateComponent.setLevel(level);
        this.recalculateLegacyShipStats();
    }

    public int getExp() {
        return this.stateComponent.getExp();
    }

    public void setExp(int exp) {
        this.stateComponent.setExp(exp);
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
        return this.stateComponent.getAmmoLight();
    }

    public void setAmmoLight(int val) {
        this.stateComponent.setAmmoLight(val);
    }

    public int getAmmoHeavy() {
        return this.stateComponent.getAmmoHeavy();
    }

    public void setAmmoHeavy(int val) {
        this.stateComponent.setAmmoHeavy(val);
    }

    public int getNumAircraftLight() {
        return this.stateComponent.getNumAircraftLight();
    }

    public int getNumAircraftHeavy() {
        return this.stateComponent.getNumAircraftHeavy();
    }

    public boolean hasAirLight() {
        return this.getNumAircraftLight() > 0;
    }

    public boolean hasAirHeavy() {
        return this.getNumAircraftHeavy() > 0;
    }

    public void setNumAircraftLight(int count) {
        this.stateComponent.setNumAircraftLight(count);
    }

    public void setNumAircraftHeavy(int count) {
        this.stateComponent.setNumAircraftHeavy(count);
    }

    public boolean isPointerSelected() {
        return this.stateComponent.isPointerSelected();
    }

    public void setPointerSelected(boolean selected) {
        this.stateComponent.setPointerSelected(selected);
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
        return this.stateComponent.getFaceId();
    }

    public void setFaceId(int id) {
        this.stateComponent.setFaceId(id);
    }

    public int getMouthId() {
        return this.stateComponent.getMouthId();
    }

    public void setMouthId(int id) {
        this.stateComponent.setMouthId(id);
    }

    public int getEmotionPrimary() {
        return this.stateComponent.getEmotionPrimary();
    }

    public void setEmotionPrimary(int val) {
        this.stateComponent.setEmotionPrimary(val);
    }

    void resetFaceTick() {
        this.emotions.resetFaceTick();
    }

    public int getEmotionSecondary() {
        return this.stateComponent.getEmotionSecondary();
    }

    public void setEmotionSecondary(int val) {
        this.stateComponent.setEmotionSecondary(val);
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
        return this.stateComponent.getMorale();
    }

    public void setMorale(int val) {
        this.stateComponent.setMorale(val);
        if (!this.level().isClientSide) {
            this.recalculateLegacyShipStats();
        }
    }

    public void addMorale(int delta) {
        this.setMorale(this.getMorale() + delta);
    }

    public void decrMorale(int type) {
        int[] moraleCost = {-2, -4, -6, -6, -8, -5};
        if (type >= 0 && type < moraleCost.length) {
            this.addMorale(moraleCost[type]);
        }
    }

    public int getFormationTeam() {
        return this.stateComponent.getFormationTeam();
    }

    public void setFormationTeam(int team) {
        this.stateComponent.setFormationTeam(team);
    }

    public int getFormationSlot() {
        return this.stateComponent.getFormationSlot();
    }

    public void setFormationSlot(int slot) {
        this.stateComponent.setFormationSlot(slot);
    }

    public boolean isNoFuel() {
        return this.getFuel() <= 0;
    }

    public void setNoFuel(boolean val) {
        boolean wasNoFuel = this.isNoFuel();
        this.stateComponent.setNoFuel(val);
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
                this.getVehicle() instanceof EntityMountBase &&
                !this.canSummonMounts()
            ) {
                this.stopRiding();
            }
        }
    }

    public boolean isInDeadPose() {
        return (
            this.isDeadOrDying() || this.getHealth() <= 0.0F || this.isNoFuel()
        );
    }

    public int getStateEmotion(int index) {
        return this.stateComponent.getStateEmotion(index);
    }

    public void setStateEmotion(int index, int value, boolean sync) {
        this.stateComponent.setStateEmotion(index, value);
    }

    public void tickEmotions() {
        this.emotions.tickEmotions();
    }

    public int getAttackTick() {
        return this.stateComponent.getAttackTick();
    }

    public void setAttackTick(int value) {
        this.stateComponent.setAttackTick(value);
    }

    public int getAttackTick2() {
        return this.stateComponent.getAttackTick2();
    }

    public void setAttackTick2(int value) {
        this.stateComponent.setAttackTick2(value);
    }

    public float getSwingTime(float partialTick) {
        return this.getAttackAnim(partialTick);
    }

    public boolean getIsSitting() {
        return this.isOrderedToSit() || this.isInSittingPose();
    }

    public boolean isCombatSuppressed() {
        return (
            this.getIsSitting() || this.stateComponent.getCraning() > 0
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

    public Player getOwnerPlayer() {
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

    public boolean isOwnerTooFar() {
        return this.movementHelper.isOwnerTooFar();
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

    public void spawnLightAttackMuzzleParticles(
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

    public void spawnLightAttackTargetParticles(
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

    protected ShipStateComponent createStateComponent() {
        return new ShipStateComponent(this);
    }

    @Override
    public ShipStateComponent getStateComponent() {
        return this.stateComponent;
    }



    public int[] getGuardedPos() {
        return new int[] {
            this.stateComponent.getGuardX(),
            this.stateComponent.getGuardY(),
            this.stateComponent.getGuardZ(),
            this.stateComponent.getGuardDim(),
            this.stateComponent.getGuardType(),
        };
    }

    public void setGuardedPos(int x, int y, int z, int dim, int type) {
        this.stateComponent.setGuardX(x);
        this.stateComponent.setGuardY(y);
        this.stateComponent.setGuardZ(z);
        this.stateComponent.setGuardDim(dim);
        this.stateComponent.setGuardType(type);
    }



    public boolean isStateMarried() {
        return this.stateComponent.isStateMarried();
    }

    public void setStateMarried(boolean value) {
        this.stateComponent.setStateMarried(value);
    }

    public boolean isStateNoEquip() {
        return this.stateComponent.isStateNoEquip();
    }

    public void setStateNoEquip(boolean value) {
        this.stateComponent.setStateNoEquip(value);
    }

    public boolean isStateCanMelee() {
        return this.stateComponent.isStateCanMelee();
    }

    public void setStateCanMelee(boolean value) {
        this.stateComponent.setStateCanMelee(value);
    }

    public boolean isStateLightAttack() {
        return this.stateComponent.isStateLightAttack();
    }

    public void setStateLightAttack(boolean value) {
        this.stateComponent.setStateLightAttack(value);
    }

    public boolean isStateHeavyAttack() {
        return this.stateComponent.isStateHeavyAttack();
    }

    public void setStateHeavyAttack(boolean value) {
        this.stateComponent.setStateHeavyAttack(value);
    }

    public boolean isStateLightAircraftAttack() {
        return this.stateComponent.isStateLightAircraftAttack();
    }

    public void setStateLightAircraftAttack(boolean value) {
        this.stateComponent.setStateLightAircraftAttack(value);
    }

    public boolean isStateHeavyAircraftAttack() {
        return this.stateComponent.isStateHeavyAircraftAttack();
    }

    public void setStateHeavyAircraftAttack(boolean value) {
        this.stateComponent.setStateHeavyAircraftAttack(value);
    }

    public boolean isStateRingEffect() {
        return this.stateComponent.isStateRingEffect();
    }

    public void setStateRingEffect(boolean value) {
        this.stateComponent.setStateRingEffect(value);
    }

    public boolean isStateGuiBtn1() {
        return !(this instanceof org.trp.shincolle.entity.EntityTransportWa)
            && !(this instanceof org.trp.shincolle.entity.EntityCarrierAkagi)
            && !(this instanceof org.trp.shincolle.entity.EntityCarrierKaga)
            && !(this instanceof org.trp.shincolle.entity.EntityCarrierWo)
            && !(this instanceof org.trp.shincolle.entity.EntityCarrierHime);
    }

    public void setStateGuiBtn1(boolean value) {
        this.stateComponent.setStateGuiBtn1(value);
    }

    public boolean isStateGuiBtn2() {
        return !(this instanceof org.trp.shincolle.entity.EntityTransportWa)
            && !this.supportsAircraftCombat();
    }

    public void setStateGuiBtn2(boolean value) {
        this.stateComponent.setStateGuiBtn2(value);
    }

    public boolean isStateGuiBtn3() {
        return this.supportsAircraftCombat();
    }

    public void setStateGuiBtn3(boolean value) {
        this.stateComponent.setStateGuiBtn3(value);
    }

    public boolean isStateGuiBtn4() {
        return this.supportsAircraftCombat();
    }

    public void setStateGuiBtn4(boolean value) {
        this.stateComponent.setStateGuiBtn4(value);
    }

    public boolean isStateAntiAir() {
        return this.stateComponent.isStateAntiAir();
    }

    public void setStateAntiAir(boolean value) {
        this.stateComponent.setStateAntiAir(value);
    }

    public boolean isStateCanRide() {
        return this.stateComponent.isStateCanRide();
    }

    public void setStateCanRide(boolean value) {
        this.stateComponent.setStateCanRide(value);
    }

    public boolean isStateAppearance() {
        return this.stateComponent.isStateAppearance();
    }

    public void setStateAppearance(boolean value) {
        this.stateComponent.setStateAppearance(value);
    }

    public boolean isStateAutoPump() {
        return this.stateComponent.isStateAutoPump();
    }

    public void setStateAutoPump(boolean value) {
        this.stateComponent.setStateAutoPump(value);
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
        EntityShipLegacyState state = this.stateComponent.getLegacyState();
        return state.getBoolean(state.updateFlag, index);
    }

    public void setUpdateFlag(int index, boolean value) {
        EntityShipLegacyState state = this.stateComponent.getLegacyState();
        state.setBoolean(state.updateFlag, index, value);
    }

    public byte[] getBodyHeightStand() {
        return this.stateComponent.getLegacyState().bodyHeightStand;
    }

    public byte[] getBodyHeightSit() {
        return this.stateComponent.getLegacyState().bodyHeightSit;
    }

    public float[] getModelPos() {
        return this.stateComponent.getLegacyState().modelPos;
    }

    public void setModelPos(float[] pos) {
        this.stateComponent.getLegacyState().applyModelPos(pos);
        this.refreshDimensions();
    }

    public BlockPos[] getWaypoints() {
        return this.stateComponent.getLegacyState().waypoints;
    }

    public void setWaypoints(BlockPos[] points) {
        this.stateComponent.getLegacyState().applyWaypoints(points);
    }

    public int getRidingState() {
        return this.entityData.get(LEGACY_RIDING_STATE);
    }

    public void setRidingState(int state) {
        this.entityData.set(LEGACY_RIDING_STATE, Math.max(0, state));
    }

    public int getGuardedPos(int index) {
        return switch (index) {
            case 0 -> this.stateComponent.getGuardX();
            case 1 -> this.stateComponent.getGuardY();
            case 2 -> this.stateComponent.getGuardZ();
            case 3 -> this.stateComponent.getGuardDim();
            case 4 -> this.stateComponent.getGuardType();
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
        int shipClassId = this.stateComponent.getShipClassId();
        net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
            org.trp.shincolle.Shincolle.MODID, "ship-idle-" + shipClassId
        );
        return SoundEvent.createVariableRangeEvent(id);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        int shipClassId = this.stateComponent.getShipClassId();
        net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
            org.trp.shincolle.Shincolle.MODID, "ship-hurt-" + shipClassId
        );
        return SoundEvent.createVariableRangeEvent(id);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        int shipClassId = this.stateComponent.getShipClassId();
        net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
            org.trp.shincolle.Shincolle.MODID, "ship-death-" + shipClassId
        );
        return SoundEvent.createVariableRangeEvent(id);
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

    public void playAttackSound() {
        this.audioHelper.playAttackSound();
    }

    public void playItemPickupSound() {
        this.audioHelper.playItemPickupSound();
    }

    public void playKnockbackSound() {
        this.audioHelper.playKnockbackSound();
    }

    public void playFeedSound() {
        this.audioHelper.playFeedSound();
    }

    @Override
    public boolean displayFireAnimation() {
        if (super.displayFireAnimation()) {
            return true;
        }
        return (this.getHealth() / this.getMaxHealth()) <= 0.25F;
    }

    public int getFuel() {
        return this.stateComponent.getFuel();
    }

    public void setFuel(int val) {
        boolean wasNoFuel = this.isNoFuel();
        this.stateComponent.setFuel(val);
        boolean isNoFuelNow = this.isNoFuel();
        if (wasNoFuel != isNoFuelNow) {
            this.updateFuelState(isNoFuelNow);
        }
    }

    public boolean getEquipFlag(String key) {
        return this.stateComponent.getEquipFlag(key);
    }

    public void setEquipFlag(String key, boolean value) {
        this.stateComponent.setEquipFlag(key, value);
    }

    CompoundTag copyEquipFlagsTag() {
        return this.stateComponent.copyEquipFlagsTag();
    }

    void setEquipFlagsTag(CompoundTag flags) {
        this.stateComponent.setEquipFlagsTag(flags);
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

    public boolean isHostile() {
        return isHostileShipMob();
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
        this.stateComponent.setStateAntiSub(true);
        this.stateComponent.setStatePvp(true);
        this.stateComponent.setStatePassiveAttack(true);
        this.stateComponent.setStateOnSight(false);
        this.stateComponent.setStatePickItem(false);
        this.stateComponent.setStateAutoPump(false);
        this.stateComponent.setFleeHp(0);
        this.randomizeEquipFlags();

        fillHostileAmmoLoadout();
        this.recalculateLegacyShipStats();
        this.setHealth(this.getMaxHealth());
        this.shipDeathTicks = 0;
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
        return this.stateComponent.getLegacyState();
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
    public boolean isNoAi() {
        return super.isNoAi() || stopAI;
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
    private transient boolean savingAsRoot = false;

    @Override
    public boolean isPassenger() {
        if (
            shincolle$isSavingChunkEntities.get() &&
            this.getVehicle() instanceof Player
        ) {
            return false;
        }
        if (this.savingAsRoot) {
            return false;
        }
        return super.isPassenger();
    }

    @Override
    public boolean startRiding(Entity vehicle, boolean force) {
        boolean result = super.startRiding(vehicle, force);
        if (result && !this.level().isClientSide() && vehicle != null) {
            if (vehicle instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(
                    new net.minecraft.network.protocol.game.ClientboundSetPassengersPacket(
                        serverPlayer
                    )
                );
            } else if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.getChunkSource().broadcast(
                    vehicle,
                    new net.minecraft.network.protocol.game.ClientboundSetPassengersPacket(
                        vehicle
                    )
                );
            }
        }
        return result;
    }

    @Override
    public void stopRiding() {
        Entity vehicle = this.getVehicle();
        super.stopRiding();
        if (!this.level().isClientSide() && vehicle != null) {
            if (vehicle instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(
                    new net.minecraft.network.protocol.game.ClientboundSetPassengersPacket(
                        serverPlayer
                    )
                );
            } else if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.getChunkSource().broadcast(
                    vehicle,
                    new net.minecraft.network.protocol.game.ClientboundSetPassengersPacket(
                        vehicle
                    )
                );
            }
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

    @Override
    public boolean canBeLeashed() {
        return !this.isHostileShipMob() && super.canBeLeashed();
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
                } else if (distSq > 1024.0D
                    && this.tickCount > minionDespawn
                    && this.getRandom().nextInt(800) == 0
                ) {
                    this.discard();
                    return true;
                }
            } else {
                this.discard();
                return true;
            }
        }
        return false;
    }

    private void tickCombatAndInventory() {
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
    }

    private void updateBossEvent() {
        if (this.bossEvent == null) {
            return;
        }
        if (this.isHostileShipMob() && this.getScaleLevel() > 1) {
            this.bossEvent.setName(this.getDisplayName());
            this.bossEvent.setProgress(Mth.clamp(this.getHealth() / this.getMaxHealth(), 0.0F, 1.0F));
        } else {
            if (!this.bossEvent.getPlayers().isEmpty()) {
                this.bossEvent.removeAllPlayers();
            }
        }
    }

    protected void tickAliveLogic() {
        this.protectAgainstSlopeFallAndVoid();
        this.emotions.tickEmotions();

        if (!this.level().isClientSide && tickHostileDespawn()) {
            return;
        }

        this.updateMountSummon();

        if (this.getIsSitting() || this.isInDeadPose()) {
            this.getNavigation().stop();
        }

        if (!this.isNoFuel()) {
            this.tickCombatAndInventory();
        } else {
            this.passiveCombat.clearTarget(true);
        }

        if (this.isAlive() && (this.tickCount & 7) == 0) {
            org.trp.shincolle.utility.TaskHelper.onUpdateTask(this);
        }

        this.updateBossEvent();

        this.movementHelper.tickSearchlightAssist();
        this.compassHelper.tickCompassChunkLoading();
        this.audioHelper.tickTimeKeepingSound();

        if (!this.level().isClientSide) {
            if ((this.tickCount % 32) == 0) {
                this.tickPeriodicEffects();
            }
            if ((this.tickCount % 128) == 0
                && this.isStateMarried()
                && this.isStateRingEffect()
                && !this.isNoFuel()
            ) {
                this.applyAuraEffects();
            }
        }
        this.suppliesHelper.tickFuelDecay();
        this.suppliesHelper.tickAutoRecovery();
        this.suppliesHelper.tickAutoSupplies();
        tickLegacyTimers();
        if ((this.tickCount % 16) == 0) {
            this.movementHelper.tickWaypointMove();
        }
        if ((this.tickCount % 40) == 0) {
            this.recalculateLegacyShipStats();
        }
    }

    private void protectAgainstSlopeFallAndVoid() {
        if (this.level().isClientSide) return;

        if (this.getY() < this.level().getMinBuildHeight() - 10) {
            Entity owner = this.getOwner();
            if (owner != null && owner.isAlive()) {
                this.teleportTo(owner.getX(), owner.getY() + 0.5D, owner.getZ());
            } else {
                this.teleportTo(this.getX(), Math.max((double) this.level().getSeaLevel() + 2.0D, this.getY() + 64.0D), this.getZ());
            }
            this.setDeltaMovement(Vec3.ZERO);
            this.setXRot(0.0F);
        }
    }

    @Override
    protected void tickDeath() {
        this.deathHelper.tickDeath();
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        if (this.bossEvent != null) {
            this.bossEvent.removeAllPlayers();
        }
        if (this.level() instanceof ServerLevel serverLevel) {
            this.compassHelper.clearCompassForcedChunks(serverLevel);
        }
        super.remove(reason);
    }


    public void tryFlareTarget(@Nullable Entity target) {
        if (target != null) {
            tryFlareTarget(target.blockPosition());
        }
    }

    public void tryFlareTarget(@Nullable Vec3 targetPos) {
        if (targetPos != null) {
            tryFlareTarget(BlockPos.containing(targetPos));
        }
    }

    public void tryFlareTarget(@Nullable BlockPos targetPos) {
        if (targetPos == null || !Config.canFlare || this.stateComponent.getEquipFlare() <= 0) {
            return;
        }
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (serverLevel.getBrightness(LightLayer.BLOCK, targetPos) < 11) {
            BlockHelper.placeLightBlock(serverLevel, targetPos);
        } else {
            BlockHelper.updateNearbyLightBlock(serverLevel, targetPos);
        }
    }

    protected boolean hasSearchlightEquip() {
        return this.stateComponent.getEquipSearchlight() > 0;
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

    public boolean useKaitaiHammer(Player player, ItemStack stack) {
        return this.kaitaiHelper.useKaitaiHammer(player, stack);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            InteractionResult result = this.interactionHelper.handleInteraction(player, hand);
            if (result != InteractionResult.PASS) {
                return result;
            }

            if (this.isTame() && this.isOwnedBy(player)) {
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

            return InteractionResult.PASS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (this.isCombatSuppressed()) {
            return false;
        }
        if (!this.isStateCanMelee()) {
            return false;
        }
        if (!this.level().isClientSide) {
            this.addShipExp(Config.shipExpGainMelee);
        }
        boolean result = super.doHurtTarget(target);
        if (result && !this.level().isClientSide) {
            this.decrMorale(0);
            this.setCombatTick(this.tickCount);
            this.playSound(
                ModSounds.SHIP_HIT.get(),
                this.getSoundVolume(),
                this.getShipSoundPitch()
            );
            this.playAttackSound();
            this.setAttackTick(50);
            applyEmotesReaction(3);
            if (target instanceof LivingEntity livingTarget) {
                this.applyAttackEffects(livingTarget);
            }
        }
        return result;
    }

    private boolean tryUseRepairGoddess(DamageSource source, float reduced) {
        if (this.level().isClientSide) {
            return false;
        }
        if (source.getEntity() instanceof Player p && p.getMainHandItem().is(ModItems.KAITAI_HAMMER.get())) {
            return false;
        }
        if (this.isDeadOrDying() || source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }
        if (reduced < this.getHealth()) {
            return false;
        }
        Entity attacker = source.getEntity();
        boolean isOwnerAttack = attacker instanceof Player && attacker.getUUID().equals(this.getOwnerUUID());
        if (isOwnerAttack) {
            return false;
        }
        if (this.consumeItemInInventory(ModItems.REPAIR_GODDESS.get())) {
            this.setHealth(this.getMaxHealth());
            this.customHurtTime = 120;
            this.spawnGoddessParticles();
            this.playFeedSound();
            return true;
        }
        return false;
    }

    private void handlePostHurtEffects(boolean hurtResult) {
        if (this.level().isClientSide || !hurtResult) {
            return;
        }
        this.decrMorale(5);
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

        if (tryUseRepairGoddess(source, reduced)) {
            return false;
        }

        boolean result = super.hurt(source, reduced);
        handlePostHurtEffects(result);
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
            this.legacyShipStats.getBuffedAttr(LegacyShipStats.STAT_DODGE) + getInvisibleDodgeBonus(),
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
        super.heal(amount * this.legacyShipStats.getBuffedAttr(LegacyShipStats.STAT_HEALING_MODIFIER));
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
            new EntityShipFollowOwnerGoal(this, 1.2D, 12.0F, 6.0F)
        );
        this.goalSelector.addGoal(4, new EntityAIShipGetOffChair(this));
        this.goalSelector.addGoal(5, new EntityAIShipSitOnChair(this, 1.0D));
        this.goalSelector.addGoal(6, new ShipLookAtPlayerGoal(this, 8.0F));
        this.goalSelector.addGoal(7, new ShipRandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new ShipRandomStrollGoal(this, 1.0D));
    }

    public void onInventoryChanged() {
        this.recalculateLegacyShipStats();
    }

    public int getMaxAircraftLight() {
        return this.combat.getMaxAircraftLight();
    }

    public int getMaxAircraftHeavy() {
        return this.combat.getMaxAircraftHeavy();
    }

    public boolean hasItemInInventory(net.minecraft.world.item.Item item) {
        return this.inventoryHelper.findItemInInventory(item) != -1;
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

    @Override
    public void doCalcShipAttributesAddEffect() {
        this.calcShipAttributesAddEffect();
    }

    protected float[] computeLegacyAuraBuffs() {
        return new float[21];
    }

    @Override
    public float[] doComputeLegacyAuraBuffs() {
        return this.computeLegacyAuraBuffs();
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
        return (this.tickCount + (this.stateComponent.getTimekeepLegacy() << 7)) & mask;
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
        return 0;
    }

    private void initializeLegacyState() {
        for (int i = 0; i < LEGACY_STATE_EMOTION_COUNT; i++) {
            setStateEmotion(i, getInitialLegacyEmotion(i), false);
        }
        this.stateComponent.setLegacyStateInitialized(true);
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
        return this.stateComponent.isLegacyStateInitialized();
    }

    void setLegacyStateInitializedInternal(boolean initialized) {
        this.stateComponent.setLegacyStateInitialized(initialized);
    }

    void resetDeathStateForSpawnEgg() {
        this.deathHelper.resetDeathStateForSpawnEgg();
    }

    public int getWpStayTimeMax() {
        return this.movementHelper.getWpStayTimeMax();
    }

    private void updateEmotionTimer() {
        int timer5 = this.stateComponent.getEmotionTimer();
        if (timer5 > 0) {
            timer5--;
            this.stateComponent.setEmotionTimer(timer5);
            if (timer5 == 0) {
                setStateEmotion(6, 0, false);
            }
        }
    }

    private void updateAudioTimer() {
        int timer6 = this.stateComponent.getAudioTimer();
        if (timer6 > 0) {
            timer6--;
            this.stateComponent.setAudioTimer(timer6);
        }
    }

    private void updateMountAttackCooldowns() {
        for (int i = 0; i < 4; i++) {
            int timer = this.stateComponent.getMountAttackCd(i);
            if (timer > 0) {
                this.stateComponent.setMountAttackCd(i, timer - 1);
            }
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
        this.suppliesHelper.tickSupplies();

        this.updateEmotionTimer();
        this.updateAudioTimer();
        this.updateMountAttackCooldowns();
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
            if (s.isAlive() && !s.equals(this) && (includeNonOwned || (owner != null && s.isOwnedBy(owner)))) {
                s.applyEmotesReaction(type);
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
        return (this.customSwingTicks + partialTick) / MAX_SWING_TICKS;
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

    public int getHitHeight() {
        return this.stateComponent.getHitHeight();
    }

    public void setHitHeight(int value) {
        this.stateComponent.setHitHeight(value);
    }

    public int getHitAngle() {
        return this.stateComponent.getHitAngle();
    }

    public void setHitAngle(int value) {
        this.stateComponent.setHitAngle(value);
    }

    public int getSensitiveBody() {
        return this.stateComponent.getSensitiveBody();
    }

    public void setSensitiveBody(int value) {
        this.stateComponent.setSensitiveBody(value);
    }

    private int getDefaultBodyZone(int h) {
        if (h > 92) return 0;
        if (h > 78) return 1;
        if (h > 73) return 2;
        if (h > 58) return 3;
        if (h > 47) return 4;
        if (h > 37) return 5;
        return 6;
    }

    public int getBodyZoneFromHeight() {
        int h = getHitHeight();
        byte[] bhs = getBodyHeightStand();
        if (bhs == null || bhs.length < 6) {
            return getDefaultBodyZone(h);
        }
        for (int i = 0; i < 6; i++) {
            if (h > (bhs[i] & 0xFF)) {
                return i;
            }
        }
        return 6;
    }

    public int getHitBodyID() {
        int zone = getBodyZoneFromHeight();
        int angle = getHitAngle();
        int side;
        if (angle >= 250 && angle < 290) side = 3;
        else if (angle >= 110 && angle < 250) side = 2;
        else if (angle >= 70 && angle < 110) side = 1;
        else side = 0;

        int valueForZone3;
        if (side == 2) {
            valueForZone3 = 1;
        } else if (side == 0) {
            valueForZone3 = 5;
        } else {
            valueForZone3 = 10;
        }

        int valueForZone4;
        if (side == 2) {
            valueForZone4 = 6;
        } else if (side == 0) {
            valueForZone4 = 2;
        } else {
            valueForZone4 = 10;
        }

        return switch (zone) {
            case 0 -> 7;
            case 1 -> (side == 2) ? 4 : 8;
            case 2 -> 3;
            case 3 -> valueForZone3;
            case 4 -> valueForZone4;
            case 5 -> (side == 2) ? 0 : 2;
            default -> 9;
        };
    }

    public void checkCaressed() {
        int zone = getBodyZoneFromHeight();
        if (zone <= 3) {
            this.setStateEmotion(6, 1, false);
            this.stateComponent.setEmotionTimer(80);
        }
    }

    private net.minecraft.world.entity.LivingEntity aiTarget;

    public net.minecraft.world.entity.LivingEntity getAITarget() {
        return this.aiTarget;
    }

    public void setAITarget(net.minecraft.world.entity.LivingEntity target) {
        this.aiTarget = target;
    }

    public void pushAITarget() {
        if (this.aiTarget != null) {
            float yawRad = this.getYRot() * net.minecraft.util.Mth.DEG_TO_RAD;
            net.minecraft.world.phys.Vec3 push = new net.minecraft.world.phys.Vec3(
                -net.minecraft.util.Mth.sin(yawRad) * 0.5f,
                0.5f,
                net.minecraft.util.Mth.cos(yawRad) * 0.5f
            );
            this.aiTarget.hasImpulse = true;
            this.aiTarget.hurtMarked = true;
            this.aiTarget.setDeltaMovement(this.aiTarget.getDeltaMovement().add(push));
            this.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
            this.playKnockbackSound();
        }
    }

    public void attackAITarget() {
        if (this.aiTarget != null) {
            float damage = (float) this.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE);
            if (damage <= 0.0F) {
                damage = 2.0F;
            }
            this.aiTarget.hurt(this.damageSources().mobAttack(this), damage);
        }
    }

    public void interactPointer(
        net.minecraft.world.entity.player.Player player
    ) {
        this.setAITarget(player);
        boolean isOwner = this.isOwnedBy(player);
        if (isOwner && !this.isInDeadPose()) {
            if (this.getMorale() < CARESS_MORALE_CAP) {
                this.addMorale(Config.baseCaressMorale);
            }
            this.applyEmotesReaction(0);
        } else {
            this.applyEmotesReaction(1);
        }
        this.setAITarget(null);
    }

    public FaceExpressionConfig getFaceExpressionConfig() {
        return this.faceExpressionConfig;
    }

    protected FaceExpressionConfig createFaceExpressionConfig() {
        return null;
    }

    protected FaceExpressionConfig createSimpleFaceConfig(int eyesOpen, int eyesHalf, int eyesClosed) {
        return FaceExpressionConfig.builder()
            .normal(new FaceTimeline(0, new FaceStep[0], eyesOpen, MOUTH_FRONT_0))
            .cry(new FaceTimeline(0, new FaceStep[0], eyesHalf, MOUTH_FRONT_0))
            .damaged(new FaceTimeline(0, new FaceStep[0], eyesHalf, MOUTH_FRONT_0))
            .scorn(new FaceTimeline(0, new FaceStep[0], eyesHalf, MOUTH_FRONT_0))
            .hungry(new FaceTimeline(0, new FaceStep[0], eyesHalf, MOUTH_FRONT_0))
            .angry(new FaceTimeline(0, new FaceStep[0], eyesOpen, MOUTH_FRONT_0))
            .bored(new FaceTimeline(0, new FaceStep[0], eyesClosed, MOUTH_FRONT_0))
            .shy(new FaceTimeline(0, new FaceStep[0], eyesOpen, MOUTH_FRONT_0))
            .happy(new FaceTimeline(0, new FaceStep[0], eyesOpen, MOUTH_FRONT_0))
            .build();
    }
}
