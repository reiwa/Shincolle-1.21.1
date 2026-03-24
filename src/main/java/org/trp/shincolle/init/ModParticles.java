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

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM_TARGET =
            PARTICLES.register("particleteam_target", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_TEAM_TARGET_ENTITY =
            PARTICLES.register("particleteam_target_entity", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_EMOTION =
            PARTICLES.register("particleemotion", () -> new SimpleParticleType(false));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLE_HEAL_SPARKLE =
            PARTICLES.register("particleheal_sparkle", () -> new SimpleParticleType(false));

    private ModParticles() {
    }
}
