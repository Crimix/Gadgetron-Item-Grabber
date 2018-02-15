package com.black_dog20.itemgrabber.config;

import net.minecraftforge.common.config.Config;

public class Client{
	
	public static boolean Sneak = false;
	
	public static boolean BPS = false;
	
	public final HUDPos iconHUDPos = new HUDPos(0, 0);
	
	public static class HUDPos {
		public HUDPos(final int x, final int y) {
			this.x = x;
			this.y = y;
		}

		@Config.Comment("The x coordinate")
		public int x;

		@Config.Comment("The y coordinate")
		public int y;
	}
}
