package upcraftlp.shadowcreatures.util.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import upcraftlp.shadowcreatures.init.GhostDimension;
import upcraftlp.shadowcreatures.world.DimTeleporterGhost;

public class CommandGhostDim extends CommandBase{

	@Override
	public String getCommandName() {
		return "ghost";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/ghost";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer)
		{
			int dim;
			EntityPlayerMP player = (EntityPlayerMP) sender;
			DimTeleporterGhost teleporter = new DimTeleporterGhost(player.getServerForPlayer());
			if(player.dimension == GhostDimension.getId())
			{
				dim = 0;
			}
			else
			{
				dim = GhostDimension.getId();
			}
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, dim, teleporter);
			
		}
		else
		{
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "ONLY AVAILABLE TO PLAYERS!"));
		}
	}

}
