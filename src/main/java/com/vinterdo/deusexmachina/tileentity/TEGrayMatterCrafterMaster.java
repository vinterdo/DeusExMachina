package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModFluids;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.recipes.RecipeGrayMatter;
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

public class TEGrayMatterCrafterMaster extends TEIMultiblockMaster implements IFluidHandler, IEnergyReceiver
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
	
	public TEGrayMatterCrafterMaster()
	{
		super();
		tank.setFluid(new FluidStack(ModFluids.grayMatter, 0));
		setNumOfStacks(17);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!worldObj.isRemote && isFormed())
		{
			ItemStack grid[][] = new ItemStack[4][4];
			for (int y = 0; y < 4; y++)
			{
				for (int x = 0; x < 4; x++)
				{
					grid[x][y] = stacks.get(y * 4 + x);
				}
			}
			RecipeGrayMatter rec = RecipeGrayMatter.getMatchingRecipe(grid);
			if (rec != null && super.calcSpaceForStack(rec.output, 16, 17) >= rec.output.stackSize)
			{
				gmTarget = rec.grayMatter;
				progressTarget = rec.time;
				
				if (gmConsumed >= gmTarget && progress >= progressTarget)
				{
					
					ItemStack output = rec.output.copy();
					output.getItem().onCreated(output, this.getWorldObj(), null);
					this.addItemToRange(output, 16, 17);
					
					for (int y = 0; y < 4; y++)
					{
						for (int x = 0; x < 4; x++)
						{
							if (rec.grid[x][y] != null)
								this.decrStackSize(y * 4 + x, rec.grid[x][y].stackSize);
						}
					}
					progress = 0;
					gmConsumed = 0;
				} else
				{
					if (energy.getEnergyStored() > rec.rfPerTick)
					{
						if (progress < progressTarget)
							progress++;
						energy.setEnergyStored(energy.getEnergyStored() - rec.rfPerTick);
					}
					
					if (tank.getFluidAmount() > 0 && gmConsumed < gmTarget)
					{
						if (tank.getFluidAmount() >= GM_PER_TICK)
						{
							tank.drain(GM_PER_TICK, true);
							gmConsumed += GM_PER_TICK;
						} else
						{
							gmConsumed += tank.getFluidAmount();
							tank.drain(tank.getFluidAmount(), true);
						}
					}
					
				}
			}
		}
	}
	
	@Override
	public void tryForming()
	{
		members = new ArrayList<TEMultiblock>();
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				for (int z = 0; z < 3; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 2, zCoord + z - 1);
					
					if (te instanceof TEGrayMatterCrafter || te instanceof TEGrayMatterCrafterPort)
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
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				for (int z = 0; z < 3; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 2, zCoord + z - 1);
					if (te == null)
					{
						return false;
					} else if (te instanceof TEGrayMatterCrafter)
					{
						TEMultiblock tem = (TEMultiblock) te;
						if (tem.getMaster() != null)
							return false;
					} else if (te instanceof TEGrayMatterCrafterPort)
					{
						if (!(y == 0 && (x == 1 && (z == 0 || z == 2) || z == 1 && (x == 0 || x == 2))))
						{
							return false;
						}
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
