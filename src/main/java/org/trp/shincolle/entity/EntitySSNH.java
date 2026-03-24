package org.trp.shincolle.entity;

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

    public EntitySSNH(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 8, 0, 50});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 72);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 6);
        setStateMinor(STATE_MINOR_RARITY, 3);
        setStateFlag(15, false);
        setStateFlag(16, false);
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
        setEquipFlag(EQUIP_HAND_RING, true);
        setEquipFlag(EQUIP_RING_BASE, true);
        setEquipFlag(EQUIP_TORPEDO, true);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            if ((this.tickCount % 128) == 0) {
                updateServerLogic();
            }
            updateRidingState();
        }
    }

    private void updateServerLogic() {
        if (this.getStateFlag(9) && this.getStateMinor(6) > 0) {
            int duration = 80 + this.getLevel();
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            if (this.getStateFlag(1) && this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration, 0, false, false));
            }
        }
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_HAND_RING, "gui.shincolle.equip.ring"),
                new EquipOption(EQUIP_RING_BASE, "gui.shincolle.equip.ring_base"),
                new EquipOption(EQUIP_TORPEDO, "gui.shincolle.equip.torpedo")
        );
    }

    private void updateRidingState() {
        if (this.isPassenger()) {
            Entity vehicle = this.getVehicle();
            if (vehicle instanceof LivingEntity living && living.isCrouching()) {
                this.stopRiding();
                return;
            }
            if ((this.tickCount % 40) == 0) {
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false));
            }
            return;
        }

        boolean canFindTarget = (this.tickCount & 0x7F) == 0 && this.getRandom().nextInt(4) == 0;
        boolean isActionBlocked = this.getIsSitting() || this.getStateFlag(2) || this.isLeashed();
        if (canFindTarget && !isActionBlocked) {
            findRideTarget();
        }
    }

    private void findRideTarget() {
        AABB range = this.getBoundingBox().inflate(6.0D, 4.0D, 6.0D);
        List<Entity> candidates = this.level().getEntities(this, range,
                ent -> ent.isAlive() && ent.canBeCollidedWith() && canRideEntity(ent));
        if (!candidates.isEmpty()) {
            Entity target = candidates.get(this.getRandom().nextInt(candidates.size()));
            this.startRiding(target, true);
        }
    }

    private boolean canRideEntity(Entity target) {
        if (target == this) {
            return false;
        }
        if (target instanceof EntityShipBase ship) {
            return Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID());
        }
        if (target instanceof Player player) {
            return Objects.equals(player.getUUID(), this.getOwnerUUID());
        }
        return false;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.SSNH_SPAWN_EGG.get();
    }
}

