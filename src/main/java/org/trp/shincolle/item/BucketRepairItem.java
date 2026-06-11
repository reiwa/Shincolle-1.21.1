package org.trp.shincolle.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.trp.shincolle.init.ModDataAttachments;

public class BucketRepairItem extends Item {
    public BucketRepairItem(Properties properties) {
        super(properties);
    }

    public static void setParticleTimer(Player player, int ticks) {
        player.setData(ModDataAttachments.PARTICLE_TIMER.get(), ticks);
    }

    public static int getParticleTimer(Player player) {
        return player.getData(ModDataAttachments.PARTICLE_TIMER.get());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 40;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player && !level.isClientSide) {
            setParticleTimer(player, 220);
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 220, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 220, 0));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return stack;
    }
}
