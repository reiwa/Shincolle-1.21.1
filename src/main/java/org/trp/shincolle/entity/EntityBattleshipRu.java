package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.init.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public class EntityBattleshipRu extends EntityShipBase {

    public static final String EQUIP_WEAPON = "equip_weapon";
    public static final String EQUIP_BASE = "equip_base";
    public static final String EQUIP_GLOVES = "equip_gloves";

    private static final int EMOTION_SKILL_PHASE = 5;

    private int remainAttack = 0;
    private Vec3 skillTarget = Vec3.ZERO;

    public EntityBattleshipRu(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setEquipFlag(EQUIP_WEAPON, true);
        setEquipFlag(EQUIP_BASE, true);
        setEquipFlag(EQUIP_GLOVES, true);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientEffects();
        } else {
            updateServerEffects();
        }
    }

    public double getPassengersRidingOffset() {
        if (!this.getIsSitting()) {
            return this.getBbHeight() * 0.72f;
        }
        if (checkModelState(0, this.getStateEmotion(0))) {
            if (this.getStateEmotion(1) == 4) {
                return this.getBbHeight() * 0.51f;
            }
            if (this.getStateEmotion(7) == 4) {
                return 0.0;
            }
            return this.getBbHeight() * 0.55f;
        }
        return this.getBbHeight() * 0.45f;
    }

    @Override
    protected boolean performHeavyAttack(Entity target) {
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (!consumeHeavyAmmo(1)) {
            return false;
        }

        this.setAttackTick(50);
        this.applyEmotesReaction(3);

        Vec3 targetPos = target.position().add(0.0D, target.getBbHeight() * 0.35D, 0.0D);
        this.skillTarget = targetPos;
        if (this.getStateEmotion(EMOTION_SKILL_PHASE) == 0) {
            this.setStateEmotion(EMOTION_SKILL_PHASE, 1, true);
            this.remainAttack = 5 + (int) (this.getLevel() * 0.035f);
        }
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 220.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
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
        return List.of(
                new EquipOption(EQUIP_WEAPON, "gui.shincolle.equip.weapon"),
                new EquipOption(EQUIP_BASE, "gui.shincolle.equip.base"),
                new EquipOption(EQUIP_GLOVES, "gui.shincolle.equip.gloves")
        );
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("RuRemainAttack", this.remainAttack);
        tag.putDouble("RuTargetX", this.skillTarget.x);
        tag.putDouble("RuTargetY", this.skillTarget.y);
        tag.putDouble("RuTargetZ", this.skillTarget.z);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.remainAttack = Math.max(0, tag.getInt("RuRemainAttack"));
        if (tag.contains("RuTargetX")) {
            this.skillTarget = new Vec3(tag.getDouble("RuTargetX"), tag.getDouble("RuTargetY"), tag.getDouble("RuTargetZ"));
        }
    }

    private void updateClientEffects() {
        if ((this.tickCount & 0xF) != 0) {
            return;
        }
        if (checkModelState(3, this.getStateEmotion(0)) && (this.tickCount & 0x1FF) < 400
                && !this.getIsSitting() && !this.isPassenger()) {
            this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX(), this.getY() + 1.34D, this.getZ(),
                    0.0D, 0.05D, 0.0D);
        }
        if ((this.tickCount & 0x3F) == 0) {
            if (this.getStateEmotion(1) == 4 && checkModelState(0, this.getStateEmotion(0))
                    && (this.tickCount & 0x1FF) > 400) {
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER,
                        this.getX(), this.getY() + 0.6D, this.getZ(),
                        0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void updateServerEffects() {
        if ((this.tickCount & 0x7F) == 0 && this.level().isDay() && this.getStateFlag(9)) {
            this.addEffect(new MobEffectInstance(MobEffects.LUCK, 150, Math.max(0, this.getStateMinor(0) / 140), false, false));
        }
        if (this.getStateEmotion(EMOTION_SKILL_PHASE) > 0) {
            updateSkillEffect();
        }
    }

    private void updateSkillEffect() {
        if (this.remainAttack > 0) {
            if ((this.tickCount & 3) == 0) {
                --this.remainAttack;
                spawnSkillMissile();
            }
        } else {
            this.setStateEmotion(EMOTION_SKILL_PHASE, 0, true);
            this.remainAttack = 0;
        }
    }

    private void spawnSkillMissile() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float damage = Math.max(2.0F, baseDamage * 0.2F);
        float speed = 0.7f;
        int life = 200;
        float explosionRadius = 3.5f;

        double targetX = this.skillTarget.x + this.getRandom().nextFloat() * 8.0f - 4.0f;
        double targetY = this.skillTarget.y + this.getRandom().nextFloat() * 4.0f - 2.0f;
        double targetZ = this.skillTarget.z + this.getRandom().nextFloat() * 8.0f - 4.0f;

        Vec3 origin = this.position().add(0.0D, this.getBbHeight() * 0.4D, 0.0D);
        Vec3 target = new Vec3(targetX, targetY, targetZ);
        Vec3 direction = target.subtract(origin);
        Vec3 velocity = direction.lengthSqr() < 1.0E-6D ? Vec3.ZERO : direction.normalize().scale(speed);

        EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this, null, damage,
                EntityAbyssMissile.MoveType.PRESET_VELOCITY, speed, 0.25f, 0.25f, velocity, life, explosionRadius);
        missile.setPos(origin.x, origin.y, origin.z);
        serverLevel.addFreshEntity(missile);
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BATTLESHIP_RU_SPAWN_EGG.get();
    }
}
