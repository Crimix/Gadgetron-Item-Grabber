package com.black_dog20.itemgrabber.init;

import com.black_dog20.itemgrabber.block.BlockAntiMagnet;
import com.black_dog20.itemgrabber.block.BlockAntiMagnet;
import com.black_dog20.itemgrabber.reference.AntiType;
import com.black_dog20.itemgrabber.reference.Reference;
import com.black_dog20.itemgrabber.tileentity.TileEntityAntiMagnet;

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
	
	public static BlockAntiMagnet AntiMagnetT1Up = new BlockAntiMagnet("antiMagnetT1Up", AntiType.UP, 2);
	public static BlockAntiMagnet AntiMagnetT1Down = new BlockAntiMagnet("antiMagnetT1Down", AntiType.DOWN, 2);
	public static BlockAntiMagnet AntiMagnetT1All = new BlockAntiMagnet("antiMagnetT1All", AntiType.ALL, 2);
	
	public static BlockAntiMagnet AntiMagnetT2Up = new BlockAntiMagnet("antiMagnetT2Up", AntiType.UP, 3);
	public static BlockAntiMagnet AntiMagnetT2Down = new BlockAntiMagnet("antiMagnetT2Down", AntiType.DOWN, 3);
	public static BlockAntiMagnet AntiMagnetT2All = new BlockAntiMagnet("antiMagnetT2All", AntiType.ALL, 3);
	
	public static BlockAntiMagnet AntiMagnetT3Up = new BlockAntiMagnet("antiMagnetT3Up", AntiType.UP, 4);
	public static BlockAntiMagnet AntiMagnetT3Down = new BlockAntiMagnet("antiMagnetT3Down", AntiType.DOWN, 4);
	public static BlockAntiMagnet AntiMagnetT3All = new BlockAntiMagnet("antiMagnetT3All", AntiType.ALL, 4);
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		IForgeRegistry<Block> r = evt.getRegistry();
		r.register(AntiMagnetT1Up);
		r.register(AntiMagnetT1Down);
		r.register(AntiMagnetT1All);
		r.register(AntiMagnetT2Up);
		r.register(AntiMagnetT2Down);
		r.register(AntiMagnetT2All);
		r.register(AntiMagnetT3Up);
		r.register(AntiMagnetT3Down);
		r.register(AntiMagnetT3All);
		
		GameRegistry.registerTileEntity(TileEntityAntiMagnet.class, Reference.MOD_ID+":"+"antimagnet");
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();
		r.register(new ItemBlock(AntiMagnetT1Up).setRegistryName(AntiMagnetT1Up.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT1Down).setRegistryName(AntiMagnetT1Down.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT1All).setRegistryName(AntiMagnetT1All.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT2Up).setRegistryName(AntiMagnetT2Up.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT2Down).setRegistryName(AntiMagnetT2Down.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT2All).setRegistryName(AntiMagnetT2All.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT3Up).setRegistryName(AntiMagnetT3Up.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT3Down).setRegistryName(AntiMagnetT3Down.getRegistryName()));
		r.register(new ItemBlock(AntiMagnetT3All).setRegistryName(AntiMagnetT3All.getRegistryName()));
	
	}
}
