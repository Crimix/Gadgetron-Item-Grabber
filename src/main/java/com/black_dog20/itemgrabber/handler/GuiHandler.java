package com.black_dog20.itemgrabber.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.black_dog20.itemgrabber.Grabber;


public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		/*TileEntity entity = world.getTileEntity(x, y, z);
		ItemStack item = player.getHeldItem();

		if (ID == vut.guiVehicleInventory) {
			return new ContainerVehicle(player, world, x, y, z, world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId")));
		}
		else if(ID == vut.guiVehicleUpgrade){
			return new ContainerVehicleUpgrade(player, world, x, y, z, world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId")));
			
		}*/
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		/*TileEntity entity = world.getTileEntity(x, y, z);

		if (ID == vut.guiVehicleInventory) {
			Entity vehicle = world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId"));
			if(vehicle !=null){
				return new GuiVehicle(player, world, x, y, z,vehicle );
			}
			else return null;
		}
		else if(ID == vut.guiVehicleUpgrade){
			Entity vehicle = world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId"));
			if(vehicle !=null){
				return new GuiVehicleUpgrade(player, world, x, y, z, vehicle);
			}
			else return null;
		}*/

		return null;
	}

}