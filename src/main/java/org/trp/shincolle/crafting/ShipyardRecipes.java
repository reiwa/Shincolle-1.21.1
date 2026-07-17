package org.trp.shincolle.crafting;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.item.LegacyEquipStats;
import org.trp.shincolle.item.ShipSpawnEggItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLPaths;

import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public final class ShipyardRecipes {
    private static final int MAT_COUNT = 4;
    private static final int SMALL_BASE_POWER = 57600;
    private static final int SMALL_POWER_PER_MAT = 2100;
    private static final int SMALL_MIN_EACH_MAT = 16;
    private static final int SMALL_BASE_TOTAL = 64;

    private static final int LARGE_BASE_POWER = 460800;
    private static final int LARGE_POWER_PER_MAT = 256;
    private static final int LARGE_MIN_EACH_MAT = 100;
    private static final int LARGE_BASE_TOTAL = 400;

    private static final float NORMAL_FLOOR = 0.2F;
    private static final float SMALL_MATS_SCALE = 15.625F;
    private static final float SMALL_EQUIP_RATE_DENOMINATOR = 128.0F;
    private static final float MIN_RANDOM_THRESHOLD = 0.0125F;

    private static final int LAVA_FUEL_MB = 1000;
    private static final int LAVA_FUEL_VALUE = 20000;

    private static List<ShipCandidate> SMALL_SHIP_CANDIDATES = new ArrayList<>(List.of(
            new ShipCandidate(ModEntities.DESTROYER_I.get(), 80, 0),
            new ShipCandidate(ModEntities.DESTROYER_RO.get(), 90, 0),
            new ShipCandidate(ModEntities.DESTROYER_HA.get(), 100, 0),
            new ShipCandidate(ModEntities.DESTROYER_NI.get(), 110, 0),
            new ShipCandidate(ModEntities.TRANSPORT_WA.get(), 120, 1),
            new ShipCandidate(ModEntities.SUBM_KA.get(), 140, 2),
            new ShipCandidate(ModEntities.SUBM_YO.get(), 160, 2),
            new ShipCandidate(ModEntities.SUBM_SO.get(), 180, 2),
            new ShipCandidate(ModEntities.HEAVY_CRUISER_RI.get(), 200, 2),
            new ShipCandidate(ModEntities.HEAVY_CRUISER_NE.get(), 256, 2)
    ));

    private static List<ShipCandidate> LARGE_SHIP_CANDIDATES = new ArrayList<>(List.of(
            new ShipCandidate(ModEntities.DESTROYER_HIME.get(), 500, 0),
            new ShipCandidate(ModEntities.CARRIER_WO.get(), 650, 3),
            new ShipCandidate(ModEntities.BATTLESHIP_TA.get(), 800, 2),
            new ShipCandidate(ModEntities.BATTLESHIP_RU.get(), 800, 2),
            new ShipCandidate(ModEntities.CA_HIME.get(), 2000, 2),
            new ShipCandidate(ModEntities.NORTHERN_HIME.get(), 2600, 1),
            new ShipCandidate(ModEntities.SSNH.get(), 2600, 2),
            new ShipCandidate(ModEntities.ISOLATED_HIME.get(), 2700, 1),
            new ShipCandidate(ModEntities.HARBOUR_HIME.get(), 2800, 1),
            new ShipCandidate(ModEntities.AIRFIELD_HIME.get(), 3000, 1),
            new ShipCandidate(ModEntities.CARRIER_HIME.get(), 3000, 3),
            new ShipCandidate(ModEntities.SUBM_HIME.get(), 3500, 2),
            new ShipCandidate(ModEntities.BATTLESHIP_RE.get(), 3800, 2),
            new ShipCandidate(ModEntities.BATTLESHIP_HIME.get(), 4600, 2),
            new ShipCandidate(ModEntities.MIDWAY_HIME.get(), 4800, 1),
            new ShipCandidate(ModEntities.CARRIER_W_DEMON.get(), 5000, 3)
    ));

    private static final List<Candidate> SMALL_EQUIP_TYPE_CANDIDATES = List.of(
            new Candidate(18, 80, 1),
            new Candidate(26, 80, 2),
            new Candidate(27, 80, 0),
            new Candidate(25, 90, 0),
            new Candidate(20, 100, 2),
            new Candidate(24, 120, 1),
            new Candidate(28, 120, 2),
            new Candidate(0, 128, 2),
            new Candidate(4, 160, 2),
            new Candidate(14, 200, 0),
            new Candidate(12, 256, 3),
            new Candidate(1, 320, 2)
    );

    private static final List<Candidate> LARGE_EQUIP_TYPE_CANDIDATES = List.of(
            new Candidate(19, 500, 1),
            new Candidate(21, 800, 2),
            new Candidate(29, 1000, 2),
            new Candidate(13, 1000, 3),
            new Candidate(5, 1200, 2),
            new Candidate(16, 1400, 0),
            new Candidate(2, 1600, 2),
            new Candidate(15, 2000, 0),
            new Candidate(6, 2400, 3),
            new Candidate(8, 2400, 3),
            new Candidate(10, 2400, 3),
            new Candidate(22, 2800, 3),
            new Candidate(17, 3200, 0),
            new Candidate(7, 3800, 3),
            new Candidate(9, 3800, 3),
            new Candidate(11, 3800, 3),
            new Candidate(3, 4400, 2),
            new Candidate(23, 5000, 3)
    );

    private ShipyardRecipes() {
    }

    public static int getSmallMaterialType(ItemStack stack) {
        if (stack.isEmpty()) {
            return -1;
        }
        if (stack.is(ModItems.GRUDGE.get())) {
            return 0;
        }
        if (stack.is(ModItems.ABYSS_METAL.get())) {
            return 1;
        }
        if (stack.is(ModItems.AMMO_LIGHT.get()) || stack.is(ModItems.AMMO_LIGHT_CONTAINER.get())
                || stack.is(ModItems.AMMO_HEAVY.get()) || stack.is(ModItems.AMMO_HEAVY_CONTAINER.get())) {
            return 2;
        }
        if (stack.is(ModItems.ABYSS_POLYMETAL.get())) {
            return 3;
        }
        if (isFuel(stack)) {
            return 4;
        }
        return -1;
    }

    public static boolean isFuel(ItemStack stack) {
        return getFuelValue(stack) > 0;
    }

    public static int getFuelValue(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        if (stack.is(Items.LAVA_BUCKET)) {
            return LAVA_FUEL_VALUE;
        }
        int burn = stack.getBurnTime(RecipeType.SMELTING);
        if (burn > 0) {
            return burn;
        }
        return canDrainLavaFuel(stack) ? LAVA_FUEL_VALUE : 0;
    }

    public static ItemStack consumeOneFuel(ItemStack stack) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        if (stack.is(Items.LAVA_BUCKET)) {
            return new ItemStack(Items.BUCKET);
        }

        if (stack.getCount() == 1) {
            Optional<IFluidHandlerItem> handlerOptional = FluidUtil.getFluidHandler(stack.copyWithCount(1));
            if (handlerOptional.isPresent()) {
                IFluidHandlerItem handler = handlerOptional.get();
                FluidStack drained = handler.drain(new FluidStack(Fluids.LAVA, LAVA_FUEL_MB), IFluidHandler.FluidAction.EXECUTE);
                if (isLavaFuelStack(drained)) {
                    return handler.getContainer();
                }
            }
        }

        ItemStack remaining = stack.copy();
        remaining.shrink(1);
        return remaining;
    }

    private static boolean canDrainLavaFuel(ItemStack stack) {
        if (stack.isEmpty() || stack.getCount() != 1) {
            return false;
        }

        Optional<IFluidHandlerItem> handlerOptional = FluidUtil.getFluidHandler(stack.copyWithCount(1));
        if (handlerOptional.isEmpty()) {
            return false;
        }

        IFluidHandlerItem handler = handlerOptional.get();
        FluidStack drained = handler.drain(new FluidStack(Fluids.LAVA, LAVA_FUEL_MB), IFluidHandler.FluidAction.SIMULATE);
        return isLavaFuelStack(drained);
    }

    private static boolean isLavaFuelStack(FluidStack stack) {
        return !stack.isEmpty()
                && stack.getAmount() == LAVA_FUEL_MB
                && (stack.getFluid() == Fluids.LAVA || stack.getFluid() == Fluids.FLOWING_LAVA);
    }

    public static boolean canSmallBuild(int[] mats) {
        return mats[0] >= SMALL_MIN_EACH_MAT
                && mats[1] >= SMALL_MIN_EACH_MAT
                && mats[2] >= SMALL_MIN_EACH_MAT
                && mats[3] >= SMALL_MIN_EACH_MAT;
    }

    public static int calcSmallGoalPower(int[] mats) {
        if (!canSmallBuild(mats)) {
            return 0;
        }
        int total = mats[0] + mats[1] + mats[2] + mats[3];
        return SMALL_BASE_POWER + Math.max(0, total - SMALL_BASE_TOTAL) * SMALL_POWER_PER_MAT;
    }

    public static boolean canLargeBuild(int[] mats) {
        return mats[0] >= LARGE_MIN_EACH_MAT
                && mats[1] >= LARGE_MIN_EACH_MAT
                && mats[2] >= LARGE_MIN_EACH_MAT
                && mats[3] >= LARGE_MIN_EACH_MAT;
    }

    public static int calcLargeGoalPower(int[] mats) {
        if (!canLargeBuild(mats)) {
            return 0;
        }
        int total = mats[0] + mats[1] + mats[2] + mats[3];
        return LARGE_BASE_POWER + Math.max(0, total - LARGE_BASE_TOTAL) * LARGE_POWER_PER_MAT;
    }

    public static ItemStack createSmallShipResult(int[] mats) {
        ItemStack result = new ItemStack(ModItems.SHIPSPAWNEGGS.get());
        putShipyardMatsTag(result, mats, false);
        return result;
    }

    public static ItemStack createLargeShipResult(int[] mats) {
        ItemStack result = new ItemStack(ModItems.SHIPSPAWNEGGL.get());
        putShipyardMatsTag(result, mats, true);
        return result;
    }

    public static ItemStack createSmallEquipResult(int[] mats) {
        int totalMats = sumMats(mats);
        float equipRate = Math.min(totalMats / SMALL_EQUIP_RATE_DENOMINATOR, 1.0F);

        if (ThreadLocalRandom.current().nextFloat() < equipRate) {
            int rollType = rollEquipType(false, mats);
            ItemStack equip = rollEquipOfType(rollType, totalMats, false);
            if (!equip.isEmpty()) {
                return equip;
            }
        }

        if (ThreadLocalRandom.current().nextBoolean()) {
            return new ItemStack(ModItems.AMMO_LIGHT_CONTAINER.get(), 11 + ThreadLocalRandom.current().nextInt(11));
        }
        return new ItemStack(ModItems.AMMO_HEAVY_CONTAINER.get(), 2 + ThreadLocalRandom.current().nextInt(2));
    }

    public static ItemStack createLargeEquipResult(int[] mats) {
        int totalMats = sumMats(mats);
        int rollType = rollEquipType(true, mats);
        ItemStack equip = rollEquipOfType(rollType, totalMats, true);
        if (!equip.isEmpty()) {
            return equip;
        }

        Item fallback = ModItems.EQUIP_CANNON.get();
        if (fallback instanceof LegacyEquipItem legacyEquipItem) {
            return legacyEquipItem.createVariantStack(0);
        }
        return new ItemStack(fallback);
    }

    public static EntityType<? extends Mob> rollShipEntityType(boolean largeShipyard, ItemStack stack) {
        int[] mats = getShipyardMatsTag(stack);
        if (mats == null) {
            mats = new int[]{0, 0, 0, 0};
        }

        return rollShipType(largeShipyard, mats);
    }

    public static int getShipClassFromEntityType(EntityType<?> type) {
        if (type == ModEntities.DESTROYER_I.get()) return 0;
        if (type == ModEntities.DESTROYER_RO.get()) return 1;
        if (type == ModEntities.DESTROYER_HA.get()) return 2;
        if (type == ModEntities.DESTROYER_NI.get()) return 3;
        if (type == ModEntities.HEAVY_CRUISER_RI.get()) return 9;
        if (type == ModEntities.HEAVY_CRUISER_NE.get()) return 10;
        if (type == ModEntities.CARRIER_WO.get()) return 12;
        if (type == ModEntities.BATTLESHIP_RU.get()) return 13;
        if (type == ModEntities.BATTLESHIP_TA.get()) return 14;
        if (type == ModEntities.BATTLESHIP_RE.get()) return 15;
        if (type == ModEntities.TRANSPORT_WA.get()) return 16;
        if (type == ModEntities.SUBM_KA.get()) return 17;
        if (type == ModEntities.SUBM_YO.get()) return 18;
        if (type == ModEntities.SUBM_SO.get()) return 19;
        if (type == ModEntities.CARRIER_HIME.get()) return 20;
        if (type == ModEntities.AIRFIELD_HIME.get()) return 21;
        if (type == ModEntities.BATTLESHIP_HIME.get()) return 26;
        if (type == ModEntities.DESTROYER_HIME.get()) return 27;
        if (type == ModEntities.HARBOUR_HIME.get()) return 28;
        if (type == ModEntities.ISOLATED_HIME.get()) return 29;
        if (type == ModEntities.MIDWAY_HIME.get()) return 30;
        if (type == ModEntities.NORTHERN_HIME.get()) return 31;
        if (type == ModEntities.CARRIER_W_DEMON.get()) return 33;
        if (type == ModEntities.SUBM_HIME.get()) return 44;
        if (type == ModEntities.CA_HIME.get()) return 49;
        if (type == ModEntities.SSNH.get()) return 72;
        if (type == ModEntities.DESTROYER_SHIMAKAZE.get()) return 36;
        if (type == ModEntities.BB_KONGOU.get()) return 60;
        if (type == ModEntities.BB_HIEI.get()) return 61;
        if (type == ModEntities.BB_HARUNA.get()) return 62;
        if (type == ModEntities.BB_KIRISHIMA.get()) return 63;
        if (type == ModEntities.BATTLESHIP_NAGATO.get()) return 37;
        if (type == ModEntities.BATTLESHIP_YAMATO.get()) return 46;
        if (type == ModEntities.SUBM_U511.get()) return 38;
        if (type == ModEntities.SUBM_RO500.get()) return 39;
        if (type == ModEntities.CARRIER_KAGA.get()) return 47;
        if (type == ModEntities.CARRIER_AKAGI.get()) return 48;
        if (type == ModEntities.DESTROYER_AKATSUKI.get()) return 51;
        if (type == ModEntities.DESTROYER_HIBIKI.get()) return 52;
        if (type == ModEntities.DESTROYER_IKAZUCHI.get()) return 53;
        if (type == ModEntities.DESTROYER_INAZUMA.get()) return 54;
        if (type == ModEntities.CRUISER_TENRYUU.get()) return 56;
        if (type == ModEntities.CRUISER_TATSUTA.get()) return 57;
        if (type == ModEntities.CRUISER_ATAGO.get()) return 58;
        if (type == ModEntities.CRUISER_TAKAO.get()) return 59;
        return -1;
    }

    public static ItemStack[] getKaitaiDrops(int shipClass, RandomSource random) {
        switch (shipClass) {
            case -2:
                return new ItemStack[] {
                    new ItemStack(ModItems.GRUDGE.get(), 10 + random.nextInt(8)),
                    new ItemStack(ModItems.ABYSS_METAL.get(), 10 + random.nextInt(8)),
                    new ItemStack(ModItems.AMMO_LIGHT.get(), 10 + random.nextInt(8)),
                    new ItemStack(ModItems.ABYSS_POLYMETAL.get(), 10 + random.nextInt(8))
                };
            case -1:
                return new ItemStack[] {
                    new ItemStack(ModItems.GRUDGE.get(), 90 + random.nextInt(8)),
                    new ItemStack(ModItems.ABYSS_METAL.get(), 90 + random.nextInt(8)),
                    new ItemStack(ModItems.AMMO_LIGHT.get(), 90 + random.nextInt(8)),
                    new ItemStack(ModItems.ABYSS_POLYMETAL.get(), 90 + random.nextInt(8))
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
                    new ItemStack(ModItems.GRUDGE.get(), 12 + random.nextInt(8)),
                    new ItemStack(ModItems.ABYSS_METAL.get(), 12 + random.nextInt(8)),
                    new ItemStack(ModItems.AMMO_LIGHT.get(), 12 + random.nextInt(8)),
                    new ItemStack(ModItems.ABYSS_POLYMETAL.get(), 12 + random.nextInt(8))
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
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 49:
            case 50:
            case 72:
                if (org.trp.shincolle.Config.consumptionLevel == 0) {
                    return new ItemStack[] {
                        new ItemStack(ModItems.GRUDGE_BLOCK.get(), 1),
                        new ItemStack(ModItems.ABYSSIUM.get(), 1),
                        new ItemStack(ModItems.AMMO_HEAVY.get(), 1),
                        new ItemStack(ModItems.POLYMETAL.get(), 1)
                    };
                }
                return new ItemStack[] {
                    new ItemStack(ModItems.GRUDGE_BLOCK.get(), 10 + random.nextInt(3)),
                    new ItemStack(ModItems.ABYSSIUM.get(), 10 + random.nextInt(3)),
                    new ItemStack(ModItems.AMMO_HEAVY.get(), 10 + random.nextInt(3)),
                    new ItemStack(ModItems.POLYMETAL.get(), 10 + random.nextInt(3))
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
                    new ItemStack(ModItems.GRUDGE.get(), EntityShipBase.KAITAI_AMOUNT_SMALL + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1)),
                    new ItemStack(ModItems.ABYSS_METAL.get(), EntityShipBase.KAITAI_AMOUNT_SMALL + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1)),
                    new ItemStack(ModItems.AMMO_LIGHT.get(), EntityShipBase.KAITAI_AMOUNT_SMALL + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1)),
                    new ItemStack(ModItems.ABYSS_POLYMETAL.get(), EntityShipBase.KAITAI_AMOUNT_SMALL + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_SMALL * 0.25F) + 1))
                };
            case 56:
            case 57:
            case 58:
            case 59:
                return new ItemStack[] {
                    new ItemStack(ModItems.GRUDGE_BLOCK.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1)),
                    new ItemStack(ModItems.ABYSSIUM.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1)),
                    new ItemStack(ModItems.AMMO_HEAVY.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1)),
                    new ItemStack(ModItems.POLYMETAL.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt((int) (EntityShipBase.KAITAI_AMOUNT_LARGE * 0.25F) + 1))
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
                    new ItemStack(ModItems.GRUDGE_BLOCK.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1)),
                    new ItemStack(ModItems.ABYSSIUM.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1)),
                    new ItemStack(ModItems.AMMO_HEAVY.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1)),
                    new ItemStack(ModItems.POLYMETAL.get(), EntityShipBase.KAITAI_AMOUNT_LARGE + random.nextInt(EntityShipBase.KAITAI_AMOUNT_LARGE + 1))
                };
            default:
                return new ItemStack[] {
                    ItemStack.EMPTY,
                    ItemStack.EMPTY,
                    ItemStack.EMPTY,
                    ItemStack.EMPTY
                };
        }
    }

    public static boolean addLargeMaterialStock(int[] matStock, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        int level = org.trp.shincolle.Config.consumptionLevel;
        int multiplier = level == 0 ? 10 : (level == 1 ? 2 : 1);

        int[] taggedMats = getShipyardMatsTag(stack);
        if (taggedMats != null) {
            for (int i = 0; i < 4; i++) {
                matStock[i] += taggedMats[i];
            }
            return true;
        }

        if (stack.is(ModItems.GRUDGE.get())) {
            matStock[0] += multiplier;
            return true;
        }
        if (stack.is(ModItems.ABYSS_METAL.get())) {
            matStock[1] += multiplier;
            return true;
        }
        if (stack.is(ModItems.AMMO_LIGHT.get())) {
            matStock[2] += multiplier;
            return true;
        }
        if (stack.is(ModItems.AMMO_HEAVY.get())) {
            matStock[2] += 4 * multiplier;
            return true;
        }
        if (stack.is(ModItems.AMMO_LIGHT_CONTAINER.get())) {
            matStock[2] += 9 * multiplier;
            return true;
        }
        if (stack.is(ModItems.AMMO_HEAVY_CONTAINER.get())) {
            matStock[2] += 36 * multiplier;
            return true;
        }
        if (stack.is(ModItems.ABYSS_POLYMETAL.get())) {
            matStock[3] += multiplier;
            return true;
        }
        if (stack.is(ModItems.GRUDGE_BLOCK.get())) {
            matStock[0] += 9 * multiplier;
            return true;
        }
        if (stack.is(ModItems.ABYSSIUM.get())) {
            matStock[1] += 9 * multiplier;
            return true;
        }
        if (stack.is(ModItems.POLYMETAL.get())) {
            matStock[3] += 9 * multiplier;
            return true;
        }
        if (stack.is(ModItems.GRUDGE_HEAVY_BLOCK.get())) {
            matStock[0] += 81 * multiplier;
            return true;
        }

        
        int shipClass = -3;
        if (stack.is(ModItems.SHIPSPAWNEGGS.get())) {
            shipClass = -2;
        } else if (stack.is(ModItems.SHIPSPAWNEGGL.get())) {
            shipClass = -1;
        } else if (stack.getItem() instanceof ShipSpawnEggItem shipEgg) {
            shipClass = getShipClassFromEntityType(shipEgg.getEntityType());
        }

        if (shipClass >= -2) {
            ItemStack[] drops = getKaitaiDrops(shipClass, RandomSource.create());
            for (ItemStack drop : drops) {
                if (!drop.isEmpty()) {
                    ItemStack copy = drop.copy();
                    while (!copy.isEmpty()) {
                        addLargeMaterialStock(matStock, copy);
                        copy.shrink(1);
                    }
                }
            }
            return true;
        }

        
        if (stack.getItem() instanceof LegacyEquipItem legacyEquipItem) {
            int equipId = legacyEquipItem.getEquipId(stack);
            int[] misc = LegacyEquipStats.getMiscAttrs(equipId);
            if (misc != null && misc.length >= 5) {
                int cost = misc[3];
                int matType = misc[4];
                if (matType >= 0 && matType < MAT_COUNT) {
                    int share = Math.max(1, cost / 20);

                    
                    for (int i = 0; i < 4; i++) {
                        int amount = share + ThreadLocalRandom.current().nextInt(Math.max(1, share / 4));
                        matStock[i] += amount * multiplier;
                    }

                    
                    int extra = share + ThreadLocalRandom.current().nextInt(Math.max(1, share / 4));
                    matStock[matType] += extra * multiplier;
                    return true;
                }
            }
        }

        return false;
    }

    public static ItemStack createLargeOutputMaterial(int selectMat, boolean compressed) {
        return switch (selectMat) {
            case 0 -> new ItemStack(compressed ? ModItems.GRUDGE_BLOCK.get() : ModItems.GRUDGE.get(), 1);
            case 1 -> new ItemStack(compressed ? ModItems.ABYSSIUM.get() : ModItems.ABYSS_METAL.get(), 1);
            case 2 -> new ItemStack(compressed ? ModItems.AMMO_LIGHT_CONTAINER.get() : ModItems.AMMO_LIGHT.get(), 1);
            case 3 -> new ItemStack(compressed ? ModItems.POLYMETAL.get() : ModItems.ABYSS_POLYMETAL.get(), 1);
            default -> ItemStack.EMPTY;
        };
    }

    public static void moveBuildMaterialAmount(int[] matBuild, int[] matStock, int matType, int value) {
        if (matType < 0 || matType >= 4) {
            return;
        }

        int[] amounts = new int[]{1000, 100, 10, 1};
        int step = amounts[value % 4];
        boolean stockToBuild = value <= 3;
        if (stockToBuild) {
            step = Math.min(step, matStock[matType]);
            step = Math.min(step, 1000 - matBuild[matType]);
            matStock[matType] -= step;
            matBuild[matType] += step;
        } else {
            step = Math.min(step, matBuild[matType]);
            matBuild[matType] -= step;
            matStock[matType] += step;
        }
    }

    public static int[] getCurrentSmallMaterialAmount(ItemStack[] stacks) {
        int[] mats = new int[4];
        for (int i = 0; i < 4; i++) {
            mats[i] = stacks[i].isEmpty() ? 0 : stacks[i].getCount();
        }
        return mats;
    }

    private static void putShipyardMatsTag(ItemStack stack, int[] mats, boolean large) {
        int[] data = new int[]{mats[0], mats[1], mats[2], mats[3]};
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> customData.update(tag -> {
            tag.putIntArray("ShipyardMats", data);
            tag.putBoolean("LargeShipyard", large);
        }));
    }

    private static int[] getShipyardMatsTag(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return null;
        }
        CompoundTag tag = customData.copyTag();
        if (!tag.contains("ShipyardMats", Tag.TAG_INT_ARRAY)) {
            return null;
        }
        int[] mats = tag.getIntArray("ShipyardMats");
        if (mats.length < 4) {
            return null;
        }
        return new int[]{mats[0], mats[1], mats[2], mats[3]};
    }

    public static void loadConfig() {
        Path configDir = FMLPaths.CONFIGDIR.get().resolve("shincolle");
        Path configFile = configDir.resolve("shipyard_recipes.json");

        if (!Files.exists(configDir)) {
            try {
                Files.createDirectories(configDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (!Files.exists(configFile)) {
            writeDefaultConfig(configFile, gson);
            return;
        }

        readConfig(configFile, gson);
    }

    private static void writeDefaultConfig(Path configFile, Gson gson) {
        JsonObject root = new JsonObject();
        
        JsonArray smallArray = new JsonArray();
        for (ShipCandidate candidate : SMALL_SHIP_CANDIDATES) {
            JsonObject obj = new JsonObject();
            obj.addProperty("result_ship", BuiltInRegistries.ENTITY_TYPE.getKey(candidate.entityType).toString());
            obj.addProperty("target_total_mats", candidate.mean);
            obj.addProperty("preferred_material", ShipyardMaterial.fromIndex(candidate.preferredMaterial).getSerializedName());
            smallArray.add(obj);
        }
        root.add("small_shipyards", smallArray);

        JsonArray largeArray = new JsonArray();
        for (ShipCandidate candidate : LARGE_SHIP_CANDIDATES) {
            JsonObject obj = new JsonObject();
            obj.addProperty("result_ship", BuiltInRegistries.ENTITY_TYPE.getKey(candidate.entityType).toString());
            obj.addProperty("target_total_mats", candidate.mean);
            obj.addProperty("preferred_material", ShipyardMaterial.fromIndex(candidate.preferredMaterial).getSerializedName());
            largeArray.add(obj);
        }
        root.add("large_shipyards", largeArray);

        try (Writer writer = Files.newBufferedWriter(configFile)) {
            gson.toJson(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readConfig(Path configFile, Gson gson) {
        try (Reader reader = Files.newBufferedReader(configFile)) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            
            if (root.has("small_shipyards")) {
                JsonArray smallArray = root.getAsJsonArray("small_shipyards");
                List<ShipCandidate> tempSmall = new ArrayList<>();
                for (int i = 0; i < smallArray.size(); i++) {
                    JsonObject obj = smallArray.get(i).getAsJsonObject();
                    String shipId = obj.get("result_ship").getAsString();
                    int targetMats = obj.get("target_total_mats").getAsInt();
                    String preferredMatStr = obj.get("preferred_material").getAsString();
                    ResourceLocation resLoc = ResourceLocation.parse(shipId);
                    if (BuiltInRegistries.ENTITY_TYPE.containsKey(resLoc)) {
                        EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(resLoc);
                        @SuppressWarnings("unchecked")
                        EntityType<? extends Mob> mobType = (EntityType<? extends Mob>) entityType;
                        int matIndex = -1;
                        for (ShipyardMaterial mat : ShipyardMaterial.values()) {
                            if (mat.getSerializedName().equals(preferredMatStr)) {
                                matIndex = mat.getIndex();
                                break;
                            }
                        }
                        tempSmall.add(new ShipCandidate(mobType, targetMats, matIndex));
                    }
                }
                if (!tempSmall.isEmpty()) {
                    SMALL_SHIP_CANDIDATES = tempSmall;
                }
            }

            if (root.has("large_shipyards")) {
                JsonArray largeArray = root.getAsJsonArray("large_shipyards");
                List<ShipCandidate> tempLarge = new ArrayList<>();
                for (int i = 0; i < largeArray.size(); i++) {
                    JsonObject obj = largeArray.get(i).getAsJsonObject();
                    String shipId = obj.get("result_ship").getAsString();
                    int targetMats = obj.get("target_total_mats").getAsInt();
                    String preferredMatStr = obj.get("preferred_material").getAsString();
                    
                    ResourceLocation resLoc = ResourceLocation.parse(shipId);
                    if (BuiltInRegistries.ENTITY_TYPE.containsKey(resLoc)) {
                        EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(resLoc);
                        @SuppressWarnings("unchecked")
                        EntityType<? extends Mob> mobType = (EntityType<? extends Mob>) entityType;
                        int matIndex = -1;
                        for (ShipyardMaterial mat : ShipyardMaterial.values()) {
                            if (mat.getSerializedName().equals(preferredMatStr)) {
                                matIndex = mat.getIndex();
                                break;
                            }
                        }
                        tempLarge.add(new ShipCandidate(mobType, targetMats, matIndex));
                    }
                }
                if (!tempLarge.isEmpty()) {
                    LARGE_SHIP_CANDIDATES = tempLarge;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static EntityType<? extends Mob> rollShipType(boolean largeShipyard, int[] mats) {
        List<ShipCandidate> candidates = largeShipyard ? LARGE_SHIP_CANDIDATES : SMALL_SHIP_CANDIDATES;
        int totalMats = sumMats(mats);

        float[] probs = new float[candidates.size()];
        float totalProb = 0.0F;
        for (int i = 0; i < candidates.size(); i++) {
            ShipCandidate candidate = candidates.get(i);
            int meanNew = candidate.preferredMaterial >= 0 && candidate.preferredMaterial <= 3
                    ? candidate.mean - mats[candidate.preferredMaterial]
                    : candidate.mean;
            int meanDist = Math.abs(totalMats - meanNew);
            if (!largeShipyard) {
                meanDist = (int) (meanDist * SMALL_MATS_SCALE);
            }
            float prob = getNormDist(meanDist);
            probs[i] = prob;
            totalProb += prob;
        }

        if (totalProb <= 0.0F) {
            return candidates.getFirst().entityType;
        }

        float random = ThreadLocalRandom.current().nextFloat() * totalProb;
        float sum = MIN_RANDOM_THRESHOLD;
        for (int i = 0; i < probs.length; i++) {
            sum += probs[i];
            if (sum > random) {
                return candidates.get(i).entityType;
            }
        }
        return candidates.getLast().entityType;
    }

    private static int rollEquipType(boolean largeShipyard, int[] mats) {
        List<Candidate> candidates = largeShipyard ? LARGE_EQUIP_TYPE_CANDIDATES : SMALL_EQUIP_TYPE_CANDIDATES;
        int totalMats = sumMats(mats);

        float[] probs = new float[candidates.size()];
        float totalProb = 0.0F;
        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            int meanNew = candidate.preferredMaterial >= 0 && candidate.preferredMaterial <= 3
                    ? candidate.mean - mats[candidate.preferredMaterial]
                    : candidate.mean;
            int meanDist = Math.abs(totalMats - meanNew);
            if (!largeShipyard) {
                meanDist = (int) (meanDist * SMALL_MATS_SCALE);
            }

            float prob = getNormDist(meanDist);
            probs[i] = prob;
            totalProb += prob;
        }

        if (totalProb <= 0.0F) {
            return -1;
        }

        float random = ThreadLocalRandom.current().nextFloat() * totalProb;
        float sum = MIN_RANDOM_THRESHOLD;
        for (int i = 0; i < probs.length; i++) {
            sum += probs[i];
            if (sum > random) {
                return candidates.get(i).id;
            }
        }

        return candidates.getLast().id;
    }

    private static ItemStack rollEquipOfType(int type, int totalMats, boolean largeShipyard) {
        if (type < 0) {
            return ItemStack.EMPTY;
        }

        int scaledMats = largeShipyard ? totalMats : (int) (totalMats * SMALL_MATS_SCALE);
        Map<Integer, int[]> miscAttrs = LegacyEquipStats.getAllMiscAttrs();
        int[] equipIds = new int[miscAttrs.size()];
        float[] probs = new float[miscAttrs.size()];

        int count = 0;
        float totalProb = 0.0F;
        for (Map.Entry<Integer, int[]> entry : miscAttrs.entrySet()) {
            int equipId = entry.getKey();
            int[] misc = entry.getValue();
            if (misc.length < 3 || misc[1] != type) {
                continue;
            }

            int meanDist = Math.abs(scaledMats - misc[2]);
            float prob = getNormDist(meanDist);
            equipIds[count] = equipId;
            probs[count] = prob;
            totalProb += prob;
            count++;
        }

        if (count == 0 || totalProb <= 0.0F) {
            return ItemStack.EMPTY;
        }

        float random = ThreadLocalRandom.current().nextFloat() * totalProb;
        float sum = 0.0F;
        for (int i = 0; i < count; i++) {
            sum += probs[i];
            if (sum > random) {
                return createEquipStackFromEquipId(equipIds[i]);
            }
        }

        return createEquipStackFromEquipId(equipIds[count - 1]);
    }

    private static ItemStack createEquipStackFromEquipId(int equipId) {
        if (equipId < 0) {
            return ItemStack.EMPTY;
        }

        int itemType = equipId % 100;
        int variant = equipId / 100;
        Item equipItem = resolveEquipItemByType(itemType);
        if (equipItem == null) {
            return ItemStack.EMPTY;
        }

        if (equipItem instanceof LegacyEquipItem legacyEquipItem) {
            return legacyEquipItem.createVariantStack(variant);
        }

        return new ItemStack(equipItem);
    }

    private static Item resolveEquipItemByType(int itemType) {
        return switch (itemType) {
            case 0, 1, 2, 3 -> ModItems.EQUIP_CANNON.get();
            case 4, 5 -> ModItems.EQUIP_TORPEDO.get();
            case 6, 7, 8, 9, 10, 11, 12, 13 -> ModItems.EQUIP_AIRPLANE.get();
            case 14, 15 -> ModItems.EQUIP_RADAR.get();
            case 16, 17 -> ModItems.EQUIP_TURBINE.get();
            case 18, 19 -> ModItems.EQUIP_ARMOR.get();
            case 20, 21 -> ModItems.EQUIP_MACHINEGUN.get();
            case 22, 23 -> ModItems.EQUIP_CATAPULT.get();
            case 24 -> ModItems.EQUIP_DRUM.get();
            case 25 -> ModItems.EQUIP_COMPASS.get();
            case 26 -> ModItems.EQUIP_FLARE.get();
            case 27 -> ModItems.EQUIP_SEARCHLIGHT.get();
            case 28, 29 -> ModItems.EQUIP_AMMO.get();
            default -> null;
        };
    }

    private static int sumMats(int[] mats) {
        return mats[0] + mats[1] + mats[2] + mats[3];
    }

    private static float getNormDist(int x) {
        float value = calcNormalDist(0.5F - x * 2.5E-4F) * 0.50132567F;
        return Math.max(value, NORMAL_FLOOR);
    }

    private static float calcNormalDist(float x) {
        float s1 = 2.5066283F;
        float s2 = 1.0F / ((float) 0.2 * s1);
        float s3 = x - (float) 0.5;
        float s4 = -(s3 * s3);
        float s5 = 2.0F * (float) 0.2 * (float) 0.2;
        return (float) (s2 * Math.exp(s4 / s5));
    }

    private record Candidate(int id, int mean, int preferredMaterial) {
    }

    private record ShipCandidate(EntityType<? extends Mob> entityType, int mean, int preferredMaterial) {
    }
}
