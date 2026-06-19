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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ParticleCube extends Particle {
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
            return "SHINCOLLE_CUBE";
        }
    };

    private final int particleType;
    private float quadSize;
    private float shotYaw;
    private float shotPitch;
    private float scaleOut;
    private float scaleIn;
    private float alphaOut;
    private float alphaIn;
    private final double par1;
    private final double par2;
    private final double par3;
    private final int hostEntityId;

    public ParticleCube(ClientLevel level, double x, double y, double z, float scale, int type, int hostEntityId, double par1, double par2, double par3) {
        super(level, x, y, z);
        this.setSize(0.0F, 0.0F);
        this.hostEntityId = hostEntityId;
        this.xd = 0.0D;
        this.yd = 0.0D;
        this.zd = 0.0D;
        this.quadSize = scale;
        this.particleType = type;
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.hasPhysics = false;

        if (type == 1) {
            this.lifetime = 30;
            this.rCol = 1.0F;
            this.gCol = 0.8F;
            this.bCol = 0.9F;
        } else {
            this.quadSize = (float) par1;
            this.lifetime = 40;
            this.rCol = 1.0F;
            this.gCol = 0.8F;
            this.bCol = 0.9F;
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

        if (this.particleType == 1) {
            Vec3 look = host.getViewVector(1.0F);
            float[] lookDeg = org.trp.shincolle.utility.CalcHelper.getLookDegree(look.x, look.y, look.z, false);
            float[] posOffset = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(0.0F, 0.0F, host.getBbWidth() * 2.0F, lookDeg[0], lookDeg[1], 1.0F);
            this.setPos(host.getX() + posOffset[0], host.getY() + host.getBbHeight() * 0.6D, host.getZ() + posOffset[2]);
            this.shotYaw = lookDeg[0];
            this.shotPitch = lookDeg[1];
            this.alphaIn = this.age > 20 ? 1.0F + (20 - this.age) * 0.1F : (this.age < 4 ? 0.2F + this.age * 0.2F : 0.95F);
            this.alphaOut = 0.0F;
            if (this.age > 20) {
                this.scaleOut = this.quadSize * (1.0F + (this.age - 20));
                this.scaleIn = this.quadSize * 0.4F * (1.0F - (this.age - 20) * 0.1F);
            } else if (this.age < 8) {
                this.scaleOut = this.quadSize * 0.3F * (this.age * 0.3F);
                this.scaleIn = this.quadSize * 0.4F * (this.age * 0.125F);
            } else {
                this.scaleOut = this.quadSize;
                this.scaleIn = this.quadSize * 0.4F;
            }
            this.scaleOut += this.random.nextFloat() * 0.04F - 0.01F;
            this.scaleIn += this.random.nextFloat() * 0.04F - 0.005F;
        } else {
            float yaw = host instanceof LivingEntity living ? living.yBodyRot : host.getYRot();
            float pitch = host.getXRot();
            float[] posOffset = org.trp.shincolle.utility.CalcHelper.rotateXZByAxis(host.getBbWidth() * 2.0F, 0.0F, yaw * Mth.DEG_TO_RAD, 1.0F);
            this.setPos(host.getX() + posOffset[1], host.getY() + host.getBbHeight() * 0.6D, host.getZ() + posOffset[0]);
            this.shotYaw = yaw * Mth.DEG_TO_RAD;
            this.shotPitch = pitch * Mth.DEG_TO_RAD;
            this.alphaIn = this.age < 32 ? this.random.nextFloat() * 0.5F + 0.75F : (this.lifetime - this.age) * 0.1F + 0.2F;
            this.alphaOut = this.alphaIn * 0.25F;
            this.scaleOut = this.quadSize * this.age * ((Mth.cos(this.age) + 1.0F) * 0.005F + 0.015F);
            this.scaleIn = this.scaleOut * 0.75F;
            this.scaleOut += this.random.nextFloat() * 0.04F - 0.01F;
            this.scaleIn += this.random.nextFloat() * 0.04F - 0.005F;
        }
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        if (this.age <= 1) {
            return;
        }

        float[] v1 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, -1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v2 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, 1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v3 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, 1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v4 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, -1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v5 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, -1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v6 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, 1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v7 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, 1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleOut);
        float[] v8 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, -1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleOut);

        float[] t1 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, -1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] t2 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, 1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] t3 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, 1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] t4 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, -1.0F, -1.0F, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] t5 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, -1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] t6 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(-1.0F, 1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] t7 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, 1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleIn);
        float[] t8 = org.trp.shincolle.utility.CalcHelper.rotateXYZByYawPitch(1.0F, -1.0F, 1.0F, this.shotYaw, this.shotPitch, this.scaleIn);

        Vec3 cameraPos = camera.getPosition();
        double hx = Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x();
        double hy = Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y();
        double hz = Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z();

        double[][] vt = new double[8][3];
        double[][] vt2 = new double[8][3];

        vt[0][0] = hx + v1[0]; vt[0][1] = hy + v1[1]; vt[0][2] = hz + v1[2];
        vt[1][0] = hx + v2[0]; vt[1][1] = hy + v2[1]; vt[1][2] = hz + v2[2];
        vt[2][0] = hx + v3[0]; vt[2][1] = hy + v3[1]; vt[2][2] = hz + v3[2];
        vt[3][0] = hx + v4[0]; vt[3][1] = hy + v4[1]; vt[3][2] = hz + v4[2];
        vt[4][0] = hx + v5[0]; vt[4][1] = hy + v5[1]; vt[4][2] = hz + v5[2];
        vt[5][0] = hx + v6[0]; vt[5][1] = hy + v6[1]; vt[5][2] = hz + v6[2];
        vt[6][0] = hx + v7[0]; vt[6][1] = hy + v7[1]; vt[6][2] = hz + v7[2];
        vt[7][0] = hx + v8[0]; vt[7][1] = hy + v8[1]; vt[7][2] = hz + v8[2];

        vt2[0][0] = hx + t1[0]; vt2[0][1] = hy + t1[1]; vt2[0][2] = hz + t1[2];
        vt2[1][0] = hx + t2[0]; vt2[1][1] = hy + t2[1]; vt2[1][2] = hz + t2[2];
        vt2[2][0] = hx + t3[0]; vt2[2][1] = hy + t3[1]; vt2[2][2] = hz + t3[2];
        vt2[3][0] = hx + t4[0]; vt2[3][1] = hy + t4[1]; vt2[3][2] = hz + t4[2];
        vt2[4][0] = hx + t5[0]; vt2[4][1] = hy + t5[1]; vt2[4][2] = hz + t5[2];
        vt2[5][0] = hx + t6[0]; vt2[5][1] = hy + t6[1]; vt2[5][2] = hz + t6[2];
        vt2[6][0] = hx + t7[0]; vt2[6][1] = hy + t7[1]; vt2[6][2] = hz + t7[2];
        vt2[7][0] = hx + t8[0]; vt2[7][1] = hy + t8[1]; vt2[7][2] = hz + t8[2];

        buffer.addVertex((float) vt2[7][0], (float) vt2[7][1], (float) vt2[7][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[6][0], (float) vt2[6][1], (float) vt2[6][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[5][0], (float) vt2[5][1], (float) vt2[5][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[4][0], (float) vt2[4][1], (float) vt2[4][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);

        buffer.addVertex((float) vt2[3][0], (float) vt2[3][1], (float) vt2[3][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[2][0], (float) vt2[2][1], (float) vt2[2][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[6][0], (float) vt2[6][1], (float) vt2[6][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[7][0], (float) vt2[7][1], (float) vt2[7][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);

        buffer.addVertex((float) vt2[0][0], (float) vt2[0][1], (float) vt2[0][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[1][0], (float) vt2[1][1], (float) vt2[1][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[2][0], (float) vt2[2][1], (float) vt2[2][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[3][0], (float) vt2[3][1], (float) vt2[3][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);

        buffer.addVertex((float) vt2[4][0], (float) vt2[4][1], (float) vt2[4][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[5][0], (float) vt2[5][1], (float) vt2[5][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[1][0], (float) vt2[1][1], (float) vt2[1][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[0][0], (float) vt2[0][1], (float) vt2[0][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);

        buffer.addVertex((float) vt2[2][0], (float) vt2[2][1], (float) vt2[2][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[1][0], (float) vt2[1][1], (float) vt2[1][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[5][0], (float) vt2[5][1], (float) vt2[5][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[6][0], (float) vt2[6][1], (float) vt2[6][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);

        buffer.addVertex((float) vt2[3][0], (float) vt2[3][1], (float) vt2[3][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[7][0], (float) vt2[7][1], (float) vt2[7][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[4][0], (float) vt2[4][1], (float) vt2[4][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);
        buffer.addVertex((float) vt2[0][0], (float) vt2[0][1], (float) vt2[0][2]).setColor(1.0F, 1.0F, 1.0F, this.alphaIn);

        buffer.addVertex((float) vt[7][0], (float) vt[7][1], (float) vt[7][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[6][0], (float) vt[6][1], (float) vt[6][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[5][0], (float) vt[5][1], (float) vt[5][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[4][0], (float) vt[4][1], (float) vt[4][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);

        buffer.addVertex((float) vt[3][0], (float) vt[3][1], (float) vt[3][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[2][0], (float) vt[2][1], (float) vt[2][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[6][0], (float) vt[6][1], (float) vt[6][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[7][0], (float) vt[7][1], (float) vt[7][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);

        buffer.addVertex((float) vt[0][0], (float) vt[0][1], (float) vt[0][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[1][0], (float) vt[1][1], (float) vt[1][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[2][0], (float) vt[2][1], (float) vt[2][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[3][0], (float) vt[3][1], (float) vt[3][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);

        buffer.addVertex((float) vt[4][0], (float) vt[4][1], (float) vt[4][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[5][0], (float) vt[5][1], (float) vt[5][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[1][0], (float) vt[1][1], (float) vt[1][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[0][0], (float) vt[0][1], (float) vt[0][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);

        buffer.addVertex((float) vt[2][0], (float) vt[2][1], (float) vt[2][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[1][0], (float) vt[1][1], (float) vt[1][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[5][0], (float) vt[5][1], (float) vt[5][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[6][0], (float) vt[6][1], (float) vt[6][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);

        buffer.addVertex((float) vt[3][0], (float) vt[3][1], (float) vt[3][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[7][0], (float) vt[7][1], (float) vt[7][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[4][0], (float) vt[4][1], (float) vt[4][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
        buffer.addVertex((float) vt[0][0], (float) vt[0][1], (float) vt[0][2]).setColor(this.rCol, this.gCol, this.bCol, this.alphaOut);
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
            return new ParticleCube(level, x, y, z, (float) scale, (int) Math.round(particleType), (int) Math.round(hostEntityId), 1.0D, 0.0D, 0.0D);
        }
    }
}
