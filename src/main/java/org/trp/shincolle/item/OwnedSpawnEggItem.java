package org.trp.shincolle.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

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
        }
        return super.useOn(context);
    }
}
