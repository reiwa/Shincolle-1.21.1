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

public class Particle91Type extends Particle {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/particle/particle91type.png");

    private static final ParticleRenderType TEXTURED_RENDER = new ParticleRenderType() {
        @Override
        public BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {
            RenderSystem.setShader(GameRenderer::getPositionColorTexLightmapShader);
            RenderSystem.setShaderTexture(0, TEXTURE);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);
        }

        @Override
        public String toString() {
            return "SHINCOLLE_91TYPE";
        }
    };

    private final int fadeTime = 16;
    private final int middTime = 60;
    private final int totalTime = 2 * this.fadeTime + this.middTime;
    private final float fadeCoef = 1.0F / this.fadeTime;
    private final float sizeScale;
    private float alphaVal;

    protected Particle91Type(ClientLevel level, double x, double y, double z, double scale) {
        super(level, x, y, z);
        this.setSize(0.0F, 0.0F);
        this.xo = x;
        this.yo = y + this.random.nextDouble() * 4.0D;
        this.zo = z;
        this.x = this.xo;
        this.y = this.yo;
        this.z = this.zo;
        this.hasPhysics = false;
        this.sizeScale = (float) scale;
        this.lifetime = 136;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ > this.lifetime) {
            this.remove();
        }
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float f11 = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float f12 = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float f13 = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        float yawRad = camera.getYRot() * Mth.DEG_TO_RAD;
        float pitchRad = camera.getXRot() * Mth.DEG_TO_RAD;
        float cosYaw = Mth.cos(yawRad);
        float sinYaw = Mth.sin(yawRad);
        float cosPitch = Mth.cos(pitchRad);

        int light = LightTexture.FULL_BRIGHT;

        for (int i = 0; i < 6; ++i) {
            int partAge = this.age - i * 8;
            if (partAge <= -1 || partAge >= this.totalTime) {
                continue;
            }

            float minu = 0.16666667F * i;
            float maxu = 0.16666667F * (i + 1);
            float scale;
            float px = f11 - (i - 2.5F) * this.sizeScale * 2.0F * cosYaw;
            float py = f12;
            float pz = f13 - (i - 2.5F) * this.sizeScale * 2.0F * sinYaw;

            if (partAge < this.fadeTime) {
                scale = this.sizeScale * (3.0F - 2.0F * this.fadeCoef * partAge);
                this.alphaVal = this.fadeCoef * partAge;
            } else if (partAge >= this.fadeTime + this.middTime) {
                partAge -= this.fadeTime + this.middTime;
                scale = this.sizeScale * (1.0F + 2.0F * this.fadeCoef * partAge);
                this.alphaVal = 1.0F - this.fadeCoef * partAge;
            } else {
                scale = this.sizeScale;
                this.alphaVal = 1.0F;
            }

            addQuad(buffer, scale, px, py, pz, cosYaw, cosPitch, sinYaw, minu, maxu, 0.0F, 1.0F, light);
        }
    }

    private void addQuad(VertexConsumer buffer, float scale, float x, float y, float z,
                         float offx, float offy, float offz, float minu, float maxu, float minv, float maxv, int light) {
        float offsetX = offx * scale;
        float offsetY = offy * scale;
        float offsetZ = offz * scale;
        buffer.addVertex(x - offsetX, y - offsetY, z - offsetZ).setColor(1.0F, 1.0F, 1.0F, this.alphaVal).setUv(maxu, maxv).setLight(light);
        buffer.addVertex(x - offsetX, y + offsetY, z - offsetZ).setColor(1.0F, 1.0F, 1.0F, this.alphaVal).setUv(maxu, minv).setLight(light);
        buffer.addVertex(x + offsetX, y + offsetY, z + offsetZ).setColor(1.0F, 1.0F, 1.0F, this.alphaVal).setUv(minu, minv).setLight(light);
        buffer.addVertex(x + offsetX, y - offsetY, z + offsetZ).setColor(1.0F, 1.0F, 1.0F, this.alphaVal).setUv(minu, maxv).setLight(light);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return TEXTURED_RENDER;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Provider() {}

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double scale, double hostEntityId, double particleType) {
            return new Particle91Type(level, x, y, z, scale);
        }
    }
}
