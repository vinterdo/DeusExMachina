package com.vinterdo.deusexmachina.fluids;

import net.minecraftforge.fluids.Fluid;

public class EtherFluid extends Fluid 
{

	public EtherFluid() 
	{
		super("metalFluid");
		setLuminosity(5);
		setDensity(1000);
		setTemperature(295);
		setViscosity(1500);
		setGaseous(false);
	}

}
