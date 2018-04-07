package com.black_dog20.itemgrabber.handler;

import java.util.List;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageConfigSync;
import com.black_dog20.itemgrabber.reference.NBTTags;
import com.black_dog20.itemgrabber.utility.MagnetHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void onMagnetUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(player.world.isRemote) return;
			IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
			int range = 0;
			double speed = 0;
			if (mh!= null && mh.getHasMagnetOn() && !(mh.getSneakDeactivate() && player.isSneaking()) && !mh.getTempOff()) {	
				range = MagnetHelper.getRange(player);
				speed = MagnetHelper.getSpeed(player);
				if(range != 0){
					List<EntityItem> floatingItems = player.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, 
									new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range), MagnetHelper.floatingItemsToPickUp(player));
					int pulledItems = 0;
					if (!floatingItems.isEmpty()){
						for (EntityItem entityItem : floatingItems) {
							if(pulledItems > 200) break;
							entityItem.addVelocity((player.posX - entityItem.posX) * speed, ((player.posY+(player.getEyeHeight()/3)) - entityItem.posY) * speed, (player.posZ - entityItem.posZ) * speed);
							entityItem.getEntityData().setString(NBTTags.TRACKED_BY, player.getName());
							pulledItems++;
						}
					}
					List<EntityXPOrb> floatingXP = player.getEntityWorld().getEntitiesWithinAABB(EntityXPOrb.class, 
							new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range));
					if (!floatingXP.isEmpty()){
						for (EntityXPOrb entityXP : floatingXP) {
							entityXP.addVelocity((player.posX - entityXP.posX) * speed, (player.posY - entityXP.posY) * speed, (player.posZ - entityXP.posZ) * speed);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event){
		if(!event.player.world.isRemote){
			PacketHandler.network.sendTo(new MessageConfigSync(), (EntityPlayerMP) event.player);
		}
	}
	
	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(player.world.isRemote) return;
			NBTTagCompound nbt = player.getEntityData();
			
			if(nbt.getInteger(NBTTags.BLOCKED) <= 0){
				nbt.removeTag(NBTTags.BLOCKED);
				IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
				if(mh != null) {
					mh.setTempOff(false);
				}
			}else{
				nbt.setInteger(NBTTags.BLOCKED, (nbt.getInteger(NBTTags.BLOCKED))-1);
			}
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
	
	@SubscribeEvent
	public void onEntityAdded(EntityJoinWorldEvent event) {
		if(event.getEntity().world.isRemote) return;
		Entity entity = event.getEntity();
		if (entity instanceof EntityItem) {
			EntityItem entityItem = (EntityItem) entity;
			if(entityItem.getThrower() != null && !entityItem.getThrower().equals("")) {
				entityItem.getEntityData().setInteger(NBTTags.PICKUP_IN, 100);
				entityItem.getEntityData().setString(NBTTags.DROPPED_BY, entityItem.getThrower());
			}
		}
	}

	
	@SubscribeEvent
	public void onTossItem(ItemTossEvent event){
		if(event.getPlayer().world.isRemote ) return;
		event.getEntityItem().getEntityData().setInteger(NBTTags.PICKUP_IN, 100);
		event.getEntityItem().getEntityData().setString(NBTTags.DROPPED_BY, event.getPlayer().getName());
	}
	
}