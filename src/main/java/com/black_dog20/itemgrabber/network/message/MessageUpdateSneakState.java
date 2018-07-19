package com.black_dog20.itemgrabber.network.message;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateSneakState implements IMessage, IMessageHandler<MessageUpdateSneakState, IMessage>{

	private boolean sneak;
	public MessageUpdateSneakState() {}
	public MessageUpdateSneakState(boolean sneak) {
		this.sneak = sneak;
	}
	
	@Override
	public IMessage onMessage(MessageUpdateSneakState message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			IBeltHandler mh = BeltHandler.instanceFor(player);
			if(mh != null)
				mh.setSneakDeactivate(message.sneak);
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		sneak = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(sneak);
	}

}
