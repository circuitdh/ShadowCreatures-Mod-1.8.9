package upcraftlp.shadowcreatures.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MorphHandler {

	public static String getMorph(EntityPlayer player)
	{
		if(player.getNBTTagCompound().hasKey("morph"))
		{
			NBTTagCompound nbt = (NBTTagCompound) player.getNBTTagCompound().getTag("morph");
			return nbt.getString("morph");
		}
		else
		{
			return null;
		}
	}
	
}
