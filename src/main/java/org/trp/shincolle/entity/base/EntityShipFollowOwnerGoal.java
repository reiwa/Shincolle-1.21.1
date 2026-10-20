package org.trp.shincolle.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

final class EntityShipFollowOwnerGoal extends Goal {

    private static final int DEFAULT_TP_COOLDOWN = 200;
    private static final double DEFAULT_TP_DIST_SQ = 256.0;
    private static final int BASE_COOLDOWN = 64;
    private static final int MIN_COOLDOWN = BASE_COOLDOWN / 4;
    private static final double MIN_RANGE = 2.0 * 2.0;
    private static final double MAX_RANGE = 32.0 * 32.0;

    private final EntityShipBase ship;
    private final double speed;
    private final float defaultMaxDist;
    private final float defaultMinDist;
    private int findCooldown;
    private int checkTP_T;
    private int checkTP_D;
    private double lastOwnerX;
    private double lastOwnerY;
    private double lastOwnerZ;
    private boolean hasOwnerPos;
    private boolean[] formationDir = new boolean[]{false, true};

    EntityShipFollowOwnerGoal(EntityShipBase ship, double speed, float maxDist, float minDist) {
        this.ship = ship;
        this.speed = speed;
        this.defaultMaxDist = maxDist;
        this.defaultMinDist = minDist;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!canFollowOwner()) {
            return false;
        }
        LivingEntity owner = this.ship.getOwner();
        if (owner == null || owner.level() != this.ship.level()) {
            return false;
        }
        if (owner instanceof Player player && this.ship.playerHasCombatRation(player)) {
            return this.ship.distanceToSqr(owner) > 1.5 * 1.5;
        }
        net.minecraft.world.phys.Vec3 moveTarget = resolveMoveTarget(owner);
        double distSq = this.ship.distanceToSqr(moveTarget.x, moveTarget.y, moveTarget.z);
        if (hasActiveFormation(owner)) {
            return distSq > 7.0D;
        }
        float minDist = resolveFollowMinDistance();
        float maxDist = resolveFollowMaxDistance(minDist);
        double maxDistSq = maxDist * maxDist + this.ship.getBbWidth() * 0.75F;
        return distSq > maxDistSq;
    }

    @Override
    public boolean canContinueToUse() {
        if (!canFollowOwner()) {
            return false;
        }
        LivingEntity owner = this.ship.getOwner();
        if (owner == null || owner.level() != this.ship.level()) {
            return false;
        }
        if (owner instanceof Player player && this.ship.playerHasCombatRation(player)) {
            return this.ship.distanceToSqr(owner) > 1.5 * 1.5;
        }
        net.minecraft.world.phys.Vec3 moveTarget = resolveMoveTarget(owner);
        double distSq = this.ship.distanceToSqr(moveTarget.x, moveTarget.y, moveTarget.z);
        if (hasActiveFormation(owner)) {
            return distSq > 4.0D;
        }
        float minDist = resolveFollowMinDistance();
        double minDistSq = minDist * minDist + this.ship.getBbWidth() * 0.75F;
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
    public void tick() {
        LivingEntity owner = ship.getOwner();
        if (owner == null || owner.level() != this.ship.level()) {
            this.stop();
            return;
        }

        --this.findCooldown;
        ++this.checkTP_T;
        ship.resetInteractionEmotionState();
        boolean hasRation = owner instanceof Player player && this.ship.playerHasCombatRation(player);
        if (hasRation) {
            ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            if (this.ship.tickCount % 32 == 0) {
                EmotionParticleType[] positiveEmotes = {
                        EmotionParticleType.HEART,
                        EmotionParticleType.MUSIC_NOTE,
                        EmotionParticleType.HAPPY_BOB,
                        EmotionParticleType.SPARKLE_EYES,
                        EmotionParticleType.POUT_BOUNCE,
                        EmotionParticleType.LAUGH,
                        EmotionParticleType.HAPPY_GLANCE,
                        EmotionParticleType.BLINK,
                        EmotionParticleType.BLUSH
                };
                EmotionParticleType selected = positiveEmotes[this.ship.getRandom().nextInt(positiveEmotes.length)];
                this.ship.applyParticleEmotion(selected);
            }
        }
        LivingEntity combatTarget = ship.getTarget();
        if (combatTarget != null && combatTarget.isAlive() && ship.hasLineOfSight(combatTarget)) {
            ship.getLookControl().setLookAt(combatTarget, 30.0F, 30.0F);
        } else {
            ship.getLookControl().setLookAt(owner, 30.0F, 30.0F);
        }

        net.minecraft.world.phys.Vec3 moveTarget = hasRation ? owner.position() : resolveMoveTarget(owner);
        double distSq = ship.distanceToSqr(moveTarget.x, moveTarget.y, moveTarget.z);
        float minDist = resolveFollowMinDistance();
        float maxDist = resolveFollowMaxDistance(minDist);
        double minDistSq = hasRation
                ? (1.5D * 1.5D)
                : (hasActiveFormation(owner) ? 4.0D : (minDist * minDist + this.ship.getBbWidth() * 0.75F));
        double maxDistSq = hasActiveFormation(owner) ? 7.0D : (maxDist * maxDist + this.ship.getBbWidth() * 0.75F);

        if (distSq <= minDistSq) {
            ship.getNavigation().stop();
        } else if (this.findCooldown <= 0 || ship.getNavigation().isDone()) {
            this.findCooldown = calcCooldown(maxDistSq);
            ship.getNavigation().moveTo(moveTarget.x, moveTarget.y, moveTarget.z, this.speed);
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

    private boolean hasActiveFormation(LivingEntity owner) {
        int teamId = ship.getFormationTeam();
        int slotId = ship.getFormationSlot();
        if (teamId >= 0 && slotId >= 0) {
            org.trp.shincolle.attachment.AdmiralData data = owner.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            return data.getFormationID(teamId) > 0;
        }
        return false;
    }

    private net.minecraft.world.phys.Vec3 resolveMoveTarget(LivingEntity owner) {
        int teamId = ship.getFormationTeam();
        int slotId = ship.getFormationSlot();
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

    @Override
    public void stop() {
        ship.getNavigation().stop();
    }

    private boolean canFollowOwner() {
        return this.ship.shouldFollowOwner();
    }

    private float resolveFollowMinDistance() {
        int configured = this.ship.getStateComponent().getFollowMin();
        if (configured <= 0) {
            return this.defaultMinDist;
        }
        int clamped = Mth.clamp(configured, 1, 31);
        return (float) clamped;
    }

    private float resolveFollowMaxDistance(float minDist) {
        int configured = this.ship.getStateComponent().getFollowMax();
        if (configured <= 0) {
            return Math.max(this.defaultMaxDist, minDist + 1.0F);
        }
        int minValue = Math.max(2, Mth.floor(minDist) + 1);
        int clamped = Mth.clamp(configured, minValue, 32);
        return (float) clamped;
    }

    private void applyTeleport(LivingEntity owner) {
        double tx = owner.getX();
        double ty = owner.getY() + 0.75;
        double tz = owner.getZ();
        if (ship.level() instanceof ServerLevel serverLevel) {
            int cx = Mth.floor(tx) >> 4;
            int cz = Mth.floor(tz) >> 4;
            if (!serverLevel.hasChunk(cx, cz)) {
                return;
            }
        }
        ship.getNavigation().stop();
        ship.teleportTo(tx, ty, tz);
        this.checkTP_T = 0;
        this.checkTP_D = 0;
    }

    private void updateFormationDirection(LivingEntity owner) {
        double ox = owner.getX();
        double oy = owner.getY();
        double oz = owner.getZ();
        if (!hasOwnerPos) {
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
}
