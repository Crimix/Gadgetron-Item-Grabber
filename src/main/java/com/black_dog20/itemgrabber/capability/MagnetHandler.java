package com.black_dog20.itemgrabber.capability;

import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageSyncMagnetCapability;
import com.black_dog20.itemgrabber.network.message.MessageSyncMagnetCapabilityTracking;

import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
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
	private boolean dirty = false;
	
	
	@Override
	public void setHasMagnetOn(boolean hasMagnetOn) {
		magnetOn = hasMagnetOn;
		dirty = true;
	}

	@Override
	public boolean getHasMagnetOn() {
		return magnetOn;
	}

	@Override
	public void setHasBelt(boolean hasBelt) {
		belt = hasBelt;
		dirty = true;
	}

	@Override
	public boolean getHasBelt() {
		return belt;
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
		dirty = true;
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
	
	@Override
	public void updateClient(EntityPlayer player){
		if(!player.world.isRemote && dirty){
			PacketHandler.network.sendTo(new MessageSyncMagnetCapability(this), (EntityPlayerMP) player);
			((WorldServer) player.world).getEntityTracker().sendToTracking(player, PacketHandler.network.getPacketFrom(new MessageSyncMagnetCapabilityTracking(this, player)));
			dirty = false;
		}
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
