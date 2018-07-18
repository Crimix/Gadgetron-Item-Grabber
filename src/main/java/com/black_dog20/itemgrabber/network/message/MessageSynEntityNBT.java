package com.black_dog20.itemgrabber.network.message;

import com.black_dog20.itemgrabber.Grabber;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSynEntityNBT implements IMessage, IMessageHandler<MessageSynEntityNBT, IMessage>{

	public MessageSynEntityNBT() {}
	
	
	private NBTTagCompound nbt = new NBTTagCompound();
	private int id;
	
	public MessageSynEntityNBT(Entity entity) {
		id = entity.getEntityId();
		nbt = entity.getEntityData();
	}
	
	
	@Override
	public IMessage onMessage(MessageSynEntityNBT message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer player = Grabber.Proxy.getPlayerFromMessageContext(ctx);
			Entity entity = player.world.getEntityByID(message.id);
			if(message.nbt != null)
				entity.getEntityData().merge(message.nbt);
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		ByteBufUtils.writeTag(buf, nbt);
	}

}
