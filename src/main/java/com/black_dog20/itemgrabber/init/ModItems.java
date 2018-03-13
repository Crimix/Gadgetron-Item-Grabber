package com.black_dog20.itemgrabber.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import com.black_dog20.itemgrabber.item.ItemBase;
import com.black_dog20.itemgrabber.item.ItemBelt;
import com.black_dog20.itemgrabber.item.ItemMagnet;
import com.black_dog20.itemgrabber.reference.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModItems {
	
	public static final Item belt = new ItemBase("belt");
	public static final Item beltT1 = new ItemBelt("beltT1",1);
	public static final Item beltT2 = new ItemBelt("beltT2",2);
	public static final Item magnetT1 = new ItemMagnet("magnetT1",1);
	public static final Item magnetT2 = new ItemMagnet("magnetT2",2);
	public static final Item coreT1 = new ItemBase("coreT1");
	public static final Item screenT1 = new ItemBase("screenT1");
	public static final Item caseT1 = new ItemBase("caseT1");
	public static final Item plateT1 = new ItemBase("plateT1");
	public static final Item coreT2 = new ItemBase("coreT2");
	public static final Item screenT2 = new ItemBase("screenT2");
	public static final Item caseT2 = new ItemBase("caseT2");
	public static final Item plateT2 = new ItemBase("plateT2");
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();

		r.register(belt);
		r.register(beltT1);
		r.register(beltT2);
		r.register(magnetT1);
		r.register(magnetT2);
		r.register(coreT1);
		r.register(screenT1);
		r.register(caseT1);
		r.register(plateT1);
		r.register(coreT2);
		r.register(screenT2);
		r.register(caseT2);
		r.register(plateT2);
	}
}
