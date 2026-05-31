package org.trp.shincolle.utility;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class EnchantHelper {

    private EnchantHelper() {}

    public static float[] calcEnchantEffect(final ItemStack stack) {
        final float[] ench = new float[21];
        if (stack.isEmpty()) {
            return ench;
        }

        ItemEnchantments enchants = stack.getEnchantments();
        for (Holder<Enchantment> holder : enchants.keySet()) {
            int lv = enchants.getLevel(holder);
            if (lv <= 0) {
                continue;
            }

            if (holder.is(Enchantments.PROTECTION)) {
                ench[0] += 0.1F * lv;
            } else if (holder.is(Enchantments.FIRE_PROTECTION) || holder.is(Enchantments.PROJECTILE_PROTECTION)) {
                ench[0] += 0.05F * lv;
            } else if (holder.is(Enchantments.FEATHER_FALLING)) {
                ench[7] += 0.1F * lv;
                ench[20] -= 0.1F * lv;
            } else if (holder.is(Enchantments.BLAST_PROTECTION)) {
                ench[0] += 0.05F * lv;
                ench[20] += 0.1F * lv;
            } else if (holder.is(Enchantments.RESPIRATION)) {
                ench[14] += 0.15F * lv;
            } else if (holder.is(Enchantments.AQUA_AFFINITY) || holder.is(Enchantments.DEPTH_STRIDER)) {
                ench[7] += 0.05F * lv;
                ench[15] += 0.25F * lv;
            } else if (holder.is(Enchantments.THORNS)) {
                ench[13] += 0.15F * lv;
            } else if (holder.is(Enchantments.FROST_WALKER)) {
                ench[9] += 0.25F * lv;
            } else if (holder.is(Enchantments.SHARPNESS) || holder.is(Enchantments.POWER) ||
                       holder.is(Enchantments.SMITE) || holder.is(Enchantments.BANE_OF_ARTHROPODS)) {
                ench[1] += 0.08F * lv;
            } else if (holder.is(Enchantments.KNOCKBACK) || holder.is(Enchantments.PUNCH)) {
                ench[8] += 0.15F * lv;
                ench[20] += 0.05F * lv;
            } else if (holder.is(Enchantments.FIRE_ASPECT) || holder.is(Enchantments.FLAME)) {
                ench[10] += 0.25F * lv;
                ench[11] += 0.25F * lv;
            } else if (holder.is(Enchantments.LOOTING) || holder.is(Enchantments.FORTUNE) ||
                       holder.is(Enchantments.LUCK_OF_THE_SEA)) {
                ench[16] += 0.25F * lv;
            } else if (holder.is(Enchantments.EFFICIENCY)) {
                ench[6] += 0.1F * lv;
            } else if (holder.is(Enchantments.SILK_TOUCH)) {
                ench[17] += 0.25F * lv;
            } else if (holder.is(Enchantments.UNBREAKING)) {
                ench[5] += 0.2F * lv;
            } else if (holder.is(Enchantments.INFINITY)) {
                ench[18] += 0.25F * lv;
            } else if (holder.is(Enchantments.LURE)) {
                ench[12] += 0.25F * lv;
            } else if (holder.is(Enchantments.MENDING)) {
                ench[19] += 0.5F * lv;
            } else {
                for (int j = 0; j < ench.length; j++) {
                    ench[j] += 0.01F * lv;
                }
            }
        }
        return ench;
    }

    public static int calcEnchantNumber(final ItemStack stack) {
        int number = 0;
        if (stack.isEmpty()) {
            return 0;
        }

        ItemEnchantments enchants = stack.getEnchantments();
        for (Holder<Enchantment> holder : enchants.keySet()) {
            number += enchants.getLevel(holder);
        }
        return number;
    }
}
