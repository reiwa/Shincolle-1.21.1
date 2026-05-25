package org.trp.shincolle.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModSounds;

import java.util.List;

public class EntityCruiserAtago extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";

    public EntityCruiserAtago(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 2);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 58);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 4);
        setStateMinor(STATE_MINOR_RARITY, 4);
        setStateMinor(STATE_MINOR_GRUDGE_CONSUMPTION, org.trp.shincolle.Config.fuelConsumeCA);
        setStateGuiBtn4(false);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && !this.level().isClientSide) {
            if (source.getEntity() instanceof LivingEntity attacker) {
                int duration = 80 + this.getStateMinor(0);
                int amp = Math.max(0, this.getStateMinor(0) / 80);
                attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, amp, false, false));
            }
        }
        return result;
    }

    @Override
    protected boolean performHeavyAttack(Entity target) {
        if (target == null || this.level().isClientSide) {
            return false;
        }
        if (!consumeHeavyAmmo(1)) {
            return false;
        }
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return false;
        }

        float baseDamage = Math.max(4.0F, this.getLegacyShipStats().getFirepower());
        EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this, target,
                baseDamage * 1.4F, 0.7F, 200, 3.5F);
        if (this.isStateMarried() && this.isStateRingEffect()) {
            missile.setSpecialEffectType(EntityAbyssMissile.SPECIAL_EFFECT_PULL_FIELD);
        }
        serverLevel.addFreshEntity(missile);

        this.playSound(ModSounds.SHIP_FIREHEAVY.get(), this.getSoundVolume(),
                this.getRandom().nextFloat() * 0.12F + 0.83F);
        this.setAttackTick(50);
        this.setFuel(this.getFuel() - org.trp.shincolle.Config.fuelConsumeActionHeavy);
        this.applyEmotesReaction(3);
        return true;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        return list;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CRUISER_ATAGO_SPAWN_EGG.get();
    }
}

