package com.black_dog20.itemgrabber.handler;

import java.util.List;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.client.model.Belt;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.item.ItemMagnet;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageSyncMagnetCapabilityTracking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
								new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range));
				if (floatingItems.isEmpty())
					return;
				for (EntityItem entityItem : floatingItems) {
					entityItem.addVelocity((player.posX - entityItem.posX) * speed, (player.posY - entityItem.posY) * speed, (player.posZ - entityItem.posZ) * speed);
				}
			}
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
					EntityTracker et = ((WorldServer) player.world).getEntityTracker();
					et.sendToTracking(player, PacketHandler.network.getPacketFrom(new MessageSyncMagnetCapabilityTracking(mh.getHasMagnetOn(), mh.getHasBelt(), mh.getTier(), player)));
				}
			}
		}
	}
	
}