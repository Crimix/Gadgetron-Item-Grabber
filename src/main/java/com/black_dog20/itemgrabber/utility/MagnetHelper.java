package com.black_dog20.itemgrabber.utility;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.api.API;
import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.itemgrabber.Grabber;
import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.reference.Constants;
import com.black_dog20.itemgrabber.reference.NBTTags;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class MagnetHelper {
	private static final List<ResourceLocation> blackListedItems = new ArrayList<ResourceLocation>();
	private static List<Predicate<EntityItem>> extrnalItemHandlers = new ArrayList<Predicate<EntityItem>>();
	private static List<Predicate<EntityPlayer>> extranlAntigrabbers = new ArrayList<Predicate<EntityPlayer>>();
	
	public static void addEntityItemHandler(Predicate<EntityItem> handler) {
		extrnalItemHandlers.add(handler);
	}
	
	public static void addEntityPlayerHandler(Predicate<EntityPlayer> handler) {
		extranlAntigrabbers.add(handler);
	}
	
	public static void addBlackListedItem(ResourceLocation item) {
		blackListedItems.add(item);
	}
	public static Predicate<EntityItem> floatingItemsToPickUp(EntityPlayer player){
		return ((x) -> {
			if(blackListedItems.contains(Item.REGISTRY.getNameForObject(x.getItem().getItem())))
				return false;
			NBTTagCompound nbt = x.getEntityData();

			for(Predicate<EntityItem> h : extrnalItemHandlers) {
				if(h.apply(x)) {
					return false;
				}
			}

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
	

	public static void checkIfMagnetShouldBeTempOff(EntityPlayer player) {
		IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
		NBTTagCompound nbt = player.getEntityData();
		for(Predicate<EntityPlayer> p : extranlAntigrabbers) {
			if(p.apply(player)) {
				nbt.setInteger(NBTTags.BLOCKED, Constants.PLAYER_BLOCKED_TIME);
				if(mh != null && !mh.getTempOff()) {
					mh.setTempOff(true);
				}
			}
		}
	}

	public static Predicate<EntityPlayer> hasMagnetOnCheck(){
		return ((x) -> {
			IBeltHandler mh = x.getCapability(BeltHandler.CAP, null);
			return mh!= null && hasMagnetInInventory(x) && mh.getHasMagnetOn();
		});
	}
	
	public static Predicate<EntityItem> hasPickUpInTag(){
		return ((x) -> {
			return x.getEntityData().hasKey(NBTTags.PICKUP_IN);
		});
	}
	
	public static int getRange(EntityPlayer player){
		int result = 0;
		if(API.isItemStackInInventory(player, new ItemStack(ModItems.magnetT1)))
			result = getRange(1);

		if(API.isItemStackInInventory(player, new ItemStack(ModItems.magnetT2)))
			result = getRange(2);
		
		return result;
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
	
	
	public static boolean hasMagnetInInventory(EntityPlayer player){
		boolean result = false;
		result = API.isItemStackInInventory(player, new ItemStack(ModItems.magnetT1)) && !result ? true : result;
		result = API.isItemStackInInventory(player, new ItemStack(ModItems.magnetT2)) && !result ? true : result;
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
    
    public static void setEntityMotion(Entity entity, double x, double y, double z, float speedModifier) {
    	Vec3d entityVec = new Vec3d(entity.posX, entity.posY- entity.getYOffset() + entity.height / 2, entity.posZ);
    	Vec3d finalVec = new Vec3d(x-entityVec.x, y-entityVec.y, z-entityVec.z);
    	
    	if(finalVec.lengthVector() > 1)
    		finalVec = finalVec.normalize();
    	
    	entity.motionX = finalVec.x * speedModifier;
    	entity.motionY = finalVec.y * speedModifier;
    	entity.motionZ = finalVec.z * speedModifier;
    }
    
    

}
