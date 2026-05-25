package org.trp.shincolle.entity.base;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.trp.shincolle.init.ModItems;
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
        int shipClass = this.ship.getStateMinor(EntityShipBase.STATE_MINOR_SHIP_CLASS);

        switch (shipClass) {
            case -2:
                return new ItemStack[] {
                    new ItemStack(
                        ModItems.GRUDGE.get(),
                        10 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.ABYSS_METAL.get(),
                        10 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.AMMO_LIGHT.get(),
                        10 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.ABYSS_POLYMETAL.get(),
                        10 + this.ship.getRandom().nextInt(8)
                    ),
                };
            case -1:
                return new ItemStack[] {
                    new ItemStack(
                        ModItems.GRUDGE.get(),
                        90 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.ABYSS_METAL.get(),
                        90 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.AMMO_LIGHT.get(),
                        90 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.ABYSS_POLYMETAL.get(),
                        90 + this.ship.getRandom().nextInt(8)
                    ),
                };
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
            case 18:
            case 19:
                return new ItemStack[] {
                    new ItemStack(
                        ModItems.GRUDGE.get(),
                        12 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.ABYSS_METAL.get(),
                        12 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.AMMO_LIGHT.get(),
                        12 + this.ship.getRandom().nextInt(8)
                    ),
                    new ItemStack(
                        ModItems.ABYSS_POLYMETAL.get(),
                        12 + this.ship.getRandom().nextInt(8)
                    ),
                };
            case 12:
            case 13:
            case 14:
            case 15:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 49:
            case 50:
            case 72:
                if (this.ship.getRandom().nextBoolean()) {
                    return new ItemStack[] {
                        new ItemStack(ModItems.GRUDGE_BLOCK.get(), 1),
                        new ItemStack(ModItems.ABYSSIUM.get(), 1),
                        new ItemStack(ModItems.AMMO_HEAVY.get(), 1),
                        new ItemStack(ModItems.POLYMETAL.get(), 1),
                    };
                }
                return new ItemStack[] {
                    new ItemStack(
                        ModItems.GRUDGE_BLOCK.get(),
                        10 + this.ship.getRandom().nextInt(3)
                    ),
                    new ItemStack(
                        ModItems.ABYSSIUM.get(),
                        10 + this.ship.getRandom().nextInt(3)
                    ),
                    new ItemStack(
                        ModItems.AMMO_HEAVY.get(),
                        10 + this.ship.getRandom().nextInt(3)
                    ),
                    new ItemStack(
                        ModItems.POLYMETAL.get(),
                        10 + this.ship.getRandom().nextInt(3)
                    ),
                };
            case 36:
            case 38:
            case 39:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                return new ItemStack[] {
                    new ItemStack(
                        ModItems.GRUDGE.get(),
                        EntityShipBase.KAITAI_AMOUNT_SMALL +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1
                            )
                    ),
                    new ItemStack(
                        ModItems.ABYSS_METAL.get(),
                        EntityShipBase.KAITAI_AMOUNT_SMALL +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1
                            )
                    ),
                    new ItemStack(
                        ModItems.AMMO_LIGHT.get(),
                        EntityShipBase.KAITAI_AMOUNT_SMALL +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1
                            )
                    ),
                    new ItemStack(
                        ModItems.ABYSS_POLYMETAL.get(),
                        EntityShipBase.KAITAI_AMOUNT_SMALL +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1
                            )
                    ),
                };
            case 56:
            case 57:
            case 58:
            case 59:
                return new ItemStack[] {
                    new ItemStack(
                        ModItems.GRUDGE_BLOCK.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1
                            )
                    ),
                    new ItemStack(
                        ModItems.ABYSSIUM.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1
                            )
                    ),
                    new ItemStack(
                        ModItems.AMMO_HEAVY.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1
                            )
                    ),
                    new ItemStack(
                        ModItems.POLYMETAL.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(
                                (int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1
                            )
                    ),
                };
            case 37:
            case 46:
            case 47:
            case 48:
            case 60:
            case 61:
            case 62:
            case 63:
                return new ItemStack[] {
                    new ItemStack(
                        ModItems.GRUDGE_BLOCK.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1)
                    ),
                    new ItemStack(
                        ModItems.ABYSSIUM.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1)
                    ),
                    new ItemStack(
                        ModItems.AMMO_HEAVY.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1)
                    ),
                    new ItemStack(
                        ModItems.POLYMETAL.get(),
                        EntityShipBase.KAITAI_AMOUNT_LARGE +
                            this.ship.getRandom().nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1)
                    ),
                };
            default:
                return new ItemStack[] {
                    ItemStack.EMPTY,
                    ItemStack.EMPTY,
                    ItemStack.EMPTY,
                    ItemStack.EMPTY,
                };
        }
    }
}
