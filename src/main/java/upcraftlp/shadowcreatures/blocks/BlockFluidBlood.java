package upcraftlp.shadowcreatures.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import upcraftlp.shadowcreatures.init.ShadowBlocks;

public class BlockFluidBlood extends BlockFluidClassic{

	public BlockFluidBlood(Fluid fluid) {
		super(fluid, Material.water);
		this.setUnlocalizedName("fluid_blood");
		this.setRegistryName("fluid_blood");
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		mix(world, pos, state);
	}
	
	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
		super.onNeighborBlockChange(world, pos, state, neighborBlock);
		mix(world, pos, state);
	}
	
	public void mix(World world, BlockPos pos, IBlockState state)
	{
		for (EnumFacing facing : EnumFacing.values())
		{
			Block block = world.getBlockState(pos.offset(facing)).getBlock();
			if (block == Blocks.cauldron)
			{
				world.setBlockState(pos.offset(facing), ShadowBlocks.brewing_cauldron.getDefaultState());
			}
		}
	}
	
}
