package upcraftlp.shadowcreatures.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockWoolPad extends NonOpaqueBlock {

	public BlockWoolPad() {
		super("wool_pad", Material.cloth, 0.8f, 4.0f);
		this.setStepSound(soundTypeCloth);
		
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
		float height = 0.125f;
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 0.5D - height, pos.getZ() + 1);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.fallDistance = 0;
		entity.motionY *= 0;
	}

}

