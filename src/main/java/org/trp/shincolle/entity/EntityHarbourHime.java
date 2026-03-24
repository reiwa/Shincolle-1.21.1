package org.trp.shincolle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityHarbourHime extends EntityShipBase {

    public EntityHarbourHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 28);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 2);
        setStateMinor(STATE_MINOR_RARITY, 1);
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (!(this.getStateFlag(1) && this.getStateFlag(9))) {
            return;
        }

        float baseHeal = 1.0F + this.getStateMinor(0) * 0.01F;
        if (this.getHealth() < this.getMaxHealth()) {
            this.heal(baseHeal);
        }

        AABB range = this.getBoundingBox().inflate(12.0D, 8.0D, 12.0D);
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class, range);
        for (EntityShipBase ship : ships) {
            if (ship == this || ship.getHealth() >= ship.getMaxHealth()) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.heal(baseHeal * 0.5F);
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.HARBOUR_HIME_SPAWN_EGG.get();
    }
}

