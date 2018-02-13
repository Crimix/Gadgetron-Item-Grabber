package com.black_dog20.itemgrabber.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MagnetHandler implements IMagnetHandler, ICapabilitySerializable<NBTTagCompound> {

	@CapabilityInject(IMagnetHandler.class) 
	public static final Capability<IMagnetHandler> CAP = null;
	private boolean magnetOn = false;
	private boolean belt = false;
	private int tier = 0;
	
	
	@Override
	public void setHasMagnetOn(boolean hasMagnetOn) {
		magnetOn = hasMagnetOn;
	}

	@Override
	public boolean getHasMagnetOn() {
		return magnetOn;
	}

	@Override
	public void setHasBelt(boolean hasBelt) {
		belt = hasBelt;
	}

	@Override
	public boolean getHasBelt() {
		return belt;
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
	}

	@Override
	public int getTier() {
		return tier;
	}

	@Override
	public void copyTo(IMagnetHandler other) {
		other.setHasBelt(belt);
		other.setHasMagnetOn(magnetOn);
		other.setTier(tier);
	}
	
	public static IMagnetHandler instanceFor(EntityPlayer player) { 
		return player.getCapability(CAP, null); 
	}

	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CAP ? CAP.<T> cast(this) : null;

	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CAP.getStorage().writeNBT(CAP, this, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		CAP.getStorage().readNBT(CAP, this, null, nbt);
	}

}
