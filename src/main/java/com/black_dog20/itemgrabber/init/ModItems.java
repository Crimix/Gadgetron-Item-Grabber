package com.black_dog20.itemgrabber.init;

import com.black_dog20.itemgrabber.item.ItemBelt;
import com.black_dog20.itemgrabber.item.ItemMagnet;
import com.black_dog20.itemgrabber.item.ItemBase;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModItems {
	
	//public static final Item belt = new ItemBelt();
	public static final Item magnetT1 = new ItemMagnet("ItemMagnetT1",1);
	public static final Item magnetT2 = new ItemMagnet("ItemMagnetT2",2);
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();

		//r.register(belt);
		r.register(magnetT1);
		r.register(magnetT2);
	}
}
