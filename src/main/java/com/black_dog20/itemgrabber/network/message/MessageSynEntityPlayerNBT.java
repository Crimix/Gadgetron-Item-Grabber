package com.black_dog20.itemgrabber.network.message;

import java.util.UUID;

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

public class MessageSynEntityPlayerNBT implements IMessage, IMessageHandler<MessageSynEntityPlayerNBT, IMessage>{

	public MessageSynEntityPlayerNBT() {}
	
	
	private NBTTagCompound nbt = new NBTTagCompound();
	private String id;
	
	public MessageSynEntityPlayerNBT(EntityPlayer entity) {
		id = entity.getUniqueID().toString();
		nbt = entity.getEntityData();
	}
	
	public MessageSynEntityPlayerNBT(EntityPlayer entity, NBTTagCompound nbt) {
		id = entity.getUniqueID().toString();
		this.nbt = nbt;
	}
	
	
	@Override
	public IMessage onMessage(MessageSynEntityPlayerNBT message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer player = Grabber.Proxy.getPlayerFromMessageContext(ctx);
			Entity entity = player.world.getPlayerEntityByUUID(UUID.fromString(message.id));
			if(message.nbt != null)
				entity.getEntityData().merge(message.nbt);
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = ByteBufUtils.readUTF8String(buf);
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, id);
		ByteBufUtils.writeTag(buf, nbt);
	}

}
