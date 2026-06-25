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
        getStateComponent().setFactionId(10);
        getStateComponent().setShipClassId(26);
        getStateComponent().setSpecialEquip(3);
        getStateComponent().setRarity(1);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
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
        if (!(this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0)) {
            return;
        }
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }
        int duration = 50 + this.getStateComponent().getAffectionLegacy();
        int amp = Math.max(0, this.getStateComponent().getAffectionLegacy() / 70);
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

    @Override
    public boolean hasShipMounts() {
        return true;
    }

    @Override
    public org.trp.shincolle.entity.base.EntityMountBase summonMountEntity() {
        return new EntityMountBaH(org.trp.shincolle.init.ModEntities.MOUNT_BA_H.get(), this.level());
    }
}

