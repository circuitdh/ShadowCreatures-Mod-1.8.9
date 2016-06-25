package upcraftlp.shadowcreatures.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySword extends EntityThrowable {
	
	public EntitySword instance = this;
	private ItemStack stack;
	private int randomTilt;
	private boolean onFire;
	
	public EntitySword(World world)
	{
		super(world);
		randomTilt = rand.nextInt(360);
		this.onFire = false;
	}
	
	public EntitySword(World world, EntityLivingBase entity, ItemStack newStack)
	{
		super(world, entity);
		this.stack = newStack;
		randomTilt = rand.nextInt(360);
		this.onFire = false;
	}
		
	public EntitySword(World world, double x, double y, double z, ItemStack stack)
	{
		super(world, x, y, z);
		this.stack = stack;
		randomTilt = rand.nextInt(360);
		this.onFire = false;
	}
	
	public void setOnFire(boolean val)
	{
		this.onFire = val;
	}
	
	public boolean getOnFire()
	{
		return this.onFire;
	}
	
	public int getRandomTilt()
	{
		return randomTilt;
	}
	
	public void setRandomTilt(int angle)
	{
		randomTilt = angle;
	}

	private void InflictDamage(MovingObjectPosition pos)
	{
		pos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 20);
		if(this.getOnFire())
		{
			pos.entityHit.setFire(12);
		}
	}
	
	private void DestroySelf(Boolean spawnItem)
	{
		if(spawnItem)
		{
			EntityItem item = new EntityItem(worldObj, this.posX, this.posY + 1, this.posZ, this.getStack());
			this.worldObj.spawnEntityInWorld(item);
		}
		this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[10]);
		this.setDead();
	}
		
	protected void onImpact(MovingObjectPosition pos)
	{
		if(worldObj.isRemote) return;
		if(pos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			Block block = this.worldObj.getBlockState(pos.getBlockPos()).getBlock();
			if(block == Blocks.tallgrass || block == Blocks.vine || block == Blocks.red_flower || block == Blocks.yellow_flower || block == Blocks.brown_mushroom || block == Blocks.red_mushroom || block == Blocks.reeds|| block == Blocks.double_plant || block == Blocks.deadbush || block == Blocks.wheat || block == Blocks.carrots || block == Blocks.potatoes || block == Blocks.waterlily || block == Blocks.barrier)
			{
				BlockPos blockPos = pos.getBlockPos();
				IBlockState blockstate = this.worldObj.getBlockState(blockPos);
				TileEntity tileEntiy = this.worldObj.getTileEntity(blockPos);
				EntityPlayer player = (EntityPlayer) this.getThrower();
				this.worldObj.destroyBlock(blockPos, false);
				block.harvestBlock(this.worldObj, player, blockPos, blockstate, tileEntiy);
				
			}
			else
			{
				this.DestroySelf(true);
			}
		}
		else
		{
			if(pos.entityHit != null)
			{
				this.InflictDamage(pos);
			}
			this.DestroySelf(false);
		}
	}

	public ItemStack getStack() {
		return this.stack;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.ticksExisted > 60) this.DestroySelf(true);
	}
	
	public Item getItem()
	{
		return this.stack.getItem();
	}
}
