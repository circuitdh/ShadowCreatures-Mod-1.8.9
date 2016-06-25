package upcraftlp.shadowcreatures.items;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.init.ShadowTabs;

public class ItemLegendarySword extends ItemSword {
	
	private static final String name = "legendary_sword";
	
	public ItemLegendarySword() {
		super(ShadowMaterials.ARC_ANGEL);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setNoRepair();
		this.setMaxStackSize(1);
		this.setCreativeTab(ShadowTabs.tabShadowHunter);
		
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		float rangeXZ = 3.5f;
		float rangeY = 1.2f;
		List<Entity> entities = entity.worldObj.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(entity.posX - rangeXZ, entity.posY - rangeY, entity.posZ - rangeXZ, entity.posX + rangeXZ, entity.posY + rangeY, entity.posZ + rangeXZ));
		entities.remove(entity);
		if(player.shouldHeal() && entity instanceof EntityLivingBase)
		{
			EntityLivingBase entityLiving = (EntityLivingBase) entity;
			float health = this.getDamageVsEntity();
			if(entityLiving.getHealth() < this.getDamageVsEntity() && entityLiving.getHealth() >= 0) health = entityLiving.getHealth();
			player.heal(health);
		}
		
		Iterator<Entity> i  = entities.iterator();
		while(i.hasNext())
		{
			Entity currentEntity = i.next();
			if(currentEntity != player && currentEntity instanceof EntityLivingBase)
			{
				EntityLivingBase livingEntity = (EntityLivingBase) currentEntity;
				if(player.shouldHeal())
				{
					player.heal(livingEntity.getHealth() / 4);
				}
				livingEntity.addPotionEffect(new PotionEffect(Potion.wither.id, 20 * 7, 0, false, false));
				currentEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), this.getDamageVsEntity() / 12);
			}
		}
		
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.isRemote) return;
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if(!player.capabilities.isCreativeMode)
			{
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 20 * 15, 2, false, false));
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 20 * 15, 0, false, false));
			}
		}
		else
		{
			if(world.canBlockSeeSky(entity.getPosition()))
			{
				EntityLightningBolt light = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ);
				//world.spawnEntityInWorld(light);
				world.addWeatherEffect(light);
				entity.attackEntityFrom(DamageSource.lightningBolt, 300.0f);
			}
			else
			{
				entity.attackEntityFrom(DamageSource.magic, 500.0f);
			}
			
		}
		
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(EnumChatFormatting.DARK_PURPLE + "Sword of Arc Angel Raziel");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
