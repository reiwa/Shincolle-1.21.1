package org.trp.shincolle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;

public class OwnerPaperItem extends Item {
    public static final String SignNameA = "SignNameA";
    public static final String SignNameB = "SignNameB";
    public static final String SignIDA = "SignIDA";
    public static final String SignIDB = "SignIDB";

    public OwnerPaperItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public String getDescriptionId() {
        return "item.shincolle.ownerpaper.name";
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && !player.isShiftKeyDown()) {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData == null || customData.isEmpty()) {
                stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY,
                        data -> data.update(tag -> {
                            tag.putString(SignNameA, player.getName().getString());
                            tag.putString(SignNameB, "");
                            tag.putUUID(SignIDA, player.getUUID());
                            tag.putBoolean("signPos", false);
                        }));
            } else {
                CompoundTag tag = customData.copyTag();
                boolean signPos = tag.getBoolean("signPos");
                if (signPos) {
                    stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY,
                            data -> data.update(t -> {
                                t.putString(SignNameA, player.getName().getString());
                                t.putUUID(SignIDA, player.getUUID());
                                t.putBoolean("signPos", false);
                            }));
                } else {
                    stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY,
                            data -> data.update(t -> {
                                t.putString(SignNameB, player.getName().getString());
                                t.putUUID(SignIDB, player.getUUID());
                                t.putBoolean("signPos", true);
                            }));
                }
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.hasUUID(SignIDA)) {
                tooltipComponents.add(Component.literal(tag.getUUID(SignIDA).toString())
                        .withStyle(ChatFormatting.RED)
                        .append(" ")
                        .append(Component.literal(tag.getString(SignNameA)).withStyle(ChatFormatting.AQUA)));
            }
            if (tag.hasUUID(SignIDB)) {
                tooltipComponents.add(Component.literal(tag.getUUID(SignIDB).toString())
                        .withStyle(ChatFormatting.RED)
                        .append(" ")
                        .append(Component.literal(tag.getString(SignNameB)).withStyle(ChatFormatting.AQUA)));
            }
        }
    }
}
