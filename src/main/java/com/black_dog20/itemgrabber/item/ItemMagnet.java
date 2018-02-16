package com.black_dog20.itemgrabber.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.config.ModConfig;

public class ItemMagnet extends ItemBase{

	private int tier;
	
	public ItemMagnet(String name, int tier){
		super(name);
		this.tier = tier;
		
		this.addPropertyOverride(new ResourceLocation("state"), new IItemPropertyGetter() {
			
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				if(entityIn != null){
					IMagnetHandler mh = entityIn.getCapability(MagnetHandler.CAP, null);
					if(mh != null && !mh.getHasMagnetOn()){
						return 0;
					}
				}
				return -1;
			}
		});
	}
	
	public int getTier(){
		return tier;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add("Range: "+ ModConfig.getRange(tier)+" blocks");
		if(ModConfig.client.blockPerSec)
			tooltip.add("Speed: "+(ModConfig.getSpeed(tier)*20)+" blocks/sec");
		else
			tooltip.add("Speed: "+ModConfig.getSpeed(tier)+" blocks/tick");
		IMagnetHandler mh = playerIn.getCapability(MagnetHandler.CAP, null);
		if(mh != null){
			tooltip.add("Active: " + mh.getHasMagnetOn());
			tooltip.add("Sneak Mode: " + mh.getSneakDeactivate());
		}
	}	
}
