package upcraftlp.shadowcreatures.events;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import upcraftlp.shadowcreatures.entity.EntityWolfHuman;
import upcraftlp.shadowcreatures.entity.EnumShadowCreature;
import upcraftlp.shadowcreatures.util.PlayerProperties;

public class WerewolfEvents {

	boolean hasShownMoon = false;
	boolean hasShownNoMoon = true;
	
	@SubscribeEvent
	public void beWerewolf(LivingUpdateEvent event)
	{
		
		if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		{
			PlayerProperties props = PlayerProperties.get((EntityPlayer) event.entity);
			EntityPlayer player = (EntityPlayer) event.entity;
			if(props.getCreatureType() == EnumShadowCreature.WEREWOLF)
			{
				if(event.entity.worldObj.getCurrentMoonPhaseFactor() == 1.0f && event.entity.worldObj.getWorldTime() > 13048 && event.entity.worldObj.getWorldTime() < 22925)
				{
					if(!hasShownMoon)
					{
						//TODO Werewolf morph
						hasShownMoon = true;
						hasShownNoMoon = false;
						player.addChatComponentMessage(new ChatComponentText("The full moon rises!"));
						player.addPotionEffect(new PotionEffect(Potion.absorption.getId(), 7*60*20, 10, false, false)); //7*60*2 = 7 Minutes = 1 Night;
					}
					
					player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 40, 2, false, false));
					player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 40, 19, false, false));
					player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 40, 3, false, false));
					player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 40, 1, false, false));
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 40, 3, false, false));
					player.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 40, 3, false, false));
				}
				else
				{
					if(!hasShownNoMoon)
					{
						hasShownNoMoon = true;
						hasShownMoon = false;
						player.addChatComponentMessage(new ChatComponentText("Full moon is over!"));
					}
					player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 40, 2, false, false));
				}
			}
			else
			{
				if(hasShownMoon || !hasShownNoMoon)
				{
					hasShownMoon = false;
					hasShownNoMoon = true;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void getWerewolf(LivingAttackEvent event)
	{
		if(event.entity instanceof EntityPlayer && event.source.getEntity() instanceof EntityWolfHuman && !event.entity.worldObj.isRemote)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			PlayerProperties props = PlayerProperties.get(player);
			if(props.getCreatureType() != EnumShadowCreature.WEREWOLF && props.getCreatureType() != EnumShadowCreature.VAMPIRE)
			{
				Random random = new Random();
				if(random.nextInt(100) == 0)
				{
					player.addChatComponentMessage(new ChatComponentText("You've been infected with lycanthrophy"));
					props.setCreatureType(EnumShadowCreature.WEREWOLF);
				}
			}
		}
	}
	
}
