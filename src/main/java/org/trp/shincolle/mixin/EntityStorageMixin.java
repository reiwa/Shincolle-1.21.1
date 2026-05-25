package org.trp.shincolle.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.chunk.storage.EntityStorage;
import net.minecraft.world.level.entity.ChunkEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.trp.shincolle.entity.base.EntityShipBase;

@Mixin(EntityStorage.class)
public abstract class EntityStorageMixin {

    @Inject(method = "storeEntities", at = @At("HEAD"))
    private void shincolle$beforeStoreEntities(ChunkEntities<Entity> entities, CallbackInfo ci) {
        EntityShipBase.shincolle$isSavingChunkEntities.set(true);
    }

    @Inject(method = "storeEntities", at = @At("RETURN"))
    private void shincolle$afterStoreEntities(ChunkEntities<Entity> entities, CallbackInfo ci) {
        EntityShipBase.shincolle$isSavingChunkEntities.set(false);
    }
}
