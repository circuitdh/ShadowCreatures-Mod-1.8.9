package upcraftlp.shadowcreatures.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import upcraftlp.shadowcreatures.entity.EnumShadowCreature;

public class PlayerProperties implements IExtendedEntityProperties {

	public static final String propName = "shadowProps";
	EnumShadowCreature creaturetype;
	protected EntityPlayer currentPlayer;
	protected World currentWorld;
	
	public PlayerProperties(EntityPlayer player)
	{
		this.currentPlayer = player;
		this.creaturetype = EnumShadowCreature.SHADOWHUNTER;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(propName, new PlayerProperties(player));
	}
	
	public static final PlayerProperties get(EntityPlayer player)
	{
		return (PlayerProperties) player.getExtendedProperties(propName);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("creaturetype", this.getCreatureType().name());
		compound.setTag(propName, nbt);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(propName);
		this.creaturetype = EnumShadowCreature.valueOf(nbt.getString("creaturetype"));	
	}

	public EnumShadowCreature getCreatureType()
	{
		return this.creaturetype;
	}
	
	public void setCreatureType(EnumShadowCreature newType)
	{
		this.creaturetype = newType;
	}
	
	@Override
	public void init(Entity entity, World world) {
		currentPlayer = (EntityPlayer) entity;
		currentWorld = world;
		
	}

}
