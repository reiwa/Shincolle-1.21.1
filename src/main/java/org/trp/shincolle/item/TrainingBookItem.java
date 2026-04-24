package org.trp.shincolle.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.trp.shincolle.Config;
import org.trp.shincolle.entity.base.EntityShipBase;

import java.util.List;

public class TrainingBookItem extends Item {
    public TrainingBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        if (!(interactionTarget instanceof EntityShipBase ship)) {
            return InteractionResult.PASS;
        }

        if (!ship.isTame() || !ship.isOwnedBy(player)) {
            return InteractionResult.PASS;
        }

        if (player.level().isClientSide) {
            return InteractionResult.sidedSuccess(true);
        }

        int minLevelGain = Config.trainingBookLevelMin;
        int maxLevelGain = Math.max(minLevelGain, Config.trainingBookLevelMax);
        int levelGain = minLevelGain;
        if (maxLevelGain > minLevelGain) {
            levelGain += player.getRandom().nextInt(maxLevelGain - minLevelGain + 1);
        }

        if (!ship.addTrainingBookLevel(levelGain)) {
            return InteractionResult.FAIL;
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.sidedSuccess(false);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("gui.shincolle.trainingbook").withStyle(ChatFormatting.GOLD));
    }
}
