package org.trp.shincolle.crafting;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
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
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.item.LegacyEquipStats;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public final class ShipyardRecipes {
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

    private static final List<Candidate> SMALL_SHIP_CANDIDATES = List.of(
            new Candidate(0, 80, 0),
            new Candidate(1, 90, 0),
            new Candidate(2, 100, 0),
            new Candidate(3, 110, 0),
            new Candidate(16, 120, 1),
            new Candidate(17, 140, 2),
            new Candidate(18, 160, 2),
            new Candidate(19, 180, 2),
            new Candidate(9, 200, 2),
            new Candidate(10, 256, 2)
    );

    private static final List<Candidate> LARGE_SHIP_CANDIDATES = List.of(
            new Candidate(27, 500, 0),
            new Candidate(12, 650, 3),
            new Candidate(14, 800, 2),
            new Candidate(13, 800, 2),
            new Candidate(49, 2000, 2),
            new Candidate(31, 2600, 1),
            new Candidate(72, 2600, 2),
            new Candidate(29, 2700, 1),
            new Candidate(28, 2800, 1),
            new Candidate(21, 3000, 1),
            new Candidate(20, 3000, 3),
            new Candidate(44, 3500, 2),
            new Candidate(15, 3800, 2),
            new Candidate(26, 4600, 2),
            new Candidate(30, 4800, 1),
            new Candidate(33, 5000, 3)
    );

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

        int type = rollShipType(largeShipyard, mats);
        return getShipEntityTypeForType(type, largeShipyard);
    }

    public static boolean addLargeMaterialStock(int[] matStock, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        int[] taggedMats = getShipyardMatsTag(stack);
        if (taggedMats != null) {
            for (int i = 0; i < 4; i++) {
                matStock[i] += taggedMats[i];
            }
            return true;
        }

        if (stack.is(ModItems.GRUDGE.get())) {
            matStock[0] += 1;
            return true;
        }
        if (stack.is(ModItems.ABYSS_METAL.get())) {
            matStock[1] += 1;
            return true;
        }
        if (stack.is(ModItems.AMMO_LIGHT.get()) || stack.is(ModItems.AMMO_HEAVY.get())) {
            matStock[2] += 1;
            return true;
        }
        if (stack.is(ModItems.AMMO_LIGHT_CONTAINER.get()) || stack.is(ModItems.AMMO_HEAVY_CONTAINER.get())) {
            matStock[2] += 9;
            return true;
        }
        if (stack.is(ModItems.ABYSS_POLYMETAL.get())) {
            matStock[3] += 1;
            return true;
        }
        if (stack.is(ModItems.GRUDGE_BLOCK.get())) {
            matStock[0] += 9;
            return true;
        }
        if (stack.is(ModItems.ABYSSIUM.get())) {
            matStock[1] += 9;
            return true;
        }
        if (stack.is(ModItems.POLYMETAL.get())) {
            matStock[3] += 9;
            return true;
        }
        if (stack.is(ModItems.GRUDGE_HEAVY_BLOCK.get())) {
            matStock[0] += 81;
            return true;
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

    private static EntityType<? extends Mob> getShipEntityTypeForType(int type, boolean largeShipyard) {
        return switch (type) {
            case 0 -> ModEntities.DESTROYER_I.get();
            case 1 -> ModEntities.DESTROYER_RO.get();
            case 2 -> ModEntities.DESTROYER_HA.get();
            case 3 -> ModEntities.DESTROYER_NI.get();
            case 9 -> ModEntities.HEAVY_CRUISER_RI.get();
            case 10 -> ModEntities.HEAVY_CRUISER_NE.get();
            case 12 -> ModEntities.CARRIER_WO.get();
            case 13 -> ModEntities.BATTLESHIP_RU.get();
            case 14 -> ModEntities.BATTLESHIP_TA.get();
            case 15 -> ModEntities.BATTLESHIP_RE.get();
            case 16 -> ModEntities.TRANSPORT_WA.get();
            case 17 -> ModEntities.SUBM_KA.get();
            case 18 -> ModEntities.SUBM_YO.get();
            case 19 -> ModEntities.SUBM_SO.get();
            case 20 -> ModEntities.CARRIER_HIME.get();
            case 21 -> ModEntities.AIRFIELD_HIME.get();
            case 26 -> ModEntities.BATTLESHIP_HIME.get();
            case 27 -> ModEntities.DESTROYER_HIME.get();
            case 28 -> ModEntities.HARBOUR_HIME.get();
            case 29 -> ModEntities.ISOLATED_HIME.get();
            case 30 -> ModEntities.MIDWAY_HIME.get();
            case 31 -> ModEntities.NORTHERN_HIME.get();
            case 33 -> ModEntities.CARRIER_W_DEMON.get();
            case 44 -> ModEntities.SUBM_HIME.get();
            case 49 -> ModEntities.CA_HIME.get();
            case 72 -> ModEntities.SSNH.get();
            default -> largeShipyard ? ModEntities.DESTROYER_HIME.get() : ModEntities.DESTROYER_I.get();
        };
    }

    private static Item getShipEggForType(int type, boolean largeShipyard) {
        return switch (type) {
            case 0 -> ModItems.DESTROYER_I_SPAWN_EGG.get();
            case 1 -> ModItems.DESTROYER_RO_SPAWN_EGG.get();
            case 2 -> ModItems.DESTROYER_HA_SPAWN_EGG.get();
            case 3 -> ModItems.DESTROYER_NI_SPAWN_EGG.get();
            case 9 -> ModItems.HEAVY_CRUISER_RI_SPAWN_EGG.get();
            case 10 -> ModItems.HEAVY_CRUISER_NE_SPAWN_EGG.get();
            case 12 -> ModItems.CARRIER_WO_SPAWN_EGG.get();
            case 13 -> ModItems.BATTLESHIP_RU_SPAWN_EGG.get();
            case 14 -> ModItems.BATTLESHIP_TA_SPAWN_EGG.get();
            case 15 -> ModItems.BATTLESHIP_RE_SPAWN_EGG.get();
            case 16 -> ModItems.TRANSPORT_WA_SPAWN_EGG.get();
            case 17 -> ModItems.SUBM_KA_SPAWN_EGG.get();
            case 18 -> ModItems.SUBM_YO_SPAWN_EGG.get();
            case 19 -> ModItems.SUBM_SO_SPAWN_EGG.get();
            case 20 -> ModItems.CARRIER_HIME_SPAWN_EGG.get();
            case 21 -> ModItems.AIRFIELD_HIME_SPAWN_EGG.get();
            case 26 -> ModItems.BATTLESHIP_HIME_SPAWN_EGG.get();
            case 27 -> ModItems.DESTROYER_HIME_SPAWN_EGG.get();
            case 28 -> ModItems.HARBOUR_HIME_SPAWN_EGG.get();
            case 29 -> ModItems.ISOLATED_HIME_SPAWN_EGG.get();
            case 30 -> ModItems.MIDWAY_HIME_SPAWN_EGG.get();
            case 31 -> ModItems.NORTHERN_HIME_SPAWN_EGG.get();
            case 33 -> ModItems.CARRIER_W_DEMON_SPAWN_EGG.get();
            case 44 -> ModItems.SUBM_HIME_SPAWN_EGG.get();
            case 49 -> ModItems.CA_HIME_SPAWN_EGG.get();
            case 72 -> ModItems.SSNH_SPAWN_EGG.get();
            default -> largeShipyard ? ModItems.DESTROYER_HIME_SPAWN_EGG.get() : ModItems.DESTROYER_I_SPAWN_EGG.get();
        };
    }

    private static int rollShipType(boolean largeShipyard, int[] mats) {
        List<Candidate> candidates = largeShipyard ? LARGE_SHIP_CANDIDATES : SMALL_SHIP_CANDIDATES;
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
            return candidates.getFirst().id;
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
}
