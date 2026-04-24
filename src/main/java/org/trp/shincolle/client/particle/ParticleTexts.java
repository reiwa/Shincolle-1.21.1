package org.trp.shincolle.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ParticleTexts extends TextureSheetParticle {
    private static final int TYPE_COUNT = 5;
    private static final float TYPE_ROW_HEIGHT = 1.0F / TYPE_COUNT;
    private static final float BASE_SCALE = 0.34F;
    private static final float TEXT_ASPECT_Y = 0.22F;
    private static final int BASE_LIFETIME = 26;

    private final SpriteSet sprites;
    private final int textType;

    protected ParticleTexts(ClientLevel level, double x, double y, double z, int textType, double riseSpeed,
                            double spread, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.textType = Mth.clamp(textType, 0, TYPE_COUNT - 1);

        this.lifetime = BASE_LIFETIME + this.random.nextInt(6);
        this.quadSize = BASE_SCALE + this.random.nextFloat() * 0.06F;
        this.alpha = 0.0F;
        this.friction = 0.93F;
        this.gravity = 0.0F;
        this.hasPhysics = false;

        double spreadScale = Math.max(0.0D, spread);
        this.x += (this.random.nextDouble() - 0.5D) * spreadScale;
        this.y += this.random.nextDouble() * 0.35D + 0.15D;
        this.z += (this.random.nextDouble() - 0.5D) * spreadScale;

        this.xd = (this.random.nextDouble() - 0.5D) * 0.02D;
        this.yd = Math.max(0.02D, riseSpeed);
        this.zd = (this.random.nextDouble() - 0.5D) * 0.02D;

        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.removed) {
            return;
        }

        this.setSpriteFromAge(this.sprites);
        float progress = this.lifetime <= 0 ? 1.0F : (float) this.age / (float) this.lifetime;
        if (progress < 0.2F) {
            this.alpha = progress / 0.2F;
        } else if (progress > 0.8F) {
            this.alpha = (1.0F - progress) / 0.2F;
        } else {
            this.alpha = 1.0F;
        }
        this.alpha = Mth.clamp(this.alpha, 0.0F, 1.0F);
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        TextureAtlasSprite sprite = this.sprite;
        float u0 = sprite.getU0();
        float u1 = sprite.getU1();

        float rowMin = this.textType * TYPE_ROW_HEIGHT;
        float rowMax = rowMin + TYPE_ROW_HEIGHT;
        float v0 = Mth.lerp(rowMin, sprite.getV0(), sprite.getV1());
        float v1 = Mth.lerp(rowMax, sprite.getV0(), sprite.getV1());

        Vec3 cameraPos = camera.getPosition();
        float px = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float py = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float pz = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        float scale = this.getQuadSize(partialTicks);
        int light = this.getLightColor(partialTicks);

        Quaternionf rotation = camera.rotation();
        Vector3f[] corners = new Vector3f[]{
            new Vector3f(-1.0F, -TEXT_ASPECT_Y, 0.0F),
            new Vector3f(-1.0F, TEXT_ASPECT_Y, 0.0F),
            new Vector3f(1.0F, TEXT_ASPECT_Y, 0.0F),
            new Vector3f(1.0F, -TEXT_ASPECT_Y, 0.0F)
        };

        for (Vector3f corner : corners) {
            corner.rotate(rotation);
            corner.mul(scale);
            corner.add(px, py, pz);
        }

        emitVertex(buffer, corners[0], u1, v1, light);
        emitVertex(buffer, corners[1], u1, v0, light);
        emitVertex(buffer, corners[2], u0, v0, light);
        emitVertex(buffer, corners[3], u0, v1, light);

        emitVertex(buffer, corners[3], u1, v1, light);
        emitVertex(buffer, corners[2], u1, v0, light);
        emitVertex(buffer, corners[1], u0, v0, light);
        emitVertex(buffer, corners[0], u0, v1, light);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float partialTicks) {
        return LightTexture.FULL_BRIGHT;
    }

    private void emitVertex(VertexConsumer buffer, Vector3f pos, float u, float v, int light) {
        buffer.addVertex(pos.x(), pos.y(), pos.z())
                .setColor(this.rCol, this.gCol, this.bCol, this.alpha)
                .setUv(u, v)
                .setLight(light);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public ParticleTexts createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                            double xSpeed, double ySpeed, double zSpeed) {
            int textType = (int) Math.round(xSpeed);
            double rise = ySpeed > 0.0D ? ySpeed : 0.045D;
            double spread = zSpeed > 0.0D ? zSpeed : 0.85D;
            return new ParticleTexts(level, x, y, z, textType, rise, spread, this.sprites);
        }
    }
}
