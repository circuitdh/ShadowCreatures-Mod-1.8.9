package upcraftlp.shadowcreatures.blocks;

import net.minecraft.block.material.Material;

public class BlockWeaponForge extends NonOpaqueRotationalBlock {

	public BlockWeaponForge() {
		super("weaponforge", Material.anvil, 5.0f, 30.0f);
		this.setHarvestLevel("pickaxe", 2);
	}

	
	
}
