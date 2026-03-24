package org.trp.shincolle.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public class EntityNorthernHime extends EntityShipBase {

    public static final String EQUIP_CANNON = "equip_cannon";
    public static final String EQUIP_SANTA_CLOTH = "equip_santa_cloth";
    public static final String EQUIP_SANTA_HAT = "equip_santa_hat";
    public static final String EQUIP_UMBRELLA = "equip_umbrella";
    public static final String EQUIP_SHOES = "equip_shoes";

    public EntityNorthernHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_CANNON, "gui.shincolle.equip.cannon"),
                new EquipOption(EQUIP_SANTA_CLOTH, "gui.shincolle.equip.santa_cloth"),
                new EquipOption(EQUIP_SANTA_HAT, "gui.shincolle.equip.santa_hat"),
                new EquipOption(EQUIP_UMBRELLA, "gui.shincolle.equip.umbrella"),
                new EquipOption(EQUIP_SHOES, "gui.shincolle.equip.shoes")
        );
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.NORTHERN_HIME_SPAWN_EGG.get();
    }

    @Override
    protected void migrateLegacyStateFlags(int stateFlags) {
        setEquipFlag(EQUIP_CANNON, (stateFlags & (1 << 0)) != 0);
        boolean santa = (stateFlags & (1 << 1)) != 0;
        setEquipFlag(EQUIP_SANTA_CLOTH, santa);
        setEquipFlag(EQUIP_SANTA_HAT, santa);
        setEquipFlag(EQUIP_UMBRELLA, (stateFlags & (1 << 2)) != 0);
        setEquipFlag(EQUIP_SHOES, (stateFlags & (1 << 3)) != 0);
    }

    @Override
    protected void setFaceNormal() {
        this.setFaceId(FACE_EYES_OPEN);
        int tick = this.tickCount & EMOTION_TICK_MASK_8BIT;
        if (this.getEmotionSecondary() == EMOTION_BORED && tick > 200) {
            this.setMouthId(mapLegacyMouth(0));
        } else {
            this.setMouthId(mapLegacyMouth(3));
        }
    }

    @Override
    protected void setFaceCry() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        if (tick < 128) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 2 : 5));
        } else {
            this.setFaceId(FACE_CRY);
            this.setMouthId(mapLegacyMouth(5));
        }
    }

    @Override
    protected void setFaceDamaged() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_9BIT;
        if (tick < 200) {
            this.setFaceId(FACE_DOT_EYES_TEAR);
            this.setMouthId(mapLegacyMouth(tick < 60 ? 4 : 5));
        } else if (tick < 400) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 3 : 5));
        } else {
            this.setFaceId(FACE_SOFT);
            this.setMouthId(mapLegacyMouth(tick < 450 ? 2 : 3));
        }
    }

    @Override
    protected void setFaceScorn() {
        this.setFaceId(FACE_EYES_HALF);
        this.setMouthId(mapLegacyMouth(3));
    }

    @Override
    protected void setFaceHungry() {
        this.setFaceId(FACE_DESPAIR);
        this.setMouthId(mapLegacyMouth(3));
    }

    @Override
    protected void setFaceAngry() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        if (tick < 128) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 64 ? 3 : 1));
        } else {
            this.setFaceId(FACE_EYES_HALF);
            this.setMouthId(mapLegacyMouth(tick < 170 ? 0 : 3));
        }
    }

    @Override
    protected void setFaceBored() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_9BIT;
        if (tick < 170) {
            this.setFaceId(FACE_EYES_CLOSED);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 0 : 3));
        } else if (tick < 340) {
            this.setFaceId(FACE_DOT_EYES);
            this.setMouthId(mapLegacyMouth(tick < 250 ? 4 : 3));
        } else {
            this.setFaceId(FACE_EYES_OPEN);
            this.setMouthId(mapLegacyMouth(tick < 420 ? 4 : 3));
        }
    }

    @Override
    protected void setFaceShy() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        this.setFaceId(FACE_EYES_OPEN);
        this.setMouthId(mapLegacyMouth(tick < 150 ? 3 : 2));
    }

    @Override
    protected void setFaceHappy() {
        int tick = getFaceElapsed() & EMOTION_TICK_MASK_8BIT;
        if (tick < 140) {
            this.setFaceId(FACE_TENSION);
            this.setMouthId(mapLegacyMouth(tick < 80 ? 4 : 3));
        } else {
            this.setFaceId(FACE_WINK);
            this.setMouthId(mapLegacyMouth(3));
        }
    }

    private int mapLegacyMouth(int legacyId) {
        return switch (legacyId) {
            case 0 -> MOUTH_FRONT_0;
            case 1 -> MOUTH_FRONT_1;
            case 2 -> MOUTH_FRONT_2;
            case 3 -> MOUTH_FLIP_0;
            case 4 -> MOUTH_FLIP_1;
            case 5 -> MOUTH_FLIP_2;
            default -> MOUTH_FRONT_0;
        };
    }


}