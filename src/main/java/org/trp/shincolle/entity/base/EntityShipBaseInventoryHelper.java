package org.trp.shincolle.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.menu.ShipContainerMenu;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

class EntityShipBaseInventoryHelper {

    private final EntityShipBase ship;

    EntityShipBaseInventoryHelper(EntityShipBase ship) {
        this.ship = ship;
    }

    void tickAutoPickupItems() {
        if (!this.ship.supportsItemPickup()) {
            return;
        }
        if (!this.ship.getStateFlag(ShipContainerMenu.STATE_FLAG_PICK_ITEM)) {
            return;
        }
        if ((this.ship.tickCount % EntityShipBase.PICK_ITEM_SCAN_INTERVAL_TICKS) != 0) {
            return;
        }
        if (
            this.ship.getIsSitting() ||
            this.ship.isPassenger() ||
            this.ship.isVehicle() ||
            this.ship.isInDeadPose()
        ) {
            return;
        }
        if (this.ship.hasPointerTargetEntity() || this.ship.getTarget() != null) {
            return;
        }
        if (!hasCargoRoom()) {
            return;
        }

        ItemEntity target = findNearestPickItem();
        if (target == null) {
            return;
        }

        if (this.ship.distanceToSqr(target) <= 9.0D) {
            tryPickupItemEntity(target);
            this.ship.getNavigation().stop();
        } else {
            this.ship.getNavigation().moveTo(target, 1.0D);
        }
    }

    private boolean hasCargoRoom() {
        int slotCount = this.ship.getAccessibleInventorySlotCount();
        for (
            int i = ShipInventoryHandler.getEquipSlotCount();
            i < slotCount;
            i++
        ) {
            ItemStack stack = this.ship.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                return true;
            }
            int limit = Math.min(
                stack.getMaxStackSize(),
                this.ship.inventory.getSlotLimit(i)
            );
            if (stack.getCount() < limit) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    private ItemEntity findNearestPickItem() {
        double followCap = Math.max(
            2.0D,
            this.ship.getStateMinor(ShipContainerMenu.STATE_MINOR_FOLLOW_MAX)
        );
        double statRange = Math.max(
            2.0D,
            this.ship.getLegacyShipStats().getAttackRange() * 0.5D + 2.0D
        );
        double pickRange = Math.min(followCap + 2.0D, statRange);

        AABB scanBox = this.ship.getBoundingBox().inflate(
            pickRange,
            pickRange * 0.5D + 1.0D,
            pickRange
        );
        LivingEntity owner = this.ship.getOwner();
        List<ItemEntity> items = this.ship.level().getEntitiesOfClass(
            ItemEntity.class,
            scanBox,
            item -> {
                if (!item.isAlive() || item.getItem().isEmpty() || item.hasPickUpDelay()) {
                    return false;
                }
                if (owner != null) {
                    if (item.distanceToSqr(owner) > followCap * followCap) {
                        return false;
                    }
                }
                return true;
            }
        );
        if (items.isEmpty()) {
            return null;
        }

        items.sort((a, b) ->
            Double.compare(this.ship.distanceToSqr(a), this.ship.distanceToSqr(b))
        );
        return items.get(0);
    }

    private void tryPickupItemEntity(ItemEntity itemEntity) {
        ItemStack stack = itemEntity.getItem();
        if (stack.isEmpty()) {
            return;
        }

        int originalCount = stack.getCount();
        ItemStack remaining = insertIntoCargo(stack.copy());
        int inserted = originalCount - remaining.getCount();
        if (inserted <= 0) {
            return;
        }

        if (remaining.isEmpty()) {
            itemEntity.discard();
        } else {
            itemEntity.setItem(remaining);
        }

        this.ship.playSound(
            SoundEvents.ITEM_PICKUP,
            0.2F,
            ((this.ship.getRandom().nextFloat() - this.ship.getRandom().nextFloat()) *
                    0.7F +
                1.0F) * 2.0F
        );
        this.ship.playItemPickupSound();
    }

    private ItemStack insertIntoCargo(ItemStack stack) {
        ItemStack remaining = stack;
        int slotCount = this.ship.getAccessibleInventorySlotCount();
        for (
            int i = ShipInventoryHandler.getEquipSlotCount();
            i < slotCount && !remaining.isEmpty();
            i++
        ) {
            remaining = this.ship.inventory.insertItem(i, remaining, false);
        }
        return remaining;
    }

    boolean hasLiquidDrumEquip() {
        int equipSlots = Math.min(
            ShipInventoryHandler.getEquipSlotCount(),
            this.ship.inventory.getSlots()
        );
        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = this.ship.inventory.getStackInSlot(slot);
            if (
                stack.isEmpty() ||
                !(stack.getItem() instanceof LegacyEquipItem equipItem)
            ) {
                continue;
            }
            if (
                equipItem.getEquipTypeId(stack) == EntityShipBase.EQUIP_TYPE_DRUM &&
                equipItem.getVariant(stack) == EntityShipBase.EQUIP_DRUM_VARIANT_LIQUID
            ) {
                return true;
            }
        }
        return false;
    }

