package org.trp.shincolle.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.List;
import java.util.function.Supplier;

public class OwnedSpawnEggItem extends DeferredSpawnEggItem {
    private final Supplier<? extends EntityType<? extends Mob>> typeSupplier;

    public OwnedSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int primaryColor, int secondaryColor, Item.Properties properties) {
        super(type, primaryColor, secondaryColor, properties);
        this.typeSupplier = type;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && !context.getLevel().isClientSide) {
            ItemStack stack = context.getItemInHand();

            CustomData customData = stack.get(DataComponents.ENTITY_DATA);
            boolean isResurrection = false;
            int costLevel = 0;
            if (customData != null) {
                net.minecraft.nbt.CompoundTag tag = customData.copyTag();
                if (tag.getBoolean("ShincolleSpawnEgg")) {
                    isResurrection = true;
                    costLevel = tag.getInt("ShipLevel") / 3;
                }
            }

            if (isResurrection && costLevel > 0 && !player.isCreative()) {
                if (player.experienceLevel < costLevel) {
                    player.displayClientMessage(Component.translatable("chat.shincolle:levelfail"), false);
                    return InteractionResult.FAIL;
                }
            }

            stack.update(DataComponents.ENTITY_DATA, CustomData.EMPTY, existingData -> existingData.update(tag -> {
                if (!tag.hasUUID("Owner")) {
                    tag.putUUID("Owner", player.getUUID());
                }
                if (!tag.contains("Tame")) {
                    tag.putBoolean("Tame", true);
                }
                if (!tag.contains("id")) {
                    tag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(this.typeSupplier.get()).toString());
                }
            }));

            InteractionResult result = super.useOn(context);
            if (result.consumesAction() && isResurrection && costLevel > 0 && !player.isCreative()) {
                player.giveExperienceLevels(-costLevel);
            }
            return result;
        }
        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        
        HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hitresult.getType() != HitResult.Type.BLOCK) {
            return super.use(level, player, hand);
        } else if (!(level instanceof net.minecraft.server.level.ServerLevel)) {
            return InteractionResultHolder.success(stack);
        } else {
            CustomData customData = stack.get(DataComponents.ENTITY_DATA);
            boolean isResurrection = false;
            int costLevel = 0;
            if (customData != null) {
                net.minecraft.nbt.CompoundTag tag = customData.copyTag();
                if (tag.getBoolean("ShincolleSpawnEgg")) {
                    isResurrection = true;
                    costLevel = tag.getInt("ShipLevel") / 3;
                }
            }
            
            if (isResurrection && costLevel > 0 && !player.isCreative()) {
                if (player.experienceLevel < costLevel) {
                    player.displayClientMessage(Component.translatable("chat.shincolle:levelfail"), false);
                    return InteractionResultHolder.fail(stack);
                }
            }
            
            InteractionResultHolder<ItemStack> result = super.use(level, player, hand);
            if (result.getResult().consumesAction() && isResurrection && costLevel > 0 && !player.isCreative()) {
                player.giveExperienceLevels(-costLevel);
            }
            return result;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        CustomData customData = stack.get(DataComponents.ENTITY_DATA);
        if (customData != null) {
            net.minecraft.nbt.CompoundTag tag = customData.copyTag();
            if (tag.getBoolean("ShincolleSpawnEgg")) {
                int costLevel = tag.getInt("ShipLevel") / 3;
                tooltipComponents.add(Component.translatable("gui.shincolle.eggText").append(" " + costLevel).withStyle(net.minecraft.ChatFormatting.AQUA));
            }
        }
    }
}
