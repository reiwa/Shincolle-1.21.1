package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityAirplaneT extends EntityAircraftBase {

    public EntityAirplaneT(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected boolean isDefaultLightAircraft() {
        return false;
    }

    @Override
    protected void applyFlyParticle() {
        if (this.tickCount % 2 == 0) {
            Vec3 motion = this.getDeltaMovement();
            this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX(), this.getY() + this.getBbHeight(), this.getZ(),
                    -motion.x * 0.5D, 0.07D, -motion.z * 0.5D);
        }
    }
}
