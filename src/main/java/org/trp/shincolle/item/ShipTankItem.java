package org.trp.shincolle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;

import java.util.List;
import java.util.Optional;

public class ShipTankItem extends Item {
    private static final String TAG_VARIANT = "LegacyVariant";
    private static final int[] CAPACITY_BY_VARIANT = new int[]{32000, 128000, 512000, 2048000};

    public ShipTankItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    public int getVariantCount() {
        return CAPACITY_BY_VARIANT.length;
    }

    public int getVariant(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return 0;
        }

        int raw = customData.copyTag().getInt(TAG_VARIANT);
        return Mth.clamp(raw, 0, CAPACITY_BY_VARIANT.length - 1);
    }

    public int getModelVariant(ItemStack stack) {
        return getVariant(stack);
    }

    public ItemStack createVariantStack(int variant) {
        int clamped = Mth.clamp(variant, 0, CAPACITY_BY_VARIANT.length - 1);
        ItemStack stack = new ItemStack(this);
        if (clamped > 0) {
            stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY,
                    data -> data.update(tag -> tag.putInt(TAG_VARIANT, clamped)));
        }
        return stack;
    }

    public void addAllVariantsToCreativeTab(CreativeModeTab.Output output) {
        for (int i = 0; i < CAPACITY_BY_VARIANT.length; i++) {
            output.accept(createVariantStack(i));
        }
    }

    public static int getCapacity(int variant) {
        int clamped = Mth.clamp(variant, 0, CAPACITY_BY_VARIANT.length - 1);
        return CAPACITY_BY_VARIANT[clamped];
    }

    public static int getCapacity(ItemStack stack) {
        if (stack.getItem() instanceof ShipTankItem shipTankItem) {
            return getCapacity(shipTankItem.getVariant(stack));
        }
        return CAPACITY_BY_VARIANT[0];
    }

    @Override
    public Component getName(ItemStack stack) {
        int variant = getVariant(stack);
        String suffix = variant > 0 ? String.valueOf(variant) : "";
        return Component.translatable("item.shincolle:ShipTank" + suffix + ".name");
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        int capacity = getCapacity(stack);
        tooltipComponents.add(Component.translatable("gui.shincolle.shiptank").withStyle(ChatFormatting.GRAY));

        FluidStack fluid = FluidUtil.getFluidContained(stack).orElse(FluidStack.EMPTY);
        int amount = fluid.getAmount();

        if (fluid.isEmpty()) {
            tooltipComponents.add(Component.literal(amount + " / " + capacity + " mB")
                    .withStyle(ChatFormatting.DARK_AQUA));
            return;
        }

        tooltipComponents.add(
                Component.literal(amount + " / " + capacity + " mB ").withStyle(ChatFormatting.AQUA)
                        .append(fluid.getHoverName().copy().withStyle(ChatFormatting.AQUA))
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        Direction side = context.getClickedFace();
        InteractionHand hand = context.getHand();

        if (level.isClientSide) {
            return InteractionResult.PASS;
        }

        Optional<IFluidHandlerItem> itemHandlerOpt = FluidUtil.getFluidHandler(stack);
        if (itemHandlerOpt.isEmpty()) {
            return InteractionResult.PASS;
        }

        IFluidHandlerItem itemHandler = itemHandlerOpt.get();

        Optional<IFluidHandler> blockHandlerOpt = FluidUtil.getFluidHandler(level, pos, side);
        if (blockHandlerOpt.isPresent()) {
            IFluidHandler blockHandler = blockHandlerOpt.get();
            FluidStack contained = itemHandler.getFluidInTank(0);
            FluidStack transferred = contained.isEmpty()
                    ? FluidUtil.tryFluidTransfer(itemHandler, blockHandler, itemHandler.getTankCapacity(0), true)
                    : FluidUtil.tryFluidTransfer(blockHandler, itemHandler, contained, true);

            if (!transferred.isEmpty()) {
                if (player != null) {
                    player.setItemInHand(hand, itemHandler.getContainer());
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        FluidStack contained = itemHandler.getFluidInTank(0);
        if (contained.isEmpty()) {
            FluidActionResult pickup = FluidUtil.tryPickUpFluid(stack, player, level, pos, side);
            if (pickup.isSuccess()) {
                if (player != null) {
                    player.setItemInHand(hand, pickup.getResult());
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            return InteractionResult.PASS;
        }

        BlockState clickedState = level.getBlockState(pos);
        BlockPos placePos = clickedState.canBeReplaced() ? pos : pos.relative(side);
        FluidActionResult placed = FluidUtil.tryPlaceFluid(player, level, hand, placePos, stack, contained);
        if (placed.isSuccess()) {
            if (player != null) {
                player.setItemInHand(hand, placed.getResult());
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }
}