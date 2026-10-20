package org.trp.shincolle.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.trp.shincolle.custom.CustomShipConfig;
import org.trp.shincolle.custom.CustomShipRegistry;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.item.CustomShipSpawnEggItem;

public class EntityCustomShip extends EntityShipBase {

    private static final EntityDataAccessor<String> CUSTOM_SHIP_ID =
            SynchedEntityData.defineId(EntityCustomShip.class, EntityDataSerializers.STRING);

    public EntityCustomShip(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{0, 25, 0, 40});
        getStateComponent().setFactionId(6);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 80.0)
                .add(Attributes.ATTACK_DAMAGE, 12.0)
                .add(Attributes.ARMOR, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.FOLLOW_RANGE, 36.0)
                .add(Attributes.STEP_HEIGHT, 1.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CUSTOM_SHIP_ID, "");
    }

    public String getCustomShipId() {
        return this.entityData.get(CUSTOM_SHIP_ID);
    }

    public void setCustomShipId(String id) {
        this.entityData.set(CUSTOM_SHIP_ID, id != null ? id : "");
        applyShipConfig();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (CUSTOM_SHIP_ID.equals(key)) {
            applyShipConfig();
            this.refreshDimensions();
        }
    }

    @Override
    public void tick() {
        super.tick();
        String id = getCustomShipId();
        if (!id.isEmpty()) {
            boolean missing = !CustomShipRegistry.hasShip(id);
            if (!missing && this.level().isClientSide()) {
                missing = !CustomShipRegistry.hasBakedModel(id);
            }
            if (missing) {
                this.discard();
            }
        }
    }

    public void applyShipConfig() {
        String id = getCustomShipId();
        if (id.isEmpty()) {
            return;
        }

        CustomShipConfig config = CustomShipRegistry.getConfig(id);
        if (config == null) {
            this.discard();
            return;
        }

        if (config.stats != null) {
            if (this.getAttribute(Attributes.MAX_HEALTH) != null) {
                this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(config.stats.max_hp);
            }
            if (this.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(config.stats.attack_power);
            }
            if (this.getAttribute(Attributes.ARMOR) != null) {
                this.getAttribute(Attributes.ARMOR).setBaseValue(config.stats.armor);
            }
            if (this.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(config.stats.movement_speed);
            }
            this.setHealth((float) config.stats.max_hp);
        }

        if (config.ship_class != null) {
            switch (config.getParsedShipClass()) {
                case DESTROYER -> getStateComponent().setShipClassId(0);
                case LIGHT_CRUISER -> getStateComponent().setShipClassId(1);
                case HEAVY_CRUISER -> getStateComponent().setShipClassId(2);
                case BATTLESHIP -> getStateComponent().setShipClassId(3);
                case AIRCRAFT_CARRIER -> getStateComponent().setShipClassId(4);
                case SUBMARINE -> getStateComponent().setShipClassId(5);
                default -> getStateComponent().setShipClassId(0);
            }
        }
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        String id = getCustomShipId();
        if (!id.isEmpty()) {
            CustomShipConfig config = CustomShipRegistry.getConfig(id);
            if (config != null && config.hitbox != null) {
                return EntityDimensions.scalable(config.hitbox.width, config.hitbox.height)
                        .withEyeHeight(config.hitbox.eye_height);
            }
        }
        return super.getDefaultDimensions(pose);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString(CustomShipSpawnEggItem.TAG_CUSTOM_SHIP_ID, getCustomShipId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains(CustomShipSpawnEggItem.TAG_CUSTOM_SHIP_ID)) {
            setCustomShipId(tag.getString(CustomShipSpawnEggItem.TAG_CUSTOM_SHIP_ID));
        }
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.CUSTOM_SHIP_SPAWN_EGG.get();
    }

    @Override
    protected org.trp.shincolle.entity.base.FaceExpressionConfig createFaceExpressionConfig() {
        return org.trp.shincolle.entity.base.FaceExpressionConfig.builder()
            .normal(new org.trp.shincolle.entity.base.FaceTimeline(0xFF, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(240, FACE_EYES_CLOSED, mapLegacyMouth(0)),
                new org.trp.shincolle.entity.base.FaceStep(248, FACE_EYES_OPEN, mapLegacyMouth(0))
            }, FACE_EYES_OPEN, mapLegacyMouth(0)))
            .normalBored(new org.trp.shincolle.entity.base.FaceTimeline(0xFF, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(160, FACE_EYES_OPEN, mapLegacyMouth(0)),
                new org.trp.shincolle.entity.base.FaceStep(240, FACE_EYES_CLOSED, mapLegacyMouth(3))
            }, FACE_EYES_OPEN, mapLegacyMouth(3)))
            .cry(new org.trp.shincolle.entity.base.FaceTimeline(EMOTION_TICK_MASK_8BIT, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(64, FACE_DOT_EYES_TEAR, mapLegacyMouth(5)),
                new org.trp.shincolle.entity.base.FaceStep(128, FACE_DOT_EYES_TEAR, mapLegacyMouth(2))
            }, FACE_CRY, mapLegacyMouth(2)))
            .damaged(new org.trp.shincolle.entity.base.FaceTimeline(EMOTION_TICK_MASK_9BIT, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(60, FACE_DOT_EYES_TEAR, mapLegacyMouth(5)),
                new org.trp.shincolle.entity.base.FaceStep(200, FACE_DOT_EYES_TEAR, mapLegacyMouth(2)),
                new org.trp.shincolle.entity.base.FaceStep(250, FACE_TENSION, mapLegacyMouth(0)),
                new org.trp.shincolle.entity.base.FaceStep(400, FACE_TENSION, mapLegacyMouth(4)),
                new org.trp.shincolle.entity.base.FaceStep(450, FACE_SOFT, mapLegacyMouth(0))
            }, FACE_SOFT, mapLegacyMouth(1)))
            .scorn(new org.trp.shincolle.entity.base.FaceTimeline(0, new org.trp.shincolle.entity.base.FaceStep[0], FACE_EYES_HALF, mapLegacyMouth(1)))
            .hungry(new org.trp.shincolle.entity.base.FaceTimeline(0, new org.trp.shincolle.entity.base.FaceStep[0], FACE_DESPAIR, mapLegacyMouth(2)))
            .angry(new org.trp.shincolle.entity.base.FaceTimeline(EMOTION_TICK_MASK_8BIT, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(64, FACE_EYES_CLOSED, mapLegacyMouth(0)),
                new org.trp.shincolle.entity.base.FaceStep(128, FACE_EYES_CLOSED, mapLegacyMouth(1)),
                new org.trp.shincolle.entity.base.FaceStep(170, FACE_EYES_HALF, mapLegacyMouth(1))
            }, FACE_EYES_HALF, mapLegacyMouth(2)))
            .bored(new org.trp.shincolle.entity.base.FaceTimeline(EMOTION_TICK_MASK_9BIT, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(80, FACE_DOT_EYES, mapLegacyMouth(0)),
                new org.trp.shincolle.entity.base.FaceStep(170, FACE_DOT_EYES, mapLegacyMouth(4)),
                new org.trp.shincolle.entity.base.FaceStep(340, FACE_WINK, mapLegacyMouth(0))
            }, FACE_EYES_OPEN, mapLegacyMouth(0)))
            .shy(new org.trp.shincolle.entity.base.FaceTimeline(EMOTION_TICK_MASK_8BIT, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(80, FACE_EYES_OPEN, mapLegacyMouth(3)),
                new org.trp.shincolle.entity.base.FaceStep(140, FACE_EYES_OPEN, mapLegacyMouth(2))
            }, FACE_WINK, mapLegacyMouth(0)))
            .happy(new org.trp.shincolle.entity.base.FaceTimeline(EMOTION_TICK_MASK_8BIT, new org.trp.shincolle.entity.base.FaceStep[] {
                new org.trp.shincolle.entity.base.FaceStep(80, FACE_TENSION, mapLegacyMouth(0)),
                new org.trp.shincolle.entity.base.FaceStep(140, FACE_TENSION, mapLegacyMouth(4))
            }, FACE_WINK, mapLegacyMouth(4)))
            .build();
    }

    public ItemStack createCustomSpawnEgg() {
        return CustomShipSpawnEggItem.createForShip(ModItems.CUSTOM_SHIP_SPAWN_EGG.get(), getCustomShipId());
    }

    @Override
    public java.util.List<EquipOption> getEquipOptions() {
        java.util.List<EquipOption> list = new java.util.ArrayList<>(super.getEquipOptions());
        String id = getCustomShipId();
        CustomShipConfig config = CustomShipRegistry.getConfig(id);
        if (config != null && config.appearance_parts != null) {
            for (CustomShipConfig.AppearancePartConfig part : config.appearance_parts) {
                if (part.key != null && !part.key.isEmpty()) {
                    list.add(new EquipOption(part.key, part.name != null && !part.name.isEmpty() ? part.name : part.key));
                }
            }
        }
        return list;
    }

    @Override
    public boolean getEquipFlag(String key) {
        net.minecraft.nbt.CompoundTag nbt = this.copyEquipFlagsTag();
        if (nbt != null && nbt.contains(key)) {
            return nbt.getBoolean(key);
        }
        return true;
    }
}
