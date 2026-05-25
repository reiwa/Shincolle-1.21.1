package org.trp.shincolle.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

class EntityShipBasePointer {
    private static final double POINTER_ENTITY_ATTACK_RANGE_SQR = 4.0D;
    private static final double POINTER_ENTITY_PATH_REFRESH_DISTANCE_SQR = 1.0D;
    private static final int POINTER_ENTITY_PATH_RECALC_INTERVAL = 10;
    private static final double POINTER_ENTITY_MOVE_SPEED = 1.1D;

    private final EntityShipBase ship;

    private Vec3 pointerTarget;
    private long pointerTargetUntil;
    private boolean pointerAlongX;
    private boolean pointerFaceP;

    private UUID pointerTargetEntityId;
    private long pointerTargetEntityUntil;

    private int pointerTargetEntityAttackTick = 0;
    private int pointerTargetEntityLightShotTick = 0;
    private int pointerTargetEntityHeavyShotTick = 0;
    private int pointerTargetEntityPathTick = 0;

    EntityShipBasePointer(EntityShipBase ship) {
        this.ship = ship;
    }

    void saveToNbt(CompoundTag compound) {
        if (this.pointerTarget != null) {
            long remaining = Math.max(0L, this.pointerTargetUntil - this.ship.level().getGameTime());
            if (remaining > 0L) {
                CompoundTag targetTag = new CompoundTag();
                targetTag.putDouble("X", this.pointerTarget.x);
                targetTag.putDouble("Y", this.pointerTarget.y);
                targetTag.putDouble("Z", this.pointerTarget.z);
                targetTag.putLong("Remaining", remaining);
                targetTag.putBoolean("AlongX", this.pointerAlongX);
                targetTag.putBoolean("FaceP", this.pointerFaceP);
                compound.put("PointerTarget", targetTag);
            }
        }
        if (this.pointerTargetEntityId != null) {
            long remaining = Math.max(0L, this.pointerTargetEntityUntil - this.ship.level().getGameTime());
            if (remaining > 0L) {
                CompoundTag targetTag = new CompoundTag();
                targetTag.putUUID("Id", this.pointerTargetEntityId);
                targetTag.putLong("Remaining", remaining);
                compound.put("PointerTargetEntity", targetTag);
            }
        }
    }

    void loadFromNbt(CompoundTag compound) {
        if (compound.contains("PointerTarget")) {
            CompoundTag targetTag = compound.getCompound("PointerTarget");
            double x = targetTag.getDouble("X");
            double y = targetTag.getDouble("Y");
            double z = targetTag.getDouble("Z");
            long remaining = targetTag.getLong("Remaining");
            if (remaining > 0L) {
                this.pointerTarget = new Vec3(x, y, z);
                this.pointerTargetUntil = this.ship.level().getGameTime() + remaining;
                this.pointerAlongX = targetTag.getBoolean("AlongX");
                this.pointerFaceP = targetTag.getBoolean("FaceP");
            } else {
                this.pointerTarget = null;
                this.pointerTargetUntil = 0L;
            }
        } else {
            this.pointerTarget = null;
            this.pointerTargetUntil = 0L;
        }
        if (compound.contains("PointerTargetEntity")) {
            CompoundTag targetTag = compound.getCompound("PointerTargetEntity");
            UUID id = targetTag.hasUUID("Id") ? targetTag.getUUID("Id") : null;
            long remaining = targetTag.getLong("Remaining");
            if (id != null && remaining > 0L) {
                this.pointerTargetEntityId = id;
                this.pointerTargetEntityUntil = this.ship.level().getGameTime() + remaining;
            } else {
                this.pointerTargetEntityId = null;
                this.pointerTargetEntityUntil = 0L;
            }
        } else {
            this.pointerTargetEntityId = null;
            this.pointerTargetEntityUntil = 0L;
        }
    }

