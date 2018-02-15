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

		return null;
	}

	public MessageConfigSync() {}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(ModConfig.server.rangeT1);
		buf.writeDouble(ModConfig.server.speedT1);
		buf.writeInt(ModConfig.server.rangeT2);
		buf.writeDouble(ModConfig.server.speedT2);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		ModConfig.changeConfigServer(true);
		int rangeT1 = buf.readInt();
		double speedT1 = buf.readDouble();
		int rangeT2 = buf.readInt();
		double speedT2 = buf.readDouble();
		ModConfig.syncServerSettings(rangeT1, speedT1, rangeT2, speedT2);
	}
}
