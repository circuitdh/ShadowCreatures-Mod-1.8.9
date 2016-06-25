package upcraftlp.shadowcreatures.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import upcraftlp.shadowcreatures.init.ShadowItems;

public class ShapedCrafting {

	public static void init()
	{
		//Iron Rod
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ShadowItems.iron_rod, 1), "I", "I", 'I', "ingotIron"));
		
		//Inscribing Tool
		GameRegistry.addRecipe(new ItemStack(ShadowItems.crystalpen, 1), " C", "R ", 'C', new ItemStack(ShadowItems.crystal, 1), 'R', new ItemStack(ShadowItems.iron_rod, 1));
		
		//TODO Angel Sword [TEMP]
		GameRegistry.addRecipe(new ItemStack(ShadowItems.angelsword, 1), " R ", " R ", "CSC", 'C', new ItemStack(ShadowItems.crystal, 1), 'R', new ItemStack(ShadowItems.iron_rod, 1), 'S', new ItemStack(Items.stick, 1));
		
	}
	
}