    void tickPointerTargetEntity() {
        if (this.pointerTargetEntityId == null) {
            return;
        }
        if (this.ship.isInDeadPose()) {
            clearPointerTargetEntity();
            this.ship.getNavigation().stop();
            return;
        }
        if (this.ship.isCombatSuppressed()) {
            this.ship.getNavigation().stop();
            return;
        }
        Entity target = getPointerTargetEntity();
        if (target == null || !target.isAlive()) {
            clearPointerTargetEntity();
            return;
        }
        handlePointerTargetEntityCombat(target);
    }

    void setPointerTarget(Vec3 target, long durationTicks) {
        this.pointerTarget = target;
        this.pointerTargetUntil = this.ship.level().getGameTime() + Math.max(0L, durationTicks);

        LivingEntity ownerRaw = this.ship.getOwner();
        if (ownerRaw instanceof Player owner) {
            Vec3 refPos = this.ship.position();
            int teamId = this.ship.getFormationTeam();
            if (teamId >= 0 && this.ship.getFormationSlot() > 0) {
                for (Entity e : this.ship.level().getEntities(this.ship, this.ship.getBoundingBox().inflate(64.0))) {
                    if (e instanceof EntityShipBase other && other.isOwnedBy(owner)
                            && other.getFormationTeam() == teamId && other.getFormationSlot() == 0) {
                        refPos = other.position();
                        break;
                    }
                }
            }
            boolean[] dir = org.trp.shincolle.utility.FormationHelper.getFormationDirection(
                    target.x, target.z, refPos.x, refPos.z);
            this.pointerAlongX = dir[0];
            this.pointerFaceP = dir[1];
        }

        updateSynchedData();
    }

    boolean hasPointerTarget() {
        if (this.ship.level().isClientSide) {
            readSynchedData();
        }
        return this.pointerTarget != null && this.ship.level().getGameTime() <= this.pointerTargetUntil;
    }

    Vec3 getPointerTarget() {
        if (this.ship.level().isClientSide) {
            readSynchedData();
        }
        if (this.pointerTarget == null) return null;
        
        int teamId = this.ship.getFormationTeam();
        int slotId = this.ship.getFormationSlot();
        
        if (teamId >= 0 && slotId > 0) {
            
            LivingEntity ownerRaw = this.ship.getOwner();
            if (ownerRaw instanceof Player owner) {
                org.trp.shincolle.attachment.AdmiralData data = owner.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
                int formationId = data.getFormationID(teamId);

                Vec3 refPos = this.ship.position();
                for (Entity e : this.ship.level().getEntities(this.ship, this.ship.getBoundingBox().inflate(64.0))) {
                    if (e instanceof EntityShipBase other && other.isOwnedBy(owner) 
                            && other.getFormationTeam() == teamId && other.getFormationSlot() == 0) {
                        refPos = other.position();
                        break;
                    }
                }
                return org.trp.shincolle.utility.FormationHelper.getFormationPos(formationId, slotId, this.pointerTarget, this.pointerAlongX, this.pointerFaceP);
            }
        }
        
        return this.pointerTarget;
    }

    Vec3 getRawPointerTarget() {
        if (this.ship.level().isClientSide) {
            readSynchedData();
        }
        return this.pointerTarget;
    }

    long getPointerTargetRemainingTicks() {
        if (this.ship.level().isClientSide) {
            readSynchedData();
        }
        return this.pointerTarget == null ? 0L : Math.max(0L, this.pointerTargetUntil - this.ship.level().getGameTime());
    }

    void clearPointerTarget() {
        this.pointerTarget = null;
        this.pointerTargetUntil = 0L;
        updateSynchedData();
    }

    void setPointerTargetEntity(Entity target, long durationTicks) {
        if (target == null) {
            clearPointerTargetEntity();
            return;
        }
        this.pointerTarget = null;
        this.pointerTargetUntil = 0L;
        this.pointerTargetEntityId = target.getUUID();
        this.pointerTargetEntityUntil = this.ship.level().getGameTime() + Math.max(0L, durationTicks);

        int aimDelay = Math.max(5, (int) (20.0F * (150 - this.ship.getLevel()) / 150.0F) + 10);
        this.pointerTargetEntityAttackTick = this.ship.tickCount + aimDelay;
        this.pointerTargetEntityLightShotTick = this.ship.tickCount + aimDelay;
        this.pointerTargetEntityHeavyShotTick = this.ship.tickCount + aimDelay;
        this.pointerTargetEntityPathTick = 0;
        this.ship.getCombat().resetAircraftLaunchDelay();
        updateSynchedData();
    }

