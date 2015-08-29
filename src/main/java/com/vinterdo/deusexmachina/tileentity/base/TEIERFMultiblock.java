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
		return getMaster() == null ? 0 : ((IFluidHandler) getMaster()).fill(from, resource, doFill);
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return getMaster() == null ? null : ((IFluidHandler) getMaster()).drain(from, resource, doDrain);
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return getMaster() == null ? null : ((IFluidHandler) getMaster()).drain(from, maxDrain, doDrain);
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return getMaster() == null ? false : ((IFluidHandler) getMaster()).canFill(from, fluid);
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return getMaster() == null ? false : ((IFluidHandler) getMaster()).canDrain(from, fluid);
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return getMaster() == null ? null : ((IFluidHandler) getMaster()).getTankInfo(from);
	}
	
}
