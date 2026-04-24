package org.trp.shincolle.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, Shincolle.MODID);

    private static final Supplier<SoundEvent>[] SHIP_TIME_SOUNDS = createShipTimeSounds();

    public static final Supplier<SoundEvent> SHIP_IDLE = register("ship-idle");
    public static final Supplier<SoundEvent> SHIP_HURT = register("ship-hurt");
    public static final Supplier<SoundEvent> SHIP_DEATH = register("ship-death");
    public static final Supplier<SoundEvent> SHIP_FIRELIGHT = register("ship-firelight");
    public static final Supplier<SoundEvent> SHIP_EXPLODE = register("ship-explode");
    public static final Supplier<SoundEvent> SHIP_FIREHEAVY = register("ship-fireheavy");
    public static final Supplier<SoundEvent> SHIP_HIT = register("ship-hit");
    public static final Supplier<SoundEvent> SHIP_LEVELUP = register("ship-levelup");
    public static final Supplier<SoundEvent> SHIP_MACHINEGUN = register("ship-machinegun");
    public static final Supplier<SoundEvent> SHIP_AIRCRAFT = register("ship-aircraft");
    public static final Supplier<SoundEvent> SHIP_MARRY = register("ship-marry");
    public static final Supplier<SoundEvent> SHIP_FEED = register("ship-feed");

    private static Supplier<SoundEvent>[] createShipTimeSounds() {
        @SuppressWarnings("unchecked")
        Supplier<SoundEvent>[] sounds = (Supplier<SoundEvent>[]) new Supplier[24];
        for (int i = 0; i < sounds.length; i++) {
            sounds[i] = register("ship-time" + i);
        }
        return sounds;
    }

    public static SoundEvent getShipTimeSound(int hour) {
        int idx = Math.floorMod(hour, SHIP_TIME_SOUNDS.length);
        return SHIP_TIME_SOUNDS[idx].get();
    }

    private static Supplier<SoundEvent> register(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
