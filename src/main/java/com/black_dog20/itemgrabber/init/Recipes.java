package com.black_dog20.itemgrabber.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.black_dog20.itemgrabber.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.coreT1, new Object[] { "aaa", "rrr", "ttt", 't', "ingotTrillium" , 'r', "crystalRaritanium", 'a',"ingotAdamantine" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.screenT1, new Object[] { "ggg", "grg", "ggg", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,11) , 'r', ModItems.coreT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.plateT1, new Object[] { "ara", "rar", "ara", 'a', "ingotAdamantine", 'r', new ItemStack(Items.DYE,1,1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.caseT1, new Object[] { "tat", "pcp", "ttt", 't', "ingotTitanium", 'a', "ingotAdamantine", 'p', ModItems.plateT1, 'c', "ingotCarbonox" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.magnetT1, new Object[] { "s", "c", 's', ModItems.screenT1, 'c', ModItems.caseT1 }));
		
	}

}
