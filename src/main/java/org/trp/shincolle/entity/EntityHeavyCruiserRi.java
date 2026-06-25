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

public class EntityHeavyCruiserRi extends EntityShipBase {

    public static final String EQUIP_LEFT = "equip_left";
    public static final String EQUIP_RIGHT = "equip_right";
    public static final String EQUIP_CLOAK = "equip_cloak";
    public static final String EQUIP_HAIR = "equip_hair";

    public EntityHeavyCruiserRi(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 20, 0, 40});
        getStateComponent().setFactionId(2);
        getStateComponent().setShipClassId(9);
        getStateComponent().setSpecialEquip(4);
        getStateComponent().setRarity(4);
        getStateComponent().setGrudgeConsumption(org.trp.shincolle.Config.fuelConsumeCA);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? this.getBbHeight() * 0.05f : this.getBbHeight() * 0.55f;
        }
        return this.getBbHeight() * 0.7f;
    }

    private void updateServerLogic() {
        if (!this.level().isDay() && this.isStateRingEffect()) {
            int duration = 150;
            int ampSpeed = Math.max(0, this.getStateComponent().getAffectionLegacy() / 50);
            int ampJump = Math.max(0, this.getStateComponent().getAffectionLegacy() / 40);
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, ampSpeed, false, false));
            this.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, ampJump, false, false));
        }
    }

    @Override
    protected float[] computeLegacyAuraBuffs() {
        float[] buffs = new float[21];
        if (!this.level().isDay() && this.isStateRingEffect()) {
            buffs[9] += 0.15F;
            buffs[12] += 0.15F;
        }
        return buffs;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_LEFT, "gui.shincolle.equip.left"));
        list.add(new EquipOption(EQUIP_RIGHT, "gui.shincolle.equip.right"));
        list.add(new EquipOption(EQUIP_CLOAK, "gui.shincolle.equip.cloak"));
        list.add(new EquipOption(EQUIP_HAIR, "gui.shincolle.equip.hair"));
        return list;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.HEAVY_CRUISER_RI_SPAWN_EGG.get();
    }
}

