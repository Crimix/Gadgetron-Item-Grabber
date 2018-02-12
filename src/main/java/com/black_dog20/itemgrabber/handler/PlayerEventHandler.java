package com.black_dog20.itemgrabber.handler;

import java.util.List;

import com.black_dog20.itemgrabber.client.model.Belt;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.item.ItemMagnet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
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
			if (player.getHeldItemMainhand().getItem() instanceof ItemMagnet) {
				ItemMagnet magnet = (ItemMagnet) player.getHeldItemMainhand().getItem();
				int range = magnet.getRange();
				double speed = magnet.getSpeed();
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
	
	
	

}