package org.trp.shincolle.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.trp.shincolle.custom.CustomShipConfig;
import org.trp.shincolle.custom.CustomShipRegistry;
import org.trp.shincolle.entity.EntityCustomShipGeo;
import org.trp.shincolle.entity.base.EntityShipBase;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.Optional;

public class CustomShipGeoModel extends GeoModel<EntityCustomShipGeo> {

    @Override
    public ResourceLocation getModelResource(EntityCustomShipGeo animatable) {
        String id = animatable.getCustomShipId();
        return ResourceLocation.fromNamespaceAndPath(CustomShipRegistry.NAMESPACE, "geo/" + id + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityCustomShipGeo animatable) {
        String id = animatable.getCustomShipId();
        ResourceLocation loc = CustomShipRegistry.getTextureLocation(id);
        if (loc != null) {
            return loc;
        }
        return ResourceLocation.fromNamespaceAndPath(CustomShipRegistry.NAMESPACE, "textures/entity/" + id + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityCustomShipGeo animatable) {
        String id = animatable.getCustomShipId();
        return ResourceLocation.fromNamespaceAndPath(CustomShipRegistry.NAMESPACE, "animations/" + id + ".animation.json");
    }

    @Override
    public net.minecraft.client.renderer.RenderType getRenderType(EntityCustomShipGeo animatable, ResourceLocation texture) {
        return net.minecraft.client.renderer.RenderType.entityTranslucent(texture);
    }

    @Override
    public BakedGeoModel getBakedModel(ResourceLocation location) {
        String path = location.getPath();
        String id = path.substring(path.lastIndexOf('/') + 1).replace(".geo.json", "");
        BakedGeoModel customModel = org.trp.shincolle.custom.CustomShipGeoData.getBakedModel(id);
        BakedGeoModel modelToUse = null;
        if (customModel != null) {
            modelToUse = customModel;
        } else {
            try {
                modelToUse = super.getBakedModel(location);
            } catch (Exception e) {
                return null;
            }
        }

        if (modelToUse != null && getAnimationProcessor() != null) {
            getAnimationProcessor().setActiveModel(modelToUse);
        }
        return modelToUse;
    }

    @Override
    public Animation getAnimation(EntityCustomShipGeo animatable, String name) {
        String id = animatable.getCustomShipId();
        var bakedAnims = org.trp.shincolle.custom.CustomShipGeoData.getBakedAnimations(id);
        if (bakedAnims != null) {
            Animation anim = bakedAnims.getAnimation(name);
            if (anim != null) {
                return anim;
            }
        }
        try {
            return super.getAnimation(animatable, name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setCustomAnimations(EntityCustomShipGeo animatable, long instanceId, AnimationState<EntityCustomShipGeo> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        String id = animatable.getCustomShipId();

        applyFacialParts(animatable);

        EntityModelData modelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        if (modelData != null) {
            float headPitch = modelData.headPitch() * Mth.DEG_TO_RAD;
            float headYaw = modelData.netHeadYaw() * Mth.DEG_TO_RAD;

            getBone("head").ifPresent(head -> {
                head.setRotX(headPitch);
                head.setRotY(headYaw);
            });
            getBone("Head").ifPresent(head -> {
                head.setRotX(headPitch);
                head.setRotY(headYaw);
            });
        }

        boolean hasWalk = CustomShipRegistry.hasAnimation(id, "walk");
        boolean hasRun = CustomShipRegistry.hasAnimation(id, "run");
        if (!hasWalk && !hasRun) {
            float limbSwing = animationState.getLimbSwing();
            float limbSwingAmount = animationState.getLimbSwingAmount();
            float angleAdd1 = Mth.cos(limbSwing * 0.7F) * limbSwingAmount;
            float angleAdd2 = Mth.cos(limbSwing * 0.7F + (float) Math.PI) * limbSwingAmount;

            applyBoneRotX("leg_left", angleAdd1);
            applyBoneRotX("LegLeft", angleAdd1);
            applyBoneRotX("leg_right", angleAdd2);
            applyBoneRotX("LegRight", angleAdd2);

            applyBoneRotX("arm_left", angleAdd2 * 0.5F);
            applyBoneRotX("ArmLeft", angleAdd2 * 0.5F);
            applyBoneRotX("arm_right", angleAdd1 * 0.5F);
            applyBoneRotX("ArmRight", angleAdd1 * 0.5F);
        }

        if (!CustomShipRegistry.hasAnimation(id, "sit") && animatable.getIsSitting()) {
            applyBoneRotX("leg_left", -1.4F);
            applyBoneRotX("LegLeft", -1.4F);
            applyBoneRotX("leg_right", -1.4F);
            applyBoneRotX("LegRight", -1.4F);
            getBone("body").ifPresent(b -> b.setPosY(b.getPosY() - 5.0F));
            getBone("root").ifPresent(r -> r.setPosY(r.getPosY() - 5.0F));
        }

        if (!CustomShipRegistry.hasAnimation(id, "dead") && (animatable.isInDeadPose() || animatable.isDeadOrDying())) {
            getBone("root").ifPresent(r -> {
                r.setRotX(-1.57F);
                r.setRotZ(0.0F);
                r.setPosY(r.getPosY() + 4.0F);
            });
        }

        CustomShipConfig config = CustomShipRegistry.getConfig(id);
        if (config != null && config.appearance_parts != null) {
            for (CustomShipConfig.AppearancePartConfig part : config.appearance_parts) {
                if (part.key != null && !part.key.isEmpty()) {
                    boolean visible = animatable.getEquipFlag(part.key);
                    if (part.bones != null) {
                        for (String boneName : part.bones) {
                            setBoneHidden(boneName, !visible);
                        }
                    }
                }
            }
        }
    }

    private void applyFacialParts(EntityCustomShipGeo entity) {
        int face = entity.getFaceId();
        int mouth = entity.getMouthId();
        int emo = entity.getEmotionPrimary();
        boolean isShy = (emo == EntityShipBase.EMOTION_SHY || emo == EntityShipBase.EMOTION_HAPPY);

        for (int i = 0; i <= 4; i++) {
            boolean visible = (i == (face % 5));
            setBoneHidden("Face" + i, !visible);
            setBoneHidden("face" + i, !visible);
        }

        for (int i = 0; i <= 2; i++) {
            boolean visible = (i == (mouth % 3));
            setBoneHidden("Mouth" + i, !visible);
            setBoneHidden("mouth" + i, !visible);
        }

        setBoneHidden("Flush0", !isShy);
        setBoneHidden("Flush1", !isShy);
        setBoneHidden("flush0", !isShy);
        setBoneHidden("flush1", !isShy);
    }

    private void setBoneHidden(String boneName, boolean hidden) {
        getBone(boneName).ifPresent(bone -> bone.setHidden(hidden));
    }

    private void applyBoneRotX(String boneName, float rotX) {
        getBone(boneName).ifPresent(bone -> bone.setRotX(rotX));
    }
}
