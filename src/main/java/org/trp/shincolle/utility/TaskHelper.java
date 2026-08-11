package org.trp.shincolle.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.trp.shincolle.Config;
import org.trp.shincolle.block.entity.WayPointBlockEntity;
import org.trp.shincolle.entity.EntityShipFishingHook;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import java.util.ArrayList;
import java.util.List;

public class TaskHelper {
    
    private TaskHelper() {}

    public static void onUpdateTask(EntityShipBase host) {
        if (host.getIsSitting() || !host.isAlive()) {
            return;
        }
        int taskId = host.getStateComponent().getTaskId();
        switch (taskId) {
            case 1: 
                onUpdateCooking(host);
                break;
            case 2: 
                onUpdateFishing(host);
                break;
            case 3: 
                onUpdateMining(host);
                break;
            case 4: 
                onUpdateCrafting(host);
                break;
            default:
                break;
        }
    }

    public static void onUpdateCooking(EntityShipBase host) {
        if (host == null || host.level().isClientSide) return;
        ItemStack mainStack = host.getHeldItemMainhandSlot();
        ItemStack offhandStack = host.getHeldItemOffhandSlot();

        final net.minecraft.world.item.Item originalMainItem = mainStack.getItem();
        final net.minecraft.world.item.Item originalOffhandItem = offhandStack.getItem();

        Level level = host.level();
        int gx = host.getGuardedPos(0);
        int gy = host.getGuardedPos(1);
        int gz = host.getGuardedPos(2);
        if (gy <= 0) return;

        BlockPos wpPos = new BlockPos(gx, gy, gz);
        if (host.distanceToSqr(gx + 0.5, gy, gz + 0.5) > 25.0D) {
            host.getNavigation().moveTo(gx + 0.5D, gy, gz + 0.5D, 1.0D);
            return;
        }

        if (level.getBlockEntity(wpPos) instanceof WayPointBlockEntity wpbe) {
            BlockPos chestPos = wpbe.getChestPos();
            if (chestPos.getY() <= 0) return;

            net.minecraft.world.level.block.entity.BlockEntity targetBE = level.getBlockEntity(chestPos);
            if (targetBE == null) return;

            net.neoforged.neoforge.items.IItemHandler entireHandler = null;
            if (targetBE instanceof net.minecraft.world.Container container) {
                entireHandler = new net.neoforged.neoforge.items.wrapper.InvWrapper(container);
            } else {
                entireHandler = level.getCapability(net.neoforged.neoforge.capabilities.Capabilities.ItemHandler.BLOCK, chestPos, null);
            }

            if (entireHandler == null || entireHandler.getSlots() < 3) return;

            boolean swing = false;
            
            if (!mainStack.isEmpty()) {
                var smeltingRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(mainStack), level);
                if (smeltingRecipe.isPresent()) {
                    ItemStack remainderSim = entireHandler.insertItem(0, mainStack, true);
                    int canFit = mainStack.getCount() - remainderSim.getCount();
                    
                    if (canFit > 0) {
                        ItemStack material = InventoryHelper.getAndRemoveItem(host.getInventory(), mainStack, canFit, false, false, false, null);
                        if (!material.isEmpty()) {
                            swing = true;
                            ItemStack left = entireHandler.insertItem(0, material, false);
                            if (!left.isEmpty()) InventoryHelper.moveItemstackToInv(host.getInventory(), left, null);
                        }
                    }
                }
            }

            offhandStack = host.getHeldItemOffhandSlot();
            if (!offhandStack.isEmpty()) {
                ItemStack remainderSim = entireHandler.insertItem(1, offhandStack, true);
                int canFit = offhandStack.getCount() - remainderSim.getCount();

                if (canFit > 0) {
                    ItemStack fuel = InventoryHelper.getAndRemoveItem(host.getInventory(), offhandStack, canFit, false, false, false, null);
                    if (!fuel.isEmpty()) {
                        swing = true;
                        ItemStack left = entireHandler.insertItem(1, fuel, false);
                        if (!left.isEmpty()) InventoryHelper.moveItemstackToInv(host.getInventory(), left, null);
                    }
                }
            }

            ItemStack inOutputSlot = entireHandler.getStackInSlot(2);
            if (!inOutputSlot.isEmpty()) {
                if (!inOutputSlot.is(originalMainItem) && !inOutputSlot.is(originalOffhandItem)) {
                    boolean isPotentialMaterial = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(inOutputSlot), level).isPresent();
                    if (!isPotentialMaterial) {
                        ItemStack taken = entireHandler.extractItem(2, 64, false);
                        if (!taken.isEmpty()) {
                            swing = true;
                            InventoryHelper.moveItemstackToInv(host.getInventory(), taken, null);
                            
                            host.addShipExp(Config.expGainTask[0]);
                            host.setFuel(host.getFuel() - Config.consumeGrudgeTask[0]);
                            host.addMorale(100);
                            
                            float failChance = (Config.shipMaxLevelNormal - host.getLevel()) / (float) Config.shipMaxLevelNormal * 0.2F + 0.05F;
                            if (host.getRandom().nextFloat() < failChance) {
                                ItemEntity entity = new ItemEntity(level, chestPos.getX() + 0.5, chestPos.getY() + 1.0, chestPos.getZ() + 0.5, new ItemStack(Items.CHARCOAL));
                                level.addFreshEntity(entity);
                                host.applyParticleEmotion(6);
                            }
                        }
                    }
                }
            }

