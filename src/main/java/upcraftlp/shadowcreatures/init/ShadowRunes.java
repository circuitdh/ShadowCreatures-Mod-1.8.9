package upcraftlp.shadowcreatures.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import upcraftlp.shadowcreatures.items.BasicRune;

public class ShadowRunes {
	
	private static final HashMap<Item, String> runes = new HashMap<Item, String>();
	public static Item rune_blank = new BasicRune("rune_blank");
	
	public static void create()
	{
		runes.put(rune_blank, "rune_blank");
	}
	
	public static void register()
	{
		Set<Item> keys = runes.keySet();
		for(Iterator<Item> i = keys.iterator(); i.hasNext();)
		{
			Item currentRune = i.next();
			GameRegistry.registerItem(currentRune, runes.get(currentRune));
		}
	}
	
	public static void registerRuneRenders()
	{
		Set<Item> keys = runes.keySet();
		for(Iterator<Item> i = keys.iterator(); i.hasNext();)
		{
			Item currentRune = i.next();
			ShadowItems.registerRender(currentRune);
		}
		
	}
}
