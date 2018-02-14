package com.black_dog20.itemgrabber.item;

import java.util.List;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemMagnet extends ItemBase{

	private int range;
	private double speed;
	
	public ItemMagnet(String name, int range,double speed){
		super(name);
		this.range = range;
		this.speed = speed;
	}
	
	public int getRange(){
		return range;
	}
	
	
	public double getSpeed(){
		return speed;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add("Range: "+range);
		tooltip.add("Speed: "+speed+" b/s");
		IMagnetHandler mh = playerIn.getCapability(MagnetHandler.CAP, null);
		if(mh != null)
			tooltip.add("Active: " + mh.getHasMagnetOn());
	}	
}
