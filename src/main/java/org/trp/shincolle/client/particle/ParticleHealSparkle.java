package org.trp.shincolle.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ParticleHealSparkle extends TextureSheetParticle {
    private static final int LIFETIME = 20;
    private static final int MAX_BEAM_AGE = 20;
    private static final float BASE_SCALE = 0.075F;

    private final SpriteSet sprites;
    private final float beamFad;
    private final float beamRiseSpeed;
    private final float beamHeight;
    private final float[][] beams;
    private int beamCurrent;

    protected ParticleHealSparkle(ClientLevel level, double x, double y, double z,
                                  double beamFad, double beamRiseSpeed, double beamHeight,
                                  SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.beamFad = (float) Math.max(0.0D, beamFad);
        this.beamRiseSpeed = (float) beamRiseSpeed;
        this.beamHeight = (float) Math.max(0.0D, beamHeight);
        this.lifetime = LIFETIME;
        this.hasPhysics = false;
        this.quadSize = BASE_SCALE;
        this.alpha = 1.0F;
        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;
        this.setSpriteFromAge(sprites);

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
        TextureAtlasSprite sprite = this.sprite;
        float u0 = sprite.getU0();
        float u1 = sprite.getU1();
        float v0 = sprite.getV0();
        float v1 = sprite.getV1();

        Vec3 cameraPos = camera.getPosition();
        float baseX = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float baseY = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float baseZ = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        Quaternionf rotation = camera.rotation();
        int light = this.getLightColor(partialTicks);

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

            float r = beam[3];
            float g = beam[4];
            float b = beam[5];
            float a = beam[6];

            emitVertex(buffer, corners[0], u1, v1, light, r, g, b, a);
            emitVertex(buffer, corners[1], u1, v0, light, r, g, b, a);
            emitVertex(buffer, corners[2], u0, v0, light, r, g, b, a);
            emitVertex(buffer, corners[3], u0, v1, light, r, g, b, a);

            emitVertex(buffer, corners[3], u1, v1, light, r, g, b, a);
            emitVertex(buffer, corners[2], u1, v0, light, r, g, b, a);
            emitVertex(buffer, corners[1], u0, v0, light, r, g, b, a);
            emitVertex(buffer, corners[0], u0, v1, light, r, g, b, a);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float partialTicks) {
        return LightTexture.FULL_BRIGHT;
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

    private void emitVertex(VertexConsumer buffer, Vector3f pos, float u, float v, int light,
                            float red, float green, float blue, float alpha) {
        buffer.addVertex(pos.x(), pos.y(), pos.z())
                .setColor(red, green, blue, alpha)
                .setUv(u, v)
                .setLight(light);
    }

    private int getParticleSetting(Level level) {
        if (Minecraft.getInstance().level != level) {
            return 0;
        }
        return Minecraft.getInstance().options.particles().get().getId();
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public ParticleHealSparkle createParticle(SimpleParticleType type, ClientLevel level, double x, double y,
                                                  double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleHealSparkle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
