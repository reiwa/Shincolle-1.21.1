package org.trp.shincolle.entity.base;

import net.minecraft.world.entity.Entity;

public interface IShipRenderState {
    int getScaleLevel();
    int getStateEmotion(int index);
    boolean isStateMarried();
    boolean getIsSitting();
    int getFaceId();
    int getMouthId();
    float getHeadTiltAngle(float ageInTicks);
    Entity getVehicle();
}
