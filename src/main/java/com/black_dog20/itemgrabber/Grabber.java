package com.black_dog20.itemgrabber;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.black_dog20.itemgrabber.client.render.BeltRender;
import com.black_dog20.itemgrabber.handler.EventHandler;
import com.black_dog20.itemgrabber.handler.GuiHandler;
import com.black_dog20.itemgrabber.handler.PlayerEventHandler;
import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.init.Recipes;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.proxies.IProxy;
import com.black_dog20.itemgrabber.reference.Reference;
import com.black_dog20.itemgrabber.utility.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Grabber {

	@Mod.Instance(Reference.MOD_ID)
	public static Grabber instance = new Grabber();

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy Proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		MinecraftForge.EVENT_BUS.register(new EventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		Proxy.registerKeyBindings();
		ModItems.init();
		PacketHandler.init();
		Proxy.registerRenders();

		LogHelper.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		Proxy.registerKeyInputHandler();
		Recipes.init();
		
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")).addLayer(new BeltRender());
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim")).addLayer(new BeltRender());
		
		LogHelper.info("Initialization Complete!");
}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		LogHelper.info("Post Initialization Complete!");
	}

	public void reloadRecipes() {
		Recipes.init();
	}
}
