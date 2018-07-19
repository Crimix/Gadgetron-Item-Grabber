package com.black_dog20.itemgrabber;

import org.apache.logging.log4j.Logger;

import com.black_dog20.itemgrabber.init.Recipes;
import com.black_dog20.itemgrabber.api.GrabberApi;
import com.black_dog20.itemgrabber.handler.EventHandler;
import com.black_dog20.itemgrabber.handler.GuiHandler;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.proxies.IProxy;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import vazkii.botania.common.block.subtile.functional.SubTileSolegnolia;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, acceptedMinecraftVersions = Reference.MC_VERSIONS)
public class Grabber {

	@Mod.Instance(Reference.MOD_ID)
	public static Grabber instance = new Grabber();
	public static Logger logger;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy Proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		Proxy.registerKeyBindings();
		PacketHandler.init();
		Proxy.registerRendersPreInit();
		SetupMagnet();
		
		logger.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		Proxy.registerKeyInputHandler();
		Recipes.init();
		
		logger.info("Initialization Complete!");
}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		logger.info("Post Initialization Complete!");
	}

	
	public void SetupMagnet(){
		GrabberApi.addBlacklistedItem(new ResourceLocation("appliedenergistics2", "item.ItemCrystalSeed"));
		GrabberApi.addEntityItemHandler((x) -> Loader.isModLoaded("botania") && SubTileSolegnolia.hasSolegnoliaAround(x));
		GrabberApi.addEntityPlayerHandler((x) -> Loader.isModLoaded("botania") && SubTileSolegnolia.hasSolegnoliaAround(x));
	}
}
