package com.black_dog20.itemgrabber.utility;

import java.util.List;

import com.black_dog20.itemgrabber.Grabber;
import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.reference.NBTTags;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.Loader;
import vazkii.botania.common.block.subtile.functional.SubTileSolegnolia;

public class MagnetHelper {
	
	private static final List<ResourceLocation> blackListedItems = ImmutableList.of(new ResourceLocation("appliedenergistics2", "item.ItemCrystalSeed"));

	
	public static Predicate<EntityItem> floatingItemsToPickUp(EntityPlayer player){
		return ((x) -> {
			if(blackListedItems.contains(Item.REGISTRY.getNameForObject(x.getItem().getItem())))
				return false;
			NBTTagCompound nbt = x.getEntityData();

			if(Loader.isModLoaded("botania") && SubTileSolegnolia.hasSolegnoliaAround(x))
				return false;
			if(nbt.getBoolean("PreventRemoteMovement"))
				return false;
			if(nbt.hasKey(NBTTags.BLOCKED))
				return false;
			if(!canPickUp(nbt, player))
				return false;
			if(canTrack(nbt, player))
				return true;

			int range = Math.max(ModConfig.server.rangeT1, ModConfig.server.rangeT2);
			EntityPlayer nearest = findNearestEntityWithinAABB(x, 
					new AxisAlignedBB(x.posX - range, x.posY - range, x.posZ - range, x.posX + range, x.posY + range, x.posZ + range));

			if(nearest != null){
				if(nearest.getName().equals(player.getName()))
					return true;
				else
					return false;
			}
			return false;
		});
	}
	
	private static boolean canTrack(NBTTagCompound nbt, EntityPlayer player){
		if(nbt.hasKey(NBTTags.TRACKED_BY) && nbt.getString(NBTTags.TRACKED_BY).equals(player.getName()))
			return true;
		else 
			return false;
	}
	
	private static boolean canPickUp(NBTTagCompound nbt, EntityPlayer player){
		if (!nbt.hasKey(NBTTags.PICKUP_IN) ||  (nbt.hasKey(NBTTags.PICKUP_IN) && !nbt.getString(NBTTags.DROPPED_BY).equals(player.getName()))){
			return true;
		}else 
			return false;
	}
	
	
	public static Predicate<EntityPlayer> hasMagnetOnCheck(){
		return ((x) -> {
			IMagnetHandler mh = x.getCapability(MagnetHandler.CAP, null);
			return mh!= null && (hasMagnetInInventory(x) || mh.getHasBelt()) && mh.getHasMagnetOn();
		});
	}
	
	public static Predicate<EntityItem> hasPickUpInTag(){
		return ((x) -> {
			return x.getEntityData().hasKey(NBTTags.PICKUP_IN);
		});
	}
	
	public static int getRange(EntityPlayer player){
		int result = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT1)))
			result = getRange(1);

		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT2)))
			result = getRange(2);
		
		IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
		if(mh != null && mh.getHasBelt())
			result = getRange(mh.getTier());
		
		return result;
	}
	
	public static double getSpeed(EntityPlayer player){
		double result = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT1)))
			result = getSpeed(1);
		
		if(player.inventory.hasItemStack(new ItemStack(ModItems.magnetT2)))
			result = getSpeed(2);
		
		IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
		if(mh != null && mh.getHasBelt())
			result = getSpeed(mh.getTier());
		
		return result;
	}
	
	public static double getSpeed(int tier){
		switch (tier) {
		case 1:
			return Grabber.Proxy.getServerConfig().speedT1;
		case 2:
			return Grabber.Proxy.getServerConfig().speedT2;
			
		default:
			return 0;
		}
	}
	
	public static int getRange(int tier){
		switch (tier) {
		case 1:
			return Grabber.Proxy.getServerConfig().rangeT1;
		case 2:
			return Grabber.Proxy.getServerConfig().rangeT2;
			
		default:
			return 0;
		}
	}
	
	public static int getBeltTier(EntityPlayer player){
		int result = 0;
		InventoryPlayer ip = player.inventory;
		if(ip.hasItemStack(new ItemStack(ModItems.beltT1)))
			result = 1;
		if(ip.hasItemStack(new ItemStack(ModItems.beltT2)))
			result = 2;
		return result;
	}
	
	
	public static boolean hasMagnetInInventory(EntityPlayer player){
		boolean result = false;
		InventoryPlayer ip = player.inventory;
		result = ip.hasItemStack(new ItemStack(ModItems.magnetT1)) && !result ? true : result;
		result = ip.hasItemStack(new ItemStack(ModItems.magnetT2)) && !result ? true : result;
		return result;
	}
	
    public static EntityPlayer findNearestEntityWithinAABB(EntityItem item, AxisAlignedBB aabb)
    {
        List<EntityPlayer> list = item.getEntityWorld().getEntitiesWithinAABB(EntityPlayer.class, aabb);
        EntityPlayer result = null;
        double shortestDistance = Double.MAX_VALUE;

        for (EntityPlayer player : list)
        {
            if (hasMagnetOnCheck().apply(player) && canPickUp(item.getEntityData(),player))
            {
            	int range = getRange(player);
                double testDistance = item.getDistanceToEntity(player);

                if (testDistance <= shortestDistance && testDistance <= range)
                {
                    result = player;
                    shortestDistance = testDistance;
                }
            }
        }

        return result;
    }

}
