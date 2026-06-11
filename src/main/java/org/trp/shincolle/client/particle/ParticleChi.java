package org.trp.shincolle.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.EntityShipBase;

public class ParticleChi extends Particle {
    private static final ParticleRenderType UNTEXTURED_RENDER = new ParticleRenderType() {
        @Override
        public BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        }

        @Override
        public String toString() {
            return "SHINCOLLE_CHI";
        }
    };

    private final int hostEntityId;
    private final int particleType;
    private final float sizeScale;
    private float radChi;

    protected ParticleChi(ClientLevel level, double scale, int hostEntityId, int particleType) {
        super(level, 0.0D, 0.0D, 0.0D);
        this.setSize(0.0F, 0.0F);
        this.hostEntityId = hostEntityId;
        this.particleType = particleType;
        this.hasPhysics = false;
        this.xd = 0.0D;
        this.yd = 0.0D;
        this.zd = 0.0D;
        this.sizeScale = (float) scale;

        Entity host = this.level.getEntity(this.hostEntityId);
        if (host != null) {
            this.setPos(host.getX(), host.getY() + host.getBbHeight() * 0.55D, host.getZ());
        }
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (particleType == 1) {
            this.rCol = 1.0F;
            this.gCol = 1.0F;
            this.bCol = 1.0F;
            this.alpha = 1.0F;
            this.lifetime = 40;
            this.radChi = (float) scale * 12.0F;
        }
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ > this.lifetime) {
            this.remove();
            return;
        }

        Entity host = this.level.getEntity(this.hostEntityId);
        if (host == null || !host.isAlive()) {
            this.remove();
            return;
        }

        if (host instanceof EntityShipBase ship) {
            int phase = ship.getStateEmotion(5);
            if (phase == 0 || phase == 2) {
                this.remove();
                return;
            }
        }

        float angle = (2.0F * (float) Math.PI / this.lifetime) * this.age;
        float cos = Mth.cos(angle);
        float sin = Mth.sin(angle);
        float offsetX = this.radChi * cos;
        float offsetZ = this.radChi * sin;

        this.setPos(host.getX() + offsetX, host.getY() + host.getBbHeight() * 0.55D, host.getZ() + offsetZ);
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        if (this.age <= 1) {
            return;
        }

        Vec3 cameraPos = camera.getPosition();
        float px = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float py = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float pz = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        float size = this.sizeScale;

        drawCross(buffer, px, py, pz, size, this.rCol, this.gCol, this.bCol, this.alpha);

        float alpha2 = this.alpha * 0.5F;
        drawCross(buffer, px, py, pz, size * 1.3F, this.rCol, this.gCol, this.bCol, alpha2);
    }

    private void drawCross(VertexConsumer buffer, float x, float y, float z, float s, float r, float g, float b, float a) {
        buffer.addVertex(x, y, z + s).setColor(r, g, b, a);
        buffer.addVertex(x, y + s, z).setColor(r, g, b, a);
        buffer.addVertex(x + s, y, z).setColor(r, g, b, a);
        buffer.addVertex(x, y - s, z).setColor(r, g, b, a);

        buffer.addVertex(x + s, y, z).setColor(r, g, b, a);
        buffer.addVertex(x, y + s, z).setColor(r, g, b, a);
        buffer.addVertex(x, y, z - s).setColor(r, g, b, a);
        buffer.addVertex(x, y - s, z).setColor(r, g, b, a);

        buffer.addVertex(x, y, z - s).setColor(r, g, b, a);
        buffer.addVertex(x, y + s, z).setColor(r, g, b, a);
        buffer.addVertex(x - s, y, z).setColor(r, g, b, a);
        buffer.addVertex(x, y - s, z).setColor(r, g, b, a);

        buffer.addVertex(x - s, y, z).setColor(r, g, b, a);
        buffer.addVertex(x, y + s, z).setColor(r, g, b, a);
        buffer.addVertex(x, y, z + s).setColor(r, g, b, a);
        buffer.addVertex(x, y - s, z).setColor(r, g, b, a);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return UNTEXTURED_RENDER;
    }

    @Override
    protected int getLightColor(float partialTicks) {
        return LightTexture.FULL_BRIGHT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprites) {}

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double scale, double hostEntityId, double particleType) {
            return new ParticleChi(level, scale, (int) Math.round(hostEntityId), (int) Math.round(particleType));
        }
    }
}
