package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.multiblockstructures.MultiBlockStructure;
import com.vinterdo.deusexmachina.multiblockstructures.StructureBlastFurnace;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class TEBlastFurnaceMaster extends TEIMultiblockMaster
{
	@Synchronized(id = 0)
	@NBTSaved(name = "burningTime")
	public int	burningTime;
	@Synchronized(id = 1)
	@NBTSaved(name = "progress")
	public int	progress;
	@Synchronized(id = 2)
	@NBTSaved(name = "progressTarget")
	public int	progressTarget;
	
	public static final int	PROGRESS_MULT		= 1;
	public static final int	SMELT_TIME			= 100;
	public static final int	SMELT_TIME_STEEL	= 400;
	
	public static final MultiBlockStructure structure = new StructureBlastFurnace();
	
	public TEBlastFurnaceMaster()
	{
		super();
		this.setNumOfStacks(9);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!worldObj.isRemote && formed)
		{
			if (burningTime > 0)
			{
				smeltItems();
			} else
			{
				burnFuel();
			}
		}
	}
	
	private void burnFuel()
	{
		for (int i = 0; i < 3; i++)
		{
			if (stacks.get(i + 3) != null && TileEntityFurnace.isItemFuel(stacks.get(i + 3)))
			{
				burningTime += TileEntityFurnace.getItemBurnTime(stacks.get(i + 3));
				this.decrStackSize(i + 3, 1);
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
	}
	
	private void smeltItems()
	{
		burningTime -= 1;
		
		for (int i = 0; i < 3; i++)
		{
			if (stacks.get(i) != null)
			{
				if (stacks.get(i).getItem() == ModItems.steelDust
						|| FurnaceRecipes.smelting().getSmeltingResult(stacks.get(i)) != null)
				{
					ItemStack target;
					if (stacks.get(i).getItem() == ModItems.steelDust)
					{
						progressTarget = SMELT_TIME_STEEL;
						target = new ItemStack(ModItems.steelIngot, 1);
					} else
					{
						progressTarget = SMELT_TIME;
						target = FurnaceRecipes.smelting().getSmeltingResult(stacks.get(i)).copy();
						if (target.stackSize == 0)
							target.stackSize = 1;
					}
					
					int spaceLeft = calcSpaceForStack(target, 6, 9);
					
					if (spaceLeft - target.stackSize > 0)
					{
						progress += PROGRESS_MULT;
						if (progress > progressTarget)
						{
							addItemToRange(target, 6, 9);
							this.decrStackSize(i, 1);
							progress -= progressTarget;
						}
					}
					break;
				}
			}
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
		return ModBlocks.blastFurnaceMaster.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		if (slot <= 2)
			return true;
		if (slot > 2 && slot <= 5)
			return TileEntityFurnace.getItemBurnTime(itemStack) > 0;
			
		return false;
	}
}
