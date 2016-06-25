package upcraftlp.shadowcreatures.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import upcraftlp.shadowcreatures.items.BasicItem;
import upcraftlp.shadowcreatures.items.ItemAngelSword;
import upcraftlp.shadowcreatures.items.ItemBlade;
import upcraftlp.shadowcreatures.items.ItemBomb;
import upcraftlp.shadowcreatures.items.ItemFlashlight;
import upcraftlp.shadowcreatures.items.ItemFlesh;
import upcraftlp.shadowcreatures.items.ItemHolyWater;
import upcraftlp.shadowcreatures.items.ItemInscriber;
import upcraftlp.shadowcreatures.items.ItemIronRod;
import upcraftlp.shadowcreatures.items.ItemLegendarySword;



public class ShadowItems {
	
	private static final HashMap<Item, String> items = new HashMap<Item, String>();
	
	public static Item stardust = new BasicItem("stardust");
	public static Item fairydust = new BasicItem("fairydust");
	public static Item silverdust = new BasicItem("silverdust");
	public static Item bottledholywater = new ItemHolyWater();
	public static Item mortar = new BasicItem("mortar");
	public static Item lightstone = new ItemFlashlight();
	public static Item crystalpen = new ItemInscriber();
	public static Item crystal = new BasicItem("crystal").setFull3D();
	public static Item iron_rod = new ItemIronRod();
	public static Item angelsword = new ItemBlade();
	public static Item namedangelsword = new ItemAngelSword();
	public static Item flesh = new ItemFlesh();
	public static Item legendarySword = new ItemLegendarySword();
	public static Item bomb = new ItemBomb();
	
	public static void create()
	{
		items.put(legendarySword, "legendary_sword");
		items.put(flesh, "flesh");				
		items.put(angelsword, "angelsword");
		items.put(namedangelsword, "namedangelsword");
		items.put(stardust, "stardust");
		items.put(fairydust, "fairydust");
		items.put(silverdust, "silverdust");
		items.put(mortar, "mortar");
		items.put(lightstone, "lightstone");
		items.put(crystalpen, "crystalpen");
		items.put(crystal, "crystal");
		items.put(iron_rod, "iron_rod");
		items.put(bottledholywater, "bottled_holy_water");
		items.put(bomb, "bomb");
	}
	
	public static void register()
	{
		Set<Item> keys = items.keySet();
		for(Iterator<Item> i = keys.iterator(); i.hasNext();)
		{
			Item currentItem = i.next();
			GameRegistry.registerItem(currentItem, items.get(currentItem));
		}
	}
	
	public static void registerItemRenders()
	{
		Set<Item> keys = items.keySet();
		for(Iterator<Item> i = keys.iterator(); i.hasNext();)
		{
			Item currentItem = i.next();
			registerRender(currentItem);
		}
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
