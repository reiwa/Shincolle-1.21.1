package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountHbH;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountHbH<T extends Mob> extends RendererSimpleMob<T, ModelMountHbH<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_hb_h.png");

    public RendererMountHbH(EntityRendererProvider.Context context) {
        super(context, new ModelMountHbH<>(context.bakeLayer(ModelMountHbH.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
