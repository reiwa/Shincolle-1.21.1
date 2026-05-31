package org.trp.shincolle.entity;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

public class EntityNorthernHime extends EntityShipBase {

    public static final String EQUIP_CANNON = "equip_cannon";
    public static final String EQUIP_SANTA_CLOTH = "equip_santa_cloth";
    public static final String EQUIP_SANTA_HAT = "equip_santa_hat";
    public static final String EQUIP_UMBRELLA = "equip_umbrella";
    public static final String EQUIP_SHOES = "equip_shoes";

    private int goRidingTicks;
    private boolean goRiding;
    private Entity goRideEntity;

    public EntityNorthernHime(
        EntityType<? extends TamableAnimal> type,
        Level level
    ) {
        super(type, level);
        setModelPos(new float[] { -6, 25, 0, 40 });
        setStateMinor(STATE_MINOR_FACTION_ID, 7);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 31);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 5);
        setStateMinor(
            STATE_MINOR_GRUDGE_CONSUMPTION,
            org.trp.shincolle.Config.fuelConsumeBBV
        );
        setStateCanRide(true);
    }

    @Override
    public boolean supportsAircraftCombat() {
        return true;
    }

    @Override
    public EntityType<? extends TamableAnimal> getAttackAircraftType(
        boolean isLightAircraft
    ) {
        return isLightAircraft
            ? ModEntities.AIRPLANE.get()
            : ModEntities.TAKOYAKI.get();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
            .add(Attributes.MAX_HEALTH, 200.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.ATTACK_DAMAGE, 10.0D)
            .add(Attributes.FOLLOW_RANGE, 40.0D)
            .add(Attributes.STEP_HEIGHT, 1.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(
        ServerLevel level,
        AgeableMob otherParent
    ) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        List<EquipOption> list = new java.util.ArrayList<>(
            super.getEquipOptions()
        );
        list.add(new EquipOption(EQUIP_CANNON, "gui.shincolle.equip.cannon"));
        list.add(
            new EquipOption(
                EQUIP_SANTA_CLOTH,
                "gui.shincolle.equip.santa_cloth"
            )
        );
        list.add(
            new EquipOption(EQUIP_SANTA_HAT, "gui.shincolle.equip.santa_hat")
        );
        list.add(
            new EquipOption(EQUIP_UMBRELLA, "gui.shincolle.equip.umbrella")
        );
        list.add(new EquipOption(EQUIP_SHOES, "gui.shincolle.equip.shoes"));
        return list;
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.NORTHERN_HIME_SPAWN_EGG.get();
    }

    @Override
    protected void migrateLegacyStateFlags(int stateFlags) {
        setEquipFlag(EQUIP_CANNON, (stateFlags & (1 << 0)) != 0);
        boolean santa = (stateFlags & (1 << 1)) != 0;
        setEquipFlag(EQUIP_SANTA_CLOTH, santa);
        setEquipFlag(EQUIP_SANTA_HAT, santa);
        setEquipFlag(EQUIP_UMBRELLA, (stateFlags & (1 << 2)) != 0);
        setEquipFlag(EQUIP_SHOES, (stateFlags & (1 << 3)) != 0);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 64) == 0) {
            handlePeriodicEffects();
        }
        updateServerRidingLogic();
    }

    private void handlePeriodicEffects() {
        if (
            this.getStateMinor(6) > 0 && this.getHealth() < this.getMaxHealth()
        ) {
            this.heal(this.getMaxHealth() * 0.03f + 1.0f);
        }

        if (
            this.isStateMarried() &&
            this.isStateRingEffect() &&
            this.getStateMinor(6) > 25
        ) {
            healNearbyAllies();
        }

        if (this.isPassenger() && this.getMorale() < 7650) {
            this.addMorale(150);
        }

        if ((this.tickCount % 256) == 0 && this.getRandom().nextInt(3) == 0) {
            checkRiding();
        }
    }

    private void healNearbyAllies() {
        int remainingHeals = Math.max(1, this.getLevel() / 25 + 1);

        List<LivingEntity> targets = this.level().getEntitiesOfClass(
            LivingEntity.class,
            this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D)
        );

        for (LivingEntity target : targets) {
            if (remainingHeals <= 0) {
                break;
            }

            if (target == this) {
                continue;
            }

            boolean isAlly = false;
            if (target instanceof Player player) {
                if (Objects.equals(player.getUUID(), this.getOwnerUUID())) {
                    isAlly = true;
                }
            } else if (target instanceof EntityShipBase ship) {
                if (Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                    isAlly = true;
                }
            }

            if (!isAlly) {
                continue;
            }

            if (target.getHealth() / target.getMaxHealth() >= 0.98f) {
                continue;
            }

            float healAmount;
            if (target instanceof Player) {
                healAmount =
                    1.0f +
                    target.getMaxHealth() * 0.02f +
                    this.getLevel() * 0.02f;
            } else {
                healAmount =
                    1.0f +
                    target.getMaxHealth() * 0.02f +
                    this.getLevel() * 0.1f;
            }

            target.heal(healAmount);
            spawnHealParticles(target);
            this.decrGrudgeNum(25);

            remainingHeals--;
        }
    }

    private void spawnHealParticles(LivingEntity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        double y = target.getY() + target.getBbHeight() * 0.6D;
        serverLevel.sendParticles(
            ParticleTypes.HAPPY_VILLAGER,
            target.getX(),
            y,
            target.getZ(),
            4,
            0.3D,
            0.2D,
            0.3D,
            0.01D
        );
    }

    private void decrGrudgeNum(int amount) {
        int next = Math.max(0, this.getStateMinor(6) - amount);
        this.setStateMinor(6, next);
    }

    @Override
    protected void setFaceNormal() {
        this.setFaceId(FACE_EYES_OPEN);
        int tick = this.tickCount & EMOTION_TICK_MASK_8BIT;
        if (this.getEmotionSecondary() == EMOTION_BORED && tick > 200) {
            this.setMouthId(mapLegacyMouth(0));
        } else {
            this.setMouthId(mapLegacyMouth(3));
        }
    }

    @Override
    protected void setFaceCry() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        if (tick < 128) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 2 : 5));
        } else {
            this.setFaceId(FACE_CRY);
            this.setMouthId(mapLegacyMouth(5));
        }
    }

    @Override
    protected void setFaceDamaged() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_9BIT;
        if (tick < 200) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 60 ? 4 : 5));
        } else if (tick < 400) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 3 : 5));
        } else {
            this.setFaceId(FACE_SOFT);
            this.setMouthId(mapLegacyMouth(tick < 450 ? 2 : 3));
        }
    }

    @Override
    protected void setFaceScorn() {
        this.setFaceId(FACE_EYES_HALF);
        this.setMouthId(mapLegacyMouth(3));
    }

    @Override
    protected void setFaceHungry() {
        this.setFaceId(FACE_DESPAIR);
        this.setMouthId(mapLegacyMouth(3));
    }

    @Override
    protected void setFaceAngry() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        if (tick < 128) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 3 : 1));
        } else {
            this.setFaceId(FACE_EYES_HALF);
            this.setMouthId(mapLegacyMouth(tick < 170 ? 0 : 3));
        }
    }

    @Override
    protected void setFaceBored() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_9BIT;
        if (tick < 170) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 0 : 3));
        } else if (tick < 340) {
            this.setFaceId(FACE_DOT_EYES);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 4 : 3));
        } else {
            this.setFaceId(FACE_EYES_OPEN);
            this.setMouthId(mapLegacyMouth(tick < 420 ? 4 : 3));
        }
    }

    @Override
    protected void setFaceShy() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        this.setFaceId(FACE_EYES_OPEN);
        this.setMouthId(mapLegacyMouth(tick < 150 ? 3 : 2));
    }

    @Override
    protected void setFaceHappy() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        if (tick < 140) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 4 : 3));
        } else {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(3));
        }
    }

    private void updateServerRidingLogic() {
        if (this.goRiding) {
            updateGoRidingState();
        }
        if (this.isPassenger()) {
            Entity vehicle = this.getVehicle();
            if (
                vehicle instanceof LivingEntity living && living.isCrouching()
            ) {
                this.stopRiding();
            }
        }
    }

    private void updateGoRidingState() {
        this.goRidingTicks++;
        if (
            this.goRidingTicks > 200 ||
            this.goRideEntity == null ||
            !this.goRideEntity.isAlive()
        ) {
            cancelGoRiding();
            return;
        }
        float distRiding = this.distanceTo(this.goRideEntity);
        if (
            distRiding <= 2.0f &&
            !this.goRideEntity.isPassenger() &&
            this.getPassengers().isEmpty() &&
            this.goRideEntity.getPassengers().isEmpty()
        ) {
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
        List<LivingEntity> hitList = this.level().getEntitiesOfClass(
            LivingEntity.class,
            range
        );
        hitList.removeIf(target -> !isRideable(target));
        if (!hitList.isEmpty()) {
            this.goRideEntity = hitList.get(
                this.getRandom().nextInt(hitList.size())
            );
            this.goRidingTicks = 0;
            this.goRiding = true;
        }
    }

    private boolean isRideable(Entity target) {
        if (!(target instanceof Player || target instanceof EntityShipBase)) {
            return false;
        }
        if (
            target == this ||
            target.isPassenger() ||
            !target.getPassengers().isEmpty()
        ) {
            return false;
        }
        if (target instanceof EntityShipBase ship) {
            boolean allowed = Objects.equals(
                ship.getOwnerUUID(),
                this.getOwnerUUID()
            );
            return allowed;
        }
        if (target instanceof Player player) {
            boolean allowed = Objects.equals(
                player.getUUID(),
                this.getOwnerUUID()
            );
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

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4
                ? 0.0
                : this.getBbHeight() * 0.08f;
        }
        return this.getBbHeight() * 0.48f;
    }
}
