package org.trp.shincolle.entity.base.path;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class ShipLegacyNavigation extends GroundPathNavigation {

    private static final int STUCK_CHECK_INTERVAL = 32;
    private static final int STUCK_MAX_TICKS = 100;
    private static final double STUCK_DISTANCE_SQR = 1.0D;
    private static final double POSITION_TIMEOUT_MOVED_SQR = 0.25D;
    private static final float JUMP_SPRINT_FACTOR = 0.35F;
    private static final float UNSTUCK_DIRECTION_FACTOR = 0.5F;
    private static final double LIQUID_HOVER_OFFSET = 0.08D;

    private final ShipLegacyPathFinder pathFinder;
    private final float maxDistanceToWaypoint;
    private final int hostCeilWidth;
    private final int hostCeilHeight;
    private final int hostCeilDepth;

    private ShipLegacyPath currentPath;
    private double speedModifier;
    private int totalTicks;
    private int ticksAtLastPos;
    private Vec3 lastPosCheck = Vec3.ZERO;
    private Vec3 lastPosStuck = Vec3.ZERO;
    private long timeoutCachedNode;
    private long timeoutTimer;
    private double timeoutLimit;
    private BlockPos targetPos;

    public ShipLegacyNavigation(Mob mob, Level level) {
        super(mob, level);

        this.pathFinder = new ShipLegacyPathFinder(level, false);
        this.hostCeilWidth = Mth.floor(mob.getBbWidth() + 1.0F);
        this.hostCeilHeight = Mth.floor(mob.getBbHeight() + 1.0F);
        this.hostCeilDepth = this.hostCeilWidth;
        this.maxDistanceToWaypoint = mob.getBbWidth() > 0.75F ? mob.getBbWidth() * 0.5F : 0.75F - mob.getBbWidth() * 0.5F;
    }

    @Override
    public boolean moveTo(double x, double y, double z, double speed) {
        if (!canNavigate()) {
            return false;
        }

        BlockPos pos = BlockPos.containing(x, y, z);
        ShipLegacyPath path = getPathToPos(pos);
        return setPath(path, speed);
    }

    @Override
    public boolean moveTo(Entity entity, double speed) {
        if (!canNavigate()) {
            return false;
        }

        ShipLegacyPath path = getPathToEntity(entity);
        return setPath(path, speed);
    }

    @Override
    public void tick() {
        this.totalTicks++;

        if (noPath()) {
            return;
        }

        if (canNavigate()) {
            pathFollow();
        }

        if (noPath()) {
            return;
        }

        Vec3 target = this.currentPath.getPosition(this.mob);
        double wantedY = target.y;

        if (isInLiquid()) {
            double hoverY = getLiquidHoverY(target);
            wantedY = Mth.lerp(0.4D, this.mob.getY(), hoverY);
        } else {
            BlockPos pos = BlockPos.containing(target.x, target.y - 0.5D, target.z);
            BlockState state = this.level.getBlockState(pos);
            double maxY = state.getCollisionShape(this.level, pos).max(net.minecraft.core.Direction.Axis.Y);
            if (!Double.isInfinite(maxY) && !Double.isNaN(maxY)) {
                wantedY = pos.getY() + maxY + 0.1D;
            }
        }

        this.mob.getMoveControl().setWantedPosition(target.x, wantedY, target.z, this.speedModifier);
    }

    @Override
    public void stop() {
        this.currentPath = null;
        this.path = null;
    }

    @Override
    public boolean isDone() {
        return noPath();
    }

    public boolean noPath() {
        return this.currentPath == null || this.currentPath.isFinished();
    }

    private ShipLegacyPath getPathToEntity(Entity target) {
        if (this.currentPath != null && !this.currentPath.isFinished()) {
            ShipLegacyPathPoint finalPoint = this.currentPath.getFinalPathPoint();

            if (finalPoint != null
                    && finalPoint.getX() == target.blockPosition().getX()
                    && finalPoint.getY() == target.blockPosition().getY()
                    && finalPoint.getZ() == target.blockPosition().getZ()) {
                return this.currentPath;
            }
        }

        this.targetPos = target.blockPosition();
        float range = getPathSearchRange();
        return this.pathFinder.findPath(this.mob, target, range);
    }

    private ShipLegacyPath getPathToPos(BlockPos pos) {
        if (this.currentPath != null
                && !this.currentPath.isFinished()
                && pos.equals(this.targetPos)) {
            return this.currentPath;
        }

        this.targetPos = pos;
        float range = getPathSearchRange();
        return this.pathFinder.findPath(this.mob, pos.getX(), pos.getY(), pos.getZ(), range);
    }

    private boolean setPath(ShipLegacyPath path, double speed) {
        if (path == null) {
            this.currentPath = null;
            this.path = null;
            return false;
        }

        this.currentPath = path;
        this.path = null;
        this.speedModifier = speed;

        Vec3 hostPos = getEntityPosition();
        this.ticksAtLastPos = this.totalTicks;
        this.lastPosCheck = hostPos;
        this.lastPosStuck = hostPos;
        this.timeoutCachedNode = 0L;
        this.timeoutTimer = 0L;
        this.timeoutLimit = 0.0D;
        return true;
    }

    private float getPathSearchRange() {
        double follow = this.mob.getAttributeValue(Attributes.FOLLOW_RANGE);
        return (float) Math.max(48.0D, follow);
    }

    private boolean canNavigate() {
        return !this.mob.isPassenger()
                && (this.mob.onGround() || isInLiquid() || this.mob.isNoGravity()
                    || this.mob.fallDistance < 2.0F);
    }

    private void pathFollow() {
        Vec3 hostPos = getEntityPosition();
        int limit = this.currentPath.getCurrentPathLength();

        for (int i = this.currentPath.getCurrentPathIndex(); i < this.currentPath.getCurrentPathLength(); i++) {
            if (this.currentPath.getPathPointFromIndex(i).getY() != Mth.floor(hostPos.y)) {
                limit = i;
                break;
            }
        }

        Vec3 nextPos = this.currentPath.getCurrentPos();
        double centerX = nextPos.x + 0.5D;
        double centerZ = nextPos.z + 0.5D;

        double reach = this.maxDistanceToWaypoint + Math.min(1.25D, this.speedModifier * 0.35D);
        double dx = this.mob.getX() - centerX;
        double dz = this.mob.getZ() - centerZ;

        if (dx * dx + dz * dz <= reach * reach) {
            this.currentPath.incrementPathIndex();
        }

        for (int i = limit - 1; i >= this.currentPath.getCurrentPathIndex(); i--) {
            Vec3 test = this.currentPath.getVectorFromIndex(this.mob, i);
            if (isDirectPathBetweenPoints(hostPos, test, this.hostCeilWidth, this.hostCeilHeight, this.hostCeilDepth)) {
                this.currentPath.setCurrentPathIndex(i);
                break;
            }
        }

        checkForStuck(hostPos);
    }

    private void checkForStuck(Vec3 hostPos) {
        if (hasExceededMaxTicks()) {
            this.lastPosStuck = hostPos;
            this.ticksAtLastPos = this.totalTicks;
        }

        if (isStuck(hostPos) && this.currentPath != null) {
            applyUnstuckMotion(hostPos);
            this.lastPosStuck = hostPos;
            this.ticksAtLastPos = this.totalTicks;
        }

        checkPathTimeout(hostPos);
    }

    private boolean hasExceededMaxTicks() {
        return this.totalTicks - this.ticksAtLastPos > STUCK_CHECK_INTERVAL;
    }

    private boolean isStuck(Vec3 hostPos) {
        return hostPos.distanceToSqr(this.lastPosStuck) < STUCK_DISTANCE_SQR
                && this.totalTicks - this.ticksAtLastPos > STUCK_MAX_TICKS;
    }

    private void applyUnstuckMotion(Vec3 hostPos) {
        Vec3 targetPos = this.currentPath.getPosition(this.mob);

        if (targetPos == null) {
            return;
        }

        double dx = targetPos.x - hostPos.x;
        double dz = targetPos.z - hostPos.z;
        double lengthSq = dx * dx + dz * dz;

        if (lengthSq < 1.0E-6D) {
            return;
        }

        double length = Math.sqrt(lengthSq);
        double dirX = dx / length;
        double dirZ = dz / length;

        Vec3 motion = this.mob.getDeltaMovement();
        this.mob.setDeltaMovement(
                dirX * UNSTUCK_DIRECTION_FACTOR * this.speedModifier,
                motion.y,
                dirZ * UNSTUCK_DIRECTION_FACTOR * this.speedModifier);

        if (this.mob.getRandom().nextBoolean()) {
            this.mob.getJumpControl().jump();
            float bonus = this.mob.getSpeed() * JUMP_SPRINT_FACTOR;
            this.mob.setDeltaMovement(
                    this.mob.getDeltaMovement().x + dirX * bonus,
                    this.mob.getDeltaMovement().y,
                    this.mob.getDeltaMovement().z + dirZ * bonus);
        }
    }

    private void checkPathTimeout(Vec3 hostPos) {
        if (this.currentPath == null || this.currentPath.isFinished()) {
            this.timeoutTimer = 0L;
            return;
        }

        Vec3 currentNode = hostPos;

        if (currentNode.distanceToSqr(this.lastPosCheck) < POSITION_TIMEOUT_MOVED_SQR) {
            if (this.timeoutCachedNode == this.currentPath.getCurrentPathIndex()) {
                this.timeoutTimer++;

                if (this.timeoutLimit <= 0.0D) {
                    Vec3 pathPos = this.currentPath.getPosition(this.mob);

                    if (pathPos != null) {
                        double dist = hostPos.distanceTo(pathPos);
                        double speed = Math.max(0.01D, this.mob.getSpeed());
                        this.timeoutLimit = dist / speed * 60.0D;
                    }
                }

                if (this.timeoutLimit > 0.0D && this.timeoutTimer > this.timeoutLimit * 2.0D) {
                    stop();
                    this.timeoutCachedNode = 0L;
                    this.timeoutTimer = 0L;
                    this.timeoutLimit = 0.0D;
                }
            } else {
                this.timeoutCachedNode = this.currentPath.getCurrentPathIndex();
                this.timeoutTimer = 0L;
                this.timeoutLimit = 0.0D;
            }
        } else {
            this.timeoutCachedNode = this.currentPath.getCurrentPathIndex();
            this.timeoutTimer = 0L;
            this.timeoutLimit = 0.0D;
        }

        this.lastPosCheck = currentNode;
    }

    private Vec3 getEntityPosition() {
        return new Vec3(this.mob.getX(), getPathableYPos(), this.mob.getZ());
    }

    private int getPathableYPos() {
        if (this.mob.isInWater() || this.mob.isInLava()) {
            BlockPos.MutableBlockPos pos = this.mob.blockPosition().mutable();
            int y = pos.getY();
            int scan = 0;

            while (scan++ < 16 && y < this.level.getMaxBuildHeight() && !this.level.getFluidState(pos).isEmpty()) {
                y++;
                pos.setY(y);
            }

            return y;
        }

        return Mth.floor(this.mob.getY() + 0.5D);
    }

    private double getLiquidHoverY(Vec3 target) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(target.x, target.y, target.z);
        Level level = this.level;
        int minY = level.getMinBuildHeight();

        while (pos.getY() > minY) {
            FluidState fluid = level.getFluidState(pos);
            if (!fluid.isEmpty()) {
                return pos.getY() + fluid.getHeight(level, pos) - LIQUID_HOVER_OFFSET;
            }
            pos.move(net.minecraft.core.Direction.DOWN);
        }

        BlockPos mobPos = this.mob.blockPosition();
        FluidState mobFluid = level.getFluidState(mobPos);
        if (mobFluid.isEmpty()) {
            BlockPos below = mobPos.below();
            mobFluid = level.getFluidState(below);
            if (!mobFluid.isEmpty()) {
                mobPos = below;
            }
        }
        if (!mobFluid.isEmpty()) {
            return mobPos.getY() + mobFluid.getHeight(level, mobPos) - LIQUID_HOVER_OFFSET;
        }

        return target.y;
    }

    private boolean isDirectPathBetweenPoints(Vec3 from, Vec3 to, int sizeX, int sizeY, int sizeZ) {
        int x1 = Mth.floor(from.x);
        int y1 = (int) from.y;
        int z1 = Mth.floor(from.z);
        double dx = to.x - from.x;
        double dy = to.y - from.y;
        double dz = to.z - from.z;
        double offsetSq = dx * dx + dy * dy + dz * dz;

        if (offsetSq < 1.0E-8D) {
            return false;
        }

        double invDist = 1.0D / Math.sqrt(offsetSq);
        dx *= invDist;
        dy *= invDist;
        dz *= invDist;

        if (!this.isSafeToStandAt(x1, y1, z1, sizeX + 2, sizeY + 1, sizeZ + 2, from, dx, dz)) {
            return false;
        }

        double unitX = 1.0D / Math.abs(dx);
        double unitY = 1.0D / Math.abs(dy);
        double unitZ = 1.0D / Math.abs(dz);
        double proX = x1 - from.x;
        double proY = y1 - from.y;
        double proZ = z1 - from.z;

        if (dx >= 0.0D) proX += 1.0D;
        if (dy >= 0.0D) proY += 1.0D;
        if (dz >= 0.0D) proZ += 1.0D;

        proX /= dx;
        proY /= dy;
        proZ /= dz;

        int dirX = dx < 0.0D ? -1 : 1;
        int dirY = dy < 0.0D ? -1 : 1;
        int dirZ = dz < 0.0D ? -1 : 1;
        int x2 = Mth.floor(to.x);
        int y2 = Mth.floor(to.y);
        int z2 = Mth.floor(to.z);
        int xIntOffset = x2 - x1;
        int yIntOffset = y2 - y1;
        int zIntOffset = z2 - z1;

        while (xIntOffset * dirX > 0 || yIntOffset * dirY > 0 || zIntOffset * dirZ > 0) {
            if (proX < proY && proX < proZ) {
                proX += unitX;
                x1 += dirX;
                xIntOffset = x2 - x1;
            } else if (proY < proX && proY < proZ) {
                proY += unitY;
                y1 += dirY;
                yIntOffset = y2 - y1;
            } else {
                proZ += unitZ;
                z1 += dirZ;
                zIntOffset = z2 - z1;
            }

            if (!this.isSafeToStandAt(x1, y1, z1, sizeX, sizeY, sizeZ, from, dx, dz)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSafeToStandAt(
            int xOffset,
            int yOffset,
            int zOffset,
            int xSize,
            int ySize,
            int zSize,
            Vec3 orgPos,
            double vecX,
            double vecZ) {
        int xSize2 = xOffset - xSize / 2;
        int zSize2 = zOffset - zSize / 2;

        if (!this.isPositionClear(xSize2, yOffset, zSize2, xSize, ySize, zSize, orgPos, vecX, vecZ)) {
            return false;
        }

        if (isInLiquid()) {
            return true;
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x1 = xSize2; x1 < xSize2 + xSize; x1++) {
            for (int z1 = zSize2; z1 < zSize2 + zSize; z1++) {
                double x2 = x1 + 0.5D - orgPos.x;
                double z2 = z1 + 0.5D - orgPos.z;

                if (x2 * vecX + z2 * vecZ < 0.0D) {
                    continue;
                }

                pos.set(x1, yOffset - 1, z1);

                if (this.level.getBlockState(pos).isAir()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isPositionClear(
            int x,
            int y,
            int z,
            int sizeX,
            int sizeY,
            int sizeZ,
            Vec3 from,
            double dirX,
            double dirZ) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int ix = x; ix < x + sizeX; ix++) {
            for (int iy = y; iy < y + sizeY; iy++) {
                for (int iz = z; iz < z + sizeZ; iz++) {
                    double deltaX = ix + 0.5D - from.x;
                    double deltaZ = iz + 0.5D - from.z;

                    if (deltaX * dirX + deltaZ * dirZ < 0.0D) {
                        continue;
                    }

                    pos.set(ix, iy, iz);
                    BlockState state = this.level.getBlockState(pos);

                    if (state.getBlock() instanceof StairBlock
                            || state.getBlock() instanceof LadderBlock
                            || state.is(BlockTags.CLIMBABLE)) {
                        return false;
                    }

                    if (!state.getCollisionShape(this.level, pos).isEmpty()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isInLiquid() {
        return this.mob.isInWaterOrBubble() || this.mob.isInLava();
    }
}
