package upcraftlp.shadowcreatures.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockLevitator extends NonOpaqueBlock {

	public BlockLevitator() {
		super("levitator", Material.wood, 2.0f, 15.0f);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 1);
		
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
		float height = 0.125f;
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1 - height, pos.getZ() + 1);
	}
	
	
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.fallDistance = 0;
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 5, 3, true, false));
			player.jump();
			//TODO Smooth! Levitation
		}
	}

}

