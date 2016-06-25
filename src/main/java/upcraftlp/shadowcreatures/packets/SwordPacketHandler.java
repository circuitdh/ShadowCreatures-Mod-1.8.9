package upcraftlp.shadowcreatures.packets;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import upcraftlp.shadowcreatures.entity.EntitySword;

public class SwordPacketHandler implements IMessageHandler<SwordMessage, IMessage> {

	@Override
	public IMessage onMessage(SwordMessage message, MessageContext ctx) {
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				EntitySword entitySword = new EntitySword(player.worldObj, (EntityLivingBase) player, player.getHeldItem());
				player.worldObj.spawnEntityInWorld(entitySword);
				player.replaceItemInInventory(player.inventory.currentItem, null);
		return null;
	}
}
