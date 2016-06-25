package upcraftlp.shadowcreatures.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHolyWater extends BasicItem{
	
	public ItemHolyWater() {
		super("bottled_holy_water");
		this.setMaxStackSize(1);
		this.hasSubtypes = true;
		this.setMaxDamage(0);
		this.setFull3D();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		//worldIn.spawnEntityInWorld(entityIn);
		ItemStack stack = itemStackIn.copy();
		if (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode)
		{
			stack.stackSize--;
		}
		
		
		
		return stack;
	}

	
}
