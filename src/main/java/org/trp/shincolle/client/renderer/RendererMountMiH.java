package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountMiH;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountMiH<T extends Mob> extends RendererSimpleMob<T, ModelMountMiH<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_mi_h.png");

    public RendererMountMiH(EntityRendererProvider.Context context) {
        super(context, new ModelMountMiH<>(context.bakeLayer(ModelMountMiH.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
