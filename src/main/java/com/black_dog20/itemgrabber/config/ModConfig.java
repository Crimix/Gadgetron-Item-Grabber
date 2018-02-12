package com.black_dog20.itemgrabber.config;

import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("itemgrabber.config.title")
public class ModConfig {
	
	private static boolean configurationServer = false;
	
	@Config.Name("Magnet Speed")
	@Config.LangKey("itemgrabber.magnet.speed")
	@Config.RangeDouble(min = 0.02, max = 0.2)
	public static double SPEED = 0.02;
	
	@Config.Name("Magnet Range")
	@Config.LangKey("itemgrabber.magnet.range")
	@Config.RangeInt(min = 5, max = 15)
	public static int RANGE = 5;
	
	
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID) && !configurationServer) {
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
	
	public static void changeConfigServer(boolean value){
		configurationServer = value;
	}

}
