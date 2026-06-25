package org.trp.shincolle.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.Shincolle;

public class ParticlePointerLine extends Particle {

    private static final ResourceLocation TEXTURE_LASER = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/particle/particlelaser.png");

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
            return "SHINCOLLE_POINTER_LINE_UNTEXTURED";
        }
    };

    private static final ParticleRenderType TEXTURED_RENDER = new ParticleRenderType() {
        @Override
        public BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {
            RenderSystem.setShader(GameRenderer::getPositionColorTexLightmapShader);
            RenderSystem.setShaderTexture(0, TEXTURE_LASER);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);
        }

        @Override
        public String toString() {
            return "SHINCOLLE_POINTER_LINE_TEXTURED";
        }
    };

    private final int particleType;
    private final float vx, vy, vz;
    private final float shotYaw, shotPitch;

    private float scaleOut = 0.05f;
    private float scaleIn = 0.0125f;
    private float alphaOut = 0.1f;
    private float alphaIn = 0.2f;

    private float particleScale = 0.5f;

    protected ParticlePointerLine(ClientLevel level, double x, double y, double z,
                                  double vx, double vy, double vz, int type) {
        super(level, x, y, z);
        this.particleType = type;
        this.vx = (float) vx;
        this.vy = (float) vy;
        this.vz = (float) vz;

        double d1 = Math.sqrt(vx * vx + vy * vy + vz * vz);
        double motX = vx, motY = vy, motZ = vz;
        if (d1 > 1.0E-4) {
            motX /= d1;
            motY /= d1;
            motZ /= d1;
        }
        double f1 = Math.sqrt(motX * motX + motZ * motZ);
        this.shotPitch = (float) -Math.atan2(motY, f1);
        this.shotYaw = (float) -Math.atan2(motX, motZ);

        this.hasPhysics = false;

        if (this.particleType == 0) {
            this.lifetime = 9;
            this.rCol = 1.0f;
            this.gCol = 1.0f;
            this.bCol = 1.0f;
        } else if (this.particleType == 2) {
            this.lifetime = 11;
            this.age = 4;
            this.rCol = 1.0f;
            this.gCol = 0.0f;
            this.bCol = 0.0f;
            this.alpha = 1.0f;
        } else {
            this.lifetime = 11;
            this.age = 4;
            this.rCol = 1.0f;
            this.gCol = 0.0f;
            this.bCol = 1.0f;
            this.alpha = 1.0f;
        }
    }

    @Override
    public void tick() {
        if (this.particleType == 0) {
            if (this.age > 4) {
                this.alphaIn = 1.0f + (4 - this.age) * 0.2f;
            } else {
                this.alphaIn = 0.2f + this.age * 0.2f;
            }
            this.alphaOut = this.alphaIn * 0.5f;
        }

        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    private float[] rotateXYZByYawPitch(float x, float y, float z, float yaw, float pitch, float scale) {
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
        if (this.particleType == 0 && this.age <= 1) {
            return;
        }

        Vec3 cameraPos = camera.getPosition();
        float hx = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float hy = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float hz = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        float tx = hx + this.vx;
        float ty = hy + this.vy;
        float tz = hz + this.vz;

        int light = LightTexture.FULL_BRIGHT;

        if (this.particleType == 0) {
            float[] v1 = rotateXYZByYawPitch(1.0f, -1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);
            float[] v2 = rotateXYZByYawPitch(1.0f, 1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);
            float[] v3 = rotateXYZByYawPitch(-1.0f, 1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);
            float[] v4 = rotateXYZByYawPitch(-1.0f, -1.0f, -1.0f, this.shotYaw, this.shotPitch, this.scaleOut);

            float[] v5 = rotateXYZByYawPitch(1.0f, -1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);
            float[] v6 = rotateXYZByYawPitch(1.0f, 1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);
            float[] v7 = rotateXYZByYawPitch(-1.0f, 1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);
            float[] v8 = rotateXYZByYawPitch(-1.0f, -1.0f, 0.0f, this.shotYaw, this.shotPitch, this.scaleIn);

            drawQuad(buffer, tx + v1[0], ty + v1[1], tz + v1[2], tx + v2[0], ty + v2[1], tz + v2[2],
                             hx + v2[0], hy + v2[1], hz + v2[2], hx + v1[0], hy + v1[1], hz + v1[2], this.alphaOut, light);
            drawQuad(buffer, tx + v4[0], ty + v4[1], tz + v4[2], tx + v3[0], ty + v3[1], tz + v3[2],
                             hx + v3[0], hy + v3[1], hz + v3[2], hx + v4[0], hy + v4[1], hz + v4[2], this.alphaOut, light);
            drawQuad(buffer, tx + v1[0], ty + v1[1], tz + v1[2], tx + v4[0], ty + v4[1], tz + v4[2],
                             hx + v4[0], hy + v4[1], hz + v4[2], hx + v1[0], hy + v1[1], hz + v1[2], this.alphaOut, light);
            drawQuad(buffer, tx + v2[0], ty + v2[1], tz + v2[2], tx + v3[0], ty + v3[1], tz + v3[2],
                             hx + v3[0], hy + v3[1], hz + v3[2], hx + v2[0], hy + v2[1], hz + v2[2], this.alphaOut, light);

            drawQuad(buffer, tx + v5[0], ty + v5[1], tz + v5[2], tx + v6[0], ty + v6[1], tz + v6[2],
                             hx + v6[0], hy + v6[1], hz + v6[2], hx + v5[0], hy + v5[1], hz + v5[2], this.alphaIn, light);
            drawQuad(buffer, tx + v8[0], ty + v8[1], tz + v8[2], tx + v7[0], ty + v7[1], tz + v7[2],
                             hx + v7[0], hy + v7[1], hz + v7[2], hx + v8[0], hy + v8[1], hz + v8[2], this.alphaIn, light);
            drawQuad(buffer, tx + v5[0], ty + v5[1], tz + v5[2], tx + v8[0], ty + v8[1], tz + v8[2],
                             hx + v8[0], hy + v8[1], hz + v8[2], hx + v5[0], hy + v5[1], hz + v5[2], this.alphaIn, light);
            drawQuad(buffer, tx + v6[0], ty + v6[1], tz + v6[2], tx + v7[0], ty + v7[1], tz + v7[2],
                             hx + v7[0], hy + v7[1], hz + v7[2], hx + v6[0], hy + v6[1], hz + v6[2], this.alphaIn, light);

            drawQuad(buffer, hx + v4[0], hy + v4[1], hz + v4[2], hx + v3[0], hy + v3[1], hz + v3[2],
                             hx + v2[0], hy + v2[1], hz + v2[2], hx + v1[0], hy + v1[1], hz + v1[2], this.alphaOut, light);
            drawQuad(buffer, tx + v1[0], ty + v1[1], tz + v1[2], tx + v2[0], ty + v2[1], tz + v2[2],
                             tx + v3[0], ty + v3[1], tz + v3[2], tx + v4[0], ty + v4[1], tz + v4[2], this.alphaOut, light);

            drawQuad(buffer, hx + v8[0], hy + v8[1], hz + v8[2], hx + v7[0], hy + v7[1], hz + v7[2],
                             hx + v6[0], hy + v6[1], hz + v6[2], hx + v5[0], hy + v5[1], hz + v5[2], this.alphaIn, light);
            drawQuad(buffer, tx + v5[0], ty + v5[1], tz + v5[2], tx + v6[0], ty + v6[1], tz + v6[2],
                             tx + v7[0], ty + v7[1], tz + v7[2], tx + v8[0], ty + v8[1], tz + v8[2], this.alphaIn, light);

        } else {
            float minU = 0.0f;
            float maxU = (float) this.random.nextInt(32) + 32;
            float minV = (this.age % 12) / 12.0f;
            float maxV = minV + 0.08333333f;

            float yOff = this.particleScale * 0.3f;

            drawTexturedQuad(buffer, tx, ty, tz, tx, ty + yOff, tz, hx, hy + yOff, hz, hx, hy, hz, maxU, minV, minU, maxV, light);
            drawTexturedQuad(buffer, hx, hy, hz, hx, hy + yOff, hz, tx, ty + yOff, tz, tx, ty, tz, minU, maxV, maxU, minV, light);
        }
    }

    private void drawQuad(VertexConsumer buffer, float x1, float y1, float z1, float x2, float y2, float z2,
                          float x3, float y3, float z3, float x4, float y4, float z4, float alpha, int light) {
        buffer.addVertex(x1, y1, z1).setColor(this.rCol, this.gCol, this.bCol, alpha);
        buffer.addVertex(x2, y2, z2).setColor(this.rCol, this.gCol, this.bCol, alpha);
        buffer.addVertex(x3, y3, z3).setColor(this.rCol, this.gCol, this.bCol, alpha);
        buffer.addVertex(x4, y4, z4).setColor(this.rCol, this.gCol, this.bCol, alpha);
    }

    private void drawTexturedQuad(VertexConsumer buffer, float x1, float y1, float z1, float x2, float y2, float z2,
                                  float x3, float y3, float z3, float x4, float y4, float z4, 
                                  float u1, float v1, float u2, float v2, int light) {
        buffer.addVertex(x1, y1, z1).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setUv(u1, v2).setLight(light);
        buffer.addVertex(x2, y2, z2).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setUv(u1, v1).setLight(light);
        buffer.addVertex(x3, y3, z3).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setUv(u2, v1).setLight(light);
        buffer.addVertex(x4, y4, z4).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setUv(u2, v2).setLight(light);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return this.particleType == 0 ? UNTEXTURED_RENDER : TEXTURED_RENDER;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final int type;

        public Provider(int type, float[] ignored) {
            this.type = type;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new ParticlePointerLine(level, x, y, z, vx, vy, vz, this.type);
        }
    }
}
