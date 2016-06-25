package upcraftlp.shadowcreatures.init;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import upcraftlp.shadowcreatures.Reference;
import upcraftlp.shadowcreatures.entity.EnumShadowCreature;
import upcraftlp.shadowcreatures.events.AttackEvents;
import upcraftlp.shadowcreatures.events.PlayerEvents;
import upcraftlp.shadowcreatures.events.VampireEvents;
import upcraftlp.shadowcreatures.events.WerewolfEvents;
import upcraftlp.shadowcreatures.util.BucketHandler;
import upcraftlp.shadowcreatures.util.ModUpdate;
import upcraftlp.shadowcreatures.util.PlayerProperties;

public class ShadowEvents {
	
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new ShadowEvents());
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
		MinecraftForge.EVENT_BUS.register(new AttackEvents());
		MinecraftForge.EVENT_BUS.register(new VampireEvents());
		MinecraftForge.EVENT_BUS.register(new WerewolfEvents());
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
	}
	
	@SubscribeEvent
	public void cloneProperites(PlayerEvent.Clone event)
	{
		PlayerProperties propsOld = PlayerProperties.get(event.original);
		PlayerProperties propsNew = PlayerProperties.get(event.entityPlayer);
		propsNew.setCreatureType(propsOld.getCreatureType());
	}
	
	private boolean hasShownUpdate = false;
	
	@SubscribeEvent
	public void updateCheck(ClientTickEvent event)
	{
		if(ModUpdate.isNewVersionAvailable() && !hasShownUpdate && Minecraft.getMinecraft().currentScreen == null)
		{
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage((IChatComponent) new ChatComponentText(EnumChatFormatting.AQUA + "Update Available to Shadow Creatures!"));
			
			ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, 
		              Reference.UPDATE_URL);
		        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Downlaod version " + EnumChatFormatting.DARK_AQUA + ModUpdate.latestVersion + EnumChatFormatting.RESET + " from " + EnumChatFormatting.BLUE + "here" + EnumChatFormatting.RESET + ".").setChatStyle(new ChatStyle().setChatClickEvent(versionCheckChatClickEvent)));
			
			
			hasShownUpdate = true;
		}
	}
	
	@SubscribeEvent
	public void registerPlayerProperties(EntityConstructing event)
	{
		if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		{
			PlayerProperties.register((EntityPlayer) event.entity);
		}
	}
	
	@SubscribeEvent
	public void loadPlayer(PlayerEvent.LoadFromFile event)
	{
		EnumShadowCreature creaturetype = EnumShadowCreature.SHADOWHUNTER;
		PlayerProperties props = PlayerProperties.get(event.entityPlayer);
		try {
			creaturetype = EnumShadowCreature.valueOf(FileUtils.readFileToString(event.getPlayerFile("sctype")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(creaturetype != null)
		{
			props.setCreatureType(creaturetype);
		}
	}
	
	@SubscribeEvent
	public void savePlayer(PlayerEvent.SaveToFile event)
	{
		PlayerProperties props = PlayerProperties.get(event.entityPlayer);
		try {
			FileUtils.touch(event.getPlayerFile("sctype"));
			FileUtils.writeStringToFile(event.getPlayerFile("sctype"), props.getCreatureType().name(), false);
		} catch (FileNotFoundException fnf) {
			System.out.println("[SHadowCreatures] No saved Data found for " + event.entityPlayer.getDisplayNameString() + ". Creating new File.");
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}
