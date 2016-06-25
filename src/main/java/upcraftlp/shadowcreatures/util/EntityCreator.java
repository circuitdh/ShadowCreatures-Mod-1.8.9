package upcraftlp.shadowcreatures.util;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import upcraftlp.shadowcreatures.ShadowCreaturesMod;
import upcraftlp.shadowcreatures.init.ShadowConfig;

public class EntityCreator {
	
	@SuppressWarnings({ "deprecation" })
	public static final void createEntity(Class<? extends Entity> entityClass, Render<? extends Entity> render, String entityName, EnumCreatureType creature, int spawnChance, int minSpawn, int maxSpawn, BiomeGenBase[] biomes, int primaryColor, int secondaryColor)
	{
		int id = ShadowConfig.getEntityID();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
		EntityRegistry.addSpawn(entityName, spawnChance, minSpawn, maxSpawn, creature, biomes);
		EntityRegistry.registerModEntity(entityClass, entityName, id, ShadowCreaturesMod.instance, 32, 3, true, primaryColor, secondaryColor);
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}
}
