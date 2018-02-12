package com.black_dog20.itemgrabber.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.black_dog20.itemgrabber.Grabber;
import com.black_dog20.itemgrabber.config.ModConfig;


public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {

	@Override
	public IMessage onMessage(MessageConfigSync message, MessageContext context) {
		Grabber.instance.Proxy.ServerRecipes();
		return null;
	}

	public MessageConfigSync() {}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		ModConfig.changeConfigServer(true);

	}
}
