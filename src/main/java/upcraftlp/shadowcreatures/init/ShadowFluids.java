package upcraftlp.shadowcreatures.init;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import upcraftlp.shadowcreatures.blocks.BlockFluidBlood;
import upcraftlp.shadowcreatures.blocks.BlockFluidHolyWater;
import upcraftlp.shadowcreatures.fluids.FluidBlood;
import upcraftlp.shadowcreatures.fluids.FluidHolyWater;
import upcraftlp.shadowcreatures.items.BasicBucket;
import upcraftlp.shadowcreatures.util.BucketHandler;

public class ShadowFluids {

	public static FluidBlood fluidBlood = new FluidBlood();
	public static BlockFluidBlood blockFluidBlood;
	public static BasicBucket bucketBlood;
	
	public static FluidHolyWater fluidHoly = new FluidHolyWater();
	public static BlockFluidHolyWater blockFluidHolyWater;
	public static BasicBucket bucketHoly;
	
	
	public static void create()
	{
		//Blood
		FluidRegistry.registerFluid(fluidBlood);
		blockFluidBlood = new BlockFluidBlood(fluidBlood);
		GameRegistry.registerBlock(blockFluidBlood, "blockFluidBlood");
		registerFluid(blockFluidBlood, "blood");
		bucketBlood  = new BasicBucket(blockFluidBlood);
		GameRegistry.registerItem(bucketBlood, "blood_bucket");
		FluidContainerRegistry.registerFluidContainer(new FluidStack(fluidBlood, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketBlood), new ItemStack(Items.bucket));
		BucketHandler.INSTANCE.buckets.put(blockFluidBlood, bucketBlood);
		
		//Holy Water
		FluidRegistry.registerFluid(fluidHoly);
		blockFluidHolyWater = new BlockFluidHolyWater(fluidHoly);
		GameRegistry.registerBlock(blockFluidHolyWater, "blockFluidHolyWater");
		registerFluid(blockFluidHolyWater, "holy_water");
		bucketHoly  = new BasicBucket(blockFluidHolyWater);
		GameRegistry.registerItem(bucketHoly, "holy_water_bucket");
		FluidContainerRegistry.registerFluidContainer(new FluidStack(fluidHoly, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketHoly), new ItemStack(Items.bucket));
		BucketHandler.INSTANCE.buckets.put(blockFluidHolyWater, bucketHoly);
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerFluid(BlockFluidClassic fluidBlock, String name)
	{
		Item item = Item.getItemFromBlock(fluidBlock);
		ModelBakery.registerItemVariants(item);
		final ModelResourceLocation location = new ModelResourceLocation("sc:fluids", name);
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition(){
			@Override
			public ModelResourceLocation getModelLocation(ItemStack itemStack)
			{
				return location;
				
			}
		});
		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase(){
			
			
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return location;
			}
			
		});
	}
	
	public static void registerFluidBuckets()
	{
		registerFluidBucket(bucketBlood);
		registerFluidBucket(bucketHoly);
	}
	
	private static void registerFluidBucket(BasicBucket bucket)
	{
		ShadowItems.registerRender(bucket);
	}
	
}