    boolean hasPointerTargetEntity() {
        if (this.ship.level().isClientSide) {
            readSynchedData();
        }
        return this.pointerTargetEntityId != null && this.ship.level().getGameTime() <= this.pointerTargetEntityUntil;
    }

    Entity getPointerTargetEntity() {
        if (this.ship.level().isClientSide) {
            readSynchedData();
        }
        if (!hasPointerTargetEntity() || this.pointerTargetEntityId == null) {
            return null;
        }
        if (this.ship.level() instanceof ServerLevel serverLevel) {
            return serverLevel.getEntity(this.pointerTargetEntityId);
        }
        if (this.ship.level().isClientSide && this.ship.level() instanceof net.minecraft.client.multiplayer.ClientLevel clientLevel) {
            for (Entity e : clientLevel.entitiesForRendering()) {
                if (e.getUUID().equals(this.pointerTargetEntityId)) {
                    return e;
                }
            }
        }
        return null;
    }

    long getPointerTargetEntityRemainingTicks() {
        if (this.ship.level().isClientSide) {
            readSynchedData();
        }
        return this.pointerTargetEntityId == null
                ? 0L
                : Math.max(0L, this.pointerTargetEntityUntil - this.ship.level().getGameTime());
    }

    void clearPointerTargetEntity() {
        this.pointerTargetEntityId = null;
        this.pointerTargetEntityUntil = 0L;
        updateSynchedData();
    }

    private void updateSynchedData() {
        if (this.ship.level().isClientSide) return;

        CompoundTag tag = new CompoundTag();
        if (this.pointerTarget != null) {
            tag.putDouble("PX", this.pointerTarget.x);
            tag.putDouble("PY", this.pointerTarget.y);
            tag.putDouble("PZ", this.pointerTarget.z);
            tag.putLong("PUntil", this.pointerTargetUntil);
            tag.putBoolean("PAX", this.pointerAlongX);
            tag.putBoolean("PFP", this.pointerFaceP);
        }
        if (this.pointerTargetEntityId != null) {
            tag.putUUID("PEId", this.pointerTargetEntityId);
            tag.putLong("PEUntil", this.pointerTargetEntityUntil);
        }
        this.ship.getEntityData().set(EntityShipBase.POINTER_TARGET_DATA, tag);
    }

    private void readSynchedData() {
        if (!this.ship.level().isClientSide) return;

        CompoundTag tag = this.ship.getEntityData().get(EntityShipBase.POINTER_TARGET_DATA);
        if (tag.isEmpty()) {
            this.pointerTarget = null;
            this.pointerTargetUntil = 0L;
            this.pointerTargetEntityId = null;
            this.pointerTargetEntityUntil = 0L;
            return;
        }

        if (tag.contains("PX")) {
            this.pointerTarget = new Vec3(tag.getDouble("PX"), tag.getDouble("PY"), tag.getDouble("PZ"));
            this.pointerTargetUntil = tag.getLong("PUntil");
            this.pointerAlongX = tag.getBoolean("PAX");
            this.pointerFaceP = tag.getBoolean("PFP");
        } else {
            this.pointerTarget = null;
            this.pointerTargetUntil = 0L;
        }

        if (tag.contains("PEId")) {
            this.pointerTargetEntityId = tag.getUUID("PEId");
            this.pointerTargetEntityUntil = tag.getLong("PEUntil");
        } else {
            this.pointerTargetEntityId = null;
            this.pointerTargetEntityUntil = 0L;
        }
    }

