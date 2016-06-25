package upcraftlp.shadowcreatures.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMagicBomb extends EntityThrowable {
	
	public EntityMagicBomb(World world)
	{
		super(world);
		this.isImmuneToFire = true;
	}
	
	public EntityMagicBomb(World world, EntityLivingBase entity)
	{
		super(world, entity);
		this.isImmuneToFire = true;
	}
	
	public EntityMagicBomb(World world, double x, double y, double z)
	{
		super(world, x, y, z);
		this.isImmuneToFire = true;
	}
	
	public boolean getOnFire()
	{
		return false;
	}
	
	private void DestroySelf()
	{
		//this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 4.5f, false);
		//this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
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
		
		//TODO Magic Particles
		
		super.onUpdate();
	}
	
	

	@Override
	protected void onImpact(MovingObjectPosition pos) {	
		//TODO Magic Explosion and cause Damage
		this.DestroySelf();
	}
}
