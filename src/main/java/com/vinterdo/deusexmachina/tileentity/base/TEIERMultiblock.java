package com.vinterdo.deusexmachina.tileentity.base;

import cofh.api.energy.IEnergyReceiver;
import net.minecraftforge.common.util.ForgeDirection;

public class TEIERMultiblock extends TEIUniversalMultiblock implements IEnergyReceiver
{
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return getMaster() == null ? false : ((IEnergyReceiver) getMaster()).canConnectEnergy(from);
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return getMaster() == null ? 0 : ((IEnergyReceiver) getMaster()).receiveEnergy(from, maxReceive, simulate);
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return getMaster() == null ? 0 : ((IEnergyReceiver) getMaster()).getEnergyStored(from);
	}
	
	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return getMaster() == null ? 0 : ((IEnergyReceiver) getMaster()).getMaxEnergyStored(from);
	}
}
