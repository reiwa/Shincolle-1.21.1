package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityBattleshipHime extends EntityShipBase {

    public EntityBattleshipHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 26);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 3);
        setStateMinor(STATE_MINOR_RARITY, 1);
        setStateFlag(15, false);
        setStateFlag(16, false);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            applyBuffToNearbyAllies();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            if (this.getStateEmotion(1) == 4) {
                return 0.0;
            }
            return this.getBbHeight() * 0.62f;
        }
        return this.getBbHeight() * 0.76f;
    }

    private void applyBuffToNearbyAllies() {
        if (!(this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0)) {
            return;
        }
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }
        int duration = 50 + this.getStateMinor(0);
        int amp = Math.max(0, this.getStateMinor(0) / 70);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, amp, false, false));
            ship.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, amp, false, false));
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BATTLESHIP_HIME_SPAWN_EGG.get();
    }
}

