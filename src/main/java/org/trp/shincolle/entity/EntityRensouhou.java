package org.trp.shincolle.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import org.trp.shincolle.entity.base.EntityShincolleSimpleMob;

public class EntityRensouhou extends EntityShincolleSimpleMob {

    public EntityRensouhou(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }
}
