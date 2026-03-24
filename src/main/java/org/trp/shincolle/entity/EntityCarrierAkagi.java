package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityCarrierAkagi extends EntityShipBase {

    public EntityCarrierAkagi(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 20, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 5);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 48);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 1);
        setStateMinor(STATE_MINOR_RARITY, 8);
        setStateFlag(13, false);
        setStateFlag(14, false);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientEffects();
        } else if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateClientEffects() {
        if ((this.tickCount % 128) == 0 && this.getRandom().nextInt(4) == 0 && !this.getStateFlag(2)) {
            this.applyParticleEmotion(9);
        }
    }

    private void updateServerLogic() {
        if (!(this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0)) {
            return;
        }

        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }

        int duration = 50 + this.getStateMinor(0);
        int amp = Math.max(0, this.getStateMinor(0) / 85);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, amp, false, false));
        }
    }

    @Override
    public boolean supportsAircraftCombat() {
        return true;
    }

    @Override
    public EntityType<? extends TamableAnimal> getAttackAircraftType(boolean isLightAircraft) {
        return isLightAircraft ? ModEntities.AIRPLANE_ZERO.get() : ModEntities.AIRPLANE_T.get();
    }

    @Override
    public double getAircraftLaunchHeight() {
        return this.getBbHeight() * 0.65D;
    }

    @Override
    public float getAircraftLightLevelBonus() {
        return 0.28F;
    }

    @Override
    public float getAircraftHeavyLevelBonus() {
        return 0.18F;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_AKAGI_SPAWN_EGG.get();
    }
}

