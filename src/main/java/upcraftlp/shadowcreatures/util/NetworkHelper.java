package upcraftlp.shadowcreatures.util;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import upcraftlp.shadowcreatures.Reference;
import upcraftlp.shadowcreatures.packets.SwordMessage;
import upcraftlp.shadowcreatures.packets.SwordPacketHandler;

public class NetworkHelper {
	private static int id = 0;
	public static SimpleNetworkWrapper network;
	
	public static void init()
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		network.registerMessage(SwordPacketHandler.class, SwordMessage.class, ++id, Side.SERVER);
	}
	
	
	
}
