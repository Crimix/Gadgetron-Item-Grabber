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

	private AntiType type;
	private int range;

	public TileEntityAntiMagnet(AntiType type, int range) {
		this.type = type;
		this.range = range;
	}

	@Override
	public void update() {
		if(this.world.isRemote) return;
		List<EntityItem> floatingItems = new ArrayList<EntityItem>();
		List<EntityPlayer> players = new ArrayList<EntityPlayer>();

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
