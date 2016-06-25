package upcraftlp.shadowcreatures.init;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import upcraftlp.shadowcreatures.ShadowCreaturesMod;
import upcraftlp.shadowcreatures.entity.EntityBomb;
import upcraftlp.shadowcreatures.entity.EntitySword;
import upcraftlp.shadowcreatures.render.RenderBomb;
import upcraftlp.shadowcreatures.render.RenderSword;
import upcraftlp.shadowcreatures.tile.TileEntityBrewingCauldron;

public class ShadowEntities {

	public static void create()
	{
		//TileEntityBrewingCauldron
		GameRegistry.registerTileEntity(TileEntityBrewingCauldron.class, "TileEntityShadowCauldron");
		
		
		//TODO following line causes error!
		//Thrown Sword
		EntityRegistry.registerModEntity(EntitySword.class, "swordEntity", ShadowConfig.getEntityID(), ShadowCreaturesMod.instance, 128, 30, true);
		
		//Bomb
		EntityRegistry.registerModEntity(EntityBomb.class, "bombEntity", ShadowConfig.getEntityID(), ShadowCreaturesMod.instance, 128, 30, true);
		
	
	
	
	}
	
	public static void createRenders()
	{
		//Bomb
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, RenderBomb::new);
		
		//Thrown Sword
				//RenderingRegistry.registerEntityRenderingHandler(EntityThrownSword.class, RenderThrownSword::new);
				RenderingRegistry.registerEntityRenderingHandler(EntitySword.class, RenderSword::new);
	}
	
}
