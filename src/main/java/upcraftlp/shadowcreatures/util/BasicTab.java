package upcraftlp.shadowcreatures.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import upcraftlp.shadowcreatures.init.ShadowItems;
import upcraftlp.shadowcreatures.init.ShadowRunes;

public class BasicTab extends CreativeTabs {

	public BasicTab(String label) {
		super(label);
		//this.setBackgroundImageName("ShadowWorld.png");
	}

	@Override
	public Item getTabIconItem() {
		if (getTabLabel().equals("tabShadowHunter"))
		{
			return ShadowItems.lightstone;
		} 
		else
		{
			return ShadowRunes.rune_blank;	
		}
		
	}

}
