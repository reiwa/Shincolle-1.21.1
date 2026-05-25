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

public class EntityCarrierAkagi extends EntityShipBase {

    public static final String EQUIP_CAT_PARTS = "equip_cat_parts";
    public static final String EQUIP_BACK_QUIVER = "equip_back_quiver";
    public static final String EQUIP_BREASTPLATE = "equip_breastplate";
    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_DECK_HAND = "equip_deck_hand";
    public static final String EQUIP_BOW = "equip_bow";
    public static final String EQUIP_SKIRT = "equip_skirt";
    public static final String EQUIP_SHOES = "equip_shoes";

    public EntityCarrierAkagi(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 20, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 5);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 48);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 1);
        setStateMinor(STATE_MINOR_RARITY, 8);
        setStateMinor(STATE_MINOR_GRUDGE_CONSUMPTION, org.trp.shincolle.Config.fuelConsumeCV);
        setStateGuiBtn1(false);
        setStateGuiBtn2(false);
}

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientEffects();
        }
    }



    private void updateClientEffects() {
        if ((this.tickCount % 128) == 0 && this.getRandom().nextInt(4) == 0 && !this.isStateNoEquip()) {
            this.applyParticleEmotion(9);
        }
    }

    @Override
    protected void applyAuraEffects() {
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
        return 0.28F;
    }

    @Override
    public float getAircraftHeavyLevelBonus() {
        return 0.18F;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CARRIER_AKAGI_SPAWN_EGG.get();
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.addAll(List.of(
                new EquipOption(EQUIP_CAT_PARTS, "gui.shincolle.equip.cat_parts"),
                new EquipOption(EQUIP_BACK_QUIVER, "gui.shincolle.equip.back_quiver"),
                new EquipOption(EQUIP_BREASTPLATE, "gui.shincolle.equip.breastplate"),
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_DECK_HAND, "gui.shincolle.equip.deck_hand"),
                new EquipOption(EQUIP_BOW, "gui.shincolle.equip.bow"),
                new EquipOption(EQUIP_SKIRT, "gui.shincolle.equip.skirt"),
                new EquipOption(EQUIP_SHOES, "gui.shincolle.equip.shoes")
        ));
        return list;
    }
}

