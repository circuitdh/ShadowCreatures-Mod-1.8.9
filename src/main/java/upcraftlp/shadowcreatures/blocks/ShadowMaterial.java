package upcraftlp.shadowcreatures.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ShadowMaterial extends Material {
	
	public static final Material mirrorGlass = new ShadowMaterial(MapColor.airColor).setImmovableMobility();
	
	public ShadowMaterial(MapColor color)
    {
		super(color);
    }
	
}
