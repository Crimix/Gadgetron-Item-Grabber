package com.black_dog20.itemgrabber.init;

import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.coreT1, new Object[] { "aaa", "rrr", "ttt", 't', "ingotTrillium" , 'r', "crystalRaritanium", 'a',"ingotAdamantine" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.screenT1, new Object[] { "ggg", "grg", "ggg", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,11) , 'r', ModItems.coreT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.plateT1, new Object[] { "ara", "rar", "ara", 'a', "ingotAdamantine", 'r', new ItemStack(Items.DYE,1,1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.caseT1, new Object[] { "tat", "pcp", "ttt", 't', "ingotTitanium", 'a', "ingotAdamantine", 'p', ModItems.plateT1, 'c', "ingotCarbonox" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.magnetT1, ModItems.screenT1, ModItems.caseT1));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.coreT2, new Object[] { "rrr", "rcr", "rrr", 'c', ModItems.coreT1 , 'r', "crystalRaritanium" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.screenT2, new Object[] { "ggg", "grg", "ggg", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,5) , 'r', ModItems.coreT2 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.plateT2, new Object[] { "aaa", "apa", "aaa", 'a', "ingotAdamantine", 'p', ModItems.plateT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.caseT2, new Object[] { "tat", "pcp", "ttt", 't', "ingotCarbonox", 'a', "ingotAdamantine", 'p', ModItems.plateT2, 'c', ModItems.magnetT1 }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.magnetT2, ModItems.screenT2, ModItems.caseT2));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.blackLeather, Items.LEATHER, new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()), "ingotTitanium" ));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blackLeather, 6), new Object[] { "ltl", "ldl", "ltl", 'l', Items.LEATHER , 'd', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()), 't', "ingotTitanium" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.belt, new Object[] { "lll", "ttt", "lll", 't', "ingotTitanium" , 'l', ModItems.blackLeather }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.beltT1, ModItems.belt, ModItems.magnetT1));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.beltT2, ModItems.belt, ModItems.magnetT2));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.AntiMagnetChunk, new Object[] { "tat", "ata", "tat", 'a', ModBlocks.AntiMagnetAll, 't', "ingotCarbonox" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.AntiMagnetAll, new Object[] { "sss", "scs", "sss", 's', new ItemStack(Blocks.STONE_SLAB, 1, 0) , 'c', ModItems.coreT2 }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModBlocks.AntiMagnetAll, ModBlocks.AntiMagnetDown));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModBlocks.AntiMagnetUp, ModBlocks.AntiMagnetAll));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModBlocks.AntiMagnetDown, ModBlocks.AntiMagnetUp));

	}

}
