package com.black_dog20.itemgrabber.config;

import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("gadgetronig.config.title")
public class ModConfig {
	
	@Config.LangKey("gadgetronig.config.client")
	public static final Client client = new Client();

	@Config.LangKey("gadgetronig.config.server")
	public static final Server server = new Server();
	
	public static class Client{
		
		@Config.LangKey("gadgetronig.config.sneak")
		public boolean sneak = false;
		
		@Config.LangKey("gadgetronig.config.blockpersec")
		public boolean blockPerSec = false;
		
		@Config.LangKey("gadgetronig.config.iconpos")
		public final HUDPos iconHUDPos = new HUDPos();
		
		public static class HUDPos {

			@Config.LangKey("gadgetronig.config.iconpos.position")
			public position pos = position.Top_Left;
			
			@Config.LangKey("gadgetronig.config.iconpos.hide")
			public boolean hide = false;
			
			@Config.LangKey("gadgetronig.config.iconpos.x")
			public int x = 0;

			@Config.LangKey("gadgetronig.config.iconpos.y")
			public int y = 0;
		}
	}

	public enum position {
		Top_Left,
		Center_Left,
		Bottom_Left,
		Top_Middle,
		Center_Middle,
		Bottom_Middle,
		Top_Right,
		Center_Right,
		Bottom_Right
	}
	
	
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID)) {
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}

	

}
