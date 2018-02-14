package com.black_dog20.itemgrabber.utility;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

public class MagnetHelper {
	
	private static final List<ResourceLocation> blackListedItems = ImmutableList.of(new ResourceLocation("appliedenergistics2", "item.ItemCrystalSeed"));

	
	public static Predicate<EntityItem> NotCheckTags(EntityPlayer player){
		return ((x) -> {
			if(blackListedItems.contains(Item.REGISTRY.getNameForObject(x.getItem().getItem())))
				return false;
			NBTTagCompound nbt = x.getEntityData();
			if(nbt.hasKey("trackBy") && nbt.getString("trackBy").equals(player.getName())){
				return true;
			}else if(nbt.hasKey("trackBy") && !nbt.getString("trackBy").equals(player.getName())){
				return false;
			}else {
				return !x.getEntityData().hasKey("countdown") || !x.getEntityData().getString("tossedBy").equals(player.getName());
			}
		});
	}
	
	public static Predicate<EntityItem> CheckTags(){
		return ((x) -> {
			return x.getEntityData().hasKey("countdown");
		});
	}

}
