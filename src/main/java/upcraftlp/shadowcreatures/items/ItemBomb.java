package upcraftlp.shadowcreatures.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.entity.EntityBomb;

public class ItemBomb extends BasicItem {

	private int fuseTime;
	private int fuseTimeDefault = (int) (3.5 * 20); //3.5 seconds
	
	public ItemBomb() {
		super("bomb");
		this.setFull3D();
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		
			if(!playerIn.capabilities.isCreativeMode)
			{
				stack.stackSize--;
				if(stack.stackSize <= 0)
				{
					playerIn.replaceItemInInventory(playerIn.inventory.currentItem, null);
				}
				else
				{
					playerIn.replaceItemInInventory(playerIn.inventory.currentItem, stack);
				}	
			}
			if(!worldIn.isRemote)
			{
				EntityBomb grenade = new EntityBomb(worldIn, playerIn);
				grenade.fuseTicks = this.fuseTime;
				worldIn.spawnEntityInWorld(grenade);
				worldIn.playSoundAtEntity(grenade, "random.fuse", 0.7f, 0.8f);
			}
		}
		
	
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		this.fuseTime  = fuseTimeDefault;
		player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		world.playSoundAtEntity(player, "random.fizz", 0.7f, 0.8f); //TODO Trigger Sound
		world.playSoundAtEntity(player, "random.fuse", 0.7f, 0.8f);
		return itemStack;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		this.fuseTime--;
		if(this.fuseTime <= 0)
		{
			if(!player.worldObj.isRemote)
			{
				player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 4.5f, false);
				stack.stackSize--;
				if(stack.stackSize <= 0)
				{
					player.replaceItemInInventory(player.inventory.currentItem, null);
				}
				else
				{
					
					player.replaceItemInInventory(player.inventory.currentItem, stack);
				}
			}
		}
	}
	
	

	
}
