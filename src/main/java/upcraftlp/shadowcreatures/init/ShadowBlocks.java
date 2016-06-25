package upcraftlp.shadowcreatures.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import upcraftlp.shadowcreatures.blocks.BasicOre;
import upcraftlp.shadowcreatures.blocks.BlockBrewingCauldron;
import upcraftlp.shadowcreatures.blocks.BlockLevitator;
import upcraftlp.shadowcreatures.blocks.BlockMirror;
import upcraftlp.shadowcreatures.blocks.BlockRunicAltar;
import upcraftlp.shadowcreatures.blocks.BlockWeaponForge;
import upcraftlp.shadowcreatures.blocks.BlockWoolPad;

public class ShadowBlocks {
	
	private static final HashMap<Block, String> blocks = new HashMap<Block, String>();
	public static Block weaponforge = new BlockWeaponForge();
	public static Block brewing_cauldron = new BlockBrewingCauldron();
	public static Block crystalore = new BasicOre("crystalore", Material.rock, 3, ShadowItems.crystal).setLightLevel(7.0f);
	public static Block crystalore_end = new BasicOre("crystalore_end", Material.rock, 3, ShadowItems.crystal).setLightLevel(11.0f);
	public static Block mirror_top = new BlockMirror("top");
	public static Block mirror_base = new BlockMirror("bottom");
	public static Block runic_altar = new BlockRunicAltar();
	public static Block wool_pad = new BlockWoolPad();
	public static Block levitator = new BlockLevitator();
	
	
	public static void create()
	{
		blocks.put(runic_altar, "runic_altar");
		blocks.put(mirror_top, "mirror_top");
		blocks.put(mirror_base, "mirror_base");
		blocks.put(crystalore, "crystalore");
		blocks.put(crystalore_end, "crystalore_end");
		blocks.put(weaponforge, "weaponforge");
		blocks.put(brewing_cauldron, "brewing_cauldron");
		blocks.put(levitator, "levitator");
		blocks.put(wool_pad, "wool_pad");
		
	}
	
	public static void register()
	{
		Set<Block> keys = blocks.keySet();
		for(Iterator<Block> i = keys.iterator(); i.hasNext();)
		{
			Block currentBlock = i.next();
			GameRegistry.registerBlock(currentBlock, blocks.get(currentBlock));
		}
		
	}
	
	
	public static void registerBlockRenders()
	{
		Set<Block> keys = blocks.keySet();
		for(Iterator<Block> i = keys.iterator(); i.hasNext();)
		{
			Item currentBlockItem = Item.getItemFromBlock(i.next());
			ShadowItems.registerRender(currentBlockItem);
		}
	}
}
