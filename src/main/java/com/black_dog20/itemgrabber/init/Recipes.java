package com.black_dog20.itemgrabber.init;

import com.black_dog20.gadgetron.utility.RecipeToJSON;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		
		
	}
	
	@SuppressWarnings("unused")
	private void generateJSON() {

		RecipeToJSON toJSON = new RecipeToJSON(Reference.MOD_NAME, true);
		
		toJSON.addShapedRecipe(ModItems.coreT1, new Object[] { "awa", "rcr", "twt", 't', "plateTrillium" , 'r', "shardRaritanium", 'a',"plateAdamantine", 'w', "wireConductingMetal" , 'c', "crystalRaritanium"});
		toJSON.addShapedRecipe(ModItems.screenT1, new Object[] { "pgp", "gsg", "pgp", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,11) , 'p', "plateTitanium", 's', "silicon" });
		toJSON.addShapedRecipe(ModItems.plateT1, new Object[] { " r ", "rar", " r ", 'a', "plateAdamantine", 'r', new ItemStack(Items.DYE,1,1) });
		toJSON.addShapedRecipe(ModItems.caseT1, new Object[] { "tat", "pcp", "ttt", 't', "ingotTitanium", 'a', "ingotAdamantine", 'p', ModItems.plateT1, 'c', "plateCarbonox" });
		toJSON.addShapelessRecipe(ModItems.magnetT1, ModItems.screenT1, ModItems.caseT1, ModItems.coreT1);
		toJSON.addShapedRecipe(ModItems.coreT2, new Object[] { "rwr", "rcr", "rwr", 'c', ModItems.coreT1 , 'r', "crystalRaritanium", 'w', "wireConductingMetal"});
		toJSON.addShapedRecipe(ModItems.screenT2, new Object[] { "ggg", "gsg", "ggg", 'g', new ItemStack(Blocks.STAINED_GLASS_PANE, 1,5) , 's', "silicon" });
		toJSON.addShapedRecipe(ModItems.plateT2, new Object[] { "aaa", "apa", "aaa", 'a', "plateAdamantine", 'p', ModItems.plateT1 });
		toJSON.addShapedRecipe(ModItems.caseT2, new Object[] { "tat", "pcp", "ttt", 't', "ingotCarbonox", 'a', "ingotAdamantine", 'p', ModItems.plateT2, 'c', ModItems.magnetT1 });
		toJSON.addShapelessRecipe(ModItems.magnetT2, ModItems.screenT2, ModItems.caseT2, ModItems.coreT2);
		
		toJSON.addShapedRecipe(ModBlocks.AntiMagnetChunk, new Object[] { "twt", "sas", "twt", 'a', ModBlocks.AntiMagnetAll, 't', "plateCarbonox", 's', "shardRaritanium", 'w', "wireConductingMetal" });
		toJSON.addShapedRecipe(ModBlocks.AntiMagnetAll, new Object[] { "sps", "scs", "sps", 's', new ItemStack(Blocks.STONE_SLAB, 1, 0), 'p', "plateCarbonox", 'c', ModItems.coreT2 });
		toJSON.addShapelessRecipe(ModBlocks.AntiMagnetAll, ModBlocks.AntiMagnetDown);
		toJSON.addShapelessRecipe(ModBlocks.AntiMagnetUp, ModBlocks.AntiMagnetAll);
		toJSON.addShapelessRecipe(ModBlocks.AntiMagnetDown, ModBlocks.AntiMagnetUp);
		toJSON.addShapelessRecipe(ModBlocks.AntiMagnetAll, ModBlocks.AntiMagnetChunk);
	}

}
