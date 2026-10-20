package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.EntityAircraftBase;

import java.util.EnumSet;

public class GoalShipAircraftAttack extends Goal {

    private final EntityAircraftBase host;
    private Entity target;
    private Vec3 randPos;
    private double distSq;
    private float rangeSq;

    public GoalShipAircraftAttack(EntityAircraftBase host) {
        this.host = host;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        Entity targetEntity = this.host.getMissionTarget();
        if (targetEntity == null || !targetEntity.isAlive()) {
            return false;
        }
        
        if (this.host.getMissionTick() > 20 && (this.host.hasAmmoLight() || this.host.hasAmmoHeavy())) {
            this.target = targetEntity;
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        float attackRange = this.host.isMissionLightAircraft() ? 6.0F : 16.0F;
        this.rangeSq = attackRange * attackRange;
        this.updateRandomPos();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() || (this.target != null && this.target.isAlive() && !this.host.getNavigation().isDone());
    }

    @Override
    public void stop() {
        this.target = null;
        this.host.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.target == null) return;

        double dx = this.target.getX() - this.host.getX();
        double dz = this.target.getZ() - this.host.getZ();
        double dy = (this.target.getY() + 2.0D) - this.host.getY();
        if (this.target.isInWaterOrBubble() && dy < 0.0D) {
            dy = Math.max(-4.0D, dy);
        }
        this.distSq = dx * dx + dy * dy + dz * dz;

        if ((this.host.tickCount & 0xF) == 0 || this.randPos == null || this.host.getNavigation().isDone()) {
            updateRandomPos();
        }

        double speed;
        if (this.host.getAttackDelay() > 0) {
            speed = 0.3D;
        } else {
            speed = this.distSq > this.rangeSq ? 0.6D : 0.3D;
        }
        this.host.getNavigation().moveTo(this.randPos.x, this.randPos.y, this.randPos.z, speed);

        if (this.host.getAttackDelay() <= 0 && this.host.hasLineOfSight(this.target) && this.distSq < this.rangeSq) {
            if (this.host.isMissionLightAircraft() && this.host.hasAmmoLight()) {
                this.host.attackWithLightAmmo(this.target);
            } else if (!this.host.isMissionLightAircraft() && this.host.hasAmmoHeavy()) {
                this.host.attackWithHeavyAmmo(this.target);
            }
        }
    }

    private void updateRandomPos() {
        double minDist, randDist;
        if (this.host.isMissionLightAircraft()) {
            minDist = 5.0D;
            randDist = 2.0D;
        } else {
            minDist = 12.0D;
            randDist = 5.0D;
        }

        Entity ref = this.target != null ? this.target : this.host;
        Level level = this.host.level();

        float currentYaw = this.host.getYRot();
    
        for (int i = 0; i < 25; i++) {
            float angle = currentYaw + (i * 15.0F); 
            double rad = Math.toRadians(angle);
        
            double dist = minDist + this.host.getRandom().nextDouble() * randDist;
            double newX = ref.getX() + Math.cos(rad) * dist;
            double newZ = ref.getZ() + Math.sin(rad) * dist;
            double targetY = ref.getY() + ref.getBbHeight();

            BlockPos surfaceCheck = BlockPos.containing(newX, targetY, newZ);
            while (surfaceCheck.getY() < level.getMaxBuildHeight() && !level.getFluidState(surfaceCheck).isEmpty()) {
                surfaceCheck = surfaceCheck.above();
            }

            double baseY = Math.max(targetY, (double) surfaceCheck.getY());
            double newY = baseY + 2.0D + this.host.getRandom().nextDouble() * 2.0D;

            BlockPos targetPos = BlockPos.containing(newX, newY, newZ);
            if (level.getBlockState(targetPos).getCollisionShape(level, targetPos).isEmpty() && level.getFluidState(targetPos).isEmpty()) {
                this.randPos = new Vec3(newX, newY, newZ);
                return;
            }
        }

        BlockPos fallbackCheck = BlockPos.containing(ref.getX(), ref.getY() + ref.getBbHeight(), ref.getZ());
        while (fallbackCheck.getY() < level.getMaxBuildHeight() && !level.getFluidState(fallbackCheck).isEmpty()) {
            fallbackCheck = fallbackCheck.above();
        }
        this.randPos = new Vec3(ref.getX(), Math.max(ref.getY() + 5.0D, fallbackCheck.getY() + 3.0D), ref.getZ());
    }
}