package com.black_dog20.itemgrabber.handler;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

	@SubscribeEvent 
	public void addPlayerCapabilities(AttachCapabilitiesEvent<Entity> entity) {
		if(entity.getObject() instanceof EntityPlayer){
			entity.addCapability(new ResourceLocation(Reference.MOD_ID, "MagnetHandler"), new MagnetHandler());
		}
	}

	@SubscribeEvent 
	public void persistPlayerCapabilities(PlayerEvent.Clone e) {
		if(e.isWasDeath()) {
			IMagnetHandler newCap = e.getEntityPlayer().getCapability(MagnetHandler.CAP, null);
			IMagnetHandler oldCap = e.getOriginal().getCapability(MagnetHandler.CAP, null);

			if(oldCap != null)
				oldCap.copyTo(newCap);
		}
	}
}
