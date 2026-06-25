package org.trp.shincolle.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ParticleSprayCyan extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final float maxQuadSize;

    protected ParticleSprayCyan(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, SpriteSet sprites) {
        super(level, x, y, z, vx, vy, vz);
        this.sprites = sprites;

        double speedSq = vx * vx + vy * vy + vz * vz;
        double speedLimit = 0.3D;
        double speed = Math.sqrt(speedSq);
        if (speedSq > speedLimit * speedLimit) {
            this.xd = (vx / speed) * speedLimit;
            this.yd = (vy / speed) * speedLimit;
            this.zd = (vz / speed) * speedLimit;
        } else {
            this.xd = vx;
            this.yd = vy;
            this.zd = vz;
        }

        double initialVelocity = speed / 0.15D;

        if (initialVelocity > 1.25D) {
            float velred = 1.4F - (float) initialVelocity;
            this.rCol = Math.max(0.0F, Math.min(1.0F, velred));
            this.gCol = 1.0F;
            this.bCol = 1.0F;
            this.alpha = 0.75F;
        } else if (initialVelocity < 0.25D) {
            float velgb = ((float) initialVelocity - 0.2F) * 3.333F;
            this.rCol = 1.0F;
            this.gCol = Math.max(0.0F, Math.min(1.0F, velgb));
            this.bCol = Math.max(0.0F, Math.min(1.0F, velgb));
            this.alpha = 0.75F;
        } else {
            this.rCol = 0.7F;
            this.gCol = 1.0F;
            this.bCol = 1.0F;
            this.alpha = 0.75F;
        }

        this.lifetime = 40;

        this.hasPhysics = true;
        this.gravity = 0.0F;
        this.friction = 0.96F;

        if (speed > 0.25D) {
            this.maxQuadSize = 0.15F;
        } else {
            this.maxQuadSize = 0.09F;
        }
        this.quadSize = 0.0F;

        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSpriteFromAge(this.sprites);
            float ageRatio = (float) this.age / (float) this.lifetime;
            this.quadSize = this.maxQuadSize * Math.min(1.0F, ageRatio * 32.0F);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleSprayCyan(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
