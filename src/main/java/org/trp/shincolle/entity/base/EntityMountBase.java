package org.trp.shincolle.entity.base;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.path.ShipLegacyNavigation;
import org.trp.shincolle.entity.base.path.ShipMoveControl;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityMountBase extends PathfinderMob {

    public static boolean stopAI = false;

    private static final EntityDataAccessor<Optional<UUID>> HOST_UUID =
        SynchedEntityData.defineId(
            EntityMountBase.class,
            EntityDataSerializers.OPTIONAL_UUID
        );
    private static final EntityDataAccessor<Integer> STATE_EMOTION =
        SynchedEntityData.defineId(
            EntityMountBase.class,
            EntityDataSerializers.INT
        );

    private static final double SHIP_FLOATING_DEPTH = 0.3;
    private static final double BUOY_MIN_DEPTH = 0.15;
    private static final double BUOY_COEFF = 0.035;
    private static final double BUOY_EXPONENT = 0.6;
    private static final double BUOY_OFFSET = 0.005;
    private static final double BUOY_DAMP = 0.80;
    private static final double BUOY_MAX_MOTION = 0.1;

    protected float[] seatPos = { 0.0f, 0.0f, 0.0f };
    protected float[] seatPos2 = { 0.0f, 0.0f, 0.0f };
    protected double shipDepth = 0.0;
    public int keyPressed = 0;
    public int keyTick = 0;

    public boolean isSubmarineMode = false;

    @Nullable
    protected EntityShipBase host;

    private int lightAttackCooldown = 0;
    private int heavyAttackCooldown = 0;

    protected EntityMountBase(
        EntityType<? extends PathfinderMob> type,
        Level level
    ) {
        super(type, level);
        this.setPathfindingMalus(
            net.minecraft.world.level.pathfinder.PathType.WATER,
            0.0F
        );
        this.setPathfindingMalus(
            net.minecraft.world.level.pathfinder.PathType.WATER_BORDER,
            0.0F
        );
        this.moveControl = new ShipMoveControl(this, 30.0F);
        this.navigation = new ShipLegacyNavigation(this, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MountFollowHostGoal(this));
        this.goalSelector.addGoal(11, new MountRangeAttackGoal(this));
        this.goalSelector.addGoal(12, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(25, new RandomStrollGoal(this, 0.8));
    }

    private void applyWaterBuoyancy() {
        double depth = this.shipDepth;
        double upward = 0.0;
        if (depth > BUOY_MIN_DEPTH) {
            upward = BUOY_COEFF * Math.pow(depth, BUOY_EXPONENT) - BUOY_OFFSET;
        }
        Vec3 dm = this.getDeltaMovement();
        double newY = (dm.y + upward) * BUOY_DAMP;
        newY = Mth.clamp(newY, -BUOY_MAX_MOTION, BUOY_MAX_MOTION);
        this.setDeltaMovement(dm.x, newY, dm.z);
    }

    private static final class MountFollowHostGoal extends Goal {

        private static final int DEFAULT_TP_COOLDOWN = 200;
        private static final double DEFAULT_TP_DIST_SQ = 256.0;
        private static final int BASE_COOLDOWN = 64;
        private static final int MIN_COOLDOWN = BASE_COOLDOWN / 4;
        private static final double MIN_RANGE = 2.0 * 2.0;
        private static final double MAX_RANGE = 32.0 * 32.0;

        private final EntityMountBase mount;
        private int findCooldown = 0;
        private int checkTP_T = 0;
        private int checkTP_D = 0;
        private double lastOwnerX;
        private double lastOwnerY;
        private double lastOwnerZ;
        private boolean hasOwnerPos;
        private boolean[] formationDir = new boolean[]{false, true};

        MountFollowHostGoal(EntityMountBase mount) {
            this.mount = mount;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        private boolean canFollow(EntityShipBase h) {
            return h != null
                && h.isAlive()
                && !h.isOrderedToSit()
                && !h.isInSittingPose()
                && !h.isInDeadPose()
                && !mount.isPassenger()
                && mount.getControllingPassenger() == null;
        }

        @Override
        public boolean canUse() {
            EntityShipBase h = mount.getHost();
            if (!canFollow(h)) return false;

            if (h.hasPointerTarget()) return true;
            if (h.getGuardedPos(4) == 1 || h.hasPointerTargetEntity()) return false;

            LivingEntity owner = h.getOwner();
            if (owner == null || owner.level() != mount.level()) return false;

            if (owner instanceof Player player && h.playerHasCombatRation(player)) {
                return mount.distanceToSqr(owner) > 1.5 * 1.5;
            }

            Vec3 moveTarget = resolveMoveTarget(h, owner);
            double distSq = mount.distanceToSqr(moveTarget.x, moveTarget.y, moveTarget.z);
            if (hasActiveFormation(h, owner)) {
                return distSq > 7.0D;
            }

            float minDist = resolveFollowMinDistance(h);
            float maxDist = resolveFollowMaxDistance(h, minDist);
            double maxDistSq = maxDist * maxDist + mount.getBbWidth() * 0.75F;
            return distSq > maxDistSq;
        }

        @Override
        public boolean canContinueToUse() {
            EntityShipBase h = mount.getHost();
            if (!canFollow(h)) return false;

            if (h.hasPointerTarget()) return true;
            if (h.getGuardedPos(4) == 1 || h.hasPointerTargetEntity()) return false;

            LivingEntity owner = h.getOwner();
            if (owner == null || owner.level() != mount.level()) return false;

            if (owner instanceof Player player && h.playerHasCombatRation(player)) {
                return mount.distanceToSqr(owner) > 1.5 * 1.5;
            }

            Vec3 moveTarget = resolveMoveTarget(h, owner);
            double distSq = mount.distanceToSqr(moveTarget.x, moveTarget.y, moveTarget.z);
            if (hasActiveFormation(h, owner)) {
                return distSq > 4.0D;
            }

            float minDist = resolveFollowMinDistance(h);
            double minDistSq = minDist * minDist + mount.getBbWidth() * 0.75F;
            return distSq > minDistSq;
        }

        @Override
        public void start() {
            this.findCooldown = 0;
            this.checkTP_T = 0;
            this.checkTP_D = 0;
            this.hasOwnerPos = false;
        }

        @Override
        public void stop() {
            mount.getNavigation().stop();
        }

        @Override
        public void tick() {
            EntityShipBase h = mount.getHost();
            if (!canFollow(h)) {
                this.stop();
                return;
            }

            --this.findCooldown;

            if (h.hasPointerTarget()) {
                Vec3 pt = h.getPointerTarget();
                if (this.findCooldown <= 0 || mount.getNavigation().isDone()) {
                    this.findCooldown = MIN_COOLDOWN;
                    mount.getNavigation().moveTo(pt.x, pt.y, pt.z, 1.0);
                }
                return;
            }

            LivingEntity owner = h.getOwner();
            if (owner == null || owner.level() != mount.level()) {
                this.stop();
                return;
            }

            ++this.checkTP_T;
            boolean hasRation = owner instanceof Player player && h.playerHasCombatRation(player);
            mount.getLookControl().setLookAt(owner, 30.0F, 30.0F);

            Vec3 moveTarget = hasRation ? owner.position() : resolveMoveTarget(h, owner);
            double distSq = mount.distanceToSqr(moveTarget.x, moveTarget.y, moveTarget.z);
            float minDist = resolveFollowMinDistance(h);
            float maxDist = resolveFollowMaxDistance(h, minDist);
            double minDistSq = hasRation
                ? (1.5D * 1.5D)
                : (hasActiveFormation(h, owner) ? 4.0D : (minDist * minDist + mount.getBbWidth() * 0.75F));
            double maxDistSq = hasActiveFormation(h, owner) ? 7.0D : (maxDist * maxDist + mount.getBbWidth() * 0.75F);

            if (distSq <= minDistSq) {
                mount.getNavigation().stop();
            } else if (this.findCooldown <= 0 || mount.getNavigation().isDone()) {
                this.findCooldown = calcCooldown(maxDistSq);
                mount.getNavigation().moveTo(moveTarget.x, moveTarget.y, moveTarget.z, 1.0);
            }

            if (!org.trp.shincolle.Config.canTeleport) {
                return;
            }

            int tpCooldown = org.trp.shincolle.Config.shipTeleport.length > 0
                ? org.trp.shincolle.Config.shipTeleport[0]
                : DEFAULT_TP_COOLDOWN;
            double configTpDistSq = org.trp.shincolle.Config.shipTeleport.length > 1
                ? org.trp.shincolle.Config.shipTeleport[1]
                : DEFAULT_TP_DIST_SQ;
            double tpDistSq = Math.max(configTpDistSq, maxDistSq);

            if (distSq > tpDistSq) {
                ++this.checkTP_D;
                if (this.checkTP_D > tpCooldown) {
                    this.checkTP_D = 0;
                    applyTeleport(owner);
                    return;
                }
            } else {
                this.checkTP_D = 0;
            }

            if (this.checkTP_T > tpCooldown) {
                this.checkTP_T = 0;
                applyTeleport(owner);
            }
        }

        private int calcCooldown(double maxDistSq) {
            double clamped = Mth.clamp(maxDistSq, MIN_RANGE, MAX_RANGE);
            double ratio = (clamped - MIN_RANGE) / (MAX_RANGE - MIN_RANGE);
            return (int) (MIN_COOLDOWN + ratio * (BASE_COOLDOWN - MIN_COOLDOWN));
        }

        private float resolveFollowMinDistance(EntityShipBase h) {
            int configured = h.getStateComponent().getFollowMin();
            if (configured <= 0) {
                return 6.0F;
            }
            return (float) Mth.clamp(configured, 1, 31);
        }

        private float resolveFollowMaxDistance(EntityShipBase h, float minDist) {
            int configured = h.getStateComponent().getFollowMax();
            if (configured <= 0) {
                return Math.max(12.0F, minDist + 1.0F);
            }
            int minValue = Math.max(2, Mth.floor(minDist) + 1);
            return (float) Mth.clamp(configured, minValue, 32);
        }

        private boolean hasActiveFormation(EntityShipBase h, LivingEntity owner) {
            int teamId = h.getFormationTeam();
            int slotId = h.getFormationSlot();
            if (teamId >= 0 && slotId >= 0) {
                org.trp.shincolle.attachment.AdmiralData data = owner.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
                return data.getFormationID(teamId) > 0;
            }
            return false;
        }

        private Vec3 resolveMoveTarget(EntityShipBase h, LivingEntity owner) {
            int teamId = h.getFormationTeam();
            int slotId = h.getFormationSlot();
            if (teamId >= 0 && slotId >= 0) {
                org.trp.shincolle.attachment.AdmiralData data = owner.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
                int formationId = data.getFormationID(teamId);
                if (formationId > 0) {
                    updateFormationDirection(owner);
                    return org.trp.shincolle.utility.FormationHelper.getFormationPos(formationId, slotId, owner.position(), formationDir[0], formationDir[1]);
                }
            }
            return owner.position();
        }

        private void updateFormationDirection(LivingEntity owner) {
            double ox = owner.getX();
            double oy = owner.getY();
            double oz = owner.getZ();
            if (!this.hasOwnerPos) {
                this.lastOwnerX = ox;
                this.lastOwnerY = oy;
                this.lastOwnerZ = oz;
                this.hasOwnerPos = true;
                return;
            }
            double dx = this.lastOwnerX - ox;
            double dy = this.lastOwnerY - oy;
            double dz = this.lastOwnerZ - oz;
            double dsq = dx * dx + dy * dy + dz * dz;
            if (dsq > 7.0D) {
                this.formationDir = org.trp.shincolle.utility.FormationHelper.getFormationDirection(ox, oz, this.lastOwnerX, this.lastOwnerZ);
                this.lastOwnerX = ox;
                this.lastOwnerY = oy;
                this.lastOwnerZ = oz;
            }
        }

        private void applyTeleport(LivingEntity owner) {
            double tx = owner.getX();
            double ty = owner.getY() + 0.75;
            double tz = owner.getZ();
            if (mount.level() instanceof ServerLevel serverLevel) {
                int cx = Mth.floor(tx) >> 4;
                int cz = Mth.floor(tz) >> 4;
                if (!serverLevel.hasChunk(cx, cz)) {
                    return;
                }
                mount.getNavigation().stop();
                mount.teleportTo(tx, ty, tz);
                this.checkTP_T = 0;
                this.checkTP_D = 0;
            }
        }
    }
    private static final class MountRangeAttackGoal extends Goal {

        private final EntityMountBase mount;
        private LivingEntity target;
        private int aimTick = 0;
        private int lightDelay = 0;
        private int heavyDelay = 0;
        private int lightAircraftDelay = 0;
        private int heavyAircraftDelay = 0;
        private boolean aircraftLaunchTypeLight = true;

        MountRangeAttackGoal(EntityMountBase mount) {
            this.mount = mount;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            EntityShipBase h = mount.getHost();
            if (
                h == null || h.isOrderedToSit() || mount.isPassenger()
            ) return false;
            
            target = h.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public boolean canContinueToUse() {
            return canUse();
        }

        @Override
        public void start() {
            aimTick = 0;
            lightDelay = 0;
            heavyDelay = 0;
            lightAircraftDelay = 0;
            heavyAircraftDelay = 0;
        }

        @Override
        public void stop() {
            target = null;
        }

        @Override
        public void tick() {
            if (target == null || !target.isAlive()) return;
            EntityShipBase h = mount.getHost();
            if (h == null) return;

            mount.getLookControl().setLookAt(target, 30.0F, 30.0F);
            ++aimTick;
            if (lightDelay > 0) --lightDelay;
            if (heavyDelay > 0) --heavyDelay;
            if (lightAircraftDelay > 0) --lightAircraftDelay;
            if (heavyAircraftDelay > 0) --heavyAircraftDelay;

            int aimRequired =
                (int) ((20.0f * (150 - h.getLevel())) / 150.0f) + 10;
            if (aimTick < aimRequired) return;

            double rangeSq = Math.pow(
                Math.max(1.0, h.getLegacyShipStats().getAttackRange()),
                2
            );
            if (mount.distanceToSqr(target) > rangeSq * 1.5) return;

            
            if (
                h.isStateLightAttack() &&
                h.getAmmoLight() > 0 &&
                lightDelay <= 0
            ) {
                h.performLightAttack(target);
                lightDelay = h.getLegacyShipStats().getLightDelay();
            }
            if (
                h.isStateHeavyAttack() &&
                h.getAmmoHeavy() > 0 &&
                heavyDelay <= 0
            ) {
                h.performHeavyAttack(target);
                heavyDelay = h.getLegacyShipStats().getHeavyDelay();
            }

            
            if (h.supportsAircraftCombat()) {
                if (this.aircraftLaunchTypeLight && lightAircraftDelay <= 0) {
                    if (h.performLightAircraftAttack(target)) {
                        int delay = h.getLegacyShipStats().getLightDelay();
                        lightAircraftDelay = delay;
                        heavyAircraftDelay = delay;
                        this.aircraftLaunchTypeLight = false;
                    }
                } else if (
                    !this.aircraftLaunchTypeLight && heavyAircraftDelay <= 0
                ) {
                    if (h.performHeavyAircraftAttack(target)) {
                        int delay = h.getLegacyShipStats().getHeavyDelay();
                        lightAircraftDelay = delay;
                        heavyAircraftDelay = delay;
                        this.aircraftLaunchTypeLight = true;
                    }
                }
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 4.0)
            .add(Attributes.MOVEMENT_SPEED, 0.3)
            .add(Attributes.FOLLOW_RANGE, 64.0)
            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
            .add(Attributes.STEP_HEIGHT, 3.0)
            .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    public boolean isNoAi() {
        return super.isNoAi() || stopAI;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HOST_UUID, Optional.empty());
        builder.define(STATE_EMOTION, 0);
    }

    public void setHostUUID(@Nullable UUID uuid) {
        this.entityData.set(HOST_UUID, Optional.ofNullable(uuid));
    }

    @Nullable
    public UUID getHostUUID() {
        return this.entityData.get(HOST_UUID).orElse(null);
    }

    @Nullable
    public EntityShipBase getHost() {
        if (this.host == null) {
            for (Entity p : this.getPassengers()) {
                if (p instanceof EntityShipBase ship) {
                    this.host = ship;
                    break;
                }
            }
        }
        return this.host;
    }

    public void setStateEmotion(int value) {
        this.entityData.set(STATE_EMOTION, value);
    }

    public int getStateEmotion() {
        return this.entityData.get(STATE_EMOTION);
    }

    public double getShipDepth() {
        return this.shipDepth;
    }

    @Override
    public void tick() {
        double fluidH = this.getFluidHeight(net.minecraft.tags.FluidTags.WATER);
        this.shipDepth = fluidH;

        if (this.isSubmarineMode && fluidH <= 0.4) {
            this.isSubmarineMode = false;
        }

        if (this.isInWater() && !this.isPassenger() && !this.isSubmarineMode) {
            if (this.isVehicle() || this.getNavigation().isDone()) {
                applyWaterBuoyancy();
            }
        }

        super.tick();

        if ((this.tickCount & 0x7F) == 0) this.setAirSupply(300);

        if (this.level().isClientSide) {
            updateClientLogic();
        } else {
            updateServerLogic();
        }

        handleMovement();
    }

    protected void updateClientLogic() {
        updateShipDepthClient();
        spawnMovingParticle();
    }

    private void updateShipDepthClient() {
        double fluidH = this.getFluidHeight(net.minecraft.tags.FluidTags.WATER);
        if (fluidH > 0.0) {
            this.shipDepth = fluidH;
        } else {
            this.shipDepth = 0.0;
        }
    }

    private void spawnMovingParticle() {
        if (this.shipDepth <= 0.0) return;
        double motX = this.getX() - this.xo;
        double motZ = this.getZ() - this.zo;
        double limit = 0.25;
        motX = Mth.clamp(motX, -limit, limit);
        motZ = Mth.clamp(motZ, -limit, limit);
        if (motX != 0.0 || motZ != 0.0) {
            double width = this.getBbWidth();
            int amount = 2 + this.random.nextInt(3);
            for (int i = 0; i < amount; i++) {
                double px =
                    this.getX() +
                    motX * 3.0 +
                    (this.random.nextDouble() - 0.5) * width;
                double py =
                    this.getY() +
                    0.6 +
                    (this.random.nextDouble() - 0.5) * width * 0.15;
                double pz =
                    this.getZ() +
                    motZ * 3.0 +
                    (this.random.nextDouble() - 0.5) * width;
                double vx = -motX * 1.5;
                double vz = -motZ * 1.5;
                this.level().addParticle(
                    ParticleTypes.CLOUD,
                    px,
                    py,
                    pz,
                    vx,
                    0.0,
                    vz
                );
            }
        }
    }

    protected void updateServerLogic() {
        if (!checkHostExistence()) return;

        if (lightAttackCooldown > 0) --lightAttackCooldown;
        if (heavyAttackCooldown > 0) --heavyAttackCooldown;

        if ((this.tickCount & 0x1F) == 0) {
            syncWithHost();
        }
    }

    protected boolean checkHostExistence() {
        UUID uuid = getHostUUID();
        if (uuid == null) {
            this.discard();
            return false;
        }

        if (this.host == null || this.host.isRemoved()) {
            Entity entity = ((ServerLevel) this.level()).getEntity(uuid);
            if (entity instanceof EntityShipBase ship) {
                this.host = ship;
            } else {
                this.discard();
                return false;
            }
        }

        if (this.host.getVehicle() != this) {
            this.discard();
            return false;
        }
        return true;
    }

    protected void syncWithHost() {
        if (this.host == null) return;

        float hostMaxHP = this.host.getMaxHealth();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(hostMaxHP * 0.5);

        double hostSpeed = this.host.getAttributeValue(
            Attributes.MOVEMENT_SPEED
        );
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(hostSpeed);

        float kr = this.host.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_KNOCKBACK_RESISTANCE);
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(
            Mth.clamp(kr, 0.0f, 1.0f)
        );

        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(64.0);
    }

    protected void handleMovement() {
        if (this.keyTick > 0) {
            --this.keyTick;
            LivingEntity rider = getControllingPassenger();
            if (rider != null) {
                this.setYRot(rider.getYRot());
                this.yRotO = rider.yRotO;
                this.setXRot(rider.getXRot());
                this.xRotO = rider.xRotO;
                this.yBodyRot = rider.yBodyRot;
                this.yBodyRotO = rider.yBodyRotO;
                this.yHeadRot = rider.getYHeadRot();
                this.yHeadRotO = rider.yHeadRotO;
            }
        } else if (
            Math.abs(this.getX() - this.xo) > 0.001 ||
            Math.abs(this.getZ() - this.zo) > 0.001
        ) {
            handleAIMovementRotation();
        } else {
            syncRotationWithHost();
        }
    }

    protected void handleAIMovementRotation() {
        double dx = this.getX() - this.xo;
        double dz = this.getZ() - this.zo;
        float yaw = (float) (Mth.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;
        this.setYRot(yaw);
        this.yRotO = yaw;
        this.yBodyRot = yaw;
        this.yHeadRot = yaw;
        syncHostToMount();
    }

    protected void syncRotationWithHost() {
        EntityShipBase h = getHost();
        if (h != null) {
            this.setYRot(h.getYRot());
            this.yRotO = h.yRotO;
            this.yBodyRot = h.yBodyRot;
            this.yBodyRotO = h.yBodyRotO;
            this.yHeadRot = h.getYHeadRot();
            this.yHeadRotO = h.yHeadRotO;
            this.setXRot(h.getXRot());
            this.xRotO = h.xRotO;
        }
    }

    protected void syncHostToMount() {
        EntityShipBase h = getHost();
        if (h != null) {
            h.setYRot(this.getYRot());
            h.yRotO = this.yRotO;
            h.yBodyRot = this.yBodyRot;
            h.yBodyRotO = this.yBodyRotO;
            h.yHeadRot = this.yHeadRot;
            h.yHeadRotO = this.yHeadRotO;
            h.setXRot(this.getXRot());
            h.xRotO = this.xRotO;
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (
            this.host != null &&
            (this.host.isOrderedToSit() || this.host.isInSittingPose()) &&
            getControllingPassenger() == null
        ) {
            this.setDeltaMovement(Vec3.ZERO);
            this.setSpeed(0.0F);
            super.travel(Vec3.ZERO);
            return;
        }

        if (this.isAlive()) {
            LivingEntity rider = getControllingPassenger();
            if (rider != null) {
                this.setYRot(rider.getYRot());
                this.yRotO = this.getYRot();
                float visualPitch = rider.getXRot();
                this.yBodyRot = rider.yBodyRot;
                this.yHeadRot = rider.getYHeadRot();

                float strafe = rider.xxa * 0.5F;
                float forward = rider.zza;
                if (forward <= 0.0F) forward *= 0.25F;

                this.setSpeed(
                    (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED)
                );

                if (rider.getXRot() > 60.0F && forward > 0.0F) {
                    this.isSubmarineMode = true;
                }

                if (
                    this.isInWaterOrBubble() &&
                    (forward != 0 || strafe != 0) &&
                    this.tickCount % 2 == 0 &&
                    this.level() instanceof ServerLevel sl
                ) {
                    double motX = this.getX() - this.xo;
                    double motZ = this.getZ() - this.zo;
                    double limit = 0.25;
                    motX = Mth.clamp(motX, -limit, limit);
                    motZ = Mth.clamp(motZ, -limit, limit);
                    double width = this.getBbWidth();
                    int amount = 2 + this.random.nextInt(3);
                    for (int i = 0; i < amount; i++) {
                        double px =
                            this.getX() +
                            motX * 3.0 +
                            (this.random.nextDouble() - 0.5) * width;
                        double py =
                            this.getY() +
                            0.6 +
                            (this.random.nextDouble() - 0.5) * width * 0.15;
                        double pz =
                            this.getZ() +
                            motZ * 3.0 +
                            (this.random.nextDouble() - 0.5) * width;
                        double vx = -motX * 1.5;
                        double vz = -motZ * 1.5;
                        sl.sendParticles(
                            ParticleTypes.CLOUD,
                            px,
                            py,
                            pz,
                            0,
                            vx,
                            0.0,
                            vz,
                            1.0
                        );
                    }
                }

                float travelPitch = 0.0F;
                if (rider.getXRot() > 60.0F || rider.getXRot() < -60.0F) {
                    travelPitch = visualPitch;
                }
                this.setXRot(travelPitch);
                this.setRot(this.getYRot(), this.getXRot());

                super.travel(new Vec3(strafe, travelVector.y, forward));

                if (this.isSubmarineMode) {
                    Vec3 currentMotion = this.getDeltaMovement();

                    if (forward > 0.0F) {
                        double pitchRadians = Math.toRadians(rider.getXRot());
                        double speed = this.getAttributeValue(
                            Attributes.MOVEMENT_SPEED
                        );
                        double verticalSpeed = -Math.sin(pitchRadians) * speed;

                        this.setDeltaMovement(
                            currentMotion.x,
                            verticalSpeed,
                            currentMotion.z
                        );
                    } else {
                        this.setDeltaMovement(
                            currentMotion.x,
                            0.0,
                            currentMotion.z
                        );
                    }
                }

                this.setXRot(visualPitch);
                this.setRot(this.getYRot(), this.getXRot());
                this.calculateEntityAnimation(false);
                return;
            }
        }
        super.travel(travelVector);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        for (Entity p : this.getPassengers()) {
            if (p instanceof Player player) return player;
        }
        return null;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(
        Entity passenger,
        EntityDimensions dimensions,
        float scale
    ) {
        float[] pos = (passenger instanceof EntityShipBase)
            ? seatPos
            : seatPos2;
        float radians = this.yBodyRot * (float) (Math.PI / 180.0);
        float cosR = Mth.cos(radians);
        float sinR = Mth.sin(radians);
        float rz = pos[0] * cosR + pos[2] * sinR;
        float rx = pos[2] * cosR - pos[0] * sinR;
        return new Vec3(rx, pos[1], rz);
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return true;
    }

    @Override
    public boolean shouldRiderFaceForward(Player player) {
        return true;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.level().isClientSide) return InteractionResult.SUCCESS;

        if (player.isSecondaryUseActive()) {
            if (this.host != null && this.host.isOwnedBy(player)) {
                this.host.mobInteract(player, hand);
                return InteractionResult.SUCCESS;
            }
        } else {
            if (this.distanceToSqr(player) < 16.0) {
                player.startRiding(this, true);
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.level().isClientSide) return false;

        if (this.host == null) {
            this.discard();
            return false;
        }

        if (
            source.is(DamageTypeTags.IS_FALL) ||
            source.is(DamageTypes.IN_WALL) ||
            source.is(DamageTypes.STARVE) ||
            source.is(DamageTypes.CACTUS) ||
            source.is(DamageTypeTags.IS_DROWNING) ||
            source.is(DamageTypeTags.NO_ANGER)
        ) {
            return false;
        }

        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            this.discard();
            return false;
        }

        Entity attacker = source.getEntity();
        if (attacker != null && attacker.equals(this)) {
            this.host.setOrderedToSit(false);
            return false;
        }

        float mountArmor = this.host.getLegacyShipStats().getArmor() * 0.5F;
        float reduced =
            amount *
            (1.0F - mountArmor + (this.random.nextFloat() * 0.5F - 0.25F));

        if (reduced > 0.0F && reduced < 1.0F) {
            reduced = 1.0F;
        } else if (reduced < 0.0F) {
            reduced = 0.0F;
        }

        if (reduced <= 0.0f) return false;

        this.host.setOrderedToSit(false);

        if (this.random.nextInt(5) == 0) {
            this.host.applyEmotesReaction(2);
        }

        return super.hurt(source, reduced);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        UUID uuid = getHostUUID();
        if (uuid != null) compound.putUUID("HostUUID", uuid);
        compound.putInt("StateEmotion", getStateEmotion());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.hasUUID("HostUUID")) setHostUUID(
            compound.getUUID("HostUUID")
        );
        setStateEmotion(compound.getInt("StateEmotion"));
    }

    public float[] getSeatPos() {
        return this.seatPos;
    }

    public void setSeatPos(float x, float y, float z) {
        this.seatPos[0] = x;
        this.seatPos[1] = y;
        this.seatPos[2] = z;
    }

    public void setSeatPos2(float x, float y, float z) {
        this.seatPos2[0] = x;
        this.seatPos2[1] = y;
        this.seatPos2[2] = z;
    }
}
