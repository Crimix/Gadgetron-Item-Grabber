package com.black_dog20.itemgrabber.network.message;

import com.black_dog20.itemgrabber.Grabber;
import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;


import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncMagnetCapability implements IMessage, IMessageHandler<MessageSyncMagnetCapability, IMessage>{

	boolean hasMagnetOn;
	boolean hasBelt;
	int tier;
	
	public MessageSyncMagnetCapability() {}
	
	public MessageSyncMagnetCapability(boolean hasMagnetOn, boolean hasBelt, int tier) {
		this.hasMagnetOn = hasMagnetOn;
		this.hasBelt = hasBelt;
		this.tier = tier;
	}
	
	
	@Override
	public IMessage onMessage(MessageSyncMagnetCapability message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer player = Grabber.Proxy.getPlayerFromMessageContext(ctx);
			IMagnetHandler mh = MagnetHandler.instanceFor(player);
			if(mh != null){
				mh.setHasMagnetOn(message.hasMagnetOn);
				mh.setHasBelt(message.hasBelt);
				mh.setTier(message.tier);
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		hasMagnetOn = buf.readBoolean();
		hasBelt = buf.readBoolean();
		tier = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(hasMagnetOn);
		buf.writeBoolean(hasBelt);
		buf.writeInt(tier);
	}

}