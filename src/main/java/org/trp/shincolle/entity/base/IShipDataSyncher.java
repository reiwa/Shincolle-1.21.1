package org.trp.shincolle.entity.base;

import net.minecraft.network.syncher.EntityDataAccessor;

public interface IShipDataSyncher {
    <T> T getData(EntityDataAccessor<T> key);
    <T> void setData(EntityDataAccessor<T> key, T value);
}
