package upcraftlp.shadowcreatures.entity;

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
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityWolfHuman extends EntityMob {
	
	public EntityWolfHuman(World worldIn) {
		super(worldIn);
		
        this.setAITasks();
        this.setSprinting(false);
        this.setSize(0.6F, 1.95F);
		
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232523D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(60.0D);
	}
	
	public void setAITasks()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.5D, false));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityVampireHuman.class, 3.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.7D));
        this.tasks.addTask(7, new EntityAIWander(this, 0.7D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityWolf.class, 20.0F));
        if(this.worldObj.getCurrentMoonPhaseFactor() == 1.0f) this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityVampireHuman>(this, EntityVampireHuman.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityBat>(this, EntityBat.class, false));
		//TODO this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVampireBat.class, true));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
	}
	
}
