package upcraftlp.shadowcreatures.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import upcraftlp.shadowcreatures.tile.TileEntityAltar;

public class BlockRunicAltar extends NonOpaqueBlock implements ITileEntityProvider {
	
	public BlockRunicAltar() {
		super("runic_altar", Material.rock, 25, 6000);
		this.setHarvestLevel("pickaxe", 2);
		this.setLightLevel(40.0f);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAltar();
	}
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityAltar altar = (TileEntityAltar) worldIn.getTileEntity(pos);
		@SuppressWarnings("unused")
		InventoryBasic inventory = altar.inventory;
		@SuppressWarnings("unused")
		ItemStack itemStack = playerIn.getCurrentEquippedItem();
		
		return true;
	}
	
}
