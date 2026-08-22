package org.trp.shincolle.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.block.entity.*;

public class ModBlockEntities {

    public static final DeferredRegister<
        BlockEntityType<?>
    > BLOCK_ENTITY_TYPES = DeferredRegister.create(
        Registries.BLOCK_ENTITY_TYPE,
        Shincolle.MODID
    );

    public static final DeferredHolder<
        BlockEntityType<?>,
        BlockEntityType<DeskBlockEntity>
    > DESK = BLOCK_ENTITY_TYPES.register("blockdesk", () ->
        BlockEntityType.Builder.of(
            DeskBlockEntity::new,
            ModBlocks.DESK.get()
        ).build(null)
    );

    public static final DeferredHolder<
        BlockEntityType<?>,
        BlockEntityType<SmallShipyardBlockEntity>
    > SMALL_SHIPYARD = BLOCK_ENTITY_TYPES.register("small_shipyard", () ->
        BlockEntityType.Builder.of(
            SmallShipyardBlockEntity::new,
            ModBlocks.SMALL_SHIPYARD.get()
        ).build(null)
    );

    public static final DeferredHolder<
        BlockEntityType<?>,
        BlockEntityType<LargeShipyardBlockEntity>
    > LARGE_SHIPYARD = BLOCK_ENTITY_TYPES.register("large_shipyard", () ->
        BlockEntityType.Builder.of(
            LargeShipyardBlockEntity::new,
            ModBlocks.LARGE_SHIPYARD.get(),
            ModBlocks.GRUDGE_HEAVY_BLOCK.get()
        ).build(null)
    );

    public static final DeferredHolder<
        BlockEntityType<?>,
        BlockEntityType<org.trp.shincolle.block.entity.VolCoreBlockEntity>
    > VOL_CORE = BLOCK_ENTITY_TYPES.register("blockvolcore", () ->
        BlockEntityType.Builder.of(
            org.trp.shincolle.block.entity.VolCoreBlockEntity::new,
            ModBlocks.VOL_CORE.get()
        ).build(null)
    );

    public static final DeferredHolder<
        BlockEntityType<?>,
        BlockEntityType<WayPointBlockEntity>
    > WAYPOINT = BLOCK_ENTITY_TYPES.register("blockwaypoint", () ->
        BlockEntityType.Builder.of(
            WayPointBlockEntity::new,
            ModBlocks.WAYPOINT.get()
        ).build(null)
    );

    public static final DeferredHolder<
        BlockEntityType<?>,
        BlockEntityType<CraneBlockEntity>
    > CRANE = BLOCK_ENTITY_TYPES.register("blockcrane", () ->
        BlockEntityType.Builder.of(
            CraneBlockEntity::new,
            ModBlocks.CRANE.get()
        ).build(null)
    );

    public static final DeferredHolder<
        BlockEntityType<?>,
        BlockEntityType<org.trp.shincolle.block.entity.ChairBlockEntity>
    > CHAIR = BLOCK_ENTITY_TYPES.register("blockchair", () ->
        BlockEntityType.Builder.of(
            org.trp.shincolle.block.entity.ChairBlockEntity::new,
            ModBlocks.CHAIR.get()
        ).build(null)
    );
}
