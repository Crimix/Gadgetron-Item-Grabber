package com.black_dog20.itemgrabber.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.black_dog20.itemgrabber.Grabber;
import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;

public class MessageSyncMagnetCapabilityTracking implements IMessage, IMessageHandler<MessageSyncMagnetCapabilityTracking, IMessage>{

	private boolean hasMagnetOn;
	private boolean hasBelt;
	private int tier;
	private int id;
	private boolean sneak;
	
	public MessageSyncMagnetCapabilityTracking() {}
	
	public MessageSyncMagnetCapabilityTracking(IMagnetHandler mh, EntityPlayer player) {
		hasMagnetOn = mh.getHasMagnetOn();
		hasBelt = mh.getHasBelt();
		tier = mh.getTier();
		sneak = mh.getSneakDeactivate();
		id = player.getEntityId();
	}
	
	
	@Override
	public IMessage onMessage(MessageSyncMagnetCapabilityTracking message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer playerTrack = Grabber.Proxy.getPlayerByIDFromMessageContext(message.id, ctx);
			IMagnetHandler mh = MagnetHandler.instanceFor(playerTrack);
			if(mh != null){
				mh.setHasMagnetOn(message.hasMagnetOn);
				mh.setHasBelt(message.hasBelt);
				mh.setTier(message.tier);
				mh.setSneakDeactivate(message.sneak);
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
		sneak = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(hasMagnetOn);
		buf.writeBoolean(hasBelt);
		buf.writeInt(tier);
		buf.writeInt(id);
		buf.writeBoolean(sneak);
	}

}
