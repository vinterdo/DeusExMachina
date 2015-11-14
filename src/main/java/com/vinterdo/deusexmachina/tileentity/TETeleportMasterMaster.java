package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.multiblockstructures.MultiBlockStructure;
import com.vinterdo.deusexmachina.multiblockstructures.StructureTeleportMaster;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TETeleportMasterMaster extends TEIMultiblockMaster implements IEnergyReceiver
{
	public static final int					ENERGY_CAPACITY	= 100000;
	public static final int					ENERGY_CONSUME	= 320;
															
	@Synchronized(id = 0)
	@NBTSaved(name = "energy")
	public EnergyStorage					energy			= new EnergyStorage(ENERGY_CAPACITY);
															
	public static final MultiBlockStructure	structure		= new StructureTeleportMaster();
															
	public TETeleportMasterMaster()
	{
		super();
		setNumOfStacks(52);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!worldObj.isRemote && isFormed())
		{
		
		}
	}
	
	@Override
	public void tryForming()
	{
		members = new ArrayList<TEMultiblock>();
		structure.getMembers(this, members);
		super.tryForming();
	}
	
	@Override
	public boolean isProperMultiblock()
	{
		return structure.isValidMultiblock(this);
	}
	
	@Override
	public String getInventoryName()
	{
		return ModBlocks.teleportMasterMaster.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return false; //TODO : implement slots
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		energy.readFromNBT(tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		energy.writeToNBT(tag);
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		if (energy.getEnergyStored() < energy.getMaxEnergyStored())
			return energy.receiveEnergy(maxReceive, simulate);
		else
			return 0;
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return energy.getEnergyStored();
	}
	
	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return energy.getMaxEnergyStored();
	}
}
