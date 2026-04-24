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
        if (!(this.ship.level() instanceof net.minecraft.server.level.ServerLevel)) {
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
        if (!(this.ship.level() instanceof net.minecraft.client.multiplayer.ClientLevel clientLevel)) {
            return;
        }
        double baseX = this.ship.getX() + (this.ship.getRandom().nextDouble() - 0.5D) * 0.2D;
        double baseY = this.ship.getY() + this.ship.getBbHeight() * 0.6D;
        double baseZ = this.ship.getZ() + (this.ship.getRandom().nextDouble() - 0.5D) * 0.2D;
        float height = (float) (this.ship.getBbHeight() * 0.6D);
        clientLevel.addParticle(ModParticles.PARTICLE_EMOTION.get(), baseX, baseY, baseZ,
                height, this.ship.getId(), type.getId());
    }

    private int getMoraleLevel() {
        int morale = this.ship.getMorale();
        if (morale > 5100) return 0;
        if (morale > 3900) return 1;
        if (morale > 2100) return 2;
        if (morale > 900) return 3;
        return 4;
    }

    private void setEmotesTick(int ticks) {
        this.emotesTick = Math.max(this.emotesTick, ticks);
    }

    private void reactionNormal() {
        switch (getMoraleLevel()) {
            case 0 -> {
                this.ship.setEmotionPrimary(EntityShipBase.EMOTION_HAPPY);
                EmotionParticleType[] emotes = {
                        EmotionParticleType.BLUSH,
                        EmotionParticleType.DIZZY_EYES,
                        EmotionParticleType.POUT_BOUNCE,
                        EmotionParticleType.HEART,
                        EmotionParticleType.MUSIC_NOTE
                };
                applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
            }
            case 1 -> {
                this.ship.setEmotionPrimary(EntityShipBase.EMOTION_SHY);
                EmotionParticleType[] emotes = {
                        EmotionParticleType.HEART,
                        EmotionParticleType.LAUGH,
                        EmotionParticleType.MUSIC_NOTE
                };
                applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
            }
            case 2 -> {
                this.ship.setEmotionPrimary(EntityShipBase.EMOTION_SHY);
                EmotionParticleType[] emotes = {
                        EmotionParticleType.SIGH,
                        EmotionParticleType.MUSIC_NOTE,
                        EmotionParticleType.PEACE,
                        EmotionParticleType.HAPPY_GLANCE,
                        EmotionParticleType.BLINK
                };
                applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
            }
            case 3 -> {
                EmotionParticleType[] emotes = {
                        EmotionParticleType.SIGH,
                        EmotionParticleType.SWEAT_DROPS,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.SWEAT_DROP_BIG
                };
                applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
            }
            default -> {
                EmotionParticleType[] emotes = {
                        EmotionParticleType.TEARS,
                        EmotionParticleType.SWEAT_DROPS,
                        EmotionParticleType.ORZ,
                        EmotionParticleType.SILENCE,
                        EmotionParticleType.GLOOM
                };
                applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
            }
        }
    }

    private void reactionStranger() {
        this.ship.setEmotionPrimary(EntityShipBase.EMOTION_ANGRY);
        if (this.ship.getRandom().nextBoolean()) {
            applyParticleEmotion(this.ship.getRandom().nextBoolean()
                    ? EmotionParticleType.ANGER
                    : EmotionParticleType.CROSS);
        } else {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.DROOL,
                    EmotionParticleType.SWEAT_DROPS,
                    EmotionParticleType.ORZ,
                    EmotionParticleType.TEARS,
                    EmotionParticleType.SWEAT_DROP_BIG,
                    EmotionParticleType.GLOOM
            };
            applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
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
                    EmotionParticleType.MUSIC_NOTE
            };
            applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
        } else {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.SPARKLE_EYES,
                    EmotionParticleType.SIGH,
                    EmotionParticleType.MUSIC_NOTE,
                    EmotionParticleType.EXCLAMATION,
                    EmotionParticleType.MUSIC_NOTE,
                    EmotionParticleType.ANGER
            };
            applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
        }
    }

    private void reactionDamaged() {
        if (getMoraleLevel() <= 2) {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.SIGH,
                    EmotionParticleType.SILENCE,
                    EmotionParticleType.SWEAT_DROPS,
                    EmotionParticleType.QUESTION,
                    EmotionParticleType.TEARS
            };
            applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
        } else {
            EmotionParticleType[] emotes = {
                    EmotionParticleType.SIGH,
                    EmotionParticleType.SILENCE,
                    EmotionParticleType.SWEAT_DROPS,
                    EmotionParticleType.QUESTION,
                    EmotionParticleType.SWEAT_DROP_BIG,
                    EmotionParticleType.TEARS
            };
            applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
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
                        EmotionParticleType.MUSIC_NOTE
                };
                applyParticleEmotion(emotesSparkling[this.ship.getRandom().nextInt(emotesSparkling.length)]);
            }
            case 2 -> {
                EmotionParticleType[] emotesNormal = {
                        EmotionParticleType.HAPPY_GLANCE,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.HAPPY_BOB,
                        EmotionParticleType.DROOL,
                        EmotionParticleType.SHAKE_HEAD,
                        EmotionParticleType.LAUGH,
                        EmotionParticleType.BLINK
                };
                applyParticleEmotion(emotesNormal[this.ship.getRandom().nextInt(emotesNormal.length)]);
            }
            default -> {
                EmotionParticleType[] emotesTired = {
                        EmotionParticleType.SWEAT_DROP_BIG,
                        EmotionParticleType.SWEAT_DROPS,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.TEARS,
                        EmotionParticleType.DIZZY_EYES,
                        EmotionParticleType.ORZ,
                        EmotionParticleType.SCRATCH_HEAD
                };
                applyParticleEmotion(emotesTired[this.ship.getRandom().nextInt(emotesTired.length)]);
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
                        EmotionParticleType.HAPPY_BOB
                };
                applyParticleEmotion(emotesOk[this.ship.getRandom().nextInt(emotesOk.length)]);
            }
            default -> {
                EmotionParticleType[] emotesTired = {
                        EmotionParticleType.SWEAT_DROP_BIG,
                        EmotionParticleType.SILLY_TONGUE,
                        EmotionParticleType.QUESTION,
                        EmotionParticleType.DIZZY_EYES,
                        EmotionParticleType.HAPPY_BOB,
                        EmotionParticleType.SCRATCH_HEAD
                };
                applyParticleEmotion(emotesTired[this.ship.getRandom().nextInt(emotesTired.length)]);
            }
        }
    }

    private void reactionShock() {
        EmotionParticleType[] emotes = {
                EmotionParticleType.SWEAT_DROP_BIG,
                EmotionParticleType.TEARS,
                EmotionParticleType.EXCLAMATION,
                EmotionParticleType.SHOCK
        };
        applyParticleEmotion(emotes[this.ship.getRandom().nextInt(emotes.length)]);
    }
}
