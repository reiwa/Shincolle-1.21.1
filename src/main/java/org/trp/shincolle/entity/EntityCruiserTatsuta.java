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

public class EntityCruiserTatsuta extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_RING = "equip_ring";
    public static final String EQUIP_SIDE = "equip_side";

    public EntityCruiserTatsuta(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 22, 0, 42});
        setStateMinor(STATE_MINOR_FACTION_ID, 1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 57);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 4);
        setStateMinor(STATE_MINOR_RARITY, 3);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_RING, true);
        setEquipFlag(EQUIP_SIDE, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (!this.level().isDay() && this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 150, 0, false, false));
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_RING, "gui.shincolle.equip.ring"),
                new EquipOption(EQUIP_SIDE, "gui.shincolle.equip.side")
        );
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CRUISER_TATSUTA_SPAWN_EGG.get();
    }
}

