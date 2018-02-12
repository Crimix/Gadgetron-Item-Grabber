package com.black_dog20.itemgrabber.handler;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;


import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.reference.NBTTags;


public class EventHandler {

	@SubscribeEvent
	public void Tool(ItemTooltipEvent event) {

	}
	
	
	
	/*@SubscribeEvent
	public void PlayerJoin(PlayerLoggedInEvent event){
		System.out.println("hey");
		Field inventories = ReflectionHelper.findField(InventoryPlayer.class, "allInventories","field_184440_g");
		inventories.setAccessible(true);
		Object value = null;
		try {
			value = inventories.get(event.player.inventory);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(value != null){
		List<NonNullList<ItemStack>> test = (List<NonNullList<ItemStack>>)value;
		List<NonNullList<ItemStack>> newList = new ArrayList<NonNullList<ItemStack>>();
		for(NonNullList<ItemStack> o : test)
			newList.add(o);
		newList.add(NonNullList.<ItemStack>withSize(4, new ItemStack(Items.ACACIA_BOAT,1)));
		try {
			inventories.set(event.player.inventory, newList);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}*/
	
}