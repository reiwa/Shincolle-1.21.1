package org.trp.shincolle.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ParticleFog extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final float initAlpha;

    protected ParticleFog(ClientLevel level, double x, double y, double z,
                          double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;

        double radius = 0.02D;
        double theta = this.random.nextDouble() * Math.PI * 2;
        double phi = Math.acos(2 * this.random.nextDouble() - 1);

        this.xd = xSpeed + Math.sin(phi) * Math.cos(theta) * radius;
        this.yd = Math.max(0, ySpeed + Math.cos(phi) * radius);
        this.zd = zSpeed + Math.sin(phi) * Math.sin(theta) * radius;

        this.quadSize = (this.random.nextFloat() * 0.5F + 0.5F) * 0.25F;
        this.lifetime = 60;
        this.hasPhysics = false;
        this.alpha = 1.0F;
        this.initAlpha = this.alpha;

        this.setSpriteFromAge(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        this.move(this.xd, this.yd, this.zd);

        float lifeFrac = (float) this.age / (float) this.lifetime;
        this.alpha = this.initAlpha * (1.0F - lifeFrac);

        this.xd *= 0.9D;
        this.yd *= 0.9D;
        this.zd *= 0.9D;

        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected int getLightColor(float partialTick) {
        return net.minecraft.client.renderer.LightTexture.FULL_BRIGHT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleFog(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
