package com.vinterdo.deusexmachina.fluids;

import net.minecraftforge.fluids.Fluid;

public class LifeFluid extends Fluid 
{

	public LifeFluid() 
	{
		super("lifeFluid");
		setLuminosity(5);
		setDensity(1000);
		setTemperature(295);
		setViscosity(1500);
		setGaseous(false);
	}

}
