package com.black_dog20.itemgrabber.network.message;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.init.ModItems;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateBeltState implements IMessage, IMessageHandler<MessageUpdateBeltState, IMessage>{


	public MessageUpdateBeltState() {}
	
	@Override
	public IMessage onMessage(MessageUpdateBeltState message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			IMagnetHandler mh = MagnetHandler.instanceFor(player);
			if(mh.getTier() == 1) {
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.beltT1));
			} else 	if(mh.getTier() == 2) {
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.beltT2));
			}
			
			mh.setHasBelt(false);
			mh.setTier(0);
			
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
