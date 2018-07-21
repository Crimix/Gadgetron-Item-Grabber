package com.black_dog20.itemgrabber.tileentity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.itemgrabber.reference.AntiType;
import com.black_dog20.itemgrabber.reference.Constants;
import com.black_dog20.itemgrabber.reference.NBTTags;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityAntiMagnet extends TileEntity implements ITickable{

    private int[] ranges = {2,3,4};
	private AntiType type;
	private int rangeMarker = 0;

	
	public TileEntityAntiMagnet() {
		super();
	}
	
	public TileEntityAntiMagnet(AntiType type) {
		super();
		this.type = type;
	}
	
	public void incrementMarker() {
		rangeMarker++;
		if(rangeMarker == (ranges.length)) {
			rangeMarker = 0;
		}
		sendUpdates();
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
			IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
			if(mh != null && !mh.getTempOff()) {
				mh.setTempOff(true);
			}
			player.getEntityData().setInteger(NBTTags.BLOCKED, Constants.PLAYER_BLOCKED_TIME);
		}

	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		rangeMarker = nbt.getInteger("rangeMarker");
		this.type = AntiType.valueOf(nbt.getString("antiType"));
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("rangeMarker", rangeMarker);
		nbt.setString("antiType", type.name());
		return super.writeToNBT(nbt);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound data = new NBTTagCompound();
		return writeToNBT(data);
	}
	
    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound data = new NBTTagCompound();
        writeToNBT(data);
        return new SPacketUpdateTileEntity(this.pos, 1, data);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity s35PacketUpdateTileEntity) {
        readFromNBT(s35PacketUpdateTileEntity.getNbtCompound());
        world.markBlockRangeForRenderUpdate(this.pos, this.pos);
        this.world.notifyBlockUpdate(this.pos, world.getBlockState(this.pos), world.getBlockState(this.pos), 3);
    }
    
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}

}