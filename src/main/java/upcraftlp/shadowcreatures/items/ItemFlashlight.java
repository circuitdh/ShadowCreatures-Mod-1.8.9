package upcraftlp.shadowcreatures.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemFlashlight extends BasicItem {

	public ItemFlashlight() {
		super("lightstone");
		this.setFull3D();
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Lights up your way!");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

}
