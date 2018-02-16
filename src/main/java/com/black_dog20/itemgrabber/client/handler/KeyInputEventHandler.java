package com.black_dog20.itemgrabber.client.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.black_dog20.itemgrabber.client.settings.Keybindings;
import com.black_dog20.itemgrabber.network.PacketHandler;
import com.black_dog20.itemgrabber.network.message.MessageUpdateMagnetState;


@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if(Keybindings.ON.isPressed()){
			PacketHandler.network.sendToServer(new MessageUpdateMagnetState());
		}
	}
}
