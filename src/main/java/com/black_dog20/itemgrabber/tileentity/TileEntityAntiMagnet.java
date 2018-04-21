package com.black_dog20.itemgrabber.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.reference.AntiType;
import com.black_dog20.itemgrabber.reference.Constants;
import com.black_dog20.itemgrabber.reference.NBTTags;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityAntiMagnet extends TileEntity implements ITickable{

    private int[] ranges = {2,3,4};
	private AntiType type;
	private int rangeMarker = 0;

	public TileEntityAntiMagnet(AntiType type) {
		this.type = type;
	}
	
	public void incrementMarker() {
		rangeMarker++;
		if(rangeMarker == (ranges.length)) {
			rangeMarker = 0;
		}
	}
	
	public String getRangeString() {
		int xz = (((ranges[rangeMarker])*2)+1);
		int y = (((ranges[rangeMarker])*2)+1);
		switch (type) {
		case ALL:
			y = (((ranges[rangeMarker])*2)+1);
			break;
			
		case UP:
		case DOWN:
			y = ((ranges[rangeMarker])+1);
			break;
		}
		return xz+"x"+y+"x"+xz;
	}

	@Override
	public void update() {
		if(this.world.isRemote) return;
		List<EntityItem> floatingItems = new ArrayList<EntityItem>();
		List<EntityPlayer> players = new ArrayList<EntityPlayer>();
		int range = ranges[rangeMarker];
		range++;
		if(type == AntiType.UP) {
			floatingItems = this.world.getEntitiesWithinAABB(EntityItem.class, 
					new AxisAlignedBB(pos.getX() - range, pos.getY(), pos.getZ() - range, pos.getX()+ range, pos.getY() + range, pos.getZ() + range));
		
			players = this.world.getEntitiesWithinAABB(EntityPlayer.class, 
					new AxisAlignedBB(pos.getX() - range, pos.getY(), pos.getZ() - range, pos.getX()+ range, pos.getY() + range, pos.getZ() + range));
		
		}
		if(type == AntiType.DOWN) {
			floatingItems = this.world.getEntitiesWithinAABB(EntityItem.class, 
					new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX()+ range, pos.getY(), pos.getZ() + range));
			
			players = this.world.getEntitiesWithinAABB(EntityPlayer.class, 
					new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX()+ range, pos.getY(), pos.getZ() + range));

		}
		if(type == AntiType.ALL) {
			floatingItems = this.world.getEntitiesWithinAABB(EntityItem.class, 
					new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX()+ range, pos.getY() + range, pos.getZ() + range));
			
			players = this.world.getEntitiesWithinAABB(EntityPlayer.class, 
					new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX()+ range, pos.getY() + range, pos.getZ() + range));

		}
		
		for(EntityItem item : floatingItems) {
			item.getEntityData().setInteger(NBTTags.BLOCKED, 100);
		}
		
		for(EntityPlayer player : players) {
			IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
			if(mh != null && !mh.getTempOff()) {
				mh.setTempOff(true);
			}
			player.getEntityData().setInteger(NBTTags.BLOCKED, Constants.PLAYER_BLOCKED_TIME);
		}

	}

}
