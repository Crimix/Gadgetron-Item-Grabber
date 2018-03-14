package com.black_dog20.itemgrabber.jei;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.itemgrabber.init.ModItems;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class JEIPlugin extends BlankModPlugin{

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		// TODO Auto-generated method stub
		
	}

}
