package org.trp.shincolle.entity.base;

public final class FaceExpressionConfig {
    private final FaceTimeline normal;
    private final FaceTimeline normalBored;
    private final FaceTimeline cry;
    private final FaceTimeline scorn;
    private final FaceTimeline damaged;
    private final FaceTimeline hungry;
    private final FaceTimeline angry;
    private final FaceTimeline bored;
    private final FaceTimeline shy;
    private final FaceTimeline happy;

    private FaceExpressionConfig(Builder builder) {
        this.normal = builder.normal;
        this.normalBored = builder.normalBored;
        this.cry = builder.cry;
        this.scorn = builder.scorn;
        this.damaged = builder.damaged;
        this.hungry = builder.hungry;
        this.angry = builder.angry;
        this.bored = builder.bored;
        this.shy = builder.shy;
        this.happy = builder.happy;
    }

    public FaceTimeline getNormal() { return normal; }
    public FaceTimeline getNormalBored() { return normalBored; }
    public FaceTimeline getCry() { return cry; }
    public FaceTimeline getScorn() { return scorn; }
    public FaceTimeline getDamaged() { return damaged; }
    public FaceTimeline getHungry() { return hungry; }
    public FaceTimeline getAngry() { return angry; }
    public FaceTimeline getBored() { return bored; }
    public FaceTimeline getShy() { return shy; }
    public FaceTimeline getHappy() { return happy; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FaceTimeline normal;
        private FaceTimeline normalBored;
        private FaceTimeline cry;
        private FaceTimeline scorn;
        private FaceTimeline damaged;
        private FaceTimeline hungry;
        private FaceTimeline angry;
        private FaceTimeline bored;
        private FaceTimeline shy;
        private FaceTimeline happy;

        public Builder normal(FaceTimeline timeline) { this.normal = timeline; return this; }
        public Builder normalBored(FaceTimeline timeline) { this.normalBored = timeline; return this; }
        public Builder cry(FaceTimeline timeline) { this.cry = timeline; return this; }
        public Builder scorn(FaceTimeline timeline) { this.scorn = timeline; return this; }
        public Builder damaged(FaceTimeline timeline) { this.damaged = timeline; return this; }
        public Builder hungry(FaceTimeline timeline) { this.hungry = timeline; return this; }
        public Builder angry(FaceTimeline timeline) { this.angry = timeline; return this; }
        public Builder bored(FaceTimeline timeline) { this.bored = timeline; return this; }
        public Builder shy(FaceTimeline timeline) { this.shy = timeline; return this; }
        public Builder happy(FaceTimeline timeline) { this.happy = timeline; return this; }

        public FaceExpressionConfig build() {
            return new FaceExpressionConfig(this);
        }
    }
}
