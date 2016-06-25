package upcraftlp.shadowcreatures.fluids;

import java.awt.Color;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidHolyWater extends Fluid {

	public FluidHolyWater() {
		super("fluid_holy_water", new ResourceLocation("sc:blocks/fluid_generic_still"), new ResourceLocation("sc:blocks/fluid_generic_flow"));
		this.setTemperature(295);
		this.setDensity(1000);
		this.setLuminosity(7);
		this.setViscosity(1000);
	}
	
	
	
	@Override
	public int getColor() {
		return new Color(204, 249, 255).getRGB();
	}
	

}
