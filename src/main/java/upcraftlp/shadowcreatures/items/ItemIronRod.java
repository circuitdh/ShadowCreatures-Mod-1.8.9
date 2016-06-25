package upcraftlp.shadowcreatures.items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.init.ShadowTabs;

public class ItemIronRod extends ItemSword{
	
	private static final String name = "iron_rod";
	
	public ItemIronRod() {
		super(ToolMaterial.STONE);
		this.setNoRepair();
		this.canRepair = false;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(ShadowTabs.tabShadowHunter);
		
	}

	@Override
	public boolean canHarvestBlock(Block blockIn) {
		return true;
	}
	
	@Override
	public int getDamage(ItemStack stack) {
		return 0;
	}
	
	@Override
	public boolean isDamaged(ItemStack stack) {
		return false;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		return itemStackIn;
	}

}
