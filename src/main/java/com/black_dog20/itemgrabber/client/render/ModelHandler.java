package com.black_dog20.itemgrabber.client.render;

import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public final class ModelHandler {
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent evt) {

		for(Item item : Item.REGISTRY) {
			if(item instanceof IItemModelRegister)
				((IItemModelRegister) item).initModel();
		}
	}
}