package com.black_dog20.itemgrabber.proxies;

import com.black_dog20.itemgrabber.client.gui.GuiMagnetIcon;
import com.black_dog20.itemgrabber.client.handler.KeyInputEventHandler;
import com.black_dog20.itemgrabber.client.render.BeltRender;
import com.black_dog20.itemgrabber.client.settings.Keybindings;
import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.config.Server;
import com.black_dog20.itemgrabber.config.ServerConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;


public class ClientProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(Keybindings.ON);
		ClientRegistry.registerKeyBinding(Keybindings.TAKE_OFF);
	}

	@Override
	public void registerKeyInputHandler() {
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
	}

	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		switch (ctx.side) {
		case CLIENT:
			EntityPlayer entityClientPlayerMP = Minecraft.getMinecraft().player;
			return entityClientPlayerMP;
		case SERVER:
			EntityPlayer entityPlayerMP = ctx.getServerHandler().player;
			return entityPlayerMP;
		}
		return null;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			EntityPlayer entityClientPlayer = (EntityPlayer) Minecraft.getMinecraft().world.getEntityByID(id);
			return entityClientPlayer;
		}
		return null;
	}

	@Override
	public void registerRendersPreInit() {
		MinecraftForge.EVENT_BUS.register(new GuiMagnetIcon());
	}
	
	@Override
	public void ServerRecipes() {
	}

	@Override
	public void registerRendersInit() {
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")).addLayer(new BeltRender());
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim")).addLayer(new BeltRender());
	}

	@Override
	public Server getServerConfig(){
		if(ServerConfig.onServer)
			return ServerConfig.server;
		else
			return ModConfig.server;
	}

}
