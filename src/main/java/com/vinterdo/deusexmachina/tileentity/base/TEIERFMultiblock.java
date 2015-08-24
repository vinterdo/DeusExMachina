package com.vinterdo.deusexmachina.tileentity.base;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TEIERFMultiblock extends TEIERMultiblock implements IFluidHandler
{
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return master == null ? 0 : ((IFluidHandler) master).fill(from, resource, doFill);
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return master == null ? null : ((IFluidHandler) master).drain(from, resource, doDrain);
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return master == null ? null : ((IFluidHandler) master).drain(from, maxDrain, doDrain);
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return master == null ? false : ((IFluidHandler) master).canFill(from, fluid);
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return master == null ? false : ((IFluidHandler) master).canDrain(from, fluid);
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return master == null ? null : ((IFluidHandler) master).getTankInfo(from);
	}
	
}