            if (swing) {
                host.startCustomSwing();
                if (host.getRandom().nextInt(5) == 0) {
                    host.applyParticleEmotion(host.getRandom().nextInt(5));
                }
            }
        }
    }

    public static void onUpdateFishing(EntityShipBase host) {
        if (host == null) return;
        Level level = host.level();
        ItemStack rod = host.getHeldItemMainhandSlot();
        if (rod.isEmpty() || rod.getItem() != Items.FISHING_ROD) {
            rod = host.getHeldItemOffhandSlot();
        }
        if (rod.isEmpty() || rod.getItem() != Items.FISHING_ROD) return;

        int gx = host.getGuardedPos(0);
        int gy = host.getGuardedPos(1);
        int gz = host.getGuardedPos(2);
        if (gy > 0 && host.distanceToSqr(gx + 0.5, gy, gz + 0.5) > 25.0D) {
            host.getNavigation().moveTo(gx + 0.5D, gy, gz + 0.5D, 1.0D);
            return;
        }

        if (Math.abs(host.getDeltaMovement().x) > 0.1D || Math.abs(host.getDeltaMovement().z) > 0.1D) return;


        BlockPos waterPos = null;
        boolean hasWater = false;
        BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos();
        for (int dy = 0; dy >= -3; dy--) {
            for (int dx = -5; dx <= 5; dx++) {
                for (int dz = -5; dz <= 5; dz++) {
                    mutPos.set(host.getX() + dx, host.getY() + dy, host.getZ() + dz);
                    
                    if (level.getFluidState(mutPos).is(net.minecraft.tags.FluidTags.WATER) &&
                            level.getBlockState(mutPos.above()).isAir()) {
                        hasWater = true;
                        waterPos = mutPos.immutable();
                        break;
                    }
                }
                if (hasWater) break;
            }
            if (hasWater) break;
        }
        if (!hasWater) return;

        if (host.getFishHook() == null || host.getFishHook().isRemoved()) {
            host.startCustomSwing();
            if (!level.isClientSide) {
                EntityShipFishingHook hook = new EntityShipFishingHook(level, host);
                hook.setPos(waterPos.getX() + 0.1D + host.getRandom().nextDouble() * 0.8D,
                        waterPos.getY() + 1.0D,
                        waterPos.getZ() + 0.1D + host.getRandom().nextDouble() * 0.8D);
                level.addFreshEntity(hook);
                host.applyParticleEmotion(host.getRandom().nextInt(4) + 1);
            }
            return;
        }

        if (level instanceof ServerLevel serverLevel) {
            if (host.getFishHook().tickCount > Config.tickFishingMin + host.getRandom().nextInt(Config.tickFishingMax)) {
                host.startCustomSwing();
                LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(net.minecraft.world.level.storage.loot.BuiltInLootTables.FISHING);
                LootParams params = (new LootParams.Builder(serverLevel))
                        .withParameter(LootContextParams.ORIGIN, host.position())
                        .withParameter(LootContextParams.TOOL, rod)
                        .withParameter(LootContextParams.THIS_ENTITY, host)
                        .create(LootContextParamSets.FISHING);
                
                List<ItemStack> items = lootTable.getRandomItems(params);
                for (ItemStack stack : items) {
                    ItemStack remainder = ItemHandlerHelper.insertItemStacked(host.getInventory(), stack, false);
                    if (!remainder.isEmpty()) {
                        ItemEntity entity = new ItemEntity(level, host.getX(), host.getY(), host.getZ(), remainder);
                        level.addFreshEntity(entity);
                    }
                }
                
                host.getFishHook().discard();
                host.addShipExp(Config.expGainTask[1]);
                host.setFuel(host.getFuel() - Config.consumeGrudgeTask[1]);
                host.addMorale(300);
                host.applyParticleEmotion(host.getRandom().nextInt(5));
            } else if (host.getFishHook().tickCount > Config.tickFishingMin + Config.tickFishingMax + 20) {
                host.getFishHook().discard();
            }
        }
    }

    public static void onUpdateMining(EntityShipBase host) {
        if (host == null) return;
        Level level = host.level();
        ItemStack pickaxe = host.getHeldItemMainhandSlot();
        if (pickaxe.isEmpty() || !pickaxe.is(net.minecraft.tags.ItemTags.PICKAXES)) return;

        if (Math.abs(host.getDeltaMovement().x) > 0.1D || Math.abs(host.getDeltaMovement().z) > 0.1D || host.getDeltaMovement().y > 0.1D) return;

        if ((host.tickCount & 63) == 0) {
            host.getNavigation().moveTo(host.getX() + host.getRandom().nextInt(9) - 4.0D, host.getY() + host.getRandom().nextInt(5) - 2.0D, host.getZ() + host.getRandom().nextInt(9) - 4.0D, 1.0D);
            return;
        }

        if (host.getRandom().nextInt(5) > 2) {
            host.startCustomSwing();
            if (!level.isClientSide && host.getRandom().nextInt(10) > 8) {
                host.applyParticleEmotion(host.getRandom().nextInt(5));
            }
        }

        if (!level.isClientSide && (host.tickCount & 31) == 0 && host.tickCount - host.getStateComponent().getMiningTimer() > Config.tickMiningMin + host.getRandom().nextInt(Config.tickMiningMax)) {
            int xl = (int) host.getX();
            int yl = (int) host.getY();
            int zl = (int) host.getZ();
            int stoneCount = 0;
            boolean canMine = false;
            BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos();
            for (int dy = -3; dy < 5 && !canMine; ++dy) {
                for (int dx = -3; dx < 4 && !canMine; ++dx) {
                    for (int dz = -3; dz < 4; ++dz) {
                        mutPos.set(xl + dx, yl + dy, zl + dz);
                        if (level.getBlockState(mutPos).is(BlockTags.BASE_STONE_OVERWORLD) || level.getBlockState(mutPos).is(BlockTags.BASE_STONE_NETHER)) {
                            if (++stoneCount > 120) {
                                canMine = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (canMine) {
                ItemStack result = new ItemStack(Items.COBBLESTONE);
                int rng = host.getRandom().nextInt(100);
                if (rng < 2) result = new ItemStack(Items.DIAMOND);
                else if (rng < 10) result = new ItemStack(Items.GOLD_ORE);
                else if (rng < 30) result = new ItemStack(Items.IRON_ORE);
                else if (rng < 60) result = new ItemStack(Items.COAL);
                ItemHandlerHelper.insertItemStacked(host.getInventory(), result, false);
                
                host.addShipExp(Config.expGainTask[2]);
                host.setFuel(host.getFuel() - Config.consumeGrudgeTask[2]);
                host.addMorale(-200);
                host.applyParticleEmotion(host.getRandom().nextInt(5));
                host.startCustomSwing();
                host.getStateComponent().setMiningTimer(host.tickCount);
            }
        }
    }

    public static void onUpdateCrafting(EntityShipBase host) {
        if (host == null || host.level().isClientSide) return;

        
        IItemHandler inv = host.getInventory();
        ItemStack recipePaper = host.getHeldItemMainhandSlot();
        if (recipePaper.isEmpty() || !recipePaper.is(ModItems.RECIPE_PAPER.get())) return;

        
        CustomData customData = recipePaper.get(DataComponents.CUSTOM_DATA);
        if (customData == null) return;
        CompoundTag tag = customData.copyTag();
        if (!tag.contains("Recipe", 9)) return;
        ListTag recipeList = tag.getList("Recipe", 10);

        ItemStack resultTemplate = ItemStack.EMPTY;
        List<ItemStack> materials = new ArrayList<>();
        for (int i = 0; i < recipeList.size(); i++) {
            CompoundTag itemTag = recipeList.getCompound(i);
            int slot = itemTag.getInt("Slot");
            ItemStack stack = ItemStack.parseOptional(host.level().registryAccess(), itemTag);
            if (slot == 9) {
                resultTemplate = stack;
            } else if (slot >= 0 && slot < 9 && !stack.isEmpty()) {
                materials.add(stack);
            }
        }
        if (resultTemplate.isEmpty()) return;

        
        List<ItemStack> uniqueMaterials = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        int taskSide = host.getStateComponent().getTaskSide();
        boolean checkMeta = (taskSide & (1 << 18)) != 0;
        boolean checkOre = (taskSide & (1 << 19)) != 0;
        boolean checkNbt = (taskSide & (1 << 20)) != 0;

        for (ItemStack m : materials) {
            boolean found = false;
            for (int i = 0; i < uniqueMaterials.size(); i++) {
                if (InventoryHelper.matchTargetItem(uniqueMaterials.get(i), m, checkMeta, checkNbt, checkOre)) {
                    counts.set(i, counts.get(i) + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                uniqueMaterials.add(m.copy());
                counts.add(1);
            }
        }

        
        int gx = host.getGuardedPos(0);
        int gy = host.getGuardedPos(1);
        int gz = host.getGuardedPos(2);
        if (gy <= 0) return;

        if (host.distanceToSqr(gx + 0.5, gy, gz + 0.5) > 25.0D) {
            host.getNavigation().moveTo(gx + 0.5D, gy, gz + 0.5D, 1.0D);
            return;
        }

        BlockPos wpPos = new BlockPos(gx, gy, gz);
        Level level = host.level();
        if (!(level.getBlockEntity(wpPos) instanceof WayPointBlockEntity wpbe)) return;
        BlockPos chestPos = wpbe.getChestPos();
        if (chestPos.getY() <= 0) return;

        
        List<IItemHandler> inHandlers = InventoryHelper.getHandlersFromSide(level, chestPos, taskSide, 0); 
        boolean swing = false;

        for (int i = 0; i < uniqueMaterials.size(); i++) {
            ItemStack temp = uniqueMaterials.get(i);
            int needed = counts.get(i);
            int has = InventoryHelper.calcItemStackAmount(inv, temp, checkMeta, checkNbt, checkOre);
            
            if (has < needed) {
                int pullCount = needed - has;
                for (IItemHandler h : inHandlers) {
                    ItemStack pulled = InventoryHelper.getAndRemoveItem(h, temp, pullCount, checkMeta, checkNbt, checkOre, null);
                    if (!pulled.isEmpty()) {
                        InventoryHelper.moveItemstackToInv(inv, pulled, null);
                        pullCount -= pulled.getCount();
                        if (pullCount <= 0) break;
                    }
                }
            }
            
            if (InventoryHelper.calcItemStackAmount(inv, temp, checkMeta, checkNbt, checkOre) < needed) {
                return; 
            }
        }

        
        for (int i = 0; i < uniqueMaterials.size(); i++) {
            InventoryHelper.getAndRemoveItem(inv, uniqueMaterials.get(i), counts.get(i), checkMeta, checkNbt, checkOre, null);
            swing = true;
        }

        if (swing) {
            host.startCustomSwing();
            ItemStack finalResult = resultTemplate.copy();
            
            List<IItemHandler> outHandlers = InventoryHelper.getHandlersFromSide(level, chestPos, taskSide, 1); 
            for (IItemHandler h : outHandlers) {
                finalResult = ItemHandlerHelper.insertItemStacked(h, finalResult, false);
                if (finalResult.isEmpty()) break;
            }
            
            if (!finalResult.isEmpty()) {
                InventoryHelper.moveItemstackToInv(inv, finalResult, null);
            }
            
            if (!finalResult.isEmpty()) {
                ItemEntity entity = new ItemEntity(level, chestPos.getX() + 0.5, chestPos.getY() + 1.0, chestPos.getZ() + 0.5, finalResult);
                level.addFreshEntity(entity);
            }
            
            host.addShipExp(Config.expGainTask[3]);
            host.setFuel(host.getFuel() - Config.consumeGrudgeTask[3]);
            host.addMorale(-10);
            if (host.getRandom().nextInt(5) == 0) {
                host.applyParticleEmotion(host.getRandom().nextInt(5));
            }
        }
    }
}
