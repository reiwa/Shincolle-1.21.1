package org.trp.shincolle.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.crafting.ShipyardRecipes;
import org.trp.shincolle.init.ModSounds;

class EntityShipBaseKaitai {

    private final EntityShipBase ship;

    EntityShipBaseKaitai(EntityShipBase ship) {
        this.ship = ship;
    }

    boolean useKaitaiHammer(Player player, ItemStack stack) {
        if (
            !this.ship.level().isClientSide &&
            !this.ship.isOwnedBy(player) &&
            !player.hasPermissions(2)
        ) {
            return false;
        }

        if (this.ship.level().isClientSide) {
            return true;
        }

        if (!player.getAbilities().instabuild) {
            this.spawnKaitaiDrops();
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            this.ship.playSound(
                ModSounds.SHIP_DEATH.get(),
                this.ship.getSoundVolume(),
                this.ship.getShipSoundPitch()
            );
        }

        this.ship.applyParticleEmotion(8);
        this.ship.applyEmotesAOE(10.0, 6, false);
        this.ship.discard();
        return true;
    }

    private void spawnKaitaiDrops() {
        if (!(this.ship.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        ItemStack[] drops = this.createKaitaiDrops();
        serverLevel.addFreshEntity(
            new ItemEntity(
                serverLevel,
                this.ship.getX() + 0.5D,
                this.ship.getY() + 0.8D,
                this.ship.getZ() + 0.5D,
                drops[0]
            )
        );
        serverLevel.addFreshEntity(
            new ItemEntity(
                serverLevel,
                this.ship.getX() + 0.5D,
                this.ship.getY() + 0.8D,
                this.ship.getZ() - 0.5D,
                drops[1]
            )
        );
        serverLevel.addFreshEntity(
            new ItemEntity(
                serverLevel,
                this.ship.getX() - 0.5D,
                this.ship.getY() + 0.8D,
                this.ship.getZ() + 0.5D,
                drops[2]
            )
        );
        serverLevel.addFreshEntity(
            new ItemEntity(
                serverLevel,
                this.ship.getX() - 0.5D,
                this.ship.getY() + 0.8D,
                this.ship.getZ() - 0.5D,
                drops[3]
            )
        );

        for (int i = 0; i < this.ship.inventory.getSlots(); ++i) {
            ItemStack invItem = this.ship.inventory.getStackInSlot(i);
            if (invItem.isEmpty()) {
                continue;
            }

            float offsetX = this.ship.getRandom().nextFloat() * 0.8F + 0.1F;
            float offsetY = this.ship.getRandom().nextFloat() * 0.8F + 0.1F;
            float offsetZ = this.ship.getRandom().nextFloat() * 0.8F + 0.1F;
            serverLevel.addFreshEntity(
                new ItemEntity(
                    serverLevel,
                    this.ship.getX() + offsetX,
                    this.ship.getY() + offsetY,
                    this.ship.getZ() + offsetZ,
                    invItem.copy()
                )
            );
        }
    }

    private ItemStack[] createKaitaiDrops() {
        int shipClass = this.ship.getStateComponent().getShipClassId();
        return ShipyardRecipes.getKaitaiDrops(shipClass, this.ship.getRandom());
    }
}
