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
        setStateMinor(STATE_MINOR_GRUDGE_CONSUMPTION, org.trp.shincolle.Config.fuelConsumeCL);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
    }



    @Override
    protected void applyAuraEffects() {
        if (!this.level().isDay() && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
            this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 150, 0, false, false));
        }
    }

    @Override
    protected float[] computeLegacyAuraBuffs() {
        float[] buffs = new float[21];
        if (!this.level().isDay() && this.isStateRingEffect()) {
            buffs[9] += 0.15F;
            buffs[15] += 0.15F;
        }
        return buffs;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        list.add(new EquipOption(EQUIP_EARS, "gui.shincolle.equip.ears"));
        list.add(new EquipOption(EQUIP_SIDE, "gui.shincolle.equip.side"));
        list.add(new EquipOption(EQUIP_MASK, "gui.shincolle.equip.mask"));
        list.add(new EquipOption(EQUIP_SHOES, "gui.shincolle.equip.shoes"));
        return list;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CRUISER_TENRYUU_SPAWN_EGG.get();
    }
}

