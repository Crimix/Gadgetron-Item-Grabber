package com.black_dog20.itemgrabber.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.black_dog20.itemgrabber.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

	/*public static CobbleGen cobbleGen;
	public static OreSoulcystal soulcystalOre;
	public static BlockSoulBarrier soulBarrier;
	public static BlockSoulBarrierFloor soulBarrierFloor;*/
	
	public static void init() {
		/*cobbleGen = new CobbleGen(Material.ROCK);
		soulcystalOre = new OreSoulcystal();
		soulBarrier = new BlockSoulBarrier();
		soulBarrierFloor = new BlockSoulBarrierFloor();*/
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
    	/*cobbleGen.initModel();
    	soulcystalOre.initModel();
    	soulBarrier.initModel();
    	soulBarrierFloor.initModel();*/
    }
}
