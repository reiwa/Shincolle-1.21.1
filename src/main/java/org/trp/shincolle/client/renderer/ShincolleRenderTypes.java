package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class ShincolleRenderTypes extends RenderType {

    private ShincolleRenderTypes(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
        super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
    }

    public static RenderType getFlatGlow(ResourceLocation textureLocation) {
        RenderType.CompositeState renderState = RenderType.CompositeState.builder()
                .setShaderState(RENDERTYPE_EYES_SHADER)

                .setTextureState(new RenderStateShard.TextureStateShard(textureLocation, false, false))

                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)

                .setCullState(NO_CULL)
                .setWriteMaskState(COLOR_DEPTH_WRITE)
                .setLightmapState(NO_LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(false);

        return create("shincolle_flat_glow", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, renderState);
    }
}