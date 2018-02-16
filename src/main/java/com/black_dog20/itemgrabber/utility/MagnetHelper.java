package com.black_dog20.itemgrabber.utility;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.item.ItemMagnet;
import com.black_dog20.itemgrabber.reference.NBTTags;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

public class MagnetHelper {
	
	private static final List<ResourceLocation> blackListedItems = ImmutableList.of(new ResourceLocation("appliedenergistics2", "item.ItemCrystalSeed"));

	
	public static Predicate<EntityItem> NotCheckTags(EntityPlayer player){
		return ((x) -> {
			if(blackListedItems.contains(Item.REGISTRY.getNameForObject(x.getItem().getItem())))
				return false;
			NBTTagCompound nbt = x.getEntityData();
			if(nbt.hasKey(NBTTags.TRACKED_BY) && nbt.getString(NBTTags.TRACKED_BY).equals(player.getName())){
				return true;
			}else if(nbt.hasKey(NBTTags.TRACKED_BY) && !nbt.getString(NBTTags.TRACKED_BY).equals(player.getName())){
				return false;
			}else {
				return !x.getEntityData().hasKey(NBTTags.PICKUP_IN) || !x.getEntityData().getString(NBTTags.DROPPED_BY).equals(player.getName());
			}
		});
	}
	
	public static Predicate<EntityItem> CheckTags(){
		return ((x) -> {
			return x.getEntityData().hasKey(NBTTags.PICKUP_IN);
		});
	}
	
	public static int getRange(EntityPlayer player){
		int result = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT1))){
			result = ModConfig.getRange(((ItemMagnet)ModItems.magnetT1).getTier());
		}
		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT2))){
			result = ModConfig.getRange(((ItemMagnet)ModItems.magnetT2).getTier());
		}
		return result;
	}
	
	public static double getSpeed(EntityPlayer player){
		double result = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT1))){
			result = ModConfig.getSpeed(((ItemMagnet)ModItems.magnetT1).getTier());
		}
		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT2))){
			result = ModConfig.getSpeed(((ItemMagnet)ModItems.magnetT2).getTier());
		}
		return result;
	}
	
	public static boolean hasMagnetInInventory(EntityPlayer player){
		boolean result = false;
		InventoryPlayer ip = player.inventory;
		result = ip.hasItemStack(new ItemStack(ModItems.magnetT1)) && !result ? true : result;
		result = ip.hasItemStack(new ItemStack(ModItems.magnetT2)) && !result ? true : result;
		return result;
	}

}
