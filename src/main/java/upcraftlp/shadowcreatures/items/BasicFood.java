package upcraftlp.shadowcreatures.items;

import net.minecraft.item.ItemFood;
import upcraftlp.shadowcreatures.init.ShadowTabs;

public class BasicFood extends ItemFood {
	public BasicFood(String name, int healAmount, float saturationModifier, boolean petMeat)
	{
		super(healAmount, saturationModifier, petMeat);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(ShadowTabs.tabShadowHunter);
	}
}