    void tickAutoPump() {
        if (!this.ship.getStateFlag(ShipContainerMenu.STATE_FLAG_AUTO_PUMP)) {
            return;
        }
        if (!hasLiquidDrumEquip()) {
            return;
        }
        if (
            this.ship.getIsSitting() ||
            this.ship.isPassenger() ||
            this.ship.isVehicle() ||
            this.ship.isInDeadPose()
        ) {
            return;
        }

        tickAutoPumpXp();

        if ((this.ship.tickCount % EntityShipBase.AUTO_PUMP_INTERVAL_TICKS) != 0) {
            return;
        }

        BlockPos sourcePos = findNearbyPumpSource();
        if (sourcePos == null) {
            return;
        }

        FluidState fluidState = this.ship.level().getFluidState(sourcePos);
        if (!fluidState.is(Fluids.WATER) && !fluidState.is(Fluids.LAVA)) {
            return;
        }

        FluidStack pumpedFluid = new FluidStack(
            fluidState.getType(),
            FluidType.BUCKET_VOLUME
        );

        if (tryStorePumpedFluid(pumpedFluid)) {
            if (this.ship.level() instanceof ServerLevel) {
                this.ship.level().setBlockAndUpdate(
                    sourcePos,
                    Blocks.AIR.defaultBlockState()
                );
            }
            net.minecraft.sounds.SoundEvent sound = fluidState.is(Fluids.LAVA)
                ? SoundEvents.BUCKET_FILL_LAVA
                : SoundEvents.BUCKET_FILL;
            this.ship.playSound(
                sound,
                0.5F,
                this.ship.getRandom().nextFloat() * 0.4F + 0.8F
            );
        }
    }

