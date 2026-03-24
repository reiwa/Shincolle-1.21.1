package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityMidwayHime extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_COLLAR = "equip_collar";

    public EntityMidwayHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 30);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 2);
        setStateMinor(STATE_MINOR_RARITY, 2);
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_COLLAR, true);
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

        int duration = 100 + this.getStateMinor(0);
        int amp = Math.max(0, this.getStateMinor(0) / 80);
        AABB range = this.getBoundingBox().inflate(14.0D, 8.0D, 14.0D);
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class, range);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, amp, false, false));
        }
        if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
            this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, amp, false, false));
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_COLLAR, "gui.shincolle.equip.collar")
        );
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.MIDWAY_HIME_SPAWN_EGG.get();
    }
}

