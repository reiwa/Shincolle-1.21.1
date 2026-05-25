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
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.trp.shincolle.entity.base.EntityMountBase;

import org.trp.shincolle.utility.CalcHelper;

public class ParticleLightning extends Particle {
    private static final ParticleRenderType LIGHTNING_RENDER = new ParticleRenderType() {
        @Override
        public BufferBuilder begin(Tesselator tesselator, net.minecraft.client.renderer.texture.TextureManager textureManager) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            return tesselator.begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        }

        @Override
        public String toString() {
            return "SHINCOLLE_LIGHTNING";
        }
    };

    private final int hostEntityId;
    private final int particleType;
    private int numStem = 4;
    private float scaleX = 0.1F;
    private float scaleY = 0.12F;
    private float scaleZ = 0.1F;
    private float stemWidth = 0.01F;
    private double[][] prevShape;

    protected ParticleLightning(ClientLevel level, double x, double y, double z, double scale, int hostEntityId, int particleType) {
        super(level, x, y, z);
        this.setSize(0.0F, 0.0F);
        this.hostEntityId = hostEntityId;
        this.particleType = particleType;

        this.hasPhysics = false;
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
        this.scale((float) scale);

        Entity host = level.getEntity(hostEntityId);
        float hostWidth = host != null ? host.getBbWidth() : 1.0F;
        float hostHeight = host != null ? host.getBbHeight() : 2.0F;

        if (scale <= 0.0) {
            scale = 1.0;
        }

        switch (this.particleType) {
            case 1:
                this.rCol = 1.0F;
                this.gCol = 0.5F;
                this.bCol = 0.7F;
                this.alpha = 1.0F;
                this.numStem = 4;
                this.scaleX = 0.5F + hostWidth * 0.5F;
                this.scaleY = 0.5F + hostWidth * 0.5F;
                this.scaleZ = 0.5F + hostWidth * 0.5F;
                this.stemWidth = 0.01F * hostWidth;
                this.lifetime = 40;
                break;
            case 2:
                this.rCol = 1.0F;
                this.gCol = 0.5F;
                this.bCol = 0.7F;
                this.alpha = 1.0F;
                this.numStem = 12;
                this.scaleX = 0.25F;
                this.scaleY = 0.25F;
                this.scaleZ = 0.25F;
                this.stemWidth = 0.005F;
                this.lifetime = 40;
                break;
            case 3:
                this.rCol = 1.0F;
                this.gCol = 0.5F;
                this.bCol = 0.7F;
                this.alpha = 1.0F;
                this.numStem = 4;
                this.scaleX = 1.0F;
                this.scaleY = 1.0F;
                this.scaleZ = 1.0F;
                this.stemWidth = 0.025F;
                this.lifetime = 40;
                break;
            case 4:
                this.rCol = 0.0F;
                this.gCol = 0.7F;
                this.bCol = 1.0F;
                this.alpha = 1.0F;
                this.numStem = 12;
                this.scaleX = 0.75F;
                this.scaleY = 0.75F;
                this.scaleZ = 0.75F;
                this.stemWidth = 0.008F;
                this.lifetime = 40;
                break;
            case 5:
                this.rCol = 0.0F;
                this.gCol = 0.0F;
                this.bCol = 0.0F;
                this.alpha = 0.0F;
                this.numStem = 4;
                this.scaleX = (float) scale;
                this.scaleY = (float) scale;
                this.scaleZ = (float) scale;
                this.stemWidth = 0.1F;
                this.lifetime = 40;
                break;
            case 6:
                this.rCol = 1.0F;
                this.gCol = 0.5F;
                this.bCol = 0.7F;
                this.alpha = 1.0F;
                this.numStem = 8;
                this.scaleX = 1.75F;
                this.scaleY = 1.75F;
                this.scaleZ = 1.75F;
                this.stemWidth = 0.006F;
                this.lifetime = (int) scale;
                break;
            default:
                this.rCol = 1.0F;
                this.gCol = 0.4F + this.random.nextFloat() * 0.3F;
                this.bCol = 0.4F + this.random.nextFloat() * 0.3F;
                this.alpha = 1.0F;
                this.numStem = 4;
                this.scaleX = 0.1F;
                this.scaleY = 0.12F;
                this.scaleZ = 0.1F;
                this.stemWidth = 0.01F;
                this.lifetime = 20;
                break;
        }

        if (this.particleType != 0) {
            this.prevShape = new double[this.numStem][6];
            updateShape();
        }

        if (host != null) {
            if (this.particleType == 3) {
                updateYamatoPosition();
            } else if (this.particleType == 1 || this.particleType == 4 || this.particleType == 5) {
                float sc = (this.particleType == 1) ? (float) scale : 0.25F;
                this.setPos(host.getX() + (this.random.nextFloat() * sc * 2.0F) - sc,
                            host.getY() + hostHeight * 0.5D + (this.random.nextFloat() * sc * 2.0F) - sc,
                            host.getZ() + (this.random.nextFloat() * sc * 2.0F) - sc);
            } else if (this.particleType == 2) {
                float yaw = host instanceof LivingEntity living ? living.yBodyRot : host.getYRot();
                float[] partPos = rotateXZByAxis(1.0F, 0.0F, -yaw * Mth.DEG_TO_RAD);
                this.setPos(host.getX() + partPos[0],
                            host.getY() + hostHeight * 0.8D,
                            host.getZ() + partPos[1]);
            } else if (this.particleType == 6) {
                this.setPos(host.getX() + (this.random.nextFloat() * 2.0F) - 1.0F,
                            host.getY() + hostHeight * 0.5D + (this.random.nextFloat() * 2.0F) - 1.0F,
                            host.getZ() + (this.random.nextFloat() * 2.0F) - 1.0F);
            } else {
                updatePosition(true);
            }
        }

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
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

        if (this.particleType == 3) {
            updateYamatoPosition();
        } else if (this.particleType == 0) {
            updatePosition(false);
        }

        if (this.particleType != 0 && this.age % 2 == 0) {
            updateShape();
        }

        if (this.particleType == 4) {
            if (this.lifetime - this.age < 6) {
                this.alpha = (this.lifetime - this.age) * 0.15F + 0.2F;
            }
            this.gCol = 0.6F + this.random.nextFloat() * 0.6F;
            this.rCol = this.gCol - 0.3F;
        } else if (this.particleType == 5) {
            this.alpha = this.lifetime - this.age < 10 ? (this.lifetime - this.age) * 0.015F + 0.018F : 0.35F;
            this.gCol = 0.0F + this.random.nextFloat() * 0.1F;
            this.rCol = this.gCol + this.random.nextFloat() * 0.15F;
            this.bCol = this.rCol + this.random.nextFloat() * 0.15F;
        } else if (this.particleType != 0) {
            if (this.lifetime - this.age < 6) {
                this.alpha = (this.lifetime - this.age) * 0.15F + 0.2F;
            }
            this.gCol = 0.4F + this.random.nextFloat() * 0.75F;
            this.bCol = 0.1F + this.gCol;
        }
    }

    private void updateShape() {
        for (int i = 0; i < this.numStem; i++) {
            float offx = (this.random.nextFloat() - 0.5F) * this.scaleX;
            float offy = (this.random.nextFloat() - 0.5F) * this.scaleY;
            float offz = (this.random.nextFloat() - 0.5F) * this.scaleZ;
            if (i == 0) {
                this.prevShape[i][0] = offx;
                this.prevShape[i][1] = offy;
                this.prevShape[i][2] = offz;
                this.prevShape[i][3] = this.prevShape[i][0];
                this.prevShape[i][4] = this.prevShape[i][1];
                this.prevShape[i][5] = this.prevShape[i][2];
                continue;
            }
            if (i == this.numStem - 1) {
                this.prevShape[i][0] = this.prevShape[i - 1][0] + offx;
                this.prevShape[i][1] = this.prevShape[i - 1][1] + offy;
                this.prevShape[i][2] = this.prevShape[i - 1][2] + offz;
                this.prevShape[i][3] = this.prevShape[i][0];
                this.prevShape[i][4] = this.prevShape[i][1];
                this.prevShape[i][5] = this.prevShape[i][2];
                continue;
            }
            this.prevShape[i][0] = this.prevShape[i - 1][0] + offx;
            this.prevShape[i][1] = this.prevShape[i - 1][1] + offy;
            this.prevShape[i][2] = this.prevShape[i - 1][2] + offz;
            this.prevShape[i][3] = this.prevShape[i - 1][3] + offx + this.stemWidth;
            this.prevShape[i][4] = this.prevShape[i - 1][4] + offy + this.stemWidth;
            this.prevShape[i][5] = this.prevShape[i - 1][5] + offz + this.stemWidth;
        }
    }

    private void updateYamatoPosition() {
        Entity host = this.level.getEntity(this.hostEntityId);
        if (host == null || !host.isAlive()) {
            this.remove();
            return;
        }
        float yaw = host instanceof LivingEntity living ? living.yBodyRot : host.getYRot();
        float[] posOffset = CalcHelper.rotateXZByAxis(host.getBbWidth() * 2.0F, 0.0F, yaw * Mth.DEG_TO_RAD, 1.0F);
        this.setPos(host.getX() + posOffset[1],
                    host.getY() + host.getBbHeight() * 0.6D,
                    host.getZ() + posOffset[0]);
    }

    private void updatePosition(boolean initial) {
        Entity host = this.level.getEntity(this.hostEntityId);
        if (host == null || !host.isAlive()) {
            if (this.age > 2) this.remove();
            return;
        }

        float randx = this.random.nextFloat() + 0.1F;
        float yaw = host instanceof LivingEntity living ? living.yBodyRot : host.getYRot();
        float[] newPos = rotateXZByAxis(0.8F + this.random.nextFloat() * 0.2F, randx, -yaw * Mth.DEG_TO_RAD);

        this.x = host.getX() + newPos[0];
        this.y = host.getY() + (initial ? 1.53D : 1.76D) + randx * 0.25D;
        this.z = host.getZ() + newPos[1];

        if (host instanceof EntityMountBase mount) {
            if (mount.getShipDepth() > 0.0) {
                this.y -= 0.08D;
            }
            if (mount.getHost() != null && mount.getHost().isOrderedToSit()) {
                this.y -= 0.23D;
            }
        }
    }

    private float[] rotateXZByAxis(float z, float x, float angle) {
        float cos = Mth.cos(angle);
        float sin = Mth.sin(angle);
        return new float[]{
                z * cos + x * sin,
                x * cos - z * sin
        };
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float px = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float py = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float pz = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        Quaternionf rotation = camera.rotation();
        Vector3f right = new Vector3f(1.0F, 0.0F, 0.0F).rotate(rotation);
        right.y = 0;
        right.normalize();

        if (this.particleType == 0) {
            float cosPitch = Mth.cos(camera.getXRot() * Mth.DEG_TO_RAD);

            for (int i = this.numStem - 1; i >= 0; i--) {
                float offx = (this.random.nextFloat() - 0.5F) * 0.1F * (i + 1);
                float offz = (this.random.nextFloat() - 0.5F) * 0.1F * (i + 1);

                float yOffset = (i == 0) ? (cosPitch * this.scaleY) : (cosPitch * this.scaleY - i * this.scaleY);
                float currentY = py + yOffset;

                float v1x = px + offx + right.x() * this.stemWidth;
                float v1z = pz + offz + right.z() * this.stemWidth;
                float v2x = px + offx - right.x() * this.stemWidth;
                float v2z = pz + offz - right.z() * this.stemWidth;

                buffer.addVertex(v1x, currentY, v1z).setColor(this.rCol, this.gCol, this.bCol, this.alpha);
                buffer.addVertex(v2x, currentY, v2z).setColor(this.rCol, this.gCol, this.bCol, this.alpha);
            }
        } else {
            for (int i = this.numStem - 1; i >= 0; i--) {
                float v1x = px + (float) this.prevShape[i][0];
                float v1y = py + (float) this.prevShape[i][1];
                float v1z = pz + (float) this.prevShape[i][2];
                float v2x = px + (float) this.prevShape[i][3];
                float v2y = py + (float) this.prevShape[i][4];
                float v2z = pz + (float) this.prevShape[i][5];

                buffer.addVertex(v1x, v1y, v1z).setColor(this.rCol, this.gCol, this.bCol, this.alpha);
                buffer.addVertex(v2x, v2y, v2z).setColor(this.rCol, this.gCol, this.bCol, this.alpha);
            }
            for (int i = this.numStem - 1; i >= 0; i--) {
                float v1x = px + (float) this.prevShape[i][3];
                float v1y = py + (float) this.prevShape[i][4];
                float v1z = pz + (float) this.prevShape[i][5];
                float v2x = px + (float) this.prevShape[i][0];
                float v2y = py + (float) this.prevShape[i][1];
                float v2z = pz + (float) this.prevShape[i][2];

                buffer.addVertex(v1x, v1y, v1z).setColor(this.rCol, this.gCol, this.bCol, this.alpha);
                buffer.addVertex(v2x, v2y, v2z).setColor(this.rCol, this.gCol, this.bCol, this.alpha);
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return LIGHTNING_RENDER;
    }

    @Override
    protected int getLightColor(float partialTicks) {
        return LightTexture.FULL_BRIGHT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprites) {}

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double scale, double hostEntityId, double particleType) {
            return new ParticleLightning(level, x, y, z, scale, (int) Math.round(hostEntityId), (int) Math.round(particleType));
        }
    }
}