    private void handlePointerTargetEntityCombat(Entity target) {
        if (this.ship.isCombatSuppressed() || this.ship.isInDeadPose() || target == null || !target.isAlive()) {
            return;
        }

        double desiredRangeSqr = getPointerTargetEntityPreferredRangeSqr(target);
        double distanceSqr = this.ship.distanceToSqr(target);
        boolean hasRangedAmmo = this.ship.getCombat().canUseLightAmmo()
                || this.ship.getCombat().canUseHeavyAmmo()
                || this.ship.getCombat().hasAircraftAttackEnabled();
        double stopRangeSqr = hasRangedAmmo
                ? desiredRangeSqr + POINTER_ENTITY_PATH_REFRESH_DISTANCE_SQR
                : desiredRangeSqr;

        boolean onSight = this.ship.hasLineOfSight(target);

        boolean needsCloser = distanceSqr > stopRangeSqr;
        boolean cannotSee = !onSight && distanceSqr > desiredRangeSqr * 0.5D;

        if (needsCloser || cannotSee) {
            if (this.pointerTargetEntityPathTick-- <= 0) {
                this.pointerTargetEntityPathTick = POINTER_ENTITY_PATH_RECALC_INTERVAL;
                if (!this.ship.getNavigation().moveTo(target, POINTER_ENTITY_MOVE_SPEED)) {
                    this.pointerTargetEntityPathTick = 2;
                }
            }
            return;
        }

        this.ship.getNavigation().stop();
        this.ship.getMoveControl().setWantedPosition(
                this.ship.getX(), this.ship.getY(), this.ship.getZ(), 0.0D);

        if (this.ship.getCombat().hasAircraftAttackEnabled()) {
            this.ship.getCombat().tryPerformAircraftCycle(target);
        }

        if (this.ship.getCombat().canUseLightAmmo()) {
            int lightInterval = Math.max(1, this.ship.getLegacyShipStats().getLightDelay());
            if ((this.ship.tickCount - this.pointerTargetEntityLightShotTick) >= lightInterval) {
                this.pointerTargetEntityLightShotTick = this.ship.tickCount;
                this.ship.performLightAttack(target);
            }
        }

        if (this.ship.getCombat().canUseHeavyAmmo()) {
            int heavyInterval = Math.max(1, this.ship.getLegacyShipStats().getHeavyDelay());
            if ((this.ship.tickCount - this.pointerTargetEntityHeavyShotTick) >= heavyInterval) {
                this.ship.performHeavyAttack(target);
                this.pointerTargetEntityHeavyShotTick = this.ship.tickCount;
            }
        }

        if (this.ship.getCombat().canUseMeleeAttack()
                && distanceSqr <= getPointerTargetEntityAttackRangeSqr(target)) {
            int meleeInterval = Math.max(1, this.ship.getLegacyShipStats().getMeleeDelay());
            if ((this.ship.tickCount - this.pointerTargetEntityAttackTick) >= meleeInterval) {
                this.pointerTargetEntityAttackTick = this.ship.tickCount;
                this.ship.doHurtTarget(target);
            }
        }
    }

    private double getPointerTargetEntityPreferredRangeSqr(Entity target) {
        boolean canMelee = this.ship.getCombat().canUseMeleeAttack();
        boolean canAmmo = this.ship.getCombat().canUseLightAmmo() || this.ship.getCombat().canUseHeavyAmmo();
        boolean canAir = this.ship.getCombat().hasAircraftAttackEnabled();

        if (canAmmo) {
            double range = Math.max(2.0D, this.ship.getLegacyShipStats().getAttackRange());
            return range * range;
        } else if (canAir) {
            double range = Math.max(24.0D, this.ship.getLegacyShipStats().getAttackRange() * 1.5D);
            return range * range;
        } else if (canMelee) {
            return getPointerTargetEntityAttackRangeSqr(target);
        }

        return getPointerTargetEntityAttackRangeSqr(target);
    }

    private double getPointerTargetEntityAttackRangeSqr(Entity target) {
        double width = this.ship.getBbWidth() * 2.0F;
        double reach = width * width + target.getBbWidth();
        return Math.max(reach, POINTER_ENTITY_ATTACK_RANGE_SQR);
    }
}
