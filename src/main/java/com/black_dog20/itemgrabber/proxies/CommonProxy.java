package com.black_dog20.itemgrabber.proxies;

import com.black_dog20.itemgrabber.Grabber;
import com.black_dog20.itemgrabber.handler.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public abstract class CommonProxy implements IProxy {

	public void registerNetworkStuff() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Grabber.instance, new GuiHandler());
	}

	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		switch (ctx.side) {
		case CLIENT: {
			assert false : "Message for CLIENT received on dedicated server";
		}
		case SERVER: {
			EntityPlayer entityPlayerMP = ctx.getServerHandler().player;
			return entityPlayerMP;
		}
		default:
			assert false : "Invalid side in TestMsgHandler: " + ctx.side;
		}
		return null;

	}

	public void render() {

	}
}
