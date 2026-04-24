package org.trp.shincolle.entity.base;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.EntityAircraftBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.menu.ShipContainerMenu;

class EntityShipBaseCombat {
    private static final float HEAVY_MISSILE_DAMAGE_MULTIPLIER = 1.4F;
    private static final float HEAVY_MISSILE_SPEED = 0.7F;
    private static final int HEAVY_MISSILE_LIFE = 200;
    private static final float HEAVY_MISSILE_EXPLOSION_RADIUS = 3.5F;

    private static final int AMMO_LIGHT_VALUE = 1;
    private static final int AMMO_LIGHT_CONTAINER_VALUE = 10;
    private static final int AMMO_HEAVY_VALUE = 1;
    private static final int AMMO_HEAVY_CONTAINER_VALUE = 10;
    private static final int AIRCRAFT_LIGHT_AMMO_COST = 6;
    private static final int AIRCRAFT_HEAVY_AMMO_COST = 2;
    private static final int AIRCRAFT_RECOVERY_BASE_DELAY = 120;
    private static final int AIRCRAFT_COOLDOWN_FALLBACK = 40;

    private int aircraftRecoveryTick = 0;
    private int aircraftLaunchDelay = 20;
    private boolean aircraftLaunchTypeLight = false;

    private final EntityShipBase ship;

    EntityShipBaseCombat(EntityShipBase ship) {
        this.ship = ship;
    }

    boolean canUseLightAmmo() {
        return this.ship.isStateLightAttack()
                && this.ship.getAmmoLight() > 0;
    }

    boolean canUseHeavyAmmo() {
        return this.ship.isStateHeavyAttack()
                && this.ship.getAmmoHeavy() > 0;
    }

    boolean canUseMeleeAttack() {
        return this.ship.isStateCanMelee();
    }

    boolean canUseLightAircraft() {
        return this.ship.supportsAircraftCombat()
                && this.ship.isStateLightAircraftAttack()
                && this.ship.hasAirLight()
                && this.ship.getAmmoLight() >= AIRCRAFT_LIGHT_AMMO_COST;
    }

    boolean canUseHeavyAircraft() {
        return this.ship.supportsAircraftCombat()
                && this.ship.isStateHeavyAircraftAttack()
                && this.ship.hasAirHeavy()
                && this.ship.getAmmoHeavy() >= AIRCRAFT_HEAVY_AMMO_COST;
    }

    boolean hasAircraftAttackEnabled() {
        return canUseLightAircraft() || canUseHeavyAircraft();
    }

    void tickAircraftRecovery() {
        if (!this.ship.supportsAircraftCombat()) {
            return;
        }

        int maxLight = getMaxAircraftLight();
        int maxHeavy = getMaxAircraftHeavy();
        if (maxLight <= 0 && maxHeavy <= 0) {
            return;
        }

        if (this.ship.getNumAircraftLight() <= 0 && this.ship.getNumAircraftHeavy() <= 0 && this.ship.tickCount < 20) {
            this.ship.setNumAircraftLight(maxLight);
            this.ship.setNumAircraftHeavy(maxHeavy);
        }

        if (this.ship.getNumAircraftLight() > maxLight) {
            this.ship.setNumAircraftLight(maxLight);
        }
        if (this.ship.getNumAircraftHeavy() > maxHeavy) {
            this.ship.setNumAircraftHeavy(maxHeavy);
        }

        this.aircraftRecoveryTick--;
        if (this.aircraftRecoveryTick > 0) {
            return;
        }

        this.aircraftRecoveryTick = Math.max(20, AIRCRAFT_RECOVERY_BASE_DELAY);
        if (this.ship.getNumAircraftLight() < maxLight) {
            this.ship.setNumAircraftLight(this.ship.getNumAircraftLight() + 1);
        }
        if (this.ship.getNumAircraftHeavy() < maxHeavy) {
            this.ship.setNumAircraftHeavy(this.ship.getNumAircraftHeavy() + 1);
        }
    }

