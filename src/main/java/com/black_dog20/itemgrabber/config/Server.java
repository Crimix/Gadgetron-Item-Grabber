package com.black_dog20.itemgrabber.config;

import net.minecraftforge.common.config.Config;

public class Server {
	
	@Config.Name("Magnet T1 Range")
	@Config.LangKey("gadgetronig.config.range")
	@Config.RangeInt(min = 5, max = 20)
	public int rangeT1 = 10;
	
	@Config.Name("Magnet T2 Range")
	@Config.LangKey("gadgetronig.config.range")
	@Config.RangeInt(min = 10, max = 25)
	public int rangeT2 = 15;
}
