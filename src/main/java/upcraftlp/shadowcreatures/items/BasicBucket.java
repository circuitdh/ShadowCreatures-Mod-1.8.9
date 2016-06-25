package upcraftlp.shadowcreatures.items;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import upcraftlp.shadowcreatures.init.ShadowTabs;

public class BasicBucket extends ItemBucket{

	public BasicBucket(Block containedBlock) {
		super(containedBlock);
		this.setUnlocalizedName("bucket_" + containedBlock.getUnlocalizedName().substring(11));
		this.setRegistryName("bucket_" + containedBlock.getUnlocalizedName().substring(11));
		this.setCreativeTab(ShadowTabs.tabShadowHunter);
		this.setContainerItem(Items.bucket);
	}

	
}
