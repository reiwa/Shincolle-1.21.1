package org.trp.shincolle.entity.base;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModSounds;
import org.trp.shincolle.item.CombatRationItem;

import java.util.Random;

public class EntityShipBaseInteractionHelper {

    private final EntityShipBase ship;

    public EntityShipBaseInteractionHelper(EntityShipBase ship) {
        this.ship = ship;
    }

    public InteractionResult handleInteraction(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (
            stack.getItem() instanceof
                org.trp.shincolle.item.PointerItem ptr &&
            ptr.isPetting(stack)
        ) {
            return InteractionResult.sidedSuccess(this.ship.level().isClientSide);
        }

        if (
            stack.is(ModItems.KAITAI_HAMMER.get()) &&
            player.isShiftKeyDown()
        ) {
            return this.ship.useKaitaiHammer(player, stack)
                ? InteractionResult.sidedSuccess(this.ship.level().isClientSide)
                : InteractionResult.PASS;
        }

        if (!this.ship.isTame()) {
            return InteractionResult.PASS;
        }

        if (!this.ship.isOwnedBy(player)) {
            return InteractionResult.PASS;
        }

        if (
            stack.is(ModItems.OWNER_PAPER.get()) && player.isShiftKeyDown()
        ) {
            return this.useOwnerPaper(player, stack)
                ? InteractionResult.sidedSuccess(this.ship.level().isClientSide)
                : InteractionResult.PASS;
        }

        if (stack.is(ModItems.TRAINING_BOOK.get())) {
            if (player.level().isClientSide) {
                return InteractionResult.sidedSuccess(this.ship.level().isClientSide);
            }

            int minLevelGain = org.trp.shincolle.Config.trainingBookLevelMin;
            int maxLevelGain = Math.max(minLevelGain, org.trp.shincolle.Config.trainingBookLevelMax);
            int levelGain = minLevelGain;
            if (maxLevelGain > minLevelGain) {
                levelGain += player.getRandom().nextInt(maxLevelGain - minLevelGain + 1);
            }

            if (!this.ship.addTrainingBookLevel(levelGain)) {
                return InteractionResult.FAIL;
            }

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            this.ship.focusOnPlayer(player);
            return InteractionResult.sidedSuccess(this.ship.level().isClientSide);
        }

        if (stack.is(ModItems.AMMO_LIGHT.get())) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;
            int gain = (int) (30 * modAmmo * multiplier);
            this.ship.setAmmoLight(this.ship.getAmmoLight() + gain);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.ship.suppliesHelper.checkAndPlayFeedSound();
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            this.ship.resetInteractionEmotionState();
            this.ship.focusOnPlayer(player);
            return InteractionResult.sidedSuccess(this.ship.level().isClientSide);
        }

