package upcraftlp.shadowcreatures.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import akka.japi.Pair;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import upcraftlp.shadowcreatures.init.ShadowBlocks;
import upcraftlp.shadowcreatures.init.ShadowItems;

public class CauldronCrafting {

	private static HashMap<Pair<ItemStack, FluidStack>, ItemStack> recipes = new HashMap<Pair<ItemStack, FluidStack>, ItemStack>();	
	
	public static void init()
	{
		addRecipe(Item.getItemFromBlock(ShadowBlocks.crystalore), FluidRegistry.LAVA, FluidContainerRegistry.BUCKET_VOLUME * 1, ShadowItems.crystal);
		addRecipe(Item.getItemFromBlock(ShadowBlocks.crystalore_end), FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME * 1, ShadowItems.crystal);
	}
	
	public static void addRecipe(Item itemIn, Fluid fluidIn, int amount, Item itemOut)
	{
		ItemStack itemStackIn = new ItemStack(itemIn, 1);
		ItemStack itemStackOut = new ItemStack(itemOut, 1);
		FluidStack fluidStackIn = new FluidStack(fluidIn, amount);
		recipes.put(new Pair<ItemStack, FluidStack>(itemStackIn, fluidStackIn), itemStackOut);
		
	}
	
	public static void verify(InventoryBasic inventory, World world, EntityPlayer player, FluidTank tank)
	{
		FluidStack fluidStack = tank.getFluid();
		ItemStack input = inventory.getStackInSlot(0);
		if(input == null) return;
		Set<Pair<ItemStack, FluidStack>> keys = recipes.keySet();
		
		for(Iterator<Pair<ItemStack, FluidStack>> i = keys.iterator(); i.hasNext();)
		{
			Pair<ItemStack, FluidStack> pair = i.next();
			if(ItemStack.areItemsEqual(input, pair.first()) && fluidStack.containsFluid(pair.second()))
			{
				fluidStack.amount -= pair.second().amount;
				inventory.clear();
				if(fluidStack.amount > 0)
				{
					tank.setFluid(fluidStack);
				}
				else
				{
					tank.setFluid(null);
				}
				if(!world.isRemote)
				{
					
					ItemStack output = recipes.get(pair);
					EntityItem itemOut = new EntityItem(world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), output.copy());
					Random random = new Random();
					itemOut.playSound("random.bow", 100, random.nextInt(100));
					world.spawnEntityInWorld(itemOut);
					
				}
				break;
			}
		}
	}
}
