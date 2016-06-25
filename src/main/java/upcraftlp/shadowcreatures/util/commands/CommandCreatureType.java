package upcraftlp.shadowcreatures.util.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import upcraftlp.shadowcreatures.entity.EnumShadowCreature;
import upcraftlp.shadowcreatures.util.PlayerProperties;

public class CommandCreatureType extends CommandBase {

	@Override
	public String getCommandName() {
		return "setcreature";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/setcreaturetype <type>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(args.length != 1) 
		{
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + getCommandUsage(sender)));
		}
		else
		{
			if(sender instanceof EntityPlayer)
			{
				EntityPlayerMP player = (EntityPlayerMP) sender;
				PlayerProperties props = PlayerProperties.get(player);
				props.setCreatureType(EnumShadowCreature.valueOf(args[0]));
			
			}
			else
			{
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "ONLY AVAILABLE TO PLAYERS!"));
			}
		}
		
	}

}
