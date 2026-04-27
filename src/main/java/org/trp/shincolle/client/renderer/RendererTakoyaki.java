package org.trp.shincolle.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.ModelTakoyaki;
import org.trp.shincolle.client.renderer.layer.GenericGlowLayer;

public class RendererTakoyaki<T extends Mob> extends RendererSimpleMob<T, ModelTakoyaki<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/takoyaki.png");

    public RendererTakoyaki(EntityRendererProvider.Context context) {
        super(context, new ModelTakoyaki<>(context.bakeLayer(ModelTakoyaki.LAYER_LOCATION)), 0.5f, 0.34f, TEXTURE);
        this.addLayer(new GenericGlowLayer<>(this, TEXTURE));
    }
}
