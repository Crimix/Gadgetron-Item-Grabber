package com.black_dog20.itemgrabber.handler;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.client.model.Belt;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.item.ItemMagnet;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageSyncMagnetCapabilityTracking;
import com.black_dog20.itemgrabber.reference.NBTTags;
import com.black_dog20.itemgrabber.utility.MagnetHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerEventHandler {
	
	@SubscribeEvent
	public void OnPlayerUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(player.world.isRemote) return;
			IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
			if (mh!= null && mh.getHasMagnetOn() && player.inventory.hasItemStack(new ItemStack(ModItems.magnetT1))) {
				int range = 5;
				double speed = 0.02;
				List<EntityItem> floatingItems = player.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, 
								new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range), MagnetHelper.NotCheckTags(player));
				int pulledItems = 0;
				if (!floatingItems.isEmpty()){
					for (EntityItem entityItem : floatingItems) {
						if(pulledItems > 200) break;
						entityItem.addVelocity((player.posX - entityItem.posX) * speed, (player.posY - entityItem.posY) * speed, (player.posZ - entityItem.posZ) * speed);
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
	
	@SubscribeEvent
	public void test(WorldTickEvent event){
		if(event.world.isRemote) return;
		for(EntityItem e : event.world.getEntities(EntityItem.class, MagnetHelper.CheckTags())){
			NBTTagCompound nbt = e.getEntityData();
			if(nbt.getInteger(NBTTags.PICKUP_IN) <= 0){
				nbt.removeTag(NBTTags.PICKUP_IN);
			}else{
				nbt.setInteger(NBTTags.PICKUP_IN, (nbt.getInteger(NBTTags.PICKUP_IN))-1);
			}
		}
	}
	
	@SubscribeEvent
	public void OnTossItem(ItemTossEvent event){
		if(event.getPlayer().world.isRemote) return;
		event.getEntityItem().getEntityData().setInteger(NBTTags.PICKUP_IN, 100);
		event.getEntityItem().getEntityData().setString(NBTTags.DROPPED_BY, event.getPlayer().getName());
	}
	
}