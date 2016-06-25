package upcraftlp.shadowcreatures.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.entity.EnumShadowCreature;
import upcraftlp.shadowcreatures.util.PlayerProperties;

public class ItemFlesh extends BasicFood{

	public ItemFlesh() {
		super("flesh", 10, 0.9f, true);
		this.setAlwaysEdible();
		
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote)
		{
			PlayerProperties props = PlayerProperties.get(player);
			if(props.getCreatureType() == EnumShadowCreature.WEREWOLF)
			{
				player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 1200, 23));
				
			}
			else
			{
				player.addPotionEffect(new PotionEffect(Potion.hunger.getId(), 6000, 127));
				player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 3000, 2));
				player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 12000, 31));
				
			}
			super.onFoodEaten(stack, worldIn, player);
		}
		
		
	}

}
