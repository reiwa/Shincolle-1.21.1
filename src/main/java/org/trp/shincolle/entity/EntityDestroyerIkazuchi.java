package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class EntityDestroyerIkazuchi extends EntityShipBase implements IShipRiderType {

    public static final String EQUIP_RIGGING = "equip_rigging";
    public static final String EQUIP_ANCHOR = "equip_anchor";
    private static final long RAIDEN_GATTAI_DURATION_TICKS = 20L * 45L;
    private static final long RAIDEN_GATTAI_COOLDOWN_TICKS = 20L * 20L;

    private int riderType;
    private boolean isRaiden;
    private long raidenGattaiExpireTick;
    private long raidenGattaiCooldownUntilTick;

    public EntityDestroyerIkazuchi(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 50});
        setStateMinor(STATE_MINOR_FACTION_ID, -1);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 53);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 5);
        setStateMinor(STATE_MINOR_RARITY, 2);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setStateCanRide(true);
        setEquipFlag(EQUIP_RIGGING, true);
        setEquipFlag(EQUIP_ANCHOR, true);
        this.riderType = 0;
        this.isRaiden = false;
        this.raidenGattaiExpireTick = 0L;
        this.raidenGattaiCooldownUntilTick = 0L;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isRaiden() && this.getVehicle() instanceof EntityShipBase vehicle) {
            return vehicle.mobInteract(player, hand);
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide) {
            updateClientLogic();
        }

        updateRiderRotation();
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();
        updateServerLogic();
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getBbHeight() * 0.23f;
        }
        return this.getBbHeight() * 0.64f;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean damaged = super.hurt(source, amount);
        if (damaged && !this.level().isClientSide) {
            if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
                akatsuki.dismountAllRider();
            }
            if (this.isRaiden) {
                dismountRaiden();
            }
        }
        return damaged;
    }

    @Override
    protected void updateFuelState(boolean nofuel) {
        if (nofuel) {
            if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
                akatsuki.dismountAllRider();
            }
            if (this.isRaiden) {
                dismountRaiden();
            }
        }
        super.updateFuelState(nofuel);
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging"),
                new EquipOption(EQUIP_ANCHOR, "gui.shincolle.equip.anchor")
        );
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.DESTROYER_IKAZUCHI_SPAWN_EGG.get();
    }

    @Override
    protected void migrateLegacyStateFlags(int stateFlags) {

    }

    private void updateClientLogic() {
        if ((this.tickCount % 4) == 0 && !this.getIsSitting() && !this.isInDeadPose() && this.getEquipFlag(EQUIP_RIGGING)) {
            float[] partPos = rotateXZByAxis(-0.42f, 0.0f, (this.yBodyRot % 360.0f) * Mth.DEG_TO_RAD, 1.0f);
            this.level().addParticle(ParticleTypes.SMOKE,
                    this.getX() + partPos[1], this.getY() + 1.4D, this.getZ() + partPos[0],
                    0.0D, 0.0D, 0.0D);
        }
        if ((this.tickCount % 16) == 0) {
            updateState();
        }
    }

    private void updateServerLogic() {
        if ((this.tickCount % 32) != 0) {
            return;
        }

        updateState();
        if (!this.isRaiden) {
            this.raidenGattaiExpireTick = 0L;
        }
        if (this.raidenGattaiCooldownUntilTick > 0L && this.level().getGameTime() >= this.raidenGattaiCooldownUntilTick) {
            this.raidenGattaiCooldownUntilTick = 0L;
        }
        if (this.isRaiden && !(this.getVehicle() instanceof EntityDestroyerInazuma)) {
            this.isRaiden = false;
        }
        if (this.isRaiden && (this.getIsSitting() || this.isInDeadPose() || isRaidenGattaiDurationExpired())) {
            dismountRaiden();
        }
        if (this.riderType == 0 && this.isRaiden && this.getMorale() < 7650) {
            this.addMorale(100);
        }
        if ((this.tickCount % 128) == 0) {
            applyBuffToPlayer();
            tryRaidenGattai();
        }
    }

    private void updateState() {
        checkRiderType();
        checkIsRaiden();
        checkRidingState();
    }

    private void applyBuffToPlayer() {
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0) {
            if (this.getOwnerPlayer() != null && this.distanceToSqr(this.getOwnerPlayer()) < 256.0D) {
                int amp = this.getStateMinor(0) / 50;
                this.getOwnerPlayer().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,
                        80 + this.getStateMinor(0), amp, false, false));
            }
        }
    }

    private void updateRiderRotation() {
        if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
            akatsuki.syncRotateToRider();
        } else if (this.getVehicle() instanceof EntityDestroyerInazuma inazuma) {
            this.yBodyRot = inazuma.yBodyRot;
            this.yBodyRotO = inazuma.yBodyRotO;
            this.yHeadRot = inazuma.yHeadRot;
            this.yHeadRotO = inazuma.yHeadRotO;
            this.setYRot(inazuma.getYRot());
            this.yRotO = inazuma.yRotO;
        }
    }

    private void tryRaidenGattai() {
        if (!canAttemptGattai()) {
            return;
        }

        List<EntityDestroyerInazuma> list = this.level().getEntitiesOfClass(EntityDestroyerInazuma.class,
                this.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
        for (EntityDestroyerInazuma inazuma : list) {
            if (canGattaiWith(inazuma)) {
                this.startRiding(inazuma, true);
                beginRaidenGattai(inazuma);
                break;
            }
        }
    }

    private boolean canAttemptGattai() {
        if (this.getStateMinor(43) > 0) {
            dismountRaiden();
            this.stopRiding();
            return false;
        }
        if (isRaidenGattaiCooldownActive()) {
            return false;
        }
        return !this.getIsSitting() && !this.isPassenger() && this.getEquipFlag(EQUIP_RIGGING)
                && this.riderType <= 0 && !this.isRaiden
                && this.getHealth() > this.getMaxHealth() * 0.5f;
    }

    private void beginRaidenGattai(EntityDestroyerInazuma inazuma) {
        this.isRaiden = true;
        inazuma.setRaiden(true);

        long expireTick = this.level().getGameTime() + RAIDEN_GATTAI_DURATION_TICKS;
        this.setRaidenGattaiExpireTick(expireTick);
        inazuma.setRaidenGattaiExpireTick(expireTick);
    }

    private boolean canGattaiWith(EntityDestroyerInazuma partner) {
        if (partner == null || !partner.isAlive()) {
            return false;
        }
        if (!Objects.equals(this.getOwnerUUID(), partner.getOwnerUUID())) {
            return false;
        }
        return partner.getRiderType() == 0 && !partner.isRaiden()
                && !partner.isStateNoEquip() && partner.getStateMinor(43) == 0
                && !partner.isRaidenGattaiCooldownActive();
    }

    private void checkRiderType() {
        this.riderType = 0;
        if (this.getVehicle() instanceof EntityDestroyerAkatsuki akatsuki) {
            this.riderType = akatsuki.getRiderType();
        }
    }

    private void checkIsRaiden() {
        this.isRaiden = this.getVehicle() instanceof EntityDestroyerInazuma;
    }

    private void checkRidingState() {
        if (this.riderType == 7 || this.isRaiden) {
            this.setRidingState(2);
        } else {
            this.setRidingState(0);
        }
    }

    private void dismountRaiden() {
        if (this.getVehicle() instanceof EntityDestroyerInazuma inazuma) {
            this.startRaidenGattaiCooldown();
            inazuma.startRaidenGattaiCooldown();
            this.isRaiden = false;
            inazuma.setRaiden(false);
            this.stopRiding();
            inazuma.placeIkazuchiAfterRaidenDismount(this);
        }
    }

    boolean isRaidenGattaiCooldownActive() {
        return this.raidenGattaiCooldownUntilTick > this.level().getGameTime();
    }

    void setRaidenGattaiExpireTick(long expireTick) {
        this.raidenGattaiExpireTick = expireTick;
    }

    void startRaidenGattaiCooldown() {
        this.raidenGattaiExpireTick = 0L;
        this.raidenGattaiCooldownUntilTick = Math.max(
                this.raidenGattaiCooldownUntilTick,
                this.level().getGameTime() + RAIDEN_GATTAI_COOLDOWN_TICKS
        );
    }

    private boolean isRaidenGattaiDurationExpired() {
        return this.raidenGattaiExpireTick > 0L && this.level().getGameTime() >= this.raidenGattaiExpireTick;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putLong("RaidenGattaiExpireTick", this.raidenGattaiExpireTick);
        compound.putLong("RaidenGattaiCooldownUntilTick", this.raidenGattaiCooldownUntilTick);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.raidenGattaiExpireTick = compound.getLong("RaidenGattaiExpireTick");
        this.raidenGattaiCooldownUntilTick = compound.getLong("RaidenGattaiCooldownUntilTick");
    }

    @Override
    public int getRiderType() {
        return this.riderType;
    }

    @Override
    public void setRiderType(int type) {
        this.riderType = type;
    }

    public boolean isRaiden() {
        return this.isRaiden;
    }

    public void setRaiden(boolean raiden) {
        this.isRaiden = raiden;
    }

    @Override
    protected void setFaceNormal() {
        this.setFaceId(FACE_EYES_OPEN);
        int tick = this.tickCount & EMOTION_TICK_MASK_8BIT;
        if (this.getEmotionSecondary() == EMOTION_BORED && tick > 160) {
            this.setMouthId(mapLegacyMouth(4));
        } else {
            this.setMouthId(mapLegacyMouth(0));
        }
    }

    @Override
    protected void setFaceCry() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 128) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 2 : 5));
        } else {
            this.setFaceId(FACE_CRY);
            this.setMouthId(mapLegacyMouth(tick < 190 ? 2 : 5));
        }
    }

    @Override
    protected void setFaceDamaged() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_9BIT);
        if (tick < 200) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 60 ? 4 : 5));
        } else if (tick < 400) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 4 : 5));
        } else {
            this.setFaceId(FACE_SOFT);
            this.setMouthId(mapLegacyMouth(tick < 450 ? 4 : 5));
        }
    }

    @Override
    protected void setFaceScorn() {
        this.setFaceId(FACE_EYES_HALF);
        this.setMouthId(mapLegacyMouth(1));
    }

    @Override
    protected void setFaceHungry() {
        this.setFaceId(FACE_DESPAIR);
        this.setMouthId(mapLegacyMouth(5));
    }

    @Override
    protected void setFaceAngry() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 128) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 0 : 4));
        } else {
            this.setFaceId(FACE_EYES_HALF);
            this.setMouthId(mapLegacyMouth(tick < 170 ? 1 : 4));
        }
    }

    @Override
    protected void setFaceBored() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_9BIT);
        if (tick < 170) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 0 : 4));
        } else if (tick < 340) {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 0 : 4));
        } else {
            this.setFaceId(FACE_EYES_OPEN);
            this.setMouthId(mapLegacyMouth(tick < 420 ? 5 : 4));
        }
    }

    @Override
    protected void setFaceShy() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        this.setFaceId(FACE_EYES_OPEN);
        this.setMouthId(mapLegacyMouth(tick < 150 ? 2 : 4));
    }

    @Override
    protected void setFaceHappy() {
        int tick = getLegacyFaceTick(EMOTION_TICK_MASK_8BIT);
        if (tick < 140) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 4 : 5));
        } else {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(4));
        }
    }

}
