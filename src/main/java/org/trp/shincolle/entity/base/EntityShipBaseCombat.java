package org.trp.shincolle.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.EntityAircraftBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.utility.CombatHelper;

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

    private final IShipCombatOwner ship;

    EntityShipBaseCombat(IShipCombatOwner ship) {
        this.ship = ship;
    }

    boolean canUseLightAmmo() {
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null) {
            return this.ship.getAmmoLight() > 0;
        }
        return this.ship.isStateGuiBtn1()
                && this.ship.isStateLightAttack()
                && (shipEntity.isHostileShipMob()
                    || this.ship.getAmmoLight() > 0
                    || shipEntity.hasItemInInventory(ModItems.AMMO_LIGHT.get())
                    || shipEntity.hasItemInInventory(ModItems.AMMO_LIGHT_CONTAINER.get()));
    }

    boolean canUseHeavyAmmo() {
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null) {
            return this.ship.getAmmoHeavy() > 0;
        }
        return this.ship.isStateGuiBtn2()
                && this.ship.isStateHeavyAttack()
                && (shipEntity.isHostileShipMob()
                    || this.ship.getAmmoHeavy() > 0
                    || shipEntity.hasItemInInventory(ModItems.AMMO_HEAVY.get())
                    || shipEntity.hasItemInInventory(ModItems.AMMO_HEAVY_CONTAINER.get()));
    }

    boolean canUseMeleeAttack() {
        return this.ship.isStateCanMelee();
    }

    boolean canUseLightAircraft() {
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null) {
            return this.ship.hasAirLight() && this.ship.getAmmoLight() >= AIRCRAFT_LIGHT_AMMO_COST;
        }
        return this.ship.isStateGuiBtn3()
                && this.ship.isStateLightAircraftAttack()
                && this.ship.hasAirLight()
                && (shipEntity.isHostileShipMob()
                    || this.ship.getAmmoLight() >= AIRCRAFT_LIGHT_AMMO_COST
                    || shipEntity.hasItemInInventory(ModItems.AMMO_LIGHT.get())
                    || shipEntity.hasItemInInventory(ModItems.AMMO_LIGHT_CONTAINER.get()));
    }

    boolean canUseLightAircraftManual() {
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null) {
            return !isCombatSuppressed() && this.ship.supportsAircraftCombat() && this.ship.hasAirLight() && this.ship.getAmmoLight() >= AIRCRAFT_LIGHT_AMMO_COST;
        }
        return !isCombatSuppressed()
                && this.ship.supportsAircraftCombat()
                && this.ship.hasAirLight()
                && (shipEntity.isHostileShipMob()
                    || this.ship.getAmmoLight() >= AIRCRAFT_LIGHT_AMMO_COST
                    || shipEntity.hasItemInInventory(ModItems.AMMO_LIGHT.get())
                    || shipEntity.hasItemInInventory(ModItems.AMMO_LIGHT_CONTAINER.get()));
    }

    boolean canUseHeavyAircraft() {
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null) {
            return this.ship.hasAirHeavy() && this.ship.getAmmoHeavy() >= AIRCRAFT_HEAVY_AMMO_COST;
        }
        return this.ship.isStateGuiBtn4()
                && this.ship.isStateHeavyAircraftAttack()
                && this.ship.hasAirHeavy()
                && (shipEntity.isHostileShipMob()
                    || this.ship.getAmmoHeavy() >= AIRCRAFT_HEAVY_AMMO_COST
                    || shipEntity.hasItemInInventory(ModItems.AMMO_HEAVY.get())
                    || shipEntity.hasItemInInventory(ModItems.AMMO_HEAVY_CONTAINER.get()));
    }

    boolean canUseHeavyAircraftManual() {
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null) {
            return !isCombatSuppressed() && this.ship.supportsAircraftCombat() && this.ship.hasAirHeavy() && this.ship.getAmmoHeavy() >= AIRCRAFT_HEAVY_AMMO_COST;
        }
        return !isCombatSuppressed()
                && this.ship.supportsAircraftCombat()
                && this.ship.hasAirHeavy()
                && (shipEntity.isHostileShipMob()
                    || this.ship.getAmmoHeavy() >= AIRCRAFT_HEAVY_AMMO_COST
                    || shipEntity.hasItemInInventory(ModItems.AMMO_HEAVY.get())
                    || shipEntity.hasItemInInventory(ModItems.AMMO_HEAVY_CONTAINER.get()));
    }

    boolean hasAircraftAttackEnabled() {
        return canUseLightAircraft() || canUseHeavyAircraft();
    }

    private boolean isCombatSuppressed() {
        return this.ship.isCombatSuppressed();
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

        if (this.ship.getNumAircraftLight() <= 0 && this.ship.getNumAircraftHeavy() <= 0 && this.ship.getTickCount() < 20) {
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
        if (isCombatSuppressed()) {
            return false;
        }
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
        if (isCombatSuppressed()) {
            return;
        }
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

        this.ship.asShipEntity().tryFlareTarget(target);
        if (this.ship.isSubmarine()) {
            performSubmarineLightAttack(serverLevel, target);
        } else {
            performStandardLightAttack(serverLevel, target);
        }
        this.ship.decrMorale(1);
    }

    private void performSubmarineLightAttack(ServerLevel serverLevel, Entity target) {
        float firepower = this.ship.getLegacyShipStats().getFirepower();
        if (firepower <= 0.0F) {
            firepower = 2.0F;
        }

        EntityAbyssMissile.MoveType moveType = EntityAbyssMissile.MoveType.ARC;

        EntityAbyssMissile missile = new EntityAbyssMissile(
                serverLevel,
                this.ship.asShipEntity(),
                target,
                firepower,
                moveType,
                0.65F,
                1.04F,
                1.04F,
                null,
                160,
                3.0F
        );
        missile.setPos(this.ship.getX(), this.ship.getY() + this.ship.getBbHeight() * 0.6D, this.ship.getZ());
        serverLevel.addFreshEntity(missile);

        this.ship.playSound(ModSounds.SHIP_FIREHEAVY.get(), this.ship.getShipSoundVolume(),
                this.ship.getRandom().nextFloat() * 0.12F + 0.83F);
        this.ship.playAttackSound();
        this.ship.setAttackTick(50);
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionLight);
        this.ship.applyEmotesReaction(3);
    }

    private void performStandardLightAttack(ServerLevel serverLevel, Entity target) {
        float damage = this.ship.getLegacyShipStats().getFirepower();
        if (damage <= 0.0F) {
            damage = 2.0F;
        }

        double distance = this.ship.distanceTo(target);
        float finalDamage = CombatHelper.applyCombatRateToDamage(this.ship.asShipEntity(), true, (float) distance, damage);

        if (finalDamage > 0.0F) {
            boolean hurt = target.hurt(this.ship.damageSources().mobAttack(this.ship.asLivingEntity()), finalDamage);
            if (hurt && target instanceof LivingEntity livingTarget) {
                this.ship.applyAttackEffects(livingTarget);
            }
            this.ship.spawnLightAttackTargetParticles(serverLevel, target);
            this.ship.spawnLightAttackMuzzleParticles(serverLevel, target);
            this.ship.playSound(ModSounds.SHIP_FIRELIGHT.get(), this.ship.getShipSoundVolume(),
                    this.ship.getRandom().nextFloat() * 0.12F + 0.98F);
        } else {
            boolean hurt = target.hurt(this.ship.damageSources().mobAttack(this.ship.asLivingEntity()), 0.00001F);
            if (hurt && target instanceof LivingEntity livingTarget) {
                this.ship.applyAttackEffects(livingTarget);
            }
            this.ship.spawnLightAttackMuzzleParticles(serverLevel, target);
            this.ship.playSound(ModSounds.SHIP_FIRELIGHT.get(), this.ship.getShipSoundVolume(),
                    this.ship.getRandom().nextFloat() * 0.12F + 0.98F);
        }

        this.ship.playAttackSound();
        this.ship.setAttackTick(50);
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionLight);
        this.ship.applyEmotesReaction(3);
    }

    boolean performHeavyAttack(Entity target) {
        if (isCombatSuppressed()) {
            return false;
        }
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

        this.ship.asShipEntity().tryFlareTarget(target);

        float damage = this.ship.getLegacyShipStats().getFirepower();
        if (damage <= 0.0F) {
            damage = 4.0F;
        }
        float missileDamage = damage * HEAVY_MISSILE_DAMAGE_MULTIPLIER;

        float speed = HEAVY_MISSILE_SPEED;
        int life = HEAVY_MISSILE_LIFE;
        float radius = HEAVY_MISSILE_EXPLOSION_RADIUS;
        org.trp.shincolle.entity.projectile.EntityAbyssMissile.MoveType moveType = org.trp.shincolle.entity.projectile.EntityAbyssMissile.MoveType.DIRECT;
        int specialEffect = org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_NONE;

        ItemStack ammoStack = ItemStack.EMPTY;
        int equipSlots = Math.min(
            org.trp.shincolle.inventory.ShipInventoryHandler.getEquipSlotCount(),
            this.ship.getInventory().getSlots()
        );
        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = this.ship.getInventory().getStackInSlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof org.trp.shincolle.item.LegacyEquipItem equipItem)) {
                continue;
            }
            int typeId = equipItem.getEquipTypeId(stack);
            if (typeId == EntityShipBase.EQUIP_TYPE_AMMO || typeId == EntityShipBase.EQUIP_TYPE_AMMO_2) {
                ammoStack = stack;
                break;
            }
        }

        if (!ammoStack.isEmpty()) {
            org.trp.shincolle.item.LegacyEquipItem equipItem = (org.trp.shincolle.item.LegacyEquipItem) ammoStack.getItem();
            int variant = equipItem.getVariant(ammoStack);
            if (variant == 5) {
                specialEffect = org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_PULL_FIELD;
            } else if (variant == 8) {
                specialEffect = org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_CLUSTER;
                moveType = org.trp.shincolle.entity.projectile.EntityAbyssMissile.MoveType.ARC;
            }
        }

        EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this.ship.asShipEntity(), target,
                missileDamage, moveType, speed, 1.04F, 1.04F, null, life, radius);
        if (specialEffect != org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_NONE) {
            missile.setSpecialEffectType(specialEffect);
        }
        serverLevel.addFreshEntity(missile);

        this.ship.playSound(ModSounds.SHIP_FIREHEAVY.get(), this.ship.getShipSoundVolume(),
                this.ship.getRandom().nextFloat() * 0.12F + 0.83F);
        this.ship.playAttackSound();
        this.ship.setAttackTick(50);
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionHeavy);
        this.ship.applyEmotesReaction(3);
        this.ship.decrMorale(2);
        return true;
    }

    boolean performHeavyAttack(Vec3 targetPos) {
        if (isCombatSuppressed()) {
            return false;
        }
        if (!canUseHeavyAmmo()) {
            return false;
        }
        if (!(this.ship.level() instanceof ServerLevel serverLevel)) {
            return false;
        }
        if (targetPos == null) {
            return false;
        }
        if (!consumeHeavyAmmo(1)) {
            return false;
        }

        this.ship.asShipEntity().tryFlareTarget(targetPos);

        float damage = this.ship.getLegacyShipStats().getFirepower();
        if (damage <= 0.0F) {
            damage = 4.0F;
        }
        float missileDamage = damage * HEAVY_MISSILE_DAMAGE_MULTIPLIER;

        float speed = HEAVY_MISSILE_SPEED;
        int life = HEAVY_MISSILE_LIFE;
        float radius = HEAVY_MISSILE_EXPLOSION_RADIUS;
        org.trp.shincolle.entity.projectile.EntityAbyssMissile.MoveType moveType = org.trp.shincolle.entity.projectile.EntityAbyssMissile.MoveType.DIRECT;
        int specialEffect = org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_NONE;

        ItemStack ammoStack = ItemStack.EMPTY;
        int equipSlots = Math.min(
            org.trp.shincolle.inventory.ShipInventoryHandler.getEquipSlotCount(),
            this.ship.getInventory().getSlots()
        );
        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = this.ship.getInventory().getStackInSlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof org.trp.shincolle.item.LegacyEquipItem equipItem)) {
                continue;
            }
            int typeId = equipItem.getEquipTypeId(stack);
            if (typeId == EntityShipBase.EQUIP_TYPE_AMMO || typeId == EntityShipBase.EQUIP_TYPE_AMMO_2) {
                ammoStack = stack;
                break;
            }
        }

        if (!ammoStack.isEmpty()) {
            org.trp.shincolle.item.LegacyEquipItem equipItem = (org.trp.shincolle.item.LegacyEquipItem) ammoStack.getItem();
            int variant = equipItem.getVariant(ammoStack);
            if (variant == 5) {
                specialEffect = org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_PULL_FIELD;
            } else if (variant == 8) {
                specialEffect = org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_CLUSTER;
                moveType = org.trp.shincolle.entity.projectile.EntityAbyssMissile.MoveType.ARC;
            }
        }

        EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this.ship.asShipEntity(), null, targetPos,
                missileDamage, moveType, speed, 1.04F, 1.04F, null, life, radius);
        if (specialEffect != org.trp.shincolle.entity.projectile.EntityAbyssMissile.SPECIAL_EFFECT_NONE) {
            missile.setSpecialEffectType(specialEffect);
        }
        serverLevel.addFreshEntity(missile);

        this.ship.playSound(ModSounds.SHIP_FIREHEAVY.get(), this.ship.getShipSoundVolume(),
                this.ship.getRandom().nextFloat() * 0.12F + 0.83F);
        this.ship.playAttackSound();
        this.ship.setAttackTick(50);
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionHeavy);
        this.ship.applyEmotesReaction(3);
        this.ship.decrMorale(2);
        return true;
    }

    boolean consumeHeavyAmmo(int amount) {
        if (amount <= 0) {
            return true;
        }
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null || shipEntity.isHostileShipMob()) {
            return true;
        }

        if (this.ship.getAmmoHeavy() < amount) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;
            int addAmmo = 0;
            boolean supplied = false;

            if (shipEntity.consumeItemInInventory(ModItems.AMMO_HEAVY.get())) {
                addAmmo = (int) (15 * modAmmo * multiplier);
                supplied = true;
            } else if (shipEntity.consumeItemInInventory(ModItems.AMMO_HEAVY_CONTAINER.get())) {
                addAmmo = (int) (135 * modAmmo * multiplier);
                supplied = true;
            }

            if (supplied) {
                this.ship.setAmmoHeavy(this.ship.getAmmoHeavy() + addAmmo);
                if (shipEntity.getEmotesTick() <= 0) {
                    shipEntity.setEmotesTick(40);
                    int rnd = this.ship.getRandom().nextInt(3);
                    if (rnd == 0) shipEntity.applyParticleEmotion(EmotionParticleType.DROOL);
                    else if (rnd == 1) shipEntity.applyParticleEmotion(EmotionParticleType.BLINK);
                    else shipEntity.applyParticleEmotion(EmotionParticleType.SIGH);
                }
            }
        }

        if (this.ship.getAmmoHeavy() < amount) {
            if (shipEntity.getEmotesTick() <= 0) {
                shipEntity.setEmotesTick(20);
                shipEntity.applyParticleEmotion(EmotionParticleType.BLINK);
            }
            return false;
        }

        this.ship.setAmmoHeavy(this.ship.getAmmoHeavy() - amount);
        this.ship.setCombatTick(this.ship.getTickCount());
        return true;
    }

    boolean consumeLightAmmo(int amount) {
        if (amount <= 0) {
            return true;
        }
        EntityShipBase shipEntity = this.ship.asShipEntity();
        if (shipEntity == null || shipEntity.isHostileShipMob()) {
            return true;
        }

        if (this.ship.getAmmoLight() < amount) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;
            int addAmmo = 0;
            boolean supplied = false;

            if (shipEntity.consumeItemInInventory(ModItems.AMMO_LIGHT.get())) {
                addAmmo = (int) (30 * modAmmo * multiplier);
                supplied = true;
            } else if (shipEntity.consumeItemInInventory(ModItems.AMMO_LIGHT_CONTAINER.get())) {
                addAmmo = (int) (270 * modAmmo * multiplier);
                supplied = true;
            }

            if (supplied) {
                this.ship.setAmmoLight(this.ship.getAmmoLight() + addAmmo);
                if (shipEntity.getEmotesTick() <= 0) {
                    shipEntity.setEmotesTick(40);
                    int rnd = this.ship.getRandom().nextInt(3);
                    if (rnd == 0) shipEntity.applyParticleEmotion(EmotionParticleType.DROOL);
                    else if (rnd == 1) shipEntity.applyParticleEmotion(EmotionParticleType.BLINK);
                    else shipEntity.applyParticleEmotion(EmotionParticleType.SIGH);
                }
            }
        }

        if (this.ship.getAmmoLight() < amount) {
            if (shipEntity.getEmotesTick() <= 0) {
                shipEntity.setEmotesTick(20);
                shipEntity.applyParticleEmotion(EmotionParticleType.BLINK);
            }
            return false;
        }

        this.ship.setAmmoLight(this.ship.getAmmoLight() - amount);
        this.ship.setCombatTick(this.ship.getTickCount());
        return true;
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

    private boolean isLightAmmo(ItemStack stack) {
        return stack.is(ModItems.AMMO_LIGHT.get());
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

    int getMaxAircraftLight() {
        return 8 + this.ship.getLevel() / 5 + (int) (this.ship.getLevel() * this.ship.getAircraftLightLevelBonus());
    }

    int getMaxAircraftHeavy() {
        return 4 + this.ship.getLevel() / 10 + (int) (this.ship.getLevel() * this.ship.getAircraftHeavyLevelBonus());
    }

    boolean performLightAircraftAttack(Entity target) {
        if (!canUseLightAircraft()) {
            return false;
        }
        if (!consumeLightAmmo(AIRCRAFT_LIGHT_AMMO_COST)) {
            return false;
        }
        this.ship.setNumAircraftLight(Math.max(0, this.ship.getNumAircraftLight() - 1));
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionLightAircraft);
        this.ship.decrMorale(3);
        this.ship.asShipEntity().tryFlareTarget(target);
        return spawnAircraft(target, true);
    }

    boolean performLightAircraftAttackManual(Entity target) {
        if (isCombatSuppressed()) return false;
        if (!this.ship.supportsAircraftCombat()) return false;
        if (!this.ship.hasAirLight()) return false;
        if (this.ship.getAmmoLight() < AIRCRAFT_LIGHT_AMMO_COST) return false;
        if (!consumeLightAmmo(AIRCRAFT_LIGHT_AMMO_COST)) return false;
        this.ship.setNumAircraftLight(Math.max(0, this.ship.getNumAircraftLight() - 1));
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionLightAircraft);
        this.ship.decrMorale(3);
        boolean spawned = spawnAircraft(target, true);
        
        return spawned;
    }

    boolean performHeavyAircraftAttack(Entity target) {
        if (!canUseHeavyAircraft()) {
            return false;
        }
        if (!consumeHeavyAmmo(AIRCRAFT_HEAVY_AMMO_COST)) {
            return false;
        }
        this.ship.setNumAircraftHeavy(Math.max(0, this.ship.getNumAircraftHeavy() - 1));
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionHeavyAircraft);
        this.ship.decrMorale(4);
        this.ship.asShipEntity().tryFlareTarget(target);
        return spawnAircraft(target, false);
    }

    boolean performHeavyAircraftAttackManual(Entity target) {
        if (isCombatSuppressed()) return false;
        if (!this.ship.supportsAircraftCombat()) return false;
        if (!this.ship.hasAirHeavy()) return false;
        if (this.ship.getAmmoHeavy() < AIRCRAFT_HEAVY_AMMO_COST) return false;
        if (!consumeHeavyAmmo(AIRCRAFT_HEAVY_AMMO_COST)) return false;
        this.ship.setNumAircraftHeavy(Math.max(0, this.ship.getNumAircraftHeavy() - 1));
        this.ship.setFuel(this.ship.getFuel() - org.trp.shincolle.Config.fuelConsumeActionHeavyAircraft);
        this.ship.decrMorale(4);
        boolean spawned = spawnAircraft(target, false);
        
        return spawned;
    }

    private boolean spawnAircraft(Entity target, boolean lightAircraft) {
        EntityType<? extends net.minecraft.world.entity.TamableAnimal> type = this.ship.getAttackAircraftType(lightAircraft);
        if (type == null) return false;
        if (!(this.ship.level() instanceof ServerLevel serverLevel)) return false;

        Entity spawned = type.create(serverLevel);
        if (spawned == null) return false;
        if (!(spawned instanceof EntityAircraftBase aircraft)) return false;

        double launchY = this.ship.getY() + this.ship.getAircraftLaunchHeight();
        aircraft.moveTo(this.ship.getX(), launchY, this.ship.getZ(), this.ship.getYRot(), this.ship.getXRot());
        aircraft.initCarrierMission(this.ship.asShipEntity(), target, lightAircraft);
        serverLevel.addFreshEntity(aircraft);

        this.ship.playAttackSound();
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
