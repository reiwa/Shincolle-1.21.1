package org.trp.shincolle.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ParticleSpray extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final float maxQuadSize;

    protected ParticleSpray(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, SpriteSet sprites) {
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

        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;
        this.alpha = 0.5F;

        this.lifetime = 50;

        this.hasPhysics = true;
        this.gravity = 0.0F;
        this.friction = 0.96F;

        if (speed > 0.25D) {
            this.maxQuadSize = 0.15F;
        } else {
            this.maxQuadSize = 0.075F;
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
            return new ParticleSpray(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
