package upcraftlp.shadowcreatures.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionHolyWater extends Potion {

	private static final ResourceLocation ICON = new ResourceLocation("sc:textures/gui/potions.png");
	
	public PotionHolyWater() {
		super(new ResourceLocation("holy_water"), false, 0xc10606);
		
	}

	@Override
	public String getName() {
		return "potion.holy_water.name";
	}
	
	@Override
	public boolean hasStatusIcon() {
		return false;
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		mc.renderEngine.bindTexture(ICON);
		mc.currentScreen.drawTexturedModalRect(x + 8, y + 8, 16, 0, 16, 16);
	}
	
	public static void performEffect(EntityLivingBase entityLivingBaseIn)
	{
		
	}
}
