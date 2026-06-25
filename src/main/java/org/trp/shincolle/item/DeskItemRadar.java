package org.trp.shincolle.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.trp.shincolle.menu.DeskMenu;

public class DeskItemRadar extends Item {

    public DeskItemRadar(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new SimpleMenuProvider(
                (id, inv, p) -> new DeskMenu(id, inv, 1),
                Component.translatable(this.getDescriptionId())
            ), buffer -> {
                buffer.writeInt(1);
                buffer.writeInt(0);
                buffer.writeInt(0);
            });
        }
        
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
