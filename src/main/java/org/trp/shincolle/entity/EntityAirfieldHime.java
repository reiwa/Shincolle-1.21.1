package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
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
import org.trp.shincolle.init.ModItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class EntityAirfieldHime extends EntityShipBase {

    public static final String EQUIP_HAND = "equip_hand";

    public EntityAirfieldHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 21);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 2);
        setStateMinor(STATE_MINOR_RARITY, 4);
        setStateFlag(STATE_FLAG_CAN_RIDE, true);
        setEquipFlag(EQUIP_HAND, true);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 180.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.FOLLOW_RANGE, 36.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(new EquipOption(EQUIP_HAND, "gui.shincolle.equip.hand"));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide && (this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            if (this.getStateEmotion(1) == 4) {
                return this.getBbHeight() * 0.65f;
            }
            return this.getBbHeight() * 0.56f;
        }
        return this.getBbHeight() * 0.75f;
    }

    private void updateServerLogic() {
        if (this.getStateMinor(6) > 0 && this.getHealth() < this.getMaxHealth()) {
            this.heal(this.getMaxHealth() * 0.06f + 1.0f);
        }

        if (!(this.getStateFlag(1) && this.getStateFlag(9) && this.getStateMinor(6) > 50)) {
            return;
        }

        int healCount = this.getLevel() / 15 + 2;
        AABB range = this.getBoundingBox().inflate(12.0D, 12.0D, 12.0D);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, range);
        for (LivingEntity target : targets) {
            if (healCount <= 0) {
                break;
            }
            if (target == this || target.getHealth() / target.getMaxHealth() >= 0.96f) {
                continue;
            }

            boolean healed = false;
            if (target instanceof Player player) {
                if (!Objects.equals(player.getUUID(), this.getOwnerUUID())) {
                    continue;
                }
                target.heal(1.0f + target.getMaxHealth() * 0.04f + this.getLevel() * 0.04f);
                healed = true;
            } else if (target instanceof EntityShipBase ship) {
                if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                    continue;
                }
                target.heal(1.0f + target.getMaxHealth() * 0.04f + this.getLevel() * 0.1f);
                healed = true;
            }

            if (!healed) {
                continue;
            }
            spawnHealParticles(target);
            consumeGrudge(50);
            healCount--;
        }
    }

    private void consumeGrudge(int amount) {
        int next = Math.max(0, this.getStateMinor(6) - amount);
        this.setStateMinor(6, next);
    }

    private void spawnHealParticles(LivingEntity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        double y = target.getY() + target.getBbHeight() * 0.6D;
        serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, target.getX(), y, target.getZ(),
                4, 0.3D, 0.2D, 0.3D, 0.01D);
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.AIRFIELD_HIME_SPAWN_EGG.get();
    }
}
