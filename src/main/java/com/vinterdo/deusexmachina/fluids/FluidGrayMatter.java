package com.vinterdo.deusexmachina.fluids;

import net.minecraftforge.fluids.Fluid;

public class FluidGrayMatter extends Fluid
{

	public FluidGrayMatter() 
	{
		super("grayMatter");
		setLuminosity(0);
		setDensity(2000);
		setTemperature(295);
		setViscosity(8000);
		setGaseous(false);
	}

}
