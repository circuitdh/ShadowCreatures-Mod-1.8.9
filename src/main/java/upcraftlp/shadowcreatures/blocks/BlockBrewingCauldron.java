package upcraftlp.shadowcreatures.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.tile.TileEntityBrewingCauldron;

public class BlockBrewingCauldron extends NonOpaqueBlock implements ITileEntityProvider {

	public BlockBrewingCauldron() {
		super("brewing_cauldron", Material.iron, 5.0f, 20.0f);
		this.setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBrewingCauldron();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(playerIn.isSneaking()) return false;
		TileEntityBrewingCauldron te = (TileEntityBrewingCauldron) worldIn.getTileEntity(pos);
		te.interact(playerIn);
		return true;
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		TileEntityBrewingCauldron te = (TileEntityBrewingCauldron) worldIn.getTileEntity(pos);
		te.drop(pos);
		super.onBlockHarvested(worldIn, pos, state, player);
	}

}
