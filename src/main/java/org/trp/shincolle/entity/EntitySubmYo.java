package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.init.ModItems;

import java.util.List;

public class EntitySubmYo extends EntityShipBase {

    public static final String EQUIP_BASE = "equip_base";
    public static final String EQUIP_NORMAL_BODY = "equip_normal_body";

    private static final float[][] TORPEDO_OFFSETS = {
            {0.1f, 0.35f}, {0.1f, -0.35f}
    };

    public EntitySubmYo(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 45});
        setStateMinor(STATE_MINOR_FACTION_ID, 8);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 18);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 6);
        setStateMinor(STATE_MINOR_RARITY, 2);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
        setEquipFlag(EQUIP_BASE, true);
        setEquipFlag(EQUIP_NORMAL_BODY, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientEffects();
        }
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateClientEffects() {
        if ((this.tickCount % 4) == 0) {
            boolean shouldGlow = checkModelState(0, this.getStateEmotion(0))
                    && !this.isStateNoEquip()
                    && !(this.getIsSitting() && this.getStateEmotion(1) == 4);
            if (shouldGlow) {
                spawnEyeGlowParticles();
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_BASE, "gui.shincolle.equip.base"),
                new EquipOption(EQUIP_NORMAL_BODY, "gui.shincolle.equip.normal_body")
        );
    }

    private void spawnEyeGlowParticles() {
        float radYaw = (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD;
        float zOffset = this.getIsSitting() ? -0.1f : 0.15f;
        float[] left = rotateXZByAxis(zOffset, 0.35f, radYaw, 1.0f);
        float[] right = rotateXZByAxis(zOffset, -0.35f, radYaw, 1.0f);
        double yOffset = this.getIsSitting() ? 1.2D : 1.4D;
        this.level().addParticle(ParticleTypes.END_ROD,
                this.getX() + left[1], this.getY() + yOffset, this.getZ() + left[0],
                0.0D, 0.02D, 0.0D);
        this.level().addParticle(ParticleTypes.END_ROD,
                this.getX() + right[1], this.getY() + yOffset, this.getZ() + right[0],
                0.0D, 0.02D, 0.0D);
    }

    private void updateServerLogic() {
        if (this.isStateRingEffect()) {
            int duration = 40 + this.getLevel();
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            this.addEffect(new MobEffectInstance(MobEffects.GLOWING, duration, 0, false, false));
            if (this.isStateMarried() && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            }
        }
    }

    @Override
    protected boolean performHeavyAttack(Entity target) {
        if (target == null || this.level().isClientSide) {
            return false;
        }
        if (!consumeHeavyAmmo(1)) {
            return false;
        }

        this.setAttackTick(50);
        this.applyEmotesReaction(3);
        spawnTorpedoes(target);
        return true;
    }

    private void spawnTorpedoes(Entity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float damage = Math.max(3.0F, baseDamage * 0.35F);
        float speed = 0.65f;
        int life = 160;
        float explosionRadius = 3.0f;
        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;

        for (float[] offset : TORPEDO_OFFSETS) {
            float[] pos = rotateXZByAxis(offset[0], offset[1], yawRad, 1.0f);
            EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this, target, damage, speed, life, explosionRadius);
            missile.setPos(this.getX() + pos[1], this.getY() + this.getBbHeight() * 0.6D, this.getZ() + pos[0]);
            serverLevel.addFreshEntity(missile);
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.SUBM_YO_SPAWN_EGG.get();
    }
}

