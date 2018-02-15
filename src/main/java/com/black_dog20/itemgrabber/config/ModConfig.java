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
	
	private static boolean onOnlineServer = false;
	private static final Server onlineServer = new Server();
	
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
		public final HUDPos iconHUDPos = new HUDPos(0, 0);
		
		public static class HUDPos {
			public HUDPos(final int x, final int y) {
				this.x = x;
				this.y = y;
			}

			@Config.LangKey("gadgetronig.config.iconpos.x")
			public int x;

			@Config.LangKey("gadgetronig.config.iconpos.y")
			public int y;
		}
	}
	
	public static class Server {

		@Config.Name("Magnet T1 Speed")
		@Config.LangKey("gadgetronig.config.speed")
		@Config.RangeDouble(min = 0.02, max = 0.2)
		public double speedT1 = 0.02;
		
		@Config.Name("Magnet T1 Range")
		@Config.LangKey("gadgetronig.config.range")
		@Config.RangeInt(min = 5, max = 15)
		public int rangeT1 = 5;
		
		@Config.Name("Magnet T2 Speed")
		@Config.LangKey("gadgetronig.config.speed")
		@Config.RangeDouble(min = 0.02, max = 0.2)
		public double speedT2 = 0.1;
		
		@Config.Name("Magnet T2 Range")
		@Config.LangKey("gadgetronig.config.range")
		@Config.RangeInt(min = 5, max = 15)
		public int rangeT2 = 10;
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
	
	public static void changeConfigServer(boolean value){
		onOnlineServer = value;
	}
	
	public static void syncServerSettings(int rangeT1, double speedT1,int rangeT2, double speedT2){
		onlineServer.rangeT1=rangeT1;
		onlineServer.speedT1=speedT1;
		onlineServer.rangeT2=rangeT2;
		onlineServer.speedT2=speedT2;
	}
	
	public static int getRange(int tier){
		if(onOnlineServer){
			switch (tier) {
			case 1:
				return onlineServer.rangeT1;
			case 2:
				return onlineServer.rangeT2;

			default:
				return onlineServer.rangeT1;
			}
		}
		switch (tier) {
		case 1:
			return server.rangeT1;
		case 2:
			return server.rangeT2;

		default:
			return server.rangeT1;
		}
	}
	
	public static double getSpeed(int tier){
		if(onOnlineServer){
			switch (tier) {
			case 1:
				return onlineServer.speedT1;
			case 2:
				return onlineServer.speedT2;

			default:
				return onlineServer.speedT1;
			}
		}
		switch (tier) {
		case 1:
			return server.speedT1;
		case 2:
			return server.speedT2;

		default:
			return server.speedT1;
		}
	}

	

}
