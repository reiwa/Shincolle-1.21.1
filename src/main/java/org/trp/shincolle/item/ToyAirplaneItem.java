package org.trp.shincolle.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.network.S2CAdmiralDataSyncPayload;

public class ToyAirplaneItem extends Item {
    public ToyAirplaneItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        ItemStack result = super.finishUsingItem(stack, level, entityLiving);
        if (entityLiving instanceof Player player && !level.isClientSide) {
            player.hurt(player.damageSources().generic(), 1.0F);
            if (BucketRepairItem.getParticleTimer(player) != 0) {
                AdmiralData data = player.getData(ModDataAttachments.ADMIRAL_DATA.get());
                data.setAppearance(1);
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, new S2CAdmiralDataSyncPayload(data.serializeNBT()));
                BucketRepairItem.setParticleTimer(player, 0);
            }
        }
        return result;
    }
}
