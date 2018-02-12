package com.black_dog20.itemgrabber.client.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
	}
}
