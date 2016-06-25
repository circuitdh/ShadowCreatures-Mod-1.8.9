package upcraftlp.shadowcreatures.init;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import upcraftlp.shadowcreatures.entity.EntityVampireHuman;
import upcraftlp.shadowcreatures.entity.EntityWolfHuman;
import upcraftlp.shadowcreatures.render.RenderVampireHuman;
import upcraftlp.shadowcreatures.render.RenderWolfHuman;
import upcraftlp.shadowcreatures.util.EntityCreator;

public class ShadowCreatures {

	public static BiomeGenBase[] biomesVampire = new BiomeGenBase[] {BiomeGenBase.plains, BiomeGenBase.savanna, BiomeGenBase.river};
	public static BiomeGenBase[] biomesWolf = new BiomeGenBase[] {};
	
	public static void create()
	{
		EntityCreator.createEntity(EntityVampireHuman.class, new RenderVampireHuman(), "Vampire", EnumCreatureType.MONSTER, 10, 0, 4, biomesVampire, 0x0c0040, 0xfdff00);
		EntityCreator.createEntity(EntityWolfHuman.class, new RenderWolfHuman(), "Werewolf", EnumCreatureType.MONSTER, 10, 0, 4, biomesWolf, 0x0c0040, 0xfdff00);
	}
	
}