    boolean tryPerformAircraftCycle(Entity target) {
        if (!this.ship.supportsAircraftCombat()) {
            return false;
        }
        if (!(this.ship.level() instanceof ServerLevel)) {
            return false;
        }
        if (target == null || !target.isAlive()) {
            return false;
        }

        this.aircraftLaunchDelay--;
        if (!this.ship.isStateLightAircraftAttack()) {
            this.aircraftLaunchTypeLight = false;
        }
        if (!this.ship.isStateHeavyAircraftAttack()) {
            this.aircraftLaunchTypeLight = true;
        }

        if (this.aircraftLaunchDelay > 0) {
            return false;
        }

        boolean launched = false;
        if (this.aircraftLaunchTypeLight) {
            launched = performLightAircraftAttack(target);
            if (!launched) {
                launched = performHeavyAircraftAttack(target);
            }
        } else {
            launched = performHeavyAircraftAttack(target);
            if (!launched) {
                launched = performLightAircraftAttack(target);
            }
        }

        this.aircraftLaunchTypeLight = !this.aircraftLaunchTypeLight;
        if (launched) {
            int lightDelay = this.ship.getLegacyShipStats().getLightDelay();
            int heavyDelay = this.ship.getLegacyShipStats().getHeavyDelay();
            int delay = Math.max(20, Math.max(lightDelay, heavyDelay));
            this.aircraftLaunchDelay = delay;
            return true;
        }

        this.aircraftLaunchDelay = AIRCRAFT_COOLDOWN_FALLBACK;
        return false;
    }

