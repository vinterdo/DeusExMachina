package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.recipes.RecipeMacerator;
import com.vinterdo.deusexmachina.reference.ModIds;
import com.vinterdo.deusexmachina.tileentity.base.TEI;

import cofh.thermalexpansion.util.crafting.PulverizerManager;
import cofh.thermalexpansion.util.crafting.PulverizerManager.RecipePulverizer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import net.minecraft.item.ItemStack;

public class TEEssenceMacerator extends TEI
{
	@Synchronized(id = 0)
	@NBTSaved(name = "progress")
	public int	progress;
	@Synchronized(id = 1)
	@NBTSaved(name = "power")
	public int	power;
	@Synchronized(id = 2)
	@NBTSaved(name = "progressTarget")
	public int	progressTarget;
	
	public TEEssenceMacerator()
	{
		super();
		
		setNumOfStacks(8);
	}
	
	public float getProgressPercent()
	{
		return (float) progress / (float) progressTarget;
	}
	
	public static final int	PROGRESS_MULTIPLER	= 100;
	public static final int	ESSENCE_POWER		= 100;
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			if (power > 0)
			{
				if (Loader.isModLoaded(ModIds.TE))
				{
					processOresTE();
				} else
				{
					processOresVanilla();
				}
			} else
			{
				if (stacks.get(3) != null && stacks.get(3).getItem() == ModItems.essence && isSpaceForCapsules())
				{
					decrStackSize(3, 1);
					addItemToRange(new ItemStack(ModItems.essenceContainer), 7, 8);
					
					power += ESSENCE_POWER;
				}
			}
		}
	}
	
	private boolean isSpaceForCapsules()
	{
		return ((stacks.get(7) != null && stacks.get(7).stackSize < stacks.get(7).getMaxStackSize())
				|| stacks.get(7) == null);
	}
	
	private void processOresVanilla()
	{
		for (int i = 0; i < 3; i++)
		{
			if (stacks.get(i) != null)
			{
				RecipeMacerator recipe = RecipeMacerator.getRecipe(stacks.get(i));
				if (recipe != null && isSpaceInOutput(recipe.getOutput()))
				{
					progressTarget = recipe.getEnergy();
					power--;
					progress += PROGRESS_MULTIPLER;
					if (progress > recipe.getEnergy())
					{
						addItemToRange(recipe.getOutput().copy(), 4, 7);
						consumeItem(i);
						progress = 0;
					}
					return;
				}
			}
		}
	}
	
	@Optional.Method(modid = ModIds.TE)
	private void processOresTE()
	{
		for (int i = 0; i < 3; i++)
		{
			if (stacks.get(i) != null)
			{
				RecipePulverizer recipe = PulverizerManager.getRecipe(stacks.get(i));
				if (recipe != null && isSpaceInOutput(recipe.getPrimaryOutput()))
				{
					progressTarget = recipe.getEnergy();
					power--;
					progress += PROGRESS_MULTIPLER;
					if (progress > recipe.getEnergy())
					{
						addItemToRange(recipe.getPrimaryOutput().copy(), 4, 7);
						consumeItem(i);
						progress = 0;
					}
					return;
				}
			}
		}
	}
	
	private void consumeItem(int slot)
	{
		decrStackSize(slot, 1);
	}
	
	private boolean isSpaceInOutput(ItemStack input)
	{
		int space = this.calcSpaceForStack(input, 4, 7);
		return space >= input.stackSize;
	}
	
	@Override
	public String getInventoryName()
	{
		return ModBlocks.essenceMacerator.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		if (slot < 3)
			return true;
		if (slot == 3 && itemStack.getItem() == ModItems.essence)
			return true;
		return false;
	}
}
