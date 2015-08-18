package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEI;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TEEssenceProcessor extends TEI
{
	@Synchronized(id = 1)
	@NBTSaved(name = "progress")
	public int				progress;
	public static final int	PROGRESS_MULTIPLER	= 1;
	public static final int	PROGRESS_TARGET		= 100;
	
	public TEEssenceProcessor()
	{
		super();
		this.setNumOfStacks(9);
	}
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			TileEntity heater = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
			
			int essences = 0, capsules = 0;
			for (int i = 0; i < 3; i++)
			{
				if (stacks.get(i) != null && stacks.get(i).getItem() == ModItems.essenceContainer)
				{
					capsules += stacks.get(i).stackSize;
				}
				if (stacks.get(i + 3) != null
						&& stacks.get(i + 3).getItem() == Item.getItemFromBlock(ModBlocks.essenceOre))
				{
					essences += stacks.get(i + 3).stackSize;
				}
			}
			
			if (heater != null && heater instanceof TEHeater && ((TEHeater) heater).isWorking() && essences > 0
					&& capsules > 0)
			{
				progress += PROGRESS_MULTIPLER;
				
				if (progress >= PROGRESS_TARGET)
				{
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					progress -= PROGRESS_TARGET;
					for (int i = 0; i < 3; i++)
					{
						if (stacks.get(6 + i) == null)
						{
							stacks.set(6 + i, new ItemStack(ModItems.essence));
							substractItems();
							return;
						} else if (stacks.get(6 + i).getItem() == ModItems.essence && stacks.get(6 + i).stackSize < 64)
						{
							stacks.get(6 + i).stackSize++;
							substractItems();
							return;
						}
					}
				}
			}
		}
	}
	
	private void substractItems()
	{
		boolean done = false;
		for (int j = 0; j < 3 && !done; j++)
		{
			if (stacks.get(j) != null && stacks.get(j).getItem() == ModItems.essenceContainer)
			{
				decrStackSize(j, 1);
				done = true;
			}
		}
		done = false;
		for (int j = 0; j < 3 && !done; j++)
		{
			if (stacks.get(j + 3) != null && stacks.get(j + 3).getItem() == Item.getItemFromBlock(ModBlocks.essenceOre))
			{
				decrStackSize(j + 3, 1);
				done = true;
			}
		}
	}
	
	@Override
	public String getInventoryName()
	{
		return ModBlocks.essenceProcessor.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		if (slot < 3 && itemStack.getItem() == ModItems.essenceContainer)
			return true;
		if (slot >= 3 && slot < 6 && itemStack.getItem() instanceof ItemBlock)
		{
			Block block = ((ItemBlock) itemStack.getItem()).field_150939_a;
			return block == ModBlocks.essenceOre;
		}
		return false;
	}
}
