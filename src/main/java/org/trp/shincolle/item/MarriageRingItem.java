package org.trp.shincolle.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.trp.shincolle.attachment.AdmiralData;
import org.trp.shincolle.entity.base.EmotionParticleType;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModDataAttachments;
import org.trp.shincolle.network.S2CAdmiralDataSyncPayload;

import java.util.List;

public class MarriageRingItem extends Item {

    public MarriageRingItem(Properties properties) {
        super(properties);
    }

    public boolean isActive(ItemStack stack) {
        net.minecraft.world.item.component.CustomData customData = stack.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return false;
        }
        return customData.copyTag().getBoolean("isActive");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.pass(stack);
        }
        boolean newActiveState = !isActive(stack);
        if (!newActiveState) {
            stack.remove(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
        } else {
            stack.update(net.minecraft.core.component.DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.EMPTY, data ->
                data.update(tag -> tag.putBoolean("isActive", true))
            );
        }

        AdmiralData data = player.getData(ModDataAttachments.ADMIRAL_DATA);
        data.setRingActive(newActiveState);

        if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, new S2CAdmiralDataSyncPayload(data.serializeNBT()));
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isActive(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && isSelected && entity instanceof Player player) {
            if (player.tickCount % 64 == 0) {
                applyAuraToNearbyShips(player, level);
            }
        }
    }

    private void applyAuraToNearbyShips(Player player, Level level) {
        AABB area = player.getBoundingBox().inflate(6.0, 5.0, 6.0);
        List<EntityShipBase> nearbyShips = level.getEntitiesOfClass(EntityShipBase.class, area);

        for (EntityShipBase ship : nearbyShips) {
            if (ship == null || !ship.isAlive() || !ship.isTame() || !ship.isOwnedBy(player)) {
                continue;
            }

            ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            if (ship.getRandom().nextInt(5) == 0) {
                ship.applyParticleEmotion(EmotionParticleType.HEART);
            }
        }
    }

    @Override
    public void appendHoverText(
        ItemStack stack,
        Item.TooltipContext context,
        List<net.minecraft.network.chat.Component> tooltipComponents,
        net.minecraft.world.item.TooltipFlag tooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (net.neoforged.fml.loading.FMLEnvironment.dist == net.neoforged.api.distmarker.Dist.CLIENT) {
            org.trp.shincolle.client.ClientProxy.appendMarriageRingTooltip(stack, tooltipComponents);
        }
    }
}
