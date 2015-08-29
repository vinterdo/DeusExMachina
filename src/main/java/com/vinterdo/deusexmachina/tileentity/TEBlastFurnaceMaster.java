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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
	
	public static final int	PROGRESS_MULT		= 8;
	public static final int	SMELT_TIME			= 400;
	public static final int	SMELT_TIME_STEEL	= 3200;
	public static final int	HEATER_MULT			= 1;
	public static final int	MAX_BURNING_TIME	= 10000;	// it only apply to burningTime stored from heaters
	public static final int	BURN_CONSUME		= 8;
	
	public static final MultiBlockStructure structure = new StructureBlastFurnace();
	
	public TEBlastFurnaceMaster()
	{
		super();
		setNumOfStacks(9);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!worldObj.isRemote && formed)
		{
			getBurningTimeFromHeaters();
			if (burningTime > BURN_CONSUME)
			{
				smeltItems();
			} else
			{
				burnFuel();
			}
		}
		
		if (worldObj.isRemote)
		{
			spawnParticles();
		}
	}
	
	private void getBurningTimeFromHeaters()
	{
		for (int x = -1; x < 2; x++)
		{
			for (int z = -1; z < 2; z++)
			{
				TEHeater te = worldObj.getTileEntity(xCoord + x, yCoord - 4, zCoord + z) instanceof TEHeater
						? (TEHeater) worldObj.getTileEntity(xCoord + x, yCoord - 4, zCoord + z) : null;
				if (te != null && te.isWorking() && burningTime < MAX_BURNING_TIME)
				{
					burningTime += HEATER_MULT;
				}
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
				decrStackSize(i + 3, 1);
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
	}
	
	private void smeltItems()
	{
		burningTime -= BURN_CONSUME;
		
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
					
					if (spaceLeft >= target.stackSize)
					{
						progress += PROGRESS_MULT;
						if (progress > progressTarget)
						{
							addItemToRange(target, 6, 9);
							decrStackSize(i, 1);
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
	
	@SideOnly(Side.CLIENT)
	private void spawnParticles()
	{
		if (isFormed() && burningTime > BURN_CONSUME)
		{
			worldObj.spawnParticle("smoke", xCoord + Math.random() * 2.0 - 0.5, yCoord + Math.random() * 0.4 - 2.9,
					zCoord + Math.random() * 2.0 - 0.5, 0.0D, 0.0D, 0.0D);
			worldObj.spawnParticle("flame", xCoord + Math.random() * 2.0 - 0.5, yCoord + Math.random() * 0.4 - 2.9,
					zCoord + Math.random() * 2.0 - 0.5, 0.0D, 0.0D, 0.0D);
			for (int i = 0; i < 3; i++)
				worldObj.spawnParticle("smoke", xCoord + Math.random(), yCoord + 0.5, zCoord + Math.random(), 0.0D,
						0.1D, 0.0D);
		}
	}
}
