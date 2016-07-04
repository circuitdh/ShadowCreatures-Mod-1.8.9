package upcraftlp.shadowcreatures.events;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class PlayerEvents {

	public void foodOverride(PlayerUseItemEvent event)
	{
		if(event.item.getItem() instanceof ItemFood)
		{
			
			event.item.stackSize--;
			event.entityPlayer.addPotionEffect(new PotionEffect(Potion.saturation.id, 20));
			event.setCanceled(true);
		}
	}
}
