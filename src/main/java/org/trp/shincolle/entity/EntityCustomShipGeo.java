package org.trp.shincolle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import org.trp.shincolle.custom.CustomShipRegistry;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntityCustomShipGeo extends EntityCustomShip implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public EntityCustomShipGeo(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 0, event -> {
            event.getController().transitionLength(0);
            String id = getCustomShipId();

            if (this.isInDeadPose()) {
                if (CustomShipRegistry.hasAnimation(id, "dead")) {
                    return event.setAndContinue(RawAnimation.begin().thenPlay("dead"));
                }
            }

            if (this.getIsSitting()) {
                if (CustomShipRegistry.hasAnimation(id, "sit")) {
                    return event.setAndContinue(RawAnimation.begin().thenLoop("sit"));
                }
            }

            if (event.isMoving()) {
                boolean isSprint = (this.getIsSprinting() || event.getLimbSwingAmount() > 0.9F);
                if (isSprint && CustomShipRegistry.hasAnimation(id, "run")) {
                    return event.setAndContinue(RawAnimation.begin().thenLoop("run"));
                }
                if (CustomShipRegistry.hasAnimation(id, "walk")) {
                    return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
                }
            }

            if (CustomShipRegistry.hasAnimation(id, "idle")) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
            }

            return PlayState.STOP;
        }));

        controllers.add(new AnimationController<>(this, "attack", 0, event -> {
            event.getController().transitionLength(0);
            String id = getCustomShipId();
            boolean isAttacking = (this.getAttackTick() > 0 || this.isStateLightAttack() || this.isStateHeavyAttack());
            if (isAttacking && CustomShipRegistry.hasAnimation(id, "attack")) {
                return event.setAndContinue(RawAnimation.begin().thenPlay("attack"));
            }
            return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
