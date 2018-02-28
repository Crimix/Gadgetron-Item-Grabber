package com.black_dog20.itemgrabber.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
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
		TextComponentTranslation range = new TextComponentTranslation("tooltip.gadgetronig:range");
		TextComponentTranslation speed = new TextComponentTranslation("tooltip.gadgetronig:speed");
		TextComponentTranslation blocks = new TextComponentTranslation("tooltip.gadgetronig:blocks");
		blocks.getStyle().setColor(TextFormatting.WHITE);
		TextComponentTranslation sec = new TextComponentTranslation("tooltip.gadgetronig:sec");
		sec.getStyle().setColor(TextFormatting.WHITE);
		TextComponentTranslation tick = new TextComponentTranslation("tooltip.gadgetronig:tick");
		tick.getStyle().setColor(TextFormatting.WHITE);
		TextComponentTranslation active = new TextComponentTranslation("tooltip.gadgetronig:active");
		TextComponentTranslation sneak = new TextComponentTranslation("tooltip.gadgetronig:sneak");
		TextComponentTranslation on = new TextComponentTranslation("tooltip.gadgetronig:on");
		on.getStyle().setColor(TextFormatting.GREEN);
		TextComponentTranslation off = new TextComponentTranslation("tooltip.gadgetronig:off");
		off.getStyle().setColor(TextFormatting.RED);
		
		IMagnetHandler mh = playerIn.getCapability(MagnetHandler.CAP, null);
		if(mh != null){
			TextComponentTranslation activeState = mh.getHasMagnetOn() ? on : off;
			TextComponentTranslation sneakState = mh.getSneakDeactivate() ? on : off;
			tooltip.add(active.getFormattedText() + ": " + activeState.getFormattedText());
			tooltip.add(sneak.getFormattedText() + ": " + sneakState.getFormattedText());
		}
		tooltip.add(range.getFormattedText() + ": " + ModConfig.getRange(tier) + " " + blocks.getFormattedText());
		if(ModConfig.client.blockPerSec)
			tooltip.add(speed.getFormattedText() + ": "+ (ModConfig.getSpeed(tier)*20) + " " + blocks.getFormattedText() + "/" + sec.getFormattedText());
		else
			tooltip.add(speed.getFormattedText() + ": " + ModConfig.getSpeed(tier) + " " + blocks.getFormattedText() + "/" + tick.getFormattedText());
		

	}	
}
