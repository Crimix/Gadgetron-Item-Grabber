package com.black_dog20.itemgrabber.config;

import net.minecraftforge.common.config.Config;

public class Server {
	
	@Config.LangKey("gadgetronig.config.range.t1")
	@Config.RangeInt(min = 5, max = 20)
	public int rangeT1 = 10;
	
	@Config.LangKey("gadgetronig.config.speed.t1")
	@Config.RangeDouble(min = 0.25, max = 1)
	public double speedT1 = 0.45;
	
	@Config.LangKey("gadgetronig.config.range.t2")
	@Config.RangeInt(min = 10, max = 25)
	public int rangeT2 = 15;
	
	@Config.LangKey("gadgetronig.config.speed.t2")
	@Config.RangeDouble(min = 0.25, max = 1)
	public double speedT2 = 0.75;
}
