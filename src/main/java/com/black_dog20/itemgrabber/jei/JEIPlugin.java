package com.black_dog20.itemgrabber.jei;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.itemgrabber.init.ModItems;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin{

	@Override
	public void register(IModRegistry registry) {
		List<ItemStack> magnetList = new ArrayList<ItemStack>();
		magnetList.add(new ItemStack(ModItems.magnetT1));
		magnetList.add(new ItemStack(ModItems.magnetT2));
		for(ItemStack stack : magnetList){
			registry.addIngredientInfo(stack, ItemStack.class, I18n.format("magnet.info.start"));
		}
		
		List<ItemStack> list = new ArrayList<ItemStack>();

		
		for(ItemStack stack : list){
			String info = getFormattedString(stack);
			if(info != null){
				registry.addIngredientInfo(stack, ItemStack.class, info);
			}
		}
				
		
	}
	private String getFormattedString(ItemStack stack){
		String res = I18n.format(stack.getItem().getUnlocalizedName()+".info");
		if(res.contains(stack.getItem().getUnlocalizedName())){
			return null;
		}
		else{
			return res;
		}
	}
}
