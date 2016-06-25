package upcraftlp.shadowcreatures.util;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public class FlightHandler {

	private static ArrayList<String> flightPlayers = new ArrayList<String>();
	
	public static void add(EntityPlayer player)
	{
		flightPlayers.add(player.getName());
		player.capabilities.allowFlying = true;
		player.sendPlayerAbilities();

	}
	
	public static void remove(EntityPlayer player)
	{
		if(isFlight(player))
		{
			flightPlayers.remove(player.getName());
			player.capabilities.allowFlying = false;
			player.sendPlayerAbilities();
		}
	}
	
	public static boolean isFlight(EntityPlayer player)
	{
		if(flightPlayers.contains(player.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
