package org.trp.shincolle.reference;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModBlocks;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.item.ShipTankItem;


import java.util.*;

public class Values {

    public static final List<Integer> ShipBookList;
    public static final List<Integer> EnemyBookList;
    public static final Map<Integer, int[]> ShipNameIconMap;
    public static final Map<Byte, int[]> ShipTypeIconMap;
    public static final Map<Short, ItemStack> ItemIconMap;
    public static final Map<Integer, List<int[]>> BookList;
    public static final Map<Integer, float[]> FormationAttrs;
    public static final Map<Integer, float[]> MoraleAttrs;
    public static final int[] PageLimit = new int[]{2, 29, 6, 20, 26, 19, 4};

    static {
        // Ship Book List
        ShipBookList = Collections.unmodifiableList(Arrays.asList(0, 1, 2, 3, 9, 10, 12, 14, 15, 21, 26, 28, 31, 33, 16, 17, 18, 19, 20, 13, 27, 49, 29, 30, 44, 72));

        // Enemy Book List
        EnemyBookList = Collections.unmodifiableList(Arrays.asList(36, 37, 38, 39, 46, 47, 48, 51, 52, 53, 54, 56, 57, 58, 59, 60, 61, 62, 63));

        // Ship Name Icon Map (sheetID, U, V)
        Map<Integer, int[]> tempShipNameIconMap = new HashMap<>();
        tempShipNameIconMap.put(0, new int[]{1, 0, 0});
        tempShipNameIconMap.put(1, new int[]{1, 11, 0});
        tempShipNameIconMap.put(2, new int[]{1, 22, 0});
        tempShipNameIconMap.put(3, new int[]{1, 33, 0});
        tempShipNameIconMap.put(4, new int[]{1, 44, 0});
        tempShipNameIconMap.put(5, new int[]{1, 55, 0});
        tempShipNameIconMap.put(6, new int[]{1, 66, 0});
        tempShipNameIconMap.put(7, new int[]{1, 77, 0});
        tempShipNameIconMap.put(8, new int[]{1, 88, 0});
        tempShipNameIconMap.put(9, new int[]{1, 99, 0});
        tempShipNameIconMap.put(10, new int[]{1, 110, 0});
        tempShipNameIconMap.put(11, new int[]{1, 121, 0});
        tempShipNameIconMap.put(12, new int[]{1, 132, 0});
        tempShipNameIconMap.put(13, new int[]{1, 143, 0});
        tempShipNameIconMap.put(14, new int[]{1, 154, 0});
        tempShipNameIconMap.put(15, new int[]{1, 165, 0});
        tempShipNameIconMap.put(16, new int[]{1, 176, 0});
        tempShipNameIconMap.put(17, new int[]{1, 187, 0});
        tempShipNameIconMap.put(18, new int[]{1, 198, 0});
        tempShipNameIconMap.put(19, new int[]{1, 209, 0});
        tempShipNameIconMap.put(64, new int[]{1, 220, 0});
        tempShipNameIconMap.put(20, new int[]{2, 0, 59});
        tempShipNameIconMap.put(21, new int[]{2, 11, 59});
        tempShipNameIconMap.put(22, new int[]{2, 22, 59});
        tempShipNameIconMap.put(23, new int[]{2, 33, 59});
        tempShipNameIconMap.put(34, new int[]{2, 44, 59});
        tempShipNameIconMap.put(41, new int[]{2, 55, 59});
        tempShipNameIconMap.put(26, new int[]{2, 66, 59});
        tempShipNameIconMap.put(27, new int[]{2, 77, 59});
        tempShipNameIconMap.put(28, new int[]{2, 88, 59});
        tempShipNameIconMap.put(29, new int[]{2, 99, 59});
        tempShipNameIconMap.put(30, new int[]{2, 110, 59});
        tempShipNameIconMap.put(31, new int[]{2, 121, 59});
        tempShipNameIconMap.put(32, new int[]{2, 132, 59});
        tempShipNameIconMap.put(40, new int[]{2, 143, 59});
        tempShipNameIconMap.put(33, new int[]{2, 154, 59});
        tempShipNameIconMap.put(35, new int[]{2, 165, 59});
        tempShipNameIconMap.put(25, new int[]{2, 176, 59});
        tempShipNameIconMap.put(24, new int[]{2, 187, 59});
        tempShipNameIconMap.put(45, new int[]{2, 198, 59});
        tempShipNameIconMap.put(43, new int[]{2, 209, 59});
        tempShipNameIconMap.put(49, new int[]{2, 220, 59});
        tempShipNameIconMap.put(44, new int[]{2, 231, 59});
        tempShipNameIconMap.put(50, new int[]{2, 242, 59});
        tempShipNameIconMap.put(65, new int[]{3, 0, 118});
        tempShipNameIconMap.put(66, new int[]{3, 11, 118});
        tempShipNameIconMap.put(67, new int[]{3, 22, 118});
        tempShipNameIconMap.put(68, new int[]{3, 33, 118});
        tempShipNameIconMap.put(69, new int[]{3, 44, 118});
        tempShipNameIconMap.put(70, new int[]{3, 55, 118});
        tempShipNameIconMap.put(71, new int[]{3, 66, 118});
        tempShipNameIconMap.put(72, new int[]{3, 77, 118});
        tempShipNameIconMap.put(73, new int[]{3, 88, 118});
        tempShipNameIconMap.put(74, new int[]{3, 99, 118});
        tempShipNameIconMap.put(75, new int[]{3, 110, 118});
        tempShipNameIconMap.put(76, new int[]{3, 121, 118});
        tempShipNameIconMap.put(77, new int[]{3, 132, 118});
        tempShipNameIconMap.put(78, new int[]{3, 143, 118});
        tempShipNameIconMap.put(79, new int[]{3, 154, 118});
        tempShipNameIconMap.put(80, new int[]{3, 165, 118});
        tempShipNameIconMap.put(81, new int[]{3, 176, 118});
        tempShipNameIconMap.put(82, new int[]{3, 187, 118});
        tempShipNameIconMap.put(83, new int[]{4, 0, 177});
        tempShipNameIconMap.put(84, new int[]{4, 11, 177});
        tempShipNameIconMap.put(36, new int[]{101, 0, 0});
        tempShipNameIconMap.put(37, new int[]{101, 11, 0});
        tempShipNameIconMap.put(46, new int[]{101, 22, 0});
        tempShipNameIconMap.put(47, new int[]{101, 33, 0});
        tempShipNameIconMap.put(48, new int[]{101, 44, 0});
        tempShipNameIconMap.put(51, new int[]{101, 55, 0});
        tempShipNameIconMap.put(52, new int[]{101, 66, 0});
        tempShipNameIconMap.put(53, new int[]{101, 77, 0});
        tempShipNameIconMap.put(54, new int[]{101, 88, 0});
        tempShipNameIconMap.put(55, new int[]{101, 99, 0});
        tempShipNameIconMap.put(56, new int[]{101, 110, 0});
        tempShipNameIconMap.put(57, new int[]{101, 121, 0});
        tempShipNameIconMap.put(58, new int[]{101, 132, 0});
        tempShipNameIconMap.put(59, new int[]{101, 143, 0});
        tempShipNameIconMap.put(60, new int[]{101, 154, 0});
        tempShipNameIconMap.put(61, new int[]{101, 165, 0});
        tempShipNameIconMap.put(62, new int[]{101, 176, 0});
        tempShipNameIconMap.put(63, new int[]{101, 187, 0});
        tempShipNameIconMap.put(38, new int[]{101, 198, 0});
        tempShipNameIconMap.put(39, new int[]{101, 209, 0});
        ShipNameIconMap = Collections.unmodifiableMap(tempShipNameIconMap);

        // Ship Type Icon Map (U, V)
        Map<Byte, int[]> tempShipTypeIconMap = new HashMap<>();
        tempShipTypeIconMap.put((byte)7, new int[]{12, 74});
        tempShipTypeIconMap.put((byte)-1, new int[]{41, 0});
        tempShipTypeIconMap.put((byte)1, new int[]{41, 29});
        tempShipTypeIconMap.put((byte)2, new int[]{41, 58});
        tempShipTypeIconMap.put((byte)3, new int[]{41, 87});
        tempShipTypeIconMap.put((byte)6, new int[]{70, 0});
        tempShipTypeIconMap.put((byte)5, new int[]{70, 29});
        tempShipTypeIconMap.put((byte)4, new int[]{70, 58});
        tempShipTypeIconMap.put((byte)10, new int[]{70, 87});
        tempShipTypeIconMap.put((byte)8, new int[]{99, 0});
        tempShipTypeIconMap.put((byte)9, new int[]{99, 58});
        ShipTypeIconMap = Collections.unmodifiableMap(tempShipTypeIconMap);

        Map<Short, ItemStack> tempItemIconMap = new HashMap<>();
        tempItemIconMap.put((short)0, new ItemStack(Items.IRON_INGOT));
        tempItemIconMap.put((short)1, new ItemStack(ModItems.GRUDGE.get()));
        tempItemIconMap.put((short)2, new ItemStack(ModItems.GRUDGE_BLOCK.get()));
        tempItemIconMap.put((short)3, new ItemStack(ModItems.GRUDGE_HEAVY_BLOCK.get()));
        tempItemIconMap.put((short)4, new ItemStack(ModItems.ABYSS_METAL.get()));
        tempItemIconMap.put((short)5, new ItemStack(ModItems.ABYSSIUM.get()));
        tempItemIconMap.put((short)6, new ItemStack(ModItems.ABYSS_POLYMETAL.get()));
        tempItemIconMap.put((short)9, new ItemStack(ModItems.POLYMETAL_ORE.get()));
        tempItemIconMap.put((short)7, new ItemStack(ModItems.POLYMETAL.get()));
        tempItemIconMap.put((short)8, new ItemStack(ModItems.POLYMETAL_GRAVEL.get()));
        tempItemIconMap.put((short)10, new ItemStack(Items.GUNPOWDER));
        tempItemIconMap.put((short)11, new ItemStack(Items.BLAZE_POWDER));
        tempItemIconMap.put((short)12, new ItemStack(ModItems.AMMO_LIGHT.get()));
        tempItemIconMap.put((short)13, new ItemStack(ModItems.AMMO_LIGHT_CONTAINER.get()));
        tempItemIconMap.put((short)14, new ItemStack(ModItems.AMMO_HEAVY.get()));
        tempItemIconMap.put((short)15, new ItemStack(ModItems.AMMO_HEAVY_CONTAINER.get()));
        tempItemIconMap.put((short)16, new ItemStack(ModItems.BUCKET_REPAIR.get()));
        tempItemIconMap.put((short)17, new ItemStack(Items.LAVA_BUCKET));
        tempItemIconMap.put((short)18, new ItemStack(Items.NETHER_STAR));
        tempItemIconMap.put((short)19, new ItemStack(ModItems.MARRIAGE_RING.get()));
        tempItemIconMap.put((short)20, new ItemStack(Items.PAPER));
        // tempItemIconMap.put((short)21, new ItemStack(ModItems.OwnerPaper.get())); // Missing
        tempItemIconMap.put((short)22, new ItemStack(Items.STICK));
        tempItemIconMap.put((short)23, new ItemStack(ModItems.KAITAI_HAMMER.get()));
        tempItemIconMap.put((short)24, new ItemStack(ModItems.MODERN_KIT.get()));
        tempItemIconMap.put((short)25, new ItemStack(ModItems.SHIPSPAWNEGGS.get()));
        tempItemIconMap.put((short)26, new ItemStack(ModItems.SHIPSPAWNEGGL.get()));
        // tempItemIconMap.put((short)27, new ItemStack(ModItems.ShipSpawnEgg, 1, 2)); // Meta-based
        tempItemIconMap.put((short)28, new ItemStack(ModItems.INSTANT_CON_MAT.get()));
        tempItemIconMap.put((short)29, new ItemStack(Items.DIAMOND_BLOCK));
        tempItemIconMap.put((short)30, new ItemStack(ModItems.REPAIR_GODDESS.get()));
        tempItemIconMap.put((short)31, new ItemStack(ModItems.POINTER_ITEM.get()));
        tempItemIconMap.put((short)32, new ItemStack(ModItems.TOY_AIRPLANE.get()));
        // tempItemIconMap.put((short)37, new ItemStack(ModBlocks.BlockChair.get())); // Missing
        tempItemIconMap.put((short)33, new ItemStack(ModItems.DESK.get()));
        tempItemIconMap.put((short)50, new ItemStack(ModItems.DESK_ITEM_BOOK.get()));
        tempItemIconMap.put((short)51, new ItemStack(ModItems.DESK_ITEM_RADAR.get()));
        tempItemIconMap.put((short)52, new ItemStack(Items.WRITABLE_BOOK));
        tempItemIconMap.put((short)53, new ItemStack(Items.COMPASS));
        tempItemIconMap.put((short)34, new ItemStack(Items.OBSIDIAN));
        tempItemIconMap.put((short)35, new ItemStack(Items.WHITE_WOOL));
        tempItemIconMap.put((short)38, new ItemStack(Items.OAK_PLANKS));
        tempItemIconMap.put((short)39, new ItemStack(Items.LEATHER));
        tempItemIconMap.put((short)36, new ItemStack(ModItems.SMALL_SHIPYARD.get()));
        tempItemIconMap.put((short)54, new ItemStack(ModItems.TARGET_WRENCH.get()));
        tempItemIconMap.put((short)55, new ItemStack(ModItems.VOL_CORE.get()));
        tempItemIconMap.put((short)56, new ItemStack(ModBlocks.VOL_BLOCK.get()));
        tempItemIconMap.put((short)61, new ItemStack(ModBlocks.FRAME.get()));
        tempItemIconMap.put((short)62, new ItemStack(ModItems.WAYPOINT.get()));
        tempItemIconMap.put((short)63, new ItemStack(ModItems.CRANE.get()));
        tempItemIconMap.put((short)64, new ItemStack(Items.PISTON));
        tempItemIconMap.put((short)65, new ItemStack(ModItems.TRAINING_BOOK.get()));
        tempItemIconMap.put((short)66, new ItemStack(Items.MAGMA_BLOCK));
        tempItemIconMap.put((short)67, new ItemStack(ModItems.SHIP_TANK.get()));
        tempItemIconMap.put((short)68, ((ShipTankItem) ModItems.SHIP_TANK.get()).createVariantStack(1));
        tempItemIconMap.put((short)69, ((ShipTankItem) ModItems.SHIP_TANK.get()).createVariantStack(2));
        tempItemIconMap.put((short)70, ((ShipTankItem) ModItems.SHIP_TANK.get()).createVariantStack(3));
        tempItemIconMap.put((short)71, new ItemStack(Items.CAULDRON));
        tempItemIconMap.put((short)72, new ItemStack(Items.LAPIS_LAZULI));
        tempItemIconMap.put((short)73, new ItemStack(ModItems.RECIPE_PAPER.get()));
        tempItemIconMap.put((short)74, new ItemStack(ModItems.OP_TOOL.get()));
        tempItemIconMap.put((short)75, ((LegacyEquipItem) ModItems.EQUIP_AMMO.get()).createVariantStack(7));
        tempItemIconMap.put((short)76, new ItemStack(Items.POTION));
        tempItemIconMap.put((short)77, new ItemStack(ModItems.GRUDGE_XP.get()));
        tempItemIconMap.put((short)78, new ItemStack(ModBlocks.GRUDGE_XP_BLOCK.get()));
        tempItemIconMap.put((short)79, new ItemStack(Items.EXPERIENCE_BOTTLE));
        ItemIconMap = Collections.unmodifiableMap(tempItemIconMap);

        // Book List
        Map<Integer, List<int[]>> tempBookList = new HashMap<>();
        tempBookList.put(0, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}));
        tempBookList.put(1, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}));
        tempBookList.put(1000, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, 76, 0, 0, 0, 100, 56}, new int[]{2, 0, 13, -3, 1}, new int[]{2, 0, 43, -3, 2}, new int[]{2, 0, 73, -3, 3}));
        tempBookList.put(1001, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, 17, 1}, new int[]{2, 0, 23, 17, 0}, new int[]{2, 0, 81, 17, 4}));
        tempBookList.put(1002, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 0, 56, 100, 56}, new int[]{1, 1, 0, 73, 0, 0, 112, 100, 59}, new int[]{2, 0, 5, 52, 9}, new int[]{2, 0, 30, 52, 6}, new int[]{2, 0, 55, 52, 7}, new int[]{2, 0, 80, 52, 8}));
        tempBookList.put(1003, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 0}, new int[]{2, 0, 23, -3, 0}, new int[]{2, 0, 43, -3, 0}, new int[]{2, 0, 3, 17, 0}, new int[]{2, 0, 23, 17, 1}, new int[]{2, 0, 43, 17, 0}, new int[]{2, 0, 3, 37, 0}, new int[]{2, 0, 23, 37, 10}, new int[]{2, 0, 43, 37, 0}, new int[]{2, 0, 81, 17, 12}, new int[]{2, 1, 3, 110, 12}, new int[]{2, 1, 28, 110, 13}, new int[]{2, 1, 53, 110, 14}, new int[]{2, 1, 78, 110, 15}));
        tempBookList.put(1004, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, 17, 17}, new int[]{2, 0, 23, 17, 1}, new int[]{2, 0, 81, 17, 16}));
        tempBookList.put(1005, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 23, -3, 18}, new int[]{2, 0, 3, 17, 4}, new int[]{2, 0, 43, 17, 4}, new int[]{2, 0, 23, 37, 4}, new int[]{2, 0, 81, 17, 19}));
        tempBookList.put(1006, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, 17, 20}, new int[]{2, 0, 23, 17, 1}, new int[]{2, 0, 81, 17, 21}));
        tempBookList.put(1007, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 4}, new int[]{2, 0, 23, -3, 4}, new int[]{2, 0, 43, -3, 4}, new int[]{2, 0, 3, 17, 4}, new int[]{2, 0, 23, 17, 4}, new int[]{2, 0, 43, 17, 4}, new int[]{2, 0, 23, 37, 22}, new int[]{2, 0, 81, 17, 23}));
        tempBookList.put(1008, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 23}, new int[]{2, 0, 23, -3, 54}, new int[]{2, 0, 43, -3, 78}, new int[]{2, 0, 3, 17, 78}, new int[]{2, 0, 23, 17, 78}, new int[]{2, 0, 43, 17, 78}, new int[]{2, 0, 81, 17, 24}));
        tempBookList.put(1009, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, 17, 23}, new int[]{2, 0, 23, 17, 25}, new int[]{2, 0, 81, 17, 28}));
        tempBookList.put(1010, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 2}, new int[]{2, 0, 23, -3, 3}, new int[]{2, 0, 43, -3, 2}, new int[]{2, 0, 3, 17, 3}, new int[]{2, 0, 23, 17, 29}, new int[]{2, 0, 43, 17, 3}, new int[]{2, 0, 3, 37, 2}, new int[]{2, 0, 23, 37, 3}, new int[]{2, 0, 43, 37, 2}, new int[]{2, 0, 81, 17, 30}));
        tempBookList.put(1011, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, 37, 6}, new int[]{2, 0, 23, 17, 6}, new int[]{2, 0, 43, -3, 2}, new int[]{2, 0, 81, 17, 31}));
        tempBookList.put(1012, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}));
        tempBookList.put(1013, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}));
        tempBookList.put(1014, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 23, -3, 6}, new int[]{2, 0, 3, 17, 6}, new int[]{2, 0, 23, 17, 6}, new int[]{2, 0, 43, 17, 6}, new int[]{2, 0, 23, 37, 6}, new int[]{2, 0, 81, 17, 32}));
        tempBookList.put(1015, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 1}, new int[]{2, 0, 23, -3, 1}, new int[]{2, 0, 43, -3, 1}, new int[]{2, 0, 3, 17, 1}, new int[]{2, 0, 23, 17, 52}, new int[]{2, 0, 43, 17, 1}, new int[]{2, 0, 3, 37, 1}, new int[]{2, 0, 23, 37, 1}, new int[]{2, 0, 43, 37, 1}, new int[]{2, 0, 81, 17, 50}));
        tempBookList.put(1016, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 1}, new int[]{2, 0, 23, -3, 1}, new int[]{2, 0, 43, -3, 1}, new int[]{2, 0, 3, 17, 1}, new int[]{2, 0, 23, 17, 53}, new int[]{2, 0, 43, 17, 1}, new int[]{2, 0, 3, 37, 1}, new int[]{2, 0, 23, 37, 1}, new int[]{2, 0, 43, 37, 1}, new int[]{2, 0, 81, 17, 51}));
        tempBookList.put(1017, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 51}, new int[]{2, 0, 23, -3, 50}, new int[]{2, 0, 43, -3, 35}, new int[]{2, 0, 3, 17, 34}, new int[]{2, 0, 23, 17, 34}, new int[]{2, 0, 43, 17, 34}, new int[]{2, 0, 3, 37, 34}, new int[]{2, 0, 43, 37, 34}, new int[]{2, 0, 81, 17, 33}));
        tempBookList.put(1018, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 1}, new int[]{2, 0, 23, -3, 1}, new int[]{2, 0, 43, -3, 38}, new int[]{2, 0, 3, 17, 38}, new int[]{2, 0, 23, 17, 38}, new int[]{2, 0, 43, 17, 38}, new int[]{2, 0, 3, 37, 38}, new int[]{2, 0, 23, 37, 39}, new int[]{2, 0, 43, 37, 38}, new int[] {2, 0, 81, 17, 37}));
        tempBookList.put(1019, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 4}, new int[]{2, 0, 43, -3, 4}, new int[]{2, 0, 3, 17, 4}, new int[]{2, 0, 23, 17, 4}, new int[]{2, 0, 43, 17, 4}, new int[]{2, 0, 23, 37, 4}, new int[]{2, 0, 81, 17, 54}));
        tempBookList.put(1020, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 34}, new int[]{2, 0, 23, -3, 66}, new int[]{2, 0, 43, -3, 34}, new int[]{2, 0, 3, 17, 66}, new int[]{2, 0, 23, 17, 2}, new int[]{2, 0, 43, 17, 66}, new int[]{2, 0, 3, 37, 34}, new int[]{2, 0, 23, 37, 66}, new int[]{2, 0, 43, 37, 34}, new int[]{2, 0, 81, 17, 56}));
        tempBookList.put(1021, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 34}, new int[]{2, 0, 23, -3, 56}, new int[]{2, 0, 43, -3, 34}, new int[]{2, 0, 3, 17, 56}, new int[]{2, 0, 23, 17, 3}, new int[]{2, 0, 43, 17, 56}, new int[]{2, 0, 3, 37, 34}, new int[]{2, 0, 23, 37, 56}, new int[]{2, 0, 43, 37, 34}, new int[]{2, 0, 81, 17, 55}));
        tempBookList.put(1022, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 4}, new int[]{2, 0, 43, -3, 4}, new int[]{2, 0, 23, 17, 34}, new int[]{2, 0, 3, 37, 4}, new int[]{2, 0, 43, 37, 4}, new int[]{2, 0, 81, 17, 61}));
        tempBookList.put(1023, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 23, 17, 1}, new int[]{2, 0, 23, 37, 22}, new int[]{2, 0, 81, 17, 62}));
        tempBookList.put(1024, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 4}, new int[]{2, 0, 23, -3, 4}, new int[]{2, 0, 43, -3, 4}, new int[]{2, 0, 3, 17, 4}, new int[]{2, 0, 23, 17, 2}, new int[]{2, 0, 43, 17, 4}, new int[]{2, 0, 3, 37, 4}, new int[]{2, 0, 23, 37, 64}, new int[]{2, 0, 43, 37, 4}, new int[]{2, 0, 81, 17, 63}));
        tempBookList.put(1025, Arrays.asList(new int[] {3, 4, 1}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 23}, new int[]{2, 0, 23, -3, 24}, new int[]{2, 0, 43, -3, 52}, new int[]{2, 0, 3, 17, 78}, new int[]{2, 0, 23, 17, 78}, new int[]{2, 0, 43, 17, 78}, new int[]{2, 0, 3, 37, 78}, new int[]{2, 0, 81, 17, 65}, new int[]{1, 2, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 2, 3, -3, 77}, new int[]{2, 2, 23, -3, 77}, new int[]{2, 2, 43, -3, 77}, new int[]{2, 2, 3, 17, 77}, new int[]{2, 2, 23, 17, 77}, new int[]{2, 2, 43, 17, 77}, new int[]{2, 2, 3, 37, 77}, new int[]{2, 2, 23, 37, 77}, new int[]{2, 2, 43, 37, 77}, new int[]{2, 2, 81, 17, 78}, new int[]{1, 4, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 4, 3, -3, 79}, new int[]{2, 4, 23, -3, 79}, new int[]{2, 4, 43, -3, 79}, new int[]{2, 4, 3, 17, 79}, new int[]{2, 4, 23, 17, 1}, new int[]{2, 4, 43, 17, 79}, new int[]{2, 4, 3, 37, 79}, new int[]{2, 4, 23, 37, 79}, new int[]{2, 4, 43, 37, 79}, new int[]{2, 4, 81, 17, 77}));
        tempBookList.put(1026, Arrays.asList(new int[] {3, 6, 1}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 6}, new int[]{2, 0, 23, -3, 71}, new int[]{2, 0, 43, -3, 6}, new int[]{2, 0, 3, 17, 6}, new int[]{2, 0, 23, 17, 71}, new int[]{2, 0, 43, 17, 6}, new int[]{2, 0, 3, 37, 6}, new int[]{2, 0, 23, 37, 71}, new int[]{2, 0, 43, 37, 6}, new int[]{2, 0, 81, 17, 67}, new int[]{1, 2, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 2, 3, -3, 34}, new int[]{2, 2, 23, -3, 67}, new int[]{2, 2, 43, -3, 34}, new int[]{2, 2, 3, 17, 34}, new int[]{2, 2, 23, 17, 67}, new int[]{2, 2, 43, 17, 34}, new int[]{2, 2, 3, 37, 34}, new int[]{2, 2, 23, 37, 67}, new int[]{2, 2, 43, 37, 34}, new int[]{2, 2, 81, 17, 68}, new int[]{1, 4, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 4, 3, -3, 5}, new int[]{2, 4, 23, -3, 68}, new int[]{2, 4, 43, -3, 5}, new int[]{2, 4, 3, 17, 5}, new int[]{2, 4, 23, 17, 68}, new int[]{2, 4, 43, 17, 5}, new int[]{2, 4, 3, 37, 5}, new int[]{2, 4, 23, 37, 68}, new int[]{2, 4, 43, 37, 5}, new int[]{2, 4, 81, 17, 69}, new int[]{1, 6, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 6, 3, -3, 3}, new int[]{2, 6, 23, -3, 69}, new int[]{2, 6, 43, -3, 3}, new int[]{2, 6, 3, 17, 3}, new int[]{2, 6, 23, 17, 69}, new int[]{2, 6, 43, 17, 3}, new int[]{2, 6, 3, 37, 3}, new int[]{2, 6, 23, 37, 69}, new int[]{2, 6, 43, 37, 3}, new int[]{2, 6, 81, 17, 70}));
        tempBookList.put(1027, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, 17, 1}, new int[]{2, 0, 23, 17, 20}, new int[]{2, 0, 43, 17, 72}, new int[]{2, 0, 81, 17, 73}));
        tempBookList.put(1028, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{2, 0, 43, 17, 74}));
        tempBookList.put(1029, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{2, 0, 3, -3, 76}, new int[]{2, 0, 23, -3, 76}, new int[]{2, 0, 43, -3, 76}, new int[]{2, 0, 3, 17, 76}, new int[]{2, 0, 23, 17, 75}, new int[]{2, 0, 43, 17, 76}, new int[]{2, 0, 3, 37, 76}, new int[]{2, 0, 23, 37, 76}, new int[]{2, 0, 43, 37, 76}, new int[]{2, 0, 81, 17, 75}));
        tempBookList.put(2000, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, -6, 0, 100, 72, 100, 62}, new int[]{1, 1, 0, -6, 0, 100, 134, 100, 46}, new int[]{2, 0, 3, -3, 1}, new int[]{2, 0, 23, -3, 17}, new int[]{2, 0, 43, -3, 1}, new int[]{2, 0, 3, 17, 17}, new int[]{2, 0, 23, 17, 34}, new int[]{2, 0, 43, 17, 17}, new int[]{2, 0, 3, 37, 34}, new int[]{2, 0, 23, 37, 34}, new int[]{2, 0, 43, 37, 34}, new int[]{2, 0, 81, 17, 36}));
        tempBookList.put(2001, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 25, -12, 0, 0, 230, 50, 26}));
        tempBookList.put(2002, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 25, -12, 0, 50, 230, 50, 26}));
        tempBookList.put(2003, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, 60, 0, 100, 180, 100, 65}, new int[]{1, 1, -7, -18, 0, 200, 0, 38, 38}, new int[]{1, 1, 31, -18, 0, 200, 38, 38, 38}, new int[]{1, 1, 69, -18, 0, 200, 76, 38, 38}));
        tempBookList.put(2004, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}));
        tempBookList.put(3013, Arrays.asList(new int[] {0, 0, 0, 0}, new int[]{0, 1, 0, 0}, new int[]{1, 0, 0, 120, 0, 100, 245, 100, 11}));
        BookList = Collections.unmodifiableMap(tempBookList);

        // Formation Attributes
        Map<Integer, float[]> tempFormationAttrs = new HashMap<>();
        tempFormationAttrs.put(10, new float[]{0.0f, 2.0f, 2.0f, 1.2f, 1.2f, 0.3f, 1.3f, 0.08f, 4.0f, 1.75f, 1.75f, 1.75f, 1.25f, 0.5f, 0.4f, 0.1f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(11, new float[]{0.0f, 1.75f, 1.75f, 1.2f, 1.2f, 0.4f, 1.3f, 0.08f, 4.0f, 1.55f, 1.55f, 1.55f, 1.2f, 0.5f, 0.4f, 0.1f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(12, new float[]{0.0f, 1.55f, 1.55f, 1.15f, 1.15f, 0.5f, 1.2f, 0.08f, 3.0f, 1.4f, 1.4f, 1.4f, 1.2f, 0.5f, 0.4f, 0.1f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(13, new float[]{0.0f, 1.4f, 1.4f, 1.15f, 1.15f, 0.6f, 1.2f, 0.08f, 3.0f, 1.3f, 1.3f, 1.3f, 1.15f, 0.5f, 0.4f, 0.1f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(14, new float[]{0.0f, 1.3f, 1.3f, 1.1f, 1.1f, 0.7f, 1.1f, 0.08f, 2.0f, 1.2f, 1.2f, 1.2f, 1.15f, 0.5f, 0.4f, 0.1f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(15, new float[]{0.0f, 1.25f, 1.25f, 1.1f, 1.1f, 0.8f, 1.1f, 0.08f, 2.0f, 1.1f, 1.1f, 1.1f, 1.1f, 0.5f, 0.4f, 0.1f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(20, new float[]{0.0f, 1.4f, 1.4f, 1.1f, 1.1f, 0.9f, 1.08f, 0.0f, 2.0f, 1.15f, 1.15f, 1.15f, 1.55f, 1.2f, 1.0f, -0.15f, 0.0f, 0.1f, 0.0f, 0.3f, 0.05f});
        tempFormationAttrs.put(21, new float[]{0.0f, 1.4f, 1.4f, 1.1f, 1.1f, 0.9f, 1.08f, 0.0f, 2.0f, 1.15f, 1.15f, 1.15f, 1.55f, 1.2f, 1.0f, -0.15f, 0.0f, 0.1f, 0.0f, 0.3f, 0.05f});
        tempFormationAttrs.put(22, new float[]{0.0f, 1.5f, 1.5f, 1.15f, 1.15f, 0.75f, 1.15f, 0.0f, 3.0f, 1.3f, 1.3f, 1.3f, 1.75f, 1.1f, 1.0f, -0.05f, 0.0f, 0.1f, 0.0f, 0.1f, 0.05f});
        tempFormationAttrs.put(23, new float[]{0.0f, 1.5f, 1.5f, 1.15f, 1.15f, 0.75f, 1.15f, 0.0f, 3.0f, 1.3f, 1.3f, 1.3f, 1.75f, 1.1f, 1.0f, -0.05f, 0.0f, 0.1f, 0.0f, 0.1f, 0.05f});
        tempFormationAttrs.put(24, new float[]{0.0f, 1.3f, 1.3f, 1.05f, 1.05f, 1.0f, 1.0f, 0.0f, 1.0f, 1.1f, 1.1f, 1.1f, 1.35f, 1.1f, 1.0f, -0.05f, 0.0f, 0.1f, 0.0f, 0.1f, 0.05f});
        tempFormationAttrs.put(25, new float[]{0.0f, 1.3f, 1.3f, 1.05f, 1.05f, 1.0f, 1.0f, 0.0f, 1.0f, 1.1f, 1.1f, 1.1f, 1.35f, 1.1f, 1.0f, -0.05f, 0.0f, 0.1f, 0.0f, 0.1f, 0.05f});
        tempFormationAttrs.put(30, new float[]{0.0f, 0.6f, 0.3f, 2.0f, 2.0f, 1.5f, 1.0f, -0.1f, 4.0f, 1.1f, 1.0f, 1.0f, 1.0f, 2.0f, 1.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.1f});
        tempFormationAttrs.put(31, new float[]{0.0f, 1.0f, 0.65f, 1.2f, 1.2f, 1.25f, 1.0f, -0.1f, 1.0f, 1.1f, 1.1f, 1.1f, 1.0f, 1.75f, 1.3f, -0.3f, 0.0f, 0.0f, 0.0f, 0.3f, 0.1f});
        tempFormationAttrs.put(32, new float[]{0.0f, 1.0f, 0.65f, 1.2f, 1.2f, 1.25f, 1.0f, -0.1f, 1.0f, 1.1f, 1.1f, 1.1f, 1.0f, 1.75f, 1.3f, -0.3f, 0.0f, 0.0f, 0.0f, 0.3f, 0.1f});
        tempFormationAttrs.put(33, new float[]{0.0f, 1.0f, 0.65f, 1.2f, 1.2f, 1.25f, 1.0f, -0.1f, 1.0f, 1.1f, 1.1f, 1.1f, 1.0f, 1.75f, 1.3f, -0.3f, 0.0f, 0.0f, 0.0f, 0.3f, 0.1f});
        tempFormationAttrs.put(34, new float[]{0.0f, 1.0f, 0.65f, 1.2f, 1.2f, 1.25f, 1.0f, -0.1f, 1.0f, 1.1f, 1.1f, 1.1f, 1.0f, 1.75f, 1.3f, -0.3f, 0.0f, 0.0f, 0.0f, 0.3f, 0.1f});
        tempFormationAttrs.put(35, new float[]{0.0f, 0.6f, 0.3f, 2.0f, 2.0f, 1.5f, 1.0f, -0.1f, 4.0f, 1.1f, 1.0f, 1.0f, 1.0f, 2.0f, 1.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.1f});
        tempFormationAttrs.put(40, new float[]{0.0f, 1.2f, 1.2f, 1.0f, 1.0f, 0.75f, 1.0f, 0.18f, 2.0f, 1.25f, 1.25f, 1.25f, 0.65f, 0.3f, 0.8f, 0.25f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(41, new float[]{0.0f, 1.1f, 1.1f, 1.0f, 1.0f, 0.85f, 1.0f, 0.18f, 2.0f, 1.2f, 1.2f, 1.2f, 0.7f, 0.3f, 0.8f, 0.25f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(42, new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.95f, 1.0f, 0.18f, 2.0f, 1.15f, 1.15f, 1.15f, 0.75f, 0.3f, 0.8f, 0.25f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(43, new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.05f, 1.0f, 0.18f, 1.0f, 1.1f, 1.1f, 1.1f, 0.8f, 0.3f, 0.8f, 0.25f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(44, new float[]{0.0f, 0.9f, 0.9f, 1.0f, 1.0f, 1.15f, 1.0f, 0.18f, 1.0f, 1.05f, 1.05f, 1.05f, 0.85f, 0.3f, 0.8f, 0.25f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(45, new float[]{0.0f, 0.8f, 0.8f, 1.0f, 1.0f, 1.25f, 1.0f, 0.18f, 1.0f, 1.0f, 1.0f, 1.0f, 0.9f, 0.3f, 0.8f, 0.25f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(50, new float[]{0.0f, 0.9f, 0.9f, 0.9f, 0.9f, 1.35f, 0.8f, 0.05f, -2.0f, 1.15f, 1.0f, 1.0f, 1.0f, 1.0f, 1.75f, -0.15f, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f});
        tempFormationAttrs.put(51, new float[]{0.0f, 0.9f, 0.9f, 0.9f, 0.9f, 1.35f, 0.8f, 0.05f, -2.0f, 1.15f, 1.0f, 1.0f, 1.0f, 1.0f, 1.75f, -0.15f, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f});
        tempFormationAttrs.put(52, new float[]{0.0f, 0.9f, 0.9f, 0.9f, 0.9f, 1.35f, 0.8f, 0.05f, -2.0f, 1.15f, 1.0f, 1.0f, 1.0f, 1.0f, 1.75f, -0.15f, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f});
        tempFormationAttrs.put(53, new float[]{0.0f, 0.9f, 0.9f, 0.9f, 0.9f, 1.35f, 0.8f, 0.05f, -2.0f, 1.15f, 1.0f, 1.0f, 1.0f, 1.0f, 1.75f, -0.15f, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f});
        tempFormationAttrs.put(54, new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.2f, 0.9f, 0.05f, -1.0f, 1.15f, 1.0f, 1.0f, 1.0f, 1.0f, 1.75f, -0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        tempFormationAttrs.put(55, new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.2f, 0.9f, 0.05f, -1.0f, 1.15f, 1.0f, 1.0f, 1.0f, 1.0f, 1.75f, -0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        FormationAttrs = Collections.unmodifiableMap(tempFormationAttrs);

        // Morale Attributes
        Map<Integer, float[]> tempMoraleAttrs = new HashMap<>();
        tempMoraleAttrs.put(0, new float[]{0.0f, 1.25f, 1.25f, 1.25f, 1.25f, 1.2f, 1.4f, 0.15f, 4.0f, 1.2f, 1.2f, 1.2f, 1.5f, 1.5f, 1.5f, 0.25f, 0.5f, 0.5f, 0.5f, 0.5f, 0.25f});
        tempMoraleAttrs.put(1, new float[]{0.0f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.2f, 0.08f, 2.0f, 1.1f, 1.1f, 1.1f, 1.25f, 1.25f, 1.25f, 0.12f, 0.25f, 0.25f, 0.25f, 0.25f, 0.15f});
        tempMoraleAttrs.put(2, new float[]{0.0f, 0.9f, 0.9f, 0.9f, 0.9f, 0.9f, 0.8f, -0.08f, -2.0f, 0.9f, 0.9f, 0.9f, 0.75f, 0.75f, 0.75f, -0.12f, -0.25f, -0.25f, -0.25f, -0.25f, -0.1f});
        tempMoraleAttrs.put(3, new float[]{0.0f, 0.75f, 0.75f, 0.75f, 0.75f, 0.8f, 0.6f, -0.15f, -4.0f, 0.8f, 0.8f, 0.8f, 0.5f, 0.5f, 0.5f, -0.25f, -0.5f, -0.5f, -0.5f, -0.5f, -0.2f});
        MoraleAttrs = Collections.unmodifiableMap(tempMoraleAttrs);
    }

    public static float[] getResetFormationValue() {
        return new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public static float[] getResetMoraleValue() {
        return new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }
}
