package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TEGrayMatterCrafterMaster extends TEIMultiblockMaster implements IFluidHandler
{
	@Synchronized(id = 0)
	@NBTSaved(name = "progress")
	public int	progress;
	@Synchronized(id = 1)
	@NBTSaved(name = "progressTarget")
	public int	progressTarget;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
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
					
					if (te instanceof TEGrayMatterCrafter)
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
		return false;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return null;
	}
}
