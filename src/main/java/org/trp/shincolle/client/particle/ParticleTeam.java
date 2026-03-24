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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.*;

public class ParticleTeam extends TextureSheetParticle {
    private static final int DEFAULT_LIFETIME = 30;
    private static final int FOLLOW_LIFETIME = 20 * 60 * 60;
    private static final float FOLLOW_SMOOTH_FACTOR = 0.35F;
    private static final float DEFAULT_SCALE = 0.35F;
    private static final float BASE_SCALE_MULTIPLIER = 3.0F;
    private static final float DEFAULT_HEIGHT = 1.5F;
    private static final float TOP_OFFSET = 1.3F;
    private static final float BASE_OFFSET = 0.26F;

    private static final EnumMap<FollowKind, Map<Integer, ParticleTeam>> FOLLOW_PARTICLES = new EnumMap<>(
            FollowKind.class);

    private final SpriteSet sprites;
    private float markerHeight;
    private final RenderStyle renderStyle;
    private final int followEntityId;
    private final FollowKind followKind;

    static {
        for (FollowKind kind : FollowKind.values()) {
            FOLLOW_PARTICLES.put(kind, new HashMap<>());
        }
    }

    protected ParticleTeam(ClientLevel level, double x, double y, double z, double markerHeight, SpriteSet sprites,
                           RenderStyle renderStyle, int followEntityId, FollowKind followKind) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.followEntityId = followEntityId;
        this.followKind = followKind == null ? FollowKind.NONE : followKind;
        this.lifetime = this.followEntityId >= 0 ? FOLLOW_LIFETIME : DEFAULT_LIFETIME;
        this.quadSize = DEFAULT_SCALE;
        this.alpha = 1.0F;
        this.hasPhysics = false;
        this.markerHeight = markerHeight > 0.0D ? (float) markerHeight : DEFAULT_HEIGHT;
        this.renderStyle = renderStyle == null ? RenderStyle.DEFAULT_GREEN : renderStyle;
        this.rCol = this.renderStyle.colorR;
        this.gCol = this.renderStyle.colorG;
        this.bCol = this.renderStyle.colorB;
        this.setSpriteFromAge(sprites);
        registerFollowParticle();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.removed) {
            return;
        }
        if (this.followEntityId >= 0) {
            Entity followEntity = this.level.getEntity(this.followEntityId);
            if (followEntity == null || !followEntity.isAlive()) {
                this.remove();
                return;
            }
            Vec3 pos = followEntity.position();
            this.x = Mth.lerp(FOLLOW_SMOOTH_FACTOR, this.x, pos.x);
            this.y = Mth.lerp(FOLLOW_SMOOTH_FACTOR, this.y, pos.y);
            this.z = Mth.lerp(FOLLOW_SMOOTH_FACTOR, this.z, pos.z);
            this.markerHeight = followEntity.getBbHeight() > 0.0F ? followEntity.getBbHeight() : DEFAULT_HEIGHT;
        }
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
        TextureAtlasSprite sprite = this.sprite;
        float u0 = sprite.getU0();
        float u1 = sprite.getU1();
        float v0 = sprite.getV0();
        float v1 = sprite.getV1();
        float vMid = Mth.lerp(0.5F, v0, v1);

        Vec3 cameraPos = camera.getPosition();
        float x = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float y = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float z = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        float quadSize = this.getQuadSize(partialTicks);
        float topY = y + this.markerHeight + this.renderStyle.topOffset;
        float baseY = y + this.renderStyle.baseOffset;
        int light = this.getLightColor(partialTicks);

        if (this.renderStyle.topAlpha > 0.0F) {
            renderBillboard(buffer, camera, x, topY, z, quadSize, u0, u1, v0, vMid, light,
                    this.renderStyle.topAlpha);
        }
        if (this.renderStyle.baseAlpha > 0.0F) {
            renderBase(buffer, x, baseY, z, quadSize * this.renderStyle.baseScaleMultiplier, u0, u1, vMid, v1, light,
                    this.renderStyle.baseAlpha);
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

    private void renderBillboard(VertexConsumer buffer, Camera camera, float x, float y, float z, float scale,
                                 float u0, float u1, float v0, float v1, int light, float alpha) {
        Quaternionf rotation = camera.rotation();
        Vector3f[] corners = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, -1.0F, 0.0F)
        };

        for (Vector3f corner : corners) {
            corner.rotate(rotation);
            corner.mul(scale);
            corner.add(x, y, z);
        }

        emitVertex(buffer, corners[0], u1, v1, light, alpha);
        emitVertex(buffer, corners[1], u1, v0, light, alpha);
        emitVertex(buffer, corners[2], u0, v0, light, alpha);
        emitVertex(buffer, corners[3], u0, v1, light, alpha);

        emitVertex(buffer, corners[3], u1, v1, light, alpha);
        emitVertex(buffer, corners[2], u1, v0, light, alpha);
        emitVertex(buffer, corners[1], u0, v0, light, alpha);
        emitVertex(buffer, corners[0], u0, v1, light, alpha);
    }

    private void renderBase(VertexConsumer buffer, float x, float y, float z, float halfScale,
                            float u0, float u1, float v0, float v1, int light, float alpha) {
        emitVertex(buffer, x + halfScale, y, z + halfScale, u1, v1, light, alpha);
        emitVertex(buffer, x + halfScale, y, z - halfScale, u1, v0, light, alpha);
        emitVertex(buffer, x - halfScale, y, z - halfScale, u0, v0, light, alpha);
        emitVertex(buffer, x - halfScale, y, z + halfScale, u0, v1, light, alpha);

        emitVertex(buffer, x + halfScale, y, z - halfScale, u1, v1, light, alpha);
        emitVertex(buffer, x + halfScale, y, z + halfScale, u1, v0, light, alpha);
        emitVertex(buffer, x - halfScale, y, z + halfScale, u0, v0, light, alpha);
        emitVertex(buffer, x - halfScale, y, z - halfScale, u0, v1, light, alpha);
    }

    private void emitVertex(VertexConsumer buffer, Vector3f pos, float u, float v, int light, float alpha) {
        emitVertex(buffer, pos.x(), pos.y(), pos.z(), u, v, light, alpha);
    }

    private void emitVertex(VertexConsumer buffer, float x, float y, float z, float u, float v, int light, float alpha) {
        buffer.addVertex(x, y, z)
                .setColor(this.rCol, this.gCol, this.bCol, alpha)
                .setUv(u, v)
                .setLight(light);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        private final RenderStyle renderStyle;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
            this.renderStyle = RenderStyle.DEFAULT_GREEN;
        }

        public Provider(SpriteSet sprites, RenderStyle renderStyle) {
            this.sprites = sprites;
            this.renderStyle = renderStyle == null ? RenderStyle.DEFAULT_GREEN : renderStyle;
        }

        @Override
        public ParticleTeam createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                           double xSpeed, double ySpeed, double zSpeed) {
            int followEntityId = ySpeed >= 0.0D ? (int) Math.round(ySpeed) : -1;
            int kindId = (int) Math.round(zSpeed);
            FollowKind followKind = FollowKind.fromMarkerId(kindId);
            return new ParticleTeam(level, x, y, z, xSpeed, this.sprites, this.renderStyle, followEntityId,
                    followKind);
        }
    }

    public static ParticleTeam getFollowParticle(FollowKind kind, int entityId) {
        if (kind == null || kind == FollowKind.NONE) {
            return null;
        }
        return FOLLOW_PARTICLES.get(kind).get(entityId);
    }

    public static void removeFollowParticle(FollowKind kind, int entityId) {
        if (kind == null || kind == FollowKind.NONE) {
            return;
        }
        ParticleTeam particle = FOLLOW_PARTICLES.get(kind).remove(entityId);
        if (particle != null) {
            particle.remove();
        }
    }

    public static void clearFollowParticles(FollowKind kind, Set<Integer> keepEntityIds) {
        if (kind == null || kind == FollowKind.NONE) {
            return;
        }
        Map<Integer, ParticleTeam> particles = FOLLOW_PARTICLES.get(kind);
        if (keepEntityIds == null || keepEntityIds.isEmpty()) {
            List<ParticleTeam> snapshot = new ArrayList<>(particles.values());
            particles.clear();
            for (ParticleTeam particle : snapshot) {
                particle.remove();
            }
            return;
        }
        List<Map.Entry<Integer, ParticleTeam>> snapshot = new ArrayList<>(particles.entrySet());
        for (Map.Entry<Integer, ParticleTeam> entry : snapshot) {
            if (keepEntityIds.contains(entry.getKey())) {
                continue;
            }
            particles.remove(entry.getKey());
            entry.getValue().remove();
        }
    }

    public static void clearAllFollowParticles() {
        for (FollowKind kind : FollowKind.values()) {
            if (kind == FollowKind.NONE) {
                continue;
            }
            clearFollowParticles(kind, null);
        }
    }

    public boolean isAliveParticle() {
        return !this.removed;
    }

    public RenderStyle getRenderStyle() {
        return this.renderStyle;
    }

    public FollowKind getFollowKind() {
        return this.followKind;
    }

    private void registerFollowParticle() {
        if (this.followEntityId < 0 || this.followKind == FollowKind.NONE) {
            return;
        }
        Map<Integer, ParticleTeam> particles = FOLLOW_PARTICLES.get(this.followKind);
        ParticleTeam existing = particles.put(this.followEntityId, this);
        if (existing != null && existing != this) {
            existing.remove();
        }
    }

    @Override
    public void remove() {
        super.remove();
        if (this.followEntityId < 0 || this.followKind == FollowKind.NONE) {
            return;
        }
        Map<Integer, ParticleTeam> particles = FOLLOW_PARTICLES.get(this.followKind);
        ParticleTeam existing = particles.get(this.followEntityId);
        if (existing == this) {
            particles.remove(this.followEntityId);
        }
    }

    public enum FollowKind {
        NONE(0),
        SHIP_MARKER(1),
        TARGET_ENTITY(2);

        private final int markerId;

        FollowKind(int markerId) {
            this.markerId = markerId;
        }

        public int getMarkerId() {
            return this.markerId;
        }

        public static FollowKind fromMarkerId(int markerId) {
            for (FollowKind kind : values()) {
                if (kind.markerId == markerId) {
                    return kind;
                }
            }
            return NONE;
        }
    }

    public static final class RenderStyle {
        public static final RenderStyle DEFAULT_GREEN = new RenderStyle(
                0.0F, 1.0F, 0.0F,
                TOP_OFFSET, BASE_OFFSET,
                0.95F, 0.45F,
                BASE_SCALE_MULTIPLIER
        );

        public static final RenderStyle DEFAULT_BLUE = new RenderStyle(
                0.2F, 0.6F, 1.0F,
                TOP_OFFSET, BASE_OFFSET,
                0.95F, 0.45F,
                BASE_SCALE_MULTIPLIER
        );

        public static final RenderStyle SELECTED_RED = new RenderStyle(
                1.0F, 0.2F, 0.2F,
                TOP_OFFSET, BASE_OFFSET,
                0.95F, 0.45F,
                BASE_SCALE_MULTIPLIER
        );

        public static final RenderStyle TARGET_WHITE = new RenderStyle(
                1.0F, 1.0F, 1.0F,
                0.0F, 0.0F,
                0.95F, 0.45F,
                BASE_SCALE_MULTIPLIER
        );

        public static final RenderStyle TARGET_RED = new RenderStyle(
                1.0F, 0.2F, 0.2F,
                0.0F, 0.0F,
                0.95F, 0.45F,
                BASE_SCALE_MULTIPLIER
        );

        public final float colorR;
        public final float colorG;
        public final float colorB;
        public final float topOffset;
        public final float baseOffset;
        public final float topAlpha;
        public final float baseAlpha;
        public final float baseScaleMultiplier;

        public RenderStyle(float colorR, float colorG, float colorB,
                           float topOffset, float baseOffset,
                           float topAlpha, float baseAlpha,
                           float baseScaleMultiplier) {
            this.colorR = Mth.clamp(colorR, 0.0F, 1.0F);
            this.colorG = Mth.clamp(colorG, 0.0F, 1.0F);
            this.colorB = Mth.clamp(colorB, 0.0F, 1.0F);
            this.topOffset = topOffset;
            this.baseOffset = baseOffset;
            this.topAlpha = Mth.clamp(topAlpha, 0.0F, 1.0F);
            this.baseAlpha = Mth.clamp(baseAlpha, 0.0F, 1.0F);
            this.baseScaleMultiplier = baseScaleMultiplier;
        }
    }
}
