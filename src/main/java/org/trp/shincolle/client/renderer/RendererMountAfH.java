package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountAfH;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountAfH<T extends Mob> extends RendererSimpleMob<T, ModelMountAfH<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_af_h.png");

    public RendererMountAfH(EntityRendererProvider.Context context) {
        super(context, new ModelMountAfH<>(context.bakeLayer(ModelMountAfH.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
