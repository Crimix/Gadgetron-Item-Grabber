package com.black_dog20.itemgrabber.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;

public class MessageUpdateMagnetState implements IMessage, IMessageHandler<MessageUpdateMagnetState, IMessage>{

	public MessageUpdateMagnetState() {}
	
	@Override
	public IMessage onMessage(MessageUpdateMagnetState message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			IMagnetHandler mh = MagnetHandler.instanceFor(player);
			mh.setHasMagnetOn(!mh.getHasMagnetOn());
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

}
