package org.trp.shincolle.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
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
    }

    boolean hasPointerTarget() {
        return this.pointerTarget != null && this.ship.level().getGameTime() <= this.pointerTargetUntil;
    }

    Vec3 getPointerTarget() {
        return this.pointerTarget;
    }

    long getPointerTargetRemainingTicks() {
        return this.pointerTarget == null ? 0L : Math.max(0L, this.pointerTargetUntil - this.ship.level().getGameTime());
    }

    void clearPointerTarget() {
        this.pointerTarget = null;
        this.pointerTargetUntil = 0L;
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
    }

    boolean hasPointerTargetEntity() {
        return this.pointerTargetEntityId != null && this.ship.level().getGameTime() <= this.pointerTargetEntityUntil;
    }

    Entity getPointerTargetEntity() {
        if (!hasPointerTargetEntity() || this.pointerTargetEntityId == null) {
            return null;
        }
        if (this.ship.level() instanceof ServerLevel serverLevel) {
            return serverLevel.getEntity(this.pointerTargetEntityId);
        }
        return null;
    }

    long getPointerTargetEntityRemainingTicks() {
        return this.pointerTargetEntityId == null
                ? 0L
                : Math.max(0L, this.pointerTargetEntityUntil - this.ship.level().getGameTime());
    }

    void clearPointerTargetEntity() {
        this.pointerTargetEntityId = null;
        this.pointerTargetEntityUntil = 0L;
    }

    private void handlePointerTargetEntityCombat(Entity target) {
        if (this.ship.isInDeadPose() || target == null || !target.isAlive()) {
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

        if (distanceSqr > stopRangeSqr) {
            if (this.pointerTargetEntityPathTick-- <= 0) {
                this.pointerTargetEntityPathTick = POINTER_ENTITY_PATH_RECALC_INTERVAL;
                this.ship.getNavigation().moveTo(target, POINTER_ENTITY_MOVE_SPEED);
            }
            return;
        }

        this.ship.getNavigation().stop();

        if (this.ship.getCombat().hasAircraftAttackEnabled()) {
            if (this.ship.getCombat().tryPerformAircraftCycle(target)) {
                return;
            }
        }

        if (this.ship.getCombat().canUseLightAmmo()) {
            int lightInterval = this.ship.getLegacyShipStats().getLightDelay();
            if ((this.ship.tickCount - this.pointerTargetEntityLightShotTick) >= lightInterval) {
                this.pointerTargetEntityLightShotTick = this.ship.tickCount;
                this.ship.performLightAttack(target);
            }
            return;
        }

        if (this.ship.getCombat().canUseHeavyAmmo()) {
            int heavyInterval = this.ship.getLegacyShipStats().getHeavyDelay();
            if ((this.ship.tickCount - this.pointerTargetEntityHeavyShotTick) >= heavyInterval) {
                this.pointerTargetEntityHeavyShotTick = this.ship.tickCount;
                if (this.ship.performHeavyAttack(target)) {
                    return;
                }
            }
        }

        if (this.ship.getCombat().canUseMeleeAttack()
                && distanceSqr <= getPointerTargetEntityAttackRangeSqr(target)
                && (this.ship.tickCount - this.pointerTargetEntityAttackTick) >= this.ship.getLegacyShipStats().getMeleeDelay()) {
            this.pointerTargetEntityAttackTick = this.ship.tickCount;
            this.ship.doHurtTarget(target);
        }
    }

    private double getPointerTargetEntityPreferredRangeSqr(Entity target) {
        boolean canMelee = this.ship.getCombat().canUseMeleeAttack();
        boolean canAmmo = this.ship.getCombat().canUseLightAmmo() || this.ship.getCombat().canUseHeavyAmmo();
        boolean canAir = this.ship.getCombat().hasAircraftAttackEnabled();

        if (canMelee) {
            return getPointerTargetEntityAttackRangeSqr(target);
        } else if (canAmmo) {
            double range = Math.max(2.0D, this.ship.getLegacyShipStats().getAttackRange());
            return range * range;
        } else if (canAir) {
            double range = Math.max(24.0D, this.ship.getLegacyShipStats().getAttackRange() * 1.5D);
            return range * range;
        }

        return getPointerTargetEntityAttackRangeSqr(target);
    }

    private double getPointerTargetEntityAttackRangeSqr(Entity target) {
        double width = this.ship.getBbWidth() * 2.0F;
        double reach = width * width + target.getBbWidth();
        return Math.max(reach, POINTER_ENTITY_ATTACK_RANGE_SQR);
    }
}
