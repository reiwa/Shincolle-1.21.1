package org.trp.shincolle.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.init.ModItems;

import java.util.List;

public class EntityIsolatedHime extends EntityShipBase {

    public static final String EQUIP_HAT_BASE = "equip_hat_base";
    public static final String EQUIP_HEAD_GEAR = "equip_head_gear";
    public static final String EQUIP_CLOTH_1 = "equip_cloth_1";
    public static final String EQUIP_CLOTH_2 = "equip_cloth_2";
    public static final String EQUIP_CLOTH_3 = "equip_cloth_3";
    public static final String EQUIP_LEG_OUTER = "equip_leg_outer";
    public static final String EQUIP_LEG_ARMOR = "equip_leg_armor";
    public static final String EQUIP_ROAD = "equip_road";

    public EntityIsolatedHime(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        setModelPos(new float[]{-6, 30, 0, 40});
        setStateMinor(STATE_MINOR_FACTION_ID, 10);
        setStateMinor(STATE_MINOR_SHIP_CLASS, 29);
        setStateMinor(STATE_MINOR_SPECIAL_EQUIP, 2);
        setStateMinor(STATE_MINOR_RARITY, 8);
        setStateCanRide(true);
        setEquipFlag(EQUIP_HAT_BASE, true);
        setEquipFlag(EQUIP_HEAD_GEAR, true);
        setEquipFlag(EQUIP_CLOTH_1, true);
        setEquipFlag(EQUIP_CLOTH_2, true);
        setEquipFlag(EQUIP_CLOTH_3, true);
        setEquipFlag(EQUIP_LEG_OUTER, true);
        setEquipFlag(EQUIP_LEG_ARMOR, true);
        setEquipFlag(EQUIP_ROAD, true);
    }

    @Override
    protected boolean performHeavyAttack(Entity target) {
        if (target == null || this.level().isClientSide) {
            return false;
        }
        if (!consumeHeavyAmmo(1)) {
            return false;
        }

        this.setAttackTick(50);
        this.applyEmotesReaction(3);
        spawnMissile(target);
        return true;
    }

    private void spawnMissile(Entity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float damage = Math.max(5.0F, baseDamage * 0.45F);
        float speed = 0.75f;
        int life = 200;
        float explosionRadius = 4.0f;
        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;
        float[] pos = rotateXZByAxis(0.1f, 0.0f, yawRad, 1.0f);
        EntityAbyssMissile missile = new EntityAbyssMissile(serverLevel, this, target, damage, speed, life, explosionRadius);
        missile.setPos(this.getX() + pos[1], this.getY() + this.getBbHeight() * 0.65D, this.getZ() + pos[0]);
        serverLevel.addFreshEntity(missile);
    }

    @Override
    public List<EquipOption> getEquipOptions() {
        return List.of(
                new EquipOption(EQUIP_HAT_BASE, "gui.shincolle.equip.head_base"),
                new EquipOption(EQUIP_HEAD_GEAR, "gui.shincolle.equip.head"),
                new EquipOption(EQUIP_CLOTH_1, "gui.shincolle.equip.upper"),
                new EquipOption(EQUIP_CLOTH_2, "gui.shincolle.equip.cloak"),
                new EquipOption(EQUIP_CLOTH_3, "gui.shincolle.equip.upper"),
                new EquipOption(EQUIP_LEG_OUTER, "gui.shincolle.equip.leg"),
                new EquipOption(EQUIP_LEG_ARMOR, "gui.shincolle.equip.leg"),
                new EquipOption(EQUIP_ROAD, "gui.shincolle.equip.rigging")
        );
    }

    @Override
    protected Item getShipSpawnEggItem() {
        return ModItems.ISOLATED_HIME_SPAWN_EGG.get();
    }
}

