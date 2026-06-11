package org.trp.shincolle.entity.base;

import org.trp.shincolle.init.ModParticles;

final class EntityShipBaseReactions {

    private final EntityShipBase ship;

    private int emotesTick;
    private int emotionParticleSeq;

    EntityShipBaseReactions(EntityShipBase ship) {
        this.ship = ship;
    }

    void tickEmotes() {
        if (this.emotesTick > 0) {
            this.emotesTick--;
        }
    }

    int getEmotesTick() {
        return this.emotesTick;
    }

    void applyEmotesReaction(int type) {
        if (this.emotesTick > 10 && type == 2) {
            return;
        }
        if (this.emotesTick > 0 && type != 2) {
            return;
        }
        switch (type) {
            case 0 -> {
                if (this.ship.getRandom().nextInt(7) == 0) {
                    setEmotesTick(50);
                    reactionNormal();
                }
            }
            case 1 -> {
                if (this.ship.getRandom().nextInt(9) == 0) {
                    setEmotesTick(60);
                    reactionStranger();
                }
            }
            case 2 -> {
                setEmotesTick(40);
                reactionDamaged();
            }
            case 3 -> {
                if (this.ship.getRandom().nextInt(6) == 0) {
                    setEmotesTick(60);
                    reactionAttack();
                }
            }
            case 4 -> {
                if (this.ship.getRandom().nextInt(3) == 0) {
                    setEmotesTick(20);
                    reactionIdle();
                }
            }
            case 5 -> {
                if (this.ship.getRandom().nextInt(3) == 0) {
                    setEmotesTick(25);
                    reactionCommand();
                }
            }
            case 6 -> reactionShock();
            default -> {
            }
        }
    }

    void applyParticleEmotion(EmotionParticleType type) {
        if (this.ship.level().isClientSide) {
            spawnEmotionParticleClient(type);
            return;
        }
        if (
            !(this.ship.level() instanceof
                    net.minecraft.server.level.ServerLevel)
        ) {
            return;
        }
        int nextSeq = this.emotionParticleSeq++ & 0x7FFF;
        int packed = (nextSeq << 16) | (type.getId() & 0xFF);
        this.ship.setEmotionParticlePacked(packed);
    }

    void applyParticleEmotion(int typeId) {
        applyParticleEmotion(EmotionParticleType.fromId(typeId));
    }

    void spawnEmotionParticleClient(EmotionParticleType type) {
        if (
            !(this.ship.level() instanceof
                    net.minecraft.client.multiplayer.ClientLevel clientLevel)
        ) {
            return;
        }
        double baseX =
            this.ship.getX() +
            (this.ship.getRandom().nextDouble() - 0.5D) * 0.2D;
        double baseY = this.ship.getY() + this.ship.getBbHeight() * 0.6D;
        double baseZ =
            this.ship.getZ() +
            (this.ship.getRandom().nextDouble() - 0.5D) * 0.2D;
        float height = (float) (this.ship.getBbHeight() * 0.6D);
        clientLevel.addParticle(
            ModParticles.PARTICLE_EMOTION.get(),
            baseX,
            baseY,
            baseZ,
            height,
            this.ship.getId(),
            type.getId()
        );
    }

    private int getMoraleLevel() {
        int morale = this.ship.getMorale();
        if (morale > 5100) return 0;
        if (morale > 3900) return 1;
        if (morale > 2100) return 2;
        if (morale > 900) return 3;
        return 4;
    }

    void setEmotesTick(int ticks) {
        this.emotesTick = Math.max(this.emotesTick, ticks);
    }

    private static final java.util.Set<Integer> MAJOR_BODY_PARTS = java.util.Set.of(0, 1, 2, 4);

