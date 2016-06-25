package upcraftlp.shadowcreatures.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import upcraftlp.shadowcreatures.entity.EntityVampireHuman;
import upcraftlp.shadowcreatures.entity.EnumShadowCreature;
import upcraftlp.shadowcreatures.util.PlayerProperties;

public class VampireEvents {

	@SubscribeEvent
	public void getVampire(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityPlayer && event.source.getEntity() instanceof EntityVampireHuman)
		{
			PlayerProperties props = PlayerProperties.get((EntityPlayer) event.entity);
			if(props.getCreatureType() == EnumShadowCreature.SHADOWHUNTER)
			props.setCreatureType(EnumShadowCreature.VAMPIRE);
		}
		
	}
	
	@SubscribeEvent
	public void beVampire(LivingUpdateEvent event)
	{
		if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		{
			PlayerProperties props = PlayerProperties.get((EntityPlayer) event.entity);
			EntityPlayer player = (EntityPlayer) event.entity;
			if(props.getCreatureType() == EnumShadowCreature.VAMPIRE)
			{
				if(event.entity.worldObj.getWorldTime() > 22925 || event.entity.worldObj.getWorldTime() < 13048)
				{
					if(event.entity.worldObj.canBlockSeeSky(event.entity.getPosition()) && event.entity.worldObj.provider.getDimensionId() != -1 && event.entity.worldObj.provider.getDimensionId() != 1)
					{
						
						if(player.isInWater())
						{
							player.attackEntityFrom(DamageSource.inFire, 7.5f);
						}
						if(player.isInLava())
						{
							player.attackEntityFrom(DamageSource.inFire, 20.0f);
						}
						if(!player.isWet())
						{
							player.setFire(1);
							player.attackEntityFrom(DamageSource.inFire, 1.5f);
						}
						
					}
					if(!event.entity.worldObj.canBlockSeeSky(event.entity.getPosition()) && event.entity.dimension != -1) player.extinguish();
				}
				else
				{
						player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 40, 3, false, false));
				}
			}
		}
	}
}
