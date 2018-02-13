package com.black_dog20.itemgrabber.capability;

import net.minecraft.entity.player.EntityPlayer;

public interface IBaseCapability<T> {

	void copyTo(T other);
}
