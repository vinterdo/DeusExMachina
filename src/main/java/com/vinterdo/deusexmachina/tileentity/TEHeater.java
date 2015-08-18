package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEI;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class TEHeater extends TEI
{
	@Synchronized(id = 1)
	@NBTSaved(name = "burningTime")
	public int burningTime = 0;
	
	public TEHeater()
	{
		super();
		this.setNumOfStacks(9);
	}
	
	@Override
	public void updateEntity()
	{
		if (burningTime > 0)
		{
			--burningTime;
			if (burningTime == 0)
			{
				this.worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1,
						zCoord + 1);
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		} else
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			for (int i = 0; i < 9; i++)
			{
				int burn = TileEntityFurnace.getItemBurnTime(stacks.get(i));
				if (burn > 0)
				{
					decrStackSize(i, 1);
					burningTime += burn;
					this.worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1,
							yCoord + 1, zCoord + 1);
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					break;
				}
			}
		}
	}
	
	public boolean isWorking()
	{
		return burningTime > 0;
	}
	
	@Override
	public String getInventoryName()
	{
		return ModBlocks.heater.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return TileEntityFurnace.getItemBurnTime(itemStack) > 0;
	}
}
