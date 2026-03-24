package org.trp.shincolle.entity;

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

public class EntitySubmU511 extends EntityShipBase {

    public static final String EQUIP_BASE = "equip_base";
    public static final String EQUIP_HAT = "equip_hat";
    public static final String EQUIP_PIPE = "equip_pipe";

    private static final float[][] TORPEDO_OFFSETS = {
            {0.1f, 0.0f}
    };

    public EntitySubmU511(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 20, 0, 45});
        setStateMinor(STATE_MINOR_FACTION_ID, 8);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 38);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 6);
        setStateMinor(STATE_MINOR_RARITY, 3);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
        setEquipFlag(EQUIP_BASE, true);
        setEquipFlag(EQUIP_HAT, true);
        setEquipFlag(EQUIP_PIPE, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            int duration = 40 + this.getLevel();
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            if (this.getStateFlag(1) && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
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

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_BASE, "gui.shincolle.equip.base"),
                new EquipOption(EQUIP_HAT, "gui.shincolle.equip.hat"),
                new EquipOption(EQUIP_PIPE, "gui.shincolle.equip.pipe")
        );
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
        return ModItems.SUBM_U511_SPAWN_EGG.get();
    }
}

