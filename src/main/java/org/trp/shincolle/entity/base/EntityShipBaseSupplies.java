package org.trp.shincolle.entity.base;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.item.CombatRationItem;
import org.trp.shincolle.menu.ShipContainerMenu;

class EntityShipBaseSupplies {

    private static final int AUTO_RATION_INTERVAL_TICKS = 128;
    private static final int AUTO_RATION_MAX_FUEL = 100;

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
            this.ship.getStateMinor(ShipContainerMenu.STATE_MINOR_RATION_MORALE),
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
            this.ship.setNumAircraftLight(this.ship.getNumAircraftLight() + 2);
            this.ship.setNumAircraftHeavy(this.ship.getNumAircraftHeavy() + 2);
        }

        this.ship.addMorale(200);
        this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
        this.ship.applyParticleEmotion(EmotionParticleType.HAPPY_BOB);
        this.ship.playFeedSound();

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        this.ship.focusOnPlayer(player);
        return true;
    }

    void tickAutoSupplies() {
        if (this.ship.level().isClientSide || this.ship.isHostileShipMob()) {
            return;
        }

        int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;

        if (this.ship.getFuel() <= 0) {
            float modFuel = this.ship.getLegacyShipStats().getBuffedAttr(17);
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
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(18);
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
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(18);
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
