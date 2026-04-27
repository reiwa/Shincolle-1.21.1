package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelMountSuH;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererMountSuH<T extends Mob> extends RendererSimpleMob<T, ModelMountSuH<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/mount_su_h.png");

    public RendererMountSuH(EntityRendererProvider.Context context) {
        super(context, new ModelMountSuH<>(context.bakeLayer(ModelMountSuH.LAYER_LOCATION)), 0.5f, 0.42f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
