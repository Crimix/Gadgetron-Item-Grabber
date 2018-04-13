package com.black_dog20.itemgrabber.api;

import com.black_dog20.itemgrabber.utility.MagnetHelper;
import com.google.common.base.Predicate;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class API {

    /**
     * Adds a predicate that if true will prevent an EntityItem under specific conditions
     * from being grabbed by an Item grabber. 
     * 
     * Please do not just add a Predicate that always returns true, it will break the mod. 
     *
     * @param predicate The predicate that if true will stop the EntityItem for being grabbed by the Itemgrabber.
     */
	public static void addEntityItemHandler(Predicate<EntityItem> predicate) {
		MagnetHelper.addEntityItemHandler(predicate);
	}
	
    /**
     * Adds a predicate that if true will temporarily turn of a players magnet under specific conditions. 
     * 
     * Please do not just add a Predicate that always returns true, it will break the mod. 
     *
     * @param predicate The predicate that if true will temporarily turn of the EntityPlayer's itemgrabber.
     */
	public static void addEntityPlayerHandler(Predicate<EntityPlayer> predicate) {
		MagnetHelper.addEntityPlayerHandler(predicate);
	}
	
	   /**
     * Adds an item to the list of Blacklisted items such that the grabber will never try to grab the specific item. 
     *
     * @param item The ResourceLocation of that specific item.
     */
	public static void addBlacklistedItem(ResourceLocation item) {
		MagnetHelper.addBlackListedItem(item); 
	}

}
