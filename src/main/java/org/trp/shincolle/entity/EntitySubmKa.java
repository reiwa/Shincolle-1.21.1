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

public class EntitySubmKa extends EntityShipBase {

    public static final String EQUIP_BASE = "equip_base";
    public static final String EQUIP_HEAD_BASE = "equip_head_base";
    public static final String EQUIP_NORMAL_BODY = "equip_normal_body";
    public static final String EQUIP_TORPEDO = "equip_torpedo";

    public EntitySubmKa(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 45});
        getStateComponent().setFactionId(8);
        getStateComponent().setShipClassId(17);
        getStateComponent().setSpecialEquip(6);
        getStateComponent().setRarity(4);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeSS);
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
        if (this.isHostile()) {
            if (this.getRandom().nextInt(2) == 0) {
                int duration = 40 + this.getScaleLevel() * 10;
                this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            }
        } else if (this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            int duration = 40 + (int) (this.getLevel() * 50.0F / 150.0F);
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            if (this.isStateMarried() && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_BASE, "gui.shincolle.equip.base"));
        list.add(new EquipOption(EQUIP_HEAD_BASE, "gui.shincolle.equip.head_base"));
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
        return ModItems.SUBM_KA_SPAWN_EGG.get();
    }

    @Override
    public boolean isSubmarine() {
        return true;
    }

    @Override
    protected float getInvisibleDodgeBonus() {
        return 0.25F;
    }
}

