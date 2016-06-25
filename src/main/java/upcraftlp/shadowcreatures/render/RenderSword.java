package upcraftlp.shadowcreatures.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import upcraftlp.shadowcreatures.entity.EntitySword;
import upcraftlp.shadowcreatures.init.ShadowItems;

public class RenderSword extends Render<EntitySword>{

	private final Item sword;
	
	public RenderSword(RenderManager renderManager) {
		super(renderManager);
		this.sword = ShadowItems.angelsword;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySword entity) {
		return TextureMap.locationBlocksTexture;
	}
	
	@Override
	public void doRender(EntitySword entity, double x, double y, double z, float entityYaw, float partialTicks) {
		ItemStack itemStack = new ItemStack(this.sword, 1);
		Minecraft.getMinecraft().getRenderItem().renderItem(itemStack, Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(itemStack));
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	

}
