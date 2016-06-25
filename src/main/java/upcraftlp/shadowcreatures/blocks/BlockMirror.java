package upcraftlp.shadowcreatures.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import upcraftlp.shadowcreatures.init.ShadowBlocks;
import upcraftlp.shadowcreatures.init.ShadowDimension;
import upcraftlp.shadowcreatures.world.DimTeleporterIdris;

public class BlockMirror extends NonOpaqueRotationalBlock {

	public BlockMirror(String half) {
		super("mirror", ShadowMaterial.mirrorGlass, 0.3f, 1.5f);
		this.setStepSound(soundTypeGlass);
		if (half == "top") this.setCreativeTab(null);
		this.setLightLevel(35);
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {

		if (state.getBlock() == ShadowBlocks.mirror_base
				&& worldIn.getBlockState(pos.up()).getBlock() == ShadowBlocks.mirror_top) {
			worldIn.setBlockToAir(pos);
			worldIn.setBlockToAir(pos.up());
		}
		if (state.getBlock() == ShadowBlocks.mirror_top
				&& worldIn.getBlockState(pos.down()).getBlock() == ShadowBlocks.mirror_base) {
			worldIn.setBlockToAir(pos);
			worldIn.setBlockToAir(pos.down());
		}
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		if (world.getBlockState(pos).getBlock() == ShadowBlocks.mirror_base
				&& world.getBlockState(pos.up()).getBlock() == ShadowBlocks.mirror_top) {
			world.setBlockToAir(pos);
			world.setBlockToAir(pos.up());
		}
		if (world.getBlockState(pos).getBlock() == ShadowBlocks.mirror_top
				&& world.getBlockState(pos.down()).getBlock() == ShadowBlocks.mirror_base) {
			world.setBlockToAir(pos);
			world.setBlockToAir(pos.down());
		}
	}

	@Override
	public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, BlockPos pos, Entity entity) {
		if (entity instanceof EntityDragon || entity instanceof EntityDragonPart) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return null;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn.ridingEntity == null && entityIn.riddenByEntity == null && !worldIn.isRemote
				&& worldIn instanceof WorldServer && entityIn instanceof EntityPlayer) {
			worldIn.theProfiler.startSection("portal");
			int dim = 0;
			if (worldIn.provider.getDimensionId() == ShadowDimension.getId()) {
				dim = 0;
			} else {
				dim = ShadowDimension.getId();
			}
				
			EntityPlayerMP player = (EntityPlayerMP) entityIn;
			DimTeleporterIdris teleporter = new DimTeleporterIdris(player.getServerForPlayer());
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, dim, teleporter);
			worldIn.theProfiler.endSection();

		}
		else
		{
			entityIn.setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		BlockPos newPos = pos.up();
		if (placer instanceof EntityPlayer)
			worldIn.setBlockState(newPos, ShadowBlocks.mirror_top.getDefaultState().withProperty(FACING,
					placer.getHorizontalFacing().getOpposite()));
		return ShadowBlocks.mirror_base.getDefaultState().withProperty(FACING,
				placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos.up()).getBlock().isReplaceable(worldIn, pos.up())) {
			return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
		} else {
			return false;
		}
	}

}