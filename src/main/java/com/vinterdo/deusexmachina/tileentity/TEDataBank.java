package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.recipes.RecipeGrayMatter;
import com.vinterdo.deusexmachina.tileentity.base.TEI;

import net.minecraft.item.ItemStack;

public class TEDataBank extends TEI
{
	public TEDataBank()
	{
		super();
		this.setNumOfStacks(9 * 9);
	}
	
	@Override
	public void updateEntity()
	{
	
	}
	
	@Override
	public String getInventoryName()
	{
		return ModBlocks.dataBank.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return itemStack.getItem() == ModItems.researchCore;
	}
	
	public boolean containsRecipe(RecipeGrayMatter rec)
	{
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				ItemStack coreRes = stacks.get(y * 9 + x);
				if (coreRes != null && coreRes.stackTagCompound != null
						&& coreRes.stackTagCompound.getString("researchName").equals(rec.getName()))
				{
					return true;
				}
			}
		}
		return false;
	}
}
