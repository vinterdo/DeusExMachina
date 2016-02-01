package com.vinterdo.deusexmachina.fluids;

import net.minecraftforge.fluids.Fluid;

public class MetalFluid extends Fluid 
{

	public MetalFluid() 
	{
		super("metalFluid");
		setLuminosity(5);
		setDensity(1000);
		setTemperature(295);
		setViscosity(1500);
		setGaseous(false);
	}

}