    @Nullable
    private BlockPos findNearbyPumpSource() {
        BlockPos center = this.ship.blockPosition();
        BlockPos bestPos = null;
        double bestDist = Double.MAX_VALUE;

        for (int dx = -3; dx <= 3; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -3; dz <= 3; dz++) {
                    BlockPos pos = center.offset(dx, dy, dz);
                    FluidState fluidState = this.ship.level().getFluidState(pos);
                    if (fluidState.isEmpty() || !fluidState.isSource()) {
                        continue;
                    }
                    if (
                        !fluidState.is(Fluids.WATER) &&
                        !fluidState.is(Fluids.LAVA)
                    ) {
                        continue;
                    }
                    double dist = pos.distToCenterSqr(this.ship.position());
                    if (dist < bestDist) {
                        bestDist = dist;
                        bestPos = pos;
                    }
                }
            }
        }

        return bestPos;
    }

    private boolean tryStorePumpedFluid(FluidStack pumpedFluid) {
        int slotCount = this.ship.getAccessibleInventorySlotCount();
        for (
            int i = ShipInventoryHandler.getEquipSlotCount();
            i < slotCount;
            i++
        ) {
            ItemStack stack = this.ship.inventory.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            ItemStack extracted = this.ship.inventory.extractItem(i, 1, false);
            if (extracted.isEmpty()) {
                continue;
            }

            Optional<IFluidHandlerItem> handlerOptional =
                FluidUtil.getFluidHandler(extracted);
            if (handlerOptional.isEmpty()) {
                ItemStack remainder = this.ship.inventory.insertItem(
                    i,
                    extracted,
                    false
                );
                if (
                    !remainder.isEmpty() &&
                    this.ship.level() instanceof ServerLevel serverLevel
                ) {
                    serverLevel.addFreshEntity(
                        new ItemEntity(
                            serverLevel,
                            this.ship.getX(),
                            this.ship.getY(),
                            this.ship.getZ(),
                            remainder
                        )
                    );
                }
                continue;
            }

            IFluidHandlerItem handler = handlerOptional.get();
            if (
                handler.fill(
                    pumpedFluid.copy(),
                    IFluidHandler.FluidAction.SIMULATE
                ) < pumpedFluid.getAmount()
            ) {
                ItemStack remainder = this.ship.inventory.insertItem(
                    i,
                    extracted,
                    false
                );
                if (
                    !remainder.isEmpty() &&
                    this.ship.level() instanceof ServerLevel serverLevel
                ) {
                    serverLevel.addFreshEntity(
                        new ItemEntity(
                            serverLevel,
                            this.ship.getX(),
                            this.ship.getY(),
                            this.ship.getZ(),
                            remainder
                        )
                    );
                }
                continue;
            }

            int filled = handler.fill(
                pumpedFluid.copy(),
                IFluidHandler.FluidAction.EXECUTE
            );
            ItemStack container = handler.getContainer();
            ItemStack remaining = this.ship.inventory.insertItem(
                i,
                container,
                false
            );
            if (
                !remaining.isEmpty() &&
                this.ship.level() instanceof ServerLevel serverLevel
            ) {
                serverLevel.addFreshEntity(
                    new ItemEntity(
                        serverLevel,
                        this.ship.getX(),
                        this.ship.getY(),
                        this.ship.getZ(),
                        remaining
                    )
                );
            }
            if (filled >= pumpedFluid.getAmount()) {
                return true;
            }
        }
        return false;
    }

    private void tickAutoPumpXp() {
        if ((this.ship.tickCount % EntityShipBase.AUTO_PUMP_XP_INTERVAL_TICKS) != 0) {
            return;
        }

        if (!(this.ship.level() instanceof ServerLevel)) {
            return;
        }

        List<ExperienceOrb> orbs = this.ship.level().getEntitiesOfClass(
            ExperienceOrb.class,
            this.ship.getBoundingBox().inflate(7.0D)
        );
        if (!orbs.isEmpty()) {
            for (ExperienceOrb orb : orbs) {
                if (!orb.isAlive()) {
                    continue;
                }

                double distSqr = this.ship.distanceToSqr(orb);
                if (distSqr > 9.0D) {
                    Vec3 pull = this.ship.position()
                        .add(0.0D, 0.4D, 0.0D)
                        .subtract(orb.position())
                        .normalize()
                        .scale(0.25D);
                    orb.setDeltaMovement(orb.getDeltaMovement().add(pull));
                } else {
                    this.ship.setStateMinor(
                        EntityShipBase.STATE_MINOR_PUMPED_XP,
                        this.ship.getStateMinor(EntityShipBase.STATE_MINOR_PUMPED_XP) +
                            orb.getValue()
                    );
                    orb.discard();
                }
            }
        }

        int bottleSlot = findFirstCargoItem(Items.GLASS_BOTTLE);
        while (
            bottleSlot >= 0 &&
            this.ship.getStateMinor(EntityShipBase.STATE_MINOR_PUMPED_XP) >= EntityShipBase.XP_BOTTLE_COST
        ) {
            ItemStack extracted = this.ship.inventory.extractItem(
                bottleSlot,
                1,
                false
            );
            if (extracted.isEmpty()) {
                break;
            }

            this.ship.setStateMinor(
                EntityShipBase.STATE_MINOR_PUMPED_XP,
                this.ship.getStateMinor(EntityShipBase.STATE_MINOR_PUMPED_XP) - EntityShipBase.XP_BOTTLE_COST
            );

            ItemStack remaining = insertIntoCargo(
                new ItemStack(Items.EXPERIENCE_BOTTLE)
            );
            if (
                !remaining.isEmpty() &&
                this.ship.level() instanceof ServerLevel serverLevel
            ) {
                serverLevel.addFreshEntity(
                    new ItemEntity(
                        serverLevel,
                        this.ship.getX(),
                        this.ship.getY(),
                        this.ship.getZ(),
                        remaining
                    )
                );
            }

            bottleSlot = findFirstCargoItem(Items.GLASS_BOTTLE);
        }
    }

    private int findFirstCargoItem(Item item) {
        int slotCount = this.ship.getAccessibleInventorySlotCount();
        for (
            int i = ShipInventoryHandler.getEquipSlotCount();
            i < slotCount;
            i++
        ) {
            ItemStack stack = this.ship.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.is(item)) {
                return i;
            }
        }
        return -1;
    }

    int findItemInInventory(Item item) {
        int slots = this.ship.inventory.getAccessibleSlotCount();
        for (int i = 0; i < slots; i++) {
            ItemStack stack = this.ship.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.is(item)) {
                return i;
            }
        }
        return -1;
    }

    boolean consumeItemInInventory(Item item) {
        int slot = findItemInInventory(item);
        if (slot >= 0) {
            ItemStack stack = this.ship.inventory.getStackInSlot(slot);
            stack.shrink(1);
            if (stack.isEmpty()) {
                this.ship.inventory.setStackInSlot(slot, ItemStack.EMPTY);
            }
            this.ship.onInventoryChanged();
            return true;
        }
        return false;
    }
}