        if (stack.is(ModItems.AMMO_LIGHT_CONTAINER.get())) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;
            int gain = (int) (270 * modAmmo * multiplier);
            this.ship.setAmmoLight(this.ship.getAmmoLight() + gain);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.ship.suppliesHelper.checkAndPlayFeedSound();
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            this.ship.resetInteractionEmotionState();
            this.ship.focusOnPlayer(player);
            return InteractionResult.sidedSuccess(this.ship.level().isClientSide);
        }

        if (stack.is(ModItems.AMMO_HEAVY.get())) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;
            int gain = (int) (15 * modAmmo * multiplier);
            this.ship.setAmmoHeavy(this.ship.getAmmoHeavy() + gain);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.ship.suppliesHelper.checkAndPlayFeedSound();
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            this.ship.resetInteractionEmotionState();
            this.ship.focusOnPlayer(player);
            return InteractionResult.sidedSuccess(this.ship.level().isClientSide);
        }

        if (stack.is(ModItems.AMMO_HEAVY_CONTAINER.get())) {
            float modAmmo = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_AMMO_CONSUMPTION);
            int multiplier = org.trp.shincolle.Config.consumptionLevel == 0 ? 10 : 1;
            int gain = (int) (135 * modAmmo * multiplier);
            this.ship.setAmmoHeavy(this.ship.getAmmoHeavy() + gain);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.ship.suppliesHelper.checkAndPlayFeedSound();
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            this.ship.resetInteractionEmotionState();
            this.ship.focusOnPlayer(player);
            return InteractionResult.sidedSuccess(this.ship.level().isClientSide);
        }

        if (
            stack.is(ModItems.MARRIAGE_RING.get()) && !this.ship.isStateMarried()
        ) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.ship.setStateMarried(true);
            this.ship.setMorale(16000);
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            this.ship.applyParticleEmotion(EmotionParticleType.HEART);
            org.trp.shincolle.attachment.AdmiralData data = player.getData(org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA);
            data.setMarriageNum(data.getMarriageNum() + 1);
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, new org.trp.shincolle.network.S2CAdmiralDataSyncPayload(data.serializeNBT()));
            }
            if (this.ship.level() instanceof ServerLevel serverLevel) {
                for (int i = 0; i < 7; ++i) {
                    double px =
                        this.ship.getX() +
                        (this.ship.getRandom().nextFloat() * 2.0F - 1.0F);
                    double py =
                        this.ship.getY() +
                        0.5D +
                        (this.ship.getRandom().nextFloat() * 2.0F);
                    double pz =
                        this.ship.getZ() +
                        (this.ship.getRandom().nextFloat() * 2.0F - 1.0F);
                    double d0 = this.ship.getRandom().nextGaussian() * 0.02D;
                    double d1 = this.ship.getRandom().nextGaussian() * 0.02D;
                    double d2 = this.ship.getRandom().nextGaussian() * 0.02D;
                    serverLevel.sendParticles(
                        ParticleTypes.HEART,
                        px,
                        py,
                        pz,
                        0,
                        d0,
                        d1,
                        d2,
                        1.0D
                    );
                }
            }
            this.ship.playSound(
                ModSounds.SHIP_MARRY.get(),
                this.ship.getSoundVolume(),
                this.ship.getShipSoundPitch()
            );

            Random javaRand = new Random();
            for (int i = 0; i < 3; ++i) {
                this.ship.getLegacyShipStats().addBonusRandom(javaRand);
            }
            this.ship.recalculateLegacyShipStats();

            this.ship.resetInteractionEmotionState();
            this.ship.focusOnPlayer(player);
            return InteractionResult.sidedSuccess(
                this.ship.level().isClientSide
            );
        }

        if (stack.getItem() instanceof CombatRationItem) {
            if (
                this.ship.suppliesHelper.consumeCombatRationInHand(stack, player)
            ) {
                this.ship.focusOnPlayer(player);
                return InteractionResult.sidedSuccess(
                    this.ship.level().isClientSide
                );
            }
        }

        if (stack.is(ModItems.MODERN_KIT.get())) {
            if (this.useModernKitInHand(stack, player)) {
                return InteractionResult.sidedSuccess(
                    this.ship.level().isClientSide
                );
            }
        }

        if (stack.is(ModItems.BUCKET_REPAIR.get())) {
            if (
                this.ship.suppliesHelper.consumeBucketRepairInHand(stack, player)
            ) {
                return InteractionResult.sidedSuccess(
                    this.ship.level().isClientSide
                );
            }
        }

        if (stack.is(ModItems.TOY_AIRPLANE.get())) {
            if (
                this.ship.suppliesHelper.consumeToyAirplaneInHand(stack, player)
            ) {
                return InteractionResult.sidedSuccess(
                    this.ship.level().isClientSide
                );
            }
        }

        if (stack.is(ModItems.GRUDGE.get())) {
            int gain = 300 + this.ship.getRandom().nextInt(500);
            this.ship.setFuel(this.ship.getFuel() + gain);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.ship.suppliesHelper.checkAndPlayFeedSound();
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            this.ship.resetInteractionEmotionState();
            this.ship.focusOnPlayer(player);
            return InteractionResult.sidedSuccess(
                this.ship.level().isClientSide
            );
        }

        if (stack.has(DataComponents.FOOD)) {
            FoodProperties food = stack.getFoodProperties(player);
            if (food != null && food.nutrition() > 0) {
                float fv = food.nutrition();
                float sv = food.saturation();
                if (fv < 1.0F) fv = 1.0F;
                int grudgeValue = (int) ((fv +
                        this.ship.getRandom().nextInt((int) fv + 5)) *
                    sv *
                    20.0F);
                float modFuel = this.ship.getLegacyShipStats().getBuffedAttr(LegacyShipStats.STAT_FUEL_CONSUMPTION);
                int gain = (int) (grudgeValue * modFuel);
                this.ship.setFuel(this.ship.getFuel() + gain);
                this.ship.addMorale(grudgeValue);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                this.ship.suppliesHelper.checkAndPlayFeedSound();
                this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
                this.ship.resetInteractionEmotionState();
                this.ship.focusOnPlayer(player);
                return InteractionResult.sidedSuccess(
                    this.ship.level().isClientSide
                );
            }
        }

        return InteractionResult.PASS;
    }

    private boolean useModernKitInHand(ItemStack stack, Player player) {
        Random javaRand = new Random();
        if (!this.ship.getLegacyShipStats().addBonusRandom(javaRand)) {
            return false;
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
        this.ship.resetInteractionEmotionState();
        this.ship.recalculateLegacyShipStats();
        this.ship.playSound(
            ModSounds.SHIP_MARRY.get(),
            this.ship.getSoundVolume(),
            this.ship.getShipSoundPitch()
        );
        this.ship.focusOnPlayer(player);
        return true;
    }

    private boolean useOwnerPaper(Player player, ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return false;
        }
        CompoundTag tag = customData.copyTag();
        if (!tag.hasUUID("SignIDA") || !tag.hasUUID("SignIDB")) {
            return false;
        }
        java.util.UUID ida = tag.getUUID("SignIDA");
        java.util.UUID idb = tag.getUUID("SignIDB");
        java.util.UUID currentOwnerUUID = this.ship.getOwnerUUID();
        if (currentOwnerUUID == null) {
            return false;
        }
        java.util.UUID targetUUID = currentOwnerUUID.equals(ida) ? idb : ida;
        Player targetPlayer = this.ship.level().getPlayerByUUID(targetUUID);
        if (targetPlayer != null) {
            this.ship.setOwnerUUID(targetUUID);
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_CRY);
            this.ship.playSound(
                ModSounds.SHIP_MARRY.get(),
                this.ship.getSoundVolume(),
                1.0F
            );
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            return true;
        }
        return false;
    }
}
