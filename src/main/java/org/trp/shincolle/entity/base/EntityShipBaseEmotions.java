package org.trp.shincolle.entity.base;

import net.minecraft.util.Mth;

class EntityShipBaseEmotions {
    private static final int EMOTION_UPDATE_INTERVAL = 64;
    private static final float LOW_HEALTH_THRESHOLD = 0.35F;
    private static final int BLINK_DURATION = 25;
    private static final int BLINK_RANDOM_INTERVAL = 240;

    private static final int CRY_DURATION = 80;
    private static final int SCORN_DURATION = 45;
    private static final int ANGRY_DURATION = 40;
    private static final int SHY_DURATION = 80;
    private static final int HAPPY_DURATION = 60;

    private static final int SCORN_TOGGLE_MASK = 0x7FF;
    private static final int SCORN_TOGGLE_THRESHOLD = 1024;

    private static final int NORMAL_MOUTH_TICK_MASK = 0xFF;
    private static final int NORMAL_MOUTH_TICK_THRESHOLD = 160;

    private static final int CRY_MASK = 0xFF;
    private static final int DAMAGED_MASK = 0x1FF;
    private static final int BORED_MASK = 0x1FF;
    private static final int ANGRY_MASK = 0xFF;
    private static final int SHY_MASK = 0xFF;
    private static final int HAPPY_MASK = 0xFF;

    private static final float HEAD_TILT_MAX_ANGLE = -0.27F;
    private static final float HEAD_TILT_HALF_PI = (float) (Math.PI / 2.0);
    private static final int HEAD_TILT_RESET_BASE_TICKS = 70;
    private static final int HEAD_TILT_RESET_RANDOM_TICKS = 5;
    private static final float HEAD_TILT_IN_SPEED = 0.1F;
    private static final float HEAD_TILT_OUT_SPEED = 0.2F;
    private static final float HEAD_TILT_IN_MAX_TICKS = 10.0F;
    private static final float HEAD_TILT_OUT_MAX_TICKS = 8.0F;
    private static final float HEAD_TILT_ANGLE_EPS = 0.03F;

    private final EntityShipBase ship;

    private int faceTick = -1;
    private int lastEmotionUpdateTick = 0;
    private int headTiltTick = 0;
    private boolean headTiltActive = false;
    private int headTiltState = 0;

    EntityShipBaseEmotions(EntityShipBase ship) {
        this.ship = ship;
    }

    void tickEmotions() {
        if ((this.ship.tickCount - this.lastEmotionUpdateTick) >= EMOTION_UPDATE_INTERVAL) {
            this.lastEmotionUpdateTick = this.ship.tickCount;
            updateEmotionState();
        }
        applyEmotionState();
    }

    float getHeadTiltAngle(float ageInTicks) {
        int cooldown = this.ship.tickCount - this.headTiltTick;
        float maxAngle = getHeadTiltMaxAngle();
        float partTick = ageInTicks - (int) ageInTicks + cooldown;

        if (cooldown > getHeadTiltResetBaseTicks() + this.ship.getRandom().nextInt(getHeadTiltResetRandomTicks())) {
            this.headTiltTick = this.ship.tickCount;
            partTick = ageInTicks - (int) ageInTicks;
            this.headTiltActive = this.ship.getRandom().nextInt(10) > 4;
        }

        if (this.headTiltActive) {
            if (this.headTiltState > 0) {
                return maxAngle;
            }
            float f = Mth.sin(partTick * getHeadTiltInSpeed() * HEAD_TILT_HALF_PI) * maxAngle;
            if (f - getHeadTiltAngleEps() < maxAngle || partTick > getHeadTiltInMaxTicks()) {
                this.headTiltState = 1;
                f = maxAngle;
            }
            return f;
        }

        if (this.headTiltState <= 0) {
            return 0.0F;
        }
        float f = (1.0F - Mth.sin(partTick * getHeadTiltOutSpeed() * HEAD_TILT_HALF_PI)) * maxAngle;
        if (f + getHeadTiltAngleEps() > 0.0F || partTick > getHeadTiltOutMaxTicks()) {
            this.headTiltState = 0;
            f = 0.0F;
        }
        return f;
    }

    void resetFaceTick() {
        this.faceTick = -1;
    }

    void ensureFaceTick() {
        if (this.faceTick <= 0) {
            this.faceTick = this.ship.tickCount;
        }
    }

    int getFaceElapsed() {
        ensureFaceTick();
        return this.ship.tickCount - this.faceTick;
    }

