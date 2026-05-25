package org.trp.shincolle.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntitySSNH extends EntityShipBase {

    public static final String EQUIP_HAND_RING = "equip_hand_ring";
    public static final String EQUIP_RING_BASE = "equip_ring_base";
    public static final String EQUIP_TORPEDO = "equip_torpedo";

    private int goRidingTicks;
    private boolean goRiding;
    private Entity goRideEntity;

    public EntitySSNH(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 8, 0, 50});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 72);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 6);
        setStateMinor(STATE_MINOR_RARITY, 3);
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
        if ((this.tickCount % 256) == 0 && this.getRandom().nextInt(3) == 0) {
            checkRiding();
        }
        updateServerRidingLogic();
    }

    private void updateServerLogic() {
        if (this.isStateRingEffect()) {
            int duration = 80 + this.getLevel();
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            if (this.isStateMarried() && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        list.add(new EquipOption(EQUIP_HAND_RING, "gui.shincolle.equip.ring"));
        list.add(new EquipOption(EQUIP_RING_BASE, "gui.shincolle.equip.ring_base"));
        list.add(new EquipOption(EQUIP_TORPEDO, "gui.shincolle.equip.torpedo"));
        return list;
    }

    private void updateServerRidingLogic() {
        if (this.goRiding) {
            updateGoRidingState();
        }
        if (this.isPassenger()) {
            Entity vehicle = this.getVehicle();
            if (vehicle instanceof LivingEntity living && living.isCrouching()) {
                this.stopRiding();
            } else if ((this.tickCount % 40) == 0) {
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false));
            }
        }
    }

    private void updateGoRidingState() {
        this.goRidingTicks++;
        if (this.goRidingTicks > 200 || this.goRideEntity == null || !this.goRideEntity.isAlive()) {
            cancelGoRiding();
            return;
        }
        float distRiding = this.distanceTo(this.goRideEntity);
        if (distRiding <= 2.0f && !this.goRideEntity.isPassenger() && this.getPassengers().isEmpty() && this.goRideEntity.getPassengers().isEmpty()) {
            this.startRiding(this.goRideEntity, true);
            this.getNavigation().stop();
            cancelGoRiding();
        } else if ((this.tickCount % 32) == 0 && distRiding > 2.0f) {
            this.getNavigation().moveTo(this.goRideEntity, 1.0);
        }
    }

    private void cancelGoRiding() {
        this.goRidingTicks = 0;
        this.goRideEntity = null;
        this.goRiding = false;
    }

    private void checkRiding() {
        cancelGoRiding();
        if (this.getIsSitting() || this.isLeashed() || this.isStateNoEquip()) {
            return;
        }
        if (this.isPassenger()) {
            if (this.getRandom().nextInt(2) == 0) {
                this.stopRiding();
            }
            return;
        }

        AABB range = this.getBoundingBox().inflate(6.0D, 4.0D, 6.0D);
        List<LivingEntity> hitList = this.level().getEntitiesOfClass(LivingEntity.class, range);
        hitList.removeIf(target -> !isRideable(target));
        if (!hitList.isEmpty()) {
            this.goRideEntity = hitList.get(this.getRandom().nextInt(hitList.size()));
            this.goRidingTicks = 0;
            this.goRiding = true;
        }
    }

    private boolean isRideable(Entity target) {
        if (!(target instanceof Player || target instanceof EntityShipBase)) {
            return false;
        }
        if (target == this || target.isPassenger() || !target.getPassengers().isEmpty()) {
            return false;
        }
        if (target instanceof EntityShipBase ship) {
            boolean allowed = Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID());
            return allowed;
        }
        if (target instanceof Player player) {
            boolean allowed = Objects.equals(player.getUUID(), this.getOwnerUUID());
            return allowed;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isPassenger()) {
            this.stopRiding();
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean supportsItemPickup() {
        return true;
    }
    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.SSNH_SPAWN_EGG.get();
    }

    @Override
    public boolean isSubmarine() {
        return true;
    }

    @Override
    protected float getInvisibleDodgeBonus() {
        return 0.35F;
    }
}

