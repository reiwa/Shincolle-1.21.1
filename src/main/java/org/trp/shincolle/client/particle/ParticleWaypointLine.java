package org.trp.shincolle.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.particles.SimpleParticleType;

public class ParticleWaypointLine extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final float pScale;

    private final int lineType;

    protected ParticleWaypointLine(ClientLevel level, double x, double y, double z,
                                    double vx, double vy, double vz,
                                    int lineType, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.lineType = lineType;

        this.xd = vx;
        this.yd = vy;
        this.zd = vz;

        if (lineType == 0) {
            this.rCol = 0.5f;
            this.gCol = 0.0f;
            this.bCol = 0.5f;
        } else if (lineType == 1) {
            this.rCol = 0.2f;
            this.gCol = 0.8f;
            this.bCol = 1.0f;
        } else {
            this.rCol = 1.0f;
            this.gCol = 0.0f;
            this.bCol = 0.0f;
        }
        this.alpha = 0.5f;

        this.quadSize = this.quadSize * 3.0f;
        this.pScale = this.quadSize;
        this.lifetime = 100;

        this.hasPhysics = false;
        this.gravity = 0.0f;
        this.friction = 1.0f;

        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.setSpriteFromAge(this.sprites);

        this.x += this.xd;
        this.y += this.yd;
        this.z += this.zd;

        float ageRatio = (float) this.age / this.lifetime;
        float scaleFactor = Math.min(1.0f, ageRatio * 32.0f);
        this.quadSize = this.pScale * scaleFactor;

        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return LightTexture.FULL_BRIGHT;
    }

    public static class ProviderPurple implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public ProviderPurple(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleWaypointLine(level, x, y, z, xSpeed, ySpeed, zSpeed, 0, this.sprites);
        }
    }

    public static class ProviderCyan implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public ProviderCyan(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleWaypointLine(level, x, y, z, xSpeed, ySpeed, zSpeed, 1, this.sprites);
        }
    }

    public static class ProviderRed implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public ProviderRed(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleWaypointLine(level, x, y, z, xSpeed, ySpeed, zSpeed, 2, this.sprites);
        }
    }
}
