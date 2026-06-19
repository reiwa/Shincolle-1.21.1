package org.trp.shincolle.utility;

import net.minecraft.world.effect.MobEffects;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;

public class CombatHelper {


    private CombatHelper() {}

    public static float calcMissRate(EntityShipBase host, float distance) {
        float miss;
        float range = host.getLegacyShipStats().getAttackRange();
        float levelMod = 0.001F * host.getLevel();
        if (range <= 3.0F) {
            miss = 0.25F - levelMod;
        } else if (range <= 6.0F) {
            miss = 0.25F + 0.15F * (distance / range) - levelMod;
        } else {
            miss = 0.25F + 0.25F * (distance / range) - levelMod;
        }
        miss -= host.getLegacyShipStats().getBuffedAttr(12);
        miss = Math.max(0.0F, Math.min(miss, 0.5F));
        if (host.hasEffect(MobEffects.BLINDNESS)) {
            miss += 0.4F;
        }
        return miss;
    }

    public static float applyCombatRateToDamage(EntityShipBase host, boolean canMultiHit, float distance, float rawAtk) {
        if (host == null) {
            return rawAtk;
        }
        float miss = calcMissRate(host, distance);
        float cri = miss + host.getLegacyShipStats().getBuffedAttr(9);
        float dhit = cri + host.getLegacyShipStats().getBuffedAttr(10);
        float thit = dhit + host.getLegacyShipStats().getBuffedAttr(11);
        float roll = host.getRandom().nextFloat();

        if (roll <= miss) {
            host.spawnCombatTextParticle(EntityShipBase.COMBAT_TEXT_MISS);
            return 0.0F;
        }
        if (roll <= cri) {
            host.spawnCombatTextParticle(EntityShipBase.COMBAT_TEXT_CRITICAL);
            return rawAtk * 1.5F;
        }
        if (canMultiHit && roll <= dhit) {
            host.spawnCombatTextParticle(EntityShipBase.COMBAT_TEXT_DOUBLE_HIT);
            return rawAtk * 2.0F;
        }
        if (canMultiHit && roll <= thit) {
            host.spawnCombatTextParticle(EntityShipBase.COMBAT_TEXT_TRIPLE_HIT);
            return rawAtk * 3.0F;
        }
        return rawAtk;
    }

    public static EntityAbyssMissile.MoveType calcMissileMoveType(EntityShipBase host, double targetY) {
        double depth = host.getShipDepth();
        if (depth > 2.0D) {
            return EntityAbyssMissile.MoveType.DIRECT;
        } else if (depth > 0.0D) {
            return (targetY <= host.getY() || targetY - host.getY() < depth)
                    ? EntityAbyssMissile.MoveType.TORPEDO
                    : EntityAbyssMissile.MoveType.ARC;
        } else {
            return EntityAbyssMissile.MoveType.ARC;
        }
    }
}

