package com.black_dog20.itemgrabber.client.settings;

import org.lwjgl.input.Keyboard;

import com.black_dog20.itemgrabber.reference.Names;

import net.minecraft.client.settings.KeyBinding;

public class Keybindings {
	
	public static KeyBinding ON = new KeyBinding(Names.Keys.ON, Keyboard.KEY_M, Names.Keys.CATEGORY);
	public static KeyBinding TAKE_OFF = new KeyBinding(Names.Keys.TAKE_OFF, Keyboard.KEY_U, Names.Keys.CATEGORY);

}
