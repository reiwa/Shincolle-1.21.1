package org.trp.shincolle.entity.base;

import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.inventory.ShipInventoryHandler;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.item.LegacyEquipStats;
import org.trp.shincolle.reference.Values;
import org.trp.shincolle.utility.FormationHelper;
import org.trp.shincolle.utility.EnchantHelper;

class EntityShipBaseStatsHelper {

    private final EntityShipBase ship;

    EntityShipBaseStatsHelper(EntityShipBase ship) {
        this.ship = ship;
    }

    int getAttrBonus(int index) {
        return this.ship.getLegacyShipStats().getBonus(index);
    }

    void setAttrBonus(int index, int value) {
        this.ship.getLegacyShipStats().setBonus(index, value);
        this.recalculateLegacyShipStats();
    }

    void recalculateLegacyShipStats() {
        int teamId = this.ship.getFormationTeam();
        int slotId = this.ship.getFormationSlot();
        float[] formationBuffs = Values.getResetFormationValue();
        float[] moraleBuffs = Values.getResetMoraleValue();

        int morale = this.ship.getMorale();
        int moraleId = -1;
        if (morale > 5100) moraleId = 0;
        else if (morale > 3900) moraleId = 1;
        else if (morale <= 900) moraleId = 3;
        else if (morale <= 2100) moraleId = 2;
        float[] attrsMorale = Values.MoraleAttrs.get(moraleId);
        if (attrsMorale != null) {
            moraleBuffs = attrsMorale;
        }

        Player owner = this.ship.getOwnerPlayer();
        if (owner != null && teamId >= 0) {
            AdmiralData data = owner.getData(ModDataAttachments.ADMIRAL_DATA);
            int formationId = data.getFormationID(teamId);
            formationBuffs = FormationHelper.getFormationBuffs(formationId, slotId);
        }

        float[] potionBuffs = this.computePotionBuffs();
        float[] auraBuffs = this.ship.computeLegacyAuraBuffs();
        float[] combinedBuffs = new float[21];
        for (int i = 0; i < combinedBuffs.length; ++i) {
            if (potionBuffs != null && i < potionBuffs.length) {
                combinedBuffs[i] += potionBuffs[i];
            }
            if (auraBuffs != null && i < auraBuffs.length) {
                combinedBuffs[i] += auraBuffs[i];
            }
        }

        this.ship.getLegacyShipStats().recalculate(
            this.ship.getStateMinor(EntityShipBase.STATE_MINOR_SHIP_CLASS),
            this.ship.getLevel(),
            this.collectEquipBonuses(),
            formationBuffs,
            moraleBuffs,
            combinedBuffs,
            this.ship.isHostileShipMob(),
            this.ship.getScaleLevel()
        );

        this.ship.calcShipAttributesAddEffect();
        this.applyEquipmentAttackEffects();

        if (this.ship.level() != null && !this.ship.level().isClientSide) {
            this.ship.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
                this.ship.getLegacyShipStats().getMaxHealth()
            );
            this.ship.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(
                this.ship.getLegacyShipStats().getFirepower() * EntityShipBase.LEGACY_MELEE_DAMAGE_FACTOR
            );
            this.ship.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(
                this.ship.getLegacyShipStats().getMoveSpeed() * EntityShipBase.CRUISE_SPEED_FACTOR
            );
            this.ship.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(
                Math.max(24.0D, this.ship.getLegacyShipStats().getAttackRange())
            );
            if (this.ship.getHealth() > this.ship.getMaxHealth()) {
                this.ship.setHealth(this.ship.getMaxHealth());
            }
        }
    }

    private float[] computePotionBuffs() {
        float[] potion = new float[21];
        for (MobEffectInstance instance : this.ship.getActiveEffects()) {
            Holder<MobEffect> effect = instance.getEffect();
            int amp = instance.getAmplifier();
            int lv = Mth.clamp(amp, 0, 4) + 1;

            if (effect == MobEffects.MOVEMENT_SPEED) {
                potion[7] += 0.08F * lv;
            } else if (effect == MobEffects.MOVEMENT_SLOWDOWN) {
                potion[7] -= 0.15F * lv;
                potion[20] += 0.15F * lv;
            } else if (effect == MobEffects.DIG_SPEED) {
                potion[6] += 0.6F * lv;
            } else if (effect == MobEffects.DIG_SLOWDOWN) {
                potion[6] -= 0.6F * lv;
            } else if (effect == MobEffects.DAMAGE_BOOST) {
                potion[1] += 15.0F * lv;
                potion[2] += 15.0F * lv;
                potion[3] += 15.0F * lv;
                potion[4] += 15.0F * lv;
                potion[20] += 0.15F * lv;
            } else if (effect == MobEffects.JUMP) {
                potion[8] += 2.0F * lv;
            } else if (effect == MobEffects.WATER_BREATHING) {
                potion[15] += 0.15F * lv;
                potion[14] += 20.0F * lv;
            } else if (effect == MobEffects.BLINDNESS) {
                potion[8] -= 24.0F;
            } else if (effect == MobEffects.WEAKNESS) {
                potion[1] -= 15.0F * lv;
                potion[2] -= 15.0F * lv;
                potion[3] -= 15.0F * lv;
                potion[4] -= 15.0F * lv;
                potion[20] -= 0.15F * lv;
            } else if (effect == MobEffects.POISON) {
                potion[5] -= 0.25F * lv;
                potion[20] -= 0.1F * lv;
            } else if (effect == MobEffects.HEALTH_BOOST) {
                potion[0] += 150.0F * lv;
                potion[19] += 0.5F * lv;
            } else if (effect == MobEffects.ABSORPTION) {
                potion[0] += 100.0F * lv;
                potion[5] += 0.2F * lv;
            } else if (effect == MobEffects.SATURATION) {
                potion[17] += 0.5F * lv;
                potion[18] += 0.5F * lv;
            } else if (effect == MobEffects.LEVITATION) {
                potion[15] += 0.1F * lv;
                potion[13] += 20.0F * lv;
                potion[20] -= 0.2F * lv;
            } else if (effect == MobEffects.LUCK) {
                potion[9] += 0.2F * lv;
                potion[10] += 0.2F * lv;
                potion[11] += 0.2F * lv;
            } else if (effect == MobEffects.UNLUCK) {
                potion[9] -= 0.3F * lv;
                potion[10] -= 0.3F * lv;
                potion[11] -= 0.3F * lv;
            }
        }
        return potion;
    }

    void tickPeriodicEffects() {
        if (this.ship.level().isClientSide) {
            return;
        }
        float hp1p = Math.max(1.0F, this.ship.getMaxHealth() * 0.01F);
        for (MobEffectInstance instance : this.ship.getActiveEffects()) {
            Holder<MobEffect> effect = instance.getEffect();
            int amp = instance.getAmplifier();
            int lv = Mth.clamp(amp, 0, 4) + 1;
            if (effect == MobEffects.REGENERATION) {
                if (this.ship.getHealth() < this.ship.getMaxHealth()) {
                    this.ship.heal((hp1p + 4.0F) * (1.0F + lv * 0.5F));
                }
            } else if (effect == MobEffects.WITHER) {
                this.ship.hurt(
                    this.ship.damageSources().magic(),
                    (hp1p + 4.0F) * (1.0F + lv * 0.5F)
                );
            } else if (effect == MobEffects.SATURATION) {
                if (this.ship.getHealth() < this.ship.getMaxHealth()) {
                    this.ship.heal((hp1p + 2.0F) * (0.8F + lv * 0.2F));
                }
                this.ship.setMorale(this.ship.getMorale() + 120 * lv);
            }
        }
    }

    private float[] collectEquipBonuses() {
        float[] equipBonuses = new float[LegacyEquipStats.ATTR_COUNT];
        int equipSlots = Math.min(
            ShipInventoryHandler.getEquipSlotCount(),
            this.ship.inventory.getSlots()
        );
        int drumCount = 0;
        int compassCount = 0;
        int flareCount = 0;
        int searchlightCount = 0;

        int shipEquipType = this.ship.supportsAircraftCombat() ? 3 : 1;

        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = this.ship.inventory.getStackInSlot(slot);
            if (
                stack.isEmpty() ||
                !(stack.getItem() instanceof LegacyEquipItem equipItem)
            ) {
                continue;
            }

            int equipTypeId = equipItem.getEquipTypeId(stack);
            switch (equipTypeId) {
                case EntityShipBase.EQUIP_TYPE_DRUM -> drumCount++;
                case EntityShipBase.EQUIP_TYPE_COMPASS -> compassCount++;
                case EntityShipBase.EQUIP_TYPE_FLARE -> flareCount++;
                case EntityShipBase.EQUIP_TYPE_SEARCHLIGHT -> searchlightCount++;
                default -> {
                }
            }

            int equipId = equipItem.getEquipId(stack);
            int[] misc = LegacyEquipStats.getMiscAttrs(equipId);
            if (misc != null && misc.length > 0) {
                int itemEquipType = misc[0];
                if (shipEquipType != 2 && itemEquipType != 2 && shipEquipType != itemEquipType) {
                    continue;
                }
            }

            float[] stats = LegacyEquipStats.getMainAttrs(equipId);
            if (stats == null) {
                continue;
            }

            float[] raw = stats.clone();
            float[] enchant = org.trp.shincolle.utility.EnchantHelper.calcEnchantEffect(stack);
            int itemEquipType = (misc != null && misc.length > 0) ? misc[0] : 2;
            float[] finalStats = calcEquipStatWithEnchant(itemEquipType, raw, enchant);

            int len = Math.min(equipBonuses.length, finalStats.length);
            for (int i = 0; i < len; i++) {
                equipBonuses[i] += finalStats[i];
            }
        }

        this.ship.setStateMinor(EntityShipBase.STATE_MINOR_EQUIP_DRUM, drumCount);
        this.ship.setStateMinor(EntityShipBase.STATE_MINOR_EQUIP_COMPASS, compassCount);
        this.ship.setStateMinor(EntityShipBase.STATE_MINOR_EQUIP_FLARE, flareCount);
        this.ship.setStateMinor(EntityShipBase.STATE_MINOR_EQUIP_SEARCHLIGHT, searchlightCount);

        return equipBonuses;
    }

    private static float[] calcEquipStatWithEnchant(int equipType, float[] raw, float[] enchant) {
        float[] newstat = new float[21];
        float modTemp;
        newstat[0] = raw[0] * (1.0f + enchant[0]);
        modTemp = equipType == 1 ? 1.0f + enchant[1] : 1.0f;
        newstat[1] = raw[1] * modTemp;
        newstat[2] = raw[2] * modTemp;
        newstat[3] = raw[3] * modTemp;
        newstat[4] = raw[4] * modTemp;
        modTemp = equipType == 2 ? 1.0f + enchant[5] : 1.0f;
        newstat[5] = raw[5] * modTemp;
        newstat[6] = raw[6] * (1.0f + enchant[6]);
        modTemp = raw[7] < 0.0f ? Math.max(0.0f, 1.0f - enchant[7]) : 1.0f + enchant[7];
        newstat[7] = raw[7] * modTemp;
        for (int i = 8; i <= 14; ++i) {
            newstat[i] = raw[i] * (1.0f + enchant[i]);
        }
        modTemp = raw[15] < 0.0f ? Math.max(0.0f, 1.0f - enchant[15]) : 1.0f + enchant[15];
        newstat[15] = raw[15] * modTemp;
        newstat[16] = raw[16] + (equipType == 1 ? enchant[16] : 0.0f);
        newstat[17] = raw[17] + (equipType != 1 ? enchant[17] : 0.0f);
        newstat[18] = raw[18] + (equipType == 1 ? enchant[18] : 0.0f);
        newstat[19] = raw[19] + (equipType != 1 ? enchant[19] : 0.0f);
        newstat[20] = raw[20] + (equipType != 1 ? enchant[20] : 0.0f);
        return newstat;
    }

    private void applyEquipmentAttackEffects() {
        int equipSlots = Math.min(
            ShipInventoryHandler.getEquipSlotCount(),
            this.ship.inventory.getSlots()
        );
        for (int slot = 0; slot < equipSlots; slot++) {
            ItemStack stack = this.ship.inventory.getStackInSlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof LegacyEquipItem equipItem)) {
                continue;
            }
            int typeId = equipItem.getEquipTypeId(stack);
            if (typeId == EntityShipBase.EQUIP_TYPE_AMMO || typeId == EntityShipBase.EQUIP_TYPE_AMMO_2) {
                int variant = equipItem.getVariant(stack);
                switch (variant) {
                    case 0 -> this.ship.getAttackEffectMap().put(MobEffects.POISON, new int[]{0, 120, 50});
                    case 1 -> this.ship.getAttackEffectMap().put(MobEffects.POISON, new int[]{1, 120, 70});
                    case 3 -> this.ship.getAttackEffectMap().put(MobEffects.CONFUSION, new int[]{0, 120, 50});
                    case 4 -> this.ship.getAttackEffectMap().put(MobEffects.WITHER, new int[]{0, 100, 25});
                    case 6 -> this.ship.getAttackEffectMap().put(MobEffects.LEVITATION, new int[]{0, 100, 50});
                    case 7 -> {
                        net.minecraft.world.item.component.CustomData customData = stack.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
                        if (customData != null) {
                            net.minecraft.nbt.CompoundTag tag = customData.copyTag();
                            if (tag.contains("PList", 9)) {
                                net.minecraft.nbt.ListTag plist = tag.getList("PList", 10);
                                for (int i = 0; i < plist.size(); i++) {
                                    net.minecraft.nbt.CompoundTag ptag = plist.getCompound(i);
                                    int pid = ptag.getInt("PID");
                                    int plv = ptag.getInt("PLV");
                                    int ptick = ptag.getInt("PTick");
                                    int pchance = ptag.getInt("PChance");
                                    Holder<MobEffect> effHolder = EnchantHelper.getMobEffectHolderFromOldId(pid);
                                    if (effHolder != null) {
                                        this.ship.getAttackEffectMap().put(effHolder, new int[]{plv, ptick, pchance});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
