package upcraftlp.shadowcreatures.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import upcraftlp.shadowcreatures.init.ShadowBlocks;
import upcraftlp.shadowcreatures.init.ShadowEntities;
import upcraftlp.shadowcreatures.init.ShadowFluids;
import upcraftlp.shadowcreatures.init.ShadowItems;
import upcraftlp.shadowcreatures.init.ShadowRunes;

public class ClientProxy extends CommonProxy {
	
	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ShadowEntities.createRenders();
		super.preInit(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		ShadowBlocks.registerBlockRenders();
		ShadowItems.registerItemRenders();
		ShadowRunes.registerRuneRenders();
		ShadowFluids.registerFluidBuckets();
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
	
	
}
