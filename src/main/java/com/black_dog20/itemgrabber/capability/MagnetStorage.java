package com.black_dog20.itemgrabber.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MagnetStorage implements IStorage<IMagnetHandler>{

	public NBTTagCompound writeNBT(Capability<IMagnetHandler> capability, IMagnetHandler instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("magnetOn", instance.getHasMagnetOn());
		nbt.setBoolean("belt", instance.getHasBelt());
		nbt.setInteger("tier", instance.getTier());
		nbt.setBoolean("sneakDeactivate", instance.getSneakDeactivate());
		nbt.setBoolean("tempOff", instance.getTempOff());
		return nbt;
	}

	public void readNBT(Capability<IMagnetHandler> capability, IMagnetHandler instance, EnumFacing side, NBTBase nbt) {
		instance.setHasMagnetOn(((NBTTagCompound) nbt).getBoolean("magnetOn"));
		instance.setHasBelt(((NBTTagCompound) nbt).getBoolean("belt"));
		instance.setTier(((NBTTagCompound) nbt).getInteger("tier"));
		instance.setSneakDeactivate(((NBTTagCompound) nbt).getBoolean("sneakDeactivate"));
		instance.setTempOff(((NBTTagCompound) nbt).getBoolean("tempOff"));
	}

}
