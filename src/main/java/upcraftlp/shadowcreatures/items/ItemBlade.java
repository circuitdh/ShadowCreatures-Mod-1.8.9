package upcraftlp.shadowcreatures.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.init.ShadowItems;

public class ItemBlade extends BasicItem{
	
	
	public ItemBlade()
	{
		super("angelsword");
		this.setFull3D();
		this.setNoRepair();
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		return new ItemStack(ShadowItems.namedangelsword, 1);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Strengthen by calling it's name!");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}

