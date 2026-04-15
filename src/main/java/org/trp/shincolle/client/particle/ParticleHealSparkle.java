package org.trp.shincolle.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ParticleHealSparkle extends Particle {
    private static final ParticleRenderType UNTEXTURED_RENDER = new ParticleRenderType() {
        @Override
        public BufferBuilder begin(Tesselator tesselator, TextureManager tm) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        }

        @Override
        public String toString() {
            return "SHINCOLLE_HEAL_SPARKLE";
        }
    };

    private static final int LIFETIME = 20;
    private static final int MAX_BEAM_AGE = 20;
    private static final float BASE_SCALE = 0.075F;

    private final float beamFad;
    private final float beamRiseSpeed;
    private final float beamHeight;
    private final float[][] beams;
    private int beamCurrent;
    private final float quadSize;

    protected ParticleHealSparkle(ClientLevel level, double x, double y, double z,
                                  double beamFad, double beamRiseSpeed, double beamHeight) {
        super(level, x, y, z);
        this.beamFad = (float) Math.max(0.0D, beamFad);
        this.beamRiseSpeed = (float) beamRiseSpeed;
        this.beamHeight = (float) Math.max(0.0D, beamHeight);
        this.lifetime = LIFETIME;
        this.hasPhysics = false;
        this.quadSize = BASE_SCALE;

        int setting = getParticleSetting(level);
        int numBeam = Math.max(1, (3 - setting) * 15);
        this.beams = new float[numBeam][8];
        this.beamCurrent = 0;
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

        int setting = getParticleSetting(this.level);
        int spawnCount = Math.max(1, 4 - setting);
        for (int i = 0; i < spawnCount; i++) {
            spawnBeam();
        }

        for (float[] beam : this.beams) {
            beam[1] += this.beamRiseSpeed;
            beam[7] += 1.0F;
            beam[6] = Math.min(1.0F, this.random.nextFloat() + 0.1F);
        }
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float baseX = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float baseY = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float baseZ = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        Quaternionf rotation = camera.rotation();

        for (float[] beam : this.beams) {
            float beamAge = beam[7];
            if (beamAge <= 0.0F || beamAge >= MAX_BEAM_AGE) {
                continue;
            }

            float size = (MAX_BEAM_AGE - beamAge) * 0.05F * this.quadSize;
            if (size <= 0.0F) {
                continue;
            }

            float px = baseX + beam[0];
            float py = baseY + beam[1];
            float pz = baseZ + beam[2];

            Vector3f[] corners = new Vector3f[]{
                    new Vector3f(-1.0F, -1.0F, 0.0F),
                    new Vector3f(-1.0F, 1.0F, 0.0F),
                    new Vector3f(1.0F, 1.0F, 0.0F),
                    new Vector3f(1.0F, -1.0F, 0.0F)
            };

            for (Vector3f corner : corners) {
                corner.rotate(rotation);
                corner.mul(size);
                corner.add(px, py, pz);
            }

            float r = Math.min(1.0F, Math.max(0.0F, beam[3]));
            float g = Math.min(1.0F, Math.max(0.0F, beam[4]));
            float b = Math.min(1.0F, Math.max(0.0F, beam[5]));
            float a = Math.min(1.0F, Math.max(0.0F, beam[6]));

            buffer.addVertex(corners[0].x(), corners[0].y(), corners[0].z()).setColor(r, g, b, a);
            buffer.addVertex(corners[1].x(), corners[1].y(), corners[1].z()).setColor(r, g, b, a);
            buffer.addVertex(corners[2].x(), corners[2].y(), corners[2].z()).setColor(r, g, b, a);
            buffer.addVertex(corners[3].x(), corners[3].y(), corners[3].z()).setColor(r, g, b, a);

            buffer.addVertex(corners[3].x(), corners[3].y(), corners[3].z()).setColor(r, g, b, a);
            buffer.addVertex(corners[2].x(), corners[2].y(), corners[2].z()).setColor(r, g, b, a);
            buffer.addVertex(corners[1].x(), corners[1].y(), corners[1].z()).setColor(r, g, b, a);
            buffer.addVertex(corners[0].x(), corners[0].y(), corners[0].z()).setColor(r, g, b, a);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return UNTEXTURED_RENDER;
    }

    private void spawnBeam() {
        float randFactor = this.random.nextFloat() * 1.2F - 0.5F;
        float red = 1.0F;
        float green = Mth.clamp(1.0F + randFactor, 0.001F, 1.5F);
        float blue = 1.0F;

        this.beams[this.beamCurrent][0] = (this.random.nextFloat() - 0.5F) * this.beamFad;
        this.beams[this.beamCurrent][1] = (this.random.nextFloat() - 0.5F) * this.beamFad;
        this.beams[this.beamCurrent][2] = (this.random.nextFloat() - 0.5F) * this.beamFad;
        this.beams[this.beamCurrent][3] = red;
        this.beams[this.beamCurrent][4] = green;
        this.beams[this.beamCurrent][5] = blue;
        this.beams[this.beamCurrent][6] = 1.0F;
        this.beams[this.beamCurrent][7] = 0.0F;

        this.beamCurrent = (this.beamCurrent + 1) % this.beams.length;
    }

    private int getParticleSetting(Level level) {
        if (Minecraft.getInstance().level != level) {
            return 0;
        }
        return Minecraft.getInstance().options.particles().get().getId();
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprites) {
        }

        @Override
        public ParticleHealSparkle createParticle(SimpleParticleType type, ClientLevel level, double x, double y,
                                                  double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleHealSparkle(level, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