    void recalculateAmmoCounts() {
        int light = 0;
        int heavy = 0;
        for (int i = 0; i < this.ship.getInventory().getSlots(); i++) {
            ItemStack stack = this.ship.getInventory().getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }
            if (isLightAmmo(stack)) {
                light += stack.getCount() * AMMO_LIGHT_VALUE;
            } else if (isLightAmmoContainer(stack)) {
                light += stack.getCount() * AMMO_LIGHT_CONTAINER_VALUE;
            } else if (isHeavyAmmo(stack)) {
                heavy += stack.getCount() * AMMO_HEAVY_VALUE;
            } else if (isHeavyAmmoContainer(stack)) {
                heavy += stack.getCount() * AMMO_HEAVY_CONTAINER_VALUE;
            }
        }
        this.ship.setAmmoLight(light);
        this.ship.setAmmoHeavy(heavy);
    }

    void performLightAttack(Entity target) {
        if (!canUseLightAmmo()) {
            return;
        }
        if (!(this.ship.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (target == null || !target.isAlive()) {
            return;
        }
        if (!consumeLightAmmo(1)) {
            return;
        }

        float damage = this.ship.getLegacyShipStats().getFirepower();
        if (damage <= 0.0F) {
            damage = 2.0F;
        }
        target.hurt(this.ship.damageSources().mobAttack(this.ship), damage);

        spawnLightAttackTargetParticles(serverLevel, target);
        spawnLightAttackMuzzleParticles(serverLevel, target);
        this.ship.playSound(ModSounds.SHIP_FIRELIGHT.get(), this.ship.getSoundVolume(),
                this.ship.getRandom().nextFloat() * 0.12F + 0.98F);
        this.ship.setAttackTick(50);
        this.ship.applyEmotesReaction(3);
    }

    boolean performHeavyAttack(Entity target) {
        if (!canUseHeavyAmmo()) {
            return false;
        }
        if (!(this.ship.level() instanceof ServerLevel serverLevel)) {
            return false;
        }
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (!consumeHeavyAmmo(1)) {
            return false;
        }

        float damage = this.ship.getLegacyShipStats().getFirepower();
        if (damage <= 0.0F) {
            damage = 4.0F;
        }
        float missileDamage = damage * HEAVY_MISSILE_DAMAGE_MULTIPLIER;

        EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this.ship, target,
                missileDamage, HEAVY_MISSILE_SPEED, HEAVY_MISSILE_LIFE, HEAVY_MISSILE_EXPLOSION_RADIUS);
        serverLevel.addFreshEntity(missile);
        this.ship.playSound(ModSounds.SHIP_FIREHEAVY.get(), this.ship.getSoundVolume(),
                this.ship.getRandom().nextFloat() * 0.12F + 0.83F);
        this.ship.setAttackTick(50);
        this.ship.applyEmotesReaction(3);
        return true;
    }

    boolean consumeHeavyAmmo(int amount) {
        if (amount <= 0) {
            return true;
        }
        int remaining = amount;
        for (int i = 0; i < this.ship.getInventory().getSlots() && remaining > 0; i++) {
            ItemStack stack = this.ship.getInventory().getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }
            if (isHeavyAmmo(stack)) {
                int take = Math.min(stack.getCount(), remaining);
                ItemStack updated = stack.copy();
                updated.shrink(take);
                this.ship.getInventory().setStackInSlot(i, updated);
                remaining -= take;
            } else if (isHeavyAmmoContainer(stack)) {
                if (stack.getCount() <= 0) {
                    continue;
                }
                ItemStack updated = stack.copy();
                updated.shrink(1);
                this.ship.getInventory().setStackInSlot(i, updated);

                int used = Math.min(remaining, AMMO_HEAVY_CONTAINER_VALUE);
                int leftover = AMMO_HEAVY_CONTAINER_VALUE - used;
                remaining -= used;

                if (leftover > 0) {
                    insertAmmoRemainder(ModItems.AMMO_HEAVY.get(), leftover, i);
                }
            }
        }
        return remaining <= 0;
    }

    boolean consumeLightAmmo(int amount) {
        if (amount <= 0) {
            return true;
        }
        int remaining = amount;
        for (int i = 0; i < this.ship.getInventory().getSlots() && remaining > 0; i++) {
            ItemStack stack = this.ship.getInventory().getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }
            if (isLightAmmo(stack)) {
                int take = Math.min(stack.getCount(), remaining);
                ItemStack updated = stack.copy();
                updated.shrink(take);
                this.ship.getInventory().setStackInSlot(i, updated);
                remaining -= take;
            } else if (isLightAmmoContainer(stack)) {
                if (stack.getCount() <= 0) {
                    continue;
                }
                ItemStack updated = stack.copy();
                updated.shrink(1);
                this.ship.getInventory().setStackInSlot(i, updated);

                int used = Math.min(remaining, AMMO_LIGHT_CONTAINER_VALUE);
                int leftover = AMMO_LIGHT_CONTAINER_VALUE - used;
                remaining -= used;

                if (leftover > 0) {
                    insertAmmoRemainder(ModItems.AMMO_LIGHT.get(), leftover, i);
                }
            }
        }
        return remaining <= 0;
    }

    private boolean isLightAmmo(ItemStack stack) {
        return stack.is(ModItems.AMMO_LIGHT.get());
    }

    void returnAircraftToDeck(boolean lightAircraft) {
        if (!this.ship.supportsAircraftCombat()) {
            return;
        }
        if (lightAircraft) {
            int max = getMaxAircraftLight();
            this.ship.setNumAircraftLight(Math.min(max, this.ship.getNumAircraftLight() + 1));
        } else {
            int max = getMaxAircraftHeavy();
            this.ship.setNumAircraftHeavy(Math.min(max, this.ship.getNumAircraftHeavy() + 1));
        }
    }

    private boolean isLightAmmoContainer(ItemStack stack) {
        return stack.is(ModItems.AMMO_LIGHT_CONTAINER.get());
    }

    private boolean isHeavyAmmo(ItemStack stack) {
        return stack.is(ModItems.AMMO_HEAVY.get());
    }

    private boolean isHeavyAmmoContainer(ItemStack stack) {
        return stack.is(ModItems.AMMO_HEAVY_CONTAINER.get());
    }

    private void insertAmmoRemainder(net.minecraft.world.item.Item item, int count, int avoidSlot) {
        if (count <= 0) {
            return;
        }
        ItemStack remaining = new ItemStack(item, count);
        for (int i = 0; i < this.ship.getInventory().getSlots() && !remaining.isEmpty(); i++) {
            if (i == avoidSlot) {
                continue;
            }
            remaining = this.ship.getInventory().insertItem(i, remaining, false);
        }
        if (!remaining.isEmpty() && this.ship.level() instanceof ServerLevel serverLevel) {
            serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.ship.getX(), this.ship.getY(), this.ship.getZ(), remaining));
        }
    }

    private void spawnLightAttackTargetParticles(ServerLevel serverLevel, Entity target) {
        double posX = target.getX();
        double posY = target.getY();
        double posZ = target.getZ();

        serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, posX, posY + 1.5D, posZ,
                1, 0.0D, 0.0D, 0.0D, 0.0D);

        for (int i = 0; i < 15; ++i) {
            double ran1 = (this.ship.getRandom().nextFloat() * 3.0F) - 1.5F;
            double ran2 = (this.ship.getRandom().nextFloat() * 3.0F) - 1.5F;
            serverLevel.sendParticles(ParticleTypes.LAVA,
                    posX + ran1, posY + 1.0D, posZ + ran2,
                    1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }

    private void spawnLightAttackMuzzleParticles(ServerLevel serverLevel, Entity target) {
        Vec3 from = this.ship.position().add(0.0D, 0.8D, 0.0D);
        Vec3 to = target.position().add(0.0D, target.getBbHeight() * 0.5D, 0.0D);
        Vec3 look = to.subtract(from);
        if (look.lengthSqr() < 1.0E-6D) {
            look = this.ship.getLookAngle();
        } else {
            look = look.normalize();
        }

        double posX = this.ship.getX();
        double posY = this.ship.getY();
        double posZ = this.ship.getZ();

        for (int i = 0; i < 24; ++i) {
            double ran1 = this.ship.getRandom().nextFloat() - 0.5F;
            double ran2 = this.ship.getRandom().nextFloat();
            double ran3 = this.ship.getRandom().nextFloat();
            double baseX = posX + look.x - 0.5D + 0.05D * i;
            double baseZ = posZ + look.z - 0.5D + 0.05D * i;

            serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE,
                    baseX, posY + 0.6D + ran1, baseZ,
                    1, look.x * 0.3D * ran2, 0.05D * ran2, look.z * 0.3D * ran2, 0.0D);
            serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE,
                    baseX, posY + 1.0D + ran1, baseZ,
                    1, look.x * 0.3D * ran3, 0.05D * ran3, look.z * 0.3D * ran3, 0.0D);
        }
    }

    private int getMaxAircraftLight() {
        return 8 + this.ship.getLevel() / 5 + (int) (this.ship.getLevel() * this.ship.getAircraftLightLevelBonus());
    }

    private int getMaxAircraftHeavy() {
        return 4 + this.ship.getLevel() / 10 + (int) (this.ship.getLevel() * this.ship.getAircraftHeavyLevelBonus());
    }

    private boolean performLightAircraftAttack(Entity target) {
        if (!canUseLightAircraft()) {
            return false;
        }
        if (!consumeLightAmmo(AIRCRAFT_LIGHT_AMMO_COST)) {
            return false;
        }
        this.ship.setNumAircraftLight(Math.max(0, this.ship.getNumAircraftLight() - 1));
        return spawnAircraft(target, true);
    }

    private boolean performHeavyAircraftAttack(Entity target) {
        if (!canUseHeavyAircraft()) {
            return false;
        }
        if (!consumeHeavyAmmo(AIRCRAFT_HEAVY_AMMO_COST)) {
            return false;
        }
        this.ship.setNumAircraftHeavy(Math.max(0, this.ship.getNumAircraftHeavy() - 1));
        return spawnAircraft(target, false);
    }

    private boolean spawnAircraft(Entity target, boolean lightAircraft) {
        EntityType<? extends net.minecraft.world.entity.TamableAnimal> type = this.ship.getAttackAircraftType(lightAircraft);
        if (type == null || !(this.ship.level() instanceof ServerLevel serverLevel)) {
            return false;
        }

        Entity spawned = type.create(serverLevel);
        if (!(spawned instanceof EntityAircraftBase aircraft)) {
            return false;
        }

        double launchY = this.ship.getY() + this.ship.getAircraftLaunchHeight();
        aircraft.moveTo(this.ship.getX(), launchY, this.ship.getZ(), this.ship.getYRot(), this.ship.getXRot());
        aircraft.initCarrierMission(this.ship, target, lightAircraft);
        serverLevel.addFreshEntity(aircraft);

        this.ship.setAttackTick(50);
        this.ship.applyEmotesReaction(3);
        return true;
    }

    void resetAircraftLaunchDelay() {
        int lightDelay = this.ship.getLegacyShipStats().getLightDelay();
        int heavyDelay = this.ship.getLegacyShipStats().getHeavyDelay();
        this.aircraftLaunchDelay = Math.max(20, Math.max(lightDelay, heavyDelay));
    }
}
