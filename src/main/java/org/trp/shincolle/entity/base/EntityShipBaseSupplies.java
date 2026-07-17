package org.trp.shincolle.entity.base;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.item.CombatRationItem;

class EntityShipBaseSupplies {

    private static final int AUTO_RATION_INTERVAL_TICKS = 128;
    private static final int AUTO_RATION_MAX_FUEL = 10000;

    private static final float AUTO_HEAL_THRESHOLD_RATIO = 0.9F;
    private static final float AUTO_HEAL_FAST_RATIO = 0.08F;
    private static final float AUTO_HEAL_FAST_FLAT = 15.0F;
    private static final float AUTO_HEAL_SLOW_RATIO = 0.03F;
    private static final float AUTO_HEAL_SLOW_FLAT = 1.0F;

    private final EntityShipBase ship;
    private int feedSoundCooldown = 0;

    EntityShipBaseSupplies(EntityShipBase ship) {
        this.ship = ship;
    }

    boolean checkAndPlayFeedSound() {
        if (this.feedSoundCooldown <= 0) {
            this.ship.playFeedSound();
            this.feedSoundCooldown = 30;
            return true;
        }
        return false;
    }

    void tickSupplies() {
        if (this.feedSoundCooldown > 0) {
            this.feedSoundCooldown--;
        }
    }

    void tickAutoRation() {
        if ((this.ship.tickCount % AUTO_RATION_INTERVAL_TICKS) != 0) {
            return;
        }

        int threshold = Mth.clamp(
            this.ship.getStateComponent().getRationMorale(),
            1,
            4
        );
        if (getMoraleLevelLegacy(this.ship.getMorale()) < threshold) {
            return;
        }

        if (
            this.ship.getFuel() >= AUTO_RATION_MAX_FUEL &&
            this.ship.getHealth() >= this.ship.getMaxHealth()
        ) {
            return;
        }

        consumeOneCombatRation();
    }

    private int getMoraleLevelLegacy(int morale) {
        if (morale > 5100) {
            return 0;
        }
        if (morale > 3900) {
            return 1;
        }
        if (morale > 2100) {
            return 2;
        }
        if (morale > 900) {
            return 3;
        }
        return 4;
    }

    private boolean consumeOneCombatRation() {
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

            if (
                !(stack.getItem() instanceof CombatRationItem combatRationItem)
            ) {
                continue;
            }

            int variant = combatRationItem.getVariant(stack);
            applyCombatRationEffect(variant);
            stack.shrink(1);
            if (stack.isEmpty()) {
                this.ship.inventory.setStackInSlot(i, ItemStack.EMPTY);
            } else {
                this.ship.inventory.setStackInSlot(i, stack);
            }

            return true;
        }

