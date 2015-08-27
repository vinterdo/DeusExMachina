package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModFluids;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TEGrayMatterFabricatorMaster extends TEIMultiblockMaster implements IFluidHandler, IEnergyReceiver
{
	public static final int	ESSENCE_MAX_STORAGE	= 1000;
	public static final int	MATTER_MAX_STORAGE	= 1000;
	public static final int	FLUID_TANK_CAPACITY	= 10000;
	public static final int	ENERGY_CAPACITY		= 100000;
	public static final int	ENERGY_CONSUME		= 320;
	
	@Synchronized(id = 0)
	@NBTSaved(name = "essence")
	public int essence;
	
	@Synchronized(id = 1)
	@NBTSaved(name = "matter")
	public int matter;
	
	@Synchronized(id = 2)
	@NBTSaved(name = "tank")
	public FluidTank tank = new FluidTank(FLUID_TANK_CAPACITY);
	
	@Synchronized(id = 3)
	@NBTSaved(name = "energy")
	public EnergyStorage energy = new EnergyStorage(ENERGY_CAPACITY);
	
	public TEGrayMatterFabricatorMaster()
	{
		super();
		setNumOfStacks(3);
		tank.setFluid(new FluidStack(ModFluids.grayMatter, 0));
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!worldObj.isRemote && isFormed())
		{
			if (essence > 0 && matter > 0 && tank.getFluidAmount() < tank.getCapacity()
					&& energy.getEnergyStored() > ENERGY_CONSUME)
			{
				tank.fill(new FluidStack(ModFluids.grayMatter, 1), true);
				
				essence--;
				matter--;
				energy.modifyEnergyStored(-1 * ENERGY_CONSUME);
			}
			
			if (matter == 0 && (getStackInSlot(0) != null) && getStackInSlot(0).getItem() == ModItems.steelIngot)
			{
				matter += MATTER_MAX_STORAGE;
				decrStackSize(0, 1);
			}
			
			if (essence == 0 && (stacks.get(2) == null || stacks.get(2).stackSize < stacks.get(2).getMaxStackSize())
					&& (getStackInSlot(1) != null))
			{
				essence += ESSENCE_MAX_STORAGE;
				decrStackSize(1, 1);
				if (stacks.get(2) == null)
				{
					stacks.set(2, new ItemStack(ModItems.essenceContainer, 1));
				} else
				{
					stacks.get(2).stackSize++;
				}
			}
		}
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void tryForming()
	{
		members = new ArrayList<TEMultiblock>();
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 2; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 2, yCoord + y - 1, zCoord + z - 2);
					
					if (te instanceof TEGrayMatterFabricator || te instanceof TEIERGrayMatterFabricatorEnergyPort)
					{
						members.add((TEMultiblock) te);
					}
				}
			}
		}
		super.tryForming();
	}
	
	@Override
	public boolean isProperMultiblock()
	{
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 2; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 2, yCoord + y - 1, zCoord + z - 2);
					if (te == null)
					{
						return false;
					} else if (te instanceof TEIERGrayMatterFabricatorEnergyPort)
					{
						if (y == 0 && ((x == 0 || x == 4) && z == 2) || (x == 2 && (z == 0 || z == 4)))
						{
							TEMultiblock tem = (TEMultiblock) te;
							if (tem.getMaster() != null)
								return false;
						} else
							return false;
					} else if (te instanceof TEGrayMatterFabricator)
					{
						TEMultiblock tem = (TEMultiblock) te;
						if (tem.getMaster() != null)
							return false;
					} else if (te != this)
					{
						return false;
					}
					
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String getInventoryName()
	{
		return ModBlocks.grayMatterFabricatorMaster.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		if (slot == 0 && itemStack.getItem() == ModItems.steelIngot)
			return true;
		if (slot == 1 && itemStack.getItem() == ModItems.essence)
			return true;
			
		return false;
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
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if (resource.getFluid() == ModFluids.grayMatter)
			return drain(from, resource.amount, doDrain);
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return tank.drain(maxDrain, doDrain);
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return false;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return fluid == ModFluids.grayMatter;
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
		return energy.receiveEnergy(maxReceive, simulate);
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
