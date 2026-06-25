package org.trp.shincolle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityHarbourHime extends EntityShipBase {

    public EntityHarbourHime(
        EntityType<? extends TamableAnimal> type,
        Level level
    ) {
        super(type, level);
        setModelPos(new float[] { -6, 30, 0, 40 });
        getStateComponent().setFactionId(10);
        getStateComponent().setShipClassId(28);
        getStateComponent().setSpecialEquip(2);
        getStateComponent().setRarity(1);
        setStateCanRide(true);
    }

    @Override
    public boolean supportsAircraftCombat() {
        return true;
    }

    @Override
    public EntityType<? extends TamableAnimal> getAttackAircraftType(
        boolean isLightAircraft
    ) {
        return isLightAircraft
            ? ModEntities.AIRPLANE.get()
            : ModEntities.TAKOYAKI.get();
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (!(this.isStateMarried() && this.isStateRingEffect())) {
            return;
        }

        float baseHeal = 1.0F + this.getStateComponent().getAffectionLegacy() * 0.01F;
        if (this.getHealth() < this.getMaxHealth()) {
            this.heal(baseHeal);
        }

        AABB range = this.getBoundingBox().inflate(12.0D, 8.0D, 12.0D);
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(
            EntityShipBase.class,
            range
        );
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

    @Override
    public boolean hasShipMounts() {
        return true;
    }

    @Override
    public org.trp.shincolle.entity.base.EntityMountBase summonMountEntity() {
        return new EntityMountHbH(
            org.trp.shincolle.init.ModEntities.MOUNT_HB_H.get(),
            this.level()
        );
    }
}
