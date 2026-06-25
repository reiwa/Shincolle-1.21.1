package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.Config;
import org.trp.shincolle.block.entity.CraneBlockEntity;
import org.trp.shincolle.block.entity.IWaypoint;
import org.trp.shincolle.block.entity.WayPointBlockEntity;
import org.trp.shincolle.item.CombatRationItem;
import org.trp.shincolle.utility.BlockHelper;

class EntityShipBaseMovementHelper {

    private final EntityShipBase ship;

    EntityShipBaseMovementHelper(EntityShipBase ship) {
        this.ship = ship;
    }

    public int getWpStayTimeMax() {
        int wpstay = this.ship.getStateComponent().getWpStay();
        if (wpstay >= 1 && wpstay <= 5) return wpstay * 100;
        if (wpstay >= 6 && wpstay <= 10) return (wpstay - 5) * 1200;
        if (wpstay >= 11 && wpstay <= 16) return (wpstay - 10) * 12000;
        return 0;
    }

    protected void tickWaypointMove() {
        if (
            this.ship.getStateComponent().isStateDisableGuardPos() ||
            this.ship.getStateComponent().getGuardY() <= 0 ||
            this.ship.isOrderedToSit() ||
            this.ship.isLeashed() ||
            this.ship.isVehicle()
        ) {
            return;
        }

        BlockPos pos = new BlockPos(
            this.ship.getStateComponent().getGuardX(),
            this.ship.getStateComponent().getGuardY(),
            this.ship.getStateComponent().getGuardZ()
        );
        double distSq = this.ship.distanceToSqr(
            pos.getX() + 0.5D,
            pos.getY(),
            pos.getZ() + 0.5D
        );

        net.minecraft.world.level.block.entity.BlockEntity be =
            this.ship.level().getBlockEntity(pos);
        if (be instanceof CraneBlockEntity) {
            if (distSq < 64.0D) {
                if (this.ship.getStateComponent().getCraning() == 0) {
                    this.ship.getStateComponent().setCraning(1);
                    this.ship.ejectPassengers();
                }
            } else if (this.ship.getFuel() > 0) {
                this.ship.getNavigation().moveTo(
                    pos.getX() + 0.5D,
                    pos.getY() - 2.0D,
                    pos.getZ() + 0.5D,
                    1.0D
                );
            }
        } else {
            this.ship.getStateComponent().setCraning(0);
        }

        if (be instanceof IWaypoint wp) {
            if (
                this.ship.getStateComponent().getEquipFlare() > 0 && this.ship.getStateComponent().getEquipSearchlight() > 0
            ) return;
            if (distSq < 9.0D) {
                try {
                    boolean timeout = false;
                    int wpstay = this.ship.getStateComponent().getWpStayTimer();
                    int staytimemax = Math.max(
                        this.getWpStayTimeMax(),
                        wp instanceof WayPointBlockEntity wp2
                            ? wp2.getStayTimeTicks()
                            : 0
                    );
                    if (wpstay < staytimemax) {
                        this.ship.getStateComponent().setWpStayTimer(wpstay + 16);
                    } else {
                        timeout = true;
                    }
                    if (timeout) {
                        this.ship.getStateComponent().setWpStayTimer(0);
                        BlockPos next = wp.getNextPos();
                        BlockPos last = wp.getLastPos();
                        BlockPos[] wps = this.ship.getWaypoints();
                        BlockPos shiplast =
                            wps != null && wps.length > 0
                                ? wps[0]
                                : BlockPos.ZERO;
                        BlockPos targetPos = null;
                        if (next.getY() > 0 && next.equals(shiplast)) {
                            if (last.getY() > 0) targetPos = last;
                            else if (next.getY() > 0) targetPos = next;
                        } else if (next.getY() > 0) {
                            targetPos = next;
                        }
                        if (targetPos != null) {
                            this.ship.getStateComponent().setGuardX(targetPos.getX());
                            this.ship.getStateComponent().setGuardY(targetPos.getY());
                            this.ship.getStateComponent().setGuardZ(targetPos.getZ());
                            if (this.ship.getFuel() > 0) {
                                this.ship.getStateComponent().setFollowMin(2);
                                this.ship.getNavigation().moveTo(
                                    targetPos.getX() + 0.5D,
                                    targetPos.getY(),
                                    targetPos.getZ() + 0.5D,
                                    1.0D
                                );
                            }
                        }
                        BlockPos[] newWps =
                            wps != null && wps.length > 0
                                ? wps
                                : new BlockPos[] { BlockPos.ZERO };
                        newWps[0] = pos;
                        this.ship.setWaypoints(newWps);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (
                (this.ship.tickCount & 0x7F) == 0 && this.ship.getFuel() > 0
            ) {
                this.ship.getNavigation().moveTo(
                    pos.getX() + 0.5D,
                    pos.getY(),
                    pos.getZ() + 0.5D,
                    1.0D
                );
            }
        }
    }

    public boolean shouldFollowOwner() {
        if (
            this.ship.isOrderedToSit() ||
            this.ship.isInSittingPose() ||
            this.ship.isInDeadPose() ||
            this.ship.isPassenger()
        ) {
            return false;
        }
        LivingEntity owner = this.ship.getOwner();
        if (owner == null) {
            return false;
        }
        if (this.ship.getGuardedPos(4) == 1 || this.ship.hasPointerTarget()) {
            return false;
        }
        if (this.ship.hasPointerTargetEntity()) {
            return false;
        }

        int configuredMin = this.ship.getStateComponent().getFollowMin();
        float minDist =
            configuredMin <= 0 ? 6.0F : (float) Mth.clamp(configuredMin, 1, 31);

        int configuredMax = this.ship.getStateComponent().getFollowMax();
        float maxDist =
            configuredMax <= 0
                ? Math.max(12.0F, minDist + 1.0F)
                : (float) Mth.clamp(
                      configuredMax,
                      Math.max(2, Mth.floor(minDist) + 1),
                      32
                  );

        double checkMinDist = minDist;
        if (owner instanceof Player player && playerHasCombatRation(player)) {
            checkMinDist = 1.5D;
        }

        double distanceSqr = this.ship.distanceToSqr(owner);
        return (
            distanceSqr > (checkMinDist * checkMinDist) &&
            distanceSqr < (maxDist * maxDist) * 256.0D
        );
    }

    public boolean isOwnerTooFar() {
        if (
            this.ship.isOrderedToSit() ||
            this.ship.isInSittingPose() ||
            this.ship.isInDeadPose() ||
            this.ship.isPassenger()
        ) {
            return false;
        }
        LivingEntity owner = this.ship.getOwner();
        if (owner == null) {
            return false;
        }
        if (this.ship.getGuardedPos(4) == 1 || this.ship.hasPointerTarget()) {
            return false;
        }
        if (this.ship.hasPointerTargetEntity()) {
            return false;
        }

        int configuredMin = this.ship.getStateComponent().getFollowMin();
        float minDist =
            configuredMin <= 0 ? 6.0F : (float) Mth.clamp(configuredMin, 1, 31);

        int configuredMax = this.ship.getStateComponent().getFollowMax();
        float maxDist =
            configuredMax <= 0
                ? Math.max(12.0F, minDist + 1.0F)
                : (float) Mth.clamp(
                      configuredMax,
                      Math.max(2, Mth.floor(minDist) + 1),
                      32
                  );

        double distanceSqr = this.ship.distanceToSqr(owner);
        return (
            distanceSqr > (maxDist * maxDist) &&
            distanceSqr < (maxDist * maxDist) * 256.0D
        );
    }

    private boolean playerHasCombatRation(Player player) {
        ItemStack mainHand = player.getMainHandItem();
        if (
            !mainHand.isEmpty() && mainHand.getItem() instanceof CombatRationItem
        ) {
            return true;
        }
        ItemStack offHand = player.getOffhandItem();
        if (
            !offHand.isEmpty() && offHand.getItem() instanceof CombatRationItem
        ) {
            return true;
        }
        return false;
    }

    public boolean shouldRetreatForLowHealth() {
        if (this.ship.getIsSitting()) {
            return false;
        }
        int fleeHp = Mth.clamp(
            this.ship.getStateComponent().getFleeHp(),
            0,
            100
        );
        if (fleeHp <= 0) {
            return false;
        }
        return this.ship.getHealth() <= this.ship.getMaxHealth() * (fleeHp / 100.0F);
    }

    public void tickRetreatMovement() {
        LivingEntity owner = this.ship.getOwner();
        if (owner == null) {
            this.ship.getNavigation().stop();
            return;
        }

        double distanceSqr = this.ship.distanceToSqr(owner);
        if (distanceSqr > 4.0D) {
            this.ship.getNavigation().moveTo(owner, 1.25D);
        } else {
            this.ship.getNavigation().stop();
        }
        this.ship.getLookControl().setLookAt(owner, 30.0F, 30.0F);
    }

    public void applyDeadFloatStabilization() {
        Vec3 motion = this.ship.getDeltaMovement();
        double motionY = Mth.clamp(
            motion.y * 0.55D + computeDeadFluidSurfaceCorrection(0.08D),
            -0.05D,
            0.05D
        );
        double motionX = motion.x * 0.3D;
        double motionZ = motion.z * 0.3D;

        if (Math.abs(motionX) < EntityShipBase.DEAD_FLOAT_STOP_EPSILON) {
            motionX = 0.0D;
        }
        if (Math.abs(motionZ) < EntityShipBase.DEAD_FLOAT_STOP_EPSILON) {
            motionZ = 0.0D;
        }

        this.ship.setDeltaMovement(motionX, motionY, motionZ);
    }

    private double computeDeadFluidSurfaceCorrection(double strength) {
        double surfaceY = getDeadFluidSurfaceY();
        if (Double.isNaN(surfaceY)) {
            return 0.0D;
        }

        double targetY = surfaceY - EntityShipBase.DEAD_FLOAT_HOVER_OFFSET;
        return Mth.clamp((targetY - this.ship.getY()) * strength, -0.03D, 0.03D);
    }

    private double getDeadFluidSurfaceY() {
        Level level = this.ship.level();
        BlockPos pos = BlockPos.containing(
            this.ship.getX(),
            this.ship.getY(),
            this.ship.getZ()
        );
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
    void tickSearchlightAssist() {
        if (!Config.canSearchlight) {
            return;
        }
        if ((this.ship.tickCount % Config.searchlightCD) != 0) {
            return;
        }
        if (!this.ship.hasSearchlightEquip() || !this.ship.isAlive()) {
            return;
        }
        if (this.ship.level().isClientSide) {
            return;
        }

        BlockPos pos = this.ship.blockPosition();
        if (this.ship.level().getBrightness(LightLayer.BLOCK, pos) < 10) {
            BlockHelper.placeLightBlock(this.ship.level(), pos);
        } else {
            BlockHelper.updateNearbyLightBlock(this.ship.level(), pos);
        }
    }
}
