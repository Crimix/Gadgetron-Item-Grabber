package com.black_dog20.itemgrabber.init;

import com.black_dog20.itemgrabber.block.BlockAntiMagnet;
import com.black_dog20.itemgrabber.block.BlockAntiMagnetChunk;
import com.black_dog20.itemgrabber.reference.AntiType;
import com.black_dog20.itemgrabber.reference.Reference;
import com.black_dog20.itemgrabber.tileentity.TileEntityAntiMagnet;
import com.black_dog20.itemgrabber.tileentity.TileEntityAntiMagnetChunk;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {
	
	public static BlockAntiMagnet AntiMagnetUp = new BlockAntiMagnet("antiMagnetUp", AntiType.UP);
	public static BlockAntiMagnet AntiMagnetDown = new BlockAntiMagnet("antiMagnetDown", AntiType.DOWN);
	public static BlockAntiMagnet AntiMagnetAll = new BlockAntiMagnet("antiMagnetAll", AntiType.ALL);
	
	
	public static BlockAntiMagnetChunk AntiMagnetChunk = new BlockAntiMagnetChunk("antiMagnetChunk");
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		IForgeRegistry<Block> r = evt.getRegistry();
		r.register(AntiMagnetUp);
		r.register(AntiMagnetDown);
		r.register(AntiMagnetAll);
		r.register(AntiMagnetChunk);
		
		GameRegistry.registerTileEntity(TileEntityAntiMagnet.class, Reference.MOD_ID+":"+"antimagnet");
		GameRegistry.registerTileEntity(TileEntityAntiMagnetChunk.class, Reference.MOD_ID+":"+"antimagnetchunk");
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();
		r.register(new ItemBlock(AntiMagnetUp).setRegistryName(AntiMagnetUp.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetDown).setRegistryName(AntiMagnetDown.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetAll).setRegistryName(AntiMagnetAll.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetChunk).setRegistryName(AntiMagnetChunk.getRegistryName()));
	}
}