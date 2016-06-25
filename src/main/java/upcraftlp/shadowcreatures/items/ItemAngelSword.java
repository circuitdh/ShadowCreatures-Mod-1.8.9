package upcraftlp.shadowcreatures.items;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import upcraftlp.shadowcreatures.init.ShadowMisc;
import upcraftlp.shadowcreatures.util.KeyBindings;

public class ItemAngelSword extends ItemSword
{
	
	private static final String name = "namedangelsword";
	Random random = new Random();
	
	public ItemAngelSword()
	{
		super(ShadowMaterials.ANGEL);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setNoRepair();
		this.setMaxDamage(19);
		this.canRepair = false;
		this.setCreativeTab(null);
		
	}	
		
	public boolean hasColor(ItemStack itemStack)
	{
		return true;
	}
		
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int par2)
	{
		if(itemStack.getTagCompound() != null)
		{
			NBTTagCompound nbtData = (NBTTagCompound) itemStack.getTagCompound().getTag("data");
				return nbtData.getInteger("color");
		}
		else
		{
			return 0x000000;
		}
	}
	
	
	@Override
	public Item setHasSubtypes(boolean hasSubtypes) {
		return null;
	}
	
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		
		NBTTagCompound nbt = new NBTTagCompound();
		if(itemStack.getTagCompound() == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
			nbt.setInteger("color", ShadowMisc.swordColors.get(random.nextInt(ShadowMisc.swordColors.size())));
			nbt.setString("name", EnumChatFormatting.DARK_PURPLE + ShadowMisc.swordNames.get(random.nextInt(ShadowMisc.swordNames.size())));
			itemStack.getTagCompound().setTag("data", nbt);			
		}
		NBTTagCompound nbtNew = (NBTTagCompound) itemStack.getTagCompound().getTag("data");
		itemStack.setStackDisplayName(nbtNew.getString("name"));		
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("The Blade is glowing...");
		tooltip.add("Press " + Keyboard.getKeyName(KeyBindings.throwSword.getKeyCode()) + " to throw.");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	
}
