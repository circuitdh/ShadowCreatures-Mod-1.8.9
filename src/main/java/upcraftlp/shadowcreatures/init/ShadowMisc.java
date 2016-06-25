package upcraftlp.shadowcreatures.init;

import java.util.ArrayList;

import net.minecraftforge.oredict.OreDictionary;

public class ShadowMisc {

	public static void init()
	{
		swords();
	}
	public static ArrayList<Integer> swordColors = new ArrayList<Integer>();
	public static ArrayList<String> swordNames = new ArrayList<String>();
	
	public static void swords()
	{
		//SwordNames
		
		swordNames.add("Abrariel");
		swordNames.add("Adriel");
		swordNames.add("Amriel");
		swordNames.add("Arariel");
		swordNames.add("Asmodel");
		swordNames.add("Atheed");
		swordNames.add("Barachiel");
		swordNames.add("Camael");
		swordNames.add("Cassiel");
		swordNames.add("Dumah");
		swordNames.add("Eremiel");
		//swordNames.add("Gabriel");
		swordNames.add("Gadreel");
		swordNames.add("Gagiel");
		swordNames.add("Hadraniel");
		swordNames.add("Haniel");
		swordNames.add("Harahel");
		swordNames.add("Harut");
		swordNames.add("Israfiel");
		swordNames.add("Ithuriel");
		swordNames.add("Jahoel");
		swordNames.add("Jegudiel");
		swordNames.add("Jehuel");
		swordNames.add("Jerahmeel");
		swordNames.add("Jophiel");
		swordNames.add("Khamael");
		swordNames.add("Lailah");
		swordNames.add("Malik");
		swordNames.add("Marut");
		swordNames.add("Metatron");
		//swordNames.add("Michael");
		swordNames.add("Moroni");
		swordNames.add("Munkar");
		swordNames.add("Muriel");
		swordNames.add("Nakir");
		swordNames.add("Nuriel");
		swordNames.add("Pahaliah");
		swordNames.add("Penemue");
		swordNames.add("Peniel");
		swordNames.add("Puriel");
		swordNames.add("Raguel");
		//swordNames.add("Raphael");
		swordNames.add("Raqeeb");
		//swordNames.add("Raziel");
		swordNames.add("Remiel");
		swordNames.add("Ridwan");
		swordNames.add("Sachiel");
		swordNames.add("Samandiriel");
		swordNames.add("Sandolphon");
		swordNames.add("Sansanvi");
		swordNames.add("Sanvi");
		swordNames.add("Saraqael");
		swordNames.add("Sealtiel");
		swordNames.add("Semangelaf");
		swordNames.add("Shamsiel");
		swordNames.add("Taharial");
		swordNames.add("Telantes");
		//swordNames.add("Uriel");
		swordNames.add("Yahoel");
		swordNames.add("Zadkiel");
		swordNames.add("Zaphkiel");
		
		//Sword Colors
		swordColors.add(0xe6e6fa);
		swordColors.add(0xc6e2ff);
		swordColors.add(0xd3ffce);
		swordColors.add(0xffe4e1);
		swordColors.add(0xffc67f);
		swordColors.add(0x6195a0);
		swordColors.add(0xff8c00);
		swordColors.add(0xf0aa17);
		swordColors.add(0x743d74);
		swordColors.add(0x450303);
		swordColors.add(0x7a7a48);
		swordColors.add(0x6f7048);
		swordColors.add(0x530179);
		swordColors.add(0x5c1465);
		swordColors.add(0x673888);
		swordColors.add(0xd4ffea);
		swordColors.add(0x00e7eb);
		swordColors.add(0xa104ee);
		swordColors.add(0xe7ae18);
		swordColors.add(0x00ffab);
		swordColors.add(0xffed5d);
		swordColors.add(0xffef6e);
		swordColors.add(0x64cdcf);
		swordColors.add(0x990067);
		swordColors.add(0x70e4e7);
		
	}
	
	public static void oreDict()
	{
		//items
		OreDictionary.registerOre("dustStar", ShadowItems.stardust);
		OreDictionary.registerOre("dustSilver", ShadowItems.silverdust);
		OreDictionary.registerOre("dustFairy", ShadowItems.fairydust);
		OreDictionary.registerOre("gemMagicCrystal", ShadowItems.crystal);
		OreDictionary.registerOre("gemMagic", ShadowItems.crystal);
		OreDictionary.registerOre("gemCrystal", ShadowItems.crystal);
		OreDictionary.registerOre("stickIron", ShadowItems.iron_rod);
		OreDictionary.registerOre("rodIron", ShadowItems.iron_rod);
		
		//blocks
		OreDictionary.registerOre("oreMagicCrystal", ShadowBlocks.crystalore);
		OreDictionary.registerOre("oreMagic", ShadowBlocks.crystalore);
		OreDictionary.registerOre("oreCrystal", ShadowBlocks.crystalore);
		
		//runes
		OreDictionary.registerOre("rune", ShadowRunes.rune_blank);
		OreDictionary.registerOre("runeBasic", ShadowRunes.rune_blank);
		OreDictionary.registerOre("runeBlank", ShadowRunes.rune_blank);
	}
}
