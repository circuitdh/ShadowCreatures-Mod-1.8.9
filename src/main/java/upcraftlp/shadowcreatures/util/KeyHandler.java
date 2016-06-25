package upcraftlp.shadowcreatures.util;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import upcraftlp.shadowcreatures.init.ShadowItems;
import upcraftlp.shadowcreatures.packets.SwordMessage;

public class KeyHandler {

	SimpleNetworkWrapper network = NetworkHelper.network;
	
	@SubscribeEvent
	public void onKeyPressed(KeyInputEvent event)
	{
		if(KeyBindings.morph.isKeyDown())
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MORPH"));
		}
		if(KeyBindings.throwSword.isKeyDown())
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if(player.getHeldItem() != null)
			{
				if(player.getHeldItem().getItem() == ShadowItems.namedangelsword)
				{
					Random rand = new Random();
					player.swingItem();
					player.setItemInUse(player.getHeldItem(), player.getHeldItem().getMaxItemUseDuration());
					player.worldObj.playSoundAtEntity(player, "random.bow", 0.7f, 0.4f / (rand.nextFloat() * 0.4f + 0.8f));
					network.sendToServer(new SwordMessage());
				}
			}
			
		}
	}
}
