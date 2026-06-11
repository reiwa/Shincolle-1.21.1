package org.trp.shincolle.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.init.ModEntities;

import java.util.Optional;
import java.util.UUID;

public class EntityProjectileStatic extends Entity {
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityProjectileStatic.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityProjectileStatic.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LIFE_LENGTH = SynchedEntityData.defineId(EntityProjectileStatic.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> PULL_FORCE = SynchedEntityData.defineId(EntityProjectileStatic.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> RANGE = SynchedEntityData.defineId(EntityProjectileStatic.class, EntityDataSerializers.FLOAT);

    public EntityProjectileStatic(EntityType<? extends EntityProjectileStatic> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    public EntityProjectileStatic(Level level) {
        this(ModEntities.PROJECTILE_STATIC.get(), level);
    }

    public void initAttrs(Entity owner, int type, int lifeLength, float pullForce, float range) {
        if (owner != null) {
            setOwner(owner);
            this.setPos(owner.getX(), owner.getY() + owner.getBbHeight() * 0.6D, owner.getZ());
        }
        setProjectileType(type);
        setLifeLength(lifeLength);
        setPullForce(pullForce);
        setRange(range);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(OWNER_UUID, Optional.empty());
        builder.define(TYPE, 0);
        builder.define(LIFE_LENGTH, 20);
        builder.define(PULL_FORCE, 0.0F);
        builder.define(RANGE, 0.0F);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("Owner")) {
            this.entityData.set(OWNER_UUID, Optional.of(tag.getUUID("Owner")));
        }
        this.entityData.set(TYPE, tag.getInt("Type"));
        this.entityData.set(LIFE_LENGTH, tag.getInt("Life"));
        this.entityData.set(PULL_FORCE, tag.getFloat("PullForce"));
        this.entityData.set(RANGE, tag.getFloat("Range"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        getOwnerUuid().ifPresent(uuid -> tag.putUUID("Owner", uuid));
        tag.putInt("Type", getProjectileType());
        tag.putInt("Life", getLifeLength());
        tag.putFloat("PullForce", getPullForce());
        tag.putFloat("Range", getRange());
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            if (this.tickCount == 1) {
                spawnSphereLight();
            }
            return;
        }

        if (this.tickCount > getLifeLength()) {
            this.discard();
            return;
        }

        Entity owner = getOwnerEntity();
        if (owner == null || !owner.isAlive()) {
            this.discard();
            return;
        }

        if ((this.tickCount & 3) == 0) {
            AABB area = this.getBoundingBox().inflate(getRange());
            for (Entity target : this.level().getEntities(this, area, this::canPullEntity)) {
                Vec3 dist = this.position().subtract(target.position());
                if (dist.lengthSqr() <= 1.0D) {
                    continue;
                }
                target.setDeltaMovement(target.getDeltaMovement().add(dist.x * getPullForce(), dist.y * getPullForce(), dist.z * getPullForce()));
            }
        }
    }

    private void spawnSphereLight() {
        if (this.level().isClientSide) {
            org.trp.shincolle.client.ClientProxy.spawnSphereLight(this.level(),
                    this.getX(), this.getY(), this.getZ(),
                    this.getRange() * 2.0D, (double) this.getId(), (double) this.getProjectileType());
        }
    }

    private boolean canPullEntity(Entity target) {
        Entity owner = getOwnerEntity();
        if (owner == null) {
            return false;
        }
        return target.isAlive() && target.isPickable() && target.isPushable() && target != owner && !owner.isAlliedTo(target);
    }

    public void setOwner(Entity owner) {
        this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
    }

    public Optional<UUID> getOwnerUuid() {
        return this.entityData.get(OWNER_UUID);
    }

    public Entity getOwnerEntity() {
        Optional<UUID> ownerUuid = getOwnerUuid();
        if (ownerUuid.isEmpty() || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }
        return serverLevel.getEntity(ownerUuid.get());
    }

    public void setProjectileType(int type) {
        this.entityData.set(TYPE, type);
    }

    public int getProjectileType() {
        return this.entityData.get(TYPE);
    }

    public void setLifeLength(int lifeLength) {
        this.entityData.set(LIFE_LENGTH, lifeLength);
    }

    public int getLifeLength() {
        return this.entityData.get(LIFE_LENGTH);
    }

    public void setPullForce(float pullForce) {
        this.entityData.set(PULL_FORCE, pullForce);
    }

    public float getPullForce() {
        return this.entityData.get(PULL_FORCE);
    }

    public void setRange(float range) {
        this.entityData.set(RANGE, range);
    }

    public float getRange() {
        return this.entityData.get(RANGE);
    }
}
