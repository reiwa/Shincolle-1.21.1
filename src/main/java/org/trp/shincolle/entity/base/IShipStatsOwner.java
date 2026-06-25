package org.trp.shincolle.entity.base;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.trp.shincolle.inventory.ShipInventoryHandler;

import java.util.Collection;
import java.util.Map;

public interface IShipStatsOwner {
    LegacyShipStats getLegacyShipStats();
    int getFormationTeam();
    int getFormationSlot();
    int getMorale();
    Player getOwnerPlayer();
    float[] doComputeLegacyAuraBuffs();

    ShipStateComponent getStateComponent();
    int getLevel();
    boolean isHostileShipMob();
    int getScaleLevel();
    void doCalcShipAttributesAddEffect();
    Level level();
    AttributeInstance getAttribute(Holder<Attribute> attribute);
    float getHealth();
    float getMaxHealth();
    void setHealth(float health);
    Collection<MobEffectInstance> getActiveEffects();
    void heal(float amount);
    boolean hurt(DamageSource source, float amount);
    DamageSources damageSources();
    void addMorale(int delta);
    boolean supportsAircraftCombat();
    ShipInventoryHandler getInventory();
    Map<Holder<MobEffect>, int[]> getAttackEffectMap();
}
