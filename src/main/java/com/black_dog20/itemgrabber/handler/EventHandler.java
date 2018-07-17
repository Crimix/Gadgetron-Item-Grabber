package com.black_dog20.itemgrabber.handler;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageConfigSync;
import com.black_dog20.itemgrabber.network.message.MessageUpdateSneakState;
import com.black_dog20.itemgrabber.reference.NBTTags;
import com.black_dog20.itemgrabber.utility.MagnetHelper;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event){
		if(!event.player.world.isRemote){
			PacketHandler.network.sendTo(new MessageConfigSync(), (EntityPlayerMP) event.player);
		}
	}
	
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event){
		if(!event.getEntity().world.isRemote){
			NBTTagCompound nbt = event.getEntity().getEntityData();
			nbt.removeTag(NBTTags.PICKUP_IN);
			nbt.removeTag(NBTTags.DROPPED_BY);
			nbt.removeTag(NBTTags.TRACKED_BY);
			nbt.removeTag(NBTTags.BLOCKED);
		}
	}
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event){
		if(event.world.isRemote) return;
		for(EntityItem e : event.world.getEntities(EntityItem.class, MagnetHelper.hasPickUpInTag())){
			NBTTagCompound nbt = e.getEntityData();
			if(nbt.getInteger(NBTTags.PICKUP_IN) <= 0){
				nbt.removeTag(NBTTags.PICKUP_IN);
			}else{
				nbt.setInteger(NBTTags.PICKUP_IN, (nbt.getInteger(NBTTags.PICKUP_IN))-1);
			}
			
			if(nbt.getInteger(NBTTags.BLOCKED) <= 0){
				nbt.removeTag(NBTTags.BLOCKED);
			}else{
				nbt.setInteger(NBTTags.BLOCKED, (nbt.getInteger(NBTTags.BLOCKED))-1);
			}
		}
	}
	
	/*@SubscribeEvent
	public void onEntityAdded(EntityJoinWorldEvent event) {
		if(event.getEntity().world.isRemote) return;
		Entity entity = event.getEntity();
		if (entity instanceof EntityItem) {
			EntityItem entityItem = (EntityItem) entity;
			if(entityItem.getThrower() != null && !entityItem.getThrower().equals("")) {
				entityItem.getEntityData().setInteger(NBTTags.PICKUP_IN, Constants.PICK_UP_DELAY);
				entityItem.getEntityData().setString(NBTTags.DROPPED_BY, entityItem.getThrower());
			}
		}
	}*/
	
	@SubscribeEvent
	public void OnPlayerCapabilityUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(player.world.isRemote){
				IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
				if(mh != null){
					if( mh.getSneakDeactivate() != ModConfig.client.sneak){
						PacketHandler.network.sendToServer(new MessageUpdateSneakState(ModConfig.client.sneak));
					}
				}
				
			}
		}
	}
	
}