package upcraftlp.shadowcreatures.init;

import net.minecraft.command.ServerCommandManager;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import upcraftlp.shadowcreatures.util.commands.CommandCrash;
import upcraftlp.shadowcreatures.util.commands.CommandCreatureType;
import upcraftlp.shadowcreatures.util.commands.CommandGhostDim;
import upcraftlp.shadowcreatures.util.commands.CommandHeal;

public class ShadowCommands {

	public static void init(FMLServerStartingEvent event)
	{
		ServerCommandManager manager = (ServerCommandManager) event.getServer().getCommandManager();
		manager.registerCommand(new CommandHeal());
		manager.registerCommand(new CommandGhostDim());
		//DEBUG ONLY
		if(ShadowConfig.isDebug) 
		{
			manager.registerCommand(new CommandCreatureType());
			manager.registerCommand(new CommandCrash());
		}
	}
}
