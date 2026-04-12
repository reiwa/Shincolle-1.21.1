package org.trp.shincolle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;

import java.util.List;
import java.util.Objects;

public class EntityBattleshipTa extends EntityShipBase implements IShipSummonAttack {

    public static final String EQUIP_CLOAK = "equip_cloak";
    public static final String EQUIP_RIGGING = "equip_rigging";

    private static final int MAX_RENSOUHOU = 6;

    private int numRensouhou = 0;

    public EntityBattleshipTa(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 6);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 14);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 3);
        setStateMinor(STATE_MINOR_RARITY, 3);
        setStateGuiBtn3(false);
        setStateGuiBtn4(false);
        setEquipFlag(EQUIP_CLOAK, true);
        setEquipFlag(EQUIP_RIGGING, true);
    }

    @Override
    protected void tickAliveLogic() {
        super.tickAliveLogic();

        if ((this.tickCount % 128) == 0) {
            updateServerLogic();
        }
    }

    public double getPassengersRidingOffset() {
        if (this.getIsSitting()) {
            return this.getStateEmotion(1) == 4 ? 0.0 : this.getBbHeight() * 0.47f;
        }
        return this.getBbHeight() * 0.76f;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_CLOAK, "gui.shincolle.equip.cloak"),
                new EquipOption(EQUIP_RIGGING, "gui.shincolle.equip.rigging")
        );
    }

    @Override
    protected void performLightAttack(Entity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (target == null || !target.isAlive()) {
            return;
        }
        if (this.numRensouhou <= 0) {
            return;
        }
        if (!consumeLightAmmo(4)) {
            return;
        }

        this.numRensouhou--;
        this.setAttackTick(50);
        this.applyEmotesReaction(3);

        serverLevel.sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY() + 1.0D, this.getZ(),
                10, 0.25D, 0.1D, 0.25D, 0.02D);
        summonRensouhou(serverLevel);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("NumRensouhou", this.numRensouhou);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("NumRensouhou")) {
            this.numRensouhou = Math.max(0, Math.min(MAX_RENSOUHOU, tag.getInt("NumRensouhou")));
        }
    }

    @Override
    public int getNumServant() {
        return this.numRensouhou;
    }

    @Override
    public void setNumServant(int num) {
        this.numRensouhou = Math.max(0, Math.min(MAX_RENSOUHOU, num));
    }

    private void updateServerLogic() {
        if (this.numRensouhou < MAX_RENSOUHOU) {
            this.numRensouhou++;
        }
        if (this.isStateMarried() && this.isStateRingEffect() && this.getStateMinor(6) > 0) {
            applyBuffToNearbyAllies();
        }
    }

    private void applyBuffToNearbyAllies() {
        List<EntityShipBase> ships = this.level().getEntitiesOfClass(EntityShipBase.class,
                this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D));
        if (ships.isEmpty()) {
            return;
        }
        int duration = 50 + this.getStateMinor(0);
        int amp = Math.max(0, this.getStateMinor(0) / 80);
        for (EntityShipBase ship : ships) {
            if (ship == this) {
                continue;
            }
            if (!Objects.equals(ship.getOwnerUUID(), this.getOwnerUUID())) {
                continue;
            }
            ship.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, amp, false, false));
        }
    }

    private void summonRensouhou(ServerLevel serverLevel) {
        if (checkModelState(0, this.getStateEmotion(0))) {
            EntityRensouhou rensouhou = ModEntities.RENSOUHOU.get().create(serverLevel);
            if (rensouhou != null) {
                rensouhou.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                serverLevel.addFreshEntity(rensouhou);
            }
        } else {
            EntityRensouhouS rensouhouS = ModEntities.RENSOUHOU_S.get().create(serverLevel);
            if (rensouhouS != null) {
                rensouhouS.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                serverLevel.addFreshEntity(rensouhouS);
            }
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.BATTLESHIP_TA_SPAWN_EGG.get();
    }
}

