package org.trp.shincolle.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.projectile.EntityProjectileStatic;

public class RendererProjectileStatic extends EntityRenderer<EntityProjectileStatic> {
    public RendererProjectileStatic(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(EntityProjectileStatic entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(EntityProjectileStatic entity) {
        return ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/abyss_missile.png");
    }
}
