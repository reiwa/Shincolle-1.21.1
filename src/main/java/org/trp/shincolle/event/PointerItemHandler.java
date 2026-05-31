package org.trp.shincolle.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.PointerItem;

@EventBusSubscriber(modid = Shincolle.MODID)
public class PointerItemHandler {

    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        handleLeftClick(event.getEntity());
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getLevel().isClientSide) {
            if (event.getEntity().isShiftKeyDown()) {
                boolean handled = handleLeftClick(event.getEntity());
                if (handled) {
                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean handleLeftClick(Player player) {
        if (!player.isShiftKeyDown()) {
            return false;
        }
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        boolean hasPointerInMain = main.is(ModItems.POINTER_ITEM.get());
        boolean hasPointerInOff = off.is(ModItems.POINTER_ITEM.get());

        if (hasPointerInMain) {
            if (main.getItem() instanceof PointerItem pointer) {
                pointer.onSwingMiss(player, main);
                return true;
            }
        } else if (hasPointerInOff && main.isEmpty()) {
            if (off.getItem() instanceof PointerItem pointer) {
                pointer.onSwingMiss(player, off);
                return true;
            }
        }
        return false;
    }
}
