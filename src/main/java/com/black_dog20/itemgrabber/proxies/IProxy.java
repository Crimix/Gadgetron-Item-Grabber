package com.black_dog20.itemgrabber.proxies;

import com.black_dog20.itemgrabber.config.Server;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy {

	public abstract void registerKeyBindings();

	public abstract void registerKeyInputHandler();

	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx);

	EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx);

	public void registerRendersPreInit();
	
	public void registerRendersInit();
	
	public void ServerRecipes();

	public Server getServerConfig();
}
