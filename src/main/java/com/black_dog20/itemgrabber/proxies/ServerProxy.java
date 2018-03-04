package com.black_dog20.itemgrabber.proxies;

import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.config.Server;
import com.black_dog20.itemgrabber.config.ServerConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		// NOOP

	}

	@Override
	public void registerKeyInputHandler() {
		// TODO Auto-generated method stub

	}

	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerRendersPreInit() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void ServerRecipes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRendersInit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Server getServerConfig(){
		return ModConfig.server;
	}
}
