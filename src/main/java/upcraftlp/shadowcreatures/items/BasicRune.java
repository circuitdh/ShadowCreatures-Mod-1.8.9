package upcraftlp.shadowcreatures.items;

import net.minecraft.item.Item;
import upcraftlp.shadowcreatures.init.ShadowTabs;

public class BasicRune extends Item {
	
	public BasicRune(String name)
	{
		super();
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(ShadowTabs.tabShadowRunes);
		this.setMaxStackSize(1);
	}
	
}
