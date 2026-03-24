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

public class EntityCarrierWDemon extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";

    public EntityCarrierWDemon(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 9);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 33);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 1);
        setStateMinor(STATE_MINOR_RARITY, 2);
        setStateFlag(14, false);
        setEquipFlag(EQUIP_RIGGING, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                    this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
            if (!ships.isEmpty()) {
                int duration = 50 + this.getStateMinor(0);
                int amp = Math.max(0, this.getStateMinor(0) / 70);
                for (EntityShipBase ship : ships) {
                    if (ship == this) {
                        continue;
                    }
                    if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                        continue;
                    }
                    ship.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, duration, amp, false, false));
                }
            }
        }

        this.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 150, 0, false, false));
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
    }

    @Override
    public boolean supportsAircraftCombat() {
        return true;
    }

    @Override
    public EntityType<? extends TamableAnimal> getAttackAircraftType(boolean isLightAircraft) {
        return isLightAircraft ? ModEntities.AIRPLANE.get() : ModEntities.TAKOYAKI.get();
    }

    @Override
    public double getAircraftLaunchHeight() {
        return this.getBbHeight() * 1.2D;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_W_DEMON_SPAWN_EGG.get();
    }
}