    private void reactionNormal() {
        this.ship.resetFaceTick();
        int m = this.ship.getMorale();
        int body = this.ship.getHitBodyID();
        int baseMorale = 50;
        boolean sensitive = (body == this.ship.getSensitiveBody());

        switch (getMoraleLevel()) {
            case 0 -> {
                this.ship.setStateEmotion(1, sensitive ? 7 : 8, true);
                if (sensitive) {
                    applyParticleEmotion(this.ship.getRandom().nextBoolean() ? 31 : 10);
                    if (m < 7650) {
                        this.ship.addMorale(baseMorale * 3 + this.ship.getRandom().nextInt(baseMorale + 1));
                    }
                } else {
                    applyParticleEmotion(MAJOR_BODY_PARTS.contains(body) ? (this.ship.isStateMarried() ? 15 : 1) : (this.ship.getRandom().nextBoolean() ? 1 : 7));
                }
            }
            case 1 -> {
                this.ship.setStateEmotion(1, 7, true);
                if (sensitive) {
                    applyParticleEmotion(this.ship.isStateMarried() ? (this.ship.getRandom().nextBoolean() ? 31 : 10) : 10);
                    this.ship.addMorale(baseMorale + this.ship.getRandom().nextInt(baseMorale + 1));
                } else {
                    applyParticleEmotion(MAJOR_BODY_PARTS.contains(body) ? (this.ship.isStateMarried() ? 1 : 16) : (this.ship.getRandom().nextBoolean() ? 1 : 7));
                }
            }
            case 2 -> {
                if (sensitive) {
                    this.ship.setStateEmotion(1, 7, true);
                    applyParticleEmotion(this.ship.isStateMarried() ? 19 : 18);
                    this.ship.addMorale(baseMorale + this.ship.getRandom().nextInt(baseMorale + 1));
                    if (this.ship.getRandom().nextInt(6) == 0) {
                        this.ship.pushAITarget();
                    }
                } else {
                    if (MAJOR_BODY_PARTS.contains(body)) {
                        applyParticleEmotion(this.ship.isStateMarried() ? 1 : 27);
                        if (this.ship.getRandom().nextInt(8) == 0) {
                            this.ship.pushAITarget();
                        }
                    } else {
                        int[] emotes = {30, 7, 26, 11, 29};
                        applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
                    }
                }
            }
            case 3 -> {
                if (sensitive) {
                    this.ship.setStateEmotion(1, 7, true);
                    applyParticleEmotion(32);
                    this.ship.addMorale(this.ship.getRandom().nextInt(baseMorale + 1));
                    if (this.ship.getRandom().nextInt(2) == 0) {
                        this.ship.pushAITarget();
                    } else if (this.ship.getAITarget() != null && this.ship.getRandom().nextInt(8) == 0) {
                        this.ship.attackAITarget();
                    }
                } else {
                    if (MAJOR_BODY_PARTS.contains(body)) {
                        this.ship.setStateEmotion(1, 3, true);
                        applyParticleEmotion(32);
                        if (this.ship.getRandom().nextInt(4) == 0) {
                            this.ship.pushAITarget();
                        }
                    } else {
                        int[] emotes = {30, 2, 3, 0};
                        applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
                    }
                }
            }
            default -> {
                if (sensitive) {
                    this.ship.setStateEmotion(1, 7, true);
                    applyParticleEmotion(6);
                    this.ship.addMorale(-(baseMorale * 10 + this.ship.getRandom().nextInt(baseMorale * 5 + 1)));
                    this.ship.pushAITarget();
                    if (this.ship.getAITarget() != null && this.ship.getRandom().nextInt(3) == 0) {
                        this.ship.attackAITarget();
                    }
                } else {
                    if (MAJOR_BODY_PARTS.contains(body)) {
                        this.ship.setStateEmotion(1, 2, true);
                        applyParticleEmotion(this.ship.getRandom().nextInt(3) == 0 ? 6 : 32);
                        if (this.ship.getRandom().nextInt(2) == 0) {
                            this.ship.pushAITarget();
                        } else if (this.ship.getAITarget() != null && this.ship.getRandom().nextInt(5) == 0) {
                            this.ship.attackAITarget();
                        }
                    } else {
                        int[] emotes = {8, 2, 20, 5, 34};
                        applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
                    }
                }
            }
        }
    }

    private void reactionStranger() {
        int body = this.ship.getHitBodyID();
        if (body == this.ship.getSensitiveBody()) {
            this.ship.setStateEmotion(1, 6, true);
            applyParticleEmotion(this.ship.getRandom().nextBoolean() ? 6 : 22);
            if (this.ship.getRandom().nextInt(2) == 0) {
                this.ship.pushAITarget();
            } else if (this.ship.getAITarget() != null && this.ship.getRandom().nextInt(4) == 0) {
                this.ship.attackAITarget();
            }
        } else {
            this.ship.setStateEmotion(1, 3, true);
            if (MAJOR_BODY_PARTS.contains(body)) {
                applyParticleEmotion(this.ship.getRandom().nextBoolean() ? 6 : 5);
                if (this.ship.getRandom().nextInt(4) == 0) {
                    this.ship.pushAITarget();
                } else if (this.ship.getAITarget() != null && this.ship.getRandom().nextInt(8) == 0) {
                    this.ship.attackAITarget();
                }
            } else {
                int[] emotes = {9, 2, 20, 8, 0, 34};
                applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
            }
        }
    }

    private void reactionAttack() {
        if (getMoraleLevel() == 0) {
            this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
            EmotionParticleType[] emotes = {
                EmotionParticleType.SILLY_TONGUE,
                EmotionParticleType.EVIL_GRIN,
                EmotionParticleType.TONGUE_OUT,
                EmotionParticleType.LAUGH,
                EmotionParticleType.MUSIC_NOTE,
            };
            applyParticleEmotion(
                emotes[this.ship.getRandom().nextInt(emotes.length)]
            );
        } else {
            EmotionParticleType[] emotes = {
                EmotionParticleType.SPARKLE_EYES,
                EmotionParticleType.SIGH,
                EmotionParticleType.MUSIC_NOTE,
                EmotionParticleType.EXCLAMATION,
                EmotionParticleType.MUSIC_NOTE,
                EmotionParticleType.ANGER,
            };
            applyParticleEmotion(
                emotes[this.ship.getRandom().nextInt(emotes.length)]
            );
        }
    }

