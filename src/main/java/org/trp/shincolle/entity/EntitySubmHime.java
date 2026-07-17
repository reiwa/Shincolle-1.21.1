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

public class EntitySubmHime extends EntityShipBase {

    public static final String EQUIP_COLLAR = "equip_collar";
    public static final String EQUIP_TAILS = "equip_tails";

    private static final float[][] TORPEDO_OFFSETS = {
            {0.15f, 0.45f}, {0.15f, -0.45f}
    };

    public EntitySubmHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 45});
        getStateComponent().setFactionId(10);
        getStateComponent().setShipClassId(44);
        getStateComponent().setSpecialEquip(6);
        getStateComponent().setRarity(3);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeSS);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (this.isStateRingEffect()) {
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 300, 0, false, false));
            if (this.isStateMarried() && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 300, 0, false, false));
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_COLLAR, "gui.shincolle.equip.collar"));
        list.add(new EquipOption(EQUIP_TAILS, "gui.shincolle.equip.tails"));
        return list;
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
        this.playAttackSound();
        this.applyEmotesReaction(3);
        spawnTorpedoes(target);
        return true;
    }

    private void spawnTorpedoes(Entity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float damage = Math.max(4.0F, baseDamage * 0.4F);
        float speed = 0.65f;
        int life = 180;
        float explosionRadius = 3.5f;
        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;

        for (float[] offset : TORPEDO_OFFSETS) {
            float[] pos = rotateXZByAxis(offset[0], offset[1], yawRad, 1.0f);
            EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this, target, damage, speed, life, explosionRadius);
            missile.setPos(this.getX() + pos[1], this.getY() + this.getBbHeight() * 0.6D, this.getZ() + pos[0]);
            serverLevel.addFreshEntity(missile);
        }
    }

    @Override
    public boolean supportsItemPickup() {
        return true;
    }
    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.SUBM_HIME_SPAWN_EGG.get();
    }

    @Override
    public boolean hasShipMounts() {
        return true;
    }

    @Override
    public org.trp.shincolle.entity.base.EntityMountBase summonMountEntity() {
        return new EntityMountSuH(org.trp.shincolle.init.ModEntities.MOUNT_SU_H.get(), this.level());
    }

    @Override
    public boolean isSubmarine() {
        return true;
    }

    @Override
    protected float getInvisibleDodgeBonus() {
        return 0.30F;
    }
}

