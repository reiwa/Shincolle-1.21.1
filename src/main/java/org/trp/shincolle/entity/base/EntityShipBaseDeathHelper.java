package org.trp.shincolle.entity.base;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.GameRules;
import org.trp.shincolle.entity.EntityShipGrudge;

import java.util.UUID;

class EntityShipBaseDeathHelper {

    private final EntityShipBase ship;

    EntityShipBaseDeathHelper(EntityShipBase ship) {
        this.ship = ship;
    }

    void tickDeath() {
        this.ship.updateMountSummon();
        this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HUNGRY);
        this.ship.setFaceHungry();
        this.ship.shipDeathTicks++;
        if (this.ship.isInWaterOrBubble() || this.ship.isInLava()) {
            this.ship.movementHelper.applyDeadFloatStabilization();
        }
        if (
            !this.ship.level().isClientSide &&
            this.ship.shipDeathTicks == EntityShipBase.SHIP_DEATH_MAX_TICKS
        ) {
            spawnShipGrudge();
        }
        if (this.ship.shipDeathTicks >= EntityShipBase.SHIP_DEATH_MAX_TICKS) {
            this.ship.discard();
        }
        this.ship.deathTime = 0;
    }

    void die(DamageSource cause, Runnable superCall) {
        if (!this.ship.level().isClientSide) {
            if (this.ship.isHostileShipMob()) {
                this.ship.applyEmotesAOE(48.0, 6, true);
            } else {
                this.ship.applyEmotesAOE(16.0, 6, false);
            }
        }

        if (
            !this.ship.level().isClientSide &&
            this.ship.level()
                .getGameRules()
                .getBoolean(GameRules.RULE_SHOWDEATHMESSAGES)
        ) {
            Component customMessage = Component.translatable(
                "chat.shincolle.entity_fainted",
                this.ship.getDisplayName()
            );

            if (
                this.ship instanceof TamableAnimal tamed &&
                tamed.getOwner() instanceof ServerPlayer owner
            ) {
                owner.sendSystemMessage(customMessage);
            } else if (this.ship.hasCustomName()) {
                this.ship.level()
                    .getServer()
                    .getPlayerList()
                    .broadcastSystemMessage(customMessage, false);
            }
        }

        Component backupName = this.ship.getCustomName();
        this.ship.setCustomName(null);

        UUID backupOwner = null;
        if (this.ship instanceof TamableAnimal tamed) {
            backupOwner = tamed.getOwnerUUID();
            tamed.setOwnerUUID(null);
        }

        superCall.run();

        this.ship.setCustomName(backupName);
        if (this.ship instanceof TamableAnimal tamed && backupOwner != null) {
            tamed.setOwnerUUID(backupOwner);
        }
    }

    private void spawnShipGrudge() {
        ItemStack spawnEgg = createShipSpawnEggStack();
        EntityShipGrudge grudge = new EntityShipGrudge(
            this.ship.level(),
            this.ship.getX(),
            this.ship.getY() + 0.5D,
            this.ship.getZ(),
            spawnEgg,
            this.ship.getOwnerUUID()
        );
        this.ship.level().addFreshEntity(grudge);
    }

    ItemStack createShipSpawnEggStack() {
        ItemStack egg = new ItemStack(this.ship.getShipSpawnEggItem());
        CompoundTag shipTag = new CompoundTag();
        this.ship.addAdditionalSaveData(shipTag);
        shipTag.putString(
            "id",
            BuiltInRegistries.ENTITY_TYPE.getKey(this.ship.getType()).toString()
        );
        shipTag.putBoolean(EntityShipBase.TAG_SPAWN_EGG, true);
        if (this.ship.isHostileShipMob()) {
            shipTag.putBoolean(EntityShipBase.TAG_SPAWN_EGG_NO_EXP, true);
        }
        shipTag.putInt("Fuel", 0);
        shipTag.putFloat("Health", this.ship.getMaxHealth());
        shipTag.putShort("DeathTime", (short) 0);
        shipTag.putShort("HurtTime", (short) 0);
        egg.set(DataComponents.ENTITY_DATA, CustomData.of(shipTag));
        egg.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
        return egg;
    }

    void resetDeathStateForSpawnEgg() {
        this.ship.setHealth(this.ship.getMaxHealth());
        this.ship.deathTime = 0;
        this.ship.shipDeathTicks = 0;
        this.ship.setFuel(0);
    }
}
