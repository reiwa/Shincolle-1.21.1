package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountCaWD;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountCaWD<T extends Mob> extends RendererSimpleMob<T, ModelMountCaWD<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_ca_w_d.png");

    public RendererMountCaWD(EntityRendererProvider.Context context) {
        super(context, new ModelMountCaWD<>(context.bakeLayer(ModelMountCaWD.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
