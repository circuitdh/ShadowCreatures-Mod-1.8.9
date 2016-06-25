package upcraftlp.shadowcreatures.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import upcraftlp.shadowcreatures.entity.EntityWolfHuman;
import upcraftlp.shadowcreatures.entity.models.ModelWolfHuman;

public class RenderWolfHuman extends RenderLiving<EntityWolfHuman> {

	public RenderWolfHuman() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelWolfHuman(), 0);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWolfHuman entity) {
		return new ResourceLocation("sc:textures/entity/WolfHuman.png");
	}
	
}
