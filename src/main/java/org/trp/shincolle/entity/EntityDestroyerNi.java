package org.trp.shincolle.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.base.FaceExpressionConfig;
import org.trp.shincolle.init.ModItems;

import java.util.List;

public class EntityDestroyerNi extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_HEAD_ORNAMENT = "equip_head_ornament";

    private final float maxUpStep;

    public EntityDestroyerNi(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 0, 0, 25});
        getStateComponent().setFactionId(-1);
        getStateComponent().setShipClassId(3);
        getStateComponent().setSpecialEquip(5);
        getStateComponent().setRarity(1);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
        this.maxUpStep = 2.0f;
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            applyBuffToOwner();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getBbHeight() * 0.12f;
        }
        return this.getBbHeight() * 0.77f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"));
        list.add(new EquipOption(EQUIP_HEAD_ORNAMENT, "gui.shincolle.equip.head_ornament"));
        return list;
    }

    private void applyBuffToOwner() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateComponent().getFuel() > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateComponent().getAffectionLegacy() / 50;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,
                        80 + this.getStateComponent().getAffectionLegacy(), amp, false, false));
            }
        }
    }

    @Override
    protected FaceExpressionConfig createFaceExpressionConfig() {
        return createSimpleFaceConfig(FACE_EYES_OPEN, FACE_EYES_HALF, FACE_EYES_CLOSED);
    }

    @Override
    public boolean supportsItemPickup() {
        return true;
    }
protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_NI_SPAWN_EGG.get();
    }
}

