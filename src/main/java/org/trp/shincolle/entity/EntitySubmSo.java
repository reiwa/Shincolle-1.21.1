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

public class EntitySubmSo extends EntityShipBase {

    public static final String EQUIP_HEAD_BASE = "equip_head_base";
    public static final String EQUIP_CANNON = "equip_cannon";
    public static final String EQUIP_NORMAL_BODY = "equip_normal_body";
    public static final String EQUIP_TORPEDO = "equip_torpedo";

    public EntitySubmSo(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 45});
        setStateMinor(STATE_MINOR_FACTION_ID, 8);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 19);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 6);
        setStateMinor(STATE_MINOR_RARITY, 4);
        setStateMinor(STATE_MINOR_GRUDGE_CONSUMPTION, org.trp.shincolle.Config.fuelConsumeSS);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (this.isStateRingEffect()) {
            int duration = 40 + this.getLevel();
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            if (this.isStateMarried() && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_HEAD_BASE, "gui.shincolle.equip.head_base"));
        list.add(new EquipOption(EQUIP_CANNON, "gui.shincolle.equip.cannon"));
        list.add(new EquipOption(EQUIP_NORMAL_BODY, "gui.shincolle.equip.normal_body"));
        list.add(new EquipOption(EQUIP_TORPEDO, "gui.shincolle.equip.torpedo"));
        return list;
    }

    @Override
    public boolean supportsItemPickup() {
        return true;
    }
    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.SUBM_SO_SPAWN_EGG.get();
    }

    @Override
    public boolean isSubmarine() {
        return true;
    }

    @Override
    protected float getInvisibleDodgeBonus() {
        return 0.20F;
    }
}

