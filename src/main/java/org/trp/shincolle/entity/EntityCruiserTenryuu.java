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

public class EntityCruiserTenryuu extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_EARS = "equip_ears";
    public static final String EQUIP_SIDE = "equip_side";
    public static final String EQUIP_MASK = "equip_mask";
    public static final String EQUIP_SHOES = "equip_shoes";

    public EntityCruiserTenryuu(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 22, 0, 42});
        setStateMinor(STATE_MINOR_FACTION_ID, 1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 56);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 4);
        setStateMinor(STATE_MINOR_RARITY, 5);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_EARS, true);
        setEquipFlag(EQUIP_SIDE, true);
        setEquipFlag(EQUIP_MASK, true);
        setEquipFlag(EQUIP_SHOES, true);
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
                new EquipOption(EQUIP_EARS, "gui.shincolle.equip.ears"),
                new EquipOption(EQUIP_SIDE, "gui.shincolle.equip.side"),
                new EquipOption(EQUIP_MASK, "gui.shincolle.equip.mask"),
                new EquipOption(EQUIP_SHOES, "gui.shincolle.equip.shoes")
        );
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CRUISER_TENRYUU_SPAWN_EGG.get();
    }
}

