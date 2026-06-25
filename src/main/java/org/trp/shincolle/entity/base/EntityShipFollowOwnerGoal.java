package org.trp.shincolle.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

final class EntityShipFollowOwnerGoal extends Goal {

    private static final int TP_COOLDOWN = 200;
    private static final double TP_DIST_SQ = 256.0;

    private final EntityShipBase ship;
    private final double speed;
    private final float defaultMaxDist;
    private final float defaultMinDist;
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
        if (owner == null) {
            return false;
        }
        double distSq = this.ship.distanceToSqr(owner);
        if (owner instanceof Player player && this.ship.playerHasCombatRation(player)) {
            return distSq > 1.5 * 1.5;
        }
        float minDist = resolveFollowMinDistance();
        float maxDist = resolveFollowMaxDistance(minDist);
        return distSq > maxDist * maxDist;
    }

    @Override
    public boolean canContinueToUse() {
        if (!canFollowOwner()) {
            return false;
        }
        LivingEntity owner = this.ship.getOwner();
        if (owner == null) {
            return false;
        }
        double distSq = this.ship.distanceToSqr(owner);
        if (owner instanceof Player player && this.ship.playerHasCombatRation(player)) {
            return distSq > 1.5 * 1.5;
        }
        float minDist = resolveFollowMinDistance();
        return distSq > minDist * minDist;
    }

    @Override
    public void start() {
        this.checkTP_T = 0;
        this.checkTP_D = 0;
        this.hasOwnerPos = false;
    }

    @Override
    public void tick() {
        LivingEntity owner = ship.getOwner();
        if (owner == null) {
            return;
        }

        ++this.checkTP_T;
        ship.resetInteractionEmotionState();
        if (owner instanceof Player player && this.ship.playerHasCombatRation(player)) {
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
        ship.getLookControl().setLookAt(owner, 30.0F, 30.0F);

        int teamId = ship.getFormationTeam();
        int slotId = ship.getFormationSlot();
        net.minecraft.world.phys.Vec3 moveTarget = owner.position();

        if (teamId >= 0 && slotId >= 0) {
            org.trp.shincolle.attachment.AdmiralData data = owner.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            int formationId = data.getFormationID(teamId);
            updateFormationDirection(owner);
            moveTarget = org.trp.shincolle.utility.FormationHelper.getFormationPos(formationId, slotId, owner.position(), formationDir[0], formationDir[1]);
        }

        ship.getNavigation().moveTo(moveTarget.x, moveTarget.y, moveTarget.z, this.speed);

        double distSq = ship.distanceToSqr(owner);

        if (distSq > TP_DIST_SQ) {
            ++this.checkTP_D;
            if (this.checkTP_D > TP_COOLDOWN) {
                this.checkTP_D = 0;
                applyTeleport(owner);
                return;
            }
        } else {
            this.checkTP_D = 0;
        }

        if (this.checkTP_T > TP_COOLDOWN) {
            this.checkTP_T = 0;
            applyTeleport(owner);
        }
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
