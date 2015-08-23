package com.vinterdo.deusexmachina.tileentity.base;

import cofh.api.energy.IEnergyReceiver;
import net.minecraftforge.common.util.ForgeDirection;

public class TEIERMultiblock extends TEIUniversalMultiblock implements IEnergyReceiver
{
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return master == null ? false : ((IEnergyReceiver) master).canConnectEnergy(from);
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return master == null ? 0 : ((IEnergyReceiver) master).receiveEnergy(from, maxReceive, simulate);
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return master == null ? 0 : ((IEnergyReceiver) master).getEnergyStored(from);
	}
	
	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return master == null ? 0 : ((IEnergyReceiver) master).getMaxEnergyStored(from);
	}
}
