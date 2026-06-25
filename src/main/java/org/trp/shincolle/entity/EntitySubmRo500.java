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

public class EntitySubmRo500 extends EntityShipBase {

    public static final String EQUIP_BASE_1 = "equip_base_1";
    public static final String EQUIP_BASE_2 = "equip_base_2";
    public static final String EQUIP_FLOWER = "equip_flower";

    private static final float[][] TORPEDO_OFFSETS = {
            {0.1f, 0.0f}
    };

    public EntitySubmRo500(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 20, 0, 45});
        getStateComponent().setFactionId(8);
        getStateComponent().setShipClassId(39);
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
            int duration = 40 + this.getLevel();
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            if (this.isStateMarried() && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_BASE_1, "gui.shincolle.equip.base"));
        list.add(new EquipOption(EQUIP_BASE_2, "gui.shincolle.equip.base"));
        list.add(new EquipOption(EQUIP_FLOWER, "gui.shincolle.equip.flower"));
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
    public boolean supportsItemPickup() {
        return true;
    }
    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.SUBM_RO500_SPAWN_EGG.get();
    }

    @Override
    public boolean isSubmarine() {
        return true;
    }

    @Override
    protected float getInvisibleDodgeBonus() {
        return 0.35F;
    }

    @Override
    protected net.minecraft.world.BossEvent.BossBarColor getBossBarColor() {
        return net.minecraft.world.BossEvent.BossBarColor.YELLOW;
    }
}

