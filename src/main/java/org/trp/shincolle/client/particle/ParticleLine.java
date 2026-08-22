package org.trp.shincolle.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ParticleLine extends Particle {

    private static final ParticleRenderType UNTEXTURED_RENDER = new ParticleRenderType() {
        @Override
        public BufferBuilder begin(Tesselator tesselator, net.minecraft.client.renderer.texture.TextureManager textureManager) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        }

        @Override
        public String toString() {
            return "SHINCOLLE_LINE_UNTEXTURED";
        }
    };

    private final double dirX;
    private final double dirY;
    private final double dirZ;
    private final int lineType;
    private float shotYaw;
    private float shotPitch;
    private float scaleOut;
    private float scaleIn;
    private float alphaOut;
    private float alphaIn;

    protected ParticleLine(ClientLevel level, double x, double y, double z,
                           double vx, double vy, double vz,
                           int lineType) {
        super(level, x, y, z);
        this.dirX = vx;
        this.dirY = vy;
        this.dirZ = vz;
        this.lineType = lineType;
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
        this.hasPhysics = false;
        this.setSize(0.0f, 0.0f);
        this.lifetime = 16;

        // Compact sleek laser thickness matching 1.12.2_old
        this.scaleOut = 0.06f;
        this.scaleIn = 0.018f;
        this.alphaIn = 0.95f;
        this.alphaOut = 0.22f;

        double d1 = Math.sqrt(vx * vx + vy * vy + vz * vz);
        if (d1 > 1.0E-4) {
            double motX = vx / d1;
            double motY = vy / d1;
            double motZ = vz / d1;
            double f1 = Math.sqrt(motX * motX + motZ * motZ);
            this.shotPitch = (float) -Math.atan2(motY, f1);
            this.shotYaw = (float) -Math.atan2(motX, motZ);
        }

        if (lineType == 0) {
            // Pale white-sky aura for ground coordinate / guard position target
            this.rCol = 0.85f;
            this.gCol = 0.92f;
            this.bCol = 1.0f;
        } else if (lineType == 1) {
            // Magenta-purple aura for guarded entity
            this.rCol = 0.95f;
            this.gCol = 0.2f;
            this.bCol = 1.0f;
        } else {
            // Red aura for enemy target
            this.rCol = 1.0f;
            this.gCol = 0.15f;
            this.bCol = 0.15f;
        }
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    private static float[] rotateXYZByYawPitch(float x, float y, float z, float yaw, float pitch, float scale) {
        float cosYaw = Mth.cos(yaw);
        float sinYaw = Mth.sin(yaw);
        float cosPitch = Mth.cos(-pitch);
        float sinPitch = Mth.sin(-pitch);
        float[] newPos = new float[]{x, y, z};
        newPos[1] = y * cosPitch + z * sinPitch;
        newPos[2] = z * cosPitch - y * sinPitch;
        float x2 = newPos[0];
        float z2 = newPos[2];
        newPos[0] = x2 * cosYaw - z2 * sinYaw;
        newPos[2] = z2 * cosYaw + x2 * sinYaw;
        newPos[0] *= scale;
        newPos[1] *= scale;
        newPos[2] *= scale;
        return newPos;
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        double lenSqr = dirX * dirX + dirY * dirY + dirZ * dirZ;
        if (lenSqr < 1.0E-4) {
            return;
        }

        Vec3 cam = camera.getPosition();
        float sx = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cam.x);
        float sy = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cam.y);
        float sz = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cam.z);

        float ex = sx + (float) this.dirX;
        float ey = sy + (float) this.dirY;
        float ez = sz + (float) this.dirZ;

        float fade = 1.0f - ((float) this.age + partialTicks) / (float) this.lifetime;
        float curAlphaOut = Math.max(0.0f, Math.min(1.0f, fade)) * this.alphaOut;
        float curAlphaIn = Math.max(0.0f, Math.min(1.0f, fade)) * this.alphaIn;

        // Outer glow tube offsets (rotated by shot yaw and pitch)
        float[] v1 = rotateXYZByYawPitch(1.0f, -1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v2 = rotateXYZByYawPitch(1.0f, 1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v3 = rotateXYZByYawPitch(-1.0f, 1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v4 = rotateXYZByYawPitch(-1.0f, -1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);

        // Inner solid white core offsets
        float[] v5 = rotateXYZByYawPitch(1.0f, -1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] v6 = rotateXYZByYawPitch(1.0f, 1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] v7 = rotateXYZByYawPitch(-1.0f, 1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] v8 = rotateXYZByYawPitch(-1.0f, -1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);

        // 1. Render Inner Solid White Core (4 quads)
        drawQuad(buffer, sx + v5[0], sy + v5[1], sz + v5[2], sx + v6[0], sy + v6[1], sz + v6[2],
                         ex + v6[0], ey + v6[1], ez + v6[2], ex + v5[0], ey + v5[1], ez + v5[2],
                         1.0f, 1.0f, 1.0f, curAlphaIn);
        drawQuad(buffer, sx + v6[0], sy + v6[1], sz + v6[2], sx + v7[0], sy + v7[1], sz + v7[2],
                         ex + v7[0], ey + v7[1], ez + v7[2], ex + v6[0], ey + v6[1], ez + v6[2],
                         1.0f, 1.0f, 1.0f, curAlphaIn);
        drawQuad(buffer, sx + v7[0], sy + v7[1], sz + v7[2], sx + v8[0], sy + v8[1], sz + v8[2],
                         ex + v8[0], ey + v8[1], ez + v8[2], ex + v7[0], ey + v7[1], ez + v7[2],
                         1.0f, 1.0f, 1.0f, curAlphaIn);
        drawQuad(buffer, sx + v8[0], sy + v8[1], sz + v8[2], sx + v5[0], sy + v5[1], sz + v5[2],
                         ex + v5[0], ey + v5[1], ez + v5[2], ex + v8[0], ey + v8[1], ez + v8[2],
                         1.0f, 1.0f, 1.0f, curAlphaIn);

        // 2. Render Outer Colored Halo Tube (4 quads)
        drawQuad(buffer, sx + v1[0], sy + v1[1], sz + v1[2], sx + v2[0], sy + v2[1], sz + v2[2],
                         ex + v2[0], ey + v2[1], ez + v2[2], ex + v1[0], ey + v1[1], ez + v1[2],
                         this.rCol, this.gCol, this.bCol, curAlphaOut);
        drawQuad(buffer, sx + v2[0], sy + v2[1], sz + v2[2], sx + v3[0], sy + v3[1], sz + v3[2],
                         ex + v3[0], ey + v3[1], ez + v3[2], ex + v2[0], ey + v2[1], ez + v2[2],
                         this.rCol, this.gCol, this.bCol, curAlphaOut);
        drawQuad(buffer, sx + v3[0], sy + v3[1], sz + v3[2], sx + v4[0], sy + v4[1], sz + v4[2],
                         ex + v4[0], ey + v4[1], ez + v4[2], ex + v3[0], ey + v3[1], ez + v3[2],
                         this.rCol, this.gCol, this.bCol, curAlphaOut);
        drawQuad(buffer, sx + v4[0], sy + v4[1], sz + v4[2], sx + v1[0], sy + v1[1], sz + v1[2],
                         ex + v1[0], ey + v1[1], ez + v1[2], ex + v4[0], ey + v4[1], ez + v4[2],
                         this.rCol, this.gCol, this.bCol, curAlphaOut);
    }

    private static void drawQuad(VertexConsumer buffer,
                                 float x1, float y1, float z1,
                                 float x2, float y2, float z2,
                                 float x3, float y3, float z3,
                                 float x4, float y4, float z4,
                                 float r, float g, float b, float a) {
        buffer.addVertex(x1, y1, z1).setColor(r, g, b, a);
        buffer.addVertex(x2, y2, z2).setColor(r, g, b, a);
        buffer.addVertex(x3, y3, z3).setColor(r, g, b, a);
        buffer.addVertex(x4, y4, z4).setColor(r, g, b, a);

        buffer.addVertex(x4, y4, z4).setColor(r, g, b, a);
        buffer.addVertex(x3, y3, z3).setColor(r, g, b, a);
        buffer.addVertex(x2, y2, z2).setColor(r, g, b, a);
        buffer.addVertex(x1, y1, z1).setColor(r, g, b, a);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return UNTEXTURED_RENDER;
    }

    public static class ProviderCyan implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new ParticleLine(level, x, y, z, vx, vy, vz, 0);
        }
    }

    public static class ProviderPurple implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new ParticleLine(level, x, y, z, vx, vy, vz, 1);
        }
    }

    public static class ProviderRed implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new ParticleLine(level, x, y, z, vx, vy, vz, 2);
        }
    }
}
