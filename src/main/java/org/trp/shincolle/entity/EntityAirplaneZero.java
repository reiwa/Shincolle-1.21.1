package org.trp.shincolle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public class EntityAirplaneZero extends EntityAircraftBase {

    public EntityAirplaneZero(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void applyFlyParticle() {
        net.minecraft.world.phys.Vec3 motion = this.getDeltaMovement();
        double trailX = this.getX() - motion.x * 1.5D;
        double trailY = this.getY() + this.getBbHeight();
        double trailZ = this.getZ() - motion.z * 1.5D;

        this.level().addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE,
                trailX, trailY, trailZ,
                -motion.x * 0.5D, -motion.y * 0.5D, -motion.z * 0.5D);
    }
}
