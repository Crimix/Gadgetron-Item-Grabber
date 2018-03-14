package com.black_dog20.itemgrabber.init;

import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.coreT1, new Object[] { "aaa", "rrr", "ttt", 't', "ingotTrillium" , 'r', "crystalRaritanium", 'a',"ingotAdamantine" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.screenT1, new Object[] { "ggg", "grg", "ggg", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,11) , 'r', ModItems.coreT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.plateT1, new Object[] { "ara", "rar", "ara", 'a', "ingotAdamantine", 'r', new ItemStack(Items.DYE,1,1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.caseT1, new Object[] { "tat", "pcp", "ttt", 't', "ingotTitanium", 'a', "ingotAdamantine", 'p', ModItems.plateT1, 'c', "ingotCarbonox" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.magnetT1, new Object[] { "s", "c", 's', ModItems.screenT1, 'c', ModItems.caseT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.coreT2, new Object[] { "rrr", "rcr", "rrr", 'c', ModItems.coreT1 , 'r', "crystalRaritanium" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.screenT2, new Object[] { "ggg", "grg", "ggg", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,5) , 'r', ModItems.coreT2 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.plateT2, new Object[] { "aaa", "apa", "aaa", 'a', "ingotAdamantine", 'p', ModItems.plateT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.caseT2, new Object[] { "tat", "pcp", "ttt", 't', "ingotCarbonox", 'a', "ingotAdamantine", 'p', ModItems.plateT2, 'c', ModItems.magnetT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.magnetT2, new Object[] { "s", "c", 's', ModItems.screenT2, 'c', ModItems.caseT2 }));
	
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.blackLeather, new Object[] { "lt", "d ", 'l', Items.LEATHER , 'd', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()), 't', "ingotTitanium" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blackLeather, 6), new Object[] { "ltl", "ldl", "ltl", 'l', Items.LEATHER , 'd', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()), 't', "ingotTitanium" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.belt, new Object[] { "lll", "ttt", "lll", 't', "ingotTitanium" , 'l', ModItems.blackLeather }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.beltT1, new Object[] { "b", "m", 'b', ModItems.belt , 'm', ModItems.magnetT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.beltT2, new Object[] { "b", "m", 'b', ModItems.belt , 'm', ModItems.magnetT2 }));
	}

}
