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

public class EntityCarrierHime extends EntityShipBase {

    public static final String EQUIP_LEFT = "equip_left";
    public static final String EQUIP_RIGHT = "equip_right";

    public EntityCarrierHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 20);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 1);
        setStateMinor(STATE_MINOR_RARITY, 3);
        setStateFlag(13, false);
        setStateFlag(14, false);
        setEquipFlag(EQUIP_LEFT, true);
        setEquipFlag(EQUIP_RIGHT, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            updateServerLogic();
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
        int amp = Math.max(0, this.getStateMinor(0) / 70);
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
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_LEFT, "gui.shincolle.equip.left"),
                new EquipOption(EQUIP_RIGHT, "gui.shincolle.equip.right")
        );
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
        return this.getBbHeight() * 0.9D;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_HIME_SPAWN_EGG.get();
    }
}

