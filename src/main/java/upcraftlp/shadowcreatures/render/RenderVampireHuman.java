package upcraftlp.shadowcreatures.render;



import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import upcraftlp.shadowcreatures.entity.EntityVampireHuman;
import upcraftlp.shadowcreatures.entity.models.ModelVampireHuman;

public class RenderVampireHuman extends RenderLiving<EntityVampireHuman> {

	public RenderVampireHuman() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelVampireHuman(), 0);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityVampireHuman entity) {
		return new ResourceLocation("sc:textures/entity/VampireHuman.png");
	}
	
}
