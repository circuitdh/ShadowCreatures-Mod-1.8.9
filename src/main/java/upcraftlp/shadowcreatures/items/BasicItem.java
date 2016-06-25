package upcraftlp.shadowcreatures.items;

import net.minecraft.item.Item;
import upcraftlp.shadowcreatures.init.ShadowTabs;

public class BasicItem extends Item {
	
	public BasicItem(String name)
	{
		super();
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(ShadowTabs.tabShadowHunter);
	}
	
}
