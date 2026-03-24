package org.trp.shincolle.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.init.ModEntities;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityShipGrudge extends Entity {
    private static final int DEFAULT_PICKUP_DELAY = 20;
    private static final int DESPAWN_TICKS = 20 * 60 * 5;
    private static final String TAG_ITEM = "StoredItem";
    private static final String TAG_OWNER = "Owner";
    private static final String TAG_PICKUP_DELAY = "PickupDelay";
    private static final String TAG_AGE = "Age";

    private ItemStack storedItem = ItemStack.EMPTY;
    @Nullable
    private UUID ownerId;
    private int pickupDelay = DEFAULT_PICKUP_DELAY;
    private int age;

    public EntityShipGrudge(EntityType<? extends EntityShipGrudge> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    public EntityShipGrudge(Level level, double x, double y, double z, ItemStack stack, @Nullable UUID ownerId) {
        this(ModEntities.SHIP_GRUDGE.get(), level);
        this.setPos(x, y, z);
        this.storedItem = stack.copy();
        this.ownerId = ownerId;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    public void tick() {
        super.tick();
        if (this.pickupDelay > 0) {
            this.pickupDelay--;
        }
        if (!this.level().isClientSide) {
            if (this.age++ >= DESPAWN_TICKS) {
                this.discard();
                return;
            }
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void playerTouch(Player player) {
        if (this.level().isClientSide) {
            return;
        }
        if (this.pickupDelay > 0 || this.storedItem.isEmpty()) {
            return;
        }
        if (this.ownerId != null && !this.ownerId.equals(player.getUUID())) {
            return;
        }
        if (player.getInventory().add(this.storedItem)) {
            player.take(this, this.storedItem.getCount());
            this.storedItem = ItemStack.EMPTY;
            this.discard();
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (!this.storedItem.isEmpty()) {
            tag.put(TAG_ITEM, this.storedItem.save(this.registryAccess()));
        }
        if (this.ownerId != null) {
            tag.putUUID(TAG_OWNER, this.ownerId);
        }
        tag.putInt(TAG_PICKUP_DELAY, this.pickupDelay);
        tag.putInt(TAG_AGE, this.age);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains(TAG_ITEM)) {
            this.storedItem = ItemStack.parse(this.registryAccess(), tag.getCompound(TAG_ITEM)).orElse(ItemStack.EMPTY);
        } else {
            this.storedItem = ItemStack.EMPTY;
        }
        this.ownerId = tag.hasUUID(TAG_OWNER) ? tag.getUUID(TAG_OWNER) : null;
        this.pickupDelay = tag.getInt(TAG_PICKUP_DELAY);
        this.age = tag.getInt(TAG_AGE);
    }
}
