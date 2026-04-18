package org.trp.shincolle.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.block.entity.LargeShipyardBlockEntity;
import org.trp.shincolle.block.entity.SmallShipyardBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Shincolle.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SmallShipyardBlockEntity>> SMALL_SHIPYARD =
            BLOCK_ENTITY_TYPES.register("small_shipyard", () ->
                    BlockEntityType.Builder.of(SmallShipyardBlockEntity::new, ModBlocks.SMALL_SHIPYARD.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LargeShipyardBlockEntity>> LARGE_SHIPYARD =
            BLOCK_ENTITY_TYPES.register("large_shipyard", () ->
                    BlockEntityType.Builder.of(LargeShipyardBlockEntity::new, ModBlocks.LARGE_SHIPYARD.get()).build(null));
}
