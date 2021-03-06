package upcraftlp.shadowcreatures.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionBlood extends Potion {

	private static final ResourceLocation ICON = new ResourceLocation("sc:textures/gui/potions.png");
	
	public PotionBlood() {
		super(new ResourceLocation("blood"), false, 0xc10606);
		
	}

	@Override
	public String getName() {
		return "potion.blood.name";
	}
	
	@Override
	public boolean hasStatusIcon() {
		return true;
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		mc.renderEngine.bindTexture(ICON);
		mc.currentScreen.drawTexturedModalRect(x + 8, y + 8, 0, 0, 16, 16);
	}
	
	public static void performEffect(EntityLivingBase entityLivingBaseIn)
	{
		if(entityLivingBaseIn instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
			if(!player.getActivePotionEffects().contains(damageBoost))
			{
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2, 3));
			}
		}
	}
	
}
