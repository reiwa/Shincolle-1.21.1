package org.trp.shincolle.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;
import java.util.Locale;

public class LegacyEquipItem extends Item {
    private static final String TAG_VARIANT = "LegacyVariant";

    private final String legacyNameBase;
    private final int[] equipTypeByVariant;
    private final int[] modelVariantByVariant;

    public LegacyEquipItem(Properties properties, String legacyNameBase, int[] equipTypeByVariant) {
        this(properties, legacyNameBase, equipTypeByVariant, null);
    }

    public LegacyEquipItem(Properties properties, String legacyNameBase, int[] equipTypeByVariant, int[] modelVariantByVariant) {
        super(properties.stacksTo(1));
        this.legacyNameBase = legacyNameBase;
        this.equipTypeByVariant = equipTypeByVariant.clone();
        this.modelVariantByVariant = new int[this.equipTypeByVariant.length];
        if (modelVariantByVariant != null) {
            int copyLen = Math.min(this.modelVariantByVariant.length, modelVariantByVariant.length);
            for (int i = 0; i < copyLen; i++) {
                this.modelVariantByVariant[i] = Math.max(0, modelVariantByVariant[i]);
            }
        }
    }

    public int getVariantCount() {
        return this.equipTypeByVariant.length;
    }

    public int getVariant(ItemStack stack) {
        if (this.equipTypeByVariant.length <= 1) {
            return 0;
        }

        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return 0;
        }

