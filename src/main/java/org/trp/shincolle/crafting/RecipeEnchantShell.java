package org.trp.shincolle.crafting;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModRecipeSerializers;
import org.trp.shincolle.item.LegacyEquipItem;

public class RecipeEnchantShell extends CustomRecipe {
    public RecipeEnchantShell(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.width() == 3 && input.height() == 3) {
            ItemStack stack0 = input.getItem(0);
            if (!stack0.is(Items.POTION) && !stack0.is(Items.SPLASH_POTION) && !stack0.is(Items.LINGERING_POTION)) {
                return false;
            }
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (r == 0 && c == 0) continue;
                    ItemStack stackX = input.getItem(r * 3 + c);
                    if (r == 1 && c == 1) {
                        if (!stackX.is(ModItems.EQUIP_AMMO.get())) {
                            return false;
                        }
                        if (stackX.getItem() instanceof LegacyEquipItem equipItem) {
                            int var = equipItem.getVariant(stackX);
                            if (var == 7) {
                                continue;
                            }
                        }
                        return false;
                    }
                    if (stackX.is(stack0.getItem())) {
                        if (ItemStack.isSameItemSameComponents(stack0, stackX)) {
                            continue;
                        }
                        return false;
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack ammo = input.getItem(4);
        ItemStack potion = input.getItem(0);
        if (ammo.is(ModItems.EQUIP_AMMO.get()) && (potion.is(Items.POTION) || potion.is(Items.SPLASH_POTION) || potion.is(Items.LINGERING_POTION))) {
            ItemStack ammoNew = ammo.copy();
            PotionContents contents = potion.get(DataComponents.POTION_CONTENTS);
            if (contents != null && contents.hasEffects()) {
                MobEffectInstance effect = null;
                for (MobEffectInstance eff : contents.getAllEffects()) {
                    effect = eff;
                    break;
                }
                if (effect == null) {
                    return ammoNew;
                }
                
                int pid = getOldIdFromMobEffect(effect.getEffect());
                if (pid < 1) {
                    return ammoNew;
                }
                int plv = effect.getAmplifier();
                int ptime = 100;
                int pchance = 20;

                CustomData customData = ammo.get(DataComponents.CUSTOM_DATA);
                if (customData != null) {
                    CompoundTag tag = customData.copyTag();
                    if (tag.contains("PList", 9)) {
                        ListTag plist = tag.getList("PList", 10);
                        if (!plist.isEmpty()) {
                            CompoundTag nbt0 = plist.getCompound(0);
                            int pidOld = nbt0.getInt("PID");
                            int plvOld = nbt0.getInt("PLV");
                            int ptimeOld = nbt0.getInt("PTick");
                            int pchanceOld = nbt0.getInt("PChance");
                            if (pid == pidOld && plv == plvOld) {
                                ptime = ptimeOld + 20;
                                pchance = pchanceOld + 10;
                                if (pchance > 100) {
                                    pchance = 100;
                                }
                            }
                        }
                    }
                }

                ListTag listNew = new ListTag();
                CompoundTag nbt02 = new CompoundTag();
                nbt02.putInt("PID", pid);
                nbt02.putInt("PLV", plv);
                nbt02.putInt("PTick", ptime);
                nbt02.putInt("PChance", pchance);
                listNew.add(nbt02);

                CompoundTag existing = customData != null ? customData.copyTag() : new CompoundTag();
                existing.put("PList", listNew);
                ammoNew.set(DataComponents.CUSTOM_DATA, CustomData.of(existing));
            }
            return ammoNew;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.ENCHANT_SHELL.get();
    }

    public static int getOldIdFromMobEffect(Holder<net.minecraft.world.effect.MobEffect> effect) {
        if (effect == net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED) return 1;
        if (effect == net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN) return 2;
        if (effect == net.minecraft.world.effect.MobEffects.DIG_SPEED) return 3;
        if (effect == net.minecraft.world.effect.MobEffects.DIG_SLOWDOWN) return 4;
        if (effect == net.minecraft.world.effect.MobEffects.DAMAGE_BOOST) return 5;
        if (effect == net.minecraft.world.effect.MobEffects.HEAL) return 6;
        if (effect == net.minecraft.world.effect.MobEffects.HARM) return 7;
        if (effect == net.minecraft.world.effect.MobEffects.JUMP) return 8;
        if (effect == net.minecraft.world.effect.MobEffects.CONFUSION) return 9;
        if (effect == net.minecraft.world.effect.MobEffects.REGENERATION) return 10;
        if (effect == net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE) return 11;
        if (effect == net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE) return 12;
        if (effect == net.minecraft.world.effect.MobEffects.WATER_BREATHING) return 13;
        if (effect == net.minecraft.world.effect.MobEffects.INVISIBILITY) return 14;
        if (effect == net.minecraft.world.effect.MobEffects.BLINDNESS) return 15;
        if (effect == net.minecraft.world.effect.MobEffects.NIGHT_VISION) return 16;
        if (effect == net.minecraft.world.effect.MobEffects.HUNGER) return 17;
        if (effect == net.minecraft.world.effect.MobEffects.WEAKNESS) return 18;
        if (effect == net.minecraft.world.effect.MobEffects.POISON) return 19;
        if (effect == net.minecraft.world.effect.MobEffects.WITHER) return 20;
        if (effect == net.minecraft.world.effect.MobEffects.HEALTH_BOOST) return 21;
        if (effect == net.minecraft.world.effect.MobEffects.ABSORPTION) return 22;
        if (effect == net.minecraft.world.effect.MobEffects.SATURATION) return 23;
        if (effect == net.minecraft.world.effect.MobEffects.GLOWING) return 24;
        if (effect == net.minecraft.world.effect.MobEffects.LEVITATION) return 25;
        if (effect == net.minecraft.world.effect.MobEffects.LUCK) return 26;
        if (effect == net.minecraft.world.effect.MobEffects.UNLUCK) return 27;
        return 0;
    }
}
