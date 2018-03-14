package com.black_dog20.itemgrabber.network;

import com.black_dog20.itemgrabber.network.message.MessageConfigSync;
import com.black_dog20.itemgrabber.network.message.MessageSyncMagnetCapability;
import com.black_dog20.itemgrabber.network.message.MessageSyncMagnetCapabilityTracking;
import com.black_dog20.itemgrabber.network.message.MessageUpdateBeltState;
import com.black_dog20.itemgrabber.network.message.MessageUpdateMagnetState;
import com.black_dog20.itemgrabber.network.message.MessageUpdateSneakState;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class PacketHandler {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {
		network.registerMessage(MessageConfigSync.class, MessageConfigSync.class, 1, Side.CLIENT);
		network.registerMessage(MessageUpdateMagnetState.class, MessageUpdateMagnetState.class, 2, Side.SERVER);
		network.registerMessage(MessageSyncMagnetCapability.class, MessageSyncMagnetCapability.class, 3, Side.CLIENT);
		network.registerMessage(MessageSyncMagnetCapabilityTracking.class, MessageSyncMagnetCapabilityTracking.class, 4, Side.CLIENT);
		network.registerMessage(MessageUpdateSneakState.class, MessageUpdateSneakState.class, 5, Side.SERVER);
		network.registerMessage(MessageUpdateBeltState.class, MessageUpdateBeltState.class, 6, Side.SERVER);
	}

}