        int raw = customData.copyTag().getInt(TAG_VARIANT);
        return Mth.clamp(raw, 0, this.equipTypeByVariant.length - 1);
    }

    public int getEquipTypeId(ItemStack stack) {
        return this.equipTypeByVariant[getVariant(stack)];
    }

    public int getEquipId(ItemStack stack) {
        int variant = getVariant(stack);
        return this.equipTypeByVariant[variant] + variant * 100;
    }

    public int getModelVariant(ItemStack stack) {
        return this.modelVariantByVariant[getVariant(stack)];
    }

    public ItemStack createVariantStack(int variant) {
        int clamped = Mth.clamp(variant, 0, this.equipTypeByVariant.length - 1);
        ItemStack stack = new ItemStack(this);

        if (clamped > 0) {
            stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY,
                    data -> data.update(tag -> tag.putInt(TAG_VARIANT, clamped)));
        }

        return stack;
    }

    public void addAllVariantsToCreativeTab(CreativeModeTab.Output output) {
        for (int variant = 0; variant < this.equipTypeByVariant.length; variant++) {
            output.accept(createVariantStack(variant));
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        int variant = getVariant(stack);
        String suffix = variant > 0 ? String.valueOf(variant) : "";
        return Component.translatable("item.shincolle:" + this.legacyNameBase + suffix + ".name");
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public java.util.Optional<net.minecraft.world.inventory.tooltip.TooltipComponent> getTooltipImage(ItemStack stack) {
        java.util.List<Component> scaledLines = new java.util.ArrayList<>();

        addSpecialTooltip(stack, scaledLines);

        int equipId = getEquipId(stack);
        float[] main = LegacyEquipStats.getMainAttrs(equipId);
        int[] misc = LegacyEquipStats.getMiscAttrs(equipId);
        if (main != null && misc != null) {
            addMainTooltip(main, scaledLines);
            addMiscTooltip(misc, scaledLines);
        }

        if (scaledLines.isEmpty()) {
            return java.util.Optional.empty();
        }

        return java.util.Optional.of(new ScaledTextTooltipData(scaledLines, 0.75F));
    }

    private void addSpecialTooltip(ItemStack stack, List<Component> tooltipComponents) {
        int variant = getVariant(stack);

        switch (this.legacyNameBase) {
            case "EquipCompass" -> tooltipComponents.add(Component.translatable("gui.shincolle.compass").withStyle(ChatFormatting.GRAY));
            case "EquipFlare" -> tooltipComponents.add(Component.translatable("gui.shincolle.flare").withStyle(ChatFormatting.GRAY));
            case "EquipSearchlight" -> tooltipComponents.add(Component.translatable("gui.shincolle.searchlight").withStyle(ChatFormatting.GRAY));
            case "EquipDrum" -> {
                if (variant == 1) {
                    tooltipComponents.add(Component.translatable("gui.shincolle.drum1").withStyle(ChatFormatting.GRAY));
                } else if (variant == 2) {
                    tooltipComponents.add(Component.translatable("gui.shincolle.drum2b").withStyle(ChatFormatting.GRAY));
                } else {
                    tooltipComponents.add(Component.translatable("gui.shincolle.drum").withStyle(ChatFormatting.GRAY));
                }
            }
            case "EquipAmmo" -> {
                if (variant == 5) {
                    tooltipComponents.add(Component.translatable("gui.shincolle.equip.gravity").withStyle(ChatFormatting.YELLOW));
                } else if (variant == 8) {
                    tooltipComponents.add(Component.translatable("gui.shincolle.equip.cluster").withStyle(ChatFormatting.YELLOW));
                }
            }
            case "EquipTorpedo" -> {
                int speedLevel = getTorpedoSpeedLevel(variant);
                if (speedLevel > 0) {
                    tooltipComponents.add(Component.translatable("gui.shincolle.equip.torpedospeed", speedLevel)
                            .withStyle(ChatFormatting.YELLOW));
                }
            }
            default -> {
            }
        }
    }

    private static int getTorpedoSpeedLevel(int variant) {
        return switch (variant) {
            case 3, 4 -> 1;
            case 5 -> 2;
            case 6 -> 3;
            default -> 0;
        };
    }

    private static void addMainTooltip(float[] main, List<Component> tooltipComponents) {
        addFlatStat(tooltipComponents, main[0], "gui.shincolle.hp", ChatFormatting.RED, 1);
        addFlatStat(tooltipComponents, main[1], "gui.shincolle.firepower1", ChatFormatting.RED, 1);
        addFlatStat(tooltipComponents, main[2], "gui.shincolle.torpedo", ChatFormatting.GREEN, 1);
        addFlatStat(tooltipComponents, main[3], "gui.shincolle.airfirepower", ChatFormatting.RED, 1);
        addFlatStat(tooltipComponents, main[4], "gui.shincolle.airtorpedo", ChatFormatting.GREEN, 1);
        addPercentStat(tooltipComponents, main[5], "gui.shincolle.armor", ChatFormatting.WHITE, 1);
        addFlatStat(tooltipComponents, main[6], "gui.shincolle.attackspeed", ChatFormatting.WHITE, 2);
        addFlatStat(tooltipComponents, main[7], "gui.shincolle.movespeed", ChatFormatting.GRAY, 2);
        addFlatStat(tooltipComponents, main[8], "gui.shincolle.range", ChatFormatting.LIGHT_PURPLE, 1);
        addPercentStat(tooltipComponents, main[9], "gui.shincolle.critical", ChatFormatting.AQUA, 0);
        addPercentStat(tooltipComponents, main[10], "gui.shincolle.doublehit", ChatFormatting.YELLOW, 0);
        addPercentStat(tooltipComponents, main[11], "gui.shincolle.triplehit", ChatFormatting.GOLD, 0);
        addPercentStat(tooltipComponents, main[12], "gui.shincolle.missreduce", ChatFormatting.RED, 0);
        addPercentStat(tooltipComponents, main[15], "gui.shincolle.dodge", ChatFormatting.GOLD, 0);
        addFlatStat(tooltipComponents, main[13], "gui.shincolle.antiair", ChatFormatting.YELLOW, 1);
        addFlatStat(tooltipComponents, main[14], "gui.shincolle.antiss", ChatFormatting.AQUA, 1);

        addLabelPercent(tooltipComponents, main[16], "gui.shincolle.equip.xp", ChatFormatting.GREEN);
        addLabelPercent(tooltipComponents, main[17], "gui.shincolle.equip.grudge", ChatFormatting.DARK_PURPLE);
        addLabelPercent(tooltipComponents, main[18], "gui.shincolle.equip.ammo", ChatFormatting.DARK_AQUA);
        addLabelPercent(tooltipComponents, main[19], "gui.shincolle.equip.hpres", ChatFormatting.DARK_GREEN);
        addLabelPercent(tooltipComponents, main[20], "gui.shincolle.equip.kb", ChatFormatting.DARK_RED);
    }

    private static void addMiscTooltip(int[] misc, List<Component> tooltipComponents) {
        MutableComponent line = Component.translatable("gui.shincolle.equip.enchtype")
                .append(" ")
                .append(getEnchantTypeComponent(misc[5]));

        if (misc[0] == 1) {
            line.append(Component.literal("  ").append(Component.translatable("gui.shincolle.notforcarrier").withStyle(ChatFormatting.DARK_RED)));
        } else if (misc[0] == 3) {
            line.append(Component.literal("  ").append(Component.translatable("gui.shincolle.carrieronly").withStyle(ChatFormatting.DARK_AQUA)));
        }
        tooltipComponents.add(line);

        tooltipComponents.add(Component.translatable(misc[3] > 400 ? "block.shincolle.large_shipyard" : "block.shincolle.small_shipyard")
                .withStyle(ChatFormatting.DARK_RED));

        String materialKey = switch (misc[4]) {
            case 1 -> "item.shincolle.abyss_metal";
            case 2 -> "item.shincolle.ammo";
            case 3 -> "item.shincolle.abyss_polymetal";
            default -> "item.shincolle.grudge";
        };

        MutableComponent mats = Component.translatable("gui.shincolle.equip.matstype")
                .withStyle(ChatFormatting.DARK_PURPLE)
                .append(Component.literal(" (").withStyle(ChatFormatting.GRAY))
                .append(Component.translatable(materialKey).withStyle(ChatFormatting.GRAY))
                .append(Component.literal(") " + misc[3] + "  ").withStyle(ChatFormatting.GRAY))
                .append(Component.translatable("gui.shincolle.equip.matsrarelevel").withStyle(ChatFormatting.DARK_PURPLE))
                .append(Component.literal(" " + misc[2]).withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(mats);
    }

    private static MutableComponent getEnchantTypeComponent(int enchType) {
        return switch (enchType) {
            case 1 -> Component.translatable("gui.shincolle.equip.enchtype1").withStyle(ChatFormatting.RED);
            case 2 -> Component.translatable("gui.shincolle.equip.enchtype0").withStyle(ChatFormatting.AQUA);
            case 3 -> Component.translatable("gui.shincolle.equip.enchtype2").withStyle(ChatFormatting.GRAY);
            default -> Component.empty();
        };
    }

    private static void addFlatStat(List<Component> tooltipComponents, float value, String key, ChatFormatting color, int precision) {
        if (value == 0.0F) {
            return;
        }

        String format = precision <= 0 ? "%.0f" : (precision == 1 ? "%.1f" : "%.2f");
        tooltipComponents.add(Component.literal(String.format(Locale.ROOT, format, value) + " ")
                .withStyle(color)
                .append(Component.translatable(key).withStyle(color)));
    }

    private static void addPercentStat(List<Component> tooltipComponents, float value, String key, ChatFormatting color, int precision) {
        if (value == 0.0F) {
            return;
        }

        float percent = value * 100.0F;
        String format = precision <= 0 ? "%.0f%%" : "%.1f%%";
        tooltipComponents.add(Component.literal(String.format(Locale.ROOT, format, percent) + " ")
                .withStyle(color)
                .append(Component.translatable(key).withStyle(color)));
    }

    private static void addLabelPercent(List<Component> tooltipComponents, float value, String key, ChatFormatting color) {
        if (value == 0.0F) {
            return;
        }

        tooltipComponents.add(Component.translatable(key)
                .withStyle(color)
                .append(Component.literal(String.format(Locale.ROOT, " %.0f%%", value * 100.0F)).withStyle(color)));
    }
}
