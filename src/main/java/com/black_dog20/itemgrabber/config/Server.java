package com.black_dog20.itemgrabber.config;

import net.minecraftforge.common.config.Config;

public class Server {

	@Config.Name("Magnet Speed")
	@Config.LangKey("itemgrabber.magnet.speed")
	@Config.RangeDouble(min = 0.02, max = 0.2)
	public static double SPEEDT1 = 0.02;
	
	@Config.Name("Magnet Range")
	@Config.LangKey("itemgrabber.magnet.range")
	@Config.RangeInt(min = 5, max = 15)
	public static int RANGET1 = 5;
	
	@Config.Name("Magnet Speed")
	@Config.LangKey("itemgrabber.magnet.speed")
	@Config.RangeDouble(min = 0.02, max = 0.2)
	public static double SPEEDT2 = 0.02;
	
	@Config.Name("Magnet Range")
	@Config.LangKey("itemgrabber.magnet.range")
	@Config.RangeInt(min = 5, max = 15)
	public static int RANGET2 = 5;
}
