package org.trp.shincolle.entity.base;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import org.trp.shincolle.init.ModSounds;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class EntityShipBaseAudioHelper {

    static final float SHIP_SOUND_VOLUME = 0.6F;
    private static final int AMBIENT_SOUND_MIN_INTERVAL_TICKS = 80;
    private static final int AMBIENT_SOUND_MAX_PER_TICK = 3;
    private static final ConcurrentMap<Long, Integer> AMBIENT_SOUNDS_PER_TICK =
        new ConcurrentHashMap<>();

    private final EntityShipBase ship;

    EntityShipBaseAudioHelper(EntityShipBase ship) {
        this.ship = ship;
    }

    private boolean tryAcquireAmbientSoundSlot() {
        if (this.ship.level() == null) {
            return false;
        }

        long gameTime = this.ship.level().getGameTime();
        int count = AMBIENT_SOUNDS_PER_TICK.merge(gameTime, 1, Integer::sum);

        if (count == 1) {
            AMBIENT_SOUNDS_PER_TICK.keySet().removeIf(
                tick -> tick < gameTime - 1L
            );
        }

        if (count > AMBIENT_SOUND_MAX_PER_TICK) {
            AMBIENT_SOUNDS_PER_TICK.computeIfPresent(gameTime, (tick, value) ->
                Math.max(0, value - 1)
            );
            return false;
        }

        return true;
    }

    void playAmbientSound() {
        if (this.ship.isNoFuel() || this.ship.getRandom().nextInt(10) > 3) {
            return;
        }
        if (!tryAcquireAmbientSoundSlot()) {
            return;
        }
        SoundEvent sound;
        if (this.ship.getStateComponent().isStateMarried() && this.ship.getRandom().nextInt(5) == 0) {
            int shipClassId = this.ship.getStateComponent().getShipClassId();
            net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                org.trp.shincolle.Shincolle.MODID, "ship-marry-" + shipClassId
            );
            sound = SoundEvent.createVariableRangeEvent(id);
        } else {
            sound = this.ship.getAmbientSound();
        }
        if (sound != null) {
            this.ship.playSound(
                sound,
                this.ship.getSoundVolume(),
                this.ship.getShipSoundPitch()
            );
        }
    }

    void playHurtSound(DamageSource source, Runnable superCall) {
        if (this.ship.hurtSoundCooldown <= 0) {
            this.ship.hurtSoundCooldown = 20 + this.ship.getRandom().nextInt(30);
            superCall.run();
        }
    }

    void tickTimeKeepingSound() {
        if (
            !this.ship.getStateComponent().isStateTimekeep() ||
            !this.ship.isAlive() ||
            this.ship.isInDeadPose()
        ) {
            return;
        }
        long worldTime = this.ship.level().getDayTime();
        if (worldTime % EntityShipBase.TIMEKEEP_INTERVAL_TICKS != 0L) {
            return;
        }

        int hour = (int) ((worldTime / EntityShipBase.TIMEKEEP_INTERVAL_TICKS) % 24L);
        SoundEvent timeSound = ModSounds.getShipTimeSound(hour);
        if (timeSound != null) {
            this.ship.playSound(timeSound, this.ship.getSoundVolume(), 1.0F);
        }
    }

    void playAttackSound() {
        if (this.ship.level().isClientSide) {
            return;
        }
        if (this.ship.getRandom().nextInt(10) > 7) {
            int shipClassId = this.ship.getStateComponent().getShipClassId();
            net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                org.trp.shincolle.Shincolle.MODID, "ship-hit-" + shipClassId
            );
            SoundEvent voiceSound = SoundEvent.createVariableRangeEvent(id);
            this.ship.playSound(
                voiceSound,
                this.ship.getSoundVolume(),
                this.ship.getShipSoundPitch()
            );
        }
    }

    void playItemPickupSound() {
        if (this.ship.level().isClientSide) {
            return;
        }
        if (this.ship.getStateComponent().getAudioTimer() <= 0 && this.ship.getRandom().nextInt(2) == 0) {
            this.ship.getStateComponent().setAudioTimer(40 + this.ship.getRandom().nextInt(10));
            int shipClassId = this.ship.getStateComponent().getShipClassId();
            net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                org.trp.shincolle.Shincolle.MODID, "ship-item-" + shipClassId
            );
            SoundEvent voiceSound = SoundEvent.createVariableRangeEvent(id);
            this.ship.playSound(
                voiceSound,
                this.ship.getSoundVolume(),
                this.ship.getShipSoundPitch()
            );
        }
    }

    void playKnockbackSound() {
        if (this.ship.level().isClientSide) {
            return;
        }
        if (this.ship.getStateComponent().getAudioTimer() <= 0) {
            this.ship.getStateComponent().setAudioTimer(40 + this.ship.getRandom().nextInt(10));
            int shipClassId = this.ship.getStateComponent().getShipClassId();
            net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                org.trp.shincolle.Shincolle.MODID, "ship-knockback-" + shipClassId
            );
            SoundEvent voiceSound = SoundEvent.createVariableRangeEvent(id);
            this.ship.playSound(
                voiceSound,
                this.ship.getSoundVolume(),
                this.ship.getShipSoundPitch()
            );
        }
    }

    void playFeedSound() {
        if (this.ship.level().isClientSide) {
            return;
        }
        int shipClassId = this.ship.getStateComponent().getShipClassId();
        net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
            org.trp.shincolle.Shincolle.MODID, "ship-feed-" + shipClassId
        );
        SoundEvent voiceSound = SoundEvent.createVariableRangeEvent(id);
        this.ship.playSound(
            voiceSound,
            this.ship.getSoundVolume(),
            this.ship.getShipSoundPitch()
        );
    }
}