        return false;
    }

    boolean consumeCombatRationInHand(ItemStack stack, Player player) {
        if (!(stack.getItem() instanceof CombatRationItem combatRationItem)) {
            return false;
        }

        applyCombatRationEffect(combatRationItem.getVariant(stack));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return true;
    }

    private void applyCombatRationEffect(int variant) {
        int fuelGain = CombatRationItem.rollFuelGain(this.ship.getRandom(), variant);
        this.ship.setFuel(Math.min(AUTO_RATION_MAX_FUEL, this.ship.getFuel() + fuelGain));
        this.ship.addMorale(CombatRationItem.getMoraleValue(variant));

        if (this.ship.getHealth() < this.ship.getMaxHealth()) {
            this.ship.heal(this.ship.getMaxHealth() * 0.05F + 1.0F);
        }

        if (this.feedSoundCooldown <= 0) {
            this.ship.playFeedSound();
            this.feedSoundCooldown = 30;
        }

        this.ship.applyParticleEmotion(
            switch (this.ship.getRandom().nextInt(3)) {
                case 1 -> EmotionParticleType.DROOL;
                case 2 -> EmotionParticleType.SIGH;
                default -> EmotionParticleType.HEART;
            }
        );
        this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
        this.ship.resetInteractionEmotionState();
    }

    boolean consumeBucketRepairInHand(ItemStack stack, Player player) {
        if (!stack.is(ModItems.BUCKET_REPAIR.get())) {
            return false;
        }

        if (this.ship.getHealth() < this.ship.getMaxHealth()) {
            if (this.ship.supportsAircraftCombat()) {
                this.ship.heal(this.ship.getMaxHealth() * 0.05F + 10.0F);
            } else {
                this.ship.heal(this.ship.getMaxHealth() * 0.1F + 5.0F);
            }

            if (this.ship.supportsAircraftCombat()) {
                this.ship.setNumAircraftLight(this.ship.getNumAircraftLight() + 1);
                this.ship.setNumAircraftHeavy(this.ship.getNumAircraftHeavy() + 1);
            }

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            this.ship.applyParticleEmotion(EmotionParticleType.HEART);
            this.ship.playFeedSound();
            this.ship.focusOnPlayer(player);
            return true;
        }
        return false;
    }

    boolean consumeToyAirplaneInHand(ItemStack stack, Player player) {
        if (!stack.is(ModItems.TOY_AIRPLANE.get())) {
            return false;
        }

        if (this.ship.supportsAircraftCombat()) {
            int maxLight = this.ship.getMaxAircraftLight();
            int maxHeavy = this.ship.getMaxAircraftHeavy();
            int addLight = this.ship.getRandom().nextInt(3) + 1;
            int addHeavy = this.ship.getRandom().nextInt(3) + 1;
            this.ship.setNumAircraftLight(Math.min(maxLight, this.ship.getNumAircraftLight() + addLight));
            this.ship.setNumAircraftHeavy(Math.min(maxHeavy, this.ship.getNumAircraftHeavy() + addHeavy));
        }

        this.ship.addMorale(200);
        this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
        if (this.ship.getEmotesTick() <= 0) {
            this.ship.setEmotesTick(40);
            this.ship.applyParticleEmotion(EmotionParticleType.HAPPY_BOB);
        }
        this.ship.playFeedSound();

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        this.ship.focusOnPlayer(player);
        return true;
    }

    void tickFuelDecay() {
        if (this.ship.isHostileShipMob()) {
            return;
        }
        if (this.ship.tickCount % org.trp.shincolle.Config.fuelDecayInterval != 0) {
            return;
        }
        if (this.ship.getFuel() <= 0) {
            return;
        }

        int consume = this.ship.getStateComponent().getGrudgeConsumption();

        double dist = Math.sqrt(this.ship.distanceToSqr(this.ship.xo, this.ship.yo, this.ship.zo));
        consume += (int) (dist * org.trp.shincolle.Config.fuelMoveDecayFactor);

        this.ship.setFuel(this.ship.getFuel() - consume);
    }

    void tickAutoRecovery() {
        if (this.ship.isHostileShipMob()) {
            return;
        }

        if (
            (this.ship.tickCount & 0x1F) == 0 &&
            this.ship.getHealth() < this.ship.getMaxHealth() * AUTO_HEAL_THRESHOLD_RATIO
        ) {
            if (this.ship.consumeItemInInventory(ModItems.BUCKET_REPAIR.get())) {
                this.ship.heal(
                    this.ship.getMaxHealth() * AUTO_HEAL_FAST_RATIO +
                        AUTO_HEAL_FAST_FLAT
                );
                if (this.ship.supportsAircraftCombat()) {
                    this.ship.setNumAircraftLight(this.ship.getNumAircraftLight() + 1);
                    this.ship.setNumAircraftHeavy(this.ship.getNumAircraftHeavy() + 1);
                }
                this.ship.applyParticleEmotion(EmotionParticleType.HEART);
            }
        }

        if (
            (this.ship.tickCount & 0xFF) == 0 &&
            this.ship.getHealth() < this.ship.getMaxHealth()
        ) {
            this.ship.heal(
                this.ship.getMaxHealth() * AUTO_HEAL_SLOW_RATIO + AUTO_HEAL_SLOW_FLAT
            );
        }
    }

    void tickAutoSupplies() {
        if (this.ship.level().isClientSide || this.ship.isHostileShipMob()) {
            return;
        }

        int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;

        if (this.ship.getFuel() <= 0) {
            float modFuel = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_FUEL_CONSUMPTION);
            if (this.ship.consumeItemInInventory(ModItems.GRUDGE.get())) {
                this.ship.setFuel((int) (300 * modFuel * multiplier));
                this.applyAutoSupplyEffects();
            } else if (
                this.ship.consumeItemInInventory(ModItems.GRUDGE_BLOCK.get())
            ) {
                this.ship.setFuel((int) (2700 * modFuel * multiplier));
                this.applyAutoSupplyEffects();
            }
        }

        if (this.ship.getAmmoLight() <= 0) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            if (this.ship.consumeItemInInventory(ModItems.AMMO_LIGHT.get())) {
                this.ship.setAmmoLight((int) (30 * modAmmo * multiplier));
                this.applyAutoSupplyEffects();
            } else if (
                this.ship.consumeItemInInventory(ModItems.AMMO_LIGHT_CONTAINER.get())
            ) {
                this.ship.setAmmoLight((int) (270 * modAmmo * multiplier));
                this.applyAutoSupplyEffects();
            }
        }

        if (this.ship.getAmmoHeavy() <= 0) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            if (this.ship.consumeItemInInventory(ModItems.AMMO_HEAVY.get())) {
                this.ship.setAmmoHeavy((int) (15 * modAmmo * multiplier));
                this.applyAutoSupplyEffects();
            } else if (
                this.ship.consumeItemInInventory(ModItems.AMMO_HEAVY_CONTAINER.get())
            ) {
                this.ship.setAmmoHeavy((int) (135 * modAmmo * multiplier));
                this.applyAutoSupplyEffects();
            }
        }
    }

    private void applyAutoSupplyEffects() {
        if (this.ship.getEmotesTick() <= 0) {
            this.ship.setEmotesTick(40);
            int rnd = this.ship.getRandom().nextInt(3);
            if (rnd == 0) this.ship.applyParticleEmotion(EmotionParticleType.DROOL);
            else if (rnd == 1) this.ship.applyParticleEmotion(
                EmotionParticleType.BLINK
            );
            else this.ship.applyParticleEmotion(EmotionParticleType.SIGH);
        }
    }
}
