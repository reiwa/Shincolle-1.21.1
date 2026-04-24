package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityCarrierKaga extends EntityShipBase {

    public static final String EQUIP_EAR01 = "equip_ear01";
    public static final String EQUIP_EAR02 = "equip_ear02";
    public static final String EQUIP_EQUIPABASE = "equip_equipabase";
    public static final String EQUIP_EQUIPB01 = "equip_equipb01";
    public static final String EQUIP_EQUIPC01 = "equip_equipc01";
    public static final String EQUIP_EQUIPD01 = "equip_equipd01";
    public static final String EQUIP_EQUIPE01 = "equip_equipe01";
    public static final String EQUIP_EQUIPGLOVE = "equip_equipglove";
    public static final String EQUIP_EQUIPS01 = "equip_equips01";
    public static final String EQUIP_EQUIPSL01 = "equip_equipsl01";
    public static final String EQUIP_EQUIPSR01 = "equip_equipsr01";
    public static final String EQUIP_TAIL01 = "equip_tail01";

    public EntityCarrierKaga(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 20, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 5);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 47);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 1);
        setStateMinor(STATE_MINOR_RARITY, 8);
        setStateGuiBtn1(false);
        setStateGuiBtn2(false);
            setEquipFlag(EQUIP_EAR01, true);
        setEquipFlag(EQUIP_EAR02, true);
        setEquipFlag(EQUIP_EQUIPABASE, true);
        setEquipFlag(EQUIP_EQUIPB01, true);
        setEquipFlag(EQUIP_EQUIPC01, true);
        setEquipFlag(EQUIP_EQUIPD01, true);
        setEquipFlag(EQUIP_EQUIPE01, true);
        setEquipFlag(EQUIP_EQUIPGLOVE, true);
        setEquipFlag(EQUIP_EQUIPS01, true);
        setEquipFlag(EQUIP_EQUIPSL01, true);
        setEquipFlag(EQUIP_EQUIPSR01, true);
        setEquipFlag(EQUIP_TAIL01, true);
}

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    private void updateServerLogic() {
        if (!(this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0)) {
            return;
        }

        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }

        int duration = 50 + this.getStateMinor(0);
        int amp = Math.max(0, this.getStateMinor(0) / 85);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, amp, false, false));
        }
    }

    @Override
    public boolean supportsAircraftCombat() {
        return true;
    }

    @Override
    public EntityType<? extends TamableAnimal> getAttackAircraftType(boolean isLightAircraft) {
        return isLightAircraft ? ModEntities.AIRPLANE_ZERO.get() : ModEntities.AIRPLANE_T.get();
    }

    @Override
    public double getAircraftLaunchHeight() {
        return this.getBbHeight() * 0.65D;
    }

    @Override
    public float getAircraftLightLevelBonus() {
        return 0.4F;
    }

    @Override
    public float getAircraftHeavyLevelBonus() {
        return 0.2F;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_KAGA_SPAWN_EGG.get();
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_EAR01, "gui.shincolle.equip.ear01"),
                new EquipOption(EQUIP_EAR02, "gui.shincolle.equip.ear02"),
                new EquipOption(EQUIP_EQUIPABASE, "gui.shincolle.equip.equipabase"),
                new EquipOption(EQUIP_EQUIPB01, "gui.shincolle.equip.equipb01"),
                new EquipOption(EQUIP_EQUIPC01, "gui.shincolle.equip.equipc01"),
                new EquipOption(EQUIP_EQUIPD01, "gui.shincolle.equip.equipd01"),
                new EquipOption(EQUIP_EQUIPE01, "gui.shincolle.equip.equipe01"),
                new EquipOption(EQUIP_EQUIPGLOVE, "gui.shincolle.equip.equipglove"),
                new EquipOption(EQUIP_EQUIPS01, "gui.shincolle.equip.equips01"),
                new EquipOption(EQUIP_EQUIPSL01, "gui.shincolle.equip.equipsl01"),
                new EquipOption(EQUIP_EQUIPSR01, "gui.shincolle.equip.equipsr01"),
                new EquipOption(EQUIP_TAIL01, "gui.shincolle.equip.tail01")
        );
    }
}

