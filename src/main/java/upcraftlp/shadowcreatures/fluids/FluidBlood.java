package upcraftlp.shadowcreatures.fluids;

import java.awt.Color;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidBlood extends Fluid {

	public FluidBlood() {
		super("fluid_blood", new ResourceLocation("sc:blocks/fluid_generic_still"), new ResourceLocation("sc:blocks/fluid_generic_flow"));
		this.setTemperature(309);
		this.setDensity(1066);
		this.setLuminosity(3);
		this.setViscosity(3500);
	}
	
	
	
	@Override
	public int getColor() {
		return new Color(180, 33, 33).getRGB();
	}
	

}
