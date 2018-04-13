package com.black_dog20.itemgrabber.handler;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageSyncMagnetCapabilityTracking;
import com.black_dog20.itemgrabber.network.message.MessageUpdateSneakState;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
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
			IMagnetHandler newCap = e.getEntityPlayer().getCapability(MagnetHandler.CAP, null);
			IMagnetHandler oldCap = e.getOriginal().getCapability(MagnetHandler.CAP, null);

			if(oldCap != null)
				oldCap.copyTo(newCap);
	}
	
	@SubscribeEvent 
	public void OnPlayerStartTrackingPlayer(PlayerEvent.StartTracking event){
		if(event.getTarget().world.isRemote) return;
		if(event.getTarget() instanceof EntityPlayer){
			EntityPlayer trackedPlayer = (EntityPlayer) event.getTarget();
			EntityPlayer trackingPlayer = event.getEntityPlayer();
			PacketHandler.network.sendTo(new MessageSyncMagnetCapabilityTracking(MagnetHandler.instanceFor(trackedPlayer),trackedPlayer), (EntityPlayerMP) trackingPlayer);
		}
	}
	
	@SubscribeEvent
	public void OnPlayerCapabilityUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(!player.world.isRemote) {
				IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
				if(mh != null){
					mh.updateClient(player);
				}
			}
			if(player.world.isRemote){
				IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
				if(mh != null){
					if( mh.getSneakDeactivate() != ModConfig.client.sneak){
						PacketHandler.network.sendToServer(new MessageUpdateSneakState(ModConfig.client.sneak));
					}
				}
				
			}
		}
	}
}
