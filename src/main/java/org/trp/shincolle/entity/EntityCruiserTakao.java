package org.trp.shincolle.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;

public class EntityCruiserTakao extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_BAG = "equip_bag";
    public static final String EQUIP_HAT = "equip_hat";
    public static final String EQUIP_SHOES = "equip_shoes";

    public EntityCruiserTakao(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 2);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 59);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 4);
        setStateMinor(STATE_MINOR_RARITY, 4);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_BAG, true);
        setEquipFlag(EQUIP_HAT, true);
        setEquipFlag(EQUIP_SHOES, true);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && !this.level().isClientSide) {
            if (source.getEntity() instanceof LivingEntity attacker) {
                int duration = 80 + this.getStateMinor(0);
                int amp = Math.max(0, this.getStateMinor(0) / 80);
                attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, amp, false, false));
            }
        }
        return result;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_BAG, "gui.shincolle.equip.bag"),
                new EquipOption(EQUIP_HAT, "gui.shincolle.equip.hat"),
                new EquipOption(EQUIP_SHOES, "gui.shincolle.equip.shoes")
        );
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CRUISER_TAKAO_SPAWN_EGG.get();
    }
}

