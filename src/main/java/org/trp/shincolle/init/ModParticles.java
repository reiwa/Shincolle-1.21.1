package org.trp.shincolle.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Shincolle.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM =
            PARTICLES.register("particleteam", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM_SELECTED =
            PARTICLES.register("particleteam_selected", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM_SELECTED_RED =
            PARTICLES.register("particleteam_selected_red", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM_SELECTED_YELLOW =
            PARTICLES.register("particleteam_selected_yellow", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM_TARGET =
            PARTICLES.register("particleteam_target", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM_TARGET_ENTITY =
            PARTICLES.register("particleteam_target_entity", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_EMOTION =
            PARTICLES.register("particleemotion", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_HEAL_SPARKLE =
            PARTICLES.register("particleheal_sparkle", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEXTS =
            PARTICLES.register("particletexts", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_LIGHTNING =
            PARTICLES.register("particle_lightning", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_SPRAY_RED =
            PARTICLES.register("particle_spray_red", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_SPRAY =
            PARTICLES.register("particle_spray", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_SPRAY_CYAN =
            PARTICLES.register("particle_spray_cyan", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_GODDESS =
            PARTICLES.register("particle_goddess", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_WAYPOINT =
            PARTICLES.register("particle_waypoint", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_WAYPOINT_LINE =
            PARTICLES.register("particle_waypoint_line", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_WAYPOINT_LINE_PURPLE =
            PARTICLES.register("particle_waypoint_line_purple", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_WAYPOINT_LINE_RED =
            PARTICLES.register("particle_waypoint_line_red", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_CRANING =
            PARTICLES.register("particle_craning", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_SPARKLE =
            PARTICLES.register("particle_sparkle", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_CHI =
            PARTICLES.register("particle_chi", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_91TYPE =
            PARTICLES.register("particle_91type", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_CUBE =
            PARTICLES.register("particle_cube", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_BEAM =
            PARTICLES.register("particle_beam", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_SPHERE_LIGHT =
            PARTICLES.register("particle_sphere_light", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_SHINE =
            PARTICLES.register("particle_shine", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_FOG =
            PARTICLES.register("particle_fog", () -> new SimpleParticleType(false));


    private ModParticles() {
    }
}
