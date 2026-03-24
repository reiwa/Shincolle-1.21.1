package org.trp.shincolle.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.init.ModEntities;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class EntityProjectileBeam extends Entity {
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityProjectileBeam.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(EntityProjectileBeam.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> LIFE = SynchedEntityData.defineId(EntityProjectileBeam.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityProjectileBeam.class, EntityDataSerializers.INT);

    private static final int TYPE_SHORT = 1;
    private static final int LIFE_SHORT = 8;
    private static final int LIFE_LONG = 31;
    private static final float SPEED_SHORT = 3.0F;
    private static final float SPEED_LONG = 4.0F;

    private int age;
    private double accX;
    private double accY;
    private double accZ;
    private final Set<UUID> damagedTargets = new HashSet<>();

    public EntityProjectileBeam(EntityType<? extends EntityProjectileBeam> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    public EntityProjectileBeam(Level level) {
        this(ModEntities.PROJECTILE_BEAM.get(), level);
    }

    public void initAttrs(Entity owner, int type, float ax, float ay, float az, float damage) {
        this.setOwner(owner);
        this.setBeamType(type);
        this.setDamage(damage);

        float speed;
        if (type == TYPE_SHORT) {
            this.setPos(owner.getX(), owner.getY() + owner.getBbHeight() * 0.75D, owner.getZ());
            this.setLife(LIFE_SHORT);
            speed = SPEED_SHORT;
        } else {
            this.setPos(owner.getX() + ax, owner.getY() + owner.getBbHeight() * 0.5D, owner.getZ() + az);
            this.setLife(LIFE_LONG);
            speed = SPEED_LONG;
        }

        this.accX = ax * speed;
        this.accY = ay * speed;
        this.accZ = az * speed;
        this.setDeltaMovement(this.accX, this.accY, this.accZ);
        this.updateRotationFromMovement(this.getDeltaMovement());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(OWNER_UUID, Optional.empty());
        builder.define(DAMAGE, 6.0F);
        builder.define(LIFE, LIFE_LONG);
        builder.define(TYPE, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("Owner")) {
            this.entityData.set(OWNER_UUID, Optional.of(tag.getUUID("Owner")));
        }
        this.entityData.set(DAMAGE, tag.getFloat("Damage"));
        this.entityData.set(LIFE, tag.getInt("Life"));
        this.entityData.set(TYPE, tag.getInt("Type"));
        this.age = tag.getInt("Age");
        this.accX = tag.getDouble("AccX");
        this.accY = tag.getDouble("AccY");
        this.accZ = tag.getDouble("AccZ");
        this.setDeltaMovement(this.accX, this.accY, this.accZ);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        getOwnerUuid().ifPresent(uuid -> tag.putUUID("Owner", uuid));
        tag.putFloat("Damage", getDamage());
        tag.putInt("Life", getLife());
        tag.putInt("Type", getBeamType());
        tag.putInt("Age", this.age);
        tag.putDouble("AccX", this.accX);
        tag.putDouble("AccY", this.accY);
        tag.putDouble("AccZ", this.accZ);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            return;
        }

        this.age++;
        if (this.age > getLife()) {
            this.discard();
            return;
        }
        Entity owner = getOwnerEntity();
        if (owner == null || !owner.isAlive()) {
            this.discard();
            return;
        }

        Vec3 delta = new Vec3(this.accX, this.accY, this.accZ);
        this.setDeltaMovement(delta);
        move(MoverType.SELF, delta);
        updateRotationFromMovement(delta);

        for (Entity target : this.level().getEntities(this, this.getBoundingBox().inflate(1.5D), this::canHitEntity)) {
            if (this.damagedTargets.add(target.getUUID())) {
                onImpact(target, owner);
            }
        }
    }

    private boolean canHitEntity(Entity target) {
        if (!target.isAlive() || !target.isPickable()) {
            return false;
        }
        Entity owner = getOwnerEntity();
        if (owner == null || target == owner) {
            return false;
        }
        return !owner.isAlliedTo(target);
    }

    private void onImpact(Entity target, Entity owner) {
        DamageSource source = owner instanceof LivingEntity livingOwner
                ? this.damageSources().mobAttack(livingOwner)
                : this.damageSources().generic();
        target.hurt(source, getDamage());
    }

    private void updateRotationFromMovement(Vec3 delta) {
        double dx = delta.x;
        double dz = delta.z;
        double dy = delta.y;
        float yaw = (float) (Mth.atan2(dz, dx) * (180.0F / Math.PI)) - 90.0F;
        float pitch = (float) (-(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180.0F / Math.PI)));
        this.setYRot(yaw);
        this.setXRot(pitch);
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    public void setOwner(Entity owner) {
        this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
    }

    public Entity getOwnerEntity() {
        Optional<UUID> ownerUuid = getOwnerUuid();
        if (ownerUuid.isEmpty() || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }
        return serverLevel.getEntity(ownerUuid.get());
    }

    public Optional<UUID> getOwnerUuid() {
        return this.entityData.get(OWNER_UUID);
    }

    public void setDamage(float damage) {
        this.entityData.set(DAMAGE, damage);
    }

    public float getDamage() {
        return this.entityData.get(DAMAGE);
    }

    public void setLife(int life) {
        this.entityData.set(LIFE, life);
    }

    public int getLife() {
        return this.entityData.get(LIFE);
    }

    public void setBeamType(int type) {
        this.entityData.set(TYPE, type);
    }

    public int getBeamType() {
        return this.entityData.get(TYPE);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
