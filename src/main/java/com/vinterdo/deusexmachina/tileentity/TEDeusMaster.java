package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModFluids;
import com.vinterdo.deusexmachina.multiblockstructures.MultiBlockStructure;
import com.vinterdo.deusexmachina.multiblockstructures.StructureDeus;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TEDeusMaster extends TEIMultiblockMaster implements IFluidHandler, IEnergyReceiver
{
	private static final int	FLUID_TANK_CAPACITY	= 10000;
	private static final int	ENERGY_CAPACITY		= 100000;
	private static final int	GM_PER_TICK			= 10;
	@Synchronized(id = 0)
	@NBTSaved(name = "progress")
	public int					progress;
	@Synchronized(id = 1)
	@NBTSaved(name = "progressTarget")
	public int					progressTarget;
	@Synchronized(id = 2)
	@NBTSaved(name = "tank")
	public FluidTank			tank				= new FluidTank(FLUID_TANK_CAPACITY);
	@Synchronized(id = 3)
	@NBTSaved(name = "energy")
	public EnergyStorage		energy				= new EnergyStorage(ENERGY_CAPACITY);
	@Synchronized(id = 4)
	@NBTSaved(name = "gmConsumed")
	public int					gmConsumed;
	@Synchronized(id = 5)
	@NBTSaved(name = "gmTarget")
	public int					gmTarget;
	
	public static final MultiBlockStructure structure = new StructureDeus();
	
	public TEDeusMaster()
	{
		super();
		tank.setFluid(new FluidStack(ModFluids.grayMatter, 0));
		setNumOfStacks(17);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
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
		return ModBlocks.grayMatterCrafterMaster.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		
		return false;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if (resource.getFluid() == ModFluids.grayMatter)
			return tank.fill(resource, doFill);
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return null;
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return fluid == ModFluids.grayMatter;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[]
		{ this.tank.getInfo() };
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
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		tank.readFromNBT(tag);
		energy.readFromNBT(tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tank.writeToNBT(tag);
		energy.writeToNBT(tag);
	}
}
