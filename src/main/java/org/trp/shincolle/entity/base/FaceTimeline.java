package org.trp.shincolle.entity.base;

public final class FaceTimeline {
    private final int tickMask;
    private final FaceStep[] steps;
    private final int fallbackFaceId;
    private final int fallbackMouthId;

    public FaceTimeline(int tickMask, FaceStep[] steps, int fallbackFaceId, int fallbackMouthId) {
        this.tickMask = tickMask;
        this.steps = steps;
        this.fallbackFaceId = fallbackFaceId;
        this.fallbackMouthId = fallbackMouthId;
    }

    public int getTickMask() {
        return this.tickMask;
    }

    public FaceStep[] getSteps() {
        return this.steps;
    }

    public int getFallbackFaceId() {
        return this.fallbackFaceId;
    }

    public int getFallbackMouthId() {
        return this.fallbackMouthId;
    }
}
