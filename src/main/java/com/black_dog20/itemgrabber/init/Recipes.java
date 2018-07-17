package com.black_dog20.itemgrabber.init;

import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.coreT1, new Object[] { "awa", "rcr", "twt", 't', "plateTrillium" , 'r', "shardRaritanium", 'a',"plateAdamantine", 'w', "wireConductingMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.screenT1, new Object[] { "pgp", "gsg", "pgp", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,11) , 'p', "plateTitanium", 's', "silicon" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.plateT1, new Object[] { " r ", "rar", " r ", 'a', "plateAdamantine", 'r', new ItemStack(Items.DYE,1,1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.caseT1, new Object[] { "tat", "pcp", "ttt", 't', "ingotTitanium", 'a', "ingotAdamantine", 'p', ModItems.plateT1, 'c', "plateCarbonox" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.magnetT1, ModItems.screenT1, ModItems.caseT1, ModItems.coreT1));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.coreT2, new Object[] { "rwr", "rcr", "rwr", 'c', ModItems.coreT1 , 'r', "crystalRaritanium", 'w', "wireConductingMetal"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.screenT2, new Object[] { "ggg", "gsg", "ggg", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,5) , 's', "silicon" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.plateT2, new Object[] { "aaa", "apa", "aaa", 'a', "plateAdamantine", 'p', ModItems.plateT1 }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.caseT2, new Object[] { "tat", "pcp", "ttt", 't', "ingotCarbonox", 'a', "ingotAdamantine", 'p', ModItems.plateT2, 'c', ModItems.magnetT1 }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.magnetT2, ModItems.screenT2, ModItems.caseT2, ModItems.coreT2));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.AntiMagnetChunk, new Object[] { "twt", "sas", "twt", 'a', ModBlocks.AntiMagnetAll, 't', "plateCarbonox", 's', "shardRaritanium", 'w', "wireConductingMetal" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.AntiMagnetAll, new Object[] { "sps", "scs", "sps", 's', new ItemStack(Blocks.STONE_SLAB, 1, 0), 'p', "plateCarbonox", 'c', ModItems.coreT2 }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModBlocks.AntiMagnetAll, ModBlocks.AntiMagnetDown));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModBlocks.AntiMagnetUp, ModBlocks.AntiMagnetAll));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModBlocks.AntiMagnetDown, ModBlocks.AntiMagnetUp));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModBlocks.AntiMagnetAll, ModBlocks.AntiMagnetChunk));
		
	}

}
