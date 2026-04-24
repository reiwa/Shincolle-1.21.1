package org.trp.shincolle.entity.base;

final class EntityShipBaseFaceExpressions {

    private final EntityShipBase ship;
    private final EntityShipBaseEmotions emotions;

    EntityShipBaseFaceExpressions(EntityShipBase ship, EntityShipBaseEmotions emotions) {
        this.ship = ship;
        this.emotions = emotions;
    }

    void setFaceNormal() {
        this.ship.setFaceId(EntityShipBase.FACE_EYES_OPEN);
        if (this.ship.getEmotionSecondary() == EntityShipBase.EMOTION_BORED
                && (this.ship.tickCount & this.emotions.getNormalMouthTickMask()) > this.emotions.getNormalMouthTickThreshold()) {
            this.ship.setMouthId(this.ship.resolveMouthId(EntityShipBase.MOUTH_FLIP_0));
        } else {
            this.ship.setMouthId(EntityShipBase.MOUTH_FRONT_0);
        }
    }

    void setFaceCry() {
        int tick = this.ship.getFaceElapsed() & this.emotions.getCryMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(64, EntityShipBase.FACE_DOT_EYES_TEAR, EntityShipBase.MOUTH_FLIP_2),
                new FaceStep(128, EntityShipBase.FACE_DOT_EYES_TEAR, EntityShipBase.MOUTH_FRONT_2),
                new FaceStep(256, EntityShipBase.FACE_CRY, EntityShipBase.MOUTH_FRONT_2)
        };
        applyFaceTimeline(steps, EntityShipBase.FACE_CRY, EntityShipBase.MOUTH_FRONT_2, tick);
    }

    void setFaceScornOrDamaged() {
        if ((this.ship.tickCount & this.emotions.getScornToggleMask()) > this.emotions.getScornToggleThreshold()) {
            setFaceDamaged();
        } else {
            setFaceScorn();
        }
    }

    void setFaceScorn() {
        this.ship.setFaceId(EntityShipBase.FACE_EYES_HALF);
        this.ship.setMouthId(EntityShipBase.MOUTH_FRONT_1);
    }

    void setFaceDamaged() {
        int tick = this.ship.getFaceElapsed() & this.emotions.getDamagedMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(60, EntityShipBase.FACE_DOT_EYES_TEAR, EntityShipBase.MOUTH_FLIP_2),
                new FaceStep(200, EntityShipBase.FACE_DOT_EYES_TEAR, EntityShipBase.MOUTH_FRONT_2),
                new FaceStep(250, EntityShipBase.FACE_TENSION, EntityShipBase.MOUTH_FRONT_0),
                new FaceStep(400, EntityShipBase.FACE_TENSION, EntityShipBase.MOUTH_FLIP_1),
                new FaceStep(450, EntityShipBase.FACE_SOFT, EntityShipBase.MOUTH_FRONT_0)
        };
        applyFaceTimeline(steps, EntityShipBase.FACE_SOFT, EntityShipBase.MOUTH_FRONT_1, tick);
    }

    void setFaceHungry() {
        this.ship.setFaceId(EntityShipBase.FACE_DESPAIR);
        this.ship.setMouthId(EntityShipBase.MOUTH_FRONT_2);
    }

    void setFaceAngry() {
        int tick = this.ship.getFaceElapsed() & this.emotions.getAngryMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(64, EntityShipBase.FACE_EYES_CLOSED, EntityShipBase.MOUTH_FRONT_0),
                new FaceStep(128, EntityShipBase.FACE_EYES_CLOSED, EntityShipBase.MOUTH_FRONT_1),
                new FaceStep(170, EntityShipBase.FACE_EYES_HALF, EntityShipBase.MOUTH_FRONT_1)
        };
        applyFaceTimeline(steps, EntityShipBase.FACE_EYES_HALF, EntityShipBase.MOUTH_FRONT_2, tick);
    }

    void setFaceBored() {
        int tick = this.ship.getFaceElapsed() & this.emotions.getBoredMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(80, EntityShipBase.FACE_DOT_EYES, EntityShipBase.MOUTH_FRONT_0),
                new FaceStep(170, EntityShipBase.FACE_DOT_EYES, EntityShipBase.MOUTH_FLIP_1),
                new FaceStep(340, EntityShipBase.FACE_WINK, EntityShipBase.MOUTH_FRONT_0)
        };
        applyFaceTimeline(steps, EntityShipBase.FACE_EYES_OPEN, EntityShipBase.MOUTH_FRONT_0, tick);
    }

    void setFaceShy() {
        int tick = this.ship.getFaceElapsed() & this.emotions.getShyMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(80, EntityShipBase.FACE_EYES_OPEN, EntityShipBase.MOUTH_FLIP_0),
                new FaceStep(140, EntityShipBase.FACE_EYES_OPEN, EntityShipBase.MOUTH_FRONT_2)
        };
        applyFaceTimeline(steps, EntityShipBase.FACE_WINK, EntityShipBase.MOUTH_FRONT_0, tick);
    }

    void setFaceHappy() {
        int tick = this.ship.getFaceElapsed() & this.emotions.getHappyMask();
        FaceStep[] steps = new FaceStep[]{
                new FaceStep(80, EntityShipBase.FACE_TENSION, EntityShipBase.MOUTH_FRONT_0),
                new FaceStep(140, EntityShipBase.FACE_TENSION, EntityShipBase.MOUTH_FLIP_1)
        };
        applyFaceTimeline(steps, EntityShipBase.FACE_WINK, EntityShipBase.MOUTH_FLIP_1, tick);
    }

    private void applyFaceTimeline(FaceStep[] steps, int fallbackFaceId, int fallbackMouthId, int tick) {
        for (FaceStep step : steps) {
            if (tick < step.untilTick) {
                this.ship.setFaceId(step.faceId);
                this.ship.setMouthId(this.ship.resolveMouthId(step.mouthId));
                return;
            }
        }
        this.ship.setFaceId(fallbackFaceId);
        this.ship.setMouthId(this.ship.resolveMouthId(fallbackMouthId));
    }

    private record FaceStep(int untilTick, int faceId, int mouthId) {
    }
}
