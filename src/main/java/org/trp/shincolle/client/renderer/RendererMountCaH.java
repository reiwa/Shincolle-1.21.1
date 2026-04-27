package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountCaH;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountCaH<T extends Mob> extends RendererSimpleMob<T, ModelMountCaH<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_ca_h.png");

    public RendererMountCaH(EntityRendererProvider.Context context) {
        super(context, new ModelMountCaH<>(context.bakeLayer(ModelMountCaH.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
