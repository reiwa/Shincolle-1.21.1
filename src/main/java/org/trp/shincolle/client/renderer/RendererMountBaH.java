package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountBaH;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountBaH<T extends Mob> extends RendererSimpleMob<T, ModelMountBaH<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_ba_h.png");

    public RendererMountBaH(EntityRendererProvider.Context context) {
        super(context, new ModelMountBaH<>(context.bakeLayer(ModelMountBaH.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
