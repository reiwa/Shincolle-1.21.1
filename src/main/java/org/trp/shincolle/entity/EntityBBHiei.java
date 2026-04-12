package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityBBHiei extends EntityShipBase {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_ANCHOR = "equip_anchor";

    public EntityBBHiei(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 6);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 61);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 3);
        setStateMinor(STATE_MINOR_RARITY, 2);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_ANCHOR, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientParticles();
        }
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        if ((this.tickCount % 128) == 0) {
            applyBuffToNearbyAllies();
        }
    }

    public double getPassengersRidingOffset() {
        if (!this.getIsSitting()) {
            return this.getBbHeight() * 0.75f;
        }
        if (checkModelState(1, this.getStateEmotion(0))) {
            return this.getBbHeight() * 0.42f;
        }
        if (this.getStateEmotion(1) == 4) {
            return 0.0;
        }
        return this.getBbHeight() * 0.35f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_ANCHOR, "gui.shincolle.equip.anchor")
        );
    }

    private void updateClientParticles() {
        if (this.tickCount % 4 == 0 && !this.getIsSitting() && this.getEquipFlag(EQUIP_RIGGING) && !this.isInDeadPose()) {
            float[] partPos = rotateXZByAxis(-0.6f, 0.0f, this.yBodyRot * Mth.DEG_TO_RAD, 1.0f);
            for (int i = 0; i < 3; i++) {
                this.level().addParticle(ParticleTypes.SMOKE,
                        this.getX() + partPos[1], this.getY() + 1.17D + i * 0.1D, this.getZ() + partPos[0],
                        0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void applyBuffToNearbyAllies() {
        if (!(this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0)) {
            return;
        }
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }
        int duration = 100 + this.getStateMinor(0) * 2;
        int amp = Math.max(0, this.getStateMinor(0) / 120);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.SATURATION, duration, amp, false, false));
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BB_HIEI_SPAWN_EGG.get();
    }
}

