package com.black_dog20.itemgrabber.item;

import java.util.List;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageUpdateMagnetState;
import com.black_dog20.itemgrabber.utility.MagnetHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemBelt extends ItemBase{
	
	private int tier;
	
	public ItemBelt(String name, int tier){
		super(name);
		this.tier = tier;
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		TextComponentTranslation rightclick = new TextComponentTranslation("tooltip.gadgetronig:rightclick");
		TextComponentTranslation shiftclick = new TextComponentTranslation("tooltip.gadgetronig:shiftclick");
		
		tooltip.add(rightclick.getFormattedText());
		tooltip.add(shiftclick.getFormattedText());
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			if(player.isSneaking()) {
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.belt));
				if(tier == 1)
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(ModItems.magnetT1));
				else
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(ModItems.magnetT2));
			} else {
			
			IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
			if(mh != null){
				if(!mh.getHasBelt()) {
					mh.setTier(tier);
					mh.setHasBelt(true);
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ItemStack.EMPTY);
				} else {
					player.sendMessage(new TextComponentTranslation("msg.already_have"));
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
				}
			}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));

	}
}
