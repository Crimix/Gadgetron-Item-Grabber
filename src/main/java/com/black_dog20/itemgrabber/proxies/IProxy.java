package com.black_dog20.itemgrabber.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy {

	public abstract void registerKeyBindings();

	public abstract void registerKeyInputHandler();

	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx);

	EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx);

	public void registerRendersPreInit();
	
	public void registerRendersInit();
	
	public void ServerRecipes();

}
