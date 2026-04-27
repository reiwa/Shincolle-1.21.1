package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountIsH;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountIsH<T extends Mob> extends RendererSimpleMob<T, ModelMountIsH<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_is_h.png");

    public RendererMountIsH(EntityRendererProvider.Context context) {
        super(context, new ModelMountIsH<>(context.bakeLayer(ModelMountIsH.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
