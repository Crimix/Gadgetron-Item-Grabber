package com.black_dog20.itemgrabber.network.message;

import java.util.UUID;

import com.black_dog20.itemgrabber.Grabber;
import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncMagnetCapabilityTracking implements IMessage, IMessageHandler<MessageSyncMagnetCapabilityTracking, IMessage>{

	boolean hasMagnetOn;
	boolean hasBelt;
	int tier;
	int id;
	
	public MessageSyncMagnetCapabilityTracking() {}
	
	public MessageSyncMagnetCapabilityTracking(boolean hasMagnetOn, boolean hasBelt, int tier, EntityPlayer player) {
		this.hasMagnetOn = hasMagnetOn;
		this.hasBelt = hasBelt;
		this.tier = tier;
		id = player.getEntityId();
	}
	
	
	@Override
	public IMessage onMessage(MessageSyncMagnetCapabilityTracking message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer playerTrack = Grabber.Proxy.getPlayerByIDFromMessageContext(id, ctx);
			IMagnetHandler mh = MagnetHandler.instanceFor(playerTrack);
			if(mh != null){
				mh.setHasMagnetOn(message.hasMagnetOn);
				mh.setHasBelt(message.hasBelt);
				mh.setTier(message.tier);
				System.out.println("yes");
			}
			else{
				System.out.println("damn");
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		hasMagnetOn = buf.readBoolean();
		hasBelt = buf.readBoolean();
		tier = buf.readInt();
		id = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(hasMagnetOn);
		buf.writeBoolean(hasBelt);
		buf.writeInt(tier);
		buf.writeInt(id);
	}

}
