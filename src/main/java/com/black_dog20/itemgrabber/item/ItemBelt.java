package com.black_dog20.itemgrabber.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemBelt extends ItemBase{
	
	public ItemBelt(){
		super("ItemBelt");
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		if(stack.getTagCompound()!= null && stack.getTagCompound().hasKey("countdown"))
			tooltip.add("Countdown: " + stack.getTagCompound().getInteger("countdown"));
	}
}
