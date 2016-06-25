package upcraftlp.shadowcreatures.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBomb extends EntityThrowable {
	
	private int randomTilt;
	private int fuseTicksDefault = (int) (3.5 * 20); //3.5 seconds
	public int fuseTicks;
	
	public EntityBomb(World world)
	{
		super(world);
		randomTilt = rand.nextInt(360);
		this.isImmuneToFire = true;
		this.fuseTicks = fuseTicksDefault;
	}
	
	public EntityBomb(World world, EntityLivingBase entity)
	{
		super(world, entity);
		randomTilt = rand.nextInt(360);
		this.isImmuneToFire = true;
		this.fuseTicks = fuseTicksDefault;
	}
	
	public EntityBomb(World world, double x, double y, double z)
	{
		super(world, x, y, z);
		randomTilt = rand.nextInt(360);
		this.isImmuneToFire = true;
		this.fuseTicks = fuseTicksDefault;
	}
	
	public boolean getOnFire()
	{
		return false;
	}
	
	public int getRandomTilt()
	{
		return randomTilt;
	}
	
	public void setRandomTilt(int angle)
	{
		randomTilt = angle;
	}
	
	private void DestroySelf()
	{
		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 4.5f, false);
		this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		this.setDead();
	}
	
	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}
	
	@Override
	public boolean canRenderOnFire() {
		return false;
	}
	
	@Override
	public void onUpdate() {
		
		if(this.fuseTicks <= 0)
		{
			if(this.worldObj.isRemote) return;
			this.DestroySelf();
		}
		else
		{
			this.fuseTicks--;
			if(this.ticksExisted % 5 == 0) this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		}
		super.onUpdate();
	}
	
	public void setFuseTicks(int fuseTicks) {
		this.fuseTicks = fuseTicks;
	}
	
	public int getFuseTicks() {
		return fuseTicks;
	}

	@Override
	protected void onImpact(MovingObjectPosition pos) {	
		if(this.motionY <= 0)
		{
			this.motionY *= -0.2D;
		}
		else
		{
			this.motionY *= 0.2D;
		}
		this.posY += 0.1D;
		this.motionX *= 0.3D;
		this.motionZ *= 0.3D;
		this.worldObj.playSoundAtEntity(this, "random.fuse", 0.7f, 0.8f);
	}
}
