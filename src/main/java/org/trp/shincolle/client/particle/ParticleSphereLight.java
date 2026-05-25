package org.trp.shincolle.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.utility.CalcHelper;

public class ParticleSphereLight extends Particle {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/particle/particlegradientline.png");

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
            return "SHINCOLLE_SPHERE_LIGHT";
        }
    };

    private final int particleType;
    private int beamCurrent;
    private final int hostEntityId;
    private float[][] beamPos;
    private float beamRad;
    private float beamSpd;
    private float beamThick;
    private float beamHeight;
    private float pScale;

    public ParticleSphereLight(ClientLevel level, double x, double y, double z, float scale, int type, int hostEntityId) {
        super(level, x, y, z);
        this.setSize(0.0f, 0.0f);
        this.hostEntityId = hostEntityId;
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
        this.particleType = type;
        this.hasPhysics = false;
        this.beamCurrent = 0;

        int setting = getParticleSetting(level);
        int numBeam = (3 - setting) * 25;

        Entity hostEntity = level.getEntity(hostEntityId);
        if (hostEntity instanceof org.trp.shincolle.entity.projectile.EntityProjectileStatic projectile) {
            this.lifetime = projectile.getLifeLength();
            this.beamHeight = projectile.getBbHeight() * 0.5f;
        } else {
            this.lifetime = 40;
            this.beamHeight = 0.25f;
        }

        switch (type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                initializeDefaultType(scale, numBeam);
                break;
            case 5:
                initializeType5(scale, numBeam);
                break;
            default:
                initializeType5(scale, numBeam);
                break;
        }

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
    }

    private void initializeDefaultType(float scale, int numBeam) {
        this.pScale = scale;
        this.beamRad = 1.0f;
        this.beamSpd = 0.9f;
        this.beamThick = 1.0f;
        this.rCol = 1.0f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;
        this.alpha = 1.0f;
        this.beamPos = new float[numBeam][6];
    }

    private void initializeType5(float scale, int numBeam) {
        this.pScale = scale;
        this.beamRad = 0.5f;
        this.beamSpd = 0.8f;
        this.beamThick = 2.0f;
        this.rCol = 0.0f;
        this.gCol = 0.0f;
        this.bCol = 0.0f;
        this.alpha = 0.8f;
        this.beamPos = new float[numBeam][6];
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
        if (host != null) {
            this.setPos(host.getX(), host.getY() + this.beamHeight, host.getZ());
        }

        switch (this.particleType) {
            case 0:
                updateType0();
                break;
            case 1:
                updateType1();
                break;
            case 5:
                updateType5();
                break;
            default:
                updateType5();
                break;
        }
    }

    private void updateType0() {
        if (this.age <= 30) {
            int setting = getParticleSetting(this.level);
            int particlesToSpawn = (3 - setting) * 3;
            for (int i = 0; i < particlesToSpawn; ++i) {
                spawnBeam(this.rCol, this.gCol, this.bCol, this.alpha, 360.0f);
            }
        }
        updateBeamPositionsCommon();
    }

    private void updateType1() {
        if (this.age <= 40) {
            for (int i = 0; i < 2; ++i) {
                spawnBeam(this.rCol, this.gCol, this.bCol, this.alpha, 540.0f);
            }
        }
        updateBeamPositionsType1();
    }

    private void updateType5() {
        if (this.age <= this.lifetime * 0.95f) {
            if (this.age > this.lifetime * 0.5f) {
                this.alpha *= 0.8f;
            }
            int setting = getParticleSetting(this.level);
            int particlesToSpawn = (3 - setting) * 3;
            for (int i = 0; i < particlesToSpawn; ++i) {
                float r = this.rCol + this.random.nextFloat() * 0.1f;
                float b = this.bCol + this.random.nextFloat() * 0.2f;
                spawnBeam(r, this.gCol, b, this.alpha, 360.0f);
            }
        }
        updateBeamPositionsCommon();
    }

    private void spawnBeam(float r, float g, float b, float a, float angleRange) {
        float[] newpos = CalcHelper.rotateXZByAxis(this.beamRad * (this.random.nextFloat() + 1.0f), this.beamRad * (this.random.nextFloat() + 1.0f), this.random.nextFloat() * angleRange * ((float) Math.PI / 180), 1.0f);
        this.beamPos[this.beamCurrent] = new float[]{newpos[0], newpos[1], r, g, b, a};
        this.beamCurrent = (this.beamCurrent + 1) % this.beamPos.length;
    }

    private void updateBeamPositionsCommon() {
        for (float[] beam : this.beamPos) {
            beam[0] *= this.beamSpd;
            beam[1] *= this.beamSpd;
            beam[0] = clampToMinimumMagnitude(beam[0], 0.001f);
            beam[1] = clampToMinimumMagnitude(beam[1], 0.001f);
        }
    }

    private void updateBeamPositionsType1() {
        float multiplier = 1.0f + this.beamSpd;
        for (float[] beam : this.beamPos) {
            beam[0] *= multiplier;
            beam[1] *= multiplier;
            if (this.age > 30) {
                beam[5] *= 0.8f;
            }
        }
    }

    private float clampToMinimumMagnitude(float value, float minMagnitude) {
        if (value > 0.0f && value < minMagnitude) {
            return minMagnitude;
        }
        if (value < 0.0f && value > -minMagnitude) {
            return -minMagnitude;
        }
        return value;
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float px = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float py = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float pz = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        Quaternionf rotation = camera.rotation();
        int light = LightTexture.FULL_BRIGHT;

        for (float[] beam : this.beamPos) {
            renderBeam(buffer, rotation, px, py, pz, beam, light);
        }
    }

    private void renderBeam(VertexConsumer buffer, Quaternionf rotation, float px, float py, float pz, float[] beam, int light) {
        if (beam[0] == 0.0f && beam[1] == 0.0f) return;
        float depth = this.random.nextFloat() * 0.1f;
        float x0 = beam[0];
        float y0 = beam[1];
        float r = beam[2];
        float g = beam[3];
        float b = beam[4];
        float a = beam[5];
        float scaledX = this.pScale * x0;
        float scaledY = this.pScale * y0;
        float thickOffsetX = y0 * this.beamThick;
        float thickOffsetY = x0 * this.beamThick;

        addBeamVertex(buffer, rotation, px, py, pz, scaledX - thickOffsetX, scaledY + thickOffsetY, depth, 1.0f, 1.0f, r, g, b, a, light);
        addBeamVertex(buffer, rotation, px, py, pz, scaledX, scaledY, depth, 1.0f, 0.0f, r, g, b, a, light);
        addBeamVertex(buffer, rotation, px, py, pz, x0, y0, depth, 0.0f, 0.0f, r, g, b, a, light);
        addBeamVertex(buffer, rotation, px, py, pz, x0 - thickOffsetX, y0 + thickOffsetY, depth, 0.0f, 1.0f, r, g, b, a, light);
    }

    private void addBeamVertex(VertexConsumer buffer, Quaternionf rotation, float px, float py, float pz, float lx, float ly, float lz, float u, float v, float r, float g, float b, float a, int light) {
        Vector3f pos = new Vector3f(lx, ly, lz);
        pos.mul(-0.25f, -0.25f, 0.25f);
        pos.rotate(rotation);
        pos.add(px, py, pz);
        buffer.addVertex(pos.x(), pos.y(), pos.z()).setColor(r, g, b, a).setUv(u, v).setLight(light);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return TEXTURED_RENDER;
    }

    protected int getParticleSetting(Level level) {
        if (Minecraft.getInstance().level != level) {
            return 0;
        }
        return Minecraft.getInstance().options.particles().get().getId();
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double scale, double hostEntityId, double particleType) {
            return new ParticleSphereLight(level, x, y, z, (float) scale, (int) Math.round(particleType), (int) Math.round(hostEntityId));
        }
    }
}
