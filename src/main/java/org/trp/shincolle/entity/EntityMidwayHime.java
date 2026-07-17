package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

public class EntityMidwayHime extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_COLLAR = "equip_collar";

    public EntityMidwayHime(
        EntityType<? extends TamableAnimal> type,
        Level level
    ) {
        super(type, level);
        setModelPos(new float[] { -6, 30, 0, 40 });
        getStateComponent().setFactionId(10);
        getStateComponent().setShipClassId(30);
        getStateComponent().setSpecialEquip(2);
        getStateComponent().setRarity(2);
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

        int duration = 100 + this.getStateComponent().getAffectionLegacy();
        int amp = Math.max(0, this.getStateComponent().getAffectionLegacy() / 80);
        AABB range = this.getBoundingBox().inflate(14.0D, 8.0D, 14.0D);
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(
            EntityShipBase.class,
            range
        );
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(
                new MobEffectInstance(
                    MobEffects.ABSORPTION,
                    duration,
                    amp,
                    false,
                    false
                )
            );
        }
        if (
            this.getOwnerPlayer() != null &&
            this.distanceToSqr(this.getOwnerPlayer()) < 256.0D
        ) {
            this.getOwnerPlayer().addEffect(
                new MobEffectInstance(
                    MobEffects.ABSORPTION,
                    300,
                    amp,
                    false,
                    false
                )
            );
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(
            super.getEquipOptions()
        );
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        list.add(new EquipOption(EQUIP_COLLAR, "gui.shincolle.equip.collar"));
        return list;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.MIDWAY_HIME_SPAWN_EGG.get();
    }

    @Override
    public boolean hasShipMounts() {
        return true;
    }

    @Override
    public org.trp.shincolle.entity.base.EntityMountBase summonMountEntity() {
        return new EntityMountMiH(
            org.trp.shincolle.init.ModEntities.MOUNT_MI_H.get(),
            this.level()
        );
    }
}
