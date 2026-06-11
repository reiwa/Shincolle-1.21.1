package org.trp.shincolle.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.trp.shincolle.entity.EntitySSNH;
import org.trp.shincolle.entity.base.EntityShipBase;

import java.util.Objects;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "canAddPassenger", at = @At("HEAD"), cancellable = true)
    private void shincolle$allowOwnedPassenger(Entity passenger, CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;
        if (passenger instanceof EntityShipBase ship) {
            if (self instanceof Player player && Objects.equals(ship.getOwnerUUID(), player.getUUID())) {
                cir.setReturnValue(true);
            } else if (self instanceof EntityShipBase vehicleShip && Objects.equals(ship.getOwnerUUID(), vehicleShip.getOwnerUUID())) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "getPassengerAttachmentPoint", at = @At("RETURN"), cancellable = true)
    private void shincolle$offsetOwnedPassenger(Entity passenger, EntityDimensions dimensions, float scale,
                                                CallbackInfoReturnable<Vec3> cir) {
        Entity self = (Entity) (Object) this;
        if (self instanceof Player player
                && passenger instanceof EntityShipBase ship
                && Objects.equals(ship.getOwnerUUID(), player.getUUID())) {
            double passengerY;
            Vec3 localOffset;
            if (passenger instanceof EntitySSNH) {
                passengerY = player.getEyeHeight() - 0.33D;
                localOffset = new Vec3(-0.35D, passengerY, -0.07D);
            } else {
                passengerY = player.getEyeHeight() - 0.5D;
                localOffset = new Vec3(0.0D, passengerY, -0.25D);
            }
            float radians = -player.yBodyRot * ((float)Math.PI / 180F);
            cir.setReturnValue(localOffset.yRot(radians));
        }
    }
}
