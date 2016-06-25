package upcraftlp.shadowcreatures.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.util.PlayerProperties;

public class EntityVampireHuman extends EntityMob {
	
	public EntityVampireHuman(World worldIn) {
		super(worldIn);
				
        this.setAITasks();
        this.setSprinting(false);
        this.setSize(0.6F, 1.8F);
		
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
	}
	
	public void setAITasks()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 3.0D, false));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityVillager.class, 3.0D, false));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityWolf.class, 3.0D, true));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityWolfHuman.class, 3.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.7D));
        this.tasks.addTask(7, new EntityAIWander(this, 0.7D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityWolf.class, 20.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityWolfHuman.class, 20.0F));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityWolf>(this, EntityWolf.class, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityWolfHuman>(this, EntityWolfHuman.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, true));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.5D, false));
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(worldObj.getWorldTime() > 22925 || worldObj.getWorldTime() < 13048)
		{
			if(worldObj.canBlockSeeSky(this.getPosition()) && worldObj.provider.getDimensionId() != -1 && worldObj.provider.getDimensionId() != 1)
			{
				//Burn them vampires!
				this.setFire(1);
			}
		}
		if(this.getAITarget() instanceof EntityPlayer)
		{
			System.out.println("okay");
			EntityPlayer player = (EntityPlayer) this.getAITarget();
			PlayerProperties props = PlayerProperties.get(player);
			if(props.getCreatureType() == EnumShadowCreature.VAMPIRE) this.navigator.clearPathEntity();
		}
		
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if(entity instanceof EntityPlayer)
		{
			PlayerProperties props = PlayerProperties.get((EntityPlayer) entity);
			if(props.getCreatureType() == EnumShadowCreature.VAMPIRE)
			{
				return super.attackEntityAsMob(entity);
			}
		}
		super.attackEntityAsMob(entity);
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1);
	}
	
}
