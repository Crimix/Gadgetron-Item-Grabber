package com.black_dog20.itemgrabber.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.itemgrabber.reference.Constants;
import com.black_dog20.itemgrabber.reference.NBTTags;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.ITickable;
import net.minecraft.world.chunk.Chunk;

public class TileEntityAntiMagnetChunk extends TileEntity implements ITickable{

	public TileEntityAntiMagnetChunk() {
		super();
	}

	@Override
	public void update() {
		if(this.world.isRemote) return;
		List<EntityItem> floatingItems = new ArrayList<EntityItem>();
		List<EntityPlayer> players = new ArrayList<EntityPlayer>();

		Chunk c = this.world.getChunkFromBlockCoords(this.pos);
		ClassInheritanceMultiMap<Entity>[] map = c.getEntityLists();
		for(ClassInheritanceMultiMap<Entity> o : map) {
			if(!o.isEmpty())
				for(Entity e : o) {
					if(e.posY <= (this.pos.getY()-1)+8 && e.posY >= (this.pos.getY()-1)-8) {
						if(e instanceof EntityItem)
							floatingItems.add((EntityItem) e);
						if(e instanceof EntityPlayer)
							players.add((EntityPlayer) e);
					}
				}
		}
			
		
		
		
		for(EntityItem item : floatingItems) {
			item.getEntityData().setInteger(NBTTags.BLOCKED, 100);
		}
		
		for(EntityPlayer player : players) {
			IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
			if(mh != null && !mh.getTempOff()) {
				mh.setTempOff(true);
			}
			player.getEntityData().setInteger(NBTTags.BLOCKED, Constants.PLAYER_BLOCKED_TIME);
		}

	}

}
