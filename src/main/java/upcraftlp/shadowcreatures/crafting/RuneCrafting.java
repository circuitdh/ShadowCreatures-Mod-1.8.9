package upcraftlp.shadowcreatures.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import upcraftlp.shadowcreatures.init.ShadowRunes;

public class RuneCrafting {

	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(ShadowRunes.rune_blank), " S ", "SSS", " S ", 'S', new ItemStack(Blocks.stone, 1, 0));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ShadowRunes.rune_blank), "rune"));
	}
	
}
