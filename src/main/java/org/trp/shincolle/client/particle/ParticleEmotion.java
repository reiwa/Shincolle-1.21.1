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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.trp.shincolle.entity.base.EmotionParticleType;

public class ParticleEmotion extends TextureSheetParticle {
    private static final float BASE_SCALE_MIN = 0.275F;
    private static final float BASE_SCALE_RANGE = 0.05F;
    private static final float ICON_SIZE = 1.0F / 16.0F;
    private static final int FADE_TICK_MAX = 5;
    private static final int LIFETIME_FALLBACK = 2000;

    private final SpriteSet sprites;
    private final int emotionId;
    private final int hostEntityId;
    private final float addHeight;
    private final float entType;

    private int playTimes;
    private int fadeTick;
    private int fadeState;
    private int stayTick;
    private int stayTickCount;
    private int frameSize;
    private int frameIndex;
    private int frameMax;
    private float playSpeed;
    private float playSpeedCount;
    private float iconU;
    private float iconV;
    private double addX;
    private double addY;
    private double addZ;

    protected ParticleEmotion(ClientLevel level, double x, double y, double z, float height, int hostEntityId,
                              int emotionId, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.emotionId = emotionId;
        this.hostEntityId = hostEntityId;
        this.addHeight = height;
        this.entType = 1.0F;
        this.playSpeed = 1.0F;
        this.playSpeedCount = 0.0F;
        this.stayTick = 10;
        this.stayTickCount = 0;
        this.fadeTick = 0;
        this.fadeState = 0;
        this.frameSize = 1;
        this.frameIndex = -1;
        this.frameMax = 15;
        this.quadSize = this.random.nextFloat() * BASE_SCALE_RANGE + BASE_SCALE_MIN;
        this.alpha = 0.0F;
        this.hasPhysics = false;
        this.lifetime = LIFETIME_FALLBACK;
        configureForType(emotionId);
        applyIconCoordinates(emotionId);
        this.setSpriteFromAge(sprites);
        calcParticlePosition();
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        updateHostPosition();

        switch (this.fadeState) {
            case 0 -> {
                this.fadeTick++;
                this.alpha = this.fadeTick * (1.0F / FADE_TICK_MAX);
                if (this.fadeTick > FADE_TICK_MAX) {
                    this.fadeState = 1;
                }
            }
            case 1 -> {
                this.playSpeedCount += this.playSpeed;
                this.frameIndex = this.frameSize * (int) this.playSpeedCount;
                this.alpha = 1.0F;
            }
            case 2 -> {
                this.fadeTick--;
                this.alpha = this.fadeTick * (1.0F / FADE_TICK_MAX);
                if (this.fadeTick < 1) {
                    this.remove();
                    return;
                }
            }
            default -> {
                this.remove();
                return;
            }
        }

        int clampedFrame = Math.min(this.frameIndex, this.frameMax);
        if (clampedFrame >= this.frameMax) {
            this.frameIndex = this.frameMax;
            if (this.stayTickCount > this.stayTick) {
                this.frameIndex = this.frameMax + 1;
                this.stayTickCount = 0;
            } else {
                this.stayTickCount++;
            }
        }

        if (this.frameIndex > this.frameMax) {
            if (--this.playTimes <= 0) {
                this.fadeState = 2;
            } else {
                this.frameIndex = 0;
                this.playSpeedCount = 0.0F;
            }
        }
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        if (this.frameIndex < 0) {
            return;
        }
        TextureAtlasSprite sprite = this.sprite;
        float u0 = Mth.lerp(this.iconU, sprite.getU0(), sprite.getU1());
        float u1 = Mth.lerp(this.iconU + ICON_SIZE, sprite.getU0(), sprite.getU1());
        float frameV = this.iconV + (this.frameIndex * ICON_SIZE);
        float v0 = Mth.lerp(frameV, sprite.getV0(), sprite.getV1());
        float v1 = Mth.lerp(frameV + (ICON_SIZE * this.frameSize), sprite.getV0(), sprite.getV1());

        Vec3 cameraPos = camera.getPosition();
        float px = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float py = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float pz = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        float quadSize = this.getQuadSize(partialTicks);
        float yStretch = this.frameSize;
        int light = this.getLightColor(partialTicks);

        Quaternionf rotation = camera.rotation();
        Vector3f[] corners = new Vector3f[]{
                new Vector3f(-1.0F, -yStretch, 0.0F),
                new Vector3f(-1.0F, yStretch, 0.0F),
                new Vector3f(1.0F, yStretch, 0.0F),
                new Vector3f(1.0F, -yStretch, 0.0F)
        };

        for (Vector3f corner : corners) {
            corner.rotate(rotation);
            corner.mul(quadSize);
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

    private void updateHostPosition() {
        if (this.hostEntityId < 0) {
            return;
        }
        Entity host = this.level.getEntity(this.hostEntityId);
        if (host == null || !host.isAlive()) {
            this.remove();
            return;
        }
        this.x = host.getX() + this.addX;
        this.y = host.getY() + this.addY;
        this.z = host.getZ() + this.addZ;
    }

    private void configureForType(int type) {
        switch (type) {
            case 1 -> {
                this.frameMax = 7;
                this.playTimes = 4;
                this.stayTick = 0;
            }
            case 2 -> {
                this.frameMax = 7;
                this.playTimes = 3;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 5;
                this.stayTick = 0;
            }
            case 3 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.fadeTick = 3;
            }
            case 4 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.fadeTick = 3;
                this.stayTick = 20;
            }
            case 5 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.stayTick = 20;
                this.playSpeed = 0.5F;
            }
            case 6 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.fadeTick = 3;
            }
            case 7 -> {
                this.frameMax = 15;
                this.playTimes = 1;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 3;
                this.stayTick = 3;
                this.playSpeed = 0.7F;
            }
            case 8 -> {
                this.frameMax = 7;
                this.playTimes = 3;
                this.fadeTick = 3;
                this.stayTick = 0;
                this.playSpeed = 0.5F;
            }
            case 9 -> {
                this.frameMax = 7;
                this.playTimes = 2;
                this.fadeTick = 3;
                this.stayTick = 1;
                this.playSpeed = 0.5F;
            }
            case 10 -> {
                this.frameMax = 7;
                this.playTimes = 4;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 3;
                this.stayTick = 1;
            }
            case 11 -> {
                this.frameMax = 7;
                this.playTimes = 2;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 3;
                this.stayTick = 0;
                this.playSpeed = 0.75F;
            }
            case 12 -> {
                this.frameMax = 14;
                this.playTimes = 1;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 3;
                this.stayTick = 20;
                this.playSpeed = 0.75F;
                this.frameSize = 2;
            }
            case 13 -> {
                this.frameMax = 7;
                this.playTimes = 2;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 3;
                this.stayTick = 0;
                this.playSpeed = 0.75F;
            }
            case 14 -> {
                this.frameMax = 7;
                this.playTimes = 2;
                this.fadeTick = 3;
                this.stayTick = 0;
            }
            case 15 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.fadeTick = 3;
                this.stayTick = 15;
                this.playSpeed = 0.7F;
            }
            case 16 -> {
                this.frameMax = 7;
                this.playTimes = 3;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 3;
                this.stayTick = 0;
            }
            case 17 -> {
                this.frameMax = 15;
                this.playTimes = 1;
                this.fadeTick = 3;
                this.playSpeed = 0.5F;
            }
            case 18 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.stayTick = 0;
                this.playSpeed = 0.4F;
            }
            case 19 -> {
                this.frameMax = 7;
                this.playTimes = 3;
                this.alpha = 1.0F;
                this.fadeState = 1;
                this.fadeTick = 3;
                this.stayTick = 0;
                this.playSpeed = 0.75F;
            }
            case 20 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.fadeTick = 3;
                this.stayTick = 20;
                this.playSpeed = 0.5F;
            }
            case 21, 22, 23, 24, 25, 26, 27, 28 -> {
                this.frameMax = 0;
                this.playTimes = 1;
                this.stayTick = 40;
            }
            case 29 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.fadeTick = 3;
                this.playSpeed = 0.35F;
                this.stayTick = 20;
            }
            case 30 -> {
                this.frameMax = 7;
                this.playTimes = 1;
                this.fadeTick = 3;
                this.playSpeed = 0.75F;
                this.stayTick = 3;
            }
            case 31 -> {
                this.frameMax = 3;
                this.quadSize += 0.2F;
                this.playTimes = 1;
                this.fadeTick = 3;
                this.playSpeed = 0.75F;
                this.stayTick = 30;
            }
            case 32 -> {
                this.frameMax = 5;
                this.playTimes = 4;
                this.playSpeed = 0.75F;
                this.stayTick = 0;
            }
            case 33 -> {
                this.frameMax = 4;
                this.playTimes = 1;
                this.playSpeed = 0.25F;
                this.stayTick = 30;
            }
            case 34 -> {
                this.frameMax = 0;
                this.quadSize += 0.3F;
                this.playTimes = 1;
                this.stayTick = 50;
            }
            default -> {
                this.frameMax = 15;
                this.playTimes = 1;
            }
        }
    }

    private void applyIconCoordinates(int type) {
        EmotionParticleType resolved = EmotionParticleType.fromId(type);
        this.iconU = resolved.getIconColumn() * ICON_SIZE;
        this.iconV = resolved.getIconRow() * ICON_SIZE;
    }

    private void calcParticlePosition() {
        Player player = Minecraft.getInstance().player;
        float angle = 0.0F;
        if (player != null) {
            angle = player.getYRot() * (float) Math.PI / 180.0F;
        }

        double baseX = 0.0D;
        double baseY = 0.0D;
        double baseZ = 0.0D;

        if (this.entType == 1.0F) {
            float frontDist = 0.7F;
            float leftDist = -0.2F;
            switch (this.emotionId) {
                case 12 -> {
                    leftDist = 0.0F;
                    baseY += 0.6D;
                }
                case 15 -> {
                    frontDist = 1.5F;
                    leftDist = -0.7F;
                }
                case 19 -> {
                    frontDist = 1.4F;
                    leftDist = -1.1F;
                }
                case 34 -> {
                    frontDist = -0.2F;
                    leftDist = 0.0F;
                    baseY -= 0.2D;
                }
                default -> {
                }
            }
            double[] rotated = rotateXZ(frontDist, leftDist, angle);
            baseX += rotated[1];
            baseY -= 0.2D;
            baseZ += rotated[0];
        } else {
            double[] rotated = rotateXZ(0.0F, -0.2F, angle);
            baseX += rotated[1];
            baseY += 0.5D;
            baseZ += rotated[0];
        }

        double addX2 = 0.0D;
        double addY2 = 0.0D;
        double addZ2 = 0.0D;
        if (this.addHeight > 2.0F) {
            this.quadSize += 1.0F;
            addX2 = 1.2D;
            addY2 = 1.5D;
            addZ2 = 0.5D;
        }

        double[] rotated;
        switch (this.emotionId) {
            case 2 -> {
                rotated = rotateXZ(-0.2F - (float) addZ2, this.random.nextFloat() * 0.3F - 1.0F - (float) addX2,
                        angle);
                baseX += rotated[1];
                baseY = baseY + this.random.nextDouble() * this.addHeight * 0.2D + this.addHeight * 1.8D + addY2;
                baseZ += rotated[0];
            }
            case 15 -> {
                rotated = rotateXZ(this.random.nextFloat() * 0.1F - 0.7F - (float) addX2,
                        this.random.nextFloat() * 0.1F + 0.2F + (float) addZ2, angle);
                baseX += rotated[1];
                baseY = baseY + this.random.nextDouble() * this.addHeight * 0.2D + this.addHeight * 1.6D + addY2;
                baseZ += rotated[0];
            }
            case 34 -> {
                rotated = rotateXZ(0.15F, 0.0F, angle);
                baseX += rotated[1];
                baseY = baseY + this.random.nextDouble() * this.addHeight * 0.15D + this.addHeight * 1.9D + addY2;
                baseZ += rotated[0];
            }
            default -> {
                rotated = rotateXZ(0.5F - (float) addZ2, this.random.nextFloat() * 0.3F + 0.7F + (float) addX2,
                        angle);
                baseX += rotated[1];
                baseY = baseY + this.random.nextDouble() * this.addHeight * 0.5D + this.addHeight * 1.5D + addY2;
                baseZ += rotated[0];
            }
        }

        this.addX = baseX;
        this.addY = baseY;
        this.addZ = baseZ;
        this.x += this.addX;
        this.y += this.addY;
        this.z += this.addZ;
    }

    private double[] rotateXZ(float x, float z, float angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double rx = x * cos - z * sin;
        double rz = x * sin + z * cos;
        return new double[]{rx, rz};
    }

    public static class Provider implements ParticleProvider<net.minecraft.core.particles.SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public ParticleEmotion createParticle(net.minecraft.core.particles.SimpleParticleType type, ClientLevel level,
                                              double x, double y, double z, double xSpeed, double ySpeed,
                                              double zSpeed) {
            float height = (float) xSpeed;
            int hostEntityId = ySpeed >= 0.0D ? (int) Math.round(ySpeed) : -1;
            int emotionId = (int) Math.round(zSpeed);
            return new ParticleEmotion(level, x, y, z, height, hostEntityId, emotionId, this.sprites);
        }
    }
}