    private void updateEmotionState() {
        if (this.ship.isNoFuel()) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HUNGRY);
            this.faceTick = -1;
            return;
        }

        float healthRatio = this.ship.getHealth() / this.ship.getMaxHealth();
        if (healthRatio <= 0.25F) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_CRY);
            this.faceTick = -1;
            return;
        }
        if (healthRatio <= 0.5F) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_ANGRY);
            this.faceTick = -1;
            return;
        }
        if (healthRatio <= 0.75F) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_SCORN);
            this.faceTick = -1;
            return;
        }

        int current = this.ship.getEmotionPrimary();
        if (current == EntityShipBase.EMOTION_HUNGRY) {
            this.faceTick = -1;
        } else if (current != EntityShipBase.EMOTION_NORMAL && current != EntityShipBase.EMOTION_BORED) {
            return;
        }

        if (this.ship.getEmotionPrimary() == EntityShipBase.EMOTION_NORMAL) {
            if (this.ship.getRandom().nextInt(3) == 0) {
                this.ship.setEmotionPrimary(EntityShipBase.EMOTION_BORED);
            }
        } else if (this.ship.getRandom().nextInt(4) == 0) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_NORMAL);
        }

        if (this.ship.getEmotionSecondary() == EntityShipBase.EMOTION_NORMAL) {
            if (this.ship.getRandom().nextInt(3) == 0) {
                this.ship.setEmotionSecondary(EntityShipBase.EMOTION_BORED);
            }
        } else if (this.ship.getRandom().nextInt(3) == 0) {
            this.ship.setEmotionSecondary(EntityShipBase.EMOTION_NORMAL);
        }
    }

    private void applyEmotionState() {
        int emotion = this.ship.getEmotionPrimary();
        switch (emotion) {
            case EntityShipBase.EMOTION_BLINK -> applyBlink();
            case EntityShipBase.EMOTION_CRY ->
                    applyTimedEmotion(CRY_DURATION, this.ship::setFaceCry, EntityShipBase.EMOTION_NORMAL);
            case EntityShipBase.EMOTION_SCORN ->
                    applyTimedEmotion(SCORN_DURATION, this.ship::setFaceScornOrDamaged, EntityShipBase.EMOTION_NORMAL);
            case EntityShipBase.EMOTION_ANGRY ->
                    applyTimedEmotion(ANGRY_DURATION, this.ship::setFaceAngry, EntityShipBase.EMOTION_NORMAL);
            case EntityShipBase.EMOTION_SHY ->
                    applyTimedEmotion(SHY_DURATION, this.ship::setFaceShy, EntityShipBase.EMOTION_NORMAL);
            case EntityShipBase.EMOTION_HAPPY ->
                    applyTimedEmotion(HAPPY_DURATION, this.ship::setFaceHappy, EntityShipBase.EMOTION_NORMAL);
            case EntityShipBase.EMOTION_BORED -> this.ship.setFaceBored();
            case EntityShipBase.EMOTION_HUNGRY -> this.ship.setFaceHungry();
            case EntityShipBase.EMOTION_NORMAL, EntityShipBase.EMOTION_DEBUG -> this.ship.setFaceNormal();
            default -> this.ship.setFaceNormal();
        }

        if (emotion == EntityShipBase.EMOTION_NORMAL && this.ship.getRandom().nextInt(BLINK_RANDOM_INTERVAL) == 0) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_BLINK);
            this.faceTick = -1;
        }
    }

    private void applyBlink() {
        ensureFaceTick();
        int tick = getFaceElapsed();
        if (tick >= BLINK_DURATION) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_NORMAL);
            this.faceTick = -1;
            return;
        }
        this.ship.setFaceId(EntityShipBase.FACE_EYES_CLOSED);
        this.ship.setMouthId(EntityShipBase.MOUTH_FRONT_0);
    }

    private void applyTimedEmotion(int maxTime, Runnable action, int resetEmotion) {
        ensureFaceTick();
        if (getFaceElapsed() > maxTime) {
            this.ship.setEmotionPrimary(resetEmotion);
            this.faceTick = -1;
            this.ship.setFaceNormal();
            return;
        }
        action.run();
    }

    private float getHeadTiltMaxAngle() {
        return HEAD_TILT_MAX_ANGLE;
    }

    private int getHeadTiltResetBaseTicks() {
        return HEAD_TILT_RESET_BASE_TICKS;
    }

    private int getHeadTiltResetRandomTicks() {
        return HEAD_TILT_RESET_RANDOM_TICKS;
    }

    private float getHeadTiltInSpeed() {
        return HEAD_TILT_IN_SPEED;
    }

    private float getHeadTiltOutSpeed() {
        return HEAD_TILT_OUT_SPEED;
    }

    private float getHeadTiltInMaxTicks() {
        return HEAD_TILT_IN_MAX_TICKS;
    }

    private float getHeadTiltOutMaxTicks() {
        return HEAD_TILT_OUT_MAX_TICKS;
    }

    private float getHeadTiltAngleEps() {
        return HEAD_TILT_ANGLE_EPS;
    }

    int getScornToggleMask() {
        return SCORN_TOGGLE_MASK;
    }

    int getScornToggleThreshold() {
        return SCORN_TOGGLE_THRESHOLD;
    }

    int getNormalMouthTickMask() {
        return NORMAL_MOUTH_TICK_MASK;
    }

    int getNormalMouthTickThreshold() {
        return NORMAL_MOUTH_TICK_THRESHOLD;
    }

    int getCryMask() {
        return CRY_MASK;
    }

    int getDamagedMask() {
        return DAMAGED_MASK;
    }

    int getBoredMask() {
        return BORED_MASK;
    }

    int getAngryMask() {
        return ANGRY_MASK;
    }

    int getShyMask() {
        return SHY_MASK;
    }

    int getHappyMask() {
        return HAPPY_MASK;
    }
}