    private void reactionDamaged() {
        if (getMoraleLevel() <= 2) {
            EmotionParticleType[] emotes = {
                EmotionParticleType.SIGH,
                EmotionParticleType.SILENCE,
                EmotionParticleType.SWEAT_DROPS,
                EmotionParticleType.QUESTION,
                EmotionParticleType.TEARS,
            };
            applyParticleEmotion(
                emotes[this.ship.getRandom().nextInt(emotes.length)]
            );
        } else {
            EmotionParticleType[] emotes = {
                EmotionParticleType.SIGH,
                EmotionParticleType.SILENCE,
                EmotionParticleType.SWEAT_DROPS,
                EmotionParticleType.QUESTION,
                EmotionParticleType.SWEAT_DROP_BIG,
                EmotionParticleType.TEARS,
            };
            applyParticleEmotion(
                emotes[this.ship.getRandom().nextInt(emotes.length)]
            );
        }
    }

    private void reactionIdle() {
        switch (getMoraleLevel()) {
            case 0, 1 -> {
                EmotionParticleType[] emotesSparkling = {
                    EmotionParticleType.SILLY_TONGUE,
                    EmotionParticleType.EVIL_GRIN,
                    EmotionParticleType.TONGUE_OUT,
                    EmotionParticleType.DROOL,
                    EmotionParticleType.HEART,
                    EmotionParticleType.POUT_BOUNCE,
                    EmotionParticleType.LAUGH,
                    EmotionParticleType.SPARKLE_EYES,
                    EmotionParticleType.MUSIC_NOTE,
                };
                applyParticleEmotion(
                    emotesSparkling[
                        this.ship.getRandom().nextInt(emotesSparkling.length)
                    ]
                );
            }
            case 2 -> {
                EmotionParticleType[] emotesNormal = {
                    EmotionParticleType.HAPPY_GLANCE,
                    EmotionParticleType.QUESTION,
                    EmotionParticleType.HAPPY_BOB,
                    EmotionParticleType.DROOL,
                    EmotionParticleType.SHAKE_HEAD,
                    EmotionParticleType.LAUGH,
                    EmotionParticleType.BLINK,
                };
                applyParticleEmotion(
                    emotesNormal[
                        this.ship.getRandom().nextInt(emotesNormal.length)
                    ]
                );
            }
            default -> {
                EmotionParticleType[] emotesTired = {
                    EmotionParticleType.SWEAT_DROP_BIG,
                    EmotionParticleType.SWEAT_DROPS,
                    EmotionParticleType.QUESTION,
                    EmotionParticleType.TEARS,
                    EmotionParticleType.DIZZY_EYES,
                    EmotionParticleType.ORZ,
                    EmotionParticleType.SCRATCH_HEAD,
                };
                applyParticleEmotion(
                    emotesTired[
                        this.ship.getRandom().nextInt(emotesTired.length)
                    ]
                );
            }
        }
    }

    private void reactionCommand() {
        switch (getMoraleLevel()) {
            case 0, 1, 2 -> {
                EmotionParticleType[] emotesOk = {
                    EmotionParticleType.CIRCLE,
                    EmotionParticleType.EXCLAMATION,
                    EmotionParticleType.SPARKLE_EYES,
                    EmotionParticleType.HAPPY_GLANCE,
                    EmotionParticleType.HAPPY_BOB,
                };
                applyParticleEmotion(
                    emotesOk[this.ship.getRandom().nextInt(emotesOk.length)]
                );
            }
            default -> {
                EmotionParticleType[] emotesTired = {
                    EmotionParticleType.SWEAT_DROP_BIG,
                    EmotionParticleType.SILLY_TONGUE,
                    EmotionParticleType.QUESTION,
                    EmotionParticleType.DIZZY_EYES,
                    EmotionParticleType.HAPPY_BOB,
                    EmotionParticleType.SCRATCH_HEAD,
                };
                applyParticleEmotion(
                    emotesTired[
                        this.ship.getRandom().nextInt(emotesTired.length)
                    ]
                );
            }
        }
    }

    private void reactionShock() {
        EmotionParticleType[] emotes = {
            EmotionParticleType.SWEAT_DROP_BIG,
            EmotionParticleType.TEARS,
            EmotionParticleType.EXCLAMATION,
            EmotionParticleType.SHOCK,
        };
        applyParticleEmotion(
            emotes[this.ship.getRandom().nextInt(emotes.length)]
        );
    }
}
