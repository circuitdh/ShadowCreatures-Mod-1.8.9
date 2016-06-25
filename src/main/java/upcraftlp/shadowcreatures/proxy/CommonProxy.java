package upcraftlp.shadowcreatures.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import upcraftlp.shadowcreatures.crafting.AltarCrafting;
import upcraftlp.shadowcreatures.crafting.CauldronCrafting;
import upcraftlp.shadowcreatures.crafting.RuneCrafting;
import upcraftlp.shadowcreatures.crafting.ShapedCrafting;
import upcraftlp.shadowcreatures.crafting.ShapelessCrafting;
import upcraftlp.shadowcreatures.init.GhostDimension;
import upcraftlp.shadowcreatures.init.ShadowBiomes;
import upcraftlp.shadowcreatures.init.ShadowBlocks;
import upcraftlp.shadowcreatures.init.ShadowCreatures;
import upcraftlp.shadowcreatures.init.ShadowDimension;
import upcraftlp.shadowcreatures.init.ShadowEntities;
import upcraftlp.shadowcreatures.init.ShadowEvents;
import upcraftlp.shadowcreatures.init.ShadowFluids;
import upcraftlp.shadowcreatures.init.ShadowItems;
import upcraftlp.shadowcreatures.init.ShadowMisc;
import upcraftlp.shadowcreatures.init.ShadowPotions;
import upcraftlp.shadowcreatures.init.ShadowRunes;
import upcraftlp.shadowcreatures.util.KeyBindings;
import upcraftlp.shadowcreatures.util.NetworkHelper;
import upcraftlp.shadowcreatures.world.WorldGen;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event)
	{
		//Client-Server-Networking
		NetworkHelper.init();
		
		//Items, Blocks, Fluids and everything basic
		ShadowItems.create();
		ShadowBlocks.create();
		ShadowRunes.create();
		
		ShadowItems.register();
		ShadowBlocks.register();
		ShadowRunes.register();
		
		ShadowPotions.create();
		ShadowFluids.create();
		
		//Miscelleanous and OreDictionary
		ShadowMisc.init();
		ShadowMisc.oreDict();
		
		//Entities
		ShadowEntities.create();
	}
	
	public void init(FMLInitializationEvent event)
	{
		//World Generation
		GameRegistry.registerWorldGenerator(new WorldGen(), 0);
		
		//Biomes and Dimensions
		ShadowBiomes.create();
		ShadowBiomes.register();
		ShadowDimension.init();
		GhostDimension.init();
		
		//Crafting etc.
		ShapedCrafting.init();
		ShapelessCrafting.init();
		RuneCrafting.init();
		AltarCrafting.init();
		CauldronCrafting.init();
		
		//Entities and Events
		ShadowCreatures.create();
		ShadowEvents.init();
		KeyBindings.initKeys();
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		//Inter-Mod-Stuff
		//Recipe removing
	}
}
